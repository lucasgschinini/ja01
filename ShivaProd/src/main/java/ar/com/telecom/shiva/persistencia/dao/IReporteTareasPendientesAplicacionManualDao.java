package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteTareasPendientesAplicacionManual;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteTareasPendientesAplicacionManualFiltro;
/**
 * 
 * @author u610512 MDB
 *
 */
public interface IReporteTareasPendientesAplicacionManualDao {
	/**
	 * 
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public List<VistaSoporteTareasPendientesAplicacionManual> buscarTareasPendientesAplicacionManual(VistaSoporteReporteTareasPendientesAplicacionManualFiltro filtro) throws PersistenciaExcepcion;


}
