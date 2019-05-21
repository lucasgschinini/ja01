package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaAplicarDeudaDto;

public class RegistroAplicarDeudaMapeador extends Mapeador {
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistroAplicarDeuda modelo = (ShvMasRegistroAplicarDeuda) vo;
		RegistroOperacionMasivaAplicarDeudaDto registroOperacionMasivaAplicarDeudaDto = new RegistroOperacionMasivaAplicarDeudaDto();
		
		try {
			registroOperacionMasivaAplicarDeudaDto.setTipoOperacion(
					modelo.getTipoOperacion()!=null?modelo.getTipoOperacion().getDescripcionCorta():null);
			registroOperacionMasivaAplicarDeudaDto.setClienteDuenoDebito(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoDebito():null);
			registroOperacionMasivaAplicarDeudaDto.setNumeroReferenciaFactura(modelo.getNumeroReferenciaFactura());
			registroOperacionMasivaAplicarDeudaDto.setDestransferirTerceros(modelo.getDestransferirTerceros());
			registroOperacionMasivaAplicarDeudaDto.setDeudaMigrada(modelo.getDeudaMigrada());
			registroOperacionMasivaAplicarDeudaDto.setImporte(modelo.getImporte());
			registroOperacionMasivaAplicarDeudaDto.setClienteDuenoCredito(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoCredito():null);
			registroOperacionMasivaAplicarDeudaDto.setCuenta(modelo.getCuenta());
			registroOperacionMasivaAplicarDeudaDto.setTipoRemanente(modelo.getTipoRemanente());
			registroOperacionMasivaAplicarDeudaDto.setNumeroReferenciaNC(modelo.getNumeroReferenciaNC());
			registroOperacionMasivaAplicarDeudaDto.setCreditoMigrado(modelo.getCreditoMigrado());
			registroOperacionMasivaAplicarDeudaDto.setAccionSobreIntereses(modelo.getAccionSobreIntereses());
			registroOperacionMasivaAplicarDeudaDto.setPorcentajeBonificacionIntereses(modelo.getPorcentajeBonificacionIntereses());
			registroOperacionMasivaAplicarDeudaDto.setImporteBonificacionIntereses(modelo.getImporteBonificacionIntereses());
			registroOperacionMasivaAplicarDeudaDto.setClienteDuenoAcuerdoFacturacion(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoAcuerdo():null);
			registroOperacionMasivaAplicarDeudaDto.setAcuerdoFacturacionDestino(modelo.getAcuerdoFacturacionDestino());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return registroOperacionMasivaAplicarDeudaDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaAplicarDeudaDto registroOperacionMasivaAplicarDeudaDto = (RegistroOperacionMasivaAplicarDeudaDto) dto;
		
		ShvMasRegistroAplicarDeuda registroAplicarDeudaModelo = (ShvMasRegistroAplicarDeuda)
				(vo != null ? vo : new ShvMasRegistroAplicarDeuda());
		try {
			
			registroAplicarDeudaModelo.setTipoOperacion(
					TipoPagoEnum.getEnumByDescripcionCorta(registroOperacionMasivaAplicarDeudaDto.getTipoOperacion()));
			registroAplicarDeudaModelo.setNumeroReferenciaFactura(registroOperacionMasivaAplicarDeudaDto.getNumeroReferenciaFactura());
			registroAplicarDeudaModelo.setDestransferirTerceros(registroOperacionMasivaAplicarDeudaDto.getDestransferirTerceros());
			registroAplicarDeudaModelo.setDeudaMigrada(registroOperacionMasivaAplicarDeudaDto.getDeudaMigrada());
			registroAplicarDeudaModelo.setImporte(registroOperacionMasivaAplicarDeudaDto.getImporte());
			registroAplicarDeudaModelo.setCuenta(registroOperacionMasivaAplicarDeudaDto.getCuenta());
			registroAplicarDeudaModelo.setTipoRemanente(registroOperacionMasivaAplicarDeudaDto.getTipoRemanente());
			registroAplicarDeudaModelo.setNumeroReferenciaNC(registroOperacionMasivaAplicarDeudaDto.getNumeroReferenciaNC());
			registroAplicarDeudaModelo.setCreditoMigrado(registroOperacionMasivaAplicarDeudaDto.getCreditoMigrado());
			registroAplicarDeudaModelo.setAccionSobreIntereses(registroOperacionMasivaAplicarDeudaDto.getAccionSobreIntereses());
			registroAplicarDeudaModelo.setPorcentajeBonificacionIntereses(registroOperacionMasivaAplicarDeudaDto.getPorcentajeBonificacionIntereses());
			registroAplicarDeudaModelo.setImporteBonificacionIntereses(registroOperacionMasivaAplicarDeudaDto.getImporteBonificacionIntereses());
			registroAplicarDeudaModelo.setAcuerdoFacturacionDestino(registroOperacionMasivaAplicarDeudaDto.getAcuerdoFacturacionDestino());
					
			ShvMasRegistroClienteSiebel registroClientesModelo = (ShvMasRegistroClienteSiebel)
					(registroAplicarDeudaModelo.getClientesSiebel() != null ? 
							registroAplicarDeudaModelo.getClientesSiebel() : new ShvMasRegistroClienteSiebel());
			
			registroClientesModelo.setClienteDuenoDebito(registroOperacionMasivaAplicarDeudaDto.getClienteDuenoDebito());
			registroClientesModelo.setClienteDuenoCredito(registroOperacionMasivaAplicarDeudaDto.getClienteDuenoCredito());
			registroClientesModelo.setClienteDuenoAcuerdo(registroOperacionMasivaAplicarDeudaDto.getClienteDuenoAcuerdoFacturacion());
			registroAplicarDeudaModelo.setClientesSiebel(registroClientesModelo);
			registroClientesModelo.setRegistro(registroAplicarDeudaModelo);
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return registroAplicarDeudaModelo;
	}
}
