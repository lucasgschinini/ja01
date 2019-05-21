package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.AccionesSobreDiferenciaDeCambioEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ReintentoExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoSalidaCobranzasWs;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoNotaCreditoCalipsoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;

public class CobroBatchSoporteImputacionCalipsoServicioImpl extends Servicio implements ICobroBatchSoporteImputacionCalipsoServicio{

	public CobroBatchSoporteImputacionCalipsoServicioImpl() {
		
	}
	
	@Autowired 
	private IClienteCalipsoServicio clienteCalipsoServicio;
	
	@Autowired 
	private ICobroDao cobroDao;
	
	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;
	
	@Autowired
	private ICobroBatchSoporteServicio cobroBatchSoporteServicio;
	
	@Autowired
	private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	
	@Autowired
	private ITipoComprobanteDao tipoComprobanteDao; 
	

	/**
	 * Prepara el mensaje a Calipso y lo agrega a JmsMonitorMensajeria.
	 * Ademas inserta en las tablas de MensariaFactura y mensajeriaMediosPago.
	 * @param listaMediosPagoAEnviar
	 * @param factura
	 * @throws NegocioExcepcion 
	 * @throws ReintentoExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	public Boolean apropiacionCalipso(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, 
			ShvCobFacturaSinOperacion factura, ShvCobTransaccionSinOperacion transaccion, Date fechaCobranza, MonedaEnum monedaOperacion) 
					throws NegocioExcepcion, ReintentoExcepcion, PersistenciaExcepcion {
		
		EntradaCalipsoApropiacionWS eEntrada = new EntradaCalipsoApropiacionWS();
		TipoInvocacionEnum tipoInvocacion = null;
		
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		eEntrada.setModoOperacion(SiNoEnum.NO);
		eEntrada.setMonedaOperacion(monedaOperacion);
		eEntrada.setFechaCobranza(fechaCobranza);
		
		if(!Validaciones.isObjectNull(factura)){
			
			eEntrada.setIdOperacion(transaccion.getIdOperacion());
			eEntrada.setIdTransaccion(transaccion.getIdTransaccion());
			eEntrada.setNumeroTransaccion(transaccion.getNumeroTransaccion());
			
			// Armo mensaje apropiacion con factura
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA);
			tipoInvocacion = TipoInvocacionEnum.$01; 
			
			ShvCobFacturaCalipsoSinOperacion facturaCalipso = (ShvCobFacturaCalipsoSinOperacion) factura;
			DetalleFactura detalleFactura = new DetalleFactura();
			IdDocumento idDocumento = new IdDocumento();
			idDocumento.setTipoComprobante(facturaCalipso.getTipoComprobante());
			idDocumento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(facturaCalipso.getClaseComprobante())));
			idDocumento.setSucursalComprobante(facturaCalipso.getSucursalComprobante());
			idDocumento.setNumeroComprobante(facturaCalipso.getNumeroComprobante());
			
			detalleFactura.setIdDocumento(idDocumento);
			detalleFactura.setMontoACancelarEnPesos(facturaCalipso.getImporteCobrar());
			
			// Si la factura es de Calipso, el “Pago Parcial Cancelatorio” se enviará como “Pago Total”,
			// siempre y cuando la moneda del cobro se corresponda con la moneda del documento.
			// Caso contrario, el tipo de pago se calcula en base a la respuesta del servicio, y en esta llamada se enviará vacío.
			
			if (monedaOperacion.equals(facturaCalipso.getMoneda())) {
				if (TipoPagoEnum.PAGO_PARCIAL_CANCELATORIO.equals(facturaCalipso.getTipoPago())) {
					detalleFactura.setTipoOperacion(TipoPagoEnum.PAGO_TOTAL);	
				} else {
					detalleFactura.setTipoOperacion(facturaCalipso.getTipoPago());	
				}
			}
			
			detalleFactura.setTipoMora(facturaCalipso.getQueHacerConIntereses()!=null?
			facturaCalipso.getQueHacerConIntereses().getCodigoCalipsoApropiacion():null);
			detalleFactura.setImporteBonificacionIntereses(facturaCalipso.getImporteBonificacionIntereses());

			// Solo seteo el acuerdo en la llamada a Calipso, si existen intereses generados
			if (!Validaciones.isObjectNull(facturaCalipso.getCobradorInteresesTrasladados()) && facturaCalipso.getCobradorInteresesTrasladados().compareTo(BigDecimal.ZERO) > 0) {
				detalleFactura.setAcuerdoFacturacion(facturaCalipso.getAcuerdoTrasladoCargo());
			}
			
			detalleFactura.setAccionSobreDiferenciaDeCambio(AccionesSobreDiferenciaDeCambioEnum.NA);
			eEntrada.setDetalleFactura(detalleFactura);
			
			if(Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)){
				
				List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();
				
				for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoAEnviar) {
					// Armo mensaje apropiacion con factura y Medios de pago
					eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP);
					tipoInvocacion = TipoInvocacionEnum.$03;
					
					eEntrada.setIdOperacion(factura.getTransaccion().getIdOperacion());
					eEntrada.setIdTransaccion(factura.getTransaccion().getIdTransaccion());
					eEntrada.setNumeroTransaccion(factura.getTransaccion().getNumeroTransaccion());
					eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
					
					IdDocumento idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
					if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
						
						ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medioPagoNotaCreditoCalipso 
							= (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;
						
						DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
						detalleNotaCredito.setImporte(medioPagoNotaCreditoCalipso.getImporte());
						idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
						idDocumentoMedioPagoNotaCreditoCalipso.setTipoComprobante(TipoComprobanteEnum.CRE);
						idDocumentoMedioPagoNotaCreditoCalipso.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoNotaCreditoCalipso.getClaseComprobante())));
						idDocumentoMedioPagoNotaCreditoCalipso.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
						idDocumentoMedioPagoNotaCreditoCalipso.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
						detalleNotaCredito.setIdDocumento(idDocumentoMedioPagoNotaCreditoCalipso);
						listaCtaONotaCredito.add(detalleNotaCredito);
					}else{
						if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
							
							ShvCobMedioPagoCTASinOperacion medioPagoCTA = (ShvCobMedioPagoCTASinOperacion)medioPago;
							
							DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
							detalleNotaCredito.setImporte(medioPagoCTA.getImporte());
							idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
							idDocumentoMedioPagoNotaCreditoCalipso.setTipoComprobante(TipoComprobanteEnum.CTA);
							idDocumentoMedioPagoNotaCreditoCalipso.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoCTA.getClaseComprobante())));
							idDocumentoMedioPagoNotaCreditoCalipso.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
							idDocumentoMedioPagoNotaCreditoCalipso.setNumeroComprobante(medioPagoCTA.getNroComprobante());
							detalleNotaCredito.setIdDocumento(idDocumentoMedioPagoNotaCreditoCalipso);
							listaCtaONotaCredito.add(detalleNotaCredito);
							
						}
					}
				}
				
				eEntrada.setListaCtaONotaCredito(listaCtaONotaCredito);
				
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(eEntrada.getIdOperacion(), eEntrada.getIdTransaccion(), eEntrada.getNumeroTransaccion(), 
						SistemaEnum.CALIPSO, tipoInvocacion)) 
				{
					Traza.advertencia(getClass(), "Se envia la apropiacion de la operacion id: "+eEntrada.getIdOperacion()
							+" a Calipso con tipo de apropiacion: " + eEntrada.getTipoApropiacion().getDescripcion());
					
					// -> enviar mensaje a CALIPSO con factura y/o Medios de pago
					SalidaCalipsoApropiacionWS respuesta = clienteCalipsoServicio.apropiarCobro(eEntrada);
					
					if(!Validaciones.isObjectNull(respuesta)){
						Traza.advertencia(getClass(), "El resultado de la respuesta de Calipso ("+eEntrada.getIdOperacion()+") "
								+ "fue: " + respuesta.getResultadoInvocacion().getResultado());
						
						String estadoRespuesta = respuesta.getResultadoInvocacion().getResultado(); //OK / ERR
						if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
								&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuesta.getResultadoInvocacion().getCodigoError())) {
							estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
							Traza.auditoria(getClass(), "Metodo "+MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP.getDescripcion()
									+" ("+eEntrada.getIdOperacion()+"."+eEntrada.getNumeroTransaccion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
						}
						
						// guardar datos de la respuesta de calipso
						
						setearDatosDeRespuestaAFacturaCalipso(transaccion,respuesta);
						
						cargarIdCobranzasEnMediosPagosDeCalipso(listaMediosPagoAEnviar, respuesta, transaccion);
						
						if(Validaciones.isCollectionNotEmpty(respuesta.getListaNotasCreditoODebito())){
							// si la lista tiene algun elemento crear una factura.
							crearFacturaCalipsoYAsociarla(respuesta,factura.getTransaccion().getNumeroTransaccion(),factura.getIdCobro(),factura.getTransaccion().getIdTransaccion(),listaMediosPagoAEnviar);
						}
						
						if(Validaciones.isCollectionNotEmpty(respuesta.getListaCTAoNotaDeCredito())){
							// si la lista tiene algun elemento buscar el medio de pago y crear el nuevo medio de pago.
							crearNotasCreditoODebitoCalipsoYAsociarla(respuesta.getListaCTAoNotaDeCredito(), transaccion);
						}
						
						if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuesta)){
							
							if (cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion)){
								
								return true;
							}
						}
					}
				} // fin puedeEnviarMensaje a calipso (factura y Medios de pago)
				
			}else{
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(eEntrada.getIdOperacion(), eEntrada.getIdTransaccion(), eEntrada.getNumeroTransaccion(), 
						SistemaEnum.CALIPSO, tipoInvocacion)) 
				{
					// -> enviar mensaje a CALIPSO con solo la factura
					Traza.advertencia(getClass(), "Se envia la apropiacion de la operacion id: "+ eEntrada.getIdOperacion()
							+" a Calipso con tipo de apropiacion: " + eEntrada.getTipoApropiacion().getDescripcion());
					SalidaCalipsoApropiacionWS respuestaSoloFactura = clienteCalipsoServicio.apropiarCobro(eEntrada);
					
					if(!Validaciones.isObjectNull(respuestaSoloFactura)){
						Traza.advertencia(getClass(), "El resultado de la respuesta de Calipso ("+eEntrada.getIdOperacion()+") "
								+ "fue: " + respuestaSoloFactura.getResultadoInvocacion().getResultado());
						
						String estadoRespuesta = respuestaSoloFactura.getResultadoInvocacion().getResultado(); //OK / ERR
						if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
								&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuestaSoloFactura.getResultadoInvocacion().getCodigoError())) {
							estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
							Traza.auditoria(getClass(), "Metodo "+MensajeServicioEnum.CLP_APROP_DEUDA.getDescripcion()
									+" ("+eEntrada.getIdOperacion()+"."+eEntrada.getNumeroTransaccion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
						}
						
						setearDatosDeRespuestaAFacturaCalipso(transaccion,respuestaSoloFactura);
						
						
						if(Validaciones.isCollectionNotEmpty(respuestaSoloFactura.getListaNotasCreditoODebito())){
							// si la lista tiene algun elemento crear una factura.
							crearFacturaCalipsoYAsociarla(respuestaSoloFactura,factura.getTransaccion().getNumeroTransaccion(),factura.getIdCobro(), factura.getTransaccion().getIdTransaccion(),listaMediosPagoAEnviar);
						}
						
						if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuesta)){
							if (cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion)){
							
								return true;
							}
						}
					}
				} // fin puedeEnviarMensaje a calipso (factura)
			}
		
		}else{
			if(Validaciones.isCollectionNotEmpty(listaMediosPagoAEnviar)){
				ShvCobTransaccionSinOperacion transaccionAEnviar = listaMediosPagoAEnviar.get(0).getTransaccion();
				
				// Armo mensaje apropiacion con Medios de pago
				eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_APROP_MP);
				tipoInvocacion = TipoInvocacionEnum.$02;
				
				eEntrada.setIdOperacion(transaccionAEnviar.getIdOperacion());
				eEntrada.setIdTransaccion(transaccionAEnviar.getIdTransaccion());
				eEntrada.setNumeroTransaccion(transaccionAEnviar.getNumeroTransaccion());
				eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
				eEntrada.setModoOperacion(SiNoEnum.NO);
				
				//Ahi envio vacio el detalle de factur
				eEntrada.setDetalleFactura(null);
				
				List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();
				
				for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoAEnviar) {
					// Armo mensaje apropiacion con Medios de pago
					
					eEntrada.setIdOperacion(medioPago.getTransaccion().getIdOperacion());
					eEntrada.setIdTransaccion(medioPago.getTransaccion().getIdTransaccion());
					eEntrada.setNumeroTransaccion(medioPago.getTransaccion().getNumeroTransaccion());
					eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
					IdDocumento idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
					
					if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
						ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;
						
						DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
						detalleNotaCredito.setImporte(medioPagoNotaCreditoCalipso.getImporte());
						idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
						idDocumentoMedioPagoNotaCreditoCalipso.setTipoComprobante(TipoComprobanteEnum.CRE);
						idDocumentoMedioPagoNotaCreditoCalipso.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoNotaCreditoCalipso.getClaseComprobante())));
						idDocumentoMedioPagoNotaCreditoCalipso.setSucursalComprobante(medioPagoNotaCreditoCalipso.getSucursalComprobante());
						idDocumentoMedioPagoNotaCreditoCalipso.setNumeroComprobante(medioPagoNotaCreditoCalipso.getNroComprobante());
						detalleNotaCredito.setIdDocumento(idDocumentoMedioPagoNotaCreditoCalipso);
						listaCtaONotaCredito.add(detalleNotaCredito);
					}else{
						if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
							ShvCobMedioPagoCTASinOperacion medioPagoCTA = (ShvCobMedioPagoCTASinOperacion)medioPago;
							
							DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
							detalleNotaCredito.setImporte(medioPagoCTA.getImporte());
							idDocumentoMedioPagoNotaCreditoCalipso = new IdDocumento();
							idDocumentoMedioPagoNotaCreditoCalipso.setTipoComprobante(TipoComprobanteEnum.CTA);
							idDocumentoMedioPagoNotaCreditoCalipso.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(String.valueOf(medioPagoCTA.getClaseComprobante())));
							idDocumentoMedioPagoNotaCreditoCalipso.setSucursalComprobante(medioPagoCTA.getSucursalComprobante());
							idDocumentoMedioPagoNotaCreditoCalipso.setNumeroComprobante(medioPagoCTA.getNroComprobante());
							detalleNotaCredito.setIdDocumento(idDocumentoMedioPagoNotaCreditoCalipso);
							listaCtaONotaCredito.add(detalleNotaCredito);
							
						}
					}
				}
				
				eEntrada.setListaCtaONotaCredito(listaCtaONotaCredito);
				
				if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(eEntrada.getIdOperacion(), eEntrada.getIdTransaccion(), eEntrada.getNumeroTransaccion(), 
						SistemaEnum.CALIPSO, tipoInvocacion)) 
				{
					// -> enviar mensaje a CALIPSO con solo medios de pago
					Traza.advertencia(getClass(), "Se envia la apropiacion de la operacion id: "+eEntrada.getIdOperacion()
							+" a Calipso con tipo de apropiacion: " + eEntrada.getTipoApropiacion().getDescripcion());
					SalidaCalipsoApropiacionWS respuesta = clienteCalipsoServicio.apropiarCobro(eEntrada);
					
					if(!Validaciones.isObjectNull(respuesta)){
						Traza.advertencia(getClass(), "El resultado de la respuesta de Calipso ("+eEntrada.getIdOperacion()+") "
								+ "fue: " + respuesta.getResultadoInvocacion().getResultado());
						
						String estadoRespuesta = respuesta.getResultadoInvocacion().getResultado(); //OK / ERR
						if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
								&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuesta.getResultadoInvocacion().getCodigoError())) {
							estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
							Traza.auditoria(getClass(), "Metodo "+MensajeServicioEnum.CLP_APROP_MP.getDescripcion()
									+" ("+eEntrada.getIdOperacion()+"."+eEntrada.getNumeroTransaccion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
						}
						
						cargarIdCobranzasEnMediosPagosDeCalipso(listaMediosPagoAEnviar, respuesta, transaccion);
						
						if(Validaciones.isCollectionNotEmpty(respuesta.getListaCTAoNotaDeCredito())){
							// si la lista tiene algun elemento buscar el medio de pago y crear el nuevo medio de pago.
							crearNotasCreditoODebitoCalipsoYAsociarla(respuesta.getListaCTAoNotaDeCredito(), transaccion);
						}
						
						if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(estadoRespuesta)){
							if (cobroBatchSoporteImputacionServicio.apropiacionDeimos(transaccion)){
								return true;
							}
						}
					}
				} // fin puedeEnviarMensaje a calipso (Medios de pago)
			}
		}
		return false;
	}
	
	/**
	 * Carga los datos de la respuesta de Calipso a la factura de la transaccion
	 * @param cobro
	 * @param respuesta
	 * @param numeroTransaccion
	 * @return
	 */
	private void setearDatosDeRespuestaAFacturaCalipso(ShvCobTransaccionSinOperacion transaccion, 
			SalidaCalipsoApropiacionWS respuesta) throws NegocioExcepcion{

		
		ShvCobFacturaSinOperacion factura = transaccion.getFactura();
		//u562163 - Defectos 586, 589, 593, 620
		if(TipoResultadoEnum.OK.getDescripcionCalipso().equals(respuesta.getResultadoInvocacion().getResultado())){
            factura.setCobradorInteresesGenerados(respuesta.getDetalleFactura().getMontoCuenta());
            factura.setCobradorInteresesTrasladados(respuesta.getDetalleFactura().getMontoCalculadoMora());
		}
		
		factura.setIdCobranza(respuesta.getDetalleFactura().getIdCobranza() != null ? respuesta.getDetalleFactura().getIdCobranza().longValue() : null);
		((ShvCobFacturaCalipsoSinOperacion)factura).setCobradorMontoACuenta(respuesta.getDetalleFactura().getMontoCuenta());
		((ShvCobFacturaCalipsoSinOperacion)factura).setTipoDeCambioFechaCobro((respuesta.getDetalleFactura().getTipoCambioFechaCobro()));
		((ShvCobFacturaCalipsoSinOperacion)factura).setTipoDeCambioFechaEmision((respuesta.getDetalleFactura().getTipoCambioFechaEmision()));
		((ShvCobFacturaCalipsoSinOperacion)factura).setImporteAplicadoAFechaEmisionMonedaPesos(respuesta.getDetalleFactura().getImporteAplicadoFechaEmisionPesos());
		((ShvCobFacturaCalipsoSinOperacion)factura).setImporteAplicadoAFechaEmisionMonedaOrigen(respuesta.getDetalleFactura().getImporteAplicadoMonedaOrigen());
		
		boolean existeErrorONOK = false;
		boolean apropiarDeimos = false;
		boolean existeOK = false;
		boolean existeWRN = false;

		StringBuffer detalleMensajeError   = new StringBuffer(Constantes.EMPTY_STRING);
		StringBuffer detalleMensajeWarning = new StringBuffer(Constantes.EMPTY_STRING);
		
		for (ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado resultado : respuesta.getDetalleFactura().getListaResultadoApropiacion()){
			if ((TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(resultado.getResultado())
				  &&  !Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(resultado.getCodigoError()))
				||	TipoResultadoEnum.NOK.getDescripcionCalipso().equals(resultado.getResultado())){

				existeErrorONOK = true;

				detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
				detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
				detalleMensajeError.append(resultado.getDescripcionError().toString().trim());
				
				break;
			} else if (TipoResultadoEnum.WRN.getDescripcionCalipso().equals(resultado.getResultado())) {
					if (Constantes.MSJ_COD_RESP_DOCUMENTO_MIGRADO_DEIMOS.equals(resultado.getCodigoError())){
						apropiarDeimos = true;
					} else if (!Constantes.MSJ_COD_RESP_SALDO_RESTANTE_SIN_SALDAR.equals(resultado.getCodigoError())){
						
						existeWRN = true;
						
						detalleMensajeWarning.append(TipoMensajeEstadoEnum.WRN.getDescripcion());
						detalleMensajeWarning.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeWarning.append(resultado.getDescripcionError().toString().trim());
					}
					
			} else if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(resultado.getResultado())
					||	(TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(resultado.getResultado())
							  &&  Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(resultado.getCodigoError()))){
				existeOK = true;
				factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
			}
			
		}
		
