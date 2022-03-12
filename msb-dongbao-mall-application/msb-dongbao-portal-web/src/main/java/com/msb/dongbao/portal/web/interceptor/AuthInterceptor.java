package com.msb.dongbao.portal.web.interceptor;

import com.msb.dongbao.common.base.TokenException;
import com.msb.dongbao.common.base.annotations.TokenCheck;
import com.msb.dongbao.common.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName AuthInterceptor
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/12 18:58
 * @Version 1.0
 **/
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器进入");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(TokenCheck.class)) {
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)){
                throw new LoginException("token 为空");
            }
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required()){
                //校验token
                try {
                    JwtUtil.parseToken(token);
                }catch (Exception e){
                    throw new TokenException("token 异常");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
