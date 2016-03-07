package test.zhb;

import com.zhb.entity.Bill;
import com.zhb.entity.Product;
import com.zhb.service.BusinessOperation;
import com.zhb.service.WorkView;
import com.zhb.util.UtilTool;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by ton on 16-3-6.
 */
public class PrintTest {
    private String originalData = "['ITEM000001','ITEM000001','ITEM000001','ITEM000002-5','ITEM000003-2']";

    @Test
    public void testPrintReceipt(){
        BusinessOperation businessOperation = new BusinessOperation();

        //切分原始数据
        Map<String,BigDecimal> dataMap =  UtilTool.splitOriginalDataToMap(originalData);

        //生成产品-数量 map
        Map<Product,BigDecimal> productBigDecimalMap = businessOperation.toProductCountMap(dataMap);

        Bill bill = businessOperation.produceBills(productBigDecimalMap,1);

        WorkView workView = new WorkView();

        workView.printReceipt(bill);
    }
}
