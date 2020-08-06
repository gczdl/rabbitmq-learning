package com.example.demo.direct;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 17:22
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();

        Channel channel = connection.createChannel();
        String exchangeName="logs_direct";
        channel.exchangeDeclare(exchangeName,"direct");

        //获取临时队列
        String queue = channel.queueDeclare().getQueue();

        //基于route_key帮定队列和交换机
        channel.queueBind(queue,exchangeName,"error");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });


    }
}
