package ar.com.telecom.shiva.base.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;


public class Validaciones {
	/** */
	private static final String ALPHANUMERIC_REGULAR_EXPRESSION = "[a-zA-Z0-9_\\s\\.,]+";
	/** */
	private static final String NUMERIC_REGULAR_EXPRESSION = "[0-9]+";
	/** */
	private static final String NUMERIC_NATURAL_EXPRESSION = "0*[1-9]{1}[0-9]*";
	/** */
	private static final String NUMERIC_REGULAR_EXPRESSION_LENGTH_VALIDATION = "[0-9]{%s,%s}";
	/** */
	private static final String NUMERIC_REGULAR_EXPRESSION_VALIDATION = "^([1-9]{1}\\d{0,2}(\\.\\d{3}){1,3}|(\\d{1,13}))";
	
	private static final String INPORTE_DOS_DECIMALES_REGULAR_EXPRESSION = "^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:\\.[0-9]{3})*)(\\,\\d{2,2})+$";
	/** */
	private static final String EMAIL_REGULAR_EXPRESSION = "[a-zA-Z0-9]+[a-zA-Z0-9_\\-\\.]*@([a-zA-Z0-9\\-]+){1}(\\.[a-zA-Z0-9]+){1,3}";
	/** */
	private static final Character ZERO_CHARACTER = '0';
	/** */
	
	private static final String TEXTO_REGULAR_EXPRESSION2 = "a-zA-Z0-9_\\s\\{\\}\\[\\]\\(\\)\\.,;:!¡¿\\?@#%\\$\\+\\-'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ";
	
	private static final String ACEPTAR_CARACTERES_ESPECIALES_EXPRESION_REGULAR = "["+TEXTO_REGULAR_EXPRESSION2+"]*";
	
	public static final String EXCLUIR_CARACTERES_ESPECIALES_EXPRESION_REGULAR = "[^"+TEXTO_REGULAR_EXPRESSION2+"]";
															
	private static final String CUIT_REGULAR_EXPRESSION = "^[0-9]{2}-[0-9]{8}-[0-9]$";
	
	private static final String UNDERSCORE = "_";

	
	public static final String EXPRESION_REGULAR_EMPTY_NUMERICO_SERIALIZADO = "^0+$";
	public static final String EXPRESION_REGULAR_EMPTY_ALFANUMERICO_SERIALIZADO = "^\\s+$";
	public static final String EXPRESION_REGULAR_CONTIENE_RETORNO_WIN = "^(.*\\r\\n.*)*$";
	public static final String EXPRESION_REGULAR_CONTIENE_RETORNO_LINUX = "^(.*\\n.*)*$";
	public static final String EXPRESION_REGULAR_CONTIENE_EN_EL_MEDIO_PREFIX = ".+";
	public static final String EXPRESION_REGULAR_CONTIENE_EN_EL_MEDIO_POSTFIX = ".*";
	
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_DUC = "^U[0-9]{3}-[0-9]{8}$"; // Valido DUC (formato "Unnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_DEBITO = "^[ABE]-[D|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es Calipso ( "X-nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_LETRA_E = "^[E]-[0-9]{4}-[0-9]{8}$"; // Valido si es Calipso CON LETRA E ("E-nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_1_DEBITO = "^[AB]-[D|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es mic ( "X-nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_2_DEBITO = "^[D|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es Mic ( "nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_CRE = "^[ABX]-[C|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es Calipso Creditos ( "X-nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_1_CREDITO= "^[AB]-[C|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es mic ( "X-nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_2_CREDITO = "^[C|0-9][0-9]{3}-[0-9]{8}$"; // Valido si es Mic ( "nnnn-nnnnnnnn")
	public static final String EXPRESION_REGULAR_NRO_DOC_TIPO_CAP = "^([ABCM]-)?[0-9]{4}-[0-9]{8}$"; // Si es SAP, los valores son: A/B/C/M/Vacío => X-nnnn-nnnnnnnn o nnnn-nnnnnnnn;
	
	private static final String EXPRESION_REGULAR_NRO_LEGAL_NEXUS = "^[AB]-{1}[0-9]{4}-[0-9]{8}$"; //valida el formato para NEXUS (X-nnnn-nnnnnnnn)
	private static final String EXPRESION_REGULAR_NRO_LEGAL_SAP = "^[AB]-{1}[0-9]{4}-[0-9]{8}$"; //valida el formato para SAP (X-nnnn-nnnnnnnn)
	private static final String EXPRESION_REGULAR_NRO_LEGAL_NEGOCIO_NET = "^[AB]{1}[0-9]{4}-[0-9]{8}$"; //Valida el formato para Negocio.Net ("Xnnn-nnnnnnnn")
	private static final String EXPRESION_REGULAR_NRO_LEGAL_OPEN = "^[0-9]{4}-[0-9]{8}$"; //Valida lel formato para OPEN ( nnnn-nnnnnnnn)
	
	
	