		if (existeErrorONOK) {
			factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
			factura.setMensajeEstado(detalleMensajeError.toString() );
			
			factura.setEstado(EstadoFacturaMedioPagoEnum.ERROR);

			Traza.advertencia(getClass(), "Se realizo el cambio de estado de la factura " 
					+ factura.getEstado().descripcion() + " correspondientes a la operacion id: " + factura.getTransaccion().getIdOperacion());
			
		}
		
		if (apropiarDeimos) {
			factura.setMigradoDeimos(SiNoEnum.SI);
		}
		
		if (existeWRN) {
			factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
			factura.setMensajeEstado(detalleMensajeWarning.toString());
			factura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
			Traza.advertencia(getClass(), "Se realizo el cambio de estado de la factura a " 
					+ factura.getEstado().descripcion() + " correspondientes a la operacion id: " + factura.getTransaccion().getIdOperacion());
		}
		
		if (existeOK) {
			factura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
			
			Traza.advertencia(getClass(), "Se realizo el cambio de estado de la factura a " 
					+ factura.getEstado().descripcion() + " correspondientes a la operacion id: " + factura.getTransaccion().getIdOperacion());
		}
		
		for (ShvCobMedioPagoSinOperacion mp : transaccion.getMediosPago()){
			
			if (mp instanceof ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion){
			
				try {
					if (EstadoFacturaMedioPagoEnum.APROPIADA.equals(factura.getEstado())){
						cobroBatchSoporteImputacionServicio.generarCargosOReintegro(transaccion,null);
					}
				
				} catch (NegocioExcepcion | ReintentoExcepcion e) {
					throw new NegocioExcepcion (e.getMessage());
				}
			
			} else if (mp instanceof ShvCobMedioPagoUsuarioSinOperacion
					&& !TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())
					&& !TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())
					&& !TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(mp.getTipoMedioPago().getDescripcion())){
			
				mp.setEstado(factura.getEstado());
			}
		}	
	
	}
	
	
	/**
	 * Carga todos los id cobranzas en los medios de pago de calipso y setea la marca migrado Deimos en los medio de pago
	 * @param listaMediosPagoAEnviar
	 * @param mensajeMIC
	 */
	private void cargarIdCobranzasEnMediosPagosDeCalipso(List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar, 
			SalidaCalipsoApropiacionWS respuesta, ShvCobTransaccionSinOperacion transaccion) {
		
		// Seteo el id de cobranza a los medios de pago y seteo la marca Migrado Deimos en los medios de pago
		if (Validaciones.isCollectionNotEmpty(respuesta.getListaCTAoNotaDeCredito())) {
			for (DetalleCTAoNotaCredito detalleCTAoNC: respuesta.getListaCTAoNotaDeCredito()) {
				
				Boolean encontrado = false;
				BigInteger idCobranza = detalleCTAoNC.getIdCobranza();
					
				for (ShvCobMedioPagoSinOperacion medioPago : listaMediosPagoAEnviar) {
					ShvCobMedioPagoSinOperacion medioPagoAux = transaccion.getMedioPagoPorIdMedioPago(medioPago.getIdMedioPago());
					
					if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
						ShvCobMedioPagoCTASinOperacion medioPagoCTA = (ShvCobMedioPagoCTASinOperacion) medioPagoAux;
						// Comparar
						if(detalleCTAoNC.getIdDocumento().getTipoComprobante().equals(medioPagoCTA.getTipoComprobante()) &&
								detalleCTAoNC.getIdDocumento().getClaseComprobante().equals(medioPagoCTA.getClaseComprobante()) &&
								detalleCTAoNC.getIdDocumento().getSucursalComprobante().equals(medioPagoCTA.getSucursalComprobante()) &&
								detalleCTAoNC.getIdDocumento().getNumeroComprobante().equals(medioPagoCTA.getNroComprobante())){
							medioPagoCTA.setIdCobranza(idCobranza != null ? idCobranza.longValue():0);
							encontrado = true;
							
							cobroBatchSoporteImputacionServicio.setearEstadoMedioPago(medioPago,detalleCTAoNC,transaccion.getIdOperacion() );
							Traza.advertencia(getClass(), "Se actualizo el id de cobranza del medio de pago CTA con id " + medioPagoAux.getIdMedioPago()  + " con el valor: " + idCobranza
									+ " correspondiente a la operacion id: " + transaccion.getIdOperacion() + "." + medioPagoAux.getTransaccion().getNumeroTransaccion());
							break;
						}
					}else{
						if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
							ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medioPagoNotaCreditoCalipso 
								= (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion) medioPagoAux;
							// Comparar
							if(detalleCTAoNC.getIdDocumento().getTipoComprobante().equals(medioPagoNotaCreditoCalipso.getTipoComprobante()) &&
									detalleCTAoNC.getIdDocumento().getClaseComprobante().equals(medioPagoNotaCreditoCalipso.getClaseComprobante()) &&
									detalleCTAoNC.getIdDocumento().getSucursalComprobante().equals(medioPagoNotaCreditoCalipso.getSucursalComprobante()) &&
									detalleCTAoNC.getIdDocumento().getNumeroComprobante().equals(medioPagoNotaCreditoCalipso.getNroComprobante())){
								medioPagoNotaCreditoCalipso.setIdCobranza(idCobranza != null ? idCobranza.longValue():0);
								encontrado = true;
								cobroBatchSoporteImputacionServicio.setearEstadoMedioPago(medioPago,detalleCTAoNC,transaccion.getIdOperacion());
								Traza.advertencia(getClass(), "Se actualizo el id de cobranza del medio de pago Nota de Credito con id " + medioPagoAux.getIdMedioPago() + " con el valor: " + idCobranza
										+ " correspondiente a la operacion id: " + transaccion.getIdOperacion() + "." + medioPagoAux.getTransaccion().getNumeroTransaccion());
								break;
							}
						}
					}
					if (!encontrado) { 
						Traza.advertencia(getClass(), "No se encontro ningun medio de pago CTA o Nota de credito con " 
								+ " tipo Comprobante: " + detalleCTAoNC.getIdDocumento().getTipoComprobante()
								+ " clase Comprobante: " + detalleCTAoNC.getIdDocumento().getClaseComprobante()
								+ " sucursal Comprobante: " + detalleCTAoNC.getIdDocumento().getSucursalComprobante()
								+ " numero Comprobante: " + detalleCTAoNC.getIdDocumento().getNumeroComprobante()
								+ " correspondientes a la operacion id: " + transaccion.getIdOperacion() + "." + medioPago.getTransaccion().getNumeroTransaccion());
					}
				}
			}
		} else {
			Traza.advertencia(getClass(), "No se envio ningun Medio de pago Calipso a apropiar para la operacion id: " + transaccion.getIdOperacion());
		}
	}
	
	
	/**
	 * Crea una factura a partir de la respuesta de calipso y la asocia a la
	 * factura que ya existia.
	 * @param respuestaSoloFactura
	 * @param transaccion
	 * @throws PersistenciaExcepcion 
	 */
	private void crearFacturaCalipsoYAsociarla(SalidaCalipsoApropiacionWS respuesta,Integer numeroTransaccion, Long idCobro, Integer idTransaccion, List<ShvCobMedioPagoSinOperacion> listaMediosPagoAEnviar)throws NegocioExcepcion, PersistenciaExcepcion{

		Collection<ShvCobTransaccion> nuevasTransaccionesPorDifCambio = null;
		
		ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
		//Elimino las transacciones que se crearon en la simulacion x dif de cambio
		cobro = cobroBatchSoporteImputacionServicio.eliminarTransaccionesEnEstadoDiferenciaDeCambioSim(cobro);

		ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(idTransaccion);
		
		ShvCobFactura factura = transaccion.getFactura();
		
		try {
			nuevasTransaccionesPorDifCambio = new ArrayList<ShvCobTransaccion>();
			DetalleNotaCreditoDebito notaCreditooDebito = respuesta.getListaNotasCreditoODebito().get(0);
			IdDocumento documento = notaCreditooDebito.getCtaoNotaCredito();
			
			if (TipoComprobanteEnum.CRE.equals(documento.getTipoComprobante())) {
				
				ShvCobMedioPagoNotaCreditoCalipso notaCredito = new ShvCobMedioPagoNotaCreditoCalipso();

				notaCredito.setTipoComprobante(documento.getTipoComprobante());
				notaCredito.setClaseComprobante(documento.getClaseComprobante());
				notaCredito.setSucursalComprobante(documento.getSucursalComprobante());
				notaCredito.setNroComprobante(documento.getNumeroComprobante());
				
				notaCredito.setFechaVencimiento(notaCreditooDebito.getFechaVencimiento());
				notaCredito.setFechaEmision(notaCreditooDebito.getFechaVencimiento());
				
				notaCredito.setIdClienteLegado(factura.getIdClienteLegado());
				
				notaCredito.setImporte(notaCreditooDebito.getImporte());
				notaCredito.setIdCobranza(notaCreditooDebito.getIdCobranza().longValue());
				notaCredito.setImporteCapital(notaCreditooDebito.getImporteCapital());
				notaCredito.setImporteImpuestos(notaCreditooDebito.getImporteImpuestos());
				
				//Dolares
				notaCredito.setMoneda(notaCreditooDebito.getMoneda());
				
				notaCredito.setIdDocumentoCuentaCobranza(notaCreditooDebito.getIdDocumentoCuentasCobranza().longValue());
				notaCredito.setImporteAplicado(notaCreditooDebito.getImporteAplicado());
				notaCredito.setOrigenDocumento(notaCreditooDebito.getOrigenDelDocumento());
				notaCredito.setRazonSocialClienteLegado(notaCreditooDebito.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
				notaCredito.setTipoCliente(notaCreditooDebito.getInformacionAdicionalTagetikCalipso().getTipoCliente());
				notaCredito.setUnidadOperativa(notaCreditooDebito.getInformacionAdicionalDacota().getUnidadOperativa());
				notaCredito.setSubtipo(notaCreditooDebito.getInformacionAdicionalDacota().getSubTipo());
				notaCredito.setHolding(notaCreditooDebito.getInformacionAdicionalDacota().getHolding());
				notaCredito.setTipoMedioPago(tipoMedioPagoDao.buscarMedioPago(TipoMedioPagoEnum.NOTA_CREDITO));
				notaCredito.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
				notaCredito.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				notaCredito.setSistemaOrigen(SistemaEnum.CALIPSO);
				notaCredito.setIdDocumentoCuentaCobranzaPadre(((ShvCobFacturaCalipso)factura).getIdDocumentoCuentaCobranza());
				notaCredito.setGeneradoPorCobro(SiNoEnum.SI);
				notaCredito.setIdCobro(cobro.getIdCobro());
				
				//MonedaOperacion
				notaCredito.setMonedaImporte(cobro.getMonedaOperacion());
				
				ShvCobTransaccion transaccionDifCambio = new ShvCobTransaccion();
				transaccionDifCambio.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO);
				transaccionDifCambio.setNumeroTransaccion(factura.getTransaccion().getOperacion().getTransacciones().size() + 1);
				transaccionDifCambio.setOperacion(factura.getTransaccion().getOperacion());
				//se reemplaza el idTransaccionPadre por el numero de transaccion
				transaccionDifCambio.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());
				
				transaccionDifCambio.setGrupo(factura.getTransaccion().getGrupo());
				transaccionDifCambio.setIdSociedad(factura.getSociedad());
				transaccionDifCambio.setSistema(factura.getSistemaOrigen());
				transaccionDifCambio.setTipoImputacion(TipoImputacionEnum.AUTOMATICA); 
				
				transaccionDifCambio.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				transaccionDifCambio.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				
				// Copio la factura, le seteo el importe aplicado en Calipso, y la agrego a la nueva transaccion
				ShvCobFacturaCalipso copiaFactura = (ShvCobFacturaCalipso) Utilidad.clonarObjeto(factura);
				copiaFactura.setIdFactura(null);
				copiaFactura.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
				copiaFactura.setImporteCobrar(copiaFactura.getImporteAplicadoAFechaEmisionMonedaPesos());
				copiaFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				copiaFactura.setTransaccion(transaccionDifCambio);
				
				// Limpio campos que no deben ser informados en la copia de la Factura
				cobroBatchSoporteServicio.limpiarCamposCopiaDocumentoAplicacionPorDifCambio(copiaFactura);
				
				transaccionDifCambio.getShvCobFactura().add(copiaFactura);

				// Copio el/los medios de pago y los agrego a la transaccion
				for (ShvCobMedioPago medioPagoOriginal : factura.getTransaccion().getShvCobMedioPago()) {
					ShvCobMedioPago copiaMedioPago = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoOriginal);
					copiaMedioPago.setIdMedioPago(null);
					copiaMedioPago.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
					copiaMedioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					copiaMedioPago.setTransaccion(transaccionDifCambio);
					
					copiaMedioPago = cobroBatchSoporteImputacionServicio.clonarListaDeClientesMedioPagoUsuario(medioPagoOriginal, copiaMedioPago);
					
					transaccionDifCambio.getShvCobMedioPago().add(copiaMedioPago);
				}

				// Agrego la nota de credito generada en el cobrador
				notaCredito.setTransaccion(transaccionDifCambio);
				transaccionDifCambio.getShvCobMedioPago().add(notaCredito);
				
				nuevasTransaccionesPorDifCambio = new ArrayList<ShvCobTransaccion>();
				nuevasTransaccionesPorDifCambio.add(transaccionDifCambio);
				
			} else if (TipoComprobanteEnum.DEB.equals(documento.getTipoComprobante())) {
				
				// Genero una transaccion que contendrá la nota
				// de debito
				ShvCobTransaccion transaccionDifCambioNotaDebitoGenerada = new ShvCobTransaccion();
				transaccionDifCambioNotaDebitoGenerada.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO);
				transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccion(factura.getTransaccion().getOperacion().getTransacciones().size() + 1);
				transaccionDifCambioNotaDebitoGenerada.setOperacion(factura.getTransaccion().getOperacion());
				// se reemplaza el idTransaccionPadre por el numero de transaccion
				transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());

				transaccionDifCambioNotaDebitoGenerada.setGrupo(factura.getTransaccion().getGrupo());
				transaccionDifCambioNotaDebitoGenerada.setIdSociedad(factura.getSociedad());
				transaccionDifCambioNotaDebitoGenerada.setSistema(factura.getSistemaOrigen());
				transaccionDifCambioNotaDebitoGenerada.setTipoImputacion(TipoImputacionEnum.AUTOMATICA); 
				
				transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				transaccionDifCambioNotaDebitoGenerada.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				
				ShvCobFacturaCalipso facturaNuevaNotaDebito = new ShvCobFacturaCalipso();
				facturaNuevaNotaDebito.setIdFactura(null);
				facturaNuevaNotaDebito.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
				
				facturaNuevaNotaDebito.setSociedad(SociedadEnum.TELECOM);
				
				//Id de cobranza
				facturaNuevaNotaDebito.setIdCobranza(notaCreditooDebito.getIdCobranza().longValue());
				
				//Importe
				facturaNuevaNotaDebito.setImporteCobrar(notaCreditooDebito.getImporteAplicado());
				facturaNuevaNotaDebito.setImporteOriginal(notaCreditooDebito.getImporte());
				facturaNuevaNotaDebito.setImporteCapital(notaCreditooDebito.getImporteCapital());
				facturaNuevaNotaDebito.setImporteImpuestos(notaCreditooDebito.getImporteImpuestos());
				
				//Dolares
				facturaNuevaNotaDebito.setMoneda(notaCreditooDebito.getMoneda());
				
				//ID nota de debito
				facturaNuevaNotaDebito.setTipoComprobante(tipoComprobanteDao.buscarComprobante(documento.getTipoComprobante()));
				facturaNuevaNotaDebito.setClaseComprobante(documento.getClaseComprobante());
				facturaNuevaNotaDebito.setSucursalComprobante(documento.getSucursalComprobante());
				facturaNuevaNotaDebito.setNumeroComprobante(documento.getNumeroComprobante());
				
				//ID Documento Cuentas Cobranza
				facturaNuevaNotaDebito.setIdDocumentoCuentaCobranza(notaCreditooDebito.getIdDocumentoCuentasCobranza().longValue());
				facturaNuevaNotaDebito.setIdDocumentoCuentaCobranzaPadre(((ShvCobFacturaCalipso)factura).getIdDocumentoCuentaCobranza());
				//Fecha de vencimiento
				facturaNuevaNotaDebito.setFechaVencimiento(notaCreditooDebito.getFechaVencimiento());
				
				//Fecha Valor
				facturaNuevaNotaDebito.setFechaValor(new Date());
				//Importe aplicado
				facturaNuevaNotaDebito.setImporteAplicado(notaCreditooDebito.getImporteAplicado());
				
				//Origen del documento
				facturaNuevaNotaDebito.setOrigenDocumento(notaCreditooDebito.getOrigenDelDocumento());
				
				//Informacion adicional tagetik
				facturaNuevaNotaDebito.setRazonSocialClienteLegado(notaCreditooDebito.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
				facturaNuevaNotaDebito.setTipoCliente(notaCreditooDebito.getInformacionAdicionalTagetikCalipso().getTipoCliente());
				facturaNuevaNotaDebito.setCuit(notaCreditooDebito.getInformacionAdicionalTagetikCalipso().getCuit());
				
				//Información adicional Dacota
				facturaNuevaNotaDebito.setUnidadOperativa(notaCreditooDebito.getInformacionAdicionalDacota().getUnidadOperativa());
				facturaNuevaNotaDebito.setSubtipo(notaCreditooDebito.getInformacionAdicionalDacota().getSubTipo());
				facturaNuevaNotaDebito.setHolding(notaCreditooDebito.getInformacionAdicionalDacota().getHolding());
				
				facturaNuevaNotaDebito.setGeneradoPorCobro(SiNoEnum.SI);
				facturaNuevaNotaDebito.setSaldoActual(notaCreditooDebito.getImporte());
				facturaNuevaNotaDebito.setIdClienteLegado(factura.getIdClienteLegado());
				facturaNuevaNotaDebito.setIdFacturaCalipsoPadre(factura.getIdFactura());
				facturaNuevaNotaDebito.setMoneda(MonedaEnum.PES);
				facturaNuevaNotaDebito.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				facturaNuevaNotaDebito.setSistemaOrigen(SistemaEnum.CALIPSO);
				facturaNuevaNotaDebito.setTransaccion(transaccionDifCambioNotaDebitoGenerada);
				//MonedaOperacion
				facturaNuevaNotaDebito.setMonedaImporteCobrar(cobro.getMonedaOperacion());
				facturaNuevaNotaDebito.setIdCobro(factura.getIdCobro());
				
				// Ante una diferencia de cambio, puede llegar el mensaje 8077 a nivel factura
				// pero que solo debemos mostrar a nivel de nota de debito por diferencia de cambio, ya que es un mensaje propio de esta
				
				for (ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado resultado : respuesta.getDetalleFactura().getListaResultadoApropiacion()) {

					if (TipoResultadoEnum.WRN.getDescripcionCalipso().equals(resultado.getResultado())
							&& Constantes.MSJ_COD_RESP_SALDO_RESTANTE_SIN_SALDAR.equals(resultado.getCodigoError())) {
						
						StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);
						detalleMensaje.append(TipoMensajeEstadoEnum.WRN.getDescripcion());
						detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensaje.append(resultado.getDescripcionError().toString().trim());	
						
						facturaNuevaNotaDebito.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
						facturaNuevaNotaDebito.setMensajeEstado(detalleMensaje.toString());
					}
				}
				
				transaccionDifCambioNotaDebitoGenerada.getShvCobFactura().add(facturaNuevaNotaDebito);

				nuevasTransaccionesPorDifCambio.add(transaccionDifCambioNotaDebitoGenerada);

				// Genero una transaccion que contendrá una
				// copia de la transaccion original, cuya
				// factura tendrá el
				// importe modificado
				ShvCobTransaccion transaccionDifCambioFacturaOriginal = new ShvCobTransaccion();
				transaccionDifCambioFacturaOriginal.setEstadoProcesamiento(EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO);
				transaccionDifCambioFacturaOriginal.setNumeroTransaccion(factura.getTransaccion().getOperacion().getTransacciones().size() + 2);
				transaccionDifCambioFacturaOriginal.setOperacion(factura.getTransaccion().getOperacion());
				// se reemplaza por el numero de transaccion padre
				transaccionDifCambioFacturaOriginal.setNumeroTransaccionPadre(factura.getTransaccion().getNumeroTransaccion());

				transaccionDifCambioFacturaOriginal.setGrupo(factura.getTransaccion().getGrupo());
				transaccionDifCambioFacturaOriginal.setIdSociedad(factura.getSociedad());
				transaccionDifCambioFacturaOriginal.setSistema(factura.getSistemaOrigen());
				transaccionDifCambioFacturaOriginal.setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
				
				transaccionDifCambioFacturaOriginal.setNumeroTransaccionFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				transaccionDifCambioFacturaOriginal.setNumeroTransaccionPadreFicticio(factura.getTransaccion().getNumeroTransaccionFicticio());
				
				

				// Copio la factura, le seteo el importe
				// aplicado en Calipso, y la agrego a la nueva
				// transaccion

				// Registro de cobro de factura
				// Tipo de cambio al cobro: -- (sin Info)
				// Tipo de cambio a fecha de emisión:
				// <tipoCambioFechaEmision>
				// Importe cobrado en moneda origen a fecha de
				// emisión: <importeAplicadoMonedaOrigen>

				ShvCobFacturaCalipso copiaFactura = (ShvCobFacturaCalipso) Utilidad.clonarObjeto(factura);
				copiaFactura.setIdFactura(null);
				copiaFactura.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
				copiaFactura.setImporteCobrar(copiaFactura.getImporteAplicadoAFechaEmisionMonedaPesos());
				copiaFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
				copiaFactura.setTransaccion(transaccionDifCambioFacturaOriginal);

				// Limpio campos que no deben ser informados en la copia de la Factura
				cobroBatchSoporteServicio.limpiarCamposCopiaDocumentoAplicacionPorDifCambio(copiaFactura);

				transaccionDifCambioFacturaOriginal.getShvCobFactura().add(copiaFactura);

				nuevasTransaccionesPorDifCambio.add(transaccionDifCambioFacturaOriginal);

				BigDecimal importeCopiaFacturaPendiente = copiaFactura.getImporteCobrar();
				BigDecimal importeNotaDebitoPendiente = facturaNuevaNotaDebito.getImporteCobrar();

				// Copio el/los medios de pago y los agrego a la
				// transaccion según corresponda
				for (ShvCobMedioPago medioPagoOriginal : factura.getTransaccion().getShvCobMedioPago()) {

					ShvCobMedioPago copiaMedioPago = (ShvCobMedioPago) Utilidad.clonarObjeto(medioPagoOriginal);
					copiaMedioPago.setIdMedioPago(null);
					copiaMedioPago.setEstado(EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO);
					copiaMedioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
					
					copiaMedioPago = cobroBatchSoporteImputacionServicio.clonarListaDeClientesMedioPagoUsuario(medioPagoOriginal, copiaMedioPago);

					// Completo primero los medios de pago de la
					// transaccion que contiene la
					// copia de la factura original
					if (importeCopiaFacturaPendiente.compareTo(BigDecimal.ZERO) != 0) {

						if (copiaMedioPago.getImporte()	.compareTo(importeCopiaFacturaPendiente) > 0) {

							// Debo partir el medio de pago
							ShvCobMedioPago copiaMedioPagoParcial = (ShvCobMedioPago) Utilidad.clonarObjeto(copiaMedioPago);

							// En el caso de que sea un medio de
							// pago de usuario, debo clonar la
							// lista de clientes de manera
							// independiente, ya que el
							// metodo clonarObjeto no se banca
							// clonar objetos
							copiaMedioPagoParcial = cobroBatchSoporteImputacionServicio.clonarListaDeClientesMedioPagoUsuario(copiaMedioPago, copiaMedioPagoParcial);

							copiaMedioPagoParcial.setImporte(importeCopiaFacturaPendiente);

							// Asigno la copia del medio de pago
							// parcial a la transaccion
							copiaMedioPagoParcial.setTransaccion(transaccionDifCambioFacturaOriginal);
							transaccionDifCambioFacturaOriginal.getShvCobMedioPago().add(copiaMedioPagoParcial);

							// Actualizo el importe del medio de
							// pago parcial que todavia queda
							// pendiente para uso
							copiaMedioPago.setImporte(copiaMedioPago.getImporte().subtract(importeCopiaFacturaPendiente));

							// Actualizo el importe de copia
							// factura pendiente de cubrir, a
							// cero.
							importeCopiaFacturaPendiente = BigDecimal.ZERO;

						} else {

							// Asigno la copia del medio de pago
							// parcial a la transaccion
							copiaMedioPago.setTransaccion(transaccionDifCambioFacturaOriginal);
							transaccionDifCambioFacturaOriginal.getShvCobMedioPago().add(copiaMedioPago);

							// Actualizo el importe de copia
							// factura pendiente.
							importeCopiaFacturaPendiente = importeCopiaFacturaPendiente.subtract(copiaMedioPago.getImporte());
						}
					}

					// Completo luego los medios de pago para la
					// transaccion que contiene la
					// nueva nota de debito
					if (importeCopiaFacturaPendiente.compareTo(BigDecimal.ZERO) == 0 && importeNotaDebitoPendiente.compareTo(BigDecimal.ZERO) != 0) {

						// Asigno la copia del medio de pago
						// parcial a la transaccion
						copiaMedioPago.setTransaccion(transaccionDifCambioNotaDebitoGenerada);
						transaccionDifCambioNotaDebitoGenerada.getShvCobMedioPago().add(copiaMedioPago);

						// Actualizo el importe de copia factura
						// pendiente.
						importeNotaDebitoPendiente = importeNotaDebitoPendiente.subtract(copiaMedioPago.getImporte());
					}
				}
			}
			
			cobro.getOperacion().getTransacciones().addAll(nuevasTransaccionesPorDifCambio);
			cobro = cobroDao.modificar(cobro);
			

		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * Crea un nuevo medio de pago CTA a partir de los que estan en la lista
	 * @param listaCTAoNotaDeCredito
	 * @param transaccion
	 */
	private void crearNotasCreditoODebitoCalipsoYAsociarla(List<DetalleCTAoNotaCredito> listaCTAoNotaDeCredito, 
			ShvCobTransaccionSinOperacion transaccionSinOperacion) throws NegocioExcepcion{

			for(DetalleCTAoNotaCredito detalleCTAoNotaCredito : listaCTAoNotaDeCredito){
				
			//Busco si hay un medio de pago nuevo
			if(!Validaciones.isObjectNull(detalleCTAoNotaCredito.getDetalleCtaNtaCre())){
					
				//Padre
				IdDocumento idDocumentoPadre = detalleCTAoNotaCredito.getIdDocumento();
					
				// Hijo
				IdDocumento idDocumentoHijo = detalleCTAoNotaCredito.getDetalleCtaNtaCre().getCtaResultante();
					
				//Sin Operacion - Repito la misma logica pero para los objetos sin operacion
				for (ShvCobMedioPagoSinOperacion medioPago: cobroBatchSoporteImputacionServicio.listarMediosPago(transaccionSinOperacion, SistemaEnum.CALIPSO)) {
					if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
						ShvCobMedioPagoCTASinOperacion medPagoCta = (ShvCobMedioPagoCTASinOperacion)medioPago;
						if(medPagoCta.getTipoComprobante().equals(idDocumentoPadre.getTipoComprobante())
								&& medPagoCta.getClaseComprobante().equals(idDocumentoPadre.getClaseComprobante())
								&& medPagoCta.getSucursalComprobante().equals(idDocumentoPadre.getSucursalComprobante())
								&& medPagoCta.getNroComprobante().equals(idDocumentoPadre.getNumeroComprobante())){
								
							// Creo el medio de pago padre
							ShvCobMedioPagoCTASinOperacion medPagoCtaNueva = new ShvCobMedioPagoCTASinOperacion();
							medPagoCtaNueva.setTipoComprobante(idDocumentoHijo.getTipoComprobante());
							medPagoCtaNueva.setClaseComprobante(idDocumentoHijo.getClaseComprobante());
							medPagoCtaNueva.setSucursalComprobante(idDocumentoHijo.getSucursalComprobante());
							medPagoCtaNueva.setNroComprobante(idDocumentoHijo.getNumeroComprobante());
							medPagoCtaNueva.setGeneradoPorCobro(SiNoEnum.SI);
							medPagoCtaNueva.setImporte(detalleCTAoNotaCredito.getDetalleCtaNtaCre().getImporte());
							medPagoCtaNueva.setTipoMedioPago(medioPago.getTipoMedioPago());
							medPagoCtaNueva.setEstado(EstadoFacturaMedioPagoEnum.NUEVO);
							medPagoCtaNueva.setIdClienteLegado(medPagoCta.getIdClienteLegado());
							medPagoCtaNueva.setMedioPagoPadre(medPagoCta);
							medPagoCtaNueva.setTransaccion(transaccionSinOperacion);
							//Dolares
							medPagoCtaNueva.setMoneda(detalleCTAoNotaCredito.getDetalleCtaNtaCre().getMoneda());
							
							//MonedaImporte
							medPagoCtaNueva.setMonedaImporte(medioPago.getMonedaImporte());
								
							transaccionSinOperacion.getShvCobMedioPago().add(medPagoCtaNueva);
							
//							seteaIdCalipsoAMedioPago(medPagoCta,medPagoCtaNueva,transaccionSinOperacion);
							setearIdCalipsoALosDemasMediosPago(transaccionSinOperacion.getIdOperacion(),transaccionSinOperacion.getNumeroTransaccion(),medPagoCta,medPagoCtaNueva);
						}
					}else{
						if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
							ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medPagoNotaCredito = (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;
							if(medPagoNotaCredito.getTipoComprobante().equals(idDocumentoPadre.getTipoComprobante())
									&& medPagoNotaCredito.getClaseComprobante().equals(idDocumentoPadre.getClaseComprobante())
									&& medPagoNotaCredito.getSucursalComprobante().equals(idDocumentoPadre.getSucursalComprobante())
									&& medPagoNotaCredito.getNroComprobante().equals(idDocumentoPadre.getNumeroComprobante())){
									
								// Creo el medio de pago padre
								ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medPagoNotaCreditoNueva = new ShvCobMedioPagoNotaCreditoCalipsoSinOperacion();
								medPagoNotaCreditoNueva.setTipoComprobante(idDocumentoHijo.getTipoComprobante());
								medPagoNotaCreditoNueva.setClaseComprobante(idDocumentoHijo.getClaseComprobante());
								medPagoNotaCreditoNueva.setSucursalComprobante(idDocumentoHijo.getSucursalComprobante());
								medPagoNotaCreditoNueva.setNroComprobante(idDocumentoHijo.getNumeroComprobante());
								medPagoNotaCreditoNueva.setGeneradoPorCobro(SiNoEnum.SI);
								medPagoNotaCreditoNueva.setImporte(detalleCTAoNotaCredito.getDetalleCtaNtaCre().getImporte());
								medPagoNotaCreditoNueva.setTipoMedioPago(medioPago.getTipoMedioPago());
								medPagoNotaCreditoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE);
								medPagoNotaCreditoNueva.setIdClienteLegado(medPagoNotaCredito.getIdClienteLegado());
								medPagoNotaCreditoNueva.setMedioPagoPadre(medPagoNotaCredito);
								medPagoNotaCreditoNueva.setTransaccion(transaccionSinOperacion);
								//Dolares
								medPagoNotaCreditoNueva.setMoneda(detalleCTAoNotaCredito.getDetalleCtaNtaCre().getMoneda());
								
								//MonedaImporte
								medPagoNotaCreditoNueva.setMonedaImporte(medioPago.getMonedaImporte());
									
								transaccionSinOperacion.getShvCobMedioPago().add(medPagoNotaCreditoNueva);
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param idOperacion
	 * @param numeroTransaccion
	 * @param medPagoCta
	 * @param medPagoCtaNueva
	 * @throws NegocioExcepcion
	 */
	@Override
	public void setearIdCalipsoALosDemasMediosPago(Long idOperacion, Integer numeroTransaccion, ShvCobMedioPagoCTASinOperacion medPagoCta, ShvCobMedioPagoCTASinOperacion medPagoCtaNueva) throws NegocioExcepcion {
		try {
			List<ShvCobMedioPagoCTA> lista = cobroDao.buscarMedioPagoCtaIgualACta(idOperacion, numeroTransaccion, medPagoCta);
			for(ShvCobMedioPago medioPago : lista){
						
				if(medioPago instanceof ShvCobMedioPagoCTA){
					ShvCobMedioPagoCTA medioPagoCtaAux = (ShvCobMedioPagoCTA)medioPago; 
	
					if(medPagoCta.getTipoComprobante().equals(medioPagoCtaAux.getTipoComprobante())
							&& medPagoCta.getClaseComprobante().equals(medioPagoCtaAux.getClaseComprobante())
							&& medPagoCta.getSucursalComprobante().equals(medioPagoCtaAux.getSucursalComprobante())
							&& medPagoCta.getNroComprobante().equals(medioPagoCtaAux.getNroComprobante())){
						
						medioPagoCtaAux.setTipoComprobante(medPagoCtaNueva.getTipoComprobante());
						medioPagoCtaAux.setClaseComprobante(medPagoCtaNueva.getClaseComprobante());
						medioPagoCtaAux.setSucursalComprobante(medPagoCtaNueva.getSucursalComprobante());
						medioPagoCtaAux.setNroComprobante(medPagoCtaNueva.getNroComprobante());
						
						cobroDao.guardarMedioPago(medioPagoCtaAux);
						Traza.auditoria(getClass(), "Se modifican los campos del id de calipso del medio de pago id: " + medioPagoCtaAux.getIdMedioPago() + " con los datos"
								+ " Tipo Comprobante: " + medPagoCtaNueva.getTipoComprobante()
								+ " Clase Comprobante: " + medPagoCtaNueva.getClaseComprobante()
								+ " Sucursal Comprobante: " + medPagoCtaNueva.getSucursalComprobante()
								+ " Numero Comprobante: " + medPagoCtaNueva.getNroComprobante());
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	
	/**
	 * Retorna la respuesta de generación de cargos o reintegros de Calipso
	 * @param mpProxFactura
	 * @param tratamientoDiferencia
	 * @param entradaCalipsoCargo
	 * @return
	 * @throws ReintentoExcepcion 
	 */
	public boolean generarCargoReintegroCalipso(
			ShvCobMedioPagoDebitoProximaFacturaCalipsoSinOperacion mpProxFactura,	
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia, 
			EntradaCalipsoCargosWS entradaCalipsoCargo) 
					throws NegocioExcepcion, ReintentoExcepcion{
		
		TipoInvocacionEnum tipoInvocacion = null;
		if (MensajeServicioEnum.CLP_GENERACION_CARGO_DEBITO.equals(entradaCalipsoCargo.getTipoMensaje())) {
			tipoInvocacion = TipoInvocacionEnum.$06;
		} else if (MensajeServicioEnum.CLP_GENERACION_CARGO_CREDITO.equals(entradaCalipsoCargo.getTipoMensaje())) {
			tipoInvocacion = TipoInvocacionEnum.$07;
		} else if (MensajeServicioEnum.CLP_GENERACION_CARGO_INTERES.equals(entradaCalipsoCargo.getTipoMensaje())) {
			tipoInvocacion = TipoInvocacionEnum.$08;
		}
		
		SalidaCalipsoCargosWS salidaCalipsoCargo = null;
		try {
			
			if (cobroBatchSoporteImputacionServicio.puedeEnviarMensaje(Long.valueOf(entradaCalipsoCargo.getIdOperacion()),Integer.valueOf(entradaCalipsoCargo.getIdTransaccion()), 
					Integer.valueOf(entradaCalipsoCargo.getNumeroTransaccion()), SistemaEnum.CALIPSO, tipoInvocacion)){
				
				salidaCalipsoCargo = clienteCalipsoServicio.generarCargoCalipso(entradaCalipsoCargo, TipoProcesoEnum.COBRO);
				
				
				if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())
						&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equals(salidaCalipsoCargo.getResultado().getCodigoError())){
					
					salidaCalipsoCargo.getResultado().setResultado(TipoResultadoEnum.OK.getDescripcionCalipso());
				}
				
				if (TipoResultadoEnum.OK.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())
						|| TipoResultadoEnum.WRN.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())){
					
					StringBuffer detalleMensaje = new StringBuffer(Constantes.EMPTY_STRING);

					if (TipoResultadoEnum.WRN.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())){
						
						detalleMensaje.append(TipoMensajeEstadoEnum.WRN.getDescripcion());
						detalleMensaje.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensaje.append(salidaCalipsoCargo.getResultado().getDescripcionError().toString().trim());	
						
					}

					if (!Validaciones.isObjectNull(mpProxFactura)){
						if (!Validaciones.isObjectNull(salidaCalipsoCargo.getIdMovMer())){
							
							mpProxFactura.setCobradorIdMovMer(salidaCalipsoCargo.getIdMovMer().longValue());
							mpProxFactura.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
							mpProxFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
							//u562163 - Defectos 586, 589, 593, 620
							mpProxFactura.setCobradorIntereseGenerados(salidaCalipsoCargo.getMontoACuenta());
							mpProxFactura.setCobradorInteresesTrasladados(salidaCalipsoCargo.getMontoCalculadoMora());
							
							if (!Validaciones.isNullOrEmpty(detalleMensaje.toString())){
								mpProxFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
								mpProxFactura.setMensajeEstado(detalleMensaje.toString());
							}
						}
					} else {
						if (!Validaciones.isObjectNull(tratamientoDiferencia)){
							if (!Validaciones.isObjectNull(salidaCalipsoCargo.getIdMovMer())){
								
								tratamientoDiferencia.setIdMovMerCobrador(salidaCalipsoCargo.getIdMovMer().longValue());
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.APROPIADA);
								tratamientoDiferencia.setTipoMensajeEstado(TipoMensajeEstadoEnum.OK);
								//u562163 - Defectos 586, 589, 593, 620
								tratamientoDiferencia.setCobradorInteresesGenerados(salidaCalipsoCargo.getMontoACuenta());
								tratamientoDiferencia.setCobradorInteresesTrasladados(salidaCalipsoCargo.getMontoCalculadoMora());
								
								if (!Validaciones.isNullOrEmpty(detalleMensaje.toString())){
									tratamientoDiferencia.setTipoMensajeEstado(TipoMensajeEstadoEnum.WRN);
									tratamientoDiferencia.setMensajeEstado(detalleMensaje.toString());
								}
							}
						}
					}
				} else {
					if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())
							|| TipoResultadoEnum.NOK.getDescripcionCalipso().equals(salidaCalipsoCargo.getResultado().getResultado())){
						
						StringBuffer detalleMensajeError   = new StringBuffer(Constantes.EMPTY_STRING);
						
						detalleMensajeError.append(TipoMensajeEstadoEnum.ERR.getDescripcion());
						detalleMensajeError.append(Constantes.EXCLAMATION_AND_WHITESPACE);
						detalleMensajeError.append(salidaCalipsoCargo.getResultado().getDescripcionError());
						
						if (!Validaciones.isObjectNull(mpProxFactura)){
							mpProxFactura.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
							mpProxFactura.setMensajeEstado(detalleMensajeError.toString());
							mpProxFactura.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
								
						} else {
							if (!Validaciones.isObjectNull(tratamientoDiferencia)){
								tratamientoDiferencia.setEstado(EstadoFacturaMedioPagoEnum.ERROR);
								tratamientoDiferencia.setMensajeEstado(detalleMensajeError.toString());
								tratamientoDiferencia.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
							}
						}
					}
				}
			}
			
		} catch (NegocioExcepcion | NumberFormatException e) {
			
			throw new NegocioExcepcion(e.getMessage());
		}
		
		return false;
	}
	
	/**
     * Se encarga de enviar el mensaje de confirmacion de la factura y/o medios de pago a Calipso.
     * Ademas, recibe el cobro para acutalizar el estado en el objeto.
     * 
      * @param cobro  del cual se obtienen los datos para enviar a Calipso el mensaje de confirmacion.
     * @param listaOperacionTransacciones  se usa para saber a que transacciones modificar el estado.
     * @throws NegocioExcepcion 
      */
	 @Override
     public String confirmacionCalipso(List<String> listaOperacionTransacciones, ShvCobCobro cobro) throws NegocioExcepcion {
		try {
			for (String operacionTransaccion : listaOperacionTransacciones) {
				cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(operacionTransaccion.split("\\.")[1], cobro, EstadoTransaccionEnum.EN_PROCESO_CONFIRMACION);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		// Envio a calipso
		EntradaCalipsoConfirmacionWS eEntrada = new EntradaCalipsoConfirmacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_CONFIRMACION);
		eEntrada.setIdOperacion(BigInteger.valueOf(Integer.valueOf(listaOperacionTransacciones.get(0).split("\\.")[0])));
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());

		List<DetalleFactura> listaFacturasAConfirmar = new ArrayList<DetalleFactura>();
		List<DetalleCargoSalidaCobranzasWs> listaCargosAConfirmar = new ArrayList<DetalleCargoSalidaCobranzasWs>();
		List<DetalleCTAoNotaCredito> listaCtaONotaCreditoAConfirmar = new ArrayList<DetalleCTAoNotaCredito>();

		for (String operacionTransaccion : listaOperacionTransacciones) {

			ShvCobTransaccion tran = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));

			ShvCobFactura factura = tran.getFactura();
			// BigInteger idMovMer = obtenerIdMovMer(tran);

			if (factura instanceof ShvCobFacturaCalipso) {
				ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;

				BigInteger idCobranza = BigInteger.valueOf(facturaCalipso
						.getIdCobranza());
				DetalleFactura detalleFactura = new DetalleFactura();
				detalleFactura.setIdCobranza(idCobranza);
				listaFacturasAConfirmar.add(detalleFactura);
			}

			if (!Validaciones.isObjectNull(tran.getTratamientoDiferencia())) {
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP
						.equals(tran.getTratamientoDiferencia()
								.getTipoTratamientoDiferencia())
						&& !Validaciones.isObjectNull(tran
								.getTratamientoDiferencia()
								.getIdMovMerCobrador())) {

					BigInteger idMovMer = null;

					if (!Validaciones.isObjectNull(tran
							.getTratamientoDiferencia().getIdMovMerCobrador())) {
						idMovMer = new BigInteger(String.valueOf(tran
								.getTratamientoDiferencia()
								.getIdMovMerCobrador()));
					}

					DetalleCargoSalidaCobranzasWs detalleCargo = new DetalleCargoSalidaCobranzasWs();
					detalleCargo.setIdMovMer(idMovMer);
					listaCargosAConfirmar.add(detalleCargo);
				}
			}

			for (ShvCobMedioPago medioPago : cobroBatchSoporteImputacionServicio.listarMediosPago(tran,
					SistemaEnum.CALIPSO)) {

				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {

					ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;

					DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
					detalleNotaCredito.setIdCobranza(BigInteger
							.valueOf(medioPagoNotaCreditoCalipso
									.getIdCobranza()));

					listaCtaONotaCreditoAConfirmar.add(detalleNotaCredito);

				} else if (medioPago instanceof ShvCobMedioPagoCTA) {

					ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;

					DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
					detalleNotaCredito.setIdCobranza(BigInteger
							.valueOf(medioPagoCTA.getIdCobranza()));

					listaCtaONotaCreditoAConfirmar.add(detalleNotaCredito);

				}
			}
			
			for (ShvCobMedioPago medioPago : cobroBatchSoporteImputacionServicio.listarMediosPagoProxFactura(tran, SistemaEnum.CALIPSO)) {
			
				if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {

					BigInteger idMovMer = null;

					if (!Validaciones
							.isObjectNull(((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago)
									.getCobradorIdMovMer())) {
						idMovMer = new BigInteger(
								String.valueOf(((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago)
										.getCobradorIdMovMer()));
					}

					DetalleCargoSalidaCobranzasWs detalleCargo = new DetalleCargoSalidaCobranzasWs();
					detalleCargo.setIdMovMer(idMovMer);
					listaCargosAConfirmar.add(detalleCargo);
				}
			}
		}

		eEntrada.setListaFacturasAConfirmar(listaFacturasAConfirmar);
		eEntrada.setListaCargosAConfirmar(listaCargosAConfirmar);
		eEntrada.setListaCtaONotaCreditoAConfirmar(listaCtaONotaCreditoAConfirmar);

		SalidaCalipsoConfirmacionWS respuesta = clienteCalipsoServicio
				.confirmarCobro(eEntrada);
		if (!Validaciones.isObjectNull(respuesta)) {

			String estadoRespuesta = respuesta.getResultadoInvocacion().getResultado(); // OK / ERR
			if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(
					estadoRespuesta)
					&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA
							.equalsIgnoreCase(respuesta
									.getResultadoInvocacion().getCodigoError())) {
				estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
				Traza.auditoria(getClass(), "Metodo "
						+ MensajeServicioEnum.CLP_CONFIRMACION.getDescripcion()
						+ " (" + eEntrada.getIdOperacion() + ", rta: "
						+ estadoRespuesta + ") cambiada por "
						+ Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
			}

			return estadoRespuesta;

		} else {
			return "";
		}
     }
	 
	 @Override
	 public String desapropiacionParcialCalipso(List<ShvCobTransaccionSinOperacion> listaTransacciones, List<String> listaIdMovMer, Long idOperacion) throws NegocioExcepcion{
		 
		EntradaCalipsoDesapropiacionWS eEntrada = new EntradaCalipsoDesapropiacionWS();
		eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_DESAPROPIACION);
		
		eEntrada.setIdOperacion(BigInteger.valueOf(idOperacion));
		eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		
		List<DetalleFactura> listaFacturasADesapropiar = new ArrayList<DetalleFactura>();
		List<DetalleCTAoNotaCredito> listaCtaONotaCreditoADesapropiar = new ArrayList<DetalleCTAoNotaCredito>();
		List<DetalleCargoSalidaCobranzasWs> listaCargosADesapropiar = new ArrayList<DetalleCargoSalidaCobranzasWs>();


		for (ShvCobTransaccionSinOperacion transaccion : listaTransacciones){
			ShvCobFacturaSinOperacion factura = null;
			if (!Validaciones.isObjectNull(transaccion.getFactura())){
				factura = transaccion.getFactura();
			}
			
			if (factura instanceof ShvCobFacturaCalipsoSinOperacion && EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())){
				ShvCobFacturaCalipsoSinOperacion facturaCalipso = (ShvCobFacturaCalipsoSinOperacion) factura;
				
				// idcobranzas de facturas
				BigInteger idCobranza = BigInteger.valueOf(facturaCalipso.getIdCobranza());
				DetalleFactura detalleFactura = new DetalleFactura();
				detalleFactura.setIdCobranza(idCobranza);
				listaFacturasADesapropiar.add(detalleFactura);
			}
			
			
			List<ShvCobMedioPagoSinOperacion> listaMediosPagoCalipso = cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion, SistemaEnum.CALIPSO);
			
			for (ShvCobMedioPagoSinOperacion medioPago: listaMediosPagoCalipso) {
				
				if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medioPago.getEstado())){
					
					if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipsoSinOperacion){
						ShvCobMedioPagoNotaCreditoCalipsoSinOperacion medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipsoSinOperacion)medioPago;
						
						DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
						// idcobranzas de MP
						detalleNotaCredito.setIdCobranza(BigInteger.valueOf(medioPagoNotaCreditoCalipso.getIdCobranza()));
						
						listaCtaONotaCreditoADesapropiar.add(detalleNotaCredito);
					}else{
						if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
							ShvCobMedioPagoCTASinOperacion medioPagoCTA = (ShvCobMedioPagoCTASinOperacion)medioPago;
							
							DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
							// idcobranzas de MP
							detalleNotaCredito.setIdCobranza(BigInteger.valueOf(medioPagoCTA.getIdCobranza()));
							
							listaCtaONotaCreditoADesapropiar.add(detalleNotaCredito);
						}
					}
				}
			}
		}
			
			if(!Validaciones.isObjectNull(listaIdMovMer)){
				for (String idMovMer : listaIdMovMer) {
					DetalleCargoSalidaCobranzasWs detalleCargo = new DetalleCargoSalidaCobranzasWs();
					detalleCargo.setIdMovMer(new BigInteger(idMovMer));
					listaCargosADesapropiar.add(detalleCargo);
					
				}
			}
			
