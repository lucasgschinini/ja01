/**
 * 
 */
package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.CobroCompensacionPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author u586743
 *
 */
public abstract class GenerarResumenPdf extends GenerarPdf {
	private static Font fuenteTabla = Arial5;
//	private static Integer cantidadRegistros=0;
//	private static Integer paginaNumero = 1;
//	private static Integer cantidadRegitrosPorHoja = 50;

	public static byte []  generarResumenCompensacion(CobroCompensacionPdfCap entrada) throws NegocioExcepcion {
		Document documento = new Document(PageSize.LETTER);
		byte []  documentoByteArray = null;
		_Paginado _paginado= new _Paginado();
		
		if ((entrada.getListaCaps().size()+entrada.getListaDeuda().size()-41)% _paginado.getCantidadRegitrosPorHoja() == 0) {
			_paginado.setCantidadRegitrosPorHoja(47);
		}
		
		String idOperacion =Utilidad.rellenarCerosIzquierda(entrada.getIdOperacion().toString(),7);
		String idInternoSAP =Utilidad.rellenarCerosIzquierda(entrada.getIdInternoSAP(),10);
	
		String pdfName = "Operación de Cobro "+idOperacion+" - K$ "+idInternoSAP+" - Resumen.pdf";

		FileOutputStream baos ;  

		try {
			baos = new FileOutputStream(ControlArchivo.convertToFile(pdfName));
			PdfWriter pdfwriter = PdfWriter.getInstance(documento, baos);
			PageNumerator eventos = new PageNumerator();
			pdfwriter.setPageEvent(eventos);
			
			documento.open();

			crearTablaCabecera(documento, entrada);
			crearTablaDocumentosTECO(documento, entrada, _paginado);
			crearTablaDocumentoCap(documento, entrada, _paginado);
//			crearTablaDocumentosTECO(documento, entrada);
//			crearTablaDocumentoCap(documento, entrada);
//			
			crearTotalizadores(documento, entrada);
			insertarFirmaFinal(documento , entrada.getDetalleFirma() , entrada.getUsuarioVerificador(), entrada.getUrlFirma());

			documento.close();
			baos.close();

			
			
			File pdfABorrar = ControlArchivo.convertToFile(pdfName);
			byte[] byteArray = new byte[(int) pdfABorrar.length()];
		    byteArray = FileUtils.readFileToByteArray(pdfABorrar);
			pdfABorrar.delete();

			documentoByteArray = byteArray;

		} catch (FileNotFoundException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		} catch (IOException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}
		return documentoByteArray;

	}

