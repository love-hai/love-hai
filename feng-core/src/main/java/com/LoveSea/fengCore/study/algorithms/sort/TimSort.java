package com.LoveSea.fengCore.study.algorithms.sort;

import java.util.Comparator;

/**
 * 一种稳定、自适应、迭代的归并排序，在部分排序数组上运行时，所需的比较次数远少于 n lg(n) 次，而在随机数组上运行时，
 * 其性能可与传统归并排序相媲美。与所有正确的归并排序一样，这种排序稳定且运行时间为 O(n log n)（最坏情况）。在最坏情况下，
 * 这种排序需要临时存储空间来存储 n/2 个对象引用；在最好情况下，它仅需要一小段常量空间。
 * <a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">Tim算法</a>
 * @author Josh Bloch
 */
class TimSort<T> {
    /**
     * 这是将要合并的最小序列。较短的序列将通过调用 binarySort 来加长。如果整个数组小于此长度，则不会执行合并。
     * 此常数应为 2 的幂。在 Tim Peter 的 C 实现中，它是 64，但根据经验，32 在此实现中效果更好。如果您将此常数
     * 设置为非 2 的幂的数字（这种情况不太可能发生），则需要更改 minRunLength 计算。如果减小此常数，则必须更改 TimSort
     * 构造函数中的 stackLen 计算，否则可能会出现 ArrayOutOfBounds 异常。有关所需最小堆栈长度（作为要排序的数组长度和
     * 最小合并序列长度的函数）的讨论，
     * 请参阅<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">listsort.txt</a>。
     */
    // minMerge 最小合并序列长度阈值。
    private static final int MIN_MERGE = 32;

    /**
     * 正在排序的数组。
     */
    private final T[] a;

    /**
     * 这个数组里面对象的比较器。
     */
    private final Comparator<? super T> c;

    /**
     * When we get into galloping mode, we stay there until both runs win less
     * often than MIN_GALLOP consecutive times.
     * gallopingMode 用于控制何时进入 galloping 模式。它初始化为 MIN_GALLOP。mergeLo 和 mergeHi 方法会根据随机数据
     */
    private static final int MIN_GALLOP = 7;

    /**
     * This controls when we get *into* galloping mode.  It is initialized
     * to MIN_GALLOP.  The mergeLo and mergeHi methods nudge it higher for
     * random data, and lower for highly structured data.
     */
    private int minGallop = MIN_GALLOP;

    /**
     * 用于合并的临时数组的最大初始大小。数组可以增长以满足需求。
     * 与 Tim 的原始 C 版本不同，我们在对较小的数组进行排序时不会分配这么多存储空间。
     * 此更改是性能所必需的。
     */
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;

    /**
     * 合并的临时存储。可以在构造函数中选择性地提供工作区数组，如果是，只要它足够大就会被使用。
     */
    private T[] tmp;
    private int tmpBase; // tmp 数组切片的基数
    private int tmpLen;  // tmp 数组切片的长度

    /**
     * A stack of pending runs yet to be merged.  Run i starts at
     * address base[i] and extends for len[i] elements.  It's always
     * true (so long as the indices are in bounds) that:
     * <p>
     * runBase[i] + runLen[i] == runBase[i + 1]
     * <p>
     * so we could cut the storage for this, but it's a minor amount,
     * and keeping all the info explicit simplifies the code.
     */
    private int stackSize = 0;  // 堆栈上待处理的运行数
    // 子数组的开始索引
    private final int[] runBase;
    // 子数组的长度
    private final int[] runLen;

