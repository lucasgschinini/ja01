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
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
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
import ar.com.telecom.shiva.negocio.servicios.IReporteTareasPendientesAplicacionManualServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteTareasPendientesAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.IReporteTareasPendientesAplicacionManualDao;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteReporteTareasPendientesAplicacionManualFiltro;

public class ReporteTareasPendientesAplicacionManualServicioImpl implements IReporteTareasPendientesAplicacionManualServicio {

	@Autowired IReporteTareasPendientesAplicacionManualDao reporteTareasPendientesAplicacionManualDao;
	@Autowired ILdapServicio ldapServicio;
	@Autowired private MailServicio mailServicio;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private ParamRespWfTareaServicioImpl paramRespWfTareaServicioImpl;
	
	/**
	 * busca Tareas de Aplicación Manual Pendientes
	 * @throws NegocioExcepcion
	 */
	public void buscarTareasPendientesAplicacionManual(String fecha) throws NegocioExcepcion{
		
		try {
			
			Traza.auditoria(getClass(), "Comienzo del metodo generarArchivoReporteTareaPendienteAplicacionManual");
			
			String valores = parametroServicio.getValorTexto(Constantes.LISTA_SOCIEDAD_SISTEMA_TAREAS_PENDIENTES_APLICACION_MANUAL);
			String[] valoresString = valores.split(";");
			
			Long numerosDeDiasARestar = parametroServicio.getValorNumerico(Constantes.REPORTE_APLICACION_MANUAL_PENDIENTE_DIAS_A_RESTAR);
			
			String fechaFiltro = calcularFecha(fecha, numerosDeDiasARestar.intValue());
			
			//Logica para el armado de un archivo por empresa
			for(String valor : valoresString) { 
				String[] parametrosString = valor.split("\\|");
				SociedadEnum sociedad = SociedadEnum.getEnumByApocope(parametrosString[0]);
				SistemaEnum sistema = SistemaEnum.getEnumByDescripcionCorta(parametrosString[1]);
				
				//Armado de filtro
				VistaSoporteReporteTareasPendientesAplicacionManualFiltro filtro = new VistaSoporteReporteTareasPendientesAplicacionManualFiltro();
				filtro.setSistema(sistema);
				filtro.setSociedad(sociedad);
				//Busco todos los datos necesarios para armar el reporte de los cobros pendientes de confirmacion aplicacion manual.
				List<VistaSoporteTareasPendientesAplicacionManual> reporteTareasPendientesAplicacionManual = reporteTareasPendientesAplicacionManualDao.buscarTareasPendientesAplicacionManual(filtro);

				Traza.auditoria(getClass(), "~");
				Traza.auditoria(getClass(), "~ Para el sistema: " + sistema + " y la sociedad "+ sociedad +" se obtuvieron " + reporteTareasPendientesAplicacionManual.size() + " tareas pendientes de aprobacion.");
				Traza.auditoria(getClass(), "~");
				
				enviarMailReporteTareasPendientesAplicacionManualYgeneracionArchivo(reporteTareasPendientesAplicacionManual, sistema, sociedad, fechaFiltro);
				
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
	private void enviarMailReporteTareasPendientesAplicacionManualYgeneracionArchivo(List<VistaSoporteTareasPendientesAplicacionManual> reporteTareasPendientesAplicacionManual, SistemaEnum sistema, SociedadEnum sociedad, String fechaFiltro) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail con detalle de procesamiento...");
			
			// REEMPLAZAR CON EL METODO DE AGUS
			
			PerfilFiltro filtro = new PerfilFiltro();
			filtro.setSociedad(sociedad.name());
			filtro.setSistema(sistema.name());	
			filtro.setEmpresa(Constantes.ASTERISK);
			filtro.setSegmento(Constantes.ASTERISK);
			filtro.setTipoTarea(TipoTareaEnum.COB_CONF_APLIC_MANUAL.name());
			TipoPerfilEnum perfilBusqueda = paramRespWfTareaServicioImpl.buscarPerfil(filtro);
			
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
			String listaDestinatarioCorreoPorSistema = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_BATCH_TAREAS_PENDIENTES_APLICACION_MANUAL);
			
			String sociedadySistema = sociedad + "|" + sistema;
			String destinatariosCC = listaDestinatarioCorreoEnComun + ";" + obtenerGrupoDeMails(listaDestinatarioCorreoPorSistema, sociedadySistema);
			
			String asunto = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.aplicacion.manual.tareas.pendientes"), sociedad.getDescripcion(), sistema.getDescripcion());
			
			// Cuerpo de Mail + Adjuntos
			StringBuffer cuerpoMail = new StringBuffer();
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			if (Validaciones.isCollectionNotEmpty(reporteTareasPendientesAplicacionManual)) {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.manual.existe.pendientes"), fechaFiltro));
				
				ByteArrayOutputStream outputStream = null;
				
				outputStream = new ByteArrayOutputStream();
				HSSFWorkbook workbook = exportarReporteTareasPendientesAplicacionManual(reporteTareasPendientesAplicacionManual);
				workbook.write(outputStream);
				
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				
				Date fechaDelDia = null;
				try {

					fechaDelDia = formatoDelTexto.parse(fechaFiltro);

				} catch (ParseException e) {
					throw new NegocioExcepcion(e.getMessage(), e);
				}
				
				String nombreArchivo = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporteTareasPendientesAplicacionManual.nombre.archivo"), 
						sociedad.getDescripcion(),sistema.getDescripcion(), Utilidad.formatDateAAAAMMDD(fechaDelDia));
				
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
											//+ sistema.getDescripcion() 
										//	+ " (" + sistema.name() + ")"
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
	private String obtenerGrupoDeMails(String grupoDeMails, String sociedadySistema) {
			
		String grupoDeMail = "";
		HashMap<String, String> listaGrupoDeMails = new HashMap<String, String>();
		String[] grupoDeMailsString = grupoDeMails.split(";");
		
		for (String grupoDeMailString : grupoDeMailsString) {
			String[] grupoDeMailPorSociedadySistema = grupoDeMailString.split("-");
			listaGrupoDeMails.put(grupoDeMailPorSociedadySistema[0], grupoDeMailPorSociedadySistema[1]);
		}
		
		if (listaGrupoDeMails.containsKey(sociedadySistema)){
			grupoDeMail = listaGrupoDeMails.get(sociedadySistema).toString();
		}
		
		return grupoDeMail;
	}
	
	/**
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("resource")
	private HSSFWorkbook exportarReporteTareasPendientesAplicacionManual(List<VistaSoporteTareasPendientesAplicacionManual> reporteTareasPendientesAplicacionManual) throws NegocioExcepcion{
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
				workbook = this.fillTabla(workbook, sheet, reporteTareasPendientesAplicacionManual, rowActual, definicionColumnas);
			} catch(NegocioExcepcion e){
				throw new NegocioExcepcion(e.getMessage(), e);
			}			
			
			return workbook;
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @param workbook
	 * @param sheet
	 * @param cobroDto
	 * @throws NegocioExcepcion
	 */
	protected HSSFWorkbook fillTabla(HSSFWorkbook workbook,
			HSSFSheet sheet, List<VistaSoporteTareasPendientesAplicacionManual> reporteAplicacionManualPendientes, int rowActual, ExportacionExcelDefinicionColumnas definicionColumnas) throws NegocioExcepcion{
		
		HSSFRow row17 = sheet.createRow(rowActual);
		HSSFCell row17Column1 = row17.createCell(0);
		HSSFCell row17Column2 = row17.createCell(1);
		HSSFCell row17Column3 = row17.createCell(2);
		HSSFCell row17Column4 = row17.createCell(3);
		HSSFCell row17Column5 = row17.createCell(4);
		HSSFCell row17Column6 = row17.createCell(5);
		HSSFCell row17Column7 = row17.createCell(6);
		HSSFCell row17Column8 = row17.createCell(7);
		
		List<VistaSoporteTareasPendientesAplicacionManual> listaFinal = new ArrayList<VistaSoporteTareasPendientesAplicacionManual>(reporteAplicacionManualPendientes);
		Iterator<VistaSoporteTareasPendientesAplicacionManual> iterator = null;
		iterator = (Iterator<VistaSoporteTareasPendientesAplicacionManual>) listaFinal.iterator();
		
		int rowCrear = rowActual +1;


		while(iterator.hasNext()){
			VistaSoporteTareasPendientesAplicacionManual reporte = iterator.next();
			HSSFRow row = sheet.createRow(rowCrear);
			HSSFCell fechaEnvio = row.createCell(0);
			fechaEnvio.setCellValue(Utilidad.formatDateDDMMAAAAconBarra(reporte.getFechaCreacionTarea()));
			HSSFCell cuit = row.createCell(1);
			cuit.setCellValue(reporte.getCuit());
			HSSFCell tipoOperacion = row.createCell(2);
			tipoOperacion.setCellValue(reporte.getTipoOperacion());
			HSSFCell moneda = row.createCell(3);
			moneda.setCellValue(reporte.getMoneda());
			HSSFCell montoImpuMonedaOrigen = row.createCell(4);
			montoImpuMonedaOrigen.setCellValue(Utilidad.formatCurrency(reporte.getMontoImputarEnMonedaOrigen(), false, true));
			HSSFCell tipoCambio = row.createCell(5);
			tipoCambio.setCellValue(Validaciones.isObjectNull(reporte.getTipoCambio()) ? "" : Utilidad.formatCurrencyBDAgregandoDecimales(reporte.getTipoCambio(), 5).toString().replace(",", "|").replace(".", ",").replace("|", "."));
			HSSFCell montoImpuPesos = row.createCell(6);
			montoImpuPesos.setCellValue(Utilidad.formatCurrency(reporte.getMontoImputarEnPesos(), false, true));
			HSSFCell idTransShiva = row.createCell(7);
			if(reporte.getTipoOperacion().equals("COB")){
			idTransShiva.setCellValue(reporte.getNumeroTransaccionFicticio());
			}else{
				idTransShiva.setCellValue(reporte.getNumeroTransaccionFicticio());
			}
			rowCrear++;
		}

		row17Column1.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_FECHAENVIO);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column1);
		row17Column2.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_CUIT);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column2);
		row17Column3.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_TIPOOPERACION);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column3);
		row17Column4.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONEDA);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column4);
		row17Column5.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONTOIMPUTARMONEDAORIGEN);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column5);
		row17Column6.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_TIPOCAMBIO);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column6);
		row17Column7.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_MONTOIMPUTARPESOS);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column7);
		row17Column8.setCellValue(Mensajes.REPORTE_APLICACION_MANUAL_PENDIENTES_TITULO_IDTRANSSHIVA);
		definicionColumnas.getCeldasConBordesYColorDeTabla().add(row17Column8);
		
		pintarCeldasTabla(workbook, sheet, definicionColumnas);
		
		return workbook;
	}


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
	
	public void pintarFondoBlancoHoja(HSSFSheet sheet){
		sheet.setPrintGridlines(false);
        sheet.setDisplayGridlines(false);
	}

}
