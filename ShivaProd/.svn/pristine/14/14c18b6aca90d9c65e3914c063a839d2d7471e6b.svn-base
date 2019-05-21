package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * @author u578936 MA.Uehara
 *
 */
public enum TipoRemanenteEnum {
	//Remanente NO Actualizable
	TRANSFERIBLE_NO_ACTUALIZABLE(1, "TRANSFERIBLE NO ACTUALIZABLE", idMedioPago_Remanente_Actualizable(), "SN"),
	NO_TRANSFERIBLE_NO_ACTUALIZABLE(2, "NO TRANSFERIBLE NO ACTUALIZABLE", idMedioPago_Remanente_Actualizable(), "NN"),
	
	//Remanente Actualizable
	TRANSFERIBLE_ACTUALIZABLE(3, "TRANSFERIBLE ACTUALIZABLE", idMedioPago_Remanente_NO_Actualizable(), "SS"),
	FICTICIO_PARA_NOTAS_DE_CREDITO_MIC(4, "FICTICIO PARA NOTAS DE CREDITO MIC", idMedioPago_Remanente_NO_Actualizable(), "NC"),
	DEPOSITOS_EN_GARANTIA(5, "DEPOSITOS EN GARANTIA", idMedioPago_Remanente_NO_Actualizable(), "NG"),
	PAGO_A_CUENTA_POR_RECLAMO(6, "PAGO A CUENTA POR RECLAMO", idMedioPago_Remanente_NO_Actualizable(), "NP");

	String descripcion;
	long codigo;
	String idTipoMedioPago;
	String nombreCorto;
	
	private TipoRemanenteEnum(long codigo, String descripcion, String idTipoMedioPago, String nombreCorto) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.idTipoMedioPago = idTipoMedioPago;
		this.nombreCorto = nombreCorto;
	}

	public String descripcion() {
		return this.descripcion;
	}
	public long codigo() {
		return this.codigo;
	}
	public String getIdTipoMedioPago() {
		return this.idTipoMedioPago;
	}
	public String nombreCorto() {
		return this.nombreCorto;
	}

	
	public static String idMedioPago_Remanente_NO_Actualizable() {
		return "20";
	}
	
	public static String idMedioPago_Remanente_Actualizable() {
		return "19";
	}
	
	public static TipoRemanenteEnum getEnumByName(String name){
		for (TipoRemanenteEnum enumerador : TipoRemanenteEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoRemanenteEnum getEnumByCodigo(Long codigo){
		for (TipoRemanenteEnum enumerador : TipoRemanenteEnum.values()) {
			if (enumerador.codigo() == codigo) {
				return enumerador;
			}
		}
		return null;
	}

	public static TipoRemanenteEnum getEnumByCodigo(String strCodigo){
		
		if (!Validaciones.isNullOrEmpty(strCodigo)) {
			
			Long codigo = new Long(strCodigo); 
			for (TipoRemanenteEnum enumerador : TipoRemanenteEnum.values()) {
				if (enumerador.codigo() == codigo) {
					return enumerador;
				}
			}
		}
		return null;
	}

	public static TipoRemanenteEnum getEnumByDescripcion(String descripcion){
		for (TipoRemanenteEnum enumerador : TipoRemanenteEnum.values()) {
			if (enumerador.descripcion().equals(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}
}
