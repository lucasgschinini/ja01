package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.IMapeadorUtil;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;

/**
 * 
 * @author u573005, fabio.giaquinta.ruiz, sprint 5
 *
 */



public class CobroOnlineTransaccionesMapeador extends MapeadorResultadoBusqueda implements IMapeadorUtil{
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {

		CobroTransaccionDto dto = new CobroTransaccionDto();
		VistaSoporteResultadoBusquedaCobroTransaccion vista = (VistaSoporteResultadoBusquedaCobroTransaccion) vistaSoporteResultadoBusqueda;
		
//		------------------------
//		Comunes
//		------------------------
		dto.setNumeroTransaccionFormateado(vista.getNumeroTransaccionFormateado());
		dto.setNumeroTransaccionFicticioFormateado(vista.getNumeroTransaccionFicticioFormateado());
		dto.setNumeroTransaccion(vista.getNumeroTransaccion().toString());
		if(!Validaciones.isObjectNull(vista.getNumeroTransaccionFicticio())){
		dto.setNumeroTransaccionFicticio(vista.getNumeroTransaccionFicticio().toString());
		}
		dto.setNumeroTransaccionOriginal(vista.getNumeroTransaccionOriginal().toString());
		EstadoTransaccionEnum estadoTransaccion = vista.getEstadoTransaccion();
		if(!Validaciones.isObjectNull(estadoTransaccion)){
			dto.setEstadoTransaccion(estadoTransaccion.descripcion());
		}
		
//		------------------------
//		Facturas
//		------------------------
		SistemaEnum sistemaDoc = vista.getSistemaOrigenDocumento();
		if(!Validaciones.isObjectNull(sistemaDoc)){
			dto.setSistemaDoc(sistemaDoc.getDescripcion());
			dto.setSistemaAcuerdo(sistemaDoc.getDescripcion());
		}
		
		TipoComprobanteEnum tipoDocumento = vista.getTipoComprobante();
		if(!Validaciones.isObjectNull(tipoDocumento)){
			dto.setTipoDocumento(tipoDocumento.descripcion());
		}
		
		OrigenDocumentoEnum origenDocumento = vista.getOrigenDocumento();
		if (!Validaciones.isObjectNull(origenDocumento) && !Validaciones.isObjectNull(vista.getIdFactura())) {
			dto.setSubtipoDocumento(origenDocumento.name());
			dto.setSubtipoDocumentoDesc(origenDocumento.descripcion());
		} else {
			dto.setSubtipoDocumento(vista.getSubTipoDocumento());
			dto.setSubtipoDocumentoDesc(vista.getDescripcionSubTipoDocumento());
		}
		
		
		if (SistemaEnum.MIC.equals(sistemaDoc) && TipoComprobanteEnum.DUC.equals(vista.getTipoComprobante())) {
			dto.setNroDoc(vista.getNumeroReferencia());
		} else {
			dto.setNroDoc(vista.getNumeroDocumento());
			dto.setNumeroReferencia(vista.getNumeroReferencia());
		}
		dto.setFechaVenc(Utilidad.formatDatePicker(vista.getFechaVencimiento()));
		dto.setFecha2doVenc(Utilidad.formatDatePicker(vista.getFechaSegundoVencimiento()));
		dto.setCobrarAl2doVenc(vista.getCobrarSegundoVencimiento());
		
		MonedaEnum moneda = vista.getMoneda();
		if(!Validaciones.isObjectNull(moneda)){
			dto.setMoneda(moneda.getSigno2());
		}		
		
		dto.setFechaCobro(Utilidad.formatDatePicker(vista.getFechaCobro()));
		
		BigDecimal importe = vista.getImporte();
		if(!Validaciones.isObjectNull(importe)){
			dto.setImporte(Utilidad.formatDecimales(importe, 2));
		}
		
		BigDecimal tipoDeCambioFechaCobro = vista.getTipoDeCambioFechaCobro();
		if(!Validaciones.isObjectNull(tipoDeCambioFechaCobro)){
			dto.setTipoDeCambioFechaCobro(Utilidad.formatDecimales(tipoDeCambioFechaCobro, 7));
		}
		
		BigDecimal tipoDeCambioFechaEmision = vista.getTipoDeCambioFechaEmision();
		if(!Validaciones.isObjectNull(tipoDeCambioFechaEmision)){
			dto.setTipoDeCambioFechaEmision(Utilidad.formatDecimales(tipoDeCambioFechaEmision, 7));
		}

		BigDecimal importeMonedaOrigen = vista.getImporteAplicadoFechaEmisionMonedaOrigen();
		if(!Validaciones.isObjectNull(importeMonedaOrigen)){
			dto.setImporteAplicadoFechaEmisionMonedaOrigen(Utilidad.formatDecimales(importeMonedaOrigen, 2));
		}
		
		 dto.setNumeroGrupo(vista.getGrupo());
		
//		------------------------
//		Medio de Pago
//		------------------------
		SistemaEnum sistemaMedioPago = vista.getSistemaMedioPago();
		if(!Validaciones.isObjectNull(sistemaMedioPago)){
			dto.setSistemaMedioPago(sistemaMedioPago.getDescripcion());
		}
		
		TipoMedioPagoEnum tipoMedioPago = vista.getTipoMedioPago();
		if(!Validaciones.isObjectNull(tipoMedioPago)) {
			if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.equals(tipoMedioPago) || TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.equals(tipoMedioPago)){
				dto.setTipoMedioPago(TipoTratamientoDiferenciaEnum.getEnumByIdtipoMedioPagoAsociado(tipoMedioPago.getIdTipoMedioPago()).getDescripcion());
			} else {
				dto.setTipoMedioPago(TipoCreditoEnum.getEnumByIdTipoMedioPago(tipoMedioPago.getIdTipoMedioPago()).getDescripcion());
			}
			dto.setTipoMedioPagoNombre(tipoMedioPago.name());
		}
		
