package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.util.FileCopyUtils;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IExcelBusquedaCobroServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

/**
 * Clase de Exportacion de la busqueda de Cobros.
 * 
 * @author u562163 
 */
public class ExportacionBusquedaCobrosImpl implements IExcelBusquedaCobroServicio {
	
	
	private HSSFCellStyle estiloCurrency;
	
    
	private static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String ATTACHMENT_FILENAME = "attachment; filename=";

	private String name;
	private Collection<String> titulosGrales;
	private Collection<String> columnNames;
	private Collection<String> columnDescriptions;
	private Collection<String> columnDescriptionsGral;
	private Collection<String> columnNamesGral;
	
	private Collection<HSSFCell> celdasConBordesYColorDeTabla = new ArrayList<HSSFCell>();
	private Collection<HSSFCell> celdasConBordesYColorDeTablaObservacion = new ArrayList<HSSFCell>();
	private Collection<HSSFCell> celdasConBordes = new ArrayList<HSSFCell>();
	private Collection<HSSFCell> celdasConBordesCentrado = new ArrayList<HSSFCell>();
	private Collection<HSSFCell> palabrasEnNegrita = new ArrayList<HSSFCell>();
	private Collection<HSSFCell> palabrasEnNegritaYsubrayado = new ArrayList<HSSFCell>();
	
