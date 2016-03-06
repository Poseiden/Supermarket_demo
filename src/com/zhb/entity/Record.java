package com.zhb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ton on 16-3-3.
 * 记录类 （账单上的每条记录）
 */
public class Record implements Serializable {
    private static final long serialVersionUID = -416505332892171646L;

    private String id;
    private Product product;
    private BigDecimal count;
    private Favorable favorable;    //最终选择的优惠策略
    private BigDecimal totalPrice;  //优惠后的价格
    private BigDecimal originalPrice;   //原价格

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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Favorable getFavorable() {
        return favorable;
    }

    public void setFavorable(Favorable favorable) {
        this.favorable = favorable;
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

    /******Constructor*****/
    public Record(Product product, BigDecimal count, Favorable favorable, BigDecimal totalPrice, BigDecimal originalPrice) {
        this.product = product;
        this.count = count;
        this.favorable = favorable;
        this.totalPrice = totalPrice;
        this.originalPrice = originalPrice;
    }

    public Record() {
    }

    /*****Method*****/

    @Override
    /**
     * 重写toString方法，按格式输出
     */
    public String toString() {
        String content = "";
        if(this.getFavorable().getType() == 0) {
            content = String.format("名称：%s,数量：%s(%s),单价：%s(元)，小计：%s(元)", this.getProduct().getName(), this.getCount(),this.getProduct().getUnit() ,this.getProduct().getPrice(),this.getTotalPrice());
        }else if(this.getFavorable().getType() == 1){
            content = String.format("名称：%s,数量：%s(%s),单价：%s(元),小计：%s(元),节省：%s(元)", this.getProduct().getName(), this.getCount(), this.getProduct().getUnit(),this.getProduct().getPrice(),this.getTotalPrice(),this.getOriginalPrice().subtract(this.getTotalPrice()));
        }
        return content;
    }
}
