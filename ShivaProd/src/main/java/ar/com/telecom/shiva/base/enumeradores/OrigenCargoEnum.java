package ar.com.telecom.shiva.base.enumeradores;

/**
 * @author fabio.giaquinta.ruiz
 * 23/09/2014
 */
public enum OrigenCargoEnum {

	TRASLADO_SALDO_COMPENSACION("00", "Traslado Saldo Compensacion"),
	TRASLADO_SALDO_FACTURA("01", "Traslado Saldo factura"),
	TRASLADO_NC("02", "Traslado NC"),
	TRASLADO_REMANENTE("03", "Traslado Remanente"),
	TRASLADO_SALDO_PAGO_CUENTA("04", "Traslado Saldo Pago a Cuenta"),
	TRASLADO_SALDO_CHEQUE("05", "Traslado Saldo Cheque"),
	TRASLADO_SALDO_DEPOSITO("06", "Traslado Saldo Deposito"),
	TRASLADO_SALDO_TRANSFERENCIA("07", "Traslado Saldo Transferencia"),
	TRASLADO_SALDO_INTERDEPOSITO("08", "Traslado Saldo Interdeposito"),
	CC_CLP_TRAS_SALDO_FACTURA("09", "Contracargo Calipso Traslado Saldo factura"),
	CC_CLP_TRAS_NC("10", "Contracargo Calipso Traslado NC"),
	CC_CLP_TRAS_REMANENTE("11", "Contracargo Calipso Traslado Remanente"),
	CC_CLP_TRAS_SALDO_PAG_CTA("12", "Contracargo Calipso Traslado Saldo Pago a Cuenta"),
	CC_CLP_TRAS_SALDO_CHEQUE("13", "Contracargo Calipso Traslado Saldo Cheque"),
	CC_CLP_TRAS_SALDO_DEPOSITO("14", "Contracargo Calipso Traslado Saldo Deposito"),
	CC_CLP_TRAS_SALDO_TRANS("15", "Contracargo Calipso Traslado Saldo Transferencia"),
	CC_CLP_TRAS_SALDO_INTER("16", "Contracargo Calipso Traslado Saldo Interdeposito"),
	CC_INT_CLP_TRAS_SALDO_FACTURA("17", "Contracargo Intereses Calipso Traslado Saldo Factura"),
	CC_INT_CLP_TRAS_NC("18", "Contracargo Intereses Calipso Traslado NC"),
	CC_INT_CLP_TRAS_REMANENTE("19", "Contracargo Intereses Calipso Traslado Remanente"),
	CC_INT_CLP_TRAS_SALDO_PAG_CTA("20", "Contracargo Intereses Calipso Traslado Saldo Pago a Cuenta"),
	CC_INT_CLP_TRAS_SALDO_CHEQUE("21", "Contracargo Intereses Calipso Traslado Saldo Cheque"),
	CC_INT_CLP_TRAS_SALDO_DEPOS("22", "Contracargo Intereses Calipso Traslado Saldo Deposito"),
	CC_INT_CLP_TRAS_SALDO_TRANS("23", "Contracargo Intereses Calipso Traslado Saldo Transferencia"),
	CC_INT_CLP_TRAS_SALDO_INTER("24", "Contracargo Intereses Calipso Traslado Saldo Interdeposito"),
	CC_INT_MORA_CLP_REGULADOS("25", "Contracargo Intereses por Mora Calipso Regulados"),
	CC_GEN_TRAS_SALDO_FACTURA("09", "Contracargo Genesis Traslado Saldo factura"),
	CC_GEN_TRAS_NC("10", "Contracargo Genesis Traslado NC"),
	CC_GEN_TRAS_REMANENTE("11", "Contracargo Genesis Traslado Remanente"),
	CC_GEN_TRAS_SALDO_PAG_CTA("12", "Contracargo Genesis Traslado Saldo Pago a Cuenta"),
	CC_GEN_TRAS_SALDO_CHEQUE("13", "Contracargo Genesis Traslado Saldo Cheque"),
	CC_GEN_TRAS_SALDO_DEPOSITO("14", "Contracargo Genesis Traslado Saldo Deposito"),
	CC_GEN_TRAS_SALDO_TRANS("15", "Contracargo Genesis Traslado Saldo Transferencia"),
	CC_GEN_TRAS_SALDO_INTER("16", "Contracargo Genesis Traslado Saldo Interdeposito"),
	CC_INT_GEN_TRAS_SALDO_FACTURA("17", "Contracargo Intereses Genesis Traslado Saldo Factura"),
	CC_INT_GEN_TRAS_NC("18", "Contracargo Genesis Traslado NC"),
	CC_INT_GEN_TRAS_REMANENTE("19", "Contracargo Genesis Traslado Remanente"),
	CC_INT_GEN_TRAS_SALDO_PAG_CTA("20", "Contracargo Genesis Traslado Saldo Pago a Cuenta"),
	CC_INT_GEN_TRAS_SALDO_CHEQUE("21", "Contracargo Genesis Traslado Saldo Cheque"),
	CC_INT_GEN_TRAS_SALDO_DEPOSITO("22", "Contracargo Genesis Traslado Saldo Deposito"),
	CC_INT_GEN_TRAS_SALDO_TRANS("23", "Contracargo Genesis Traslado Saldo Transferencia"),
	CC_INT_GEN_TRAS_SALDO_INTER("24", "Contracargo Genesis Traslado Saldo Interdeposito"),
	CC_INT_MORA_GENESIS_REGULADOS("25", "Contracargo Intereses por Mora Genesis Regulados"),
	CC_INT_MORA_GENESIS_NO_REGULAD("26", "Contracargo Intereses por Mora Genesis No Regulados");
	
	String codigo;
	String descripcion;
	
	private OrigenCargoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static OrigenCargoEnum getEnumByName(String name){
		
		for (OrigenCargoEnum enumAbuscar : OrigenCargoEnum.values()) {
			if (enumAbuscar.name().equals(name)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
	
	/**
	 * Devuelve el enumerador segun el codigo
	 * @param name
	 * @return
	 */
	public static OrigenCargoEnum getEnumByCodigo(String codigo){
		
		for (OrigenCargoEnum enumAbuscar : OrigenCargoEnum.values()) {
			if (enumAbuscar.codigo().equals(codigo)) {
				return enumAbuscar;
			}
		}
		return null; 
	}	
}
