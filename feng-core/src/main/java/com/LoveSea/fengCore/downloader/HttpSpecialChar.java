package com.LoveSea.fengCore.downloader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * @author xiahaifeng
 */

public class HttpSpecialChar {

    private final Charset ENCODE_FORMAT_UTF8 = StandardCharsets.UTF_8;
    private final Pattern needEncodePattern = Pattern.compile("^[0-9a-zA-Z.:/?=&%~`#()-+]+$");

    /**
     * 获取按要求编码后的URL列表
     *
     * @param url url
     * @return 编码后的url
     */
    public String encodeURLChinese(String url) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        url = url.trim();
        try {
            if (!needEncoding(url)) {
                // 不需要编码
                return url;
            } else {
                // 需要编码
                String allowChars = ".!*'();:@&=+_\\-$,/?#\\[\\]{}|\\^~`<>%\"";
                return encode(url, ENCODE_FORMAT_UTF8, allowChars, false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decodeURLChinese(String url) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        url = url.trim();
        try {
            return URLDecoder.decode(url, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断一个url是否需要编码，按需要增减过滤的字符
     *
     * @param url url
     * @return 需要编码的url
     */
    public boolean needEncoding(String url) {
        // 不需要编码的正则表达式
        return !needEncodePattern.matcher(url).matches();
    }

    /**
     * 对字符串中的特定字符进行编码
     *
     * @param s         待编码的字符串
     * @param enc       编码类型
     * @param allowed   不需要编码的字符
     * @param lowerCase true:小写 false：大写
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public final String encode(String s, Charset enc, String allowed,
                               boolean lowerCase) throws UnsupportedEncodingException {

        byte[] bytes = s.getBytes(enc);
        int count = bytes.length;
        /*
         * From RFC 2396:
         *
         * mark = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")" reserved =
         * ";" | "/" | ":" | "?" | "@" | "&" | "=" | "+" | "$" | ","
         */
        // final String allowed = "=,+;.'-@&/$_()!~*:"; // '?' is omitted
        char[] buf = new char[3 * count];
        int j = 0;
        for (byte aByte : bytes) {
            if ((aByte >= 0x61 && aByte <= 0x7A) || // a..z
                    (aByte >= 0x41 && aByte <= 0x5A) || // A..Z
                    (aByte >= 0x30 && aByte <= 0x39) || // 0..9
                    (allowed.indexOf(aByte) >= 0)) {
                buf[j++] = (char) aByte;
            } else {
                buf[j++] = '%';
                if (lowerCase) {
                    buf[j++] = Character.forDigit(0xF & (aByte >>> 4), 16);
                    buf[j++] = Character.forDigit(0xF & aByte, 16);
                } else {
                    buf[j++] = lowerCaseToUpperCase(Character.forDigit(
                            0xF & (aByte >>> 4), 16));
                    buf[j++] = lowerCaseToUpperCase(Character.forDigit(
                            0xF & aByte, 16));
                }

            }
        }
        return new String(buf, 0, j);
    }

    public char lowerCaseToUpperCase(char ch) {
        if (ch >= 97 && ch <= 122) { // 如果是小写字母就转化成大写字母
            ch = (char) (ch - 32);
        }
        return ch;
    }
}