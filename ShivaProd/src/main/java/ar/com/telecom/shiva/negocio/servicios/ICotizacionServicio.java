package ar.com.telecom.shiva.negocio.servicios;

import java.io.File;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.negocio.dto.CotizacionDto;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;
import ar.com.telecom.shiva.presentacion.bean.filtro.CotizacionFiltro;

public interface ICotizacionServicio {
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws NegocioExcepcion
	 */
	public int procesarContenidoArchivoCotizacionesSap (File file) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws PersistenciaExcepcion
	 * @throws NegocioExcepcion
	 */
	public List<ShvParamCotizacion> listar(CotizacionFiltro filtro) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param cotizacionDto
	 * @return
	 * @throws PersistenciaExcepcion
	 * @throws NegocioExcepcion
	 */
	public ShvParamCotizacion crear(CotizacionDto cotizacionDto) throws NegocioExcepcion;
	
}
