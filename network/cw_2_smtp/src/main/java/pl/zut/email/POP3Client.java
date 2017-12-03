package pl.zut.email;

import java.net.*;
import java.io.*;


public class POP3Client {

	String user = "";
	String pass = "";
	
	int  c = 13, d = 10;
	
	Socket socket;
	
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	
	String temp = "";
	
	BufferedReader bri = new BufferedReader( new InputStreamReader(System.in) );

	POP3Client() throws Exception {
		
		System.out.print("Enter pop3 server :");
		connectAndRetreiveEmail(bri.readLine());
	}


	private void connectAndRetreiveEmail(String strPOP3Server) throws Exception {
		
			socket = new Socket( strPOP3Server, 110 );
			
			bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
			bufferedWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ));
			
			String temp1 = "";
			
			if(socket.isConnected()) {
				
				System.out.println("connected");
				System.out.println(bufferedReader.readLine());
				
				while(true) {
					
					if(authenticate()) {

						while(!temp1.toLowerCase().equals("quit")) {
							
							System.out.println("\nType appropriate # for required service :");
							System.out.println("\t1. To Read Message");
							System.out.println("\t2. To Delete Message");
							System.out.println("\t3. To Quit And Save Changes to Inbox");
							System.out.println("\t4. To Quit Without Saving Changes to Inbox");
							
							temp1 = bri.readLine();
							
							if(temp1.equals("1")) {
								
								displayEmail();
							} else if(temp1.equals("2")) {
							
								delEmail();
							} else if(temp1.equals("3")) {
								
								quit();
								break;
							} else if(temp1.equals("4")) {

								System.out.println("Quiting without saving changes to Inbox...");
								break;
							}

						}
						
						break;
					} else {
						
						System.out.println("For giving invalid information your connection is terminated!! Logging Off...");
						break;
					}
				}
			}

		socket.close();
	}


	private boolean authenticate() throws Exception {
	
		System.out.println("\n\t\t\t\tAUTHENTICATION");
		System.out.println("\t\t\t\t--------------");
		System.out.print("Please Enter User Name : ");
		
		user = bri.readLine();
		
		System.out.print("Please Enter password : ");
		pass = bri.readLine();
		
		//System.out.println();
		bufferedWriter.write("User "+user+"\n\r");
		bufferedWriter.flush();
		bufferedReader.readLine();
		bufferedWriter.write("pass "+pass+"\n\r");bufferedWriter.flush();
		
		System.out.println("Waiting for server response...");
		temp = bufferedReader.readLine();
		
		if(temp.substring(0,4).equals("-ERR")) {
			
			System.out.println("UserName or Password invalid");
			return false;
		} else {
			
			System.out.println(temp);
			return true;
		}

	}

	
	private void delEmail() throws Exception {
		
		System.out.println("\n\t\t\t\tEMAIL DELETION");
		System.out.println(  "\t\t\t\t----- --------");
		showList();
		System.out.print("Please Enter the message no. present in the list show above to delete the message : ");
		bufferedWriter.write("dele "+bri.readLine()+"\n\r");
		bufferedWriter.flush();
		
		temp = bufferedReader.readLine();
		
		if ( temp.substring(0,4).equals("-ERR") ) {
			
			System.out.println("U have given invalid message no.");
		} else {
			
			System.out.println(temp);
		}
		
	}

	
	private void showList() throws Exception {
		
		System.out.println("\n\t\t\t\tEMAIL LIST");
		System.out.println(  "\t\t\t\t----- ----");
		System.out.println("List of the available email messages");
		
		bufferedWriter.write("List\n\r");
		bufferedWriter.flush();
		
		temp = bufferedReader.readLine();
		
		while(!temp.equals(".")) {
			
			System.out.println(temp);
			temp=bufferedReader.readLine();
		}
		
		System.out.println(temp);
	}

	
	private void displayEmail() throws Exception {
		
		System.out.println("\n\t\t\t\tDISPLAYING EMAIL");
		System.out.println(  "\t\t\t\t---------- -----");
		
		showList();
		System.out.print("Please Enter the message no. present in the list show above to read the message : ");
		
		bufferedWriter.write("retr "+bri.readLine()+"\n\r");
		bufferedWriter.flush();
		
		temp = bufferedReader.readLine();
		
		if(temp.substring(0,4).equals("-ERR")) {
			
			System.out.println("U have given invalid message no.");
		} else {
			
			while(!temp.equals(".")) {
				System.out.println(temp);
				temp=bufferedReader.readLine();
			}
		}
		
		System.out.println(temp);

	}

	private void quit() throws Exception {
		
		System.out.println("Quiting and saving changes to Inbox ....");
		
		bufferedWriter.write("quit\n\r");
		bufferedWriter.flush();
		
		System.out.println(bufferedReader.readLine());
	}

} 