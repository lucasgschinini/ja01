package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoCheque;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorBoletaDepositoChequeMapeador extends ValorMapeador {

	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired
	private IReciboPreImpresoDao reciboDao ;
	
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;
		
		ShvValBoletaDepositoCheque boletaDepositoChequeModelo = (ShvValBoletaDepositoCheque)
				(vo != null ? vo : new ShvValBoletaDepositoCheque());
		
		try{
			boletaDepositoChequeModelo.setBancoOrigen(bancoDao.buscarBanco(valorDto.getIdBancoOrigen()));
			boletaDepositoChequeModelo.setNumeroCheque(Long.valueOf(valorDto.getNumeroCheque()));

			if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
				boletaDepositoChequeModelo.setFechaRecibo(Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo()));
			}

			if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
				boletaDepositoChequeModelo.setReciboPreImpreso(reciboDao.buscarPorNumeroRecibo(valorDto.getReciboPreImpreso()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
				boletaDepositoChequeModelo.setFechaDeposito(Utilidad.parseDatePickerString(valorDto.getFechaDeposito()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())) {
				boletaDepositoChequeModelo.setFechaEmision(Utilidad.parseDatePickerString(valorDto.getFechaEmision()));
			}
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return boletaDepositoChequeModelo;
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