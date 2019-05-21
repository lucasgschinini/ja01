package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobanteUso;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;

public interface ITipoComprobanteDao {
	
	List<ShvParamTipoComprobante> listarTipoComprobante() throws PersistenciaExcepcion;

	List<ShvParamTipoLetraComprobante> listarTipoLetraComprobante() throws PersistenciaExcepcion;

	List<ShvParamTipoRetencionImpuesto> listarTipoImpuesto() throws PersistenciaExcepcion;
	
	ShvParamTipoComprobante listarTipoComprobanteClase(String idTipoComprobante) throws PersistenciaExcepcion;
	
	ShvParamTipoLetraComprobante listarTipoLetraComprobanteClase(String letraComprobante) throws PersistenciaExcepcion;

	ShvParamTipoComprobanteUso listarTipoComprobanteUso(SociedadEnum sociedad, SistemaEnum sistema, TipoComprobanteEnum tipoComprobante) throws PersistenciaExcepcion;

	ShvParamTipoComprobante buscarComprobante(TipoComprobanteEnum tipoComprobante) throws PersistenceException;
	
}
