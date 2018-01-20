package pl.zut.protocol.pop3;

import java.io.IOException;

import pl.zut.protocol.AbstractProtocol;
import pl.zut.protocol.ProtocolType;
import pl.zut.protocol.ServerConfig;

public class POP3Client extends AbstractProtocol {
	
	private String user;
	
	private String pass;
	
	public POP3Client(String user, String pass) throws IOException {

		super(ServerConfig.getPOP3ServerAddress(), ServerConfig.getPOP3Port(), ProtocolType.POP3);
		this.user = user;
		this.pass = pass;
	}

	
	public void getNewMessages() throws IOException, InterruptedException {

		initializeConnection();
		delay();
		
		send("USER "+ user);
        send("PASS " + pass);
        send("STAT");
//        send("LIST");
//        send("RETR 1");
        send("QUIT");
	}
	
}