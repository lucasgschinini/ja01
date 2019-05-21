package ar.com.telecom.shiva.base.enumeradores;
/**
 * @author u578936 MA.Uehara
 *
 */
public enum MarcaReclamoCalipsoEnum {
	RECLAMADA("Si", "RECLAMADA", "S"),
	NO_RECLAMADA("No", "NO RECLAMADA", "N");

	String descripcionCorta;
	String descripcion;
	String codigo;

	private MarcaReclamoCalipsoEnum(String descripcionCorta, String descripcion, String codigo) {
		this.descripcionCorta = descripcionCorta;
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	public String descripcionCorta() {
		return this.descripcionCorta;
	};

	public String descripcion() {
		return this.descripcion;
	};
	
	public String codigo() {
		return this.codigo;
	};

	public static MarcaReclamoCalipsoEnum getEnumByName(String name){
		for (MarcaReclamoCalipsoEnum enumerador : MarcaReclamoCalipsoEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}
	
	public static MarcaReclamoCalipsoEnum getEnumByDescripcionCorta(String descripcionCorta){
		for (MarcaReclamoCalipsoEnum enumerador : MarcaReclamoCalipsoEnum.values()) {
			if (enumerador.descripcionCorta().equals(descripcionCorta)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MarcaReclamoCalipsoEnum getEnumByDescripcion(String descripcion){
		for (MarcaReclamoCalipsoEnum enumerador : MarcaReclamoCalipsoEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
	public static MarcaReclamoCalipsoEnum getEnumByCodigo(String codigo){
		for (MarcaReclamoCalipsoEnum enumerador : MarcaReclamoCalipsoEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}
}
