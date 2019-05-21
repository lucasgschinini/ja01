package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionRegistroCotizacionSapExcepcion;
import ar.com.telecom.shiva.negocio.bean.RegistroCotizacionSap;
import ar.com.telecom.shiva.negocio.dto.CotizacionDto;

public interface ICotizacionValidacionServicio {

	CotizacionDto validarRegistroCotizacionesSap(RegistroCotizacionSap registroCotizacionesSap) throws ValidacionRegistroCotizacionSapExcepcion;
}
