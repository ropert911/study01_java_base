package com.study.mq.rabbit.template.controller;

import com.study.mq.rabbit.template.scenes.topicExChange.TopicSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangxc
 * @date 2019/9/20
 */
@RestController
@RequestMapping("/rabbit5")
@Api(value = "topic exchange类型rabbitmq",description = "")
public class TopicExchangeController {

    @Autowired
    private TopicSender topicSender;

    @RequestMapping(value = "/topic",method = RequestMethod.GET)
    public void topicTest() {
        topicSender.send();
    }
}
