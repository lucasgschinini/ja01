package ar.com.telecom.shiva.negocio.servicios.impl;

import java.text.ParseException;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IArchivoAVCDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;

/**
 * 
 * @author 
 *
 */
public class ArchivoAVCSoporteServicioImpl implements IArchivoAVCSoporteServicio {

	@Autowired
	private MailServicio mailServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired 
	private IArchivoAVCDao archivoAVCDao;
	

	@Override
	public ShvAvcArchivoAvc buscarArchivoAvcPorNombreArchivo(String name) throws NegocioExcepcion {
		ShvAvcArchivoAvc archivoAvc;
		try {
			archivoAvc = archivoAVCDao.buscarArchivoAvcPorNombreArchivo(name);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return archivoAvc;
	}
	
	
	public void enviarMailArchivoProcesadoAnteriormente(ShvAvcArchivoAvc archivo) throws NegocioExcepcion {
		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_PROCESADO_ANTERIORMENTE + " "
				+ Utilidad.formatDateAndTimeFull(archivo.getFechaProcesamiento()));
		
		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_RECHAZADO, archivo.getNombreArchivo()) , cuerpoMail );
		mailServicio.sendMail(mail);
	}
	
	/**
	 * Envia el mail aclarando que el nombre de archivo encontrado en el directorio
	 * es incorrecto.
	 */
	
	
	public void enviarMailPorNombreIncorrectoArchivoAVC(String name) throws NegocioExcepcion {
		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_NOMBRE_INCORRECTO);
		
		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_ARCHIVO_NOMBRE_INCORRECTO, name) , cuerpoMail );
		mailServicio.sendMail(mail);
	}


	@Override
	public boolean esNombreArchivoRegistroAVCDinamico(
			String nombreArchivoCompleto) {
		if (!Validaciones.isObjectNull(nombreArchivoCompleto) && nombreArchivoCompleto.startsWith("AVC_") && nombreArchivoCompleto.endsWith(Constantes.ARCHIVO_CSV)) {
			
			String nombreArchivo =  nombreArchivoCompleto.substring(0, nombreArchivoCompleto.length()-4);
			String[] campos = nombreArchivo.split("_");
			
			int numeroDeAcuerdo = Integer.parseInt(campos[1]);
			
			if(campos.length == 4){
				
				if(numeroDeAcuerdo>=1 && numeroDeAcuerdo<=999){
					
					if(campos[2].length()==8 && Validaciones.isNumeric(campos[2])){
						try{
							
							Utilidad.parseDateWSFormat(campos[2]);
							
							if(campos[3].length()==2 && Validaciones.isNumeric(campos[3])){
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


	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvAvcArchivoAvc crear(ShvAvcArchivoAvc archivoAvc) throws NegocioExcepcion {
				
		try {
			archivoAvc = archivoAVCDao.crear(archivoAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
		return archivoAvc;
	}


	@Override
	public void enviarMailConProcesamientoArchivoAVC(
			GenericDataFile archivoAvcInsertado, Long totalRegistroEnArchivo, long cantOk, long cantError, JobExecution jobExecution)
			throws NegocioExcepcion {
		
		String estado;
		Mail mail = null;

			/**
		 	* @author u573005, sprint2
		 	*Se agrega el replace del caracter "<" ya que si llega texto en el archivo con "<>" no se muestra en el mail
		 	* a causa de que lo interpreta como un tag html 
		 	*/				
			String logProcesamiento = archivoAvcInsertado.getLogProcesamiento();
			archivoAvcInsertado.setLogProcesamiento(logProcesamiento.replace("<", HtmlUtils.htmlEscape("<")).replace(System.lineSeparator(), "<br>"));
		
			 estado="OK";
			 Traza.auditoria(ArchivoAVCSoporteServicioImpl.class, "Enviando mail de procesamiento Archivo AVC finalizado. Estado: " + estado);		
			  mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
						Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_OK, archivoAvcInsertado.getNombreArchivo()) , generarCuerpoMail(archivoAvcInsertado, totalRegistroEnArchivo, cantOk, cantError, estado));
			  mailServicio.sendMail(mail);		
//		}
		
		
	}
	
	@Override
	public void enviarMailRegistrosErroneosArchivoAVC(
			GenericDataFile archivoAvcInsertado, Long totalRegistroEnArchivo,
			long cantOK, long cantError, JobExecution jobExecution)
			throws NegocioExcepcion {

		  /**
	 	  * @author u573005, sprint2
	 	  *Se agrega el replace del caracter "<" ya que si llega texto en el archivo con "<>" no se muestra en el mail
	 	  * a causa de que lo interpreta como un tag html 
	 	  */				
		  String logProcesamiento = archivoAvcInsertado.getLogProcesamiento();
		  archivoAvcInsertado.setLogProcesamiento(logProcesamiento.replace("<", HtmlUtils.htmlEscape("<")).replace(System.lineSeparator(), "<br>"));
		
		  String estado="NOK"; 
		  System.out.println("Exceptions: "+jobExecution.getAllFailureExceptions().toString());
		  Traza.auditoria(ArchivoAVCSoporteServicioImpl.class, "Enviando mail de procesamiento Archivo AVC finalizado. Estado: " + estado);		
		  Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
					Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_RECHAZADO, archivoAvcInsertado.getNombreArchivo()) , generarCuerpoMail(archivoAvcInsertado, totalRegistroEnArchivo, cantOK, cantError, estado));
		  mailServicio.sendMail(mail);	
		
		
	}


//	@Override
//	public void enviarMailPorErrorProcesoArchivoAVC(String name)
//			throws NegocioExcepcion {
//		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_NOMBRE_ERROR);
//		
//		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
//				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_ARCHIVO_NOMBRE_ERROR, name) , cuerpoMail);
//		mailServicio.sendMail(mail);		
//	}
	
	/**
	 * Crea el cuerpo del mail cuando se proceso un archivo AVC
	 * @param resultado
	 * @param cantRegistrosArchivoTotal cantidad de registros que tenia el archivo. Sin importar si fueron procesados o no.
	 * @return
	 */
	private StringBuffer generarCuerpoMail(GenericDataFile resultado, Long cantRegistrosArchivoTotal, long cantOk, long cantError, String estado) {
		StringBuffer cuerpoMail = null;
		
		if(estado.equals("OK")){
			cuerpoMail = armarCuerpoProcesadoOk(cantRegistrosArchivoTotal, resultado, cantOk, cantError, estado);
		}else if (estado.equals("NOK")) {
			cuerpoMail = armarCuerpoProcesadoRechazado(cantRegistrosArchivoTotal, resultado, cantOk, cantError, estado);
		}	
		
		return cuerpoMail;
	}

	/**
	 * 
	 * @param cantRegistrosArchivoTotal
	 * @param resultado
	 * @param cantOk
	 * @param cantError
	 * @param estado
	 * @return
	 */
	private StringBuffer armarCuerpoProcesadoOk(Long cantRegistrosArchivoTotal, GenericDataFile resultado, long cantOk, long cantError, String estado) {
		StringBuffer cuerpoMail;
		
		cuerpoMail = new StringBuffer(	"El Archivo '" + resultado.getNombreArchivo()+"' se ha procesado correctamente.<br><br>" +
										"-Registros Totales: " + cantRegistrosArchivoTotal + "<br>" +
										"-Registros Procesados: " + cantOk + "<br>" +
										"-Registros Descartados: " + cantError + "<br><br>" +
										"El resultado del procesamiento fue: <br><br>"  +
										 resultado.getLogProcesamiento() + "<br>");
		return cuerpoMail;
	}
	
	/**
	 * 
	 * @param cantRegistrosArchivoTotal
	 * @param resultado
	 * @param cantOk
	 * @param cantError
	 * @param estado
	 * @return
	 */
	private StringBuffer armarCuerpoProcesadoRechazado(Long cantRegistrosArchivoTotal, GenericDataFile resultado, long cantOk, long cantError, String estado) {
		StringBuffer cuerpoMail;
		
		cuerpoMail = new StringBuffer(	"El Archivo '" + resultado.getNombreArchivo() + "' no se ha procesado correctamente.<br><br>" +
										"-Registros Totales: " + cantRegistrosArchivoTotal + "<br>" +
										"-Registros Procesados: " + cantOk + "<br>" +
										"-Registros Descartados: " + cantError + "<br><br>" +
										"El resultado del procesamiento fue: <br><br>" + 
										resultado.getLogProcesamiento() + "<br>");
		
		return cuerpoMail;
	}


	@Override
	public void enviarMailPorArchivoVacio(String name) throws NegocioExcepcion {
		StringBuffer cuerpoMail = new StringBuffer("El archivo ")
			.append(name)
			.append(" se encuentra vacío. Se procede a rechazarlo.");
		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC),
				
				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_ARCHIVO_AVC_VACIO, name) , cuerpoMail);
		mailServicio.sendMail(mail);
	}




}
