package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoAux;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoBck;
import ar.com.telecom.shiva.persistencia.repository.DocumentoAdjuntoAuxRepositorio;
import ar.com.telecom.shiva.persistencia.repository.DocumentoAdjuntoRepositorio;
import ar.com.telecom.shiva.persistencia.repository.RegistroAVCAdjuntoRepositorio;

public class DocumentoAdjuntoDaoImpl extends Dao implements IDocumentoAdjuntoDao{

	@Autowired DocumentoAdjuntoRepositorio documentoAdjuntoRepositorio;
	@Autowired DocumentoAdjuntoAuxRepositorio documentoAdjuntoAuxRepositorio;
	@Autowired RegistroAVCAdjuntoRepositorio registroAVCAdjuntoRepositorio;
	
	public ShvDocDocumentoAdjunto crear(ShvDocDocumentoAdjunto documentoAdjunto)  throws PersistenciaExcepcion{
		try{
			ShvDocDocumentoAdjunto archivoAVCBD = documentoAdjuntoRepositorio.save(documentoAdjunto);
			documentoAdjuntoRepositorio.flush();
			return archivoAVCBD;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvDocDocumentoAdjunto buscarDocumentoAdjunto(Long idDocumentoAdjunto) throws PersistenciaExcepcion {
		try {
			String query = "select doc from ShvDocDocumentoAdjunto as doc where doc.idValorAdjunto=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idDocumentoAdjunto);
			
			List<ShvDocDocumentoAdjunto> lista = (List<ShvDocDocumentoAdjunto>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvDocDocumentoAdjunto> listarDocumentosAdjuntosPorIdTransferencia(
			Long idTransferencia) throws PersistenciaExcepcion {
		try {
			String query = "select doc from ShvAvcRegistroAdjunto as regdoc "
					+ "join regdoc.shvAvcRegistroAdjuntoPk.registroAvc as reg "
					+ "join regdoc.shvAvcRegistroAdjuntoPk.documentoAdjunto as doc "
					+ "where reg.idRegistroAvc=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idTransferencia);
			
			List<ShvDocDocumentoAdjunto> lista = (List<ShvDocDocumentoAdjunto>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void eliminarDocumentoAdjuntoARegistroAvcTransaccion(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion {
		String query = "select regdoc from ShvAvcRegistroAdjunto as regdoc "
				+ "join regdoc.shvAvcRegistroAdjuntoPk.documentoAdjunto as doc "
				+ "where doc.idValorAdjunto=?";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(documentoAdjunto.getIdValorAdjunto());
		List<ShvAvcRegistroAdjunto> lista = (List<ShvAvcRegistroAdjunto>) buscarUsandoQueryConParametros(qp);
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			ShvAvcRegistroAdjunto regAdjunto = lista.get(0);
			registroAVCAdjuntoRepositorio.delete(regAdjunto);
			registroAVCAdjuntoRepositorio.flush();
			documentoAdjuntoRepositorio.delete(documentoAdjunto);
			documentoAdjuntoRepositorio.flush();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvDocDocumentoAdjuntoAux> listarDocsAdjuntos(int pagina, int cantXPagina) throws PersistenciaExcepcion {
		if (pagina < 1 || cantXPagina <= 0) {
			throw new PersistenciaExcepcion("Argumentos no válidos");
		}
		String query = "from ShvDocDocumentoAdjuntoAux order by idValorAdjunto";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		return (List<ShvDocDocumentoAdjuntoAux>) buscarUsandoQueryConParametros(qp, (pagina - 1)*cantXPagina , cantXPagina);
	}

	@Override
	public ShvDocDocumentoAdjuntoAux guardar(ShvDocDocumentoAdjuntoAux documentoAdjunto) throws PersistenciaExcepcion {
		try{
			documentoAdjunto = documentoAdjuntoAuxRepositorio.save(documentoAdjunto);
			documentoAdjuntoAuxRepositorio.flush();
			return documentoAdjunto;
		} catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvDocDocumentoAdjuntoBck> listarDocsAdjuntosBck(int pagina, int cantXPagina) throws PersistenciaExcepcion {
		if (pagina < 1 || cantXPagina <= 0) {
			throw new PersistenciaExcepcion("Argumentos no válidos");
		}
		String query = "from ShvDocDocumentoAdjuntoBck order by idValorAdjunto";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		return (List<ShvDocDocumentoAdjuntoBck>) buscarUsandoQueryConParametros(qp, (pagina - 1)*cantXPagina , cantXPagina);
	}

	@SuppressWarnings("unchecked")
	public ShvDocDocumentoAdjuntoAux buscarDocumentoAdjuntoAux(Long idDocumentoAdjunto) throws PersistenciaExcepcion {
		try {
			String query = "select doc from ShvDocDocumentoAdjuntoAux as doc where doc.idValorAdjunto=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idDocumentoAdjunto);
			
			List<ShvDocDocumentoAdjuntoAux> lista = (List<ShvDocDocumentoAdjuntoAux>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
}
