package com.msb.dongbao.portal.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.msb.dongbao.portal.web.controller.api.CheckUtils;
import com.msb.dongbao.portal.web.util.BodyReaderHttpServletRequestWrapper;
import com.msb.dongbao.portal.web.util.HttpParamUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;

/**
 * @ClassName SignAuthFilter
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/17 14:09
 * @Version 1.0
 **/
@Component
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter生效");
        //签名的验证
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletRequest request = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取参数,统一get和post,不管url,还是body
        SortedMap<String, String> allParams = HttpParamUtils.getAllParams(request);
        //校验签名
        boolean b = CheckUtils.checkSign(allParams);
        System.out.println(b);
        if (b){
            filterChain.doFilter(request,response);
        }else {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject param = new JSONObject();
            param.put("coed",-1);
            param.put("message","签名错了");
            writer.append(param.toJSONString());
        }

    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
