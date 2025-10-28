package com.LoveSea.fengCore.commons.formula.eval;

import com.LoveSea.fengCore.commons.formula.context.*;
import com.LoveSea.fengCore.commons.formula.fun.FunUtils;
import com.LoveSea.fengCore.commons.utils.excel.MyExcelUtils;


/**
 * @author xiahaifeng
 */

public class SameSheetCellEval implements Eval {

    public SameSheetCellEval(String formula, CalFormulaContext calFormulaContext) {
        this.formula = formula;
        this.calFormulaContext = calFormulaContext;
    }

    private final CalFormulaContext calFormulaContext;

    private String formula;

    @Override
    public Object eval() {
        formula = formula.trim();
        String[] cr = FunUtils.splitCell(formula);
        String colChar = cr[0];
        int colIndex = MyExcelUtils.columnNameToIndex(colChar);
        String rowNumStr = cr[1];
        // 单元格的名称的行数是从1开始 ，所有 index 需要行数减1
        // 现在计算 同一个表的一行的不需要这个属性。
        int rowIndex = Integer.parseInt(rowNumStr) - 1;
        // 同一个sheet
        CellInfo currentCellInfo = calFormulaContext.getCurrentCellInfo();
        RowInfo rowInfo = currentCellInfo.row();
        SheetInfo sheetInfo = rowInfo.sheet();
        WorkbookInfo workbookInfo = sheetInfo.workbook();
        String url = workbookInfo.url();
        int sheetOrder = sheetInfo.sheetOrder();
        return calFormulaContext.getEvaluator().eval(url, sheetOrder, rowIndex, colIndex);
    }
}