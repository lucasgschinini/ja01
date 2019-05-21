
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


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
 *         &lt;element name="equipo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="nivel" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="tipo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="atendido" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "equipo"
})
@XmlRootElement(name = "equipos")
public class Equipos {

    protected List<Equipos.Equipo> equipo;

    /**
     * Gets the value of the equipo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Equipos.Equipo }
     * 
     * 
     */
    public List<Equipos.Equipo> getEquipo() {
        if (equipo == null) {
            equipo = new ArrayList<Equipos.Equipo>();
        }
        return this.equipo;
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
     *       &lt;attribute name="nivel" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="tipo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="atendido" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Equipo {

        @XmlAttribute(name = "id", required = true)
        protected String id;
        @XmlAttribute(name = "nivel")
        @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
        @XmlSchemaType(name = "integer")
        protected BigInteger nivel;
        @XmlAttribute(name = "tipo", required = true)
        protected String tipo;
        @XmlAttribute(name = "categoria")
        protected String categoria;
        @XmlAttribute(name = "atendido")
        protected String atendido;

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
         * Gets the value of the nivel property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public BigInteger getNivel() {
            return nivel;
        }

        /**
         * Sets the value of the nivel property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNivel(BigInteger value) {
            this.nivel = value;
        }

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
         * Gets the value of the categoria property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCategoria() {
            return categoria;
        }

        /**
         * Sets the value of the categoria property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCategoria(String value) {
            this.categoria = value;
        }

        /**
         * Gets the value of the atendido property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAtendido() {
            return atendido;
        }

        /**
         * Sets the value of the atendido property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAtendido(String value) {
            this.atendido = value;
        }

    }

}
