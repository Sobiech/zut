package pl.zut.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ClientConfig {
	
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
	
	public static String getHostAddress() {
		return properties.getProperty("ftp.host.address");
	}
	
	public static String getFtpLogin() {
		return properties.getProperty("ftp.login");
	}
	
	public static String getFtpPassword() {
		return properties.getProperty("ftp.password");
	}
}
