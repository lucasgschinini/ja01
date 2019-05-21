package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenCobroClienteDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenGrillaTransaccionDto;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.ExportacionExcelDefinicionColumnas;
import ar.com.telecom.shiva.negocio.bean.TotalAcumuladoresTransacciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

/**
 * Clase de Exportacion de Detalle de un Cobro.
 * 
 * @author u572487 Guido.N.Settecerze (CSS' assistant).
 */
@SuppressWarnings("deprecation")
public class ExportacionDetalleCobroImpl implements IExcelServicio {
	
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	
	//private HSSFCellStyle estiloCurrency;
	
	
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

//	private Resource jrxmlFilePath;
	
	private String name;
//	private Collection<String> titulosGrales;
//	private Collection<String> columnNames;
//	private Collection<String> columnDescriptions;
//	private Collection<String> columnDescriptionsGral;
//	private Collection<String> columnNamesGral;
//	
//	private Collection<HSSFCell> celdasConBordesYColorDeTabla = new ArrayList<HSSFCell>();
//	private Collection<HSSFCell> celdasConBordesYColorDeTablaObservacion = new ArrayList<HSSFCell>();
//	private Collection<HSSFCell> celdasConBordes = new ArrayList<HSSFCell>();
//	private Collection<HSSFCell> celdasConBordesCentrado = new ArrayList<HSSFCell>();
//	private Collection<HSSFCell> palabrasEnNegrita = new ArrayList<HSSFCell>();
//	private Collection<HSSFCell> palabrasEnNegritaYsubrayado = new ArrayList<HSSFCell>();
	
	private String xls = ".xls";
	
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 */
	public void generarExportacionDetalleCobro(HttpServletResponse response, CobroDto dto) throws NegocioExcepcion {
		
		try {
			ByteArrayOutputStream salida = new ByteArrayOutputStream();
			HSSFWorkbook workbook = createBook(dto);
		
			workbook.write(salida);
			
			response.setContentType(XLS_CONTENT_TYPE);
	    	response.setContentLength(salida.size());
	    		
	    	response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "ID Operación Cobro " + dto.getIdOperacion() + xls);
	    	
	    	FileCopyUtils.copy(salida.toByteArray(), response.getOutputStream());
	    	
		} catch (IOException e) {
			new NegocioExcepcion(e.getMessage(),e);
			
		}
	}
	
	public HSSFWorkbook generarExcelDetalleCobro(CobroDto dto) throws NegocioExcepcion {

		HSSFWorkbook workbook = createBook(dto);

		return workbook;

	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	protected HSSFWorkbook createBook(CobroDto dto) throws NegocioExcepcion{
		int rowActual = 0;
		String tipoMedioPago = null;
		ExportacionExcelDefinicionColumnas definicionColumnas = new ExportacionExcelDefinicionColumnas();

		if (dto.getMedios().iterator().hasNext()) {
					tipoMedioPago = dto.getMedios().iterator().next().getIdMpTipoCredito();
		}
		
		
		//celdasConBordesYColorDeTabla.clear();
		//celdasConBordesYColorDeTablaObservacion.clear();
		//getCeldasConBordesCentrado().clear();
		//celdasConBordes.clear();
		//getPalabrasEnNegrita().clear();
		//getPalabrasEnNegritaYsubrayado().clear();
		HSSFWorkbook workbook = new HSSFWorkbook();//Planilla
		definicionColumnas.setEstiloCurrency(workbook.createCellStyle());
		HSSFDataFormat df = workbook.createDataFormat();
		definicionColumnas.getEstiloCurrency().setDataFormat(df.getFormat("#,##0.00"));
		HSSFSheet sheet;
		sheet = workbook.createSheet(dto.getIdOperacionFormateado());
		dto.getIdCobroFormatiadoJSPDetalle();
		sheet.autoSizeColumn(1, true);
	    sheet.autoSizeColumn(1);
		pintarFondoBlancoHoja(sheet);
		
		//Cargo los datos de cabecera del cobro
		rowActual = this.fillHeadersRemix(workbook, sheet, dto, definicionColumnas);
		if (!Validaciones.isNullEmptyOrDash(dto.getObservacionAnterior())) {
			rowActual = this.fillObservaciones(workbook, sheet, dto,"Observaciones Anteriores: ", true, rowActual, definicionColumnas);
		}
		rowActual = this.fillObservaciones(workbook, sheet, dto, "Observacion: ", false, rowActual, definicionColumnas);
		
		//Cargo los clientes del cobro
		try {
			rowActual = this.fillTablaClientes(workbook, sheet, dto, rowActual, definicionColumnas);
		} catch(NegocioExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		//Cargo los debitos del cobro
		try {
			rowActual = this.fillTablaDebitos(workbook, sheet, dto, rowActual, definicionColumnas);
		} catch(NegocioExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
			rowActual = this.fillTablaOtrosDebitos(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo los creditos del cobro
		rowActual = this.fillTablaCreditos(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo otros medios de pago del cobro
		rowActual = this.fillTablaOtrosMediosDePago(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo los Documentos CAP
		if(TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(tipoMedioPago)
				|| TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(tipoMedioPago)){
			rowActual = this.fillTablaDocumentosCap(workbook, sheet, dto, rowActual, definicionColumnas);
		}
		
		//Cargo los comprobantes del cobro
		rowActual = this.fillTablaComprobantes(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo los documentos relacionados
		rowActual = this.fillTotalesDocumentos(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo los comprobantes de la aplicacion manual
		rowActual = this.fillTablaComprobantesApliManual(workbook, sheet, dto, rowActual, definicionColumnas);
		
		//Cargo los codigos de operacion externa
		if(Validaciones.isCollectionNotEmpty(dto.getListaCodigoOperacionesExternas())){
			rowActual = this.fillTablaCodigosOperacionExterna(workbook, sheet, dto, rowActual, definicionColumnas);
		}
		
		rowActual = this.fillTablaTransacciones(workbook, sheet, dto, rowActual, definicionColumnas);
		if(TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(tipoMedioPago)
				|| TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(tipoMedioPago)){
			rowActual = this.fillObservacionesCap(workbook, sheet, dto, "Observaciones de Documentos Cap: ", rowActual, definicionColumnas);
		}
		
		rowActual = this.fillTotalesIntereses(workbook, sheet, dto, rowActual, definicionColumnas);
		
		this.validarGuinesCentrarYImporteRightStyle(workbook, sheet, definicionColumnas);
		this.pintarCeldasTabla(workbook, sheet, definicionColumnas);
		this.pintarCeldasTablaObservaciones(workbook, sheet, definicionColumnas);
		this.pintarCeldasTablaContenido(workbook, sheet, definicionColumnas);
		this.pintarCeldasConBordesCentrado(workbook, sheet, definicionColumnas);
		this.palabrasEnNegrita(workbook, sheet, definicionColumnas);
		this.palabrasEnNegritaYsubrayado(workbook, sheet, definicionColumnas);
		//celdasConBordesYColorDeTabla.clear();
		//celdasConBordesYColorDeTablaObservacion.clear();
		//celdasConBordes.clear();
//		getCeldasConBordesCentrado().clear();
//		getPalabrasEnNegrita().clear();
//		getPalabrasEnNegritaYsubrayado().clear();
		autoAjusteDeColumna(sheet);
		return workbook;
	}

	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param sheet
	 */
	private void autoAjusteDeColumna(HSSFSheet sheet) {
		for(int i = 0; i < 45; i++)
		{
			sheet.autoSizeColumn((short)i);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	protected int fillHeadersRemix(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, ExportacionExcelDefinicionColumnas definicionColumnas) {
		
		HSSFRow row1 = sheet.createRow(1);
		HSSFRow row2 = sheet.createRow(2);
		HSSFRow row3 = sheet.createRow(3);
		HSSFRow row4 = sheet.createRow(4);
		HSSFRow row5 = sheet.createRow(5);
		HSSFRow row6 = sheet.createRow(6);
		HSSFRow row7 = sheet.createRow(7);
		HSSFRow row8 = sheet.createRow(8);
		HSSFRow row9 = sheet.createRow(9);
		HSSFRow row10 = sheet.createRow(10);
		
		HSSFCell row1Column1 = row1.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row1Column1);
		HSSFCell row2Column1 = row2.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row2Column1);
		HSSFCell row3Column1 = row3.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row3Column1);
		HSSFCell row4Column1 = row4.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row4Column1);
		HSSFCell row5Column1 = row5.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row5Column1);
		HSSFCell row6Column1 = row6.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row6Column1);
		HSSFCell row7Column1 = row7.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row7Column1);
		HSSFCell row8Column1 = row8.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row8Column1);
		HSSFCell row9Column1 = row9.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row9Column1);
		HSSFCell row10Column1 = row10.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row10Column1);
		
		HSSFCell row1Column6 = row1.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row1Column6);
		HSSFCell row2Column6 = row2.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row2Column6);
		HSSFCell row3Column6 = row3.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row3Column6);
		HSSFCell row4Column6 = row4.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row4Column6);
		HSSFCell row5Column6 = row5.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row5Column6);
		HSSFCell row6Column6 = row6.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row6Column6);
		HSSFCell row7Column6 = row7.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row7Column6);
//		HSSFCell row8Column6 = row8.createCell(4);
//		definicionColumnas.getPalabrasEnNegrita().add(row8Column6);
		HSSFCell row9Column6 = row9.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row9Column6);
		HSSFCell row10Column6 = row10.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row10Column6);
		
		HSSFCell row1Column2 = row1.createCell(2);
		HSSFCell row2Column2 = row2.createCell(2);
		HSSFCell row3Column2 = row3.createCell(2);
		HSSFCell row4Column2 = row4.createCell(2);
		HSSFCell row5Column2 = row5.createCell(2);
		HSSFCell row6Column2 = row6.createCell(2);
		HSSFCell row7Column2 = row7.createCell(2);
		HSSFCell row8Column2 = row8.createCell(2);
		HSSFCell row9Column2 = row9.createCell(2);
		HSSFCell row10Column2 = row10.createCell(2);
		
		HSSFCell row1Column7 = row1.createCell(5);
		HSSFCell row2Column7 = row2.createCell(5);
		HSSFCell row3Column7 = row3.createCell(5);
		HSSFCell row4Column7 = row4.createCell(5);
		HSSFCell row5Column7 = row5.createCell(5);
		HSSFCell row6Column7 = row6.createCell(5);
		HSSFCell row7Column7 = row7.createCell(5);
		HSSFCell row8Column7 = row8.createCell(5);
		HSSFCell row9Column7 = row9.createCell(5);
		HSSFCell row10Column7 = row10.createCell(5);
		
		row1Column1.setCellValue("Id Operación Cobro: ");
		row2Column1.setCellValue("Motivo: ");
		row3Column1.setCellValue("Analista: ");
		row4Column1.setCellValue("Estado Operación Cobro: ");
		row5Column1.setCellValue("Fecha Alta: ");
//		row6Column1.setCellValue("Responsable Aprobación Supervisor de Cobranza: ");//no tocar
		row6Column1.setCellValue("Responsable Aprobación Referente de Cobranza: ");//cambiar a row7Column1 una ves agregado el supervisor
		row7Column1.setCellValue("Responsable Aprobación Operaciones Especiales: ");
		//U562163 - Fusion Cobros
//		row8Column1.setCellValue("Responsable Aplicación Manual en Sistema Externo: ");
		row9Column1.setCellValue("Fecha Imputación: ");
		if (!Validaciones.isObjectNull(cobroDto) && !Validaciones.isNullOrEmpty(cobroDto.getIdOperacionMasiva())) {
			row10Column1.setCellValue("ID Operación Masiva:");
		}
		
		row1Column6.setCellValue("Empresa: ");
		row2Column6.setCellValue("Segmento: ");
		row3Column6.setCellValue("Copropietario: ");
		row4Column6.setCellValue("Fecha Último Estado: ");
		row5Column6.setCellValue("Fecha Derivación: ");
//		row6Column6.setCellValue("Fecha Aprobación Supervisor de Cobranza: ");//no tocar
		row6Column6.setCellValue("Fecha Aprobación Referente de Cobranza: ");//cambiar a row7Column6 una ves agregado el supervisor
		row7Column6.setCellValue("Fecha Aprobación Operaciones Especiales: ");
		//U562163 - Fusion Cobros
