package com.bridgelabz.fundoo.configure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoo.rabbitmq.MessageListener;



@Configuration
public class RobbitMqConfig {
 
 public static final String ROUTING_KEY = "my.queue.key";
 
 public static final String Exchange = "my_queue_exchange";
 
 @Bean
 Queue queue() {
  return new Queue(ROUTING_KEY, true);
 }


 @Bean
 TopicExchange exchange() {
  return new TopicExchange("Exchange");
 }


 @Bean
 Binding binding(Queue queue, TopicExchange exchange) {
  return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
 }
 @Bean
 public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
 return new Jackson2JsonMessageConverter();
 }
 
  
 @Bean
 SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
 MessageListenerAdapter listenerAdapter) {
  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
  container.setConnectionFactory(connectionFactory);
  container.setQueueNames(ROUTING_KEY);
  container.setMessageListener(listenerAdapter);
  return container;
 }
 
 

 @Bean
 MessageListenerAdapter myQueueListener(MessageListener listener) {
  return new MessageListenerAdapter(listener, "onMessage");
 }
 
}