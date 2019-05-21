package ar.com.telecom.shiva.base.jms.datos.salida.agrupador;

import java.util.Date;

public class MicInformacionAdicionalDacota {

	protected Date fechaVencimientoMora;
	protected String indicadorPeticionCorte;
	protected String codigoTarifa;
	
	public Date getFechaVencimientoMora() {
		return fechaVencimientoMora;
	}
	public void setFechaVencimientoMora(Date fechaVencimientoMora) {
		this.fechaVencimientoMora = fechaVencimientoMora;
	}
	public String getIndicadorPeticionCorte() {
		return indicadorPeticionCorte;
	}
	public void setIndicadorPeticionCorte(String indicadorPeticionCorte) {
		this.indicadorPeticionCorte = indicadorPeticionCorte;
	}
	public String getCodigoTarifa() {
		return codigoTarifa;
	}
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}
	
	
}
