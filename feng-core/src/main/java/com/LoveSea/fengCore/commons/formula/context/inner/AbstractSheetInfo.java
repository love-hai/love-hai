package com.LoveSea.fengCore.commons.formula.context.inner;

import com.LoveSea.fengCore.commons.formula.context.RowInfo;
import com.LoveSea.fengCore.commons.formula.context.SheetInfo;
import com.LoveSea.fengCore.commons.formula.context.WorkbookInfo;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class AbstractSheetInfo implements SheetInfo {

    public AbstractSheetInfo(int sheetOrder) {
        this.sheetOrder = sheetOrder;
    }

    private Map<Integer, RowInfo> rowInfoMap;

    public void addRowInfo(RowInfo rowInfo) {
        if (null == rowInfoMap) {
            rowInfoMap = new HashMap<>();
        }
        rowInfoMap.put(rowInfo.rowIndex(), rowInfo);
        ((AbstractRowInfo) rowInfo).setSheetInfo(this);
    }

    private final int sheetOrder;
    @Setter
    private WorkbookInfo workbook;

    @Override
    public WorkbookInfo workbook() {
        return workbook;
    }

    @Override
    public int sheetOrder() {
        return sheetOrder;
    }

    @Override
    public RowInfo get(int rowIndex) {
        if (null == rowInfoMap) {
            return null;
        }
        return rowInfoMap.get(rowIndex);
    }

    public void clear() {
        if (null != rowInfoMap) {
            rowInfoMap.clear();
        }
    }
}