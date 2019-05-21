package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoDUCEnum {

	GENERADO("G", "GENERADO"),
	ENVIADO("E", "ENVIADO"),
	VENCIDO("V", "VENCIDO"),
	CONV_BAJA_INEX("B", "CONV. EN BAJA/INEX"),
	COBRADO("C", "COBRADO"),
	COBRADO_Y_APLIC_REF("A", "COBRADO Y APLIC.REF."),
	COBRADO_Y_MOV_RTE_I("M", "COBRADO Y MOV. RTE I"),
	COBRADO_Y_TRANSFER("T", "COBRADO Y TRANSFER."),
	COBRADO_Y_ENV_FACT("I", "COBRADO Y ENV. FACT"),
	COBRADO_Y_UTILIZADO("U", "COBRADO Y UTILIZADO"),
	COBRO_REVERTIDO("R", "COBRO REVERTIDO");

	String codigo;
	String descripcion;
	
	private EstadoDUCEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static EstadoDUCEnum getEnumByName(String name) {
		
		for (EstadoDUCEnum estadoDuc : EstadoDUCEnum.values()) {
			if (estadoDuc.name().equalsIgnoreCase(name)) {
				return estadoDuc;
			}
		}
		        
		return null; 
	}
	
	public static EstadoDUCEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoDUCEnum estadoDuc : EstadoDUCEnum.values()) {
			if (estadoDuc.descripcion().equalsIgnoreCase(descripcion)) {
				return estadoDuc;
			}
		}
		return null;
	}
	
	public static EstadoDUCEnum getEnumByCodigo(String codigo){
		for (EstadoDUCEnum estadoDuc : EstadoDUCEnum.values()) {
			if (estadoDuc.codigo().equals(codigo)) {
				return estadoDuc;
			}
		}
		return null;
	}
}
