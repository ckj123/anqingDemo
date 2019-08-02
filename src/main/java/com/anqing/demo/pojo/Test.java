package com.anqing.demo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: chenkuojun
 * @Date: 2019/7/29 11:02
 * @Description:
 */
@Data
public class Test implements Serializable {

    private int id;
    private String name;

}
