package ar.com.telecom.shiva.negocio.servicios.validacion;

import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;

public interface IValidadorGenericoDeArchivo {

	void validarRestriccionesArchivo(MultipartFile file, Errors errors, String  tagError);
	void validarRestriccionesComprobante(OperacionMasivaDto opMasiva, Errors errors);
}
