package com.example.rabbitmq.simpledemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: adminisrator
 * @date: 2019/3/6
 * @description:
 */
public class MessageConsumer {
    private static String RABBITMQ_URL = "192.168.0.11";
    private static int RABBITMQ_PORT = 5672;
    private static String USER_NAME = "admin";
    private static String PASSWORD = "admin";
    private static String QUEUE_NAME = "direct";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接和通道
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RABBITMQ_URL);
        connectionFactory.setPort(RABBITMQ_PORT);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            if ("hello world".equals(message)){
                System.out.println(" [x] Received '" + message + "'");
            }else {
                System.out.println("消息不正确！");
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
