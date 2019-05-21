package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;


public class MicDetalleDescobroMedioPago {

	private TipoMedioPagoEnum tipoMedioPago;
	private Long cuentaRemanente;
	private TipoRemanenteEnum tipoRemanente;
	private Long numeroReferenciaMic;
	private MicResultado resultadoDescobroMedioPago;
	
	public String toString() {
		String str =  "[tipoMedioPago:" + tipoMedioPago.name() + "],"
				+ "[cuentaRemanente:" + String.valueOf(cuentaRemanente) + "],"
				+ "[tipoRemanente:" + String.valueOf(tipoRemanente.codigo()) + "],"
				+ "[numeroReferenciaMic:" + numeroReferenciaMic + "],"
				+ "[resultadoDescobroMedioPago:"+String.valueOf(resultadoDescobroMedioPago)+"]";
		return str;
	}
	
	public MicResultado getResultadoDescobroMedioPago() {
		return resultadoDescobroMedioPago;
	}

	public void setResultadoDescobroMedioPago(
			MicResultado resultadoDescobroMedioPago) {
		this.resultadoDescobroMedioPago = resultadoDescobroMedioPago;
	}

	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public Long getCuentaRemanente() {
		return cuentaRemanente;
	}

	public void setCuentaRemanente(Long cuentaRemanente) {
		this.cuentaRemanente = cuentaRemanente;
	}

	public TipoRemanenteEnum getTipoRemanente() {
		return tipoRemanente;
	}

	public void setTipoRemanente(TipoRemanenteEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}

	public Long getNumeroReferenciaMic() {
		return numeroReferenciaMic;
	}

	public void setNumeroReferenciaMic(Long numeroReferenciaMic) {
		this.numeroReferenciaMic = numeroReferenciaMic;
	}
	
}