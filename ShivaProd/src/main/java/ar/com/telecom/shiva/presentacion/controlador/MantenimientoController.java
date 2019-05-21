package ar.com.telecom.shiva.presentacion.controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.ITesterJms;
import ar.com.telecom.shiva.base.jms.util.JmsBase;
import ar.com.telecom.shiva.base.jms.util.mq.MQActiveBase;
import ar.com.telecom.shiva.base.jms.util.mq.MQSeriesBase;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;

@Controller
public class MantenimientoController {
	
	private static final String MANTENIMIENTO_PAGE ="mantenimiento/mantenimiento";
	private static final String MANTENIMIENTO_APLICACION_PAGE ="mantenimiento/mantenimientoAplicacion";
	private static final String MANTENIMIENTO_DATABASE_PAGE ="mantenimiento/mantenimientoDatabase";
	private static final String MANTENIMIENTO_TRAZAS_PAGE ="mantenimiento/mantenimientoTrazas";
	private static final String MANTENIMIENTO_MAIL_PAGE ="mantenimiento/mantenimientoMail";
	private static final String MANTENIMIENTO_JMS_PAGE ="mantenimiento/mantenimientoJms";
	private static final String MANTENIMIENTO_WS_PAGE ="mantenimiento/mantenimientoWS";
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private MailServicio mailServicio;
	
