package ar.com.telecom.shiva.base.registros.util.definicion;

public class CampoAEnviarREG {
	
	protected String nombreCampo;
	protected String valorCampo;
	
	public CampoAEnviarREG(String nombreCampo, String valorCampo) {
		this.nombreCampo=nombreCampo;
		this.valorCampo=valorCampo;
	}
	
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getValorCampo() {
		return valorCampo;
	}
	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}	
}
