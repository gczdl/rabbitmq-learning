package com.example.demo.utils;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author zdl
 * @create 2020/8/6 14:06
 */
public class RabbitMqUtils {


    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("172.18.1.53");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    //定义提供连接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel,Connection conn){
        try {
            if(channel!=null){
                channel.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
