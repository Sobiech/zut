package pl.zut.protocol;

import java.io.IOException;

import pl.zut.protocol.pop3.POP3Client;
import pl.zut.protocol.smtp.SMTPClient;

public class MailClient {
	
	private String user;
	private String password;
	
	private SMTPClient smtpClient;
	
	private POP3Client pop3Client;
	
	public MailClient(String userName, String userPassword) 
			throws IOException { 
		
		this.user = userName;
		this.password = userPassword;
	}
	
	public void getNewMessages() 
			throws IOException, InterruptedException {

		pop3Client = new POP3Client(user, password);
		pop3Client.getNewMessages();
	}
	
	public void sendMessage(String to, String message, String subject) 
			throws IOException, InterruptedException {
		
		smtpClient = new SMTPClient(user, password);
		smtpClient.sendMessage(to, subject, message);
	}
	
}
