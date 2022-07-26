package cn.liuminkai.consumer.feign;

import cn.liuminkai.consumer.pojo.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * feign声明式接口。发起远程调用的。
 *
 String url = "http://HYSTRIX-PROVIDER/goods/findOne/"+id;
 Goods goods = restTemplate.getForObject(url, Goods.class);
 *
 * 1. 定义接口
 * 2. 接口上添加注解 @FeignClient,设置value属性为 服务提供者的 应用名称
 * 3. 编写调用接口，接口的声明规则 和 提供方接口保持一致。
 * 4. 注入该接口对象，调用接口方法完成远程调用
 *
 */
// openFeign使用：1. 定义接口 2. 接口上添加注解
@FeignClient(value = "HYSTRIX-PROVIDER")
public interface GoodsFeignClient {

    // openFeign使用：3. 编写调用接口，接口的声明规则 和 提供方接口保持一致。
    @GetMapping("/goods/{id}")
    Goods findOne(@PathVariable("id") int id);
}
