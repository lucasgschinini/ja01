package ar.com.telecom.shiva.base.soa.mapeos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.mapeadores.MapeadorSOA;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;

public class CobroDebitoMapeadorSOA extends MapeadorSOA {

	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	public SOA map(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public DTO map(SOA soa) throws NegocioExcepcion {
		CobroDebitoDto dto = new CobroDebitoDto();
		
		if (soa instanceof MicDebito) {
			MicDebito micDebito = (MicDebito) soa;
			
			if(!Validaciones.isObjectNull(micDebito)){
				// Id Documento
				dto.setSistemaOrigen(SistemaEnum.MIC);
				dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
				dto.setOrigen(OrigenDebitoEnum.ONLINE);
				
				dto.setCliente(micDebito.getCodigoCliente().toString());
				dto.setCuenta(micDebito.getCuenta().toString());
				
				//
				dto.setTipoDoc(micDebito.getTipoComprobante()!=null?micDebito.getTipoComprobante().descripcion():null);
				dto.setTipoComprobanteEnum(micDebito.getTipoComprobante());
				//
				
				EstadoOrigenEnum estadoOrigenEnum = null;
				if (!Validaciones.isObjectNull(micDebito.getTipoComprobante())) {
					if (TipoComprobanteEnum.DUC.equals(micDebito.getTipoComprobante())) {
						if (!Validaciones.isObjectNull(micDebito.getCodigoTipoDuc())) {
							dto.setSubtipoDocumento(Utilidad.rellenarCerosIzquierda(micDebito.getCodigoTipoDuc().toString(), 2));
							dto.setTipoDuc(TipoDUCEnum.getEnumByCodigo(Utilidad.rellenarCerosIzquierda(micDebito.getCodigoTipoDuc().toString(), 2))); 
						}
						if (!Validaciones.isObjectNull(micDebito.getDescripcionTipoDuc())) {
							dto.setSubtipoDocumentoDesc(micDebito.getDescripcionTipoDuc().toString());
						}
						//
						dto.setNumeroReferenciaDuc(micDebito.getNumeroReferenciaMic());
						//
						if (!Validaciones.isObjectNull(micDebito.getNumeroReferenciaMic())) {
							dto.setNroDoc(micDebito.getNumeroReferenciaMic().toString());
						}
						//
						if (!Validaciones.isNullOrEmpty(micDebito.getCodigoEstadoDuc())) {
							estadoOrigenEnum = EstadoOrigenEnum.getEnumByCodigo(micDebito.getCodigoEstadoDuc());
						}
					} else {
						if(!Validaciones.isObjectNull(micDebito.getInformacionAdicionalTagetik())){
							if(!Validaciones.isObjectNull(micDebito.getInformacionAdicionalTagetik().getTipoFactura())){
								dto.setSubtipoDocumento(micDebito.getInformacionAdicionalTagetik().getTipoFactura().toString());
								dto.setTipoFactura(TipoFacturaEnum.getEnumByCodigo(micDebito.getInformacionAdicionalTagetik().getTipoFactura()));
							}
							if(!Validaciones.isObjectNull(micDebito.getInformacionAdicionalTagetik().getDescripcionTipoFactura())){
								dto.setSubtipoDocumentoDesc(micDebito.getInformacionAdicionalTagetik().getDescripcionTipoFactura().toString());
							}
						}
						
						if(!Validaciones.isObjectNull(micDebito.getClaseComprobante())
								&& !Validaciones.isNullOrEmpty(micDebito.getSucursalComprobante())
								&& !Validaciones.isNullOrEmpty(micDebito.getNumeroComprobante())){
							dto.setNroDoc(Utilidad.parsearNroDocNoDuc(micDebito.getClaseComprobante(), micDebito.getSucursalComprobante(), micDebito.getNumeroComprobante()));
							
							dto.setClaseComprobante(micDebito.getClaseComprobante());
							dto.setSucursalComprobante(micDebito.getSucursalComprobante());
							dto.setNumeroComprobante(micDebito.getNumeroComprobante());
						}
						
						if (!Validaciones.isNullOrEmpty(micDebito.getCodigoEstadoFactura())) {
							estadoOrigenEnum = EstadoOrigenEnum.getEnumByCodigo(micDebito.getCodigoEstadoFactura());
						}
						//
						dto.setNumeroReferenciaMic(micDebito.getNumeroReferenciaMic());
						//
					}
				}
				//
				if (estadoOrigenEnum!=null) {
					dto.setEstadoOrigen(estadoOrigenEnum.descripcion());
					dto.setEstadoOrigenEnum(estadoOrigenEnum);
				}
				
				//seteo el id para usar en la pantalla
				dto.setIdDebitoPantalla(micDebito.getTipoComprobante().name() + "_" + dto.getNroDoc());
				//
				dto.setAcuerdoFacturacion(micDebito.getAcuerdo());
				if (micDebito.getCodigoEstadoAcuerdoFacturacion()!=null) {
					dto.setEstadoAcuerdoFacturacionMic(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(
							Utilidad.rellenarCerosIzquierda(micDebito.getCodigoEstadoAcuerdoFacturacion().toString(), 2)));
				}
				//
				dto.setFechaVenc(Utilidad.formatDatePicker(micDebito.getFechaVencimiento()));
				dto.setSaldo1erVencMonOrigen(Utilidad.formatCurrency(micDebito.getSaldoAl1erVencimiento(), true, true, 2));
				dto.setMoneda(MonedaEnum.PES.getSigno2());
				dto.setImp1erVenc(Utilidad.formatCurrency(micDebito.getImporteAl1erVencimiento(), true, true, 2));
				dto.setImp2doVenc(Utilidad.formatCurrency(micDebito.getImporteAl2doVencimiento(), true, true, 2));
				dto.setImportePriVencTerceros(micDebito.getImporte1erVencimientoCon3eros());
				dto.setImporteSegVencTerceros(micDebito.getImporte2erVencimientoCon3eros());

				//
				dto.setPosibilidadDestransferencia(micDebito.getPosibilidadDestransferencia()!=null?
						micDebito.getPosibilidadDestransferencia().getDescripcion():null);
				
				if (!Validaciones.isObjectNull(micDebito.getInformacionAdicionalSaldosTerceros())) {
					dto.setSaldoTerceroFinanciableNOTransferible(micDebito.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableNOTransferible());
					dto.setSaldoTerceroFinanciableTransferible(micDebito.getInformacionAdicionalSaldosTerceros().getSaldoTerceroFinanciableTransferible());
					dto.setSaldoTerceroNOFinanciableTransferible(micDebito.getInformacionAdicionalSaldosTerceros().getSaldoTerceroNOFinanciableTransferible());
				}
				
				//Puede venir con un espacio, si viene con espacio es MarcaCyQEnum.VACIO
				if(!Validaciones.isObjectNull(micDebito.getCodigoMarcaCyQ())) {
					MarcaCyQEnum marcaCyQ = MarcaCyQEnum.getEnumByCodigo(micDebito.getCodigoMarcaCyQ()); 
					if (marcaCyQ!=null) {
						dto.setMarcaCyq(marcaCyQ);
					}
				}
				
				if(!Validaciones.isObjectNull(micDebito.getInformacionAdicionalTagetik())){
					dto.setRazonSocialCliente(micDebito.getInformacionAdicionalTagetik().getRazonSocialCliente());
					dto.setTipoCliente(micDebito.getInformacionAdicionalTagetik().getTipoCliente());
					dto.setCuit(Validaciones.isNullOrEmpty(micDebito.getInformacionAdicionalTagetik().getCuit())?null:
						("00-00000000-0".equalsIgnoreCase(micDebito.getInformacionAdicionalTagetik().getCuit())
							|| "0000000000000".equalsIgnoreCase(micDebito.getInformacionAdicionalTagetik().getCuit()))?null:
								micDebito.getInformacionAdicionalTagetik().getCuit());
					dto.setCicloFacturacion(micDebito.getInformacionAdicionalTagetik().getCicloFacturacion());
					dto.setMarketingCustomerGroup(micDebito.getInformacionAdicionalTagetik().getMarketingCustomerGroup());
				}
				if(!Validaciones.isObjectNull(micDebito.getInformacionAdicionalDacota())){
					dto.setCodigoTarifa(micDebito.getInformacionAdicionalDacota().getCodigoTarifa());
					dto.setIndicadorPeticionCorte(micDebito.getInformacionAdicionalDacota().getIndicadorPeticionCorte());
					dto.setFechaVencimientoMora(Utilidad.formatDatePicker(micDebito.getInformacionAdicionalDacota().getFechaVencimientoMora()));
				}
				//
				
				dto.setCobrarAl2doVenc(false); // Editable
				dto.setDestransferir3ros(false); // Editable
				dto.setImpACobrar(""); // Editable
				dto.setEstadoCptosDe3ros(micDebito.getEstadoConceptosTerceros() != null ? micDebito.getEstadoConceptosTerceros().getCodigoMic() : null);
				dto.setImp3rosTransf(Utilidad.formatCurrency(micDebito.getImporte3erosTransferidos(), true, true, 2));
				//
				if(!Validaciones.isObjectNull(micDebito.getCodigoMarcaReclamo())){
					MarcaReclamoEnum marcaReclamoEnum = MarcaReclamoEnum.getEnumByCodigo(micDebito.getCodigoMarcaReclamo());
					dto.setMarcaReclamo(marcaReclamoEnum);
					dto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
				}
				//
				if(EstadoOrigenEnum.MIGRADA.codigo().equals(micDebito.getCodigoEstadoFactura())){
					dto.setMigradoOrigen(SiNoEnum.SI.getDescripcion());
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
				}else{
					dto.setMigradoOrigen(SiNoEnum.NO.getDescripcion());	
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO);
				}			
				
				dto.setFechaEmision(Utilidad.formatDatePicker(micDebito.getFechaEmision()));
				dto.setSinDifDeCambio(false); // Editable
				dto.setFechaUltPagoParcial(Utilidad.formatDatePicker(micDebito.getFechaUltimoPagoParcial()));
				dto.setFechaPuestaCobro(Utilidad.formatDatePicker(micDebito.getFechaPuestaAlCobro()));
				
				dto.setResultadoValidacionDescripcionError("");
				
				dto.setNroConvenio(micDebito.getNumeroConvenio()!=null?micDebito.getNumeroConvenio().toString():null);
				dto.setCuota(micDebito.getCuota()!=null?micDebito.getCuota().toString():null);

				dto.setOrderByFecha(dto.getFechaVenc());
				dto.setOrderByIdClienteLegado(dto.getCliente());
				if (Validaciones.isNullEmptyOrDash(micDebito.getNumeroReferenciaMic())) {
					dto.setOrderByNumeroIdentificatorio("");
				} else {
					// Para MIC DUC --> micDebito.getNumeroReferenciaMic() es el numero de referenci DUC
					dto.setOrderByNumeroIdentificatorio(micDebito.getNumeroReferenciaMic());
				}
				// TODO u578936 Testeo pasa a CobroOnlineServicio.setearAtributosPorConsulta
//				String operacionAnalista = cobroOnlineServicio.getAnalistaCobroDebito(dto.getIdDebitoPantalla(),micDebito.getIdCobro());
//				dto.setOpeAsocAnalista(operacionAnalista);
//				if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
//					dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
//				} else {
//					dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
//				}
//				//
//				boolean marcaCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProceso(dto.getIdDebitoPantalla());
//				if (marcaCobroPendienteProceso) {
//					dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
//				} else {
//					dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
//				}
//
//				boolean marcaDesCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaDebitoEnDescobrosPendienteProceso(dto.getIdDebitoPantalla());
//				if (marcaDesCobroPendienteProceso) {
//					dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
//				} else {
//					dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
//				}
				cobroOnlineServicio.setearAtributosPorConsulta(dto, micDebito.getIdCobro());
			}		
			
		} else if (soa instanceof CalipsoDebito) {
			CalipsoDebito calipsoDeb = (CalipsoDebito) soa;
			
			if(!Validaciones.isObjectNull(calipsoDeb)){
				dto.setSistemaOrigen(SistemaEnum.CALIPSO);
				dto.setSistemaDescripcion(SistemaEnum.CALIPSO.getDescripcion());
				dto.setOrigen(OrigenDebitoEnum.ONLINE);
				if(!Validaciones.isObjectNull(calipsoDeb.getIdDocCtasCob())){
					dto.setIdDocCuentaCobranza(new BigDecimal(calipsoDeb.getIdDocCtasCob()));
				}
				//
				dto.setCliente(calipsoDeb.getIdClienteLegado().toString());
				//
				if(!Validaciones.isObjectNull(calipsoDeb.getIdDocumento())){
					//
					dto.setTipoDoc(calipsoDeb.getIdDocumento().getTipoComprobante().descripcion());
					dto.setTipoComprobanteEnum(calipsoDeb.getIdDocumento().getTipoComprobante());
					//
					dto.setClaseComprobante(calipsoDeb.getIdDocumento().getClaseComprobante());
					dto.setSucursalComprobante(calipsoDeb.getIdDocumento().getSucursalComprobante());
					dto.setNumeroComprobante(calipsoDeb.getIdDocumento().getNumeroComprobante());
					
					if(!Validaciones.isObjectNull(calipsoDeb.getIdDocumento().getClaseComprobante())
							&& !Validaciones.isNullOrEmpty(calipsoDeb.getIdDocumento().getSucursalComprobante())
							&& !Validaciones.isNullOrEmpty(calipsoDeb.getIdDocumento().getNumeroComprobante())){
						dto.setNroDoc(Utilidad.parsearNroDocNoDuc(calipsoDeb.getIdDocumento().getClaseComprobante(), calipsoDeb.getIdDocumentoCalipso().getSucursalComprobante(), calipsoDeb.getIdDocumentoCalipso().getNumeroComprobante()));
					}
					
					//seteo el id para usar en la pantalla
					dto.setIdDebitoPantalla(calipsoDeb.getIdDocumento().getTipoComprobante().name() + "_" + dto.getNroDoc());
				}
				//
				dto.setNumeroReferenciaMic(null);
				dto.setFechaVenc(Utilidad.formatDatePicker(calipsoDeb.getFechaVencimiento()));
				dto.setSaldo1erVencMonOrigen(Utilidad.formatCurrency(calipsoDeb.getSaldo1erVencimientoMonedaOrigen(), true, true, 2));
				dto.setMoneda(calipsoDeb.getMonedaOriginalFactura()!=null?calipsoDeb.getMonedaOriginalFactura().getSigno2():null);
				dto.setImp1erVenc(Utilidad.formatCurrency(calipsoDeb.getImporte1erVencimientoMonedaOrigen(), true, true, 2));				
				dto.setSaldoPesificado(Utilidad.formatCurrency(calipsoDeb.getSaldoPesificado(), true, true, 2));
				dto.setImportePriVencPesificado(calipsoDeb.getImporte1erVencimientoPesificado());
				dto.setCobrarAl2doVenc(false); // Editable
				dto.setDestransferir3ros(false); // Editable
				dto.setImpACobrar(""); // Editable
				//
				if (calipsoDeb.getMarcaReclamo()!=null) {
					MarcaReclamoEnum marcaReclamoEnum = MarcaReclamoEnum.getEnumByCodigo(calipsoDeb.getMarcaReclamo().getDescripcionCorta());
					dto.setMarcaReclamo(marcaReclamoEnum);
					dto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
				}
				//
				dto.setMigradoOrigen(calipsoDeb.getMarcaMigradoDeimos()!=null?calipsoDeb.getMarcaMigradoDeimos().getDescripcion():"");
				dto.setMarcaMigradaOrigenEnum(calipsoDeb.getMarcaMigradoDeimos());
				//
				dto.setTipoCambio(
					calipsoDeb.getTipoCambioActual()!=null?Utilidad.formatDecimales(calipsoDeb.getTipoCambioActual(), 7):""
				);

				dto.setFechaEmision(Utilidad.formatDatePicker(calipsoDeb.getFechaEmision()));
				if (MonedaEnum.DOL.getSigno2().equals(dto.getMoneda())) {
					dto.setSinDifDeCambio(false);
				}
				
				dto.setFechaUltPagoParcial(Utilidad.formatDatePicker(calipsoDeb.getFechaUltimoPagoParcial()));
				dto.setResultadoValidacionDescripcionError("");
				
				//Si el debito es automatico el estado origen sera ENV_A_DA sino el estado origen sera el estado morosidad
				
				EstadoOrigenEnum estadoOrigenEnum = null;
				
				if(!Validaciones.isNullOrEmpty(calipsoDeb.getMarcaAdhesionDebitoAutomatico())){
					if (SiNoEnum.SI.getDescripcionCorta().equalsIgnoreCase(calipsoDeb.getMarcaAdhesionDebitoAutomatico())) {
						estadoOrigenEnum = EstadoOrigenEnum.ENV_A_DA;
					} else if (!Validaciones.isNullOrEmpty(calipsoDeb.getEstadoMorosidad())) {
						estadoOrigenEnum = EstadoOrigenEnum.getEnumByDescripcion(calipsoDeb.getEstadoMorosidad());
					}
				} else if (!Validaciones.isNullOrEmpty(calipsoDeb.getEstadoMorosidad())){
					 estadoOrigenEnum = EstadoOrigenEnum.getEnumByDescripcion(calipsoDeb.getEstadoMorosidad());
				}
				
				if (!Validaciones.isObjectNull(estadoOrigenEnum)) {
					dto.setEstadoOrigen(estadoOrigenEnum.descripcion());
					dto.setEstadoOrigenEnum(estadoOrigenEnum);
				}
				
				//
				dto.setHolding(calipsoDeb.getInformacionAdicionalDacota().getHolding());
				dto.setSubTipoDakota(calipsoDeb.getInformacionAdicionalDacota().getSubTipo());
				dto.setUnidadOperativa(calipsoDeb.getInformacionAdicionalDacota().getUnidadOperativa());
				
				dto.setCuit(calipsoDeb.getInformacionAdicionalTagetikCalipso().getCuit());
				dto.setRazonSocialCliente(calipsoDeb.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
				dto.setTipoCliente(calipsoDeb.getInformacionAdicionalTagetikCalipso().getTipoCliente());
				//
				//dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
				//
				dto.setAcuerdoFacturacion(calipsoDeb.getCodigoAcuerdoFacturacion());
				dto.setEstadoAcuerdoFacturacionCalipso(calipsoDeb.getEstadoAcuerdoFacturacion());
				//
				dto.setOrderByFecha(dto.getFechaVenc());
				dto.setOrderByIdClienteLegado(dto.getCliente());
				if (Validaciones.isObjectNull(calipsoDeb.getIdDocCtasCob())) {
					dto.setOrderByNumeroIdentificatorio("");
				} else {
					dto.setOrderByNumeroIdentificatorio(calipsoDeb.getIdDocCtasCob().toString());
				}
				
				dto.setHabilitarEdicionSinDifCambio("NO");
				
				dto.setTipoCambioFechaCotizacion(calipsoDeb.getTipoCambioFechaCotizacion() != null ? 
						Utilidad.formatDecimales(calipsoDeb.getTipoCambioFechaCotizacion(),7): "");
				
				dto.setImporte1erVencimientoPesificadoFechaCotizacion(calipsoDeb.getImporte1erVencimientoPesificadoFechaCotizacion() != null ?
						Utilidad.formatCurrency(calipsoDeb.getImporte1erVencimientoPesificadoFechaCotizacion(), true, true, 2) : "");

				dto.setSaldoPesificadoFechaCotizacion(calipsoDeb.getSaldoPesificadoFechaCotizacion() != null ?
						Utilidad.formatCurrency(calipsoDeb.getSaldoPesificadoFechaCotizacion(), true, true, 2) : "");
				//
//				String operacionAnalista = cobroOnlineServicio.getAnalistaCobroDebito(dto.getIdDebitoPantalla(),calipsoDeb.getIdCobro());
//				dto.setOpeAsocAnalista(operacionAnalista);
//				if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
//					dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
//				} else {
//					dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
//				}
//				
//				boolean marcaCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProceso(dto.getIdDebitoPantalla());
//				if (marcaCobroPendienteProceso) {
//					dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
//				} else {
//					dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
//				}
//				
//				boolean marcaDesCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaDebitoEnDescobrosPendienteProceso(dto.getIdDebitoPantalla());
//				if (marcaDesCobroPendienteProceso) {
//					dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
//				} else {
//					dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
//				}
				cobroOnlineServicio.setearAtributosPorConsulta(dto, calipsoDeb.getIdCobro());
			}
			
		} else {
			throw new NegocioExcepcion("Error de mapeo: Clase no disponible");
		}
//		boolean marcaReversionDeCobroEdicion = cobroOnlineServicio.obtenerMarcaReversionDeCobroEdicion(dto);
//		if (marcaReversionDeCobroEdicion) {
//			dto.setMarcaReversionDeCobroEdicion(SiNoEnum.SI);
//		} else {
//			dto.setMarcaReversionDeCobroEdicion(SiNoEnum.NO);
//		}
		
		return dto;
	}



}
