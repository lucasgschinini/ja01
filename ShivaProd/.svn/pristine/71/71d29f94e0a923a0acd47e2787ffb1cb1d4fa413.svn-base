package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdClienteSimplificado;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineClienteRepositorio;

public class CobroOnlineClienteDaoImpl extends Dao implements ICobroOnlineClienteDao {
	
	@Autowired CobroOnlineClienteRepositorio clienteRepositorio;
	
	
	/**
	 * Guardo el cliente
	 */
	@Override
	public ShvCobEdCliente guardarCliente(ShvCobEdCliente cliente)
			throws PersistenciaExcepcion {
		
		try{
			ShvCobEdCliente clienteBD = clienteRepositorio.save(cliente);
			clienteRepositorio.flush();
			return clienteBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * Borra clientes del cobro
	 * @param idCobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarClientesDelCobro(Long idCobro)
			throws PersistenciaExcepcion {
		try {
			int countDelete = clienteRepositorio.borrarClientesDelCobro(idCobro);
			clienteRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
		
	}

	@Override
	public List<ShvCobEdCliente> listarClientesPorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		try {
			List<ShvCobEdCliente> clientesCobro = clienteRepositorio.listarClientesPorIdCobro(idCobro);
			return clientesCobro;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobEdCliente> listarClientesPorIdOperacionCobro(Long idOperacion) throws PersistenciaExcepcion {
		try {
			List<ShvCobEdCliente> clientesCobro = clienteRepositorio.listarClientesPorIdOperacionCobro(idOperacion);
			return clientesCobro;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobEdCliente buscarClientePorIdCobroYIdClienteLegado(Long idCobro, Long idClienteLegado) throws PersistenciaExcepcion {
		try {
			ShvCobEdCliente clientesCobro = clienteRepositorio.buscarClientePorIdCobroYIdClienteLegado(idCobro, idClienteLegado);
			return clientesCobro;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobEdCliente buscarClientePorIdOperacionCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado) throws PersistenciaExcepcion {
		try {
			ShvCobEdCliente clientesCobro = clienteRepositorio.buscarClientePorIdOperacionCobroYIdClienteLegado(idOperacion, idClienteLegado);
			return clientesCobro;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobEdCliente buscarClientePorIdOperacionDesCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado) throws PersistenciaExcepcion {
		try {
			ShvCobEdCliente clientesCobro = clienteRepositorio.buscarClientePorIdOperacionDesCobroYIdClienteLegado(idOperacion, idClienteLegado);
			return clientesCobro;
			
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobEdClienteSimplificado> listarClientesSimplificadosPorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		String query = "select cli from ShvCobEdClienteSimplificado as cli "
				+ " where cli.pk.idCobro=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(idCobro, Types.NUMERIC);
		
		List<ShvCobEdClienteSimplificado> lista = (List<ShvCobEdClienteSimplificado>) buscarUsandoQueryConParametros(qp);
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			return lista;
		}
		return null;
	}
	
}
