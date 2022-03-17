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

    public static String generatorSign(Map<String,String> map){
        map.remove("sign");
        //排序
        Map<String, String> stringObjectMap = sortMapByKey(map);
        //转格式
        Set<Map.Entry<String, String>> entries = stringObjectMap.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            sb.append(entry.getKey()+","+entry.getValue()).append("#");
        }
        //组装secret 参数后面添加secret
        sb.append("secret").append(appSecret);
        //生成签名

        return MD5Util.md5(sb.toString());
    }

    public static boolean checkSign(Map<String,String> map){
        String sign = (String) map.get("sign");
        map.remove("sign");
        String s = CheckUtils.generatorSign(map);
        System.out.println(s);
        if (s.equals(sign)){
            return true;
        }else {
            return false;
        }
    }

    public static Map<String,String> sortMapByKey(Map<String,String> map){
        Map<String,String> sortMap = new TreeMap<>(new MyMapComparator());
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
        HashMap<String,String> map = new HashMap<>();
        map.put("appId","1");
        map.put("name","2");
        map.put("he","3");
        //f05c92694d9a99aabeaa9969cc091373
        String s = generatorSign(map);
        System.out.println(s);
    }
}
