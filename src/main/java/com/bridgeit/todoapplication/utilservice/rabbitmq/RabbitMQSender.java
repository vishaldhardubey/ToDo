package com.bridgeit.todoapplication.utilservice.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgeit.todoapplication.userservice.model.Email;
/**
 * Purpose  : Rabbit MQ Receiver class is to send the message.
 * @author 	: Vishal Dhar Dubey
 * @version : 1.0
 */
@Component
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;

	/**
	 * <p>Convert a Java object to an Amqp Message and send it to a specific exchange with a specific routing key</p>
	 * @param email
	 * @return boolean
	 */
	public boolean produceMsg(Email email) {
		amqpTemplate.convertAndSend(exchange, routingKey, email);
		System.out.println("Send email = " + email);
		return true;
	}
}
