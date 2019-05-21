package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigInteger;
import java.text.ParseException;

import javax.xml.ws.WebServiceException;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ControlPaginado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.DetCREclientes;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.Detclientes;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.IdDocumentoAgru;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.ListaCreditosdetalle;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.ListaCreditosresultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.ListaDebitosresultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.Listadebitosdetalle;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.consultadeudaycredito.WebServiceConsultasOnlinePortType;

public class CalipsoConsultasWS {
	
	WebServiceConsultasOnlinePortType calipsoConsultasClienteProxy;
	
	/**
	 * Objetivo: Permite al consumidor del servicio conocer la deuda de un cliente en el cobrador Calipso
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public SalidaCalipsoDeudaWS consultarDeuda(EntradaCalipsoDeudaWS entrada) 
			throws NegocioExcepcion {
		try {
			
			Detclientes listaClientes = new Detclientes(); 
			for (Cliente cliente: entrada.getListaClientes()) {
				listaClientes.getIdClienteLegado().add(cliente.getIdClienteLegado());
			}
			
			IdDocumentoAgru idDocumento = new IdDocumentoAgru();
			if (entrada.getIdDocumento()!=null) {
				
				idDocumento.setTipoComprobante(
						!Validaciones.isObjectNull(entrada.getIdDocumento().getTipoComprobante())
							?entrada.getIdDocumento().getTipoComprobante().getValor():"");
				idDocumento.setClaseComprobante(
						!Validaciones.isObjectNull(entrada.getIdDocumento().getClaseComprobante())
						?entrada.getIdDocumento().getClaseComprobante().name():"");
				idDocumento.setSucursalComprobante(
						!Validaciones.isObjectNull(entrada.getIdDocumento().getSucursalComprobante())?
								Utilidad.rellenarCerosIzquierda(
										Utilidad.generarSalidaConValorOVacio(
												entrada.getIdDocumento().getSucursalComprobante()), 4) : null);
				idDocumento.setNumeroComprobante(
						!Validaciones.isObjectNull(entrada.getIdDocumento().getNumeroComprobante())?
								Utilidad.rellenarCerosIzquierda(
										Utilidad.generarSalidaConValorOVacio(
												entrada.getIdDocumento().getNumeroComprobante()), 8) : null);
			}
			
			String fechaVencimientoDesde = 
					!Validaciones.isObjectNull(
							entrada.getFechaVencimientoDesde())?
									Utilidad.formatDateAAAAMMDD(entrada.getFechaVencimientoDesde()):"";
	        String fechaVencimientoHasta =
	        		!Validaciones.isObjectNull(
							entrada.getFechaVencimientoHasta())?
									Utilidad.formatDateAAAAMMDD(entrada.getFechaVencimientoHasta()):"";
									
			String fechaCotizacion =
					!Validaciones.isObjectNull(
							entrada.getFechaCotizacion())?
									Utilidad.formatDateAAAAMMDD(entrada.getFechaCotizacion()):"";
	        
			BigInteger acuerdo = 
					!Validaciones.isObjectNull(entrada.getAcuerdo()) ? 
							entrada.getAcuerdo() : new BigInteger("0");
	        
			String moneda = 
	        		!Validaciones.isObjectNull(entrada.getMoneda())?entrada.getMoneda().getSigno2():"";
									
	        BigInteger cantidadRegistrosARetornar =
	        		!Validaciones.isObjectNull(entrada.getInformacionParaPaginado().getCantidadDeRegistrosARetornar()) ?
	        				entrada.getInformacionParaPaginado().getCantidadDeRegistrosARetornar() : new BigInteger("0");
	        
	        BigInteger ultimoIdDocCtasCob = 
	        		!Validaciones.isObjectNull(entrada.getInformacionParaPaginado().getUltimoIdDocCtasCob()) ? 
	        				entrada.getInformacionParaPaginado().getUltimoIdDocCtasCob() : new BigInteger("0");

	        String ultimaFechaDocumento = 
	        		!Validaciones.isObjectNull(
							entrada.getInformacionParaPaginado().getUltimaFechaDocumento())?
									Utilidad.formatDateAAAAMMDD(entrada.getInformacionParaPaginado().getUltimaFechaDocumento()):"";
	        String ultimoClienteDocumento = 
	        		!Validaciones.isObjectNull(
	        				entrada.getInformacionParaPaginado().getUltimoClienteDocumento())?
	        						entrada.getInformacionParaPaginado().getUltimoClienteDocumento().toString():"";
			
	        ListaDebitosresultado respuesta = 
				calipsoConsultasClienteProxy.consultaDeudaFiltroCalipso(
							listaClientes, idDocumento, fechaVencimientoDesde, fechaVencimientoHasta, fechaCotizacion,
							acuerdo, moneda, cantidadRegistrosARetornar, 
							ultimoIdDocCtasCob, ultimaFechaDocumento, 
							ultimoClienteDocumento);
			
			if (respuesta != null) {
				SalidaCalipsoDeudaWS datos = new SalidaCalipsoDeudaWS();
				
				if (respuesta.getListaDebitos() != null && respuesta.getListaDebitos().getDebito() != null) {
					
					for (Listadebitosdetalle.Debito respDebito: respuesta.getListaDebitos().getDebito()) {
						
						CalipsoDebito debito = new CalipsoDebito();
						debito.setIdClienteLegado(respDebito.getIdClienteLegado());
						
						ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento 
							documento = new ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento();
						
						if (respDebito.getIdDocumento()!=null) {
							documento.setTipoComprobante(
								TipoComprobanteEnum.getEnumByValor(
										respDebito.getIdDocumento().getTipoComprobante()));	
							documento.setClaseComprobante(
									TipoClaseComprobanteEnum.getEnumByName(
											respDebito.getIdDocumento().getClaseComprobante()));
							documento.setSucursalComprobante(
									(!Validaciones.isNullOrEmpty(respDebito.getIdDocumento().getSucursalComprobante()))? 
											respDebito.getIdDocumento().getSucursalComprobante():"");
							documento.setNumeroComprobante(
									(!Validaciones.isNullOrEmpty(respDebito.getIdDocumento().getNumeroComprobante()))? 
											respDebito.getIdDocumento().getNumeroComprobante():"");
						}
						debito.setIdDocumento(documento);
						debito.setFechaVencimiento(
								!Validaciones.isNullOrEmpty(respDebito.getFechaVencimiento())?
										Utilidad.deserializeAndFormatDate(respDebito.getFechaVencimiento(), Utilidad.ANO_MES_DIA):null);
						
						debito.setMonedaOriginalFactura(
								!Validaciones.isNullOrEmpty(respDebito.getMonedaOriginalFactura())?
										MonedaEnum.getEnumBySigno2(respDebito.getMonedaOriginalFactura()):null);
						
						debito.setImporte1erVencimientoMonedaOrigen(respDebito.getImporte1ErVencimientoMonedaOrigen());
						debito.setImporte1erVencimientoPesificado(respDebito.getImporte1ErVencimientoPesificado());
						debito.setImporte1erVencimientoPesificadoFechaCotizacion(respDebito.getImporte1ErVencimientoPesificadoFechaCotizacion());
						debito.setSaldo1erVencimientoMonedaOrigen(respDebito.getSaldo1ErVencimientoMonedaOrigen());
						debito.setSaldoPesificado(respDebito.getSaldoPesificado());
						debito.setSaldoPesificadoFechaCotizacion(respDebito.getSaldoPesificadoFechaCotizacion());
						debito.setCodigoAcuerdoFacturacion(respDebito.getCodigoAcuerdoFacturacion());
						debito.setEstadoAcuerdoFacturacion(respDebito.getEstadoAcuerdoFacturacion());
						debito.setEstadoMorosidad(respDebito.getEstadoMorosidad());
//						debito.setMarcaAdhesionDebitoAutomatico(respDebito.getMarcaAdhesionDebitoAutomatico());
						debito.setMarcaReclamo(
								!Validaciones.isNullOrEmpty(respDebito.getMarcaReclamo())?
										SiNoEnum.getEnumByDescripcionCorta(respDebito.getMarcaReclamo()):null);
						
						debito.setMarcaMigradoDeimos(
								!Validaciones.isNullOrEmpty(respDebito.getMarcaMigradoDeimos())?
										SiNoEnum.getEnumByDescripcionCorta(respDebito.getMarcaMigradoDeimos()):null);	
						
						debito.setTipoCambioActual(respDebito.getTipoCambioActual());
						
						debito.setTipoCambioFechaCotizacion(respDebito.getTipoCambioFechaCotizacion());
						
						debito.setFechaEmision(
								!Validaciones.isNullOrEmpty(respDebito.getFechaEmision())?
										Utilidad.deserializeAndFormatDate(respDebito.getFechaEmision(), Utilidad.ANO_MES_DIA):null);	
						debito.setFechaUltimoPagoParcial(
								!Validaciones.isNullOrEmpty(respDebito.getFechaUltimoPagoParcial())?
										Utilidad.deserializeAndFormatDate(respDebito.getFechaUltimoPagoParcial(), Utilidad.ANO_MES_DIA):null);
						debito.setIdDocCtasCob(respDebito.getIdDocCtasCob());
						
						//
						if (respDebito.getInformacionAdicionalTagetikCalipso()!=null) {
							debito.getInformacionAdicionalTagetikCalipso().setRazonSocialCliente(
								respDebito.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
							debito.getInformacionAdicionalTagetikCalipso().setTipoCliente(
									respDebito.getInformacionAdicionalTagetikCalipso().getTipoCliente());
							debito.getInformacionAdicionalTagetikCalipso().setCuit(
									respDebito.getInformacionAdicionalTagetikCalipso().getCUIT());
						} else {
							debito.setInformacionAdicionalTagetikCalipso(null);
						}
						
						//
						if (respDebito.getInformacionAdicionalDacota()!=null) {
							debito.getInformacionAdicionalDacota().setUnidadOperativa(
								respDebito.getInformacionAdicionalDacota().getUnidadOperativa());
							debito.getInformacionAdicionalDacota().setSubTipo(
									respDebito.getInformacionAdicionalDacota().getSubTipo());
							debito.getInformacionAdicionalDacota().setHolding(
									respDebito.getInformacionAdicionalDacota().getHolding());
						} else {
							debito.setInformacionAdicionalDacota(null);
						}
						
						datos.getListaDebitos().add(debito);
					}
				}
				
				ControlPaginado controlPaginado = new ControlPaginado();
				if (OkNokEnum.OK.getDescripcion().equalsIgnoreCase(respuesta.getResultadoProcesamiento().getResultadoImputacion())) {
					controlPaginado.setCantidadRegistrosRetornados(respuesta.getControlPaginado().getCantidadRegistrosRetornados());
					controlPaginado.setCantidadRegistrosTotales(respuesta.getControlPaginado().getCantidadRegistrosTotales());
				} else {
					controlPaginado.setCantidadRegistrosRetornados(new BigInteger("0"));
					controlPaginado.setCantidadRegistrosTotales(new BigInteger("0"));
				}
				datos.setControlPaginado(controlPaginado);
				
				ResultadoProcesamiento resultado = new ResultadoProcesamiento();
				resultado.setResultadoImputacion(respuesta.getResultadoProcesamiento().getResultadoImputacion());
				resultado.setCodigoError(respuesta.getResultadoProcesamiento().getCodigoerror());
				resultado.setDescripcionError(respuesta.getResultadoProcesamiento().getDescripcionerror());
				datos.setResultadoProcesamiento(resultado);
				
				return datos;
			}
			throw new WebServiceExcepcion("WS CalipsoConsultaDeuda : Se ha producido un error en el webservice");
		
		} catch (WebServiceException e) {
			throw new WebServiceExcepcion("WS CalipsoConsultaDeuda : Se ha producido un error en el webservice", e);
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS CalipsoConsultaDeuda - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion("WS CalipsoConsultaDeuda: Falla de conexion: " + e);
		} catch (ParseException e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * Objetivo: Permite al consumidor del servicio conocer la deuda de un cliente en el cobrador Calipso
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public SalidaCalipsoCreditoWS consultarCredito(EntradaCalipsoCreditoWS entrada) 
			throws NegocioExcepcion {
		try {
			
			DetCREclientes listaClientes = new DetCREclientes(); 
			for (Cliente cliente: entrada.getListaClientes()) {
				listaClientes.getIdClienteLegado().add(cliente.getIdClienteLegado());
			}
			
			String tipoMedioPago = 
					!Validaciones.isObjectNull(
							entrada.getTipoMedioPago())?entrada.getTipoMedioPago().name():"";
			String fechaDesde = 
					!Validaciones.isObjectNull(
							entrada.getFechaDesde())?
									Utilidad.formatDateAAAAMMDD(entrada.getFechaDesde()):"";
	        String fechaHasta =
	        		!Validaciones.isObjectNull(
							entrada.getFechaHasta())?
									Utilidad.formatDateAAAAMMDD(entrada.getFechaHasta()):"";
	        
	        BigInteger acuerdo = 
					!Validaciones.isObjectNull(entrada.getAcuerdo()) ? 
							entrada.getAcuerdo() : new BigInteger("0");
							
	        String moneda = 
	        		!Validaciones.isObjectNull(
							entrada.getMoneda())?entrada.getMoneda().getSigno2():"";
			////////						
	        BigInteger cantidadRegistrosARetornar =
	        		!Validaciones.isObjectNull(entrada.getInformacionParaPaginado().getCantidadDeRegistrosARetornar()) ?
	        				entrada.getInformacionParaPaginado().getCantidadDeRegistrosARetornar() : new BigInteger("0");

	        BigInteger ultimoIdDocCuentasCobranzaCTA = 
	        		!Validaciones.isObjectNull(entrada.getInformacionParaPaginado().getUltimoIdDocCuentasCobranzaCTA()) ?
	        				entrada.getInformacionParaPaginado().getUltimoIdDocCuentasCobranzaCTA() : new BigInteger("0");
	        
	        String ultimaFechaCTA = 
	        		!Validaciones.isObjectNull(
							entrada.getInformacionParaPaginado().getUltimaFechaCTA())?
									Utilidad.formatDateAAAAMMDD(entrada.getInformacionParaPaginado().getUltimaFechaCTA()):"";
			String ultimoClienteCTA = 
	        		!Validaciones.isObjectNull(
	        				entrada.getInformacionParaPaginado().getUltimoClienteCTA())?
	        						entrada.getInformacionParaPaginado().getUltimoClienteCTA().toString():"";
			////////						
	        BigInteger ultimoIdDocCuentasCobranzaNC = 
	        		!Validaciones.isObjectNull(entrada.getInformacionParaPaginado().getUltimoIdDocCuentasCobranzaNC())?
	        				entrada.getInformacionParaPaginado().getUltimoIdDocCuentasCobranzaNC() : new BigInteger("0");
	        				
	        String ultimaFechaNC = 
	        		!Validaciones.isObjectNull(
							entrada.getInformacionParaPaginado().getUltimaFechaNC())?
									Utilidad.formatDateAAAAMMDD(entrada.getInformacionParaPaginado().getUltimaFechaNC()):"";
			String ultimaClienteNC = 
	        		!Validaciones.isObjectNull(
	        				entrada.getInformacionParaPaginado().getUltimoClienteNC())?
	        						entrada.getInformacionParaPaginado().getUltimoClienteNC().toString():"";
	        
	        ListaCreditosresultado respuesta = 
	        		calipsoConsultasClienteProxy.consultaCreditoFiltroCalipso(
							listaClientes, tipoMedioPago, fechaDesde, fechaHasta, 
							acuerdo, moneda, cantidadRegistrosARetornar, 
							ultimoIdDocCuentasCobranzaCTA, ultimaFechaCTA, 
							ultimoClienteCTA, ultimoIdDocCuentasCobranzaNC, 
							ultimaFechaNC, ultimaClienteNC);

	        if (respuesta != null) {
				SalidaCalipsoCreditoWS datos = new SalidaCalipsoCreditoWS();
				
				if (respuesta.getListaCreditos() != null && respuesta.getListaCreditos().getCreditoCalipso() != null) {
					
					for (ListaCreditosdetalle.CreditoCalipso resp: respuesta.getListaCreditos().getCreditoCalipso()) {

						CalipsoCredito credito = new CalipsoCredito();
						credito.setIdClienteLegado(resp.getIdClienteLegado());
						
						ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento 
							documento = new ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento();
						
						if (resp.getIdDocumento()!=null) {
							documento.setTipoComprobante(
								TipoComprobanteEnum.getEnumByName(
										resp.getIdDocumento().getTipoComprobante()));	
							documento.setClaseComprobante(
									TipoClaseComprobanteEnum.getEnumByName(
											resp.getIdDocumento().getClaseComprobante()));
							documento.setSucursalComprobante(
									(!Validaciones.isNullOrEmpty(resp.getIdDocumento().getSucursalComprobante()))? 
											resp.getIdDocumento().getSucursalComprobante():null);
							documento.setNumeroComprobante(
									(!Validaciones.isNullOrEmpty(resp.getIdDocumento().getNumeroComprobante()))? 
											resp.getIdDocumento().getNumeroComprobante():null);
						}
						credito.setIdDocumento(documento);
						
						credito.setMonedaOriginalFactura(
								!Validaciones.isNullOrEmpty(resp.getMonedaOriginalFactura())?
										MonedaEnum.getEnumBySigno2(resp.getMonedaOriginalFactura()):null);
						
						credito.setImporteMonedaOrigen(resp.getImporteMonedaOrigen());
						credito.setImportePesificado(resp.getImportePesificado());
						credito.setSaldoMonedaOrigen(resp.getSaldoMonedaOrigen());
						credito.setSaldoPesificado(resp.getSaldoPesificado());
						credito.setFechaEmision(
								!Validaciones.isNullOrEmpty(resp.getFechaEmision())?
										Utilidad.deserializeAndFormatDate(resp.getFechaEmision(), Utilidad.ANO_MES_DIA):null);
						credito.setFechaVencimiento(
								!Validaciones.isNullOrEmpty(resp.getFechaVencimiento())?
										Utilidad.deserializeAndFormatDate(resp.getFechaVencimiento(), Utilidad.ANO_MES_DIA):null);
						credito.setFechaUltimoMovimiento(
								!Validaciones.isNullOrEmpty(resp.getFechaVencimiento())?
										Utilidad.deserializeAndFormatDate(resp.getFechaUltimoMovimiento(), Utilidad.ANO_MES_DIA):null);
						credito.setTipoCambioActual(resp.getTipoCambioActual());				
						credito.setIdDocCtasCob(resp.getIdDocCtasCob());
						credito.setEstadoMorosidad(resp.getEstadoMorosidad());
						credito.setMarcaReclamo(
								!Validaciones.isNullOrEmpty(resp.getMarcaReclamo())?
										SiNoEnum.getEnumByDescripcionCorta(resp.getMarcaReclamo()):null);
						credito.setMarcaMigradoDeimos(
								!Validaciones.isNullOrEmpty(resp.getMarcaMigradoDeimos())?
										SiNoEnum.getEnumByDescripcionCorta(resp.getMarcaMigradoDeimos()):null);
						//
						if (resp.getInformacionAdicionalTagetikCalipso()!=null) {
							credito.getInformacionAdicionalTagetikCalipso().setRazonSocialCliente(
								resp.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
							credito.getInformacionAdicionalTagetikCalipso().setTipoCliente(
								resp.getInformacionAdicionalTagetikCalipso().getTipoCliente());
							credito.getInformacionAdicionalTagetikCalipso().setCuit(
								resp.getInformacionAdicionalTagetikCalipso().getCUIT());
						} else {
							credito.setInformacionAdicionalTagetikCalipso(null);
						}
						
						//
						if (resp.getInformacionAdicionalDacota()!=null) {
							credito.getInformacionAdicionalDacota().setUnidadOperativa(
								resp.getInformacionAdicionalDacota().getUnidadOperativa());
							credito.getInformacionAdicionalDacota().setSubTipo(
								resp.getInformacionAdicionalDacota().getSubTipo());
							credito.getInformacionAdicionalDacota().setHolding(
								resp.getInformacionAdicionalDacota().getHolding());
						} else {
							credito.setInformacionAdicionalDacota(null);
						}
						
						datos.getListaCreditos().add(credito);
					}
				}
				
				ControlPaginado controlPaginado = new ControlPaginado();
				controlPaginado.setCantidadRegistrosRetornados(respuesta.getControlPaginado().getCantidadRegistrosRetornados());
				controlPaginado.setCantidadRegistrosTotales(respuesta.getControlPaginado().getCantidadRegistrosTotales());
				datos.setControlPaginado(controlPaginado);
				
				ResultadoProcesamiento resultado = new ResultadoProcesamiento();
				resultado.setResultadoImputacion(respuesta.getResultadoProcesamiento().getResultadoImputacion());
				resultado.setCodigoError(respuesta.getResultadoProcesamiento().getCodigoerror());
				resultado.setDescripcionError(respuesta.getResultadoProcesamiento().getDescripcionerror());
				datos.setResultadoProcesamiento(resultado);
				
				return datos;
			}
			throw new WebServiceExcepcion("WS CalipsoConsultaCredito : Se ha producido un error en el webservice");
		
		} catch (WebServiceException e) {
			throw new WebServiceExcepcion("WS CalipsoConsultaCredito : Se ha producido un error en el webservice", e);
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion("WS CalipsoConsultaCredito - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion("WS CalipsoConsultaCredito: Falla de conexion: " + e);
		} catch (ParseException e) {
			throw new NegocioExcepcion(e);
		}
	}

	/*****************************************************************************
	 * Getters & Setters
	 *****************************************************************************/
	public WebServiceConsultasOnlinePortType getCalipsoConsultasClienteProxy() {
		return calipsoConsultasClienteProxy;
	}

	public void setCalipsoConsultasClienteProxy(
			WebServiceConsultasOnlinePortType calipsoConsultasClienteProxy) {
		this.calipsoConsultasClienteProxy = calipsoConsultasClienteProxy;
	}
}
