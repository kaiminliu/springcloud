package cn.liuminkai.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
// 新版本含本版本，可以不用添加该注解
@EnableEurekaClient
// discoveryClient使用：2.激活DiscoveryClient对象 （新版本，含当前版本可以不用添加该注解）
@EnableDiscoveryClient
public class ConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}