	private static void crearTotalizadores (Document documento ,CobroCompensacionPdfCap entrada) throws NegocioExcepcion {
		BigDecimal	impTotalTECO = entrada.getImporteDeuda();
		BigDecimal	impTotalCliente = entrada.getImporteCaps().negate();
		BigDecimal	impTotal = impTotalTECO.subtract(impTotalCliente);
		
		//TODO 
		
		String aFavor = "";
		if (impTotal.signum() > 0) {
			aFavor = "SALDO A FAVOR DE TELECOM";
		}else if  (impTotal.signum() < 0) {
			aFavor = "SALDO A FAVOR DE PROVEEDOR" ;
		}
		PdfPTable tablaTotalizadores = new PdfPTable(new float [] {50,12,38});
		tablaTotalizadores.setWidthPercentage(85f);
		
		PdfPCell textoTotalTelecom = new PdfPCell(new Phrase("Total facturado por TELECOM ARGENTINA SA",Arial5Bold));
		PdfPCell textoTotalCliente = new PdfPCell(new Phrase("Total facturado por " + entrada.getCliente().getRazonSocial().toUpperCase(),Arial5Bold));
		PdfPCell textoTotal = new PdfPCell(new Phrase("Saldo",Arial5));
		PdfPCell menosRet = new PdfPCell(new Phrase("(menos las retenciones impositivas correspondientes)",Arial5));
		
		
		PdfPCell totalTelecom = new PdfPCell(new Phrase(Utilidad.formatCurrency(impTotalTECO, true, true),Arial5Bold));
		PdfPCell totalCliente = new PdfPCell(new Phrase(Utilidad.formatCurrency(impTotalCliente, true, true),Arial5Bold));
		PdfPCell total = new PdfPCell(new Phrase(Utilidad.formatCurrency(impTotal, true, true),Arial5Bold));
		PdfPCell celdaVacia = new PdfPCell();
		PdfPCell textoACompensar = new PdfPCell(new Phrase("monto a compensar",Arial5Bold));
		PdfPCell textoAFavor= new PdfPCell(new Phrase(aFavor,Arial5Bold));
		

		textoTotalTelecom.setBorder(Rectangle.NO_BORDER);
		totalTelecom.setBorder(Rectangle.NO_BORDER);
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		textoTotalCliente.setBorder(Rectangle.NO_BORDER);
		textoACompensar.setBorder(Rectangle.NO_BORDER);
		textoTotal.setBorder(Rectangle.NO_BORDER);
		menosRet.setBorder(Rectangle.NO_BORDER);
		textoAFavor.setBorder(Rectangle.NO_BORDER);
		total.setBorder(Rectangle.NO_BORDER);
		totalCliente.setBorder(Rectangle.BOTTOM);
		totalTelecom.setHorizontalAlignment(Element.ALIGN_RIGHT);
		total.setHorizontalAlignment(Element.ALIGN_RIGHT);
		totalCliente.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		
		tablaTotalizadores.addCell(textoTotalTelecom);
		tablaTotalizadores.addCell(totalTelecom);
		tablaTotalizadores.addCell(celdaVacia);
		tablaTotalizadores.addCell(textoTotalCliente);
		tablaTotalizadores.addCell(totalCliente);
		tablaTotalizadores.addCell(textoACompensar);
		tablaTotalizadores.addCell(textoTotal);
		tablaTotalizadores.addCell(total);
		tablaTotalizadores.addCell(textoAFavor);
		tablaTotalizadores.addCell(menosRet);
		tablaTotalizadores.addCell(celdaVacia);
		tablaTotalizadores.addCell(celdaVacia);
		
		
		
		try {
			documento.add(tablaTotalizadores);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	private static void crearTablaCabecera (Document documento ,CobroCompensacionPdfCap entrada) throws  NegocioExcepcion {

		int altura = 4;
		String stringFecha = formateadorFecha.format(entrada.getFecha());

		PdfPTable tablaCabecera = new PdfPTable(new float[] {15,19,18,10,10,18,10});
		tablaCabecera.setWidthPercentage(85f);
		tablaCabecera.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell celdaimagen = new PdfPCell();
		Image imagen = null ;
		try{

			imagen = Image.getInstance(entrada.getUrlLogo());

		} catch (BadElementException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		} catch (MalformedURLException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		} catch (IOException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}



		celdaimagen.addElement(imagen);


		PdfPCell titulo = new PdfPCell(new Phrase("SOLICITUD INTERNA DE COMPENSACIÓN DE CRÉDITOS / DEUDAS CON PROVEEDORES" , Arial8UnderlinedBold));
		titulo.setColspan(6);
		titulo.setBorderColor(BaseColor.WHITE);
		titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
		titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);


		tablaCabecera.addCell(imagen);
		tablaCabecera.addCell(titulo);

		PdfPCell espaciadorArriba = new PdfPCell(new Phrase("",Arial5));
		PdfPCell espaciadorAbajo = new PdfPCell(new Phrase("",Arial5));

		espaciadorArriba.setColspan(7);
		espaciadorAbajo.setColspan(7);
		espaciadorArriba.setBorder(Rectangle.NO_BORDER);
		espaciadorAbajo.setBorder(Rectangle.BOTTOM);
		espaciadorAbajo.setFixedHeight(7f);
		espaciadorArriba.setFixedHeight(7f);



		PdfPCell textoNumeroCliente 		= new PdfPCell(new Phrase("N° Cliente:",Arial5Bold));
		PdfPCell textoRazonSocial 			= new PdfPCell(new Phrase("Razón Social:",Arial5Bold));
		PdfPCell textoNumeroProveedor 		= new PdfPCell(new Phrase("N° de proveedor:",Arial5Bold));
		PdfPCell textoNumeroCuit	 		= new PdfPCell(new Phrase("N° de CUIT:",Arial5Bold));
		PdfPCell textoSectorSolicitante		= new PdfPCell(new Phrase("Sector solicitante:",Arial5Bold));
		PdfPCell textoClientesAdicionales 	= new PdfPCell(new Phrase("Numeros de cliente adicionales:",Arial5Bold));
		PdfPCell textoFecha					= new PdfPCell(new Phrase("Fecha de presentación en CAP",Arial5Bold));
		PdfPCell textoOrden					= new PdfPCell(new Phrase("Orden:",Arial5Bold));

		PdfPCell razonSocial 				= new PdfPCell(new Phrase(entrada.getCliente().getRazonSocial(),Arial5));
		PdfPCell numeroProveedor 			= new PdfPCell(new Phrase(entrada.getCliente().getIdProveedor(),Arial5));
		PdfPCell numeroCuit	 				= new PdfPCell(new Phrase(Utilidad.formatearCuit(entrada.getCliente().getCuit()),Arial5));
		PdfPCell sectorSolicitante			= new PdfPCell(new Phrase(entrada.getSectorSolicitante(),Arial5));
		PdfPCell numeroCliente 				= new PdfPCell(new Phrase(entrada.getCliente().getIdClienteLegal().toString(),Arial5));

		PdfPCell[] clientesAdicionales 		= new PdfPCell[10];

		int i =0;
		for (String clienteNumero : entrada.getClientesAdicionales()) {
			if (!Validaciones.isNullEmptyOrDash(clienteNumero)) {
				clientesAdicionales[i] = new PdfPCell(new Phrase(clienteNumero,Arial5));
				i++;
			}
			if (i>9)
			{
				break;
			}
		}


		PdfPCell fecha= new PdfPCell(new Phrase(stringFecha,Arial5));
		PdfPCell orden	= new PdfPCell(new Phrase(entrada.getOrden(),Arial5));

		textoFecha.setRowspan(2);
		textoClientesAdicionales.setRowspan(5);
		textoOrden.setRowspan(3);
		fecha.setRowspan(2);
		orden.setRowspan(3);


		textoNumeroCliente 		.setBorder(Rectangle.NO_BORDER);
		textoRazonSocial 		.setBorder(Rectangle.NO_BORDER);
		textoNumeroProveedor 	.setBorder(Rectangle.NO_BORDER);
		textoNumeroCuit	 		.setBorder(Rectangle.NO_BORDER);
		textoSectorSolicitante	.setBorder(Rectangle.NO_BORDER);
		textoClientesAdicionales.setBorder(Rectangle.NO_BORDER);
		textoFecha				.setBorder(Rectangle.NO_BORDER);
		textoOrden				.setBorder(Rectangle.NO_BORDER);
		numeroCliente 			.setBorder(Rectangle.NO_BORDER);
		razonSocial 			.setBorder(Rectangle.NO_BORDER);
		numeroProveedor 		.setBorder(Rectangle.NO_BORDER);
		numeroCuit	 			.setBorder(Rectangle.NO_BORDER);
		sectorSolicitante		.setBorder(Rectangle.NO_BORDER);
		fecha					.setBorder(Rectangle.NO_BORDER);
		orden					.setBorder(Rectangle.NO_BORDER);



		textoNumeroCliente			.setFixedHeight(altura);
		textoRazonSocial 			.setFixedHeight(altura);
		textoNumeroProveedor 		.setFixedHeight(altura);
		textoNumeroCuit	 			.setFixedHeight(altura);
		textoSectorSolicitante		.setFixedHeight(altura);
		textoClientesAdicionales 	.setFixedHeight(altura);
		textoFecha					.setFixedHeight(altura);
		textoOrden					.setFixedHeight(altura);
		razonSocial 				.setFixedHeight(altura);
		numeroProveedor 			.setFixedHeight(altura);
		numeroCuit	 				.setFixedHeight(altura);
		sectorSolicitante			.setFixedHeight(altura);

		numeroCliente.setHorizontalAlignment(Element.ALIGN_CENTER);

		for ( i =0 ;i<10;i++) {
			if (Validaciones.isObjectNull(clientesAdicionales[i])) {
				clientesAdicionales[i] = new PdfPCell(new Phrase("-",Arial5));
			}
			clientesAdicionales[i].setBorder(Rectangle.NO_BORDER);
			clientesAdicionales[i].setHorizontalAlignment(Element.ALIGN_CENTER);
		}


		tablaCabecera.addCell(espaciadorArriba);
		tablaCabecera.addCell(textoNumeroCliente);
		tablaCabecera.addCell(numeroCliente);
		tablaCabecera.addCell(textoClientesAdicionales);
		tablaCabecera.addCell(clientesAdicionales[0]);
		tablaCabecera.addCell(clientesAdicionales[1]);
		tablaCabecera.addCell(textoFecha);
		tablaCabecera.addCell(fecha);
		tablaCabecera.addCell(textoRazonSocial);
		tablaCabecera.addCell(razonSocial);
		tablaCabecera.addCell(clientesAdicionales[2]);
		tablaCabecera.addCell(clientesAdicionales[3]);
		tablaCabecera.addCell(textoNumeroProveedor);
		tablaCabecera.addCell(numeroProveedor);
		tablaCabecera.addCell(clientesAdicionales[4]);
		tablaCabecera.addCell(clientesAdicionales[5]);
		tablaCabecera.addCell(textoOrden);
		tablaCabecera.addCell(orden);
		tablaCabecera.addCell(textoNumeroCuit);
		tablaCabecera.addCell(numeroCuit);
		tablaCabecera.addCell(clientesAdicionales[6]);
		tablaCabecera.addCell(clientesAdicionales[7]);
		tablaCabecera.addCell(textoSectorSolicitante);
		tablaCabecera.addCell(sectorSolicitante);
		tablaCabecera.addCell(clientesAdicionales[8]);
		tablaCabecera.addCell(clientesAdicionales[9]);
		tablaCabecera.addCell(espaciadorAbajo);
		
		espaciadorAbajo.setFixedHeight(5);
		tablaCabecera.addCell(espaciadorArriba);

		try {

			documento.add(tablaCabecera);

		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}


	}
	private static PdfPTable crearCabeceraDeuda() {

		PdfPTable tablaDocumentos = new PdfPTable(new float[] {7,11,14,8,5,9,9,12,12,12});
		tablaDocumentos.setWidthPercentage(85f);
		PdfPCell titulo = new PdfPCell(new Phrase("Documentacion emitida por Telecom Argentina SA",Arial7Bold));
		titulo.setColspan(10);
		titulo.setBackgroundColor(amarillo);
		titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		titulo.setMinimumHeight(12f);
		titulo.setBorder(Rectangle.NO_BORDER);

		PdfPCell separador = new PdfPCell(new Phrase(" ",Arial5));
		separador.setColspan(10);
		separador.setBorder(Rectangle.NO_BORDER);
		separador.setFixedHeight(5);
		tablaDocumentos.addCell(titulo);
		tablaDocumentos.addCell(separador);

		PdfPCell textoSociedad = new PdfPCell(new Phrase("Sociedad", Arial5));
		PdfPCell textoIdCliente = new PdfPCell(new Phrase("Numero de Cliente", Arial5));
		PdfPCell textoIdComprobante = new PdfPCell(new Phrase("Numero de Comprobante", Arial5));
		PdfPCell textoVencimiento= new PdfPCell(new Phrase("Vto.", Arial5));
		PdfPCell textoMoneda= new PdfPCell(new Phrase("Moneda", Arial5));
		PdfPCell textoImporteOrigen= new PdfPCell(new Phrase("Importe Origen", Arial5));
		PdfPCell textoSaldoActual= new PdfPCell(new Phrase("Saldo Actual", Arial5));
		PdfPCell textoTipoCambio= new PdfPCell(new Phrase("Tipo de Cambio acordado para la compensación", Arial5));
		PdfPCell textoACompensar= new PdfPCell(new Phrase("A Compensar en $", Arial5));
		PdfPCell textoMontoReclamado= new PdfPCell(new Phrase("Monto reclamado o saldo pendiente en moneda origen", Arial5));
		
		textoSociedad.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoIdCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoIdComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoVencimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoMoneda.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoImporteOrigen.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoSaldoActual.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoTipoCambio.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoACompensar.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoMontoReclamado.setHorizontalAlignment(Element.ALIGN_CENTER);

		textoSociedad.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoIdCliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoIdComprobante.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoVencimiento.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoMoneda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoImporteOrigen.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoSaldoActual.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoTipoCambio.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoACompensar.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoMontoReclamado.setVerticalAlignment(Element.ALIGN_MIDDLE);

		tablaDocumentos.addCell(textoSociedad);
		tablaDocumentos.addCell(textoIdCliente);
		tablaDocumentos.addCell(textoIdComprobante);
		tablaDocumentos.addCell(textoVencimiento);
		tablaDocumentos.addCell(textoMoneda);
		tablaDocumentos.addCell(textoImporteOrigen);
		tablaDocumentos.addCell(textoSaldoActual);
		tablaDocumentos.addCell(textoTipoCambio);
		tablaDocumentos.addCell(textoACompensar);
		tablaDocumentos.addCell(textoMontoReclamado);

		return tablaDocumentos;
	}

	private static PdfPTable crearCabeceraCap(CobroCompensacionPdfCap entrada) {

		PdfPTable tablaDocumentos = new PdfPTable(new float[]  {6,12,8,5,12,12,12,12,12});
		tablaDocumentos.setWidthPercentage(85f);
		PdfPCell titulo = new PdfPCell(new Phrase("Documentación emitida por "+entrada.getCliente().getRazonSocial(),Arial7Bold));
		titulo.setColspan(9);
		titulo.setBackgroundColor(amarillo);
		titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		titulo.setMinimumHeight(12f);
		titulo.setBorder(Rectangle.NO_BORDER);

		PdfPCell separador = new PdfPCell(new Phrase(" ",Arial5));
		separador.setColspan(9);
		separador.setBorder(Rectangle.NO_BORDER);
		separador.setFixedHeight(5);
		tablaDocumentos.addCell(titulo);
		tablaDocumentos.addCell(separador);

		PdfPCell textoCodigoSociedad = new PdfPCell(new Phrase("Sociedad", Arial5));
		PdfPCell textoIdComprobante = new PdfPCell(new Phrase("Numero de Comprobante", Arial5));
		PdfPCell textoEmision= new PdfPCell(new Phrase("Emisión", Arial5));
		PdfPCell textoMoneda= new PdfPCell(new Phrase("Moneda", Arial5));
		PdfPCell textoImporteOrigen= new PdfPCell(new Phrase("Importe Origen", Arial5));
		PdfPCell textoSaldoActual= new PdfPCell(new Phrase("Saldo Actual", Arial5));
		PdfPCell textoTipoCambio= new PdfPCell(new Phrase("Tipo de Cambio acordado para la compensación", Arial5));
		PdfPCell textoACompensar= new PdfPCell(new Phrase("A Compensar en $", Arial5));
		PdfPCell textoMontoReclamado= new PdfPCell(new Phrase("Monto reclamado o saldo pendiente en moneda origen", Arial5));

		textoCodigoSociedad.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoIdComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoEmision.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoMoneda.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoImporteOrigen.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoSaldoActual.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoTipoCambio.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoACompensar.setHorizontalAlignment(Element.ALIGN_CENTER);
		textoMontoReclamado.setHorizontalAlignment(Element.ALIGN_CENTER);

		textoCodigoSociedad.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoIdComprobante.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoEmision.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoMoneda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoImporteOrigen.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoSaldoActual.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoTipoCambio.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoACompensar.setVerticalAlignment(Element.ALIGN_MIDDLE);
		textoMontoReclamado.setVerticalAlignment(Element.ALIGN_MIDDLE);

		tablaDocumentos.addCell(textoCodigoSociedad);
		tablaDocumentos.addCell(textoIdComprobante);
		tablaDocumentos.addCell(textoEmision);
		tablaDocumentos.addCell(textoMoneda);
		tablaDocumentos.addCell(textoImporteOrigen);
		tablaDocumentos.addCell(textoSaldoActual);
		tablaDocumentos.addCell(textoTipoCambio);
		tablaDocumentos.addCell(textoACompensar);
		tablaDocumentos.addCell(textoMontoReclamado);

		return tablaDocumentos;
	}

	private static void crearTablaDocumentoCap(Document documento ,CobroCompensacionPdfCap entrada, _Paginado _paginado) throws  NegocioExcepcion {
		
	//private static void crearTablaDocumentoCap(Document documento ,CobroCompensacionPdfCap entrada
		//	) throws  NegocioExcepcion {
		
		PdfPTable tablaDocumentos = crearCabeceraCap(entrada);


		for (VistaSoporteConsultaCapPdfCap registro : entrada.getListaCaps()) {


			PdfPCell codigoSociedad =  new PdfPCell(new Phrase(registro.getCodigoSociedad() , fuenteTabla));
			PdfPCell nroComprobante =  new PdfPCell(new Phrase(registro.getNroDeComprobante(),fuenteTabla));
			PdfPCell emision =  new PdfPCell(new Phrase(formateadorFecha.format(registro.getFechaEmision()),fuenteTabla));
			PdfPCell monedaSimbolo =  new PdfPCell(new Phrase(registro.getMoneda().getSigno(),fuenteTabla));
			PdfPCell importe =  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getImporteOrigen().negate(), false, true),fuenteTabla));
			PdfPCell saldo =  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getSaldoActual().negate(), false, true),fuenteTabla)); 
			PdfPCell tipoCambio = new PdfPCell(new Phrase((registro.getTipoCambio()) == null? "-" : registro.getTipoCambio().toString().replace(".",","),fuenteTabla));
			PdfPCell compensacion=  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getaCompensarEnPesos().negate(), false, true),fuenteTabla));
			PdfPCell MontoReclamado=  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getMontoReclamado()/*.negate()*/, false, true),fuenteTabla));

