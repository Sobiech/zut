
package pl.zut.zjava.wsdl.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BookManagerService", targetNamespace = "http://manager.ia.zut.locon.pl/", wsdlLocation = "http://localhost:666/books?wsdl")
public class BookManagerService
    extends Service
{

    private final static URL BOOKMANAGERSERVICE_WSDL_LOCATION;
    private final static WebServiceException BOOKMANAGERSERVICE_EXCEPTION;
    private final static QName BOOKMANAGERSERVICE_QNAME = new QName("http://manager.ia.zut.locon.pl/", "BookManagerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:666/books?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BOOKMANAGERSERVICE_WSDL_LOCATION = url;
        BOOKMANAGERSERVICE_EXCEPTION = e;
    }

    public BookManagerService() {
        super(__getWsdlLocation(), BOOKMANAGERSERVICE_QNAME);
    }

    public BookManagerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BOOKMANAGERSERVICE_QNAME, features);
    }

    public BookManagerService(URL wsdlLocation) {
        super(wsdlLocation, BOOKMANAGERSERVICE_QNAME);
    }

    public BookManagerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BOOKMANAGERSERVICE_QNAME, features);
    }

    public BookManagerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookManagerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BookManager
     */
    @WebEndpoint(name = "BookManagerPort")
    public BookManager getBookManagerPort() {
        return super.getPort(new QName("http://manager.ia.zut.locon.pl/", "BookManagerPort"), BookManager.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BookManager
     */
    @WebEndpoint(name = "BookManagerPort")
    public BookManager getBookManagerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://manager.ia.zut.locon.pl/", "BookManagerPort"), BookManager.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BOOKMANAGERSERVICE_EXCEPTION!= null) {
            throw BOOKMANAGERSERVICE_EXCEPTION;
        }
        return BOOKMANAGERSERVICE_WSDL_LOCATION;
    }

}