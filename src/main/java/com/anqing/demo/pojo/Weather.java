package com.anqing.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: chenkuojun
 * @Date: 2019/8/1 14:45
 * @Description:
 */
@Data
public class Weather implements Serializable {
    private static final long serialVersionUID = 601321481404253544L;

    /**
     * 站名
     */
    private String stationName;

    /**
     * 区站号
     */
    private String areaNo;

    /**
     * 时间(年月日)
     */
    private String dateTime;

    /**
     * 本站气压
     */
    private String zonePressure;

    /**
     * 平均气温
     */
    private String avgTemperature;

    /**
     * 最高气温
     */
    private String maxTemperature;

    /**
     * 最低气温
     */
    private String mixTemperature;

    /**
     * 水汽压
     */
    private String waterPressure;

    /**
     * 相对湿度
     */
    private String relativeHumidity;

    /**
     * 降水量
     */
    private String precipitation;

    /**
     * 蒸发量
     */
    private String evaporationCapacity;

    /**
     * 日照
     */
    private String sunlight;

    /**
     * 年份   用去区分数据
     */
    private String year;
}
