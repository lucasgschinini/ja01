
package ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cheque complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cheque">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bancoEmisorCheque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientes" type="{http://beans.ice.teco.com.ar/xsd}Cliente" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cobroRevertido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="codigoBancoDeCobro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoDeOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoDeTarea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoEmpresaReceptora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoEntidadGestora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoPostalSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comision" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="cpClienteCodigoBarras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDeAcreditacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDeCobro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDeVctoOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaPeticionReversion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaDePagoDelCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCheque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificadorDePago" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeAbsoluto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeBono" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeCheque" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeCobradoOReversar" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeComprobante" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeEfectivo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeOriginal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="importeOtrasMonedas" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="marcaDeAjuste" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marcaDePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreBancoDeCobro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreEntidadGestora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCheque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroChequeCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroConvenio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroCuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroDeAgencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeSucursalCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroLegal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroReferenciaMic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinciaDelCodBarras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recibo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referenciaDelComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sucursalBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoBono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoClienteCodigoBarras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDeCambio" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tipoDeFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDeLectura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDeOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDocumentoRelacionado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDocumentoRelacionadoTotfa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoOtrasMonedas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cheque", namespace = "http://beans.ice.teco.com.ar/xsd", propOrder = {
    "bancoEmisorCheque",
    "clientes",
    "cobroRevertido",
    "codigoBancoDeCobro",
    "codigoDeOperacion",
    "codigoDeTarea",
    "codigoEmpresaReceptora",
    "codigoEntidadGestora",
    "codigoPostalSucursal",
    "comision",
    "cpClienteCodigoBarras",
    "descripcionBanco",
    "fechaDeAcreditacion",
    "fechaDeCobro",
    "fechaDeVctoOriginal",
    "fechaPeticionReversion",
    "formaDePagoDelCliente",
    "idCheque",
    "identificadorDePago",
    "importeAbsoluto",
    "importeBono",
    "importeCheque",
    "importeCobradoOReversar",
    "importeComprobante",
    "importeEfectivo",
    "importeOriginal",
    "importeOtrasMonedas",
    "marcaDeAjuste",
    "marcaDePago",
    "moneda",
    "nombreBancoDeCobro",
    "nombreEntidadGestora",
    "numeroCheque",
    "numeroChequeCompleto",
    "numeroConvenio",
    "numeroCuota",
    "numeroDeAgencia",
    "numeroDeComprobante",
    "numeroDeSucursalCompleto",
    "numeroDeTarjeta",
    "numeroLegal",
    "numeroReferenciaMic",
    "provinciaDelCodBarras",
    "recibo",
    "referenciaDelComprobante",
    "sucursalBanco",
    "tipoBono",
    "tipoClienteCodigoBarras",
    "tipoComprobante",
    "tipoDeCambio",
    "tipoDeFacturacion",
    "tipoDeLectura",
    "tipoDeOperacion",
    "tipoDocumentoRelacionado",
    "tipoDocumentoRelacionadoTotfa",
    "tipoOtrasMonedas"
})
public class Cheque {

