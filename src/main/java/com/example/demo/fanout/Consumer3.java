package com.example.demo.fanout;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 16:29
 */
public class Consumer3 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();

        //绑定交换级和队列
        channel.queueBind(queue,"logs","");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3"+new String(body));
            }
        });

    }

}
