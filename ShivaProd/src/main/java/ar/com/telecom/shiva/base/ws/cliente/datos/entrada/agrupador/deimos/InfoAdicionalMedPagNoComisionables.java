package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos;

import java.math.BigDecimal;

public class InfoAdicionalMedPagNoComisionables {

	protected BigDecimal importe;
	protected Long codigoTipoMedioPago;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getCodigoTipoMedioPago() {
		return codigoTipoMedioPago;
	}
	public void setCodigoTipoMedioPago(Long codigoTipoMedioPago) {
		this.codigoTipoMedioPago = codigoTipoMedioPago;
	}
	
}