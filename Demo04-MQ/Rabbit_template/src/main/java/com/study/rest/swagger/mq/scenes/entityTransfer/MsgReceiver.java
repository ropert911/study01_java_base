package com.study.rest.swagger.mq.scenes.entityTransfer;

import com.study.rest.swagger.mq.model.Msg;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sk-qianxiao
 */
@Component
@RabbitListener(queues = "msgQueue")
public class MsgReceiver {

    @RabbitHandler
    public void process(Msg msg) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("user receive  : " + "id->"+msg.getId()+", content->"+msg.getContent());
    }

}
