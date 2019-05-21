package ar.com.telecom.shiva.negocio.servicios.validacion;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;

public interface ITalonarioValidacionServicio {

	void validacionIdTalonario(String idTalonario) throws ValidacionExcepcion;
	void validacionEmpresa(String empresa) throws ValidacionExcepcion;
	void validacionSegmento(String segmento) throws ValidacionExcepcion;
	void validacionRangoDesde(String rangoDesde, Boolean busqueda) throws ValidacionExcepcion;
	void validacionRangoHasta(String rangoHasta, Boolean busqueda) throws ValidacionExcepcion;
	void validacionRangoDesdeHasta(String rangoDesde, String rangoHasta, String nroSerie, Boolean busqueda) throws NegocioExcepcion;
	void validacionObservaciones(String observaciones) throws ValidacionExcepcion;
	void validacionModificacionRango(TalonarioDto talonario) throws NegocioExcepcion;
	void validacionModificacion(TalonarioDto talonario) throws ValidacionExcepcion;
	void validacionRendicion(TalonarioDto talonario) throws NegocioExcepcion;
	void validacionNroSerie(String nroSerie, Boolean busqueda) throws ValidacionExcepcion;
}
