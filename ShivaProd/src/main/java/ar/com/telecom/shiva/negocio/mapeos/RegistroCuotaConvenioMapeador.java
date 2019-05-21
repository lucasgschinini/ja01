package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroCuotaConv;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaCuotaConvenioDto;

public class RegistroCuotaConvenioMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistroCuotaConv modelo = (ShvMasRegistroCuotaConv) vo;
		RegistroOperacionMasivaCuotaConvenioDto registroCuotaConvenioDto = new RegistroOperacionMasivaCuotaConvenioDto();
		
		try {
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return registroCuotaConvenioDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaCuotaConvenioDto registroCuotaConvenioDto = (RegistroOperacionMasivaCuotaConvenioDto) dto;
		
		ShvMasRegistroCuotaConv cuotaConvenioModelo = (ShvMasRegistroCuotaConv)
				(vo != null ? vo : new ShvMasRegistroCuotaConv());
		try {
						
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return cuotaConvenioModelo;
	}
}
