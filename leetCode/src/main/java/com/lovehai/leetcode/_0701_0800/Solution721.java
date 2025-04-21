package com.lovehai.leetcode._0701_0800;

import java.util.List;

/**
 * 账户合并
 * @author xiahaifeng
 */

public class Solution721 {
    /*
    给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
    其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
    现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
    请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
    一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。合并账户后，按以下格式
    返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。
    账户本身可以以任意顺序返回。
     */

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 1. 邮箱地址是唯一的，所以可以用邮箱地址作为key，来判断是否是同一个人
        return null;
    }
}