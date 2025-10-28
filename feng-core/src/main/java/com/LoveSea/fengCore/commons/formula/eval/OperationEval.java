package com.LoveSea.fengCore.commons.formula.eval;

import com.LoveSea.fengCore.commons.formula.OperatorsSymbols;

/**
 * @author xiahaifeng
 */

public class OperationEval implements Eval {
    private Object first;
    private Object second;
    private String symbol;

    public OperationEval(Object first, Object second, String symbol) {
        this.first = first;
        this.second = second;
        this.symbol = symbol;
    }

    @Override
    public Object eval() {
        this.symbol = symbol.trim();
        switch (symbol) {
            case "+":
                return OperatorsSymbols.add(first, second);
            case "-":
                return OperatorsSymbols.sub(first, second);
            case "*":
                return OperatorsSymbols.mul(first, second);
            case "/":
                return OperatorsSymbols.divide(first, second);
            default:
                return OperatorsSymbols.compare(first, second, symbol);
        }
    }
}