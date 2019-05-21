package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoBloqueoSapEnum {
	// ROJO
	BLANCO(" ", "Autorizado el pago"),
	NUMERAL("#", "Preliminar S/ Aprob."),
	PESOS("$", "Pend. comp. SHIVA"),
	_0("0", "Bloqueos Combinados"),
	_1("1", "Créd retenido temp."),
	_2("2", "Embargo judicial"),
	_3("3", "Ret RG 726 AFIP"),
	_4("4", "Completar datos fisc"),
	_5("5", "RBA  Pago bloqueado"),
	_6("6", "FC Apócrifas"),
	_7("7", "Adeuda Dif. Cambio"),
	// ROJO TELECOM
	_8("8", "Desb.Cert.de Ret"),
	_9("9", "Probl. proveedor"),
	
	INTERR("?", "A la espera de NC"),
	IGUAL("=", "Retencion cobranza"),
	EXCLAMA("!", "Canje"),
	MAS("+", "Carta Ya Presentada"),

	MENOR("<", "echazado Imp.ddeCAP"),
	MAYOR(">", "Aprobado Imp.dde.CAP"),
	
	B("B", "Probl.usuario requir"),
	C("C", "Probl. proveedor"),
	D("D", "Bloqueo no modificab"),
	E("E", "Factura cedida"),
	G("G", "Docum compensable"),
	Q("Q", "Análisis Impuestos"),
	R("R", "Bloqueo de Logistica"),
	Y("Y", "Bloqueo por división"),
	// NARANJ
	F("F", "Pdte compensación"),
	A("A", "Estado inicial"),
	H("H", "Aprobación Interm."),
	I("I", "Rechazo CAP (SSGG)"),
	J("J", "Aprobación imposit."),
	K("K", "Aprobación CAP"),
	L("L", "Recepción Tesorería"),
	M("M", "Devol. de Tesorería"),
	N("N", "Rechazo vinculadas"),
	O("O", "Rechazo impositivo"),
	P("P", "Rechazo superv CAP"),
	S("S", "Bloqueo Corresp."),
	T("T", "Aprobacion Corresp."),
	U("U", "Ret.Insc.INPI/DNDA"),
	V("V", "Doc. Contabilizado"),
	W("W", "Bloqueo Wholesale"),
	X("X", "Analisis Imp.dde.CAP"),
	Z("Z", "Aprobacion Wholesale");

	private String descripcion;
	private String codigo;
	
	private EstadoBloqueoSapEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static EstadoBloqueoSapEnum getEnumByName(String name) {
		for (EstadoBloqueoSapEnum estado : EstadoBloqueoSapEnum.values()) {
			if (estado.name().equalsIgnoreCase(name)) {
				return estado;
			}
		}
		return null;
	}
	public static EstadoBloqueoSapEnum getEnumByCodigo(String codigo) {
		for (EstadoBloqueoSapEnum estado : EstadoBloqueoSapEnum.values()) {
			if (estado.getCodigo().equalsIgnoreCase(codigo)) {
				return estado;
			}
		}
		return null;
	}
}
