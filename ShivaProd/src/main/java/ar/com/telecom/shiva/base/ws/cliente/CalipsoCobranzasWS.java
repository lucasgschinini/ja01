package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoRespuesta;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoSalidaCobranzasWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ApropiacionSalida;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.DetFacArrayApropiacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaCreDebArrayApropiacionResponse;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaCtaCreArrayApropiacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaCtaCreArrayApropiacionResponse;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaCtaCreArrayConfirmacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaCtaCreArrayDesapropiacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaFacArrayConfirmacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaFacArrayDesapropiacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.ListaResultadoApropiacion.ResultadoApropiacion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.Listaresultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.Resultadoshiva;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cobranzas.WebServiceCalipsoCobranzasPortType;

public class CalipsoCobranzasWS {
	
	@Autowired 
	WebServiceCalipsoCobranzasPortType webServiceCalipsoCobranzasPortType;
	
	/**
	 * Objetivo: Permite al consumidor del servicio realizar las siguientes operaciones
	 * 				Apropiacion de Deuda
	 * 				Apropiacion de CTA
	 * 				Apropiacion de Deuda y CTA
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws WebServiceExcepcion
	 * @throws WebServiceFormatoMensajeExcepcion 
	 */
	public SalidaCalipsoApropiacionWS apropiarCobro(EntradaCalipsoApropiacionWS entrada) 
			throws WebServiceExcepcion, WebServiceFormatoMensajeExcepcion {
		try {
			String idShiva = Utilidad.rellenarCerosIzquierda(entrada.getIdOperacion().toString(), 7);
			String numeroTransaccion = Utilidad.rellenarCerosIzquierda(entrada.getNumeroTransaccion().toString(), 5);
			String usuario = Constantes.SHIVA_APLICACION.toUpperCase();
			String modoOperacion = !Validaciones.isObjectNull(entrada.getModoOperacion())?entrada.getModoOperacion().getDescripcionCorta():null;
			String monedaOperacion = entrada.getMonedaOperacion().getSigno2();
			String fechaCobro = Utilidad.formatDateAAAAMMDDconBarra(entrada.getFechaCobranza());
			
			//Detalle de Factura (Agrupador)
			DetFacArrayApropiacion detFac = new DetFacArrayApropiacion();
			
			if (!Validaciones.isObjectNull(entrada.getDetalleFactura()) && !Validaciones.isObjectNull(entrada.getDetalleFactura().getMontoACancelarEnPesos())) {
				//Sprint 5, si tiene setIdDocumentoCuentasCobranza, sino usa el idDocumento
				if(!Validaciones.isObjectNull(entrada.getDetalleFactura().getIdDocumentoCuentasCobranza())){
					detFac.setIdDocumentoCuentasCobranza(entrada.getDetalleFactura().getIdDocumentoCuentasCobranza());
					
					DetFacArrayApropiacion.IdDocctascob idDocumento = new DetFacArrayApropiacion.IdDocctascob();
					idDocumento.setTipo("");
					idDocumento.setClase("");
					idDocumento.setSucursal("");
					idDocumento.setNumero("");
					detFac.setIdDocctascob(idDocumento);
				}else if (!Validaciones.isObjectNull(entrada.getDetalleFactura().getIdDocumento())){
					DetFacArrayApropiacion.IdDocctascob idDocumento = new DetFacArrayApropiacion.IdDocctascob();
					idDocumento.setTipo(
							!Validaciones.isObjectNull(entrada.getDetalleFactura().getIdDocumento().getTipoComprobante())
								?entrada.getDetalleFactura().getIdDocumento().getTipoComprobante().name():"");
					idDocumento.setClase(
							!Validaciones.isObjectNull(entrada.getDetalleFactura().getIdDocumento().getClaseComprobante())
								?entrada.getDetalleFactura().getIdDocumento().getClaseComprobante().name():"");
					idDocumento.setSucursal(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											entrada.getDetalleFactura().getIdDocumento().getSucursalComprobante()), 4));
					idDocumento.setNumero(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											entrada.getDetalleFactura().getIdDocumento().getNumeroComprobante()), 8));
					detFac.setIdDocctascob(idDocumento);
				}

				detFac.setSaldo(
						!Validaciones.isObjectNull(
								entrada.getDetalleFactura().getMontoACancelarEnPesos())?
										new BigDecimal(entrada.getDetalleFactura().getMontoACancelarEnPesos().toString().replace(",", ".")):new BigDecimal("0"));
				detFac.setTipoOperacion(
						!Validaciones.isObjectNull(
								entrada.getDetalleFactura().getTipoOperacion())?
										entrada.getDetalleFactura().getTipoOperacion().getDescripcionCorta():"");
				detFac.setTipoMora(
						!Validaciones.isObjectNull(
								entrada.getDetalleFactura().getTipoMora())?
										entrada.getDetalleFactura().getTipoMora():"");
				detFac.setAlgoritmoMora(
						!Validaciones.isObjectNull(
								entrada.getDetalleFactura().getAlgoritmoMora())?
										entrada.getDetalleFactura().getAlgoritmoMora().name():"");
				detFac.setImporteBonificacionIntereses(
					!Validaciones.isObjectNull(
						entrada.getDetalleFactura().getImporteBonificacionIntereses())?
								new BigDecimal(entrada.getDetalleFactura().getImporteBonificacionIntereses().toString().replace(",", ".")):new BigDecimal("0"));
				detFac.setAcuerdo(
						Utilidad.generarSalidaConValorOVacio(entrada.getDetalleFactura().getAcuerdoFacturacion()));
				//sprint 5 
				detFac.setMontoAcumuladoSimulacion(entrada.getDetalleFactura().getMontoAcumuladoSimulacion());
				//fase 3, dolares
				detFac.setAccionSobreDiferenciaDeCambio(entrada.getDetalleFactura().getAccionSobreDiferenciaDeCambio().name());
			}
			
			//Lista de CTA o Notas de Credito
			ListaCtaCreArrayApropiacion listaCtaCre = new ListaCtaCreArrayApropiacion();
			List<ListaCtaCreArrayApropiacion.Detalle> detalles = new ArrayList<ListaCtaCreArrayApropiacion.Detalle>();
			for (DetalleCTAoNotaCredito ctaCre: entrada.getListaCtaONotaCredito()) {
				
				ListaCtaCreArrayApropiacion.Detalle detalle = new ListaCtaCreArrayApropiacion.Detalle();
				
				detalle.setImporte(
						!Validaciones.isObjectNull(ctaCre.getImporte())?ctaCre.getImporte():new BigDecimal("0"));
				
				//sprint 5
				detalle.setMontoAcumuladoSimulacion(!Validaciones.isObjectNull(ctaCre.getMontoAcumuladoSimulacion())?
						ctaCre.getMontoAcumuladoSimulacion():new BigDecimal("0"));

				//Como son excluyentes
				if(!Validaciones.isObjectNull(ctaCre.getIdDocumentoCuentasCobranza())){
					detalle.setIdDocumentoCuentasCobranza(ctaCre.getIdDocumentoCuentasCobranza());
					detalle.setIdDocumento(new BigInteger("0"));
					detalle.setTipo("");
					detalle.setClase("");
					detalle.setSucursal("");
					detalle.setNumero("");
				}else{
					detalle.setIdDocumento(new BigInteger("0"));
					detalle.setTipo(
							!Validaciones.isObjectNull(ctaCre.getIdDocumento().getTipoComprobante())
								?ctaCre.getIdDocumento().getTipoComprobante().name():"");
					detalle.setClase(
							!Validaciones.isObjectNull(ctaCre.getIdDocumento().getClaseComprobante())
								?ctaCre.getIdDocumento().getClaseComprobante().name():"");
					detalle.setSucursal(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											ctaCre.getIdDocumento().getSucursalComprobante()), 4));
					detalle.setNumero(
							Utilidad.rellenarCerosIzquierda(
									Utilidad.generarSalidaConValorOVacio(
											ctaCre.getIdDocumento().getNumeroComprobante()), 8));
					detalle.setIdDocumentoCuentasCobranza(new BigInteger("0"));
				}
				detalles.add(detalle);
			}
			listaCtaCre.getDetalle().addAll(detalles);
			
			//TODO
			ApropiacionSalida respuesta = webServiceCalipsoCobranzasPortType.apropiacion(idShiva, numeroTransaccion, usuario, modoOperacion,monedaOperacion, fechaCobro, detFac, listaCtaCre);
			
			//Salida>>>>
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				SalidaCalipsoApropiacionWS datos = new SalidaCalipsoApropiacionWS();
				datos.setIdOperacionTransaccion(respuesta.getIdShiva());
				
				if (!Validaciones.isObjectNull(respuesta.getDetFac())) {
					DetalleFactura detalleFactura = new DetalleFactura();
					detalleFactura.setIdCobranza(respuesta.getDetFac().getIdCobranza());
					detalleFactura.setMontoCalculadoMora(
							(!Validaciones.isNullOrEmpty(respuesta.getDetFac().getImportemora()))? 
									new BigDecimal(respuesta.getDetFac().getImportemora()):null);
					detalleFactura.setMontoCuenta(
							(!Validaciones.isNullOrEmpty(respuesta.getDetFac().getMontocta()))? 
									new BigDecimal(respuesta.getDetFac().getMontocta()):null);
					
					//<<< sprint 5
					detalleFactura.setTipoCambioFechaEmision(
							respuesta.getDetFac().getTipoCambioFechaEmision()
							);
					detalleFactura.setTipoCambioFechaCobro(
							respuesta.getDetFac().getTipoCambioFechaCobro()
							);
					detalleFactura.setImporteAplicadoFechaEmisionPesos(
							respuesta.getDetFac().getImporteAplicadoFechaEmisionPesos()
							);
					detalleFactura.setImporteAplicadoMonedaOrigen(
							respuesta.getDetFac().getImporteAplicadoMonedaOrigen()
							);
					
					if (!Validaciones.isObjectNull(respuesta.getDetFac().getListaResultadoApropiacion())) {
						List<ResultadoApropiacion> listaRes = respuesta.getDetFac().getListaResultadoApropiacion().getResultadoApropiacion();
						if(Validaciones.isCollectionNotEmpty(listaRes)){
							List<Resultado> listaResultado = new ArrayList<Resultado>();
							for(ResultadoApropiacion resultadoApropiacion : listaRes){
								Resultado resultado = new Resultado();
								resultado.setCodigoError(resultadoApropiacion.getCodigoerror());
								resultado.setDescripcionError(resultadoApropiacion.getDescripcionerror());
								resultado.setResultado(resultadoApropiacion.getResultado());
								listaResultado.add(resultado);
							}
							detalleFactura.setListaResultadoApropiacion(listaResultado);
						}
					}
					//sprint 5 >>>
					
					datos.setDetalleFactura(detalleFactura);
				}
				
				if (!Validaciones.isObjectNull(respuesta.getListaCreDeb())
						&& Validaciones.isCollectionNotEmpty(respuesta.getListaCreDeb().getDetalle())) {
					
					List<DetalleNotaCreditoDebito> listaNotasCreditoODebito = new ArrayList<DetalleNotaCreditoDebito>();
					
					for (ListaCreDebArrayApropiacionResponse.Detalle detalle: 
							respuesta.getListaCreDeb().getDetalle()) {
						DetalleNotaCreditoDebito notaCreDeb = new DetalleNotaCreditoDebito();
						
						//Sprint 5 
						notaCreDeb.setIdCobranza(detalle.getIdCobranza());
						notaCreDeb.setImporte(
								(detalle.getImporte() != null)? 
										new BigDecimal(detalle.getImporte().toString()):null);
						
						//<<< Sprint 5
						notaCreDeb.setImporteCapital(detalle.getImporteCapital());
						notaCreDeb.setImporteImpuestos(detalle.getImporteImpuestos());
						//Sprint 5 >>>
						
						//Dolares
						notaCreDeb.setMoneda(MonedaEnum.getEnumBySigno2(detalle.getMoneda()));
						
						IdDocumento documento = new IdDocumento();
						documento.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(detalle.getTipo()));	
						documento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalle.getClase()));
						documento.setSucursalComprobante(
								(!Validaciones.isNullOrEmpty(detalle.getSucursal()))? 
										detalle.getSucursal():null);
						documento.setNumeroComprobante(
								(!Validaciones.isNullOrEmpty(detalle.getNumero()))? 
										detalle.getNumero():null);
						notaCreDeb.setCtaoNotaCredito(documento);
						
