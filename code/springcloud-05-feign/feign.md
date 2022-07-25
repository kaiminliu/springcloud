声明式服务调用组件
客户端
不支持SpringMVC的注解，但SpringCloud对其分钟，支持了SpringMVC的注解，也就是OpenFeign

ribbon 虽然简化了eureka的调用，但是我们每次调用都需要写一个url（含服务名），再使用restTemplate调用，调用过程还是比较繁琐，每次都得写一遍url

环境搭建，ribbon 改服务，应用名


环境搭建：，修改eureka-client模块名前缀为feign，和对应模块应用名，修改父模块pom.xml


feign可以进一步简化ribbon+restTemplate，feign是进一步的封装
### 快速入门

#### 1.环境搭建
##### (1) 完成模块复制
拷贝 “springcloud-04-ribbon 01快速入门 2.客户端负载均衡”下spring-cloud-parent 到 “springcloud-05-feign 01快速入门”

##### (2) eureka-consumer，eureka-provider模块重命名为feign前缀

##### (3) 修改pom.xml和application.yml
###### 修改module的名字
spring-cloud-parent的pom.xml
```xml

    <modules>
        <module>feign-provider</module>
        <module>feign-consumer</module>
        <module>eureka-server</module>
    </modules>

```

consumer模块（port:9002）pom.xml
```xml
<artifactId>feign-consumer</artifactId>
```

provider模块（port:9001）pom.xml
```xml
<artifactId>feign-provider</artifactId>
```

###### feign-consumer，feign-provider修改应用名
consumer模块（port:9002）application.yml
```yaml
spring:
  application:
    name: feign-consumer
```

provider模块（port:9001）application.yml
```yaml
spring:
  application:
    name: feign-provider 
```

##### (4) 服务消费方（feign-consumer）引入openfeign依赖
```xml
<dependencies>

    <!--feign-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    
</dependencies>
```


#### 2.启动类上开启Fegin的功能（声明式接口）



#### 3.写一个XxxFeginClient接口
作用：
1.发起远程调用
2.简化ribbon代码

2.1 定义接口
2.2 接口添加注解@FeignClient，属性value=服务提供方应用名
2.3 编写调用接口（接口方法），接口的声明规则和提供方接口保持一致（返回对象类型可以不同）

#### 4 本地controller调用feign接口，完成远程调用（注入爆红，不用管）

### 其他功能
#### 1.超时设置
消费方调用提供方超时时间默认1s（ribbon）

ribbon:
    ConnectTimeout: 1000 # 连接超时时间，ms
    ReadTimeout: 1000 # 逻辑超时时间，ms



#### 2.日志记录
Feign只能记录debug级别的日志信息
logging:
    level:
        需要记录日志的包名: debug

@Bean一个Feign的日志类

BASIC, 记录基本的请求行，响应状态吗数据
HEADERS, 记录基本的请求行，响应状态吗数据，记录响应头信息
FULL,记录完整的请求
Logger.Level level() {
    return Logger.Level.FULL
}

在FeignClient注解上，configuration=FeginLogConfiguration.java开启日志

