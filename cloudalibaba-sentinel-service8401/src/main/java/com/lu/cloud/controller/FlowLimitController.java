package com.lu.cloud.controller;

import com.lu.cloud.service.FlowLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @Autowired
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() {

        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "------testD";
    }

}