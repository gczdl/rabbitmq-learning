package com.example.demo.direct;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 17:13
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String routingKey="error";

        channel.basicPublish("logs_direct",routingKey,null,("这是directm模型发布的基于route key：["+routingKey+"] 发送的消息").getBytes());

        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
