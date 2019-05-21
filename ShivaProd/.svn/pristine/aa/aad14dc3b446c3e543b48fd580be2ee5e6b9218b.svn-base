package ar.com.telecom.shiva.base.jms.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicDescobroEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleGeneracionCargos;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPago;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleFacturaDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicDetalleMedioPagoDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

public class JmsServicio {

	@Autowired 
	MapeadorJMS micRespuestaRecepcionMapeoJMS;
	
	@Autowired 
	MapeadorJMS micADCMapeoJMS;
	
	@Autowired 
	MapeadorJMS micRespuestaADCMapeoJMS;
	
	@Autowired 
	MapeadorJMS micGeneracionCargoMapeoJMS;
	
	@Autowired 
	MapeadorJMS micRespuestaGeneracionCargoMapeoJMS;
	
	@Autowired 
	MapeadorJMS micDescobroMapeoJMS;
	
	@Autowired 
	MapeadorJMS micRespuestaDescobroMapeoJMS;
	
	/**
	 * Mapeo el dto de ADC al JMS
	 * @param dto
	 * @return
	 */
	public MicApropiacionDesapropiacionConfirmacionEntrada mapeadorImputacionDtoJms(MicTransaccionADCDto dto){
		
		MicApropiacionDesapropiacionConfirmacionEntrada jms = new MicApropiacionDesapropiacionConfirmacionEntrada();
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setIdTransaccion(dto.getIdTransaccion()!=null?Long.valueOf(dto.getIdTransaccion().toString()):null);
		
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		
		MicDetalleFacturaDto detalleDto = dto.getDetalleFactura();
		if(!Validaciones.isObjectNull(detalleDto)){
			MicDetalleFactura detalle = new MicDetalleFactura();
			detalle.setTipoDocumento(detalleDto.getTipoDocumento());
			detalle.setTipoOperacion(detalleDto.getTipoOperacion());
			detalle.setReferenciaFactura(detalleDto.getReferenciaFactura());
			detalle.setFechaValor(detalleDto.getFechaValor());
			detalle.setAcuerdoFacturacionIntereses(detalleDto.getAcuerdoFacturacionIntereses());
			detalle.setImporte(detalleDto.getImporte());
			detalle.setQueHacerConLosIntereses(detalleDto.getQueHacerConLosIntereses());
			detalle.setImporteBonificacionIntereses(detalleDto.getImporteBonificacionIntereses());
			detalle.setQueHacerConLosTerceros(detalleDto.getQueHacerConLosTerceros());
			detalle.setMontoAcumuladoSimulacion(detalleDto.getMontoAcumuladoSimulacion());
			detalle.setFechaCobranzaAcumuladaSimulacion(Utilidad.formatDateAAAAMMDD(detalleDto.getFechaCobranzaAcumuladaSimulacion()));
			jms.setDetalleFactura(detalle);
		}
		
		List<MicDetalleMedioPagoDto> listaMediosPago = dto.getListaMediosPago();
		if (Validaciones.isCollectionNotEmpty(listaMediosPago)) {
			for (MicDetalleMedioPagoDto mpDto: listaMediosPago) {
				MicDetalleMedioPago mp = new MicDetalleMedioPago();
				mp.setTipoMedioPago(mpDto.getTipoMedioPago());
				mp.setImporte(mpDto.getImporteMedioPago());
				mp.setCuentaRemanente(mpDto.getCuentaRemanente());
				mp.setTipoRemanente(mpDto.getTipoRemanente());
				mp.setNumeroNC(mpDto.getNumeroNC());
				mp.setMontoAcumuladoSimulacion(mpDto.getMontoAcumuladoSimulacion());
				jms.getListaMediosPago().add(mp);
			}
		}
		
		return jms;
	}
	
