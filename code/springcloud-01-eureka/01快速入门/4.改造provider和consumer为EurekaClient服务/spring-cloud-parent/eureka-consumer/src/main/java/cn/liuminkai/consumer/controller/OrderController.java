package cn.liuminkai.consumer.controller;

import cn.liuminkai.consumer.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("order")
public class OrderController {

    // restTemplate使用：2.注入Bean
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        // 使用restTemplate调用
        // restTemplate使用：3.调用方法 localhost:9001为服务提供方ip和端口
        return restTemplate.getForObject("http://localhost:9001/goods/"+id, Goods.class);
    }
}
