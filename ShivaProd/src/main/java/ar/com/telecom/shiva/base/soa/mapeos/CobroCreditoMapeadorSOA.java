package ar.com.telecom.shiva.base.soa.mapeos;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.dto.SOA;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;
import ar.com.telecom.shiva.base.mapeadores.MapeadorSOA;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;

public class CobroCreditoMapeadorSOA extends MapeadorSOA {
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	public SOA map(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public DTO map(SOA soa) throws NegocioExcepcion {
		
		CobroCreditoDto dto = new CobroCreditoDto();
		
		if (soa instanceof MicCredito) {
			// Credito MIC 
			MicCredito credMic = (MicCredito) soa;
			dto.setSistemaOrigen(SistemaEnum.MIC);
			dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE.getDescripcion());

			dto.setIdClienteLegado(credMic.getCodigoCliente()!= null?credMic.getCodigoCliente().toString():null);
			dto.setCuenta(credMic.getCuenta()!= null?credMic.getCuenta().toString():null);
			
			if(!Validaciones.isObjectNull(credMic.getTipoCredito())){
				dto.setTipoCredito(credMic.getTipoCredito().getDescripcion());
				dto.setTipoCreditoEnum(credMic.getTipoCredito().name());
				
				if(TipoCreditoEnum.NOTACREDITO.equals(credMic.getTipoCredito())) {
					dto.setIdTipoMedioPago(credMic.getTipoCredito().getIdTipoMedioPago());
					
					dto.setTipoComprobante(TipoComprobanteEnum.CRE.descripcion());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.CRE);
					
					if(!Validaciones.isObjectNull(credMic.getInformacionNotaCredito())){
						dto.setClaseComprobante(credMic.getInformacionNotaCredito().getClaseComprobante());
						dto.setSucursalComprobante(credMic.getInformacionNotaCredito().getSucursalComprobante());
						dto.setNumeroComprobante(credMic.getInformacionNotaCredito().getNumeroComprobante());
						
						if(!Validaciones.isObjectNull(credMic.getInformacionNotaCredito().getClaseComprobante())
								&& !Validaciones.isNullOrEmpty(credMic.getInformacionNotaCredito().getSucursalComprobante())
								&& !Validaciones.isNullOrEmpty(credMic.getInformacionNotaCredito().getNumeroComprobante())){
							dto.setNroDoc(Utilidad.parsearNroDocNoDuc(credMic.getInformacionNotaCredito().getClaseComprobante(), credMic.getInformacionNotaCredito().getSucursalComprobante(), credMic.getInformacionNotaCredito().getNumeroComprobante()));
						}
						//
						dto.setNumeroReferenciaMic(credMic.getInformacionNotaCredito().getNumeroReferenciaMic());
					}
					//
					dto.setFechaValor(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaEmision()));
					dto.setFechaVenc(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaVencimiento()));
					//
					if(!Validaciones.isObjectNull(credMic.getInformacionAdicionalTagetikNotaCreditoMic())) {
						dto.setSubtipo(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getTipoFactura()!= null?credMic.getInformacionAdicionalTagetikNotaCreditoMic().getTipoFactura().toString():null);
						dto.setSubtipoDocumentoDesc(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getDescripcionTipoFactura());
						dto.setTipoFactura(TipoFacturaEnum.getEnumByCodigo(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getTipoFactura()!= null?credMic.getInformacionAdicionalTagetikNotaCreditoMic().getTipoFactura():null));
						dto.setTipoFacturaMicRem(null);
					}
					//Se arma el idCreditoPantalla
					dto.setIdCreditoPantalla((credMic.getTipoCredito()!= null?credMic.getTipoCredito().name():"") + "_" + dto.getNroDoc() + "_" + dto.getNumeroReferenciaMic());
					//
					dto.setOrderByFecha(dto.getFechaVenc());
					dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
					dto.setOrderByNumeroIdentificatorio(dto.getNumeroReferenciaMic()!=null?dto.getNumeroReferenciaMic():"");
					//
				} else {
					//Remanente
					dto.setTipoComprobante(TipoComprobanteEnum.REM.descripcion());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.REM);
					//
					dto.setFechaValor(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaAlta()));
					dto.setFechaAltaCredito(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaAlta()));
					//
					if(!Validaciones.isObjectNull(credMic.getInformacionRemanente()) 
							&& !Validaciones.isObjectNull(credMic.getInformacionRemanente().getCodigoTipoRemanente()))
					{
						TipoRemanenteEnum tipoRemanenteEnum = 
								TipoRemanenteEnum.getEnumByCodigo(
										credMic.getInformacionRemanente().getCodigoTipoRemanente().longValue());
						
						if (tipoRemanenteEnum!=null) {
							dto.setCodigoTipoRemanente(tipoRemanenteEnum.codigo());
							dto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
							dto.setSubtipoDocumentoDesc(credMic.getInformacionRemanente().getDescripcionTipoRemanente());
							dto.setTipoFactura(null);
							dto.setTipoFacturaMicRem(TipoRemanenteEnum.getEnumByDescripcion(credMic.getInformacionRemanente().getDescripcionTipoRemanente()));
							//
							dto.setIdTipoMedioPago(tipoRemanenteEnum.getIdTipoMedioPago());
						}
					}
					
					//Se arma el idCreditoPantalla
					StringBuffer str = new StringBuffer();
					str.append(credMic.getCodigoCliente() != null ? credMic.getCodigoCliente().toString():"");
					String nroDoc = dto.getCuenta() != null ? dto.getCuenta().toString():""; 
						   nroDoc += dto.getSubtipo() != null ? dto.getSubtipo() : "";
					str.append(nroDoc);
					if (TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE.equals(dto.getTipoFacturaMicRem())) {
						str.append("-");
						try {
							str.append(Utilidad.formatDateAAMMDD(Utilidad.parseDatePickerString(dto.getFechaAltaCredito())));
						} catch (ParseException e) {
							throw new NegocioExcepcion(e.getMessage(), e);
						}
					}
					dto.setIdCreditoPantalla(str.toString());
					//
					dto.setOrderByFecha(dto.getFechaAltaCredito());
					dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
					dto.setOrderByNumeroIdentificatorio(nroDoc!=null?nroDoc:"");
					//
				}
			}
			
			//Lo uso en la pantalla
			dto.setSaldoMonOrigen(credMic.getInformacionGeneral().getSaldo()!=null?Utilidad.formatCurrency(credMic.getInformacionGeneral().getSaldo(), true, true, 2):null);
			dto.setSaldoPesificado(dto.getSaldoMonOrigen());
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			dto.setMonedaEnum(MonedaEnum.PES);
			dto.setImpMonOrigen(credMic.getInformacionGeneral().getImporte()!=null?Utilidad.formatCurrency(credMic.getInformacionGeneral().getImporte(), true,true,2):null);
			dto.setImpPesificado(dto.getImpMonOrigen());
			dto.setFechaEmision(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaEmision()));
			dto.setFechaUltimoMov(Utilidad.formatDatePicker(credMic.getInformacionGeneral().getFechaUltimoMovimientoCobroPP()));
			//
			if(!Validaciones.isObjectNull(credMic.getInformacionGeneral())){
				MarcaReclamoEnum marcaReclamoEnum = MarcaReclamoEnum.getEnumByCodigo(credMic.getInformacionGeneral().getCodigoMarcaReclamo());
				dto.setMarcaReclamo(marcaReclamoEnum);
				dto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
				//
				if(ConstantesCobro.CODIGO_ESTADO_CREDITO_DEIMOS.equals(credMic.getInformacionGeneral().getCodigoEstadoCredito())){
					dto.setMarcaMigradoDeimos(SiNoEnum.SI);
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
				}else{
					dto.setMarcaMigradoDeimos(SiNoEnum.NO); 
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO); 
				}
				//
				if(!Validaciones.isNullOrEmpty(credMic.getInformacionGeneral().getCodigoEstadoCredito())){
					EstadoOrigenEnum estadoOrigenEnum = 
							EstadoOrigenEnum.getEnumByCodigo(credMic.getInformacionGeneral().getCodigoEstadoCredito());
					if (estadoOrigenEnum!=null) {
						dto.setEstadoOrigen(estadoOrigenEnum.descripcion());
						dto.setEstadoOrigenEnum(estadoOrigenEnum);
					}
				}
				
				//Puede venir con un espacio, si viene con espacio es MarcaCyQEnum.VACIO 
				if(!Validaciones.isObjectNull(credMic.getInformacionGeneral().getCodigoMarcaCyQ())) {
					MarcaCyQEnum marcaCyQ = MarcaCyQEnum.getEnumByCodigo(credMic.getInformacionGeneral().getCodigoMarcaCyQ()); 
					if (marcaCyQ!=null) {
						dto.setMarcaCyq(marcaCyQ);
					}
				}
				
			}
			//
			dto.setSinDifDeCambio(false);
			//
			if(!Validaciones.isObjectNull(credMic.getInformacionAdicionalTagetikNotaCreditoMic())){
				dto.setRazonSocialCliente(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getRazonSocialCliente());
				dto.setTipoCliente(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getTipoCliente());
				dto.setCuit(Validaciones.isNullOrEmpty(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getCuit())?null:
					("00-00000000-0".equalsIgnoreCase(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getCuit())
						|| "0000000000000".equalsIgnoreCase(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getCuit()))?null:
							credMic.getInformacionAdicionalTagetikNotaCreditoMic().getCuit());
				dto.setCicloFacturacion(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getCicloFacturacion());
				dto.setMarketingCustomerGroup(credMic.getInformacionAdicionalTagetikNotaCreditoMic().getMarketingCustomerGroup());
			}
			if(!Validaciones.isObjectNull(credMic.getInformacionAdicionalDacota())){
				dto.setCodigoTarifa(credMic.getInformacionAdicionalDacota().getCodigoTarifa());
				dto.setIndicadorPeticionCorte(credMic.getInformacionAdicionalDacota().getIndicadorPeticionCorte());
				dto.setFechaVencimientoMora(Utilidad.formatDatePicker(credMic.getInformacionAdicionalDacota().getFechaVencimientoMora()));
			}
			// u578936 
