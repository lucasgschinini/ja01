package ar.com.telecom.shiva.negocio.servicios;

import java.text.ParseException;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;

public interface IProcesarEntradaDetalleAplicacionesManualesServicio {
	/**
	 * Se encarga de levantar los archivos de Entrada.
	 * De validar los nombre de los archivos y filtrar los directorios
	 * Y una ves finalizado el proceso mover el archivo a Historico
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ar.com.telecom.shiva.base.excepciones.ShivaExcepcion 
	 * @throws PersistenciaExcepcion 
	 * @throws ParseException 
	 */
	public void procesarArchivosEntrada() throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion, ParseException;
	
	
}
