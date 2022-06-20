

### 启动
进入到安装目录中，cmd下执行：
.\consul agent -dev
> 执行以上命令，只能在本地访问consul，如果需要外部访问，还需添加选项：-client 0.0.0.0 -ui
> 开发模式下，不会持久化任何数据
> localhost:8500


### 快速入门
consul已启动
#### 1.创建consul-provider、consul-consumer模块，并使用RestTemplate完成远程调用
模块内容与之前Eureka对应的服务提供方和消费方一致，内容参考：Eureka-01快速入门-2.使用RestTemplate完成远程调用（pom.xml和application.yml是需要重新定义的）

(1)复制spring-cloud-parent模块至consul-01快速入门

(2)修改子模块名中的eureka为consul

(3)修改pom.xml
parent模块
```xml
    <modules>
        <module>consul-provider</module>
        <module>consul-consumer</module>
    </modules>
```
parent模块引入 spring-cloud 依赖
```xml
    <properties>
        <spring-cloud.version>Greenwich.RELEASE</spring-cloud.version>
    </properties>
```
```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
consumer模块
```xml
<artifactId>consul-consumer</artifactId>
```
provider模块
```xml
<artifactId>consul-provider</artifactId>
```

(4)测试RestTemplate远程调用

#### 2.引入Consul依赖
除了我们之前引入的web依赖，以及SpringCloud相关依赖，要使用consul，需要向所有provider、consumer（为什么consumer也需要引入，因为我们不能保证consumer是否也拥有provider的职能）引入consul相关依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--引入后，需要配置完成后，启动才不会报错-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <!-- 使用consul必须引入该依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
```

#### 3.将Provider注册到consul中（application.yml）

##### consul-provider
```yaml

# server port
server:
  port: 9001

spring:
  cloud:
    consul:
      host: localhost # consul 服务端的 ip
      port: 8500 # consul 服务端的端口 默认8500
      discovery:
        service-name: ${spring.application.name} # 当前应用注册到consul的名称
        prefer-ip-address: true # 注册ip

  application:
    name: consul-provider # 应用名称

```

##### consul-consumer
```yaml

# server port
server:
  port: 9002

spring:
  cloud:
    consul:
      host: localhost # consul 服务端的 ip
      port: 8500 # consul 服务端的端口 默认8500

  application:
    name: consul-consumer # 应用名称

```

#### 4.consul-consumer通过consul访问consul-provider
需要动态访问consul-provider，还需要使用到 DiscoveryClient 这个接口，调用获取 ip 和 port 的方法，结合 RestTemplate 完成。
##### 引入DiscoveryClient
引入步骤与 ”Eureka-01快速入门-5.consumer 服务 通过从 Eureka Server 中抓取 provider 地址 完成 远程调用“，只需要修改服务名即可
在服务消费方引入即可
###### (1)注入bean
```java
// consul-consumer OrderController.java
public class OrderController {
    // ...

    // discoveryClient使用：1.注入Bean
    @Autowired
    private DiscoveryClient discoveryClient;

    // ...
}

```

###### (2)激活bean
在启动类上添加
```java
// discoveryClient使用：2.激活Bean
@EnableDiscoveryClient
public class ConsumerApp { 
    // ...
}
```

###### (3)调用方法
```java
public class OrderController {
    // ...
    
    @GetMapping("/goods/{id}")
    public Goods findGoodsById(@PathVariable("id") int id) {
        // discoveryClient使用：3.调用方法
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-provider");
        if (instances == null || instances.size() == 0) {
            return null;
        }
        ServiceInstance provider = instances.get(0);
        String host = provider.getHost();
        int port = provider.getPort();
        String url = "http://"+ host +":"+ port +"/goods/"+id;
        System.out.println("consul: url = " + url);
        // 使用restTemplate调用
        // restTemplate使用：3.调用方法 localhost:9001为服务提供方ip和端口
        return restTemplate.getForObject(url, Goods.class);
    }

    // ...
}
```
##### 启动服务
依次启动consul.exe、consul-provider、consul-consumer
访问 consul 控制台，确保consul-provider注册成功
确保consumer成功调用provider服务