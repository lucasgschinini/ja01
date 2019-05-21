var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100;
var CANTIDAD_POR_PAGINA_DEB = 80; // 80
var CANTIDAD_POR_PAGINA_CRED = 100; // 100
var CANTIDAD_POR_PAGINA_CAP = 2;

var CANTIDAD_MAX_DEBITOS = 1500;
var CANTIDAD_MAX_CAPS = 1500;
var CANTIDAD_MAX_OTROS_DEBITOS = 1500;
var DEB_COL_POS_IMPORTE = 17;
var DEB_COL_POS_MENSAJE = 33;
var OTRO_DEB_COL_POS_IMPORTE = 0;
var OTRO_DEB_COL_POS_MENSAJE = 13;
var CRED_COL_POS_IMPORTE = 15;
var CRED_COL_POS_MENSAJE = 34;
var TR_HEIGHT = 'TR_HEIGHT';
var TR_HEIGHT_ERROR = 'TR_HEIGHT_ERROR';
var TR_HEIGHT_VALOR = 37;
var TR_HEIGHT_ERROR_VALOR = 46;

var validacionOkFecha = true;
var verHistorial = false;
var simulacionOK = false;
var transaccionesOK = false;

var TRANS_COL_POS_TRAS_INTERESES = 25;
var TRANS_COL_POS_PORCENTAJE = 26;
var TRANS_COL_POS_IMPORTE = 27;
var TRANS_COL_POS_ESTADO = 29;
var TRANS_COL_POS_MENSAJE = 30;
var TRANS_COL_POS_INTERES = 24;
var TRANS_COL_POS_INTERES_BONIF = 27;
var MAX_LENGHT_OBSERVACIONES = 250;
var validadarFormOtrosDev = new Object();

var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var ICON_BLANK = 'class="icon-plus bigger-120 icon-hidden"';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_MINUS = '<i class="icon-minus bigger-120"></i>';
var CSS_SELECTED_ROW = 'LightGray';
var BTN_NO_CLASS = 'btn-no-class'; // No es una clase CSS se utiliza como
// selector
var GESTIONABILIDAD_NO_CLASS = 'gestion-semaforo-no-class-';

var varEdicionSegunEstadoMarca = '';
var KEY_BS_RETROCESO = '8';
var KEY_BS_TAB = 9;
var KEY_BS_ENTER = 13;

var COMPENSACION = '9';
var COBRO_SALIDA_AUTOMATICA_VALORES = '10';
var OPERACION_MASIVA = [ '1', '2', '3' ];

var btnSimularClicked = false;
var anularGuardar = false;
var diferencia = '0,00';
var esBtnGuardar = false;
var _monedaDelCobro = '';
var _clienteSeleccionadoOtroDebito = '';

var motivoPrevio = '';
var motivoNuevo = '';
var fechaCotizPrev = '';

var trCapsBackgroundColor = '';
var trCapsSeleBackgroundColor = '';
var trCapsPreBackgroundColor = '';

var WiDTH_CHECK = 40;
var WiDTH_SEMAFORO = 70;

// Constantes para las solapas
var CONF_COBRO_SIGUIENTE_TAB = '#conf-cobro-tabs-t-';
var CONF_COBRO_TAB_CLIENTE = '#conf-cobro-tabs-t-0';
var CONF_COBRO_TAB_DEB = '#conf-cobro-tabs-t-1';
var CONF_COBRO_TAB_OTROS_DEB = '#conf-cobro-tabs-t-2';
var CONF_COBRO_TAB_CRED = '#conf-cobro-tabs-t-3';
var CONF_COBRO_TAB_MED_PAG = '#conf-cobro-tabs-t-4';
var CONF_COBRO_TAB_COMPROBANTE = '#conf-cobro-tabs-t-5';
var CONF_COBRO_TAB_PREV = '#conf-cobro-tabs-t-6';




var listaSolapas = [ CONF_COBRO_TAB_CLIENTE, CONF_COBRO_TAB_DEB, CONF_COBRO_TAB_OTROS_DEB, CONF_COBRO_TAB_CRED, CONF_COBRO_TAB_MED_PAG, CONF_COBRO_TAB_COMPROBANTE, CONF_COBRO_TAB_PREV ];

var INDICE_TAB_CLIENTE = 0;
var INDICE_TAB_DEBITOS = 1;
var INDICE_TAB_OTROS_DEBITOS = 2;
var INDICE_TAB_CREDITOS = 3;
var INDICE_TAB_MED_PAG = 4;
var INDICE_TAB_COMPROBANTE = 5;
var INDICE_TAB_PREV = 6;

var errorSolapaOtrosDeb = true;
var _clienteSeleccionadoOtroDebito = '';
// Control de colores de grilla de transaccion
var rowColorDef = {
		'Autom' : {
			'odd' : {
				'border_top' : '',
				'background_color' : '#FFFFFF'
			},
			'pair' : {
				'border_top' : '',
				'background_color' : '#F5F5F5'
			}
		},
		'Man_odd' : {
			'odd' : {
				'border_top' : '',
				'background_color' : '#EAF0FC'
			},
			'pair' : {
				'border_top' : '',
				'background_color' : '#DAE6FC'
			}
		},
		//D1DEF5
		//AFC6ED
//		'Man_pair' : {
//			'odd' : {
//				'border_top' : '',
//				'background_color' : '#EBF1FB'
//			},
//			'pair' : {
//				'border_top' : '',
//				'background_color' : '#E6F2FF'
//			}
//		},
		'Man_pair' : {
			'odd' : {
				'border_top' : '',
				'background_color' : '#FFFFFF'
			},
			'pair' : {
				'border_top' : '',
				'background_color' : '#F5F5F5'
			}
		},
		'grupoCurrent' : ''
	};

var TRASLADA_INTERESES_LECTURA = 4;
var TRASLADA_INTERESES_NO = 0;
var TRASLADA_INTERESES_DIFERENCIA = 3;
var TRASLADA_INTERESES = 2;
var TRASLADA_ASTERISCOS = 5;


var tablas = {
	// Tab Clientes
	clientes : null,
	clientesSeleccionados : null,
	// Tab Debitos
	clientesDebitos : null,
	debitos : null,
	debitosSeleccionados : null,
	// Tab Creditos
	clientesCreditos : null,
	creditos : null,
	creditosSeleccionados : null,
	// Tab Medios de Pago
	clientesMedios : null,
	mediosPagos : null,
	comprobantes : null,
	// Sprint 4 Compensaciones
	documentosCaps : null,
	fixedColumnsDocumentosCaps : false,
	documentosCapsSeleccionados : null,
	fixedColumnsDocumentosCapsSeleccionados : false,
	// Tab Previsualizacion
	clientesPrev : null,
	debitosPrev : null,
	creditosPrev : null,
	mediosPagosPrev : null,
	documentosCapPrev : null,
	transaccionesPrev : null,
	comprobantesPrev : null,
	comprobantesAplManual : null,
	comprobantesOtrosDebito : null,
	codigoOperacionesExternas : null,
	clientesOtrosDebitos : null,
	otrosDebSeleccionados : null,
	otrosDebitosPrev : null
};

var datosTablas = {
	clientesSeleccionados : [],
	debitosSeleccionados : [],
	creditosSeleccionados : [],
	mediosPagos : [],
	transacciones : [],
	comprobantes : [],
	tratamientoDiferencia : [],
	// Sprint 4 Compensaciones
	documentosCapsSeleccionados : [],
	documentosCaps : [],
	comprobantesAplManual : [],
	comprobantesOtrosDebito : [],
	codigoOperacionesExternas : [],
	otrosDebitosSeleccionados : [],
	idsAdjuntosOtrosDeb : []
};

function updateCheckValue(object, attr) {
	var checked = $(object).is(":checked");
	var aPos = tablas.debitosSeleccionados.fnGetPosition($(object).parents('td')[0]);
	tablas.debitosSeleccionados.fnGetData(aPos[0])[attr] = checked;
	datosTablas.debitosSeleccionados[aPos[0]][attr] = checked;
}

var eventosTablas = {
	'clientes' : 1,
	'debitos' : 1,
	'creditos' : 1,
	'isMomentoInicial' : function(nombreTabla) {
		if (this[nombreTabla] == 1) {
			this[nombreTabla]++;
			return true;
		}
		return false;
	}
};

function debitoCheckSaldoMaximo(object, attr) {
	$('#bloqueEspera').trigger('click');
	var checked = $(object).is(":checked");
	var aPos = tablas.debitosSeleccionados.fnGetPosition($(object).parents('td')[0]);
	tablas.debitosSeleccionados.fnGetData(aPos[0])[attr] = checked;
	datosTablas.debitosSeleccionados[aPos[0]][attr] = checked;

	var data = datosTablas.debitosSeleccionados[aPos[0]];
	var input = $('input:text', tablas.debitosSeleccionados.fnGetNodes(aPos[0]))[0];
	var update = false;
	var disable = false;

	if (MIC_NAME === data['sistemaOrigen']) {
		if (data['cobrarAl2doVenc']) {
			disable = update = true;
			if (data['destransferir3ros']) {
				//
				// Caso 04: SI cobrar al 2do Vencimiento y SI destransferir a
				// 3ros
				// 
				$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso04'], null));
			} else {
				//
				// Caso 03: SI cobrar al 2do Vencimiento y NO destransferir a
				// 3ros
				//
				$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso03'], null));
			}
		} else {
			if (data['destransferir3ros']) {
				disable = update = true;
				//
				// NO cobrar al 2do Vencimiento y SI destransferir a 3ros
				// 
				$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso02']), null);
			} else {
				update = true;
				$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso01']), null);
			}
		}

		if (!data['cobrarAl2doVenc'] && !data['destransferir3ros']) {
			$(input).val(truncoADosDecimalesImporte(data['saldoMaxDefaultCalculado'], null));
			updateValue(datosTablas.debitosSeleccionados, $(input).val(), aPos[0], 'impACobrar');
		}

	} else if (CALIPSO_NAME === data['sistemaOrigen']) {
		if (data['sinDifDeCambio']) {
			update = true;
			// disable = update = true;
			//
			// Caso 05: SI sin diferencia cambio
			// 
			$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso05'], null));
		} else if (data['moneda'] !== _monedaDelCobro && COMPENSACION === $('#selectMotivo').val()) {
			$(input).val(truncoADosDecimalesImporte(data['saldoMaxCaso06'], null));
			updateValue(datosTablas.debitosSeleccionados, $(input).val(), aPos[0], 'impACobrar');
		} else {
			$(input).val(truncoADosDecimalesImporte(data['saldoMaxDefaultCalculado'], null));
			updateValue(datosTablas.debitosSeleccionados, $(input).val(), aPos[0], 'impACobrar');
		}
	}
	var debito = tablas.debitosSeleccionados.fnGetData(aPos[0]);
	$(input).attr('disabled', disable);
	if (update) {
		updateValue(datosTablas.debitosSeleccionados, $(input).val(), aPos[0], 'impACobrar');
		updateValue(datosTablas.debitosSeleccionados, $(input).val(), aPos[0], 'saldoMaxDefault');
	}
	validarImporteIngresado(input, tablas.debitosSeleccionados, datosTablas.debitosSeleccionados, DEB_COL_POS_IMPORTE, 'impACobrar', 'moneda', DEB_COL_POS_MENSAJE, 'descripcionErrorValidacion', 'saldoMaxDefault', 'btnClonedDeb' + debito.idPantalla, true);

	totalDeDocumentos.actualizar();
	ocultarBloqueEspera();
}

// u573005, sprint 5
function checkTrasladarIntereses(object, attr) {
	var checked = $(object).is(":checked");
	var tabla = tablas.transaccionesPrev;
	var aPos = tabla.fnGetPosition($(object).parents('tr')[0]);
	var dataTabla = tabla.fnGetData(aPos);
	var data = datosTablas.transacciones[aPos];
	
	
	var interes = data['intereses'].replace(data['moneda'], "");
	var porcABonificar = dataTabla['porcABonificar'];
	var importeABonificar = dataTabla['importeABonificar'];
	var porcABonificarObj = $(object).parents('tr').children().find('.clasePorcABonificar');
	var importeABonificarObj = $(object).parents('tr').children().find('.claseImporteABonificar');

	var imputTransPorcABonificar = $(object).closest('tr').find('input[id="transPorcABonificar"]');
	var imputTransImporteABonificar = $(object).closest('tr').find('input[id="transImporteABonificar"]');
	var spamErrorTransPorcABonificar = imputTransPorcABonificar.closest('div').find('span');
	var spamErrorTransImporteABonificar = imputTransImporteABonificar.closest('div').find('span');

	setMensajeError(imputTransPorcABonificar, '', '', 'porcABonificar', '');
	setMensajeError(imputTransImporteABonificar, '', '', 'importeABonificar', '');
	

	if (!$.isEmpty(spamErrorTransPorcABonificar.text())) {
		contadorMensajesError.restarError(spamErrorTransPorcABonificar.text().trim());
	}
	if (!$.isEmpty(spamErrorTransImporteABonificar.text())) {
		contadorMensajesError.restarError(spamErrorTransImporteABonificar.text().trim());
	}
	
	if ($(object).attr('mostrarAsteriscos') == 'SI') {
		if (checked) {
			$(porcABonificarObj).val("");
			
		} else {
			$(porcABonificarObj).val("100");
		}
	} else {
		if (checked) {
			// se traslan intereses
			if ('' !== interes && '-' !== interes) {
				if (data.numeroGrupo != '0') {
					// cargo los datos
					porcABonificar = '';
					importeABonificar = '';

					// borro los input
					$(porcABonificarObj).val('');
					$(importeABonificarObj).val('');

					// deshabilito los campos
					$(porcABonificarObj).attr('disabled', true);
					$(importeABonificarObj).attr('disabled', true);
					
				} else {
					// cargo los datos
					porcABonificar = '0';
					importeABonificar = '0,00';

					// borro los input
					$(porcABonificarObj).val('0');
					$(importeABonificarObj).val('0,00');

					// deshabilito los campos
					$(porcABonificarObj).attr('disabled', true);
					$(importeABonificarObj).attr('disabled', true);
				}
			}
		} else {
			// Se bonifican intereses
			if ('' !== interes && '-' !== interes) {
				// cargo los datos
				var porcCompleto = '100';
				porcABonificar = porcCompleto;
				importeABonificar = interes;

				if (data.numeroGrupo != '0') {
					porcCompleto = '';
					porcABonificar = '';
					importeABonificar = '';
					interes = '';
					$(importeABonificarObj).val('');
				} else {
					$(importeABonificarObj).val(formatear(importeToFloat(interes)));
				}

				// cargo los input
				$(porcABonificarObj).val(porcCompleto);
				

				// No habilito los campos si es proxima factura
				if (REINTEGRO_CRED_PROX_FAC_SUBTIPO === data.subtipoDocumento || DEBITO_PROX_FAC_DESC === data.tipoMedioPago || '0' != data.numeroGrupo) {
					$(porcABonificarObj).attr('disabled', true);
					$(importeABonificarObj).attr('disabled', true);
				} else {
					$(porcABonificarObj).attr('disabled', false);
					$(importeABonificarObj).attr('disabled', false);
				}
			}
		}
		updateValue(datosTablas.transacciones, porcABonificar, aPos, 'porcABonificar');
		updateValue(datosTablas.transacciones, importeABonificar, aPos, 'importeABonificar');
		
	
	}
	updateValue(datosTablas.transacciones, checked, aPos, attr);
	// se actualizan los datos en sesion
	

	// actualizo los totales
	totalInteresesTransacciones.actualizar();
}

function updateValue(dataTable, value, rowPos, attr) {
	dataTable[rowPos][attr] = value;
}

// function actualizarInfoTabla(tabla, resultado, idCustomPag,
// idBtnAgregarTodos,
// idMuestroError, cantPorPag) {
// var pgActual = 0;
// var cantMostrados = resultado.controlPaginacion.cantRegistrosMostrados;
// var total = resultado.controlPaginacion.totalRegistros;
// var infoIni = 0;
// var infoFin = 0;
//
// if (cantMostrados > 0) {
// pgActual = resultado.controlPaginacion.paginaActual;
// cantMostrados = resultado.controlPaginacion.cantRegistrosMostrados;
// infoIni = ((pgActual - 1) * cantPorPag) + 1;
// infoFin = ((pgActual - 1) * cantPorPag) + cantMostrados;
// }
//
// $('#' + idCustomPag + '_info span').text(
// 'Mostrando registros del ' + infoIni + ' al ' + infoFin + ' de '
// + total + ' registros en total');
//
// if (resultado.controlPaginacion.inhabilitarAnt) {
// $('#' + idCustomPag + '_previous').removeClass('active').addClass(
// 'disabled');
// } else {
// $('#' + idCustomPag + '_previous').removeClass('disabled').addClass(
// 'active');
// }
//
// if (resultado.controlPaginacion.inhabilitarSig) {
// $('#' + idCustomPag + '_next').removeClass('active').addClass(
// 'disabled');
// } else {
// $('#' + idCustomPag + '_next').removeClass('disabled').addClass(
// 'active');
// }
//
// tabla.fnClearTable();
// if (resultado.resultado.length > 0) {
// tabla.fnAddData(resultado.resultado, true);
// aparienciaButton.ocultarButtonDocumentosSeleccionados(tabla,
// idBtnAgregarTodos);
// $('#' + idBtnAgregarTodos).attr('disabled', false);
// } else {
// $('#' + idBtnAgregarTodos).attr('disabled', true);
// }
// errorMensaje = resultado.errorMensaje;
// if (errorMensaje !== null) {
// // Mostrar el error
// $('#' + idMuestroError).html(errorMensaje).addClass("error");
// } else {
// $('#' + idMuestroError).html("");
// }
//
// var informacionMensaje = resultado.informacionMensaje;
// if (informacionMensaje !== null) {
// // Mostrar la informacion en color verde
// $('#informacionRespuestaDebitos').text(informacionMensaje);
// } else {
// $('#informacionRespuestaDebitos').text("");
// }
//
// }

function updateValueImporte(dataTable, value, rowPos, attr, input) {
	var valueFloat = importeToFloat(value);

	updateValue(dataTable, formatear(valueFloat), rowPos, attr);
	if (input != null) {
		$(input).val(formatear(valueFloat));
	}
}

var msgErrorInputNulo = 'Este campo no puede ser nulo.';
var msgErrorInputNoValido = 'Este campo no puede ser cero o menor que cero.';
var msgErrorInput = 'Este campo debe respetar el formato 999.999.999,99 o 999999999,99';
var msgErrorInputLength = 'Este campo debe tener como maximo 9 digitos mas 2 decimales';
var msgErrorSuperaMaximoPermitido = 'Este campo no debe superar el máximo permitido (';
var msgErrorInputPorcentaje = 'Este campo debe contener un valor entre 1 y 100.';

function setMensajeError(input, mensaje, tipoRow, nombreColImp, idButton) {
	if (mensaje == '') {
		$(input).next('div').html('');
	} else {
		$(input).next('div').html('<span class="errorGrillaEditable">' + mensaje + '</span>');
		contadorMensajesError.sumarErrorByDescripcion(mensaje);
		if (nombreColImp == 'importe') {
			contadorMensajesErrorGuardableOtrosDebitos.sumarErrorGuardableByDescripcion(mensaje);
		} else if (nombreColImp == 'impACobrar') {
			contadorMensajesErrorGuardableDebitos.sumarErrorGuardableByDescripcion(mensaje);
		} else {
			contadorMensajesErrorGuardableCreditos.sumarErrorGuardableByDescripcion(mensaje);
		}
	}
	if (nombreColImp === 'impACobrar' || nombreColImp === "importeAUtilizar") {
		var lengthRowR = 0;

		if (nombreColImp == 'impACobrar') {
			lengthRowR = $('#debitosSeleccionados ' + '#' + idButton).closest('tr').height();
		} else {
			lengthRowR = $('#creditosSeleccionados ' + '#' + idButton).closest('tr').height();
		}
		if (tipoRow == TR_HEIGHT) {
			var cantRenglones = 0;
			if (nombreColImp == 'impACobrar') {
				// el td numero 25 corresponde a la columna "Operación/Analista"
				cantRenglones = $('#debitosSeleccionados ' + '#' + idButton).closest('tr').find('td:eq(25)').text().split(',').length;
			} else {
				// el td numero 32 corresponde a la columna "Operación/Analista"
				cantRenglones = $('#creditosSeleccionados ' + '#' + idButton).closest('tr').find('td:eq(32)').text().split(',').length;
			}
			if (cantRenglones < 3) {
				// si el tamaño del campo Operacion/analista es menor a 3 lines
				// de contenido
				// se debe modificar el valor del height del row. Cuando no
				// contiene el error
				lengthRowR = TR_HEIGHT_VALOR;
			}
		}
		$($('table tr ' + '#' + idButton)[0]).closest('tr').height(lengthRowR);
		$($('table tr ' + '#' + idButton)[1]).closest('tr').height(lengthRowR);
		if (nombreColImp === 'importe') {
			tablas.otrosDebSeleccionados.fnAdjustColumnSizing();
		} else if (nombreColImp === 'impACobrar') {
			tablas.debitosSeleccionados.fnAdjustColumnSizing();
		} else {
			tablas.clientesCreditos.fnAdjustColumnSizing();
		}
	}
}

function limpiarErrorCruzadoGrillaTransacciones(input, inputId, columnName) {
	var descripcionError = $(input).closest('tr').find('input[id="' + inputId + '"]').closest('div').find('span').text();
	if (!$.isEmpty(descripcionError)) {
		contadorMensajesError.restarError(descripcionError.trim());
	}
	setMensajeError(input, '', '', columnName, '');
}

// Valida los importes ingresados (999.999.999,99 Max)

function validarImporteIngresado(input, tabla, datosTabla, posColImp, nombreColImp, nombreMoneda, posColMensaje, nombreColMensaje, nombreCampoImpMax, idButton, totalizar) {
	var valor = $(input).val();
	var rowPos = tabla.fnGetPosition($(input).parents('tr')[0]);
	var valorAnterior = datosTabla[rowPos][nombreColImp];
	var mensaje = '-';

	var idImporteACobrarDebitos = "impACobrar";
	var idImporteAUtilizarCreditos = "importeAUtilizar";
	var idImporteABonificarTransac = "importeABonificar";
	var idBtnImputar = "btnImputar";

	var asuc = importeToFloat(valorAnterior);

	if (asuc === '-') {
		asuc = 0;
	} else {
		valorAnterior = formatear(asuc);
		updateValueImporte(datosTabla, '0', rowPos, nombreColImp, null);
	}
	if (totalizar) {
		if (!$.isEmpty(datosTabla[rowPos][nombreColMensaje]) && datosTabla[rowPos][nombreColMensaje] != '-') {
			contadorMensajesError.restarError(datosTabla[rowPos][nombreColMensaje]);
			if (nombreColImp === idImporteACobrarDebitos) {
				contadorMensajesErrorGuardableDebitos.restarErrorGuardable(datosTabla[rowPos][nombreColMensaje]);
			} else {
				contadorMensajesErrorGuardableCreditos.restarErrorGuardable(datosTabla[rowPos][nombreColMensaje]);
			}
		}
	}

	// TODO VER POR QUE ESTA COMENTADO
	// if (nombreColImp === 'importeABonificar') {
	// var descripcionError = $(input).closest('div').find('span').text();
	// if (!$.isEmpty(descripcionError)) {
	// contadorMensajesError.restarError(descripcionError.trim());
	// }
	// }
	// se limpian los mensajes
	setMensajeError(input, '', TR_HEIGHT, nombreColImp, idButton);
	// valido si el valor ingresado es nulo y muestro error
	if ($.isEmpty(valor)) {
		if (nombreColImp === idImporteABonificarTransac) {
			$('#btnImputar').prop('disabled', true);
			estadoButtons.btnImputar.errorInput = true;
		} else {
			// actualizo los mensajes para el resto de los campos
			tabla.fnUpdate(msgErrorInputNulo, rowPos, posColMensaje, false);
			if (nombreColImp === idImporteACobrarDebitos) {
				tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
				tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
				tabla.fnUpdate(msgErrorInputNulo, rowPos, 36, false); // resultadoValidacionDescripcionError
			}
		}
		setMensajeError(input, msgErrorInputNulo, TR_HEIGHT_ERROR, nombreColImp, idButton);
		updateValue(datosTabla, '-', rowPos, nombreColImp);

		totalDeDocumentos.actualizar();
		return true;
	}
	// Si no es formato de importe valido: 999.999.999,99 o 999999999,99
	if (!validarImporteValido(valor)) {
		if (nombreColImp === idImporteABonificarTransac) {
			$('#btnImputar').prop('disabled', true);
		} else {
			// actualizo los mensajes para el resto de los campos
			tabla.fnUpdate(msgErrorInput, rowPos, posColMensaje, false);
			if (nombreColImp === idImporteACobrarDebitos) {
				tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
				tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
				tabla.fnUpdate(msgErrorInput, rowPos, 36, false); // resultadoValidacionDescripcionError
			}

		}
		setMensajeError(input, msgErrorInput, TR_HEIGHT_ERROR, nombreColImp, idButton);
		totalDeDocumentos.actualizar();
		return true;
	} else {
		var valorSinPuntoDesimales = valor.split('.').join('');
		var valorDescompuesto = valorSinPuntoDesimales.split(',');
		var error = false;

		if (valorDescompuesto[0].length > 9) {
			error = true;
		} else if (!$.isEmpty(valorDescompuesto[1]) && valorDescompuesto[1].length > 2) {
			error = true;
		}

		if (error) {
			if (nombreColImp === idImporteABonificarTransac) {
				estadoButtons.habilitarImputarByCorreccionInput();
			} else {
				// actualizo los mensajes para el resto de los campos
				tabla.fnUpdate(msgErrorInputLength, rowPos, posColMensaje, false);
				if (nombreColImp === idImporteACobrarDebitos) {
					tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
					tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
					tabla.fnUpdate(msgErrorInputLength, rowPos, 36, false); // resultadoValidacionDescripcionError
				}
			}
			setMensajeError(input, msgErrorInputLength, TR_HEIGHT_ERROR, nombreColImp, idButton);
			updateValueImporte(datosTabla, importeToFloat(valor), rowPos, nombreColImp, input);
			totalDeDocumentos.actualizar();
			return true;
		}
	}

	var valorIngresado = importeToFloat(valor);
	var moneda = datosTabla[rowPos][nombreMoneda];
	var impMax = importeToFloat(datosTabla[rowPos][nombreCampoImpMax].replace(moneda, ""));
	var monedaDelCobro = _monedaDelCobro;
	var sinDiferenciaCambio = datosTabla[rowPos]['sinDifDeCambio'];

	// Valido importe maximo
	if (nombreCampoImpMax !== 'null') {
		// importe maximo debitos
		if (nombreColImp === idImporteACobrarDebitos) {
			// si la moneda del cobro es igual a la moneda del documento
			// aplico maximo
			if (moneda === monedaDelCobro || (moneda !== monedaDelCobro && !$.isEmpty(sinDiferenciaCambio) && sinDiferenciaCambio)) {

				if (impMax > 0 && impMax < valorIngresado) {
					mensaje = msgErrorSuperaMaximoPermitido + formatear(impMax) + ')';
					setMensajeError(input, mensaje, TR_HEIGHT_ERROR, nombreColImp, idButton);

					// actualizo los mensajes para el resto de los campos
					tabla.fnUpdate(mensaje, rowPos, posColMensaje, false);
					tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
					tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
					tabla.fnUpdate(mensaje, rowPos, 36, false); // resultadoValidacionDescripcionError

					updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
					totalDeDocumentos.actualizar();
					return true;
				}
			} else if (COMPENSACION === $('#selectMotivo').val() && moneda !== monedaDelCobro && sinDiferenciaCambio == false) {
				var impMaxCalculado = importeToFloat(datosTabla[rowPos]['saldoMaxDefaultCalculado'].replace(moneda, ""));
				if (impMaxCalculado > 0 && impMaxCalculado < valorIngresado) {
					mensaje = msgErrorSuperaMaximoPermitido + formatear(impMaxCalculado) + ')';
					setMensajeError(input, mensaje, TR_HEIGHT_ERROR, nombreColImp, idButton);

					// actualizo los mensajes para el resto de los campos
					tabla.fnUpdate(mensaje, rowPos, posColMensaje, false);
					tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
					tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
					tabla.fnUpdate(mensaje, rowPos, 36, false); // resultadoValidacionDescripcionError

					updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
					totalDeDocumentos.actualizar();
					return true;
				}

			}
			// importe maximo creditos
		} else if (nombreColImp === idImporteAUtilizarCreditos) {
			// si la moneda del cobro es igual a la moneda del documento
			// aplico maximo
			if (monedaDelCobro === moneda) {
				if (impMax > 0 && impMax < valorIngresado) {
					mensaje = msgErrorSuperaMaximoPermitido + formatear(impMax) + ')';
					setMensajeError(input, mensaje, TR_HEIGHT_ERROR, nombreColImp, idButton);
					// actualizo los mensajes para el resto de los campos
					tabla.fnUpdate(mensaje, rowPos, posColMensaje, false);
					updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
					totalDeDocumentos.actualizar();
					return true;
				}
			} // importe maximo interes a bonificar grilla transacciones
		} else if (nombreColImp === idImporteABonificarTransac) {
			if (impMax > 0 && impMax < valorIngresado) {
				// Importe a cobrar en dolares no deberia validar maximo
				mensaje = msgErrorSuperaMaximoPermitido + formatear(impMax) + ')';
				setMensajeError(input, mensaje, TR_HEIGHT_ERROR, nombreColImp, idButton);
				$('#' + idBtnImputar).prop('disabled', true);

				updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
				totalDeDocumentos.actualizar();
				return true;
			}
		}
	}
	// valido si el valor ingresado es menor a cero
	if (valorIngresado <= 0) {
		if (nombreColImp === idImporteABonificarTransac) {
			$('#btnImputar').prop('disabled', true);
		} else {
			// actualizo los mensajes para el resto de los campos
			tabla.fnUpdate(msgErrorInputNoValido, rowPos, posColMensaje, false);
			if (nombreColImp === idImporteACobrarDebitos) {
				tabla.fnUpdate(DEBITO_VALIDACION_ERROR_NAME, rowPos, 34, false); // resultadoValidacionEstado
				tabla.fnUpdate(ERROR_VALIDACION_EDICION_GRILLAS, rowPos, 35, false); // resultadoValidacionCodigoError
				tabla.fnUpdate(msgErrorInputNoValido, rowPos, 36, false); // resultadoValidacionDescripcionError
			}

		}
		setMensajeError(input, msgErrorInputNoValido, TR_HEIGHT_ERROR, nombreColImp, idButton);
		updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
		totalDeDocumentos.actualizar();
		return true;
	}
	// u573005, sprint 5
	// actualizo los datos de la grilla de transacciones
	if (nombreColImp === idImporteABonificarTransac) {
		var descripcionError = $(input).closest('div').find('span').text();

		if (!$.isEmpty(descripcionError)) {
			contadorMensajesError.restarError(descripcionError.trim());
		}

		// Si hay error en la columna importeABonificar, la limpio
		limpiarErrorCruzadoGrillaTransacciones(input, 'transPorcABonificar', 'porcABonificar');
		var lista = [];
		lista.push(tabla.fnGetData(rowPos));

		// se calcula y se actualiza el porcentaje
		var nuevoPorcentaje = Math.round((valorIngresado * 100) / impMax);
		datosTabla[rowPos]['porcABonificar'] = nuevoPorcentaje;
		tabla.fnUpdate(nuevoPorcentaje, rowPos, TRANS_COL_POS_PORCENTAJE, false);

		// actualizo el valor ingresado en la tabla
		updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);

		// actualizo los totales
		totalInteresesTransacciones.actualizar();
		// si no hay errores habilito el boton imputar
		if (contadorMensajesError.totalErroresGrilla <= 0) {
			estadoButtons.habilitarImputarByCorreccionInput();
		}
	} else {
		// actualizo los mensajes para el resto de los campos
		if (nombreColImp === idImporteACobrarDebitos) {
			if (!$.isEmpty(datosTabla[rowPos]['resultadoValidacionCodigoError']) && datosTabla[rowPos]['resultadoValidacionCodigoError'] == ERROR_VALIDACION_EDICION_GRILLAS) {
				tabla.fnUpdate(mensaje, rowPos, posColMensaje, false);
				tabla.fnUpdate(null, rowPos, 34, false); // resultadoValidacionEstado
				tabla.fnUpdate('-', rowPos, 35, false); // resultadoValidacionCodigoError
				tabla.fnUpdate('-', rowPos, 36, false); // resultadoValidacionDescripcionErro
			}
		} else {
			// para creditos
			tabla.fnUpdate(mensaje, rowPos, posColMensaje, false);
		}
	}
	updateValueImporte(datosTabla, valorIngresado, rowPos, nombreColImp, input);
	totalDeDocumentos.actualizar();
	return true;
}

// u573005, sprint 5
// Valida que el numero ingresado no sea mayor a 100 ni menor a 0
function validarPorcentaje(input, tabla, datosTabla, nombreColumna) {
	var nuevoPorcentaje = $(input).val();
	var rowPos = tabla.fnGetPosition($(input).parents('tr')[0]);
	var descripcionError = $(input).closest('div').find('span').text();
	if (!$.isEmpty(descripcionError)) {
		contadorMensajesError.restarError(descripcionError.trim());
	}
	setMensajeError(input, '', '', nombreColumna, '');

	if ($.isEmpty(nuevoPorcentaje)) {
		// deshabilito el boton imputar
		$('#btnImputar').prop('disabled', true);
		setMensajeError(input, msgErrorInputNulo, '', nombreColumna, '');
		return true;
	}

	if (nuevoPorcentaje == 0 || nuevoPorcentaje > 100) {
		// deshabilito el boton imputar
		$('#btnImputar').prop('disabled', true);
		estadoButtons.btnImputar.errorInput = true;
		setMensajeError(input, msgErrorInputPorcentaje, '', nombreColumna, '');
		return true;
	}
	// Si hay error en la columna importeABonificar, la limpio
	limpiarErrorCruzadoGrillaTransacciones(input, 'transImporteABonificar', 'importeABonificar');
	var importeCompleto = importeToFloat(tabla.fnGetData(rowPos)['intereses'].replace(datosTabla[rowPos]['moneda'], ""));
	var nuevoImporte = formatear((nuevoPorcentaje * importeCompleto) / 100);
	datosTabla[rowPos]['importeABonificar'] = nuevoImporte;
	tabla.fnUpdate(nuevoImporte, rowPos, TRANS_COL_POS_IMPORTE, false);
	updateValue(datosTabla, nuevoPorcentaje, rowPos, nombreColumna);
	// habilito el boton imputar
	if (contadorMensajesError.totalErroresGrilla <= 0) {
		// $('#btnImputar').prop('disabled', false);
		estadoButtons.habilitarImputarByCorreccionInput();
	}

	// actualizo los totales
	totalInteresesTransacciones.actualizar();
	return true;
}

function validarAcuerdoContraClienteCLP(input, esTratamientoDiferencia, esConsulta, esDebitoAProx, idMedPago) {
	var acuerdo = $(input).val().trim();
	var monedaDocumento = '';

	if (!esTratamientoDiferencia) {
		$('#bloqueEspera').trigger('click');
		var descripcionError = $(input).closest('div').find('span').text();
		if (!$.isEmpty(descripcionError)) {
			contadorMensajesError.restarError(descripcionError.trim());
		}
		setMensajeError(input, '', '', '', '');
		var row = tablas.transaccionesPrev.fnGetData($(input).closest('tr'));
		if (!$.isEmpty(row.sistemaMedioPago) && '-' !== row.sistemaMedioPago) {
			monedaDocumento = row.monedaMedioPago;
		} else {
			monedaDocumento = row.moneda;
		}
	}
	var cobro = {
		clientes : datosTablas.clientesSeleccionados,
		numeroAcuerdo : acuerdo,
		idMedioPago : idMedPago,
		debitoAProx : esDebitoAProx,
		monedaDocumento : monedaDocumento,
		monedaOperacion : _monedaDelCobro,
		esTratamientoDiferencia : esTratamientoDiferencia
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarAcuerdoContraClienteCLP",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (esTratamientoDiferencia) {
				armarRespuestaWsCampo(result, esConsulta, esConsulta);
				flagBloqueDeEspera.fValidarAcuerdoContraClienteCLP = true;
			} else {
				armarRespuestaWsTabla(result, input);
			}
		}
	});
};

// u573005, sprint 4 y 5 se agrego para validar el acuerdo contra los clientes
// del cobro y su estado

function validarAcuerdoContraClienteMic(input, esTratamientoDiferencia, esConsulta, esDebitoAProx, idMedPago) {
	var acuerdo = $(input).val();
	if (!esTratamientoDiferencia) {
		$('#bloqueEspera').trigger('click');
		var descripcionError = $(input).closest('div').find('span').text();
		if (!$.isEmpty(descripcionError)) {
			contadorMensajesError.restarError(descripcionError.trim());
		}
		setMensajeError(input, '', '', '', '');
	}
	var cobro = {
		clientes : datosTablas.clientesSeleccionados,
		numeroAcuerdo : acuerdo,
		idMedioPago : idMedPago,
		debitoAProx : esDebitoAProx
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarAcuerdoContraClienteMic",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (esTratamientoDiferencia) {
				armarRespuestaWsCampo(result, esConsulta);
				flagBloqueDeEspera.fValidarAcuerdoContraClienteMic = true;
			} else {
				armarRespuestaWsTabla(result, input);
			}
		}
	});
};

// u573005, sprint 4 y 5, extraigo comportamiento comun de respuesta de
// webservices
function armarRespuestaWsCampo(result, esConsulta) {
	var idMensaje = "wsConsultaJms2";
	if (result.success) {
		if ($.isEmpty(esConsulta)) {
			$("#estadoAcuerdoFacturacion").val(result.estadoAcuerdoFacturacion);
			$("#clienteAcuerdoFacturacion").val(result.clienteAcuerdo);
			$("#" + idMensaje).css('display', 'hidden');
			$("#" + idMensaje).text('');
			verificarCambiInputTraDif();
		} else {
			flagBloqueDeEspera.terminar();
			ocultarBloqueEspera();
			estadoButtons.habilitarSimularByCorreccionInput();
		}
	} else {
		$("#" + idMensaje).css('display', 'inline-block');
		$("#" + idMensaje).text(result.descripcionError);
		$(CONF_COBRO_TAB_PREV).css({
			"color" : "red",
			"font-weight" : "bold"
		});
		tabPrevErrorInput.acuerdo = true;

		$('#btnSimular').prop('disabled', true);
		estadoButtons.btnSimular.errorInput = true;
		if ($.isEmpty(esConsulta)) {
			flagBloqueDeEspera.fValidarSimulacion = true;
		} else {
			flagBloqueDeEspera.terminar();
			ocultarBloqueEspera();

		}
	}
}

// u573005, sprint 4 y 5, extraigo comportamiento comun de respuesta de
// webservices
function armarRespuestaWsTabla(result, input) {
	var rowPos = tablas.transaccionesPrev.fnGetPosition($(input).parents('tr')[0]);
	var estadoAcuerdo = '-';

	limpiarErrores();
	if (result.success) {
		setMensajeError(input, '', '', '', '');
		estadoAcuerdo = result.estadoAcuerdo;
		interes = result.interesesActualizados;
		interesBonificado = result.bonificacionInteresesActualizados;

		// actualizo los datos de la sesion
		// updateValue(datosTablas.transacciones, TIPO_MENSAJE_OK_NAME, rowPos,
		// 'tipoMensajeTransaccion');

		// actualizo los datos de la pantalla
		// el Ok lo muestro solo en pantalla pero a la base viaja vacio

		// tablas.transaccionesPrev.fnUpdate(TIPO_MENSAJE_OK_DESC, rowPos,
		// TRANS_COL_POS_MENSAJE, false);
		// tablas.transaccionesPrev.fnUpdate(estadoAcuerdo, rowPos,
		// TRANS_COL_POS_ESTADO, false);
		var tipoMensajeTransaccion = TIPO_MENSAJE_OK_NAME;
		var mensajeTransaccion = TIPO_MENSAJE_OK_DESC;

		if (result.debitoAProx) {
			tablas.transaccionesPrev.fnUpdate(interes, rowPos, TRANS_COL_POS_INTERES, false);
			// si el check de trasladar intereses esta unchecked, le actualizo
			// el importe a bonificar asumiento que es siempre %100, por ser deb
			// a prox,
			// sino tendria q agarrar el %a bonificar y calcular
			if (!datosTablas.transacciones[rowPos]['trasladarIntereses']) {
				tablas.transaccionesPrev.fnUpdate(interesBonificado, rowPos, TRANS_COL_POS_INTERES_BONIF, false);
			}
			// se resetea el check de trasladar intereses y el porcfentaje a
			// bonificar ( unchecked )
			// tablas.transaccionesPrev.fnUpdate(checkTrasladarIntereses,
			// rowPos, TRANS_COL_POS_TRAS_INTERESES,
			// false);TRANS_COL_POS_PORCENTAJE
			// tablas.transaccionesPrev.fnUpdate(porcABonificar, rowPos,
			// TRANS_COL_POS_PORCENTAJE, false);
			// totalInteresesTransacciones.actualizar();
		}
		if (_monedaDelCobro === MONEDA_PESOS_SIGNO_2) {

			if (!$.isEmpty(result.mensajeWrn)) {
				// updateValue(datosTablas.transacciones, 'WRN', rowPos,
				// 'tipoMensajeTransaccion');
				updateValue(datosTablas.transacciones, result.mensajeWrn, rowPos, 'mensajeTransaccion');
				updateValue(datosTablas.transacciones, estadoAcuerdo, rowPos, 'estadoAcuerdoDestinoCargos');

				tablas.transaccionesPrev.fnUpdate('WRN', rowPos, TRANS_COL_POS_MENSAJE, false);
				tablas.transaccionesPrev.fnUpdate(result.mensajeWrn, rowPos, TRANS_COL_POS_MENSAJE, false);
				tipoMensajeTransaccion = TIPO_MENSAJE_WRN_NAME;
				mensajeTransaccion = result.mensajeWrn;

				setMensajeError(input, result.mensajeWrn, '', '', '');
			}

		}
		updateValue(datosTablas.transacciones, ESTADO_FACTURACION_ACTIVO_DESC, rowPos, 'estadoAcuerdoDestinoCargos');
		tablas.transaccionesPrev.fnUpdate(estadoAcuerdo, rowPos, TRANS_COL_POS_ESTADO, false);

		var size = datosTablas.transacciones.length;
		var numeroTransaccionFormateado = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.DataTable().row($(input).closest('td')).index()).numeroTransaccionFormateado;
		var estadoIndex = false;
		for (var index = 0; index < size; index++) {
			if (datosTablas.transacciones[index].numeroTransaccionFormateado === numeroTransaccionFormateado) {
				datosTablas.transacciones[index].mensajeTransaccion = mensajeTransaccion;
				datosTablas.transacciones[index].tipoMensajeTransaccion = tipoMensajeTransaccion;
				estadoIndex = true;
			} else if (estadoIndex === true) {
				break;
			}
		}
		estadoIndex = false;
		$(tablas.transaccionesPrev.fnGetData()).each(function(j, transaccion) {
			if (transaccion.numeroTransaccionFormateado == numeroTransaccionFormateado) {
				transaccion.mensajeTransaccion = mensajeTransaccion;
				tablas.transaccionesPrev.fnUpdate(tipoMensajeTransaccion, j, TRANS_COL_POS_MENSAJE, false);
				tablas.transaccionesPrev.fnUpdate(mensajeTransaccion, j, TRANS_COL_POS_MENSAJE, false);
			}
		});
		// si no hay error habilito el boton imputar
		if (contadorMensajesError.totalErroresGrilla <= 0) {
			// $('#btnImputar').attr('disabled', false);
			estadoButtons.habilitarImputarByCorreccionInput();
		}

		totalInteresesTransacciones.actualizar();

	} else {
		// cuando hay error deshabilito el boton imputar
		$('#btnImputar').attr('disabled', true);
		estadoButtons.btnImputar.errorInput = true;
		mensajeError = TIPO_MENSAJE_ERROR_DESC + "! " + result.descripcionError;

		// Muestro el error
		setMensajeError(input, mensajeError, '', '', '');

		// actualizo tabla en sesion
		updateValue(datosTablas.transacciones, mensajeError, rowPos, 'mensajeTransaccion');
		updateValue(datosTablas.transacciones, TIPO_MENSAJE_ERROR_NAME, rowPos, 'tipoMensajeTransaccion');
		updateValue(datosTablas.transacciones, estadoAcuerdo, rowPos, 'estadoAcuerdoDestinoCargos');

		// actualizo tabla en cliente
		tablas.transaccionesPrev.fnUpdate(mensajeError, rowPos, TRANS_COL_POS_MENSAJE, false);
		tablas.transaccionesPrev.fnUpdate(estadoAcuerdo, rowPos, TRANS_COL_POS_ESTADO, false);
	}

	// actualizo el dato de la sesion en ambos casos
	updateValue(datosTablas.transacciones, $(input).val(), rowPos, 'acuerdoDestinoCargos');
	updateValue(datosTablas.transacciones, result.clienteAcuerdo, rowPos, 'clienteAcuerdoDestinoCargos');
	ocultarBloqueEspera();
}

// verificaTodosSeleccionados(tablas.clientes, tablas.clientesSeleccionados,
// 'idClienteLegado')) {}
function verificaTodosSeleccionados(tabla, tablaSeleccionado, campoId) {
	var salida = true;
	if (tablaSeleccionado.fnGetData().length == 0) {
		return false;
	}
	$(tabla.fnGetData()).each(function(j, elemento) {
		$(tablaSeleccionado.fnGetData()).each(function(i, seleccionado) {
			if (elemento[campoId] != seleccionado[campoId]) {
				salida = false;
				return false;
			}
		});
	});
	return salida;
}

$(document).ready(function() {
	// Solo se utliza para controlar el btn Simular en la solapa
	// 5 "previsualizar"
	_monedaDelCobro = MONEDA_PESOS_SIGNO_2;
	$('#prevObservacionesAnterior').hide();
	if (!$.isEmpty(SHOW_BUTTON)) {
		$('button[id^=btnVolver]').show();
	}
	$('#btnImputar').prop('disabled', true);
	$('#btnSimular').prop('disabled', true);
	estadoButtons.crear();
	estadoButtons.btnSimular.disabled = $('#btnSimular').prop('disabled');
	$("#idSpan").css('display', 'none');
	$("#idSpan2").css('display', 'none');
	$('#bloqueSubirDebitos').show();
	$('#bloqueSubirOtrosDebitos').show();
	beforeUnload.unbind();
	beforeUnload.off();
	beforeUnload.on();

	$('#btnVerResumenCompensacionPie').hide();
	$('#btnVerCartaPie').hide();
	// Para actualizar informaciones del paginado
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
		};
	};

	// -----------------------------------------------------------------------------
	// Tablas

	// Tabla de clientes
	var clientesSearchSettings = {
		dom : '<"agregarTodosClientes">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"order" : [ [ 2, "asc" ] ],
		"iDisplayLength" : DISPLAY_LENGTH,
		"buttons" : [ {
			extend : 'excelHtml5',
			text : 'Excel',
			title : "Cobro_Clientes_Sin_seleccionar",
			className : 'excelbtn',
			mColumns : "visible"
		} ],
		"aoColumns" : [ {
			"targets" : -1,
			"searchable" : false,
			"bSortable" : false,
			"data" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link  ' + BTN_NO_CLASS + data.idClienteLegado + '" title="Agregar cliente" idClienteLegado="' + data.idClienteLegado + '"><i class="icon-plus bigger-120"></i></button></div>';
				}
				return data;
			}
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "empresasAsociadas"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "origen"
		}, {
			"mData" : "segmentoAgencia"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "agenciaNegocio"
		} ],
		'columnDefs' : [ // targets son los th con las class
		{
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};
	// numeroOrder
	// Tabla de clientes seleccionados
	var selClientesSettings = {
		dom : '<"eliminarTodosClientes">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : 10,
		"iDisplayStart" : 0,
		"order" : [ [ 2, "asc" ] ],
		buttons : [ {
			extend : 'excelHtml5',
			text : 'Excel',
			title : "Cobro_Clientes_Seleccionados",
			className : 'excelbtn',
			exportOptions : {
				columns : ':visible'
			}
		} ],
		"fnDrawCallback" : function(oSettings) {
			$('#btnEliminarTodos').attr('disabled', !(oSettings.aoData.length > 0));
		},
		"aoColumns" : [ {
			"targets" : -1,
			"searchable" : false,
			"bSortable" : false,
			"data" : null,
			"render" : function(data, type, row) { // <---------
				// Editable
				// checkbox
				if (type === 'display') {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar cliente" idClienteLegado="' + data.idClienteLegado + '"><i class="icon-minus bigger-120"></i></button></div>';
				}
				return data;
			}
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "empresasAsociadas"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "origen"
		}, {
			"mData" : "segmentoAgencia"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "agenciaNegocio"
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false
		} ],
		'columnDefs' : [ // targets son los th con las class
		{
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};

	// Clientes - Debitos
	var clientesDebitosSettings = {
		dom : '<"botonExcelClientesDebitos">Bfrtip',
		"sScrollY" : SCROLL_Y,
		"sScrollX" : "100%",
		"sScrollXInner" : "110%",
		"bScrollCollapse" : true,
		"iDisplayLength" : 10,
		"order" : [ [ 2, "asc" ] ],
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Clientes_Seleccionados",
			className : 'excelbtn',
			exportOptions : {
				columns : ':visible'
			}
		} ],
		"aoColumns" : [ {
			"mData" : null, // 0
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<input type="checkbox" class="debClienteSel editor-active" onclick="verifyAllSelected(\'debTodosClientes\',\'debClienteSel\', tablas.clientesDebitos, \'btnBuscarDebitos\')" ' + (($('#debTodosClientes').is(":checked")) ? 'checked' : '') + '/>';
				}
				return data;
			},

			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "agenciaNegocio"
		}, {
			"mData" : "segmentoAgencia"
		} ],
		'columnDefs' : [ {
			width : WiDTH_CHECK,
			targets : 0
		}, {
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};

	// Tabla de debitos
	var debitosSearchSettings = {
		dom : '<"agregarTodosDebitos">Bfrt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"iDisplayLength" : DISPLAY_LENGTH,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ // 0
		{
			"mData" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					var title = '';
					if (data.semaforo.descripcionError != null) {
						title = data.semaforo.descripcionError;
					}
					if (SEMAFORO_ROJO_DESCRIPCION === data.semaforo.semaforo) {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS + data.idDebitoPantalla + '" title="Agregar débito" disabled="disabled" idDebitoVar2="' + data.idDebitoPantalla + '">' + HTML_ICON_BLANK + '</button>' + '<div class="btn btn-xs btn-link gestion" title="' + title + '">' + '<div id="' + data.idDebitoPantalla + '" class="gestion-semaforo gestion-semaforo-' + data.semaforo.semaforo + ' ' + GESTIONABILIDAD_NO_CLASS + data.idDebitoPantalla + '"></div>' + '</div>' + '</div>';
					} else {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS + data.idDebitoPantalla + '" title="Agregar débito" idDebitoVar2="' + data.idDebitoPantalla + '">' + HTML_ICON_PLUS + '</button>' + '<div class="btn btn-xs btn-link gestion" title="' + title + '">' + '<div id="' + data.idDebitoPantalla + '" class="gestion-semaforo gestion-semaforo-' + data.semaforo.semaforo + ' ' + GESTIONABILIDAD_NO_CLASS + data.idDebitoPantalla + '"></div>' + '</div>' + '</div>';
					}
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idDebitoPantalla",
			"visible" : false,
			"searchable" : false
		}, // 1
		{
			"mData" : "cliente"
		}, // 2
		{
			"mData" : "cuenta"
		}, // 3
		{
			"mData" : "sistemaDescripcion"
		},// 4
		{
			"mData" : "tipoDoc"
		}, // 5
		{
			"mData" : "subtipoDocumento", // 6
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			}
		}, {
			"mData" : "nroDoc"
		}, // 7
		{
			"mData" : "numeroReferenciaMic"
		}, // 8
		{
			"mData" : "fechaVenc"
		},// 9
		{
			"mData" : "saldo1erVencMonOrigen", // 10
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "moneda"
		}, // 11
		{
			"mData" : "imp1erVenc", // 12
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "imp2doVenc", // 13
			"mRender" : function(data, type, row) {
				if (row.fechaVencimientoMora != row.fechaVenc && MIC_NAME === row.sistemaOrigen) {
					return truncoADosDecimalesImporte(data, row.moneda);
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 14
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "estadoCptosDe3ros"
		}, // 15
		{
			"mData" : "imp3rosTransf", // 16
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "estadoOrigen"
		}, // 17
		{
			"mData" : "marcaReclamoDescripcion"
		}, // 18
		{
			"mData" : "migradoOrigen"
		}, // 19
		{
			"mData" : "estadoDeimos"
		}, // 20
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		}, // 21
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 22
		{
			"mData" : "opeAsocAnalista"
		}, // 23
		{
			"mData" : "tipoCambio",// 24
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "fechaEmision"
		}, // 25
		{
			"mData" : "nroConvenio"
		}, // 26
		{
			"mData" : "cuota"
		}, // 27
		{
			"mData" : "fechaUltPagoParcial"
		} // 28
		],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};

	// Debitos Seleccionados
	var debitosSelSettings = {
		dom : '<"eliminarTodosDebitos">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"fnDrawCallback" : function(oSettings) {
			$('#btnEliminarTodosDebitos').attr('disabled', !(oSettings.aoData.length > 0));
			selectedRow.markRowSelected('debitos', true, 'debitosSeleccionados', false);
			if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos") {
				cambiarModoEdicion(varEdicionSegunEstadoMarca, '');
			}
		},
		"aoColumns" : [ {
			"targets" : -1,
			"data" : null, // 0
			"render" : function(data, type, row) { // <---------
				// Button
				if (type === 'display') {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar débito" idsemaforo="' + data.semaforo.semaforo + '" idDebitoVar="' + data.idDebitoPantalla + '" id="btnClonedDeb' + data.idDebitoPantalla.trim() + '"><i class="icon-minus bigger-120"></i></button></div>';
				}
				return data;
			},
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idDebitoPantalla",
			"visible" : false,
			"searchable" : false
		}, // 1
		{
			"mData" : "cliente"
		},// 2
		{
			"mData" : "cuenta"
		}, // 3
		{
			"mData" : "sistemaDescripcion"
		},// 4
		{
			"mData" : "tipoDoc"
		}, // 5
		{
			"mData" : "subtipoDocumento",// 6
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			}
		}, {
			"mData" : "nroDoc"
		}, // 7
		{
			"mData" : "numeroReferenciaMic"
		},// 8
		{
			"mData" : "fechaVenc"
		},// 9
		{
			"mData" : "saldo1erVencMonOrigen", // 10
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		},// 10
		{
			"mData" : "moneda"
		},// 11
		{
			"mData" : "imp1erVenc",
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		},// 12
		{
			"mData" : "imp2doVenc",
			"mRender" : function(data, type, row) {
				if (row.fechaVencimientoMora != row.fechaVenc && MIC_NAME === row.sistemaOrigen) {
					return truncoADosDecimalesImporte(data, row.moneda);
				}
				return '-';
			}
		},// 13
		{
			"mData" : "saldoPesificado",
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		},// 14
		{
			"mData" : "cobrarAl2doVenc",
			"render" : function(data, type, row) { // <---------
				// Editable
				// checkbox
				if (type === 'display') {
					var showCheck = (MIC_NAME === row.sistemaOrigen && (row.tipoComprobanteEnum === 'FAC' || row.tipoComprobanteEnum === 'DEB') && row.fechaVencimientoMora != row.fechaVenc);
					var disable2doVenc = false;
					if (showCheck && (!$.isEmpty(row.fechaUltPagoParcial) && row.fechaUltPagoParcial !== '-')) {
						// Si tiene
						// fecha_ultimo_pago_parcial
						disable2doVenc = true;
					}
					return (showCheck) ? '<input type="checkbox" id="cobrarAl2doVenc" name="cobrarAl2doVenc" onclick="debitoCheckSaldoMaximo(this, \'cobrarAl2doVenc\')" ' + ((data) ? 'checked="checked"' : '') + ((disable2doVenc) ? ' disabled="disabled"' : '') + '>' : '-';
				}
				return data;
			}
		}, // 15
		{
			"mData" : "destransferir3ros", // 
			"render" : function(data, type, row) { // <---------
				// Editable
				// checkbox
				if (type === 'display') {
					var showCheck = (MIC_NAME === row.sistemaOrigen && (row.tipoComprobanteEnum === 'FAC' || row.tipoComprobanteEnum === 'DEB'));
					return (showCheck) ? '<input type="checkbox" id="destransferir3ros" name="destransferir3ros" onclick="debitoCheckSaldoMaximo(this, \'destransferir3ros\')" ' + ((data) ? 'checked="checked"' : '') + (!((row.estadoCptosDe3ros === 'S' || row.estadoCptosDe3ros === 'T') && row.posibilidadDestransferencia === 'SI') ? ' disabled' : '') + '>' : '-';
				}
				return data;
			}
		},// 16

		{
			"mData" : "impACobrar", // <---------
			// Editable input
			// text //17
			"render" : function(data, type, row) {
				if (type === 'display') {
					var disabled = (row.cobrarAl2doVenc || row.destransferir3ros || DUC_NAME == row.tipoDoc);
					return '<div class="controls">' + returnSignoMoneda(_monedaDelCobro) + '<input onkeypress="return validarInputNumericosComaPunto(event)" id="debImporte" maxlength="14" type="text" value="' + (validarImporteValido(data) ? formatear(importeToFloat(data)) : '0') + '" class="input" style="margin: 0 auto;width: 110px;text-align: right;" ' + ' onfocusout="return validarImporteIngresado(this, tablas.debitosSeleccionados, datosTablas.debitosSeleccionados, DEB_COL_POS_IMPORTE, \'impACobrar\', \'moneda\', DEB_COL_POS_MENSAJE, \'descripcionErrorValidacion\', \'saldoMaxDefault\',\'btnClonedDeb' + row.idPantalla + '\', true)"' + ' ' + ((disabled) ? 'disabled' : '') + '>' + '<div></div>' + '</div>';
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, // 17
		{
			"mData" : "estadoCptosDe3ros"
		}, // 18
		{
			"mData" : "imp3rosTransf",// 19
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		},// 19
		{
			"mData" : "estadoOrigen"
		},// 20
		{
			"mData" : "marcaReclamoDescripcion"
		},// 21
		{
			"mData" : "migradoOrigen"
		},// 22
		{
			"mData" : "estadoDeimos"
		},// 23
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		},// 24
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 25
		{
			"mData" : "opeAsocAnalista"
		},// 26 //Si se modifica la posicion de esta
		// columna se debe modificar el index en la
		// funcion "setMensajeErro"
		{
			"mData" : "tipoCambio",// 27
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "fechaEmision"
		},// 28
		{
			"mData" : "sinDifDeCambio",// <---------
			// Editable
			// checkbox 29
			"render" : function(data, type, row) {
				if (type === 'display') {
					var showCheck = (CALIPSO_NAME === row.sistemaOrigen && row.moneda !== _monedaDelCobro && ((row.tipoComprobanteEnum === 'FAC' || row.tipoComprobanteEnum === 'DEB' || row.tipoComprobanteEnum === 'CRE' || row.tipoComprobanteEnum === 'CTA') || $('#selectMotivo').val() == COMPENSACION));

					if (showCheck && (row.habilitarEdicionSinDifCambio === 'NO')) {
						row.habilitarEdicionSinDifCambio = 'SI';
					}
					;
					// Implementacion del 2016 04 16
					// Cobro en dólares SIN diferencia
					// de cambio Calipso
					return (showCheck) ? '<input type="checkbox" id="sinDifDeCambio" name="sinDifDeCambio" onclick="debitoCheckSaldoMaximo(this, \'sinDifDeCambio\')" ' + ((row.habilitarEdicionSinDifCambio == 'SI') ? '' : 'disabled ') + ((data) ? 'checked ' : '') + '>' : '-';
				}
				return data;
			}
		}, {
			"mData" : "nroConvenio"
		},// 30
		{
			"mData" : "cuota"
		},// 31
		{
			"mData" : "fechaUltPagoParcial"
		},// 32
		{
			"mData" : "descripcionErrorValidacion"
		},// 33
		{
			"mData" : "resultadoValidacionEstado",
			"visible" : false,
			"searchable" : false
		},// 34
		{
			"mData" : "resultadoValidacionCodigoError",
			"visible" : false,
			"searchable" : false
		},// 35
		{
			"mData" : "resultadoValidacionDescripcionError",
			"visible" : false,
			"searchable" : false
		} // 36
		],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};

	// Clientes- Creditos
	var clientesCreditosSettings = {
		dom : '<"botonExcelClientesCreditos">Bfrtip',
		"sScrollY" : SCROLL_Y,
		"sScrollX" : "100%",
		"sScrollXInner" : "110%",
		"bScrollCollapse" : true,
		"iDisplayLength" : 10,
		"order" : [ [ 2, "asc" ] ],
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Clientes_Seleccionados",
			className : 'excelbtn',
			exportOptions : {
				columns : ':visible'
			}
		} ],
		"aoColumns" : [ {
			"mData" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<input type="checkbox" class="credClienteSel editor-active" onclick="verifyAllSelected(\'credTodosClientes\',\'credClienteSel\', tablas.clientesCreditos, \'btnBuscarCreditos\')" ' + (($('#credTodosClientes').is(":checked")) ? 'checked' : '') + '/>';
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "agenciaNegocio"
		}, {
			"mData" : "segmentoAgencia"
		} ],
		'columnDefs' : [ {
			width : WiDTH_CHECK,
			targets : 0
		}, {
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};

	// Tabla de creditos sin seleccionar
	var creditosSearchSettings = {
		dom : '<"agregarTodosCreditos">Bfrt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"iDisplayLength" : DISPLAY_LENGTH,

		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"mData" : null,// 0
			"render" : function(data, type, row) {
				if (type === 'display') {
					var title = '';
					if (data.semaforo.descripcionError != null) {
						title = data.semaforo.descripcionError;
					}
					if (SEMAFORO_ROJO_DESCRIPCION === data.semaforo.semaforo) {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS + data.idCreditoPantalla + '" title="Agregar crédito" disabled="disabled" impACobrar="' + data.impACobrar + '" idsemaforo="' + data.semaforo.semaforo + '" idCreditoVar2="' + data.idCreditoPantalla + '">' + HTML_ICON_BLANK + '</button>' + '<div class="btn btn-xs btn-link gestion" title="' + title + '">' + '<div id="' + data.idCreditoPantalla + '" class="gestion-semaforo gestion-semaforo-' + data.semaforo.semaforo + ' ' + GESTIONABILIDAD_NO_CLASS + data.idCreditoPantalla + '"></div>' + '</div>' + '</div>';
					} else {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS + data.idCreditoPantalla + '" title="Agregar crédito" idsemaforo="' + data.semaforo.semaforo + '" idCreditoVar2="' + data.idCreditoPantalla + '">' + HTML_ICON_PLUS + '</button>' + '<div class="btn btn-xs btn-link gestion" title="' + title + '">' + '<div id="' + data.idCreditoPantalla + '" class="gestion-semaforo gestion-semaforo-' + data.semaforo.semaforo + ' ' + GESTIONABILIDAD_NO_CLASS + data.idCreditoPantalla + '"></div>' + '</div>' + '</div>';
					}
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idCreditoPantalla",
			"visible" : false,
			"searchable" : false
		},// 1
		{
			"mData" : "idClienteLegado"
		},// 2
		{
			"mData" : "cuenta"
		}, // 3
		{
			"mData" : "sistemaDescripcion"
		}, // 4
		{
			"mData" : "tipoCredito"
		},// 5
		{
			"mData" : "subtipo", // 6
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			}
		}, {
			"mData" : "nroDoc"
		},// 7
		{
			"mData" : "numeroReferenciaMic"
		},// 8
		{
			"mData" : "fechaValor"
		}, // 9
		{
			"mData" : "saldoMonOrigen", // 10
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "moneda"
		},// 11
		{
			"mData" : "impMonOrigen", // 12
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "impPesificado",// 13
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 14
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "fechaAltaCredito"
		},// 15
		{
			"mData" : "fechaIngresoRecibo"
		},// 16
		{
			"mData" : "fechaDeposito"
		}, // 17
		{
			"mData" : "fechaTrans"
		}, // 18
		{
			"mData" : "fechaEmision"
		}, // 19
		{
			"mData" : "fechaVenc"
		}, // 20
		{
			"mData" : "fechaUltimoMov"
		},// 21
		{
			"mData" : "tipoCambio",// 22
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "provincia"
		},// 23
		{
			"mData" : "cuit"
		},// 24
		{
			"mData" : "estadoOrigen"
		},// 25
		{
			"mData" : "motivoDescripcion",// 26
			"mRender" : function(data, type, row) {
				if ($.isEmpty(data)) {
					return '-';
				}
				return data;
			}
		}, {
			"mData" : "marcaReclamoDescripcion"
		},// 27
		{
			"mData" : "marcaMigradoDeimos"
		},// 28
		{
			"mData" : "estadoDeimos"
		},// 29
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		},// 30
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 31
		{
			"mData" : "opeAsocAnalista"
		},// 32
		{
			"mData" : "tipoCreditoEnum",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "tipoComprobanteEnum",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "idValor",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "codigoTipoRemanente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "motivo",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "razonSocialCliente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "tipoCliente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "unidadOperativa",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "holding",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "cicloFacturacion",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "marketingCustomerGroup",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "codigoTarifa",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "indicadorPeticionCorte",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "fechaVencimientoMora",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosEstadoTramite",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosApropiacionEnDeimos",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosCantidadDeCuotas",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosCantidadDeCuotasPagas",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosNroConvenio",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "orderByFecha",
			"visible" : false,
			"searchable" : false
		} ],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};

	// Creditos seleccionados
	var creditosSelSetting = {
		dom : '<"eliminarTodosCreditos">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"fnDrawCallback" : function(oSettings) {
			$('#btnEliminarTodosCreditos').attr('disabled', !(oSettings.aoData.length > 0));
			selectedRow.markRowSelected('creditos', true, 'creditosSeleccionados', false);
		},
		"aoColumns" : [ {
			"targets" : -1,
			"data" : null, // 0
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar crédito" idsemaforo="' + data.semaforo.semaforo + '" idCreditoSel="' + data.idCreditoPantalla + '"id="btnClonedCre' + data.idPantalla.trim() + '"><i class="icon-minus bigger-120"></i></button></div>';
				}
				return data;
			},
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idCreditoPantalla",
			"visible" : false,
			"searchable" : false
		}, // 01
		{
			"mData" : "idClienteLegado"
		},// 2
		{
			"mData" : "cuenta"
		}, // 3
		{
			"mData" : "sistemaDescripcion"
		},// 4
		{
			"mData" : "tipoCredito"
		},// 5
		{
			"mData" : "subtipo", // 6
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			}
		}, {
			"mData" : "nroDoc"
		},// 7
		{
			"mData" : "numeroReferenciaMic"
		}, // 8
		{
			"mData" : "fechaValor"
		},// 9
		{
			"mData" : "saldoMonOrigen", // 10
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "moneda"
		},// 11
		{
			"mData" : "impMonOrigen",// 12
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "impPesificado",// 13
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 14
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "importeAUtilizar", // <---------
			// Editable
			// input
			// text //15
			"render" : function(data, type, row) {
				if (type === 'display') {
					var disabled = (row.sistemaOrigen === 'SHIVA' && row.tipoFacturaShiva === RETENCION_NAME && row.moneda === '$');

					return '<div class="controls">' + returnSignoMoneda(_monedaDelCobro) + '<input id="debCredito" onkeypress="return validarInputNumericosComaPunto(event)" maxlength="14" type="text" value="' + (validarImporteValido(data) ? formatear(importeToFloat(data)) : '0') + '" class="input" style="margin: 0 auto;width: 110px;text-align: right;" onfocusout="return validarImporteIngresado(this, tablas.creditosSeleccionados, datosTablas.creditosSeleccionados, CRED_COL_POS_IMPORTE, \'importeAUtilizar\',\'moneda\', CRED_COL_POS_MENSAJE, \'descripcionErrorValidacion\',\'importeMaxDefault\',\'btnClonedCre' + row.idPantalla + '\', true)"' + ' ' + ((disabled) ? 'disabled' : '') + '>' + '<div></div>' + '</div>';
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "fechaAltaCredito"
		},// 16
		{
			"mData" : "fechaIngresoRecibo"
		},// 17
		{
			"mData" : "fechaDeposito"
		},// 18
		{
			"mData" : "fechaTrans"
		},// 19
		{
			"mData" : "fechaEmision"
		},// 20
		{
			"mData" : "fechaVenc"
		},// 21
		{
			"mData" : "fechaUltimoMov"
		},// 22
		{
			"mData" : "tipoCambio",// 23
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "provincia"
		},// 24
		{
			"mData" : "cuit"
		},// 25
		{
			"mData" : "estadoOrigen"
		},// 26
		{
			"mData" : "motivoDescripcion", // 27
			"mRender" : function(data, type, row) {
				if ($.isEmpty(data)) {
					return '-';
				}
				return data;
			}
		}, {
			"mData" : "marcaReclamoDescripcion"
		},// 28
		{
			"mData" : "marcaMigradoDeimos"
		},// 29
		{
			"mData" : "estadoDeimos"
		},// 30
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		},// 31
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 32
		{
			"mData" : "opeAsocAnalista"
		},// 33 //Si se modifica la posicion de esta
		// columna se debe modificar el index en la
		// funcion "setMensajeErro"
		{
			"mData" : "descripcionErrorValidacion"
		}, // 34
		{
			"mData" : "tipoCreditoEnum",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "tipoComprobanteEnum",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "idValor",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "codigoTipoRemanente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "motivo",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "razonSocialCliente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "tipoCliente",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "unidadOperativa",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "holding",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "cicloFacturacion",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "marketingCustomerGroup",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "codigoTarifa",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "indicadorPeticionCorte",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "fechaVencimientoMora",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosEstadoTramite",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosApropiacionEnDeimos",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosCantidadDeCuotas",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosCantidadDeCuotasPagas",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "dmosNroConvenio",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "orderByFecha",
			"visible" : false,
			"searchable" : false
		} ],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};

	// Clientes - Medios Pago
	var clientesSelMPagoSetting = {
		dom : '<"botonExcelClientesMedios">Bfrtip',
		"sScrollY" : SCROLL_Y,
		"sScrollX" : "100%",
		"sScrollXInner" : "110%",
		"bScrollCollapse" : true,
		"order" : [ [ 3, "asc" ] ],
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Clientes_Seleccionados",
			className : 'excelbtn',
			"mColumns" : "visible"
		} ],
		"aoColumns" : [ {
			"mData" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					var controlCliente = '';
					if (COMPENSACION === $('#selectMotivo').val()) {
						controlCliente = '<input type="radio" id="idRadioClienteMp' + eliminaCerosADerecha(data.idClienteLegado) + '" name="radioCliente" class="mpClienteSel opcionCliente editor-active" idCliente="' + data.idClienteLegado + '"> ';
					} else {
						controlCliente = '<input type="checkbox" class="mpClienteSel opcionCliente editor-active" onclick="checkAllVisibleSelected(\'seleccionarTodos\',\'opcionCliente\', tablas.clientesMedios)">';
					}
					return controlCliente;
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "empresasAsociadas"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "origen"
		}, {
			"mData" : "segmentoAgencia"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "agenciaNegocio"
		} ],
		"fnDrawCallback" : function(oSettings) {
			var checkAll = false;
			var disableCheckAll = true;
			if (tablas.clientesMedios) {
				var cant = $(".opcionCliente:visible", tablas.clientesMedios.fnGetNodes()).length;
				var checks = 0;
				$(".opcionCliente:visible", tablas.clientesMedios.fnGetNodes()).each(function(i, elem) {
					disableCheckAll = false;
					if ($(this).is(":checked"))
						checks = checks + 1;
				});
				checkAll = (cant > 0 && cant == checks);
			}
			$('#seleccionarTodos').prop('checked', checkAll);
			$('#seleccionarTodos').prop('disabled', disableCheckAll);
		},
		'columnDefs' : [ {
			width : WiDTH_CHECK,
			targets : 0
		}, {
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};

	// Medios de pagos seleccionados
	var mediosDePagosSetting = {
		dom : '<"eliminarTodosMedios">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,

		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Medios_de_pagos_Seleccionados",
			className : 'excelbtn',
			"mColumns" : "visible"
		} ],
		"fnDrawCallback" : function(oSettings) {
			$('#btnEliminarTodosMedios').attr('disabled', !(oSettings.aoData.length > 0));
		},
		"aoColumns" : [ {
			"targets" : -2,
			"data" : null,
			"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar medio de pago"><i class="icon-minus bigger-120"></i></button></div>',
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : null,
			"render" : function(data, type, row) {
				if (type === 'display' && (data.idMpTipoCredito === LIQUIDOPRODUCTO || data.idMpTipoCredito === PROVEEDORES)) {

					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Editar" id="btnEditarOtroMedioPago"><i class="icon-edit bigger-120"></i></button></div>';
				}
				return '';
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "clientesLegados",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "descMpTipoCredito"
		}, {
			"mData" : "subTipoDescripcion"
		}, {
			"mData" : "importe",
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		}, {
			"mData" : "nroActa"
		}, {
			"mData" : "nroCompensacion"
		}, {
			"mData" : "fecha"
		}, {
			"mData" : "clientesLegados"
		}, {
			"mData" : "provincia"
		}, {
			"mData" : "nroDeDocumentoInterno"
		}, {
			"mData" : "monedaImporteUtilizar",
			"visible" : false,
			"searchable" : false
		}

		]
	};
	// Tabla CAP
	// Tabla de caps
	var capsSearchSettings = {
		dom : '<"agregarTodosCaps">Bft',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"iDisplayLength" : DISPLAY_LENGTH,
		"order" : [],
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"mData" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					if (data.tipoRenglon === "AGRUPADOR") {
						var title = '';
						if (data.semaforo.descripcionError != null) {
							title = data.semaforo.descripcionError;
						}
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS + data.idPantalla + ' semaforo-' + data.semaforo.semaforo + '" title="Agregar cap" ' + (data.esNoGestionable ? 'disabled="disabled"' : '') + ' idCapVar="' + data.idPantalla + '" idProveedor="' + data.idNumeroProveedor + '">' + (data.esNoGestionable ? HTML_ICON_BLANK : HTML_ICON_PLUS) + '</button>' + '<div class="btn btn-xs btn-link gestion" title="' + title + '">' + '<div id="' + data.idPantalla + '" class="gestion-semaforo gestion-semaforo-' + data.semaforo.semaforo + ' ' + GESTIONABILIDAD_NO_CLASS + data.idPantalla + '"></div>' + '</div>' + '</div>';
					} else {
						return "";
					}
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "codigoSociedad"
		}, // 1
		{
			"mData" : "idClienteLegado"
		}, // 2
		{
			"mData" : "idNumeroProveedor"
		}, // 3
		{
			"mData" : "tipoDocumento"
		},// 4
		{
			"mData" : "nroDocumentoSap"
		}, // 5
		{
			"mData" : "nroLegalSap"
		}, // 6
		{
			"mData" : "fechaEmision"
		}, // 7
		{
			"mData" : "numeroRenglon"
		},// 8
		{
			"mData" : "monedaSigno2"
		},// 9
		{
			"mData" : "importeMonedaOrigen", // 8
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.monedaSigno2);
			}
		}, // 10
		{
			"mData" : "tipoCambio",// 9
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "importeGestionableEmision", // 10
			// OK
			"mRender" : function(data, type, row) {
				if (row.esReglonAgrupador) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoMonedaOrigen",// 11
			"mRender" : function(data, type, row) {
				if (!row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificadoEmision",// 12
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "importePesificadoDocAsociadoEmision", // 13
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, 'Pesos');
			}
		}, {
			"mData" : "importeMonedaOrigenDocAsociado",// 14
			"mRender" : function(data, type, row) {
				if (row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "tipoCambioDocAsociado",
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			} // 15
		} ],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			if ($(nRow).hasClass('even')) {
				$(nRow).removeClass('even');
			} else {
				$(nRow).removeClass('odd');
			}

			if (aData.tipoRenglon === T_RENGLON_AGRUPADOR) {
				if (aData.even === true) {
					// $('td', nRow).css('background-color',
					// '');
					$(nRow).addClass('even');
					$('td', nRow).css('border-bottom', 'none');
					trCapsBackgroundColor = 'even';
				} else {
					// $('td', nRow).css('background-color',
					// '#F2F2F7');
					$('td', nRow).css('border-bottom', 'none');
					$(nRow).addClass('odd');
					trCapsBackgroundColor = 'odd';
				}
			} else if (aData.esRenglonFin) {
				// $('td', nRow).css('background-color',
				// trCapsBackgroundColor);
				$(nRow).addClass(trCapsBackgroundColor);
				$('td', nRow).css('border-top', 'none');
				$('td', nRow).css('color', CSS_SELECTED_ROW);
			} else {
				// $('td', nRow).css('background-color',
				// trCapsBackgroundColor);
				$(nRow).addClass(trCapsBackgroundColor);
				$('td', nRow).css('border-top', 'none');
				$('td', nRow).css('border-bottom', 'none');
				$('td', nRow).css('color', CSS_SELECTED_ROW);
			}
		},
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			targets : 'nosort',
			orderable : false
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};

	// capSeleccionados
	var capsSelSettings = {
		// dom : '<"eliminarTodosCaps">Bfrtip',
		dom : '<"eliminarTodosCaps">Bft',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"iDisplayLength" : DISPLAY_LENGTH,
		"order" : [],
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"data" : null, // 0
			"render" : function(data, type, row) {
				if (type === 'display') {
					if (data.tipoRenglon === T_RENGLON_AGRUPADOR) {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">' + '<button type="button" class="btn btn-xs btn-link" title="Eliminar caps" ' + (data.esNoGestionable ? 'disabled="disabled"' : '') + 'idCapSel="' + data.idPantalla + '" id="btnClonedCap' + data.idPantallaSelected + '">' + (data.esNoGestionable ? HTML_ICON_BLANK : HTML_ICON_MINUS) + '</button></div>';
					} else {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><i id="' + data.idPantallaSelected + '" ' + ICON_BLANK + '</i></div>';
					}
				}
				return data;
			},
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "codigoSociedad"
		}, // 1
		{
			"mData" : "idClienteLegado"
		}, // 1
		{
			"mData" : "idNumeroProveedor"
		}, // 2
		{
			"mData" : "tipoDocumento"
		},// 3
		{
			"mData" : "nroDocumentoSap"
		}, // 4
		{
			"mData" : "nroLegalSap"
		}, // 5
		{
			"mData" : "fechaEmision"
		}, // 6
		{
			"mData" : "numeroRenglon"
		},// 7
		{
			"mData" : "monedaSigno2"

		},// 7
		{
			"mData" : "importeMonedaOrigen", // 8
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.monedaSigno2);
			}
		}, {
			"mData" : "tipoCambio",// 9
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "importeGestionable", // 10
			"mRender" : function(data, type, row) {
				if (row.esReglonAgrupador) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoMonedaOrigen",// 11
			"mRender" : function(data, type, row) {
				if (!row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 12
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "importePesificadoDocAsociado", // 13
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, 'Pesos');
			}
		}, {
			"mData" : "importeMonedaOrigenDocAsociado",// 14
			"mRender" : function(data, type, row) {
				if (row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "tipoCambioDocAsociado", // 15
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			} // 15}
		}, {
			"mData" : "sinDifDeCambio",// <---------
			// Editable
			// checkbox 16
			"render" : function(data, type, row) {
				if (type === 'display') {
					var showCheck = true;
					if ((row.tipoRenglon === T_RENGLON_AGRUPADOR || row.esReglonAsociado) && !row.esNoGestionable && !row.esMismaMoneda) {
						showCheck = true;
					} else {
						showCheck = false;
					}

					return (showCheck) ? '<input type="checkbox" id="sinDifDeCambio" name="sinDifDeCambio" onclick="capCheckSaldoMaximo(this, \'sinDifDeCambio\')"' + (!$.isEmpty(data) ? ' checked ' : '') + '>' : '-';

				}
				return data;
			}
		}, ],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			if (aData.esGestionableRojo) {
				$(nRow).hide();
			} else {
				if ($(nRow).hasClass('even')) {
					$(nRow).removeClass('even');
				} else {
					$(nRow).removeClass('odd');
				}
				if (aData.tipoRenglon === T_RENGLON_AGRUPADOR) {

					if (aData.even === true) {
						// $('td', nRow).css('background-color',
						// '');
						$(nRow).addClass('even');
						$('td', nRow).css('border-bottom', 'none');
						trCapsSeleBackgroundColor = 'even';
					} else {
						// $('td', nRow).css('background-color',
						// '#F2F2F7');
						$(nRow).addClass('odd');
						$('td', nRow).css('border-bottom', 'none');
						trCapsSeleBackgroundColor = 'odd';
					}
				} else if (aData.esRenglonFin) {
					$(nRow).addClass(trCapsSeleBackgroundColor);
					$('td', nRow).css('border-top', 'none');
					$('td', nRow).css('color', CSS_SELECTED_ROW);
				} else {
					$(nRow).addClass(trCapsSeleBackgroundColor);
					$('td', nRow).css('border-top', 'none');
					$('td', nRow).css('border-bottom', 'none');
					$('td', nRow).css('color', CSS_SELECTED_ROW);
				}
			}
		},
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			targets : 'nosort',
			orderable : false
		}, {
			width : WiDTH_SEMAFORO,
			targets : 0
		} ]
	};
	// ---------------------------------------------------------------------------
	// Previsualizacion

	var clientesPrevSettings = {
		dom : '<"botonExcelclientesPrevSettings">Bfrtip',
		"sScrollY" : SCROLL_Y,
		"sScrollX" : "100%",
		"sScrollXInner" : "110%",
		"bScrollCollapse" : true,
		"iDisplayLength" : 10,
		"order" : [ [ 2, "asc" ] ],
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Clientes_Previsualizacion",
			className : 'excelbtn',
			"mColumns" : "visible"
		} ],
		"aoColumns" : [ {
			"mData" : "idClienteLegado",
			"visible" : false,
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "cuit"
		}, {
			"mData" : "idClienteLegado"
		}, {
			"mData" : "empresasAsociadas"
		}, {
			"mData" : "razonSocial"
		}, {
			"mData" : "origen"
		}, {
			"mData" : "segmentoAgencia"
		}, {
			"mData" : "descripcionHolding"
		}, {
			"mData" : "agenciaNegocio"
		} ],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'string-numero',
			targets : 'numeroOrder'
		} ]
	};

	var debitosPrevSettings = {
		dom : '<"botonExceldebitosPrevSettings">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"mData" : "idDebitoPantalla",
			"visible" : false,
			"searchable" : false
		},// 0
		{
			"mData" : "cliente"
		},// 1
		{
			"mData" : "cuenta"
		}, // 2
		{
			"mData" : "sistemaDescripcion"
		},// 3
		{
			"mData" : "tipoDoc"
		}, // 4
		{
			"mData" : "subtipoDocumento"
		}, // 5
		{
			"mData" : "nroDoc"
		}, // 6
		{
			"mData" : "numeroReferenciaMic"
		},// 7
		{
			"mData" : "fechaVenc"
		},// 8
		{
			"mData" : "saldo1erVencMonOrigen",// 9
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "moneda"
		},// 10
		{
			"mData" : "imp1erVenc",// 11
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "imp2doVenc",// 12
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "saldoPesificado",// 13
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "cobrarAl2doVenc",// 14
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<input type="checkbox" ' + ((data) ? 'checked' : '') + ' disabled>';
				}
				return data;
			}
		}, {
			"mData" : "destransferir3ros",// 15
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<input type="checkbox" ' + ((data) ? 'checked' : '') + ' disabled>';
				}
				return data;
			}
		}, {
			"mData" : "impACobrar",// 16
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		}, {
			"mData" : "estadoCptosDe3ros"
		},// 17
		{
			"mData" : "imp3rosTransf",// 18
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "estadoOrigen"
		}, // 19
		{
			"mData" : "marcaReclamoDescripcion"
		}, // 20
		{
			"mData" : "migradoOrigen"
		}, // 21
		{
			"mData" : "estadoDeimos"
		}, // 22
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		}, // 23
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 24
		{
			"mData" : "opeAsocAnalista"
		}, // 25
		{
			"mData" : "tipoCambio",// 26
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "fechaEmision"
		}, // 27
		{
			"mData" : "sinDifDeCambio", // 28
			"render" : function(data, type, row) {
				// if ( type === 'display' ) {
				// return '<input type="checkbox" ' +
				// ((data) ? 'checked': '') + '
				// disabled>';
				//		    		  
				// }
				// return data;
				if (type === 'display') {
					var showCheck = (CALIPSO_NAME === row.sistemaOrigen && row.moneda !== _monedaDelCobro && (row.tipoComprobanteEnum === 'FAC' || row.tipoComprobanteEnum === 'DEB' || row.tipoComprobanteEnum === 'CRE' || row.tipoComprobanteEnum === 'CTA') && $('#selectMotivo').val() == COMPENSACION);

					return (showCheck) ? '<input type="checkbox" disabled ' + ((data) ? 'checked ' : '') + '>' : '-';
				}
				return data;
			}
		}, {
			"mData" : "nroConvenio"
		}, // 29
		{
			"mData" : "cuota"
		}, // 30
		{
			"mData" : "fechaUltPagoParcial"
		}, // 31
		{
			"mData" : "descripcionErrorValidacion"
		} // 32
		],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		} ]
	};
	// MAR
	var otrosDebitosPrevSettings = {
		dom : '<"botonExcelOtrosDebitosPrevSettings">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],

		"aoColumns" : [ {
			"mData" : "sociedad",// 0
			"width" : 100,
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "sistema", // 1
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "tipoDebito", // 2
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "referenciaPago", // 3
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "cliente", // 4
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "numeroDocumento", // 5
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "fechaVencimiento", // 6
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "moneda", // 7
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "tipoCambio", // 8
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}, {
			"mData" : "importe", // 9
			"render" : function(data, type, row) {
				if (type === 'display') {
					return returnSignoMoneda(_monedaDelCobro) + (validarImporteValido(data) ? formatear(importeToFloat(data)) : '0');
				}
				return data;
			}
		}, {
			"mData" : "importeMonedaOrigen", // 10
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}

				return MONEDA_DOLAR_SIGNO_2 + data;
			}
		}, {
			"mData" : "sinDiferenciaDeCambio", // 11
			"render" : function(data, type, row) {
				if (type === 'display') {
					var showCheck = (row.moneda !== _monedaDelCobro && $('#selectMotivo').val() == COMPENSACION);

					return (showCheck) ? '<input type="checkbox" id="sinDiferenciaDeCambio" name="sinDiferenciaDeCambio" onclick="otrosDebitoCheck(this, \'sinDiferenciaDeCambio\')" ' + ((data == 'S') ? 'checked ' : '') + '>' : '<input type="checkbox" disabled>';
				}
				return data;
			}
		}, {
			"mData" : "nombreAdjunto", // 12
			"render" : function(data) {
				if ($.isEmpty(data)) {
					data = '-';
					return data;
				}
				return data;
			}
		}
		,{
				// 13
				"targets" : -1,
				"data" : null,
				"defaultContent" : '',
				"render" : function(data, type, row) {
					if (type === 'display') {
						if (!$.isEmpty(row.idAdjunto)) {
							return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargarAdjuntoOtroDeb" idOtroDebVar="' + data.idOtrosDebitoPantalla + '" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div>';
						}

						return data;
					}
					return data;
				},
				"searchable" : false,
				"bSortable" : false			
		} 
	],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		} ]
	};

	var creditosPrevSetting = {
		dom : '<"botonExcelcreditosPrevSettings">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"mData" : "idCreditoPantalla",
			"visible" : false,
			"searchable" : false
		}, // 0
		{
			"mData" : "idClienteLegado"
		},// 1
		{
			"mData" : "cuenta"
		}, // 2
		{
			"mData" : "sistemaDescripcion"
		},// 3
		{
			"mData" : "tipoCredito"
		}, // 4
		{
			"mData" : "subtipo", // 5
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			}
		}, {
			"mData" : "nroDoc"
		}, // 6
		{
			"mData" : "numeroReferenciaMic"
		}, // 7
		{
			"mData" : "fechaValor"
		},// 8
		{
			"mData" : "saldoMonOrigen",// 9
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "moneda"
		},// 10
		{
			"mData" : "impMonOrigen",// 11
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.moneda);
			}
		}, {
			"mData" : "impPesificado",// 12
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 13
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "importeAUtilizar",// 14
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		}, {
			"mData" : "fechaAltaCredito"
		}, // 15
		{
			"mData" : "fechaIngresoRecibo"
		},// 16
		{
			"mData" : "fechaDeposito"
		}, // 17
		{
			"mData" : "fechaTrans"
		},// 18
		{
			"mData" : "fechaEmision"
		},// 19
		{
			"mData" : "fechaVenc"
		},// 20
		{
			"mData" : "fechaUltimoMov"
		},// 21
		{
			"mData" : "tipoCambio",// 22
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "provincia"
		},// 23
		{
			"mData" : "cuit"
		},// 24
		{
			"mData" : "estadoOrigen"
		},// 25
		{
			"mData" : "motivo", // 26
			"mRender" : function(data, type, row) {
				if ($.isEmpty(data)) {
					return '-';
				}
				return data;
			}
		}, {
			"mData" : "marcaReclamoDescripcion"
		}, // 27
		{
			"mData" : "marcaMigradoDeimos"
		}, // 28
		{
			"mData" : "estadoDeimos"
		}, // 29
		{
			"mData" : "marcaPagoCompensacionEnProcesoShiva"
		}, // 30
		{
			"mData" : "marcaReversionDeCobroProcesoPendiente"
		}, // 31
		{
			"mData" : "opeAsocAnalista"
		}, // 32
		{
			"mData" : "descripcionErrorValidacion"
		}, // 34
		{
			"mData" : "orderByFecha",
			"visible" : false,
			"searchable" : false
		} // 35
		],
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		} ]
	};

	var mediosDePagosPrevSetting = {
		dom : '<"botonExcelmediosDePagosPrevSettings">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Medios_de_pagos_Previsualizacion",
			className : 'excelbtn',
			"mColumns" : "visible"
		} ],
		"aoColumns" : [ {
			"mData" : "clientesLegados",
			"visible" : false,
			"searchable" : false
		}, // 0
		{
			"mData" : "descMpTipoCredito"
		}, // 1
		{
			"mData" : "subTipoDescripcion"
		},// 2
		{
			"mData" : "importe", // 3
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		}, {
			"mData" : "nroActa"
		}, // 4
		{
			"mData" : "nroCompensacion"
		}, // 5
		{
			"mData" : "fecha"
		}, // 5
		{
			"mData" : "clientesLegados"
		}, // 6
		{
			"mData" : "provincia"
		}, {
			"mData" : "nroDeDocumentoInterno"
		} ]
	};

	var documentosCapPrevSettings = {
		dom : '<"botonExcelDocumentosCapPrevSettings">Bft',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"iDisplayLength" : DISPLAY_LENGTH,
		"order" : [],
		buttons : [ {
			extend : "excelHtml5"
		} ],
		"aoColumns" : [ {
			"mData" : "codigoSociedad",
			"width" : 10
		}, // 1
		{
			"mData" : "idClienteLegado"
		}, // 1
		{
			"mData" : "idNumeroProveedor"
		}, // 2
		{
			"mData" : "tipoDocumento"
		},// 3
		{
			"mData" : "nroDocumentoSap"
		}, // 4
		{
			"mData" : "nroLegalSap"
		}, // 5
		{
			"mData" : "fechaEmision"
		}, // 6
		{
			"mData" : "numeroRenglon"
		},// 7
		{
			"mData" : "monedaSigno2"

		},// 7
		{
			"mData" : "importeMonedaOrigen", // 8
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, row.monedaSigno2);
			}
		}, {
			"mData" : "tipoCambio",// 9
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "importeGestionable", // 10
			"mRender" : function(data, type, row) {
				if (row.esReglonAgrupador) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "saldoMonedaOrigen",// 11
			"mRender" : function(data, type, row) {
				if (!row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "saldoPesificado",// 12
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, 'Pesos');
				}
				return '-';
			}
		}, {
			"mData" : "importePesificadoDocAsociado", // 13
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, 'Pesos');
			}
		}, {
			"mData" : "importeMonedaOrigenDocAsociado",// 14
			"mRender" : function(data, type, row) {
				if (row.esReglonAsociado) {
					return truncoADosDecimalesImporte(data, row.monedaSigno2);
				}
				return '-';
			}
		}, {
			"mData" : "tipoCambioDocAsociado",
			"mRender" : function(data, type, row) {
				if (row.monedaSigno2 != _monedaDelCobro) {
					return data;
				}
				return '-';
			} // 15
		}, {
			"mData" : "sinDifDeCambio",// <---------
			// Editable
			// checkbox 29
			"render" : function(data, type, row) {
				if (type === 'display') {
					var showCheck = true;
					var salida = '-';
					if ((row.tipoRenglon === T_RENGLON_AGRUPADOR || row.esReglonAsociado) && !row.esNoGestionable && !row.esMismaMoneda) {
						showCheck = true;
					} else {
						showCheck = false;
					}
					if (showCheck) {
						salida = '<input type="checkbox" ' + (data ? 'checked' : '') + ' disabled>';
					}
					return salida;
				}
				return data;
			}
		}, {
			"mData" : "importeDiferenciaCambio",// 17
			"render" : function(data, type, row) {
				if (row.sinDifDeCambio == null || !row.sinDifDeCambio) {
					return row.importeDiferenciaCambio;
				} else {
					return '-';
				}
			}
		}, ],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			if (aData.esGestionableRojo) {
				$(nRow).hide();
			} else {
				if ($(nRow).hasClass('even')) {
					$(nRow).removeClass('even');
				} else {
					$(nRow).removeClass('odd');
				}
				if (aData.tipoRenglon === T_RENGLON_AGRUPADOR) {

					if (aData.even === true) {
						// $('td', nRow).css('background-color',
						// '');
						$(nRow).addClass('even');
						$('td', nRow).css('border-bottom', 'none');
						trCapsPreBackgroundColor = 'even';
					} else {
						// $('td', nRow).css('background-color',
						// '#F2F2F7');
						$(nRow).addClass('odd');
						$('td', nRow).css('border-bottom', 'none');
						trCapsPreBackgroundColor = 'odd';
					}
				} else if (aData.esRenglonFin) {
					$(nRow).addClass(trCapsPreBackgroundColor);
					$('td', nRow).css('border-top', 'none');
					$('td', nRow).css('color', CSS_SELECTED_ROW);
				} else {
					$(nRow).addClass(trCapsPreBackgroundColor);
					$('td', nRow).css('border-top', 'none');
					$('td', nRow).css('border-bottom', 'none');
					$('td', nRow).css('color', CSS_SELECTED_ROW);
				}
			}
		},
		'columnDefs' : [ // targets son los th con las class
		// date o importe
		{
			type : 'latam_date',
			targets : 'date'
		}, {
			type : 'comparador-currency',
			targets : 'importe'
		}, {
			targets : 'nosort',
			orderable : false
		} ]
	};

	var transaccionesPrevSettings = {
		dom : '<"botonExceltransaccionesPrevSettings">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5"
		} ],
		// se deshabilita el orden de las columnas
		"orderFixed" : [ [ 35, 'asc' ], [ 1, 'asc' ], [ 36, 'asc' ], [ 33, 'asc' ], [ 34, 'asc' ], [ 3, 'desc' ], [ 4, 'desc' ], [ 6, 'desc' ], [ 7, 'desc' ], [ 17, 'desc' ], [ 18, 'asc' ] ],
		"columnDefs" : [ {
			"targets" : 'nosort',
			"orderable" : false
		} ],

		"aoColumns" : [ {
			"mData" : "apocopeGrupo",
			"width" : 100  // 0
		}, 
		{
			"mData" : "numeroTransaccionFicticioFormateado",
			"width" : 100
		},// 1
		{
			"mData" : "estadoTransaccion"
		}, // 2
		{
			"mData" : "sistemaDoc"
		},// 3
		{
			"mData" : "tipoDocumento"
		}, // 4
		{
			"mData" : "subtipoDocumento",// 4
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoDocumentoDesc != null ? oData.subtipoDocumentoDesc : "";
			} //5
		}, {
			"mData" : "nroDoc"
		}, // 6
		{
			"mData" : "numeroReferencia"
		},// 7
		{
			"mData" : "fechaVenc"
		},// 8
		{
			"mData" : "moneda"
		},// 9
		{
			"mData" : "fechaCobro"
		},// 10
		{
			"mData" : "importe", // 11
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		},
		// deberia venir con 7 decimales con coma
		{
			"mData" : "tipoDeCambioFechaCobro",// 12
			"mRender" : function(data, type, row) {
				// cuando colorLetraRegistro es 0, no es
				// diferencia de cambio
				if (row.moneda !== _monedaDelCobro && row.colorLetraRegistro === '0') {
					return data;
				}
				return '-';
			}
		},
		// deberia venir con 7 decimales con coma
		{
			"mData" : "tipoDeCambioFechaEmision",// 13
			"mRender" : function(data, type, row) {
				// cuando colorLetraRegistro es 1, es
				// diferencia de cambio
				if (row.moneda !== _monedaDelCobro && row.colorLetraRegistro === '1') {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "importeAplicadoFechaEmisionMonedaOrigen",// 14
			"mRender" : function(data, type, row) {
				if (row.moneda !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, row.moneda);
				}
				return '-';
			}
		}, {
			"mData" : "sistemaMedioPago"
		},// 15
		{
			"mData" : "tipoMedioPago"
		},// 16
		{
			"mData" : "subtipoMedioPago",// 17
			'fnCreatedCell' : function(nTd, sData, oData, iRow, iCol) {
				nTd.title = oData.subtipoMedioPagoDesc != null ? oData.subtipoMedioPagoDesc : "";
			}
		}, {
			"mData" : "referenciaMedioPago"
		},// 18
		{
			"mData" : "fechaMedioPago"
		},// 19
		{
			"mData" : "monedaMedioPago"
		},// 20
		{
			"mData" : "importeMedioPago", // 21
			"mRender" : function(data, type, row) {
				return truncoADosDecimalesImporte(data, _monedaDelCobro);
			}
		}, {
			"mData" : "tipoDeCambioFechaCobroMedioPago",// 22
			"mRender" : function(data, type, row) {
				if (row.monedaMedioPago !== _monedaDelCobro) {
					return data;
				}
				return '-';
			}
		}, {
			"mData" : "importeAplicadoFechaEmisionMonedaOrigenMedioPago",// 23
			"mRender" : function(data, type, row) {
				if (row.monedaMedioPago !== _monedaDelCobro) {
					return truncoADosDecimalesImporte(data, row.monedaMedioPago);
				}
				return '-';
			}
		}, {
			"mData" : "intereses",// 24
			"mRender" : function(data, type, row) {
				var resultado = validarHabilitacionCampo(row);

				if (resultado > TRASLADA_INTERESES_NO) {
					if (resultado == TRASLADA_ASTERISCOS) {
						return "**";
					} else if (row.moneda !== '-') {
						return truncoADosDecimalesImporte(data, row.moneda);
					} else {
						return truncoADosDecimalesImporte(data, row.monedaMedioPago);
					}
				} else {
					// si es reintegro NO a proxima
					// factura, se muestra un guion
					return '-';
				}
			}
		}, {
			"mData" : "trasladarIntereses",// 25
			"render" : function(data, type, row, full) { // <---------
				// Editable
				// checkbox
				if (type === 'display') {
					var resultado = validarHabilitacionCampo(row);
					var disabled = false;
					var checkDisabled = false;
					var checkChecked = false;

					if (data) {
						// data devuelve true o false
						checkChecked = data;
					}

					if (resultado > TRASLADA_INTERESES_NO) {
						// cuando el interes es cero, se
						// muestra un guion
						if (TRASLADA_INTERESES_DIFERENCIA == resultado || TRASLADA_INTERESES_LECTURA == resultado) {
							disabled = true;
						}
						if (row.noHabilitadoTrasladarIntereses == true || row.esTrasladarInteresesObligatorio) {
							checkDisabled = true;
						}
						if (resultado == TRASLADA_ASTERISCOS) {
							disabled = false;
						}
					} else {
						// si es reintegro NO a proxima
						// factura, se muestra un guion
						disabled = true;
					}
//					if (!disabled) {
//						if (row.noHabilitadoTrasladarIntereses === 'true') {
//							checkDisabled = true;
//						}
//					}
//					if (resultado == 2 && row.fechaCobroMenorIgualFechaVto) {
//						disabled = false;
//					}

//					if (row.numeroGrupo != '0') {
//						if (row.generaInteresesParamUso) {
//							disabled = false;
//							//checkChecked = !data;
//						}
//					}

					// actualizo los datos de la sesion
					datosTablas.transacciones[full.row]['trasladarIntereses'] = data;

					// verifyAllSelected('trasladarTodosIntereses','checkTrasladar',
					// tablas.transaccionesPrev,
					// 'btnBuscarDebitos');
					
					return (!disabled) ? '<input type="checkbox" mostrarAsteriscos="'+row.mostrarAsteriscos+'"  class="checkTrasladar" onclick="checkTrasladarIntereses(this, \'trasladarIntereses\'); verifyAllSelected(\'trasladarTodosIntereses\',\'checkTrasladar\', tablas.transaccionesPrev, \'btnBuscarDebitos\')" ' + ((checkChecked) ? 'checked' : '') + ' ' + ((checkDisabled) ? 'disabled' : '') + '>' : '-';
					
				}
				return data;
			}
		}, {
			"mData" : "porcABonificar", // 26 <---------
			// Editable
			// input text //
			"render" : function(data, type, row, full) {
				if (type === 'display') {

					var disabled = false;

					// dentro de esta validacion
					// contemplo si la grilla se tiene
					// que ver en solo lectura o no
					// (resultado 4)
					var resultado = validarHabilitacionCampo(row);

					// si es reintegro a proxima
					// factura, se grisa el campo
					if (resultado > 0) {
						if (resultado == 1 || resultado == 3 || resultado == 4) {
							disabled = true;
							if (resultado == 3 || (resultado == 4 && row.intereses === '-')) {
								data = '';
							}
						}
					} else {
						// si es reintegro NO a proxima
						// factura, se grisa y se borra
						// el campo
						disabled = true;
						data = '';
					}

					// si se traladan intereses
					// deshabilito el campo para su
					// edicion
					if (row.trasladarIntereses) {
						disabled = true;
					}

					// seteo 100 para la primera vez que
					// se simula la transaccion
					if (data === '-') {
						data = '100';
					}

					// actualizo los datos de la sesion
					datosTablas.transacciones[full.row]['porcABonificar'] = data;
					
					if (resultado == TRASLADA_ASTERISCOS && !row.trasladarIntereses) {
						return '<div class="controls">' + '<input id="transPorcABonificarAsterisco" class="clasePorcABonificar" maxlength="3" type="text" value="' + 100 + '" style="margin: 0 auto; width:23px; text-align:right; font: normal 11px Verdana;" disabled >&nbsp;%<div></div></div>';
					} else if (resultado == TRASLADA_ASTERISCOS && row.trasladarIntereses) {
						return '<div class="controls">' + '<input id="transPorcABonificarAsterisco" class="clasePorcABonificar" maxlength="3" type="text" value="" style="margin: 0 auto; width:23px; text-align:right; font: normal 11px Verdana;" disabled >&nbsp;%<div></div></div>';
					}
					return '<div class="controls">' + '<input onkeypress="return validarInputNumericosEnteros(event);" id="transPorcABonificar" class="clasePorcABonificar" maxlength="3" type="text" value="' + data + '" style="margin: 0 auto; width:23px; text-align:right; font: normal 11px Verdana;" ' + 'onfocusout="return validarPorcentaje(this, tablas.transaccionesPrev, datosTablas.transacciones, \'porcABonificar\')"' + ' ' + ((disabled) ? 'disabled' : '') + '>&nbsp;%<div></div></div>';
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "importeABonificar", // 27
			// <---------
			// Editable
			// input
			// text //
			"mRender" : function(data, type, row, full) {
				if (type === 'display') {
					var disabled = false;
					var importe = '';

					var resultado = validarHabilitacionCampo(row);

					// si es reintegro a proxima
					// factura, se grisa el campo
					if (resultado > 0) {

						if (resultado == 1 || resultado == 3 || resultado == 4 || resultado == TRASLADA_ASTERISCOS) {
							disabled = true;
						}

						// si el importe no trae nada y
						// no tiene intereses entonces
						// lo vacio
						if ('-' === data && '-' === row.intereses) {
							importe = '';
						} else {

							// si el importe a bonificar
							// viene vacio quiere decir
							// que es la primer
							// simulacion
							// con lo cual lo completo
							// con el monto de intereses
							if ('-' === data && row.numeroGrupo == '0') {
								data = row.intereses.replace(row.moneda, "");
								// data =
								// data.replace("$","");
								
							}
							if (resultado != 3) {
								if (validarImporteValido(data)) {
									importe = formatear(importeToFloat(data));
								}
							}
						}
						if (row.numeroGrupo != '0') {
							data = '';
							importe = '';
						}
					} else {
						// si es reintegro NO a proxima
						// factura, se grisa y se borra
						// el campo
						disabled = true;
						importe = '';
					}

					// si se traladan intereses
					// deshabilito el campo para su
					// edicion
					if (row.trasladarIntereses && resultado != TRASLADA_ASTERISCOS) {
						disabled = true;
					}

					// actualizo los datos de la sesion
					datosTablas.transacciones[full.row]['importeABonificar'] = importe;

					return '<div class="controls" style="text-align: right;">' + (importe !== '' ? ((row.moneda !== '-') ? row.moneda : row.monedaMedioPago) : '-') + '&nbsp;<input onkeypress="return validarInputNumericosComaPunto(event);" id="transImporteABonificar" class="claseImporteABonificar" maxlength="12" type="text" value="' + importe + '" style="margin: 0 auto;width: 110px; text-align: right;  font: normal 11px Verdana;" ' + 'onfocusout="validarImporteIngresado(this, tablas.transaccionesPrev, datosTablas.transacciones, ' + full.col + ', \'importeABonificar\', \'moneda\', TRANS_COL_POS_MENSAJE, \'mensajeTransaccion\', \'intereses\', false)"' + ((disabled) ? 'disabled' : '') + '>' + '<div></div></div>';
				}
				return data;
			},
			"className" : "dt-body-center",
			"searchable" : false,
			"bSortable" : false
		}, {
			"mData" : "acuerdoDestinoCargos",// 28
			// <---------
			// Editable
			// input
			// text
			// //
			"render" : function(data, type, row, full) {
				if (type === 'display') {
					var resultado = validarHabilitacionCampo(row);
					var disabled = false;
					var subtipoDocumento = row.subtipoDocumento;
					var estadoAcuerdoDestinoCargos = row.estadoAcuerdoDestinoCargos;

					if (resultado > 0) {
						// si los intereses son 0, se
						// grisa y se borra
						if (resultado == 3 || resultado == 4 || resultado == TRASLADA_ASTERISCOS) {
							disabled = true;
							if (resultado == 3) {
								data = '';
							}
						}
						if ('-' === data) {
							data = '';
						}
					} else {
						// si es reintegro NO a proxima
						// factura, se grisa y se borra
						// el campo
						disabled = true;
						data = '';
					}

					// Si el documentos es diferente a MIC o Calipso, el acuerdo
					// destino quedará deshabilitado.
					if (row.numeroGrupo != '0') {
						data = '';
					}

					// actualizo los datos de la sesion
					datosTablas.transacciones[full.row]['acuerdoDestinoCargos'] = data;
					datosTablas.transacciones[full.row]['estadoAcuerdoDestinoCargos'] = estadoAcuerdoDestinoCargos;
					datosTablas.transacciones[full.row]['clienteAcuerdoDestinoCargos'] = row.clienteAcuerdoDestinoCargos;

					// TODO - a Fabio SIM 0 si el
					// acuerdo viene vacio y el usuario
					// no lo toca, cuando imputa tiene
					// que saltar error o cuando????
					// si reintegro credito a proxima
					// factura, va siempre deshabilitado
					// y se tiene que modificar del
					// tratamiento de la diferencia
					if (REINTEGRO_CRED_PROX_FAC_SUBTIPO === subtipoDocumento) {
						return '<div class="controls"><input type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" disabled ></div>';
					} else if (REINTEGRO_POR_CHEQUE_SUBTIPO === subtipoDocumento || REINTEGRO_PAGO_CUENTA_TERCEROS_SUBTIPO === subtipoDocumento || REINTEGRO_CREDITO_RED_INTEL_SUBTIPO === subtipoDocumento || REINTEGRO_TRANSFERENCIA_BAN_SUBTIPO === subtipoDocumento || ENVIO_A_GANANCIAS_SUBTIPO === subtipoDocumento || SALDO_A_PAGAR_SUBTIPO === subtipoDocumento || SALDO_A_COBRAR_SUBTIPO === subtipoDocumento) {
						// si es reintegro No a proxima
						// factura, no tiene sistema
						// entonces pongo el campo
						// deshabilitado sin nada
						return '<div class="controls"><input type="text" style="margin: 0 auto; width: 110px; font: normal 11px Verdana;" disabled ></div>';
					} else {
						// Si es debito a proxima
						// factura, si la factura es MIC
						// y el acuerdo de la factura
						// está activo
						if (MIC_DESC_PANTALLA === row.sistemaMedioPago && DEBITO_PROX_FAC_DESC === row.tipoMedioPago && ESTADO_FACTURACION_ACTIVO_DESC === estadoAcuerdoDestinoCargos && !disabled) {
							if ($.isEmpty(row.acuerdoDestinoCargosOriginal) || row.acuerdoDestinoCargosOriginal != '-') {
								return '<div class="controls"> ' + '<input onkeypress="return validarInputNumericosEnteros(event);"' + 'class="claseAcuerdoFacturacion" maxlength="10" type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" ' + 'onfocusout="validarAcuerdoContraClienteMic(this, false, false, ' + ((DEBITO_PROX_FAC_DESC === row.tipoMedioPago) ? 'true' : 'false') + ',' + row.idMedioPago + ')"' + ((disabled) ? 'disabled' : '') + '><div></div></div>';
							} else {
								return '<div class="controls"><input type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" disabled ></div>';
							}

						} else if (
						// Si es debito a proxima
						// factura, si la factura es MIC
						// y el acuerdo de la factura
						// está activo
						MIC_DESC_PANTALLA === row.sistemaDoc && '-' !== row.tipoDocumento && ESTADO_FACTURACION_ACTIVO_DESC === estadoAcuerdoDestinoCargos && !disabled) {
							if ($.isEmpty(row.acuerdoDestinoCargosOriginal) || row.acuerdoDestinoCargosOriginal != '-') {
								return '<div class="controls"> ' + '<input onkeypress="return validarInputNumericosEnteros(event);"' + 'class="claseAcuerdoFacturacion" maxlength="10" type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" ' + 'onfocusout="validarAcuerdoContraClienteMic(this, false, false, false, null)"' + ((disabled) ? 'disabled' : '') + '><div></div></div>';
							} else {
								return '<div class="controls"><input type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" disabled ></div>';
							}

						} else {

							if (MIC_DESC_PANTALLA === row.sistemaDoc || MIC_DESC_PANTALLA === row.sistemaMedioPago) {

								return '<div class="controls"> ' + '<input onkeypress="return validarInputNumericosEnteros(event);"' + 'class="claseAcuerdoFacturacion" maxlength="10" type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" ' + 'onfocusout="validarAcuerdoContraClienteMic(this, false, false, ' + ((DEBITO_PROX_FAC_DESC === row.tipoMedioPago) ? 'true' : 'false') + ',' + row.idMedioPago + ')"' + ((disabled) ? 'disabled' : '') + '><div></div></div>';
							} else {
								return '<div class="controls"> ' + '<input onkeypress="return validarInputNumericosEnteros(event);"' + 'class="claseAcuerdoFacturacion" maxlength="10" type="text" value="' + data + '" style="margin: 0 auto; width: 110px; text-align: right; font: normal 11px Verdana;" ' + 'onfocusout="validarAcuerdoContraClienteCLP(this, false, false, ' + ((DEBITO_PROX_FAC_DESC === row.tipoMedioPago) ? 'true' : 'false') + ',' + row.idMedioPago + ')"' + ((disabled) ? 'disabled' : '') + '><div></div></div>';
							}
						}
					}
				}
				return data;
			}
		}, {
			"mData" : "estadoAcuerdoDestinoCargos",// 
			"render" : function(data, type, row) {
				if (type === 'display') {

					var resultado = validarHabilitacionCampo(row);
					// var disabled = false;

					if (resultado > 0) {
						// cuando el interes es cero, se
						// muestra un guion
						if (resultado == 3 || resultado == 4) {
							// disabled = true;
							if (resultado == 3) {
								data = '-';
							}
						}
					} else {
						// si es reintegro NO a proxima
						// factura, se muestra un guion
						// disabled = true;
						data = '-';
					}

					return data;
				}
				return data;
			}
		}, {
			"mData" : "mensajeTransaccion"
		}, // 29
		{
			"mData" : "mensajeDebito"
		},// 30
		{
			"mData" : "mensajeCredito"
		},// 31
		// si esta columna viene con 1 es diferencia de
		// cambio
		{
			"mData" : "colorLetraRegistro",
			"visible" : false
		},// 32
		// Esta columna se usa para ordenar los
		// registros de diferencia de cambio
		{
			"mData" : "numeroTransaccionOriginal",
			"visible" : false
		}, // 33
		{
			"mData" : "numeroGrupo",
			"visible" : false
		}, // 34
		{
			"mData" : "numeroTransaccionFormateado",
			"visible" : false
		} // 35
		],
		"fnDrawCallback" : function(oSettings) {
			// quita el estilo css por defecto para el cambio de
			// color de fondo de los registros de la grilla de
			// transacciones
			// si se cambia de lugar probablemente no funcione
			$("tr", tablas.transaccionesPrev).removeClass("even odd");
			selectedRow.markRowSelected('transacciones', false, 'prevTransacciones', false);
		},
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			var registroAnterior = "1";
			var nroTransaccion = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull]).numeroTransaccion;
			var nroTransaccionAnterior = nroTransaccion;
			var apocopeGrupoAnterior = "";
			

			if (iDisplayIndexFull > 0) {
				registroAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).colorFondoRegistro;
				nroTransaccionAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).numeroTransaccion;
				apocopeGrupoAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).numeroGrupo;
			}
			$('td', nRow).css('border-top', 'none');
			$('td', nRow).css('border-bottom', 'none');
			
			// Al no venir ordenadas las transacciones por numero de transaccion, se cambia la logica para el cambio de color del fondo de los registros de la grilla.
				var rowColor = null;
//			if (/.*Autom.*/.test(aData.apocopeGrupo)) {
//				rowColor = rowColorDef[aData.apocopeGrupo.split('-')[1]];
//			} else {
				if (apocopeGrupoAnterior != aData.numeroGrupo) {
					if ($.isEmpty(rowColorDef.grupoCurrent )) {
						rowColorDef.grupoCurrent = 'Man_pair';
					} else if (rowColorDef.grupoCurrent == 'Man_odd') {
						rowColorDef.grupoCurrent = 'Man_pair';
					} else if (rowColorDef.grupoCurrent == 'Man_pair') {
						rowColorDef.grupoCurrent = 'Man_odd';
					}
				}
				rowColor = rowColorDef[rowColorDef.grupoCurrent];
			//}

			if (nroTransaccion !=nroTransaccionAnterior && aData.colorFondoRegistro === "1" && registroAnterior == "1") {
				$('td', nRow).css('border-top', '1px solid #E1E1E1');
				//$('td', nRow).css('background-color', '#F2F2F7');
				$('td', nRow).css('background-color', rowColor.pair.background_color);
				aData.colorFondoRegistro = "0";
			} else if (nroTransaccion === nroTransaccionAnterior && registroAnterior === "0") {
				//$('td', nRow).css('background-color', '#F2F2F7');
				$('td', nRow).css('background-color', rowColor.pair.background_color);
				aData.colorFondoRegistro = "0";
			} else if (nroTransaccion != nroTransaccionAnterior && aData.colorFondoRegistro === "0" && registroAnterior === "0") {
				$('td', nRow).css('border-top', '1px solid #E1E1E1');
				//$('td', nRow).css('background-color', '');
				$('td', nRow).css('background-color', rowColor.odd.background_color);
				
				aData.colorFondoRegistro = "1";
			} else if ( nroTransaccion === nroTransaccionAnterior && registroAnterior === "1") {
				//$('td', nRow).css('background-color', '');
				$('td', nRow).css('background-color', rowColor.odd.background_color);
				aData.colorFondoRegistro = "1";
			} else {
				if (aData.colorFondoRegistro === "0") {
					if (registroAnterior === "1") {
						$('td', nRow).css('border-top', '1px solid #E1E1E1');
					}
					//$('td', nRow).css('background-color', '#F2F2F7');
					$('td', nRow).css('background-color', rowColor.pair.background_color);
				} else {
					if (registroAnterior === "0") {
						$('td', nRow).css('border-top', '1px solid #E1E1E1');
					}
						//$('td', nRow).css('background-color', '');
					$('td', nRow).css('background-color', rowColor.odd.background_color);
				}
			}

			if (aData.colorLetraRegistro === "0") {
				$('td', nRow).css('color', '');
				//$('td', nRow).css('background-color', rowColor.odd.background_color);
			} else {
				$('td', nRow).css('color', CSS_SELECTED_ROW);
			}
			$('td:first', nRow).css('background-color', rowColor.pair.background_color);

//			var registroAnterior = "1";
//			var nroTransaccion = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull]).numeroTransaccion;
//			var nroTransaccionAnterior = nroTransaccion;
//			if (iDisplayIndexFull > 0) {
//				registroAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).colorFondoRegistro;
//				nroTransaccionAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).numeroTransaccion;
//			}
//			$('td', nRow).css('border-top', 'none');
//			$('td', nRow).css('border-bottom', 'none');
//
//			// Al no venir ordenadas las transacciones por numero de
//			// transaccion, se cambia la logica para el cambio de color del
//			// fondo de los registros de la grilla.
//			if (nroTransaccion != nroTransaccionAnterior && aData.colorFondoRegistro === "1" && registroAnterior == "1") {
//				$('td', nRow).css('border-top', '1px solid #E1E1E1');
//				$('td', nRow).css('background-color', '#F2F2F7');
//				aData.colorFondoRegistro = "0";
//			} else if (nroTransaccion === nroTransaccionAnterior && registroAnterior === "0") {
//				$('td', nRow).css('background-color', '#F2F2F7');
//				aData.colorFondoRegistro = "0";
//			} else if (nroTransaccion != nroTransaccionAnterior && aData.colorFondoRegistro === "0" && registroAnterior === "0") {
//				$('td', nRow).css('border-top', '1px solid #E1E1E1');
//				$('td', nRow).css('background-color', '');
//				aData.colorFondoRegistro = "1";
//			} else if (nroTransaccion === nroTransaccionAnterior && registroAnterior === "1") {
//				$('td', nRow).css('background-color', '');
//				aData.colorFondoRegistro = "1";
//			} else {
//				if (aData.colorFondoRegistro === "0") {
//					if (registroAnterior === "1") {
//						$('td', nRow).css('border-top', '1px solid #E1E1E1');
//					}
//					$('td', nRow).css('background-color', '#F2F2F7');
//				} else {
//					if (registroAnterior === "0") {
//						$('td', nRow).css('border-top', '1px solid #E1E1E1');
//					}
//					$('td', nRow).css('background-color', '');
//				}
//			}
//
//			if (aData.colorLetraRegistro === "0") {
//				$('td', nRow).css('color', '');
//			} else {
//				$('td', nRow).css('color', CSS_SELECTED_ROW);
//			}
		}
	};

	var comprobantesPrevSetting = {
		dom : '<"botonExcelcomprobantesPrevSettings">Brt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayStart" : 0,
		"bPaginate" : false,

		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_Comprobantes_Previsualizacion",
			className : 'excelbtn',
			"mColumns" : "visible"
		} ],
		"aoColumns" : [ {
			"mData" : "idComprobante",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "nombreArchivo"
		}, {
			"mData" : "descripcionArchivo"
		}, {
			"targets" : -1,
			"data" : null,
			"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>',
			"searchable" : false,
			"bSortable" : false
		} ]
	};

	var comprobantesAplManual = {
		dom : 'rt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayStart" : 0,
		"bPaginate" : false,

		// buttons : [ {
		// extend : "excelHtml5",
		// text : "Excel",
		// title : "Cobro_Comprobantes_Previsualizacion",
		// className : 'excelbtn',
		// "mColumns" : "visible"
		// } ],
		"aoColumns" : [ {
			"mData" : "idComprobante",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "nombreArchivo"
		}, {
			"mData" : "descripcionArchivo"
		}, {
			"targets" : -1,
			"data" : null,
			"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button action="eliminar" type="button" class="btn btn-xs btn-link" title="Eliminar comprobante"><i class="icon-trash bigger-120"></i></button></div>',
			"searchable" : false,
			"bSortable" : false
		} ]
	};
	// ***********************************************************************************************
	// Eventos que reinician las tablas segun los tabs
	var reIniciarTablas = {
		'debitos' : function(destroy) {
			// Para para poder rehablitar el boton export excel
			if (destroy) {
				tablas.clientesDebitos.fnDestroy();
				tablas.debitosSeleccionados.fnDestroy();
				tablas.debitos.fnDestroy();
			}

			tablas.clientesDebitos = $("#clientesDebitos").dataTable(clientesDebitosSettings);
			tablas.debitosSeleccionados = $("#debitosSeleccionados").dataTable(debitosSelSettings);
			tablas.debitos = $("#debitos").dataTable(debitosSearchSettings);
			$("div.agregarTodosDebitos").html('<button type="button" id="btnAgregarTodosDebitos" class="customAll" disabled><font size=1,5>Agregar todos</font></button>');
			// crearTablaFixedColumn( datosTablas, tablas ,
			// "clientesDebitos" );
			crearTablaFixedColumn(datosTablas, tablas, "debitos");
			crearTablaFixedColumn(datosTablas, tablas, "debitosSeleccionados");

			// fixedColumns(tablas.debitos, 1, 0);
			// fixedColumns(tablas.debitosSeleccionados, 1, 0);
			$("div.eliminarTodosDebitos").html('<button type="button" id="btnEliminarTodosDebitos" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');
			if (destroy) {
				agregarBotonExcelBasico(tablas.clientesDebitos.api(), "Cobro_Clientes_Seleccionados", "div.botonExcelClientesDebitos");
				agregarBotonExcelBasicoDebitosSelSettings(tablas.debitosSeleccionados.api(), "Cobro_Debitos_Seleccionados", "div.eliminarTodosDebitos");
				agregarBotonExcelTodosDebitos(tablas.debitos.api(), "Cobro_Debitos_sin_Seleccionar", "div.agregarTodosDebitos");
			}

			$('#btnEliminarTodosDebitos').click(function() {
				var mensaje = "Desea&nbsp;eliminar&nbsp;todos&nbsp;los&nbsp;Debitos&nbsp;de&nbsp;la&nbsp;grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						aparienciaButton.descubrirButtonPlusDocumentosTodos(tablas.debitos, tablas.debitosSeleccionados);
						detalleDebitosDelCobro.subTodosDocumentos();
						tablas.debitosSeleccionados.fnClearTable();
						datosTablas.debitosSeleccionados = [];
						totalDeDocumentos.actualizar();
						contadorMensajesError.resetearError();
						contadorMensajesErrorGuardableDebitos.resetearError();
						$('#btnAgregarTodosDebitos').attr('disabled', false);
						$('#btnEliminarTodosDebitos').attr('disabled', true);
						selectedRow.clearVariables('debitos');

					}
				});
			});
			$('#debitos tbody').off('click');
			$('#debitos tbody').on('click', 'button', function() {
				if (tablas.debitosSeleccionados.fnGetData() != null) {
					$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
				}
				var idDebito = $(this).attr('idDebitoVar2');
				var existe = false;

				$(tablas.debitosSeleccionados.fnGetData()).each(function(j, debito) {
					if (debito.idDebitoPantalla == idDebito) {
						existe = true;
						return false;
					}
				});

				var aData = null;
				var esAgregable = '';
				if (!existe) {
					$(tablas.debitos.fnGetData()).each(function(j, debitos) {
						if (debitos.idDebitoPantalla == idDebito) {
							aData = tablas.debitos.fnGetData()[j];
							esAgregable = detalleDebitosDelCobro.verificar(aData.tipoDoc);

							if ($('#cantidadDebitosEnGrilla').val() == CANTIDAD_MAX_DEBITOS) {
								esAgregable = 'ERROR: La cantidad total de debitos que se intentan subir superan el maximo de ' + CANTIDAD_MAX_DEBITOS + 'debitos esperados.';
							}
							if ($.isEmpty(esAgregable)) {
								$('#btnEliminarTodos').attr('disabled', false);
							} else {
								aData = null;
							}

							return false;
						}
					});
					if ($.isEmpty(esAgregable) && aData != null) {
						var agregar = [];
						if (!/-$/.test(aData.impACobrar)) {
							aData.impACobrar = formatear(importeToFloat(aData.impACobrar));
						}
						agregar.push(aData);
						$('#errorRespuestaDebitos').text('');
						validarEstadoDeimos(agregar, tablas.debitos, tablas.debitosSeleccionados, datosTablas.debitosSeleccionados, 'errorRespuestaDebitos', 'btnEliminarTodosDebitos');
					} else {
						$('#errorRespuestaDebitos').text(esAgregable);
					}
					// Este metodo recorre las 2
					// tablas para verificar si
					// estan todos seleccionados
					// if
					// (verificaTodosSeleccionados(tablas.debitos,
					// tablas.debitosSeleccionados,
					// 'idPantalla')) {
					// $('#btnAgregarTodos').attr('disabled',
					// true);
					// }
					if (tablas.debitosSeleccionados.fnGetData() != null) {
						$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
					}
				} else {
					// rev: ¿mensaje comunicando
					// que el cliente ya existe?
				}
			});

			$('#btnAgregarTodosDebitos').click(function() {
				var mensaje = "Desea&nbsp;agregar&nbsp;todos&nbsp;los&nbsp;Debitos&nbsp;de&nbsp;la&nbsp;grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						var esAgregable = detalleDebitosDelCobro.verificarLista(tablas.debitos.fnGetData(), false);
						if ($.isEmpty(esAgregable)) {
							$('#errorRespuestaDebitos').text('');
							validarEstadoDeimos(tablas.debitos.fnGetData(), tablas.debitos, tablas.debitosSeleccionados, datosTablas.debitosSeleccionados, 'errorRespuestaDebitos', 'btnEliminarTodosDebitos');
							$('#btnAgregarTodosDebitos').attr('disabled', true);
						} else {
							$('#errorRespuestaDebitos').text(esAgregable[0].salida);
						}
					}
				});
			});
		},
		'creditos' : function(destroy) {
			// Para para poder rehablitar el boton export excel
			if (destroy) {
				tablas.clientesCreditos.fnDestroy();
				tablas.creditosSeleccionados.fnDestroy();
				tablas.creditos.fnDestroy();
			}
			// Para para poder hablitar el boton export excel
			tablas.clientesCreditos = $("#clientesCreditos").dataTable(clientesCreditosSettings);
			tablas.creditosSeleccionados = $("#creditosSeleccionados").dataTable(creditosSelSetting);
			tablas.creditos = $("#creditos").dataTable(creditosSearchSettings);

			$("div.eliminarTodosCreditos").html('<button type="button" id="btnEliminarTodosCreditos" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');
			$("div.agregarTodosCreditos").html('<button type="button" id="btnAgregarTodosCreditos" class="customAll" disabled><font size=1,5>Agregar todos</font></button>');

			// fixedColumns(tablas.creditos, 1, 0);
			// fixedColumns(tablas.creditosSeleccionados, 1, 0);
			// crearTablaFixedColumn( datosTablas, tablas ,
			// "clientesCreditos" );
			crearTablaFixedColumn(datosTablas, tablas, "creditos");
			crearTablaFixedColumn(datosTablas, tablas, "creditosSeleccionados");

			if (destroy) {
				agregarBotonExcelBasico(tablas.clientesCreditos.api(), "Cobro_Clientes_Seleccionados", "div.botonExcelClientesCreditos");
				agregarBotonExcelEliminarTodosCreditos(tablas.creditosSeleccionados.api(), "Cobro_Creditos_Seleccionados", "div.eliminarTodosCreditos");
				agregarBotonExcelAgregarTodosCreditos(tablas.creditos.api(), "Cobro_Creditos_Sin_Seleccionar", "div.agregarTodosCreditos");
			}

			$('#btnEliminarTodosCreditos').click(function() {
				var mensaje = "Desea&nbsp;eliminar&nbsp;todos&nbsp;los&nbsp;Creditos&nbsp;de&nbsp;la&nbsp;grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						aparienciaButton.descubrirButtonPlusDocumentosTodos(tablas.creditos, tablas.creditosSeleccionados);
						tablas.creditosSeleccionados.fnClearTable();
						datosTablas.creditosSeleccionados = [];
						contadorMensajesError.resetearError('CobroCreditoDto');
						contadorMensajesErrorGuardableCreditos.resetearError();
						totalDeDocumentos.actualizar();
						$('#btnEliminarTodosCreditos').attr('disabled', true);
						$('#btnAgregarTodosCreditos').attr('disabled', false);
						selectedRow.clearVariables('creditos');
					}
				});
			});

			$('#creditos tbody').off();
			$('#creditos tbody').on('click', 'button', function() {

				var idCredito = $(this).attr('idCreditoVar2');
				var existe = false;
				$(tablas.creditosSeleccionados.fnGetData()).each(function(j, seleccionado) {
					if (seleccionado.idCreditoPantalla == idCredito) {
						existe = true;
						return false;
					}
				});

				if (!existe) {
					var aData = null;
					$(tablas.creditos.fnGetData()).each(function(j, creditos) {
						if (creditos.idCreditoPantalla == idCredito) {
							$('#btnEliminarTodos').attr('disabled', false);
							aData = tablas.creditos.fnGetData()[j];
							return false;
						}
					});
					var agregar = [];
					agregar.push(aData);
					validarEstadoDeimos(agregar, tablas.creditos, tablas.creditosSeleccionados, datosTablas.creditosSeleccionados, 'errorRespuestaCreditos', 'btnEliminarTodosCreditos');
				}
			});

			$('#btnAgregarTodosCreditos').click(function() {
				var mensaje = "Desea&nbsp;agregar&nbsp;todos&nbsp;los&nbsp;Creditos&nbsp;de&nbsp;la&nbsp;grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						validarEstadoDeimos(tablas.creditos.fnGetData(), tablas.creditos, tablas.creditosSeleccionados, datosTablas.creditosSeleccionados, 'errorRespuestaCreditos', 'btnEliminarTodosCreditos');
						$('#btnAgregarTodosCreditos').attr('disabled', true);
					}
				});
			});

		},
		'otrosDebitos' : function(destroy) {
			if (destroy) {
				tablas.clientesOtrosDebitos.fnDestroy();
				tablas.otrosDebSeleccionados.fnDestroy();
				tablas.comprobantesOtrosDebito.fnDestroy();

				if (datosTablas.otrosDebitosSeleccionados.length > 0) {
					for (var indice = 0; indice < datosTablas.otrosDebitosSeleccionados.length; indice++) {
						if (!$.isEmpty(datosTablas.otrosDebitosSeleccionados[indice].idAdjunto)) {
							if (!$.isEmpty(datosTablas.comprobantesOtrosDebito[0])) {
								if (datosTablas.otrosDebitosSeleccionados[indice].idAdjunto !== datosTablas.comprobantesOtrosDebito[0].idComprobante) {
									datosTablas.idsAdjuntosOtrosDeb.push(datosTablas.comprobantesOtrosDebito[0].idComprobante);
								}
							}
						}
					}
				} else if (!$.isEmpty(datosTablas.comprobantesOtrosDebito[0])) {
					datosTablas.idsAdjuntosOtrosDeb.push(datosTablas.comprobantesOtrosDebito[0].idComprobante);
				}

				datosTablas.comprobantesOtrosDebito = [];
				$("#btnAdjComprobanteOtrosDeb").prop('disabled', false);
			}
			cargarSolapaOtrosDeb();
			cargarComboSinDiferenciaCambio();

			$('#btnEliminarTodosOtrosDebitos').click(function() {
				var mensaje = "¿Desea eliminar todos los débitos de la grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						/*
						 * guardo los idsAdjuntos para eliminarlos al momento de
						 * cambiar de pantalla o guardar el cobro
						 */
						for (var indice = 0; indice < datosTablas.otrosDebitosSeleccionados.length; indice++) {
							if (!$.isEmpty(datosTablas.otrosDebitosSeleccionados[indice].idAdjunto)) {
								datosTablas.idsAdjuntosOtrosDeb.push(datosTablas.otrosDebitosSeleccionados[indice].idAdjunto);
							}
						}

						tablas.otrosDebSeleccionados.fnClearTable();
						datosTablas.otrosDebitosSeleccionados = [];
						totalDeDocumentos.actualizar();
						contadorMensajesError.resetearError();
						contadorMensajesErrorGuardableOtrosDebitos.resetearError();
						$('#btnEliminarTodosOtrosDebitos').attr('disabled', true);
						selectedRow.clearVariables('otrosDebSeleccionados');

					}
				});
			});

			if (destroy) {
				agregarBotonExcelBasicoOtrosDebitosSelSettings(tablas.otrosDebSeleccionados.api(), "Cobro_Otros_Debitos_Seleccionados", "div.eliminarTodosOtrosDebitos");
			}
		},
		'mediosPagos' : function(destroy) {
			// Para para poder rehablitar el boton export excel
			if (destroy) {
				tablas.clientesMedios.fnDestroy();
				tablas.mediosPagos.fnDestroy();
				tablas.documentosCaps.fnDestroy();
				tablas.fixedColumnsDocumentosCaps = false;
				tablas.documentosCapsSeleccionados.fnDestroy();
				tablas.fixedColumnsDocumentosCapsSeleccionados = false;
			}
			tablas.clientesMedios = $("#clientesMedios").dataTable(clientesSelMPagoSetting);
			tablas.mediosPagos = $("#mediosPagos").dataTable(mediosDePagosSetting);

			tablas.documentosCaps = $("#caps").dataTable(capsSearchSettings);
			tablas.documentosCapsSeleccionados = $("#capSeleccionados").dataTable(capsSelSettings);

			$("div.eliminarTodosMedios").html('<button type="button" id="btnEliminarTodosMedios" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');

			crearTablaFixedColumn(datosTablas, tablas, "mediosPagos");

			$("div.eliminarTodosCaps").html('<button type="button" id="btnEliminarTodosCaps" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');
			$("div.agregarTodosCaps").html('<button type="button" id="btnAgregarTodosCaps" class="customAll" disabled><font size=1,5>Agregar todos</font></button>');
			if (destroy) {
				agregarBotonExcelBasico(tablas.clientesMedios.api(), "Cobro_Clientes_Seleccionados", "div.botonExcelClientesMedios");
				agregarBotonExcelBasico(tablas.mediosPagos.api(), "Cobro_Medios_de_pagos_Seleccionados", "div.eliminarTodosMedios");
				agregarBotonExcel(tablas.documentosCaps.api(), "Cobro_Documentos_caps_a_seleccionar", "div.agregarTodosCaps", function(data, column, row) {
					if (column === 0) {
						return '';
					}
					return data;
				});

				agregarBotonExcel(tablas.documentosCapsSeleccionados.api(), "Cobro_Documentos_caps_a_seleccionados", "div.eliminarTodosCaps", function(data, column, row) {
					if (column === 0) {
						return '';
					}

					if (column === 17) {
						var esRenglonAgrupador = tablas.documentosCapsSeleccionados.fnGetData(row).esReglonAgrupador;
						var esRenglonAsociado = tablas.documentosCapsSeleccionados.fnGetData(row).esReglonAsociado;
						var esMismaMoneda = tablas.documentosCapsSeleccionados.fnGetData(row).esMismaMoneda;
						var sinDifDeCambio = tablas.documentosCapsSeleccionados.fnGetData(row).sinDifDeCambio;

						if ((esRenglonAgrupador || esRenglonAsociado) && !esMismaMoneda) {
							if (sinDifDeCambio) {
								return 'SI';
							} else {
								return 'NO';
							}
						} else if (data == '-') {
							return data;
						}
					}
					return data;
				});
			}
			$('#btnEliminarTodosMedios').click(function() {
				var mensaje = "Desea&nbsp;eliminar&nbsp;todos&nbsp;los&nbsp;Medios&nbsp;de&nbsp;Pago&nbsp;de&nbsp;la&nbsp;grilla?";
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						tablas.mediosPagos.fnClearTable();
						datosTablas.mediosPagos = [];
						datosTablas.documentosCapsSeleccionados = [];
						totalDeDocumentos.actualizar();
						actualizarControles('medio');
						importe = totalDeDocumentos.actualizarCap();
					}
				});
			});
			// Tablas cap seleccionar de documentos cap busqueda
			registrarEventoCapsSearch();
		},
		'previsualizacion' : function(destroy) {
			// Para para poder rehablitar el boton export excel
			if (destroy) {
				tablas.clientesPrev.fnDestroy();
				tablas.debitosPrev.fnDestroy();
				tablas.otrosDebitosPrev.fnDestroy();
				tablas.creditosPrev.fnDestroy();
				tablas.mediosPagosPrev.fnDestroy();
				tablas.documentosCapPrev.fnDestroy();
				tablas.comprobantesPrev.fnDestroy();
				tablas.transaccionesPrev.fnDestroy();
				tablas.comprobantesAplManual.fnDestroy();
			}// MAR
			tablas.clientesPrev = $("#prevClientes").dataTable(clientesPrevSettings);
			tablas.debitosPrev = $("#prevDebitos").dataTable(debitosPrevSettings);
			tablas.otrosDebitosPrev = $("#prevOtrosDebitos").dataTable(otrosDebitosPrevSettings);
			tablas.creditosPrev = $("#prevCreditos").dataTable(creditosPrevSetting);
			tablas.mediosPagosPrev = $("#prevMediosPagos").dataTable(mediosDePagosPrevSetting);
			tablas.documentosCapPrev = $("#prevDocumentosCap").dataTable(documentosCapPrevSettings);
			tablas.comprobantesPrev = $("#prevComprobantes").dataTable(comprobantesPrevSetting);
			tablas.transaccionesPrev = $("#prevTransacciones").dataTable(transaccionesPrevSettings);
			tablas.comprobantesAplManual = $("#comprobantesAplManual").dataTable(comprobantesAplManual);

			if (destroy) {

				agregarBotonExcelBasico(tablas.clientesPrev.api(), "Cobro_Clientes_Previsualizacion", "div.botonExcelclientesPrevSettings");
				agregarBotonExceldebitosPrev(tablas.debitosPrev.api(), "Cobro_Debitos_Previsualizacion", "div.botonExceldebitosPrevSettings");
				agregarBotonExcelOtrosDebitosPrev(tablas.otrosDebitosPrev.api(), "Cobro_OtrosDebitos_Previsualizacion", "div.botonExcelOtrosDebitosPrevSettings");
				agregarBotonExcelcreditosPrev(tablas.creditosPrev.api(), "Cobro_Creditos_Previsualizacion", "div.botonExcelcreditosPrevSettings");
				agregarBotonExcelBasico(tablas.mediosPagosPrev.api(), "Cobro_Medios_de_pagos_Previsualizacion", "div.botonExcelmediosDePagosPrevSettings");
				agregarBotonExcelBasico(tablas.documentosCapPrev.api(), "Cobro_Documentos_Cap_Previsualizacion", "div.botonExcelDocumentosCapPrevSettings");
				agregarBotonExcelBasico(tablas.comprobantesPrev.api(), "Cobro_Comprobantes_Previsualizacion", "div.botonExcelcomprobantesPrevSettings");
				// agregarBotonExcelBasico(tablas.comprobantesAplManual
				// .api(),
				// "Cobro_Comprobantes_Previsualizacion",
				// "div.botonExcelcomprobantesPrevSettings");
				agregarBotonExceltransaccionesPrev(tablas.transaccionesPrev.api(), "Cobro_Transacciones_Previsualizacion", "div.botonExceltransaccionesPrevSettings");
			}

			tablas.debitosPrev.fnSort([ [ 8, 'asc' ] ]);
			tablas.creditosPrev.fnSort([ [ 34, 'asc' ] ]);
			tablas.mediosPagosPrev.fnSort([ [ 5, 'asc' ] ]);
		}
	};

	// ***********************************************************************************************
	// Eventos que dibuja los tabs

	var drawTabClientes = function() {
		if (datosTablas.clientesSeleccionados.length > 0) {
			tablas.clientesSeleccionados.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			tablas.clientesSeleccionados.fnAdjustColumnSizing(true);
		}

		// tablas.clientes.fnDestroy();
		crearBuscadorClientes();

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);
		} else if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos") {
			$('#bloqueSubirDebitos').hide();
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);
	};

	var drawTabDebitos = function() {
		// Rehabilito las tablas para export a excel
		reIniciarTablas.debitos(true);
		//
		// Verifico la longitud de los clientes
		var allClientesId11Pos = true;
		for (var i = 0; i < datosTablas.clientesSeleccionados.length; i++) {
			if (datosTablas.clientesSeleccionados[i].idClienteLegado.length <= 10) {
				allClientesId11Pos = false;
			}
		}
		// Cargo los datos
		if (datosTablas.clientesSeleccionados.length > 0) {
			$('#debTodosClientes').prop('checked', true);
			$('#debTodosClientes').prop('disabled', false);
			$('#btnBuscarDebitos').prop('disabled', allClientesId11Pos);
			tablas.clientesDebitos.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			$('#debTodosClientes').prop('checked', false);
			$('#debTodosClientes').prop('disabled', true);
			$('#btnBuscarDebitos').prop('disabled', true);
			tablas.clientesDebitos.fnAdjustColumnSizing(false);
		}

		if (datosTablas.debitosSeleccionados.length > 0) {
			$('#btnEliminarTodosDebitos').attr('disabled', false);
			tablas.debitosSeleccionados.fnAddData(datosTablas.debitosSeleccionados, true);
			// tablas.debitosSeleccionados.fnAdjustColumnSizing(true);
		} else {
			$('#btnEliminarTodosDebitos').attr('disabled', true);
			tablas.debitosSeleccionados.fnAdjustColumnSizing(false);
		}

		// crearBuscadorDebitos();
		tablas.debitosSeleccionados.fnSort([ [ 10, 'asc' ] ]);

		selectedRow.markRowSelected('debitos', true, 'debitosSeleccionados', true);

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);

			$('#bloqueSubirDebitos').hide();
			$('.bloqueBuscarDebitos').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos") {
			$('.bloqueDebitos :input').attr('disabled', true);
			$('.bloqueDebitos :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);
		// dispara las validaciones en los debitos importado de
		// los campos editabnles
		validarDebitosExportados();
		fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
	};

	var drawTabOtrosDebitos = function() {

		reIniciarTablas.otrosDebitos(true);
		if (datosTablas.clientesSeleccionados.length > 0) {
			tablas.clientesOtrosDebitos.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			tablas.clientesOtrosDebitos.fnAdjustColumnSizing(false);
		}
		if (datosTablas.comprobantesOtrosDebito.length > 0) {
			tablas.comprobantesOtrosDebito.fnAddData(datosTablas.comprobantesOtrosDebito, true);
		} else {
			tablas.comprobantesOtrosDebito.fnAdjustColumnSizing(false);
		}
		if (datosTablas.otrosDebitosSeleccionados.length > 0) {
			$('#btnEliminarTodosOtrosDebitos').attr('disabled', false);
			tablas.otrosDebSeleccionados.fnAddData(datosTablas.otrosDebitosSeleccionados, true);
			// tablas.debitosSeleccionados.fnAdjustColumnSizing(true);
		} else {
			$('#btnEliminarTodosOtrosDebitos').attr('disabled', true);
		}
		if (objetoVacio(CONFOTROSDEBITOS)) {
			$('#alertErrorGuardado').show();
			$('#errorGuardado').text("No existe configuracion de campos de la solapa Otros debitos.");
		} else {
			cargarComboSinDiferenciaCambio();
			vaciarCombo('selectTipoDebitoOtrosDeb');
			vaciarCombo('selectMonedaOtrosDeb');
			vaciarCombo('selectSistemaOtrosDeb');
			$('#selectTipoDebitoOtrosDeb').prop('disabled', true);
			$('#selectMonedaOtrosDeb').prop('disabled', true);
			$('#selectSistemaOtrosDeb').prop('disabled', true);
			crearCombo('selectSociedadOtrosDeb', CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)], CONFCOMBOSOCIEDAD);
			visualizarControlesForm();
		}

		tablas.comprobantesOtrosDebito.fnAdjustColumnSizing();

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirOtrosDebitos').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirOtrosDebitos').hide();
			$('.bloqueSociedadYSistema').hide();
			$('.bloqueReferenciaPago').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos") {
			$('.bloqueOtrosDebitos :input').attr('disabled', true);
			$('.bloqueOtrosDebitos :button').attr('disabled', true);
			$('#bloqueSubirOtrosDebitos').hide();
		}

		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);

	};

	var drawTabCreditos = function() {
		// Rehabilito las tablas para export a excel
		reIniciarTablas.creditos(true);
		//
		if (datosTablas.clientesSeleccionados.length > 0) {
			$('#credTodosClientes').prop('checked', true);
			$('#credTodosClientes').prop('disabled', false);
			$('#btnBuscarCreditos').prop('disabled', false);
			tablas.clientesCreditos.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			$('#credTodosClientes').prop('checked', false);
			$('#credTodosClientes').prop('disabled', true);
			$('#btnBuscarCreditos').prop('disabled', true);
			tablas.clientesCreditos.fnAdjustColumnSizing(false);
		}

		if (datosTablas.creditosSeleccionados.length > 0) {
			$('#btnEliminarTodosCreditos').attr('disabled', false);
			tablas.creditosSeleccionados.fnAddData(datosTablas.creditosSeleccionados, true);
			// tablas.creditosSeleccionados.fnAdjustColumnSizing(true);
		} else {
			$('#btnEliminarTodosCreditos').attr('disabled', true);
			tablas.creditosSeleccionados.fnAdjustColumnSizing(false);
		}

		// crearBuscadorCreditos();
		tablas.creditosSeleccionados.fnSort([ [ 9, 'asc' ] ]);
		selectedRow.markRowSelected('creditos', true, 'creditosSeleccionados', true);
		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);

			$('.bloqueBuscarCreditos').hide();
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);

		fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
	};

	var drawTabMediosDePago = function() {
		// Rehabilito las tablas para export a excel
		reIniciarTablas.mediosPagos(true);
		//
		if (datosTablas.clientesSeleccionados.length > 0) {
			tablas.clientesMedios.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			tablas.clientesMedios.fnAdjustColumnSizing(false);
		}
		if (COMPENSACION === $('#selectMotivo').val()) {
			$('#seleccionarTodos').hide();
		} else {
			$('#seleccionarTodos').show();
		}
		if (datosTablas.mediosPagos.length > 0) {
			$('#btnEliminarTodosMedios').attr('disabled', false);
			tablas.mediosPagos.fnAddData(datosTablas.mediosPagos, true);

		} else {
			$('#btnEliminarTodosMedios').attr('disabled', true);
			tablas.mediosPagos.fnAdjustColumnSizing(false);
			if (datosTablas.documentosCapsSeleccionados.length > 0) {
				$('#btnEliminarTodosCaps').attr('disabled', false);
				tablas.documentosCapsSeleccionados.fnAddData(datosTablas.documentosCapsSeleccionados, true);
			} else {
				$('#btnEliminarTodosCaps').attr('disabled', true);
				tablas.documentosCapsSeleccionados.fnAdjustColumnSizing(false);
			}
		}

		actualizarControles('medio');

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#inputMedioDePago').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);

			$('#inputMedioDePago').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos") {
			$('#inputMedioDePago').hide();
		} else {
			$('#selectMedioPago').val("").change();
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);

		$('#monedaOperacionMpago').text($("#selectMonedaOperacion option:selected").val());

	};

	var drawTabComprobantes = function() {
		if (datosTablas.comprobantes.length > 0) {
			tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);
		} else {
			tablas.comprobantes.fnAdjustColumnSizing(true);
		}

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueAgregarComprobante').hide();
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);

			$('#bloqueAgregarComprobante').hide();
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueAgregarComprobante').hide();
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);
	};

	var drawTabPrev = function() {
		// Rehabilito las tablas para export a excel
		reIniciarTablas.previsualizacion(true);
		// eventPre = false;
		$('#prevObservText2').val($('#prevObservText').val());
		if (!$.isEmpty($('#prevObservTextAnterior').val())) {
			$('#prevObservTextAnterior2').val($('#prevObservTextAnterior').val());
			$('#prevObservacionesAnterior2').show();
		} else {
			$('#prevObservacionesAnterior2').hide();
		}

		$('#prevEmpresa').val($("#selectEmpresa option:selected").text());

		if ($("#selectSegmento option:selected").text() == "Seleccione un item...") {
			$('#prevSegmento').val("");
		} else {
			$('#prevSegmento').val($("#selectSegmento option:selected").text());
		}

		$('#prevMonedaOperacion').val($("#selectMonedaOperacion option:selected").text());

		if ($("#selectCopropietario option:selected").text() == "Seleccione un item...") {
			$('#prevCopropietario').val("");
		} else {
			$('#prevCopropietario').val($("#selectCopropietario option:selected").text());
		}

		if ($("#selectMotivo option:selected").text() == "Seleccione un item...") {
			$('#prevMotivo').val("");
		} else {
			$('#prevMotivo').val($("#selectMotivo option:selected").text());
		}

		if (datosTablas.clientesSeleccionados.length > 0) {
			tablas.clientesPrev.fnAddData(datosTablas.clientesSeleccionados, true);
		} else {
			tablas.clientesPrev.fnAdjustColumnSizing(false);
		}
		if (datosTablas.debitosSeleccionados.length > 0) {
			tablas.debitosPrev.fnAddData(datosTablas.debitosSeleccionados, true);
		} else {
			tablas.debitosPrev.fnAdjustColumnSizing(false);
		}
		if (datosTablas.creditosSeleccionados.length > 0) {
			tablas.creditosPrev.fnAddData(datosTablas.creditosSeleccionados, true);
		} else {
			tablas.creditosPrev.fnAdjustColumnSizing(false);
		}
		if (datosTablas.mediosPagos.length > 0) {
			tablas.mediosPagosPrev.fnAddData(datosTablas.mediosPagos, true);
			tablas.mediosPagosPrev.fnAdjustColumnSizing(false);
		} else {
			tablas.mediosPagosPrev.fnAdjustColumnSizing(false);
		}
		if (datosTablas.documentosCapsSeleccionados.length > 0) {
			tablas.documentosCapPrev.fnAddData(datosTablas.documentosCapsSeleccionados, true);
			tablas.documentosCapPrev.fnAdjustColumnSizing(false);
			actualizarInfoTablaSinPaginado(contadorRenglon(datosTablas.documentosCapsSeleccionados), "customPagCapPrev");
		} else {
			tablas.documentosCapPrev.fnAdjustColumnSizing(false);
		}

		if (datosTablas.otrosDebitosSeleccionados.length > 0) {
			tablas.otrosDebitosPrev.fnAddData(datosTablas.otrosDebitosSeleccionados);
			tablas.otrosDebitosPrev.fnAdjustColumnSizing(false);
		} else {
			tablas.otrosDebitosPrev.fnAdjustColumnSizing(true);
		}

		if (datosTablas.comprobantes.length > 0) {
			tablas.comprobantesPrev.fnAddData(datosTablas.comprobantes, true);
			tablas.comprobantesPrev.fnAdjustColumnSizing(false);
		} else {
			tablas.comprobantesPrev.fnAdjustColumnSizing(false);
		}

		if (datosTablas.comprobantesAplManual.length > 0) {
			tablas.comprobantesAplManual.fnAddData(datosTablas.comprobantesAplManual, true);
			tablas.comprobantesAplManual.fnAdjustColumnSizing(false);
		} else {
			tablas.comprobantesAplManual.fnAdjustColumnSizing(true);
		}

		if (datosTablas.codigoOperacionesExternas.length > 0) {
			tablas.codigoOperacionesExternas.fnAddData(datosTablas.codigoOperacionesExternas, true);
			tablas.codigoOperacionesExternas.fnAdjustColumnSizing(false);
		} else {
			tablas.codigoOperacionesExternas.fnAdjustColumnSizing(false);
		}

		// cargo combo tratamiento diferencia
		habilitarOpcionesTrataDif();
		// Habilito o deshabilitar el combo de tratamiento de la
		// diferencia
		validarGrisadoTrataDif();

		// muestro combo de debitos o creditos segun la
		// diferencia
		comboReintegro();

		// limpio los errores previsualizar
		limpiarErroresTabPrevisualizar();

		// cargo los datos del tratamiento de diferencia que
		// estan guardados en sesion
		var tratamientoDiferencia = datosTablas.tratamientoDiferencia;
		if (!$.isEmpty(datosTablas.mediosPagos[0])) {
			if (datosTablas.mediosPagos[0].idMpTipoCredito == 35) {
				tratamientoDiferencia.tipoTratamientoDiferencia = "";
			}
		}

		if (!$.isEmpty(tratamientoDiferencia) && tratamientoDiferencia.tipoTratamientoDiferencia != "" && tratamientoDiferencia.tipoTratamientoDiferencia != null) {

			var valorSeleccionadoGuardado = tratamientoDiferencia.tipoTratamientoDiferencia;
			var optionsCredito = $('#selectEnvio:visible option');
			var optionsDebito = $('#selectEnvio1:visible option');
			var opcionSeleccionada = '';

			// se carga la opcion seleccionada segun el combo
			// que se esta visualizando
			if (optionsCredito.length) {
				opcionSeleccionada = $.map(optionsCredito, function(option) {
					if (option.value === valorSeleccionadoGuardado) {
						return option.value;
					}
				});
				$('#selectEnvio option[value=\"' + opcionSeleccionada + '\"]').prop("selected", "selected");
			} else {
				opcionSeleccionada = $.map(optionsDebito, function(option) {
					if (option.value === valorSeleccionadoGuardado) {
						return option.value;
					}
				});
				$('#selectEnvio1 option[value="' + opcionSeleccionada + '"]').prop("selected", "selected");
			}

			$("#tramReintInput").val(tratamientoDiferencia.numeroTramiteReintegro);
			$("#fechaAltaTramReint").val(tratamientoDiferencia.fechaAltaTramiteReintegro);
			if (APLICACION_MANUAL === valorSeleccionadoGuardado) {
				$("#sistemaAplicacionManual").val(tratamientoDiferencia.sistemaDestino);
				if (tratamientoDiferencia.referencia !== "") {
					$("#referenciaInput").val(tratamientoDiferencia.referencia);
				}
			} else {
				$("#selectSistDestino").val(tratamientoDiferencia.sistemaDestino);
			}
			$("#lineaInput").val(tratamientoDiferencia.linea);
			$("#acuerdoFactInput").val(tratamientoDiferencia.acuerdoFacturacion);
			$("#estadoAcuerdoFacturacion").val(tratamientoDiferencia.estadoAcuerdoFacturacion);

			if (APLICACION_MANUAL === valorSeleccionadoGuardado) {

			}
		} else {
			$('#bloqueAgregarComprobanteAplManual').hide();
		}

		if (varEdicionSegunEstadoMarca == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
		} else if (varEdicionSegunEstadoMarca == "edicionParcial") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
		}
		$('.bloqueDescargar').attr('disabled', false);
		$('.busquedaResultado').attr('disabled', false);
		selectedRow.markRowSelected('transacciones', false, 'prevTransacciones', true);

		tablas.comprobantesAplManual.fnAdjustColumnSizing();
	};

	var clearTabClientes = function() {
		tablas.clientesSeleccionados.fnClearTable();
		tablas.clientes.fnDestroy();
		$("#clientes > tbody").html("");
		clearSearchCriteria('searchCriteraCliente');
	};

	var clearTabDebitos = function() {
		tablas.clientesDebitos.fnClearTable();
		tablas.debitosSeleccionados.fnClearTable();

		tablas.debitos.fnDestroy();
		$('#customPagDebitos_info span').text('Mostrando registros del 0 al 0 de 0 registros en total');
		$('#customPagDebitos_previous').removeClass('active').addClass('disabled');
		$('#customPagDebitos_next').removeClass('active').addClass('disabled');
		$("#debitos > tbody").html("");
		clearSearchCriteria('searchCriteraDebito');
	};

	var clearTabOtrosDebitos = function() {
		tablas.clientesOtrosDebitos.fnClearTable();
		tablas.otrosDebSeleccionados.fnClearTable();
		tablas.comprobantesOtrosDebito.fnClearTable();
		clearSearchCriteria('bloqueReferenciaPago');
		validadarFormOtrosDev = new Object();
		vaciarCombo('selectMonedaOtrosDeb');
		$('#selectMonedaOtrosDeb').prop('disabled', true);
		noVisualizarControlesForm();
		datosTablas.idsAdjuntosOtrosDeb = [];

	};

	var clearTabCreditos = function() {
		tablas.clientesCreditos.fnClearTable();
		tablas.creditosSeleccionados.fnClearTable();
		tablas.creditos.fnDestroy();
		$("#creditos > tbody").html("");
		clearSearchCriteria('searchCriteraCredito');
	};

	var clearTabMediosDePago = function() {
		tablas.clientesMedios.fnClearTable();
		tablas.mediosPagos.fnClearTable();
		tablas.documentosCaps.fnClearTable();
		tablas.documentosCapsSeleccionados.fnClearTable();
		clearSearchCriteria('inputMedioDePago');
	};

	var clearTabComprobantes = function() {
		tablas.comprobantes.fnClearTable();
	};

	var clearTabPrev = function() {
		tablas.clientesPrev.fnClearTable();
		tablas.debitosPrev.fnClearTable();
		tablas.otrosDebitosPrev.fnClearTable();
		tablas.creditosPrev.fnClearTable();
		tablas.mediosPagosPrev.fnClearTable();
		tablas.documentosCapPrev.fnClearTable();
		tablas.transaccionesPrev.fnClearTable();
		tablas.comprobantesPrev.fnClearTable();
		tablas.comprobantesAplManual.fnClearTable();
		tablas.codigoOperacionesExternas.fnClearTable();
	};

	var draw = [ drawTabClientes, drawTabDebitos, drawTabOtrosDebitos, drawTabCreditos, drawTabMediosDePago, drawTabComprobantes, drawTabPrev ];
	var clear = [ clearTabClientes, clearTabDebitos, clearTabOtrosDebitos, clearTabCreditos, clearTabMediosDePago, clearTabComprobantes, clearTabPrev ];

	var validarDebitosExportados = function() {
		// if (verfico el esatdo del cobrop)

		$(tablas.debitosSeleccionados.fnGetData()).each(function(index, debito) {
			if (debito.origen == DEBITO_ORIGEN_IMPORTACION_NAME && debito.resultadoValidacionEstado != DEBITO_PENDIENTE_NAME) {
				var trRow = tablas.debitosSeleccionados.fnGetNodes(index);
				if (DEBITO_VALIDACION_ERROR_NAME == debito.resultadoValidacionEstado && ERROR_SALDO_MAXIMO_DEBITO != debito.resultadoValidacionCodigoError && ERROR_VALIDACION_EDICION_GRILLAS != debito.resultadoValidacionCodigoError) {
					$(trRow).find('input').attr('disabled', 'true');
				} else {
					validarImporteIngresado($(trRow).find('input:text')[0], tablas.debitosSeleccionados, datosTablas.debitosSeleccionados, DEB_COL_POS_IMPORTE, 'impACobrar', 'moneda', DEB_COL_POS_MENSAJE, 'descripcionErrorValidacion', 'saldoMaxDefault', 'btnClonedDeb' + debito.idPantalla, true);
				}
			}
		});
	};

	var crearBuscadorClientes = function() {
		tablas.clientes = $("#clientes").dataTable(clientesSearchSettings);

		// fixedColumns(tablas.clientes, 1, 0);
		$("div.agregarTodosClientes").html('<button type="button" id="btnAgregarTodos"  class="customAll" disabled><font size=1,5>Agregar todos</font></button>');

		if (eventosTablas.isMomentoInicial('clientes')) {

			$('#clientes tbody').on('click', 'button', function() {
				var existe = false;
				var idClienteLegado = $(this).attr('idClienteLegado');
				$(tablas.clientesSeleccionados.fnGetData()).each(function(j, seleccionado) {
					if (seleccionado.idClienteLegado == idClienteLegado) {
						existe = true;
						return false;
					}
				});
				if (!existe) {
					$(tablas.clientes.fnGetData()).each(function(j, cliente) {
						if (cliente.idClienteLegado == idClienteLegado) {
							$('#btnEliminarTodos').attr('disabled', false);
							var aData = tablas.clientes.fnGetData()[j];

							tablas.clientesSeleccionados.fnAddData([ aData ], true);
							aparienciaButton.ocultarButtonPlus(tablas.clientes, idClienteLegado, j);
							datosTablas.clientesSeleccionados.push(aData);

							return false;
						}
					});
				}
				// Este metodo recorre las 2
				// tablas para verificar si
				// estan todos seleccionados
				if (verificaTodosSeleccionados(tablas.clientes, tablas.clientesSeleccionados, 'idClienteLegado')) {
					$('#btnAgregarTodos').attr('disabled', true);
				}
			});
		} else {
			agregarBotonExcelBasico(tablas.clientes.api(), "Cobro_Clientes_Sin_seleccionar", "div.agregarTodosClientes");
		}

		$('#btnAgregarTodos').click(function() {
			var mensaje = "Desea&nbsp;agregar&nbsp;todos&nbsp;los&nbsp;Clientes&nbsp;de&nbsp;la&nbsp;grilla?";
			bootbox.confirm(mensaje, function(result) {
				if (result) {
					var existe = false;
					var agregar = [];
					$(tablas.clientes.fnGetData()).each(function(i, cliente) {
						$(tablas.clientesSeleccionados.fnGetData()).each(function(j, seleccionado) {
							if (seleccionado.idClienteLegado == cliente.idClienteLegado) {
								existe = true;
								return false;
							}
						});
						if (!existe) {
							aparienciaButton.ocultarButtonPlus(tablas.clientes, cliente.idClienteLegado, i);
							agregar.push(cliente);
						}
						existe = false;
					});

					if (agregar.length > 0) {
						tablas.clientesSeleccionados.fnAddData(agregar, true);
						datosTablas.clientesSeleccionados = $.merge(datosTablas.clientesSeleccionados, agregar);
						$('#btnEliminarTodos').attr('disabled', false);
						$('#btnAgregarTodos').attr('disabled', true);
					}
				}
			});
		});

	};
	// TODO NO SE USA EN NINGUL LADO
	// var fixedColumns = function(tabla, cantColIzq,
	// cantColDer){
	// var fixedColumnsControl = new
	// $.fn.dataTable.FixedColumns( tabla, {
	// iLeftColumns: cantColIzq,
	// iRightColumns: cantColDer
	// });
	// };

	// En el tabs el bloque de espera se lanza cuando cambia de
	// solapa. El tamaï¿½o que toma
	// es de la solapa origen y no la de destino que seria la
	// que deberia tener

	$("#conf-cobro-tabs").steps({
		headerTag : "h3",
		bodyTag : "div",
		transitionEffect : "none",
		enableFinishButton : false,
		enablePagination : false,
		enableAllSteps : true,
		finish : true,
		titleTemplate : "#title#",
		cssClass : "tabcontrol",
		onStepChanging : function(event, currentIndex, newIndex) {
			// Inicio lógica bootbox otros débitos. F.N.Q U591368
			/*
			 * Flag para que en la tabla de otros debitos se pueda manejar o no
			 * el cambio de solapa
			 */
			var cobroValido = true;

			if (newIndex != INDICE_TAB_OTROS_DEBITOS && currentIndex == INDICE_TAB_OTROS_DEBITOS) {
				/*
				 * Seteo el flag en false para que no pueda hacerse el cambio de
				 * solapa
				 */
				cobroValido = false;
				/* Lógica si el cobro es diferente de compensacion */
				if (hayCamposSinAgregarOtrosDeb()) {
					var mensaje = 'Existe un débito en proceso de configuración. Si presiona "Aceptar" se perderá la información que aún no fue agregada a la grilla.';
					bootbox.confirm(mensaje, function(result) {
						if (result) {
							/*
							 * Si aceptan el bootbox vuelvo a setear true al
							 * flag para que se pueda hacer el cambio de solapa
							 */
							limpiarInputsYSelectsOtrosDeb();
							cobroValido = true;
							$(CONF_COBRO_SIGUIENTE_TAB + newIndex).click();
						} else {
							/*
							 * Escondo el bootbox y retorno false porque sino se
							 * genera un loop.
							 */
							bootbox.hideAll();
							$(CONF_COBRO_SIGUIENTE_TAB + currentIndex).click();
							return false;
						}

					});
					ocultarBloqueEspera();
					return false;
				} else {
					cobroValido = true;

				}
				// else {
				// mostrarBloqueEspera();
				// setTimeout(function() {
				// draw[newIndex]();
				// clear[currentIndex]();
				// ocultarBloqueEspera();
				// }, 500);
				// cobroValido = true;
				// return true;
				// }
			}
			// Fin lógica bootbox otros débitos. F.N.Q U591368

			if (newIndex == INDICE_TAB_CREDITOS && $('#selectMotivo').val() == COMPENSACION) {
				return false;
			}

			cargarGrillaDocumentosCapDinamica(newIndex);

			flagBloqueDeEspera.inicializar([ 'fGuardarConfCobro', 'fHabilitarBtnSimular', 'modificarTransaccionesConIntereses', 'habilitarBtnImputarYCargarGrillaTransacciones' ]);

			if (cobroValido && validarDatosCobro()) {
				setTimeout(function() {
					if (validacionOkFecha) {
						// seteo el campo hidden en true si estoy en la
						// solapa de previsualizacion, para guardar las
						// transacciones
						// cuando hago clic en el boton guardar o cuando me voy
						// de solapa
						// o al hacer clic en el boton imputar
						if (currentIndex == INDICE_TAB_PREV && newIndex != INDICE_TAB_PREV) {
							$('#modificarTransacciones').val("true");
						} else {
							$('#modificarTransacciones').val("false");
						}
						if (
							!vengoReedicion() &&
							COB_ERROR_COBRO_NAME != $('#estado').val() && COB_ERROR_APROPIACION_NAME != $('#estado').val()
						) {
							esCambioDeSolapa = true;
							guardarCobro((newIndex == INDICE_TAB_PREV) ? true : undefined);

						} else {
							if (
								currentIndex != INDICE_TAB_PREV &&
								newIndex == INDICE_TAB_PREV &&
								!vengoReedicion()
							) {
								habilitarBtnImputarYCargarGrillaTransacciones();
							} else {
								flagBloqueDeEspera.habilitarBtnImputarYCargarGrillaTransacciones = true;
							}
							flagBloqueDeEspera.fGuardarConfCobro = true;
							flagBloqueDeEspera.fHabilitarBtnSimular = true;
							flagBloqueDeEspera.modificarTransaccionesConIntereses = true;
						}

					} else {
						flagBloqueDeEspera.fGuardarConfCobro = true;
						flagBloqueDeEspera.fHabilitarBtnSimular = true;
						flagBloqueDeEspera.modificarTransaccionesConIntereses = true;
						flagBloqueDeEspera.habilitarBtnImputarYCargarGrillaTransacciones = true;
					}
					// cargo los datos en la tab siguiente y borro
					// los de la anterior
					draw[newIndex]();
					clear[currentIndex]();
				}, 1300);
				return true;
			} else {
				setTimeout(function() {
					flagBloqueDeEspera.clear();
					ocultarBloqueEspera();
				}, 500);
			}

			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);
		},
		onStepChanged : function(event, currentIndex, priorIndex) {
			$("#divLoader").css({
				height : ($.getDocHeight()) + 'px'
			});

			if (currentIndex == INDICE_TAB_PREV) {
				// Cuando termina la
				flagBloqueDeEspera.comando = tarea;
				flagBloqueDeEspera.comandoName = 'ejecutar';
				flagBloqueDeEspera.comandoParam = datosTablas.tratamientoDiferencia;
				finBloqueDeEspera();
			} else {
				finBloqueDeEspera();
			}
		}
	});

	$(CONF_COBRO_TAB_CRED).mouseover(function() {
		if ($('#selectMotivo').val() == COMPENSACION) {
			$(CONF_COBRO_TAB_CRED).addClass("step-disabled-li-a");
			$(CONF_COBRO_TAB_CRED).parent().css({
				"border-color" : "#fff #fff #bbb"
			});
		} else {
			$(CONF_COBRO_TAB_CRED).removeClass("step-disabled-li-a");
			$(CONF_COBRO_TAB_CRED).parent().removeAttr('style');
		}
	});

	var habilitarOpcionesTrataDif = function() {

		if ($('#selectMotivo').val() == COMPENSACION) {
			$('#selectEnvio option[value=SALDO_A_PAGAR]').show();
			$('#selectEnvio1 option[value=SALDO_A_COBRAR]').show();

			$("#tramReint").hide();
			$("#fechaAltaTramReintOpc").hide();

			$('#selectEnvio1 option[value=DEBITO_PROX_FAC]').hide();
			$('#selectEnvio option[value=ENVIO_A_GANANCIAS]').hide();
			$('#selectEnvio option[value=REINTEGRO_CRED_PROX_FAC]').hide();
			$('#selectEnvio option[value=REINTEGRO_POR_CHEQUE]').hide();
			$('#selectEnvio option[value=REINTEGRO_PAGO_CUENTA_TERCEROS]').hide();
			$('#selectEnvio option[value=REINTEGRO_CREDITO_RED_INTEL]').hide();
			$('#selectEnvio option[value=REINTEGRO_TRANSFERENCIA_BAN]').hide();
			$('#selectEnvio option[value=APLICACION_MANUAL]').hide();
		} else {

			if (_monedaDelCobro === MONEDA_PESOS_SIGNO_2) {
				$('#selectEnvio option[value=REINTEGRO_POR_CHEQUE]').show();
				$('#selectEnvio option[value=REINTEGRO_PAGO_CUENTA_TERCEROS]').show();
				$('#selectEnvio option[value=REINTEGRO_CREDITO_RED_INTEL]').show();
				$('#selectEnvio option[value=REINTEGRO_TRANSFERENCIA_BAN]').show();
				$('#selectEnvio option[value=APLICACION_MANUAL]').show();
			} else {
				$('#selectEnvio option[value=REINTEGRO_POR_CHEQUE]').hide();
				$('#selectEnvio option[value=REINTEGRO_PAGO_CUENTA_TERCEROS]').hide();
				$('#selectEnvio option[value=REINTEGRO_CREDITO_RED_INTEL]').hide();
				$('#selectEnvio option[value=REINTEGRO_TRANSFERENCIA_BAN]').hide();
				// $('#selectEnvio option[value=APLICACION_MANUAL]').hide();
			}

			$('#selectEnvio option[value=SALDO_A_PAGAR]').hide();
			$('#selectEnvio1 option[value=SALDO_A_COBRAR]').hide();

			$("#tramReint").show();
			$("#fechaAltaTramReintOpc").show();
			$('#selectEnvio1 option[value=DEBITO_PROX_FAC]').show();
			$('#selectEnvio option[value=ENVIO_A_GANANCIAS]').show();
			$('#selectEnvio option[value=REINTEGRO_CRED_PROX_FAC]').show();
		}

	};

	// U572497 Guido.Settecerze
	var comboReintegro = function() {

		if (diferencia > "0,00") {
			$('.comboDebito').show();
			$('.comboCredito').hide();
		} else if (diferencia < "0,00") {
			$('.comboCredito').show();
			$('.comboDebito').hide();
		} else if (diferencia == "0,00") {
			$('.comboDebito').hide();
			$('.comboCredito').show();
			$('#selectEnvio').val(' ');
		}
	};

	// U572497 Guido.Settecerze
	validarGrisadoTrataDif();

	var limpiarErrores = function() {
		/* ésta funcion se encuentra repetida mas arriba */
		$(CONF_COBRO_TAB_CLIENTE).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});

		$(CONF_COBRO_TAB_OTROS_DEB).css({
			"color" : "5f5f5f",
			"font-weight" : "normal"
		});

		if (!contadorMensajesErrorGuardableDebitos.isError()) {
			$(CONF_COBRO_TAB_DEB).css({
				"color" : "#5f5f5f",
				"font-weight" : "normal"
			});
		}
		if (!contadorMensajesErrorGuardableCreditos.isError()) {
			$(CONF_COBRO_TAB_CRED).css({
				"color" : "#5f5f5f",
				"font-weight" : "normal"
			});
		}
		$(CONF_COBRO_TAB_MED_PAG).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
		$(CONF_COBRO_TAB_COMPROBANTE).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
		$(CONF_COBRO_TAB_PREV).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
		$("span.error").text("");
	};

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// DATE PICKER
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#vencimientoDesde").datepicker({
		firstDay : 1
	});
	$("#vencimientoHasta").datepicker({
		firstDay : 1
	});
	$("#credDesde").datepicker({
		firstDay : 1
	});
	$("#credHasta").datepicker({
		firstDay : 1
	});
	$("#fechaAltaTramReint").datepicker({
		firstDay : 1
	});
	$("#fechaTipoCambio").datepicker({
		firstDay : 1,
		onClose : function() {
			this.focus();
		}
	});
	$("#emisionDesdeCap").datepicker({
		firstDay : 1
	});
	$("#emisionHastaCap").datepicker({
		firstDay : 1
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB CLIENTES
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	(function() {
		var empresaPrev = null;
		var segPrev = null;
		$("#selectEmpresa").focus(function() {
			empresaPrev = this.value;
		}).change(function() {
			if ($.isEmpty(this.value)) {
				var options = [ {
					text : 'Seleccione un item...',
					value : ''
				} ];
				$("#selectSegmento").replaceOptions(options);
				$("#selectSegmento").val(options[0].value);
				$("#selectCopropietario").replaceOptions(options);
				$("#selectCopropietario").val(options[0].value);
			} else if (this.value != empresaPrev) {
				$('#bloqueEspera').trigger('click');
				empresaPrev = this.value;
				$.ajax({
					"dataType" : 'json',
					"type" : "GET",
					"url" : 'configuracion-cobro/buscarSegmentos',
					"data" : {
						idEmpresa : this.value
					},
					"success" : function(result) {
						if (result.length > 0) {
							if (result.length == 1) {
								$("#selectSegmento").replaceOptions(result);
								$("#selectSegmento").val(result[0].value).trigger('change');
							} else {
								var options = [ {
									text : 'Seleccione un item...',
									value : ''
								} ];
								$("#selectCopropietario").replaceOptions(options);
								$("#selectSegmento").val(options[0].value);
								$.each(result, function(index, option) {
									options.push(option);
								});
								$("#selectSegmento").replaceOptions(options);
								$("#selectSegmento").val(options[0].value);
							}
						}
						ocultarBloqueEspera();
					}
				});
			}
			;
		});

		$("#selectSegmento").focus(function() {
			segPrev = this.value;
		}).change(function() {
			if ($.isEmpty(this.value)) {
				var options = [ {
					text : 'Seleccione un item...',
					value : ''
				} ];
				$("#selectCopropietario").replaceOptions(options);
				$("#selectCopropietario").val(options[0].value);
			} else if (this.value != segPrev) {
				$('#bloqueEspera').trigger('click');
				segPrev = this.value;
				$.ajax({
					"dataType" : 'json',
					"type" : "GET",
					"url" : 'configuracion-cobro/buscarCopropietarios',
					"data" : {
						idEmpresa : $("#selectEmpresa").val(),
						idSegmento : this.value
					},
					"success" : function(result) {
						var options = [ {
							text : 'Seleccione un item...',
							value : ''
						} ];
						$.each(result, function(index, option) {
							options.push(option);
						});
						$("#selectCopropietario").replaceOptions(options);
						$("#selectCopropietario").val(options[0].value);
						ocultarBloqueEspera();
					}
				});
			}
			;
			ocultarBloqueEspera();
		});
	})();

	crearBuscadorClientes();

	tablas.clientesSeleccionados = $("#clientesSeleccionados").dataTable(selClientesSettings);
	// fixedColumns(tablas.clientesSeleccionados, 1, 0);

	$("div.eliminarTodosClientes").html('<button type="button" id="btnEliminarTodos" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');

	$('#clientesSeleccionados tbody').on('click', 'button', function() {
		var idClienteLegado = $(this).attr('idClienteLegado');
		var encontrado = detalleDocumentos.verificarClienteDeseleccionar(idClienteLegado);
		if (encontrado) {
			// Si el cliente tiene debitos y/o
			// creditos seleccionados y/o medios
			// de pago y/o cargos a próxima
			var detalleClienteADesasociar = detalleDocumentos.detalleClienteADesasociar(idClienteLegado);
			if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos" && detalleClienteADesasociar.cantDebitos > 0) {
				$('#errorClienteConDebitos').text("El cliente no puede ser eliminado por tener débito/s asociado/s.");
				$('#errorClienteConDebitos').show();
			} else {
				$("#errorClienteConDebitos").hide();
				bootbox.confirm(detalleClienteADesasociar.msg, function(result) {
					if (result) {
						EliminarEnCascada.documentosByClienteId(idClienteLegado);
						// Limpio
						// el
						// campo
						// acuerdoFactInput
						// de la
						// solapa
						$('#acuerdoFactInput').val('');
						$("#estadoAcuerdoFacturacion").val('');
						aparienciaButton.descubrirButtonPlusCliente(tablas.clientes, idClienteLegado);

						$(tablas.clientesSeleccionados.fnGetData()).each(function(j, cliente) {
							if (cliente.idClienteLegado == idClienteLegado) {
								tablas.clientesSeleccionados.fnDeleteRow(j);
								return false;
							}
						});
						tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
						$('#btnAgregarTodos').attr('disabled', false);
					}
				});
			}
		} else {
			aparienciaButton.descubrirButtonPlusCliente(tablas.clientes, idClienteLegado);
			$(tablas.clientesSeleccionados.fnGetData()).each(function(j, cliente) {
				if (cliente.idClienteLegado == idClienteLegado) {
					tablas.clientesSeleccionados.fnDeleteRow(j);
					return false;
				}
			});

			// Modificacion M.A.Uehara:
			// Puse esta linea por sino no se
			// borra de las tablas de
			// clientesSeleccionados
			EliminarEnCascada.clienteById(idClienteLegado);
			$('#btnAgregarTodos').attr('disabled', false);
			tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
		}
	});

	$('#btnEliminarTodos').click(function() {
		var detalleAEliminar = detalleDocumentos.detalleClientesADesasociar();
		if (varEdicionSegunEstadoMarca == "edicionTotalMenosDebitos" && detalleAEliminar.cantDebitos > 0) {
			$('#errorClienteConDebitos').text("Uno de los clientes no puede ser eliminado por tener débito/s asociado/s.");
			$('#errorClienteConDebitos').show();
		} else {
			$("#errorClienteConDebitos").hide();
			var mensaje = "Desea&nbsp;eliminar&nbsp;todos&nbsp;los&nbsp;Clientes&nbsp;de&nbsp;la&nbsp;grilla?";
			bootbox.confirm(mensaje, function(result) {
				if (result) {
					if (detalleDocumentos.verificarClientesDeseleccionar()) {
						var detalleDeseleccionar = detalleDocumentos.detalleClientesADesasociar();
						bootbox.confirm(detalleDeseleccionar.msg, function(result) {
							if (result) {
								aparienciaButton.descubrirButtonPlusClienteTodos(tablas.clientes, tablas.clientesSeleccionados);
								tablas.clientesSeleccionados.fnClearTable();
								tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
								datosTablas.clientesSeleccionados.length = 0;
								datosTablas.debitosSeleccionados.length = 0;
								datosTablas.otrosDebitosSeleccionados.length = 0;
								datosTablas.creditosSeleccionados.length = 0;
								datosTablas.documentosCapsSeleccionados = 0;
								datosTablas.mediosPagos.length = 0;
								totalDeDocumentos.actualizar();
								$('#acuerdoFactInput').val('');
								$("#estadoAcuerdoFacturacion").val('');
							}
							;
						});
					} else {
						aparienciaButton.descubrirButtonPlusClienteTodos(tablas.clientes, tablas.clientesSeleccionados);
						tablas.clientesSeleccionados.fnClearTable();
						tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
						datosTablas.clientesSeleccionados.length = 0;
					}
					$('#btnAgregarTodos').attr('disabled', false);
				}
			});
		}
	});

	$('#selectMonedaOperacion').change(function() {
		if (this.value !== _monedaDelCobro) {
			var moneda = this.value;

			if (detalleDocumentos.verificarClientesDeseleccionar()) {
				// var moneda = this.value;
				var detalleDeseleccionar = detalleDocumentos.detalleClientesADesasociar('moneda');
				bootbox.confirm(detalleDeseleccionar.msg, function(result) {
					if (result) {
						datosTablas.debitosSeleccionados.length = 0;
						datosTablas.creditosSeleccionados.length = 0;
						datosTablas.otrosDebitosSeleccionados.length = 0;
						datosTablas.mediosPagos.length = 0;
						_monedaDelCobro = moneda;
						totalDeDocumentos.actualizar();
						$('#acuerdoFactInput').val('');
						$("#estadoAcuerdoFacturacion").val('');

						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
					} else {
						$('#selectMonedaOperacion').val(_monedaDelCobro);
					}
				});
			} else {
				_monedaDelCobro = moneda;
				totalDeDocumentos.actualizar();
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
			}
		}
	});

	// Filtro los medios de pago que difieren de compensaciones,
	// cuando el motivo es Compensacion

	$('#selectMotivo').focus(function() {
		// Se valida que no sea vacio, para cuando se elija
		// "Seleccione un item.." , no afecte el funcionamiento

		$('#selectMotivo option[value=1]').hide();
		$('#selectMotivo option[value=2]').hide();
		$('#selectMotivo option[value=3]').hide();
		$('#selectMotivo option[value=10]').hide();

		if ($('#selectMotivo').val() != '') {
			motivoPrevio = $('#selectMotivo').val();
		}
	}).change(function() {

		$('#selectMotivo option[value=1]').hide();
		$('#selectMotivo option[value=2]').hide();
		$('#selectMotivo option[value=3]').hide();
		$('#selectMotivo option[value=10]').hide();

		motivoNuevo = $('#selectMotivo').val();

		if (motivoNuevo === COMPENSACION) {
			$('#tipoDeCambio').css('height', '20px');
		}

		// en este metodo, si se cancela el pop-up,
		// vuelve a asignarle el motivo viejo al id
		// #selectMotivo
		borrarDocumentosSegunMotivoCobro(motivoPrevio, motivoNuevo, this);
	});

	// -----------------------------
	// BUSQUEDA CLIENTES
	// -----------------------------
	$('#selectCriterio').change(function() {
		clearInputText('searchCriteraCliente');

		if (this.value == "BUSQUEDA_POR_CUIT") {
			$("#textCriterio").attr("maxlength", 13);
		} else {
			$("#textCriterio").attr("maxlength", 11);
		}
	});

	$('#btnBuscarClientes').click(function() {
		$('#bloqueEspera').trigger('click');

		$("#errorBusqueda").text("");
		var criterio = $('#selectCriterio').val();
		var busqueda = $('#textCriterio').val();
		criterio = $.trim(criterio);
		busqueda = $.trim(busqueda);

		$("span.error").text("");
		var confCobroClienteFiltro = {
			criterio : criterio,
			busqueda : busqueda
		};

		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/busquedaClientes",
			"dataType" : "json",
			"data" : JSON.stringify(eval(confCobroClienteFiltro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					tablas.clientes.fnClearTable();
					tablas.clientes.fnAdjustColumnSizing(false);
					$('#btnAgregarTodos').attr('disabled', true);

					if (result.clientes.length > 0) {
						tablas.clientes.fnAddData(result.clientes, true);

						aparienciaButton.ocultarButtonClientesSeleccionados(tablas.clientes, tablas.clientesSeleccionados);
						// Este metodo
						// recorre las 2
						// tablas para
						// verificar si
						// estan todos
						// seleccionados
						$('#btnAgregarTodos').attr('disabled', false);
						if (verificaTodosSeleccionados(tablas.clientes, tablas.clientesSeleccionados, 'idClienteLegado')) {
							$('#btnAgregarTodos').attr('disabled', true);
						}
					}
				} else {
					$(result.campoError).text(result.descripcionError);
				}
				ocultarBloqueEspera();
			}
		});
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB CLIENTES
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB DEBITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	reIniciarTablas.debitos(false);

	$('#debitosSeleccionados tbody').on('click', 'button', function() {
		$('#bloqueEspera').trigger('click');
		if (tablas.debitosSeleccionados.fnGetData() != null) {
			$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
		}
		var idDebitos = $(this).attr('idDebitoVar');
		var aData = null;

		// Desgriso el registro de la grilla de
		// arriba
		aparienciaButton.descubrirButtonPlusDocumento(tablas.debitos, idDebitos);

		$(tablas.debitosSeleccionados.fnGetData()).each(function(j, debito) {
			if (debito.idDebitoPantalla == idDebitos) {
				aData = tablas.debitosSeleccionados.fnGetData()[j];
				tablas.debitosSeleccionados.fnDeleteRow(j);
				detalleDebitosDelCobro.subDocumento(aData.tipoComprobanteEnum);
				return false;
			}
		});

		// Resto el importe a cobrar del debito
		// deseleccionado
		if (!$.isEmpty(aData.descripcionErrorValidacion) && aData.descripcionErrorValidacion != '-') {
			contadorMensajesError.restarError(aData.descripcionErrorValidacion);
			contadorMensajesErrorGuardableDebitos.restarErrorGuardable(aData.descripcionErrorValidacion);
		}
		$('#btnAgregarTodosDebitos').attr('disabled', false);
		// tablas.debitosSeleccionados.fnDeleteRow($(this).parents('tr')[0]);
		datosTablas.debitosSeleccionados = $.grep(datosTablas.debitosSeleccionados, function(seleccionado) {
			return seleccionado.idDebitoPantalla != idDebitos;
		});
		totalDeDocumentos.actualizar();
		tablas.debitosSeleccionados.fnAdjustColumnSizing(true);

		setTimeout(function() {
			ocultarBloqueEspera();
		}, 500);
	});

	$('#debitosSeleccionados tbody').on('click', 'td', function() {
		selectedRow.withFixedColumn(this, 'debitos');
	});
	$('#debitosSeleccionados').on('page.dt', function() {
		selectedRow.debitosCambioPag = true;
	});

	$('#btnBuscarDebitos').click(function() {
		$('#bloqueEspera').trigger('click');
		$("span.error").text("");

		// TODO Voy validar por jabaScript luego
		// vere por java

		var clientes = $(".debClienteSel:checked", tablas.clientesDebitos.fnGetNodes());

		var idsClientes = '';
		if (clientes.length > 0) {
			$(clientes).each(function(i, elem) {
				aData = tablas.clientesDebitos.fnGetData($(this).closest('tr')[0]);
				// Si el cliente tiene mas de diez digitos no se busca en Mic y
				// en Calipso
				if (aData.idClienteLegado.length <= 10) {
					idsClientes += aData.idClienteLegado + ',';
				}
			});
			idsClientes = idsClientes.substring(0, idsClientes.length - 1);
		}

		var idsDebs = '';
		if (tablas.debitosSeleccionados.fnGetData().length > 0) {
			$(tablas.debitosSeleccionados.fnGetData()).each(function(j, seleccionado) {
				idsDebs += seleccionado.idDebitoPantalla + ',';
			});
			idsDebs = idsDebs.substring(0, idsDebs.length - 1);
		}
		var confCobroDebitoFiltro = {
			idsClientes : idsClientes,
			idsDoc : idsDebs,
			tipoDoc : $('#selectTipoDoc').val(),
			nroDoc : $('#nroDoc').val(),
			sistema : $('#selectSistema').val(),
			moneda : $('#selectMoneda').val(),
			refMIC : $('#refMIC').val(),
			acuerdoFac : $('#acuerdoFac').val(),
			vencDesde : $('#vencimientoDesde').val(),
			vencHasta : $('#vencimientoHasta').val(),
			monedaImporteACobrar : _monedaDelCobro,
			idCobro : $('#idCobro').val(),
			fechaTipoCambio : $('#fechaTipoCambio').val(),
			motivo : $('#selectMotivo').val()
		};
		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/buscarDebitos",
			"dataType" : "json",
			"data" : JSON.stringify(eval(confCobroDebitoFiltro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				$("span.error").text("");
				tablas.debitos.fnClearTable();
				if (result.success) {
					actualizarInfoTabla(tablas.debitos, result, 'customPagDebitos', 'btnAgregarTodosDebitos', 'errorRespuestaDebitos', CANTIDAD_POR_PAGINA_DEB, aparienciaButton.ocultarButtonDocumentosSeleccionados);
				} else {
					if (result.campoError.indexOf('-') > 0) {
						var campos = result.campoError.split('-');
						for (var i = 0, size = campos.length; i < size; i++) {
							$(campos[i]).text(result.descripcionError);
						}
					} else {
						$(result.campoError).text(result.descripcionError);
					}
				}
				ocultarBloqueEspera();
			}
		});
	});

	// $("#customPagDebitos_next").click(
	// function() {
	// if ($(this).hasClass('active')
	// && !($(this).hasClass('disabled'))) {
	// paginarTabla(tablas.debitos,
	// 'paginarDebitos', 'stge',
	// 'customPagDebitos',
	// 'btnAgregarTodosDebitos',
	// 'errorRespuestaDebitos',
	// CANTIDAD_POR_PAGINA_DEB);
	// }
	// });
	registrarEventoPaginacion('Debitos', 'debitos', CANTIDAD_POR_PAGINA_DEB, 'next', aparienciaButton.ocultarButtonDocumentosSeleccionados);
	// $("#customPagDebitos_previous").click(
	// function() {
	// if ($(this).hasClass('active')
	// && !($(this).hasClass('disabled'))) {
	// paginarTabla(tablas.debitos,
	// 'paginarDebitos', 'ant',
	// 'customPagDebitos',
	// 'btnAgregarTodosDebitos',
	// 'errorRespuestaDebitos',
	// CANTIDAD_POR_PAGINA_DEB);
	// }
	// });
	registrarEventoPaginacion('Debitos', 'debitos', CANTIDAD_POR_PAGINA_DEB, 'previous', aparienciaButton.ocultarButtonDocumentosSeleccionados);

	$("#debTodosClientes").click(function() {
		checkUncheckAll('debClienteSel', tablas.clientesDebitos, $(this).prop('checked'));

		// $('#btnBuscarDebitos').prop('disabled',!($(this).prop('checked')));
	});

	$("#trasladarTodosIntereses").click(function() {
		checkUncheckAllTrasladarIntereses('checkTrasladar', tablas.transaccionesPrev, $(this).prop('checked'));
	});

	// Seleccionar todos o deseleccionar en trasladar intereses
	function checkUncheckAllTrasladarIntereses(classNameChecks, table, state) {
		$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
			$(this).prop('checked', table.fnGetData($(elem)[0].parentElement.parentElement).esTrasladarInteresesObligatorio || state);
			if (!$(this).prop('disabled')) {
				checkTrasladarIntereses(this, 'trasladarIntereses');
			}
		});
	}
	;

	//
	// IMPORTAR DEBITOS
	//
	$('#impDebitosArch').change(function() {
		$('#btnImportarDebitos').attr('disabled', !$(this).val());
	});

	$('#resultadoImportacion').hide();

	$('#btnImportarDebitos').click(function() {
		flagBloqueDeEspera.inicializar([ 'fPersistirDebitos', 'fGuardarConfCobro' ]);
		$("#errorArchDebitos").hide();
		// Primero debo guardar los debitos
		// Seleccionados...
		// No encontre la manera de hacer un
		// submit con archivos y con dto
		// en el mismo
		
		var error = false;
		if (!$('#impDebitosArch').val()) {
			$("#errorArchDebitos").text("Debe seleccionar un archivo.");
			$("#errorArchDebitos").show();
			error = true;
		}
		
		if(!error){
			if (tablas.debitosSeleccionados.fnGetData() != null) {
				$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
			}
			var cobro = {
					idCobro : $('#idCobro').val(),
					debitos : datosTablas.debitosSeleccionados
			};
			$.ajax({
				"type" : "POST",
				"url" : "configuracion-cobro/persistirDebitos",
				"dataType" : "json",
				"data" : JSON.stringify(eval(cobro)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						// Importo los
						// debitos
						importarCSV();
					}
					flagBloqueDeEspera.fPersistirDebitos = true;
					
				}
			});			
		}else{
			ocultarBloqueEspera();
		}
		
		finBloqueDeEspera();
	});
	var importarCSV = function() {
		$("#impDebitos").ajaxForm({
			data : {
				idCobro : $('#idCobro').val(),
			},
			dataType : 'json',
			success : function(data) {
				$('.fileupload').fileupload('clear');
				if (data.success) {
					if (data.clientes && data.clientes.length > 0) {
						var existe = false;
						var agregar = [];
						$(data.clientes).each(function(i, nuevoCliente) {
							$(datosTablas.clientesSeleccionados).each(function(i, cliente) {
								if (nuevoCliente.idClienteLegado == cliente.idClienteLegado) {
									existe = true;
									return false;
								}
							});
							if (!existe) {
								agregar.push(nuevoCliente);
							}
							existe = false;
						});
						if (agregar.length > 0) {
							tablas.clientesDebitos.fnAddData(agregar, true);
							datosTablas.clientesSeleccionados = $.merge(datosTablas.clientesSeleccionados, agregar);
						}
					}
					if (data.debitos && data.debitos.length > 0) {
						var size = data.debitos.length;
						var monedaImporteCobrar = _monedaDelCobro;
						// seteo la moneda
						// Operacion en los
						// debitos importados
						for (var indice = 0; indice < size; indice++) {
							data.debitos[indice].monedaImporteCobrar = monedaImporteCobrar;
						}
						$.merge(datosTablas.debitosSeleccionados, data.debitos);
						tablas.debitosSeleccionados.fnAddData(data.debitos, true);
						aparienciaButton.ocultarButtonCreditosDebitosSeleccionados(datosTablas.debitosSeleccionados);
						seAgregaronRegistros = true;
						totalDeDocumentos.actualizar();
					}
					$('#resultadoImpText').val("Resultado de la importación OK.");
					detalleDebitosDelCobro.addLista(datosTablas.debitosSeleccionados);

					var comp = {
						idComprobante : data.comprobanteJsonResponse.id,
						nombreArchivo : data.comprobanteJsonResponse.fileName,
						descripcionArchivo : data.comprobanteJsonResponse.descripcion
					};
					datosTablas.comprobantes.push(comp);
					// Modifico el tamaño del
					// bloque de espara
					$("#divLoader").css({
						height : ($.getDocHeight()) + 'px'
					});

					guardarCobro();
				} else {
					$('#btnImportarDebitos').attr('disabled', true);
					$('#resultadoImpText').val(data.errors);
					flagBloqueDeEspera.terminar();
				}
				$('#resultadoImportacion').show();
				$('#btnImportarDebitos').attr('disabled', true);
				if (tablas.debitosSeleccionados.fnGetData() != null) {
					$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
				}
			}
		}).submit();
	};
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB DEBITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB OTROS DEBITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// Cargar los onClinck de los combos
	cargarTrigger();
	cargarOtrosDebito();
	cargarComboSinDiferenciaCambio();

	$('#impOtrosDebitosArch').change(function() {
		$('#btnImportarOtrosDebitos').attr('disabled', !$(this).val());
	});

	$('#resultadoImportacionOtrosDebitos').hide();

	$('#btnImportarOtrosDebitos').click(function() {
		flagBloqueDeEspera.inicializar([ 'fPersistirDebitos', 'fGuardarConfCobro' ]);
		$("#errorArchOtrosDebitos").hide();
		
		var error = false;
		
		if (!$('#impOtrosDebitosArch').val()) {
			$("#errorArchOtrosDebitos").text("Debe seleccionar un archivo.");
			$("#errorArchOtrosDebitos").show();
			error = true;
		}
		
		if(!error){
			if (tablas.otrosDebSeleccionados.fnGetData() != null) {
				$('#cantidadOtrosDebitosEnGrilla').val(tablas.otrosDebSeleccionados.fnGetData().length);
			}
			var cobro = {
				idCobro : $('#idCobro').val(),
				otrosDebitos : datosTablas.otrosDebitosSeleccionados
			};
			$.ajax({
				"type" : "POST",
				"url" : "configuracion-cobro/persistirOtrosDebitos",
				"dataType" : "json",
				"data" : JSON.stringify(eval(cobro)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						// Importo los otros debitos
						$('#resultadoImpOtrosDebText').val('');
						importarCSVOtrosDebitos();
					}
					flagBloqueDeEspera.fPersistirDebitos = true;
	
				}
			});
		}else{			
			ocultarBloqueEspera();
		}
		finBloqueDeEspera();
	});

	reIniciarTablas.otrosDebitos(false);
	$('#btnAgregarOtroDeb').click(function() {
		var cobro = {
			"idCobro" : 1213213
		};
		$.ajax({
			"type" : "POST",
			"url" : "isAlive",
			"dataType" : "json",
			"data" : JSON.stringify(eval(cobro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {

			},
			"error" : function() {
				beforeUnload.off();
				// Para que fallse
				$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobros-configuracion-edicion";
				$('#formCobro')[0].submit();
			}
		});
		agregarOtrosDeb();
		totalDeDocumentos.actualizar();

	});
	$("#textFechaVencimientoOtrosDeb").datepicker({
		firstDay : 1
	});
	$('#grillaOtrosDeb tbody').on('click', 'button', function() {
		var idOtroDebito = $(this).attr('idOtroDebVar');
		var idBoton = this.id;

		$('#bloqueEspera').trigger('click');

		editarOEliminarDeGrillaOtrosDeb(idOtroDebito, idBoton);
		descargarComprobanteEnGrillaOtrosDeb(idOtroDebito, idBoton);
		totalDeDocumentos.actualizar();

		ocultarBloqueEspera();
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB OTROS DEBITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB CREDITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	reIniciarTablas.creditos(false);

	$('#creditosSeleccionados tbody').on('click', 'button', function() {
		$('#bloqueEspera').trigger('click');
		var idCreditoSel = $(this).attr('idCreditoSel');
		var aData = null;

		// Desgriso el registro de la grilla de
		// arriba
		aparienciaButton.descubrirButtonPlusDocumento(tablas.creditos, idCreditoSel);

		$(tablas.creditosSeleccionados.fnGetData()).each(function(j, credito) {
			if (credito.idCreditoPantalla == idCreditoSel) {
				aData = tablas.creditosSeleccionados.fnGetData()[j];
				tablas.creditosSeleccionados.fnDeleteRow(j);
				return false;
			}
		});

		// Elimino el credito seleccionado
		if (!$.isEmpty(aData.descripcionErrorValidacion) && aData.descripcionErrorValidacion != '-') {
			contadorMensajesError.restarError(aData.descripcionErrorValidacion);
			contadorMensajesErrorGuardableCreditos.restarErrorGuardable(aData.descripcionErrorValidacion);
		}

		datosTablas.creditosSeleccionados = $.grep(datosTablas.creditosSeleccionados, function(seleccionado) {
			return seleccionado.idCreditoPantalla != aData.idCreditoPantalla;
		});
		totalDeDocumentos.actualizar();
		tablas.creditosSeleccionados.fnAdjustColumnSizing(true);

		setTimeout(function() {
			ocultarBloqueEspera();
		}, 500);
	});
	$('#creditosSeleccionados tbody').on('click', 'td', function() {
		selectedRow.withFixedColumn(this, 'creditos');
	});
	$('#creditosSeleccionados').on('.dt', function() {
		selectedRow.creditosCambioPag = true;
	});

	$('#btnBuscarCreditos').click(function() {
		$('#bloqueEspera').trigger('click');
		$("span.error").text("");
		var tipoMP = $('#credSelectTipoMP').val();
		var sistema = $('#credSelectSistema').val();
		var moneda = $('#credSelectMoneda').val();
		var credDesde = $('#credDesde').val();
		var credHasta = $('#credHasta').val();
		var acuerdoFac = $('#credAcuerdo').val();
		var clientes = $(".credClienteSel:checked", tablas.clientesCreditos.fnGetNodes());
		var idCobro = $('#idCobro').val();

		var idsClientes = '';
		if (clientes.length > 0) {
			$(clientes).each(function(i, elem) {
				aData = tablas.clientesCreditos.fnGetData($(this).closest('tr')[0]);
				// Si el cliente tiene mas de diez digitos no se busca en Mic y
				// en Calipso
				if (aData.idClienteLegado.length <= 10 || sistema !== '' || sistema !== 'SHV') {
					idsClientes += aData.idClienteLegado + ',';
				}
			});
			idsClientes = idsClientes.substring(0, idsClientes.length - 1);
		}

		var idsCreds = '';
		if (tablas.creditosSeleccionados.fnGetData().length > 0) {
			$(tablas.creditosSeleccionados.fnGetData()).each(function(j, seleccionado) {
				idsCreds += seleccionado.idCreditoPantalla + ',';
			});
			idsCreds = idsCreds.substring(0, idsCreds.length - 1);
		}

		var confCobroCreditoFiltro = {
			idsClientes : idsClientes,
			idsDoc : idsCreds,
			tipoMedioPago : tipoMP,
			sistema : sistema,
			moneda : moneda,
			credDesde : credDesde,
			credHasta : credHasta,
			acuerdoFac : acuerdoFac,
			monedaImporteACobrar : _monedaDelCobro,
			idCobro : idCobro
		};
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : "configuracion-cobro/buscarCreditos",
			"data" : JSON.stringify(eval(confCobroCreditoFiltro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				$("span.error").text("");
				tablas.creditos.fnClearTable();
				if (result.success) {
					actualizarInfoTabla(tablas.creditos, result, 'customPagCreditos', 'btnAgregarTodosCreditos', 'errorRespuestaCreditos', CANTIDAD_POR_PAGINA_CRED, aparienciaButton.ocultarButtonDocumentosSeleccionados);
				} else {
					$(result.campoError).text(result.descripcionError);
				}
				ocultarBloqueEspera();
			}
		});
	});

	$("#customPagCreditos_next").click(function() {
		if ($(this).hasClass('active'))
			paginarTabla(tablas.creditos, 'paginarCreditos', 'stge', 'customPagCreditos', 'btnAgregarTodosCreditos', 'errorRespuestaCreditos', CANTIDAD_POR_PAGINA_CRED, aparienciaButton.ocultarButtonDocumentosSeleccionados);
	});

	$("#customPagCreditos_previous").click(function() {
		if ($(this).hasClass('active'))
			paginarTabla(tablas.creditos, 'paginarCreditos', 'ant', 'customPagCreditos', 'btnAgregarTodosCreditos', 'errorRespuestaCreditos', CANTIDAD_POR_PAGINA_CRED, aparienciaButton.ocultarButtonDocumentosSeleccionados);
	});

	$("#credTodosClientes").click(function() {
		checkUncheckAll('credClienteSel', tablas.clientesCreditos, $(this).prop('checked'));
		$('#btnBuscarCreditos').prop('disabled', !($(this).prop('checked')));
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB CREDITOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB MEDIOS DE PAGO
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	//
	reIniciarTablas.mediosPagos(false);
	//
	$("#medioPagoFecha").datepicker({
		firstDay : 1
	});
	$("#seleccionarTodos").click(function() {
		$('.opcionCliente').prop('checked', $(this).prop('checked'));
	});

	$('#mediosPagos tbody').on('click', 'button', function() {
		// Si el id de Btn es vacio es el boton
		// de eleimanr
		if ($.isEmpty(this.id)) {
			tablas.mediosPagos.fnDeleteRow($(this).parents('tr')[0]);
			datosTablas.mediosPagos = tablas.mediosPagos.fnGetData();
			totalDeDocumentos.actualizar();
			datosTablas.documentosCapsSeleccionados.length = 0;
			actualizarControles('medio');
			importe = totalDeDocumentos.actualizarCap();
		} else if ('btnEditarOtroMedioPago' === this.id) {
			$('#bloqueEspera').trigger('click');
			btnEditarOtroMedioPagoOnClick(this);
			ocultarBloqueEspera();
		}

	});

	// Generales

	$('#impMPagoDiv').hide();
	$('#errorImpMPagoDiv').hide();

	$('#fechaMedioPagoDiv').hide();
	$('#fechaMedioPagoErrorDiv').hide();

	$('#nroActaDiv').hide();
	$('#nroActaErrorDiv').hide();

	// Particulares
	$('#bloqueBuscarCap').hide();

	$('#provinciaDiv').hide();
	$('#errorProvinciaDiv').hide();

	$('#tipoComprobanteSapDiv').hide();
	$('#errorTipoComprobanteSapDiv').hide();

	(function() {
		$("#selectMedioPago").change(function() {
			$("span.error").text("");

			clearInputText('inputMedioDePago');
			clearSearchCriteria('searchCriteraCap');

			$('#selectProvincia')[0].selectedIndex = 0;
			$('#selecSubTipo')[0].selectedIndex = 0;

			$('#errorImpMPagoDiv').hide();
			$('#fechaMedioPagoErrorDiv').hide();
			$('#nroActaErrorDiv').hide();
			$('#errorProvinciaDiv').hide();
			$('#errorSelecSubTipoDiv').hide();

			$('#impMPagoDiv').hide();
			$('#fechaMedioPagoDiv').hide();
			$('#nroActaDiv').hide();
			// Particulares
			$('#bloqueBuscarCap').hide();
			$('#provinciaDiv').hide();
			$('#selecSubTipoDiv').hide();

			$("#nroActaDiv label").html('<span class="rojo">* </span>Nro. Compensación');

			$('#btnAgregarMedioCompensacion').hide();
			$('#btnAgregarMedio').show();
			if ($.isEmpty(datosTablas.mediosPagos)) {
				$('button[id^="btnAgregarMedio"]').attr('disabled', false);
			}

			tablesClear('documentosCaps', 'caps', tablas);

			if ($.isEmpty(datosTablas.mediosPagos)) {
				tablesClear('documentosCapsSeleccionados', 'capSeleccionados', tablas, datosTablas);
			} else {
				tablesClear('documentosCapsSeleccionados', 'capSeleccionados', tablas);
			}
			totalDeDocumentos.actualizarCap();
			if (!$.isEmpty(this.value)) {
				$('#impMPagoDiv').show();
				$('#errorImpMPagoDiv').show();
				$('#fechaMedioPagoDiv').show();
				$('#fechaMedioPagoErrorDiv').show();

				// this.value == NOTACREDPENDEMI
				if (this.value === PLANPAGO || this.value === DESISTIMIENTO) {
					$('#nroActaDiv').show();
					$('#nroActaErrorDiv').show();
					if (this.value === DESISTIMIENTO) {
						$("#nroActaDiv label").html('<span class="rojo">* </span>Nro. de Acta');
					} else {
						$('#nroActaDiv').hide();
						$('#nroActaErrorDiv').hide();
					}
				} else {
					$('#medioPagoFecha').val($('#fechaTipoCambio').val());
					if (this.value === LIQUIDOPRODUCTO || this.value === PROVEEDORES) {
						$('#bloqueBuscarCap').show();
						// tablas.documentosCaps
						// =
						// $("#caps").dataTable(capsSearchSettings);
						// tablas.documentosCapsSeleccionados
						// =
						// $("#capSeleccionados").dataTable(capsSelSettings);

						crearTablaFixedColumn(datosTablas, tablas, "documentosCaps");
						crearTablaFixedColumn(datosTablas, tablas, "documentosCapsSeleccionados");
						tablas.documentosCaps.fnAdjustColumnSizing();
						tablas.documentosCapsSeleccionados.fnAdjustColumnSizing();
						if (this.value === LIQUIDOPRODUCTO) {
							$('#selecSubTipoDiv').show();
						}
						;
						$('#errorSelecSubTipoDiv').show();
						$('#impMPagoDiv').hide();
						$('#errorImpMPagoDiv').hide();

						// TODO IM 1469
						// $('#nroActaErrorDiv').hide();
						// $('#nroActaDiv').hide();
						$('#nroActaDiv').show();
						$('#nroActaErrorDiv').show();

						$('#btnAgregarMedioCompensacion').show();
						$('#btnAgregarMedio').hide();
						if ($.isEmpty(datosTablas.documentosCapsSeleccionados)) {
							$('button[id^="btnAgregarMedio"]').attr('disabled', true);
						}
					} else if (this.value === IIBB) {
						$('#provinciaDiv').show();
						$('#errorProvinciaDiv').show();

						$('#nroActaDiv').show();
						$('#nroActaErrorDiv').show();
					} else {
						$('#nroActaDiv').show();
						$('#nroActaErrorDiv').show();
					}
				}
			}
		});
	})();

	$('button[id^="btnAgregarMedio"]').click(function() {
		// ------------------------------
		// VALIDACION AGREGAR MEDIO PAGO
		// ------------------------------
		$("span.error").text("");
		var medioPago = $('#selectMedioPago').val();
		var descripcionMedioPago = $("#selectMedioPago option:selected").text();
		var importe = $('#textImpMPago').val();
		var nroActa = $('#textNroActa').val();
		// var nroCompensacion = $('#textNroActa').val();
		var fecha = $('#medioPagoFecha').val();
		var subTipoLiquido = null;
		var clientes = [];

		// Si es un medio de pago
		// liquidoProducto o proveedores
		// Los clientes los toma de los
		// documentos caps
		if (medioPago === LIQUIDOPRODUCTO || medioPago === PROVEEDORES) {
			for (var i = 0, size = datosTablas.documentosCapsSeleccionados.length; i < size; i++) {
				if (clientes.indexOf(datosTablas.documentosCapsSeleccionados[i].idClienteLegado) < 0) {
					clientes.push(datosTablas.documentosCapsSeleccionados[i].idClienteLegado);
				}
			}
		} else if (medioPago === OTRAS_COMPENSACIONES || medioPago === INTERCOMPANY || medioPago === IIBB || medioPago === CESION_CREDITOS) {
			if (!$.isEmpty($('.mpClienteSel:checked').attr('idCliente'))) {
				clientes.push($('.mpClienteSel:checked').attr('idCliente'));
			}
		} else {
			clientes = $(".opcionCliente:checked", tablas.clientesMedios.fnGetNodes());
		}
		var validacionOk = true;

		// Validacion medioPago
		if ($.isEmpty(medioPago)) {
			$("#errorMedioPago").text("Este campo es requerido.");
			validacionOk = false;
		} else {
			// Validacion clientes
			if (medioPago == NOTACREDPENDEMI && clientes.length == 0) {
				clientes = [];
			} else if (clientes.length == 0) {
				$("#errorClientesMP").text("Debe seleccionar al menos un cliente.");
				validacionOk = false;
			}
			// Validacion Nro. Acta
			if (medioPago !== NOTACREDPENDEMI && medioPago !== PLANPAGO) {
				if ($.isEmpty(nroActa)) {
					$("#errorNroActa").text("Este campo es requerido.");
					validacionOk = false;
				} else {
					nroActa = $.trim(nroActa);
					if (!$.validacionCantCaracteres(nroActa, 26)) {
						$("#errorNroActa").text("Este campo debe contener menos de 26 caracteres.");
						validacionOk = false;
					}
				}
			} else {
				nroActa = '-';
			}

			// Validacion Fecha
			if (medioPago != NOTACREDPENDEMI) {
				if ($.isEmpty(fecha)) {
					$("#errorFecha").text("Este campo es requerido.");
					validacionOk = false;
				} else if (!$.formatoFecha(fecha)) {
					$("#errorFecha").text("Este campo debe respetar el formato DD/MM/AAAA.");
					validacionOk = false;
				} else if (!$.validacionFecha(fecha)) {
					$("#errorFecha").text("Debe ingresar una fecha valida.");
					validacionOk = false;
				}
			} else {
				var hoy = new Date();
				var dia = (hoy.getDate() < 10 ? '0' + hoy.getDate() : hoy.getDate());
				var mes = ((hoy.getMonth() + 1) < 10 ? '0' + (hoy.getMonth() + 1) : (hoy.getMonth() + 1));
				fecha = dia + "/" + mes + "/" + hoy.getFullYear();
			}
		}
		//
		if (medioPago === LIQUIDOPRODUCTO) {
			subTipoLiquido = $('#selecSubTipo').val();
			if ($.isEmpty(subTipoLiquido)) {
				$("#errorSelecSubTipo").text("Este campo es requerido.");
				validacionOk = false;
			}
		}
		// Validacion Importe
		if (medioPago === LIQUIDOPRODUCTO || medioPago === PROVEEDORES) {
			var importeNumerico = totalDeDocumentos.actualizarCap();
			if (importeNumerico < 0) {
				// Invierto el signo. Dado que
				// sap utiliza signo negativos
				importeNumerico = importeNumerico * -1;
				importe = formatear(importeNumerico);
			} else {
				$("#errorRespuestaCapSeleccionado").text('El importe total debe ser menor a 0');
				validacionOk = false;
			}
		}
		if ($.isEmpty(importe)) {
			$("#errorImporte").text("Este campo es requerido.");
			validacionOk = false;
		} else {
			importe = $.trim(importe);
			if (!validarImporteValido(importe)) {
				$("#errorImporte").text(msgErrorInput);
				validacionOk = false;
			} else {
				var valorSinPuntoDesimales = importe.split('.').join('');
				var valorDescompuesto = valorSinPuntoDesimales.split(',');

				if (valorDescompuesto[0].length > 9) {
					validacionOk = false;
					$("#errorImporte").text(msgErrorInputLength);
				} else if (!$.isEmpty(valorDescompuesto[1]) && valorDescompuesto[1].length > 2) {
					validacionOk = false;
					$("#errorImporte").text(msgErrorInputLength);
				} else {
					var importeNumerico = importeToFloat(importe);
					if (importeNumerico <= 0) {
						$("#errorImporte").text(msgErrorInputNoValido);
						validacionOk = false;
					}
				}
			}
			importe = truncoADosDecimalesImporte(importe);
		}

		if (IIBB === medioPago) {
			if ($.isEmpty($('#selectProvincia').val())) {
				$("#errorProvincia").text("Este campo es requerido.");
				validacionOk = false;
			}
		}

		// ----------------------------------
		// FIN VALIDACION AGREGAR MEDIO PAGO
		// ----------------------------------

		if (validacionOk) {
			if (clientes.length != 0) {
				var medio;
				var idsClientes = '';
				var aData;
				var idProvincia = $('#selectProvincia option:selected').val();
				var provincia = '-';
				if (!$.isEmpty(idProvincia)) {
					provincia = $('#selectProvincia option:selected').text();
				}
				if (medioPago === LIQUIDOPRODUCTO || medioPago === PROVEEDORES) {
					idsClientes = clientes.join(' - ');
					$('.mpClienteSel').attr('checked', false);
				} else if (medioPago === OTRAS_COMPENSACIONES || medioPago === INTERCOMPANY || medioPago === IIBB || medioPago === CESION_CREDITOS) {
					idsClientes = clientes.join(' - ');
					$('.mpClienteSel').attr('checked', false);
				} else {
					$(clientes).each(function(i, elem) {
						aData = tablas.clientesMedios.fnGetData($(this).closest('tr')[0]);
						$(this).prop('checked', false);
						idsClientes = idsClientes + ' - ' + eliminaCerosADerecha(aData.idClienteLegado);
					});
					idsClientes = idsClientes.substring(3);
				}

				medio = {
					clientesLegados : idsClientes,
					idMpTipoCredito : medioPago,
					descMpTipoCredito : descripcionMedioPago,
					importe : importe,
					nroActa : (medioPago == DESISTIMIENTO) ? nroActa.replace(/["']/g, "") : '-',
					nroCompensacion : (medioPago != DESISTIMIENTO) ? nroActa.replace(/["']/g, "") : '-',
					fecha : fecha,
					clientesLegados : idsClientes,
					monedaImporteUtilizar : _monedaDelCobro,
					provincia : provincia,
					idProvincia : idProvincia,
					subTipo : $.isEmpty(subTipoLiquido) ? '-' : subTipoLiquido,
					nroDeDocumentoInterno : '-',
					subTipoDescripcion : $.isEmpty(subTipoLiquido) ? '-' : $('#selecSubTipo option:selected').text(),
				};

				datosTablas.mediosPagos.push(medio);
				tablas.mediosPagos.fnAddData([ medio ], true);
				totalDeDocumentos.actualizar();
				$('#btnEliminarTodosMedios').attr('disabled', false);
				$('#seleccionarTodos').prop('checked', false);
			} else {
				medio = {
					clientesLegados : '',
					idMpTipoCredito : medioPago,
					descMpTipoCredito : descripcionMedioPago,
					importe : importe,
					nroActa : '',
					nroCompensacion : '',
					fecha : fecha,
					clientesLegados : '',
					monedaImporteUtilizar : ''
				};
				tablas.mediosPagos.fnAddData([ medio ], true);
				datosTablas.mediosPagos.push(medio);
				totalDeDocumentos.actualizar();
			}

			$('#selectMedioPago').val("");
			$('#textImpMPago').val("");
			$('#textNroActa').val("");
			$('#medioPagoFecha').val("");
			$("span.error").text("");
			$('#impMPagoDiv').hide();
			$('#errorImpMPagoDiv').hide();
			$('#fechaMedioPagoDiv').hide();
			$('#fechaMedioPagoErrorDiv').hide();
			$('#nroActaDiv').hide();
			$('#nroActaErrorDiv').hide();

			/** ****************************** */
			$('#selectProvincia')[0].selectedIndex = 0;
			$('#selecSubTipo')[0].selectedIndex = 0;
			$('#errorSelecSubTipoDiv').hide();
			// Particulares
			$('#bloqueBuscarCap').hide();
			$('#provinciaDiv').hide();
			$('#selecSubTipoDiv').hide();

			$('#btnAgregarMedioCompensacion').hide();
			$('#btnAgregarMedio').show();

			actualizarControles('medio');
		}
	});
	// -----------------------------------------------------------------------------
	// BUSCAR CAP
	// -----------------------------------------------------------------------------

	registrarEventosCaps();
	// -----------------------------------------------------------------------------
	// BUSCAR CAP
	// -----------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB MEDIOS DE PAGO
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB COMPROBANTES
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	$('#btnAdjComprobante').click(function() {
		$('#bloqueEspera').trigger('click');
		$("#errorArchComprobante").hide();
		$("#errorDescripcionComp").hide();

		var error = false;
		if (!$('#comprobanteArch').val()) {
			$("#errorArchComprobante").text("Debe seleccionar un archivo.");
			$("#errorArchComprobante").show();
			error = true;
		}
		if (!$('#descripcionComprobante').val() || $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, '').length == 0) {
			$("#errorDescripcionComp").text("Debe ingresar una descripción.");
			$("#errorDescripcionComp").show();
			error = true;
		}

		if (!error) {
			$("#adjComprobanteForm").ajaxForm({
				dataType : 'json',
				data : {
					idCobro : $('#idCobro').val(),
					descripcion : $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, ''),
					motivoAdjunto : MOTIVO_ADJUNTO_COMPROBANTE_COBRO
				},
				success : function(data) {
					$('.fileupload').fileupload('clear');
					if (data.success) {
						var comp = {
							idComprobante : data.id,
							nombreArchivo : data.fileName,
							descripcionArchivo : data.descripcion
						};
						tablas.comprobantes.fnAddData([ comp ], true);
						datosTablas.comprobantes.push(comp);
						$('#descripcionComprobante').val("");
					} else {
						$(data.campoError).text(data.descripcionError);
						$(data.campoError).show();
					}
					ocultarBloqueEspera();
				}
			}).submit();
		} else {
			ocultarBloqueEspera();
		}
	});

	tablas.comprobantes = $("#comprobantes").dataTable({
		dom : 'rt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		// "iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		"bPaginate" : false,
		"aoColumns" : [ {
			"mData" : "idComprobante",
			"visible" : false,
			"searchable" : false
		}, {
			"mData" : "nombreArchivo"
		}, {
			"mData" : "descripcionArchivo"
		}, {
			"targets" : -1,
			"data" : null,
			"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button action="eliminar" type="button" class="btn btn-xs btn-link" title="Eliminar comprobante"><i class="icon-trash bigger-120"></i></button></div>',
			"searchable" : false,
			"bSortable" : false
		} ]
	});

	$('#comprobantes tbody').on('click', 'button', function() {
		var node = $(this).parents('tr')[0];
		var aData = tablas.comprobantes.fnGetData(node);

		if ($(this).attr('action') == 'eliminar') {
			$('#bloqueEspera').trigger('click');
			$.ajax({
				"type" : "POST",
				"url" : "configuracion-cobro/eliminarComprobante",
				"dataType" : "json",
				"data" : "{ \"idComprobante\": " + aData.idComprobante + "}",
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						tablas.comprobantes.fnDeleteRow(node);
						datosTablas.comprobantes = $.grep(datosTablas.comprobantes, function(seleccionado) {
							return seleccionado.idComprobante != aData.idComprobante;
						});
					}
					ocultarBloqueEspera();
				}
			});
		} else {
			var iframe = document.createElement("iframe");
			iframe.src = "configuracion-cobro/descargarComprobante?id=" + aData.idComprobante;
			iframe.style.display = "none";
			document.body.appendChild(iframe);
		}
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB COMPROBANTES
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB PREVISUALIZACION
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	(function() {
		if ($('#selectSistDestino').val() == "") {
			$(".reintegroProxFactOpcLinea").hide();
			$(".reintegroProxFactOpcAcuerdo").hide();
			$(".referenciaAplicacionManual").hide();
			$("#wsConsultaJms").css('display', 'none');
			$("#wsConsultaJms2").css('display', 'none');
		}
	})();
	$('#selectSistDestino').change(function() {
		$('#errorSistemaDestino').val("");
		$('#errorSistemaDestino').css('display', 'none');
		$('#lineaInput').val("");
		$('#acuerdoFactInput').val("");
		$("#estadoAcuerdoFacturacion").val("");
		estadoButtons.habilitarSimularBySelect();
		if ($('#selectSistDestino').val() == "") {
			$(".reintegroProxFactOpcLinea").hide();
			$(".reintegroProxFactOpcAcuerdo").hide();
			$(".referenciaAplicacionManual").hide();
			verificarCambiInputTraDifBqEspera();
		}
		if (CALIPSO_DESC_CORTA === this.value) {
			$(".reintegroProxFactOpcLinea").hide();
			$(".reintegroProxFactOpcAcuerdo").show();
			verificarCambiInputTraDifBqEspera();
		}
		if (MIC_DESC_CORTA === this.value) {
			$(".reintegroProxFactOpcLinea").show();
			$(".reintegroProxFactOpcAcuerdo").show();
			if ($('#acuerdoFactInput').val() == "") {
				if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
					flagBloqueDeEspera.inicializar([ 'fAutoCompletarCampoAcuerdo', 'fValidarSimulacion' ]);
					autoCompletarCampoAcuerdo();
					finBloqueDeEspera();
				}
			}
		}
		limpiarErroresTabPrevisualizar();
		$("#wsConsultaJms").css('display', 'none');
		$("#wsConsultaJms2").css('display', 'none');
	});
	$("#referenciaInput").change(function(event) {
		verificarCambiInputTraDifBqEspera();
	});
	// Guido.Settecerze
	$('#sistemaAplicacionManual').change(function() {
		$('#errorSistemaDestino').val("");
		$('#errorSistemaDestino').css('display', 'none');
		$('#referenciaInput').val("");
		estadoButtons.habilitarSimularBySelect(); // ver si corresponde esta
		// linea
		limpiarErroresTabPrevisualizar();
		$("#wsConsultaJms").css('display', 'none');
		$("#wsConsultaJms2").css('display', 'none');

		if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
			verificarCambiInputTraDifBqEspera();
			if ("SAP" == $("#sistemaAplicacionManual").val() && $("#referenciaInput").val() == "") {
				buscarCuentaContable();
			}
		}
	});
	// Rehabilito las tablas para export a excel
	reIniciarTablas.previsualizacion(false);
	//

	$('#prevEmpresa').val($("#selectEmpresa option:selected").text());

	if ($("#selectSegmento option:selected").text() == "Seleccione un item...") {
		$('#prevSegmento').val("");
	} else {
		$('#prevSegmento').val($("#selectSegmento option:selected").text());
	}

	$('#prevMonedaOperacion').val($("#selectMonedaOperacion option:selected").text());

	if ($("#selectCopropietario option:selected").text() == "Seleccione un item...") {
		$('#prevCopropietario').val("");
	} else {
		$('#prevCopropietario').val($("#selectCopropietario option:selected").text());
	}

	if ($("#selectMotivo option:selected").text() == "Seleccione un item...") {
		$('#prevMotivo').val("");
	} else {
		$('#prevMotivo').val($("#selectMotivo option:selected").text());
	}

	$('#prevComprobantes tbody').on('click', 'button', function() {
		var node = $(this).parents('tr')[0];
		var aData = tablas.comprobantesPrev.fnGetData(node);
		if ($(this).attr('action') == 'descargar') {
			var iframe = document.createElement("iframe");
			iframe.src = "configuracion-cobro/descargarComprobante?id=" + aData.idComprobante;
			iframe.style.display = "none";
			document.body.appendChild(iframe);
			
//			$('#formAdjunto').val(aData.id);
//			$('#formCobro')[0].action = urlAsynchronousHTTP + "/descargarComprobanteCobro1";
//			$('#formCobro')[0].submit();
			
		}
	});
	$('#prevOtrosDebitos tbody').on('click', 'button', function() {
		var idOtroDebito = $(this).attr('idOtroDebVar');
		var idBoton = this.id;

		$('#bloqueEspera').trigger('click');

		if (idBoton == 'btnDescargar') {
			$(tablas.otrosDebitosPrev.fnGetData()).each(function(j, debito) {
				if (debito.idOtrosDebitoPantalla == idOtroDebito) {
					$('#bloqueEspera').trigger('click');
					var iframe = document.createElement("iframe");
					iframe.src = "configuracion-cobro/descargarComprobante?id=" + debito.idAdjunto;
					iframe.style.display = "none";
					document.body.appendChild(iframe);
					ocultarBloqueEspera();
				}
			});
		}

		ocultarBloqueEspera();
	});

	$(".reintegroOpc").hide();
	$("#tramReint").show();
	$("#fechaAltaTramReintOpc").show();

	$(".reintegroProxFactOpc").hide();

	// U572497 Guido.Settecerze
	(function() {
		var envioPrev;
		$("#selectEnvio").focus(function() {
			envioPrev = this.value;
		}).change(function() {
			if (this.value != envioPrev) {
				limpiarErroresTabPrevisualizar();
				limpiarErrores();
				envioPrev = this.value;
				$('#envioOpc').hide();
				trataDifInputClear();
				if ($.isEmpty(this.value)) {
					$(".reintegroProxFactOpcLinea").hide();
					$(".reintegroProxFactOpcAcuerdo").hide();
					$(".reintegroOpc").hide();
					$(".reintegroProxFactOpc").hide();
					$(".referenciaAplicacionManual").hide();
					$("#bloqueAgregarComprobanteAplManual").hide();
					$('#btnSimular').prop('disabled', true);
					envioPrev = this.value;
					if (!anularGuardar) {
						verificarCambiInputTraDifBqEspera();
					}
				} else {
					var error = detalleDebitosDelCobro.verificar('TRATAMIENTO');
					estadoButtons.habilitarSimularBySelect();
					$('#selectSistDestino').val('');
					$('#selectSistDestino').prop('disabled', false);
					// $('#btnSimular').prop('disabled',false);
					if (!$.isEmpty(error)) {
						// ACA TENGO QUE MOSTAR
						// EL ERROR
						$('#btnSimular').prop('disabled', true);
						$('#errorEnvio').text(error);
						// $('#selectEnvio').val('');
						tabPrevErrorInput.selectEnvio = true;
						$('#errorEnvio').css('display', 'inline-block');
						$(CONF_COBRO_TAB_PREV).css({
							"color" : "red",
							"font-weight" : "bold"
						});
					} else {
						if (!anularGuardar) {
							verificarCambiInputTraDifBqEspera();
						}
						trataDifInputClear();
						$(".sistemaApliManual").hide();
						$(".referenciaAplicacionManual").hide();
						$("#spanSistemaDestino").text("");
						if (REINTEGRO_CRED_PROX_FAC === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show();
							// traReintegro
							// y
							// Fecha
							$(".reintegroProxFactOpc").show(); // selectSistema
							$(".sistemaDestino").show();
							$("#bloqueAgregarComprobanteAplManual").hide();
							if (_monedaDelCobro !== MONEDA_PESOS_SIGNO_2) {
								$('#selectSistDestino option[value=CLP]').prop("selected", "selected").change();
								$('#selectSistDestino').prop('disabled', true);
							}
							// $("#spanSistemaDestino").text("");
						} else if (ENVIO_A_GANANCIAS === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").hide(); // traReintegro
							// y
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").text("");
						} else if (APLICACION_MANUAL === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").hide();
							// traReintegro
							// y
							// Fecha
							$(".reintegroProxFactOpc").show();

							$(".sistemaDestino").hide(); // selectSistema

							$(".sistemaApliManual").show(); // selectSistemaAplicacionManual
							$(".referenciaAplicacionManual").show(); // referenciaSistemaAplicacionManual

							$("#bloqueAgregarComprobanteAplManual").show(); // bloque
							// comprobante
							// aplicación
							// manual
							tablas.comprobantesAplManual.fnAdjustColumnSizing();
							$("#spanSistemaDestino").text("*");
						} else if (REINTEGRO_POR_CHEQUE === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show();
							// traReintegro
							// y
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").val("");
						} else if (REINTEGRO_PAGO_CUENTA_TERCEROS === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show(); // traReintegro // y //
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").val("");
						} else if (REINTEGRO_CREDITO_RED_INTEL === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show(); // traReintegro // y //
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").val("");
						} else if (REINTEGRO_TRANSFERENCIA_BAN === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show(); // traReintegro // y //
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").val("");
						} else if (SALDO_A_PAGAR === this.value) {
							$('.reintegroProxFactOpcLinea').hide();
							$(".reintegroProxFactOpcAcuerdo").hide();
							$(".reintegroOpc").show(); // traReintegro // y //
							// Fecha
							$(".reintegroProxFactOpc").hide(); // selectSistema
							$("#bloqueAgregarComprobanteAplManual").hide();
							// $("#spanSistemaDestino").val("");
						}
					}
				}
				estadoButtons.btnSimular.disabled = $('#btnSimular').prop('disabled');
			}// ;
		});
	})();

	(function() {
		var envioPrev;
		$("#selectEnvio1").focus(function() {
			envioPrev = this.value;
		}).change(function() {
			if (this.value != envioPrev) {
				limpiarErroresTabPrevisualizar();
				limpiarErrores();
				if ($.isEmpty(this.value)) {
					$('#btnSimular').prop('disabled', true);
					envioPrev = this.value;
					if (!anularGuardar) {
						verificarCambiInputTraDifBqEspera();
					}
				} else {
					var error = detalleDebitosDelCobro.verificar('TRATAMIENTO');
					envioPrev = this.value;
					if ($.isEmpty(error)) {
						estadoButtons.habilitarSimularBySelect();
						if (!anularGuardar) {
							verificarCambiInputTraDifBqEspera();
						}
					} else {
						// ACA TENGO QUE MOSTAR
						// EL ERROR
						$('#errorEnvio').text(error);
						tabPrevErrorInput.selectEnvio = true;
						$('#errorEnvio').css('display', 'inline-block');
						$(CONF_COBRO_TAB_PREV).css({
							"color" : "red",
							"font-weight" : "bold"
						});
					}
				}
			}
			estadoButtons.btnSimular.disabled = $('#btnSimular').prop('disabled');
		});
	})();

	function validarFechaAltaTramReint() {
		tabPrevErrorInput.fechaAltaTramReint = false;
		tabPrevErrorInput.fechaAltaTramReintRequerido = false;

		$("#errorFechaAltaTramReint").text("");
		$('#errorFechaAltaTramReint').css('display', 'none');
		var fecha = $('#fechaAltaTramReint').val();
		limpiarMarcaErrorTabPrevisualizar();

		if ($.isEmpty(fecha)) {
			$("#errorFechaAltaTramReint").text("Este campo es requerido.");
			tabPrevErrorInput.fechaAltaTramReintRequerido = true;
		} else if (!$.formatoFecha(fecha)) {
			$("#errorFechaAltaTramReint").text("Este campo debe respetar el formato DD/MM/AAAA.");
			tabPrevErrorInput.fechaAltaTramReint = true;
		} else if (!$.validacionFecha(fecha)) {
			$("#errorFechaAltaTramReint").text("Debe ingresar una fecha valida.");
			tabPrevErrorInput.fechaAltaTramReint = true;
		}
		if (tabPrevErrorInput.fechaAltaTramReint) {
			$('#errorFechaAltaTramReint').css('display', 'inline-block');
			$(CONF_COBRO_TAB_PREV).css({
				"color" : "red",
				"font-weight" : "bold"
			});
		}
	}
	$('#fechaAltaTramReint').focusout(function() {
		$('#inputTratamientoDeDiferencia').find('input').prop('disabled', true);
		validarFechaAltaTramReint();
		// verificarCambiInputTraDif();
		setTimeout(function() {
			$('#inputTratamientoDeDiferencia').find('input').prop('disabled', false);
		}, 500);
	});
	$('#fechaAltaTramReint').change(function() {
		validarFechaAltaTramReint();
		verificarCambiInputTraDifBqEspera();
	});
	$('#tramReintInput').focusout(function() {
		$('#inputTratamientoDeDiferencia').find('input').prop('disabled', true);
		tabPrevErrorInput.tramReintInputRequerido = false;
		limpiarMarcaErrorTabPrevisualizar();
		$('#errorTramReintInput').text('');
		$('#errorTramReintInput').css('display', 'none');

		if ($.isEmpty($("#tramReintInput").val())) {
			tabPrevErrorInput.tramReintInputRequerido = true;
			$('#errorTramReintInput').text('Este campo es requerido.');
			$('#errorTramReintInput').css("display", "inline-block");
			$(CONF_COBRO_TAB_PREV).css({
				"color" : "red",
				"font-weight" : "bold"
			});
		}
		verificarCambiInputTraDifBqEspera();
		setTimeout(function() {
			$('#inputTratamientoDeDiferencia').find('input').prop('disabled', false);
		}, 500);
	});

	$("#tramReintInput").keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		return validarInputNumerico(event) || keycode == KEY_BS_RETROCESO;
	});

	// $("#fechaTipoCambio").focus(function() {
	// fechaCotizPrev = $("#fechaTipoCambio").val();
	//		
	// });

	$("#fechaTipoCambio").change(function() {

		if (fechaCotizPrev != $("#fechaTipoCambio").val()) {
			if (detalleDocumentos.verificarClientesDeseleccionar()) {
				// var moneda = this.value;
				var detalleDeseleccionar = detalleDocumentos.detalleClientesADesasociar('fechaCotizacion');
				bootbox.confirm(detalleDeseleccionar.msg, function(result) {
					if (result) {
						datosTablas.debitosSeleccionados.length = 0;
						datosTablas.creditosSeleccionados.length = 0;
						datosTablas.otrosDebitosSeleccionados.length = 0;
						datosTablas.documentosCapsSeleccionados.length = 0;
						datosTablas.mediosPagos.length = 0;
						totalDeDocumentos.actualizar();
						consultaCotizacionCompensaciones();
						fechaCotizPrev = $("#fechaTipoCambio").val();
					} else {
						$('#fechaTipoCambio').val(fechaCotizPrev);
					}
				});
			} else {
				consultaCotizacionCompensaciones();
				fechaCotizPrev = $("#fechaTipoCambio").val();
			}
		}

	});

	// $('#fechaTipoCambio').hover(function() {
	// if ($("#fechaTipoCambio").is(":focus")) {
	// $(CONF_COBRO_TAB_CLIENTE).focus();
	// }
	// });

	$("#fechaTipoCambio").keydown(function(event) {
		if (KEY_BS_ENTER === (event.keyCode ? event.keyCode : event.which)) {
			$("#tipoDeCambio").focus();
		}
		return true;
	});

	// buscarAcuerdoPorLinea se hace solo onfocusout y no enter
	$("#lineaInput").focusout(function() {
		$("#wsConsultaJms").text("");
		$("#wsConsultaJms").css('display', 'none');
		tabPrevErrorInput.linea = false;
		limpiarMarcaErrorTabPrevisualizar();

		var lineaInputVal = $('#lineaInput').val().trim();
		if (lineaInputVal != "") {
			$('#inputTratamientoDeDiferencia').find('input').prop('disabled', true);
			tabPrevErrorInput.acuerdo = false;
			// se valida que la linea tenga asociada un
			// acuerdo y le paso la operacion 2 que es
			// validar la linea y luego el acuerdo
			buscarAcuerdoPorLinea(lineaInputVal);
			setTimeout(function() {
				$('#inputTratamientoDeDiferencia').find('input').prop('disabled', false);
			}, 500);
		} else {
			limpiarErrores();
		}
	});
	$("#lineaInput").keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		return validarInputNumerico(event) || keycode == KEY_BS_RETROCESO;
		;
	});
	// se hace solo onfocusout y no enter
	$("#acuerdoFactInput").focusout(function(el) {
		// el: es todo el window que se usa despues
		estadoButtons.habilitarSimularByCorreccionInput();
		$('#inputTratamientoDeDiferencia').find('input').prop('disabled', true);
		validarAcuerdo(el);
		setTimeout(function() {
			$('#inputTratamientoDeDiferencia').find('input').prop('disabled', false);
		}, 500);
	});
	$("#acuerdoFactInput").keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		return validarInputNumerico(event) || keycode == KEY_BS_RETROCESO;
		;
	});
	$("#acuerdoFactInput").change(function(event) {
		var acuerdoFactInputVal = $('#acuerdoFactInput').val();
		if (!$.isEmpty(acuerdoFactInputVal) && $.validacionIsNumeric(acuerdoFactInputVal)) {
			verificarCambiInputTraDifBqEspera();
		}
	});
	function validarAcuerdo(el) {
		// limpiarErrores();
		$("#wsConsultaJms2").text("");
		$("#wsConsultaJms2").css('display', 'none');
		tabPrevErrorInput.acuerdo = false;
		tabPrevErrorInput.acuerdoAutocompletar = false;
		limpiarMarcaErrorTabPrevisualizar();

		var acuerdoFactInputVal = $('#acuerdoFactInput').val();
		if (acuerdoFactInputVal != "") {
			if (!$.isEmpty(acuerdoFactInputVal) && !$.validacionIsNumeric(acuerdoFactInputVal)) {
				$("#wsConsultaJms2").text("Este campo debe respetar el formato 9999999999.");
				$("#wsConsultaJms2").css('display', 'inline-block');
				$(CONF_COBRO_TAB_PREV).css({
					"color" : "red",
					"font-weight" : "bold"
				});
				tabPrevErrorInput.acuerdo = true;
			} else {
				// el.currentTarget: es el input text para
				// manejar el mensaje
				var input = el.currentTarget;
				if (MIC_DESC_CORTA === $('#selectSistDestino').val()) {
					flagBloqueDeEspera.inicializar([ 'fValidarAcuerdoContraClienteMic', 'fValidarSimulacion' ]);
					validarAcuerdoContraClienteMic(input, true, false, false, null);
					finBloqueDeEspera();
				} else if (CALIPSO_DESC_CORTA === $('#selectSistDestino').val()) {
					flagBloqueDeEspera.inicializar([ 'fValidarAcuerdoContraClienteCLP', 'fValidarSimulacion' ]);
					validarAcuerdoContraClienteCLP(input, true, false, false, null);
					finBloqueDeEspera();
				}
			}
		}
		// TODO - a Fabio PREV 1 Maxi, si el acuerdo esta vacio,
		// muestro error de campo requerido para mic y calipso?
		// else {
		// $("#wsConsultaJms2").css('display', 'inline-block');
		// $("#wsConsultaJms2").html('Este campo es
		// requerido.');
		// };
	}

	// U572497 Guido.Settecerze
	function validarAcuerdoExistenteCLP(acuerdoFactInput) {
		var acuerdoFactVar = acuerdoFactInput.split(" ").join("");
		var cobro = {
			numeroAcuerdo : acuerdoFactVar
		};
		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/validarAcuerdoExistenteCLP",
			"dataType" : "json",
			"data" : JSON.stringify(eval(cobro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					$("#estadoAcuerdoFacturacion").val(result.estadoAcuerdoFacturacion);
					$("#clienteAcuerdoFacturacion").val(result.clienteAcuerdo);
					limpiarErrores();
				} else {
					$("#wsConsultaJms2").css('display', 'inline-block');
					$("#wsConsultaJms2").text(result.descripcionError);
					$(CONF_COBRO_TAB_PREV).css({
						"color" : "red",
						"font-weight" : "bold"
					});
				}
				return result.success;
			}
		});
	}

	// U572497 Guido.Settecerze
	function autoCompletarCampoAcuerdo() {
		var cobro = {
			clientes : datosTablas.clientesSeleccionados
		};
		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/autoCompletarCampoAcuerdo",
			"dataType" : "json",
			"data" : JSON.stringify(eval(cobro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				tabPrevErrorInput.acuerdoAutocompletar = false;
				var nroAcuerdo = result.nroAcuerdo;
				if (nroAcuerdo != null) {
					$("#acuerdoFactInput").val(nroAcuerdo);
					-$("#estadoAcuerdoFacturacion").val(result.estadoAcuerdoFacturacion);
					$("#clienteAcuerdoFacturacion").val(result.clienteAcuerdo);
					limpiarErrores();
					$("#wsConsultaJms2").css('display', 'none');
					$("#wsConsultaJms2").text('');
					verificarCambiInputTraDif();
				} else {
					if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
						$("#wsConsultaJms2").css('display', 'inline-block');
						$("#wsConsultaJms2").text(result.descripcionError);
						$(CONF_COBRO_TAB_PREV).css({
							"color" : "red",
							"font-weight" : "bold"
						});
					}
					flagBloqueDeEspera.fValidarSimulacion = true;
					tabPrevErrorInput.acuerdoAutocompletar = true;
				}
				flagBloqueDeEspera.fAutoCompletarCampoAcuerdo = true;
			}
		});
	}
	;
	$('#prevTransacciones tbody').on('click', 'td', function() {
		selectedRow.withoutFixedColumn(this, 'transacciones', 'prevTransacciones');
	});
	$('#prevTransacciones').on('page.dt', function() {
		selectedRow.transaccionesCambioPag = true;
	});

	// ----------------------------------------------------------------------------------------
	// COMPROBANTE APLICACION MANUAL
	// ----------------------------------------------------------------------------------------

	$('#btnAdjComprobanteAplManual').click(function() {
		$('#bloqueEspera').trigger('click');
		$("#errorArchComprobanteAplManual").hide();

		if (datosTablas.comprobantesAplManual.length == 0) {

			var error = false;
			if (!$('#comprobanteArchAplManual').val()) {
				$("#errorArchComprobanteAplManual").text("Debe seleccionar un archivo.");
				$("#errorArchComprobanteAplManual").show();
				error = true;
			}

			if (!error) {
				$("#adjComprobanteFormAplManual").ajaxForm({
					dataType : 'json',
					data : {
						idCobro : $('#idCobro').val(),
						descripcion : "Detalle de la Aplicación Manual",
						motivoAdjunto : MOTIVO_ADJUNTO_APLICACION_MANUAL
					},
					success : function(data) {
						$('.fileupload').fileupload('clear');
						if (data.success) {
							var comp = {
								idComprobante : data.id,
								nombreArchivo : data.fileName,
								descripcionArchivo : data.descripcion
							};

							tablas.comprobantesAplManual.fnAddData([ comp ], true);
							datosTablas.comprobantesAplManual.push(comp);
							$('#btnAdjComprobanteAplManual').prop('disabled', true);

						} else {
							$(data.campoError + "AplManual").text(data.descripcionError);
							$(data.campoError + "AplManual").show();
						}
						ocultarBloqueEspera();
					}
				}).submit();
			} else {
				ocultarBloqueEspera();
			}
		} else {
			ocultarBloqueEspera();
		}

	});

	var codigoOperacionesExternas = {
		dom : '<"botonExcelcodigoOperacionesExternas">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
		buttons : [ {
			extend : "excelHtml5",
			text : "Excel",
			title : "Cobro_CodigoOperacionesExternas_Previsualizacion",
			className : 'excelbtn',
			exportOptions : {
				columns : ':visible',
			}
		} ],
		// ACA
		"aoColumns" : [ {
			"mData" : "grupo", // 0
			"width" : 100
		}, {
			"mData" : "nroTransaccion" // 1
		}, {
			"mData" : "sistema" // 2
		}, {
			"mData" : "codigoOperacionExterna" // 3
		}, {
			"mData" : "referencia" // 4
		}, {
			"mData" : "importe" // 5
		}, {
			"mData" : "responsableImputacion" // 6
		}, {
			"mData" : "fechaImputacion" // 7
		} ]
	};
	tablas.codigoOperacionesExternas = $("#codigoOperacionesExternas").dataTable(codigoOperacionesExternas);

	$('#comprobantesAplManual tbody').on('click', 'button', function() {
		var node = $(this).parents('tr')[0];
		var aData = tablas.comprobantesAplManual.fnGetData(node);

		if ($(this).attr('action') == 'eliminar') {
			$('#bloqueEspera').trigger('click');
			$.ajax({
				"type" : "POST",
				"url" : "configuracion-cobro/eliminarComprobante",
				"dataType" : "json",
				"data" : "{ \"idComprobante\": " + aData.idComprobante + "}",
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						tablas.comprobantesAplManual.fnDeleteRow(node);
						datosTablas.comprobantesAplManual = $.grep(datosTablas.comprobantesAplManual, function(seleccionado) {
							return seleccionado.idComprobante != aData.idComprobante;
						});
						$('#btnAdjComprobanteAplManual').prop('disabled', false);
					}
					ocultarBloqueEspera();
				}
			});
		} else {
			var iframe = document.createElement("iframe");
			iframe.src = "configuracion-cobro/descargarComprobante?id=" + aData.idComprobante;
			iframe.style.display = "none";
			document.body.appendChild(iframe);
		}
	});

	// ----------------------------------------------------------------------------------------
	// FIN COMPROBANTE APLICACION MANUAL
	// ----------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB PREVISUALIZACION
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// GUARDAR
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	$('button[id="btnGuardar"]').click(function() {
		
		if (validacionOkFecha) {
			flagBloqueDeEspera.inicializar([ 'fGuardarConfCobro', 'fHabilitarBtnSimular', 'modificarTransaccionesConIntereses', 'habilitarBtnImputarYCargarGrillaTransacciones' ]);

			if (validarDatosCobro()) {
				setTimeout(function() {
					if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
						$('#modificarTransacciones').val("true");
					} else {
						$('#modificarTransacciones').val("false");
					}

					// En esta evento no
					// utilizo el ajax
					// de simular
					esBtnGuardar = true;
					guardarCobro();

					// esta funcion
					// valdia si se debe
					// terminar el
					// bloque de espera
					finBloqueDeEspera();
				}, 1300);
			} else {
				setTimeout(function() {
					flagBloqueDeEspera.clear();
					ocultarBloqueEspera();
				}, 500);
			}

		}
	});

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// SIMULAR
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------

	function simular() {
		btnSimularClicked = true;
		contadorMensajesError.resetearError();
		if (validacionOkFecha) {
			// flagBloqueDeEspera.inicializar(['fGuardarConfCobro',
			// 'modificarTransaccionesConIntereses',
			// 'fHabilitarBtnSimular', 'fSimularCobro',
			// 'habilitarBtnImputarYCargarGrillaTransacciones']);
			if (validarDatosCobro()) {
				setTimeout(function() {
					// seteo el flag para simular el cobro
					// despues del guardado
					$('#simularCobro').val("true");
					guardarCobro();
					// esta funcion valida si se debe terminar
					// el bloque de espera
					// finBloqueDeEspera();
				}, 1300);
			} else {
				flagBloqueDeEspera.clear();
				setTimeout(function() {
					// $('#btnSimular').prop('disabled', false);
					ocultarBloqueEspera();
				}, 500);
			}
		}
		btnSimularClicked = false;
	}

	$('#btnSimular').click(function() {
		$('#btnSimular').prop('disabled', true);

		flagBloqueDeEspera.inicializar([ 'fVerficarSimulacionOk', 'fGuardarConfCobro', 'modificarTransaccionesConIntereses', 'fHabilitarBtnSimular', 'fSimularCobro', 'habilitarBtnImputarYCargarGrillaTransacciones' ]);

		var cobro = {
			idCobro : $('#idCobro').val()
		};

		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/verficiarSimulacionOk",
			"dataType" : "json",
			"data" : JSON.stringify(eval(cobro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					var mensaje = "Advertencia: al continuar con la simulación, se refrescará la grilla de transacciones y los datos ingresados.";
					bootbox.confirm(mensaje, function(result) {
						if (result) {
							simular();
						} else {
							flagBloqueDeEspera.clear();
							ocultarBloqueEspera();
						}
					});
				} else {
					simular();
				}
				flagBloqueDeEspera.fVerficarSimulacionOk = true;
			}
		});
		// flagBloqueDeEspera.comando =
		// tareaHabilitarBtn;
		// flagBloqueDeEspera.comandoName =
		// 'ejecutar';
		// flagBloqueDeEspera.comandoParam =
		// 'btnSimular';
		finBloqueDeEspera();
		setTimeout(function() {
			$('#btnSimular').prop('disabled', false);
		}, 1000);

	});

	var buscarAcuerdoPorLinea = function(lineaInput) {
		flagBloqueDeEspera.inicializar([ 'fValidarAcuerdoContraClienteMic',
		// 'fAutoCompletarCampoAcuerdo',
		'fValidarSimulacion', 'fBuscarAcuerdoPorLinea' ]);
		var cobro = {
			numeroLinea : lineaInput
		};
		$.ajax({
			"type" : "POST",
			"url" : "configuracion-cobro/buscarAcuerdoPorLinea",
			"dataType" : "json",
			"data" : JSON.stringify(eval(cobro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					$("#estadoAcuerdoFacturacion").val(result.estadoAcuerdoFacturacion);
					limpiarErrores();
					$("#wsConsultaJms").css('display', 'none');
					$("#wsConsultaJms").text('');
					var idAcuerdo = "acuerdoFactInput";
					$("#" + idAcuerdo).val(result.nroAcuerdo);
					// se valida el estado del acuerdo y
					// que tenga clientes del cobro
					validarAcuerdoContraClienteMic($("#" + idAcuerdo), true, false, false, null);
				} else {
					$("#wsConsultaJms").css('display', 'inline-block');
					$("#wsConsultaJms").text(result.descripcionError);
					$(CONF_COBRO_TAB_PREV).css({
						"color" : "red",
						"font-weight" : "bold"
					});
					tabPrevErrorInput.linea = true;
					flagBloqueDeEspera.clear();
				}
				flagBloqueDeEspera.fBuscarAcuerdoPorLinea = true;
			}
		});
		finBloqueDeEspera();
	};
	// habilito el orden por fecha a la tabla de transacciones
	var idTabla = "prevTransacciones";
	agregarOrdenFecha(idTabla);

	if (tablas.debitosSeleccionados.fnGetData() != null) {
		$('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
	}

	$('button[id^="btnVerCarta"]').click(function() {
		$('#bloqueEspera').trigger('click');
		beforeUnload.off();
		btnVerCartaOnClick();
		setTimeout(function() {
			beforeUnload.on();
			ocultarBloqueEspera();
		}, 5000);
	});
	$('button[id^="btnVerResumenCompensacion"]').click(function() {
		$('#bloqueEspera').trigger('click');
		beforeUnload.off();
		btnVerResumenCompensacionOnClick();
		setTimeout(function() {
			beforeUnload.on();
			ocultarBloqueEspera();
		}, 5000);
	});

	if (datosTablas.comprobantesAplManual.length == 0) {
		$('#btnAdjComprobanteAplManual').prop('disabled', false);
	} else {
		$('#btnAdjComprobanteAplManual').prop('disabled', true);
	}

	if (datosTablas.comprobantesOtrosDebito.length == 0) {
		$('#btnAdjComprobanteOtrosDeb').prop('disabled', false);
	} else {
		$('#btnAdjComprobanteOtrosDeb').prop('disabled', true);
	}

	logicaBotonAdjuntarOtrosDebito();
	
//	function descargarComprobanteEnGrillaOtrosDeb(idAdjunto, idBoton) {
//		var aData = null;
//		// var row = null;
//
//		if (idBoton == 'btnDescargarAdjunto') {
//			$(tablas.otrosDebSeleccionados.fnGetData()).each(function(j, debito) {
//				if (debito.idAdjunto == idAdjunto) {
//					aData = tablas.otrosDebSeleccionados.fnGetData([ j ]);
//					$('#bloqueEspera').trigger('click');
//					var iframe = document.createElement("iframe");
//					iframe.src = "configuracion-cobro/descargarComprobante?id=" + aData.idAdjunto;
//					iframe.style.display = "none";
//					document.body.appendChild(iframe);
//					ocultarBloqueEspera();
//				}
//			});
//		}
//	}
});

var limpiarErrores = function() {

	$(CONF_COBRO_TAB_CLIENTE).css({
		"color" : "#5f5f5f",
		"font-weight" : "normal"
	});
	if (!contadorMensajesErrorGuardableDebitos.isError()) {
		$(CONF_COBRO_TAB_DEB).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
	}

	$(CONF_COBRO_TAB_OTROS_DEB).css({
		"color" : "5f5f5f",
		"font-weight" : "normal"
	});

	if (!contadorMensajesErrorGuardableCreditos.isError()) {
		$(CONF_COBRO_TAB_CRED).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
	}
	grisarSolapaCreditosSegunMotivo($('#selectMotivo').val());
	$(CONF_COBRO_TAB_MED_PAG).css({
		"color" : "#5f5f5f",
		"font-weight" : "normal"
	});
	$(CONF_COBRO_TAB_COMPROBANTE).css({
		"color" : "#5f5f5f",
		"font-weight" : "normal"
	});
	$(CONF_COBRO_TAB_PREV).css({
		"color" : "#5f5f5f",
		"font-weight" : "normal"
	});
	$("span.error").text("");
};

var btnImputarvalidacionTrasacciones = function(result) {
	if (result.success) {
		listaTransacciones = result.aaData;
		if (listaTransacciones.length > 0) {
			// cargo los datos en la sesion
			datosTablas.transacciones = listaTransacciones;

			// Cargo los datos en pantalla y a su vez actualizo la sesion
			tablas.transaccionesPrev.fnClearTable();
			tablas.transaccionesPrev.fnAddData(listaTransacciones, true);
			tablas.transaccionesPrev.fnAdjustColumnSizing(false);
			// Actualizo el cambio habilitarEdicionSinDifCambio en
			// CobroOnlineDto
			// Si hay un error 8043 No se puede generar documento por diferencia
			// de cambio
			if (!result.simulacionEnBatch && !result.transaccionesOK) {
				habitarSinDiferenciaCambioDebito();
			}
			// actualizo los totales
			totalInteresesTransacciones.actualizar();
			selectedRow.clearVariables('transacciones');
		}
		limpiarErrores();

		if (!result.simulacionEnBatch) {
			if (result.transaccionesOK && !$.isEmpty(listaTransacciones)) {
				transaccionesOK = true;
				if (result.imputable) {
					$('#btnImputar').prop('disabled', false);
				} else {
					$('#btnImputar').prop('disabled', true);
				}
			} else {
				transaccionesOK = false;
				$('#btnImputar').prop('disabled', true);
			}
			if (!result.simulablePorEstado) {
				$('#btnSimular').prop('disabled', true);
			}
		} else {
			$('#btnImputar').prop('disabled', true);
			$('#btnSimular').prop('disabled', true);
			habilitarEdicionParcial();
		}
		if (result.descripcionError !== "" && result.descripcionError != null) {
			$("#mensajeErrorSimular").css('display', 'inline-block');
			$("#mensajeErrorSimular").text(result.descripcionError);
		}
	} else {
		$("#mensajeErrorSimular").css('display', 'inline-block');
		$("#mensajeErrorSimular").text(result.descripcionError);
		$('#btnImputar').prop('disabled', true);
		if (ERRROR_CAMBIO_COBRO_SIMULADO == result.descripcionError) {
			listaTransacciones = result.aaData;
			if (listaTransacciones.length > 0) {
				// cargo los datos en la sesion
				datosTablas.transacciones = listaTransacciones;

				// Cargo los datos en pantalla y a su vez actualizo la sesion
				tablas.transaccionesPrev.fnClearTable();
				tablas.transaccionesPrev.fnAddData(listaTransacciones, true);
				tablas.transaccionesPrev.fnAdjustColumnSizing(false);
				// actualizo los totales
				totalInteresesTransacciones.actualizar();
				selectedRow.clearVariables('transacciones');
			}
		}
		simulacionOK = false;
	}
	if (varEdicionSegunEstadoMarca == 'edicionParcial') {
		$('#btnImputar').prop('disabled', true);
		$('#btnSimular').prop('disabled', true);
		habilitarEdicionParcial();
	}
	actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
	if (COB_ERROR_COBRO_DESC == result.estado.estadoDescripcion || COB_ERROR_APROPIACION_DESC == result.estado.estadoDescripcion) {
		// $('button[id="btnGuardar"]').attr('disabled', true);
		if (COB_ERROR_COBRO_DESC == result.estado.estadoDescripcion) {
			$('#estado').val(COB_ERROR_COBRO_NAME);
		} else {
			$('#estado').val(COB_ERROR_APROPIACION_NAME);
		}
	}
	// Solo se utiliza en la solapa 5 'previsualizar'
	estadoButtons.btnSimular.disabled = $('#btnImputar').prop('disabled');
	estadoButtons.btnImputar.disabled = $('#btnImputar').prop('disabled');
	if (emptyGrillas()) {
		$('#btnImputar').prop('disabled', true);
		estadoButtons.btnImputar.disabled = $('#btnImputar').prop('disabled');
	}

	if (result.observacionesDocCap !== null || !$.isEmpty(result.observacionesDocCap)) {
		$("#prevObservCapText2").val(result.observacionesDocCap);
	}
};
var habitarSinDiferenciaCambioDebito = function() {
	var size = datosTablas.transacciones.length;
	var sizeDebitos = datosTablas.debitosSeleccionados.length;
	var moneda = _monedaDelCobro;

	var regExp = new RegExp('(.)*' + ERROR_CALIPSO_NO_GENERAR_DIFERECIA_CAMBIO + '(.)*');
	var regExpOld = new RegExp('(.)*' + ERROR_CALIPSO_NO_GENERAR_DIFERECIA_CAMBIO_OLD + '(.)*');

	for (var indexTransacciones = 0; indexTransacciones < size; indexTransacciones++) {
		if (!$.isEmpty(datosTablas.transacciones[indexTransacciones].idFactura) || datosTablas.transacciones[indexTransacciones].idFactura !== '-') {

			if (regExp.test(datosTablas.transacciones[indexTransacciones].mensajeDebito) || regExpOld.test(datosTablas.transacciones[indexTransacciones].mensajeDebito)) {
				// Debo buscar el debito asociado a la factura
				for (var indexDebitos = 0; indexDebitos < sizeDebitos; indexDebitos++) {
					if (datosTablas.transacciones[indexTransacciones] !== '-' && datosTablas.transacciones[indexTransacciones].nroDoc === datosTablas.debitosSeleccionados[indexDebitos].nroDoc && datosTablas.transacciones[indexTransacciones].moneda !== moneda) {
						datosTablas.debitosSeleccionados[indexDebitos].habilitarEdicionSinDifCambio = SI_NAME;
					}
				}

			}
		}
	}
};
// Evito los repetidos
var prepararNuevaListaSeleccionados = function(lista, listaSeleccionados) {
	var agregar = [];
	var existe = false;
	$(lista.fnGetData()).each(function(i, debito) {
		$(listaSeleccionados.fnGetData()).each(function(j, seleccionado) {
			if (seleccionado.idDebitoPantalla == debito.idDebitoPantalla) {
				existe = true;
				return false;
			}
		});
		if (!existe) {
			agregar.push(debito);
		}
		existe = false;
	});

	return agregar;
};

// Evito los repetidos VER
var prepararNuevaListaSeleccionadosCre = function(lista, listaSeleccionados) {
	var agregar = [];
	var existe = false;
	$(lista.fnGetData()).each(function(i, credito) {
		$(listaSeleccionados.fnGetData()).each(function(j, seleccionado) {
			if (seleccionado.idCreditoPantalla == credito.idCreditoPantalla) {
				existe = true;
				return false;
			}
		});
		if (!existe) {
			agregar.push(credito);
		}
		existe = false;
	});

	return agregar;
};

// u573005 sprint 4
// se validan en deimos para actualizar el semaforo
var validarEstadoDeimos = function(listaAdata, tabla, tablaSeleccionados, datosTablaSeleccionados, idMuestroError, idBotonEliminar) {
	if (listaAdata.length > 0) {
		// consulto deimos
		var creditosDebitos = {
			listaIdatosComunesEntrada : listaAdata,
			empresaCodigo : $("#selectEmpresa").val(),
			listaIdatosYaSeleccionados : datosTablaSeleccionados
		};

		$('#bloqueEspera').trigger('click');
		$.ajax({
			"dataType" : "json",
			"type" : "POST",
			"url" : "configuracion-cobro/validarEstadoDeimos",
			"data" : JSON.stringify(eval(creditosDebitos)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				errorMensaje = result.errorMensaje;
				if (errorMensaje !== null) {
					// Mostrar el error
					$('#' + idMuestroError).text(errorMensaje);
					ocultarBloqueEspera();
				} else {
					$('#' + idMuestroError).text("");

					var seAgregaronRegistros = false;
					var listaDebitosCreditos = result.listaDebitosCreditos;
					var listaDebitosCreditosSeleccionados = result.listaDebitosCreditosSeleccionados;

					if (listaDebitosCreditos.length > 0) {
						if (listaDebitosCreditos.length > 1) {
							// Si son varios, Cargar la tabla de arriba
							// de debitos y creditos
							tabla.fnClearTable();
							tabla.fnAddData(listaDebitosCreditos, true);
						} else {
							// Si es uno solo
							$(tabla.fnGetData()).each(function(j, debito) {
								if (debito.idPantalla == listaDebitosCreditos[0].idPantalla) {
									aparienciaButton.ocultarButtonPlus(tabla, listaDebitosCreditos[0].idPantalla, j);
									var semaforoGestionabilidad = '.' + GESTIONABILIDAD_NO_CLASS + listaDebitosCreditos[0].idPantalla;
									var idButton = '.' + BTN_NO_CLASS + listaDebitosCreditos[0].idPantalla;
									$(semaforoGestionabilidad).removeClass('gestion-semaforo-rojo gestion-semaforo-amarillo');
									if (SEMAFORO_ROJO_DESCRIPCION === listaDebitosCreditos[0].semaforo.semaforo) {
										$(semaforoGestionabilidad).addClass('gestion-semaforo-rojo');

									} else {
										$(semaforoGestionabilidad).addClass('gestion-semaforo-amarillo');
										// actualizar
										// estado en
										// deimos
										seAgregaronRegistros = true;
									}
									if (debito.type == 'CobroCreditoDto') {
										$(tabla.fnGetNodes(j)).find('td:eq(28)').text(listaDebitosCreditos[0].estadoDeimos);
									} else {
										$(tabla.fnGetNodes(j)).find('td:eq(19)').text(listaDebitosCreditos[0].estadoDeimos);
									}
									$(idButton).closest('div').find('div:eq(0)').prop('title', listaDebitosCreditos[0].semaforo.descripcionError);
									return false;
								}
							});
						}
					}

					if (listaDebitosCreditosSeleccionados.length > 0) {
						datosTablaSeleccionados = $.merge(datosTablaSeleccionados, listaDebitosCreditosSeleccionados);
						// tablaSeleccionados.fnClearTable();
						tablaSeleccionados.fnAddData(listaDebitosCreditosSeleccionados, true);
						tablaSeleccionados.fnAdjustColumnSizing(true);

						totalDeDocumentos.actualizar();
						aparienciaButton.ocultarButtonCreditosDebitosSeleccionados(datosTablaSeleccionados);
						if (idMuestroError === 'errorRespuestaDebitos') {
							detalleDebitosDelCobro.subTodosDocumentos();
							detalleDebitosDelCobro.addLista(datosTablaSeleccionados);
						}
						seAgregaronRegistros = true;
					}

					if (seAgregaronRegistros) {
						$('#' + idBotonEliminar).attr('disabled', false);
					}
					ocultarBloqueEspera();
				}

			}
		});
	}
};
// retorna elementos del segundo vector que no se encuentren en el primer vector
function filtraDuplicados(primerVector, segundoVector) {
	var primerVectorLength = primerVector.length;
	var segundoVectorLength = segundoVector.length;
	var vectorSalida = [];

	for (var indexSegundo = 0; indexSegundo < segundoVectorLength; indexSegundo++) {
		var encontrado = false;
		for (var indexPrimero = 0; indexPrimero < primerVectorLength; indexPrimero++) {
			if (segundoVector[indexSegundo].idPantalla == primerVector[indexPrimero].idPantalla) {
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			vectorSalida.push(segundoVector[indexSegundo]);
		}
	}

	return vectorSalida;
}
var validarTratamientoDiferenciaInpurOp = function() {
	var index = $('#conf-cobro-tabs .current').index();
	var salida = true;

	if (index == 5) {
		if (diferencia != "0,00") {
			var selectEnvioFinal = "";
			if ($("#selectEnvio").val() == "") {
				selectEnvioFinal = $("#selectEnvio1").val();
			} else {
				selectEnvioFinal = $("#selectEnvio").val();
			}
			if (!$.isEmpty(selectEnvioFinal)) {
				if (tabPrevErrorInput.selectEnvio) {
					return false;
				}

				if (selectEnvioFinal == 'ENVIO_A_GANANCIAS' || selectEnvioFinal == 'DEBITO_PROX_FAC' || selectEnvioFinal == 'SALDO_A_COBRAR' || selectEnvioFinal == 'SALDO_A_PAGAR') {
					return true;
				}

				if (btnSimularClicked) {
					if ('APLICACION_MANUAL' === selectEnvioFinal) {
						var sistemaApliManual = $('#sistemaAplicacionManual').val();
						if ($.isEmpty(sistemaApliManual)) {
							$("#errorSistemaDestino").text("Este campo es requerido.");
							$('#errorSistemaDestino').css('display', 'inline-block');

							salida = false;
						} else if (datosTablas.comprobantesAplManual.length == 0) {
							$('#errorComprobanteAplicacionManualSimular').text("Se requiere el campo Comprobante de Aplicación Manual.");
							$('#errorComprobanteAplicacionManualSimular').show();
							salida = false;
						} else {
							return true;
						}

					}
					if ($.isEmpty($('#fechaAltaTramReint').val())) {
						$("#errorFechaAltaTramReint").text("Este campo es requerido.");
						$('#errorFechaAltaTramReint').css('display', 'inline-block');
						salida = false;
					}
					if ($.isEmpty($('#tramReintInput').val())) {
						$("#errorTramReintInput").text("Este campo es requerido.");
						$('#errorTramReintInput').css('display', 'inline-block');
						salida = false;
					}

					if ($.isEmpty($('#acuerdoFactInput'))) {
						if (tabPrevErrorInput.acuerdoAutocompletar) {
							tabPrevErrorInput.acuerdoAutocompletar = false;
						}
					}

					if (selectEnvioFinal == 'REINTEGRO_CRED_PROX_FAC') {
						var sistemaEnvio = $('#selectSistDestino').val();

						if ($.isEmpty(sistemaEnvio)) {
							$("#errorSistemaDestino").text("Este campo es requerido.");
							$('#errorSistemaDestino').css('display', 'inline-block');
							salida = false;
						} else {
							var acuerdoFactInputVal = $('#acuerdoFactInput').val();
							if (!$.isEmpty(acuerdoFactInputVal) && !$.validacionIsNumeric(acuerdoFactInputVal)) {
								$("#wsConsultaJms2").text("Este campo debe respetar el formato 9999999999.");
								$("#wsConsultaJms2").css('display', 'inline-block');
								$(CONF_COBRO_TAB_PREV).css({
									"color" : "red",
									"font-weight" : "bold"
								});
								salida = false;
							} else if ($.isEmpty(acuerdoFactInputVal)) {
								$("#wsConsultaJms2").text("Este campo es requerido.");
								$("#wsConsultaJms2").css('display', 'inline-block');
								$(CONF_COBRO_TAB_PREV).css({
									"color" : "red",
									"font-weight" : "bold"
								});
								salida = false;
							}

							var lineaInputVal = $("#lineaInput").val();
							if (!$.isEmpty(lineaInputVal) && !$.validacionIsNumeric(lineaInputVal)) {
								$("#wsConsultaJms").text("Este campo debe respetar el formato 9999999999.");
								$("#wsConsultaJms").css('display', 'inline-block');
								$(CONF_COBRO_TAB_PREV).css({
									"color" : "red",
									"font-weight" : "bold"
								});
								salida = false;
							}
						}
					}
					if (tabPrevErrorInput.fechaAltaTramReint || tabPrevErrorInput.tramReintInputRequerido || tabPrevErrorInput.acuerdo || tabPrevErrorInput.linea || tabPrevErrorInput.acuerdoAutocompletar) {
						salida = false;
					}
				}
			}
			if (tabPrevErrorInput.fechaAltaTramReint) {
				salida = false;
			}
		}

		if (salida) {
			trataDifErrorClear();
		}
	}
	return salida;
};
var emptyGrillas = function() {
	if ($.isEmpty(datosTablas.debitosSeleccionados) && $.isEmpty(datosTablas.creditosSeleccionados) && $.isEmpty(datosTablas.mediosPagos) && $.isEmpty(datosTablas.otrosDebitosSeleccionados)) {
		return true;
	}
	return false;
};

var obtenerTratamientoDiferencia = function() {
	var selectEnvioFinal = "";

	if ($.isEmpty(datosTablas.tratamientoDiferencia)) {
		// Si no hay datos de tratamiento Diferencia, lo inicializo de esta
		// manera
		var datosTratamientoDiferencia = {
			tipoTratamientoDiferencia : '',
			// conCalculoInteres: ,
			numeroTramiteReintegro : '',
			fechaAltaTramiteReintegro : '',
			sistemaDestino : '',
			linea : '',
			acuerdoFacturacion : '',
			importe : '',
			moneda : '',
			referencia : ''
		};
		return datosTratamientoDiferencia;
	} else {
		// Si se han eliminadoClientes y cambio la diferencia de tal manera que
		// el tratamiento de la diferencia ya sea correcto
		// Elimino la seleccion
		trataDifActulizaOpciones();
		if ($("#selectEnvio").val() == "") {
			selectEnvioFinal = $("#selectEnvio1").val();
		} else {
			selectEnvioFinal = $("#selectEnvio").val();
		}
		// Si se elige compensacion Liquido producto, se elimina el tratamiento
		// viejo que pueda llegar a tener
		if (!$.isEmpty(datosTablas.mediosPagos) && datosTablas.mediosPagos[0].idMpTipoCredito == 35) {
			selectEnvioFinal = "";
		}

		var sistemaAUsar = "";
		var referencia = "";
		if (APLICACION_MANUAL === $("#selectEnvio").val()) {
			sistemaAUsar = $("#sistemaAplicacionManual").val();
			referencia = $("#referenciaInput").val();
		} else {
			sistemaAUsar = $("#selectSistDestino").val();
		}

		var datosTratamientoDiferencia = {
			tipoTratamientoDiferencia : selectEnvioFinal,
			// conCalculoInteres: ,
			numeroTramiteReintegro : $("#tramReintInput").val(),
			fechaAltaTramiteReintegro : $("#fechaAltaTramReint").val(),
			sistemaDestino : sistemaAUsar,
			linea : $("#lineaInput").val(),
			acuerdoFacturacion : $("#acuerdoFactInput").val(),
			estadoAcuerdoFacturacion : $("#estadoAcuerdoFacturacion").val(),
			idClienteAcuerdoFacturacion : $("#clienteAcuerdoFacturacion").val(),
			importe : diferencia,
			moneda : _monedaDelCobro,
			referencia : referencia
		};
		// guardo los datos de tratamiento en la sesion para recuperarlos cuando
		// cargo la solapa
		return datosTratamientoDiferencia;
	}
};

var guardarCobro = function(habilitarBtnImputar) {
	datosTablas.tratamientoDiferencia = obtenerTratamientoDiferencia();

	if (!$.isEmpty(datosTablas.documentosCapsSeleccionados && !$.isEmpty(datosTablas.mediosPagos))) {
		datosTablas.mediosPagos[0].listaDocumentoCap = datosTablas.documentosCapsSeleccionados;
	}
	var idComprobanteAplManual = null;
	if (!$.isEmpty(datosTablas.comprobantesAplManual)) {
		// Como los comprobantes de apliacion manual al momento de configuar
		// solo van a ser 1
		idComprobanteAplManual = datosTablas.comprobantesAplManual[0].idComprobante;
	}
	var idComprobanteOtrosDebitos = null;
	if (!$.isEmpty(datosTablas.comprobantesOtrosDebito)) {
		idComprobanteOtrosDebitos = datosTablas.comprobantesOtrosDebito[0].idComprobante;
	}

	var cobro = {
		idCobro : $('#idCobro').val(),
		idCobroPadre : $('#idCobroPadre').val(),
		idOperacion : $('#idOperacion').val(),
		idEmpresa : $('#selectEmpresa').val(),
		idSegmento : $('#selectSegmento').val(),
		monedaOperacion : _monedaDelCobro,
		idMotivoCobro : $('#selectMotivo').val(),
		observacion : $('#prevObservText').val(),
		idAnalista : $('#idAnalista').val(),
		idCopropietario : $('#selectCopropietario').val(),
		tratamientoDiferencia : datosTablas.tratamientoDiferencia,
		clientes : datosTablas.clientesSeleccionados,
		debitos : datosTablas.debitosSeleccionados,
		otrosDebitos : datosTablas.otrosDebitosSeleccionados,
		creditos : datosTablas.creditosSeleccionados,
		medios : datosTablas.mediosPagos,
		fechaTipoCambio : $('#fechaTipoCambio').val(),
		tipoCambio : $('#tipoDeCambio').val(),
		btnGuardar : esBtnGuardar,
		idAdjuntoApliacionManualPrev : idComprobanteAplManual,
		idAdjuntoOtrosDebitos : idComprobanteOtrosDebitos,
		listaIdsAdjuntosOtrosDebitos : datosTablas.idsAdjuntosOtrosDeb,
		vengoReedicion: $('#vengoReedicion').val(),
		idTarea: $('#idTarea').val()
	};

	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/guardarConfCobro",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {

			if (result.success) {
				if (result.vengoReedicion) {
					$('button[id="btnGuardar"]').text('Guardar').attr('disabled', false);
					$('#vengoReedicion').val('');
					$('#idTarea').val('');

				}
				if ($('#modificarTransacciones').val() == "true" && $('#exportarExcel').val() != "ExportarModificarTransacciones") {
					// guardo las transacciones modificables y luego disparo la
					// imputacion
					modificarTransaccionesConIntereses();
				} else if ($('#exportarExcel').val() == "ExportarModificarTransacciones") {
					modificarTransaccionesConIntereses(true);
				} else {
					flagBloqueDeEspera.modificarTransaccionesConIntereses = true;
				}
				if ($('#exportarExcel').val() == "true") {
					beforeUnload.off();
					exportarDetalleAExcel();
					setTimeout(function() {
						beforeUnload.on();
					}, 5000);
				}

				if (verHistorial) {
					// Como cambio de vista. desactivo el event
					// beforeunload
					beforeUnload.off();
					flagBloqueDeEspera.clear();
					$('#idOperacionFormateado').val(result.idOperacion);
					$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobro-historial";
					$('#formCobro')[0].submit();
				}
				if (result.idCobro != null && result.idCobro != "") {
					$('#idCobro').val(result.idCobro);
					habilitarBtnHistorial();
				}
				if (result.idCobroPadre != null && result.idCobroPadre != "") {
					// TODO Maxi: hay que ver para que se utiliza el dCobroPadre
					// no se esta seteando en guardarConfCobro
					$('#idCobroPadre').val(result.idCobroPadre);
				}
				if (result.idOperacion != null && result.idOperacion != "") {
					$('#idOperacion').val(result.idOperacion);
				}
				if (result.informacionMensaje != null && result.informacionMensaje != "") {
					$('#estado').val(result.informacionMensaje);
				}
				habilitarBtnExportar();

				if ($('#simularCobro').val() == "true") {
					simularCobro();
					flagBloqueDeEspera.fHabilitarBtnSimular = true;
				} else {
					// Si es la solapa previsualizar habilito el btnSimular
					if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
						if (!emptyGrillas() && !vengoReedicion()) {
							habilitarBtnSimular();
						} else {
							$('#btnSimular').prop('disabled', true);
							estadoButtons.btnSimular.disabled = $('#btnSimular').prop('disabled');
							flagBloqueDeEspera.fHabilitarBtnSimular = true;
						}
					} else {
						flagBloqueDeEspera.fHabilitarBtnSimular = true;
					}
				}
				if (result.primerCobro) {
					$('#idCobro').val(result.idCobro);
					// TODO Maxi: hay que ver para que se utiliza el dCobroPadre
					// no se esta seteando guardarConfCobro
					$('#idCobroPadre').val(result.idCobroPadre);
					$('#idOperacion').val(result.idOperacion);
					$('#estado').val(result.informacionMensaje);
				}
				if (COB_ERROR_COBRO_NAME == $('#estado').val() || COB_ERROR_APROPIACION_NAME == $('#estado').val()) {
					logicaAlGuardarErrorCobroErrorApropiacion(result.idCobro);
					flagBloqueDeEspera.clear();
				}
				if (habilitarBtnImputar != undefined && habilitarBtnImputar == true) {
					// if (!emptyGrillas()) {
					// habilitarBtnImputarYCargarGrillaTransacciones();
					// } else {
					// $('#btnImputar').prop('disabled', true);
					// flagBloqueDeEspera.habilitarBtnImputarYCargarGrillaTransacciones
					// = true;
					// }
					habilitarBtnImputarYCargarGrillaTransacciones();
				} else {
					flagBloqueDeEspera.habilitarBtnImputarYCargarGrillaTransacciones = true;
				}
				if ($('#simularCobro').val() != "true" && !(habilitarBtnImputar != undefined && habilitarBtnImputar == true)) {
					actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
				}
				if (ESTADO_VEC_OBSERVACION.indexOf(result.estado.estadoDescripcion) > -1 && esBtnGuardar) {
					if (!$.isEmpty(result.observacionAnterior)) {
						$('#prevObservTextAnterior').val(result.observacionAnterior);
						$('#prevObservacionesAnterior').show();
					} else {
						$('#prevObservacionesAnterior').hide();
					}
					$('#prevObservText').val(result.observacion);
				}
				cambiarModoEdicion(result.edicionSegunEstadoMarca, result.esPerfilSupervisorCobranza);

				if (result.eliminarComprobanteAplicacionManualPrev) {
					datosTablas.comprobantesAplManual.length = 0;
					tablas.comprobantesAplManual.fnClearTable();
					$('#btnAdjComprobanteAplManual').prop('disabled', false);
				}
			}
			if (esBtnGuardar) {
				esBtnGuardar = false;
			}
			flagBloqueDeEspera.fGuardarConfCobro = true;
		}
	});
};
function cambiarModoEdicion(informacionMensaje, validarEdicionCobroSegunUsuario) {
	varEdicionSegunEstadoMarca = "";
	if (validarEdicionCobroSegunUsuario) {
		$('#conf-cobro-tabs :input').attr('disabled', true);
		$('#conf-cobro-tabs :button').attr('disabled', true);
		$('#searchCriteraCliente').hide();
		$('#selectCopropietario').attr('disabled', false);
		$('#selectMotivo').attr('disabled', false);
		$('#prevObservText').attr('disabled', false);
		varEdicionSegunEstadoMarca = "edicionParcial";
		habilitarEdicionParcial();
	} else {
		if (informacionMensaje == "edicionParcial") {
			habilitarEdicionParcial();
		} else if (informacionMensaje == "edicionTotalMenosDebitos") {
			$('.bloqueDebitos :input').attr('disabled', true);
			$('.bloqueDebitos :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
			$('#btnEliminarTodosDebitos').attr('disabled', true);
			$('#customPagDebitos_previous').addClass('disabled');
			$('#customPagDebitos_next').addClass('disabled');
			$('#selectMotivo').attr('disabled', true);

			if (varEdicionSegunEstadoMarca != "edicionParcial") {
				varEdicionSegunEstadoMarca = "edicionTotalMenosDebitos";
			}
		} else if (informacionMensaje == "sinEdicion") {
			$('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#bloqueSubirDebitos').hide();
			varEdicionSegunEstadoMarca = "sinEdicion";
		} else if (informacionMensaje == "reedicion") {
			$('.btn').attr('disabled', false);
			$('#conf-cobro-tabs :input').attr('disabled', false);
			$('#conf-cobro-tabs :button').attr('disabled', false);
			$('#searchCriteraCliente').show();
			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);
			
		}
	}
	$('.bloqueDescargar').attr('disabled', false);
	$('.busquedaResultado').attr('disabled', false);

}

function actualizarEstadoCobroVista(descripcionEstado, descripcionMarca) {
	$('#cobroEstado').val(descripcionEstado);
	$('#divEstado').show();
	if (!$.isEmpty(descripcionMarca) && '-' !== descripcionMarca.trim()) {
		var marcas = descripcionMarca.split('|');
		$('#cobroSubEstados').val(marcas.join('\n'));
		// TODO UEHARA dejo por un tiempo por si hay que retornar todos los
		// marcas
		// $('#cobroSubEstados').css('height', (20 * marcas.length) + 'px');
		$('#divCobroSubEstados').show();
		// TODO PROVISORIO!!!!!!!!!!!
		$('#divBtnCompensacion').css('display', 'none');
	} else {
		$('#cobroSubEstados').val('');
		$('#divCobroSubEstados').hide();
	}
}

function simularCobro() {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val()
	};

	$.ajax({
		"dataType" : "json",
		"type" : "POST",
		"url" : "configuracion-cobro/simularCobro",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			flagBloqueDeEspera.fSimularCobro = true;
			btnImputarvalidacionTrasacciones(result);
			// seteo el flag para que deje de simular cuando guarda
			$('#simularCobro').val("false");
		}
	});
}

// Se guardan las transacciones en el cobro y despues se inicia la imputacion
var modificarTransaccionesConIntereses = function(sinImputar) {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val(),
		transacciones : datosTablas.transacciones
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/modificarTransaccionesConIntereses",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				if (sinImputar == undefined) {
					if ($('#imputarCobro').val() == "true") {
						$('#idOperacionFormateado').val(result.idOperacion);
						habilitarEdicionParcial();
						imputarSinAprobacion();
					} else if ($('#imputarConAprobacion').val() == "true") {
						$('#idOperacionFormateado').val(result.idOperacion);
						habilitarEdicionParcial();
						imputarConAprobacion();
					} else {
						flagBloqueDeEspera.modificarTransaccionesConIntereses = true;
					}
				} else {
					beforeUnload.off();
					exportarDetalleAExcel();
					setTimeout(function() {
						beforeUnload.on();
						flagBloqueDeEspera.modificarTransaccionesConIntereses = true;
					}, 5000);
				}
			}
		}
	});

};

// Guido.Settecerze
function logicaAlGuardarErrorCobroErrorApropiacion(idCobro) {
	$('#bloqueEspera').trigger('click');
	flagBloqueDeEspera.noTerminar = true;
	beforeUnload.off();
	$('#idCobro').val(idCobro);
	$('#formCobro')[0].action = urlAsynchronousHTTP + "/logicaAlGuardarErrorCobroErrorApropiacion";
	$('#formCobro')[0].submit();
};

// Guido.Settecerze
var habilitarBtnSimular = function() {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/habilitarBtnSimular",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				$('#btnSimular').prop('disabled', false);
			} else {
				$('#btnSimular').prop('disabled', true);
			}
			// Se utiliza para controlar el bloque de espera
			if (varEdicionSegunEstadoMarca == 'edicionParcial' || varEdicionSegunEstadoMarca == 'edicionTotalMenosDebitos') {
				$('#btnSimular').prop('disabled', true);
			}
			flagBloqueDeEspera.fHabilitarBtnSimular = true;
			estadoButtons.btnSimular.disabled = $('#btnSimular').prop('disabled');
			estadoButtons.btnSimular.causa = result.causa;
		}
	});
};

// Guido.Settecerze
var habilitarBtnExportar = function() {
	if ($('#idOperacion').val() == null || $('#idOperacion').val() == "") {
		$('button[id="btnExportar"]').prop('disabled', true);
	} else {
		$('button[id="btnExportar"]').prop('disabled', false);
	}
};

var exportarDetalleAExcel = function() {
	flagBloqueDeEspera.clear();
	$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobro-detalle-aprobacion/exportacionDetalleCobro";
	$('#formCobro')[0].submit();
	$('#exportarExcel').val("false");
};

var habilitarBtnHistorial = function() {
	$('button[id="btnHistorial"]').prop('disabled', false);
};
// Guido.Settecerze
var habilitarBtnImputarYCargarGrillaTransacciones = function() {
	var cobro = {
		transaccionesOK : transaccionesOK,
		idOperacion : $('#idOperacion').val(),
		idCobro : $('#idCobro').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/habilitarBtnImputarYCargarGrillaTransacciones",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			btnImputarvalidacionTrasacciones(result);
			flagBloqueDeEspera.habilitarBtnImputarYCargarGrillaTransacciones = true;
		}
	});
};

// Guido.Settecerze
var validarTransacciones = function() {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val()

	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarTransacciones",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				if ($('#prevObservText').val() == "") {
					$('#errorTransacciones').text("El campo Observaciones es requerido.");
					$('#errorObservaciones').text("Este campo es requerido.");
					$('#errorTransacciones').show();
					$('#errorObservaciones').show();
					$("#idSpan").css('display', 'inline-block');
					$(CONF_COBRO_TAB_CLIENTE).css({
						"color" : "red",
						"font-weight" : "bold"
					});
					flagBloqueDeEspera.terminar();
				} else {
					$(CONF_COBRO_TAB_CLIENTE).css({
						"color" : "#5f5f5f",
						"font-weight" : "normal"
					});
					$("#idSpan").css('display', 'none');
					$('#vaAsteriscoObservacion').val("false");
					$('#errorTransacciones').hide();
					$('#errorObservaciones').hide();
					if (validacionOkFecha) {
						validarSiRequiereAprobacion();
					}
				}
			} else {
				$(CONF_COBRO_TAB_CLIENTE).css({
					"color" : "#5f5f5f",
					"font-weight" : "normal"
				});
				$("#idSpan").css('display', 'none');
				$('#vaAsteriscoObservacion').val("false");
				$('#errorTransacciones').hide();
				$('#errorObservaciones').hide();
				if (validacionOkFecha) {
					validarSiRequiereAprobacion();
				}
			}
			flagBloqueDeEspera.validarTransacciones = true;
		}
	});
};
/**
 * Se utiliza en la solapa "previsualizar" para guardar el tratamiento de la
 * diferencia, se invoca cada ves que los campos de este se modifica.
 * Desabilitando el boton imputar.
 */
// var guardarTratamientoDiferenciaYHabilitarBtnImputar =
// function(btnSimularClicked) {
var guardarTratamientoDiferenciaYHabilitarBtnImputar = function() {
	var vTratamientoDif = validarTratamientoDiferenciaInpurOp();
	var selectEnvioFinal = "";
	if (vTratamientoDif) {
		$("#alertErrorGuardado").hide();
		limpiarErrores();
	} else {
		// btnSimularClicked es una variable globar que se setea en true cuando
		// se realizar un click en el btnSimular
		$("#errorGuardado").text("Error al guardar. Revise los errores.");
		$("#alertErrorGuardado").show();
		$(CONF_COBRO_TAB_PREV).css({
			"color" : "red",
			"font-weight" : "bold"
		});
		ocultarBloqueEspera();
		return;
	}
	if ($("#selectEnvio").val() == "") {
		selectEnvioFinal = $("#selectEnvio1").val();
	} else {
		selectEnvioFinal = $("#selectEnvio").val();
	}
	var sistemaAUsar = "";
	var referencia = "";
	if (APLICACION_MANUAL === $("#selectEnvio").val()) {
		sistemaAUsar = $("#sistemaAplicacionManual").val();
		referencia = $('#referenciaInput').val();
	} else {
		sistemaAUsar = $("#selectSistDestino").val();
	}
	var datosTratamientoDiferencia = {
		tipoTratamientoDiferencia : selectEnvioFinal,
		// conCalculoInteres: ,
		numeroTramiteReintegro : $("#tramReintInput").val(),
		fechaAltaTramiteReintegro : $("#fechaAltaTramReint").val(),
		sistemaDestino : sistemaAUsar,
		linea : $("#lineaInput").val(),
		acuerdoFacturacion : $("#acuerdoFactInput").val(),
		estadoAcuerdoFacturacion : $("#estadoAcuerdoFacturacion").val(),
		idClienteAcuerdoFacturacion : $("#clienteAcuerdoFacturacion").val(),
		importe : diferencia,
		moneda : _monedaDelCobro,
		referencia : referencia
	};
	datosTablas.tratamientoDiferencia = datosTratamientoDiferencia;
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val(),
		tratamientoDiferencia : datosTratamientoDiferencia
	};
	if (!$.isEmpty(datosTablas.comprobantesAplManual)) {
		cobro.listaComprobanteAplicacionManual = [];
		cobro.listaComprobanteAplicacionManual = datosTablas.comprobantesAplManual;
	}
	if (!$.isEmpty(datosTablas.comprobantesOtrosDebito)) {
		cobro.listaComprobanteOtrosDebito = [];
		cobro.listaComprobanteOtrosDebito = datosTablas.comprobantesOtrosDebito;
	}
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarSimulacion",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (!result.success) {
				$("#mensajeErrorSimular").css('display', 'inline-block');
				$("#mensajeErrorSimular").text(result.descripcionError);
				$('#btnImputar').prop('disabled', true);
			} else {
				$("#mensajeErrorSimular").css('display', 'none');
				$("#mensajeErrorSimular").text('');
			}
			actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
			if (result.eliminarComprobanteAplicacionManualPrev) {
				datosTablas.comprobantesAplManual.length = 0;
				tablas.comprobantesAplManual.fnClearTable();

				$('#btnAdjComprobanteAplManual').prop('disabled', false);

			}
			flagBloqueDeEspera.fValidarSimulacion = true;
		}
	});
};

// Guido.Settecerze
/**
 * Esta funcion tambien guarda el tratamiento de la direferencia
 */
var validarComprobanteObligatorio = function() {
	$('#btnImputar').prop('disabled', true);
	flagBloqueDeEspera.inicializar([ 'validarComprobanteObligatorio', 'validarDisponibilidadSaldoValoresEnProceso', 'validarTransacciones', 'validarSiRequiereAprobacion', 'fGuardarConfCobro', 'modificarTransaccionesConIntereses', 'fHabilitarBtnSimular', 'habilitarBtnImputarYCargarGrillaTransacciones' ]);

	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val(),
		idEmpresa : $('#selectEmpresa').val(),
		idSegmento : $('#selectSegmento').val(),
		monedaOperacion : _monedaDelCobro,
		tratamientoDiferencia : datosTablas.tratamientoDiferencia,
		debitos : datosTablas.debitosSeleccionados,
		creditos : datosTablas.creditosSeleccionados,
		medios : datosTablas.mediosPagos,
		transacciones : datosTablas.transacciones
	};

	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarComprobanteObligatorio",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (!result.success) {
				if (!$.isEmpty(result.informacionMensaje) && result.informacionMensaje != null) {

					// errorDescripcionComp es el error que se muestra
					// en la Tab comprobante
					// este no se va ver. dado que cuando cambio de tab
					// se borra. u578936 Max

					if (result.campoError === "Aplicacion") {
						$("#idSpan3").css('display', 'inline-block');
						$(CONF_COBRO_TAB_PREV).css({
							"color" : "red",
							"font-weight" : "bold"
						});
						$('#errorComprobanteAplicacionManualImputar').text(result.informacionMensaje);
						$('#errorComprobanteAplicacionManualImputar').show();

					} else {
						$('#errorTransacciones').text(result.informacionMensaje);
						$('#errorTransacciones').show();

						$('#errorDescripcionComp').text("Este campo es requerido.");
						$('#errorDescripcionComp').show();

						$("#idSpan2").css('display', 'inline-block');
						$(CONF_COBRO_TAB_COMPROBANTE).css({
							"color" : "red",
							"font-weight" : "bold"
						})
					}

				} else {
					$('#errorTransacciones').text(result.errors);
					$('#errorTransacciones').show();
				}

				flagBloqueDeEspera.terminar();
			} else {
				$(CONF_COBRO_TAB_COMPROBANTE).css({
					"color" : "#5f5f5f",
					"font-weight" : "normal"
				});
				$("#idSpan2").css('display', 'none');
				$('#vaAsteriscoObservacion').val("false");
				$('#errorTransacciones').hide();
				$('#errorDescripcionComp').hide();
				if (validacionOkFecha) {
					validarDisponibilidadSaldoValoresEnProceso();
				}
			}
			flagBloqueDeEspera.validarComprobanteObligatorio = true;
		}
	});
	flagBloqueDeEspera.comando = tareaHabilitarBtn;
	flagBloqueDeEspera.comandoName = 'ejecutar';
	flagBloqueDeEspera.comandoParam = 'btnImputar';
	finBloqueDeEspera();
};

var validarDisponibilidadSaldoValoresEnProceso = function() {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val(),
		idEmpresa : $('#selectEmpresa').val(),
		idSegmento : $('#selectSegmento').val(),
		monedaOperacion : _monedaDelCobro,
		tratamientoDiferencia : datosTablas.tratamientoDiferencia,
		debitos : datosTablas.debitosSeleccionados,
		creditos : datosTablas.creditosSeleccionados,
		medios : datosTablas.mediosPagos,
		transacciones : datosTablas.transacciones
	};

	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarDisponibilidadSaldoValoresEnProceso",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (!result.success) {
				if (!$.isEmpty(result.informacionMensaje) && result.informacionMensaje != null) {
					$('#errorTransacciones').text(result.informacionMensaje);
					$('#errorTransacciones').show();

					// errorDescripcionComp es el error que se muestra
					// en la Tab comprobante
					// este no se va ver. dado que cuando cambio de tab
					// se borra. u578936 Max
					$('#errorDescripcionComp').text("Este campo es requerido.");
					$('#errorDescripcionComp').show();

					$("#idSpan2").css('display', 'inline-block');
					$(CONF_COBRO_TAB_COMPROBANTE).css({
						"color" : "red",
						"font-weight" : "bold"
					});
				} else {
					$('#errorTransacciones').text(result.errors);
					$('#errorTransacciones').show();
				}

				flagBloqueDeEspera.terminar();
			} else {
				$(CONF_COBRO_TAB_COMPROBANTE).css({
					"color" : "#5f5f5f",
					"font-weight" : "normal"
				});
				$("#idSpan2").css('display', 'none');
				$('#vaAsteriscoObservacion').val("false");
				$('#errorTransacciones').hide();
				$('#errorDescripcionComp').hide();
				if (validacionOkFecha) {
					validarTransacciones();
				}
			}
			flagBloqueDeEspera.validarDisponibilidadSaldoValoresEnProceso = true;
		}
	});
};

// Guido.Settecerze
var validarSiRequiereAprobacion = function() {
	var cobro = {
		idCobro : $('#idCobro').val(),
		idOperacion : $('#idOperacion').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarSiRequiereAprobacion",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				if (validacionOkFecha) {
					// se setean los flags para que se ejecute la imputacion
					// luego de guardar las transacciones modificadas si las
					// hubiera
					$('#imputarCobro').val("false");
					$('#imputarConAprobacion').val("true");
					$('#modificarTransacciones').val("true");
					guardarCobro();
				}
			} else {
				if (validacionOkFecha) {
					$('#imputarCobro').val("true");
					$('#imputarConAprobacion').val("false");
					$('#modificarTransacciones').val("true");
					guardarCobro();
				}
			}
			flagBloqueDeEspera.validarSiRequiereAprobacion = true;
		}

	});
};

// U572497 Guido.Settecerze
function imputarConAprobacion() {
	$('#bloqueEspera').trigger('click');
	// Cambio de vista. Desactivo el evento beforenload
	beforeUnload.off();
	$('#formCobro')[0].action = urlAsynchronousHTTP + "/imputarConAprobacion";
	$('#formCobro')[0].submit();
};

// U572497 Guido.Settecerze
function imputarSinAprobacion() {
	$('#bloqueEspera').trigger('click');
	// Cambio de vista. Desactivo el evento beforenload
	beforeUnload.off();
	$('#formCobro')[0].action = urlAsynchronousHTTP + "/imputarSinAprobacion";
	$('#formCobro')[0].submit();
};

// MA.Uehara
var EliminarEnCascada = {
	// eliminarCreditosByCliente(idClienteLegado)
	// Elimino los creditos del cliente EliminarEnCascada.creditosByCliente
	'creditosByCliente' : function(idClienteLegado) {
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		var aux = [];
		for (var indice = 0, size = datosTablas.creditosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.creditosSeleccionados[indice].idClienteLegado != idClienteLegado) {
				aux.push(datosTablas.creditosSeleccionados[indice]);
			}
		}
		datosTablas.creditosSeleccionados.length = 0;
		datosTablas.creditosSeleccionados = aux;
	},
	// eliminarDebitosByCliente
	'debitosByCliente' : function(idClienteLegado) {
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		var aux = [];
		for (var indice = 0, size = datosTablas.debitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.debitosSeleccionados[indice].cliente != idClienteLegado) {
				aux.push(datosTablas.debitosSeleccionados[indice]);
			}
		}
		datosTablas.debitosSeleccionados.length = 0;
		datosTablas.debitosSeleccionados = aux;
	},
	'otrosDebitosByCliente' : function(idClienteLegado) {
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		var aux = [];
		for (var indice = 0, size = datosTablas.otrosDebitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.otrosDebitosSeleccionados[indice].cliente != idClienteLegado) {
				aux.push(datosTablas.otrosDebitosSeleccionados[indice]);
			}
		}
		datosTablas.otrosDebitosSeleccionados.length = 0;
		datosTablas.otrosDebitosSeleccionados = aux;
	},
	'mediosDePagoByCliente' : function(idClienteLegado) {
		var aux = [];
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);

		for (var indice = 0, size = datosTablas.mediosPagos.length; indice < size; indice++) {
			var clientesLegadosVector = datosTablas.mediosPagos[indice].clientesLegados.split(' - ');
			if ($.inArray(idClienteLegado, clientesLegadosVector) < 0) {
				aux.push(datosTablas.mediosPagos[indice]);
			} else {
				if (clientesLegadosVector.length != 1) {
					var auxIdCliente = [];
					for (var i = 0, sizeClientesLegados = clientesLegadosVector.length; i < sizeClientesLegados; i++) {
						if (clientesLegadosVector[i] != idClienteLegado) {
							auxIdCliente.push(clientesLegadosVector[i]);
						}
					}
					if (auxIdCliente.length > 1) {
						datosTablas.mediosPagos[indice].clientesLegados = auxIdCliente.join(' - ');
					} else if (auxIdCliente.length == 1) {
						datosTablas.mediosPagos[indice].clientesLegados = auxIdCliente[0];
					}
					aux.push(datosTablas.mediosPagos[indice]);
				}
			}
		}

		datosTablas.documentosCapsSeleccionados.length = 0;
		datosTablas.mediosPagos.length = 0;
		datosTablas.mediosPagos = aux;
	},
	'clienteById' : function(idClienteLegado) {
		var aux = [];
		for (var indice = 0, size = datosTablas.clientesSeleccionados.length; indice < size; indice++) {
			if (datosTablas.clientesSeleccionados[indice].idClienteLegado != idClienteLegado) {
				aux.push(datosTablas.clientesSeleccionados[indice]);
				// break;
			}
		}
		datosTablas.clientesSeleccionados.length = 0;
		datosTablas.clientesSeleccionados = aux;
	},
	'documentosByClienteId' : function(idClienteLegado) {
		this.debitosByCliente(idClienteLegado);
		this.otrosDebitosByCliente(idClienteLegado);
		this.creditosByCliente(idClienteLegado);
		this.clienteById(idClienteLegado);
		this.mediosDePagoByCliente(idClienteLegado);
		totalDeDocumentos.actualizar();
	}
};

// Llevo un contador por los errores de edicion de las grillas de creditos o de
// debitos que impida guardar en la base,
// al llevar la cuenta de errores evito utilizar loop
var contadorMensajesError = {
	'totalErroresGrilla' : 0,
	'restarError' : function(descripcion) {
		if (this.isErrorNoGuardableByDescripcion(descripcion)) {
			this.totalErroresGrilla--;
		}
	},
	'sumarErrorByDescripcion' : function(descripcion) {
		if (this.isErrorNoGuardableByDescripcion(descripcion)) {
			this.totalErroresGrilla++;
		}
	},
	'isErrorNoGuardableByDocumento' : function(documento) {
		if (documento.descripcionErrorValidacion == msgErrorInputNulo || documento.descripcionErrorValidacion == msgErrorInput || documento.descripcionErrorValidacion == msgErrorInputLength || documento.descripcionErrorValidacion == msgErrorInputPorcentaje

		) {
			return true;
		}
		return false;
	},
	'isErrorNoGuardableByDescripcion' : function(descripcion) {
		if (descripcion == msgErrorInputNulo || descripcion == msgErrorInput || descripcion == msgErrorInputLength || descripcion == msgErrorInputPorcentaje || (!$.isEmpty(descripcion) && descripcion.startsWith(TIPO_MENSAJE_ERROR_DESC))) {
			return true;
		}
		return false;
	},
	'isError' : function() {
		return (this.totalErroresGrilla != 0);
	},
	'resetearError' : function() {
		this.totalErroresGrilla = 0;
	}
};

// contador para activar las solapas debitos/creditos
var contadorMensajesErrorGuardableDebitos = {
	'totalErroresGuardable' : 0,
	'restarErrorGuardable' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable--;
		}
	},
	'sumarErrorGuardableByDescripcion' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable++;
		}
	},
	'isErrorGuardableByDocumento' : function(documento) {
		if (!$.isEmpty(documento.descripcionErrorValidacion) && documento.descripcionErrorValidacion.startsWith(msgErrorSuperaMaximoPermitido)) {
			return true;
		}
		return false;
	},
	'isErrorGuardableByDescripcion' : function(descripcion) {
		if (!$.isEmpty(descripcion) && descripcion.startsWith(msgErrorSuperaMaximoPermitido)) {
			return true;
		}
		return false;
	},
	'isError' : function() {
		return (this.totalErroresGuardable != 0);
	},
	'resetearError' : function() {
		this.totalErroresGuardable = 0;
	}
};

var contadorMensajesErrorGuardableOtrosDebitos = {
	'totalErroresGuardable' : 0,
	'restarErrorGuardable' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable--;
		}
	},
	'sumarErrorGuardableByDescripcion' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable++;
		}
	},
	'isErrorGuardableByDocumento' : function(documento) {
		if (!$.isEmpty(documento.descripcionErrorValidacion) && documento.descripcionErrorValidacion.startsWith(msgErrorSuperaMaximoPermitido)) {
			return true;
		}
		return false;
	},
	'isErrorGuardableByDescripcion' : function(descripcion) {
		if (descripcion == msgErrorInputNulo || descripcion == msgErrorInput || descripcion == msgErrorInputLength || descripcion == msgErrorInputPorcentaje || (!$.isEmpty(descripcion) && descripcion.startsWith(TIPO_MENSAJE_ERROR_DESC))) {
			return true;
		}
		return false;
	},
	'isError' : function() {
		return (this.totalErroresGuardable != 0);
	},
	'resetearError' : function() {
		this.totalErroresGuardable = 0;
	}
};

var contadorMensajesErrorGuardableCreditos = {
	'totalErroresGuardable' : 0,
	'restarErrorGuardable' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable--;
		}
	},
	'sumarErrorGuardableByDescripcion' : function(descripcion) {
		if (this.isErrorGuardableByDescripcion(descripcion)) {
			this.totalErroresGuardable++;
		}
	},
	'isErrorGuardableByDocumento' : function(documento) {
		if (!$.isEmpty(documento.descripcionErrorValidacion) && documento.descripcionErrorValidacion.startsWith(msgErrorSuperaMaximoPermitido)) {
			return true;
		}
		return false;
	},
	'isErrorGuardableByDescripcion' : function(descripcion) {
		if (!$.isEmpty(descripcion) && descripcion.startsWith(msgErrorSuperaMaximoPermitido)) {
			return true;
		}
		return false;
	},
	'isError' : function() {
		return (this.totalErroresGuardable != 0);
	},
	'resetearError' : function() {
		this.totalErroresGuardable = 0;
	}
};

// MA.Uehara funciones para totalizar el importe de debitos y creditos
// Calcula la diferencia entre los debitos y la suma de los creditos y medio de
// pagos
var totalDeDocumentos = {
	'total' : function(documento, campo) {
		var total = 0;
		for (var i = 0, size = documento.length; i < size; i++) {
			if (!$.isEmpty(documento[i][campo]) && documento[i][campo] !== '-') {
				total = total + importeToFloat(documento[i][campo]);
			}
		}
		return total;
	},
	'actualizar' : function() {
		// Se hace la sumatoria de los Débitos y los Otros Débitos
		var totalDebito = this.total(datosTablas.debitosSeleccionados, 'impACobrar') + this.total(datosTablas.otrosDebitosSeleccionados, 'importe');
		var totalCredito = this.total(datosTablas.creditosSeleccionados, 'importeAUtilizar');
		var totalMediosDepagos = this.total(datosTablas.mediosPagos, 'importe');

		var subTotal = totalCredito + totalMediosDepagos;
		var delta = parseFloat(totalDebito.toFixed(2)) - parseFloat(subTotal.toFixed(2));
		$('[id^=prevTotal]').val(_monedaDelCobro + formatear(delta));
		diferencia = $("#prevTotal_p4").val().replace("U$S", "");
		diferencia = diferencia.replace("$", "");
		$('[id^=prevSumCreditos]').val(_monedaDelCobro + ' ' + formatear(totalCredito + totalMediosDepagos));
		$('[id^=prevSumDebitos]').val(_monedaDelCobro + ' ' + formatear(totalDebito));
	},
	'actualizarCap' : function() {
		var totalCaps = this.total(datosTablas.documentosCapsSeleccionados, 'importeGestionable');
		$('#compensacionTotal').val(_monedaDelCobro + ' ' + formatear(totalCaps));
		this.actualizar();
		return totalCaps;
	}
};

// MA.Uehara
// Verifico si el cliente posse algun credito / debito / medio de pago / cargos
// a próxima factura en el cobro asociados
// No lo utilizo como objetos
// Se usa para obtener el la cantidad documentos y por que monto posee un
// cliente.
var detalleDocumentos = {
	// Retorna la cantidad de débitos que selecciono un cliente y su monto
	// total.
	'debitosByCliente' : function(idClienteLegado) {
		var detalle = new Object();
		detalle.cant = 0;
		detalle.monto = 0;
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		for (var indice = 0, size = datosTablas.debitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.debitosSeleccionados[indice].cliente == idClienteLegado) {
				detalle.cant++;
				detalle.monto += importeToFloat(datosTablas.debitosSeleccionados[indice].impACobrar);
			}
		}
		return detalle;
	},
	'otrosDebByCliente' : function(idClienteLegado) {
		var detalle = new Object();
		detalle.cant = 0;
		detalle.monto = 0;
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		for (var indice = 0, size = datosTablas.otrosDebitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.otrosDebitosSeleccionados[indice].cliente == idClienteLegado) {
				detalle.cant++;
				detalle.monto += importeToFloat(datosTablas.otrosDebitosSeleccionados[indice].importe);
			}
		}
		return detalle;
	},
	// Retorna la cantidad de créditos que selecciono un cliente y su monto
	// total.
	'creditosByCliente' : function(idClienteLegado) {
		var detalle = new Object();
		detalle.cant = 0;
		detalle.monto = 0;
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		for (var indice = 0, size = datosTablas.creditosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.creditosSeleccionados[indice].idClienteLegado == idClienteLegado) {
				detalle.cant++;
				detalle.monto += importeToFloat(datosTablas.creditosSeleccionados[indice].importeAUtilizar);
			}
		}
		return detalle;
	},
	// Retorna la cantidad de medios de pago que selecciono un cliente y su
	// monto total.
	'mediosDePagoByCliente' : function(idClienteLegado) {
		var detalle = new Object();
		detalle.cant = 0;
		detalle.monto = 0;
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		for (var indice = 0, size = datosTablas.mediosPagos.length; indice < size; indice++) {
			var clientesLegadosVector = datosTablas.mediosPagos[indice].clientesLegados.split(' - ');
			if ($.inArray(idClienteLegado, clientesLegadosVector) >= 0) {
				detalle.cant++;
				detalle.monto += importeToFloat(datosTablas.mediosPagos[indice].importe);
			}
		}
		return detalle;
	},
	// Retorna la cargos a proxima facturas que selecciono un cliente y su monto
	// total.
	// 'cargosAProximaFacturaByCliente': function(idClienteLegado) {
	// var detalle = new Object();
	// detalle.cant = 0;
	// detalle.monto = 0;
	//
	// return detalle;
	// },
	// retorna la cantidad de documentos que posee el cliente
	// retornaClienteDeseleccionarCant
	// detalleDocumentos.documentosByCliente
	'documentosByCliente' : function(idClienteLegado) {
		var detalle = new Object();
		detalle.cant = 0;
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);
		for (var indice = 0, size = datosTablas.mediosPagos.length; indice < size; indice++) {
			var clientesLegadosVector = datosTablas.mediosPagos[indice].clientesLegados.split(' - ');
			if ($.inArray(idClienteLegado, clientesLegadosVector) >= 0) {
				detalle.cant++;
			}
		}
		for (var indice = 0, size = datosTablas.debitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.debitosSeleccionados[indice].cliente == idClienteLegado) {
				detalle.cant++;
			}
		}
		for (var indice = 0, size = datosTablas.creditosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.creditosSeleccionados[indice].idClienteLegado == idClienteLegado) {
				detalle.cant++;
			}
		}
		return detalle;
	},
	// Verifica si el cliente a deseleccionar posee documetos
	// detalleDocumentos.verificarClienteDeseleccionar
	'verificarClienteDeseleccionar' : function(idClienteLegado) {
		idClienteLegado = eliminaCerosADerecha(idClienteLegado);

		for (var indice = 0, size = datosTablas.mediosPagos.length; indice < size; indice++) {
			var clientesLegadosVector = datosTablas.mediosPagos[indice].clientesLegados.split(' - ');
			if ($.inArray(idClienteLegado, clientesLegadosVector) >= 0) {
				return true;
			}
		}

		for (var indice = 0, size = datosTablas.debitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.debitosSeleccionados[indice].cliente == idClienteLegado) {
				return true;
			}
		}

		for (var indice = 0, size = datosTablas.otrosDebitosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.otrosDebitosSeleccionados[indice].cliente == idClienteLegado) {
				return true;
			}
		}

		for (var indice = 0, size = datosTablas.creditosSeleccionados.length; indice < size; indice++) {
			if (datosTablas.creditosSeleccionados[indice].idClienteLegado == idClienteLegado) {
				return true;
			}
		}

		for (var indice = 0, size = datosTablas.documentosCapsSeleccionados.length; indice < size; indice++) {
			if (datosTablas.documentosCapsSeleccionados[indice].idClienteLegado == idClienteLegado) {
				return true;
			}
		}
		return !$.isEmpty($('#acuerdoFactInput').val());
	},
	// Verifica si el cliente a deseleccionar posee documetos
	// detalleDocumentos.verificarClientesDeseleccionar
	'verificarClientesDeseleccionar' : function() {
		if (datosTablas.debitosSeleccionados.length > 0 || datosTablas.creditosSeleccionados.length > 0 || datosTablas.mediosPagos.length > 0 || datosTablas.otrosDebitosSeleccionados.length > 0) {
			return true;
		}
		return !$.isEmpty($('#acuerdoFactInput').val());
	},
	// retorna un objeto con
	// detalle.cant => cantidad total de documentos
	// detalle.msg => mensaje de error;
	// 'detalleDocumentos.detalleClientesADesasociar'
	// argumento tipoMensaje si es undefined es para eliminar cliente si es
	// moneda es para el cambio de moneda

	'detalleClientesADesasociar' : function(tipoMensaje) {
		var detalleDebitos = new Object();
		var detalleCreditos = new Object();
		var detalleMedios = new Object();
		var detalleOtrosDeb = new Object();
		// var detalleProxima = new Object();
		var detalle = new Object();

		detalleDebitos.cant = 0.00;
		detalleDebitos.monto = 0.00;
		detalleOtrosDeb.cant = 0.00;
		detalleOtrosDeb.monto = 0.00;
		detalleCreditos.cant = 0.00;
		detalleCreditos.monto = 0.00;
		detalleMedios.monto = 0.00;
		detalleMedios.cant = 0;
		detalle.cant = 0.00;
		detalle.msg = "";

		detalleDebitos.cant = datosTablas.debitosSeleccionados.length;
		for (var indice = 0; indice < detalleDebitos.cant; indice++) {
			detalleDebitos.monto += importeToFloat(datosTablas.debitosSeleccionados[indice].impACobrar);
		}

		detalleOtrosDeb.cant = datosTablas.otrosDebitosSeleccionados.length;
		for (var indice = 0; indice < detalleOtrosDeb.cant; indice++) {
			detalleOtrosDeb.monto += importeToFloat(datosTablas.otrosDebitosSeleccionados[indice].importe);
		}

		detalleCreditos.cant = datosTablas.creditosSeleccionados.length;
		for (var indice = 0; indice < detalleCreditos.cant; indice++) {
			detalleCreditos.monto += importeToFloat(datosTablas.creditosSeleccionados[indice].importeAUtilizar);
		}

		detalleMedios.cant = datosTablas.mediosPagos.length;
		for (var indice = 0; indice < detalleMedios.cant; indice++) {
			detalleMedios.monto += importeToFloat(datosTablas.mediosPagos[indice].importe);
		}
		// TODO LUEGO VER FACTURAS A PROXIMO PAGO
		detalle.cant = detalleDebitos.cant + detalleCreditos.cant + detalleMedios.cant;
		detalle.cantDebitos = detalleDebitos.cant + detalleOtrosDeb.cant;

		detalle.msg = this.creaMsgErrorEliminarTodosLosClientes(detalleDebitos, detalleOtrosDeb, detalleCreditos, detalleMedios, null, tipoMensaje);

		return detalle;
	},
	// retorna un objeto con
	// detalle.cant => cantidad total de documentos
	// detalle.msg => mensaje de error;
	// 'detalleDocumentos.detalleClienteADesasociar'
	'detalleClienteADesasociar' : function(idClienteLegado) {
		var detalleDebitos = new Object();
		var detalleCreditos = new Object();
		var detalleMedios = new Object();
		var detalleOtrosDeb = new Object();
		// var detalleProxima = new Object();
		var detalle = new Object();

		detalleDebitos.cant = 0.0;
		detalleDebitos.monto = 0.0;
		detalleOtrosDeb.cant = 0.0;
		detalleOtrosDeb.monto = 0.0;
		detalleCreditos.cant = 0.0;
		detalleCreditos.monto = 0.0;
		detalle.cant = 0.0;
		detalle.msg = "";
		detalleDebitos = this.debitosByCliente(idClienteLegado);
		detalleCreditos = this.creditosByCliente(idClienteLegado);
		detalleOtrosDeb = this.otrosDebByCliente(idClienteLegado);
		detalleMedios = this.mediosDePagoByCliente(idClienteLegado);

		// detalleproxima factura VER
		// TODO LUEGO VER FACTURAS A PROXIMO PAGO
		detalle.cant = detalleDebitos.cant + detalleCreditos.cant + detalleMedios.cant;
		detalle.cantDebitos = detalleDebitos.cant + detalleOtrosDeb.cant;
		detalle.msg = this.creaMsgErrorEliminarTodosLosClientes(detalleDebitos, detalleOtrosDeb, detalleCreditos, detalleMedios, null);
		return detalle;
	},
	'detalleCreditosMediosPagoADesasociar' : function(tipoMensaje) {
		var detalleCreditos = new Object();
		var detalleMedios = new Object();
		var detalleDebitos = new Object();
		var detalleOtrosDeb = new Object();
		// var detalleProxima = new Object();
		var detalle = new Object();

		detalleDebitos.cant = 0.00;
		detalleDebitos.monto = 0.00;
		detalleOtrosDeb.cant = 0.00;
		detalleOtrosDeb.monto = 0.00;
		detalleCreditos.cant = 0.00;
		detalleCreditos.monto = 0.00;
		detalleMedios.monto = 0.00;
		detalleMedios.cant = 0;
		detalle.cant = 0.00;
		detalle.msg = "";

		detalleCreditos.cant = datosTablas.creditosSeleccionados.length;
		for (var indice = 0; indice < detalleCreditos.cant; indice++) {
			detalleCreditos.monto += importeToFloat(datosTablas.creditosSeleccionados[indice].importeAUtilizar);
		}

		detalleDebitos.cant = datosTablas.debitosSeleccionados.length;
		for (var indice = 0; indice < detalleDebitos.cant; indice++) {
			detalleDebitos.monto += importeToFloat(datosTablas.debitosSeleccionados[indice].impACobrar);
		}

		detalleOtrosDeb.cant = datosTablas.otrosDebitosSeleccionados.length;
		for (var indice = 0; indice < detalleOtrosDeb.cant; indice++) {
			detalleOtrosDeb.monto += importeToFloat(datosTablas.otrosDebitosSeleccionados[indice].importe);
		}

		detalleMedios.cant = datosTablas.mediosPagos.length;
		for (var indice = 0; indice < detalleMedios.cant; indice++) {
			detalleMedios.monto += importeToFloat(datosTablas.mediosPagos[indice].importe);
		}
		// TODO LUEGO VER FACTURAS A PROXIMO PAGO
		detalle.cant = detalleCreditos.cant + detalleMedios.cant + detalleDebitos.cant + detalleOtrosDeb.cant;

		detalle.msg = this.creaMsgErrorEliminarTodosLosClientes(detalleDebitos, detalleOtrosDeb, detalleCreditos, detalleMedios, null, null);
		return detalle;
	},
	'creaMsgErrorEliminarTodosLosClientes' : function(detalleDebitos, detalleOtrosDeb, detalleCreditos, detalleMedios, detalleProxima, tipoMensaje) {

		var msg = '';

		if (detalleDebitos != null || detalleOtrosDeb != null) {
			msg = 'Existen Débitos/Otros Débitos/Créditos/otros medios de pago/cargos a próxima factura ';
		} else {
			msg = 'Existen Créditos/otros medios de pago/cargos a próxima factura ';
		}

		if (tipoMensaje === undefined) {
			msg += 'en el cobro asociados a el(los) cliente(s) que desea eliminar.';
		} else {
			msg += 'asociados al cobro que se desea modificar.';
		}
		// funciï¿½n interna para armar el mensaje
		function detallePorTipoDocumento(detalle, tipo) {
			var msgParte = ' La cantidad de ' + detalle.cant + ' ' + tipo + ' por el monto de ' + returnSignoMoneda(_monedaDelCobro) + formatear(detalle.monto) + ' serán desasociados.';
			return msgParte;
		}
		;
		if (detalleDebitos != null && detalleDebitos.cant != 0) {
			msg += detallePorTipoDocumento(detalleDebitos, 'Débitos');
		}
		if (detalleOtrosDeb != null && detalleOtrosDeb.cant != 0) {
			msg += detallePorTipoDocumento(detalleOtrosDeb, 'Otros Débitos');
		}
		if (detalleCreditos != null && detalleCreditos.cant != 0) {
			msg += detallePorTipoDocumento(detalleCreditos, 'Créditos');
		}
		if (detalleMedios != null && detalleMedios.cant != 0) {
			msg += detallePorTipoDocumento(detalleMedios, 'otros medios de pago');
		}
		if (detalleProxima != null && detalleProxima.cant != 0) {
			msg += detallePorTipoDocumento(detalleProxima, 'cargos a próxima factura');
		}
		msg += " ¿Desea continuar con la operación?.";
		return msg;
	}
};

// M.A.Uehara
var aparienciaButton = {
	// Button para todas las tablas
	'ocultarButtonPlus' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index); // cambio el font color de los
		// clientes seleccionados
		$(aRow).children().css('color', CSS_SELECTED_ROW);
		if (isNotButton === undefined) {
			$('.' + BTN_NO_CLASS + id).attr('disabled', true).html(HTML_ICON_BLANK);
		}
	},
	'descubrirButtonPlus' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index);

		if (isNotButton !== undefined) {
			if (isNotButton !== 'renglon') {
				$(aRow).children().css('color', '');
				if ('semaforo-rojo' === isNotButton) {
					$('.' + BTN_NO_CLASS + id + '.semaforo-rojo').attr('disabled', false).html(HTML_ICON_BLANK);
				} else {
					$('.' + BTN_NO_CLASS + id + '.' + isNotButton).attr('disabled', false).html(HTML_ICON_PLUS);
				}
			}
		} else {
			$(aRow).children().css('color', '');
			$('.' + BTN_NO_CLASS + id).attr('disabled', false).html(HTML_ICON_PLUS);
		}
	},

	// Se oculta un button plus mediante el id de la entidad
	'ocultarbuttonPlusById' : function(id) {
		$('.' + BTN_NO_CLASS + id).attr('disabled', true).html(HTML_ICON_BLANK);
	},
	// Tabla Clientes
	// Hace visible un boton segun la tabla clientes y el id => idClienteLegado
	'descubrirButtonPlusCliente' : function(tabla, id) {
		$(tabla.fnGetData()).each(function(j, cliente) {
			if (cliente.idClienteLegado == id) {
				aparienciaButton.descubrirButtonPlus(tabla, id, j);
				return false;
			}

		});
	},
	// hace visible todos los botones de la tabla de seleccion clientes que
	// estan presentes en clientesSeleccionados
	'descubrirButtonPlusClienteTodos' : function(tabla, tablaSeleccionados) {
		$(tablaSeleccionados.fnGetData()).each(function(j, clienteSeleccionado) {
			$(tabla.fnGetData()).each(function(i, cliente) {
				if (clienteSeleccionado.idClienteLegado == cliente.idClienteLegado) {
					aparienciaButton.descubrirButtonPlus(tabla, cliente.idClienteLegado, i);
					return false;
				}
			});
		});
	},
	// Se llama cuando se realiza una nueva busqueda y ya hay clientes
	// seleccionados
	'ocultarButtonClientesSeleccionados' : function(tabla, tablaSeleccionados) {
		$(tablaSeleccionados.fnGetData()).each(function(j, clienteSeleccionado) {
			$(tabla.fnGetData()).each(function(i, cliente) {
				if (clienteSeleccionado.idClienteLegado == cliente.idClienteLegado) {
					aparienciaButton.ocultarButtonPlus(tabla, cliente.idClienteLegado, i);
					return false;
				}
			});
		});
	},
	// Tablas Creditos y Debitos
	// hace visible todos los botones de la tablas de creditos o debitos que
	// esten presentes en la de seleccionaods
	'descubrirButtonPlusDocumentosTodos' : function(tabla, tablaSeleccionados) {
		$(tablaSeleccionados.fnGetData()).each(function(j, seleccionado) {
			$(tabla.fnGetData()).each(function(i, doc) {
				if (seleccionado.idPantalla == doc.idPantalla) {
					if ("DocumentoCapDto" === doc.type) {
						aparienciaButton.descubrirButtonPlus(tabla, doc.idPantalla, j, doc.esReglonAgrupador ? doc.classSemaforo : 'renglon');
					} else {
						aparienciaButton.descubrirButtonPlus(tabla, doc.idPantalla, j);
					}
					return false;
				}
			});
		});
	},
	// ocultarButtonDebitosSeleccionados
	// Se llama cuando se realiza una nueva busqueda y ya hay creditos o debitos
	// seleccionados
	'ocultarButtonCreditoDebitoSeleccionados' : function(tabla, tablaSeleccionados) {
		$(tablaSeleccionados.fnGetData()).each(function(j, docSeleccionado) {
			$(tabla.fnGetData()).each(function(i, doc) {
				if (docSeleccionado.idPantalla == doc.idPantalla) {
					if ("DocumentoCapDto" === doc.type) {
						aparienciaButton.ocultarButtonPlus(tabla, doc.idPantalla, i, doc.esReglonAgrupador ? undefined : true);
					} else {
						aparienciaButton.ocultarButtonPlus(tabla, doc.idPantalla, i);
					}
					return false;
				}
			});
		});
	},
	// hace visible un button en la tabla por id
	'descubrirButtonPlusDocumento' : function(tabla, id) {
		$(tabla.fnGetData()).each(function(j, doc) {
			if (id == doc.idPantalla) {
				if ("DocumentoCapDto" === doc.type) {
					aparienciaButton.descubrirButtonPlus(tabla, id, j, doc.esReglonAgrupador ? doc.classSemaforo : 'renglon');
				} else {
					aparienciaButton.descubrirButtonPlus(tabla, id, j);
				}
			}
		});
	},
	// oculta un button en la tabla segun por id
	'ocultarButtonPlusDocumento' : function(tabla, id) {
		$(tabla.fnGetData()).each(function(j, doc) {
			if (id == doc.idPantalla) {
				aparienciaButton.ocultarButtonPlus(tabla, id, j);
			}
		});
	},
	'ocultarButtonCreditosDebitosSeleccionados' : function(creditosDebitos) {
		for (var i = 0, size = creditosDebitos.length; i < size; i++) {
			var documento = creditosDebitos[i];
			if (documento.type == 'CobroDebitoDto') {
				aparienciaButton.ocultarButtonPlusDocumento(tablas.debitos, documento.idPantalla);
			} else if (documento.type == 'CobroCreditoDto') {
				aparienciaButton.ocultarButtonPlusDocumento(tablas.creditos, documento.idPantalla);
			} else {
				aparienciaButton.ocultarButtonPlusDocumento(tablas.documentosCaps, documento.idPantalla);
			}
		}
	},
	'ocultarButtonDocumentosSeleccionados' : function(tabla, discriminador) {
		if (discriminador == 'btnAgregarTodosDebitos') {
			aparienciaButton.ocultarButtonCreditoDebitoSeleccionados(tabla, tablas.debitosSeleccionados);
		} else if (discriminador == 'btnAgregarTodosCaps') {
			aparienciaButton.ocultarButtonCreditoDebitoSeleccionados(tabla, tablas.documentosCapsSeleccionados);
		} else {
			aparienciaButton.ocultarButtonCreditoDebitoSeleccionados(tabla, tablas.creditosSeleccionados);
		}
	}
};
function updateTrataDif(tratamientoDiferencia) {
	if (!$.isEmpty(tratamientoDiferencia) && tratamientoDiferencia.tipoTratamientoDiferencia != "" && tratamientoDiferencia.tipoTratamientoDiferencia != null) {

		var optionsCredito = $('#selectEnvio option');
		var optionsDebito = $('#selectEnvio1 option');
		var opcionSeleccionada = '';
		var valorSeleccionadoGuardado = tratamientoDiferencia.tipoTratamientoDiferencia;
		opcionSeleccionada = $.map(optionsCredito, function(option) {
			if (option.value == valorSeleccionadoGuardado) {
				return option.value;
			}
		});
		if ($.isEmpty(opcionSeleccionada)) {
			opcionSeleccionada = $.map(optionsDebito, function(option) {
				if (option.value == valorSeleccionadoGuardado) {
					return option.value;
				}
			});
			$('#selectEnvio1 option[value="' + opcionSeleccionada + '"]').prop("selected", "selected").change();
		} else {
			$('#selectEnvio option[value=\"' + opcionSeleccionada + '\"]').prop("selected", "selected").change();
		}

		$("#tramReintInput").val(tratamientoDiferencia.numeroTramiteReintegro);
		$("#fechaAltaTramReint").val(tratamientoDiferencia.fechaAltaTramiteReintegro);
		if (APLICACION_MANUAL === tratamientoDiferencia.tipoTratamientoDiferencia) {
			$("#sistemaAplicacionManual").val(tratamientoDiferencia.sistemaDestino).change();
			$("#referenciaInput").val(tratamientoDiferencia.referencia);
		} else {
			$("#selectSistDestino").val(tratamientoDiferencia.sistemaDestino).change();
		}

		$("#lineaInput").val(tratamientoDiferencia.linea);
		if (!$.isEmpty(tratamientoDiferencia.acuerdoFacturacion)) {
			$("#acuerdoFactInput").val(tratamientoDiferencia.acuerdoFacturacion);
		}
		$("#estadoAcuerdoFacturacion").val(tratamientoDiferencia.estadoAcuerdoFacturacion);
		$("#clienteAcuerdoFacturacion").val(tratamientoDiferencia.idClienteAcuerdoFacturacion);
	} else {
		flagBloqueDeEspera.fAutoCompletarCampoAcuerdo = true;
	}
}
function trataDifActulizaOpciones() {
	var tratamientoDiferencia = datosTablas.tratamientoDiferencia;
	
	
	if (!$.isEmpty(tratamientoDiferencia) && tratamientoDiferencia.tipoTratamientoDiferencia != "" && tratamientoDiferencia.tipoTratamientoDiferencia != null) {
		var optionsCredito = $('#selectEnvio option');
		var optionsDebito = $('#selectEnvio1 option');
		// var optionsDebito = $('#1 option');
		var opcionSeleccionada = '';
		var valorSeleccionadoGuardado = tratamientoDiferencia.tipoTratamientoDiferencia;
		if (optionsCredito.length) {
			opcionSeleccionada = $.map(optionsCredito, function(option) {
				if (option.value == valorSeleccionadoGuardado) {
					return 'credito';
				}
			});
		}
		if (opcionSeleccionada == '' && optionsDebito.length) {
			opcionSeleccionada = $.map(optionsDebito, function(option) {
				if (option.value == valorSeleccionadoGuardado) {
					return 'debito';
				}
			});
		}

		if (diferencia == "0,00") {
			datosTablas.tratamientoDiferencia = null;
			trataDifInputClear();
			trataDifInputHide();
			$('#selectEnvio1').val('');
			$('#selectEnvio').val('');
		} else if (diferencia > "0,00") {
			if (opcionSeleccionada == 'credito') {
				datosTablas.tratamientoDiferencia = null;
				trataDifInputClear();
				trataDifInputHide();
				$('#selectEnvio').val('');
				$('#selectEnvio1').val('');
			}
		} else if (diferencia < "0,00") {
			if (opcionSeleccionada == 'debito') {
				datosTablas.tratamientoDiferencia = null;
				trataDifInputClear();
				trataDifInputHide();
				$('#selectEnvio1').val('');
				$('#selectEnvio').val('');
			}
		}

		if (diferencia != "0,00" && tieneCompensacionNoSeleccionada(datosTablas.mediosPagos)) {
			datosTablas.tratamientoDiferencia = null;
			trataDifInputClear();
			trataDifInputHide();
			$('#selectEnvio1').val('');
			$('#selectEnvio').val('');
		}
	}
};
function trataDifInputHide() {
	$(".reintegroProxFactOpcLinea").hide();
	$(".reintegroProxFactOpcAcuerdo").hide();
	$("#wsConsultaJms").css('display', 'none');
	$("#wsConsultaJms2").css('display', 'none');
	$(".reintegroOpc").hide();
	$(".reintegroProxFactOpc").hide();
	$(".referenciaAplicacionManual").hide();

};
function trataDifInputClear() {
	$("#sistemaAplicacionManual").val("");
	$('#tramReintInput').val("");
	$('#fechaAltaTramReint').val("");
	$('#selectSistDestino').val("");
	$('#acuerdoFactInput').val("");
	$('#lineaInput').val("");
	$('#estadoAcuerdoFacturacion').val("");
	$('#referenciaInput').val("");
	$('#errorArchComprobanteAplManual').text("");
	$('#errorArchComprobanteAplManual').val("");
	$('#errorArchComprobanteAplManual').hide();
	$('#referenciaInput').val("");
};

function trataDifErrorClear() {
	$('#errorFechaAltaTramReint').text('');
	$('#errorFechaAltaTramReint').css('display', 'none');
	$('#errorTramReintInput').text('');
	$('#errorTramReintInput').css('display', 'none');
	$('#errorSistemaDestino').text("");
	$('#errorSistemaDestino').css('display', 'none');
	$('#wsConsultaJms').css('display', 'none');
	$('#wsConsultaJms2').css('display', 'none');
	$('#errorEnvio').css('display', 'none');
	$('#errorEnvio').text('');
};
function limpiarMarcaErrorTabPrev() {
	if (!tabPrevErrorInput.fechaAltaTramReint && !tabPrevErrorInput.tramReintInputRequerido) {
		$("span.error").text("");
	}
};
function limpiarMarcaErrorTabPrevisualizar() {
	if (!tabPrevErrorInput.fechaAltaTramReint && !tabPrevErrorInput.tramReintInputRequerido && !tabPrevErrorInput.acuerdo && !tabPrevErrorInput.linea && !tabPrevErrorInput.acuerdoAutocompletar) {
		$(CONF_COBRO_TAB_PREV).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
	}
}

function limpiarErroresTabPrevisualizar() {
	tabPrevErrorInput.fechaAltaTramReint = false;
	tabPrevErrorInput.fechaAltaTramReintRequerido = false;
	tabPrevErrorInput.tramReintInputRequerido = false;
	tabPrevErrorInput.linea = false;
	tabPrevErrorInput.acuerdo = false;
	tabPrevErrorInput.acuerdoAutocompletar = false;
	tabPrevErrorInput.selectEnvio = false;
};

var tabPrevErrorInput = {
	'fechaAltaTramReint' : false,
	'tramReintInput' : false,
	'linea' : false,
	'acuerdo' : false,
	'acuerdoAutocompletar' : false,
	'selectEnvio' : false,
	'limpiarErroresTabPrevisualizar' : function() {
		this.fechaAltaTramReint = false;
		this.tramReintInput = false;
		this.linea = false;
		this.acuerdo = false;
		this.acuerdoAutocompletar = false;
		this.selectEnvio = false;
	}
};
// Guido.Settecerze
var validarEdicionCobro = function() {
	var cobro = {
		idCobro : $('#idCobro').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarEdicionCobro",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			varEdicionSegunEstadoMarca = "";
			if (result.informacionMensaje == "edicionParcial") {
				habilitarEdicionParcial();
			} else if (result.informacionMensaje == "edicionTotalMenosDebitos") {
				$('.bloqueDebitos :input').attr('disabled', true);
				$('.bloqueDebitos :button').attr('disabled', true);
				$('#selectMotivo').attr('disabled', true);
				if (varEdicionSegunEstadoMarca != "edicionParcial") {
					varEdicionSegunEstadoMarca = "edicionTotalMenosDebitos";
				}
			} else if (result.informacionMensaje == "sinEdicion") {
				$('#conf-cobro-tabs :input').attr('disabled', true);
				$('#conf-cobro-tabs :button').attr('disabled', true);
				varEdicionSegunEstadoMarca = "sinEdicion";
			}
			$('.bloqueDescargar').attr('disabled', false);
			$('.busquedaResultado').attr('disabled', false);
			flagBloqueDeEspera.validarEdicionCobro = true;
		}
	});
};
function habilitarEdicionParcial() {
	$('#conf-cobro-tabs :input').attr('disabled', true);
	$('#conf-cobro-tabs :button').attr('disabled', true);
	$('#searchCriteraCliente').hide();
	$('#selectCopropietario').attr('disabled', false);
	if ($('#selectMotivo').val() === COMPENSACION || $('#selectMotivo').val() === COBRO_SALIDA_AUTOMATICA_VALORES || $('#selectMotivo').val() === OPERACION_MASIVA[0] || $('#selectMotivo').val() === OPERACION_MASIVA[1] || $('#selectMotivo').val() === OPERACION_MASIVA[2]) {
		$('#selectMotivo').attr('disabled', true);
	} else {
		$('#selectMotivo').attr('disabled', false);
	}
	$('#prevObservText').attr('disabled', false);
	varEdicionSegunEstadoMarca = "edicionParcial";
	$('#btnDescargarAdjuntoOtroDeb').attr('disabled', false);
};

// Guido.Settecerze
var validarEdicionCobroSegunUsuario = function() {
	var cobro = {
		idCobro : $('#idCobro').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/validarEdicionCobroSegunUsuario",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				$('#conf-cobro-tabs :input').attr('disabled', true);
				$('#conf-cobro-tabs :button').attr('disabled', true);
				$('#searchCriteraCliente').hide();
				$('#selectCopropietario').attr('disabled', false);
				$('#selectMotivo').attr('disabled', false);
				$('#prevObservText').attr('disabled', false);

				varEdicionSegunEstadoMarca = "edicionParcial";
			}
			flagBloqueDeEspera.validarEdicionCobroSegunUsuario = true;
		}
	});
};

var buscarCobro = function(id) {

	// $('#bloqueEspera').trigger('click');
	var cobro = {
		idCobro : id,
		vengoReedicion : $('#vengoReedicion').val(),
		idTarea : $('#idTarea').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/buscarConfCobro",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				if (!vengoReedicion()) {
					$('#idCobro').val(result.cobro.idCobro);	
					actualizarEstadoCobroVista(result.cobro.descripcionEstado, result.cobro.descripcionMarca);
				
					// TODO Maxi: hay que revisar para que se usa. Desde
					// buscarConfCobro viene el dato idCobroPadre
					
					
					$('#estado').val(result.cobro.estado);

					validarEdicionCobro();
					validarEdicionCobroSegunUsuario();

					$('#idOperacion').val(result.cobro.idOperacion);
				} else {
					cambiarModoEdicion('sinEdicion', false);
					// Desahabilito todos los botones
					$('.btn').attr('disabled', true);
					$('button[id="btnGuardar"]').text('Reeditar').attr('disabled', false);
					$('button[id="btnVolver"]').attr('disabled', false);
					$('#idCobro').val("");
				}
				$('#idCobroPadre').val(result.cobro.idCobroPadre);
				$('#selectEmpresa').val(result.cobro.idEmpresa);
				$('#selectSegmento').val(result.cobro.idSegmento);
				$('#selectMonedaOperacion').val(returnSignoMoneda(result.cobro.monedaOperacion));

				_monedaDelCobro = returnSignoMoneda(result.cobro.monedaOperacion);
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');

				$('#idAnalista').val(result.cobro.idAnalista);
				$('#analista').val(result.cobro.nombreAnalista);
				$('#prevAnalista').val(result.cobro.nombreAnalista);
				/*
				 * Cargo la lista de copropietarios posibles y selecciono el que
				 * tiene cargado el cobro encontrado
				 */
				var options = [ {
					text : 'Seleccione un item...',
					value : ''
				} ];
				$.each(result.listaCopropietarios, function(index, option) {
					options.push(option);
				});
				$("#selectCopropietario").replaceOptions(options);

				$('#selectCopropietario').val(result.cobro.idCopropietario);

				$('#selectMotivo').val(result.cobro.idMotivoCobro);

				// Filtro los medios de pago que difieren de
				// compensaciones, cuando el motivo es Compensacion
				drawOtrosMedioPagoSegunMotivoCobro($('#selectMotivo').val());

				if (!$.isEmpty(result.cobro.observacionAnterior)) {
					$('#prevObservTextAnterior').val(result.cobro.observacionAnterior);
					$('#prevObservacionesAnterior').show();
				} else {
					$('#prevObservacionesAnterior').hide();
				}
				if (!$.isEmpty(result.cobro.observacion)) {
					$('#prevObservText').val(result.cobro.observacion);
				}
				if (!$.isEmpty(result.cobro.observacionAnterior)) {
					$('#prevObservTextAnterior').val(result.cobro.observacionAnterior);
				}
				/* Lista de Clientes */
				$.each(result.cobro.clientes, function(j, seleccionado) {
					seleccionado.idClienteLegado = completarCerosADerecha(seleccionado.idClienteLegado);
					tablas.clientesSeleccionados.fnAddData(seleccionado, true);
					datosTablas.clientesSeleccionados.push(seleccionado);
				});

				$("#divLoader").css({
					height : ($.getDocHeight()) + 'px'
				});
				/* Lista de Debitos */
				$.each(result.cobro.debitos, function(j, seleccionado) {
					detalleDebitosDelCobro.addDocumento(seleccionado.tipoComprobanteEnum);
					datosTablas.debitosSeleccionados.push(seleccionado);

					if (!$.isEmpty(seleccionado.resultadoValidacionDescripcionError)) {
						contadorMensajesErrorGuardableDebitos.sumarErrorGuardableByDescripcion(seleccionado.resultadoValidacionDescripcionError);
					}
				});
				/* Lista de Creditos */
				$.each(result.cobro.creditos, function(j, seleccionado) {
					datosTablas.creditosSeleccionados.push(seleccionado);

					if (!$.isEmpty(seleccionado.resultadoValidacionDescripcionError)) {
						contadorMensajesErrorGuardableCreditos.sumarErrorGuardableByDescripcion(seleccionado.resultadoValidacionDescripcionError);
					}
				});
				/* Lista de Medios de Pago */
				$.each(result.cobro.medios, function(j, seleccionado) {
					datosTablas.mediosPagos.push(seleccionado);
					// TODO para sprint 5?
					// modificaciones de documentos caps
					if (seleccionado.listaDocumentoCap != null) {
						// datosTablas.documentosCapsSeleccionados
						// =
						// seleccionado.listaDocumentoCap.slice();
						var even = true;
						for (var i = 0, size = seleccionado.listaDocumentoCap.length; i < size; i++) {
							if (seleccionado.listaDocumentoCap[i].esReglonAgrupador) {
								even = !even;
							}
							seleccionado.listaDocumentoCap[i].even = even;
							datosTablas.documentosCapsSeleccionados.push(seleccionado.listaDocumentoCap[i]);
						}
					}
				});
				/* Lista de Comprobantes */
				// datosTablas.comprobantes =
				// result.cobro.listaComprobantes.slice()
				$.each(result.cobro.listaComprobantes, function(j, seleccionado) {
					datosTablas.comprobantes.push(seleccionado);
				});

				// CODIGO OPERACION EXTERNA
				$.each(result.cobro.listaCodigoOperacionesExternas, function(j, seleccionado) {
					datosTablas.codigoOperacionesExternas.push(seleccionado);
				});

				$.each(result.cobro.listaComprobanteAplicacionManual, function(j, seleccionado) {
					datosTablas.comprobantesAplManual.push(seleccionado);
				});
				// Otros debitos
				$.each(result.cobro.listaComprobanteOtrosDebito, function(j, seleccionado) {
					if(seleccionado.descripcionArchivo === 'Archivo Importacion de Otros Debitos.') {
						datosTablas.comprobantes.push(seleccionado);
					}
					datosTablas.comprobantesOtrosDebito.push(seleccionado);
				});
				/* Lista de otrosDebitpos */
				$.each(result.cobro.otrosDebitos, function(j, seleccionado) {
					datosTablas.otrosDebitosSeleccionados.push(seleccionado);

					if (!$.isEmpty(seleccionado.resultadoValidacionDescripcionError)) {
						contadorMensajesErrorGuardableCreditos.sumarErrorGuardableByDescripcion(seleccionado.resultadoValidacionDescripcionError);
					}
				});
				totalDeDocumentos.actualizar();
				datosTablas.tratamientoDiferencia = result.cobro.tratamientoDiferencia;

				anularGuardar = true;
				updateTrataDif(datosTablas.tratamientoDiferencia);
				flagBloqueDeEspera.buscarConfCobro = true;
				anularGuardar = false;

				validarSolapaDebitosCreditos();
				grisarSolapaCreditosSegunMotivo($('#selectMotivo').val());
				actualizarControlesPorCambioDeMotivo($('#selectMotivo').val());

				fechaCotizPrev = result.cobro.fechaTipoCambio;
				$('#fechaTipoCambio').val(result.cobro.fechaTipoCambio);
				$('#tipoDeCambio').val(result.cobro.tipoCambio);
				if (!$.isEmpty($('#tipoDeCambio').val()) && ($('#tipoDeCambio').val().indexOf('\n') !== -1)) {
					$('#tipoDeCambio').css('height', '40px');
				}
				mostrarBtnGenerarDocumentacionCompensaciones($('#selectMotivo').val(), $('#estado').val(), datosTablas.mediosPagos);
				//
				if (datosTablas.comprobantesAplManual.length > 0) {
					// tablas.comprobantesAplManual.fnAddData(
					// datosTablas.comprobantesAplManual, true);
					// tablas.comprobantesAplManual.fnAdjustColumnSizing(false);
					$('#btnAdjComprobanteAplManual').prop('disabled', true);
				} else {
					// tablas.comprobantesAplManual.fnAdjustColumnSizing(false);
					$('#btnAdjComprobanteAplManual').prop('disabled', false);
				}
				if (datosTablas.comprobantesOtrosDebito.length > 0) {
					$('#btnAdjComprobanteOtrosDeb').prop('disabled', true);
				} else {
					$('#btnAdjComprobanteOtrosDeb').prop('disabled', false);
				}
				
				//
				if (vengoReedicion()) {
					flagBloqueDeEspera.clear();
				}
			}
			// TODO PARA PAU
			// ocultarBloqueEspera();
		}
	});
};

var acuInteresesTransacciones = {
	'totalTraslados$' : 0,
	'totalBonificados$' : 0,
	'totalReintegro$' : 0,
	'totalTrasladosU$S' : 0,
	'totalBonificadosU$S' : 0,
	'totalReintegroU$S' : 0,
	'inicializar' : function() {
		this.totalTraslados$ = 0;
		this.totalBonificados$ = 0;
		this.totalReintegro$ = 0;
		this.totalTrasladosU$S = 0;
		this.totalBonificadosU$S = 0;
		this.totalReintegroU$S = 0;
	},

	'totalTraslados' : function(moneda, intereses, importeABonificar) {
		if (importeABonificar === undefined) {
			this['totalTraslados' + moneda] += importeToFloat(intereses);
		} else {
			this['totalTraslados' + moneda] += (importeToFloat(intereses) - importeToFloat(importeABonificar));
		}
	},
	'totalBonificados' : function(moneda, importeABonificar) {
		this['totalBonificados' + moneda] += importeToFloat(importeABonificar);
	},
	'totalReintegro' : function(moneda, intereses) {
		this['totalReintegro' + moneda] += importeToFloat(intereses);
	},
};
// u573005, objeto para totalizar el importe de intereses
var totalInteresesTransacciones = {
	'actualizar' : function() {
		acuInteresesTransacciones.inicializar();
		var documento = datosTablas.transacciones;
		var campo = 'importeABonificar';
		var size = documento.length;

		for (var i = 0; i < size; i++) {
			var importeABonificar = documento[i][campo];

			if ("-" !== importeABonificar && "" !== importeABonificar) {
				var tipoDocumento = documento[i]['tipoDocumento'];
				var subtipoDocumento = documento[i]['subtipoDocumento'];
				var valorCheck = documento[i]['trasladarIntereses'];
				var moneda = '-';
				if (documento[i].sistemaDoc !== '-') {
					moneda = documento[i]['moneda'];
				} else {
					moneda = documento[i]['monedaMedioPago'];
				}
				var intereses = documento[i]['intereses'].replace(moneda, "");

				if (REINTEGRO_CRED_PROX_FAC_SUBTIPO === subtipoDocumento) {
					// totalReintegro += importeToFloat(intereses);
					acuInteresesTransacciones.totalReintegro(moneda, intereses);
				} else if (true == valorCheck) {
					// totalTraslados += importeToFloat(intereses);
					acuInteresesTransacciones.totalTraslados(moneda, intereses);
				} else {
					if (REINTEGRO_POR_CHEQUE_SUBTIPO !== subtipoDocumento && REINTEGRO_PAGO_CUENTA_TERCEROS_SUBTIPO !== subtipoDocumento && REINTEGRO_CREDITO_RED_INTEL_SUBTIPO !== subtipoDocumento && REINTEGRO_TRANSFERENCIA_BAN_SUBTIPO !== subtipoDocumento && ENVIO_A_GANANCIAS_DESC !== tipoDocumento && SALDO_A_PAGAR_DESC !== tipoDocumento && SALDO_A_COBRAR_DESC !== tipoDocumento) {
						// totalBonificados +=
						// importeToFloat(importeABonificar);
						acuInteresesTransacciones.totalBonificados(moneda, importeABonificar);
						// actualizo tambien el total de traslados con lo
						// restante
						// totalTraslados += (importeToFloat(intereses) -
						// importeToFloat(importeABonificar));
						acuInteresesTransacciones.totalTraslados(moneda, intereses, importeABonificar);
					}
				}
			}
		}

		$('#prevSumInteresesTraslados').val(MONEDA_PESOS_SIGNO_2 + formatear(acuInteresesTransacciones['totalTraslados' + MONEDA_PESOS_SIGNO_2]));
		$('#prevSumInteresesBonificados').val(MONEDA_PESOS_SIGNO_2 + formatear(acuInteresesTransacciones['totalBonificados' + MONEDA_PESOS_SIGNO_2]));
		$('#prevSumInteresesReintegro').val(MONEDA_PESOS_SIGNO_2 + formatear(acuInteresesTransacciones['totalReintegro' + MONEDA_PESOS_SIGNO_2]));
		$('#prevSumInteresesTrasladosDolares').val(MONEDA_DOLAR_SIGNO_2 + formatear(acuInteresesTransacciones['totalTraslados' + MONEDA_DOLAR_SIGNO_2]));
		$('#prevSumInteresesBonificadosDolares').val(MONEDA_DOLAR_SIGNO_2 + formatear(acuInteresesTransacciones['totalBonificados' + MONEDA_DOLAR_SIGNO_2]));
		$('#prevSumInteresesReintegroDolares').val(MONEDA_DOLAR_SIGNO_2 + formatear(acuInteresesTransacciones['totalReintegro' + MONEDA_DOLAR_SIGNO_2]));
	}
};

// u573005, sprint 5
// dentro de esta validacion contemplo si la grilla se tiene que ver en solo
// lectura o no
function validarHabilitacionCampo(registro) {
	// se formatean los intereses en cualquier tipo de moneda que venga
	// var interesesFormateados = registro.intereses.replace(((registro.moneda
	// !== '-') ? registro.moneda : registro.monedaMedioPago), "");
	var interesesFormateados = registro.intereses;
	var interesesFloat = importeToFloat(interesesFormateados);
	var colorLetraRegistro = registro.colorLetraRegistro;
	var soloLectura = registro.soloLecturaPantalla;

	var salida = TRASLADA_INTERESES_NO;

	if (soloLectura) {
		salida = TRASLADA_INTERESES_LECTURA;
	} else {

		if (registro.generaInteresesParamUso) {
			if (REINTEGRO_CRED_PROX_FAC_SUBTIPO === registro.subtipoDocumento || DEBITO_PROX_FAC_DESC === registro.tipoMedioPago) {
//				// es reintegro proxima factura
				if (interesesFloat > 0) {
					salida = 1;
				} else {
					TRASLADA_INTERESES_DIFERENCIA = 3;
				}
			} else if (interesesFloat > 0) {
				if (colorLetraRegistro === "0") {
					// interes mayor a cero y no es diferencia de cambio
					salida = TRASLADA_INTERESES;
					if (registro.mostrarAsteriscos == 'SI') {
						salida = TRASLADA_ASTERISCOS;
					}
				} else {
					// interes mayor a cero y moneda en dolares, o sea es el
					// registro informativo de calipso
					salida = TRASLADA_INTERESES_DIFERENCIA;
				}
			} else {
				salida = TRASLADA_INTERESES_NO;
				if (registro.mostrarAsteriscos == 'SI') {
					salida = TRASLADA_ASTERISCOS;
				}
			}
		} else if (registro.esTrasladarInteresesObligatorio){
			salida = TRASLADA_INTERESES;
			registro.porcABonificar = '0';
//			registro.importeABonificar = '';
		}
	}
	return salida;
}

// U572497 Guido.Settecerze
function exportarDetalle() {
	flagBloqueDeEspera.inicializar([ 'fExportacionDetalleCobro', 'fGuardarConfCobro', 'fHabilitarBtnSimular', 'modificarTransaccionesConIntereses', 'habilitarBtnImputarYCargarGrillaTransacciones' ]);
	$('#btnExportar').attr('disabled', true);

	if (COB_ERROR_COBRO_NAME == $('#estado').val() || COB_ERROR_APROPIACION_NAME == $('#estado').val()) {
		beforeUnload.off();
		exportarDetalleAExcel();
		setTimeout(function() {
			beforeUnload.on();
			flagBloqueDeEspera.clear();
		}, 5000);

	} else {
		flagBloqueDeEspera.fExportacionDetalleCobro = true;
		if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
			$('#exportarExcel').val("ExportarModificarTransacciones");
		} else {
			$('#exportarExcel').val("true");
		}

		if (validarDatosCobro()) {
			setTimeout(function() {
				// En esta evento no utilizo el ajax de simular
				guardarCobro();

				// esta funcion valdia si se debe terminar el bloque de espera
				finBloqueDeEspera();
			}, 1300);
		} else {
			setTimeout(function() {
				flagBloqueDeEspera.terminar();
				ocultarBloqueEspera();
			}, 500);
		}
	}

	setTimeout(function() {
		$('#btnExportar').attr('disabled', false);
		finBloqueDeEspera();
	}, 5000);
};

function historialCobro(idCobro) {
	$('#bloqueEspera').trigger('click');
	if (COB_ERROR_COBRO_NAME == $('#estado').val() || COB_ERROR_APROPIACION_NAME == $('#estado').val()) {
		beforeUnload.off();
		$('#idOperacionFormateado').val($('#idCobro').val());
		$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobro-historial";
		$('#formCobro')[0].submit();
	} else {
		verHistorial = true;
		guardarCobro();
	}
};

function volverBusqueda() {
	$('#goBack').val("true");
	$('#formCobro')[0].action = urlAsynchronousHTTP + $('#idVolver').val();
	$('#formCobro')[0].submit();
};

var selectedRow = {
		'debitos' : '',
		'debitosCambioPag' : false, // Debo tener un flag que indique si se cambio
		// la pagina
		'creditos' : '',
		'creditosCambioPag' : false,
		'transacciones' : '',
		'transaccionesTdBg' : '',
		'transaccionesTdBgFirst' : '',
		'transaccionesIndex' : '',
		'transaccionesCambioPag' : false,
		'capsSele' : '',
		'clearVariables' : function(tipo) {
			this[tipo] = '';
			this[tipo + 'CambioPag'] = false;
			if ('transacciones' === tipo) {
				this[tipo + 'TdBg'] = '';
				this[tipo + 'Index'] = '';
				this[tipo + 'TdBgFirst'] = '';
			}
		},
		'withFixedColumn' : function(td, tipo) {
			var idButton = $(td).find('button:eq(0)').attr('id');
			this[tipo + 'CambioPag'] = false;
			// Si tiene idButton quiere decir que pulse sobre el td con botton ('-')
			if (idButton === undefined || idButton == null || idButton == '') {
				// Como las tablas tiene un solo button
				var idButton = $(td).closest('tr').find('td button:eq(0)').attr('id');
				var tableTr = $('table tr' + ' #' + idButton).closest('tr');
				if ($(tableTr).hasClass('selectedCustom')) {
					var input = $('input:hover');
					if (input.length == 0) {
						$(tableTr[1]).removeClass('selectedCustom');
						$(tableTr[0]).removeClass('selectedCustom');
						this[tipo] = '';
					} else if (input[0].disabled) {
						$(tableTr[1]).removeClass('selectedCustom');
						$(tableTr[0]).removeClass('selectedCustom');
						this[tipo] = '';
					}
				} else {
					$('table tr.selectedCustom').removeClass('selectedCustom');
					$(tableTr[1]).addClass('selectedCustom');
					$(tableTr[0]).addClass('selectedCustom');
					this[tipo] = idButton;
				}
			}
		},
		'withoutFixedColumn' : function(td, tipo, tableName) {
			var tableTr = $(td).closest('tr');
			this[tipo + 'CambioPag'] = false;
			if ($(tableTr).hasClass('selectedCustom')) {
				var input = $('input:hover');
				if (input.length == 0) {
					$(tableTr).removeClass('selectedCustom');
					//if (this[tipo + 'TdBg'] == BACKGROUND_COLOR_GREY) {
						$(tableTr).find('td').css('background-color', this[tipo + 'TdBg']);
						$(tableTr).find('td:first').css('background-color', this[tipo + 'TdBgFirst']);
					//}
					this[tipo] = '';
					this[tipo + 'TbBg'] = '';
					this[tipo + 'TdBgFirst'] = '';
				} else if (input[0].disabled) {
					$(tableTr).removeClass('selectedCustom');
					//if (this[tipo + 'TdBg'] == BACKGROUND_COLOR_GREY) {
						$(tableTr).find('td').css('background-color', this[tipo + 'TdBg']);
						$(tableTr).find('td:first').css('background-color', this[tipo + 'TdBgFirst']);
					//}
					this[tipo] = '';
					this[tipo + 'TbBg'] = '';
					this[tipo + 'TdBgFirst'] = '';
				}
			} else {
				$('table tr.selectedCustom').removeClass('selectedCustom');
				if (!$.isEmpty(this[tipo + 'TdBg']) && !$.isEmpty(this[tipo])) {
					var trOld = $('#' + tableName).find('tr:has(td:contains("' + this[tipo] + '"))');
					if (!$.isEmpty(trOld)) {
						$(trOld).find('td').css('background-color', this[tipo + 'TdBg']);
						$(trOld).find('td:first').css('background-color', this[tipo + 'TdBgFirst']);
					}
				}
				this[tipo + 'TdBg'] = $(tableTr).find('td:eq(1)').css('background-color');
				this[tipo + 'TdBgFirst'] = $(tableTr).find('td:first').css('background-color');
				//if (this[tipo + 'TdBg'] == BACKGROUND_COLOR_GREY) {
					$(tableTr).find('td').css('background-color', "");
				//}
				this[tipo + 'Index'] = $('#' + tableName).DataTable().row(tableTr).index();
				$(tableTr).addClass('selectedCustom');
				this[tipo] = $(tableTr).find('td:eq(1)').text();
			}
		},
		'markRowSelected' : function(tipo, isFixedColumn, tableName, isChangeTab) {
			if (this[tipo + 'CambioPag'] || isChangeTab) {
				if (this[tipo + 'CambioPag']) {
					$('table tr.selectedCustom').removeClass('selectedCustom');
				}
				selectedRow.debitosCambioPag = false;
				if (!$.isEmpty(this[tipo])) {
					if (isFixedColumn) {
						var tableTr = $('table tr' + ' #' + this[tipo]).closest('tr');
						if (!$.isEmpty(tableTr)) {
							$('table tr.selectedCustom').removeClass('selectedCustom');
							$(tableTr[1]).addClass('selectedCustom');
							$(tableTr[0]).addClass('selectedCustom');
						} else {
							this.clearVariables(tipo);
						}
					} else {
						// var tableTr = $('#' +
						// tableName).find('tr:has(td:contains("' + this[tipo] +
						// '"))');
						var tableTr = $('#' + tableName).dataTable().fnGetNodes(this[tipo + 'Index']);
						if (!$.isEmpty(tableTr)) {
							$('#' + tableName + ' tr.selectedCustom').removeClass('selectedCustom');
							
							if ('transacciones' === tipo) {
								this[tipo + 'TdBg'] = $(tableTr).find('td:eq(1)').css('background-color');
								this[tipo + 'TdBgFirst'] = $(tableTr).find('td:first').css('background-color');
							} else {
								this[tipo + 'TdBg'] = $(tableTr).find('td').css('background-color');
							}
							//if (this[tipo + 'TdBg'] == BACKGROUND_COLOR_GREY) {
								$(tableTr).find('td').css('background-color', "");
							//}
							$(tableTr).addClass('selectedCustom');
						} else {
							this.clearVariables(tipo);
						}
					}
				}
			}
		},
		'withFixedColumnCaps' : function(td, tipo) {
			var idButton = $(td).find('button:eq(0)').attr('id');
			this[tipo + 'CambioPag'] = false;
			// Si tiene idButton quiere decir que pulse sobre el td con botton ('-')
			if (idButton === undefined || idButton == null || idButton == '') {
				// Como las tablas tiene un solo button
				var idButton = $(td).closest('tr').find('td button:eq(0)').attr('id');
				var tableTr = [];
				if (!$.isEmpty(idButton)) {
					tableTr = $('table tr' + ' #' + idButton).closest('tr');
					if ($(tableTr).hasClass('selectedCustom')) {
						var input = $('input:hover');
						if (input.length == 0) {
							$(tableTr[1]).removeClass('selectedCustom');
							$(tableTr[0]).removeClass('selectedCustom');
							this[tipo] = '';
						} else if (input[0].disabled) {
							$(tableTr[1]).removeClass('selectedCustom');
							$(tableTr[0]).removeClass('selectedCustom');
							this[tipo] = '';
						}
					} else {
						$('table tr.selectedCustom').removeClass('selectedCustom');
						$(tableTr[1]).addClass('selectedCustom');
						$(tableTr[0]).addClass('selectedCustom');
						this[tipo] = idButton;
					}
				} else {
					idButton = $(td).closest('tr').find('td i:eq(0)').attr('id');
					tableTr = $('table tr' + ' #' + idButton).closest('tr');
					if ($(tableTr).hasClass('selectedCustom')) {
						var input = $('input:hover');
						if (input.length == 0) {
							$(tableTr[1]).removeClass('selectedCustom');
							$(tableTr[0]).removeClass('selectedCustom');
							this[tipo] = '';
						} else if (input[0].disabled) {
							$(tableTr[1]).removeClass('selectedCustom');
							$(tableTr[0]).removeClass('selectedCustom');
							this[tipo] = '';
						}
					} else {
						$('table tr.selectedCustom').removeClass('selectedCustom');
						// $(tableTr[1]).addClass('selectedCustom');
						$(tableTr[0]).addClass('selectedCustom');
						this[tipo] = idButton;
					}
				}

			}
		},
	};
var BACKGROUND_COLOR_WHITE = "rgba(0, 0, 0, 0)";
var BACKGROUND_COLOR_GREY = "rgb(242, 242, 247)";

function verificarCambiInputTraDif() {
	if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
		guardarTratamientoDiferenciaYHabilitarBtnImputar();
	} else {
		flagBloqueDeEspera.fValidarSimulacion = true;
	}
}
function verificarCambiInputTraDifBqEspera() {
	if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
		flagBloqueDeEspera.inicializar([ 'fValidarSimulacion' ]);
		guardarTratamientoDiferenciaYHabilitarBtnImputar();
		finBloqueDeEspera();
	}
}

var validarDatosCobro = function() {
	var tabClientesOk = true;
	var prevObservText = $('#prevObservText').val();
	var index = $('#conf-cobro-tabs .current').index();

	prevObservText = $.trim(prevObservText);

	if (!($.validacionCaracteresEspeciales(prevObservText))) {
		$("#errorObservaciones").text("Este campo debe contener un formato de texto correcto.");
		tabClientesOk = false;
	}
	if ($.isEmpty($('#selectEmpresa').val())) {
		$("#errorEmpresa").text("Este campo es requerido.");
		tabClientesOk = false;
	}
	if ($.isEmpty($('#selectSegmento').val())) {
		$("#errorSegmento").text("Este campo es requerido.");
		tabClientesOk = false;
	}
	if ($.isEmpty($('#selectMotivo').val())) {
		$("#errorMotivo").text("Este campo es requerido.");
		tabClientesOk = false;
	}

	if ($('#selectMotivo').val() === COMPENSACION) {
		if (!validarFechaTipoCambio()) {
			tabClientesOk = false;
		}
		if ($.isEmpty($('#tipoDeCambio').val())) {
			tabClientesOk = false;
			$("#errorTipoDeCambio").text("Este campo es requerido.");
		}
	}
	var vTratamientoDif = validarTratamientoDiferenciaInpurOp();

	if (tabClientesOk & vTratamientoDif & !contadorMensajesError.isError()) {
		$("#alertErrorGuardado").hide();
		limpiarErrores();

		validarSolapaDebitosCreditos();

		return true;
	} else {
		// btnSimularClicked es una variable globar que se setea en true cuando
		// se realizar un click en el btnSimular
		if (contadorMensajesError.isError() && btnSimularClicked && index == INDICE_TAB_OTROS_DEBITOS) {
			// En la simulacion ignoro los errores de la grilla de transacciones
			return true;
		}
		if (!(!vTratamientoDif && btnSimularClicked)) {
			$("#errorGuardado").text("Error al guardar. Revise los errores.");
			$("#alertErrorGuardado").show();
			$('#conf-cobro-tabs-t-' + index).css({
				"color" : "red",
				"font-weight" : "bold"
			});
		} else {
			validarSolapaDebitosCreditos();
		}
		return false;
	}
};

var validarSolapaDebitosCreditos = function() {

	if (!contadorMensajesErrorGuardableDebitos.isError()) {
		$("#errorGuardado").text("");
		$("#alertErrorGuardado").hide();
		$(CONF_COBRO_TAB_DEB).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
	} else {
		$("#errorGuardado").text("Revise los errores.");
		$("#alertErrorGuardado").show();
		$(CONF_COBRO_TAB_DEB).css({
			"color" : "red",
			"font-weight" : "bold"
		});
	}

	if (!contadorMensajesErrorGuardableCreditos.isError() && !contadorMensajesErrorGuardableDebitos.isError()) {
		$("#errorGuardado").text("");
		$("#alertErrorGuardado").hide();
		$(CONF_COBRO_TAB_CRED).css({
			"color" : "#5f5f5f",
			"font-weight" : "normal"
		});
		grisarSolapaCreditosSegunMotivo($('#selectMotivo').val());
	} else if (contadorMensajesErrorGuardableCreditos.isError()) {
		$("#errorGuardado").text("Revise los errores.");
		$("#alertErrorGuardado").show();
		$(CONF_COBRO_TAB_CRED).css({
			"color" : "red",
			"font-weight" : "bold"
		});
	}

	// devuelvo true porque siempre dejo guardar
	return true;
};

function buscarCuentaContable() {
	$('#bloqueEspera').trigger('click');

	var cobro = {
		clientes : datosTablas.clientesSeleccionados
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/buscarCuentaContablePorDefault",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				$("#referenciaInput").val(result.cuentaContable);
				$("#referenciaInput").change();
			}
		}
	});

};

function verificarAutoCompletarCampoAcuerdo() {
	var cobro = {
		clientes : datosTablas.clientesSeleccionados
	};
	$.ajax({
		"type" : "POST",
		"url" : "configuracion-cobro/autoCompletarCampoAcuerdo",
		"dataType" : "json",
		"data" : JSON.stringify(eval(cobro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			var nroAcuerdo = result.nroAcuerdo;
			tabPrevErrorInput.acuerdoAutocompletar = false;
			if (!$.isEmpty(nroAcuerdo)) {
				if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
					$("#wsConsultaJms2").css('display', 'inline-block');
					$("#wsConsultaJms2").text('Este campo es requerido.');
					$(CONF_COBRO_TAB_PREV).css({
						"color" : "red",
						"font-weight" : "bold"
					});
					$('#btnSimular').prop('disabled', true);
					estadoButtons.btnSimular.errorInput = true;
					// tabPrevErrorInput.acuerdoAutocompletar = true;
				}
			} else {
				if ($('#conf-cobro-tabs .current').index() == INDICE_TAB_PREV) {
					$("#wsConsultaJms2").css('display', 'inline-block');
					$("#wsConsultaJms2").text(result.descripcionError);
					$(CONF_COBRO_TAB_PREV).css({
						"color" : "red",
						"font-weight" : "bold"
					});
					$('#btnSimular').prop('disabled', true);
					estadoButtons.btnSimular.errorInput = true;
					// tabPrevErrorInput.acuerdoAutocompletar = true;
				}
			}
			flagBloqueDeEspera.terminar();
			ocultarBloqueEspera();
		}
	});
};


function vengoReedicion() {
	return !$.isEmpty($('#vengoReedicion').val()) && 'true' == $('#vengoReedicion').val();
}
