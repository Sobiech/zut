package pl.locon.zut.ia.customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "customers")
public class CustomerList {

    private List<Customer> customerList;

    public CustomerList(){}

    public CustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @XmlElement( name = "customer" )
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