	private static final String NUMERIC_COMODIN = "^[0-9\\%]{0,10}$";
	
	
	
	/**
	 * Verifico que la coleccion no es nula ni está vacia
	 * @param value
	 * @return
	 */
	public static boolean isCollectionNotEmpty(Collection<?> value) {
		return (!isObjectNull(value) && !value.isEmpty());
	}
	
	/**
	 * Verifico que el map no es nula ni está vacia
	 * @param value
	 * @return
	 */
	public static boolean isMapNotEmpty(Map<?,?> value) {
		return (!isObjectNull(value) && !value.isEmpty());
	}
	
	/**
	 * Verifico que si la cadena está vacia o nula
	 * @param value
	 * @returns if a string is null or empty
	 */
	public static boolean isNullOrEmpty (String value){
		return isObjectNull(value) || Utilidad.EMPTY_STRING.equals(value.trim()) || "null".equalsIgnoreCase(value);
	}

	/**
	 * Verifico si la cadena es nula, vacio o guion
	 * @param value
	 * @return
	 */
	public static boolean isNullEmptyOrDash (String value){
		return isObjectNull(value) || isEmptyString(value) || "null".equalsIgnoreCase(value) || "-".equalsIgnoreCase(value);
	}
	
	/**
	 * Verifica que el <code>String</code> enviado por parámetro sea vacío o
	 * sólo espacios en blanco.
	 * @param value
	 * @return <code>true</code> si el string es vacio o se trata de espacios en blanco.
	 */
	public static boolean isEmptyString (String value) {
		return Utilidad.EMPTY_STRING.equals(value.trim());
	}
	
	/**
	 * Verifico que si es un objeto vacio
	 * @param obj
	 * @return if an object is null
	 */
	public static boolean isObjectNull(Object obj){
		return obj == null;
	}
	
	/**
	 * Verifico que si es una clase instanciada
	 * @param obj
	 * @return if an object
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isClass(Object obj, Class clase) throws ClassNotFoundException{
		if (!Validaciones.isObjectNull(obj)) {
			return clase.isInstance(obj);
		} else {
			return false;
		}
	}
	
	/**
	 * Verifico que si es numerico
	 * @param value
	 * @return if value match with Numeric Pattern
	 */	
	public static boolean isNumeric(String value){
		return !isNullOrEmpty(value) && value.matches(NUMERIC_REGULAR_EXPRESSION);
	}
	
	/**
	 * Verifico que si es numerico
	 * @param value
	 * @return if value match with Numeric Pattern
	 */	
	public static boolean isNumeric(String value,int min,int max){
		return !isNullOrEmpty(value) && value.matches(String.format(NUMERIC_REGULAR_EXPRESSION_LENGTH_VALIDATION,min,max));
	}
	
	/**
	 * Verifico que si es numerico con una cantidad de decimales (la cual se pasa por parametro) 
	 * @param value, decimales
	 * @return if value match with Numeric with limited decimals Pattern
	 */	
	public static boolean isNumericDecimal(String value, int decimales){
		/** */
		final String NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION = "(,\\d{1,"+decimales+"})?$";
		/** */
		return !isNullOrEmpty(value) && value.matches(String.format("^"+NUMERIC_REGULAR_EXPRESSION_VALIDATION + NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION));
	}
	
