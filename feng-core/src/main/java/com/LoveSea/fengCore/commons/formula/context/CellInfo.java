package com.LoveSea.fengCore.commons.formula.context;

/**
 * @author xiahaifeng
 */
public interface CellInfo {

    int colIndex();

    RowInfo row();

    boolean isFormula();

    Object value();

    String formula();

    CellType type();
}