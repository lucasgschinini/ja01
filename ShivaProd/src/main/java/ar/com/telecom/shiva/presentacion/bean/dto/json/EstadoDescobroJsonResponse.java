package ar.com.telecom.shiva.presentacion.bean.dto.json;

public class EstadoDescobroJsonResponse {

	private String estadoDescripcion;
	private String marcaDescripcion;
	
	public EstadoDescobroJsonResponse() {
		// TODO Auto-generated constructor stub
	}

	
	public EstadoDescobroJsonResponse(String estadoDescripcion,
			String marcaDescripcion) {
		super();
		this.estadoDescripcion = estadoDescripcion;
		this.marcaDescripcion = marcaDescripcion;
	}


	/**
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	/**
	 * @param estadoDescripcion the estadoDescripcion to set
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	/**
	 * @return the marcaDescripcion
	 */
	public String getMarcaDescripcion() {
		return marcaDescripcion;
	}

	/**
	 * @param marcaDescripcion the marcaDescripcion to set
	 */
	public void setMarcaDescripcion(String marcaDescripcion) {
		this.marcaDescripcion = marcaDescripcion;
	}

	
}
