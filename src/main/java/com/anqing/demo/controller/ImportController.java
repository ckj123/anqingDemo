package com.anqing.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: chenkuojun
 * @Date: 2019/7/29 10:27
 * @Description:
 */
@Controller
public class ImportController {
    @RequestMapping(value = "/index")
    public String home() {
        return "static/index";
    }
}
