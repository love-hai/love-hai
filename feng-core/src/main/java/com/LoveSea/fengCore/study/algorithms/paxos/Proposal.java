package com.LoveSea.fengCore.study.algorithms.paxos;

import lombok.Setter;

/**
 * @author xiahaifeng
 */

public class Proposal {

    public Proposal(ValueOfW valueOfW, String value, Long proposal_id) {
        this.value = value;
    }
    private volatile ValueOfW valueOfW;

    public ValueOfW getValueOfW() {
        return valueOfW;
    }


    private volatile Long proposal_id = null;

    public Long getProposalId() {
        return proposal_id;
    }

    private volatile String value;

    public String getValue() {
        return value;
    }

    public Proposal() {
    }
}