package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReversionValorDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvValReversionValor;
import ar.com.telecom.shiva.persistencia.repository.ReversionValorRepositorio;

public class ReversionValorDaoImpl extends Dao implements IReversionValorDao {

	@Autowired ReversionValorRepositorio reversionValorRepositorio;
	
	public ShvValReversionValor crearReversionValor(ShvValReversionValor reversionValor) throws PersistenciaExcepcion {
		try{
			
			ShvValReversionValor revValor = reversionValorRepositorio.save(reversionValor);
			reversionValorRepositorio.flush();
			return revValor;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvValReversionValor buscarReversionValor(String idValorPorReversion) throws PersistenciaExcepcion {
		String query = "from ShvValReversionValor as revValor" + 
				" where revValor.shvValReversionValorPK.valorPorReversion.idValorPorReversion=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(new Long(idValorPorReversion));
		
		List<ShvValReversionValor> reversionValor = (List<ShvValReversionValor>) buscarUsandoQueryConParametros(qp);
		if (Validaciones.isCollectionNotEmpty(reversionValor)) {
			return reversionValor.get(0);
		} else {
			return null;
		}
	}
	
}
