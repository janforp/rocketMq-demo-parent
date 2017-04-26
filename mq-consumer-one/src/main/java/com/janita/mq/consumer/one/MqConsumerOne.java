package com.janita.mq.consumer.one;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.janita.mq.consumer.one.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Janita on 2017-04-26 10:39
 */
@Component
public class MqConsumerOne {

    @Value("${rocketMq.base.ip}")
    private String rocketIp;
    @Value("${rocketMq.producerGroup}")
    private String group;
    @Value("${rocketMq.topic}")
    private String topic;
    @Value("${rocketMq.tags}")
    private String tags;
    @Value("${rocketMq.keys}")
    private String keys;

    @Autowired
    private ConsumerService consumerService;

    /**
     * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
     * 但是实际PushConsumer内部是使用长轮询Pull方式从Broker拉消息，然后再回调用户Listener方法<br>
     */
    public void subscribeMsg(){

        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
        consumer.setNamesrvAddr(rocketIp);
        try {
            /**
             * 订阅指定topic下tags分别等于TagA或TagB
             */
            consumer.subscribe(topic,tags+"||"+"otherTag");

        }catch (MQClientException e) {
            e.printStackTrace();
        }
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        /**
         * 注册监听器
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**
             * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                System.out.println("******* 开始消费 *******" + Thread.currentThread().getName() + " Receive New Messages : " + msgs);

                for (MessageExt msg : msgs){
                    String message = new String(msg.getBody());
                    System.out.println("*******消息是 ： " + message);
                    consumerService.consumer(message);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        try {
            consumer.start();
        }catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("------------ Consumer Started .");
    }
}
