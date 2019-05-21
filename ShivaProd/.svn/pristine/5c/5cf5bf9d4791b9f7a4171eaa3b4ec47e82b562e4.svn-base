package ar.com.telecom.shiva.base.ws.cliente.servicios.impl.dummy;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel.RespuestaClienteCRM;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class DummySiebelServicio implements IClienteSiebelServicio {

	
	@Override

	public SalidaSiebelConsultarClienteWS consultarClienteSiebel(String codigoClienteLegajo) 
			throws NegocioExcepcion {
		try {			
			
			SalidaSiebelConsultarClienteWS datos = new SalidaSiebelConsultarClienteWS();
			RespuestaClienteCRM clienteCRM = getClienteCRM(codigoClienteLegajo); 
			if (clienteCRM != null) {
				datos.getListaClientes().put(clienteCRM.getCodigoClienteLegado(), clienteCRM);
			}
			
			return datos;

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public SalidaSiebelConsultarClienteWS consultarClientes(
			EntradaSiebelConsultarClienteWS entradaWS) throws NegocioExcepcion {
		
		try {			
			
			SalidaSiebelConsultarClienteWS datos = new SalidaSiebelConsultarClienteWS();
			RespuestaClienteCRM clienteCRM = getClienteCRM(entradaWS.getLegadoClienteID()); 
			if (clienteCRM != null) {
				datos.getListaClientes().put(clienteCRM.getCodigoClienteLegado(), clienteCRM);
			}
			
			return datos;

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("resource")
	private RespuestaClienteCRM getClienteCRM(String idClienteLegado) {
		
		RespuestaClienteCRM clienteCRM = new RespuestaClienteCRM();
		
		idClienteLegado = (new Formatter().format("%010d", new Long(idClienteLegado))).toString();

		if ("4000014744".equals(idClienteLegado)) {
			clienteCRM.setCodigoClienteLegado("4000014744");
			clienteCRM.setCodigoClienteAgrupador("4000014742");
			clienteCRM.setRazonSocialClienteAgrupador("PRUEBA MIC2");
			clienteCRM.setCuit("20029706043");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("2601173930".equals(idClienteLegado)) {
			clienteCRM.setCodigoClienteLegado("2601173930");
			clienteCRM.setCodigoClienteAgrupador("30261224021");
			clienteCRM.setRazonSocialClienteAgrupador("PRUEBA MIC");
			clienteCRM.setCuit("20029706041");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("4000067215".equals(idClienteLegado)) {
			clienteCRM.setCodigoClienteLegado("4000067215");
			clienteCRM.setCodigoClienteAgrupador("3026122402");
			clienteCRM.setRazonSocialClienteAgrupador("FERNANDEZ");
			clienteCRM.setCuit("20029706043");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("0000007005".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000007005");
			clienteCRM.setCodigoClienteAgrupador("3028336801");
			clienteCRM.setRazonSocialClienteAgrupador("PLUPEREZ    PEREZ");
			clienteCRM.setCuit("30709302279");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");		
			clienteCRM.setIdProvincia("G");		

		} else if ("0000097033".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000097033");
			clienteCRM.setCodigoClienteAgrupador("3028257201");
			clienteCRM.setRazonSocialClienteAgrupador("SEAPEREZ    PEREZ");
			clienteCRM.setCuit("30642731412");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("0000093602".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000093602");
			clienteCRM.setCodigoClienteAgrupador("3026368001");
			clienteCRM.setRazonSocialClienteAgrupador("COOP TEL CNEL DUGRATY");
			clienteCRM.setCuit("30642731412");
			clienteCRM.setNumeroHolding("3026368001");
			clienteCRM.setNombreHolding("COOP TEL CNEL DUGRATY");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("0000093607".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000093607");
			clienteCRM.setCodigoClienteAgrupador("3026368501");
			clienteCRM.setRazonSocialClienteAgrupador("COOP TEL PERICO LTDA");
			clienteCRM.setCuit("30566535315");
			clienteCRM.setNumeroHolding("3026368501");
			clienteCRM.setNombreHolding("COOP TEL PERICO LTDA");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("G");	

		} else if ("3000157603".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("3000157603");
			clienteCRM.setCodigoClienteAgrupador("3028324801");
			clienteCRM.setRazonSocialClienteAgrupador("SANPEREZ MIGPEREZ");
			clienteCRM.setCuit("20122130852");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("C");	

		} else if ("0000009032".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000009032");
			clienteCRM.setCodigoClienteAgrupador("3028338001");
			clienteCRM.setRazonSocialClienteAgrupador("CRIPEREZ    PEREZ");
			clienteCRM.setCuit("30709711640");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("G");	

		} else if ("0000013277".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000013277");
			clienteCRM.setCodigoClienteAgrupador("3026620701");
			clienteCRM.setRazonSocialClienteAgrupador("PODER JUDICIAL DE LA NACION");
			clienteCRM.setCuit("34546671471");
			clienteCRM.setNumeroHolding("3026620701");
			clienteCRM.setNombreHolding("PODER JUDICIAL DE LA NACION");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("0000094540".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000094540");
			clienteCRM.setCodigoClienteAgrupador("3026379001");
			clienteCRM.setRazonSocialClienteAgrupador("AEROLINEAS ARGENTINAS S.A.");
			clienteCRM.setCuit("30641405554");
			clienteCRM.setNumeroHolding("3026379001");
			clienteCRM.setNombreHolding("AEROLINEAS ARGENTINAS S.A.");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("2801175472".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("2801175472");
			clienteCRM.setCodigoClienteAgrupador("3028321801");
			clienteCRM.setRazonSocialClienteAgrupador("GIMPEREZ    PEREZ");
			clienteCRM.setCuit("30709723770");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("G");	

		} else if ("0000100005".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("0000100005");
			clienteCRM.setCodigoClienteAgrupador("3028366901");
			clienteCRM.setRazonSocialClienteAgrupador("CRIPEREZ    PEREZ");
			clienteCRM.setCuit("30709711640");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("B");	

		} else if ("2500001731".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("2500001731");
			clienteCRM.setCodigoClienteAgrupador("3028315601");
			clienteCRM.setRazonSocialClienteAgrupador("DETPEREZ    PEREZ");
			clienteCRM.setCuit("30709158984");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("B");	

		} else if ("2500001731".equals(idClienteLegado)) {
			
			clienteCRM.setCodigoClienteLegado("4000082516");
			clienteCRM.setCodigoClienteAgrupador("302831");
			clienteCRM.setRazonSocialClienteAgrupador("ZETPEREZ    PEREZ");
			clienteCRM.setCuit("30709158985");
			clienteCRM.setNumeroHolding("");
			clienteCRM.setNombreHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("B");	

		} else {
			clienteCRM = null;
		}
		
		return clienteCRM;
	}

	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio#consultarClientes(ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro)
	 */
	@Override
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) {// TODO Auto-generated method stub
		ClienteBean clienteCRM = new ClienteBean();
		if ("9032".equals(filtro.getBusqueda())) {
			
			clienteCRM.setIdClienteLegado(9032l);
			clienteCRM.setIdClienteAgrupador("3028338001");
			clienteCRM.setRazonSocialClienteAgrupador("CRIPEREZ    PEREZ");
			clienteCRM.setCuit("30709711640");
			clienteCRM.setCodigoHolding("");
			clienteCRM.setDescripcionHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("G");	

		} else if ("97033".equals(filtro.getBusqueda())) {
			
			clienteCRM.setIdClienteLegado(97033l);
			clienteCRM.setIdClienteAgrupador("3028257201");
			clienteCRM.setRazonSocialClienteAgrupador("SEAPEREZ    PEREZ");
			clienteCRM.setCuit("30642731412");
			clienteCRM.setCodigoHolding("");
			clienteCRM.setDescripcionHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OGC");
			clienteCRM.setIdProvincia("C");	

		} else if ("7005".equals(filtro.getBusqueda())) {
			
			clienteCRM.setIdClienteLegado(7005l);
			clienteCRM.setIdClienteAgrupador("3028336801");
			clienteCRM.setRazonSocialClienteAgrupador("PLUPEREZ    PEREZ");
			clienteCRM.setCuit("30709302279");
			clienteCRM.setCodigoHolding("");
			clienteCRM.setDescripcionHolding("");
			clienteCRM.setAgenciaNegocio("");
			clienteCRM.setSegmentoAgencia("OCO");
			clienteCRM.setIdProvincia("G");

		}
		List<ClienteBean> lista = new ArrayList<ClienteBean>();
		lista.add(clienteCRM);
		return lista;
	}
	
}
