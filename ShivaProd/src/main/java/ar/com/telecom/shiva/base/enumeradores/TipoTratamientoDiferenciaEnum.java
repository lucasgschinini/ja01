package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;


public enum TipoTratamientoDiferenciaEnum {
	
	// Diferencia < 0 (cr�dito para el cliente)
	ENVIO_A_GANANCIAS("14", "Env�o a Ganancias", ""),
	REINTEGRO_PAGO_CUENTA_TERCEROS("15", "Reintegro Pago Cuenta Terceros", "CT"),
	REINTEGRO_POR_CHEQUE("26", "Reintegro por Cheque", "CH"),
	REINTEGRO_CREDITO_RED_INTEL("27", "Reintegro Cr�dito Red Inteligente", "RI"),
	REINTEGRO_TRANSFERENCIA_BAN("30", "Reintegro Transferencia Bancaria", "TR"),
	REINTEGRO_CRED_PROX_FAC_CLP("31", "Cr�dito Pr�xima Factura", "PF"),
	REINTEGRO_CRED_PROX_FAC_MIC("32", "Cr�dito Pr�xima Factura", "PF"),
	// Compensaciones SAP, tratamientos especiales
	SALDO_A_PAGAR("42", "Saldo a Pagar", "SP"),
	
	// Diferencia > 0 (d�bito para el cliente)
	DEBITO_PROX_FAC_CLP("28", "D�bito Pr�xima Factura", "PF"),
	DEBITO_PROX_FAC_MIC("29", "D�bito Pr�xima Factura", "PF"),
	// Compensaciones SAP, tratamientos especiales
	SALDO_A_COBRAR("43", "Saldo a Cobrar", "SC"),
	
	// Exclusivos para el combo de tratamiento de diferencia donde no puedo diferenciar entre MIC y Calipso
	// los codigos 37 y 38 corresponden a medios de pago ficticios
	REINTEGRO_CRED_PROX_FAC("37", "Cr�dito Pr�xima Factura", "PF"),
	DEBITO_PROX_FAC("38", "D�bito Pr�xima Factura", "PF"),
	
	//Nuevos Contingencia Fusi�n
	APLICACION_MANUAL_NEGOCIO_NET("44","Aplicaci�n Manual (Negocio.Net)",""),
	APLICACION_MANUAL_SAP("45","Aplicaci�n Manual (Sap)",""),
	APLICACION_MANUAL("46","Aplicaci�n Manual","APM"),
	APLICACION_MANUAL_CABLEVISION("47","Aplicaci�n Manual (Cablevisi�n)",""),
	APLICACION_MANUAL_FIBERTEL("48","Aplicaci�n Manual (Fibertel)",""),
	APLICACION_MANUAL_NEXTEL("49","Aplicaci�n Manual (Nextel)","");
	
	String idTipoMedioPagoAsociado;
	String descripcion;
	String subTipoDocumento;
	
	/**
	 * 
	 * @param idTipoMedioPagoAsociado
	 * @param descripcion
	 * @param subTipoDocumento
	 */
	private TipoTratamientoDiferenciaEnum(String idTipoMedioPagoAsociado, String descripcion, String subTipoDocumento) {
		this.idTipoMedioPagoAsociado = idTipoMedioPagoAsociado;
		this.descripcion = descripcion;
		this.subTipoDocumento = subTipoDocumento;
	}

