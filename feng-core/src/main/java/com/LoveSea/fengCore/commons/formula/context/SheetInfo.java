package com.LoveSea.fengCore.commons.formula.context;

/**
 * @author xiahaifeng
 */

public interface SheetInfo {
    WorkbookInfo workbook();

    int sheetOrder();

    RowInfo get(int rowIndex);
}