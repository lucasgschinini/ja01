package ar.com.telecom.shiva.presentacion.bean.paginacion;


public enum PaginadorAccion {
	SIGUIENTE("next", "stge"),
	ANTERIOR("previous", "ant"),
	BUSCAR("search", "busc");

	public String descripcion;
	public String evento;

	private PaginadorAccion(String descripcion, String evento) {
		this.descripcion = descripcion;
		this.evento = evento;
	}
	public static PaginadorAccion getEnumByName(String name) {
		for (PaginadorAccion enumerado : PaginadorAccion.values()) {
			if (enumerado.name().equals(name)) {
				return enumerado;
			}
		}
		return null; 
	}
	public static PaginadorAccion getEnumByDescripcion(String descripcion) {
		for (PaginadorAccion estado : PaginadorAccion.values()) {
			if (estado.descripcion.equals(descripcion)) {
				return estado;
			}
		}
		return null; 
	}
	public static PaginadorAccion getEnumByEvento(String evento) {
		for (PaginadorAccion estado : PaginadorAccion.values()) {
			if (estado.evento.equals(evento)) {
				return estado;
			}
		}
		return null; 
	}
}
