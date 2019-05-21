package ar.com.telecom.shiva.base.enumeradores;

/**
GESTIONA_SOBRE
   
   - Valor (Transferencia)
   - Valor (Boleta Deposito p/Cheque)
   - Valor (Boleta Deposito p/Cheque Diferido)
   - Valor (Boleta Deposito p/Efectivo)
   - Valor (Cheque)
   - Valor (Cheque Diferido)
   - Valor (Efectivo)
   - Valor (Interdeposito)
   - Valor (Retención/Impuesto)
   
   - Registro AVC (Transferencia)
   - Registro AVC (Deposito Efectivo)
   - Registro AVC (Deposito Cheque)
   - Registro AVC (Deposito Cheque Diferido)
   - Registro AVC (Interdeposito)
   
   - Talonario
 */
public enum TipoTareaGestionaEnum {
	
	/**
    50 caracteres 
    12345678901234567890123456789012345678901234567890  
     */
	VALOR_TRANSF("Valor (Transferencia)"),
	VALOR_BOLETA_DEP_CHQ("Valor (Cheque)"),
	VALOR_BOLETA_DEP_CHQ_DIF("Valor (Cheque Diferido)"),
	VALOR_BOLETA_DEP_EFE("Valor (Efectivo)"),
	VALOR_CHEQUE("Valor (Cheque)"),
	VALOR_CHEQUE_DIFERIDO("Valor (Cheque Diferido)"),
	VALOR_EFECTIVO("Valor (Efectivo)"),
	VALOR_INTERDEPOSITO("Valor (Interdeposito)"),
	VALOR_RETENCION_IMPUESTO("Valor (Retencion/Impuesto)"),
	
	REGISTRO_AVC_TRANSF("Registro AVC (Transferencia)"),
	REGISTRO_AVC_DEP_EFE("Registro AVC (Efectivo)"),
	REGISTRO_AVC_DEP_CHQ("Registro AVC (Cheque)"),
	REGISTRO_AVC_DEP_CHQ_DIF("Registro AVC (Cheque Diferido)"),
	REGISTRO_AVC_INTERDEPOSITO("Registro AVC (Interdeposito)"),
	
	COBRO("Cobro"),
	DESCOBRO("Descobro"),
	
	OPERACION_MASIVA("Operacion Masiva"),
	
	TALONARIO("Talonario");
	
	String descripcion;
	
	private TipoTareaGestionaEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static TipoTareaGestionaEnum getEnumByName(String name){
		
		for (TipoTareaGestionaEnum item : TipoTareaGestionaEnum.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null; 
	}	
}
