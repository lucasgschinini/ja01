package ar.com.telecom.shiva.base.enumeradores;

public enum TipoInvocacionEnum {

	$01(1, "Apropiacion de deuda"),
	$02(2, "Apropiacion de medio de pago"),
	$03(3, "Apropiacion de deuda y medio de pago"),
	$04(4, "Desapropiacion de operacion"),
	$05(5, "Confirmacion de operacion"),
	$06(6, "Cargo a Proxima Factura Debito"),
	$07(7, "Cargo a Proxima Factura Crédito"),
	$08(8, "Simulación de Intereses"),
	$09(9, "Descobro"),
	
	//Deimos
	$10(10, "Apropiacion de documentos"),
	$11(11, "Desapropiacion de documentos"),
	
	//SAP
	$12(12, "Registrar compensacion sap"),
	$13(13, "Consultar proveedor sap");
	
	int valor;
	String descripcion;
	
	private TipoInvocacionEnum(int valor, String descripcion) {
		this.valor = valor;
	    this.descripcion = descripcion;
	}
	
	public int getValor() {
	    return this.valor;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public static TipoInvocacionEnum getEnumById(Long id) {
		for (TipoInvocacionEnum v : TipoInvocacionEnum.values()) {
			if (v.getValor()==id) {
				return v;
			}
		}
		        
		return null; 
	}
}
