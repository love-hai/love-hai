package com.LoveSea.fengCore.commons.formula.eval;

import com.LoveSea.fengCore.commons.formula.FormulaException;
import com.LoveSea.fengCore.commons.formula.classify.KeywordClassify;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;
import com.LoveSea.fengCore.commons.utils.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author xiahaifeng
 */

public class RoundEval implements Eval {
    private final CalFormulaContext context;
    private String formula;

    public RoundEval(String formula, CalFormulaContext context) {
        this.formula = formula;
        this.context = context;
    }

    @Override
    public Object eval() {
        this.formula = FunUtils.removeOuterMatchedBrackets(formula);
        List<String> evals = FunUtils.findEvalsBySeparation(formula);
        if (evals.size() != 3 && !",".equals(evals.get(1))) {
            throw new RuntimeException("Round" + formula + "公式错误");
        }
        Eval eval = KeywordClassify.select(evals.get(0), context);
        Object old = eval.eval();
        if (null == old) {
            throw new FormulaException("Round" + formula + "公式错误", FormulaException.FormulaExceptionType.DATA_ERROR);
        }
        Number number = (Number) old;
        BigDecimal oldValue = BigDecimal.valueOf(number.doubleValue());
        String scale = evals.get(2);
        int scaleNum;
        if (StringUtils.isNumeric(scale)) {
            scaleNum = Integer.parseInt(scale);
        } else {
            BigDecimal scaleNumB = (BigDecimal) KeywordClassify.select(scale, context);
            scaleNum = scaleNumB.intValue();
        }
        return oldValue.setScale(scaleNum, RoundingMode.HALF_UP);
    }
}