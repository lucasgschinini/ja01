package ar.com.telecom.shiva.base.utils;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoValorConstRecepEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.IValorConstanciaRecepcionServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class GeneradorConstanciaRecepcionValores {

	@Autowired
	IValorConstanciaRecepcionServicio valorConstanciaRecepcionServicio;
	
	private List<ShvValValor> valorModeloLista;
	private String numeroChequeReemplazar = null;

	private String TITULO_CONSTANCIA = "Constancia de recepción de valores";
	private String NUMERO_RECIBO = "N° RECIBO:";
	private String CONSTANCIA_RECEPCION = "Se deja constancia que en la fecha hemos recepcionado del cliente N° ";
	private String EL_O_LOS_VALORES = " el o los valores:";
	private String BANCO = "Banco";
	private String NUMERO_CHEQUE = "Nro Cheque";
	private String FECHA_VENCIMIENTO = "Fecha VTO";
	private String IMPORTE = "Importe";
	private String CHEQUE_REEMPLAZADO = "Cheque Reemplazado";
	private String CONSTANCIA_ANTERIOR = "Constancia Anterior";

	private String LEYENDA_DEUDA = "Para ser imputados al pago de deudas que mantiene con TELECOM ARGENTINA S.A.";
	private String LEYENDA_RECIBOS_DE_PAGO = "Los recibos de pago seran oportunamente generados y remitidos a su domicilio, ";
	private String LEYENDA_UNA_VEZ_EFECTIVIZADO = "una vez efectivizado el cobro de dicho/s documento/s.";
	private String IMPORTE_TOTAL = "Importe Total: ";
	private String LEYENDA_POR_TELECOM_ARGENTINA = "POR TELECOM ARGENTINA S.A.";

	// Tipos de Fuentes
	private Font blackFontTitulosBoldItalic = new Font(Font.FontFamily.COURIER,	12, Font.BOLDITALIC);
	private Font blackFontBoldItalic = new Font(Font.FontFamily.COURIER, 9,	Font.BOLDITALIC);
	private Font blackFontHelvetica = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
	
	// Simbolos
	private String ESPACIO_BLANCO = " ";
	private String SIMBOLO_BARRA_FECHA = "/";

	// Ancho de bordes
	private Integer ANCHO_BORDE_CERO = 0;

	// Alineacion Horizontal
	private Integer ALINEACION_HORIZONTAL_IZQUIERDA = Element.ALIGN_LEFT;
	private Integer ALINEACION_HORIZONTAL_DERECHA = Element.ALIGN_RIGHT;

	// Alineacion Vertical
	private Integer ALINEACION_VERTICAL_ARRIBA = Element.ALIGN_TOP;
	private Integer ALINEACION_VERTICAL_MEDIO = Element.ALIGN_MIDDLE;
	private Integer ALINEACION_VERTICAL_ABAJO = Element.ALIGN_BOTTOM;
	
	//Color Shiva
	private final int RED = 12;
	private final int GREEN = 134;
	private final int BLUE = 199;

	public GeneradorConstanciaRecepcionValores(List<ShvValValor> valorModeloLista) throws FileNotFoundException, DocumentException {
		this.valorModeloLista = valorModeloLista;
	}
	
	public GeneradorConstanciaRecepcionValores(ShvValValor valorModelo) throws FileNotFoundException, DocumentException {
		this.valorModeloLista = new ArrayList<ShvValValor>();
		this.valorModeloLista.add(valorModelo);
	}

	/**
	 * Metodo para agregar datos relacionados al documento
	 * 
	 * @param documento
	 */
	private void agregarMetadata(Document documento) {
		documento.addTitle("Shiva");
		documento.addSubject("Shiva");
		documento.addKeywords("TELECOM ARGENTINA S.A.");
		documento.addAuthor("TELECOM ARGENTINA S.A.");
		documento.addCreator("TELECOM ARGENTINA S.A.");
		documento.addProducer();
	}

	/**
	 * Método que obtiene y procesa el contenido y se lo pasa a los métodos que
	 * generan las tablas
	 * 
	 * @param documento
	 *            Documento sobre el cual se dibujaran todas las tablas
	 * @throws DocumentException
	 * @throws IOException
	 */
	public Document crearArchivoConstanciaRecepcionValores(Document documento) throws DocumentException, IOException, NegocioExcepcion {

		ShvValValor valor = valorModeloLista.get(0);
		ShvValConstanciaRecepcion constanciaRecepcion = valor.getConstanciaRecepcion();
		

		agregarMetadata(documento);

		/* Empieza recorrido cabecera */
		
		/*Armado de Logo*/
		java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("logo.png"));
		java.awt.Image awtImageR = awtImage.getScaledInstance(120, 75, java.awt.Image.SCALE_SMOOTH);
		Image logo = com.itextpdf.text.Image.getInstance(awtImageR, null);

		String numeroDeRecibo = constanciaRecepcion.getIdConstanciaRecepcion().toString();
		String numeroCliente = String.valueOf(valor.getIdClienteLegado());
		String nombreCliente = valor.getRazonSocialClientePerfil();

		/*Set Fecha a Mostrar*/
		String[] dias = { "", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		
		DateFormatSymbols simbolos = new DateFormatSymbols(new Locale("ES"));
		simbolos.setWeekdays(dias);
		simbolos.setMonths(meses);

		SimpleDateFormat formateador = new SimpleDateFormat("EEEEE dd 'de' MMMMM 'de' yyyy", simbolos);
		Date fechaDate = new Date();
		String fechaDelDiaAMostrar = formateador.format(fechaDate);
		
		// Armado de PDF
		documento.open();
		addEmptyLine(documento, 3);
		crearTablaTituloLogo(documento, numeroDeRecibo, logo);
		addEmptyLine(documento, 1);
		crearTablaFecha(documento, fechaDelDiaAMostrar);
		addEmptyLine(documento, 1);
		crearTablaConstancia(documento, numeroCliente, nombreCliente);
		crearTablaTituloDatosConstancia(documento);
		ArrayList<ListaDetalleConstancia> listaDetalleConstancia = new ArrayList<ListaDetalleConstancia>();
		BigDecimal importeTotal = new BigDecimal(0.00);
		importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);

		Iterator<ShvValValor> it = valorModeloLista.iterator();
		while (it.hasNext()) {
			ShvValValor modeloDocumentable = it.next();
			/* Empieza recorrido contenido tabla */
	
			/*Set Importes*/
			BigDecimal importeIteracion = new BigDecimal(0.00);
			importeIteracion = importeIteracion.setScale(2, RoundingMode.CEILING);
			
			/*Set Cheque Reemplazado*/
			if (modeloDocumentable.getValorPadre() != null) {
				if(modeloDocumentable.getValorPadre().getValorBoletaDepositoCheque() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValorBoletaDepositoCheque().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValorBoletaDepositoChequePD() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValorBoletaDepositoChequePD().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValValorCheque() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValValorCheque().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValValorChequePD() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValValorChequePD().getNumeroCheque().toString();
				} else {
					numeroChequeReemplazar = ESPACIO_BLANCO;
				}
			} else {
				numeroChequeReemplazar = ESPACIO_BLANCO;
			}
			
			/* Set Banco, Numero Cheque y dia, mes y anio de la Fecha Vencimiento Segun Tipo Valor */
			String banco = "";
			String numeroCheque = "";
			String diaVencimiento = "";
			String mesVencimiento = "";
			String anioVencimiento = "";
			if (modeloDocumentable.getValorBoletaDepositoChequePD() != null) {
				Date fechaVencimiento = modeloDocumentable.getValorBoletaDepositoChequePD().getFechaVencimiento();
				Calendar caledarFechaVencimiento = Calendar.getInstance();
			    caledarFechaVencimiento.setTime(fechaVencimiento);
				diaVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.DAY_OF_MONTH));
				mesVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.MONTH)+1);//le sumo uno porque el nro del mes comienza en 0. Ejemplo, enero=0
				anioVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.YEAR));
						
				banco = modeloDocumentable.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion();
				numeroCheque = modeloDocumentable.getValorBoletaDepositoChequePD().getNumeroCheque().toString();
				if(modeloDocumentable.getValorBoletaDepositoChequePD().getReciboPreImpreso() != null){
					numeroDeRecibo = modeloDocumentable.getValorBoletaDepositoChequePD().getReciboPreImpreso().getNumeroRecibo();
				}
			} else if (modeloDocumentable.getValorBoletaDepositoCheque() != null) {
					banco = modeloDocumentable.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion();
					numeroCheque = modeloDocumentable.getValorBoletaDepositoCheque().getNumeroCheque().toString();
					if(modeloDocumentable.getValorBoletaDepositoCheque().getReciboPreImpreso() != null){
						numeroDeRecibo = modeloDocumentable.getValorBoletaDepositoCheque().getReciboPreImpreso().getNumeroRecibo();
					}
			} else if (modeloDocumentable.getValorBoletaEfectivo() != null) {
					banco = ESPACIO_BLANCO;
					numeroCheque = ESPACIO_BLANCO;
					if(modeloDocumentable.getValorBoletaEfectivo().getReciboPreImpreso() != null){
						numeroDeRecibo = modeloDocumentable.getValorBoletaEfectivo().getReciboPreImpreso().getNumeroRecibo();
					}
			}
			
			String idConstanciaAnterior = "";
