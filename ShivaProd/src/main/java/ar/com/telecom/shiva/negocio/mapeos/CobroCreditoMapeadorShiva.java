package ar.com.telecom.shiva.negocio.mapeos;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;

public class CobroCreditoMapeadorShiva extends MapeadorResultadoBusqueda {
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		try {
			CobroCreditoDto dto = new CobroCreditoDto();
			VistaSoporteResultadoBusquedaValor vista = (VistaSoporteResultadoBusquedaValor) vistaSoporteResultadoBusqueda;
			
			dto.setIdClienteLegado(vista.getIdClienteLegado());
			
			dto.setSistemaOrigen(SistemaEnum.SHIVA);
			dto.setSistemaDescripcion(SistemaEnum.SHIVA.getDescripcion());
			dto.setOrigen(OrigenDebitoEnum.ONLINE.getDescripcion());
			
			TipoCreditoEnum tipoCreditoEnum = TipoCreditoEnum.getEnumByIdTipoValor(vista.getIdTipoValor());
			dto.setTipoCredito(tipoCreditoEnum!=null?tipoCreditoEnum.getDescripcion():null);
			dto.setTipoCreditoEnum(tipoCreditoEnum!=null?tipoCreditoEnum.name():null);
			//
			//Lo uso en la pantalla
			String idPantalla = vista.getIdValor() 
					+ "_" + (tipoCreditoEnum!=null?tipoCreditoEnum.getIdTipoValor():"0") 
					+ "_" + vista.getReferenciaValor() + "_" + vista.getIdClienteLegado();
			dto.setIdCreditoPantalla(idPantalla);
			
			//
			dto.setTipoComprobante(null);
			dto.setTipoComprobanteEnum(null);
			if (Validaciones.isNullOrEmpty(vista.getIdOrigen())) {
				dto.setTipoComprobanteEnumShiva(TipoShivaEnum.AVISO_DE_PAGO);
			} else {
				dto.setTipoComprobanteEnumShiva(TipoShivaEnum.getEnumByIdOrigen(vista.getIdOrigen()));
			}
			//
			if (!Validaciones.isNullOrEmpty(vista.getIdTipoRetencion())) {
				TipoRetencionEnum tipoRetencionEnum = TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(vista.getIdTipoRetencion()));
				if (tipoRetencionEnum!=null) {
					dto.setSubtipo(tipoRetencionEnum.getIdString());
					dto.setSubtipoDocumentoDesc(tipoRetencionEnum.getDescripcion());
					dto.setDescripcionTipoRetencion(tipoRetencionEnum.getDescripcion());
					//
					dto.setIdTipoMedioPago(tipoRetencionEnum.getIdTipoMedioPago());
					//				
				}
			} else {
				dto.setIdTipoMedioPago(tipoCreditoEnum!=null?tipoCreditoEnum.getIdTipoMedioPago():null);
			}
			//
			dto.setNroDoc(vista.getReferenciaValor());
			dto.setSaldoMonOrigen(Utilidad.formatCurrency(vista.getSaldoDisponible(), true, true, 2));
			dto.setMoneda(MonedaEnum.PES.getSigno2());
			dto.setMonedaEnum(MonedaEnum.PES);
			dto.setImpMonOrigen(Utilidad.formatCurrency(vista.getImporte(), true,true,2));
			//
			dto.setFechaAltaCredito(Utilidad.formatDatePicker(Utilidad.parseDateAndTimeString(vista.getFechaAlta())));
			dto.setFechaEmision(Utilidad.formatDatePicker(Utilidad.parseDatePickerString(vista.getFechaEmision())));
			//
			dto.setFechaValor(Utilidad.formatDatePicker(vista.getFechaValor()));
			dto.setFechaIngresoRecibo(Utilidad.formatDatePicker(vista.getFechaIngresoRecibo()));
			dto.setFechaDeposito(Utilidad.formatDatePicker(vista.getFechaDeposito()));
			dto.setFechaTrans(Utilidad.formatDatePicker(vista.getFechaTransferencia()));
			dto.setFechaVenc(Utilidad.formatDatePicker(vista.getFechaVencimiento()));
			dto.setFechaUltimoMov(Utilidad.formatDatePicker(vista.getFechaUltimoEstado()));
			//
			dto.setProvincia(vista.getProvinciaRetencion());
			dto.setCuit(!Validaciones.isNullOrEmpty(vista.getNroCuitRetencion())?Utilidad.formatearCuit(vista.getNroCuitRetencion()):null);
			//
			dto.setReferenciaValor(vista.getReferenciaValor());
			//
			EstadoOrigenEnum estadoOrigenEnum = EstadoOrigenEnum.getEnumByDescripcion(vista.getEstadoValor());
			dto.setEstadoOrigen(estadoOrigenEnum!=null?vista.getEstadoValor():null);
			
			dto.setSinDifDeCambio(false);
			
			//campos semaforo
			dto.setEstadoOrigenEnum(estadoOrigenEnum);
			
			//Para ordenamiento
	        dto.setOrderByIdClienteLegado(dto.getIdClienteLegado());
			dto.setOrderByNumeroIdentificatorio(dto.getNroDoc()!=null?dto.getNroDoc():"");
			dto.setOrderByFecha(!Validaciones.isNullOrEmpty(vista.getFechaBusqueda()) 
					? Utilidad.formatDatePicker(Utilidad.parseDateAndTimeString(vista.getFechaBusqueda())) : null);
			
			//Para semaforo
			if (String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE);
			} else if (String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.CHEQUE);
			} else if(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF);
			} else if (String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.CHEQUEDIF);
			} else if(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.BOLETA_DEPOSITO_EFECTIVO);
			} else if (String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()).equals(vista.getIdTipoValor())) {
				dto.setTipoFacturaShiva(TipoCreditoEnum.EFECTIVO);
			} else {
				dto.setTipoFacturaShiva(TipoCreditoEnum.getEnumByIdTipoValor(vista.getIdTipoValor()));		
			}
			//
			MotivoShivaEnum motivo = MotivoShivaEnum.getEnumByDescripcion(vista.getMotivo());
			dto.setMotivo(motivo);
			dto.setMotivoDescripcion(motivo!=null?motivo.descripcion():null);
			//
//			// La logica para completar el siguientes atributo no esta desarrollada todabia. 
//			dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
			//
			dto.setIdValor(vista.getIdValor());
			//
			String operacionAnalista = cobroOnlineServicio.getAnalistaCobroCredito(dto.getIdCreditoPantalla(),vista.getIdCobro());
			dto.setOpeAsocAnalista(operacionAnalista);
			if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
				dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
			} else {
				dto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
			}
			//
			boolean marcaCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(dto.getIdCreditoPantalla());
			if (marcaCobroPendienteProceso) {
				dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
			} else {
				dto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
			}
			boolean marcaDesCobroPendienteProceso = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProceso(dto.getIdCreditoPantalla());
			if (marcaDesCobroPendienteProceso) {
				dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
			} else {
				dto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
			}
			boolean marcaReversionDeCobroEdicion = cobroOnlineServicio.obtenerMarcaReversionDeCobroEdicion(dto);
			if (marcaReversionDeCobroEdicion) {
				dto.setMarcaReversionDeCobroEdicion(SiNoEnum.SI);
			} else {
				dto.setMarcaReversionDeCobroEdicion(SiNoEnum.NO);
			}
			
			
			return dto;
		} catch (ParseException e) {
			Traza.error(CobroCreditoMapeadorShiva.class, e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

}
