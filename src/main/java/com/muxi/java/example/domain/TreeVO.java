package com.muxi.java.example.domain;

import java.util.List;

public class TreeVO {

    private String sqNum;

    private Long id;

    private Long parentId;

    private List<TreeVO> childList;

    public String getSqNum() {
        return sqNum;
    }

    public void setSqNum(String sqNum) {
        this.sqNum = sqNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeVO> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeVO> childList) {
        this.childList = childList;
    }
}

