package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
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
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;

public class DescobroTransaccionesMapeador extends MapeadorResultadoBusqueda implements IMapeadorUtil {

	boolean soloLecturaPantalla;
	
	@Override
	public void setObjeto(Object cualquierAtributo) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)
			throws NegocioExcepcion {
		
		DescobroTransaccionDto dto = new DescobroTransaccionDto();
		VistaSoporteResultadoBusquedaDescobroTransaccion vista = (VistaSoporteResultadoBusquedaDescobroTransaccion) vistaSoporteResultadoBusqueda;
		
//		------------------------
//		Comunes
//		------------------------
		dto.setNumeroTransaccionFormateado(vista.getNumeroTransaccionFormateado());
		dto.setNumeroTransaccionFicticioFormateado(vista.getNumeroTransaccionFicticioFormateado());
		dto.setNumeroTransaccion(vista.getNumeroTransaccion().toString());
		dto.setNumeroTransaccionFicticio(vista.getNumeroTransaccionFicticio().toString());
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
		}
		
		TipoComprobanteEnum tipoDocumento = vista.getTipoComprobante();
		if(!Validaciones.isObjectNull(tipoDocumento)){
			dto.setTipoDocumento(tipoDocumento.descripcion());
		}
		
		OrigenDocumentoEnum origenDocumento = vista.getOrigenDocumento();
		if(!Validaciones.isObjectNull(origenDocumento) && !Validaciones.isObjectNull(vista.getIdFactura())){
			dto.setSubtipoDocumento(origenDocumento.name());
			dto.setSubtipoDocumentoDesc(origenDocumento.descripcion());
		} else {
			dto.setSubtipoDocumento(vista.getSubTipoDocumento());
			dto.setSubtipoDocumentoDesc(vista.getDescripcionSubTipoDocumento());
		}
		
		dto.setNroDoc(vista.getNumeroDocumento());
		dto.setNumeroReferenciaMic(vista.getNumeroReferenciaMic());		
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
		if(!Validaciones.isObjectNull(tipoMedioPago)){
			if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.equals(tipoMedioPago) || TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.equals(tipoMedioPago)){
				dto.setTipoMedioPago(TipoTratamientoDiferenciaEnum.getEnumByIdtipoMedioPagoAsociado(tipoMedioPago.getIdTipoMedioPago()).getDescripcion());
			} else {
				dto.setTipoMedioPago(TipoCreditoEnum.getEnumByIdTipoMedioPago(tipoMedioPago.getIdTipoMedioPago()).getDescripcion());
			}
		}
		
		OrigenDocumentoEnum origenMedioPago = vista.getOrigenDocumento();
		if(!Validaciones.isObjectNull(origenMedioPago) && vista.getIdMedioPago() > 0){
			dto.setSubtipoMedioPago(origenMedioPago.name());
			dto.setSubtipoMedioPagoDesc(origenMedioPago.descripcion());
		} else {
			TipoRetencionEnum subTipoMedioPago = vista.getSubTipoMedioPago();
			if(!Validaciones.isObjectNull(subTipoMedioPago)){
				dto.setSubtipoMedioPago(subTipoMedioPago.getIdTipoMedioPago());
				dto.setSubtipoMedioPagoDesc(subTipoMedioPago.getDescripcion());
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
			dto.setTipoDeCambioFechaCobroMedioPago(Utilidad.formatDecimales(tipoDeCambioFechaCobroMedioPago, 7));
		}
		
		BigDecimal tipoDeCambioFechaEmisionMedioPago = vista.getTipoDeCambioFechaEmisionMedioPago();
		if(!Validaciones.isObjectNull(tipoDeCambioFechaEmisionMedioPago)){
			dto.setTipoDeCambioFechaEmisionMedioPago(Utilidad.formatDecimales(tipoDeCambioFechaEmisionMedioPago, 7));
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
			String interesesFormateado = Utilidad.formatDecimales(intereses, 2);
			dto.setIntereses(interesesFormateado);
		}
		
		BigDecimal cobradorInteresesTrasladados = vista.getCobradorInteresesTrasladados();
		if(!Validaciones.isObjectNull(cobradorInteresesTrasladados)){
			String cobradorInteresesTrasladadosFormateado = Utilidad.formatCurrency(cobradorInteresesTrasladados, 2);
			dto.setCobradorInteresesTrasladados(cobradorInteresesTrasladadosFormateado);
		}
		
		SiNoEnum trasladarIntereses = vista.getTrasladarIntereses();
		if(!Validaciones.isObjectNull(trasladarIntereses) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			if(SiNoEnum.SI.equals(trasladarIntereses)){
				dto.setTrasladarIntereses(true);
				dto.setTrasladarInteresesFormateado(SiNoEnum.SI.getDescripcion());
			} else {
				dto.setTrasladarIntereses(false);
				dto.setTrasladarInteresesFormateado(SiNoEnum.NO.getDescripcion());
			}
		} else {
			dto.setTrasladarIntereses(false);
			dto.setTrasladarInteresesFormateado("-");
		}
		
		Integer porcABonificar = vista.getPorcentajeABonificar();
		if(!Validaciones.isObjectNull(porcABonificar) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			dto.setPorcABonificar(porcABonificar.toString());
		}
		
		BigDecimal importeABonificar = vista.getImporteABonificar();
		if(!Validaciones.isObjectNull(importeABonificar) && !Validaciones.isObjectNull(vista.getIntereses()) && vista.getIntereses().compareTo(new BigDecimal(0)) > 0){
			dto.setImporteABonificar(Utilidad.formatDecimales(importeABonificar, 2));
		}
		
		
		if((!Validaciones.isObjectNull(cobradorInteresesTrasladados) 
				&& !new BigDecimal("0").equals(cobradorInteresesTrasladados)
				&& cobradorInteresesTrasladados.compareTo(new BigDecimal("0")) == 1)
				|| TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.equals(tipoMedioPago) 
				|| TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.equals(tipoMedioPago) 
				|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento().equals(vista.getSubTipoDocumento())) {
			
			SistemaEnum sistemaAcuerdo = vista.getSistemaAcuerdo(); 
			if(!Validaciones.isObjectNull(sistemaAcuerdo)){
				dto.setSistemaAcuerdo(sistemaAcuerdo.getDescripcion());
			}
			
			Long acuerdoDestinoCargos = vista.getAcuerdoDestinoCargos();
			if(!Validaciones.isObjectNull(acuerdoDestinoCargos)){
				dto.setAcuerdoDestinoCargos(acuerdoDestinoCargos.toString());
			}
			
			EstadoAcuerdoFacturacionEnum estadoAcuerdo = vista.getEstadoAcuerdoDestinoCargos();
			if(!Validaciones.isObjectNull(estadoAcuerdo)){
				dto.setEstadoAcuerdoDestinoCargos(estadoAcuerdo.descripcion());
			}
			
			Long idClienteAcuerdoCargo = vista.getIdClienteLegadoAcuerdoTrasladoCargo();
			if(!Validaciones.isObjectNull(idClienteAcuerdoCargo)){
				dto.setClienteAcuerdoDestinoCargos(idClienteAcuerdoCargo.toString());
			}
			
			EstadoCargoGeneradoEnum estadoCargo = vista.getEstadoCargoGenerado();
			if(!Validaciones.isObjectNull(estadoCargo)){
				dto.setEstadoCargoProximaFactura(estadoCargo.descripcion());
			}

			SistemaEnum sistemaAcuerdoContracargo = vista.getSistemaAcuerdoContracargo(); 
			if(!Validaciones.isObjectNull(sistemaAcuerdoContracargo)){
				dto.setSistemaAcuerdoFactDestinoContracargos(sistemaAcuerdoContracargo.name());
				dto.setSistemaAcuerdoFactDestinoContracargosDescripcion(sistemaAcuerdoContracargo.getDescripcion());
//				if(SistemaEnum.MIC.equals(sistemaAcuerdoContracargo) && SistemaEnum.MIC.equals(sistemaAcuerdo)) {
//					dto.setMicAcuerdoExistente(true);//mas abajo se setea el booleano definitivo, este es parcial(SOLO VALIDA Q SEA MIC)
//				}
			}else if(!Validaciones.isObjectNull(sistemaAcuerdo)){
				dto.setSistemaAcuerdoFactDestinoContracargos(sistemaAcuerdo.name());
				dto.setSistemaAcuerdoFactDestinoContracargosDescripcion(sistemaAcuerdo.getDescripcion());
//				if(SistemaEnum.MIC.equals(sistemaAcuerdo)) {
//					dto.setMicAcuerdoExistente(true);//mas abajo se setea el booleano definitivo, este es parcial(SOLO VALIDA Q SEA MIC)
//				}
			}
			
			//TODO - a Fabio 1Reversion PROBAR TRANSACCION DATOS CONTRACARGO
			Long acuerdoContracargo = vista.getAcuerdoContracargo();
			if(!Validaciones.isObjectNull(acuerdoContracargo)){
				dto.setAcuerdoFactDestinoContracargos(acuerdoContracargo.toString());
			}else if(!Validaciones.isObjectNull(acuerdoDestinoCargos)){
				dto.setAcuerdoFactDestinoContracargos(acuerdoDestinoCargos.toString());
			}
			
			//TODO - a Fabio 1Reversion PROBAR TRANSACCION DATOS CONTRACARGO
			EstadoAcuerdoFacturacionEnum estadoAcuerdoContracargo = vista.getEstadoAcuerdoContracargo();
			if(!Validaciones.isObjectNull(estadoAcuerdoContracargo)){
				dto.setEstadoAcuerdoDestinoContracargos(estadoAcuerdoContracargo.descripcion());
//				if((!EstadoAcuerdoFacturacionEnum.ACTIVO.equals(estadoAcuerdoContracargo) 
//						&& !EstadoAcuerdoFacturacionEnum.INCOMUNICADO.equals(estadoAcuerdoContracargo))
//						&& dto.isMicAcuerdoExistente()) {
//					dto.setMicAcuerdoExistente(false);
//				}
			}else if(!Validaciones.isObjectNull(estadoAcuerdo)){
				//chequear si rompe aca, acuerdoDestinoCargos.toString(), no debiera 
				//porque primero pregunta q haya un estado acuerdo, si hay un estado, deberia haber un acuerdo.
				if(!Validaciones.isObjectNull(acuerdoDestinoCargos)){
					if(acuerdoDestinoCargos.toString().equals(dto.getAcuerdoFactDestinoContracargos())) {
						dto.setEstadoAcuerdoDestinoContracargos(estadoAcuerdo.descripcion());
//						if((!EstadoAcuerdoFacturacionEnum.ACTIVO.equals(estadoAcuerdo) 
//								&& !EstadoAcuerdoFacturacionEnum.INCOMUNICADO.equals(estadoAcuerdo))
//								&& dto.isMicAcuerdoExistente()) {
//							dto.setMicAcuerdoExistente(false);
//						}
					}
				}
			}
			
			//TODO - a Fabio 1Reversion PROBAR TRANSACCION DATOS CONTRACARGO
			Long idClienteAcuerdoContracargo = vista.getIdClienteAcuerdoContracargo();
			if(!Validaciones.isObjectNull(idClienteAcuerdoContracargo)){
				dto.setClienteAcuerdoDestinoContracargos(idClienteAcuerdoContracargo.toString());
			}else if(!Validaciones.isObjectNull(idClienteAcuerdoCargo)){
				dto.setClienteAcuerdoDestinoContracargos(idClienteAcuerdoCargo.toString());
			}

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
		
		dto.setSoloLecturaPantalla(soloLecturaPantalla);
		
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
		return dto;
	}

	public ShvCobTransaccion map(DescobroTransaccionDto dto, ShvCobTransaccion modelo) throws NegocioExcepcion{

		
		modelo = mapearTratamientosDiferencias(dto, modelo);
		
		modelo = mapearFacturas(dto, modelo);
		
		modelo = mapearMediosDePago(dto, modelo);
		
		
		String tipoMensajeTransaccion = dto.getTipoMensajeTransaccion();
		if(!Validaciones.isNullEmptyOrDash(tipoMensajeTransaccion)) {
			modelo.setTipoMensajeEstado(TipoMensajeEstadoEnum.getEnumByName(tipoMensajeTransaccion));
		}else {
			modelo.setTipoMensajeEstado(null);
		}
		
		String mensajeTransaccion = dto.getMensajeTransaccion();
		if(!Validaciones.isNullEmptyOrDash(mensajeTransaccion)) {
			modelo.setMensajeEstado(mensajeTransaccion);
		}else {
			modelo.setMensajeEstado(null);
		}
		
		return modelo;
	}
	
	private ShvCobTransaccion mapearTratamientosDiferencias(DescobroTransaccionDto dto, ShvCobTransaccion modelo) {
		
		ShvCobTratamientoDiferencia tratamientoDiferencia = modelo.getTratamientoDiferencia();
		
		if(!Validaciones.isObjectNull(tratamientoDiferencia)){

			Integer idTratamientoDiferencia = tratamientoDiferencia.getIdTratamientoDiferencia();
			
			if((!Validaciones.isObjectNull(idTratamientoDiferencia) && idTratamientoDiferencia.toString().equals(dto.getIdTratamientoDiferencia()))
				|| (!Validaciones.isObjectNull(idTratamientoDiferencia) && idTratamientoDiferencia.toString().equals(dto.getIdTratamientoDiferencia()))){
	
				//contracargos
				
				String sistemaAcuerdoContracargos = dto.getSistemaAcuerdoFactDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(sistemaAcuerdoContracargos)){
					tratamientoDiferencia.setSistemaAcuerdoContracargo(SistemaEnum.getEnumByName(sistemaAcuerdoContracargos));
				} else {
					tratamientoDiferencia.setSistemaAcuerdoContracargo(null);
				}
				
				String acuerdoContracargos = dto.getAcuerdoFactDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(acuerdoContracargos)){
					tratamientoDiferencia.setAcuerdoContracargo(acuerdoContracargos);
				} else {
					tratamientoDiferencia.setAcuerdoContracargo(null);
				}
				
				String estadoAcuerdoContracargos = dto.getEstadoAcuerdoDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(estadoAcuerdoContracargos)){
					tratamientoDiferencia.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByName(estadoAcuerdoContracargos));
				} else {
					tratamientoDiferencia.setEstadoAcuerdoContracargo(null);
				}
				
				String clienteAcuerdoContracargos = dto.getClienteAcuerdoDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(clienteAcuerdoContracargos)){
					tratamientoDiferencia.setIdClienteLegadoAcuerdoContracargo(new Long(clienteAcuerdoContracargos));
				} else {
					tratamientoDiferencia.setIdClienteLegadoAcuerdoContracargo(null);
				}
				
				String tipoMensajeCredito = dto.getTipoMensajeCredito();
				if(!Validaciones.isNullEmptyOrDash(tipoMensajeCredito)) {
					tratamientoDiferencia.setTipoMensajeEstado(TipoMensajeEstadoEnum.getEnumByName(tipoMensajeCredito));
				}else {
					tratamientoDiferencia.setTipoMensajeEstado(null);
				}
				
				String mensajeCredito = dto.getMensajeCredito();
				if(!Validaciones.isNullEmptyOrDash(mensajeCredito)) {
					tratamientoDiferencia.setMensajeEstado(mensajeCredito);
				}else {
					tratamientoDiferencia.setMensajeEstado(null);
				}
			}
		}
		return modelo;
	}

	private ShvCobTransaccion mapearFacturas(DescobroTransaccionDto dto, ShvCobTransaccion modelo) {
		
		ShvCobFactura factura = modelo.getFactura();
		
		if(!Validaciones.isObjectNull(factura)){
				
			Integer idFacturaCobro = factura.getIdFacturaCobro();
			Integer idFactura = factura.getIdFactura();
				
			if((!Validaciones.isObjectNull(idFacturaCobro) && idFacturaCobro.toString().equals(dto.getIdFactura()))
					|| (!Validaciones.isObjectNull(idFactura) && idFactura.toString().equals(dto.getIdFactura()))){
				
//					//contracargos
				
				String sistemaAcuerdoContracargos = dto.getSistemaAcuerdoFactDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(sistemaAcuerdoContracargos)){
					factura.setSistemaAcuerdoContracargo(SistemaEnum.getEnumByName(sistemaAcuerdoContracargos));
				} else {
					factura.setSistemaAcuerdoContracargo(null);
				}
				
				String acuerdoContracargos = dto.getAcuerdoFactDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(acuerdoContracargos)){
					factura.setAcuerdoContracargo(acuerdoContracargos);
				} else {
					factura.setAcuerdoContracargo(null);
				}
				
				String estadoAcuerdoContracargos = dto.getEstadoAcuerdoDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(estadoAcuerdoContracargos)){
					factura.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByName(estadoAcuerdoContracargos));
				} else {
					factura.setEstadoAcuerdoContracargo(null);
				}
				
				String clienteAcuerdoContracargos = dto.getClienteAcuerdoDestinoContracargos();
				if(!Validaciones.isNullEmptyOrDash(clienteAcuerdoContracargos)){
					factura.setIdClienteLegadoAcuerdoContracargo(new Long(clienteAcuerdoContracargos));
				} else {
					factura.setIdClienteLegadoAcuerdoContracargo(null);
				}

				String tipoMensajeDebito = dto.getTipoMensajeDebito();
				if(!Validaciones.isNullEmptyOrDash(tipoMensajeDebito)) {
					factura.setTipoMensajeEstado(TipoMensajeEstadoEnum.getEnumByName(tipoMensajeDebito));
				}else {
					factura.setTipoMensajeEstado(null);
				}
				
				String mensajeDebito = dto.getMensajeDebito();
				if(!Validaciones.isNullEmptyOrDash(mensajeDebito)) {
					factura.setMensajeEstado(mensajeDebito);
				}else {
					factura.setMensajeEstado(null);
				}
			}
		}
		return modelo;
	}
	
	private ShvCobTransaccion mapearMediosDePago(DescobroTransaccionDto dto, ShvCobTransaccion modelo) {
		Set<ShvCobMedioPago> mediosPago = modelo.getMediosPago();
		if(Validaciones.isCollectionNotEmpty(mediosPago)){
			Iterator<ShvCobMedioPago> it = mediosPago.iterator();
			
			while(it.hasNext()){
				ShvCobMedioPago medioPago = it.next();
				ShvParamTipoMedioPago tipoMedioPago = medioPago.getTipoMedioPago();
				
				if(!Validaciones.isObjectNull(medioPago)){
					
					String tipoMensajeCredito = dto.getTipoMensajeCredito();
					if(!Validaciones.isNullEmptyOrDash(tipoMensajeCredito)) {
						medioPago.setTipoMensajeEstado(TipoMensajeEstadoEnum.getEnumByName(tipoMensajeCredito));
					} else {
						medioPago.setTipoMensajeEstado(null);
					}
					
					String mensajeCredito = dto.getMensajeCredito();
					if(!Validaciones.isNullEmptyOrDash(mensajeCredito)) {
						medioPago.setMensajeEstado(mensajeCredito);
					} else {
						medioPago.setMensajeEstado(null);
					}
					
					if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())
							|| TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){	
						
						String idMedioPago = dto.getIdMedioPago();
						
						if(!Validaciones.isNullEmptyOrDash(idMedioPago)){	
							
							if((!Validaciones.isObjectNull(medioPago.getIdMedioPagoCobro()) && medioPago.getIdMedioPagoCobro().toString().equals(idMedioPago))
									|| (!Validaciones.isObjectNull(medioPago.getIdMedioPago()) && medioPago.getIdMedioPago().toString().equals(idMedioPago))){
								
								if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){
									
									ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoClp = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;
									
									//contracargos
									
									String sistemaAcuerdoContracargos = dto.getSistemaAcuerdoFactDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(sistemaAcuerdoContracargos)){
										medioPagoClp.setSistemaAcuerdoContracargo(SistemaEnum.getEnumByName(sistemaAcuerdoContracargos));
									} else {
										medioPagoClp.setSistemaAcuerdoContracargo(null);
									}
									
									String acuerdoContracargos = dto.getAcuerdoFactDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(acuerdoContracargos)){
										medioPagoClp.setAcuerdoContracargo(acuerdoContracargos);
									} else {
										medioPagoClp.setAcuerdoContracargo(null);
									}
									
									String estadoAcuerdoContracargos = dto.getEstadoAcuerdoDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(estadoAcuerdoContracargos)){
										medioPagoClp.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByName(estadoAcuerdoContracargos));
									} else {
										medioPagoClp.setEstadoAcuerdoContracargo(null);
									}
									
									String clienteAcuerdoContracargos = dto.getClienteAcuerdoDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(clienteAcuerdoContracargos)){
										medioPagoClp.setIdClienteLegadoAcuerdoContracargo(new Long(clienteAcuerdoContracargos));
									} else {
										medioPagoClp.setIdClienteLegadoAcuerdoContracargo(null);
									}

								} else if(TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.getIdTipoMedioPago().equals(tipoMedioPago.getIdTipoMedioPago())){
									
									ShvCobMedioPagoDebitoProximaFacturaMic medioPagoMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;
									
									//contracargos
									
									String sistemaAcuerdoContracargos = dto.getSistemaAcuerdoFactDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(sistemaAcuerdoContracargos)){
										medioPagoMic.setSistemaAcuerdoContracargo(SistemaEnum.getEnumByName(sistemaAcuerdoContracargos));
									} else {
										medioPagoMic.setSistemaAcuerdoContracargo(null);
									}
									
									String acuerdoContracargos = dto.getAcuerdoFactDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(acuerdoContracargos)){
										medioPagoMic.setAcuerdoContracargo(acuerdoContracargos);
									} else {
										medioPagoMic.setAcuerdoContracargo(null);
									}
									
									String estadoAcuerdoContracargos = dto.getEstadoAcuerdoDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(estadoAcuerdoContracargos)){
										medioPagoMic.setEstadoAcuerdoContracargo(EstadoAcuerdoFacturacionEnum.getEnumByName(estadoAcuerdoContracargos));
									} else {
										medioPagoMic.setEstadoAcuerdoContracargo(null);
									}
									
									String clienteAcuerdoContracargos = dto.getClienteAcuerdoDestinoContracargos();
									if(!Validaciones.isNullEmptyOrDash(clienteAcuerdoContracargos)){
										medioPagoMic.setIdClienteLegadoAcuerdoContracargo(new Long(clienteAcuerdoContracargos));
									} else {
										medioPagoMic.setIdClienteLegadoAcuerdoContracargo(null);
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
	
	public DTO map(CobroTransaccionDto cobroTransaccion) throws NegocioExcepcion {
		
		DescobroTransaccionDto dto = new DescobroTransaccionDto();
		
//		------------------------
//		Comunes
//		------------------------
		dto.setNumeroTransaccionFormateado(cobroTransaccion.getNumeroTransaccionFormateado());
		dto.setNumeroTransaccionFicticioFormateado(cobroTransaccion.getNumeroTransaccionFicticioFormateado());
		dto.setNumeroTransaccion(cobroTransaccion.getNumeroTransaccion().toString());
		dto.setNumeroTransaccionFicticio(cobroTransaccion.getNumeroTransaccionFicticio().toString());
		dto.setNumeroTransaccionOriginal(cobroTransaccion.getNumeroTransaccionOriginal().toString());
		
		EstadoTransaccionEnum estadoTransaccion = EstadoTransaccionEnum.getEnumByDescripcion(cobroTransaccion.getEstadoTransaccion());
		if(!Validaciones.isObjectNull(estadoTransaccion)){
			dto.setEstadoTransaccion(estadoTransaccion.descripcion());
		}
		
//		------------------------
//		Facturas
//		------------------------
		SistemaEnum sistemaDoc = SistemaEnum.getEnumByDescripcion(cobroTransaccion.getSistemaDoc());
		if(!Validaciones.isObjectNull(sistemaDoc)){
			dto.setSistemaDoc(sistemaDoc.getDescripcion());
		}else {
			dto.setSistemaDoc("-");
		}
		
		TipoComprobanteEnum tipoDocumento = TipoComprobanteEnum.getEnumByDescripcion(cobroTransaccion.getTipoDocumento());
		if(!Validaciones.isObjectNull(tipoDocumento)){
			dto.setTipoDocumento(tipoDocumento.descripcion());
		}else {
			dto.setTipoDocumento("-");
		}
		
//		OrigenDocumentoEnum origenDocumento = OrigenDocumentoEnum.getEnumByName(cobroTransaccion.getSubtipoDocumentoDesc());
//		if(!Validaciones.isObjectNull(origenDocumento) && !Validaciones.isObjectNull(cobroTransaccion.getIdFactura())){
//			dto.setSubtipoDocumento(origenDocumento.name());
//			dto.setSubtipoDocumentoDesc(origenDocumento.descripcion());
//		} else {
			dto.setSubtipoDocumento(cobroTransaccion.getSubtipoDocumento());
			dto.setSubtipoDocumentoDesc(cobroTransaccion.getSubtipoDocumentoDesc());
//		}
		
		dto.setNroDoc(cobroTransaccion.getNroDoc());
		dto.setNumeroReferenciaMic(cobroTransaccion.getNumeroReferencia());		
		dto.setFechaVenc(cobroTransaccion.getFechaVenc());
		dto.setFecha2doVenc(cobroTransaccion.getFecha2doVenc());
		dto.setCobrarAl2doVenc(cobroTransaccion.isCobrarAl2doVenc());
		MonedaEnum moneda = MonedaEnum.getEnumBySigno2(cobroTransaccion.getMoneda());
		if(!Validaciones.isObjectNull(moneda)){
			dto.setMoneda(moneda.getSigno2());
		}else {
			dto.setMoneda("-");
		}
		
		dto.setFechaCobro(cobroTransaccion.getFechaCobro());
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getImporte())){
			dto.setImporte(cobroTransaccion.getImporte());
		}
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getTipoDeCambioFechaCobro())){
			dto.setTipoDeCambioFechaCobro(cobroTransaccion.getTipoDeCambioFechaCobro());
		}
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getTipoDeCambioFechaEmision())){
			dto.setTipoDeCambioFechaEmision(cobroTransaccion.getTipoDeCambioFechaEmision());
		}
		if(!Validaciones.isObjectNull(cobroTransaccion.getImporteAplicadoFechaEmisionMonedaOrigen())){
			dto.setImporteAplicadoFechaEmisionMonedaOrigen(cobroTransaccion.getImporteAplicadoFechaEmisionMonedaOrigen());
		}
		
		dto.setApocopeGrupo(cobroTransaccion.getApocopeGrupo());
		dto.setNumeroGrupo(cobroTransaccion.getNumeroGrupo());
		
