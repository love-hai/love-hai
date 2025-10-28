package com.lovehai.leetcode._2001_2100;

/**
 *
 * @author xiahaifeng
 */

class Bank {
    private final long[] balances;

    public Bank(long[] balance) {
        this.balances = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!this.checkAccountExisting(account1) || !this.checkAccountExisting(account2)) {
            return false;
        }
        synchronized (String.valueOf(account2)) {
            if (balances[account1 - 1] >= money) {
                balances[account1 - 1] -= money;
                balances[account2 - 1] += money;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean deposit(int account, long money) {
        if (!this.checkAccountExisting(account)) {
            return false;
        }
        synchronized (String.valueOf(account)) {
            balances[account - 1] += money;
            return true;
        }
    }

    public boolean withdraw(int account, long money) {
        if (!this.checkAccountExisting(account)) {
            return false;
        }
        synchronized (String.valueOf(account)) {
            if (balances[account - 1] >= money) {
                balances[account - 1] -= money;
                return true;
            }
        }
        return false;
    }

    private boolean checkAccountExisting(int account) {
        return balances.length >= account;
    }
}
