
package pl.zut.zjava.wsdl.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchByISBNResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchByISBNResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="book" type="{http://manager.ia.zut.locon.pl/}book" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchByISBNResponse", propOrder = {
    "book"
})
public class SearchByISBNResponse {

    protected pl.zut.zjava.wsdl.gen.Book book;

    /**
     * Gets the value of the book property.
     * 
     * @return
     *     possible object is
     *     {@link pl.zut.zjava.wsdl.gen.Book }
     *     
     */
    public pl.zut.zjava.wsdl.gen.Book getBook() {
        return book;
    }

    /**
     * Sets the value of the book property.
     * 
     * @param value
     *     allowed object is
     *     {@link pl.zut.zjava.wsdl.gen.Book }
     *     
     */
    public void setBook(pl.zut.zjava.wsdl.gen.Book value) {
        this.book = value;
    }

}
