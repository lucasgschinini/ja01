package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;

public class CobroOnlineCreditoMapeador extends Mapeador {
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdCredito modelo = (ShvCobEdCredito) vo;
		
		CobroCreditoDto creditoDto = new CobroCreditoDto();

		/**************************** PANTALLA *******************************/
		creditoDto.setId(modelo.getPk().getIdCredito());
		creditoDto.setIdCredito(String.valueOf(modelo.getPk().getIdCredito()));
		creditoDto.setIdClienteLegado(modelo.getIdClienteLegado()!=null?modelo.getIdClienteLegado().toString():"");
		//
		creditoDto.setClaseComprobante(modelo.getClaseComprobante());
		creditoDto.setSucursalComprobante(modelo.getSucursalComprobante());
		creditoDto.setNumeroComprobante(modelo.getNumeroComprobante());
		//
		creditoDto.setCuenta(modelo.getCuenta()!=null?modelo.getCuenta().toString():"");
		creditoDto.setCuit(modelo.getCuit());
		//
		creditoDto.setFechaAltaCredito(Utilidad.formatDatePicker(modelo.getFechaAlta()));
		creditoDto.setFechaEmision(Utilidad.formatDatePicker(modelo.getFechaEmision()));
		creditoDto.setFechaUltimoMov(Utilidad.formatDatePicker(modelo.getFechaUltimoMovimiento()));
		creditoDto.setFechaValor(Utilidad.formatDatePicker(modelo.getFechaValor()));
		creditoDto.setFechaVenc(Utilidad.formatDatePicker(modelo.getFechaVencimiento()));
		//
		creditoDto.setImporteAUtilizar(Utilidad.formatCurrency(modelo.getImporteAUtilizar(), false, false));
		creditoDto.setImpPesificado(Utilidad.formatCurrency(modelo.getImportePesificado(), false, false));
		creditoDto.setSaldoMonOrigen(Utilidad.formatCurrency(modelo.getSaldoMonedaOrigen(), false, false));
		creditoDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getSaldoPesificado(), false, false));
		creditoDto.setImpMonOrigen(Utilidad.formatCurrency(modelo.getImporteMonedaOrigen(), false, false));
		//
		creditoDto.setCodigoTipoRemanente(modelo.getCodigoTipoRemamente());
		//
		creditoDto.setMoneda(modelo.getMoneda().getSigno2());
		creditoDto.setMonedaEnum(modelo.getMoneda());
		//
		creditoDto.setProvincia(modelo.getProvincia());
		creditoDto.setRazonSocialCliente(modelo.getRazonSocialCliente());
		BigDecimal tipoCambio = modelo.getTipoDeCambio();
		if(!Validaciones.isObjectNull(tipoCambio)){
			creditoDto.setTipoCambio(Utilidad.formatDecimales(tipoCambio, 7));
		}
		//
		creditoDto.setSistemaOrigen(modelo.getSistemaOrigen());
		creditoDto.setSistemaDescripcion(modelo.getSistemaOrigen().getDescripcion());
		//		
		/************ VALORES SHIVA **************/
		creditoDto.setFechaDeposito(Utilidad.formatDatePicker(modelo.getFechaDeposito()));
		creditoDto.setFechaIngresoRecibo(Utilidad.formatDatePicker(modelo.getFechaIngresoRecibo()));
		creditoDto.setFechaTrans(Utilidad.formatDatePicker(modelo.getFechaTransferencia()));
		creditoDto.setDescripcionTipoRetencion(modelo.getTipoRetencion()!=null?modelo.getTipoRetencion().getDescripcion():null);
		creditoDto.setReferenciaValor(modelo.getReferenciaValor());
		creditoDto.setIdValor(modelo.getIdValor());
		
		/**************************** WS *******************************/
		creditoDto.setCicloFacturacion(modelo.getCicloFacturacion());
		creditoDto.setMarcaCyq(modelo.getMarcaCyq());
		//
		if(!Validaciones.isObjectNull(modelo.getMarcaReclamo())){
			MarcaReclamoEnum marcaReclamoEnum = modelo.getMarcaReclamo();
			creditoDto.setMarcaReclamo(marcaReclamoEnum);
			creditoDto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
		}
		creditoDto.setMarcaMigradoDeimos(modelo.getMarcaMigradoDeimos());
		creditoDto.setMarcaMigradaOrigenEnum(modelo.getMarcaMigradoDeimos());
		//
		creditoDto.setCodigoTarifa(modelo.getCodigoTarifa());
		creditoDto.setFechaVencimientoMora(Utilidad.formatDatePicker(modelo.getFechaVencimientoMora()));
		creditoDto.setHolding(modelo.getHolding());
		creditoDto.setIdDocumentoCuentaCobranza(modelo.getIdDocumentoCuentaCobranza());
		creditoDto.setIndicadorPeticionCorte(modelo.getIndicadorPeticionCorte());
		//
		creditoDto.setMarketingCustomerGroup(modelo.getMarketingCustomerGroup());
		creditoDto.setNumeroReferenciaMic(modelo.getNroReferenciaMic()!=null?modelo.getNroReferenciaMic().toString():null);
		creditoDto.setNumeroAcuerdo(modelo.getNumeroAcuerdo());
		creditoDto.setTipoCliente(modelo.getTipoCliente());
		creditoDto.setUnidadOperativa(modelo.getUnidadOperativa());
		
		/************DEIMOS***************/
		creditoDto.setDmosApropiacionEnDeimos(modelo.getDmosApropiacionEnDeimos());
		creditoDto.setDmosEstadoTramite(modelo.getDmosEstadoTramite());
		if (Validaciones.isObjectNull(modelo.getDmosApropiacionEnDeimos())) {
			creditoDto.setEstadoDeimos("-");
		} else {
			creditoDto.setEstadoDeimos(modelo.getDmosEstadoTramite().descripcion());
		}
		creditoDto.setDmosCantidadDeCuotas(modelo.getDmosCantidadDeCuotas());
		creditoDto.setDmosCantidadDeCuotasPagas(modelo.getDmosCantidadDeCuotasPagas());
		creditoDto.setDmosNroConvenio(modelo.getDmosNumeroConvenio()!=null?modelo.getDmosNumeroConvenio().longValue():null);
		
		/************ RESULTADO VALIDACION ***************/
		if (Constantes.ERROR_VALIDACION_EDICION_GRILLAS.equals(modelo.getResultadoValidacionCodigoError())) {
			creditoDto.setDescripcionErrorValidacion(modelo.getResultadoValidacionDescripcionError());
		} else {
			creditoDto.setDescripcionErrorValidacion("-");
		}
		creditoDto.setResultadoValidacionEstado(modelo.getResultadoValidacionEstado());
		creditoDto.setResultadoValidacionCodigoError(modelo.getResultadoValidacionCodigoError());
		creditoDto.setResultadoValidacionDescripcionError(modelo.getResultadoValidacionDescripcionError());
		
		//
		creditoDto.setTipoCredito(modelo.getTipoCredito()!=null?modelo.getTipoCredito().getDescripcion():null);
		creditoDto.setTipoCreditoEnum(modelo.getTipoCredito()!=null?modelo.getTipoCredito().name():null);
		//
		creditoDto.setIdTipoMedioPago(modelo.getTipoMedioPago()!=null?modelo.getTipoMedioPago().getIdTipoMedioPago():null);
		//
		creditoDto.setTipoComprobante(modelo.getTipoComprobante()!=null?modelo.getTipoComprobante().descripcion():"");
		creditoDto.setTipoComprobanteEnum(modelo.getTipoComprobante());
		//
		creditoDto.setEstadoOrigen(modelo.getEstadoOrigen()!=null?modelo.getEstadoOrigen().descripcion():"");
		creditoDto.setEstadoOrigenEnum(modelo.getEstadoOrigen());
		//
		creditoDto.setMotivoDescripcion(modelo.getMotivo()!=null?modelo.getMotivo().descripcion():null);
		creditoDto.setMotivo(modelo.getMotivo());
		//
		//
		//Importante: Despues de recuperar los datos desde la base de datos, 
		//los seteos especiales tienen que tener el mismo comportamiento que los servicios de SOA y SHIVA
		//
		if (SistemaEnum.MIC.equals(modelo.getSistemaOrigen())) {
			//MIC-NOTACREDITO
			if(TipoCreditoEnum.NOTACREDITO.equals(modelo.getTipoCredito())) {
				//
				if(!Validaciones.isObjectNull(modelo.getClaseComprobante())
						&& !Validaciones.isNullOrEmpty(modelo.getSucursalComprobante())
						&& !Validaciones.isNullOrEmpty(modelo.getNumeroComprobante())){
					creditoDto.setNroDoc(Utilidad.parsearNroDocNoDuc(modelo.getClaseComprobante(), modelo.getSucursalComprobante(), modelo.getNumeroComprobante()));
				}
				//
				if (!Validaciones.isNullOrEmpty(modelo.getSubtipo())) {
					TipoFacturaEnum tipoFacturaEnum = TipoFacturaEnum.getEnumByCodigo(new Integer(modelo.getSubtipo()));
					
					creditoDto.setSubtipo(modelo.getSubtipo());
					creditoDto.setSubtipoDocumentoDesc(tipoFacturaEnum!=null?tipoFacturaEnum.descripcion():"-");
					creditoDto.setTipoFactura(tipoFacturaEnum);
					creditoDto.setTipoFacturaMicRem(null);
				}
				
				//TODO: Sacar idCreditoPantalla
				creditoDto.setIdCreditoPantalla((modelo.getTipoCredito()!= null?modelo.getTipoCredito().name():"") 
						+ "_" + creditoDto.getNroDoc() + "_" + modelo.getNroReferenciaMic());
				
				
				creditoDto.setOrderByFecha(creditoDto.getFechaVenc());
				creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
				creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNumeroReferenciaMic()!=null?creditoDto.getNumeroReferenciaMic():"");
			} else {
				//MIC-REMANENTE
				if(!Validaciones.isObjectNull(creditoDto.getCodigoTipoRemanente()))
				{
					TipoRemanenteEnum tipoRemanenteEnum = 
							TipoRemanenteEnum.getEnumByCodigo(
									creditoDto.getCodigoTipoRemanente().longValue());
					
					if (tipoRemanenteEnum!=null) {
						creditoDto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
						creditoDto.setSubtipoDocumentoDesc(tipoRemanenteEnum.descripcion());
						creditoDto.setTipoFactura(null);
						creditoDto.setTipoFacturaMicRem(tipoRemanenteEnum);
					}
				}
				
				StringBuffer str = new StringBuffer();
				str.append(creditoDto.getIdClienteLegado() != null ? creditoDto.getIdClienteLegado().toString():"");
				String nroDoc = creditoDto.getCuenta() != null ? creditoDto.getCuenta().toString():""; 
					   nroDoc += creditoDto.getSubtipo() != null ? creditoDto.getSubtipo() : "";
				str.append(nroDoc);
				if (TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE.equals(creditoDto.getTipoFacturaMicRem())) {
					str.append("-");
					try {
						str.append(Utilidad.formatDateAAMMDD(Utilidad.parseDatePickerString(creditoDto.getFechaAltaCredito())));
					} catch (ParseException e) {
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				}
				//TODO: Sacar idCreditoPantalla
				creditoDto.setIdCreditoPantalla(str.toString());
				//
				creditoDto.setOrderByFecha(creditoDto.getFechaAltaCredito());
				creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
				creditoDto.setOrderByNumeroIdentificatorio(nroDoc!=null?nroDoc:"");
			}
			creditoDto.setSinDifDeCambio(false);
			//
		}
		
		if (SistemaEnum.CALIPSO.equals(modelo.getSistemaOrigen())) {
			//
			if(!Validaciones.isObjectNull(modelo.getClaseComprobante())
					&& !Validaciones.isNullOrEmpty(modelo.getSucursalComprobante())
					&& !Validaciones.isNullOrEmpty(modelo.getNumeroComprobante())){
				creditoDto.setNroDoc(Utilidad.parsearNroDocNoDuc(modelo.getClaseComprobante(), modelo.getSucursalComprobante(), modelo.getNumeroComprobante()));
			}
			
			//TODO: Sacar idCreditoPantalla
			creditoDto.setIdCreditoPantalla(creditoDto.getTipoComprobanteEnum().name() + "_" + creditoDto.getNroDoc());
			//
			creditoDto.setOrderByFecha(creditoDto.getFechaVenc());
			creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
			creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNroDoc()!=null?creditoDto.getNroDoc():"");
			creditoDto.setSubtipo(modelo.getSubtipo());

			//
			if(!Validaciones.isObjectNull(modelo.getMoneda())){
				if (MonedaEnum.DOL.equals(modelo.getMoneda())) {
					creditoDto.setSinDifDeCambio(true);
				} else {
					creditoDto.setSinDifDeCambio(false);
				}
			}
		}
		if (SistemaEnum.SHIVA.equals(modelo.getSistemaOrigen())) {
			//
			//Sirve para el uso del semaforo, pero para la edición no lo necesito
			//if (Validaciones.isNullOrEmpty(vista.getIdOrigen())) {
			//	creditoDto.setTipoComprobanteEnumShiva(TipoShivaEnum.AVISO_DE_PAGO);
			//} else {
			//	creditoDto.setTipoComprobanteEnumShiva(TipoShivaEnum.getEnumByIdOrigen(vista.getIdOrigen()));
			//}
			//
			creditoDto.setNroDoc(modelo.getReferenciaValor());
			//
			TipoRetencionEnum tipoRetencionEnum = TipoRetencionEnum.getEnumByIdTipoMedioPago(creditoDto.getIdTipoMedioPago());
			if (tipoRetencionEnum!=null) {
				creditoDto.setSubtipo(tipoRetencionEnum.getIdString());
				creditoDto.setSubtipoDocumentoDesc(tipoRetencionEnum.getDescripcion());
				creditoDto.setDescripcionTipoRetencion(tipoRetencionEnum.getDescripcion());
			}
			//
			//TODO: Sacar idCreditoPantalla
			String idPantalla = creditoDto.getIdValor()
					+ "_" + (modelo.getTipoCredito()!=null?modelo.getTipoCredito().getIdTipoValor():"0")
					+ "_" + creditoDto.getReferenciaValor() + "_" + creditoDto.getIdClienteLegado();
			creditoDto.setIdCreditoPantalla(idPantalla);
			//
			creditoDto.setOrderByFecha(!Validaciones.isNullOrEmpty(creditoDto.getFechaValorShiva(modelo.getTipoCredito())) 
					? creditoDto.getFechaValorShiva(modelo.getTipoCredito()) : null);
			creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
			creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNroDoc()!=null?creditoDto.getNroDoc():"");
			//
			
			//
			creditoDto.setSinDifDeCambio(false);
		}
		
		//
		//TODO: Ahi se usa (ver sacar idCreditoPantalla)
		if (!Validaciones.isNullEmptyOrDash(modelo.getIdCreditoReferencia())) {
			creditoDto.setIdCreditoPantalla(modelo.getIdCreditoReferencia());
		}
				
		creditoDto.setMonedaImporteUtilizar(modelo.getMonedaImporteAUtilizar() != null? modelo.getMonedaImporteAUtilizar().name():null);
		
		return creditoDto;
	}
	
	
	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroCreditoDto creditoDto = (CobroCreditoDto) dto;
		
		ShvCobEdCredito creditoModelo = (ShvCobEdCredito)
				(vo != null ? vo : new ShvCobEdCredito());
		try{
			/************************** PANTALLA *****************************/
			//
			creditoModelo.setIdCreditoReferencia(!Validaciones.isNullEmptyOrDash(creditoDto.getIdCreditoPantalla())?creditoDto.getIdCreditoPantalla():null);
			//
			creditoModelo.setSistemaOrigen(creditoDto.getSistemaOrigen());
			//
			creditoModelo.setClaseComprobante(creditoDto.getClaseComprobante());
			creditoModelo.setSucursalComprobante(!Validaciones.isNullEmptyOrDash(creditoDto.getSucursalComprobante())?creditoDto.getSucursalComprobante():null);
			creditoModelo.setNumeroComprobante(!Validaciones.isNullEmptyOrDash(creditoDto.getNumeroComprobante())?creditoDto.getNumeroComprobante():null);
			//
			creditoModelo.setTipoComprobante(creditoDto.getTipoComprobanteEnum());
			creditoModelo.setTipoCredito(TipoCreditoEnum.getEnumByName(creditoDto.getTipoCreditoEnum()));
			//
			String idTipoMedioPago = "";
			if (creditoModelo.getTipoCredito()!=null) 
			{
				if (SistemaEnum.SHIVA.equals(creditoDto.getSistemaOrigen())) {
					if (!Validaciones.isNullEmptyOrDash(creditoDto.getSubtipo())) {
						TipoRetencionEnum tipoRetencionEnum = TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(creditoDto.getSubtipo()));
						creditoModelo.setTipoRetencion(tipoRetencionEnum);
						if (tipoRetencionEnum != null) {
							idTipoMedioPago = tipoRetencionEnum.getIdTipoMedioPago();
						}
					} else {
						idTipoMedioPago = creditoModelo.getTipoCredito().getIdTipoMedioPago();
					}
				}
				if (SistemaEnum.MIC.equals(creditoDto.getSistemaOrigen())) {
					if (!Validaciones.isObjectNull(creditoDto.getCodigoTipoRemanente())) {
						creditoModelo.setCodigoTipoRemamente(creditoDto.getCodigoTipoRemanente());
						
						TipoRemanenteEnum tipoRemanenteEnum = TipoRemanenteEnum.getEnumByCodigo(creditoDto.getCodigoTipoRemanente());
						if (tipoRemanenteEnum!=null) {
							idTipoMedioPago = tipoRemanenteEnum.getIdTipoMedioPago();
						}
					} else {
						idTipoMedioPago = creditoModelo.getTipoCredito().getIdTipoMedioPago(); 
						
					}
				}
				if (SistemaEnum.CALIPSO.equals(creditoDto.getSistemaOrigen())) {
					idTipoMedioPago = creditoModelo.getTipoCredito().getIdTipoMedioPago();
				}
			}
			if (!Validaciones.isNullEmptyOrDash(idTipoMedioPago)) {
				Modelo tipoMedPagoModelo = tipoMedioPagoDao.buscar(idTipoMedioPago);
				creditoModelo.setTipoMedioPago(tipoMedPagoModelo!=null? (ShvParamTipoMedioPago)tipoMedPagoModelo : null);
			}
			//
			creditoModelo.setIdClienteLegado(!Validaciones.isNullEmptyOrDash(creditoDto.getIdClienteLegado())?Long.valueOf(creditoDto.getIdClienteLegado()):null);
			creditoModelo.setCuenta(!Validaciones.isNullEmptyOrDash(creditoDto.getCuenta())?Long.valueOf(creditoDto.getCuenta()):null);
			creditoModelo.setMoneda(MonedaEnum.getEnumBySigno2(creditoDto.getMoneda()));
			creditoModelo.setImporteMonedaOrigen(!Validaciones.isNullEmptyOrDash(creditoDto.getImpMonOrigen())?Utilidad.stringToBigDecimal(creditoDto.getImpMonOrigen()):null);
			creditoModelo.setImportePesificado(!Validaciones.isNullEmptyOrDash(creditoDto.getImpPesificado())?Utilidad.stringToBigDecimal(creditoDto.getImpPesificado()):null);
			creditoModelo.setImporteAUtilizar(!Validaciones.isNullEmptyOrDash(creditoDto.getImporteAUtilizar())?Utilidad.stringToBigDecimal(creditoDto.getImporteAUtilizar()):null);
			creditoModelo.setSaldoPesificado(!Validaciones.isNullEmptyOrDash(creditoDto.getSaldoPesificado())?Utilidad.stringToBigDecimal(creditoDto.getSaldoPesificado()):null);
			creditoModelo.setSaldoMonedaOrigen(!Validaciones.isNullEmptyOrDash(creditoDto.getSaldoMonOrigen())?Utilidad.stringToBigDecimal(creditoDto.getSaldoMonOrigen()):null);
			//
			creditoModelo.setFechaValor(!Validaciones.isNullEmptyOrDash(creditoDto.getFechaValor())?Utilidad.parseDatePickerString(creditoDto.getFechaValor()):null);
			creditoModelo.setFechaAlta(!Validaciones.isNullEmptyOrDash(creditoDto.getFechaAltaCredito())?Utilidad.parseDatePickerString(creditoDto.getFechaAltaCredito()):null);
			creditoModelo.setFechaEmision(!Validaciones.isNullEmptyOrDash(creditoDto.getFechaEmision())?Utilidad.parseDatePickerString(creditoDto.getFechaEmision()): null);
			creditoModelo.setFechaUltimoMovimiento(!Validaciones.isNullEmptyOrDash(creditoDto.getFechaUltimoMov())?Utilidad.parseDatePickerString(creditoDto.getFechaUltimoMov()):null);
			creditoModelo.setFechaVencimiento(!Validaciones.isNullEmptyOrDash(creditoDto.getFechaVenc())?Utilidad.parseDatePickerString(creditoDto.getFechaVenc()):null);

			creditoModelo.setTipoDeCambio(!Validaciones.isNullEmptyOrDash(creditoDto.getTipoCambio()) ? new BigDecimal(creditoDto.getTipoCambio().replace(",", ".")) : null);

			creditoModelo.setProvincia(!Validaciones.isNullEmptyOrDash(creditoDto.getProvincia())?creditoDto.getProvincia():null);
			creditoModelo.setCuit(!Validaciones.isNullEmptyOrDash(creditoDto.getCuit())?creditoDto.getCuit():null);
			creditoModelo.setEstadoOrigen(!Validaciones.isNullEmptyOrDash(creditoDto.getEstadoOrigen())?EstadoOrigenEnum.getEnumByDescripcion(creditoDto.getEstadoOrigen()):null);
			//
			creditoModelo.setSubtipo(!Validaciones.isNullEmptyOrDash(creditoDto.getSubtipo())?creditoDto.getSubtipo():null);
			//
			if (SistemaEnum.SHIVA.equals(creditoDto.getSistemaOrigen())) {
				creditoModelo.setIdValor(creditoDto.getIdValor());
				creditoModelo.setFechaIngresoRecibo(Utilidad.parseDatePickerString(creditoDto.getFechaIngresoRecibo()));
				creditoModelo.setFechaDeposito(Utilidad.parseDatePickerString(creditoDto.getFechaDeposito()));
				creditoModelo.setFechaTransferencia(Utilidad.parseDatePickerString(creditoDto.getFechaTrans()));
				creditoModelo.setTipoRetencion(!Validaciones.isNullEmptyOrDash(creditoDto.getSubtipo())?TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(creditoDto.getSubtipo())):null);
				creditoModelo.setReferenciaValor(!Validaciones.isNullEmptyOrDash(creditoDto.getNroDoc())?creditoDto.getNroDoc():null);
				creditoModelo.setMotivo(creditoDto.getMotivo());
			}
			//
			creditoModelo.setMarcaCyq(creditoDto.getMarcaCyq());
			creditoModelo.setMarcaReclamo(creditoDto.getMarcaReclamo());
			creditoModelo.setMarcaMigradoDeimos(creditoDto.getMarcaMigradoDeimos());
			//
			creditoModelo.setIdDocumentoCuentaCobranza(creditoDto.getIdDocumentoCuentaCobranza());
			creditoModelo.setNroReferenciaMic(!Validaciones.isNullEmptyOrDash(creditoDto.getNumeroReferenciaMic())?Long.valueOf(creditoDto.getNumeroReferenciaMic()):null);
			creditoModelo.setNumeroAcuerdo(creditoDto.getNumeroAcuerdo());
			//
			/**************************** OTROS SOA (campos ocultos) *******************************/
			creditoModelo.setRazonSocialCliente(!Validaciones.isNullEmptyOrDash(creditoDto.getRazonSocialCliente())?creditoDto.getRazonSocialCliente():null);
			creditoModelo.setTipoCliente(!Validaciones.isNullEmptyOrDash(creditoDto.getTipoCliente())?creditoDto.getTipoCliente():null);
			creditoModelo.setUnidadOperativa(!Validaciones.isNullEmptyOrDash(creditoDto.getUnidadOperativa())?creditoDto.getUnidadOperativa():null);
			creditoModelo.setHolding(!Validaciones.isNullEmptyOrDash(creditoDto.getHolding())?creditoDto.getHolding():null);
			creditoModelo.setCicloFacturacion(creditoDto.getCicloFacturacion());
			creditoModelo.setMarketingCustomerGroup(!Validaciones.isNullEmptyOrDash(creditoDto.getMarketingCustomerGroup())?creditoDto.getMarketingCustomerGroup():null);
			creditoModelo.setCodigoTarifa(!Validaciones.isNullEmptyOrDash(creditoDto.getCodigoTarifa())?creditoDto.getCodigoTarifa():null);
			creditoModelo.setIndicadorPeticionCorte(!Validaciones.isNullEmptyOrDash(creditoDto.getIndicadorPeticionCorte())?creditoDto.getIndicadorPeticionCorte():null);
			creditoModelo.setFechaVencimientoMora(Utilidad.parseDatePickerString(creditoDto.getFechaVencimientoMora()));
			creditoModelo.setSubtipo(!Validaciones.isNullEmptyOrDash(creditoDto.getSubtipo())?creditoDto.getSubtipo():null);
			
			
			/**************************** Deimos *******************************/
			creditoModelo.setDmosApropiacionEnDeimos(creditoDto.getDmosApropiacionEnDeimos());
			creditoModelo.setDmosEstadoTramite(creditoDto.getDmosEstadoTramite());
			creditoModelo.setDmosCantidadDeCuotas(creditoDto.getDmosCantidadDeCuotas());
			creditoModelo.setDmosCantidadDeCuotasPagas(creditoDto.getDmosCantidadDeCuotasPagas());
			creditoModelo.setDmosNumeroConvenio(creditoDto.getDmosNroConvenio()); 
			//
			if (!Validaciones.isNullEmptyOrDash(creditoDto.getDescripcionErrorValidacion())) {
				creditoModelo.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_ERROR);
				creditoModelo.setResultadoValidacionCodigoError(Constantes.ERROR_VALIDACION_EDICION_GRILLAS);
				creditoModelo.setResultadoValidacionDescripcionError(creditoDto.getDescripcionErrorValidacion());
			} else {
				creditoModelo.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_OK);
				creditoModelo.setResultadoValidacionCodigoError(null);
				creditoModelo.setResultadoValidacionDescripcionError(null);
			}
			creditoModelo.setEstadoOrigen(!Validaciones.isNullEmptyOrDash(creditoDto.getEstadoOrigen())?EstadoOrigenEnum.getEnumByDescripcion(creditoDto.getEstadoOrigen()):null);
			
//			creditoModelo.setMonedaImporteAUtilizar(creditoDto.getMonedaImporteUtilizar() != null? MonedaEnum.getEnumBySigla(creditoDto.getMonedaImporteUtilizar()):null);
			
			if (!Validaciones.isObjectNull(creditoDto.getMonedaImporteUtilizar())){
				
				//Si el getEnumBySigla es null, entonces ya tenia seteada La MONEDA IMPORTE A COBRAR, SINO, LO SETEO
				if (!Validaciones.isObjectNull(MonedaEnum.getEnumBySigno2(creditoDto.getMonedaImporteUtilizar()))){
					
					creditoModelo.setMonedaImporteAUtilizar(MonedaEnum.getEnumBySigno2(creditoDto.getMonedaImporteUtilizar()));
				} else {
					creditoModelo.setMonedaImporteAUtilizar(MonedaEnum.getEnumByName(creditoDto.getMonedaImporteUtilizar()));
				}
					
			}
			
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return creditoModelo;
	}

}
