/*******************************************************************************
	Validaciones de campos de formulario
*******************************************************************************/
var errorLeyenda = new Object();
/* CAMBPO EMPRESA */
var validacion_empresa = function(dto) {
	var error = null;
	if (isUndefinedNullDashEmptyOrFalse(dto.empresa)) {
		error = new Object();
		error.campo = 'errorEmpresa';
		error.mensaje = errorLeyenda.obligatorio;
	};
	return error;
};
var validacion_segmento = function(dto) {
	var error = null;
	if (isUndefinedNullDashEmptyOrFalse(dto.segmento)) {
		error = new Object();
		error.campo = 'errorSegmento';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};

//boletaConValorValidacionServicio.validacionCodCliente(boleta.getCodCliente());
var validacion_codCliente = function(dto) {
	var error = null;
	if ($.isEmpty(dto.codCliente)) {
		error = new Object();
		error.campo = 'errorCodCliente';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.codCliente)) {
		error = new Object();
		error.campo = 'errorCodCliente';
		error.mensaje = errorLeyenda.obligatorio;
	} else if ($.isEmpty(dto.codClienteAgrupador)) {
		error = new Object();
		error.campo = 'errorCodCliente';
		error.mensaje = errorLeyenda.boletaAltaMensajeSiebelNoValidado;
	} else if ($.isEmpty(dto.razonSocialClienteAgrupador)) {
		error = new Object();
		error.campo = 'errorCodCliente';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};

var validacion_Cliente = function(dto) {
	var error = null;
	if ($.isEmpty(dto.cliente)) {
		error = new Object();
		error.campo = 'errorClienteSeleccionado';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};

//boletaConValorValidacionServicio.validacionCodClienteSiebel(boleta.getCodClienteAgrupador());
//boletaConValorValidacionServicio.validacionRazonSocial(boleta.getRazonSocialClienteAgrupador());
var validacion_email = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.email)) {
		if (!$.isNullOrEmail(dto.email)) {
			error = new Object();
			error.campo = 'errorEmail';
			error.mensaje = errorLeyenda.formatoEmail;
		}
	}
	return error;
};

//boletaConValorValidacionServicio.validacionAnalista(boleta.getIdAnalista());
var validacion_analista = function(dto) {
	var error = null;
	if (isUndefinedNullDashEmptyOrFalse(dto.idAnalista)) {
		error = new Object();
		error.campo = 'errorNombreAnalista';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};



//boletaConValorValidacionServicio.validacionObservaciones(boleta.getObservaciones());
var validar_observaciones = function(dto) {
	var error = null;
	if (!$.validacionCaracteresEspeciales(dto.observaciones)) {
		error = new Object();
		error.campo = 'errorObservaciones';
		error.mensaje = errorLeyenda.formatoTexto;
	}
	return error;
};
var validar_observacionesEmail = function(dto) {
	var error = null;
	if (!$.validacionCaracteresEspeciales(dto.observacionMail)) {
		error = new Object();
		error.campo = 'errorObservacionesMail';
		error.mensaje = errorLeyenda.formatoTexto;
	}
	return error;
};

var validar_observacionesEmail_boleta = function(dto) {
	var error = null;
	if (dto.enviarBoletaMail) {
		if(!$.isEmpty(dto.observacionMail)){
			if (!$.validacionCaracteresEspeciales(dto.observacionMail)) {
				error = new Object();
				error.campo = 'errorObservacionesMail';
				error.mensaje = errorLeyenda.formatoTexto;
			}
		}else{
			error = new Object();
			error.campo = 'errorObservacionesMail';
			error.mensaje = errorLeyenda.obligatorio;
		}
	}
		
	return error;
};




function validacionObservaciones(observaciones) {
	if (!$.isEmpty(observaciones)) {
		if (!$.validacionCaracteresEspeciales((observaciones))) {
			return false;
		}
	}
	return true;
}
var validacion_operacionAsociada = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.operacionAsociada)) {
		if (!$.validacionIsNumeric(dto.operacionAsociada)) {
			error = new Object();
			error.campo = 'errorOperacionAsociada';
			error.mensaje = errorLeyenda.numerico;
		}
	}
	return error;
};
var validacion_operacionAsociadaObligatorio = function(dto) {
	var error = null;
	if ($.isEmpty(dto.operacionAsociada)) {
		error = new Object();
		error.campo = 'errorOperacionAsociada';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.operacionAsociada)) {
		error = new Object();
		error.campo = 'errorOperacionAsociada';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};
