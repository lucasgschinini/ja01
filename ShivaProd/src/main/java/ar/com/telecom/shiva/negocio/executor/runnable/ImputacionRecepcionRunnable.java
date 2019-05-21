package ar.com.telecom.shiva.negocio.executor.runnable;

import java.text.DecimalFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TrazaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicRespuestaRecepcionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaRecepcionSalida;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.soa.reintentos.IReintentos;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionRecepcionRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

public class ImputacionRecepcionRunnable implements Runnable {
	
	private String idThread;
	private ImputacionRecepcionRto datosEntrada;
	private String usuarioBatchCobrosRecepcion = "";
	
	private IControlJms micControlMQ;
	private IParametroServicio parametroServicio;
	private IDescobroImputacionServicio descobroImputacionServicio;
	
	private IReintentos reintentosMensajeria;
	private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	private ITransaccionCobroServicio transaccionCobroServicio;
	private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	
	private MapeadorJMS micRespuestaRecepcionMapeoJMS;
	private MapeadorJMS micRespuestaADCMapeoJMS;
	private MapeadorJMS micRespuestaGeneracionCargoMapeoJMS;
	private MapeadorJMS micRespuestaDescobroMapeoJMS;
	
	private	ICobroDao cobroDao;
	private IDescobroDao descobroDao;
	
	public ImputacionRecepcionRunnable(ImputacionRecepcionRto datosEntrada) {
		this.datosEntrada = datosEntrada;
	}
	
	/**
	 * 
	 * @throws NegocioExcepcion 
	 */
	private void inicializarServicios(IControlJms micControlMQ) throws NegocioExcepcion {
		this.micControlMQ =  micControlMQ;
		parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
		this.usuarioBatchCobrosRecepcion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_RECEPCION); 
		descobroImputacionServicio = (IDescobroImputacionServicio) Configuracion.getBeanBatch("descobroImputacionServicio");
		
		reintentosMensajeria = (IReintentos) Configuracion.getBeanBatch("reintentosMensajeria");
		mensajeriaTransaccionServicio = (IMensajeriaTransaccionServicio) Configuracion.getBeanBatch("mensajeriaTransaccionServicio");
		transaccionCobroServicio = (ITransaccionCobroServicio) Configuracion.getBeanBatch("transaccionCobroServicio");
		
		micRespuestaRecepcionMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaRecepcionMapeoJMS");
		micRespuestaADCMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaADCMapeoJMS");
		micRespuestaGeneracionCargoMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaGeneracionCargoMapeoJMS");
		micRespuestaDescobroMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaDescobroMapeoJMS");
		
		cobroBatchSoporteImputacionMicServicio = (ICobroBatchSoporteImputacionMicServicio) Configuracion.getBeanBatch("cobroSoporteMicServicio");
		
