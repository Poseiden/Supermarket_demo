package com.zhb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ton on 16-3-3.
 * 产品类
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 4204523317659259390L;

    private String barCode;
    private String name;
    private BigDecimal price;
    private List<Favorable> favorableList = new ArrayList<Favorable>();     //所参与的优惠活动

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Favorable> getFavorable() {
        return favorableList;
    }

    public void setFavorable(List<Favorable> favorableList) {
        this.favorableList = favorableList;
    }

    /*Constructor*/
    public Product(String barCode, String name, BigDecimal price,List<Favorable> favorableList) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
        this.favorableList = favorableList;
    }

    public Product() {
    }
}
