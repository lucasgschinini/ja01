package ar.com.telecom.shiva.persistencia.bean;

public class VistaSoporteResultadoCobroCreditoDebito extends VistaSoporteResultadoBusqueda{
	
	private String idReferencia;
	private String operacionAnalista;
	
	public String getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getOperacionAnalista() {
		return operacionAnalista;
	}
	public void setOperacionAnalista(String operacionAnalista) {
		this.operacionAnalista = operacionAnalista;
	}
}
