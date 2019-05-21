/**
 * 
 */
package ar.com.telecom.shiva.base.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author u586743
 *
 */
public class PageNumerator extends PdfPageEventHelper {
	/** The template with the total number of pages. */
	PdfTemplate total;

	


	
	public void onOpenDocument(PdfWriter writer, Document document) {
	    total = writer.getDirectContent().createTemplate(30, 12);
	}

	
	
	public void onEndPage(PdfWriter writer, Document document) {
	    PdfPTable table = new PdfPTable(3);
	    try {
	        table.setWidths(new int[]{24, 24, 2});
	        table.getDefaultCell().setFixedHeight(10);
	        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	        PdfPCell cell = new PdfPCell();
	        cell.setBorder (Rectangle.NO_BORDER);
	       // cell.setBorderWidthTop (1);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setPhrase(new Phrase("USO INTERNO EXCLUSIVAMENTE" , new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL)));
	        table.addCell(cell);

	        cell = new PdfPCell();
	        cell.setBorder (Rectangle.NO_BORDER);
	       // cell.setBorderWidthTop (1);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setPhrase(new Phrase(String.format("Pagina %d de", writer.getPageNumber()), new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL)));
	        table.addCell(cell);

	        cell = new PdfPCell(Image.getInstance(total));
	        cell.setBorder (Rectangle.NO_BORDER);
	       // cell.setBorderWidthTop (1);
	        table.addCell(cell);
	        table.setTotalWidth(document.getPageSize().getWidth()
	                - document.leftMargin() - document.rightMargin());
	        table.writeSelectedRows(0, -1, document.leftMargin(),
	                document.bottomMargin() - 15, writer.getDirectContent());
	    }
	    catch(DocumentException de) {
	        throw new ExceptionConverter(de);
	    }
	}


	public void onCloseDocument(PdfWriter writer, Document document) {
	    ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
	            new Phrase(String.valueOf(writer.getPageNumber() - 1), new Font(Font.FontFamily.UNDEFINED, 8, Font.NORMAL)),
	            2, 2, 0);
	    }
	}
