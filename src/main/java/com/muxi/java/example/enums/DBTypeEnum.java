package com.muxi.java.example.enums;

import lombok.Getter;

public enum DBTypeEnum {
    DEFAULTDB("default", "默认库"),
    OTHERDB("other", "其它库");

    @Getter
    private String type;

    @Getter
    private String name;

    DBTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