//						notaCreDeb.setIdDocumentoCuentasCobranza(detalle.getIdDocumentoCuentasCobranza());
						notaCreDeb.setIdDocumentoCuentasCobranza(detalle.getIdDocctascob());
						
						try {
							notaCreDeb.setFechaVencimiento(
									(detalle.getVencimiento() != null)? 
											Utilidad.parseFechaBarraString(detalle.getVencimiento()):null);
						} catch (ParseException e) {
							throw new WebServiceExcepcion(e);
						}
						
						//<<< sprint 5
						notaCreDeb.setImporteAplicado(detalle.getImporteAplicado());
						notaCreDeb.setOrigenDelDocumento(OrigenDocumentoEnum.getEnumByName(detalle.getOrigenDelDocumento()));
						
						if (!Validaciones.isObjectNull(detalle.getInformacionAdicionalTagetikCalipso())) {
							notaCreDeb.getInformacionAdicionalTagetikCalipso().setRazonSocialCliente(
									detalle.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
							notaCreDeb.getInformacionAdicionalTagetikCalipso().setTipoCliente(
									detalle.getInformacionAdicionalTagetikCalipso().getTipoCliente());
							notaCreDeb.getInformacionAdicionalTagetikCalipso().setCuit(
									detalle.getInformacionAdicionalTagetikCalipso().getCUIT());
						} else {
							notaCreDeb.setInformacionAdicionalTagetikCalipso(null);
						}
						
						if (!Validaciones.isObjectNull(detalle.getInformacionAdicionalDacota())) {
							notaCreDeb.getInformacionAdicionalDacota().setUnidadOperativa(
									detalle.getInformacionAdicionalDacota().getUnidadOperativa());
							notaCreDeb.getInformacionAdicionalDacota().setSubTipo(
									detalle.getInformacionAdicionalDacota().getSubTipo());
							notaCreDeb.getInformacionAdicionalDacota().setHolding(
									detalle.getInformacionAdicionalDacota().getHolding());
						} else {
							notaCreDeb.setInformacionAdicionalDacota(null);
						}
						//sprint 5 >>>
						
						listaNotasCreditoODebito.add(notaCreDeb);
					}
					
					datos.setListaNotasCreditoODebito(listaNotasCreditoODebito);
				}
				
				 
				if (!Validaciones.isObjectNull(respuesta.getListaCtaCre())
						&& Validaciones.isCollectionNotEmpty(respuesta.getListaCtaCre().getDetalle())) {
					
					List<DetalleCTAoNotaCredito> listaCtaCredito = 
							new ArrayList<DetalleCTAoNotaCredito>();
					
					for (ListaCtaCreArrayApropiacionResponse.Detalle detalle: respuesta.getListaCtaCre().getDetalle()) {
						
						DetalleCTAoNotaCredito notaCtaCre = new DetalleCTAoNotaCredito();
						notaCtaCre.setIdCobranza(detalle.getIdCobranza());							
						
						IdDocumento documento = new IdDocumento();
						documento.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(detalle.getTipo()));	
						documento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalle.getClase()));
						documento.setSucursalComprobante(
								(!Validaciones.isNullOrEmpty(detalle.getSucursal()))? 
										detalle.getSucursal():null);
						documento.setNumeroComprobante(
								(!Validaciones.isNullOrEmpty(detalle.getNumero()))? 
										detalle.getNumero():null);
						
						notaCtaCre.setIdDocumento(documento);
						
						//Dolares
						notaCtaCre.setTipoCambioFechaEmision(detalle.getTipoCambioFechaEmision());
						notaCtaCre.setTipoCambioFechaCobro(detalle.getTipoCambioFechaCobro());
						notaCtaCre.setImporteAplicadoFechaEmisionPesos(detalle.getImporteAplicadoFechaEmisionPesos());
						notaCtaCre.setImporteAplicadoMonedaOrigen(detalle.getImporteAplicadoMonedaOrigen());
						
						if (Validaciones.isCollectionNotEmpty(detalle.getDetallenewcta())) {
							ListaCtaCreArrayApropiacionResponse.Detalle.Detallenewcta detalleNuevaCta 
								= detalle.getDetallenewcta().get(0);
							
							if (Validaciones.isObjectNull(detalleNuevaCta.getImporte()) &&
									Validaciones.isNullOrEmpty(detalleNuevaCta.getTipo()) &&
									Validaciones.isNullOrEmpty(detalleNuevaCta.getClase()) &&
									Validaciones.isNullOrEmpty(detalleNuevaCta.getSucursal()) &&
									Validaciones.isNullOrEmpty(detalleNuevaCta.getNumero())) {
								
								notaCtaCre.setDetalleCtaNtaCre(null);
								
							} else {
								
								DetalleCTAoNotaCreditoRespuesta nuevaCta = new DetalleCTAoNotaCreditoRespuesta();
								
								nuevaCta.setImporte(detalleNuevaCta.getImporte());
								//Dolares
								nuevaCta.setMoneda(MonedaEnum.getEnumBySigno2(detalleNuevaCta.getMoneda()));
								
//								sprint 5
								notaCtaCre.setIdDocumentoCuentasCobranza(detalleNuevaCta.getIdDocumentoCuentasCobranza());
								
								IdDocumento documentoNuevaCta = new IdDocumento();
								documentoNuevaCta.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(detalleNuevaCta.getTipo()));	
								documentoNuevaCta.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalleNuevaCta.getClase()));
								documentoNuevaCta.setSucursalComprobante(
										(!Validaciones.isNullOrEmpty(detalleNuevaCta.getSucursal()))? 
												detalleNuevaCta.getSucursal():null);
								documentoNuevaCta.setNumeroComprobante(
										(!Validaciones.isNullOrEmpty(detalleNuevaCta.getNumero()))? 
												detalleNuevaCta.getNumero():null);
								nuevaCta.setCtaResultante(documentoNuevaCta);
								
								try {
									nuevaCta.setFechaAltaOriginalCTA(
											(detalleNuevaCta.getFechaoriginalcta() != null)? 
													Utilidad.parseFechaBarraString(detalleNuevaCta.getFechaoriginalcta()):null);
								} catch (ParseException e) {
									throw new WebServiceExcepcion(e);
								}
								
								notaCtaCre.setDetalleCtaNtaCre(nuevaCta);
							}
						}
						
