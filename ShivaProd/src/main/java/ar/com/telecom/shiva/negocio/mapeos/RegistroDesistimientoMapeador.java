package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaDesistimientoDto;

public class RegistroDesistimientoMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistroDesistimiento modelo = (ShvMasRegistroDesistimiento) vo;
		RegistroOperacionMasivaDesistimientoDto registroDesistimientoDto = new RegistroOperacionMasivaDesistimientoDto();
		
		try {
			registroDesistimientoDto.setClienteDuenoDebito(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoDebito():null);
			registroDesistimientoDto.setNumeroReferenciaFactura(modelo.getNumeroReferenciaFactura());
			registroDesistimientoDto.setImporte(modelo.getImporte());
			registroDesistimientoDto.setDeudaMigrada(modelo.getDeudaMigrada());
			registroDesistimientoDto.setNumeroActaDesistimiento(modelo.getNumeroActaDesistimiento());
			registroDesistimientoDto.setFechaActaDesistimiento(modelo.getFechaActaDesistimiento());
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}		return registroDesistimientoDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaDesistimientoDto registroDesistimientoDto = (RegistroOperacionMasivaDesistimientoDto) dto;
		
		ShvMasRegistroDesistimiento desistimientoModelo = (ShvMasRegistroDesistimiento)
				(vo != null ? vo : new ShvMasRegistroDesistimiento());
		try {
			
			desistimientoModelo.setNumeroReferenciaFactura(registroDesistimientoDto.getNumeroReferenciaFactura());
			desistimientoModelo.setImporte(registroDesistimientoDto.getImporte());
			desistimientoModelo.setDeudaMigrada(registroDesistimientoDto.getDeudaMigrada());
			desistimientoModelo.setNumeroActaDesistimiento(registroDesistimientoDto.getNumeroActaDesistimiento());
			desistimientoModelo.setFechaActaDesistimiento(registroDesistimientoDto.getFechaActaDesistimiento());
			
			ShvMasRegistroClienteSiebel registroClientesModelo = (ShvMasRegistroClienteSiebel)
					(desistimientoModelo.getClientesSiebel() != null ? 
							desistimientoModelo.getClientesSiebel() : new ShvMasRegistroClienteSiebel());
			registroClientesModelo.setClienteDuenoDebito(registroDesistimientoDto.getClienteDuenoDebito());
			registroClientesModelo.setRegistro(desistimientoModelo);
			desistimientoModelo.setClientesSiebel(registroClientesModelo);
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return desistimientoModelo;
	}
	
}
