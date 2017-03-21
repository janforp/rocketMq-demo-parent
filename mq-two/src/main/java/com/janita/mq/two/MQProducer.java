package com.janita.mq.two;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Created by Janita on 2017-03-21 13:11
 */
public class MQProducer {

    public static void main(String[] args){
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("TwoProducer");
        try {
            producer.start();
            int i = 0;

            while (true){
                i++;
                Message msg = new Message("PushTopic","push","1",("Just for yyp" + i).getBytes());
                SendResult result = producer.send(msg);

                msg = new Message("PullTopic","pull","1","Just for test!".getBytes());
                result = producer.send(msg);
                System.out.println("******* 发送消息的 id : "+result.getMsgId() + "  ***** 发送消息的状态 result : "+result.getSendStatus());

                if (i % 10 == 0){
                    Thread.sleep(5000);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
