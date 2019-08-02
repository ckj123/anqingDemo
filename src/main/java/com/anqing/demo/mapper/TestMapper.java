package com.anqing.demo.mapper;

import com.anqing.demo.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: chenkuojun
 * @Date: 2019/7/29 10:44
 * @Description:
 */
@Mapper
public interface TestMapper{

    public Test queryById(int id);
}
