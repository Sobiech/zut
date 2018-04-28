package pl.locon.zut.ia.manager;

import pl.locon.zut.ia.customer.Customer;
import pl.locon.zut.ia.customer.CustomerList;
import pl.locon.zut.ia.xml.XmlFileManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerManager extends AbstractDaoManager<Customer, CustomerList> {


    public CustomerManager()
            throws JAXBException, IOException {

        super(CustomerList.class);
        this.dataList = getData().getCustomerList();
    }


    @Override
    protected String getFileUrl() {
        return XmlFileManager.CUSTOMER;
    }


    public List<Customer> getCustomerListByFirstname(String firstname) {

        return this.dataList
            .stream()
            .filter( customer -> customer.getFirstname().equalsIgnoreCase(firstname) )
            .collect(Collectors.toList());
    }



    public List<Customer> getCustomerListByLastname(String lastname) {

        return this.dataList
            .stream()
            .filter( customer -> customer.getLastname().equalsIgnoreCase(lastname) )
            .collect(Collectors.toList());
    }



    public List<Customer> getCustomerListByCity(String city) {

        return this.dataList
            .stream()
            .filter( customer -> customer.getDeliveryAddress().getCity().equalsIgnoreCase(city) )
            .collect(Collectors.toList());
    }

}
