package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteHistoricoDeValores;

public interface IReporteValoresPorEmpresaDisponiblesDao {

	/**
	 * Se obtendra todos los valores de los clientes que no pertenecen a Telecom Argentina.-
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<VistaSoporteHistoricoDeValores> buscarValores() throws PersistenciaExcepcion;
}
