package com.login.application;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.login.controller.User;

public class RegEmail {

	public static void main() {
		// TODO Auto-generated method stub
		
		
		String toUser = User.getEmail();	//Recipient email
		String fromSender = "Admin@theoptimum.net";		//Sender email
		String smtp = "smtp.theoptimum.net";	//SMTP Server
		
		//Define host properties
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", smtp);
		Session session = Session.getDefaultInstance(properties);
		
		try {
			//Define mailing address
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromSender));
			
			//Define recipient
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			
			//Subject Header
			message.setSubject("Hi! Your account with <assignment> has been created!");
			
			//Email body
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Dear "+User.getName()+",\n\n");
			stringBuilder.append("Thank you for registering with us!");
			stringBuilder.append("The Login ID for your account is the same as your email.");
			stringBuilder.append("Your initial password is " +User.getPass()+", You are required to change it upon logging into our system");
			stringBuilder.append("\n\n");
			stringBuilder.append("With warmest regards,\n");
			stringBuilder.append("The friendly MailBot");
			
			
			//Send mail
			Transport.send(message);
			System.out.println("An email has been sent to "+User.getName()+".");
			System.out.println("Please take note that their email address is :"+User.getEmail()+".");
		
		}	catch(Exception e) {
			
		}
		
	}

}
