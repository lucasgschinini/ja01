package ar.com.telecom.shiva.base.enumeradores;

/**
 * @author fabio.giaquinta.ruiz
 * 23/09/2014
 */
public enum OrigenEnum {
	
	CAJERO_PAGADOR(1, "Cajero Pagador"),
	CLIENTE(2, "Cliente"),
	OF_RECIBO_PREIMPRESO(3, "Oficina Recibo Preimpreso"),
	OF_CONSTANCIA_AUTO(4, "Oficina Constancia Automática"),
	CONCILIACION(5, "Conciliación"),
	CONCILIACION_EXTERNA(6, "Conciliacion Externa"),
	REVERSION(7, "Reversión"),
	BANCO(8, "Banco");

	Integer codigo;
	String descripcion;
	
	private OrigenEnum(Integer codigo, String descripcion) {
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
	public static OrigenEnum getEnumByName(String name){
		
		for (OrigenEnum enumAbuscar : OrigenEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
}