//		for ( int i=1;i<=200;i++) {
//			PdfPCell nroComprobante =  new PdfPCell(new Phrase("comprobante",fuenteTabla));
//			PdfPCell emision =  new PdfPCell(new Phrase(_paginado.getCantidadRegistros().toString(),fuenteTabla));
//			PdfPCell monedaSimbolo =  new PdfPCell(new Phrase("$",fuenteTabla));
//			PdfPCell importe =  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));
//			PdfPCell saldo =  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla)); 
//			PdfPCell tipoCambio = new PdfPCell(new Phrase("-",fuenteTabla));
//			PdfPCell compensacion=  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));
//			PdfPCell MontoReclamado=  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));

			codigoSociedad.setHorizontalAlignment(Element.ALIGN_CENTER);
			nroComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
			emision.setHorizontalAlignment(Element.ALIGN_CENTER);
			nroComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
			emision.setHorizontalAlignment(Element.ALIGN_CENTER);
			monedaSimbolo.setHorizontalAlignment(Element.ALIGN_CENTER);
			importe.setHorizontalAlignment(Element.ALIGN_RIGHT);
			saldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tipoCambio.setHorizontalAlignment(Element.ALIGN_CENTER);
			compensacion.setHorizontalAlignment(Element.ALIGN_RIGHT);
			MontoReclamado.setHorizontalAlignment(Element.ALIGN_RIGHT);

			tablaDocumentos.addCell(codigoSociedad);
			tablaDocumentos.addCell(nroComprobante);
			tablaDocumentos.addCell(emision);
			tablaDocumentos.addCell(monedaSimbolo);
			tablaDocumentos.addCell(importe);
			tablaDocumentos.addCell(saldo);
			tablaDocumentos.addCell(tipoCambio);
			tablaDocumentos.addCell(compensacion);
			tablaDocumentos.addCell(MontoReclamado);

			_paginado.setCantidadRegistros(_paginado.getCantidadRegistros() + 1);

			if ((_paginado.getCantidadRegistros() % 41 == 0 && _paginado.getPaginaNumero() == 1) || _paginado.getCantidadRegistros() % _paginado.getCantidadRegitrosPorHoja() == 0 && _paginado.getPaginaNumero() > 1 ) {
				_paginado.setPaginaNumero(_paginado.getPaginaNumero() + 1);
				_paginado.setCantidadRegistros(0);
				try {
					documento.add(tablaDocumentos);
				} catch (DocumentException e) {
					throw new  NegocioExcepcion(e.getMessage(),e);
				}
				insertarFirma(documento, entrada.getDetalleFirma(),entrada.getUsuarioVerificador(), entrada.getUrlFirma());
				
				tablaDocumentos = crearCabeceraCap(entrada);
			}
		}
		
		PdfPCell total = new PdfPCell(new Phrase(Utilidad.formatCurrency(entrada.getImporteCaps().negate(), false, true),fuenteTabla));
		PdfPCell celdaVacia6 = new PdfPCell();
		PdfPCell celdaVacia = new PdfPCell();
		celdaVacia6.setColspan(7);
		celdaVacia6.setBorder(Rectangle.NO_BORDER);
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		total.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tablaDocumentos.addCell(celdaVacia6);
		tablaDocumentos.addCell(total);
		tablaDocumentos.addCell(celdaVacia);
	

		try {
			documento.add(tablaDocumentos);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	private static void crearTablaDocumentosTECO(Document documento ,CobroCompensacionPdfCap entrada, _Paginado _paginado) throws  NegocioExcepcion {
		PdfPTable tablaDocumentos = crearCabeceraDeuda();

//
				for (VistaSoporteConsultaDeudaPdfCap registro : entrada.getListaDeuda()) {
		
					PdfPCell sociedad = new PdfPCell(new Phrase (Validaciones.isObjectNull(registro.getSociedad().getApocope())?"-":registro.getSociedad().getApocope() , fuenteTabla));
					PdfPCell IdCliente  =  new PdfPCell(new Phrase( registro.getCliente(),fuenteTabla));
					PdfPCell nroComprobante =  new PdfPCell(new Phrase ((Validaciones.isObjectNull(registro.getNroDeComprobante())? (Validaciones.isObjectNull(registro.getNroReferencia())? "-": registro.getNroReferencia()): registro.getNroDeComprobante()),fuenteTabla));
					PdfPCell vencimiento =  new PdfPCell(new Phrase(Validaciones.isObjectNull(registro.getFechaDateVto())?"-":formateadorFecha.format(registro.getFechaDateVto()),fuenteTabla));
					PdfPCell monedaSimbolo =  new PdfPCell(new Phrase(registro.getMoneda().getSigno(),fuenteTabla));
					PdfPCell importe =  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getImporteOrigen(), false, true),fuenteTabla));
					PdfPCell saldo =  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getSaldo(), false, true),fuenteTabla)); 
					PdfPCell tipoCambio = new PdfPCell(new Phrase((registro.getTipoCambio()) == null? "-" : registro.getTipoCambio().toString().replace(".",","),fuenteTabla));
					PdfPCell compensacion=  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getaCompensarEnPesos(), false, true),fuenteTabla));
					PdfPCell MontoReclamado=  new PdfPCell(new Phrase(Utilidad.formatCurrency(registro.getMontoReclamado(), false, true),fuenteTabla));
				
