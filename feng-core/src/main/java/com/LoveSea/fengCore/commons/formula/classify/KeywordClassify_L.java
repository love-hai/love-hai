package com.LoveSea.fengCore.commons.formula.classify;


import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.eval.Eval;
import com.LoveSea.fengCore.commons.formula.eval.LetEval;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

/**
 * @author xiahaifeng
 */

public class KeywordClassify_L {
    public static Eval select(String formulas, CalFormulaContext context) {
        if (formulas.length() >= 3 && "LET".equalsIgnoreCase(formulas.substring(0, 3))) {
            char next = FunUtils.findNextNoBlankChar(formulas, 3);
            if ('(' == next) {
                return new LetEval(formulas.substring(3), context);
            }
        }
        return CommonClassify.select(formulas, context);
    }
}