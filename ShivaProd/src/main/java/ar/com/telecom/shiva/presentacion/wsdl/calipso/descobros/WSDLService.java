package ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2019-05-21T12:28:50.177-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebServiceClient(name = "WSDLService", 
                  wsdlLocation = "file:/C:/Desarrollo/Workspace/ShivaProd/src/main/resources/wsdl/calipso/WebServiceReversionCobros.wsdl",
                  targetNamespace = "urn:WSDL") 
public class WSDLService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:WSDL", "WSDLService");
    public final static QName WebServiceReversionCobrosPort = new QName("urn:WSDL", "WebServiceReversionCobrosPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Desarrollo/Workspace/ShivaProd/src/main/resources/wsdl/calipso/WebServiceReversionCobros.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSDLService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Desarrollo/Workspace/ShivaProd/src/main/resources/wsdl/calipso/WebServiceReversionCobros.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSDLService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSDLService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSDLService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSDLService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSDLService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSDLService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns WebServiceReversionCobrosPortType
     */
    @WebEndpoint(name = "WebServiceReversionCobrosPort")
    public WebServiceReversionCobrosPortType getWebServiceReversionCobrosPort() {
        return super.getPort(WebServiceReversionCobrosPort, WebServiceReversionCobrosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceReversionCobrosPortType
     */
    @WebEndpoint(name = "WebServiceReversionCobrosPort")
    public WebServiceReversionCobrosPortType getWebServiceReversionCobrosPort(WebServiceFeature... features) {
        return super.getPort(WebServiceReversionCobrosPort, WebServiceReversionCobrosPortType.class, features);
    }

}
