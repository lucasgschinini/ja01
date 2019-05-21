package ar.com.telecom.shiva.negocio.servicios;

import java.util.Date;
import java.util.Set;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.ShvParamCalendarioWrapper;

public interface IParametroCalendarioServicio {
	public Set<ShvParamCalendarioWrapper> buscaParamtroCalendarioApartirDe(Date fechaDesde) throws NegocioExcepcion;
	public Date calcularFechaHasta(Date fechaDesde, boolean diasCorridos, int cantidadDias, Set<ShvParamCalendarioWrapper> setShvParamCalendarioWrapper);
		
}