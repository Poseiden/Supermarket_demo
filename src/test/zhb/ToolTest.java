package test.zhb;
import com.zhb.util.UtilTool;
import org.junit.Test;

import java.util.List;

/**
 * Created by ton on 16-3-3.
 */
public class ToolTest {

    @Test
    public void test(){
        String originalData = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";

        List<String> list = UtilTool.splitOriginalData(originalData);
        for(String _str:list){
            System.out.println(_str);
        }
    }
}
