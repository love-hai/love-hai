package com.LoveSea.fengCore.commons.formula.context.inner;

import com.LoveSea.fengCore.commons.formula.context.ApplicationInfo;
import com.LoveSea.fengCore.commons.formula.context.WorkbookInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahaifeng
 */

public class AbstractApplicationInfo implements ApplicationInfo {

    public AbstractApplicationInfo() {
    }

    private Map<String, WorkbookInfo> workbookInfoMap;

    @Override
    public WorkbookInfo getWorkbookInfo(String url) {
        if (workbookInfoMap == null) {
            return null;
        }
        return workbookInfoMap.get(url);
    }

    public void addWorkbookInfo(WorkbookInfo workbookInfo) {
        if (null == workbookInfoMap) {
            workbookInfoMap = new HashMap<>();
        }
        workbookInfoMap.put(workbookInfo.url(), workbookInfo);
        ((AbstractWorkbookInfo) workbookInfo).setApplicationInfo(this);
    }
}