package com.LoveSea.fengCore.study.algorithms.paxos;

import com.LoveSea.fengCore.study.algorithms.Snowflake;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

/**
 * @author xiahaifeng
 */

public class Worker {
    private ValueOfW valueOfW;

    public synchronized boolean isAcceptProposal(Proposal proposal) {
        if (proposal.getProposalId() == null) {
            return false;
        }
        return null == valueOfW.getProposalId() || proposal.getProposalId() > valueOfW.getProposalId();
    }

    public synchronized void acceptProposal(Proposal proposal) {
        this.valueOfW = new ValueOfW(proposal.getValueOfW(), proposal.getValue(), proposal.getProposalId());
    }


    public static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    Random random = new Random();
    public Snowflake snowflake;

    public Proposal prepareProposal() {
        int len = random.nextInt(100);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return new Proposal(this.valueOfW, sb.toString(), snowflake.generateId());
    }
}