package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICompensacionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.persistencia.repository.ValorRepositorio;

public class CompensacionDaoImpl extends Dao implements ICompensacionDao{

	@Autowired ValorRepositorio valorRepositorio;
	
	@SuppressWarnings("unchecked")
	public List<ShvTalCompensacion> listarCompensacionesPorIdRecibo(Integer idRecibo) throws PersistenciaExcepcion {
		try {
			String query = "from ShvTalCompensacion where reciboPreImpreso.idReciboPreimpreso=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idRecibo);
			
			return (List<ShvTalCompensacion>) buscarUsandoQueryConParametros(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}	
	

}
