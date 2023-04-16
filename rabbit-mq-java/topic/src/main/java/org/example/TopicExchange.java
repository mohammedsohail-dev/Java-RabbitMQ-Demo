package org.example;



import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicExchange {
  /**
   * Declare a Topic Exchange with the name my-topic-exchange.
   *
   * @throws IOException
   * @throws TimeoutException
   */
  public static void declareExchange(String topicexchange) throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    //Create Topic Exchange
    channel.exchangeDeclare(topicexchange, BuiltinExchangeType.TOPIC, true);
    channel.close();
  }

  /**
   * Declare Queues to receive respective interested messages.
   *
   * @throws IOException
   * @throws TimeoutException
   */
  public static void declareQueues(String topic) throws IOException, TimeoutException {
    //Create a channel - do not share the Channel instance
    Channel channel = ConnectionManager.getConnection().createChannel();

    //Create the Queues
    channel.queueDeclare(topic, true, false, false, null);

    channel.close();
  }

  /**
   * Declare Bindings - register interests using routing key patterns.
   *
   * @throws IOException
   * @throws TimeoutException
   */
  public static void declareBindings(String topic,String topicexchange,String routingkey) throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    //Create bindings - (queue, exchange, routingKey) - routingKey != null
    channel.queueBind(topic,topicexchange,routingkey);

    channel.close();
  }

  /**
   * Assign Consumers to each of the Queue.
   *
   * @throws IOException
   * @throws TimeoutException
   */
  public static void subscribeMessage(String topic) throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    channel.basicConsume(topic, true, ((consumerTag, message) -> {
      System.out.println("\n\n======="+topic+"==========");
      System.out.println(consumerTag);
      System.out.println(topic + new String(message.getBody()));
      System.out.println(message.getEnvelope());
    }), consumerTag -> {
      System.out.println(consumerTag);
    });

    
  }

  /**
   * Publish Messages with different routing keys.
   *
   * @throws IOException
   * @throws TimeoutException
   */
  public static void publishMessage(String message,String topicexchange,String routingkey) throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    channel.basicPublish(topicexchange, routingkey , null, message.getBytes());

    
    channel.close();
  }

}
