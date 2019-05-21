package ar.com.telecom.shiva.presentacion.bean.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.validacion.ITalonarioValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;

public class TalonarioValidacionWeb implements Validator{

	private ITalonarioValidacionServicio talonarioValidacionServicio;
	
	public boolean supports(Class<?> clazz) {
		return TalonarioDto.class.isAssignableFrom(clazz) || TalonarioFiltro.class.isAssignableFrom(clazz) ;
	}
	
	/**
	 * decide que metodo de validacion se ejecuta: DTO o Filtro
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		if(target instanceof TalonarioDto){
			validarTalonarioDto(target,errors);
		}
		if(target instanceof TalonarioFiltro){
			validarTalonarioFiltro(target,errors);
		}
	}
	/**
	 * valida por Filtro
	 * @param target
	 * @param errors
	 * @return
	 */
	private Errors validarTalonarioFiltro(Object target, Errors errors) {
		TalonarioFiltro talonarioFiltro= (TalonarioFiltro) target;
		Boolean validarDesdeHasta = true;
		Boolean busqueda = true;
		try {
			talonarioValidacionServicio.validacionEmpresa(talonarioFiltro.getEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		try {
			talonarioValidacionServicio.validacionSegmento(talonarioFiltro.getSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}

		try {
			talonarioValidacionServicio.validacionNroSerie(talonarioFiltro.getNroSerie(), busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("nroSerie", e.getLeyenda());
			validarDesdeHasta = false;
		}
		
		try {
			talonarioValidacionServicio.validacionRangoDesde(talonarioFiltro.getRangoDesde(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoDesde", e.getLeyenda());
			validarDesdeHasta = false;
		}
		
		try {
			talonarioValidacionServicio.validacionRangoHasta(talonarioFiltro.getRangoHasta(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoHasta", e.getLeyenda());
			validarDesdeHasta = false;
		}
		if (validarDesdeHasta){
			try {
				talonarioValidacionServicio.validacionRangoDesdeHasta(talonarioFiltro.getRangoDesde(), talonarioFiltro.getRangoHasta(), talonarioFiltro.getNroSerie(), busqueda);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("rangoDesde", e.getLeyenda());
			} catch (NegocioExcepcion ex) {
				Traza.error(getClass(), ex.getMessage(), ex);
				errors.rejectValue(Constantes.EXCEPCION, ex.getMessage());
			}
		}
		return errors;
	}

	/**
	 * valida por DTO. Decide que metodo se ejecuta: alta, rechazo o modificacion
	 * @param target
	 * @param errors
	 * @throws NegocioExcepcion 
	 */
	public void validarTalonarioDto(Object target, Errors errors) {
		TalonarioDto talonarioDto=(TalonarioDto) target;
		
		if (talonarioDto.getOperation()!=null) {
			if (Constantes.CREATE.intValue() == talonarioDto.getOperation().intValue()) {
				errors = validarAlta(errors, talonarioDto);
			} else {
				if (Constantes.UPDATE.intValue() == talonarioDto.getOperation().intValue()){
					errors = validarModificacion(errors, talonarioDto);
				} else {
					if (Constantes.RECHAZAR_TALONARIO.intValue() == talonarioDto.getOperation().intValue()){
						errors = validarRechazo(errors, talonarioDto);
					} else {
						if (Constantes.RENDIR_TALONARIO.intValue() == talonarioDto.getOperation().intValue()){
							errors = validarRendicion(errors, talonarioDto);
						} else {
							if (Constantes.RECHAZAR_RENDICION.intValue() == talonarioDto.getOperation().intValue()){
								errors = validarRechazoRendicion(errors, talonarioDto);
							}
						}
					}
				}
			}
		} else {
			errors.rejectValue(Constantes.EXCEPCION, "title.operation.unknown");
		}
	}
	

	/**
	 * Valida el alta de talonarios
	 * @param errors
	 * @param talonario
	 * @return
	 * @throws NegocioExcepcion 
	 */
	private Errors validarAlta(Errors errors, TalonarioDto talonario) {
		Boolean validarDesdeHasta = true;
		Boolean busqueda = false;
		try {
			talonarioValidacionServicio.validacionEmpresa(talonario.getIdEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		
		try {
			talonarioValidacionServicio.validacionNroSerie(talonario.getNroSerie(), busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("nroSerie", e.getLeyenda());
			validarDesdeHasta = false;
		}
		
		try {
			talonarioValidacionServicio.validacionSegmento(talonario.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}
		
		try {
			talonarioValidacionServicio.validacionRangoDesde(talonario.getRangoDesde(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoDesde", e.getLeyenda());
			validarDesdeHasta = false;
		}
		try {
			talonarioValidacionServicio.validacionRangoHasta(talonario.getRangoHasta(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoHasta", e.getLeyenda());
			validarDesdeHasta = false;
		}
		if (validarDesdeHasta){
			try {
				talonarioValidacionServicio.validacionRangoDesdeHasta(talonario.getRangoDesde(), talonario.getRangoHasta(), talonario.getNroSerie(), busqueda);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
			} catch (NegocioExcepcion ex) {
				Traza.error(getClass(), ex.getMessage(), ex);
				errors.rejectValue(Constantes.EXCEPCION, ex.getMessage());
			}
		}
				
		return errors;
	}
	
	private Errors validarModificacion(Errors errors, TalonarioDto talonario){
		Boolean validarDesdeHasta = true;
		Boolean busqueda = false;

		
		try {
			talonarioValidacionServicio.validacionEmpresa(talonario.getIdEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		
		try {
			talonarioValidacionServicio.validacionSegmento(talonario.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}

		try {
			talonarioValidacionServicio.validacionNroSerie(talonario.getNroSerie(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("nroSerie", e.getLeyenda());
			validarDesdeHasta = false;
		}
		
		try {
			talonarioValidacionServicio.validacionRangoDesde(talonario.getRangoDesde(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoDesde", e.getLeyenda());
			validarDesdeHasta = false;
		}
		try {
			talonarioValidacionServicio.validacionRangoHasta(talonario.getRangoHasta(),busqueda);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("rangoHasta", e.getLeyenda());
			validarDesdeHasta = false;
		}
		if (validarDesdeHasta){
			try {
				talonarioValidacionServicio.validacionModificacionRango(talonario);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
			} catch (NegocioExcepcion ex) {
				Traza.error(getClass(), ex.getMessage(), ex);
				errors.rejectValue(Constantes.EXCEPCION, ex.getMessage());
			}
		}
		try {
			talonarioValidacionServicio.validacionModificacion(talonario);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("accion", e.getLeyenda());
			talonario.setDescripcionNingunaModificacion(e.getLeyenda());
			talonario.setErrorNingunaModificacion(true);
		} 
		return errors;
	}
	
	/**
	 * Valida el rechazo de talonarios
	 * @param errors
	 * @param talonarioDto
	 * @return
	 */
	private Errors validarRechazo(Errors errors, TalonarioDto talonarioDto) {
		try {
			talonarioValidacionServicio.validacionIdTalonario(talonarioDto.getIdTalonario());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		try {
			talonarioValidacionServicio.validacionObservaciones(talonarioDto.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		
		return errors;
	}
	
	/**
	 * Valida la rendicion de talonarios
	 * @param errors
	 * @param talonarioDto
	 * @return
	 */
	private Errors validarRendicion(Errors errors, TalonarioDto talonarioDto) {
		try {
			talonarioValidacionServicio.validacionRendicion(talonarioDto);
		} catch (NegocioExcepcion e) {
			errors.rejectValue("accion", e.getMessage());
			talonarioDto.setDescripcionNingunaModificacion(e.getMessage());
			talonarioDto.setErrorNingunaModificacion(true);
		}
		
		return errors;
	}
	
	/**
	 * Valida el rechazo de talonarios
	 * @param errors
	 * @param talonarioDto
	 * @return
	 */
	private Errors validarRechazoRendicion(Errors errors, TalonarioDto talonarioDto) {
		try {
			talonarioValidacionServicio.validacionIdTalonario(talonarioDto.getIdTalonario());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		try {
			talonarioValidacionServicio.validacionObservaciones(talonarioDto.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		
		return errors;
	}
	
	/*******************************
	 *      GETTERS & SETTERS      *
	 *******************************/
	
	public ITalonarioValidacionServicio getTalonarioValidacionServicio() {
		return talonarioValidacionServicio;
	}

	public void setTalonarioValidacionServicio(
			ITalonarioValidacionServicio talonarioValidacionServicio) {
		this.talonarioValidacionServicio = talonarioValidacionServicio;
	}
	
	
}
