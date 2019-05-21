package ar.com.telecom.shiva.negocio.semaforo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.CamposReglasGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.CodigoSociedadSapEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresDocumentosCapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;

/**
 * @author u578936 MA.Uehara
 *
 */
public abstract class SemaforoGestionabilidad {
	private List<CamposReglasGestionabilidadEnum> nombreCamposResultado = null;
	// Se utiliza para ordenar la lista de reglas
	private Comparator<PostReglaEnum> comparador = null;
	private List<PostReglaEnum> listaReglas = null;

	public SemaforoGestionabilidad() {
		this.nombreCamposResultado = Arrays.asList(
			new CamposReglasGestionabilidadEnum[] {
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				CamposReglasGestionabilidadEnum.SEMAFORO,
				CamposReglasGestionabilidadEnum.OBSERVACIONES,
				CamposReglasGestionabilidadEnum.ERRORES_DEBITO,
				CamposReglasGestionabilidadEnum.ERRORES_CREDITO,
				CamposReglasGestionabilidadEnum.ERRORES_CAP
		});
		this.comparador = new Comparator<PostReglaEnum>() {
			@Override
			public int compare(PostReglaEnum o1, PostReglaEnum o2) {
				if (o1.posicion > o2.posicion) {
					return 1;
				} else if (o1.posicion < o2.posicion) {
					return -1;
				}
				return 0;
			}
		};
	}
	
	/**
	 * Valida si el objeto pasado como parametro es un CobroDebitoDto o CobroCreditoDto.
	 * Ademas llama al metodo validar() que se implementa en las clases derivadas
	 * @param dto
	 * @return True si valida el objeto y False en caso contrario
	 * @throws ShivaExcepcion
	 */
	protected boolean validarEntrada(DocumentoGestionableDTO dto) throws NegocioExcepcion{
		if (Validaciones.isObjectNull(dto)) {
			throw new NegocioExcepcion("No se proporciona un argumento");
		}
		if (dto instanceof CobroDebitoDto|| dto instanceof CobroCreditoDto || dto instanceof DocumentoCapDto) {
			return this.validar(dto);
		}
		return false;
	}; 
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	protected boolean isCampoResultado(CamposReglasGestionabilidadEnum key) {
		return nombreCamposResultado.contains(key);
	}
	
	/**
	 * Retorna un semaforo por defecto si el dto pasado como argumento cumple con
	 * las condiciones a desarrollar en la implementacion. Retorna una instancia
	 * de GestionabilidadDto. De lo contrario un null.
	 * @param dto
	 * @return
	 */
	protected abstract GestionabilidadDto retornarSemaforoPorDefecto(DocumentoGestionableDTO dto);
	
	/**
	 * Retorna el nombre de un getter para llamarlo por reflexion
	 * 
	 * @param key
	 * @param origen
	 * @param tipo
	 * @return
	 */
	protected abstract String armarGetter(CamposReglasGestionabilidadEnum key,String origen,String tipo);
	
	/**
	 * Llama al getter de una clase (CobroDebitoDto o CobroCreditoDto)
	 * @param key
	 * @param dto
	 * @return
	 */
	protected abstract Object llamarGetter(CamposReglasGestionabilidadEnum key,DocumentoGestionableDTO dto) throws NegocioExcepcion;
	
	/**
	 * Determina la gestionabilidad de un documento. Estas reglas estan detalladas
	 * en el documento "Gestionabilidad de documentos.xls"
	 * @param dto
	 * @return
	 * @throws ShivaExcepcion
	 */
	public GestionabilidadDto determinarGetionabilidad(DocumentoGestionableDTO dto) throws NegocioExcepcion {
		
		if (!this.validarEntrada(dto)) {
			StringBuilder str = new StringBuilder(MSG_SEMAFORO_NO_GESTIONABLE);
			Traza.debug(getClass(), str.toString());
			return this.retornaGestionabildadNoProcesada();
		}
		// Luego de veridicar si se cumple todas las resticciones de alguna
		// regla, la variable salida es TRUE si se cumple la regla y FALSE si no se
		// cumple ninguna regla
		boolean salida = false;

		GestionabilidadDto semaforo = this.retornarSemaforoPorDefecto(dto);
		if (semaforo != null) {
			return semaforo;
		}
		// Recorro las reglas de gestionabilidad de documentos
		for (PostReglaEnum posicionRegla : this.getListaReglas()) {
			Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(posicionRegla);
			
			// Recorro las partes de la regla corriente a partir del Key
			salida = recorrerRegla (regla,dto);

			//			for (CamposReglasGestionabilidadEnum key : regla.keySet()) {
//				if (!this.isCampoResultado(key) && !Validaciones.isNullOrEmpty(key.atributo())) {
//					Object valueObject = this.llamarGetter(key,dto);
//					salida = this.aplicarRegla(regla, key, valueObject, dto);
//					if (!salida) {
//						break; // Salgo del loop:
//					}
//				}
//			}
			
			// Si Salida es TRUE quiere decir que una Regla a pasado
			if (salida) {
				semaforo = new GestionabilidadDto(
					(String) regla.get(
						CamposReglasGestionabilidadEnum.COBRAR_DEUDA).get(0),
					(String) regla.get(
						CamposReglasGestionabilidadEnum.SEMAFORO).get(0),
					(String) regla.get(
						CamposReglasGestionabilidadEnum.OBSERVACIONES).get(0)
				);
				if (!SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo.getSemaforo())) {
					this.setearErroresGestionabilidad(
						posicionRegla,
						dto, 
						semaforo
					);
				} else if (SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo.getSemaforo())) {
					List<PostReglaEnum> reglasFueraGrupo = new ArrayList<PostReglaEnum>();
					reglasFueraGrupo.add(PostReglaEnum.R2_2);
					reglasFueraGrupo.add(PostReglaEnum.R3);
					

					// Son reglas para Semaforo Amarillos que no estan detenrminadas en grupo (SHIVA, MIC, o CALIPSO)
					// Una forma de eviatr esto es ordernar las reglas segun el semanforo. o sea.
					// Esto se logra con solo modificar las posiciones en PostReglaEnum
					for (PostReglaEnum reg : reglasFueraGrupo) {
						regla = this.obtenerReglaGestionabilidad(reg);
						salida = recorrerRegla(regla, dto);
						if (salida) {
							semaforo = new GestionabilidadDto(
									(String) regla.get(
										CamposReglasGestionabilidadEnum.COBRAR_DEUDA).get(0),
									(String) regla.get(
										CamposReglasGestionabilidadEnum.SEMAFORO).get(0),
									(String) regla.get(
										CamposReglasGestionabilidadEnum.OBSERVACIONES).get(0));
							
							if (!SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo.getSemaforo())) {
								this.setearErroresGestionabilidad(
									reg,
									dto, 
									semaforo
								);
								break;
							}
						}
					}
					
				}
				
				return semaforo;
			}
		}
		