    @XmlElement(nillable = true)
    protected String bancoEmisorCheque;
    @XmlElement(nillable = true)
    protected List<Cliente> clientes;
    protected Boolean cobroRevertido;
    @XmlElement(nillable = true)
    protected String codigoBancoDeCobro;
    @XmlElement(nillable = true)
    protected String codigoDeOperacion;
    @XmlElement(nillable = true)
    protected String codigoDeTarea;
    @XmlElement(nillable = true)
    protected String codigoEmpresaReceptora;
    @XmlElement(nillable = true)
    protected String codigoEntidadGestora;
    @XmlElement(nillable = true)
    protected String codigoPostalSucursal;
    @XmlElement(nillable = true)
    protected Long comision;
    @XmlElement(nillable = true)
    protected String cpClienteCodigoBarras;
    @XmlElement(nillable = true)
    protected String descripcionBanco;
    @XmlElement(nillable = true)
    protected String fechaDeAcreditacion;
    @XmlElement(nillable = true)
    protected String fechaDeCobro;
    @XmlElement(nillable = true)
    protected String fechaDeVctoOriginal;
    @XmlElement(nillable = true)
    protected String fechaPeticionReversion;
    @XmlElement(nillable = true)
    protected String formaDePagoDelCliente;
    @XmlElement(nillable = true)
    protected String idCheque;
    @XmlElement(nillable = true)
    protected Long identificadorDePago;
    @XmlElement(nillable = true)
    protected Long importeAbsoluto;
    @XmlElement(nillable = true)
    protected Long importeBono;
    @XmlElement(nillable = true)
    protected Long importeCheque;
    @XmlElement(nillable = true)
    protected Long importeCobradoOReversar;
    @XmlElement(nillable = true)
    protected Long importeComprobante;
    @XmlElement(nillable = true)
    protected Long importeEfectivo;
    @XmlElement(nillable = true)
    protected Long importeOriginal;
    @XmlElement(nillable = true)
    protected Long importeOtrasMonedas;
    @XmlElement(nillable = true)
    protected String marcaDeAjuste;
    @XmlElement(nillable = true)
    protected String marcaDePago;
    @XmlElement(nillable = true)
    protected String moneda;
    @XmlElement(nillable = true)
    protected String nombreBancoDeCobro;
    @XmlElement(nillable = true)
    protected String nombreEntidadGestora;
    @XmlElement(nillable = true)
    protected String numeroCheque;
    @XmlElement(nillable = true)
    protected String numeroChequeCompleto;
    @XmlElement(nillable = true)
    protected Long numeroConvenio;
    @XmlElement(nillable = true)
    protected Long numeroCuota;
    @XmlElement(nillable = true)
    protected String numeroDeAgencia;
    @XmlElement(nillable = true)
    protected String numeroDeComprobante;
    @XmlElement(nillable = true)
    protected String numeroDeSucursalCompleto;
    @XmlElement(nillable = true)
    protected String numeroDeTarjeta;
    @XmlElement(nillable = true)
    protected String numeroLegal;
    @XmlElement(nillable = true)
    protected String numeroReferenciaMic;
    @XmlElement(nillable = true)
    protected String provinciaDelCodBarras;
    @XmlElement(nillable = true)
    protected String recibo;
    @XmlElement(nillable = true)
    protected String referenciaDelComprobante;
    @XmlElement(nillable = true)
    protected String sucursalBanco;
    @XmlElement(nillable = true)
    protected String tipoBono;
    @XmlElement(nillable = true)
    protected String tipoClienteCodigoBarras;
    @XmlElement(nillable = true)
    protected String tipoComprobante;
    @XmlElement(nillable = true)
    protected Long tipoDeCambio;
    @XmlElement(nillable = true)
    protected String tipoDeFacturacion;
    @XmlElement(nillable = true)
    protected String tipoDeLectura;
    @XmlElement(nillable = true)
    protected String tipoDeOperacion;
    @XmlElement(nillable = true)
    protected String tipoDocumentoRelacionado;
    @XmlElement(nillable = true)
    protected String tipoDocumentoRelacionadoTotfa;
    @XmlElement(nillable = true)
    protected String tipoOtrasMonedas;

