package com.LoveSea.fengCore.study.leetCode;

/**
 * 12. 整数转罗马数字
 * <table border="1">
 * <tr><th>符号<th>数字
 * <tr><td>I<td>1
 * <tr><td>V<td>5
 * <tr><td>X<td>10
 * <tr><td>L<td>50
 * <tr><td>C<td>100
 * <tr><td>D<td>500
 * <tr><td>M<td>1000
 * </table>
 * <p>
 * 罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要将符号附加4次，请使用 减法形式。
 * 给定一个整数，将其转换为罗马数字。
 *
 * @author xiahaifeng
 */

public class Solution12 {
    public String intToRoman(int num) {
        int div = 1000;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int n = num / div;
            if (n > 0) {
                if (n == 4) {
                    sb.append(getRoman(div));
                    sb.append(getRoman(div * 5));
                } else if (n == 9) {
                    sb.append(getRoman(div));
                    sb.append(getRoman(div * 10));
                } else if (n >= 5) {
                    sb.append(getRoman(div * 5));
                    sb.append(getRoman(div).repeat(n - 5));
                } else {
                    sb.append(getRoman(div).repeat(n));
                }
            }
            num %= div;
            div /= 10;
        }
        return sb.toString();
    }

    public String getRoman(int num) {
        return switch (num) {
            case 1 -> "I";
            case 5 -> "V";
            case 10 -> "X";
            case 50 -> "L";
            case 100 -> "C";
            case 500 -> "D";
            case 1000 -> "M";
            default -> {
                throw new IllegalArgumentException("num is not valid");
            }
        };
    }
}