//		salida = recorrerRegla(this.obtenerReglaGestionabilidad(PostReglaEnum.R3), dto);
//		Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(PostReglaEnum.R3);
//		if (salida) {
//			semaforo = new GestionabilidadDto(
//					(String) regla.get(
//						CamposReglasGestionabilidadEnum.COBRAR_DEUDA).get(0),
//					(String) regla.get(
//						CamposReglasGestionabilidadEnum.SEMAFORO).get(0),
//					(String) regla.get(
//						CamposReglasGestionabilidadEnum.OBSERVACIONES).get(0));
//			
//			if (!SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo.getSemaforo())) {
//				this.setearErroresGestionabilidad(
//					PostReglaEnum.R3,
//					dto, 
//					semaforo
//				);
//			}
//			
//			return semaforo;
//		}
		List<PostReglaEnum> reglasFueraGrupo = new ArrayList<PostReglaEnum>();
		reglasFueraGrupo.add(PostReglaEnum.R2_2);
		reglasFueraGrupo.add(PostReglaEnum.R3);
		

		// Son reglas para Semaforo Amarillos que no estan detenrminadas en grupo (SHIVA, MIC, o CALIPSO)
		// Una forma de eviatr esto es ordernar las reglas segun el semanforo. o sea.
		// Esto se logra con solo modificar las posiciones en PostReglaEnum
		for (PostReglaEnum reg : reglasFueraGrupo) {
			Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(reg);
			salida = recorrerRegla(regla, dto);
			if (salida) {
				semaforo = new GestionabilidadDto(
						(String) regla.get(
							CamposReglasGestionabilidadEnum.COBRAR_DEUDA).get(0),
						(String) regla.get(
							CamposReglasGestionabilidadEnum.SEMAFORO).get(0),
						(String) regla.get(
							CamposReglasGestionabilidadEnum.OBSERVACIONES).get(0));
				
				if (!SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo.getSemaforo())) {
					this.setearErroresGestionabilidad(
						reg,
						dto, 
						semaforo
					);
				}
				return semaforo;
			}
		}
		// No puede retornar Null. Asigno un Semaforo por defecto verde
		semaforo = new GestionabilidadDto(
			"Si",
			SemaforoGestionabilidadEnum.VERDE.getDescripcion(),
			MSG_SEMAFORO_NO_CON_CUMPLE_NINGUNA_REGLA
		);
		StringBuilder str = new StringBuilder(MSG_SEMAFORO_NO_CON_CUMPLE_NINGUNA_REGLA);
		Traza.debug(getClass(), str.toString());	
		return semaforo;
	}

	private boolean recorrerRegla(Map<CamposReglasGestionabilidadEnum, List<Object>> regla, DocumentoGestionableDTO dto) throws NegocioExcepcion {
		
		boolean salida = false;
		
		for (CamposReglasGestionabilidadEnum key : regla.keySet()) {
			if (!this.isCampoResultado(key) && !Validaciones.isNullOrEmpty(key.atributo())) {
				
				Object valueObject = this.llamarGetter(key,dto);
				salida = this.aplicarRegla(regla, key, valueObject, dto);
				if (!salida) {
					break; // Salgo del loop:
				}
			}
		}
		
		return salida;
	}

	/**
	 * 
	 * @param posicion
	 * @param dto
	 * @param semaforo
	 */
	protected abstract void setearErroresGestionabilidad(PostReglaEnum posicion,DocumentoGestionableDTO dto,GestionabilidadDto semaforo);
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	protected abstract boolean validar(DocumentoGestionableDTO dto);

	/**
	 * 
	 * @param regla
	 * @param key
	 * @param valueObject
	 * @param dto
	 * @return
	 */
	protected boolean aplicarRegla(
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla,
		CamposReglasGestionabilidadEnum key,
		Object valueObject,
		DocumentoGestionableDTO dto) 
	{
		String sistemaOrigen="";

		if (dto instanceof CobroDebitoDto) {
			sistemaOrigen = ((CobroDebitoDto) dto).getSistemaOrigen().getDescripcion();
		} else  if (dto instanceof CobroCreditoDto){
			sistemaOrigen = ((CobroCreditoDto) dto).getSistemaOrigen().getDescripcion();
		} else if (dto instanceof DocumentoCapDto){
			sistemaOrigen = ((DocumentoCapDto) dto).getSistemaOrigen().getDescripcion();
		}

		// Casos especiales: es un mumerico
		if (CamposReglasGestionabilidadEnum.CANTIDAD_CUOTAS_PAGAS_DEIMOS.equals(key)) {
			if (valueObject != null) {
				Long valor = (Long) valueObject;
				switch ((String)regla.get(key).get(0)) {
				case ">0":
					if (valor != null && valor > 0l) {
						return true;
					}
					break;
				default:
					break;
				}
			}
		}
		
		
		
		// moneda del documento diferente a... 
//		if (CamposReglasGestionabilidadEnum.NOT_MONEDA.equals(key)) {
//			if (!regla.get(key).contains(valueObject)) {
//				return true;
//			} else {
//				return false;
//			}
//		}
		// moneda del documento diferente a... 
		if (key.name().startsWith("NOT_")) {
			if (!regla.get(key).contains(valueObject)) {
				return true;
			} else {
				return false;
			}
		}
		
		if (
			CamposReglasGestionabilidadEnum.SALDO_PESIFICADO_FECHA_DE_EMISION.equals(key) ||
			CamposReglasGestionabilidadEnum.SALDO_PRIMER_VENC_MON_ORIGEN.equals(key) ||
			CamposReglasGestionabilidadEnum.SALDO_MONEDA_ORIGEN.equals(key)
		) {
			if (valueObject != null ) {
				String cadena = (String) valueObject;
				BigDecimal saldo = null;
				if (Validaciones.isNullEmptyOrDash(cadena.trim())) {
					return false;
				} else {
					saldo = Utilidad.stringToBigDecimal(cadena.trim());
				}
				BigDecimal compara = new BigDecimal("0.00");
				switch ((String)regla.get(key).get(0)) {
				case "=0,00":
					if (saldo.compareTo(compara) == 0) {
						return true;
					}
					break;
				default:
					break;
				}
			}
			return false;
		}
		if (
			SistemaEnum.CALIPSO.getDescripcion().equals(sistemaOrigen) &&
			CamposReglasGestionabilidadEnum.ESTADO.equals(key)
		) {
			if (regla.get(key).contains(RestriccionCampo.NULL)) {
				if (valueObject == null) {
					return true;
				}
			} else {
				return regla.get(key).contains(valueObject);
			}
		}
		if (
			SistemaEnum.SHIVA.getDescripcion().equals(sistemaOrigen) &&
			CamposReglasGestionabilidadEnum.MOTIVO.equals(key)
		) {
			if (regla.get(key).contains(RestriccionCampo.NULL)) {
				if (valueObject == null) {
					return true;
				}
			} else {
				return regla.get(key).contains(valueObject);
			}
		}
		if (regla.get(key).contains(valueObject)) {
			return true;
		}
		return false;
	}

	private Map<CamposReglasGestionabilidadEnum, List<Object>> agregarRetorno(
			Map<CamposReglasGestionabilidadEnum, List<Object>> regla,
			SemaforoGestionabilidadEnum semaforo,
			String cobrarDeuda,
			String observaciones
		) {
		return agregarRetorno(
			regla,
			semaforo,
			cobrarDeuda,
			observaciones,
			null,
			null,
			null
		);
	}
	private Map<CamposReglasGestionabilidadEnum, List<Object>> agregarRetorno(
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla,
		SemaforoGestionabilidadEnum semaforo,
		String cobrarDeuda,
		String observaciones,
		Object[] erroresDebito,
		Object[] erroresCredito,
		Object[] erroresDocCap
	) {
		regla.put(
			CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
			Arrays.asList(
				new Object[] {
					cobrarDeuda
		}));
		regla.put(
			CamposReglasGestionabilidadEnum.SEMAFORO,
			Arrays.asList(
				new Object[] {
					semaforo.getDescripcion()
		}));
		regla.put(
			CamposReglasGestionabilidadEnum.OBSERVACIONES,
			Arrays.asList(
				new Object[] {
					observaciones
		}));
		if(erroresDebito != null && erroresDebito.length != 0) {
			regla.put(
				CamposReglasGestionabilidadEnum.ERRORES_DEBITO,
				Arrays.asList(
					erroresDebito
			));
		}
		if(erroresCredito != null && erroresCredito.length != 0) {
			regla.put(
				CamposReglasGestionabilidadEnum.ERRORES_CREDITO,
				Arrays.asList(
					erroresCredito
			));
		}
		
		if(erroresDocCap != null && erroresDocCap.length != 0) {
			regla.put(
				CamposReglasGestionabilidadEnum.ERRORES_CAP,
				Arrays.asList(
						erroresDocCap
			));
		}
		return regla;
	}
	
	private Map<CamposReglasGestionabilidadEnum, List<Object>> agregarRetorno(
			Map<CamposReglasGestionabilidadEnum, List<Object>> regla,
			SemaforoGestionabilidadEnum semaforo,
			String cobrarDeuda,
			String observaciones,
			Object[] erroresCap
		) {
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						cobrarDeuda
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SEMAFORO,
				Arrays.asList(
					new Object[] {
						semaforo.getDescripcion()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.OBSERVACIONES,
				Arrays.asList(
					new Object[] {
						observaciones
			}));
			if(erroresCap != null && erroresCap.length != 0) {
				regla.put(
					CamposReglasGestionabilidadEnum.ERRORES_CAP,
					Arrays.asList(
							erroresCap
				));
			}
			return regla;
		}
	/**
	 * Obtiene las reglas para determinar la
	 * gestionabilidad de deuda. Retorna una regla segun un numero de
	 * posicion. Esta regla esta descripta en un Map<"Attributo", >
	 * @return Map<CamposReglasGestionabilidadEnum, List<Object>>
	 */
	protected Map<CamposReglasGestionabilidadEnum, List<Object>> obtenerReglaGestionabilidad(
		PostReglaEnum posicionRegla
	) {
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla =
			new HashMap<CamposReglasGestionabilidadEnum, List<Object>>();
		switch (posicionRegla) {
		case R35:
			/*** Regla R35 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA_OPERACION,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			// La regla en si es Moneda <> PES pero al no tener el negado 
			regla.put(
				CamposReglasGestionabilidadEnum.NOT_MONEDA,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES.getSigno2()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SALDO_PESIFICADO_FECHA_DE_EMISION,
				Arrays.asList(
					new Object[] {
						"=0,00"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R35",
				new Object[] {
					ErroresDebitoEnum.E_8702,
				},
				new Object[] {
					ErroresCreditoEnum.E_8702,
				},
				null
			);
			break;
		case R36:
			/*** Regla 17 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA_OPERACION,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES.getSigno2()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SALDO_PRIMER_VENC_MON_ORIGEN,
				Arrays.asList(
					new Object[] {
						"=0,00"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R36",
				new Object[] {
					ErroresDebitoEnum.E_8702,
				},
				new Object[] {
					ErroresCreditoEnum.E_8702,
				},
				null
			);
			break;
		case R37:
			/*** Regla 17 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA_OPERACION,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA,
				Arrays.asList(
					new Object[] {
						MonedaEnum.PES.getSigno2()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.CRE,
						TipoComprobanteEnum.CTA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SALDO_MONEDA_ORIGEN,
				Arrays.asList(
					new Object[] {
						"=0,00"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R37",
				new Object[] {
					ErroresDebitoEnum.E_8702,
				},
				new Object[] {
					ErroresCreditoEnum.E_8702,
				},
				null
			);
			break;
		case R38:
			/*** Regla 17 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA_OPERACION,
				Arrays.asList(
					new Object[] {
						MonedaEnum.DOL
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA,
				Arrays.asList(
					new Object[] {
						MonedaEnum.DOL.getSigno2()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SALDO_PRIMER_VENC_MON_ORIGEN,
				Arrays.asList(
					new Object[] {
						"=0,00"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R38",
				new Object[] {
					ErroresDebitoEnum.E_8702,
				},
				new Object[] {
					ErroresCreditoEnum.E_8702,
				},
				null
			);
			break;
		case R39:
			/*** Regla 17 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA_OPERACION,
				Arrays.asList(
					new Object[] {
						MonedaEnum.DOL
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MONEDA,
				Arrays.asList(
					new Object[] {
						MonedaEnum.DOL.getSigno2()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.CRE,
						TipoComprobanteEnum.CTA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SALDO_MONEDA_ORIGEN,
				Arrays.asList(
					new Object[] {
						"=0,00"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R39",
				new Object[] {
					ErroresDebitoEnum.E_8702,
				},
				new Object[] {
					ErroresCreditoEnum.E_8702,
				},
				null
			);
			break;
		case R2:
			/*** Regla 2 t1 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
				Arrays.asList(new Object[] {SiNoEnum.SI}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R2",
				new Object[] {
					ErroresDebitoEnum.E_8999
				},
				new Object[] {
					ErroresCreditoEnum.E_8999
				},
				new Object[] {
					ErroresDocumentosCapEnum.E_8999
				}
			);
			break;
		case R2_SHIVA:
			/*** Regla 2_SHIVA t1 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
				Arrays.asList(new Object[] {SiNoEnum.SI}));
			regla.put(
					CamposReglasGestionabilidadEnum.ORIGEN,
					Arrays.asList(new Object[] {SistemaEnum.SHIVA}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R2_SHIVA",
				new Object[] {
					ErroresDebitoEnum.E_8999
				},
				new Object[] {
					ErroresCreditoEnum.E_8999
				},
				new Object[] {
					ErroresDocumentosCapEnum.E_8999
				}
			);
			break;
		case R2_NOT_SHIVA:
			/*** Regla 2 NOT SHIVA t1 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
				Arrays.asList(new Object[] {SiNoEnum.SI}));
			regla.put(
					CamposReglasGestionabilidadEnum.NOT_ORIGEN,
					Arrays.asList(new Object[] {SistemaEnum.SHIVA}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"Si",
				"R2_NOT_SHIVA",
				new Object[] {
					ErroresDebitoEnum.E_8999
				},
				new Object[] {
					ErroresCreditoEnum.E_8999
				},
				new Object[] {
					ErroresDocumentosCapEnum.E_8999
				}
			);
			break;
		case R2_1:
			/*** Regla 2_2 t2 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_REVERSION_PROCESO_PENDIENTE,
				Arrays.asList(new Object[] {SiNoEnum.SI}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R2_1",
				new Object[] {
					ErroresDebitoEnum.E_8700
				},
				new Object[] {
					ErroresCreditoEnum.E_8700
				},
				null
			);
			break;
		case R2_2:
			/*** Regla 2_2 t2 ***/
			
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_REVERSION_EDICION,
				Arrays.asList(new Object[] {SiNoEnum.SI}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R2_2",
				new Object[] {
					ErroresDebitoEnum.W_8701
				},
				new Object[] {
					ErroresCreditoEnum.W_8701
				},
				new Object[] {
					ErroresDocumentosCapEnum.W_8701
				}
			);
			break;
		case R3:
			/*** Regla 3 t3 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R3",
				new Object[] {
					ErroresDebitoEnum.W_8501
				},
				new Object[] {
					ErroresCreditoEnum.W_8501
				},
				new Object[] {
					ErroresDocumentosCapEnum.W_8501
				}
			);
			break;
		case R5:
			/*** Regla 5 t4 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
				Arrays.asList(
					new Object[] {
						TipoFacturaEnum.FACTURA_ONLINE,
						TipoFacturaEnum.NC_ONLINE
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R5",
				new Object[] {
					ErroresDebitoEnum.E_8001
				},
				new Object[] {
					ErroresCreditoEnum.E_8001
				},
				null
			);
			break;
		case R6:
			/*** Regla 6 t5 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.FACTURADA,
						EstadoOrigenEnum.ENV_A_DA,
						EstadoOrigenEnum.CONVENIO_PAGO_CUOTAS,
						EstadoOrigenEnum.ANULADA,
						EstadoOrigenEnum.COMPENSADORA,
						EstadoOrigenEnum.COBRADA_POR_DA,
						EstadoOrigenEnum.COBRADA_OF_TECO,
						EstadoOrigenEnum.COBRADA_EN_BANCO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R6",
				new Object[] {
					ErroresDebitoEnum.E_8020,
					ErroresDebitoEnum.E_8021,
					ErroresDebitoEnum.E_8022,
					ErroresDebitoEnum.E_8023,
					ErroresDebitoEnum.E_8024,
					ErroresDebitoEnum.E_8025,
					ErroresDebitoEnum.E_8026,
					ErroresDebitoEnum.E_8027
				},
				new Object[] {
					ErroresCreditoEnum.E_8020,
					ErroresCreditoEnum.E_8021,
					ErroresCreditoEnum.E_8022,
					ErroresCreditoEnum.E_8023,
					ErroresCreditoEnum.E_8024
				},
				null
			);
			break;
		case R7:
			/*** Regla 7 t6 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(new Object[] {
					SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(
					new Object[] {
						MarcaReclamoEnum.PENDIENTE 
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R7",
				new Object[] {
					ErroresDebitoEnum.E_8060,
				},
				new Object[] {
					ErroresCreditoEnum.E_8060,
				},
				null
			);
			break;
		case R9:
			/*** Regla 9 t7 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.MIGRADA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_DEIMOS,
				Arrays.asList(
					new Object[] {
						EstadoTramiteDeimosEnum.EGCA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.CANTIDAD_CUOTAS_PAGAS_DEIMOS,
				Arrays.asList(
					new Object[] {
						">0"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R9",
				new Object[] {
					ErroresDebitoEnum.E_8040,
				},
				new Object[] {
					ErroresCreditoEnum.E_8040,
				},
				null
			);
			break;
		case R10:
			/*** Regla 10 t8 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.MIGRADA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_DEIMOS,
				Arrays.asList(
					new Object[] {
						EstadoTramiteDeimosEnum.CO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R10",
				new Object[] {
					ErroresDebitoEnum.E_8041,
				},
				new Object[] {
					ErroresCreditoEnum.E_8041,
				},
				null
			);
			break;
		case R8:
			/*** Regla 8 t9 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.MIGRADA
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R8",
				new Object[] {
					ErroresDebitoEnum.W_8502
				},
				new Object[] {
					ErroresCreditoEnum.W_8502
				},
				null
			);
			break;
		case R11:
			/*** Regla 11 t10 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
					Arrays.asList(
						new Object[] {
							TipoComprobanteEnum.FAC,
							TipoComprobanteEnum.DEB,
							TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
				Arrays.asList(
					new Object[] {
						TipoFacturaEnum.FACT_FLEXCAB,
						TipoFacturaEnum.FACT_BAJAS,
						TipoFacturaEnum.FACT_POST_BAJ,
						TipoFacturaEnum.FACT_INIC_ESP,
						TipoFacturaEnum.NOTA_DEB_N_PAG,
						TipoFacturaEnum.INTERES_RECARGO,
						TipoFacturaEnum.DESCUENTOS,
						TipoFacturaEnum.DERECHO_REHABIL,
						TipoFacturaEnum.ND_INICIAL_ESP,
						TipoFacturaEnum.DESC_F_INIC_ESP,
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.ENV_AL_CLIENTE,
						EstadoOrigenEnum.AVISO_DE_INCOMUNIC,
						EstadoOrigenEnum.IMPAGO_AF_NO_INCOMP,
						EstadoOrigenEnum.SUSP_PROC_INCOMUNI,
						EstadoOrigenEnum.IMPAGO_GEST_PERSONAL,
						EstadoOrigenEnum.AVISO_INC_GP_AUT,
						EstadoOrigenEnum.AVISO_INC_GP,
						EstadoOrigenEnum.INC_GP_AUTORIZADO,
						EstadoOrigenEnum.ORDEN_INC_GENERADA,
						EstadoOrigenEnum.REAB_PEND_COBRO_GEN,
						EstadoOrigenEnum.REAB_PEND_COBRO,
						EstadoOrigenEnum.ORD_INC_GENERADA_GP,
						EstadoOrigenEnum.BAJA_GESTIO_PERS_AUT,
						EstadoOrigenEnum.ORD_BAJ_GES_PERS_GEN,
						EstadoOrigenEnum.INCOMUNICADO,
						EstadoOrigenEnum.ORDEN_BAJA_GENERADA,
						EstadoOrigenEnum.AVISO_BAJA_ENVIADO,
						EstadoOrigenEnum.ACUSE_DE_RECIBO,
						EstadoOrigenEnum.INCOMUNICADO_GP,
						EstadoOrigenEnum.AVISO_BAJA_GP_AUT,
						EstadoOrigenEnum.AVISO_BAJA_GP,
						EstadoOrigenEnum.ACUSE_RES_GES_PERSON,
						EstadoOrigenEnum.BAJA,
						EstadoOrigenEnum.IMPAGO_FACT_ESPECIAL
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(
					new Object[] {
						MarcaReclamoEnum.SIN_MARCA,
						MarcaReclamoEnum.ANULADO,
						MarcaReclamoEnum.RESUELTO_A,
						MarcaReclamoEnum.RESUELTO_P,
						MarcaReclamoEnum.RESUELTO_T
				}));
//TODO Marca modulos marcasde Eva Deprecado
//			regla.put(
//					CamposReglasGestionabilidadEnum.MARCA_MODULO_MARCAS_EVA,
//					Arrays.asList(new Object[] { "Sin marca", "Desistido",
//							"En Legales", "En financiación", "Reclamada",
//							"Incobrables", "En Quiebra", "En Concurso",
//							"En Legales Posbaja", "(*)" }));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
					Arrays.asList(
						new Object[] {
							SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
					Arrays.asList(
						new Object[] {
							SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R11"
			);
			break;
		case R32:
			/*** Regla 32 t11 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.DUC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.COBRADO,
						EstadoOrigenEnum.COBRADO_Y_APLIC_REF,
						EstadoOrigenEnum.COBRADO_Y_MOV_RTE_I,
						EstadoOrigenEnum.COBRADO_Y_TRANSFER,
						EstadoOrigenEnum.COBRADO_Y_ENV_FACT,
						EstadoOrigenEnum.COBRADO_Y_UTILIZADO,
						EstadoOrigenEnum.APROPIADO_SHIVA
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R32",
				new Object[] {
					ErroresDebitoEnum.E_8061,
					ErroresDebitoEnum.E_8062,
					ErroresDebitoEnum.E_8063,
					ErroresDebitoEnum.E_8064,
					ErroresDebitoEnum.E_8065,
					ErroresDebitoEnum.E_8066,
					ErroresDebitoEnum.E_8067
				},
				null,
				null
			);
			break;
		case R12:
			/*** Regla 12 t12 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.DUC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(
					new Object[] {
						MarcaReclamoEnum.PENDIENTE
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R12",
				new Object[] {
					ErroresDebitoEnum.E_8060,
				},
				null,
				null
			);
			break;
		case R13:
			/*** Regla 13 t13 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
					Arrays.asList(
						new Object[] {
							SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.DUC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO_DUC,
				Arrays.asList(
					new Object[] {
						TipoDUCEnum.VENTA_LINEAS,
						TipoDUCEnum.SERV_TEMPORARIOS,
						TipoDUCEnum.ANTIC_FINANCIACION,
						TipoDUCEnum.ADELANTOS,
						TipoDUCEnum.DESVIO_CONSULMO,
						TipoDUCEnum.USO_INDEBIDO,
						TipoDUCEnum.CBIO_DOMICILIO,
						TipoDUCEnum.CBIO_NUMERO,
						TipoDUCEnum.CBIO_TITULARIDAD,
						TipoDUCEnum.CBIO_DATOS_CLIENTE,
						TipoDUCEnum.CUOTAS_FINANCIACION,
						TipoDUCEnum.ANTIC_FINAN_CONDOR,
						TipoDUCEnum.PAGO_CTA_FACT,
						TipoDUCEnum.VENTA_TP_CAB_MINI,
						TipoDUCEnum.PAGO_ANTIC_TX_OYP,
						TipoDUCEnum.PAGO_ANTICIPO_OYP,
						TipoDUCEnum.CUOTA_PLAN_FINAN_OYP,
						TipoDUCEnum.PAGO_GARANTIA_OYP,
						TipoDUCEnum.ANTICIPO_DE_FINANCIA,
						TipoDUCEnum.ANTIC_FINAN_SUR,
						
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.GENERADO,
						EstadoOrigenEnum.ENVIADO,
						EstadoOrigenEnum.VENCIDO,
						EstadoOrigenEnum.CONV_BAJA_INEX
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(
					new Object[] { 
						MarcaReclamoEnum.SIN_MARCA,
						MarcaReclamoEnum.ANULADO,
						MarcaReclamoEnum.RESUELTO_A,
						MarcaReclamoEnum.RESUELTO_P,
						MarcaReclamoEnum.RESUELTO_T
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
					Arrays.asList(
						new Object[] {
							SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R13"
			);
			break;
		case R14:
			/*** Regla 14 t14 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.REM
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
				Arrays.asList(
					new Object[] {
						TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE,
						TipoRemanenteEnum.FICTICIO_PARA_NOTAS_DE_CREDITO_MIC,
						TipoRemanenteEnum.DEPOSITOS_EN_GARANTIA,
						TipoRemanenteEnum.PAGO_A_CUENTA_POR_RECLAMO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R14",
				null,
				new Object[] {
					ErroresCreditoEnum.E_8049,
					ErroresCreditoEnum.E_8050,
					ErroresCreditoEnum.E_8051,
					ErroresCreditoEnum.E_8052
				},
				null
			);
			break;
		case R15:
			/*** Regla 15 t15 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.MIC
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.REM
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
				Arrays.asList(
					new Object[] {
						TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE,
						TipoRemanenteEnum.NO_TRANSFERIBLE_NO_ACTUALIZABLE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
					Arrays.asList(
						new Object[] {
							SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R15"
			);
			break;
		case R17:
			/*** Regla 17 t16 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(new Object[] {
					TipoComprobanteEnum.FAC,
					TipoComprobanteEnum.DEB,
					TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
							EstadoOrigenEnum.DESISTIDO,
							EstadoOrigenEnum.ENV_A_DA
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R17",
				new Object[] {
					ErroresDebitoEnum.E_8030,
					ErroresDebitoEnum.E_8031,
				},
				new Object[] {
					ErroresCreditoEnum.E_8030,
				},
				null
			);
			break;
		case R19:
			/*** Regla 19 t17 ***/
			regla.put(CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_MIGRACION_ORIGEN,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_DEIMOS,
				Arrays.asList(
					new Object[] {
						EstadoTramiteDeimosEnum.EGCA
			}));
			// TODO CASO ESPECIAL
			regla.put(
				CamposReglasGestionabilidadEnum.CANTIDAD_CUOTAS_PAGAS_DEIMOS,
				Arrays.asList(
					new Object[] {
						">0"
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO, 
				"No",
				"R19",
				new Object[] {
					ErroresDebitoEnum.E_8040,
				},
				new Object[] {
					ErroresCreditoEnum.E_8040,
				},
				null
			);
			break;
		case R20:
			/*** Regla 20 t18 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_MIGRACION_ORIGEN,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_DEIMOS,
				Arrays.asList(
					new Object[] {
						EstadoTramiteDeimosEnum.CO
				}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R20",
				new Object[] {
					ErroresDebitoEnum.E_8041,
				},
				new Object[] {
					ErroresCreditoEnum.E_8041,
				},
				null
			);
			break;
		case R18: // TODO tiene que ir despues del 19 20
			/*** Regla 18 t19 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_MIGRACION_ORIGEN,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R18",
				new Object[] {
					ErroresDebitoEnum.W_8503
				},
				new Object[] {
					ErroresCreditoEnum.W_8503
				},
				null
			);
			break;
		case R21:
			/*** Regla 21 t20 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(new Object[] {
					SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(new Object[] {
					MarcaReclamoEnum.RECLAMADA
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R21",
				new Object[] {
					ErroresDebitoEnum.W_8504,
				},
				new Object[] {
					ErroresCreditoEnum.W_8504,
				},
				null
			);
			break;
		case R22:
			/*** Regla 22 t21 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] { 
						EstadoOrigenEnum.CONCURSO,
						EstadoOrigenEnum.FINANCIADA,
						EstadoOrigenEnum.INCOBRABLE,
						EstadoOrigenEnum.INCOBRABLES,
						EstadoOrigenEnum.LEGALES,
						EstadoOrigenEnum.NO_INNOVAR,
						EstadoOrigenEnum.QUIEBRA,
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R22",
				new Object[] {
					ErroresDebitoEnum.W_8505,
					ErroresDebitoEnum.W_8506,
					ErroresDebitoEnum.W_8507,
					ErroresDebitoEnum.W_8508,
					ErroresDebitoEnum.W_8509,
					ErroresDebitoEnum.W_8510,
					ErroresDebitoEnum.W_8511,
				},
				new Object[] {
					ErroresCreditoEnum.W_8505,
					ErroresCreditoEnum.W_8506,
					ErroresCreditoEnum.W_8507,
					ErroresCreditoEnum.W_8508,
					ErroresCreditoEnum.W_8509,
					ErroresCreditoEnum.W_8510,
					ErroresCreditoEnum.W_8511,
				},
				null
			);
			break;
		case R23:
			/*** Regla 23 22 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.FAC,
						TipoComprobanteEnum.DEB,
						TipoComprobanteEnum.CRE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.ADM,
						EstadoOrigenEnum.CARTA_COMERCIAL,
						EstadoOrigenEnum.PRIMER_CARTA_DOC,
						EstadoOrigenEnum.SEGUNDA_CARTA_DOC,
						EstadoOrigenEnum.WATERMARK,
						//TODO Esto esta hecho asi. Por que nesesito hacer una restriccion
						// que mache cuando no hay estado
						RestriccionCampo.NULL
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_RECLAMO_ORIGEN,
				Arrays.asList(
					new Object[] {
						MarcaReclamoEnum.NO_RECLAMADA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_MIGRACION_ORIGEN,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R23"
			);
			break;
		case R24:
			/*** Regla 24 t23 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.CALIPSO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO,
				Arrays.asList(
					new Object[] {
						TipoComprobanteEnum.CTA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
					Arrays.asList(
						new Object[] {
							SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R24"
			);
			break;
		case R26:
			/*** Regla 26 t25 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SHIVA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MOTIVO,
					Arrays.asList(
						new Object[] {
							MotivoShivaEnum.VALOR_EN_GARANTIA,
							MotivoShivaEnum.VALOR_POR_RECLAMO_DE_FACTURACION
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R26",
				null,
				new Object[] {
					ErroresCreditoEnum.E_8204,
					ErroresCreditoEnum.E_8205
				},
				null
			);
			break;
		case R27:
			/*** Regla 27 t26 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SHIVA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
					Arrays.asList(
						new Object[] {
							TipoCreditoEnum.EFECTIVO,
							TipoCreditoEnum.CHEQUE,
							TipoCreditoEnum.CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO,
							TipoCreditoEnum.TRANSFERENCIA,
							TipoCreditoEnum.INTERDEPOSITO,
							TipoCreditoEnum.RETENCION
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.NO_DISPONIBLE,
						EstadoOrigenEnum.AVISO_DE_PAGO_PENDIENTE_DE_CONFIRMAR,
						EstadoOrigenEnum.AVISO_DE_PAGO_RECHAZADO,
						EstadoOrigenEnum.USADO,
						EstadoOrigenEnum.SUSPENDIDO,
						EstadoOrigenEnum.ANULADO,
						EstadoOrigenEnum.BOLETA_PENDIENTE_DE_AUTORIZAR,
						EstadoOrigenEnum.BOLETA_RECHAZADA
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R27",
				null,
				new Object[] {
					ErroresCreditoEnum.E_8206,
					ErroresCreditoEnum.E_8207,
					ErroresCreditoEnum.E_8208,
					ErroresCreditoEnum.E_8209,
					ErroresCreditoEnum.E_8210,
					ErroresCreditoEnum.E_8211,
					ErroresCreditoEnum.E_8213,
					ErroresCreditoEnum.E_8214
				},
				null
			);
			break;
		case R28:
			/*** Regla 28 t27 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SHIVA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
					Arrays.asList(
						new Object[] {
							TipoCreditoEnum.EFECTIVO,
							TipoCreditoEnum.CHEQUE,
							TipoCreditoEnum.CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO,
							TipoCreditoEnum.TRANSFERENCIA,
							TipoCreditoEnum.INTERDEPOSITO,
							TipoCreditoEnum.RETENCION
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.DISPONIBLE
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MOTIVO,
				Arrays.asList(
					new Object[] {
						MotivoShivaEnum.CONCURSOS_Y_QUIEBRA,
						MotivoShivaEnum.POSTBAJA,
						MotivoShivaEnum.COBRANZA_REGULAR,
						MotivoShivaEnum.CHEQUE_RECHAZADO,
						MotivoShivaEnum.PAGO_A_CUENTA,
						MotivoShivaEnum.COBRO_PLAN_DE_FINACIACION,
						MotivoShivaEnum.CHEQUE_DE_GARANTIA_CORRIENTE,
						RestriccionCampo.NULL
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
				}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R28"
			);
			break;
		case R30:
			/*** Regla 30 t28 ***/
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SHIVA
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.SUBTIPO,
					Arrays.asList(
						new Object[] {
							TipoCreditoEnum.EFECTIVO,
							TipoCreditoEnum.CHEQUE,
							TipoCreditoEnum.CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE,
							TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF,
							TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO,
				Arrays.asList(
					new Object[] {
						EstadoOrigenEnum.BOLETA_PENDIENTE_DE_CONCILIACION
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MOTIVO,
					Arrays.asList(
						new Object[] {
							MotivoShivaEnum.CONCURSOS_Y_QUIEBRA,
							MotivoShivaEnum.POSTBAJA,
							MotivoShivaEnum.COBRANZA_REGULAR,
							MotivoShivaEnum.CHEQUE_RECHAZADO,
							MotivoShivaEnum.PAGO_A_CUENTA,
							MotivoShivaEnum.COBRO_PLAN_DE_FINACIACION,
							MotivoShivaEnum.CHEQUE_DE_GARANTIA_CORRIENTE,
							RestriccionCampo.NULL
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				//TODO Cambio la definicion de sprint 5. En un futuro se va volver a usar
				//SemaforoGestionabilidadEnum.AMARILLO,
				"Si",
				"R30",
				null,
				new Object[] {
					ErroresCreditoEnum.E_8212
				},
				null
			);
			break;
		// SAP!!!!!!!!!
		
		case R40://44 del excel //  Origen = SAP | ¿El documento está asociado a un proveedor inhabilitado? = Si  => Semaforo Rojo
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.PROVEEDOR_INHABILITADO,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R40 - 44 del exc el",
				new Object[] {
					ErroresDocumentosCapEnum.E_4501,
					ErroresDocumentosCapEnum.E_4502,
					ErroresDocumentosCapEnum.E_4503,
					ErroresDocumentosCapEnum.E_4504,
					ErroresDocumentosCapEnum.E_4505,
					ErroresDocumentosCapEnum.E_4506,
					ErroresDocumentosCapEnum.E_4507,
					ErroresDocumentosCapEnum.E_4508,
					ErroresDocumentosCapEnum.E_4509,
					ErroresDocumentosCapEnum.E_4510
				}
			);
//			default:
			break;
		case R40_1: // 43 // Origen = Sap | Empresa = 3200 => Semaforo Naranja
			regla.put(CamposReglasGestionabilidadEnum.SOCIEDAD, 
				Arrays.asList(
					new Object[] {
						CodigoSociedadSapEnum._3200.getCodigo()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
					regla, 
					SemaforoGestionabilidadEnum.NARANJA, 
					"No", 
					"Todo documento perteneciente a la sociedad 3200, no podrá ser gestionado. Semáforo: Naranja. Gestionabilidad de documentos.xlsx",
					new Object[] {
							ErroresDocumentosCapEnum.E_3200
							
					});
			break;
		case R41: //38 del exel // Origen = Sap | Tipo = DQ - Liq a Pagar WS ,K- - Rendición G Menores, .... K¡ - ND_Dif. de Cambio SA - Doc. Contable Manual  => Semaforo = rojo
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO_SAP, 
				Arrays.asList(
					new Object[] {
						TipoDocumentoCapEnum.DQ,
						TipoDocumentoCapEnum.K$, //("K$", "Compensación SHIVA"),
						TipoDocumentoCapEnum.AD, // ("K-", "Rendición G Menores"),
						TipoDocumentoCapEnum.K0, // ("K0", "Antic. de gastos FF"),
						TipoDocumentoCapEnum.K1, // ("K1", "Docum Financieros"),
						TipoDocumentoCapEnum.K3, // ("K3", "Rendición Fondo Fijo"),
						TipoDocumentoCapEnum.K4, // ("K4", "Tasas Impuestos IMP"),
						TipoDocumentoCapEnum.K5, // ("K5", "Tasas Impuestos CAP"),
						TipoDocumentoCapEnum.K6, // ("K6", "Comision recaudacion"),
						TipoDocumentoCapEnum.K7, // ("K7", "Doc. anticipos Comex"),
						TipoDocumentoCapEnum.K8, // ("K8", "Liquido Producto"),
						TipoDocumentoCapEnum.K9, // ("K9", "Recarga T. Monedero"),
						TipoDocumentoCapEnum.KA, // ("KA", "Doc.Anul.Fact.Prov."),
						TipoDocumentoCapEnum.KE, // ("KE", "Factura Prov Exterio"),
						TipoDocumentoCapEnum.KF, // ("KF", "N Crédito Prov Exter"),
						TipoDocumentoCapEnum.KG, // ("KG", "N Débito Prov Exteri"),
						TipoDocumentoCapEnum.KH, // ("KH", "Factura Serv Public"),
						TipoDocumentoCapEnum.KI, // ("KI", "N Crédito Serv Publi"),
						TipoDocumentoCapEnum.KJ, // ("KJ", "Fact. de Telecentros"),
						TipoDocumentoCapEnum.KO, // ("KO", "Despacho Aduana"),
						TipoDocumentoCapEnum.KP, // ("KP", "Otr Comprob Serv Ext"),
						TipoDocumentoCapEnum.KS, // ("KS", "Pago PM o Compensac"),
						TipoDocumentoCapEnum.KU, // ("KU", "Provisiones acumulab"),
						TipoDocumentoCapEnum.KV, // ("KV", "Provis c/OC p/Gasto"),
						TipoDocumentoCapEnum.KW, // ("KW", "Provis c/OC p/FI-AA"),
						TipoDocumentoCapEnum.KX, // ("KX", "Provisiones sin OC"),
						TipoDocumentoCapEnum.KY, // ("KY", "Comisiones bancarias"),
						TipoDocumentoCapEnum.EX, // ("K!", "NC_Dif. de Cambio"),
						TipoDocumentoCapEnum.EQ, // ("K=", "FC_Dif. de Cambio"),
						TipoDocumentoCapEnum.QU, // ("K?", "Dif. de Cambio"),
						TipoDocumentoCapEnum.KZ, // ("KZ", "Pago a Proveedor"),
						TipoDocumentoCapEnum.XE, // ("K¡", "ND_Dif. de Cambio");
						TipoDocumentoCapEnum.SA,
						TipoDocumentoCapEnum.AB,
						TipoDocumentoCapEnum.CC,
						TipoDocumentoCapEnum.CD,
						TipoDocumentoCapEnum.CE,
						TipoDocumentoCapEnum.CF,
						TipoDocumentoCapEnum.CI,
						TipoDocumentoCapEnum.DJ,
						TipoDocumentoCapEnum.HR,
						TipoDocumentoCapEnum.HS,
						TipoDocumentoCapEnum.DT, //(K.)
						TipoDocumentoCapEnum.MS, //(K+)
						TipoDocumentoCapEnum.N1,
						TipoDocumentoCapEnum.N2,
						TipoDocumentoCapEnum.N3,
						TipoDocumentoCapEnum.N4,
						TipoDocumentoCapEnum.N5,
						TipoDocumentoCapEnum.N6,
						TipoDocumentoCapEnum.N7,
						TipoDocumentoCapEnum.N8,
						TipoDocumentoCapEnum.P1,
						TipoDocumentoCapEnum.P7,
						TipoDocumentoCapEnum.PE,
						TipoDocumentoCapEnum.PJ,
						TipoDocumentoCapEnum.PK,
						TipoDocumentoCapEnum.PL,
						TipoDocumentoCapEnum.PY,
						TipoDocumentoCapEnum.YP,
						TipoDocumentoCapEnum.YQ,
						TipoDocumentoCapEnum.ZA,
						TipoDocumentoCapEnum.ZP
						
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R41 - 38 del execl",
				new Object[] {
					ErroresDocumentosCapEnum.E_4301,
					ErroresDocumentosCapEnum.E_4302,
					ErroresDocumentosCapEnum.E_4303,
					ErroresDocumentosCapEnum.E_4304,
					ErroresDocumentosCapEnum.E_4305,
					ErroresDocumentosCapEnum.E_4306,
					ErroresDocumentosCapEnum.E_4307,
					ErroresDocumentosCapEnum.E_4308,
					ErroresDocumentosCapEnum.E_4309,
					ErroresDocumentosCapEnum.E_4310,
					
					ErroresDocumentosCapEnum.E_4311,
					ErroresDocumentosCapEnum.E_4312,
					ErroresDocumentosCapEnum.E_4313,
					ErroresDocumentosCapEnum.E_4314,
					ErroresDocumentosCapEnum.E_4315,
					ErroresDocumentosCapEnum.E_4316,
					ErroresDocumentosCapEnum.E_4317,
					ErroresDocumentosCapEnum.E_4318,
					ErroresDocumentosCapEnum.E_4319,
					ErroresDocumentosCapEnum.E_4320,
					ErroresDocumentosCapEnum.E_4321,
					ErroresDocumentosCapEnum.E_4322,
					ErroresDocumentosCapEnum.E_4323,
					ErroresDocumentosCapEnum.E_4324,
					ErroresDocumentosCapEnum.E_4325,
					ErroresDocumentosCapEnum.E_4326,
					ErroresDocumentosCapEnum.E_4327,
					ErroresDocumentosCapEnum.E_4328,
					ErroresDocumentosCapEnum.E_4329,
					ErroresDocumentosCapEnum.E_4331,
					ErroresDocumentosCapEnum.E_4332,
					ErroresDocumentosCapEnum.E_4333,
					ErroresDocumentosCapEnum.E_4334,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapAB,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapCC,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapCD,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapCE,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapCF,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapCI,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapDJ,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapHR,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapHS,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapDT,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapMS,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN1,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN2,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN3,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN4,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN5,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN6,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN7,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapN8,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapP1,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapP7,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapPE,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapPJ,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapPK,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapPL,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapPY,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapYP,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapYQ,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapZA,
					ErroresDocumentosCapEnum.E_tipoDocumentoCapZP
				}
			);
			break;	
		case R42://39 del excel // Origen = Sap | ¿El documento es preliminar? = Si  => Semaforo = rojo
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ES_DOCUMENTO_PRELIMINAR,//OK
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R42 - 39 del excel",
				new Object[] {
						ErroresDocumentosCapEnum.E_4401
					}
			);
			break;	
		case R43:	//40 // Origen = Sap | Estado = (blanco) - Autorizado el pago # - Preliminar S/ Aprob. 0 - Bloqueos Combinados 1 - Créd retenido temp. 2 - Embargo judicial 3 - Ret RG 726 AFIP 4 - Completar datos fisc 5 - RBA  Pago bloqueado 6 - FC Apócrifas 7 - Adeuda Dif. Cambio < - Rechazado Imp.ddeCAP > - Aprobado Imp.dde.CAP B - Probl.usuario requir C - Probl. proveedor D - Bloqueo no modificab E - Factura cedida G - Docum compensable Q - Análisis Impuestos R - Bloqueo de Logistica Y - Bloqueo por división

			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_BLOQUEO_SAP, //ok
				Arrays.asList(
					new Object[] {
						EstadoBloqueoSapEnum.BLANCO,
						EstadoBloqueoSapEnum.NUMERAL,
						//EstadoBloqueoSapEnum.PESOS,
						EstadoBloqueoSapEnum._0,
						EstadoBloqueoSapEnum._1,
						EstadoBloqueoSapEnum._2,
						EstadoBloqueoSapEnum._3,
						EstadoBloqueoSapEnum._4,
						EstadoBloqueoSapEnum._5,
						EstadoBloqueoSapEnum._6,
						EstadoBloqueoSapEnum._7,
						EstadoBloqueoSapEnum._8,
						EstadoBloqueoSapEnum._9,
						EstadoBloqueoSapEnum.INTERR,
						EstadoBloqueoSapEnum.IGUAL,
						EstadoBloqueoSapEnum.EXCLAMA,
						EstadoBloqueoSapEnum.MAS,
						EstadoBloqueoSapEnum.MENOR,
						EstadoBloqueoSapEnum.MAYOR,
						EstadoBloqueoSapEnum.B,
						EstadoBloqueoSapEnum.C,
						EstadoBloqueoSapEnum.D,
						EstadoBloqueoSapEnum.E,
						EstadoBloqueoSapEnum.G,
						EstadoBloqueoSapEnum.Q,
						EstadoBloqueoSapEnum.R,
						EstadoBloqueoSapEnum.Y
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R43 / 40 del excel",
				new Object[] {
					ErroresDocumentosCapEnum.E_4201,
					ErroresDocumentosCapEnum.E_4202,
					ErroresDocumentosCapEnum.E_4203,
					ErroresDocumentosCapEnum.E_4204,
					ErroresDocumentosCapEnum.E_4205,
					ErroresDocumentosCapEnum.E_4206,
					ErroresDocumentosCapEnum.E_4207,
					ErroresDocumentosCapEnum.E_4208,
					ErroresDocumentosCapEnum.E_4209,
					ErroresDocumentosCapEnum.E_4210,
					ErroresDocumentosCapEnum.E_4211,
					ErroresDocumentosCapEnum.E_estadoBloqueoSap8,
					ErroresDocumentosCapEnum.E_estadoBloqueoSap9,
					ErroresDocumentosCapEnum.E_estadoBloqueoSapInterr,
					ErroresDocumentosCapEnum.E_estadoBloqueoSapIgual,
					ErroresDocumentosCapEnum.E_estadoBloqueoSapExclama,
					ErroresDocumentosCapEnum.E_estadoBloqueoSapMas,
					ErroresDocumentosCapEnum.E_4212,
					ErroresDocumentosCapEnum.E_4213,
					ErroresDocumentosCapEnum.E_4214,
					ErroresDocumentosCapEnum.E_4215,
					ErroresDocumentosCapEnum.E_4216,
					ErroresDocumentosCapEnum.E_4217,
					ErroresDocumentosCapEnum.E_4218,
					ErroresDocumentosCapEnum.E_4219,
					ErroresDocumentosCapEnum.E_4220,
					ErroresDocumentosCapEnum.E_4221

				}
			);
			break;	
		case R44://41 del excel | Origen = cap | ¿El documento está incluido en otra operación SHIVA en estado pendiente de reversión? = si => Semanforo rojo
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_REVERSION_PROCESO_PENDIENTE,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.ROJO,
				"No",
				"R44 - 41 del exc el",
				new Object[] {
					ErroresDocumentosCapEnum.E_4431,
				}
			);
			break;
// NARANJA
		case R45:	// 42 | Origen = sap | estado = A - Estado inicial H - Aprobación Interm. I - Rechazo CAP (SSGG) J - Aprobación imposit. K - Aprobación CAP L - Recepción Tesorería M - Devol. de Tesorería N - Rechazo vinculadas O - Rechazo impositivo P - Rechazo superv CAP S - Bloqueo Corresp. T - Aprobacion Corresp. U - Ret.Insc.INPI/DNDA V - Doc. Contabilizado W - Bloqueo Wholesale X - Analisis Imp.dde.CAP Z - Aprobacion Wholesale => Semaforo = Naranaja
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(CamposReglasGestionabilidadEnum.SOCIEDAD, 
				Arrays.asList(
					new Object[] {
						CodigoSociedadSapEnum.M650.getCodigo(),
						CodigoSociedadSapEnum.M660.getCodigo()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_BLOQUEO_SAP, //Ok
				Arrays.asList(
					new Object[] {
							EstadoBloqueoSapEnum.A,
							EstadoBloqueoSapEnum.H,
							EstadoBloqueoSapEnum.I,
							EstadoBloqueoSapEnum.J,
							EstadoBloqueoSapEnum.K,
							EstadoBloqueoSapEnum.L,
							EstadoBloqueoSapEnum.M,
							EstadoBloqueoSapEnum.N,
							EstadoBloqueoSapEnum.O,
							EstadoBloqueoSapEnum.P,
							EstadoBloqueoSapEnum.S,
							EstadoBloqueoSapEnum.T,
							EstadoBloqueoSapEnum.U,
							EstadoBloqueoSapEnum.V,
							EstadoBloqueoSapEnum.W,
							EstadoBloqueoSapEnum.X,
							EstadoBloqueoSapEnum.Z
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.NARANJA,
				"No",
				"R45 / 42 del excel",
				new Object[] {
						ErroresDocumentosCapEnum.E_4101,
						ErroresDocumentosCapEnum.E_4102,
						ErroresDocumentosCapEnum.E_4103,
						ErroresDocumentosCapEnum.E_4104,
						ErroresDocumentosCapEnum.E_4105,
						ErroresDocumentosCapEnum.E_4106,
						ErroresDocumentosCapEnum.E_4107,
						ErroresDocumentosCapEnum.E_4108,
						ErroresDocumentosCapEnum.E_4109,
						ErroresDocumentosCapEnum.E_4110,
						ErroresDocumentosCapEnum.E_4111,
						ErroresDocumentosCapEnum.E_4112,
						ErroresDocumentosCapEnum.E_4113,
						ErroresDocumentosCapEnum.E_4114,
						ErroresDocumentosCapEnum.E_4115,
						ErroresDocumentosCapEnum.E_4116,
						ErroresDocumentosCapEnum.E_4117
					}
			);
			break;
		case R46:	//43 en el excel | Origen = Sap | Informa padre inexistente = Si => Naranja
			regla.put(
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(CamposReglasGestionabilidadEnum.SOCIEDAD, 
				Arrays.asList(
					new Object[] {
						CodigoSociedadSapEnum.M650.getCodigo(),
						CodigoSociedadSapEnum.M660.getCodigo()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.INFORMA_PADRE_INEXISTENTE, //OK
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.NARANJA,
				"No",
				"R46 / 43 del execl",
				new Object[] {
					ErroresDocumentosCapEnum.E_4601
				}
			);
			break;
		case R47:// 44 del excel // Origen = Sap | Tipo = DP - K2 - KB - KC - KD - KK - KL - KM - KN - KQ - KR - KT | Estado = F - Pdte compensación | ¿El item NO Shiva está incluido en otra operación de cobro SHIVA en estado pendiente de imputar cobro, cobro en proceso, pendiente MIC, pendiente procesar respuesta MIC o pendiente de confirmación manual? = No | ¿El documento está incluido en otra operación de cobro SHIVA en estado en edición, en edición verificando débitos, pendiente referente de cobranza, pendiente de aprobador de operaciones especiales o cobro rechazado? = No | ¿El item NO Shiva está incluido en otra operación de reversión de cobro SHIVA en estado pendiente de imputar descobro, descobro en proceso, descobro pendiente MIC, descobro pendiente procesar respuesta MIC, descobro en error,  error en primer descobro o descobro pendiente de confirmación manual? = No | ¿El documento está incluido en otra operación de reversión de cobro SHIVA en estado en edición? = NO 
			regla.put(		//¿El documento está asociado a un proveedor inhabilitado? = No - BLANCO | ¿El documento es preliminar? = No | ¿El documento está incluido en otra operación SHIVA en estado pendiente de reversión? = No | Informa padre inexistente = No
				CamposReglasGestionabilidadEnum.ORIGEN,
				Arrays.asList(
					new Object[] {
						SistemaEnum.SAP
			}));
			regla.put(CamposReglasGestionabilidadEnum.SOCIEDAD, 
				Arrays.asList(
					new Object[] {
						CodigoSociedadSapEnum.M650.getCodigo(),
						CodigoSociedadSapEnum.M660.getCodigo()
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.TIPO_SAP,//Ok
				Arrays.asList(
					new Object[] {
						TipoDocumentoCapEnum.DP,
						TipoDocumentoCapEnum.K2,
						TipoDocumentoCapEnum.KB,
						TipoDocumentoCapEnum.KC,
						TipoDocumentoCapEnum.KD,
						TipoDocumentoCapEnum.KK,
						TipoDocumentoCapEnum.KL,
						TipoDocumentoCapEnum.KM,
						TipoDocumentoCapEnum.KN,
						TipoDocumentoCapEnum.KQ,
						TipoDocumentoCapEnum.KR,
						TipoDocumentoCapEnum.KT
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ESTADO_BLOQUEO_SAP, //Ok
				Arrays.asList(
					new Object[] {
						EstadoBloqueoSapEnum.F
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_PAGO_COMPENSACION_PROCESO_SHIVA, //Ok
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA,// OK
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.PROVEEDOR_INHABILITADO,// OK
					Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.ES_DOCUMENTO_PRELIMINAR,//Ok
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.MARCA_REVERSION_PROCESO_PENDIENTE,//Ok
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.INFORMA_PADRE_INEXISTENTE, //Ok
				Arrays.asList(
					new Object[] {
						SiNoEnum.NO
			}));
			regla.put(
				CamposReglasGestionabilidadEnum.COBRAR_DEUDA,
				Arrays.asList(
					new Object[] {
						SiNoEnum.SI
			}));
			regla = this.agregarRetorno(
				regla,
				SemaforoGestionabilidadEnum.VERDE,
				"Si",
				"R40--44 del excel",
				null,
				null,
				null
			);
			break;
		// Gestionabilidad SAP

		}
		return regla;
	}
	// Crea
	protected GestionabilidadDto retornaGestionabildadNoProcesada() {
		GestionabilidadDto semaforo = new GestionabilidadDto(
			"No",
			SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
			MSG_SEMAFORO_NO_GESTIONABLE
		);
		return semaforo;
	}
	
	protected List<CamposReglasGestionabilidadEnum> getNombreCamposResultado() {
		return nombreCamposResultado;
	}
	protected void setNombreCamposResultado(
			List<CamposReglasGestionabilidadEnum> nombreCamposResultado) {
		this.nombreCamposResultado = nombreCamposResultado;
	}
	protected Comparator<PostReglaEnum> getComparador() {
		return comparador;
	}
	protected void setComparador(Comparator<PostReglaEnum> comparador) {
		this.comparador = comparador;
	}
	protected List<PostReglaEnum> getListaReglas() {
		return listaReglas;
	}
	protected void setListaReglas(List<PostReglaEnum> listaReglas) {
		this.listaReglas = listaReglas;
	}
	public static final String GETTER = "get";
	public static final String MSG_SEMAFORO_NO_CON_CUMPLE_NINGUNA_REGLA = "No se cumplio ninguna regla.";
	public static final String MSG_SEMAFORO_NO_GESTIONABLE = "No se pudo determinar la gestionabilidad  del debito o crédito por falta de datos.";
}