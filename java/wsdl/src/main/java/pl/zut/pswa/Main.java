package pl.zut.pswa;

import com.google.common.collect.ImmutableList;
import pl.zut.pswa.enums.PersistenceUnitFactory;
import pl.zut.pswa.service.ws.AbstractWebService;
import pl.zut.pswa.service.ws.AuthWebService;
import pl.zut.pswa.service.ws.UserWebService;
import pl.zut.pswa.service.ws.impl.AuthWebServiceImpl;
import pl.zut.pswa.service.ws.impl.UserWebServiceImpl;

import javax.xml.ws.Endpoint;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PersistenceUnitFactory.PSWA_UNIT.createEntityManager();
        initializeEndpoints();
    }


    private static void initializeEndpoints() {

        List<AbstractWebService> services =
            ImmutableList.of(
                new UserWebServiceImpl(),
                new AuthWebServiceImpl()
            );

        services.forEach( service -> Endpoint.publish(service.getWebServiceURL(), service));
    }

}