		OrigenDocumentoEnum origenMedioPago = vista.getOrigenDocumento();
		if(!Validaciones.isObjectNull(origenMedioPago) && vista.getIdMedioPago() > 0){
			dto.setSubtipoMedioPago(origenMedioPago.name());
			dto.setSubtipoMedioPagoDesc(origenMedioPago.descripcion());
		} else {
			TipoRetencionEnum subTipoMedioPagoRetencion = vista.getSubTipoMedioPagoRetencion();
			if (!Validaciones.isObjectNull(subTipoMedioPagoRetencion)) {
				dto.setSubtipoMedioPago(String.valueOf(subTipoMedioPagoRetencion.getId()));
				dto.setSubtipoMedioPagoDesc(subTipoMedioPagoRetencion.getDescripcion());
			}
			
			TipoRemanenteEnum subTipoRemanente = vista.getSubTipoMedioPagoRemanente();
			if (!Validaciones.isObjectNull(subTipoRemanente)) {
				dto.setSubtipoMedioPago(String.valueOf(subTipoRemanente.codigo()));
				dto.setSubtipoMedioPagoDesc(subTipoRemanente.descripcion());
			}
		}
		
		dto.setReferenciaMedioPago(vista.getReferenciaMedioPago());
		dto.setFechaMedioPago(Utilidad.formatDatePicker(vista.getFechaMedioPago()));
		
		MonedaEnum monedaMedioPago = vista.getMonedaMedioPago();
		if(!Validaciones.isObjectNull(monedaMedioPago)){
			dto.setMonedaMedioPago(monedaMedioPago.getSigno2());
		}
		
		BigDecimal importeMedioPago = vista.getImporteMedioPago();
		if(!Validaciones.isObjectNull(importeMedioPago)){
			dto.setImporteMedioPago(Utilidad.formatDecimales(importeMedioPago, 2));
		}
		
		BigDecimal tipoDeCambioFechaCobroMedioPago = vista.getTipoDeCambioFechaCobroMedioPago();
		if(!Validaciones.isObjectNull(tipoDeCambioFechaCobroMedioPago)){
			dto.setTipoDeCambioFechaCobroMedioPago(Utilidad.formatDecimales(tipoDeCambioFechaCobroMedioPago,7));
		}
		
		BigDecimal tipoDeCambioFechaEmisionMedioPago = vista.getTipoDeCambioFechaEmisionMedioPago();
		if(!Validaciones.isObjectNull(tipoDeCambioFechaEmisionMedioPago)){
			dto.setTipoDeCambioFechaEmisionMedioPago(Utilidad.formatDecimales(tipoDeCambioFechaEmisionMedioPago,7));
		}

		BigDecimal importeMonedaOrigenMedioPago = vista.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago();
		if(!Validaciones.isObjectNull(importeMonedaOrigenMedioPago)){
			dto.setImporteAplicadoFechaEmisionMonedaOrigenMedioPago(Utilidad.formatDecimales(importeMonedaOrigenMedioPago, 2));
		}
		
		
		
//		------------------------
//		Comunes
//		------------------------
		BigDecimal intereses = vista.getIntereses();
		if(!Validaciones.isObjectNull(intereses)){
			dto.setIntereses(Utilidad.formatDecimales(intereses, 2));
		}
		
