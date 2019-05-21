package ar.com.telecom.shiva.presentacion.bean.dto.json;

public class CotizacionJsonResponse extends JsonResponse {

	private String cotizacion;
	public CotizacionJsonResponse() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}
	/**
	 * @param cotizacion the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	
}
