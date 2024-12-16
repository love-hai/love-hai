package com.LoveSea.fengCore.study.algorithms.paxos;

import com.LoveSea.fengCore.study.algorithms.Snowflake;

import java.util.Random;

/**
 * @author xiahaifeng
 */

public class ValueOfW {

    public ValueOfW(ValueOfW valueOfW, String value, Long proposal_id) {
        this.value = value;
    }
    private volatile ValueOfW valueOfW;

    private volatile Long proposal_id = null;

    private volatile String value;

    public ValueOfW() {
    }

    public Long getProposalId() {
        return proposal_id;
    }

    public void setValue(ValueOfW value) {
        this.valueOfW = value;
    }

    public ValueOfW getValue() {
        return valueOfW;
    }

    public String toString() {
        return value + " " + (null != valueOfW ? valueOfW.toString() : "");
    }
}