package com.LoveSea.fengCore.commons.formula.classify;


import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.eval.Eval;
import com.LoveSea.fengCore.commons.formula.eval.RoundEval;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

/**
 * @author xiahaifeng
 */

public class KeywordClassify_R {
    public static Eval select(String formulas, CalFormulaContext context) {
        if (formulas.length() >= 5 && "ROUND".equalsIgnoreCase(formulas.substring(0, 5))) {
            char next = FunUtils.findNextNoBlankChar(formulas, 5);
            if ('(' == next) {
                return new RoundEval(formulas.substring(5), context);
            }
        }
        return CommonClassify.select(formulas, context);
    }
}