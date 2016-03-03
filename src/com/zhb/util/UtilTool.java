package com.zhb.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ton on 16-3-3.
 * 拆分字符串工具类
 */
public class UtilTool {

    /**
     * 分割原始传入的字符串
     * @param originalData 原始字符串
     * @return ‘商品条形码-数量’ 的字符串集合
     */
    public static List<String> splitOriginalData(String originalData){
        //先把首尾的【】去掉，之后以，号分割开
        String[] productData = originalData.substring(1,originalData.length()-1).split(",");
        List<String> productStr = new ArrayList<String>();
        for(String _pro : productData){
            //将每个字符串的单引号去掉并且加入集合
            productStr.add(_pro.substring(1, _pro.length()-1));
        }

        return productStr;
    }
}
