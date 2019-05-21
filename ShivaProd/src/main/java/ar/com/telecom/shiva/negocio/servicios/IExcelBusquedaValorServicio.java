package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;


/**
 * Servicio para el manejo de los cobros Onlines
 * 
 */
public interface IExcelBusquedaValorServicio {
	
	public void generarExportacionBusquedaValores(HttpServletResponse response, List<ValorDto> listaValores) throws NegocioExcepcion;
	
}
 