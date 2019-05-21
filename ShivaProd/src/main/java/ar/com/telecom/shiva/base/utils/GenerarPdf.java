/**
 * 
 */
package ar.com.telecom.shiva.base.utils;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author u586743
 *
 */
public class GenerarPdf {
	protected static final SimpleDateFormat formateadorFecha  = new SimpleDateFormat("dd/MM/yyyy");
	protected static final Font TimesRoman12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	protected static final Font Curier12BoldItalic = new Font(Font.FontFamily.COURIER,12, Font.BOLDITALIC);
	protected static final Font TimesRoman12Bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	protected static final Font TimesRoman12BoldRed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
	protected static final Font TimesRoman7Underlined = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.UNDERLINE);
	protected static final Font TimesRoman7 = new Font(Font.FontFamily.TIMES_ROMAN,7, Font.NORMAL);
	protected static final Font TimesRoman7Bold = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
	protected static final Font TimesRoman7Blanca = new Font(Font.FontFamily.TIMES_ROMAN,7, Font.NORMAL,BaseColor.WHITE);
	protected static final Font TimesRoman5 = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL);
	protected static final Font TimesRoman5Bold = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.BOLD);
	protected static final Font Arial8UnderlinedBold = new Font(Font.FontFamily.UNDEFINED, 8, Font.UNDERLINE | Font.BOLD);
	protected static final Font Arial7Bold = new Font(Font.FontFamily.UNDEFINED, 7,  Font.BOLD);
	protected static final Font Arial5Bold = new Font(Font.FontFamily.UNDEFINED, 5, Font.BOLD);
	protected static final Font Arial5 = new Font(Font.FontFamily.UNDEFINED, 5, Font.NORMAL);
	protected static final Font Arial8 = new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL);
	
	protected static Float altoCeldas = 12f ;
	protected static BaseColor verde = new BaseColor(0, 176, 80);
	protected static BaseColor amarillo = new BaseColor(255, 255, 153);
	
	
	protected static PdfPCell crearCelda(String frase, Integer borderWidth,
			Integer alineacionVertical, Integer alineacionHorizontal,
			Font fuente) {
		PdfPCell celda = new PdfPCell(new Paragraph(new Phrase(frase, fuente)));
		celda.setBorderWidth(borderWidth);
		celda.setVerticalAlignment(alineacionVertical);
		celda.setHorizontalAlignment(alineacionHorizontal);
		return celda;
	}
	
	protected static void addEmptyLine(Document documento, int numeroCeldasVacias)
			throws DocumentException {
		for (int i = 0; i < numeroCeldasVacias; i++) {

			PdfPTable tablaVacia = new PdfPTable(1);
			tablaVacia.setWidthPercentage(100);

			PdfPCell celdaVacia = crearCelda(" ", 0, Element.ALIGN_BOTTOM, Element.ALIGN_LEFT, Curier12BoldItalic);
			celdaVacia.setBorder(Rectangle.NO_BORDER);

			tablaVacia.addCell(celdaVacia);

			documento.add(tablaVacia);
		}
	}

	protected static void insertarFirma(Document documento , String entrada,String usuario, URL url) throws NegocioExcepcion {
		PdfPTable tablaMensajeCuerpo = new PdfPTable(1);
		PdfPCell celdaFirma = new PdfPCell();
		Image imagen = null ;

		tablaMensajeCuerpo.setWidthPercentage(85);

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
			frase2.add(vector[4]+" \n");
			frase2.add(vector[5]+" \n");
			frase2.add(vector[6]+" \n");
			Phrase frase3 = new Phrase(vector[7],TimesRoman7Bold);


			celdaFirma.addElement(frase1);
			celdaFirma.addElement(frase2);
			celdaFirma.addElement(frase3);

		}
		
		tablaMensajeCuerpo.addCell(celdaFirma);
		tablaMensajeCuerpo.setHorizontalAlignment(Element.ALIGN_RIGHT);


		try {

			documento.add(tablaMensajeCuerpo);
			documento.newPage();


		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}
	}
	
}
