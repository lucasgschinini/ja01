package ar.com.telecom.shiva.negocio.servicios;

import java.util.Collection;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.TipoMedioPagoDto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;


public interface ITipoMedioPagoServicio extends IServicio {
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<ShvParamTipoMedioPago> listarMediosPago() throws NegocioExcepcion;

	/**
	 * 
	 * @param tipoMedioPago
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamTipoMedioPago buscarMedioPago(TipoMedioPagoEnum tipoMedioPago) throws NegocioExcepcion;
	
	/**
	 * Retorna el TipoMedioPagoDto 
	 * @param id
	 * @return
	 * @throws NegocioExcepcion
	 */
	TipoMedioPagoDto buscar(String id) throws NegocioExcepcion;
	
}
