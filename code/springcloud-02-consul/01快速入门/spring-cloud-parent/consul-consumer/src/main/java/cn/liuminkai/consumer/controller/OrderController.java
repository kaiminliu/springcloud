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

    // discoveryClient使用：1.注入Bean
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        // discoveryClient使用：3.调用方法
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-provider");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance provider = instances.get(0);
        String host = provider.getHost();
        int port = provider.getPort();
        String url = "http://"+ host +":"+ port +"/goods/"+id;
        System.out.println("consul: url = " + url);
        // 使用restTemplate调用
        // restTemplate使用：3.调用方法 localhost:9001为服务提供方ip和端口
        return restTemplate.getForObject(url, Goods.class);
    }
}
