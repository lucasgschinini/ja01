package ar.com.telecom.shiva.negocio.reportes;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Map;

import org.springframework.core.io.Resource;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public abstract class ReportUtilType {
	
	protected IReportsUtils reportsUtils;
	private  Resource jrxmlFilePath;
	
	public ReportUtilType(IReportsUtils reportsUtils, Resource jrxmlFilePath){
		this.jrxmlFilePath = jrxmlFilePath;
		this.reportsUtils = reportsUtils;
	}
	
	public abstract ByteArrayOutputStream getOutputStream(Collection dataSource, Map<String, Object> parameters) throws NegocioExcepcion;
	public abstract void createStructure() throws NegocioExcepcion;
	public abstract void writeReport(Collection dataSource) throws NegocioExcepcion;
	public abstract String getFileExtension();
	public abstract String getContentType();
	
	/**
	 * Ruta del template del reporte
	 * @return
	 */
	public Resource getJrxmlFilePath() {
		return jrxmlFilePath;
	}

	public void setJrxmlFilePath(Resource jrxmlFilePath) {
		this.jrxmlFilePath = jrxmlFilePath;
	}
}
