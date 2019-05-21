package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITransaccionCobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;
import ar.com.telecom.shiva.persistencia.repository.TransaccionCobroRepositorio;
import ar.com.telecom.shiva.persistencia.repository.TransaccionSimpleCobroRepositorio;

public class TransaccionCobroDaoImpl extends Dao implements ITransaccionCobroDao {

	@Autowired 
	TransaccionCobroRepositorio transaccionCobroRepositorio;
	@Autowired 
	TransaccionSimpleCobroRepositorio transaccionSimpleCobroRepositorio;
	
	/**
	 * Inserta un cobro
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobTransaccionSimple buscarTransaccionCobroPorTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		try{
			
			ShvCobTransaccionSimple cobroTransaccion = 
					transaccionSimpleCobroRepositorio.buscarTransaccionCobroPorTransaccion(idTransaccion);
			return cobroTransaccion;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyNumero(
			Long idOperacion, Integer numeroTransaccion) throws PersistenciaExcepcion {
	
		try{
			
			ShvCobTransaccionSimple cobroTransaccion = 
					transaccionSimpleCobroRepositorio.buscarTransaccionCobroPorOperacionyNumero(idOperacion, numeroTransaccion);
			return cobroTransaccion;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}


	@Override
	public ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyTransaccion(
			Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion {
		
		try{
			ShvCobTransaccionSimple cobroTransaccion = 
					transaccionSimpleCobroRepositorio.buscarTransaccionCobroPorOperacionyTransaccion(idOperacion, idTransaccion);
			return cobroTransaccion;
			
		} catch (Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Borra una transaccion
	 * @param debito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unused")
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarTransaccionPorIdTransaccion(Integer idTransaccion) throws PersistenciaExcepcion {
		try {
			int countDelete = transaccionCobroRepositorio.borrarTransaccionPorIdTransaccion(idTransaccion);
			transaccionCobroRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	@Override
	public Integer verificarNroTransaccionFicticio(Long idOperacion, Integer numeroTransaccionFicticio) throws PersistenciaExcepcion {

		QueryParametrosUtil qp = new QueryParametrosUtil();
		Integer resultado = 0;
		String query = "select count (*) as cantidad from shv_cob_transaccion where id_operacion = ? and numero_transaccion_ficticio = ?";
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(numeroTransaccionFicticio, Types.NUMERIC);
		qp.setSql(query);
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(qp);

		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("cantidad"))) {
				resultado = Integer.valueOf(archivo.get("cantidad").toString());
			}
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer buscarNroTransaccionFicticio(Long idOperacion, Integer idTransaccion)  {
		List<Integer> list = null;
		try {	
			QueryParametrosUtil qp = new QueryParametrosUtil(
					  "select numeroTransaccionFicticio from ShvCobTransaccion "
					+ "where operacion.idOperacion=? and numeroTransaccion=?");
			qp.addParametro(idOperacion);
			qp.addParametro(idTransaccion);
			
			list = (List<Integer>) buscarUsandoQueryConParametros(qp);
			} catch (PersistenciaExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(Validaciones.isCollectionNotEmpty(list)){
			return list.get(0);
		} else {
			return null;
		}
			
	}
	/**
	 * Getters & Setters
	 */
	public TransaccionCobroRepositorio getTransaccionCobroRepositorio() {
		return transaccionCobroRepositorio;
	}

	public void setTransaccionCobroRepositorio(
			TransaccionCobroRepositorio transaccionCobroRepositorio) {
		this.transaccionCobroRepositorio = transaccionCobroRepositorio;
	}

	public TransaccionSimpleCobroRepositorio getTransaccionSimpleCobroRepositorio() {
		return transaccionSimpleCobroRepositorio;
	}

	public void setTransaccionSimpleCobroRepositorio(
			TransaccionSimpleCobroRepositorio transaccionSimpleCobroRepositorio) {
		this.transaccionSimpleCobroRepositorio = transaccionSimpleCobroRepositorio;
	}
	

}
