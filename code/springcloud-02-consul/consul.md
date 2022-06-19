

### 启动
进入到安装目录中，cmd下执行：
.\consul agent -dev
> 执行以上命令，只能在本地访问consul，如果需要外部访问，还需添加选项：-client 0.0.0.0 -ui
> 开发模式下，不会持久化任何数据
> localhost:8500


### 快速入门
consul已启动
#### 1.创建consul-provider、consul-consumer模块，并使用RestTemplate完成远程调用
模块内容与之前Eureka对应的服务提供方和消费方一致，内容参考：Eureka-2.使用RestTemplate完成远程调用（pom.xml和application.yml是需要重新定义的）


#### 2.将Provider注册到consul中（application.yml）


#### 3.consul-consumer通过consul访问consul-provider
