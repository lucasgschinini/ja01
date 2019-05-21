package ar.com.telecom.shiva.negocio.dto.cobros;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;

@SuppressWarnings("serial")
public class MicDetalleDescobroMedioPagoDto extends DTO {
	
	private TipoMedioPagoEnum tipoMedioPago;
	private Long cuentaRemanente;
	private TipoRemanenteEnum tipoRemanente;
	private Long numeroReferenciaMic;
	private MicResultadoDto resultadoDescobroMedioPago;
	
	public String toString() {
		String str =  "[tipoMedioPago:" + String.valueOf(tipoMedioPago != null ?tipoMedioPago.name():null) + "],"
				+ "[cuentaRemanente:" + String.valueOf(cuentaRemanente) + "],"
				+ "[tipoRemanente:" + String.valueOf(tipoRemanente != null ?tipoRemanente.codigo():null) + "],"
				+ "[numeroReferenciaMic:" + numeroReferenciaMic + "],"
				+ "[resultadoDescobroMedioPago:"+String.valueOf(resultadoDescobroMedioPago)+"]";
		return str;
	}
	
	public MicResultadoDto getResultadoDescobroMedioPago() {
		return resultadoDescobroMedioPago;
	}

	public void setResultadoDescobroMedioPago(
			MicResultadoDto resultadoDescobroMedioPago) {
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
