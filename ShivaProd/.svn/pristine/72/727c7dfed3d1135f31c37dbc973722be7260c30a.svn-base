package ar.com.telecom.shiva.base.enumeradores;

public enum TipoChequeEnum {

	CHEQUE(5,"Cheque"),
	CHEQUE_DIFERIDO(6,"Cheque Diferido");
	
	int idTipoCheque;
	String descripcion;
	
	private TipoChequeEnum(int idTipoCheque, String descripcion) {
	    this.descripcion = descripcion;
	    this.idTipoCheque = idTipoCheque;
	}
	
	public int getIdTipoCheque() {
		return idTipoCheque;
	}
	public void setIdTipoCheque(int idTipoCheque) {
		this.idTipoCheque = idTipoCheque;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
public static TipoChequeEnum getEnumByIndice(int idTipoCheque) {
		
		for (TipoChequeEnum tipoCheque : TipoChequeEnum.values()) {
			if (tipoCheque.getIdTipoCheque()==idTipoCheque) {
				return tipoCheque;
			}
		}
		        
		return null; 
	}
	
	public static TipoChequeEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoChequeEnum tipoCheque : TipoChequeEnum.values()) {
			if (tipoCheque.getDescripcion().equals(descripcion)) {
				return tipoCheque;
			}
		}
		        
		return null; 
	}
	
	
}
