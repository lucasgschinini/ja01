package ar.com.telecom.shiva.base.enumeradores;

/**
 * @author u578936 M.A.Uehara
 *
 */
public enum EstadoNotificacionEnum {
	ACTIVA("Activa"),
	ELIMINADA("Eliminada");
	
	String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	private EstadoNotificacionEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public static EstadoNotificacionEnum getEnumByDescripcion(String descripcion) {
		for (EstadoNotificacionEnum enumumerado : EstadoNotificacionEnum.values()) {
			if (enumumerado.descripcion.equalsIgnoreCase(descripcion)){
				return enumumerado;
			}
		}
		return null;
	}
	
	public static EstadoNotificacionEnum getEnumByName(String name) {
		for (EstadoNotificacionEnum enumumerado : EstadoNotificacionEnum.values()) {
			if (enumumerado.name().equalsIgnoreCase(name)) {
				return enumumerado;
			}
		}
		return null;
	}
}
