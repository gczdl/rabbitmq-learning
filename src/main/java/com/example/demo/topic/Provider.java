package com.example.demo.topic;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 18:11
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topic","topic");

        //动态路由key
        String routekey = "user.save.fjie";
        //发布消息
        channel.basicPublish("topic",routekey,null,("这是路由中的动态订阅模型,route key: ["+routekey+"]").getBytes());

        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
