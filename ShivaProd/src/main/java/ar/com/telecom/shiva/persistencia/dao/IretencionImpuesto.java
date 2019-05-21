package ar.com.telecom.shiva.persistencia.dao;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;

public interface IretencionImpuesto {
	
	
	ShvParamTipoRetencionImpuesto buscarRetencionImpuesto(String idRetencionImpuesto) throws PersistenciaExcepcion;

}
