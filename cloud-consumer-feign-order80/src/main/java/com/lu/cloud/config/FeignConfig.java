package com.lu.cloud.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer myRetryer() {
        return Retryer.NEVER_RETRY;
        // 最大请求除数为3(1+2),初始间隔时间为100ms,重试最大间隔时间为1s
//        return new Retryer.Default(100, 1, 3);
    }
}
