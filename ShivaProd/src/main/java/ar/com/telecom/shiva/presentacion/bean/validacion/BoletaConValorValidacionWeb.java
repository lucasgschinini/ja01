package ar.com.telecom.shiva.presentacion.bean.validacion;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IBoletaConValorValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.impl.ValidadorGenericoDeArchivoImpl;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;

//@Component
public class BoletaConValorValidacionWeb implements Validator {

	@Autowired
	private IBoletaConValorValidacionServicio boletaConValorValidacionServicio;
	
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired
	private IValorPorReversionServicio valorPorReversionServicio;
	
	@Autowired
	private ValidadorGenericoDeArchivoImpl validadorGenericoDeArchivo;

	public boolean supports(Class<?> clazz) {
		return ValorDto.class.isAssignableFrom(clazz)
				|| BoletaConValorFiltro.class.isAssignableFrom(clazz)
				|| ValoresDto.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		if (target instanceof ValorDto) {
			validarValorDto(target, errors);
		}
		if (target instanceof BoletaConValorFiltro) {
			validarBoletaConValorFiltro(target, errors);
		}
		if (target instanceof ValoresDto) {
			validarValoresDto(target, errors);
		}

	}

	private void validarBoletaConValorFiltro(Object target, Errors errors) {
		BoletaConValorFiltro boletaFiltro = (BoletaConValorFiltro) target;

		if(!Validaciones.isCollectionNotEmpty(boletaFiltro.getListaCamposAValidar())){
			try {
				boletaConValorValidacionServicio.validacionEmpresa(boletaFiltro.getEmpresa());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("empresa", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionSegmento(boletaFiltro
						.getSegmento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("segmento", e.getLeyenda());
			}
			
			try {			
				boletaConValorValidacionServicio.validacionCodClienteEsNumerico(boletaFiltro.getCodCliente());
				boletaConValorValidacionServicio.validacionCodClienteSiebelNoValidado(boletaFiltro.getCodCliente(),boletaFiltro.getCodClienteAgrupador());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("codCliente", e.getLeyenda());
			}
					
	//		-- Sprint 3, Req. 11, Validacion Filtros
			try {
				boletaConValorValidacionServicio.validarNumeroBoletaFiltro(boletaFiltro.getNroBoletaFiltro());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("nroBoletaFiltro", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validarReferenciaValorFiltro(boletaFiltro.getReferenciaDelValorFiltro());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("referenciaDelValorFiltro", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validarIdValorFiltro(boletaFiltro.getIdValorFiltro());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("idValorFiltro", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validarIdCobroShivaFiltro(boletaFiltro.getIdCobroShivaFiltro());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("idCobroShivaFiltro", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionFechaDesde(boletaFiltro.getFechaDesde());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaDesde", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio
						.validacionFechaHasta(boletaFiltro.getFechaHasta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaHasta", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio
						.validacionFechaDesdeHasta(boletaFiltro.getFechaDesde(), boletaFiltro.getFechaHasta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaDesde", e.getLeyenda());
			}		
			
			try {
				boletaConValorValidacionServicio.validacionImporteDesdeHastaFormato(boletaFiltro.getImporteDesde());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("importeDesde", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionImporteDesdeHastaFormato(boletaFiltro.getImporteHasta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("importeHasta", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionImporteDesdeHasta(boletaFiltro.getImporteDesde(), boletaFiltro.getImporteHasta() );
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("importeDesde", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionObservaciones(boletaFiltro.getObservaciones());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("observaciones", e.getLeyenda());
			}
		}else{
			try {
				boletaConValorValidacionServicio.validacionObservaciones(boletaFiltro.getObservacionesAEnviar());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("observacionesAEnviar", e.getLeyenda());
			}
		}
		
	}

	public void validarValoresDto(Object target, Errors errors) {
		ValoresDto valoresDto = (ValoresDto) target;
		ValorDto valorDto = valoresDto.getValorDto();

		if (valorDto.getOperation() != null) {
			if (Constantes.CREATE.intValue() == valorDto.getOperation()
					.intValue()) {
				errors = validarAlta(errors, valorDto);
			} else {
				if (Constantes.UPDATE.intValue() == valorDto.getOperation()
						.intValue()) {
					errors = validarModificacion(errors, valorDto);
				} else {
					if (Constantes.UNSUBSCRIBE.intValue() == valorDto
							.getOperation().intValue()) {
						errors = validarBaja(errors, valorDto);
					} else {
						if (Constantes.SUBIR_COMPROBANTE.intValue() == valorDto.getOperation().intValue()) {
							if (Validaciones.isNullOrEmpty(valorDto.getDescripcionComprobante())) {
								errors.rejectValue("comprobanteDescripcionVacio", "error.obligatorio");
							} else {
								if (!Validaciones.esFormatoTexto(valorDto.getDescripcionComprobante())) {
									errors.rejectValue("comprobanteDescripcionVacio", "error.formatoTexto");
								} else {
									validadorGenericoDeArchivo.validarRestriccionesArchivo(valoresDto.getFileComprobante(), errors, "comprobanteVacio");
								}
							}
						}
					}
				}
			}
		} else {
			errors.rejectValue("errorGeneral", "title.operation.unknown");
		}
	}
	
	public void validarComprobantes(MultipartFile file, String descripcion) throws ValidacionExcepcion, NegocioExcepcion {
		if (!Validaciones.esFormatoTexto(descripcion)) {
			throw new ValidacionExcepcion("", "error.formatoTexto", "#errorDescripcionComp");
		} 
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			throw new ValidacionExcepcion("","error.nombreArchivoMuyLargo", "#errorArchComprobante");
		} else {
			try {
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					throw new ValidacionExcepcion("","valor.error.archivoVacio", "#errorArchComprobante");
				} else { 
					if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
						throw new ValidacionExcepcion("","error.archivoProhibido", "#errorArchComprobante");
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							throw new ValidacionExcepcion("","error.extensionProhibida", "#errorArchComprobante");
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								throw new ValidacionExcepcion("","error.pesoMaximoSuperado", "#errorArchComprobante");
							} else if (ControlArchivo.archivoTieneCaracteresNoPermitidos(file.getOriginalFilename())) {
								throw new ValidacionExcepcion("","error.caracteresInvalidos","#errorArchComprobante"); 
							}
						}
					}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
	}
	
	public void validarValorDto(Object target, Errors errors) {
		ValorDto valorDto = (ValorDto) target;

		if (valorDto.getOperation() != null) {
			if (Constantes.CREATE.intValue() == valorDto.getOperation().intValue()) {
				errors = validarAlta(errors, valorDto);
			} else {
				if (Constantes.UPDATE.intValue() == valorDto.getOperation().intValue()) {
					errors = validarModificacion(errors, valorDto);
				} else {
					if (Constantes.UNSUBSCRIBE.intValue() == valorDto.getOperation().intValue()) {
						errors = validarBaja(errors, valorDto);
					} else {
						if (Constantes.CONFIRMAR_AVISO_PAGO.intValue() == valorDto.getOperation().intValue()) {
							errors = validarConfirmacionAvisoPago(errors, valorDto);
						} else {
							if (Constantes.RECHAZAR_AVISO_PAGO.intValue() == valorDto.getOperation().intValue()) {
								errors = validarRechazoAvisoPago(errors, valorDto);
							}else{
								if (Constantes.CREAR_VALOR_REGISTRO_AVC.intValue() == valorDto.getOperation().intValue()) {
									errors = validarAltaValorAPartirRegistroAvc(errors, valorDto);
								} else {
									if (Constantes.CONFIRMAR_VALOR_REGISTRO_AVC.intValue() == valorDto.getOperation().intValue()) {
										errors = validarConfirmarValorRegistroAvc(errors, valorDto);
									} else {
										if (Constantes.SUBIR_COMPROBANTE.intValue() == valorDto.getOperation().intValue()) {
											if (Validaciones.isNullOrEmpty(valorDto.getDescripcionComprobante())) {
												if(valorDto.isAdjuntando()) {
													errors.rejectValue("comprobanteDescripcionVacioModif", "error.obligatorio");
													valorDto.setOperation(Constantes.CREAR_VALOR_REGISTRO_AVC);
												}
											}else {
												if (!Validaciones.esFormatoTexto(valorDto.getDescripcionComprobante())) {
													errors.rejectValue("comprobanteDescripcionVacioModif", "error.formatoTexto");
												} else {
													validadorGenericoDeArchivo.validarRestriccionesArchivo(valorDto.getFileComprobanteModificacion(), errors, "errorArchivoVacio");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			errors.rejectValue("errorGeneral", "title.operation.unknown");
		}
	}

	/**
	 * Valido el alta
	 * 
	 * @param errors
	 * @param usuario
	 * @return
	 */

	private Errors validarAlta(Errors errors, ValorDto boleta) {

		// SECCION DATOS GENERALES

		try {
			boletaConValorValidacionServicio.validacionEmpresa(boleta.getIdEmpresa());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.empresa", e.getLeyenda());
		}
		try {
			boletaConValorValidacionServicio.validacionSegmento(boleta.getIdSegmento());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.segmento", e.getLeyenda());
		}
//		try {			
//			boletaConValorValidacionServicio.validacionCodCliente(boleta.getCodCliente());
//			boletaConValorValidacionServicio.validacionCodClienteSiebel(boleta.getCodClienteAgrupador());
//			boletaConValorValidacionServicio.validacionRazonSocial(boleta.getRazonSocialClienteAgrupador());
//		} catch (ValidacionExcepcion e) {
//			errors.rejectValue("valorDto.codCliente", e.getLeyenda());
//		}
		if (boleta.isCheckEnviarMailBoleta()) {
			try {
				boletaConValorValidacionServicio.validacionEmailCheck(boleta.getEmail());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.email", e.getLeyenda());
			}
		} else {
			try {
				boletaConValorValidacionServicio.validacionEmail(boleta.getEmail());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.email", e.getLeyenda());
			}
		}
		try {
			boletaConValorValidacionServicio.validacionAnalista(boleta.getIdAnalista());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.nombreAnalista", e.getLeyenda());
		}
		try {
			boletaConValorValidacionServicio.validacionObservaciones(boleta.getObservaciones());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.observaciones", e.getLeyenda());
		}
		try {
			boletaConValorValidacionServicio.validacionObservaciones(boleta.getObservacionMail());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.observacionMail", e.getLeyenda());
		}
		
		// FIN DATOS GENERALES
		// CAJERO PAGADOR
		if (boleta.isCajeroPagador()) {
			try {
				boletaConValorValidacionServicio.validacionCopropietario(boleta.getIdCopropietario());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.copropietario", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionOperacionAsociadaObligatoria(boleta.getOperacionAsociada());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.operacionAsociada", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo",e.getLeyenda());
			}
		
		} else {
			try {
				boletaConValorValidacionServicio.validacionOperacionAsociada(boleta.getOperacionAsociada());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.operacionAsociada", e.getLeyenda());
			}
		}
		
		if("3".equals(boleta.getIdOrigen())){
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo", e.getLeyenda());
			}
		}
		// FIN CAJERO PAGADOR
		// BOLETA Y AVISO
		try {
			boletaConValorValidacionServicio.validacionTipoValor(boleta.getIdTipoValor());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.tipoValor", e.getLeyenda());
		}
		try {
			boletaConValorValidacionServicio.validacionImporte(boleta.getImporte());
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.importe", e.getLeyenda());
		}
		try {
			boletaConValorValidacionServicio.validacionReciboPreImpreso(boleta.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
			errors.rejectValue("valorDto.reciboPreImpreso", e.getLeyenda());
		}

		// SECCION DIFERENCIADA POR TIPO DE VALOR
		String evaluarTipo = boleta.getIdTipoValor();
		String evaluarTipoImpuesto = boleta.getIdTipoImpuesto();
		
		if (Validaciones.isNullOrEmpty(evaluarTipo)) {
			try {
				boletaConValorValidacionServicio.validacionCheckRadioSeleccionado(boleta.isCheckImprimirBoleta(), boleta.isCheckEnviarMailBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.checkEnviarMailBoleta", e.getLeyenda());
			}
		}


		if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString().equals(evaluarTipo)) {
			try {
				boletaConValorValidacionServicio.validacionOrigen(boleta.getIdOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.origen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoOrigen(boleta.getIdBancoOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoOrigen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDeCheque(boleta.getNumeroCheque());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCheque", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionCheckRadioSeleccionado(boleta.isCheckImprimirBoleta(), boleta.isCheckEnviarMailBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.checkEnviarMailBoleta", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionOrigen(boleta.getIdOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.origen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoOrigen(boleta.getIdBancoOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoOrigen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDeCheque(boleta.getNumeroCheque());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCheque", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaVencimiento(boleta.getFechaVencimiento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaVencimiento", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionCheckRadioSeleccionado(boleta.isCheckImprimirBoleta(), boleta.isCheckEnviarMailBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.checkEnviarMailBoleta", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) {
			try {
				boletaConValorValidacionServicio.validacionOrigen(boleta.getIdOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.origen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionCheckRadioSeleccionado(boleta.isCheckImprimirBoleta(), boleta.isCheckEnviarMailBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.checkEnviarMailBoleta", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.CHEQUE.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionListaComprobantes(boleta.getListaComprobantes());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("comprobanteVacio", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoOrigen(boleta.getIdBancoOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoOrigen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDeCheque(boleta.getNumeroCheque());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCheque", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaDeposito(boleta.getFechaDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(boleta.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroDocumentoContable", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(boleta.getNumeroBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroBoleta",	e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionListaComprobantes(boleta.getListaComprobantes());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("comprobanteVacio", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoOrigen(boleta.getIdBancoOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoOrigen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDeCheque(boleta.getNumeroCheque());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCheque", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaVencimiento(boleta.getFechaVencimiento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaVencimiento", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaDeposito(boleta.getFechaDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(boleta.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroDocumentoContable", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(boleta.getNumeroBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroBoleta",	e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionListaComprobantes(boleta.getListaComprobantes());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("comprobanteVacio", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaDeposito(boleta.getFechaDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio
						.validacionNumeroDocumentoContable(boleta.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroDocumentoContable", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroBoleta(boleta.getNumeroBoleta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroBoleta",	e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.TRANSFERENCIA.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionListaComprobantes(boleta.getListaComprobantes());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("comprobanteVacio", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionCuit(boleta.getCuit());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.cuit", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(boleta.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(boleta.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroCuenta", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoOrigen(boleta.getIdBancoOrigen());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.bancoOrigen", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(boleta.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroDocumentoContable", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaTransferencia(boleta.getFechaTransferencia());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaTransferencia", e.getLeyenda());
			}

			try {
				boletaConValorValidacionServicio.validacionNumeroReferencia(boleta.getNumeroReferencia());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.numeroReferencia", e.getLeyenda());
			}

		} else 
		if (TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio
						.validacionListaComprobantes(boleta.getListaComprobantes());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("comprobanteVacio", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionTipoImpuesto(boleta.getIdTipoImpuesto());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.tipoImpuesto", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio
						.validacionNumeroConstancia(boleta.getNroConstancia());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.nroConstancia", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaEmision(boleta.getFechaEmision());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaEmision", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(boleta.getFechaIngresoRecibo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("valorDto.fechaIngresoRecibo", e.getLeyenda());
			}
			
			//-------- Tipo Impuesto -------------------------------------
			if ("1".equals(evaluarTipoImpuesto)) {
				try {
					boletaConValorValidacionServicio.validacionCuitIIBB(boleta.getCuitIbb());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("valorDto.cuitIbb", e.getLeyenda());
				}
				try {
					boletaConValorValidacionServicio.validacionProvincia(boleta.getIdProvincia());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("valorDto.provincia", e.getLeyenda());
				}
				if(!Validaciones.isNullOrEmpty(boleta.getFechaEmision())){
					try {
						boletaConValorValidacionServicio.validacionFechaEmisionPorProvincia(Utilidad.parseDatePickerString(boleta.getFechaEmision()), boleta.getIdProvincia());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("valorDto.fechaEmision", e.getLeyenda(), e.getParametros(), "");
					} catch (ParseException ex) {
						Traza.error(getClass(), "Error al parsear ...", ex);
						errors.rejectValue("valorDto.fechaEmision", "error.fechaParseo");
					}
				}
				
				if(!Validaciones.isNullOrEmpty(boleta.getIdTipoComprobante())){
					try {
						boletaConValorValidacionServicio.validacionLetraComprobante(boleta.getIdLetraComprobante());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("valorDto.letraComprobante", e.getLeyenda());
					}
					try {
						boletaConValorValidacionServicio.validacionNumeroLegalComprobanteObligatorio(boleta.getNumeroLegalComprobante());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("valorDto.numeroLegalComprobante", e.getLeyenda());
					}
				} else {
					try {
						boletaConValorValidacionServicio.validacionNumeroLegalComprobante(boleta.getNumeroLegalComprobante());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("valorDto.numeroLegalComprobante", e.getLeyenda());
					}
				}
			}
		}
		return errors;
	}

	private Errors validarModificacion(Errors errors, ValorDto valorDto) {

		// SECCION DATOS GENERALES
		if(valorDto.isModifAnaRecha()){
			try {
				boletaConValorValidacionServicio.validacionEmpresa(valorDto.getIdEmpresa());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("empresa", e.getLeyenda());
			}
		}
		if(valorDto.isModifAnaRecha()){
			try {
				boletaConValorValidacionServicio.validacionSegmento(valorDto.getIdSegmento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("segmento", e.getLeyenda());
			}
		}
		if(valorDto.isModifAnaRecha() || valorDto.isModifAdmRecha()){
			try {			
				boletaConValorValidacionServicio.validacionCodCliente(Validaciones.isObjectNull(valorDto.getCliente()) ? "" :valorDto.getCliente().getIdClienteLegado());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("codCliente", e.getLeyenda());
			}
		}
		if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha() || valorDto.isModifSupRecha()){
			if (valorDto.isCheckEnviarMailBoleta()) {
				try {
					boletaConValorValidacionServicio.validacionEmailCheck(valorDto.getEmail());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("email", e.getLeyenda());
				}
			} else {
				try {
					boletaConValorValidacionServicio.validacionEmail(valorDto.getEmail());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("email", e.getLeyenda());
				}
			}
			try {
				boletaConValorValidacionServicio.validacionObservaciones(valorDto.getObservaciones());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("observaciones", e.getLeyenda());
			}
		}
		
		
		// FIN DATOS GENERALES
		
		// CAJERO PAGADOR
		if (valorDto.isCajeroPagador()) {
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
				try {
					boletaConValorValidacionServicio.validacionCopropietario(valorDto.getCopropietario());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("copropietario", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
				try {
					boletaConValorValidacionServicio.validacionOperacionAsociadaObligatoria(valorDto.getOperacionAsociada());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("operacionAsociada", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
				try {
					boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("reciboPreImpreso", e.getLeyenda());
				}
			}
		} else {
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha() || valorDto.isModifSupRecha()){
				try {
					boletaConValorValidacionServicio.validacionOperacionAsociada(valorDto.getOperacionAsociada());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("operacionAsociada", e.getLeyenda());
				}
			}
		}
		// FIN CAJERO PAGADOR
		
		// BOLETA Y AVISO
		if(valorDto.isModifAnaRecha()){
			try {
				boletaConValorValidacionServicio.validacionImporte(valorDto.getImporte());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("importe", e.getLeyenda());
			}
		}

		// SECCION DIFERENCIADA POR TIPO DE VALOR
		String evaluarTipo = valorDto.getIdTipoValor();
		String evaluarTipoImpuesto = valorDto.getIdTipoImpuesto();

		if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString().equals(evaluarTipo)) { // BD CHEQUE---------------------------------------------------------------------------------
			try {
				boletaConValorValidacionServicio.validacionFechaEmisionCheque(valorDto.getFechaEmision());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaEmisionCheque", e.getLeyenda());
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionOrigen(valorDto.getIdOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("origen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoOrigen(valorDto.getIdBancoOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoOrigen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDeCheque(valorDto.getNumeroCheque());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCheque", e.getLeyenda());
				}
			}
			if("3".equals(valorDto.getIdOrigen())){
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("reciboPreImpreso", e.getLeyenda());
					}
				}
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
					}
				}
			}
			if (valorDto.isModifAnaRecha()) {
				try {
					boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
			
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}
		} else if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionFechaEmisionCheque(valorDto.getFechaEmision());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaEmisionCheque", e.getLeyenda());
			}
			if (valorDto.isModifAnaRecha()) {
				try {
					boletaConValorValidacionServicio.validacionOrigen(valorDto.getIdOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("origen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoOrigen(valorDto.getIdBancoOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoOrigen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDeCheque(valorDto.getNumeroCheque());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCheque", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaVencimiento(valorDto.getFechaVencimiento());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaVencimiento", e.getLeyenda());
				}
			}
			if("3".equals(valorDto.getIdOrigen())){
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("reciboPreImpreso", e.getLeyenda());
					}
				}
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
					}
				}
			} else {
				// TODO HAY que verificar por que al editar se puede modificar 
				try {
					boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
				}
				
				try {
					boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("reciboPreImpreso", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
		} else if (TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) { 
			if (valorDto.isModifAnaRecha()) {
				try {
					boletaConValorValidacionServicio.validacionOrigen(valorDto.getIdOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("origen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if("3".equals(valorDto.getIdOrigen())){
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("reciboPreImpreso", e.getLeyenda());
					}
				}
				if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
					try {
						boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
					}
				}
			} else {
				try {
					boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
				}
				
				try {
					boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("reciboPreImpreso", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
		} else 
		if (TipoValorEnum.CHEQUE.getIdTipoValorString().equals(evaluarTipo)) {
			try {
				boletaConValorValidacionServicio.validacionFechaEmisionCheque(valorDto.getFechaEmision());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaEmisionCheque", e.getLeyenda());
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoOrigen(valorDto.getIdBancoOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoOrigen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDeCheque(valorDto.getNumeroCheque());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCheque", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaDeposito(valorDto.getFechaDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}
		} else 
		if (TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionFechaEmisionCheque(valorDto.getFechaEmision());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaEmisionCheque", e.getLeyenda());
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoOrigen(valorDto.getIdBancoOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoOrigen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDeCheque(valorDto.getNumeroCheque());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCheque", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha() || valorDto.isModifAnaNoRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaVencimiento(valorDto.getFechaVencimiento());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaVencimiento", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaDeposito(valorDto.getFechaDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroBoletaNumerico(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}
		} else if (TipoValorEnum.EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) { // EFECTIVO---------------------------------------------------------------------------------
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaDeposito(valorDto.getFechaDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroBoleta(valorDto.getNumeroBoleta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroBoleta", e.getLeyenda());
				}
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), false);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}
		} else if (TipoValorEnum.TRANSFERENCIA.getIdTipoValorString().equals(evaluarTipo)) { // TRANSFERENCIA---------------------------------------------------------------------------------
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoOrigen(valorDto.getIdBancoOrigen());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoOrigen", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroReferencia(valorDto.getNumeroReferencia());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroReferencia", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaTransferencia(valorDto.getFechaTransferencia());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaTransferencia", e.getLeyenda());
				}
			}
		} else 
		if (TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString().equals(evaluarTipo)){
//			if(valorDto.isModifAnaRecha()){
//				try {
//					boletaConValorValidacionServicio.validacionListaComprobantes(valorDto.getListaComprobantes());
//				} catch (ValidacionExcepcion e) {
//					errors.rejectValue("comprobanteVacio", e.getLeyenda());
//				}
//			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionAcuerdo(valorDto.getIdAcuerdo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("acuerdo", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionBancoDeposito(valorDto.getIdBancoDeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("bancoDeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNroCuenta(valorDto.getIdNroCuenta());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroCuenta", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaInterdeposito(valorDto.getFechaInterdeposito());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaInterdeposito", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroInterdeposito(valorDto.getNumeroInterdepositoCdif());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroInterdepositoCdif", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionCodOrganismo(valorDto.getCodOrganismo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("codOrganismo", e.getLeyenda());
				}
			}			
		} else if (TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(evaluarTipo)) { 
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionTipoImpuesto(valorDto.getTipoImpuesto());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("tipoImpuesto", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionNumeroConstancia(valorDto.getNroConstancia());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("nroConstancia", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					boletaConValorValidacionServicio.validacionFechaEmision(valorDto.getFechaEmision());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaEmision", e.getLeyenda());
				}
			}
			if(valorDto.isModifAnaRecha()){
				try {
					if(Validaciones.isNumeric(valorDto.getNumeroDocumentoContable())){
						throw new ValidacionExcepcion("", "error.numerico");
					}
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
				}
			}
			if ((Integer.valueOf(TipoRetencionEnum.RETENCION_IIBB.getId())).equals(Integer.valueOf(evaluarTipoImpuesto))) {
				
				try {
					boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
				}
				
				if(valorDto.isModifAnaRecha()){
					try {
						boletaConValorValidacionServicio.validacionCuitIIBB(valorDto.getCuitIbb());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("cuitIbb", e.getLeyenda());
					}
				}
				if(valorDto.isModifAnaRecha()){
					try {
						boletaConValorValidacionServicio.validacionProvincia(valorDto.getProvincia());
					} catch (ValidacionExcepcion e) {
						errors.rejectValue("provincia", e.getLeyenda());
					}
				}
				if(valorDto.isModifAnaRecha() || valorDto.isModifUltimoMes()){
					if(!Validaciones.isNullOrEmpty(valorDto.getIdTipoComprobante())){
						try {
							boletaConValorValidacionServicio.validacionLetraComprobante(valorDto.getIdLetraComprobante());
						} catch (ValidacionExcepcion e) {
							errors.rejectValue("letraComprobante", e.getLeyenda());
						}
						try {
							boletaConValorValidacionServicio.validacionNumeroLegalComprobanteObligatorio(valorDto.getNumeroLegalComprobante());
						} catch (ValidacionExcepcion e) {
							errors.rejectValue("numeroLegalComprobante", e.getLeyenda());
						}
					} else {
						try {
							boletaConValorValidacionServicio.validacionNumeroLegalComprobante(valorDto.getNumeroLegalComprobante());
						} catch (ValidacionExcepcion e) {
							errors.rejectValue("numeroLegalComprobante", e.getLeyenda());
						}
					}
				}
			}
			try {
				boletaConValorValidacionServicio.validacionFechaRecibo(valorDto.getFechaIngresoRecibo(), true);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionReciboPreImpreso(valorDto.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}
		}

		if (!errors.hasErrors()) {
			try {
				boletaConValorValidacionServicio.validacionModificacion(valorDto);
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("accion", e.getLeyenda());
				valorDto.setDescripcionNingunaModificacion(e.getLeyenda());
				valorDto.setErrorNingunaModificacion(true);
			}
		}
		return errors;
	}

	private Errors validarBaja(Errors errors, ValorDto boletaDto) {
		return null;
	}
	
	/**
	Valido la confirmacion
	* Nro. de Documento Contable
	*	Campo de tipo numérico.
	*	Campo obligatorio para todos los tipos de Avisos de Pago, exceptuando “Retenciones e Impuestos” e “Interdepósitos”
	*Sección “Comprobantes”
	*	Es obligatorio adjuntar comprobante para todos los tipos de Aviso de Pago
	*Observaciones
	*	Campo de tipo alfanumerico
	**/
	private Errors validarConfirmacionAvisoPago(Errors errors, ValorDto valorDto) {
		
		try {
			boletaConValorValidacionServicio.validacionObservacionConfirmacion(valorDto.getObservacionConfirmacion(), false);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observacionConfirmacion", e.getLeyenda());
		}
		
		String evaluarTipo = valorDto.getIdTipoValor();
		// BD CHEQUE---------------------------------------------------------------------------------
		if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString().equals(evaluarTipo)) {
		} else 
		// BD CHEQUE DIFERIDO------------------------------------------------------------------------	
		if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) {
		} else 
		// BD EFECTIVO-------------------------------------------------------------------------------	
		if (TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) { 
		} else 
		// CHEQUE------------------------------------------------------------------------------------	
		if (TipoValorEnum.CHEQUE.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
			}
		} else 
		// CHEQUE DIFERIDO---------------------------------------------------------------------------	
		if (TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
			}
		} else
		// EFECTIVO-----------------------------------------------------------------------------------	
		if (TipoValorEnum.EFECTIVO.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
			}
		} else 
		// TRANSFERENCIA---------------------------------------------------------------------------------	
		if (TipoValorEnum.TRANSFERENCIA.getIdTipoValorString().equals(evaluarTipo)) { 
			try {
				boletaConValorValidacionServicio.validacionNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("numeroDocumentoContable", e.getLeyenda());
			}
		} else 
		// Interdeposito---------------------------------------------------------------------------------
		if (TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString().equals(evaluarTipo)){
		} else 
		// IMPUESTO/RETENCION---------------------------------------------------------------------------------	
		if (TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(evaluarTipo)) {
		}
		return errors;	
	}
	
	/**
	Valido la confirmacion
	* Observaciones Confirmacion 
	*	Campo de tipo alfanumerico y obligatorio
	**/
	private Errors validarRechazoAvisoPago(Errors errors, ValorDto valorDto) {
		
		try {
			boletaConValorValidacionServicio.validacionObservacionConfirmacion(valorDto.getObservacionConfirmacion(), true);
		} catch (ValidacionExcepcion e) {
			errors.rejectValue("observacionConfirmacion", e.getLeyenda());
		}
		return errors;	
	}
	
	/**
	 * Valido el valor cuando se da de alta a partir de un registro Avc.
	 * @param errors
	 * @param valor
	 * @return
	 */
	private Errors validarAltaValorAPartirRegistroAvc(Errors errors, ValorDto valor) {
	
		if(valor.isAdjuntando()){
			//Comprobantes
			if (Validaciones.isNullOrEmpty(valor.getDescripcionComprobante())) {
				errors.rejectValue("comprobanteDescripcionVacioModif", "error.obligatorio");
			} else {
//				if (valor.getFileComprobanteModificacion().getBytes().length==0){
//					errors.rejectValue("errorArchivoVacio", "valor.error.archivoVacio");
//				}
				if (!Validaciones.esFormatoTexto(valor.getDescripcionComprobante())) {
					errors.rejectValue("comprobanteDescripcionVacioModif", "error.formatoTexto");
				} else {
					validadorGenericoDeArchivo.validarRestriccionesArchivo(valor.getFileComprobanteModificacion(), errors, "errorArchivoVacio");
				}
			}
		}else{
			try {
				boletaConValorValidacionServicio.validacionSegmento(valor.getIdSegmento());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("segmento", e.getLeyenda());
			}
			//Al dar de alta un valor por reversion desde consultar registros avc
			//sin conciliar.Valida que estos campos no esten vacios.
			try {
				boletaConValorValidacionServicio.validacionAcuerdo(valor.getIdAcuerdo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("acuerdo", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionBancoDeposito(valor.getIdBancoDeposito());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("bancoDeposito", e.getLeyenda());
			}
			try {
				boletaConValorValidacionServicio.validacionNroCuenta(valor.getIdNroCuenta());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("numeroCuenta", e.getLeyenda());
			}
			
			try {
				boletaConValorValidacionServicio.validacionAnalista(valor.getIdAnalista());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("nombreAnalista", e.getLeyenda());
			}
			if (Validaciones.isNullOrEmpty(valor.getValorPorReversion())){
				try {
					boletaConValorValidacionServicio.validacionObservaciones(valor.getObservaciones());
				} catch (ValidacionExcepcion e) {
					errors.rejectValue("observaciones", e.getLeyenda());
				}
				try {
					boletaConValorValidacionServicio.validacionListaComprobantes(valor.getListaComprobantes());
				} catch (ValidacionExcepcion e) {
					valor.setErrorComprobanteVacioModif(true);
				}
			}


			try {
				boletaConValorValidacionServicio.validarReciboPreImpreso(valor.getReciboPreImpreso());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("reciboPreImpreso", e.getLeyenda());
			}


			try {
				boletaConValorValidacionServicio.validarFechaIngresoRecibo(valor.getFechaIngresoRecibo());
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("fechaIngresoRecibo", e.getLeyenda());
			}

			
			//validar Unicidad
			try {
				boolean unicidad;
				if(valor.getIdValor() != null){
					/*
					 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
					 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
					 */
					 unicidad = valorServicio.reValidarUnicidadValor(valor);
					 
					/*
					 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
					 * Este método es el que debe quedar cuando se habilite la mejora en el próximo Release, eliminando los anteriores.
					 */
					//unicidad = valorServicio.validarUnicidadValor(valor, true);
					
				} else {
					unicidad = valorPorReversionServicio.validarValorPorReversion(valor, errors, "errorSaldoReversado");
				}
				if(!unicidad){
					valor.setErrorUnicidadRegistro(true);
					switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valor.getIdTipoValor()))) {
				    case CHEQUE:
				    	valor.setDescripcionUnicidadRegistro(
						"No se puede proceder al alta del valor debido a la existencia de un cheque con" 
						+ " Nro. Cheque " + valor.getNumeroCheque()
						+ " para Banco Origen " + valor.getBancoOrigen()
						+ " con Importe " + valor.getImporte()
						+ " y Fecha Depósito " + valor.getFechaDeposito());
						break;
				    case CHEQUE_DIFERIDO:
				    	valor.setDescripcionUnicidadRegistro("No se puede proceder al alta del valor debido a la existencia de un cheque diferido con" 
				    			+ " Nro. Cheque " + valor.getNumeroCheque()
								+ " para Banco Origen " + valor.getBancoOrigen()
								+ " con Importe " + valor.getImporte()
								+ " y Fecha Depósito " + valor.getFechaDeposito()
				    			+ " y Fecha Vencimiento " + valor.getFechaVencimiento());
						break;
				    case TRANSFERENCIA:
				    	valor.setDescripcionUnicidadRegistro("No se puede proceder al alta del valor debido a la existencia de un transferencia con "
				    			+ " Nro. Referencia " + valor.getNumeroReferencia()
				    			+ " para Fecha Transferencia " + valor.getFechaTransferencia()
				    			+ " con Importe "+ valor.getImporte()
				    			+ " y Acuerdo "+ valor.getIdAcuerdo());
				    	break;
				    case INTERDEPÓSITO:
				    	valor.setDescripcionUnicidadRegistro("No se puede proceder al alta del valor debido a la existencia de un interdepósito con "
				    			+ " Cliente Legado " + valor.getCodCliente()
				    			+ " con número Interdepósito " + valor.getNumeroInterdepositoCdif()
				    			+ " y fecha Interdepósito " + valor.getFechaInterdeposito());
				    	break;
					default:
					};
					
				}
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), e.getMessage(), e);
				errors.rejectValue(Constantes.EXCEPCION, e.getMessage());
			}
		}
		return errors;
	}
	
	private Errors validarConfirmarValorRegistroAvc(Errors errors, ValorDto valor) {
	
		if(valor.isEstaRechazandoConfirmacion()){
			try {
				if(!Validaciones.isNullOrEmpty(valor.getObservacionesConfirmarAlta())) {
					boletaConValorValidacionServicio.validacionObservaciones(valor.getObservacionesConfirmarAlta());
				}else {
					errors.rejectValue("observacionesConfirmarAlta", "error.obligatorio");
				}
			} catch (ValidacionExcepcion e) {
				errors.rejectValue("observacionesConfirmarAlta", e.getLeyenda());
			}
		}
	
		return errors;
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public IBoletaConValorValidacionServicio getBoletaConValorValidacionServicio() {
		return boletaConValorValidacionServicio;
	}

	public void setBoletaConValorValidacionServicio(
			IBoletaConValorValidacionServicio boletaConValorValidacionServicio) {
		this.boletaConValorValidacionServicio = boletaConValorValidacionServicio;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}
}
