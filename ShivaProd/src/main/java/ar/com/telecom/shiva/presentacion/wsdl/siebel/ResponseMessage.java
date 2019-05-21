
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.response.HeaderResponse;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/responseHeader}header"/>
 *         &lt;element name="body">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}asociarIDsClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}oficinasComercialesDeUnClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}notificarReplicaClienteEnLegadoResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}novedadAltaClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}novedadModificacionClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}resultadoActualizacionClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarFactibilidadInactivarClienteEnLegadoResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}inactivarClienteEnLegadosResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}resultadoInactivacionClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/errorMessage}error"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}crearCRMIdClienteResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}publicarNovedadClienteDiariaResp"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarMorosidadClienteResp"/>
 *                 &lt;/choice>
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
    "header",
    "body"
})
@XmlRootElement(name = "responseMessage", namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
public class ResponseMessage {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/responseHeader", required = true)
    protected HeaderResponse header;
    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage", required = true)
    protected ResponseMessage.Body body;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse }
     *     
     */
    public HeaderResponse getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse }
     *     
     */
    public void setHeader(HeaderResponse value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseMessage.Body }
     *     
     */
    public ResponseMessage.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMessage.Body }
     *     
     */
    public void setBody(ResponseMessage.Body value) {
        this.body = value;
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
     *       &lt;choice>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}asociarIDsClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}oficinasComercialesDeUnClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}notificarReplicaClienteEnLegadoResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}novedadAltaClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}novedadModificacionClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}resultadoActualizacionClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarFactibilidadInactivarClienteEnLegadoResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}inactivarClienteEnLegadosResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}resultadoInactivacionClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/errorMessage}error"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}crearCRMIdClienteResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}publicarNovedadClienteDiariaResp"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage}consultarMorosidadClienteResp"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "asociarIDsClienteResp",
        "oficinasComercialesDeUnClienteResp",
        "consultarClienteResp",
        "notificarReplicaClienteEnLegadoResp",
        "novedadAltaClienteResp",
        "novedadModificacionClienteResp",
        "resultadoActualizacionClienteResp",
        "consultarFactibilidadInactivarClienteEnLegadoResp",
        "inactivarClienteEnLegadosResp",
        "resultadoInactivacionClienteResp",
        "error",
        "crearCRMIdClienteResp",
        "publicarNovedadClienteDiariaResp",
        "consultarMorosidadClienteResp"
    })
    public static class Body {

        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected AsociarIDsClienteResp asociarIDsClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected OficinasComercialesDeUnClienteResp oficinasComercialesDeUnClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected ConsultarClienteResp consultarClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected NotificarReplicaClienteEnLegadoResp notificarReplicaClienteEnLegadoResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected NovedadAltaClienteResp novedadAltaClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected NovedadModificacionClienteResp novedadModificacionClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected ResultadoActualizacionClienteResp resultadoActualizacionClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected ConsultarFactibilidadInactivarClienteEnLegadoResp consultarFactibilidadInactivarClienteEnLegadoResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected InactivarClienteEnLegadosResp inactivarClienteEnLegadosResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected ResultadoInactivacionClienteResp resultadoInactivacionClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/errorMessage")
        protected Error error;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected CrearCRMIdClienteResp crearCRMIdClienteResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected PublicarNovedadClienteDiariaResp publicarNovedadClienteDiariaResp;
        @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/cliente/responseMessage")
        protected ConsultarMorosidadClienteResp consultarMorosidadClienteResp;

        /**
         * Gets the value of the asociarIDsClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link AsociarIDsClienteResp }
         *     
         */
        public AsociarIDsClienteResp getAsociarIDsClienteResp() {
            return asociarIDsClienteResp;
        }

        /**
         * Sets the value of the asociarIDsClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link AsociarIDsClienteResp }
         *     
         */
        public void setAsociarIDsClienteResp(AsociarIDsClienteResp value) {
            this.asociarIDsClienteResp = value;
        }

        /**
         * Gets the value of the oficinasComercialesDeUnClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link OficinasComercialesDeUnClienteResp }
         *     
         */
        public OficinasComercialesDeUnClienteResp getOficinasComercialesDeUnClienteResp() {
            return oficinasComercialesDeUnClienteResp;
        }

        /**
         * Sets the value of the oficinasComercialesDeUnClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link OficinasComercialesDeUnClienteResp }
         *     
         */
        public void setOficinasComercialesDeUnClienteResp(OficinasComercialesDeUnClienteResp value) {
            this.oficinasComercialesDeUnClienteResp = value;
        }

        /**
         * Gets the value of the consultarClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarClienteResp }
         *     
         */
        public ConsultarClienteResp getConsultarClienteResp() {
            return consultarClienteResp;
        }

        /**
         * Sets the value of the consultarClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarClienteResp }
         *     
         */
        public void setConsultarClienteResp(ConsultarClienteResp value) {
            this.consultarClienteResp = value;
        }

        /**
         * Gets the value of the notificarReplicaClienteEnLegadoResp property.
         * 
         * @return
         *     possible object is
         *     {@link NotificarReplicaClienteEnLegadoResp }
         *     
         */
        public NotificarReplicaClienteEnLegadoResp getNotificarReplicaClienteEnLegadoResp() {
            return notificarReplicaClienteEnLegadoResp;
        }

        /**
         * Sets the value of the notificarReplicaClienteEnLegadoResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link NotificarReplicaClienteEnLegadoResp }
         *     
         */
        public void setNotificarReplicaClienteEnLegadoResp(NotificarReplicaClienteEnLegadoResp value) {
            this.notificarReplicaClienteEnLegadoResp = value;
        }

        /**
         * Gets the value of the novedadAltaClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link NovedadAltaClienteResp }
         *     
         */
        public NovedadAltaClienteResp getNovedadAltaClienteResp() {
            return novedadAltaClienteResp;
        }

        /**
         * Sets the value of the novedadAltaClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link NovedadAltaClienteResp }
         *     
         */
        public void setNovedadAltaClienteResp(NovedadAltaClienteResp value) {
            this.novedadAltaClienteResp = value;
        }

        /**
         * Gets the value of the novedadModificacionClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link NovedadModificacionClienteResp }
         *     
         */
        public NovedadModificacionClienteResp getNovedadModificacionClienteResp() {
            return novedadModificacionClienteResp;
        }

        /**
         * Sets the value of the novedadModificacionClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link NovedadModificacionClienteResp }
         *     
         */
        public void setNovedadModificacionClienteResp(NovedadModificacionClienteResp value) {
            this.novedadModificacionClienteResp = value;
        }

        /**
         * Gets the value of the resultadoActualizacionClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link ResultadoActualizacionClienteResp }
         *     
         */
        public ResultadoActualizacionClienteResp getResultadoActualizacionClienteResp() {
            return resultadoActualizacionClienteResp;
        }

        /**
         * Sets the value of the resultadoActualizacionClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ResultadoActualizacionClienteResp }
         *     
         */
        public void setResultadoActualizacionClienteResp(ResultadoActualizacionClienteResp value) {
            this.resultadoActualizacionClienteResp = value;
        }

        /**
         * Gets the value of the consultarFactibilidadInactivarClienteEnLegadoResp property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarFactibilidadInactivarClienteEnLegadoResp }
         *     
         */
        public ConsultarFactibilidadInactivarClienteEnLegadoResp getConsultarFactibilidadInactivarClienteEnLegadoResp() {
            return consultarFactibilidadInactivarClienteEnLegadoResp;
        }

        /**
         * Sets the value of the consultarFactibilidadInactivarClienteEnLegadoResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarFactibilidadInactivarClienteEnLegadoResp }
         *     
         */
        public void setConsultarFactibilidadInactivarClienteEnLegadoResp(ConsultarFactibilidadInactivarClienteEnLegadoResp value) {
            this.consultarFactibilidadInactivarClienteEnLegadoResp = value;
        }

        /**
         * Gets the value of the inactivarClienteEnLegadosResp property.
         * 
         * @return
         *     possible object is
         *     {@link InactivarClienteEnLegadosResp }
         *     
         */
        public InactivarClienteEnLegadosResp getInactivarClienteEnLegadosResp() {
            return inactivarClienteEnLegadosResp;
        }

        /**
         * Sets the value of the inactivarClienteEnLegadosResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link InactivarClienteEnLegadosResp }
         *     
         */
        public void setInactivarClienteEnLegadosResp(InactivarClienteEnLegadosResp value) {
            this.inactivarClienteEnLegadosResp = value;
        }

        /**
         * Gets the value of the resultadoInactivacionClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link ResultadoInactivacionClienteResp }
         *     
         */
        public ResultadoInactivacionClienteResp getResultadoInactivacionClienteResp() {
            return resultadoInactivacionClienteResp;
        }

        /**
         * Sets the value of the resultadoInactivacionClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ResultadoInactivacionClienteResp }
         *     
         */
        public void setResultadoInactivacionClienteResp(ResultadoInactivacionClienteResp value) {
            this.resultadoInactivacionClienteResp = value;
        }

        /**
         * Gets the value of the error property.
         * 
         * @return
         *     possible object is
         *     {@link Error }
         *     
         */
        public Error getError() {
            return error;
        }

        /**
         * Sets the value of the error property.
         * 
         * @param value
         *     allowed object is
         *     {@link Error }
         *     
         */
        public void setError(Error value) {
            this.error = value;
        }

        /**
         * Gets the value of the crearCRMIdClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link CrearCRMIdClienteResp }
         *     
         */
        public CrearCRMIdClienteResp getCrearCRMIdClienteResp() {
            return crearCRMIdClienteResp;
        }

        /**
         * Sets the value of the crearCRMIdClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link CrearCRMIdClienteResp }
         *     
         */
        public void setCrearCRMIdClienteResp(CrearCRMIdClienteResp value) {
            this.crearCRMIdClienteResp = value;
        }

        /**
         * Gets the value of the publicarNovedadClienteDiariaResp property.
         * 
         * @return
         *     possible object is
         *     {@link PublicarNovedadClienteDiariaResp }
         *     
         */
        public PublicarNovedadClienteDiariaResp getPublicarNovedadClienteDiariaResp() {
            return publicarNovedadClienteDiariaResp;
        }

        /**
         * Sets the value of the publicarNovedadClienteDiariaResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link PublicarNovedadClienteDiariaResp }
         *     
         */
        public void setPublicarNovedadClienteDiariaResp(PublicarNovedadClienteDiariaResp value) {
            this.publicarNovedadClienteDiariaResp = value;
        }

        /**
         * Gets the value of the consultarMorosidadClienteResp property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarMorosidadClienteResp }
         *     
         */
        public ConsultarMorosidadClienteResp getConsultarMorosidadClienteResp() {
            return consultarMorosidadClienteResp;
        }

        /**
         * Sets the value of the consultarMorosidadClienteResp property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarMorosidadClienteResp }
         *     
         */
        public void setConsultarMorosidadClienteResp(ConsultarMorosidadClienteResp value) {
            this.consultarMorosidadClienteResp = value;
        }

    }

}
