package ar.com.telecom.shiva.negocio.servicios;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;

public interface IExcelLegajoServicio {

	public static final String EXCEL_ROWS_EXCEEDED = "Este archivo contiene los primeros N registros que cumplen con el criterio seleccionado. Debe refinar los criterios de búsqueda";
	public static final String DATA_NOT_FOUND = "No se encontaron datos para los criterios de busqueda ingresados.";
	public static final Integer MAX_ROWS_ALLOWED = 20000;
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss.SSSS";
    public static final String TIME_PATETRN = "HH:mm:ss.SSS";
    public static final SimpleDateFormat sdfForDate = new SimpleDateFormat(DATE_TIME_PATTERN);
    public static final SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
    
	public static final String POI_XLS = "excel";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	public static final String REG_EXP_SEPARATOR = ";";
	
	
	public HSSFWorkbook generarExcelDetalleLegajo(LegajoChequeRechazadoDto legajoChequeRechazadoDto) throws NegocioExcepcion;	
}
