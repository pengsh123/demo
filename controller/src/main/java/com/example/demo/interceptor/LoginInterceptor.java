package com.example.demo.interceptor;

import com.example.bean.annotation.MustLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * Created by pengsh on 09/08 9:44
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod && handler != null) {
            Annotation annotation = ((HandlerMethod) handler).getMethod().getAnnotation(MustLogin.class);
            if (annotation != null) {
                String token = request.getHeader("token");
                if (token != null) {
                    log.info("登录校验通过");
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
