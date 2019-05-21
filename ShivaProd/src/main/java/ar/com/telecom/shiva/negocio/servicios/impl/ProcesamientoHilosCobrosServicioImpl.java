package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IProcesamientoHilosCobrosServicio;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCantidadHilosEnCurso;
import ar.com.telecom.shiva.persistencia.dao.IProcesamientoHilosCobrosDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

@Service
public class ProcesamientoHilosCobrosServicioImpl extends Servicio implements IProcesamientoHilosCobrosServicio{
	
	@Autowired
	IProcesamientoHilosCobrosDao hilosCobrosDao;

	@Override
	public List<ResultadoBusquedaCantidadHilosEnCurso> obtenerCantidadHilosEnCurso(EstadoProcesamientoHilosEnum estado, TipoImputacionEnum tipoImputacion) throws PersistenciaExcepcion {
		
		List<ResultadoBusquedaCantidadHilosEnCurso> lista = hilosCobrosDao.obtenerCantidadHilosEnCurso(estado,tipoImputacion);
		
		return lista;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvCobProcHilosCobros insertarActualizarHilo(ShvCobProcHilosCobros hilo) throws PersistenciaExcepcion {
		
		ShvCobProcHilosCobros hiloNuevo = hilosCobrosDao.insertarActualizarHilo(hilo);
		return hiloNuevo;
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

	public IProcesamientoHilosCobrosDao getHilosCobrosDao() {
		return hilosCobrosDao;
	}

	public void setHilosCobrosDao(IProcesamientoHilosCobrosDao hilosCobrosDao) {
		this.hilosCobrosDao = hilosCobrosDao;
	}

	

}
