package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSapConsultaProveedoresWS;

public class ConsultarProveedoresSapRto {
	
	private String operacionFormateado;
	private Long idOperacion;
	private EntradaSapConsultaProveedoresWS entradaSapConsultaProveedoresWS;
	
	/**
	 * @return the operacionFormateado
	 */
	public String getOperacionFormateado() {
		return operacionFormateado;
	}

	/**
	 * @param operacionFormateado the operacionFormateado to set
	 */
	public void setOperacionFormateado(String operacionFormateado) {
		this.operacionFormateado = operacionFormateado;
	}

	public EntradaSapConsultaProveedoresWS getEntradaSapConsultaProveedoresWS() {
		return entradaSapConsultaProveedoresWS;
	}

	public void setEntradaSapConsultaProveedoresWS(
			EntradaSapConsultaProveedoresWS entradaSapConsultaProveedoresWS) {
		this.entradaSapConsultaProveedoresWS = entradaSapConsultaProveedoresWS;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
}
