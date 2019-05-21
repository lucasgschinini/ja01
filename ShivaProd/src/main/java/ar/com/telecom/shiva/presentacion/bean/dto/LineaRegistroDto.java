package ar.com.telecom.shiva.presentacion.bean.dto;

public class LineaRegistroDto extends GestionDto {

	private static final long serialVersionUID = 4510075138514171929L;
	private String idOperacion;
	private String estadoReversionOperacion;


	public LineaRegistroDto(String idDescobro) {
		this.idOperacion = idDescobro;
	}

	public String getDescripcionDetalle() {
		return estadoReversionOperacion;
	}

	public void setDescripcionDetalle(String descripcionDetalle) {
		this.estadoReversionOperacion = descripcionDetalle;
	}

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

//	@Override
//	public String toString() {
//		return "LineaRegistroDto [idDescobro=" + idOperacion
//				+ ", descripcionDetalle=" + estadoReversionOperacion + "]";
//	}
}
