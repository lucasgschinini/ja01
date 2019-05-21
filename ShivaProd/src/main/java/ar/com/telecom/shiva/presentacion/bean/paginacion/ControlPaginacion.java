package ar.com.telecom.shiva.presentacion.bean.paginacion;

import java.io.Serializable;



public abstract class ControlPaginacion implements Serializable {
	private static final long serialVersionUID = 20170609L;

	protected int paginaActual;
	protected int totalPaginas;
	protected int cantPorPagina;
	protected int totalRegistros;
	protected int cantRegistrosMostrados;
	protected boolean inhabilitarAnt;
	protected boolean inhabilitarSig;

	private PaginadorAccion accion;
	
	public ControlPaginacion() {
		this.paginaActual = 1;
		this.totalPaginas = 0;
		this.totalRegistros = 0;
		this.cantRegistrosMostrados = 0;
		this.inhabilitarAnt = false;
		this.inhabilitarSig = true;
	}
	
	public void incrementarPagina() {
		this.paginaActual++; 
	}
	
	public void decrementarPagina() {
		this.paginaActual--;
		if (this.paginaActual < 1) this.paginaActual = 1; 
	}
	
	public void actualizarPagina() {
		this.totalPaginas = (this.totalRegistros / this.cantPorPagina) + (((this.totalRegistros % this.cantPorPagina) > 0 || this.totalRegistros == 0) ? 1:0);
		if (this.paginaActual > this.totalPaginas) this.paginaActual = this.totalPaginas;
		this.inhabilitarAnt = (this.paginaActual == 1);
		this.inhabilitarSig = (this.paginaActual == this.totalPaginas);
	}
	
	public int getPaginaAnterior() {
		if ((this.paginaActual-1) == 1) {
			return 1;
		}
		return (paginaActual - 1);
	}
	
	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public int getCantPorPagina() {
		return cantPorPagina;
	}

	public void setCantPorPagina(int cantPorPagina) {
		this.cantPorPagina = cantPorPagina;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getCantRegistrosMostrados() {
		return cantRegistrosMostrados;
	}

	public void setCantRegistrosMostrados(int cantRegistrosMostrados) {
		this.cantRegistrosMostrados = cantRegistrosMostrados;
	}

	public boolean isInhabilitarAnt() {
		return inhabilitarAnt;
	}

	public void setInhabilitarAnt(boolean inhabilitarAnt) {
		this.inhabilitarAnt = inhabilitarAnt;
	}

	public boolean isInhabilitarSig() {
		return inhabilitarSig;
	}

	public void setInhabilitarSig(boolean inhabilitarSig) {
		this.inhabilitarSig = inhabilitarSig;
	}

	/**
	 * @return the accion
	 */
	public PaginadorAccion getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(PaginadorAccion accion) {
		this.accion = accion;
	}
}