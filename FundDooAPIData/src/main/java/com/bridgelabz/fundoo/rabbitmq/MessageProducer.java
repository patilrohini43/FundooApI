package com.bridgelabz.fundoo.rabbitmq;

import java.util.Date;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.configure.RobbitMqConfig;


@Component
public class MessageProducer {
 
 @Autowired
  private RabbitTemplate rabbitTemplate;
 
 public void sendMessage(RabbitMqBody messageBody) {
  System.out.println(new Date());
 
  rabbitTemplate.convertAndSend(RobbitMqConfig.Exchange,RobbitMqConfig.ROUTING_KEY, messageBody);

     System.out.println("Is listener returned ::: "+rabbitTemplate.isReturnListener());
     System.out.println(new Date());
 }


}