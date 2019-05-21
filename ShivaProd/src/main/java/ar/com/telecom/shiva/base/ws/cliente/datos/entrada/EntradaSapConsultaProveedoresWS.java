package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class EntradaSapConsultaProveedoresWS extends EntradaWS {
	
	protected List<String> listaCuits = new ArrayList<String>();
	
	/**
	 * @return the listaCuits
	 */
	public List<String> getListaCuits() {
		return listaCuits;
	}
	/**
	 * @param listaCuits the listaCuits to set
	 */
	public void setListaCuits(List<String> listaCuits) {
		this.listaCuits = listaCuits;
	}
}
