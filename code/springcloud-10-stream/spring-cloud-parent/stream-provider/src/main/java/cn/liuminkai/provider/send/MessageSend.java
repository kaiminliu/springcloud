package cn.liuminkai.provider.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

// 1 创建消息发送类，绑定 通道 "output"，并转载到spring容器中
@Component
@EnableBinding(Source.class)
public class MessageSend {

    // 2 注入 消息通道 "output"
    @Autowired
    public MessageChannel output;

    // 3 编写消息发送方法
    public void send() {
        output.send(MessageBuilder.withPayload("hello stream ...").build());
    }
}
