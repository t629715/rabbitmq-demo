package com.example.rabbitmq.simpledemo;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: adminisrator
 * @date: 2019/3/6
 * @description:
 */
public class MessageProducer {
    private static String RABBITMQ_URL = "192.168.0.11";
    private static int RABBITMQ_PORT = 5672;
    private static String USER_NAME = "admin";
    private static String PASSWORD = "admin";
    private static String DIRECT_QUEUE = "direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_URL);
        connectionFactory.setPort(RABBITMQ_PORT);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("DIRECT_QUEUE",false,false,false,null);
        String message = "hello";
        channel.basicPublish("",DIRECT_QUEUE,null,message.getBytes("UTF-8"));
        System.out.println("发送消息："+message);
        channel.close();
        connection.close();
    }
}
