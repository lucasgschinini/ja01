package ar.com.telecom.shiva.base.utils;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class ControlXml {
	
	public ControlXml () {
	}
	
	/**
	 * XML to Object
	 * @param mensaje
	 * @return
	 */
	public synchronized Object unmarshal(String mensaje) throws ShivaExcepcion {
		return null;
    }
	
	/**
	 * Objet to XML
	 * 
	 * @param object
	 * @return
	 */
	public synchronized static String marshal(Class<?> clase, Object object, String carpeta, String nombreArchivo) throws ShivaExcepcion {
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clase);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(clase.cast(object), new FileWriter(carpeta+nombreArchivo));
			StringWriter sw = new StringWriter();
			marshaller.marshal(clase.cast(object), sw);
			return sw.toString();
		} catch (JAXBException | IOException e) {
			throw new ShivaExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Busco un elemento en los nodos/tags del xml
	 * @param nombreElemento
	 * @param array
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static String buscarElemento(String nombreElemento, byte[] array) throws NegocioExcepcion {
		
		try {
			String cadena = new String(array);
			if (Validaciones.isNullOrEmpty(cadena)) {
				Traza.advertencia(ControlXml.class, "[buscarElemento("+nombreElemento+")] Se ha ingresado la cadena vacia y se retornara vacia");
				return "";
			}
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
			
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    ByteArrayInputStream bis = new ByteArrayInputStream(array);
		    Document doc = db.parse(bis);
	
		    NodeList nodeList = doc.getElementsByTagName(nombreElemento);
		    
		    if (nodeList != null && nodeList.getLength() > 0) {
		    	Element element = ((Element) nodeList.item(0));
		    	if (element != null && element.getChildNodes() != null) {
		    		Node nodo = element.getFirstChild(); 
		    		if (nodo != null) {
			    		String valor = nodo.getNodeValue();
				    	if (!Validaciones.isNullOrEmpty(valor)) {
				        	return valor;
				        }
		    		} 
		    	}
		    }
		    
		    Traza.advertencia(ControlXml.class, "[buscarElemento("+nombreElemento+")] No se ha encontrado el elemento en el xml y se retornara vacia");
	        return "";
		} catch (Exception e) {
    		throw new NegocioExcepcion(e);
    	}
	}
}

