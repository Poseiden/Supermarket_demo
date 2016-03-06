package test.zhb;

import com.zhb.entity.Bill;
import com.zhb.entity.Product;
import com.zhb.entity.Record;
import com.zhb.service.BusinessOperation;
import com.zhb.util.UtilTool;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ton on 16-3-5.
 * 业务测试类
 */
public class BusinessTest {
    private String originalData = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000002','ITEM000002','ITEM000002','ITEM000003-4']";
//private String originalData = "['ITEM000002','ITEM000002','ITEM000002']";
    private BusinessOperation businessOperation = null;

    @Before
    public void beforeTest(){
        businessOperation = new BusinessOperation();
    }

    public void testToProductCountMap(){
        Map<String,BigDecimal> countMap = UtilTool.splitOriginalDataToMap(originalData);

        Map<Product,BigDecimal> productIntegerMap = businessOperation.toProductCountMap(countMap);

        for(Map.Entry<Product,BigDecimal> _entry:productIntegerMap.entrySet()){
            System.out.println(_entry.getKey().getBarCode()+"----------"+_entry.getValue());
        }
        System.out.println("---------------------------------------------------");
        for(String _barCode: businessOperation.errorProducts){
            System.out.println(_barCode);
        }
        System.out.println("----------------------------------------------------");
        for(Product _product: businessOperation.products){
            System.out.println(_product.getBarCode()+"-----------");
        }
    }

    @Test
    public void produceBills(){
        Map<String,BigDecimal> countMap = UtilTool.splitOriginalDataToMap(originalData);
        Map<Product,BigDecimal> productIntegerMap = businessOperation.toProductCountMap(countMap);
        Bill bill = businessOperation.produceBills(productIntegerMap,0);
        List<Record> records = bill.getRecords();

        for(Record _record: records){
            System.out.println(String.format("名称：%s，数量：%s，总价格：%s，原价格：%s",_record.getProduct().getName(), _record.getCount(),_record.getTotalPrice(),_record.getOriginalPrice()));
        }
        System.out.println(String.format("总价：%s",bill.getTotalPrice()));
//        System.out.println(String.format("id:%s,名称：%s,记录数：%s,现价：%s,原价：%s",bill.getId(),bill.getRecords().get(0).getProduct().getName(), bill.getRecords().size(),bill.getTotalPrice(),bill.getOriginalPrice()));

//        System.out.println(new BigDecimal(3).multiply(new BigDecimal("0.95")));
//        System.out.println(new BigDecimal(3).add(new BigDecimal(12.00)));
    }

}
