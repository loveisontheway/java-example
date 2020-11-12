package com.muxi.java.example.enums;

/**
 * 评估类别 枚举
 *
 * @author jl.jiang 2020/10/12
 */
public enum EstimateTypeEnum {

    E2501("E2501", "车辆使用情况"),
    E2502("E2502", "车辆违章情况"),
    E2503("E2503", "车辆出险情况"),
    E2504("E2504", "车辆超速情况");

    private String code;
    private String name;

    EstimateTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // code -> name
    public static String getName(String code) {
        for (EstimateTypeEnum e : EstimateTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