var validacion_tipoValor = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idTipoValor)) {
		error = new Object();
		error.campo = 'errorTipoValor';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};

var validacion_importe = function(dto) {
	var error = null;
	var msj = '';
	
	if ($.isEmpty(dto.importe)) {
		msj = errorLeyenda.obligatorio;
	} else if((dto.importe.split('.').join('').replace(',','.') * 1) == 0){
		msj = errorLeyenda.valorCero;
	} else if (!validarImporteValido(dto.importe)) {
		msj = errorLeyenda.numeroConDosDigitos;
	} else {
		var valorSinPuntoDesimales = dto.importe.split('.').join('');
		var valorDescompuesto = valorSinPuntoDesimales.split(',');

		if (valorDescompuesto[0].length > 9) {
			msj = errorLeyenda.msgErrorInputLength;
		} else if (!$.isEmpty(valorDescompuesto[1]) && valorDescompuesto[1].length > 2) {
			msj = errorLeyenda.msgErrorInputLength;
		} else {
//			var importeNumerico = importeToFloat(importe);
//			if (importeNumerico <= 0) {
//			}
		}
	}
	if (!$.isEmpty(msj)) {
		error = new Object();
		error.campo = 'errorImporte';
		error.mensaje = msj;
	}
	return error;
};
var validacion_reciboPreImpreso = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.reciboPreImpreso)) {
		if (!$.isFormatoRecibo(dto.reciboPreImpreso)) {
			error = new Object();
			error.campo = 'errorReciboPreImpreso';
			error.mensaje = errorLeyenda.formatoRecibo;
		}
	}
	return error;
};

/******************************************************************************
 TipoValorEnum.CHEQUE
*******************************************************************************/
//boletaConValorValidacionServicio.validacionListaComprobantes(boleta.getListaComprobantes());

//boletaConValorValidacionServicio.validacionAcuerdo(boleta.getIdAcuerdo());
var validacion_idAcuerdo = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idAcuerdo)) {
		error = new Object();
		error.campo = 'errorAcuerdo';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
var validacion_idBancoDeposito = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idBancoDeposito)) {
		error = new Object();
		error.campo = 'errorBancoDeposito';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
var validacion_idNroCuenta = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idNroCuenta)) {
		error = new Object();
		error.campo = 'errorNumeroCuenta';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};

var validacion_idBancoOrigen = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idBancoOrigen)) {
		error = new Object();
		error.campo = 'errorIdBancoOrigen';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
var validacion_numeroCheque = function(dto) {
	var error = null;
	if ($.isEmpty(dto.numeroCheque)) {
		error = new Object();
		error.campo = 'errorNumeroCheque';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.numeroCheque)) {
		error = new Object();
		error.campo = 'errorNumeroCheque';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};
var validacion_fechaDeposito = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaDeposito)) {
		error = new Object();
		error.campo = 'errorFechaDeposito';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaDeposito)) {
		error = new Object();
		error.campo = 'errorFechaDeposito';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	}
	return error;
};

var validacion_numeroDocumentoContable = function(dto) {
	var error = null;
	if ($.isEmpty(dto.numeroDocumentoContable)) {
		error = new Object();
		error.campo = 'errorNumeroDocumentoContable';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.numeroDocumentoContable)) {
		error = new Object();
		error.campo = 'errorNumeroDocumentoContable';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};

var validacion_numeroBoleta = function(dto) {
	var error = null;
	if ($.isEmpty(dto.numeroBoleta)) {
		error = new Object();
		error.campo = 'errorNumeroBoleta';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.numeroBoleta)) {
		error = new Object();
		error.campo = 'errorNumeroBoleta';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};

