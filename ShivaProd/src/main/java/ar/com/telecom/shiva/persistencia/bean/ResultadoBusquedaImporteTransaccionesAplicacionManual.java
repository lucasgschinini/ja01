package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;

public class ResultadoBusquedaImporteTransaccionesAplicacionManual {

	
	private String numTransaccionFormateado;
	private BigDecimal importe;
	
	public ResultadoBusquedaImporteTransaccionesAplicacionManual() {
		
	}

	public String getNumTransaccionFormateado() {
		return numTransaccionFormateado;
	}

	public void setNumTransaccionFormateado(String numTransaccionFormateado) {
		this.numTransaccionFormateado = numTransaccionFormateado;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

}
