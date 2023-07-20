package com.muxi.java.example.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

/**
 * 中国行政区划-sys_area
 *
 * @author elec
 * @date 2023-05-05
 */
public class SysArea {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级行政代码
     */
    private Long parentCode;

    /**
     * 行政代码
     */
    private Long areaCode;

    /**
     * 邮政编码
     */
    private Integer zipCode;

    /**
     * 区号
     */
    private String cityCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 组合名
     */
    private String mergerName;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 缩写
     */
    private String abbr;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

    public Long getParentCode() {
        return parentCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    public Long getAreaCode() {
        return areaCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLat() {
        return lat;
    }

}