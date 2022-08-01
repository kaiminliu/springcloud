package cn.liuminkai.provider.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 4 使用 消息通道 "output" 发送消息
@RestController
@RequestMapping("send")
public class SendController {

    @Autowired
    private MessageSend messageSend;

    @GetMapping
    public String send(){
        messageSend.send();
        return "success";
    }
}
