package ar.com.telecom.shiva.base.enumeradores;


public enum TipoMedioPagoEnum {
	
	// Para buscar en SHV_PARAM_TIPO_MEDIO_PAGO
	CHEQUEPROPIO("01", "CHEQUES PROPIOS", "CHQ",""),
	CHEQUEDIFERIDO("02", "CHEQUES DIFERIDOS", "CHQ",""),
	BOLETADEDEPOSITO("03", "BOLETA DE DEPOSITO", "DEP",""),
	TRANSFBANCARIA("05", "TRANSF. BANCARIA", "TRF",""),

	// RETENCIONES
	IMPMUNSEGHIG("07", "IMP. MUN. SEG. HIG", "RET", ""),
	IMPUESTOALSELLO("08", "IMPUESTO AL SELLO", "RET", ""),
	RETSEGSEC("09", "RET SEG SEC", "RET", ""),
	RETENCIONGANANCIA("10", "RETENCION GANANCIAS", "RET", ""),
	RETENCIONIVA("11", "RETENCION IVA", "RET", ""),
	RETENCIONIIBB("12", "RETENCION IIBB", "RET", ""),

	DESISTIMIENTO("16", "DESISTIMIENTO", "", ""),
	
	INTERDEPOSITO("17", "INTERDEPOSITOS", "IDEP", ""),

	REMANOACTUALIZABLE("19", "REMA NO ACTUALIZABLE", "REM", "RT"),
	REMANENTEACTUALIZADO("20", "REMANEN. ACTUALIZADO", "REM", "RT"),

	PAGO_A_CUENTA("21", "PAGO CUENTA CALIPSO", "CTA", ""),
	NOTA_CREDITO("22", "NOTA DE CREDITO", "NC", "NC"),
	PLAN_DE_PAGO("25", "PLANPAGO", "", ""),

	// RETENCIONES
	IVARG3349("23", "IVA RG3349 (PNUD)", "RET", ""),
	IMPUESTOTASAMUNICIPAL("24", "IMPUESTO TASAS MUNICIPALES", "RET", ""),
	
	COMPENSACION_OTRAS("33", "OTRAS COMPENSACIONES", "", ""),
	COMPENSACION_INTERCOMPANY("34", "COMPENSACION INTERCOMPANY", "", ""),
	COMPENSACION_LIQUIDO_PROD("35", "COMPENSACION LIQUIDO PRODUCTO", "", ""),
	COMPENSACION_IIBB("39","COMPENSACION IIBB","",""),
	COMPENSACION_CESION_CREDITOS("40","COMPENSACION CESION CREDITOS","",""),
	COMPENSACION_PROVEEDORES("41","COMPENSACION PROVEEDORES","",""),
//	COMPENSACION_INTERCOMPANY("34", "INTERCOMPANY", "", ""),
//	COMPENSACION_LIQUIDO_PROD("35", "LIQUIDO PRODUCTO", "", ""),
	ENVIO_A_GANANCIAS("14", "ENVIO A GANANCIAS", "", ""),
	REINTEGRO_PAGO_CUENTA_TERCEROS("15", "REINTEGRO PAGO CUENTA TERCEROS", "", ""),
	REINTEGRO_POR_CHEQUE("26", "REINTEGRO POR CHEQUE", "", ""),
	REINTEGRO_CREDITO_RED_INTEL("27", "REINTEGRO CREDITO RED INTELIGENTE", "", ""),
	DEBITO_PROXIMA_FACTURA_CALIPSO("28", "DEBITO PROXIMA FACTURA (CALIPSO)", "", ""),
	DEBITO_PROXIMA_FACTURA_MIC("29", "DEBITO PROXIMA FACTURA (MIC)", "", ""),
	REINTEGRO_TRANSFERENCIA_BAN("30", "REINTEGRO TRANSFERENCIA BANCARIA", "", ""),
	CREDITO_PROXIMA_FACTURA_CALIPSO("31", "CREDITO PROXIMA FACTURA (CALIPSO)", "", ""),
	CREDITO_PROXIMA_FACTURA_MIC("32", "CREDITO PROXIMA FACTURA (MIC)", "", ""),
	
