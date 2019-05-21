package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

public class MensajeriaTransaccionMapeador extends Mapeador {

	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobTransaccionMensajeriaDetalle modelo = (ShvCobTransaccionMensajeriaDetalle) vo;
		CobMensajeriaTransaccionDto dto = new CobMensajeriaTransaccionDto();
		
		dto.setIdTransaccionMensajeria(modelo.getIdTransaccionMensajeria());
		dto.setIdTransaccion(modelo.getIdTransaccion());
		dto.setIdOperacion(modelo.getIdOperacion());
		
		dto.setFechaAlta(modelo.getFechaCreacion());
		dto.setFechaEnvio(modelo.getFechaEnvio());
		dto.setFechaRecepcion(modelo.getFechaRecepcion());
		
		dto.setServicio(modelo.getServicio());
		dto.setEstado(modelo.getEstado());
		
		dto.setCantReintentos(modelo.getCantReintentos());
		dto.setMensajeEnviado(modelo.getMensajeEnviado());
		dto.setRespuestaRecibida(modelo.getRespuestaRecibida());
		
		return dto;
		
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobMensajeriaTransaccionDto mensajeria = (CobMensajeriaTransaccionDto) dto;
		ShvCobTransaccionMensajeriaDetalle modelo = (ShvCobTransaccionMensajeriaDetalle)
				(vo != null ? vo : new ShvCobTransaccionMensajeriaDetalle());
		
		modelo.setIdTransaccion(mensajeria.getIdTransaccion());
		modelo.setIdOperacion(mensajeria.getIdOperacion());
		
		modelo.setFechaCreacion(mensajeria.getFechaAlta());
		modelo.setFechaEnvio(mensajeria.getFechaEnvio());
		modelo.setFechaRecepcion(mensajeria.getFechaRecepcion());
		
		modelo.setServicio(mensajeria.getServicio());
		modelo.setEstado(mensajeria.getEstado());
		
		modelo.setCantReintentos(mensajeria.getCantReintentos());
		modelo.setMensajeEnviado(mensajeria.getMensajeEnviado());
		modelo.setRespuestaRecibida(mensajeria.getRespuestaRecibida());
		
		return modelo;
	}



}
