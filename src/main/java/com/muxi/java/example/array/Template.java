package com.muxi.java.example.array;

/**
 * @author jjl
 * @date 2023/6/7
 */
public class Template {
    private String pro;
    private String step;
    private String name;

    public Template() {
    }

    public Template(String pro, String step, String name) {
        this.pro = pro;
        this.step = step;
        this.name = name;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
