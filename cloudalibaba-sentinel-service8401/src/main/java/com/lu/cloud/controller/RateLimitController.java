package com.lu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zzyy
 * @create 2023-05-30 16:13
 */
@RestController
@Slf4j
public class RateLimitController {
    @GetMapping("/rateLimit/byUrl")
    public String byUrl() {
        return "按rest地址限流测试OK";
    }

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handleException")
    public Map<String,Object> byResource() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "按资源名称SentinelResource限流测试OK");
        return map;
    }

    public Map<String, Object> handleException(BlockException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "服务不可用@SentinelResource启动");
        return map;
    }
}