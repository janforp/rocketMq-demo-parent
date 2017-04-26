package com.janita.mq.consumer.one.controller;

import com.janita.mq.consumer.one.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Janita on 2017-04-26 10:37
 */
@RestController
@RequestMapping("/consumerOne")
public class UserConsumerController {

    @PostMapping
    public User consumerUser(){
        User user = new User();
        user.setUserId("1");
        user.setUsername("2");
        return user;
    }
}
