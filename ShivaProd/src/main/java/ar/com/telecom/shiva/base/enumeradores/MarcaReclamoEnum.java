package ar.com.telecom.shiva.base.enumeradores;

public enum MarcaReclamoEnum {

	SIN_MARCA("", "SIN MARCA", SiNoEnum.NO, SistemaEnum.MIC),
	ANULADO("D", "ANULADO", SiNoEnum.NO, SistemaEnum.MIC),
	RESUELTO_A("A", "RESUELTO A", SiNoEnum.NO, SistemaEnum.MIC),
	RESUELTO_P("P", "RESUELTO P", SiNoEnum.NO, SistemaEnum.MIC),
	RESUELTO_T("T", "RESUELTO T", SiNoEnum.NO, SistemaEnum.MIC),
	PENDIENTE("R", "PENDIENTE", SiNoEnum.SI, SistemaEnum.MIC),
	RECLAMADA("S", "RECLAMADA", SiNoEnum.SI, SistemaEnum.CALIPSO),
	NO_RECLAMADA("N", "NO RECLAMADA", SiNoEnum.NO, SistemaEnum.CALIPSO);
	
	String codigo;
	String descripcion;
	SiNoEnum siNoEnum; //Para mostrar en la pantalla
	SistemaEnum sistemaEnum;

	private MarcaReclamoEnum(String codigo, String descripcion, SiNoEnum siNoEnum, SistemaEnum sistemaEnum) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.siNoEnum = siNoEnum;
		this.sistemaEnum = sistemaEnum;
	}

	public String codigo(){
		return this.codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public String descripcionAMostrar() {
		return this.siNoEnum.descripcion;
	}
	
	public static MarcaReclamoEnum getEnumByName(String name) {
		
		for (MarcaReclamoEnum marcaReclamo : MarcaReclamoEnum.values()) {
			if (marcaReclamo.name().equalsIgnoreCase(name)) {
				return marcaReclamo;
			}
		}
		return null;
	}

	public static MarcaReclamoEnum getEnumByCodigo(String codigo) {
		
		for (MarcaReclamoEnum marcaReclamo : MarcaReclamoEnum.values()) {
			if (marcaReclamo.codigo().equalsIgnoreCase(codigo)) {
				return marcaReclamo;
			}
		}
		return null;
	}

	public static MarcaReclamoEnum getEnumByDescripcion(String descripcion) {
		
		for (MarcaReclamoEnum marcaReclamo : MarcaReclamoEnum.values()) {
			if (marcaReclamo.descripcion().equalsIgnoreCase(descripcion)) {
				return marcaReclamo;
			}
		}
		return null;
	}
}
