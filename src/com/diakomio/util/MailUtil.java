package com.diakomio.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	private final static String username = "warrantypartners";
	private final static String password = "P@$$w0rd@4321";
	private static String subject = "";
	private static String body = "";
	private static String recipient = "";
	private static String sender = "warrantypartners@gmail.com";
	final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static final Properties props = new Properties();

	public static void sendEmail() {
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("Send Success");
	}

	public static String getSubject() {
		return subject;
	}

	public static void setSubject(String subject) {
		MailUtil.subject = subject;
	}

	public static String getBody() {
		return body;
	}

	public static void setBody(String body) {
		MailUtil.body = body;
	}

	public static String getRecipient() {
		return recipient;
	}

	public static void setRecipient(String recipient) {
		MailUtil.recipient = recipient;
	}
}
