/**
 * 
 */
package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;

/**
 * @author u586743
 *
 */
public class EntradaSapRegistrarCompensacionWS extends EntradaWS {
	 
	private static final long serialVersionUID = 1L;
	
	protected List<Partida> ListaPartidas = new ArrayList<Partida>();
	
	protected Long idOperacionShiva;
	
	protected String usuarioCobrador;

	byte[] pdfFlie ;
	
	/**
	 * @return the listaPartidas
	 */
	public List<Partida> getListaPartidas() {
		return ListaPartidas;
	}

	/**
	 * @param listaPartidas the listaPartidas to set
	 */
	public void setListaPartidas(List<Partida> listaPartidas) {
		ListaPartidas = listaPartidas;
	}

	/**
	 * @return the idOperacionShiva
	 */
	public Long getIdOperacionShiva() {
		return idOperacionShiva;
	}

	/**
	 * @param idOperacionShiva the idOperacionShiva to set
	 */
	public void setIdOperacionShiva(Long idOperacionShiva) {
		this.idOperacionShiva = idOperacionShiva;
	}

	/**
	 * @return the usuarioCobrador
	 */
	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}

	/**
	 * @param usuarioCobrador the usuarioCobrador to set
	 */
	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}

	/**
	 * @return the pdfFlie
	 */
	public byte[] getPdfFlie() {
		return pdfFlie;
	}

	/**
	 * @param pdfFlie the pdfFlie to set
	 */
	public void setPdfFlie(byte[] pdfFlie) {
		this.pdfFlie = pdfFlie;
	}

	

}