//						sprint 5
						Resultadoshiva resShiva = detalle.getResultadoApropiacion();
						if(!Validaciones.isObjectNull(resShiva)){
							Resultado resultadoApropiacion = new Resultado();
							resultadoApropiacion.setCodigoError(resShiva.getCodigoerror());
							resultadoApropiacion.setDescripcionError(resShiva.getDescripcionerror());
							resultadoApropiacion.setResultado(resShiva.getResultado());
							notaCtaCre.setResultadoApropiacion(resultadoApropiacion);
						}
						
						listaCtaCredito.add(notaCtaCre);
					}
					
					datos.setListaCTAoNotaDeCredito(listaCtaCredito);
				}
				
				Resultado resultado = new Resultado();
				resultado.setResultado(respuesta.getResultado().getResultado().getResultado());
				resultado.setCodigoError(respuesta.getResultado().getResultado().getCodigoerror());
				resultado.setDescripcionError(respuesta.getResultado().getResultado().getDescripcionerror());
				datos.setResultadoInvocacion(resultado);
				
				return datos;
			}
			throw new WebServiceExcepcion("WS CalipsoCobranzas Apropiacion: Se ha producido un error en el webservice");
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS CalipsoCobranzas Apropiacion - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion("WS CalipsoCobranzas Apropiacion: Falla de conexion: " + e);
		}
	}
	
	
	/**
	 * Objetivo: Permite al consumidor del servicio solicitar la confirmacion de todas las transaccciones
	 * asociadas a una operación
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws WebServiceExcepcion
	 * @throws WebServiceFormatoMensajeExcepcion 
	 */
	public SalidaCalipsoConfirmacionWS confirmarCobro(EntradaCalipsoConfirmacionWS entrada) 
			throws WebServiceExcepcion, WebServiceFormatoMensajeExcepcion {
		try {
			BigInteger idShiva = entrada.getIdOperacion();
			String usuCobrador = Constantes.SHIVA_APLICACION.toUpperCase();
			
			ListaFacArrayConfirmacion listaFac = new ListaFacArrayConfirmacion();
			List<ListaFacArrayConfirmacion.Detalle> detalles = new ArrayList<ListaFacArrayConfirmacion.Detalle>();
			for (DetalleFactura detFac: entrada.getListaFacturasAConfirmar()) {
				ListaFacArrayConfirmacion.Detalle detalle = new ListaFacArrayConfirmacion.Detalle();
				detalle.setIdCobranza(detFac.getIdCobranza());
				detalles.add(detalle);
			}
			listaFac.getDetalle().addAll(detalles);
			
//			Sprint 5
			List<ListaFacArrayConfirmacion.DetalleCargos> listaDetalleCargos = new ArrayList<ListaFacArrayConfirmacion.DetalleCargos>();
			for (DetalleCargoSalidaCobranzasWs detCargos: entrada.getListaCargosAConfirmar()) {
				ListaFacArrayConfirmacion.DetalleCargos detalleCargo = new ListaFacArrayConfirmacion.DetalleCargos();
				detalleCargo.setIdMovMer(detCargos.getIdMovMer());
				listaDetalleCargos.add(detalleCargo);
			}			
			listaFac.getDetalleCargos().addAll(listaDetalleCargos);
			
			ListaCtaCreArrayConfirmacion listaCtaCre = new ListaCtaCreArrayConfirmacion();
			List<ListaCtaCreArrayConfirmacion.Detalle> detallesCtaCre = new ArrayList<ListaCtaCreArrayConfirmacion.Detalle>();
			for (DetalleCTAoNotaCredito detCtaCre: entrada.getListaCtaONotaCreditoAConfirmar()) {
				ListaCtaCreArrayConfirmacion.Detalle detalle = new ListaCtaCreArrayConfirmacion.Detalle();
				detalle.setIdCobranza(detCtaCre.getIdCobranza());
				detallesCtaCre.add(detalle);
			}
			listaCtaCre.getDetalle().addAll(detallesCtaCre);
			
			Listaresultado respuesta = webServiceCalipsoCobranzasPortType.confirmacion(idShiva, usuCobrador, listaFac, listaCtaCre);
			
			if (respuesta != null) {
				SalidaCalipsoConfirmacionWS datos = new SalidaCalipsoConfirmacionWS();
				
				datos.setIdOperacion(respuesta.getIdShiva());
				
				Resultado resultado = new Resultado();
				resultado.setResultado(respuesta.getResultado().getResultado());
				resultado.setCodigoError(respuesta.getResultado().getCodigoerror());
				resultado.setDescripcionError(respuesta.getResultado().getDescripcionerror());
				datos.setResultadoInvocacion(resultado);
				
				return datos;
			}
			throw new WebServiceExcepcion("WS CalipsoCobranzas Confirmacion: Se ha producido un error en el webservice");
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS CalipsoCobranzas Confirmacion - Error de formato: " + e);
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion("WS CalipsoCobranzas Confirmacion: Falla de conexion: " + e);
		}
	}
	
	/**
	 * Invoca al Servicio de desapropiacion de operacion
	 * 
	 * Objetivo: Permite al consumidor del servicio solicitar el pedido de desapropiacion
	 * de todas las transacciones asociadas a una operacion
	 * Proveedor: Calipso
	 *   
	 * @param entrada
	 * @return
	 * @throws WebServiceExcepcion
	 * @throws WebServiceFormatoMensajeExcepcion 
	 */
	public SalidaCalipsoDesapropiacionWS desapropiarOperacion(EntradaCalipsoDesapropiacionWS entrada) 
			throws WebServiceExcepcion, WebServiceFormatoMensajeExcepcion {
		try {
			BigInteger idShiva = entrada.getIdOperacion();
			String usuCobrador = Constantes.SHIVA_APLICACION.toUpperCase();
			
			ListaFacArrayDesapropiacion listaFac = new ListaFacArrayDesapropiacion();
			List<ListaFacArrayDesapropiacion.Detalle> detalles = new ArrayList<ListaFacArrayDesapropiacion.Detalle>();
			for (DetalleFactura detFac: entrada.getListaFacturasADesapropiar()) {
				ListaFacArrayDesapropiacion.Detalle detalle = new ListaFacArrayDesapropiacion.Detalle();
				detalle.setIdCobranza(detFac.getIdCobranza());
				detalles.add(detalle);
			}
			listaFac.getDetalle().addAll(detalles);
			
//			Sprint 5
			List<ListaFacArrayDesapropiacion.DetalleCargos> listaDetalleCargos = new ArrayList<ListaFacArrayDesapropiacion.DetalleCargos>();
			for (DetalleCargoSalidaCobranzasWs detCargos: entrada.getListaCargosADesapropiar()) {
				ListaFacArrayDesapropiacion.DetalleCargos detalleCargo = new ListaFacArrayDesapropiacion.DetalleCargos();
				detalleCargo.setIdMovMer(detCargos.getIdMovMer());
				listaDetalleCargos.add(detalleCargo);
			}			
			listaFac.getDetalleCargos().addAll(listaDetalleCargos);
			
			ListaCtaCreArrayDesapropiacion listaCtaCre = new ListaCtaCreArrayDesapropiacion();
			List<ListaCtaCreArrayDesapropiacion.Detalle> detallesCtaCre = new ArrayList<ListaCtaCreArrayDesapropiacion.Detalle>();
			for (DetalleCTAoNotaCredito detCtaCre: entrada.getListaCtaONotaCreditoADesapropiar()) {
				ListaCtaCreArrayDesapropiacion.Detalle detalle = new ListaCtaCreArrayDesapropiacion.Detalle();
				detalle.setIdCobranza(detCtaCre.getIdCobranza());
				detallesCtaCre.add(detalle);
			}
			listaCtaCre.getDetalle().addAll(detallesCtaCre);
			
			Listaresultado respuesta = webServiceCalipsoCobranzasPortType.desapropiacion(idShiva, usuCobrador, listaFac, listaCtaCre);
			
			if (respuesta != null) {
				SalidaCalipsoDesapropiacionWS datos = new SalidaCalipsoDesapropiacionWS();
				
				datos.setIdOperacion(respuesta.getIdShiva());
				
				Resultado resultado = new Resultado();
				resultado.setResultado(respuesta.getResultado().getResultado());
				resultado.setCodigoError(respuesta.getResultado().getCodigoerror());
				resultado.setDescripcionError(respuesta.getResultado().getDescripcionerror());
				datos.setResultadoInvocacion(resultado);
				
				return datos;
			}
			throw new WebServiceExcepcion("WS CalipsoCobranzas Desapropiacion: Se ha producido un error en el webservice");
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS CalipsoCobranzas Desapropiacion - Error de formato: " + e);
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion("WS CalipsoCobranzas Desapropiacion: Falla de conexion: " + e);
		}
	}
	
	
	/*****************************************************************************
	 * Getters & Setters
	 *****************************************************************************/
	public WebServiceCalipsoCobranzasPortType getWebServiceCalipsoCobranzasPortType() {
		return webServiceCalipsoCobranzasPortType;
	}

	public void setWebServiceCalipsoCobranzasPortType(
			WebServiceCalipsoCobranzasPortType webServiceCalipsoCobranzasPortType) {
		this.webServiceCalipsoCobranzasPortType = webServiceCalipsoCobranzasPortType;
	}

}
