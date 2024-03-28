package com.zeng.studing.system.component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Slf4j
@Component
public class RocketMqTool {
    @Resource
    private RocketMQTemplate template;

    public void test() {
        send("test", "HELLO ROCKET MQ.");
    }

    // 发送 JSON
    public void send(String tag, String json) {
        Message<String> msg = MessageBuilder.withPayload(json).setHeader(
                MessageConst.PROPERTY_TAGS, tag
        ).build();
        template.send(msg);
    }


}
