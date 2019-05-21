package ar.com.telecom.shiva.persistencia.dao;

import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.ConfCampoTipoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaCampoValidacionBean;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;

public interface IParametroConfReglaCampoDao {
	
	public List<Map<String, Object>> obtnerConfiguracionRegla(MonedaEnum monedaOperacion, ConfCampoTipoEnum tipoConfiguracion) throws PersistenciaExcepcion ;
	
	List<ShvParamConfCampo> listarCamposImportacion() throws PersistenciaExcepcion;

	List<ShvParamConfCampo> listarCamposOrden() throws PersistenciaExcepcion;

	//List<Map<String, Object>> obtenerCamposReglaImportacionActivosOrdenados(MonedaEnum monedaCobro) throws PersistenciaExcepcion;

	//List<Map<String, Object>> obtenerSociedades(MonedaEnum monedaCobro) throws PersistenciaExcepcion;

	//List<Map<String, Object>> obtenerSistemas(MonedaEnum monedaCobro) throws PersistenciaExcepcion;

	//List<Map<String, Object>> obtenerTipoComprobantes(MonedaEnum monedaCobro) throws PersistenciaExcepcion;
	
	//public List<Map<String, Object>> obtenerMonedas(MonedaEnum monedaCobro) throws PersistenciaExcepcion;

	List<ConfReglaBean> obtenerCamposReglaImportacionActivosOrdenadosMapeados(MonedaEnum monedaCobro, List<TipoComprobanteEnum> listaComprobanteDuplicado) throws NegocioExcepcion;

	List<Object> listaSociedadesActivos(MonedaEnum monedaCobro) throws NegocioExcepcion;

	List<Object> listaSistemasActivos(MonedaEnum monedaCobro) throws NegocioExcepcion;

	List<Object> listaTipoComprobanteActivos(MonedaEnum monedaCobro) throws NegocioExcepcion;

	public List<Object> listaMonedaActivos(MonedaEnum monedaCobro) throws NegocioExcepcion;

	//List<Map<String, Object>> obtenerTipoDatoTipoCampoValidacion() throws PersistenciaExcepcion;

	List<ConfReglaCampoValidacionBean> obtenerTipoDatoTipoCampoValidacionMapeado() throws NegocioExcepcion;
	
	List<ShvParamConfCampo> obtenerCamposDeImportacion(String sociedad, String sistema, String tipoComprobante, String moneda, MonedaEnum monedaCobro, String idMotivoCobro) throws NegocioExcepcion, PersistenciaExcepcion;
	
	boolean tieneMasDeUnTipoDeMoneda(String sociedad, String sistema, String tipoComprobante, MonedaEnum monedaCobro) throws NegocioExcepcion, PersistenciaExcepcion;
	
	Map<String,String> obtenerTipoComprobantePorSociedadySistema(String sociedad, String sistema, String moneda, MonedaEnum monedaCobro) throws PersistenciaExcepcion;

}
