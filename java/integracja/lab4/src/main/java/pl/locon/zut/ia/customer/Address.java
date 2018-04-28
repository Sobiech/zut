package pl.locon.zut.ia.customer;

import javax.xml.bind.annotation.XmlType;

@XmlType( name = "address")
public class Address {

    private String city;

    private String address1;

    private String address2;

    private String postCode;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
