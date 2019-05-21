package ar.com.telecom.shiva.test.util;

import junit.framework.TestCase;

import org.junit.Test;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.ControlXml;

public class ControlXmlTest extends TestCase {
	
	
	@SuppressWarnings("unused")
	@Test
	public void testFindElementInXml(){
		try {
			String mensajeRecibido = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:WSDL\"><SOAP-ENV:Header/><SOAP-ENV:Body><ns1:ApropiacionResponse><respuestaapropiacion><idShiva>0000279.00002</idShiva><detFac/><listaCreDeb/><listaCtaCre><detalle><idref>5635157</idref><tipo>CTA</tipo><clase>X</clase><sucursal>0308</sucursal><numero>00003924</numero><idcob>5635450</idcob><detallenewcta/></detalle></listaCtaCre><resultado><idShiva>0000279.00002</idShiva><Resultado><resultado>OK</resultado><codigoerror/><descripcionerror/></Resultado></resultado></respuestaapropiacion></ns1:ApropiacionResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>";
			String idOperacionTransaccion = ControlXml.buscarElemento("idShiva", mensajeRecibido.getBytes());
			String descripcionError = "AC-"+idOperacionTransaccion+"-"+ControlXml.buscarElemento("descripcionerror", mensajeRecibido.getBytes());
			
		} catch (NegocioExcepcion e) {
		}
	}
	

//	@Test
//	public void testJava2Xml(){
//		Cabecera cabecera = new Cabecera();
//		Detalle detalle = new Detalle();
//		
//		cabecera.setCliente("cliente");
//		cabecera.setComprobante("comprobante");
//		cabecera.setConcepto("concepto");
//		cabecera.setCuit("cuit");
//		cabecera.setDescripcion("descripcion");
//		cabecera.setDomicilio("domicilio");
//		cabecera.setFecha("fecha");
//		cabecera.setHora("hora");
//		cabecera.setIdOperacion("idOperacion");
//		cabecera.setRazonSocial("razonSocial");
//		cabecera.setResponsableIVA("responsableIVA");
//		cabecera.setTipo("tipo");
//		cabecera.setTotal("1");
//		
//		ChequeRechazado chequeRechazados = new ChequeRechazado();
//		Cheque cheque = new Cheque();
//		cheque.setDescripcion("descripcion");
//		cheque.setImporte("1");
//		cheque.setReferencia1("referencia1");
//		cheque.setReferencia2("referencia2");
//		cheque.setReferencia3("referencia3");
//		
//		Cheque cheque2 = new Cheque();
//		cheque2.setDescripcion("descripcion");
//		cheque2.setImporte("1");
//		cheque2.setReferencia1("referencia1");
//		cheque2.setReferencia2("referencia2");
//		cheque2.setReferencia3("referencia3");
//		
//		List<Cheque> listaCheques = new ArrayList<Cheque>();
//		
//		Factura factura = new Factura();
//		factura.setAcuerdo("1");
//		factura.setClase("clase");
//		factura.setComprobante("comprobante");
//		factura.setConsecuenciaCobro("consecuenciaCobro");
//		factura.setImporte("1");
//		factura.setImporteVencimiento("1");
//		factura.setIntereses("1");
//		factura.setInteresesBonificados("1");
//		factura.setMoneda("moneda");
//		factura.setNumero("numero");
//		factura.setOperacion("operacion");
//		factura.setOrigen("origen");
//		factura.setPago("pago");
//		factura.setReferencia("referencia");
//		factura.setSaldo("1");
//		factura.setSucursal("sucursal");
////		factura.setTipoCambio("tipoCambio");
//		factura.setVencimiento("vencimiento");
//		
//		Factura factura2 = new Factura();
//		factura2.setAcuerdo("1");
//		factura2.setClase("clase");
//		factura2.setComprobante("comprobante");
//		factura2.setConsecuenciaCobro("consecuenciaCobro");
//		factura2.setImporte("1");
//		factura2.setImporteVencimiento("1");
//		factura2.setIntereses("1");
//		factura2.setInteresesBonificados("1");
//		factura2.setMoneda("moneda");
//		factura2.setNumero("numero");
//		factura2.setOperacion("operacion");
//		factura2.setOrigen("origen");
//		factura2.setPago("pago");
//		factura2.setReferencia("referencia");
//		factura2.setSaldo("1");
//		factura2.setSucursal("sucursal");
//		factura2.setTipoCambio("tipoCambio");
//		factura2.setVencimiento("vencimiento");
//
//		List<Factura> listaFactura = new ArrayList<Factura>();
//		
//		Medio medio = new Medio();
//		medio.setDescripcion("descripcion");
//		medio.setImporte("1");
//		medio.setReferencia1("referencia1");
//		medio.setReferencia2("referencia2");
//		medio.setReferencia3("referencia3");
//		Medio medio2 = new Medio();
//		medio2.setDescripcion("descripcion");
//		medio2.setImporte("1");
//		medio2.setReferencia1("referencia1");
//		medio2.setReferencia2("referencia2");
//		medio2.setReferencia3("referencia3");
//		
//		List<Medio> listaMedio = new ArrayList<Medio>();
//		
//		listaMedio.add(medio);
//		listaMedio.add(medio2);
//		listaFactura.add(factura);
//		listaFactura.add(factura2);
//		listaCheques.add(cheque);
//		listaCheques.add(cheque2);
//		
//		Facturas facturas = new Facturas();
//		chequeRechazados.setCheque(listaCheques);
//		facturas.setCantidadFacturas("2");
//		facturas.setFactura(listaFactura);
//		facturas.setImporteTotal("1");
//		
//		detalle.setChequesRechazados(chequeRechazados);
//		detalle.setFacturas(facturas);
//		detalle.setMediosDePago(listaMedio);
//		
//		Documentos docs = new Documentos();
//		docs.setDocumento(new Documento());
//		docs.getDocumento().setCabecera(cabecera);
//		docs.getDocumento().setDetalle(detalle);
		
//		try {
//			ControlXml.marshal(Documentos.class, docs, "C:\\Temp\\Java2Xml\\", "testJava2Xml.xml");
//		} catch (ShivaExcepcion e) {
//			e.printStackTrace();
//		}
//	}
}