	private String xls = ".xls";
	
	
	/**
	 * Metodo que exporta a excel la busqueda de valores
	 * @author u562163
	 */
	public void generarExportacionBusquedaCobros(HttpServletResponse response, List<CobroDto> listaCobros) throws NegocioExcepcion {
		
		try {
			
			if(Validaciones.isCollectionNotEmpty(listaCobros)){
				ByteArrayOutputStream salida = new ByteArrayOutputStream();
				//Creo la planilla
				HSSFWorkbook workbook = createBook(listaCobros);
			
				workbook.write(salida);
				
				response.setContentType(XLS_CONTENT_TYPE);
		    	response.setContentLength(salida.size());
		    	
		    	//Nombre del archivo
		    	response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "Busqueda cobros" + xls);
		    	
		    	FileCopyUtils.copy(salida.toByteArray(), response.getOutputStream());
			}
		} catch (IOException e) {
			new NegocioExcepcion(e.getMessage(), e);
			
		}
	}
	
	/**
	 * Metodo que arma toda la planilla.
	 * @author u562163
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	protected HSSFWorkbook createBook(List<CobroDto> listaCobros) throws NegocioExcepcion{
		
		celdasConBordesYColorDeTabla.clear();
		celdasConBordesYColorDeTablaObservacion.clear();
		getCeldasConBordesCentrado().clear();
		celdasConBordes.clear();
		getPalabrasEnNegrita().clear();
		getPalabrasEnNegritaYsubrayado().clear();
		
		//Planilla
		HSSFWorkbook workbook = new HSSFWorkbook();
		estiloCurrency = workbook.createCellStyle();
		HSSFDataFormat df = workbook.createDataFormat();
		estiloCurrency.setDataFormat(df.getFormat("#,##0.00"));
		
		//Hoja
		HSSFSheet sheet = workbook.createSheet("Busqueda de cobros");//Nombre de la hoja

		pintarFondoBlancoHoja(sheet);
		
		int cantidadColumnas = fillTablaCobros(workbook, sheet, listaCobros);
		
		validarGuinesCentrarYImporteRightStyle(workbook, sheet);
		pintarCeldasTabla(workbook, sheet);
		pintarCeldasTablaObservaciones(workbook, sheet);
		pintarCeldasTablaContenido(workbook, sheet);
		pintarCeldasConBordesCentrado(workbook, sheet);
		palabrasEnNegrita(workbook, sheet);
		palabrasEnNegritaYsubrayado(workbook, sheet);
		
		celdasConBordesYColorDeTabla.clear();
		celdasConBordesYColorDeTablaObservacion.clear();
		celdasConBordes.clear();
		getCeldasConBordesCentrado().clear();
		getPalabrasEnNegrita().clear();
		getPalabrasEnNegritaYsubrayado().clear();
		
		//IMPORTANTE
		autoAjusteDeColumna(sheet,  cantidadColumnas);
		return workbook;
	}

	@SuppressWarnings("unchecked")
	private int fillTablaCobros(HSSFWorkbook workbook, HSSFSheet sheet, List<CobroDto> listaCobros) {
		HSSFRow row26 = sheet.createRow(1);
		
		HSSFRow row27 = sheet.createRow(2);
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
		HSSFCell row27Column33 = row27.createCell(33);
		HSSFCell row27Column34 = row27.createCell(34);
		HSSFCell row27Column35 = row27.createCell(35);
		HSSFCell row27Column36 = row27.createCell(36);
		HSSFCell row27Column37 = row27.createCell(37);
		HSSFCell row27Column38 = row27.createCell(38);
		HSSFCell row27Column39 = row27.createCell(39);
		HSSFCell row27Column40 = row27.createCell(40);
		HSSFCell row27Column41 = row27.createCell(41);
		HSSFCell row27Column42 = row27.createCell(42);
		HSSFCell row27Column43 = row27.createCell(43);
		HSSFCell row27Column44 = row27.createCell(44);
		HSSFCell row27Column45 = row27.createCell(45);
		HSSFCell row27Column46 = row27.createCell(46);
		HSSFCell row27Column47 = row27.createCell(47);
		HSSFCell row27Column48 = row27.createCell(48);
		HSSFCell row27Column49 = row27.createCell(49);
		HSSFCell row27Column50 = row27.createCell(50);
		
		row26Column1.setCellValue("Búsqueda de valores");
		palabrasEnNegrita.add(row26Column1);
		
		/*
		 * <th><spring:message
					code="cobro.busqueda.resultado.idOperacionCobro" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.motivoCobro" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.cliente" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.estado" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.subestado" /></th>
			<th class="datetimeSeconds"><spring:message
					code="cobro.busqueda.resultado.fechaUltimoEstado" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.analista" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.copropietario" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.analistaTeamComercial" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.idReversionPadre" /></th>
			<th><spring:message
					code="cobro.busqueda.resultado.idReversion" /></th>
			<th class="importe"><spring:message
					code="cobro.busqueda.resultado.importeTotal" /></th>
			<th class="dateTimes"><spring:message
					code="cobro.busqueda.resultado.fechaAlta" /></th>
			<th class="dateTimes"><spring:message
					code="cobro.busqueda.resultado.fechaDerivacion" /></th>
			<th class="dateTimes"><spring:message
					code="cobro.busqueda.resultado.fechaAprobacionReferente" /></th>
			<th class="dateTimes"><spring:message
					code="cobro.busqueda.resultado.fechaImputacion" /></th>
															*/
		
		row27Column1.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.empresa"));
		celdasConBordesYColorDeTabla.add(row27Column1);
		row27Column2.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.segmento"));
		celdasConBordesYColorDeTabla.add(row27Column2);
		row27Column3.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.codCliente").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column3);
		row27Column4.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.razonSocialAgrupador").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column4);
		row27Column5.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaValor").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column5);
		row27Column6.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.tipoValor").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column6);
		row27Column7.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.importe"));
		celdasConBordesYColorDeTabla.add(row27Column7);
		row27Column8.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.saldoDisponible").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column8);
		row27Column9.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.estadoValor").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column9);
		row27Column10.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.analista"));
		celdasConBordesYColorDeTabla.add(row27Column10);
		row27Column11.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.copropietario").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column11);
		row27Column12.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.usuarioAutorizacion").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column12);
		row27Column13.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.ejecutivo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column13);
		row27Column14.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.asistente").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column14);
		row27Column15.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.analistaTeamComercial").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column15);
		row27Column16.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.supervisorTeamComercial").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column16);
		row27Column17.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.gerenteRegionalTeamComercial").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column17);
		row27Column18.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.identificadorInternoValor").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column18);
		row27Column19.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.origen").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column19);
		row27Column20.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.bancoDeposito").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column20);
		row27Column21.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroAcuerdo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column21);
		row27Column22.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroBoleta").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column22);
		row27Column23.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.referenciaValor").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column23);
		row27Column24.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.descripcionBancoOrigen").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column24);
		row27Column25.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.descripcionTipoImpuesto").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column25);
		row27Column26.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.provincias").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column26);
		row27Column27.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.cuit").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column27);
		row27Column28.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.codOrganismo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column28);
		row27Column29.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroRecibo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column29);
		row27Column30.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroConstanciaRecepcion").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column30);
		row27Column31.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.operacionAsociada").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column31);
		row27Column32.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.facturaRelacionada").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column32);
		row27Column33.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.documentacionOriginalRecibida").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column33);
		row27Column34.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.motivo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column34);
		row27Column35.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroChequeAReemplazar").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column35);
		row27Column36.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.nroDocumentoContable").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column36);
		row27Column37.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.motivoSuspencion").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column37);
		row27Column38.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.archivoValoresConciliar").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column38);
		row27Column39.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaAlta").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column39);
		row27Column40.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaIngresoRecibo").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column40);
		row27Column41.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaEmision").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column41);
		row27Column42.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaVencimiento").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column42);
		row27Column43.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaTransferencia").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column43);
		row27Column44.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaDeposito").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column44);
		row27Column45.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaDisponible").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column45);
		row27Column46.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaUltimoEstado").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column46);
		row27Column47.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.bdImpresa").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column47);
		row27Column48.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.bdEnviadaMail").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column48);
		row27Column49.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.observaciones").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column49);
		row27Column50.setCellValue(Propiedades.MENSAJES_PROPIEDADES.getString("valor.cobroFormateado").replaceAll("&nbsp;", " "));
		celdasConBordesYColorDeTabla.add(row27Column50);
		
		List<? extends DTO> debitosFinal;
		Iterator<ValorDto> iterator = null;
		try {
			debitosFinal = Utilidad.guionesNull(listaCobros);
			iterator = (Iterator<ValorDto>) debitosFinal.iterator();
		} catch (ShivaExcepcion e) {
			new NegocioExcepcion(e.getMessage(), e);
		}
		
		int rowCrear = 3;
		while(iterator.hasNext()){
			ValorDto valor = iterator.next();
			
			HSSFRow row = sheet.createRow(rowCrear);
			
			HSSFCell empresa = row.createCell(1);
			empresa.setCellValue(valor.getEmpresa());
			celdasConBordes.add(empresa);
			
			HSSFCell segmento = row.createCell(2);
			segmento.setCellValue(valor.getSegmento());
			celdasConBordes.add(segmento);
			
			HSSFCell codCliente = row.createCell(3);
			codCliente.setCellValue(valor.getCodCliente());
			celdasConBordes.add(codCliente);
			
			HSSFCell razonSocialAgrupador = row.createCell(4);
			razonSocialAgrupador.setCellValue(valor.getRazonSocialClienteAgrupador());
			celdasConBordes.add(razonSocialAgrupador);
			
			HSSFCell fechaValor = row.createCell(5);
			fechaValor.setCellValue(valor.getFechaValorFormateado());
			celdasConBordes.add(fechaValor);
			
			HSSFCell tipoValor = row.createCell(6);
			tipoValor.setCellValue(valor.getTipoValor());
			celdasConBordes.add(tipoValor);
			
			HSSFCell importe = row.createCell(7);
			importe.setCellValue(valor.getImporte());
			celdasConBordes.add(importe);
			
			HSSFCell saldoDisponible = row.createCell(8);
			saldoDisponible.setCellValue(valor.getSaldoDisponible());
			celdasConBordes.add(saldoDisponible);
			
			HSSFCell estadoValor = row.createCell(9);
			estadoValor.setCellValue(valor.getEstadoValor());
			celdasConBordes.add(estadoValor);
			
			HSSFCell analista = row.createCell(10);
			analista.setCellValue(valor.getNombreAnalista());
			celdasConBordes.add(analista);
			
			HSSFCell copropietario = row.createCell(11);
			copropietario.setCellValue(valor.getCopropietario());
			celdasConBordes.add(copropietario);
			
			HSSFCell usuarioAutorizacion = row.createCell(12);
			usuarioAutorizacion.setCellValue(valor.getUsuarioAutorizacion());
			celdasConBordes.add(usuarioAutorizacion);
			
			HSSFCell ejecutivo = row.createCell(13);
			ejecutivo.setCellValue(valor.getUsuarioEjecutivo());
			celdasConBordes.add(ejecutivo);
			
			HSSFCell asistente = row.createCell(14);
			asistente.setCellValue(valor.getUsuarioAsistente());
			celdasConBordes.add(asistente);
			
			HSSFCell analistaTeamComercial = row.createCell(15);
			analistaTeamComercial.setCellValue(valor.getUsuarioAnalistaTeamComercial());
			celdasConBordes.add(analistaTeamComercial);
			
			HSSFCell supervisorTeamComercial = row.createCell(16);
			supervisorTeamComercial.setCellValue(valor.getUsuarioSupervisorTeamComercial());
			celdasConBordes.add(supervisorTeamComercial);
			
			HSSFCell gerenteRegionalTeamComercial = row.createCell(17);
			gerenteRegionalTeamComercial.setCellValue(valor.getUsuarioGerenteRegionalTeamComercial());
			celdasConBordes.add(gerenteRegionalTeamComercial);
			
			HSSFCell identificadorInternoValor = row.createCell(18);
			identificadorInternoValor.setCellValue(valor.getIdValor());
			celdasConBordes.add(identificadorInternoValor);
			
			HSSFCell origen = row.createCell(19);
			origen.setCellValue(valor.getOrigen());
			celdasConBordes.add(origen);
			
			HSSFCell bancoDeposito = row.createCell(20);
			bancoDeposito.setCellValue(valor.getBancoDeposito());
			celdasConBordes.add(bancoDeposito);
			
			HSSFCell idAcuerdo = row.createCell(21);
			idAcuerdo.setCellValue(valor.getIdAcuerdo());
			celdasConBordes.add(idAcuerdo);
			
			HSSFCell nroBoleta = row.createCell(22);
			nroBoleta.setCellValue(valor.getNroBoleta());
			celdasConBordes.add(nroBoleta);
			
			HSSFCell referenciaValor = row.createCell(23);
			referenciaValor.setCellValue(valor.getReferenciaValor());
			celdasConBordes.add(referenciaValor);
			
			HSSFCell bancoOrigen = row.createCell(24);
			bancoOrigen.setCellValue(valor.getBancoOrigen());
			celdasConBordes.add(bancoOrigen);
			
			HSSFCell tipoRetencion = row.createCell(25);
			tipoRetencion.setCellValue(valor.getTipoRetencion());
			celdasConBordes.add(tipoRetencion);
			
			HSSFCell provincia = row.createCell(26);
			provincia.setCellValue(valor.getProvincia());
			celdasConBordes.add(provincia);
			
			HSSFCell nroCuitRetencion = row.createCell(27);
			nroCuitRetencion.setCellValue(valor.getNroCuitRetencion());
			celdasConBordes.add(nroCuitRetencion);
			
			HSSFCell codigoOrganismo = row.createCell(28);
			codigoOrganismo.setCellValue(valor.getCodigoOrganismo());
			celdasConBordes.add(codigoOrganismo);
			
			HSSFCell reciboPreImpreso = row.createCell(29);
			reciboPreImpreso.setCellValue(valor.getReciboPreImpreso());
			celdasConBordes.add(reciboPreImpreso);
			
			HSSFCell constancia = row.createCell(30);
			constancia.setCellValue(valor.getConstancia());
			celdasConBordes.add(constancia);
			
			HSSFCell operacionAsociada = row.createCell(31);
			operacionAsociada.setCellValue(valor.getOperacionAsociada());
			celdasConBordes.add(operacionAsociada);
			
			HSSFCell facturaRelacionada = row.createCell(32);
			facturaRelacionada.setCellValue(valor.getFacturaRelacionada());
			celdasConBordes.add(facturaRelacionada);
			
			HSSFCell documentacionOriginalRecibida = row.createCell(33);
			documentacionOriginalRecibida.setCellValue(valor.getDocumentacionOriginalRecibida());
			celdasConBordes.add(documentacionOriginalRecibida);
			
			HSSFCell motivo = row.createCell(34);
			motivo.setCellValue(valor.getMotivo());
			celdasConBordes.add(motivo);
			
			HSSFCell valorPadre = row.createCell(35);
			valorPadre.setCellValue(valor.getValorPadre());
			celdasConBordes.add(valorPadre);
			
			HSSFCell numeroDocumentoContable = row.createCell(36);
			numeroDocumentoContable.setCellValue(valor.getNumeroDocumentoContable());
			celdasConBordes.add(numeroDocumentoContable);
			
			HSSFCell motivoSuspension = row.createCell(37);
			motivoSuspension.setCellValue(valor.getMotivoSuspension());
			celdasConBordes.add(motivoSuspension);
			
			HSSFCell archivoValoresAconciliar = row.createCell(38);
			archivoValoresAconciliar.setCellValue(valor.getArchivoValoresAconciliar());
			celdasConBordes.add(archivoValoresAconciliar);
			
			HSSFCell fechaAltaValor = row.createCell(39);
			fechaAltaValor.setCellValue(valor.getFechaAltaValor());
			celdasConBordes.add(fechaAltaValor);
			
			HSSFCell fechaIngresoRecibo = row.createCell(40);
			fechaIngresoRecibo.setCellValue(valor.getFechaIngresoRecibo());
			celdasConBordes.add(fechaIngresoRecibo);
			
			HSSFCell fechaEmision = row.createCell(41);
			fechaEmision.setCellValue(valor.getFechaEmision());
			celdasConBordes.add(fechaEmision);
			
			HSSFCell fechaVencimiento = row.createCell(42);
			fechaVencimiento.setCellValue(valor.getFechaVencimiento());
			celdasConBordes.add(fechaVencimiento);
			
			HSSFCell fechaTransferencia = row.createCell(43);
			fechaTransferencia.setCellValue(valor.getFechaTransferencia());
			celdasConBordes.add(fechaTransferencia);
			
			HSSFCell fechaDeposito = row.createCell(44);
			fechaDeposito.setCellValue(valor.getFechaDeposito());
			celdasConBordes.add(fechaDeposito);
			
			HSSFCell fechaDisponible = row.createCell(45);
			fechaDisponible.setCellValue(valor.getFechaDisponible());
			celdasConBordes.add(fechaDisponible);
			
			HSSFCell fechaUltimoEstado = row.createCell(46);
			fechaUltimoEstado.setCellValue(valor.getFechaUltimoEstado());
			celdasConBordes.add(fechaUltimoEstado);
			
			HSSFCell boletaImpresaEstado = row.createCell(47);
			boletaImpresaEstado.setCellValue(valor.getBoletaImpresaEstado().getDescripcion());
			celdasConBordes.add(boletaImpresaEstado);
			
			HSSFCell boletaEnviadaMailEstado = row.createCell(48);
			boletaEnviadaMailEstado.setCellValue(valor.getBoletaEnviadaMailEstado().getDescripcion());
			celdasConBordes.add(boletaEnviadaMailEstado);
			
			HSSFCell observaciones = row.createCell(49);
			observaciones.setCellValue(valor.getObservaciones());
			celdasConBordes.add(observaciones);
			
			HSSFCell cobroFormateado = row.createCell(50);
			cobroFormateado.setCellValue(valor.getCobroFormateado());
			celdasConBordes.add(cobroFormateado);
			
			rowCrear++;
		}
		return rowCrear;
	}

	
	/***************************************************************************
	 * ESTILOS PARA LAS CELDAS
	 * ************************************************************************/
	
	/**
	 * @author u562163
	 * @param sheet
	 */
	private void autoAjusteDeColumna(HSSFSheet sheet, int cantidadColumnas) {
		for(int i = 0; i < cantidadColumnas; i++)
		{
			sheet.autoSizeColumn((short)i);
		}
	}
	
	/**
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTabla(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> celdasConBordesDeTabla2 = getCeldasConBordesYColorDeTabla();
		HSSFCellStyle style = workbook.createCellStyle();
//		style.setWrapText(true);
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
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaObservaciones(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> celdasConBordesDeTablaObservacion = getCeldasConBordesYColorDeTablaObservacion();
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
		for (HSSFCell hssfCell : celdasConBordesDeTablaObservacion) {
			hssfCell.setCellStyle(style);
		}
	}
	
	/**
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasTablaContenido(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> celdasConBordesDeTabla2 = getCeldasConBordes();
		HSSFCellStyle style = workbook.createCellStyle();
//		style.setWrapText(true);
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
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void pintarCeldasConBordesCentrado(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> celdasConBordesDeTabla2 = getCeldasConBordesCentrado();
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
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegrita(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> palabrasEnNegrita2 = getPalabrasEnNegrita();
		HSSFCellStyle estiloNegrita = workbook.createCellStyle();
		HSSFFont negrita = workbook.createFont();
		negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		estiloNegrita.setFont(negrita);
		for (HSSFCell hssfCell : palabrasEnNegrita2) {
			hssfCell.setCellStyle(estiloNegrita);
		}
	}
	
	/**
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void palabrasEnNegritaYsubrayado(HSSFWorkbook workbook,
			HSSFSheet sheet){
		Collection<HSSFCell> palabrasEnNegritaYsubrayado2 = getPalabrasEnNegritaYsubrayado();
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
	 * @author u562163
	 * @param workbook
	 * @param sheet
	 */
	public void validarGuinesCentrarYImporteRightStyle(HSSFWorkbook workbook,
			HSSFSheet sheet){
		List<HSSFCell> listaAux = new ArrayList<HSSFCell>();
		Collection<HSSFCell> celdasConBordes = getCeldasConBordes();
		
		HSSFCellStyle style = workbook.createCellStyle();
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
		
		HSSFCellStyle styleImporte = workbook.createCellStyle();
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
		styleImporte.setDataFormat((short)7); 
		
		for (HSSFCell hssfCell : celdasConBordes) {
			if(hssfCell.CELL_TYPE_STRING==hssfCell.getCellType()){
				if(!Validaciones.isNullOrEmpty(hssfCell.getStringCellValue()) && hssfCell.getStringCellValue().startsWith("$")){
					hssfCell.setCellStyle(styleImporte);
				}else if(!"-".equals(hssfCell.getStringCellValue())){
					listaAux.add(hssfCell);
				}else{
					hssfCell.setCellStyle(style);
				}
			} else if(hssfCell.CELL_TYPE_NUMERIC==hssfCell.getCellType()){
				
			}
		}
		setCeldasConBordes(listaAux);
	}
	
	/**
	 * @author u562163
	 * @param sheet
	 */
	public void pintarFondoBlancoHoja(HSSFSheet sheet){
		sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
	}
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public Collection<String> getTitulosGrales() {
		return titulosGrales;
	}

	public void setTitulosGrales(Collection<String> titulosGrales) {
		this.titulosGrales = titulosGrales;
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

	public Collection<String> getColumnDescriptionsGral() {
		return columnDescriptionsGral;
	}

	public void setColumnDescriptionsGral(Collection<String> columnDescriptionsGral) {
		this.columnDescriptionsGral = columnDescriptionsGral;
	}

	public Collection<String> getColumnNamesGral() {
		return columnNamesGral;
	}

	public void setColumnNamesGral(Collection<String> columnNamesGral) {
		this.columnNamesGral = columnNamesGral;
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


	public Collection<HSSFCell> getCeldasConBordesYColorDeTabla() {
		return celdasConBordesYColorDeTabla;
	}


	public void setCeldasConBordesYColorDeTabla(Collection<HSSFCell> celdasConBordesYColorDeTabla) {
		this.celdasConBordesYColorDeTabla = celdasConBordesYColorDeTabla;
	}


	public Collection<HSSFCell> getPalabrasEnNegrita() {
		return palabrasEnNegrita;
	}


	public void setPalabrasEnNegrita(Collection<HSSFCell> palabrasEnNegrita) {
		this.palabrasEnNegrita = palabrasEnNegrita;
	}


	public Collection<HSSFCell> getPalabrasEnNegritaYsubrayado() {
		return palabrasEnNegritaYsubrayado;
	}


	public void setPalabrasEnNegritaYsubrayado(
			Collection<HSSFCell> palabrasEnNegritaYsubrayado) {
		this.palabrasEnNegritaYsubrayado = palabrasEnNegritaYsubrayado;
	}


	public Collection<HSSFCell> getCeldasConBordes() {
		return celdasConBordes;
	}


	public void setCeldasConBordes(Collection<HSSFCell> celdasConBordes) {
		this.celdasConBordes = celdasConBordes;
	}


	public Collection<HSSFCell> getCeldasConBordesYColorDeTablaObservacion() {
		return celdasConBordesYColorDeTablaObservacion;
	}


	public void setCeldasConBordesYColorDeTablaObservacion(
			Collection<HSSFCell> celdasConBordesYColorDeTablaObservacion) {
		this.celdasConBordesYColorDeTablaObservacion = celdasConBordesYColorDeTablaObservacion;
	}


	public Collection<HSSFCell> getCeldasConBordesCentrado() {
		return celdasConBordesCentrado;
	}


	public void setCeldasConBordesCentrado(Collection<HSSFCell> celdasConBordesCentrado) {
		this.celdasConBordesCentrado = celdasConBordesCentrado;
	}
	
}
