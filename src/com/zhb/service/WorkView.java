package com.zhb.service;

import com.zhb.entity.Bill;
import com.zhb.entity.Record;

import java.util.List;

/**
 * Created by ton on 16-3-6.
 */
public class WorkView {

    /**
     * 打印小票
     * @param bill  账单
     */
    public void printReceipt(Bill bill){
        System.out.println("***<没钱赚商店>购物清单***");
        List<Record> records = bill.getRecords();
        for(Record _record:records){
            System.out.println(_record.toString());
        }

        System.out.println("-------------------------");
        System.out.println("买二赠一商品:");

        List<Record> favorableRecords = bill.getFavorableRecords();
        for(Record _record:favorableRecords){
            if(_record.getFavorable().getType() == 0){
                System.out.println(String.format("名称：%s，数量：%s(%s)",_record.getProduct().getName(),_record.getCount(),_record.getProduct().getUnit()));
            }
        }

        System.out.println("--------------------------");
        System.out.println(String.format("总计：%s(元)",bill.getTotalPrice()));
        System.out.println(bill.getFavorableRecords() == null ? null : (String.format("节省：%s(元)",bill.getOriginalPrice().subtract(bill.getTotalPrice()))));
    }
}
