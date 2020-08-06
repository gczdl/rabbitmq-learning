package com.example.demo.helloworld;

import com.example.demo.utils.RabbitMqUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zdl
 * @create 2020/8/6 13:40
 */
public class Consumer {


    public static void main(String[] args) throws IOException, TimeoutException{
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
        //参数2，用来定义队列是否启动持久化(仅队列，不包含消息)
        //参数3，exclusive 是否独占队列
        //参数4，autoDelete，是否在消费完成后自动删除队列
        //参数5，额外参数
        channel.queueDeclare("hello",false,false,false,null);


        //消费消息
        //参数1：消费队列名称
        //参数2：开启消息自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override  //最后一个参数；消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body)+"=====================");
            }
        });

//        channel.close();
//        connection.close();
    }

}
