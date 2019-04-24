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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.bridgelabz.fundoo.rabbitmq.MessageListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



@Configuration
public class RobbitMqConfig {


	public static final String Exchange = "fundoo.direct.exchange";
	public static final String USER_QUEUE="fundoo.user.queue";
	public static final String NOTE_QUEUE="fundoo.note.queue";
	public static final String ROUTING_KEY1 = "userRoutingKey";
	public static final String ROUTING_KEY2 = "noteRoutingkey";





	@Bean
	TopicExchange exchange() {
		return new TopicExchange(Exchange);
	}

	@Bean(name="userQueue")
	Queue userQueue() {
		return new Queue(USER_QUEUE, true);
	}

	@Bean
	Binding binding(Queue userQueue, TopicExchange exchange) {
		return BindingBuilder.bind(userQueue).to(exchange).with(ROUTING_KEY1);
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/*
	 * @Bean(name="userListner") SimpleMessageListenerContainer
	 * container(ConnectionFactory connectionFactory, MessageListenerAdapter
	 * listenerAdapter) { SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory);
	 * container.setQueueNames(USER_QUEUE);
	 * container.setMessageListener(listenerAdapter); return container; }
	 */


	// @Bean
	// @Primary
	// MessageListenerAdapter myQueueListener(MessageListener listener) {
	//  return new MessageListenerAdapter(listener, "onMessage");
	// }

	//for elasticSearch
	
	

	@Bean(name="noteQueue")
	Queue noteQueue() {
		return new Queue(NOTE_QUEUE, true);
	}

	@Bean
	Binding binding1(Queue noteQueue, TopicExchange exchange) {
		return BindingBuilder.bind(noteQueue).to(exchange).with(ROUTING_KEY2);
	}

	/*
	 * @Bean(name="noteListner") SimpleMessageListenerContainer
	 * container1(ConnectionFactory connectionFactory, MessageListenerAdapter
	 * listenerAdapter) { SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory);
	 * container.setQueueNames(NOTE_QUEUE);
	 * container.setMessageListener(listenerAdapter); return container; }
	 */

	/*
	 * @Bean MessageListenerAdapter myQueueListener1(MessageListener listener) {
	 * return new MessageListenerAdapter(listener, "operation"); }
	 */
}