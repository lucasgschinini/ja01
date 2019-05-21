package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;

public class CobroOnlineDebitoMapeador extends Mapeador implements ICobroOnlineDebitoMapeador {
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdDebito modelo = (ShvCobEdDebito) vo;
		
		CobroDebitoDto dto = new CobroDebitoDto();
		//
		dto.setId(modelo.getPk().getIdDebito());
		dto.setIdDebito(modelo.getPk().getIdDebito().toString());
		dto.setCliente(modelo.getIdClienteLegado()!= null?modelo.getIdClienteLegado().toString():"");
		//
		dto.setClaseComprobante(modelo.getClaseComprobante());
		dto.setSucursalComprobante(modelo.getSucursalComprobante());
		dto.setNumeroComprobante(modelo.getNumeroComprobante());
		dto.setSistemaOrigen(modelo.getSistemaOrigen());
		dto.setSistemaDescripcion(modelo.getSistemaOrigen().getDescripcion());
		//
		dto.setTipoComprobanteEnum(modelo.getTipoComprobante());
		if(!Validaciones.isObjectNull(modelo.getTipoComprobante())){
			dto.setTipoDoc(modelo.getTipoComprobante().descripcion());
		}
		//
		dto.setCobrarAl2doVenc(modelo.getCheckCobrarSegVencimiento()!=null?modelo.getCheckCobrarSegVencimiento().getEnum():false);
		dto.setDestransferir3ros(modelo.getCheckDestranferirTerceros()!=null?modelo.getCheckDestranferirTerceros().getEnum():false);
		//dto.setSinDifDeCambio(modelo.getCheckSinDiferenciaCambio()!=null?modelo.getCheckSinDiferenciaCambio().getEnum():false);

