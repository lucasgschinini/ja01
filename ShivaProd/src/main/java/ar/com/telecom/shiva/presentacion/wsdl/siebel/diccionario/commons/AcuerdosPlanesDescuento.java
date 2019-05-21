
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="acuerdoPlanDescuento" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="acuerdo">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="acuerdoPlanDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="fechaInicioVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="fechaFinVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="plan">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="planDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="descripcionDescuento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="tipoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="indicadorPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/all>
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
    "acuerdoPlanDescuento"
})
@XmlRootElement(name = "acuerdosPlanesDescuento")
public class AcuerdosPlanesDescuento {

    protected List<AcuerdosPlanesDescuento.AcuerdoPlanDescuento> acuerdoPlanDescuento;

    /**
     * Gets the value of the acuerdoPlanDescuento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acuerdoPlanDescuento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcuerdoPlanDescuento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento }
     * 
     * 
     */
    public List<AcuerdosPlanesDescuento.AcuerdoPlanDescuento> getAcuerdoPlanDescuento() {
        if (acuerdoPlanDescuento == null) {
            acuerdoPlanDescuento = new ArrayList<AcuerdosPlanesDescuento.AcuerdoPlanDescuento>();
        }
        return this.acuerdoPlanDescuento;
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
     *         &lt;element name="acuerdo">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="acuerdoPlanDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="fechaInicioVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="fechaFinVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                 &lt;/all>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="plan">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="planDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="descripcionDescuento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="tipoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="indicadorPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/all>
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
    public static class AcuerdoPlanDescuento {

        @XmlElement(required = true)
        protected AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo acuerdo;
        @XmlElement(required = true)
        protected AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan plan;

        /**
         * Gets the value of the acuerdo property.
         * 
         * @return
         *     possible object is
         *     {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo }
         *     
         */
        public AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo getAcuerdo() {
            return acuerdo;
        }

        /**
         * Sets the value of the acuerdo property.
         * 
         * @param value
         *     allowed object is
         *     {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo }
         *     
         */
        public void setAcuerdo(AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo value) {
            this.acuerdo = value;
        }

        /**
         * Gets the value of the plan property.
         * 
         * @return
         *     possible object is
         *     {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan }
         *     
         */
        public AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan getPlan() {
            return plan;
        }

        /**
         * Sets the value of the plan property.
         * 
         * @param value
         *     allowed object is
         *     {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan }
         *     
         */
        public void setPlan(AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan value) {
            this.plan = value;
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
         *         &lt;element name="acuerdoPlanDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="fechaInicioVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="fechaFinVigenciaAcuerdo" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
        public static class Acuerdo {

            @XmlElement(required = true)
            protected String acuerdoPlanDescuentoID;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fechaInicioVigenciaAcuerdo;
            @XmlElement(required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fechaFinVigenciaAcuerdo;

            /**
             * Gets the value of the acuerdoPlanDescuentoID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAcuerdoPlanDescuentoID() {
                return acuerdoPlanDescuentoID;
            }

            /**
             * Sets the value of the acuerdoPlanDescuentoID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAcuerdoPlanDescuentoID(String value) {
                this.acuerdoPlanDescuentoID = value;
            }

            /**
             * Gets the value of the fechaInicioVigenciaAcuerdo property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFechaInicioVigenciaAcuerdo() {
                return fechaInicioVigenciaAcuerdo;
            }

            /**
             * Sets the value of the fechaInicioVigenciaAcuerdo property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFechaInicioVigenciaAcuerdo(XMLGregorianCalendar value) {
                this.fechaInicioVigenciaAcuerdo = value;
            }

            /**
             * Gets the value of the fechaFinVigenciaAcuerdo property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getFechaFinVigenciaAcuerdo() {
                return fechaFinVigenciaAcuerdo;
            }

            /**
             * Sets the value of the fechaFinVigenciaAcuerdo property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setFechaFinVigenciaAcuerdo(XMLGregorianCalendar value) {
                this.fechaFinVigenciaAcuerdo = value;
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
         *       &lt;all>
         *         &lt;element name="planDescuentoID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="descripcionDescuento" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="tipoPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="indicadorPlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        public static class Plan {

            @XmlElement(required = true)
            protected String planDescuentoID;
            @XmlElement(required = true)
            protected String descripcionDescuento;
            @XmlElement(required = true)
            protected String tipoPlan;
            @XmlElement(required = true)
            protected String indicadorPlan;

            /**
             * Gets the value of the planDescuentoID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPlanDescuentoID() {
                return planDescuentoID;
            }

            /**
             * Sets the value of the planDescuentoID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPlanDescuentoID(String value) {
                this.planDescuentoID = value;
            }

            /**
             * Gets the value of the descripcionDescuento property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescripcionDescuento() {
                return descripcionDescuento;
            }

            /**
             * Sets the value of the descripcionDescuento property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescripcionDescuento(String value) {
                this.descripcionDescuento = value;
            }

            /**
             * Gets the value of the tipoPlan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTipoPlan() {
                return tipoPlan;
            }

            /**
             * Sets the value of the tipoPlan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTipoPlan(String value) {
                this.tipoPlan = value;
            }

            /**
             * Gets the value of the indicadorPlan property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIndicadorPlan() {
                return indicadorPlan;
            }

            /**
             * Sets the value of the indicadorPlan property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIndicadorPlan(String value) {
                this.indicadorPlan = value;
            }

        }

    }

}
