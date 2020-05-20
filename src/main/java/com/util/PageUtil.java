package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页数据打包
 * Create by HP on 2020/5/20
 * 游魂
 */
public class PageUtil {
    public static Map<String, Object> pack(Object total, Object obj){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("count", total);
        map.put("data", obj);
        return map;
    }
}
