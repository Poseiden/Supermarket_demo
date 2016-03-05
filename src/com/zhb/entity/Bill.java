package com.zhb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ton on 16-3-3.
 * 账单类
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = 315690697863231327L;

    private String id;
    private List<Record> records = new ArrayList<Record>();
    private double totalPrice;  //总价
    private double originalPrice;   //原价

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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    /*********constructor******/
    public Bill(List<Record> records, double totalPrice,double originalPrice) {
        this.records = records;
        this.totalPrice = totalPrice;
        this.originalPrice = originalPrice;
    }

    public Bill() {
    }
}
