
package ar.com.telecom.shiva.presentacion.wsdl.delta.header.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for header element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="header">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="consumer">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="credentials" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="code" use="required">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="10"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="transport" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="appID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="replyToQueue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="replyToQueueManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="serviceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="communicationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                   &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="message">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="timestamp" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="date" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;pattern value="(19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3]):([0-5]\d):([0-5]\d)"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="expiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="messageId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                   &lt;attribute name="conversationId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                   &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="service">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;attribute name="code" use="required">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/attribute>
 *                   &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="properties" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="property" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                             &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
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
    "consumer",
    "transport",
    "message",
    "service",
    "properties"
})
@XmlRootElement(name = "header")
public class HeaderResponse {

    @XmlElement(required = true)
    protected HeaderResponse.Consumer consumer;
    protected HeaderResponse.Transport transport;
    @XmlElement(required = true)
    protected HeaderResponse.Message message;
    @XmlElement(required = true)
    protected HeaderResponse.Service service;
    protected HeaderResponse.Properties properties;

    /**
     * Gets the value of the consumer property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse.Consumer }
     *     
     */
    public HeaderResponse.Consumer getConsumer() {
        return consumer;
    }

    /**
     * Sets the value of the consumer property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse.Consumer }
     *     
     */
    public void setConsumer(HeaderResponse.Consumer value) {
        this.consumer = value;
    }

    /**
     * Gets the value of the transport property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse.Transport }
     *     
     */
    public HeaderResponse.Transport getTransport() {
        return transport;
    }

    /**
     * Sets the value of the transport property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse.Transport }
     *     
     */
    public void setTransport(HeaderResponse.Transport value) {
        this.transport = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse.Message }
     *     
     */
    public HeaderResponse.Message getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse.Message }
     *     
     */
    public void setMessage(HeaderResponse.Message value) {
        this.message = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse.Service }
     *     
     */
    public HeaderResponse.Service getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse.Service }
     *     
     */
    public void setService(HeaderResponse.Service value) {
        this.service = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderResponse.Properties }
     *     
     */
    public HeaderResponse.Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderResponse.Properties }
     *     
     */
    public void setProperties(HeaderResponse.Properties value) {
        this.properties = value;
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
     *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="credentials" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="code" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;maxLength value="10"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "userID",
        "credentials"
    })
    public static class Consumer {

        @XmlElement(required = true)
        protected String userID;
        @XmlElement(required = true)
        protected String credentials;
        @XmlAttribute(name = "code", required = true)
        protected String code;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the userID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUserID() {
            return userID;
        }

        /**
         * Sets the value of the userID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUserID(String value) {
            this.userID = value;
        }

        /**
         * Gets the value of the credentials property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCredentials() {
            return credentials;
        }

        /**
         * Sets the value of the credentials property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCredentials(String value) {
            this.credentials = value;
        }

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
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
     *         &lt;element name="timestamp" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="date" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;pattern value="(19|20)\d\d(0[1-9]|10|11|12)(0[1-9]|1\d|2\d|30|31) (0\d|1\d|2[0-3]):([0-5]\d):([0-5]\d)"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="expiration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="messageId" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="conversationId" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}short" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "timestamp",
        "date",
        "expiration"
    })
    public static class Message {

        protected String timestamp;
        protected String date;
        protected String expiration;
        @XmlAttribute(name = "messageId")
        protected String messageId;
        @XmlAttribute(name = "conversationId")
        protected String conversationId;
        @XmlAttribute(name = "priority")
        protected Short priority;

        /**
         * Gets the value of the timestamp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTimestamp() {
            return timestamp;
        }

        /**
         * Sets the value of the timestamp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTimestamp(String value) {
            this.timestamp = value;
        }

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDate(String value) {
            this.date = value;
        }

        /**
         * Gets the value of the expiration property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExpiration() {
            return expiration;
        }

        /**
         * Sets the value of the expiration property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExpiration(String value) {
            this.expiration = value;
        }

        /**
         * Gets the value of the messageId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessageId() {
            return messageId;
        }

        /**
         * Sets the value of the messageId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessageId(String value) {
            this.messageId = value;
        }

        /**
         * Gets the value of the conversationId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConversationId() {
            return conversationId;
        }

        /**
         * Sets the value of the conversationId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConversationId(String value) {
            this.conversationId = value;
        }

        /**
         * Gets the value of the priority property.
         * 
         * @return
         *     possible object is
         *     {@link Short }
         *     
         */
        public Short getPriority() {
            return priority;
        }

        /**
         * Sets the value of the priority property.
         * 
         * @param value
         *     allowed object is
         *     {@link Short }
         *     
         */
        public void setPriority(Short value) {
            this.priority = value;
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
     *         &lt;element name="property" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "property"
    })
    public static class Properties {

        protected List<HeaderResponse.Properties.Property> property;

        /**
         * Gets the value of the property property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the property property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProperty().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HeaderResponse.Properties.Property }
         * 
         * 
         */
        public List<HeaderResponse.Properties.Property> getProperty() {
            if (property == null) {
                property = new ArrayList<HeaderResponse.Properties.Property>();
            }
            return this.property;
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
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Property {

            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "value")
            protected String value;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
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
     *       &lt;attribute name="code" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;maxLength value="50"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Service {

        @XmlAttribute(name = "code", required = true)
        protected String code;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
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
     *         &lt;element name="appID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="replyToQueue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="replyToQueueManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="serviceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="communicationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "appID",
        "replyToQueue",
        "replyToQueueManager",
        "serviceID",
        "communicationType"
    })
    public static class Transport {

        protected String appID;
        protected String replyToQueue;
        protected String replyToQueueManager;
        protected String serviceID;
        @XmlElement(required = true)
        protected String communicationType;
        @XmlAttribute(name = "code", required = true)
        protected String code;
        @XmlAttribute(name = "name", required = true)
        protected String name;

        /**
         * Gets the value of the appID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAppID() {
            return appID;
        }

        /**
         * Sets the value of the appID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAppID(String value) {
            this.appID = value;
        }

        /**
         * Gets the value of the replyToQueue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReplyToQueue() {
            return replyToQueue;
        }

        /**
         * Sets the value of the replyToQueue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReplyToQueue(String value) {
            this.replyToQueue = value;
        }

        /**
         * Gets the value of the replyToQueueManager property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReplyToQueueManager() {
            return replyToQueueManager;
        }

        /**
         * Sets the value of the replyToQueueManager property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReplyToQueueManager(String value) {
            this.replyToQueueManager = value;
        }

        /**
         * Gets the value of the serviceID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceID() {
            return serviceID;
        }

        /**
         * Sets the value of the serviceID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceID(String value) {
            this.serviceID = value;
        }

        /**
         * Gets the value of the communicationType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCommunicationType() {
            return communicationType;
        }

        /**
         * Sets the value of the communicationType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCommunicationType(String value) {
            this.communicationType = value;
        }

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }

}
