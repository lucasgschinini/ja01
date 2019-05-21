package ar.com.telecom.shiva.negocio.executor.callable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoSimulacionEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.RespuestaInvocacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacion;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionFacturaCalipso;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.ResultadoSimulacionApropiacionMedioPagoCalipso;
import ar.com.telecom.shiva.negocio.executor.callable.resultado.IListaResultadoSimulacionCallable;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ApropiacionCalipsoRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;

public class ApropiacionCalipsoCallable implements IListaResultadoSimulacionCallable {
	
	private String idThread;
	private ApropiacionCalipsoRto datosEntrada;
	
	public ApropiacionCalipsoCallable(String idThread, ApropiacionCalipsoRto datosEntrada) {
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
        
        List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		try {
			IClienteCalipsoServicio clienteCalipsoServicio = (IClienteCalipsoServicio) Configuracion.ctx.getBean("clienteCalipsoServicio");
			ICobroOnlineServicio cobroOnlineServicio = (ICobroOnlineServicio) Configuracion.ctx.getBean("cobroOnlineServicio");
			
			SalidaCalipsoApropiacionWS respuesta =  
					clienteCalipsoServicio.apropiarCobro(datosEntrada.getEntradaCalipsoApropiacionWS());
			
			if (!Validaciones.isObjectNull(respuesta)) {
							
				if (!Validaciones.isObjectNull(datosEntrada.getFactura())) {
					DetalleFactura detalleFactura = respuesta.getDetalleFactura(); 
					
					if (!Validaciones.isObjectNull(detalleFactura)) {
		
						ResultadoSimulacionApropiacionFacturaCalipso respuestaSimulacion = new ResultadoSimulacionApropiacionFacturaCalipso();
						respuestaSimulacion.setIdOperacionShiva(respuesta.getIdOperacionTransaccion());
						
			
						// Datos del documento
						//respuestaSimulacion.setTipoComprobante(detalleFactura.getIdDocumento().getTipoComprobante());
						//respuestaSimulacion.setClaseComprobante(detalleFactura.getIdDocumento().getClaseComprobante());
						//respuestaSimulacion.setSucursalComprobante(detalleFactura.getIdDocumento().getSucursalComprobante());
						//respuestaSimulacion.setNumeroComprobante(detalleFactura.getIdDocumento().getNumeroComprobante());
						
						// Datos de respuesta
						respuestaSimulacion.setInteresesTrasladados(respuesta.getDetalleFactura().getMontoCalculadoMora());
						respuestaSimulacion.setTipoDeCambioFechaCobro(respuesta.getDetalleFactura().getTipoCambioFechaCobro());
						respuestaSimulacion.setTipoDeCambioFechaEmision(respuesta.getDetalleFactura().getTipoCambioFechaEmision());
						respuestaSimulacion.setImporteAplicadoAFechaEmisionMonedaPesos(respuesta.getDetalleFactura().getImporteAplicadoFechaEmisionPesos());
						respuestaSimulacion.setImporteAplicadoAFechaEmisionMonedaOrigen(respuesta.getDetalleFactura().getImporteAplicadoMonedaOrigen());
						
						// Resultados de la apropiacion
						if (Validaciones.isCollectionNotEmpty(respuesta.getDetalleFactura().getListaResultadoApropiacion())) {
							for (ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado resultadoApropiacion : respuesta.getDetalleFactura().getListaResultadoApropiacion()) {

								// Resultados posibles: OK / NOK / ERR / WRN

								// Verifico en la respuesta No se puede generar documento por diferencia de cambio
								if (
									TipoResultadoSimulacionEnum.NOK.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado())) &&
									Constantes.MSG_COD_RESP_NO_SE_PUEDE_GENERAR_DOC_X_DIFERENCUIA_CAMBIO.equals(resultadoApropiacion.getCodigoError())
								) {
									respuestaSimulacion.setRequiereHabilitaraSinDiferenciaCambio(true);
								}

								// Verifico en la respuesta si el documento se encuentra migrado a Deimos
								if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado()))
									&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(resultadoApropiacion.getCodigoError())) {
								
									respuestaSimulacion.setMigradoDeimos(SiNoEnum.SI);
								}
								
								if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado()))
										&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(resultadoApropiacion.getCodigoError())
										&& (Validaciones.isObjectNull(respuesta.getDetalleFactura().getMontoCuenta()) 
												|| (!Validaciones.isObjectNull(respuesta.getDetalleFactura().getMontoCuenta()) && BigDecimal.ZERO.compareTo(respuesta.getDetalleFactura().getMontoCuenta()) < 0))) {
							
									// Seteo en la respuesta la necesidad de actualizar el acuerdo
									respuestaSimulacion.setRequiereBuscarAcuerdoActivo(true);
								}

								if (
										// Tracear la respuesta y nada mas.
										// Si es un simulacion, y no hay intereses generados, y hay un warning de estado de acuerdo no gestionable, no lo 
										// agrego a la lista de resultados, ya no es necesario mostrar dicho error en pantalla
										(TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado()))
										&& Constantes.MSJ_COD_RESP_ACUERDO_NO_PERMITE_GESTION.equals(resultadoApropiacion.getCodigoError())
										&& (Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) 
												|| (!Validaciones.isObjectNull(respuestaSimulacion.getInteresesTrasladados()) && BigDecimal.ZERO.compareTo(respuestaSimulacion.getInteresesTrasladados()) == 0)))

										|| 
										// Tracear la respuesta y nada mas. Este mensaje no debe formar parte de la lista de errores de la factura original
										// ya que luego es muy dificil manejar el dato. Solo le corresponde a la copia de la factura.
										(TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado()))
												&& Constantes.MSJ_COD_RESP_SALDO_RESTANTE_SIN_SALDAR.equals(resultadoApropiacion.getCodigoError()))) {
									
								} else {
								
									RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(resultadoApropiacion.getResultado()),
											resultadoApropiacion.getCodigoError(),
											resultadoApropiacion.getDescripcionError());
											
									respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
								}
							}
						}
						
						// Documentos generados nota de debito o credito
		
						if (!Validaciones.isObjectNull(respuesta.getListaNotasCreditoODebito()) 
										&& Validaciones.isCollectionNotEmpty(respuesta.getListaNotasCreditoODebito())) {
		
							for (DetalleNotaCreditoDebito nuevoDocumento : respuesta.getListaNotasCreditoODebito()) {
								
								if (TipoComprobanteEnum.DEB.equals(nuevoDocumento.getCtaoNotaCredito().getTipoComprobante())) {
								
									ShvCobFacturaCalipso notaDebito = new ShvCobFacturaCalipso();
									
									notaDebito.setImporteOriginal(nuevoDocumento.getImporte());
									notaDebito.setImporteCobrar(nuevoDocumento.getImporteAplicado());

									notaDebito.setImporteAplicado(nuevoDocumento.getImporteAplicado());
									notaDebito.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);

									notaDebito.setSaldoActual(nuevoDocumento.getImporte());
									notaDebito.setImporteImpuestos(nuevoDocumento.getImporteImpuestos());
									notaDebito.setImporteCapital(nuevoDocumento.getImporteCapital());
									
									//Dolares
									notaDebito.setMoneda(nuevoDocumento.getMoneda());
									notaDebito.setMonedaImporteCobrar(datosEntrada.getEntradaCalipsoApropiacionWS().getMonedaOperacion());

									notaDebito.setTipoComprobante(cobroOnlineServicio.buscarComprobante(nuevoDocumento.getCtaoNotaCredito().getTipoComprobante()));
									notaDebito.setClaseComprobante(nuevoDocumento.getCtaoNotaCredito().getClaseComprobante());
									notaDebito.setSucursalComprobante(nuevoDocumento.getCtaoNotaCredito().getSucursalComprobante());
									notaDebito.setNumeroComprobante(nuevoDocumento.getCtaoNotaCredito().getNumeroComprobante());
									notaDebito.setFechaVencimiento(nuevoDocumento.getFechaVencimiento());
									notaDebito.setOrigenDocumento(nuevoDocumento.getOrigenDelDocumento());
			
									notaDebito.setRazonSocialClienteLegado(nuevoDocumento.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
									notaDebito.setTipoCliente(nuevoDocumento.getInformacionAdicionalTagetikCalipso().getTipoCliente());
									notaDebito.setCuit(nuevoDocumento.getInformacionAdicionalTagetikCalipso().getCuit());
									notaDebito.setUnidadOperativa(nuevoDocumento.getInformacionAdicionalDacota().getUnidadOperativa());
									notaDebito.setSubtipo(nuevoDocumento.getInformacionAdicionalDacota().getSubTipo());
									notaDebito.setHolding(nuevoDocumento.getInformacionAdicionalDacota().getHolding());
			
									notaDebito.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
									notaDebito.setGeneradoPorCobro(SiNoEnum.SI);
									notaDebito.setSistemaOrigen(SistemaEnum.CALIPSO);
									notaDebito.setMoneda(MonedaEnum.PES);
									notaDebito.setFechaValor(new Date());
									notaDebito.setSociedad(SociedadEnum.TELECOM);
									
									notaDebito.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);

									// Ante una diferencia de cambio, puede llegar el mensaje 8077 a nivel factura
									// pero que solo debemos mostrar a nivel de nota de débito por diferencia de cambio, ya que es un mensaje propio de esta
									if(!Validaciones.isObjectNull(respuesta.getDetalleFactura())){
										
										if(Validaciones.isCollectionNotEmpty(respuesta.getDetalleFactura().getListaResultadoApropiacion())){
											
											for (ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado resultado : respuesta.getDetalleFactura().getListaResultadoApropiacion()) {
												
												if (TipoResultadoEnum.WRN.getDescripcionCalipso().equals(resultado.getResultado())
														&& Constantes.MSJ_COD_RESP_SALDO_RESTANTE_SIN_SALDAR.equals(resultado.getCodigoError())) {
													
													StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);
													detalleMensaje.append(TipoMensajeEstadoEnum.WRN.getDescripcion());
													detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
													detalleMensaje.append(resultado.getDescripcionError().toString().trim());	
													
													notaDebito.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
													notaDebito.setMensajeEstado(detalleMensaje.toString());
												}
											}
										}
										
									}
									
									respuestaSimulacion.setNotaDebitoPorDiferenciaCambio(notaDebito);									
								
								} else if (TipoComprobanteEnum.CRE.equals(nuevoDocumento.getCtaoNotaCredito().getTipoComprobante())) {
			
									ShvCobMedioPagoNotaCreditoCalipso notaCredito = new ShvCobMedioPagoNotaCreditoCalipso();
									
									notaCredito.setImporteCapital(nuevoDocumento.getImporteCapital());
									notaCredito.setImporteImpuestos(nuevoDocumento.getImporteImpuestos());
									
									//Dolares
									notaCredito.setMoneda(nuevoDocumento.getMoneda());
									notaCredito.setMonedaImporte(datosEntrada.getEntradaCalipsoApropiacionWS().getMonedaOperacion());
									
									notaCredito.setTipoComprobante(nuevoDocumento.getCtaoNotaCredito().getTipoComprobante());
									notaCredito.setClaseComprobante(nuevoDocumento.getCtaoNotaCredito().getClaseComprobante());
									notaCredito.setSucursalComprobante(nuevoDocumento.getCtaoNotaCredito().getSucursalComprobante());
									notaCredito.setNroComprobante(nuevoDocumento.getCtaoNotaCredito().getNumeroComprobante());
									notaCredito.setFechaVencimiento(nuevoDocumento.getFechaVencimiento());
									notaCredito.setFechaEmision(nuevoDocumento.getFechaVencimiento());
									notaCredito.setImporteAplicado(nuevoDocumento.getImporteAplicado());
									notaCredito.setOrigenDocumento(nuevoDocumento.getOrigenDelDocumento());
									notaCredito.setImporte(nuevoDocumento.getImporte());
			
									notaCredito.setRazonSocialClienteLegado(nuevoDocumento.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
									notaCredito.setTipoCliente(nuevoDocumento.getInformacionAdicionalTagetikCalipso().getTipoCliente());
									notaCredito.setUnidadOperativa(nuevoDocumento.getInformacionAdicionalDacota().getUnidadOperativa());
									notaCredito.setSubtipo(nuevoDocumento.getInformacionAdicionalDacota().getSubTipo());
									notaCredito.setHolding(nuevoDocumento.getInformacionAdicionalDacota().getHolding());
									
									notaCredito.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
									notaCredito.setGeneradoPorCobro(SiNoEnum.SI);
									notaCredito.setSistemaOrigen(SistemaEnum.CALIPSO);
									notaCredito.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
									notaCredito.setTipoMedioPago(datosEntrada.getTipoMedioPagoNotaCredito());
			
									respuestaSimulacion.setNotaCreditoPorDiferenciaCambio(notaCredito);
								}
							}
						}
						
						listaResultadoSimulacion.add(respuestaSimulacion);
					}
				}
				
				if (!Validaciones.isObjectNull(respuesta.getListaCTAoNotaDeCredito()) 
								&& Validaciones.isCollectionNotEmpty(respuesta.getListaCTAoNotaDeCredito())) {
				
					for (DetalleCTAoNotaCredito medioPago : respuesta.getListaCTAoNotaDeCredito()) {
					
						ResultadoSimulacionApropiacionMedioPagoCalipso respuestaSimulacion = new ResultadoSimulacionApropiacionMedioPagoCalipso();
						respuestaSimulacion.setIdOperacionShiva(respuesta.getIdOperacionTransaccion());

						// Datos del documento
						respuestaSimulacion.setTipoComprobante(medioPago.getIdDocumento().getTipoComprobante());
						respuestaSimulacion.setClaseComprobante(medioPago.getIdDocumento().getClaseComprobante());
						respuestaSimulacion.setSucursalComprobante(medioPago.getIdDocumento().getSucursalComprobante());
						respuestaSimulacion.setNumeroComprobante(medioPago.getIdDocumento().getNumeroComprobante());
						
						//Dolares
						respuestaSimulacion.setTipoCambioFechaEmision(medioPago.getTipoCambioFechaEmision());
						respuestaSimulacion.setTipoCambioFechaCobro(medioPago.getTipoCambioFechaCobro());
						respuestaSimulacion.setImporteAplicadoFechaEmisionPesos(medioPago.getImporteAplicadoFechaEmisionPesos());
						respuestaSimulacion.setImporteAplicadoMonedaOrigen(medioPago.getImporteAplicadoMonedaOrigen());
						
						// Resultados de la apropiacion
						// Resultados posibles: OK / NOK / ERR / WRN

						// Verifico en la respuesta si el documento se encuentra migrado a Deimos
						if (TipoResultadoSimulacionEnum.WARNING.equals(TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(medioPago.getResultadoApropiacion().getResultado()))
							&& Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(medioPago.getResultadoApropiacion().getCodigoError())) {
						
							respuestaSimulacion.setMigradoDeimos(SiNoEnum.SI);
						}

						RespuestaInvocacion resultadoInvocacion = new RespuestaInvocacion(
											TipoResultadoSimulacionEnum.getEnumByCodigoCalipso(medioPago.getResultadoApropiacion().getResultado()),
											medioPago.getResultadoApropiacion().getCodigoError(),
											medioPago.getResultadoApropiacion().getDescripcionError());
											
					 	respuestaSimulacion.getListaRespuestasInvocacion().add(resultadoInvocacion);
					 	
					 	listaResultadoSimulacion.add(respuestaSimulacion);
					}
				}
			} else {

				listaResultadoSimulacion.addAll(
						generarRespuestaResultadoSimulacionApropiacionFacturaCalipsoAnteError(
								datosEntrada.getFactura(), datosEntrada.getListaMediosPagoAEnviar(), null));
			}
			
		} catch (Throwable e) {
			Traza.error(this.getClass(),e.getMessage(), e);
			
			listaResultadoSimulacion.addAll(
					generarRespuestaResultadoSimulacionApropiacionFacturaCalipsoAnteError(
							datosEntrada.getFactura(), datosEntrada.getListaMediosPagoAEnviar(), e));
			
		} finally {
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo {0}.", this.idThread));
			
			Thread.currentThread().setName(orgName);
		}
		return listaResultadoSimulacion;
		
	}

	/**
	 * 
	 * @param factura
	 * @param listaMediosPagoAEnviar
	 * @param e
	 * @return
	 */
	private List<ResultadoSimulacion> generarRespuestaResultadoSimulacionApropiacionFacturaCalipsoAnteError(
			ShvCobFactura factura, List<ShvCobMedioPago>listaMediosPagoAEnviar, Throwable e) {

		String mensajeError = Validaciones.isObjectNull(e) ? "WS CalipsoCobranzas Apropiacion: Falla de conexión" : e.getMessage();
		
		List<ResultadoSimulacion> listaResultadoSimulacion = new ArrayList<ResultadoSimulacion>();
		
		if (!Validaciones.isObjectNull(factura)) {
			// Respuesta de error para la factura
			ResultadoSimulacionApropiacionFacturaCalipso resultadoSimulacionFactura = new ResultadoSimulacionApropiacionFacturaCalipso();
			resultadoSimulacionFactura.setIdOperacionShiva(factura.getTransaccion().getOperacionTransaccionFormateado());
			RespuestaInvocacion resultadoInvocacionFactura = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					mensajeError);
					
			resultadoSimulacionFactura.getListaRespuestasInvocacion().add(resultadoInvocacionFactura);
			listaResultadoSimulacion.add(resultadoSimulacionFactura);
		}
		
		// Respuesta para los medios de pago
		for (ShvCobMedioPago medioPago : listaMediosPagoAEnviar) {
			ResultadoSimulacionApropiacionMedioPagoCalipso resultadoSimulacionMedioPago = new ResultadoSimulacionApropiacionMedioPagoCalipso(); 
			resultadoSimulacionMedioPago.setIdOperacionShiva(medioPago.getTransaccion().getOperacionTransaccionFormateado());
			
			if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
				ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
				
				resultadoSimulacionMedioPago.setTipoComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante());
				resultadoSimulacionMedioPago.setClaseComprobante(medioPagoNotaCreditoCalipso.getClaseComprobante());
				resultadoSimulacionMedioPago.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
				resultadoSimulacionMedioPago.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
			} else {
				ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;
				
				resultadoSimulacionMedioPago.setTipoComprobante(medioPagoCTA.getTipoComprobante());
				resultadoSimulacionMedioPago.setClaseComprobante(medioPagoCTA.getClaseComprobante());
				resultadoSimulacionMedioPago.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
				resultadoSimulacionMedioPago.setNumeroComprobante(medioPagoCTA.getNroComprobante());
			}
			
			RespuestaInvocacion resultadoInvocacionMedioPago = new RespuestaInvocacion(
					TipoResultadoSimulacionEnum.ERROR_SERVICIO,
					null,
					mensajeError);
			
			resultadoSimulacionMedioPago.getListaRespuestasInvocacion().add(resultadoInvocacionMedioPago);
			listaResultadoSimulacion.add(resultadoSimulacionMedioPago);
		}	
		
		return listaResultadoSimulacion;
	}
	

}
