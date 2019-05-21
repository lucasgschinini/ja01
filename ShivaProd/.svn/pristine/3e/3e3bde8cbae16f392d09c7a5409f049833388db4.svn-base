package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.bean.ExportacionExcelDefinicionColumnas;
import ar.com.telecom.shiva.negocio.servicios.IExcelLegajoServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConsultaSoporteResultadoBusquedaChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoCobroRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;

public class ExportacionDetalleLegajoServImpl implements IExcelLegajoServicio{
	
//	private Resource jrxmlFilePath;
	
	private HSSFCellStyle estiloCurrency;
	
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
	
	
	
	public HSSFWorkbook generarExcelDetalleLegajo(LegajoChequeRechazadoDto legajoChequeRechazadoDto) throws NegocioExcepcion {
		
		HSSFWorkbook workbook = this.createBook(legajoChequeRechazadoDto);
		
		return workbook;
	}
	
	
	protected HSSFWorkbook createBook(LegajoChequeRechazadoDto dto) throws NegocioExcepcion{

		int rowActual = 0;
		ExportacionExcelDefinicionColumnas definicionColumnas = new ExportacionExcelDefinicionColumnas();
	
//		celdasConBordesYColorDeTabla.clear();
//		celdasConBordesYColorDeTablaObservacion.clear();
//		getCeldasConBordesCentrado().clear();
//		celdasConBordes.clear();
//		getPalabrasEnNegrita().clear();
//		getPalabrasEnNegritaYsubrayado().clear();
		HSSFWorkbook workbook = new HSSFWorkbook();//Planilla
		estiloCurrency = workbook.createCellStyle();
		HSSFDataFormat df = workbook.createDataFormat();
		estiloCurrency.setDataFormat(df.getFormat("#,##0.00"));
		HSSFSheet sheet;
		sheet = workbook.createSheet(dto.getIdLegajoChequeRechazado().toString());
//		dto.getIdCobroFormatiadoJSPDetalle();
		sheet.autoSizeColumn(1, true);
	    sheet.autoSizeColumn(1);
		pintarFondoBlancoHoja(sheet);
		
		rowActual = fillHeadersRemix(workbook, sheet, dto, definicionColumnas);
		
		if (!Validaciones.isNullEmptyOrDash(dto.getObservacionesAnteriores())) {
			rowActual = fillObservaciones(workbook, sheet, dto,"Observaciones Anteriores: ", true, rowActual, definicionColumnas);
		}
		rowActual = fillObservaciones(workbook, sheet, dto, "Observación: ", false, rowActual, definicionColumnas);
		
		try {
			rowActual = fillTablaCheque(workbook, sheet, dto, rowActual, definicionColumnas);
		} catch(NegocioExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		rowActual = fillTablaEdicionManual(workbook, sheet, dto, rowActual, definicionColumnas);

		try {
			rowActual = fillTablaCobrosRelacionados(workbook, sheet, dto, rowActual, definicionColumnas);
		} catch(NegocioExcepcion e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		rowActual = fillTablaReembolso(workbook, sheet, dto, rowActual, definicionColumnas);
		
		rowActual = fillTablaNotificaciones(workbook, sheet, dto, rowActual, definicionColumnas);
		
		rowActual = fillTablaComprobantes(workbook, sheet, dto, rowActual, definicionColumnas);
		
		validarGuinesCentrarYImporteRightStyle(workbook, sheet, definicionColumnas);
		pintarCeldasTabla(workbook, sheet, definicionColumnas);
		pintarCeldasTablaObservaciones(workbook, sheet, definicionColumnas);
		pintarCeldasTablaContenido(workbook, sheet, definicionColumnas);
		pintarCeldasConBordesCentrado(workbook, sheet, definicionColumnas);
		palabrasEnNegrita(workbook, sheet, definicionColumnas);
		palabrasEnNegritaYsubrayado(workbook, sheet, definicionColumnas);
//		celdasConBordesYColorDeTabla.clear();
//		celdasConBordesYColorDeTablaObservacion.clear();
//		celdasConBordes.clear();
//		getCeldasConBordesCentrado().clear();
//		getPalabrasEnNegrita().clear();
//		getPalabrasEnNegritaYsubrayado().clear();
		autoAjusteDeColumna(sheet);
		return workbook;
	}

	protected int fillHeadersRemix(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, ExportacionExcelDefinicionColumnas definicionColumnas) {
		
		HSSFRow row1 = sheet.createRow(1);
		HSSFRow row2 = sheet.createRow(2);
		HSSFRow row3 = sheet.createRow(3);
		HSSFRow row4 = sheet.createRow(4);
		HSSFRow row5 = sheet.createRow(5);
		HSSFRow row6 = sheet.createRow(6);
		HSSFRow row7 = sheet.createRow(7);
		HSSFRow row8 = sheet.createRow(8);
		HSSFRow row9 = sheet.createRow(9);
		
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

		HSSFCell row1Column4 = row1.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row1Column4);
		HSSFCell row2Column4 = row2.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row2Column4);
		HSSFCell row3Column4 = row3.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row3Column4);
		HSSFCell row4Column4 = row4.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row4Column4);
		HSSFCell row5Column4 = row5.createCell(4);
		definicionColumnas.getPalabrasEnNegrita().add(row5Column4);
		
		HSSFCell row1Column2 = row1.createCell(2);
		HSSFCell row2Column2 = row2.createCell(2);
		HSSFCell row3Column2 = row3.createCell(2);
		HSSFCell row4Column2 = row4.createCell(2);
		HSSFCell row5Column2 = row5.createCell(2);
		
		HSSFCell row1Column5 = row1.createCell(5);
		HSSFCell row2Column5 = row2.createCell(5);
		HSSFCell row3Column5 = row3.createCell(5);
		HSSFCell row4Column5 = row4.createCell(5);
		HSSFCell row5Column5 = row5.createCell(5);
		
		row1Column1.setCellValue("Id Legajo: ");
		row2Column1.setCellValue("Motivo: ");
		row3Column1.setCellValue("Analista: ");
		row4Column1.setCellValue("Estado Legajo: ");
		row5Column1.setCellValue("Fecha Rechazo: ");

		
		row1Column4.setCellValue("Empresa: ");
		row2Column4.setCellValue("Segmento: ");
		row3Column4.setCellValue("Copropietario: ");
		row4Column4.setCellValue("Ubicación del Cheque: ");
		row5Column4.setCellValue("Fecha Notificación Rechazo: ");


		if(!Validaciones.isObjectNull(legajoDto)){
			row1Column2.setCellValue(legajoDto.getIdLegajoChequeRechazado().toString());
			row2Column2.setCellValue(legajoDto.getMotivoLegajoDescripcion());
			row3Column2.setCellValue(legajoDto.getAnalista());
			row4Column2.setCellValue(legajoDto.getEstadoDescripcion());
			row5Column2.setCellValue(legajoDto.getFechaRechazo());

			row1Column5.setCellValue(legajoDto.getEmpresa());
			row2Column5.setCellValue(legajoDto.getSegmento());
			row3Column5.setCellValue(legajoDto.getCopropietario());
			row4Column5.setCellValue(legajoDto.getUbicacionDesc());
			row5Column5.setCellValue(legajoDto.getFechaNotificacion());
			
		}
		return 5;
	}
	
	protected int fillObservaciones(HSSFWorkbook workbook,
		HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, String titulo, boolean anterior, int rowActualint, ExportacionExcelDefinicionColumnas definicionColumnas) {
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
		
		if (!Validaciones.isObjectNull(legajoDto)) {
			if (!anterior && !Validaciones.isNullEmptyOrDash(legajoDto.getObservaciones())) {
				row12Column1.setCellValue("\r\n"+Utilidad.formateadoVista(legajoDto.getObservaciones()));
			} else if (anterior && !Validaciones.isNullEmptyOrDash(legajoDto.getObservacionesAnteriores())) {
				row12Column1.setCellValue("\r\n"+Utilidad.formateadoVista(legajoDto.getObservacionesAnteriores()));
			} else {
				row12Column1.setCellValue("-");
				definicionColumnas.getCeldasConBordesCentrado().add(row12Column1);
			}
		}	
		definicionColumnas.getCeldasConBordes().add(row12Column1);
		return rowActualint;
	}
	
	@SuppressWarnings("unchecked")
	protected int fillTablaCheque(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
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
		HSSFCell row17Column9 = row17.createCell(9);
		HSSFCell row17Column10 = row17.createCell(10);
		HSSFCell row17Column11 = row17.createCell(11);
		HSSFCell row17Column12 = row17.createCell(12);
		HSSFCell row17Column13 = row17.createCell(13);
		HSSFCell row17Column14 = row17.createCell(14);
		HSSFCell row17Column15 = row17.createCell(15);
		HSSFCell row17Column16 = row17.createCell(16);
		HSSFCell row17Column17 = row17.createCell(17);
		HSSFCell row17Column18 = row17.createCell(18);
		HSSFCell row17Column19 = row17.createCell(19);
		HSSFCell row17Column20 = row17.createCell(20);
		HSSFCell row17Column21 = row17.createCell(21);
		
		row16Column1.setCellValue("Cheques");
		definicionColumnas.getPalabrasEnNegrita().add(row16Column1);
		
		row17Column1.setCellValue("Sistema Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column1);
		row17Column2.setCellValue("Numero de Cheque");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column2);
		row17Column3.setCellValue("Banco Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column3);
		row17Column4.setCellValue("Codigo Banco Origen");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column4);
		row17Column5.setCellValue("Cliente");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column5);
		row17Column6.setCellValue("Tipo de Cheque");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column6);
		row17Column7.setCellValue("Fecha Vencimiento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column7);
		row17Column8.setCellValue("Moneda");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column8);
		row17Column9.setCellValue("Importe");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column9);
		row17Column10.setCellValue("Fecha Depósito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column10);
		row17Column11.setCellValue("Banco Depósito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column11);
		row17Column12.setCellValue("Cuenta Depósito");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column12);
		row17Column13.setCellValue("Acuerdo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column13);
		row17Column14.setCellValue("Fecha Recepción");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column14);
		row17Column15.setCellValue("Estado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column15);
		row17Column16.setCellValue("Empresa");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column16);
		row17Column17.setCellValue("Segmento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column17);
		row17Column18.setCellValue("Analista Dueño");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column18);
		row17Column19.setCellValue("Copropietario");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column19);
		row17Column20.setCellValue("Analista Team Comercial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column20);
		row17Column21.setCellValue("ID Valor");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column21);
		
		
		
		ConsultaSoporteResultadoBusquedaChequeRechazadoDto cheque = legajoDto.getChequeRechazado();
		
		int rowCrear = rowActual;
		
		if (!Validaciones.isObjectNull(legajoDto.getChequeRechazado()) && !cheque.getSistemaOrigen().equalsIgnoreCase("Usuario")) {

			HSSFRow row = sheet.createRow(rowCrear);

			HSSFCell createCellSistemaOrigen = row.createCell(1);
			createCellSistemaOrigen.setCellValue(cheque.getSistemaOrigen());
			definicionColumnas.getCeldasConBordes().add(createCellSistemaOrigen);

			HSSFCell createCellNroCheque = row.createCell(2);
			createCellNroCheque.setCellValue(cheque.getNroCheque());
			definicionColumnas.getCeldasConBordes().add(createCellNroCheque);

			HSSFCell createCellBancoOrigen = row.createCell(3);
			createCellBancoOrigen.setCellValue(cheque.getDescripcionBancoOrigen());
			definicionColumnas.getCeldasConBordes().add(createCellBancoOrigen);

			HSSFCell createCellCodigoBancoOrigen = row.createCell(4);
			createCellCodigoBancoOrigen.setCellValue(cheque.getCodBancoOrigen());
			definicionColumnas.getCeldasConBordes().add(createCellCodigoBancoOrigen);

			StringBuilder clientes = new StringBuilder();
			for (ClienteDto cl : cheque.getClientes()) {
				
				if (Validaciones.isNullEmptyOrDash(cl.getRazonSocial())) {
					clientes.append(cl.getIdClienteLegado());
				} else if(Validaciones.isNullEmptyOrDash(cl.getIdClienteLegado())) {
					clientes.append(cl.getRazonSocial());
				} else {
					clientes.append(cl.getIdClienteLegado()+" "+cl.getRazonSocial());
				
					if (cheque.getClientes().size()>1) {
						clientes.append("\n");
					}
				}
			}
			
			HSSFCell createCellClienteCheque = row.createCell(5);
			createCellClienteCheque.setCellValue(clientes.toString());
			definicionColumnas.getCeldasConBordes().add(createCellClienteCheque);

			HSSFCell createCellTipoCheque = row.createCell(6);
			createCellTipoCheque.setCellValue(cheque.getTipoCheque());
			definicionColumnas.getCeldasConBordes().add(createCellTipoCheque);

			HSSFCell createCellFechaVencimiento = row.createCell(7);
			createCellFechaVencimiento.setCellValue(cheque.getFechaVenc());
			definicionColumnas.getCeldasConBordes().add(createCellFechaVencimiento);

			HSSFCell createCellMoneda = row.createCell(8);
			createCellMoneda.setCellValue(cheque.getMoneda());
			definicionColumnas.getCeldasConBordes().add(createCellMoneda);

			HSSFCell createCellImporte = row.createCell(9);
			createCellImporte.setCellValue(cheque.getImporte());
			definicionColumnas.getCeldasConBordes().add(createCellImporte);

			HSSFCell createCellFechaDeposito = row.createCell(10);
			createCellFechaDeposito.setCellValue(cheque.getFechaDeposito());
			definicionColumnas.getCeldasConBordes().add(createCellFechaDeposito);

			HSSFCell createCellBancoDeposito = row.createCell(11);
			createCellBancoDeposito.setCellValue(cheque.getBancoDeposito());
			definicionColumnas.getCeldasConBordes().add(createCellBancoDeposito);

			HSSFCell createCellCuentaDeposito = row.createCell(12);
			createCellCuentaDeposito.setCellValue(cheque.getCuentaDeposito());
			definicionColumnas.getCeldasConBordes().add(createCellCuentaDeposito);

			HSSFCell createCellAcuerdo = row.createCell(13);
			createCellAcuerdo.setCellValue(cheque.getAcuerdo());
			definicionColumnas.getCeldasConBordes().add(createCellAcuerdo);

			HSSFCell createCellFechaRecepcion = row.createCell(14);
			createCellFechaRecepcion.setCellValue(cheque.getFechaRecepcion());
			definicionColumnas.getCeldasConBordes().add(createCellFechaRecepcion);

			HSSFCell createCellEstado = row.createCell(15);
			createCellEstado.setCellValue(cheque.getEstado());
			definicionColumnas.getCeldasConBordes().add(createCellEstado);

			HSSFCell createCellEmpresa = row.createCell(16);
			createCellEmpresa.setCellValue(cheque.getEmpresa());
			definicionColumnas.getCeldasConBordes().add(createCellEmpresa);

			HSSFCell createCellSegmento = row.createCell(17);
			createCellSegmento.setCellValue(cheque.getSegmento());
			definicionColumnas.getCeldasConBordes().add(createCellSegmento);

			HSSFCell createCellAnalista = row.createCell(18);
			createCellAnalista.setCellValue(cheque.getAnalista());
			definicionColumnas.getCeldasConBordes().add(createCellAnalista);

			HSSFCell createCellCopropietario = row.createCell(19);
			createCellCopropietario.setCellValue(cheque.getCopropietario());
			definicionColumnas.getCeldasConBordes().add(createCellCopropietario);

			HSSFCell createCellAnalistaTeamComercial = row.createCell(20);
			createCellAnalistaTeamComercial.setCellValue(cheque.getAnalistaTeamComercial());
			definicionColumnas.getCeldasConBordes().add(createCellAnalistaTeamComercial);

			HSSFCell createCellIdValor = row.createCell(21);
			createCellIdValor.setCellValue(cheque.getIdInternoValor());
			definicionColumnas.getCeldasConBordes().add(createCellIdValor);

			rowCrear++;

		}
		
		return rowCrear;
	}
	
	protected int fillTablaEdicionManual(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) {
		
		rowActual += 3;
		
		HSSFRow row41 = sheet.createRow(rowActual++);
		HSSFRow row42 = sheet.createRow(rowActual++);
		HSSFRow row43 = sheet.createRow(rowActual++);
		HSSFRow row44 = sheet.createRow(rowActual++);
		HSSFRow row45 = sheet.createRow(rowActual++);
		HSSFRow row46 = sheet.createRow(rowActual++);
		HSSFRow row47 = sheet.createRow(rowActual++);
		
		
		HSSFCell row41Column1 = row41.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row41Column1);
		HSSFCell row42Column1 = row42.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row42Column1);
		HSSFCell row43Column1 = row43.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row43Column1);
		HSSFCell row44Column1 = row44.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row44Column1);
		HSSFCell row45Column1 = row45.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row45Column1);
		HSSFCell row46Column1 = row46.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row46Column1);
		HSSFCell row47Column1 = row47.createCell(1);
		definicionColumnas.getPalabrasEnNegrita().add(row47Column1);
		
		HSSFCell row41Column3 = row41.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row41Column3);
		HSSFCell row42Column3 = row42.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row42Column3);
		HSSFCell row43Column3 = row43.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row43Column3);
		HSSFCell row44Column3 = row44.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row44Column3);
		HSSFCell row45Column3 = row45.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row45Column3);
		HSSFCell row46Column3 = row46.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row46Column3);
		HSSFCell row47Column3 = row47.createCell(3);
		definicionColumnas.getPalabrasEnNegrita().add(row47Column3);
		
		HSSFCell row42Column5 = row42.createCell(5);
		definicionColumnas.getPalabrasEnNegrita().add(row42Column5);
		HSSFCell row43Column5 = row43.createCell(5);
		definicionColumnas.getPalabrasEnNegrita().add(row43Column5);
		HSSFCell row45Column5 = row45.createCell(5);
		definicionColumnas.getPalabrasEnNegrita().add(row45Column5);
	
		HSSFCell row41Column2 = row41.createCell(2);
		HSSFCell row42Column2 = row42.createCell(2);
		HSSFCell row43Column2 = row43.createCell(2);
		HSSFCell row44Column2 = row44.createCell(2);
		HSSFCell row45Column2 = row45.createCell(2);
		HSSFCell row46Column2 = row46.createCell(2);
		HSSFCell row47Column2 = row47.createCell(2);
		
		HSSFCell row41Column4 = row41.createCell(4);
		HSSFCell row42Column4 = row42.createCell(4);
		HSSFCell row43Column4 = row43.createCell(4);
		HSSFCell row44Column4 = row44.createCell(4);
		HSSFCell row45Column4 = row45.createCell(4);
		HSSFCell row46Column4 = row46.createCell(4);
		HSSFCell row47Column4 = row47.createCell(4);
		
		HSSFCell row42Column6 = row42.createCell(6);
		HSSFCell row43Column6 = row43.createCell(6);
		HSSFCell row45Column6 = row45.createCell(6);
		
		row41Column1.setCellValue("Sistema Origen");
		row42Column1.setCellValue("Código Banco Origen");
		row43Column1.setCellValue("Fecha Emisión Cheque");
		row44Column1.setCellValue("Moneda Cheque");
		row45Column1.setCellValue("Acuerdo");
		row46Column1.setCellValue("Cod. Cliente Legado");
		row47Column1.setCellValue("Monto Pend. Reembolsar");
		
		row41Column3.setCellValue("Tipo Cheque");
		row42Column3.setCellValue("Desc. Banco Origen");
		row43Column3.setCellValue("Fecha Depósito Cheque");
		row44Column3.setCellValue("Importe Cheque");
		row45Column3.setCellValue("Banco Depósito");
		row46Column3.setCellValue("Razón Social");
		row47Column3.setCellValue("Monto Aplicado Pend. Revertir");
		
		row42Column5.setCellValue("Nro Cheque");
		row43Column5.setCellValue("Fecha Vencimiento");
		row45Column5.setCellValue("Cuenta Depósito");
		
		
		row41Column2.setCellValue(legajoDto.getSistemaOrigen());
		row42Column2.setCellValue(legajoDto.getIdBancoOrigen()+" - "+legajoDto.getBancoOrigenDescripcion());
		row43Column2.setCellValue(legajoDto.getFechaDeposito());
		row44Column2.setCellValue(legajoDto.getMondeDesc());
		if(!Validaciones.isNullEmptyOrDash(legajoDto.getIdAcuerdo())){
			row45Column2.setCellValue("Acuerdo "+legajoDto.getIdAcuerdo());
		}
		row46Column2.setCellValue(legajoDto.getIdCliente());
		row47Column2.setCellValue("$" + legajoDto.getMontoAReenvolsar()); //monto pendiente reembolsar
		
		row41Column4.setCellValue(legajoDto.getTipoChequeDescripcion());
		row42Column4.setCellValue(legajoDto.getBancoOrigenDescripcion());
		row43Column4.setCellValue(legajoDto.getFechaDeposito());
		row44Column4.setCellValue(legajoDto.getImporte());
		row45Column4.setCellValue(legajoDto.getBancoDepositoDescripcion());
		row46Column4.setCellValue(legajoDto.getDescripcionCliente());
		row47Column4.setCellValue("$" + legajoDto.getMontoARevertir());// monto aplicado pendiente revertir
		
		row42Column6.setCellValue(legajoDto.getNumeroCheque());
		row43Column6.setCellValue(legajoDto.getFechaVencimiento());
		row45Column6.setCellValue(legajoDto.getNumeroCuentaDeposito());
		
		rowActual += 7;
		
		return rowActual;
	}
	@SuppressWarnings("unchecked")
	protected int fillTablaCobrosRelacionados(HSSFWorkbook workbook,
		HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
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
	
		row21Column1.setCellValue("Cobros Relacionados");
		definicionColumnas.getPalabrasEnNegrita().add(row21Column1);
		
		row22Column1.setCellValue("Sistema Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column1);
		row22Column2.setCellValue("ID Operación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column2);
		row22Column3.setCellValue("Tipo Documento Relacionado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column3);
		row22Column4.setCellValue("Nro Legal Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column4);
		row22Column5.setCellValue("Nro Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column5);
		row22Column6.setCellValue("Convenio Financiación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column6);
		row22Column7.setCellValue("Cuota Financiación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column7);
		row22Column8.setCellValue("Cliente Legado");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column8);
		row22Column9.setCellValue("Imp Total Cobrado Documento");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column9);
		row22Column10.setCellValue("Imp Total Cobrado con Cheque");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column10);
		row22Column11.setCellValue("Imp Total Cobrado con Efectivo");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column11);
		row22Column12.setCellValue("Imp Total Cobrado con Retenciones");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column12);
		row22Column13.setCellValue("Imp Total Cobrado con Bonos");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column13);
		row22Column14.setCellValue("Imp Total Cobrado con Otras Monedas");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column14);
		row22Column15.setCellValue("Fecha Imputación");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column15);
		row22Column16.setCellValue("ID Reversión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column16);
		row22Column17.setCellValue("Analista");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column17);
		row22Column18.setCellValue("Copropietario");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column18);
		row22Column19.setCellValue("Analista Team Comercial");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column19);
		row22Column20.setCellValue("Estado Cobro");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column20);
		row22Column21.setCellValue("Fecha Reversión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column21);
		row22Column22.setCellValue("Nombre Archivo con Reversión");
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row22Column22);
		
		
		List<LegajoChequeRechazadoCobroRelacionadoDto> cobrosRelacList = new ArrayList<LegajoChequeRechazadoCobroRelacionadoDto>(legajoDto.getListaCobrosRelacionados());
		List<? extends DTO> cobrosRelacFinal;
		Iterator<LegajoChequeRechazadoCobroRelacionadoDto> iterator = null;
		try {
			cobrosRelacFinal = Utilidad.guionesNull(cobrosRelacList);
			iterator = (Iterator<LegajoChequeRechazadoCobroRelacionadoDto>) cobrosRelacFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		rowActualFinal = rowActualFinal+1;
		int rowCrear = rowActualFinal;
		while(iterator.hasNext()){
			LegajoChequeRechazadoCobroRelacionadoDto cobroRelac = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			
			HSSFCell sistemaImputacion = row.createCell(1);
			sistemaImputacion.setCellValue(cobroRelac.getSistema());
			definicionColumnas.getCeldasConBordes().add(sistemaImputacion);
			HSSFCell idOperacion = row.createCell(2);
			idOperacion.setCellValue(cobroRelac.getIdOperacion());
			definicionColumnas.getCeldasConBordes().add(idOperacion);
			HSSFCell tipoDocRelac = row.createCell(3);
			tipoDocRelac.setCellValue(cobroRelac.getTipoDocumentoDescripcion());
			definicionColumnas.getCeldasConBordes().add(tipoDocRelac);
			HSSFCell nroLegalDoc = row.createCell(4);
			nroLegalDoc.setCellValue(cobroRelac.getNumeroLegal());
			definicionColumnas.getCeldasConBordes().add(nroLegalDoc);
			HSSFCell nroDoc = row.createCell(5);
			nroDoc.setCellValue(cobroRelac.getNumeroReferencia());
			definicionColumnas.getCeldasConBordes().add(nroDoc);
			HSSFCell convecioFinanciacion = row.createCell(6);
			convecioFinanciacion.setCellValue(cobroRelac.getConvenioFinanciacion());
			definicionColumnas.getCeldasConBordes().add(convecioFinanciacion);
			HSSFCell cuotaFinanciacion = row.createCell(7);
			cuotaFinanciacion.setCellValue(cobroRelac.getCuotaFinanciacion());
			definicionColumnas.getCeldasConBordes().add(cuotaFinanciacion);
			HSSFCell clienteLegado = row.createCell(8);
			clienteLegado.setCellValue(cobroRelac.getClienteLegado());
			definicionColumnas.getCeldasConBordes().add(clienteLegado);
			HSSFCell impCobradoDoc = row.createCell(9);
			impCobradoDoc.setCellValue(cobroRelac.getImporteTotalDocumento());
			definicionColumnas.getCeldasConBordes().add(impCobradoDoc);
			HSSFCell impCobradoCheque = row.createCell(10);
			impCobradoCheque.setCellValue(cobroRelac.getImporteTotalCheque());
			definicionColumnas.getCeldasConBordes().add(impCobradoCheque);
			HSSFCell impCobradoEfectivo = row.createCell(11);
			impCobradoEfectivo.setCellValue(cobroRelac.getImporteTotalEfectivo());
			definicionColumnas.getCeldasConBordes().add(impCobradoEfectivo);
			HSSFCell impCobradoRetenciones = row.createCell(12);
			impCobradoRetenciones.setCellValue(cobroRelac.getImporteTotalRetenciones());
			definicionColumnas.getCeldasConBordes().add(impCobradoRetenciones);
			HSSFCell impCobradoBonos = row.createCell(13);
			impCobradoBonos.setCellValue(cobroRelac.getImporteTotalBonos());
			definicionColumnas.getCeldasConBordes().add(impCobradoBonos);
			HSSFCell impCobradoOtraMoneda = row.createCell(14);
			impCobradoOtraMoneda.setCellValue(cobroRelac.getImporteTotalOtrasMonedas());
			definicionColumnas.getCeldasConBordes().add(impCobradoOtraMoneda);
			HSSFCell fechaImputacion = row.createCell(15);
			fechaImputacion.setCellValue(cobroRelac.getFechaImputacion());
			definicionColumnas.getCeldasConBordes().add(fechaImputacion);
			HSSFCell idReversion = row.createCell(16);
			idReversion.setCellValue(cobroRelac.getIdDescobro());
			definicionColumnas.getCeldasConBordes().add(idReversion);
			HSSFCell analista = row.createCell(17);
			analista.setCellValue(cobroRelac.getAnalista());
			definicionColumnas.getCeldasConBordes().add(analista);
			HSSFCell copropietario = row.createCell(18);
			copropietario.setCellValue(cobroRelac.getCopropietario());
			definicionColumnas.getCeldasConBordes().add(copropietario);
			HSSFCell analistaTeamComercial = row.createCell(19);
			analistaTeamComercial.setCellValue(cobroRelac.getAnalistaTeamComercial());
			definicionColumnas.getCeldasConBordes().add(analistaTeamComercial);
			HSSFCell estadoCobro = row.createCell(20);
			estadoCobro.setCellValue(cobroRelac.getEstadoCobroDescripcion());
			definicionColumnas.getCeldasConBordes().add(estadoCobro);
			HSSFCell fechaReversion = row.createCell(21);
			fechaReversion.setCellValue(cobroRelac.getFechaReversion());
			definicionColumnas.getCeldasConBordes().add(fechaReversion);
			HSSFCell nombreArchivoReversion = row.createCell(22);
			nombreArchivoReversion.setCellValue(cobroRelac.getNombreArchivoConReversion());
			definicionColumnas.getCeldasConBordes().add(nombreArchivoReversion);
			
			rowCrear++;
		}
		
		return rowCrear;
		
	}
	
	protected int fillTablaReembolso(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
			int rowActualFinal = rowActual;
			rowActualFinal = rowActualFinal+2;
			HSSFRow row25 = sheet.createRow(rowActualFinal);
			rowActualFinal = rowActualFinal+1;
			HSSFRow row26 = sheet.createRow(rowActualFinal);
			row26.setHeightInPoints(25);
			HSSFCell row25Column1 = row25.createCell(1);
			HSSFCell row26Column1 = row26.createCell(1);
			HSSFCell row26Column2 = row26.createCell(2);
			HSSFCell row26Column3 = row26.createCell(3);
			HSSFCell row26Column4 = row26.createCell(4);
			HSSFCell row26Column5 = row26.createCell(5);
			HSSFCell row26Column6 = row26.createCell(6);
			HSSFCell row26Column7 = row26.createCell(7);
			HSSFCell row26Column8 = row26.createCell(8);
			HSSFCell row26Column9 = row26.createCell(9);
			HSSFCell row26Column10 = row26.createCell(10);
			HSSFCell row26Column11 = row26.createCell(11);
			
			row25Column1.setCellValue("Documentos Relacionados");
			definicionColumnas.getPalabrasEnNegrita().add(row25Column1);
			
			row26Column1.setCellValue("Sistema Origen");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column1);
			row26Column2.setCellValue("Tipo Comprobante");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column2);
			row26Column3.setCellValue("Número Legal");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column3);
			row26Column4.setCellValue("Número Documento");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column4);
			row26Column5.setCellValue("Cliente Legado");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column5);
			row26Column6.setCellValue("Moneda");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column6);
			row26Column7.setCellValue("Importe Total");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column7);
			row26Column8.setCellValue("Saldo");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column8);
			row26Column9.setCellValue("Importe Aplicado");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column9);
			row26Column10.setCellValue("Saldo Actual");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column10);
			row26Column11.setCellValue("Última Fecha Consulta de Saldos");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row26Column11);
			
			
			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> docRelacList = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>(legajoDto.getListaDetalleDocumentos());
			List<? extends DTO> docRelacFinal;
			Iterator<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> iterator = null;
			try {
				docRelacFinal = Utilidad.guionesNull(docRelacList);
				iterator = (Iterator<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>) docRelacFinal.iterator();
			} catch (ShivaExcepcion e) {
				new NegocioExcepcion(e.getMessage(), e);
			}
			rowActualFinal = rowActualFinal+1;
			int rowCrear = rowActualFinal;
			while(iterator.hasNext()){
				VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto docRelac = iterator.next();
				HSSFRow row = sheet.createRow(rowCrear);
				
				HSSFCell sistemaOrigen = row.createCell(1);
				sistemaOrigen.setCellValue(docRelac.getSistemaOrigenDescripcion());
				definicionColumnas.getCeldasConBordes().add(sistemaOrigen);
				HSSFCell tipoDocumento = row.createCell(2);
				tipoDocumento.setCellValue(docRelac.getTipoDocumento());
				definicionColumnas.getCeldasConBordes().add(tipoDocumento);
				HSSFCell nroLegal = row.createCell(3);
				nroLegal.setCellValue(docRelac.getNumeroLegal());
				definicionColumnas.getCeldasConBordes().add(nroLegal);
				HSSFCell nroDoc = row.createCell(4);
				nroDoc.setCellValue(docRelac.getNumeroReferencia());
				definicionColumnas.getCeldasConBordes().add(nroDoc);
				HSSFCell cliente = row.createCell(5);
				cliente.setCellValue(docRelac.getClienteLegado());
				definicionColumnas.getCeldasConBordes().add(cliente);
				HSSFCell moneda = row.createCell(6);
				moneda.setCellValue(docRelac.getMonedaDocumento());
				definicionColumnas.getCeldasConBordes().add(moneda);
				HSSFCell importeTotal = row.createCell(7);
				importeTotal.setCellValue(docRelac.getImporteTotalDocumento());
				definicionColumnas.getCeldasConBordes().add(importeTotal);
				HSSFCell saldoDoc = row.createCell(8);
				saldoDoc.setCellValue(docRelac.getSaldoDocumentoLuegoAplicarCheque());
				definicionColumnas.getCeldasConBordes().add(saldoDoc);
				HSSFCell importeCheque = row.createCell(9);
				importeCheque.setCellValue(docRelac.getImporteAplicadoDelCheque());
				definicionColumnas.getCeldasConBordes().add(importeCheque);
				HSSFCell saldoActual = row.createCell(10);
				saldoActual.setCellValue(docRelac.getSaldoActualDocumento());
				definicionColumnas.getCeldasConBordes().add(saldoActual);
				HSSFCell fechaConsulta = row.createCell(11);
				fechaConsulta.setCellValue(docRelac.getFechaConsultaSaldo());
				definicionColumnas.getCeldasConBordes().add(fechaConsulta);

				
				rowCrear++;
			}
			
			return rowCrear;
	}

	protected int fillTablaNotificaciones(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
			int rowActualFinal = rowActual;
			rowActualFinal = rowActualFinal+2;
			HSSFRow row28 = sheet.createRow(rowActualFinal);
			rowActualFinal = rowActualFinal+1;
			HSSFRow row29 = sheet.createRow(rowActualFinal);
			row28.setHeightInPoints(25);
			HSSFCell row28Column1 = row28.createCell(1);
			HSSFCell row29Column1 = row29.createCell(1);
			HSSFCell row29Column2 = row29.createCell(2);
			HSSFCell row29Column3 = row29.createCell(3);
			HSSFCell row29Column4 = row29.createCell(4);
			HSSFCell row29Column5 = row29.createCell(5);
			HSSFCell row29Column6 = row29.createCell(6);
			HSSFCell row29Column7 = row29.createCell(7);
			HSSFCell row29Column8 = row29.createCell(8);
			HSSFCell row29Column9 = row29.createCell(9);
			
			row28Column1.setCellValue("Notificaciones Historicas");
			definicionColumnas.getPalabrasEnNegrita().add(row28Column1);
			
			row29Column1.setCellValue("Tipo Notificacion");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column1);
			row29Column2.setCellValue("Datos Del Recepctor");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column2);
			row29Column3.setCellValue("Fecha de Contacto");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column3);
			row29Column4.setCellValue("Tipo de Contacto");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column4);
			row29Column5.setCellValue("Datos del Contacto");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column5);
			row29Column6.setCellValue("Acuse de Recibo");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column6);
			row29Column7.setCellValue("Fecha Recepción");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column7);
			row29Column8.setCellValue("Observaciones");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column8);
			row29Column9.setCellValue("Usuario que cargó la notificación");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row29Column9);
			
			List<LegajoChequeRechazadoNotificacionDto> notifList = new ArrayList<LegajoChequeRechazadoNotificacionDto>(legajoDto.getListaNotificaciones());
			List<? extends DTO> notifFinal;
			Iterator<LegajoChequeRechazadoNotificacionDto> iterator = null;
			try {
				notifFinal = Utilidad.guionesNull(notifList);
				iterator = (Iterator<LegajoChequeRechazadoNotificacionDto>) notifFinal.iterator();
			} catch (ShivaExcepcion e) {
				new NegocioExcepcion(e.getMessage(), e);
			}
			rowActualFinal = rowActualFinal+1;
			int rowCrear = rowActualFinal;
			while(iterator.hasNext()){
				LegajoChequeRechazadoNotificacionDto notif = iterator.next();
				HSSFRow row = sheet.createRow(rowCrear);
				
				HSSFCell tipoNotifiacion = row.createCell(1);
				tipoNotifiacion.setCellValue(notif.getTipoNotificacionDescripicion());
				definicionColumnas.getCeldasConBordes().add(tipoNotifiacion);
				HSSFCell datosReceptor = row.createCell(2);
				datosReceptor.setCellValue(notif.getDatosReceptor());
				definicionColumnas.getCeldasConBordes().add(datosReceptor);
				HSSFCell fechaContacto = row.createCell(3);
				fechaContacto.setCellValue(notif.getFechaContacto());
				definicionColumnas.getCeldasConBordes().add(fechaContacto);
				HSSFCell tipoContacto = row.createCell(4);
				tipoContacto.setCellValue(notif.getTipoContactoDescripcion());
				definicionColumnas.getCeldasConBordes().add(tipoContacto);
				HSSFCell datosContacto = row.createCell(5);
				datosContacto.setCellValue(notif.getDatosContacto());
				definicionColumnas.getCeldasConBordes().add(datosContacto);
				HSSFCell acuseRecibo = row.createCell(6);
				acuseRecibo.setCellValue(notif.getAcuseReciboDescripcion());
				definicionColumnas.getCeldasConBordes().add(acuseRecibo);
				HSSFCell fechaRecepcion = row.createCell(7);
				fechaRecepcion.setCellValue(notif.getFechaRecepcion());
				definicionColumnas.getCeldasConBordes().add(fechaRecepcion);
				HSSFCell observaciones = row.createCell(8);
				observaciones.setCellValue(notif.getObservaciones());
				definicionColumnas.getCeldasConBordes().add(observaciones);
				HSSFCell usuarioUltimaNotificacion = row.createCell(9);
				usuarioUltimaNotificacion.setCellValue(notif.getUsuarioDesc());
				definicionColumnas.getCeldasConBordes().add(usuarioUltimaNotificacion);

				rowCrear++;
			}
			return rowCrear;
	}
	
	protected int fillTablaComprobantes(HSSFWorkbook workbook,
			HSSFSheet sheet, LegajoChequeRechazadoDto legajoDto, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
			int rowActualFinal = rowActual;
			rowActualFinal = rowActualFinal+2;
			HSSFRow row31 = sheet.createRow(rowActualFinal);
			rowActualFinal = rowActualFinal+1;
			HSSFRow row32 = sheet.createRow(rowActualFinal);
			row31.setHeightInPoints(25);
			HSSFCell row31Column1 = row31.createCell(1);
			HSSFCell row32Column1 = row32.createCell(1);
			HSSFCell row32Column2 = row32.createCell(2);
			
			row31Column1.setCellValue("Comprobantes");
			definicionColumnas.getPalabrasEnNegrita().add(row31Column1);
			
			row32Column1.setCellValue("Nombre");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column1);
			row32Column2.setCellValue("Descripción");
			definicionColumnas.getCeldasConBordesYColorDeTabla().add(row32Column2);
	
			List<ComprobanteDto> comprobanteList = new ArrayList<ComprobanteDto>(legajoDto.getListaComprobantes());
			List<? extends DTO> comprobanteFinal;
			Iterator<ComprobanteDto> iterator = null;
			try {
				comprobanteFinal = Utilidad.guionesNull(comprobanteList);
				iterator = (Iterator<ComprobanteDto>) comprobanteFinal.iterator();
			} catch (ShivaExcepcion e) {
				new NegocioExcepcion(e.getMessage(), e);
			}
			rowActualFinal = rowActualFinal+1;
			int rowCrear = rowActualFinal;
			while(iterator.hasNext()){
				ComprobanteDto comprobante = iterator.next();
				HSSFRow row = sheet.createRow(rowCrear);
				
				HSSFCell nombre = row.createCell(1);
				nombre.setCellValue(comprobante.getNombreArchivo());
				definicionColumnas.getCeldasConBordes().add(nombre);
				HSSFCell descripcion = row.createCell(2);
				descripcion.setCellValue(comprobante.getDescripcionArchivo());
				definicionColumnas.getCeldasConBordes().add(descripcion);

				rowCrear++;
			}
			return rowCrear;
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
	 */
	public void pintarCeldasTabla(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
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
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		Collection<HSSFCell> celdasConBordesDeTablaObservacion = definicionColumnas.getCeldasConBordesYColorDeTablaObservacion();
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
		for (HSSFCell hssfCell : celdasConBordesDeTablaObservacion) {
			hssfCell.setCellStyle(style);
		}
	}
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaContenido(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		Collection<HSSFCell> celdasConBordesDeTabla2 = definicionColumnas.getCeldasConBordes();
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
		for (HSSFCell hssfCell : celdasConBordesDeTabla2) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasConBordesCentrado(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		Collection<HSSFCell> celdasConBordesDeTabla2 = definicionColumnas.getCeldasConBordesCentrado();
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
		for (HSSFCell hssfCell : celdasConBordesDeTabla2) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegrita(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		Collection<HSSFCell> palabrasEnNegrita2 = definicionColumnas.getPalabrasEnNegrita();
		HSSFCellStyle estiloNegrita = workbook.createCellStyle();
		HSSFFont negrita = workbook.createFont();
		negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		estiloNegrita.setFont(negrita);
		for (HSSFCell hssfCell : palabrasEnNegrita2) {
			hssfCell.setCellStyle(estiloNegrita);
		}
	}
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegritaYsubrayado(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		Collection<HSSFCell> palabrasEnNegritaYsubrayado2 = definicionColumnas.getPalabrasEnNegritaYsubrayado();
		HSSFCellStyle estiloNegrita = workbook.createCellStyle();
		HSSFFont negrita = workbook.createFont();
		negrita.setUnderline(HSSFFont.U_SINGLE);
		negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		estiloNegrita.setFont(negrita);
		for (HSSFCell hssfCell : palabrasEnNegritaYsubrayado2) {
			hssfCell.setCellStyle(estiloNegrita);
		}
	}	
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param workbook
	 * @param sheet
	 */
	public void validarGuinesCentrarYImporteRightStyle(HSSFWorkbook workbook,
			HSSFSheet sheet, ExportacionExcelDefinicionColumnas definicionColumnas){
		List<HSSFCell> listaAux = new ArrayList<HSSFCell>();
		Collection<HSSFCell> celdasConBordes = definicionColumnas.getCeldasConBordes();
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
		for (HSSFCell hssfCell : celdasConBordes) {
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
		
	}
	
	
	/**
	 * @author u572487 Guido.N.Settecerze
	 * @param sheet
	 */
	public void pintarFondoBlancoHoja(HSSFSheet sheet){
		sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
	}
	
//	
//
//	public Collection<String> getTitulosGrales() {
//		return titulosGrales;
//	}
//
//	public void setTitulosGrales(Collection<String> titulosGrales) {
//		this.titulosGrales = titulosGrales;
//	}
//
//	public Collection<String> getColumnNames() {
//		return columnNames;
//	}
//
//	public void setColumnNames(Collection<String> columnNames) {
//		this.columnNames = columnNames;
//	}
//
//	public Collection<String> getColumnDescriptions() {
//		return columnDescriptions;
//	}
//
//	public void setColumnDescriptions(Collection<String> columnDescriptions) {
//		this.columnDescriptions = columnDescriptions;
//	}
//
//	public Collection<String> getColumnDescriptionsGral() {
//		return columnDescriptionsGral;
//	}
//
//	public void setColumnDescriptionsGral(Collection<String> columnDescriptionsGral) {
//		this.columnDescriptionsGral = columnDescriptionsGral;
//	}
//
//	public Collection<String> getColumnNamesGral() {
//		return columnNamesGral;
//	}
//
//	public void setColumnNamesGral(Collection<String> columnNamesGral) {
//		this.columnNamesGral = columnNamesGral;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getXls() {
//		return xls;
//	}
//
//	public void setXls(String xls) {
//		this.xls = xls;
//	}
//
//
//	public Collection<HSSFCell> getCeldasConBordesYColorDeTabla() {
//		return celdasConBordesYColorDeTabla;
//	}
//
//
//	public void setCeldasConBordesYColorDeTabla(Collection<HSSFCell> celdasConBordesYColorDeTabla) {
//		this.celdasConBordesYColorDeTabla = celdasConBordesYColorDeTabla;
//	}
//
//
//	public Collection<HSSFCell> getPalabrasEnNegrita() {
//		return palabrasEnNegrita;
//	}
//
//
//	public void setPalabrasEnNegrita(Collection<HSSFCell> palabrasEnNegrita) {
//		this.palabrasEnNegrita = palabrasEnNegrita;
//	}
//
//
//	public Collection<HSSFCell> getPalabrasEnNegritaYsubrayado() {
//		return palabrasEnNegritaYsubrayado;
//	}
//
//
//	public void setPalabrasEnNegritaYsubrayado(
//			Collection<HSSFCell> palabrasEnNegritaYsubrayado) {
//		this.palabrasEnNegritaYsubrayado = palabrasEnNegritaYsubrayado;
//	}
//
//
//	public Collection<HSSFCell> getCeldasConBordes() {
//		return celdasConBordes;
//	}
//
//
//	public void setCeldasConBordes(Collection<HSSFCell> celdasConBordes) {
//		this.celdasConBordes = celdasConBordes;
//	}
//
//
//	public Collection<HSSFCell> getCeldasConBordesYColorDeTablaObservacion() {
//		return celdasConBordesYColorDeTablaObservacion;
//	}
//
//
//	public void setCeldasConBordesYColorDeTablaObservacion(
//			Collection<HSSFCell> celdasConBordesYColorDeTablaObservacion) {
//		this.celdasConBordesYColorDeTablaObservacion = celdasConBordesYColorDeTablaObservacion;
//	}
//
//
//	public Collection<HSSFCell> getCeldasConBordesCentrado() {
//		return celdasConBordesCentrado;
//	}
//
//
//	public void setCeldasConBordesCentrado(Collection<HSSFCell> celdasConBordesCentrado) {
//		this.celdasConBordesCentrado = celdasConBordesCentrado;
//	}
//
//	public String getXls() {
//		return xls;
//	}
//
//	public void setXls(String xls) {
//		this.xls = xls;
//	}

	
	public String getValorDeCelda(HSSFCell celda){
		String salida;
		
			if (celda.getCellType() == Cell.CELL_TYPE_STRING ) {
					salida = celda.getStringCellValue();
			} else {
				salida = String.valueOf(celda.getNumericCellValue());
			}
		return salida;
	}
}
