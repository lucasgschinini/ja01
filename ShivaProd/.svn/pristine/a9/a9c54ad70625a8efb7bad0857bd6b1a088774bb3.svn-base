package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoNotificacionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoNotificacionRepositorio;
import ar.com.telecom.shiva.persistencia.repository.LegajoChequeRechazadoNotificacionSimplificadoRepositorio;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class LegajoChequeRechazadoNotificacionDaoImp extends Dao implements ILegajoChequeRechazadoNotificacionDao {
	@Autowired
	private LegajoChequeRechazadoNotificacionRepositorio legajoChequeRechazadoNotificacionRepositorio;
	@Autowired
	private LegajoChequeRechazadoNotificacionSimplificadoRepositorio legajoChequeRechazadoNotificacionSimplificadoRepositorio;
	@Override
	public ShvLgjNotificacion guardar(ShvLgjNotificacion shvLgjNotificacion)throws PersistenciaExcepcion {
		return this.legajoChequeRechazadoNotificacionRepositorio.saveAndFlush(shvLgjNotificacion);
	}

	@Override
	public ShvLgjNotificacionSimplificado guardar(ShvLgjNotificacionSimplificado shvLgjNotificacion) throws PersistenciaExcepcion {
		return this.legajoChequeRechazadoNotificacionSimplificadoRepositorio.saveAndFlush(shvLgjNotificacion);
	}
	@Override
	public ShvLgjNotificacion buscar(Long idNotificacion) throws PersistenciaExcepcion {
		return this.legajoChequeRechazadoNotificacionRepositorio.findOne(idNotificacion);
	}

	@Override
	public List<ShvLgjNotificacionSimplificado> lista(Long idLegajoChequeRechazado) throws PersistenciaExcepcion {
		return this.legajoChequeRechazadoNotificacionSimplificadoRepositorio.listar(idLegajoChequeRechazado);
	}
	
	@Override
	public int cambiarEstado(Long idNotificacion, Date fechaModificacion, EstadoNotificacionEnum estado) throws PersistenciaExcepcion {
		return this.legajoChequeRechazadoNotificacionSimplificadoRepositorio.cambiarEstado(idNotificacion, fechaModificacion, estado);
	}
	
}
