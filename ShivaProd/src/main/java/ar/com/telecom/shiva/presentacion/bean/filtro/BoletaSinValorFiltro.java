package ar.com.telecom.shiva.presentacion.bean.filtro;


public class BoletaSinValorFiltro extends Filtro {

	private static final long serialVersionUID = 1L;
	
	private String idBoleta;
	
	private String boletasAAnularSelected;
	private String boletasAEnviarMailSelected;
	private String boletasAImprimirSelected;
	
	public BoletaSinValorFiltro(){}

	public BoletaSinValorFiltro(String idBoleta) {
		this.setIdBoleta(idBoleta);
	}
	
	public BoletaSinValorFiltro(String idEmpresa, String idSegmento) {
		super.setEmpresa(idEmpresa);
		super.setSegmento(idSegmento);
	}

	public String getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(String idBoleta) {
		this.idBoleta = idBoleta;
	}

	public String getBoletasAAnularSelected() {
		return boletasAAnularSelected;
	}

	public void setBoletasAAnularSelected(String boletasAAnularSelected) {
		this.boletasAAnularSelected = boletasAAnularSelected;
	}

	public String getBoletasAEnviarMailSelected() {
		return boletasAEnviarMailSelected;
	}

	public void setBoletasAEnviarMailSelected(String boletasAEnviarMailSelected) {
		this.boletasAEnviarMailSelected = boletasAEnviarMailSelected;
	}

	public String getBoletasAImprimirSelected() {
		return boletasAImprimirSelected;
	}

	public void setBoletasAImprimirSelected(String boletasAImprimirSelected) {
		this.boletasAImprimirSelected = boletasAImprimirSelected;
	}
	
	

}
