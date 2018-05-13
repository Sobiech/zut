package pl.zut.pswa.config;

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

    private final static String CFG_FILE_NAME = "config.properties";

    private static final Properties properties;

    static {

        URL res = Config.class.getClassLoader().getResource(CFG_FILE_NAME);
        if (res == null) {
            throw new UncheckedIOException(new FileNotFoundException(CFG_FILE_NAME));
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


    public static String getWebServiceHostAddress() {
        return properties.getProperty("webservice.host.address");
    }

}
