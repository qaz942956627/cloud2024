package com.lu.cloud.controller;

import com.lu.cloud.apis.PayFeignApi;
import com.lu.cloud.entities.PayDTO;
import com.lu.cloud.resp.ResultData;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author 小卢
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        log.info(LocalDateTime.now().toString());
        ResultData resultData = payFeignApi.getPayInfo(id);
        return resultData;
    }

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimiterFallback")
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myRatelimit(id);
    }

    public String myRateLimiterFallback(Integer id, Throwable t) {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}