//		row8Column6.setCellValue("Fecha Aplicación Manual en Sistema Externo: ");
		row9Column6.setCellValue("Moneda de la Operación: ");
		
		if (!Validaciones.isObjectNull(cobroDto) && !Validaciones.isNullOrEmpty(cobroDto.getIdOperacionMasiva())) {
			row10Column6.setCellValue("Nombre Archivo:");
		}

		if(!Validaciones.isObjectNull(cobroDto)){
			row1Column2.setCellValue(cobroDto.getIdOperacionFormateado());
			row2Column2.setCellValue(cobroDto.getDescripcionMotivoCobro());
			row3Column2.setCellValue(cobroDto.getNombreAnalista());
			String estadoMarca = "";
			if(!Validaciones.isObjectNull(cobroDto.getEstado())){
				estadoMarca = cobroDto.getEstado().descripcion();
			}
			Collection<MarcaEnum> marcas = cobroDto.getMarcas();
			for (MarcaEnum marcaEnum : marcas) {
				estadoMarca = estadoMarca.concat(" / ").concat(marcaEnum.descripcion());
			}
			row4Column2.setCellValue(estadoMarca);//ver

			row5Column2.setCellValue(cobroDto.getFechaAltaFormateado());//ver
			row6Column2.setCellValue("");//ver Responsable Aprobación Supervisor de Cobranza FALTA
			row6Column2.setCellValue(cobroDto.getNombreApellidoUsuarioAprobacionReferenteCobranza());
			row7Column2.setCellValue(cobroDto.getNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales());
			//U562163 - Fusion Cobros
			if (!Validaciones.isNullOrEmpty(cobroDto.getNombreApellidoReferenteCaja())) {
				row8Column2.setCellValue(cobroDto.getNombreApellidoReferenteCaja());
			} 
//			else if (!Validaciones.isNullOrEmpty(cobroDto.getNombreApellidoReferenteOperacionExterna())) {
//				row8Column2.setCellValue(cobroDto.getNombreApellidoReferenteOperacionExterna());
//			}
			
			row9Column2.setCellValue(cobroDto.getFechaImputacion()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaImputacion()):"");
			
			if (!Validaciones.isNullOrEmpty(cobroDto.getIdOperacionMasiva())) {
				row10Column2.setCellValue(cobroDto.getIdOperacionMasiva());
			}

			row1Column7.setCellValue(cobroDto.getEmpresa());
			row2Column7.setCellValue(cobroDto.getSegmento());
			row3Column7.setCellValue(cobroDto.getNombreCopropietario());
			row4Column7.setCellValue(cobroDto.getFechaUltimoEstado()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaUltimoEstado()):"");//cambiar
			row5Column7.setCellValue(cobroDto.getFechaDerivacion()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaDerivacion()):"");
			//			row6Column7.setCellValue(cobroDto.getFechaAprobacionSupervisorCobranza()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaAprobacionSupervisorCobranza()):"");
			row6Column7.setCellValue(cobroDto.getFechaAprobacionReferenteCobranza()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaAprobacionReferenteCobranza()):"");
			row7Column7.setCellValue(cobroDto.getFechaAprobacionOperacionesEspeciales()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaAprobacionOperacionesEspeciales()):"");
			//U562163 - Fusion Cobros
			if (!Validaciones.isObjectNull(cobroDto.getFechaReferenteCaja())) {
				row8Column7.setCellValue(Utilidad.formatDateAndTimeFull(cobroDto.getFechaReferenteCaja()));
			}
