package pl.zut.zjava.commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public final class Config {

	private static final Properties properties;
	
	private static final String FILE_NAME = "config.properties";
	
	static {
		
		URL res = Config.class.getClassLoader().getResource(FILE_NAME);
		if (res == null) {
			throw new UncheckedIOException(new FileNotFoundException(FILE_NAME));
		}
		
		URI uri;

		try {
			uri = res.toURI(); 
		} catch ( URISyntaxException ex ) { 
			throw new IllegalArgumentException(ex); 
		}

		try ( InputStream is = Files.newInputStream(Paths.get(uri)) ) { 
			
			properties = new Properties();
			properties.load(is); 
		} catch ( IOException ex ) {
			
			throw new UncheckedIOException("Failed to load resource", ex); 
		}
		
	}
	
	
	public static String getDbConnectionString() {
		return properties.getProperty("rdbm.conn.string");
	}
	
	
}
