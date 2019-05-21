package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.ConfCampoTipoEnum;
import ar.com.telecom.shiva.base.enumeradores.ConfFormObligatoriedadEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConfReglaCampoValidacionBean;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParametroConfReglaCampoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;
import ar.com.telecom.shiva.persistencia.repository.ParametroConfReglaCampoRepositorio;

public class ParametroConfReglaCampoDaoImpl extends Dao implements IParametroConfReglaCampoDao  {
	
	@Autowired
	ParametroConfReglaCampoRepositorio parametroConfReglaCampoRepositorio;
	
	@Override
	public List<Map<String, Object>> obtnerConfiguracionRegla(MonedaEnum monedaOperacion, ConfCampoTipoEnum tipoConfiguracion) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select regla.MONEDA_OPERACION, regla.SOCIEDAD, regla.MONEDA, campo.TIPO_CAMPO, ");
		str.append("regla.SISTEMA_ORIGEN, regla.TIPO_COMPROBANTE, ");
		str.append("campo.NOMBRE,reglaCampo.LONGITUD, reglaCampo.TIPO_DE_DATO, reglaCampo.validacion, reglaCampo.validacion_MSG, reglaCampo.OBLIGATORIEDAD, reglaCampo.ORDEN ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) inner join SHV_PARAM_CONF_CAMPO campo ");
		str.append("ON(campo.ID_CONF_CAMPO = reglaCampo.ID_CONF_CAMPO) ");
		str.append("where reglaCampo.TIPO_CONFIGURACION =? ");
	
