package com.janita.mq.producer.controller;

import com.janita.mq.producer.MqProducer;
import com.janita.mq.producer.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Janita on 2017-04-26 10:21
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private MqProducer producer;

    @GetMapping("/{userId}")
    public User createUser(@PathVariable String userId){
        User user = new User();
        user.setUserId(userId);
        user.setUsername("HHHHH");
        //发送消息
        producer.sendMsg("有人查询用户 ： "+userId +"名字 ：" +user.getUsername());
        return user;
    }
}