		if(!Validaciones.isObjectNull(vista.getCobradorInteresesTrasladados())){
			dto.setCobradorInteresesTrasladados(vista.getCobradorInteresesTrasladados().toString());
		}
		
		SiNoEnum trasladarIntereses = vista.getTrasladarIntereses();
		if (!Validaciones.isObjectNull(trasladarIntereses) && !SistemaEnum.MIC.equals(sistemaDoc) && !SistemaEnum.CALIPSO.equals(sistemaDoc)) {
			if(SiNoEnum.SI.equals(trasladarIntereses)){
				dto.setTrasladarIntereses(true);
			} else {
				dto.setTrasladarIntereses(false);
			}
		} else if(!Validaciones.isObjectNull(trasladarIntereses) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			if(SiNoEnum.SI.equals(trasladarIntereses)){
				dto.setTrasladarIntereses(true);
			} else {
				dto.setTrasladarIntereses(false);
			}
		} else {
			dto.setTrasladarIntereses(false);
			dto.setTrasladarInteresesFormateado("-");
		}
		dto.setGeneraInteresesParamUso(SiNoEnum.SI.equals(vista.getGeneraInteresesParamUso()) ? true : false);
		Integer porcABonificar = vista.getPorcentajeABonificar();
		if(!Validaciones.isObjectNull(porcABonificar) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			dto.setPorcABonificar(porcABonificar.toString());
		}
		
		BigDecimal importeABonificar = vista.getImporteABonificar();
		if(!Validaciones.isObjectNull(importeABonificar) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			String importeABonificarFormateado = Utilidad.formatCurrency(importeABonificar, 2);
			dto.setImporteABonificar(Utilidad.formatCurrencySacarPesos(importeABonificarFormateado));
		}
		
		Long acuerdoDestinoCargos = vista.getAcuerdoDestinoCargos();
		if(!Validaciones.isObjectNull(acuerdoDestinoCargos)){
			dto.setAcuerdoDestinoCargos(acuerdoDestinoCargos.toString());
		}

		Long acuerdoDestinoCargosOriginal = vista.getAcuerdoDestinoCargosOriginal();
		if(!Validaciones.isObjectNull(acuerdoDestinoCargosOriginal)){
			dto.setAcuerdoDestinoCargosOriginal(acuerdoDestinoCargosOriginal.toString());
		}

		EstadoAcuerdoFacturacionEnum estadoAcuerdo = vista.getEstadoAcuerdoDestinoCargos();
		if(!Validaciones.isObjectNull(estadoAcuerdo)){
			dto.setEstadoAcuerdoDestinoCargos(estadoAcuerdo.descripcion());
		}
		
		Long idClienteLegadoAcuerdoTrasladoCargo = vista.getIdClienteLegadoAcuerdoTrasladoCargo();
		if (!Validaciones.isObjectNull(idClienteLegadoAcuerdoTrasladoCargo)) {
			dto.setClienteAcuerdoDestinoCargos(idClienteLegadoAcuerdoTrasladoCargo.toString());
		}
		
		//
		// Manejo de visualizacion de mensajes de error para transaccion, debito y credito
		//
		TipoMensajeEstadoEnum tipoMensajeTransaccion = vista.getTipoMensajeTransaccion();
		dto.setMensajeTransaccion(vista.getMensajeTransaccion());
		if (!Validaciones.isObjectNull(tipoMensajeTransaccion)) {
			dto.setTipoMensajeTransaccion(tipoMensajeTransaccion.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeTransaccion)) {
				dto.setMensajeTransaccion(tipoMensajeTransaccion.name());
			} else {
				dto.setMensajeTransaccion(vista.getMensajeTransaccion());
			}
		}

		TipoMensajeEstadoEnum tipoMensajeDebito = vista.getTipoMensajeDebito();
		dto.setMensajeDebito(vista.getMensajeDebito());
		if (!Validaciones.isObjectNull(tipoMensajeDebito)) {
			dto.setTipoMensajeDebito(tipoMensajeDebito.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeDebito)) {
				dto.setMensajeDebito(tipoMensajeDebito.name());
			} else {
				dto.setMensajeDebito(vista.getMensajeDebito());
			}
		}
		
		TipoMensajeEstadoEnum tipoMensajeCredito = vista.getTipoMensajeCredito();
		dto.setMensajeCredito(vista.getMensajeCredito());
		if (!Validaciones.isObjectNull(tipoMensajeCredito)) {
			dto.setTipoMensajeCredito(tipoMensajeCredito.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeCredito)) {
				dto.setMensajeCredito(tipoMensajeCredito.name());
			} else {
				dto.setMensajeCredito(vista.getMensajeCredito());
			}
		}

		//el id_transaccion_padre con valor indica que es diferencia de cambio de calipso
		//entonces se pasa un 1 para que se dibuja todo grisado con el color de letra diferente