//		for ( int i=0;i<70;i++) {
//			PdfPCell IdCliente  =  new PdfPCell(new Phrase("cliente",fuenteTabla));
//			PdfPCell nroComprobante =  new PdfPCell(new Phrase("comprobante",fuenteTabla));
//			PdfPCell vencimiento =  new PdfPCell(new Phrase(_paginado.getCantidadRegistros().toString(),fuenteTabla));
//			PdfPCell monedaSimbolo =  new PdfPCell(new Phrase("$",fuenteTabla));
//			PdfPCell importe =  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));
//			PdfPCell saldo =  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla)); 
//			PdfPCell tipoCambio = new PdfPCell(new Phrase("-",fuenteTabla));
//			PdfPCell compensacion=  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));
//			PdfPCell MontoReclamado=  new PdfPCell(new Phrase("999.999.999,99",fuenteTabla));

			sociedad.setHorizontalAlignment(Element.ALIGN_CENTER);
			IdCliente.setHorizontalAlignment(Element.ALIGN_CENTER);
			vencimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
			nroComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
			vencimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
			monedaSimbolo.setHorizontalAlignment(Element.ALIGN_CENTER);
			importe.setHorizontalAlignment(Element.ALIGN_RIGHT);
			saldo.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tipoCambio.setHorizontalAlignment(Element.ALIGN_CENTER);
			compensacion.setHorizontalAlignment(Element.ALIGN_RIGHT);
			MontoReclamado.setHorizontalAlignment(Element.ALIGN_RIGHT);

			tablaDocumentos.addCell(sociedad);
			tablaDocumentos.addCell(IdCliente);
			tablaDocumentos.addCell(nroComprobante);
			tablaDocumentos.addCell(vencimiento);
			tablaDocumentos.addCell(monedaSimbolo);
			tablaDocumentos.addCell(importe);
			tablaDocumentos.addCell(saldo);
			tablaDocumentos.addCell(tipoCambio);
			tablaDocumentos.addCell(compensacion);
			tablaDocumentos.addCell(MontoReclamado);


			_paginado.setCantidadRegistros(_paginado.getCantidadRegistros() + 1);

			if ((_paginado.getCantidadRegistros() % 41 == 0 && _paginado.getPaginaNumero() == 1) || _paginado.getCantidadRegistros() % _paginado.getCantidadRegitrosPorHoja() == 0 && _paginado.getPaginaNumero() > 1 ) {
				_paginado.setPaginaNumero(_paginado.getPaginaNumero() + 1);
				_paginado.setCantidadRegistros(0);
				try {
					documento.add(tablaDocumentos);
				} catch (DocumentException e) {
					throw new  NegocioExcepcion(e.getMessage(),e);
				}
				insertarFirma(documento, entrada.getDetalleFirma(),entrada.getUsuarioVerificador(), entrada.getUrlFirma());
				tablaDocumentos = crearCabeceraDeuda();
			}
		}

		PdfPCell total = new PdfPCell(new Phrase(Utilidad.formatCurrency(entrada.getImporteDeuda(), false, true),fuenteTabla));
		PdfPCell celdaVacia7 = new PdfPCell();
		PdfPCell celdaVacia9 = new PdfPCell();
		PdfPCell celdaVacia = new PdfPCell();
		
		celdaVacia7.setColspan(8);
		celdaVacia9.setColspan(10);
		celdaVacia9.setBorder(Rectangle.NO_BORDER);
		celdaVacia9.setFixedHeight(5);
		celdaVacia7.setBorder(Rectangle.NO_BORDER);
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		total.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		tablaDocumentos.addCell(celdaVacia7);
		tablaDocumentos.addCell(total);
		tablaDocumentos.addCell(celdaVacia);
		tablaDocumentos.addCell(celdaVacia9);

		try {
			documento.add(tablaDocumentos);
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
protected static void insertarFirmaFinal(Document documento , String entrada,String usuario, URL url) throws NegocioExcepcion {
		PdfPTable tablaMensajeCuerpo = new PdfPTable(2);
		PdfPCell celdaFirma = new PdfPCell();
		PdfPCell celdaTextoFirma = new PdfPCell(new Phrase("\n\n FIRMA AUTORIZANTE \n\n "+" ACLARACION O SELLO \n DEL AUTORIZANTE", Arial8));
		Image imagen = null ;

		tablaMensajeCuerpo.setWidthPercentage(85);

		if (Validaciones.isObjectNull(url)) {
			String noFirma = "No existe la imagen de la firma del usuario " + (!Validaciones.isNullEmptyOrDash(usuario) ? usuario : "" ) + "\n\n";
			Phrase frase2 = new Phrase(noFirma,TimesRoman7);

			Traza.advertencia(GenerarPdf.class, "No se existe la URL de la firma del usuario " + usuario);
			celdaFirma.addElement(frase2);
		} else {
			try {
				imagen = Image.getInstance(url);
			} catch (BadElementException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			} catch (MalformedURLException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			} catch (IOException e) {
				throw new  NegocioExcepcion(e.getMessage(),e);
			}
			imagen.scaleAbsoluteWidth(120f);
			celdaFirma.addElement(imagen);
		}

		celdaFirma.setBorder(Rectangle.NO_BORDER);
		celdaTextoFirma.setBorder(Rectangle.NO_BORDER);

		if (Validaciones.isNullEmptyOrDash(entrada)) {
			String noFirma = "No existe el detalle de la  firma del usuario " + (!Validaciones.isNullEmptyOrDash(usuario) ? usuario : "" );
			Phrase frase2 = new Phrase(noFirma,TimesRoman7);

			Traza.advertencia(GenerarPdf.class, noFirma);
			celdaFirma.addElement(frase2);
			tablaMensajeCuerpo.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			String[] vector = entrada.split(";");
			Phrase frase1 = new Phrase(vector[0],TimesRoman7Bold);
			Phrase frase2 = new Phrase("",TimesRoman7);
			Phrase frase3 = new Phrase(vector[8],TimesRoman7Bold);

			frase2.add(vector[1]+" \n");
			frase2.add(vector[2]+" \n");
			frase2.add(vector[3]+" \n");
			frase2.add(vector[4]+" \n");
			frase2.add(vector[5]+" \n");
			frase2.add(vector[6]+" \n");
			frase2.add(vector[7]+" \n");
			
			celdaFirma.addElement(frase1);
			celdaFirma.addElement(frase2);
			celdaFirma.addElement(frase3);
		}
		tablaMensajeCuerpo.addCell(celdaTextoFirma);
		tablaMensajeCuerpo.addCell(celdaFirma);

		try {
			documento.add(tablaMensajeCuerpo);
			documento.newPage();
		} catch (DocumentException e) {
			throw new  NegocioExcepcion(e.getMessage(),e);
		}
	}
}
