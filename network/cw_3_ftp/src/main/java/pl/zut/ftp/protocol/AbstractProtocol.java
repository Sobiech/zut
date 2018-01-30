package pl.zut.ftp.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.swing.JTextArea;

public abstract class AbstractProtocol {

	protected Socket socket;
	
	protected String serverAddress;
	protected Integer serverPort;
	
	protected String protocolName;
	
	protected DataOutputStream dataOutputStream;
	
	protected Integer delayMS;
	
	private JTextArea console;
	
	public AbstractProtocol(String host, Integer port, String protocol, JTextArea console)  {
		
		this.console = console;
		this.serverAddress = host;
		this.serverPort = port;
		this.protocolName = protocol;
		
		// opoznienie w ms
		this.delayMS = 500;
	}
	
	
	public void initializeConnection() throws IOException {
		initializeSocket();
		initializeOutputStream();
	}
	
	
	private void initializeSocket() throws IOException {
		socket = SocketFactory.getDefault().createSocket(serverAddress, serverPort);
	}
	
	
	private void initializeOutputStream() throws IOException {
		
		 final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         (new Thread(new Runnable() {
        	 
              public void run() {
            	  
                   try {

                	   String line;
                        while( (line = br.readLine()) != null ) {
                        	appendConsole( protocolName + " SERVER: "  + line );
                        }
                   } catch (IOException e) {
                	   
                        e.printStackTrace();
                   }
              }
         })).start();
         
         dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
	}
	
	
	protected void send(String message) throws IOException, InterruptedException {
		
		message += "\r\n";
		dataOutputStream.writeBytes(message);
		appendConsole( String.format("%s CLIENT: %s", protocolName, message ));
		delay();
	}
	
	
	protected void delay() throws InterruptedException {
		Thread.sleep(delayMS);
	}
	
	protected void appendConsole(String message) {
		this.console.append(String.format("%s\n", message));
	}
}

