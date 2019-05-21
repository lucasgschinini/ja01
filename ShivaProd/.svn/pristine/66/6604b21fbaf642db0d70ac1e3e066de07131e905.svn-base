package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamRespWfTarea;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;

public interface IParamRespWfTareaDao {

	List<ShvParamRespWfTarea> consultarPerfiles(PerfilFiltro filtro) throws PersistenciaExcepcion;

	TipoPerfilEnum responsableImputacion(PerfilFiltro filtro) throws PersistenciaExcepcion;
}
