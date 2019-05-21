package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.TipoValorBean;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public interface ITipoValorDao {

	ShvParamTipoValor buscarTipoValor(String idTipoValor) throws PersistenciaExcepcion;

	List<ShvParamTipoValor> buscarTipoValorBoletaCV(ValorDto boletaDto) throws PersistenciaExcepcion;

	List<ShvParamTipoValor> buscarTipoValorAvisoPag(ValorDto boletaDto) throws PersistenciaExcepcion;

	public List<TipoValorBean> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws PersistenciaExcepcion;
}
