package pl.zut.pswa.service.ws;

import pl.zut.pswa.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "user" )
public interface UserWebService extends AbstractWebService {

    @WebMethod
    @WebResult(name = "user")
    User getUserById(@WebParam( name = "id") Long id)  throws Exception;

    @WebMethod
    @WebResult(name = "user")
    List<User> findByFirstName(@WebParam( name = "first_name") String firstName) throws Exception;


    @WebMethod
    @WebResult(name = "user")
    List<User> findByLastName(@WebParam( name = "last_name") String lastName) throws Exception;


    @WebMethod
    @WebResult(name = "user")
    User findByPhoneNumber(@WebParam( name = "phone") String phone ) throws Exception;

}
