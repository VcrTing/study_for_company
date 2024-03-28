package com.zeng.studing.mq;

import org.apache.rocketmq.common.filter.ExpressionType;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.lang.model.type.ExecutableType;

@RocketMQMessageListener(
        topic = "test",
        consumerGroup = "springboot_consumer_group",
        selectorExpression = "test"
        // selectorType = ExpressionType.TAG
)
@Component
public class MQTestListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String json) {
        System.out.println("ROCKETMQ MSG = " + json);
    }
}