		//
		dto.setAcuerdoFacturacion(modelo.getAcuerdoFacturacion());
		if (!Validaciones.isNullEmptyOrDash(modelo.getEstadoAcuerdoFacturacion())) {
			if (SistemaEnum.CALIPSO.equals(modelo.getSistemaOrigen())) {
				dto.setEstadoAcuerdoFacturacionCalipso(modelo.getEstadoAcuerdoFacturacion());
			} else {
				dto.setEstadoAcuerdoFacturacionMic(EstadoAcuerdoFacturacionEnum.getEnumByName(modelo.getEstadoAcuerdoFacturacion()));
			}
		}
		//
		dto.setMarcaCyq(modelo.getMarcaCyq());
		dto.setCuenta(modelo.getCuenta()!=null?modelo.getCuenta().toString():"");
		dto.setCuota(modelo.getCuota()!=null?modelo.getCuota().toString():"");
		dto.setEstadoCptosDe3ros(modelo.getEstadoConceptoTerceros() != null ? modelo.getEstadoConceptoTerceros().getCodigoMic() : "");
		//
		dto.setFechaEmision(Utilidad.formatDatePicker(modelo.getFechaEmision()));
		dto.setFechaPuestaCobro(Utilidad.formatDatePicker(modelo.getFechaPuestaCobro()));
		dto.setFechaUltPagoParcial(Utilidad.formatDatePicker(modelo.getFechaUltimoPagoParcial()));
		dto.setFechaVenc(Utilidad.formatDatePicker(modelo.getFechaVencimiento()));
		//
		dto.setIdDocCuentaCobranza(modelo.getIdDocCuentaCobranza());
		//
		dto.setImpACobrar(Utilidad.formatCurrency(modelo.getImporteACobrar(), false, false));
		dto.setImp1erVenc(Utilidad.formatCurrency(modelo.getImportePriVencMonedaOrigen(),false, false));
		dto.setImportePriVencPesificado(modelo.getImportePriVencPesificado());
		dto.setImportePriVencTerceros(modelo.getImportePriVencTerceros());
		dto.setImporteSegVencTerceros(modelo.getImporteSegVencTerceros());
		dto.setImp2doVenc(Utilidad.formatCurrency(modelo.getImporteSegVenc(), false, false));
		dto.setImp3rosTransf(Utilidad.formatCurrency(modelo.getImporteTercerosTransferidos(), false, false));
		//
		if(!Validaciones.isObjectNull(modelo.getMarcaReclamo())){
			MarcaReclamoEnum marcaReclamoEnum = modelo.getMarcaReclamo();
			dto.setMarcaReclamo(marcaReclamoEnum);
			dto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
		}
		//
		dto.setMarcaMigradoDeimos(modelo.getMarcaMigradoDeimos()!=null?modelo.getMarcaMigradoDeimos().getDescripcion():"");
		dto.setMoneda(modelo.getMoneda()!=null?modelo.getMoneda().getSigno2():"");
		dto.setNroConvenio(modelo.getNumeroConvenio()!=null?modelo.getNumeroConvenio().toString():"");
		dto.setNumeroReferenciaMic(modelo.getNumeroReferenciaMic()!=null?modelo.getNumeroReferenciaMic().toString():"");
		dto.setPosibilidadDestransferencia(modelo.getPosibilidadDestransferencia()!=null?modelo.getPosibilidadDestransferencia().getDescripcion():"");
		dto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getSaldoPesificado(), false, false));
		dto.setSaldo1erVencMonOrigen(Utilidad.formatCurrency(modelo.getSaldoPriVencMonedaOrigen(), false, false));
		dto.setMigradoOrigen(modelo.getMarcaMigradoDeimos()!=null?modelo.getMarcaMigradoDeimos().getDescripcion():"");
		dto.setMarcaMigradaOrigenEnum(modelo.getMarcaMigradoDeimos());
		//
		//Fabio CORRECCION SPRINT 4 el tipo de cambio se tiene que mostrar con 7 decimales, como en la grilla de transacciones de la solapa previsualizacion
		BigDecimal tipoCambio = modelo.getTipoDeCambio();
		
		if(!Validaciones.isObjectNull(tipoCambio)){
			dto.setTipoCambio(Utilidad.formatDecimales(tipoCambio, 7));
		}
		//
		dto.setMarcaApropiarDeimos(modelo.getMarcaApropiarDeimos()!=null?modelo.getMarcaApropiarDeimos().getDescripcion():"");
		dto.setOrigen(modelo.getOrigen());
		
		/************SaldosTercero***************/
		dto.setSaldoTerceroFinanciableNOTransferible(modelo.getSaldoTerceroFinanciableNOTransferible());
		dto.setSaldoTerceroFinanciableTransferible(modelo.getSaldoTerceroFinanciableTransferible());
		dto.setSaldoTerceroNOFinanciableTransferible(modelo.getSaldoTerceroNOFinanciableTransferible());
		
		/************TAGETIK***************/
		dto.setRazonSocialCliente(modelo.getRazonSocialCliente());
		dto.setTipoCliente(modelo.getTipoCliente());
		dto.setCuit(modelo.getCuit());
		dto.setCicloFacturacion(modelo.getCicloFacturacion());
		dto.setMarketingCustomerGroup(modelo.getMarketingCustomerGroup());
		dto.setTipoFactura(modelo.getTipoFactura());
		
		/************DAKOTA***************/
		dto.setUnidadOperativa(modelo.getUnidadOperativa());
		dto.setHolding(modelo.getHolding());
		dto.setFechaVencimientoMora(Utilidad.formatDatePicker(modelo.getFechaVencimientoMora()));
		dto.setIndicadorPeticionCorte(modelo.getIndicadorPeticionCorte());
		dto.setCodigoTarifa(modelo.getCodigoTarifa());		
		dto.setSubTipoDakota(modelo.getSubtipoDakota());
		
		/************DEIMOS***************/
		dto.setDmosApropiacionEnDeimos(modelo.getDmosApropiacionEnDeimos());
		if (Validaciones.isObjectNull(modelo.getDmosEstadoTramite())) {
			dto.setEstadoDeimos("-");
		} else {
			dto.setEstadoDeimos(modelo.getDmosEstadoTramite().descripcion());
		}
		dto.setDmosEstadoTramite(modelo.getDmosEstadoTramite());
		dto.setDmosCantidadDeCuotas(modelo.getDmosCantidadDeCuotas());
		dto.setDmosCantidadDeCuotasPagas(modelo.getDmosCantidadDeCuotasPagas());
		dto.setDmosNroConvenio(modelo.getDmosNumeroConvenio()!=null?modelo.getDmosNumeroConvenio().longValue():null);
		
		/************ RESULTADO VALIDACION ***************/
		
		if (!Validaciones.isNullOrEmpty(modelo.getResultadoValidacionDescripcionError())){
			dto.setDescripcionErrorValidacion(modelo.getResultadoValidacionDescripcionError());
		}else{
			dto.setDescripcionErrorValidacion("-");
		}
		
		dto.setResultadoValidacionEstado(modelo.getResultadoValidacionEstado());
		dto.setResultadoValidacionCodigoError(modelo.getResultadoValidacionCodigoError());
		dto.setResultadoValidacionDescripcionError(modelo.getResultadoValidacionDescripcionError());
		
		
		//Importante: Despues de recuperar los datos desde la base de datos, 
		//los seteos especiales tienen que tener el mismo comportamiento que los servicios de SOA
		//
		TipoClaseComprobanteEnum claseComprobante = modelo.getClaseComprobante();
		String sucursalComprobante = modelo.getSucursalComprobante();
		String numeroComprobante = modelo.getNumeroComprobante();
		Long numeroReferenciaMic = modelo.getNumeroReferenciaMic();
		String numeroReferenciaDuc = modelo.getNumeroReferenciaDuc();
		
		if (SistemaEnum.MIC.equals(modelo.getSistemaOrigen())) {
			
			if (TipoComprobanteEnum.DUC.equals(modelo.getTipoComprobante()))
			{
				dto.setEstadoOrigen(modelo.getEstadoDuc()!=null?modelo.getEstadoDuc().descripcion():"");
				// En los documentos tipo DUC se persiste el campo EstadoOrigen Sino que se utiliza el campo estadoDuc
				dto.setEstadoOrigenEnum(modelo.getEstadoDuc()!=null?EstadoOrigenEnum.getEnumByName(modelo.getEstadoDuc().name()):null);
				dto.setNumeroReferenciaDuc(!Validaciones.isNullEmptyOrDash(numeroReferenciaDuc)?numeroReferenciaDuc:"");
				dto.setNroDoc(!Validaciones.isNullEmptyOrDash(numeroReferenciaDuc)?numeroReferenciaDuc:null);
				//
				if(!Validaciones.isObjectNull(modelo.getTipoDuc())){
					dto.setSubtipoDocumento(modelo.getTipoDuc().codigo());
					dto.setSubtipoDocumentoDesc(modelo.getTipoDuc().descripcion());
					dto.setTipoDuc(modelo.getTipoDuc()); 
				}
			} else {
				dto.setEstadoOrigen(modelo.getEstadoOrigen()!=null?modelo.getEstadoOrigen().descripcion():"");
				dto.setEstadoOrigenEnum(modelo.getEstadoOrigen());
				//
				if(!Validaciones.isObjectNull(modelo.getSubtipo())){
					dto.setSubtipoDocumento(modelo.getSubtipo());
					TipoFacturaEnum tipoFacturaEnum = TipoFacturaEnum.getEnumByCodigo(new Integer(modelo.getSubtipo()));
					dto.setSubtipoDocumentoDesc(tipoFacturaEnum!=null?tipoFacturaEnum.descripcion():"");
					dto.setTipoFactura(tipoFacturaEnum);
				}
				//
				dto.setClaseComprobante(claseComprobante);
				dto.setSucursalComprobante(sucursalComprobante);
				dto.setNumeroComprobante(numeroComprobante);
				if(!Validaciones.isObjectNull(claseComprobante)
						&& !Validaciones.isNullOrEmpty(sucursalComprobante)
						&& !Validaciones.isNullOrEmpty(numeroComprobante)){
					dto.setNroDoc(Utilidad.parsearNroDocNoDuc(claseComprobante, sucursalComprobante, numeroComprobante));
				} 
				//
				dto.setNumeroReferenciaMic(numeroReferenciaMic!=null?numeroReferenciaMic.toString():null);
			}
			dto.setSinDifDeCambio(false);
		}
		
		if (SistemaEnum.CALIPSO.equals(modelo.getSistemaOrigen())) {
			dto.setEstadoOrigen(modelo.getEstadoOrigen()!=null?modelo.getEstadoOrigen().descripcion():"");
			dto.setEstadoOrigenEnum(modelo.getEstadoOrigen());
			
			if(!Validaciones.isObjectNull(claseComprobante)
					&& !Validaciones.isNullOrEmpty(sucursalComprobante)
					&& !Validaciones.isNullOrEmpty(numeroComprobante)){
				dto.setNroDoc(Utilidad.parsearNroDocNoDuc(claseComprobante, sucursalComprobante, numeroComprobante));
			}
			dto.setClaseComprobante(claseComprobante);
			dto.setSucursalComprobante(sucursalComprobante);
			dto.setNumeroComprobante(numeroComprobante);
			//
			dto.setNumeroReferenciaMic(null);
			dto.setNumeroReferenciaDuc(null);
			dto.setSinDifDeCambio(modelo.getCheckSinDiferenciaCambio()!=null?modelo.getCheckSinDiferenciaCambio().getEnum():false);
		}
		
		//TODO: Sacar el if
		if (Validaciones.isNullEmptyOrDash(modelo.getIdDebitoReferencia())) {
			String idDebitoPantalla = "";
			if(!Validaciones.isObjectNull(modelo.getTipoComprobante())){
				idDebitoPantalla = modelo.getTipoComprobante().name() + "_" + dto.getNroDoc();
			}else{
				idDebitoPantalla = dto.getNroDoc();
			}
			dto.setIdDebitoPantalla(idDebitoPantalla);
		} else {
			dto.setIdDebitoPantalla(modelo.getIdDebitoReferencia());
		}
		
		dto.setOrderByFecha(dto.getFechaVenc());
		dto.setOrderByIdClienteLegado(dto.getCliente());
		dto.setOrderByNumeroIdentificatorio(dto.getNroDoc()!=null?dto.getNroDoc():"");
		
		dto.setMonedaImporteCobrar(modelo.getMonedaImporteACobrar() != null?modelo.getMonedaImporteACobrar().name():null);
		
		if (!Validaciones.isObjectNull(modelo.getHabilitarEdicionSinDifCambio())) {
			dto.setHabilitarEdicionSinDifCambio(modelo.getHabilitarEdicionSinDifCambio().name());
		}
		
		
		if(!Validaciones.isObjectNull(modelo.getImporte1erVencimientoPesificadoFechaCotizacion())){
			dto.setImporte1erVencimientoPesificadoFechaCotizacion(Utilidad.formatCurrency(modelo.getImporte1erVencimientoPesificadoFechaCotizacion(), false, false));
		}

		if(!Validaciones.isObjectNull(modelo.getSaldoPesificadoFechaCotizacion())){
			dto.setSaldoPesificadoFechaCotizacion(Utilidad.formatCurrency(modelo.getSaldoPesificadoFechaCotizacion(), false, false));
		}
	
		if(!Validaciones.isObjectNull(modelo.getTipoCambioFechaCotizacion())){
			dto.setTipoCambioFechaCotizacion(Utilidad.formatDecimales(modelo.getTipoCambioFechaCotizacion(), 7));
		}
		
		if(!Validaciones.isObjectNull(modelo.getFechaTipoCambio())){
			dto.setFechaTipoCambio(Utilidad.formatDatePicker(modelo.getFechaTipoCambio()));			
		}
		dto.setSociedad(modelo.getSociedad());
		
		return dto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroDebitoDto debitoDto = (CobroDebitoDto) dto;
		
		ShvCobEdDebito debitoModelo = (ShvCobEdDebito)	(vo != null ? vo : new ShvCobEdDebito());
		try{
			
			debitoModelo.setIdDebitoReferencia(!Validaciones.isNullEmptyOrDash(debitoDto.getIdDebitoPantalla())?debitoDto.getIdDebitoPantalla():null);
			
			debitoModelo.setIdClienteLegado(Long.valueOf(debitoDto.getCliente()));
			
			debitoModelo.setSistemaOrigen(debitoDto.getSistemaOrigen());
			
			debitoModelo.setClaseComprobante(debitoDto.getClaseComprobante());
			debitoModelo.setSucursalComprobante(!Validaciones.isNullEmptyOrDash(debitoDto.getSucursalComprobante())?debitoDto.getSucursalComprobante():null);
			debitoModelo.setNumeroComprobante(!Validaciones.isNullEmptyOrDash(debitoDto.getNumeroComprobante())?debitoDto.getNumeroComprobante():null);
			
			//
			debitoModelo.setTipoComprobante(TipoComprobanteEnum.getEnumByDescripcion(debitoDto.getTipoDoc()));
			if (TipoComprobanteEnum.DUC.equals(debitoModelo.getTipoComprobante())) {
				debitoModelo.setEstadoDuc(!Validaciones.isNullEmptyOrDash(debitoDto.getEstadoOrigen())?EstadoDUCEnum.getEnumByDescripcion(debitoDto.getEstadoOrigen()):null);
				debitoModelo.setTipoDuc(TipoDUCEnum.getEnumByCodigo(Utilidad.rellenarCerosIzquierda(debitoDto.getSubtipoDocumento(),2)));
				debitoModelo.setNumeroReferenciaDuc(!Validaciones.isNullEmptyOrDash(debitoDto.getNumeroReferenciaDuc())?debitoDto.getNumeroReferenciaDuc():null);
			} else {
				debitoModelo.setEstadoOrigen(!Validaciones.isNullEmptyOrDash(debitoDto.getEstadoOrigen())?EstadoOrigenEnum.getEnumByDescripcion(debitoDto.getEstadoOrigen()):null);
				debitoModelo.setSubtipo(!Validaciones.isNullEmptyOrDash(debitoDto.getSubtipoDocumento())?debitoDto.getSubtipoDocumento():null);
				debitoModelo.setNumeroReferenciaMic(!Validaciones.isNullEmptyOrDash(debitoDto.getNumeroReferenciaMic())?Long.valueOf(debitoDto.getNumeroReferenciaMic()):null);
			}
			//
			debitoModelo.setCuenta(!Validaciones.isNullEmptyOrDash(debitoDto.getCuenta())?Long.valueOf(Long.valueOf(debitoDto.getCuenta())):null);
			//
			debitoModelo.setNumeroReferenciaMic(!Validaciones.isNullEmptyOrDash(debitoDto.getNumeroReferenciaMic())?Long.valueOf(debitoDto.getNumeroReferenciaMic()):null); 
			debitoModelo.setFechaVencimiento(Utilidad.parseDatePickerString(debitoDto.getFechaVenc()));
			//
			debitoModelo.setMoneda(MonedaEnum.getEnumBySigno2(debitoDto.getMoneda()));
			debitoModelo.setImportePriVencMonedaOrigen(!Validaciones.isNullEmptyOrDash(debitoDto.getImp1erVenc())?Utilidad.stringToBigDecimal(debitoDto.getImp1erVenc()):null);
			debitoModelo.setImporteSegVenc(!Validaciones.isNullEmptyOrDash(debitoDto.getImp2doVenc())?Utilidad.stringToBigDecimal(debitoDto.getImp2doVenc()):null);
			debitoModelo.setImporteTercerosTransferidos(!Validaciones.isNullEmptyOrDash(debitoDto.getImp3rosTransf())?Utilidad.stringToBigDecimal(debitoDto.getImp3rosTransf()):null);
			debitoModelo.setSaldoPriVencMonedaOrigen(!Validaciones.isNullEmptyOrDash(debitoDto.getSaldo1erVencMonOrigen())?Utilidad.stringToBigDecimal(debitoDto.getSaldo1erVencMonOrigen()):null);
			debitoModelo.setSaldoPesificado(!Validaciones.isNullEmptyOrDash(debitoDto.getSaldoPesificado())?Utilidad.stringToBigDecimal(debitoDto.getSaldoPesificado()):null);
			debitoModelo.setEstadoConceptoTerceros(EstadoConceptoTercerosEnum.getEnumByCodigoMic(debitoDto.getEstadoCptosDe3ros()));
			debitoModelo.setImportePriVencPesificado(debitoDto.getImportePriVencPesificado());
			debitoModelo.setImportePriVencTerceros(debitoDto.getImportePriVencTerceros());
			debitoModelo.setImporteSegVencTerceros(debitoDto.getImporteSegVencTerceros());
			debitoModelo.setMarcaReclamo(debitoDto.getMarcaReclamo());
			debitoModelo.setTipoDeCambio(!Validaciones.isNullEmptyOrDash(debitoDto.getTipoCambio()) ? new BigDecimal(debitoDto.getTipoCambio().replace(",", ".")) : null);
			debitoModelo.setFechaEmision(Utilidad.parseDatePickerString(debitoDto.getFechaEmision()));
			debitoModelo.setNumeroConvenio(!Validaciones.isNullEmptyOrDash(debitoDto.getNroConvenio())?Long.valueOf(debitoDto.getNroConvenio()):null);
			debitoModelo.setCuota(!Validaciones.isNullEmptyOrDash(debitoDto.getCuota())?Long.valueOf(Long.valueOf(debitoDto.getCuota())):null);
			debitoModelo.setFechaUltimoPagoParcial(Utilidad.parseDatePickerString(debitoDto.getFechaUltPagoParcial()));
			
			// Campos adicionales que se editan al seleccionar un debito
			debitoModelo.setCheckCobrarSegVencimiento(debitoDto.isCobrarAl2doVenc()?SiNoEnum.SI:SiNoEnum.NO);
			debitoModelo.setCheckDestranferirTerceros(debitoDto.isDestransferir3ros()?SiNoEnum.SI:SiNoEnum.NO);
			debitoModelo.setImporteACobrar(!Validaciones.isNullEmptyOrDash(debitoDto.getImpACobrar())?Utilidad.stringToBigDecimal(debitoDto.getImpACobrar()):null);
			debitoModelo.setCheckSinDiferenciaCambio(debitoDto.isSinDifDeCambio()?SiNoEnum.SI:SiNoEnum.NO);
			
			// Campos de acuerdo
			debitoModelo.setAcuerdoFacturacion(!Validaciones.isNullEmptyOrDash(debitoDto.getAcuerdoFacturacion())? debitoDto.getAcuerdoFacturacion():null);
			if (SistemaEnum.CALIPSO.equals(debitoModelo.getSistemaOrigen())) {
				debitoModelo.setEstadoAcuerdoFacturacion(!Validaciones.isNullEmptyOrDash(debitoDto.getEstadoAcuerdoFacturacionCalipso())?debitoDto.getEstadoAcuerdoFacturacionCalipso():null);
			} else {
				debitoModelo.setEstadoAcuerdoFacturacion(debitoDto.getEstadoAcuerdoFacturacionMic()!=null?debitoDto.getEstadoAcuerdoFacturacionMic().name():null);
			}
			
			//
			debitoModelo.setCicloFacturacion(debitoDto.getCicloFacturacion());
			debitoModelo.setMarcaCyq(debitoDto.getMarcaCyq());
			debitoModelo.setCodigoTarifa(!Validaciones.isNullEmptyOrDash(debitoDto.getCodigoTarifa())?debitoDto.getCodigoTarifa():null);
			debitoModelo.setCuit(!Validaciones.isNullEmptyOrDash(debitoDto.getCuit())?debitoDto.getCuit():null);
			//
			debitoModelo.setFechaPuestaCobro(Utilidad.parseDatePickerString(debitoDto.getFechaPuestaCobro()));
			debitoModelo.setFechaVencimientoMora(Utilidad.parseDatePickerString(debitoDto.getFechaVencimientoMora()));
			debitoModelo.setHolding(!Validaciones.isNullEmptyOrDash(debitoDto.getHolding())?debitoDto.getHolding():null);
			debitoModelo.setIdDocCuentaCobranza(debitoDto.getIdDocCuentaCobranza());
			debitoModelo.setIndicadorPeticionCorte(!Validaciones.isNullEmptyOrDash(debitoDto.getIndicadorPeticionCorte())?debitoDto.getIndicadorPeticionCorte():null);
			//
			debitoModelo.setMarcaMigradoDeimos(SiNoEnum.getEnumByDescripcion(debitoDto.getMigradoOrigen()));
			debitoModelo.setMarketingCustomerGroup(!Validaciones.isNullEmptyOrDash(debitoDto.getMarketingCustomerGroup())?debitoDto.getMarketingCustomerGroup():null) ;
			debitoModelo.setPosibilidadDestransferencia(SiNoEnum.getEnumByDescripcion(debitoDto.getPosibilidadDestransferencia()));
			debitoModelo.setRazonSocialCliente(!Validaciones.isNullEmptyOrDash(debitoDto.getRazonSocialCliente())?debitoDto.getRazonSocialCliente():null);
			debitoModelo.setSubtipoDakota(!Validaciones.isNullEmptyOrDash(debitoDto.getSubTipoDakota())?debitoDto.getSubTipoDakota():null);
			//
			debitoModelo.setTipoCliente(!Validaciones.isNullEmptyOrDash(debitoDto.getTipoCliente())?debitoDto.getTipoCliente():null);
			debitoModelo.setTipoFactura(debitoDto.getTipoFactura());
			debitoModelo.setUnidadOperativa(!Validaciones.isNullEmptyOrDash(debitoDto.getUnidadOperativa())?debitoDto.getUnidadOperativa():null);
			debitoModelo.setMarcaApropiarDeimos(SiNoEnum.getEnumByDescripcion(debitoDto.getMarcaApropiarDeimos()));
			debitoModelo.setOrigen(debitoDto.getOrigen());
			//Deimos
			debitoModelo.setDmosApropiacionEnDeimos(debitoDto.getDmosApropiacionEnDeimos());
			debitoModelo.setDmosEstadoTramite(debitoDto.getDmosEstadoTramite());
			debitoModelo.setDmosCantidadDeCuotas(debitoDto.getDmosCantidadDeCuotas());
			debitoModelo.setDmosCantidadDeCuotasPagas(debitoDto.getDmosCantidadDeCuotasPagas());
			debitoModelo.setDmosNumeroConvenio(debitoDto.getDmosNroConvenio()); 
			
			debitoModelo.setResultadoValidacionEstado(
				debitoDto.getResultadoValidacionEstado()!=null?debitoDto.getResultadoValidacionEstado():EstadoDebitoImportadoEnum.VALIDACION_OK
			);
			debitoModelo.setResultadoValidacionCodigoError(
				!Validaciones.isNullEmptyOrDash(debitoDto.getResultadoValidacionCodigoError())?debitoDto.getResultadoValidacionCodigoError():null
			);
			debitoModelo.setResultadoValidacionDescripcionError(
				!Validaciones.isNullEmptyOrDash(debitoDto.getResultadoValidacionDescripcionError())?debitoDto.getResultadoValidacionDescripcionError():null
			);
			
			//
			debitoModelo.setSaldoTerceroFinanciableNOTransferible(debitoDto.getSaldoTerceroFinanciableNOTransferible());
			debitoModelo.setSaldoTerceroFinanciableTransferible(debitoDto.getSaldoTerceroFinanciableTransferible());
			debitoModelo.setSaldoTerceroNOFinanciableTransferible(debitoDto.getSaldoTerceroNOFinanciableTransferible());
			
			if(!Validaciones.isNullEmptyOrDash(debitoDto.getTipoCambioFechaCotizacion())){
				debitoModelo.setTipoCambioFechaCotizacion(Utilidad.stringToBigDecimal(debitoDto.getTipoCambioFechaCotizacion()));
			}
			
			if(!Validaciones.isNullEmptyOrDash(debitoDto.getSaldoPesificadoFechaCotizacion())){
				debitoModelo.setSaldoPesificadoFechaCotizacion(Utilidad.stringToBigDecimal(debitoDto.getSaldoPesificadoFechaCotizacion()));
			}
			
			if(!Validaciones.isNullEmptyOrDash(debitoDto.getImporte1erVencimientoPesificadoFechaCotizacion())){
			debitoModelo.setImporte1erVencimientoPesificadoFechaCotizacion(Utilidad.stringToBigDecimal(debitoDto.getImporte1erVencimientoPesificadoFechaCotizacion()));
			}
			
			if(!Validaciones.isNullEmptyOrDash(debitoDto.getFechaTipoCambio())){
				debitoModelo.setFechaTipoCambio(Utilidad.parseDatePickerString(debitoDto.getFechaTipoCambio()));
			}
			
			if (!Validaciones.isObjectNull(debitoDto.getMonedaImporteCobrar())){
				
				//Si el getEnumBySigla es null, entonces ya tenia seteada La MONEDA IMPORTE A COBRAR, SINO, LO SETEO
				if (!Validaciones.isObjectNull(MonedaEnum.getEnumBySigno2(debitoDto.getMonedaImporteCobrar()))){
					debitoModelo.setMonedaImporteACobrar(MonedaEnum.getEnumBySigno2(debitoDto.getMonedaImporteCobrar()));
				} else {
					debitoModelo.setMonedaImporteACobrar(MonedaEnum.getEnumByName(debitoDto.getMonedaImporteCobrar()));
				}
					
			}
			
			debitoModelo.setHabilitarEdicionSinDifCambio(SiNoEnum.getEnumByName(debitoDto.getHabilitarEdicionSinDifCambio()));
			debitoModelo.setSociedad(SociedadEnum.TELECOM);
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return debitoModelo;
	}
	
	
	/**
	 * Mapeo DebitoDto contra los datos obtenidos del archivo.
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param debitoDto
	 * @return
	 */
	public CobroDebitoDto mapeoDebitoDto(String[] campos, CobroDebitoDto debitoDto) {
		debitoDto.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.PENDIENTE);
		debitoDto.setOrigen(OrigenDebitoEnum.IMPORTACION);
		
		
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.SISTEMA-1])) {
			SistemaEnum sistemaOrigen = SistemaEnum.getEnumByDescripcionCorta(campos[ConstantesCobro.SISTEMA-1].trim().toUpperCase());
			debitoDto.setSistemaOrigen(sistemaOrigen);
			debitoDto.setSistemaDescripcion(sistemaOrigen.getDescripcion());
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1])) {
			TipoComprobanteEnum tipoComprobante = TipoComprobanteEnum.getEnumByName(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
			debitoDto.setTipoComprobanteEnum(tipoComprobante);
			debitoDto.setTipoDoc(tipoComprobante.descripcion());
		}
		boolean flagSinLetra = false;
		boolean flagConLetra = false;
		
		if (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) || 
				campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name())) {

			String[] split = campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().split("-");
			if(split.length == 2){
				flagSinLetra = true;
			}
			if(split.length == 3){
				flagConLetra = true;
			}
			if(flagSinLetra){
				debitoDto.setSucursalComprobante(split[0]);
				debitoDto.setNumeroComprobante(split[1]);
				debitoDto.setNroDoc(debitoDto.getSucursalComprobante() + "-" + debitoDto.getNumeroComprobante());
			} else {
				if(flagConLetra){
					debitoDto.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(split[0]));
					debitoDto.setSucursalComprobante(split[1]);
					debitoDto.setNumeroComprobante(split[2]);
					debitoDto.setNroDoc(
						debitoDto.getClaseComprobante().name() + "-" + 
						debitoDto.getSucursalComprobante() + "-" + debitoDto.getNumeroComprobante()
					);
				}
			}
		} else {
			if(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())){				
				String[] split = campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().split("-");
				debitoDto.setSucursalComprobante(split[0].toUpperCase());
				debitoDto.setNumeroComprobante(split[1]);
				debitoDto.setNroDoc(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().toUpperCase());
			}
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1])) {
			debitoDto.setNumeroReferenciaMic(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.MONEDA-1])) {
			debitoDto.setMoneda(campos[ConstantesCobro.MONEDA-1].trim());//ver si es moneda o moneda origen
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1])) {
			debitoDto.setCobrarAl2doVenc(SiNoEnum.getEnumByDescripcionCorta(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim()).getEnum());
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1])) {
			debitoDto.setDestransferir3ros(SiNoEnum.getEnumByDescripcionCorta(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim()).getEnum());
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.IMPORTE_A_COBRAR-1])) {
			debitoDto.setImpACobrar(campos[ConstantesCobro.IMPORTE_A_COBRAR-1].trim());	
		}

		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1])) {
			debitoDto.setSinDifDeCambio(SiNoEnum.getEnumByDescripcionCorta(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim()).getEnum());
		}


//		debitoDto.setSinDifDeCambio(SiNoEnum.NO.getEnum());
		
		debitoDto.setSaldo1erVencMonOrigen(null);
		debitoDto.setImp3rosTransf(null);
		debitoDto.setImp2doVenc(null);
		debitoDto.setImporteSegVencTerceros(BigDecimal.ZERO);
		debitoDto.setSaldoPesificado(null);
		String idDebitoPantalla = "";
		if(!Validaciones.isObjectNull(debitoDto.getTipoComprobanteEnum())){
			idDebitoPantalla = debitoDto.getTipoComprobanteEnum().name();
		}
		if(!Validaciones.isObjectNull(debitoDto.getNroDoc())){
			idDebitoPantalla = idDebitoPantalla + "_" + debitoDto.getNroDoc();
		}
		debitoDto.setIdDebitoPantalla(idDebitoPantalla);
		return debitoDto;
	}	

}

