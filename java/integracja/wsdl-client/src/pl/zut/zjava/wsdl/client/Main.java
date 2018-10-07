package pl.zut.zjava.wsdl.client;

import pl.zut.zjava.wsdl.gen.Book;
import pl.zut.zjava.wsdl.gen.BookManager;
import pl.zut.zjava.wsdl.gen.BookManagerService;
import pl.zut.zjava.wsdl.gen.Exception_Exception;

import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception_Exception {

        BookManagerService client = new BookManagerService();
        BookManager manager = client.getBookManagerPort();
        List<Book> bookList = manager.searchByTitle("Kacper");

        System.out.println(bookList);
    }

}
