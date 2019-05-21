package ar.com.telecom.shiva.base.utils.logs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ar.com.telecom.shiva.base.excepciones.Excepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.presentacion.seguridad.TokenSessionListener;
import ch.qos.logback.classic.Level;

public class Traza {
	
	private static Logger log = LoggerFactory.getLogger(Traza.class);
	
	public static final String NIVEL = "nivel";
	
	/** */
	//private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss.SSSS";
	private static final String TIME_PATETRN = "HH:mm:ss.SSS";
	
	
	/**
	 * Me permite tracear el tiempo de procesamiento de un metodo que invocamos<br>
	 * <br>
	 * Nota:<br> 
	 * Ejemplo a tener en cuenta a la hora de tracear el tiempo de procesamiento de un metodo <br>
	 * <br>
	 * // Medicion de tiempo al inicio del procesamiento <br>
	 * double fechaHoraInicioNanoTime = System.nanoTime(); <br>
	 * Date fechaHoraInicio = new Date();<br>
	 * <br>
	 * ...nombreMetodoInvocante...<br>
	 * <br>
	 * Traza.loguearInfoProcesamiento(this.getClass(), "nombreMetodoInvocante", fechaHoraInicio, fechaHoraInicioNanoTime);<br>
	 * <br>
	 * @param fechaHoraInicio
	 * @param fechaHoraInicioNanoTime
	 */
	public static void loguearInfoProcesamiento(Class<?> clase, String metodo, Date fechaHoraInicio, double fechaHoraInicioNanoTime, int cantRegistros) {
		// Medicion de tiempo al fin del procesamiento
		double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
		Date fechaHoraFin = new Date();
		
		try {
			Date difference = Utilidad.differenceBetweenDates(fechaHoraInicio, fechaHoraFin);

			String TIME_PATETRN = "HH:mm:ss.SSS";
			SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
			
			// Trazo tiempo total de procesamiento
			String detalle = "Tiempo de procesamiento: " 
					+ sdfForDateDifference.format(difference) + "(" + ((double) elapsedTime / 1000000000.0) + " segundos) para '" + cantRegistros + "' registros";
			
			Traza.tiempo(clase, metodo, detalle);
			
		} catch (ParseException e) {
		}
	}
	
	
	/**
	 * Me permite tracear el tiempo de procesamiento de un metodo que invocamos<br>
	 * <br>
	 * Nota:<br> 
	 * Ejemplo a tener en cuenta a la hora de tracear el tiempo de procesamiento de un metodo <br>
	 * <br>
	 * // Medicion de tiempo al inicio del procesamiento <br>
	 * double fechaHoraInicioNanoTime = System.nanoTime(); <br>
	 * Date fechaHoraInicio = new Date();<br>
	 * <br>
	 * ...nombreMetodoInvocante...<br>
	 * <br>
	 * Traza.loguearInfoProcesamiento(this.getClass(), "nombreMetodoInvocante", fechaHoraInicio, fechaHoraInicioNanoTime);<br>
	 * <br>
	 * @param fechaHoraInicio
	 * @param fechaHoraInicioNanoTime
	 */
	public static void loguearInfoProcesamiento(Class<?> clase, String metodo, Date fechaHoraInicio, double fechaHoraInicioNanoTime) {
		// Medicion de tiempo al fin del procesamiento
		double elapsedTime = System.nanoTime() - fechaHoraInicioNanoTime;
		Date fechaHoraFin = new Date();
		
		try {
			Date difference = Utilidad.differenceBetweenDates(fechaHoraInicio, fechaHoraFin);

			String TIME_PATETRN = "HH:mm:ss.SSS";
			SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
			
			// Trazo tiempo total de procesamiento
			String detalle = "Tiempo de procesamiento: " 
							+ sdfForDateDifference.format(difference) + "(" + ((double) elapsedTime / 1000000000.0) + " segundos) ";
			
			Traza.tiempo(clase, metodo, detalle);
		} catch (ParseException e) {
		}
	}
	
