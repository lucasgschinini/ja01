package ar.com.telecom.shiva.negocio.servicios;

import java.text.ParseException;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ReportePerfilesExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.ReportePerfilesAuditoriaEntrada;

public interface IReportePerfilesAuditoriaServicio {

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ShivaExcepcion
	 * @throws PersistenciaExcepcion
	 * @throws ParseException
	 * @throws ReportePerfilesExcepcion
	 */
	public List<ReportePerfilesAuditoriaEntrada> procesarArchivoEntrada() throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion, ParseException, ReportePerfilesExcepcion;

	/**
	 * 
	 * @param parametro
	 * @return
	 * @throws NegocioExcepcion
	 * @throws ShivaExcepcion
	 * @throws PersistenciaExcepcion
	 * @throws ParseException
	 */
	public int generarReporte(String[] parametro) throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion, ParseException;
	
}
