package com.janita.mq.two;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by Janita on 2017-03-21 13:11
 */
public class MQConsumer {

    public static void main(String[] args){

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("TwoConsumer");

        try {

            /**
             * 订阅指定PushTopic下任何tags
             */
            consumer.subscribe("PushTopic","*");
            consumer.subscribe("PullTopic","*");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            consumer.registerMessageListener(new MessageListenerConcurrently() {
                        @Override
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext context) {

//                            Message msg = msgList.get(0);
//                            System.out.println("***消费第一条消息****"+msg.toString());

                            MessageExt messageExt = msgList.get(0);
                            System.out.println("******* msg :\t " + new String(messageExt.getBody()));

                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
