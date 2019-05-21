package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoCajaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCajaHistorialEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICajaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCajCaja;
import ar.com.telecom.shiva.persistencia.modelo.ShvCajCajaHistorial;
import ar.com.telecom.shiva.persistencia.repository.CajaHistorialRepositorio;

public class CajaDaoImpl extends Dao implements ICajaDao{
	
	@Autowired
	CajaHistorialRepositorio cajaHistorialRepositorio;

	/**
	 * Busca las cajas abiertas.
	 * @return Una lista de <code>ShvCajCaja</code> con los resultados de la búsqueda.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCajCajaHistorial> buscarCajasAbiertas(String fecha) throws PersistenciaExcepcion {
		String query = "select cajaHistorial from ShvCajCajaHistorial as cajaHistorial " +
				" where cajaHistorial.estado=? " +
				" and TO_DATE(?,'dd/mm/yyyy') > cajaHistorial.fechaApertura" +
				" and cajaHistorial.fechaCierre is null";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(EstadoCajaHistorialEnum.ABIERTA);
		qp.addParametro(fecha);
		
		List<ShvCajCajaHistorial> cajas = (List<ShvCajCajaHistorial>) buscarUsandoQueryConParametros(qp);
		return cajas;
	}

	/**
	 * Busca todas las cajas que se encuentran habilidadas.
	 * @return Una lista de <code>ShvCajCaja</code> con los resultados de la búsqueda.
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unchecked")
	public List<ShvCajCaja> buscarCajasHabilitadas()
			throws PersistenciaExcepcion {
		String query = "select caja from ShvCajCaja as caja where caja.estado=? ";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(EstadoCajaEnum.HABILITADA);
		
		List<ShvCajCaja> cajas = (List<ShvCajCaja>)buscarUsandoQueryConParametros(qp);
		return cajas;
	}

	@Override
	public void abrirCajas() throws PersistenciaExcepcion {
		List<ShvCajCaja> cajas = buscarCajasHabilitadas();
		for (ShvCajCaja caja : cajas) {
			ShvCajCajaHistorial cajaHistorial = crearCajaHistorial(caja, EstadoCajaHistorialEnum.ABIERTA);
			cajaHistorialRepositorio.save(cajaHistorial);
			cajaHistorialRepositorio.flush();
		}
	}

	@Override
	public void cerrarCajas(String fecha) throws PersistenciaExcepcion {
		List<ShvCajCajaHistorial> cajas = buscarCajasAbiertas(fecha);
		for (ShvCajCajaHistorial cajaHistorial : cajas) {
			cajaHistorial.setFechaCierre(new Date());
			cajaHistorial.setEstado(EstadoCajaHistorialEnum.CERRADA);
			cajaHistorialRepositorio.save(cajaHistorial);
			cajaHistorialRepositorio.flush();
		}
	}
	
	/**
	 * Setea a una caja historial dada por parametro los flags de reportes en default (NO).
	 * @param cajaHistorial
	 */
	private void setReportesCajaHistorialEnDefault(ShvCajCajaHistorial cajaHistorial) {
		cajaHistorial.setContabilidad(SiNoEnum.NO);
		cajaHistorial.setDacota(SiNoEnum.NO);
		cajaHistorial.setScard(SiNoEnum.NO);
		cajaHistorial.setGeo(SiNoEnum.NO);
		cajaHistorial.setTagetik(SiNoEnum.NO);
		cajaHistorial.setMorosidad(SiNoEnum.NO);
		cajaHistorial.setSigma(SiNoEnum.NO);
	}
	
	/**
	 * Crea una caja historial con el estado deseado y los flags de reportes en NO por default 
	 * @param caja
	 * @param estado
	 * @return Un <code>ShvCajCajaHistorial</code>.
	 */
	private ShvCajCajaHistorial crearCajaHistorial(ShvCajCaja caja, 
			EstadoCajaHistorialEnum estado) 
	{		
		ShvCajCajaHistorial cajaHistorial = new ShvCajCajaHistorial();
		cajaHistorial.setCaja(caja);
		cajaHistorial.setEstado(estado);
		cajaHistorial.setFechaApertura(new Date());
		setReportesCajaHistorialEnDefault(cajaHistorial);
		return cajaHistorial;
	}
}
