package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoEfectivo;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorBoletaDepositoEfectivoMapeador extends ValorMapeador {

	@Autowired
	private IReciboPreImpresoDao reciboDao;

	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;

		ShvValBoletaDepositoEfectivo boletaDepositoEfectivoModelo = (ShvValBoletaDepositoEfectivo) (vo != null ? vo
				: new ShvValBoletaDepositoEfectivo());

		try {

			if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
				boletaDepositoEfectivoModelo.setFechaRecibo(Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo()));
			}

			if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
				boletaDepositoEfectivoModelo.setReciboPreImpreso(reciboDao.buscarPorNumeroRecibo(valorDto.getReciboPreImpreso()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
				boletaDepositoEfectivoModelo.setFechaDeposito(Utilidad.parseDatePickerString(valorDto.getFechaDeposito()));
			}

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return boletaDepositoEfectivoModelo;
	}

}