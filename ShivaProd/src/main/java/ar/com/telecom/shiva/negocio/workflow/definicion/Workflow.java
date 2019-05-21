package ar.com.telecom.shiva.negocio.workflow.definicion;

import java.util.List;

public class Workflow {
	private TipoWorkflow tipoWorkflow;
	private List<Transicion> transiciones;
	
	public Workflow() {
	}
	public TipoWorkflow getTipoWorkflow() {
		return tipoWorkflow;
	}
	public void setTipoWorkflow(TipoWorkflow tipoWorkflow) {
		this.tipoWorkflow = tipoWorkflow;
	}
	
	public String getNombre() {
		return tipoWorkflow.name();
	}
	public String getDescripcion() {
		return tipoWorkflow.descripcion();
	}

	public List<Transicion> getTransiciones() {
		return transiciones;
	}
	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
	}
}
