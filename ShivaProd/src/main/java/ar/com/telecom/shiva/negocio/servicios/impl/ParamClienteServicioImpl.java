package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParamClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.persistencia.dao.IParamClienteDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCliente;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class ParamClienteServicioImpl extends Servicio implements IParamClienteServicio {
	@Autowired
	private IParamClienteDao paramClienteDao;
	
	@Override
	public List<ClienteBean> consultarClientes(ClienteFiltro filtro) throws NegocioExcepcion {
		List<ClienteBean> clienteBeanList = null;
		try {
			Traza.advertencia(ParamClienteServicioImpl.class, "Buscando " 
								+ CriterioBusquedaClienteEnum.getEnumByName(filtro.getCriterio()).getDescripcion() 
								+ " "
								+ filtro.getBusqueda()
								+ " en tabla interna."
							 );
			clienteBeanList = this.mapear(paramClienteDao.consultarClientes(filtro));
		} catch (PersistenciaExcepcion ex) {
			throw new NegocioExcepcion(ex.getMessage(), ex);
		}
		return clienteBeanList;
	}

	@Override
	public List<ClienteBean> consultarClientes(List<Long> listaIds) throws NegocioExcepcion {
		List<ClienteBean> clienteBeanList = null;
		try {
			Traza.advertencia(ParamClienteServicioImpl.class, "Buscando " 
					+ CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name()
					+ "( "
					+ StringUtils.join(listaIds, ", ")
					+ ") en tabla interna."
				 );
			clienteBeanList = this.mapear(paramClienteDao.consultarClientes(listaIds));

		} catch (PersistenciaExcepcion ex) {
			throw new NegocioExcepcion(ex.getMessage(), ex);
		}
		return clienteBeanList;
	}

	private List<ClienteBean> mapear(List<ShvParamCliente> shvParamClienteList) throws NegocioExcepcion {
		List<ClienteBean> clienteBeanList = new ArrayList<ClienteBean>();

		for (ShvParamCliente shvParamCliente : shvParamClienteList) {
			ClienteBean miCliente = new ClienteBean();
			
			clienteBeanList.add(this.mapear(miCliente, shvParamCliente));
		}
		return clienteBeanList;
	}
	private ClienteBean mapear(ClienteBean miCliente, ShvParamCliente shvParamCliente) throws NegocioExcepcion {
		if (Validaciones.isObjectNull(miCliente)) {
			miCliente = new ClienteBean();
		}
		miCliente.setIdClienteLegado(shvParamCliente.getIdClienteLegado());
		miCliente.setPermiteUsoCV(shvParamCliente.getPermiteUsoCV());
		miCliente.setPermiteUsoFT(shvParamCliente.getPermiteUsoFT());
		miCliente.setPermiteUsoNX(shvParamCliente.getPermiteUsoNX());
		miCliente.setPermiteUsoTA(shvParamCliente.getPermiteUsoTA());
		miCliente.setPermiteUsoTP(shvParamCliente.getPermiteUsoTP());
		miCliente.setEmpresasAsociadas(Utilidad.armadoCampoEmpresasAsociadas(shvParamCliente.getPermiteUsoTA(), 
				shvParamCliente.getPermiteUsoTP(), shvParamCliente.getPermiteUsoCV(), shvParamCliente.getPermiteUsoNX()));
		miCliente.setRazonSocial(shvParamCliente.getRazonSocial());
		if (!Validaciones.isObjectNull(shvParamCliente.getOrigen())) {
			miCliente.setOrigen(shvParamCliente.getOrigen().getCodigo());
		}
		miCliente.setCodigoHolding(Validaciones.isObjectNull(shvParamCliente.getNumHolding()) ? "" : shvParamCliente.getNumHolding().toString());
		miCliente.setDescripcionHolding(shvParamCliente.getDescripcionHolding());
		miCliente.setCuit(shvParamCliente.getCuit());
		miCliente.setAgenciaNegocio(Validaciones.isObjectNull(shvParamCliente.getIdAgenciaNegocio()) ? "" : shvParamCliente.getIdAgenciaNegocio().toString());
		miCliente.setDescripcionAgenciaNegocio(shvParamCliente.getDescripcionAgenciaNegocio());
		miCliente.setSegmentoAgencia(shvParamCliente.getSegmento().getDescripcion());
		miCliente.setIdProvincia(shvParamCliente.getProvincia().getIdProvincia());
		miCliente.setClienteOrigen(ClienteOrigenEnum.USUARIO.getDescripcion());
		miCliente.setIdClienteAgrupador(Validaciones.isObjectNull(shvParamCliente.getIdClienteLegado()) ? "" : shvParamCliente.getIdClienteLegado().toString());
		miCliente.setIdClientePerfil(Validaciones.isObjectNull(shvParamCliente.getIdClienteLegado()) ? "" : shvParamCliente.getIdClienteLegado().toString());
		miCliente.setRazonSocialClienteAgrupador(shvParamCliente.getRazonSocial());
		return miCliente;
	}
}
