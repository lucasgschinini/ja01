package ar.com.telecom.shiva.base.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.presentacion.bean.dto.LineaRegistroDto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneradorArchivoPdf extends GeneradorComprobantePago {

	private Font blackFontSubTitulo = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
	private Font blackFontTitulosCabeceraTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	private Font blackFontTablaOperaciones = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
	private Font blackFontFinDeReporte = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

	private PdfWriter pw;
	private Date fecha=  Calendar.getInstance().getTime();

	// Tabla Cabecera
	private String ESTADO_REV_OPERACIONES = "Estado de reversión de la Operación";
	private String ID_OPERACIONES_SHIVA = "ID de Operación Shiva";

	// Mensaje
	
	private String REPORTE_OPERACIONES_TRUNCAS = "Reporte Operaciones Truncas";

	// Nombre archivo

	
	public GeneradorArchivoPdf() {
	}

	public ByteArrayOutputStream GeneraArchivoPdf(List<LineaRegistroDto> registros, 
			String nombreArchivo) throws DocumentException, IOException,
			SQLException {
	
		Document documento = new Document();
		ByteArrayOutputStream archivo = new ByteArrayOutputStream();
		pw = PdfWriter.getInstance(documento,
				archivo);
		agregarMetadata(documento);
		documento.open();
		pw.setPageEmpty(true);

		crearTitulos(documento);
		
		crearTablaDescobros(documento);

		for (LineaRegistroDto lineaRegistroDto : registros) {
			crearContenidoArchivo(lineaRegistroDto, documento);
		}
		
		agregarPieDePdf(documento);
		
		FileOutputStream file = new FileOutputStream(nombreArchivo);
		archivo.writeTo(file);
		return archivo;
	}

	private void agregarPieDePdf(Document documento) throws DocumentException {
		PdfPCell celdaH2 = crearCelda(Mensajes.MENSAJE_FIN_DE_REPORTE, ANCHO_BORDE_CERO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontFinDeReporte);
		PdfPTable tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(Rectangle.BOX);
		tabla.setWidthPercentage(100);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.addCell(celdaH2);

		documento.add(tabla);
		
		documento.close();
	}

	private void crearTablaDescobros(Document documento)
			throws DocumentException {
		addEmptyLine(documento, 2);

		PdfPCell celdaCol1 = crearCelda(ID_OPERACIONES_SHIVA, ANCHO_BORDE_UNO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA,
				blackFontTablaOperaciones,true);
		PdfPCell celdaCol2 = crearCelda(ESTADO_REV_OPERACIONES,
				ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_IZQUIERDA, blackFontTablaOperaciones,true);
		float[] medidaCeldas = { .15f, .30f };
		PdfPTable tabla = new PdfPTable(medidaCeldas);
		tabla.setWidthPercentage(70);

		tabla.addCell(celdaCol1);
		tabla.addCell(celdaCol2);

		documento.add(tabla);
	}

	private void crearTitulos(Document documento) throws DocumentException {
		PdfPCell celdaH1 = crearCelda(REPORTE_OPERACIONES_TRUNCAS,ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_ABAJO,
				ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulosCabeceraTitulo);
		
		PdfPCell celdaH11 = crearCelda(Utilidad.formatDateAndTime(fecha), ANCHO_BORDE_CERO,
				ALINEACION_HORIZONTAL_ABAJO, ALINEACION_VERTICAL_DERECHA,
				blackFontTitulosCabecera);
		
		PdfPCell celdaH2 = crearCelda(Mensajes.MENSAJE_SUB_TITULO, ANCHO_BORDE_CERO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA,
				blackFontSubTitulo);
		
		addEmptyLine(documento, 2);
		float[] medidaCeldas = { .50f, .50f };
		PdfPTable tabla = new PdfPTable(medidaCeldas);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		tabla.setWidthPercentage(95);
		tabla.addCell(celdaH1);
		tabla.addCell(celdaH11);

		documento.add(tabla);

		addEmptyLine(documento, 2);

		tabla = new PdfPTable(1);
		tabla.getDefaultCell().setBorder(Rectangle.BOX);
		tabla.setWidthPercentage(100);
		tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		tabla.addCell(celdaH2);

		documento.add(tabla);
	}

	public void crearContenidoArchivo(LineaRegistroDto lineaRegistroDto,
			Document documento) throws DocumentException, IOException,
			SQLException {

		PdfPCell celdaCol1 = crearCelda(lineaRegistroDto.getIdOperacion()
				.toString(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos);
		PdfPCell celdaCol2 = crearCelda(
				lineaRegistroDto.getDescripcionDetalle(), ANCHO_BORDE_UNO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA,
				blackFontTitulos);

		float[] medidaCeldas = { .15f, .30f };
		PdfPTable tabla = new PdfPTable(medidaCeldas);
		tabla.setWidthPercentage(70);

		tabla.addCell(celdaCol1);
		tabla.addCell(celdaCol2);

		documento.add(tabla);
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
	 * @param cabeceraTabla
	 *            si la celda va a ser cabecera de tabla
	 * @return
	 */
	protected PdfPCell crearCelda(String frase, Integer borderWidth,
			Integer alineacionVertical, Integer alineacionHorizontal,
			Font fuente, boolean cabeceraTabla) {
		PdfPCell celda = new PdfPCell(new Phrase(frase, fuente));
		celda.setBorderWidth(borderWidth);
		celda.setVerticalAlignment(alineacionVertical);
		celda.setHorizontalAlignment(alineacionHorizontal);
		if (cabeceraTabla) {
			celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		}
		return celda;
	}
}