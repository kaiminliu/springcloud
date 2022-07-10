分布式配置中心
分布式多环境
每个微服务如果都有属于自己的配置文件，我们要改他们相同的配置，那么所有的服务都需要更改，打包测试
### 快速入门
1.使用git创建远程仓库，上传配置文件
2.搭建config server 模块
3.导入config-server依赖
启动类，启勇config功能
4.编写配置（客户端和服务端），设置Gitee远程仓库地址
bootstrap.yaml
服务端的端口8888，客户端保持
应用名
config.server.git.uri
label: master 主分支
5.测试访问远程配置文件

#### 客户端刷新
服务端刷新，客户端要配置后才能够刷新

1.引入actuator依赖
2.获取配置信息类上，添加@RefreshScope注解
3.添加配置（啥用）
bootstrap.yaml
    management.endoints.web.exposure.include:fresh
4.使用curl工具发送post请求
curl -X POST /actuator/refresh

使用这个只能一次刷新一个微服务，如何刷新多个，那么就需要结合bus使用


本地配置杂用

### 集成eureka（不清楚，理清楚）
server注册到eureka
client从eureka中访问