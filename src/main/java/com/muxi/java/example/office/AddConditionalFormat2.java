package com.muxi.java.example.office;

import com.spire.xls.*;

import java.awt.*;

/**
 * 应用单元格值、公式及数据条类型的条件格式
 *
 * @author jl.jiang 2021/1/22
 */
public class AddConditionalFormat2 {
    public static void main(String[] args) {
        //创建实例，加载测试文档
        Workbook wb = new Workbook();
        wb.loadFromFile("test.xlsx");

        //获取第一个工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //获取应用条件格式的数据范围
        CellRange range = sheet.getCellRange("A2:H27");

        //添加条件格式1
        ConditionalFormatWrapper format1 = range.getConditionalFormats().addCondition();
        //条件格式类型1基于单元格值
        format1.setFormatType(ConditionalFormatType.CellValue);
        //将数值在60到90之间的单元格进行字体加粗，并设置字体颜色为橙色
        format1.setFirstFormula("90");
        format1.setSecondFormula("100");
        format1.setOperator(ComparisonOperatorType.Between);
        format1.setFontColor(new Color(30, 144, 255));
        //format1.setBackColor(Color.orange);

        //添加条件格式2
        ConditionalFormatWrapper format2 = range.getConditionalFormats().addCondition();
        format2.setFormatType(ConditionalFormatType.CellValue);
        format2.setFirstFormula("60");
        format2.setOperator(ComparisonOperatorType.Less);
        format2.setFontColor(Color.red);
        //format2.setBackColor(Color.red);
        format2.isBold();
        //添加边框格式（边框颜色、边框类型）到条件格式2
        format2.setLeftBorderColor(Color.red);
        format2.setRightBorderColor(new Color(0, 0, 139));
        format2.setTopBorderColor(new Color(123, 104, 238));
        format2.setBottomBorderColor(new Color(50, 205, 50));
        format2.setLeftBorderStyle(LineStyleType.Medium);
        format2.setRightBorderStyle(LineStyleType.Thick);
        format2.setTopBorderStyle(LineStyleType.Double);
        format2.setBottomBorderStyle(LineStyleType.Double);

        //条件格式3的类型为公式
        ConditionalFormatWrapper format3 = range.getConditionalFormats().addCondition();
        format3.setFormatType(ConditionalFormatType.Formula);

        //自定义公式将低于60的单元格所在的行填充背景色
        format3.setFirstFormula("=OR($C2<60,$D2<60,$E2<60,$F2<60,$G2<60,$H2<60)");
        format3.setBackColor(Color.lightGray);


        //获取第二个工作表
        Worksheet sheet2 = wb.getWorksheets().get(1);

        //获取应用条件格式的数据范围
        CellRange range2 = sheet2.getCellRange("B2:D7");

        //添加条件类型4为data bars
        ConditionalFormatWrapper format4 = range2.getConditionalFormats().addCondition();
        format4.setFormatType(ConditionalFormatType.DataBar);
        format4.getDataBar().setBarColor(new Color(152, 251, 152));

        //保存文档
        wb.saveToFile("AddConditionalFormat2.xlsx", ExcelVersion.Version2013);
        wb.dispose();
    }
}
