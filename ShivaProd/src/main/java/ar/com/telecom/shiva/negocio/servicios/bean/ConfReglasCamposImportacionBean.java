package ar.com.telecom.shiva.negocio.servicios.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.ConfFormObligatoriedadEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ImportacionDebitosAuxiliar;
import ar.com.telecom.shiva.negocio.confCampoRegla.emun.ParamConfCampoOtrosDebitosEnum;
import ar.com.telecom.shiva.persistencia.dao.IParametroConfReglaCampoDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ConfReglasCamposImportacionBean implements Serializable {
		
	private static final long serialVersionUID = 20180628L;

	private List<ShvParamConfCampo> listarCamposImportacion;
	private List<ShvParamConfCampo> camposDeImportacion;
	private Map<String, ConfReglasBean> configuracionRta = new HashMap<String, ConfReglasBean>();
	private Map<String, List<Object>> dominiosRta = new HashMap<String, List<Object>>();
	private Map<Integer, String> listaErrores;
	private List<ConfReglaBean> configuracion;
	private MonedaEnum monedaCobro;
	private ImportacionDebitosAuxiliar importarDebitosAuxiliar;
	private String campos[];
	private int nroRegistro = -1;
	private Map<String, List<Object>> restriccionesMap = new HashMap<String, List<Object>>();

	public ConfReglasCamposImportacionBean() {
	
	}

	public void limpiarParametro() {
		for (ShvParamConfCampo shvParamConfCampoFixed : listarCamposImportacion) {
			shvParamConfCampoFixed.getListaErrores().clear();
		}
	}

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarFormato() throws NegocioExcepcion {
		if (
			!Validaciones.isObjectNull(campos) &&
			campos.length == listarCamposImportacion.size()
		) {
			return false;
		} else {
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importarDebitosAuxiliar.getResultadoValidaciones().append("Deben ser " + listarCamposImportacion.size() + 
					" campos y el registro posee " + campos.length + ".");
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			Traza.error(getClass(), "Cantidad de campos erronea en el registro nro: " + nroRegistro + ", " + 
					"Deben ser " + listarCamposImportacion.size() + 
					" campos y el registro posee " + campos.length + ".");
			return true;
		}
	}
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 */
	public boolean validarRegistro(IParametroConfReglaCampoDao parametroConfReglaCampoDaoImpl, MonedaEnum monedaCobro, String idMotivoCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		
		Map<String, ConfReglasBean> configuracionRta = new HashMap<String, ConfReglasBean>();
		listaErrores = new HashMap<Integer, String>();
		camposDeImportacion = new ArrayList<ShvParamConfCampo>();
		String sociedad="";
		String sistema="";
		String tipoComprobante="";
		String moneda="";
		
		boolean salida = false;
		
		// Se valida que los campos pertenecientes a la clave del alta de Otros Debitos (Sociedad, Sistema, Tipo de Debito y Moneda) esten informados y con los datos correspondientes. 
		for (ShvParamConfCampo shvParamConfCampoFixed : listarCamposImportacion) {
			if ("SELECT".equalsIgnoreCase(shvParamConfCampoFixed.getTipoDeDato())) {
				configuracionRta.put(shvParamConfCampoFixed.getNombre(), new ConfReglasBean());
				Traza.auditoria(getClass(), "Se va procesar el campo [" + shvParamConfCampoFixed.getNombre()+ "] : valor :" + campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]);
				
				if (!validarObligatoriedad(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1], shvParamConfCampoFixed, this.nroRegistro)) {
					configuracionRta.get(shvParamConfCampoFixed.getNombre()).setValidado(SiNoEnum.NO);
				}
				if (!this.validarDominio(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1], shvParamConfCampoFixed, this.nroRegistro, dominiosRta, configuracionRta, configuracion)) {
					configuracionRta.get(shvParamConfCampoFixed.getNombre()).setValidado(SiNoEnum.NO);
				}
				
				if (SiNoEnum.SI.equals(configuracionRta.get(shvParamConfCampoFixed.getNombre()).getValidado())) {
					configuracionRta.get(shvParamConfCampoFixed.getNombre()).setValor(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]);
					this.validacionRelacionadaFrisando(campos, listarCamposImportacion, configuracionRta.get(shvParamConfCampoFixed.getNombre()).getReglas(), shvParamConfCampoFixed);
					
					if (ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion().equals(shvParamConfCampoFixed.getNombre())) {
						sociedad = SociedadEnum.getEnumByApocope(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).name();
					} else if (ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion().equals(shvParamConfCampoFixed.getNombre())) {
						sistema = SistemaEnum.getEnumByDescripcionCorta(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).name();
					} else if (ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion().equals(shvParamConfCampoFixed.getNombre())) {
						tipoComprobante = TipoComprobanteEnum.getEnumByValor(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).name();
					} else if (ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion().equals(shvParamConfCampoFixed.getNombre())) {
						moneda = MonedaEnum.getEnumBySigno2(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).name();
					}
				}
			}
		}
		
		if (!Validaciones.isNullOrEmpty(sociedad)&&!Validaciones.isNullOrEmpty(sistema)&&!Validaciones.isNullOrEmpty(tipoComprobante)){
			
			camposDeImportacion=parametroConfReglaCampoDaoImpl.obtenerCamposDeImportacion(sociedad, sistema, tipoComprobante, moneda, monedaCobro, idMotivoCobro);
		}
		
		if (Validaciones.isCollectionNotEmpty(camposDeImportacion)){
			// Valido los campos restantes No Select!!!
			for (ShvParamConfCampo shvParamConfCampoFixed : listarCamposImportacion) {
				
				if (!"SELECT".equalsIgnoreCase(shvParamConfCampoFixed.getTipoDeDato())){
					if (!existeCampo(shvParamConfCampoFixed,camposDeImportacion) && !Validaciones.isNullEmptyOrDash(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])){
						agregarError(ErrorBean.PRIORIDAD_NO_VACIO,mensajeErrorCampoInformado(shvParamConfCampoFixed,campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).toString());
					}
				} else if (ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion().equals(shvParamConfCampoFixed.getNombre()) &&
						!Validaciones.isNullOrEmpty(sociedad)&&!Validaciones.isNullOrEmpty(sistema)&&!Validaciones.isNullOrEmpty(tipoComprobante)&&!Validaciones.isNullOrEmpty(moneda)) {
					Map<String,String> listaTipoComprobante = parametroConfReglaCampoDaoImpl.obtenerTipoComprobantePorSociedadySistema(sociedad, sistema, moneda, monedaCobro);
					if (listaTipoComprobante.containsKey(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])){
						campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1] = listaTipoComprobante.get(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).replace("_", "");
					}
					
					
				}
				
			}
			
			List<ConfReglaBean> confPorCampo = optenerConfiguraciones(configuracionRta);
			
			for (ShvParamConfCampo shvParamConfCampoFixed : camposDeImportacion) {
				
				if (!"SELECT".equalsIgnoreCase(shvParamConfCampoFixed.getTipoDeDato())) {
					ConfReglaBean confReglaBean = optenerConfiguracion(shvParamConfCampoFixed.getNombre(), confPorCampo);
					StringBuffer str = new StringBuffer();

					if (Validaciones.isObjectNull(confReglaBean)) {
						if (!Validaciones.isNullEmptyOrDash(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])) {
							str.append(this.mensajeErrorVacio(shvParamConfCampoFixed,campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]));
						}
					} else {
						boolean errorLongitud = false;

						if (ConfFormObligatoriedadEnum.OBLIGATORIO.equals(confReglaBean.getObligatoriedad()) &&
							Validaciones.isNullEmptyOrDash(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])) {
							agregarError(ErrorBean.PRIORIDAD_OBLIGATORIEDAD, mensajeErrorObligatorio(shvParamConfCampoFixed).toString());
						}
						if (confReglaBean.getLongitudLong() < campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1].length()) {
							agregarError(ErrorBean.PRIORIDAD_ERROR_LONGITUD,
								this.mensajeErrorLongitud(
										shvParamConfCampoFixed,
										campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
										confReglaBean.getLongitud()
									).toString()
							);
							errorLongitud = true;
						}
						if ("NUMERO_LEGAL".equals(confReglaBean.getTipoDeDato()) ||
							"EX-REG".equals(confReglaBean.getTipoDeDato())
						) {
							if (!campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1].matches(confReglaBean.getValidacion())) {
								agregarError(
									ErrorBean.PRIORIDAD_ERROR_FORMATO,
									this.mensajeErrorValor(
											shvParamConfCampoFixed,
											campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
											confReglaBean.getValidacionMsg()
									).toString()
								);
							}
						} else if ("DATE".equals(confReglaBean.getTipoDeDato())) {
							if (
								!errorLongitud &&
								confReglaBean.getLongitudLong() != campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1].length()
							) {
								agregarError(
									ErrorBean.PRIORIDAD_ERROR_LONGITUD,
									this.mensajeErrorLongitud(
										shvParamConfCampoFixed,
										campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
										confReglaBean.getLongitud()
									).toString()
								);
							}
							if (!Validaciones.validarFechaConParse(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])) {
								agregarError(
									ErrorBean.PRIORIDAD_ERROR_FORMATO,
									this.mensajeErrorValor(
											shvParamConfCampoFixed,
											campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
											"dd/mm/yyyy"
									).toString()
								);
							} else if (!campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1].matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) {
								agregarError(
									ErrorBean.PRIORIDAD_ERROR_FORMATO,
									this.mensajeErrorValor(
											shvParamConfCampoFixed,
											campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
											"dd/mm/yyyy"
									).toString()
								);
							}
						} else if ("NUMERICO".equals(confReglaBean.getTipoDeDato())) {
							if (!Validaciones.isNumeric(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])) {
								agregarError(
									ErrorBean.PRIORIDAD_ERROR_FORMATO,
									this.mensajeErrorValor(
											shvParamConfCampoFixed,
											campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
											confReglaBean.getValidacionMsg()
									).toString()
								);
							}
						} else if ("CURRENCY".equals(confReglaBean.getTipoDeDato())) {
							if (!Validaciones.isImporteFormatoValor(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1])) {
								agregarError(
										ErrorBean.PRIORIDAD_ERROR_FORMATO,
										this.mensajeErrorValor(
												shvParamConfCampoFixed,
												campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1],
												"999.999.999,99"
										).toString()
									);
							}
						}
					}
				}
			}
		}
		
		List<Integer> keys = new ArrayList<Integer>(listaErrores.keySet());
		
		for (Integer key : keys) {
			
			importarDebitosAuxiliar.getResultadoValidaciones().append(listaErrores.get(key));
			salida=true;
		}
		return salida;
	}
	
	private boolean existeCampo(ShvParamConfCampo shvParamConfCampoFixed, List<ShvParamConfCampo> camposDeImportacion) {
		boolean existeCampo=false;
		for (ShvParamConfCampo shvParamConfCampo : camposDeImportacion) {
			if (shvParamConfCampo.getNombre().equals(shvParamConfCampoFixed.getNombre())){
				existeCampo=true;
				break;
			}
		}
		return existeCampo;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean validarDuplicidaEnArchivo() {
		Integer numeroRegistro = null;
		Map<String, String> mapComponetesIdPantalla = new HashMap<String, String>();
		boolean salida = false;

		for (ShvParamConfCampo confCampo : listarCamposImportacion) {
			mapComponetesIdPantalla.put(confCampo.getNombre(), campos[confCampo.getOrdenCampos().intValue() -1]);
		}		
				
		numeroRegistro = importarDebitosAuxiliar.getListaIdPantalla().get(generarIdPantalla(mapComponetesIdPantalla, false));
		if (Validaciones.isObjectNull(numeroRegistro)) {
			importarDebitosAuxiliar.getListaIdPantalla().put(generarIdPantalla(mapComponetesIdPantalla, false), this.nroRegistro);
		} else {
			importarDebitosAuxiliar.getResultadoValidaciones().append("Linea número: ").append(this.nroRegistro).append(". Causa: Registro Duplicado en ");
			importarDebitosAuxiliar.getResultadoValidaciones().append(" Linea número: ").append(numeroRegistro).append(Constantes.RETORNO_UNIX);
			salida = true;
		}
		return salida;
	}
	private String generarIdPantalla(Map<String, String> mapComponeteIdPantal) {
		return generarIdPantalla(mapComponeteIdPantal, true);
	}
	private String generarIdPantalla(Map<String, String> mapComponeteIdPantal, boolean real) {
		StringBuffer idReferencia = new StringBuffer();
		if (real) {
			SociedadEnum sociedad = SociedadEnum.getEnumByApocope(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion()));
			SistemaEnum sistema = SistemaEnum.getEnumByDescripcionCorta(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion()));
			TipoComprobanteEnum tipoDebitoEnum = TipoComprobanteEnum.getEnumByValor(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion()));
	
			idReferencia.append(sociedad.name()).append("_").append(sistema.name()).append("_");
	
			if (TipoComprobanteEnum.C_C.equals(tipoDebitoEnum)) {
				if (!SistemaEnum.SAP.equals(sistema)) {
					idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.REFERENCIAPAGO.getDescripcion()));
				} else {
					idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.CLIENTE.getDescripcion()));
				}
			} else if (TipoComprobanteEnum.CDD.equals(tipoDebitoEnum)) {
				idReferencia.append(tipoDebitoEnum.getDescripcion());
			} else {
				idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.NUMERODOCUMENTO.getDescripcion()));
			}
		} else {
			idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion())).append("_").append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion())).append("_");
			idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion())).append("_");
			if (TipoComprobanteEnum.C_C.getValor().equals(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion()))) {
				if (!SistemaEnum.SAP.getDescripcionCorta().equals(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion()))) {
					idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.REFERENCIAPAGO.getDescripcion()));
				} else {
					idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.CLIENTE.getDescripcion()));
				}
			} else if (TipoComprobanteEnum.CDD.getValor().equals(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion()))) {
				idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion()));
			} else {
				idReferencia.append(mapComponeteIdPantal.get(ParamConfCampoOtrosDebitosEnum.NUMERODOCUMENTO.getDescripcion()));
			}
		}
		
		return idReferencia.toString();
	}
	//Linea número: 1. Causa: Longitud erronea del campo Numero de Documento. Debe ser de longitud 13 (B-0472-
	private String mensajeErrorLongitud(ShvParamConfCampo shvParamConfCampo, String valor, String longitudEsperada) {
		StringBuffer str = new StringBuffer();
		str.append(ConstantesCobro.LINEA_NRO);
		str.append(nroRegistro);
		str.append(ConstantesCobro.CAUSA);
		str.append("Longitud erronea del campo ");
		str.append(shvParamConfCampo.getNombre());
		str.append(Constantes.PUNTO);
		str.append(Constantes.WHITESPACE);
		str.append("Debe ser de longitud: ");
		str.append(longitudEsperada);
		str.append("(").append(valor.length()).append(").");
		str.append(Constantes.CARRIAGE_RETURN);

		return str.toString();
	}
	private String mensajeErrorObligatorio(ShvParamConfCampo shvParamConfCampo) {
		StringBuffer importarDebitosAuxiliar = new StringBuffer();
		importarDebitosAuxiliar.append(ConstantesCobro.LINEA_NRO);
		importarDebitosAuxiliar.append(nroRegistro);
		importarDebitosAuxiliar.append(ConstantesCobro.CAUSA);
		importarDebitosAuxiliar.append(ConstantesCobro.ERROR_EL_CAMPO);
		importarDebitosAuxiliar.append(shvParamConfCampo.getNombre());
		importarDebitosAuxiliar.append(ConstantesCobro.ERROR_OBLIGATORIO);
		importarDebitosAuxiliar.append(Constantes.PUNTO);
		importarDebitosAuxiliar.append(Constantes.CARRIAGE_RETURN);
		return importarDebitosAuxiliar.toString();
	}
	private String mensajeErrorVacio(ShvParamConfCampo shvParamConfCampo, String valor) {
		StringBuffer importarDebitosAuxiliar = new StringBuffer();
		importarDebitosAuxiliar.append(ConstantesCobro.LINEA_NRO);
		importarDebitosAuxiliar.append(nroRegistro);
		importarDebitosAuxiliar.append(ConstantesCobro.CAUSA);
		importarDebitosAuxiliar.append(ConstantesCobro.ERROR_EL_CAMPO);
		importarDebitosAuxiliar.append(shvParamConfCampo.getNombre());
		importarDebitosAuxiliar.append(" debe ser vacio");
		importarDebitosAuxiliar.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
		importarDebitosAuxiliar.append(valor);
		importarDebitosAuxiliar.append(Constantes.CLOSE_PARENTESIS);
		importarDebitosAuxiliar.append(Constantes.PUNTO);
		importarDebitosAuxiliar.append(Constantes.CARRIAGE_RETURN);
		return importarDebitosAuxiliar.toString();
	}
	private String mensajeErrorValor(ShvParamConfCampo confReglasBean, String valor, String mensaje) {
		StringBuffer str = new StringBuffer();
		str.append(ConstantesCobro.LINEA_NRO).append(nroRegistro);
		str.append(ConstantesCobro.CAUSA).append(ConstantesCobro.ERROR_TIPO_DE_DATO);
		str.append(Constantes.WHITESPACE).append(confReglasBean.getNombre());
		str.append(Constantes.DOS_PUNTOS);
		str.append(Constantes.WHITESPACE);
		str.append(mensaje);
		str.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
		str.append(valor);
		str.append(Constantes.CLOSE_PARENTESIS);
		str.append(Constantes.PUNTO);
		str.append(Constantes.CARRIAGE_RETURN);
		return str.toString();
	}
	/**
	 * 
	 * @param nombreCampo
	 * @param configuraciones
	 * @return
	 */
	private ConfReglaBean optenerConfiguracion(String nombreCampo, List<ConfReglaBean> configuraciones) {
		for (ConfReglaBean confReglaBean : configuraciones) {
			if (confReglaBean.getNombre().equals(nombreCampo)) {
				return confReglaBean;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param configuracionRta
	 * @return
	 */
	private List<ConfReglaBean> optenerConfiguraciones(Map<String, ConfReglasBean> configuracionRta) {
		List<ConfReglaBean> configuraciones = configuracionRta.get(ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion()).getReglas();
		SistemaEnum sistema = SistemaEnum.getEnumByDescripcionCorta((String) configuracionRta.get(ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion()).getValor());
		TipoComprobanteEnum tipoComprobante = TipoComprobanteEnum.getEnumByValor((String) configuracionRta.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion()).getValor());
		MonedaEnum monedaEnum = MonedaEnum.getEnumBySigno2((String) configuracionRta.get(ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion()).getValor());
		List<ConfReglaBean> configuracionFiltrada = new ArrayList<>();

		for (ConfReglaBean conf : configuraciones) {
			
			if (!Validaciones.isObjectNull(monedaEnum)){
				if (sistema.equals(conf.getSistema()) && tipoComprobante.equals(conf.getTipoComprobante()) && monedaEnum.equals(conf.getMoneda())) {
					configuracionFiltrada.add(conf);
				}
			} else if (sistema.equals(conf.getSistema()) && tipoComprobante.equals(conf.getTipoComprobante())) {
				configuracionFiltrada.add(conf);
			}
		}
		return configuracionFiltrada;
	}
	/**
	 * 
	 * @param configuracionRta
	 * @return
	 */
	private boolean camposSelectOk(Map<String, ConfReglasBean> configuracionRta) {
		boolean validado = true;
		for (Object key: configuracionRta.keySet()) {
			if (SiNoEnum.NO.equals(configuracionRta.get(key).getValidado())) {
				validado = false;
				break;
			}
		}
		return validado;
	}
	
	// Metodos Auxiliares
	/**
	 * Se valida que el campo venga informado
	 * @param campo
	 * @param confReglasBean
	 * @param nroRegistro
//	 * @param configuracionRta
	 * @return
	 */
	private boolean validarObligatoriedad(Object campo, ShvParamConfCampo confReglasBean, int nroRegistro) {
		boolean salida = true;
		if (Validaciones.isNullEmptyOrDash((String)campo)) {
			StringBuffer importarDebitosAuxiliar = new StringBuffer();
			importarDebitosAuxiliar.append(ConstantesCobro.LINEA_NRO);
			importarDebitosAuxiliar.append(nroRegistro);
			importarDebitosAuxiliar.append(ConstantesCobro.CAUSA);
			importarDebitosAuxiliar.append(ConstantesCobro.ERROR_EL_CAMPO);
			importarDebitosAuxiliar.append(confReglasBean.getNombre());
			importarDebitosAuxiliar.append(ConstantesCobro.ERROR_OBLIGATORIO);
			importarDebitosAuxiliar.append(Constantes.PUNTO);
			importarDebitosAuxiliar.append(Constantes.CARRIAGE_RETURN);
			salida = false;
			agregarError(ErrorBean.PRIORIDAD_OBLIGATORIEDAD, importarDebitosAuxiliar.toString());
		}
		return salida;
	}
	/**
	 * 
	 * @param campo
	 * @param confReglasBean
	 * @param nroRegistro
	 * @param dominiosRta
	 * @param configuracionRta
	 * @param confs
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean validarDominio(String campo, ShvParamConfCampo confReglasBean, int nroRegistro, Map<String, List<Object>> dominiosRta, Map<String, ConfReglasBean> configuracionRta, List<ConfReglaBean> confs) throws NegocioExcepcion {
		Object buscado = null;
		boolean error = true;
		List<Object> lista = null;
		List<String> listaOpciones = new ArrayList<String>();
		List<Object> listaExcluidos = this.restriccionesMap.get(confReglasBean.getNombre());
		if (Validaciones.isObjectNull(listaExcluidos)) {
			listaExcluidos = new ArrayList<Object>();
		}
		
		if (ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion().equals(confReglasBean.getNombre())) {
			buscado = SociedadEnum.getEnumByApocope(campo);
			lista = dominiosRta.get(ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion());
			for (Object object : lista) {
				listaOpciones.add(((SociedadEnum) object).getApocope());
			}
		} else if (ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion().equals(confReglasBean.getNombre())) {
			buscado = SistemaEnum.getEnumByDescripcionCorta(campo);
			lista = dominiosRta.get(ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion());
			for (Object object : lista) {
				listaOpciones.add(((SistemaEnum) object).getDescripcionCorta());
			}
		} else if (ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion().equals(confReglasBean.getNombre())) {
			buscado = TipoComprobanteEnum.getEnumByValor(campo);
			lista = dominiosRta.get(ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion());
			for (Object object : lista) {
				listaOpciones.add(((TipoComprobanteEnum) object).getValor());
			}
		} else if (ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion().equals(confReglasBean.getNombre())) {
			buscado = MonedaEnum.getEnumBySigno2(campo);
			lista = dominiosRta.get(ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion());
			for (Object object : lista) {
				listaOpciones.add(((MonedaEnum) object).getSigno2());
			}
		}
		
		if (!Validaciones.isObjectNull(buscado)){
			if (listaExcluidos.contains(buscado)) {
				error = false;
				agregarError(
					ErrorBean.PRIORIDAD_EXCLUIDO,
					this.mensajeErrorExluido(confReglasBean, campo, StringUtils.join(listaExcluidos.iterator(), " o ")).toString()
				);
			} else if (!lista.contains(buscado)) {
				error = false;
				agregarError(
					ErrorBean.PRIORIDAD_DOMINIO,
					this.mensajeErrorDominio(confReglasBean, campo, StringUtils.join(obtenerDescripcion(listaOpciones, confReglasBean).iterator(), " o ")).toString()
				);
			} else {
				configuracionRta.get(confReglasBean.getNombre()).setCampoNombre(confReglasBean.getNombre());
				configuracionRta.get(confReglasBean.getNombre()).setValor(buscado);
				configuracionRta.get(confReglasBean.getNombre()).setReglas(this.verificarConfiguracion(buscado, confs, confReglasBean.getNombre(), false));
			}
		} else {
			error = false;
		}
//		if (Validaciones.isObjectNull(buscado)) {
//			error = false;
//			agregarError(
//				ErrorBean.PRIORIDAD_DOMINIO,
//				this.mensajeErrorDominio(confReglasBean, campo, StringUtils.join(obtenerDescripcion(listaOpciones, confReglasBean).iterator(), " o ")).toString()
//			);
//		} else 
		return error;
	}
	
	private List<String> obtenerDescripcion(List<String> listaOpciones, ShvParamConfCampo confReglasBean){
		
		List<String> listaDescripciones = new ArrayList<String>();
		String descripcion;
		StringBuffer str;
		
		for (String dominio: listaOpciones){
			
			descripcion = "";
			
			if (ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion().equals(confReglasBean.getNombre())) {
				descripcion = SociedadEnum.getEnumByApocope(dominio).getDescripcion();
			} else if (ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion().equals(confReglasBean.getNombre())) {
				descripcion = SistemaEnum.getEnumByDescripcionCorta(dominio).getDescripcion();
			} else if (ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion().equals(confReglasBean.getNombre())) {
				// JFV
				descripcion = TipoComprobanteEnum.getEnumByValor(dominio).getDescripcion();
			} else if (ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion().equals(confReglasBean.getNombre())) {
				descripcion = MonedaEnum.getEnumBySigno2(dominio).getDescripcion();
			}
			
			str = new StringBuffer();
			str.append(dominio.replaceAll("[\\d]", ""));
			str.append(Constantes.WHITESPACE);
			str.append(Constantes.OPEN_CORCHETES).append(descripcion).append(Constantes.CLOSE_CORCHETES);
			
			listaDescripciones.add(str.toString());
			
		}
		
		return listaDescripciones;
		
	}
	
	private StringBuffer mensajeErrorExluido(ShvParamConfCampo confReglasBean, String valor, String dominio) {
		StringBuffer str = new StringBuffer();
		str.append(ConstantesCobro.LINEA_NRO).append(nroRegistro);
		str.append(ConstantesCobro.CAUSA).append("Valor no permitido del campo");
		str.append(Constantes.WHITESPACE).append(confReglasBean.getNombre());
		str.append(Constantes.DOS_PUNTOS);
		str.append(Constantes.WHITESPACE);
		str.append(dominio);
		str.append(" para la importacion");
		str.append(valor);
		str.append(Constantes.CARRIAGE_RETURN);
		return str;
	}
	private StringBuffer mensajeErrorDominio(ShvParamConfCampo confReglasBean, String valor, String dominio) {
		StringBuffer str = new StringBuffer();
		str.append(ConstantesCobro.LINEA_NRO).append(nroRegistro);
		str.append(ConstantesCobro.CAUSA).append(ConstantesCobro.ERROR_TIPO_DE_DATO);
		str.append(Constantes.WHITESPACE).append(confReglasBean.getNombre());
		str.append(Constantes.DOS_PUNTOS);
		str.append(Constantes.WHITESPACE);
		str.append(dominio);
		str.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
		str.append(valor);
		str.append(Constantes.CLOSE_PARENTESIS);
		str.append(Constantes.PUNTO);
		str.append(Constantes.CARRIAGE_RETURN);
		return str;
	}
	/**
	 * 
	 * @param value
	 * @param confs
	 * @param nombreCampo
	 * @param comoString
	 * @return
	 * @throws NegocioExcepcion
	 */
	private  List<ConfReglaBean> verificarConfiguracion(Object value, List<ConfReglaBean> confs, String nombreCampo, boolean comoString) throws NegocioExcepcion {
		List<ConfReglaBean> confsFiltrado = new ArrayList<ConfReglaBean>();
		
		for (ConfReglaBean conf : confs) {
			Object valueObject = null;
			try {
				Method method = null;
				method = conf.getClass().getMethod("get" + Utilidad.primeraLetraMayuscula(nombreCampo) + (comoString ? "Desc": ""));
				valueObject = method.invoke(conf, (Object[]) null);
				if (comoString) {
					String valorString = (String)value;
					if (valorString.equalsIgnoreCase(((String)valueObject))) {
						confsFiltrado.add(conf);
					}
				} else {
//					if (value.equals(valueObject)) {
//						confsFiltrado.add(conf);
//					}
					if (value.toString().equals(valueObject.toString().replaceAll("[\\d]", "_"))) {
						confsFiltrado.add(conf);
					}
				}
			} catch (Exception e) {
				throw new NegocioExcepcion(e.getMessage());
			}
		}
		return confsFiltrado;
	}
	/**
	 * 
	 * @param campos
	 * @param listarCamposImportacion
	 * @param confs
	 * @param shvParamConfCampoFixed
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean validacionRelacionadaFrisando(
		String campos[],
		List<ShvParamConfCampo> listarCamposImportacion,
		List<ConfReglaBean> confs,
		ShvParamConfCampo shvParamConfCampoFixed 
		) throws NegocioExcepcion {
		
		boolean salida = true;
		for (ShvParamConfCampo shvParamConfCampo : listarCamposImportacion) {
			if (!shvParamConfCampoFixed.equals(shvParamConfCampo)) {
				if ("SELECT".equalsIgnoreCase(shvParamConfCampo.getTipoDeDato())) {
					if (!this.obtenerOpciones(confs, shvParamConfCampo, campos[shvParamConfCampo.getOrdenCampos().intValue() - 1], shvParamConfCampoFixed)) {
						salida = false;
					}
//				} else {
//					if (!Validaciones.isCollectionNotEmpty(obtenerPorCampo(confs, shvParamConfCampo))) {
//						if (!Validaciones.isNullEmptyOrDash(campos[shvParamConfCampo.getOrdenCampos().intValue() - 1])) {
//							salida = false;
//							agregarError(
//								ErrorBean.PRIORIDAD_NO_VACIO,
//								mensajeErrorNoEsVacio(
//										shvParamConfCampoFixed,
//										shvParamConfCampo,
//										campos[shvParamConfCampo.getOrdenCampos().intValue() - 1]
//								).toString()
//							);
//						}
//					}
				}
			}
		}
		return salida;
	}
	
	// Error si el campo se informa con datos
	private StringBuffer mensajeErrorCampoInformado(ShvParamConfCampo shvParamConfCampoFixed, String valor) {
		
		StringBuffer str = new StringBuffer();
		str.append(ConstantesCobro.LINEA_NRO);
		str.append(nroRegistro);
		str.append(ConstantesCobro.CAUSA);
		str.append("El campo ");
		str.append(shvParamConfCampoFixed.getNombre());
		str.append(" debe estar vacio.");
		str.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
		str.append(valor);
		str.append(Constantes.CLOSE_PARENTESIS);
		str.append(Constantes.PUNTO);
		str.append(Constantes.CARRIAGE_RETURN);
		
		
		return str;
	}
	/**
	 * 
	 * @param confs
	 * @param shvParamConfCampo
	 * @param valor
	 * @param shvParamConfCampoFixed
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean obtenerOpciones(List<ConfReglaBean> confs, ShvParamConfCampo shvParamConfCampo, String valor, ShvParamConfCampo shvParamConfCampoFixed) throws NegocioExcepcion {
		Set<String> descripciones = new HashSet<String>();
		List<String> opciones = new ArrayList<String>();
		boolean salida = true;

		for (ConfReglaBean conf : confs) {
			Method method = null;
			try {
				method = conf.getClass().getMethod("get" + Utilidad.primeraLetraMayuscula(shvParamConfCampo.getNombre()) + "Desc");
				opciones.add(((String) method.invoke(conf, (Object[]) null)).replaceAll("[\\d]", ""));
			} catch (Exception e) {
				throw new NegocioExcepcion(e.getMessage());
			}
		}
		
		if (Validaciones.isCollectionNotEmpty(opciones) && !opciones.contains(valor)) {
			descripciones.addAll(obtenerDescripcion(opciones, shvParamConfCampo));
			agregarError(
				ErrorBean.PRIORIDAD_OPCION,
					mensajeErrorOpciones(
					shvParamConfCampoFixed,
					shvParamConfCampo,
					StringUtils.join(descripciones.iterator(), " o "),
					valor
				).toString()
			);
			salida = false;
		}
		return salida;
	}
//  Linea número: 13. Causa: Valor/es posible/s para el campo Referencia MIC, con Tipo de Documento DUC: Vacio (123123).
	private StringBuffer mensajeErrorOpciones(ShvParamConfCampo shvParamConfCampoFixed, ShvParamConfCampo shvParamConfCampo, String valorOpciones, String valor) {
		
		StringBuffer str = new StringBuffer();
		str.append("Linea número: ").append(nroRegistro).append(".");
		str.append(" Causa: ");
		str.append("Valor/es posible/s para el campo ");
		str.append(shvParamConfCampo.getNombre());
		str.append(", ");
		str.append("con ").append(shvParamConfCampoFixed.getNombre()).append(" ").append(campos[shvParamConfCampoFixed.getOrdenCampos().intValue() - 1]).append(": ");
		str.append(valorOpciones);
		str.append("(");
		str.append(valor);
		str.append(").").append(Constantes.CARRIAGE_RETURN);
		
		return str;
	}
	/**
	 * 
	 * @param confs
	 * @param shvParamConfCampo
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ConfReglaBean> obtenerPorCampo(List<ConfReglaBean> confs, ShvParamConfCampo shvParamConfCampo) throws NegocioExcepcion {
		List<ConfReglaBean> opciones = new ArrayList<ConfReglaBean>();

		for (ConfReglaBean conf : confs) {
			if (conf.getNombre().equalsIgnoreCase(shvParamConfCampo.getNombre())) {
				opciones.add(conf);
			} 
		}
		return opciones;
	}
	/**
	 * 
	 * @param campos
	 * @param listarCamposImportacion
	 * @param confs
	 * @param shvParamConfCampoFixed
	 * @return
	 * @throws NegocioExcepcion
	 */
	private boolean validacionRelacionadaFrisandoNoSelect(
		String campos[],
		List<ShvParamConfCampo> listarCamposImportacion,
		List<ConfReglaBean> confs,
		ShvParamConfCampo shvParamConfCampoFixed 
		) throws NegocioExcepcion {
		boolean salida = true;
		for (ShvParamConfCampo shvParamConfCampo : listarCamposImportacion) {
			if (!shvParamConfCampoFixed.equals(shvParamConfCampo)) {
				if ("SELECT".equalsIgnoreCase(shvParamConfCampo.getTipoDeDato())) {
					obtenerOpciones(confs, shvParamConfCampo, campos[shvParamConfCampo.getOrdenCampos().intValue() - 1], shvParamConfCampoFixed);
				}
			}
		}
		return salida;
	}
	
	public boolean validarDuplicidadEnCobro() {
		Map<String, String> mapComponetesIdPantalla = new HashMap<String, String>();

		for (ShvParamConfCampo confCampo : listarCamposImportacion) {
			mapComponetesIdPantalla.put(confCampo.getNombre(), campos[confCampo.getOrdenCampos().intValue() -1]);
		}
		
		if (importarDebitosAuxiliar.verificarExistenciaNumeroDocumento(generarIdPantalla(mapComponetesIdPantalla))) {
			importarDebitosAuxiliar.getResultadoValidaciones().append("Linea número: ").append(this.nroRegistro).append(". Causa: Registro Duplicado en el cobro. ");
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.RETORNO_UNIX);
			return true;
		}
		
		return false;
	}
	
	public void agregarError(Integer key, String descripcion){
		
		StringBuffer errores;
		
		if (Validaciones.isObjectNull(listaErrores.get(key))){
			errores = new StringBuffer();
		} else {
			errores = new StringBuffer(listaErrores.get(key));
		}
		
		errores.append(descripcion);
		listaErrores.put(key, errores.toString());
		
	}
/******************************************************************************/
	// Getter an Setter
	public List<ShvParamConfCampo> getListarCamposImportacion() {
		return listarCamposImportacion;
	}



	public void setListarCamposImportacion(
			List<ShvParamConfCampo> listarCamposImportacion) {
		this.listarCamposImportacion = listarCamposImportacion;
	}



	public Map<String, ConfReglasBean> getConfiguracionRta() {
		return configuracionRta;
	}



	public void setConfiguracionRta(Map<String, ConfReglasBean> configuracionRta) {
		this.configuracionRta = configuracionRta;
	}



	public Map<String, List<Object>> getDominiosRta() {
		return dominiosRta;
	}

	public void setDominiosRta(Map<String, List<Object>> dominiosRta) {
		this.dominiosRta = dominiosRta;
	}
	public List<ConfReglaBean> getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(List<ConfReglaBean> configuracion) {
		this.configuracion = configuracion;
	}
	public MonedaEnum getMonedaCobro() {
		return monedaCobro;
	}
	public void setMonedaCobro(MonedaEnum monedaCobro) {
		this.monedaCobro = monedaCobro;
	}
	public ImportacionDebitosAuxiliar getImportarDebitosAuxiliar() {
		return importarDebitosAuxiliar;
	}
	public void setImportarDebitosAuxiliar(
			ImportacionDebitosAuxiliar importarDebitosAuxiliar) {
		this.importarDebitosAuxiliar = importarDebitosAuxiliar;
	}
	public String[] getCampos() {
		return campos;
	}
	public void setCampos(String[] campos) {
		this.campos = campos;
	}
	public int getNroRegistro() {
		return nroRegistro;
	}
	public void setNroRegistro(int nroRegistro) {
		this.nroRegistro = nroRegistro;
	}
	
	/**
	 * Se setea todos los valores que seran excluidos para la clave del alta de Otros Debitos (Sociedad, Sistema, Tipo de Comprobate, Moneda) 	
	 * @param tipoComprobantesExcluido
	 */
	public void setTipoComprobantesExcluido(String tipoComprobantesExcluido) {
		String componetes[] = tipoComprobantesExcluido.split(";");
		for (String componente : componetes) {
			String campos[] = componente.split(":");
			
			String opciones[] = campos[1].split("-");
			List<Object> lista = new ArrayList<Object>();
			if (ParamConfCampoOtrosDebitosEnum.TIPOCOMPROBANTE.getDescripcion().equalsIgnoreCase(campos[0])) {
				for (String opc : opciones) {
					lista.add(TipoComprobanteEnum.getEnumByValor(opc));
				}
				this.restriccionesMap.put(campos[0], lista);
			} else if (ParamConfCampoOtrosDebitosEnum.SISTEMA.getDescripcion().equalsIgnoreCase(campos[0])) {
				for (String opc : opciones) {
					lista.add(SistemaEnum.getEnumByDescripcionCorta(opc));
				}
				this.restriccionesMap.put(campos[0], lista);
			} else if (ParamConfCampoOtrosDebitosEnum.SOCIEDAD.getDescripcion().equalsIgnoreCase(campos[0])) {
				for (String opc : opciones) {
					lista.add(SociedadEnum.getEnumByApocope(opc));
				}
				this.restriccionesMap.put(campos[0], lista);
			} else if (ParamConfCampoOtrosDebitosEnum.MONEDA.getDescripcion().equalsIgnoreCase(campos[0])) {
				for (String opc : opciones) {
					lista.add(MonedaEnum.getEnumBySigno2(opc));
				}
				this.restriccionesMap.put(campos[0], lista);
			}
		}
	}
	
}
