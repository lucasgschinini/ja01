package ar.com.telecom.shiva.presentacion.bean.validacion;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class RegistroAvcValorValidacionWeb implements Validator{

	private IRegistroAVCServicio registroAvcServicio;
	
	public boolean supports(Class<?> clazz) {
		return ValorDto.class.isAssignableFrom(clazz) ;
	}

	public void validate(Object target, Errors errors) {
		if(target instanceof ValorDto){
			validarValorDto(target,errors);
		}
	}

	private void validarValorDto(Object target, Errors errors) {
		ValorDto valorDto=(ValorDto) target;
		try {
			validacionSegmento(valorDto.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("segmento", e.getLeyenda());
		}
		
		try {
			validacionCodCliente(valorDto.getCodCliente());
			validacionCodClienteSiebel(valorDto.getCodClienteAgrupador());
			validacionRazonSocial(valorDto.getRazonSocialClienteAgrupador());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("codCliente", e.getLeyenda());
		}	
		try {
			validacionObservaciones(valorDto.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observaciones", e.getLeyenda());
		}
		try {
			validacionComprobantes(valorDto.getListaComprobantes());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("listaComprobantes", e.getLeyenda());
		}
		
	}
	
	public void validacionSegmento(String segmento) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(segmento)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionCodCliente(String codCliente)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codCliente)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
		if (!Validaciones.isNumeric(codCliente)) {
			throw new ValidacionExcepcion("","error.numerico");
		}
	}

	public void validacionRazonSocial(String razonSocial)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(razonSocial)) {
			throw new ValidacionExcepcion("","boleta.alta.mensaje.siebel.no.validado");
		}
	}
	
	public void validacionCodClienteSiebel(String codClienteSiebel) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codClienteSiebel)) {
			throw new ValidacionExcepcion("","boleta.alta.mensaje.siebel.no.validado");
		}
	}
	
	public void validacionObservaciones(String observaciones) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			if (!Validaciones.esFormatoTexto(observaciones)) {
				throw new ValidacionExcepcion("","error.formatoTexto");
			}
		}
	}
	
	public void validacionComprobantes(List<ComprobanteDto> listaComprobantes) throws ValidacionExcepcion {
		if(!Validaciones.isCollectionNotEmpty(listaComprobantes)) {
			throw new ValidacionExcepcion("","error.conciliacion.validacion.alta.valor.comprobantes");
		}
	}
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	public IRegistroAVCServicio getRegistroAvcServicio() {
		return registroAvcServicio;
	}

	public void setRegistroAvcServicio(IRegistroAVCServicio registroAvcServicio) {
		this.registroAvcServicio = registroAvcServicio;
	}
	

}
