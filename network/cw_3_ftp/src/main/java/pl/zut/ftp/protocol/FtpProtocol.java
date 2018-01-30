package pl.zut.ftp.protocol;

import java.io.IOException;

import javax.swing.JTextArea;

import pl.zut.ftp.ClientConfig;

public class FtpProtocol extends AbstractProtocol {

	public FtpProtocol(JTextArea console) {
		super(ClientConfig.getHostAddress(), 21, "FTP", console);
	}
	
	
	public void login(String login, String pass) throws IOException, InterruptedException {
		send("USER " + login);
		delay();
		send("PASS " + pass);
		delay();
		send("PASV");
		delay();
	}
	
	public void logout() throws InterruptedException, IOException {
		send("QUIT");
		delay();
	}
	
	public void sendMessage(String msg) throws InterruptedException, IOException {
		this.send(msg);
	}
	

}