//			String operacionAnalista = cobroOnlineServicio.getAnalistaCobroCredito(dto.getIdCreditoPantalla(),credMic.getIdCobro());
//			dto.setOpeAsocAnalista(operacionAnalista);
//			if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
//				dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
//			} else {
//				dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
//			}
			//
//			boolean marcaCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(dto.getIdCreditoPantalla());
//			if (marcaCobroPendienteProceso) {
//				dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
//			} else {
//				dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
//			}
//			boolean marcaDesCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProceso(dto.getIdCreditoPantalla());
//			if (marcaDesCobroPendienteProceso) {
//				dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
//			} else {
//				dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
//			}
			cobroOnlineServicio.setearAtributosPorConsulta(dto, credMic.getIdCobro());
		} else if (soa instanceof CalipsoCredito) {
			// Credito Calipso
			CalipsoCredito credCalipso = (CalipsoCredito) soa;
			
			//
			if(!Validaciones.isObjectNull(credCalipso.getIdDocumento())){
				TipoCreditoEnum tipoCreditoEnum = TipoCreditoEnum.getEnumByValor(credCalipso.getIdDocumento().getTipoComprobante().name());
				dto.setTipoCredito(tipoCreditoEnum!=null?tipoCreditoEnum.getDescripcion():null);
				dto.setTipoCreditoEnum(tipoCreditoEnum!=null?tipoCreditoEnum.name():null);
				dto.setTipoComprobante(credCalipso.getIdDocumento().getTipoComprobante().descripcion());
				dto.setTipoComprobanteEnum(credCalipso.getIdDocumento().getTipoComprobante());
				//
				dto.setIdTipoMedioPago(tipoCreditoEnum!=null?tipoCreditoEnum.getIdTipoMedioPago():null);
				//
				dto.setClaseComprobante(credCalipso.getIdDocumento().getClaseComprobante());
				dto.setSucursalComprobante(credCalipso.getIdDocumento().getSucursalComprobante());
				dto.setNumeroComprobante(credCalipso.getIdDocumento().getNumeroComprobante());
				
				if(!Validaciones.isObjectNull(credCalipso.getIdDocumento().getClaseComprobante())
					&& !Validaciones.isNullOrEmpty(credCalipso.getIdDocumento().getSucursalComprobante())
					&& !Validaciones.isNullOrEmpty(credCalipso.getIdDocumento().getNumeroComprobante()))
				{
					dto.setNroDoc(Utilidad.parsearNroDocNoDuc(credCalipso.getIdDocumento().getClaseComprobante(), credCalipso.getIdDocumento().getSucursalComprobante(), credCalipso.getIdDocumento().getNumeroComprobante()));
				}
			}
			//Lo uso en la pantalla
			dto.setIdCreditoPantalla(dto.getTipoComprobanteEnum().name() + "_" + dto.getNroDoc());
			//
			//
			dto.setSistemaOrigen(SistemaEnum.CALIPSO);
			dto.setIdDocumentoCuentaCobranza(new Long(credCalipso.getIdDocCtasCob().toString()));
			dto.setSistemaDescripcion(SistemaEnum.CALIPSO.getDescripcion());
			dto.setIdClienteLegado(credCalipso.getIdClienteLegado()!=null?credCalipso.getIdClienteLegado().toString():null);
			//
			dto.setSaldoMonOrigen(credCalipso.getSaldoMonedaOrigen()!=null?Utilidad.formatCurrency(credCalipso.getSaldoMonedaOrigen(), true,true,2):null);
			dto.setMoneda(credCalipso.getMonedaOriginalFactura()!=null?credCalipso.getMonedaOriginalFactura().getSigno2():null);
			dto.setImpMonOrigen(credCalipso.getImporteMonedaOrigen()!=null?Utilidad.formatCurrency(credCalipso.getImporteMonedaOrigen(), true,true,2):null);
			dto.setImpPesificado(credCalipso.getImportePesificado()!=null?Utilidad.formatCurrency(credCalipso.getImportePesificado(), true,true,2):null);
			dto.setSaldoPesificado(credCalipso.getSaldoPesificado()!=null?Utilidad.formatCurrency(credCalipso.getSaldoPesificado(), true,true,2):null);
			//
			dto.setFechaValor(Utilidad.formatDatePicker(credCalipso.getFechaEmision()));
			dto.setFechaEmision(Utilidad.formatDatePicker(credCalipso.getFechaEmision()));
			dto.setFechaVenc(Utilidad.formatDatePicker(credCalipso.getFechaVencimiento()));
			dto.setFechaUltimoMov(Utilidad.formatDatePicker(credCalipso.getFechaUltimoMovimiento()));
			dto.setTipoCambio(
				credCalipso.getTipoCambioActual()!=null?Utilidad.formatDecimales(credCalipso.getTipoCambioActual(), 7):""
			);
			if (credCalipso.getMarcaReclamo()!=null) {
				MarcaReclamoEnum marcaReclamoEnum = MarcaReclamoEnum.getEnumByCodigo(credCalipso.getMarcaReclamo().getDescripcionCorta());
				dto.setMarcaReclamo(marcaReclamoEnum);
				dto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
			}
			//
			dto.setMarcaMigradoDeimos(credCalipso.getMarcaMigradoDeimos());
			dto.setMarcaMigradaOrigenEnum(credCalipso.getMarcaMigradoDeimos());
			//
			if(!Validaciones.isObjectNull(credCalipso.getMonedaOriginalFactura())){
				dto.setMonedaEnum(credCalipso.getMonedaOriginalFactura());
				
				if(MonedaEnum.DOL.equals(credCalipso.getMonedaOriginalFactura())){
					dto.setSinDifDeCambio(true);
				}else{
					dto.setSinDifDeCambio(false);
				}
			}
			//
			//
			if(!Validaciones.isObjectNull(credCalipso.getInformacionAdicionalTagetikCalipso())){
				dto.setRazonSocialCliente(credCalipso.getInformacionAdicionalTagetikCalipso().getRazonSocialCliente());
				dto.setTipoCliente(credCalipso.getInformacionAdicionalTagetikCalipso().getTipoCliente());
				dto.setCuit(Utilidad.formatearCuit(credCalipso.getInformacionAdicionalTagetikCalipso().getCuit()));
			}
			if(!Validaciones.isObjectNull(credCalipso.getInformacionAdicionalDacota())){
				dto.setUnidadOperativa(credCalipso.getInformacionAdicionalDacota().getUnidadOperativa());
				dto.setHolding(credCalipso.getInformacionAdicionalDacota().getHolding());
				dto.setSubtipo(credCalipso.getInformacionAdicionalDacota().getSubTipo());
			}
			//
			if(!Validaciones.isNullOrEmpty(credCalipso.getEstadoMorosidad())){
				EstadoOrigenEnum estadoOrigenEnum = 
						EstadoOrigenEnum.getEnumByDescripcion(credCalipso.getEstadoMorosidad());
				if (estadoOrigenEnum!=null) {
					dto.setEstadoOrigen(estadoOrigenEnum.descripcion());
					dto.setEstadoOrigenEnum(estadoOrigenEnum);
				}
			}
			//
			dto.setOrderByFecha(dto.getFechaVenc());
			dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
			//
			if (Validaciones.isObjectNull(dto.getIdDocumentoCuentaCobranza())) {
				dto.setOrderByNumeroIdentificatorio("");
			}  else {
				dto.setOrderByNumeroIdentificatorio(dto.getIdDocumentoCuentaCobranza().toString());
			}

			
			cobroOnlineServicio.setearAtributosPorConsulta(dto, credCalipso.getIdCobro());
		} else {
			throw new NegocioExcepcion("Error de mapeo: Clase no disponible");
		}
		return dto;
		
	}
	
}
