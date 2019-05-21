package ar.com.telecom.shiva.base.enumeradores;

public enum TipoDUCEnum {

	VENTA_LINEAS("01", "VENTA LINEAS"),
	SERV_TEMPORARIOS("02", "SERV. TEMPORARIOS"),
	ANTIC_FINANCIACION("03", "ANTIC. FINANCIACION"),
	ADELANTOS("04", "ADELANTOS"),
	DESVIO_CONSULMO("05", "DESVIO DE CONSUMO"),
	USO_INDEBIDO("06", "USO INDEBIDO"),
	CBIO_DOMICILIO("07", "CBIO. DOMICILIO"),
	CBIO_NUMERO("08", "CBIO. NUMERO"),
	CBIO_TITULARIDAD("09", "CBIO. TITULARIDAD"),
	CBIO_DATOS_CLIENTE("10", "CBIO. DATOS CLIENTES"),
	CUOTAS_FINANCIACION("11", "CUOTAS FINANCIACION"),
	ANTIC_FINAN_CONDOR("12", "ANTIC. FINAN. CONDOR"),
	PAGO_CTA_FACT("13", "PAGO A CTA. DE FACT"),
	VENTA_TP_CAB_MINI("14", "VENTA TP CAB. MINI"),
	PAGO_CTA_FACT_OYP("15", "PAGO A CTA.FACT.OYP"),
	SALDO_ACTA_COMPENSAC("16", "SALDO ACTA COMPENSAC"),
	PAGO_ANTIC_TX_OYP("17", "PAGO ANTIC.TX OYP"),
	PAGO_ANTICIPO_OYP("18", "PAGO ANTICIPO OYP"),
	CUOTA_PLAN_FINAN_OYP("19", "CUOTA PLAN FINAN.OYP"),
	PAGO_GARANTIA_OYP("20", "PAGO GARANTIA OYP"),
	ANTICIPO_DE_FINANCIA("21", "ANTICIPO DE FINANCIA"),
	ANTIC_FINAN_SUR("22", "ANTIC. FINAN. SUR");

	String codigo;
	String descripcion;
	
	private TipoDUCEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static TipoDUCEnum getEnumByName(String name) {
		
		for (TipoDUCEnum tipoDuc : TipoDUCEnum.values()) {
			if (tipoDuc.name().equalsIgnoreCase(name)) {
				return tipoDuc;
			}
		}
		        
		return null;
	}
	public static TipoDUCEnum getEnumByCodigo(String codigo) {
		
		for (TipoDUCEnum tipoDuc : TipoDUCEnum.values()) {
			if (tipoDuc.codigo().equalsIgnoreCase(codigo)) {
				return tipoDuc;
			}
		}
		return null;
	}
	public static TipoDUCEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoDUCEnum tipoDuc : TipoDUCEnum.values()) {
			if (tipoDuc.descripcion().equalsIgnoreCase(descripcion)) {
				return tipoDuc;
			}
		}
		return null;
	}
	public static String getStringValues() {
		StringBuffer str = new StringBuffer();
		for (TipoDUCEnum tipoDuc : TipoDUCEnum.values()) {
			str.append(tipoDuc.descripcion);
			str.append("(");
			str.append(tipoDuc.codigo);
			str.append(") - ");
		}
		str.delete(str.length() - 3, str.length());
		return str.toString();
	}
}
