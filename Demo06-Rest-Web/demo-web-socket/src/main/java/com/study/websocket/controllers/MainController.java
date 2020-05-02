package com.study.websocket.controllers;

import com.study.websocket.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    @RequestMapping(value = "/some-action", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> someAction() {
        messagingTemplate.convertAndSendToUser("UserA", "/queue/notify", new Notification("hello"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
