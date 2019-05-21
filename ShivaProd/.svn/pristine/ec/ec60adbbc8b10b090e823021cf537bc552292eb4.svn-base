package ar.com.telecom.shiva.base.registros.mapeos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReintegroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;

public class MicOperacionMasivaRegistroDtoMapeador {
	public static List<ClienteDto> map(ShvMasRegistroClienteSiebel modelo) {
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
		
		if (!Validaciones.isObjectNull(modelo)) {
			if (
				!Validaciones.isObjectNull(modelo.getClienteDuenoDebito()) &&
				modelo.getClienteDuenoDebito() != 0l
			) {
				ClienteDto clienteDebito = new ClienteDto();
				clienteDebito.setIdClienteLegado(
					Utilidad.addStartingZeros(modelo.getClienteDuenoDebito().toString(), 10)
				);
				clienteDebito.setRazonSocial(!Validaciones.isObjectNull(modelo.getRazonSocialClienteDebito()) ? modelo.getRazonSocialClienteDebito() : Utilidad.EMPTY_STRING);
				clienteDebito.setCuit(!Validaciones.isObjectNull(modelo.getCuitDebito()) ? modelo.getCuitDebito() : Utilidad.EMPTY_STRING);
				clienteDebito.setCodigoHolding(!Validaciones.isObjectNull(modelo.getNroHoldingDebito()) ? modelo.getNroHoldingDebito().toString() : Utilidad.EMPTY_STRING);
				clienteDebito.setDescripcionHolding(!Validaciones.isObjectNull(modelo.getDescripcionHoldingDebito()) ? modelo.getDescripcionHoldingDebito() : Utilidad.EMPTY_STRING);
				clienteDebito.setAgenciaNegocio(!Validaciones.isObjectNull(modelo.getNroAgenciaNegocioDebito()) ? modelo.getNroAgenciaNegocioDebito().toString() : Utilidad.EMPTY_STRING);
				clienteDebito.setDescripcionAgenciaNegocio(!Validaciones.isObjectNull(modelo.getDescripcionAgenciaNegocioDebito()) ? modelo.getDescripcionAgenciaNegocioDebito() : Utilidad.EMPTY_STRING);
				clienteDebito.setIdClientePerfil(!Validaciones.isObjectNull(modelo.getNroClientePerfilDebito()) ? modelo.getNroClientePerfilDebito().toString() : Utilidad.EMPTY_STRING);
				clienteDebito.setSegmentoAgencia(!Validaciones.isObjectNull(modelo.getSegmentoAgenciaNegocioDebito()) ? modelo.getSegmentoAgenciaNegocioDebito().toString() : Utilidad.EMPTY_STRING);
				clienteDebito.setIdProvincia(!Validaciones.isObjectNull(modelo.getCodigoProvinciaDebito()) ? modelo.getCodigoProvinciaDebito().toString() : Utilidad.EMPTY_STRING);
				clientesDto.add(clienteDebito);
			}
			if (
					!Validaciones.isObjectNull(modelo.getClienteDuenoCredito()) && 
					!modelo.getClienteDuenoCredito().equals(modelo.getClienteDuenoDebito()) &&
					modelo.getClienteDuenoCredito() != 0l
					) {
				ClienteDto clienteCredito = new ClienteDto();
				clienteCredito.setIdClienteLegado(
					Utilidad.addStartingZeros(modelo.getClienteDuenoCredito().toString(), 10)
				);
				clienteCredito.setRazonSocial(!Validaciones.isObjectNull(modelo.getRazonSocialClienteCredito()) ? modelo.getRazonSocialClienteCredito(): Utilidad.EMPTY_STRING);
				clienteCredito.setCuit(!Validaciones.isObjectNull(modelo.getCuitCredito()) ? modelo.getCuitCredito() : Utilidad.EMPTY_STRING);
				clienteCredito.setCodigoHolding(!Validaciones.isObjectNull(modelo.getNroHoldingCredito()) ? modelo.getNroHoldingCredito().toString() : Utilidad.EMPTY_STRING);
				clienteCredito.setDescripcionHolding(!Validaciones.isObjectNull(modelo.getDescripcionHoldingCredito()) ? modelo.getDescripcionHoldingCredito() : Utilidad.EMPTY_STRING);
				clienteCredito.setAgenciaNegocio(!Validaciones.isObjectNull(modelo.getNroAgenciaNegocioCredito()) ? modelo.getNroAgenciaNegocioCredito().toString() : Utilidad.EMPTY_STRING);
				clienteCredito.setDescripcionAgenciaNegocio(!Validaciones.isObjectNull(modelo.getDescripcionAgenciaNegocioCredito()) ? modelo.getDescripcionAgenciaNegocioCredito() : Utilidad.EMPTY_STRING);
				clienteCredito.setIdClientePerfil(!Validaciones.isObjectNull(modelo.getNroClientePerfilCredito()) ? modelo.getNroClientePerfilCredito().toString() : Utilidad.EMPTY_STRING);
				clienteCredito.setSegmentoAgencia(!Validaciones.isObjectNull(modelo.getSegmentoAgenciaNegocioCredito()) ? modelo.getSegmentoAgenciaNegocioCredito().toString() : Utilidad.EMPTY_STRING);
				clienteCredito.setIdProvincia(!Validaciones.isObjectNull(modelo.getCodigoProvinciaCredito()) ? modelo.getCodigoProvinciaCredito().toString() : Utilidad.EMPTY_STRING);
				clientesDto.add(clienteCredito);
			}
			if (
					!Validaciones.isObjectNull(modelo.getClienteDuenoAcuerdo()) &&
					!modelo.getClienteDuenoAcuerdo().equals(modelo.getClienteDuenoDebito()) &&
					!modelo.getClienteDuenoAcuerdo().equals(modelo.getClienteDuenoCredito()) &&
					modelo.getClienteDuenoAcuerdo() != 0l
					) {
				ClienteDto clienteAcuerdo = new ClienteDto();
				clienteAcuerdo.setIdClienteLegado(
					Utilidad.addStartingZeros(modelo.getClienteDuenoDebito().toString(), 10)
				);
				clienteAcuerdo.setRazonSocial(!Validaciones.isObjectNull(modelo.getRazonSocialClienteAcuerdo()) ? modelo.getRazonSocialClienteAcuerdo(): Utilidad.EMPTY_STRING);
				clienteAcuerdo.setCuit(!Validaciones.isObjectNull(modelo.getCuitAcuerdo()) ? modelo.getCuitAcuerdo() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setCodigoHolding(!Validaciones.isObjectNull(modelo.getNroHoldingAcuerdo()) ? modelo.getNroHoldingAcuerdo().toString() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setDescripcionHolding(!Validaciones.isObjectNull(modelo.getDescripcionHoldingAcuerdo()) ? modelo.getDescripcionHoldingAcuerdo() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setAgenciaNegocio(!Validaciones.isObjectNull(modelo.getNroAgenciaNegocioAcuerdo()) ? modelo.getNroAgenciaNegocioAcuerdo().toString() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setDescripcionAgenciaNegocio(!Validaciones.isObjectNull(modelo.getDescripcionAgenciaNegocioAcuerdo()) ? modelo.getDescripcionAgenciaNegocioAcuerdo() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setIdClientePerfil(!Validaciones.isObjectNull(modelo.getNroClientePerfilAcuerdo()) ? modelo.getNroClientePerfilAcuerdo().toString() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setSegmentoAgencia(!Validaciones.isObjectNull(modelo.getSegmentoAgenciaNegocioAcuerdo()) ? modelo.getSegmentoAgenciaNegocioAcuerdo().toString() : Utilidad.EMPTY_STRING);
				clienteAcuerdo.setIdProvincia(!Validaciones.isObjectNull(modelo.getCodigoProvinciaAcuerdo()) ? modelo.getCodigoProvinciaAcuerdo().toString() : Utilidad.EMPTY_STRING);
				clientesDto.add(clienteAcuerdo);
			}
		}
		
		return clientesDto;
	}

	public static CobroDebitoDto map(CobroDebitoDto dto, ShvMasRegistro shvMasRegistroModelo) {

		// Aplicar Deuda
		if (shvMasRegistroModelo instanceof ShvMasRegistroAplicarDeuda) {
			ShvMasRegistroAplicarDeuda shvMasRegistro = (ShvMasRegistroAplicarDeuda) shvMasRegistroModelo;
			dto = new CobroDebitoDto();
			
			dto.setSistemaOrigen(SistemaEnum.MIC);
			dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE);

			dto.setCliente(Utilidad.addStartingZeros(shvMasRegistro.getClientesSiebel().getClienteDuenoDebito().toString(), 10)); 
			dto.setCuenta(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralCuentaDebito()) ? shvMasRegistro.getDebitoImputadoGralCuentaDebito().toString() : "");
			dto.setTipoComprobanteEnum(shvMasRegistro.getDebitoImputadoGralTipoDocumento());
			dto.setTipoDoc(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralTipoDocumento()) ? shvMasRegistro.getDebitoImputadoGralTipoDocumento().getDescripcion() : null);
			dto.setFechaPuestaCobro(Utilidad.formatDatePicker(shvMasRegistro.getDebitoImputadoGralFechaPuestaCobro()));
			
			if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralTipoDocumento())) {
				if (TipoComprobanteEnum.DUC.equals(dto.getTipoComprobanteEnum())) {
					if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralEstadoDuc())) {
						dto.setEstadoOrigenEnum(
							EstadoOrigenEnum.getEnumByName(shvMasRegistro.getDebitoImputadoGralEstadoDuc().name())	
						);
					} else {
						dto.setEstadoOrigenEnum(null);
					}
					if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralTipoDuc())) {
						dto.setSubtipoDocumento(shvMasRegistro.getDebitoImputadoGralTipoDuc().codigo());
						dto.setSubtipoDocumentoDesc(shvMasRegistro.getDebitoImputadoGralTipoDuc().descripcion());
						dto.setTipoDuc(shvMasRegistro.getDebitoImputadoGralTipoDuc());
					}
					dto.setNumeroReferenciaDuc(shvMasRegistro.getDebitoImputadoGralNumeroReferenciaMic().toString());
					dto.setNroDoc(shvMasRegistro.getDebitoImputadoGralNumeroReferenciaMic().toString());
		
				} else {
					dto.setClaseComprobante(shvMasRegistro.getDebitoImputadoGralClaseComprobante());
					dto.setSucursalComprobante(shvMasRegistro.getDebitoImputadoGralSucursalComprobante());
					dto.setNumeroComprobante(shvMasRegistro.getDebitoImputadoGralNumeroComprobante());
					dto.setNumeroReferenciaMic(shvMasRegistro.getDebitoImputadoGralNumeroReferenciaMic().toString());
					
					if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoTagetikTipoFactura())) {
						dto.setSubtipoDocumentoDesc(shvMasRegistro.getDebitoTagetikTipoFactura().descripcion());
						dto.setTipoFactura(shvMasRegistro.getDebitoTagetikTipoFactura());
						dto.setSubtipoDocumento("" + shvMasRegistro.getDebitoTagetikTipoFactura().codigo());
						dto.setEstadoOrigen(shvMasRegistro.getDebitoImputadoGralCodigoEstadoFactura().descripcion());
						dto.setEstadoOrigenEnum(
							EstadoOrigenEnum.getEnumByCodigo(shvMasRegistro.getDebitoImputadoGralCodigoEstadoFactura().codigo().trim())
						);
					} else {
						dto.setSubtipoDocumentoDesc("");
					}
					dto.setNroDoc(Utilidad.parsearNroDocNoDuc(dto.getClaseComprobante(), dto.getSucursalComprobante(), dto.getNumeroComprobante()));
				}
			}
			dto.setFechaVenc(Utilidad.formatDatePicker(shvMasRegistro.getDebitoImputadoGralFechaVencimiento()));
			dto.setImp1erVenc(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralImportePrimerVencimiento(),false, false));
			dto.setImp2doVenc(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralImporteSegundoVencimiento(),false, false));
			dto.setSaldo1erVencMonOrigen(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralSaldoPrimerVencimiento(), false, false));
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			dto.setAcuerdoFacturacion(shvMasRegistro.getDebitoImputadoGralAcuerdo());
			dto.setMarcaMigradoDeimos(SiNoEnum.SI.name());
			dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
			dto.setEstadoAcuerdoFacturacionMic(shvMasRegistro.getDebitoImputadoGralEstadoAcuerdoFacturacion());
			
			dto.setImportePriVencTerceros(Utilidad.stringToBigDecimal(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralImportePrimerVencimientoConTerceros(),false, false)));
			dto.setImporteSegVencTerceros(Utilidad.stringToBigDecimal(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralImportePrimerVencimientoConTerceros(),false, false)));
			
			if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralPosibilidadDestransferencia())) {
				dto.setPosibilidadDestransferencia(shvMasRegistro.getDebitoImputadoGralPosibilidadDestransferencia().getDescripcion());
			} else {
				dto.setPosibilidadDestransferencia(null);
			}
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			
			if (Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralEstadoConceptoTerceros())) {
				dto.setEstadoCptosDe3ros(null);
			} else {
				dto.setEstadoCptosDe3ros(shvMasRegistro.getDebitoImputadoGralEstadoConceptoTerceros().getCodigoMic());
			}
			
			
			dto.setImp3rosTransf(Utilidad.formatCurrency(shvMasRegistro.getDebitoImputadoGralImporteTercerosTransferibles(), false, false));
			
			if(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralMarcaReclamo())) {
				dto.setMarcaReclamo(shvMasRegistro.getDebitoImputadoGralMarcaReclamo());
				dto.setMarcaReclamoDescripcion(shvMasRegistro.getDebitoImputadoGralMarcaReclamo().descripcion());
			}
			if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralMarcaCyq())) {
				dto.setMarcaCyq(shvMasRegistro.getDebitoImputadoGralMarcaCyq());
			} else {
				dto.setMarcaCyq(MarcaCyQEnum.VACIO);
			}
			dto.setFechaEmision(Utilidad.formatDatePicker(shvMasRegistro.getDebitoImputadoGralFechaEmision()));
			dto.setNroConvenio(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralNumeroConvenio()) ? shvMasRegistro.getDebitoImputadoGralNumeroConvenio().toString() : Utilidad.EMPTY_STRING);
			dto.setCuota(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralCuota()) ? shvMasRegistro.getDebitoImputadoGralCuota().toString() : Utilidad.EMPTY_STRING);
			dto.setFechaUltPagoParcial(!Validaciones.isObjectNull(shvMasRegistro.getDebitoImputadoGralFechaUltimoPagoParcial())?Utilidad.formatDatePicker(shvMasRegistro.getDebitoImputadoGralFechaUltimoPagoParcial()) : Utilidad.EMPTY_STRING);
			
			// Tagetik
			dto.setRazonSocialCliente(shvMasRegistro.getDebitoTagetikRazonSocialCliente());
			if (!Validaciones.isObjectNull(shvMasRegistro.getDebitoTagetikTipoCliente())) {
				dto.setTipoCliente(shvMasRegistro.getDebitoTagetikTipoCliente().codigo());
			}
			dto.setCuit(Validaciones.isNullOrEmpty(shvMasRegistro.getDebitoTagetikCuit())?null:
				("00-00000000-0".equalsIgnoreCase(shvMasRegistro.getDebitoTagetikCuit())
					|| "0000000000000".equalsIgnoreCase(shvMasRegistro.getDebitoTagetikCuit()))?null:
					shvMasRegistro.getDebitoTagetikCuit());
			dto.setCicloFacturacion(shvMasRegistro.getDebitoTagetikCicloFacturacion());
			dto.setMarketingCustomerGroup(shvMasRegistro.getDebitoTagetikMarketingCustomerGroup());
			
			// DAKOTA
			dto.setFechaVencimientoMora(!Validaciones.isObjectNull(shvMasRegistro.getDebitoDakotaFechaVencimientoMora()) ? Utilidad.formatDatePicker(shvMasRegistro.getDebitoDakotaFechaVencimientoMora()) : null);
			dto.setIndicadorPeticionCorte(shvMasRegistro.getDebitoDakotaIndicadorPeticionCorte());
			dto.setCodigoTarifa(shvMasRegistro.getDebitoDakotaCodigoTarifa());
			
			dto.setSaldoTerceroFinanciableNOTransferible(shvMasRegistro.getDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible());
			dto.setSaldoTerceroFinanciableTransferible(shvMasRegistro.getDebitoSaldosTercerosSaldoTerceroFinanciableTransferible());
			dto.setSaldoTerceroNOFinanciableTransferible(shvMasRegistro.getDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible());
	
			dto.setMarcaMigradoDeimos(SiNoEnum.NO.getDescripcion());
			dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO);
			
			dto.setImpACobrar(
				!Validaciones.isObjectNull(shvMasRegistro.getImporte()) ? Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false) : "0"
			);
			dto.setIdDebitoPantalla(dto.getTipoComprobanteEnum().name() + "_" + dto.getNroDoc());
			
			dto.setMonedaImporteCobrar(MonedaEnum.PES.getSigno2());
		// DESESTIMIENTO
		} else if (shvMasRegistroModelo instanceof ShvMasRegistroDesistimiento) {
			ShvMasRegistroDesistimiento shvMasRegistroDesistimiento = (ShvMasRegistroDesistimiento) shvMasRegistroModelo;
			dto = new CobroDebitoDto();
			
			dto.setSistemaOrigen(SistemaEnum.MIC);
			dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE);

			dto.setCliente(Utilidad.addStartingZeros(shvMasRegistroDesistimiento.getClientesSiebel().getClienteDuenoDebito().toString(), 10)); 
			dto.setCuenta(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralCuentaDebito()) ? shvMasRegistroDesistimiento.getDebitoImputadoGralCuentaDebito().toString() : "");
			dto.setTipoComprobanteEnum(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDocumento());
			dto.setTipoDoc(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDocumento()) ? shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDocumento().getDescripcion() : null);
			dto.setFechaPuestaCobro(Utilidad.formatDatePicker(shvMasRegistroDesistimiento.getDebitoImputadoGralFechaPuestaCobro()));
			
			if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDocumento())) {
				if (TipoComprobanteEnum.DUC.equals(dto.getTipoComprobanteEnum())) {
					if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralEstadoDuc())) {
						dto.setEstadoOrigenEnum(
							EstadoOrigenEnum.getEnumByName(shvMasRegistroDesistimiento.getDebitoImputadoGralEstadoDuc().name())	
						);
					} else {
						dto.setEstadoOrigenEnum(null);
					}
					if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDuc())) {
						dto.setSubtipoDocumento(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDuc().codigo());
						dto.setSubtipoDocumentoDesc(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDuc().descripcion());
						dto.setTipoDuc(shvMasRegistroDesistimiento.getDebitoImputadoGralTipoDuc());
					}
					dto.setNumeroReferenciaDuc(shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroReferenciaMic().toString());
					dto.setNroDoc(shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroReferenciaMic().toString());
		
				} else {
					dto.setClaseComprobante(shvMasRegistroDesistimiento.getDebitoImputadoGralClaseComprobante());
					dto.setSucursalComprobante(shvMasRegistroDesistimiento.getDebitoImputadoGralSucursalComprobante());
					dto.setNumeroComprobante(shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroComprobante());
					dto.setNumeroReferenciaMic(shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroReferenciaMic().toString());
					
					if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoTagetikTipoFactura())) {
						dto.setSubtipoDocumentoDesc(shvMasRegistroDesistimiento.getDebitoTagetikTipoFactura().descripcion());
						dto.setTipoFactura(shvMasRegistroDesistimiento.getDebitoTagetikTipoFactura());
						dto.setSubtipoDocumento("" + shvMasRegistroDesistimiento.getDebitoTagetikTipoFactura().codigo());
						dto.setEstadoOrigen(shvMasRegistroDesistimiento.getDebitoImputadoGralCodigoEstadoFactura().descripcion());
						dto.setEstadoOrigenEnum(
							EstadoOrigenEnum.getEnumByCodigo(shvMasRegistroDesistimiento.getDebitoImputadoGralCodigoEstadoFactura().codigo().trim())
						);
					} else {
						dto.setSubtipoDocumentoDesc("");
					}
					dto.setNroDoc(Utilidad.parsearNroDocNoDuc(dto.getClaseComprobante(), dto.getSucursalComprobante(), dto.getNumeroComprobante()));
				}
			}
			dto.setFechaVenc(Utilidad.formatDatePicker(shvMasRegistroDesistimiento.getDebitoImputadoGralFechaVencimiento()));
			dto.setImp1erVenc(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralImportePrimerVencimiento(),false, false));
			dto.setImp2doVenc(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralImporteSegundoVencimiento(),false, false));
			dto.setSaldo1erVencMonOrigen(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralSaldoPrimerVencimiento(), false, false));
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			dto.setAcuerdoFacturacion(shvMasRegistroDesistimiento.getDebitoImputadoGralAcuerdo());
			dto.setMarcaMigradoDeimos(SiNoEnum.SI.name());
			dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
			dto.setEstadoAcuerdoFacturacionMic(shvMasRegistroDesistimiento.getDebitoImputadoGralEstadoAcuerdoFacturacion());
			
			dto.setImportePriVencTerceros(Utilidad.stringToBigDecimal(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralImportePrimerVencimientoConTerceros(),false, false)));
			dto.setImporteSegVencTerceros(Utilidad.stringToBigDecimal(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralImportePrimerVencimientoConTerceros(),false, false)));
			
			if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralPosibilidadDestransferencia())) {
				dto.setPosibilidadDestransferencia(shvMasRegistroDesistimiento.getDebitoImputadoGralPosibilidadDestransferencia().getDescripcion());
			} else {
				dto.setPosibilidadDestransferencia(null);
			}
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			
			if (Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralEstadoConceptoTerceros())) {
				dto.setEstadoCptosDe3ros(null);
			} else {
				dto.setEstadoCptosDe3ros(shvMasRegistroDesistimiento.getDebitoImputadoGralEstadoConceptoTerceros().getCodigoMic());
			}
			
			
			dto.setImp3rosTransf(Utilidad.formatCurrency(shvMasRegistroDesistimiento.getDebitoImputadoGralImporteTercerosTransferibles(), false, false));
			
			if(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralMarcaReclamo())) {
				dto.setMarcaReclamo(shvMasRegistroDesistimiento.getDebitoImputadoGralMarcaReclamo());
				dto.setMarcaReclamoDescripcion(shvMasRegistroDesistimiento.getDebitoImputadoGralMarcaReclamo().descripcion());
			}
			if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralMarcaCyq())) {
				dto.setMarcaCyq(shvMasRegistroDesistimiento.getDebitoImputadoGralMarcaCyq());
			} else {
				dto.setMarcaCyq(MarcaCyQEnum.VACIO);
			}
			dto.setFechaEmision(Utilidad.formatDatePicker(shvMasRegistroDesistimiento.getDebitoImputadoGralFechaEmision()));
			dto.setNroConvenio(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroConvenio()) ? shvMasRegistroDesistimiento.getDebitoImputadoGralNumeroConvenio().toString() : Utilidad.EMPTY_STRING);
			dto.setCuota(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralCuota()) ? shvMasRegistroDesistimiento.getDebitoImputadoGralCuota().toString() : Utilidad.EMPTY_STRING);
			dto.setFechaUltPagoParcial(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoImputadoGralFechaUltimoPagoParcial())?Utilidad.formatDatePicker(shvMasRegistroDesistimiento.getDebitoImputadoGralFechaUltimoPagoParcial()) : Utilidad.EMPTY_STRING);
			
			// Tagetik
			dto.setRazonSocialCliente(shvMasRegistroDesistimiento.getDebitoTagetikRazonSocialCliente());
			if (!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoTagetikTipoCliente())) {
				dto.setTipoCliente(shvMasRegistroDesistimiento.getDebitoTagetikTipoCliente().codigo());
			}
			dto.setCuit(Validaciones.isNullOrEmpty(shvMasRegistroDesistimiento.getDebitoTagetikCuit())?null:
				("00-00000000-0".equalsIgnoreCase(shvMasRegistroDesistimiento.getDebitoTagetikCuit())
					|| "0000000000000".equalsIgnoreCase(shvMasRegistroDesistimiento.getDebitoTagetikCuit()))?null:
					shvMasRegistroDesistimiento.getDebitoTagetikCuit());
			dto.setCicloFacturacion(shvMasRegistroDesistimiento.getDebitoTagetikCicloFacturacion());
			dto.setMarketingCustomerGroup(shvMasRegistroDesistimiento.getDebitoTagetikMarketingCustomerGroup());
			
			// DAKOTA
			dto.setFechaVencimientoMora(!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getDebitoDakotaFechaVencimientoMora()) ? Utilidad.formatDatePicker(shvMasRegistroDesistimiento.getDebitoDakotaFechaVencimientoMora()) : null);
			dto.setIndicadorPeticionCorte(shvMasRegistroDesistimiento.getDebitoDakotaIndicadorPeticionCorte());
			dto.setCodigoTarifa(shvMasRegistroDesistimiento.getDebitoDakotaCodigoTarifa());
			
			dto.setSaldoTerceroFinanciableNOTransferible(shvMasRegistroDesistimiento.getDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible());
			dto.setSaldoTerceroFinanciableTransferible(shvMasRegistroDesistimiento.getDebitoSaldosTercerosSaldoTerceroFinanciableTransferible());
			dto.setSaldoTerceroNOFinanciableTransferible(shvMasRegistroDesistimiento.getDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible());
	
			dto.setMarcaMigradoDeimos(SiNoEnum.NO.getDescripcion());
			dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO);
			
			dto.setImpACobrar(
				!Validaciones.isObjectNull(shvMasRegistroDesistimiento.getImporte()) ? Utilidad.formatCurrency(shvMasRegistroDesistimiento.getImporte(), false, false) : "0"
			);
			dto.setIdDebitoPantalla(dto.getTipoComprobanteEnum().name() + "_" + dto.getNroDoc());
			dto.setMonedaImporteCobrar(MonedaEnum.PES.getSigno2());
		}
		return dto;
	}
	
	public static CobroCreditoDto map(CobroCreditoDto dto, ShvMasRegistro shvMasRegistroModelo) throws NegocioExcepcion {
		dto.setMonedaImporteUtilizar(MonedaEnum.PES.getSigno2());
		if (shvMasRegistroModelo instanceof ShvMasRegistroAplicarDeuda) {
			ShvMasRegistroAplicarDeuda shvMasRegistro = (ShvMasRegistroAplicarDeuda) shvMasRegistroModelo;
		
			dto.setSistemaOrigen(SistemaEnum.MIC);
			dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE.getDescripcion());
	
			dto.setIdClienteLegado(Utilidad.addStartingZeros(shvMasRegistro.getCreditoCodigoCliente().toString(), 10)); 
			dto.setCuenta(!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoCuenta()) ? shvMasRegistro.getCreditoMedioPagoCuenta().toString():null);
			
			if (!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoCredito())) {
				dto.setTipoCredito(shvMasRegistro.getCreditoMedioPagoTipoCredito().getDescripcion());
				dto.setTipoCreditoEnum(shvMasRegistro.getCreditoMedioPagoTipoCredito().name());
				
				if (TipoComprobanteEnum.CRE.equals(shvMasRegistro.getCreditoMedioPagoTipoCredito())) {
					dto.setIdTipoMedioPago(shvMasRegistro.getCreditoMedioPagoTipoCredito().getValor());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.CRE);
					dto.setIdTipoMedioPago(TipoComprobanteEnum.CRE.getValor());
					dto.setTipoCreditoEnum(TipoCreditoEnum.NOTACREDITO.name());
					dto.setTipoCredito(TipoCreditoEnum.NOTACREDITO.name());
					
					
					if (!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoComprobante())) {
						dto.setTipoComprobante(shvMasRegistro.getCreditoMedioPagoTipoComprobante().name());
						dto.setTipoComprobanteEnum(shvMasRegistro.getCreditoMedioPagoTipoComprobante());	
					}
	
					dto.setClaseComprobante(shvMasRegistro.getCreditoMedioPagoClaseComprobante());
					dto.setSucursalComprobante(shvMasRegistro.getCreditoMedioPagoSucursalComprobante());
					dto.setNumeroComprobante(shvMasRegistro.getCreditoMedioPagoNumeroComprobante());
						
					if (
						!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoClaseComprobante()) && 
						!Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoMedioPagoSucursalComprobante()) && 
						!Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoMedioPagoNumeroComprobante())
					) {
						dto.setNroDoc(Utilidad.parsearNroDocNoDuc(shvMasRegistro.getCreditoMedioPagoClaseComprobante(), shvMasRegistro.getCreditoMedioPagoSucursalComprobante(), shvMasRegistro.getCreditoMedioPagoNumeroComprobante()));
					}
						
					dto.setNumeroReferenciaMic(!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoNroReferenciaMic()) ? shvMasRegistro.getCreditoMedioPagoNroReferenciaMic().toString() : "");
					
				
					dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaEmision()));
					//dto.setFechaVenc(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralVencimiento()));
					
					
					
					dto.setSubtipo(!Validaciones.isObjectNull(shvMasRegistro.getCreditoTagetikTipoFactura()) ? "" + shvMasRegistro.getCreditoTagetikTipoFactura().codigo() : null);
					dto.setSubtipoDocumentoDesc(!Validaciones.isObjectNull(shvMasRegistro.getCreditoTagetikTipoFactura()) ? shvMasRegistro.getCreditoTagetikTipoFactura().descripcion() : null);
					dto.setTipoFactura(shvMasRegistro.getCreditoTagetikTipoFactura());
					dto.setTipoFacturaMicRem(null);
					
					
					//Se arma el idCreditoPantalla
					dto.setIdCreditoPantalla((!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoCredito()) ? shvMasRegistro.getCreditoMedioPagoTipoCredito().name() : "") + "_" + dto.getNroDoc() + "_" + dto.getNumeroReferenciaMic());
					//
					dto.setOrderByFecha(dto.getFechaVenc());
					dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
					dto.setOrderByNumeroIdentificatorio(!Validaciones.isObjectNull(dto.getNumeroReferenciaMic()) ? dto.getNumeroReferenciaMic() : "");
					//
				} else {
					//Remanente
					dto.setTipoComprobante(TipoComprobanteEnum.REM.descripcion());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.REM);
					dto.setIdTipoMedioPago(TipoComprobanteEnum.REM.getValor());
					dto.setTipoCreditoEnum(TipoCreditoEnum.REMANENTE.name());
					dto.setTipoCredito(TipoCreditoEnum.REMANENTE.name());
					
					dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaAlta()));
					dto.setFechaAltaCredito(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaAlta()));
					//
					TipoRemanenteEnum tipoRemanenteEnum = shvMasRegistro.getRemanenteTipoRemanente();
						
					if (!Validaciones.isObjectNull(tipoRemanenteEnum)) {
						dto.setCodigoTipoRemanente(tipoRemanenteEnum.codigo());
						dto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
						dto.setSubtipoDocumentoDesc(tipoRemanenteEnum.descripcion());
						dto.setTipoFactura(null);
						dto.setTipoFacturaMicRem(tipoRemanenteEnum);
						dto.setIdTipoMedioPago(tipoRemanenteEnum.getIdTipoMedioPago());
					}
					
					//Se arma el idCreditoPantalla
					StringBuffer str = new StringBuffer();
					str.append(!Validaciones.isObjectNull(dto.getIdClienteLegado()) ? dto.getIdClienteLegado().toString() : "");
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
				//Lo uso en la pantalla
				dto.setSaldoMonOrigen(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralSaldo()) ? Utilidad.formatCurrency(shvMasRegistro.getCreditoGralSaldo(), true, true, 2) : null);
				dto.setSaldoPesificado(dto.getSaldoMonOrigen());
				dto.setMoneda(MonedaEnum.PES.getSigno2());
				dto.setMonedaEnum(MonedaEnum.PES);
				dto.setImpMonOrigen(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralImporte()) ? Utilidad.formatCurrency(shvMasRegistro.getCreditoGralImporte(), true,true,2) : null);
				dto.setImpPesificado(dto.getImpMonOrigen());
				dto.setFechaEmision(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaEmision()));
				dto.setFechaUltimoMov(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaUltimoMovimiento()));
				//
				dto.setMarcaReclamo(shvMasRegistro.getCreditoGralMarcaReclamo());
				dto.setMarcaReclamoDescripcion(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralMarcaReclamo()) ? shvMasRegistro.getCreditoGralMarcaReclamo().descripcionAMostrar() : null);
				//
				if (ConstantesCobro.CODIGO_ESTADO_CREDITO_DEIMOS.equals(shvMasRegistro.getEstadoOrigen())) {
					dto.setMarcaMigradoDeimos(SiNoEnum.SI);
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
					}else{
						dto.setMarcaMigradoDeimos(SiNoEnum.NO); 
						dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO); 
					}
					//
					dto.setEstadoOrigen(shvMasRegistro.getEstadoOrigen().descripcion());
					dto.setEstadoOrigenEnum(shvMasRegistro.getEstadoOrigen());
									//Puede venir con un espacio, si viene con espacio es MarcaCyQEnum.VACIO 
					if(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralMarcaCyq())) {
						dto.setMarcaCyq(shvMasRegistro.getCreditoGralMarcaCyq());
					}
					
			}
				//
				dto.setSinDifDeCambio(false);
				// Tagetik
				dto.setRazonSocialCliente(shvMasRegistro.getCreditoTagetikRazonSocialCliente());
				dto.setTipoCliente(shvMasRegistro.getCreditoTagetikTipoCliente());
				dto.setCuit(Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoTagetikCuit())?null:
						("00-00000000-0".equalsIgnoreCase(shvMasRegistro.getCreditoTagetikCuit())
							|| "0000000000000".equalsIgnoreCase(shvMasRegistro.getCreditoTagetikCuit()))?null:
								shvMasRegistro.getCreditoTagetikCuit());
				dto.setCicloFacturacion(shvMasRegistro.getCreditoTagetikCicloFacturacion());
				dto.setMarketingCustomerGroup(shvMasRegistro.getCreditoTagetikMarketingCustomerGroup());
				
				dto.setCodigoTarifa(shvMasRegistro.getCreditoDakotaCodigoTarifa());
				dto.setIndicadorPeticionCorte(shvMasRegistro.getCreditoDakotaIndicadorPeticionCorte());
				dto.setFechaVencimientoMora(!Validaciones.isObjectNull(shvMasRegistro.getCreditoDakotaFechaVencimientoMora()) ? Utilidad.formatDatePicker(shvMasRegistro.getCreditoDakotaFechaVencimientoMora()) : "");	
				
				dto.setImporteAUtilizar(
					!Validaciones.isObjectNull(shvMasRegistro.getImporte()) ? Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false) : "0"
				);
	
		} else if (shvMasRegistroModelo instanceof ShvMasRegistroReintegro) {
			ShvMasRegistroReintegro shvMasRegistro = (ShvMasRegistroReintegro) shvMasRegistroModelo;
			
			dto.setSistemaOrigen(SistemaEnum.MIC);
			dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE.getDescripcion());
	
			dto.setIdClienteLegado(Utilidad.addStartingZeros(shvMasRegistro.getCreditoCodigoCliente().toString(), 10)); 
			dto.setCuenta(!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoCuenta()) ? shvMasRegistro.getCreditoMedioPagoCuenta().toString():null);
			
			if (!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoCredito())) {
				dto.setTipoCredito(shvMasRegistro.getCreditoMedioPagoTipoCredito().getDescripcion());
				dto.setTipoCreditoEnum(shvMasRegistro.getCreditoMedioPagoTipoCredito().name());
				
				if (TipoComprobanteEnum.CRE.equals(shvMasRegistro.getCreditoMedioPagoTipoCredito())) {
					dto.setIdTipoMedioPago(shvMasRegistro.getCreditoMedioPagoTipoCredito().getValor());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.CRE);
					dto.setIdTipoMedioPago(TipoComprobanteEnum.CRE.getValor());
					dto.setTipoCreditoEnum(TipoCreditoEnum.NOTACREDITO.name());
					dto.setTipoCredito(TipoCreditoEnum.NOTACREDITO.name());
					
					
					if (!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoComprobante())) {
						dto.setTipoComprobante(shvMasRegistro.getCreditoMedioPagoTipoComprobante().name());
						dto.setTipoComprobanteEnum(shvMasRegistro.getCreditoMedioPagoTipoComprobante());	
					}
	
					dto.setClaseComprobante(shvMasRegistro.getCreditoMedioPagoClaseComprobante());
					dto.setSucursalComprobante(shvMasRegistro.getCreditoMedioPagoSucursalComprobante());
					dto.setNumeroComprobante(shvMasRegistro.getCreditoMedioPagoNumeroComprobante());
						
					if (
						!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoClaseComprobante()) && 
						!Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoMedioPagoSucursalComprobante()) && 
						!Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoMedioPagoNumeroComprobante())
					) {
						dto.setNroDoc(Utilidad.parsearNroDocNoDuc(shvMasRegistro.getCreditoMedioPagoClaseComprobante(), shvMasRegistro.getCreditoMedioPagoSucursalComprobante(), shvMasRegistro.getCreditoMedioPagoNumeroComprobante()));
					}
						
					dto.setNumeroReferenciaMic(!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoNroReferenciaMic()) ? shvMasRegistro.getCreditoMedioPagoNroReferenciaMic().toString() : "");
					
				
					dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaEmision()));
					//dto.setFechaVenc(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralVencimiento()));
					
					
					
					dto.setSubtipo(!Validaciones.isObjectNull(shvMasRegistro.getCreditoTagetikTipoFactura()) ? "" + shvMasRegistro.getCreditoTagetikTipoFactura().codigo() : null);
					dto.setSubtipoDocumentoDesc(!Validaciones.isObjectNull(shvMasRegistro.getCreditoTagetikTipoFactura()) ? shvMasRegistro.getCreditoTagetikTipoFactura().descripcion() : null);
					dto.setTipoFactura(shvMasRegistro.getCreditoTagetikTipoFactura());
					dto.setTipoFacturaMicRem(null);
					
					
					//Se arma el idCreditoPantalla
					dto.setIdCreditoPantalla((!Validaciones.isObjectNull(shvMasRegistro.getCreditoMedioPagoTipoCredito()) ? shvMasRegistro.getCreditoMedioPagoTipoCredito().name() : "") + "_" + dto.getNroDoc() + "_" + dto.getNumeroReferenciaMic());
					//
					dto.setOrderByFecha(dto.getFechaVenc());
					dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
					dto.setOrderByNumeroIdentificatorio(!Validaciones.isObjectNull(dto.getNumeroReferenciaMic()) ? dto.getNumeroReferenciaMic() : "");
					//
				} else {
					//Remanente
					dto.setTipoComprobante(TipoComprobanteEnum.REM.descripcion());
					dto.setTipoComprobanteEnum(TipoComprobanteEnum.REM);
					dto.setIdTipoMedioPago(TipoComprobanteEnum.REM.getValor());
					dto.setTipoCreditoEnum(TipoCreditoEnum.REMANENTE.name());
					dto.setTipoCredito(TipoCreditoEnum.REMANENTE.name());
					
					dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaAlta()));
					dto.setFechaAltaCredito(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaAlta()));
					//
					TipoRemanenteEnum tipoRemanenteEnum = shvMasRegistro.getRemanenteTipoRemanente();
						
					if (!Validaciones.isObjectNull(tipoRemanenteEnum)) {
						dto.setCodigoTipoRemanente(tipoRemanenteEnum.codigo());
						dto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
						dto.setSubtipoDocumentoDesc(tipoRemanenteEnum.descripcion());
						dto.setTipoFactura(null);
						dto.setTipoFacturaMicRem(tipoRemanenteEnum);
						dto.setIdTipoMedioPago(tipoRemanenteEnum.getIdTipoMedioPago());
					}
					
					//Se arma el idCreditoPantalla
					StringBuffer str = new StringBuffer();
					str.append(!Validaciones.isObjectNull(dto.getIdClienteLegado()) ? dto.getIdClienteLegado().toString() : "");
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
				//Lo uso en la pantalla
				dto.setSaldoMonOrigen(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralSaldo()) ? Utilidad.formatCurrency(shvMasRegistro.getCreditoGralSaldo(), true, true, 2) : null);
				dto.setSaldoPesificado(dto.getSaldoMonOrigen());
				dto.setMoneda(MonedaEnum.PES.getSigno2());
				dto.setMonedaEnum(MonedaEnum.PES);
				dto.setImpMonOrigen(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralImporte()) ? Utilidad.formatCurrency(shvMasRegistro.getCreditoGralImporte(), true,true,2) : null);
				dto.setImpPesificado(dto.getImpMonOrigen());
				dto.setFechaEmision(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaEmision()));
				dto.setFechaUltimoMov(Utilidad.formatDatePicker(shvMasRegistro.getCreditoGralFechaUltimoMovimiento()));
				//
				dto.setMarcaReclamo(shvMasRegistro.getCreditoGralMarcaReclamo());
				dto.setMarcaReclamoDescripcion(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralMarcaReclamo()) ? shvMasRegistro.getCreditoGralMarcaReclamo().descripcionAMostrar() : null);
				//
				if (ConstantesCobro.CODIGO_ESTADO_CREDITO_DEIMOS.equals(shvMasRegistro.getEstadoOrigen())) {
					dto.setMarcaMigradoDeimos(SiNoEnum.SI);
					dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
					}else{
						dto.setMarcaMigradoDeimos(SiNoEnum.NO); 
						dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO); 
					}
					//
					dto.setEstadoOrigen(shvMasRegistro.getEstadoOrigen().descripcion());
					dto.setEstadoOrigenEnum(shvMasRegistro.getEstadoOrigen());
									//Puede venir con un espacio, si viene con espacio es MarcaCyQEnum.VACIO 
					if(!Validaciones.isObjectNull(shvMasRegistro.getCreditoGralMarcaCyq())) {
						dto.setMarcaCyq(shvMasRegistro.getCreditoGralMarcaCyq());
					}
					
			}
				//
				dto.setSinDifDeCambio(false);
				// Tagetik
				dto.setRazonSocialCliente(shvMasRegistro.getCreditoTagetikRazonSocialCliente());
				dto.setTipoCliente(shvMasRegistro.getCreditoTagetikTipoCliente());
				dto.setCuit(Validaciones.isNullOrEmpty(shvMasRegistro.getCreditoTagetikCuit())?null:
						("00-00000000-0".equalsIgnoreCase(shvMasRegistro.getCreditoTagetikCuit())
							|| "0000000000000".equalsIgnoreCase(shvMasRegistro.getCreditoTagetikCuit()))?null:
								shvMasRegistro.getCreditoTagetikCuit());
				dto.setCicloFacturacion(shvMasRegistro.getCreditoTagetikCicloFacturacion());
				dto.setMarketingCustomerGroup(shvMasRegistro.getCreditoTagetikMarketingCustomerGroup());
				
				dto.setCodigoTarifa(shvMasRegistro.getCreditoDakotaCodigoTarifa());
				dto.setIndicadorPeticionCorte(shvMasRegistro.getCreditoDakotaIndicadorPeticionCorte());
				dto.setFechaVencimientoMora(!Validaciones.isObjectNull(shvMasRegistro.getCreditoDakotaFechaVencimientoMora()) ? Utilidad.formatDatePicker(shvMasRegistro.getCreditoDakotaFechaVencimientoMora()) : "");	
				
				dto.setImporteAUtilizar(
					!Validaciones.isObjectNull(shvMasRegistro.getImporte()) ? Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false) : "0"
				);
			} else if (shvMasRegistroModelo instanceof ShvMasRegistroGanancias) {
				ShvMasRegistroGanancias shvMasRegistroGanacias = (ShvMasRegistroGanancias) shvMasRegistroModelo;
				
				dto.setSistemaOrigen(SistemaEnum.MIC);
				dto.setSistemaDescripcion(SistemaEnum.MIC.getDescripcion());
				dto.setOrigen(OrigenDebitoEnum.ONLINE.getDescripcion());
		
				dto.setIdClienteLegado(Utilidad.addStartingZeros(shvMasRegistroGanacias.getCreditoCodigoCliente().toString(), 10)); 
				dto.setCuenta(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoCuenta()) ? shvMasRegistroGanacias.getCreditoMedioPagoCuenta().toString():null);
				
				if (!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito())) {
					dto.setTipoCredito(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito().getDescripcion());
					dto.setTipoCreditoEnum(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito().name());
					
					if (TipoComprobanteEnum.CRE.equals(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito())) {
						dto.setIdTipoMedioPago(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito().getValor());
						dto.setTipoComprobanteEnum(TipoComprobanteEnum.CRE);
						dto.setIdTipoMedioPago(TipoComprobanteEnum.CRE.getValor());
						dto.setTipoCreditoEnum(TipoCreditoEnum.NOTACREDITO.name());
						dto.setTipoCredito(TipoCreditoEnum.NOTACREDITO.name());
						
						
						if (!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoTipoComprobante())) {
							dto.setTipoComprobante(shvMasRegistroGanacias.getCreditoMedioPagoTipoComprobante().name());
							dto.setTipoComprobanteEnum(shvMasRegistroGanacias.getCreditoMedioPagoTipoComprobante());	
						}
		
						dto.setClaseComprobante(shvMasRegistroGanacias.getCreditoMedioPagoClaseComprobante());
						dto.setSucursalComprobante(shvMasRegistroGanacias.getCreditoMedioPagoSucursalComprobante());
						dto.setNumeroComprobante(shvMasRegistroGanacias.getCreditoMedioPagoNumeroComprobante());
							
						if (
							!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoClaseComprobante()) && 
							!Validaciones.isNullOrEmpty(shvMasRegistroGanacias.getCreditoMedioPagoSucursalComprobante()) && 
							!Validaciones.isNullOrEmpty(shvMasRegistroGanacias.getCreditoMedioPagoNumeroComprobante())
						) {
							dto.setNroDoc(Utilidad.parsearNroDocNoDuc(shvMasRegistroGanacias.getCreditoMedioPagoClaseComprobante(), shvMasRegistroGanacias.getCreditoMedioPagoSucursalComprobante(), shvMasRegistroGanacias.getCreditoMedioPagoNumeroComprobante()));
						}
							
						dto.setNumeroReferenciaMic(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoNroReferenciaMic()) ? shvMasRegistroGanacias.getCreditoMedioPagoNroReferenciaMic().toString() : "");
						
					
						dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralFechaEmision()));
						//dto.setFechaVenc(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralVencimiento()));
						
						
						
						dto.setSubtipo(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoTagetikTipoFactura()) ? "" + shvMasRegistroGanacias.getCreditoTagetikTipoFactura().codigo() : null);
						dto.setSubtipoDocumentoDesc(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoTagetikTipoFactura()) ? shvMasRegistroGanacias.getCreditoTagetikTipoFactura().descripcion() : null);
						dto.setTipoFactura(shvMasRegistroGanacias.getCreditoTagetikTipoFactura());
						dto.setTipoFacturaMicRem(null);
						
						
						//Se arma el idCreditoPantalla
						dto.setIdCreditoPantalla((!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito()) ? shvMasRegistroGanacias.getCreditoMedioPagoTipoCredito().name() : "") + "_" + dto.getNroDoc() + "_" + dto.getNumeroReferenciaMic());
						//
						dto.setOrderByFecha(dto.getFechaVenc());
						dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
						dto.setOrderByNumeroIdentificatorio(!Validaciones.isObjectNull(dto.getNumeroReferenciaMic()) ? dto.getNumeroReferenciaMic() : "");
						//
					} else {
						//Remanente
						dto.setTipoComprobante(TipoComprobanteEnum.REM.descripcion());
						dto.setTipoComprobanteEnum(TipoComprobanteEnum.REM);
						dto.setIdTipoMedioPago(TipoComprobanteEnum.REM.getValor());
						dto.setTipoCreditoEnum(TipoCreditoEnum.REMANENTE.name());
						dto.setTipoCredito(TipoCreditoEnum.REMANENTE.name());
						
						dto.setFechaValor(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralFechaAlta()));
						dto.setFechaAltaCredito(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralFechaAlta()));
						//
						TipoRemanenteEnum tipoRemanenteEnum = shvMasRegistroGanacias.getRemanenteTipoRemanente();
							
						if (!Validaciones.isObjectNull(tipoRemanenteEnum)) {
							dto.setCodigoTipoRemanente(tipoRemanenteEnum.codigo());
							dto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
							dto.setSubtipoDocumentoDesc(tipoRemanenteEnum.descripcion());
							dto.setTipoFactura(null);
							dto.setTipoFacturaMicRem(tipoRemanenteEnum);
							dto.setIdTipoMedioPago(tipoRemanenteEnum.getIdTipoMedioPago());
						}
						
						//Se arma el idCreditoPantalla
						StringBuffer str = new StringBuffer();
						str.append(!Validaciones.isObjectNull(dto.getIdClienteLegado()) ? dto.getIdClienteLegado().toString() : "");
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
					//Lo uso en la pantalla
					dto.setSaldoMonOrigen(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoGralSaldo()) ? Utilidad.formatCurrency(shvMasRegistroGanacias.getCreditoGralSaldo(), true, true, 2) : null);
					dto.setSaldoPesificado(dto.getSaldoMonOrigen());
					dto.setMoneda(MonedaEnum.PES.getSigno2());
					dto.setMonedaEnum(MonedaEnum.PES);
					dto.setImpMonOrigen(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoGralImporte()) ? Utilidad.formatCurrency(shvMasRegistroGanacias.getCreditoGralImporte(), true,true,2) : null);
					dto.setImpPesificado(dto.getImpMonOrigen());
					dto.setFechaEmision(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralFechaEmision()));
					dto.setFechaUltimoMov(Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoGralFechaUltimoMovimiento()));
					//
					dto.setMarcaReclamo(shvMasRegistroGanacias.getCreditoGralMarcaReclamo());
					dto.setMarcaReclamoDescripcion(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoGralMarcaReclamo()) ? shvMasRegistroGanacias.getCreditoGralMarcaReclamo().descripcionAMostrar() : null);
					//
					if (ConstantesCobro.CODIGO_ESTADO_CREDITO_DEIMOS.equals(shvMasRegistroGanacias.getEstadoOrigen())) {
						dto.setMarcaMigradoDeimos(SiNoEnum.SI);
						dto.setMarcaMigradaOrigenEnum(SiNoEnum.SI);
						}else{
							dto.setMarcaMigradoDeimos(SiNoEnum.NO); 
							dto.setMarcaMigradaOrigenEnum(SiNoEnum.NO); 
						}
						//
						dto.setEstadoOrigen(shvMasRegistroGanacias.getEstadoOrigen().descripcion());
						dto.setEstadoOrigenEnum(shvMasRegistroGanacias.getEstadoOrigen());
										//Puede venir con un espacio, si viene con espacio es MarcaCyQEnum.VACIO 
						if(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoGralMarcaCyq())) {
							dto.setMarcaCyq(shvMasRegistroGanacias.getCreditoGralMarcaCyq());
						}
						
				}
					//
					dto.setSinDifDeCambio(false);
					// Tagetik
					dto.setRazonSocialCliente(shvMasRegistroGanacias.getCreditoTagetikRazonSocialCliente());
					dto.setTipoCliente(shvMasRegistroGanacias.getCreditoTagetikTipoCliente());
					dto.setCuit(Validaciones.isNullOrEmpty(shvMasRegistroGanacias.getCreditoTagetikCuit())?null:
							("00-00000000-0".equalsIgnoreCase(shvMasRegistroGanacias.getCreditoTagetikCuit())
								|| "0000000000000".equalsIgnoreCase(shvMasRegistroGanacias.getCreditoTagetikCuit()))?null:
									shvMasRegistroGanacias.getCreditoTagetikCuit());
					dto.setCicloFacturacion(shvMasRegistroGanacias.getCreditoTagetikCicloFacturacion());
					dto.setMarketingCustomerGroup(shvMasRegistroGanacias.getCreditoTagetikMarketingCustomerGroup());
					
					dto.setCodigoTarifa(shvMasRegistroGanacias.getCreditoDakotaCodigoTarifa());
					dto.setIndicadorPeticionCorte(shvMasRegistroGanacias.getCreditoDakotaIndicadorPeticionCorte());
					dto.setFechaVencimientoMora(!Validaciones.isObjectNull(shvMasRegistroGanacias.getCreditoDakotaFechaVencimientoMora()) ? Utilidad.formatDatePicker(shvMasRegistroGanacias.getCreditoDakotaFechaVencimientoMora()) : "");	
					
					dto.setImporteAUtilizar(
						!Validaciones.isObjectNull(shvMasRegistroGanacias.getImporte()) ? Utilidad.formatCurrency(shvMasRegistroGanacias.getImporte(), false, false) : "0"
					);
			}
		return dto;
	}
		
	public static CobroMedioDePagoDto map(CobroMedioDePagoDto dto, ShvMasRegistro shvMasRegistroModelo) {
		
		if (shvMasRegistroModelo instanceof ShvMasRegistroDesistimiento) {
			ShvMasRegistroDesistimiento shvMasRegistro = (ShvMasRegistroDesistimiento) shvMasRegistroModelo;

			dto.setClientesLegados(Utilidad.addStartingZeros(shvMasRegistro.getDebitoImputadoCodigoCliente().toString(), 10));
			dto.setDescMpTipoCredito(TipoMedioPagoEnum.DESISTIMIENTO.getDescripcion());
			dto.setIdMpTipoCredito(TipoMedioPagoEnum.DESISTIMIENTO.getIdTipoMedioPago());
			dto.setNroActa(shvMasRegistro.getNumeroActaDesistimiento());
			dto.setFecha(!Validaciones.isObjectNull(shvMasRegistro.getFechaActaDesistimiento()) ? Utilidad.formatDatePicker(shvMasRegistro.getFechaActaDesistimiento()) : "");
			dto.setImporte(Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false));
			dto.setMonedaImporteUtilizar(MonedaEnum.PES.getSigno2());
			dto.setMonedaImporteUtilizarSigno2(MonedaEnum.PES.getSigno2());
		}
		return dto;
	}
	
	
	public static CobroTratamientoDiferenciaDto map(CobroTratamientoDiferenciaDto dto, ShvMasRegistro shvMasRegistroModelo) {
		dto.setMoneda(MonedaEnum.PES.getSigno2());
		if (shvMasRegistroModelo instanceof ShvMasRegistroGanancias) {
			ShvMasRegistroGanancias shvMasRegistro = (ShvMasRegistroGanancias) shvMasRegistroModelo;
			dto.setImporte(Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false));
			dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getName());
		} else if (shvMasRegistroModelo instanceof ShvMasRegistroReintegro) {
			ShvMasRegistroReintegro shvMasRegistro = (ShvMasRegistroReintegro) shvMasRegistroModelo;
			dto.setImporte(Utilidad.formatCurrency(shvMasRegistro.getImporte(), false, false));
			dto.setFechaAltaTramiteReintegro(!Validaciones.isObjectNull(shvMasRegistro.getFechaAltaTramiteReintegro()) ? Utilidad.formatDatePicker(shvMasRegistro.getFechaAltaTramiteReintegro()) : "");
			
			switch (shvMasRegistro.getTipoReintegro()) {
			case CBU:
				dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.name());
				break;
			case CH:
				dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.name());
				break;
			case PCT:
				dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.name());
				break;
			case PF:
				dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.name());
				break;
			case RI:
				dto.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.name());
				break;
			}
			dto.setNumeroTramiteReintegro(shvMasRegistro.getTramiteReintegro());
			dto.setSistemaDestino(SistemaEnum.MIC.getDescripcionCorta());
			if (TipoReintegroEnum.PF.equals(shvMasRegistro.getTipoReintegro())) {
				dto.setAcuerdoFacturacion(shvMasRegistro.getAcuerdoGralAcuerdoFacturacion());
				dto.setLinea(shvMasRegistro.getAcuerdoGralLinea());
				if (shvMasRegistro.getAcuerdoGralEstadoAcuerdoFacturacion() != null) {
					dto.setEstadoAcuerdoFacturacion(shvMasRegistro.getAcuerdoGralEstadoAcuerdoFacturacion().name());
				}
				dto.setIdClienteAcuerdoFacturacion(shvMasRegistro.getAcuerdoGralCodigoCliente());
			}
			
		}
		return dto;
	}

}
