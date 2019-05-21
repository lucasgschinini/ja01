package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.CotizacionDto;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamCotizacion;

public class CotizacionMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvParamCotizacion modelo = (ShvParamCotizacion) vo;
		
		CotizacionDto cotizacionDto = new CotizacionDto();
		
		if(!Validaciones.isObjectNull(modelo.getTipoDeCambio())){
			cotizacionDto.setTipoDeCambio(modelo.getTipoDeCambio());
		}
			
		
		if(!Validaciones.isObjectNull(modelo.getFechaValidezDesde())){
			cotizacionDto.setFechaValidezDesde(modelo.getFechaValidezDesde());
		}
		
		if(!Validaciones.isObjectNull(modelo.getIdCotizacion())){
			cotizacionDto.setIdCotizacion(modelo.getIdCotizacion());
		}
		
		if(!Validaciones.isObjectNull(modelo.getMonedaDestino())){
			cotizacionDto.setMonedaDestino(modelo.getMonedaDestino());
		}
		
		if(!Validaciones.isObjectNull(modelo.getMonedaProcedencia())){
			cotizacionDto.setMonedaProcedencia(modelo.getMonedaProcedencia());
		}	
		
		
		if(!Validaciones.isObjectNull(modelo.getTipoCotizacion())){
			cotizacionDto.setTipoCotizacion(modelo.getTipoCotizacion());
		}
		
		return cotizacionDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CotizacionDto cotizacionDto = (CotizacionDto) dto;
		
		ShvParamCotizacion cotizacionModelo = (ShvParamCotizacion)	(vo != null ? vo : new ShvParamCotizacion());
		
		if(!Validaciones.isObjectNull(cotizacionDto.getTipoDeCambio())){
			cotizacionModelo.setTipoDeCambio(cotizacionDto.getTipoDeCambio());
		}
			
		
		if(!Validaciones.isObjectNull(cotizacionDto.getFechaValidezDesde())){
			cotizacionModelo.setFechaValidezDesde(cotizacionDto.getFechaValidezDesde());
		}
		
		if(!Validaciones.isObjectNull(cotizacionDto.getIdCotizacion())){
			cotizacionModelo.setIdCotizacion(cotizacionDto.getIdCotizacion());
		}
		
		if(!Validaciones.isObjectNull(cotizacionDto.getMonedaDestino())){
			cotizacionModelo.setMonedaDestino(cotizacionDto.getMonedaDestino());
		}
		
		if(!Validaciones.isObjectNull(cotizacionDto.getMonedaProcedencia())){
			cotizacionModelo.setMonedaProcedencia(cotizacionDto.getMonedaProcedencia());
		}	
		
		
		if(!Validaciones.isObjectNull(cotizacionDto.getTipoCotizacion())){
			cotizacionModelo.setTipoCotizacion(cotizacionDto.getTipoCotizacion());
		}
		
		return cotizacionModelo;
	}

}
