package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdMedioPagoCliente;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioPagoClienteDto;

public class CobroOnlineMedioPagoClienteMapeador extends Mapeador {
	

	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdMedioPagoCliente modelo = (ShvCobEdMedioPagoCliente) vo;
		
		CobroMedioPagoClienteDto dto = new CobroMedioPagoClienteDto();
		dto.setId(modelo.getIdMedioPagoCliente());
		dto.setIdClienteLegado(modelo.getIdClienteLegado());

		return dto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		return null;
	}

}