		parametros.addCampoAlQuery(tipoConfiguracion.name(), Types.CHAR);
		if (!Validaciones.isObjectNull(monedaOperacion)) {
			str.append("AND regla.MONEDA_OPERACION = ? ");
			parametros.addCampoAlQuery(monedaOperacion.name(), Types.CHAR);
		}
		str.append("and campo.activo = ? AND reglaCampo.activo = ? order by regla.MONEDA_OPERACION, regla.SOCIEDAD, regla.MONEDA, regla.SISTEMA_ORIGEN, regla.TIPO_COMPROBANTE, reglaCampo.ORDEN ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);

		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorno campos regla importacion activos ordenados
	 */
	private List<Map<String, Object>> obtenerCamposReglaImportacionActivosOrdenados(MonedaEnum monedaCobro, List<TipoComprobanteEnum> listaComprobanteDuplicado) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select regla.MONEDA_OPERACION, regla.SOCIEDAD, regla.MONEDA, campo.TIPO_CAMPO, ");
		str.append("regla.SISTEMA_ORIGEN, regla.TIPO_COMPROBANTE, ");
		str.append("campo.NOMBRE, reglaCampo.LONGITUD, reglaCampo.TIPO_DE_DATO, reglaCampo.VALIDACION, reglaCampo.VALIDACION_MSG, reglaCampo.OBLIGATORIEDAD, reglaCampo.ORDEN, reglaCampo.ID_CONF_REGLA, reglaCampo.ID_CONF_CAMPO ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) inner join SHV_PARAM_CONF_CAMPO campo ");
		str.append("ON(campo.ID_CONF_CAMPO = reglaCampo.ID_CONF_CAMPO) ");
		str.append("where campo.CAMPO_IMPORTACION =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		str.append("and reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("and campo.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
		if(Validaciones.isCollectionNotEmpty(listaComprobanteDuplicado)){
			str.append("and regla.tipo_comprobante in ");
			String valores = "(";
			for (TipoComprobanteEnum tipoComprobante: listaComprobanteDuplicado) {
				valores += "?, ";
				parametros.addCampoAlQuery(tipoComprobante.name(), Types.VARCHAR);
			}
			if (valores.length() > 3) {
				valores = valores.substring(0,
						valores.length() - 2) + ") ";
			}
			str.append(valores);
		}
		str.append(" order by campo.ORDEN_CAMPOS, reglaCampo.ID_CONF_REGLA, reglaCampo.ID_CONF_CAMPO ");
		

		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista de Sociedades
	 */
	private List<Map<String, Object>> obtenerSociedades(MonedaEnum monedaCobro) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select DISTINCT regla.SOCIEDAD ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) ");
		str.append("where reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);

		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	
	private List<Map<String, Object>> obtenerMonedas(MonedaEnum monedaCobro) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select DISTINCT regla.MONEDA ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) ");
		str.append("where reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");

		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
		
		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista de Sistemas
	 */

	private List<Map<String, Object>> obtenerSistemas(MonedaEnum monedaCobro) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select DISTINCT regla.SISTEMA_ORIGEN ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) ");
		str.append("where reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");

		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
	
		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista Tipo Comprobantes
	 */
	private List<Map<String, Object>> obtenerTipoComprobantes(MonedaEnum monedaCobro) throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select DISTINCT regla.TIPO_COMPROBANTE ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) ");
		str.append("where reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");

		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
	
		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista de campos con Tipo Dato, Tipo Campo y Validacion
	 */
	private List<Map<String, Object>> obtenerTipoDatoTipoCampoValidacion() throws PersistenciaExcepcion {
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select campo.nombre, campo.tipo_campo, orden_campos, regla_campo.tipo_de_Dato, regla_campo.validacion, regla_campo.longitud ");
		str.append("FROM shv_param_conf_campo campo left join shv_param_conf_regla_campo regla_campo ");
		str.append("on (campo.id_conf_campo = regla_campo.id_conf_campo) ");
		str.append("where campo_importacion = 'SI' ");
		str.append("group by campo.nombre, campo.Tipo_campo, orden_campos, regla_campo.tipo_de_Dato, regla_campo.validacion, regla_campo.longitud ");
		str.append("order by orden_campos ");
	
		parametros.setSql(str.toString());
		return buscarUsandoSQL(parametros);
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista de campos para la importacion
	 */
	@Override
	public List<ShvParamConfCampo> listarCamposImportacion() throws PersistenciaExcepcion {
		try {
			List<ShvParamConfCampo> list = parametroConfReglaCampoRepositorio.listarCamposImportacion(SiNoEnum.SI, SiNoEnum.SI);
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Retorna la lista de campos ordenados
	 */
	@Override
	public List<ShvParamConfCampo> listarCamposOrden() throws PersistenciaExcepcion {
		try {
			List<ShvParamConfCampo> list = parametroConfReglaCampoRepositorio.listarCamposOrden();
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Método de mapeo de datos de importación.
	 */
	@Override
	public List<ConfReglaBean> obtenerCamposReglaImportacionActivosOrdenadosMapeados(MonedaEnum monedaCobro, List<TipoComprobanteEnum> listaComprobanteDuplicado) throws NegocioExcepcion {
		List<Map<String, Object>> camposReglaImportacion;
		List<ConfReglaBean> listaResultado = new ArrayList<ConfReglaBean>();

		try {
			camposReglaImportacion = this.obtenerCamposReglaImportacionActivosOrdenados(monedaCobro, listaComprobanteDuplicado);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		for (Map<String, Object> registro : camposReglaImportacion) {
			ConfReglaBean confRegla = new ConfReglaBean();
			if (!Validaciones.isObjectNull(registro.get("MONEDA_OPERACION"))) {
				confRegla.setMoneda(MonedaEnum.getEnumByName((String)registro.get("MONEDA_OPERACION")));
			}
			if (!Validaciones.isObjectNull(registro.get("SOCIEDAD"))) {
				confRegla.setSociedad(SociedadEnum.getEnumByName((String)registro.get("SOCIEDAD")));
			}
			if (!Validaciones.isObjectNull(registro.get("SISTEMA_ORIGEN"))) {
				confRegla.setSistema(SistemaEnum.getEnumByName((String)registro.get("SISTEMA_ORIGEN")));
			}
			if (!Validaciones.isObjectNull(registro.get("TIPO_COMPROBANTE"))) {
				confRegla.setTipoComprobante(TipoComprobanteEnum.getEnumByName((String)registro.get("TIPO_COMPROBANTE")));
			}
			if (!Validaciones.isObjectNull(registro.get("MONEDA"))) {
				confRegla.setMoneda(MonedaEnum.getEnumByName((String)registro.get("MONEDA")));
			}
			if (!Validaciones.isObjectNull(registro.get("NOMBRE"))) {
				confRegla.setNombre((String)registro.get("NOMBRE"));
			}
			if (!Validaciones.isObjectNull(registro.get("LONGITUD"))) {
				confRegla.setLongitud((String)registro.get("LONGITUD"));
			}
			if (!Validaciones.isObjectNull(registro.get("TIPO_DE_DATO"))) {
				confRegla.setTipoDeDato((String)registro.get("TIPO_DE_DATO"));
			}
			if (!Validaciones.isObjectNull(registro.get("VALIDACION"))) {
				confRegla.setValidacion((String)registro.get("VALIDACION"));
			}
			if (!Validaciones.isObjectNull(registro.get("VALIDACION_MSG"))) {
				confRegla.setValidacionMsg((String)registro.get("VALIDACION_MSG"));
			}
			if (!Validaciones.isObjectNull(registro.get("OBLIGATORIEDAD"))) {
				confRegla.setObligatoriedad(ConfFormObligatoriedadEnum.getEnumByName((String)registro.get("OBLIGATORIEDAD")));
			}
			if (!Validaciones.isObjectNull(registro.get("ORDEN"))) {
				confRegla.setOrden((BigDecimal) registro.get("ORDEN"));
			}
			if (!Validaciones.isObjectNull(registro.get("ID_CONF_REGLA"))) {
				confRegla.setIdConfRango((BigDecimal) registro.get("ID_CONF_REGLA"));
			}
			if (!Validaciones.isObjectNull(registro.get("ID_CONF_CAMPO"))) {
				confRegla.setIdConfCampo((BigDecimal) registro.get("ID_CONF_CAMPO"));
			}

			listaResultado.add(confRegla);

		}

		return listaResultado;
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Me traigo todas las Sociedades activas de la parametrica.
	 */
	@Override
	public List<Object> listaSociedadesActivos(MonedaEnum monedaCobro) throws NegocioExcepcion {
		List<Object> listaSociedades = new ArrayList<Object>();
		List<Map<String, Object>> sociedades;
		try {
			sociedades = this.obtenerSociedades(monedaCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		SociedadEnum sociedad = null;
		for (Map<String, Object> registro : sociedades) {
			if (!Validaciones.isObjectNull(registro.get("SOCIEDAD"))) {
				sociedad = SociedadEnum.getEnumByName((String)registro.get("SOCIEDAD"));
				listaSociedades.add(sociedad);
			}
		}
		return listaSociedades;
	}
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Me traigo todas las Sociedades activas de la parametrica.
	 */
	@Override
	public List<Object> listaMonedaActivos(MonedaEnum monedaCobro) throws NegocioExcepcion {
		List<Object> listaMonedas = new ArrayList<Object>();
		List<Map<String, Object>> monedas;
		try {
			monedas = this.obtenerMonedas(monedaCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		MonedaEnum moneda = null;
		for (Map<String, Object> registro : monedas) {
			if (!Validaciones.isObjectNull(registro.get("MONEDA"))) {
				moneda = MonedaEnum.getEnumByName((String)registro.get("MONEDA"));
				listaMonedas.add(moneda);
			}
		}
		return listaMonedas;
	}
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Me traigo todos los Sistemas activas de la parametrica.
	 */
	@Override
	public List<Object> listaSistemasActivos(MonedaEnum monedaCobro) throws NegocioExcepcion {
		List<Object> listaSistemas = new ArrayList<Object>();
		List<Map<String, Object>> sociedades;
		try {
			sociedades = this.obtenerSistemas(monedaCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		SistemaEnum sistema = null;
		for (Map<String, Object> registro : sociedades) {
			if (!Validaciones.isObjectNull(registro.get("SISTEMA_ORIGEN"))) {
				sistema = SistemaEnum.getEnumByName((String)registro.get("SISTEMA_ORIGEN"));
				listaSistemas.add(sistema);
			}
		}
		return listaSistemas;
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Me traigo todos los Tipo Comprobantes activas de la parametrica.
	 */
	@Override
	public List<Object> listaTipoComprobanteActivos(MonedaEnum monedaCobro) throws NegocioExcepcion {
		List<Object> listaTipoComprobante = new ArrayList<Object>();
		List<Map<String, Object>> tipoComprobantes;
		try {
			tipoComprobantes = this.obtenerTipoComprobantes(monedaCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		TipoComprobanteEnum tipoComprobante = null;
		for (Map<String, Object> registro : tipoComprobantes) {
			if (!Validaciones.isObjectNull(registro.get("TIPO_COMPROBANTE"))) {
				tipoComprobante = TipoComprobanteEnum.getEnumByName((String)registro.get("TIPO_COMPROBANTE"));
				listaTipoComprobante.add(tipoComprobante);
			}
		}
		return listaTipoComprobante;
	}
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Método que mapea la lista de campos con Tipo Dato, Tipo Campo y Validacion.
	 */
	@Override
	public List<ConfReglaCampoValidacionBean> obtenerTipoDatoTipoCampoValidacionMapeado() throws NegocioExcepcion {
		List<Map<String, Object>> camposReglaImportacion;
		List<ConfReglaCampoValidacionBean> listaResultado = new ArrayList<ConfReglaCampoValidacionBean>();
		ConfReglaCampoValidacionBean confRegla = new ConfReglaCampoValidacionBean();
		try {
			camposReglaImportacion = this.obtenerTipoDatoTipoCampoValidacion();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		/**
		 * str.append("Select campo.nombre, campo.tipo_campo, orden_campos, regla_campo.tipo_de_Dato, regla_campo.validacion ");
		 */
		for (Map<String, Object> registro : camposReglaImportacion) {

			if (!Validaciones.isObjectNull(registro.get("NOMBRE"))) {
				confRegla.setNombre((String)registro.get("NOMBRE"));
			}
			if (!Validaciones.isObjectNull(registro.get("TIPO_CAMPO"))) {
				confRegla.setTipoCampo((String)registro.get("TIPO_CAMPO"));
			}
			if (!Validaciones.isObjectNull(registro.get("TIPO_DE_DATO"))) {
				confRegla.setTipoDeDato((String)registro.get("TIPO_DE_DATO"));
			}
			if (!Validaciones.isObjectNull(registro.get("VALIDACION"))) {
				confRegla.setValidacion((String)registro.get("VALIDACION"));
			}
			if (!Validaciones.isObjectNull(registro.get("ORDEN"))) {
				confRegla.setOrden((BigDecimal) registro.get("ORDEN"));
			}
			if (!Validaciones.isObjectNull(registro.get("LONGITUD"))) {
				confRegla.setLongitud(Long.getLong((String)registro.get("LONGITUD")));
			}
			listaResultado.add(confRegla);

		}

		return listaResultado;
	}
	
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Método que mapea la lista de campos con Tipo Dato, Tipo Campo y Validacion.
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public List<ShvParamConfCampo> obtenerCamposDeImportacion(String sociedad, String sistema, String tipoComprobante, String moneda, MonedaEnum monedaCobro, String idMotivoCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select campo.ID_CONF_CAMPO, campo.NOMBRE, campo.TIPO_CAMPO, campo.CAMPO_IMPORTACION, campo.ACTIVO, campo.ORDEN_CAMPOS, regla.TIPO_COMPROBANTE ");
		str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
		str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) inner join SHV_PARAM_CONF_CAMPO campo ");
		str.append("ON(campo.ID_CONF_CAMPO = reglaCampo.ID_CONF_CAMPO) ");
		str.append("where campo.CAMPO_IMPORTACION =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		
		str.append("and reglaCampo.ACTIVO =? ");
		str.append("and regla.ACTIVO =? ");
		str.append("and campo.ACTIVO =? ");
		str.append("AND regla.moneda_operacion =? ");
		str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
		
		str.append("and regla.sociedad=? ");
		str.append("and regla.sistema_origen=? ");
		parametros.addCampoAlQuery(sociedad, Types.CHAR);
		parametros.addCampoAlQuery(sistema, Types.CHAR);
		
		if (!Validaciones.isNullOrEmpty(moneda)){
			str.append("and regla.moneda=? ");
			parametros.addCampoAlQuery(moneda, Types.CHAR);
		} else if (tieneMasDeUnTipoDeMoneda(sociedad, sistema, tipoComprobante, monedaCobro)){
			str.append("group by campo.ID_CONF_CAMPO, campo.NOMBRE, campo.TIPO_CAMPO, campo.CAMPO_IMPORTACION, campo.ACTIVO, campo.ORDEN_CAMPOS ");
			str.append("HAVING COUNT(*) > 1 ");
		}
	
		str.append(" order by campo.ID_CONF_CAMPO, campo.NOMBRE, campo.TIPO_CAMPO, campo.CAMPO_IMPORTACION, campo.ACTIVO, campo.ORDEN_CAMPOS, regla.TIPO_COMPROBANTE");
		
		parametros.setSql(str.toString());
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);
		List<ShvParamConfCampo> listaResultado = new ArrayList<ShvParamConfCampo>();
		
		if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
			
			for (Map<String, Object> registro : listaResultadoQuery) {
				
				if (!Validaciones.isObjectNull(registro.get("TIPO_COMPROBANTE"))){
					
					if (registro.get("TIPO_COMPROBANTE").toString().contains(tipoComprobante.replaceAll("_", ""))){
						
						ShvParamConfCampo resultado = new ShvParamConfCampo();
						
						if (!Validaciones.isObjectNull(registro.get("ID_CONF_CAMPO"))){
							resultado.setIdConfCampo(Long.parseLong(registro.get("ID_CONF_CAMPO").toString()));
						}
						
						if (!Validaciones.isObjectNull(registro.get("NOMBRE"))){
							resultado.setNombre(registro.get("NOMBRE").toString());
						}
						
						if (!Validaciones.isObjectNull(registro.get("TIPO_CAMPO"))){
							resultado.setTipoDeDato(registro.get("TIPO_CAMPO").toString());
						}
						
						if (!Validaciones.isObjectNull(registro.get("CAMPO_IMPORTACION"))){
							resultado.setCampoImportacion(SiNoEnum.getEnumByDescripcion(registro.get("CAMPO_IMPORTACION").toString()));
						}
						
						if (!Validaciones.isObjectNull(registro.get("ACTIVO"))){
							resultado.setActivo(SiNoEnum.getEnumByDescripcion(registro.get("ACTIVO").toString()));
						}
						
						if (!Validaciones.isObjectNull(registro.get("ORDEN_CAMPOS"))){
							resultado.setOrdenCampos(Long.parseLong(registro.get("ORDEN_CAMPOS").toString()));
						}
						
						if (Constantes.ID_MOTIVO_COMPENSACION.equals(idMotivoCobro)||
							!Constantes.ID_MOTIVO_COMPENSACION.equals(idMotivoCobro)&&!Constantes.sinDiferenciaDeCambio.equals(resultado.getNombre())){
							listaResultado.add(resultado);
						}
						
					}
				}
				
			}
			
		}

		return listaResultado;
	}	
	
	
	/**
	 * @author U587496 Guido.Settecerze
	 * 
	 * Método que mapea la lista de campos con Tipo Dato, Tipo Campo y Validacion.
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public boolean tieneMasDeUnTipoDeMoneda(String sociedad, String sistema, String tipoComprobante, MonedaEnum monedaCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		
		Integer resultado = 0;
		StringBuffer str = new StringBuffer();
		QueryParametrosUtil parametros = new QueryParametrosUtil();

		str.append("Select count(*) monedas ");
		str.append("FROM SHV_PARAM_CONF_REGLA ");
		str.append("where ACTIVO =? ");
		str.append("AND moneda_operacion =? ");
		parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
		parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
		
		str.append("and sociedad=? ");
		str.append("and sistema_origen=? ");
		str.append("and tipo_comprobante=? ");
		parametros.addCampoAlQuery(sociedad, Types.CHAR);
		parametros.addCampoAlQuery(sistema, Types.CHAR);
		parametros.addCampoAlQuery(tipoComprobante, Types.CHAR);

		parametros.setSql(str.toString());
		
		List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);
		
		for (Map<String, Object> archivo : listaResultadoQuery) {

			if (!Validaciones.isObjectNull(archivo.get("monedas"))) {
				resultado = Integer.valueOf(archivo.get("monedas").toString());
			}
		}
		
		return (resultado > 1);
		
	}
	
	public Map<String,String> obtenerTipoComprobantePorSociedadySistema(String sociedad, String sistema, String moneda, MonedaEnum monedaCobro) throws PersistenciaExcepcion {
		try {
			
			StringBuffer str = new StringBuffer();
			QueryParametrosUtil parametros = new QueryParametrosUtil();
			
			str.append("select tipo_Comprobante ");
			str.append("FROM SHV_PARAM_CONF_REGLA_CAMPO reglaCampo inner join SHV_PARAM_CONF_REGLA regla ");
			str.append("ON(regla.ID_CONF_REGLA = reglaCampo.ID_CONF_REGLA) inner join SHV_PARAM_CONF_CAMPO campo ");
			str.append("ON(campo.ID_CONF_CAMPO = reglaCampo.ID_CONF_CAMPO) ");
			str.append("where campo.CAMPO_IMPORTACION =? ");
			parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
			
			str.append("and reglaCampo.ACTIVO =? ");
			str.append("and regla.ACTIVO =? ");
			str.append("and campo.ACTIVO =? ");
			str.append("AND regla.moneda_operacion =? ");
			str.append("AND reglaCampo.TIPO_CONFIGURACION =? ");
			parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
			parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
			parametros.addCampoAlQuery(SiNoEnum.SI.name(), Types.CHAR);
			parametros.addCampoAlQuery(monedaCobro.name(), Types.CHAR);
			parametros.addCampoAlQuery(ConfCampoTipoEnum.CONF_OTROS_DEBITOS.name(), Types.CHAR);
			
			str.append("and regla.sociedad=? ");
			str.append("and regla.sistema_origen=? ");
			parametros.addCampoAlQuery(sociedad, Types.CHAR);
			parametros.addCampoAlQuery(sistema, Types.CHAR);
			
			parametros.setSql(str.toString());
			
			List<Map<String, Object>> listaResultadoQuery = buscarUsandoSQL(parametros);
			Map<String,String> listaResultado = new HashMap<String,String>();
			
			if(Validaciones.isCollectionNotEmpty(listaResultadoQuery)){
				
				String idTipoComprobante="";
				for (Map<String, Object> registro : listaResultadoQuery) {
					
					String resultado = "";
					if (!Validaciones.isObjectNull(registro.get("TIPO_COMPROBANTE"))){
						resultado = registro.get("TIPO_COMPROBANTE").toString();
						if (TipoComprobanteEnum.C_C.equals(resultado)){
							idTipoComprobante=resultado.replace("_", "/");
						} 
						
						idTipoComprobante=resultado.replaceAll("[\\d]", "").replace("_", "");
						
					}
					
					listaResultado.put(idTipoComprobante, resultado);
					
				}
				
			}
			
			return listaResultado;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
