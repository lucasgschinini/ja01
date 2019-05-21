package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ITransaccionCobroDao;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class TransaccionCobroServicioImpl extends Servicio implements ITransaccionCobroServicio {

	@Autowired 
	ITransaccionCobroDao transaccionCobroDao;
	
	@Autowired
	IWorkflowCobros workflowCobros;
	
	@Autowired
	ICobroDao cobroDao;
	
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
	public Integer buscarIdTransaccion(Long idOperacion, Integer numeroTransaccion) throws NegocioExcepcion {
		try {
			ShvCobTransaccionSimple transaccionCobro = transaccionCobroDao.buscarTransaccionCobroPorOperacionyNumero(idOperacion, numeroTransaccion);
			if (transaccionCobro != null) {
				return transaccionCobro.getIdTransaccion();
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Integer buscarNumeroTransaccion(Long idOperacion, Integer idTransaccion) throws NegocioExcepcion {
		try {
			ShvCobTransaccionSimple transaccionCobro = transaccionCobroDao.buscarTransaccionCobroPorOperacionyTransaccion(idOperacion, idTransaccion);
			if (transaccionCobro != null) {
				return transaccionCobro.getNumeroTransaccion();
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void borrarTransaccionPorIdTransaccion(Integer idTransaccion) throws NegocioExcepcion {
		try {
			
			transaccionCobroDao.borrarTransaccionPorIdTransaccion(idTransaccion);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}

	public ITransaccionCobroDao getTransaccionCobroDao() {
		return transaccionCobroDao;
	}

	public void setTransaccionCobroDao(ITransaccionCobroDao transaccionCobroDao) {
		this.transaccionCobroDao = transaccionCobroDao;
	}	
}
