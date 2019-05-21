package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDocumento;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDocumentoSimplificado;
import ar.com.telecom.shiva.persistencia.repository.DocumentoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DocumentoSimplificadoRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class DocumentoDaoImpl extends Dao implements IDocumentoDao{

	
	@Autowired DocumentoRepositorio documentoRepositorio;
	
	@Autowired DocumentoSimplificadoRepositorio documentoSimplificadoRepositorio;
	
	public ShvCobDocumento insertarActualizar(ShvCobDocumento documento)  throws PersistenciaExcepcion{
		try{
			ShvCobDocumento doc = documentoRepositorio.save(documento);
			documentoRepositorio.flush();
			return doc;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public ShvCobDocumentoSimplificado insertarActualizarDocumentoSimplificado(ShvCobDocumentoSimplificado documento)  throws PersistenciaExcepcion{
		try{
			ShvCobDocumentoSimplificado doc = documentoSimplificadoRepositorio.save(documento);
			documentoSimplificadoRepositorio.flush();
			return doc;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvCobDocumentoSimplificado> listarPendientes(String estado, Filtro scardFiltro) throws PersistenciaExcepcion {
		try {
			StringBuffer query = new StringBuffer();
			query.append("from ShvCobDocumentoSimplificado where estado = ? and fechaCreacion <= TO_TIMESTAMP( ? , ? ) order by idOperacion,tipoOperacion,idClienteLegado ");

			QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
			
			qp.addParametro(new String(estado));
			qp.addParametro(new String(scardFiltro.getFechaHasta() + " 23:59:59"));
			qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
			
			List<ShvCobDocumentoSimplificado> list = (List<ShvCobDocumentoSimplificado>) buscarUsandoQueryConParametros(qp); 
			return list;			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
