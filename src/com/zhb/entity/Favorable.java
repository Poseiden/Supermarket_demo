package com.zhb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ton on 16-3-3.
 * 优惠类
 * Note : 在真实的业务环境中，商品与优惠本人认为应该是多对多的关系，但是由于本代码仅是一版简单的demo，并没有数据库的存在，
 *      故在此实体的设计上删繁就简，舍弃了商品集合这个属性
 */
public class Favorable implements Serializable {
    private static final long serialVersionUID = -326684716269722458L;

    private String id;
    private int type;   //优惠类型，0表示买二赠一，1表示打折
    /*********getter and setter***********/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /******Constructor*****/
    public Favorable(String id, int type) {
        this.id = id;
        this.type = type;
    }

    public Favorable() {
    }
}
