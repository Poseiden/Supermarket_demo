package com.zhb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private BigDecimal totalPrice;  //总价
    private BigDecimal originalPrice;   //原价
    private List<Record> favorableRecords = new ArrayList<Record>();    //参与优惠的消费记录集合

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public List<Record> getFavorableRecords() {
        return favorableRecords;
    }

    public void setFavorableRecords(List<Record> favorableRecords) {
        this.favorableRecords = favorableRecords;
    }

    /*********constructor******/
    public Bill(List<Record> records, BigDecimal totalPrice,BigDecimal originalPrice,List<Record> favorableRecords) {
        this.records = records;
        this.totalPrice = totalPrice;
        this.originalPrice = originalPrice;
        this.favorableRecords = favorableRecords;
    }

    public Bill() {
    }
}
