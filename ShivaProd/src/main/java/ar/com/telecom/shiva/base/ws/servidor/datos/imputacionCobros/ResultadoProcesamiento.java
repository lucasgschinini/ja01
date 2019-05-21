package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros;

import java.io.Serializable;

public class ResultadoProcesamiento extends Object implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String resultadoImputacion;
	private String codigoError;
	private String descripcionError;
	
	public String toString() {
		return "Resultado["+this.resultadoImputacion+"-"+this.codigoError+":"+this.descripcionError+"]";
	}
	
	public String getResultadoImputacion() {
		return resultadoImputacion;
	}
	public void setResultadoImputacion(String resultadoImputacion) {
		this.resultadoImputacion = resultadoImputacion;
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
