package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteHistoricoDeValores;

public interface IReporteValoresPorEmpresaDao {

	/**
	 * Se obtendra todos los valores de los clientes que no pertenecen a Telecom Argentina.-
	 *   
	 * @param registrosXPagina 	>0
	 * @param pagina			>0
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<VistaSoporteHistoricoDeValores> buscarValores(Long registrosXPagina , Long pagina) throws PersistenciaExcepcion;
	Long cantidadRegistros() throws PersistenciaExcepcion;
}
