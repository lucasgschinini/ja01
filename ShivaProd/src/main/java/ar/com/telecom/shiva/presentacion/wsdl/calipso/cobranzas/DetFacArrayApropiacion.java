
package ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * <p>Java class for detFacArrayApropiacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="detFacArrayApropiacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocctascob">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="idDocumentoCuentasCobranza" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoMora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="algoritmoMora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeBonificacionIntereses" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="acuerdo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoAcumuladoSimulacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="accionSobreDiferenciaDeCambio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detFacArrayApropiacion", propOrder = {
    "idDocctascob",
    "idDocumentoCuentasCobranza",
    "saldo",
    "tipoOperacion",
    "tipoMora",
    "algoritmoMora",
    "importeBonificacionIntereses",
    "acuerdo",
    "montoAcumuladoSimulacion",
    "accionSobreDiferenciaDeCambio"
})
public class DetFacArrayApropiacion {

    @XmlElement(required = true)
    protected DetFacArrayApropiacion.IdDocctascob idDocctascob;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    @XmlSchemaType(name = "integer")
    protected BigInteger idDocumentoCuentasCobranza;
    @XmlElement(required = true)
    protected BigDecimal saldo;
    @XmlElement(required = true)
    protected String tipoOperacion;
    @XmlElement(required = true)
    protected String tipoMora;
    @XmlElement(required = true)
    protected String algoritmoMora;
    @XmlElement(required = true)
    protected BigDecimal importeBonificacionIntereses;
    @XmlElement(required = true)
    protected String acuerdo;
    @XmlElement(required = true)
    protected BigDecimal montoAcumuladoSimulacion;
    @XmlElement(required = true)
    protected String accionSobreDiferenciaDeCambio;

    /**
     * Gets the value of the idDocctascob property.
     * 
     * @return
     *     possible object is
     *     {@link DetFacArrayApropiacion.IdDocctascob }
     *     
     */
    public DetFacArrayApropiacion.IdDocctascob getIdDocctascob() {
        return idDocctascob;
    }

    /**
     * Sets the value of the idDocctascob property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetFacArrayApropiacion.IdDocctascob }
     *     
     */
    public void setIdDocctascob(DetFacArrayApropiacion.IdDocctascob value) {
        this.idDocctascob = value;
    }

    /**
     * Gets the value of the idDocumentoCuentasCobranza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigInteger getIdDocumentoCuentasCobranza() {
        return idDocumentoCuentasCobranza;
    }

    /**
     * Sets the value of the idDocumentoCuentasCobranza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoCuentasCobranza(BigInteger value) {
        this.idDocumentoCuentasCobranza = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSaldo(BigDecimal value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the tipoMora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMora() {
        return tipoMora;
    }

    /**
     * Sets the value of the tipoMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMora(String value) {
        this.tipoMora = value;
    }

    /**
     * Gets the value of the algoritmoMora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgoritmoMora() {
        return algoritmoMora;
    }

    /**
     * Sets the value of the algoritmoMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgoritmoMora(String value) {
        this.algoritmoMora = value;
    }

    /**
     * Gets the value of the importeBonificacionIntereses property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteBonificacionIntereses() {
        return importeBonificacionIntereses;
    }

    /**
     * Sets the value of the importeBonificacionIntereses property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteBonificacionIntereses(BigDecimal value) {
        this.importeBonificacionIntereses = value;
    }

    /**
     * Gets the value of the acuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcuerdo() {
        return acuerdo;
    }

    /**
     * Sets the value of the acuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcuerdo(String value) {
        this.acuerdo = value;
    }

    /**
     * Gets the value of the montoAcumuladoSimulacion property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoAcumuladoSimulacion() {
        return montoAcumuladoSimulacion;
    }

    /**
     * Sets the value of the montoAcumuladoSimulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoAcumuladoSimulacion(BigDecimal value) {
        this.montoAcumuladoSimulacion = value;
    }

    /**
     * Gets the value of the accionSobreDiferenciaDeCambio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccionSobreDiferenciaDeCambio() {
        return accionSobreDiferenciaDeCambio;
    }

    /**
     * Sets the value of the accionSobreDiferenciaDeCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccionSobreDiferenciaDeCambio(String value) {
        this.accionSobreDiferenciaDeCambio = value;
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
     *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="clase" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "tipo",
        "clase",
        "sucursal",
        "numero"
    })
    public static class IdDocctascob {

        @XmlElement(required = true)
        protected String tipo;
        @XmlElement(required = true)
        protected String clase;
        @XmlElement(required = true)
        protected String sucursal;
        @XmlElement(required = true)
        protected String numero;

        /**
         * Gets the value of the tipo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * Sets the value of the tipo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipo(String value) {
            this.tipo = value;
        }

        /**
         * Gets the value of the clase property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getClase() {
            return clase;
        }

        /**
         * Sets the value of the clase property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setClase(String value) {
            this.clase = value;
        }

        /**
         * Gets the value of the sucursal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSucursal() {
            return sucursal;
        }

        /**
         * Sets the value of the sucursal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSucursal(String value) {
            this.sucursal = value;
        }

        /**
         * Gets the value of the numero property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumero() {
            return numero;
        }

        /**
         * Sets the value of the numero property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumero(String value) {
            this.numero = value;
        }

    }

}
