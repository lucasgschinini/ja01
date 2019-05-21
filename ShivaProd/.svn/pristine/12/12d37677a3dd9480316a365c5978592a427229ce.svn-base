package ar.com.telecom.shiva.presentacion.bean.validacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.impl.RegistroAVCServicioImpl;
import ar.com.telecom.shiva.negocio.servicios.validacion.impl.ValidadorGenericoDeArchivoImpl;

public class RegistroAvcValidacionWeb implements Validator{

	@Autowired
	private IRegistroAVCServicio registroAvcServicio;
	
	@Autowired
	private ValidadorGenericoDeArchivoImpl validadorGenericoDeArchivo;
	
	public boolean supports(Class<?> clazz) {
		return RegistroAVCDto.class.isAssignableFrom(clazz) ;
	}

	public void validate(Object target, Errors errors) {
		RegistroAVCDto registroAvc = (RegistroAVCDto)target;
	
		if (Constantes.ANULAR_REGISTRO_AVC.equals(registroAvc.getOperation())) {
			validarAnulacionRegistroAvc(target, errors);
		} else if (Constantes.CONFIRMAR_ANULAR_REGISTRO_AVC.equals(registroAvc.getOperation())) {
			validarConfirmacionAnulacionRegistroAvc(target, errors);
		} else if (Constantes.RECHAZAR_ANULAR_REGISTRO_AVC.equals(registroAvc.getOperation())) {
			validarRechazarAnulacionRegistroAvc(target, errors);
		} else if (Constantes.EDITAR_TRANSFERENCIA.equals(registroAvc.getOperation())) {
			validarEdicionTransferenciaDto(target, errors);
		} else if (Constantes.SUBIR_COMPROBANTE.equals(registroAvc.getOperation())){
			validarComprobante(target, errors);
		} else if (Constantes.BUSCAR_REGISTRO_AVC.equals(registroAvc.getOperation())){
			validarFechaAltaDesdeHasta(target, errors);
			validarFechaDerivacionDesdeHasta(target, errors);
			validarNumeroBoleta(registroAvc.getNroBoletaFiltro(), errors);
			validarNumeroReferencia(registroAvc.getReferenciaDelValorFiltro(), errors);
			validarArchivoAvcFiltroImporte(target,errors);
		} else {
		
			switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(registroAvc.getTipoValor()))) {
		    case BOLETA_DEPOSITO_CHEQUE:
		    	validarDepositoDto(target,errors);
		    	break;
		    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
		    	validarDepositoDto(target,errors);
		    	break;
		    case BOLETA_DEPOSITO_EFECTIVO:
		    	validarDepositoDto(target,errors);
		    	break;
		    case TRANSFERENCIA:
		    	validarTransferenciaDto(target,errors);
		    	break;
		    case INTERDEPÓSITO:
		    	validarInterdepositoDto(target,errors);
		    	break;
		    	default:
			};
		}
	}
	
	private void validarArchivoAvcFiltroImporte(Object target, Errors errors) {
		RegistroAVCDto registroDto= (RegistroAVCDto) target;

		try {
			validacionImporteDesdeHastaFormato(registroDto.getImporteDesde());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("importeDesde", e.getLeyenda());
		}

		try {
			validacionImporteDesdeHastaFormato(registroDto.getImporteHasta());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("importeHasta", e.getLeyenda());
		}
		
		try {
			validacionImporteDesdeHasta(registroDto.getImporteDesde(), registroDto.getImporteHasta() );
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("importeDesde", e.getLeyenda());
		}
	}
	
	public void validacionImporteDesdeHastaFormato(String importe)
			throws ValidacionExcepcion {
		
		if (!Validaciones.isNullOrEmpty(importe)){
			if (!Validaciones.isImporteFormato(importe, 2)) {
				throw new ValidacionExcepcion("", "error.numeroConDosDigitos");
			}
		}
	}
	
	public void validacionImporteDesdeHasta(String importeDesde, String importeHasta) throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(importeDesde)
				&& !Validaciones.isNullOrEmpty(importeHasta)) {

			if (Validaciones.isImporteFormato(importeDesde, 2)	&& Validaciones.isImporteFormato(importeHasta, 2)) {
			//if (Validaciones.isImporteFormatoValor(importeDesde) && Validaciones.isImporteFormatoValor(importeHasta)) {
				if (!Validaciones.validacionImporteMayorDesdeHasta(importeDesde, importeHasta)) {
					throw new ValidacionExcepcion("importeDesde",
							"error.importeDesdeHastaIncorrecto");
				}
			}
		}
	}
	
	private void validarFechaDerivacionDesdeHasta(Object target, Errors errors) {
		RegistroAVCDto registroDto=(RegistroAVCDto) target;
		try {
			validacionFechaDesde(registroDto.getFechaDerivacionDesdeFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaDerivacionDesdeFormateada", e.getLeyenda());
		}
		try {
			validacionFechaHasta(registroDto.getFechaDerivacionHastaFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaDerivacionHastaFormateada", e.getLeyenda());
		}
		
		try {
		validacionFechaDesdeHasta(registroDto.getFechaDerivacionDesdeFormateada(), registroDto.getFechaDerivacionHastaFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaDerivacionDesdeFormateada", e.getLeyenda());
		}
	}
	
	public void validacionFechaDesde(String boletaFiltro)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(boletaFiltro)) {
			if (!Validaciones.validarFecha(boletaFiltro)) {
				throw new ValidacionExcepcion("fechaDesde",
						"error.fechaIncorrecta");
			}

		}

	}

	public void validacionFechaHasta(String boletaFiltro)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(boletaFiltro)) {

			if (!Validaciones.validarFecha(boletaFiltro)) {
				throw new ValidacionExcepcion("fechaHasta",
						"error.fechaIncorrecta");
			}
		}
	}

	public void validacionFechaDesdeHasta(String fechaDesde, String FechaHasta)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(fechaDesde)
				&& !Validaciones.isNullOrEmpty(FechaHasta)) {

			if (Validaciones.validarFecha(fechaDesde)
					&& Validaciones.validarFecha(FechaHasta)) {

				if (!Validaciones
						.validarFechaDesdeHasta(fechaDesde, FechaHasta)) {
					throw new ValidacionExcepcion("fechaHasta",
							"error.fechaDesdeHastaIncorrecto");
				}
			}
		}
	}
	
	private void validarFechaAltaDesdeHasta(Object target, Errors errors) {
		RegistroAVCDto registroDto=(RegistroAVCDto) target;
		try {
			validacionFechaDesde(registroDto.getFechaAltaDesdeFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaAltaDesdeFormateada", e.getLeyenda());
		}
		try {
			validacionFechaHasta(registroDto.getFechaAltaHastaFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaAltaHastaFormateada", e.getLeyenda());
		}
		
		try {
		validacionFechaDesdeHasta(registroDto.getFechaAltaDesdeFormateada(), registroDto.getFechaAltaHastaFormateada());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("fechaAltaDesdeFormateada", e.getLeyenda());
		}
	}

	public void validarTransferenciaDto(Object target, Errors errors) {
		TransferenciaDto registroDto=(TransferenciaDto) target;
		
		if (registroDto.getOperation()!=null) {
//			if (Constantes.CREATE.intValue() == registroDto.getOperation().intValue()) {
//				errors = validarTransferenciaAltaDto(registroDto,errors);
//			} else {
				if (Constantes.UPDATE.intValue() == registroDto.getOperation().intValue()){
					errors = validarConfirmacionAnulacion(registroDto,errors);
				}
//			}
		} else {
			errors.rejectValue(Constantes.EXCEPCION, "title.operation.unknown");
		}
	}
	
	public void validarInterdepositoDto(Object target, Errors errors) {
		RegistroAVCDto registroDto=(RegistroAVCDto) target;
		
		if (registroDto.getOperation()!=null) {
//			if (Constantes.CREATE.intValue() == registroDto.getOperation().intValue()) {
//				errors = validarTransferenciaAltaDto(registroDto,errors);
//			} else {
				if (Constantes.UPDATE.intValue() == registroDto.getOperation().intValue()){
					errors = validarConfirmacionAnulacion(registroDto,errors);
				}
//			}
		} else {
			errors.rejectValue(Constantes.EXCEPCION, "title.operation.unknown");
		}
	}
	
	public void validarDepositoDto(Object target, Errors errors) {
		RegistroAVCDto registroDto=(RegistroAVCDto) target;
		
		if (registroDto.getOperation()!=null) {
//			if (Constantes.CREATE.intValue() == registroDto.getOperation().intValue()) {
//				errors = validarTransferenciaAltaDto(registroDto,errors);
//			} else {
				if (Constantes.UPDATE.intValue() == registroDto.getOperation().intValue()){
					errors = validarConfirmacionAnulacion(registroDto,errors);
				}
//			}
		} else {
			errors.rejectValue(Constantes.EXCEPCION, "title.operation.unknown");
		}
	}
	
	private Errors validarConfirmacionAnulacion(Object target, Errors errors) {
		RegistroAVCDto registroDto = (RegistroAVCDto) target;
		try {
			if (!Validaciones.isNullEmptyOrDash(registroDto.getObservacionConfirmarAnulacion())) {
				if (!Validaciones.esFormatoTexto(registroDto.getObservacionConfirmarAnulacion())) {
						throw new ValidacionExcepcion("observacionConfirmarAnulacion", "error.formatoTexto");
				}
			} else {
				throw new ValidacionExcepcion("observacionConfirmarAnulacion", "error.obligatorio");
			}

		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		return errors;
	}
	
	/**
	 * Metodo exclusivo para validar campo observaciones anular un registro AVC
	 * @param target
	 * @param errors
	 * @return
	 */
	private Errors validarAnulacionRegistroAvc(Object target, Errors errors) {
		RegistroAVCDto registroDto = (RegistroAVCDto) target;
		try {
			if (Validaciones.isNullOrEmpty(registroDto.getObservacionAnulacion())) {
				throw new ValidacionExcepcion("observacionAnulacion", "error.obligatorio");
			}
			if (!Validaciones.esFormatoTexto(registroDto.getObservacionAnulacion())) {
				throw new ValidacionExcepcion("observacionAnulacion", "error.formatoTexto");
			}
			if (Validaciones.isNullOrEmpty(registroDto.getRegistrosAVCAAnularSelected())) {
				throw new ValidacionExcepcion("registrosAVCAAnularSelected", "conciliacion.mensaje.error.validacion.registros");
			}
			
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		return errors;
	}
	
	/**
	 * Metodo exclusivo para validar campo observaciones al confirmar la anulacion de un registro AVC
	 * @param target
	 * @param errors
	 * @return
	 */
	private Errors validarConfirmacionAnulacionRegistroAvc(Object target, Errors errors) {
		RegistroAVCDto registroDto = (RegistroAVCDto) target;
		try {
			if (!Validaciones.esFormatoTexto(registroDto.getObservacionConfirmarAnulacion())) {
				throw new ValidacionExcepcion("observacionConfirmarAnulacion", "error.formatoTexto");
			}

		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		return errors;
	}
	
	/**
	 * Metodo exclusivo para validar campo observaciones al rechazar la anulacion de un registro AVC
	 * @param target
	 * @param errors
	 * @return
	 */
	private Errors validarRechazarAnulacionRegistroAvc(Object target, Errors errors) {
		RegistroAVCDto registroDto = (RegistroAVCDto) target;
		try {
			if (!Validaciones.isNullEmptyOrDash(registroDto.getObservacionConfirmarAnulacion())) {
				if (!Validaciones.esFormatoTexto(registroDto.getObservacionConfirmarAnulacion())) {
						throw new ValidacionExcepcion("observacionConfirmarAnulacion", "error.formatoTexto");
				}
			} else {
				throw new ValidacionExcepcion("observacionConfirmarAnulacion", "error.obligatorio");
			}

		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		return errors;
	}
	
	private Errors validarEdicionTransferenciaDto(Object target, Errors errors) {
		TransferenciaDto transferenciaDto=(TransferenciaDto) target;
		try {
			if(!Utilidad.EMPTY_STRING.equals(transferenciaDto.getCuit().trim())){
				
				String cuit = transferenciaDto.getCuit().trim();
				//U562163 - Fusion - Paquete 01
//				cuit = cuit.replace("-", "");
				if(Validaciones.validarCuit(cuit)){
					if(!Validaciones.isCollectionNotEmpty(transferenciaDto.getListaComprobantes())){
						throw new ValidacionExcepcion("comprobanteError", "error.conciliacion.validacion.transf.obligatorio.comprobante");
					}
				}else{
					throw new ValidacionExcepcion("cuit","error.conciliacion.validacion.transferencia.cuit");
				}
			}
			
			if(transferenciaDto.getNuevaObservacion() != null  && !Utilidad.EMPTY_STRING.equals(transferenciaDto.getNuevaObservacion().trim())){
				if(!Validaciones.esFormatoTexto(transferenciaDto.getNuevaObservacion())){
					throw new ValidacionExcepcion("observacion", "error.conciliacion.validacion.transferencia.observacion");	
				}
			}
			
			if(Validaciones.isCollectionNotEmpty(transferenciaDto.getListaComprobantes())
					&& Utilidad.EMPTY_STRING.equals(transferenciaDto.getCuit().trim())){
				throw new ValidacionExcepcion("cuit", "error.conciliacion.validacion.transf.obligatorio.cuit");
			}
			
			if (!errors.hasErrors()) {
				try {
					registroAvcServicio.validacionModificacionTransferencia(transferenciaDto);
				} catch (ValidacionExcepcion e) {
					Traza.error(RegistroAVCServicioImpl.class, e.getMessage(), e);
//					if(!Validaciones.isNullOrEmpty(e.getLeyenda())){
						//Si la leyenda no es null fue por validacion
						errors.rejectValue("accion", e.getLeyenda());
						transferenciaDto.setDescripcionNingunaModificacion(e.getLeyenda());
						transferenciaDto.setErrorNingunaModificacion(true);
//					}else{
//						//Se disparo la ValidacionExcepcion por una excepcion
//					}
				} catch (NegocioExcepcion e) {
					Traza.error(getClass(), "Se ha producido un error de aplicacion en la validacion de una transferencia", e);
					errors.rejectValue(Constantes.EXCEPCION, "Se ha producido un error de aplicacion en la validacion de una transferencia");
				}
			}
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
		return errors;
	}
	
	private void validarComprobante(Object target, Errors errors) {
		TransferenciaDto transferencia = (TransferenciaDto) target;
		if (Validaciones.isNullOrEmpty(transferencia.getDescripcionComprobante())) {
			errors.rejectValue("comprobanteDescripcionVacioModif", "error.obligatorio");
		} else {
			if (!Validaciones.esFormatoTexto(transferencia.getDescripcionComprobante())) {
				errors.rejectValue("comprobanteDescripcionVacioModif", "error.formatoTexto");
			} else {
				validadorGenericoDeArchivo.validarRestriccionesArchivo(transferencia.getFileComprobanteModificacion(), errors, "comprobanteError");
			}
		}
	}

	public void validarNumeroBoleta(String numero, Errors errors){
		try{
			if(!Validaciones.isNullOrEmpty(numero)){
				if (!Validaciones.isNumeric(numero)) {
					throw new ValidacionExcepcion("nroBoletaFiltro", "error.numerico");
				}
			}
			if(!Validaciones.isNullOrEmpty(numero)){
				if (numero.length()>10) {
					throw new ValidacionExcepcion("nroBoletaFiltro", "error.tamañoMayorADiez");
				}
			}
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
		}
	}
	
	public void validarNumeroReferencia(String numero, Errors errors){
		try{
			if(!Validaciones.isNullOrEmpty(numero)){
				if (!Validaciones.isNumeric(numero)) {
					throw new ValidacionExcepcion("referenciaDelValorFiltro", "error.numerico");
				}
			}
		} catch (ValidacionExcepcion e) {
			errors.rejectValue(e.getCodigoLeyenda(), e.getLeyenda());
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

	public ValidadorGenericoDeArchivoImpl getValidadorGenericoDeArchivo() {
		return validadorGenericoDeArchivo;
	}

	public void setValidadorGenericoDeArchivo(
			ValidadorGenericoDeArchivoImpl validadorGenericoDeArchivo) {
		this.validadorGenericoDeArchivo = validadorGenericoDeArchivo;
	}

}
