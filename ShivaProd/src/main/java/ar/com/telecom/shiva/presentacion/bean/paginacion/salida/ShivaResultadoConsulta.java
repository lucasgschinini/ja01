package ar.com.telecom.shiva.presentacion.bean.paginacion.salida;

import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;

public class ShivaResultadoConsulta {
	
	private OkNokEnum resultadoConsulta;
	private String codigoError;
	private String descripcionError;
	
	public String toString() {
		String str = "[resultadoInvocacion:"+String.valueOf(resultadoConsulta.getDescripcion())+"],"
				+ "[codigoError:"+String.valueOf(codigoError)+"],"
				+ "[descripcionError:"+String.valueOf(descripcionError)+"]";
		
		return str;
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

	public OkNokEnum getResultadoConsulta() {
		return resultadoConsulta;
	}
	public void setResultadoConsulta(OkNokEnum resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}
}
