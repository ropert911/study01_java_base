package com.study.mq.rabbit.template.scenes.entityTransfer;

import com.study.mq.rabbit.template.model.Msg;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 */
@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Msg msg) {
        System.out.println("msg send : " + msg.toString());
        this.rabbitTemplate.convertAndSend("msgQueue", msg);
    }
}
