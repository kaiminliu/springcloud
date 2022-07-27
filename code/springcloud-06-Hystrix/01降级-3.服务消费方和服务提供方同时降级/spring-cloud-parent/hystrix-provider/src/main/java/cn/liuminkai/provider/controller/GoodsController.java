package cn.liuminkai.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    // hystrix使用：2.指定降级方法
    @HystrixCommand(
            fallbackMethod = "findOne_fallback",
            // hystrix使用：5. 配置超时时间，详细name和value可以查看HystrixCommandProperties.java的构造器
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            }
    )
    public Goods findOne(@PathVariable("id") int id) throws InterruptedException {
        Goods one = goodsService.findOne(id);

        // hystrix使用：4.测试：模拟降级环境
        // 4.1 异常
        //int i = 1/0;
        // 4.2 超时
        Thread.sleep(3000);

        // 将服务端口添加到返回对象中
        one.setTitle(one.getTitle() + ":" + port);
        return one;
    }


    // hystrix使用：1.定义降级方法
    public Goods findOne_fallback(int id) {
        Goods one = goodsService.findOne(id);
        one.setTitle("在服务提供方被降级，降级原因可能是 逻辑执行超时 或 逻辑执行异常");
        return one;
    }
}

