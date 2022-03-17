package com.msb.dongbao.portal.web.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName HttpParamUtils
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/17 14:18
 * @Version 1.0
 **/
public class HttpParamUtils {
    public static SortedMap<String, String> getAllParams(HttpServletRequest request) throws IOException {
        //获取url上的参数
        Map<String, String> urlParams = getUrlParams(request);
        //获取body上的参数
        Map<String, String> bodyParams = getBodyParams(request);

        //总的参数的map
        SortedMap<String,String> allMap = new TreeMap<>();
        for (Map.Entry<String, String> stringStringEntry : urlParams.entrySet()) {
            allMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        for (Map.Entry<String, String> stringStringEntry : bodyParams.entrySet()) {
            allMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        return allMap;
    }

    private static Map<String, String> getBodyParams(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        //转map
        Map map = JSONObject.parseObject(sb.toString(), Map.class);
        return map;
    }

    private static Map<String, String> getUrlParams(HttpServletRequest request) throws UnsupportedEncodingException {
        String queryParam = "";
        //编码和解码
        if (!StringUtils.isBlank(request.getQueryString())){
            queryParam = URLDecoder.decode(request.getQueryString(), "utf-8");
        }
        Map<String, String> result = new HashMap<>();
        String[] split = queryParam.split("&");
        for (String s : split) {
            int i = s.indexOf("=");
            result.put(s.substring(0, i), s.substring(i + 1));

        }
        return result;
    }

    public static void main(String[] args) {
        String abc = "jincheng=123";
        int i = abc.indexOf("=");
        System.out.println(i);
        System.out.println(abc.substring(0, i));
        System.out.println(abc.substring(i + 1));
    }
}
