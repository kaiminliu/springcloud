package cn.liuminkai.consumer.controller;

import cn.liuminkai.consumer.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    // restTemplate使用：2.注入Bean
    @Autowired
    private RestTemplate restTemplate;

    // discoveryClient使用：1.注入Bean 注入的是 org.springframework.cloud.client.discovery 包下的
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        // discoveryClient使用：3.调用方法 动态获取提供方ip和端口 EUREKA-PROVIDER为提供方应用名
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-PROVIDER");

        // EurekaServer中是否有EUREKA-PROVIDER
        if (instances == null || instances.size() == 0) {
            // 没有
            return null;
        }

        ServiceInstance provider = instances.get(0);
        String host = provider.getHost();
        int port = provider.getPort();

        String url = "http://" + host + ":" + port + "/goods/" + id;
        System.out.println("url = " + url);

        // 使用restTemplate调用
        // restTemplate使用：3.调用方法
        return restTemplate.getForObject(url, Goods.class);
    }
}
