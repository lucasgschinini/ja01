package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.List;

import ar.com.telecom.shiva.negocio.bean.TotalAcumuladoresTransacciones;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;

public class TransaccionesJsonResponse extends JsonResponse {

	public boolean transaccionesOK;
	
	private boolean simulacionBatchOK;
	
	private boolean simulacionEnBatch;

	private boolean imputable;

	private boolean simulablePorEstado;

	private EstadoCobroJsonResponse estado = new EstadoCobroJsonResponse();

	private List<CobroTransaccionDto> aaData;

	private TotalAcumuladoresTransacciones total = null;
//	private String totalTraslados;
//	private String totalBonificados;
//	private String totalReintegro;
	private String observacionesDocCap;
	
	/**
	 * @return the observacionesDocCap
	 */
	public String getObservacionesDocCap() {
		return observacionesDocCap;
	}

	/**
	 * @param observacionesDocCap the observacionesDocCap to set
	 */
	public void setObservacionesDocCap(String observacionesDocCap) {
		this.observacionesDocCap = observacionesDocCap;
	}

	public List<CobroTransaccionDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<CobroTransaccionDto> aaData) {
		this.aaData = aaData;
	}

	public boolean getTransaccionesOK() {
		return transaccionesOK;
	}

	public void setTransaccionesOK(boolean simulacionOnlineOK) {
		this.transaccionesOK = simulacionOnlineOK;
	}

	public boolean getSimulacionEnBatch() {
		return simulacionEnBatch;
	}

	public void setSimulacionEnBatch(boolean simulacionEnBatch) {
		this.simulacionEnBatch = simulacionEnBatch;
	}

	public boolean getSimulacionBatchOK() {
		return simulacionBatchOK;
	}

	public void setSimulacionBatchOK(boolean simulacionBatchOK) {
		this.simulacionBatchOK = simulacionBatchOK;
	}
	/**
	 * @return the imputable
	 */
	public boolean isImputable() {
		return imputable;
	}
	/**
	 * @param imputable the imputable to set
	 */
	public void setImputable(boolean imputable) {
		this.imputable = imputable;
	}

	/**
	 * @return the simulablePorEstado
	 */
	public boolean isSimulablePorEstado() {
		return simulablePorEstado;
	}

	/**
	 * @param simulablePorEstado the simulablePorEstado to set
	 */
	public void setSimulablePorEstado(boolean simulablePorEstado) {
		this.simulablePorEstado = simulablePorEstado;
	}

	/**
	 * @return the estado
	 */
	public EstadoCobroJsonResponse getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoCobroJsonResponse estado) {
		this.estado = estado;
	}

	/**
	 * @return the total
	 */
	public TotalAcumuladoresTransacciones getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(TotalAcumuladoresTransacciones total) {
		this.total = total;
	}
}
