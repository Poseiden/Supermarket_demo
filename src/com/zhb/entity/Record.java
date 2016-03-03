package com.zhb.entity;

import java.io.Serializable;

/**
 * Created by ton on 16-3-3.
 * 记录类 （账单上的每条记录）
 */
public class Record implements Serializable {
    private static final long serialVersionUID = -416505332892171646L;

    private String id;
    private Product product;
    private int count;
    private Favorable favorable;
    private double totalPrice;
    private double originalPrice;

    /*****getter and setter*****/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Favorable getFavorable() {
        return favorable;
    }

    public void setFavorable(Favorable favorable) {
        this.favorable = favorable;
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

    /******Constructor*****/
    public Record(Product product, int count, Favorable favorable, double totalPrice, double originalPrice) {
        this.product = product;
        this.count = count;
        this.favorable = favorable;
        this.totalPrice = totalPrice;
        this.originalPrice = originalPrice;
    }
}
