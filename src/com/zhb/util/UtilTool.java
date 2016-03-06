package com.zhb.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ton on 16-3-3.
 * 拆分原始数据工具类
 */
public class UtilTool {

    /**
     * 分割原始传入的字符串
     * @param originalData 原始字符串
     * @return ‘商品条形码-数量’ 的字符串集合
     */
    public static List<String> splitOriginalDataToList(String originalData){
        //先把首尾的【】去掉，之后以，号分割开
        String[] productData = originalData.substring(1,originalData.length()-1).split(",");
        List<String> productStr = new ArrayList<String>();
        for(String _pro : productData){
            //将每个字符串的单引号去掉并且加入集合
            productStr.add(_pro.substring(1, _pro.length()-1));
        }

        return productStr;
    }

    /**
     * 分割原始传入的字符串
     * @param originalData
     * @return key为商品条形码，value为商品数量
     */
    public static Map<String,BigDecimal> splitOriginalDataToMap(String originalData){
        String[] productData = originalData.substring(1,originalData.length()-1).split(",");
        Map<String,BigDecimal> countMap = new HashMap<String,BigDecimal>();
        for(String str:productData){
            String[] data = str.substring(1, str.length()-1).split("-");    //若有-，那么第一个元素是条形码，第二个元素是数量

            if(countMap.containsKey(data[0])){
                countMap.put(data[0],countMap.get(data[0]).add((data.length==1?new BigDecimal(1):new BigDecimal(data[1]))));
            }else{
                countMap.put(data[0], data.length == 1 ? new BigDecimal(1) : new BigDecimal(data[1]));
            }
        }

        return countMap;
    }
}
