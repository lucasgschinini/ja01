package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoSimplificado;
import ar.com.telecom.shiva.persistencia.repository.CompensacionRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CompensacionRepositorioSimplificado;
import ar.com.telecom.shiva.persistencia.repository.ReciboPreImpresoCompensacionSimplificadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ReciboPreImpresoRepositorio;

public class ReciboPreImpresoDaoImpl extends Dao implements IReciboPreImpresoDao{

	@Autowired ReciboPreImpresoRepositorio reciboRepositorio;
	@Autowired ReciboPreImpresoCompensacionSimplificadoRepositorio reciboPreImpresoSimplificadoRepositorio;
	@Autowired CompensacionRepositorio compensacionRepositorio;
	@Autowired CompensacionRepositorioSimplificado compensacionRepositorioSimplificado;

	

	@Override
	public ShvTalReciboPreImpreso buscarRecibo(Integer idRecibo) throws PersistenciaExcepcion {
		try {
			return reciboRepositorio.findOne(idRecibo);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public ShvTalReciboPreImpresoCompensacionSimplificado buscarReciboCompensacionSimplificado(Integer idRecibo) throws PersistenciaExcepcion {
		try {
			return reciboPreImpresoSimplificadoRepositorio.findOne(idRecibo);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvTalReciboPreImpreso actualizarRecibo(ShvTalReciboPreImpreso reciboModelo)
			throws PersistenciaExcepcion {
		try {
			ShvTalReciboPreImpreso reciboBD = reciboRepositorio.save(reciboModelo);
			
			reciboRepositorio.flush();
			return reciboBD;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public ShvTalReciboPreImpresoCompensacionSimplificado actualizarReciboSimplificado(ShvTalReciboPreImpresoCompensacionSimplificado reciboModeloSimplificado)
			throws PersistenciaExcepcion {
		try {
			ShvTalReciboPreImpresoCompensacionSimplificado reciboBDSimplificado = reciboPreImpresoSimplificadoRepositorio.save(reciboModeloSimplificado);
			
			reciboPreImpresoSimplificadoRepositorio.flush();
			return reciboBDSimplificado;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public void actualizarCompensaciones(ShvTalReciboPreImpreso reciboModelo)
			throws PersistenciaExcepcion {
		try {
			reciboRepositorio.saveAndFlush(reciboModelo);		
			compensacionRepositorio.deleteInBatch(reciboModelo.getShvTalCompensacion());
			compensacionRepositorio.flush();
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public void actualizarCompensaciones(Set<ShvTalCompensacionSimplificado> listaCompensacionesSimplificadas) throws PersistenciaExcepcion {
		try {
			compensacionRepositorioSimplificado.save(listaCompensacionesSimplificadas);
			compensacionRepositorioSimplificado.flush();
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ReciboPreImpresoRepositorio getReciboRepositorio() {
		return reciboRepositorio;
	}

	public void setReciboRepositorio(ReciboPreImpresoRepositorio reciboRepositorio) {
		this.reciboRepositorio = reciboRepositorio;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ShvTalReciboPreImpreso buscarPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion {

		try {
			String query = "from ShvTalReciboPreImpreso where numeroRecibo=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(numeroRecibo);
			List<ShvTalReciboPreImpreso> reciboModelo = ((List<ShvTalReciboPreImpreso>) buscarUsandoQueryConParametros(qp));
			
			return (!reciboModelo.isEmpty()) ? reciboModelo.get(0) : null;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ShvTalReciboPreImpresoSimplificado buscarSimplificadoPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion {

		try {
			String query = "from ShvTalReciboPreImpresoSimplificado where numeroRecibo=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(numeroRecibo);
			List<ShvTalReciboPreImpresoSimplificado> reciboModelo = ((List<ShvTalReciboPreImpresoSimplificado>) buscarUsandoQueryConParametros(qp));
			
			return (!reciboModelo.isEmpty()) ? reciboModelo.get(0) : null;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ShvTalReciboPreImpresoCompensacionSimplificado buscarSimplificadoConCompensacionPorNumeroRecibo(String numeroRecibo) throws PersistenciaExcepcion {

		try {
			String query = "from ShvTalReciboPreImpresoCompensacionSimplificado where numeroRecibo=? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(numeroRecibo);
			List<ShvTalReciboPreImpresoCompensacionSimplificado> reciboModelo = ((List<ShvTalReciboPreImpresoCompensacionSimplificado>) buscarUsandoQueryConParametros(qp));
			
			return (!reciboModelo.isEmpty()) ? reciboModelo.get(0) : null;

		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
