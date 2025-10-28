package com.LoveSea.fengCore.commons.formula.context;

/**
 * @author xiahaifeng
 */

public interface WorkbookInfo {
    String url();

    ApplicationInfo getApplicationInfo();

    SheetInfo get(int sheetOrder);

    int sheetCount();
}