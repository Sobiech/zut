package pl.zut.pswa.service.ws.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import pl.zut.pswa.config.Config;
import pl.zut.pswa.service.ws.AbstractWebService;

import java.util.Objects;

public abstract class AbstractWebServiceImpl
        implements AbstractWebService {

    AbstractWebServiceImpl() {
        getLogger().info("webService served on: {}", getWebServiceURL());
    }

    @Override
    public String getWebServiceURL() {
        return Config.getWebServiceHostAddress().concat(getWebServicePath());
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
