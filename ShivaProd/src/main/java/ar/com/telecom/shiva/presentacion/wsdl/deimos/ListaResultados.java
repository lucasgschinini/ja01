
package ar.com.telecom.shiva.presentacion.wsdl.deimos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListaResultados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListaResultados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Resultado" type="{http://shiva.ws.intelap.com/}Resultado" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaResultados", propOrder = {
    "resultado"
})
public class ListaResultados {

    @XmlElement(name = "Resultado", required = true)
    protected List<Resultado> resultado;

    /**
     * Gets the value of the resultado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Resultado }
     * 
     * 
     */
    public List<Resultado> getResultado() {
        if (resultado == null) {
            resultado = new ArrayList<Resultado>();
        }
        return this.resultado;
    }

}
