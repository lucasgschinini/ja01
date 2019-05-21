package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ClienteDireccionVo;
import ar.com.telecom.shiva.negocio.bean.LegajoChequeRechazadoNotificacionPdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class GenerarCartaLegajoPdf  extends GenerarPdf{
	
	

	public static byte []  generarCartaLegajo(LegajoChequeRechazadoNotificacionPdf entrada) throws NegocioExcepcion {
		Document documento = new Document(PageSize.LETTER);
		byte []  documentoByteArray = null;
		/*Set Fecha a Mostrar*/
		String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" };
		
		DateFormatSymbols simbolos = new DateFormatSymbols(new Locale("ES"));
		simbolos.setMonths(meses);

		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", simbolos);
		
		Date fechaDate = entrada.getFechaCreacionCarta();
		String fechaDelDiaAMostrar = formateador.format(fechaDate);
		String numCheque = entrada.getNumCheque();
		String idLegajo = entrada.getIdLegajoChequeRechazado().toString();
	
		String pdfName = "Legajo " + idLegajo + " - Cheque " + numCheque + " - Carta.pdf";

		FileOutputStream baos ;
		try {
			baos = new FileOutputStream(ControlArchivo.convertToFile(pdfName));
			PdfWriter.getInstance(documento, baos);
			
			String bancoOrigen = entrada.getBancoOrigen();
			String fechaEmision = Utilidad.formatDatePicker(entrada.getFechaEmision());
			String importeCheque = entrada.getImporteCheque();
			String motivoRechazo = entrada.getMotivoRechazo();
			String fechaReembolso = Utilidad.formatDatePicker(entrada.getFechaReembolso());	
			
			String primerParrafo = "Nro Linea: " + entrada.getNroLinea();
			
			String segundoParrafo = "Estimado Cliente:";

			String tercerParrafo = "Nos dirijimos a Ud. a los efectos de proceder a la entrega del cheque Nro. " + numCheque + ", del " +
									bancoOrigen + ", con fecha de emisión " + fechaEmision + ", importe " + importeCheque + ", rechazado por motivo " +
									motivoRechazo + ", debido a que el mismo ha sido reembolsado en su totalidad, el" +
									" día " + fechaReembolso;

			String cuartoParrafo = "Sin otro particular, aprovechamos para saludarlo atentamente.";

			documento.open();
			
			crearTablaTitulo(documento);
			crearTablaFecha(documento, fechaDelDiaAMostrar);
			crearTablaEncabezado(documento, entrada.getCliente());
			crearTablaCuerpoCarta(documento, primerParrafo);
			addEmptyLine(documento, 1);
			crearTablaCuerpoCarta(documento, segundoParrafo);
			crearTablaCuerpoCarta(documento, tercerParrafo);
			addEmptyLine(documento, 1);
			crearTablaCuerpoCarta(documento, cuartoParrafo);
			insertarFirma(documento , entrada.getDetalleFirma() , entrada.getAnalistaFirmante(), entrada.getUrlFirma());

			documento.close();
			baos.close();
			File pdfABorrar = ControlArchivo.convertToFile(pdfName);
			byte[] byteArray = new byte[(int) pdfABorrar.length()];
		    byteArray = FileUtils.readFileToByteArray(pdfABorrar);
			pdfABorrar.delete();
			
			
			documentoByteArray = byteArray;
			


		} catch (IOException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}

		return documentoByteArray;
	}	

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private static void crearTablaFecha(Document documento, String fechaDelDiaAMostrar) throws DocumentException {

		PdfPTable tablaFecha = new PdfPTable(1);
		tablaFecha.setWidthPercentage(85);

		PdfPCell celdaFecha = crearCelda("Buenos Aires " + fechaDelDiaAMostrar, 0, Element.ALIGN_TOP, Element.ALIGN_RIGHT,TimesRoman12);
		celdaFecha.setBorder(Rectangle.NO_BORDER);

		tablaFecha.addCell(celdaFecha);

		documento.add(tablaFecha);

		addEmptyLine(documento, 1);
	}
	
	private static void crearTablaTitulo(Document documento) throws DocumentException {
		PdfPTable tablaMensajeTitulo = new PdfPTable(1);
		tablaMensajeTitulo.setWidthPercentage(85);

		PdfPCell celdaMensajeTitulo = crearCelda("NOTIFICACION DEVOLUCION CHEQUE", 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12BoldRed);
		celdaMensajeTitulo.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeTitulo2 = crearCelda("RECHAZADO", 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12BoldRed);
		celdaMensajeTitulo2.setBorder(Rectangle.NO_BORDER);
		
		tablaMensajeTitulo.addCell(celdaMensajeTitulo);
		tablaMensajeTitulo.addCell(celdaMensajeTitulo2);
		documento.add(tablaMensajeTitulo);		
	}	


	private static void crearTablaEncabezado(Document documento, ClienteDireccionVo cliente) throws DocumentException {

		PdfPTable tablaMensajeEncabezado = new PdfPTable(1);
		tablaMensajeEncabezado.setWidthPercentage(85);
		
		PdfPCell celdaMensajeEncabezado = crearCelda("Sr/a: ", 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);
		celdaMensajeEncabezado.setPaddingBottom(5);

		PdfPCell celdaMensajeEncabezado2 = crearCelda( cliente.getRazonSocial().toUpperCase(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado3 = crearCelda("Calle: " + cliente.getNombreCalle() + " " + cliente.getAltura() + "    Piso: " + cliente.getPiso() + "    Depto: " + cliente.getPuerta(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado4 = crearCelda("Código Postal: " + cliente.getCodigoPostal() + "    Localidad: " + cliente.getDescLocalidad(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeEncabezado5 = crearCelda("Provincia: " + cliente.getDescProvincia(), 0, Element.ALIGN_MIDDLE, Element.ALIGN_LEFT, TimesRoman12);
		celdaMensajeEncabezado.setBorder(Rectangle.NO_BORDER);
		celdaMensajeEncabezado.setPaddingBottom(20);


		
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado2);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado3);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado4);
		tablaMensajeEncabezado.addCell(celdaMensajeEncabezado5);
		tablaMensajeEncabezado.setSpacingAfter(8.5f);

		documento.add(tablaMensajeEncabezado);

		addEmptyLine(documento, 1);
	}

	private static void crearTablaCuerpoCarta(Document documento,String mensajeCarta) throws DocumentException {

		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		tablaMensajeCuerpo.setWidthPercentage(85);

		PdfPCell celdaMensajeCuerpo = new PdfPCell();

		Phrase frase = new Phrase((mensajeCarta), TimesRoman12);
		Paragraph parrafo = new Paragraph();
		parrafo.add(frase);
		parrafo.setAlignment(Element.ALIGN_JUSTIFIED);
		parrafo.setLeading(14,0);
		celdaMensajeCuerpo.addElement(parrafo);
		celdaMensajeCuerpo.setBorder(Rectangle.NO_BORDER);


		tablaMensajeCuerpo.addCell(celdaMensajeCuerpo);

		documento.add(tablaMensajeCuerpo);

		addEmptyLine(documento, 1);
	}
	
	public static void insertarFirma(Document documento , String entrada,String usuario, URL url) throws NegocioExcepcion{
		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		PdfPCell celdaFirma = new PdfPCell();
		Image imagen = null ;

		tablaMensajeCuerpo.setWidthPercentage(30);

		if (Validaciones.isObjectNull(url)) {
			Traza.advertencia(GenerarPdf.class, "No existe la URL de la firma del usuario " + usuario);
			String noFirma = "No existe la imagen de la firma del usuario " + (!Validaciones.isNullEmptyOrDash(usuario) ? usuario : "" ) + "\n\n";
			Phrase frase2 = new Phrase(noFirma,TimesRoman7);
			celdaFirma.addElement(frase2);

		} else {
			try {
				imagen = Image.getInstance(url);
				imagen.scaleAbsoluteWidth(120f);
				celdaFirma.addElement(imagen);
			} catch (BadElementException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			} catch (MalformedURLException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			} catch (IOException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			}
			
		}

		celdaFirma.setBorder(Rectangle.NO_BORDER);

		if (Validaciones.isNullEmptyOrDash(entrada)) {
			String noFirma = "No existe el detalle de la  firma del usuario " + (!Validaciones.isNullEmptyOrDash(usuario) ? usuario : "" );

			Traza.advertencia(GenerarPdf.class, noFirma);
			Phrase frase2 = new Phrase(noFirma,TimesRoman7);
			celdaFirma.addElement(frase2);

		} else {
			String[] vector = entrada.split(";");

			Phrase frase1 = new Phrase(vector[0],TimesRoman7Bold);
			Phrase frase2 = new Phrase("",TimesRoman7);
			frase2.add(vector[1]+" \n");
			frase2.add(vector[2]+" \n");
			frase2.add(vector[3]+" \n");

			celdaFirma.addElement(frase1);
			celdaFirma.addElement(frase2);

		}
		
		tablaMensajeCuerpo.addCell(celdaFirma);
		tablaMensajeCuerpo.setHorizontalAlignment(Element.ALIGN_RIGHT);

		try {

			documento.add(tablaMensajeCuerpo);

		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}
	
	}
}