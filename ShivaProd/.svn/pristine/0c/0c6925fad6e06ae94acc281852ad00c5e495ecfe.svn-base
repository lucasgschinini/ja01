package ar.com.telecom.shiva.negocio.executor.runnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TrazaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroCargoGenerado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroFactura;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroMedioPago;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleDescobroOperacionPosteriorRelacionada;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleInteresesGenerados;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleDescobroCargoGeneradoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleDescobroFacturaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleDescobroMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleDescobroOperacionPosteriorRelacionadaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicResultadoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroRespuestaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosRespuestaDto;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionRespuestaRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionMicServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionCobroServicio;

public class ImputacionRespuestaRunnable implements Runnable {
	
	private String idThread;
	private ImputacionRespuestaRto datosEntrada;
	private String usuarioBatch = "";
	
	private IParametroServicio parametroServicio;
	private ICobroBatchSoporteImputacionMicServicio cobroBatchSoporteImputacionMicServicio;
	private IDescobroImputacionServicio descobroImputacionServicio;
	
	private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	private ITransaccionCobroServicio transaccionCobroServicio;
	
	private MapeadorJMS micRespuestaADCMapeoJMS;
	private MapeadorJMS micRespuestaGeneracionCargoMapeoJMS;
	private MapeadorJMS micRespuestaDescobroMapeoJMS;
	
	public ImputacionRespuestaRunnable(ImputacionRespuestaRto datosEntrada) {
		this.datosEntrada = datosEntrada;
	}
	
	/**
	 * 
	 * @throws NegocioExcepcion 
	 */
	private void inicializarServicios() throws NegocioExcepcion {
		parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
		this.usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH); 
		descobroImputacionServicio = (IDescobroImputacionServicio) Configuracion.getBeanBatch("descobroImputacionServicio");
		
		mensajeriaTransaccionServicio = (IMensajeriaTransaccionServicio) Configuracion.getBeanBatch("mensajeriaTransaccionServicio");
		transaccionCobroServicio = (ITransaccionCobroServicio) Configuracion.getBeanBatch("transaccionCobroServicio");
		
		micRespuestaADCMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaADCMapeoJMS");
		micRespuestaGeneracionCargoMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaGeneracionCargoMapeoJMS");
		micRespuestaDescobroMapeoJMS = (MapeadorJMS) Configuracion.getBeanBatch("micRespuestaDescobroMapeoJMS");
		
