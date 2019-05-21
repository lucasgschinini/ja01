package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;

import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.CobroCompensacionPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class GenerarCartaPdf extends GenerarPdf {

	public static byte[] generarCartaCompensacion(CobroCompensacionPdfCap entrada) throws NegocioExcepcion {
		Document documento = new Document(PageSize.LETTER);
		// Traza.error(TestPdfCartaBatchRunner.class,
		// "...........................................................2221");
		byte[] documentoByteArray = null;
		/* Set Fecha a Mostrar */
		// String[] dias = { "", "Domingo", "Lunes", "Martes", "Miércoles",
		// "Jueves", "Viernes", "Sábado" };
		String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" };

		DateFormatSymbols simbolos = new DateFormatSymbols(new Locale("ES"));
		// simbolos.setWeekdays(dias);
		simbolos.setMonths(meses);

		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", simbolos);
		SimpleDateFormat formateadorNota = new SimpleDateFormat("MM/yyyy");

		Date fechaDate = entrada.getFecha();
		String fechaDelDiaAMostrar = formateador.format(fechaDate);
		String nota = formateadorNota.format(fechaDate);
		String idOperacion = Utilidad.rellenarCerosIzquierda(entrada.getIdOperacion().toString(), 7);
		String idInternoSAP = Utilidad.rellenarCerosIzquierda(entrada.getIdInternoSAP(), 10);

		String pdfName = "Operación de Cobro " + idOperacion + " - K$ " + idInternoSAP + " - Carta.pdf";

		FileOutputStream baos;
		try {
			baos = new FileOutputStream(ControlArchivo.convertToFile(pdfName));
			PdfWriter.getInstance(documento, baos);

			String clienteNombre = (entrada.getCliente().getRazonSocial()).toUpperCase();
			String clienteDireccion = (entrada.getClienteDireccion()).toUpperCase();
			String clienteLocalidad = (entrada.getClienteLocalidad()).toUpperCase();
			String moneda = (entrada.getMoneda().getDescripcion()).toUpperCase();
			String monedaSimbolo = entrada.getMoneda().getSigno();
			BigDecimal importe = entrada.getImporteDeuda();

			NumerosALetras numerosALetras = new NumerosALetras();
			String importeStringConPuntos = Utilidad.formatCurrency(importe, false, true);
			String importeStringSinPuntos = Utilidad.formatCurrency(importe, false, false);

			String primerParrafo = "Nos dirigimos a ustedes para comunicarles, al solo efecto informativo que, de conformidad a lo autorizado por el Convenio de Interconexión de Redes y/o el Convenio de CPP (en adelante el Convenio) entre TELECOM ARGENTINA S.A. (en adelante Telecom) y " + clienteNombre + ", Telecom realizó la compensación entre los importes de la facturación emitida y el líquido producto resultante de la facturación del CPP que realiza Telecom por cuenta y orden de " + clienteNombre + ", con vto. en el corriente mes.";

			String segundoParrafo = "Conforme surge del detalle que se adjunta en el Anexo I, resulta que la facturación emitida por Telecom asciende a la suma de";

			String segundoParrafoMoneda = " " + moneda + " " + numerosALetras.Convertir(importeStringSinPuntos, false) + " (" + monedaSimbolo + importeStringConPuntos + "), ";

			String segundo2Parrafo = " importe que procedió a compensarse de la transferencia de fondos que Telecom debe realizar a favor de " + clienteNombre + " por el CPP originado en el Líquido Producto.";

			String tercerParrafo = "La compensación informada en esta Nota se realiza sin perjuicio de la facultad de las " + "Partes de cuestionar las facturas indicadas en el Anexo I, de conformidad con el procedimiento " + "de objeción de facturas y/o de impugnación del Líquido Producto previsto en los " + "correspondientes Convenios. En consecuencia, las Partes efectuarán los eventuales reajustes " + "que resulten de la aplicación de dichos procedimientos mediante la emisión de las notas de " + "débito o crédito que correspondan. Asimismo, la presente compensación no afecta en modo " + "alguno la facultad de presentar facturas complementarias en los términos de los Convenios.";

			String cuartoParrafo = "Saludamos a usted muy atentamente.";

			documento.open();

			crearTablaFecha(documento, fechaDelDiaAMostrar);
			crearTablaEncabezado(documento, clienteNombre, clienteDireccion, clienteLocalidad, nota);
			crearTablaCuerpoCarta(documento, primerParrafo);
			crearTablaCuerpoCarta(documento, segundoParrafo, segundoParrafoMoneda, segundo2Parrafo);
			crearTablaCuerpoCarta(documento, tercerParrafo);
			crearTablaCuerpoCarta(documento, cuartoParrafo, false);
			insertarFirma(documento, entrada.getDetalleFirma(), entrada.getUsuarioVerificador(), entrada.getUrlFirma());

			crearTablaDocumentosDeuda(documento, entrada);

			documento.close();
			baos.close();
			File pdfABorrar = ControlArchivo.convertToFile(pdfName);
			byte[] byteArray = new byte[(int) pdfABorrar.length()];
			byteArray = FileUtils.readFileToByteArray(pdfABorrar);
			pdfABorrar.delete();

			documentoByteArray = byteArray;

		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return documentoByteArray;
	}

	private static void crearTablaDocumentosDeuda(Document documento, CobroCompensacionPdfCap entrada) throws NegocioExcepcion {

		String importeStringConPuntos = Utilidad.formatCurrency(entrada.getImporteDeuda(), false, true);
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

		Font fuenteTabla = TimesRoman7;

		PdfPTable tablaTitulo = new PdfPTable(1);
		tablaTitulo.setWidthPercentage(85);

		PdfPCell titulo = new PdfPCell(new Phrase("ANEXO I", TimesRoman12Bold));
		titulo.setBorder(Rectangle.NO_BORDER);
		titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell emptycell = new PdfPCell(new Phrase("  ", TimesRoman7));
		emptycell.setBorder(Rectangle.NO_BORDER);

		tablaTitulo.addCell(titulo);
		tablaTitulo.addCell(emptycell);
		try {
			documento.add(tablaTitulo);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		PdfPTable tablaAnexo = crearTablaDocumentosCabecera();

		Integer cantidadDocumentos = 0;

		// para probar la tabla dinamica y la incercion de la firma en cada hoa
		// se comenta el foreach
		// y la creacion de todas las PdfPCell y se decomenta el siguiente for
		for (VistaSoporteConsultaDeudaPdfCap registro : entrada.getListaDeuda()) {

			//MAR
			
			PdfPCell sociedad = new PdfPCell(new Phrase ((registro.getSociedad()) == null ? "-" : registro.getSociedad().getApocope(), fuenteTabla));

			PdfPCell nroNuevo = new PdfPCell(new Phrase(registro.getCliente(), fuenteTabla));
			PdfPCell tipo = new PdfPCell(new Phrase(registro.getTipo(), fuenteTabla));
			PdfPCell razonSocial = new PdfPCell(new Phrase(registro.getRazonSocial(), fuenteTabla));
			PdfPCell nroComprobante = new PdfPCell(new Phrase(registro.getNroDeComprobante(), fuenteTabla));
			PdfPCell fechaPrimernVencimiento = new PdfPCell(new Phrase(formateadorFecha.format(registro.getFechaDateVto()), fuenteTabla));
			PdfPCell monedaSimbolo = new PdfPCell(new Phrase(registro.getMoneda().getSigno(), fuenteTabla));
			PdfPCell importe = new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getImporteOrigen(), false, true), fuenteTabla));
			PdfPCell saldo = new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getSaldo(), false, true), fuenteTabla));
			PdfPCell tipoCambio = new PdfPCell(new Phrase((registro.getTipoCambio()) == null ? "-" : registro.getTipoCambio().toString().replace(".", ","), fuenteTabla));
			PdfPCell compensacion = new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getaCompensarEnPesos(), false, true), fuenteTabla));

			// for (int i = 0; i <200; i++) {

			// PdfPCell nroNuevo = new PdfPCell(new Phrase("9032",fuenteTabla));
			// PdfPCell tipo = new PdfPCell(new Phrase("FAC",fuenteTabla));
			// PdfPCell razonSocial = new PdfPCell(new
			// Phrase("TELEFONICA MOVILES ARG. S.A.",fuenteTabla));
			// PdfPCell nroComprobante = new PdfPCell(new
			// Phrase("",fuenteTabla));
			// PdfPCell fechaPrimernVencimiento = new PdfPCell(new
			// Phrase(formateadorFecha.format(new Date()),fuenteTabla));
			// PdfPCell monedaSimbolo = new PdfPCell(new
			// Phrase("$",fuenteTabla));
			// PdfPCell importe = new PdfPCell(new
			// Phrase(Utilidad.formatCurrency(new BigDecimal(999999599.98f),
			// false, true),fuenteTabla));
			// PdfPCell saldo = new PdfPCell(new
			// Phrase(Utilidad.formatCurrency(new BigDecimal(999995999.98f),
			// false, true),fuenteTabla));
			// PdfPCell tipoCambio = new PdfPCell(new
			// Phrase(cantidadDocumentos.toString(),fuenteTabla));
			// PdfPCell compensacion= new PdfPCell(new
			// Phrase(Utilidad.formatCurrency(new BigDecimal(999999599.98f),
			// false, true),fuenteTabla));
			//
			//Mar
			sociedad.setFixedHeight(altoCeldas);
			
			nroNuevo.setFixedHeight(altoCeldas);
			tipo.setFixedHeight(altoCeldas);
			razonSocial.setFixedHeight(altoCeldas);
			nroComprobante.setFixedHeight(altoCeldas);
			fechaPrimernVencimiento.setFixedHeight(altoCeldas);
			monedaSimbolo.setFixedHeight(altoCeldas);
			importe.setFixedHeight(altoCeldas);
			saldo.setFixedHeight(altoCeldas);
			tipoCambio.setFixedHeight(altoCeldas);
			compensacion.setFixedHeight(altoCeldas);
