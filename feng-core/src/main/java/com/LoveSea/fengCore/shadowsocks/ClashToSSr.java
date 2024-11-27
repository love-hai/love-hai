package com.LoveSea.fengCore.shadowsocks;

import com.LoveSea.fengCore.shadowsocks.impl.ClashToSSrImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiahaifeng
 */

public interface ClashToSSr {
    String transform(File file) throws IOException;

    String transform(String configUrl) throws IOException;

    String transform(InputStream in);

    static ClashToSSr of() {
        return new ClashToSSrImpl();
    }
}