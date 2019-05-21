package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;


/**
 * Servicio para el manejo de los cobros Onlines
 * 
 */
public interface IExcelBusquedaCobroServicio {
	
	public void generarExportacionBusquedaCobros(HttpServletResponse response, List<CobroDto> listaCobros) throws NegocioExcepcion;
	
}
 