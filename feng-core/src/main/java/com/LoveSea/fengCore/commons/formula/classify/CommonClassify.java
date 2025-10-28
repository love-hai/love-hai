package com.LoveSea.fengCore.commons.formula.classify;


import com.LoveSea.fengCore.commons.formula.FormulaException;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.eval.ConstantEval;
import com.LoveSea.fengCore.commons.formula.eval.Eval;
import com.LoveSea.fengCore.commons.formula.eval.SameSheetCellEval;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

/**
 * @author xiahaifeng
 */

public class CommonClassify {
    public static Eval select(String formula, CalFormulaContext context) {
        if (FunUtils.isCell(formula)) {
            return new SameSheetCellEval(formula, context);
        }
        Object v = context.getLetKV(formula);
        if (v != null) {
            return new ConstantEval(v);
        }
        throw new FormulaException("非法函数:" + formula, FormulaException.FormulaExceptionType.ILLEGAL_FUN);
    }
}