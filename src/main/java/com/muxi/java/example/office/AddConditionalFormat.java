package com.muxi.java.example.office;

import com.spire.xls.*;
import com.spire.xls.core.IConditionalFormat;
import com.spire.xls.core.spreadsheet.collections.XlsConditionalFormats;
import com.spire.xls.core.spreadsheet.conditionalformatting.TimePeriodType;

import java.awt.*;

/**
 * 应用条件格式高亮重复值、唯一值、峰值、高于或低于平均值
 * 官网：https://www.e-iceblue.cn/
 * Spire.XLS for Java（Java Excel 组件）
 * Maven：http://repo.e-iceblue.cn/#browse/browse:maven-public:e-iceblue
 * Document：https://www.e-iceblue.cn/licensing/install-spirepdf-for-java-from-maven-repository.html
 *
 * @author jl.jiang 2021/1/22
 */
public class AddConditionalFormat {
    public static void main(String[] args) {
        //创建实例，加载测试文档
        Workbook wb = new Workbook();
        wb.loadFromFile("test.xlsx");

        //获取第一个工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //添加条件格式1并指定数据范围
        XlsConditionalFormats format1 = sheet.getConditionalFormats().add();
        format1.addRange(sheet.getCellRange("A2:A12"));
        //高亮低于平均数值的单元格
        IConditionalFormat cf1 = format1.addAverageCondition(AverageType.Below);
        cf1.setBackColor(new Color(230, 230, 250));
        //高亮高于平均数值的单元格
        IConditionalFormat cf2 = format1.addAverageCondition(AverageType.Above);
        cf2.setBackColor(new Color(224, 255, 255));

        //添加条件格式2并指定数据范围
        XlsConditionalFormats format2 = sheet.getConditionalFormats().add();
        format2.addRange(sheet.getCellRange("B2:B12"));
        //高亮最高值
        IConditionalFormat cf3 = format2.addTopBottomCondition(TopBottomType.Top, 1);
        cf3.setBackColor(new Color(144, 238, 144));
        //高亮最低值单元格
        IConditionalFormat cf4 = format2.addTopBottomCondition(TopBottomType.Bottom, 1);
        cf4.setBackColor(new Color(221, 160, 221));

        //添加条件格式3并指定数据范围
        XlsConditionalFormats format3 = sheet.getConditionalFormats().add();
        format3.addRange(sheet.getCellRange("C2:C12"));
        //高亮唯一值的单元格
        IConditionalFormat cf5 = format3.addDuplicateValuesCondition();
        cf5.setFormatType(ConditionalFormatType.UniqueValues);
        cf5.setBackColor(new Color(0, 255, 255));

        //添加条件格式4并指定数据范围
        XlsConditionalFormats format4 = sheet.getConditionalFormats().add();
        format4.addRange(sheet.getCellRange("D2:D12"));
        //高亮重复数值的单元格
        IConditionalFormat cf6 = format4.addDuplicateValuesCondition();
        cf6.setFormatType(ConditionalFormatType.DuplicateValues);
        cf6.setBackColor(new Color(255, 228, 196));

        //添加条件格式5并指定数据范围
        XlsConditionalFormats format5 = sheet.getConditionalFormats().add();
        format5.addRange(sheet.getCellRange("E2:E12"));
        //高亮本周日期的单元格
        IConditionalFormat cf7 = format5.addTimePeriodCondition(TimePeriodType.ThisWeek);
        cf7.setBackColor(new Color(255, 165, 0));

        //保存文档
        wb.saveToFile("AddConditionalFormat.xlsx", ExcelVersion.Version2013);
        wb.dispose();
    }
}
