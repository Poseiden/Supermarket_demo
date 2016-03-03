package com.zhb.entity;

import java.io.Serializable;

/**
 * Created by ton on 16-3-3.
 * 优惠类
 */
public class Favorable implements Serializable {
    private static final long serialVersionUID = -326684716269722458L;

    private String id;
    private Product product;
    private int type;

    /*********getter and setter***********/
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /******Constructor*****/
    public Favorable(String id, Product product, int type) {
        this.id = id;
        this.product = product;
        this.type = type;
    }
}