//Ver
			sociedad.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			nroNuevo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tipo.setHorizontalAlignment(Element.ALIGN_CENTER);
			razonSocial.setHorizontalAlignment(Element.ALIGN_LEFT);
			nroComprobante.setHorizontalAlignment(Element.ALIGN_LEFT);
			fechaPrimernVencimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
			monedaSimbolo.setHorizontalAlignment(Element.ALIGN_CENTER);
			importe.setHorizontalAlignment(Element.ALIGN_RIGHT);
			saldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tipoCambio.setHorizontalAlignment(Element.ALIGN_CENTER);
			compensacion.setHorizontalAlignment(Element.ALIGN_RIGHT);

			tablaAnexo.addCell(sociedad);
			
			tablaAnexo.addCell(nroNuevo);
			tablaAnexo.addCell(tipo);
			tablaAnexo.addCell(razonSocial);
			tablaAnexo.addCell(nroComprobante);
			tablaAnexo.addCell(fechaPrimernVencimiento);
			tablaAnexo.addCell(monedaSimbolo);
			tablaAnexo.addCell(importe);
			tablaAnexo.addCell(saldo);
			tablaAnexo.addCell(tipoCambio);
			tablaAnexo.addCell(compensacion);

			cantidadDocumentos++;

			if (cantidadDocumentos % 41 == 0) {
				try {
					documento.add(tablaAnexo);
				} catch (DocumentException e) {
					throw new NegocioExcepcion(e.getMessage(), e);
				}
				insertarFirma(documento, entrada.getDetalleFirma(), entrada.getUsuarioVerificador(), entrada.getUrlFirma());
				tablaAnexo = crearTablaDocumentosCabecera();

			}
		}

		PdfPCell celdaVacia = new PdfPCell();
		celdaVacia.setColspan(10);
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		PdfPCell total = new PdfPCell(new Phrase(importeStringConPuntos, fuenteTabla));
		total.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tablaAnexo.addCell(celdaVacia);
		tablaAnexo.addCell(total);

		try {

			documento.add(tablaAnexo);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		insertarFirma(documento, entrada.getDetalleFirma(), entrada.getUsuarioVerificador(), entrada.getUrlFirma());

	}

	private static PdfPTable crearTablaDocumentosCabecera() {

		Font fuenteHeader = TimesRoman7Blanca;

		PdfPTable tablaAnexo = new PdfPTable(new float[] { 7, 6, 5, 21, 12, 9, 4, 10, 10, 9, 10 });

		tablaAnexo.setWidthPercentage(90);

		PdfPCell hSociedad = new PdfPCell(new Phrase("Sociedad", fuenteHeader));
		
		PdfPCell hNroNuevo = new PdfPCell(new Phrase("Cliente", fuenteHeader));
		PdfPCell hTipo = new PdfPCell(new Phrase("Tipo", fuenteHeader));
		PdfPCell hRazonSocial = new PdfPCell(new Phrase("Razon Social", fuenteHeader));
		PdfPCell hNroComprobante = new PdfPCell(new Phrase("Nro Comprobante", fuenteHeader));
		PdfPCell hFechaPrimernVencimiento = new PdfPCell(new Phrase("Primer Vto.", fuenteHeader));
		PdfPCell hMonedaSimbolo = new PdfPCell(new Phrase("Mon.", fuenteHeader));
		PdfPCell hImporte = new PdfPCell(new Phrase("Importe", fuenteHeader));
		PdfPCell hSaldo = new PdfPCell(new Phrase("Saldo", fuenteHeader));
		PdfPCell hTipoCambio = new PdfPCell(new Phrase("Tipo Cambio", fuenteHeader));
		PdfPCell hCompensacion = new PdfPCell(new Phrase("Compensacion", fuenteHeader));

		hSociedad.setFixedHeight(altoCeldas);
		
		hNroNuevo.setFixedHeight(altoCeldas);
		hTipo.setFixedHeight(altoCeldas);
		hRazonSocial.setFixedHeight(altoCeldas);
		hNroComprobante.setFixedHeight(altoCeldas);
		hFechaPrimernVencimiento.setFixedHeight(altoCeldas);
		hMonedaSimbolo.setFixedHeight(altoCeldas);
		hImporte.setFixedHeight(altoCeldas);
		hSaldo.setFixedHeight(altoCeldas);
		hTipoCambio.setFixedHeight(altoCeldas);
		hCompensacion.setFixedHeight(altoCeldas);

		hSociedad.setBackgroundColor(verde);
		
		hNroNuevo.setBackgroundColor(verde);
		hTipo.setBackgroundColor(verde);
		hRazonSocial.setBackgroundColor(verde);
		hNroComprobante.setBackgroundColor(verde);
		hFechaPrimernVencimiento.setBackgroundColor(verde);
		hMonedaSimbolo.setBackgroundColor(verde);
		hImporte.setBackgroundColor(verde);
		hSaldo.setBackgroundColor(verde);
		hTipoCambio.setBackgroundColor(verde);
		hCompensacion.setBackgroundColor(verde);

		tablaAnexo.addCell(hSociedad);
		
		tablaAnexo.addCell(hNroNuevo);
		tablaAnexo.addCell(hTipo);
		tablaAnexo.addCell(hRazonSocial);
		tablaAnexo.addCell(hNroComprobante);
		tablaAnexo.addCell(hFechaPrimernVencimiento);
		tablaAnexo.addCell(hMonedaSimbolo);
		tablaAnexo.addCell(hImporte);
		tablaAnexo.addCell(hSaldo);
		tablaAnexo.addCell(hTipoCambio);
		tablaAnexo.addCell(hCompensacion);

		return tablaAnexo;
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private static void crearTablaFecha(Document documento, String fechaDelDiaAMostrar) throws DocumentException {

		// addEmptyLine(documento, 0);

		PdfPTable tablaFecha = new PdfPTable(1);
		tablaFecha.setWidthPercentage(85);

		PdfPCell celdaFecha = crearCelda("Buenos Aires " + fechaDelDiaAMostrar, 0, Element.ALIGN_TOP, Element.ALIGN_RIGHT, TimesRoman12);
		celdaFecha.setBorder(Rectangle.NO_BORDER);

		tablaFecha.addCell(celdaFecha);

		documento.add(tablaFecha);

		addEmptyLine(documento, 1);
	}

	/**
	 * Método para crear espacios vacios entre tablas
	 * 
	 * @param documento
	 * @param numeroCeldasVacias
	 *            Cantidad de celdas vacias que se quieren crear antes/despues
	 * @throws DocumentException
	 */

	private static void crearTablaEncabezado(Document documento, String clienteNombre, String clienteDireccion, String clienteLocalidad, String fecha) throws DocumentException {

		PdfPTable tablaMensajeEncabezado = new PdfPTable(1);
		tablaMensajeEncabezado.setWidthPercentage(85);

		PdfPCell celdaMensajeEncabezado = crearCelda("SEÑORES", 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado2 = crearCelda(clienteNombre.toUpperCase(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado3 = crearCelda(clienteDireccion.toUpperCase(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado4 = crearCelda(clienteLocalidad.toUpperCase(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaVacia = crearCelda(" ", 0, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, Curier12BoldItalic);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezadoRef = crearCelda("Ref.: NOTA " + fecha, 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezadoRef.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezadoCliente = crearCelda("At.: A quien corresponda", 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12Bold);
		celdaMensajeEncabezadoCliente.setBorder(Rectangle.NO_BORDER);

		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado2);

		if (!clienteDireccion.trim().equalsIgnoreCase("")) {
			tablaMensajeEncabezado.addCell(celdaMensajeEncabezado3);
		}

		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado4);
		tablaMensajeEncabezado.addCell(celdaVacia);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezadoRef);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezadoCliente);
		tablaMensajeEncabezado.setSpacingAfter(8.5f);

		documento.add(tablaMensajeEncabezado);

		addEmptyLine(documento, 1);
	}

	private static void crearTablaCuerpoCarta(Document documento, String mensajeCarta) throws DocumentException {

		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		tablaMensajeCuerpo.setWidthPercentage(85);

		PdfPCell celdaMensajeCuerpo = new PdfPCell();

		Phrase frase = new Phrase(("       " + mensajeCarta), TimesRoman12);
		Paragraph parrafo = new Paragraph();
		parrafo.add(frase);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		parrafo.setLeading(14, 0);
		celdaMensajeCuerpo.addElement(parrafo);
		celdaMensajeCuerpo.setBorder(Rectangle.NO_BORDER);

		tablaMensajeCuerpo.addCell(celdaMensajeCuerpo);

		documento.add(tablaMensajeCuerpo);

		addEmptyLine(documento, 1);
	}

	private static void crearTablaCuerpoCarta(Document documento, String preImporte, String importe, String posImporte) throws DocumentException {

		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		tablaMensajeCuerpo.setWidthPercentage(85);

		PdfPCell celdaMensajeCuerpo = new PdfPCell();

		Phrase segundoParrafo = new Phrase(("       " + preImporte), TimesRoman12);
		segundoParrafo.add(new Chunk(importe, TimesRoman12Bold));
		segundoParrafo.add(new Chunk(posImporte, TimesRoman12));

		// ajusta el interlineado del parrafo
		segundoParrafo.setLeading(14, 0);

		Paragraph parrafo = new Paragraph(segundoParrafo);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);

		celdaMensajeCuerpo.addElement(parrafo);
		celdaMensajeCuerpo.setBorder(Rectangle.NO_BORDER);

		tablaMensajeCuerpo.addCell(celdaMensajeCuerpo);

		documento.add(tablaMensajeCuerpo);

		addEmptyLine(documento, 1);
	}

	private static void crearTablaCuerpoCarta(Document documento, String mensajeCarta, boolean consangria) throws DocumentException {

		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		tablaMensajeCuerpo.setWidthPercentage(85);

		PdfPCell celdaMensajeCuerpo = new PdfPCell();
		String sangria = "";
		if (consangria) {
			sangria = "       ";
		} else {
			sangria = "";
		}

		String mensaje = sangria + mensajeCarta;

		Phrase frase = new Phrase((mensaje), TimesRoman12);
		Paragraph parrafo = new Paragraph();
		parrafo.add(frase);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		parrafo.setLeading(14, 0);
		celdaMensajeCuerpo.addElement(parrafo);
		celdaMensajeCuerpo.setBorder(Rectangle.NO_BORDER);

		tablaMensajeCuerpo.addCell(celdaMensajeCuerpo);

		documento.add(tablaMensajeCuerpo);

		addEmptyLine(documento, 1);
	}

}