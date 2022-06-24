package com.muxi.java.example.office;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class ExportExcel {
    /**
     * 创建excel文件
     *
     * @param objData    数据
     * @param fileName   文件名
     * @param sheetName  sheet名
     * @param columns    表头
     * @param mergeIndex 需要合并的列号集合
     * @return
     */
    public static int exportToExcelForXlsx(List<List<Object>> objData, String fileName, String sheetName, List<String> columns, List<Integer> mergeIndex, boolean isTree) {
        int flag = 0;
        Collections.sort(mergeIndex);//将列号排序
        // 创建工作薄
        XSSFWorkbook wb = new XSSFWorkbook();
        // sheet1
        XSSFSheet sheet1 = wb.createSheet(sheetName);

        //设置样式
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平对齐

        //表头
        sheet1.createFreezePane(0, 1);//冻结表头
        XSSFRow sheet1row1 = sheet1.createRow((short) 0);
        sheet1row1.setHeight((short) 480);
        //写入表头
        if (columns != null && columns.size() > 0) {
            for (int i = 0; i < columns.size(); i++) {
                String column = columns.get(i);
                //列
                XSSFCell cell = sheet1row1.createCell(i);
                cell.setCellValue(column);
            }
        }

        int dataSatrtIndex = 1;//数据开始行
        boolean isMerge = false;
        if (mergeIndex.size() != 0) {
            isMerge = true;
        }

        //写入数据
        if (objData != null && objData.size() > 0) {
            Map<Integer, MergeModel> poiModels = new HashMap<Integer, MergeModel>();

            //循环写入表中数据
            int i = 0;
            for (; i < objData.size(); i++) {
                //数据行
                XSSFRow row = sheet1.createRow((short) (i + dataSatrtIndex));
                //行内循环，既单元格（列）
                List<Object> list = objData.get(i);
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                int j = 0;
                for (Object o : list) {
                    //数据列
                    String content = "";
                    if (o != null) {
                        if (o.toString().contains(".") && isNumeric(o.toString())) {
                            content = decimalFormat.format(Float.valueOf(o.toString()));
                        } else if (o.toString().contains("-") && o.toString().contains(":")) {
                            content = String.valueOf(o).split("\\.")[0];
                        } else {
                            content = String.valueOf(o);
                        }
                    }

                    if (isMerge && mergeIndex.contains(j)) {
                        //如果该列需要合并
                        MergeModel poiModel = poiModels.get(j);
                        if (poiModel == null) {
                            poiModel = new MergeModel();
                            poiModel.setContent(content);
                            poiModel.setRowIndex(i + dataSatrtIndex);
                            poiModel.setCellIndex(j);
                            poiModels.put(j, poiModel);
                        } else {
                            if (!poiModel.getContent().equals(content)) {
                                // 如果不同了，则将前面的数据合并写入
                                if (isTree) {
                                    // 此列向后的所有列都进行一次写入合并操作，并清空。
                                    // 树结构中存在这种情况，a目录和b目录为同级目录，a目录下最后一个子目录和b目录下的第一个子目录名称相同，
                                    // 防止本来不应该合并的单元格被合并
                                    addMergedRegionValue(sheet1, poiModels, mergeIndex, i + dataSatrtIndex, poiModel.getCellIndex());
                                } else {
                                    XSSFRow lastRow = sheet1.getRow(poiModel.getRowIndex());
                                    XSSFCell lastCell = lastRow.createCell(poiModel.getCellIndex());//创建列
                                    lastCell.setCellValue(poiModel.getContent());
                                    // 合并单元格
                                    if (poiModel.getRowIndex() != i + dataSatrtIndex - 1) {
                                        sheet1.addMergedRegion(new CellRangeAddress(poiModel.getRowIndex(), i + dataSatrtIndex - 1, poiModel.getCellIndex(), poiModel.getCellIndex()));
                                    }
                                }
                                // 将新数据存入
                                poiModel.setContent(content);
                                poiModel.setRowIndex(i + dataSatrtIndex);
                                poiModel.setCellIndex(j);
                                poiModels.put(j, poiModel);
                            }
                        }
                        row.createCell(j);//创建单元格
                    } else {//该列不需要合并
                        //数据列
                        XSSFCell cell = row.createCell(j);
                        cell.setCellValue(content);
                    }

                    j++;
                }
            }

            //将最后一份存入
            if (poiModels.size() != 0) {
                for (Integer key : poiModels.keySet()) {
                    MergeModel poiModel = poiModels.get(key);
                    XSSFRow lastRow = sheet1.getRow(poiModel.getRowIndex());
                    XSSFCell lastCell = lastRow.getCell(poiModel.getCellIndex());
                    lastCell.setCellValue(poiModel.getContent());
                    //合并单元格
                    if (poiModel.getRowIndex() != i + dataSatrtIndex - 1) {
                        sheet1.addMergedRegion(new CellRangeAddress(poiModel.getRowIndex(), i + dataSatrtIndex - 1, poiModel.getCellIndex(), poiModel.getCellIndex()));
                    }
                }
            }
        } else {
            flag = -1;
        }

        //设置固定列宽，poi的列宽设置有点操蛋，大概规律网上有不少版本自行百度
        //这里大概是143像素
        for (int i = 0; i < columns.size(); i++) {
            sheet1.setColumnWidth(i, 4550);
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("E:\\" + fileName + ".xlsx");
            wb.write(out);
        } catch (Exception ex) {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                flag = 0;
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 判断是不是数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    private static void addMergedRegionValue(XSSFSheet sheet, Map<Integer, MergeModel> poiModels,
                                             List<Integer> mergeIndex, int nowRowIndex, int nowCellIndex) {
        if (poiModels != null && poiModels.size() != 0 && mergeIndex != null && mergeIndex.size() != 0) {
            for (Integer index : mergeIndex) {
                if (index >= nowCellIndex) {
                    MergeModel poiModel = poiModels.remove(index);//删除并获取value
                    if (poiModel != null) {
                        XSSFRow lastRow = sheet.getRow(poiModel.getRowIndex());
                        XSSFCell lastCell = lastRow.createCell(poiModel.getCellIndex());//创建列
                        lastCell.setCellValue(poiModel.getContent());
                        // 合并单元格
                        if (poiModel.getRowIndex() != nowRowIndex - 1) {
                            sheet.addMergedRegion(new CellRangeAddress(poiModel.getRowIndex(), nowRowIndex - 1, poiModel.getCellIndex(), poiModel.getCellIndex()));
                        }
                    }
                }
            }
        }
    }

}