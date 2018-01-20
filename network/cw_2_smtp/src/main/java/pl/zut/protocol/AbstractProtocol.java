package pl.zut.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public abstract class AbstractProtocol {

	protected SSLSocket socket;
	
	protected String serverAddress;
	protected Integer serverPort;
	
	protected ProtocolType type;
	
	protected DataOutputStream dataOutputStream;
	
	protected Integer delayMS;
	
	public AbstractProtocol(String host, Integer port, ProtocolType type)  {
		
		this.serverAddress = host;
		this.serverPort = port;
		this.type = type;
		
		// opoznienie w ms
		this.delayMS = 1000;
	}
	
	
	protected void initializeConnection() throws IOException {
		initializeSocket();
		initializeOutputStream();
	}
	
	
	private void initializeSocket() throws IOException {
		
		SocketFactory socketFactory = SSLSocketFactory.getDefault();
		socket = (SSLSocket) socketFactory.createSocket(serverAddress, serverPort);
	}
	
	
	private void initializeOutputStream() throws IOException {
		
		 final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         (new Thread(new Runnable() {
        	 
              public void run() {
            	  
                   try {

                	   String line;
                        while( (line = br.readLine()) != null ) {
                             System.out.println( type.name() + " SERVER: "  + line );
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
		System.out.println( String.format("%s CLIENT: %s", type.name(), message ));
		delay();
	}
	
	
	protected void delay() throws InterruptedException {
		Thread.sleep(delayMS);
	}
	
}
