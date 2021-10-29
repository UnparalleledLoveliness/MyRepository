package com.example.demo.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

public class Send {

  @Autowired
  RabbitTemplate rabbitTemplate;

  private final static String QUEUE_NAME = "simple_queue";

  public static void main(String[] argv) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    Channel channel = connection.createChannel();
    /**
     * 参数明细
     * 1、queue 队列名称
     * 2、durable 是否持久化，如果持久化，mq重启后队列还在
     * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
     * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
     * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
     */
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    // 向指定的队列中发送消息
    /**
     * 参数明细：
     * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
     * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
     * 3、props，消息的属性
     * 4、body，消息内容
     */
    for(int i=0;i<10;i++)
    {
      channel.basicPublish("",QUEUE_NAME,null,String.valueOf(i).getBytes());
    }
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    //关闭通道和连接(资源关闭最好用try-catch-finally语句处理)
    channel.close();
    connection.close();
  }
}