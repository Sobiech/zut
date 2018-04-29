package pl.locon.zut.ia;

import pl.locon.zut.ia.manager.BookManager;
import pl.locon.zut.ia.manager.CustomerManager;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws JAXBException, IOException {

        String url = "http://localhost:666/books";
        String customer = "http://localhost:666/customer";

        Endpoint.publish(url, new BookManager());
        System.out.println("Book manager published");

        Endpoint.publish(customer, new CustomerManager());
        System.out.println("Customer manager published");
    }

}
