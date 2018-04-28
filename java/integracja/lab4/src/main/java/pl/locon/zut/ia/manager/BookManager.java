package pl.locon.zut.ia.manager;


import pl.locon.zut.ia.book.Book;
import pl.locon.zut.ia.book.BookList;
import pl.locon.zut.ia.xml.XmlFileManager;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : dataList) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }


    public List<Book> searchByAuthor(final String searchingAuthor) {

        List<Book> resultList = new ArrayList<>();

        for ( Book book : dataList ) {

            Optional<String> result = book.getAuthors().stream()
                .filter( author -> author.toLowerCase().contains(searchingAuthor))
                .findAny();

            if ( result.isPresent() ) {
                resultList.add(book);
            }
        }

        return resultList;
    }

    /*
    public List<Book> searchByAuthor(final String searchingAuthor) {

        return book.stream()
            .filter(book -> book.getAuthor().toLowerCase().contains(searchingAuthor))
            .collect(Collectors.toList());
    }*/


    public Book searchByISBN(String isbn) {
        for (Book b : dataList) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }

}

