package ar.com.telecom.shiva.presentacion.bean.filtro;

public class ClienteFiltro extends Filtro {
	
	private static final long serialVersionUID = 20170602L;

	private String criterio;
	private String busqueda;
	private boolean obtnerPrimero = false;

	public ClienteFiltro() {
		super();
		this.criterio = "";
		this.busqueda = "";
	}
	
	public ClienteFiltro clone() {
		ClienteFiltro clone = new ClienteFiltro();
		clone.setCriterio(this.criterio);
		clone.setBusqueda(this.busqueda);
		return clone;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	/**
	 * @return the obtnerPrimero
	 */
	public boolean isObtnerPrimero() {
		return obtnerPrimero;
	}

	/**
	 * @param obtnerPrimero the obtnerPrimero to set
	 */
	public void setObtnerPrimero(boolean obtnerPrimero) {
		this.obtnerPrimero = obtnerPrimero;
	}
	
}
