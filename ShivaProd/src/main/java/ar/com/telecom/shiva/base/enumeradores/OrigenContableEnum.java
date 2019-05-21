package ar.com.telecom.shiva.base.enumeradores;

/**
 * 
 * @author u564030
 *
 */
public enum OrigenContableEnum {
	
	// Movimientos de origenes contables correspondientes a Legajos de Cheques Rechazados
	
	$015("015", "Baja Cheque Rechazado"),
	$040("040", "Reversi�n por Ch. Rechazado (circuito cobranza bancaria)"),
	$041("041", "Aplicaci�n Mic x Reversi�n de Ch. Rech (circuito cobranza bancaria)"),
	$042("042", "Aplicaci�n Calipso x Reversi�n de Ch. Rech (circuito cobranza bancaria)"),
	$043("043", "Suspensi�n de Cheque por Rechazo"),
	$044("044", "Aplicaci�n DEIMOs x Reversi�n de Ch. Rech (circuito cobranza bancaria)"),
	$045("045", "Alta de legajo de cheque rechazado (circuito manual)"),
	$046("046", "Env�o a legales de cheque rechazado (circuito manual)"),
	$047("047", "Desistimiento de cheque rechazado (circuito manual)");
	
	String codigo;
	String descripcion;
	
	private OrigenContableEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
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
	public static OrigenContableEnum getEnumByName(String name){
		
		for (OrigenContableEnum enumAbuscar : OrigenContableEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
	
public static OrigenContableEnum getEnumByCodigo(String codigo){
		
		for (OrigenContableEnum enumAbuscar : OrigenContableEnum.values()) {
			if (enumAbuscar.codigo().equals(codigo)) {
				return enumAbuscar;
			}
		}
		return null; 
	}
}
