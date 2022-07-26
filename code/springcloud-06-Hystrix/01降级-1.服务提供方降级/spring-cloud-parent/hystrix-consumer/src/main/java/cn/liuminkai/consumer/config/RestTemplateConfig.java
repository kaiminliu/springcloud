package cn.liuminkai.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// restTemplate使用：1.定义Bean
@Configuration
public class RestTemplateConfig {

    // ribbon使用：1.RestTemplate定义添加 @LoadBalanced 注解
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
