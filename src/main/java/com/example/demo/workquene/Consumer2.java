package com.example.demo.workquene;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 15:31
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException{
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws
                    IOException {
                System.out.println("消费者-2："+new String(body));
                //参数1：确认队列中具体那个消息，参数2：
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
