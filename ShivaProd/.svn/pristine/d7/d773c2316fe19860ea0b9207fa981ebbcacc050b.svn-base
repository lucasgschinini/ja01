package ar.com.telecom.shiva.test.soa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaDeimosConsultaEstadoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Documento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos.Transaccion;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class WSDeimosTest extends SoporteContextoSpringTest {

	@Autowired 
	IClienteDeimosServicio clienteDeimosServicio;
	
	/**
	 */
	//@Test
	public void testApropiacionDeimos() throws NegocioExcepcion {
		System.out.println("---------------- Apropiacion ----------------------");
		
		EntradaDeimosApropiacionWS entradaDeimosApropiacion = new EntradaDeimosApropiacionWS();
		
		//Seteo Numero Operacion y el modo operacion : "S"-> simulaciónimputación
		entradaDeimosApropiacion.setIdOperacionShiva(new Long("0"));
		entradaDeimosApropiacion.setModoOperacion(SiNoEnum.SI);
		
		List<Documento> listaDocumentos = new ArrayList<Documento>();
		Documento doc = new Documento();
		doc.setEmpresa(EmpresaEnum.TA);
		doc.setImporte(new BigDecimal("22"));
		
		Transaccion transaccion = new Transaccion();
		transaccion.setIdTransaccion(new Integer("0"));
		transaccion.setIdSecuencia(new Integer("0"));
		transaccion.setListaDocumentos(listaDocumentos);
		entradaDeimosApropiacion.setTransaccion(transaccion);
		
		SalidaDeimosApropiacionWS datos = 
						clienteDeimosServicio.apropiarDocumento(entradaDeimosApropiacion);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getResultadoProcesamiento().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
			
		
		System.out.println("--------------------------------------");
	}
	
	/**
	 * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	 * <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"/>
	 * <soap:Body><ns2:ConsultaEstadoDocumento xmlns:ns2="http://shiva.ws.intelap.com/">
	 * <ConsultaEstadoDeudaFiltroDeimos><Empresa>TA</Empresa><Sistema>MIC</Sistema><IdDocumentoMIC><NumeroReferenciaMIC>875057158010564</NumeroReferenciaMIC>
	 * </IdDocumentoMIC><IdDocumentoCalipso xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/>
	 * </ConsultaEstadoDeudaFiltroDeimos></ns2:ConsultaEstadoDocumento></soap:Body></soap:Envelope>
	 */
	@Test
	public void testConsultaDeimos() throws NegocioExcepcion {
		System.out.println("---------------- Consulta Deimos ----------------------");
		
		EntradaDeimosConsultaEstadoDeudaWS entradaDeimosConsulta = new EntradaDeimosConsultaEstadoDeudaWS();
		
		entradaDeimosConsulta.setEmpresa(EmpresaEnum.TA);
		entradaDeimosConsulta.setSistema(SistemaEnum.MIC);
		entradaDeimosConsulta.setIdNumeroReferenciaMic(new Long("875057158010564"));

		SalidaDeimosConsultaEstadoDocumentoWS datos = 
						clienteDeimosServicio.consultarEstadoDocumento(entradaDeimosConsulta);
		
		if (datos!=null) {
			System.out.println("resultado: ");
			System.out.println(datos.getResultadoProcesamiento().toString());
			
		} else {
			System.out.println("resultado: sin datos");
		}
			
		
		System.out.println("--------------------------------------");
	}
}
