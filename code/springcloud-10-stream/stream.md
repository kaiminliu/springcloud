简化消息中间件的使用
要看ppt，Stream架构
### 消息生产者
1.创建消息生产者模块，引入依赖

2.编写配置，定义builder，和bindings
stream.binders:
    名字可以随便取（自定义绑定器名称）：
        type: 绑定器类型（rabbit）
stream.bindings:
    output： channel
    destination: exchange


3.定义消息发送业务类。添加@EnableBinding(Sourse.class)，注入MessageChannel output，完成消息发送



### 消息消费者

1.创建消息消费者模块，引入依赖

2.编写配置，定义builder，和bindings


3.定义消息接受业务类。添加@EnableBinding(Sink.class)，注入MessageChannel input，完成消息接受
