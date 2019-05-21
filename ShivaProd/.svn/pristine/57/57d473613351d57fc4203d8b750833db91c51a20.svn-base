package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.bean.ShvParamCalendarioWrapper;
import ar.com.telecom.shiva.negocio.servicios.IParametroCalendarioServicio;
import ar.com.telecom.shiva.persistencia.dao.IParametroCalendarioDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;

public class ParametroCalendarioServicioImpl implements IParametroCalendarioServicio {
	@Autowired 
	IParametroCalendarioDao parametroCalendarioDao;

	@Override
	public Set<ShvParamCalendarioWrapper> buscaParamtroCalendarioApartirDe(Date fechaDesde) throws NegocioExcepcion {
		try {
			Set<ShvParamCalendarioWrapper> setShvParamCalendarioWrapper = new HashSet<ShvParamCalendarioWrapper>();
			List<ShvParamCalendario> listaShvParamCalendarios = parametroCalendarioDao.buscaParamtroCalendarioApartirDe(fechaDesde);
			// TODO CONSULTA TECNICA!!!!!!!!
			for(ShvParamCalendario shvParamCalendario : listaShvParamCalendarios) {
				setShvParamCalendarioWrapper.add(new ShvParamCalendarioWrapper(shvParamCalendario));
			}
			return setShvParamCalendarioWrapper;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public Date calcularFechaHasta(Date fechaDesde, boolean diasCorridos, int cantidadDias, Set<ShvParamCalendarioWrapper> setShvParamCalendarioWrapper) {
		return Utilidad.calcularDiaHasta(setShvParamCalendarioWrapper, fechaDesde, cantidadDias, diasCorridos);
	}
}