	/**
	 * Me permite cambiar la ruta y el nombre del archivo
	 * @param valor
	 */
	public synchronized static void cambiarNombreArchivoLog(String valor) {
		MDC.put("logFileName", valor);
	}
	public synchronized static void resetearDefaultArchivoLog() {
		MDC.remove("logFileName");
	}
	
	
	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Aspecto
	 * @param clase
	 * @param metodo
	 * @param detalle
	 */
	public static void aspectoDebug(Class<?> clase, String metodo, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ASPECTO");
		String usuario = TokenSessionListener.getUsuario();
		log.debug("[{}][{}]: {}", new Object[]{usuario, metodo, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Aspecto
	 * @param clase
	 * @param metodo
	 * @param detalle
	 */
	public static void aspectoInfo(Class<?> clase, String metodo, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ASPECTO");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario, metodo, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: InfoMemoriaCPU
	 * @param clase
	 * @param metodo
	 * @param detalle
	 */
	public static void infoMemoriaCPU(Class<?> clase, String metodo, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "PERFORMANCE");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario, metodo, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Tiempo
	 * @param clase
	 * @param metodo
	 * @param detalle
	 */
	public static void tiempo(Class<?> clase, String metodo, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "TIEMPO");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario, metodo, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Tiempo
	 * @param clase
	 * @param metodo
	 * @param detalle
	 */
	public static void tiempo(Class<?> clase, String usuario, String metodo, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "TIEMPO");
		log.info("[{}][{}]: {}", new Object[]{usuario, metodo, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Debug
	 * Definición: 
	 * [DEBUG] : Detalle
	 */
	public static void debug(Class<?> clase, String detalle) {
		log = LoggerFactory.getLogger(clase);
		if (log.isDebugEnabled()) {
			MDC.put(NIVEL, "DEBUG");
			log.debug(": {}", detalle);
			MDC.put(NIVEL, "");
		}
	}
	
	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Advertencia
	 * Definición: 
	 * [ADVERTENCIA] : Detalle
	 */
	public static void advertencia(Class<?> clase, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ADVERTENCIA");
		String usuario = TokenSessionListener.getUsuario();
		log.warn("[{}]: {}", new Object[]{usuario,detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Advertencia con la excepcion
	 * Definición: 
	 * [ADVERTENCIA] : Detalle, TimeError, Excepcion
	 */
	public static void advertencia(Class<?> clase, String detalle, Throwable ex) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ADVERTENCIA");
		String usuario = TokenSessionListener.getUsuario();
		long timeError = (ex instanceof Excepcion)? ((Excepcion)ex).getTimeError() : 0;
		log.warn("[{}]: {}, {}, {}", new Object[]{usuario,detalle,timeError,ex});
		MDC.put(NIVEL, "");
	}

	/**
	 * Capacidad de log: Advertencia con la descripción de la excepcion
	 * Definición: 
	 * [ADVERTENCIA] : Detalle, Descripcion
	 */
	public static void advertencia(Class<?> clase, String usuario, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ADVERTENCIA");
		log.warn("[{}]: {}", new Object[]{usuario, detalle});
		MDC.put(NIVEL, "");
	}
	
	/**************************************************************************************/
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] : Detalle
	 */
	public static void auditoria(Class<?> clase, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}]: {}", new Object[]{usuario,detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] [Accion] : Detalle
	 */
	public static void auditoria(Class<?> clase, String accion, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario,accion,detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Accion] : Detalle
	 */
	public static void auditoriaLogin(Class<?> clase, String detalle, String usuario) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		log.info("[{}][{}]: {}", new Object[]{usuario, "login", detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] [Accion] : Detalle
	 */
	public static void auditoriaLogin(Class<?> clase, String detalle, String usuario, String roles) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		log.info("[{}][{}][{}]: {}", new Object[]{usuario, roles, "login", detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] [Accion] : Detalle
	 * @throws ParseException 
	 */
	public static void auditoria_tiempo_consulta(Class<?> clase, String query, 
			Date start, Date end, int cantidad) {
		
		try {
			SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
			Date difference = Utilidad.differenceBetweenDates(start, end);
		
			StringBuilder builder = new StringBuilder();
			builder.append("Tiempo de ejecucion de la consulta: ");
			builder.append(query);
			builder.append(" , que ha devuelto ");
			builder.append(cantidad);
			builder.append(" registros, ha sido de: ");
			builder.append(sdfForDateDifference.format(difference));
	
			transaccionBBDD(clase, builder.toString());
		} catch (ParseException e) {
			error(Traza.class, e.getMessage(), e);
		}
	}
	
	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] [Accion] : Detalle
	 * @throws ParseException 
	 */
	public static void auditoria_tiempo_insersion(Class<?> clase, String query, 
			Date start, Date end) {
		
		try {
			SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
			Date difference = Utilidad.differenceBetweenDates(start, end);
		
			StringBuilder builder = new StringBuilder();
			builder.append("Tiempo de ejecucion de la insersión: ");
			builder.append(query);
			builder.append(", ha sido de: ");
			builder.append(sdfForDateDifference.format(difference));
	
			transaccionBBDD(clase, builder.toString());
		} catch (ParseException e) {
			error(Traza.class, e.getMessage(), e);
		}
	}

	/**
	 * Capacidad de log: Auditoria
	 * Definición: 
	 * [AUDITORIA] [Usuario] [Rol] [Accion] : Detalle
	 * @throws ParseException 
	 */
	public static void auditoria_tiempo_actualzacion(Class<?> clase, String query, 
			Date start, Date end) {
		
		try {
			SimpleDateFormat sdfForDateDifference = new SimpleDateFormat(TIME_PATETRN);
			Date difference = Utilidad.differenceBetweenDates(start, end);
		
			StringBuilder builder = new StringBuilder();
			builder.append("Tiempo de ejecucion de la actualizacion: ");
			builder.append(query);
			builder.append(", ha sido de: ");
			builder.append(sdfForDateDifference.format(difference));
	
			transaccionBBDD(clase, builder.toString());
		} catch (ParseException e) {
			error(Traza.class, e.getMessage(), e);
		}
	}
	
	/**
	 * Audito todo sobre MQ Series
	 * @param clase
	 * @param detalle
	 */
	public static void auditoria_MQ(Class<?> clase, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario,"MQ",detalle});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Audito todo sobre WS
	 * @param clase
	 * @param detalle
	 */
	public static void auditoria_WS(Class<?> clase, String detalle) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "AUDITORIA");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}][{}]: {}", new Object[]{usuario,"WS",detalle});
		MDC.put(NIVEL, "");
	}
	
	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Evento
	 * Definición: 
	 * [EVENTO] [Id Evento] [Id Transacción] [Evento] 
	 */
	public static void evento(Class<?> clase, String idEvento, String idTransaccion, String evento) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "EVENTO");
		log.info("[{}][{}][{}]", new Object[]{idEvento,idTransaccion,evento});
		MDC.put(NIVEL, "");
	}
	
