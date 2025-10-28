package com.LoveSea.fengCore.commons.formula.classify;


import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.eval.Eval;
import com.LoveSea.fengCore.commons.formula.eval.IfFun;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

/**
 * @author xiahaifeng
 */

public class KeywordClassify_I {
    public static Eval select(String formula, CalFormulaContext context) {
        if (formula.length() >= 2 && "IF".equalsIgnoreCase(formula.substring(0, 2))) {
            char next = FunUtils.findNextNoBlankChar(formula, 2);
            if ('(' == next) {
                return new IfFun(formula.substring(2), context);
            }
        }
        return CommonClassify.select(formula, context);
    }
}