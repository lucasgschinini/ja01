package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso;

public class Resultado {

	protected String resultado;
	protected String codigoError;
	protected String descripcionError;
    
	public Resultado() {}
    
	public String toString() {
		return "Resultado["+this.resultado+"-"+this.codigoError+":"+this.descripcionError+"]";
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
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