	/**
	 * Verifico que el formato sea numerico, respetando un limite de decimales. Acepta que empiece con "$"
	 * @param value
	 * @param decimales
	 * @return
	 */
	public static boolean isImporteFormato(String value, int decimales){
		/** */
		final String NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION = "(,?\\d{1,"+decimales+"})?$";
		/** */
		return !isNullOrEmpty(value) && value.matches("\\$?"+String.format(NUMERIC_REGULAR_EXPRESSION_VALIDATION + NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION));
	}
	public static boolean isImporteFormatoDosDecimales(String value) {
		return !isNullOrEmpty(value) && value.matches(INPORTE_DOS_DECIMALES_REGULAR_EXPRESSION);
	}
	
	
	/**
	 * Verifico que el formato sea numerico, respetando un limite de decimales. Acepta que empiece con "$"
	 * @param value
	 * @param decimales
	 * @return
	 */
	public static boolean isImporteFormatoValor(String value){
		/** */
		boolean respuesta = false;
	
		if(isNullOrEmpty(value) || value.length() > 14) { 
			return false; 
		} else {
			String primerChar = value.substring(0, 1);
			if(!(primerChar.matches(String.format(NUMERIC_REGULAR_EXPRESSION)))) { 
				return false;
			} else {
				if(value.contains(",")){
					StringTokenizer stcom = new StringTokenizer(value, ",");
					if(stcom.countTokens() != 2) { 
						return false;
					} else {
						String antesComa = stcom.nextToken();
						String despComa = stcom.nextToken();
						if(despComa.length() > 2 || !despComa.matches(String.format(NUMERIC_REGULAR_EXPRESSION))) { 
							return false;
						} else { 
							respuesta = true;
						}
						if(antesComa.contains(".")){
							StringTokenizer stpun = new StringTokenizer(antesComa, ".");
							if(!(stpun.countTokens() == 2 || stpun.countTokens() == 3)) { 
								return false;
							} else {
								int cont = 1;
								while(stpun.hasMoreTokens()){
									String subString = stpun.nextToken();
									if(cont == 1){
										if (subString.length() < 4 && subString.matches(String.format(NUMERIC_REGULAR_EXPRESSION))) { 
											respuesta = true;
										} else {
											return false;
										}
									} else {
										if (subString.length() == 3 && subString.matches(String.format(NUMERIC_REGULAR_EXPRESSION))) { 
											respuesta = true;
										} else {
											return false;
										}
									}
									cont++;
								}
							}
						} else {
							if(!(antesComa.matches(String.format(NUMERIC_REGULAR_EXPRESSION)) && (antesComa.length() < 10))){
								return false;
							}
						}
					}
				}
				if(value.contains(".") && !value.contains(",")){
					StringTokenizer stpun = new StringTokenizer(value, ".");
					if(!(stpun.countTokens() == 2 || stpun.countTokens() == 3)) { 
						respuesta = false;
					} else {
						int cont = 1;
						while(stpun.hasMoreTokens()){
							String subString = stpun.nextToken();
							if(cont == 1){
								if (subString.length() < 4 && subString.matches(String.format(NUMERIC_REGULAR_EXPRESSION))) { 
									respuesta = true;
								} else {
									return false;
								}
							} else {
								if (subString.length() == 3 && subString.matches(String.format(NUMERIC_REGULAR_EXPRESSION))) { 
									respuesta = true;
								} else {
									return false;
								}
							}
							cont++;
						}
					}
				}
				if(value.matches(String.format(NUMERIC_REGULAR_EXPRESSION)) && value.length() < 10) { 
					respuesta = true; }
				if(value.substring(0, 1).equals(".") || value.substring(0, 1).equals(",")) { 
					return false; }
			}
		}
		return respuesta;
	}
	
	/**
	 * Verifico que el formato sea nnnn-nnnnnnnn
	 * @param value
	 * @return
	 */
	public static boolean isFormatoRecibo(String value){
		boolean respuesta = false;
		if(value.length() != 13){
			respuesta = false;
		} else {
			String parte1 = value.substring(0, 4);
			String parte2 = value.substring(4, 5);
			String parte3 = value.substring(5, 13);
		
			respuesta = !isNullOrEmpty(value)
				&& parte1.matches(String.format(NUMERIC_REGULAR_EXPRESSION))
				&& parte2.matches("-")
				&& parte3.matches(String.format(NUMERIC_REGULAR_EXPRESSION));
		}
		return respuesta;
	}
	
	/**
	 * Verifico que sea numerico con o sin decimales. Permite separar las unidades de mil con "."  
	 * @param value
	 * @return if value match with Numeric with decimals Pattern
	 */
	public static boolean isNumericDecimal(String value){
		/** */
		final String NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION = "(,\\d+)?$";
		/** */
		return !isNullOrEmpty(value) && value.matches(String.format("^"+NUMERIC_REGULAR_EXPRESSION_VALIDATION + NUMERIC_REGULAR_EXPRESSION_DECIMAL_VALIDATION));
	}

	/**
	 * Verifico que si es alfanumerico
	 * @param value
	 * @return if value match with Alpha Numeric Pattern
	 */
	public static boolean isAlphaNumeric(String value){
		return !isNullOrEmpty(value) && value.matches(ALPHANUMERIC_REGULAR_EXPRESSION);
	}

	
	/**
	 * Verifico que si es numérico o nulo
	 * @param value
	 * @return if value match with Numeric Pattern
	 */	
	public static boolean isNullOrNumeric(String value){
		if (!isNullOrEmpty(value)) {
			return value.matches(NUMERIC_REGULAR_EXPRESSION);
		} 
		
		return true; 
	}
	
	/**
	 * Verifico que si es un número natural
	 * @param value
	 * @return if value match with Numeric Pattern
	 */	
	public static boolean isNullOrNumericNatural(String value){
		if (!isNullOrEmpty(value) && !value.equals('0')) {
			return value.matches(NUMERIC_NATURAL_EXPRESSION);
		} 
		
		return true; 
	}
	
