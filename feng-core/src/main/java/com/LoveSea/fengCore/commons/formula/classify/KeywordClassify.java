package com.LoveSea.fengCore.commons.formula.classify;

import com.LoveSea.fengCore.commons.formula.OperatorsSymbols;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.eval.ConstantEval;
import com.LoveSea.fengCore.commons.formula.eval.Eval;
import com.LoveSea.fengCore.commons.formula.eval.OperationEval;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;
import com.LoveSea.fengCore.commons.utils.StringUtils;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class KeywordClassify {
    public static Eval select(String formula, CalFormulaContext context) {
        formula = FunUtils.removeOuterMatchedBrackets(formula).trim();
        List<String> evals = FunUtils.findEvalsByOperation(formula);
        Map<Integer, Object> evalMap = null;

        while (evals.size() > 1) {
            if (null == evalMap) {
                evalMap = new HashMap<>();
            }
            int opGrade = -1;
            int evalIndex = -1;
            for (int i = 0; i < evals.size(); i++) {
                String evalItem = evals.get(i);
                int evalGrade = OperatorsSymbols.getGrade(evalItem);
                if (evalGrade > opGrade) {
                    opGrade = evalGrade;
                    evalIndex = i;
                }
            }
            if (opGrade == -1) {
                throw new RuntimeException("公式错误" + formula);
            }
            int firstIndex = evalIndex - 1;
            Object first;
            if (evalMap.containsKey(firstIndex)) {
                first = evalMap.get(firstIndex);
            } else {
                first = KeywordClassify.select(evals.get(firstIndex), context).eval();
            }
            int secondIndex = evalIndex + 1;
            Object second;
            if (evalMap.containsKey(secondIndex)) {
                second = evalMap.get(secondIndex);
            } else {
                second = KeywordClassify.select(evals.get(secondIndex), context).eval();
            }
            OperationEval operationEval = new OperationEval(first, second, evals.get(evalIndex));
            Object newV = operationEval.eval();
            for (int i = firstIndex; i <= secondIndex; i++) {
                evalMap.remove(i);
                evals.remove(firstIndex);
            }
            evals.add(firstIndex, null);
            for (Integer i : Lists.newArrayList(evalMap.keySet())) {
                if (i >= firstIndex) {
                    Object v = evalMap.remove(i);
                    evalMap.put(i - 2, v);
                }
            }
            evalMap.put(firstIndex, newV);
            if (evals.size() == 1) {
                return new ConstantEval(newV);
            }
        }
        if (formula.startsWith("\"")) {
            return new ConstantEval(formula);
        }
        if (StringUtils.isNumber(formula)) {
            return new ConstantEval(new BigDecimal(formula));
        }
        if ("TRUE".equals(formula)) {
            return new ConstantEval(true);
        } else if ("FALSE".equals(formula)) {
            return new ConstantEval(false);
        }
        if (formula.startsWith("I") || formula.startsWith("i")) {
            return KeywordClassify_I.select(formula, context);
        }
        if (formula.startsWith("R") || formula.startsWith("r")) {
            return KeywordClassify_R.select(formula, context);
        }
        if (formula.startsWith("L") || formula.startsWith("l")) {
            return KeywordClassify_L.select(formula, context);
        }
        return CommonClassify.select(formula, context);
    }
}