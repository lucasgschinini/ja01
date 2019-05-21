package ar.com.telecom.shiva.negocio.servicios;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;

public interface IReglaConciliacionServicio {

	List<ReglaConciliacionDto> listarReglasConciliacion(List<ReglaConciliacionDto> reglas) throws NegocioExcepcion;
}
