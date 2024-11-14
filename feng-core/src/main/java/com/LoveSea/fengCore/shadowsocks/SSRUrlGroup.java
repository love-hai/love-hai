package com.LoveSea.fengCore.shadowsocks;

import lombok.Data;

import java.util.List;

/**
 * @author xiahaifeng
 */
@Data
public class SSRUrlGroup {
    String name;
    List<SSRUrlItem> SSRUrlItems;
}