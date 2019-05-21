package ar.com.telecom.shiva.base.enumeradores;
/**
 * @author u578936 MA.Uehara
 *
 */
public enum SemaforoGestionabilidadEnum {

	ROJO("rojo"),
	NARANJA("naranja"),
	AMARILLO("amarillo"),
	VERDE("verde");

	String descripcion;

	private SemaforoGestionabilidadEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public static SemaforoGestionabilidadEnum getEnumByName(String name){
		for (SemaforoGestionabilidadEnum enumerador : SemaforoGestionabilidadEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static SemaforoGestionabilidadEnum getEnumByDescripcion(String descripcion){
		for (SemaforoGestionabilidadEnum enumerador : SemaforoGestionabilidadEnum.values()) {
			if (enumerador.getDescripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}