var validacion_numeroBoletaNumerico = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.numeroBoleta)) {
		if (!$.validacionIsNumeric(dto.numeroBoleta)) {
			error = new Object();
			error.campo = 'errorNumeroBoleta';
			error.mensaje = errorLeyenda.numerico;
		}
	}
	return error;
};
var validacion_fechaIngresoRecibo = function(dto) {
	var error = null;
	if (!$.validacionFecha(dto.fechaIngresoRecibo)) {
		error = new Object();
		error.campo = 'errorFechaIngresoRecibo';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	}
	return error;
};
var validacion_fechaIngresoReciboObligatorio = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaIngresoRecibo)) {
		error = new Object();
		error.campo = 'errorFechaIngresoRecibo';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaIngresoRecibo)) {
		error = new Object();
		error.campo = 'errorFechaIngresoRecibo';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	}
	return error;
};
var validacion_fechaVencimiento = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaVencimiento)) {
		error = new Object();
		error.campo = 'errorFechaVencimiento';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaVencimiento)) {
		error = new Object();
		error.campo = 'errorFechaVencimiento';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	}
	return error;
};
var validacion_cuit = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.cuit)) {
		if (!$.cuitValidarFormato(dto.cuit)) {
			error = new Object();
			error.campo = 'errorCuit';
			error.mensaje = errorLeyenda.formatoCuit;
		} else if(!$.cuitValidarDigitoVerificador(dto.cuit)) {
			error = new Object();
			error.campo = 'errorCuit';
			error.mensaje = errorLeyenda.digitoVerificadorCuit;
		} else if($.cuitValidarDosPrimerodDigitos(dto.cuit)) {
			error = new Object();
			error.campo = 'errorCuit';
			error.mensaje = errorLeyenda.dosPrimeroDigitosCuit;
		}
	}
	return error;
};

var validacion_cuitIbb= function(dto) {
	var error = null;
	if ("1" == dto.idTipoImpuesto) {
		if (!$.isEmpty(dto.cuitIbb)) {
			if (!$.cuitValidarFormato(dto.cuitIbb)) {
				error = new Object();
				error.campo = 'errorCuitIbb';
				error.mensaje = errorLeyenda.formatoCuit;
			} else if(!$.cuitValidarDigitoVerificador(dto.cuitIbb)) {
				error = new Object();
				error.campo = 'errorCuitIbb';
				error.mensaje = errorLeyenda.digitoVerificadorCuit;
			} else if($.cuitValidarDosPrimerodDigitos(dto.cuitIbb)) {
				error = new Object();
				error.campo = 'errorCuitIbb';
				error.mensaje = errorLeyenda.dosPrimeroDigitosCuit;
			}
		} else {
			error = new Object();
			error.campo = 'errorCuitIbb';
			error.mensaje = errorLeyenda.obligatorio;
		}
	}
	return error;
};

var validacion_fechaTransferencia = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaTransferencia)) {
		error = new Object();
		error.campo = 'errorFechaTransferencia';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaTransferencia)) {
		error = new Object();
		error.campo = 'errorFechaTransferencia';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	}
	return error;
};

var validacion_numeroReferencia = function(dto) {
	var error = null;
	if ($.isEmpty(dto.numeroReferencia)) {
		error = new Object();
		error.campo = 'errorNumeroReferencia';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.numeroReferencia)) {
		error = new Object();
		error.campo = 'errorNumeroReferencia';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};
//IdTipoImpuesto()

var validacion_idTipoImpuesto = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idTipoImpuesto)) {
		error = new Object();
		error.campo = 'errorIdTipoImpuesto';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
//NroConstancia
var validacion_nroConstancia = function(dto) {
	var error = null;
	
	if ($.isEmpty(dto.nroConstancia)) {
		error = new Object();
		error.campo = 'errorNroConstancia';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionIsNumeric(dto.nroConstancia)) {
		error = new Object();
		error.campo = 'errorNroConstancia';
		error.mensaje = errorLeyenda.numerico;
	}
	return error;
};
var validacion_fechaEmision = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaEmision)) {
		error = new Object();
		error.campo = 'errorFechaEmision';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaEmision)) {
		error = new Object();
		error.campo = 'errorFechaEmision';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	} else {
		var date = $( "#inputFechaEmision" ).datepicker( "getDate" ); 
		var dateNow = new Date();
		
		var dateNowSinTime = new Date(dateNow.getFullYear(), dateNow.getMonth(), dateNow.getDate());

		if (date.getTime() > dateNowSinTime.getTime()) {
			error = new Object();
			error.campo = 'errorFechaEmision';
			error.mensaje = errorLeyenda.fechaEmisionIncorrecto;
		} else {
			if ("1" == dto.idTipoImpuesto) {
				var tope = $('#selectProvincia option:selected').attr('tope');
				if (!$.isEmpty(tope)) {
					var fechaTope = new Date(dateNowSinTime.getFullYear(),dateNowSinTime.getMonth() - tope, dateNowSinTime.getDate());
					if (fechaTope.getTime() > date.getTime()) {
						error = new Object();
						error.campo = 'errorFechaEmision';
						error.mensaje = errorLeyenda.fechaEmision_provincia_meses + " " + tope + " meses.";
					}
				}
			}
		}
	}
	return error;
};




