
package ar.com.telecom.shiva.presentacion.wsdl.siebel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.request.HeaderRequest;


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
 *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/requestHeader}header"/>
 *         &lt;element name="body">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}asociarIDsClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}asociarIDsClientesReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}oficinasComercialesDeUnClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}notificarReplicaClienteEnLegadoReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}novedadAltaClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}novedadModificacionClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}resultadoActualizacionClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarFactibilidadInactivarClienteEnLegadoReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}inactivarClienteEnLegadosReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}resultadoInactivacionClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}crearCRMIdClienteReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}publicarNovedadClienteDiariaReq"/>
 *                   &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarMorosidadClienteReq"/>
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
@XmlRootElement(name = "requestMessage")
public class RequestMessage {

    @XmlElement(namespace = "http://www.telecom.com.ar/XMLSchema/bios/requestHeader", required = true)
    protected HeaderRequest header;
    @XmlElement(required = true)
    protected RequestMessage.Body body;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderRequest }
     *     
     */
    public HeaderRequest getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderRequest }
     *     
     */
    public void setHeader(HeaderRequest value) {
        this.header = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link RequestMessage.Body }
     *     
     */
    public RequestMessage.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMessage.Body }
     *     
     */
    public void setBody(RequestMessage.Body value) {
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
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}asociarIDsClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}asociarIDsClientesReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}oficinasComercialesDeUnClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}notificarReplicaClienteEnLegadoReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}novedadAltaClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}novedadModificacionClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}resultadoActualizacionClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarFactibilidadInactivarClienteEnLegadoReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}inactivarClienteEnLegadosReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}resultadoInactivacionClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}crearCRMIdClienteReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}publicarNovedadClienteDiariaReq"/>
     *         &lt;element ref="{http://www.telecom.com.ar/XMLSchema/bios/cliente/requestMessage}consultarMorosidadClienteReq"/>
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
        "asociarIDsClienteReq",
        "asociarIDsClientesReq",
        "oficinasComercialesDeUnClienteReq",
        "consultarClienteReq",
        "notificarReplicaClienteEnLegadoReq",
        "novedadAltaClienteReq",
        "novedadModificacionClienteReq",
        "resultadoActualizacionClienteReq",
        "consultarFactibilidadInactivarClienteEnLegadoReq",
        "inactivarClienteEnLegadosReq",
        "resultadoInactivacionClienteReq",
        "crearCRMIdClienteReq",
        "publicarNovedadClienteDiariaReq",
        "consultarMorosidadClienteReq"
    })
    public static class Body {

        protected AsociarIDsClienteReq asociarIDsClienteReq;
        protected AsociarIDsClientesReq asociarIDsClientesReq;
        protected OficinasComercialesDeUnClienteReq oficinasComercialesDeUnClienteReq;
        protected ConsultarClienteReq consultarClienteReq;
        protected NotificarReplicaClienteEnLegadoReq notificarReplicaClienteEnLegadoReq;
        protected NovedadAltaClienteReq novedadAltaClienteReq;
        protected NovedadModificacionClienteReq novedadModificacionClienteReq;
        protected ResultadoActualizacionClienteReq resultadoActualizacionClienteReq;
        protected ConsultarFactibilidadInactivarClienteEnLegadoReq consultarFactibilidadInactivarClienteEnLegadoReq;
        protected InactivarClienteEnLegadosReq inactivarClienteEnLegadosReq;
        protected ResultadoInactivacionClienteReq resultadoInactivacionClienteReq;
        protected CrearCRMIdClienteReq crearCRMIdClienteReq;
        protected PublicarNovedadClienteDiariaReq publicarNovedadClienteDiariaReq;
        protected ConsultarMorosidadClienteReq consultarMorosidadClienteReq;

        /**
         * Servicio de asociación de IDs de Cliente entre Legados y CRM-Siebel y viceversa
         * 
         * @return
         *     possible object is
         *     {@link AsociarIDsClienteReq }
         *     
         */
        public AsociarIDsClienteReq getAsociarIDsClienteReq() {
            return asociarIDsClienteReq;
        }

        /**
         * Sets the value of the asociarIDsClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link AsociarIDsClienteReq }
         *     
         */
        public void setAsociarIDsClienteReq(AsociarIDsClienteReq value) {
            this.asociarIDsClienteReq = value;
        }

        /**
         * Servicio de asociación de IDs de varios Cliente y sus Legados
         * 
         * @return
         *     possible object is
         *     {@link AsociarIDsClientesReq }
         *     
         */
        public AsociarIDsClientesReq getAsociarIDsClientesReq() {
            return asociarIDsClientesReq;
        }

        /**
         * Sets the value of the asociarIDsClientesReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link AsociarIDsClientesReq }
         *     
         */
        public void setAsociarIDsClientesReq(AsociarIDsClientesReq value) {
            this.asociarIDsClientesReq = value;
        }

        /**
         * Servicio de Consulta de las Oficinas Comerciales de un Cliente ( Legado o CRM-Siebel )
         * 
         * @return
         *     possible object is
         *     {@link OficinasComercialesDeUnClienteReq }
         *     
         */
        public OficinasComercialesDeUnClienteReq getOficinasComercialesDeUnClienteReq() {
            return oficinasComercialesDeUnClienteReq;
        }

        /**
         * Sets the value of the oficinasComercialesDeUnClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link OficinasComercialesDeUnClienteReq }
         *     
         */
        public void setOficinasComercialesDeUnClienteReq(OficinasComercialesDeUnClienteReq value) {
            this.oficinasComercialesDeUnClienteReq = value;
        }

        /**
         * Servicio de Consulta Cliente en CRM-Siebel invocado por Legados, etc.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarClienteReq }
         *     
         */
        public ConsultarClienteReq getConsultarClienteReq() {
            return consultarClienteReq;
        }

        /**
         * Sets the value of the consultarClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarClienteReq }
         *     
         */
        public void setConsultarClienteReq(ConsultarClienteReq value) {
            this.consultarClienteReq = value;
        }

        /**
         * Legado Notifica haber Replicado el Cliente Siebel en su Sistema
         * 
         * @return
         *     possible object is
         *     {@link NotificarReplicaClienteEnLegadoReq }
         *     
         */
        public NotificarReplicaClienteEnLegadoReq getNotificarReplicaClienteEnLegadoReq() {
            return notificarReplicaClienteEnLegadoReq;
        }

        /**
         * Sets the value of the notificarReplicaClienteEnLegadoReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link NotificarReplicaClienteEnLegadoReq }
         *     
         */
        public void setNotificarReplicaClienteEnLegadoReq(NotificarReplicaClienteEnLegadoReq value) {
            this.notificarReplicaClienteEnLegadoReq = value;
        }

        /**
         * Servicio Novedad de Alta Cliente invocado por CRM-Siebel
         * 
         * @return
         *     possible object is
         *     {@link NovedadAltaClienteReq }
         *     
         */
        public NovedadAltaClienteReq getNovedadAltaClienteReq() {
            return novedadAltaClienteReq;
        }

        /**
         * Sets the value of the novedadAltaClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link NovedadAltaClienteReq }
         *     
         */
        public void setNovedadAltaClienteReq(NovedadAltaClienteReq value) {
            this.novedadAltaClienteReq = value;
        }

        /**
         * Servicio Novedad de Modificacion Cliente invocado por CRM-Siebel
         * 
         * @return
         *     possible object is
         *     {@link NovedadModificacionClienteReq }
         *     
         */
        public NovedadModificacionClienteReq getNovedadModificacionClienteReq() {
            return novedadModificacionClienteReq;
        }

        /**
         * Sets the value of the novedadModificacionClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link NovedadModificacionClienteReq }
         *     
         */
        public void setNovedadModificacionClienteReq(NovedadModificacionClienteReq value) {
            this.novedadModificacionClienteReq = value;
        }

        /**
         * Notificación de Sistema Legado de la Actualización de datos de un Cliente
         * 
         * @return
         *     possible object is
         *     {@link ResultadoActualizacionClienteReq }
         *     
         */
        public ResultadoActualizacionClienteReq getResultadoActualizacionClienteReq() {
            return resultadoActualizacionClienteReq;
        }

        /**
         * Sets the value of the resultadoActualizacionClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link ResultadoActualizacionClienteReq }
         *     
         */
        public void setResultadoActualizacionClienteReq(ResultadoActualizacionClienteReq value) {
            this.resultadoActualizacionClienteReq = value;
        }

        /**
         * Gets the value of the consultarFactibilidadInactivarClienteEnLegadoReq property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarFactibilidadInactivarClienteEnLegadoReq }
         *     
         */
        public ConsultarFactibilidadInactivarClienteEnLegadoReq getConsultarFactibilidadInactivarClienteEnLegadoReq() {
            return consultarFactibilidadInactivarClienteEnLegadoReq;
        }

        /**
         * Sets the value of the consultarFactibilidadInactivarClienteEnLegadoReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarFactibilidadInactivarClienteEnLegadoReq }
         *     
         */
        public void setConsultarFactibilidadInactivarClienteEnLegadoReq(ConsultarFactibilidadInactivarClienteEnLegadoReq value) {
            this.consultarFactibilidadInactivarClienteEnLegadoReq = value;
        }

        /**
         * Se inactiva un Cliente en los Legados indicados
         * 
         * @return
         *     possible object is
         *     {@link InactivarClienteEnLegadosReq }
         *     
         */
        public InactivarClienteEnLegadosReq getInactivarClienteEnLegadosReq() {
            return inactivarClienteEnLegadosReq;
        }

        /**
         * Sets the value of the inactivarClienteEnLegadosReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link InactivarClienteEnLegadosReq }
         *     
         */
        public void setInactivarClienteEnLegadosReq(InactivarClienteEnLegadosReq value) {
            this.inactivarClienteEnLegadosReq = value;
        }

        /**
         * Notificación de Sistema Legado de la Inactivación de un Cliente
         * 
         * @return
         *     possible object is
         *     {@link ResultadoInactivacionClienteReq }
         *     
         */
        public ResultadoInactivacionClienteReq getResultadoInactivacionClienteReq() {
            return resultadoInactivacionClienteReq;
        }

        /**
         * Sets the value of the resultadoInactivacionClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link ResultadoInactivacionClienteReq }
         *     
         */
        public void setResultadoInactivacionClienteReq(ResultadoInactivacionClienteReq value) {
            this.resultadoInactivacionClienteReq = value;
        }

        /**
         * Gets the value of the crearCRMIdClienteReq property.
         * 
         * @return
         *     possible object is
         *     {@link CrearCRMIdClienteReq }
         *     
         */
        public CrearCRMIdClienteReq getCrearCRMIdClienteReq() {
            return crearCRMIdClienteReq;
        }

        /**
         * Sets the value of the crearCRMIdClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link CrearCRMIdClienteReq }
         *     
         */
        public void setCrearCRMIdClienteReq(CrearCRMIdClienteReq value) {
            this.crearCRMIdClienteReq = value;
        }

        /**
         * Publicacion de novedades (Alta / Modificacion) de clientes CRM a la DIRIA
         * 
         * @return
         *     possible object is
         *     {@link PublicarNovedadClienteDiariaReq }
         *     
         */
        public PublicarNovedadClienteDiariaReq getPublicarNovedadClienteDiariaReq() {
            return publicarNovedadClienteDiariaReq;
        }

        /**
         * Sets the value of the publicarNovedadClienteDiariaReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link PublicarNovedadClienteDiariaReq }
         *     
         */
        public void setPublicarNovedadClienteDiariaReq(PublicarNovedadClienteDiariaReq value) {
            this.publicarNovedadClienteDiariaReq = value;
        }

        /**
         * Gets the value of the consultarMorosidadClienteReq property.
         * 
         * @return
         *     possible object is
         *     {@link ConsultarMorosidadClienteReq }
         *     
         */
        public ConsultarMorosidadClienteReq getConsultarMorosidadClienteReq() {
            return consultarMorosidadClienteReq;
        }

        /**
         * Sets the value of the consultarMorosidadClienteReq property.
         * 
         * @param value
         *     allowed object is
         *     {@link ConsultarMorosidadClienteReq }
         *     
         */
        public void setConsultarMorosidadClienteReq(ConsultarMorosidadClienteReq value) {
            this.consultarMorosidadClienteReq = value;
        }

    }

}
