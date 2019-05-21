package ar.com.telecom.shiva.negocio.dto.cobros;

import ar.com.telecom.shiva.base.dto.DTO;

@SuppressWarnings("serial")
public class MicResultadoDto extends DTO {
	
	private String resultadoInvocacion;
	private String codigoError;
	private String descripcionError;

	public String toString() {
		String str = "[resultadoInvocacion:" + String.valueOf(resultadoInvocacion) + "]"
			+ "[codigoError:" + String.valueOf(codigoError) + "]"
			+ "[descripcionError:" + String.valueOf(descripcionError) + "]";
		return str;
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
}
