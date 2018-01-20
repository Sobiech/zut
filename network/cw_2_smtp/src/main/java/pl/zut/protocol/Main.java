package pl.zut.protocol;

public class Main {
	
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Initializing");
			
			MailClient client = new MailClient(ServerConfig.getLogin(), ServerConfig.getPassword());
			
			client.getNewMessages();
			client.sendMessage("kacper.sobisz@gmail.com", "Hello mother fucker!", "Test");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
}
