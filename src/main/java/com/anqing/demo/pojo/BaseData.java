package com.anqing.demo.pojo;

import java.io.Serializable;

public class BaseData implements Serializable {
    private String date;
    private String avg;

    public BaseData() {
    }

    public BaseData(String date, String avg) {
        this.date = date;
        this.avg = avg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }
}
