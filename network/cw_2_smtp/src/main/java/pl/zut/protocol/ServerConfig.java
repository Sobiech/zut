package pl.zut.protocol;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerConfig {
	
	private static final String FILE_NAME = "main.properties";
	
	private static Properties properties;
	
	static {
		
		properties = new Properties();
		
		try {
			
			FileInputStream in = new FileInputStream(FILE_NAME);
			properties.load(in);
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getSMTPServerAddress() {
		
		return properties.getProperty("smtp.server.address");
	}
	
	
	public static Integer getSMTPPort() {
		
		return Integer.valueOf(properties.getProperty("smtp.server.port"));
	}
	
	
	public static String getPOP3ServerAddress() {
		
		return properties.getProperty("pop3.server.address");
	}
	
	
	public static Integer getPOP3Port() {
		
		return Integer.valueOf(properties.getProperty("pop3.server.port"));
	}
	
	public static String getLogin() {
		
		return properties.getProperty("login.name");
	}
	
	
	public static String getPassword() {
		
		return properties.getProperty("login.password");
	}

	
}