	// Exclusivos para el combo de tratamiento de diferencia donde no puedo diferenciar entre MIC y Calipso
	// los codigos 37 y 38 corresponden a medios de pago ficticios
	REINTEGRO_CRED_PROX_FAC("37", "CREDITO PROXIMA FACTURA", "", ""),
	DEBITO_PROX_FAC("38", "DEBITO PROXIMA FACTURA", "", ""),
	
	// Compensaciones SAP, tratamientos especiales
	SALDO_A_PAGAR("42", "SALDO A PAGAR", "", ""),
	SALDO_A_COBRAR("43", "SALDO A COBRAR", "", ""),
	
	//Para el MQ
	RT("", "Remanente", "", "RT"),
	NC("", "Nota de Credito", "", "NC"),
	TC("", "Transferencia de Cargos", "", ""),
	
	//Para MQ de COBROS
	REM("", "Remanente", "", "RT"),
	CRE("", "Nota de Credito", "", "NC"),
	CTA("", "CTA", "", ""),
	TODOS("", "Todos", "", ""),
	
	//Nuevos Contingencia Fusión 
	APLICACION_MANUAL_PERSONAL_NEGOCIO_NET	("44", "APLICACION MANUAL (Personal - Negocio.NET)", "", ""),
	APLICACION_MANUAL_PERSONAL_SAP			("45", "APLICACION MANUAL (Personal - SAP)", "", ""),
	APLICACION_MANUAL						("46", "APLICACION MANUAL", "", ""),
	APLICACION_MANUAL_FIBERCORP_OPEN		("47", "APLICACION MANUAL (FiberCorp - Open)", "", ""),
	APLICACION_MANUAL_FIBERCORP_SAP			("48", "APLICACION MANUAL (FiberCorp - SAP)", "", ""),
	APLICACION_MANUAL_NEXTEL_NEXUS			("49", "APLICACION MANUAL (Nextel - Nexus)", "", ""),
	
	APLICACION_MANUAL_TELECOM_HUAWEI			("50", "APLICACION MANUAL (Telecom - Huawei)", "", ""),
	APLICACION_MANUAL_TELECOM_SAP				("51", "APLICACION MANUAL (Telecom - SAP)", "", ""),
	APLICACION_MANUAL_PUBLICIDAD_Y_WHOLE_SALES_OPEN	("52", "APLICACION MANUAL (Publicidad y Wholesale - Open)", "", ""),
	APLICACION_MANUAL_PUBLICIDAD_Y_WHOLE_SALES_SAP	("53", "APLICACION MANUAL (Publicidad y Wholesale - SAP)", "", "")
	;
	
	String idTipoMedioPago;
	String descripcion;
	String abreviaturaTipoMedioPago;
	String codigoGeneralTipoMedioPago;
	
	/**
	 * 
	 * @param idTipoMedioPago
	 * @param descripcion
	 */
	private TipoMedioPagoEnum(String idTipoMedioPago, String descripcion, String abreviaturaTipoMedioPago, String codigoGeneralTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
		this.descripcion = descripcion;
		this.abreviaturaTipoMedioPago = abreviaturaTipoMedioPago;
		this.codigoGeneralTipoMedioPago = codigoGeneralTipoMedioPago;
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoMedioPagoEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoMedioPagoEnum tipoMP : TipoMedioPagoEnum.values()) {
			if (tipoMP.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipoMP;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param idTipoMedioPago
	 * @return
	 */
	public static TipoMedioPagoEnum getEnumByIdTipoMedioPago(String idTipoMedioPago) {
		
		for (TipoMedioPagoEnum tipoMP : TipoMedioPagoEnum.values()) {
			if (tipoMP.getIdTipoMedioPago().equalsIgnoreCase(idTipoMedioPago)) {
				return tipoMP;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoMedioPagoEnum getEnumByName(String name) {
		
		for (TipoMedioPagoEnum campo : TipoMedioPagoEnum.values()) {
			if (campo.name().equalsIgnoreCase(name)) {
				return campo;
			}
		}
		        
		return null; 
	}

	/**
	 * @return the idTipoMedioPago
	 */
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the abreviaturaTipoMedioPago
	 */
	public String getAbreviaturaTipoMedioPago() {
		return abreviaturaTipoMedioPago;
	}
	
	/**
	 * @return the codigoGeneralTipoMedioPago
	 */
	public String getCodigoGeneralTipoMedioPago() {
		return codigoGeneralTipoMedioPago;
	}
}
