package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IReglaCobroServicio;
import ar.com.telecom.shiva.persistencia.dao.IReglaCobroDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaCobro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ReglaCobroFiltro;


public class ReglaCobroServicioImpl extends Servicio implements IReglaCobroServicio{
	

	@Autowired IReglaCobroDao reglaCobroDao;
	
	
	public List<ShvParamReglaCobro> buscar(ReglaCobroFiltro reglaCobroFiltro)
			throws PersistenciaExcepcion, NegocioExcepcion {
		
		List<ShvParamReglaCobro> list =  reglaCobroDao.buscar(reglaCobroFiltro);
		
		return list;
	}
	

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

}
