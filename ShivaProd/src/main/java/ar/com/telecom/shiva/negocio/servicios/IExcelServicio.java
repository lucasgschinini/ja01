package ar.com.telecom.shiva.negocio.servicios;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;


/**
 * Servicio para el manejo de los cobros Onlines
 * 
 */
public interface IExcelServicio {
	
	public void generarExportacionDetalleCobro(HttpServletResponse response, CobroDto dto) throws NegocioExcepcion;
	
	public HSSFWorkbook generarExcelDetalleCobro(CobroDto dto) throws NegocioExcepcion;
	
}
 