//		------------------------
//		Medio de Pago
//		------------------------
		if(!Validaciones.isObjectNull(cobroTransaccion.getSistemaMedioPago())){
			dto.setSistemaMedioPago(cobroTransaccion.getSistemaMedioPago());
		}
		
		if(!Validaciones.isNullEmptyOrDash(cobroTransaccion.getTipoMedioPago())){
			dto.setTipoMedioPago(cobroTransaccion.getTipoMedioPago());
		} else {
			dto.setTipoMedioPago("-");
		}
		
		if(!Validaciones.isNullEmptyOrDash(cobroTransaccion.getSubtipoMedioPago()) && new Long(cobroTransaccion.getIdMedioPago()) > 0){
			dto.setSubtipoMedioPago(cobroTransaccion.getSubtipoMedioPago());
			dto.setSubtipoMedioPagoDesc(cobroTransaccion.getSubtipoMedioPagoDesc());
		} else {
			if(!Validaciones.isObjectNull(cobroTransaccion.getSubtipoMedioPago())){
				dto.setSubtipoMedioPago(cobroTransaccion.getSubtipoMedioPago());
				dto.setSubtipoMedioPagoDesc(cobroTransaccion.getSubtipoMedioPagoDesc());
			}
		}
		
		dto.setReferenciaMedioPago(cobroTransaccion.getReferenciaMedioPago());
		dto.setFechaMedioPago(cobroTransaccion.getFechaMedioPago());
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getImporteMedioPago())){
			dto.setImporteMedioPago(cobroTransaccion.getImporteMedioPago());
		}
		
		dto.setMonedaMedioPago(cobroTransaccion.getMonedaMedioPago());
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getTipoDeCambioFechaCobroMedioPago())){
			dto.setTipoDeCambioFechaCobroMedioPago(cobroTransaccion.getTipoDeCambioFechaCobroMedioPago());
		}
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago())){
			dto.setImporteAplicadoFechaEmisionMonedaOrigenMedioPago(cobroTransaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago());
		}
		