//			ShvValConstanciaRecepcionValor constanciaRecepcion = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValor(modeloDocumentable.getIdValor());
//			if(constanciaRecepcion != null){
				ShvValConstanciaRecepcion idConstanciaRecepcionPadre = constanciaRecepcion.getIdConstanciaRecepcionPadre();
				if (idConstanciaRecepcionPadre != null) {
					if (EstadoValorConstRecepEnum.ANULADA.equals(idConstanciaRecepcionPadre.getEstado())) {
						idConstanciaAnterior = String.valueOf(idConstanciaRecepcionPadre.getIdConstanciaRecepcion());
					}
				}
//			}
	
			ListaDetalleConstancia listaDetalleConstanciaItera = new ListaDetalleConstancia();
			listaDetalleConstanciaItera.setBanco(banco);
			listaDetalleConstanciaItera.setChequeNumero(numeroCheque);
			listaDetalleConstanciaItera.setDiaVencimiento(diaVencimiento);
			listaDetalleConstanciaItera.setMesVencimiento(mesVencimiento);
			listaDetalleConstanciaItera.setAnioVencimiento(anioVencimiento);
			importeIteracion = modeloDocumentable.getImporte();
			importeTotal = importeTotal.add(importeIteracion);
			listaDetalleConstanciaItera.setImporte(modeloDocumentable.getImporte());
			listaDetalleConstanciaItera.setNumeroChequeReemplazado(numeroChequeReemplazar);
			listaDetalleConstanciaItera.setConstanciaAnterior(idConstanciaAnterior);
			listaDetalleConstancia.add(listaDetalleConstanciaItera);
		}
		// Fin relleno contenido tabla 

		crearTablaDatosConstancia(documento, listaDetalleConstancia);
		addEmptyLine(documento, 1);
		crearTablaImputacion(documento);
		crearTablaImporteTotal(documento, importeTotal);
		crearTablaPorTelecomArgentina(documento);

		return documento;
	}
	
	/**
	 * Método que obtiene y procesa el contenido y se lo pasa a los métodos que
	 * generan las tablas
	 * 
	 * @param documento
	 *            Documento sobre el cual se dibujaran todas las tablas
	 * @throws DocumentException
	 * @throws IOException
	 */
	public Document crearArchivoConstanciaRecepcionValores(Document documento, List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor)
			throws DocumentException, IOException, NegocioExcepcion {

		ShvValValor modeloCabecera = valorModeloLista.get(0);

		agregarMetadata(documento);

		/* Empieza recorrido cabecera */
		
		/*Armado de Logo*/
		java.awt.Image awtImage = Toolkit
				.getDefaultToolkit()
				.createImage(getClass().getClassLoader().getResource("logo.png"));
		java.awt.Image awtImageR = awtImage.getScaledInstance(120, 75, java.awt.Image.SCALE_SMOOTH);
		Image logo = com.itextpdf.text.Image.getInstance(awtImageR, null);

		ShvValConstanciaRecepcionValor constancia = constanciaRecepcionValor.get(0);
		String numeroDeRecibo = " ";
		if (!Validaciones.isObjectNull(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion())) {
			numeroDeRecibo += constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion().toString();
		}
		String numeroCliente = String.valueOf(modeloCabecera.getIdClienteLegado());
		String nombreCliente = modeloCabecera.getRazonSocialClientePerfil();

		/*Set Fecha a Mostrar*/
		String[] dias = { "", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
		String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		
		DateFormatSymbols simbolos = new DateFormatSymbols(new Locale("ES"));
		simbolos.setWeekdays(dias);
		simbolos.setMonths(meses);

		SimpleDateFormat formateador = new SimpleDateFormat("EEEEE dd 'de' MMMMM 'de' yyyy", simbolos);
		Date fechaDate = new Date();
		String fechaDelDiaAMostrar = formateador.format(fechaDate);
		
		// Armado de PDF
		documento.open();
		addEmptyLine(documento, 3);
		crearTablaTituloLogo(documento, numeroDeRecibo, logo);
		addEmptyLine(documento, 1);
		crearTablaFecha(documento, fechaDelDiaAMostrar);
		addEmptyLine(documento, 1);
		crearTablaConstancia(documento, numeroCliente, nombreCliente);
		crearTablaTituloDatosConstancia(documento);
		ArrayList<ListaDetalleConstancia> listaDetalleConstancia = new ArrayList<ListaDetalleConstancia>();
		BigDecimal importeTotal = new BigDecimal(0.00);
		importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);

		Iterator<ShvValValor> it = valorModeloLista.iterator();
		while (it.hasNext()) {
			ShvValValor modeloDocumentable = it.next();
			/* Empieza recorrido contenido tabla */
	
			/*Set Importes*/
			BigDecimal importeIteracion = new BigDecimal(0.00);
			importeIteracion = importeIteracion.setScale(2, RoundingMode.CEILING);
			
			/*Set Cheque Reemplazado*/
			if (modeloDocumentable.getValorPadre() != null) {
				if(modeloDocumentable.getValorPadre().getValorBoletaDepositoCheque() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValorBoletaDepositoCheque().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValorBoletaDepositoChequePD() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValorBoletaDepositoChequePD().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValValorCheque() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValValorCheque().getNumeroCheque().toString();
				} else if(modeloDocumentable.getValorPadre().getValValorChequePD() != null){
					numeroChequeReemplazar = modeloDocumentable.getValorPadre().getValValorChequePD().getNumeroCheque().toString();
				} else {
					numeroChequeReemplazar = ESPACIO_BLANCO;
				}
			} else {
				numeroChequeReemplazar = ESPACIO_BLANCO;
			}
			
			/* Set Banco, Numero Cheque y dia, mes y anio de la Fecha Vencimiento Segun Tipo Valor */
			String banco = "";
			String numeroCheque = "";
			String diaVencimiento = "";
			String mesVencimiento = "";
			String anioVencimiento = "";
			if (modeloDocumentable.getValorBoletaDepositoChequePD() != null) {
				Date fechaVencimiento = modeloDocumentable.getValorBoletaDepositoChequePD().getFechaVencimiento();
				Calendar caledarFechaVencimiento = Calendar.getInstance();
			    caledarFechaVencimiento.setTime(fechaVencimiento);
				diaVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.DAY_OF_MONTH));
				mesVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.MONTH)+1);//le sumo uno porque el nro del mes comienza en 0. Ejemplo, enero=0
				anioVencimiento = String.valueOf(caledarFechaVencimiento.get(Calendar.YEAR));
						
				banco = modeloDocumentable.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion();
				numeroCheque = modeloDocumentable.getValorBoletaDepositoChequePD().getNumeroCheque().toString();
				if(modeloDocumentable.getValorBoletaDepositoChequePD().getReciboPreImpreso() != null){
					numeroDeRecibo = modeloDocumentable.getValorBoletaDepositoChequePD().getReciboPreImpreso().getNumeroRecibo();
				}
			} else if (modeloDocumentable.getValorBoletaDepositoCheque() != null) {
					banco = modeloDocumentable.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion();
					numeroCheque = modeloDocumentable.getValorBoletaDepositoCheque().getNumeroCheque().toString();
					if(modeloDocumentable.getValorBoletaDepositoCheque().getReciboPreImpreso() != null){
						numeroDeRecibo = modeloDocumentable.getValorBoletaDepositoCheque().getReciboPreImpreso().getNumeroRecibo();
					}
			} else if (modeloDocumentable.getValorBoletaEfectivo() != null) {
					banco = ESPACIO_BLANCO;
					numeroCheque = ESPACIO_BLANCO;
					if(modeloDocumentable.getValorBoletaEfectivo().getReciboPreImpreso() != null){
						numeroDeRecibo = modeloDocumentable.getValorBoletaEfectivo().getReciboPreImpreso().getNumeroRecibo();
					}
			}
			
			String idConstanciaAnterior = "";
			if(constancia != null){
				ShvValConstanciaRecepcion idConstanciaRecepcionPadre = constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcionPadre();
				if (idConstanciaRecepcionPadre != null) {
					if (EstadoValorConstRecepEnum.ANULADA.equals(idConstanciaRecepcionPadre.getEstado())) {
						idConstanciaAnterior = String.valueOf(idConstanciaRecepcionPadre.getIdConstanciaRecepcion());
					}
				}
			}
	
			ListaDetalleConstancia listaDetalleConstanciaItera = new ListaDetalleConstancia();
			listaDetalleConstanciaItera.setBanco(banco);
			listaDetalleConstanciaItera.setChequeNumero(numeroCheque);
			listaDetalleConstanciaItera.setDiaVencimiento(diaVencimiento);
			listaDetalleConstanciaItera.setMesVencimiento(mesVencimiento);
			listaDetalleConstanciaItera.setAnioVencimiento(anioVencimiento);
			importeIteracion = modeloDocumentable.getImporte();
			importeTotal = importeTotal.add(importeIteracion);
			listaDetalleConstanciaItera.setImporte(modeloDocumentable.getImporte());
			listaDetalleConstanciaItera.setNumeroChequeReemplazado(numeroChequeReemplazar);
			listaDetalleConstanciaItera.setConstanciaAnterior(idConstanciaAnterior);
			listaDetalleConstancia.add(listaDetalleConstanciaItera);
		}
		// Fin relleno contenido tabla 

		crearTablaDatosConstancia(documento, listaDetalleConstancia);
		addEmptyLine(documento, 1);
		crearTablaImputacion(documento);
		crearTablaImporteTotal(documento, importeTotal);
		crearTablaPorTelecomArgentina(documento);

		return documento;
	}


	/**
	 * Método que genera la tabla correspondiente a la franja de Cobranza y logo
	 * del banco (No cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param logo
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTituloLogo(Document documento, String numeroRecibo,
			Image logo) throws DocumentException, IOException {
		float[] medidaCeldas = { .80f, .03f, .47f };
		PdfPTable tablaGeneralTituloLogo = new PdfPTable(medidaCeldas);
		tablaGeneralTituloLogo.setWidthPercentage(80);

		PdfPTable tablaTituloRecibo = new PdfPTable(1);

		PdfPCell celdaTitulo = crearCelda(TITULO_CONSTANCIA, ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_ABAJO, ALINEACION_HORIZONTAL_IZQUIERDA,
				blackFontTitulosBoldItalic);
		celdaTitulo.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaNumeroRecibo = crearCelda(NUMERO_RECIBO + numeroRecibo,
				ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO,
				ALINEACION_HORIZONTAL_IZQUIERDA, blackFontBoldItalic);
		celdaNumeroRecibo.setBorder(Rectangle.NO_BORDER);

		tablaTituloRecibo.addCell(celdaTitulo);
		tablaTituloRecibo.addCell(celdaNumeroRecibo);

		PdfPCell celdaTituloRecibo = new PdfPCell(tablaTituloRecibo);
		celdaTituloRecibo.setBorderWidth(ANCHO_BORDE_CERO);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaLogo = new PdfPCell(logo);
		celdaLogo.setBorderWidth(ANCHO_BORDE_CERO);
		celdaLogo.setVerticalAlignment(ALINEACION_VERTICAL_ARRIBA);
		celdaLogo.setHorizontalAlignment(ALINEACION_HORIZONTAL_DERECHA);

		tablaGeneralTituloLogo.addCell(celdaTituloRecibo);
		tablaGeneralTituloLogo.addCell(celdaMedio);
		tablaGeneralTituloLogo.addCell(celdaLogo);
		tablaGeneralTituloLogo.setSpacingBefore(1f);
		tablaGeneralTituloLogo.setSpacingAfter(2f);

		documento.add(tablaGeneralTituloLogo);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaFecha(Document documento, String fechaDelDiaAMostrar)
			throws DocumentException {

		PdfPTable tablaFecha = new PdfPTable(1);
		tablaFecha.setWidthPercentage(80);

		PdfPCell celdaFecha = crearCelda(
				"Buenos Aires, " + fechaDelDiaAMostrar, ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_DERECHA,
				blackFontBoldItalic);
		celdaFecha.setBorder(Rectangle.NO_BORDER);

		tablaFecha.addCell(celdaFecha);

		documento.add(tablaFecha);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaConstancia(Document documento, String numeroCliente,
			String nombreCliente) throws DocumentException {

		PdfPTable tablaMensajeConstancia = new PdfPTable(1);
		tablaMensajeConstancia.setWidthPercentage(83);

		PdfPCell celdaMensajeConstancia = crearCelda(CONSTANCIA_RECEPCION,
				ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO,
				ALINEACION_HORIZONTAL_IZQUIERDA, blackFontBoldItalic);
		celdaMensajeConstancia.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeConstanciaCliente = crearCelda(numeroCliente
				+ ESPACIO_BLANCO + nombreCliente + EL_O_LOS_VALORES,
				ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO,
				ALINEACION_HORIZONTAL_IZQUIERDA, blackFontBoldItalic);
		celdaMensajeConstanciaCliente.setBorder(Rectangle.NO_BORDER);

		tablaMensajeConstancia.addCell(celdaMensajeConstancia);
		tablaMensajeConstancia.addCell(celdaMensajeConstanciaCliente);
		tablaMensajeConstancia.setSpacingAfter(8f);

		documento.add(tablaMensajeConstancia);
	}

	/**
	 * Metodo que crea la tabla con el titulo de Datos del Depositante
	 * 
	 * @param documento
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTituloDatosConstancia(Document documento)
			throws DocumentException, IOException {

		PdfPTable tablaDetallesConstancia = new PdfPTable(6);
		tablaDetallesConstancia.setWidthPercentage(90);

		PdfPCell celdaBanco = new PdfPCell(new Phrase(BANCO, blackFontBoldItalic));
		celdaBanco.setBorderWidth(ANCHO_BORDE_CERO);
		celdaBanco.setVerticalAlignment(ALINEACION_VERTICAL_ARRIBA);
		celdaBanco.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaBanco.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaBanco);

		PdfPCell celdaNumeroCheque = new PdfPCell(new Phrase(NUMERO_CHEQUE, blackFontBoldItalic));
		celdaNumeroCheque.setBorderWidth(ANCHO_BORDE_CERO);
		celdaNumeroCheque.setVerticalAlignment(ALINEACION_VERTICAL_ARRIBA);
		celdaNumeroCheque.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaNumeroCheque.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaNumeroCheque);

		PdfPCell celdaFechaVencimiento = new PdfPCell(new Phrase(FECHA_VENCIMIENTO, blackFontBoldItalic));
		celdaFechaVencimiento.setBorderWidth(ANCHO_BORDE_CERO);
		celdaFechaVencimiento.setVerticalAlignment(ALINEACION_VERTICAL_ARRIBA);
		celdaFechaVencimiento.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaFechaVencimiento.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaFechaVencimiento);

		PdfPCell celdaImporte = new PdfPCell(new Phrase(IMPORTE, blackFontBoldItalic));
		celdaImporte.setBorderWidth(ANCHO_BORDE_CERO);
		celdaImporte.setVerticalAlignment(ALINEACION_VERTICAL_ARRIBA);
		celdaImporte.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaImporte.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaImporte);

		PdfPCell celdaChequeReemplazado = new PdfPCell(new Phrase(CHEQUE_REEMPLAZADO, blackFontBoldItalic));
		celdaChequeReemplazado.setBorderWidth(ANCHO_BORDE_CERO);
		celdaChequeReemplazado.setVerticalAlignment(ALINEACION_VERTICAL_MEDIO);
		celdaChequeReemplazado.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaChequeReemplazado.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaChequeReemplazado);

		PdfPCell celdaConstanciaAnterior = new PdfPCell(new Phrase(CONSTANCIA_ANTERIOR, blackFontBoldItalic));
		celdaConstanciaAnterior.setBorderWidth(ANCHO_BORDE_CERO);
		celdaConstanciaAnterior.setVerticalAlignment(ALINEACION_VERTICAL_MEDIO);
		celdaConstanciaAnterior.setHorizontalAlignment(ALINEACION_HORIZONTAL_IZQUIERDA);
		celdaConstanciaAnterior.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaDetallesConstancia.addCell(celdaConstanciaAnterior);

		//tablaDetallesConstancia.addCell(crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontBoldItalic));

		documento.add(tablaDetallesConstancia);
	}

	/**
	 * Método encargado de crear las tablas para llenar con los datos de
	 * Detalles del Pago y del Deposito (NO Cheque de Pago Diferido)
	 * 
	 * @param documento
	 * @param listaDetalleConstancia
	 * @param listaDetalleDeposito
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaDatosConstancia(Document documento,
			ArrayList<ListaDetalleConstancia> listaDetalleConstancia)
			throws DocumentException, IOException {

		PdfPTable tablaDetallesConstancia = new PdfPTable(6);
		tablaDetallesConstancia.setWidthPercentage(90);

		for (ListaDetalleConstancia detalleConstancia : listaDetalleConstancia) {
			tablaDetallesConstancia.addCell(crearCelda(detalleConstancia.getBanco(), ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));
			tablaDetallesConstancia.addCell(crearCelda(detalleConstancia.getChequeNumero(), ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));
			if (!Validaciones.isNullOrEmpty(detalleConstancia
					.getDiaVencimiento())
					&& !Validaciones.isNullOrEmpty(detalleConstancia
							.getMesVencimiento())
					&& !Validaciones.isNullOrEmpty(detalleConstancia
							.getAnioVencimiento())) {
				tablaDetallesConstancia.addCell(crearCelda(detalleConstancia.getDiaVencimiento() + SIMBOLO_BARRA_FECHA + detalleConstancia.getMesVencimiento() + SIMBOLO_BARRA_FECHA
						+ detalleConstancia.getAnioVencimiento(), ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));				
			} else {
				tablaDetallesConstancia.addCell(crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));				
			}
			String importe = Utilidad.formatCurrency(detalleConstancia.getImporte(), 2);
			tablaDetallesConstancia.addCell(crearCelda(importe, ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_DERECHA, blackFontHelvetica));
			tablaDetallesConstancia.addCell(crearCelda(detalleConstancia.getNumeroChequeReemplazado().toString(), ANCHO_BORDE_CERO,	ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));
			tablaDetallesConstancia.addCell(crearCelda(detalleConstancia.getConstanciaAnterior(), ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA, blackFontHelvetica));
		}

		documento.add(tablaDetallesConstancia);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaImputacion(Document documento)
			throws DocumentException {

		PdfPTable tablaMensajeConstancia = new PdfPTable(1);
		tablaMensajeConstancia.setWidthPercentage(87);

		PdfPCell celdaMensajeDeuda = crearCelda(LEYENDA_DEUDA,
				ANCHO_BORDE_CERO, ALINEACION_VERTICAL_MEDIO,
				ALINEACION_HORIZONTAL_IZQUIERDA, blackFontBoldItalic);
		celdaMensajeDeuda.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeRecibosDePago = crearCelda(
				LEYENDA_RECIBOS_DE_PAGO, ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA,
				blackFontBoldItalic);
		celdaMensajeRecibosDePago.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeUnaVezEfectivizado = crearCelda(
				LEYENDA_UNA_VEZ_EFECTIVIZADO, ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA,
				blackFontBoldItalic);
		celdaMensajeUnaVezEfectivizado.setBorder(Rectangle.NO_BORDER);

		tablaMensajeConstancia.addCell(celdaMensajeDeuda);
		tablaMensajeConstancia.addCell(celdaMensajeRecibosDePago);
		tablaMensajeConstancia.addCell(celdaMensajeUnaVezEfectivizado);
		tablaMensajeConstancia.setSpacingAfter(5f);

		documento.add(tablaMensajeConstancia);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaImporteTotal(Document documento,
			BigDecimal importeTotal) throws DocumentException {

		PdfPTable tablaImporteTotal = new PdfPTable(1);
		tablaImporteTotal.setWidthPercentage(87);
		NumerosALetras numerosALetras = new NumerosALetras();

		String importeTotalFormato = Utilidad.formatCurrency(importeTotal, 2);
		PdfPCell celdaImporteTotal = crearCelda(
				IMPORTE_TOTAL
						+ importeTotalFormato
						+ " Pesos "
						+ numerosALetras.Convertir(importeTotal.toString(),
								false) + "- .", ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA,
				blackFontBoldItalic);
		celdaImporteTotal.setBorder(Rectangle.NO_BORDER);

		tablaImporteTotal.addCell(celdaImporteTotal);
		tablaImporteTotal.setSpacingAfter(5f);

		documento.add(tablaImporteTotal);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaPorTelecomArgentina(Document documento)
			throws DocumentException {

		PdfPTable tablaPorTelecomArgentina = new PdfPTable(1);
		tablaPorTelecomArgentina.setWidthPercentage(87);

		PdfPCell celdaPorTelecomArgentina = crearCelda(
				LEYENDA_POR_TELECOM_ARGENTINA, ANCHO_BORDE_CERO,
				ALINEACION_VERTICAL_MEDIO, ALINEACION_HORIZONTAL_IZQUIERDA,
				blackFontTitulosBoldItalic);
		celdaPorTelecomArgentina.setBorder(Rectangle.NO_BORDER);

		tablaPorTelecomArgentina.addCell(celdaPorTelecomArgentina);

		documento.add(tablaPorTelecomArgentina);
	}

	/**
	 * Metodo general de creación de celdas
	 * 
	 * @param frase
	 * @param borderWidth
	 *            Ancho del borde
	 * @param alineacionVertical
	 *            Tipo de alineación vertical dentro de la celda
	 * @param alineacionHorizontal
	 *            Tipo de alineación horizontal dentro de la celda
	 * @param fuente
	 *            Tipo de fuente a utilizar en la celda
	 * @return
	 */
	private PdfPCell crearCelda(String frase, Integer borderWidth,
			Integer alineacionVertical, Integer alineacionHorizontal,
			Font fuente) {
		PdfPCell celda = new PdfPCell(new Phrase(frase, fuente));
		celda.setBorderWidth(borderWidth);
		celda.setVerticalAlignment(alineacionVertical);
		celda.setHorizontalAlignment(alineacionHorizontal);
		return celda;
	}

	/**
	 * Método para crear espacios vacios entre tablas
	 * 
	 * @param documento
	 * @param numeroCeldasVacias
	 *            Cantidad de celdas vacias que se quieren crear antes/despues
	 * @throws DocumentException
	 */
	private void addEmptyLine(Document documento, int numeroCeldasVacias)
			throws DocumentException {
		for (int i = 0; i < numeroCeldasVacias; i++) {

			PdfPTable tablaVacia = new PdfPTable(1);
			tablaVacia.setWidthPercentage(100);

			PdfPCell celdaVacia = crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_CERO,
					ALINEACION_VERTICAL_ABAJO, ALINEACION_HORIZONTAL_IZQUIERDA,
					blackFontTitulosBoldItalic);
			celdaVacia.setBorder(Rectangle.NO_BORDER);

			tablaVacia.addCell(celdaVacia);

			documento.add(tablaVacia);
		}

	}

	/**
	 * @return the valorModeloLista
	 */
	public List<ShvValValor> getValorModeloLista() {
		return valorModeloLista;
	}

	/**
	 * @param valorModeloLista the valorModeloLista to set
	 */
	public void setValorModeloLista(List<ShvValValor> valorModeloLista) {
		this.valorModeloLista = valorModeloLista;
	}
}
