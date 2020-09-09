package com.example.demo.controller;

import com.example.bean.annotation.MustLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengsh on 09/08 9:41
 */
@RestController
@Slf4j
public class DemoController {


    @PostMapping("/demo")
    @MustLogin
    public String demo(String key) throws Exception {
        log.info("demo方法执行");
        int a=1/0;
        return "执行完成";
    }
}
