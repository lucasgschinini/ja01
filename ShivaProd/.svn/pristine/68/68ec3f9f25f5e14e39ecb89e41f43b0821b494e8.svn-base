package ar.com.telecom.shiva.base.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.DatosWorkflowEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.NroDocumentoLegal;
import ar.com.telecom.shiva.negocio.bean.ShvParamCalendarioWrapper;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosSimulacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;

public class Utilidad {

    /** The Constant dateFormat. */
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    
    /** The Constant dateFormat. */
    public static final String DATE_FORMAT_PICKER = "dd/MM/yyyy";
    
    /** The constante AAAA/MM/DD */
    public static final String ANO_MES_DIA_BARRA = "yyyy/MM/dd";
   
    /** The constante AAAA/MM/DD */
    public static final String ANO_MES_DIA_DASH = "yyyy-MM-dd";
    
    /** The constante AAAAMMDD */
    public static final String ANO_MES_DIA = "yyyyMMdd";
    
    /** The constante AAAAMMDD */
    public static final String ANO_CORTO_MES_DIA = "yyMMdd";
    
    /** The constante AAAAMMDD */
    public static final String DIA_MES_ANO = "ddMMyyyy";
    
    /** The constante HHMMSSTH */
    public static final String HORA_MIN_SEG_TH = "HHmmssS";

    public static final String HORA_MIN_SEG_TH2 = "HH:mm:ssS";
    
    public static final String HORA_MIN_SEG_TH3 = "HHmmss";

    /** The Constant DATE_TIME_FORMAT. */
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    
    /** The Constant DATE_TIME_FULL_FORMAT. */
    public static final String DATE_TIME_FULL_FORMAT = "dd/MM/yyyy HH:mm:ss";
    
    /** The Constant DATE_TIME_FULL_FORMAT MILLISECONDS. */
    public static final String DATE_TIME_FULL_FORMAT_MILLISECONDS = "dd/MM/yyyy HH:mm:ss.SSS";

    /** The Constant AAAAMMDD_HHmmss. */
    public static final String AAAAMMDD_HHmmss = "yyyyMMdd_HHmmss";
    
    /** The Constant DATE_TIME_FULL_FORMAT. */
    public static final String DATE_TIME_FULL_FORMAT_BASE_DATOS = "yyyy-MM-dd HH:mm:ss";
    
    /** The Constant DATE_TIME_FULL_FORMAT con formato para la base de datos*/
    public static final String ORACLE_DATE_TIME_FULL_FORMAT = "dd/mm/yyyy hh24:mi:ss";
    
    /** The Constant DATE_TIME_FULL_FORMAT_MILLISECONDS con formato para la base de datos*/
    public static final String ORACLE_DATE_TIME_FULL_FORMAT_MILLISECONDS = "dd/mm/yyyy hh24:mi:ss.SSS";
    
	/** The Constant DATE_TIME_FORMAT. */
	public static final String DATE_TIME_YEAR_FORMAT = "dd/MM/yy HH:mm:ss";
    
	/** The Constant DATE_TIME_FORMAT. */
	public static final String DATE_TIME_YEAR__DOS_FORMAT = "dd/MM/yy";
	
    /** The Constant dateFormat. */
    public static final String DATE_FORMAT_POINT = "dd.MM.yyyy";
    
    /** The Constant ZERO_CHARACTER. */
    public static final String ZERO_CHARACTER = "0";
    
    /** The Constant DECIMAL_CHARACTER. */
    public static final String DECIMAL_CHARACTER = ",";
    
    /** The Constant Point. */
	public static final String POINT = ".";
    
	/** The constant ASTERISCO */
    public static final String ASTERISK = "*";
    
    /** The Constant DOUBLE_POINT_CHARACTER. */
    public static final String DOUBLE_POINT_CHARACTER = ":";
    
    public static final String DASH_CHARACTER = "-";
    
    /** The constant Sigla Pesos */
    public static final String PESOS_CHAR = "$";
    
    /** The constant Sigla Dolar */
    public static final String DOLAR_CHAR = "U$S";
    
    /** The Constant EMPTY_STRING. */
    public static final String EMPTY_STRING = "";
    
    /** The Constant SPACE_CHARACTER. */
    public static final String SPACE_CHARACTER = " ";
    
    /** The constant Time*/
    private static final DecimalFormat TIME_FORMAT_4 = new DecimalFormat("0000;0000");
    
	private static final Date JAVA_START_DATE = new Date(0);
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    
    /** The Constant dateFormat. */
    public static final String MES_ANIO_BARRA = "MM/yyyy";
    
    /** The Constant DATE_TIME_FULL_FORMAT MILLISECONDS CON GUIONES. 2015-09-15-12.40.08.181983*/
    public static final String DATE_TIME_FULL_FORMAT_MILLISECONDS_GUIONES = "yyyy-MM-dd-HH.mm.ss.SSS";
    
    /**   The Constant DATE_TIME_FULL_FORMAT MILLISECONDS CON GUIONES yyyy/MM/dd HH:mm:ss */
    public static final String DATE_TIME_FULL_FORMAT_BARRA = "yyyy/MM/dd HH:mm:ss";
    
    public static final Pattern EXCLUIR_CARACTERES_ESPECIALES_PATTERN = Pattern.compile(Validaciones.EXCLUIR_CARACTERES_ESPECIALES_EXPRESION_REGULAR);

    public static final short DATOS_MODIFICADOS_SOLO_DATOS = 0;
    public static final short DATOS_MODIFICADOS_SOLO_ESTADO = 1;
    public static final short DATOS_MODIFICADOS_FORMATEADO = 2;

	/**
     * THE CONSTRUCTOR.
     */
    protected Utilidad() {
        super();
    }
    
    /**
	 * Retorna el nombre simple de una clase
	 * @param Class clase
	 * @return String
	 */
	public static String getClassName(Class<?> clase) {
		StringTokenizer token = new StringTokenizer(clase.getName(), ".");
		String className = "";
		
		while (token.hasMoreTokens()) {
			className = token.nextToken();
		}
		
		return className;
	}
	/***************************************************************************************
	 * Verificar version
	 */
	public static void verificarVersion(IParametroServicio parametroServicio) throws ShivaExcepcion {
		try {
			
			//Verifico si el argumento VM entorno existe
			if (Validaciones.isNullOrEmpty(Constantes.VERSION_APLICACION)) {
				throw new ShivaExcepcion(Mensajes.VERSION_PROP);
			} else {
				String version = parametroServicio.getValorTexto(Constantes.VERSION);
				
				//Verifico si la propiedad version coincide con la BD
				if(!Constantes.VERSION_APLICACION.equalsIgnoreCase(version)) {
					System.out.println("ERROR DE VERSION!");
					throw new ShivaExcepcion(Utilidad.reemplazarMensajes(Mensajes.VERSION_BD,version,Constantes.VERSION_APLICACION));
				} 
			}
			
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Verifico que si el horario del logueo cumple con el rango permitido
	 */
	public static boolean verificarRangoHorarioBatchCobros(IParametroServicio parametroServicio) throws ShivaExcepcion {
		try {
			Date hoy = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(hoy);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
			String dia = "";
			switch (dayOfWeek) {
				case 1: 
					dia = "domingo"; break;
				case 2: 
					dia = "lunes"; break;
				case 3: 
					dia = "martes"; break;
				case 4: 
					dia = "miercoles"; break;
				case 5: 
					dia = "jueves"; break;
				case 6: 
					dia = "viernes"; break;	
				case 7: 
					dia = "sabado"; break;
			}
			
			String rangoHorario = parametroServicio.getValorTexto("horarioCobros.permitido."+dia);
			
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			
			Date time1 = (Date) formatter.parse(rangoHorario);
			
			String actual = formatter.format(hoy);
			Date horaActual = (Date) formatter.parse(actual);
			
			if ((horaActual.compareTo(time1) <= 0)) {
				return true;
			}
			
			return false;
			
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		} catch (ParseException e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Verifico que si el horario del logueo cumple con el rango permitido
	 */
	public static String verificarRangoHorario(IParametroServicio parametroServicio) throws ShivaExcepcion {
		try {
			Date hoy = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(hoy);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			
			String dia = "";
			switch (dayOfWeek) {
				case 1: 
					dia = "domingo"; break;
				case 2: 
					dia = "lunes"; break;
				case 3: 
					dia = "martes"; break;
				case 4: 
					dia = "miercoles"; break;
				case 5: 
					dia = "jueves"; break;
				case 6: 
					dia = "viernes"; break;	
				case 7: 
					dia = "sabado"; break;
				default:
					return Mensajes.RANGO_HORARIO_DOMINGO;
			}
			
			String rangoHorario = parametroServicio.getValorTexto("rangoHorario.permitido."+dia);
			
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			
			String str1 = rangoHorario.split("-")[0];
			Date time1 = (Date) formatter.parse(str1);
			
			String str2 = rangoHorario.split("-")[1];
			Date time2 = (Date) formatter.parse(str2);
			
			String actual = formatter.format(hoy);
			Date horaActual = (Date) formatter.parse(actual);
			
			if (str1.equalsIgnoreCase(str2)){
				return Utilidad.reemplazarMensajes(Mensajes.RANGO_HORARIO_NO_PERMITIDO, dia);
			}
			
			if ((time1.compareTo(horaActual) <= 0) && (horaActual.compareTo(time2) <= 0)) {
				return "";
			}
			
			return Utilidad.reemplazarMensajes(Mensajes.RANGO_HORARIO_PERMITIDO, str1, str2);
			
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		} catch (ParseException e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}

	/*****************************************************************************************************************************
     * Errores
     */
	public static boolean isAjax(HttpServletRequest request) {
		return request != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
	
	public static void tracearErrorRequest(Class<?> clase, HttpServletRequest request) {
		try {
			String mensaje = "";
					
			if (request != null) {
				Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
				if (throwable != null) {
					mensaje = ": " + throwable.getMessage();
				} 
				if (Validaciones.isNullOrEmpty(mensaje)) {
					mensaje =  (request.getAttribute("javax.servlet.error.message") != null 
							&& !Validaciones.isNullOrEmpty((String)request.getAttribute("javax.servlet.error.message")))
							? ": " + (String)request.getAttribute("javax.servlet.error.message"): "";
				}
				String codigoError =  request.getAttribute("javax.servlet.error.status_code") != null
						? "" + (Integer)request.getAttribute("javax.servlet.error.status_code"): "";
				String requestUri = request.getAttribute("javax.servlet.error.request_uri") != null
						? (String)request.getAttribute("javax.servlet.error.request_uri"): "";
				
				String detalle = codigoError + " - " + requestUri  + mensaje;
				String usuario = Utilidad.getUsuarioSesion(request);
				Traza.advertencia(clase, usuario, detalle);
			}
		} catch (Exception e) {}
	}
	
    /**
     * Retorna el error al string
     * @param e
     * @return
     */
    public static StringBuilder stackTraceToString( Exception e) {
        StringBuilder s = new StringBuilder();
        s.append(e+"<br>");
        for (StackTraceElement stackTraceEl : e.getStackTrace()) {
            s.append("	at: "+stackTraceEl.toString()+"<br>");
        }
        return s;
    }
	
	/**
	 * Retorna como String el trace de una excepción
	 * 
	 * @param Throwable ex
	 * @return
	 */
	public static String getStackTrace(Throwable ex) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pilaMensajes = new PrintWriter(sWriter);
		ex.printStackTrace(pilaMensajes);
		return sWriter.toString(); 
	}
	
	/**
	 * Devuelve la descripcion corta del error
	 * @param e
	 * @return
	 */
	public static String descError(Throwable e) {
		return e.getCause()!=null?e.getCause().getMessage():e.getMessage();
	}
    
	/**
	 * Setting error General
	 * @param result
	 * @return
	 */
	public static String getErrorGeneral(BindingResult result) {
		if (result.hasErrors()){
			FieldError error = result.getFieldError(Constantes.EXCEPCION);
			if (!Validaciones.isObjectNull(error)) {
				return error.getCode(); 
			}
		}
		return null;
	}
	
	/**
	 * Me permite tracear el tiempo de ejecuciones
	 * @param fechaHoraInicioNanoTime
	 * 
	 * Ejemplo
	 * double fechaHoraInicioNanoTime = System.nanoTime();
	 * ... ejecucion ...
	 * tracearTiempo(clase.class o getClass(), metodo, fechaHoraInicioNanoTime);
	 * 
	 */
	public static void tracearTiempo(Class<?> clase, String metodo, double fechaHoraInicioNanoTime) {
		double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
		String detalle = "Tiempo: " + ((double) elapsedTime / 1000000000.0);
		
		Traza.tiempo(clase, metodo, detalle);
	}
	
    
    /****************************************************************************************************
     * Tratamiento de cadenas
     */
    /**
	 * Devolver el valor de una cadena separada por un caracter
	 * dada una posicion 
	 */
	public static String getStringOfArray(String cadena, String regx, int position) {
		String value = "";
		if (Validaciones.isNullOrEmpty(cadena)) {
			return value;
		} else {
			String[] array = cadena.split(regx);
			int count=0;
			for (String temp : array) {
				if (count == position) {
					return temp;
				}
				count++;
			}
		}
		return value;
	}
	
    /**
     * Removes the starting zeros and set decimal.
     * @param s the string
     * @param cant1 Cantidad de caracteres minima
     * @param cant2 Cantidad de decimales luego de la que agrega "."
     * @return the string
     */
    public static String removeStartingZerosAndSetDecimal(final String s,
                                                          final int cant1,
                                                          final int cant2) {

        return setDecimal(removeStartingZeros(s, cant1), cant2);
    }

    /**
     * Removes the starting zeros.
     * @param string the string
     * @param cant Cantidad de caracteres minima
     * @return the string
     */
    public static String removeStartingZeros(final String string,
                                             final int cant) {

        String text = string;
        while (((text.startsWith(ZERO_CHARACTER) || (text
            .startsWith(SPACE_CHARACTER)))) && (text.length() > cant)) {
            text = text.substring(1, text.length());
        }
        if (text.length() == 0) {
            text = ZERO_CHARACTER;
        }
        return text;
    }
    /**
     * Add the starting zeros.
     * @param string the string
     * @param cant Cantidad de caracteres del string final
     * @return
     */
    public static String addStartingZeros(final String string, final int cant) {
    	ArrayList<String> mask = new ArrayList<String>();
    	String result = "";
    	for(int i = 0; i < cant; i++) {
    		mask.add("0");
    	}
    	if (string.length() == cant) {
    		result = string;
    	} else if (string.length() < cant) {
	    	for (int i = cant - 1, j = 1; i > 0 ; i--, j++) {
	    		if (j <= string.length()) {
	    			mask.set(i, ""+ string.charAt(string.length() - j));
	    		} else {
	    			break;
	    		}
	    	}
	    	StringBuilder sb = new StringBuilder();
	    	for (String str : mask) {
	    		sb.append(str);
	    	}
	    	result = sb.toString();
    	}
    	return result;
    };
    /**
     * Elimina todos los 0 del principio y no le agrega un 0 si esta vacia.
     * @param string the string
     * @param cant Cantidad de caracteres minima
     * @return the string
     */
    public static String removeStartingZeros(final String string) {

        String text = string;
        while (((text.startsWith(ZERO_CHARACTER) || (text
            .startsWith(SPACE_CHARACTER)))) && (text.length() > 1)) {
            text = text.substring(1, text.length());
        }
        if (text.length() == 0) {
            text = "";
        }
        return text;
    }
    /**
     * Reemplaza un "." por ",", despues agrega la cantidad de decimales,
     * Si la longitud es menor completa con ceros.
     * @param string the string
     * @param cant Cantidad de decimales luego de la que agrega "."
     * @return the string
     */
    public static String setDecimal(final String string, final int cant) {

        String text = string;
        if(text.contains(".")){
            text = text.replace(".", ",");
        }
        if ((text.indexOf(DECIMAL_CHARACTER) < 0) && (text.length() > 0)) {
            int length = text.length();
            if (length > cant) {
                text =
                    text.substring(0, text.length() - cant) + DECIMAL_CHARACTER
                        + text.substring(text.length() - cant);
            } else {
                StringBuilder sb = new StringBuilder(ZERO_CHARACTER);
                sb.append(DECIMAL_CHARACTER);
                int add = cant - length;
                for (int i = 0; i < add; i++) {
                    sb.append(ZERO_CHARACTER);
                }
                sb.append(text);
                text = sb.toString();
            }
        }
        return text;
    }
    
    public static String removerCaracteresEspeciales(String str) {
    	return EXCLUIR_CARACTERES_ESPECIALES_PATTERN.matcher(str).replaceAll("");
    }
    
    /**
     * Agrego ... si el valor supera la cantidad maxima de caracteres
     * @param value
     * @param maxString
     * @return
     */
    public static String addTreePointsToString(String value, int maxString) {
    	String stringShort = value;
        if (stringShort.length() > maxString) {
        	return "..." + stringShort.substring(stringShort.length()-maxString, stringShort.length());
        }
        return value;
    }
    
    /**
     * Agrego ... si el valor supera la cantidad maxima de caracteres (PATH)
     * @param value
     * @param maxString
     * @return
     */
    public static String addTreePointsToStringPath(String value, int maxString) {
    	String stringShort = value.toString().substring(
        		value.toString().lastIndexOf("\\")+1, value.toString().length());
        stringShort = stringShort.toString().substring(
        		stringShort.toString().lastIndexOf("/")+1, stringShort.toString().length());
        
        if (stringShort.length() > maxString) {
        	return "..." + stringShort.substring(stringShort.length()-maxString, stringShort.length());
        }
        return stringShort;
    }
    
    /**
     * Obtiene el nombre de archivo del PATH completo
     * @param value
     * @param maxString
     * @return
     */
    public static String toStringPath(String value) {
    	String stringShort = value.toString().substring(
        		value.toString().lastIndexOf("\\")+1, value.toString().length());
        stringShort = stringShort.toString().substring(
        		stringShort.toString().lastIndexOf("/")+1, stringShort.toString().length());
        
        return stringShort;
    }
    
    /**
     * Getting servlet request contextPath
     */
    public static String requestContextPath(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.indexOf(request.getServletPath()));
		
		return url;
	}
    
    /**
	 * Este método reemplaza el {0} por un valor ingresado
	 * Recibe un mensaje con un {0}, y un valor a reemplazar. 
	 * @param constant
	 * @param variable
	 */
//	public static String reemplazarMensajes(String mensaje, String variable) {
//		String newconstant = new String(mensaje);
//		newconstant = newconstant.replace("{0}", variable);
//		return newconstant;
//	}
//    
//	/**
//	 * Este método reemplaza el {0}/{1} por valores ingresados respectivamente
//	 * @param constant
//	 * @param variable
//	 */
//	public static String reemplazarMensajes(String mensaje, String variable0, String variable1) {
//		String newconstant = "";
//		if(!Validaciones.isNullOrEmpty(mensaje)){
//			newconstant = new String(mensaje);
//			newconstant = newconstant.replace("{0}", variable0);
//			newconstant = newconstant.replace("{1}", variable1);
//		}
//		
//		return newconstant;
//	}
//	
//	/**
//	 * Este método reemplaza el {0}/{1}/{2} por valores ingresados respectivamente
//	 * @param constant
//	 * @param variable
//	 */
//	public static String reemplazarMensajes(String mensaje, String variable0, String variable1, String variable2) {
//		String newconstant = new String(mensaje);
//		newconstant = newconstant.replace("{0}", variable0);
//		newconstant = newconstant.replace("{1}", variable1);
//		newconstant = newconstant.replace("{2}", variable2);
//		return newconstant;
//	}
//	
//	/**
//	 * Este método reemplaza el {0}/{1}/{2}/{3} por valores ingresados respectivamente
//	 * @param constant
//	 * @param variable
//	 */
//	public static String reemplazarMensajes(String mensaje, String variable0, String variable1, String variable2, String variable3) {
//		String newconstant = new String(mensaje);
//		newconstant = newconstant.replace("{0}", variable0);
//		newconstant = newconstant.replace("{1}", variable1);
//		newconstant = newconstant.replace("{2}", variable2);
//		newconstant = newconstant.replace("{3}", variable3);
//		return newconstant;
//	}
	
	/**
	 * Este método reemplaza el {0}/{1}/{2}/{3}/{4} por valores ingresados respectivamente
	 * @param constant
	 * @param variable
	 */
    public static String reemplazarMensajes(String mensaje, String ... variables) {
		String newconstant = new String(mensaje);
		for (int indice = 0; indice < variables.length; indice++) {
			newconstant = newconstant.replace("{" +indice+ "}", variables[indice]);
		}
		return newconstant;
	}
	
    /**
	 * Este método reemplaza los {1}.. {n} por los valores
	 * Recibe un mensaje, definidas en Constants y un map,
	 * con clave siendo: {1}, {2}... {n} y sus respectivos valores. 
	 * @param constant
	 * @param variable
	 */
	public static String reemplazarMensajes(String mensaje, Map<String, String> variables) {
		String newconstant = new String(mensaje);
		Iterator<String> iterator = variables.keySet().iterator();
		while (iterator.hasNext()){
			String key = (String) iterator.next();
			try{
				newconstant = newconstant.replace(key, (String) variables.get(key));
			}catch(NullPointerException e){
				newconstant = newconstant.replace(key, "null");
			}
		}
		return newconstant;
	}
	
	/**
	 * Retorna valor en caso de existir, de no existir retorna ""
	 * @param valor
	 * @return
	 */
	public static String generarSalidaConValorOVacio(Object valor) {
		String str = String.valueOf(valor);
		String retorno = !Validaciones.isNullOrEmpty(str)?str:"";
		return retorno;
	}
	
	/**
	 * Convierte bigdecimal a 999999999,99999
	 * @param d
	 * @param cantDecimales
	 * @return
	 */
	public static String formatDecimales(BigDecimal d, int cantDecimales) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		String unCero = "0";
    		String cantCeros = "";
    		
    		for(int i = 0; i < cantDecimales; i++){
    			cantCeros += unCero;
    		}
    		
    		DecimalFormat df = new DecimalFormat("#0." + cantCeros);

    		String result = df.format(d);
    		// Como se usa en otros servidor que no el locate este seteado en otra region
    		// fuerzo a que se el separador decimal sea el ","
    		return result.replace(".", ",");
    	} else {
    		return EMPTY_STRING;
    	}
    }
	
	/**
	 * Convierte bigdecimal a un valor en centavos
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDecimalesACentavosComoTexto(BigDecimal d) {
    	
		int cantDecimales = 2;
		
		if(!Validaciones.isObjectNull(d)){
    		
    		String unCero = "0";
    		String cantCeros = "";
    		
    		for(int i = 0; i < cantDecimales; i++){
    			cantCeros += unCero;
    		}
    		
    		DecimalFormat df = new DecimalFormat("#0." + cantCeros);

    		String result = df.format(d);
    		// Como se usa en otros servidor que no el locate este seteado en otra region
    		// fuerzo a que se el separador decimal sea el ","
    		// reemplazo cualquier punto o coma que puediera quedar por nada, de manera de mostrar centavos
    		return result.replace(".", "").replace(",", "");
    	} else {
    		return EMPTY_STRING;
    	}
    }
	
	
    /**********************************************************************************
     * Formatos currency
     **********************************************************************************/
    
	/**
	 * Convierte bigdecimal a $999.999.999,99, y le agrego el simbolo de moneda
	 * que venga informado
	 * 
	 * @param d
	 * @param cantDecimales
	 * @return
	 */
	public static String formatCurrency(BigDecimal d, int cantDecimales, String simboloMoneda) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		//BigDecimal decimalAux = d.movePointLeft(cantDecimales);
    		String ret = new DecimalFormat("#,##0.00").format(d);
    		
    		//Como es muy raro que el formateo se convierta en 999.999.999,99 en forma local
    		//por eso si en otros servidores salen como 999,999,999.99  
    		if (".".equals(ret.substring((ret.length()-3),(ret.length()-2)))) {
    			//999|999|999.99
    			//999|999|999,99
        		//999.999.999,99
    			String formato = ret.replace(",", "|").replace(".", ",").replace("|", ".");
    			ret = formato;
    		}
    		
    		return simboloMoneda + ret;
    	} else {
    		return EMPTY_STRING;
    	}
    }

