package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IValorConstanciaRecepcionServicio;
import ar.com.telecom.shiva.persistencia.dao.IValorConstanciaRecepcionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValorPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValConstanciaRecepcionValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ValorConstanciaRecepcionServicioImpl extends Servicio implements IValorConstanciaRecepcionServicio {

	
	@Autowired 
	IValorConstanciaRecepcionDao valorConstanciaRecepcionDao;

	/**
	 * Graba la ShvValConstanciaRecepcion en la base.
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvValConstanciaRecepcion actualizarConstanciaRecepcion(ShvValConstanciaRecepcion constancia) throws NegocioExcepcion{
		try {
			return valorConstanciaRecepcionDao.actualizarConstanciaRecepcion(constancia);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public ShvValConstanciaRecepcionValor buscarConstanciaRecepcionAsignadaAValor(Long idValor) throws NegocioExcepcion {
		try {
			return valorConstanciaRecepcionDao.buscarConstanciaRecepcionAsignadaAValor(idValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public ShvValConstanciaRecepcionValorSimplificado buscarConstanciaRecepcionAsignadaAValorSimplificado(Long idValor) throws NegocioExcepcion {
		try {
			return valorConstanciaRecepcionDao.buscarConstanciaRecepcionAsignadaAValorSimplificado(idValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public ShvValConstanciaRecepcionValor actualizarConstanciaRecepcionValor(ShvValConstanciaRecepcionValor nuevaConstancia) throws NegocioExcepcion {
		try {
			return valorConstanciaRecepcionDao.actualizarConstanciaRecepcionValor(nuevaConstancia);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public List<ShvValValor> buscarValoresAsociadosAConstancia(Integer idConstanciaRecepcion) throws NegocioExcepcion {
		try {
			return valorConstanciaRecepcionDao.buscarValoresAsociadosAConstancia(idConstanciaRecepcion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValConstanciaRecepcionValor actualizarRelacionConstanciaValor(ShvValValor valor, ShvValConstanciaRecepcion nuevaConstancia) throws NegocioExcepcion {
		try {
			ShvValConstanciaRecepcionValor constanciaRecepcionValorActual = buscarConstanciaRecepcionAsignadaAValor(valor.getIdValor());
			valorConstanciaRecepcionDao.eliminarRelacionConstanciaValor(constanciaRecepcionValorActual);
	//		constanciaRecepcionValorActual.getShvValConstanciaRecepcionValorPk().setConstanciaRecepcion(nuevaConstancia);
			ShvValConstanciaRecepcionValor nuevaConstanciaValor = new ShvValConstanciaRecepcionValor();
			ShvValConstanciaRecepcionValorPk shvValConstanciaRecepcionValorPk = new ShvValConstanciaRecepcionValorPk();
			shvValConstanciaRecepcionValorPk.setValor(valor);
			shvValConstanciaRecepcionValorPk.setConstanciaRecepcion(nuevaConstancia);
			nuevaConstanciaValor.setShvValConstanciaRecepcionValorPk(shvValConstanciaRecepcionValorPk );
			actualizarConstanciaRecepcionValor(nuevaConstanciaValor);
			return nuevaConstanciaValor;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
	}
	
	@Override
	public List<ShvValValor> retornarListaValores(List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor) throws NegocioExcepcion {
		 List<ShvValValor> valores = new ArrayList<ShvValValor>();
		for (ShvValConstanciaRecepcionValor shvValConstanciaRecepcionValor : constanciaRecepcionValor) {
			valores.add(shvValConstanciaRecepcionValor.getShvValConstanciaRecepcionValorPk().getValor());
		}
		return valores;
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

	public IValorConstanciaRecepcionDao getValorConstanciaRecepcionDao() {
		return valorConstanciaRecepcionDao;
	}

	public void setValorConstanciaRecepcionDao(
			IValorConstanciaRecepcionDao valorConstanciaRecepcionDao) {
		this.valorConstanciaRecepcionDao = valorConstanciaRecepcionDao;
	}

}