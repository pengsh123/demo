package com.example.demo.aop;

import com.example.util.EmailSend;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by pengsh on 09/08 10:24
 */
@Component
@Slf4j
@Aspect
public class ExceptionAop {

    @Around("execution(* com.example.demo.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        try {
            Object[] args = pj.getArgs();
            return pj.proceed();
        } catch (Exception e) {
            String exceptionStr = getExceptionStr(e);
            log.error(exceptionStr);
            EmailSend.send("异常",exceptionStr);
            return "服务器异常";
        }
    }

    private String getExceptionStr(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
