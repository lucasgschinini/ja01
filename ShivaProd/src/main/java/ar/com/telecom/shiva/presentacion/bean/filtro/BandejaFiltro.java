package ar.com.telecom.shiva.presentacion.bean.filtro;

public class BandejaFiltro extends Filtro {

	private static final long serialVersionUID = 1L;
	
	private String idFiltro;
	
	private String descripcion;
	
	public BandejaFiltro(){}

	public BandejaFiltro(String idFiltro, String descripcion) {
		super();
		this.idFiltro = idFiltro;
		this.descripcion = descripcion;
	}

	public String getIdFiltro() {
		return idFiltro;
	}

	public void setIdFiltro(String idFiltro) {
		this.idFiltro = idFiltro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
