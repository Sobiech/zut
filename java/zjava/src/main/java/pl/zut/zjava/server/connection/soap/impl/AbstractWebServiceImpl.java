package pl.zut.zjava.server.connection.soap.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import pl.zut.zjava.server.connection.soap.AbstractWebService;

import java.util.Objects;

public abstract class AbstractWebServiceImpl
        implements AbstractWebService {

    public static final String HOST = "http://192.168.4.10:4242";

    AbstractWebServiceImpl() {
        getLogger().info("webService served on: {}", getWebServiceURL());
    }

    @Override
    public String getWebServiceURL() {
        return HOST.concat(getWebServicePath());
    }

    void validateParameters(Object... objects)
            throws Exception {

        for ( Object obj: ArrayUtils.toArray(objects) ){
            if (Objects.isNull(obj) ) {
                throw new Exception("One of parameters are invalid!");
            }
        }
    }

    protected abstract String getWebServicePath();

    protected abstract Logger getLogger();
}
