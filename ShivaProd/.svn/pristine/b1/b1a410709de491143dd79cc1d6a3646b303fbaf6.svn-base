package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParamClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class ClienteServicioImpl extends Servicio implements IClienteServicio {
	@Autowired
	private IParamClienteServicio paramClienteServicio;

	@Autowired
	private IClienteSiebelServicio clienteSiebelServicio;

	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion {
		CriterioBusquedaClienteEnum criterioBusquedaClienteEnum = CriterioBusquedaClienteEnum.getEnumByName(filtro.getCriterio());
		List<ClienteBean> shvParamClienteList = new ArrayList<ClienteBean>();
		List<ClienteBean> siebelList = new ArrayList<ClienteBean>();
		List<ClienteBean> clienteList = new ArrayList<ClienteBean>();
		List<Long> idsClientesLegadosList = new ArrayList<Long>();

		Traza.auditoria(ClienteServicioImpl.class, "INICIO - búsqueda de clientes");
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.equals(criterioBusquedaClienteEnum)) {
			Traza.auditoria(ClienteServicioImpl.class, "Realizando búsqueda por CUIT");
			// Si la busqueda es por CUIT Busco tanto en Siebel como en ShvParamCliente
			filtro.setBusqueda(filtro.getBusqueda().replace("-", ""));

			clienteList.addAll(this.mergearListas(
				this.clienteSiebelServicio.consultarClientes(filtro),
				this.paramClienteServicio.consultarClientes(filtro)
			));

		} else {
 			// BUSCO EL CLIENTE
			ClienteFiltro filtroClone = filtro.clone();
			filtroClone.setCriterio(CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name());
			Traza.auditoria(ClienteServicioImpl.class, "Realizando búsqueda por Id Cliente Legado");
 			if (filtroClone.getBusqueda().trim().length() <= 10) {
 	 			siebelList = this.clienteSiebelServicio.consultarClientes(filtroClone);	
 			}
 		
 			if (filtroClone.getBusqueda().trim().length() > 10 || siebelList.isEmpty()) {
 				shvParamClienteList = this.paramClienteServicio.consultarClientes(filtroClone);
 			}

 			siebelList = this.mergearListas(siebelList, shvParamClienteList);

 			if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.equals(criterioBusquedaClienteEnum)) {
 				clienteList.addAll(siebelList);
 			} else if (!siebelList.isEmpty()) {
 				// SI LA BUSQUEDA ES POR HOLDING O NEGOCIO debo buscar por el holding o la agencia del primer cliente encontrado.(y unico) 
 				
 				if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.equals(criterioBusquedaClienteEnum)) {
 					Traza.auditoria(ClienteServicioImpl.class, "Realizando búsqueda por Agencia de Negocio");
 					if (Validaciones.isNullEmptyOrDash(siebelList.get(0).getAgenciaNegocio())) {
 						Traza.auditoria(ClienteServicioImpl.class, "Este cliente no tiene Agencia de Negocio");
 						Traza.auditoria(ClienteServicioImpl.class, "FIN - búsqueda de clientes.");
 						throw new ValidacionExcepcion("","error.siebel.cliente.agencia.negocio");
 					} else {
 						filtro.setBusqueda(siebelList.get(0).getAgenciaNegocio());
 					}
 				} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.equals(criterioBusquedaClienteEnum)) {
 					Traza.auditoria(ClienteServicioImpl.class, "Realizando búsqueda por Holding");
 					if (Validaciones.isNullEmptyOrDash(siebelList.get(0).getCodigoHolding())) {
 						Traza.auditoria(ClienteServicioImpl.class, "Este cliente no tiene Holding");
 						Traza.auditoria(ClienteServicioImpl.class, "FIN - búsqueda de clientes.");
 						throw new ValidacionExcepcion("","error.siebel.cliente.holding");
 					} else {
 						filtro.setBusqueda(siebelList.get(0).getCodigoHolding());
 					}
 				}
 				clienteList.addAll(this.mergearListas(
 					this.clienteSiebelServicio.consultarClientes(filtro),
 					this.paramClienteServicio.consultarClientes(filtro)
 				));
 			
 			} else {
				Traza.auditoria(ClienteServicioImpl.class, "Cliente inexistente");
				Traza.auditoria(ClienteServicioImpl.class, "FIN - búsqueda de clientes.");
				throw new ValidacionExcepcion("","conf.cobro.mensaje.siebel.vacio");
 			}
 		}
		for (ClienteBean clienteBean : clienteList) {
			if(ClienteOrigenEnum.SBL.name().equals(clienteBean.getOrigen())) {
				idsClientesLegadosList.add(clienteBean.getIdClienteLegado());
			}
		}
		if (Validaciones.isCollectionNotEmpty(idsClientesLegadosList)) {
			List<ClienteBean> consultarClientes = this.paramClienteServicio.consultarClientes(idsClientesLegadosList);
			if(Validaciones.isCollectionNotEmpty(consultarClientes)) {
				for (ClienteBean clienteBean : clienteList) {
					if(ClienteOrigenEnum.SBL.name().equals(clienteBean.getOrigen())) {
						for (ClienteBean clienteResultado : consultarClientes) {
							if(clienteBean.getIdClienteLegado().equals(clienteResultado.getIdClienteLegado())){
								clienteBean.setEmpresasAsociadas(clienteResultado.getEmpresasAsociadas());
								clienteBean.setPermiteUsoCV(clienteResultado.getPermiteUsoCV());
								clienteBean.setPermiteUsoFT(clienteResultado.getPermiteUsoFT());
								clienteBean.setPermiteUsoNX(clienteResultado.getPermiteUsoNX());
								clienteBean.setPermiteUsoTA(clienteResultado.getPermiteUsoTA());
								clienteBean.setPermiteUsoTP(clienteResultado.getPermiteUsoTP());
								break;
							}
						}
					}
				}
			}
		}
		Traza.auditoria(ClienteServicioImpl.class, "FIN - búsqueda de clientes.");
		return clienteList;
	}
	
	public ClienteBean consultarCliente(String idClienteLegado) throws NegocioExcepcion {
		ClienteFiltro filtro = new ClienteFiltro();
		List<ClienteBean> clienteBeanLista;

		filtro.setCriterio(CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name());
		filtro.setBusqueda(idClienteLegado);

		clienteBeanLista = this.consultarClientes(filtro);

		if (!clienteBeanLista.isEmpty()) {
			return clienteBeanLista.get(0);
		}
		return null;
	}
	
	
	private List<ClienteBean> mergearListas(List<ClienteBean> listaSiebel, List<ClienteBean> listaInterna) {
		if (listaInterna.isEmpty() && listaSiebel.isEmpty()) {
			return new ArrayList<ClienteBean>();
		} 
		if (listaSiebel.isEmpty()) {
			return listaInterna;
		} else if (listaInterna.isEmpty()) {
			return listaSiebel;
		}
		
		for (ClienteBean cliente : listaInterna) {
			if (cliente.getIdClienteLegado().toString().length() <= 10 && !listaSiebel.contains(cliente)) {
				listaSiebel.add(cliente);
			} else if (cliente.getIdClienteLegado().toString().length() == 11) {
				listaSiebel.add(cliente);
			}
		}
		return listaSiebel;
	}
	
	public ClienteDto mapear(ClienteBean clienteBean) {
		if (Validaciones.isObjectNull(clienteBean)) {
			return null;
		}
		ClienteDto miCliente = new ClienteDto();
		miCliente.setIdClienteLegado(clienteBean.getIdClienteLegado().toString());
		miCliente.setEmpresasAsociadas(clienteBean.getEmpresasAsociadas());
		miCliente.setRazonSocial(ClienteOrigenEnum.DELTA.getDescripcion().equals(clienteBean.getClienteOrigen()) ? clienteBean.getRazonSocialClienteAgrupador() : clienteBean.getRazonSocial());
		miCliente.setOrigen(clienteBean.getOrigen());
		miCliente.setCodigoHolding(clienteBean.getCodigoHolding());
		miCliente.setDescripcionHolding(clienteBean.getDescripcionHolding());
		miCliente.setCuit(clienteBean.getCuit());
		miCliente.setAgenciaNegocio(clienteBean.getAgenciaNegocio());
		miCliente.setDescripcionAgenciaNegocio(clienteBean.getDescripcionAgenciaNegocio());
		miCliente.setSegmentoAgencia(clienteBean.getSegmentoAgencia());
		miCliente.setIdClientePerfil(clienteBean.getIdClientePerfil());
		miCliente.setIdProvincia(clienteBean.getIdProvincia());
		return miCliente;
	}
}
