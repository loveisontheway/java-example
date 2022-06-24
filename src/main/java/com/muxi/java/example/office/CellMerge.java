package com.muxi.java.example.office;

import java.util.ArrayList;
import java.util.List;


public class CellMerge {
    public static void main(String[] args) {
        List<String> columns = new ArrayList<>();//标头
        columns.add("目录");
        columns.add("目录");
        columns.add("目录");
        columns.add("内容");
        String fileName = "文件名字";//文件名字
        String sheetName = "sheet名字";//sheet名字
        //内容数据
        List<List<Object>> exportData = new ArrayList<>();
        //行内的数据
        List<Object> rowData = new ArrayList<Object>();
        rowData.add("一级目录1");
        rowData.add("二级目录1");
        rowData.add("三级目录1");
        rowData.add("内容1");
        exportData.add(rowData);
        List<Object> rowData2 = new ArrayList<Object>();
        rowData2.add("一级目录1");
        rowData2.add("二级目录1");
        rowData2.add("三级目录1");
        rowData2.add("内容2");
        exportData.add(rowData2);
        List<Object> rowData3 = new ArrayList<Object>();
        rowData3.add("一级目录1");
        rowData3.add("二级目录1");
        rowData3.add("三级目录2");
        rowData3.add("内容3");
        exportData.add(rowData3);
        List<Object> rowData4 = new ArrayList<Object>();
        rowData4.add("一级目录1");
        rowData4.add("二级目录1");
        rowData4.add("三级目录2");
        rowData4.add("内容4");
        exportData.add(rowData4);
        List<Object> rowData5 = new ArrayList<Object>();
        rowData5.add("一级目录1");
        rowData5.add("二级目录2");
        rowData5.add("三级目录3");
        rowData5.add("内容5");
        exportData.add(rowData5);
        List<Object> rowData6 = new ArrayList<Object>();
        rowData6.add("一级目录1");
        rowData6.add("二级目录2");
        rowData6.add("三级目录3");
        rowData6.add("内容6");
        exportData.add(rowData6);
        //需要合并的列号
        List<Integer> mergeIndex = new ArrayList<Integer>();
        mergeIndex.add(0);
        mergeIndex.add(1);
        mergeIndex.add(2);
        int flag = ExportExcel.exportToExcelForXlsx(exportData, fileName, sheetName, columns, mergeIndex, true);
        System.out.println(flag);
    }
}