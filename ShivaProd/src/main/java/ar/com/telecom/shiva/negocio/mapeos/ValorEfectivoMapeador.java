package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorEfectivo;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorEfectivoMapeador extends ValorMapeador {

	@Autowired
	IReciboPreImpresoDao reciboDao;
		
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}
	

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		

		ValorDto valorDto = (ValorDto) dto;
		ShvValValorEfectivo valorModelo = (ShvValValorEfectivo) (vo != null ? vo : new ShvValValorEfectivo());

		try {
			valorModelo.setFechaDeposito((Utilidad.parseDatePickerString(valorDto.getFechaDeposito())));

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

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return valorModelo;
	}

}
