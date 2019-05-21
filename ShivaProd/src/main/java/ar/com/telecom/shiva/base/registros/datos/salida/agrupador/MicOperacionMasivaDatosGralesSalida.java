package ar.com.telecom.shiva.base.registros.datos.salida.agrupador;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaDatosGralesSalida extends REG {
	
	private BigDecimal importe;
	private MonedaEnum moneda;
	
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public MonedaEnum getMoneda() {
		return moneda;
	}
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
}
