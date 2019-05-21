package ar.com.telecom.shiva.base.mapeadores;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosRespuestaGeneralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivacAgrupacionCamposEntradaEnum;
import ar.com.telecom.shiva.base.registros.util.definicion.FormatoRegistros;


public abstract class MapeadorREG {
	private FormatoRegistros defaultFormatoRegistro;
	
	/**
	 * REG --> String msg a enviar
	 * @param REG
	 * @return
	 */
	public abstract String serializar(REG reg) throws NegocioExcepcion;
	
	/** 
	 * String --> REG 
	 * @param reg recibido
	 * @return
	 */
	public abstract REG deserializar(REG reg, String campos[]) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param reg
	 * @param agrupador
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	public abstract MicOperacionMasivaDatosRespuestaGeneralesEntrada deserializar(
			MicOperacionMasivaDatosRespuestaGeneralesEntrada reg,
			MicOperacionMasivacAgrupacionCamposEntradaEnum agrupador,
			String campos[]
	) throws NegocioExcepcion;
		
	
	/*****************************************************************
	 * Getters / Setters
	 ****************************************************************/
	
	public FormatoRegistros getDefaultFormatoRegistro() {
		return defaultFormatoRegistro;
	}

	public void setDefaultFormatoRegistro(FormatoRegistros defaultFormatoRegistro) {
		this.defaultFormatoRegistro = defaultFormatoRegistro;
	}
}
