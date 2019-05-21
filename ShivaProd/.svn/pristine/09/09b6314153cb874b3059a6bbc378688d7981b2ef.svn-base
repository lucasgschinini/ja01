package ar.com.telecom.shiva.base.enumeradores;

public enum TipoMedioPagoAgregarConfCobroEnum {
	
	OTRAS_COMPENSACIONES("31", "Otras Compensaciones"),
	LIQUIDOPRODUCTO("33", "Liquido Producto"),
	INTERCOMPANY("32", "Intercompany"),
	DESISTIMIENTO("16", "Desistimiento"),
	PLANPAGO("25", "Plan de Pago"),
	IIBB("39", "IIBB"),
	CESION_CREDITOS("40", "Cesión Créditos"),
	PROVEEDORES("41", "Proveedores");
//	NOTACREDPENDEMI("30", "Nota de Crédito pendiente de Emisión");
	 
	String valor;
	String descripcion;
	
	private TipoMedioPagoAgregarConfCobroEnum(String valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public static TipoMedioPagoAgregarConfCobroEnum getEnumByValor(String valor) {
		for (TipoMedioPagoAgregarConfCobroEnum item : TipoMedioPagoAgregarConfCobroEnum.values()) {
			if (item.getValor().equalsIgnoreCase(valor)) {
				return item;
			}
		}
		return null;
	}
}
