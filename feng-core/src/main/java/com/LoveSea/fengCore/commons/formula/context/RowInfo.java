package com.LoveSea.fengCore.commons.formula.context;

/**
 * @author xiahaifeng
 */

public interface RowInfo {
    int rowIndex();

    CellInfo get(int col);

    SheetInfo sheet();
}