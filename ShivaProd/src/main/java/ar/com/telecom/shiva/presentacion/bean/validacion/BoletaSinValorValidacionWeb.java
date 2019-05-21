package ar.com.telecom.shiva.presentacion.bean.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.negocio.servicios.validacion.IBoletaSinValorValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public class BoletaSinValorValidacionWeb implements Validator{

	private IBoletaSinValorValidacionServicio boletaSinValorValidacionServicio;
	
	public boolean supports(Class<?> clazz) {
		return BoletaSinValorDto.class.isAssignableFrom(clazz) || BoletaSinValorFiltro.class.isAssignableFrom(clazz) ;
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof BoletaSinValorDto){
			validarBoletaSinValorDto(target,errors);
		}
		if(target instanceof BoletaSinValorFiltro){
			validarBoletaSinValorFiltro(target,errors);
		}
	}
	
	private void validarBoletaSinValorFiltro(Object target, Errors errors) {
		BoletaSinValorFiltro boletaFiltro=(BoletaSinValorFiltro) target;
		boolean desdeHasta = true;
		try {
			boletaSinValorValidacionServicio.validacionEmpresa(boletaFiltro.getEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionSegmento(boletaFiltro.getSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionFechaDesde(boletaFiltro.getFechaDesde());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
			desdeHasta = false;
		}
		
		try {
			boletaSinValorValidacionServicio.validacionFechaHasta(boletaFiltro.getFechaHasta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
			desdeHasta = false;
		}
		
		try {
			if (desdeHasta){
				boletaSinValorValidacionServicio.validacionFechaAltaDesdeHasta(boletaFiltro);
			}
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionImporteDesdeHasta(boletaFiltro);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		
	}

	public void validarBoletaSinValorDto(Object target, Errors errors){
		BoletaSinValorDto boletaDto=(BoletaSinValorDto) target;
		
		if (boletaDto.getOperation()!=null) {
				if (Constantes.CREATE.intValue() == boletaDto.getOperation().intValue()) {
					errors = validarAlta(errors, boletaDto);
				} else {
					if (Constantes.UPDATE.intValue() == boletaDto.getOperation().intValue()) {
						errors = validarModificacion(errors, boletaDto);
					} else {
						if (Constantes.UNSUBSCRIBE.intValue() == boletaDto.getOperation().intValue()) {
							errors = validarBaja(errors, boletaDto);
						}
					}
				}
			} else {
				errors.rejectValue("errorGeneral", "title.operation.unknown");
		}
	}
	
	/**
	 * Valido el alta
	 * @param errors
	 * @param usuario
	 * @return
	 */
	private Errors validarAlta(Errors errors, BoletaSinValorDto boleta) {
		try {
			boletaSinValorValidacionServicio.validacionEmpresa(boleta.getIdEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionSegmento(boleta.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionCodCliente(boleta.getCodCliente());
			boletaSinValorValidacionServicio.validacionCodClienteSiebel(boleta.getCodClienteAgrupador());
			boletaSinValorValidacionServicio.validacionRazonSocial(boleta.getRazonSocialClienteAgrupador());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("codCliente", e.getLeyenda());
		}	
		
		try {
			boletaSinValorValidacionServicio.validacionEmail(boleta.getEmail(), boleta.getCheckEnviarMailBoleta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("email", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionAnalista(boleta.getIdAnalista());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("nombreAnalista", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionCopropietario(boleta.getIdCopropietario());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("copropietario", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionImporte(boleta.getImporte());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("importe", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("acuerdo", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("bancoDeposito", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("nroCuenta", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionOperacionAsociada(boleta.getOperacionAsociada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("operacionAsociada", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionOrigen(boleta.getIdOrigen());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("origen", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionObservaciones(boleta.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionObservacionMail(boleta.getObservacionMail(),boleta.getCheckEnviarMailBoleta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observacionMail", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionCheckRadioSeleccionado(boleta.getCheckImprimirBoleta(), boleta.getCheckEnviarMailBoleta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("checkEnviarMailBoleta", e.getLeyenda());
		}
		
		return errors;
	}
	
	private Errors validarModificacion(Errors errors, BoletaSinValorDto boletaDto) {
		
		try {
			boletaSinValorValidacionServicio.validacionEmail(boletaDto.getEmail(),false);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("email", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionCopropietario(boletaDto.getIdCopropietario());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("copropietario", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionOperacionAsociada(boletaDto.getOperacionAsociada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("operacionAsociada", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionMotivo(boletaDto.getIdMotivo());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("motivo", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionObservaciones(boletaDto.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		
		try {
			boletaSinValorValidacionServicio.validacionObservacionMail(boletaDto.getObservacionMail());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observacionMail", e.getLeyenda());
		}
		
		if (!errors.hasErrors()) {
			try {
				boletaSinValorValidacionServicio.validacionModificacion(boletaDto);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("accion", e.getLeyenda());
				boletaDto.setDescripcionNingunaModificacion(e.getLeyenda());
				boletaDto.setErrorNingunaModificacion(true);
			}
		}
		
		return errors;
	}
	
	private Errors validarBaja(Errors errors, BoletaSinValorDto boletaDto) {
		return null;
	}
	
	public IBoletaSinValorValidacionServicio getBoletaSinValorValidacionServicio() {
		return boletaSinValorValidacionServicio;
	}

	public void setBoletaSinValorValidacionServicio(
			IBoletaSinValorValidacionServicio boletaSinValorValidacionServicio) {
		this.boletaSinValorValidacionServicio = boletaSinValorValidacionServicio;
	}
}
