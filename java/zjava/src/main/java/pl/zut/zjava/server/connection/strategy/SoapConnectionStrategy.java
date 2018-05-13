package pl.zut.zjava.server.connection.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.dto.WorkerListDto;
import pl.zut.zjava.entity.Worker;
import pl.zut.zjava.server.connection.protocol.soap.WorkerEndpointService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URI;
import java.util.List;

public class SoapConnectionStrategy
        implements ConnectionStrategy {


    private static final Logger logger = LoggerFactory.getLogger(SoapConnectionStrategy.class);


    private final String path;


    public SoapConnectionStrategy(final String path) {
        this.path = path;
    }


    @Override
    public List<Worker> getWorkerList(String host, Integer port, String sid)
            throws Exception {

        long start = System.currentTimeMillis();
        logger.debug("getWorkerList(): {}:{}/{} by sid:{}",host,port,path, sid);

        try {

            String wsdlPath = "/".concat(path).concat("?wsdl");
            URI uri = new URI("http", null, host, port, wsdlPath, null, null);

            QName serviceQN = new QName(
                "http://impl.soap.connection.server.zjava.zut.pl/",
                "WorkerEndpointServiceImplService"
            );

            Service service = Service.create(uri.toURL(), serviceQN);
            WorkerEndpointService endpoint = service.getPort(WorkerEndpointService.class);
            WorkerListDto dto = endpoint.getWorkerList(sid);

            return dto.getWorkerList();
        } finally {

            logger.debug("getWorkerList(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }
    }
}
