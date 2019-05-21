
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultaChequesEntrada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaChequesEntrada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bancos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cantidadARetornar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codigoBancoCobro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoEmpresaReceptora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCobroDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCobroHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importeDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importeHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeCheque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDeInvocacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimoRegistroProcesado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaChequesEntrada", namespace = "http://cheques.consulta.dto.ws.ice.teco.com.ar/xsd", propOrder = {
    "bancos",
    "cantidadARetornar",
    "clientes",
    "codigoBancoCobro",
    "codigoEmpresaReceptora",
    "fechaCobroDesde",
    "fechaCobroHasta",
    "importeDesde",
    "importeHasta",
    "numeroDeCheque",
    "password",
    "tipoDeInvocacion",
    "ultimoRegistroProcesado",
    "usuario"
})
public class ConsultaChequesEntrada {

    @XmlElement(nillable = true)
    protected List<String> bancos;
    @XmlElement(nillable = true)
    protected String cantidadARetornar;
    @XmlElement(nillable = true)
    protected List<String> clientes;
    @XmlElement(nillable = true)
    protected String codigoBancoCobro;
    @XmlElement(nillable = true)
    protected String codigoEmpresaReceptora;
    @XmlElement(nillable = true)
    protected String fechaCobroDesde;
    @XmlElement(nillable = true)
    protected String fechaCobroHasta;
    @XmlElement(nillable = true)
    protected String importeDesde;
    @XmlElement(nillable = true)
    protected String importeHasta;
    @XmlElement(nillable = true)
    protected String numeroDeCheque;
    @XmlElement(nillable = true)
    protected String password;
    @XmlElement(nillable = true)
    protected String tipoDeInvocacion;
    @XmlElement(nillable = true)
    protected String ultimoRegistroProcesado;
    @XmlElement(nillable = true)
    protected String usuario;

    /**
     * Gets the value of the bancos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bancos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBancos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBancos() {
        if (bancos == null) {
            bancos = new ArrayList<String>();
        }
        return this.bancos;
    }

    /**
     * Gets the value of the cantidadARetornar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadARetornar() {
        return cantidadARetornar;
    }

    /**
     * Sets the value of the cantidadARetornar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadARetornar(String value) {
        this.cantidadARetornar = value;
    }

    /**
     * Gets the value of the clientes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList<String>();
        }
        return this.clientes;
    }

    /**
     * Gets the value of the codigoBancoCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBancoCobro() {
        return codigoBancoCobro;
    }

    /**
     * Sets the value of the codigoBancoCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBancoCobro(String value) {
        this.codigoBancoCobro = value;
    }

    /**
     * Gets the value of the codigoEmpresaReceptora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEmpresaReceptora() {
        return codigoEmpresaReceptora;
    }

    /**
     * Sets the value of the codigoEmpresaReceptora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEmpresaReceptora(String value) {
        this.codigoEmpresaReceptora = value;
    }

    /**
     * Gets the value of the fechaCobroDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCobroDesde() {
        return fechaCobroDesde;
    }

    /**
     * Sets the value of the fechaCobroDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCobroDesde(String value) {
        this.fechaCobroDesde = value;
    }

    /**
     * Gets the value of the fechaCobroHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCobroHasta() {
        return fechaCobroHasta;
    }

    /**
     * Sets the value of the fechaCobroHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCobroHasta(String value) {
        this.fechaCobroHasta = value;
    }

    /**
     * Gets the value of the importeDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporteDesde() {
        return importeDesde;
    }

    /**
     * Sets the value of the importeDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporteDesde(String value) {
        this.importeDesde = value;
    }

    /**
     * Gets the value of the importeHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporteHasta() {
        return importeHasta;
    }

    /**
     * Sets the value of the importeHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporteHasta(String value) {
        this.importeHasta = value;
    }

    /**
     * Gets the value of the numeroDeCheque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeCheque() {
        return numeroDeCheque;
    }

    /**
     * Sets the value of the numeroDeCheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeCheque(String value) {
        this.numeroDeCheque = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the tipoDeInvocacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeInvocacion() {
        return tipoDeInvocacion;
    }

    /**
     * Sets the value of the tipoDeInvocacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeInvocacion(String value) {
        this.tipoDeInvocacion = value;
    }

    /**
     * Gets the value of the ultimoRegistroProcesado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimoRegistroProcesado() {
        return ultimoRegistroProcesado;
    }

    /**
     * Sets the value of the ultimoRegistroProcesado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimoRegistroProcesado(String value) {
        this.ultimoRegistroProcesado = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

}
