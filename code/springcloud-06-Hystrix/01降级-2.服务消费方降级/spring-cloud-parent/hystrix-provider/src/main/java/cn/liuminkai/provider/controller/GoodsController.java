package cn.liuminkai.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.liuminkai.provider.pojo.Goods;
import cn.liuminkai.provider.service.GoodsService;

//@SuppressWarnings("ALL") // 这个不知为啥会添加，我记得我没有添加过，所以我就注释了，如果有问题就解开
@RestController
@RequestMapping
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public String index() {
        return "eureka-provider goods";
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/goods/{id}")
    public Goods findOne(@PathVariable("id") int id) throws InterruptedException {
        Goods one = goodsService.findOne(id);
        int i = 3/0;
        //Thread.sleep(5000);
        // 将服务端口添加到返回对象中
        one.setTitle(one.getTitle() + ":" + port);
        return one;
    }
}

