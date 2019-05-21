package ar.com.telecom.shiva.base.enumeradores;

public enum TipoFacturaEnum {

	FACT_FLEXCAB (51,"FACT. FLEXCAB"),  
	FACT_BAJAS  (52,"FACT. BAJAS"), 
	FACT_POST_BAJ (53,"FACT. POST. BAJ"),
	FACT_INIC_ESP (54,"FACT. INIC. ESP"),
	NOTA_DEB_N_PAG (55,"NOTA DEB. N PAG"),
	INTERES_RECARGO (71,"INTERES/RECARGO"),
	DESCUENTOS  (72,"DESCUENTOS"),
	DERECHO_REHABIL (73,"DERECHO REHABIL"),
	ND_INICIAL_ESP  (74,"ND INICIAL ESP"),
	DESC_F_INIC_ESP (75,"DESC.F.INIC.ESP"),
	FACTURA_ONLINE (76,"FACTURA ONLINE"), 
	NC_ONLINE (77,"NC ONLINE"); 

	int codigo;
	String descripcion;

	private TipoFacturaEnum(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public int codigo(){
		return this.codigo;
	}

	public String descripcion() {
		return this.descripcion;
	}

	public static TipoFacturaEnum getEnumByName(String name) {
		for (TipoFacturaEnum enumerador : TipoFacturaEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoFacturaEnum getEnumByCodigo(int codigo) {
		for (TipoFacturaEnum enumerador : TipoFacturaEnum.values()) {
			if (enumerador.codigo() == codigo) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoFacturaEnum getEnumByDescripcion(String descripcion) {
		for (TipoFacturaEnum enumerador : TipoFacturaEnum.values()) {
			if (enumerador.descripcion().equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}