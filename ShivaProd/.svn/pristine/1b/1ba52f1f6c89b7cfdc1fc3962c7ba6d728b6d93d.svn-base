package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.presentacion.bean.dto.LineaRegistroDto;

public class OperacionTruncaMapeador extends Mapeador {

	@Autowired 
	ICobroDao cobroDao;
	
	/**
	 * 
	 */
	public DTO map(Modelo vo) throws NegocioExcepcion {
		try {
			ShvCobDescobro descobro = (ShvCobDescobro) vo;
			ShvCobCobro cobro = cobroDao.buscarCobro(descobro.getIdCobro());
			
			LineaRegistroDto lineaRegistroDto = new LineaRegistroDto(
					Utilidad.rellenarCerosIzquierda(String.valueOf(cobro.getOperacion().getIdOperacion()), 7));
			
			lineaRegistroDto.setDescripcionDetalle(descobro.getWorkflow().getEstado().descripcion());
			
			return lineaRegistroDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}

}