	@Autowired
	private ITesterJms micImputacionCobrosTesterMQ;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
	}

	@RequestMapping("/mantenimiento")
	public ModelAndView mantenimiento(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_PAGE);
		
		return mv;	
	}
	
	@RequestMapping("/mantenimientoAplicacion")
	public ModelAndView mantenimientoAplicacion(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_APLICACION_PAGE); 
		mv.addObject("entorno", Constantes.ENTORNO);
		
		//mv.addObject("mostrarCosasDesarrollo", Constantes.ES_ENTORNO_DESA);
		//Por las dudas no pongo por cuestiones de seguridad
		//mv.addObject("servidorJboss", Constantes.SERVIDOR_JBOSS);
		//mv.addObject("servidorShiva", Constantes.SERVIDOR_SHIVA);
		
		return mv;
	}
	
	@RequestMapping("/mantenimientoParametros")
	public ModelAndView mantenimientoParametros(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		Enumeration<String> i = request.getParameterNames();
		while (i.hasMoreElements()) {
			Traza.auditoria(getClass(), i.nextElement());
		}
		
		Traza.auditoria(getClass(), request.getParameter("p3999"));
		Traza.auditoria(getClass(), request.getParameter("p4000"));
		Traza.auditoria(getClass(), request.getParameter("p4500"));
		
		ModelAndView mv=mantenimientoAplicacion(request);
		return mv;	
	}
	
	@RequestMapping("/mantenimientoTrazas")
	public ModelAndView mantenimientoTrazas(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_TRAZAS_PAGE);
		mv.addObject("tracearSQL", ControlVariablesSingleton.isTracearSQL());
		
		List<String> listaArchivosLog = new ArrayList<String>();
		File directorioShivaLog = new File(Constantes.SERVIDOR_SHIVA+"/logs/");
		if(directorioShivaLog.isDirectory()) 
		{ 
			String[] listado = directorioShivaLog.list();
			if (listado != null) {
				for (String nombre: listado) {
					if (nombre.contains("shiva")) {
						File file = new File(Constantes.SERVIDOR_SHIVA+"/logs/"+nombre);
						if (file.exists() && file.isFile()) {
							listaArchivosLog.add(nombre);
						}
					}
				}
			}
		}
		mv.addObject("listaArchivosLog", listaArchivosLog);
		return mv;
	}
	
	
	@RequestMapping("/mantenimientoTracearSQL")
	public ModelAndView mantenimientoTracearSQL(HttpServletRequest request) throws Exception {
		ControlVariablesSingleton.permitirTraceoSQL();
		return mantenimientoTrazas(request);
	}
	@RequestMapping("/mantenimientoQuitarTraceoSQL")
	public ModelAndView mantenimientoQuitarTraceoSQL(HttpServletRequest request) throws Exception {
		ControlVariablesSingleton.quitarTraceoSQL();
		return mantenimientoTrazas(request);
	}
	@RequestMapping("/mantenimientoDescargarShivaLog")
	public ModelAndView mantenimientoDescargarShivaLog(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		if (!Validaciones.isNullOrEmpty(request.getParameter("nombreArchivoADescargar"))) 
		{
			String nombreArchivo = (String)request.getParameter("nombreArchivoADescargar");
			String path = Constantes.SERVIDOR_SHIVA+"/logs/" + nombreArchivo;
			ControlArchivo.descargarArchivo(path, resp);
		}
		
		return null;
	}
	@RequestMapping("/mantenimientoDescargarServidorLog")
	public ModelAndView mantenimientoDescargarServidorLog(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		String path = Constantes.SERVIDOR_JBOSS_LOG+"/server.log";
		ControlArchivo.descargarArchivo(path, resp); 
    	return null;
	}
	
	@RequestMapping("/mantenimientoDatabase")
	public ModelAndView mantenimientoDatabase(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_DATABASE_PAGE);
		mv.addObject("estadisticas", parametroServicio.getEstadisticas());
		return mv;
	}
	
	@RequestMapping("/mantenimientoMail")
	public ModelAndView mantenimientoMail(HttpServletRequest request) throws Exception {
		ModelAndView mv=mantenimientoMailGeneral(); 
		mv.addObject("mail", null);
		return mv;
	}
	
	@RequestMapping("/mantenimientoMailTest")
	public ModelAndView mantenimientoMailTest(HttpServletRequest request) throws Exception {
		String resultado = "Se ha enviado exitosamente";
		try {
			mailServicio.testMail();
		} catch (Exception ex) {
			resultado = Utilidad.getStackTrace(ex);
		}
		
		ModelAndView mv=mantenimientoMailGeneral(); 
		mv.addObject("mail", resultado);
		return mv;
	}
	/**
	 * Metodo Refactorizado
	 * @return
	 */
	private ModelAndView mantenimientoMailGeneral() {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_MAIL_PAGE); 
		mv.addObject("mail_host", mailServicio.getMailConfiguracion().getHost()); 
		mv.addObject("mail_port", mailServicio.getMailConfiguracion().getPort());
		mv.addObject("mail_from", mailServicio.getMailConfiguracion().getFromName()+" ("+mailServicio.getMailConfiguracion().getFrom() + ")"); 
		mv.addObject("mail_auth", mailServicio.getMailConfiguracion().getAuth());
		mv.addObject("mail_encoding", mailServicio.getMailConfiguracion().getEncoding()); 
		mv.addObject("mail_debug", mailServicio.getMailConfiguracion().getDebug());
		mv.addObject("mail_toTest", mailServicio.getMailConfiguracion().getTo());
		return mv;
	}
	
	
	@RequestMapping("/mantenimientoJms")
	public ModelAndView mantenimientoJms(HttpServletRequest request) throws Exception {
		ModelAndView mv=pruebaConexionJMS();
		return mv;
	}
	
	/**
	 * Metodo Refactorizado que prueba la conexionJMQ e informa los resultados a la pagina
	 * @return
	 * @throws JmsExcepcion 
	 */
	private ModelAndView pruebaConexionJMS() throws JmsExcepcion {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_JMS_PAGE); 
		/**Obtengo las propiedades del MIC*/
		JmsBase base = micImputacionCobrosTesterMQ.verificarPropiedadesJms();
		if (base instanceof MQSeriesBase) {
			MQSeriesBase baseMQSeries = (MQSeriesBase) micImputacionCobrosTesterMQ.verificarPropiedadesJms();
			
			mv.addObject("mq_mic_host", baseMQSeries.getConnectionFactory().getHostName()); 
			mv.addObject("mq_mic_queueManager", baseMQSeries.getConnectionFactory().getQueueManager());
			mv.addObject("mq_mic_port", baseMQSeries.getConnectionFactory().getPort()); 
			mv.addObject("mq_mic_transportType", baseMQSeries.getConnectionFactory().getTransportType());
			mv.addObject("mq_mic_channel", baseMQSeries.getConnectionFactory().getChannel()); 
			mv.addObject("mq_mic_queue_receptor", baseMQSeries.getQueueReceptor().getBaseQueueName());
			mv.addObject("mq_mic_queue_emisor", baseMQSeries.getQueueEmisor().getBaseQueueName()); 
			
			mv.addObject("mq_mic_aplicacion", micImputacionCobrosTesterMQ.getJmsPropiedadesContexto().getAplicacion());
			mv.addObject("mq_mic_servicio", micImputacionCobrosTesterMQ.getJmsPropiedadesContexto().getServicio());
			mv.addObject("mq_mic_contrato_1", 
					Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.contrato.1"));
			mv.addObject("mq_mic_contrato_2", 
					Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.contrato.2"));
			
			/**Verifico el estado de conexion al MIC*/
			String micEstadoServidor = "Deconectado";
			String micEstadoQueueManager = "Cerrado";
			String micExcepcion = "";
			try {
				baseMQSeries = (MQSeriesBase) micImputacionCobrosTesterMQ.verificarConexion();
				boolean estado = baseMQSeries.isServidorMQconectada();
				if (estado) {
					micEstadoServidor = "Conectado";
				}
				
				estado = baseMQSeries.isQueueManagerAbierta();
				if (estado) {
					micEstadoQueueManager = "Abierto";
				}
			} catch (JmsExcepcion e) {
				micEstadoServidor = "Error al conectar";
				micExcepcion = Utilidad.getStackTrace(e);
			}
			mv.addObject("micEstadoServidor", micEstadoServidor);
			mv.addObject("micEstadoQueueManager", micEstadoQueueManager);
			mv.addObject("micExcepcion", micExcepcion);
		} else {
			
			/**Verifico el estado de conexion al MIC*/
			String micEstadoServidor = "Deconectado";
			String micEstadoQueueManager = "Cerrado";
			String micExcepcion = "";
			try {
				MQActiveBase baseMQActive = (MQActiveBase) micImputacionCobrosTesterMQ.verificarPropiedadesJms();
				
				mv.addObject("mq_mic_host", baseMQActive.getConnectionFactory().getBrokerURL()); 
				mv.addObject("mq_mic_queueManager", "");
				mv.addObject("mq_mic_port", ""); 
				mv.addObject("mq_mic_transportType", "");
				mv.addObject("mq_mic_channel", ""); 
				mv.addObject("mq_mic_queue_input", baseMQActive.getQueueReceptor().getQueueName());
				mv.addObject("mq_mic_queue_output", baseMQActive.getQueueEmisor().getQueueName()); 
			
				baseMQActive = (MQActiveBase) micImputacionCobrosTesterMQ.verificarConexion();
				boolean estado = baseMQActive.isServidorMQconectada();
				if (estado) {
					micEstadoServidor = "Conectado";
				}
				
				estado = baseMQActive.isQueueManagerAbierta();
				if (estado) {
					micEstadoQueueManager = "Abierto";
				}
			} catch (JMSException | JmsExcepcion e) {
				micEstadoServidor = "Error al conectar";
				micExcepcion = Utilidad.getStackTrace(e);
			}
			mv.addObject("micEstadoServidor", micEstadoServidor);
			mv.addObject("micEstadoQueueManager", micEstadoQueueManager);
			mv.addObject("micExcepcion", micExcepcion);
		}
		
		mv.addObject("mostrarPruebaMic", Constantes.ES_ENTORNO_DESA || Constantes.ES_ENTORNO_INTE);
		return mv;
	}

	
	@RequestMapping("/mantenimientoWS")
	public ModelAndView mantenimientoWS(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView(MANTENIMIENTO_WS_PAGE);
		return mv;
	}
}
