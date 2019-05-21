package ar.com.telecom.shiva.negocio.dto.cobros;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;

@SuppressWarnings("serial")
public class MicTransaccionGeneracionCargosRespuestaDto extends DTO {
	
	private String idOperacionTransaccion;
	private Integer idTransaccion;
	private TipoInvocacionEnum tipoInvocacion;
	private String resultadoInvocacion;
	private String codigoError;
	private String descripcionError;
	private BigDecimal interesesGeneradosNoRegulados;
	private BigDecimal interesesGeneradosRegulados;
	private BigDecimal interesesBonificadosNoRegulados;
	private BigDecimal interesesBonificadosRegulados;
	
	public String toString() {
		String resultado = this.idOperacionTransaccion + "," 
				+ this.idTransaccion + "," 
				+ this.resultadoInvocacion+ "," 
				+ this.codigoError + "," 
				+ this.descripcionError + ","
				+ this.interesesGeneradosNoRegulados + "," 
				+ this.interesesGeneradosRegulados + "," 
				+ this.interesesBonificadosNoRegulados + "," 
				+ this.interesesBonificadosRegulados; 
		return resultado;
	}
	
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}
	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}
	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}
	
	public String getResultadoInvocacion() {
		return resultadoInvocacion;
	}
	public void setResultadoInvocacion(String resultadoInvocacion) {
		this.resultadoInvocacion = resultadoInvocacion;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	public String getDescripcionError() {
		return descripcionError;
	}
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	public BigDecimal getInteresesBonificadosRegulados() {
		return interesesBonificadosRegulados;
	}
	public void setInteresesBonificadosRegulados(
			BigDecimal interesesBonificadosRegulados) {
		this.interesesBonificadosRegulados = interesesBonificadosRegulados;
	}
	public BigDecimal getInteresesBonificadosNoRegulados() {
		return interesesBonificadosNoRegulados;
	}
	public void setInteresesBonificadosNoRegulados(
			BigDecimal interesesBonificadosNoRegulados) {
		this.interesesBonificadosNoRegulados = interesesBonificadosNoRegulados;
	}
	public Integer getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}


	public BigDecimal getInteresesGeneradosNoRegulados() {
		return interesesGeneradosNoRegulados;
	}


	public void setInteresesGeneradosNoRegulados(
			BigDecimal interesesGeneradosNoRegulados) {
		this.interesesGeneradosNoRegulados = interesesGeneradosNoRegulados;
	}


	public BigDecimal getInteresesGeneradosRegulados() {
		return interesesGeneradosRegulados;
	}


	public void setInteresesGeneradosRegulados(
			BigDecimal interesesGeneradosRegulados) {
		this.interesesGeneradosRegulados = interesesGeneradosRegulados;
	}
}
