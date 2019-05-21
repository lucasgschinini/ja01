package ar.com.telecom.shiva.presentacion.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.IClienteValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ClienteJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.facade.IClienteFacade;

public class ClienteFacadeImpl implements IClienteFacade {
	@Autowired
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;

	@Autowired
	private IClienteSiebelServicio clienteConsultarSiebelServicio;

	@Autowired
	private IClienteValidacionServicio clienteValidacionServicio;
	
	@Autowired
	private IClienteServicio clienteServicio;
	
	@Override
	public List<ClienteDto> consultarClienteSiebel(ClienteFiltro filtro) throws NegocioExcepcion {
		clienteValidacionServicio.validarFiltroClientes(filtro);
		List<ClienteBean> listaClienteWs = clienteServicio.consultarClientes(filtro);

		return this.mapeador(listaClienteWs);
	}

	@Override
	public String consultarClienteString(String idClienteLegado) throws NegocioExcepcion {
		ClienteFiltro filtro = new ClienteFiltro();

		filtro.setBusqueda(idClienteLegado);
		filtro.setCriterio(CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name());

		List<ClienteBean> listaClienteWs = clienteServicio.consultarClientes(filtro);
		StringBuilder str = new StringBuilder();

		if (!Validaciones.isObjectNull(listaClienteWs) && listaClienteWs.size() > 0) {
			ClienteBean cliente = listaClienteWs.get(0);
			if (
				!Validaciones.isObjectNull(cliente.getIdClienteAgrupador()) &&
				!Validaciones.isNullOrEmpty(cliente.getRazonSocialClienteAgrupador())
			) {
				str.append(cliente.getIdClienteAgrupador()).append(";");
				str.append(cliente.getRazonSocialClienteAgrupador().trim());
			}
			if (
				!Validaciones.isNullOrEmpty(cliente.getCodigoHolding()) && 
				!Validaciones.isNullOrEmpty(cliente.getDescripcionHolding())
			) {
				str.append(";");
				str.append(cliente.getCodigoHolding().trim()).append(";");
				str.append(cliente.getDescripcionHolding().trim());
			}
		}

		return str.toString();
	}

	
	public ClienteDto consultarCliente(String idClienteLegado) throws NegocioExcepcion {
		ClienteFiltro filtro = new ClienteFiltro();

		filtro.setBusqueda(idClienteLegado);
		filtro.setCriterio(CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name());
		
		List<ClienteDto> lista = this.consultarClienteSiebel(filtro);
		return lista.get(0);
	}
	
	@Override
	public ClienteJsonResponse consultarCliente(ClienteFiltro filtro) throws NegocioExcepcion {
		List<ClienteDto> listaclienteSiebelDto = new ArrayList<ClienteDto>();
		ClienteJsonResponse rta = new ClienteJsonResponse(); 
		
		try {
			clienteValidacionServicio.validarFiltroClientes(filtro);
			List<ClienteBean> listaClienteWs = this.clienteServicio.consultarClientes(filtro);
	
			if (!Validaciones.isObjectNull(listaClienteWs) && !listaClienteWs.isEmpty()) {
				rta.setClientes(this.mapeador(listaClienteWs));
				rta.setSuccess(true);
			} else {
				rta.setClientes(listaclienteSiebelDto);
				rta.setSuccess(false);
				rta.setCampoError("#errorBusqueda");
				//if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.getNombre().equals(confCobroClienteFiltro.getCriterio())) {
					rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.vacio"));
				//} else {
				//	rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.criterio.vacio"));
				//}
			}
		} catch (Throwable ex) {
			
			if ((ex instanceof ValidacionExcepcion)
					|| (ex.getCause()!=null && ex.getCause() instanceof ValidacionExcepcion)) 
			{
				ValidacionExcepcion ve = null;
				if (ex instanceof ValidacionExcepcion) {
					ve = (ValidacionExcepcion)ex;
				} else {
					ve = (ValidacionExcepcion)ex.getCause();
				}
				rta.setClientes(listaclienteSiebelDto);
				rta.setSuccess(false);
				
				if (!Validaciones.isNullOrEmpty(ve.getCampoError())) {
					rta.setCampoError(ve.getCampoError());
				} else {
					rta.setCampoError("#errorBusqueda"); 
				}
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString(ve.getLeyenda()));
				return rta;
			}
			
			//Otros errores que no sean de la validacion
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
				rta.setClientes(listaclienteSiebelDto);
				rta.setSuccess(false);
				rta.setCampoError("#errorBusqueda");
				rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
				return rta;
			} 
			
			rta.setClientes(listaclienteSiebelDto);
			rta.setSuccess(false);
			rta.setCampoError("#errorBusqueda");
			rta.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
		}
		
		return rta;
	}
	private List<ClienteDto> mapeador(List<ClienteBean> lista) {
		List<ClienteDto> clienteDtoList = new ArrayList<ClienteDto>();

		if (!Validaciones.isObjectNull(lista) && !lista.isEmpty()) {
			for (ClienteBean clienteBean : lista) {
				clienteDtoList.add(clienteServicio.mapear(clienteBean));
			}
		}
		return clienteDtoList;
	}
}
