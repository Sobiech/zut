package pl.locon.zut.ia.book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "book")
public class Book {

    private String id;

    private String title;

    private List<String> authors;

    // odkomentowac dla zad 1
//    private String author;

    private String isbn;

    private Integer year;


    // zadanie 3 dodanie obiektu Publisher
    private Publisher publisher;

    private Integer pages;

    public Book() {}

    public Book(String id, String title, List<String> authors, String isbn, Integer year, Publisher publisher, Integer pages) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.year = year;
        this.publisher = publisher;
        this.pages = pages;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElementWrapper(name = "authors")
    @XmlElement( name = "author")
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String>  author) {
        this.authors = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @XmlElement( name = "publisher")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /*
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }*/

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                '}';
    }
}
