package com.LoveSea.fengCore.commons.formula.eval;

import com.LoveSea.fengCore.commons.formula.FormulaException;
import com.LoveSea.fengCore.commons.formula.classify.KeywordClassify;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

import java.util.List;

/**
 * @author xiahaifeng
 */

public class IfFun implements Eval {
    private CalFormulaContext context;
    private String formula;

    public IfFun(String formula, CalFormulaContext context) {
        this.formula = formula;
        this.context = context;
    }


    @Override
    public Object eval() {
        this.formula = FunUtils.removeOuterMatchedBrackets(this.formula);
        List<String> evals = FunUtils.findEvalsBySeparation(this.formula);
        if (evals.size() != 5 && !",".equals(evals.get(1)) && !",".equals(evals.get(3))) {
            throw new FormulaException("If" + this.formula + "公式错误", FormulaException.FormulaExceptionType.OTHER);
        }
        Eval condition = KeywordClassify.select(evals.get(0), context);
        Object conditionV = condition.eval();
        if (null == conditionV || conditionV.getClass() != Boolean.class) {
            throw new FormulaException("If" + this.formula + "公式错误", FormulaException.FormulaExceptionType.DATA_ERROR);
        }
        boolean conditionValue = (Boolean) conditionV;
        Object trueV = KeywordClassify.select(evals.get(2), context).eval();
        Object falseV = KeywordClassify.select(evals.get(4), context).eval();
        return conditionValue ? trueV : falseV;
    }
}