package com.LoveSea.fengCore.shadowsocks;

import com.LoveSea.fengCore.shadowsocks.impl.ParseSSTapImpl;

/**
 * @author xiahaifeng
 */

public interface ParseSSTap {
    SSRUrlParseRes parseSSrSubLink(String ssrLink) throws Exception;

    SSRUrlItem parseSSr(String ssrUrl);

    String parserSSRUrlItem(SSRUrlItem ssrUrlItem);

    static ParseSSTap of() {
        return new ParseSSTapImpl();
    }
}