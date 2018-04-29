package pl.locon.zut.ia.manager;


import pl.locon.zut.ia.book.Book;
import pl.locon.zut.ia.book.BookList;
import pl.locon.zut.ia.xml.XmlFileManager;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
public class BookManager extends AbstractDaoManager<Book, BookList> {


    public BookManager()
            throws JAXBException, IOException {

        super(BookList.class);
        this.dataList = getData().getBooks();
    }


    @Override
    protected String getFileUrl() {
        return XmlFileManager.BOOKS_3;
    }


    @WebMethod
    public void addBook(@WebParam(name = "book") Book book) throws Exception {

        System.out.println("Got : " + book);
        if ( !Objects.isNull(book.getTitle()) && !book.getAuthors().isEmpty()) {
            dataList.add(book);
            System.out.println(book);
            return;
        }

        throw new Exception("Invalid parameter");
    }


    @WebMethod
    public @WebResult( name = "book" ) List<Book> searchByTitle(@WebParam(name = "title")  String title) throws Exception {

        if ( Objects.isNull(title) ) {
            throw new Exception("Invalid parameter");
        }

        List<Book> result = new ArrayList<>();
        for (Book b : dataList) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }


    @WebMethod
    public @WebResult(name = "book") List<Book> searchByAuthor(@WebParam(name = "author")  String searchingAuthor) throws Exception {

        if ( Objects.isNull(searchingAuthor) ) {
            throw new Exception("Invalid parameter");
        }

        List<Book> resultList = new ArrayList<>();

        for ( Book book : dataList ) {

            if ( book.getAuthors() != null && book.getAuthors().contains(searchingAuthor) ) {
                resultList.add(book);
            }
        }

        return resultList;
    }


    @WebMethod
    public @WebResult( name = "book" ) Book searchByISBN(@WebParam(name = "isbn") String isbn) throws Exception {

        if ( Objects.isNull(isbn) ) {
            throw new Exception("Invalid parameter");
        }

        for (Book b : dataList) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }

        return null;
    }



    @WebMethod
    public boolean removeBook(@WebParam(name = "id")  String id) throws Exception {

        if ( Objects.isNull(id) ) {
            throw new Exception("Invalid parameters");
        }

        for(Book b : dataList) {
            if( b.getId().equals(id) )	{
                dataList.remove(b);
                return true;
            }
        }

        throw new Exception("Could not remove book with id:" + id);
    }


}

