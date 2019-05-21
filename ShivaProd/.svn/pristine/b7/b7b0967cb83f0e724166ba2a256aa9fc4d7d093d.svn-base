package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ExportacionExcelDefinicionColumnas;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReporteAplicacionManualPendientesServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteAplicacionManualPendientes;
import ar.com.telecom.shiva.persistencia.dao.IReporteAplicacionManualPendienteDao;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteAplicacionManualPendientesFiltro;

public class ReporteAplicacionManualPendientesServicioImpl implements IReporteAplicacionManualPendientesServicio {
	
	@Autowired IReporteAplicacionManualPendienteDao reporteAplicacionManualPendientesDao;
	@Autowired ILdapServicio ldapServicio;
	@Autowired private MailServicio mailServicio;
	@Autowired private IParametroServicio parametroServicio;
	
	/**
	 * Genera Archivo Reporte de Aplicación Manual Pendientes
	 * 
	 * @author U587496 Guido.Settecerze
	 * @throws NegocioExcepcion
	 */
	public void generarArchivoReporteAplicacionManualPendientes(String fecha) throws NegocioExcepcion{
		
		try {
			
			Traza.auditoria(getClass(), "Comienzo del metodo generarArchivoReporteAplicacionManualPendientes");
			
			String sistemasString = parametroServicio.getValorTexto(Constantes.LISTA_EMPRESAS_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			List<SistemaEnum> sistemas = SistemaEnum.obtenerListaDeSistemas(sistemasString);
			
			Long numerosDeDiasARestar = parametroServicio.getValorNumerico(Constantes.REPORTE_APLICACION_MANUAL_PENDIENTE_DIAS_A_RESTAR);
			
			String fechaFiltro = calcularFecha(fecha, numerosDeDiasARestar.intValue());
			
			//Logica para el armado de un archivo por empresa
			for(SistemaEnum sistema : sistemas) { 
				
				//Armado de filtro
				VistaSoporteReporteAplicacionManualPendientesFiltro filtro = new VistaSoporteReporteAplicacionManualPendientesFiltro(sistema.name(),fechaFiltro);
				
				//Busco todos los datos necesarios para armar el reporte de los cobros pendientes de confirmacion aplicacion manual.
				List<VistaSoporteAplicacionManualPendientes> reporteAplicacionManualPendientes = reporteAplicacionManualPendientesDao.buscarCobros(filtro);

				Traza.auditoria(getClass(), "~");
				Traza.auditoria(getClass(), "~ Para el sistema: " + sistema + " se obtuvieron " + reporteAplicacionManualPendientes.size() + " tareas pendientes de aprobacion.");
				Traza.auditoria(getClass(), "~");
				
				enviarMailReporteAplicacionManualPendientesYgeneracionArchivo(reporteAplicacionManualPendientes, sistema, fechaFiltro);
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	private String calcularFecha(String fecha, int numerosDeDiasARestar) throws NegocioExcepcion{
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaDelDia = null;
		try {

			fechaDelDia = formatoDelTexto.parse(fecha);

		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		String fechaNueva = Utilidad.formatDateDDMMAAAAconBarra(Utilidad.restarDiasAFecha(fechaDelDia, numerosDeDiasARestar));
		
		return fechaNueva;
		
	}
	/**
	 * 
	 * @param cuerpoMail
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	private void enviarMailReporteAplicacionManualPendientesYgeneracionArchivo(List<VistaSoporteAplicacionManualPendientes> reporteAplicacionManualPendientes, SistemaEnum sistema, String fechaFiltro) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail con detalle de procesamiento...");
			
			// Destinatarios 'para'
			//
			
			TipoPerfilEnum perfilBusqueda = TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS;
			
			if (!SistemaEnum.CABLEVISION.equals(sistema) && !SistemaEnum.NEXTEL.equals(sistema) && !SistemaEnum.FIBERTEL.equals(sistema)) {
				perfilBusqueda = TipoPerfilEnum.REFERENTE_CAJA;
			}
			
			StringBuffer destinatariosPara = new StringBuffer();
			Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilBusqueda.descripcion());
			for (UsuarioLdapDto usuario: usuariosLdap) {
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					destinatariosPara.append(usuario.getMail());
					destinatariosPara.append(";");
				}
			}
			
			// Destinatarios 'en copia'
			//
			String listaDestinatarioCorreoEnComun = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_COMUN_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			String listaDestinatarioCorreoPorSistema = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			String destinatariosCC = listaDestinatarioCorreoEnComun + ";" + obtenerGrupoDeMails(listaDestinatarioCorreoPorSistema, sistema.getDescripcionCorta());
			
			String asunto = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.aplicacion.manual.pendientes"), sistema.getDescripcion());
			
			// Cuerpo de Mail + Adjuntos
			//
			StringBuffer cuerpoMail = new StringBuffer();
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			if (Validaciones.isCollectionNotEmpty(reporteAplicacionManualPendientes)) {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.manual.existe.pendientes"), fechaFiltro));
				
				ByteArrayOutputStream outputStream = null;
				
				outputStream = new ByteArrayOutputStream();
				HSSFWorkbook workbook = exportarReporteAplicacionManualPendiente(reporteAplicacionManualPendientes);
				workbook.write(outputStream);
				
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				
				Date fechaDelDia = null;
				try {

					fechaDelDia = formatoDelTexto.parse(fechaFiltro);

				} catch (ParseException e) {
					throw new NegocioExcepcion(e.getMessage(), e);
				}
				
				String nombreArchivo = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporteAplicacionManualPendientes.nombre.archivo"), 
						sistema.getDescripcionCorta(), Utilidad.formatDateAAAAMMDD(fechaDelDia));
				
				listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), nombreArchivo, "xls"));
				
			} else {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.manual.no.existe.pendientes"), fechaFiltro));
				
			}
			
			// Si tengo destinatarios, armo mail y lo envío
			
			if (!Validaciones.isNullOrEmpty(destinatariosPara.toString()) && !Validaciones.isNullOrEmpty(destinatariosCC)) {
				
				Mail mailDetalleProcesamiento = new Mail(destinatariosPara.toString(), 
									 destinatariosCC.replace(",", ";") + ";", 
									 asunto,
									 cuerpoMail);
				
				if (Validaciones.isCollectionNotEmpty(listaAdjuntos)){
					
					mailDetalleProcesamiento.setAdjuntos(listaAdjuntos);
					
				}

				mailServicio.sendMail(mailDetalleProcesamiento);
				
			} else {
				Traza.auditoria(getClass(), "~ Error: no existen usuarios con perfil '" 
											+ TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.descripcion() 
											+ "' ni destinatarios configurados para la empresa '" 
											+ sistema.getDescripcion() 
											+ " (" + sistema.name() + ")"
											+ "' para poder enviar el detalle del procesamiento.");
			}
			
			Traza.auditoria(getClass(), "~ Fin de envío de mail.");
			Traza.auditoria(getClass(), "~");
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param grupoDeMails
	 * @param sistema
	 * @return
	 */
	private String obtenerGrupoDeMails(String grupoDeMails, String sistema) {
			
		String grupoDeMail = "";
		HashMap<String, String> listaGrupoDeMails = new HashMap<String, String>();
		String[] grupoDeMailsString = grupoDeMails.split(";");
		
		for (String grupoDeMailString : grupoDeMailsString) {
			String[] grupoDeMailPorEmpresa = grupoDeMailString.split("-");
			listaGrupoDeMails.put(grupoDeMailPorEmpresa[0], grupoDeMailPorEmpresa[1]);
		}
		
		if (listaGrupoDeMails.containsKey(sistema)){
			grupoDeMail = listaGrupoDeMails.get(sistema).toString();
		}
		
		return grupoDeMail;
	}
	
	/**
	 * @author U587496 Guido.Settecerze
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("resource")
	private HSSFWorkbook exportarReporteAplicacionManualPendiente(List<VistaSoporteAplicacionManualPendientes> reporteAplicacionManualPendientes) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~ Comienza la lógica de generación Excel de Aplicacion Manual Pendientes...");
			
			int rowActual = 0;
			
			ExportacionExcelDefinicionColumnas definicionColumnas = new ExportacionExcelDefinicionColumnas();
			
			HSSFWorkbook workbook = new HSSFWorkbook();//Planilla
			definicionColumnas.setEstiloCurrency(workbook.createCellStyle());
			HSSFDataFormat df = workbook.createDataFormat();
			definicionColumnas.getEstiloCurrency().setDataFormat(df.getFormat("#,##0.00"));
			
			HSSFSheet sheet;
			sheet = workbook.createSheet();
			sheet.autoSizeColumn(1, true);
		    sheet.autoSizeColumn(1);
			pintarFondoBlancoHoja(sheet);
			
			//Cargo la tabla
			try {
				workbook = this.fillTabla(workbook, sheet, reporteAplicacionManualPendientes, rowActual, definicionColumnas);
			} catch(NegocioExcepcion e){
				throw new NegocioExcepcion(e.getMessage(), e);
			}			
			
			return workbook;
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u587496 Guido.Settecerze
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @throws NegocioExcepcion
	 */
	protected HSSFWorkbook fillTabla(HSSFWorkbook workbook,
			HSSFSheet sheet, List<VistaSoporteAplicacionManualPendientes> reporteAplicacionManualPendientes, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		
		HSSFRow row17 = sheet.createRow(rowActual);
		HSSFCell row17Column1 = row17.createCell(0);
		HSSFCell row17Column2 = row17.createCell(1);
		HSSFCell row17Column3 = row17.createCell(2);
		HSSFCell row17Column4 = row17.createCell(3);
		HSSFCell row17Column5 = row17.createCell(4);
		HSSFCell row17Column6 = row17.createCell(5);
		HSSFCell row17Column7 = row17.createCell(6);
		HSSFCell row17Column8 = row17.createCell(7);
		HSSFCell row17Column9 = row17.createCell(8);
		
		List<VistaSoporteAplicacionManualPendientes> listaFinal = new ArrayList<VistaSoporteAplicacionManualPendientes>(reporteAplicacionManualPendientes);
		Iterator<VistaSoporteAplicacionManualPendientes> iterator = null;
		iterator = (Iterator<VistaSoporteAplicacionManualPendientes>) listaFinal.iterator();
		
		int rowCrear = rowActual +1;


		while(iterator.hasNext()){
			VistaSoporteAplicacionManualPendientes reporte = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell fechaEnvio = row.createCell(0);
			fechaEnvio.setCellValue(Utilidad.formatDateDDMMAAAAconBarra(reporte.getFechaCreacionTareaAprobacion()));
			HSSFCell archivoPedido = row.createCell(1);
			archivoPedido.setCellValue(reporte.getNombreArchivo());
			HSSFCell cuit = row.createCell(2);
			cuit.setCellValue(reporte.getCuit());
			HSSFCell tipoOperacion = row.createCell(3);
			tipoOperacion.setCellValue(reporte.getTipoOperacion());
			HSSFCell moneda = row.createCell(4);
			moneda.setCellValue(reporte.getMoneda());
			HSSFCell montoImpuMonedaOrigen = row.createCell(5);
			montoImpuMonedaOrigen.setCellValue(Utilidad.formatCurrency(reporte.getMontoImputarEnMonedaOrigen(), false, true));
			HSSFCell tipoCambio = row.createCell(6);
			tipoCambio.setCellValue(Validaciones.isObjectNull(reporte.getTipoDeCambio()) ? "" : Utilidad.formatCurrencyBDAgregandoDecimales(reporte.getTipoDeCambio(), 5).toString().replace(",", "|").replace(".", ",").replace("|", "."));
			HSSFCell montoImpuPesos = row.createCell(7);
			montoImpuPesos.setCellValue(Utilidad.formatCurrency(reporte.getMontoImputarEnPesos(), false, true));
			HSSFCell idTransShiva = row.createCell(8);
			idTransShiva.setCellValue(reporte.getIdTransaccionCobro());
			
			rowCrear++;
		}

		row17Column1.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_FECHAENVIO);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column1);
		row17Column2.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_ARCHIVOPEDIDO);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column2);
		row17Column3.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_CUIT);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column3);
		row17Column4.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_TIPOOPERACION);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column4);
		row17Column5.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONEDA);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column5);
		row17Column6.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONTOIMPUTARMONEDAORIGEN);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column6);
		row17Column7.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_TIPOCAMBIO);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column7);
		row17Column8.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONTOIMPUTARPESOS);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column8);
		row17Column9.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_IDTRANSSHIVA);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column9);
		
		pintarCeldasTabla(workbook, sheet, definicionColumnas);
		
		return workbook;
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
	 * @author u587496 Guido.N.Settecerze
	 * @param sheet
	 */
	public void pintarFondoBlancoHoja(HSSFSheet sheet){
		sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
	}
	
}
