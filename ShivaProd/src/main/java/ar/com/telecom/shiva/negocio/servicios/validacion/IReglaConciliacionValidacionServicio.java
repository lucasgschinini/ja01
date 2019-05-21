package ar.com.telecom.shiva.negocio.servicios.validacion;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;

public interface IReglaConciliacionValidacionServicio {

	boolean validarCantConciliacionTotal(ReglaConciliacionDto reglas) throws NegocioExcepcion;

	boolean validarCantConciliacionParcial(ReglaConciliacionDto reglas) throws NegocioExcepcion;

	boolean validarOrdenConciliacionTotal(ReglaConciliacionDto reglas) throws NegocioExcepcion;

	boolean validarDuplicidadRegla(ReglaConciliacionDto reglas) throws NegocioExcepcion;

}
