package com.LoveSea.fengCore.study.gfg;

/**
 * @author xiahaifeng
 */

public class Solution176 {

    /**
     * hasGeekSubsequence : 判断小写字符串中是否包含geek子序列
     */
    public static boolean hasGeekSubsequence(String str) {
        String geek = "geek";
        int geekLength = geek.length();
        for (int i = 0, j = 0; i < str.length() && j < geekLength; i++) {
            if (str.charAt(i) == geek.charAt(j))
                j++;
            if (j == geekLength)
                return true;
        }
        return false;
    }

    /**
     * countPalindromicSubstrings :
     * 给定一个字符串 s 和一个整数 k，返回 s 中回文子串的数量，这些子串的长度为 k。
     * 回文：正着读和反着读是一样的
     */
    public static int countPalindromicSubstrings(String s, int k) {
        int[] charCount = new int[26];
        char[] str = s.toCharArray();
        int result = 0;
        int j = k - 1;
        int i = 0;
        for (int l = 0; l < k; l++)
            charCount[str[l] - 'a']++;
        while (true) {
            result += palindromic(charCount, k);
            i++;
            j++;
            if (j >= s.length())
                break;
            charCount[str[i - 1] - 'a']--;
            charCount[str[j] - 'a']++;
        }
        return result;
    }

    private static int palindromic(int[] charCount, int k) {
        int odd = 0;
        for (int c : charCount) {
            if (c % 2 != 0)
                odd++;
        }
        if (k % 2 == 0)
            return (odd == 0 ? 1 : 0);
        return (odd == 1 ? 1 : 0);
    }

    // 给定一个数组 arr，返回包含奇数个奇数元素的偶数长度子数组的数量。
    public static long countoddSubarrays2(int n, int[] arr) {
        int childLength = 2;
        boolean[] childAddSub;
        boolean[] twoChildAddSub;
        long countoddSubarraysResult = 0;
        childAddSub = new boolean[n - 1];
        twoChildAddSub = new boolean[n - 1];
        for (int i = 0; i < n - 1; i++) {
            childAddSub[i] = (arr[i] % 2 ^ arr[i + 1] % 2) == 1;
            twoChildAddSub[i] = childAddSub[i];
            countoddSubarraysResult += childAddSub[i] ? 1 : 0;
        }
        while (childLength < n) {
            childLength += 2;
            for (int i = 0; i <= n - childLength; i++) {
                childAddSub[i] = childAddSub[i] ^ twoChildAddSub[i + childLength - 2];
                countoddSubarraysResult += childAddSub[i] ? 1 : 0;
            }
        }
        return countoddSubarraysResult;
    }

    public static long countoddSubarrays(int n, int[] arr) {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int odd = 0;
            for (int j = i; j < n; j++) {
                if ((arr[j] & 1) == 1)
                    odd ^= 1;
                if ((odd & 1) == 1 && (j & 1) != (i & 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int planStock = (i) / 7;
            if (i % 7 != 0) {
                planStock += 1;
            }
            int planStock2 = (i + 6) / 7;
            if (planStock!=planStock2){
                System.out.println(i);
            }
        }


    }


}