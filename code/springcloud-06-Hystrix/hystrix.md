熔断器：为了保护微服务不受侵害

级联失败（雪崩：一个服务失败，导致整个链路服务都失败），A=>B=>C（c出问题了，B就会一直尝试调用C，直到资源耗尽，A也是）

功能：
1.隔离
    1.1 线程池隔离
        如果不隔离，当A先后调用B、C、D（BCD是不同服务），全部的线程都给了B，那么C、D就得不到调用的机会
        每个提供方，消费方为其提供对应的线程池
    1.2 信号量隔离
        每个提供方，消费方都为其提供对应的线程数
2.降级
    服务失败（异常或超时）后，提供友好提示
    1.能访问到服务器，当时服务挂了
    2.网络不通畅，没有访问到服务器
3.熔断
    A调用C，C里面又调用了很多服务，而且这些服务很多都报错，那么就会降级，A调用C就一直报错，就会启动熔断机制
    把C所有的服务都拒绝掉，不管是否能够访问，拒绝之后，C的压力就减少了，可能一会之后，C就能够恢复。
    熔断器在C恢复后，A调用C也会正常调用
4.限流


### 环境搭建

#### 1、完成模块复制
拷贝 “spring-cloud-05-feign 01快速入门”下spring-cloud-parent 到 “spring-cloud-06-hystrix” 降级 或 熔断下

#### 2、feign-consumer，feign-provider模块重命名为hystrix前缀


#### 3、修改pom.xml和application.yml
##### 修改module的名字
spring-cloud-parent的pom.xml
```xml

    <modules>
        <module>hystrix-provider</module>
        <module>hystrix-consumer</module>
        <module>eureka-server</module>
    </modules>

```

consumer模块（port:9002）pom.xml
```xml
<artifactId>hystrix-consumer</artifactId>
```

provider模块（port:9001）pom.xml
```xml
<artifactId>hystrix-provider</artifactId>
```

##### hystrix-consumer，hystrix-provider修改应用名
consumer模块（port:9002）application.yml
```yaml
spring:
  application:
    name: hystrix-consumer
```

provider模块（port:9001）application.yml
```yaml
spring:
  application:
    name: hystrix-provider 
```

#### 4、不用引入hystrix依赖，因为spring-cloud-starter-openfeign已经包含了


#### 5、可选，修改服务消费方，OrderController.java，调用url的服务名
FEIGN-PROVIDER 改为 HYSTRIX-PROVIDER

#### 6、修改服务消费方，GoodsFeignClient.java 注解上的服务名
feign-provider 改为 hystrix-provider



### 降级

#### 服务提供方降级
服务异常或调用超时，返回默认数据
1.引入hystrix依赖
3.定义降级方法
    # 方法的返回值需要和原方法一致
    # 方法的参数需要与原方法一样
    # 方法名要改
4.原接口方法上使用@HystrixCommand注解配置降级方法
    fallbackMethod 指定降级后调用的方法
    commandProperties {HystrixProperty} 配置属性 属性name HystrixCommandProperties的构造器中
        
2.在启动类上开启Hystrix功能, @EnableC

测试
1.制造一个异常，或超时环境
2.请求结果
如果业务时间超过1s，我们该如何解决，commandProperties中配置即可（我不太懂ribbon的超时和hystrix超时）


#### 服务消费方降级
可以使用服务提供方降级的逻辑实现，但是feign中集成了hystrix，它可以简化hystrix降级的处理逻辑
0.开启feign对hystrix的支持
feign:
    hystrix:
        enabled:true
1.定义feign接口实现类，复写方法，即降级方法
加上@Component注解
2.在@FeignClient注解中使用fallback属性设置降级处理类

测试





### 熔断（机制原理要重新看视频）
Hystrix熔断机制，用于监控微服务调用情况，当失败的情况达到预定的阈值（5秒失败20次），会打开断路器，拒绝所有请求，直到服务恢复正常为止

测试（代码看给的）

### 熔断监控

Hystrix 提供不了 Hystrix-dashboard功能，用于实时监控微服务运行状态

Turbine工具聚合监控

#### 搭建Turbine工具搭建
二、修改被监控模块
导入依赖，在低版本不用导入
