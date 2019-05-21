package ar.com.telecom.shiva.negocio.reportes.impl;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.Resource;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.reportes.IReportsUtils;
import ar.com.telecom.shiva.negocio.reportes.ReportUtilType;

public class PoiXLSReportUtil extends ReportUtilType {

	private HSSFCellStyle estiloCurrency;
	private HSSFCellStyle estiloCurrencyDolar;
	
	public PoiXLSReportUtil(IReportsUtils reportsUtils, Resource jrxmlFilePath) {
		super(reportsUtils, jrxmlFilePath);
	}

	@Override
	public ByteArrayOutputStream getOutputStream(Collection dataSource, Map<String, Object> parameters)
			throws NegocioExcepcion {
		
		ByteArrayOutputStream salida = new ByteArrayOutputStream();
		
		try {
		    Date start = new Date();
	        HSSFWorkbook workbook = createBook(dataSource, parameters);
			
			Traza.debug(this.getClass(),"Before write report to ByteArrayOutputStream.");
			workbook.write(salida);
			Traza.debug(this.getClass(),"After write report to ByteArrayOutputStream.");
			
			Date end = new Date();
			
	        Date difference = Utilidad.differenceBetweenDates(start, end);
            Traza.advertencia(this.getClass(),"Tiempo de ejecucion de HSSFWorkbook:" + IReportsUtils.sdfForDateDifference.format(difference));
            
		} catch (IOException e1) {
			Traza.error(this.getClass(),"Error en IO", e1);
			throw new NegocioExcepcion(e1);
		} catch (ParseException e) {
            Traza.error(this.getClass(),"Error en differenceBetweenDates", e);
            throw new NegocioExcepcion(e);
        }
        
		return salida;
	}

	protected HSSFWorkbook createBook(Collection dataSource, Map<String, Object> parameters){
		int rowNumber = 0;
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		Traza.debug(this.getClass(),"HSSFWorkbook has been generated.");
		
		estiloCurrency = workbook.createCellStyle();
		HSSFDataFormat df = workbook.createDataFormat();
		estiloCurrency.setDataFormat(df.getFormat("$#,##0.00"));
	
		estiloCurrencyDolar = workbook.createCellStyle();
		estiloCurrencyDolar.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFSheet sheet = workbook.createSheet();
		Traza.debug(this.getClass(),"HSSFSheet has been generated.");
		
		boolean isExceded = false;
		int excelMaxRows = IReportsUtils.MAX_ROWS_ALLOWED;
		
		rowNumber = fillSuperHeaders(rowNumber, workbook, sheet, parameters);
		
		rowNumber = fillHeaders(rowNumber, workbook, sheet);
		if (dataSource==null || dataSource.isEmpty()){
			fillCollectionEmptyMessage(rowNumber, sheet);
		}else{
			isExceded = fillInformation(dataSource, rowNumber, sheet,excelMaxRows);
		}
		
		//Si está excedido, pongo la lista de columnas menos 1 y ajusto la primera fila.
		if (isExceded) {
		    boolean error = false;
	        HSSFWorkbook workbookAux = workbook;
	        try {
	            autoAdjustment(reportsUtils.getColumnDescriptions().size()-1,sheet,true);
	        } catch (Throwable e) {
	            Traza.error(this.getClass(),"Method: adjustColumnSize", e);
	            error = true;
	        } finally {
	            if (error) {
	                workbook = workbookAux;
	            }
	        }
		} else {
		    autoAdjustment(reportsUtils.getColumnDescriptions().size(),sheet);
		}
		
		//Despues de ajustar las columnas, si está excedido, se coloca el mensaje y despues se fusionan las columnas
		if(isExceded){
            fillOverflowMessage(excelMaxRows,workbook,sheet);
        }
		
		
		return workbook;
	}

	protected void fillOverflowMessage(int rowNumber, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(rowNumber);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(IReportsUtils.EXCEL_ROWS_EXCEEDED);
		cell.setCellStyle(style);
		int lastColumnIndex = reportsUtils.getColumnDescriptions().size()-1;
		sheet.addMergedRegion(new CellRangeAddress(rowNumber,rowNumber,0,lastColumnIndex));
	}
	
	/**
	 * Por un defecto, se autoajusta las columnas de la primera fila
	 * @param number
	 * @param sheet
	 * @param firstRow
	 */
	protected void autoAdjustment(int number, HSSFSheet sheet, boolean firstRow) {
	    if (firstRow) {
	        HSSFRow row = sheet.getRow(0);
	        for (int i = 0; i < number; i++) {
	            sheet.autoSizeColumn(i);
	        }
	    } else {
	        for (int i = 0; i < number; i++) {
	            sheet.autoSizeColumn((short) i);
	         }
	    }
    }
	