//		------------------------
//		Comunes
//		------------------------
		if(!Validaciones.isObjectNull(cobroTransaccion.getIntereses())){
			dto.setIntereses(cobroTransaccion.getIntereses());
		}
		
		//VER BIEN ESTO
		if(!Validaciones.isObjectNull(cobroTransaccion.getTrasladarIntereses()) && !Validaciones.isObjectNull(cobroTransaccion.getIntereses()) && !cobroTransaccion.getIntereses().substring(cobroTransaccion.getIntereses().length()-4, cobroTransaccion.getIntereses().length()).equals("0,00")){
			if(cobroTransaccion.getTrasladarIntereses()){
				dto.setTrasladarIntereses(true);
				dto.setTrasladarInteresesFormateado(SiNoEnum.SI.getDescripcion());
			} else {
				dto.setTrasladarIntereses(false);
				dto.setTrasladarInteresesFormateado(SiNoEnum.NO.getDescripcion());
			}
		} else {
			dto.setTrasladarIntereses(false);
			dto.setTrasladarInteresesFormateado("-");
		}
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getPorcABonificar())){
			dto.setPorcABonificar(cobroTransaccion.getPorcABonificar());
		}
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getImporteABonificar())){
//			String importeABonificarFormateado = Utilidad.formatCurrency(importeABonificar, 2);
			dto.setImporteABonificar(cobroTransaccion.getImporteABonificar());
		}
		
		
		if((!Validaciones.isNullEmptyOrDash(cobroTransaccion.getCobradorInteresesTrasladados())
				&& Utilidad.decimalMayorACero(new BigDecimal(cobroTransaccion.getCobradorInteresesTrasladados())))
				|| TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion().equals(dto.getTipoMedioPago())
				|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getDescripcion().equals(dto.getTipoMedioPago())
				) {

			SistemaEnum sistemaAcuerdo = null;
					
			if(!Validaciones.isNullEmptyOrDash(cobroTransaccion.getSistemaAcuerdo())){
				sistemaAcuerdo = SistemaEnum.getEnumByDescripcion(cobroTransaccion.getSistemaAcuerdo());
			}
			if(!Validaciones.isNullEmptyOrDash(cobroTransaccion.getSistemaMedioPago())){
				sistemaAcuerdo = SistemaEnum.getEnumByDescripcion(cobroTransaccion.getSistemaMedioPago());
			}
			
			if(!Validaciones.isObjectNull(sistemaAcuerdo)){
				dto.setSistemaAcuerdo(sistemaAcuerdo.getDescripcion());
				dto.setSistemaAcuerdoFactDestinoContracargos(sistemaAcuerdo.name());
				dto.setSistemaAcuerdoFactDestinoContracargosDescripcion(sistemaAcuerdo.getDescripcion());
			}else {
				dto.setSistemaAcuerdo("-");
				dto.setSistemaAcuerdoFactDestinoContracargos("-");
				dto.setSistemaAcuerdoFactDestinoContracargosDescripcion("-");
			}
			
			if(!Validaciones.isObjectNull(cobroTransaccion.getAcuerdoDestinoCargos())){
				dto.setAcuerdoDestinoCargos(cobroTransaccion.getAcuerdoDestinoCargos());
				dto.setAcuerdoFactDestinoContracargos(cobroTransaccion.getAcuerdoDestinoCargos());
			}
			
			EstadoAcuerdoFacturacionEnum estadoAcuerdo = EstadoAcuerdoFacturacionEnum.getEnumByDescripcion(cobroTransaccion.getEstadoAcuerdoDestinoCargos());
			if(!Validaciones.isObjectNull(estadoAcuerdo)){
				dto.setEstadoAcuerdoDestinoCargos(estadoAcuerdo.descripcion());
				dto.setEstadoAcuerdoDestinoContracargos(estadoAcuerdo.descripcion());
			}else {
				dto.setEstadoAcuerdoDestinoCargos("-");
				dto.setEstadoAcuerdoDestinoContracargos("-");
			}
			
			if(!Validaciones.isObjectNull(cobroTransaccion.getClienteAcuerdoDestinoCargos())){
				dto.setClienteAcuerdoDestinoCargos(cobroTransaccion.getClienteAcuerdoDestinoCargos());
				dto.setClienteAcuerdoDestinoContracargos(cobroTransaccion.getClienteAcuerdoDestinoCargos());
			}
		}
		
		
		EstadoCargoGeneradoEnum estadoCargo = EstadoCargoGeneradoEnum.getEnumByName(cobroTransaccion.getEstadoCargoProximaFactura());
		if(!Validaciones.isObjectNull(estadoCargo)){
			dto.setEstadoCargoProximaFactura(estadoCargo.descripcion());
		}else {
			dto.setEstadoCargoProximaFactura("-");
		}
		
		
		//
		// Manejo de visualizacion de mensajes de error para transaccion, debito y credito
		//
		TipoMensajeEstadoEnum tipoMensajeTransaccion = TipoMensajeEstadoEnum.getEnumByDescripcion(cobroTransaccion.getTipoMensajeTransaccion());
		dto.setMensajeTransaccion(cobroTransaccion.getMensajeTransaccion());
		if (!Validaciones.isObjectNull(tipoMensajeTransaccion)) {
			dto.setTipoMensajeTransaccion(tipoMensajeTransaccion.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeTransaccion)) {
				dto.setMensajeTransaccion(tipoMensajeTransaccion.name());
			} else {
				dto.setMensajeTransaccion(cobroTransaccion.getMensajeTransaccion());
			}
		}

		TipoMensajeEstadoEnum tipoMensajeDebito = TipoMensajeEstadoEnum.getEnumByName(cobroTransaccion.getTipoMensajeDebito());
		dto.setMensajeDebito(cobroTransaccion.getMensajeDebito());
		if (!Validaciones.isObjectNull(tipoMensajeDebito)) {
			dto.setTipoMensajeDebito(tipoMensajeDebito.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeDebito)) {
				dto.setMensajeDebito(tipoMensajeDebito.name());
			} else {
				dto.setMensajeDebito(cobroTransaccion.getMensajeDebito());
			}
		}
		
		TipoMensajeEstadoEnum tipoMensajeCredito = TipoMensajeEstadoEnum.getEnumByName(cobroTransaccion.getTipoMensajeCredito());
		dto.setMensajeCredito(cobroTransaccion.getMensajeCredito());
		if (!Validaciones.isObjectNull(tipoMensajeCredito)) {
			dto.setTipoMensajeCredito(tipoMensajeCredito.name());
			if (TipoMensajeEstadoEnum.OK.equals(tipoMensajeCredito)) {
				dto.setMensajeCredito(tipoMensajeCredito.name());
			} else {
				dto.setMensajeCredito(cobroTransaccion.getMensajeCredito());
			}
		}

		dto.setColorLetraRegistro(cobroTransaccion.getColorLetraRegistro());
		
		dto.setSoloLecturaPantalla(soloLecturaPantalla);
		
		if(!Validaciones.isObjectNull(cobroTransaccion.getIdFactura())){
			dto.setIdFactura(cobroTransaccion.getIdFactura());
		}
		if(!Validaciones.isObjectNull(cobroTransaccion.getIdMedioPago())){
			dto.setIdMedioPago(cobroTransaccion.getIdMedioPago());
		}
		if(!Validaciones.isObjectNull(cobroTransaccion.getIdTratamientoDiferencia())){
			dto.setIdTratamientoDiferencia(cobroTransaccion.getIdTratamientoDiferencia());
		}
		return dto;
	}
}
