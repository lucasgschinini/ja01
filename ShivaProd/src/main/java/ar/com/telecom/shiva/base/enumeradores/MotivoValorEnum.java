package ar.com.telecom.shiva.base.enumeradores;

public enum MotivoValorEnum {
	
	CONCURSOS_QUIEBRAS (1 ,"Concursos y Quiebras"),
	POST_BAJA (2,"Postbaja"),
	COBRANZA_REGULAR(3, "Cobranza Regular"),
	CHEQUE_RECHAZADO(4,"Cheque Rechazado"),
	PAGO_CUENTA(5,"Pago a Cuenta"),
	COBRO_PLAN_FINANCIACION(6,"Cobro Plan de Financiación"),
	CHEQUE_GARANTIA_CORRIENTE(7,"Cheque de Garantía Corriente"),
	VALOR_GARANTIA(8,"Valor en Garantía"),
	VALOR_RECLAMO_FACTURACION(9,"Valor por Reclamo de Facturación");
	
	
	
	Integer codigo;
	String descripcion;
	
	private MotivoValorEnum(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public Integer codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoValorEnum getEnumByName(String name){
		
		for (MotivoValorEnum enumAbuscar : MotivoValorEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoValorEnum getEnumByCodigo(Integer name){
		
		for (MotivoValorEnum enumAbuscar : MotivoValorEnum.values()) {
			if (enumAbuscar.codigo().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static MotivoValorEnum getEnumByCodigo(String name){
		
		for (MotivoValorEnum enumAbuscar : MotivoValorEnum.values()) {
			if (enumAbuscar.codigo().toString().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
	
}

