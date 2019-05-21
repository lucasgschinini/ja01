package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaCantidadHilosEnCurso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;

public interface IProcesamientoHilosCobrosDao {

	public List<ResultadoBusquedaCantidadHilosEnCurso> obtenerCantidadHilosEnCurso(EstadoProcesamientoHilosEnum estado, TipoImputacionEnum tipoImputacion) throws PersistenciaExcepcion;
	
	ShvCobProcHilosCobros insertarActualizarHilo(ShvCobProcHilosCobros hilo) throws PersistenciaExcepcion;
	
}
