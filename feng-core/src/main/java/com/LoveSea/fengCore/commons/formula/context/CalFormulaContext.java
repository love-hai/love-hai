package com.LoveSea.fengCore.commons.formula.context;


import com.LoveSea.fengCore.commons.formula.FormulaEvaluator;

import java.util.Map;

/**
 * @author xiahaifeng
 */

public class CalFormulaContext {

    public CalFormulaContext(FormulaEvaluator evaluator, CellInfo currentCellInfo) {
        this.currentCellInfo_ = currentCellInfo;
        this.evaluator_ = evaluator;
    }

    private final FormulaEvaluator evaluator_;
    private final CellInfo currentCellInfo_;
    private Map<String, Object> letKVMap;


    public void setLetKV(Map<String, Object> letKVMap) {
        this.letKVMap = letKVMap;
    }

    public Object getLetKV(String key) {
        if (null == letKVMap) {
            return null;
        }
        return letKVMap.get(key);
    }

    public CalFormulaContext nextCell(CellInfo cellInfo) {
        return new CalFormulaContext(evaluator_, cellInfo);
    }

    public FormulaEvaluator getEvaluator() {
        return evaluator_;
    }

    public CellInfo getCurrentCellInfo() {
        return currentCellInfo_;
    }
}