package ar.com.telecom.shiva.negocio.servicios.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenDescobroDocumentosRelacionadosDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenDescobroTransaccionDtoInvertido;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ExportacionExcelDefinicionColumnas;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelDescobroServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

/**
 * Clase de Exportacion de Detalle de un Descobro.
 * 
 * @author u573005, fabio.giaquinta.ruiz, sprint 7
 */
public class ExportacionDetalleDescobroImpl implements IExcelDescobroServicio {
	
	@Autowired
	private IDescobroServicio descobroServicio;
	
	private HSSFCellStyle estiloCurrency;
	
	
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

//	private String name;
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
//	
//	private String xls = ".xls";
	
	public HSSFWorkbook generarExcelDetalleDescobro(DescobroDto descobroDto, CobroDto cobroDto) throws NegocioExcepcion {

		HSSFWorkbook workbook = createBook(descobroDto, cobroDto);

		return workbook;

	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param descobroDto
	 * @param cobroDto
	 * @return HSSFWorkbook
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	protected HSSFWorkbook createBook(DescobroDto descobroDto, CobroDto cobroDto) throws NegocioExcepcion{
		
		if(!Validaciones.isObjectNull(descobroDto.getIdOperacionDescobro())){
			ExportacionExcelDefinicionColumnas definicionColumnas = new ExportacionExcelDefinicionColumnas();
			//Relleno todos los campos String nulos de los dtos con un guion
			List<DTO> dtos = new ArrayList<DTO>();
			dtos.add(descobroDto);
			dtos.add(cobroDto);
			try {
				dtos = (List<DTO>) Utilidad.guionesNullRecursivo(dtos);
			} catch (ShivaExcepcion e) {
				new NegocioExcepcion(e.getMessage(), e);
			}
			DescobroDto descobroDtoConGuion = (DescobroDto) dtos.get(0);
			CobroDto cobroDtoConGuion  = (CobroDto) dtos.get(1);
			
			String tipoMedioPago = null;
			if(cobroDto.getMedios().iterator().hasNext()){
						tipoMedioPago = cobroDto.getMedios().iterator().next().getIdMpTipoCredito();
			}
						
			//celdasConBordesYColorDeTabla.clear();
			//celdasConBordesYColorDeTablaObservacion.clear();
			//getCeldasConBordesCentrado().clear();
			//celdasConBordes.clear();
			//getPalabrasEnNegrita().clear();
			//getPalabrasEnNegritaYsubrayado().clear();
			
			//Creo una planilla nueva
			HSSFWorkbook workbook = new HSSFWorkbook();
			estiloCurrency = workbook.createCellStyle();
			HSSFDataFormat df = workbook.createDataFormat();
			estiloCurrency.setDataFormat(df.getFormat("#,##0.00"));
			HSSFSheet sheet;
			
			sheet = workbook.createSheet(descobroDto.getIdOperacionFormateado());
					
			pintarFondoBlancoHoja(sheet);
			
			int rowActual = 0;
			
			//Cargo los datos de cabecera del descobro
			rowActual = this.fillHeadersRemix(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo las obervaciones del. descobro
			rowActual = this.fillObservaciones(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los datos del cobro que se quiere revertir
			rowActual = this.fillTablaCobroARevertir(workbook, sheet, cobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los clientes del cobro a revertir
			rowActual = this.fillTablaClientes(workbook, sheet, cobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los debitos del cobro a revertir
			rowActual = this.fillTablaDebitos(workbook, sheet, cobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los creditos del cobro a revertir
			rowActual = this.fillTablaCreditos(workbook, sheet, cobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo otros medios de pago del cobro a revertir
			rowActual = this.fillTablaOtrosMediosDePago(workbook, sheet, cobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los Documentos CAP
			if (TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(tipoMedioPago)
					|| TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(tipoMedioPago)) {
				rowActual = this.fillTablaDocumentosCap(workbook, sheet, cobroDto, rowActual, definicionColumnas);
			}
			
			//Cargo las transacciones del descobro
			rowActual = this.fillTablaTransacciones(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo las Observaciones CAP
			if (TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(tipoMedioPago)
					|| TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(tipoMedioPago)) {
				rowActual = this.fillObservacionesCap(workbook, sheet, cobroDto, "Observaciones de Documentos Cap: ", rowActual, definicionColumnas);
			}
			
			//Cargo los documentos relacionados a la reversion
			rowActual = this.fillTablaDocumentosRelacionados(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo las transacciones del descobro
			rowActual = this.fillTablaOperacionRelacionadas(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			
			//Cargo los comprobantes del cobro a revertir
			rowActual = this.fillTablaComprobantes(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);

			//Cargo los codigos de operacion externa
			if (Validaciones.isCollectionNotEmpty(descobroDto.getListaCodigoDeOperacionesExternas())) {
				rowActual = this.fillTablaCodigosOperacionExterna(workbook, sheet, descobroDtoConGuion, rowActual, definicionColumnas);
			}
			
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
//			getCeldasConBordesCentrado().clear();
//			getPalabrasEnNegrita().clear();
//			getPalabrasEnNegritaYsubrayado().clear();
			autoAjusteDeColumna(sheet);
			
			return workbook;
		} else {
			//Aviso que el id operacion descobro llego vacio
			String mensajeError = "El id operacion descobro esta vacio.";	
			Traza.error(getClass(), mensajeError);
			throw new NegocioExcepcion(mensajeError, mensajeError); 
		}
	}

	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param sheet
	 */
	private void autoAjusteDeColumna(HSSFSheet sheet) {
		for(int indiceColumna = 0; indiceColumna < 45; indiceColumna++){
			sheet.autoSizeColumn(indiceColumna, true);
		}
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param descobroDto
	 */
	protected int fillHeadersRemix(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) {
		
		rowActual++;
		HSSFRow row1 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row2 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row3 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row4 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row5 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row6 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row7 = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow row8 = sheet.createRow(rowActual);
		
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
		
		HSSFCell row1Column2 = row1.createCell(2);
		HSSFCell row2Column2 = row2.createCell(2);
		HSSFCell row3Column2 = row3.createCell(2);
		HSSFCell row4Column2 = row4.createCell(2);
		HSSFCell row5Column2 = row5.createCell(2);
		HSSFCell row6Column2 = row6.createCell(2);
		HSSFCell row7Column2 = row7.createCell(2);
		HSSFCell row8Column2 = row8.createCell(2);
		
		HSSFCell row1Column7 = row1.createCell(5);
		HSSFCell row2Column7 = row2.createCell(5);
		HSSFCell row3Column7 = row3.createCell(5);
		HSSFCell row4Column7 = row4.createCell(5);
		HSSFCell row5Column7 = row5.createCell(5);
		HSSFCell row6Column7 = row6.createCell(5);
		HSSFCell row7Column7 = row7.createCell(5);
		
		row1Column1.setCellValue("Id Operación Reversión Cobro: ");
		row2Column1.setCellValue("Motivo: ");
		row3Column1.setCellValue("Id Reversión Padre: ");
		row4Column1.setCellValue("Copropietario: ");
		row5Column1.setCellValue("Fecha Última Modificación: ");
		row6Column1.setCellValue("Fecha Derivación: ");
		row7Column1.setCellValue("Responsable Aplicación Manual en Sistema Externo: ");
		row8Column1.setCellValue("Moneda de la Operacion: ");
		
		row1Column6.setCellValue("Empresa: ");
		row2Column6.setCellValue("Segmento: ");
		row3Column6.setCellValue("Analista: ");
		row4Column6.setCellValue("Estado Operación Cobro: ");
		row5Column6.setCellValue("Fecha Alta: ");
		row6Column6.setCellValue("Fecha Imputación: ");
		row7Column6.setCellValue("Fecha Aplicación Manual en Sistema Externo: ");

		if (!Validaciones.isObjectNull(descobroDto)) {
			
			
			row1Column2.setCellValue(descobroDto.getIdOperacionFormateado());
			row2Column2.setCellValue(descobroDto.getDescripcionMotivoReversion());
			
			if (!Validaciones.isObjectNull(descobroDto.getIdDescobroPadre())) {
				row3Column2.setCellValue(Utilidad.rellenarCerosIzquierda(descobroDto.getIdDescobroPadre().toString(), 7));
			} else {
				row3Column2.setCellValue("-");
			}
			row4Column2.setCellValue(descobroDto.getNombreCopropietario());
			row5Column2.setCellValue(descobroDto.getFechaUltimaModificacion()!=null?Utilidad.formatDateAndTimeFull(descobroDto.getFechaUltimaModificacion()):"-");
			row6Column2.setCellValue(descobroDto.getFechaDerivacion()!=null?Utilidad.formatDateAndTimeFull(descobroDto.getFechaDerivacion()):"-");
			
			if (!Validaciones.isObjectNull(descobroDto.getFechaAprobAplicacionManualRefOperacionesExternas())) {
				row7Column7.setCellValue(Utilidad.formatDateAndTimeFull(descobroDto.getFechaAprobAplicacionManualRefOperacionesExternas()));
				row7Column2.setCellValue(!Validaciones.isObjectNull(descobroDto.getNombreApellidoAprobAplicacionManualRefOperacionesExternas()) ? descobroDto.getNombreApellidoAprobAplicacionManualRefOperacionesExternas():"-");
			} else if (!Validaciones.isObjectNull(descobroDto.getFechaConfirmacionAplicacionManualRefCaja())) {
				row7Column7.setCellValue(Utilidad.formatDateAndTimeFull(descobroDto.getFechaConfirmacionAplicacionManualRefCaja()));
				row7Column2.setCellValue(!Validaciones.isObjectNull(descobroDto.getNombreApellidoAprobAplicacionManualRefCaja()) ? descobroDto.getNombreApellidoAprobAplicacionManualRefCaja() : "-");
			} else {
				row7Column7.setCellValue("-");
				row7Column2.setCellValue("-");
			}
			
			row8Column2.setCellValue(MonedaEnum.getEnumBySigno2(descobroDto.getMonedaOperacion()).getDescripcion());
			
			row1Column7.setCellValue(descobroDto.getEmpresa());
			row2Column7.setCellValue(descobroDto.getSegmento());
			row3Column7.setCellValue(descobroDto.getNombreAnalista());
			String estadoMarca = "-";
			if(!Validaciones.isObjectNull(descobroDto.getEstado())){
				estadoMarca = descobroDto.getEstado().descripcion();
			}
			Collection<MarcaEnum> marcas = descobroDto.getMarcas();
			for (MarcaEnum marcaEnum : marcas) {
				estadoMarca = estadoMarca.concat(" / ").concat(marcaEnum.descripcion());
			}
			row4Column7.setCellValue(estadoMarca);
			row5Column7.setCellValue(descobroDto.getFechaAltaFormateado()!=null?descobroDto.getFechaAltaFormateado():"-");
			row6Column7.setCellValue(descobroDto.getFechaReversion()!=null?Utilidad.formatDateAndTimeFull(descobroDto.getFechaReversion()):"-");
		}
		
		return rowActual;
	}
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param descobroDto
	 * @throws NegocioExcepcion 
	 */
	protected int fillObservaciones(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		
		HSSFCellStyle style = workbook.createCellStyle();
		
		style.setAlignment(CellStyle.ALIGN_LEFT);
		
		rowActual = rowActual + 3;
		HSSFRow cabecera = sheet.createRow(rowActual);
		CellRangeAddress regionTituloObservacion = new CellRangeAddress(rowActual, rowActual, 1, 7);
		rowActual++;
		CellRangeAddress regionContenidoObservacion = new CellRangeAddress(rowActual, rowActual, 1, 7);
		HSSFRow detalle = sheet.createRow(rowActual);
		
		cabecera.setHeight((short)400);
		detalle.setHeight((short)2400);
	    sheet.addMergedRegion(regionTituloObservacion);
	    sheet.addMergedRegion(regionContenidoObservacion);
		
		bordeObservacion(workbook, sheet, regionContenidoObservacion);
		
		HSSFCell cabecerarCol1 = cabecera.createCell(1);
		cabecerarCol1.setCellValue("Observaciones: ");
		definicionColumnas.getCeldasConBordesYColorDeTablaObservacion().add(cabecerarCol1);
		
		HSSFCell detalleCol1 = detalle.createCell(1);
		
		String observaciones = "";
		if(!Validaciones.isObjectNull(descobroDto)){
		
			//busco si hay observaciones tipo chat
			observaciones = Utilidad.formateadoVista(descobroServicio.obtenerObseHistorial(descobroDto, null));
			
			//si no, obtengo las observaciones del campo
			if(Validaciones.isNullEmptyOrDash(observaciones)){
				observaciones = descobroDto.getObservacion();
			}
			
			if(!Validaciones.isNullEmptyOrDash(observaciones)){
				
				detalleCol1.setCellValue(observaciones);
				
				style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
				
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBottomBorderColor((short)8);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setLeftBorderColor((short)8);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setRightBorderColor((short)8);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style.setTopBorderColor((short)8);
				style.setWrapText(true);
				detalleCol1.setCellStyle(style);
				
				String[] observacionesPorLinea = observaciones.split(Constantes.RETORNO_WIN);
				int cantidadLineas = observacionesPorLinea.length;
				
				detalle.setHeight((short)(400 * cantidadLineas));
			}
		}
		
		//si no tiene observaciones queda con un guion
		if(Validaciones.isNullEmptyOrDash(observaciones)){
			style.setVerticalAlignment(CellStyle.VERTICAL_JUSTIFY);
			detalleCol1.setCellValue("-");
			detalleCol1.setCellStyle(style);
			definicionColumnas.getCeldasConBordesCentrado().add(detalleCol1);
			
		}		
		
		return rowActual;
	}

	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param region
	 */
	private void bordeObservacion(HSSFWorkbook workbook, HSSFSheet sheet, CellRangeAddress region) {
		RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		RegionUtil.setBottomBorderColor((short)8, region, sheet, workbook);
		RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		RegionUtil.setLeftBorderColor((short)8, region, sheet, workbook);
		RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		RegionUtil.setRightBorderColor((short)8, region, sheet, workbook);
		RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, workbook);
		RegionUtil.setTopBorderColor((short)8, region, sheet, workbook);
	}
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return int
	 * @throws NegocioExcepcion
	 */
	protected int fillTablaCobroARevertir(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		
		rowActual = rowActual + 3;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		HSSFCell detalleCol14 = detalle.createCell(14);
		HSSFCell detalleCol15 = detalle.createCell(15);
		HSSFCell detalleCol16 = detalle.createCell(16);
		HSSFCell detalleCol17 = detalle.createCell(17);
		
		cabeceraCol1.setCellValue("Cobro a Revertir");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Empresa");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Segmento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Id Operación Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Moneda de la operación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Motivo Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Sub Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Fecha último estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		detalleCol10.setCellValue("Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		detalleCol11.setCellValue("Copropietario");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		detalleCol12.setCellValue("Analista Team Comercial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		detalleCol13.setCellValue("Importe Total");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		detalleCol14.setCellValue("Fecha Alta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol14);
		detalleCol15.setCellValue("Fecha Derivación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol15);
		detalleCol16.setCellValue("Fecha Autorización Referente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol16);
		detalleCol17.setCellValue("Fecha Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol17);
		
		
		rowActual++;
		HSSFRow row = sheet.createRow(rowActual);
		
		//empresa 1
		HSSFCell empresa = row.createCell(1);
		empresa.setCellValue(cobroDto.getEmpresa());
		definicionColumnas.getCeldasConBordes().add(empresa);
		
		//segmento 2
		HSSFCell segmento = row.createCell(2);
		segmento.setCellValue(cobroDto.getSegmento());
		definicionColumnas.getCeldasConBordes().add(segmento);
		
		//id operacion cobro 3
		HSSFCell idOperacionCobro = row.createCell(3);
		idOperacionCobro.setCellValue(cobroDto.getIdOperacionFormateado());
		definicionColumnas.getCeldasConBordes().add(idOperacionCobro);
		
		// motivo del cobro 4 
		HSSFCell monedaOperacion = row.createCell(4);
		monedaOperacion.setCellValue(cobroDto.getMonedaOperacion());
		definicionColumnas.getCeldasConBordes().add(monedaOperacion);
		
		// motivo del cobro 5 
		HSSFCell motivoCobro = row.createCell(5);
		motivoCobro.setCellValue(cobroDto.getDescripcionMotivoCobro());
		definicionColumnas.getCeldasConBordes().add(motivoCobro);
		
		//cliente 6
		HSSFCell cliente = row.createCell(6);		
		cliente.setCellValue(cobroDto.getListaIdClientesLegadoRazonSocialConBarraRBarraN());
		definicionColumnas.getCeldasConBordes().add(cliente);
		
		//estado 7
		HSSFCell estado = row.createCell(7);
		estado.setCellValue(cobroDto.getEstado()!=null?cobroDto.getEstado().descripcion():"-");
		definicionColumnas.getCeldasConBordes().add(estado);
		
		//sub estado 8
		HSSFCell subestado = row.createCell(8);
		MarcaEnum subestadoValor = null;
		
		if(Validaciones.isCollectionNotEmpty(cobroDto.getMarcas())){
			subestadoValor = cobroDto.getMarcas().iterator().next();
		}
		subestado.setCellValue(subestadoValor!=null?subestadoValor.descripcion():"-");
		definicionColumnas.getCeldasConBordes().add(subestado);
		
		//Fecha último estado 9
		HSSFCell fechaUltimoEstado = row.createCell(9);
		fechaUltimoEstado.setCellValue(cobroDto.getFechaUltimaModificacion()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaUltimaModificacion()):"-");
		definicionColumnas.getCeldasConBordes().add(fechaUltimoEstado);
		
		//analista 10
		HSSFCell analista = row.createCell(10);
		analista.setCellValue(cobroDto.getNombreAnalista());
		definicionColumnas.getCeldasConBordes().add(analista);
		
		//copropietario 11
		HSSFCell copropietario = row.createCell(11);
		copropietario.setCellValue(cobroDto.getNombreCopropietario());
		definicionColumnas.getCeldasConBordes().add(copropietario);
		
		//analista team comercial 12
		HSSFCell analistaTeamComercial = row.createCell(12);
		analistaTeamComercial.setCellValue(cobroDto.getNombreAnalistaTeamComercial());
		definicionColumnas.getCeldasConBordes().add(analistaTeamComercial);
		
		//importe total 13
		HSSFCell importeTotal = row.createCell(13);
		importeTotal.setCellValue(cobroDto.getImporteTotalDeudaFormateado());
		definicionColumnas.getCeldasConBordes().add(importeTotal);
		
		//Fecha alta 14
		HSSFCell fechaAlta = row.createCell(14);
		fechaAlta.setCellValue(cobroDto.getFechaAlta()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaAlta()):"-");
		definicionColumnas.getCeldasConBordes().add(fechaAlta);
		
		//Fecha derivacion 15
		HSSFCell fechaDerivacion = row.createCell(15);
		fechaDerivacion.setCellValue(cobroDto.getFechaDerivacion()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaDerivacion()):"-");
		definicionColumnas.getCeldasConBordes().add(fechaDerivacion);
		
		//Fecha autorizacion referente 16
		HSSFCell fechaAutReferente = row.createCell(16);
		fechaAutReferente.setCellValue(cobroDto.getFechaAprobacionReferenteCobranza()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaAprobacionReferenteCobranza()):"-");
		definicionColumnas.getCeldasConBordes().add(fechaAutReferente);
		
		//Fecha imputacion 17
		HSSFCell fechaImputacion = row.createCell(17);
		fechaImputacion.setCellValue(cobroDto.getFechaImputacion()!=null?Utilidad.formatDateAndTimeFull(cobroDto.getFechaImputacion()):"-");
		definicionColumnas.getCeldasConBordes().add(fechaImputacion);
		
		//agrego una fila para separar siempre con dos filas vacias
		rowActual++;
		
		return rowActual;
	}
	
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return int
	 * @throws NegocioExcepcion
	 */
	protected int fillTablaClientes(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		
		cabeceraCol1.setCellValue("Clientes");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("CUIT");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Empresas Asociadas");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Razón Social");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Segmento Agencia de Negocio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Holding");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Agencia de Negocio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		
//		detalleCol1.setCellValue("Cliente");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
//		detalleCol2.setCellValue("Razón Social");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
//		detalleCol3.setCellValue("Holding");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
//		detalleCol4.setCellValue("CUIT");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
//		detalleCol5.setCellValue("Agencia de Negocio");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
//		detalleCol6.setCellValue("Segmento Agencia de Negocio");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
//		
//		
		rowActual++;
		for(ClienteDto cliente : cobroDto.getClientesOrdenadosAsc()){
			
			HSSFRow row = sheet.createRow(rowActual);
			
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
			
			rowActual++;
		}
		
		return rowActual;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return int
	 * @throws NegocioExcepcion
	 */
	protected int fillTablaDebitos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{

		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;		
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		HSSFCell detalleCol14 = detalle.createCell(14);
		HSSFCell detalleCol15 = detalle.createCell(15);
		HSSFCell detalleCol16 = detalle.createCell(16);
		HSSFCell detalleCol17 = detalle.createCell(17);
		HSSFCell detalleCol18 = detalle.createCell(18);
		HSSFCell detalleCol19 = detalle.createCell(19);
		HSSFCell detalleCol20 = detalle.createCell(20);
		HSSFCell detalleCol21 = detalle.createCell(21);
		HSSFCell detalleCol22 = detalle.createCell(22);
		HSSFCell detalleCol23 = detalle.createCell(23);
		HSSFCell detalleCol24 = detalle.createCell(24);
		HSSFCell detalleCol25 = detalle.createCell(25);
		HSSFCell detalleCol26 = detalle.createCell(26);
		HSSFCell detalleCol27 = detalle.createCell(27);
		HSSFCell detalleCol28 = detalle.createCell(28);
		HSSFCell detalleCol29 = detalle.createCell(29);
		HSSFCell detalleCol30 = detalle.createCell(30);
		HSSFCell detalleCol31 = detalle.createCell(31);
		
		cabeceraCol1.setCellValue("Débitos");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Cuenta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Nro. Referencia MIC");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Saldo 1° vto moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		detalleCol10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		detalleCol11.setCellValue("Importe 1er vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		detalleCol12.setCellValue("Importe 2do vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		detalleCol13.setCellValue("Saldo pesificado Fecha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		detalleCol14.setCellValue("Check Cobrar al 2° vto");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol14);
		detalleCol15.setCellValue("Check Destransferir 3eros");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol15);
		detalleCol16.setCellValue("Importe a cobrar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol16);
		detalleCol17.setCellValue("Estado conceptos 3eros");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol17);
		detalleCol18.setCellValue("Importe 3eros transferidos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol18);
		detalleCol19.setCellValue("Estado origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol19);
		detalleCol20.setCellValue("Reclamo en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol20);
		detalleCol21.setCellValue("Migrado en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol21);
		detalleCol22.setCellValue("Estado en Deimos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol22);
		detalleCol23.setCellValue("Pago/Compensación en proceso");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol23);
		detalleCol24.setCellValue("Op. Asociada + Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol24);
		detalleCol25.setCellValue("Tipo de cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol25);
		detalleCol26.setCellValue("Fecha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol26);
		detalleCol27.setCellValue("Check Sin Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol27);
		detalleCol28.setCellValue("Nro. Convenio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol28);
		detalleCol29.setCellValue("Cuota Convenio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol29);
		detalleCol30.setCellValue("Fecha ult. pago parcial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol30);
		detalleCol31.setCellValue("Resultado validación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol31);
		
		rowActual++;
		for(CobroDebitoDto debito : cobroDto.getDebitos()){
			
			HSSFRow row = sheet.createRow(rowActual);
			
			//Cliente 1
			HSSFCell cliente = row.createCell(1);
			cliente.setCellValue(debito.getCliente());
			definicionColumnas.getCeldasConBordes().add(cliente);
			
			//Cuenta 2
			HSSFCell cuenta = row.createCell(2);
			cuenta.setCellValue(debito.getCuenta());
			definicionColumnas.getCeldasConBordes().add(cuenta);
			
			//Sistema 3
			HSSFCell sistema = row.createCell(3);
			sistema.setCellValue(debito.getSistemaDescripcion());
			definicionColumnas.getCeldasConBordes().add(sistema);
			
			//Tipo Documento 4
			HSSFCell tipoDoc = row.createCell(4);
			tipoDoc.setCellValue(debito.getTipoDoc());
			definicionColumnas.getCeldasConBordes().add(tipoDoc);
			
			//Subtipo Documento 5
			HSSFCell subTipoDoc = row.createCell(5);
			subTipoDoc.setCellValue(debito.getSubtipoDocumentoDesc());
			definicionColumnas.getCeldasConBordes().add(subTipoDoc);
			
			//Nro. Documento 6
			HSSFCell nroDoc = row.createCell(6);
			nroDoc.setCellValue(debito.getNroDoc());
			definicionColumnas.getCeldasConBordes().add(nroDoc);
			
			//Nro. Referencia MIC 7
			HSSFCell nroReferenciaMic = row.createCell(7);
			nroReferenciaMic.setCellValue(debito.getNumeroReferenciaMic());
			definicionColumnas.getCeldasConBordes().add(nroReferenciaMic);
			
			//Fecha Vencimiento 8
			HSSFCell fechaVenc = row.createCell(8);
			fechaVenc.setCellValue(debito.getFechaVenc());
			definicionColumnas.getCeldasConBordes().add(fechaVenc);
			
			//Saldo 1° vto moneda origen 9
			HSSFCell saldo1erVencMonOrigen = row.createCell(9);
			saldo1erVencMonOrigen.setCellValue(
				!Validaciones.isNullEmptyOrDash(
					debito.getSaldo1erVencMonOrigen()) ? debito.getMoneda() + Utilidad.formatCurrencySacarPunto(debito.getSaldo1erVencMonOrigen()) : "-"
			);
			definicionColumnas.getCeldasConBordes().add(saldo1erVencMonOrigen);
			
			//Moneda 10
			HSSFCell moneda = row.createCell(10);
			moneda.setCellValue(debito.getMoneda());
			definicionColumnas.getCeldasConBordes().add(moneda);
			
			//Importe 1er vto 11
			HSSFCell imp1erVenc = row.createCell(11);
			imp1erVenc.setCellValue(
				!Validaciones.isNullEmptyOrDash(
					debito.getImp1erVenc()) ? debito.getMoneda() + Utilidad.formatCurrencySacarPunto(debito.getImp1erVenc()) : "-"
			);
			definicionColumnas.getCeldasConBordes().add(imp1erVenc);
			
			//Importe 2do vto 12
			HSSFCell imp2doVenc = row.createCell(12);
			imp2doVenc.setCellValue(
				!Validaciones.isNullEmptyOrDash(
					debito.getImp2doVenc()) ? debito.getMoneda() + Utilidad.formatCurrencySacarPunto(debito.getImp2doVenc()) : "-"
			);
			definicionColumnas.getCeldasConBordes().add(imp2doVenc);
			
			//Saldo pesificado Fecha Emisión 13
			HSSFCell saldoPesificado = row.createCell(13);
			if (MonedaEnum.DOL.getSigno2().equals(debito.getMoneda()) && !debito.getMoneda().equals(cobroDto.getMonedaOperacion())) {
				saldoPesificado.setCellValue(
					!Validaciones.isNullEmptyOrDash(
						debito.getSaldoPesificado()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(debito.getSaldoPesificado()) : "-"
				);
			} else {
				saldoPesificado.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(saldoPesificado);
			
			//Check Cobrar al 2° vto 14
			HSSFCell checkCobrarAl2doVenc = row.createCell(14);
			if(debito.isCobrarAl2doVenc()){
				checkCobrarAl2doVenc.setCellValue("SI");
			}else{
				checkCobrarAl2doVenc.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(checkCobrarAl2doVenc);
			
			//Check Destransferir 3eros 15
			HSSFCell checkDestransferir3ros = row.createCell(15);
			if(debito.isDestransferir3ros()){
				checkDestransferir3ros.setCellValue("SI");
			}else{
				checkDestransferir3ros.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(checkDestransferir3ros);
			
			//Importe a cobrar 16
			HSSFCell impACobrar = row.createCell(16);
			impACobrar.setCellValue(
				!Validaciones.isNullEmptyOrDash(
					debito.getImpACobrar()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(debito.getImpACobrar()) :"-"
			);
			definicionColumnas.getCeldasConBordes().add(impACobrar);
			
			//Estado conceptos 3eros 17
			HSSFCell estadoCptosDe3ros = row.createCell(17);
			estadoCptosDe3ros.setCellValue(debito.getEstadoCptosDe3ros());
			definicionColumnas.getCeldasConBordes().add(estadoCptosDe3ros);
			
			//Importe 3eros transferidos 18
			HSSFCell imp3rosTransf = row.createCell(18);
			imp3rosTransf.setCellValue(
				!Validaciones.isNullEmptyOrDash(
					debito.getImp3rosTransf()) ? debito.getMoneda() + Utilidad.formatCurrencySacarPunto(debito.getImp3rosTransf()) : "-"
			);
			definicionColumnas.getCeldasConBordes().add(imp3rosTransf);
			
			//Estado origen 19
			HSSFCell estadoOrigen = row.createCell(19);
			estadoOrigen.setCellValue(debito.getEstadoOrigen());
			definicionColumnas.getCeldasConBordes().add(estadoOrigen);
			
			//Reclamo en origen 20
			HSSFCell reclamoOrigen = row.createCell(20);
			reclamoOrigen.setCellValue(debito.getMarcaReclamo()!=null?debito.getMarcaReclamo().descripcionAMostrar():"");
			definicionColumnas.getCeldasConBordes().add(reclamoOrigen);
			
			//Migrado en origen 21
			HSSFCell migradoOrigen = row.createCell(21);
			migradoOrigen.setCellValue(debito.getMigradoOrigen());
			definicionColumnas.getCeldasConBordes().add(migradoOrigen);
			
			//Estado en Deimos 22
			HSSFCell estadoDeimos = row.createCell(22);
			estadoDeimos.setCellValue(debito.getEstadoDeimos());
			definicionColumnas.getCeldasConBordes().add(estadoDeimos);
			
			//Pago/Compensación en proceso 23
			HSSFCell pagoCompensacionProceso = row.createCell(23);
			pagoCompensacionProceso.setCellValue(debito.getMarcaPagoCompensacionEnProcesoShiva()!=null?debito.getMarcaPagoCompensacionEnProcesoShiva().getDescripcion():"");
			definicionColumnas.getCeldasConBordes().add(pagoCompensacionProceso);
			
			//Op. Asociada + Analista 24
			HSSFCell opeAsocAnalista = row.createCell(24);
			if (!Validaciones.isNullEmptyOrDash(debito.getOpeAsocAnalista())) {
				String opeAsocAnalistVec[] = debito.getOpeAsocAnalista().split("<br>");
				opeAsocAnalista.setCellValue(StringUtils.join(opeAsocAnalistVec, " "));
			} else {
				opeAsocAnalista.setCellValue(debito.getOpeAsocAnalista());
			}
			definicionColumnas.getCeldasConBordes().add(opeAsocAnalista);
			
			//Tipo de cambio 25
			HSSFCell tipoCambio = row.createCell(25);
			if(!Validaciones.isNullEmptyOrDash(debito.getTipoCambio()) && !Validaciones.isNullEmptyOrDash(debito.getMoneda()) && !debito.getMoneda().equals(cobroDto.getMonedaOperacion())){
				tipoCambio.setCellValue("?"+debito.getTipoCambio());
			} else {
				tipoCambio.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(tipoCambio);
			
			//Fecha Emisión 26
			HSSFCell fechaEmision = row.createCell(26);
			fechaEmision.setCellValue(debito.getFechaEmision());
			definicionColumnas.getCeldasConBordes().add(fechaEmision);
			
			//Check Sin Diferencia de Cambio
			HSSFCell checkSinDifDeCambio = row.createCell(27);
			if(debito.isSinDifDeCambio()){
				checkSinDifDeCambio.setCellValue("SI");
			}else{
				checkSinDifDeCambio.setCellValue("NO");
			}
			definicionColumnas.getCeldasConBordes().add(checkSinDifDeCambio);
			
			//Nro. Convenio 28
			HSSFCell nroConvenio = row.createCell(28);
			if(!Validaciones.isNullEmptyOrDash(debito.getNroConvenio())){
				nroConvenio.setCellValue(debito.getNroConvenio());
			} else {
				nroConvenio.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(nroConvenio);
			
			//Cuota Convenio 29
			HSSFCell cuota = row.createCell(29);
			if(!Validaciones.isNullEmptyOrDash(debito.getCuota())){
				cuota.setCellValue(debito.getCuota());
			} else {
				cuota.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(cuota);
			
			//Fecha ult. pago parcial 30
			HSSFCell fechaUltPagoParcial = row.createCell(30);
			fechaUltPagoParcial.setCellValue(debito.getFechaUltPagoParcial());
			definicionColumnas.getCeldasConBordes().add(fechaUltPagoParcial);
			
			//Resultado validación 31
			HSSFCell resultadoValidacionDescripcionError = row.createCell(31);
			resultadoValidacionDescripcionError.setCellValue(debito.getResultadoValidacionDescripcionError());
			definicionColumnas.getCeldasConBordes().add(resultadoValidacionDescripcionError);
			
			rowActual++;
		}
		
		return rowActual;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return int
	 */
	protected int fillTablaCreditos(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		HSSFCell detalleCol14 = detalle.createCell(14);
		HSSFCell detalleCol15 = detalle.createCell(15);
		HSSFCell detalleCol16 = detalle.createCell(16);
		HSSFCell detalleCol17 = detalle.createCell(17);
		HSSFCell detalleCol18 = detalle.createCell(18);
		HSSFCell detalleCol19 = detalle.createCell(19);
		HSSFCell detalleCol20 = detalle.createCell(20);
		HSSFCell detalleCol21 = detalle.createCell(21);
		HSSFCell detalleCol22 = detalle.createCell(22);
		HSSFCell detalleCol23 = detalle.createCell(23);
		HSSFCell detalleCol24 = detalle.createCell(24);
		HSSFCell detalleCol25 = detalle.createCell(25);
		HSSFCell detalleCol26 = detalle.createCell(26);
		HSSFCell detalleCol27 = detalle.createCell(27);
		HSSFCell detalleCol28 = detalle.createCell(28);
		HSSFCell detalleCol29 = detalle.createCell(29);
		HSSFCell detalleCol30 = detalle.createCell(30);
		HSSFCell detalleCol31 = detalle.createCell(31);
		HSSFCell detalleCol32 = detalle.createCell(32);
		
		cabeceraCol1.setCellValue("Créditos");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Cuenta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Nro. Referencia MIC");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Fecha Valor");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Saldo en moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		detalleCol10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		detalleCol11.setCellValue("Importe moneda origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		detalleCol12.setCellValue("Importe pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		detalleCol13.setCellValue("Saldo pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		detalleCol14.setCellValue("Importe a utilizar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol14);
		detalleCol15.setCellValue("Fecha Alta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol15);
		detalleCol16.setCellValue("Fecha Ingreso Recibo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol16);
		detalleCol17.setCellValue("Fecha Depósito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol17);
		detalleCol18.setCellValue("Fecha Transferencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol18);
		detalleCol19.setCellValue("Feha Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol19);
		detalleCol20.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol20);
		detalleCol21.setCellValue("Fecha últ. Movimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol21);
		detalleCol22.setCellValue("Tipo de cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol22);
		detalleCol23.setCellValue("Provincia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol23);
		detalleCol24.setCellValue("CUIT");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol24);
		detalleCol25.setCellValue("Estado origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol25);
		detalleCol26.setCellValue("Motivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol26);
		detalleCol27.setCellValue("Reclamo en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol27);
		detalleCol28.setCellValue("Migrado en origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol28);
		detalleCol29.setCellValue("Estado en Deimos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol29);
		detalleCol30.setCellValue("Pago/compensación en proceso");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol30);
		detalleCol31.setCellValue("Op. Asociada + Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol31);
		detalleCol32.setCellValue("Resultado validación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol32);
		
		rowActual++;
		
		if(Validaciones.isCollectionNotEmpty(cobroDto.getCreditos())){
			
			for(CobroCreditoDto credito : cobroDto.getCreditos()){
				
				HSSFRow row = sheet.createRow(rowActual);
				
				//Cliente 1
				HSSFCell idClienteLegado = row.createCell(1);
				idClienteLegado.setCellValue(credito.getIdClienteLegado());
				definicionColumnas.getCeldasConBordes().add(idClienteLegado);
				
				//Cuenta 2
				HSSFCell cuenta = row.createCell(2);
				cuenta.setCellValue(credito.getCuenta());
				definicionColumnas.getCeldasConBordes().add(cuenta);
				
				//Sistema 3
				HSSFCell sistemaDescripcion = row.createCell(3);
				sistemaDescripcion.setCellValue(credito.getSistemaDescripcion());
				definicionColumnas.getCeldasConBordes().add(sistemaDescripcion);
				
				//Tipo Documento 4
				HSSFCell tipoComprobante = row.createCell(4);
				tipoComprobante.setCellValue(credito.getTipoCredito());
				definicionColumnas.getCeldasConBordes().add(tipoComprobante);
				
				//Subtipo Documento 5
				HSSFCell subtipoDocumentoDesc = row.createCell(5);
				subtipoDocumentoDesc.setCellValue(credito.getSubtipoDocumentoDesc());
				definicionColumnas.getCeldasConBordes().add(subtipoDocumentoDesc);
				
				//Nro. Documento 6
				HSSFCell nroDoc = row.createCell(6);
				nroDoc.setCellValue(credito.getNroDoc());
				definicionColumnas.getCeldasConBordes().add(nroDoc);
				
				//Nro. Referencia MIC 7
				HSSFCell numeroReferenciaMic = row.createCell(7);
				numeroReferenciaMic.setCellValue(credito.getNumeroReferenciaMic());
				definicionColumnas.getCeldasConBordes().add(numeroReferenciaMic);
				
				//Fecha Valor 8
				HSSFCell fechaValor = row.createCell(8);
				fechaValor.setCellValue(credito.getFechaValor());
				definicionColumnas.getCeldasConBordes().add(fechaValor);
				
				//Saldo en moneda origen 9
				HSSFCell saldoMonOrigen = row.createCell(9);
				saldoMonOrigen.setCellValue(
					credito.getSaldoMonOrigen() != "-" ?
							credito.getMoneda() + Utilidad.formatCurrencySacarPunto(credito.getSaldoMonOrigen()):"-");
				
				definicionColumnas.getCeldasConBordes().add(saldoMonOrigen);
				
				//Moneda 10
				HSSFCell moneda = row.createCell(10);
				moneda.setCellValue(credito.getMoneda());
				definicionColumnas.getCeldasConBordes().add(moneda);
				
				//Importe moneda origen 11
				HSSFCell impMonOrigen = row.createCell(11);
				impMonOrigen.setCellValue(credito.getImpMonOrigen()!="-"? credito.getMoneda() + Utilidad.formatCurrencySacarPunto(credito.getImpMonOrigen()):"-");

				definicionColumnas.getCeldasConBordes().add(impMonOrigen);
				
				//Importe pesificado 12
				HSSFCell impPesificado = row.createCell(12);
				String impPesificadoStr = "-";
				String saldoPesificadoStr = "-";
				if (MonedaEnum.DOL.equals(credito.getMonedaEnum()) && !credito.getMoneda().equals(cobroDto.getMonedaOperacion())) {
					impPesificadoStr = !Validaciones.isNullEmptyOrDash(credito.getImpPesificado()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(credito.getImpPesificado()):"-";
					saldoPesificadoStr = !Validaciones.isNullEmptyOrDash(credito.getSaldoPesificado()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(credito.getSaldoPesificado()):"-";
				}
				impPesificado.setCellValue(impPesificadoStr);
				definicionColumnas.getCeldasConBordes().add(impPesificado);
				
				//Saldo pesificado 13
				HSSFCell saldoPesificado = row.createCell(13);
				saldoPesificado.setCellValue(saldoPesificadoStr);
				definicionColumnas.getCeldasConBordes().add(saldoPesificado);
				
				//Importe a utilizar 14
				HSSFCell importeAUtilizar = row.createCell(14);
				importeAUtilizar.setCellValue(!Validaciones.isNullEmptyOrDash(credito.getImporteAUtilizar()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(credito.getImporteAUtilizar()):"-");
				definicionColumnas.getCeldasConBordes().add(importeAUtilizar);
				
				//Fecha Alta 15
				HSSFCell fechaAltaFormateado = row.createCell(15);
				fechaAltaFormateado.setCellValue(credito.getFechaAltaFormateado());
				definicionColumnas.getCeldasConBordes().add(fechaAltaFormateado);
				
				//Fecha Ingreso Recibo 16
				HSSFCell fechaIngresoRecibo = row.createCell(16);
				fechaIngresoRecibo.setCellValue(credito.getFechaIngresoRecibo());
				definicionColumnas.getCeldasConBordes().add(fechaIngresoRecibo);
				
				//Fecha Depósito 17
				HSSFCell fechaDeposito = row.createCell(17);
				fechaDeposito.setCellValue(credito.getFechaDeposito());
				definicionColumnas.getCeldasConBordes().add(fechaDeposito);
				
				//Fecha Transferencia 18
				HSSFCell fechaTrans = row.createCell(18);
				fechaTrans.setCellValue(credito.getFechaTrans());
				definicionColumnas.getCeldasConBordes().add(fechaTrans);
				
				//Feha Emisión 19
				HSSFCell fechaEmision = row.createCell(19);
				fechaEmision.setCellValue(credito.getFechaEmision());
				definicionColumnas.getCeldasConBordes().add(fechaEmision);
				
				//Fecha Vencimiento 20
				HSSFCell fechaVenc = row.createCell(20);
				fechaVenc.setCellValue(credito.getFechaVenc());
				definicionColumnas.getCeldasConBordes().add(fechaVenc);
				
				//Fecha últ. Movimiento 21
				HSSFCell fechaUltimoMov = row.createCell(21);
				fechaUltimoMov.setCellValue(credito.getFechaUltimoMov());
				definicionColumnas.getCeldasConBordes().add(fechaUltimoMov);
				
				//Tipo de cambio 22
				HSSFCell tipoCambio = row.createCell(22);
				if(!Validaciones.isNullEmptyOrDash(credito.getTipoCambio()) && !Validaciones.isNullEmptyOrDash(credito.getMoneda()) && !credito.getMoneda().equals(cobroDto.getMonedaOperacion())){
					tipoCambio.setCellValue("?"+credito.getTipoCambio());
				} else {
					tipoCambio.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(tipoCambio);
				
				//Provincia 23
				HSSFCell provincia = row.createCell(23);
				provincia.setCellValue(credito.getProvincia());
				definicionColumnas.getCeldasConBordes().add(provincia);
				
				//CUIT 24
				HSSFCell cuit = row.createCell(24);
				cuit.setCellValue(credito.getCuit());
				definicionColumnas.getCeldasConBordes().add(cuit);
				
				//Estado origen 25
				HSSFCell estadoOrigen = row.createCell(25);
				estadoOrigen.setCellValue(credito.getEstadoOrigen());
				definicionColumnas.getCeldasConBordes().add(estadoOrigen);
				
				//Motivo 26
				HSSFCell motivo = row.createCell(26);
				if(!Validaciones.isObjectNull(credito.getMotivo())){
					motivo.setCellValue(credito.getMotivo().descripcion());
				}
				definicionColumnas.getCeldasConBordes().add(motivo);
				
				//Reclamo en origen 27
				HSSFCell reclamoEnOrigen = row.createCell(27);
				if(!Validaciones.isObjectNull(credito.getMarcaReclamo())){
					reclamoEnOrigen.setCellValue(credito.getMarcaReclamo().descripcion());
				}
				definicionColumnas.getCeldasConBordes().add(reclamoEnOrigen);
				
				//Migrado en origen 28
				HSSFCell migradoEnOrigen = row.createCell(28);
				if(!Validaciones.isObjectNull(credito.getMarcaMigradoDeimos())){
					migradoEnOrigen.setCellValue(credito.getMarcaMigradoDeimos().getDescripcion());// ver migradoEnOrigen si es desc corta o no
				}
				definicionColumnas.getCeldasConBordes().add(migradoEnOrigen);
				
				//Estado en Deimos 29
				HSSFCell estadoDeimos = row.createCell(29);
				estadoDeimos.setCellValue(credito.getEstadoDeimos());
				definicionColumnas.getCeldasConBordes().add(estadoDeimos);
				
				//Pago/compensación en proceso 30
				HSSFCell pagoCompEnProceso = row.createCell(30);
				pagoCompEnProceso.setCellValue("NO");//ver pago compensacion en proceso (harcodiado, todabia no existe, corregir en el futuro).
				definicionColumnas.getCeldasConBordes().add(pagoCompEnProceso);
				
				//Op. Asociada + Analista 31
				HSSFCell opeAsocAnalista = row.createCell(31);
				if (!Validaciones.isObjectNull(credito.getOpeAsocAnalista())) {
					String opeAsocAnalistVec[] = credito.getOpeAsocAnalista().split("<br>");
					opeAsocAnalista.setCellValue(StringUtils.join(opeAsocAnalistVec, " "));
				} else {
					opeAsocAnalista.setCellValue(credito.getOpeAsocAnalista());
				}
				definicionColumnas.getCeldasConBordes().add(opeAsocAnalista);
				
				//Resultado validación 33
				HSSFCell resultadoValidacion = row.createCell(32);
				resultadoValidacion.setCellValue(credito.getResultadoValidacionDescripcionError());
				definicionColumnas.getCeldasConBordes().add(resultadoValidacion);
				
				rowActual++;
			}
		}
		
		
		return rowActual;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return
	 */
	protected int fillTablaOtrosMediosDePago(HSSFWorkbook workbook,
			HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
//		HSSFCell detalleCol10 = detalle.createCell(10);

		cabeceraCol1.setCellValue("Otros Medios de Pago");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Tipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Sub Tipo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Nro. de Compensación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Nro. de Acta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Fecha");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
//		detalleCol8.setCellValue("Medio de pago en Proceso");
//		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol8.setCellValue("Provincia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Nro de Documento Interno");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		
		rowActual++;
		
		if(Validaciones.isCollectionNotEmpty(cobroDto.getMedios())){
			
			for(CobroMedioDePagoDto medioDePago : cobroDto.getMedios()){
				
				HSSFRow row = sheet.createRow(rowActual);
				
				//Tipo Medio de Pago 1
				HSSFCell tipoMedioPago = row.createCell(1);
				tipoMedioPago.setCellValue(medioDePago.getMpTipoCredito()!=null?medioDePago.getMpTipoCredito().getDescripcion():"-");
				definicionColumnas.getCeldasConBordes().add(tipoMedioPago);
				
				//Sub Tipo 2
				HSSFCell subTipo = row.createCell(2);
				subTipo.setCellValue(medioDePago.getSubTipoDescripcion());
				definicionColumnas.getCeldasConBordes().add(subTipo);
				
				//Importe 3
				HSSFCell importe = row.createCell(3);
				importe.setCellValue(!Validaciones.isNullEmptyOrDash(medioDePago.getImporte()) ? cobroDto.getMonedaOperacion() + Utilidad.formatCurrencySacarPunto(medioDePago.getImporte()):"-");
				definicionColumnas.getCeldasConBordes().add(importe);
				
				//Nro. de Compensación 4
				HSSFCell nroCompensacion = row.createCell(4);
				nroCompensacion.setCellValue(medioDePago.getNroCompensacion());
				definicionColumnas.getCeldasConBordes().add(nroCompensacion);
				
				//Nro. de Acta 5
				HSSFCell nroActa = row.createCell(5);
				nroActa.setCellValue(medioDePago.getNroActa());
				definicionColumnas.getCeldasConBordes().add(nroActa);
				
				//Fecha 6
				HSSFCell fecha = row.createCell(6);
				fecha.setCellValue(medioDePago.getFecha());
				definicionColumnas.getCeldasConBordes().add(fecha);
				
				//Cliente 7
				HSSFCell clientesLegados = row.createCell(7);
				clientesLegados.setCellValue(medioDePago.getClientesLegados());
				definicionColumnas.getCeldasConBordes().add(clientesLegados);
				
//				//Medio de pago en Proceso 8
//				HSSFCell medioDePagoEnProceso = row.createCell(8);
//				if(!(Validaciones.isObjectNull(medioDePago.getCheckMedioPagoEnProceso())) && ("S".equals(medioDePago.getCheckMedioPagoEnProceso().getDescripcionCorta()))){//ver
//					medioDePagoEnProceso.setCellValue("SI");
//				}else{
//					medioDePagoEnProceso.setCellValue("NO");
//				}
//				definicionColumnas.getCeldasConBordes().add(medioDePagoEnProceso);
				
				//Provincia 8
				HSSFCell provincia = row.createCell(8);
				provincia.setCellValue(medioDePago.getProvincia());
				definicionColumnas.getCeldasConBordes().add(provincia);
				
				//Nro de Documento Interno 9
				HSSFCell nroDocumentoInterno = row.createCell(9);
				nroDocumentoInterno.setCellValue(medioDePago.getNroDeDocumentoInterno());
				definicionColumnas.getCeldasConBordes().add(nroDocumentoInterno);
				
				rowActual++;
			}
		}
			
		return rowActual;
	}
	
	
	
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param descobroDto
	 * @param rowActual
	 * @return
	 */
	protected int fillTablaTransacciones(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		HSSFCell detalleCol14 = detalle.createCell(14);
		HSSFCell detalleCol15 = detalle.createCell(15);
		HSSFCell detalleCol16 = detalle.createCell(16);
		HSSFCell detalleCol17 = detalle.createCell(17);
		HSSFCell detalleCol18 = detalle.createCell(18);
		HSSFCell detalleCol19 = detalle.createCell(19);
		HSSFCell detalleCol20 = detalle.createCell(20);
		HSSFCell detalleCol21 = detalle.createCell(21);
		HSSFCell detalleCol22 = detalle.createCell(22);
		HSSFCell detalleCol23 = detalle.createCell(23);
		HSSFCell detalleCol24 = detalle.createCell(24);
		HSSFCell detalleCol25 = detalle.createCell(25);
		HSSFCell detalleCol26 = detalle.createCell(26);
		HSSFCell detalleCol27 = detalle.createCell(27);
		HSSFCell detalleCol28 = detalle.createCell(28);
		HSSFCell detalleCol29 = detalle.createCell(29);
		HSSFCell detalleCol30 = detalle.createCell(30);
		HSSFCell detalleCol31 = detalle.createCell(31);
		HSSFCell detalleCol32 = detalle.createCell(32);
		HSSFCell detalleCol33 = detalle.createCell(33);
		HSSFCell detalleCol34 = detalle.createCell(34);
		HSSFCell detalleCol35 = detalle.createCell(35);
		HSSFCell detalleCol36 = detalle.createCell(36);
		HSSFCell detalleCol37 = detalle.createCell(37);
		HSSFCell detalleCol38 = detalle.createCell(38);

		cabeceraCol1.setCellValue("Transacciones");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		detalleCol1.setCellValue("Grupo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Nro. Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Estado Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Sistema Doc");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Tipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Subtipo Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Nro. Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Nro. Referencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		detalleCol10.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		detalleCol11.setCellValue("Fecha Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		detalleCol12.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		detalleCol13.setCellValue("Tipo de Cambio al Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		detalleCol14.setCellValue("Tipo de Cambio a Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol14);
		detalleCol15.setCellValue("Importe Cobrado en Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol15);
		detalleCol16.setCellValue("Sistema MP");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol16);
		detalleCol17.setCellValue("Tipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol17);
		detalleCol18.setCellValue("Subtipo Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol18);
		detalleCol19.setCellValue("Referencia Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol19);
		detalleCol20.setCellValue("Fecha Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol20);
		detalleCol21.setCellValue("Moneda Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol21);
		detalleCol22.setCellValue("Importe Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol22);
		detalleCol23.setCellValue("Tipo Cambio al Cobro Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol23);
		detalleCol24.setCellValue("Importe Cobrado en Moneda Origen Medio de Pago");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol24);
		detalleCol25.setCellValue("Intereses");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol25);
		detalleCol26.setCellValue("Trasladar Intereses");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol26);
		detalleCol27.setCellValue("Porcentaje a Bonificar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol27);
		detalleCol28.setCellValue("Importe a Bonificar");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol28);
		detalleCol29.setCellValue("Sistema Acuerdo de Facturación Destino de Cargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol29);
		detalleCol30.setCellValue("Acuerdo Facturación Destino de Cargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol30);
		detalleCol31.setCellValue("Estado Acuerdo Facturación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol31);
	
		detalleCol32.setCellValue("Estado Cargo a Proxima Factura");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol32);
		
		detalleCol33.setCellValue("Sistema Acuerdo de Facturación Destino de Contracargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol33);
		detalleCol34.setCellValue("Acuerdo Facturación Destino de Contracargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol34);
		detalleCol35.setCellValue("Estado Acuerdo Facturación Destino de Contracargos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol35);
		
		detalleCol36.setCellValue("Mensaje Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol36);
		detalleCol37.setCellValue("Mensaje Débito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol37);
		detalleCol38.setCellValue("Mensaje Crédito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol38);
		
		if(Validaciones.isCollectionNotEmpty(descobroDto.getTransacciones())){
			
			Set<DescobroTransaccionDto> transaccionesOrdenInvertido = new TreeSet<DescobroTransaccionDto>(new ComparatorOrdenDescobroTransaccionDtoInvertido());
			
			transaccionesOrdenInvertido.addAll(descobroServicio.formatearImportesTransacciones(descobroDto.getTransacciones(), descobroDto.getMonedaOperacion()));
			
			rowActual++;		
			for(DescobroTransaccionDto transaccion : transaccionesOrdenInvertido){
				
				HSSFRow row = sheet.createRow(rowActual);
				
				HSSFCell grupo = row.createCell(1);
				grupo.setCellValue(transaccion.getApocopeGrupo());
				definicionColumnas.getCeldasConBordes().add(grupo);
				
				//Nro. Transacción 1
				HSSFCell numeroTransaccion = row.createCell(2);
				numeroTransaccion.setCellValue(transaccion.getNumeroTransaccionFicticioFormateado());
				definicionColumnas.getCeldasConBordes().add(numeroTransaccion);
				
				//Estado Transacción 2
				HSSFCell estadoTransaccion = row.createCell(3);
				estadoTransaccion.setCellValue(transaccion.getEstadoTransaccion());
				definicionColumnas.getCeldasConBordes().add(estadoTransaccion);
				
				//Sistema Doc 3
				HSSFCell sistemaDoc = row.createCell(4);
				sistemaDoc.setCellValue(transaccion.getSistemaDoc());
				definicionColumnas.getCeldasConBordes().add(sistemaDoc);
				
				//Tipo Documento 4
				HSSFCell tipoDocumento = row.createCell(5);
				tipoDocumento.setCellValue(transaccion.getTipoDocumento());
				definicionColumnas.getCeldasConBordes().add(tipoDocumento);
				
				//Subtipo Documento 5
				HSSFCell subtipoDocumento = row.createCell(6);
				subtipoDocumento.setCellValue(transaccion.getSubtipoDocumento());
				definicionColumnas.getCeldasConBordes().add(subtipoDocumento);
				
				//Nro. Documento 6
				HSSFCell nroDoc = row.createCell(7);
				nroDoc.setCellValue(transaccion.getNroDoc());
				definicionColumnas.getCeldasConBordes().add(nroDoc);
				
				//Nro. Referencia MIC 7
				HSSFCell numeroReferenciaMic = row.createCell(8);
				numeroReferenciaMic.setCellValue(transaccion.getNumeroReferenciaMic());
				definicionColumnas.getCeldasConBordes().add(numeroReferenciaMic);
				
				//Fecha Vencimiento 8
				HSSFCell fechaVenc = row.createCell(9);
				fechaVenc.setCellValue(transaccion.getFechaVenc());
				definicionColumnas.getCeldasConBordes().add(fechaVenc);
				
				//Moneda 9
				HSSFCell moneda = row.createCell(10);
				moneda.setCellValue(transaccion.getMoneda());
				definicionColumnas.getCeldasConBordes().add(moneda);
				
				//Fecha Cobro 10
				HSSFCell fechaCobro = row.createCell(11);
				fechaCobro.setCellValue(transaccion.getFechaCobro());
				definicionColumnas.getCeldasConBordes().add(fechaCobro);
				
				//Importe 11
				HSSFCell importe = row.createCell(12);
				importe.setCellValue(transaccion.getImporte());
				definicionColumnas.getCeldasConBordes().add(importe);
				
				//Se pone el signo "?" adelante del valor que contiene la celda, para que cuando se le da estilo, se lo alinea a la derecha. En el estilo se le borra el signo
				//Tipo de cambio al cobro 12
				HSSFCell tipoDeCambioFechaCobro = row.createCell(13);
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaCobro()) && !Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(descobroDto.getMonedaOperacion()) && transaccion.getColorLetraRegistro().equals("0")){
					tipoDeCambioFechaCobro.setCellValue("?"+transaccion.getTipoDeCambioFechaCobro());
				} else {
					tipoDeCambioFechaCobro.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(tipoDeCambioFechaCobro);

				
				//Tipo de cambio a emisión 13
				HSSFCell tipoCambioEmision = row.createCell(14);
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaEmision()) && !Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(descobroDto.getMonedaOperacion()) && transaccion.getColorLetraRegistro().equals("1")){
					tipoCambioEmision.setCellValue("?"+transaccion.getTipoDeCambioFechaEmision());
				} else {
					tipoCambioEmision.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(tipoCambioEmision);
				
				//Importe cobrado en moneda origen 14
				HSSFCell importeAplicadoFechaEmisionMonedaOrigen = row.createCell(15);
				
				if(!Validaciones.isNullEmptyOrDash(transaccion.getImporteAplicadoFechaEmisionMonedaOrigen()) && !Validaciones.isNullEmptyOrDash(transaccion.getMoneda()) && !transaccion.getMoneda().equals(descobroDto.getMonedaOperacion())){
					importeAplicadoFechaEmisionMonedaOrigen.setCellValue(transaccion.getImporteAplicadoFechaEmisionMonedaOrigen());
				} else {
					importeAplicadoFechaEmisionMonedaOrigen.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(importeAplicadoFechaEmisionMonedaOrigen);
				
				//Sistema MP 15
				HSSFCell sistemaMedioPago = row.createCell(16);
				sistemaMedioPago.setCellValue(transaccion.getSistemaMedioPago());
				definicionColumnas.getCeldasConBordes().add(sistemaMedioPago);
				
				//Tipo Medio de Pago 16
				HSSFCell tipoMedioPago = row.createCell(17);
				tipoMedioPago.setCellValue(transaccion.getTipoMedioPago());
				definicionColumnas.getCeldasConBordes().add(tipoMedioPago);
				
				//Subtipo Medio de Pago 17
				HSSFCell subtipoMedioPago = row.createCell(18);
				subtipoMedioPago.setCellValue(transaccion.getSubtipoMedioPago());
				definicionColumnas.getCeldasConBordes().add(subtipoMedioPago);
				
				//Referencia Medio de Pago 18
				HSSFCell referenciaMedioPago = row.createCell(19);
				referenciaMedioPago.setCellValue(transaccion.getReferenciaMedioPago());
				definicionColumnas.getCeldasConBordes().add(referenciaMedioPago);
				
				//Fecha Medio de Pago 19
				HSSFCell fechaMedioPago = row.createCell(20);
				fechaMedioPago.setCellValue(transaccion.getFechaMedioPago());
				definicionColumnas.getCeldasConBordes().add(fechaMedioPago);

				//Moneda Medio de Pago 20
				HSSFCell monedaMedioPago = row.createCell(21);
				monedaMedioPago.setCellValue(transaccion.getMonedaMedioPago());
				definicionColumnas.getCeldasConBordes().add(monedaMedioPago);
				
				//Importe Medio de Pago 21
				HSSFCell importeMedioPago = row.createCell(22);
				importeMedioPago.setCellValue(transaccion.getImporteMedioPago());
				definicionColumnas.getCeldasConBordes().add(importeMedioPago);
				
				//Tipo Cambio al Cobro Medio de Pago 22
				HSSFCell tipoDeCambioFechaCobroMedioPago = row.createCell(23);
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDeCambioFechaCobroMedioPago()) && !Validaciones.isNullEmptyOrDash(transaccion.getMonedaMedioPago()) && !transaccion.getMonedaMedioPago().equals(descobroDto.getMonedaOperacion())){
					tipoDeCambioFechaCobroMedioPago.setCellValue("?"+transaccion.getTipoDeCambioFechaCobroMedioPago());
				} else {
					tipoDeCambioFechaCobroMedioPago.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(tipoDeCambioFechaCobroMedioPago);
				
				//Importe Cobrado en Moneda Origen Medio de Pago 23
				HSSFCell importeAplicadoFechaEmisionMonedaOrigenMedioPago = row.createCell(24);
				if(!Validaciones.isNullEmptyOrDash(transaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago()) && !Validaciones.isNullEmptyOrDash(transaccion.getMonedaMedioPago()) && !transaccion.getMonedaMedioPago().equals(descobroDto.getMonedaOperacion())){
					importeAplicadoFechaEmisionMonedaOrigenMedioPago.setCellValue(transaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago());
				} else {
					importeAplicadoFechaEmisionMonedaOrigenMedioPago.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(importeAplicadoFechaEmisionMonedaOrigenMedioPago);
				
				//Intereses 24
				HSSFCell intereses = row.createCell(25);
				intereses.setCellValue(transaccion.getIntereses());
				definicionColumnas.getCeldasConBordes().add(intereses);
				
				//Trasladar intereses 25
				HSSFCell trasladarIntereses = row.createCell(26);
				trasladarIntereses.setCellValue(transaccion.getTrasladarInteresesFormateado());
				definicionColumnas.getCeldasConBordes().add(trasladarIntereses);
				
				//Porcentaje a bonificar 26
				HSSFCell porcABonificar = row.createCell(27);
				porcABonificar.setCellValue(transaccion.getPorcABonificar());
				definicionColumnas.getCeldasConBordes().add(porcABonificar);
				
				//Importe a bonificar 27
				HSSFCell importeABonificar = row.createCell(28);
				importeABonificar.setCellValue(transaccion.getImporteABonificar());
				definicionColumnas.getCeldasConBordes().add(importeABonificar);
				
				//Sistema Acuerdo de Facturación destino de cargos 28
				HSSFCell sistemaAcuerdoDestinoCargos = row.createCell(29);
				sistemaAcuerdoDestinoCargos.setCellValue(transaccion.getSistemaAcuerdo());
				definicionColumnas.getCeldasConBordes().add(sistemaAcuerdoDestinoCargos);
				
				//Acuerdo de Facturación destino de cargos 29
				HSSFCell acuerdoDestinoCargos = row.createCell(30);
				acuerdoDestinoCargos.setCellValue(transaccion.getAcuerdoDestinoCargos());
				definicionColumnas.getCeldasConBordes().add(acuerdoDestinoCargos);
				
				//Estado del acuerdo de facturación destino de cargos 30
				HSSFCell estadoAcuerdoDestinoCargos = row.createCell(31);
				estadoAcuerdoDestinoCargos.setCellValue(transaccion.getEstadoAcuerdoDestinoCargos());
				definicionColumnas.getCeldasConBordes().add(estadoAcuerdoDestinoCargos);
				
				//Estado Cargo a Proxima Factura 31
				HSSFCell estadoCargoProximaFactura = row.createCell(32);
				estadoCargoProximaFactura.setCellValue(transaccion.getEstadoCargoProximaFactura());
				definicionColumnas.getCeldasConBordes().add(estadoCargoProximaFactura);
				
				//Sistema acuerdo de facturación destino de contracargos 32
				HSSFCell sistemaAcuerdoDestinoContracargos = row.createCell(33);
				if(!Validaciones.isNullEmptyOrDash(transaccion.getSistemaAcuerdoFactDestinoContracargos())){
					sistemaAcuerdoDestinoContracargos.setCellValue(SistemaEnum.getEnumByName(transaccion.getSistemaAcuerdoFactDestinoContracargos()).getDescripcion());
				} else {
					sistemaAcuerdoDestinoContracargos.setCellValue("-");
				}
				definicionColumnas.getCeldasConBordes().add(sistemaAcuerdoDestinoContracargos);
				
				//Acuerdo de facturación destino de contracargos 33
				HSSFCell acuerdoDestinoContracargos = row.createCell(34);
				acuerdoDestinoContracargos.setCellValue(transaccion.getAcuerdoFactDestinoContracargos());
				definicionColumnas.getCeldasConBordes().add(acuerdoDestinoContracargos);
				
				//Estado del acuerdo de facturación destino de contracargos 34
				HSSFCell estadoAcuerdoDestinoContracargos = row.createCell(35);
				estadoAcuerdoDestinoContracargos.setCellValue(transaccion.getEstadoAcuerdoDestinoContracargos());
				definicionColumnas.getCeldasConBordes().add(estadoAcuerdoDestinoContracargos);
				
				//Mensaje Transacción 35
				HSSFCell mensajeTransaccion = row.createCell(36);
				mensajeTransaccion.setCellValue(transaccion.getMensajeTransaccion());
				definicionColumnas.getCeldasConBordes().add(mensajeTransaccion);
				
				//Mensaje Débito 36
				HSSFCell mensajeDebito = row.createCell(37);
				mensajeDebito.setCellValue(transaccion.getMensajeDebito());
				definicionColumnas.getCeldasConBordes().add(mensajeDebito);
				
				//Mensaje Crédito 37
				HSSFCell mensajeCredito = row.createCell(38);
				mensajeCredito.setCellValue(transaccion.getMensajeCredito());
				definicionColumnas.getCeldasConBordes().add(mensajeCredito);
				
				rowActual++;
			}
		}
		
		return rowActual;
	}
	
	protected int fillTablaDocumentosRelacionados(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		
		HSSFRow cabecera = sheet.createRow(rowActual);

		rowActual++;
		
		HSSFRow detalle = sheet.createRow(rowActual);
//		detalle.setHeightInPoints(25);
		
		CellRangeAddress regionTituloDocRelac1 = new CellRangeAddress(rowActual, rowActual, 1, 3);
		sheet.addMergedRegion(regionTituloDocRelac1);
		CellRangeAddress regionTituloDocRelac2 = new CellRangeAddress(rowActual, rowActual, 4, 8);
		sheet.addMergedRegion(regionTituloDocRelac2);
		CellRangeAddress regionTituloDocRelac3 = new CellRangeAddress(rowActual, rowActual, 9, 13);
		sheet.addMergedRegion(regionTituloDocRelac3);
		
		rowActual++;
		HSSFRow subDetalle = sheet.createRow(rowActual);
//		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		
		cabeceraCol1.setCellValue("Documentos Relacionados a la Reversión");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		
		detalleCol1.setCellValue("");
		detalleCol4.setCellValue("Documento Original");
		detalleCol9.setCellValue("Documento Cancelatorio");
		
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		
		HSSFCell subDetalleCol1 = subDetalle.createCell(1);
		HSSFCell subDetalleCol2 = subDetalle.createCell(2);
		HSSFCell subDetalleCol3 = subDetalle.createCell(3);
		HSSFCell subDetalleCol4 = subDetalle.createCell(4);
		HSSFCell subDetalleCol5 = subDetalle.createCell(5);
		HSSFCell subDetalleCol6 = subDetalle.createCell(6);
		HSSFCell subDetalleCol7 = subDetalle.createCell(7);
		HSSFCell subDetalleCol8 = subDetalle.createCell(8);
		HSSFCell subDetalleCol9 = subDetalle.createCell(9);
		HSSFCell subDetalleCol10 = subDetalle.createCell(10);
		HSSFCell subDetalleCol11 = subDetalle.createCell(11);
		HSSFCell subDetalleCol12 = subDetalle.createCell(12);
		HSSFCell subDetalleCol13 = subDetalle.createCell(13);

		subDetalleCol1.setCellValue("Nro.Transacción");
		subDetalleCol2.setCellValue("ID Cobranza");
		subDetalleCol3.setCellValue("Fecha Imputación");
		subDetalleCol4.setCellValue("Sistema Documento");
		subDetalleCol5.setCellValue("Tipo Documento");
		subDetalleCol6.setCellValue("Subtipo Documento");
		subDetalleCol7.setCellValue("Nro.Documento");
		subDetalleCol8.setCellValue("Importe");
		subDetalleCol9.setCellValue("Sistema Documento");
		subDetalleCol10.setCellValue("Tipo Documento");
		subDetalleCol11.setCellValue("Subtipo Documento");
		subDetalleCol12.setCellValue("Nro.Documento");
		subDetalleCol13.setCellValue("Importe");
		
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol1);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol2);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol3);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol4);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol5);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol6);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol7);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol8);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol9);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol10);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol11);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol12);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(subDetalleCol13);
		
		rowActual++;
		
		if(Validaciones.isCollectionNotEmpty(descobroDto.getDocumentosRelacionados())){
			
			Set<DescobroDocumentoRelacionadoDto> listaDocRelacionadosOrdenada = new TreeSet<DescobroDocumentoRelacionadoDto>(new ComparatorOrdenDescobroDocumentosRelacionadosDto());
			
			listaDocRelacionadosOrdenada.addAll(descobroDto.getDocumentosRelacionados());
			
			for(DescobroDocumentoRelacionadoDto docRelac : listaDocRelacionadosOrdenada){
				
				HSSFRow row = sheet.createRow(rowActual);
							
				//Nro Transaccion
				HSSFCell nroTransaccion = row.createCell(1);
				nroTransaccion.setCellValue(docRelac.getNroTransaccionFicticioFormateado());
				definicionColumnas.getCeldasConBordes().add(nroTransaccion);
				
				//idCobranza
				HSSFCell idCobranza = row.createCell(2);
				idCobranza.setCellValue(docRelac.getIdCobranzaGenerada());
				definicionColumnas.getCeldasConBordes().add(idCobranza);
				
				//fechaImputacion
				HSSFCell fechaImputacion = row.createCell(3);
				fechaImputacion.setCellValue(docRelac.getFechaImputacion());
				definicionColumnas.getCeldasConBordes().add(fechaImputacion);
				
				//sistemaDoc
				HSSFCell sistemaDocOriginal = row.createCell(4);
				sistemaDocOriginal.setCellValue(docRelac.getSistemaOrigen());
				definicionColumnas.getCeldasConBordes().add(sistemaDocOriginal);
				
				//tipoDocI
				HSSFCell tipoDocOriginal = row.createCell(5);
				tipoDocOriginal.setCellValue(docRelac.getTipoComprobanteOriginal());
				definicionColumnas.getCeldasConBordes().add(tipoDocOriginal);
				
				//subtipoDoc
				HSSFCell subtipoDocOriginal = row.createCell(6);
				subtipoDocOriginal.setCellValue(docRelac.getOrigenDocumentoOriginal());
				definicionColumnas.getCeldasConBordes().add(subtipoDocOriginal);
				
				//nroDoc
				HSSFCell nroDocOriginal = row.createCell(7);
				nroDocOriginal.setCellValue(docRelac.getNroDocumentoOriginalFormateado());
				definicionColumnas.getCeldasConBordes().add(nroDocOriginal);
				
				//importe
				HSSFCell importeOriginal = row.createCell(8);
				importeOriginal.setCellValue(docRelac.getImporteAplicadoOriginalFormateado());
				definicionColumnas.getCeldasConBordes().add(importeOriginal);

				//sistemaDoc
				HSSFCell sistemaDocGenerado = row.createCell(9);
				sistemaDocGenerado.setCellValue(docRelac.getSistemaOrigen());
				definicionColumnas.getCeldasConBordes().add(sistemaDocGenerado);
				
				//tipoDoc
				HSSFCell tipoDocGenerado = row.createCell(10);
				tipoDocGenerado.setCellValue(docRelac.getTipoComprobanteGenerado());
				definicionColumnas.getCeldasConBordes().add(tipoDocGenerado);
				
				//subtipoDoc
				HSSFCell subtipoDocGenerado = row.createCell(11);
				subtipoDocGenerado.setCellValue(docRelac.getOrigenDocumentoGenerado());
				definicionColumnas.getCeldasConBordes().add(subtipoDocGenerado);
				
				//nroDoc
				HSSFCell nroDocGenerado = row.createCell(12);
				nroDocGenerado.setCellValue(docRelac.getNroDocumentoGeneradoFormateado());
				definicionColumnas.getCeldasConBordes().add(nroDocGenerado);
				
				//importe
				HSSFCell importeGenerado = row.createCell(13);
				importeGenerado.setCellValue(docRelac.getImporteAplicadoGeneradoFormateado());
				definicionColumnas.getCeldasConBordes().add(importeGenerado);
				
				rowActual++;
			}
		}
		
		
		return rowActual;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param descobroDto
	 * @param rowActual
	 * @return int
	 */
	protected int fillTablaOperacionRelacionadas(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);
		HSSFCell detalleCol3 = detalle.createCell(3);
		HSSFCell detalleCol4 = detalle.createCell(4);
		HSSFCell detalleCol5 = detalle.createCell(5);
		HSSFCell detalleCol6 = detalle.createCell(6);
		HSSFCell detalleCol7 = detalle.createCell(7);
		HSSFCell detalleCol8 = detalle.createCell(8);
		HSSFCell detalleCol9 = detalle.createCell(9);
		HSSFCell detalleCol10 = detalle.createCell(10);
		HSSFCell detalleCol11 = detalle.createCell(11);
		HSSFCell detalleCol12 = detalle.createCell(12);
		HSSFCell detalleCol13 = detalle.createCell(13);
		HSSFCell detalleCol14 = detalle.createCell(14);
		HSSFCell detalleCol15 = detalle.createCell(15);
		HSSFCell detalleCol16 = detalle.createCell(16);
		HSSFCell detalleCol17 = detalle.createCell(17);
		HSSFCell detalleCol18 = detalle.createCell(18);
		HSSFCell detalleCol19 = detalle.createCell(19);

		cabeceraCol1.setCellValue("Operaciones de Cobro Relacionadas");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Id Operación Relacionada");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		detalleCol3.setCellValue("Id Operación Cobro Padre");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol3);
		detalleCol4.setCellValue("Id Transacción Cobro Padre");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol4);
		detalleCol5.setCellValue("Tipo Documento Relacionado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol5);
		detalleCol6.setCellValue("Nro. Documento Relacionado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol6);
		detalleCol7.setCellValue("Motivo Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol7);
		detalleCol8.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol8);
		detalleCol9.setCellValue("Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol9);
		detalleCol10.setCellValue("Sub Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol10);
		detalleCol11.setCellValue("Fecha Último Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol11);
		detalleCol12.setCellValue("Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol12);
		detalleCol13.setCellValue("Copropietario");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol13);
		detalleCol14.setCellValue("Analista Team Comercial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol14);
		detalleCol15.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol15);
		detalleCol16.setCellValue("Fecha Alta");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol16);
		detalleCol17.setCellValue("Fecha Derivación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol17);
		detalleCol18.setCellValue("Fecha Autorización Referente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol18);
		detalleCol19.setCellValue("Fecha Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol19);
		
		rowActual++;
		
		if(Validaciones.isCollectionNotEmpty(descobroDto.getOperacionesRelacionadas())){
			
			for(DescobroOperacionesRelacionadasDto opRelacionadas : descobroDto.obtenerOperacionesRelacionadasOrdenadasDescendente()){
				HSSFRow row = sheet.createRow(rowActual);
				
				//Sistema 1
				HSSFCell sistema = row.createCell(1);
				sistema.setCellValue(opRelacionadas.getSistema());
				definicionColumnas.getCeldasConBordes().add(sistema);
				
				//Id Operación Relacionada 2
				HSSFCell idOperacionRelacionada = row.createCell(2);
				idOperacionRelacionada.setCellValue(opRelacionadas.getIdOperacionRelacionada());
				definicionColumnas.getCeldasConBordes().add(idOperacionRelacionada);
				
				//Id Operación Cobro Padre 3
				HSSFCell idOperacionCobroPadre = row.createCell(3);
				idOperacionCobroPadre.setCellValue(opRelacionadas.getIdOperacionCobroPadre());
				definicionColumnas.getCeldasConBordes().add(idOperacionCobroPadre);
				
				//Id Transacción Cobro Padre 4
				HSSFCell idTransaccionCobroPadre = row.createCell(4);
				idTransaccionCobroPadre.setCellValue(opRelacionadas.getIdTransaccionCobroPadre());
				definicionColumnas.getCeldasConBordes().add(idTransaccionCobroPadre);
				
				//Tipo Documento Relacionado 5
				HSSFCell tipoDocumentoRelacionado = row.createCell(5);
				tipoDocumentoRelacionado.setCellValue(opRelacionadas.getTipoDocumentoRelacionado());
				definicionColumnas.getCeldasConBordes().add(tipoDocumentoRelacionado);
				
				//Nro Documento Relacionado 6
				HSSFCell nroDocumentoRelacionado = row.createCell(6);
				nroDocumentoRelacionado.setCellValue(opRelacionadas.getNroDocumentoRelacionado());
				definicionColumnas.getCeldasConBordes().add(nroDocumentoRelacionado);
				
				//Motivo Cobro 7
				HSSFCell motivoCobro = row.createCell(7);
				motivoCobro.setCellValue(opRelacionadas.getMotivoCobro());
				definicionColumnas.getCeldasConBordes().add(motivoCobro);
				
				//Cliente 8
				HSSFCell cliente = row.createCell(8);
				cliente.setCellValue(opRelacionadas.getIdCliente());
				definicionColumnas.getCeldasConBordes().add(cliente);
				
				//Estado 9
				HSSFCell estado = row.createCell(9);
				estado.setCellValue(opRelacionadas.getEstadoCobro());
				definicionColumnas.getCeldasConBordes().add(estado);
				
				//Sub Estado 10
				HSSFCell subEstado = row.createCell(10);
				subEstado.setCellValue(opRelacionadas.getSubEstadoCobro());
				definicionColumnas.getCeldasConBordes().add(subEstado);
				
				//Fecha Último Estado 11
				HSSFCell fechaUltimoEstado = row.createCell(11);
				fechaUltimoEstado.setCellValue(opRelacionadas.getFechaUltimoEstado());
				definicionColumnas.getCeldasConBordes().add(fechaUltimoEstado);
				
				//Analista 12
				HSSFCell analista = row.createCell(12);
				analista.setCellValue(opRelacionadas.getAnalista());
				definicionColumnas.getCeldasConBordes().add(analista);
				
				//Copropietario 13
				HSSFCell copropietario = row.createCell(13);
				copropietario.setCellValue(opRelacionadas.getCopropietario());
				definicionColumnas.getCeldasConBordes().add(copropietario);
				
				//Analista Team Comercial 14
				HSSFCell analistaTeamComercial = row.createCell(14);
				analistaTeamComercial.setCellValue(opRelacionadas.getAnalistaTeamComercial());
				definicionColumnas.getCeldasConBordes().add(analistaTeamComercial);
				
				//Importe 15
				HSSFCell importe = row.createCell(15);
				importe.setCellValue(opRelacionadas.getImporteFormateado());
				definicionColumnas.getCeldasConBordes().add(importe);
				
				//Fecha Alta 16
				HSSFCell fechaAlta = row.createCell(16);
				fechaAlta.setCellValue(opRelacionadas.getFechaAltaOp());
				definicionColumnas.getCeldasConBordes().add(fechaAlta);
				
				//Fecha Derivación 17
				HSSFCell fechaDerivacion = row.createCell(17);
				fechaDerivacion.setCellValue(opRelacionadas.getFechaDerivacion());
				definicionColumnas.getCeldasConBordes().add(fechaDerivacion);
				
				//Fecha Autorización Referente 18
				HSSFCell fechaAutorizacionReferente = row.createCell(18);
				fechaAutorizacionReferente.setCellValue(opRelacionadas.getFechaAutorizacionReferente());
				definicionColumnas.getCeldasConBordes().add(fechaAutorizacionReferente);
				
				//Fecha Imputación 19
				HSSFCell fechaImputacion = row.createCell(19);
				fechaImputacion.setCellValue(opRelacionadas.getFechaImputacion());
				definicionColumnas.getCeldasConBordes().add(fechaImputacion);
				
				rowActual++;
			}
		}
		
		return rowActual;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @param rowActual
	 * @return int
	 */
	protected int fillTablaComprobantes(HSSFWorkbook workbook,
			HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
		rowActual = rowActual + 2;
		HSSFRow cabecera = sheet.createRow(rowActual);
		rowActual++;
		HSSFRow detalle = sheet.createRow(rowActual);
		detalle.setHeightInPoints(25);
		
		HSSFCell cabeceraCol1 = cabecera.createCell(1);
		HSSFCell detalleCol1 = detalle.createCell(1);
		HSSFCell detalleCol2 = detalle.createCell(2);

		cabeceraCol1.setCellValue("Comprobantes");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		detalleCol1.setCellValue("Nombre del archivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol1);
		detalleCol2.setCellValue("Descripción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(detalleCol2);
		
		if(Validaciones.isCollectionNotEmpty(descobroDto.getListaComprobantes())){
			
			rowActual++;
			for(ComprobanteDto comprobante : descobroDto.getListaComprobantes()){
				HSSFRow row = sheet.createRow(rowActual);
				
				//Nombre del archivo 1
				HSSFCell nombreArchivo = row.createCell(1);
				nombreArchivo.setCellValue(comprobante.getNombreArchivo());
				definicionColumnas.getCeldasConBordes().add(nombreArchivo);
				
				//Descripción 2
				HSSFCell descripcionArchivo = row.createCell(2);
				descripcionArchivo.setCellValue(comprobante.getDescripcionArchivo());
				definicionColumnas.getCeldasConBordes().add(descripcionArchivo);
				
				rowActual++;
			}
		}
		
		
		return rowActual;
	}
	
	protected int fillTablaCodigosOperacionExterna(HSSFWorkbook workbook, HSSFSheet sheet, DescobroDto descobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas){
		
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
		
		cabeceraCol1.setCellValue("Códigos de Operaciones Externas");
		definicionColumnas.getPalabrasEnNegrita().add(cabeceraCol1);
		
		codigoCol1.setCellValue("Nro. Transacción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol1);
		
		codigoCol2.setCellValue("Sistema");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol2);
		
		codigoCol3.setCellValue("Código de Operación Externa");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol3);

		codigoCol4.setCellValue("Referencia");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol4);
		
		codigoCol5.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(codigoCol5);
		
		rowActual++;
		for(CodigoOperacionExternaDto codigo : descobroDto.getListaCodigoDeOperacionesExternas()){
			HSSFRow row = sheet.createRow(rowActual);
			
			//Número de Transacción 1
			HSSFCell numeroTransaccion = row.createCell(1);
			numeroTransaccion.setCellValue(Validaciones.isObjectNull(codigo.getNroTransaccion()) ? "" : codigo.getNroTransaccion());
			definicionColumnas.getCeldasConBordes().add(numeroTransaccion);
			//Sistema 2
			HSSFCell sistema = row.createCell(2);
			sistema.setCellValue(Validaciones.isObjectNull(codigo.getSistema()) ? "" : codigo.getSistema());
			definicionColumnas.getCeldasConBordes().add(sistema);
			//Codigo de operacion 3
			HSSFCell codigoOperacion = row.createCell(3);
			codigoOperacion.setCellValue(codigo.getCodigoOperacionExterna());
			definicionColumnas.getCeldasConBordes().add(codigoOperacion);
			//Referencia de Imputación 4
			HSSFCell referencia = row.createCell(4);
			referencia.setCellValue(Validaciones.isObjectNull(codigo.getReferencia()) ? "" : codigo.getReferencia());
			definicionColumnas.getCeldasConBordes().add(referencia);
			//Importe 5
			HSSFCell importe = row.createCell(5);
			if(!Validaciones.isObjectNull(codigo.getImporte())) {
				importe.setCellValue(codigo.getImporte());
			} else {
				importe.setCellValue("-");
			}
			definicionColumnas.getCeldasConBordes().add(importe);
			
			rowActual++;
		}
		
		return rowActual;
	}
	
	@SuppressWarnings("unchecked")
	private int fillTablaDocumentosCap (HSSFWorkbook workbook, HSSFSheet sheet, CobroDto cobroDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) {
		int rowActualFinal = rowActual;
		rowActualFinal=rowActualFinal+2;
		HSSFRow row1 = sheet.createRow(rowActualFinal);
		rowActualFinal=rowActualFinal+1;
		HSSFRow row2 = sheet.createRow(rowActualFinal);
		row2.setHeightInPoints(25);
		HSSFCell row1Column1 = row1.createCell(1);
		HSSFCell row2Column1 = row2.createCell(1);
		HSSFCell row2Column2 = row2.createCell(2);
		HSSFCell row2Column3 = row2.createCell(3);
		HSSFCell row2Column4 = row2.createCell(4);
		HSSFCell row2Column5 = row2.createCell(5);
		HSSFCell row2Column6 = row2.createCell(6);
		HSSFCell row2Column7 = row2.createCell(7);
		HSSFCell row2Column8 = row2.createCell(8);
		HSSFCell row2Column9 = row2.createCell(9);
		HSSFCell row2Column10 = row2.createCell(10);
		HSSFCell row2Column11 = row2.createCell(11);
		HSSFCell row2Column12 = row2.createCell(12);
		HSSFCell row2Column13 = row2.createCell(13);
		HSSFCell row2Column14 = row2.createCell(14);
		HSSFCell row2Column15 = row2.createCell(15);
		HSSFCell row2Column16 = row2.createCell(16);
		HSSFCell row2Column17 = row2.createCell(17);
		HSSFCell row2Column18 = row2.createCell(18);
		HSSFCell row2Column19 = row2.createCell(19);
		
		row1Column1.setCellValue("Documentos Cap");
		definicionColumnas.getPalabrasEnNegrita().add(row1Column1);
		
		row2Column1.setCellValue("Sociedad");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column1);
		row2Column2.setCellValue("Número de Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column2);
		row2Column3.setCellValue("Número de proveedor");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column3);
		row2Column4.setCellValue("Tipo de documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column4);
		row2Column5.setCellValue("Número de Documento Interno");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column5);
		row2Column6.setCellValue("Numero Legal de Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column6);
		row2Column7.setCellValue("Fecha de Emisión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column7);
		row2Column8.setCellValue("Renglón");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column8);
		row2Column9.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column9);
		row2Column10.setCellValue("Importe Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column10);
		row2Column11.setCellValue("Tipo de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column11);
		row2Column12.setCellValue("Importe Gestionable");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column12);
		row2Column13.setCellValue("Saldo Moneda Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column13);
		row2Column14.setCellValue("Saldo Pesificado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column14);
		row2Column15.setCellValue("Importe Pesificado Documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column15);
		row2Column16.setCellValue("Importe Moneda Origen Documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column16);
		row2Column17.setCellValue("Tipo de Cambio documento Asociado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column17);
		row2Column18.setCellValue("Sin Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column18);
		row2Column19.setCellValue("Importe de la Diferencia de Cambio");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row2Column19);
		
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
			List<DocumentoCapDto> documentosCapList = new ArrayList<DocumentoCapDto>();
			
			if (Validaciones.isCollectionNotEmpty(medioDePago.getListaDocumentoCap())) {
				documentosCapList = new ArrayList<DocumentoCapDto>(medioDePago.getListaDocumentoCap());
				
				
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
		
		HSSFRow row1 = sheet.createRow(rowTituloNro);
		HSSFRow row2 = sheet.createRow(rowContenidoNro);
		row1.setHeight((short)400);
		row2.setHeight((short)2400);
		CellRangeAddress regionTituloObservacion = new CellRangeAddress(rowTituloNro,rowTituloNro, (short)1,(short)7);
		CellRangeAddress regionContenidoObservacion = new CellRangeAddress(rowContenidoNro,rowContenidoNro,(short)1,(short)7);
		sheet.addMergedRegion(regionTituloObservacion);
		sheet.addMergedRegion(regionContenidoObservacion);
		
		bordeObservacion(workbook, sheet, regionContenidoObservacion);
		
		HSSFCell row1Column1 = row1.createCell(1);
		HSSFCell row2Column1 = row2.createCell(1);
		row2Column1.setCellStyle(style);
		row1Column1.setCellValue(titulo);
		definicionColumnas.getCeldasConBordesYColorDeTablaObservacion().add(row1Column1);
		
//		cobroDto.setObservacionCap(descobroServicio.obbuscarTransacciones(cobroDto.getIdCobro()).getObservacionesDocCap());

		if (!Validaciones.isNullEmptyOrDash(cobroDto.getObservacionCap())) {
				row2Column1.setCellValue("\r\n"+Utilidad.formateadoVista(cobroDto.getObservacionCap()));
			} else {
				row2Column1.setCellValue("-");
				definicionColumnas.getCeldasConBordesCentrado().add(row2Column1);
			}
		definicionColumnas.getCeldasConBordes().add(row2Column1);
		return rowActualint;
	}

	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTabla(HSSFWorkbook workbook, HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		Collection<HSSFCell> celdasConBordesDeTabla2 = definicionColumnas.getCeldasConBordesYColorDeTabla();
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
		for (HSSFCell hssfCell : celdasConBordesDeTabla2) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaObservaciones(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {

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
	public void pintarCeldasConBordesCentrado(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas) {
		
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
		//Collection<HSSFCell> celdasConBordes = getCeldasConBordes();
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
			String valorDeCelda = this.getValorDeCelda(hssfCell);
			if (!Validaciones.isNullOrEmpty(valorDeCelda) && 
					valorDeCelda.startsWith("$") || valorDeCelda.startsWith("U$S") || valorDeCelda.startsWith("?")) {
				hssfCell.setCellStyle(styleImporte);
				hssfCell.setCellValue(valorDeCelda.replace("?", " "));
			} else if(!"-".equals(valorDeCelda)) {
				listaAux.add(hssfCell);
			} else {
				hssfCell.setCellStyle(style);
			}
		}
		definicionColumnas.setCeldasConBordes(listaAux);
	}
	

	public void pintarFondoBlancoHoja(HSSFSheet sheet) {
		sheet.setPrintGridlines(false);
		sheet.setDisplayGridlines(false);
	}	

	public String getValorDeCelda(HSSFCell celda) {
		String salida;
		
			if (celda.getCellType() == Cell.CELL_TYPE_STRING ) {
					salida = celda.getStringCellValue();
			} else {
				salida = String.valueOf(celda.getNumericCellValue());
			}
		return salida;
	}
}
