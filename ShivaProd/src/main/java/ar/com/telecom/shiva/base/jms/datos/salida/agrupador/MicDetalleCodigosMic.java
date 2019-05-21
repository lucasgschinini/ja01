package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

public class MicDetalleCodigosMic {

	private Long idCobranza;

	public String toString() {
		String str = "[idCobranza:"+String.valueOf(idCobranza)+"]";
		return str;
	}
	
	public Long getIdCobranza() {
		return idCobranza;
	}

	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}
	
}