	/**
	 * Devuelve el enumerador segun el codigo
	 * @param name
	 * @return
	 */
	public static TipoTratamientoDiferenciaEnum getEnumByName(String name){
		
		TipoTratamientoDiferenciaEnum retorno = null;
		
		if (ENVIO_A_GANANCIAS.name().equalsIgnoreCase(name)) {
			retorno = ENVIO_A_GANANCIAS; 
		} else 
			if (REINTEGRO_PAGO_CUENTA_TERCEROS.name().equalsIgnoreCase(name)) {
				retorno = REINTEGRO_PAGO_CUENTA_TERCEROS; 
			} else 
				if (REINTEGRO_POR_CHEQUE.name().equalsIgnoreCase(name)) {
					retorno = REINTEGRO_POR_CHEQUE; 
				} else 
					if (REINTEGRO_CREDITO_RED_INTEL.name().equalsIgnoreCase(name)) {
						retorno = REINTEGRO_CREDITO_RED_INTEL; 
					} else 
						if (REINTEGRO_TRANSFERENCIA_BAN.name().equalsIgnoreCase(name)) {
							retorno = REINTEGRO_TRANSFERENCIA_BAN; 
						} else 
							if (REINTEGRO_CRED_PROX_FAC_CLP.name().equalsIgnoreCase(name)) {
								retorno = REINTEGRO_CRED_PROX_FAC_CLP; 
							} else 
								if (REINTEGRO_CRED_PROX_FAC_MIC.name().equalsIgnoreCase(name)) {
									retorno = REINTEGRO_CRED_PROX_FAC_MIC; 
								} else 
									if (DEBITO_PROX_FAC_CLP.name().equalsIgnoreCase(name)) {
										retorno = DEBITO_PROX_FAC_CLP; 
									} else 
										if (DEBITO_PROX_FAC_MIC.name().equalsIgnoreCase(name)) {
											retorno = DEBITO_PROX_FAC_MIC; 
										} else 
											if (REINTEGRO_CRED_PROX_FAC.name().equalsIgnoreCase(name)) {
												retorno = REINTEGRO_CRED_PROX_FAC; 
											} else 
												if (DEBITO_PROX_FAC.name().equalsIgnoreCase(name)) {
													retorno = DEBITO_PROX_FAC; 
												} else 
													if (SALDO_A_PAGAR.name().equalsIgnoreCase(name)) {
														retorno = SALDO_A_PAGAR; 
													} else 
														if (SALDO_A_COBRAR.name().equalsIgnoreCase(name)) {
															retorno = SALDO_A_COBRAR; 
														} else 
															if (APLICACION_MANUAL.name().equalsIgnoreCase(name)) {
																retorno = APLICACION_MANUAL; 
															} else 
																if (APLICACION_MANUAL_NEGOCIO_NET.name().equalsIgnoreCase(name)) {
																	retorno = APLICACION_MANUAL_NEGOCIO_NET; 
																} else 
																	if (APLICACION_MANUAL_SAP.name().equalsIgnoreCase(name)) {
																		retorno = APLICACION_MANUAL_SAP; 
																	} else 
																		if (APLICACION_MANUAL_CABLEVISION.name().equalsIgnoreCase(name)) {
																			retorno = APLICACION_MANUAL_CABLEVISION; 
																		} else 
																			if (APLICACION_MANUAL_FIBERTEL.name().equalsIgnoreCase(name)) {
																				retorno = APLICACION_MANUAL_FIBERTEL; 
																			} else 
																				if (APLICACION_MANUAL_NEXTEL.name().equalsIgnoreCase(name)) {
																					retorno = APLICACION_MANUAL_NEXTEL; 
																				} 
		
		return retorno;
	}
	
	/**
	 * @return the idTipoMedioPagoAsociado
	 */
	public String getIdTipoMedioPagoAsociado() {
		return idTipoMedioPagoAsociado;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the subTipoDocumento
	 */
	public String getSubTipoDocumento() {
		return subTipoDocumento;
	}

	/**
	 * Dado un Id de Tipo de medio de pago, retorna el Enumerador asociado
	 * 
	 * @param idTipoMedioPagoAsociado
	 * @return
	 */
	public static TipoTratamientoDiferenciaEnum getEnumByIdtipoMedioPagoAsociado(String idTipoMedioPagoAsociado) {
		for (TipoTratamientoDiferenciaEnum item : TipoTratamientoDiferenciaEnum.values()) {
			if (item.getIdTipoMedioPagoAsociado().equalsIgnoreCase(idTipoMedioPagoAsociado)) {
				return item;
			}
		}
		return null;
	}
	
	public static TipoTratamientoDiferenciaEnum getEnumByDescripcion(String descripcion) {
		for (TipoTratamientoDiferenciaEnum item : TipoTratamientoDiferenciaEnum.values()) {
			if (item.getDescripcion().equalsIgnoreCase(descripcion)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Retorna la lista de posibles valores para utilizar al momento de tratar una diferencia de debitos
	 * 
	 * @return
	 */
	public static List<TipoTratamientoDiferenciaEnum> getEnumComboTratamientoDiferenciaDebito() {
		
		List<TipoTratamientoDiferenciaEnum> lista = new ArrayList<TipoTratamientoDiferenciaEnum>();
		lista.add(TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC);
		lista.add(TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR);
		
		return lista;
	}
	
	/**
	 * Retorna la lista de posibles valores para utilizar al momento de tratar una diferencia de cr�ditos
	 * 
	 * @return
	 */
	public static List<TipoTratamientoDiferenciaEnum> getEnumComboTratamientoDiferenciaCredito() {
		
		List<TipoTratamientoDiferenciaEnum> lista = new ArrayList<TipoTratamientoDiferenciaEnum>();
		lista.add(TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS);
		lista.add(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC);
		lista.add(TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE);
		lista.add(TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS);
		lista.add(TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL);
		lista.add(TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN);
		lista.add(TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR);
		lista.add(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL);


		return lista;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
	    return this.name();
	}
	public static TipoTratamientoDiferenciaEnum getEnumBySubTipo(String subTipo) {
		for (TipoTratamientoDiferenciaEnum item : TipoTratamientoDiferenciaEnum.values()) {
			if (item.getSubTipoDocumento().equalsIgnoreCase(subTipo)) {
				return item;
			}
		}
		return null;
	}

	public static boolean esTipoAplicacionManual(TipoTratamientoDiferenciaEnum enumerador) {
		if (
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(enumerador) ||
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(enumerador) ||
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(enumerador) ||
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(enumerador) ||
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(enumerador) ||
			TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(enumerador)
		) {
			return true;
		}
		return false;
	}
}
