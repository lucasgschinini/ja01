package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorTransferencia;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorTransferenciaMapeador extends ValorMapeador {

	@Autowired
	private IBancoDao bancoDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;
		
		ShvValValorTransferencia valorModelo = (ShvValValorTransferencia)
				(vo != null ? vo : new ShvValValorTransferencia());
		try{
			valorModelo.setNumeroReferencia(Long.valueOf(valorDto.getNumeroReferencia()));
			valorModelo.setFechaTransferencia(Utilidad.parseDatePickerString(valorDto.getFechaTransferencia()));
			valorModelo.setBancoOrigen(bancoDao.buscarBanco(valorDto.getIdBancoOrigen()));
			
			valorModelo.setCuit(valorDto.getCuit());
			
			if (!Validaciones.isNullOrEmpty(valorDto.getDocumentacionOriginalRecibida())) {
				valorModelo.setDocumentacionOriginalRecibida(SiNoEnum.getEnumByDescripcion(valorDto.getDocumentacionOriginalRecibida()));
			}

		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return valorModelo;
	}
	
	
	/**************************************************************************************
	 * Getters & Setters 
	 **************************************************************************************/
	
	
	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}
}
