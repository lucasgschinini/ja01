package ar.com.telecom.shiva.base.enumeradores;

public enum AccionesReglaCobroEnum {
	
	PERMITIR("Permitir la transferencia a pr�xima factura"),
	NO_PERMITIR("No permitir la transferencia a pr�xima factura"),
	CON_COMPROB("Permitir la transferencia a pr�xima factura CON comprobante"),
	SIN_COMPROB("Permitir la transferencia a pr�xima factura SIN comprobante");

	private AccionesReglaCobroEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	
}
