package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorChequePagoDiferido;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorChequeDiferidoMapeador extends ValorMapeador {

	@Autowired
	private IBancoDao bancoDao;
	@Autowired
	IReciboPreImpresoDao reciboDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;
		
		ShvValValorChequePagoDiferido valorModelo = (ShvValValorChequePagoDiferido)
				(vo != null ? vo : new ShvValValorChequePagoDiferido());
		try{
			valorModelo.setBancoOrigen(bancoDao.buscarBanco(valorDto.getIdBancoOrigen()));
			valorModelo.setNumeroCheque(Long.valueOf(valorDto.getNumeroCheque()));
			valorModelo.setFechaDeposito((Utilidad.parseDatePickerString(valorDto.getFechaDeposito())));
			valorModelo.setFechaVencimiento((Utilidad.parseDatePickerString(valorDto.getFechaVencimiento())));
			
			if (!Validaciones.isNullOrEmpty(valorDto.getNumeroBoleta())) {
				valorModelo.setNumeroBoleta(Long.valueOf(valorDto.getNumeroBoleta()));
			}

			if (!Validaciones.isNullOrEmpty(valorDto.getDocumentacionOriginalRecibida())) {
				valorModelo.setDocumentacionOriginalRecibida(SiNoEnum.getEnumByDescripcion(valorDto.getDocumentacionOriginalRecibida()));
			}
			
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
				valorModelo.setFechaRecibo((Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo())));
			}

			if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
				valorModelo.setReciboPreImpreso(reciboDao.buscarPorNumeroRecibo(valorDto.getReciboPreImpreso()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())) {
				valorModelo.setFechaEmision(Utilidad.parseDatePickerString(valorDto.getFechaEmision()));
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
