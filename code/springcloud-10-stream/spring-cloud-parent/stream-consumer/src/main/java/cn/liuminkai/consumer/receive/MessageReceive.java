package cn.liuminkai.consumer.receive;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

// 1.创建消息接收类，绑定通道 input，并转载到spring容器中
@Component
@EnableBinding(Sink.class)
public class MessageReceive {

    // 2.监听 来自通道 input 的消息
    @StreamListener(Sink.INPUT)
    public void receive(Message message) {
        System.out.println("message = " + message.getPayload());
    }
}
