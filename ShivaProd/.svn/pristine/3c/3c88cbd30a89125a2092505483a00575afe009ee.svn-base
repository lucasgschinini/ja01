package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaGananciasDto;

public class RegistroGananciasMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistroGanancias modelo = (ShvMasRegistroGanancias) vo;
		RegistroOperacionMasivaGananciasDto registroGananciasDto = new RegistroOperacionMasivaGananciasDto();
		
		try {
			registroGananciasDto.setClienteDuenoCredito(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoCredito():null);
			registroGananciasDto.setCuentaOrigen(modelo.getCuentaOrigen());
			registroGananciasDto.setTipoRemanente(modelo.getTipoRemanente());
			registroGananciasDto.setNumeroReferenciaNC(modelo.getNumeroReferenciaNC());
			registroGananciasDto.setImporte(modelo.getImporte());
			registroGananciasDto.setCreditoMigrado(modelo.getCreditoMigrado());
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return registroGananciasDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaGananciasDto registroGananciasDto = (RegistroOperacionMasivaGananciasDto) dto;
		
		ShvMasRegistroGanancias gananciasModelo = (ShvMasRegistroGanancias)
				(vo != null ? vo : new ShvMasRegistroGanancias());
		try {
			
			gananciasModelo.setCuentaOrigen(registroGananciasDto.getCuentaOrigen());
			gananciasModelo.setTipoRemanente(registroGananciasDto.getTipoRemanente());
			gananciasModelo.setNumeroReferenciaNC(registroGananciasDto.getNumeroReferenciaNC());
			gananciasModelo.setImporte(registroGananciasDto.getImporte());
			gananciasModelo.setCreditoMigrado(registroGananciasDto.getCreditoMigrado());
			
			ShvMasRegistroClienteSiebel registroClientesModelo = (ShvMasRegistroClienteSiebel)
					(gananciasModelo.getClientesSiebel() != null ? 
							gananciasModelo.getClientesSiebel() : new ShvMasRegistroClienteSiebel());
			
			registroClientesModelo.setClienteDuenoCredito(registroGananciasDto.getClienteDuenoCredito());
			registroClientesModelo.setRegistro(gananciasModelo);
			gananciasModelo.setClientesSiebel(registroClientesModelo);
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return gananciasModelo;
	}

}