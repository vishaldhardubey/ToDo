package com.bridgeit.todoapplication.utilservice;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgeit.todoapplication.userservice.model.Email;

	/**
	 * Description : Class is to send the mail to corresponding mail id.
	 * @author 	: Vishal Dhar Dubey
	 * @version : 1.0
	 * @since   : 14-07-2018
	 */
	@Component
	public class MailService {
		// Static values for the admin email and password.
		final static String admin = "vishalcapgemini1807@gmail.com";
		final static String passwordAdmin = "vish@1993";
		
		@Autowired
		private JavaMailSender javaMailSender;
		
		/**
		 * Function is used to send email to the emailID provided by user.
		 * @param email Object
		 * @return boolean
		 */
		public boolean send(Email email) {
			System.out.println("Inside Mail Service");
			
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper=new MimeMessageHelper(message);
				
				messageHelper.setTo(email.getTo());
				messageHelper.setText(email.getText());
				messageHelper.setSubject(email.getSubject());
				
				System.out.println("################Inside try block for MimeMessage");
				javaMailSender.send(message);
				System.out.println("Sent mail");
				return true;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
}