	protected void autoAdjustment(int number, HSSFSheet sheet) {
		this.autoAdjustment(number, sheet, false);
	}
	/**
	 * 
	 * @param dataSource
	 * @param rowNumber
	 * @param sheet
	 * @param rowLimit
	 * @return si se ha excedido de filas
	 */
	protected boolean fillInformation(Collection dataSource, int rowNumber,
			HSSFSheet sheet,Integer rowLimit) {
		//completo la tabla
		Iterator iterator = dataSource.iterator();
		while (iterator.hasNext() && rowNumber<rowLimit) {
			Object data = (Object) iterator.next();
			if (data!=null){
				HSSFRow row = sheet.createRow(rowNumber++);
				Iterator it = reportsUtils.getColumnNames().iterator();
				int colNumber = 0;
				while (it.hasNext()) {
					String name = ((String) it.next()).trim();
					HSSFCell cell = row.createCell(colNumber++);
					setValue(data, name, cell);
				}
			}
		}
		return iterator.hasNext();
	}

	protected int fillHeaders(int rowNumber, HSSFWorkbook workbook,
			HSSFSheet sheet) {
		//agrego los titulos
		HSSFRow rowT = sheet.createRow(rowNumber++);
		@SuppressWarnings("rawtypes")
		Iterator ite = reportsUtils.getColumnDescriptions().iterator();
		
		int colNumber1 = 0;
		while (ite.hasNext()) {

			String name = (String) ite.next();
			HSSFCell cell = rowT.createCell(colNumber1++);
			cell.setCellStyle(styleTitles(workbook));
			cell.setCellValue(name);
		}
		return rowNumber;
	}

	protected int fillSuperHeaders(int rowNumber, HSSFWorkbook workbook,
			HSSFSheet sheet, Map<String, Object> parameters) {
		
		if ((parameters != null && !parameters.isEmpty()) 
				&& Validaciones.isCollectionNotEmpty(reportsUtils.getTitulosGrales())
				&& Validaciones.isCollectionNotEmpty(reportsUtils.getColumnDescriptionsGral())
				&& Validaciones.isCollectionNotEmpty(reportsUtils.getColumnNamesGral())) 
		{
			int lastColumnIndex = reportsUtils.getColumnDescriptions().size()-1;
			
			String claseSuperHeaders = (String)parameters.get("claseSuperHeaders");
			if (!Validaciones.isNullOrEmpty(claseSuperHeaders)) {
				Object data = (Object) parameters.get(claseSuperHeaders);
				if (!Validaciones.isObjectNull(data)) {
					HSSFRow row = sheet.createRow(rowNumber++);
					
					HSSFCell cell = row.createCell(0);
					sheet.addMergedRegion(new CellRangeAddress(rowNumber-1,rowNumber-1,0,lastColumnIndex));
					cell.setCellStyle(styleTitles(workbook));
					cell.setCellValue((reportsUtils.getTitulosGrales().toArray()[0]).toString());
					
					int firstRow = rowNumber;
					for (String description : reportsUtils.getColumnDescriptionsGral()) {
						row = sheet.createRow(rowNumber++);
						cell = row.createCell(0);
						cell.setCellValue(description);
					}
					for (String name : reportsUtils.getColumnNamesGral()) {
						row = sheet.getRow(firstRow++);
						cell = row.createCell(1);
						setValue(data, name, cell);
					}	
					row = sheet.createRow(rowNumber++);
					
					row = sheet.createRow(rowNumber++);
					cell = row.createCell(0);
					sheet.addMergedRegion(new CellRangeAddress(rowNumber-1,rowNumber-1,0,lastColumnIndex));
					cell.setCellStyle(styleTitles(workbook));
					cell.setCellValue((reportsUtils.getTitulosGrales().toArray()[1]).toString());
				}
			}
		}
		return rowNumber;
	}

