package pl.zut.zjava.server.connection.soap.impl;


import org.postgresql.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.zjava.commons.dto.WorkerListDto;
import pl.zut.zjava.entity.service.WorkerService;
import pl.zut.zjava.entity.service.impl.WorkerServiceImpl;
import pl.zut.zjava.server.connection.soap.WorkerEndpointService;
import pl.zut.zjava.server.session.SessionCache;
import pl.zut.zjava.server.session.SessionDto;

import javax.jws.WebService;
import java.util.Objects;

@WebService(endpointInterface = "pl.zut.zjava.server.connection.soap.WorkerEndpointService")
public class WorkerEndpointServiceImpl
        extends AbstractWebServiceImpl implements WorkerEndpointService {


    public static final String PATH = "/workers";

    private static final Logger logger = LoggerFactory.getLogger(WorkerEndpointServiceImpl.class);

    private WorkerService abstractWorkerService;


    public WorkerEndpointServiceImpl() {
        super();
        abstractWorkerService = new WorkerServiceImpl();
    }


    @Override
    public WorkerListDto getWorkerList(String sid)
            throws Exception {

        long start = System.currentTimeMillis();

        try {

            logger.debug("getWorkerList(): started");
            String decodedSid = new String(Base64.decode(sid));

            SessionDto sessionDto = SessionCache.get().getSession(decodedSid);
            if ( Objects.isNull(sessionDto) || !sessionDto.isValid() ) {
                throw new Exception("Unauthorized, given sid is invalid or doesn't exists");
            }

            SessionCache.get().evict(decodedSid);

            return abstractWorkerService.getWorkerList();
        } finally {

            logger.debug("getWorkerList(): done in {}[ms]", ( System.currentTimeMillis() - start ) );
        }
    }


    @Override
    protected String getWebServicePath() {
        return PATH;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
