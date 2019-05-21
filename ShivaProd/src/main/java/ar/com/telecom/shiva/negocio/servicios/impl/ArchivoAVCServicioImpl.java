package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.mapeos.ArchivoAVCMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCDepositoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCInterdepositoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCTransferenciaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaArchivoAVC;
import ar.com.telecom.shiva.persistencia.dao.IArchivoAVCDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAVCDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ArchivoAVCServicioImpl extends Servicio implements IArchivoAVCServicio{

	@Autowired private IParametroServicio parametroServicio;
	@Autowired private ArchivoAVCMapeador archivoAvcMapeador;
	@Autowired private RegistroAVCDepositoMapeador depositoMapeador;
	@Autowired private RegistroAVCInterdepositoMapeador interdepositoMapeador;
	@Autowired private RegistroAVCTransferenciaMapeador transferenciaMapeador;
	@Autowired IRegistroAVCDao registroAVCDao;
	@Autowired IArchivoAVCDao archivoAVCDao;
	@Autowired IWorkflowRegistrosAVC workflowRegistrosAVC;
	@Autowired private MailServicio mailServicio;
	
	/**
	 * Inserta el archivo y los registros AVC en la base. Ademas sube el archivo como documento adjunto.
	 * @param archivoAVC
	 * @param listaRegistrosAVC
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvAvcArchivoAvc crearArchivoConRegistrosAVC(ArchivoAVCDto archivoAVC, List<RegistroAVCDto> listaRegistrosAVC) throws NegocioExcepcion {
		
		try {
			ShvAvcArchivoAvc archivoAVCModelo = (ShvAvcArchivoAvc) archivoAvcMapeador.map(archivoAVC, null);
			adjuntarArchivo(archivoAVC,archivoAVCModelo);
	        ShvAvcArchivoAvc archivoAVCInsertado = archivoAVCDao.crear(archivoAVCModelo);
	        
	        // Inserto el archivo y los registros AVC en la base
	        if (Validaciones.isCollectionNotEmpty(listaRegistrosAVC)) {
		        for (RegistroAVCDto registroAvc : listaRegistrosAVC) {
		        	ShvAvcRegistroAvc registroAVCModelo = null;
		        	
		        	// Deposito
		        	if(ConciliacionServicioImpl.esDeposito(registroAvc.getAcuerdo())){
		        		DepositoDto deposito = (DepositoDto) registroAvc;
		        		
		        		registroAVCModelo = (ShvAvcRegistroAvc) depositoMapeador.map(deposito, null);
		        		String datosOriginales = deposito.getDatosOriginalesRegistroAVC();
			    		datosOriginales += Utilidad.datosOriginales(deposito, workflowRegistrosAVC.getListaDatosOriginalesDeposito());
			    		ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			    		registroAVCModelo.setWorkFlow(workflow);
		        	} else {
			        	// Interdeposito
			        	if (TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equals((registroAvc.getAcuerdo()))) {
			        		InterdepositoDto interdeposito = (InterdepositoDto) registroAvc;
			        		
			        		registroAVCModelo = (ShvAvcRegistroAvc) interdepositoMapeador.map(interdeposito, null);
			        		String datosOriginales = interdeposito.getDatosOriginalesRegistroAVC();
			    			datosOriginales += Utilidad.datosOriginales(interdeposito, workflowRegistrosAVC.getListaDatosOriginalesInterdeposito());
			    			ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			    			registroAVCModelo.setWorkFlow(workflow);
				    			
						} else {
							//Transferencia
							TransferenciaDto transferencia = (TransferenciaDto) registroAvc;
							if (TipoAcuerdoEnum.TRANSFERENCIA.descripcion().equals(transferencia.getAcuerdo())){
								buscarCuit(transferencia);
							}
							registroAVCModelo = (ShvAvcRegistroAvc) transferenciaMapeador.map(transferencia, null);
							String datosOriginales = transferencia.getDatosOriginalesRegistroAVC();
					    	datosOriginales += Utilidad.datosOriginales(transferencia, workflowRegistrosAVC.getListaDatosOriginalesTransferencia());
					    	ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
					    	registroAVCModelo.setWorkFlow(workflow);
					    }
		        	}
		        	
		        	registroAVCModelo.setArchivoAvc(archivoAVCInsertado);
		        	registroAVCModelo = registroAVCDao.crear(registroAVCModelo);
				}
	        }
	        
	        return archivoAVCInsertado;
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	private void buscarCuit(TransferenciaDto transferencia){
		transferencia.setCuit(Utilidad.cuitFormateado(transferencia.getObservacion().toCharArray()));
	}


	
	public DTO buscar(Integer id) throws NegocioExcepcion {
		try { 
			ShvAvcArchivoAvc archivo = archivoAVCDao.buscarArchivoPorIdArchivo(id);
			ArchivoAVCDto archivoDTO = (ArchivoAVCDto)archivoAvcMapeador.map(archivo);
			return archivoDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		ArchivoAVCFiltro archivoFiltro = (ArchivoAVCFiltro) filtro;
		Collection<DTO> listaArchivoDTO = new ArrayList<DTO>();
		try {
			List<ResultadoBusquedaArchivoAVC> listaResultadoBusquedaArchivoAVC = new ArrayList<ResultadoBusquedaArchivoAVC>();
			if(Validaciones.isNullOrEmpty(archivoFiltro.getFechaDesde()) && Validaciones.isNullOrEmpty(archivoFiltro.getFechaHasta())){
				listaResultadoBusquedaArchivoAVC = (List<ResultadoBusquedaArchivoAVC>)archivoAVCDao.buscarArchivosAvcParaResultadoConciliacion(archivoFiltro);
			}else{
				listaResultadoBusquedaArchivoAVC = (List<ResultadoBusquedaArchivoAVC>)archivoAVCDao.buscarArchivosAvc(archivoFiltro);
			}
			
			for (ResultadoBusquedaArchivoAVC resultadoBusquedaArchivoAVC : listaResultadoBusquedaArchivoAVC) {
				listaArchivoDTO.add(archivoAvcMapeador.map(resultadoBusquedaArchivoAVC));
			}
			
			return listaArchivoDTO;			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Envia mail con el procesamiento del archivo.
	 */
	public void enviarMailConProcesamientoArchivoAVC(ShvAvcArchivoAvc archivoAvcInsertado, String logProcesamiento, Boolean procesoOK, Long totalRegistroEnArchivo) throws NegocioExcepcion {
		try{
			ArchivoAVCFiltro archivoFiltro = new ArchivoAVCFiltro();
			archivoFiltro.setIdArchivoAvc(archivoAvcInsertado.getIdArchivosAvc());
			List<ResultadoBusquedaArchivoAVC> listaProcesamiento = (List<ResultadoBusquedaArchivoAVC>) archivoAVCDao.buscarArchivosAvc(archivoFiltro);
			if(Validaciones.isCollectionNotEmpty(listaProcesamiento)){
				ResultadoBusquedaArchivoAVC resultado = listaProcesamiento.get(0);

				/**
				 * @author u573005, sprint2
				 *Se agrega el replace del caracter "<" ya que si llega texto en el archivo con "<>" no se muestra en el mail
				 * a causa de que lo interpreta como un tag html 
				 */				
				resultado.setLogProcesamiento(logProcesamiento.replace("<", HtmlUtils.htmlEscape("<")).replace(System.lineSeparator(), "<br>"));
				
				if (procesoOK){
					Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
							Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_OK, archivoAvcInsertado.getNombreArchivo()) , generarCuerpoMail(resultado,procesoOK,totalRegistroEnArchivo));
					mailServicio.sendMail(mail);
				} else {
					Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
							Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_RECHAZADO, archivoAvcInsertado.getNombreArchivo()) , generarCuerpoMail(resultado,procesoOK,totalRegistroEnArchivo));
					mailServicio.sendMail(mail);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Crea el cuerpo del mail cuando se proceso un archivo AVC
	 * @param resultado
	 * @param cantRegistrosArchivoTotal cantidad de registros que tenia el archivo. Sin importar si fueron procesados o no.
	 * @return
	 */
	private StringBuffer generarCuerpoMail(ResultadoBusquedaArchivoAVC resultado, Boolean procesoOK, Long cantRegistrosArchivoTotal) {
		StringBuffer cuerpoMail = null;
		
		if(Mensajes.CUERPO_MAIL_ARCHIVO_AVC_PROCESADO_OK.equalsIgnoreCase((resultado.getLogProcesamiento()))
			|| Mensajes.CUERPO_MAIL_ARCHIVO_AVC_VACIO.startsWith(resultado.getLogProcesamiento())
			|| procesoOK){
			cuerpoMail = armarCuerpoProcesadoOk(resultado, cantRegistrosArchivoTotal);
		}else{
			cuerpoMail = new StringBuffer("El Archivo '" + resultado.getNombreArchivo() + "' no se ha procesado correctamente.<br><br>" +
					"El resultado del procesamiento fue: <br><br>" + 
					resultado.getLogProcesamiento() + "<br>");
		}	
		
		return cuerpoMail;
	}

	/**
	 * @author u573005, sprint3 
	 * Se refactoriza en un metodo que arme el cuerpo para los casos
	 * en que el archivo se procesa correctamente
	 * @param resultado
	 * @param cantRegistrosArchivoTotal
	 * @return
	 */
	private StringBuffer armarCuerpoProcesadoOk(ResultadoBusquedaArchivoAVC resultado, Long cantRegistrosArchivoTotal) {
		StringBuffer cuerpoMail;
		String resultadoDetalle = "";
		String registrosDescartados = calcularCantidadDescartados(cantRegistrosArchivoTotal,resultado.getRegistrosProcesados(),resultado.getNroAcuerdo());
		if(!Validaciones.isNullOrEmpty(registrosDescartados)){
			if(Long.valueOf(registrosDescartados) > 0){
				registrosDescartados = " - Registros Descartados: " + registrosDescartados + "<br>";
			}else{
				registrosDescartados = "<br>";
			}
		}
		
		if(!Mensajes.CUERPO_MAIL_ARCHIVO_AVC_PROCESADO_OK.equalsIgnoreCase((resultado.getLogProcesamiento()))){
			resultadoDetalle = resultado.getLogProcesamiento() + "<br>";
		}		
		cuerpoMail = new StringBuffer("El Archivo '" + resultado.getNombreArchivo() + "' se ha procesado correctamente.<br><br>" +
										"El resultado del procesamiento fue: <br><br>" +
										" - Registros Procesados: " + resultado.getRegistrosProcesados() + "<br>" +
										registrosDescartados +
										resultadoDetalle);
		return cuerpoMail;
	}

	private String calcularCantidadDescartados(Long cantRegistrosArchivoTotal, Long registrosProcesados, String nroAcuerdo) {
		
		if (ConciliacionServicioImpl.esDeposito(nroAcuerdo)){
			// resto 1 por la cabecera
			return String.valueOf(cantRegistrosArchivoTotal-registrosProcesados-1);
		} else {
			if (ConciliacionServicioImpl.esInterdeposito(nroAcuerdo)){
				// resto 1 por la cabecera
				return String.valueOf(cantRegistrosArchivoTotal-registrosProcesados-1);
			} else {
				// resto 2 por la cabecera y la ultima linea de totales
				return String.valueOf(cantRegistrosArchivoTotal-registrosProcesados-2);
			}
		}
	}

	
//
//	/**
//	 * Envia el mail aclarando que el archivo que se quizo procesar
//	 * ya habia sido procesado anteriormente.
//	 */
//	public void enviarMailArchivoProcesadoAnteriormente(ShvAvcArchivoAvc archivo) throws NegocioExcepcion {
//		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_PROCESADO_ANTERIORMENTE + " "
//				+ Utilidad.formatDateAndTimeFull(archivo.getFechaProcesamiento()));
//		
//		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
//				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_ARCHIVOAVC_RECHAZADO, archivo.getNombreArchivo()) , cuerpoMail );
//		mailServicio.sendMail(mail);
//	}
//	
//	/**
//	 * Envia el mail aclarando que el nombre de archivo encontrado en el directorio
//	 * es incorrecto.
//	 */
//	public void enviarMailPorNombreIncorrectoArchivoAVC(String name) throws NegocioExcepcion {
//		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_NOMBRE_INCORRECTO);
//		
//		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
//				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_ARCHIVO_NOMBRE_INCORRECTO, name) , cuerpoMail );
//		mailServicio.sendMail(mail);
//	}
//	
	/**
	 * Envia el mail aclarando que el nombre de archivo encontrado en el directorio
	 * es incorrecto.
	 */
	public void enviarMailPorErrorProcesoArchivoAVC(String name) throws NegocioExcepcion {
		StringBuffer cuerpoMail = new StringBuffer(Mensajes.CUERPO_MAIL_ARCHIVO_NOMBRE_ERROR);
		
		Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_ARCHIVOAVC), 
				Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_ARCHIVO_NOMBRE_ERROR, name) , cuerpoMail);
		mailServicio.sendMail(mail);
	}
	/**
	 * asocia al idArchivoAvc insertando en la tabla SHV_AVC_REG_AVC_ADJUNTO
	 */
	public void adjuntarArchivo(ArchivoAVCDto archivoAVC, ShvAvcArchivoAvc archivoAVCModelo) throws PersistenciaExcepcion {
		try {
			// Genero el documento y lo inserto en la base
			ShvDocDocumentoAdjunto documentoModelo = new  ShvDocDocumentoAdjunto();
			documentoModelo.setNombreArchivo(archivoAVC.getNombreArchivo());
			documentoModelo.setArchivoAdjunto(archivoAVC.getArchivoAdjunto());
			documentoModelo.setFechaCreacion(archivoAVC.getFechaProcesamiento());
			documentoModelo.setUsuarioCreacion(archivoAVC.getUsuarioProcesamiento());
			
			// Asocio el adjunto con el archivo
			archivoAVCModelo.setDocumentoAdjunto(documentoModelo);
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e.getMessage(), e);
		}
	}
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	public void anular(DTO dto) throws NegocioExcepcion {
	}

	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public IRegistroAVCDao getRegistroAVCDao() {
		return registroAVCDao;
	}

	public void setRegistroAVCDao(IRegistroAVCDao registroAVCDao) {
		this.registroAVCDao = registroAVCDao;
	}

	public IArchivoAVCDao getArchivoAVCDao() {
		return archivoAVCDao;
	}

	public void setArchivoAVCDao(IArchivoAVCDao archivoAVCDao) {
		this.archivoAVCDao = archivoAVCDao;
	}

	public IWorkflowRegistrosAVC getWorkflowRegistrosAVC() {
		return workflowRegistrosAVC;
	}

	public void setWorkflowRegistrosAVC(IWorkflowRegistrosAVC workflowRegistrosAVC) {
		this.workflowRegistrosAVC = workflowRegistrosAVC;
	}

	public RegistroAVCDepositoMapeador getDepositoMapeador() {
		return depositoMapeador;
	}

	public void setDepositoMapeador(RegistroAVCDepositoMapeador depositoMapeador) {
		this.depositoMapeador = depositoMapeador;
	}

	public RegistroAVCInterdepositoMapeador getInterdepositoMapeador() {
		return interdepositoMapeador;
	}

	public void setInterdepositoMapeador(RegistroAVCInterdepositoMapeador interdepositoMapeador) {
		this.interdepositoMapeador = interdepositoMapeador;
	}

	public RegistroAVCTransferenciaMapeador getTransferenciaMapeador() {
		return transferenciaMapeador;
	}

	public void setTransferenciaMapeador(RegistroAVCTransferenciaMapeador transferenciaMapeador) {
		this.transferenciaMapeador = transferenciaMapeador;
	}
	
	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public ArchivoAVCMapeador getArchivoAvcMapeador() {
		return archivoAvcMapeador;
	}

	public void setArchivoAvcMapeador(ArchivoAVCMapeador archivoAvcMapeador) {
		this.archivoAvcMapeador = archivoAvcMapeador;
	}

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}

}
