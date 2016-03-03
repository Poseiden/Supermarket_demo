package com.zhb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ton on 16-3-3.
 * 账单类
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = 315690697863231327L;

    private String id;
    private List<Record> records = null;
    private double totalPrice;

    /*********getter and setter************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    /*********constructor******/
    public Bill(List<Record> records, double totalPrice) {
        this.records = records;
        this.totalPrice = totalPrice;
    }
}