var validacion_idProvincia = function(dto) {
	var error = null;
	if ("1" == dto.idTipoImpuesto) {
		if ($.isEmpty(dto.idProvincia)) {
			error = new Object();
			error.campo = 'errorIdProvincia';
			error.mensaje = errorLeyenda.obligatorio;
		}
	}
	return error;
};

var validacion_idLetraComprobante = function(dto) {
	var error = null;
	if ("1" == dto.idTipoImpuesto) {
		if ($.isEmpty(dto.idLetraComprobante) && !$.isEmpty(dto.idTipoComprobante)) {
			error = new Object();
			error.campo = 'errorLetraComprobante';
			error.mensaje = errorLeyenda.obligatorio;
		}
	}
	return error;
};



var validacion_numeroLegalComprobante = function(dto) {
	var error = null;
	if ("1" == dto.idTipoImpuesto) {
		if ($.isEmpty(dto.numeroLegalComprobante) && !$.isEmpty(dto.idTipoComprobante)) {
			error = new Object();
			error.campo = 'errorNumeroLegalComprobante';
			error.mensaje = errorLeyenda.obligatorio;
		}
		if (!$.isEmpty(dto.numeroLegalComprobante) && !$.isFormatoRecibo(dto.numeroLegalComprobante)) {
			error = new Object();
			error.campo = 'errorNumeroLegalComprobante';
			error.mensaje = errorLeyenda.formatoRecibo;
		}
	}
	
	return error;
};

