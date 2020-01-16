package com.study.rest.swagger.mq.controller;

import com.study.rest.swagger.mq.model.Msg;
import com.study.rest.swagger.mq.scenes.entityTransfer.MsgSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangxc
 * @date 2019/9/20
 */
@RestController
@RequestMapping("/rabbit4")
@Api(value = "实体传输",description = "实体传输")
public class EntityController {

    @Autowired
    private MsgSender msgSender;

    @ApiOperation("实体类传输测试")
    @RequestMapping(value = "/msg",method = RequestMethod.POST)
    public void userTest(@RequestBody Msg msg) {
        for(int i=0;i<=100;i++){
            msgSender.send(msg);
        }

    }
}
