package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoValoresClientesPuros;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro;

public interface IImputacionValoresClientesPurosDao {
	/**
	 * Se obtendra todos los valores de los clientes
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<VistaSoporteResultadoValoresClientesPuros> buscarValores(VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro filtro) throws PersistenciaExcepcion;
}
