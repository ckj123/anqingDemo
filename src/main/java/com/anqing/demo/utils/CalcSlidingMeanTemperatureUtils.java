package com.anqing.demo.utils;

import com.anqing.demo.pojo.ResultData;
import com.anqing.demo.pojo.Weather;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalcSlidingMeanTemperatureUtils {
    public static List<ResultData> deal(List<List<Object>> dataList) {
        List<Weather> excelDataList=new ArrayList<>();//用于存放所有的excel中的数据
        //数据封装格式一，将表格中的数据遍历取出后封装进对象放进List
        //将数据从excell中读取出来，然后按年进行存放在不同的集合中
        for (int i = 0; i <dataList.size(); i++) {
        //for (int i = 365; i <730; i++) {
            // 获取表格中的每一行数据
            Object stationName = String.valueOf(dataList.get(i).get(0));
            Object areaNo = String.valueOf(dataList.get(i).get(1));
            Object dateTime = String.valueOf(dataList.get(i).get(2));
            Object zonePressure = String.valueOf(dataList.get(i).get(3));
            Object avgTemperature = String.valueOf(dataList.get(i).get(4));
            Object maxTemperature = String.valueOf(dataList.get(i).get(5));
            Object mixTemperature = String.valueOf(dataList.get(i).get(6));
            Object waterPressure = String.valueOf(dataList.get(i).get(7));
            Object relativeHumidity = String.valueOf(dataList.get(i).get(8));
            Object precipitation = String.valueOf(dataList.get(i).get(9));
            Object evaporationCapacity = String.valueOf(dataList.get(i).get(10));
            Object sunlight = String.valueOf(dataList.get(i).get(11));
            // 将数据封装道实体对象中
            Weather weather=new Weather();
            weather.setStationName(stationName.toString());
            weather.setAreaNo(areaNo.toString());
            weather.setDateTime(dateTime.toString());
            weather.setZonePressure(zonePressure.toString());
            weather.setAvgTemperature(avgTemperature.toString());
            weather.setMaxTemperature(maxTemperature.toString());
            weather.setMixTemperature(mixTemperature.toString());
            weather.setWaterPressure(waterPressure.toString());
            weather.setRelativeHumidity(relativeHumidity.toString());
            weather.setPrecipitation(precipitation.toString());
            weather.setEvaporationCapacity(evaporationCapacity.toString());
            weather.setSunlight(sunlight.toString());
            weather.setYear(dateTime.toString().substring(0,4));
            // 添加到集合中
            excelDataList.add(weather);
        }
        Map<String, List<Weather>>  map = excelDataList.stream().collect(Collectors.groupingBy(Weather::getYear));
        List<ResultData> resultDataLists = new ArrayList<>(); //存放结果集
        //遍历map
        map.forEach((key,list) ->{
            // 找到第一次大于界线温度
            ResultData resultData = new ResultData();
            System.out.println("年份为:" + key);
            int flag = 0;
            for (int i = 0; i <list.size() ; i++) {
                if(Integer.parseInt(list.get(i).getAvgTemperature())>=10){
                    flag = i;
                    break;
                }
            }

            //第一次小于界线温度
            int flag1 = 0;
            for (int i = list.size()-1; i > flag1; i--) {
                if(Integer.parseInt(list.get(i).getAvgTemperature())>=10){
                    flag1 = i;
                    break;
                }
            }

            //计算滑动平均温度
            ArrayList<String> newList = new ArrayList<>();//用于存放滑动平均值

            for (int i = flag -4; i <flag1 + 4; i++){
                int sum = 0;

                for (int j = 0; j < 5 ; j++) {
                    sum = sum + Integer.parseInt(list.get(i+j).getAvgTemperature());
                }
                sum = sum/5;  // 5为滑动长度
                newList.add(String.valueOf(sum));
            }

            //选取第一个大于界限温度的滑动平均数据
            Integer ss = null;
            for (int i = 0; i < newList.size(); i++) {
                if(Integer.parseInt(newList.get(i))>=10){
                    ss = i;
                    break;
                }
            }

            // 获取初起日期
            String dateTime = null;
            for (int i = ss+flag-4; i <ss+flag+1 ; i++) {
                if(Integer.parseInt(list.get(i).getAvgTemperature())>=10){
                    dateTime = list.get(i).getDateTime();
                    break;
                }
            }
            resultData.setBeginDate(dateTime);
            System.out.println("初起日期:"+dateTime);

            //计算终日
            //选取第一个小于等于界限温度的滑动平均数据
            Integer ss1 = null;
            for (int i = newList.size()-1; i > ss; i--) {
                if(Integer.parseInt(newList.get(i))>=10){
                    ss1 = i;
                    break;
                }
            }

            // 获取终日
            String dateTime1 = null;
            //倒叙
            for (int i = ss1+flag; i > ss1+flag-5; i--) {
                if(Integer.parseInt(list.get(i).getAvgTemperature())>=10){
                    dateTime1 = list.get(i).getDateTime();
                    break;
                }
            }
            resultData.setEndDate(dateTime1);
            System.out.println("终日:"+dateTime1);


            // 计算积温  从初日到终日
            int begin = 0;
            int end = 0;
            for (int i = 0; i < list.size(); i++) {
                if(dateTime.equals(list.get(i).getDateTime())){
                    begin = i;
                }
                if(dateTime1.equals(list.get(i).getDateTime())){
                    end = i;
                }
            }

            int total = 0;
            for (int i = begin; i <= end; i++) {
                total += Integer.valueOf(list.get(i).getAvgTemperature());
            }
            resultData.setAccumulatedTemperature(String.valueOf(total));
            resultData.setYear(Integer.valueOf(key));
            System.out.println("积温为:"+ total);
            resultDataLists.add(resultData);
        });
        resultDataLists.sort((x,y) -> x.getYear() - y.getYear());
        System.out.println(resultDataLists);
        return resultDataLists;
    }
}
