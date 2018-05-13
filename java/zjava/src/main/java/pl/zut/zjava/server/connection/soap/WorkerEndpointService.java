package pl.zut.zjava.server.connection.soap;


import pl.zut.zjava.commons.dto.WorkerListDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "WorkerEndpointService" )
public interface WorkerEndpointService
        extends AbstractWebService {

    @WebMethod
    @WebResult( name = "pracownicy" )
    WorkerListDto getWorkerList(@WebParam( name = "sid") String sessionId) throws Exception;

}
