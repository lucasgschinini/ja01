package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IValorMedioPagoServicio;
import ar.com.telecom.shiva.persistencia.dao.IValorMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamValorMedioPago;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ValorMedioPagoServicioImpl extends Servicio implements IValorMedioPagoServicio {

	@Autowired
	private IValorMedioPagoDao valorMedioPagoDao;

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
	}

	@Override
	public List<ShvParamValorMedioPago> buscarPorTipoMedioPago(String tipo) throws NegocioExcepcion {
		try {
			return (List<ShvParamValorMedioPago>) valorMedioPagoDao.buscarPorTipoMedioPago(tipo);
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public List<ShvParamValorMedioPago> buscarPorTipoValor(String tipo) throws NegocioExcepcion {
		try {
			return (List<ShvParamValorMedioPago>) valorMedioPagoDao.buscarPorTipoValor(tipo);
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	public IValorMedioPagoDao getValorMedioPagoDao() {
		return valorMedioPagoDao;
	}

	public void setValorMedioPagoDao(IValorMedioPagoDao valorMedioPagoDao) {
		this.valorMedioPagoDao = valorMedioPagoDao;
	}

}
