package ar.com.telecom.shiva.test.soa;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.SapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.SapR3ConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.SapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.SapS4ConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.SapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapRegistrarCompensacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSapVerificacionPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

@SuppressWarnings("unused")
public class WSSapTest extends SoporteContextoSpringTest {


	@Autowired
	SapS4ConsultaProveedoresWS sapConsultaProveedoresWS;

	@Autowired
	SapConsultaPartidasWS sapConsultaPartidasWS;

	@Autowired
	SapVerificacionPartidasWS sapVerificacionPartidasWS;
	
	@Autowired
	SapRegistrarCompensacionWS sapRegistrarCompensacionWS;

	@Test
	public void testSapConsultaProveedoresWS() {

		EntradaSapConsultaProveedoresWS entrada = new EntradaSapConsultaProveedoresWS();
		entrada.getListaCuits().add("30566535315");

		try {
			SalidaSapConsultaProveedoresWS salida = sapConsultaProveedoresWS.consultarProveedores(entrada);

			String breakPoint = null;

		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testSapConsultaPartidasWS() throws ParseException {

		EntradaSapConsultaPartidasWS entrada = new EntradaSapConsultaPartidasWS();
		
		List<String> lista = new ArrayList<String>();
		lista.add("0001004507");
//		lista.add("0001000007");
		entrada.setTipoDocumentoCap(TipoDocumentoCapEnum.KB);
		
//		entrada.setMoneda(MonedaEnum.PES);
		entrada.setFechaTipoCambio(new Date());

		for (String proveedor : lista) {
			System.out.println("--------------------------------------------");
			System.out.println("Proveedor " + proveedor);	
			System.out.println("--------------------------------------------");
			try {
				entrada.getListaIdProveedores().clear();
				entrada.getListaIdProveedores().add(proveedor);
				SalidaSapConsultaPartidasWS salida = sapConsultaPartidasWS.consultarPartidas(entrada);
				System.out.println(Validaciones.isObjectNull(salida.getListaPartidas()) ? "NULO" : "Cantida[" + proveedor + "]: "  + salida.getListaPartidas().size());
				for (Partida partida : salida.getListaPartidas()) {
					System.out.println(partida.toString(";"));
					
				}
				String breakPoint = null;

			} catch (NegocioExcepcion e) {
				Traza.error(this.getClass(), e.getMessage());
				e.printStackTrace();
			}	
		}
	}

	@Test
	public void testSapVerificacionPartidasWS() {
		SalidaSapVerificacionPartidasWS salida ;
		EntradaSapVerificacionPartidasWS entrada = new EntradaSapVerificacionPartidasWS();
		Partida partida = new Partida();
		entrada.setIdOperacionShiva(1l);

		partida.setIdProveedor("0001000007");
		partida.setEjercicioFiscalDocSAP("2012");
		partida.setNumeroDocSAP("3600005727");
		partida.setPosicionDocSAP("001");
		partida.setMonedaDocProveedor("ARS");
		partida.setClaseDocSAP("KB");
		partida.setMesFiscalDocSAP("01");
		partida.setIndicador("H");
		partida.setImporteRenglonMonedaOrigenAFechaEmision(new BigDecimal(1200.0));
		partida.setEjercicioFiscalDocSAPVinculado("0000");
		partida.setPosicionDocSAPVinculado("000");
		partida.setClaveRef2("20120110");

		entrada.getListaPartidas().add(partida);
		Partida partida2 = new Partida();

		partida2.setIdProveedor("0001000007");
		partida2.setEjercicioFiscalDocSAP("2012");
		partida2.setNumeroDocSAP("3600005727");
		partida2.setPosicionDocSAP("001");
		partida2.setMonedaDocProveedor("ARS");
		partida2.setClaseDocSAP("KB");
		partida2.setMesFiscalDocSAP("01");
		partida2.setIndicador("H");
		partida2.setImporteRenglonMonedaOrigenAFechaEmision(new BigDecimal(200.0));
		partida2.setEjercicioFiscalDocSAPVinculado("0000");
		partida2.setPosicionDocSAPVinculado("000");
		partida2.setClaveRef2("20120110");

		entrada.getListaPartidas().add(partida2);

		try {

			salida = sapVerificacionPartidasWS.verificarPartidas(entrada);

			String breakPoint = null;

		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testImputacionWS() {
		SalidaSapRegistrarCompensacionWS salida ;
		EntradaSapRegistrarCompensacionWS entrada = new EntradaSapRegistrarCompensacionWS();
		Partida partida = new Partida();

		partida.setIdSociedad("M650");
		partida.setIdProveedor("0001000007");
		partida.setEjercicioFiscalDocSAP("2012");
		partida.setNumeroDocSAP("3600005727");
		partida.setPosicionDocSAP("001");
		partida.setMonedaDocProveedor("ARS");
		partida.setClaseDocSAP("KB");
		partida.setMesFiscalDocSAP("01");
		partida.setIndicador("H");
		partida.setImporteRenglonMonedaOrigenAFechaEmision(new BigDecimal(1200.0));
		partida.setEjercicioFiscalDocSAPVinculado("1200");
		partida.setPosicionDocSAPVinculado("000");
		partida.setClaveRef2("20120110");
		partida.setMonedaCompensacion("ARS");
		partida.setImporteRenglonMonedaOrigenAFechaEmision(new BigDecimal(123123));
		partida.setTextoPosicion("123");
		partida.setFechaEmision(new Date());

		entrada.getListaPartidas().add(partida);
		
		entrada.setIdOperacionShiva(00001l);
		
		try {
			
			salida = sapRegistrarCompensacionWS.imputar(entrada);
			
			String breakPoint = null;
			
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		}
	}
}