//				else if (!Validaciones.isObjectNull(cobroDto.getFechaReferenteOperacionExterna())) {
//				row8Column7.setCellValue(Utilidad.formatDateAndTimeFull(cobroDto.getFechaReferenteOperacionExterna()));
//			}
			
			row9Column7.setCellValue(MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()).getDescripcion());
			
			if (!Validaciones.isNullOrEmpty(cobroDto.getNombreArchivo())) {
				row10Column7.setCellValue(cobroDto.getNombreArchivo());
			}
			
		}
		return 10;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	protected int fillObservaciones(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, String titulo, boolean anterior, int rowActualint, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		rowActualint += 3;
		int rowTituloNro = rowActualint++;
		int rowContenidoNro = rowActualint++;
		
		HSSFRow row11 = sheet.createRow(rowTituloNro);
		HSSFRow row12 = sheet.createRow(rowContenidoNro);
		row11.setHeight((short)400);
		row12.setHeight((short)2400);
		Region regionTituloObservacion = new Region(rowTituloNro,(short)1, rowTituloNro,(short)7);
		Region regionContenidoObservacion = new Region(rowContenidoNro,(short)1, rowContenidoNro,(short)7);
	    sheet.addMergedRegion(regionTituloObservacion);
	    sheet.addMergedRegion(regionContenidoObservacion);
		
		bordeObservacion(workbook, sheet, regionContenidoObservacion);
		
		HSSFCell row11Column1 = row11.createCell(1);
		HSSFCell row12Column1 = row12.createCell(1);
		row12Column1.setCellStyle(style);
		row11Column1.setCellValue(titulo);
		definicionColumnas.getCeldasConBordesYColorDeTablaObservacion().add(row11Column1);
		
		if (!Validaciones.isObjectNull(cobroDto)) {
			if (!anterior && !Validaciones.isNullEmptyOrDash(cobroDto.getObservacion())) {
				row12Column1.setCellValue("\r\n"+Utilidad.formateadoVista(cobroDto.getObservacion()));
			} else if (anterior && !Validaciones.isNullEmptyOrDash(cobroDto.getObservacionAnterior())) {
				row12Column1.setCellValue("\r\n"+Utilidad.formateadoVista(cobroDto.getObservacionAnterior()));
			} else {
				row12Column1.setCellValue("-");
				definicionColumnas.getCeldasConBordesCentrado().add(row12Column1);
			}
		}	
		definicionColumnas.getCeldasConBordes().add(row12Column1);
		return rowActualint;
	}

	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param region
	 */
	private void bordeObservacion(HSSFWorkbook workbook, HSSFSheet sheet, Region region) {
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		HSSFRegionUtil.setBottomBorderColor((short)8, region, sheet, workbook);
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		HSSFRegionUtil.setLeftBorderColor((short)8, region, sheet, workbook);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		HSSFRegionUtil.setRightBorderColor((short)8, region, sheet, workbook);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		HSSFRegionUtil.setTopBorderColor((short)8, region, sheet, workbook);
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaClientes(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		rowActual += 3;
		HSSFRow row16 = sheet.createRow(rowActual++);
		HSSFRow row17 = sheet.createRow(rowActual++);
		row17.setHeightInPoints(25);
		HSSFCell row16Column1 = row16.createCell(1);
		HSSFCell row17Column1 = row17.createCell(1);
		HSSFCell row17Column2 = row17.createCell(2);
		HSSFCell row17Column3 = row17.createCell(3);
		HSSFCell row17Column4 = row17.createCell(4);
		HSSFCell row17Column5 = row17.createCell(5);
		HSSFCell row17Column6 = row17.createCell(6);
		HSSFCell row17Column7 = row17.createCell(7);
		HSSFCell row17Column8 = row17.createCell(8);
		
		row16Column1.setCellValue("Clientes");
		definicionColumnas.getPalabrasEnNegrita().add(row16Column1);
		List<ClienteDto> clientesList = new ArrayList<ClienteDto>(cobroDto.getClientes());
		List<? extends DTO> clientesFinal;
		Iterator<ClienteDto> iterator = null;
		try {
			Collections.sort(clientesList , new ComparatorOrdenCobroClienteDto());
			clientesFinal = Utilidad.guionesNull(clientesList);
			iterator = (Iterator<ClienteDto>) clientesFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		
		int rowCrear = rowActual;


		while(iterator.hasNext()){
			ClienteDto cliente = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			
			//CUIT
			HSSFCell createCellCuit = row.createCell(1);
			createCellCuit.setCellValue(cliente.getCuit());
			definicionColumnas.getCeldasConBordes().add(createCellCuit);
			
//			CLIENTE
			HSSFCell createCellCliente = row.createCell(2);
			createCellCliente.setCellValue(cliente.getIdClienteLegado());
			definicionColumnas.getCeldasConBordes().add(createCellCliente);
			
//			EMPRESAS ASOCIADAS
			HSSFCell createCellEmpresasAsociadas = row.createCell(3);
			createCellEmpresasAsociadas.setCellValue(cliente.getEmpresasAsociadas());
			definicionColumnas.getCeldasConBordes().add(createCellEmpresasAsociadas);
			
//			RAZON SOCIAL
			HSSFCell createCellRazonSocial = row.createCell(4);
			createCellRazonSocial.setCellValue(cliente.getRazonSocial());
			definicionColumnas.getCeldasConBordes().add(createCellRazonSocial);
			
//			ORIGEN
			HSSFCell createCellOrigen = row.createCell(5);
			createCellOrigen.setCellValue(cliente.getOrigen());
			definicionColumnas.getCeldasConBordes().add(createCellOrigen);

//			SEGMENTO AGENCIA NEGOCIO
			HSSFCell createCellSegAgenNeg = row.createCell(6);
			createCellSegAgenNeg.setCellValue(cliente.getSegmentoAgencia());
			definicionColumnas.getCeldasConBordes().add(createCellSegAgenNeg);
			
//			HOLDING
			HSSFCell createCellHolding = row.createCell(7);
			createCellHolding.setCellValue(cliente.getCodigoHolding());
			definicionColumnas.getCeldasConBordes().add(createCellHolding);
			
//			AGENCIA NEGOCIO
			HSSFCell createCellAgenciaNeg = row.createCell(8);
			createCellAgenciaNeg.setCellValue(cliente.getAgenciaNegocio());
			definicionColumnas.getCeldasConBordes().add(createCellAgenciaNeg);
			
			rowCrear++;
		}
		
		row17Column1.setCellValue("CUIT");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column1);
		row17Column2.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column2);
		row17Column3.setCellValue("Empresas Asociadas");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column3);
		row17Column4.setCellValue("Razón Social");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column4);
		row17Column5.setCellValue("Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column5);
		row17Column6.setCellValue("Segmento Agencia de Negocio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column6);
		row17Column7.setCellValue("Holding");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column7);
		row17Column8.setCellValue("Agencia de Negocio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column8);
		
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaDebitos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		int rowActualFinal = rowActual;
		rowActualFinal = rowActualFinal+2;
		HSSFRow row21 = sheet.createRow(rowActualFinal);
		rowActualFinal = rowActualFinal+1;
		HSSFRow row22 = sheet.createRow(rowActualFinal);
		row22.setHeightInPoints(25);
		HSSFCell row21Column1 = row21.createCell(1);
		HSSFCell row22Column1 = row22.createCell(1);
		HSSFCell row22Column2 = row22.createCell(2);
		HSSFCell row22Column3 = row22.createCell(3);
		HSSFCell row22Column4 = row22.createCell(4);
		HSSFCell row22Column5 = row22.createCell(5);
		HSSFCell row22Column6 = row22.createCell(6);
		HSSFCell row22Column7 = row22.createCell(7);
		HSSFCell row22Column8 = row22.createCell(8);
		HSSFCell row22Column9 = row22.createCell(9);
		HSSFCell row22Column10 = row22.createCell(10);
		HSSFCell row22Column11 = row22.createCell(11);
		HSSFCell row22Column12 = row22.createCell(12);
		HSSFCell row22Column13 = row22.createCell(13);
		HSSFCell row22Column14 = row22.createCell(14);
		HSSFCell row22Column15 = row22.createCell(15);
		HSSFCell row22Column16 = row22.createCell(16);
		HSSFCell row22Column17 = row22.createCell(17);
		HSSFCell row22Column18 = row22.createCell(18);
		HSSFCell row22Column19 = row22.createCell(19);
		HSSFCell row22Column20 = row22.createCell(20);
		HSSFCell row22Column21 = row22.createCell(21);
		HSSFCell row22Column22 = row22.createCell(22);
		HSSFCell row22Column23 = row22.createCell(23);
		HSSFCell row22Column24 = row22.createCell(24);
		HSSFCell row22Column25 = row22.createCell(25);
		HSSFCell row22Column26 = row22.createCell(26);
		HSSFCell row22Column27 = row22.createCell(27);
		HSSFCell row22Column28 = row22.createCell(28);
		HSSFCell row22Column29 = row22.createCell(29);
		HSSFCell row22Column30 = row22.createCell(30);
		HSSFCell row22Column31 = row22.createCell(31);
		
		row21Column1.setCellValue("Débitos");
		definicionColumnas.getPalabrasEnNegrita().add(row21Column1);
		
		row22Column1.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column1);
		row22Column2.setCellValue("Cuenta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column2);
		row22Column3.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column3);
		row22Column4.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column4);
		row22Column5.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column5);
		row22Column6.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column6);
		row22Column7.setCellValue("Nro. Referencia MIC");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column7);
		row22Column8.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column8);
		row22Column9.setCellValue("Saldo 1° vto moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column9);
		row22Column10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column10);
		row22Column11.setCellValue("Importe 1er vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column11); 
		row22Column12.setCellValue("Importe 2do vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column12);
		row22Column13.setCellValue("Saldo pesificado Fecha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column13);
		row22Column14.setCellValue("Check Cobrar al 2° vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column14);
		row22Column15.setCellValue("Check Destransferir 3eros");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column15);
		row22Column16.setCellValue("Importe a cobrar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column16);
		row22Column17.setCellValue("Estado conceptos 3eros");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column17);
		row22Column18.setCellValue("Importe 3eros transferidos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column18);
		row22Column19.setCellValue("Estado origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column19);
		row22Column20.setCellValue("Reclamo en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column20);
		row22Column21.setCellValue("Migrado en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column21);
		row22Column22.setCellValue("Estado en Deimos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column22);
		row22Column23.setCellValue("Pago/Compensación en proceso");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column23);
		row22Column24.setCellValue("Op. Asociada + Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column24);
		row22Column25.setCellValue("Tipo de cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column25);
		row22Column26.setCellValue("Fecha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column26);
		row22Column27.setCellValue("Check Sin Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column27);
		row22Column28.setCellValue("Nro. Convenio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column28);
		row22Column29.setCellValue("Cuota Convenio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column29);
		row22Column30.setCellValue("Fecha ult. pago parcial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column30);
		row22Column31.setCellValue("Resultado validación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column31);
		
		List<CobroDebitoDto> debitosList = new ArrayList<CobroDebitoDto>(cobroDto.getDebitos());
		List<? extends DTO> debitosFinal;
		Iterator<CobroDebitoDto> iterator = null;
		try {
			debitosFinal = Utilidad.guionesNull(debitosList);
			iterator = (Iterator<CobroDebitoDto>) debitosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			CobroDebitoDto debito = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell cliente = row.createCell(1);
			cliente.setCellValue(debito.getCliente());
			definicionColumnas.getCeldasConBordes().add(cliente);
			HSSFCell cuenta = row.createCell(2);
			cuenta.setCellValue(debito.getCuenta());
			definicionColumnas.getCeldasConBordes().add(cuenta);
			HSSFCell sistema = row.createCell(3);
			sistema.setCellValue(debito.getSistemaDescripcion());
			definicionColumnas.getCeldasConBordes().add(sistema);
			HSSFCell tipoDoc = row.createCell(4);
			tipoDoc.setCellValue(debito.getTipoDoc());
			definicionColumnas.getCeldasConBordes().add(tipoDoc);
			HSSFCell subTipoDoc = row.createCell(5);
			subTipoDoc.setCellValue(debito.getSubtipoDocumentoDesc());
			definicionColumnas.getCeldasConBordes().add(subTipoDoc);
			HSSFCell nroDoc = row.createCell(6);
			nroDoc.setCellValue(debito.getNroDoc());
			definicionColumnas.getCeldasConBordes().add(nroDoc);
			HSSFCell nroReferenciaMic = row.createCell(7);
			nroReferenciaMic.setCellValue(debito.getNumeroReferenciaMic());
			definicionColumnas.getCeldasConBordes().add(nroReferenciaMic);
			HSSFCell fechaVenc = row.createCell(8);
			fechaVenc.setCellValue(debito.getFechaVenc());
			definicionColumnas.getCeldasConBordes().add(fechaVenc);
			HSSFCell saldo1erVencMonOrigen = row.createCell(9);
			
			saldo1erVencMonOrigen.setCellValue(!Validaciones.isNullEmptyOrDash(debito.getSaldo1erVencMonOrigen()) ?  
							Utilidad.formatCurrencySacarPunto(
								Utilidad.formatCurrency(new BigDecimal(debito.getSaldo1erVencMonOrigen().replaceAll(",", ".")), 2, debito.getMoneda())) 
													 : "-");
			definicionColumnas.getCeldasConBordes().add(saldo1erVencMonOrigen);
			HSSFCell moneda = row.createCell(10);
			moneda.setCellValue(debito.getMoneda());
			definicionColumnas.getCeldasConBordes().add(moneda);
			HSSFCell imp1erVenc = row.createCell(11);
			
			imp1erVenc.setCellValue(!Validaciones.isNullEmptyOrDash(debito.getImp1erVenc()) ? 
							Utilidad.formatCurrencySacarPunto(
									Utilidad.formatCurrency(new BigDecimal(debito.getImp1erVenc().replaceAll(",", ".")), 2, debito.getMoneda())) 
													: "-");
			
			definicionColumnas.getCeldasConBordes().add(imp1erVenc);
			HSSFCell imp2doVenc = row.createCell(12);
			imp2doVenc.setCellValue(debito.getImp2doVenc()!="-"?Utilidad.formatCurrency(new BigDecimal(debito.getImp2doVenc().replaceAll(",", ".")), true, false):"-");
			definicionColumnas.getCeldasConBordes().add(imp2doVenc);
			HSSFCell saldoPesificado = row.createCell(13);
			if (!Validaciones.isNullEmptyOrDash(debito.getMoneda()) && !debito.getMoneda().equals(cobroDto.getMonedaOperacion())) {
				saldoPesificado.setCellValue(debito.getSaldoPesificado()!="-"?Utilidad.formatCurrency(new BigDecimal(debito.getSaldoPesificado().replaceAll(",", ".")), true, false):"-");
			} else {
				saldoPesificado.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(saldoPesificado);
			HSSFCell checkCobrarAl2doVenc = row.createCell(14);
			if(debito.isCobrarAl2doVenc()){
				checkCobrarAl2doVenc.setCellValue("SI");
			}else{
				checkCobrarAl2doVenc.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(checkCobrarAl2doVenc);
			HSSFCell checkDestransferir3ros = row.createCell(15);
			if(debito.isDestransferir3ros()){
				checkDestransferir3ros.setCellValue("SI");
			}else{
				checkDestransferir3ros.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(checkDestransferir3ros);
			HSSFCell impACobrar = row.createCell(16);
			impACobrar.setCellValue(debito.getImpACobrar()!="-"?cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(new BigDecimal(debito.getImpACobrar().replaceAll(",", ".")), false, false):"-");
			definicionColumnas.getCeldasConBordes().add(impACobrar);
			HSSFCell estadoCptosDe3ros = row.createCell(17);
			estadoCptosDe3ros.setCellValue(debito.getEstadoCptosDe3ros());
			definicionColumnas.getCeldasConBordes().add(estadoCptosDe3ros);
			HSSFCell imp3rosTransf = row.createCell(18);
			imp3rosTransf.setCellValue(debito.getImp3rosTransf()!="-"?Utilidad.formatCurrency(new BigDecimal(debito.getImp3rosTransf().replaceAll(",", ".").replace("$", "")), true, false):"-");
			definicionColumnas.getCeldasConBordes().add(imp3rosTransf);
			HSSFCell estadoOrigen = row.createCell(19);
			estadoOrigen.setCellValue(debito.getEstadoOrigen());
			definicionColumnas.getCeldasConBordes().add(estadoOrigen);
			HSSFCell reclamoOrigen = row.createCell(20);
			reclamoOrigen.setCellValue(debito.getMarcaReclamo()!=null?debito.getMarcaReclamo().descripcionAMostrar():"");
			definicionColumnas.getCeldasConBordes().add(reclamoOrigen);
			HSSFCell migradoOrigen = row.createCell(21);
			migradoOrigen.setCellValue(debito.getMigradoOrigen());
			definicionColumnas.getCeldasConBordes().add(migradoOrigen);
			HSSFCell estadoDeimos = row.createCell(22);
			estadoDeimos.setCellValue(debito.getEstadoDeimos());
			definicionColumnas.getCeldasConBordes().add(estadoDeimos);
			HSSFCell pagoCompensacionProceso = row.createCell(23);
			pagoCompensacionProceso.setCellValue(debito.getMarcaPagoCompensacionEnProcesoShiva()!=null?debito.getMarcaPagoCompensacionEnProcesoShiva().getDescripcion():"");
			definicionColumnas.getCeldasConBordes().add(pagoCompensacionProceso);
			HSSFCell opeAsocAnalista = row.createCell(24);
			if (!Validaciones.isObjectNull(debito.getOpeAsocAnalista())) {
				String opeAsocAnalistVec[] = debito.getOpeAsocAnalista().split("<br>");
				opeAsocAnalista.setCellValue(StringUtils.join(opeAsocAnalistVec, " "));
				
			} else {
				opeAsocAnalista.setCellValue(debito.getOpeAsocAnalista());
			}
			definicionColumnas.getCeldasConBordes().add(opeAsocAnalista);
			HSSFCell tipoCambio = row.createCell(25);
			if(!Validaciones.isNullEmptyOrDash(debito.getTipoCambio()) && !Validaciones.isNullEmptyOrDash(debito.getMoneda()) && !debito.getMoneda().equals(cobroDto.getMonedaOperacion())){
				tipoCambio.setCellValue("?"+debito.getTipoCambio());
			} else {
				tipoCambio.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoCambio);
			HSSFCell fechaEmision = row.createCell(26);
			fechaEmision.setCellValue(debito.getFechaEmision());
			definicionColumnas.getCeldasConBordes().add(fechaEmision);
			HSSFCell checkSinDifDeCambio = row.createCell(27);
			if (SistemaEnum.CALIPSO.equals(debito.getSistemaOrigen())
					&& (   TipoComprobanteEnum.FAC.descripcion().equals(debito.getTipoDoc()) 
						|| TipoComprobanteEnum.DEB.descripcion().equals(debito.getTipoDoc()) 
						|| TipoComprobanteEnum.CRE.descripcion().equals(debito.getTipoDoc()) 
						|| TipoComprobanteEnum.CTA.descripcion().equals(debito.getTipoDoc())   )
						&& debito.getMoneda() != cobroDto.getMonedaOperacion()
						&& ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equals(cobroDto.getIdMotivoCobro())) {
				if (debito.isSinDifDeCambio()) {
					checkSinDifDeCambio.setCellValue("SI");
				} else {
					checkSinDifDeCambio.setCellValue("NO");
				}
			} else {
				checkSinDifDeCambio.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(checkSinDifDeCambio);
			HSSFCell nroConvenio = row.createCell(28);
			nroConvenio.setCellValue(debito.getNroConvenio());
			definicionColumnas.getCeldasConBordes().add(nroConvenio);
			HSSFCell cuota = row.createCell(29);
			cuota.setCellValue(debito.getCuota());
			definicionColumnas.getCeldasConBordes().add(cuota);
			HSSFCell fechaUltPagoParcial = row.createCell(30);
			fechaUltPagoParcial.setCellValue(debito.getFechaUltPagoParcial());
			definicionColumnas.getCeldasConBordes().add(fechaUltPagoParcial);
			HSSFCell resultadoValidacionDescripcionError = row.createCell(31);
			resultadoValidacionDescripcionError.setCellValue(debito.getResultadoValidacionDescripcionError());
			definicionColumnas.getCeldasConBordes().add(resultadoValidacionDescripcionError);
			
			rowCrear++;
		}
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaCreditos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		rowActualFinal = rowActualFinal+2;
		HSSFRow row26 = sheet.createRow(rowActualFinal);
		rowActualFinal = rowActualFinal+1;
		HSSFRow row27 = sheet.createRow(rowActualFinal);
		row27.setHeightInPoints(25);
		HSSFCell row26Column1 = row26.createCell(1);
		HSSFCell row27Column1 = row27.createCell(1);
		HSSFCell row27Column2 = row27.createCell(2);
		HSSFCell row27Column3 = row27.createCell(3);
		HSSFCell row27Column4 = row27.createCell(4);
		HSSFCell row27Column5 = row27.createCell(5);
		HSSFCell row27Column6 = row27.createCell(6);
		HSSFCell row27Column7 = row27.createCell(7);
		HSSFCell row27Column8 = row27.createCell(8);
		HSSFCell row27Column9 = row27.createCell(9);
		HSSFCell row27Column10 = row27.createCell(10);
		HSSFCell row27Column11 = row27.createCell(11);
		HSSFCell row27Column12 = row27.createCell(12);
		HSSFCell row27Column13 = row27.createCell(13);
		HSSFCell row27Column14 = row27.createCell(14);
		HSSFCell row27Column15 = row27.createCell(15);
		HSSFCell row27Column16 = row27.createCell(16);
		HSSFCell row27Column17 = row27.createCell(17);
		HSSFCell row27Column18 = row27.createCell(18);
		HSSFCell row27Column19 = row27.createCell(19);
		HSSFCell row27Column20 = row27.createCell(20);
		HSSFCell row27Column21 = row27.createCell(21);
		HSSFCell row27Column22 = row27.createCell(22);
		HSSFCell row27Column23 = row27.createCell(23);
		HSSFCell row27Column24 = row27.createCell(24);
		HSSFCell row27Column25 = row27.createCell(25);
		HSSFCell row27Column26 = row27.createCell(26);
		HSSFCell row27Column27 = row27.createCell(27);
		HSSFCell row27Column28 = row27.createCell(28);
		HSSFCell row27Column29 = row27.createCell(29);
		HSSFCell row27Column30 = row27.createCell(30);
		HSSFCell row27Column31 = row27.createCell(31);
		HSSFCell row27Column32 = row27.createCell(32);
		
		row26Column1.setCellValue("Créditos");
		definicionColumnas.getPalabrasEnNegrita().add(row26Column1);
		
		row27Column1.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column1);
		row27Column2.setCellValue("Cuenta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column2);
		row27Column3.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column3);
		row27Column4.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column4);
		row27Column5.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column5);
		row27Column6.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column6);
		row27Column7.setCellValue("Nro. Referencia MIC");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column7);
		row27Column8.setCellValue("Fecha Valor");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column8);
		row27Column9.setCellValue("Saldo en moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column9);
		row27Column10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column10);
		row27Column11.setCellValue("Importe moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column11);
		row27Column12.setCellValue("Importe pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column12);
		row27Column13.setCellValue("Saldo pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column13);
		row27Column14.setCellValue("Importe a utilizar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column14);
		row27Column15.setCellValue("Fecha Alta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column15);
		row27Column16.setCellValue("Fecha Ingreso Recibo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column16);
		row27Column17.setCellValue("Fecha Depósito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column17);
		row27Column18.setCellValue("Fecha Transferencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column18);
		row27Column19.setCellValue("Feha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column19);
		row27Column20.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column20);
		row27Column21.setCellValue("Fecha últ. Movimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column21);
		row27Column22.setCellValue("Tipo de cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column22);
		row27Column23.setCellValue("Provincia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column23);
		row27Column24.setCellValue("CUIT");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column24);
		row27Column25.setCellValue("Estado origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column25);
		row27Column26.setCellValue("Motivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column26);
		row27Column27.setCellValue("Reclamo en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column27);
		row27Column28.setCellValue("Migrado en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column28);
		row27Column29.setCellValue("Estado en Deimos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column29);
		row27Column30.setCellValue("Pago/compensación en proceso");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column30);
		row27Column31.setCellValue("Op. Asociada + Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column31);
		row27Column32.setCellValue("Resultado validación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row27Column32);
		
		
		List<CobroCreditoDto> creditosList = new ArrayList<CobroCreditoDto>(cobroDto.getCreditos());
		List<? extends DTO> creditosFinal;
		Iterator<CobroCreditoDto> iterator = null;
		try {
			creditosFinal = Utilidad.guionesNull(creditosList);
			iterator = (Iterator<CobroCreditoDto>) creditosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			CobroCreditoDto credito = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell idClienteLegado = row.createCell(1);
			idClienteLegado.setCellValue(credito.getIdClienteLegado());
			definicionColumnas.getCeldasConBordes().add(idClienteLegado);
			HSSFCell cuenta = row.createCell(2);
			cuenta.setCellValue(credito.getCuenta());
			definicionColumnas.getCeldasConBordes().add(cuenta);
			HSSFCell sistemaDescripcion = row.createCell(3);
			sistemaDescripcion.setCellValue(credito.getSistemaDescripcion());
			definicionColumnas.getCeldasConBordes().add(sistemaDescripcion);
			HSSFCell tipoComprobante = row.createCell(4);
			tipoComprobante.setCellValue(credito.getTipoCredito());
			definicionColumnas.getCeldasConBordes().add(tipoComprobante);
			HSSFCell subtipoDocumentoDesc = row.createCell(5);
			subtipoDocumentoDesc.setCellValue(credito.getSubtipoDocumentoDesc());
			definicionColumnas.getCeldasConBordes().add(subtipoDocumentoDesc);
			HSSFCell nroDoc = row.createCell(6);
			nroDoc.setCellValue(credito.getNroDoc());
			definicionColumnas.getCeldasConBordes().add(nroDoc);
			HSSFCell numeroReferenciaMic = row.createCell(7);
			numeroReferenciaMic.setCellValue(credito.getNumeroReferenciaMic());
			definicionColumnas.getCeldasConBordes().add(numeroReferenciaMic);
			HSSFCell fechaValor = row.createCell(8);
			fechaValor.setCellValue(credito.getFechaValor());
			definicionColumnas.getCeldasConBordes().add(fechaValor);
			HSSFCell saldoMonOrigen = row.createCell(9);
			saldoMonOrigen.setCellValue(credito.getSaldoMonOrigen()!="-"? credito.getMoneda() + Utilidad.formatCurrency(new BigDecimal(credito.getSaldoMonOrigen().replaceAll(",", ".")), false, false):"-");
			definicionColumnas.getCeldasConBordes().add(saldoMonOrigen);
			HSSFCell moneda = row.createCell(10);
			moneda.setCellValue(credito.getMoneda());
			definicionColumnas.getCeldasConBordes().add(moneda);
			HSSFCell impMonOrigen = row.createCell(11);
			impMonOrigen.setCellValue(credito.getImpMonOrigen()!="-"? credito.getMoneda() + Utilidad.formatCurrency(new BigDecimal(credito.getImpMonOrigen().replaceAll(",", ".")), false, false):"-");
			definicionColumnas.getCeldasConBordes().add(impMonOrigen);
			HSSFCell impPesificado = row.createCell(12);
			String impPesificadoStr = "-";
			String saldoPesificadoStr = "-";
			if (!Validaciones.isNullEmptyOrDash(credito.getMoneda()) && !credito.getMoneda().equals(cobroDto.getMonedaOperacion())) {
				impPesificadoStr = credito.getImpPesificado()!="-"?Utilidad.formatCurrency(new BigDecimal(credito.getImpPesificado().replaceAll(",", ".")), true, false):"-";
				saldoPesificadoStr = credito.getSaldoPesificado()!="-"?Utilidad.formatCurrency(new BigDecimal(credito.getSaldoPesificado().replaceAll(",", ".")), true, false):"-";
			}
			impPesificado.setCellValue(impPesificadoStr);
			definicionColumnas.getCeldasConBordes().add(impPesificado);
			HSSFCell saldoPesificado = row.createCell(13);
			saldoPesificado.setCellValue(saldoPesificadoStr);
			definicionColumnas.getCeldasConBordes().add(saldoPesificado);
			HSSFCell importeAUtilizar = row.createCell(14);
			importeAUtilizar.setCellValue(credito.getImporteAUtilizar()!="-"?cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(new BigDecimal(credito.getImporteAUtilizar().replaceAll(",", ".")), false, false):"-");
			definicionColumnas.getCeldasConBordes().add(importeAUtilizar);
			HSSFCell fechaAltaFormateado = row.createCell(15);
			fechaAltaFormateado.setCellValue(credito.getFechaAltaFormateado());
			definicionColumnas.getCeldasConBordes().add(fechaAltaFormateado);
			HSSFCell fechaIngresoRecibo = row.createCell(16);
			fechaIngresoRecibo.setCellValue(credito.getFechaIngresoRecibo());
			definicionColumnas.getCeldasConBordes().add(fechaIngresoRecibo);
			HSSFCell fechaDeposito = row.createCell(17);
			fechaDeposito.setCellValue(credito.getFechaDeposito());
			definicionColumnas.getCeldasConBordes().add(fechaDeposito);
			HSSFCell fechaTrans = row.createCell(18);
			fechaTrans.setCellValue(credito.getFechaTrans());
			definicionColumnas.getCeldasConBordes().add(fechaTrans);
			HSSFCell fechaEmision = row.createCell(19);
			fechaEmision.setCellValue(credito.getFechaEmision());
			definicionColumnas.getCeldasConBordes().add(fechaEmision);
			HSSFCell fechaVenc = row.createCell(20);
			fechaVenc.setCellValue(credito.getFechaVenc());
			definicionColumnas.getCeldasConBordes().add(fechaVenc);
			HSSFCell fechaUltimoMov = row.createCell(21);
			fechaUltimoMov.setCellValue(credito.getFechaUltimoMov());
			definicionColumnas.getCeldasConBordes().add(fechaUltimoMov);
			HSSFCell tipoCambio = row.createCell(22);
			if(!Validaciones.isNullEmptyOrDash(credito.getTipoCambio()) && !Validaciones.isNullEmptyOrDash(credito.getMoneda()) && !credito.getMoneda().equals(cobroDto.getMonedaOperacion())){
				tipoCambio.setCellValue("?"+credito.getTipoCambio());
			} else {
				tipoCambio.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoCambio);
			HSSFCell provincia = row.createCell(23);
			provincia.setCellValue(credito.getProvincia());
			definicionColumnas.getCeldasConBordes().add(provincia);
			HSSFCell cuit = row.createCell(24);
			cuit.setCellValue(credito.getCuit());
			definicionColumnas.getCeldasConBordes().add(cuit);
			HSSFCell estadoOrigen = row.createCell(25);
			estadoOrigen.setCellValue(credito.getEstadoOrigen());
			definicionColumnas.getCeldasConBordes().add(estadoOrigen);
			HSSFCell motivo = row.createCell(26);
			if(!Validaciones.isObjectNull(credito.getMotivo()))
			motivo.setCellValue(credito.getMotivo().descripcion());
			definicionColumnas.getCeldasConBordes().add(motivo);
			HSSFCell reclamoEnOrigen = row.createCell(27);
			if(!Validaciones.isObjectNull(credito.getMarcaReclamo())){
				reclamoEnOrigen.setCellValue(credito.getMarcaReclamo().descripcion());
			}
			definicionColumnas.getCeldasConBordes().add(reclamoEnOrigen);
			HSSFCell migradoEnOrigen = row.createCell(28);
			if(!Validaciones.isObjectNull(credito.getMarcaMigradoDeimos())){
				migradoEnOrigen.setCellValue(credito.getMarcaMigradoDeimos().getDescripcion());// ver migradoEnOrigen si es desc corta o no
			}
			definicionColumnas.getCeldasConBordes().add(migradoEnOrigen);
			HSSFCell estadoDeimos = row.createCell(29);
			estadoDeimos.setCellValue(credito.getEstadoDeimos());
			definicionColumnas.getCeldasConBordes().add(estadoDeimos);
			HSSFCell pagoCompEnProceso = row.createCell(30);
			pagoCompEnProceso.setCellValue(credito.getMarcaPagoCompensacionEnProcesoShivaDesc());//ver pago compensacion en proceso (harcodiado, todabia no existe, corregir en el futuro).
			definicionColumnas.getCeldasConBordes().add(pagoCompEnProceso);
			HSSFCell opeAsocAnalista = row.createCell(31);
			if (!Validaciones.isObjectNull(credito.getOpeAsocAnalista())) {
				String opeAsocAnalistVec[] = credito.getOpeAsocAnalista().split("<br>");
				opeAsocAnalista.setCellValue(StringUtils.join(opeAsocAnalistVec, " "));
			} else {
				opeAsocAnalista.setCellValue(credito.getOpeAsocAnalista());
			}
			definicionColumnas.getCeldasConBordes().add(opeAsocAnalista);
			HSSFCell resultadoValidacion = row.createCell(32);
			resultadoValidacion.setCellValue(credito.getResultadoValidacionDescripcionError());
			definicionColumnas.getCeldasConBordes().add(resultadoValidacion);
			
			rowCrear++;
		}
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaOtrosMediosDePago(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		rowActualFinal = rowActualFinal+2;
		HSSFRow row31 = sheet.createRow(rowActualFinal);
		rowActualFinal = rowActualFinal+1;
		HSSFRow row32 = sheet.createRow(rowActualFinal);
		row32.setHeightInPoints(25);
		HSSFCell row31Column1 = row31.createCell(1);
		HSSFCell row32Column1 = row32.createCell(1);
		HSSFCell row32Column2 = row32.createCell(2);
		HSSFCell row32Column3 = row32.createCell(3);
		HSSFCell row32Column4 = row32.createCell(4);
		HSSFCell row32Column5 = row32.createCell(5);
		HSSFCell row32Column6 = row32.createCell(6);
		HSSFCell row32Column7 = row32.createCell(7);
		HSSFCell row32Column8 = row32.createCell(8);
		HSSFCell row32Column9 = row32.createCell(9);
		//HSSFCell row32Column7 = row32.createCell(7);

		row31Column1.setCellValue("Otros Medios de Pago");
		definicionColumnas.getPalabrasEnNegrita().add(row31Column1);
		
		row32Column1.setCellValue("Tipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column1);
		row32Column2.setCellValue("Sub Tipo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column2);
		row32Column3.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column3);
		row32Column4.setCellValue("Nro. de Compensación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column4);
		row32Column5.setCellValue("Nro. de Acta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column5);
		row32Column6.setCellValue("Fecha");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column6);
		row32Column7.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column7);
		row32Column8.setCellValue("Provincia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column8);
		row32Column9.setCellValue("Nro de Documento Interno");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column9);
		//row32Column7.setCellValue("Medio de pago en Proceso");
		//definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column7);
		
		
		List<CobroMedioDePagoDto> medioDePagosList = new ArrayList<CobroMedioDePagoDto>(cobroDto.getMedios());
		List<? extends DTO> medioDePagosFinal;
		Iterator<CobroMedioDePagoDto> iterator = null;
		try {
			medioDePagosFinal = Utilidad.guionesNull(medioDePagosList);
			iterator = (Iterator<CobroMedioDePagoDto>) medioDePagosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			CobroMedioDePagoDto medioDePago = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell tipoMedioPago = row.createCell(1);
			tipoMedioPago.setCellValue(medioDePago.getMpTipoCredito()!=null?medioDePago.getMpTipoCredito().getDescripcion():"-");
			definicionColumnas.getCeldasConBordes().add(tipoMedioPago);
			HSSFCell subTipo = row.createCell(2);
			subTipo.setCellValue(medioDePago.getSubTipoDescripcion());
			definicionColumnas.getCeldasConBordes().add(subTipo);
			HSSFCell importe = row.createCell(3);
			importe.setCellValue(medioDePago.getImporte()!="-"? MonedaEnum.getEnumByName(medioDePago.getMonedaImporteUtilizar()).getSigno2() + Utilidad.formatCurrency(new BigDecimal(medioDePago.getImporte().replaceAll(",", ".")), false, false):"-");
			definicionColumnas.getCeldasConBordes().add(importe);
			HSSFCell nroCompensacion = row.createCell(4);
			nroCompensacion.setCellValue(medioDePago.getNroCompensacion());
			definicionColumnas.getCeldasConBordes().add(nroCompensacion);
			HSSFCell nroActa = row.createCell(5);
			nroActa.setCellValue(medioDePago.getNroActa());
			definicionColumnas.getCeldasConBordes().add(nroActa);
			HSSFCell fecha = row.createCell(6);
			fecha.setCellValue(medioDePago.getFecha());
			definicionColumnas.getCeldasConBordes().add(fecha);
			HSSFCell clientesLegados = row.createCell(7);
			clientesLegados.setCellValue(medioDePago.getClientesLegados());
			definicionColumnas.getCeldasConBordes().add(clientesLegados);
			HSSFCell provincia = row.createCell(8);
			provincia.setCellValue(medioDePago.getProvincia());
			definicionColumnas.getCeldasConBordes().add(provincia);
			HSSFCell nroDeDocumentoInterno = row.createCell(9);
			nroDeDocumentoInterno.setCellValue(medioDePago.getNroDeDocumentoInterno());
			definicionColumnas.getCeldasConBordes().add(nroDeDocumentoInterno);
			/*
			HSSFCell medioDePagoEnProceso = row.createCell(7);
			if(!(Validaciones.isObjectNull(medioDePago.getCheckMedioPagoEnProceso())) && ("S".equals(medioDePago.getCheckMedioPagoEnProceso().getDescripcionCorta()))){//ver
				medioDePagoEnProceso.setCellValue("SI");
			}else{
				medioDePagoEnProceso.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(medioDePagoEnProceso);
			*/
			
			rowCrear++;
		}
		
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaComprobantes(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+2;
		HSSFRow row36 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row37 = sheet.createRow(rowActualFinal);
		row37.setHeightInPoints(25);
		HSSFCell row36Column1 = row36.createCell(1);
		HSSFCell row37Column1 = row37.createCell(1);
		HSSFCell row37Column2 = row37.createCell(2);

		row36Column1.setCellValue("Comprobantes");
		definicionColumnas.getPalabrasEnNegrita().add(row36Column1);
		
		row37Column1.setCellValue("Nombre del archivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row37Column1);
		row37Column2.setCellValue("Descripción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row37Column2);
		
		List<ComprobanteDto> comprobantesList = new ArrayList<ComprobanteDto>(cobroDto.getListaComprobantes());
		List<? extends DTO> comprobantesFinal;
		Iterator<ComprobanteDto> iterator = null;
		
		String nombreArchivoOtrosDeb = "Archivo Importacion de Otros Debitos.";
		
		for (ComprobanteDto comprobante : cobroDto.getListaComprobanteOtrosDebito()) {
			if (nombreArchivoOtrosDeb.equals(comprobante.getDescripcionArchivo())){
				comprobantesList.add(comprobante);
			}
		}
		
		try {
			comprobantesFinal = Utilidad.guionesNull(comprobantesList);
			iterator = (Iterator<ComprobanteDto>) comprobantesFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal=rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			ComprobanteDto comprobante = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell nombreArchivo = row.createCell(1);
			nombreArchivo.setCellValue(comprobante.getNombreArchivo());
			definicionColumnas.getCeldasConBordes().add(nombreArchivo);
			HSSFCell descripcionArchivo = row.createCell(2);
			descripcionArchivo.setCellValue(comprobante.getDescripcionArchivo());
			definicionColumnas.getCeldasConBordes().add(descripcionArchivo);
			
			rowCrear++;
		}
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	protected int fillTotalesDocumentos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		
		rowActualFinal=rowActualFinal+2;
		HSSFRow row41 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row42 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row43 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row44 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row45 = sheet.createRow(rowActualFinal);

		
		HSSFCell row41Column1 = row41.createCell(1);
		HSSFCell row42Column1 = row42.createCell(1);
		HSSFCell row43Column1 = row43.createCell(1);
		HSSFCell row44Column1 = row44.createCell(1);
		HSSFCell row45Column1 = row45.createCell(1);
		HSSFCell row42Column2 = row42.createCell(2);
		HSSFCell row43Column2 = row43.createCell(2);
		HSSFCell row44Column2 = row44.createCell(2);
		HSSFCell row45Column2 = row45.createCell(2);

		
		row41Column1.setCellValue("Totales Documentos");
		definicionColumnas.getPalabrasEnNegritaYsubrayado().add(row41Column1);
		
		row42Column1.setCellValue("Total Débitos: ");
		row43Column1.setCellValue("Total Medios de Pago: ");
		row44Column1.setCellValue("Diferencia: ");
		row45Column1.setCellValue("Tratamiento de Diferencia: ");
		
		BigDecimal sumaDebitos = cobroDto.getSumaImpDebitos();
		BigDecimal sumaOtrosDebitos = cobroDto.getSumaImpOtrosDebitos();
		BigDecimal sumaCreditos = cobroDto.getSumaImpCreditos();
		BigDecimal sumaMediosPago = cobroDto.getSumaImpMediosDePago();
		BigDecimal sumaDebitosFinal = new BigDecimal(0);
		BigDecimal sumaCreditosMediosPago = new BigDecimal(0);
		
		if(!Validaciones.isObjectNull(sumaDebitos)){
			sumaDebitosFinal = sumaDebitos;
		}	
		
		
		row42Column2.setCellValue(!Validaciones.isObjectNull(sumaDebitos) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(sumaDebitos, false, false):"-");
		if(!Validaciones.isObjectNull(sumaOtrosDebitos)){
			sumaDebitosFinal = (sumaOtrosDebitos).add(sumaDebitos);
		}	
		
		
		row42Column2.setCellValue(!Validaciones.isObjectNull(sumaDebitosFinal) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(sumaDebitosFinal, false, false):"-");
		
		
		
		if(!Validaciones.isObjectNull(sumaCreditos) && !Validaciones.isObjectNull(sumaMediosPago)){
			sumaCreditosMediosPago = sumaCreditos.add(sumaMediosPago);
		}
		row43Column2.setCellValue(cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(sumaCreditosMediosPago, false, false));
		row44Column2.setCellValue(cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(sumaDebitosFinal.subtract(sumaCreditosMediosPago), false, false));
		
		String tratamientoDiferencia = cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia();
		if(!Validaciones.isObjectNull(tratamientoDiferencia)){
			TipoTratamientoDiferenciaEnum tipoTratamientoDiferenciaEnum = TipoTratamientoDiferenciaEnum.getEnumByName(tratamientoDiferencia);
			row45Column2.setCellValue(tipoTratamientoDiferenciaEnum.getDescripcion());

			List<TipoTratamientoDiferenciaEnum> traDifConCampos = Arrays.asList(
				new TipoTratamientoDiferenciaEnum[] {
					TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS,
					TipoTratamientoDiferenciaEnum.APLICACION_MANUAL,
					TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC
				}
			);
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(tipoTratamientoDiferenciaEnum)) {
				
				
				// Sistema destino
				HSSFCell row45Column3 = row45.createCell(3); //Clave
				HSSFCell row45Column4 = row45.createCell(4); //Valor
	
				row45Column3.setCellValue("Sistema destino: ");
				if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getSistemaDestino())) {
					row45Column4.setCellValue("");
				} else {
					SistemaEnum sistema = SistemaEnum.getEnumByDescripcionCorta(cobroDto.getTratamientoDiferencia().getSistemaDestino());
					row45Column4.setCellValue(sistema.getDescripcion());
				}
				
				HSSFCell row45Column5 = row45.createCell(5); //Clave
				HSSFCell row45Column6 = row45.createCell(6); //Valor
				
				row45Column5.setCellValue("Referencia: ");
				if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getReferencia())) {
					row45Column6.setCellValue("");
				} else {
					row45Column6.setCellValue(cobroDto.getTratamientoDiferencia().getReferencia());
				}
				
			} else if (!traDifConCampos.contains(tipoTratamientoDiferenciaEnum)) {
				// Trámite de reintegro
				HSSFCell row45Column3 = row45.createCell(3); //Clave
				HSSFCell row45Column4 = row45.createCell(4); //Valor

				// Fecha alta trámite de reintegro:
				HSSFCell row45Column5 = row45.createCell(5); //Clave
				HSSFCell row45Column6 = row45.createCell(6); //Valor

				row45Column3.setCellValue("Trámite de reintegro: ");
				row45Column5.setCellValue("Fecha alta trámite de reintegro: ");

				if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getNumeroTramiteReintegro())) {
					row45Column4.setCellValue("");
				} else {
					row45Column4.setCellValue(cobroDto.getTratamientoDiferencia().getNumeroTramiteReintegro());
				}
				if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getFechaAltaTramiteReintegro())) {
					row45Column6.setCellValue("");
				} else {
					row45Column6.setCellValue(cobroDto.getTratamientoDiferencia().getFechaAltaTramiteReintegro());
				}
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.equals(tipoTratamientoDiferenciaEnum)) {
					rowActualFinal=rowActualFinal+1;
					HSSFRow row46 = sheet.createRow(rowActualFinal);
					// tabulado
					HSSFCell row46Column1 = row46.createCell(1); //Clave
					HSSFCell row46Column2 = row46.createCell(2); //Valor
					// Sistema destino
					HSSFCell row46Column3 = row46.createCell(3); //Clave
					HSSFCell row46Column4 = row46.createCell(4); //Valor
					// Sistema Línea
					HSSFCell row46Column5 = row46.createCell(5); //Clave
					HSSFCell row46Column6 = row46.createCell(6); //Valor
					// Sistema Acuerdo de facturación
					HSSFCell row46Column7 = row46.createCell(7); //Clave
					HSSFCell row46Column8 = row46.createCell(8); //Valor

					row46Column1.setCellValue("");
					row46Column2.setCellValue("");

					row46Column3.setCellValue("Sistema destino: ");
					if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getSistemaDestino())) {
						row46Column4.setCellValue("");
					} else {
						SistemaEnum sistema = SistemaEnum.getEnumByDescripcionCorta(cobroDto.getTratamientoDiferencia().getSistemaDestino());
						row46Column4.setCellValue(sistema.getDescripcion());
					}
					row46Column5.setCellValue("Sistema Línea: ");
					if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getLinea())) {
						row46Column6.setCellValue("");
					} else {
						row46Column6.setCellValue(cobroDto.getTratamientoDiferencia().getLinea());
					}
					row46Column7.setCellValue("Sistema Acuerdo de facturación: ");
					if (Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia().getAcuerdoFacturacion())) {
						row46Column8.setCellValue("");
					} else {
						row46Column8.setCellValue(cobroDto.getTratamientoDiferencia().getAcuerdoFacturacion());
					}
				}
				
				
				
			}
		
		}
		
		return rowActualFinal;
	}
	
	/**
	 * @author u587496 Guido.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaComprobantesApliManual(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+2;
		HSSFRow row36 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row37 = sheet.createRow(rowActualFinal);
		row37.setHeightInPoints(25);
		HSSFCell row36Column1 = row36.createCell(1);
		HSSFCell row37Column1 = row37.createCell(1);
		HSSFCell row37Column2 = row37.createCell(2);

		row36Column1.setCellValue("Detalle de la Aplicación Manual");
		definicionColumnas.getPalabrasEnNegrita().add(row36Column1);
		
		row37Column1.setCellValue("Nombre del archivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row37Column1);
		row37Column2.setCellValue("Descripción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row37Column2);
		
		List<ComprobanteDto> comprobantesList = new ArrayList<ComprobanteDto>(cobroDto.getListaComprobanteAplicacionManual());
		List<? extends DTO> comprobantesFinal;
		Iterator<ComprobanteDto> iterator = null;
		try {
			comprobantesFinal = Utilidad.guionesNull(comprobantesList);
			iterator = (Iterator<ComprobanteDto>) comprobantesFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal=rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			ComprobanteDto comprobante = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell nombreArchivo = row.createCell(1);
			nombreArchivo.setCellValue(comprobante.getNombreArchivo());
			definicionColumnas.getCeldasConBordes().add(nombreArchivo);
			HSSFCell descripcionArchivo = row.createCell(2);
			descripcionArchivo.setCellValue(comprobante.getDescripcionArchivo());
			definicionColumnas.getCeldasConBordes().add(descripcionArchivo);
			
			rowCrear++;
		}
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	@SuppressWarnings("unchecked")
	protected int fillTablaTransacciones(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+3;
		HSSFRow row47 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row48 = sheet.createRow(rowActualFinal);
		row48.setHeightInPoints(25);
		
		HSSFCell row47Column1 = row47.createCell(1);
		HSSFCell row48Column1 = row48.createCell(1);
		HSSFCell row48Column2 = row48.createCell(2);
		HSSFCell row48Column3 = row48.createCell(3);
		HSSFCell row48Column4 = row48.createCell(4);
		HSSFCell row48Column5 = row48.createCell(5);
		HSSFCell row48Column6 = row48.createCell(6);
		HSSFCell row48Column7 = row48.createCell(7);
		HSSFCell row48Column8 = row48.createCell(8);
		HSSFCell row48Column9 = row48.createCell(9);
		HSSFCell row48Column10 = row48.createCell(10);
		HSSFCell row48Column11 = row48.createCell(11);
		HSSFCell row48Column12 = row48.createCell(12);
		HSSFCell row48Column13 = row48.createCell(13);
		HSSFCell row48Column14 = row48.createCell(14);
		HSSFCell row48Column15 = row48.createCell(15);
		HSSFCell row48Column16 = row48.createCell(16);
		HSSFCell row48Column17 = row48.createCell(17);
		HSSFCell row48Column18 = row48.createCell(18);
		HSSFCell row48Column19 = row48.createCell(19);
		HSSFCell row48Column20 = row48.createCell(20);
		HSSFCell row48Column21 = row48.createCell(21);
		HSSFCell row48Column22 = row48.createCell(22);
		HSSFCell row48Column23 = row48.createCell(23);
		HSSFCell row48Column24 = row48.createCell(24);
		HSSFCell row48Column25 = row48.createCell(25);
		HSSFCell row48Column26 = row48.createCell(26);
		HSSFCell row48Column27 = row48.createCell(27);
		HSSFCell row48Column28 = row48.createCell(28);
		HSSFCell row48Column29 = row48.createCell(29);
		HSSFCell row48Column30 = row48.createCell(30);
		HSSFCell row48Column31 = row48.createCell(31);
		HSSFCell row48Column32 = row48.createCell(32);
		HSSFCell row48Column33 = row48.createCell(33);

		row47Column1.setCellValue("Transacciones");
		definicionColumnas.getPalabrasEnNegrita().add(row47Column1);
		
		row48Column1.setCellValue("Grupo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column1);
		row48Column2.setCellValue("Nro. Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column2);
		row48Column3.setCellValue("Estado Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column3);
		row48Column4.setCellValue("Sistema Doc");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column4);
		row48Column5.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column5);
		row48Column6.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column6);
		row48Column7.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column7);
		row48Column8.setCellValue("Nro. Referencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column8);
		row48Column9.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column9);
		row48Column10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column10);
		row48Column11.setCellValue("Fecha Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column11);
		row48Column12.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column12);
		row48Column13.setCellValue("Tipo de cambio al cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column13);
		row48Column14.setCellValue("Tipo de cambio a fecha emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column14);
		row48Column15.setCellValue("Importe cobrado en moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column15);
		row48Column16.setCellValue("Sistema MP");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column16);
		row48Column17.setCellValue("Tipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column17);
		row48Column18.setCellValue("Subtipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column18);
		row48Column19.setCellValue("Referencia Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column19);
		row48Column20.setCellValue("Fecha Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column20);
		row48Column21.setCellValue("Moneda Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column21);
		row48Column22.setCellValue("Importe Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column22);
		row48Column23.setCellValue("Tipo Cambio Al Cobro Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column23);
		row48Column24.setCellValue("Importe Cobrado Moneda Origen Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column24);
		row48Column25.setCellValue("Intereses");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column25);
		row48Column26.setCellValue("Trasladar intereses");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column26);
		row48Column27.setCellValue("Porcentaje a bonificar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column27);
		row48Column28.setCellValue("Importe a bonificar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column28);
		row48Column29.setCellValue("Acuerdo Facturación destino de cargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column29);
		row48Column30.setCellValue("Estado Acuerdo Facturación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column30);
		row48Column31.setCellValue("Mensaje Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column31);
		row48Column32.setCellValue("Mensaje Débito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column32);
		row48Column33.setCellValue("Mensaje Crédito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row48Column33);
		
		List<CobroTransaccionDto> transaccionesList = new ArrayList<CobroTransaccionDto>(cobroDto.getTransacciones());
		Set<CobroTransaccionDto> setOrdenadoTransaccionesDto = new TreeSet<CobroTransaccionDto>(new ComparatorOrdenGrillaTransaccionDto());
		
		List<? extends DTO> transaccionesFinal;
		Iterator<CobroTransaccionDto> iterator = null;
		try {
			transaccionesFinal = Utilidad.guionesNull(transaccionesList);
			setOrdenadoTransaccionesDto.addAll((Collection<? extends CobroTransaccionDto>) transaccionesFinal);
			iterator = (Iterator<CobroTransaccionDto>) setOrdenadoTransaccionesDto.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		
		while(iterator.hasNext()){
			CobroTransaccionDto transaccion = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			
			HSSFCell grupo = row.createCell(1);
			grupo.setCellValue(transaccion.getApocopeGrupo());
			definicionColumnas.getCeldasConBordes().add(grupo);
			
			HSSFCell numeroTransaccion = row.createCell(2);
			numeroTransaccion.setCellValue(transaccion.getNumeroTransaccionFicticioFormateado());
			definicionColumnas.getCeldasConBordes().add(numeroTransaccion);
			HSSFCell estadoTransaccion = row.createCell(3);
			estadoTransaccion.setCellValue(transaccion.getEstadoTransaccion());
			definicionColumnas.getCeldasConBordes().add(estadoTransaccion);
			HSSFCell sistemaDoc = row.createCell(4);
			sistemaDoc.setCellValue(transaccion.getSistemaDoc());
			definicionColumnas.getCeldasConBordes().add(sistemaDoc);
			HSSFCell tipoDocumento = row.createCell(5);
			tipoDocumento.setCellValue(transaccion.getTipoDocumento());
			definicionColumnas.getCeldasConBordes().add(tipoDocumento);
			HSSFCell subtipoDocumento = row.createCell(6);
			subtipoDocumento.setCellValue(transaccion.getSubtipoDocumento());
			definicionColumnas.getCeldasConBordes().add(subtipoDocumento);
			HSSFCell nroDoc = row.createCell(7);
			nroDoc.setCellValue(transaccion.getNroDoc());
			definicionColumnas.getCeldasConBordes().add(nroDoc);
			HSSFCell numeroReferenciaMic = row.createCell(8);
			numeroReferenciaMic.setCellValue(transaccion.getNumeroReferencia());
			definicionColumnas.getCeldasConBordes().add(numeroReferenciaMic);
			HSSFCell fechaVenc = row.createCell(9);
			fechaVenc.setCellValue(transaccion.getFechaVenc());
			definicionColumnas.getCeldasConBordes().add(fechaVenc);
			HSSFCell moneda = row.createCell(10);
			moneda.setCellValue(transaccion.getMoneda());
			definicionColumnas.getCeldasConBordes().add(moneda);
			HSSFCell fechaCobro = row.createCell(11);
			fechaCobro.setCellValue(transaccion.getFechaCobro());
			definicionColumnas.getCeldasConBordes().add(fechaCobro);
			HSSFCell importe = row.createCell(12);
			importe.setCellValue(transaccion.getImporte());
			definicionColumnas.getCeldasConBordes().add(importe);
			
			//Se pone el signo "?" adelante del valor que contiene la celda, para que cuando se le da estilo, se lo alinea a la derecha. En el estilo se le borra el signo
			HSSFCell tipoDeCambioFechaCobro = row.createCell(13);
			if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaCobro()) && !Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(cobroDto.getMonedaOperacion()) && transaccion.getColorLetraRegistro().equals("0")){
				tipoDeCambioFechaCobro.setCellValue("?"+transaccion.getTipoDeCambioFechaCobro());
			} else {
				tipoDeCambioFechaCobro.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoDeCambioFechaCobro);
			HSSFCell tipoDeCambioFechaEmision = row.createCell(14);
			if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaEmision()) && !Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(cobroDto.getMonedaOperacion()) && transaccion.getColorLetraRegistro().equals("1")){
				tipoDeCambioFechaEmision.setCellValue("?"+transaccion.getTipoDeCambioFechaEmision());
			} else {
				tipoDeCambioFechaEmision.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoDeCambioFechaEmision);
			HSSFCell importeAplicadoFechaEmisionMonedaOrigen = row.createCell(15);
			if(!Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(cobroDto.getMonedaOperacion())){
				importeAplicadoFechaEmisionMonedaOrigen.setCellValue(transaccion.getImporteAplicadoFechaEmisionMonedaOrigen());
			} else {
				importeAplicadoFechaEmisionMonedaOrigen.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(importeAplicadoFechaEmisionMonedaOrigen);
			HSSFCell sistemaMedioPago = row.createCell(16);
			sistemaMedioPago.setCellValue(transaccion.getSistemaMedioPago());
			definicionColumnas.getCeldasConBordes().add(sistemaMedioPago);
			HSSFCell tipoMedioPago = row.createCell(17);
			tipoMedioPago.setCellValue(transaccion.getTipoMedioPago());
			definicionColumnas.getCeldasConBordes().add(tipoMedioPago);
			HSSFCell subtipoMedioPago = row.createCell(18);
			subtipoMedioPago.setCellValue(transaccion.getSubtipoMedioPago());
			definicionColumnas.getCeldasConBordes().add(subtipoMedioPago);
			HSSFCell referenciaMedioPago = row.createCell(19);
			referenciaMedioPago.setCellValue(transaccion.getReferenciaMedioPago());
			definicionColumnas.getCeldasConBordes().add(referenciaMedioPago);
			HSSFCell fechaMedioPago = row.createCell(20);
			fechaMedioPago.setCellValue(transaccion.getFechaMedioPago());
			definicionColumnas.getCeldasConBordes().add(fechaMedioPago);
			HSSFCell monedaMedioPago = row.createCell(21);
			monedaMedioPago.setCellValue(transaccion.getMonedaMedioPago());
			definicionColumnas.getCeldasConBordes().add(monedaMedioPago);
			HSSFCell importeMedioPago = row.createCell(22);
			importeMedioPago.setCellValue(transaccion.getImporteMedioPago());
			definicionColumnas.getCeldasConBordes().add(importeMedioPago);
			HSSFCell  tipoCambioAlCobroMedioPago= row.createCell(23);
			if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaCobroMedioPago()) && !Validaciones.isNullEmptyOrDash(transaccion.getMonedaMedioPago()) && !transaccion.getMonedaMedioPago().equals(cobroDto.getMonedaOperacion())){
				tipoCambioAlCobroMedioPago.setCellValue("?"+transaccion.getTipoDeCambioFechaCobroMedioPago());
			} else {
				tipoCambioAlCobroMedioPago.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoCambioAlCobroMedioPago);
			HSSFCell importeCobradoMonedaOrigenMedioPago = row.createCell(24);
			if(!Validaciones.isNullEmptyOrDash(transaccion.getMonedaMedioPago()) && !transaccion.getMonedaMedioPago().equals(cobroDto.getMonedaOperacion())){
				importeCobradoMonedaOrigenMedioPago.setCellValue(transaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago());
			} else {
				importeCobradoMonedaOrigenMedioPago.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(importeCobradoMonedaOrigenMedioPago);
			HSSFCell intereses = row.createCell(25);
			
			if (SiNoEnum.SI.equals(transaccion.getMostrarAsteriscos())) {
				intereses.setCellValue("**");
			} else {
				if (!Validaciones.isNullEmptyOrDash(transaccion.getMoneda())) {
					intereses.setCellValue(transaccion.getIntereses() + transaccion.getMoneda());
				} else {
					intereses.setCellValue(transaccion.getIntereses() + transaccion.getMonedaMedioPago());
				}
				
			}
			
			
			definicionColumnas.getCeldasConBordes().add(intereses);
			HSSFCell trasladarIntereses = row.createCell(26);
			int resultado = cobroOnlineServicio.validarHabilitacionCampo(transaccion);
			if (resultado > 0) {
				//cuando el interes es cero, se muestra un guion
				if (resultado == 3 || resultado == 4) {
					trasladarIntereses.setCellValue("-");
					transaccion.setPorcABonificar("-");
					transaccion.setImporteABonificar("-");
				}else if (resultado == 2 && transaccion.isFechaCobroMenorIgualFechaVto()) {
						trasladarIntereses.setCellValue("-");
						transaccion.setPorcABonificar("-");
						transaccion.setImporteABonificar("-");
				} else {
					if(transaccion.getTrasladarIntereses()){
						trasladarIntereses.setCellValue("SI");
					} else {
						trasladarIntereses.setCellValue("NO");
					}
				}
			} else {
				//si es reintegro NO a proxima factura, se muestra un guion	 
				trasladarIntereses.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(trasladarIntereses);
			
			
			HSSFCell porcABonificar = row.createCell(27);
			if (SiNoEnum.SI.equals(transaccion.getMostrarAsteriscos())) {
				if (!transaccion.getTrasladarIntereses()) {
					porcABonificar.setCellValue("100%");
				} else {
					porcABonificar.setCellValue("");
				}
				
			} else if (!Validaciones.isNullEmptyOrDash(transaccion.getPorcABonificar())) {
				porcABonificar.setCellValue(transaccion.getPorcABonificar() + "%");
			} else {
				porcABonificar.setCellValue(transaccion.getPorcABonificar());
			}
			definicionColumnas.getCeldasConBordes().add(porcABonificar);
			
			
			HSSFCell importeABonificar = row.createCell(28);
			importeABonificar.setCellValue(transaccion.getImporteABonificar());
			
			definicionColumnas.getCeldasConBordes().add(importeABonificar);
			HSSFCell acuerdoDestinoCargos = row.createCell(29);
			// Destino Cargo Comienzo
			// Se copia el comportamiento del front-end definido en shiva-conf-cobro
			String data = transaccion.getAcuerdoDestinoCargos();
			if (resultado > 0) {
				
				if (resultado == 3 || resultado == 4) {
					data = transaccion.getAcuerdoDestinoCargos();
					if (resultado == 3) {
						data = "-";
					}
				}
				if ("-".equals(data)) {
					data = "-";
				}
			} else {
				// si es reintegro NO a proxima  factura, se grisa y se borra  el campo
				data = "-";
			}
			acuerdoDestinoCargos.setCellValue(data);
			// Destino Cargo END 
			definicionColumnas.getCeldasConBordes().add(acuerdoDestinoCargos);
			// Se copia el comportamiento del front-end definido en shiva-conf-cobro
			HSSFCell estadoAcuerdoDestinoCargos = row.createCell(30);
			String dataEstadoAcuerdo = transaccion.getEstadoAcuerdoDestinoCargos();
			if (resultado > 0) {
				//cuando el interes es cero, se muestra un guion
				if(resultado == 3) {
					dataEstadoAcuerdo = "-";
				}
			} else {
				//si es reintegro NO a proxima factura, se muestra un guion	 
				dataEstadoAcuerdo = "-";
			}
			estadoAcuerdoDestinoCargos.setCellValue(dataEstadoAcuerdo);
			definicionColumnas.getCeldasConBordes().add(estadoAcuerdoDestinoCargos);
			//
			HSSFCell mensajeTransaccion = row.createCell(31);
			mensajeTransaccion.setCellValue(transaccion.getMensajeTransaccion());
			definicionColumnas.getCeldasConBordes().add(mensajeTransaccion);
			HSSFCell mensajeDebito = row.createCell(32);
			mensajeDebito.setCellValue(transaccion.getMensajeDebito());
			definicionColumnas.getCeldasConBordes().add(mensajeDebito);
			HSSFCell mensajeCredito = row.createCell(33);
			mensajeCredito.setCellValue(transaccion.getMensajeCredito());
			definicionColumnas.getCeldasConBordes().add(mensajeCredito);
			
			rowCrear++;
		}
		return rowCrear;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 */
	protected int fillTotalesIntereses(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+3;
		HSSFRow row52 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row53 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row54 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row55 = sheet.createRow(rowActualFinal);

		HSSFCell row52Column1 = row52.createCell(1);
		HSSFCell row53Column1 = row53.createCell(1);
		HSSFCell row54Column1 = row54.createCell(1);
		HSSFCell row55Column1 = row55.createCell(1);
		HSSFCell row53Column2 = row53.createCell(2);
		HSSFCell row54Column2 = row54.createCell(2);
		HSSFCell row55Column2 = row55.createCell(2);
		HSSFCell row53Column3 = row53.createCell(3);
		HSSFCell row54Column3 = row54.createCell(3);
		HSSFCell row55Column3 = row55.createCell(3);
		HSSFCell row53Column4 = row53.createCell(4);
		HSSFCell row54Column4 = row54.createCell(4);
		HSSFCell row55Column4 = row55.createCell(4);

		row52Column1.setCellValue("Totales Intereses");
		definicionColumnas.getPalabrasEnNegritaYsubrayado().add(row52Column1);

		row53Column1.setCellValue("Intereses Trasladados: ");
		row54Column1.setCellValue("Intereses Bonificados: ");
		row55Column1.setCellValue("Intereses por Reintegro: ");

		TotalAcumuladoresTransacciones acu = cobroOnlineServicio.calculaTotalInteresesTransacciones(cobroDto);
		row53Column2.setCellValue(Utilidad.formatCurrency(acu.getTotalTraslados(), 2, MonedaEnum.PES.getSigno2()));
		row54Column2.setCellValue(Utilidad.formatCurrency(acu.getTotalBonificados(), 2, MonedaEnum.PES.getSigno2()));
		row55Column2.setCellValue(Utilidad.formatCurrency(acu.getTotalReintegro(), 2, MonedaEnum.PES.getSigno2()));

		row53Column3.setCellValue("Intereses Trasladados: ");
		row54Column3.setCellValue("Intereses Bonificados: ");
		row55Column3.setCellValue("Intereses por Reintegro: ");
		
		//provisorio hasta q haya logica para los totalizadores de intereses en dolares
		row53Column4.setCellValue(Utilidad.formatCurrency(acu.getTotalTrasladosU$S(), 2, MonedaEnum.DOL.getSigno2()));
		row54Column4.setCellValue(Utilidad.formatCurrency(acu.getTotalBonificadosU$S(), 2, MonedaEnum.DOL.getSigno2()));
		row55Column4.setCellValue(Utilidad.formatCurrency(acu.getTotalReintegroU$S(), 2, MonedaEnum.DOL.getSigno2()));
		
		return rowActualFinal;
	}

	@SuppressWarnings("unchecked")
	private int fillTablaDocumentosCap (HSSFWorkbook workbook, HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) {
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+2;
		HSSFRow row56 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row57 = sheet.createRow(rowActualFinal);
		row57.setHeightInPoints(25);
		HSSFCell row56Column1 = row56.createCell(1);
		HSSFCell row57Column1 = row57.createCell(1);
		HSSFCell row57Column2 = row57.createCell(2);
		HSSFCell row57Column3 = row57.createCell(3);
		HSSFCell row57Column4 = row57.createCell(4);
		HSSFCell row57Column5 = row57.createCell(5);
		HSSFCell row57Column6 = row57.createCell(6);
		HSSFCell row57Column7 = row57.createCell(7);
		HSSFCell row57Column8 = row57.createCell(8);
		HSSFCell row57Column9 = row57.createCell(9);
		HSSFCell row57Column10 = row57.createCell(10);
		HSSFCell row57Column11 = row57.createCell(11);
		HSSFCell row57Column12 = row57.createCell(12);
		HSSFCell row57Column13 = row57.createCell(13);
		HSSFCell row57Column14 = row57.createCell(14);
		HSSFCell row57Column15 = row57.createCell(15);
		HSSFCell row57Column16 = row57.createCell(16);
		HSSFCell row57Column17 = row57.createCell(17);
		HSSFCell row57Column18 = row57.createCell(18);
		HSSFCell row57Column19 = row57.createCell(19);
		
		row56Column1.setCellValue("Documentos Cap");
		definicionColumnas.getPalabrasEnNegrita().add(row56Column1);
		
		row57Column1.setCellValue("Sociedad");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column1);
		
		row57Column2.setCellValue("Número de Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column2);
		row57Column3.setCellValue("Número de proveedor");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column3);
		row57Column4.setCellValue("Tipo de documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column4);
		row57Column5.setCellValue("Número de Documento Interno");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column5);
		row57Column6.setCellValue("Numero Legal de Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column6);
		row57Column7.setCellValue("Fecha de Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column7);
		row57Column8.setCellValue("Renglón");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column8);
		row57Column9.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column9);
		row57Column10.setCellValue("Importe Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column10);
		row57Column11.setCellValue("Tipo de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column11);
		row57Column12.setCellValue("Importe Gestionable");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column12);
		row57Column13.setCellValue("Saldo Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column13);
		row57Column14.setCellValue("Saldo Pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column14);
		row57Column15.setCellValue("Importe Pesificado Documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column15);
		row57Column16.setCellValue("Importe Moneda Origen Documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column16);
		row57Column17.setCellValue("Tipo de Cambio documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column17);
		row57Column18.setCellValue("Sin Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column18);
		row57Column19.setCellValue("Importe de la Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row57Column19);
		
		List<CobroMedioDePagoDto> medioDePagosList = new ArrayList<CobroMedioDePagoDto>(cobroDto.getMedios());
		List<? extends DTO> medioDePagosFinal;
		Iterator<CobroMedioDePagoDto> iterator = null;
		try {
			medioDePagosFinal = Utilidad.guionesNull(medioDePagosList);
			iterator = (Iterator<CobroMedioDePagoDto>) medioDePagosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			CobroMedioDePagoDto medioDePago = iterator.next();
			
			List<DocumentoCapDto> documentosCapList = new ArrayList<DocumentoCapDto>(medioDePago.getListaDocumentoCap());
			List<? extends DTO> documentosCapFinal;
			Iterator<DocumentoCapDto> iteratorDocCap = null;
			try {
				documentosCapFinal = Utilidad.guionesNull(documentosCapList);
				iteratorDocCap = (Iterator<DocumentoCapDto>) documentosCapFinal.iterator();
			} catch (ShivaExcepcion e) {
				new NegocioExcepcion(e.getMessage(), e);
			}
			while(iteratorDocCap.hasNext()){
				DocumentoCapDto dto = iteratorDocCap.next();
				HSSFRow row = sheet.createRow(rowCrear);
				
				HSSFCell sociedad = row.createCell(1);
				sociedad.setCellValue(dto.getCodigoSociedad());
				definicionColumnas.getCeldasConBordes().add(sociedad);
				
				
				HSSFCell numeroDeCliente = row.createCell(2);
				numeroDeCliente.setCellValue(dto.getIdClienteLegado());
				definicionColumnas.getCeldasConBordes().add(numeroDeCliente);
				HSSFCell numeroDeProveedor = row.createCell(3);
				numeroDeProveedor.setCellValue(dto.getIdNumeroProveedor());
				definicionColumnas.getCeldasConBordes().add(numeroDeProveedor);
				HSSFCell tipoDocumento = row.createCell(4);
				tipoDocumento.setCellValue(dto.getTipoDocumentoCodigo());
				definicionColumnas.getCeldasConBordes().add(tipoDocumento);
				HSSFCell numeroDocumentoInterno = row.createCell(5);
				numeroDocumentoInterno.setCellValue(dto.getNroDocumentoSap());
				definicionColumnas.getCeldasConBordes().add(numeroDocumentoInterno);
				HSSFCell numeroLegalDocumento = row.createCell(6);
				numeroLegalDocumento.setCellValue(dto.getNroLegalSap());
				definicionColumnas.getCeldasConBordes().add(numeroLegalDocumento);
				HSSFCell fechaEmision = row.createCell(7);
				fechaEmision.setCellValue(dto.getFechaEmision());
				definicionColumnas.getCeldasConBordes().add(fechaEmision);
				HSSFCell renglon = row.createCell(8);
				renglon.setCellValue(dto.getNumeroRenglon() != null ? dto.getNumeroRenglon() + "" : "-");
				definicionColumnas.getCeldasConBordes().add(renglon);
				HSSFCell moneda = row.createCell(9);
				moneda.setCellValue(dto.getMonedaSigno2());
				definicionColumnas.getCeldasConBordes().add(moneda);
				HSSFCell importeMonedaOrigen = row.createCell(10);
				importeMonedaOrigen.setCellValue(dto.getImporteMonedaOrigenDetalle().replace("&euro;", MonedaEnum.EUR.getSigno2()));
				definicionColumnas.getCeldasConBordes().add(importeMonedaOrigen);
				HSSFCell tipoDeCambio = row.createCell(11);
				tipoDeCambio.setCellValue(dto.getTipoCambio());
				definicionColumnas.getCeldasConBordes().add(tipoDeCambio);
				HSSFCell importeGestionable = row.createCell(12);
				importeGestionable.setCellValue(dto.getImporteGestionableDetalle());
				definicionColumnas.getCeldasConBordes().add(importeGestionable);
				HSSFCell saldoMonedaOrigen = row.createCell(13);
				saldoMonedaOrigen.setCellValue(dto.getSaldoMonedaOrigenDetalle().replace("&euro;", MonedaEnum.EUR.getSigno2()));
				definicionColumnas.getCeldasConBordes().add(saldoMonedaOrigen);
				HSSFCell saldoPesificado = row.createCell(14);
				saldoPesificado.setCellValue(dto.getSaldoPesificadoDetalle());
				definicionColumnas.getCeldasConBordes().add(saldoPesificado);
				HSSFCell importePersificadoDocAsociado = row.createCell(15);
				importePersificadoDocAsociado.setCellValue(dto.getImportePesificadoDocAsociadoDetalle());
				definicionColumnas.getCeldasConBordes().add(importePersificadoDocAsociado);
				HSSFCell imoporteMonedaOrigenDocAsociado = row.createCell(16);
				imoporteMonedaOrigenDocAsociado.setCellValue(dto.getImporteMonedaOrigenDocAsociadoDetalle().replace("&euro;", MonedaEnum.EUR.getSigno2()));
				definicionColumnas.getCeldasConBordes().add(imoporteMonedaOrigenDocAsociado);
				HSSFCell tipoCambioDocAsociado = row.createCell(17);
				tipoCambioDocAsociado.setCellValue(dto.getTipoCambioDocAsociado());
				definicionColumnas.getCeldasConBordes().add(tipoCambioDocAsociado);
				HSSFCell sinDiferenciaCambio = row.createCell(18);
				if ((dto.isEsReglonAgrupador() || dto.isEsReglonAsociado())
					 && !dto.isEsNoGestionable() 
					 && !dto.isEsMismaMoneda()) {
					if (dto.getSinDifDeCambio()) {
						sinDiferenciaCambio.setCellValue("SI");
					} else {
						sinDiferenciaCambio.setCellValue("NO");
					}
				} else {
					sinDiferenciaCambio.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(sinDiferenciaCambio);
				HSSFCell importeDiferenciaCambio = row.createCell(19);
				importeDiferenciaCambio.setCellValue(dto.getImporteDiferenciaCambioDetalle());
				definicionColumnas.getCeldasConBordes().add(importeDiferenciaCambio);
				rowCrear++;
			}
		}
		return rowCrear;
	}

	protected int fillObservacionesCap(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, String titulo, int rowActualint, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		HSSFCellStyle style = workbook.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		rowActualint += 3;
		int rowTituloNro = rowActualint++;
		int rowContenidoNro = rowActualint++;
		
		HSSFRow row58 = sheet.createRow(rowTituloNro);
		HSSFRow row59 = sheet.createRow(rowContenidoNro);
		row58.setHeight((short)400);
		row59.setHeight((short)2400);
		Region regionTituloObservacion = new Region(rowTituloNro,(short)1, rowTituloNro,(short)7);
		Region regionContenidoObservacion = new Region(rowContenidoNro,(short)1, rowContenidoNro,(short)7);
		sheet.addMergedRegion(regionTituloObservacion);
		sheet.addMergedRegion(regionContenidoObservacion);
		
		bordeObservacion(workbook, sheet, regionContenidoObservacion);
		
		HSSFCell row58Column1 = row58.createCell(1);
		HSSFCell row59Column1 = row59.createCell(1);
		row59Column1.setCellStyle(style);
		row58Column1.setCellValue(titulo);
		definicionColumnas.getCeldasConBordesYColorDeTablaObservacion().add(row58Column1);
		
		try {
			cobroDto.setObservacionCap(cobroOnlineServicio.buscarTransacciones(cobroDto.getIdCobro(),false).getObservacionesDocCap());
		} catch (PersistenciaExcepcion e) {
			new PersistenciaExcepcion(e.getMessage(),e);
		}

		if (!Validaciones.isNullEmptyOrDash(cobroDto.getObservacionCap())) {
				row59Column1.setCellValue("\r\n"+Utilidad.formateadoVista(cobroDto.getObservacionCap()));
			} else {
				row59Column1.setCellValue("-");
				definicionColumnas.getCeldasConBordesCentrado().add(row59Column1);
			}
		definicionColumnas.getCeldasConBordes().add(row59Column1);
		return rowActualint;
	}

	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTabla(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor((short)8);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor((short)8);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor((short)8);
		HSSFFont font = workbook.createFont();
	    font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
	    font.setColor(IndexedColors.WHITE.getIndex());
	    style.setFont(font);
	    style.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		for (HSSFCell hssfCell : definicionColumnas.getCeldasConBordesYColorDeTabla()) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaObservaciones(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor((short)8);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor((short)8);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor((short)8);
		HSSFFont font = workbook.createFont();
		font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		style.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		for (HSSFCell hssfCell : definicionColumnas.getCeldasConBordesYColorDeTablaObservacion()) {
			hssfCell.setCellStyle(style);
		}
	}
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaContenido(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor((short)8);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor((short)8);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor((short)8);
		for (HSSFCell hssfCell : definicionColumnas.getCeldasConBordes()) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasConBordesCentrado(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor((short)8);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor((short)8);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor((short)8);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		for (HSSFCell hssfCell : definicionColumnas.getCeldasConBordesCentrado()) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegrita(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle estiloNegrita = workbook.createCellStyle();
		HSSFFont negrita = workbook.createFont();
		negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		estiloNegrita.setFont(negrita);
		for (HSSFCell hssfCell : definicionColumnas.getPalabrasEnNegrita()) {
			hssfCell.setCellStyle(estiloNegrita);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegritaYsubrayado(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		HSSFCellStyle estiloNegrita = workbook.createCellStyle();
		HSSFFont negrita = workbook.createFont();
		negrita.setUnderline(HSSFFont.U_SINGLE);
		negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		estiloNegrita.setFont(negrita);
		for (HSSFCell hssfCell : definicionColumnas.getPalabrasEnNegritaYsubrayado()) {
			hssfCell.setCellStyle(estiloNegrita);
		}
	}	
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void validarGuinesCentrarYImporteRightStyle(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		List<HSSFCell> listaAux = new ArrayList<HSSFCell>();
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFCellStyle styleImporte = workbook.createCellStyle();
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor((short)8);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor((short)8);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor((short)8);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor((short)8);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styleImporte.setWrapText(true);
		styleImporte.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleImporte.setBottomBorderColor((short)8);
		styleImporte.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleImporte.setLeftBorderColor((short)8);
		styleImporte.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleImporte.setRightBorderColor((short)8);
		styleImporte.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleImporte.setTopBorderColor((short)8);
		styleImporte.setAlignment(CellStyle.ALIGN_RIGHT);
		for (HSSFCell hssfCell : definicionColumnas.getCeldasConBordes()) {
			if(!Validaciones.isNullOrEmpty(getValorDeCelda(hssfCell)) && 
					getValorDeCelda(hssfCell).startsWith("$") || getValorDeCelda(hssfCell).startsWith("U$S") || getValorDeCelda(hssfCell).startsWith("€") || getValorDeCelda(hssfCell).startsWith("?")){
				hssfCell.setCellStyle(styleImporte);
				hssfCell.setCellValue(hssfCell.getStringCellValue().replace("?", " "));
			}else if(!"-".equals(getValorDeCelda(hssfCell))) {
				listaAux.add(hssfCell);
			}else {
				hssfCell.setCellStyle(style);
			}
		}
		definicionColumnas.setCeldasConBordes(listaAux);
	}
	
	protected int fillTablaCodigosOperacionExterna(HSSFWorkbook workbook, HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell codigoCol1 = detalle.createCell(1);
		HSSFCell codigoCol2 = detalle.createCell(2);
		HSSFCell codigoCol3 = detalle.createCell(3);
		HSSFCell codigoCol4 = detalle.createCell(4);
		HSSFCell codigoCol5 = detalle.createCell(5);
		HSSFCell codigoCol6 = detalle.createCell(6);
		HSSFCell codigoCol7 = detalle.createCell(7);
		HSSFCell codigoCol8 = detalle.createCell(8);
		
		cabeceraCol1.setCellValue("Códigos de Operaciones Externas");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		codigoCol1.setCellValue("Grupo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol1);
		
		codigoCol2.setCellValue("Nro. Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol2);
		
		codigoCol3.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol3);
		
		codigoCol4.setCellValue("Código de Operación Externa");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol4);

		codigoCol5.setCellValue("Referencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol5);
		
		codigoCol6.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol6);
		
		codigoCol7.setCellValue("Responsable de la Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol7);
		
		codigoCol8.setCellValue("Fecha de la Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol8);
		
		rowActual++;
		for(CodigoOperacionExternaDto codigo : cobroDto.getListaCodigoOperacionesExternas()){
			HSSFRow row = sheet.createRow(rowActual);

			HSSFCell grupo =row.createCell(1);
			grupo.setCellValue(codigo.getGrupo());
			definicionColumnas.getCeldasConBordes().add(grupo);
			
			//Número de Transacción 1
			HSSFCell numeroTransaccion = row.createCell(2);
			if(!Validaciones.isObjectNull(codigo.getNroTransaccion())) {
				numeroTransaccion.setCellValue(codigo.getNroTransaccion());
			} else {
				numeroTransaccion.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(numeroTransaccion);
			//Sistema 2
			HSSFCell sistema = row.createCell(3);
			if(!Validaciones.isObjectNull(codigo.getSistema())) {
				sistema.setCellValue(codigo.getSistema());
			} else {
				sistema.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(sistema);
			//Codigo de operacion 3
			HSSFCell codigoOperacion = row.createCell(4);
			codigoOperacion.setCellValue(codigo.getCodigoOperacionExterna());
			definicionColumnas.getCeldasConBordes().add(codigoOperacion);
			//Referencia de Imputación 4
			HSSFCell referencia = row.createCell(5);
			if(!Validaciones.isObjectNull(codigo.getReferencia())){
				referencia.setCellValue(codigo.getReferencia());
			} else {
				referencia.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(referencia);
			//Importe 5
			HSSFCell importe = row.createCell(6);
			if(!Validaciones.isObjectNull(codigo.getImporte())) {
				importe.setCellValue(codigo.getImporte());
			} else {
				importe.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(importe);
			
			HSSFCell responsableImputacion = row.createCell(7);
			responsableImputacion.setCellValue(codigo.getResponsableImputacion());
			definicionColumnas.getCeldasConBordes().add(responsableImputacion);
			HSSFCell fechaImputacion = row.createCell(8);
			if(!Validaciones.isObjectNull(codigo.getFechaImputacion())) {
				fechaImputacion.setCellValue(codigo.getFechaImputacion());
			} else {
				importe.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(fechaImputacion);
			
			
			rowActual++;
		}
			
		
		return rowActual;
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param sheet
	 */
	public void pintarFondoBlancoHoja(HSSFSheet sheet){
		sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXls() {
		return xls;
	}

	public void setXls(String xls) {
		this.xls = xls;
	}

	public String getValorDeCelda(HSSFCell celda){
		String salida;
		
			if (celda.getCellType() == Cell.CELL_TYPE_STRING ) {
					salida = celda.getStringCellValue();
			} else {
				salida = String.valueOf(celda.getNumericCellValue());
			}
		return salida;
	}
	
	@SuppressWarnings("unchecked")
	protected int fillTablaOtrosDebitos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		int rowActualFinal = rowActual;
		rowActualFinal = rowActualFinal+2;
		HSSFRow row21 = sheet.createRow(rowActualFinal);
		rowActualFinal = rowActualFinal+1;
		HSSFRow row22 = sheet.createRow(rowActualFinal);
		row22.setHeightInPoints(25);
		HSSFCell row21Column1 = row21.createCell(1);
		HSSFCell row22Column1 = row22.createCell(1);
		HSSFCell row22Column2 = row22.createCell(2);
		HSSFCell row22Column3 = row22.createCell(3);
		HSSFCell row22Column4 = row22.createCell(4);
		HSSFCell row22Column5 = row22.createCell(5);
		HSSFCell row22Column6 = row22.createCell(6);
		HSSFCell row22Column7 = row22.createCell(7);
		HSSFCell row22Column8 = row22.createCell(8);
		HSSFCell row22Column9 = row22.createCell(9);
		HSSFCell row22Column10 = row22.createCell(10);
		HSSFCell row22Column11 = row22.createCell(11);
		HSSFCell row22Column12 = row22.createCell(12);
		HSSFCell row22Column13 = row22.createCell(13);
		
		row21Column1.setCellValue("Otros Débitos");
		definicionColumnas.getPalabrasEnNegrita().add(row21Column1);
		
		row22Column1.setCellValue("Sociedad");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column1);
		row22Column2.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column2);
		row22Column3.setCellValue("Tipo de Débito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column3);
		row22Column4.setCellValue("Referencia de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column4);
		row22Column5.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column5);
		row22Column6.setCellValue("Número Legal");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column6);
		row22Column7.setCellValue("Fecha de Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column7);
		row22Column8.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column8);
		row22Column9.setCellValue("Tipo de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column9);
		row22Column10.setCellValue("Importe a Cobrar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column10);
		row22Column11.setCellValue("Importe a Cobrar en Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column11);
		row22Column12.setCellValue("Sin Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column12);
		row22Column13.setCellValue("Adjunto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column13);
		
		List<CobroOtrosDebitoDto> otrosDebitosList = new ArrayList<CobroOtrosDebitoDto>(cobroDto.getOtrosDebitos());
		List<? extends DTO> otrosDebitosFinal;
		Iterator<CobroOtrosDebitoDto> iterator = null;
		try {
			otrosDebitosFinal = Utilidad.guionesNull(otrosDebitosList);
			iterator = (Iterator<CobroOtrosDebitoDto>) otrosDebitosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			CobroOtrosDebitoDto otroDebito = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell sociedad = row.createCell(1);
			sociedad.setCellValue(otroDebito.getSociedad());
			definicionColumnas.getCeldasConBordes().add(sociedad);
			
			HSSFCell sistema = row.createCell(2);
			sistema.setCellValue(otroDebito.getSistema());
			definicionColumnas.getCeldasConBordes().add(sistema);
			
			HSSFCell tipoDebito = row.createCell(3);
			tipoDebito.setCellValue(otroDebito.getTipoDebito());
			definicionColumnas.getCeldasConBordes().add(tipoDebito);
			
			HSSFCell referenciaPago = row.createCell(4);
			if(!Validaciones.isObjectNull(otroDebito.getReferenciaPago())){
				referenciaPago.setCellValue(otroDebito.getReferenciaPago());
					
			}else{
				referenciaPago.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(referenciaPago);
			
			HSSFCell cliente = row.createCell(5);
			if (Validaciones.isObjectNull(otroDebito.getCliente())) {
				String resp = "-";
				cliente.setCellValue(resp);
			} else {
				cliente.setCellValue(otroDebito.getCliente());
			}
			
			definicionColumnas.getCeldasConBordes().add(cliente);
			
			HSSFCell numeroLegal = row.createCell(6);
			numeroLegal.setCellValue(otroDebito.getNumeroDocumento());
			definicionColumnas.getCeldasConBordes().add(numeroLegal);
			
			HSSFCell fechaVencimiento = row.createCell(7);
			fechaVencimiento.setCellValue(otroDebito.getFechaVencimiento());
			definicionColumnas.getCeldasConBordes().add(fechaVencimiento);
			
			HSSFCell moneda = row.createCell(8);
			moneda.setCellValue(otroDebito.getMoneda());
			definicionColumnas.getCeldasConBordes().add(moneda);
			
			HSSFCell tipoCambio = row.createCell(9);
			tipoCambio.setCellValue(otroDebito.getTipoCambio());
			definicionColumnas.getCeldasConBordes().add(tipoCambio);
			
			HSSFCell importe = row.createCell(10);
			
			importe.setCellValue(!Validaciones.isNullEmptyOrDash(otroDebito.getImporte()) ?  
					Utilidad.formatCurrencySacarPunto(
						Utilidad.formatCurrency(new BigDecimal(otroDebito.getImporte().replaceAll(",", ".")), 2, cobroDto.getMonedaOperacion())) 
											 : "-");
			
			definicionColumnas.getCeldasConBordes().add(importe);
			
			HSSFCell importeMonedaOrigen = row.createCell(11);
			
			if (!Validaciones.isNullEmptyOrDash(otroDebito.getMoneda()) && !otroDebito.getMoneda().equals(cobroDto.getMonedaOperacion())) {
				importeMonedaOrigen.setCellValue(otroDebito.getImporteMonedaOrigen()!="-"?Utilidad.formatCurrency(new BigDecimal(otroDebito.getImporteMonedaOrigen().replaceAll(",", ".")), 2, otroDebito.getMoneda()):"-");
			} else {
				importeMonedaOrigen.setCellValue("-");
			}
			
			definicionColumnas.getCeldasConBordes().add(importeMonedaOrigen);
						
			HSSFCell sinDiferencia = row.createCell(12);
			sinDiferencia.setCellValue(otroDebito.getSinDiferenciaDeCambio());
			definicionColumnas.getCeldasConBordes().add(sinDiferencia);
			
			HSSFCell adjunto = row.createCell(13);
			adjunto.setCellValue(otroDebito.getNombreAdjunto());
			definicionColumnas.getCeldasConBordes().add(adjunto);
			
			rowCrear++;
		}
		return rowCrear;
	}
}