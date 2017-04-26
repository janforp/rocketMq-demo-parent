package com.janita.mq.consumer.one;

import com.janita.mq.consumer.one.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Janita on 2017-04-26 10:36
 */
@SpringBootApplication
@Import(value = {SpringUtil.class})
public class ConsumerOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOneApplication.class, args);

        MqConsumerOne consumerOne = SpringUtil.getBean(MqConsumerOne.class);
        consumerOne.subscribeMsg();
    }
    
}
