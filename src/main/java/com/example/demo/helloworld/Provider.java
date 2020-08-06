package com.example.demo.helloworld;


import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zdl
 * @create 2020/8/6 11:41
 */
public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory=new ConnectionFactory();
//        connectionFactory.setHost("172.18.1.53");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
//
//        //获取链接对象
//        Connection connection=connectionFactory.newConnection();

        Connection connection = RabbitMqUtils.getConnection();

        //获取链接通道
        Channel channel = connection.createChannel();

        //通道绑定对应的消息队列
        //参数1，队列名称，不存在队列将自动创建队列
        //参数2，用来定义队列是否启动持久化
        //参数3，exclusive 是否独占队列,只能被当前通道绑定
        //参数4，autoDelete，是否在消费完成后并且消费者断开连接将自动删除队列，被消费者消费完，队列没有其他元素就删除队列
        //参数5，额外参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1：交换机名称 参数2：队列名称 参数3：传递消息额外设置 参数4：消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());

        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
