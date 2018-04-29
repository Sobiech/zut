package pl.locon.zut.ia;

import pl.locon.zut.ia.book.BookList;
import pl.locon.zut.ia.manager.BookManager;
import pl.locon.zut.ia.manager.CustomerManager;
import pl.locon.zut.ia.xml.XmlFileManager;
import pl.locon.zut.ia.xml.XmlUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {


    public static void main(String[] args) throws JAXBException, IOException, URISyntaxException {


        // Zadanie 1
        /*
        Book
            book = new Book();
            book.setAuthors("Kacper Sobisz22");
            book.setTitle("Laboratorium Java i XML");

        BookManager
            manager = new BookManager(Collections.singletonList(book));
        BookList bookList = new BookList(manager.searchByTitle("Java"));
        XmlUtils.MarshalDataToFile(XmlFileManager.LAB, BookList.class, bookList);
        */

        // Zadanie 2
        XmlUtils.UnmarshallDataFromFile(XmlFileManager.BOOKS_2, BookList.class).getBooks().forEach(System.out::println);


        // Zadanie 3

        new BookManager().getData().getBooks().forEach(System.out::println);
        new CustomerManager().getData().getCustomerList().forEach(System.out::println);

//        XmlUtils.UnmarshallDataFromFile(XmlFileManager.BOOKS_3, BookList.class).getBooks().forEach(System.out::println);


        // Zadanie dodadkowe


    }

}
