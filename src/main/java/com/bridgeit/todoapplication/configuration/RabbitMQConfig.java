package com.bridgeit.todoapplication.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;

/**
 * <p>
 * <b>Class is for configuring the RabbitMQ.</b>
 * </p>
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 19-07-2018
 */
@Configuration
public class RabbitMQConfig {

	@Value("${jsa.rabbitmq.exchange}")
	String exchange;

	@Value("${jsa.rabbitmq.queue}")
	String queueName;

	@Value("${jsa.rabbitmq.routingkey}")
	private String routingkey;

	/**
	 * <p>
	 * Construct a new queue, given a name and durability flag. The queue is
	 * non-exclusive and non auto-delete.
	 * </p>
	 * 
	 * @return Queue Object
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	/**
	 * <p>
	 * Simple container collecting information to describe a direct exchange. Used
	 * in conjunction with administrative operations
	 * </p>
	 * 
	 * @return DirectExchange Object
	 */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	/**
	 * <p>
	 * Simple container collecting information to describe a binding. Takes String
	 * destination and exchange names as arguments to facilitate wiring using code
	 * based configuration. Can be used in conjunction with AmqpAdmin, or created
	 * via a BindingBuilder
	 * </p>
	 * 
	 * @param queue
	 * @param exchange
	 * @return Binding
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	/**
	 * <p>
	 * Construct with an internal ObjectMapper instance and trusted packed to all
	 * (*)
	 * </p>
	 * @return MessageConverter
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * <p>A RabbitListenerContainerFactory implementation to build a regular SimpleMessageListenerContainer</p>
	 * @param connectionFactory
	 * @param configurer
	 * @return factory
	 */
	@Bean
	public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}

	/**
	 * <p>Function is to return Amqp Template.</p>
	 * @param connectionFactory
	 * @return rabbitTemplate
	 */
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate((ConnectionFactory) connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
