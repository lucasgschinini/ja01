package ar.com.telecom.shiva.base.registros.datos;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;

@SuppressWarnings("serial")
public class MicOperacionMasivaCabeceraPie extends REG {
	
	private TipoRegistroEnum tipoRegistro;
	private Long cantidadRegistros;
	private Date fechaProcesamiento;
	
	public TipoRegistroEnum getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistroEnum tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

}
