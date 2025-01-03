package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */

public interface RetryLevel extends Comparable<RetryLevel> {
    RetryLevel LEVEL_EMPTY = RetryLevel.of(-1);
    RetryLevel L_0 = RetryLevel.of(0);
    RetryLevel L_1 = RetryLevel.of(1);
    RetryLevel L_2 = RetryLevel.of(2);
    RetryLevel L_3 = RetryLevel.of(3);
    RetryLevel L_4 = RetryLevel.of(4);
    RetryLevel L_5 = RetryLevel.of(5);

    int level();

    static RetryLevel of(int level) {
        return new RetryLevelImpl(level);
    }
}