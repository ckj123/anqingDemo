package com.anqing.demo.controller;

import com.anqing.demo.pojo.ResultData;
import com.anqing.demo.utils.CalcSlidingMeanTemperatureUtils;
import com.anqing.demo.utils.ImportExcelUtil1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: chenkuojun
 * @Date: 2019/8/2 17:16
 * @Description:
 */
@Controller
@RequestMapping("/file")
public class UploadController {
    /*@Autowired
    private UploadService uploadService;*/
    @RequestMapping("/importExp")
    @ResponseBody
    public String importExp(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();//原文件名字
                InputStream is = file.getInputStream();//获取输入流
                List<List<Object>> dataList = new ImportExcelUtil1().getBankListByExcel(is, originalFilename);
                List<ResultData> deal = CalcSlidingMeanTemperatureUtils.deal(dataList);
                System.out.println(deal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "成功";
    }



}
