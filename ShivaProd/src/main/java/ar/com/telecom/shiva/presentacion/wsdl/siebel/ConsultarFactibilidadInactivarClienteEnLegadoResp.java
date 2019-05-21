
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

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
 *         &lt;element name="resultado">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="crmClienteID" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlRootElement(name = "consultarFactibilidadInactivarClienteEnLegadoResp", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class ConsultarFactibilidadInactivarClienteEnLegadoResp {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage", required = true)
    protected ConsultarFactibilidadInactivarClienteEnLegadoResp.Resultado resultado;
    @XmlAttribute(name = "crmClienteID")
    protected String crmClienteID;

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarFactibilidadInactivarClienteEnLegadoResp.Resultado }
     *     
     */
    public ConsultarFactibilidadInactivarClienteEnLegadoResp.Resultado getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarFactibilidadInactivarClienteEnLegadoResp.Resultado }
     *     
     */
    public void setResultado(ConsultarFactibilidadInactivarClienteEnLegadoResp.Resultado value) {
        this.resultado = value;
    }

    /**
     * Gets the value of the crmClienteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmClienteID() {
        return crmClienteID;
    }

    /**
     * Sets the value of the crmClienteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmClienteID(String value) {
        this.crmClienteID = value;
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
     *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    public static class Resultado {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage", required = true)
        protected String estado;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected String codigoError;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected String descripcion;

        /**
         * Gets the value of the estado property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstado() {
            return estado;
        }

        /**
         * Sets the value of the estado property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstado(String value) {
            this.estado = value;
        }

        /**
         * Gets the value of the codigoError property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoError() {
            return codigoError;
        }

        /**
         * Sets the value of the codigoError property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoError(String value) {
            this.codigoError = value;
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

    }

}