var validacionListaComprobantes = function(dto) {
	var error = null;
	if ($.isEmpty(dto.listaComprobantes)) {
		error = new Object();
		error.campo = 'errorArchComprobante';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
/**
 * u576779
 * Validacion de fecha de emision de cheque y fecha Notificacion
 * Req 67475
 */
var validacion_fechaEmisionCheque = function(dto) {
	var error = null;
	if ($.isEmpty(dto.fechaEmision)) {
		error = new Object();
		error.campo = 'errorinputFechaEmisionCheque';
		error.mensaje = errorLeyenda.obligatorio;
	} else if (!$.validacionFecha(dto.fechaEmision)) {
		error = new Object();
		error.campo = 'errorinputFechaEmisionCheque';
		error.mensaje = errorLeyenda.fechaIncorrecta;
	} else {
		// 
		var dateNow = new Date();
		
		var dateNowSinTime = new Date(dateNow.getFullYear(), dateNow.getMonth(), dateNow.getDate());

		if (dto.fechaEmisionAux.getTime() > dateNowSinTime.getTime()) {
			error = new Object();
			error.campo = 'errorinputFechaEmisionCheque';
			error.mensaje = errorLeyenda.fechaEmisionChequeIncorrecto;
		}
	}
	return error;
};

var validacion_fechaNotificacion = function(dto) {
	var error = null;
	if (!$.isEmpty(dto.fechaNotificacionDisponibilidadRetiroValor)) {
		if (!$.validacionFecha(dto.fechaNotificacionDisponibilidadRetiroValor)) {
			error = new Object();
			error.campo = 'errorinputFechaNotificacionValor';
			error.mensaje = errorLeyenda.fechaIncorrecta;
		} else {
			var dateNow = new Date();
			var dateNowSinTime = new Date(dateNow.getFullYear(), dateNow.getMonth(), dateNow.getDate());

			if (dto.fechaNotificacionDisponibilidadRetiroValorAux.getTime() > dateNowSinTime.getTime()) {
				error = new Object();
				error.campo = 'errorinputFechaNotificacionValor';
				error.mensaje = errorLeyenda.fechaNoSuperiorAldia;
			}
		}
	}
	return error;
};
/*******************************************************************************
 * 
 *   BOLETA
 ******************************************************************************/
var validacion_origen = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idOrigen )) {
		error = new Object();
		error.campo = 'errorOrigen';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
var validacion_idCopropietario = function(dto) {
	var error = null;
	if ($.isEmpty(dto.idCopropietario)) {
		error = new Object();
		error.campo = 'errorCopropietario';
		error.mensaje = errorLeyenda.obligatorio;
	}
	return error;
};
var validacion_radioBtn = function(dto) {
	var error = null;
	if (!dto.imprimirBoleta && !dto.enviarBoletaMail) {
		error = new Object();
		error.campo = 'errorCheck';
		error.mensaje = errorLeyenda.radioBtnBoletaCV;
	}
	return error;
};
var validacion_email_boleta = function(dto) {
	var error = null;
	if (dto.enviarBoletaMail) {
		if ($.isEmpty(dto.email)) {
			error = new Object();
			error.campo = 'errorEmail';
			error.mensaje = errorLeyenda.obligatorio;
			return error;
		}
	} 
	if (!$.isEmpty(dto.email)) {
		if (!$.isNullOrEmail(dto.email)) {
			error = new Object();
			error.campo = 'errorEmail';
			error.mensaje = errorLeyenda.formatoEmail;
		}
	}
	return error;
};
function crearValidadorAltaValorXAvc(dto){
	errorLeyenda.obligatorio = 'Este campo es requerido.';
	errorLeyenda.fechaEmisionIncorrecto = 'El campo Fecha de Retención no debe ser posterior al día de hoy.';
	errorLeyenda.fechaEmisionChequeIncorrecto = 'El campo Fecha de Emision no debe ser posterior al día de hoy.';
	errorLeyenda.fechaIncorrecta='Este campo debe respetar el formato DD/MM/AAAA.';
	errorLeyenda.fechaNoSuperiorAldia=  'La fecha no puede ser superior al la del día.';
	errorLeyenda.formatoTexto = 'Este campo debe contener un formato de texto correcto.';
	
	
	
	
	var validador = new Object();
	var tipo = dto.idTipoValor;
	
	if (
		TipoValorEnum.CHEQUE.idTipoValor.toString() === tipo ||
		TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === tipo
	) {
		
		validador.validacion_fechaEmisionCheque = validacion_fechaEmisionCheque;
		if (dto.idOrigen == '7') {
			validador.validacion_idAcuerdo = validacion_idAcuerdo;
			validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
			validador.validacion_idNroCuenta = validacion_idNroCuenta;
		}
	}
	
	if (TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() !== tipo) {	
		validador.validacion_fechaNotificacion = validacion_fechaNotificacion;
	}
	
	validador.validacion_Cliente = validacion_Cliente;
	validador.validacion_segmento = validacion_segmento;
	validador.validar_observaciones = validar_observaciones;
	return validador;
}
function crearValidador(dto) {
	var tipo = dto.idTipoValor;
	errorLeyenda.obligatorio = 'Este campo es requerido.';
	errorLeyenda.numerico = 'Este campo debe contener valores num\u00E9ricos.';
	errorLeyenda.boletaAltaMensajeSiebelNoValidado = 'El cliente no fue validado contra el servicio de Consulta de Clientes.';
	errorLeyenda.formatoEmail = 'Este campo debe respetar el formato mínimo xxx@y.com';
	errorLeyenda.formatoTexto = 'Este campo debe contener un formato de texto correcto.';
	errorLeyenda.numeroConDosDigitos = 'Este campo debe respetar el formato 999.999.999,99';
	errorLeyenda.msgErrorInputLength = 'Este campo debe tener como maximo 9 digitos mas 2 decimales';
	errorLeyenda.formatoRecibo= 'Este campo debe respetar el formato nnnn-nnnnnnnn.';
	errorLeyenda.fechaIncorrecta='Este campo debe respetar el formato DD/MM/AAAA.';
	errorLeyenda.formatoCuit = 'Este campo debe respetar los formato 99-99999999-9 o 99999999999.';
	errorLeyenda.digitoVerificadorCuit = 'El digito verificador es incorrecto.';
	errorLeyenda.dosPrimeroDigitosCuit = 'Los primeros 2 digitos son incorrectos.';
	errorLeyenda.fechaEmisionIncorrecto = 'El campo Fecha de Retención no debe ser posterior al día de hoy.';
	errorLeyenda.fechaEmisionChequeIncorrecto = 'El campo Fecha de Emision no debe ser posterior al día de hoy.';
	errorLeyenda.fechaEmision_provincia_meses='Para la Provincia seleccionada, el período entre la Fecha de Emisión y la fecha actual, no puede ser mayor a ';
	errorLeyenda.valorCero = 'Este campo debe ser distinto de 0.';
	errorLeyenda.radioBtnBoletaCV = 'Se debe seleccionar una opción.';
	errorLeyenda.fechaNoSuperiorAldia=  'La fecha no puede ser superior al la del día.';
	var validador = new Object();

	// Validaciones Generales del valor
	validador.validacion_tipoValor = validacion_tipoValor;

	validador.validacion_fechaNotificacion = validacion_fechaNotificacion;

	if (
			TipoValorEnum.CHEQUE.idTipoValor.toString() === tipo ||
			TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === tipo ||
			TipoValorEnum.EFECTIVO.idTipoValor.toString() === tipo ||
			TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() === tipo ||
			TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() === tipo
	) {	
		validador.validacionListaComprobantes = validacionListaComprobantes;
		validador.validacion_segmento = validacion_segmento;
		validador.validacion_empresa = validacion_empresa;
		// u578936 M.A.Uehara Validacion del cliente para la nueva seleccion de cliente
		// 67475 Fusión paquete valores
		validador.validacion_Cliente = validacion_Cliente;
		validador.validacion_email = validacion_email;
		validador.validacion_analista = validacion_analista;
		validador.validar_observaciones = validar_observaciones;
		validador.validacion_operacionAsociada = validacion_operacionAsociada;
		validador.validacion_reciboPreImpreso = validacion_reciboPreImpreso;
		validador.validacion_importe = validacion_importe;
	} else if(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.idTipoValor.toString() === tipo
			  || TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === tipo
			  || TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.idTipoValor.toString() === tipo) {
		if( !isUndefinedNullDashEmptyOrFalse(dto.fechaNotificacionDisponibilidadRetiroValor) ) {
			validador.validacionListaComprobantes = validacionListaComprobantes;
		}
		validador.validacion_segmento = validacion_segmento;//Si
		validador.validacion_empresa = validacion_empresa;// Si
		// u578936 M.A.Uehara Validacion del cliente para la nueva seleccion de cliente
		// 67475 Fusión paquete valores
		validador.validacion_Cliente = validacion_Cliente;
		validador.validacion_analista = validacion_analista;//Sio
		validador.validar_observaciones = validar_observaciones;// Si
		
		validador.validacion_importe = validacion_importe; //ASi
		validador.validacion_radioBtn = validacion_radioBtn;
		// Banco Acuerdo cuenta
		validador.validacion_idAcuerdo = validacion_idAcuerdo;		//Si
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;//Si
		validador.validacion_idNroCuenta = validacion_idNroCuenta;//Si
		// origen
		validador.validacion_origen = validacion_origen;	// Si
		//
		validador.validacion_email_boleta = validacion_email_boleta;//Si
		
		if (!dto.cajeroPagador) {
			validador.validacion_operacionAsociada = validacion_operacionAsociada;//Si
			validador.validacion_fechaNotificacion = validacion_fechaNotificacion;
		} else {
			validador.validacion_operacionAsociadaObligatorio = validacion_operacionAsociadaObligatorio;//Si
			validador.validacion_idCopropietario  = validacion_idCopropietario;//Si
		}
		validador.validacion_email = validacion_email;//Si
		// 3 => Oficina Recibo Preimpreso
		if (dto.idOrigen == 3) { 
			validador.validacion_fechaIngresoReciboObligatorio = validacion_fechaIngresoReciboObligatorio;	//Si
			validador.validacion_reciboPreImpreso = validacion_reciboPreImpreso;	//Si
			validador.validacion_fechaNotificacion = validacion_fechaNotificacion;
		}
	} else {
		validador.validacion_segmento = validacion_segmento;
		validador.validacion_empresa = validacion_empresa;
		// u578936 M.A. Uehara Boleta Sin valor. No se va Modificar la seleccion del cliente por el momento
		validador.validacion_codCliente = validacion_codCliente;
		validador.validacion_analista = validacion_analista;
		validador.validar_observaciones = validar_observaciones;
		validador.validacion_importe = validacion_importe;
		validador.validacion_radioBtn = validacion_radioBtn;
		// Banco Acuerdo cuenta
		validador.validacion_idAcuerdo = validacion_idAcuerdo;
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
		// origen
		validador.validacion_origen = validacion_origen;
		//
		validador.validacion_email_boleta = validacion_email_boleta;
		validador.validar_observacionesEmail_boleta = validar_observacionesEmail_boleta;
		validador.validacion_operacionAsociada = validacion_operacionAsociada;
	}

	if (TipoValorEnum.CHEQUE.idTipoValor.toString() === tipo) {
		// CHEQUE
		//validador.cheque = new Object();
		validador.validacion_idAcuerdo = validacion_idAcuerdo;
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
		
		validador.validacion_idBancoOrigen = validacion_idBancoOrigen;
		validador.validacion_numeroCheque = validacion_numeroCheque;
		validador.validacion_fechaDeposito = validacion_fechaDeposito;
		validador.validacion_numeroDocumentoContable = validacion_numeroDocumentoContable;
		validador.validacion_numeroBoletaNumerico = validacion_numeroBoletaNumerico;
		validador.validacion_fechaIngresoRecibo = validacion_fechaIngresoRecibo;
		validador.validacion_fechaEmisionCheque = validacion_fechaEmisionCheque;
	} else if (TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === tipo) {
		//validador.chequeDf = new Object();
		validador.validacion_idAcuerdo = validacion_idAcuerdo;
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
		validador.validacion_idBancoOrigen = validacion_idBancoOrigen;
		validador.validacion_numeroCheque = validacion_numeroCheque;
		validador.validacion_fechaVencimiento = validacion_fechaVencimiento;
		validador.validacion_fechaDeposito = validacion_fechaDeposito;
		validador.validacion_numeroDocumentoContable = validacion_numeroDocumentoContable;
		validador.validacion_numeroBoletaNumerico = validacion_numeroBoletaNumerico;
		validador.validacion_fechaIngresoRecibo = validacion_fechaIngresoRecibo;
		validador.validacion_fechaEmisionCheque = validacion_fechaEmisionCheque;
	} else if (TipoValorEnum.EFECTIVO.idTipoValor.toString() === tipo) {
		//validador.efectivo = new Object();
		validador.validacion_idAcuerdo = validacion_idAcuerdo;
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
		
		validador.validacion_fechaDeposito = validacion_fechaDeposito;
		validador.validacion_numeroDocumentoContable = validacion_numeroDocumentoContable;
		validador.validacion_numeroBoleta = validacion_numeroBoleta;
		validador.validacion_fechaIngresoRecibo = validacion_fechaIngresoRecibo;
	} else if (TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() === tipo) {
		//validador = new Object();
		validador.validacion_cuit = validacion_cuit;
		validador.validacion_idAcuerdo = validacion_idAcuerdo;
		validador.validacion_idBancoDeposito = validacion_idBancoDeposito;
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
		validador.validacion_idBancoOrigen = validacion_idBancoOrigen;
		validador.validacion_numeroDocumentoContable = validacion_numeroDocumentoContable;
		validador.validacion_fechaTransferencia = validacion_fechaTransferencia;
		validador.validacion_numeroReferencia = validacion_numeroReferencia;
	} else if (TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() === tipo) {
		//validador.retencion = new Object();
		validador.validacion_idTipoImpuesto = validacion_idTipoImpuesto;
		validador.validacion_fechaEmision = validacion_fechaEmision;
		validador.validacion_fechaIngresoReciboObligatorio = validacion_fechaIngresoReciboObligatorio;
		validador.validacion_cuitIbb = validacion_cuitIbb;
		validador.validacion_idProvincia = validacion_idProvincia;
		validador.validacion_idLetraComprobante = validacion_idLetraComprobante;
		validador.validacion_numeroLegalComprobante = validacion_numeroLegalComprobante;
		validador.validacion_nroConstancia = validacion_nroConstancia;
	} else if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.idTipoValor.toString() === tipo) {
		validador.validacion_idBancoOrigen = validacion_idBancoOrigen;
		validador.validacion_numeroCheque = validacion_numeroCheque;
		validador.validacion_fechaEmisionCheque = validacion_fechaEmisionCheque;
	} else if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === tipo) {
		validador.validacion_idBancoOrigen = validacion_idBancoOrigen;
		validador.validacion_numeroCheque = validacion_numeroCheque;
		validador.validacion_fechaVencimiento = validacion_fechaVencimiento;
		validador.validacion_fechaNotificacion = validacion_fechaNotificacion;
		validador.validacion_fechaEmisionCheque = validacion_fechaEmisionCheque;
	} else if (TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.idTipoValor.toString() === tipo) {
		validador.validacion_idNroCuenta = validacion_idNroCuenta;
	}
	return validador;
}