	/**
	 * Verifico que si es alfanumerico o nulo
	 * @param value
	 * @return if value match with Alpha Numeric Pattern
	 */
	public static boolean isNullOrAlphaNumeric(String value){
		if (!isNullOrEmpty(value)) {
			return value.matches(ALPHANUMERIC_REGULAR_EXPRESSION);
		}
		return true;
	}
	
	/**
	 * Verifico que si la cadena comienza con cero
	 * @param value
	 * @return
	 */
	public static boolean doesItBeginWithZero(String value){
		return !isNullOrEmpty(value) && isNumeric(value) && ZERO_CHARACTER.equals(value.charAt(0));
	}
	
	/**
	 * Valido una fecha con formato DD/MM/AAAA
	 * @param fecha
	 * @return
	 */
	@Deprecated
	public static boolean validarFecha(String fecha){
		if(fecha.length() != 10){
    		return false;
    	}
    	
    	int dia = 0;
    	int mes= 0;
    	int anio = 0;
    	
    	try{
    		dia = Integer.valueOf(fecha.substring(0,2));
    		mes = Integer.valueOf(fecha.substring(3,5));
    		anio = Integer.valueOf(fecha.substring(6,10));
    	}catch(Exception e){
    		return false;
    	}
    	if(anio<=0 || (String.valueOf(anio)).length()!=4){
    		return false;
    	}else{
	    	if(mes<=0 || mes>12){
	    		return false;
	    	}else{
	    		if(dia<=0 || dia>31){
	    			return false;
	    		}else{
	    			if((mes == 4 || mes == 6 || mes == 9 || mes == 11)&&(dia > 30)){
	    				return false;
	    			}else{
	    				if(mes == 2 && isBisiesto(anio) && dia > 29){
	    					return false;
	    				}else if (mes == 2 && !isBisiesto(anio) && dia > 28){
	    					return false;
	    				}else{
	    					return true;
	    				}
	    			}
	    		}
	    	}
    	}
	}
	
	/**
	 * Este metodo valida que la fecha sea correcta usando el metodo parseDatePickerString de Utilidad,
	 * que parsea la fecha con la clase SimpleDateFormat.
	 * @param fecha
	 * @return
	 */
	public static boolean validarFechaConParse(String fecha){
		try {
			Utilidad.parseDatePickerString(fecha);
		} catch (ParseException e) {
			Traza.error(Validaciones.class, "Se ha producido error de parseo..." , e);
			return false;
		}

		return true;
	}

	/**
	 * Verifica que la fecha HASTA se mayor o igual a la fecha DESDE
	 * 
	 * @param anio
	 * @return
	 */
	public static boolean validarFechaDesdeHasta(String fechaDesde,
			String fechaHasta) {

		try {
			Date fechaDesdeDate = Utilidad.parseDatePickerString(fechaDesde);
			Date fechaHastadeDate = Utilidad.parseDatePickerString(fechaHasta);

			if (fechaDesdeDate.equals(fechaHastadeDate)) {

				return true;
			}

			if (!fechaDesdeDate.before(fechaHastadeDate)
					&& !fechaDesdeDate.equals(fechaHastadeDate)) {

				return false;
			}

		} catch (ParseException e) {
			Traza.error(Validaciones.class, "Se ha producido error de parseo..." , e);
			return false;
		}

		return true;
	}

	/**
	 * Verifica que la fecha HASTA se mayor o igual a la fecha DESDE
	 * 
	 * @param anio
	 * @return
	 */
	public static boolean validacionImporteDesdeHasta(String importeDesde,
			String importeHasta) {
		importeDesde = importeDesde.replace(".", "").replace(",", "");
		importeHasta = importeHasta.replace(".", "").replace(",", "");
		long importeDesdeInteger = Long.parseLong(importeDesde);
		long importeHastaInteger = Long.parseLong(importeHasta);

		if (importeDesdeInteger > importeHastaInteger) {
			return false;
		}

		return true;
	}
	public static boolean validacionImporteDesdeHastaMejorado(String importeDesde, String importeHasta) {
		BigDecimal importeDesdeBg = Utilidad.stringToBigDecimal(importeDesde);
		BigDecimal importeHastaBg = Utilidad.stringToBigDecimal(importeHasta);
		
		return (importeDesdeBg.compareTo(importeHastaBg) <= 0) ;
	}
	
	/**
	 * Verifica que el importe HASTA se mayor o igual a la importe DESDE
	 * 
	 * @param String importeDesde
	 * @param String importeHasta
	 * @return
	 */
	public static boolean validacionImporteMayorDesdeHasta(String importeDesde,
			String importeHasta) {
		int resultado = Utilidad.stringToBigDecimal(importeDesde).compareTo(Utilidad.stringToBigDecimal(importeHasta));
		if(resultado > 0){
			return false;
		}
		return true;
	}

