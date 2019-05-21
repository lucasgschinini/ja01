package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.ClienteConsultarSiebelWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel.RespuestaClienteCRM;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.DomicilioBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.IClienteValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class ClienteSiebelServicio implements IClienteSiebelServicio {

	@Autowired
	ClienteConsultarSiebelWS clienteConsultarSiebelWS;

	@Autowired
	private IClienteValidacionServicio clienteValidacionServicio;
	
	@Override
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion {
		List<ClienteBean> clientes = new ArrayList<ClienteBean>();

		try {
			CriterioBusquedaClienteEnum criterioBusquedaClienteEnum = CriterioBusquedaClienteEnum.getEnumByName(filtro.getCriterio());
			EntradaSiebelConsultarClienteWS entrada = null;

			if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.equals(criterioBusquedaClienteEnum)) {
				entrada = new EntradaSiebelConsultarClienteWS(
					filtro.getCriterio(),
					filtro.getBusqueda(),				//legadoClienteID
					null,								//crmHoldingID
					null,
					null								//crmClienteCUIT
				);
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.equals(criterioBusquedaClienteEnum)) {
				entrada = new EntradaSiebelConsultarClienteWS(
					filtro.getCriterio(),
					null,								//legadoClienteID
					null,								//crmHoldingID
					filtro.getBusqueda(),
					null								//crmClienteCUIT
				);
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.equals(criterioBusquedaClienteEnum)) {
				entrada = new EntradaSiebelConsultarClienteWS(
					filtro.getCriterio(),
					null,								//legadoClienteID
					null,								//crmHoldingID
					null,
					filtro.getBusqueda()				//crmClienteCUIT
				);
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.equals(criterioBusquedaClienteEnum)) {
				entrada = new EntradaSiebelConsultarClienteWS(
					filtro.getCriterio(),
					null,								//legadoClienteID
					filtro.getBusqueda(),								//crmHoldingID
					null,
					null								//crmClienteCUIT
				);
			} else {
				throw new NegocioExcepcion("Busqueda de cliente sin criterio!!!");
			}
			
			SalidaSiebelConsultarClienteWS salida = clienteConsultarSiebelWS.consultarClientes(entrada);
	
			if (salida != null && Validaciones.isMapNotEmpty(salida.getListaClientes())) {
				ClienteBean miCliente = null;
				for (RespuestaClienteCRM clienteCRM : salida.getListaClientes().values()) {
					miCliente = new ClienteBean();
					miCliente.setIdClienteLegado(Long.parseLong(clienteCRM.getCodigoClienteLegado()));
					miCliente.setEmpresasAsociadas(Utilidad.armadoCampoEmpresasAsociadas(SiNoEnum.SI, SiNoEnum.NO, SiNoEnum.NO, SiNoEnum.NO));
					miCliente.setRazonSocial(clienteCRM.getRazonSocialClienteAgrupador());
					miCliente.setOrigen(ClienteOrigenEnum.SBL.name());
					miCliente.setCodigoHolding(clienteCRM.getNumeroHolding());
					miCliente.setDescripcionHolding(clienteCRM.getNombreHolding()); //getNombreHolding
					miCliente.setCuit(clienteCRM.getCuit());
					miCliente.setAgenciaNegocio(clienteCRM.getAgenciaNegocio());
					miCliente.setDescripcionAgenciaNegocio(clienteCRM.getDescripcionAgenciaNegocio());
					miCliente.setSegmentoAgencia(clienteCRM.getSegmentoAgencia());
					miCliente.setIdClientePerfil(clienteCRM.getCodigoClienteAgrupador());
					miCliente.setIdClienteAgrupador(clienteCRM.getCodigoClienteAgrupador());
					miCliente.setRazonSocialClienteAgrupador(clienteCRM.getRazonSocialClienteAgrupador());
					miCliente.setClienteOrigen(ClienteOrigenEnum.DELTA.getDescripcion());
					miCliente.setDomicilio(new DomicilioBean());

					miCliente.getDomicilio().setAltura(clienteCRM.getDomicilio().getAltura());
					miCliente.getDomicilio().setCodigoCalle(clienteCRM.getDomicilio().getCodigoCalle());
					miCliente.getDomicilio().setCodigoCiudad(clienteCRM.getDomicilio().getCodigoCiudad());
					miCliente.getDomicilio().setCodigoPostal(clienteCRM.getDomicilio().getCodigoPostal());
					miCliente.getDomicilio().setDescLocalidad(clienteCRM.getDomicilio().getDescLocalidad());
					miCliente.getDomicilio().setDescProvincia(clienteCRM.getDomicilio().getDescProvincia());
					miCliente.getDomicilio().setEscalera(clienteCRM.getDomicilio().getEscalera());
					miCliente.getDomicilio().setNombreCalle(clienteCRM.getDomicilio().getNombreCalle());
					miCliente.getDomicilio().setNombreEdificio(clienteCRM.getDomicilio().getNombreEdificio());
					miCliente.getDomicilio().setPiso(clienteCRM.getDomicilio().getPiso());
					miCliente.getDomicilio().setPuerta(clienteCRM.getDomicilio().getPuerta());
					clientes.add(miCliente);
				}
			}
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
				throw new NegocioExcepcion(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"), ex);
			} 
			throw new NegocioExcepcion(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"), ex);
		}
		
		return clientes;
	}

	public SalidaSiebelConsultarClienteWS consultarClienteSiebel(String codigoClienteLegajo)
			throws NegocioExcepcion {
		try {
			
			EntradaSiebelConsultarClienteWS entrada 
				= new EntradaSiebelConsultarClienteWS(codigoClienteLegajo);
			
			SalidaSiebelConsultarClienteWS datosSalida = 
					clienteConsultarSiebelWS.consultarClientes(entrada);
			
			if (datosSalida != null && 
					!Validaciones.isMapNotEmpty(datosSalida.getListaClientes())) {
				throw new WebServiceExcepcion("WS ClienteConsultar: la lista de clientesCRM se encuentra vacio");
			}
			
			return datosSalida;
			
		} catch (Exception e) {
			String mensajeAuxiliar = "Se ha producido un error en el servicio de Siebel";
			throw new NegocioExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	@Override
	public SalidaSiebelConsultarClienteWS consultarClientes(EntradaSiebelConsultarClienteWS entrada) throws NegocioExcepcion {
		try {
			SalidaSiebelConsultarClienteWS salida = clienteConsultarSiebelWS.consultarClientes(entrada);

			if (salida != null && Validaciones.isMapNotEmpty(salida.getListaClientes())) {
				if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.getNombre().equals(entrada.getCriterio())) {
					RespuestaClienteCRM clienteCRM = salida.getListaClientes().values().iterator().next();

					if (!Validaciones.isNullOrEmpty(clienteCRM.getAgenciaNegocio())) {
						entrada = new EntradaSiebelConsultarClienteWS(
							entrada.getCriterio(),
							null,								//legadoClienteID
							null,								//crmHoldingID
							clienteCRM.getAgenciaNegocio(),
							null								//crmClienteCUIT
						);

						// Se ingresa por el constructor (criterio/legadoClienteId/crmHoldingId/cmrAgenciaNegocio/crmClienteCuit)
						salida = clienteConsultarSiebelWS.consultarClientes(entrada);
					} else {
						throw new ValidacionExcepcion("","error.siebel.cliente.agencia.negocio");
					}
				}
				if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.getNombre().equals(entrada.getCriterio())) {
					RespuestaClienteCRM clienteCRM = salida.getListaClientes().values().iterator().next();

					if (!Validaciones.isNullOrEmpty(clienteCRM.getNumeroHolding())) {
						entrada = new EntradaSiebelConsultarClienteWS(
							entrada.getCriterio(),
							null,						//legadoClienteID
							clienteCRM.getNumeroHolding(),
							null,							//crmAgenciaDeNegocio
							null							//crmClienteCUIT
						);

						// Se ingresa por el constructor (criterio/legadoClienteId/crmHoldingId/cmrAgenciaNegocio/crmClienteCuit)
						salida = clienteConsultarSiebelWS.consultarClientes(entrada);
					} else {
						throw new ValidacionExcepcion("","error.siebel.cliente.holding");
					}
				}
			}
			return salida;
		} catch (Exception e) {
			String mensajeAuxiliar = "Se ha producido un error en el servicio de Siebel";
			throw new NegocioExcepcion(e.getMessage(), e, mensajeAuxiliar);
		}
	}

	public ClienteConsultarSiebelWS getClienteConsultarSiebelWS() {
		return clienteConsultarSiebelWS;
	}

	public void setClienteConsultarSiebelWS(
			ClienteConsultarSiebelWS clienteConsultarSiebelWS) {
		this.clienteConsultarSiebelWS = clienteConsultarSiebelWS;
	}
}
