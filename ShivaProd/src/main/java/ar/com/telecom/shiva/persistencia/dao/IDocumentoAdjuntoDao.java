package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoAux;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjuntoBck;

public interface IDocumentoAdjuntoDao {

	
	ShvDocDocumentoAdjunto crear(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion;

	ShvDocDocumentoAdjunto buscarDocumentoAdjunto(Long idDocumentoAdjunto) throws PersistenciaExcepcion;
	
	List<ShvDocDocumentoAdjunto> listarDocumentosAdjuntosPorIdTransferencia(Long idTransferencia) throws PersistenciaExcepcion;

	void eliminarDocumentoAdjuntoARegistroAvcTransaccion(ShvDocDocumentoAdjunto documentoAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * Tratamiento de Encriptaciones
	 */
	public List<ShvDocDocumentoAdjuntoBck> listarDocsAdjuntosBck(int pagina, int cantXPagina) throws PersistenciaExcepcion;
	
	public List<ShvDocDocumentoAdjuntoAux> listarDocsAdjuntos(int pagina, int cantXPagina) throws PersistenciaExcepcion;
	public ShvDocDocumentoAdjuntoAux guardar(ShvDocDocumentoAdjuntoAux documentoAdjunto) throws PersistenciaExcepcion;
	ShvDocDocumentoAdjuntoAux buscarDocumentoAdjuntoAux(Long idDocumentoAdjunto) throws PersistenciaExcepcion;
	
	/**
	 * Fin - Encriptaciones
	 */
}
