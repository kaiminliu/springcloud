package cn.liuminkai.consumer.feign;

import cn.liuminkai.consumer.pojo.Goods;
import org.springframework.stereotype.Component;

@Component
public class GoodsFeignClientFallback implements GoodsFeignClient {
    
    // 重写的方法就是降级方法
    @Override
    public Goods findOne(int id) {
        return new Goods(id, "在服务消费方被降级，原因是建立连接超时", 0.0, 0);
    }
}