
package pl.zut.zjava.wsdl.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.zut.zjava.wsdl.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddBookResponse_QNAME = new QName("http://manager.ia.zut.locon.pl/", "addBookResponse");
    private final static QName _SearchByAuthor_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByAuthor");
    private final static QName _SearchByISBNResponse_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByISBNResponse");
    private final static QName _SearchByTitleResponse_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByTitleResponse");
    private final static QName _SearchByAuthorResponse_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByAuthorResponse");
    private final static QName _RemoveBook_QNAME = new QName("http://manager.ia.zut.locon.pl/", "removeBook");
    private final static QName _SearchByTitle_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByTitle");
    private final static QName _Exception_QNAME = new QName("http://manager.ia.zut.locon.pl/", "Exception");
    private final static QName _RemoveBookResponse_QNAME = new QName("http://manager.ia.zut.locon.pl/", "removeBookResponse");
    private final static QName _SearchByISBN_QNAME = new QName("http://manager.ia.zut.locon.pl/", "searchByISBN");
    private final static QName _AddBook_QNAME = new QName("http://manager.ia.zut.locon.pl/", "addBook");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.zut.zjava.wsdl.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link pl.zut.zjava.wsdl.gen.Book }
     * 
     */
    public pl.zut.zjava.wsdl.gen.Book createBook() {
        return new pl.zut.zjava.wsdl.gen.Book();
    }

    /**
     * Create an instance of {@link SearchByISBNResponse }
     * 
     */
    public SearchByISBNResponse createSearchByISBNResponse() {
        return new SearchByISBNResponse();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link SearchByAuthor }
     * 
     */
    public SearchByAuthor createSearchByAuthor() {
        return new SearchByAuthor();
    }

    /**
     * Create an instance of {@link SearchByISBN }
     * 
     */
    public SearchByISBN createSearchByISBN() {
        return new SearchByISBN();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link SearchByAuthorResponse }
     * 
     */
    public SearchByAuthorResponse createSearchByAuthorResponse() {
        return new SearchByAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchByTitleResponse }
     * 
     */
    public SearchByTitleResponse createSearchByTitleResponse() {
        return new SearchByTitleResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link RemoveBookResponse }
     * 
     */
    public RemoveBookResponse createRemoveBookResponse() {
        return new RemoveBookResponse();
    }

    /**
     * Create an instance of {@link RemoveBook }
     * 
     */
    public RemoveBook createRemoveBook() {
        return new RemoveBook();
    }

    /**
     * Create an instance of {@link SearchByTitle }
     * 
     */
    public SearchByTitle createSearchByTitle() {
        return new SearchByTitle();
    }

    /**
     * Create an instance of {@link Publisher }
     * 
     */
    public Publisher createPublisher() {
        return new Publisher();
    }

    /**
     * Create an instance of {@link pl.zut.zjava.wsdl.gen.Book.Authors }
     * 
     */
    public pl.zut.zjava.wsdl.gen.Book.Authors createBookAuthors() {
        return new pl.zut.zjava.wsdl.gen.Book.Authors();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByAuthor")
    public JAXBElement<SearchByAuthor> createSearchByAuthor(SearchByAuthor value) {
        return new JAXBElement<SearchByAuthor>(_SearchByAuthor_QNAME, SearchByAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByISBNResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByISBNResponse")
    public JAXBElement<SearchByISBNResponse> createSearchByISBNResponse(SearchByISBNResponse value) {
        return new JAXBElement<SearchByISBNResponse>(_SearchByISBNResponse_QNAME, SearchByISBNResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByTitleResponse")
    public JAXBElement<SearchByTitleResponse> createSearchByTitleResponse(SearchByTitleResponse value) {
        return new JAXBElement<SearchByTitleResponse>(_SearchByTitleResponse_QNAME, SearchByTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByAuthorResponse")
    public JAXBElement<SearchByAuthorResponse> createSearchByAuthorResponse(SearchByAuthorResponse value) {
        return new JAXBElement<SearchByAuthorResponse>(_SearchByAuthorResponse_QNAME, SearchByAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "removeBook")
    public JAXBElement<RemoveBook> createRemoveBook(RemoveBook value) {
        return new JAXBElement<RemoveBook>(_RemoveBook_QNAME, RemoveBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByTitle")
    public JAXBElement<SearchByTitle> createSearchByTitle(SearchByTitle value) {
        return new JAXBElement<SearchByTitle>(_SearchByTitle_QNAME, SearchByTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "removeBookResponse")
    public JAXBElement<RemoveBookResponse> createRemoveBookResponse(RemoveBookResponse value) {
        return new JAXBElement<RemoveBookResponse>(_RemoveBookResponse_QNAME, RemoveBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchByISBN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "searchByISBN")
    public JAXBElement<SearchByISBN> createSearchByISBN(SearchByISBN value) {
        return new JAXBElement<SearchByISBN>(_SearchByISBN_QNAME, SearchByISBN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manager.ia.zut.locon.pl/", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

}
