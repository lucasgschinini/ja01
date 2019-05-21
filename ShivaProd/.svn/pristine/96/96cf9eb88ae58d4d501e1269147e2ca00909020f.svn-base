package ar.com.telecom.shiva.base.enumeradores;



public enum TipoRetencionEnum {
	
	RETENCION_IIBB(1,"Retencion IIBB", "12"),
	RETENCION_IVA(2,"Retencion IVA", "11"),
	RETENCION_SEGURIDAD_SOCIAL(3,"Retencion Seguridad Social", "09"),
	RETENCION_GANANCIA(4,"Retencion Ganancia", "10"),
	IMPUESTO_AL_SELLO(5,"Impuesto Al Sello", "08"),
	IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE(6,"Impuesto Municipal Seguridad E Higienge", "07"),
	RETENCION_IVA_RG3349(7,"Retencion IVA RG3349", "23"),
	IMPUESTO_TASAS_MUNICIPALES(8,"Impuesto Tasas Municipales", "24");

	int id;
	String descripcion;
	String idTipoMedioPago;
	
	private TipoRetencionEnum(int id ,String descripcion, String idTipoMedioPago) {
		this.id=id;
		this.descripcion = descripcion;
		this.idTipoMedioPago=idTipoMedioPago;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getIdTipoMedioPago(){
		return this.idTipoMedioPago;
	}
	
	public String getIdString() {
		return String.valueOf(this.id);
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoRetencionEnum getEnumByDescripcion(String descripcion) {
		for (TipoRetencionEnum tipoRetencion : TipoRetencionEnum.values()) {
			if (tipoRetencion.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipoRetencion;
			}
		}
		        
		return null; 
	}

	/**
	 * 
	 * @param idTipoRetencion
	 * @return
	 */
	public static TipoRetencionEnum getEnumByIdTipoRetencion(Long idTipoRetencion) {
		
		for (TipoRetencionEnum tipoRetencion : TipoRetencionEnum.values()) {
			if (tipoRetencion.getId()==idTipoRetencion) {
				return tipoRetencion;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param idTipoMedioPago
	 * @return
	 */
	public static TipoRetencionEnum getEnumByIdTipoMedioPago(String idTipoMedioPago) {
		
		for (TipoRetencionEnum tipoRetencion : TipoRetencionEnum.values()) {
			if (tipoRetencion.getIdTipoMedioPago().equalsIgnoreCase(idTipoMedioPago)) {
				return tipoRetencion;
			}
		}
		        
		return null; 
	}
}