	/**
	 * Mapeo el dto de cargo al JMS
	 * @param dto
	 * @return
	 */
	public MicGeneracionCargoEntrada mapeadorCargoDtoJms(MicTransaccionGeneracionCargosDto dto){
		
		MicGeneracionCargoEntrada jms = new MicGeneracionCargoEntrada();
		jms.setIdOperacion(dto.getIdOperacion().intValue());
		if(!Validaciones.isObjectNull(dto.getIdOperacionDescobroMensajeria())){
			jms.setIdOperacionDescobroMensajeria(dto.getIdOperacionDescobroMensajeria().intValue());
		}
		jms.setIdTransaccion(Long.valueOf(dto.getIdTransaccion().toString()));
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		jms.setTipoInvocacion(dto.getTipoInvocacion());
		jms.setTipoMensaje(dto.getServicio());
		
		MicDetalleGeneracionCargosDto detalleDto = dto.getDetalleGeneracionCargos();
		if(!Validaciones.isObjectNull(detalleDto)){
			MicDetalleGeneracionCargos detalle = new MicDetalleGeneracionCargos();
			detalle.setAcuerdoFacturacion(detalleDto.getAcuerdoFacturacion());
			detalle.setImporteNoRegulado(detalleDto.getImporteNoRegulado());
			detalle.setImporteRegulado(detalleDto.getImporteRegulado());
			detalle.setFechaDesde(detalleDto.getFechaDesde());
			detalle.setFechaHasta(detalleDto.getFechaHasta());
			detalle.setCalcularFechaHasta(detalleDto.getCalcularFechaHasta());
			detalle.setQueHacerConLosIntereses(detalleDto.getQueHacerConLosIntereses());
			detalle.setImporteBonificacionInteresesNoRegulado(detalleDto.getImporteBonificacionInteresesNoRegulado());
			detalle.setImporteBonificacionInteresesRegulado(detalleDto.getImporteBonificacionInteresesRegulado());
			detalle.setTipoCliente(detalleDto.getTipoCliente());
			detalle.setTipoIva(detalleDto.getTipoIva());
			detalle.setOrigenCargo(detalleDto.getOrigenCargo());
			detalle.setLeyendaFacturaCargo(detalleDto.getLeyendaFacturaCargo());
			detalle.setLeyendaFacturaInteres(detalleDto.getLeyendaFacturaInteres());
			jms.setDetalleGeneracionCargos(detalle);
		}		
		
		return jms;
	}
	
	/**
	 * Mapeo el dto de Descobro de operaciones al JMS
	 * @param dto
	 * @return
	 */
	public MicDescobroEntrada mapeadorDescobroDtoJms(MicTransaccionDescobroDto dto){
		
		MicDescobroEntrada jms = new MicDescobroEntrada();
		jms.setIdOperacion(dto.getIdOperacion());
		jms.setIdOperacionDescobroMensajeria(dto.getIdOperacionDescobroMensajeria());
		jms.setIdSecuencia(dto.getNumeroTransaccion());
		jms.setIdTransaccion(Long.valueOf(dto.getIdTransaccion().toString()));
		
		jms.setModoEjecucion(dto.getModoEjecucion()); 
		jms.setUsuarioCobrador(dto.getUsuarioCobrador());
		jms.setFacturarContracargoFactura(dto.getFacturarContracargoFactura());
		jms.setAcuerdoFacturacionContracargoFactura(dto.getAcuerdoFacturacionContracargoFactura());
		jms.setFacturarContracargoCargo(dto.getFacturarContracargoCargo());
		jms.setAcuerdoFacturacionContracargoCargo(dto.getAcuerdoFacturacionContracargoCargo());
		
		return jms;
	}

	public MapeadorJMS getMicRespuestaRecepcionMapeoJMS() {
		return micRespuestaRecepcionMapeoJMS;
	}

	public void setMicRespuestaRecepcionMapeoJMS(
			MapeadorJMS micRespuestaRecepcionMapeoJMS) {
		this.micRespuestaRecepcionMapeoJMS = micRespuestaRecepcionMapeoJMS;
	}

	public MapeadorJMS getMicADCMapeoJMS() {
		return micADCMapeoJMS;
	}

	public void setMicADCMapeoJMS(MapeadorJMS micADCMapeoJMS) {
		this.micADCMapeoJMS = micADCMapeoJMS;
	}

	public MapeadorJMS getMicGeneracionCargoMapeoJMS() {
		return micGeneracionCargoMapeoJMS;
	}

	public void setMicGeneracionCargoMapeoJMS(MapeadorJMS micGeneracionCargoMapeoJMS) {
		this.micGeneracionCargoMapeoJMS = micGeneracionCargoMapeoJMS;
	}

	public MapeadorJMS getMicRespuestaADCMapeoJMS() {
		return micRespuestaADCMapeoJMS;
	}

	public void setMicRespuestaADCMapeoJMS(MapeadorJMS micRespuestaADCMapeoJMS) {
		this.micRespuestaADCMapeoJMS = micRespuestaADCMapeoJMS;
	}

	public MapeadorJMS getMicRespuestaGeneracionCargoMapeoJMS() {
		return micRespuestaGeneracionCargoMapeoJMS;
	}

	public void setMicRespuestaGeneracionCargoMapeoJMS(
			MapeadorJMS micRespuestaGeneracionCargoMapeoJMS) {
		this.micRespuestaGeneracionCargoMapeoJMS = micRespuestaGeneracionCargoMapeoJMS;
	}

	public MapeadorJMS getMicDescobroMapeoJMS() {
		return micDescobroMapeoJMS;
	}

	public void setMicDescobroMapeoJMS(MapeadorJMS micDescobroMapeoJMS) {
		this.micDescobroMapeoJMS = micDescobroMapeoJMS;
	}

	public MapeadorJMS getMicRespuestaDescobroMapeoJMS() {
		return micRespuestaDescobroMapeoJMS;
	}

	public void setMicRespuestaDescobroMapeoJMS(
			MapeadorJMS micRespuestaDescobroMapeoJMS) {
		this.micRespuestaDescobroMapeoJMS = micRespuestaDescobroMapeoJMS;
	}
	
}