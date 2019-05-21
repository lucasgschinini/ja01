
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="identificador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="utilizadorRazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoLinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prioridadReparacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidadHilos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoInstalacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoModeloInstalacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCabecera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categAbono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="abonoComplementario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCumplimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorLineaTemporaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaTarifaBasica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivoMorosidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operadorLargaDistancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVigenciaCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaVigenciaCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puntoInterconexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claseServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroPartidaBsAs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operadorLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="horaVigencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incomunicador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productoLogicoDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productoLogicoHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categAbonoLargaDistancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rubro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tituloEnGuia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoEnGuia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreEnGuia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreComercial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCarga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineaAsociada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descAgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNodo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descNodo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plienteUTP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaPresuscripcionProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaSuscripcionProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaBajaProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInicioFactProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productosCortesia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productosGrandesClientes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productoPrincipalID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cupIDAsociado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipFija" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incomunicaciones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="incomunicacion" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}comentario" minOccurs="0"/>
 *                           &lt;/all>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="rehabilitaciones" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="rehabilitacion" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
 *                           &lt;/all>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
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
@XmlRootElement(name = "datosComercialesParque")
public class DatosComercialesParque {

    protected String identificador;
    protected String utilizadorRazonSocial;
    protected String tipoLinea;
    protected String prioridadReparacion;
    protected String cantidadHilos;
    protected String tipoInstalacion;
    protected String codigoModeloInstalacion;
    protected String numeroCabecera;
    protected String cliente;
    protected String categAbono;
    protected String abonoComplementario;
    protected String fechaCumplimiento;
    @XmlElement(name = "IndicadorLineaTemporaria")
    protected String indicadorLineaTemporaria;
    protected String areaTarifaBasica;
    protected String motivoMorosidad;
    protected String operadorLargaDistancia;
    protected String fechaVigenciaCarrier;
    protected String horaVigenciaCarrier;
    protected String origen;
    protected String puntoInterconexion;
    protected String claseServicio;
    protected String numeroPartidaBsAs;
    protected String tipoProducto;
    protected String operadorLocal;
    protected String horaVigencia;
    protected String incomunicador;
    protected String productoLogicoDesde;
    protected String productoLogicoHasta;
    protected String categAbonoLargaDistancia;
    protected String rubro;
    protected String tituloEnGuia;
    protected String apellidoEnGuia;
    protected String nombreEnGuia;
    protected String nombreComercial;
    protected String fechaCarga;
    protected String lineaAsociada;
    protected String idAgente;
    protected String descAgente;
    protected String idNodo;
    protected String descNodo;
    protected String plienteUTP;
    protected String fechaPresuscripcionProducto;
    protected String fechaSuscripcionProducto;
    protected String fechaBajaProducto;
    protected String fechaInicioFactProducto;
    protected String productosCortesia;
    protected String productosGrandesClientes;
    protected String productoPrincipalID;
    protected String cupIDAsociado;
    protected String ipFija;
    protected DatosComercialesParque.Incomunicaciones incomunicaciones;
    protected DatosComercialesParque.Rehabilitaciones rehabilitaciones;

