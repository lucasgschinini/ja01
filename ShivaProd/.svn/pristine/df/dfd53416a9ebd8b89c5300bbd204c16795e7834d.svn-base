package ar.com.telecom.shiva.negocio.reportes;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;



public interface IReportsUtils {
	
	public static final String EXCEL_ROWS_EXCEEDED = "Este archivo contiene los primeros N registros que cumplen con el criterio seleccionado. Debe refinar los criterios de búsqueda";
	public static final String DATA_NOT_FOUND = "No se encontaron datos para los criterios de busqueda ingresados.";
	public static final Integer MAX_ROWS_ALLOWED = 20000;
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss.SSSS";
    public static final String TIME_PATETRN = "HH:mm:ss.SSS";
    public static final SimpleDateFormat sdfForDate = new SimpleDateFormat(DATE_TIME_PATTERN);
    public static final SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
    
	public static final String POI_XLS = "excel";
	public static final String PDF = "pdf";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	
	void createStructure(String reportType) throws NegocioExcepcion;
	
	void writeReport(String reportType, Collection dataSource) throws NegocioExcepcion;
	void writeInResponse(String reportType, HttpServletResponse response, 
			Collection dataSource, String title, 
			Map<String, Object> parameters) throws NegocioExcepcion;
	
	void setJrxmlFilePath(Resource jrxmlFilePath);
	Resource getJrxmlFilePath();
	Collection<String> getColumnNames();
	Collection<String> getColumnDescriptions();
	List<SuperColumn> getSuperColumnDescriptions();
	Collection<String> getTitulosGrales();
	Collection<String> getColumnDescriptionsGral();
	Collection<String> getColumnNamesGral();
}
