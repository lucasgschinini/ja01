package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDocumento;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDocumentoSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;


public interface IDocumentoDao {

	ShvCobDocumento insertarActualizar(ShvCobDocumento documento)  throws PersistenciaExcepcion;
	
	List<ShvCobDocumentoSimplificado> listarPendientes(String estado, Filtro scardFiltro) throws PersistenciaExcepcion; 
	
	ShvCobDocumentoSimplificado insertarActualizarDocumentoSimplificado(ShvCobDocumentoSimplificado documento)  throws PersistenciaExcepcion; 
}
