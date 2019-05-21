package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineOtrosDebitoDao;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineOtrosDebitoRepositorio;

public class CobroOnlineOtrosDebitoDaoImpl extends Dao implements ICobroOnlineOtrosDebitoDao {

	@Autowired
	CobroOnlineOtrosDebitoRepositorio	otrosDebitoRepositorio;
	@Autowired
	CobroOnlineAdjuntoRepositorio		cobroOnlineAdjuntoRepositorio;

	@Autowired
	IVistaSoporteServicio				vistaSoporteServicio;

	/**
	 * Borra un debito
	 * @param debito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarOtrosDebitosDelCobro(Long idCobro) throws PersistenciaExcepcion {
		try {
			otrosDebitoRepositorio.borrarOtrosDebitos(idCobro);
			otrosDebitoRepositorio.flush();

		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Transactional(readOnly = false, rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public void eliminarAdjuntosDeOtrosDeb(ArrayList<String> idsAdjuntos) throws PersistenciaExcepcion {
		try {
			for (String idAdjunto : idsAdjuntos) {
				cobroOnlineAdjuntoRepositorio.eliminarAdjuntoPorId(Long.valueOf(idAdjunto).longValue());
			}
			cobroOnlineAdjuntoRepositorio.flush();

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}