		cobroBatchSoporteImputacionMicServicio =(ICobroBatchSoporteImputacionMicServicio) Configuracion.getBeanBatch("cobroSoporteMicServicio");
	}
	
	/**
	 * 
	 * @throws NegocioExcepcion 
	 */
	private void destruirServicios() {
		parametroServicio = null;
		cobroBatchSoporteImputacionMicServicio = null;
		descobroImputacionServicio = null;
		
		mensajeriaTransaccionServicio = null;
		transaccionCobroServicio = null;
		
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
			this.inicializarServicios();
			this.idThread = "Mensaje recibido nro: " + this.datosEntrada.getCount();
			Thread.currentThread().setName(this.idThread);
			
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo: {0}.", this.idThread));
			
			this.procesarMensajeRecibido(
					datosEntrada.getCount(), datosEntrada.getIdOperacion(), datosEntrada.getIdOperacionDescobro(), datosEntrada.getTipoProceso());
			
		} catch (Throwable e) {
			Traza.error(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha producido un error al procesar ({0})", this.idThread));
			Traza.error(this.getClass(),e.getMessage(), e);
			Traza.auditoria(this.getClass(), "-------------------------------------------------------------------------------------");
			
		} finally {
			this.destruirServicios();
			
			Traza.auditoria(this.getClass(),  
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo: {0}.", this.idThread));
			Traza.infoMemoriaCPU(this.getClass(), "run", ControlMemoriaCPU.getInformacionMemoria());
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			Traza.tiempo(this.getClass(), usuarioBatch, "run", detalle);
			
			Thread.currentThread().setName(orgName);
		}
	}
	
	/**
	 * Servicio que procesa el mensaje recibido
	 * @param message
	 * @throws NegocioExcepcion 
	 */
	private void procesarMensajeRecibido(int contador, Long idOperacion, Long idOperacionDescobro, TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
	
		String msg = "";
		MensajeServicioEnum servicio = null;
		
		try {
			//Traza por cada operacion
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				String strIdOperacion = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
				String archivoIdOperacion = "Id_Operacion("+strIdOperacion+")";
				Traza.cambiarNombreArchivoLog("/operaciones/" + archivoIdOperacion);
				Thread.currentThread().setName(TrazaEnum.IMPUTACION_RESPUESTA.name());
			}

			// Buscar primero si está la transaccion en la base de datos
			CobMensajeriaTransaccionDto dto =null;
			if (!Validaciones.isObjectNull(idOperacionDescobro)){
				dto = (CobMensajeriaTransaccionDto) 
						mensajeriaTransaccionServicio.buscarUltimoMensajeRespuesta(idOperacionDescobro);
			} else {
				dto = (CobMensajeriaTransaccionDto) 
						mensajeriaTransaccionServicio.buscarUltimoMensajeRespuesta(idOperacion);
			}
			
			if (dto != null) {
				msg = dto.getRespuestaRecibida();
				servicio = dto.getServicio();
				
				if ((!Validaciones.isEmptyString(msg)) && (servicio != null)) {
					
					Traza.transaccionMQ(getClass(), servicio.name() + "- Mensaje a procesar: " + msg.toString());
					
					//Verificar mensaje
					if (micRespuestaADCMapeoJMS.verificarServicio(msg, false) &&
							micRespuestaADCMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
					{
						procesarRespuesta(new MicRespuestaADCSalida(), msg, tipoProceso, servicio);
					} else 
					if (micRespuestaGeneracionCargoMapeoJMS.verificarServicio(msg, false) &&
							micRespuestaGeneracionCargoMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
					{
						procesarRespuesta(new MicRespuestaGeneracionCargoSalida(), msg, tipoProceso, servicio);
					}
					else 
					if (micRespuestaDescobroMapeoJMS.verificarServicio(msg, false) &&
							micRespuestaDescobroMapeoJMS.verificarLongitudMsjRecibida(msg, false)) 
					{
						procesarRespuesta(new MicRespuestaDescobroSalida(), msg, tipoProceso, servicio);
					}
					else 
					{
						Traza.error(getClass(), "No puede procesar este mensaje recibido ya que el formato es invalido...");
					}
				} else {
					Traza.error(getClass(), "Este mensaje o el servicio se encuentra vacio");
				}
			} else {
				Traza.error(getClass(), "Mensaje de Respuesta en espera de recepcion");
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
	private void procesarRespuesta(JMS jmsRta, String mensaje, TipoProcesoEnum tipoProceso, MensajeServicioEnum servicio) throws NegocioExcepcion {
		
		String idOperacionTransaccion = "";
		MicRespuestaADCSalida rtaADC = null;
		MicRespuestaGeneracionCargoSalida rtaCargo = null;
		MicRespuestaDescobroSalida rtaDescobro = null;
		Long idOperacionDescobro = null;
		
		try {
			//Ahi uso campos generales
			if (jmsRta instanceof MicRespuestaADCSalida) {
				rtaADC = (MicRespuestaADCSalida) micRespuestaADCMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaADC.getIdOperacionTransaccion();
				
			} else 
			if (jmsRta instanceof MicRespuestaGeneracionCargoSalida) {
				rtaCargo = (MicRespuestaGeneracionCargoSalida) 
						micRespuestaGeneracionCargoMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaCargo.getIdOperacionTransaccion();
			} else 
			if (jmsRta instanceof MicRespuestaDescobroSalida) {
				rtaDescobro = (MicRespuestaDescobroSalida) 
						micRespuestaDescobroMapeoJMS.deserializar(mensaje, false, false);
				
				idOperacionTransaccion = rtaDescobro.getIdOperacionTransaccion();
				idOperacionDescobro = descobroImputacionServicio.buscarIdDescobroPorIdOperacionCobro(new Long(idOperacionTransaccion.split("\\.")[0]));
			}
			
			Traza.transaccionMQ(getClass(), "procesarMensajeRecibido: "
					+ "Se ha recibido el mensaje ("+idOperacionTransaccion+") de tipo: " + servicio.getDescripcion() + "("+ servicio.name()+")" );
			
			Long idOperacion = new Long(idOperacionTransaccion.split("\\.")[0]);
			Integer numeroTransaccion = new Integer(idOperacionTransaccion.split("\\.")[1]);
			if (idOperacion.compareTo(new Long("0")) == 0) {
				idOperacion = null;
			}
			
			Integer idTransaccion = null;
			if (numeroTransaccion.compareTo(new Integer("0")) != 0) {
				
				if (!Validaciones.isObjectNull(idOperacionDescobro)){
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacionDescobro, numeroTransaccion);
				} else {
					idTransaccion = transaccionCobroServicio.buscarIdTransaccion(idOperacion, numeroTransaccion);
				}
			}
						
			if (idOperacion != null && servicio != null) {
				
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
		    	
				//Proceso el mensaje
	    		try {
			    	if (jmsRta instanceof MicRespuestaADCSalida) {
			    		procesarRespuestaOperacionADC(rtaADC);
					} else 
					if (jmsRta instanceof MicRespuestaGeneracionCargoSalida) {
						procesarRespuestaOperacionCargo(rtaCargo, tipoProceso);
					} else 
					if (jmsRta instanceof MicRespuestaDescobroSalida) {
						procesarRespuestaDescobro(rtaDescobro);
					}
	    		} catch (Exception e) {
	    			Traza.error(getClass(), "Se ha ocurrido un error al procesar el mensaje recibido (" + idOperacionTransaccion + ")", e);
	    		}
		    	
	    	} else {
				Traza.error(getClass(), "Mensaje recibido (" + idOperacionTransaccion + ") con operacion o servicio desconocido");
			}
		} catch (Exception e) {
			Traza.error(getClass(), "Mensaje recibido (" + idOperacionTransaccion + ") con error de aplicacion SHIVA al procesar el mensaje", e);
		}
	}

	/**
	 * Procesa los mensajes de respuesta de ADC
	 * @param rta
	 * @throws Exception
	 */
	private void procesarRespuestaOperacionADC(MicRespuestaADCSalida rta) throws ShivaExcepcion {
		try {
			MicTransaccionADCRespuestaDto dto = new MicTransaccionADCRespuestaDto();
			dto.setIdOperacionTransaccion(rta.getIdOperacionTransaccion());
			dto.setIdTransaccion(rta.getIdTransaccion());
			dto.setTipoInvocacion(rta.getTipoInvocacion());
			String resultadoInvocacion = !Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
					rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
			dto.setResultadoInvocacion(resultadoInvocacion);
			dto.setCodigoError(rta.getResultadoLLamadaServicio().getCodigoError());
			dto.setDescripcionError(rta.getResultadoLLamadaServicio().getDescripcionError());
			
			if (TipoInvocacionEnum.$01.equals(rta.getTipoInvocacion())) {
				
				dto.setIdCobranza(rta.getIdCobranza());
				
				//Detalle de cobro de factura
				dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
				dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
				dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
				
				//Nuevo Sprint 5
				dto.setDetalleCobroFactura(rta.getDetalleCobroFactura());
				
				cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeuda(dto);
				
			} else 
			if (TipoInvocacionEnum.$02.equals(rta.getTipoInvocacion())) {
				
				dto.setIdCobranza(rta.getIdCobranza());
				//Nuevo Sprint 5
				dto.setListaDetalleMedioPago(rta.getListaDetalleMedioPago());
				
				cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionMedioPago(dto);
				
			} else
			if (TipoInvocacionEnum.$03.equals(rta.getTipoInvocacion())) {
				
				dto.setIdCobranza(rta.getIdCobranza());
				
				//Detalle de cobro de factura
				dto.setInteresesGenerados(rta.getDetalleCobroFactura().getInteresesGenerados());
				dto.setInteresesBonificadosRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosRegulados());
				dto.setInteresesBonificadosNoRegulados(rta.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
				
				//Nuevo Sprint 5
				dto.setDetalleCobroFactura(rta.getDetalleCobroFactura());
				dto.setListaDetalleMedioPago(rta.getListaDetalleMedioPago());
				
				cobroBatchSoporteImputacionMicServicio.micRecepcionApropiacionDeudaMedioPago(dto);
				
			} else
			if (TipoInvocacionEnum.$04.equals(rta.getTipoInvocacion())) {
				
				cobroBatchSoporteImputacionMicServicio.micRecepcionDesapropiacion(dto);
				
			} else
			if (TipoInvocacionEnum.$05.equals(rta.getTipoInvocacion())) {
				
				cobroBatchSoporteImputacionMicServicio.micRecepcionConfirmacion(dto);
				
			}
		} catch (Exception e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Procesa los mensajes de respuesta de cargos
	 * @param rta
	 * @throws Exception
	 */
	private void procesarRespuestaOperacionCargo(MicRespuestaGeneracionCargoSalida rta, 
			TipoProcesoEnum tipoProceso) throws ShivaExcepcion {
		
		try {
			MicTransaccionGeneracionCargosRespuestaDto dto = new MicTransaccionGeneracionCargosRespuestaDto();
			dto.setIdOperacionTransaccion(rta.getIdOperacionTransaccion());
			dto.setIdTransaccion(rta.getIdTransaccion());
			dto.setTipoInvocacion(rta.getTipoInvocacion());
			
			String resultadoInvocacion = 
					!Validaciones.isNullOrEmpty(rta.getResultadoLLamadaServicio().getResultadoInvocacion())?
							rta.getResultadoLLamadaServicio().getResultadoInvocacion():null;
			dto.setResultadoInvocacion(resultadoInvocacion);
			dto.setCodigoError(rta.getResultadoLLamadaServicio().getCodigoError());
			dto.setDescripcionError(rta.getResultadoLLamadaServicio().getDescripcionError());
			
			MicDetalleInteresesGenerados detalle = rta.getDetalleInteresesGenerados();
			if(!Validaciones.isObjectNull(detalle)){
				dto.setInteresesBonificadosNoRegulados(detalle.getInteresesBonificadosNoRegulados());
				dto.setInteresesBonificadosRegulados(detalle.getInteresesBonificadosRegulados());
				dto.setInteresesGeneradosNoRegulados(detalle.getInteresesGeneradosNoRegulados());
				dto.setInteresesGeneradosRegulados(detalle.getInteresesGeneradosRegulados());
			}
			
			if (TipoProcesoEnum.COBRO.equals(tipoProceso)) {
				cobroBatchSoporteImputacionMicServicio.micRecepcionCargo(dto);
			} else 
			if (TipoProcesoEnum.DESCOBRO.equals(tipoProceso)) {
				descobroImputacionServicio.micRecepcionContraCargo(dto);
			} else {
				Traza.error(getClass(), "No puede procesar un cargo cuando el tipo de proceso sea distinto a Cobro o Descobro: " + tipoProceso);
			}
		} catch (Exception e) {
			throw new ShivaExcepcion(e);
		}
	}
	
	/**
	 * Procesa los mensajes de respuesta de reversion de operaciones
	 * @param rta
	 * @throws Exception
	 */
	private void procesarRespuestaDescobro(MicRespuestaDescobroSalida rta) throws ShivaExcepcion {
		try {
			MicTransaccionDescobroRespuestaDto dto = new MicTransaccionDescobroRespuestaDto();
			dto.setIdOperacionTransaccion(rta.getIdOperacionTransaccion());
			dto.setIdTransaccion(rta.getIdTransaccion());
			dto.setTipoInvocacion(rta.getTipoInvocacion());
			
			if (!Validaciones.isObjectNull(rta.getResultadoLlamadaServicio())){
				MicResultadoDto micResultadoDto = new MicResultadoDto();
				micResultadoDto.setResultadoInvocacion(rta.getResultadoLlamadaServicio().getResultadoInvocacion());
				micResultadoDto.setCodigoError(rta.getResultadoLlamadaServicio().getCodigoError());
				micResultadoDto.setDescripcionError(rta.getResultadoLlamadaServicio().getDescripcionError());
				dto.setResultadoLLamadaServicio(micResultadoDto);
			}
			
			MicDetalleDescobroFactura detalleFactura = rta.getDetalleFactura();
			if(!Validaciones.isObjectNull(detalleFactura)){
				MicDetalleDescobroFacturaDto detalleFacturaDto = new MicDetalleDescobroFacturaDto();
				
				MicResultado resultadoDescobroFactura = detalleFactura.getResultadoDescobroFactura();
				if(!Validaciones.isObjectNull(resultadoDescobroFactura)){
						MicResultadoDto resultadoDto = new MicResultadoDto();
						resultadoDto.setResultadoInvocacion(resultadoDescobroFactura.getResultadoInvocacion());
						resultadoDto.setCodigoError(resultadoDescobroFactura.getCodigoError());
						resultadoDto.setDescripcionError(resultadoDescobroFactura.getDescripcionError());
					detalleFacturaDto.setResultadoDescobroFactura(resultadoDto);
				}
				
				detalleFacturaDto.setCodigoEstadoCargoGenerado(detalleFactura.getCodigoEstadoCargoGenerado());
				detalleFacturaDto.setDetalleEstadoCargoGenerado(detalleFactura.getDetalleEstadoCargoGenerado());
				detalleFacturaDto.setCodigoEstadoAcuerdoFacturacion(detalleFactura.getCodigoEstadoAcuerdoFacturacion());
				detalleFacturaDto.setDescripcionEstadoAcuerdoFacturacion(detalleFactura.getDescripcionEstadoAcuerdoFacturacion());
				detalleFacturaDto.setInteresesRealesNoReguladosTrasladados(detalleFactura.getInteresesRealesNoReguladosTrasladados());
				detalleFacturaDto.setInteresesRealesReguladosTrasladados(detalleFactura.getInteresesRealesReguladosTrasladados());
				
				dto.setDetalleFactura(detalleFacturaDto);
			}
			
			List<MicDetalleDescobroMedioPago> listaDetalleMedioPago = rta.getListaDetalleMedioPago();
			if(Validaciones.isCollectionNotEmpty(listaDetalleMedioPago)){
						
				List<MicDetalleDescobroMedioPagoDto> listaDetalleMedioPagoDto = new ArrayList<MicDetalleDescobroMedioPagoDto>();
				for(MicDetalleDescobroMedioPago medioPago : listaDetalleMedioPago){
					MicDetalleDescobroMedioPagoDto medioPagoDto = new MicDetalleDescobroMedioPagoDto();
					medioPagoDto.setTipoMedioPago(medioPago.getTipoMedioPago());
					medioPagoDto.setCuentaRemanente(medioPago.getCuentaRemanente());
					medioPagoDto.setTipoRemanente(medioPago.getTipoRemanente());
					medioPagoDto.setNumeroReferenciaMic(medioPago.getNumeroReferenciaMic());
					
					MicResultado resultadoDescobroMedioPago = medioPago.getResultadoDescobroMedioPago();
					if(!Validaciones.isObjectNull(resultadoDescobroMedioPago)){
						MicResultadoDto resultadoDescobroMedioPagoDto = new MicResultadoDto();
						resultadoDescobroMedioPagoDto.setResultadoInvocacion(resultadoDescobroMedioPago.getResultadoInvocacion());
						resultadoDescobroMedioPagoDto.setCodigoError(resultadoDescobroMedioPago.getCodigoError());
						resultadoDescobroMedioPagoDto.setDescripcionError(resultadoDescobroMedioPago.getDescripcionError());
						medioPagoDto.setResultadoDescobroMedioPago(resultadoDescobroMedioPagoDto);
					}
					listaDetalleMedioPagoDto.add(medioPagoDto);
				}
				
				dto.setListaDetalleMedioPago(listaDetalleMedioPagoDto);
			}
			
			
			MicDetalleDescobroCargoGenerado detalleCargoGenerado = rta.getDetalleCargoGenerado();
			if(!Validaciones.isObjectNull(detalleCargoGenerado)){
				MicDetalleDescobroCargoGeneradoDto detalleCargoGeneradoDto = new MicDetalleDescobroCargoGeneradoDto();
				
				detalleCargoGeneradoDto.setCodigoEstadoCargoGenerado(detalleCargoGenerado.getCodigoEstadoCargoGenerado());
				detalleCargoGeneradoDto.setDetalleEstadoCargoGenerado(detalleCargoGenerado.getDetalleEstadoCargoGenerado());
				detalleCargoGeneradoDto.setCodigoEstadoAcuerdoFacturacion(detalleCargoGenerado.getCodigoEstadoAcuerdoFacturacion());
				detalleCargoGeneradoDto.setDescripcionEstadoAcuerdoFacturacion(detalleCargoGenerado.getDescripcionEstadoAcuerdoFacturacion());
				detalleCargoGeneradoDto.setInteresesRealesNoReguladosTrasladados(detalleCargoGenerado.getInteresesRealesNoReguladosTrasladados());
				detalleCargoGeneradoDto.setInteresesRealesReguladosTrasladados(detalleCargoGenerado.getInteresesRealesNoReguladosTrasladados());
				
				MicResultado listaResultadoDescobroCargoGenerado = detalleCargoGenerado.getResultadoDescobroCargoGenerado();
				MicResultadoDto resultadoDto = new MicResultadoDto();
				resultadoDto.setResultadoInvocacion(listaResultadoDescobroCargoGenerado.getResultadoInvocacion());
				resultadoDto.setCodigoError(listaResultadoDescobroCargoGenerado.getCodigoError());
				resultadoDto.setDescripcionError(listaResultadoDescobroCargoGenerado.getDescripcionError());
				
				detalleCargoGeneradoDto.setResultadoDescobroCargoGenerado(resultadoDto);
				
				dto.setDetalleCargoGenerado(detalleCargoGeneradoDto);
			}
			
			List<MicDetalleDescobroOperacionPosteriorRelacionada> listaDetalleOperacionesPosterioresRelacionadas = rta.getListaDetalleOperacionesPosterioresRelacionadas();
			if(Validaciones.isCollectionNotEmpty(listaDetalleOperacionesPosterioresRelacionadas)){
						
				List<MicDetalleDescobroOperacionPosteriorRelacionadaDto> listaDetalleOperacionesPosterioresRelacionadasDto = new ArrayList<MicDetalleDescobroOperacionPosteriorRelacionadaDto>();
				for(MicDetalleDescobroOperacionPosteriorRelacionada operacion : listaDetalleOperacionesPosterioresRelacionadas){
					MicDetalleDescobroOperacionPosteriorRelacionadaDto operacionDto = new MicDetalleDescobroOperacionPosteriorRelacionadaDto();
					operacionDto.setSistema(operacion.getSistema());
					operacionDto.setIdOperacion(operacion.getIdOperacion());
					operacionDto.setIdSecuencia(operacion.getIdSecuencia());
					operacionDto.setTipoDocumento(operacion.getTipoDocumento());
					operacionDto.setNumeroReferenciaMic(operacion.getNumeroReferenciaMic());
					operacionDto.setCodigoCliente(operacion.getCodigoCliente());
					operacionDto.setImporteCobrado(operacion.getImporteCobrado());
					operacionDto.setFechaCobranza(operacion.getFechaCobranza());
						
					listaDetalleOperacionesPosterioresRelacionadasDto.add(operacionDto);
				}
				
				dto.setListaDetalleOperacionesPosterioresRelacionadas(listaDetalleOperacionesPosterioresRelacionadasDto);
			}
			
			descobroImputacionServicio.micRecepcionDescobro(dto);
		} catch (Exception e) {
			throw new ShivaExcepcion(e);
		}
	}
}
