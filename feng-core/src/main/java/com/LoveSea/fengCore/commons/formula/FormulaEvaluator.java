package com.LoveSea.fengCore.commons.formula;


import com.LoveSea.fengCore.commons.formula.classify.KeywordClassify;
import com.LoveSea.fengCore.commons.formula.context.ApplicationInfo;
import com.LoveSea.fengCore.commons.formula.context.CalFormulaContext;
import com.LoveSea.fengCore.commons.formula.context.CellInfo;
import com.LoveSea.fengCore.commons.formula.context.WorkbookInfo;
import com.LoveSea.fengCore.commons.formula.context.inner.AbstractApplicationInfo;
import com.LoveSea.fengCore.commons.formula.context.inner.AbstractCellInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author xiahaifeng
 */
@Slf4j
public class FormulaEvaluator {
    private final ApplicationInfo applicationInfo_;

    public ApplicationInfo getApplicationInfo() {
        return applicationInfo_;
    }

    public void Load(String url) {
        // load excel // FIXME xia 2025/6/11 11:31 : 未完成
        WorkbookInfo workbookInfo = null;
        ((AbstractApplicationInfo) applicationInfo_).addWorkbookInfo(workbookInfo);
    }

    public FormulaEvaluator() {
        this.applicationInfo_ = new AbstractApplicationInfo();
    }

    public Object eval(String url, int sheetOrder, int row, int column) {
        CellInfo cellInfo = Optional.ofNullable(applicationInfo_.getWorkbookInfo(url))
                .map(workbookInfo -> workbookInfo.get(sheetOrder))
                .map(sheetInfo -> sheetInfo.get(row))
                .map(rowInfo -> rowInfo.get(column))
                .orElse(null);
        if (null == cellInfo) {
            String cellMsg = String.format("url:%s,sheetOrder:%s,row:%s,column:%s", url, sheetOrder, row, column);
            log.error("{} 找不到 单元格信息", cellMsg);
            throw new FormulaException("找不到 单元格信息:" + cellMsg);
        }
        if (cellInfo.isFormula()) {
            if (null != cellInfo.value()) {
                return cellInfo.value();
            }
            Object v;
            try {
                String formula = cellInfo.formula();
                if (!formula.startsWith("=")) {
                    throw new FormulaException("公式错误：" + cellInfo.formula());
                }
                formula = formula.substring(1);
                CalFormulaContext context = new CalFormulaContext(this, cellInfo);
                v = KeywordClassify.select(formula, context).eval();

            } catch (FormulaException e) {
                log.error(e.getMessage(), e);
                v = e.getFormulaExceptionType().errorCode;
            } catch (Exception e) {
                log.error("公式错误：{}", cellInfo.formula(), e);
                v = FormulaException.FormulaExceptionType.OTHER.errorCode;
            }
            ((AbstractCellInfo) cellInfo).setValue(v);
            return v;
        } else {
            return cellInfo.value();
        }
    }

    public void calAll() {
    }

}