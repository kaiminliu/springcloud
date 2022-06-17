坏境搭建部分可参考 01Eureka快速入门，这里直接复制 Eureka-01快速入门-5 的代码
## 搭建高可用Eureka
### EurekaServer高可用
#### 1.准备两个Eureka Server
创建eureka-server模块的两个副本，分别取名为eureka-server1（port:8761），eureka-server2（port|:8762）
##### 修改spring-cloud-parent pom.xml
```xml
<!-- 添加如下内容 -->
<modules>
    <module>eureka-server1</module>
    <module>eureka-server2</module>
</modules>
```

##### 修改eureka-server1 pom.xml
```xml
<artifactId>eureka-server1</artifactId>
```
##### 修改eureka-server2 pom.xml
```xml
<artifactId>eureka-server2</artifactId>
```

#### 2.分别进行配置，相互注册
##### 修改host文件
因为两个模块在同一台主机上，搭建高可用时，我们不能在这两个模块上使用同一个主机名，所以需要将在 hosts文件中配置 ip映射，添加内容如下：
127.0.0.1 eureka-server1
127.0.0.1 eureka-server2
// 测试一下 hostname用任意名字能否访问成功

##### eureka-server1、eureka-server2相互注册
###### eureka-server1 application.yml
```yaml
# Eureka Server Port
server:
  port: 8761

# Eureka Server Config
eureka:
  instance:
    hostname: eureka-server1
  client:
    service-url:
      defaultZone: http://eureka-server2:8762/eureka # server1注册到server2
    fetch-registry: true # 是否拿去url
    register-with-eureka: true # 是否注册url

spring:
  application:
    name: eureka-server-ha # 集群的应用名需一致

```

###### eureka-server2 application.yml
```yaml
# Eureka Server Port
server:
  port: 8762

# Eureka Server Config
eureka:
  instance:
    hostname: eureka-server2
  client:
    service-url:
      defaultZone: http://eureka-server1:8761/eureka # server2注册到server1
    fetch-registry: true # 是否拿去url
    register-with-eureka: true # 是否注册url

spring:
  application:
    name: eureka-server-ha # 集群的应用名需一致

```


#### 3.Eureka Client 分别注册到这两个 Eureka Server中
eureka-consumer、eureka-provider都需要注册这两个server
```yaml
# application.yml
eureka:
  service-url:
    defualtZone: http://eureka-server1:8761/eureka,http://eureka-server2:8762/eureka # Eureka Server URL，根据实际情况编写

```

#### 4.启动服务
依次启动eureka-server1、eureka-server2、eureka-consumer、eureka-provider

检查使用consumer调用provider服务是否正常运行

检查关闭eureka-server1后，consumer调用provider服务是否正常运行