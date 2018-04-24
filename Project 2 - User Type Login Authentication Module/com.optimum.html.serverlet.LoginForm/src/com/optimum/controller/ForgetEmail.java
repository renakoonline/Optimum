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

public class ForgetEmail {

	public static void main(User refUser) {
		
		String toUser = refUser.getEmail();	//Recipient email
		final String fromSender = "optimum.batch5@gmail.com"; 	//Sender email
	    final String password = "Optimum2018";	//Sender email password
		final String smtp = "smtp.theoptimum.net";	//SMTP Server
		System.out.println(refUser.getEmail());
		
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
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromSender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			message.setSubject("Hi! Your password has been reset!");
			message.setText("Dear " +refUser.getFname() +", your password has been reset. The new password is " +refUser.getTempass());
			/*StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Dear "+refUser.getFname()+",\n\n");
			stringBuilder.append("Your password has been reset as requested.");
			stringBuilder.append("The Login ID for your account is the same as your email.");
			stringBuilder.append("Your password is " +refUser.getTempass()+", You are required to change it upon logging into our system");
			stringBuilder.append("\n\n");
			stringBuilder.append("With warmest regards,\n");
			stringBuilder.append("The friendly MailBot");*/
			
			Transport.send(message);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
