package ar.com.telecom.shiva.presentacion.bean.filtro;

/**
 * @author u564030
 */
public class LegajoChequeRechazadoFiltro extends Filtro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String selectCliente;
	protected String textCliente;
	
	/**
	 * 
	 */
	public LegajoChequeRechazadoFiltro() {

	}

	/**
	 * @return the selectCliente
	 */
	public String getSelectCliente() {
		return selectCliente;
	}

	/**
	 * @param selectCliente the selectCliente to set
	 */
	public void setSelectCliente(String selectCliente) {
		this.selectCliente = selectCliente;
	}

	/**
	 * @return the textCliente
	 */
	public String getTextCliente() {
		return textCliente;
	}

	/**
	 * @param textCliente the textCliente to set
	 */
	public void setTextCliente(String textCliente) {
		this.textCliente = textCliente;
	}
}
