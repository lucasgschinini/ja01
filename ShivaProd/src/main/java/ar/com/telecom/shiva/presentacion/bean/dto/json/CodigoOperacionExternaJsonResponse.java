package ar.com.telecom.shiva.presentacion.bean.dto.json;

public class CodigoOperacionExternaJsonResponse extends JsonResponse {
	
	private long idCobDescobroCodOperExt;
	private String idDescobro;
	private String codigoOperacionExterna;
	private long idCobCobroCodOperExt;
	private String idCobro;
	
	/**
	 * @return the idCobDescobroCodOperExt
	 */
	public long getIdCobDescobroCodOperExt() {
		return idCobDescobroCodOperExt;
	}
	/**
	 * @param idCobDescobroCodOperExt the idCobDescobroCodOperExt to set
	 */
	public void setIdCobDescobroCodOperExt(long idCobDescobroCodOperExt) {
		this.idCobDescobroCodOperExt = idCobDescobroCodOperExt;
	}
	/**
	 * @return the idDescobro
	 */
	public String getIdDescobro() {
		return idDescobro;
	}
	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(String idDescobro) {
		this.idDescobro = idDescobro;
	}
	/**
	 * @return the codigoOperacionExterna
	 */
	public String getCodigoOperacionExterna() {
		return codigoOperacionExterna;
	}
	/**
	 * @param codigoOperacionExterna the codigoOperacionExterna to set
	 */
	public void setCodigoOperacionExterna(String codigoOperacionExterna) {
		this.codigoOperacionExterna = codigoOperacionExterna;
	}
	public long getIdCobCobroCodOperExt() {
		return idCobCobroCodOperExt;
	}
	public void setIdCobCobroCodOperExt(long idCobCobroCodOperExt) {
		this.idCobCobroCodOperExt = idCobCobroCodOperExt;
	}
	public String getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}

	
	
}