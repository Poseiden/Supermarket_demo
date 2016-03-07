package com.zhb.service;

import com.zhb.entity.Bill;
import com.zhb.entity.Record;

import java.math.BigDecimal;
import java.util.ArrayList;
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

        List<Record> favorableRecords = bill.getFavorableRecords(); //此账单中参与优惠的消费记录
        List<Record> type0Favorable = new ArrayList<Record>();  //优惠类型为买二赠一

        for(Record _record:favorableRecords){
            if(_record.getFavorable().getType() == 0){
                type0Favorable.add(_record);
            }
        }

        if(type0Favorable.size()!=0){
            System.out.println("-------------------------");
            System.out.println("买二赠一商品:");
            for(Record _record:type0Favorable){
                System.out.println(String.format("名称：%s，数量：%s%s",_record.getProduct().getName(),_record.getCount().divideToIntegralValue(new BigDecimal("3")),_record.getProduct().getUnit()));
            }
        }

        System.out.println("--------------------------");
        System.out.println(String.format("总计：%s(元)",bill.getTotalPrice().setScale(2)));
        System.out.print(bill.getFavorableRecords().size() == 0 ? "" : (String.format("节省：%s(元)\n",bill.getOriginalPrice().subtract(bill.getTotalPrice()).setScale(2))));
        System.out.println("**********************");
    }
}
