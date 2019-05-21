package ar.com.telecom.shiva.presentacion.bean.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.negocio.servicios.validacion.IArchivoAvcValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.ArchivoAVCFiltro;

public class ArchivoAvcValidacionWeb implements Validator{

	private IArchivoAvcValidacionServicio archivoAvcValidacionServicio;
	
	public boolean supports(Class<?> clazz) {
		return ArchivoAVCFiltro.class.isAssignableFrom(clazz) ;
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof ArchivoAVCFiltro){
			validarArchivoAvcFiltro(target,errors);
		}
	}
	
	private void validarArchivoAvcFiltro(Object target, Errors errors) {
		ArchivoAVCFiltro archivoFiltro=(ArchivoAVCFiltro) target;
		try {
			archivoAvcValidacionServicio.validacionFechaAltaDesdeHasta(archivoFiltro);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
	}

	public IArchivoAvcValidacionServicio getArchivoAvcValidacionServicio() {
		return archivoAvcValidacionServicio;
	}

	public void setArchivoAvcValidacionServicio(
			IArchivoAvcValidacionServicio archivoAvcValidacionServicio) {
		this.archivoAvcValidacionServicio = archivoAvcValidacionServicio;
	}

}
