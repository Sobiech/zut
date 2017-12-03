package pl.zut.email;

import java.net.*;
import java.io.*;


public class SMTPClient {

	String user = "";
	String pass = "";
	
	int  c = 13, d = 10;
	
	Socket socket;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	
	String temp = "";
	BufferedReader bri = new BufferedReader( new InputStreamReader(System.in) );

	public SMTPClient() throws Exception {
		
		System.out.print("Enter SMTP server :");

			getConnection(bri.readLine());
			
			if ( socket.isConnected() ) {
				
				System.out.println(bufferedReader.readLine());
				greet();
				sendEmail();
			} else {
				
				System.out.println("Connection could not be established");
			}

			temp = bufferedReader.readLine();
			
			if(temp.substring(0,3).equals("250")) {
				
				System.out.println(temp.substring(3,temp.length()));
				System.out.println("Now logging Off....");
				quit();
			}
	}
	



	private void getConnection(String strSMTPServer) 
			throws Exception {
		
		socket = new Socket( strSMTPServer, 25 );
		
		bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
		bufferedWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ));

	}


	private void greet() throws Exception {
		
		System.out.println("\n\t\t\t\tGREETING SMTP SERVER");
		System.out.println(  "\t\t\t\t-------- ---- ------");
		System.out.print("Please any string as ur identification : ");
		
		user = bri.readLine();
		bufferedWriter.write( "Helo "+user+"\r\n");
		
		bufferedWriter.flush();
		
		System.out.println(bufferedReader.readLine());
	}

	private void setFrom() throws Exception {
		
		System.out.print("Please Enter your(sender's) email address : ");
		bufferedWriter.write("Mail From : "+bri.readLine()+ "\r\n");
		bufferedWriter.flush();
		temp = bufferedReader.readLine();
		
		while(!temp.substring(0,3).equals("250")) {
			
			System.out.println("Please enter a valid sender's address !!");
			bufferedWriter.write("Mail From : "+bri.readLine()+"\n\r");
			
			bufferedWriter.flush();
			temp = bufferedReader.readLine();
		}
		
		System.out.println(temp);
	}

	private String setReceipent() throws Exception {
		
		System.out.print("Please Enter destination's email address : ");
		bufferedWriter.write("RCPT To: "+bri.readLine()+"\r\n");
		bufferedWriter.flush();
		temp = bufferedReader.readLine();
		
		return temp;
	}

	private void setData() throws Exception {
		
			bufferedWriter.write("Data\r\n");
			bufferedWriter.flush();
			
			System.out.println(bufferedReader.readLine());
			System.out.println("Type the message to be sent");
			
			bufferedWriter.write(bri.readLine()+ "\r\n");
			bufferedWriter.flush();
			
			bufferedWriter.write(".\r\n");
			bufferedWriter.flush();
	}

	private void sendEmail() throws Exception {
		
		System.out.println("\n\t\t\t\tSENDING EMAIL");
		System.out.println(  "\t\t\t\t------- -----");

		//SET FROM ADDRESS (SENDER"S ADDRESS)
		setFrom();

		// setReceipent email address. On success ask for data and on failure disconnect connection.
		if(setReceipent().substring(0,3).equals("550")) {
			
			System.out.println("Destination email address not accepted by the SMTP server you gave!!");
			System.out.println("Connection to the SMTP server lost!!");
		} else {
			
			System.out.println("Destination email address accepted by the SMTP server");
			//After acceptance of destination email address ask for the message body.
			setData();
		}
	}


	private void quit() throws Exception {
		
		System.out.println("Quiting ....");
		bufferedWriter.write("quit\n\r");
		bufferedWriter.flush();
		
		System.out.println(bufferedReader.readLine());
		socket.close();
	}

	
} 