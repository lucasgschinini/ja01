
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente package. 
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

    private final static QName _ClTipoDomicilio_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/clienteDicc", "clTipoDomicilio");
    private final static QName _ClAgenciaGestionaria_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/clienteDicc", "clAgenciaGestionaria");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClDomicilios }
     * 
     */
    public ClDomicilios createClDomicilios() {
        return new ClDomicilios();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca }
     * 
     */
    public ClDomiciliosFinca createClDomiciliosFinca() {
        return new ClDomiciliosFinca();
    }

    /**
     * Create an instance of {@link ClienteCRM }
     * 
     */
    public ClienteCRM createClienteCRM() {
        return new ClienteCRM();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta }
     * 
     */
    public ClienteCRM.Cuenta createClienteCRMCuenta() {
        return new ClienteCRM.Cuenta();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Legados }
     * 
     */
    public ClienteCRM.Cuenta.Legados createClienteCRMCuentaLegados() {
        return new ClienteCRM.Cuenta.Legados();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Actividades }
     * 
     */
    public ClienteCRM.Cuenta.Actividades createClienteCRMCuentaActividades() {
        return new ClienteCRM.Cuenta.Actividades();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.JurisdiccionesIIBB }
     * 
     */
    public ClienteCRM.Cuenta.JurisdiccionesIIBB createClienteCRMCuentaJurisdiccionesIIBB() {
        return new ClienteCRM.Cuenta.JurisdiccionesIIBB();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca createClDomiciliosFincaDomicilioFinca() {
        return new ClDomiciliosFinca.DomicilioFinca();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca.Propiedades }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca.Propiedades createClDomiciliosFincaDomicilioFincaPropiedades() {
        return new ClDomiciliosFinca.DomicilioFinca.Propiedades();
    }

    /**
     * Create an instance of {@link ClDocumento }
     * 
     */
    public ClDocumento createClDocumento() {
        return new ClDocumento();
    }

    /**
     * Create an instance of {@link AgenciaNegocio }
     * 
     */
    public AgenciaNegocio createAgenciaNegocio() {
        return new AgenciaNegocio();
    }

    /**
     * Create an instance of {@link ClDomicilios.Domicilio }
     * 
     */
    public ClDomicilios.Domicilio createClDomiciliosDomicilio() {
        return new ClDomicilios.Domicilio();
    }

    /**
     * Create an instance of {@link ClienteCRM.Perfil }
     * 
     */
    public ClienteCRM.Perfil createClienteCRMPerfil() {
        return new ClienteCRM.Perfil();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Legados.Legado }
     * 
     */
    public ClienteCRM.Cuenta.Legados.Legado createClienteCRMCuentaLegadosLegado() {
        return new ClienteCRM.Cuenta.Legados.Legado();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Actividades.Actividad }
     * 
     */
    public ClienteCRM.Cuenta.Actividades.Actividad createClienteCRMCuentaActividadesActividad() {
        return new ClienteCRM.Cuenta.Actividades.Actividad();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB }
     * 
     */
    public ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB createClienteCRMCuentaJurisdiccionesIIBBJurisdiccionIIBB() {
        return new ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca.Provincia }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca.Provincia createClDomiciliosFincaDomicilioFincaProvincia() {
        return new ClDomiciliosFinca.DomicilioFinca.Provincia();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca.Localidad }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca.Localidad createClDomiciliosFincaDomicilioFincaLocalidad() {
        return new ClDomiciliosFinca.DomicilioFinca.Localidad();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca.Zona }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca.Zona createClDomiciliosFincaDomicilioFincaZona() {
        return new ClDomiciliosFinca.DomicilioFinca.Zona();
    }

    /**
     * Create an instance of {@link ClDomiciliosFinca.DomicilioFinca.Propiedades.Propiedad }
     * 
     */
    public ClDomiciliosFinca.DomicilioFinca.Propiedades.Propiedad createClDomiciliosFincaDomicilioFincaPropiedadesPropiedad() {
        return new ClDomiciliosFinca.DomicilioFinca.Propiedades.Propiedad();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/clienteDicc", name = "clTipoDomicilio")
    public JAXBElement<String> createClTipoDomicilio(String value) {
        return new JAXBElement<String>(_ClTipoDomicilio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/clienteDicc", name = "clAgenciaGestionaria")
    public JAXBElement<String> createClAgenciaGestionaria(String value) {
        return new JAXBElement<String>(_ClAgenciaGestionaria_QNAME, String.class, null, value);
    }

}
