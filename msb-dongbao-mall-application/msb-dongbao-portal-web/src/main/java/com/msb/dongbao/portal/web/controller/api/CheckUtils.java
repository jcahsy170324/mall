package com.msb.dongbao.portal.web.controller.api;

import com.msb.dongbao.common.util.MD5Util;

import java.util.*;

/**
 * @ClassName CheckUtils
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/16 21:05
 * @Version 1.0
 **/
public class CheckUtils {

    public static String appSecret = "aaa";

    public static String generatorSign(Map<String,Object> map){
        //排序
        Map<String, Object> stringObjectMap = sortMapByKey(map);
        //转格式
        Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : entries) {
            sb.append(entry.getKey()+","+entry.getValue()).append("#");
        }
        //组装secret 参数后面添加secret
        sb.append("secret").append(appSecret);
        //生成签名

        return MD5Util.md5(sb.toString());
    }

    public static Map<String,Object> sortMapByKey(Map<String,Object> map){
        Map<String,Object> sortMap = new TreeMap<>(new MyMapComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    static class MyMapComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
//        map.put("az",1);
//        map.put("z2",2);
//        map.put("bas",3);
//        Map<String, Object> stringObjectMap = sortMapByKey(map);
//        System.out.println(stringObjectMap);
        map.put("appId",1);
        map.put("name",2);
        String s = generatorSign(map);
        //a3337ee5d708e41ac38bd0d88561b95f
        System.out.println(s);
    }
}