	public void setValue(Object data, String name, HSSFCell cell) {
		try {
			PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(data, name);
			Object value = getValue(data, name);
			
			if (char.class.equals(descriptor.getPropertyType())){
				if (value == null){
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					cell.setCellValue((String.valueOf(value)).trim());
				}
			}
			if (String.class.equals(descriptor.getPropertyType())){
				if (value == null){
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					String valor = ((String)value).trim();
					if (valor.startsWith(Utilidad.PESOS_CHAR)) {
						cell.setCellStyle(estiloCurrency);
						BigDecimal bigDecimal = Utilidad.stringToBigDecimal(valor); 
						cell.setCellValue(bigDecimal.doubleValue());
						
					} else if (valor.startsWith(Utilidad.DOLAR_CHAR)) {
						cell.setCellStyle(estiloCurrencyDolar);
						cell.setCellValue(valor);
						
					} else {
						cell.setCellValue(valor);
					}
					
				}
			}
			if (long.class.equals(descriptor.getPropertyType())){
				if (Validaciones.isObjectNull(value)) {
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					cell.setCellValue((Long)value);
				}
			}
			if (Integer.class.equals(descriptor.getPropertyType())){
				if (Validaciones.isObjectNull(value)) {
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					cell.setCellValue((Integer) value);
				}
			}
			if (Long.class.equals(descriptor.getPropertyType())){
				if (Validaciones.isObjectNull(value)) {
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					cell.setCellValue((Long)value);
				}
			}			
			if (Date.class.equals(descriptor.getPropertyType())){
				Date date =(Date)value;
				if(date!=null){
					cell.setCellValue(Utilidad.formatDate(date));
				}else{
					cell.setCellValue(Utilidad.EMPTY_STRING);
				}
			}
			if (Double.class.equals(descriptor.getPropertyType())){
				if (Validaciones.isObjectNull(value)) {
					cell.setCellValue(Utilidad.EMPTY_STRING);
				} else {
					cell.setCellValue((Double)value);
				}
			}
			if (BigDecimal.class.equals(descriptor.getPropertyType())){
			
				cell.setCellStyle(estiloCurrency);
				
				if (Validaciones.isObjectNull(value)) {
					BigDecimal bd = new BigDecimal(0);
					bd.setScale(2);
					cell.setCellValue(bd.doubleValue());
				} else {
					cell.setCellValue(((BigDecimal)value).doubleValue());
				}
			}
			
		} catch(NullPointerException n){
            Traza.error(this.getClass(),"Se ha intentado grabar un valor vacio para el Campo:"+name, n);
        } catch (Exception e){
			Traza.error(this.getClass(),"Ocurrio un error al asignar valor para el Campo:"+name, e);
		}
	}	
	
	protected Object getValue(Object data, String name){
		Object value = null;
		try {
			value = PropertyUtils.getProperty(data, name);
		} catch(NullPointerException n){
            Traza.error(this.getClass(),n.getMessage(), n);
        } catch (Exception e){
			Traza.error(this.getClass(),e.getMessage(), e);
		}
		return value;
	}
	
	@Override
	public String getContentType() {
		return reportsUtils.XLS_CONTENT_TYPE;
	}
	
	@SuppressWarnings("static-access")
	private HSSFCellStyle styleTitles(HSSFWorkbook workbook){
		//Estilos
		HSSFFont fuente = workbook.createFont();
		fuente.setFontHeightInPoints((short)11);
		fuente.setFontName(fuente.FONT_ARIAL);
		fuente.setColor(HSSFColor.WHITE.index);
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		HSSFCellStyle estiloCelda = workbook.createCellStyle();
		estiloCelda.setWrapText(true);
		estiloCelda.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		estiloCelda.setFont(fuente);
		
		//#EBEFF9 = RGB (235,239,249)
//		estiloCelda.setFillForegroundColor(setColor(workbook,(byte)235,(byte)239,(byte)249).getIndex());
		estiloCelda.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
		estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		return estiloCelda;
	}
	
	private void fillCollectionEmptyMessage(int rowNumber, Sheet sheet) {
		int columnsCount = sheet.getRow(rowNumber-1).getPhysicalNumberOfCells();
		Row row = sheet.createRow(rowNumber);
		Cell cell = row.createCell(0);
		CellStyle style = sheet.getWorkbook().createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cell.setCellValue(IReportsUtils.DATA_NOT_FOUND);
		cell.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(rowNumber,rowNumber,
				0,columnsCount-1));
	}

	/**
	 * Colores
	 * @param workbook
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor= palette.findColor(r, g, b); 
			if (hssfColor == null ){
			    palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
			    hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		} catch (Exception e) {}

		return hssfColor;
	}
	
	public void createStructure() throws NegocioExcepcion {}

	public void writeReport(Collection dataSource) throws NegocioExcepcion {}

	public String getFileExtension() {
		return null;
	}

}
