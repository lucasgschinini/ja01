package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2019-05-21T12:28:49.104-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "urn:WSDL", name = "WebServiceCalipsoCobranzasPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebServiceCalipsoCobranzasPortType {

    /**
     * Metodo que nos devuelve los resultados
     * 				del proceso de
     * 				confirmacion de una
     * 				apropiacion de deuda
     * 			
     */
    @WebResult(name = "respuestaconfirmacion", targetNamespace = "urn:WSDL", partName = "respuestaconfirmacion")
    @WebMethod(operationName = "Confirmacion", action = "urn:WebServiceCalipsoCobranzasAction")
    public Listaresultado confirmacion(
        @WebParam(partName = "idShiva", name = "idShiva")
        java.math.BigInteger idShiva,
        @WebParam(partName = "usuCobrador", name = "usuCobrador")
        java.lang.String usuCobrador,
        @WebParam(partName = "listaFac", name = "listaFac")
        ListaFacArrayConfirmacion listaFac,
        @WebParam(partName = "listaCtaCre", name = "listaCtaCre")
        ListaCtaCreArrayConfirmacion listaCtaCre
    );

    /**
     * Metodo que nos devuelve los resultados
     * 				del proceso de
     * 				desapropiacion de la gestion
     * 				de la deuda de los Clientes
     * 			
     */
    @WebResult(name = "respuestadesapropiacion", targetNamespace = "urn:WSDL", partName = "respuestadesapropiacion")
    @WebMethod(operationName = "Desapropiacion", action = "urn:WebServiceCalipsoCobranzasAction")
    public Listaresultado desapropiacion(
        @WebParam(partName = "idShiva", name = "idShiva")
        java.math.BigInteger idShiva,
        @WebParam(partName = "usuCobrador", name = "usuCobrador")
        java.lang.String usuCobrador,
        @WebParam(partName = "listaFac", name = "listaFac")
        ListaFacArrayDesapropiacion listaFac,
        @WebParam(partName = "listaCtaCre", name = "listaCtaCre")
        ListaCtaCreArrayDesapropiacion listaCtaCre
    );

    /**
     * Metodo que nos devuelve los resultados
     * 				del proceso de
     * 				apropiacion de la gestion
     * 				de la deuda de los Clientes mediante las
     * 				operaciones:
     * 				Apropiacion de Deuda
     * 				Apropiacion de CTA
     * 				Apropiacion de
     * 				Deuda y CTA
     * 			
     */
    @WebResult(name = "respuestaapropiacion", targetNamespace = "urn:WSDL", partName = "respuestaapropiacion")
    @WebMethod(operationName = "Apropiacion", action = "urn:WebServiceCalipsoCobranzasAction")
    public ApropiacionSalida apropiacion(
        @WebParam(partName = "idShiva", name = "idShiva")
        java.lang.String idShiva,
        @WebParam(partName = "idTransaccion", name = "idTransaccion")
        java.lang.String idTransaccion,
        @WebParam(partName = "usuario", name = "usuario")
        java.lang.String usuario,
        @WebParam(partName = "modoOperacion", name = "modoOperacion")
        java.lang.String modoOperacion,
        @WebParam(partName = "monedaOperacion", name = "monedaOperacion")
        java.lang.String monedaOperacion,
        @WebParam(partName = "fechaCob", name = "fechaCob")
        java.lang.String fechaCob,
        @WebParam(partName = "detFac", name = "detFac")
        DetFacArrayApropiacion detFac,
        @WebParam(partName = "listaCtaCre", name = "listaCtaCre")
        ListaCtaCreArrayApropiacion listaCtaCre
    );
}