		cobroDao =(ICobroDao) Configuracion.getBeanBatch("cobroDao");
		descobroDao = (IDescobroDao) Configuracion.getBeanBatch("descobroDao");
	}
	
	/**
	 * 
	 */
	private void destruirServicios() {
		parametroServicio = null;
		descobroImputacionServicio = null;
		
		reintentosMensajeria = null;
		mensajeriaTransaccionServicio = null;
		transaccionCobroServicio = null;
		
		micRespuestaRecepcionMapeoJMS = null;
		micRespuestaADCMapeoJMS = null;
		micRespuestaGeneracionCargoMapeoJMS = null;
		micRespuestaDescobroMapeoJMS = null;
		
		cobroBatchSoporteImputacionMicServicio = null;
	}
	
	@Override
	public void run() {
		
		final String orgName = Thread.currentThread().getName();
		double startTime = System.nanoTime();
		
		try {
			this.inicializarServicios(datosEntrada.getMicControlMQ());
			this.idThread = "Mensaje recibido nro: " + this.datosEntrada.getCount();
			Thread.currentThread().setName(this.idThread);
			
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo: {0}.", this.idThread));
			
			this.procesarMensajeRecibido(
					datosEntrada.getCount(), datosEntrada.getMensajeRecibido(), datosEntrada.getTipoProceso());
			
		} catch (Throwable e) {
			Thread.currentThread().setName(this.idThread);
			Traza.error(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha producido un error al procesar ({0})", this.idThread));
			Traza.error(this.getClass(),e.getMessage(), e);
			Traza.auditoria(this.getClass(), "-------------------------------------------------------------------------------------");
			
		} finally {
			destruirServicios();
			Thread.currentThread().setName(this.idThread);
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo: {0}.", this.idThread));
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			
			Traza.tiempo(this.getClass(), usuarioBatchCobrosRecepcion, "run", detalle);
			Traza.infoMemoriaCPU(this.getClass(), "run", ControlMemoriaCPU.getInformacionMemoria());
			
			Thread.currentThread().setName(orgName);
		}
	}

	
	
	/**
	 * Servicio que procesa el mensaje recibido
	 * @param message
	 */
	private void procesarMensajeRecibido(int contador, Message message, TipoProcesoEnum tipoProceso) {
		try {   
			Traza.transaccionMQ(getClass(), "Entrada - Propiedades: " + message.toString());

			TextMessage tm = (TextMessage) message;
			String tmText = tm.getText();
            
            // He visto que recibimos caracteres 0 (de tipo nulo(NUL)) 
            // y en las trazas se eliminan estos caracteres por eso
            // he decidido reemplazarlos por un espacio
            char[] caracteres = tmText.toCharArray(); 
    	    for (int i=0;i<caracteres.length;i++) {
    	        if (caracteres[i] == 0) caracteres[i] = ' '; 
    	    }
    	    
    	    String msg = new String(caracteres); 
            Traza.transaccionMQ(getClass(), "Entrada - Contenido:\n"+msg);
            
            //Seguridad Informatica
            String app = message.getStringProperty("JMSXAppID");
            if (Validaciones.isNullOrEmpty(app) 
            		|| !(micControlMQ.getJmsPropiedadesContexto().getAplicacion().trim().equalsIgnoreCase(app.trim()))) 
            {
				Traza.error(getClass(), "No puede procesar este mensaje recibido ya que no cumple con los requisitos de seguridad ("+app+")...");
			} else {
				if (micRespuestaRecepcionMapeoJMS.verificarServicio(msg, false) &&
	            		micRespuestaRecepcionMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
					guardarRecepcionOKDeMic(msg, tipoProceso);
	            } else
	            if (micRespuestaADCMapeoJMS.verificarServicio(msg, false) &&
	            		micRespuestaADCMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
	            	guardarRecepcionRespuestaYResponderOkAMic(new MicRespuestaADCSalida(), msg, tipoProceso);
	            } else 
	            if (micRespuestaGeneracionCargoMapeoJMS.verificarServicio(msg, false) &&
	            		micRespuestaGeneracionCargoMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
	            	guardarRecepcionRespuestaYResponderOkAMic(new MicRespuestaGeneracionCargoSalida(), msg, tipoProceso);
	            }
	            else 
	            if (micRespuestaDescobroMapeoJMS.verificarServicio(msg, false) &&
	            		micRespuestaDescobroMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
	            {
	            	guardarRecepcionRespuestaYResponderOkAMic(new MicRespuestaDescobroSalida(), msg, tipoProceso);
	            }
	            else {
	               Traza.error(getClass(), "No puede procesar este mensaje recibido ya que el formato es invalido...");
	            }
			}
            //Fin - Seguridad Informatica

		} catch (JMSException e) {
        	Traza.error(getClass(), "Error JMS", e);
        } catch (Exception e) {
        	Traza.error(getClass(), "Otro Error", e);
        } 
	}
	
	
	/**************************************************************************************************
	 * Metodos privados para procesar sus mensajes
	 *************************************************************************************************/
	/**
	 * 
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	private void guardarRecepcionOKDeMic(String mensaje, TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		
		try {
			MicRespuestaRecepcionSalida rta = (MicRespuestaRecepcionSalida) micRespuestaRecepcionMapeoJMS.deserializar(mensaje, true, false);
			
			Long idOperacion = new Long(rta.getIdOperacionTransaccion().split("\\.")[0]);
			Integer numeroTransaccion = new Integer(rta.getIdOperacionTransaccion().split("\\.")[1]);
			
			//Traza por cada operacion
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				String strIdOperacion = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
				String archivoIdOperacion = "Id_Operacion("+strIdOperacion+")";
				Traza.cambiarNombreArchivoLog("/operaciones/" + archivoIdOperacion);
				Thread.currentThread().setName(TrazaEnum.RECEPCION_1ER_MSG.name());
			}

			Traza.transaccionMQ(getClass(), "Entrada - Contenido:\n"+mensaje);
			
			Integer idTransaccion = null;
			CobMensajeriaTransaccionDto cobroMensajeriaDto = null;
			
			if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
				if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
					Long idOperacionDescobro = descobroImputacionServicio.buscarIdDescobroPorIdOperacionCobro(idOperacion);
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacionDescobro, numeroTransaccion);
					cobroMensajeriaDto = (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscarMensajeDescobroCargoEnviadoAlMIC(idOperacionDescobro, idTransaccion);
				}
			} else {
				if (numeroTransaccion.compareTo(new Integer("0")) == 0) {
					cobroMensajeriaDto = (CobMensajeriaTransaccionDto) 
							mensajeriaTransaccionServicio.buscarMensajeDesapropiacionConfirmacionEnviadoAlMIC(idOperacion);
				} else {
					if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
						idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
					}
					cobroMensajeriaDto = (CobMensajeriaTransaccionDto) 
							mensajeriaTransaccionServicio.buscarMensajeApropiacionCargoEnviadoAlMIC(idOperacion, idTransaccion);
				}
			}
			
			if (!Validaciones.isObjectNull(cobroMensajeriaDto)
					&& !Validaciones.isObjectNull(cobroMensajeriaDto.getIdOperacion())
					&& !Validaciones.isObjectNull(numeroTransaccion)){
					
				String idOperacionTransaccion = Utilidad.rellenarCerosIzquierda(cobroMensajeriaDto.getIdOperacion().toString(), 7) + "." + Utilidad.rellenarCerosIzquierda(numeroTransaccion.toString(), 7);
				
				Traza.transaccionMQ(getClass(), "guardarRecepcionOKDeMic: Se ha recibido el mensaje (" + idOperacionTransaccion + ") de tipo: " + cobroMensajeriaDto.getServicio().getDescripcion() + "("+ cobroMensajeriaDto.getServicio().name()+")" );
				cobroMensajeriaDto.setFechaRecepcion(new Date());
				
				//Guardo la respuesta en la bd 
				cobroMensajeriaDto.setRespuestaRecibida(mensaje);
				
				//Si es OK, pongo como completado
				if (Constantes.MIC_COD_RESP_OK.equalsIgnoreCase(rta.getRetorno().getCodRetorno())) {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.COMPLETADO);
				} else {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.REC_ERROR);
				}
				
				//Pero en caso de no tener un mensaje enviado, pongo como recibido
				if (Validaciones.isNullOrEmpty(cobroMensajeriaDto.getMensajeEnviado())) {
					cobroMensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
				}
				mensajeriaTransaccionServicio.modificar(cobroMensajeriaDto);
				
				//Traceos
				Traza.auditoria(getClass(), "guardarRecepcionOKDeMic: Mensaje recibido (" + idOperacionTransaccion + ") actualizado en la bd con estado: " + cobroMensajeriaDto.getEstado());
				
			} else {
				Traza.error(getClass(), "guardarRecepcionOKDeMic: Mensaje recibido ("+rta.getIdOperacionTransaccion()+") NO Encontrada en la BD");
			}
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage(), e);
			throw new NegocioExcepcion(e);
		} finally {
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				Traza.resetearDefaultArchivoLog();
			}
			Thread.currentThread().setName(this.idThread);
		}
	}

	/**
	 * Servicio de Respuesta
	 * @param mensaje
	 * @throws NegocioExcepcion
	 */
	private void guardarRecepcionRespuestaYResponderOkAMic(JMS jmsRta, String mensaje, TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		
		
		String idOperacionTransaccion = "";
		MicRespuestaRecepcionEntrada jms = new MicRespuestaRecepcionEntrada();
		MicRespuestaADCSalida rtaADC = null;
		MicRespuestaGeneracionCargoSalida rtaCargo = null;
		MicRespuestaDescobroSalida rtaDescobro = null;
		TipoInvocacionEnum tipoInvocacion = null; 
		Long idOperacionDescobro = null;
		
		try {
			jms.setMensajeRecibido(mensaje);
			
			//Ahi uso campos generales
			if (jmsRta instanceof MicRespuestaADCSalida) {
				rtaADC = (MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaADC.getIdOperacionTransaccion();
				tipoInvocacion = rtaADC.getTipoInvocacion();
				
			} else 
			if (jmsRta instanceof MicRespuestaGeneracionCargoSalida) {
				rtaCargo = (MicRespuestaGeneracionCargoSalida) 
						micRespuestaGeneracionCargoMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaCargo.getIdOperacionTransaccion();
				tipoInvocacion = rtaCargo.getTipoInvocacion();
			} else 
			if (jmsRta instanceof MicRespuestaDescobroSalida) {
				rtaDescobro = (MicRespuestaDescobroSalida) 
						micRespuestaDescobroMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaDescobro.getIdOperacionTransaccion();
				tipoInvocacion = rtaDescobro.getTipoInvocacion();
				idOperacionDescobro = descobroImputacionServicio.buscarIdDescobroPorIdOperacionCobro(new Long(idOperacionTransaccion.split("\\.")[0]));
			}
			
			jms.setIdOperacionTransaccion(idOperacionTransaccion);
			Long idOperacion = new Long(idOperacionTransaccion.split("\\.")[0]);
			
			//Traza por cada operacion
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				String strIdOperacion = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
				String archivoIdOperacion = "Id_Operacion("+strIdOperacion+")";
				Traza.cambiarNombreArchivoLog("/operaciones/" + archivoIdOperacion);			
				Thread.currentThread().setName(TrazaEnum.RECEPCION_2DO_MSG.name());
			}
			
			Traza.transaccionMQ(getClass(), "Entrada - Contenido:\n"+mensaje);
			
			Integer numeroTransaccion = new Integer(idOperacionTransaccion.split("\\.")[1]);
			if (idOperacion.compareTo(new Long("0")) == 0) {
				idOperacion = null;
			}
			
			Integer idTransaccion = null;
			if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
				
				if (!Validaciones.isObjectNull(idOperacionDescobro)){
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacionDescobro, numeroTransaccion);
					jms.setIdOperacion(idOperacionDescobro);
					
				} else {
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
					jms.setIdOperacion(idOperacion);
				}
			}
			jms.setIdTransaccion(idTransaccion);
						
			//Si no esta entonces procesar la respuesta
			MensajeServicioEnum servicio = null;
			if (TipoInvocacionEnum.$01.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA;
			} else 
			if (TipoInvocacionEnum.$02.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_MP;	
			} else
			if (TipoInvocacionEnum.$03.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP;
			} else
			if (TipoInvocacionEnum.$04.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION;		
			} else
			if (TipoInvocacionEnum.$05.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION;
			} else 
			if (TipoInvocacionEnum.$06.equals(tipoInvocacion)) {
				if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_DEBITO;
				} else 
				if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO;
				}
				
			} else 
			if (TipoInvocacionEnum.$07.equals(tipoInvocacion)) {
				if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_CREDITO;	
				} else 
				if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO;
				}
				
			} else 
			if (TipoInvocacionEnum.$08.equals(tipoInvocacion)) {
				if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_GENERACION_CARGO_INTERES;
				} else 
				if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
					servicio = MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES;
				}
			} else
				if (TipoInvocacionEnum.$09.equals(tipoInvocacion)) {
				servicio = MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO;	
			}	
			jms.setServicio(servicio);
			
			if (idOperacion != null && servicio != null) {
				//Traceos
				Traza.transaccionMQ(getClass(), "guardarRecepcionRespuestaYResponderOkAMic: Se ha recibido el mensaje ("+idOperacionTransaccion+") de tipo: " + servicio.getDescripcion() + "("+ servicio.name()+")" );
				
				//Uso todos los campos
				if (jmsRta instanceof MicRespuestaADCSalida) {
					rtaADC = (MicRespuestaADCSalida) 
							micRespuestaADCMapeoJMS.deserializar(mensaje, true, false);
					rtaADC.setIdTransaccion(idTransaccion);
				} else 
				if (jmsRta instanceof MicRespuestaGeneracionCargoSalida) {
	    	
			    	rtaCargo = (MicRespuestaGeneracionCargoSalida) 
			    			micRespuestaGeneracionCargoMapeoJMS.deserializar(mensaje, true, false);
			    	rtaCargo.setIdTransaccion(idTransaccion);
				} else
				if (jmsRta instanceof MicRespuestaDescobroSalida) {
			    	
					rtaDescobro = (MicRespuestaDescobroSalida) 
			    			micRespuestaDescobroMapeoJMS.deserializar(mensaje, true, false);
					rtaDescobro.setIdTransaccion(idTransaccion);
				}
		    	
				Date fechaRecepcion = new Date();
				boolean buscarMensajeAnteriormenteProcesado =
						(TipoInvocacionEnum.$01.equals(tipoInvocacion)
							|| TipoInvocacionEnum.$02.equals(tipoInvocacion)
							|| TipoInvocacionEnum.$03.equals(tipoInvocacion))?true:false;
								
				
				//Se movio el metodo desde CobroBatchImputacionServicioImpl
				if (buscarMensajeAnteriormenteProcesado &&
						mensajeriaTransaccionServicio.mensajeAnteriormenteProcesado(idOperacion, idTransaccion, servicio)){
				
					Traza.advertencia(getClass(), "guardarRecepcionRespuestaYResponderOkAMic: No se procesa el mensaje de "+ servicio.getDescripcion()+ " de MIC ("+idOperacionTransaccion+"), dado que ya fue procesado anteriormente.");
				
				} else {
					
					ShvWfWorkflow workflow =null;
					Integer hiloEnCurso = 0;
					if (!Validaciones.isObjectNull(idOperacionDescobro)){
						workflow = descobroDao.buscarWorkflowPorIdOperacion(idOperacionDescobro);
					} else {
						workflow = cobroDao.buscarWorkflowPorIdOperacion(idOperacion);
						hiloEnCurso = cobroDao.obtenerEstadoDelHilo(idOperacion);
					}
					
					Traza.auditoria(this.getClass(), "El cobro se encuentra en el siguiente estado: [ " + workflow.getEstado().descripcion() + " ]. Estado del hilo: " + (hiloEnCurso > 0?"EN CURSO":"FINALIZADO"));
					if (!Validaciones.isObjectNull(workflow) 
							&& (((Estado.COB_PENDIENTE_MIC.equals(workflow.getEstado())
									|| Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())
									|| Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())) && hiloEnCurso == 0) 
							|| Estado.DES_PENDIENTE_MIC.equals(workflow.getEstado()))){
						
						// Buscar primero si está la transaccion en la base de datos
						CobMensajeriaTransaccionDto dto =null;

						if (!Validaciones.isObjectNull(idOperacionDescobro)){
							dto = (CobMensajeriaTransaccionDto) 
									mensajeriaTransaccionServicio.buscarMensajeRecibido(idOperacionDescobro, idTransaccion, servicio, mensaje);
						} else {
							dto = (CobMensajeriaTransaccionDto) 
									mensajeriaTransaccionServicio.buscarMensajeRecibido(idOperacion, idTransaccion, servicio, mensaje);
						}
						
						if (dto == null) {
							
							//No existe el mismo mensaje recibido y por lo tanto creo la mensajeria
							CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
					    	mensajeriaDto.setIdTransaccion(idTransaccion);
					    	mensajeriaDto.setIdOperacion(idOperacionDescobro != null ? idOperacionDescobro:idOperacion);
					    	mensajeriaDto.setServicio(servicio);
					    	mensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
					    	mensajeriaDto.setFechaAlta(fechaRecepcion);
					    	mensajeriaDto.setFechaRecepcion(fechaRecepcion);
					    	mensajeriaDto.setCantReintentos(new Integer(0));
					    	mensajeriaDto.setMensajeEnviado(null);
					    	mensajeriaDto.setRespuestaRecibida(mensaje);
					    	mensajeriaDto = crearMensajeria(mensajeriaDto);
					    	
					    	jms.setId(mensajeriaDto.getIdTransaccionMensajeria());	
	
					    	MicResultado resultado = new MicResultado();
							resultado.setResultadoInvocacion(TipoResultadoEnum.OK.getDescripcionMic());
							jms.setResultado(resultado);
							
							//No envio la fecha de recepcion ya que ya fue seteada 
							responderRespuestaRecepcion(jms, null);
							
							if (!Validaciones.isObjectNull(idOperacionDescobro)){
								descobroImputacionServicio.pasarWorkflowPendienteMICAPendienteProcesarMIC(idOperacionDescobro, tipoInvocacion, idOperacionTransaccion, usuarioBatchCobrosRecepcion);
								
							} else {
								cobroBatchSoporteImputacionMicServicio.pasarWorkflowPendienteMICAPendienteProcesarMic(idOperacion, tipoInvocacion, idOperacionTransaccion, usuarioBatchCobrosRecepcion);
								Traza.advertencia(getClass(), "("+idOperacionTransaccion+") El cobro se pasa al estado pendiente de procesar MIC");
							}
							
							responderRespuestaRecepcion(jms, null);
													
						} else {
							jms.setId(dto.getIdTransaccionMensajeria());
							
							//Si ya tengo la respuesta para el mismo mensaje recibido, simplemente lo reenvio
							if (!Validaciones.isNullOrEmpty(dto.getMensajeEnviado()) 
									&& (MensajeEstadoEnum.COMPLETADO.equals(dto.getEstado()) 
													|| MensajeEstadoEnum.CANCELADO.equals(dto.getEstado()))) {
								
								dto.setFechaRecepcion(fechaRecepcion);
								reintentosMensajeria.reenviarMensaje(dto, false);
								Traza.auditoria(getClass(), "guardarRecepcionRespuestaYResponderOkAMic: Se ha reenviado  el mensaje ("+idOperacionTransaccion+")");
								
							} else {
								//Le reenvio con un OK si no la tengo en la bd
								MicResultado resultado = new MicResultado();
								resultado.setResultadoInvocacion(TipoResultadoEnum.OK.getDescripcionMic());
								jms.setResultado(resultado);
								
								responderRespuestaRecepcion(jms, fechaRecepcion);							
							}
						}
					} else {
						Traza.error(getClass(), "Mensaje recibido se descarta por cobro/descobro en estado incorrecto o porque el hilo de imputacion se encuentra en curso. Id Operacion(" + idOperacionTransaccion + ") Estado:"+workflow.getEstado().descripcion());
					}
				}	
				
	    	} else {
				Traza.error(getClass(), "guardarRecepcionRespuestaYResponderOkAMic: Mensaje recibido (" + idOperacionTransaccion + ") con operacion o servicio desconocido");
				
				//Segun la gente de MIC, dicen que no lo necesitan esta respuesta con error y no le envio
				//MicResultado resultado = new MicResultado();
	    		//resultado.setResultadoInvocacion(TipoResultadoEnum.NOK);
	    		//jms.setResultado(resultado);
	    		
	    		//responderRespuestaRecepcion(jms);
			}
		} catch (Exception e) {
			Traza.error(getClass(), "guardarRecepcionRespuestaYResponderOkAMic: Mensaje recibido (" + idOperacionTransaccion + ") con error de aplicacion SHIVA al procesar el mensaje", e);
			
			//Segun la gente de MIC, dicen que no lo necesitan esta respuesta con error y no le envio
			//MicResultado resultado = new MicResultado();
    		//resultado.setResultadoInvocacion(TipoResultadoEnum.NOK);
    		//jms.setResultado(resultado);
    		
			//responderRespuestaRecepcion(jms);
		} finally {
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				Traza.resetearDefaultArchivoLog();
			}
			Thread.currentThread().setName(this.idThread);
		}
	}

	/**
	 * Respuesta a MIC (Servicio de Respuesta)
	 * @param jms
	 * @throws NegocioExcepcion
	 */
	private void responderRespuestaRecepcion(MicRespuestaRecepcionEntrada jms, Date fechaRecepcion) throws NegocioExcepcion {
		
		try {
			String nombreMetodo = new Object(){}.getClass().getEnclosingMethod().getName();
			if (jms != null 
					&& !Validaciones.isNullOrEmpty(jms.getIdOperacionTransaccion())
					&& jms.getResultado() != null) {
				
				if (jms.getId() != null) {
					CobMensajeriaTransaccionDto dto 
						= (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.buscar(jms.getId());
					
					if (dto != null) {
						if (fechaRecepcion != null) {
							dto.setFechaRecepcion(fechaRecepcion);
						}
						enviarRespuestaOKRecepcion(jms, dto);
						
						Traza.auditoria(getClass(), nombreMetodo + ": Mensaje de respuesta enviada ("+jms.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + dto.getEstado());
					} else {
						Traza.error(getClass(), nombreMetodo + ": Mensaje ("+ jms.getIdOperacionTransaccion() +") NO Encontrada en la BD");
					}
					
				} else {
					if (!Validaciones.isNullOrEmpty(jms.getMensajeRecibido()) 
							&& jms.getIdOperacion() != null) {
						
						// En casos de errores de validacion
						CobMensajeriaTransaccionDto mensajeriaDto = new CobMensajeriaTransaccionDto();
						mensajeriaDto.setIdOperacion(jms.getIdOperacion());
				    	mensajeriaDto.setIdTransaccion(jms.getIdTransaccion());
				    	mensajeriaDto.setServicio(jms.getServicio());
				    	mensajeriaDto.setEstado(MensajeEstadoEnum.RECIBIDO);
				    	mensajeriaDto.setFechaAlta(fechaRecepcion);
				    	mensajeriaDto.setFechaRecepcion(fechaRecepcion);
				    	mensajeriaDto.setCantReintentos(new Integer(0));
				    	mensajeriaDto.setMensajeEnviado(null);
				    	mensajeriaDto.setRespuestaRecibida(jms.getMensajeRecibido());
				    	mensajeriaDto = crearMensajeria(mensajeriaDto);
				    	
				    	jms.setId(mensajeriaDto.getIdTransaccionMensajeria());
				    	
				    	enviarRespuestaOKRecepcion(jms, mensajeriaDto);
				    	
				    	Traza.auditoria(getClass(), nombreMetodo + " (2): Mensaje de respuesta enviada ("+jms.getIdOperacionTransaccion()+") actualizado en la bd con estado: " + mensajeriaDto.getEstado());
				    } else {
				    	Traza.error(getClass(), nombreMetodo + " (" + jms.getIdOperacionTransaccion() + "): Por no tener datos suficientes, no puede enviar la respuesta al mic, ni actualizar en la BD");
				    }
				}
			} else {
				Traza.error(getClass(), nombreMetodo + ": Por no tener datos suficientes, no puede enviar la respuesta al mic, ni actualizar en la BD");
			}
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * Ejecuto el envio de la info y guardo en la BD
	 * @param jms
	 * @param dto
	 * @throws NegocioExcepcion
	 */
	private void enviarRespuestaOKRecepcion(JMS jms, CobMensajeriaTransaccionDto dto) throws NegocioExcepcion {
		// Serializar el objeto a mensaje
		String mensajeAEnviar = micRespuestaRecepcionMapeoJMS.serializar(jms, false);
				
		//Envio la respuesta por primera vez
		reintentosMensajeria.enviarRespuesta(dto, mensajeAEnviar);
	}
	
	/**
	 * Guarda el mensaje como transaccion
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	private CobMensajeriaTransaccionDto crearMensajeria(CobMensajeriaTransaccionDto dto) 
			throws NegocioExcepcion {
		
		return (CobMensajeriaTransaccionDto) mensajeriaTransaccionServicio.crearTransaccionPropia(dto);
	}
}
