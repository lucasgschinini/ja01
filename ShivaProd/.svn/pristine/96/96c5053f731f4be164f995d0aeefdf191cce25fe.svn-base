package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroClienteSiebel;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaReintegroDto;

public class RegistroReintegroMapeador extends Mapeador {

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistroReintegro modelo = (ShvMasRegistroReintegro) vo;
		RegistroOperacionMasivaReintegroDto registroReintegroDto = new RegistroOperacionMasivaReintegroDto();
		
		try {
			registroReintegroDto.setClienteDuenoCredito(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoCredito():null);
			registroReintegroDto.setCuenta(modelo.getCuenta());
			registroReintegroDto.setTipoRemanente(modelo.getTipoRemanente());
			registroReintegroDto.setNumeroReferenciaNC(modelo.getNumeroReferenciaNC());
			String numeroDocumento = Utilidad.getReferenciaNumeroDocumento(modelo.getClaseComprobante(),
 					modelo.getSucursalComprobante(), modelo.getNumeroComprobante());
			registroReintegroDto.setNumeroDocumento(numeroDocumento);
			
			registroReintegroDto.setCreditoMigrado(modelo.getCreditoMigrado());
			registroReintegroDto.setImporte(modelo.getImporte());	
			registroReintegroDto.setTramiteReintegro(modelo.getTramiteReintegro());
			registroReintegroDto.setFechaAltaTramiteReintegro(modelo.getFechaAltaTramiteReintegro());
			registroReintegroDto.setTipoReintegro(modelo.getTipoReintegro());
			registroReintegroDto.setClienteDuenoAcuerdoFacturacion(
					modelo.getClientesSiebel()!=null?modelo.getClientesSiebel().getClienteDuenoAcuerdo():null);
			registroReintegroDto.setAcuerdoFacturacionDestino(modelo.getAcuerdoFacturacionDestino());
			registroReintegroDto.setLineaDestino(modelo.getLineaDestino());
			registroReintegroDto.setReintegraConInteres(modelo.getReintegraConInteres());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return registroReintegroDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaReintegroDto registroReintegroDto = (RegistroOperacionMasivaReintegroDto) dto;
		
		ShvMasRegistroReintegro reintegroModelo = (ShvMasRegistroReintegro)
				(vo != null ? vo : new ShvMasRegistroReintegro());
		try {
			reintegroModelo.setCuenta(registroReintegroDto.getCuenta());
			reintegroModelo.setTipoRemanente(registroReintegroDto.getTipoRemanente());
			reintegroModelo.setNumeroReferenciaNC(registroReintegroDto.getNumeroReferenciaNC());
			
			if (!Validaciones.isNullOrEmpty(registroReintegroDto.getNumeroDocumento())) {
				String numeroDocumento = registroReintegroDto.getNumeroDocumento(); 
				if (numeroDocumento.split("-").length == 3) {
					reintegroModelo.setClaseComprobante(
							TipoClaseComprobanteEnum.getEnumByName(numeroDocumento.split("-")[0]));
					reintegroModelo.setSucursalComprobante(numeroDocumento.split("-")[1]);
					reintegroModelo.setNumeroComprobante(numeroDocumento.split("-")[2]);
				} else {
					reintegroModelo.setClaseComprobante(TipoClaseComprobanteEnum.S);
					reintegroModelo.setSucursalComprobante(numeroDocumento.split("-")[0]);
					reintegroModelo.setNumeroComprobante(numeroDocumento.split("-")[1]);
				}
			}
			
			reintegroModelo.setCreditoMigrado(registroReintegroDto.getCreditoMigrado());
			reintegroModelo.setImporte(registroReintegroDto.getImporte());	
			reintegroModelo.setTramiteReintegro(registroReintegroDto.getTramiteReintegro());
			reintegroModelo.setFechaAltaTramiteReintegro(registroReintegroDto.getFechaAltaTramiteReintegro());
			reintegroModelo.setTipoReintegro(registroReintegroDto.getTipoReintegro());
			reintegroModelo.setAcuerdoFacturacionDestino(registroReintegroDto.getAcuerdoFacturacionDestino());
			reintegroModelo.setLineaDestino(registroReintegroDto.getLineaDestino());
			reintegroModelo.setReintegraConInteres(registroReintegroDto.getReintegraConInteres());
			
			ShvMasRegistroClienteSiebel registroClientesModelo = (ShvMasRegistroClienteSiebel)
					(reintegroModelo.getClientesSiebel() != null ? 
							reintegroModelo.getClientesSiebel() : new ShvMasRegistroClienteSiebel());
			
			registroClientesModelo.setClienteDuenoCredito(registroReintegroDto.getClienteDuenoCredito());
			registroClientesModelo.setClienteDuenoAcuerdo(registroReintegroDto.getClienteDuenoAcuerdoFacturacion());
			registroClientesModelo.setRegistro(reintegroModelo);
			reintegroModelo.setClientesSiebel(registroClientesModelo);
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return reintegroModelo;
	}
}