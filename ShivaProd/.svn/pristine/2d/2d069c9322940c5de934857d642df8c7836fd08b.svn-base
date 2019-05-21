package ar.com.telecom.shiva.base.enumeradores;

public enum TipoMedioPagoConfCobroEnum {
	
	//SHIVA
	CHEQUE("CHEQ", "Cheque", "2,5"),
	CHEQUEDIF("CHEQDIF", "Cheque Diferido", "3,6"),
	EFECTIVO("EFE", "Efectivo", "4,7"),
	INTERDEPOSITO("INTER", "Interdepósito", "9"),
	//
	NOTACREDITO("CRE", "Nota de Crédito", ""),
	PAGOACUENTA("CTA", "Pago a Cuenta", ""),
	REMANENTE("REM", "Remanente", ""),
	//SHIVA
	RETENCION("RET", "Retencion/Impuesto", "10"),
	TRANSFERENCIA("TRANS", "Transferencia", "8");
	
	String valor;
	String descripcion;
	String idTipoValor;
	
	private TipoMedioPagoConfCobroEnum(String valor, String descripcion, String idTipoValor) {
		this.valor = valor;
		this.descripcion = descripcion;
		this.idTipoValor = idTipoValor;
	}

	public String getValor() {
		return valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getIdTipoValor() {
		return idTipoValor;
	}

	public static TipoMedioPagoConfCobroEnum getEnumByName(String name) {

		for (TipoMedioPagoConfCobroEnum enumerador : TipoMedioPagoConfCobroEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoMedioPagoConfCobroEnum getEnumByValor(String valor) {

		for (TipoMedioPagoConfCobroEnum enumerador : TipoMedioPagoConfCobroEnum.values()) {
			if (enumerador.getValor().equalsIgnoreCase(valor)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoMedioPagoConfCobroEnum getEnumByDescripcion(String descripcion) {

		for (TipoMedioPagoConfCobroEnum enumerador : TipoMedioPagoConfCobroEnum.values()) {
			if (enumerador.getDescripcion().equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
	public static TipoMedioPagoConfCobroEnum getEnumByIdTipoValor(String idTipoValor) {

		for (TipoMedioPagoConfCobroEnum enumerador : TipoMedioPagoConfCobroEnum.values()) {
			if (enumerador.getIdTipoValor().equalsIgnoreCase(idTipoValor)) {
				return enumerador;
			}
		}
		return null;
	}
}
