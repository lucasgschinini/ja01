package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTruncaFiltro;

public interface IOperacionTruncaDao {

	List<ShvCobCobro> buscarOperacionesTruncas(
			OperacionTruncaFiltro opeTruncaFiltro) throws PersistenciaExcepcion;

}
