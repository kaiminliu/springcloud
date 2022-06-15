Eureka 简单入门

1.搭建 Provider  和 Consumer 服务。

2.使用 RestTemplate 完成远程调用。

3.搭建 Eureka Server 服务。

4.改造 Provider  和 Consumer 称为 Eureka Client。

5.Consumer 服务 通过从 Eureka Server 中抓取 Provider 地址 完成 远程调用。

## 1.搭建 Provider  和 Consumer 服务
### 1.1 创建spring-cloud-parent项目
### 1.2 配置parent项目pom.xml
详情参照：资料/1项目搭建/parent-pom.xml
```xml

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.0.RELEASE</version>
        <relativePath/>
    </parent>


    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

```
### 1.3 parent下创建eureka-provider模块
#### (1)配置pom.xml
详情参照：资料/1项目搭建/provider-pom.xml
```xml

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

```
#### (2)编写提供方代码（启动类，entity，dao，service，controller）


> 确保能够通过浏览器访问到接口

### 1.4 parent下创建eureka-consumer模块
#### (1)配置pom.xml
详情参照：资料/1项目搭建/provider-pom.xml
```xml

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

```

#### (2)编写消费方代码（启动类，entity，controller）
> controller暂时不写调用提供方http接口的代码，即restTemplate部分

## 2.使用 RestTemplate 完成远程调用


## 3.搭建 Eureka Server 服务

## 4.改造 Provider  和 Consumer 称为 Eureka Client

## 5.Consumer 服务 通过从 Eureka Server 中抓取 Provider 地址 完成 远程调用
