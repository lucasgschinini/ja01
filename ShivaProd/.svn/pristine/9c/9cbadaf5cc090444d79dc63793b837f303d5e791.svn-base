package ar.com.telecom.shiva.base.enumeradores;



public enum TipoOperacionReversionEnum {
	
	REVERSION_FACTURA("ReversionFactura"),
	REVERSION_REMANENTE("ReversionRemanente"),
	REVERSION_NOTA_CREDITO("ReversionNotaCredito"),
	REVERSION_CTA("ReversionCTA"),
	REVERSION_VALORES_CHEQUE("ReversionValoresCheque"),
	REVERSION_VALORES_CHEQUE_DIFERIDO("ReversionValoresChequeDiferido"),
	REVERSION_VALORES_EFECTIVO("ReversionValoresEfectivo"),
	REVERSION_VALORES_TRANSFERENCIA("ReversionValoresTransferencia"),
	REVERSION_VALORES_INTERDEPOSITO("ReversionValoresInterdeposito");

	String descripcion;
	
	private TipoOperacionReversionEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static TipoOperacionReversionEnum getEnumByDescripcion(String descripcion) {
		for (TipoOperacionReversionEnum tipoOperacion : TipoOperacionReversionEnum.values()) {
			if (tipoOperacion.descripcion().equalsIgnoreCase(descripcion)) {
				return tipoOperacion;
			}
		}
		return null; 
	}

	public static boolean esTipoOperacionValor(TipoOperacionReversionEnum tipoOperacion) {
		if(tipoOperacion.equals(REVERSION_VALORES_CHEQUE) 
				|| tipoOperacion.equals(REVERSION_VALORES_CHEQUE_DIFERIDO) 
				|| tipoOperacion.equals(REVERSION_VALORES_EFECTIVO)
				|| tipoOperacion.equals(REVERSION_VALORES_INTERDEPOSITO)
				|| tipoOperacion.equals(REVERSION_VALORES_TRANSFERENCIA)){
			return true;
		}
		return false;
	}
}
