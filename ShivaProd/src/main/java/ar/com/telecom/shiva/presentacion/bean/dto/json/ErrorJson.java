package ar.com.telecom.shiva.presentacion.bean.dto.json;


public class ErrorJson extends JsonResponse {

	private String campoError;
	private String descripcionError;

	public ErrorJson(String campoError, String descripcionError){
		this.campoError = campoError;
		this.descripcionError = descripcionError;
	}
	
	public void setCampoError(String campoError){
		this.campoError = campoError;
	}
	
	public void setDescripcionError(String descripcionError){
		this.descripcionError = descripcionError;
	}
	
	public String getCampoError(){
		return this.campoError;
	}
	
	public String getDescripcionError(){
		return this.descripcionError;
	}
}