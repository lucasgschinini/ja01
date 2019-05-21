package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoChequePagoDiferido;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorBoletaDepositoChequeDiferidoMapeador extends ValorMapeador {

	@Autowired
	private IBancoDao bancoDao;

	@Autowired
	private IReciboPreImpresoDao reciboDao;

	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;

		ShvValBoletaDepositoChequePagoDiferido boletaDepositoChequePagoDiferidoModelo = (ShvValBoletaDepositoChequePagoDiferido) (vo != null ? vo
				: new ShvValBoletaDepositoChequePagoDiferido());

		try {
			boletaDepositoChequePagoDiferidoModelo.setBancoOrigen(bancoDao
					.buscarBanco(valorDto.getIdBancoOrigen()));
			boletaDepositoChequePagoDiferidoModelo.setFechaVencimiento(Utilidad
					.parseDatePickerString(valorDto.getFechaVencimiento()));
			boletaDepositoChequePagoDiferidoModelo.setNumeroCheque(Long
					.valueOf(valorDto.getNumeroCheque()));
		
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
				boletaDepositoChequePagoDiferidoModelo.setFechaRecibo(Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo()));
			}
			
			if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
				boletaDepositoChequePagoDiferidoModelo.setReciboPreImpreso(reciboDao.buscarPorNumeroRecibo(valorDto.getReciboPreImpreso()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
				boletaDepositoChequePagoDiferidoModelo.setFechaDeposito(Utilidad.parseDatePickerString(valorDto.getFechaDeposito()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())) {
				boletaDepositoChequePagoDiferidoModelo.setFechaEmision(Utilidad.parseDatePickerString(valorDto.getFechaEmision()));
			}

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return boletaDepositoChequePagoDiferidoModelo;
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