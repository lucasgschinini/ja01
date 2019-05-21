package ar.com.telecom.shiva.base.enumeradores;
/**
 * @author u578936 MA.Uehara
 *
 */
// LOS DATOS SON DE LA TABLA: SHV_PARAM_MOTIVO
public enum MotivoShivaEnum {
	
	CHEQUE_RECHAZADO("Cheque Rechazado", "4"), 
	CHEQUE_DE_GARANTIA_CORRIENTE("Cheque de Garantía Corriente", "7"),
	COBRANZA_REGULAR("Cobranza Regular", "3"),
	COBRO_PLAN_DE_FINACIACION("Cobro Plan de Financiación", "6"),
	CONCURSOS_Y_QUIEBRA("Concursos y Quiebras", "1"),
	PAGO_A_CUENTA("Pago a Cuenta", "5"),
	POSTBAJA("Postbaja", "2"),
	VALOR_EN_GARANTIA("Valor en Garantía", "8"),
	VALOR_POR_RECLAMO_DE_FACTURACION("Valor por Reclamo de Facturación", "9");

	String descripcion;
	String idMotivo;

	private MotivoShivaEnum(String descripcion, String idMotivo) {
		this.descripcion = descripcion;
		this.idMotivo = idMotivo;
	}
	public String descripcion() {
		return this.descripcion;
	}
	
	public String idMotivo() {
		return this.idMotivo;
	}

	public static MotivoShivaEnum getEnumByName(String name) {
		for (MotivoShivaEnum enumerador : MotivoShivaEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoShivaEnum getEnumByDescripcion(String descripcion) {
		for (MotivoShivaEnum enumerador : MotivoShivaEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}

	public static MotivoShivaEnum getEnumByIdMotivo(String idMotivo) {
		for (MotivoShivaEnum enumerador : MotivoShivaEnum.values()) {
			if (enumerador.idMotivo.equals(idMotivo)) {
				return enumerador;
			}
		}
		return null;
	}
}