    /**
     * Gets the value of the identificador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Gets the value of the utilizadorRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtilizadorRazonSocial() {
        return utilizadorRazonSocial;
    }

    /**
     * Sets the value of the utilizadorRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtilizadorRazonSocial(String value) {
        this.utilizadorRazonSocial = value;
    }

    /**
     * Gets the value of the tipoLinea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoLinea() {
        return tipoLinea;
    }

    /**
     * Sets the value of the tipoLinea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoLinea(String value) {
        this.tipoLinea = value;
    }

    /**
     * Gets the value of the prioridadReparacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrioridadReparacion() {
        return prioridadReparacion;
    }

    /**
     * Sets the value of the prioridadReparacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrioridadReparacion(String value) {
        this.prioridadReparacion = value;
    }

    /**
     * Gets the value of the cantidadHilos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadHilos() {
        return cantidadHilos;
    }

    /**
     * Sets the value of the cantidadHilos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadHilos(String value) {
        this.cantidadHilos = value;
    }

    /**
     * Gets the value of the tipoInstalacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoInstalacion() {
        return tipoInstalacion;
    }

    /**
     * Sets the value of the tipoInstalacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoInstalacion(String value) {
        this.tipoInstalacion = value;
    }

    /**
     * Gets the value of the codigoModeloInstalacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoModeloInstalacion() {
        return codigoModeloInstalacion;
    }

    /**
     * Sets the value of the codigoModeloInstalacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoModeloInstalacion(String value) {
        this.codigoModeloInstalacion = value;
    }

    /**
     * Gets the value of the numeroCabecera property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCabecera() {
        return numeroCabecera;
    }

    /**
     * Sets the value of the numeroCabecera property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCabecera(String value) {
        this.numeroCabecera = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the categAbono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategAbono() {
        return categAbono;
    }

    /**
     * Sets the value of the categAbono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategAbono(String value) {
        this.categAbono = value;
    }

    /**
     * Gets the value of the abonoComplementario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbonoComplementario() {
        return abonoComplementario;
    }

    /**
     * Sets the value of the abonoComplementario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbonoComplementario(String value) {
        this.abonoComplementario = value;
    }

    /**
     * Gets the value of the fechaCumplimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    /**
     * Sets the value of the fechaCumplimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCumplimiento(String value) {
        this.fechaCumplimiento = value;
    }

    /**
     * Gets the value of the indicadorLineaTemporaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndicadorLineaTemporaria() {
        return indicadorLineaTemporaria;
    }

    /**
     * Sets the value of the indicadorLineaTemporaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndicadorLineaTemporaria(String value) {
        this.indicadorLineaTemporaria = value;
    }

    /**
     * Gets the value of the areaTarifaBasica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaTarifaBasica() {
        return areaTarifaBasica;
    }

    /**
     * Sets the value of the areaTarifaBasica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaTarifaBasica(String value) {
        this.areaTarifaBasica = value;
    }

    /**
     * Gets the value of the motivoMorosidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoMorosidad() {
        return motivoMorosidad;
    }

    /**
     * Sets the value of the motivoMorosidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoMorosidad(String value) {
        this.motivoMorosidad = value;
    }

    /**
     * Gets the value of the operadorLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperadorLargaDistancia() {
        return operadorLargaDistancia;
    }

    /**
     * Sets the value of the operadorLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperadorLargaDistancia(String value) {
        this.operadorLargaDistancia = value;
    }

    /**
     * Gets the value of the fechaVigenciaCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVigenciaCarrier() {
        return fechaVigenciaCarrier;
    }

    /**
     * Sets the value of the fechaVigenciaCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVigenciaCarrier(String value) {
        this.fechaVigenciaCarrier = value;
    }

    /**
     * Gets the value of the horaVigenciaCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraVigenciaCarrier() {
        return horaVigenciaCarrier;
    }

    /**
     * Sets the value of the horaVigenciaCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraVigenciaCarrier(String value) {
        this.horaVigenciaCarrier = value;
    }

    /**
     * Gets the value of the origen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Sets the value of the origen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Gets the value of the puntoInterconexion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuntoInterconexion() {
        return puntoInterconexion;
    }

    /**
     * Sets the value of the puntoInterconexion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuntoInterconexion(String value) {
        this.puntoInterconexion = value;
    }

    /**
     * Gets the value of the claseServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseServicio() {
        return claseServicio;
    }

    /**
     * Sets the value of the claseServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseServicio(String value) {
        this.claseServicio = value;
    }

    /**
     * Gets the value of the numeroPartidaBsAs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPartidaBsAs() {
        return numeroPartidaBsAs;
    }

    /**
     * Sets the value of the numeroPartidaBsAs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPartidaBsAs(String value) {
        this.numeroPartidaBsAs = value;
    }

    /**
     * Gets the value of the tipoProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoProducto() {
        return tipoProducto;
    }

    /**
     * Sets the value of the tipoProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoProducto(String value) {
        this.tipoProducto = value;
    }

    /**
     * Gets the value of the operadorLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperadorLocal() {
        return operadorLocal;
    }

    /**
     * Sets the value of the operadorLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperadorLocal(String value) {
        this.operadorLocal = value;
    }

    /**
     * Gets the value of the horaVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraVigencia() {
        return horaVigencia;
    }

    /**
     * Sets the value of the horaVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraVigencia(String value) {
        this.horaVigencia = value;
    }

    /**
     * Gets the value of the incomunicador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomunicador() {
        return incomunicador;
    }

    /**
     * Sets the value of the incomunicador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomunicador(String value) {
        this.incomunicador = value;
    }

    /**
     * Gets the value of the productoLogicoDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoLogicoDesde() {
        return productoLogicoDesde;
    }

    /**
     * Sets the value of the productoLogicoDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoLogicoDesde(String value) {
        this.productoLogicoDesde = value;
    }

    /**
     * Gets the value of the productoLogicoHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoLogicoHasta() {
        return productoLogicoHasta;
    }

    /**
     * Sets the value of the productoLogicoHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoLogicoHasta(String value) {
        this.productoLogicoHasta = value;
    }

    /**
     * Gets the value of the categAbonoLargaDistancia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategAbonoLargaDistancia() {
        return categAbonoLargaDistancia;
    }

    /**
     * Sets the value of the categAbonoLargaDistancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategAbonoLargaDistancia(String value) {
        this.categAbonoLargaDistancia = value;
    }

    /**
     * Gets the value of the rubro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRubro() {
        return rubro;
    }

    /**
     * Sets the value of the rubro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRubro(String value) {
        this.rubro = value;
    }

    /**
     * Gets the value of the tituloEnGuia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloEnGuia() {
        return tituloEnGuia;
    }

    /**
     * Sets the value of the tituloEnGuia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloEnGuia(String value) {
        this.tituloEnGuia = value;
    }

    /**
     * Gets the value of the apellidoEnGuia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoEnGuia() {
        return apellidoEnGuia;
    }

    /**
     * Sets the value of the apellidoEnGuia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoEnGuia(String value) {
        this.apellidoEnGuia = value;
    }

    /**
     * Gets the value of the nombreEnGuia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEnGuia() {
        return nombreEnGuia;
    }

    /**
     * Sets the value of the nombreEnGuia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEnGuia(String value) {
        this.nombreEnGuia = value;
    }

    /**
     * Gets the value of the nombreComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Sets the value of the nombreComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComercial(String value) {
        this.nombreComercial = value;
    }

    /**
     * Gets the value of the fechaCarga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaCarga() {
        return fechaCarga;
    }

    /**
     * Sets the value of the fechaCarga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaCarga(String value) {
        this.fechaCarga = value;
    }

    /**
     * Gets the value of the lineaAsociada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaAsociada() {
        return lineaAsociada;
    }

    /**
     * Sets the value of the lineaAsociada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaAsociada(String value) {
        this.lineaAsociada = value;
    }

    /**
     * Gets the value of the idAgente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAgente() {
        return idAgente;
    }

    /**
     * Sets the value of the idAgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAgente(String value) {
        this.idAgente = value;
    }

    /**
     * Gets the value of the descAgente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescAgente() {
        return descAgente;
    }

    /**
     * Sets the value of the descAgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescAgente(String value) {
        this.descAgente = value;
    }

    /**
     * Gets the value of the idNodo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNodo() {
        return idNodo;
    }

    /**
     * Sets the value of the idNodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNodo(String value) {
        this.idNodo = value;
    }

    /**
     * Gets the value of the descNodo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNodo() {
        return descNodo;
    }

    /**
     * Sets the value of the descNodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNodo(String value) {
        this.descNodo = value;
    }

    /**
     * Gets the value of the plienteUTP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlienteUTP() {
        return plienteUTP;
    }

    /**
     * Sets the value of the plienteUTP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlienteUTP(String value) {
        this.plienteUTP = value;
    }

    /**
     * Gets the value of the fechaPresuscripcionProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPresuscripcionProducto() {
        return fechaPresuscripcionProducto;
    }

    /**
     * Sets the value of the fechaPresuscripcionProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPresuscripcionProducto(String value) {
        this.fechaPresuscripcionProducto = value;
    }

    /**
     * Gets the value of the fechaSuscripcionProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSuscripcionProducto() {
        return fechaSuscripcionProducto;
    }

    /**
     * Sets the value of the fechaSuscripcionProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSuscripcionProducto(String value) {
        this.fechaSuscripcionProducto = value;
    }

    /**
     * Gets the value of the fechaBajaProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaBajaProducto() {
        return fechaBajaProducto;
    }

    /**
     * Sets the value of the fechaBajaProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaBajaProducto(String value) {
        this.fechaBajaProducto = value;
    }

    /**
     * Gets the value of the fechaInicioFactProducto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioFactProducto() {
        return fechaInicioFactProducto;
    }

    /**
     * Sets the value of the fechaInicioFactProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioFactProducto(String value) {
        this.fechaInicioFactProducto = value;
    }

    /**
     * Gets the value of the productosCortesia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductosCortesia() {
        return productosCortesia;
    }

    /**
     * Sets the value of the productosCortesia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductosCortesia(String value) {
        this.productosCortesia = value;
    }

    /**
     * Gets the value of the productosGrandesClientes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductosGrandesClientes() {
        return productosGrandesClientes;
    }

    /**
     * Sets the value of the productosGrandesClientes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductosGrandesClientes(String value) {
        this.productosGrandesClientes = value;
    }

    /**
     * Gets the value of the productoPrincipalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoPrincipalID() {
        return productoPrincipalID;
    }

    /**
     * Sets the value of the productoPrincipalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoPrincipalID(String value) {
        this.productoPrincipalID = value;
    }

    /**
     * Gets the value of the cupIDAsociado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCupIDAsociado() {
        return cupIDAsociado;
    }

    /**
     * Sets the value of the cupIDAsociado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCupIDAsociado(String value) {
        this.cupIDAsociado = value;
    }

    /**
     * Gets the value of the ipFija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpFija() {
        return ipFija;
    }

    /**
     * Sets the value of the ipFija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpFija(String value) {
        this.ipFija = value;
    }

    /**
     * Gets the value of the incomunicaciones property.
     * 
     * @return
     *     possible object is
     *     {@link DatosComercialesParque.Incomunicaciones }
     *     
     */
    public DatosComercialesParque.Incomunicaciones getIncomunicaciones() {
        return incomunicaciones;
    }

