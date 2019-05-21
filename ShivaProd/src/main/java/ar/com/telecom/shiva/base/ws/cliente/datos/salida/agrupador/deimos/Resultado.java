package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos;


public class Resultado {

	protected DocumentoSalida documento;
	protected ResultadoApropiacionDocumento resultadoApropiacionDocumento;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	public DocumentoSalida getDocumento() {
		return documento;
	}
	public void setDocumento(DocumentoSalida documento) {
		this.documento = documento;
	}
	public ResultadoApropiacionDocumento getResultadoApropiacionDocumento() {
		return resultadoApropiacionDocumento;
	}
	public void setResultadoApropiacionDocumento(
			ResultadoApropiacionDocumento resultadoApropiacionDocumento) {
		this.resultadoApropiacionDocumento = resultadoApropiacionDocumento;
	} 
	
}