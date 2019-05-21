package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;


public class MicDetalleInteresesGenerados {

	private BigDecimal interesesGeneradosNoRegulados;
	private BigDecimal interesesGeneradosRegulados;
	private BigDecimal interesesBonificadosNoRegulados;
	private BigDecimal interesesBonificadosRegulados;
	
	
	public String toString() {
		String str = "[interesesGeneradosNoRegulados:"+String.valueOf(interesesGeneradosNoRegulados)+"],"
				+ "[interesesGeneradosRegulados:"+String.valueOf(interesesGeneradosRegulados)+"],"
				+ "[interesesBonificadosNoRegulados:"+String.valueOf(interesesBonificadosNoRegulados)+"]"
				+ "[interesesBonificadosRegulados:"+String.valueOf(interesesBonificadosRegulados)+"],";
		return str;
	}


	public BigDecimal getInteresesGeneradosNoRegulados() {
		return interesesGeneradosNoRegulados;
	}


	public void setInteresesGeneradosNoRegulados(
			BigDecimal interesesGeneradosNoRegulados) {
		this.interesesGeneradosNoRegulados = interesesGeneradosNoRegulados;
	}


	public BigDecimal getInteresesGeneradosRegulados() {
		return interesesGeneradosRegulados;
	}


	public void setInteresesGeneradosRegulados(
			BigDecimal interesesGeneradosRegulados) {
		this.interesesGeneradosRegulados = interesesGeneradosRegulados;
	}


	public BigDecimal getInteresesBonificadosNoRegulados() {
		return interesesBonificadosNoRegulados;
	}


	public void setInteresesBonificadosNoRegulados(
			BigDecimal interesesBonificadosNoRegulados) {
		this.interesesBonificadosNoRegulados = interesesBonificadosNoRegulados;
	}


	public BigDecimal getInteresesBonificadosRegulados() {
		return interesesBonificadosRegulados;
	}


	public void setInteresesBonificadosRegulados(
			BigDecimal interesesBonificadosRegulados) {
		this.interesesBonificadosRegulados = interesesBonificadosRegulados;
	}
	
	
}