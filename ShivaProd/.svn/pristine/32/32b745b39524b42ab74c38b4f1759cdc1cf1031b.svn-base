package ar.com.telecom.shiva.base.mapeadores;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;

public interface IOperacionMasivaRegistroMapeador {
	
	/**
	 * 
	 * @param registro
	 * @param modelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvMasRegistro mapRegistroEntrada(MicMasRegistro registro, ShvMasRegistro modelo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param modelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public REG mapRegistroSalida(ShvMasRegistro modelo) throws NegocioExcepcion;
	
	/**
	 * Inicializa el contadore de registros de operaciones masiva.
	 * Este mapeadro corre sobre un batch con un unico hilo
	 */
	public void inicializarContadorOperacionMasiva();

}