    /**
     * Sets the value of the incomunicaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosComercialesParque.Incomunicaciones }
     *     
     */
    public void setIncomunicaciones(DatosComercialesParque.Incomunicaciones value) {
        this.incomunicaciones = value;
    }

    /**
     * Gets the value of the rehabilitaciones property.
     * 
     * @return
     *     possible object is
     *     {@link DatosComercialesParque.Rehabilitaciones }
     *     
     */
    public DatosComercialesParque.Rehabilitaciones getRehabilitaciones() {
        return rehabilitaciones;
    }

    /**
     * Sets the value of the rehabilitaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosComercialesParque.Rehabilitaciones }
     *     
     */
    public void setRehabilitaciones(DatosComercialesParque.Rehabilitaciones value) {
        this.rehabilitaciones = value;
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
     *         &lt;element name="incomunicacion" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}comentario" minOccurs="0"/>
     *                 &lt;/all>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "incomunicacion"
    })
    public static class Incomunicaciones {

        protected List<DatosComercialesParque.Incomunicaciones.Incomunicacion> incomunicacion;

        /**
         * Gets the value of the incomunicacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the incomunicacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIncomunicacion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatosComercialesParque.Incomunicaciones.Incomunicacion }
         * 
         * 
         */
        public List<DatosComercialesParque.Incomunicaciones.Incomunicacion> getIncomunicacion() {
            if (incomunicacion == null) {
                incomunicacion = new ArrayList<DatosComercialesParque.Incomunicaciones.Incomunicacion>();
            }
            return this.incomunicacion;
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
         *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}descripcion" minOccurs="0"/>
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}comentario" minOccurs="0"/>
         *       &lt;/all>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        public static class Incomunicacion {

            @XmlElement(required = true)
            protected String motivo;
            protected String fecha;
            protected String descripcion;
            protected String comentario;
            @XmlAttribute(name = "id")
            protected String id;

            /**
             * Gets the value of the motivo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMotivo() {
                return motivo;
            }

            /**
             * Sets the value of the motivo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMotivo(String value) {
                this.motivo = value;
            }

            /**
             * Gets the value of the fecha property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFecha() {
                return fecha;
            }

            /**
             * Sets the value of the fecha property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFecha(String value) {
                this.fecha = value;
            }

            /**
             * Gets the value of the descripcion property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescripcion() {
                return descripcion;
            }

            /**
             * Sets the value of the descripcion property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescripcion(String value) {
                this.descripcion = value;
            }

            /**
             * Gets the value of the comentario property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getComentario() {
                return comentario;
            }

            /**
             * Sets the value of the comentario property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setComentario(String value) {
                this.comentario = value;
            }

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
     *         &lt;element name="rehabilitacion" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
     *                 &lt;/all>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "rehabilitacion"
    })
    public static class Rehabilitaciones {

        protected List<DatosComercialesParque.Rehabilitaciones.Rehabilitacion> rehabilitacion;

        /**
         * Gets the value of the rehabilitacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rehabilitacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRehabilitacion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DatosComercialesParque.Rehabilitaciones.Rehabilitacion }
         * 
         * 
         */
        public List<DatosComercialesParque.Rehabilitaciones.Rehabilitacion> getRehabilitacion() {
            if (rehabilitacion == null) {
                rehabilitacion = new ArrayList<DatosComercialesParque.Rehabilitaciones.Rehabilitacion>();
            }
            return this.rehabilitacion;
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
         *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/common}fecha" minOccurs="0"/>
         *       &lt;/all>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        public static class Rehabilitacion {

            protected String fecha;
            @XmlAttribute(name = "id")
            protected String id;

            /**
             * Gets the value of the fecha property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFecha() {
                return fecha;
            }

            /**
             * Sets the value of the fecha property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFecha(String value) {
                this.fecha = value;
            }

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

        }

    }

}
