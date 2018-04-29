package pl.locon.zut.ia.manager;

import com.sun.xml.ws.util.StringUtils;
import pl.locon.zut.ia.customer.Customer;
import pl.locon.zut.ia.customer.CustomerList;
import pl.locon.zut.ia.xml.XmlFileManager;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebService
public class CustomerManager extends AbstractDaoManager<Customer, CustomerList> {


    private Set<String> sidSet;


    public CustomerManager()
            throws JAXBException, IOException {

        super(CustomerList.class);
        this.dataList = getData().getCustomerList();
        sidSet = new LinkedHashSet<>();
    }


    @Override
    protected String getFileUrl() {
        return XmlFileManager.CUSTOMER;
    }


    public @WebResult( name = "sid" ) String login(@WebParam( name = "user") String user, @WebParam( name = "password" ) String password ) throws Exception {

        if ( Objects.isNull(user) || Objects.isNull(password)) {
            throw new Exception("Invalid data");
        }

        if ( !user.equals("admin") || !password.equals("admin")) {
            throw new Exception("Unauthorized");
        }

        String sid = UUID.randomUUID().toString();
        sidSet.add(sid);

        return sid;
    }


    @WebMethod
    public @WebResult( name = "customer" ) List<Customer> getCustomerListByFirstname( @WebParam( name = "firstname" )  String firstname, @WebParam(name ="sid") String sid ) throws Exception {

        if (Objects.isNull(firstname)) {
            throw new Exception("Invalid parameters");
        }

        if ( Objects.isNull(sid) || !sidSet.contains(sid) ) {
            throw new Exception("Unauthorized");
        }

        return this.dataList
            .stream()
            .filter( customer -> customer.getFirstname().equalsIgnoreCase(firstname) )
            .collect(Collectors.toList());
    }


    @WebMethod
    public @WebResult( name = "customer" ) List<Customer> getCustomerListByLastname(@WebParam( name = "lastname" )  String lastname, @WebParam(name ="sid") String sid) throws Exception {

        if (Objects.isNull(lastname)) {
            throw new Exception("Invalid parameters");
        }

        if ( Objects.isNull(sid) || !sidSet.contains(sid) ) {
            throw new Exception("Unauthorized");
        }

        return this.dataList
            .stream()
            .filter( customer -> customer.getLastname().equalsIgnoreCase(lastname) )
            .collect(Collectors.toList());
    }


    @WebMethod
    public @WebResult boolean removeSession(@WebParam( name = "sid" )  String sid) throws Exception {

        if (Objects.isNull(sid) || !sidSet.contains(sid)) {
            throw new Exception("Unauthorized");
        }

        sidSet.remove(sid);
        return true;
    }

}
