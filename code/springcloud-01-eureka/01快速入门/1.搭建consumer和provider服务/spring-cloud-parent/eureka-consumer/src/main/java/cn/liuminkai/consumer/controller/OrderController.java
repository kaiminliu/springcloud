package cn.liuminkai.consumer.controller;

import cn.liuminkai.consumer.pojo.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        // 使用restTemplate调用
        return null;
    }
}
