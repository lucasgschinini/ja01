package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class GeneradorComprobantePago {

	protected Font blackFontTitulos = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
	private Font blackFontMargen = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL);
	private Font blackFontFechaVencimiento = new Font( Font.FontFamily.HELVETICA, 5, Font.NORMAL);
	private Font blackFontPieDePagina = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL);
	private Font blackFontPieDePaginaCPD = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
	protected Font blackFontTitulosCabecera = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
	private Font whiteTitle = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.WHITE);
	

	private List<ShvBolBoletaSinValor> boletaSinValorModelo;
	private List<ShvValValor> valorModelo;
	private List<VistaSoporteResultadoBusquedaValor> valorModeloPdf;
	private IAcuerdoDao acuerdoDao;
	private FileOutputStream archivoPDFOutput;
	private File archivoPDF;
	private Document documento;
	private String nombreArchivo;

	// Costado
	public static final String PARA_OF_SERVICIOS_A_EMPRESAS = "1.PARA OF. SERVICIOS A EMPRESAS TELECOM ARGENTINA S.A.";
	public static final String PARA_CASA_CENTRAL = "1.PARA CASA CENTRAL - OF. SERVICIOS A EMPRESAS TELECOM ARGENTINA S.A.";
	public static final String PARA_LA_SUCURSAL = "2.PARA LA SUCURSAL";

	// Tabla Cabecera
	private String COBRANZA_ELECTRONICA_PLUS = "COBRANZA ELECTRONICA PLUS";
	private String COBRANZA_CON_CUSTODIA_INTEGRADA = "A1476 COBRANZA CON CUSTODIA INTEGRADA";
	private String EMPRESA = "EMPRESA:";
	private String FECHAS = "Fecha:";
	private String CODIGO = "CODIGO";
	private String NUMERO_CUIT = "N° CUIT";
	private String PRODUCTO = "PRODUCTO";
	private String ACUERDOS = "ACUERDOS";
	private String BOLETA_NUMERO = "BOLETA N°";

	// Tabla Datos del Depositante
	private String DATOS_DEL_DEPOSITANTE = "DATOS DEL DEPOSITANTE";
	private String NOMBRE = "Nombre:";
	private String DATO1 = "DATO1:";
	private String NUMERO_CLIENTE = "N° Cliente:";

	// Tabla Detalles del Pago
	private String DETALLE_DEL_PAGO = "DETALLE DEL PAGO";
	private String DATO2 = "DATO2";
	private String DATO3 = "DATO3";
	private String IMPORTE = "IMPORTE";

	// Tabla Detalles del Deposito
	private String DETALLE_DEL_DEPOSITO = "DETALLE DEL DEPOSITO";
	private String BANCO = "BANCO";
	private String CHEQUE_NUMERO = "CHEQUE N°";
	private String FECHA_VENCIMIENTO = "FECHA VENCIMIENTO";
	private String DIA = "Dia";
	private String MES = "Mes";
	private String ANIO = "Año";
	private String TOTAL_DEL_DEPOSITO = "TOTAL DEL DEPOSITO: ";

	// Mensajes pie
	private String MENSAJE_TICKET = "\"El ticket entregado por el banco correctamente intervenido, será válido como comprobante de pago\"";
	private String MENSAJE_IMPORTANTE = "IMPORTANTE: Los cheques pueden venir a la orden de la Empresa beneficiaria o de un tercero, en caso de no estar endosados, solicitar al cajero la responsabilidad por la falta de endoso";
	private String MENSAJE_IMPORTANTE_PD = "IMPORTANTE: No intervenir los cheques con el sello de caja";
	private String C_P_D = "C.P.D.";

	private final String VALOR_PRODUCTO = "004";
	
	// Simbolos
	private String ESPACIO_BLANCO = " ";
	// Ancho de bordes
	protected Integer ANCHO_BORDE_CERO = 0;
	protected Integer ANCHO_BORDE_UNO = 1;

	// Alineacion Vertical
	protected Integer ALINEACION_VERTICAL_IZQUIERDA = Element.ALIGN_LEFT;
	protected Integer ALINEACION_VERTICAL_CENTRADA = Element.ALIGN_CENTER;
	protected Integer ALINEACION_VERTICAL_DERECHA = Element.ALIGN_RIGHT;

	// Alineacion Horizontal
	private Integer ALINEACION_HORIZONTAL_ARRIBA = Element.ALIGN_TOP;
	protected Integer ALINEACION_HORIZONTAL_MEDIO = Element.ALIGN_MIDDLE;
	protected Integer ALINEACION_HORIZONTAL_ABAJO = Element.ALIGN_BOTTOM;
	
	//Color Shiva
	private final int RED = 12;
	private final int GREEN = 134;
	private final int BLUE = 199;
	
	private final int LEGAL_VERTICAL_TEXT_1_Y = 30;
	private final int LEGAL_VERTICAL_TEXT_2_Y = 390;
	private final int ROTACION = -90;
		

	public GeneradorComprobantePago(ShvValValor valorModelo)
			throws FileNotFoundException, DocumentException {
		List<ShvValValor> listaModelo = new ArrayList<ShvValValor>();
		listaModelo.add(valorModelo);
		this.valorModelo = listaModelo;
		archivoPDF = ControlArchivo.convertToFile(Constantes.NOMBRE_ARCHIVO_DEFAULT_PDF);
		archivoPDFOutput = new FileOutputStream(archivoPDF);
	}
	
	public GeneradorComprobantePago(List<ShvValValor> valorModeloLista)
			throws FileNotFoundException, DocumentException {
		this.valorModelo = valorModeloLista;
		archivoPDF = ControlArchivo.convertToFile(Constantes.NOMBRE_ARCHIVO_DEFAULT_PDF);
		archivoPDFOutput = new FileOutputStream(archivoPDF);

	}
	
	public GeneradorComprobantePago(VistaSoporteResultadoBusquedaValor valorModelo, IAcuerdoDao acuerdoDao)
			throws FileNotFoundException, DocumentException {
		List<VistaSoporteResultadoBusquedaValor> listaModelo = new ArrayList<VistaSoporteResultadoBusquedaValor>();
		listaModelo.add(valorModelo);
		this.valorModeloPdf = listaModelo;
		this.acuerdoDao= acuerdoDao;
		archivoPDF = ControlArchivo.convertToFile(Constantes.NOMBRE_ARCHIVO_DEFAULT_PDF);
		archivoPDFOutput = new FileOutputStream(archivoPDF);
	}

	public GeneradorComprobantePago(Collection<VistaSoporteResultadoBusquedaValor> valorModeloLista, IAcuerdoDao acuerdoDao)
			throws FileNotFoundException, DocumentException {
		List<VistaSoporteResultadoBusquedaValor> listaModelo = new ArrayList<VistaSoporteResultadoBusquedaValor>();
		for(VistaSoporteResultadoBusquedaValor valor : valorModeloLista ){
		listaModelo.add(valor);
		}
		this.setValorModeloPdf(listaModelo);
		this.setAcuerdoDao(acuerdoDao);
		archivoPDF = ControlArchivo.convertToFile(Constantes.NOMBRE_ARCHIVO_DEFAULT_PDF);
		archivoPDFOutput = new FileOutputStream(archivoPDF);
	}


	public GeneradorComprobantePago(){}
	
	/**
	 * Metodo para agregar datos relacionados al documento
	 * 
	 * @param documento
	 */
	protected void agregarMetadata(Document documento) {
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
	 * @throws SQLException
	 * @throws PersistenciaExcepcion 
	 */
	@SuppressWarnings({ })
	public void crearContenidoPDF(Document documento, PdfWriter pw) throws DocumentException, IOException,
			SQLException, PersistenciaExcepcion {

		agregarMetadata(documento);		
		
		generarContenidoPDF(documento, pw);
//		addEmptyLine(documento, 5);
//		addLineaTroquelada(documento);
//		addEmptyLine(documento, 3);
//		generarContenidoPDF(documento, pw, true);
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
	private void crearTablaCobranzaLogo(Document documento, Image logo)
			throws DocumentException, IOException {
		
		float[] medidaCeldas = { .80f, .10f, .57f };
		PdfPTable tablaGeneralCobranzaLogo = new PdfPTable(medidaCeldas);
		tablaGeneralCobranzaLogo.setWidthPercentage(95);

		PdfPCell celdaCobranza = crearCelda(COBRANZA_ELECTRONICA_PLUS, ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_ABAJO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulosCabecera);
		celdaCobranza.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaLogo = new PdfPCell(logo);
		celdaLogo.setBorderWidth(ANCHO_BORDE_CERO);
		celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celdaLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tablaGeneralCobranzaLogo.addCell(celdaCobranza);
		tablaGeneralCobranzaLogo.addCell(celdaMedio);
		tablaGeneralCobranzaLogo.addCell(celdaLogo);
		tablaGeneralCobranzaLogo.setSpacingAfter(2f);

		documento.add(tablaGeneralCobranzaLogo);
	}

	/**
	 * Método que genera la tabla correspondiente a la franja de Cobranza, tabla
	 * de fecha y logo del banco (Para Cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param dia
	 * @param mes
	 * @param ano
	 * @param logo
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaCobranzaFechaLogoPD(Document documento, String dia,
			String mes, String ano, Image logo) throws DocumentException,
			IOException {
		
		float[] medidaCeldas = { .40f, .38f, .50f };
		PdfPTable tablaGeneralCobranzaLogo = new PdfPTable(medidaCeldas);
		tablaGeneralCobranzaLogo.setWidthPercentage(95);

		PdfPCell celdaCobranza = crearCelda(COBRANZA_CON_CUSTODIA_INTEGRADA, ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulosCabecera);
		celdaCobranza.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablaGeneralFechas = new PdfPTable(1);
		PdfPTable tablaFechas = new PdfPTable(4);
		tablaFechas.addCell(crearCelda(FECHAS, ANCHO_BORDE_UNO,	ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA,	blackFontTitulos));
		tablaFechas.addCell(crearCelda(dia, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaFechas.addCell(crearCelda(mes, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaFechas.addCell(crearCelda(ano, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));

		PdfPCell celdaFecha = new PdfPCell(tablaFechas);
		celdaFecha.setBorder(Rectangle.NO_BORDER);
		celdaFecha.setHorizontalAlignment(ALINEACION_VERTICAL_CENTRADA);
		celdaFecha.setVerticalAlignment(ALINEACION_HORIZONTAL_ABAJO);

		PdfPCell celdaVacia = new PdfPCell(new Paragraph(ESPACIO_BLANCO));
		celdaVacia.setBorder(Rectangle.NO_BORDER);

		tablaGeneralFechas.addCell(celdaVacia);
		tablaGeneralFechas.addCell(celdaFecha);
		tablaGeneralFechas.addCell(celdaVacia);

		PdfPCell celdaGeneralFecha = new PdfPCell(tablaGeneralFechas);
		celdaGeneralFecha.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaLogo = new PdfPCell(logo);
		celdaLogo.setBorderWidth(ANCHO_BORDE_CERO);
		celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celdaLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);

		tablaGeneralCobranzaLogo.addCell(celdaCobranza);
		tablaGeneralCobranzaLogo.addCell(celdaGeneralFecha);
		tablaGeneralCobranzaLogo.addCell(celdaLogo);

		documento.add(tablaGeneralCobranzaLogo);
	}

	/**
	 * Método que genera la tabla correspondiente a la franja del nombre de la
	 * Empresa y tabla de fecha del banco (NO Cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param nombreEmpresa
	 * @param dia
	 * @param mes
	 * @param ano
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaEmpresaFecha(Document documento, String nombreEmpresa, String dia, String mes, String ano)
			throws DocumentException, IOException {
		float[] medidaCeldas = { .55f, .22f, .38f };
		PdfPTable tablaGeneralEmpresaFecha = new PdfPTable(medidaCeldas);
		tablaGeneralEmpresaFecha.setWidthPercentage(95);

		PdfPCell celdaEmpresa = crearCelda(EMPRESA + nombreEmpresa,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablaFechas = new PdfPTable(4);
		tablaFechas.addCell(crearCelda(FECHAS, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA,	blackFontTitulos));
		tablaFechas.addCell(crearCelda(dia, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaFechas.addCell(crearCelda(mes, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaFechas.addCell(crearCelda(ano, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));

		PdfPCell celdaFecha = new PdfPCell(tablaFechas);

		tablaGeneralEmpresaFecha.addCell(celdaEmpresa);
		tablaGeneralEmpresaFecha.addCell(celdaMedio);
		tablaGeneralEmpresaFecha.addCell(celdaFecha);
		tablaGeneralEmpresaFecha.setSpacingAfter(5f);

		documento.add(tablaGeneralEmpresaFecha);
	}

	/**
	 * Método que genera la tabla correspondiente a la franja del nombre de la
	 * Empresa (Para Cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param nombreEmpresa
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaEmpresaPD(Document documento, String nombreEmpresa)
			throws DocumentException, IOException {
		float[] medidaCeldas = { .85f, .22f };
		PdfPTable tablaGeneralEmpresaFecha = new PdfPTable(medidaCeldas);
		tablaGeneralEmpresaFecha.setWidthPercentage(95);

		PdfPCell celdaEmpresa = crearCelda(EMPRESA + nombreEmpresa,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos);

		PdfPCell celdaDerecha = new PdfPCell();
		celdaDerecha.setBorder(Rectangle.NO_BORDER);

		tablaGeneralEmpresaFecha.addCell(celdaEmpresa);
		tablaGeneralEmpresaFecha.addCell(celdaDerecha);
		tablaGeneralEmpresaFecha.setSpacingAfter(5f);

		documento.add(tablaGeneralEmpresaFecha);
	}

	/**
	 * Método que genera la tabla correspondiente a la franja del codigo y el
	 * numero de boleta (NO Cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param idBoleta
	 * @param cuit
	 * @param producto
	 * @param acuerdo
	 * @throws DocumentException
	 */
	private void crearTablaCodigoBoleta(Document documento, String idBoleta,
			String cuit, String producto, Integer acuerdo)
			throws DocumentException {
		
		float[] medidaCeldas = { .55f, .20f, .40f };
		PdfPTable tablaPrincipal = new PdfPTable(medidaCeldas);
		tablaPrincipal.setWidthPercentage(95);

		PdfPTable tablaCodigo = crearTablaCodigo(cuit, producto, acuerdo);
		PdfPCell celdaCodigo = new PdfPCell(tablaCodigo);
		celdaCodigo.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaCodigo);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaMedio);

		PdfPTable tablaBoleta = new PdfPTable(2);
		tablaBoleta.addCell(crearCelda(BOLETA_NUMERO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaBoleta.addCell(crearCelda(idBoleta, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		
		PdfPCell celdaBoleta = new PdfPCell(tablaBoleta);
		celdaBoleta.setBorder(Rectangle.NO_BORDER);

		tablaPrincipal.addCell(celdaBoleta);

		documento.add(tablaPrincipal);
	}

	/**
	 * Método que genera la tabla correspondiente a la franja del codigo y el
	 * numero de boleta (Para Cheque de Pago diferido)
	 * 
	 * @param documento
	 * @param idBoleta
	 * @param cuit
	 * @param producto
	 * @param acuerdo
	 * @throws DocumentException
	 */
	private void crearTablaCodigoBoletaPD(Document documento, String idBoleta,
			String cuit, String producto, Integer acuerdo)
			throws DocumentException {
		
		float[] medidaCeldas = { .55f, .01f, .21f, .20f };
		PdfPTable tablaPrincipal = new PdfPTable(medidaCeldas);
		tablaPrincipal.setWidthPercentage(95);

		PdfPTable tablaCodigo = crearTablaCodigo(cuit, producto, acuerdo);
		PdfPCell celdaCodigo = new PdfPCell(tablaCodigo);
		celdaCodigo.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaCodigo);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaMedio);

		PdfPTable tablaBoleta = new PdfPTable(2);
		tablaBoleta.addCell(crearCelda(BOLETA_NUMERO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaBoleta.addCell(crearCelda(idBoleta, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPCell celdaBoleta = new PdfPCell(tablaBoleta);
		celdaBoleta.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaBoleta);

		PdfPCell celdaDerecha = new PdfPCell();
		celdaDerecha.setBorder(Rectangle.NO_BORDER);
		tablaPrincipal.addCell(celdaDerecha);

		documento.add(tablaPrincipal);
	}

	/**
	 * Metodo separado que genera la tabla de código correspondiente a la franja
	 * de Codigo y Numero de boleta
	 * 
	 * @param cuit
	 * @param producto
	 * @param acuerdo
	 * @return
	 */
	private PdfPTable crearTablaCodigo(String cuit, String producto,
			Integer acuerdo) {
		
		PdfPTable tablaGeneralDatosCodigo = new PdfPTable(1);
		float[] medidaCeldas = { .20f, .50f };
		PdfPTable tablaDatosCodigo = new PdfPTable(medidaCeldas);
		tablaDatosCodigo.addCell(crearCelda(CODIGO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_ARRIBA, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPTable tablaCuitProdAcuerdo = new PdfPTable(3);
		tablaCuitProdAcuerdo.addCell(crearCelda(NUMERO_CUIT, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaCuitProdAcuerdo.addCell(crearCelda(PRODUCTO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaCuitProdAcuerdo.addCell(crearCelda(ACUERDOS, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));
		tablaCuitProdAcuerdo.addCell(crearCelda(cuit, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaCuitProdAcuerdo.addCell(crearCelda(producto, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaCuitProdAcuerdo.addCell(crearCelda(acuerdo.toString(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPCell celdaCuitProdAcuerdo = new PdfPCell(tablaCuitProdAcuerdo);
		celdaCuitProdAcuerdo.setBorder(Rectangle.NO_BORDER);
		tablaDatosCodigo.addCell(celdaCuitProdAcuerdo);

		PdfPCell celdaTablaDatosCodigo = new PdfPCell(tablaDatosCodigo);
		celdaTablaDatosCodigo.setBorder(Rectangle.NO_BORDER);

		tablaGeneralDatosCodigo.addCell(celdaTablaDatosCodigo);

		return tablaGeneralDatosCodigo;

	}

	/**
	 * Metodo que crea la tabla con el titulo de Datos del Depositante
	 * 
	 * @param documento
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTituloDatosDepositante(Document documento)
			throws DocumentException, IOException {

		PdfPTable tablaGeneralTituloDepositante = new PdfPTable(1);
		tablaGeneralTituloDepositante.setWidthPercentage(95);

		PdfPCell celdaDepositante = crearCelda(DATOS_DEL_DEPOSITANTE, ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, whiteTitle);
		celdaDepositante.setBackgroundColor(new BaseColor(RED, GREEN, BLUE));
		tablaGeneralTituloDepositante.addCell(celdaDepositante);
		tablaGeneralTituloDepositante.setSpacingAfter(5f);

		documento.add(tablaGeneralTituloDepositante);
	}

	/**
	 * Método que crea la tabla con los Datos del Depositante y el Número de
	 * cliente
	 * 
	 * @param documento
	 * @param nombreDepositante
	 * @param numeroCliente
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaDatosDepositante(Document documento,
			String nombreDepositante, String numeroCliente)
			throws DocumentException, IOException {

		float[] medidaCeldasDatosDepositante = { .66f, .60f };
		PdfPTable tablaGeneralDatosDepositante = new PdfPTable(medidaCeldasDatosDepositante);
		tablaGeneralDatosDepositante.setWidthPercentage(95);

		PdfPTable tablaNombre = new PdfPTable(1);
		tablaNombre.addCell(crearCelda(NOMBRE + " " + nombreDepositante, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_ABAJO, ALINEACION_VERTICAL_IZQUIERDA, blackFontTitulos));

		PdfPCell celdaTablaNombre = new PdfPCell(tablaNombre);
		celdaTablaNombre.setBorder(Rectangle.NO_BORDER);

		tablaGeneralDatosDepositante.addCell(celdaTablaNombre);

		float[] medidaCeldasNroCliente = { .20f, .60f };
		PdfPTable tablaNroCliente = new PdfPTable(medidaCeldasNroCliente);
		PdfPTable tablaDatoCliente = new PdfPTable(1);
		tablaDatoCliente.addCell(crearCelda(DATO1, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDatoCliente.addCell(crearCelda(NUMERO_CLIENTE, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA,	blackFontTitulos));
		tablaNroCliente.addCell(new PdfPCell(tablaDatoCliente));
		tablaNroCliente.addCell(crearCelda(numeroCliente, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_ABAJO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPCell celdaTablaNroCliente = new PdfPCell(tablaNroCliente);
		celdaTablaNroCliente.setBorder(Rectangle.NO_BORDER);

		tablaGeneralDatosDepositante.addCell(celdaTablaNroCliente);

		documento.add(tablaGeneralDatosDepositante);
	}

	/**
	 * Método encargado de crear los titulos de las 2 tablas con los datos del
	 * Detalle del Pago y Detalle del Déposito (NO Cheque de Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTituloDetallePagoDeposito(Document documento)
			throws DocumentException, IOException {

		float[] medidaCeldasTablaDetalles = { .34f, .01f, .56f };
		PdfPTable tablaDetalles = new PdfPTable(medidaCeldasTablaDetalles);
		tablaDetalles.setWidthPercentage(95);

		PdfPTable tablaDetallesDelPago = new PdfPTable(1);
		tablaDetallesDelPago.addCell(crearCelda(DETALLE_DEL_PAGO,ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPCell celdaTablaDetallesDelPago = new PdfPCell(tablaDetallesDelPago);
		celdaTablaDetallesDelPago.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablaDetallesDelDeposito = new PdfPTable(1);
		tablaDetallesDelDeposito.addCell(crearCelda(DETALLE_DEL_DEPOSITO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO,	ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPCell celdaTablaDetallesDelDeposito = new PdfPCell(tablaDetallesDelDeposito);
		celdaTablaDetallesDelDeposito.setBorder(Rectangle.NO_BORDER);

		tablaDetalles.addCell(celdaTablaDetallesDelPago);
		tablaDetalles.addCell(celdaMedio);
		tablaDetalles.addCell(celdaTablaDetallesDelDeposito);
		tablaDetalles.setSpacingAfter(5f);

		documento.add(tablaDetalles);
	}

	/**
	 * Método encargado de crear las tablas para llenar con los datos de
	 * Detalles del Pago y del Deposito (NO Cheque de Pago Diferido)
	 * 
	 * @param documento
	 * @param listaDetalleDelPago
	 * @param listaDetalleDeposito
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaDetallePagoDeposito(Document documento,
			ArrayList<ListaDetalleDelPago> listaDetalleDelPago,
			ArrayList<ListaDetalleDelDeposito> listaDetalleDeposito)
			throws DocumentException, IOException {

		float[] medidaCeldasTablaGeneralDatosPagoDeposito = { .34f, .01f, .56f };
		PdfPTable tablaGeneralDatosPagoDeposito = new PdfPTable(medidaCeldasTablaGeneralDatosPagoDeposito);
		tablaGeneralDatosPagoDeposito.setWidthPercentage(95);
		int cantidadBancos = 5;
		int listaConMasResultados;
		listaConMasResultados = Math.max(listaDetalleDelPago.size(), listaDetalleDeposito.size());
		if (listaConMasResultados > cantidadBancos) {
			cantidadBancos = listaConMasResultados;
		}
		PdfPTable tablaDetallesDelPago = new PdfPTable(3);
		tablaDetallesDelPago.addCell(crearCelda(DATO2, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelPago.addCell(crearCelda(DATO3, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelPago.addCell(crearCelda(IMPORTE, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		for (int i = 0; i < (cantidadBancos - listaDetalleDelPago.size()); i++) {
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		}

		PdfPCell celdaTablaDetallesDelPago = new PdfPCell(tablaDetallesDelPago);
		celdaTablaDetallesDelPago.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablaDetallesDelDeposito = new PdfPTable(3);
		tablaDetallesDelDeposito.addCell(crearCelda(BANCO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelDeposito.addCell(crearCelda(CHEQUE_NUMERO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelDeposito.addCell(crearCelda(IMPORTE, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		for (ListaDetalleDelDeposito detalleDelDeposito : listaDetalleDeposito) {
			tablaDetallesDelDeposito.addCell(crearCelda(
					detalleDelDeposito.getBanco(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			String numeroCheque = ESPACIO_BLANCO;
			if (detalleDelDeposito.getChequeNumero() != null) {
				numeroCheque = detalleDelDeposito.getChequeNumero().toString();
			}
			tablaDetallesDelDeposito.addCell(crearCelda(numeroCheque, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(Utilidad.formatCurrency(detalleDelDeposito.getImporte(), 2), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA,	blackFontTitulos));
		}

		for (int i = 0; i < (cantidadBancos - listaDetalleDeposito.size()); i++) {
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		}

		PdfPCell celdaTablaDetallesDelDeposito = new PdfPCell(tablaDetallesDelDeposito);
		celdaTablaDetallesDelDeposito.setBorder(Rectangle.NO_BORDER);

		tablaGeneralDatosPagoDeposito.addCell(celdaTablaDetallesDelPago);
		tablaGeneralDatosPagoDeposito.addCell(celdaMedio);
		tablaGeneralDatosPagoDeposito.addCell(celdaTablaDetallesDelDeposito);
		tablaGeneralDatosPagoDeposito.setSpacingAfter(5f);

		documento.add(tablaGeneralDatosPagoDeposito);
	}

	/**
	 * Método encargado de crear las tablas para llenar con los datos de
	 * Detalles del Pago y del Deposito (Para Cheque de Pago Diferido)
	 * 
	 * @param documento
	 * @param listaDetalleDelPago
	 * @param listaDetalleDeposito
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaDetallePagoDepositoPD(Document documento, ArrayList<ListaDetalleDelPago> listaDetalleDelPago, ArrayList<ListaDetalleDelDeposito> listaDetalleDeposito)
			throws DocumentException, IOException {

		float[] medidaCeldasTablaGeneralDatosPagoDeposito = { .34f, .01f, .56f };
		PdfPTable tablaGeneralDatosPagoDeposito = new PdfPTable(medidaCeldasTablaGeneralDatosPagoDeposito);
		tablaGeneralDatosPagoDeposito.setWidthPercentage(95);
		int cantidadBancos = 5;
		int listaConMasResultados;
		listaConMasResultados = Math.max(listaDetalleDelPago.size(), listaDetalleDeposito.size());
		if (listaConMasResultados > cantidadBancos) {
			cantidadBancos = listaConMasResultados;
		}
		PdfPTable tablaDetallesDelPago = new PdfPTable(3);
		tablaDetallesDelPago.addCell(crearCelda(DATO2, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelPago.addCell(crearCelda(DATO3, ANCHO_BORDE_UNO,	ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelPago.addCell(crearCelda(IMPORTE, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		for (int i = 0; i < (cantidadBancos - listaDetalleDelPago.size()); i++) {
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelPago.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		}

		PdfPCell celdaTablaDetallesDelPago = new PdfPCell(tablaDetallesDelPago);
		celdaTablaDetallesDelPago.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMedio = new PdfPCell();
		celdaMedio.setBorder(Rectangle.NO_BORDER);

		PdfPTable tablaDetallesDelDeposito = new PdfPTable(4);
		tablaDetallesDelDeposito.addCell(crearCelda(BANCO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		tablaDetallesDelDeposito.addCell(crearCelda(CHEQUE_NUMERO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		PdfPTable tablaTituloFechaVencimiento = new PdfPTable(1);
		tablaTituloFechaVencimiento.addCell(crearCelda(FECHA_VENCIMIENTO, ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_ARRIBA, ALINEACION_VERTICAL_CENTRADA, blackFontFechaVencimiento));

		PdfPTable tablaFechaVencimientoNumerico = new PdfPTable(3);
		tablaFechaVencimientoNumerico.addCell(crearCelda(DIA, ANCHO_BORDE_CERO,	ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontFechaVencimiento));
		tablaFechaVencimientoNumerico.addCell(crearCelda(MES, ANCHO_BORDE_CERO,	ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontFechaVencimiento));
		tablaFechaVencimientoNumerico.addCell(crearCelda(ANIO, ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontFechaVencimiento));

		PdfPCell celdaTablaFechaVencimientoNumerico = new PdfPCell(tablaFechaVencimientoNumerico);
		celdaTablaFechaVencimientoNumerico.setBorder(Rectangle.NO_BORDER);

		tablaTituloFechaVencimiento.addCell(celdaTablaFechaVencimientoNumerico);

		PdfPCell celdaTablaTituloFechaVencimiento = new PdfPCell(tablaTituloFechaVencimiento);
		celdaTablaTituloFechaVencimiento.setBorder(Rectangle.TOP);
		celdaTablaTituloFechaVencimiento.setBorderWidth(ANCHO_BORDE_UNO);

		tablaDetallesDelDeposito.addCell(celdaTablaTituloFechaVencimiento);
		tablaDetallesDelDeposito.addCell(crearCelda(IMPORTE, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

		for (ListaDetalleDelDeposito detalleDelDeposito : listaDetalleDeposito) {
			tablaDetallesDelDeposito.addCell(crearCelda(detalleDelDeposito.getBanco(), ANCHO_BORDE_UNO,	ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(detalleDelDeposito.getChequeNumero().toString(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

			PdfPTable tablaFechaVencimiento = new PdfPTable(3);
			tablaFechaVencimiento.addCell(crearCelda(detalleDelDeposito.getDiaVencimiento(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaFechaVencimiento.addCell(crearCelda(detalleDelDeposito.getMesVencimiento(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaFechaVencimiento.addCell(crearCelda(detalleDelDeposito.getAnioVencimiento(), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));

			PdfPCell celdaTablaFechaVencimiento = new PdfPCell(tablaFechaVencimiento);
			celdaTablaFechaVencimiento.setBorderWidth(ANCHO_BORDE_UNO);
			celdaTablaFechaVencimiento.setVerticalAlignment(ALINEACION_HORIZONTAL_MEDIO);
			celdaTablaFechaVencimiento.setHorizontalAlignment(ALINEACION_VERTICAL_CENTRADA);

			tablaDetallesDelDeposito.addCell(celdaTablaFechaVencimiento);
			tablaDetallesDelDeposito.addCell(crearCelda(Utilidad.formatCurrency(detalleDelDeposito.getImporte(), 2), ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		}

		for (int i = 0; i < (cantidadBancos - listaDetalleDeposito.size()); i++) {
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO,	ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
			tablaDetallesDelDeposito.addCell(crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_UNO, ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA, blackFontTitulos));
		}

		PdfPCell celdaTablaDetallesDelDeposito = new PdfPCell(tablaDetallesDelDeposito);
		celdaTablaDetallesDelDeposito.setBorder(Rectangle.NO_BORDER);

		tablaGeneralDatosPagoDeposito.addCell(celdaTablaDetallesDelPago);
		tablaGeneralDatosPagoDeposito.addCell(celdaMedio);
		tablaGeneralDatosPagoDeposito.addCell(celdaTablaDetallesDelDeposito);
		tablaGeneralDatosPagoDeposito.setSpacingAfter(5f);

		documento.add(tablaGeneralDatosPagoDeposito);
	}
	
	/**
	 * Método encargado de crear la tabla con el Total del Depósito (NO Cheque
	 * de Pago Diferido)
	 * 
	 * @param documento
	 * @param totalDeposito
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTotalDeposito(Document documento,
			BigDecimal totalDeposito) throws DocumentException, IOException {

		PdfPTable tablaGeneralTotalDeposito = new PdfPTable(4);
		tablaGeneralTotalDeposito.setWidthPercentage(95);

		PdfPCell celdaIzquierda1 = new PdfPCell();
		celdaIzquierda1.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaIzquierda2 = new PdfPCell();
		celdaIzquierda2.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaTotalDelDeposito = crearCelda(TOTAL_DEL_DEPOSITO,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_DERECHA, blackFontTitulos);
		celdaTotalDelDeposito.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaTotalNumerico = crearCelda(
				Utilidad.formatCurrency(totalDeposito, 2), ANCHO_BORDE_UNO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_DERECHA,
				blackFontTitulos);

		tablaGeneralTotalDeposito.addCell(celdaIzquierda1);
		tablaGeneralTotalDeposito.addCell(celdaIzquierda2);
		tablaGeneralTotalDeposito.addCell(celdaTotalDelDeposito);
		tablaGeneralTotalDeposito.addCell(celdaTotalNumerico);

		documento.add(tablaGeneralTotalDeposito);
	}

	/**
	 * Método encargado de crear la tabla con el Total del Depósito (Para Cheque
	 * de Pago Diferido)
	 * 
	 * @param documento
	 * @param totalDeposito
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void crearTablaTotalDepositoPD(Document documento,
			BigDecimal totalDeposito) throws DocumentException, IOException {

		float[] medidaCeldasTablaGeneralTotalDeposito = { .70f, .20f, .60f,
				.30f };
		PdfPTable tablaGeneralTotalDeposito = new PdfPTable(
				medidaCeldasTablaGeneralTotalDeposito);
		tablaGeneralTotalDeposito.setWidthPercentage(95);

		PdfPCell celdaIzquierdaCPD = crearCelda(C_P_D, ANCHO_BORDE_CERO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA,
				blackFontPieDePaginaCPD);
		celdaIzquierdaCPD.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaIzquierdaMensajeImportante = crearCelda(
				MENSAJE_IMPORTANTE_PD, ANCHO_BORDE_CERO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_CENTRADA,
				blackFontPieDePagina);
		celdaIzquierdaMensajeImportante.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaTotalDelDeposito = crearCelda(TOTAL_DEL_DEPOSITO,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_DERECHA, blackFontTitulos);
		celdaTotalDelDeposito.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaTotalNumerico = crearCelda(
				Utilidad.formatCurrency(totalDeposito, 2), ANCHO_BORDE_UNO,
				ALINEACION_HORIZONTAL_MEDIO, ALINEACION_VERTICAL_DERECHA,
				blackFontTitulos);

		tablaGeneralTotalDeposito.addCell(celdaIzquierdaCPD);
		tablaGeneralTotalDeposito.addCell(celdaIzquierdaMensajeImportante);
		tablaGeneralTotalDeposito.addCell(celdaTotalDelDeposito);
		tablaGeneralTotalDeposito.addCell(celdaTotalNumerico);

		documento.add(tablaGeneralTotalDeposito);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (NO Cheque de
	 * Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaMensajeTicket(Document documento)
			throws DocumentException {

		PdfPTable tablaMensajeTicket = new PdfPTable(1);
		tablaMensajeTicket.setWidthPercentage(95);

		PdfPCell celdaMensajeTicket = crearCelda(MENSAJE_TICKET,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_IZQUIERDA, blackFontPieDePagina);
		celdaMensajeTicket.setBorder(Rectangle.NO_BORDER);

		tablaMensajeTicket.addCell(celdaMensajeTicket);
		tablaMensajeTicket.setSpacingAfter(3f);

		documento.add(tablaMensajeTicket);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje de Ticket (Para Cheque
	 * de Pago Diferido)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaMensajeTicketPD(Document documento)
			throws DocumentException {

		float[] medidaCeldasTablaGeneralTotalDeposito = { .180f, .06f };
		PdfPTable tablaMensajeTicket = new PdfPTable(
				medidaCeldasTablaGeneralTotalDeposito);
		tablaMensajeTicket.setWidthPercentage(95);

		PdfPCell celdaVacia = new PdfPCell();
		celdaVacia.setBorder(Rectangle.NO_BORDER);

		PdfPCell celdaMensajeTicket = crearCelda(MENSAJE_TICKET,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_DERECHA, blackFontPieDePagina);
		celdaMensajeTicket.setBorder(Rectangle.NO_BORDER);

		tablaMensajeTicket.addCell(celdaVacia);
		tablaMensajeTicket.addCell(celdaMensajeTicket);
		tablaMensajeTicket.setSpacingAfter(3f);

		documento.add(tablaMensajeTicket);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje Importante (Para Cheque
	 * de Pago Diferido y NO)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaMensajeImportante(Document documento)
			throws DocumentException {

		PdfPTable tablaMensajeTicket = new PdfPTable(1);
		tablaMensajeTicket.setWidthPercentage(95);

		PdfPCell celdaMensajeTicket = crearCelda(MENSAJE_IMPORTANTE,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_IZQUIERDA, blackFontPieDePagina);
		celdaMensajeTicket.setBorder(Rectangle.NO_BORDER);

		tablaMensajeTicket.addCell(celdaMensajeTicket);

		documento.add(tablaMensajeTicket);
	}

	/**
	 * Método encargado de crear la tabla con el Mensaje IVA (Para Cheque de
	 * Pago Diferido y NO)
	 * 
	 * @param documento
	 * @throws DocumentException
	 */
	private void crearTablaMensajeIVA(Document documento, String categoriaIVA,
			String categoriaIIBB, String cuit, String codigoIIBB)
			throws DocumentException {

		PdfPTable tablaMensajeTicket = new PdfPTable(1);
		tablaMensajeTicket.setWidthPercentage(95);

		PdfPCell celdaMensajeTicket = crearCelda(categoriaIVA + " - C.U.I.T.:"
				+ cuit + " - " + categoriaIIBB + " N°:" + codigoIIBB,
				ANCHO_BORDE_CERO, ALINEACION_HORIZONTAL_MEDIO,
				ALINEACION_VERTICAL_DERECHA, blackFontPieDePagina);
		celdaMensajeTicket.setBorder(Rectangle.NO_BORDER);

		tablaMensajeTicket.addCell(celdaMensajeTicket);

		documento.add(tablaMensajeTicket);
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
	protected PdfPCell crearCelda(String frase, Integer borderWidth,
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
	protected void addEmptyLine(Document documento, int numeroCeldasVacias)
			throws DocumentException {
		for (int i = 0; i < numeroCeldasVacias; i++) {

			PdfPTable tablaVacia = new PdfPTable(1);
			tablaVacia.setWidthPercentage(95);

			PdfPCell celdaVacia = crearCelda(ESPACIO_BLANCO, ANCHO_BORDE_CERO,
					ALINEACION_HORIZONTAL_ABAJO, ALINEACION_VERTICAL_IZQUIERDA,
					blackFontTitulos);
			celdaVacia.setBorder(Rectangle.NO_BORDER);

			tablaVacia.addCell(celdaVacia);

			documento.add(tablaVacia);
		}

	}
	
	/**
	 * Agrega al documento los datos y estructura de boleta correspondiente según se trate
	 * de una voltea con o sin valor.
	 * @param documento
	 * @param pw
	 * @throws DocumentException
	 * @throws IOException
	 * @throws SQLException
	 * @throws PersistenciaExcepcion 
	 */
	private void generarContenidoPDF(Document documento, PdfWriter pw)
			 throws DocumentException, IOException,	SQLException, PersistenciaExcepcion 
	{
		
		if (Validaciones.isCollectionNotEmpty(valorModelo)) {
			
			// Esta sección es para Boletas con Valor
	
			Iterator<ShvValValor> it = valorModelo.iterator();
			while(it.hasNext()){
				ShvValValor shvValor = it.next();
			
				generarBoletasConValorPorDuplicadoPDF(pw, documento, shvValor, false);
				addEmptyLine(documento, 3);
				addLineaTroquelada(documento);
				addEmptyLine(documento, 3);
				generarBoletasConValorPorDuplicadoPDF(pw, documento, shvValor, true);
				
				if(it.hasNext()){
					documento.newPage();
				}
			}
		
		}else{
			if(Validaciones.isCollectionNotEmpty(boletaSinValorModelo)){

				// Esta sección es para Boletas sin Valor

				Iterator<ShvBolBoletaSinValor> iteratorBoleta = boletaSinValorModelo.iterator();
				while(iteratorBoleta.hasNext()){
					ShvBolBoletaSinValor boleta = iteratorBoleta.next();
					
					generarBoletasSinValorPorDuplicadoPDF(pw, documento, boleta, false);
					addEmptyLine(documento, 3);
					addLineaTroquelada(documento);
					addEmptyLine(documento, 3);
					generarBoletasSinValorPorDuplicadoPDF(pw, documento, boleta, true);
					
					if(iteratorBoleta.hasNext()){
						documento.newPage();
					}
				}
			}else{
				if(Validaciones.isCollectionNotEmpty(valorModeloPdf)){
			
				Iterator<VistaSoporteResultadoBusquedaValor> it = valorModeloPdf.iterator();
				while(it.hasNext()){
					VistaSoporteResultadoBusquedaValor valor = it.next();
				
					generarBoletasConValorPorDuplicadoPDF(pw, documento, valor, false);
					addEmptyLine(documento, 3);
					addLineaTroquelada(documento);
					addEmptyLine(documento, 3);
					generarBoletasConValorPorDuplicadoPDF(pw, documento, valor, true);
					
					if(it.hasNext()){
						documento.newPage();
					}
					}
				}
			}
		}
	}
	private void generarBoletasConValorPorDuplicadoPDF(PdfWriter pw, Document documento, ShvValValor shvValor, final boolean esDuplicado)
			 throws DocumentException, IOException,	SQLException{
		double fechaHoraInicioNanoTime = System.nanoTime();
		pw.setPageEvent(new PdfPageEventHelper(){
			private List<ShvValValor> valores = valorModelo;
			private int count = 0;
			private int maxCount = valores.size();
			
			@Override
		    public void onEndPage(PdfWriter writer, Document document) {
				if (maxCount > count) {
				
					ShvValValor valor = valores.get(count);
					if (valor.getValorBoletaDepositoChequePD() != null) {
						if (esDuplicado) {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_LA_SUCURSAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_2_Y, ROTACION);						
						} else {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_CASA_CENTRAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_1_Y, ROTACION);							
						}
					}
					else {
						if (esDuplicado) {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_LA_SUCURSAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_2_Y, ROTACION);
						} else {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_OF_SERVICIOS_A_EMPRESAS, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_1_Y, ROTACION);
						}
					}
				}
				count++;
			}
		});
		/*Armado de Logo*/
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 1 ", fechaHoraInicioNanoTime);
		byte[] logoBlob = shvValor.getAcuerdo().getBancoCuenta().getBanco().getLogo();
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 2 ", fechaHoraInicioNanoTime);
		ImageIcon icon = new ImageIcon(logoBlob);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 3 ", fechaHoraInicioNanoTime);
		java.awt.Image awtImage = icon.getImage();
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 4 ", fechaHoraInicioNanoTime);
		java.awt.Image awtImageR = awtImage.getScaledInstance(130, 30, java.awt.Image.SCALE_SMOOTH);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 5 ", fechaHoraInicioNanoTime);
		Image logo = com.itextpdf.text.Image.getInstance(awtImageR, null);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 6", fechaHoraInicioNanoTime);
		
		/*Nombre Depositante*/
		String nombreDepositante = shvValor.getRazonSocialClientePerfil();
		/*Numero Cliente*/
		Long numeroCliente = shvValor.getIdClienteLegado();
		/*Nombre Empresa*/
		String nombreEmpresa = shvValor.getEmpresa().getDescripcion();
		
		/*Fecha Actual*/
		Date fechaActual = new Date();
		SimpleDateFormat formateoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActualFormateada = formateoFecha.format(fechaActual);
		String diaActual = "";
		String mesActual = "";
		String anioActual = "";
		StringTokenizer tok = new StringTokenizer(fechaActualFormateada, "/");
		diaActual = tok.nextToken();
		mesActual = tok.nextToken();
		anioActual = tok.nextToken();
		
		/*CUIT*/
		String cuit = shvValor.getEmpresa().getCuit();
		/*Categoria IVA*/
		String categoriaIVA = shvValor.getAcuerdo().getBancoCuenta().getBanco().getCategoriaIVA();
		/*Categoria IBB*/
		String categoriaIIBB = shvValor.getAcuerdo().getBancoCuenta().getBanco().getCategoriaIibb();
		String codigoIIBB = shvValor.getAcuerdo().getBancoCuenta().getBanco().getCodigoIibb();
		/*Producto*/
		String producto = VALOR_PRODUCTO;
		/*ID Acuerdo*/
		Integer acuerdo = shvValor.getAcuerdo().getIdAcuerdo();
		
		/*Importe*/
		BigDecimal importeTotal = shvValor.getImporte();
		BigDecimal totalDelDeposito = new BigDecimal(0.00);
		
		/*Lista*/
		Boolean chequePD = false;
		ArrayList<ListaDetalleDelPago> listaDetalleDelPago = new ArrayList<ListaDetalleDelPago>();
		ArrayList<ListaDetalleDelDeposito> listaDetalleDeposito = new ArrayList<ListaDetalleDelDeposito>();
		ListaDetalleDelPago listaDetalleDelPagoItera = new ListaDetalleDelPago();
					
		listaDetalleDelPagoItera.setDato1(ESPACIO_BLANCO);
		listaDetalleDelPagoItera.setDato2(ESPACIO_BLANCO);
		importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
		listaDetalleDelPagoItera.setImporte(importeTotal);
		listaDetalleDelPago.add(listaDetalleDelPagoItera);
		ListaDetalleDelDeposito listaDetalleDelDepositoItera = new ListaDetalleDelDeposito();

		/*CASO CHEQUE DIFERIDO*/
		if (shvValor.getValorBoletaDepositoChequePD() != null) {
			

			/*BOLETA*/
			Long idBoleta = shvValor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta();
			/*Fila Logo*/
			crearTablaCobranzaFechaLogoPD(documento, diaActual, mesActual, anioActual, logo);
			/*Empresa*/
			crearTablaEmpresaPD(documento, nombreEmpresa);
			crearTablaCodigoBoletaPD(documento, idBoleta.toString(), cuit, producto, acuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(shvValor.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion());
			listaDetalleDelDepositoItera.setChequeNumero(shvValor.getValorBoletaDepositoChequePD().getNumeroCheque());
			/*Fecha Vencimiento*/
			Date fechaVencimiento = shvValor.getValorBoletaDepositoChequePD().getFechaVencimiento();
			String fechaVencimientoFormateada = formateoFecha.format(fechaVencimiento);
			StringTokenizer tokVen = new StringTokenizer(fechaVencimientoFormateada, "/");
			String diaVencimiento = tokVen.nextToken();
			String mesVencimiento = tokVen.nextToken();
			String anioVencimiento = tokVen.nextToken();
			listaDetalleDelDepositoItera.setDiaVencimiento(String.valueOf(diaVencimiento));
			listaDetalleDelDepositoItera.setMesVencimiento(String.valueOf(mesVencimiento));
			listaDetalleDelDepositoItera.setAnioVencimiento(String.valueOf(anioVencimiento));
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);
			chequePD = true;
		
		/*CASO CHEQUE*/
		} else if (shvValor.getValorBoletaDepositoCheque() != null) {
			

			/*BOLETA*/
			Long idBoleta = shvValor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta();
			/*Fila Logo*/
			crearTablaCobranzaLogo(documento, logo);
			/*Empresa*/
			crearTablaEmpresaFecha(documento, nombreEmpresa, diaActual, mesActual, anioActual);
			crearTablaCodigoBoleta(documento, idBoleta.toString(), cuit, producto, acuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(shvValor.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion());
			listaDetalleDelDepositoItera.setChequeNumero(shvValor.getValorBoletaDepositoCheque().getNumeroCheque());
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);

		/*CASO Efectivo*/
		} else if (shvValor.getValorBoletaEfectivo() != null) {
			/*BOLETA*/
			Long idBoleta = shvValor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta();
			/*Fila Logo*/
			crearTablaCobranzaLogo(documento, logo);
			/*Empresa*/
			crearTablaEmpresaFecha(documento, nombreEmpresa, diaActual, mesActual, anioActual);
			crearTablaCodigoBoleta(documento, idBoleta.toString(), cuit, producto, acuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(ESPACIO_BLANCO);
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);
		}

		
		addEmptyLine(documento, 1);
		crearTablaTituloDatosDepositante(documento);
		crearTablaDatosDepositante(documento, nombreDepositante, numeroCliente.toString());
		addEmptyLine(documento, 1);
		crearTablaTituloDetallePagoDeposito(documento);
		if (chequePD) {
			crearTablaDetallePagoDepositoPD(documento, listaDetalleDelPago,	listaDetalleDeposito);
			totalDelDeposito = totalDelDeposito.setScale(2,	RoundingMode.CEILING);
			crearTablaTotalDepositoPD(documento, totalDelDeposito);
			crearTablaMensajeTicketPD(documento);
		} else {
			crearTablaDetallePagoDeposito(documento, listaDetalleDelPago, listaDetalleDeposito);
			totalDelDeposito = totalDelDeposito.setScale(2,	RoundingMode.CEILING);
			crearTablaTotalDeposito(documento, totalDelDeposito);
			crearTablaMensajeTicket(documento);
		}
		crearTablaMensajeImportante(documento);
		crearTablaMensajeIVA(documento, categoriaIVA, categoriaIIBB, cuit, codigoIIBB);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - Total", fechaHoraInicioNanoTime);
		
	}
	private void generarBoletasConValorPorDuplicadoPDF(PdfWriter pw, Document documento, VistaSoporteResultadoBusquedaValor shvValor, final boolean esDuplicado)
			 throws DocumentException, IOException,	SQLException, PersistenciaExcepcion{
		double fechaHoraInicioNanoTime = System.nanoTime();
		pw.setPageEvent(new PdfPageEventHelper(){
			private List<VistaSoporteResultadoBusquedaValor> valores = valorModeloPdf;
			private int count = 0;
			private int maxCount = valores.size();
		
			@Override
		    public void onEndPage(PdfWriter writer, Document document) {
				if (maxCount > count) {
				
					VistaSoporteResultadoBusquedaValor valor = valores.get(count);
					if (valor.getTipoValor().equals(TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion())) {
						if (esDuplicado) {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_LA_SUCURSAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_2_Y, ROTACION);						
						} else {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_CASA_CENTRAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_1_Y, ROTACION);							
						}
					}
					else {
						if (esDuplicado) {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_LA_SUCURSAL, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_2_Y, ROTACION);
						} else {
							ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_OF_SERVICIOS_A_EMPRESAS, blackFontMargen), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_1_Y, ROTACION);
						}
					}
				}
				count++;
			}
		});
		 ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(shvValor.getIdAcuerdo());
		/*Armado de Logo*/
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 1 ", fechaHoraInicioNanoTime);
		byte[] logoBlob = acuerdo.getBancoCuenta().getBanco().getLogo();
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 2 ", fechaHoraInicioNanoTime);
		ImageIcon icon = new ImageIcon(logoBlob);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 3 ", fechaHoraInicioNanoTime);
		java.awt.Image awtImage = icon.getImage();
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 4 ", fechaHoraInicioNanoTime);
		java.awt.Image awtImageR = awtImage.getScaledInstance(130, 30, java.awt.Image.SCALE_SMOOTH);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 5 ", fechaHoraInicioNanoTime);
		Image logo = com.itextpdf.text.Image.getInstance(awtImageR, null);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - armado de logo - 6", fechaHoraInicioNanoTime);
		
		/*Nombre Depositante*/
		String nombreDepositante = shvValor.getRazonSocialClienteAgrupador();
		/*Numero Cliente*/
		String numeroCliente = shvValor.getIdClienteLegado();
		/*Nombre Empresa*/
		String nombreEmpresa = shvValor.getEmpresa();
		
		/*Fecha Actual*/
		Date fechaActual = new Date();
		SimpleDateFormat formateoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActualFormateada = formateoFecha.format(fechaActual);
		String diaActual = "";
		String mesActual = "";
		String anioActual = "";
		StringTokenizer tok = new StringTokenizer(fechaActualFormateada, "/");
		diaActual = tok.nextToken();
		mesActual = tok.nextToken();
		anioActual = tok.nextToken();
		
		/*CUIT*/
		String cuit = acuerdo.getBancoCuenta().getBanco().getEmpresa().getCuit();
		/*Categoria IVA*/
		String categoriaIVA = acuerdo.getBancoCuenta().getBanco().getCategoriaIVA();
		/*Categoria IBB*/
		String categoriaIIBB = acuerdo.getBancoCuenta().getBanco().getCategoriaIibb();
		String codigoIIBB = acuerdo.getBancoCuenta().getBanco().getCodigoIibb();
		/*Producto*/
		String producto = VALOR_PRODUCTO;
		/*ID Acuerdo*/
		Integer idAcuerdo= Integer.valueOf(shvValor.getIdAcuerdo());
		
		/*Importe*/
		BigDecimal importeTotal = shvValor.getImporte();
		BigDecimal totalDelDeposito = new BigDecimal(0.00);
		
		/*Lista*/
		Boolean chequePD = false;
		ArrayList<ListaDetalleDelPago> listaDetalleDelPago = new ArrayList<ListaDetalleDelPago>();
		ArrayList<ListaDetalleDelDeposito> listaDetalleDeposito = new ArrayList<ListaDetalleDelDeposito>();
		ListaDetalleDelPago listaDetalleDelPagoItera = new ListaDetalleDelPago();
					
		listaDetalleDelPagoItera.setDato1(ESPACIO_BLANCO);
		listaDetalleDelPagoItera.setDato2(ESPACIO_BLANCO);
		importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
		listaDetalleDelPagoItera.setImporte(importeTotal);
		listaDetalleDelPago.add(listaDetalleDelPagoItera);
		ListaDetalleDelDeposito listaDetalleDelDepositoItera = new ListaDetalleDelDeposito();

		/*CASO CHEQUE DIFERIDO*/
		if (shvValor.getTipoValor().equals(TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion())) {
			

			/*BOLETA*/
			Long idBoleta = Long.valueOf(shvValor.getNroBoleta());
			/*Fila Logo*/
			crearTablaCobranzaFechaLogoPD(documento, diaActual, mesActual, anioActual, logo);
			/*Empresa*/
			crearTablaEmpresaPD(documento, nombreEmpresa);
			crearTablaCodigoBoletaPD(documento, idBoleta.toString(), cuit, producto, idAcuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(shvValor.getDescripcionBancoOrigen());
			listaDetalleDelDepositoItera.setChequeNumero(Long.valueOf(shvValor.getReferenciaValor()));
			/*Fecha Vencimiento*/
			Date fechaVencimiento = shvValor.getFechaVencimiento();
			String fechaVencimientoFormateada = formateoFecha.format(fechaVencimiento);
			StringTokenizer tokVen = new StringTokenizer(fechaVencimientoFormateada, "/");
			String diaVencimiento = tokVen.nextToken();
			String mesVencimiento = tokVen.nextToken();
			String anioVencimiento = tokVen.nextToken();
			listaDetalleDelDepositoItera.setDiaVencimiento(String.valueOf(diaVencimiento));
			listaDetalleDelDepositoItera.setMesVencimiento(String.valueOf(mesVencimiento));
			listaDetalleDelDepositoItera.setAnioVencimiento(String.valueOf(anioVencimiento));
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);
			chequePD = true;
		
		/*CASO CHEQUE*/
		} else if (shvValor.getTipoValor().equals(TipoValorEnum.CHEQUE.getDescripcion())) {
			

			/*BOLETA*/
			Long idBoleta = Long.valueOf(shvValor.getNroBoleta());
			/*Fila Logo*/
			crearTablaCobranzaLogo(documento, logo);
			/*Empresa*/
			crearTablaEmpresaFecha(documento, nombreEmpresa, diaActual, mesActual, anioActual);
			crearTablaCodigoBoleta(documento, idBoleta.toString(), cuit, producto, idAcuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(shvValor.getDescripcionBancoOrigen());
			listaDetalleDelDepositoItera.setChequeNumero(Long.valueOf(shvValor.getReferenciaValor()));
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);

		/*CASO Efectivo*/
		} else if (shvValor.getTipoValor().equals(TipoValorEnum.EFECTIVO.getDescripcion())) {
			/*BOLETA*/
			Long idBoleta = Long.valueOf(shvValor.getNroBoleta());
			/*Fila Logo*/
			crearTablaCobranzaLogo(documento, logo);
			/*Empresa*/
			crearTablaEmpresaFecha(documento, nombreEmpresa, diaActual, mesActual, anioActual);
			crearTablaCodigoBoleta(documento, idBoleta.toString(), cuit, producto, idAcuerdo);
			/*Banco Cheque*/
			listaDetalleDelDepositoItera.setBanco(ESPACIO_BLANCO);
			/*Importe Total*/
			totalDelDeposito = totalDelDeposito.add(importeTotal);
			importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
			listaDetalleDelDepositoItera.setImporte(importeTotal);
			/*Final*/
			listaDetalleDeposito.add(listaDetalleDelDepositoItera);
		}

		
		addEmptyLine(documento, 1);
		crearTablaTituloDatosDepositante(documento);
		crearTablaDatosDepositante(documento, nombreDepositante, numeroCliente.toString());
		addEmptyLine(documento, 1);
		crearTablaTituloDetallePagoDeposito(documento);
		if (chequePD) {
			crearTablaDetallePagoDepositoPD(documento, listaDetalleDelPago,	listaDetalleDeposito);
			totalDelDeposito = totalDelDeposito.setScale(2,	RoundingMode.CEILING);
			crearTablaTotalDepositoPD(documento, totalDelDeposito);
			crearTablaMensajeTicketPD(documento);
		} else {
			crearTablaDetallePagoDeposito(documento, listaDetalleDelPago, listaDetalleDeposito);
			totalDelDeposito = totalDelDeposito.setScale(2,	RoundingMode.CEILING);
			crearTablaTotalDeposito(documento, totalDelDeposito);
			crearTablaMensajeTicket(documento);
		}
		crearTablaMensajeImportante(documento);
		crearTablaMensajeIVA(documento, categoriaIVA, categoriaIIBB, cuit, codigoIIBB);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarBoletasConValorPorDuplicadoPDF - Total", fechaHoraInicioNanoTime);
		
	}
	
	private void generarBoletasSinValorPorDuplicadoPDF(PdfWriter pw, Document documento, ShvBolBoletaSinValor boleta, final boolean esDuplicado)
			throws DocumentException, IOException,	SQLException 
	{
		pw.setPageEvent(new PdfPageEventHelper(){@Override
			public void onEndPage(PdfWriter writer, Document document) {
			if (esDuplicado) {
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_LA_SUCURSAL, new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL)), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_2_Y, ROTACION);
			} else {
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP, new Phrase(PARA_OF_SERVICIOS_A_EMPRESAS, new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL)), document.leftMargin(), document.top() - LEGAL_VERTICAL_TEXT_1_Y, ROTACION);
			}
		}
		});
		
		/*Lista*/
		ArrayList<ListaDetalleDelPago> listaDetalleDelPago = new ArrayList<ListaDetalleDelPago>();
		ArrayList<ListaDetalleDelDeposito> listaDetalleDeposito = new ArrayList<ListaDetalleDelDeposito>();
		ListaDetalleDelDeposito listaDetalleDelDepositoItera = new ListaDetalleDelDeposito();
		

		/*BOLETA*/
		Long idBoleta = boleta.getBoleta().getNumeroBoleta();
		/*Nombre Depositante*/
		String nombreDepositante = boleta.getRazonSocial();
		/*Numero Cliente*/
		Long numeroCliente = boleta.getIdClienteLegado();
		/*Nombre Empresa*/
		String nombreEmpresa = boleta.getEmpresa().getDescripcion();
		/*Fecha Actual*/
		Calendar caledarFechaVencimiento = Calendar.getInstance();
	    caledarFechaVencimiento.setTime(new Date());
	    String diaActual = String.valueOf(caledarFechaVencimiento.get(Calendar.DAY_OF_MONTH));
	    String mesActual = String.valueOf(caledarFechaVencimiento.get(Calendar.MONTH)+1);//le sumo uno porque el nro del mes comienza en 0. Ejemplo, enero=0
	    String anioActual = String.valueOf(caledarFechaVencimiento.get(Calendar.YEAR));
		/*Producto*/
		String producto = VALOR_PRODUCTO;
		/*ID Acuerdo*/
		Integer acuerdo = boleta.getAcuerdo().getIdAcuerdo();
		/*Importe*/
		BigDecimal importeTotal = boleta.getImporte();
		BigDecimal totalDelDeposito = new BigDecimal(0.00);
		/*CUIT*/
		String cuit = boleta.getEmpresa().getCuit();
		/*Categoria IVA*/
		String categoriaIVA = boleta.getAcuerdo().getBancoCuenta().getBanco().getCategoriaIVA();
		/*Categoria IBB*/
		String categoriaIIBB = boleta.getAcuerdo().getBancoCuenta().getBanco().getCategoriaIibb();
		String codigoIIBB = boleta.getAcuerdo().getBancoCuenta().getBanco().getCodigoIibb();
		/*Armado de Logo*/
		byte[] logoBlob = boleta.getAcuerdo().getBancoCuenta().getBanco().getLogo();
		ImageIcon icon = new ImageIcon(logoBlob);
		java.awt.Image awtImage = icon.getImage();
		java.awt.Image awtImageR = awtImage.getScaledInstance(130, 30, java.awt.Image.SCALE_SMOOTH);
		Image logo = com.itextpdf.text.Image.getInstance(awtImageR, null);
		
		
		/*Fila Logo*/
		crearTablaCobranzaLogo(documento, logo);
		/*Empresa*/
		crearTablaEmpresaFecha(documento, nombreEmpresa, diaActual, mesActual, anioActual);
		crearTablaCodigoBoleta(documento, idBoleta.toString(), cuit, producto, acuerdo);
		/*Banco Cheque*/
		listaDetalleDelDepositoItera.setBanco(ESPACIO_BLANCO);
		/*Importe Total*/
		totalDelDeposito = totalDelDeposito.add(importeTotal);
		importeTotal = importeTotal.setScale(2, RoundingMode.CEILING);
		listaDetalleDelDepositoItera.setImporte(importeTotal);
		/*Final*/
		listaDetalleDeposito.add(listaDetalleDelDepositoItera);
		
		addEmptyLine(documento, 1);
		crearTablaTituloDatosDepositante(documento);
		crearTablaDatosDepositante(documento, nombreDepositante, numeroCliente.toString());
		addEmptyLine(documento, 1);
		crearTablaTituloDetallePagoDeposito(documento);
		crearTablaDetallePagoDeposito(documento, listaDetalleDelPago, listaDetalleDeposito);
		totalDelDeposito = totalDelDeposito.setScale(2,	RoundingMode.CEILING);
		crearTablaTotalDeposito(documento, totalDelDeposito);
		crearTablaMensajeTicket(documento);
		crearTablaMensajeImportante(documento);
		crearTablaMensajeIVA(documento, categoriaIVA, categoriaIIBB, cuit, codigoIIBB);
		
		addEmptyLine(documento, 1);
	}
	
	/**
	 * Agrega una líena punteada como separador.
	 * @param documento
	 * @throws DocumentException
	 */
	private void addLineaTroquelada(Document documento) throws DocumentException
	{
		DottedLineSeparator lineaPunteada = new DottedLineSeparator();
		lineaPunteada.setLineWidth(2);
		documento.add(lineaPunteada);
	}

	public void setearListaBoletasSinValorModelo(List<ShvBolBoletaSinValor> listaModelo) throws FileNotFoundException{
		this.boletaSinValorModelo = listaModelo;
		archivoPDF = new File("archivoPDF.pdf");
		archivoPDFOutput = new FileOutputStream(archivoPDF);
	}
	
	public List<ShvValValor> getValorModelo() {
		return valorModelo;
	}

	public void setValorModelo(List<ShvValValor> valorModelo) {
		this.valorModelo = valorModelo;
	}

	public FileOutputStream getArchivoPDFOutput() {
		return archivoPDFOutput;
	}

	public void setArchivoPDFOutput(FileOutputStream archivoPDFOutput) {
		this.archivoPDFOutput = archivoPDFOutput;
	}

	public File getArchivoPDF() {
		return archivoPDF;
	}

	public void setArchivoPDF(File archivoPDF) {
		this.archivoPDF = archivoPDF;
	}

	public Document getDocumento() {
		return documento;
	}

	public void setDocumento(Document documento) {
		this.documento = documento;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the valorModeloPdf
	 */
	public List<VistaSoporteResultadoBusquedaValor> getValorModeloPdf() {
		return valorModeloPdf;
	}

	/**
	 * @param valorModeloPdf the valorModeloPdf to set
	 */
	public void setValorModeloPdf(List<VistaSoporteResultadoBusquedaValor> valorModeloPdf) {
		this.valorModeloPdf = valorModeloPdf;
	}

	/**
	 * @return the acuerdoDao
	 */
	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	/**
	 * @param acuerdoDao the acuerdoDao to set
	 */
	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}


}