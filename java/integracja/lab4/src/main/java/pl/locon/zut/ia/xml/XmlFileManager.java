package pl.locon.zut.ia.xml;

import java.io.File;

public class XmlFileManager {

    public static final String BOOKS, BOOKS_2, BOOKS_3, LAB, CUSTOMER;

    static {
        BOOKS   = GetFile("books.xml");
        BOOKS_2 = GetFile("books.2.xml");
        BOOKS_3 = GetFile("books.3.xml");
        LAB     = GetFile("lab3.xml");
        CUSTOMER= GetFile("customer/customer.xml");
    }

    private static String GetFile(final String fileName) {

        return System.getProperty("user.dir")
            .concat(File.separator)
            .concat("xml")
            .concat(File.separator)
            .concat(fileName);
    }

}