    /**
     * Creates a TimSort instance to maintain the state of an ongoing sort.
     *
     * @param a        the array to be sorted
     * @param c        the comparator to determine the order of the sort
     * @param work     a workspace array (slice)
     * @param workBase origin of usable space in work array
     * @param workLen  usable size of work array
     */
    private TimSort(T[] a, Comparator<? super T> c, T[] work, int workBase, int workLen) {
        // 要排序的数组
        this.a = a;
        // 比较器
        this.c = c;

        // Allocate temp storage (which may be increased later if necessary)
        // 获取要排序数组的长度
        int len = a.length;
        // 如果len 小于 2 * INITIAL_TMP_STORAGE_LENGTH，那么 tlen = len / 2，否则 tlen = INITIAL_TMP_STORAGE_LENGTH
        int tlen = (len < 2 * INITIAL_TMP_STORAGE_LENGTH) ?
                len >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
        // 如果工作区为空或者工作区长度小于 tlen 或者工作区基数加上 tlen 大于工作区长度，那么重新创建一个长度为 tlen 的数组
        if (work == null || workLen < tlen || workBase + tlen > work.length) {
            @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                    (a.getClass().getComponentType(), tlen);
            tmp = newArray;
            tmpBase = 0;
            tmpLen = tlen;
        } else {
            tmp = work;
            tmpBase = workBase;
            tmpLen = workLen;
        }

        /*
         * Allocate runs-to-be-merged stack (which cannot be expanded).  The
         * stack length requirements are described in listsort.txt.  The C
         * version always uses the same stack length (85), but this was
         * measured to be too expensive when sorting "mid-sized" arrays (e.g.,
         * 100 elements) in Java.  Therefore, we use smaller (but sufficiently
         * large) stack lengths for smaller arrays.  The "magic numbers" in the
         * computation below must be changed if MIN_MERGE is decreased.  See
         * the MIN_MERGE declaration above for more information.
         * The maximum value of 49 allows for an array up to length
         * Integer.MAX_VALUE-4, if array is filled by the worst case stack size
         * increasing scenario. More explanations are given in section 4 of:
         * http://envisage-project.eu/wp-content/uploads/2015/02/sorting.pdf
         */
        int stackLen = (len < 120 ? 5 : len < 1542 ? 10 : len < 119151 ? 24 : 49);
        runBase = new int[stackLen];
        runLen = new int[stackLen];
    }

    /*
     * The next method (package private and static) constitutes the
     * entire API of this class.
     */

