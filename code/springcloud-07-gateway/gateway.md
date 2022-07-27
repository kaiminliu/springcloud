存在的问题

copy hystrix
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


