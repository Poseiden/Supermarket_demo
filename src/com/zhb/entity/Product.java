package com.zhb.entity;

import java.io.Serializable;

/**
 * Created by ton on 16-3-3.
 * 产品类
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 4204523317659259390L;

    private String barCode;
    private String name;
    private double price;

    /*getter and setter*/
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*Constructor*/
    public Product(String barCode, String name, double price) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }
}
