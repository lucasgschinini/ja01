package ar.com.telecom.shiva.base.enumeradores;

public enum TipoClienteTagetikEnum {

	_1A ("1A", "UNRE OGE F.E"),
	_1B ("1B", "UNRE OTP T.R"),
	_11 ("11", "UNRE RESIDEN"),
	_12 ("12", "UNRE PRO/COM"),
	_13 ("13", "UNRE PROF"),
	_14 ("14", "UNRE CT"),
	_15 ("15", "UNRE OYP"),
	_16 ("16", "UNRE OGE L.E"),
	_17 ("17", "UNRE TPU/OTP"),
	_18 ("18", "UNRE LI.ESP."),
	_19 ("19", "UNRE OGE T.R"),
	_21 ("21", "UNGC GOBIERN"),
	_22 ("22", "UNGC G/CLIEM"),
	_23 ("23", "OP/PRES"),
	_24 ("24", "O/P RD Y TV"),
	_25 ("25", "O/P COOPERT"),
	_26 ("26", "UNGC OGE"),
	_27 ("27", "UNGC R1"),
	_28 ("28", "UNGC LI.ESP."),
	_29 ("29", "UNGC OGE LE"),
	_30 ("30", "WN.OP FIJOS"),
	_31 ("31", "WN.OP FIJOS"),
	_32 ("32", "WN.OP MOVIL"),
	_33 ("33", "WN.OP MOVIL"),
	_34 ("34", "WN.TECO PERS"),
	_35 ("35", "WN.PRESTADOR"),
	_36 ("36", "WN.PRESTADOR"),
	_37 ("37", "WN.PRES/ISPS"),
	_38 ("38", "WN.T/PUB 3RO"),
	_39 ("39", "WN.OP FIJOS"),
	_40 ("40", "WN.R Y TV"),
	_41 ("41", "WN.COOPERAT"),
	_42 ("42", "WN.TECO PERS"),
	_43 ("43", "WN.COOP ELEC"),
	_44 ("44", "WN.COOP ELEC"),
	_45 ("45", "WN.COOP ELEC"),
	_46 ("46", "WN.OP FIJOS"),
	_47 ("47", "WN.CLI GEST");

	String codigo;
	String descripcion;
	
	private TipoClienteTagetikEnum(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}
	public String codigo() {
		return this.codigo;
	}
	public static TipoClienteTagetikEnum getEnumByName(String name) {
		
		for (TipoClienteTagetikEnum tipoDoc : TipoClienteTagetikEnum.values()) {
			if (tipoDoc.name().equalsIgnoreCase(name)) {
				return tipoDoc;
			}
		}
		return null; 
	}

	public static TipoClienteTagetikEnum getEnumByCodigo(String codigo) {
		for (TipoClienteTagetikEnum tipoCliente : TipoClienteTagetikEnum.values()) {
			if (tipoCliente.codigo().equals(codigo)) {
				return tipoCliente;
			}
		}
		return null;
	}
}
