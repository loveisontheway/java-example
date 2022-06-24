package com.muxi.java.example.office;

/**
 * 用于保存需要合并的单元格
 */
public class MergeModel {
    // 内容
    private String content;
    // 记录相同内容的开始行号
    private int rowIndex;
    // 列号
    private int cellIndex;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

}