package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoCodigoExternoOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoConWorkflow;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineCodigoOperacionExternaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineSimplificadoCodigoOperacionExternaRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineSimplificadoConWorkflowRepositorio;
import ar.com.telecom.shiva.persistencia.repository.CobroOnlineSimplificadoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DocumentoAdjuntoRepositorio;

public class CobroOnlineDaoImpl extends Dao implements ICobroOnlineDao {
	
	@Autowired CobroOnlineRepositorio cobroOnlineRepositorio;
	@Autowired CobroOnlineAdjuntoRepositorio cobroOnlineAdjuntoRepositorio;
	@Autowired DocumentoAdjuntoRepositorio documentoAdjuntoRepositorio;
	@Autowired CobroOnlineSimplificadoRepositorio cobroOnlineSimplificadoRepositorio;
	@Autowired CobroOnlineSimplificadoConWorkflowRepositorio cobroOnlineSimplificadoConWorkflowRepositorio;
	@Autowired CobroOnlineSimplificadoCodigoOperacionExternaRepositorio cobroOnlineSimplificadoCodigoOperacionExternaRepositorio;
	@Autowired CobroOnlineCodigoOperacionExternaRepositorio cobroOnlineCodigoOperacionExternaRepositorio;
	
	/**
	 * Guarda un cobro nuevo o actualizado
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobro guardarCobro(ShvCobEdCobro cobro) throws PersistenciaExcepcion {
		try{
			ShvCobEdCobro cobroBD = cobroOnlineRepositorio.save(cobro);
			cobroOnlineRepositorio.flush();
			return cobroBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un cobro en la base.
	 */
	public ShvCobEdCobro buscarCobro(Long id) throws PersistenciaExcepcion {
		try {
			return cobroOnlineRepositorio.buscarCobroOnline(id);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un cobro en la base por idOperacion.
	 */
	public ShvCobEdCobro buscarCobroPorIdOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			return cobroOnlineRepositorio.buscarCobroPorIdOperacion(idOperacion);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Guarda los adjuntos del cobro online
	 */
	public ShvCobEdCobroAdjunto insertarDocumentoAjunto(ShvCobEdCobroAdjunto registroAdjunto)
			throws PersistenciaExcepcion {
		try {
			
			return cobroOnlineAdjuntoRepositorio.save(registroAdjunto);
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Lista los adjuntos del cobro online
	 */
	public List<ShvCobEdCobroAdjunto> buscarAdjuntosDelCobroOnline(Long idCobroOnline) throws PersistenciaExcepcion {
		try {
			
			List<ShvCobEdCobroAdjunto> lista = cobroOnlineAdjuntoRepositorio.buscarAdjuntosCobrosOnline(idCobroOnline);  
			
			return lista;
	
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public void eliminarDocumentoAdjuntoDelCobro(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion {
		
		List<ShvCobEdCobroAdjunto> lista = 
			cobroOnlineAdjuntoRepositorio.buscarPorIdAdjuntoCobrosOnline(documentoAdjunto.getIdValorAdjunto());
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			ShvCobEdCobroAdjunto regAdjunto = lista.get(0);
			cobroOnlineAdjuntoRepositorio.delete(regAdjunto);
			cobroOnlineAdjuntoRepositorio.flush();
			documentoAdjuntoRepositorio.delete(documentoAdjunto);
			documentoAdjuntoRepositorio.flush();
		}
	}
	
	@Override
	public List<ShvDocDocumentoAdjunto> buscarPorIdAdjuntoCobrosOnline(Long idAdjunto) throws PersistenciaExcepcion {
		try {
			List<ShvDocDocumentoAdjunto> list = new ArrayList<ShvDocDocumentoAdjunto>();
			
			List<ShvCobEdCobroAdjunto> lista = cobroOnlineAdjuntoRepositorio.buscarPorIdAdjuntoCobrosOnline(idAdjunto);  
			if(Validaciones.isCollectionNotEmpty(lista)){
				for (ShvCobEdCobroAdjunto cobrosAdjuntos : lista) {
					list.add(cobrosAdjuntos.getPk().getDocumentoAdjunto());
				}
			}
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvCobEdCliente> listarClientesDelCobroOnline(Long idCobroOnline) throws PersistenciaExcepcion {

		try {
			String queryDebito = "select cliente from ShvCobEdCliente as cliente "
					+ "where cliente.pk.cobro.idCobro= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(queryDebito);
			qp.addParametro(idCobroOnline);
			
			List<ShvCobEdCliente> list = (List<ShvCobEdCliente>) buscarUsandoQueryConParametros(qp);
			
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un cobro en la base.
	 */
	@SuppressWarnings("unchecked")
	public ShvCobEdCobroSimplificado buscarCobroSimplificado(Long id) throws PersistenciaExcepcion {
		try {
			String query = "select cobro from ShvCobEdCobroSimplificado as cobro "
					+ "where cobro.idOperacion= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(id);
			
			List<ShvCobEdCobroSimplificado> list = (List<ShvCobEdCobroSimplificado>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(list)){
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Busca un cobro en la base.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShvCobEdCobroSimplificado buscarCobroSimplificadoPorIdCobro(Long idCobro) throws PersistenciaExcepcion {
		try {
			String query = "select cobro from ShvCobEdCobroSimplificado as cobro "
					+ "where cobro.idCobro= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idCobro);
			
			List<ShvCobEdCobroSimplificado> list = (List<ShvCobEdCobroSimplificado>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(list)){
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ShvCobEdCobroSimplificadoConWorkflow buscarCobroSimplificadoConWorkflow(Long id) throws PersistenciaExcepcion {
		try {
			String query = "select cobro from ShvCobEdCobroSimplificadoConWorkflow as cobro where cobro.idCobro= ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(id);
			
			List<ShvCobEdCobroSimplificadoConWorkflow> list = (List<ShvCobEdCobroSimplificadoConWorkflow>) buscarUsandoQueryConParametros(qp);
			
			if (Validaciones.isCollectionNotEmpty(list)) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public ShvCobEdCobroAdjunto buscarAdjuntoPorGrupo(Long idOperacion, SociedadEnum sociedad, SistemaEnum sistema) throws PersistenciaExcepcion {
		try {
			
			String query = "select adjunto from ShvCobEdCobroAdjunto as adjunto "
			+ "join adjunto.pk.documentoAdjunto as doc " 
			+ "where doc.idValorAdjunto = "
			+ "(select otroDebito.idAdjunto from ShvCobEdOtrosDebito as otroDebito "
		    + "join otroDebito.pk.cobro as cob "
			+ "where cob.idOperacion = ? "
		    + "and otroDebito.sociedad = ? "
			+ "and otroDebito.sistema = ? "
			+ "and otrodebito.idAdjunto is not null)";
		
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
			qp.addCampoAlQuery(sociedad, Types.JAVA_OBJECT);
			qp.addCampoAlQuery(sistema,  Types.JAVA_OBJECT);
			
			List<ShvCobEdCobroAdjunto> list = (List<ShvCobEdCobroAdjunto>) buscarUsandoQueryConParametros(qp);
			
			if (Validaciones.isCollectionNotEmpty(list)) {
				return list.get(0);
			} else {
				return null;
			}


		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	/**
	 * Guarda un cobro nuevo o actualizado
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificado guardarCobroSimplificado(ShvCobEdCobroSimplificado cobro) throws PersistenciaExcepcion {
		try{
			ShvCobEdCobroSimplificado cobroBD = cobroOnlineSimplificadoRepositorio.save(cobro);
			cobroOnlineSimplificadoRepositorio.flush();
			return cobroBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * 
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvCobEdCobroSimplificadoConWorkflow guardarCobroSimplificadoConWorkflow(ShvCobEdCobroSimplificadoConWorkflow cobro) throws PersistenciaExcepcion {
		try{
			ShvCobEdCobroSimplificadoConWorkflow cobroBD = cobroOnlineSimplificadoConWorkflowRepositorio.save(cobro);
			cobroOnlineSimplificadoRepositorio.flush();
			return cobroBD;
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	@Override
	public ShvCobEdCobroSimplificadoCodigoExternoOperacion buscarCobroSimplificadoCodigoExternoOperacion(Long idOperacion) throws PersistenciaExcepcion {
		try {
			return cobroOnlineSimplificadoCodigoOperacionExternaRepositorio.find(idOperacion);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
}
