1.简化远程调用
2.客户端负载均衡
3.ribbon是集成在eureka中的
4.负载均衡策略


负载均衡，服务提供方只有一个模块，可以这样，先使用9000启动，再使用9001启动，那么就有两个服务了
但是启动另一个时会停止之前的服务，所以需要做如下配置

### 快速入门
#### 1.环境搭建

因为ribbon是集成在eureka中的，引入eureka依赖后，无需导入ribbon的依赖，所以本次demo都将基于eureka展开。

![](ribbon/image-20220621083752731.png)

拷贝 “Eureka-01快速入门-5.consumer服务通过EurekaServer获取url访问provider” 内容到 “Ribbon-01快速入门”，暂时无需对配置进行修改。



#### 2.在定义RestTemplate对应Bean时，除了@Bean注解，还添加一个注解：@LoadBalanced
```java
// eureka-consumer
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

```
> 使用@LoadBalanced以后，就无法使用 ip:port 方式访问服务提供方，因为ribbon会认为 ip:port 是一个instance，这个instance在EurekaServer是不存在的，进而会报错


#### 3.在使用RestTemplate调用方法时，使用 服务提供方应用名 替换 host:port
```java
public class OrderController {
    // ...
    
    /**
     * ribbon使用：2.不再从discoveryClient中动态获取ip:port，直接在RestTemplate方法调用中使用 服务应用名 替换 ip:port (简化远程调用)
     */
    @GetMapping("/goods/ribbon/{id}")
    public Goods findGoodsByIdUseRibbon(@PathVariable("id") int id) {

        // ribbon简化远程调用
        String url = "http://EUREKA-PROVIDER/goods/" + id;

        // 使用restTemplate调用
        // restTemplate使用：3.调用方法
        return restTemplate.getForObject(url, Goods.class);
    }
}

    // ...
```



#### 4.简化远程调用测试
依次启动服务 eureka-server、eureka-provider、eureka-consumer

确保eureka-server控制台中服务是完整的

确保eureka-consumer能通过ribbon简化过的接口访问到eureka-provider


#### 5.客户端负载均衡测试
要想完成负载均衡的测试，我们需要对 eureka-provider 进行一些改造，让我们能够清晰的看到负载均衡所表现的效果。首先，要有负载均衡效果
，必须有多个提供方服务（相同应用），而这里只有一个服务，那么将采用 修改端口 启动多个服务的策略，完成多个提供方服务的启动。其次，为了让
客户端调用有更好的显示效果，会将 服务的端口 添加到 返回的对象中。

##### (1) 为GoodsController.java findOne添加端口到返回的对象中
```java
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/goods/{id}")
    public Goods findOne(@PathVariable("id") int id) {
        Goods one = goodsService.findOne(id);
        // 将服务端口添加到返回对象中
        one.setTitle(one.getTitle() + ":" + port);
        return one;
    }
}
```

##### (2) 多次修改端口启动provider服务
依次启动 eureka-server（8761）、eureka-provider（port:9011）、eureka-provider（port:9012）、eureka-provider（port:9013）、eureka-consumer（9002）

> 注意: 启动另一个时会停止之前启动的服务，所以需要做如下配置
>
> ![](ribbon/image-20220621094704184.png)

经过多次调用，发现三个provider轮询提供服务，下面取前4次结果：

![](ribbon/image-20220621095521296.png)



