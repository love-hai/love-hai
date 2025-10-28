package com.LoveSea.fengCore.commons.formula.context.inner;

import com.LoveSea.fengCore.commons.formula.context.CellInfo;
import com.LoveSea.fengCore.commons.formula.context.CellType;
import com.LoveSea.fengCore.commons.formula.context.RowInfo;
import lombok.Setter;

/**
 * @author xiahaifeng
 */

public class AbstractCellInfo implements CellInfo {
    public AbstractCellInfo(int colIndex, Object value, String formula, boolean isFormula) {
        this.colIndex = colIndex;
        this.value = value;
        this.formula = formula;
        this.isFormula = isFormula;
        CellType type;
        if (isFormula) {
            type = CellType.FORMULA;
        } else {
            if (value instanceof String) {
                type = CellType.STRING;
            } else if (value instanceof Number) {
                type = CellType.NUMBER;
            } else if (value instanceof Boolean) {
                type = CellType.BOOLEAN;
            } else {
                type = CellType.UNDEFINED;
            }
        }
        this.type = type;
    }

    @Setter
    private RowInfo rowinfo;

    private final int colIndex;

    private final String formula;

    private final boolean isFormula;

    private final CellType type;

    @Setter
    private Object value;


    @Override
    public int colIndex() {
        return colIndex;
    }

    @Override
    public RowInfo row() {
        return rowinfo;
    }


    @Override
    public boolean isFormula() {
        return isFormula;
    }

    @Override
    public Object value() {
        return value;
    }

    @Override
    public String formula() {
        return formula;
    }

    @Override
    public CellType type() {
        return type;
    }
}