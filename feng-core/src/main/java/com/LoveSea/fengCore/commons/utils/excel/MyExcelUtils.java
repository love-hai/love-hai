package com.LoveSea.fengCore.commons.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author xiahaifeng
 */
@Slf4j
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

    public static int columnNameToIndex(String columnName) {
        int index = 0;
        columnName = columnName.toUpperCase();
        for (char c : columnName.toCharArray()) {
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException("Invalid column name: " + columnName);
            }
            index = index * 26 + (c - 'A' + 1);
        }
        return index - 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 1000; i++) {
            String name = indexToColumnName(i);
            if (i != columnNameToIndex(name)) {
                System.out.println("error" + i);
            }
        }


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

//    public static void main(String[] args) {
//        String path = "C:\\Users\\xiaha\\Downloads\\新建 XLSX 工作表.xlsx";
//        setCalculation(Path.of(path), ExcelCalculationEnum.manual);
//    }

    public static boolean setCalculation(Path excelPath, ExcelCalculationEnum calculation) {
        Path tempPath = excelPath.resolveSibling(excelPath.getFileName().toString() + ".tmp");
        try (ZipFile zipFile = new ZipFile(excelPath.toFile());
             ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(tempPath))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream is = zipFile.getInputStream(entry);
                ZipEntry newEntry = new ZipEntry(entry.getName());
                zos.putNextEntry(newEntry);
                if ("xl/workbook.xml".equals(entry.getName())) {
                    // 修改 workbook.xml
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setNamespaceAware(true);
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(is);
                    // 处理 calcPr
                    Node workbookElement = doc.getElementsByTagName("workbook").item(0);
                    NodeList calcPrList = ((Element) workbookElement).getElementsByTagName("calcPr");
                    Element calcPrElement;
                    if (calcPrList.getLength() > 0) {
                        calcPrElement = (Element) calcPrList.item(0);
                    } else {
                        calcPrElement = doc.createElement("calcPr");
                        workbookElement.appendChild(calcPrElement);
                    }
                    calcPrElement.setAttribute("calcId", "191029");
                    calcPrElement.setAttribute("calcMode", calculation.getCode());
                    // 写入修改后的 XML
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.transform(new DOMSource(doc), new StreamResult(zos));
                } else {
                    // 普通文件直接复制
                    is.transferTo(zos);
                }
                zos.closeEntry();
                is.close();
            }
        } catch (Exception e) {
            log.error("setCalculation failed", e);
            return false;
        }
        try {
            // 替换原文件
            Files.move(tempPath, excelPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            log.error("setCalculation failed", e);
            return false;
        } finally {
            try {
                Files.deleteIfExists(tempPath);
            } catch (IOException ignored) {
            }
        }
    }

    public static boolean setCalculateBeforeSave(Path excelPath, boolean calculateBeforeSave) {
        // TODO Auto-generated method stub
        return false;
    }
}