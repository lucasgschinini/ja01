package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteAplicacionManualPendientes;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteAplicacionManualPendientesFiltro;

public interface IReporteAplicacionManualPendienteDao {

	/**
	 * Se obtendra todos los cobros de aplicación manual en estado pendiente con filtro de fecha y empresa.
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<VistaSoporteAplicacionManualPendientes> buscarCobros(VistaSoporteReporteAplicacionManualPendientesFiltro filtro) throws PersistenciaExcepcion;
}
