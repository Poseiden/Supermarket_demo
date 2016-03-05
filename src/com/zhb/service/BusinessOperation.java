package com.zhb.service;

import com.zhb.entity.Bill;
import com.zhb.entity.Favorable;
import com.zhb.entity.Product;
import com.zhb.entity.Record;

import java.util.*;

/**
 * Created by ton on 16-3-3.
 * 业务操作类
 */
public class BusinessOperation {
    private List<Product> products = null;  //全部商品
//    private List<Favorable> favorableList = null;  //  参与优惠的商品
    private List<String> errorProducts = null; //错误条形码信息，包括未找到对应条形码的商品等

    {
        //加载全部商品
        products = new ArrayList<Product>();
        //加载全部优惠信息
//        favorableList = new ArrayList<Favorable>();
        Favorable twoAndOne = new Favorable("0",0); //买二赠一
        Favorable discount = new Favorable("1",1);  //打95折
        //初始化错误条形码集合
        errorProducts = new ArrayList<String>();

        List<Favorable> favorableList = new ArrayList<Favorable>();

        favorableList.add(twoAndOne);   //买二赠一优惠
        Product cola = new Product("ITEM000001","可口可乐",3.00,favorableList);

        favorableList = new ArrayList<Favorable>();
        favorableList.add(twoAndOne);
        favorableList.add(discount);    //买二赠一优惠 且 打95折
        Product bird = new Product("ITEM000002","羽毛球",1.00,favorableList);

        favorableList = new ArrayList<Favorable>();
        favorableList.add(discount);    //打95折优惠
        Product apple = new Product("ITEM000003","苹果",5.50,favorableList);

        products.add(cola);
        products.add(bird);
        products.add(apple);
        
        
//        List<Product> twoAndOne = new ArrayList<Product>(); //买二赠一的商品集合
//        twoAndOne.add(cola);
//        Favorable favorable1 = new Favorable("0",twoAndOne,0);  //优惠1
//
//        List<Product> discount = new ArrayList<Product>();  //  打折商品集合
//        discount.add(apple);
//        Favorable favorable2 = new Favorable("1",discount,1);   //优惠2
//
//        favorableList.add(favorable1);
//        favorableList.add(favorable2);
        
    }

    /**
     * 将 条形码-次数 根据业务对象转换成 商品-个数
     * @param stringCountMap    条形码-个数
     * @return 商品-个数 Map
     */
    public Map<Product,Integer> toProductCountMap(Map<String,Integer> stringCountMap){
        Map<Product,Integer> productCountMap = new HashMap<Product,Integer>();

        for(Map.Entry<String,Integer> _entry:stringCountMap.entrySet()){
            for(int i = 0;i<products.size();i++){
                if(_entry.getKey().equals(products.get(i).getBarCode())){  //如果找到，那么放入map，且接着查找下一个商品
                    productCountMap.put(products.get(i),_entry.getValue());
                    break;
                }
                if(i == products.size()-1){
                    errorProducts.add(_entry.getKey()); //将未找到商品信息的条形码放入错误集合中
                }
            }
        }

        return productCountMap;
    }

    /**
     * 结算
     * @param productCountMap  商品-个数 map
     * @param type 当两种优惠冲突时选择的优惠类型 0：买二赠一  1：打95折
     */
    public Bill produceBills(Map<Product,Integer> productCountMap,Integer type){
        Bill bill = new Bill();
        List<Record> records = new ArrayList<Record>();
        Double totalPriceForBill = 0.00;    //账单总价
        Double originalPriceForBill = 0.00; //账单原价

        //遍历购买的 商品-次数 集合
        Favorable _favorable = null; //商品采用的优惠
        for(Map.Entry<Product,Integer> _entry: productCountMap.entrySet()){
            List<Favorable> _favorableList = _entry.getKey().getFavorable();
            Product _product = _entry.getKey();    //价格
            Integer _count = _entry.getValue(); //数量

            if(_favorableList.size() < 1){  //不参与优惠活动
                _favorable = null;
            }else if (_favorableList.size() == 1){ //只参与一项优惠活动
                _favorable = _favorableList.get(0);
            }else if(_favorableList.size() > 1){    //两项活动都参与
                _favorable = _favorableList.get(type);
            }
            records.add(produceRecords(_favorable,_product,_count));
        }

        //计算此账单的总价和原价
        for(Record _record:records){
            totalPriceForBill += _record.getTotalPrice();
            originalPriceForBill += _record.getOriginalPrice();
        }

        bill.setId(new StringBuilder(new Random().nextInt(10000)+1+"").append(System.currentTimeMillis()+"").toString());
        bill.setRecords(records);
        bill.setTotalPrice(totalPriceForBill);
        bill.setOriginalPrice(originalPriceForBill);

        return bill;
    }

    private Record produceRecords(Favorable favorable,Product product,Integer count){
        Double sum = 0.0;
        Record record = new Record();

        //计算价钱
        if(favorable != null) {
            if (favorable.getType() == 0) {   //买二赠一
                sum += (((count / 3) * 2 * product.getPrice()) + (count % 3) * product.getPrice());
            } else {  //打95折
                sum += (count * product.getPrice() * 0.95);
            }
        }else{
            sum += (product.getPrice() * count);
        }

        record.setId(new StringBuilder(new Random().nextInt(10000)+1+"").append(System.currentTimeMillis()+"").toString());
        record.setProduct(product);
        record.setCount(count);
        record.setFavorable(favorable);
        record.setTotalPrice(sum);  //总价
        record.setOriginalPrice(product.getPrice() * count);   //原价

        return record;
    }
}