	/**
	 * Convierte bigdecimal a $999.999.999,99
	 * @param d
	 * @param cantDecimales...!!! No se toma encuenta por defecto son 2 decimales u578936
	 * @return
	 */
	public static String formatCurrency(BigDecimal d, int cantDecimales) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		//BigDecimal decimalAux = d.movePointLeft(cantDecimales);
    		String ret = new DecimalFormat("#,##0.00").format(d);
    		
    		//Como es muy raro que el formateo se convierta en 999.999.999,99 en forma local
    		//por eso si en otros servidores salen como 999,999,999.99  
    		if (".".equals(ret.substring((ret.length()-3),(ret.length()-2)))) {
    			//999|999|999.99
    			//999|999|999,99
        		//999.999.999,99
    			String formato = ret.replace(",", "|").replace(".", ",").replace("|", ".");
    			ret = formato;
    		}
    		
    		return PESOS_CHAR + ret;
    	} else {
    		return EMPTY_STRING;
    	}
    }
    
	/**
     * Saco Pesos del currency
     * @param currency
     * @return
     */
    public static String formatCurrencySacarPesos(String currency) {
    	if(!Validaciones.isObjectNull(currency)){   		
    		String newCurrency = currency.replace(PESOS_CHAR, "");
    		return  newCurrency;
    	} else {
    		return EMPTY_STRING;
    	}
    }
    
    /**
     * Saco los puntos del currency
     * @param d
     * @return
     */
    public static String formatCurrencySacarPunto(String d) {
    	if(!Validaciones.isNullOrEmpty(d)){
    		String val = d.toString().replace(".", "");
    		return val;
    	} else {
    		return "0";
    	}
    }
    
    /**
     * Saco la coma puntos del currency
     * @param d
     * @return
     */
    public static String formatCurrencySacarComa(String d) {
    	if(!Validaciones.isNullOrEmpty(d)){
    		String val = d.toString().replace(",", "");
    		return val;
    	} else {
    		return "0";
    	}
    }
    
    /**
     * Convierte bigdecimal a $999999999,99 si mostrarPesos = true y mostrarPuntos = false 
     * Convierte bigdecimal a $999.999.999,99 si mostrarPesos = true y mostrarPuntos = true
     * Convierte bigdecimal a 999999999,99 si mostrarPesos = false y mostrarPuntos = false
     * Convierte bigdecimal a 999.999.999,99 si mostrarPesos = false y mostrarPuntos = true
     * @param d
     * @param mostrarPesos
     * @param mostrarPuntos
     * @return
     */
    public static String formatCurrency(BigDecimal d, boolean mostrarPesos, boolean mostrarPuntos) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		String ret = formatCurrency(d, 2);
    		
    		if (!mostrarPesos) {
    			ret = formatCurrencySacarPesos(ret);
    		}
    		
    		if (!mostrarPuntos) {
    			ret = formatCurrencySacarPunto(ret);
    		}
    		return ret;
    	} else {
    		return EMPTY_STRING;
    	}
    }
    
    /**
     * Convierte $999.999.999,9900 a $999999999,9900 si mostrarPesos = true y mostrarPuntos = false 
     * Convierte $999.999.999,9900 a $999.999.999,9900 si mostrarPesos = true y mostrarPuntos = true
     * Convierte $999.999.999,9900 a 999999999,9900 si mostrarPesos = false y mostrarPuntos = false
     * Convierte $999.999.999,9900 a 999.999.999,9900 si mostrarPesos = false y mostrarPuntos = true
     * @param d
     * @param mostrarPesos
     * @param mostrarPuntos
     * @param cantDecimales
     * @return
     */
    public static String formatCurrency(BigDecimal d, boolean mostrarPesos, boolean mostrarPuntos, int cantDecimales) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		BigDecimal auxD = d.setScale(cantDecimales, RoundingMode.HALF_EVEN);
    		String ret = new DecimalFormat("#,##0.00").format(auxD);
    		
    		//Como es muy raro que el formateo se convierta en 999.999.999,99 en forma local
    		//por eso si en otros servidores salen como 999,999,999.99
    		if (".".equals(ret.substring((ret.length()-3),(ret.length()-2)))) {
    			//999|999|999.9900
    			//999|999|999,9900
        		//999.999.999,9900
    			String formato = ret.replace(",", "|").replace(".", ",").replace("|", ".");
    			ret = formato;
    		}
    		
    		if (!mostrarPesos) {
    			ret = formatCurrencySacarPesos(ret);
    		}
    		
    		if (!mostrarPuntos) {
    			ret = formatCurrencySacarPunto(ret);
    		}
    		
    		return ret;
    	} else {
    		return EMPTY_STRING;
    	}
    }

    /**
     * Convierte $999.999.999,99 a $999999999,99 si mostrarPesos = true y mostrarPuntos = false 
     * Convierte $999.999.999,99 a $999.999.999,99 si mostrarPesos = true y mostrarPuntos = true
     * Convierte $999.999.999,99 a 999999999,99 si mostrarPesos = false y mostrarPuntos = false
     * Convierte $999.999.999,99 a 999.999.999,99 si mostrarPesos = false y mostrarPuntos = true
     * @param d
     * @param mostrarPesos
     * @param mostrarPuntos
     * @return
     */
    public static String formatCurrency(String s, boolean mostrarPesos, boolean mostrarPuntos) {
    	
    	if(!Validaciones.isObjectNull(s)){
    		
    		String ret = s;
    		if (!mostrarPesos) {
    			ret = formatCurrencySacarPesos(ret);
    		}
    		if (!mostrarPuntos) {
    			ret = formatCurrencySacarPunto(ret);
    		}
    		return ret;
    	} else {
    		return EMPTY_STRING;
    	}
    }
    
    /**
     * Devuelve un numero de N decimales (moviendo la cantidad de posiciones)
     * @param d (Numero sin decimales)
     * @param cantDecimales (cant de decimales a mover)
     * @return
     */
    public static BigDecimal formatCurrencyBD(BigDecimal d, int cantDecimales) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		BigDecimal ret = d.movePointLeft(cantDecimales);
    		return ret;
    	} else {
    		d = new BigDecimal(0);
    		
    		BigDecimal ret = d.setScale(cantDecimales);
    		return ret;
    	}
    }
    
    /**
     * Devuelve un numero de N decimales (agregando la cantidad de decimales)
     * @param d (Numero sin decimales)
     * @param cantDecimales (cant de decimales a mover)
     * @return
     */
    public static BigDecimal formatCurrencyBDAgregandoDecimales(BigDecimal d, int cantDecimales) {
    	
    	if(!Validaciones.isObjectNull(d)){
    		
    		BigDecimal ret = d.setScale(cantDecimales, RoundingMode.HALF_EVEN);
    		return ret;
    	} else {
    		d = new BigDecimal(0);
    		BigDecimal ret = d.setScale(cantDecimales, RoundingMode.HALF_EVEN);
    		return ret;
    	}
    }
    
    
    
    
    /**
     * Devuelve en formato string, un numero de N decimales (moviendo la cantidad de posiciones)
     * @param d (Numero sin decimales)
     * @param cantDecimales (cant de decimales a mover)
     * @return
     */
    public static String getCurrencyFromBDToString(BigDecimal d, int cantDecimales) {
    	if(!Validaciones.isObjectNull(d)){
    		
    		BigDecimal decimalAux = d.movePointLeft(cantDecimales);
    		return decimalAux.toString();
    	} else {
    		return EMPTY_STRING;
    	}
    }
    
    /**
     * Permite quitar la coma decimal
     * @param d
     * @return
     */
    public static String quitarComaDecimal(String d) {
    	if(!Validaciones.isNullOrEmpty(d)){
    		String val = d.toString().replace(",", "");
    		return val;
    	} else {
    		return "0";
    	}
    }
    
    /**
     * Permite cambiar el punto quitar la coma decimal
     * @param d
     * @return
     */
    public static String cambiarPuntoDecimalPorComa(String d) {
    	if(!Validaciones.isNullOrEmpty(d)){
    		String val = d.toString().replace(".", ",");
    		return val;
    	} else {
    		return "0";
    	}
    }

    /**
    * Convierte un tipo de dato String a BigDecimal.
    * @param num
    * @return BigDecimal
    */
	public static BigDecimal stringToBigDecimal(String num) {
		BigDecimal decimal = BigDecimal.ZERO;
		
		if (!Validaciones.isNullOrEmpty(num)) {
			if(Utilidad.PESOS_CHAR.equalsIgnoreCase(num.substring(0, 1))) {
				num = num.substring(1);
			}else if(num.length() > 3 && Utilidad.DOLAR_CHAR.equalsIgnoreCase(num.substring(0, 3))) {
				num = num.substring(3);
			}
			// sino esta vacio entonces
			if (!num.isEmpty()) {
				 //primero elimina los puntos y luego remplaza las comas en puntos.
				String formatoValido = num.replace(".", "").replace(",", ".");
				decimal = new BigDecimal(formatoValido);
			}
		}
		return decimal;
	}
    
	
	/**
    * Convierte un tipo de dato String a BigDecimal.
    * @param num
    * @return BigDecimal
    */
	@Deprecated
	public static BigDecimal stringToBigDecimalSinEliminarLosPuntos(String num) {
		BigDecimal decimal = BigDecimal.ZERO;
		
		if(Utilidad.PESOS_CHAR.equalsIgnoreCase(num.substring(0, 1))){
			num = num.substring(1);
		}
		// sino esta vacio entonces
		if (!num.isEmpty()) {
			 //Remplaza la comas en punto.
			String formatoValido = num.replace(",", ".");
			decimal = new BigDecimal(formatoValido);
		}
		return decimal;
	}
		
	/**
	 * Me permite truncar n a una cadena
	 * @param cadena
	 * @param cantCaracteres
	 * @return
	 */
	public static String truncarCadena(String cadena, int cantCaracteres) {
		if (!Validaciones.isNullOrEmpty(cadena)) {
			if (cadena.trim().length() > cantCaracteres) {
				return cadena.trim().substring(0, cantCaracteres);
			} else {
				return cadena.trim();
			}
		}
		return cadena;
	}
	/**
	 * Me permite truncar n a una cadena y si si cadenaRemplazo
	 * con catena al final
	 * @param cadena
	 * @param cantCaracteres
	 * @param cadenaRemplazo
	 * @return
	 */
	public static String truncarCadena(String cadena, int cantCaracteres, String cadenaRemplazo) {
		if (!Validaciones.isNullOrEmpty(cadena)) {
			if (cadena.trim().length() > cantCaracteres) {
				String cadenaAux = null;
	
				cadenaAux = cadena.trim().substring(0, cantCaracteres);
				if (!Validaciones.isObjectNull(cadenaRemplazo)) {
					return cadenaAux.concat(cadenaRemplazo);
				} else {
					return cadenaAux;
				}
			} else {
				return cadena.trim();
			}
		}
		return cadena;
	}
	
    /****************************************************************************************************
     * Formatos Fechas
     */

    /**
     * Deserialize hour (String a String)
     * @param hour the hour
     * @return the string
     */
    public static String deserializeHour(final String hour) {

        StringBuilder sb = new StringBuilder();
        if (hour != null) {
            for (int i = 0, j = 2; j <= hour.length(); i += 2, j += 2) {
                sb.append(hour.substring(i, j));
                if (j < hour.length()) {
                    sb.append(DOUBLE_POINT_CHARACTER);
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * Deserialize hour (BigDecimal a String)
     * @param hour the hour
     * @return the string
     */
    public static String deserializeDecimalHour(final BigDecimal hour) {
        StringBuilder sb = new StringBuilder();
        sb.append(rellenarCerosDerecha(hour.toString(),6));
        sb.insert(sb.length()-4, DOUBLE_POINT_CHARACTER);
        sb.insert(sb.length()-2, DOUBLE_POINT_CHARACTER);
        
        return sb.toString();
    }
    
    /**
     * Parsea la fecha YYYYMMDD (String a Date)
     * @param date String
     * @return Date
     */
    public static Date deserializeAndFormatDate(final String date) {

        if (EMPTY_STRING.equals(date.trim()) || "00000000".equals(date.trim()) || date.length()!=8) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month =
            Integer.parseInt(date.substring(4, 6)) - 1;
        int day =
            Integer.parseInt(date.substring(6, 8));
        c.set(year, month, day);

        return c.getTime();
    }
    
    /**
     * Parsea la fecha (String a Date). Utilizar para el formato de fecha del DatePicker
     * @param date String
     * @return Date
     */
    public static String deserializeAndFormatDatePicker(final String date) {
    	
    	if (EMPTY_STRING.equals(date.trim())) {
    		return null;
    	}
    	
    	//Calendar c = Calendar.getInstance();
    	int year = Integer.parseInt(date.substring(6, 10));
    	int month =
    			Integer.parseInt(date.substring(3, 5));
    	int day =
    			Integer.parseInt(date.substring(0, 2));
    	//c.set(year, month, day);
    	
    	//return c.getTime();
    	
    	return year + "-" + month + "-" + day + " 00:00:00";
    }

    /**
     * Parsea la fecha (String a Date)
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date deserializeAndFormatDate(String date,String pattern) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	sdf.setLenient(false);
    	return sdf.parse(date);
    }
    
    /**
     * Parsea la fecha (BigDecimal a Date)
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date deserializeAndFormatDate(BigDecimal date,String pattern) throws ParseException{
    	if(Validaciones.isObjectNull(date)){
    		return null;
    	}
    	return deserializeAndFormatDate(date.toString(), pattern);
    }
    
    /**
     * Parsea la fecha completa (String a Date)
     * @param date Date
     * @param time String
     * @return Date
     */
    public static Date deserializeCompleteHour(final String date,
                                               final String time) {

        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month =
            Integer.parseInt(date.substring(4, 6)) - 1;
        int day =
            Integer.parseInt(date.substring(6, 8));
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute =
            Integer.parseInt(time.substring(2, 4));
        int second =
            Integer.parseInt(time.substring(4, 6));
        c.set(year, month, day, hour, minute, second);

        return c.getTime();
    }
    
    /**
     * @deprecated
     * Parsea la fecha completa (YYYY/MM/DD a Date)
     * se deja de usar ya que puede generar el problema java.lang.OutOfMemoryError: GC overhead limit exceeded
     * @param date Date
     * @param time String
     * @return Date
     */
    public static Date deserializarFechaYYYYMMDDconBarra(final String date) {

        Calendar c = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4));
        int month =
            Integer.parseInt(date.substring(5, 7)) - 1;
        int day =
            Integer.parseInt(date.substring(8, 10));
        
        c.set(year, month, day);

        return c.getTime();
    }
    
    /**
    * Parsea la fecha corta (Date a String)
    * @param date Date
    * @return String
    */
   public static String formatDate(final Date date) {

       if (date == null) {
           return null;
       }
       SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_FORMAT);
       return dateFormatAll.format(date);
   }
   /**
    * Parsea la fecha y le agrega 4 digitos al año
    * @param date Date
    * @return String
    */
   public static String formatDateDigitosYears(String dates) {

	   try {  
		   SimpleDateFormat twoDigitYear = new SimpleDateFormat("dd-MM-yy");
		   twoDigitYear.setLenient(false);
		   Date dates1;
		   dates1 = twoDigitYear.parse(dates);		
		   SimpleDateFormat fourDigitYear = new SimpleDateFormat("dd/MM/yyyy");	
		   return fourDigitYear.format(dates1);
		  
	   } catch (ParseException e) {		
		   Traza.error(Utilidad.class, "Error al parsear: ", e);
	   }
	   return null;
   }
   
   /**
    * Parsea la fecha corta (Date a String)
    * @param date Date
    * @return String
    */
   public static String formatDatePicker(final Date date) {
	   
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_FORMAT_PICKER);
	   return dateFormatAll.format(date);
   }
   
   /**
    * Parsea la fecha a AAAA/MM/DD
    * @param date
    * @return
    */
   public static String formatDateAAAAMMDDconBarra(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.ANO_MES_DIA_BARRA);
	   return dateFormatAll.format(date);
   }
   
   /**
    * Parsea la fecha a AAAA-MM-DD
    * @param date
    * @return
    */
   public static String formatDateAAAAMMDDconDash(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.ANO_MES_DIA_DASH);
	   return dateFormatAll.format(date);
   }
   
   /** 
    * Parsea la fecha con el formato AAAA-MM-DD a Date
    * @param String
    * @return
    */
   public static Date formatStringconDashtoDate(final String fechaString) throws ParseException{
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(ANO_MES_DIA_DASH);
	   dateFormatAll.setLenient(false);
	   return dateFormatAll.parse(fechaString);
   }
   
   /**
    * Parsea la fecha a AAMMDD
    * @param date
    * @return
    */
   public static String formatDateAAMMDD(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.ANO_CORTO_MES_DIA);
	   return dateFormatAll.format(date);
   }
   
   /**
    * Parsea la fecha a AAAAMMDD
    * @param date
    * @return
    */
   public static String formatDateAAAAMMDD(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.ANO_MES_DIA);
	   return dateFormatAll.format(date);
   }
   
   /**
    * Parsea la fecha a AAAAMMDD
    * @param date
    * @return
    */
   public static String formatDateDDMMAAAA(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DIA_MES_ANO);
	   return dateFormatAll.format(date);
   }

   /**
    * Parsea la fecha a DDMMAAAA con barra
    * @param date
    * @return
    */
   
   public static String formatDateDDMMAAAAconBarra(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_FORMAT_PICKER);
	   return dateFormatAll.format(date);
   }
   
   /**
    * Parsea la fecha a HHMMSSTH
    * @param date
    * @return
    */
   public static String formatDateHHMMSSTH(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.HORA_MIN_SEG_TH);
	   String cadena = dateFormatAll.format(date);
	   if (cadena.length() > 8) {
		   return cadena.substring(0, 8);
	   } else {
		   return rellenarCerosDerecha(cadena, 8);
	   }
   }
   /**
    * Parsea la fecha a HHMMSSTH
    * @param date
    * @return
    */
   public static String formatDateHHMMSSTH2(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.HORA_MIN_SEG_TH2);
	   String cadena = dateFormatAll.format(date);
	   if (cadena.length() > 8) {
		   return cadena.substring(0, 8);
	   } else {
		   return rellenarCerosDerecha(cadena, 8);
	   }
   }
   /**
    * Parsea la fecha a HHMMSSTH3
    * @param date
    * @return
    */
   public static String formatDateHHMMSSTH3(final Date date) {
	   if (date == null) {
		   return null;
	   }
	   SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.HORA_MIN_SEG_TH3);
	   return dateFormatAll.format(date);
   }
   /**
   * Parsea la fecha completa (Date a String)
   * @param date Date
   * @return String
   */
   public static String formatDateAndTime(final Date date) {

      if (date == null) {
          return null;
      }
      SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_FORMAT);
      return dateFormatAll.format(date);
  	}
   	
   	/**
    * Parsea la fecha completa (Date a String)
    * @param date Date
    * @return String
    */
   	public static String formatDateAndTimeFull(final Date date) {

       if (date == null) {
           return null;
       }
       SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_FULL_FORMAT);
       return dateFormatAll.format(date);
   	}
   	
	/**
	 * @author u572487 Guido.Settecerze
     * Parsea la fecha String de yyyy-mm-dd hh24:mi:ss.S a dd/MM/yyyy hh24:mi
     * @param date Date
     * @return String
     */
   	public static String formatDateAndTimeFullFromDataBase(final String fechaString) {
   		String fechaFinal = null;
   		String anio = null;
   		String mes = null;
   		String dia = null;
   		String hora = null;
   		String minuto = null;
   		
   		String[] split = fechaString.split(" ");
   		String fechaGuiones = split[0];
   		String horaSplit = split[1];
   		String[] splitFecha = fechaGuiones.split("-");
   		anio = splitFecha[0]; 
   		mes = splitFecha[1]; 
   		dia = splitFecha[2]; 
   		
   		String[] splitHora = horaSplit.split(":");
   		hora = splitHora[0];
   		minuto = splitHora[1];
   		
   		fechaFinal = dia + "/" + mes + "/" + anio + " " + hora + ":" + minuto;
   		
   		return fechaFinal;
   	}

   	/**
    * Parsea la fecha completa (Date a String)
    * @param date Date
    * @return String
    */
   	public static String formatDateAndTimeFullMilliseconds(final Date date) {

       if (date == null) {
           return null;
       }
       SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_FULL_FORMAT_MILLISECONDS);
       return dateFormatAll.format(date);
   	}
   	
   	/**
    * Parsea la fecha completa (Date a String)
    * @param date Date
    * @return String
    */
   	public static String formatDateAndTimeFullMillisecondsGuiones(final Date date) {

       if (date == null) {
           return null;
       }
       SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_FULL_FORMAT_MILLISECONDS_GUIONES);
       return dateFormatAll.format(date);
   	}
   	
   	/**
    * Parsea la fecha completa (Date a String)
    * @param date Date
    * @return String
    */
   	public static String formatDateAndTimeFullBarra(final Date date) {

       if (date == null) {
           return null;
       }
       SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_FULL_FORMAT_BARRA);
       return dateFormatAll.format(date);
   	}
   	
   	/**
   	 * Parsea la fecha completa (Date a String)
   	 * @param date Date
   	 * @return String  El string es del formato AAAAMMDD_HHMMSS.
   	 */
   	public static String formatDateAAAAMMDD_HHmmss(final Date date) {
   		
   		if (date == null) {
   			return null;
   		}
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.AAAAMMDD_HHmmss);
   		return dateFormatAll.format(date);
   	}
   	
   	/**
   	 * Parsea la fecha completa (Date a String)
   	 * @param date Date
   	 * @return String  El string es del formato dd.mm.aaaa
   	 */
   	public static String formatDatePointSeparated(final Date date) {
   		
   		if (date == null) {
   			return null;
   		}
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_FORMAT_POINT);
   		return dateFormatAll.format(date);
   	}
   	public static String formatDateGeneral(final Date date, final String format) {
   		
   		if (date == null) {
   			return null;
   		}
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(format);
   		return dateFormatAll.format(date);
   	}
   	/**
   	 * Parsea la fecha completa (String a Date)
   	 * @param String El string debe ser del formato dd.mm.aaaa
   	 * @return Date  date 
   	 */
	public static Date parseDateFormatPoint(String dateformatPoint) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_POINT);
		dateFormat.setLenient(false);
		
		return dateFormat.parse(dateformatPoint);
	}
	
   	public static String formatDatePointSeparated(int number) {
   		int[] array = extracDateFromNumberYYYYMMDD(number);
   		return rellenarCerosIzquierda(String.valueOf(array[2]),2)+"."+rellenarCerosIzquierda(String.valueOf(array[1]),2)+"."+array[0];
   	}
   	/**
   	 * retorna un array con el siguiente orden 0.año 1.mes 2.dia
   	 * @param number
   	 * @return
   	 */
   	public static int[] extracDateFromNumberYYYYMMDD(int number){
   		if (number< 10000000){
   			return null;
   		} else {
   			int year = number/10000;
   			int month = (number%10000)/100;
   			int day = (number%10000)%100;
   			int[] resultado= {year,month,day};
   			return resultado;
   		}
   	}
	/**
  	 * Parsea la fecha (String --> Date). El string debe ser del formato DD/MM/AAAA.
  	 * @param dateString
  	 * @return
  	 * @throws ParseException
  	 */
   	public static Date parseDatePickerString(String dateString) throws ParseException{
   		if (Validaciones.isNullEmptyOrDash(dateString)) {
   			return null;
   		}
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_FORMAT_PICKER);
   		dateFormatAll.setLenient(false);
	   	return dateFormatAll.parse(dateString);
   	}
	
  	/**
  	 * Parsea la fecha (String --> Date). El string debe ser del formato MM/AAAA.
  	 * @param dateString
  	 * @return
  	 * @throws ParseException
  	 */
   	public static Date parseMesAnioBarraString(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(MES_ANIO_BARRA);
   		dateFormatAll.setLenient(false);
	   	return dateFormatAll.parse(dateString);
   	}
   	
  	/**
  	 * Parsea la fecha (String --> Date). El string debe ser del formato yyyy/MM/dd.
  	 * @param dateString
  	 * @return
  	 * @throws ParseException
  	 */
   	public static Date parseFechaBarraString(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(ANO_MES_DIA_BARRA);
   		dateFormatAll.setLenient(false);
	   	return dateFormatAll.parse(dateString);
   	}
   	
  	/**
  	 * Recibe un string con yyyy-MM-dd-HH.mm.ss.SSS
  	 * Los ultimos digitos de milisegundos son solo 3 si no tira error de parseo.
  	 * @param dateString
  	 * @return
  	 * @throws ParseException
  	 */
   	public static Date parseDateFullFormatMillisecondsGuiones(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_TIME_FULL_FORMAT_MILLISECONDS_GUIONES);
   		dateFormatAll.setLenient(false);
	   	return dateFormatAll.parse(dateString);
   	}
   	
 	/**
  	 * @param dateString
  	 * @return
  	 * @throws ParseException
  	 */
   	public static Date parseDateFullFormatBarra(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_TIME_FULL_FORMAT_BARRA);
   		dateFormatAll.setLenient(false);
	   	return dateFormatAll.parse(dateString);
   	}
   	
   	/**
   	 * Parsea la fecha (String --> Date). El string debe ser del formato AAAAMMDD.
   	 * @param dateString
   	 * @return
   	 * @throws ParseException
   	 */
   	public static Date parseDateWSFormat(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(ANO_MES_DIA);
   		dateFormatAll.setLenient(false);
   		return dateFormatAll.parse(dateString);
   	}
   	
   	/**
   	 * Parsea la fecha (String --> Date)
   	 * @param dateString
   	 * @return
   	 * @throws ParseException
   	 */
   	public static Date parseDateString(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_FORMAT);
   		dateFormatAll.setLenient(false);
   		return dateFormatAll.parse(dateString);
   	}
   	
   	/**
   	 * Parsea la fecha (String --> Date)
   	 * @param dateString
   	 * @return
   	 * @throws ParseException
   	 */
   	public static Date parseDateStringCustomFormat(String dateString, String formato) throws ParseException {
   		
   		Date fecha = null;
   		
		try {
			if (!Utilidad.EMPTY_STRING.equals(dateString.trim())) {
				Locale locale = new Locale("ES");
				DateFormat sdf = Utilidad.getSimpleDateFormat(formato, locale);
				fecha = sdf.parse(dateString);
			}
		} catch (ParseException e) {
				Locale locale = new Locale("EN");
				DateFormat sdf = Utilidad.getSimpleDateFormat(formato, locale);
				fecha = sdf.parse(dateString);
		}
		
		return fecha;
   	}

	
   	/**
   	 * Parsea la fecha (String --> Date)
   	 * @param dateString
   	 * @return
   	 * @throws ParseException
   	 */
   	public static Date parseDateAndTimeString(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_TIME_FORMAT);
   		dateFormatAll.setLenient(false);
   		return dateFormatAll.parse(dateString);
   	}
   	
   	/**
   	 * Parsea la fecha (String --> Date)
   	 * @param dateString
   	 * @return
   	 * @throws ParseException
   	 */
   	public static Date parseDateAndTimeFullString(String dateString) throws ParseException{
   		SimpleDateFormat dateFormatAll = new SimpleDateFormat(DATE_TIME_FULL_FORMAT);
   		dateFormatAll.setLenient(false);
   		return dateFormatAll.parse(dateString);
   	}

    /**
     * Calcula la diferencia
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static Date differenceBetweenDates(Date start, Date end)
        throws ParseException {
    	
    	SimpleDateFormat sdfOnlyDate = new SimpleDateFormat(DATE_PATTERN);
    	sdfOnlyDate.setLenient(false);
        Long correctionFactor = sdfOnlyDate.parse(sdfOnlyDate.format(JAVA_START_DATE)).getTime();
        Long diff = end.getTime() - start.getTime() + correctionFactor;
        Date difference = new Date(diff);
        return difference;
    }

    
    /**
     * Calcula la diferencia
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static long calcularDiferenciaDeFechas(Date start, Date end) {
        Long diff = end.getTime() - start.getTime();
        return diff.longValue();
    }
    
    /**
     * Devuelve fecha anterior con respecto a la cantidad de dias desde la fecha ingresada
     * @param fecha
     * @param diferenciaEnDias
     * @return
     */
    public static Date restarDiasAFecha(Date fecha, int diferenciaEnDias) { 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fecha); // Configuramos la fecha que se recibe
    	calendar.add(Calendar.DAY_OF_YEAR, -diferenciaEnDias);  
    	return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
    
    /**
     * Devuelve fecha posterior con respecto a la cantidad de dias desde la fecha ingresada
     * @param fecha
     * @param diferenciaEnDias
     * @return
     */
    public static Date sumarDiasAFecha(Date fecha, int sumaDeDias) { 
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(fecha); 
    	calendar.add(Calendar.DAY_OF_YEAR, sumaDeDias);  
    	return calendar.getTime(); 
    }
    
    /**
     * Devuelve fecha anterior con respecto a la cantidad de minutos desde la fecha ingresada
     * @param fecha
     * @param diferenciaEnDias
     * @return
     */
    public static Date restMinutes(Date fecha, int diferenciaEnMinutos) { 
    	long tiempoActual = fecha.getTime();
    	long minutos =  diferenciaEnMinutos * 60 * 1000;
    	return new Date(tiempoActual - minutos);
    }
    /**
     * Determina si una fecha es menor o igual a otra.
     * Si hay error de parseo retorna false
     * @param fecha1
     * @param fecha2
     * @return
     */
    public static boolean verificarSiFech1EsMenosIgualFecha2(String fecha1, String fecha2) {
		boolean salida = false;
		try {
			if (
				Validaciones.isNullEmptyOrDash(fecha1) ||
				Validaciones.isNullEmptyOrDash(fecha2)
			) {
				return false;
			}
			Date fechaDate1 = parseDatePickerString(fecha1);
			Date fechaDate2 = parseDatePickerString(fecha2);
		
			if (fechaDate1.before(fechaDate2)) {
				salida = true;
			} else if (fechaDate1.equals(fechaDate2)) {
				salida = true;
			}
		} catch (ParseException e) {
			salida = false;
		}
		return salida;
	}
    /**
     * Devuelve la diferencia en dias entre la fecha1 y la fecha2.
     * @param fecha1
     * @param fecha2
     * @return
     */
    public static Long diferenciaDias(Date fecha1, Date fecha2) { 
    	long diferencia = fecha1.getTime() - fecha2.getTime();
		return diferencia / (24 * 60 * 60 * 1000);
    }
    
    /**
	 * Retorna la fecha actual
	 * @return 
	 */
	public static Date obtenerFechaActual(){
		Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
	}
	
	/**
	 * Verifica si una fecha se encuentra dentro de un rango definido por una fecha inicio y una fecha fin
	 * @param inicio
	 * @param fin
	 * @param fecha 
	 * @return
	 */
	public static Boolean seEncuentraEnRango(Date inicio, Date fin, Date fecha) {
    	return inicio != null && fin != null && fecha != null 
    			&& obtenerFechaEnMiliSegundos(fecha).compareTo(obtenerFechaEnMiliSegundos(inicio)) >= 0
    			&& obtenerFechaEnMiliSegundos(fin).compareTo(obtenerFechaEnMiliSegundos(fecha)) >= 0;
    }
    
	/**
	 * Obtiene una fecha representada en milisegundos
	 * @param date
	 * @return
	 */
    public static Long obtenerFechaEnMiliSegundos(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	/**
     * Incrementa minutos a una fecha
     * @param fecha
     * @param minutos
     * @return
     */
    public static Date sumMinutos(Date date, int minutos) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MINUTE, minutos);
	    return new Date(calendar.getTimeInMillis()); 
	}
    
    /**
     * Me devuelve la uuid unica
     * @return
     */
    public static byte[] getUUIDCalendar() {
        Calendar cal = Calendar.getInstance();
        String val = String.valueOf(cal.get(Calendar.YEAR));
        val += TIME_FORMAT_4.format(cal.get(Calendar.DAY_OF_YEAR));
        val += UUID.randomUUID().toString().replaceAll("-", "");
        return val.getBytes();
    }
    
    
	/**
	 * Parsea la fecha completa (Date a String)
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateAndTimeYearDos(final Date date) {

		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_YEAR__DOS_FORMAT);
		return dateFormatAll.format(date);
	}
	/**
	 * Parsea la fecha completa (Date a String)
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateAndTimeYear(final Date date) {

		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormatAll = new SimpleDateFormat(Utilidad.DATE_TIME_YEAR_FORMAT);
		return dateFormatAll.format(date);
	}
    
    
    /**
     * Transforma un fecha DD/MM/AAAA a formato AAAA/MM/DD.
     * @param fecha Fecha con formato DD/MM/AAAA
     * @return
     */
    public static String cambiarFormatoFechaAAAAMMDD(String fecha){
		if (Validaciones.isNullOrEmpty(fecha)) {
			return null;
		}
		String dia = fecha.substring(0, 2);
		String mes = fecha.substring(3, 5);
		String anio = fecha.substring(6, 10);

		return anio + "/" + mes + "/" + dia;
    }
    
    /**
     * Transforma un fecha AAAAMMDD a formato DD/MM/AAAA.
     * @param fecha Fecha con formato AAAAMMDD
     * @return
     */
    @Deprecated
    public static String cambiarFormatoFechaDDMMAAAA(String fecha){
    	String dia = fecha.substring(6, 8);
		String mes = fecha.substring(4, 6);
		String anio = fecha.substring(0, 4);
		
		return dia+"/"+mes+"/"+anio;
    }
    
    /**
     * Transforma un fecha DD/MM/AA a formato DD/MM/AAAA.
     * @param fecha Fecha con formato DD/MM/AA
     * @return
     */
    public static String cambiarFormatoFechaDDMMAA(String fecha){
    	if(fecha.length() == 8){
	    	String dia = fecha.substring(0, 2);
			String mes = fecha.substring(3, 5);
			String anio = fecha.substring(6, 8);
			
			return dia+"/"+mes+"/"+20+anio;
    	} else {
    		return fecha;
    	}
    }
    
    /**
     * Transforma un fecha DD/MM/AAAA a formato DD/MM/AA.
     * @param fecha Fecha con formato DD/MM/AAAA
     * @return
     */
    public static String cambiarFormatoFechaDDMMAAAABarra(String fecha){
    	if(fecha.length() == 10){
    		String diaYMes = fecha.substring(0, 6);
			String anio = fecha.substring(8, 10);
			
			return diaYMes+anio;
    	} else {
    		return fecha;
    	}
    }
    
    /****************************************************************************************************
     * Hashmap
     */
    
    /**
     * Ordenar por valor
     */
    public static HashMap<String, String> sortHashMapByValue(HashMap<String, String> miMap) {
    	
        HashMap<String, String> mapResultado = new LinkedHashMap<String, String>();
	    List<String> misMapKeys = new ArrayList<String>(miMap.keySet());
	    List<String> misMapValues = new ArrayList<String>(miMap.values());
	    TreeSet<String> conjuntoOrdenado = new TreeSet<String>(misMapValues);
	    Object[] arrayOrdenado = conjuntoOrdenado.toArray();
	    int size = arrayOrdenado.length;
	    for (int i=0; i<size; i++) {
	    	mapResultado.put(misMapKeys.get(misMapValues.indexOf(arrayOrdenado[i])),arrayOrdenado[i].toString());
	    }
	    
	    return mapResultado;
    }
    
	/****************************************************************************************************
     * Threads
     */
	/**
     * Devuelve el metodo donde se encuentra ejecutando el hilo
     * @return
     */
    public static String getMethodName() {
    	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2]; //coz 0th will be getStackTrace so 1st, getMethodName() so 2do 
        return e.getMethodName();
    }   
    
    
    /***
    * Permite ejecutar un método en una clase indicada de forma dinámica
    * @throws ShivaExcepcion
    */
    public static Object invocar(Class<?> c, String nombreMetodo, Object value1, Object value2)
    		throws ShivaExcepcion {
    	
	    //Parametros
    	Object[] args_value = {value1, value2};
    	
    	//El arreglo con los tipos de datos de los parámetros
		Class<?>[] args_class = { Object.class, Object.class };

		Method m = null;
		try {
			//Instanciacion
			Object t = c.newInstance();
			
			//Buscando el método
			m = c.getMethod(nombreMetodo, args_class);
			//Invocando el método
			return m.invoke(t, args_value);
			
		} catch (SecurityException e) {
			//Error del Buscando el método
			throw new ShivaExcepcion(e);
		} catch (NoSuchMethodException e) {
			//Error del Buscando el método
			throw new ShivaExcepcion(e);
		} catch (IllegalArgumentException e) {
			throw new ShivaExcepcion(e);
		} catch (IllegalAccessException e) {
			throw new ShivaExcepcion(e);
		} catch (InvocationTargetException e) {
			throw new ShivaExcepcion(e);
		} catch (InstantiationException e) {
			throw new ShivaExcepcion(e);
		}
    }
    
    /**
     * Recibe los datos viejos y nuevos del estado y genera un String historico
     * @param viejo
     * @param nuevo
     * @return
     */
    public static String generarDatosModificadosEstado (String estadoActual, String estadoNuevo) {
    	if (!Validaciones.isNullOrEmpty(estadoActual)) {
    		return "["+Constantes.DATOS_MODIFICADOS_ESTADO+"](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+estadoActual+")(" + Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+estadoNuevo+")";
    	} else {
    		return "["+Constantes.DATOS_MODIFICADOS_ESTADO+"](" + Constantes.DATOS_MODIFICADOS_VALOR + ": "+estadoNuevo+")";
    	}
    }
    
    /**
     * Recibe los datos viejos y nuevos de un DTO y genera un String
     * @param viejo
     * @param nuevo
     * @return
     */
    public static String generarDatosModificados (Object viejo, Object nuevo, List<String> lista) throws ShivaExcepcion{
    	String resultado= "";
    	List<Field> fieldViejo = getDTOFields(viejo);
    	List<Field> fieldNuevo = getDTOFields(nuevo);
    	
    	for (String campo : lista) {
    	
    		int i = 0;
    		for (Field fieldV : fieldViejo) {
    			boolean b = false;
    			if (fieldV.getName().equalsIgnoreCase(campo)){
    				b = true;
    			}
    			if (b){
    				fieldV.setAccessible(true);
    				fieldNuevo.get(i).setAccessible(true);
    				try {
    					if (fieldV.get(viejo)!=null){
    						if (!fieldV.get(viejo).equals(fieldNuevo.get(i).get(nuevo))) {
    							if (!(Validaciones.isNullOrEmpty(fieldV.get(viejo).toString()) 
    									&& Validaciones.isNullOrEmpty(fieldNuevo.get(i).get(nuevo).toString()))){
    								resultado=resultado+"["+DatosWorkflowEnum.getEnumByName(fieldV.getName()).descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+fieldV.get(viejo)+")(" + Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+fieldNuevo.get(i).get(nuevo)+")";
    							}
    						}
    					} else {
    						if (fieldNuevo.get(i).get(nuevo)!=null){
    							if (!fieldNuevo.get(i).get(nuevo).equals("")){
    								resultado+="["+DatosWorkflowEnum.getEnumByName(fieldV.getName()).descripcion()+"]("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": )("+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+fieldNuevo.get(i).get(nuevo)+")";
    							}
    						}
    					}
    				} catch (IllegalArgumentException | IllegalAccessException e) {
    				}
    				break;
    			}
    			i++;
    		}
    	}
    	return resultado;
    }
    
    /**
     * 
     * @param datoViejo
     * @param datoNuevo
     * @param datoWorflow
     * @return
     */
    public static String generarDatoModificado(Object datoViejo, Object datoNuevo, DatosWorkflowEnum datoWorflow, String valorViejo, String valorModificado) {
    	String resultado = "";
    	if (datoViejo!=null){
			if (!datoViejo.equals(datoNuevo)) {
				if (!(Validaciones.isNullOrEmpty(datoViejo.toString()) 
						&& Validaciones.isNullOrEmpty(datoNuevo.toString()))){
					resultado=resultado+"["+datoWorflow.descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valorViejo+")(" + Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+ valorModificado+")";
				}
			}
		} else {
			if (datoNuevo !=null){
				if (!"".equals(datoNuevo.toString())){
					resultado+="["+datoWorflow.descripcion()+"]("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": )("+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+valorModificado+")";
				}
			}
		}
    	return resultado;
    }
    
    
    /**
     * Devuelve una lista con los campos de un dto incluyendo los heredados
     * @param dto
     * @return
     */
	public static List<Field> getDTOFields(Object dto){
    	   List<Field> fields = new ArrayList<Field>();
    	    Class<? extends Object> tmpClass = dto.getClass();
    	    while (tmpClass.getName() != Object.class.getName()) {
    	        fields.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
    	        tmpClass = (Class<? extends Object>) tmpClass.getSuperclass();
    	    }
    	    return fields;
    }

    
	/**
	 * Llena los Strings null de un Object con guiones
	 * @param obj
	 * @return
	 * @throws ShivaExcepcion
	 */
	public static List<? extends DTO> guionesNull (List<? extends DTO> obj) throws ShivaExcepcion{
		try {
			if (Validaciones.isCollectionNotEmpty(obj)) { 
				Class<? extends DTO> clase = obj.get(0).getClass();
				List<Field> fields = getDTOFields(obj.get(0));
				for (DTO ob : obj) {
					if (!ob.getClass().equals(clase)){
						fields = getDTOFields(ob);
						clase = ob.getClass();
					}
					for (Field field : fields) {
						field.setAccessible(true);
						if (field.getType() == String.class && Validaciones.isNullOrEmpty((String) field.get(ob))){
							field.set(ob, "-");
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
			throw new ShivaExcepcion(e);
		}
		
		return obj;
	}
	
	public static DTO guionesNullDto (DTO dto) throws ShivaExcepcion{
		try {
			Class<? extends DTO> clase = dto.getClass();
			List<Field> fields = getDTOFields(dto);

			if (!dto.getClass().equals(clase)){
				fields = getDTOFields(dto);
			}
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getType() == String.class && Validaciones.isNullOrEmpty((String) field.get(dto))){
					field.set(dto, "-");
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
			throw new ShivaExcepcion(e);
		}
		return dto;
	}
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Este metodo recibe una lista de objetos Collection que extienden de DTO 
	 * y recorre recursivamente siempre y cuando sean objetos Set o List 
	 * para setear un guion '-' en los objetos de tipo String que esten nulos
	 * o vacios.
	 * No entra en otro tipo de objetos.
	 * @param obj
	 * @return
	 * @throws ShivaExcepcion
	 */
	@SuppressWarnings("unchecked")
	public static Collection<? extends DTO> guionesNullRecursivo(Collection<? extends DTO> obj) throws ShivaExcepcion{
		try {
			if (Validaciones.isCollectionNotEmpty(obj)) { 
				
				Iterator<? extends DTO> iteratorObj = obj.iterator();
				while(iteratorObj.hasNext()){
					
					DTO ob = iteratorObj.next();
					
					for (Field field : getDTOFields(ob)) {
						
						field.setAccessible(true);
						
						if (field.getType() == String.class 
								&& Validaciones.isNullOrEmpty((String) field.get(ob))){
							field.set(ob, "-");
						}
						if (field.getType() == Set.class
								|| field.getType() == List.class){
							guionesNullRecursivo((Collection<? extends DTO>)field.get(ob));
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | ShivaExcepcion e) {
			throw new ShivaExcepcion(e);
		}
		
		return obj;
	}
	
	
    /**
     * Recibe un dto y devuelve un String con los datos. Ignora el primer campo que recibe (debería coincidir con el serialVersionUID)
     * @param dto
     * @return
     */
    public static String datosOriginales (Object dto, List<String> lista) throws ShivaExcepcion{
    	String resultado = "";
    	Field[] fieldDto = dto.getClass().getDeclaredFields();
    	for (int i=0; i< fieldDto.length; i++){
    		boolean b = false;
    		try {
    			for (String campo : lista){
    				if (fieldDto[i].getName().equals(campo)){
    					fieldDto[i].setAccessible(true);
    					
    					if(!Validaciones.isNullOrEmpty(String.valueOf(fieldDto[i].get(dto))) && !Validaciones.isNullOrEmpty(String.valueOf(DatosWorkflowEnum.getEnumByName(fieldDto[i].getName())))  ){
    						b = true;
    					}
    					
    				}
    					
    			}
    			if (b){
    				fieldDto[i].setAccessible(true);
    				resultado=resultado+"["+DatosWorkflowEnum.getEnumByName(fieldDto[i].getName()).descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR + ": "+fieldDto[i].get(dto)+")";
    			}
			}catch (IllegalArgumentException e){ 
				throw new ShivaExcepcion(e);
			}catch (IllegalAccessException e) {
				throw new ShivaExcepcion(e);
			}
    	
    	}
    	return resultado;
    }
    
    /**
     * Convierte en mayusculas la primer letra de un String
     * @param cadena
     * @return 
     */
    public static String primeraLetraMayuscula(String cadena) {
    	if (cadena!=null){
    		cadena =cadena.substring(0,1).toUpperCase()+cadena.substring(1,cadena.length());
    	}
    	return cadena;
    }
    
	/**
	 * Convierte en mayusculas la primer letra de cada palabra de la cadena informada
	 * 
	 * @param string
	 * @return
	 */
    public static String capitalizarCadena(String string) {
		char[] chars = string.toLowerCase().toCharArray();
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
				found = false;
			}
		}
		return String.valueOf(chars);
	}
    
    /**
     * Recibe un String con el formato de retorno de generarDatosModificados y lo prepara para mostrar
     * @param dato
     * @return
     */
    public static String mostrarDatosModificados (String dato){
    	String resultado = "";
    	//flags
    	boolean b = false;
    	boolean d = false;
    	
    	for (char c : dato.toCharArray()){
    		switch(c){
    			case ']': c = '\n';d=false;
    				break;
    			case '[': if (b==false){c = '\0'; b=true;}else{c='\n';}
    				break;
    			case '(': if (d==false){c = '\0'; d=true;}else{c='\n';}
    				break;
    			case ')': c = '\0';
    				break;
    		}
    		if (c!='\0')
    			resultado+=c;
    	}
    	return resultado;
    }
    
    /**
     * Recibe un String con el formato de retorno de generarDatosModificados y los prepara para mostrar
     * Si la opcion es: DATOS_MODIFICADOS_SOLO_DATOS.  Muestra solo los datos
     * 					DATOS_MODIFICADOS_SOLO_ESTADO Muestra solo el estado
     */
    public static String mostrarDatosModificados(String dato, int opcion) {
    	String datoSalida = EMPTY_STRING;
    	if (Validaciones.isNullOrEmpty(dato)) {
    		return dato;
    	}
    	String datoSplit[] = dato.split("\\)");
    	
    	switch (opcion) {
			case DATOS_MODIFICADOS_SOLO_DATOS:
				if (datoSplit.length > 2) {
					datoSalida = StringUtils.join(
						Arrays.copyOfRange(datoSplit, 2, datoSplit.length),
						")"
					);
					if (dato.endsWith(")")) {
						datoSalida +=")";
					}
				} else if (datoSplit.length == 2) {
					int count = StringUtils.countMatches(dato, ")");
					if (count == 1) {
						datoSalida = datoSplit[1];
					} else if (StringUtils.contains(dato, "(Valor Modificado:")) {
						datoSalida = Utilidad.EMPTY_STRING;
					} else {
						datoSalida = datoSplit[1];
					}
				} else {
					datoSalida = Utilidad.EMPTY_STRING;
				}
				break;
			case DATOS_MODIFICADOS_SOLO_ESTADO:
				int index = dato.indexOf(")");
				String estado1  = dato.substring(0, dato.indexOf(")") + 1);
				String restante = dato.substring(index + 1);
				int index1 = restante.indexOf(")");
				if (index1 > -1) {
					datoSalida = estado1 + restante.substring(0, index1 + 1); 
				} else {
					datoSalida = estado1;
				}
				break;
			default:
				datoSalida = mostrarDatosModificados(dato);
			break;
		}
    	return datoSalida.trim();
    }
    /**
     * Recibe un String, busca las mayusculas y agrega un espacio delante de cada una.
     * Ej. recibe "cadenaDePrueba" devuelve "cadena De Prueba".
     * @param cadena
     * @return
     */
    public static String separarPalabrasPorMayus(String cadena){
    	String resultado = "";
    	for (char ch : cadena.toCharArray()) {
			if (Character.isUpperCase(ch)){
				resultado +=" "+ch;
			} else {
				resultado +=ch;
			}
		}
    	return resultado.trim();
    }
	
	/**
	 * Paso la lista de roles a una cadena
	 * @return
	 */
	public static String getRolesToString(Collection<? extends GrantedAuthority> roles) {
		if (Validaciones.isCollectionNotEmpty(roles)) {
			//Limpio los caracteres en blanco
			String strRoles = roles.toString().replace(" ", "");
			
			//Saco los corchetes []
			strRoles = strRoles.substring(1, strRoles.length()-1);
			
			return strRoles;
		}
		return "";
	}
	
	/**
	 * Me devuelve el usuario desde la session
	 * @return
	 */
	public static String getUsuarioSesion(HttpServletRequest httpServletRequest) {
		if ((!Validaciones.isObjectNull(httpServletRequest.getSession().getAttribute(Constantes.LOGIN_USERNAME)))
				&& (httpServletRequest.getSession().getAttribute(Constantes.LOGIN_USERNAME) instanceof String)
				&& (!Validaciones.isObjectNull((String)httpServletRequest.getSession().getAttribute(Constantes.LOGIN_USERNAME)))) 
		{
			
			return (String) httpServletRequest.getSession().getAttribute(Constantes.LOGIN_USERNAME);
		}
		
		return "";
	}
	
	
	/**
	 * agrega ceros a la izquierda de la "cadena" hasta alcanzar la "longitud"
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rellenarCerosIzquierda(String cadena, int longitud){
		String aux = cadena;
		if (!Validaciones.isNullOrEmpty(aux)) {
			while (aux.length()<longitud){
				aux= '0'+aux;
			}
		} else {
			aux="";
			while (aux.length()<longitud){
				aux= '0'+aux;
			}
		}
		return aux;
	}
	/**
	 * agrega ceros a la izquierda de la "," o el "." hasta alcanzar la cantidad de "izquierda" y hasta "derecha" ceros a la derecha. El retorno no incluye la "," o el "."
	 * la entrada debe tener solo una "," o un "." 
	 * @param cadena
	 * @param izquierda
	 * @return
	 */
	public static String rellenarCerosAmbosLados(String cadena, int izquierda, int derecha){
		String[] aux = cadena.replace(".", ",").split(",");
		aux[0] = rellenarCerosIzquierda(aux[0], izquierda);
		if (aux.length == 2){
			aux[1] = rellenarCerosDerecha(aux[1], derecha);
			return aux[0]+aux[1];
		} else {
			String ceros = "";
			for (int i = 0; i < derecha; i++) {
				ceros+="0";
			}
			return aux[0]+ceros;
		}
	}
	
	/**
	 * agrega ceros a la izquierda de la "cadena" hasta alcanzar la "enteros" y hasta "decimales" ceros a la derecha. La longitud no incluye la "," 
	 * @param cadena
	 * @param enteros
	 * @return
	 */
	public static String rellenarCerosAmbosLadosDeLaComa(String cadena, int enteros, int decimales){
		String[] aux = cadena.split(DECIMAL_CHARACTER);
		aux[0] = rellenarCerosIzquierda(aux[0], enteros);
		if (aux.length == 2){
			aux[1] = rellenarCerosDerecha(aux[1], decimales);
			return aux[0]+ DECIMAL_CHARACTER + aux[1];
		} else {
			String ceros = "";
			for (int i = 0; i < decimales; i++) {
				ceros+="0";
			}
			return aux[0]+DECIMAL_CHARACTER+ceros;
		}
	}
	/**
	 * agrega ceros a la derecha de la "cadena" hasta alcanzar la "longitud"
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rellenarCerosDerecha(String cadena, int longitud){
		String aux = cadena;
		if (!Validaciones.isNullOrEmpty(aux)) {
			while (aux.length()<longitud){
				aux += '0';
			}
		} else {
			aux="";
			while (aux.length()<longitud){
				aux += '0';
			}
		}
		return aux;
	}
	
	/**
	 * agrega espacios a la derecha de la "cadena" hasta alcanzar la "longitud"
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rellenarEspaciosDerecha(String cadena, int longitud){
		String aux = cadena;
		if (!Validaciones.isNullOrEmpty(aux)) {
			while (aux.length()<longitud){
				aux += ' ';
			}
		} else {
			aux="";
			while (aux.length()<longitud){
				aux += ' ';
			}
		}
		return aux;
	}
	
	/**
	 * agrega espacios a la derecha de la "cadena" hasta alcanzar la "longitud"
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rellenarEspaciosIzquierda(String cadena, int longitud){
		String aux = cadena;
		if (!Validaciones.isNullOrEmpty(aux)) {
			while (aux.length()<longitud){
				aux = ' '+aux;
			}
		} else {
			aux="";
			while (aux.length()<longitud){
				aux = ' '+aux;
			}
		}
		return aux;
	}
	
	
	/**
	 * recibe un objeto y una lista con los campos.
	 * Devuelve un string con los campos coincidentes y el dato correspondiente con el formato "campo1: dato1 - campo2: dato2"
	 * Concidera tambien los campos de un objeto que es atributo del objeto enviado. Siempre y cuando esten en la lista.
	 * @param o
	 * @param lista
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static String getCamposYDatos(Object o, List<String> lista) throws NegocioExcepcion{
		String resultado = "";
		List<Field> fields = getDTOFields(o);
		for (Field field : fields) {
			field.setAccessible(true);
			Field[] objeto;
			boolean flag = false;
			if (!field.getType().isPrimitive() && !(field.getType().getName().startsWith("java.lang") || field.getType().getName().startsWith("java.math")) && !field.getType().isEnum()){
				objeto = field.getType().getDeclaredFields();
				flag = true;
			} else {
				objeto = new Field[] {field};
			}
			for (Field obj : objeto) {
				obj.setAccessible(true);
				for (String campo : lista) {
					if (campo.equalsIgnoreCase(obj.getName())){
						try {
							if (field.get(o)!= null){
								resultado += " - "+Utilidad.primeraLetraMayuscula(Utilidad.separarPalabrasPorMayus(obj.getName()))+": "+(flag?obj.get(field.get(o)):obj.get(o));
							}
							break;
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new NegocioExcepcion(e.getMessage(),e);
						}
					}
				}
			}
		}
		if (resultado.length()>3){
			resultado = resultado.substring(3);
		}
		return resultado;
	}
	
	/**
	* Permite formatear la cadena al valor nn-nnnnnnnn-n
	*/
	public static String formatearCuit(String cuit){
		if (!Validaciones.isNullOrEmpty(cuit)){
			String parte1 = cuit.substring(0, 2);
			String parte2 = cuit.substring(2, 10);
			String parte3 = cuit.substring(10, 11);
			return parte1 + "-" + parte2 + "-" + parte3;
		} else {
			return null;
		}
	}
	
	/**
	* Permite quitar la cadena al valor nnnnnnnnnnn
	*/
	public static String desformatearCuit(String cuit){
		
		if(!Validaciones.isObjectNull(cuit)){
			String cuitSinBarra = cuit.replace("-", "");	
			return cuitSinBarra;
		}
		return EMPTY_STRING;
	}

	/**
	 * @autor Nicolás Quispe u591368
	 * @param array(tipo char)
	 * @return cuit con formato "nn-nnnnnnnnn-n"
	 */
	public static String cuitFormateado(char[] array) {
		String cuitFormateado = null;
		int indiceCuit = 0;
		boolean esCuitValido = true;
		char[] cuit = new char[13];
		
		int longitudDisponible = array.length;
		
		for (int indiceArray = 0; indiceArray < longitudDisponible ; indiceArray++) {
			if (!Character.isDigit(array[indiceArray])) {
				esCuitValido = false;
				continue;
			}
			
			int indiceArrayDuplicado = indiceArray;
			
			if (!esCuitValido || Character.isDigit(array[indiceArray])) {
				if (Character.isDigit(array[indiceArray]) && indiceArray + 1 < longitudDisponible) {
					int limiteCuit = 13;
					for (int i = indiceCuit; i < limiteCuit ; i++) {
						if(!cuitTieneGuiones(cuit)){
							limiteCuit = 11;
						} else {
							limiteCuit = 13;
						}
						cuit[i] = array[indiceArrayDuplicado];
						if(indiceArrayDuplicado + 1 < longitudDisponible){
							indiceArrayDuplicado++;
						} else {
							break;
						}
							
						if (indiceCuit == 10 || indiceCuit == 12) {
							if(cuit[indiceCuit] == ' ') {
								esCuitValido = false;
								continue;
							}
						}	
					}
				}
				
				/*Validar si el cuit es correcto y formateo*/
				
				if (validarCuit(cuitSinGuiones(cuit))) {
					cuitFormateado = formatearCuit(String.valueOf(cuitSinGuiones(cuit)));
					esCuitValido = true;
					break;
				} else {
					// limpio la variable 'cuit' para evitar que queden datos basura
					// de la iteración anterior
					cuit = new char[13];
				}
				
			
			} else {
				if(longitudDisponible != 0){
					longitudDisponible--;
				}
				continue;
			}
		}
		return cuitFormateado;
}
	/**
	 * @autor Nicolás Quispe u591368
	 * @param cuit con o sin guiones
	 * @return	cuit formateado sin guiones ("nnnnnnnnnnn"
	 */
	public static char[] cuitSinGuiones(char[] cuit){
		char[] cuitSinGuiones = new char[11];
		int indiceCuit = 0;
		int indiceArray = 0;
		while(indiceArray <= 12 && indiceCuit <= 10) {
			if (Character.isDigit(cuit[indiceArray])) {
				cuitSinGuiones[indiceCuit] = cuit[indiceArray];
				indiceCuit++;
			}
			indiceArray++;
		}
		return cuitSinGuiones;
}
	/**
	 * @autor Nicolás Quispe u591368
	 * @param array
	 * @return true si en la posicion 2 de la array contiene un guión
	 */
	public static boolean cuitTieneGuiones(char[] array){
		return array[2] == '-';
	}
	
	public static boolean validarCuit(char[] entrada){
		if(entrada.length != 11){
			return false;
		}
		
		for (char c : entrada) {
			if(!Character.isDigit(c)){
				return false;
			}
		}
		//Esta validacion para los casos que 23 y 33
		if (entrada[0] == '2' || entrada[0] == '3'){
			if (entrada[1] == '3'){
				char[] sexo = {'0','7'};
				for (int j = 0; j < 2; j++) {
					entrada[1] = sexo[j];
					if (algoritmoCuit(entrada, true)){
						entrada[1] = '3';
						return true;
					}
					if(entrada[0] == '3'){
						break;
					}
				}
				return false;
			}
			return algoritmoCuit(entrada, false);
		}
		return false;
	}

	private static boolean algoritmoCuit(char[] entrada, boolean es23) {
		int[] coeficiente = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int suma = 0;
		for (int j = 0; j < 10; j++) {
			suma += Integer.valueOf(String.valueOf(entrada[j]))*coeficiente[j];
		}
		int resto = suma%11;
		switch (resto) {
		case 0:
			if (Integer.valueOf(String.valueOf(entrada[10])) != 0 || es23){
				return false;
			}
			break;
		case 1:
			if (es23){
				if (!(entrada[10] == '4' || entrada[10] == '9')){
					return false;
				}
			} else if (!Integer.valueOf(String.valueOf(entrada[10])).equals(11-resto)){
				return false;
			}
			break;
		default:
			if (!Integer.valueOf(String.valueOf(entrada[10])).equals(11-resto) || es23){
				return false;
			}
			break;
		}
		return true;
	}
	
	/**
	 * Permite borrar si es un caracter no numerico
	 * @param dato
	 * @return
	 */
	public static String eliminarCaracteresNoNumericos(String dato){
    	String resultado = "";
    	
    	if (!Validaciones.isNullOrEmpty(dato)) {
    		for (char c : dato.toCharArray()){
	    		if (c >= '0' && c <= '9') {
	    			resultado+=c;
	    		}
	    	}
    	}
    	return resultado;
    }
	

	public static List<GregorianCalendar> fechasMesAnterior(String fechaParametro) throws ParseException {
		List<GregorianCalendar> listaFecha = new ArrayList<GregorianCalendar>();
		GregorianCalendar fechaFin = new GregorianCalendar(); 
		GregorianCalendar fechaInicio = new GregorianCalendar();
		fechaInicio.setTime(Utilidad.parseMesAnioBarraString(fechaParametro));
		BeanUtils.copyProperties(fechaInicio, fechaFin);
		fechaInicio.roll(GregorianCalendar.MONTH, -1);
		listaFecha.add(fechaInicio);
		listaFecha.add(fechaFin);
		return listaFecha;
	}
	
	/**
	 * Limpia los espacios vacios al inicio y al final de cada componente del array
	 * @param array
	 * @return
	 */
	public static String[] limpiarArrayString(String[] array) {
		if (Validaciones.isObjectNull(array)) {
			return array;
		} else {
			for (int i = 0; i < array.length; i++) {
				if (!Validaciones.isObjectNull(array[i])) {
					array[i] = array[i].trim();
				}
			}
		}
		return array;
	}
	
	public static Charset charsetDetector(FileInputStream fiStream) throws IOException {
	    byte[] buf = new byte[4096];

	    // (1)
	    UniversalDetector detector = new UniversalDetector(null);

	    // (2)
	    int nread;
	    while ((nread = fiStream.read(buf)) > 0 && !detector.isDone()) {
	      detector.handleData(buf, 0, nread);
	    }
	    // (3)
	    detector.dataEnd();

	    // (4)
	    String encoding = detector.getDetectedCharset();
	    Charset charset = null;
	    if (encoding != null) {
	    	charset = Charset.forName(encoding);	    	
	         Traza.auditoria(Utilidad.class,"Detected encoding = " + encoding);
	    } else {
	    	Traza.auditoria(Utilidad.class,"No encoding detected.");
	    }
	    
	    if(!Validaciones.isObjectNull(charset)){
	    	Traza.auditoria(Utilidad.class,"El encoding es: " + charset.name());
		 }else{
			 charset = Charset.forName("Cp1252");
			 Traza.auditoria(Utilidad.class,"Se setea ANSI por defecto al no haber encontrado encoding.");
		 }
	    // (5)
	    detector.reset();
	    return charset;	    
	  }
	
	/**
	 * Se crea el metodo para normalizar la creacion del objeto que parsea las fechas
	 * ya que si no tiene el metodo setLenient en false se podria generar una fecha errada
	 * a partir del un input equivocado de parte del usuario
	 * @param patron
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String patron){
		SimpleDateFormat sdf = new SimpleDateFormat(patron);
		sdf.setLenient(false);
		return sdf;
	}
	
	/**
	 * Se crea el metodo para normalizar la creacion del objeto que parsea las fechas
	 * ya que si no tiene el metodo setLenient en false se podria generar una fecha errada
	 * a partir del un input equivocado de parte del usuario
	 * @param patron
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String patron, Locale locale){
		SimpleDateFormat sdf = new SimpleDateFormat(patron, locale);
		sdf.setLenient(false);
		return sdf;
	}
	
	/**
	 * Busqueda binaria
	 * @param array
	 * @param value
	 * @return
	 */
	public static int busBin(String[] array, String value) {
		int pos = -1;
		if (array != null && array.length > 0) {
			int ini = 0;
			int end = (array.length - 1);
			String midValue;
			while ((pos < 0) && ini <= end) {
				int mid = (ini + end) / 2;
				midValue = array[mid]; 
				if (midValue.compareTo(value) == 0) {
					pos = mid;
				} else {
					if (value.compareTo(midValue) > 1) {
						ini = mid + 1;
					} else {
						end = mid - 1;
					}
				}
			}
		}
		return pos;
	}
	
	public static String[] ordenarCrec(String[] array) {
		String[] result = array;
		String aux = null;
		if (result != null && result.length > 0) {
			int lastTour = result.length - 2;
			int lastChange;
			while (lastTour >= 0) {
				lastChange = -1;
				for (int i = 0; i <= lastTour; i++) {
					if (array[i].compareTo(array[i+1]) > 0) {
						aux = array[i+1];
						array[i+1] = array[i];
						array[i] = aux;
						lastChange = i;
					}
				}
				lastTour = lastChange;
			}
		}
		return result;
	}
	
	/**
	 * Retorna X-nnnn-nnnnnnnn para los DUC
	 * @param claseComprobante
	 * @param sucursalComprobante
	 * @param numeroComprobante
	 * @return
	 */
	public static String parsearNroDocNoDuc(TipoClaseComprobanteEnum claseComprobante, String sucursalComprobante,
			String numeroComprobante) {
		
		String nroDoc = null;

		if(!Validaciones.isObjectNull(claseComprobante)) {
			// Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco.
	         if (!TipoClaseComprobanteEnum.D.equals(claseComprobante) 
	        		 && !TipoClaseComprobanteEnum.S.equals(claseComprobante)) {
	         	nroDoc = claseComprobante.name() + "-";
	         	nroDoc += Utilidad.rellenarCerosIzquierda(sucursalComprobante, 4) + "-" +
	                     Utilidad.rellenarCerosIzquierda(numeroComprobante, 8);
	         } else {
	        	 nroDoc = Utilidad.rellenarCerosIzquierda(sucursalComprobante, 4) + "-" +
	                     Utilidad.rellenarCerosIzquierda(numeroComprobante, 8);
	         }
		}
		return nroDoc;
	}
	
	/**
	 * Retorna Xnnnn-nnnnnnnn
	 * @return
	 */
	public static String getReferenciaNumeroDocumento(TipoClaseComprobanteEnum claseComprobante, String sucursalComprobante, String nroComprobante) {
        StringBuffer numeroLegal = new StringBuffer();
        
        // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco. (aplicará solo para MIC)
        if (!Validaciones.isObjectNull(claseComprobante) && !TipoClaseComprobanteEnum.D.equals(claseComprobante) && !TipoClaseComprobanteEnum.S.equals(claseComprobante)) {
        	numeroLegal.append(claseComprobante.name());
        }
  
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(sucursalComprobante, 4));
        numeroLegal.append("-");
        numeroLegal.append(Utilidad.rellenarCerosIzquierda(nroComprobante, 8));
 
        return numeroLegal.toString();
	}
	

	/**
	 * Dado un objeto, retorna una copia (clon) del mismo. 
	 * La copia de atributos solo será realizada si los mismos poseen los metodos setter y getter correspondientes definidos
	 * 
	 * @author Pablo M. Ibarrola
	 * 
	 * @param objOrigen
	 * @return
	 */
	public static Object clonarObjeto(Object objOrigen) throws Exception {
		
		// 
		// Obtengo los atributos de la clase
		//
		List<Field> atributos = new ArrayList<Field>();
 	    Class<? extends Object> tmpClass = objOrigen.getClass();
 	    
 	    while (tmpClass.getName() != Object.class.getName()) {
 	    	atributos.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
 	        tmpClass = (Class<? extends Object>) tmpClass.getSuperclass();
 	    }
 	    
		//
 	    // Creo una nueva instancia de la clase
 	    //
		Object objCopiaDestino = Class.forName(objOrigen.getClass().getName()).getConstructor().newInstance();

		// 
		// Copio los atributos de la clase origen a la clase destino
		//
		Class<?> orgClassType = objOrigen.getClass();
		
		for (Field atributo : atributos) {

			String firstLetter = atributo.getName().substring(0, 1).toUpperCase();
			
			try {
				//
				// Instancio metodos setter y getter
				//
				String getMethodName = "get" + firstLetter + atributo.getName().substring(1);
				String setMethodName = "set" + firstLetter + atributo.getName().substring(1);
				
				Method getterMethod = orgClassType.getMethod(getMethodName, new Class[]{});
				Method setterMethod = orgClassType.getMethod(setMethodName, new Class[]{atributo.getType()});
				
				//
				// Copio los valores de los atributos de la clase origen a la clase destino
				//
				if (!Validaciones.isObjectNull(getterMethod)) {
					Object value = getterMethod.invoke(objOrigen, new Object[]{});
					if (!Validaciones.isObjectNull(setterMethod)) {
						setterMethod.invoke(objCopiaDestino, new Object[]{value});
					}
				}
			} catch (NoSuchMethodException e) {
				//
				// Solo debiera caer por aqui aquellos atributos que no poseen un setter o getter definido
				// como por ejemplo 'serialVersionUID'
				// En tal caso no hacemos nada, seguimos con el siquiente atributo
				//
				// System.out.println("No existe setter o getter para copiar este atributo");
			}
		}
		
		return objCopiaDestino;
	}
	
	/**
	 * Dado un objeto, copia los elementos que son comunes en el segundo objeto
	 * 
	 * @param objOrigen
	 * @param objDestino
	 * @throws Exception
	 */
	public static void copiarAtributosComunes(Object objOrigen, Object objDestino) throws Exception {

		// 
		// Obtengo los atributos de la clase
		//
		List<Field> atributos = new ArrayList<Field>();
 	    Class<? extends Object> tmpClass = objOrigen.getClass();
 	    
 	    while (tmpClass.getName() != Object.class.getName()) {
 	    	atributos.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
 	        tmpClass = (Class<? extends Object>) tmpClass.getSuperclass();
 	    }
 	    
		// 
		// Copio los atributos de la clase origen a la clase destino
		//
		for (Field atributo : atributos) {

			String firstLetter = atributo.getName().substring(0, 1).toUpperCase();
			
			try {
				//
				// Instancio metodos setter y getter
				//
				String getMethodName = "get" + firstLetter + atributo.getName().substring(1);
				String setMethodName = "set" + firstLetter + atributo.getName().substring(1);
				
				Method getterMethod = objOrigen.getClass().getMethod(getMethodName, new Class[]{});
				Method setterMethod = objDestino.getClass().getMethod(setMethodName, new Class[]{atributo.getType()});
				
				//
				// Copio los valores de los atributos de la clase origen a la clase destino
				//
				if (!Validaciones.isObjectNull(getterMethod)) {
					Object value = getterMethod.invoke(objOrigen, new Object[]{});
					if (!Validaciones.isObjectNull(setterMethod)) {
						setterMethod.invoke(objDestino, new Object[]{value});
					}
				}
			} catch (NoSuchMethodException e) {
				//
				// Solo debiera caer por aqui aquellos atributos que no poseen un setter o getter definido
				// como por ejemplo 'serialVersionUID'
				// En tal caso no hacemos nada, seguimos con el siquiente atributo
				//
				// System.out.println("No existe setter o getter para copiar este atributo");
			}
		}
	}
	
	/**
	 * Para la factura que llega como parametro, se retorna el numero legal formateado.
	 * Se tiene en cuenta que, en caso de que 'clase comprobante' sea una 'D' o una 'S', se muestra como blanco en la cadena retornada
	 * 
	 * @author Pablo M. Ibarrola
	 * 
	 * @param factura
	 * @return
	 * 
	 * TODO: de Pablo: Lo tengo que cambiar o dejar uno que esta arriba, no puedo tener un ShvCobFactura dentro de Utilidad!!!
	 */
	public static String obtenerNumeroLegalFormateado(ShvCobFactura factura) {
		
		StringBuffer idFacturaParaMostrar = new StringBuffer();
		
		idFacturaParaMostrar.append(factura.getTipoComprobante().getIdTipoComprobante());
		idFacturaParaMostrar.append(" ");		
		 
        // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco. (aplicará solo para MIC)
        if (!TipoClaseComprobanteEnum.D.equals(factura.getClaseComprobante()) && !TipoClaseComprobanteEnum.S.equals(factura.getClaseComprobante()) && !Validaciones.isObjectNull(factura.getClaseComprobante())) {
    		idFacturaParaMostrar.append(factura.getClaseComprobante().name());		
    		idFacturaParaMostrar.append("-");		
        }
        
        idFacturaParaMostrar.append(Utilidad.rellenarCerosIzquierda(factura.getSucursalComprobante(), 4));
        idFacturaParaMostrar.append("-");		
        idFacturaParaMostrar.append(Utilidad.rellenarCerosIzquierda(factura.getNumeroComprobante(), 8));
       
        return idFacturaParaMostrar.toString();
	}

	/**
	 * Serializa un objeto a un arrays de bytes
	 * @param object
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] serializarObjeto(Object object) throws IOException {
		ByteArrayOutputStream byteArrayOS= new ByteArrayOutputStream();
		ObjectOutputStream objectOS = new ObjectOutputStream(byteArrayOS);
		objectOS.writeObject(object);
		objectOS.close();
		return byteArrayOS.toByteArray();
	}
	/**
	 * calcula el checkSum de un arrays de bytes utilizando SHA-512
	 * el hash en string de largo 128 bytes
	 * @param bytes
	 * @return String
	 */
	public static String checkSumSHA512(byte[] bytes) {
		return DigestUtils.sha512Hex(bytes);
	}
	/**
	 * Calcula el checkSum de un array de bytes utilizaodn SHA-384
	 * retorna el hash en string de largo 96 bytes
	 * @param bytes
	 * @return String
	 */
	public static String checkSumSHA384(byte[] bytes) {
		return DigestUtils.sha384Hex(bytes);
	}
	/**
	 * Concatena dos String
	 * @param text1
	 * @param text2
	 * @param separador
	 * @return
	 */
	public static String concatena(String text1, String text2, String separador) {
		StringBuffer strBuffer = new StringBuffer();
		if (!Validaciones.isNullEmptyOrDash(text1)) {
			strBuffer.append(text1);
		}
		if (!Validaciones.isNullEmptyOrDash(text2)) {
			if (!Validaciones.isNullEmptyOrDash(text1)) {
				strBuffer.append(separador);
				strBuffer.append(text2);
			} else {
				strBuffer.append(text2);
			}
		}
		return strBuffer.toString();
	}
	/**
	 * Remplaza en el texto con tag '\n' por <br>.
	 * 
	 * @param texto
	 * @return
	 */
	public static String formateadoMail(String texto) {
		if (Validaciones.isNullOrEmpty(texto)) {
			return Constantes.EMPTY_STRING;
		}else{
			return texto.replace(Constantes.CARRIAGE_RETURN, Constantes.RETORNO_HTML);
		}
	}
	public static String formateadoVista(String texto) {
		if (Validaciones.isNullOrEmpty(texto)) {
			return Constantes.EMPTY_STRING;
		}else{
			return texto.replace(Constantes.RETORNO_HTML, Constantes.RETORNO_WIN);
		}
	}
	public static int contarSaltosDeLinea(String texto, String salto) {
		return StringUtils.countMatches(texto, salto);
	}
	
	/**
	 * Retorna como cadena el valor correspondiente a fecha juliana para el día de hoy.
	 * El valor resultante será "año" + "numero de día dentro del año", por ejemplo "2015314" 
	 * es el día número 314 (10 de Noviembre de 2015) dentro del año 2015
	 *  
	 * @return
	 */
	public static String obtenerFechaJuliana() {
		return obtenerFechaJuliana(new Date());
	}
	
	/**
	 * Retorna como cadena el valor correspondiente a fecha juliana para una fecha dada.
	 * El valor resultante será "año" + "numero de día dentro del año", por ejemplo "2015314" 
	 * es el día número 314 (10 de Noviembre de 2015) dentro del año 2015 
	 * 
	 * @param fecha
	 * @return
	 */
	public static String obtenerFechaJuliana(Date fecha) {
		
		GregorianCalendar fechaParametroExtraerJuliana = new GregorianCalendar();
		fechaParametroExtraerJuliana.setTime(fecha);
		
		String fechaJuliana = String.valueOf(
				// Calculo el año
				fechaParametroExtraerJuliana.get(GregorianCalendar.YEAR)) + 
				// Calculo el numero de día para la fecha dada
				Utilidad.rellenarCerosIzquierda(String.valueOf(fechaParametroExtraerJuliana.get(GregorianCalendar.DAY_OF_YEAR)), 3);	
		
		return fechaJuliana;
		
	}
	
	
	/**
	 * Este metodo evalua que si son el mismo analista de team comercial los que vienen
	 * en la lista que recibe como parametro, entonces lo agrega a los copropietarios. 
	 * De lo contrario no agrega ninguno.
	 * 
	 * @param listaDatos    Lista de analistas de team comercial
	 * @param copropietario copropietarios que ya tiene el mail
	 * @return              retorna la lista de copropietarios para sutituir en el mail
	 */
	@SuppressWarnings("rawtypes")
	public static String agregarAnalistaTeamComercialACopropietario(List listaDatos, String copropietario) {
		
		if(Validaciones.isCollectionNotEmpty(listaDatos)){
			boolean copiarAnalistaTeam = true;
			String analistaAComparar = "";
			for (int i = 0; i < listaDatos.size(); i++) {
				
				String analista = "";
				if(listaDatos.get(i) instanceof ResultadoBusquedaDatosImputacion){
					analista = ((ResultadoBusquedaDatosImputacion) listaDatos.get(i)).getAnalistaTeamComercial();
				} else {
					if(listaDatos.get(i) instanceof ResultadoBusquedaDatosSimulacion){
						analista = ((ResultadoBusquedaDatosSimulacion) listaDatos.get(i)).getAnalistaTeamComercial();
					}
				}
				
				
				if(analista!=null) {
					if(analistaAComparar == "") {
						analistaAComparar = analista;
					}
				}
				if(analistaAComparar != ""){
					if(analista != null){
						if(!analistaAComparar.equals(analista)) {
							copiarAnalistaTeam = false;
							break;
							
						}
					}
				}
				
			}
			
			if(copiarAnalistaTeam) {
				if(!analistaAComparar.equals(copropietario))
					if(analistaAComparar!=""){
						if(copropietario!=null){
							copropietario+=";" + analistaAComparar;
						} else {
							copropietario = analistaAComparar;
						}
					}
			}
		}
		return copropietario;
	}
	
	/**
	 * 
	 * @param intereses
	 * @return
	 */
	public static boolean decimalMayorACero(BigDecimal intereses){
		//TODO: falta definir si se usa "equals a 0" o "mayor a 0"
//		if(!Validaciones.isObjectNull(intereses) && !intereses.equals(new BigDecimal(0))){
		if(!Validaciones.isObjectNull(intereses) && intereses.compareTo(new BigDecimal(0)) > 0 ){
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param objeto
	 * @return
	 */
	public static boolean esDiferenciaCambio(Object objeto){
		
		if(!Validaciones.isObjectNull(objeto)){
		
			if(objeto instanceof ShvCobMedioPago){
				if(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO.equals(((ShvCobMedioPago) objeto).getEstado())){
					return true;
				}
			}
			if(objeto instanceof ShvCobFactura){
				if(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO.equals(((ShvCobFactura) objeto).getEstado())){
					return true;
				}
			}
			if(objeto instanceof ShvCobTransaccion){
				
				ShvCobTransaccion transaccion = (ShvCobTransaccion) objeto;
				
				if(Validaciones.isCollectionNotEmpty(transaccion.getListaMediosPagoDiferenciaCambio())){
					return true;
				}
				if(Validaciones.isCollectionNotEmpty(transaccion.getListaFacturasDiferenciaCambio())){
					return true;
				}
				if(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(((ShvCobTransaccion) objeto).getEstadoProcesamiento())
						|| EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(((ShvCobTransaccion) objeto).getEstadoProcesamiento())
						){
					return true;
				}
			}
			
			if(objeto instanceof CobroTransaccionDto){
				CobroTransaccionDto cobroTransaccionDto = (CobroTransaccionDto) objeto;
				
				if(cobroTransaccionDto.getColorLetraRegistro().equals("1")){
					return true;
				}
				
			}
			
			if(objeto instanceof DescobroTransaccionDto){
				DescobroTransaccionDto descobroTransaccionDto = (DescobroTransaccionDto) objeto;
				
				if(descobroTransaccionDto.getColorLetraRegistro().equals("1")){
					return true;
				}
				
			}
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param String NroDocumentoLegal NNNNANNNNNNNN
	 * @return NroDocumentoLegal
	 * */
	public static NroDocumentoLegal obtenerNroDocumentoLegalSap(String nroDocumentoLegal){
		NroDocumentoLegal numeroLegal = new NroDocumentoLegal();
		if(nroDocumentoLegal.length() == 12 || nroDocumentoLegal.length() == 13){
		numeroLegal.setSucursal(nroDocumentoLegal.substring(0, 4));
		numeroLegal.setClase(TipoClaseComprobanteEnum.getEnumByName(nroDocumentoLegal.substring(4, 5)));
		numeroLegal.setNumero(nroDocumentoLegal.substring(5));    	
		}
		return numeroLegal;
	}
	/**
	 * 
	 * @param String NroDocumentoLegal A-NNNN-NNNNNNNN
	 * @return NroDocumentoLegal
	 * */
	public static NroDocumentoLegal obtenerNroDocumentoLegal(String nroDocumentoLegal) {
		NroDocumentoLegal numeroLegal = new NroDocumentoLegal();
		String numeroLegalVector[] = nroDocumentoLegal.split("-");

		if (numeroLegalVector.length == 3) {
			numeroLegal.setClase(TipoClaseComprobanteEnum.getEnumByName(numeroLegalVector[0]));
			numeroLegal.setSucursal(numeroLegalVector[1]);
			numeroLegal.setNumero(numeroLegalVector[2]);
		} else {
			numeroLegal.setSucursal(numeroLegalVector[0]);
			numeroLegal.setNumero(numeroLegalVector[1]);
		}
		return numeroLegal;
	}
	/**
	 * 
	 * @param NroDocumentoLegal
	 * @return String nroDocumentoLegal formateado A-NNNN-NNNNNNNN
	 */
    public static String parcearNroDocumentoLegalSap(NroDocumentoLegal nroDocumentoLegal) {
    	StringBuffer nroLegal = new StringBuffer();

    	if (!Validaciones.isObjectNull(nroDocumentoLegal.getNumero()) &&
           !Validaciones.isObjectNull(nroDocumentoLegal.getSucursal())) {
    		
	    	if (!Validaciones.isObjectNull(nroDocumentoLegal.getClase())) {
	    		nroLegal.append(nroDocumentoLegal.getClase());
	    		nroLegal.append("-");
	       	}
	    	nroLegal.append(nroDocumentoLegal.getSucursal());
	    	nroLegal.append("-");
	    	nroLegal.append(nroDocumentoLegal.getNumero());
    	}
    	return nroLegal.toString();
    }    
	/**
	 * 
	 * @param NroDocumentoLegal
	 * @return String nroDocumentoLegal formateado NNNNANNNNNNNN
	 */
    public static String parcearNroDocumentoLegalShiva(String nroDocumentoLegal){
    	StringBuffer nroLegalFormateado = new StringBuffer();
    		
    	if(!Validaciones.isObjectNull(nroDocumentoLegal)){
    		if(nroDocumentoLegal.contains("-") && (nroDocumentoLegal.length() == 13 || nroDocumentoLegal.length() == 15)){
    			String[] partesNroDocLegal = nroDocumentoLegal.split("-");
    			if(partesNroDocLegal.length == 3){
    				nroLegalFormateado.append(partesNroDocLegal[1]);
    				nroLegalFormateado.append(partesNroDocLegal[0]);
    				nroLegalFormateado.append(partesNroDocLegal[2]);
    			}else{
    				nroLegalFormateado.append(partesNroDocLegal[0]);
    				nroLegalFormateado.append(" ");
    				nroLegalFormateado.append(partesNroDocLegal[1]);
    			}
    		}
    	}
    			
    	return nroLegalFormateado.toString();
    }
    
    public static String reemplazarEspacioPorGuion(String texto) {
    	return texto.replace(' ', '_');
    }
    
    
    public static Date calcularDiaHasta(Set<ShvParamCalendarioWrapper> setShvParamCalendarioWrapper, Date dateDesde, int cantidaDias, boolean diasCorridos) {
    	Calendar calendarCurrent = GregorianCalendar.getInstance();
    	calendarCurrent.setTime(dateDesde);

    	if (cantidaDias != 0) {
    		if (!diasCorridos) {
	    		int indexDia = 0;
	    		int dayOfWeek = -1;
	
	    		while (indexDia < cantidaDias) {
	    			calendarCurrent.add(Calendar.DATE, 1);
	    			dayOfWeek = calendarCurrent.get(Calendar.DAY_OF_WEEK);
	        		if (
	        			Calendar.SATURDAY != dayOfWeek && 
	        			Calendar.SUNDAY != dayOfWeek &&
	        			!esDiaNoLaborableFeriado(setShvParamCalendarioWrapper, calendarCurrent.getTime())
	        		) {
	        			indexDia++;
	        		}
	    		}
    		} else if (cantidaDias != 0) {
    			calendarCurrent.add(Calendar.DATE, cantidaDias);
    		}
    	}
    	return calendarCurrent.getTime();
    }

    public static boolean esDiaNoLaborableFeriado(Set<ShvParamCalendarioWrapper> setShvParamCalendarioWrapper, Date date) {
    	ShvParamCalendario calendario = new ShvParamCalendario();
    	calendario.setFecha(date);
    	ShvParamCalendarioWrapper wrapper = new ShvParamCalendarioWrapper(calendario);
   
    	return setShvParamCalendarioWrapper.contains(wrapper);
    }
    /**
	 * Metodo que corta el string que recibe por parametro en 3 string
	 * de 50 caracteres como mucho. Con la salvedad que no corta ninguna palabra.
	 * @param arrayNumValor
	 * Separador de palabras por espacio
	 * @return
	 */
    public static String[] armarReferencias(String[] arrayNumValor) {
		return armarReferencias(arrayNumValor, "\\s");
	}
	/**
	 * Metodo que corta el string que recibe por parametro en 3 string
	 * de 50 caracteres como mucho. Con la salvedad que no corta ninguna palabra.
	 * @param arrayNumValor
	 * @param regEx para la separacion de palabras
	 * @return
	 */
	public static String[] armarReferencias(String[] arrayNumValor, String regEx) {
		// U562163 - 24/09/2015 - IM0563277
		String str = arrayNumValor[0];
		int contador = 0;
		String referencia1 ="";
		String referencia2 =" ";
		String referencia3 =" ";
		String[] listaPalabras = str.split(regEx);
		
		//Voy agregando a referencia1 hasta que no pueda agregar mas palabras por el limite de 50 caracteres
		while(contador<listaPalabras.length && (referencia1.length()+listaPalabras[contador].length()<=50)){
			referencia1 += listaPalabras[contador];
			if (referencia1.length()<50){
				referencia1 += " ";
			}
			contador++;
		}
		
		//Voy agregando a referencia2 hasta que no pueda agregar mas palabras por el limite de 50 caracteres
		while(contador<listaPalabras.length && (referencia2.length()+listaPalabras[contador].length()<=50)){
			referencia2 += listaPalabras[contador];
			if (referencia2.length()<50){
				referencia2 += " ";
			}
			contador++;
		}
		
		//Voy agregando a referencia3 hasta que no pueda agregar mas palabras por el limite de 50 caracteres
		while(contador<listaPalabras.length && (referencia3.length()+listaPalabras[contador].length()<=50)){
			referencia3 += listaPalabras[contador];
			if (referencia3.length()<50){
				referencia3 += " ";
			}
			contador++;
		}
		
		//si no hay nada las referencias deben ir con un espacio y la 1 no la inicializo con espacio
		if(referencia1==""){
			referencia1= " ";
		}
		
		String[] resultado = {referencia1,referencia2,referencia3};
		return resultado;
	}
	
	/**
	 * 
	 * @param sistema
	 * @param codigosOperacionExterna
	 * @return
	 */
	public static String[] armarReferencias(String sistema, String codigosOperacionExterna) {
		String vrefencia[] = new String[1];
		StringBuilder str = new StringBuilder();
		
		str.append(Utilidad.reemplazarMensajes(
			"Sistema: {0}",
				sistema
		));
		
		str.append(" | ID Op:");
		str.append(!Validaciones.isObjectNull(codigosOperacionExterna) ? codigosOperacionExterna.replace(',', ' ') : "");
	
		vrefencia[0] = str.toString();
	
		String vArmarRefencia[] = armarRef(vrefencia, codigosOperacionExterna);
		return vArmarRefencia;
	}
	
	/**
	 * 
	 * @param sistema
	 * @param referencia
	 * @param codigosOperacionExterna
	 * @return
	 */
	public static String[] armarReferencias(String sistema, String referencia, String codigosOperacionExterna) {
		String vrefencia[] = new String[1];
		StringBuilder str = new StringBuilder();
		
		str.append(Utilidad.reemplazarMensajes(
			"Sistema: {0} | Referencia: {1}",
				sistema,
				!Validaciones.isObjectNull(referencia) ? Utilidad.truncarCadena(referencia, 12, "...") : ""
		));
		
		str.append(" | ID Op:");
		str.append(!Validaciones.isObjectNull(codigosOperacionExterna) ? codigosOperacionExterna.replace(',', ' ') : "");
	
		vrefencia[0] = str.toString();
	
		String vArmarRefencia[] = armarRef(vrefencia, codigosOperacionExterna);
		return vArmarRefencia;
	}
	
	/**
	 * 
	 * @param vrefencia
	 * @param strCodigosOperacionExterno
	 * @return
	 */
	private static String[] armarRef(String[] vrefencia, String strCodigosOperacionExterno) {
	
		String codigosOperacionExterno = strCodigosOperacionExterno;
		String  axup[] = codigosOperacionExterno.split(",");
		int cant = axup.length;
		codigosOperacionExterno = codigosOperacionExterno.replace(',', ' ');
		
		String vArmarRefencia[] = Utilidad.armarReferencias(vrefencia);
		
		if (!Validaciones.isNullEmptyOrDash(codigosOperacionExterno)) {
			boolean cambiar = false;
			for (int x = 0 ; x < vArmarRefencia.length ; x++) {
				if (!Validaciones.isNullEmptyOrDash(vArmarRefencia[x])) {
					int posicion = vArmarRefencia[x].indexOf("ID Op:");
					if (posicion > -1) {
						cambiar = true;
						String vector[] = vArmarRefencia[x].split("ID Op:");
						if (vector.length > 1) {
							if (!Validaciones.isObjectNull(vector[1])) {
								vector[1] = vector[1].replace(' ', ',');
								vArmarRefencia[x] = vector[0] +"ID Op:" + vector[1];
							}
						}
					} else if (cambiar || x != 0) {
						vArmarRefencia[x] = vArmarRefencia[x].replace(' ',',');
						if (vArmarRefencia[x].startsWith(",")) {
							vArmarRefencia[x] = vArmarRefencia[x].replaceFirst(","," ");
						}
					}
				}
			}
			// Saco el ultimo ","
			for (int x = vArmarRefencia.length - 1; x >= 0; x--) {
				if (!Validaciones.isNullEmptyOrDash(vArmarRefencia[x])) {
					if (vArmarRefencia[x].endsWith(",")) {
						int pos = vArmarRefencia[x].lastIndexOf(',');
						if (pos > -1) {
							vArmarRefencia[x] = vArmarRefencia[x].substring(0, pos);
						}
						break;
					}
				}
			}
			int cantNotificada = 0;
			for (String x : vArmarRefencia) {
				if (!Validaciones.isNullEmptyOrDash(x)) {
					String vector[] = x.split(",");
					cantNotificada += vector.length;
				}
				
			}
	
			if (cantNotificada < cant) {
				if (!Validaciones.isNullEmptyOrDash(vArmarRefencia[2])) {
					String aux = vArmarRefencia[2].substring(0, vArmarRefencia[2].length() - " y otros".length());
					String vector[] = aux.split(",");
					if (vector.length > 0) {
						vector[vector.length - 1] = " y otros";
						vArmarRefencia[2] = StringUtils.join(vector, ",");
					}
				}
			}
		}
		return vArmarRefencia;
	}
	
	
	
	
	/**
	 * 
	 * @param nombreArchivoEntradaAplicacionManual
	 * @return Retorna la descripcion corta de la empresa que contiene el nombre del archivo
	 * @throws ParseException 
	 */
	public static String empresaArchivoEntradaAplicacionManual(String nombreArchivoEntradaAplicacionManual) throws ParseException {
		
		String nombreArchivo =  nombreArchivoEntradaAplicacionManual.substring(0, nombreArchivoEntradaAplicacionManual.length());
		String[] campos = nombreArchivo.split("\\.");
		return campos[3];
		
	}
	
	/**
	 * Método que formatea de empresasAsociadas a los distintos tipos de permiteUso.
	 * 
	 * @author U587496 Guido.Settecerze
	 * @param clienteDto
	 * @param modelo
	 */
	public static void formateoEmpresasAsociadasAPermiteUsos(ClienteDto clienteDto, ShvCobEdCliente modelo) {
		String[] split = clienteDto.getEmpresasAsociadas().split("-");
		String permiteTA = split[0];
		String permiteTP = split[1];
		String permiteCV = split[2];
		String permiteNX = split[3];
		SiNoEnum permiteTAEnum = null;
		SiNoEnum permiteTPEnum = null;
		SiNoEnum permiteCVEnum = null;
		SiNoEnum permiteNXEnum = null;
		if(Constantes.EMPRESA_TELECOM_ARGENTINA.equals(permiteTA)){
			permiteTAEnum = SiNoEnum.SI;
		}else {
			permiteTAEnum = SiNoEnum.NO;
		}
		if(Constantes.EMPRESA_TELECOM_PERSONAL.equals(permiteTP)){
			permiteTPEnum = SiNoEnum.SI;
		}else {
			permiteTPEnum = SiNoEnum.NO;
		}
		if(Constantes.EMPRESA_CABLEVISION.equals(permiteCV)){
			permiteCVEnum = SiNoEnum.SI;
		}else {
			permiteCVEnum = SiNoEnum.NO;
		}
		if(Constantes.EMPRESA_NEXTEL.equals(permiteNX)){
			permiteNXEnum = SiNoEnum.SI;
		}else {
			permiteNXEnum = SiNoEnum.NO;
		}
		modelo.setPermiteUsoTA(permiteTAEnum);
		modelo.setPermiteUsoTP(permiteTPEnum);
		modelo.setPermiteUsoCV(permiteCVEnum);
		modelo.setPermiteUsoNX(permiteNXEnum);
		modelo.setPermiteUsoFT(SiNoEnum.NO);
	}
	
	/**
	 * Método que formatea el campo Empresas Asociadas.
	 * 
	 * @author U587496 Guido.Settecerze
	 * 
	 * @param ta
	 * @param tp
	 * @param cv
	 * @param nx
	 * @return
	 * @throws NegocioExcepcion
	 */
	public static String armadoCampoEmpresasAsociadas(SiNoEnum ta, SiNoEnum tp, SiNoEnum cv, SiNoEnum nx) throws NegocioExcepcion {
		StringBuffer empresasAsociadas = new StringBuffer();
		
		empresasAsociadas.append((!Validaciones.isObjectNull(ta) && SiNoEnum.SI.equals(ta))?Constantes.EMPRESA_TELECOM_ARGENTINA:"  ");
		empresasAsociadas.append("-");
		empresasAsociadas.append((!Validaciones.isObjectNull(tp) && SiNoEnum.SI.equals(tp))?Constantes.EMPRESA_TELECOM_PERSONAL:"  ");
		empresasAsociadas.append("-");
		empresasAsociadas.append((!Validaciones.isObjectNull(cv) && SiNoEnum.SI.equals(cv))?Constantes.EMPRESA_CABLEVISION:"  ");
		empresasAsociadas.append("-");
		empresasAsociadas.append((!Validaciones.isObjectNull(nx) && SiNoEnum.SI.equals(nx))?Constantes.EMPRESA_NEXTEL:"  ");
		
		
		return empresasAsociadas.toString();
	}
	
	public static Collection<String> getAtributos (String data) {
		   
        Collection<String> listaAtributos = new ArrayList<String>();
       
       
        String[] parts = data.split(Pattern.quote("{"));
   
        for (int i = 0; i < parts.length; i++) {
            if (i != 0) {
            	if (parts[i].length() != 1) {
            		String atributo = parts[i].split(Pattern.quote("}"))[0];
            		listaAtributos.add(atributo);
            	} else {
            		break;
            	}
            		
            }
        }
       
        return listaAtributos;
    }
	
}
