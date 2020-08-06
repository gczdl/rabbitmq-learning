package com.example.demo.fanout;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 16:12
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明指定交换机 参数1：交换机名称 参数2：交换机类型 fanout：广播，一条消息多个消费者同时消费
        channel.exchangeDeclare("logs","fanout");
        //发布消息
        channel.basicPublish("logs","",null,"hello".getBytes());
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
