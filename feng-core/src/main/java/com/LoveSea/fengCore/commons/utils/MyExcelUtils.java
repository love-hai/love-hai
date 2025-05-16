package com.LoveSea.fengCore.commons.utils;

/**
 * @author xiahaifeng
 */

public class MyExcelUtils {


    /**
     * indexToColumnName :  excel列索引转列名
     *
     * @param columnIndex 列索引
     * @return 列名 A B C
     * @author xiahaifeng
     */
    public static String indexToColumnName(int columnIndex) {
        if (columnIndex < 0) {
            throw new IllegalArgumentException("Column index must be non-negative");
        }
        StringBuilder columnName = new StringBuilder();
        while (columnIndex >= 0) {
            columnName.insert(0, (char) ('A' + columnIndex % 26));
            columnIndex = columnIndex / 26 - 1;
        }
        return columnName.toString();

    }

    /**
     * numToColumnName : excel列序号转列名
     *
     * @param columnNum 列序号 从1开始
     * @return 列名 A B C
     * @author xiahaifeng
     */
    public static String numToColumnName(int columnNum) {
        return indexToColumnName(columnNum - 1);
    }
}