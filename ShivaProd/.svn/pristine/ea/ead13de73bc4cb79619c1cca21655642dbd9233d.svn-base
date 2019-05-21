package ar.com.telecom.shiva.base.enumeradores;
/**
 * @author u578936 MA.Uehara
 *
 */
public enum DescripcionDelTipoDeRemanenteEnum {
	TRANSFERIBLE_NO_ACTUALIZABLE("1", "TRANSFERIBLE_NO_ACTUALIZABLE"),
	NO_TRANSFERIBLE_NO_ACTUALIZABLE("2", "NO TRANSFERIBLE NO ACTUALIZABLE"),
	TRANSFERIBLE_ACTUALIZABLE("3", "TRANSFERIBLE ACTUALIZABLE"),
	FICTICIO_PARA_NOTAS_DE_CREDITO_MIC("4", "FICTICIO PARA NOTAS DE CREDITO MIC"),
	DEPOSITOS_EN_GARANTIA("5", "DEPOSITOS EN GARANTIA"),
	PAGO_A_CUENTA_POR_RECLAMO("6", "PAGO A CUENTA POR RECLAMO");

	String descripcion;
	String codigo;

	private DescripcionDelTipoDeRemanenteEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String descripcion() {
		return this.descripcion;
	}
	public String codigo() {
		return this.codigo;
	}

	public static DescripcionDelTipoDeRemanenteEnum getEnumByName(String name){
		for (DescripcionDelTipoDeRemanenteEnum enumerador : DescripcionDelTipoDeRemanenteEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static DescripcionDelTipoDeRemanenteEnum getEnumByCodigo(String codigo){
		for (DescripcionDelTipoDeRemanenteEnum enumerador : DescripcionDelTipoDeRemanenteEnum.values()) {
			if (enumerador.codigo().equals(codigo)) {
				return enumerador;
			}
		}
		return null;
	}

	public static DescripcionDelTipoDeRemanenteEnum getEnumByDescripcion(String descripcion){
		for (DescripcionDelTipoDeRemanenteEnum enumerador : DescripcionDelTipoDeRemanenteEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}
