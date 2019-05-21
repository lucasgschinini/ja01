package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaCobro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ReglaCobroFiltro;

public interface IReglaCobroServicio extends IServicio{
	
	public List<ShvParamReglaCobro> buscar(ReglaCobroFiltro reglaCobroFiltro)
			throws PersistenciaExcepcion, NegocioExcepcion;
	
}
