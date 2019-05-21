package ar.com.telecom.shiva.presentacion.bean.filtro;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;


public class VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro extends Filtro {
	private static final long serialVersionUID = 20170711L;

	private Long idLegajoChequeRechazado;
	private Estado estado;
	private Long idOperacionCobro;

	public VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro() {
	}
	/**
	 * @return the idLegajoChequeRechazado
	 */
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}
	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	/**
	 * @return the idOperacionCobro
	 */
	public Long getIdOperacionCobro() {
		return idOperacionCobro;
	}
	/**
	 * @param idOperacionCobro the idOperacionCobro to set
	 */
	public void setIdOperacionCobro(Long idOperacionCobro) {
		this.idOperacionCobro = idOperacionCobro;
	}
}
