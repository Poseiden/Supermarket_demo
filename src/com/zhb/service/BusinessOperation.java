package com.zhb.service;

import com.zhb.entity.Bill;
import com.zhb.entity.Favorable;
import com.zhb.entity.Product;
import com.zhb.entity.Record;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by ton on 16-3-3.
 * 业务操作类
 */
public class BusinessOperation {
    public List<Product> products = null;  //全部商品
    public List<String> errorProducts = null; //错误条形码信息，包括未找到对应条形码的商品等

    {
        //加载全部商品
        products = new ArrayList<Product>();
        Favorable twoAndOne = new Favorable("0",0); //买二赠一
        Favorable discount = new Favorable("1",1);  //打95折
        //初始化错误条形码集合
        errorProducts = new ArrayList<String>();

        List<Favorable> favorableList = new ArrayList<Favorable>();

        favorableList.add(twoAndOne);   //买二赠一优惠
        Product cola = new Product("ITEM000001","可口可乐",new BigDecimal(3.00),favorableList,"瓶");

        favorableList = new ArrayList<Favorable>();
        favorableList.add(twoAndOne);
        favorableList.add(discount);    //买二赠一优惠 且 打95折
        Product bird = new Product("ITEM000002","羽毛球",new BigDecimal(1.00),favorableList,"个");

        favorableList = new ArrayList<Favorable>();
        favorableList.add(discount);    //打95折优惠
        Product apple = new Product("ITEM000003","苹果",new BigDecimal(5.50),favorableList,"斤");

        products.add(cola);
        products.add(bird);
        products.add(apple);
        
    }

    /**
     * 将 条形码-次数 根据业务对象转换成 商品-个数
     * @param stringCountMap    条形码-个数
     * @return 商品-个数 Map
     */
    public Map<Product,BigDecimal> toProductCountMap(Map<String,BigDecimal> stringCountMap){
        Map<Product,BigDecimal> productCountMap = new HashMap<Product,BigDecimal>();

        for(Map.Entry<String,BigDecimal> _entry:stringCountMap.entrySet()){
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
    public Bill produceBills(Map<Product,BigDecimal> productCountMap,Integer type){
        Bill bill = new Bill();
        List<Record> records = new ArrayList<Record>();
        BigDecimal totalPriceForBill = new BigDecimal(0.00);    //账单总价
        BigDecimal originalPriceForBill = new BigDecimal(0.00); //账单原价

        //遍历购买的 商品-次数 集合
        Favorable _favorable = null; //商品采用的优惠
        for(Map.Entry<Product,BigDecimal> _entry: productCountMap.entrySet()){
            List<Favorable> _favorableList = _entry.getKey().getFavorable();
            Product _product = _entry.getKey();    //价格
            BigDecimal _count = _entry.getValue(); //数量

            Record _record = produceRecords(_product, _count, type);
            if(_record.getFavorable() != null){ //如果该条消费记录单参加任一优惠活动，那么添加进此账单集合
                bill.getFavorableRecords().add(_record);
            }

            records.add(_record);
        }

        //计算此账单的总价和原价
        for(Record _record:records){
            totalPriceForBill = totalPriceForBill.add(_record.getTotalPrice());
            originalPriceForBill = originalPriceForBill.add(_record.getOriginalPrice());
        }

        bill.setId(new StringBuilder(new Random().nextInt(10000)+1+"").append(System.currentTimeMillis()+"").toString());
        bill.setRecords(records);
        bill.setTotalPrice(totalPriceForBill);
        bill.setOriginalPrice(originalPriceForBill);

        return bill;
    }

    /**
     * 生成每条消费记录
     * @param product
     * @param count
     * @return
     */
    private Record produceRecords(Product product,BigDecimal count,Integer type){
        List<Favorable> favorableList = product.getFavorable();
        BigDecimal sum = new BigDecimal(0.0);
        Record record = new Record();
        Favorable favorable = null;

        if(favorableList.size() < 1){  //不参与优惠活动
            sum = sum.add((product.getPrice().multiply(count)));
        }else{ //参与优惠活动
            if(favorableList.size() == 1){
                favorable = favorableList.get(0);
            }else{
                favorable = favorableList.get(type);
            }

            if (favorable.getType() == 0) {   //买二赠一
                BigDecimal _sum =(((count.divideToIntegralValue(new BigDecimal(3))).multiply(new BigDecimal(2)).multiply(product.getPrice())).add((count.divideAndRemainder(new BigDecimal("3")))[1].multiply(product.getPrice())));
                sum = sum.add((((count.divideToIntegralValue(new BigDecimal(3))).multiply(new BigDecimal(2)).multiply(product.getPrice())).add((count.divideAndRemainder(new BigDecimal("3")))[1].multiply(product.getPrice()))));
            } else {  //打95折
                sum = sum.add((count.multiply(product.getPrice()).multiply(new BigDecimal("0.95"))));   //不可用double作为BigDecimal的构造源
            }
        }

        record.setId(new StringBuilder(new Random().nextInt(10000)+1+"").append(System.currentTimeMillis()+"").toString());
        record.setProduct(product);
        record.setCount(count);
        record.setFavorable(favorable);
        record.setTotalPrice(sum);  //总价
        record.setOriginalPrice(product.getPrice().multiply(count));   //原价

        return record;
    }
}
