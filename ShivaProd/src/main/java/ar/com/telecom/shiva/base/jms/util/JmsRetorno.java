package ar.com.telecom.shiva.base.jms.util;

public class JmsRetorno {
	
	protected String indicadorError;
	protected String codigoError;
	protected String indicadorRegAdicionalesEncontrados;
	protected String cantRegistrosRetornados;
	protected String registrosAdicionales;
	
	public String toString() {
		String strRetorno = "[indicadorError:"+String.valueOf(indicadorError)+"],"
				+ "[codigoError:"+String.valueOf(codigoError)+"],"
				+ "[indicadorRegAdicionalesEncontrados:"+String.valueOf(indicadorRegAdicionalesEncontrados)+"],"
				+ "[cantRegistrosRetornados:"+String.valueOf(cantRegistrosRetornados)+"],"
				+ "[registrosAdicionales:"+String.valueOf(registrosAdicionales)+"]";
		
		return strRetorno;
	}

	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	public String getIndicadorError() {
		return indicadorError;
	}

	public void setIndicadorError(String indicadorError) {
		this.indicadorError = indicadorError;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getIndicadorRegAdicionalesEncontrados() {
		return indicadorRegAdicionalesEncontrados;
	}

	public void setIndicadorRegAdicionalesEncontrados(
			String indicadorRegAdicionalesEncontrados) {
		this.indicadorRegAdicionalesEncontrados = indicadorRegAdicionalesEncontrados;
	}

	public String getCantRegistrosRetornados() {
		return cantRegistrosRetornados;
	}

	public void setCantRegistrosRetornados(String cantRegistrosRetornados) {
		this.cantRegistrosRetornados = cantRegistrosRetornados;
	}

	public String getRegistrosAdicionales() {
		return registrosAdicionales;
	}

	public void setRegistrosAdicionales(String registrosAdicionales) {
		this.registrosAdicionales = registrosAdicionales;
	}
	
}
