package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.util.List;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

//LEOF
public class OperacionTruncaFiltro extends Filtro {

	private static final long serialVersionUID = 1L;

	private String idOperacion;

	private String descripcionEstadoWorkflow;
	private String idDescripcionEstadoWorkflow;
	private List<Estado> estadosWorkflow;

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getDescripcionEstadoWorkflow() {
		return descripcionEstadoWorkflow;
	}

	public void setDescripcionEstadoWorkflow(String descripcionEstadoWorkflow) {
		this.descripcionEstadoWorkflow = descripcionEstadoWorkflow;
	}

	public String getIdDescripcionEstadoWorkflow() {
		return idDescripcionEstadoWorkflow;
	}

	public void setIdDescripcionEstadoWorkflow(
			String idDescripcionEstadoWorkflow) {
		this.idDescripcionEstadoWorkflow = idDescripcionEstadoWorkflow;
	}

	public List<Estado> getEstadosWorkflow() {
		return estadosWorkflow;
	}

	public void setEstadosWorkflow(List<Estado> estadosWorkflow) {
		this.estadosWorkflow = estadosWorkflow;
	}

	public OperacionTruncaFiltro() {
	}

}
