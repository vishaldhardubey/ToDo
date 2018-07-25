package com.bridgeit.todoapplication.utilservice.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bridgeit.todoapplication.userservice.model.Email;
import com.bridgeit.todoapplication.utilservice.MailService;
/**
 * Purpose  : Rabbit MQ Receiver class is to acknowledge the message.
 * @author 	: Vishal Dhar Dubey
 * @version : 1.0
 */
@Component
public class RabbitMQReceiver {
	
	@Autowired
	MailService mailService;
	
	/**
	 * <p>Function is to receive the email object and call send mail function.<p>
	 * @param email
	 */
	@RabbitListener(queues = "${jsa.rabbitmq.queue}")
	public void recievedMessage(Email email) {
		mailService.send(email);
		System.out.println("Recieved Message: " + email);
	}
}
