package com.example.demo.workquene;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author zdl
 * @create 2020/8/6 14:52
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection= RabbitMqUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i=0;i<20;i++){
            channel.basicPublish("","work",null,(i+"hollo work quene").getBytes());
        }
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
