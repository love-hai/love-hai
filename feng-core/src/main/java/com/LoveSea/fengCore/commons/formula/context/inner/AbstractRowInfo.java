package com.LoveSea.fengCore.commons.formula.context.inner;

import com.LoveSea.fengCore.commons.formula.context.RowInfo;
import com.LoveSea.fengCore.commons.formula.context.CellInfo;
import com.LoveSea.fengCore.commons.formula.context.SheetInfo;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class AbstractRowInfo implements RowInfo {
    public AbstractRowInfo(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    private Map<Integer, CellInfo> cellInfoMap;


    private final int rowIndex;
    @Setter
    private SheetInfo sheetInfo;

    @Override
    public int rowIndex() {
        return rowIndex;
    }

    @Override
    public CellInfo get(int col) {
        if (null == cellInfoMap) {
            return null;
        }
        return cellInfoMap.get(col);
    }

    @Override
    public SheetInfo sheet() {
        return sheetInfo;
    }

    public void addCellInfo(CellInfo cellInfo) {
        if (null == cellInfoMap) {
            cellInfoMap = new HashMap<>();
        }
        cellInfoMap.put(cellInfo.colIndex(), cellInfo);
        if (cellInfo instanceof AbstractCellInfo) {
            ((AbstractCellInfo) cellInfo).setRowinfo(this);
        }
    }
}