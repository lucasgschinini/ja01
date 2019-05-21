package ar.com.telecom.shiva.presentacion.bean.validacion;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.validacion.IOperacionMasivaValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IValidadorGenericoDeArchivo;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;

public class OperacionMasivaValidacionWeb implements Validator {

	@Autowired
	IValidadorGenericoDeArchivo validadorGenericoDeArchivo;
	
	@Autowired
	IOperacionMasivaValidacionServicio operacionMasivaValidacionServicio;
	
	public boolean supports(Class<?> clazz) {
		return OperacionMasivaDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof OperacionMasivaDto){
			validarOperacionMasivaDto(target,errors);
		}
	}

	public void validarOperacionMasivaDto(Object target, Errors errors) {
		OperacionMasivaDto dto = (OperacionMasivaDto)target;
		dto.setResultadoValidaciones("");
		
		if (Constantes.SUBIR_ARCHIVO_OPERACION_MASIVA.intValue() == dto.getOperation().intValue()) {
			
			//Validaciones de archivo comunes
			validadorGenericoDeArchivo.validarRestriccionesArchivo(dto.getFileOperacionMasiva(), errors, "pathArchivo");
			
			try {
				operacionMasivaValidacionServicio.validacionEmpresa(dto.getIdEmpresa());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("empresa", e.getLeyenda());
			}
			try {
				operacionMasivaValidacionServicio.validacionSegmento(dto.getIdSegmento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("segmento", e.getLeyenda());
			}
			try {
				operacionMasivaValidacionServicio.validacionMotivo(dto.getIdMotivo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("motivo", e.getLeyenda());
			}
			
			try {
				if (!errors.hasErrors()) {
					
					File file = ControlArchivo.convert(dto.getFileBytes(), dto.getFileOperacionMasiva());
					
					//Validacion de nombre
					if(!Validaciones.esNombreArchivoOperacionMasiva(file.getName())){
						dto.setResultadoValidaciones(Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.formatArchivo"));
					} else {
						boolean validarDuplicidad = true;
						if (TipoArchivoOperacionMasivaEnum.DSIST.name().equalsIgnoreCase(file.getName().split("_")[0])){
							if (!Validaciones.isCollectionNotEmpty(dto.getListaComprobantes())){
								errors.rejectValue("comprobanteError", "error.obligatorio");
								validarDuplicidad = false;
							}
							dto.setMoverSeccionComprobante(true);
						} 
						
						if (validarDuplicidad) {
							try {
								dto = operacionMasivaValidacionServicio.validarDuplicidad(dto);
							} catch (NegocioExcepcion e) {
								Traza.error(getClass(), e.getMessage(), e);
								errors.rejectValue(Constantes.EXCEPCION, e.getMessage());
							}
						}
					}
				}
				
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), e.getMessage(), e);
				errors.rejectValue(Constantes.EXCEPCION, e.getMessage());
			}
		} else if (Constantes.SUBIR_COMPROBANTE.intValue() == dto.getOperation().intValue()) {
			validadorGenericoDeArchivo.validarRestriccionesComprobante(dto, errors);
		}
	
	}
	

	public void validarCamposBanales(OperacionMasivaDto operacionMasiva,BindingResult errors) throws PersistenciaExcepcion,NegocioExcepcion {


		try {
			operacionMasivaValidacionServicio.validacionEmpresa(operacionMasiva.getIdEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("empresa", e.getLeyenda());
		}
		try {
			operacionMasivaValidacionServicio.validacionSegmento(operacionMasiva.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}
		try {
			operacionMasivaValidacionServicio.validacionMotivo(operacionMasiva.getIdMotivo());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("motivo", e.getLeyenda());
		}
		
		
	}
	
	public IValidadorGenericoDeArchivo getValidadorGenericoDeArchivo() {
		return validadorGenericoDeArchivo;
	}

	public void setValidadorGenericoDeArchivo(
			IValidadorGenericoDeArchivo validadorGenericoDeArchivo) {
		this.validadorGenericoDeArchivo = validadorGenericoDeArchivo;
	}

	public IOperacionMasivaValidacionServicio getOperacionMasivaValidacionServicio() {
		return operacionMasivaValidacionServicio;
	}

	public void setOperacionMasivaValidacionServicio(
			IOperacionMasivaValidacionServicio operacionMasivaValidacionServicio) {
		this.operacionMasivaValidacionServicio = operacionMasivaValidacionServicio;
	}
}
