package ar.com.telecom.shiva.base.registros.datos.entrada.decoradores;

import java.util.Date;

import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCobranzasGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaParametrosEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;

public class MicMasRegistro {

	public MicOperacionMasivaRegistroEntrada registro;
	public Date fechaArchivoSalida;
	public String nombreArchivoSalida;

	public MicMasRegistro() {
	}
	public MicMasRegistro(MicOperacionMasivaRegistroEntrada registro) {
		this.registro = registro;
		this.fechaArchivoSalida = registro.getFechaArchivoOperacionMasivaSalidaMic();
		this.nombreArchivoSalida = registro.getNombreArchivoOperacionMasivaSalidaMic();
	}
	
	public MicOperacionMasivaParametrosEntrada getParametrosGenerales() {
		return this.registro.getParametrosGenerales();
	}

	public MicOperacionMasivaDatosCobranzasGralesEntrada getDatosCobranzaGenerales() {
		return this.registro.getDatosCobranzaGenerales();
	}
	/**
	 * @return the fechaArchivoSalida
	 */
	public Date getFechaArchivoSalida() {
		return fechaArchivoSalida;
	}
	/**
	 * @param fechaArchivoSalida the fechaArchivoSalida to set
	 */
	public void setFechaArchivoSalida(Date fechaArchivoSalida) {
		this.fechaArchivoSalida = fechaArchivoSalida;
	}
	/**
	 * @return the nombreArchivoSalida
	 */
	public String getNombreArchivoSalida() {
		return nombreArchivoSalida;
	}
	/**
	 * @param nombreArchivoSalida the nombreArchivoSalida to set
	 */
	public void setNombreArchivoSalida(String nombreArchivoSalida) {
		this.nombreArchivoSalida = nombreArchivoSalida;
	}

}