//		Long idTransaccionPadre = vista.getIdTransaccionPadre();
//		if(!Validaciones.isObjectNull(idTransaccionPadre)){
//			dto.setColorLetraRegistro("1");
//		} else {
//			dto.setColorLetraRegistro("0");
//		}
		
		//el id_transaccion_padre con valor indica que es diferencia de cambio de calipso
		//entonces se pasa un 1 para que se dibuja todo grisado con el color de letra diferente
		Long numeroTransaccionPadre = vista.getNumeroTransaccionPadre();
		if(!Validaciones.isObjectNull(numeroTransaccionPadre)){
			dto.setColorLetraRegistro("1");
		} else {
			dto.setColorLetraRegistro("0");
		}
		
		//dto.setSoloLecturaPantalla(soloLecturaPantalla);
		
		Long idFactura = vista.getIdFactura();
		if(!Validaciones.isObjectNull(idFactura)){
			dto.setIdFactura(idFactura.toString());
		}
		Long idMedioPago = vista.getIdMedioPago();
		if(!Validaciones.isObjectNull(idMedioPago)){
			dto.setIdMedioPago(idMedioPago.toString());
		}
		Long idTratamientoDiferencia = vista.getIdTratamientoDiferencia();
		if(!Validaciones.isObjectNull(idTratamientoDiferencia)){
			dto.setIdTratamientoDiferencia(idTratamientoDiferencia.toString());
		}
		
		dto.setEsTrasladarInteresesObligatorio(vista.getEsTrasladarInteresesObligatorio());
		
		if (!Validaciones.isObjectNull(vista.getGeneraInteresesParamUso())) {
			dto.setGeneraInteresesParamUso(SiNoEnum.SI.equals(vista.getGeneraInteresesParamUso()) ? true : false);
		}
		
		if(!Validaciones.isObjectNull(vista.getApocope())){
			dto.setApocopeGrupo(vista.getApocope());
		}
		
		return dto;
	}

	public ShvCobTransaccion map(CobroTransaccionDto dto, ShvCobTransaccion modelo) throws NegocioExcepcion {
		
		modelo = mapearTratamientosDiferencias(dto, modelo);
		
		modelo = mapearFacturas(dto, modelo);
		
		modelo = mapearMediosDePago(dto, modelo);
		
		modelo = mapearTransaccion(dto, modelo);
		
		return modelo;
	}
	
	private ShvCobTransaccion mapearTratamientosDiferencias(CobroTransaccionDto dto, ShvCobTransaccion modelo) {
		
		ShvCobTratamientoDiferencia tratamientoDiferencia = modelo.getTratamientoDiferencia();
		
		if(!Validaciones.isObjectNull(tratamientoDiferencia)){

			Integer idTratamientoDiferencia = tratamientoDiferencia.getIdTratamientoDiferencia();
			
			if(!Validaciones.isObjectNull(idTratamientoDiferencia)){
				
				if(idTratamientoDiferencia.toString().equals(dto.getIdTratamientoDiferencia())){
					
					tratamientoDiferencia.setTrasladarIntereses(dto.getTrasladarIntereses()?SiNoEnum.SI:SiNoEnum.NO);
					
					String porcentaje = dto.getPorcABonificar();
					if(!Validaciones.isNullEmptyOrDash(porcentaje)){
						tratamientoDiferencia.setPorcentajeBonifIntereses(new Integer(porcentaje));
					} else {
						tratamientoDiferencia.setPorcentajeBonifIntereses(null);
					}
					
					String importe = dto.getImporteABonificar();
					if(!Validaciones.isNullEmptyOrDash(importe)){
						tratamientoDiferencia.setImporteBonificacionIntereses(Utilidad.stringToBigDecimal(importe));
					} else {
						tratamientoDiferencia.setImporteBonificacionIntereses(null);
					}
					
					String acuerdo = dto.getAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(acuerdo)){
						tratamientoDiferencia.setAcuerdoTrasladoCargo(acuerdo);
					}
					
					String estadoAcuerdo = dto.getEstadoAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(estadoAcuerdo)){
						tratamientoDiferencia.setEstadoAcuerdoTrasladoCargo(EstadoAcuerdoFacturacionEnum.getEnumByDescripcion(estadoAcuerdo));
					} else {
						tratamientoDiferencia.setEstadoAcuerdoTrasladoCargo(null);
					}
					
					String clienteAcuerdo = dto.getClienteAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(clienteAcuerdo)){
						tratamientoDiferencia.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(clienteAcuerdo));
					} else {
						tratamientoDiferencia.setIdClienteLegadoAcuerdoTrasladoCargo(null);
					}
				}
			}
		}
		return modelo;
	}

	private ShvCobTransaccion mapearFacturas(CobroTransaccionDto dto, ShvCobTransaccion modelo) {
		
		ShvCobFactura factura = modelo.getFactura();
		
		if(!Validaciones.isObjectNull(factura)){
				
			Integer idFactura = factura.getIdFactura();
			
			if(!Validaciones.isObjectNull(idFactura)){
				
				if(idFactura.toString().equals(dto.getIdFactura())){
					
					factura.setTrasladarIntereses(dto.getTrasladarIntereses()?SiNoEnum.SI:SiNoEnum.NO);

					String porcentaje = dto.getPorcABonificar();
					if(!Validaciones.isNullEmptyOrDash(porcentaje)){
						factura.setPorcentajeBonifIntereses(new Integer(porcentaje));
					} else {
						factura.setPorcentajeBonifIntereses(null);
					}
					
					String importe = dto.getImporteABonificar();
					if(!Validaciones.isNullEmptyOrDash(importe)){
						factura.setImporteBonificacionIntereses(Utilidad.stringToBigDecimal(importe));
						if (factura instanceof ShvCobFacturaCalipso) {
							((ShvCobFacturaCalipso)factura).setCobradorInteresesBonificados(Utilidad.stringToBigDecimal(importe));
						}
						
					} else {
						factura.setImporteBonificacionIntereses(null);
						if (factura instanceof ShvCobFacturaCalipso) {
							((ShvCobFacturaCalipso)factura).setCobradorInteresesBonificados(null);
						}
					}
					
					String acuerdo = dto.getAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(acuerdo)){
						factura.setAcuerdoTrasladoCargo(acuerdo);		
					}
					
					String estadoAcuerdo = dto.getEstadoAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(estadoAcuerdo)){
						factura.setEstadoAcuerdoTrasladoCargo(EstadoAcuerdoFacturacionEnum.getEnumByDescripcion(estadoAcuerdo));
					} else {
						factura.setEstadoAcuerdoTrasladoCargo(null);
					}
					
					String clienteAcuerdo = dto.getClienteAcuerdoDestinoCargos();
					if(!Validaciones.isNullEmptyOrDash(clienteAcuerdo)){
						factura.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(clienteAcuerdo));
					} else {
						factura.setIdClienteLegadoAcuerdoTrasladoCargo(null);
					}
				}
			}
		}
		return modelo;
	}
	
	private ShvCobTransaccion mapearMediosDePago(CobroTransaccionDto dto, ShvCobTransaccion modelo) {
		Set<ShvCobMedioPago> mediosPago = modelo.getMediosPago();
		if(Validaciones.isCollectionNotEmpty(mediosPago)){
			Iterator<ShvCobMedioPago> it = mediosPago.iterator();
			
			while(it.hasNext()){
				ShvCobMedioPago medioPago = it.next();
				ShvParamTipoMedioPago tipoMedioPago = medioPago.getTipoMedioPago();
				
				if(!Validaciones.isObjectNull(medioPago)){					
					if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())
							|| TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){	
						
						String idMedioPago = dto.getIdMedioPago();
						
						if(!Validaciones.isNullEmptyOrDash(idMedioPago)){	
							
							if(medioPago.getIdMedioPago().toString().equals(idMedioPago)){
								
								if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){
									
									ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoClp = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;
									
									medioPagoClp.setTrasladarIntereses(dto.getTrasladarIntereses()?SiNoEnum.SI:SiNoEnum.NO);
									
									String interes = dto.getIntereses();
									if(!Validaciones.isNullEmptyOrDash(interes)){
										medioPagoClp.setCobradorIntereseGenerados(Utilidad.stringToBigDecimal(interes));
									} else {
										medioPagoClp.setCobradorIntereseGenerados(null);
									}
									
									String porcentaje = dto.getPorcABonificar();
									if(!Validaciones.isNullEmptyOrDash(porcentaje)){
										medioPagoClp.setPorcentajeBonifIntereses(new Integer(porcentaje));
									} else {
										medioPagoClp.setPorcentajeBonifIntereses(null);
									}
									
									String importe = dto.getImporteABonificar();
									if(!Validaciones.isNullEmptyOrDash(importe)){
										medioPagoClp.setImporteBonificacionIntereses(Utilidad.stringToBigDecimal(importe));
									} else {
										medioPagoClp.setImporteBonificacionIntereses(null);
									}
									
									String acuerdo = dto.getAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(acuerdo)){
										medioPagoClp.setAcuerdoTrasladoCargo(acuerdo);		
									}
									
									String estadoAcuerdo = dto.getEstadoAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(estadoAcuerdo)){
										medioPagoClp.setEstadoAcuerdoTrasladoCargo(EstadoAcuerdoFacturacionEnum.getEnumByDescripcion(estadoAcuerdo));
									} else {
										medioPagoClp.setEstadoAcuerdoTrasladoCargo(null);
									}
									
									String clienteAcuerdo = dto.getClienteAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(clienteAcuerdo)){
										medioPagoClp.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(clienteAcuerdo));
									} else {
										medioPagoClp.setIdClienteLegadoAcuerdoTrasladoCargo(null);
									}

								} else if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){
									
									ShvCobMedioPagoDebitoProximaFacturaMic medioPagoMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;
									
									medioPagoMic.setTrasladarIntereses(dto.getTrasladarIntereses()?SiNoEnum.SI:SiNoEnum.NO);
									
									String interes = dto.getIntereses();
									if(!Validaciones.isNullEmptyOrDash(interes)){
										medioPagoMic.setCobradorInteresesGenerados(Utilidad.stringToBigDecimal(interes));
									} else {
										medioPagoMic.setCobradorInteresesGenerados(null);
									}
									
									String porcentaje = dto.getPorcABonificar();
									if(!Validaciones.isNullEmptyOrDash(porcentaje)){
										medioPagoMic.setPorcentajeBonifIntereses(new Integer(porcentaje));
									} else {
										medioPagoMic.setPorcentajeBonifIntereses(null);
									}
									
									String importe = dto.getImporteABonificar();
									if(!Validaciones.isNullEmptyOrDash(importe)){
										medioPagoMic.setImporteBonificacionIntereses(Utilidad.stringToBigDecimal(importe));
									} else {
										medioPagoMic.setImporteBonificacionIntereses(null);
									}
									
									String acuerdo = dto.getAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(acuerdo)){
										medioPagoMic.setAcuerdoTrasladoCargo(acuerdo);		
									}
									
									String estadoAcuerdo = dto.getEstadoAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(estadoAcuerdo)){
										medioPagoMic.setEstadoAcuerdoTrasladoCargo(EstadoAcuerdoFacturacionEnum.getEnumByDescripcion(estadoAcuerdo));
									} else {
										medioPagoMic.setEstadoAcuerdoTrasladoCargo(null);
									}
									
									String clienteAcuerdo = dto.getClienteAcuerdoDestinoCargos();
									if(!Validaciones.isNullEmptyOrDash(clienteAcuerdo)){
										medioPagoMic.setIdClienteLegadoAcuerdoTrasladoCargo(new Long(clienteAcuerdo));
									} else {
										medioPagoMic.setIdClienteLegadoAcuerdoTrasladoCargo(null);
									}
								}
							}
						}
					}
				}
			}
		}
		return modelo;
	}
	private ShvCobTransaccion mapearTransaccion(CobroTransaccionDto dto, ShvCobTransaccion modelo) {
		if(!Validaciones.isObjectNull(modelo)) {
			modelo.setMensajeEstado(dto.getMensajeTransaccion());
			if(!Validaciones.isNullEmptyOrDash(dto.getTipoMensajeTransaccion())){
				modelo.setTipoMensajeEstado(TipoMensajeEstadoEnum.getEnumByName(dto.getTipoMensajeTransaccion()));
			}
		}
		return modelo;
	}
	@Override
	public void setObjeto(Object cualquierAtributo) throws NegocioExcepcion {
		//soloLecturaPantalla = (boolean) cualquierAtributo;
	}
}
