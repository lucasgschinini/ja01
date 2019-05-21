package ar.com.telecom.shiva.base.enumeradores;


public enum TipoReintegroEnum {
	
	PF("Proxima factura"),
	PCT("Pago cuenta terceros"),
	CH("Cheque"),
	RI("Red inteligente"),
	CBU("Transferencia bancaria");
	
	String descripcion;
	
	private TipoReintegroEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return this.descripcion;
	}
		
	public static TipoReintegroEnum getEnumByName(String name){
		for (TipoReintegroEnum enumerador : TipoReintegroEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoReintegroEnum getEnumByDescripcion(String descripcion){
		for (TipoReintegroEnum enumerador : TipoReintegroEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}
