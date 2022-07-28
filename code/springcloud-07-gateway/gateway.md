存在的问题

copy hystrix
### 环境搭建

#### 1、完成模块复制
拷贝 “spring-cloud-06-hystrix 02熔断”下spring-cloud-parent 到 “spring-cloud-07-gateway 01快速入门”  下

#### 2、hystrix-consumer，hystrix-provider模块重命名为gateway前缀

#### 3、修改pom.xml和application.yml
##### 修改module的名字
spring-cloud-parent的pom.xml
```xml

    <modules>
        <module>gateway-provider</module>
        <module>gateway-consumer</module>
        <module>eureka-server</module>
    </modules>

```

consumer模块（port:9002）pom.xml
```xml
<artifactId>gateway-consumer</artifactId>
```

provider模块（port:9001）pom.xml
```xml
<artifactId>gateway-provider</artifactId>
```

##### hystrix-consumer，hystrix-provider修改应用名
consumer模块（port:9002）application.yml
```yaml
spring:
  application:
    name: gateway-consumer
```

provider模块（port:9001）application.yml
```yaml
spring:
  application:
    name: gateway-provider 
```

#### 5、可选，修改服务消费方，OrderController.java，调用url的服务名
HYSTRIX-PROVIDER 改为 GATEWAY-PROVIDER

#### 6、修改服务消费方，GoodsFeignClient.java 注解上的服务名
hystrix-provider 改为 gateway-provider

### 快熟入门
0.环境搭建
直接复制 spring-cloud-06-hystrix
1.搭建网关模块
2.映入依赖
3.编写启动类
4.编写配置文件
    路由规则
        setRoutes
            RouteDefinition
                属性id 
                属性predicates 条件
                属性filter 
                属性uri 服务提供方访问路径
                属性order
5.启动测试

### 静态路由配置
uri中 ip和端口固定 ，也就是上面快速入门的例子


### 动态路由配置
让gateway成为eureka客户端，从eureka中获取provider的url

1.映入eureka依赖
2.加入eureka相关配置
3.更改uri的格式
http://服务ip:port ==> lb://服务应用名

### 微服务名称配置
区分网关微服务名称和原始接口微服务名称
discovery.locator.enabled: 
lower-case-service-id 配置瞎写


### 网关过滤器配置
过滤器描述
pre post
GatewayFilter 局部过滤器，单个路由
GlabalFilter 全局过滤器，所有路由

#### 局部过滤器
已经内置了很多，有资料
之前的filters属性

验证时也要修改降级方法的参数

#### 全局过滤器
不需要配置
系统初始化时加载
自定义全局过滤器：
1、定义类实现GlobalFilter和Ordered接口
2.复写方法
3.完成逻辑处理