	/**
	 * Verifica que si es bisiesto
	 * @param anio
	 * @return
	 */
	public static boolean isBisiesto(int anio){
		if (anio % 400 == 0){
			return true;
		}else{
			if(anio % 4 == 0 && anio % 100 != 0){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * Verifico que sea un formato de e-mail correcto
	 * @param value
	 * @return if value match with Email Pattern
	 */	
	public static boolean isNullOrEmail(String value){
		if (!isObjectNull(value)) {
			return value.matches(EMAIL_REGULAR_EXPRESSION);
		} 
		
		return true; 
	}
	
	/**
	 * Verifico que sea un formato texto correcto
	 * @param value
	 * @return si coincide con el formato establecido	 
	 */
	public static boolean esFormatoTexto(String value){
		return value.matches(ACEPTAR_CARACTERES_ESPECIALES_EXPRESION_REGULAR);
	}
	
	/***************************************************************************
	 * Validaciones de nombres de archivos
	 * ************************************************************************/
    
    /**
	 * Verifico que sea un formato de archivo de registro AVC correcto.
	 * El formato del nombre deberia ser "AVC_acuerdo_fecha_secuencia.csv"
	 * @param nombreArchivoCompleto
	 * @return 
	 */	
	public static boolean esNombreArchivoRegistroAVC(String nombreArchivoCompleto){
		if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("AVC_") && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_CSV)) {
			
			String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
			String[] campos = nombreArchivo.split("_");
			
			if(campos.length == 4){
				if(null!=TipoAcuerdoEnum.getEnumByDescripcionTipoAcuerdo(campos[1])){
					
					if(campos[2].length()==8 && isNumeric(campos[2])){
						try{
							
							Utilidad.parseDateWSFormat(campos[2]);
							
							if(campos[3].length()==2 && isNumeric(campos[3])){
								return true;
							}
							
						} catch(ParseException e){
							return false;
						}
					}
					
				}
			}
		}
		return false;
	}
	
	 /**
		 * Verifico que sea un formato de archivo de registro AVC correcto.
		 * El formato del nombre deberia ser "AVC_acuerdo_fecha_secuencia.csv"
		 * @param nombreArchivoCompleto
		 * @return 
		 */	
		public static boolean esNombreArchivoDebitos(String nombreArchivoCompleto){
			if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("DEBITOS_") && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_TXT)) {
				
				String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
				String[] campos = nombreArchivo.split("_");
				
				if(campos.length == 3){
					
					if(campos[0].equalsIgnoreCase("DEBITOS")){
		
						if(campos[1].length()==8 && isNumeric(campos[1])){
							try{
								
								Utilidad.parseDateWSFormat(campos[1]);
								
								if(campos[2].length()==3 && isNumeric(campos[2])){
									return true;
								}
								
							} catch(ParseException e){
								return false;
							}
						}
						
					}
				}
			}
			return false;
		}
		
		 /**
		 *
		 * Verifico que sea un formato de archivo de Otros Debitos correcto.
		 * @param nombreArchivoCompleto
		 * @return 
		 */	
		public static boolean esNombreArchivoOtrosDebitos(String nombreArchivoCompleto){
			if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("OTROSDEBITOS_") && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_TXT)) {
				
				String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
				String[] campos = nombreArchivo.split("_");
				
				if(campos.length == 3){
					
					if(campos[0].equalsIgnoreCase("OTROSDEBITOS")){
						
						if(campos[1].length()==8 && isNumeric(campos[1])){
							try{
								
								Utilidad.parseDateWSFormat(campos[1]);
								
								if(campos[2].length()==3 && isNumeric(campos[2])){
									return true;
								}
								
							} catch(ParseException e){
								return false;
							}
						}
						
					}
				}
			}
			return false;
		}
	
	/**
	 * Verifico que sea un formato de archivo de reversion correcto.
	 * El formato del nombre deberia ser [Nombre Aplicación].ReversionOperaciones.[fecha].[cobrador Origen]
	 * @param nombreArchivoCompleto
	 * @return 
	 */	
	public static boolean esNombreArchivoReversion(String nombreArchivoCompleto) throws ValidacionExcepcion{
		try {
			if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("SHIVA.ReversionOperaciones.") 
					&& (nombreArchivoCompleto.endsWith(SistemaEnum.MIC.name()) || nombreArchivoCompleto.endsWith(SistemaEnum.CALIPSO.name()))) {
				
				//String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
				String[] campos = nombreArchivoCompleto.split("\\.");
				
				if(campos.length == 4){
					if(!Validaciones.isNullOrEmpty(campos[2]) && !Validaciones.isNullOrEmpty(campos[3])){
						Date fechaArchivo = Utilidad.parseDateWSFormat(campos[2]);
						
						if(!Validaciones.isObjectNull(fechaArchivo)  
								&& ((new Date()).equals(fechaArchivo) || fechaArchivo.before(new Date()))){
							if(SistemaEnum.MIC.name().equals(campos[3]) || SistemaEnum.CALIPSO.name().equals(campos[3])){
								return true;
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			throw new ValidacionExcepcion(e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * Verifico que sea un formato de archivo de operacion masiva correcto.
	 * El formato del nombre deberia ser "tipo_fecha_secuencia.txt"
	 * @param nombreArchivoCompleto
	 * @return 
	 */	
	public static boolean esNombreArchivoOperacionMasiva(String nombreArchivoCompleto){
		if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_TXT)) {
			
			String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
			String[] campos = nombreArchivo.split(UNDERSCORE);
			
			if(campos.length == 3){
				if(null!=TipoArchivoOperacionMasivaEnum.getEnumByName(campos[0])){
					try {
						if(!Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campos[1]))){
							if(campos[2].length()==3 && isNumeric(campos[2])){
								return true;
							}
						}					
						
					} catch (ParseException e) {
						return false;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Verifico que sea un formato de archivo de operacion masiva correcto.
	 * El formato del nombre deberia ser "[Nombre Aplicación].OperacionesMasivas.MIC.[direccion].[fecha]"
	 * @param nombreArchivoCompleto
	 * @return 
	 */	
	public static boolean esNombreArchivoOperacionMasivaMic(String nombreArchivoCompleto, boolean salida) {
		String discriminador = Utilidad.EMPTY_STRING;
		String app = Utilidad.EMPTY_STRING;

		if (salida) {
			discriminador = Constantes.OPERACION_MASIVA_MIC_SALIDA_NOMBRE_ARCHIVO;
			app = Constantes.OPERACION_MASIVA_MIC_SALIDA_APP_NOMBRE_ARCHIVO;
		} else {
			discriminador = Constantes.OPERACION_MASIVA_MIC_ENTRADA_NOMBRE_ARCHIVO;
			app = Constantes.OPERACION_MASIVA_MIC_ENTRADA_APP_NOMBRE_ARCHIVO;
		}
		if (
			!isObjectNull(nombreArchivoCompleto) &&
			nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_TXT)
		) {
			String nombreArchivo =  nombreArchivoCompleto.substring(
				0,
				nombreArchivoCompleto.length() - Constantes.ARCHIVO_TXT.length()
			);
			
			String[] campos = nombreArchivo.split("\\" + Constantes.PUNTO);
			if (Constantes.OPERACION_MASIVA_MIC_CANT_CAMPOS_NOMBRE_ARCHIVO == campos.length) {
				String vectorDisciminador[] = discriminador.split("\\" + Constantes.PUNTO); 
				if (
					app.equals(campos[0]) &&
					vectorDisciminador[0].equals(campos[1]) &&
					vectorDisciminador[1].equals(campos[2]) &&
					vectorDisciminador[2].equals(campos[3])
				) {
					try{
						if(!Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campos[4]))){
							return true;
						}
						
						
					} catch (ParseException e){
						return false;
					}
					try{
						Utilidad.parseDateWSFormat(campos[4]);
						
						if(campos[3].length()==2 && isNumeric(campos[3])){
							return true;
						}
					} catch(ParseException e){
						return false;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param nombreArchivoCompleto
	 * @return Retorna un booleano si el nombre del archivo es de entrada con aplicacion manual
	 * @throws ParseException 
	 */
	public static boolean esNombreArchivoEntradaApliManual(String nombreArchivoCompleto) throws ParseException {
		boolean esNombre = true;
		if (!isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("SHIVA.DetalleAplicacionesManuales") && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_CSV)) {
			String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length());
			String[] campos = nombreArchivo.split("\\.");
			if (campos.length == 6) {
				if (!campos[2].startsWith("Entrada")) {
					esNombre = false;
				}
				try{
					Utilidad.parseDateWSFormat(campos[4]);
					
				} catch(ParseException e){
					return false;
				}
			} else {
				esNombre = false;
			}
		} else {
			esNombre = false;
		}
		return esNombre;
		
	}
		
	public static boolean tieneFechaRealPago(String fecha) throws ParseException {
		boolean tieneFechaValida = true;
		if (isObjectNull(Utilidad.parseDatePickerString(fecha))) {
			tieneFechaValida = false;
		}
		return tieneFechaValida;
	}
	
	/**
	 * 
	 * @param nombreArchivo
	 * @return Retorna un booleano si el sistema origen es valido
	 * @throws PersistenciaExcepcion
	 * @throws ParseException 
	 */
	public static boolean tieneSistemaAplicacionManualValido(String nombreArchivo) throws PersistenciaExcepcion, ParseException {
		boolean tieneSistemaValido = false;
		String empresaDescripCorta = Utilidad.empresaArchivoEntradaAplicacionManual(nombreArchivo);
		ArrayList<SistemaEnum> sistemasAplicacionManual =(ArrayList<SistemaEnum>) SistemaEnum.getEnumAplicacionManual();
		for (SistemaEnum sistema : sistemasAplicacionManual) {
			if (sistema.getDescripcionCorta().equals(empresaDescripCorta)) {
				tieneSistemaValido = true;
				break;
			}
		}
		return tieneSistemaValido;
	}
	
	/**
	 * Método que valida si el cuit tiene el formato y valores correctos. Acepta el formato con y sin guiones.
	 * 
	 * @param cuit - Se espera Cuit con o sin guiones
	 * @return
	 * @throws ValidacionExcepcion
	 */
	public static boolean validarCuit(String cuit) throws ValidacionExcepcion {

		//Validacion de longitud
		if (isObjectNull(cuit) || (cuit.length() != 13 && cuit.length() != 11)){
			throw new ValidacionExcepcion("","error.cuit.formato");
		}
		
		if(cuit.length()==13){
			//Validacion del formato de cuit con guiones
			if(!cuit.matches(CUIT_REGULAR_EXPRESSION)){
				throw new ValidacionExcepcion("","error.cuit.formato");
			}
			//quito los dos guiones
			cuit = cuit.replaceAll("-", "");
		}
		
		//Validar los primeros digitos
		String dig = cuit.substring(0,2);
		if (!(dig.equals("27") 
				|| dig.equals("20") 
				|| dig.equals("23")
				|| dig.equals("24") 
				|| dig.equals("30") 
				|| dig.equals("33")
				|| dig.equals("34"))) {
			throw new ValidacionExcepcion("","error.cuit.primeros.digitos");
		}
		
		
		if (!Validaciones.validarFormatoCuit(cuit.toCharArray())) {
			throw new ValidacionExcepcion("","error.cuit.digito.verificador");
		}	
		
		return true;
	}
	
	/**
	 * Valida un Cuit con formato nnnnnnnnnnn. 
	 * @param cuit
	 * @return
	 */
	private static boolean validarFormatoCuit(char[] cuit){
		if(cuit.length != 11){
			return false;
		}
		for (char c : cuit) {
			if(!Character.isDigit(c)){
				return false;
			}
		}
		//Esta validacion para los casos que 23 y 33
		if (cuit[0] == '2' || cuit[0] == '3'){
			if (cuit[1] == '3' && !algoritmoCuit(cuit, true)){
				char[] sexo = {'0','7'};
				for (int j = 0; j < 2; j++) {
					cuit[1] = sexo[j];
					if (algoritmoCuit(cuit, true)){
						cuit[1] = '3';
						return true;
					}
					if(cuit[0] == '3'){
						break;
					}
				}
				return false;
			}
			return algoritmoCuit(cuit, false);
		}
		return false;
	}

	/**
	 * Metodo que valida
	 * @param cuit
	 * @param es23
	 * @return
	 */
	private static boolean algoritmoCuit(char[] cuit, boolean es23) {
		int[] coeficiente = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int suma = 0;
		for (int j = 0; j < 10; j++) {
			suma += Integer.valueOf(String.valueOf(cuit[j]))*coeficiente[j];
		}
		int resto = suma%11;
		switch (resto) {
		case 0:
			if (Integer.valueOf(String.valueOf(cuit[10])) != 0 || es23){
				return false;
			}
			break;
		case 1:
			if (es23){
				if (!((cuit[1] == '7' && cuit[10] == '4') || (cuit[1] == '0' && cuit[10] == '9'))){
					return false;
				}
			} else if (!Integer.valueOf(String.valueOf(cuit[10])).equals(11-resto)){
				return false;
			}
			break;
		default:
			if (!Integer.valueOf(String.valueOf(cuit[10])).equals(11-resto) && es23){
				return false;
			}
			if(!Integer.valueOf(String.valueOf(cuit[10])).equals(11-resto))
			{
				return false;
			}
			break;
		}
		return true;
	}
	
	
	/**
	 * @autor u591368 Fernando Nicolás Quispe
	 * @param cadena
	 * @param stringAComparar
	 * @param separador
	 * @return splitea la cadena con el parametro "separador". Si el "string a comparar" esta dentro del string "cadena" , retorna true. 
	 */
	public static boolean pertenece(String stringAComparar, String cadena, String separador) {
		
		boolean estaEnLaCadena = false;
		
		String[] split = cadena.split(";");
		for (String segmento : split) {
			if (segmento.equals(stringAComparar)) {
				estaEnLaCadena = true;
			}
		}
		
		return estaEnLaCadena;
	}
	
	/**
	* Valido que el documento respete el formato DUC: "Unnn-nnnnnnnn"
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoDebitosDUC(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_DUC);
	}
	
	/**
	 * Valido que el nroLegal respete alguno de los siguientes formatos: "Unnnn-nnnnnnnn"
	 * @param nroLegal
	 * @return
	 */
	public static boolean validarNroDocumentoDuc(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_DUC);
	}
	
	/**
	* Valido que el nroLegal respete alguno de los siguientes formatos: "X-nnnn-nnnnnnnn para X=A/B/E", "nnnn-nnnnnnnn" o "Unnn-nnnnnnnn"
	* @param nroLegal
	* @return
	*/
	public static boolean validarNroDocumentoDebitos(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_DUC) 
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_DEBITO) 
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_1_DEBITO) 
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_2_DEBITO);
	}
	
	/**
	 * Valido que el documento respete alguno de los sigujientes formatos: "X-nnnn-nnnnnnnn para X= A/B/C/M o vacio" "Xnnn-nnnnnnnn para X= A/B" o "nnnn-nnnnnnnn"
	 * @param documento
	 * @return
	 */
	public static boolean validarNumeroLegalOtrosDebitos(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_NEGOCIO_NET)
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_NEXUS)
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_OPEN)
				|| nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_SAP);
	}
	public static boolean validarNumeroLegalOtrosDebitosNegocio(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_NEGOCIO_NET);				
	}
	public static boolean validarNumeroLegalOtrosDebitosNexus(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_NEXUS);
	}
	public static boolean validarNumeroLegalOtrosDebitosOpen(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_OPEN);
	}
	public static boolean validarNumeroLegalOtrosDebitosSAP(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_LEGAL_SAP);
	}
	/**
	 * Valido que el nroLegal respete alguno de los siguientes formatos: "X-nnnn-nnnnnnnn para X=A/B/X"
	 * @param nroLegal
	 * @return
	 */
	public static boolean validarNroDocumentoCreditosCalipso(String nroLegal){
		return nroLegal.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_CRE);
	}
	
	
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "A-nnnn-nnnnnnnn", "B-nnnn-nnnnnnnn", "E-nnnn-nnnnnnnn"
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoDebitosCalipso(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_DEBITO);
	}
	
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "E-nnnn-nnnnnnnn"
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoDebitosCalipsoLetraE(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_CALIPSO_LETRA_E);
	}
	
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "A-nnnn-nnnnnnnn", "B-nnnn-nnnnnnnn" tanto para creditos como para debitos
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoMIC_1_debitos(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_1_DEBITO);
	}
	
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "nnnn-nnnnnnnn" tanto para creditos como para debitos
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoMIC_2_debitos(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_2_DEBITO);
	}
	
	
	public static final String EXPRESION_REGULAR_ID_REMANENTE = "^[0-9]{1,10}[0-9]{3}[1-6]$";
	
	/**
	 * Valido Formato Id Remanente: Nro de cliente legado (10 dígitos), la Cuenta (Últimos 3 dígitos de la cuenta de cobro-Secuencial) y el Tipo de Remanente (1/2/3/4/5/6).
	 * @param idRemanente
	 * @return
	 */
	public static boolean validarIdRemanente(String idRemanente){
		return idRemanente.matches(EXPRESION_REGULAR_ID_REMANENTE);
	}
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "A-nnnn-nnnnnnnn", "B-nnnn-nnnnnnnn" tanto para creditos como para debitos
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoMIC_1_creditos(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_1_CREDITO);
	}
	
	/**
	* Valido que el documento respete alguno de los siguientes formatos: "nnnn-nnnnnnnn" tanto para creditos como para debitos
	* @param documento
	* @return
	*/
	public static boolean validarNroDocumentoMIC_2_creditos(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_MIC_2_CREDITO);
	}
	
	
	public static boolean isEmptyNumericoSerializado(String value) {
		return value.matches(EXPRESION_REGULAR_EMPTY_NUMERICO_SERIALIZADO);
	}

	public static boolean isEmptyAlfanumericoSerializado(String value) {
		return value.matches(EXPRESION_REGULAR_EMPTY_ALFANUMERICO_SERIALIZADO);
	}
	public static boolean contieneRetornoWin(String value) {
		return value.matches(EXPRESION_REGULAR_CONTIENE_RETORNO_WIN);
	}
	public static boolean contieneRetornoLinux(String value) {
		return value.matches(EXPRESION_REGULAR_CONTIENE_RETORNO_LINUX);
	}
	public static boolean contieneEnElMedio(String midland, String value) {
		return value.matches(EXPRESION_REGULAR_CONTIENE_EN_EL_MEDIO_PREFIX + midland + EXPRESION_REGULAR_CONTIENE_EN_EL_MEDIO_POSTFIX);
	}
	
	public static boolean validarNroDocumentoCap(String documento){
		return documento.matches(EXPRESION_REGULAR_NRO_DOC_TIPO_CAP);
	}
	
	public static boolean validarNumericoComodin(String documento){
		return documento.matches(NUMERIC_COMODIN);
	}
}