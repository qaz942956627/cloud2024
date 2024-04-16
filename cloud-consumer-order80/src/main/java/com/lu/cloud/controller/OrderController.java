package com.lu.cloud.controller;

import com.lu.cloud.entities.PayDTO;
import com.lu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 小卢
 */
@RestController
public class OrderController {
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add", payDTO, ResultData.class);
    }

    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    public String getInfoByConsul() {
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/info", String.class);
    }


}