//		}
		
		eEntrada.setListaFacturasADesapropiar(listaFacturasADesapropiar);
		eEntrada.setListaCtaONotaCreditoADesapropiar(listaCtaONotaCreditoADesapropiar);
		eEntrada.setListaCargosADesapropiar(listaCargosADesapropiar);
		
		SalidaCalipsoDesapropiacionWS respuesta = clienteCalipsoServicio.desapropiarOperacion(eEntrada);
		
		if(!Validaciones.isObjectNull(respuesta)){
			
			String estadoRespuesta = respuesta.getResultadoInvocacion().getResultado(); //OK / ERR
			if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
					&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuesta.getResultadoInvocacion().getCodigoError())) {
				estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
				Traza.auditoria(getClass(), "Metodo "+MensajeServicioEnum.CLP_DESAPROPIACION.getDescripcion()
						+" ("+eEntrada.getIdOperacion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
			}
			
			return estadoRespuesta;

		}else{
			return "ERR";
		}
		 
	 }
	 
	 /**
	 * Se encarga de la desapropiacion de la factura y/o medios de pago Calipso
	 * pertenecientes a la operacion.
	 * Ademas, recibe el cobro para acutalizar el estado en el objeto.
	 * 
	 * @param listaOperacionTransacciones  se usa para saber a que transacciones modificar el estado.
	 * @param cobro del cual se obtienen los datos para enviar a Calipso el mensaje de desapropiacion.
	 */
	 @Override
	 public String desapropiacionCalipso(List<String> listaOperacionTransacciones, ShvCobCobro cobro, List<String> listaIdMovMer)  throws NegocioExcepcion{ 
		try {
			for (String operacionTransaccion : listaOperacionTransacciones) {
				if(!EstadoTransaccionEnum.listarEstadosError().contains(cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1])).getEstadoProcesamiento())){
					cobroBatchSoporteImputacionServicio.actualizarEstadoTransaccion(operacionTransaccion.split("\\.")[1], cobro, EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION);
				}
				cobroBatchSoporteImputacionServicio.actualizarEstadoFacturaYMP(cobro, operacionTransaccion.split("\\.")[1], EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION, SistemaEnum.CALIPSO,null);
			}
		
			EntradaCalipsoDesapropiacionWS eEntrada = new EntradaCalipsoDesapropiacionWS();
			eEntrada.setTipoApropiacion(MensajeServicioEnum.CLP_DESAPROPIACION);
			
			eEntrada.setIdOperacion(BigInteger.valueOf(cobro.getOperacion().getIdOperacion()));
			eEntrada.setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
			
			List<DetalleFactura> listaFacturasADesapropiar = new ArrayList<DetalleFactura>();
			List<DetalleCTAoNotaCredito> listaCtaONotaCreditoADesapropiar = new ArrayList<DetalleCTAoNotaCredito>();
			List<DetalleCargoSalidaCobranzasWs> listaCargosADesapropiar = new ArrayList<DetalleCargoSalidaCobranzasWs>();
			for (String operacionTransaccion : listaOperacionTransacciones) {
				
				ShvCobTransaccion transaccion = cobro.getTransaccionPorIdTransaccion(Integer.valueOf(operacionTransaccion.split("\\.")[1]));
				ShvCobFactura factura = transaccion.getFactura();
				
				if (factura instanceof ShvCobFacturaCalipso && EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(factura.getEstado())){
					ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) factura;
					
					// idcobranzas de facturas
					BigInteger idCobranza = BigInteger.valueOf(facturaCalipso.getIdCobranza());
					DetalleFactura detalleFactura = new DetalleFactura();
					detalleFactura.setIdCobranza(idCobranza);
					listaFacturasADesapropiar.add(detalleFactura);
				}
				
				for (ShvCobMedioPago medioPago: cobroBatchSoporteImputacionServicio.listarMediosPago(transaccion,SistemaEnum.CALIPSO)) {
					
					if (EstadoFacturaMedioPagoEnum.EN_PROCESO_DESAPROPIACION.equals(medioPago.getEstado())){
						
						if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
							ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso)medioPago;
							
							DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
							// idcobranzas de MP
							detalleNotaCredito.setIdCobranza(BigInteger.valueOf(medioPagoNotaCreditoCalipso.getIdCobranza()));
							
							listaCtaONotaCreditoADesapropiar.add(detalleNotaCredito);
						}else{
							if(medioPago instanceof ShvCobMedioPagoCTA){
								ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA)medioPago;
								
								DetalleCTAoNotaCredito detalleNotaCredito = new DetalleCTAoNotaCredito();
								// idcobranzas de MP
								detalleNotaCredito.setIdCobranza(BigInteger.valueOf(medioPagoCTA.getIdCobranza()));
								
								listaCtaONotaCreditoADesapropiar.add(detalleNotaCredito);
							}
						}
					}
				}
				
				if(!Validaciones.isObjectNull(listaIdMovMer)){
					for (String idMovMer : listaIdMovMer) {
						DetalleCargoSalidaCobranzasWs detalleCargo = new DetalleCargoSalidaCobranzasWs();
						detalleCargo.setIdMovMer(new BigInteger(idMovMer));
						listaCargosADesapropiar.add(detalleCargo);
						
					}
				}
				
			}
			
			eEntrada.setListaFacturasADesapropiar(listaFacturasADesapropiar);
			eEntrada.setListaCtaONotaCreditoADesapropiar(listaCtaONotaCreditoADesapropiar);
			eEntrada.setListaCargosADesapropiar(listaCargosADesapropiar);
			
			SalidaCalipsoDesapropiacionWS respuesta = clienteCalipsoServicio.desapropiarOperacion(eEntrada);
			
			if(!Validaciones.isObjectNull(respuesta)){
				
				String estadoRespuesta = respuesta.getResultadoInvocacion().getResultado(); //OK / ERR
				if (TipoResultadoEnum.ERROR.getDescripcionCalipso().equals(estadoRespuesta)
						&& Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA.equalsIgnoreCase(respuesta.getResultadoInvocacion().getCodigoError())) {
					estadoRespuesta = TipoResultadoEnum.OK.getDescripcionCalipso();
					Traza.auditoria(getClass(), "Metodo "+MensajeServicioEnum.CLP_DESAPROPIACION.getDescripcion()
							+" ("+eEntrada.getIdOperacion()+", rta: " + estadoRespuesta+") cambiada por " + Constantes.CALIPSO_COD_TRANSACCION_YA_PROCESADA);
				}
				return estadoRespuesta;

			}else{
				return "";
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	

	
	
	
}



