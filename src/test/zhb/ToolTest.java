package test.zhb;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import com.zhb.util.UtilTool;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by ton on 16-3-3.
 */
public class ToolTest {
    private String originalData = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005','ITEM000003-4']";

    public void testReturnList(){
        List<String> list = UtilTool.splitOriginalDataToList(originalData);
        for(String _str:list){
            System.out.println(_str);
        }
    }

    @Test
    public void testReturnMap(){
        Map<String,Integer> countMap = UtilTool.splitOriginalDataToMap(originalData);
        for(Map.Entry<String,Integer> _entry:countMap.entrySet()){
            System.out.println(_entry.getKey()+","+_entry.getValue());
        }
    }

}