function validarDuplicidad(lista, dto) {
	var size = null;
	var validador = null;
	var mensaje = '';
	var mensajePrefix = '';

	if (!$.isEmpty(lista)) {
		size = lista.length;
		var elemento = null;
		for (var index = 0; index < size; index++) {
			elemento = lista[index];
			// CHEQUE
			if (TipoValorEnum.CHEQUE.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.idBancoOrigen === elemento.idBancoOrigen &&
					dto.numeroCheque === elemento.numeroCheque &&
					dto.importe === elemento.importe &&
					dto.fechaDeposito === elemento.fechaDeposito
				) {
					mensaje = " Nro. Cheque " + dto.numeroCheque + " para Banco Origen " + dto.bancoOrigen + " con Importe " + dto.importe + " y Fecha Depósito " + dto.fechaDeposito + "";
					mensajePrefix = 'El aviso de pago no puede agregarse a la grilla "Avisos de pago configurados" debido a la existencia de datos duplicados: ';
					break;
				}
			} else if (TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.idBancoOrigen === elemento.idBancoOrigen &&
					dto.numeroCheque === elemento.numeroCheque &&
					dto.importe === elemento.importe &&
					dto.fechaDeposito === elemento.fechaDeposito &&
					dto.fechaVencimiento === elemento.fechaVencimiento
				) {
					mensaje =  " Nro. Cheque " + dto.numeroCheque + " para Banco Origen " + dto.bancoOrigen + " con Importe " + dto.importe + " con Fecha Depósito " + dto.fechaDeposito + " y Fecha Vencimiento " + dto.fechaVencimiento;
					mensajePrefix = 'El aviso de pago no puede agregarse a la grilla "Avisos de pago configurados" debido a la existencia de datos duplicados: ';
					break;
				}
				//VALOR_PARA_TRANSFERENCIA
			} else if (TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.numeroReferencia === elemento.numeroReferencia &&
					dto.fechaTransferencia === elemento.fechaTransferencia &&
					dto.importe === elemento.importe &&
					dto.idAcuerdo === elemento.idAcuerdo
				) {
					mensaje = "Nro. Referencia " + dto.numeroReferencia	+ " para Fecha de Transferencia " + dto.fechaTransferencia + " con Importe " + dto.importe + " y Acuerdo " + dto.idAcuerdo + "";
					mensajePrefix = 'El aviso de pago no puede agregarse a la grilla "Avisos de pago configurados" debido a la existencia de datos duplicados: ';
					break;
				}
				
			} else if (TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.tipoImpuesto === elemento.tipoImpuesto &&
					dto.nroConstancia === elemento.nroConstancia &&
					dto.codCliente === elemento.codCliente
				) {
					mensaje = "Nro. Constancia " + dto.nroConstancia + " para Tipo de Retención " + dto.tipoImpuesto + " para el Cliente " + dto.codCliente;
					mensajePrefix = 'El aviso de pago no puede agregarse a la grilla "Avisos de pago configurados" debido a la existencia de datos duplicados: ';
					break;
				}
				//
			} else if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.idBancoOrigen === elemento.idBancoOrigen &&
					dto.numeroCheque === elemento.numeroCheque &&
					dto.importe === elemento.importe 
				) {
					mensaje = " Nro. Cheque " + dto.numeroCheque + " para Banco Origen " + dto.bancoOrigen + " con Importe " + dto.importe + "";
					mensajePrefix = 'No se puede proceder al alta del registro debido a la existencia de duplicidades: ';
					break;
					
				}
			} else if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === dto.idTipoValor) {
				if (
					dto.idBancoOrigen === elemento.idBancoOrigen &&
					dto.numeroCheque === elemento.numeroCheque &&
					dto.importe === elemento.importe &&
					dto.fechaVencimiento === elemento.fechaVencimiento
				) {
					mensaje =  " Nro. Cheque " + dto.numeroCheque + " para Banco Origen " + dto.bancoOrigen + " con Importe " + dto.importe + " y Fecha Vencimiento " + dto.fechaVencimiento;
					mensajePrefix = 'No se puede proceder al alta del registro debido a la existencia de duplicidades: ';
					break;
				}
			} 
			
		}
	}
	if (!$.isEmpty(mensaje)) {
		validador = new Object();
		validador.mensaje = mensajePrefix  + mensaje;
	}
	return validador;
}