    /**
     * Gets the value of the bancoEmisorCheque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBancoEmisorCheque() {
        return bancoEmisorCheque;
    }

    /**
     * Sets the value of the bancoEmisorCheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBancoEmisorCheque(String value) {
        this.bancoEmisorCheque = value;
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
     * {@link Cliente }
     * 
     * 
     */
    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList<Cliente>();
        }
        return this.clientes;
    }

    /**
     * Gets the value of the cobroRevertido property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCobroRevertido() {
        return cobroRevertido;
    }

    /**
     * Sets the value of the cobroRevertido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCobroRevertido(Boolean value) {
        this.cobroRevertido = value;
    }

    /**
     * Gets the value of the codigoBancoDeCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBancoDeCobro() {
        return codigoBancoDeCobro;
    }

    /**
     * Sets the value of the codigoBancoDeCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBancoDeCobro(String value) {
        this.codigoBancoDeCobro = value;
    }

    /**
     * Gets the value of the codigoDeOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDeOperacion() {
        return codigoDeOperacion;
    }

    /**
     * Sets the value of the codigoDeOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDeOperacion(String value) {
        this.codigoDeOperacion = value;
    }

    /**
     * Gets the value of the codigoDeTarea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDeTarea() {
        return codigoDeTarea;
    }

    /**
     * Sets the value of the codigoDeTarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDeTarea(String value) {
        this.codigoDeTarea = value;
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
     * Gets the value of the codigoEntidadGestora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEntidadGestora() {
        return codigoEntidadGestora;
    }

    /**
     * Sets the value of the codigoEntidadGestora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEntidadGestora(String value) {
        this.codigoEntidadGestora = value;
    }

    /**
     * Gets the value of the codigoPostalSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPostalSucursal() {
        return codigoPostalSucursal;
    }

    /**
     * Sets the value of the codigoPostalSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPostalSucursal(String value) {
        this.codigoPostalSucursal = value;
    }

    /**
     * Gets the value of the comision property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getComision() {
        return comision;
    }

    /**
     * Sets the value of the comision property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setComision(Long value) {
        this.comision = value;
    }

    /**
     * Gets the value of the cpClienteCodigoBarras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpClienteCodigoBarras() {
        return cpClienteCodigoBarras;
    }

    /**
     * Sets the value of the cpClienteCodigoBarras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpClienteCodigoBarras(String value) {
        this.cpClienteCodigoBarras = value;
    }

    /**
     * Gets the value of the descripcionBanco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionBanco() {
        return descripcionBanco;
    }

    /**
     * Sets the value of the descripcionBanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionBanco(String value) {
        this.descripcionBanco = value;
    }

    /**
     * Gets the value of the fechaDeAcreditacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeAcreditacion() {
        return fechaDeAcreditacion;
    }

    /**
     * Sets the value of the fechaDeAcreditacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeAcreditacion(String value) {
        this.fechaDeAcreditacion = value;
    }

    /**
     * Gets the value of the fechaDeCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeCobro() {
        return fechaDeCobro;
    }

    /**
     * Sets the value of the fechaDeCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeCobro(String value) {
        this.fechaDeCobro = value;
    }

    /**
     * Gets the value of the fechaDeVctoOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeVctoOriginal() {
        return fechaDeVctoOriginal;
    }

    /**
     * Sets the value of the fechaDeVctoOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeVctoOriginal(String value) {
        this.fechaDeVctoOriginal = value;
    }

    /**
     * Gets the value of the fechaPeticionReversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPeticionReversion() {
        return fechaPeticionReversion;
    }

    /**
     * Sets the value of the fechaPeticionReversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPeticionReversion(String value) {
        this.fechaPeticionReversion = value;
    }

    /**
     * Gets the value of the formaDePagoDelCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaDePagoDelCliente() {
        return formaDePagoDelCliente;
    }

    /**
     * Sets the value of the formaDePagoDelCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaDePagoDelCliente(String value) {
        this.formaDePagoDelCliente = value;
    }

    /**
     * Gets the value of the idCheque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCheque() {
        return idCheque;
    }

    /**
     * Sets the value of the idCheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCheque(String value) {
        this.idCheque = value;
    }

    /**
     * Gets the value of the identificadorDePago property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdentificadorDePago() {
        return identificadorDePago;
    }

    /**
     * Sets the value of the identificadorDePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdentificadorDePago(Long value) {
        this.identificadorDePago = value;
    }

    /**
     * Gets the value of the importeAbsoluto property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteAbsoluto() {
        return importeAbsoluto;
    }

    /**
     * Sets the value of the importeAbsoluto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteAbsoluto(Long value) {
        this.importeAbsoluto = value;
    }

    /**
     * Gets the value of the importeBono property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteBono() {
        return importeBono;
    }

    /**
     * Sets the value of the importeBono property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteBono(Long value) {
        this.importeBono = value;
    }

    /**
     * Gets the value of the importeCheque property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteCheque() {
        return importeCheque;
    }

    /**
     * Sets the value of the importeCheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteCheque(Long value) {
        this.importeCheque = value;
    }

    /**
     * Gets the value of the importeCobradoOReversar property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteCobradoOReversar() {
        return importeCobradoOReversar;
    }

    /**
     * Sets the value of the importeCobradoOReversar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteCobradoOReversar(Long value) {
        this.importeCobradoOReversar = value;
    }

    /**
     * Gets the value of the importeComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteComprobante() {
        return importeComprobante;
    }

    /**
     * Sets the value of the importeComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteComprobante(Long value) {
        this.importeComprobante = value;
    }

    /**
     * Gets the value of the importeEfectivo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteEfectivo() {
        return importeEfectivo;
    }

    /**
     * Sets the value of the importeEfectivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteEfectivo(Long value) {
        this.importeEfectivo = value;
    }

    /**
     * Gets the value of the importeOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteOriginal() {
        return importeOriginal;
    }

    /**
     * Sets the value of the importeOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteOriginal(Long value) {
        this.importeOriginal = value;
    }

    /**
     * Gets the value of the importeOtrasMonedas property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getImporteOtrasMonedas() {
        return importeOtrasMonedas;
    }

    /**
     * Sets the value of the importeOtrasMonedas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setImporteOtrasMonedas(Long value) {
        this.importeOtrasMonedas = value;
    }

    /**
     * Gets the value of the marcaDeAjuste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaDeAjuste() {
        return marcaDeAjuste;
    }

    /**
     * Sets the value of the marcaDeAjuste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaDeAjuste(String value) {
        this.marcaDeAjuste = value;
    }

    /**
     * Gets the value of the marcaDePago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaDePago() {
        return marcaDePago;
    }

    /**
     * Sets the value of the marcaDePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaDePago(String value) {
        this.marcaDePago = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the nombreBancoDeCobro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreBancoDeCobro() {
        return nombreBancoDeCobro;
    }

    /**
     * Sets the value of the nombreBancoDeCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreBancoDeCobro(String value) {
        this.nombreBancoDeCobro = value;
    }

    /**
     * Gets the value of the nombreEntidadGestora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEntidadGestora() {
        return nombreEntidadGestora;
    }

    /**
     * Sets the value of the nombreEntidadGestora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEntidadGestora(String value) {
        this.nombreEntidadGestora = value;
    }

    /**
     * Gets the value of the numeroCheque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * Sets the value of the numeroCheque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCheque(String value) {
        this.numeroCheque = value;
    }

    /**
     * Gets the value of the numeroChequeCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroChequeCompleto() {
        return numeroChequeCompleto;
    }

    /**
     * Sets the value of the numeroChequeCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroChequeCompleto(String value) {
        this.numeroChequeCompleto = value;
    }

    /**
     * Gets the value of the numeroConvenio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroConvenio() {
        return numeroConvenio;
    }

    /**
     * Sets the value of the numeroConvenio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroConvenio(Long value) {
        this.numeroConvenio = value;
    }

    /**
     * Gets the value of the numeroCuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroCuota() {
        return numeroCuota;
    }

    /**
     * Sets the value of the numeroCuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroCuota(Long value) {
        this.numeroCuota = value;
    }

    /**
     * Gets the value of the numeroDeAgencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeAgencia() {
        return numeroDeAgencia;
    }

    /**
     * Sets the value of the numeroDeAgencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeAgencia(String value) {
        this.numeroDeAgencia = value;
    }

    /**
     * Gets the value of the numeroDeComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeComprobante() {
        return numeroDeComprobante;
    }

    /**
     * Sets the value of the numeroDeComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeComprobante(String value) {
        this.numeroDeComprobante = value;
    }

    /**
     * Gets the value of the numeroDeSucursalCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeSucursalCompleto() {
        return numeroDeSucursalCompleto;
    }

    /**
     * Sets the value of the numeroDeSucursalCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeSucursalCompleto(String value) {
        this.numeroDeSucursalCompleto = value;
    }

    /**
     * Gets the value of the numeroDeTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeTarjeta() {
        return numeroDeTarjeta;
    }

    /**
     * Sets the value of the numeroDeTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeTarjeta(String value) {
        this.numeroDeTarjeta = value;
    }

    /**
     * Gets the value of the numeroLegal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLegal() {
        return numeroLegal;
    }

    /**
     * Sets the value of the numeroLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLegal(String value) {
        this.numeroLegal = value;
    }

    /**
     * Gets the value of the numeroReferenciaMic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroReferenciaMic() {
        return numeroReferenciaMic;
    }

    /**
     * Sets the value of the numeroReferenciaMic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroReferenciaMic(String value) {
        this.numeroReferenciaMic = value;
    }

    /**
     * Gets the value of the provinciaDelCodBarras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaDelCodBarras() {
        return provinciaDelCodBarras;
    }

    /**
     * Sets the value of the provinciaDelCodBarras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaDelCodBarras(String value) {
        this.provinciaDelCodBarras = value;
    }

    /**
     * Gets the value of the recibo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecibo() {
        return recibo;
    }

    /**
     * Sets the value of the recibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecibo(String value) {
        this.recibo = value;
    }

    /**
     * Gets the value of the referenciaDelComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaDelComprobante() {
        return referenciaDelComprobante;
    }

    /**
     * Sets the value of the referenciaDelComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaDelComprobante(String value) {
        this.referenciaDelComprobante = value;
    }

    /**
     * Gets the value of the sucursalBanco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursalBanco() {
        return sucursalBanco;
    }

    /**
     * Sets the value of the sucursalBanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursalBanco(String value) {
        this.sucursalBanco = value;
    }

    /**
     * Gets the value of the tipoBono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBono() {
        return tipoBono;
    }

    /**
     * Sets the value of the tipoBono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBono(String value) {
        this.tipoBono = value;
    }

    /**
     * Gets the value of the tipoClienteCodigoBarras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoClienteCodigoBarras() {
        return tipoClienteCodigoBarras;
    }

    /**
     * Sets the value of the tipoClienteCodigoBarras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoClienteCodigoBarras(String value) {
        this.tipoClienteCodigoBarras = value;
    }

    /**
     * Gets the value of the tipoComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the value of the tipoComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoComprobante(String value) {
        this.tipoComprobante = value;
    }

    /**
     * Gets the value of the tipoDeCambio property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTipoDeCambio() {
        return tipoDeCambio;
    }

    /**
     * Sets the value of the tipoDeCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTipoDeCambio(Long value) {
        this.tipoDeCambio = value;
    }

    /**
     * Gets the value of the tipoDeFacturacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeFacturacion() {
        return tipoDeFacturacion;
    }

    /**
     * Sets the value of the tipoDeFacturacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeFacturacion(String value) {
        this.tipoDeFacturacion = value;
    }

    /**
     * Gets the value of the tipoDeLectura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeLectura() {
        return tipoDeLectura;
    }

    /**
     * Sets the value of the tipoDeLectura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeLectura(String value) {
        this.tipoDeLectura = value;
    }

    /**
     * Gets the value of the tipoDeOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeOperacion() {
        return tipoDeOperacion;
    }

    /**
     * Sets the value of the tipoDeOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeOperacion(String value) {
        this.tipoDeOperacion = value;
    }

    /**
     * Gets the value of the tipoDocumentoRelacionado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumentoRelacionado() {
        return tipoDocumentoRelacionado;
    }

    /**
     * Sets the value of the tipoDocumentoRelacionado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumentoRelacionado(String value) {
        this.tipoDocumentoRelacionado = value;
    }

    /**
     * Gets the value of the tipoDocumentoRelacionadoTotfa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumentoRelacionadoTotfa() {
        return tipoDocumentoRelacionadoTotfa;
    }

    /**
     * Sets the value of the tipoDocumentoRelacionadoTotfa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumentoRelacionadoTotfa(String value) {
        this.tipoDocumentoRelacionadoTotfa = value;
    }

    /**
     * Gets the value of the tipoOtrasMonedas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOtrasMonedas() {
        return tipoOtrasMonedas;
    }

    /**
     * Sets the value of the tipoOtrasMonedas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOtrasMonedas(String value) {
        this.tipoOtrasMonedas = value;
    }

}
