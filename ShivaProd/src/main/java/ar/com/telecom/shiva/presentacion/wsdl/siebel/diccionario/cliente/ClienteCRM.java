
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for clienteCRM element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="clienteCRM">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;all>
 *           &lt;element name="cuenta">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;all>
 *                     &lt;element name="numeroCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="fechaAlta" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="fechaActualiz" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="codigoTipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionTipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clDocumento"/>
 *                     &lt;element name="fechaNacimiento" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="codigoSaludo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionSaludo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoProfesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionProfesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="holdingFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="numeroHolding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="nombreHolding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoEmpresaAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionEmpresaAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoCategoriaIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCategoriaIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="tasaExclIVA" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="fechaDesdeTasaExclIVA" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="fechaHastaTasaExclIVA" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="numeroIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="jurisdiccionesIIBB">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="jurisdiccionIIBB" maxOccurs="unbounded" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;sequence>
 *                                         &lt;element name="codigoJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                         &lt;element name="descripcionJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                         &lt;element name="codigioCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                         &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                         &lt;element name="tasaExclIIBB" minOccurs="0">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                               &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                         &lt;element name="fechaDesdeTasaExclIIBB" minOccurs="0">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                         &lt;element name="fechaHastaTasaExclIIBB" minOccurs="0">
 *                                           &lt;simpleType>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                                             &lt;/restriction>
 *                                           &lt;/simpleType>
 *                                         &lt;/element>
 *                                         &lt;element name="agentePercepcionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;/sequence>
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="tasaExcl3337" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="fechaDesdePorcExcl3337" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="fechaHastaPorcExcl3337" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="domicioInternacional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="actividades">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="actividad" maxOccurs="unbounded" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;sequence>
 *                                         &lt;element name="codigoActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                         &lt;element name="descripcionActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;/sequence>
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="numeroTelefonoReferencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="numeroFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="numeroTelefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="fechaActualizacionNroTelCelular" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="numeroTelefonoLaboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="emailPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="fechaActualizacionEmailPrincipal" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="emailAlternativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="marcaNoEnviarPublicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="prioridadDesconexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionPrioridadDesconexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="marcaClienteConParqueMatrix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCodigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}agenciaNegocio"/>
 *                     &lt;element name="agrupadorSegmentoNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="legados" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="legado" maxOccurs="unbounded" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                       &lt;attribute name="clienteId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                       &lt;attribute name="ofId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                             &lt;attribute name="resultado" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                             &lt;attribute name="razon" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/all>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="perfil">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="nombreCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="codigoAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="codigoAgenciaCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="descripcionCodigoAgenciaCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="marcaVIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clDomiciliosFinca"/>
 *           &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clDomicilios"/>
 *         &lt;/all>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "clienteCRM")
public class ClienteCRM {

    @XmlElement(required = true)
    protected ClienteCRM.Cuenta cuenta;
    @XmlElement(required = true)
    protected ClienteCRM.Perfil perfil;
    @XmlElement(required = true)
    protected ClDomiciliosFinca clDomiciliosFinca;
    @XmlElement(required = true)
    protected ClDomicilios clDomicilios;

