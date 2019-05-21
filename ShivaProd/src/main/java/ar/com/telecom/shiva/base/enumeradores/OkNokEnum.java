package ar.com.telecom.shiva.base.enumeradores;

public enum OkNokEnum {

	OK("OK"),
	NOK("NOK");

	String descripcion;
	
	OkNokEnum(String descripcion){
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static OkNokEnum getEnumByDescripcion(String descripcion) {
		for (OkNokEnum okNokEnum : OkNokEnum.values()) {
			if (okNokEnum.getDescripcion().equalsIgnoreCase(descripcion)){
				return okNokEnum;
			}
		}
		return null; 
	}
}

