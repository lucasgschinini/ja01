package ar.com.telecom.shiva.presentacion.bean.filtro;


public class JurisdiccionFiltro extends Filtro {

	private static final long serialVersionUID = 1L;
	
	private String idProvincia;
	
	public JurisdiccionFiltro(){}

	public JurisdiccionFiltro(String idProvincia) {
		setIdProvincia(idProvincia);
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

}
