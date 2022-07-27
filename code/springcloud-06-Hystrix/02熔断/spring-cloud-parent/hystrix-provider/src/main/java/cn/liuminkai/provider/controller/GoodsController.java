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
    @HystrixCommand(
            fallbackMethod = "findOne_fallback",
            commandProperties = {
                    //设置Hystrix的超时时间，默认1s
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
                    //监控时间 默认5000 毫秒
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
                    //失败次数。默认20次
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "20"),
                    //失败率 默认50%
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")
            }
    )
    public Goods findOne(@PathVariable("id") int id) {
        Goods one = goodsService.findOne(id);

        // hystrix熔断测试环境模拟，id为1时，产生异常
        if(id == 1) {
            int i = 1/0;
        }

        // 将服务端口添加到返回对象中
        one.setTitle(one.getTitle() + ":" + port);
        return one;
    }

    public Goods findOne_fallback(int id) {
        Goods one = goodsService.findOne(id);
        one.setTitle("在服务提供方被降级，降级原因可能是 逻辑执行超时 或 逻辑执行异常");
        return one;
    }
}

