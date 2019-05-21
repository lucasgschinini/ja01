package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.io.IOException;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.validacion.IValidadorGenericoDeArchivo;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;

public class ValidadorGenericoDeArchivoImpl implements IValidadorGenericoDeArchivo {
	
	/**
	 * Valida los casos pertinentes para los archivos de Operaciones Masivas.
	 * @param file
	 * @param errors
	 * @param tagError - Representa el tag de error donde va a ir el mensaje.
	 */
	public void validarRestriccionesArchivo(MultipartFile file, Errors errors, String  tagError) {
		if (!Validaciones.isObjectNull(file)) {
			try {

				//Validaciones de archivo.
				if (Validaciones.isNullOrEmpty(file.getOriginalFilename())){
					throw new ValidacionExcepcion(tagError, "error.obligatorio");
				} else {
					if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
						throw new ValidacionExcepcion(tagError, "error.nombreArchivoMuyLargo");					
					} else if (ControlArchivo.isMultipartFileEmpty(file)) {
						throw new ValidacionExcepcion(tagError, "valor.error.archivoVacio");
					} else if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
						throw new ValidacionExcepcion(tagError, "error.archivoProhibido");
					} else if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
						throw new ValidacionExcepcion(tagError, "error.extensionProhibida");
					} else if (ControlArchivo.superaPesoMaximoPermitido(file)) {
						throw new ValidacionExcepcion(tagError, "error.pesoMaximoSuperado");
					}else if (ControlArchivo.archivoTieneCaracteresNoPermitidos(file.getOriginalFilename())) {
						throw new ValidacionExcepcion(tagError, "error.caracteresInvalidos");
						
					}
				}
			} catch (IOException e) {
				Traza.error(getClass(), e.getMessage());			
			} catch (ValidacionExcepcion ve) {
				errors.rejectValue(ve.getCodigoLeyenda(), ve.getLeyenda());
			}
		} else {
			errors.rejectValue(tagError, "error.obligatorio");
		}
	}
	
	public void validarRestriccionesComprobante(OperacionMasivaDto opMasiva, Errors errors) {
		if (Validaciones.isNullOrEmpty(opMasiva.getDescripcionComprobante())) {
			errors.rejectValue("comprobanteDescripcionVacioModif", "error.obligatorio");
		} else {
			if (!Validaciones.esFormatoTexto(opMasiva.getDescripcionComprobante())) {
				errors.rejectValue("comprobanteDescripcionVacioModif", "error.formatoTexto");
			} 
		}
			validarRestriccionesArchivo(opMasiva.getFileComprobanteModificacion(), errors, "comprobanteError");
	}
}
