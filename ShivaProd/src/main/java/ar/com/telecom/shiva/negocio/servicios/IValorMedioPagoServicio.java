package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamValorMedioPago;


public interface IValorMedioPagoServicio extends IServicio {
	
	List<ShvParamValorMedioPago> buscarPorTipoMedioPago(String tipo) throws NegocioExcepcion;

	List<ShvParamValorMedioPago> buscarPorTipoValor(String tipo) throws NegocioExcepcion;
}
