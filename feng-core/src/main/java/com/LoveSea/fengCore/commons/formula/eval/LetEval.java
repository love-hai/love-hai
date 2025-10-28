package com.LoveSea.fengCore.commons.formula.eval;

import com.LoveSea.fengCore.commons.formula.FormulaException;
import com.LoveSea.fengCore.commons.formula.classify.KeywordClassify;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class LetEval implements Eval {
    private String formula;
    private CalFormulaContext context;

    public LetEval(String formula, CalFormulaContext context) {
        this.formula = formula;
        this.context = context;
    }

    @Override
    public Object eval() {
        this.formula = FunUtils.removeOuterMatchedBrackets(this.formula);
        List<String> evals = FunUtils.findEvalsBySeparation(this.formula);
        int size = evals.size();
        if ((size - 1) % 4 != 0) {
            throw new FormulaException("Let" + this.formula + "公式错误");
        }
        Map<String, Object> letKV = new HashMap<>();
        context.setLetKV(letKV);
        for (int i = 0; i < size - 1; i += 4) {
            String key = evals.get(i);
            Eval value = KeywordClassify.select(evals.get(i + 2), context);
            letKV.put(key, value.eval());
        }
        String calculate = evals.get(evals.size() - 1);
        Object v = KeywordClassify.select(calculate, context).eval();
        context.setLetKV(null);
        return v;
    }
}