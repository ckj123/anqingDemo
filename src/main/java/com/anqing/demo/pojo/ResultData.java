package com.anqing.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: chenkuojun
 * @Date: 2019/8/2 14:07
 * @Description:  用于存放 初日，终日，积温
 */
@Data
public class ResultData implements Serializable {

    private static final long serialVersionUID = 1035998259231890956L;

    /**
     * 初日
     */
    private String beginDate;

    /**
     * 终日
     */
    private String endDate;

    /**
     * 积温
     */
    private String accumulatedTemperature;

    /**
     * 年份
     */
    private Integer year;
}