	/**************************************************************************************/
	
		/**
	 * Capacidad de log: Transaccion
	 * Definición: 
	 * [CONTROL-TRANSACCION][Usuario]: mensaje
	 */	public static void controlTransaccion(Class<?> clase, String mensaje) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "CONTROL-TRANSACCION");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}]: {}", new Object[]{usuario,mensaje});
		MDC.put(NIVEL, "");
	}
	
	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Transaccion
	 * Definición: 
	 * [CONTROL-TRANSACCION][Usuario]: mensaje
	 */
	public static void posibleAtaqueBruteForce(Class<?> clase, String mensaje) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "POSIBLE-ATAQUE-BRUTE-FORCE");
		log.info("{}", new Object[]{mensaje});
		MDC.put(NIVEL, "");
	}
	
	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Transaccion
	 * Definición: 
	 * [TRANSACCION-BBDD][Usuario]: Sql
	 */
	public static void transaccionBBDD(Class<?> clase, String sql) {
		if (ControlVariablesSingleton.isTracearSQL()) {
			log = LoggerFactory.getLogger(clase);
			MDC.put(NIVEL, "TRANSACCION-BBDD");
			String usuario = TokenSessionListener.getUsuario();
			log.info("[{}]: {}", new Object[]{usuario,sql});
			MDC.put(NIVEL, "");
		} 
	}
	
	/**
	 * Capacidad de log: Transaccion
	 * Definición: 
	 * [TRANSACCION-MQ][Usuario]: Mensaje
	 */
	public static void transaccionMQ(Class<?> clase, String mensaje) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "TRANSACCION-MQ");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}]: {}", new Object[]{usuario,mensaje});
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Transaccion
	 * Definición: 
	 * [TRANSACCION-WS][Usuario]: Mensaje
	 */
	public static void transaccionWS(Class<?> clase, String mensaje) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "TRANSACCION-WS");
		String usuario = TokenSessionListener.getUsuario();
		log.info("[{}]: {}", new Object[]{usuario,mensaje});
		MDC.put(NIVEL, "");
	}

	/**************************************************************************************/
	
	/**
	 * Capacidad de log: Error
	 * Definición: 
	 * [ERROR] Mensaje ERROR
	 */
	public static void error(Class<?> clase, String msg) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ERROR");
		log.error(msg);
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Error
	 * Definición: 
	 * [ERROR] Mensaje ERROR / StackTrace
	 */
	public static void error(Class<?> clase, String msg, Throwable ex) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ERROR");
		String usuario = TokenSessionListener.getUsuario();
		long timeError = (ex instanceof Excepcion)? ((Excepcion)ex).getTimeError() : 0;
		
		String mensaje = "[" + usuario + "][" + timeError + "] ";
		if (Validaciones.isNullOrEmpty(msg)) {
			 mensaje += ex.getMessage(); 
			log.error(mensaje, ex);
		} else {
			 mensaje += msg; 
			log.error(mensaje, ex);
		}
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Error
	 * Definición: 
	 * [ERROR] Mensaje ERROR / StackTrace
	 */
	public static void error(Class<?> clase, String msg, Throwable ex, long timeError) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ERROR");
		String usuario = TokenSessionListener.getUsuario();

		String mensaje = "[" + usuario + "][" + timeError + "] ";
		if (Validaciones.isNullOrEmpty(msg)) {
			 mensaje += ex.getMessage(); 
			log.error(mensaje, ex);
		} else {
			 mensaje += msg; 
			log.error(mensaje, ex);
		}
		MDC.put(NIVEL, "");
	}
	
	/**
	 * Capacidad de log: Error
	 * Definición: 
	 * [ERROR] [Time Error] [Método] [Mensaje ERROR] : StackTrace
	 */
	public static void error(Class<?> clase, String idTransaccion, String metodo, Throwable ex) {
		log = LoggerFactory.getLogger(clase);
		MDC.put(NIVEL, "ERROR");
		long timeError = (ex instanceof Excepcion)? ((Excepcion)ex).getTimeError() : 0;
		log.error("[{}][{}][{}]: {}", new Object[]{timeError,metodo,ex.getMessage(),ex});
		MDC.put(NIVEL, "");
	}	
	

	/************************************************************************************
	 * Others
	 ************************************************************************************/
	/**
	 * http://stackoverflow.com/questions/3837801/how-to-change-root-logging-level-programmatically/3838068#3838068
	 * Me permite cambiar el nivel de loggeo
	 * @param level
	 */
	public static void setLoggingLevel(Level level) {
		//Con esto funciona para una clase pero para los demás no funciona
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) log;
		//ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
		root.setLevel(level);
		root.setAdditive(true);
	}
		
}
