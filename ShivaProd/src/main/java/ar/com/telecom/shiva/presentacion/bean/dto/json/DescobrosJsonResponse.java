package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;

public class DescobrosJsonResponse extends JsonResponse {

	private Long idReversion;
	private Long idOperacion;
	private Long idCobro;
	private boolean primerDescobro;
	private List<DescobroDto> aaData;
	private String errorMensaje;
	private ArrayList<ErrorJson> errores;
	private DescobroDto descobro;
	private List<SelectOptionJsonResponse> listaCopropietarios;
	private List<SelectOptionJsonResponse> listaSistemaTransaccion;
	private EstadoCobroJsonResponse estado = new EstadoCobroJsonResponse();
	private String edicionSegunEstadoMarca = "";
	private boolean esPerfilSupervisorCobranza = false;
	private boolean habilitarBtnSimular = false;
	private boolean transaccionesOK;
	private boolean booleanIdPadreDescobroOtro = false;
	
	public List<DescobroDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<DescobroDto> aaData) {
		this.aaData = aaData;
	}

	public String getErrorMensaje() {
		return errorMensaje;
	}

	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}
	
	public ArrayList<ErrorJson> getErrores() {
		return errores;
	}

	public void setErrores(ArrayList<ErrorJson> errores) {
		this.errores = errores;
	}
	
	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Long getIdReversion() {
		return idReversion;
	}

	public void setIdReversion(Long idReversion) {
		this.idReversion = idReversion;
	}

	public boolean isPrimerDescobro() {
		return primerDescobro;
	}

	public void setPrimerDescobro(boolean primerDescobro) {
		this.primerDescobro = primerDescobro;
	}

	public DescobroDto getDescobro() {
		return descobro;
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
	
	public void setDescobro(DescobroDto descobro) {
		this.descobro = descobro;
	}

	public List<SelectOptionJsonResponse> getListaCopropietarios() {
		return listaCopropietarios;
	}

	public void setListaCopropietarios(List<SelectOptionJsonResponse> listaCopropietarios) {
		this.listaCopropietarios = listaCopropietarios;
	}

	/**
	 * @return the listaSistemaTransaccion
	 */
	public List<SelectOptionJsonResponse> getListaSistemaTransaccion() {
		return listaSistemaTransaccion;
	}

	/**
	 * @param listaSistemaTransaccion the listaSistemaTransaccion to set
	 */
	public void setListaSistemaTransaccion(List<SelectOptionJsonResponse> listaSistemaTransaccion) {
		this.listaSistemaTransaccion = listaSistemaTransaccion;
	}
	
	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	
	/**
	 * @return the esPerfilSupervisorCobranza
	 */
	public boolean isEsPerfilSupervisorCobranza() {
		return esPerfilSupervisorCobranza;
	}

	/**
	 * @param esPerfilSupervisorCobranza the esPerfilSupervisorCobranza to set
	 */
	public void setEsPerfilSupervisorCobranza(boolean esPerfilSupervisorCobranza) {
		this.esPerfilSupervisorCobranza = esPerfilSupervisorCobranza;
	}
	
	/**
	 * @return the edicionSegunEstadoMarca
	 */
	public String getEdicionSegunEstadoMarca() {
		return edicionSegunEstadoMarca;
	}

	/**
	 * @param edicionSegunEstadoMarca the edicionSegunEstadoMarca to set
	 */
	public void setEdicionSegunEstadoMarca(String edicionSegunEstadoMarca) {
		this.edicionSegunEstadoMarca = edicionSegunEstadoMarca;
	}

	/**
	 * @return the transaccionesOK
	 */
	public boolean isTransaccionesOK() {
		return transaccionesOK;
	}

	/**
	 * @param transaccionesOK the transaccionesOK to set
	 */
	public void setTransaccionesOK(boolean transaccionesOK) {
		this.transaccionesOK = transaccionesOK;
	}
	
	public boolean getHabilitarBtnSimular() {
		return habilitarBtnSimular;
	}

	public void setHabilitarBtnSimular(boolean habilitarBtnSimular) {
		this.habilitarBtnSimular = habilitarBtnSimular;
	}

	public boolean getBooleanIdPadreDescobroOtro() {
		return booleanIdPadreDescobroOtro;
	}

	public void setBooleanIdPadreDescobroOtro(boolean booleanIdPadreDescobroOtro) {
		this.booleanIdPadreDescobroOtro = booleanIdPadreDescobroOtro;
	}
}