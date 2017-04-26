package com.janita.mq.consumer.one.service;

import org.springframework.stereotype.Service;

/**
 * Created by Janita on 2017-04-26 10:46
 */
@Service
public class ConsumerService {

    public void consumer(String msg){
        System.out.println("******* " + msg);
    }
}
