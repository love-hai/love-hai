package com.LoveSea.fengCore.retryable;

import org.jetbrains.annotations.NotNull;

/**
 * @author xiahaifeng
 */

public record RetryLevelImpl(int level) implements RetryLevel {
    @Override
    public int compareTo(@NotNull RetryLevel o) {
        return Integer.compare(this.level, o.level());
    }
}