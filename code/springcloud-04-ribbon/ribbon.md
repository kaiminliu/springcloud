1.简化远程调用
2.客户端负载均衡
3.ribbon是集成在eureka中的
4.负载均衡策略


负载均衡，服务提供方只有一个模块，可以这样，先使用9000启动，再使用9001启动，那么就有两个服务了
但是启动另一个时会停止之前的服务，所以需要做如下配置

### 快速入门
#### 1.环境搭建

因为ribbon是集成在eureka中的，引入eureka依赖后，无需导入ribbon的依赖，所以本次demo都将基于eureka展开。




#### 2.在定义RestTemplate对应Bean时，除了@Bean注解，还添加一个注解：@LoadBalanced

#### 3.在使用RestTemplate调用方法时，使用 服务提供方应用名 替换 host:port

#### 4.负载均衡测试


