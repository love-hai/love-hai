package com.LoveSea.fengCore.commons.formula.eval;

/**
 * @author xiahaifeng
 */

public class ConstantEval implements Eval {
    private Object value;

    public ConstantEval(Object value) {
        this.value = value;
    }


    @Override
    public Object eval() {
        return value;
    }
}