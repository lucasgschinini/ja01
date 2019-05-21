package ar.com.telecom.shiva.negocio.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.reportes.impl.PoiXLSReportUtil;


public class ReportsUtils implements IReportsUtils{
	
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	public static final String REG_EXP_SEPARATOR = ";";

	
	private Resource jrxmlFilePath;

	protected Map<String,ReportUtilType> reportsTypes;
	
	private String name;
	
	private Collection<String> columnNames;
	private Collection<String> columnDescriptions;
	private List<SuperColumn> superColumnDescriptions;
	private Collection<String> titulosGrales;
	private Collection<String> columnDescriptionsGral;
	private Collection<String> columnNamesGral;
	
	private String xls = ".xls";
	private String pdf = ".pdf";

	public ReportsUtils(){
		reportsTypes = new HashMap<String,ReportUtilType>();
	}
    
    /**
	 * Escribe el reporte en el response
	 * @param response
	 * @param name
	 * @param dataSource
	 * @throws Exception 
     * @throws IOException 
	 */
	public void writeInResponse(String reportType, HttpServletResponse response, Collection dataSource, 
				String title, Map<String, Object> parameters) throws NegocioExcepcion {
		
		ReportUtilType reportUtilType = (ReportUtilType) reportsTypes.get(reportType);
		try {
			if (reportUtilType==null){
				throw new NegocioExcepcion();
			}
			
			ByteArrayOutputStream outputStream = reportUtilType.getOutputStream(dataSource,parameters);
			Traza.debug(this.getClass(),"Output stream of the report has been generated");
			
			response.setContentType(reportUtilType.getContentType());
			response.setContentLength(outputStream.size());
			if ( PDF.equals(reportType) ){
			    response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + title + pdf );
			}
			else if (POI_XLS.equals(reportType)){
		          response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + title + xls);
			}
			
			Traza.debug(this.getClass(),"Response Header has been set");
			FileCopyUtils.copy(outputStream.toByteArray(), response.getOutputStream());
			Traza.debug(this.getClass(),"The output stream generated has been copied to Response OutputSteam");			

		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage());
			throw new NegocioExcepcion(e);
		}
		
		
	}
	
	/**
	 * Ruta del archivo template para el reporte
	 * @return
	 */
	public Resource getJrxmlFilePath() {
		return jrxmlFilePath;
	}

	public void setJrxmlFilePath(Resource jrxmlFilePath) {
		this.jrxmlFilePath = jrxmlFilePath;
		setReportsTypes(jrxmlFilePath);
	}
	
	  /**
		 * Define los tipos de reporte que soporta
		 * @param jrxmlFilePath
		 */
		private void setReportsTypes(Resource jrxmlFilePath) {
			reportsTypes.put(POI_XLS, new PoiXLSReportUtil(this, jrxmlFilePath));
		}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	public void createStructure(String reportType) throws NegocioExcepcion {}

	public void writeReport(String reportType, Collection dataSource)  throws NegocioExcepcion{}

	public Collection<String> getTitulosGrales() {
		return titulosGrales;
	}

	public void setTitulosGrales(Collection<String> titulosGrales) {
		this.titulosGrales = titulosGrales;
	}

	public Collection<String> getColumnDescriptionsGral() {
		return columnDescriptionsGral;
	}

	public void setColumnDescriptionsGral(Collection<String> columnDescriptionsGral) {
		this.columnDescriptionsGral = columnDescriptionsGral;
	}

	public Collection<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(Collection<String> columnNames) {
		this.columnNames = columnNames;
	}

	public Collection<String> getColumnDescriptions() {
		return columnDescriptions;
	}

	public void setColumnDescriptions(Collection<String> columnDescriptions) {
		this.columnDescriptions = columnDescriptions;
	}

	public List<SuperColumn> getSuperColumnDescriptions() {
		return superColumnDescriptions;
	}
	
	public void setSuperColumnDescriptions(List<SuperColumn> superColumnDescriptions) {
		this.superColumnDescriptions = superColumnDescriptions;
	}

	public Collection<String> getColumnNamesGral() {
		return columnNamesGral;
	}

	public void setColumnNamesGral(Collection<String> columnNamesGral) {
		this.columnNamesGral = columnNamesGral;
	}

}
