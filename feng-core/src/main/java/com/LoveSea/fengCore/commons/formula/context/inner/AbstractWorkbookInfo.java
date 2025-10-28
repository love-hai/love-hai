package com.LoveSea.fengCore.commons.formula.context.inner;

import com.LoveSea.fengCore.commons.formula.context.ApplicationInfo;
import com.LoveSea.fengCore.commons.formula.context.SheetInfo;
import com.LoveSea.fengCore.commons.formula.context.WorkbookInfo;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class AbstractWorkbookInfo implements WorkbookInfo {
    public AbstractWorkbookInfo(String url) {
        this.url = url;
    }
    @Setter
    private ApplicationInfo applicationInfo;
    private Map<Integer, SheetInfo> sheetInfoMap;
    private String url;

    public void addSheetInfo(SheetInfo sheetInfo) {
        if (sheetInfoMap == null) {
            sheetInfoMap = new HashMap<>();
        }
        sheetInfoMap.put(sheetInfo.sheetOrder(), sheetInfo);
        if (sheetInfo instanceof AbstractSheetInfo) {
            ((AbstractSheetInfo) sheetInfo).setWorkbook(this);
        }
    }


    @Override
    public String url() {
        return url;
    }

    @Override
    public ApplicationInfo getApplicationInfo(){
        return applicationInfo;
    }

    @Override
    public SheetInfo get(int sheetOrder) {
        if (null == sheetInfoMap) {
            return null;
        }
        return sheetInfoMap.get(sheetOrder);
    }

    @Override
    public int sheetCount() {
        if (null == sheetInfoMap) {
            return 0;
        }
        return sheetInfoMap.size();
    }
}