    /**
     * Sorts the given range, using the given workspace array slice
     * for temp storage when possible. This method is designed to be
     * invoked from public methods (in class Arrays) after performing
     * any necessary array bounds checks and expanding parameters into
     * the required forms.
     *
     * @param a        the array to be sorted
     * @param lo       the index of the first element, inclusive, to be sorted
     * @param hi       the index of the last element, exclusive, to be sorted
     * @param c        the comparator to use
     * @param work     a workspace array (slice)
     * @param workBase origin of usable space in work array
     * @param workLen  usable size of work array
     * @since 1.8
     */
    static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c,
                         T[] work, int workBase, int workLen) {
        assert c != null && a != null && lo >= 0 && lo <= hi && hi <= a.length;
        // 需要排序的元素个数
        int nRemaining = hi - lo;
        if (nRemaining < 2)
            return;  // 小于等于1个元素，不需要排序

        // 如果数组长度小于 MIN_MERGE，那么直接使用二分插入排序
        if (nRemaining < MIN_MERGE) {
            int initRunLen = countRunAndMakeAscending(a, lo, hi, c);
            binarySort(a, lo, hi, lo + initRunLen, c);
            return;
        }

        /**
         * March over the array once, left to right, finding natural runs,
         * extending short natural runs to minRun elements, and merging runs
         * to maintain stack invariant.
         */
        TimSort<T> ts = new TimSort<>(a, c, work, workBase, workLen);
        int minRun = minRunLength(nRemaining);
        do {
            // 在剩余的数组中找到一个有序的序列的长度
            // 如过这个有序数组是严格递减的，那么将这个有序数组翻转
            int runLen = countRunAndMakeAscending(a, lo, hi, c);
            if (runLen < minRun) {
                // 如果找到的有序序列长度小于 minRun，那么将这个有序序列扩展到 minRun
                int force = Math.min(nRemaining, minRun);
                binarySort(a, lo, lo + force, lo + runLen, c);
                runLen = force;
            }

            // Push run onto pending-run stack, and maybe merge
            ts.pushRun(lo, runLen);
            ts.mergeCollapse();

            // Advance to find next run
            lo += runLen;
            nRemaining -= runLen;
        } while (nRemaining != 0);

        // Merge all remaining runs to complete sort
        assert lo == hi;
        ts.mergeForceCollapse();
        assert ts.stackSize == 1;
    }


    /**
     * binarySort : 二分法插入排序
     * @param a 要排序的数组
     * @param lo 要排序的数组的起始位置
     * @param hi 要排序的数组的结束位置
     * @param start 需要进行插入排序遍历的元素的起始位置
     * @param c 比较器
     */
    @SuppressWarnings("fallthrough")
    private static <T> void binarySort(T[] a, int lo, int hi, int start,
                                       Comparator<? super T> c) {
        // 二分法排序，将start位置的元素插入到前面已经排好序的数组中
        assert lo <= start && start <= hi;
        if (start == lo)
            start++;
        for (; start < hi; start++) {
            T pivot = a[start];
            int left = lo; // [ 排序好的数组包括left
            int right = start; // ) 排序好的数组不包括right
            assert left <= right;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c.compare(pivot, a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;
            int n = start - left;  // The number of elements to move
            switch (n) {
                case 2:
                    a[left + 2] = a[left + 1];
                case 1:
                    a[left + 1] = a[left];
                    break;
                case 0:
                    continue;
                default:
                    System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
    }

    /**
     * Returns the length of the run beginning at the specified position in
     * the specified array and reverses the run if it is descending (ensuring
     * that the run will always be ascending when the method returns).
     * <p>
     * A run is the longest ascending sequence with:
     * <p>
     * a[lo] <= a[lo + 1] <= a[lo + 2] <= ...
     * <p>
     * or the longest descending sequence with:
     * <p>
     * a[lo] >  a[lo + 1] >  a[lo + 2] >  ...
     * <p>
     * For its intended use in a stable mergesort, the strictness of the
     * definition of "descending" is needed so that the call can safely
     * reverse a descending sequence without violating stability.
     *
     * @param a  the array in which a run is to be counted and possibly reversed
     * @param lo index of the first element in the run
     * @param hi index after the last element that may be contained in the run.
     *           It is required that {@code lo < hi}.
     * @param c  the comparator to used for the sort
     * @return the length of the run beginning at the specified position in
     * the specified array
     * 找到一个递增或者递减的序列，返回这个序列的长度
     */
    private static <T> int countRunAndMakeAscending(T[] a, int lo, int hi,
                                                    Comparator<? super T> c) {
        assert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi)
            return 1;
        // Find end of run, and reverse range if descending
        if (c.compare(a[runHi++], a[lo]) < 0) { // Descending
            while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) < 0)
                runHi++;
            // 翻转前面有序的且是严格递减的序列
            reverseRange(a, lo, runHi);
        } else {                              // Ascending
            while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) >= 0)
                runHi++;
        }
        return runHi - lo;
    }

    /**
     * Reverse the specified range of the specified array.
     *
     * @param a  the array in which a range is to be reversed
     * @param lo the index of the first element in the range to be reversed
     * @param hi the index after the last element in the range to be reversed
     */
    private static void reverseRange(Object[] a, int lo, int hi) {
        hi--;
        while (lo < hi) {
            Object t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }

    /**
     * Returns the minimum acceptable run length for an array of the specified
     * length. Natural runs shorter than this will be extended with
     * {@link #binarySort}.
     * <p>
     * Roughly speaking, the computation is:
     * <p>
     * If n < MIN_MERGE, return n (it's too small to bother with fancy stuff).
     * Else if n is an exact power of 2, return MIN_MERGE/2.
     * Else return an int k, MIN_MERGE/2 <= k <= MIN_MERGE, such that n/k
     * is close to, but strictly less than, an exact power of 2.
     * <p>
     * For the rationale, see listsort.txt.
     *
     * @param n the length of the array to be sorted
     * @return the length of the minimum run to be merged
     */
    private static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        // 不停的进行除2操作，直到n小于MIN_MERGE 为止
        // 如果在这个过程中出现了余数，最后得出的最小运行长度会加1
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    /**
     * Pushes the specified run onto the pending-run stack.
     *
     * @param runBase index of the first element in the run
     * @param runLen  the number of elements in the run
     */
    private void pushRun(int runBase, int runLen) {
        this.runBase[stackSize] = runBase;
        this.runLen[stackSize] = runLen;
        stackSize++;
    }

    /**
     * Examines the stack of runs waiting to be merged and merges adjacent runs
     * until the stack invariants are reestablished:
     * <p>
     * 1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
     * 2. runLen[i - 2] > runLen[i - 1]
     * <p>
     * This method is called each time a new run is pushed onto the stack,
     * so the invariants are guaranteed to hold for i < stackSize upon
     * entry to the method.
     * <p>
     * Thanks to Stijn de Gouw, Jurriaan Rot, Frank S. de Boer,
     * Richard Bubel and Reiner Hahnle, this is fixed with respect to
     * the analysis in "On the Worst-Case Complexity of TimSort" by
     * Nicolas Auger, Vincent Jug, Cyril Nicaud, and Carine Pivoteau.
     */
    private void mergeCollapse() {
        // 当出现两个或者两个以上的有序序列时，进行合并
        while (stackSize > 1) {
            // 栈中倒数第二个序列的索引
            int n = stackSize - 2;
            if (n > 0 && runLen[n - 1] <= runLen[n] + runLen[n + 1] ||
                    n > 1 && runLen[n - 2] <= runLen[n] + runLen[n - 1]) {
                // 如果倒数第一个序列 + 倒数第二个序列的长度大于倒数第三个序列的长度
                // or
                // 或者倒数第二个序列 + 倒数第三个序列的长度大于倒数第四个序列的长度
                if (runLen[n - 1] < runLen[n + 1])
                    n--; // 如果倒数第一个序列的长度小于倒数第3个序列的长度，那么将n减1，就是将倒数第二个序列需要和前面后面较小的合并
            } else if (runLen[n] > runLen[n + 1]) {
                // 如果倒数第一个序列的长度小于倒数第二个序列的长度
                // 或者 只有一个子序列
                // 那么直接跳出循环
                break; // Invariant is established
            }
            mergeAt(n);
        }
    }

    /**
     * Merges all runs on the stack until only one remains.  This method is
     * called once, to complete the sort.
     */
    private void mergeForceCollapse() {
        while (stackSize > 1) {
            int n = stackSize - 2;
            if (n > 0 && runLen[n - 1] < runLen[n + 1])
                n--;
            mergeAt(n);
        }
    }

    /**
     * Merges the two runs at stack indices i and i+1.  Run i must be
     * the penultimate or antepenultimate run on the stack.  In other words,
     * i must be equal to stackSize-2 or stackSize-3.
     *
     * @param i stack index of the first of the two runs to merge
     */
    private void mergeAt(int i) {
        assert stackSize >= 2;
        assert i >= 0;
        assert i == stackSize - 2 || i == stackSize - 3;

        int base1 = runBase[i];
        int len1 = runLen[i];
        int base2 = runBase[i + 1];
        int len2 = runLen[i + 1];
        assert len1 > 0 && len2 > 0;
        assert base1 + len1 == base2;

        /*
         * Record the length of the combined runs; if i is the 3rd-last
         * run now, also slide over the last run (which isn't involved
         * in this merge).  The current run (i+1) goes away in any case.
         */
        runLen[i] = len1 + len2;
        if (i == stackSize - 3) {
            runBase[i + 1] = runBase[i + 2];
            runLen[i + 1] = runLen[i + 2];
        }
        stackSize--;

        /*
         * Find where the first element of run2 goes in run1. Prior elements
         * in run1 can be ignored (because they're already in place).
         */
        // 找到run2中的第一个元素在run1中的位置 ,在这个位置前面的元素都是不需要再动的。
        int k = gallopRight(a[base2], a, base1, len1, 0, c);
        assert k >= 0;
        base1 += k;
        len1 -= k;
        if (len1 == 0)
            return;

        /*
         * Find where the last element of run1 goes in run2. Subsequent elements
         * in run2 can be ignored (because they're already in place).
         */
        // 找到run1中的最后一个元素在run2中的位置 ,在这个位置后面的元素都是不需要再动的。
        len2 = gallopLeft(a[base1 + len1 - 1], a, base2, len2, len2 - 1, c);
        assert len2 >= 0;
        if (len2 == 0)
            return;

        // Merge remaining runs, using tmp array with min(len1, len2) elements
        // 进行合并

        if (len1 <= len2)
            // 如果run1的长度小于等于run2的长度，那么使用mergeLo方法进行合并
            // 应该是把run1 需要合并的部分复制到tmp数组中，然后再进行合并
            // 然后临时数组和run2进行 【run1和run2】位置上合并
            mergeLo(base1, len1, base2, len2);
        else
            mergeHi(base1, len1, base2, len2);
    }

    /**
     * Locates the position at which to insert the specified key into the
     * specified sorted range; if the range contains an element equal to key,
     * returns the index of the leftmost equal element.
     *
     * @param key  the key whose insertion point to search for
     * @param a    the array in which to search
     * @param base the index of the first element in the range
     * @param len  the length of the range; must be > 0
     * @param hint the index at which to begin the search, 0 <= hint < n.
     *             The closer hint is to the result, the faster this method will run.
     * @param c    the comparator used to order the range, and to search
     * @return the int k,  0 <= k <= n such that a[b + k - 1] < key <= a[b + k],
     * pretending that a[b - 1] is minus infinity and a[b + n] is infinity.
     * In other words, key belongs at index b + k; or in other words,
     * the first k elements of a should precede key, and the last n - k
     * should follow it.
     */
    private static <T> int gallopLeft(T key, T[] a, int base, int len, int hint,
                                      Comparator<? super T> c) {
        assert len > 0 && hint >= 0 && hint < len;
        int lastOfs = 0;
        int ofs = 1;
        if (c.compare(key, a[base + hint]) > 0) {
            // Gallop right until a[base+hint+lastOfs] < key <= a[base+hint+ofs]
            int maxOfs = len - hint;
            while (ofs < maxOfs && c.compare(key, a[base + hint + ofs]) > 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            lastOfs += hint;
            ofs += hint;
        } else { // key <= a[base + hint]
            // Gallop left until a[base+hint-ofs] < key <= a[base+hint-lastOfs]
            final int maxOfs = hint + 1;
            while (ofs < maxOfs && c.compare(key, a[base + hint - ofs]) <= 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to base
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        }
        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * Now a[base+lastOfs] < key <= a[base+ofs], so key belongs somewhere
         * to the right of lastOfs but no farther right than ofs.  Do a binary
         * search, with invariant a[base + lastOfs - 1] < key <= a[base + ofs].
         */
        lastOfs++;
        while (lastOfs < ofs) {
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (c.compare(key, a[base + m]) > 0)
                lastOfs = m + 1;  // a[base + m] < key
            else
                ofs = m;          // key <= a[base + m]
        }
        assert lastOfs == ofs;    // so a[base + ofs - 1] < key <= a[base + ofs]
        return ofs;
    }

    /**
     * Like gallopLeft, except that if the range contains an element equal to
     * key, gallopRight returns the index after the rightmost equal element.
     *
     * @param key  the key whose insertion point to search for
     * @param a    the array in which to search
     * @param base the index of the first element in the range
     * @param len  the length of the range; must be > 0
     * @param hint the index at which to begin the search, 0 <= hint < n.
     *             The closer hint is to the result, the faster this method will run.
     * @param c    the comparator used to order the range, and to search
     * @return the int k,  0 <= k <= n such that a[b + k - 1] <= key < a[b + k]
     */
    private static <T> int gallopRight(T key, T[] a, int base, int len,
                                       int hint, Comparator<? super T> c) {
        assert len > 0 && hint >= 0 && hint < len;

        int ofs = 1;
        int lastOfs = 0;
        if (c.compare(key, a[base + hint]) < 0) {
            // Gallop left until a[b+hint - ofs] <= key < a[b+hint - lastOfs]
            // 如果key小于a[base + hint] 那么就向左查找
            int maxOfs = hint + 1;
            while (ofs < maxOfs && c.compare(key, a[base + hint - ofs]) < 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;
            // ofs  ---- lastOfs
            // Make offsets relative to b
            int tmp = lastOfs;
            lastOfs = hint - ofs;
            ofs = hint - tmp;
        } else { // a[b + hint] <= key
            // Gallop right until a[b+hint + lastOfs] <= key < a[b+hint + ofs]
            // 如果key大于等于a[base + hint] 那么就向右查找
            // 最大偏移量
            int maxOfs = len - hint;
            // FIXME xia 2024-12-16 16:49 : 猜测hint是已经被证实小于key的索引
            // 如果偏移量小于maxOfs并且key大于等于a[base + hint + ofs]
            while (ofs < maxOfs && c.compare(key, a[base + hint + ofs]) >= 0) {
                lastOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int overflow int 溢出
                    ofs = maxOfs;
            }
            if (ofs > maxOfs)
                ofs = maxOfs;

            // Make offsets relative to b
            lastOfs += hint;
            ofs += hint;
            // key 在 a[base + lastOfs] 和 a[base + ofs] 之间
        }

        assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

        /*
         * Now a[b + lastOfs] <= key < a[b + ofs], so key belongs somewhere to
         * the right of lastOfs but no farther right than ofs.  Do a binary
         * search, with invariant a[b + lastOfs - 1] <= key < a[b + ofs].
         */
        lastOfs++;
        while (lastOfs < ofs) {
            // 二分查找
            int m = lastOfs + ((ofs - lastOfs) >>> 1);

            if (c.compare(key, a[base + m]) < 0)
                ofs = m;          // key < a[b + m]
            else
                lastOfs = m + 1;  // a[b + m] <= key
        }
        assert lastOfs == ofs;    // so a[b + ofs - 1] <= key < a[b + ofs]
        return ofs;
    }

    /**
     * Merges two adjacent runs in place, in a stable fashion.  The first
     * element of the first run must be greater than the first element of the
     * second run (a[base1] > a[base2]), and the last element of the first run
     * (a[base1 + len1-1]) must be greater than all elements of the second run.
     * <p>
     * For performance, this method should be called only when len1 <= len2;
     * its twin, mergeHi should be called if len1 >= len2.  (Either method
     * may be called if len1 == len2.)
     *
     * @param base1 index of first element in first run to be merged
     * @param len1  length of first run to be merged (must be > 0)
     * @param base2 index of first element in second run to be merged
     *              (must be aBase + aLen)
     * @param len2  length of second run to be merged (must be > 0)
     */
    private void mergeLo(int base1, int len1, int base2, int len2) {
        assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

        // Copy first run into temp array
        T[] a = this.a; // For performance
        // 获取一个长度为len1的数组
        T[] tmp = ensureCapacity(len1);
        // 临时数组的游标
        int cursor1 = tmpBase; // Indexes into tmp array
        // run2数组的游标
        int cursor2 = base2;   // Indexes int a
        // 合并部分的起始位置
        int dest = base1;      // Indexes int a
        // 将run1的元素复制到临时数组中
        System.arraycopy(a, base1, tmp, cursor1, len1);

        // Move first element of second run and deal with degenerate cases
        // 之前已经保证了run1的第一个元素大于run2的第一个元素
        // 将run2的第一个元素复制到合并部分的第一个位置
        a[dest++] = a[cursor2++];
        if (--len2 == 0) {
            // 如果run2的长度为0，那么直接将run1的元素复制到合并部分
            System.arraycopy(tmp, cursor1, a, dest, len1);
            return;
        }
        if (len1 == 1) {
            // 如果run1的长度为1，那么直接将run2的元素复制到合并部分
            System.arraycopy(a, cursor2, a, dest, len2);
            // 将run1的元素复制到合并部分
            a[dest + len2] = tmp[cursor1]; // Last elt of run 1 to end of merge
            return;
        }

        Comparator<? super T> c = this.c;  // Use local variable for performance
        // 加速合并的参数
        int minGallop = this.minGallop;    //  "    "       "     "      "
        outer:
        while (true) {
            int count1 = 0; // Number of times in a row that first run won
            int count2 = 0; // Number of times in a row that second run won

            /*
             * Do the straightforward thing until (if ever) one run starts
             * winning consistently.
             */
            do {
                assert len1 > 1 && len2 > 0;
                if (c.compare(a[cursor2], tmp[cursor1]) < 0) {
                    a[dest++] = a[cursor2++];
                    count2++;
                    count1 = 0;
                    if (--len2 == 0)
                        break outer;
                } else {
                    a[dest++] = tmp[cursor1++];
                    count1++;
                    count2 = 0;
                    if (--len1 == 1)
                        break outer;
                }
                // 加入出现了七次以上的其中一个序列胜出，那么就直接跳出循环
            } while ((count1 | count2) < minGallop);

            // 进行加速和合并
            /*
             * One run is winning so consistently that galloping may be a
             * huge win. So try that, and continue galloping until (if ever)
             * neither run appears to be winning consistently anymore.
             */
            do {
                assert len1 > 1 && len2 > 0;
                count1 = gallopRight(a[cursor2], tmp, cursor1, len1, 0, c);
                if (count1 != 0) {
                    System.arraycopy(tmp, cursor1, a, dest, count1);
                    dest += count1;
                    cursor1 += count1;
                    len1 -= count1;
                    if (len1 <= 1) // len1 == 1 || len1 == 0
                        break outer;
                }
                // 将run2的下一个元素复制到合并部分
                a[dest++] = a[cursor2++];
                if (--len2 == 0)
                    break outer;

                count2 = gallopLeft(tmp[cursor1], a, cursor2, len2, 0, c);
                if (count2 != 0) {
                    System.arraycopy(a, cursor2, a, dest, count2);
                    dest += count2;
                    cursor2 += count2;
                    len2 -= count2;
                    if (len2 == 0)
                        break outer;
                }
                a[dest++] = tmp[cursor1++];
                if (--len1 == 1)
                    break outer;
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGallop < 0)
                minGallop = 0;
            // 离开了加速模式，minGallop加2 ;因离开疾驰模式而受到惩罚
            minGallop += 2;  // Penalize for leaving gallop mode
        }  // End of "outer" loop
        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        if (len1 == 1) {
            assert len2 > 0;
            System.arraycopy(a, cursor2, a, dest, len2);
            a[dest + len2] = tmp[cursor1]; //  Last elt of run 1 to end of merge
        } else if (len1 == 0) {
            throw new IllegalArgumentException(
                    "Comparison method violates its general contract!");
        } else {
            assert len2 == 0;
            assert len1 > 1;
            System.arraycopy(tmp, cursor1, a, dest, len1);
        }
    }

    /**
     * Like mergeLo, except that this method should be called only if
     * len1 >= len2; mergeLo should be called if len1 <= len2.  (Either method
     * may be called if len1 == len2.)
     *
     * @param base1 index of first element in first run to be merged
     * @param len1  length of first run to be merged (must be > 0)
     * @param base2 index of first element in second run to be merged
     *              (must be aBase + aLen)
     * @param len2  length of second run to be merged (must be > 0)
     */
    private void mergeHi(int base1, int len1, int base2, int len2) {
        assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

        // Copy second run into temp array
        T[] a = this.a; // For performance
        T[] tmp = ensureCapacity(len2);
        int tmpBase = this.tmpBase;
        System.arraycopy(a, base2, tmp, tmpBase, len2);

        int cursor1 = base1 + len1 - 1;  // Indexes into a
        int cursor2 = tmpBase + len2 - 1; // Indexes into tmp array
        int dest = base2 + len2 - 1;     // Indexes into a

        // Move last element of first run and deal with degenerate cases
        a[dest--] = a[cursor1--];
        if (--len1 == 0) {
            System.arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
            return;
        }
        if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];
            return;
        }

        Comparator<? super T> c = this.c;  // Use local variable for performance
        int minGallop = this.minGallop;    //  "    "       "     "      "
        outer:
        while (true) {
            int count1 = 0; // Number of times in a row that first run won
            int count2 = 0; // Number of times in a row that second run won

            /*
             * Do the straightforward thing until (if ever) one run
             * appears to win consistently.
             */
            do {
                assert len1 > 0 && len2 > 1;
                if (c.compare(tmp[cursor2], a[cursor1]) < 0) {
                    a[dest--] = a[cursor1--];
                    count1++;
                    count2 = 0;
                    if (--len1 == 0)
                        break outer;
                } else {
                    a[dest--] = tmp[cursor2--];
                    count2++;
                    count1 = 0;
                    if (--len2 == 1)
                        break outer;
                }
            } while ((count1 | count2) < minGallop);

            /*
             * One run is winning so consistently that galloping may be a
             * huge win. So try that, and continue galloping until (if ever)
             * neither run appears to be winning consistently anymore.
             */
            do {
                assert len1 > 0 && len2 > 1;
                count1 = len1 - gallopRight(tmp[cursor2], a, base1, len1, len1 - 1, c);
                if (count1 != 0) {
                    dest -= count1;
                    cursor1 -= count1;
                    len1 -= count1;
                    System.arraycopy(a, cursor1 + 1, a, dest + 1, count1);
                    if (len1 == 0)
                        break outer;
                }
                a[dest--] = tmp[cursor2--];
                if (--len2 == 1)
                    break outer;

                count2 = len2 - gallopLeft(a[cursor1], tmp, tmpBase, len2, len2 - 1, c);
                if (count2 != 0) {
                    dest -= count2;
                    cursor2 -= count2;
                    len2 -= count2;
                    System.arraycopy(tmp, cursor2 + 1, a, dest + 1, count2);
                    if (len2 <= 1)  // len2 == 1 || len2 == 0
                        break outer;
                }
                a[dest--] = a[cursor1--];
                if (--len1 == 0)
                    break outer;
                minGallop--;
            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
            if (minGallop < 0)
                minGallop = 0;
            minGallop += 2;  // Penalize for leaving gallop mode
        }  // End of "outer" loop
        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field

        if (len2 == 1) {
            assert len1 > 0;
            dest -= len1;
            cursor1 -= len1;
            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
            a[dest] = tmp[cursor2];  // Move first elt of run2 to front of merge
        } else if (len2 == 0) {
            throw new IllegalArgumentException(
                    "Comparison method violates its general contract!");
        } else {
            assert len1 == 0;
            assert len2 > 0;
            System.arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
        }
    }

    /**
     * Ensures that the external array tmp has at least the specified
     * number of elements, increasing its size if necessary.  The size
     * increases exponentially to ensure amortized linear time complexity.
     *
     * @param minCapacity the minimum required capacity of the tmp array
     * @return tmp, whether or not it grew
     */
    private T[] ensureCapacity(int minCapacity) {
        if (tmpLen < minCapacity) {
            // Compute smallest power of 2 > minCapacity
            int newSize = -1 >>> Integer.numberOfLeadingZeros(minCapacity);
            newSize++;

            if (newSize < 0) // Not bloody likely!
                newSize = minCapacity;
            else
                newSize = Math.min(newSize, a.length >>> 1);

            @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance
                    (a.getClass().getComponentType(), newSize);
            tmp = newArray;
            tmpLen = newSize;
            tmpBase = 0;
        }
        return tmp;
    }
}
