package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.TipoMedioPagoDto;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class TipoMedioPagoServicioImpl extends Servicio implements ITipoMedioPagoServicio{

	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;

	@Override
	public TipoMedioPagoDto buscar(String id) throws NegocioExcepcion {
		
		TipoMedioPagoDto dto = null;
		ShvParamTipoMedioPago modelo = null;
		
		try {
			modelo = (ShvParamTipoMedioPago) tipoMedioPagoDao.buscar(id);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		// TODO: El mapeo lo hago aqui ya que no vale la pena armar un mapeador para una tabla parametrica
		// Si vemos que su utilización puede ser mas masiva, entonces lo armamos
		
		if (!Validaciones.isObjectNull(modelo)) {
			dto = new TipoMedioPagoDto();
			
			dto.setIdTipoMedioPago(modelo.getIdTipoMedioPago());
			dto.setDescripcion(modelo.getDescripcion());
			dto.setGeneraIntereses(modelo.getGeneraIntereses());
			dto.setOrdenImputacion(modelo.getOrdenImputacion());
		} 
		
		return dto;
	}

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
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
	}

	@Override
	public Collection<ShvParamTipoMedioPago> listarMediosPago() throws NegocioExcepcion{
		try {
			return tipoMedioPagoDao.listarMediosPago();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public ShvParamTipoMedioPago buscarMedioPago(TipoMedioPagoEnum tipoMedioPago) throws NegocioExcepcion {
		return tipoMedioPagoDao.buscarMedioPago(tipoMedioPago);
	}

	
	public ITipoMedioPagoDao getTipoMedioPagoDao() {
		return tipoMedioPagoDao;
	}

	public void setTipoMedioPagoDao(ITipoMedioPagoDao tipoMedioPagoDao) {
		this.tipoMedioPagoDao = tipoMedioPagoDao;
	}

}
