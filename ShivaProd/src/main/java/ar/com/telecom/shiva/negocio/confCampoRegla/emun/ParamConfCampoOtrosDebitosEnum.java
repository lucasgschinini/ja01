package ar.com.telecom.shiva.negocio.confCampoRegla.emun;


/**
 * @author u578936 M.A.Uehara
 *
 */
public enum ParamConfCampoOtrosDebitosEnum {
	MONEDA_OPERACION("moneda operacion"),
	SOCIEDAD("sociedad"),
	MONEDA("moneda"),
	SISTEMA("sistema"),
	TIPOCOMPROBANTE("tipoComprobante"),
	CLIENTE("cliente"),
	REFERENCIAPAGO("referenciaPago"),
	NUMERODOCUMENTO("numeroDocumento"),
	TIPOCAMBIO("tipoCambio"),
	IMPORTE("importe"),
	IMPORTEMONEDAORIGEN("importeMonedaOrigen"),
	FECHAVENCIMIENTO("fechaVencimiento"),
	SINDIFERENCIADECAMBIO("sinDiferenciaDeCambio"),
	AGREGARDETALLE("agregarDetalle");

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}
	

	private ParamConfCampoOtrosDebitosEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * 
	 * @param descripcionCorta
	 * @return
	 */
	public static ParamConfCampoOtrosDebitosEnum getEnumByDescripcion(String descripcion){
		for (ParamConfCampoOtrosDebitosEnum item : ParamConfCampoOtrosDebitosEnum.values()) {
			if (item.getDescripcion().equals(descripcion)) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static ParamConfCampoOtrosDebitosEnum getEnumByName(String name){
		for (ParamConfCampoOtrosDebitosEnum item : ParamConfCampoOtrosDebitosEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}
}
