性能要比eureka、consul好
nacos既可以作为注册中心，也可以作为配置中心

### 启动
进入到安装目录下bin目录，双击即可
windows系统下：startup.cmd
linux系统下：startup.sh
> http://localhost:8848/nacos/
> 账号：nacos 密码：nacos

![](nacos/image-20220619233959430.png)

### 快速入门
#### 1.创建nacos-provider、nacos-consumer模块，并使用RestTemplate完成远程调用
模块内容与之前Eureka对应的服务提供方和消费方一致，内容参考：Eureka-01快速入门-2.使用RestTemplate完成远程调用（pom.xml和application.yml是需要重新定义的）

(1)复制spring-cloud-parent模块至nacos-01快速入门

(2)修改子模块名中的eureka为nacos

(3)修改pom.xml
parent模块
```xml
    <modules>
        <module>nacos-provider</module>
        <module>nacos-consumer</module>
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
<artifactId>nacos-consumer</artifactId>
```
provider模块
```xml
<artifactId>nacos-provider</artifactId>
```

(4)测试RestTemplate远程调用


#### 2.引入nacos依赖
除了我们之前引入的web依赖，以及SpringCloud相关依赖，要使用nacos，需要向所有provider、consumer引入nacos相关依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- nacos -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        <version>0.2.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.alibaba.nacos</groupId>
        <artifactId>nacos-client</artifactId>
        <version>1.1.0</version>
    </dependency>
</dependencies>
```