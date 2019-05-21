package ar.com.telecom.shiva.negocio.executor.callable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPagoRespuestaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicResultado;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionFacturaMic;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoMic;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionMicRto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;

public class ApropiacionMicCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private ApropiacionMicRto datosEntrada;
	
	public ApropiacionMicCallable(String idThread, ApropiacionMicRto datosEntrada) {
		if (idThread == null) {
            throw new NullPointerException("idThread cannot be null");
        }
		this.idThread = idThread;
		this.datosEntrada = datosEntrada;
	}
	
	public List<ResultadoSimulacion> call() throws Exception {
		final String orgName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.idThread);
        Traza.auditoria(this.getClass(), 
				Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo {0}.", this.idThread));
        
        MicTransaccionADCDto entradaMicApropiacionJms = datosEntrada.getEntradaMicApropiacionJms();
        List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		try {
			IMicJmsSyncServicio micJmsSyncServicio = (IMicJmsSyncServicio) 
					Configuracion.ctx.getBean("micJmsSyncServicio");
			
			MicRespuestaADCSalida salida = null;
			if (TipoInvocacionEnum.$03.equals(entradaMicApropiacionJms.getTipoInvocacion())) {
				
				try {
					salida = micJmsSyncServicio.simularApropiarDeudaYMedioPago(entradaMicApropiacionJms);
				} catch (JmsExcepcion e) {
					
					this.generarResultadoSimulacionConExcepcionParaApropiacion(
							listaResultadoSimulacion, datosEntrada.getFactura(), 
							datosEntrada.getListaMediosPagoAEnviar(), e);				
				}

			} else 
			if (TipoInvocacionEnum.$01.equals(entradaMicApropiacionJms.getTipoInvocacion())) { 
				
				try {
					salida = micJmsSyncServicio.simularApropiarDeuda(entradaMicApropiacionJms);
				} catch (JmsExcepcion e) {
					
					this.generarResultadoSimulacionConExcepcionParaApropiacion(
							listaResultadoSimulacion, datosEntrada.getFactura(), e);
				}
				
			} else {
				
				try {
					salida = micJmsSyncServicio.simularApropiarMedioPago(entradaMicApropiacionJms);
					
				} catch (JmsExcepcion e) {
					
					this.generarResultadoSimulacionConExcepcionParaApropiacion(
							listaResultadoSimulacion, datosEntrada.getListaMediosPagoAEnviar(), e);				
				}
			}
			
			// Resultados posibles: OK / ER / WR
			if (!Validaciones.isObjectNull(salida)) {
				
				//
				// Si envié factura a apropiar
				//
				if (!Validaciones.isObjectNull(datosEntrada.getFactura())) {
					// Reviso los errores de la apropiacion de la factura
					if (!Validaciones.isObjectNull(salida.getDetalleCobroFactura())) {
						if (!Validaciones.isObjectNull(salida.getDetalleCobroFactura().getResultadoApropiacion())) {
							
							ResultadoSimulacionApropiacionFacturaMic resultadoSimulacion = new ResultadoSimulacionApropiacionFacturaMic();
							
							// Datos de respuesta
							resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
							resultadoSimulacion.setInteresesTrasladados(salida.getDetalleCobroFactura().getInteresesGenerados());
							
							if (!Validaciones.isObjectNull(salida.getDetalleCobroFactura().getInteresesBonificadosRegulados())) {
								resultadoSimulacion.setInteresesBonificados(salida.getDetalleCobroFactura().getInteresesBonificadosRegulados());
							} else {
								resultadoSimulacion.setInteresesBonificados(salida.getDetalleCobroFactura().getInteresesBonificadosNoRegulados());
							}

							for (MicResultado micResultado : salida.getDetalleCobroFactura().getResultadoApropiacion()) {
								
								// Debo recorrer las 2 iteraciones posibles, en busca de respuestas completas
								if (!Validaciones.isNullOrEmpty(micResultado.getResultadoInvocacion())) {
									
									// Verifico en la respuesta si el documento se encuentra migrado a Deimos
									if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()))
										&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(micResultado.getCodigoError())) {
									
										// Seteo en la respuesta que el documentos se encuentra migrado a Deimos
										resultadoSimulacion.setMigradoDeimos(SiNoEnum.SI);
									}
									
									if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()))
											&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(micResultado.getCodigoError())
											&& (Validaciones.isObjectNull(resultadoSimulacion.getInteresesTrasladados()) 
													|| (!Validaciones.isObjectNull(resultadoSimulacion.getInteresesTrasladados()) && BigDecimal.ZERO.compareTo(resultadoSimulacion.getInteresesTrasladados()) < 0))) {
									
										// Seteo en la respuesta la necesidad de actualizar el acuerdo
										resultadoSimulacion.setRequiereBuscarAcuerdoActivo(true);
									}
								
									if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()))
											&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(micResultado.getCodigoError())
											&& (Validaciones.isObjectNull(resultadoSimulacion.getInteresesTrasladados()) 
													|| (!Validaciones.isObjectNull(resultadoSimulacion.getInteresesTrasladados()) && BigDecimal.ZERO.compareTo(resultadoSimulacion.getInteresesTrasladados()) == 0))) {
									
										// Tracear la respuesta y nada mas.
										// Si es un simulacion, y no hay intereses generados, y hay un warning de estado de acuerdo no gestionable, no lo 
										// agrego a la lista de resultados, ya que no es necesario mostrar dicho error en pantalla
										
									} else {
										// Resultados posibles: OK / NOK / ERR
										
										RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
															TipoResultadoSimulacionEnum.getEnumByCodigoMic(micResultado.getResultadoInvocacion()),
															micResultado.getCodigoError(),
															micResultado.getDescripcionError());
															
										resultadoSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
									}
									
								}
							}
							
							listaResultadoSimulacion.add(resultadoSimulacion);
						}
					}			
				}
				
				//
				// Si envié medios de pago a apropiar
				//
				if (Validaciones.isCollectionNotEmpty(datosEntrada.getListaMediosPagoAEnviar())) {
					// Reviso los errores de la apropiacion de los medios de pago
					if (!Validaciones.isObjectNull(salida.getListaDetalleMedioPago())) {
						
						for (MicDetalleMedioPagoRespuestaEntrada micDetalleMedioPagoRespuestaEntrada : salida.getListaDetalleMedioPago()) {
							
							// Debo recorrer las 50 iteraciones posibles, en busca de respuestas completas
							if (!Validaciones.isNullOrEmpty(micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getResultadoInvocacion())) {

								ResultadoSimulacionApropiacionMedioPagoMic resultadoSimulacion = new ResultadoSimulacionApropiacionMedioPagoMic();
								
								resultadoSimulacion.setIdOperacionShiva(salida.getIdOperacionTransaccion());
								
								// Datos de respuesta
								resultadoSimulacion.setTipoMedioPago(micDetalleMedioPagoRespuestaEntrada.getTipoMedioPago());
								resultadoSimulacion.setTipoRemanente(micDetalleMedioPagoRespuestaEntrada.getTipoRemanente());
								resultadoSimulacion.setCuentaRemanente(micDetalleMedioPagoRespuestaEntrada.getCuentaRemanente());
								resultadoSimulacion.setNumeroNC(micDetalleMedioPagoRespuestaEntrada.getNumeroNC());
								
								// Verifico en la respuesta si el documento se encuentra migrado a Deimos
								if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoMic(micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getResultadoInvocacion()))
										&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getCodigoError())) {
									
									resultadoSimulacion.setMigradoDeimos(SiNoEnum.SI);
								}

								// Resultados posibles: OK / NOK / ERR
				
								RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
													TipoResultadoSimulacionEnum.getEnumByCodigoMic(micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getResultadoInvocacion()),
													micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getCodigoError(),
													micDetalleMedioPagoRespuestaEntrada.getResultadoApropiacion().getDescripcionError());
													
								resultadoSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
								
								listaResultadoSimulacion.add(resultadoSimulacion);
							}
						}
					}
				}
			}
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			if (TipoInvocacionEnum.$03.equals(entradaMicApropiacionJms.getTipoInvocacion())) {
				
				this.generarResultadoSimulacionConExcepcionParaApropiacion(
						listaResultadoSimulacion, datosEntrada.getFactura(), 
						datosEntrada.getListaMediosPagoAEnviar(), e);

			} else 
			if (TipoInvocacionEnum.$01.equals(entradaMicApropiacionJms.getTipoInvocacion())) { 
				
				this.generarResultadoSimulacionConExcepcionParaApropiacion(
						listaResultadoSimulacion, datosEntrada.getFactura(), e);
				
			} else 
			if (TipoInvocacionEnum.$02.equals(entradaMicApropiacionJms.getTipoInvocacion())) { 
				
				this.generarResultadoSimulacionConExcepcionParaApropiacion(
						listaResultadoSimulacion, datosEntrada.getListaMediosPagoAEnviar(), e);
				
			}
			
			throw new NegocioExcepcion("Tipo de Invocacion no correspondiente");
		} finally {
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.idThread));
			
			Thread.currentThread().setName(orgName);
		}
		return listaResultadoSimulacion;
		
	}
	
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param factura
	 * @param listaMediosPagos
	 * @param e
	 */
	private void generarResultadoSimulacionConExcepcionParaApropiacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, 
			ShvCobFacturaMic factura, List<ShvCobMedioPago> listaMediosPagos,
			Throwable e) {
		
		// Respuesta de error para la factura
		ResultadoSimulacionApropiacionFacturaMic resultadoSimulacionFactura = new ResultadoSimulacionApropiacionFacturaMic();
		resultadoSimulacionFactura.setIdOperacionShiva(factura.getTransaccion().getOperacionTransaccionFormateado());
		RespuestaInvocacion resultadoInvocacionFactura = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.ERROR_SERVICIO,
				null,
				e.getMessage());
				
		resultadoSimulacionFactura.getListaRespuestasInvocacion().add(resultadoInvocacionFactura);
		listaResultadoSimulacion.add(resultadoSimulacionFactura);

		// Respuesta de error para los medios de pago
		for (ShvCobMedioPago medioPago : listaMediosPagos) {
			ResultadoSimulacionApropiacionMedioPagoMic resultadoSimulacionMedioPago = new ResultadoSimulacionApropiacionMedioPagoMic(); 
			resultadoSimulacionMedioPago.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());
			
			if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
				ShvCobMedioPagoNotaCreditoMic medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMic)medioPago;
				resultadoSimulacionMedioPago.setTipoMedioPago(TipoMedioPagoEnum.NC);
				resultadoSimulacionMedioPago.setNumeroNC(Long.valueOf(medioPagoNotaCreditoMic.getNumeroNotaCredito()));
			} else if (medioPago instanceof ShvCobMedioPagoRemanente) {
				ShvCobMedioPagoRemanente medioPagoRemanente = (ShvCobMedioPagoRemanente)medioPago;
				resultadoSimulacionMedioPago.setTipoMedioPago(TipoMedioPagoEnum.RT);
				resultadoSimulacionMedioPago.setCuentaRemanente(medioPagoRemanente.getCuentaRemanente());
				resultadoSimulacionMedioPago.setTipoRemanente(TipoRemanenteEnum.getEnumByCodigo(medioPagoRemanente.getTipoRemanente()));
			}
			
			RespuestaInvocacion resultadoInvocacionMedioPago = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMessage());
			
			resultadoSimulacionMedioPago.getListaRespuestasInvocacion().add(resultadoInvocacionMedioPago);
			listaResultadoSimulacion.add(resultadoSimulacionMedioPago);
		}
	}
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param listaMediosPagos
	 * @param e
	 */
	private void generarResultadoSimulacionConExcepcionParaApropiacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, List<ShvCobMedioPago> listaMediosPagos,
			Throwable e) {
		
		// Respuesta de error para los medios de pago
		for (ShvCobMedioPago medioPago : listaMediosPagos) {
			ResultadoSimulacionApropiacionMedioPagoMic resultadoSimulacionMedioPago = new ResultadoSimulacionApropiacionMedioPagoMic(); 
			resultadoSimulacionMedioPago.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());
			
			if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
				ShvCobMedioPagoNotaCreditoMic medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMic)medioPago;
				resultadoSimulacionMedioPago.setTipoMedioPago(TipoMedioPagoEnum.NC);
				resultadoSimulacionMedioPago.setNumeroNC(Long.valueOf(medioPagoNotaCreditoMic.getNumeroNotaCredito()));
			} else if (medioPago instanceof ShvCobMedioPagoRemanente) {
				ShvCobMedioPagoRemanente medioPagoRemanente = (ShvCobMedioPagoRemanente)medioPago;
				resultadoSimulacionMedioPago.setTipoMedioPago(TipoMedioPagoEnum.RT);
				resultadoSimulacionMedioPago.setCuentaRemanente(medioPagoRemanente.getCuentaRemanente());
				resultadoSimulacionMedioPago.setTipoRemanente(TipoRemanenteEnum.getEnumByCodigo(medioPagoRemanente.getTipoRemanente()));
			}
			
			RespuestaInvocacion resultadoInvocacionMedioPago = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					e.getMessage());
			
			resultadoSimulacionMedioPago.getListaRespuestasInvocacion().add(resultadoInvocacionMedioPago);
			listaResultadoSimulacion.add(resultadoSimulacionMedioPago);
		}
	}
	
	/**
	 * 
	 * @param listaResultadoSimulacion
	 * @param factura
	 * @param e
	 */
	private void generarResultadoSimulacionConExcepcionParaApropiacion(
			List<ResultadoSimulacion> listaResultadoSimulacion, 
			ShvCobFacturaMic factura, Throwable e) {
		
		// Respuesta de error para la factura
		ResultadoSimulacionApropiacionFacturaMic resultadoSimulacionFactura = new ResultadoSimulacionApropiacionFacturaMic();
		resultadoSimulacionFactura.setIdOperacionShiva(factura.getTransaccion().getOperacionTransaccionFormateado());
		RespuestaInvocacion resultadoInvocacionFactura = new RespuestaInvocacion(
				TipoResultadoSimulacionEnum.ERROR_SERVICIO,
				null,
				e.getMessage());
				
		resultadoSimulacionFactura.getListaRespuestasInvocacion().add(resultadoInvocacionFactura);
		listaResultadoSimulacion.add(resultadoSimulacionFactura);
	}
	
}
