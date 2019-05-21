package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorInterdeposito;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorInterdepositoMapeador extends Mapeador {

	@Autowired
	private IBancoDao bancoDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		
		ShvValValorInterdeposito valorModelo = (ShvValValorInterdeposito)
				(vo != null ? vo : new ShvValValorInterdeposito());
		try{
			valorModelo.setNumeroInterdeposito(Long.valueOf(valorDto.getNumeroInterdepositoCdif()));
			valorModelo.setCodigoOrganismo(valorDto.getCodOrganismo());
		
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
