package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.math.BigDecimal;
import java.util.List;

import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;


public class MicDetalleCobroFactura {

	private BigDecimal interesesGenerados;
	private BigDecimal interesesBonificadosRegulados;
	private BigDecimal interesesBonificadosNoRegulados;
	private List<MicResultado> resultadoApropiacion;
	
	public String toString() {
		String str = "[interesesGenerados:"+String.valueOf(interesesGenerados)+"],"
				+ "[interesesBonificadosRegulados:"+String.valueOf(interesesBonificadosRegulados)+"],"
				+ "[interesesBonificadosNoRegulados:"+String.valueOf(interesesBonificadosNoRegulados)+"]"
				+ "[resultadoApropiacion:"+String.valueOf(resultadoApropiacion)+"]";
		return str;
	}
	
	public BigDecimal getInteresesGenerados() {
		return interesesGenerados;
	}
	public void setInteresesGenerados(BigDecimal interesesGenerados) {
		this.interesesGenerados = interesesGenerados;
	}
	public BigDecimal getInteresesBonificadosRegulados() {
		return interesesBonificadosRegulados;
	}
	public void setInteresesBonificadosRegulados(
			BigDecimal interesesBonificadosRegulados) {
		this.interesesBonificadosRegulados = interesesBonificadosRegulados;
	}
	public BigDecimal getInteresesBonificadosNoRegulados() {
		return interesesBonificadosNoRegulados;
	}
	public void setInteresesBonificadosNoRegulados(
			BigDecimal interesesBonificadosNoRegulados) {
		this.interesesBonificadosNoRegulados = interesesBonificadosNoRegulados;
	}
	public List<MicResultado>  getResultadoApropiacion() {
		return resultadoApropiacion;
	}
	public void setResultadoApropiacion(List<MicResultado>  resultadoApropiacion) {
		this.resultadoApropiacion = resultadoApropiacion;
	}
	
}