package com.optimum.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.optimum.pojo.User;

public class RegEmail {

	public static void main(User refUser) {	
		
		String toUser = User.getEmail();	//Recipient email
		final String fromSender = "optimum.batch5@gmail.com"; 	//Sender email
	    final String password = "Optimum2018";	//Sender email password
		final String smtp = "smtp.theoptimum.net";	//SMTP Server
		
		//Define host properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		properties.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		properties.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		properties.put("mail.smtp.port", "465"); // SMTP Port
		
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromSender, password);
			}
		};
		
		 Session session = Session.getDefaultInstance(properties, auth);
		
		try {
			//Define mailing address
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromSender));
			
			//Define recipient
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			
			//Subject Header
			message.setSubject("Hi! Your account with <assignment> has been created!");
			
			//Email body
			
			message.setText("Dear " +refUser.getFname() +", thank you for registering with us."
					+ "Your initial password is " +refUser.getPassword() +". You are required to change your password"
							+ " upon logging in. Thank you.");
			/*StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Dear "+User.getFname()+",\n\n");
			stringBuilder.append("Thank you for registering with us!");
			stringBuilder.append("The Login ID for your account is the same as your email.");
			stringBuilder.append("Your initial password is " +User.getPassword()+", You are required to change it upon logging into our system");
			stringBuilder.append("\n\n");
			stringBuilder.append("With warmest regards,\n");
			stringBuilder.append("The friendly MailBot");*/
			
			//Send mail
			Transport.send(message);
			System.out.println("An email has been sent to "+User.getFname()+".");
			System.out.println("Please take note that their email address is :"+User.getEmail()+".");
		
		}	catch(Exception e) {
			
		}
		
	}

}
