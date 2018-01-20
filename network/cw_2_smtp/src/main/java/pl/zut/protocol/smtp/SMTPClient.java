package pl.zut.protocol.smtp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import pl.zut.protocol.AbstractProtocol;
import pl.zut.protocol.ProtocolType;
import pl.zut.protocol.ServerConfig;

public class SMTPClient extends AbstractProtocol {

	
	private String userEmail;
	
	private String loginEncoded;
	
	private String passEncoded;
	
	
	public SMTPClient(String user, String pass) throws IOException {

		super(ServerConfig.getSMTPServerAddress(), ServerConfig.getSMTPPort(), ProtocolType.SMTP);
		
		this.userEmail = user;
		this.loginEncoded = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        this.passEncoded = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
	}

	
	public void sendMessage(String to, String subject, String message) throws IOException, InterruptedException {
		
		initializeConnection();
		delay();
		
		send("EHLO " + this.serverAddress );
		send("AUTH LOGIN");
		send(loginEncoded);
		send(passEncoded);
		send("MAIL FROM:<" + userEmail + ">");
		send("RCPT TO:<" + to  + ">");
		send("DATA");
		send("Subject: " + subject);
		send("From: " + userEmail);
		send("To: " + to);
		send(message);
		send(".");
		send("QUIT");
	}
	
}
