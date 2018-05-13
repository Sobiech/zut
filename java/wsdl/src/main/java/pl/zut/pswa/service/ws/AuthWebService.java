package pl.zut.pswa.service.ws;

import pl.zut.pswa.dto.AuthData;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "auth" )
public interface AuthWebService extends AbstractWebService {


    @WebMethod
    @WebResult( name = "sid" )
    String login(AuthData authData) throws Exception;

    @WebMethod
    @WebResult
    Boolean logout(@WebParam( name = "sid") String sid ) throws Exception;

}