    /**
     * Gets the value of the cuenta property.
     * 
     * @return
     *     possible object is
     *     {@link ClienteCRM.Cuenta }
     *     
     */
    public ClienteCRM.Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteCRM.Cuenta }
     *     
     */
    public void setCuenta(ClienteCRM.Cuenta value) {
        this.cuenta = value;
    }

    /**
     * Gets the value of the perfil property.
     * 
     * @return
     *     possible object is
     *     {@link ClienteCRM.Perfil }
     *     
     */
    public ClienteCRM.Perfil getPerfil() {
        return perfil;
    }

    /**
     * Sets the value of the perfil property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteCRM.Perfil }
     *     
     */
    public void setPerfil(ClienteCRM.Perfil value) {
        this.perfil = value;
    }

    /**
     * Gets the value of the clDomiciliosFinca property.
     * 
     * @return
     *     possible object is
     *     {@link ClDomiciliosFinca }
     *     
     */
    public ClDomiciliosFinca getClDomiciliosFinca() {
        return clDomiciliosFinca;
    }

    /**
     * Sets the value of the clDomiciliosFinca property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClDomiciliosFinca }
     *     
     */
    public void setClDomiciliosFinca(ClDomiciliosFinca value) {
        this.clDomiciliosFinca = value;
    }

    /**
     * Gets the value of the clDomicilios property.
     * 
     * @return
     *     possible object is
     *     {@link ClDomicilios }
     *     
     */
    public ClDomicilios getClDomicilios() {
        return clDomicilios;
    }

    /**
     * Sets the value of the clDomicilios property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClDomicilios }
     *     
     */
    public void setClDomicilios(ClDomicilios value) {
        this.clDomicilios = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="numeroCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fechaAlta" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="fechaActualiz" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="codigoTipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionTipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}clDocumento"/>
     *         &lt;element name="fechaNacimiento" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="codigoSaludo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionSaludo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoProfesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionProfesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="holdingFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroHolding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nombreHolding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoEmpresaAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionEmpresaAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCategoriaIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCategoriaIVA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tasaExclIVA" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="fechaDesdeTasaExclIVA" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="fechaHastaTasaExclIVA" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="numeroIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="jurisdiccionesIIBB">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="jurisdiccionIIBB" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="codigoJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="descripcionJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="codigioCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="tasaExclIIBB" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="fechaDesdeTasaExclIIBB" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="fechaHastaTasaExclIIBB" minOccurs="0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="agentePercepcionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="tasaExcl3337" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="fechaDesdePorcExcl3337" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="fechaHastaPorcExcl3337" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="domicioInternacional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="actividades">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="actividad" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="codigoActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="descripcionActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="numeroTelefonoReferencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="numeroFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="numeroTelefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="fechaActualizacionNroTelCelular" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="numeroTelefonoLaboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="emailPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="fechaActualizacionEmailPrincipal" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="emailAlternativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="marcaNoEnviarPublicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="prioridadDesconexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionPrioridadDesconexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="marcaClienteConParqueMatrix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCodigoAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/clienteDicc}agenciaNegocio"/>
     *         &lt;element name="agrupadorSegmentoNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="legados" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="legado" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="clienteId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="ofId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="resultado" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *                 &lt;attribute name="razon" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Cuenta {

        @XmlElement(required = true)
        protected String numeroCliente;
        protected String fechaAlta;
        protected String fechaActualiz;
        protected String codigoTipoCliente;
        protected String descripcionTipoCliente;
        protected String apellido;
        protected String nombre;
        protected String codigoGenero;
        protected String descripcionGenero;
        @XmlElement(required = true)
        protected ClDocumento clDocumento;
        protected String fechaNacimiento;
        protected String codigoSaludo;
        protected String descripcionSaludo;
        protected String codigoProfesion;
        protected String descripcionProfesion;
        protected String holdingFlag;
        protected String numeroHolding;
        protected String nombreHolding;
        protected String codigoEmpresaAsociada;
        protected String descripcionEmpresaAsociada;
        protected String razonSocial;
        protected String codigoCategoriaIVA;
        protected String descripcionCategoriaIVA;
        protected String tasaExclIVA;
        protected String fechaDesdeTasaExclIVA;
        protected String fechaHastaTasaExclIVA;
        protected String numeroIIBB;
        protected String codigoCondicionIIBB;
        protected String descripcionCondicionIIBB;
        @XmlElement(required = true)
        protected ClienteCRM.Cuenta.JurisdiccionesIIBB jurisdiccionesIIBB;
        protected String tasaExcl3337;
        protected String fechaDesdePorcExcl3337;
        protected String fechaHastaPorcExcl3337;
        protected String domicioInternacional;
        @XmlElement(required = true)
        protected ClienteCRM.Cuenta.Actividades actividades;
        @XmlElement(required = true)
        protected String numeroTelefonoReferencia;
        protected String numeroFax;
        protected String numeroTelefonoCelular;
        protected String fechaActualizacionNroTelCelular;
        protected String numeroTelefonoLaboral;
        protected String emailPrincipal;
        protected String fechaActualizacionEmailPrincipal;
        protected String emailAlternativo;
        protected String marcaNoEnviarPublicidad;
        protected String prioridadDesconexion;
        protected String descripcionPrioridadDesconexion;
        protected String marcaClienteConParqueMatrix;
        protected String codigoAutorizacion;
        protected String descripcionCodigoAutorizacion;
        @XmlElement(required = true)
        protected AgenciaNegocio agenciaNegocio;
        protected String agrupadorSegmentoNegocio;
        protected ClienteCRM.Cuenta.Legados legados;

        /**
         * Gets the value of the numeroCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroCliente() {
            return numeroCliente;
        }

        /**
         * Sets the value of the numeroCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroCliente(String value) {
            this.numeroCliente = value;
        }

        /**
         * Gets the value of the fechaAlta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaAlta() {
            return fechaAlta;
        }

        /**
         * Sets the value of the fechaAlta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaAlta(String value) {
            this.fechaAlta = value;
        }

        /**
         * Gets the value of the fechaActualiz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaActualiz() {
            return fechaActualiz;
        }

        /**
         * Sets the value of the fechaActualiz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaActualiz(String value) {
            this.fechaActualiz = value;
        }

        /**
         * Gets the value of the codigoTipoCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoTipoCliente() {
            return codigoTipoCliente;
        }

        /**
         * Sets the value of the codigoTipoCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoTipoCliente(String value) {
            this.codigoTipoCliente = value;
        }

        /**
         * Gets the value of the descripcionTipoCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionTipoCliente() {
            return descripcionTipoCliente;
        }

        /**
         * Sets the value of the descripcionTipoCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionTipoCliente(String value) {
            this.descripcionTipoCliente = value;
        }

        /**
         * Gets the value of the apellido property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApellido() {
            return apellido;
        }

        /**
         * Sets the value of the apellido property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApellido(String value) {
            this.apellido = value;
        }

        /**
         * Gets the value of the nombre property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Sets the value of the nombre property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Gets the value of the codigoGenero property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoGenero() {
            return codigoGenero;
        }

        /**
         * Sets the value of the codigoGenero property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoGenero(String value) {
            this.codigoGenero = value;
        }

        /**
         * Gets the value of the descripcionGenero property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionGenero() {
            return descripcionGenero;
        }

        /**
         * Sets the value of the descripcionGenero property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionGenero(String value) {
            this.descripcionGenero = value;
        }

        /**
         * Gets the value of the clDocumento property.
         * 
         * @return
         *     possible object is
         *     {@link ClDocumento }
         *     
         */
        public ClDocumento getClDocumento() {
            return clDocumento;
        }

        /**
         * Sets the value of the clDocumento property.
         * 
         * @param value
         *     allowed object is
         *     {@link ClDocumento }
         *     
         */
        public void setClDocumento(ClDocumento value) {
            this.clDocumento = value;
        }

        /**
         * Gets the value of the fechaNacimiento property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        /**
         * Sets the value of the fechaNacimiento property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaNacimiento(String value) {
            this.fechaNacimiento = value;
        }

        /**
         * Gets the value of the codigoSaludo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoSaludo() {
            return codigoSaludo;
        }

        /**
         * Sets the value of the codigoSaludo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoSaludo(String value) {
            this.codigoSaludo = value;
        }

        /**
         * Gets the value of the descripcionSaludo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionSaludo() {
            return descripcionSaludo;
        }

        /**
         * Sets the value of the descripcionSaludo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionSaludo(String value) {
            this.descripcionSaludo = value;
        }

        /**
         * Gets the value of the codigoProfesion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoProfesion() {
            return codigoProfesion;
        }

        /**
         * Sets the value of the codigoProfesion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoProfesion(String value) {
            this.codigoProfesion = value;
        }

        /**
         * Gets the value of the descripcionProfesion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionProfesion() {
            return descripcionProfesion;
        }

        /**
         * Sets the value of the descripcionProfesion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionProfesion(String value) {
            this.descripcionProfesion = value;
        }

        /**
         * Gets the value of the holdingFlag property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHoldingFlag() {
            return holdingFlag;
        }

        /**
         * Sets the value of the holdingFlag property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHoldingFlag(String value) {
            this.holdingFlag = value;
        }

        /**
         * Gets the value of the numeroHolding property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroHolding() {
            return numeroHolding;
        }

        /**
         * Sets the value of the numeroHolding property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroHolding(String value) {
            this.numeroHolding = value;
        }

        /**
         * Gets the value of the nombreHolding property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreHolding() {
            return nombreHolding;
        }

        /**
         * Sets the value of the nombreHolding property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreHolding(String value) {
            this.nombreHolding = value;
        }

        /**
         * Gets the value of the codigoEmpresaAsociada property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoEmpresaAsociada() {
            return codigoEmpresaAsociada;
        }

        /**
         * Sets the value of the codigoEmpresaAsociada property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoEmpresaAsociada(String value) {
            this.codigoEmpresaAsociada = value;
        }

        /**
         * Gets the value of the descripcionEmpresaAsociada property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionEmpresaAsociada() {
            return descripcionEmpresaAsociada;
        }

        /**
         * Sets the value of the descripcionEmpresaAsociada property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionEmpresaAsociada(String value) {
            this.descripcionEmpresaAsociada = value;
        }

        /**
         * Gets the value of the razonSocial property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRazonSocial() {
            return razonSocial;
        }

        /**
         * Sets the value of the razonSocial property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRazonSocial(String value) {
            this.razonSocial = value;
        }

        /**
         * Gets the value of the codigoCategoriaIVA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCategoriaIVA() {
            return codigoCategoriaIVA;
        }

        /**
         * Sets the value of the codigoCategoriaIVA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCategoriaIVA(String value) {
            this.codigoCategoriaIVA = value;
        }

        /**
         * Gets the value of the descripcionCategoriaIVA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCategoriaIVA() {
            return descripcionCategoriaIVA;
        }

        /**
         * Sets the value of the descripcionCategoriaIVA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCategoriaIVA(String value) {
            this.descripcionCategoriaIVA = value;
        }

        /**
         * Gets the value of the tasaExclIVA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTasaExclIVA() {
            return tasaExclIVA;
        }

        /**
         * Sets the value of the tasaExclIVA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTasaExclIVA(String value) {
            this.tasaExclIVA = value;
        }

        /**
         * Gets the value of the fechaDesdeTasaExclIVA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaDesdeTasaExclIVA() {
            return fechaDesdeTasaExclIVA;
        }

        /**
         * Sets the value of the fechaDesdeTasaExclIVA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaDesdeTasaExclIVA(String value) {
            this.fechaDesdeTasaExclIVA = value;
        }

        /**
         * Gets the value of the fechaHastaTasaExclIVA property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaHastaTasaExclIVA() {
            return fechaHastaTasaExclIVA;
        }

        /**
         * Sets the value of the fechaHastaTasaExclIVA property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaHastaTasaExclIVA(String value) {
            this.fechaHastaTasaExclIVA = value;
        }

        /**
         * Gets the value of the numeroIIBB property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroIIBB() {
            return numeroIIBB;
        }

        /**
         * Sets the value of the numeroIIBB property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroIIBB(String value) {
            this.numeroIIBB = value;
        }

        /**
         * Gets the value of the codigoCondicionIIBB property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCondicionIIBB() {
            return codigoCondicionIIBB;
        }

        /**
         * Sets the value of the codigoCondicionIIBB property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCondicionIIBB(String value) {
            this.codigoCondicionIIBB = value;
        }

        /**
         * Gets the value of the descripcionCondicionIIBB property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCondicionIIBB() {
            return descripcionCondicionIIBB;
        }

        /**
         * Sets the value of the descripcionCondicionIIBB property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCondicionIIBB(String value) {
            this.descripcionCondicionIIBB = value;
        }

        /**
         * Gets the value of the jurisdiccionesIIBB property.
         * 
         * @return
         *     possible object is
         *     {@link ClienteCRM.Cuenta.JurisdiccionesIIBB }
         *     
         */
        public ClienteCRM.Cuenta.JurisdiccionesIIBB getJurisdiccionesIIBB() {
            return jurisdiccionesIIBB;
        }

        /**
         * Sets the value of the jurisdiccionesIIBB property.
         * 
         * @param value
         *     allowed object is
         *     {@link ClienteCRM.Cuenta.JurisdiccionesIIBB }
         *     
         */
        public void setJurisdiccionesIIBB(ClienteCRM.Cuenta.JurisdiccionesIIBB value) {
            this.jurisdiccionesIIBB = value;
        }

        /**
         * Gets the value of the tasaExcl3337 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTasaExcl3337() {
            return tasaExcl3337;
        }

        /**
         * Sets the value of the tasaExcl3337 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTasaExcl3337(String value) {
            this.tasaExcl3337 = value;
        }

        /**
         * Gets the value of the fechaDesdePorcExcl3337 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaDesdePorcExcl3337() {
            return fechaDesdePorcExcl3337;
        }

        /**
         * Sets the value of the fechaDesdePorcExcl3337 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaDesdePorcExcl3337(String value) {
            this.fechaDesdePorcExcl3337 = value;
        }

        /**
         * Gets the value of the fechaHastaPorcExcl3337 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaHastaPorcExcl3337() {
            return fechaHastaPorcExcl3337;
        }

        /**
         * Sets the value of the fechaHastaPorcExcl3337 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaHastaPorcExcl3337(String value) {
            this.fechaHastaPorcExcl3337 = value;
        }

        /**
         * Gets the value of the domicioInternacional property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDomicioInternacional() {
            return domicioInternacional;
        }

        /**
         * Sets the value of the domicioInternacional property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDomicioInternacional(String value) {
            this.domicioInternacional = value;
        }

        /**
         * Gets the value of the actividades property.
         * 
         * @return
         *     possible object is
         *     {@link ClienteCRM.Cuenta.Actividades }
         *     
         */
        public ClienteCRM.Cuenta.Actividades getActividades() {
            return actividades;
        }

        /**
         * Sets the value of the actividades property.
         * 
         * @param value
         *     allowed object is
         *     {@link ClienteCRM.Cuenta.Actividades }
         *     
         */
        public void setActividades(ClienteCRM.Cuenta.Actividades value) {
            this.actividades = value;
        }

        /**
         * Gets the value of the numeroTelefonoReferencia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroTelefonoReferencia() {
            return numeroTelefonoReferencia;
        }

        /**
         * Sets the value of the numeroTelefonoReferencia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroTelefonoReferencia(String value) {
            this.numeroTelefonoReferencia = value;
        }

        /**
         * Gets the value of the numeroFax property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroFax() {
            return numeroFax;
        }

        /**
         * Sets the value of the numeroFax property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroFax(String value) {
            this.numeroFax = value;
        }

        /**
         * Gets the value of the numeroTelefonoCelular property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroTelefonoCelular() {
            return numeroTelefonoCelular;
        }

        /**
         * Sets the value of the numeroTelefonoCelular property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroTelefonoCelular(String value) {
            this.numeroTelefonoCelular = value;
        }

        /**
         * Gets the value of the fechaActualizacionNroTelCelular property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaActualizacionNroTelCelular() {
            return fechaActualizacionNroTelCelular;
        }

        /**
         * Sets the value of the fechaActualizacionNroTelCelular property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaActualizacionNroTelCelular(String value) {
            this.fechaActualizacionNroTelCelular = value;
        }

        /**
         * Gets the value of the numeroTelefonoLaboral property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroTelefonoLaboral() {
            return numeroTelefonoLaboral;
        }

        /**
         * Sets the value of the numeroTelefonoLaboral property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroTelefonoLaboral(String value) {
            this.numeroTelefonoLaboral = value;
        }

        /**
         * Gets the value of the emailPrincipal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmailPrincipal() {
            return emailPrincipal;
        }

        /**
         * Sets the value of the emailPrincipal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmailPrincipal(String value) {
            this.emailPrincipal = value;
        }

        /**
         * Gets the value of the fechaActualizacionEmailPrincipal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaActualizacionEmailPrincipal() {
            return fechaActualizacionEmailPrincipal;
        }

        /**
         * Sets the value of the fechaActualizacionEmailPrincipal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaActualizacionEmailPrincipal(String value) {
            this.fechaActualizacionEmailPrincipal = value;
        }

        /**
         * Gets the value of the emailAlternativo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmailAlternativo() {
            return emailAlternativo;
        }

        /**
         * Sets the value of the emailAlternativo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmailAlternativo(String value) {
            this.emailAlternativo = value;
        }

        /**
         * Gets the value of the marcaNoEnviarPublicidad property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarcaNoEnviarPublicidad() {
            return marcaNoEnviarPublicidad;
        }

        /**
         * Sets the value of the marcaNoEnviarPublicidad property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarcaNoEnviarPublicidad(String value) {
            this.marcaNoEnviarPublicidad = value;
        }

        /**
         * Gets the value of the prioridadDesconexion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrioridadDesconexion() {
            return prioridadDesconexion;
        }

        /**
         * Sets the value of the prioridadDesconexion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrioridadDesconexion(String value) {
            this.prioridadDesconexion = value;
        }

        /**
         * Gets the value of the descripcionPrioridadDesconexion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionPrioridadDesconexion() {
            return descripcionPrioridadDesconexion;
        }

        /**
         * Sets the value of the descripcionPrioridadDesconexion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionPrioridadDesconexion(String value) {
            this.descripcionPrioridadDesconexion = value;
        }

        /**
         * Gets the value of the marcaClienteConParqueMatrix property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarcaClienteConParqueMatrix() {
            return marcaClienteConParqueMatrix;
        }

        /**
         * Sets the value of the marcaClienteConParqueMatrix property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarcaClienteConParqueMatrix(String value) {
            this.marcaClienteConParqueMatrix = value;
        }

        /**
         * Gets the value of the codigoAutorizacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoAutorizacion() {
            return codigoAutorizacion;
        }

        /**
         * Sets the value of the codigoAutorizacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoAutorizacion(String value) {
            this.codigoAutorizacion = value;
        }

        /**
         * Gets the value of the descripcionCodigoAutorizacion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCodigoAutorizacion() {
            return descripcionCodigoAutorizacion;
        }

        /**
         * Sets the value of the descripcionCodigoAutorizacion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCodigoAutorizacion(String value) {
            this.descripcionCodigoAutorizacion = value;
        }

        /**
         * Gets the value of the agenciaNegocio property.
         * 
         * @return
         *     possible object is
         *     {@link AgenciaNegocio }
         *     
         */
        public AgenciaNegocio getAgenciaNegocio() {
            return agenciaNegocio;
        }

        /**
         * Sets the value of the agenciaNegocio property.
         * 
         * @param value
         *     allowed object is
         *     {@link AgenciaNegocio }
         *     
         */
        public void setAgenciaNegocio(AgenciaNegocio value) {
            this.agenciaNegocio = value;
        }

        /**
         * Gets the value of the agrupadorSegmentoNegocio property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAgrupadorSegmentoNegocio() {
            return agrupadorSegmentoNegocio;
        }

        /**
         * Sets the value of the agrupadorSegmentoNegocio property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAgrupadorSegmentoNegocio(String value) {
            this.agrupadorSegmentoNegocio = value;
        }

        /**
         * Gets the value of the legados property.
         * 
         * @return
         *     possible object is
         *     {@link ClienteCRM.Cuenta.Legados }
         *     
         */
        public ClienteCRM.Cuenta.Legados getLegados() {
            return legados;
        }

        /**
         * Sets the value of the legados property.
         * 
         * @param value
         *     allowed object is
         *     {@link ClienteCRM.Cuenta.Legados }
         *     
         */
        public void setLegados(ClienteCRM.Cuenta.Legados value) {
            this.legados = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="actividad" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="codigoActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="descripcionActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "actividad"
        })
        public static class Actividades {

            protected List<ClienteCRM.Cuenta.Actividades.Actividad> actividad;

            /**
             * Gets the value of the actividad property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the actividad property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getActividad().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ClienteCRM.Cuenta.Actividades.Actividad }
             * 
             * 
             */
            public List<ClienteCRM.Cuenta.Actividades.Actividad> getActividad() {
                if (actividad == null) {
                    actividad = new ArrayList<ClienteCRM.Cuenta.Actividades.Actividad>();
                }
                return this.actividad;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="codigoActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="descripcionActividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "codigoActividad",
                "descripcionActividad"
            })
            public static class Actividad {

                protected String codigoActividad;
                protected String descripcionActividad;

                /**
                 * Gets the value of the codigoActividad property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCodigoActividad() {
                    return codigoActividad;
                }

                /**
                 * Sets the value of the codigoActividad property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCodigoActividad(String value) {
                    this.codigoActividad = value;
                }

                /**
                 * Gets the value of the descripcionActividad property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescripcionActividad() {
                    return descripcionActividad;
                }

                /**
                 * Sets the value of the descripcionActividad property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescripcionActividad(String value) {
                    this.descripcionActividad = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="jurisdiccionIIBB" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="codigoJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="descripcionJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="codigioCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="tasaExclIIBB" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="fechaDesdeTasaExclIIBB" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="fechaHastaTasaExclIIBB" minOccurs="0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="agentePercepcionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "jurisdiccionIIBB"
        })
        public static class JurisdiccionesIIBB {

            protected List<ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB> jurisdiccionIIBB;

            /**
             * Gets the value of the jurisdiccionIIBB property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the jurisdiccionIIBB property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getJurisdiccionIIBB().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB }
             * 
             * 
             */
            public List<ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB> getJurisdiccionIIBB() {
                if (jurisdiccionIIBB == null) {
                    jurisdiccionIIBB = new ArrayList<ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB>();
                }
                return this.jurisdiccionIIBB;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="codigoJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="descripcionJurisdiccionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="codigioCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="descripcionCondicionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="tasaExclIIBB" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;pattern value="(([0-9])([0-9])([0-9])|([0-9])([0-9])|([0-9])),([0-9])([0-9])"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="fechaDesdeTasaExclIIBB" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="fechaHastaTasaExclIIBB" minOccurs="0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;pattern value="((19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3])([0-5]\d)([0-5]\d))?"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="agentePercepcionIIBB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "codigoJurisdiccionIIBB",
                "descripcionJurisdiccionIIBB",
                "codigioCondicionIIBB",
                "descripcionCondicionIIBB",
                "tasaExclIIBB",
                "fechaDesdeTasaExclIIBB",
                "fechaHastaTasaExclIIBB",
                "agentePercepcionIIBB"
            })
            public static class JurisdiccionIIBB {

                protected String codigoJurisdiccionIIBB;
                protected String descripcionJurisdiccionIIBB;
                protected String codigioCondicionIIBB;
                protected String descripcionCondicionIIBB;
                protected String tasaExclIIBB;
                protected String fechaDesdeTasaExclIIBB;
                protected String fechaHastaTasaExclIIBB;
                protected String agentePercepcionIIBB;

                /**
                 * Gets the value of the codigoJurisdiccionIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCodigoJurisdiccionIIBB() {
                    return codigoJurisdiccionIIBB;
                }

                /**
                 * Sets the value of the codigoJurisdiccionIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCodigoJurisdiccionIIBB(String value) {
                    this.codigoJurisdiccionIIBB = value;
                }

                /**
                 * Gets the value of the descripcionJurisdiccionIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescripcionJurisdiccionIIBB() {
                    return descripcionJurisdiccionIIBB;
                }

                /**
                 * Sets the value of the descripcionJurisdiccionIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescripcionJurisdiccionIIBB(String value) {
                    this.descripcionJurisdiccionIIBB = value;
                }

                /**
                 * Gets the value of the codigioCondicionIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCodigioCondicionIIBB() {
                    return codigioCondicionIIBB;
                }

                /**
                 * Sets the value of the codigioCondicionIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCodigioCondicionIIBB(String value) {
                    this.codigioCondicionIIBB = value;
                }

                /**
                 * Gets the value of the descripcionCondicionIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDescripcionCondicionIIBB() {
                    return descripcionCondicionIIBB;
                }

                /**
                 * Sets the value of the descripcionCondicionIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDescripcionCondicionIIBB(String value) {
                    this.descripcionCondicionIIBB = value;
                }

                /**
                 * Gets the value of the tasaExclIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTasaExclIIBB() {
                    return tasaExclIIBB;
                }

                /**
                 * Sets the value of the tasaExclIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTasaExclIIBB(String value) {
                    this.tasaExclIIBB = value;
                }

                /**
                 * Gets the value of the fechaDesdeTasaExclIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFechaDesdeTasaExclIIBB() {
                    return fechaDesdeTasaExclIIBB;
                }

                /**
                 * Sets the value of the fechaDesdeTasaExclIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFechaDesdeTasaExclIIBB(String value) {
                    this.fechaDesdeTasaExclIIBB = value;
                }

                /**
                 * Gets the value of the fechaHastaTasaExclIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFechaHastaTasaExclIIBB() {
                    return fechaHastaTasaExclIIBB;
                }

                /**
                 * Sets the value of the fechaHastaTasaExclIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFechaHastaTasaExclIIBB(String value) {
                    this.fechaHastaTasaExclIIBB = value;
                }

                /**
                 * Gets the value of the agentePercepcionIIBB property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAgentePercepcionIIBB() {
                    return agentePercepcionIIBB;
                }

                /**
                 * Sets the value of the agentePercepcionIIBB property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAgentePercepcionIIBB(String value) {
                    this.agentePercepcionIIBB = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="legado" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="clienteId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="ofId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="resultado" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
         *       &lt;attribute name="razon" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "legado"
        })
        public static class Legados {

            protected List<ClienteCRM.Cuenta.Legados.Legado> legado;
            @XmlAttribute(name = "resultado")
            @XmlSchemaType(name = "anySimpleType")
            protected String resultado;
            @XmlAttribute(name = "razon")
            @XmlSchemaType(name = "anySimpleType")
            protected String razon;

            /**
             * Gets the value of the legado property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the legado property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getLegado().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ClienteCRM.Cuenta.Legados.Legado }
             * 
             * 
             */
            public List<ClienteCRM.Cuenta.Legados.Legado> getLegado() {
                if (legado == null) {
                    legado = new ArrayList<ClienteCRM.Cuenta.Legados.Legado>();
                }
                return this.legado;
            }

            /**
             * Gets the value of the resultado property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResultado() {
                return resultado;
            }

            /**
             * Sets the value of the resultado property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResultado(String value) {
                this.resultado = value;
            }

            /**
             * Gets the value of the razon property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRazon() {
                return razon;
            }

            /**
             * Sets the value of the razon property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRazon(String value) {
                this.razon = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="clienteId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="ofId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Legado {

                @XmlAttribute(name = "id", required = true)
                protected String id;
                @XmlAttribute(name = "clienteId", required = true)
                protected String clienteId;
                @XmlAttribute(name = "ofId", required = true)
                protected String ofId;

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
                }

                /**
                 * Gets the value of the clienteId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getClienteId() {
                    return clienteId;
                }

                /**
                 * Sets the value of the clienteId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setClienteId(String value) {
                    this.clienteId = value;
                }

                /**
                 * Gets the value of the ofId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getOfId() {
                    return ofId;
                }

                /**
                 * Sets the value of the ofId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setOfId(String value) {
                    this.ofId = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="nombreCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codigoAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionAgenciaGestionaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCategoriaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCategClienteCompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoAgenciaCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcionCodigoAgenciaCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="marcaVIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombreCliente",
        "codigoAgenciaGestionaria",
        "descripcionAgenciaGestionaria",
        "codigoCategoriaCliente",
        "descripcionCategoriaCliente",
        "codigoCategClienteCompl",
        "descripcionCategClienteCompl",
        "codigoAgenciaCabecera",
        "descripcionCodigoAgenciaCabecera",
        "marcaVIP"
    })
    public static class Perfil {

        @XmlElement(required = true)
        protected String nombreCliente;
        protected String codigoAgenciaGestionaria;
        protected String descripcionAgenciaGestionaria;
        protected String codigoCategoriaCliente;
        protected String descripcionCategoriaCliente;
        protected String codigoCategClienteCompl;
        protected String descripcionCategClienteCompl;
        protected String codigoAgenciaCabecera;
        protected String descripcionCodigoAgenciaCabecera;
        protected String marcaVIP;

        /**
         * Gets the value of the nombreCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreCliente() {
            return nombreCliente;
        }

        /**
         * Sets the value of the nombreCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreCliente(String value) {
            this.nombreCliente = value;
        }

        /**
         * Gets the value of the codigoAgenciaGestionaria property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoAgenciaGestionaria() {
            return codigoAgenciaGestionaria;
        }

        /**
         * Sets the value of the codigoAgenciaGestionaria property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoAgenciaGestionaria(String value) {
            this.codigoAgenciaGestionaria = value;
        }

        /**
         * Gets the value of the descripcionAgenciaGestionaria property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionAgenciaGestionaria() {
            return descripcionAgenciaGestionaria;
        }

        /**
         * Sets the value of the descripcionAgenciaGestionaria property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionAgenciaGestionaria(String value) {
            this.descripcionAgenciaGestionaria = value;
        }

        /**
         * Gets the value of the codigoCategoriaCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCategoriaCliente() {
            return codigoCategoriaCliente;
        }

        /**
         * Sets the value of the codigoCategoriaCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCategoriaCliente(String value) {
            this.codigoCategoriaCliente = value;
        }

        /**
         * Gets the value of the descripcionCategoriaCliente property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCategoriaCliente() {
            return descripcionCategoriaCliente;
        }

        /**
         * Sets the value of the descripcionCategoriaCliente property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCategoriaCliente(String value) {
            this.descripcionCategoriaCliente = value;
        }

        /**
         * Gets the value of the codigoCategClienteCompl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoCategClienteCompl() {
            return codigoCategClienteCompl;
        }

        /**
         * Sets the value of the codigoCategClienteCompl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoCategClienteCompl(String value) {
            this.codigoCategClienteCompl = value;
        }

        /**
         * Gets the value of the descripcionCategClienteCompl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCategClienteCompl() {
            return descripcionCategClienteCompl;
        }

        /**
         * Sets the value of the descripcionCategClienteCompl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCategClienteCompl(String value) {
            this.descripcionCategClienteCompl = value;
        }

        /**
         * Gets the value of the codigoAgenciaCabecera property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoAgenciaCabecera() {
            return codigoAgenciaCabecera;
        }

        /**
         * Sets the value of the codigoAgenciaCabecera property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoAgenciaCabecera(String value) {
            this.codigoAgenciaCabecera = value;
        }

        /**
         * Gets the value of the descripcionCodigoAgenciaCabecera property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescripcionCodigoAgenciaCabecera() {
            return descripcionCodigoAgenciaCabecera;
        }

        /**
         * Sets the value of the descripcionCodigoAgenciaCabecera property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescripcionCodigoAgenciaCabecera(String value) {
            this.descripcionCodigoAgenciaCabecera = value;
        }

        /**
         * Gets the value of the marcaVIP property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMarcaVIP() {
            return marcaVIP;
        }

        /**
         * Sets the value of the marcaVIP property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMarcaVIP(String value) {
            this.marcaVIP = value;
        }

    }

}
