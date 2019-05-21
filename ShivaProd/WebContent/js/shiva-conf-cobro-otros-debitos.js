//*************************************************************************************************************//
//TABLAS																									   //
//*************************************************************************************************************//

/**
 * Fernando Nicolás Quispe U591368
 */
var grillaClientesOtrosDebitos = {
	dom : '<"botonExcelClientesMedios">Bfrtip',
	"sScrollY" : SCROLL_Y,
	"sScrollX" : "100%",
	"sScrollXInner" : "110%",
	"bScrollCollapse" : true,
	"iDisplayLength" : DISPLAY_LENGTH,
	"iDisplayStart" : 0,
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
				return '<input type="radio" id="idRadioCliente' + eliminaCerosADerecha(data.idClienteLegado) + '" name="radioCliente" class="mpClienteSel opcionCliente editor-active" onclick="cargarInputCliente(' + '\'' + data.idClienteLegado + '\'' + ') "> ';
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
	},
	'columnDefs' : [ {
		width : WiDTH_CHECK,
		targets : 0
	}, {
		type : 'string-numero',
		targets : 'numeroOrder'
	} ]
};

var grillaOtrosDebitos = {
	dom : '<"eliminarTodosOtrosDebitos">Bfrtip',
	"sScrollY" : SCROLL_Y,
	"sScrollX" : "100%",
	"sScrollXInner" : "110%",
	"bScrollCollapse" : true,
	"order" : [ [ 3, "asc" ] ],
	buttons : [ {
		extend : "excelHtml5"
	} ],
	"fnDrawCallback" : function(oSettings) {
		$('#btnEliminarTodosOtrosDebitos').attr('disabled', !(oSettings.aoData.length > 1));
		selectedRow.markRowSelected('otrosDebSeleccionados', true, 'otrosDebitosSeleccionados', false);
	},
	"aoColumns" : [ { // 0
		"targets" : -1,
		"data" : null,
		"defaultContent" : '',
		"render" : function(data, type, row) {
			if (type === 'display') {
				return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnEditarOtroDeb" idOtroDebVar="' + data.idOtrosDebitoPantalla + '" class="btn btn-xs btn-link" type="button" title="Editar"> <i class="icon-edit bigger-120"></i></button><a> </a>	<button id="btnEliminarOtroDeb" class="btn btn-xs btn-link" type="button" title="Eliminar de la lista" idOtroDebVar="' + data.idOtrosDebitoPantalla + '"><i class="icon-trash bigger-120"></i></button></div>';
			}
			return data;
		},
		"searchable" : false,
		"bSortable" : false
	}, {
		"mData" : "sociedad",// 1
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "sistema", // 2
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "tipoDebito", // 3
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "referenciaPago", // 4
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "cliente", // 5
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "numeroDocumento",
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "fechaVencimiento", // 7
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "moneda", // 8
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "tipoCambio", // 9
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		"mData" : "importe", // <---------
		// Editable
		// input
		// text
		// 10
		"render" : function(data, type, row) {
			if (type === 'display') {
				// var disabled = (row.sistemaOrigen === 'SHIVA' &&
				// row.tipoFacturaShiva === RETENCION_NAME && row.moneda ===
				// '$');

				return '<div class="controls">' + returnSignoMoneda(_monedaDelCobro) + '<input id="inputImporteOtrosDeb" onkeypress="return validarInputNumericosComaPunto(event)" maxlength="14" type="text" value="' + (validarImporteValido(data) ? formatear(importeToFloat(data)) : '0') + '" class="input" style="margin: 0 auto; width: 110px; text-align: right" ' + ' onfocusout="return validarImporteOtrosDeb(this,tablas.otrosDebSeleccionados, datosTablas.otrosDebitosSeleccionados, \'importe\',true)"><div></div></div>';

			}
			return data;
		},
		"className" : "dt-body-center",
		"searchable" : false,
		"bSortable" : false
	}, {
		"mData" : "importeMonedaOrigen", // 11
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}

			return MONEDA_DOLAR_SIGNO_2 + data;
		}
	}, {
		// 12
		"mData" : "sinDiferenciaDeCambio",
		"render" : function(data, type, row) {
			if (type === 'display') {
				var showCheck = (row.moneda !== _monedaDelCobro && $('#selectMotivo').val() == COMPENSACION);

				return (showCheck) ? '<input type="checkbox" id="sinDiferenciaDeCambio" name="sinDiferenciaDeCambio" onclick="otrosDebitoCheck(this, \'sinDiferenciaDeCambio\')" ' + ((data == 'S') ? 'checked ' : '') + '>' : '<input type="checkbox" disabled>';
			}
			return data;
		}
	}, {
		"mData" : "nombreAdjunto", // 13
		"render" : function(data) {
			if ($.isEmpty(data)) {
				data = '-';
				return data;
			}
			return data;
		}
	}, {
		// 14
		"targets" : -1,
		"data" : null,
		"defaultContent" : '',
		"render" : function(data, type, row) {
			if (type === 'display') {
				if (!$.isEmpty(row.idAdjunto)) {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" idOtroDebVar="' + data.idOtrosDebitoPantalla + '" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div>';
				}

				return data;
			}
			return data;
		},
		"searchable" : false,
		"bSortable" : false
	}, {
		// 15
		"targets" : -1,
		"data" : null,
		"defaultContent" : '',
		"render" : function(data, type, row) {
			if (type === 'display') {
				return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnEditarOtroDeb" idOtroDebVar="' + data.idOtrosDebitoPantalla + '" class="btn btn-xs btn-link" type="button" title="Editar"> <i class="icon-edit bigger-120"></i></button><a> </a>	<button id="btnEliminarOtroDeb" class="btn btn-xs btn-link" type="button" title="Eliminar de la lista" idOtroDebVar="' + data.idOtrosDebitoPantalla + '"><i class="icon-trash bigger-120"></i></button></div>';
			}
			return data;
		},
		"searchable" : false,
		"bSortable" : false
	}, { // 16
		"mData" : "erroresImporte",
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
	} ]
};

var grillaDetalleOtrosDeb = {
	dom : 'rt',
	"sScrollX" : true,
	"scrollY" : SCROLL_Y,
	"bScrollCollapse" : true,
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
};

// *************************************************************************************************************//
// FUNCIONES //
// *************************************************************************************************************//
function dibujarTablaClientesOtrosDeb() {
	tablas.clientesOtrosDebitos = $("#clientesOtrosDebitos").dataTable(grillaClientesOtrosDebitos);
}

function dibujarTablaOtrosDeb() {
	tablas.otrosDebSeleccionados = $("#grillaOtrosDeb").dataTable(grillaOtrosDebitos);
	$("div.eliminarTodosOtrosDebitos").html('<button type="button" id="btnEliminarTodosOtrosDebitos" class="customAll" disabled><font size=1,5>Eliminar todos</font></button>');
}

function dibujarTablaDetalleOtrosDeb() {
	tablas.comprobantesOtrosDebito = $("#comprobantesOtrosDebito").dataTable(grillaDetalleOtrosDeb);
}

function cargarSolapaOtrosDeb() {

	dibujarTablaClientesOtrosDeb();
	dibujarTablaOtrosDeb();
	dibujarTablaDetalleOtrosDeb();
}

function limpiarInputsYSelectsOtrosDeb() {
	clearInputText('bloqueReferenciaPago');
	clearSelectInput('bloqueReferenciaPago');
	clearSelectInput('bloqueSociedadYSistema');
	$('#comprobanteArchOtrosDeb').val('');
	$('#borrarComprobanteOtrosDeb').click();
}

function editarOEliminarDeGrillaOtrosDeb(idOtroDebito, idBoton) {

	var aData = null;
	var otroDeb = [];
	var row = null;

	if ('btnEditarOtroDeb' === idBoton) {

		if (tablas.otrosDebSeleccionados.fnGetData() != null) {
			$('#cantidadOtrosDebitosEnGrilla').val(tablas.otrosDebSeleccionados.fnGetData().length);
		}

		$(tablas.otrosDebSeleccionados.fnGetData()).each(function(j, debito) {
			if (debito.idOtrosDebitoPantalla == idOtroDebito) {
				aData = tablas.otrosDebSeleccionados.fnGetData([ j ]);
				llenarInputsOtrosDeb(aData);
				visualizarControlesForm(CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][$('#selectSociedadOtrosDeb').val()][$('#selectSistemaOtrosDeb').val()][$('#selectTipoDebitoOtrosDeb').val()][$('#selectMonedaOtrosDeb').val()]);
				row = tablas.otrosDebSeleccionados.fnGetNodes(j);
				tablas.otrosDebSeleccionados.fnDeleteRow(row);
				eliminarEnTablaByIndex(j, datosTablas.otrosDebitosSeleccionados);
				detalleDebitosDelCobro.subDocumento(aData.tipoDebitoEnum);
				return false;
			}
		});

	} else if ('btnEliminarOtroDeb' === idBoton) {
		$(tablas.otrosDebSeleccionados.fnGetData()).each(function(j, debito) {
			if (debito.idOtrosDebitoPantalla == idOtroDebito) {
				aData = tablas.otrosDebSeleccionados.fnGetData([ j ]);
				validarAdjuntoAEliminar(aData);

				row = tablas.otrosDebSeleccionados.fnGetNodes(j);
				tablas.otrosDebSeleccionados.fnDeleteRow(row);
				eliminarEnTablaByIndex(j, datosTablas.otrosDebitosSeleccionados);
				detalleDebitosDelCobro.subDocumento(aData.tipoDebitoEnum);

				return false;
			}
		});
	}
}

function validarAdjuntoAEliminar(debitoGrilla) {
	/* Valido si hay mas debitos en la grilla con el mismo idAdjunto a eliminar */
	if (datosTablas.otrosDebitosSeleccionados.length > 1) {
		datosTablas.otrosDebitosSeleccionados.forEach(function(debito) {
			if (!$.isEmpty(debitoGrilla.idAdjunto) && !$.isEmpty(debito.idAdjunto)) {
				if (debitoGrilla.idAdjunto != debito.idAdjunto && debitoGrilla.idAdjunto != idAdjuntoGrillaDetalleImputacion()) {
					datosTablas.idsAdjuntosOtrosDeb.push(aData.idAdjunto);
				}
			}
		});
	} else if (!$.isEmpty(debitoGrilla.idAdjunto)) {
		if (debitoGrilla.idAdjunto != idAdjuntoGrillaDetalleImputacion()) {
			datosTablas.idsAdjuntosOtrosDeb.push(debitoGrilla.idAdjunto);
		}
	}

	if (!$.isEmpty(datosTablas.idsAdjuntosOtrosDeb) && datosTablas.idsAdjuntosOtrosDeb.length > 1) {
		var compararIds = function(element) {
			return element != debitoGrilla.idAdjunto;
		};
		if (!$.isEmpty(debitoGrilla.idAdjunto)) {
			var listaFiltradaSinId = datosTablas.idsAdjuntosOtrosDeb.filter(compararIds);
			datosTablas.idsAdjuntosOtrosDeb = listaFiltradaSinId;
		}

	}
}

function hayAdjuntoEnDetalleImputacion() {
	return !$.isEmpty(datosTablas.comprobantesOtrosDebito);
}

function idAdjuntoGrillaDetalleImputacion() {
	idAdjunto = '';
	if (hayAdjuntoEnDetalleImputacion()) {
		idAdjunto = datosTablas.comprobantesOtrosDebito[0].idComprobante;
	}
	return idAdjunto;
}

function validarImporteOtrosDeb(input, tabla, datosTabla, nombreColImp, totalizar) {
	var valor = $(input).val();
	var rowPos = tabla.fnGetPosition($(input).parents('tr')[0]);
	var valorAnterior = datosTabla[rowPos][nombreColImp];
	var asuc = importeToFloat(valorAnterior);
	var listaErrores = [ msgErrorInputNulo, msgErrorInput, msgErrorInputLength, msgErrorInputPorcentaje, TIPO_MENSAJE_ERROR_DESC ];

	if (asuc === '-') {
		asuc = 0;
	} else {
		valorAnterior = formatear(asuc);
		updateValueImporte(datosTabla, '0', rowPos, nombreColImp, null);
	}

	if (totalizar) {
		listaErrores.forEach(function(error) {
			if (contadorMensajesError.totalErroresGrilla > 0) {
				contadorMensajesError.restarError(error);
			}
		});

	}
	// Limpio el mensaje de error
	setMensajeError(input, '', TR_HEIGHT, nombreColImp, '');

	if ($.isEmpty(valor) || valor == '0' || valor == '0,00') {
		setMensajeError(input, msgErrorInputNulo, TR_HEIGHT_ERROR, 'erroresImporte', null);
		updateValue(datosTabla, '-', rowPos, nombreColImp);

		totalDeDocumentos.actualizar();
		return true;
	}

	if (!validarImporteValido(valor)) {
		// actualizo los mensajes para el resto de los campos
		setMensajeError(input, msgErrorInput, TR_HEIGHT_ERROR, 'erroresImporte', '');
		updateValueImporte(datosTabla, importeToFloat(valorAnterior), rowPos, nombreColImp, input);
		totalDeDocumentos.actualizar();
		return true;
	} else {
		var valorSinPuntoDecimales = valor.split('.').join('');
		var valorDescompuesto = valorSinPuntoDecimales.split(',');
		var error = false;

		if (valorDescompuesto[0].length > 9 || (!$.isEmpty(valorDescompuesto[1]) && valorDescompuesto[1].length > 2)) {
			error = true;
		}

		if (error) {
			// actualizo los mensajes para el resto de los campos
			setMensajeError(input, msgErrorInputLength, TR_HEIGHT_ERROR, 'erroresImporte', '');
			updateValueImporte(datosTabla, importeToFloat(valor), rowPos, nombreColImp, input);
			totalDeDocumentos.actualizar();
			return true;
		}
	}

	updateValueImporte(datosTabla, importeToFloat(valor), rowPos, nombreColImp, input);
	totalDeDocumentos.actualizar();
	var tipoCambio = datosTabla[rowPos]['tipoCambio'];

	if (!$.isEmpty(valor) && !$.isEmpty(tipoCambio)) {

		importeMonedaOrigen = dividirNumeros(importeToFloat(valor), importeToFloat(tipoCambio));
		if (importeMonedaOrigen > 0) {
			tablas.otrosDebSeleccionados.fnUpdate(formatear(importeMonedaOrigen), rowPos, 11, false);
		}

	}

	return true;
}

function validaImporteOtrosDebitos(valor) {
	if (!validarImporteValido(valor)) {
		// actualizo los mensajes para el resto de los campos
		return msgErrorInput;
	} else {
		var valorSinPuntoDecimales = valor.split('.').join('');
		var valorDescompuesto = valorSinPuntoDecimales.split(',');

		if (valorDescompuesto[0].length > 9 || (!$.isEmpty(valorDescompuesto[1]) && valorDescompuesto[1].length > 2)) {
			return msgErrorInput;
		}
		if (0 > importeToFloat(valor)) {
			return msgErrorInputNoValido;
		}
		return '';
	}
}

function llenarInputsOtrosDeb(otrosDebDto) {
	$('#selectSociedadOtrosDeb').val(otrosDebDto.sociedadEnum).change();
	$('#selectSistemaOtrosDeb').val(otrosDebDto.sistemaEnum).change();
	$('#selectTipoDebitoOtrosDeb').val(otrosDebDto.tipoDebitoEnum).change();
	$('#selectMonedaOtrosDeb').val(otrosDebDto.monedaEnum).change();
	$('#textReferenciaPagoOtrosDeb').val(otrosDebDto.referenciaPago);
	$('#textClienteOtrosDeb').val(otrosDebDto.cliente);
	$('#idRadioCliente' + otrosDebDto.cliente).prop('checked', true);
	$('#textNumeroDocumentoOtrosDeb').val(otrosDebDto.numeroDocumento);
	$('#textFechaVencimientoOtrosDeb').val(otrosDebDto.fechaVencimiento);
	$('#textTipoCambioOtrosDeb').val(otrosDebDto.tipoCambio);
	$('#textImporteOtrosDeb').val(otrosDebDto.importe);
	$('#textImporteMonedaOrigenOtrosDeb').val(otrosDebDto.importeMonedaOrigen);

	if (!$('#mantenerComprobantesAdjuntosVal').is(':checked') && !$.isEmpty(otrosDebDto.idAdjunto)) {
		var adjunto = {
			nombreArchivo : otrosDebDto.nombreAdjunto,
			idComprobante : otrosDebDto.idAdjunto,
			descripcionArchivo : 'Adjunto Otros Debito',
			motivoAdjunto : MOTIVO_ADJUNTO_OTROS_DEBITO
		};

		datosTablas.comprobantesOtrosDebito.push(adjunto);
		tablas.comprobantesOtrosDebito.fnAddData(adjunto, true);

		$('#agregarDetalleDiv').show();
		$('#btnAdjComprobanteOtrosDeb').prop('disabled', true);
	}
	$('#textSinDiferenciaDeCambioOtrosDeb').val(otrosDebDto.sinDiferenciaDeCambio);
	cargarComboSinDiferenciaCambio();

}

function cargarInputCliente(idCliente) {
	$('#textClienteOtrosDeb').val(eliminaCerosADerecha(idCliente));
	_clienteSeleccionadoOtroDebito = eliminaCerosADerecha(idCliente);
}

function cargarTablaDetalleOtrosDeb() {
	tablas.comprobantesOtrosDebito = $("#comprobantesOtrosDebito").dataTable(grillaDetalleOtrosDeb);
}

// El débito manual que se esté cargando no debe estar previamente seleccionado
// Como clave de duplicidad se tomará:
// Sociedad
// Sistema
// Número legal de documento (para facturas, notas de débitos, equipos,
// servicios, documentos SAP)
// o
// número de referencia (para cuentas corrientes no SAP) //
// o
// número de cliente (para cuentas corrientes SAP) //
// o
// tipo de débito (para el caso de conjuntos de débitos)
function generarIdOtrosDebitoPantalla(sociedad, sistema, tipoDebito, nroLegal, nroCliente, nroReferencia) {
	var idReferencia = '';
	idReferencia = sociedad + '_' + sistema + '_';

	if (CONFCOMBOTIPOCOMPROBANTE.C_C === CONFCOMBOTIPOCOMPROBANTE[tipoDebito]) {
		if (CONFCOMBOSISTEMA.SAP !== CONFCOMBOSISTEMA[sistema]) {
			idReferencia += nroReferencia;
		} else {
			idReferencia += nroCliente;
		}
	} else if (CONFCOMBOTIPOCOMPROBANTE.CDD === CONFCOMBOTIPOCOMPROBANTE[tipoDebito]) {
		idReferencia += tipoDebito;
	} else {
		idReferencia += nroLegal;
	}
	return idReferencia;
}

function agregarOtrosDeb() {
	var salida = true;
	var campos = CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][$('#selectSociedadOtrosDeb').val()][$('#selectSistemaOtrosDeb').val()][$('#selectTipoDebitoOtrosDeb').val()][$('#selectMonedaOtrosDeb').val()];
	var nombre = '';
	$('#errorAgregarOtroDeb').text('');
	for ( var campo in campos) {
		if ('SELECT' === campos[campo].tipoCampo) {
			nombre = '#select' + capitalizeFirstChart(campo) + 'OtrosDeb';
		} else {
			nombre = '#text' + capitalizeFirstChart(campo) + 'OtrosDeb';
			if (!validadarFormOtrosDev[campo].val($(nombre).val())) {
				salida = false;
			}
		}
	}

	if (salida) {
		var errorDuc = detalleDebitosDelCobro.verificar($('#selectTipoDebitoOtrosDeb').val());
		var cantidadOtrosDebitoEnGrilla = -1;
		if (tablas.debitosSeleccionados.fnGetData() != null) {
			cantidadOtrosDebitoEnGrilla = tablas.otrosDebSeleccionados.fnGetData().length;
		}

		if (cantidadOtrosDebitoEnGrilla > CANTIDAD_MAX_OTROS_DEBITOS) {
			$('#errorAgregarOtroDeb').text('ERROR: La cantidad total de otros debito que se intentan subir superan el maximo de ' + CANTIDAD_MAX_OTROS_DEBITOS + ' otros debito esperados.');
			return false;
		} else if (!$.isEmpty(errorDuc)) {
			$('#errorAgregarOtroDeb').text(errorDuc);
			return false;
		} else {
			var sociedadEnum = $('#selectSociedadOtrosDeb').val();
			var sistemaEnum = $('#selectSistemaOtrosDeb').val();
			var tipoDebitoEnum = $('#selectTipoDebitoOtrosDeb').val();
			var nroLegalEnum = $('#textNumeroDocumentoOtrosDeb').val();
			// var cliente =
			// eliminaCerosADerecha($('#textClienteOtrosDeb').val());
			detalleDebitosDelCobro.addDocumento(tipoDebitoEnum);
			// Completo los campos nesesacione
			var otroDeb = {
				sociedadEnum : sociedadEnum,
				sociedad : CONFCOMBOSOCIEDAD[sociedadEnum].descripcion,
				sistemaEnum : sistemaEnum,
				sistema : CONFCOMBOSISTEMA[sistemaEnum].descripcion,
				monedaEnum : $('#selectMonedaOtrosDeb').val(),
				moneda : CONFCOMBOMONEDA[$('#selectMonedaOtrosDeb').val()].signo2,
				tipoDebitoEnum : tipoDebitoEnum,
				tipoDebito : CONFCOMBOTIPOCOMPROBANTE[tipoDebitoEnum].descripcion,
				monedaImporteCobrar : returnNameMoneda(_monedaDelCobro),
				origen : 'ONLINE',

				referenciaPago : null,
				cliente : null,
				numeroDocumento : null,
				fechaVencimiento : null,
				tipoCambio : null,
				importe : null,
				importeMonedaOrigen : null,
				sinDiferenciaDeCambio : null,
				nombreAdjunto : null,
				idOtrosDebitoPantalla : null,
				erroresImporte : '',
				idAdjunto : null,
				nombreAdjunto : ''
			};

			for ( var campo in campos) {
				if ('INPUT' === campos[campo].tipoCampo) {
					if ('agregarDetalle' != campo) {
						nombre = '#text' + capitalizeFirstChart(campo) + 'OtrosDeb';
						otroDeb[campo] = $(nombre).val();
					} else {
						otroDeb.nombreAdjunto = datosTablas.comprobantesOtrosDebito[0].nombreArchivo;
						otroDeb.idAdjunto = datosTablas.comprobantesOtrosDebito[0].idComprobante;
					}

				}
			}

			if (!$.isEmpty(otroDeb.tipoCambio)) {
				otroDeb.tipoCambio = agregarDecimales(otroDeb.tipoCambio);
			}

			otroDeb.idOtrosDebitoPantalla = generarIdOtrosDebitoPantalla(sociedadEnum, sistemaEnum, tipoDebitoEnum, nroLegalEnum, otroDeb.cliente, $('#textReferenciaPagoOtrosDeb').val());
			otroDeb.erroresImporte = '';

			var otroDebitoEnGrilla = buscarOtrosDebSeleccionadosPorReferencia(otroDeb.idOtrosDebitoPantalla);
			if (!$.isEmpty(otroDebitoEnGrilla)) {
				$('#errorAgregarOtroDeb').text('El Débito seleccionado ya forma parte del cobro a configurar');
				return false;
			}

			if (!$('#mantenerComprobantesAdjuntosVal').is(':checked')) {
				tablas.comprobantesOtrosDebito.fnClearTable();
				datosTablas.comprobantesOtrosDebito = [];
				$('#btnAdjComprobanteOtrosDeb').prop('disabled', false);
			}
			datosTablas.otrosDebitosSeleccionados.push(otroDeb);
			tablas.otrosDebSeleccionados.fnAddData(otroDeb, true);
			limpiarInputsYSelectsOtrosDeb();
			cargarComboSinDiferenciaCambio();
			noVisualizarControlesForm();
			$('#selectMonedaOtrosDeb').prop('disabled', true);
			$('#selectTipoDebitoOtrosDeb').prop('disabled', true);
			$('#selectSistemaOtrosDeb').prop('disabled', true);
			$('#idRadioCliente' + _clienteSeleccionadoOtroDebito).prop('checked', false);
			vaciarCombo('selectMonedaOtrosDeb');
			vaciarCombo('selectTipoDebitoOtrosDeb');
			vaciarCombo('selectSistemaOtrosDeb');
			crearCombo('selectSociedadOtrosDeb', CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)], CONFCOMBOSOCIEDAD);
			return true;
		}
	}
	return false;
}
function buscarOtrosDebSeleccionadosPorReferencia(referencia) {
	for (var index = 0, sizeMax = datosTablas.otrosDebitosSeleccionados.length; index < sizeMax; index++) {
		if (datosTablas.otrosDebitosSeleccionados[index].idOtrosDebitoPantalla === referencia) {
			return datosTablas.otrosDebitosSeleccionados[index];
		}
	}
	return null;
}
function cargarComboSinDiferenciaCambio() {
	$('#textSinDiferenciaDeCambioOtrosDeb').attr("disabled", false);

	if ($('#selectMotivo').val() != COMPENSACION) {
		$('#textSinDiferenciaDeCambioOtrosDeb').val('N');
		$('#textSinDiferenciaDeCambioOtrosDeb').attr("disabled", true);
	}
}

function otrosDebitoCheck(object, attr) {
	var checked = $(object).is(":checked");
	var node = $(object).parents('tr')[0];
	var aPos = tablas.otrosDebSeleccionados.fnGetPosition(node);

	if (!checked) {
		updateValue(datosTablas.otrosDebitosSeleccionados, 'N', aPos, attr);
	} else {
		updateValue(datosTablas.otrosDebitosSeleccionados, 'S', aPos, attr);
	}

}

function logicaBotonAdjuntarOtrosDebito() {
	// ----------------------------------------------------------------------------------------
	// COMPROBANTE OTROS DEBITOS
	// ----------------------------------------------------------------------------------------
	$('#btnAdjComprobanteOtrosDeb').click(function() {
		$('#bloqueEspera').trigger('click');
		$("#errorAgregarDetalleOtrosDeb").text('');

		if (datosTablas.comprobantesOtrosDebito.length == 0) {
			var error = false;
			if (!$('#comprobanteArchOtrosDeb').val()) {
				$("#errorAgregarDetalleOtrosDeb").text("Debe seleccionar un archivo.");
				$("#errorAgregarDetalleOtrosDeb").show();
				error = true;
			}
			if (!error) {
				$("#adjComprobanteFormOtrosDeb").ajaxForm({
					dataType : 'json',
					data : {
						idCobro : $('#idCobro').val(),
						descripcion : "Adjunto Otros Debito",
						motivoAdjunto : MOTIVO_ADJUNTO_OTROS_DEBITO
					},
					success : function(data) {
						$('.fileupload').fileupload('clear');
						if (data.success) {
							var comp = {
								idComprobante : data.id,
								nombreArchivo : data.fileName,
								descripcionArchivo : data.descripcion
							};

							tablas.comprobantesOtrosDebito.fnAddData([ comp ], true);
							datosTablas.comprobantesOtrosDebito.push(comp);
							$('#btnAdjComprobanteOtrosDeb').prop('disabled', true);

						} else {
							$("#errorAgregarDetalleOtrosDeb").text(data.descripcionError);
							$("#errorAgregarDetalleOtrosDeb").show();
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

	$('#comprobantesOtrosDebito tbody').on('click', 'button', function() {
		var node = $(this).parents('tr')[0];
		var aData = tablas.comprobantesOtrosDebito.fnGetData(node);

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
						tablas.comprobantesOtrosDebito.fnDeleteRow(node);
						datosTablas.comprobantesOtrosDebito = $.grep(datosTablas.comprobantesOtrosDebito, function(seleccionado) {
							return seleccionado.idComprobante != aData.idComprobante;
						});
						$('#btnAdjComprobanteOtrosDeb').prop('disabled', false);
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

	// $('#grillaOtrosDeb tbody').on('click', 'button', function() {
	// var idOtroDebito = $(this).attr('idOtroDebVar');
	// var idBoton = this.id;
	//
	// $('#bloqueEspera').trigger('click');
	//
	// editarOEliminarDeGrillaOtrosDeb(idOtroDebito, idBoton);
	// totalDeDocumentos.actualizar();
	//
	// ocultarBloqueEspera();
	//		
	//		
	//	
	// });
}

/*
 * u591368 Fernando Nicolás Quispe
 * 
 */
function descargarComprobanteEnGrillaOtrosDeb(idOtroDebito, idBoton) {
	var aData = null;
	// var row = null;

	if (idBoton == 'btnDescargar') {
		$(tablas.otrosDebSeleccionados.fnGetData()).each(function(j, debito) {
			if (debito.idOtrosDebitoPantalla == idOtroDebito) {
				aData = tablas.otrosDebSeleccionados.fnGetData([ j ]);
				$('#bloqueEspera').trigger('click');
				var iframe = document.createElement("iframe");
				iframe.src = "configuracion-cobro/descargarComprobante?id=" + aData.idAdjunto;
				iframe.style.display = "none";
				document.body.appendChild(iframe);
				ocultarBloqueEspera();
			}
		});
	}
}
// ----------------------------------------------------------------------------------------
// FIN COMPROBANTE OTROS DEBITOS
// ----------------------------------------------------------------------------------------
// Funciones para el armado de combos
function cargarTrigger() {
	if ($.isEmpty(CONFOTROSDEBITOS)) {
		$('#alertErrorGuardado').show();
		$('#errorGuardado').text("No existe configuracion de campos de la solapa Otros debitos.");
		return;
	}

	$('#selectSociedadOtrosDeb').change(function() {
		noVisualizarControlesForm();
		$('#selectMonedaOtrosDeb').prop('disabled', true);
		vaciarCombo('selectMonedaOtrosDeb');
		vaciarCombo('selectSistemaOtrosDeb');
		vaciarCombo('selectTipoDebitoOtrosDeb');
		$('#selectSistemaOtrosDeb').prop('disabled', true);
		$('#selectTipoDebitoOtrosDeb').prop('disabled', true);
		$('#btnAgregarOtroDeb').prop('disabled', true);

		if (!$.isEmpty(this.value)) {
			crearCombo('selectSistemaOtrosDeb', CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][this.value], CONFCOMBOSISTEMA);
		}
	});
	$('#selectSistemaOtrosDeb').change(function() {
		noVisualizarControlesForm();
		$('#selectMonedaOtrosDeb').prop('disabled', true);
		vaciarCombo('selectMonedaOtrosDeb');
		vaciarCombo('selectTipoDebitoOtrosDeb');
		$('#selectTipoDebitoOtrosDeb').prop('disabled', true);
		$('#btnAgregarOtroDeb').prop('disabled', true);
		if (!$.isEmpty(this.value)) {
			crearCombo('selectTipoDebitoOtrosDeb', CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][$('#selectSociedadOtrosDeb').val()][this.value], CONFCOMBOTIPOCOMPROBANTE);
		}
	});
	$('#selectTipoDebitoOtrosDeb').change(function() {
		noVisualizarControlesForm();
		vaciarCombo('selectMonedaOtrosDeb');
		$('#selectMonedaOtrosDeb').prop('disabled', true);
		$('#btnAgregarOtroDeb').prop('disabled', true);
		if (!$.isEmpty(this.value)) {
			crearCombo('selectMonedaOtrosDeb', CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][$('#selectSociedadOtrosDeb').val()][$('#selectSistemaOtrosDeb').val()][this.value], CONFCOMBOMONEDA);
		}
	});
	$('#selectMonedaOtrosDeb').change(function() {
		noVisualizarControlesForm();
		$('#btnAgregarOtroDeb').prop('disabled', true);
		if (!$.isEmpty(this.value)) {
			visualizarControlesForm(CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)][$('#selectSociedadOtrosDeb').val()][$('#selectSistemaOtrosDeb').val()][$('#selectTipoDebitoOtrosDeb').val()][$('#selectMonedaOtrosDeb').val()]);
			$('#btnAgregarOtroDeb').prop('disabled', false);
		}
	});

	// // Referencia
	// $('#textReferenciaPagoOtrosDeb').focusout(function() {
	// validadarFormOtrosDev.referenciaPago.val(this.value);
	// });
	// // // Nro Documento
	// $('#textNumeroDocumentoOtrosDeb').focusout(function() {
	// validadarFormOtrosDev.numeroDocumento.val(this.value);
	// });
	// // // Fecha de Vencimiento
	// $('#textFechaVencimientoOtrosDeb').change(function() {
	// validadarFormOtrosDev.fechaVencimiento.val(this.value);
	// });
	//	
	// // // Importe
	// $('#textImporteOtrosDeb').focusout(function() {
	// validadarFormOtrosDev.importe.val(this.value);
	// });
	// // // Tipo de Cambio
	// $('#textTipoCambioOtrosDeb').focusout(function() {
	// validadarFormOtrosDev.tipoCambio.val(this.value);
	// });

	$('#textImporteOtrosDeb').focusout(function() {
		calcularImporteMonedaOrigen('#textImporteOtrosDeb', '#textTipoCambioOtrosDeb');
		if ($.isEmpty($('#textImporteOtrosDeb').val())) {
			$('#textImporteMonedaOrigenOtrosDeb').val('');
		}
	});

	$('#textTipoCambioOtrosDeb').focusout(function() {
		calcularImporteMonedaOrigen('#textImporteOtrosDeb', '#textTipoCambioOtrosDeb');
		if ($.isEmpty($('#textTipoCambioOtrosDeb').val())) {
			$('#textImporteMonedaOrigenOtrosDeb').val('');
		}
	});

}
function visualizarControlesForm(conf) {
	for ( var confCampo in conf) {
		validadarFormOtrosDev[confCampo] = new Validador();
		validadarFormOtrosDev[confCampo].campo = conf[confCampo];
		validadarFormOtrosDev[confCampo].validaciones = [];
		validadarFormOtrosDev[confCampo].nombre = confCampo;

		mostrarErrores();

		if (conf[confCampo].nombre == 'importe') {
			$('#monedaOperacionOtroDeb').text($("#selectMonedaOperacion option:selected").val());
		}

		$('#' + conf[confCampo].nombre + 'Div').show();
		$('#error' + capitalizeFirstChart(conf[confCampo].nombre) + 'Div').show();
		if ('INFOCLIENTE' == conf[confCampo].obligatoriedad) {
			$('#' + conf[confCampo].nombre + 'Obligatorio').show();
			var obli = function(value, campoName) {
				var mensaje = '';
				if ($.isEmpty(value)) {
					mensaje = 'Debe seleccionar un cliente';
				}
				return mensaje;
			};
			validadarFormOtrosDev[confCampo].validaciones.push(obli);
			// textClienteOtrosDeb
		} else if ('OBLIGATORIO' == conf[confCampo].obligatoriedad && 'Block' !== conf[confCampo].tipoDeDato) {
			$('#' + conf[confCampo].nombre + 'Obligatorio').show();
			var obli = function(value, campoName) {
				var mensaje = '';
				if ($.isEmpty(value)) {
					mensaje = 'Este campo es requerido';
				}
				return mensaje;
			};
			validadarFormOtrosDev[confCampo].validaciones.push(obli);
		} else if ('SOLO_LECTURA' === conf[confCampo].obligatoriedad) {
			$('#text' + conf[confCampo].nombre + 'OtrosDeb').attr('disabled', true);
		}

		if (!$.isEmpty(conf[confCampo].longitud)) {
			$('#text' + capitalizeFirstChart(conf[confCampo].nombre) + 'OtrosDeb').attr('maxlength', conf[confCampo].longitud);
		}
		// Asignos objetos de validaciones
		if (!$.isEmpty(conf[confCampo].tipoDeDato)) {
			if ('NUMERO_LEGAL' === conf[confCampo].tipoDeDato) {
				// Validacion de numero legal
				var val1 = function(value, campoName) {
					var mensaje = '';
					var regExp = new RegExp(validadarFormOtrosDev[campoName].campo.validacion);
					if (!regExp.test(value)) {
						mensaje = 'Este campo debe respetar el siguiente formato: ' + validadarFormOtrosDev[campoName].campo.validacionMsg;
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val1);
			} else if ('EX-REG' === conf[confCampo].tipoDeDato) {
				// Validacion de numero legal
				var val1 = function(value, campoName) {
					var mensaje = '';
					var regExp = new RegExp(validadarFormOtrosDev[campoName].campo.validacion);
					if (!regExp.test(value)) {
						mensaje = 'Este campo debe respetar el siguiente formato: ' + validadarFormOtrosDev[campoName].campo.validacionMsg;
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val1);
			} else if ('DATE' === conf[confCampo].tipoDeDato) {
				// data-date-format="dd/mm/yyyy"
				$('#text' + capitalizeFirstChart(conf[confCampo].nombre) + 'OtrosDeb').attr('data-date-format', "dd/mm/yyyy");
				var val = function(value, campoName) {
					var mensaje = '';
					if (!eval(validadarFormOtrosDev[campoName].campo.validacion)(value)) {
						mensaje = 'Este campo debe respetar el formato DD/MM/AAAA.';
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val);
			} else if ('NUMERICO' === conf[confCampo].tipoDeDato) {
				// $('#text' + capitalizeFirstChart(conf[confCampo].nombre) +
				// 'OtrosDeb').attr('data-date-format', "dd/mm/yyyy");
				var val1 = function(value, campoName) {
					var mensaje = '';
					var regExp = new RegExp(validadarFormOtrosDev[campoName].campo.validacion);
					if (!regExp.test(value)) {
						mensaje = 'Este campo debe respetar el siguiente formato: ' + validadarFormOtrosDev[campoName].campo.validacionMsg;
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val1);
			} else if ('CURRENCY' === conf[confCampo].tipoDeDato) {
				var val = function(value, campoName) {
					var mensaje = '';
					mensaje = eval(validadarFormOtrosDev[campoName].campo.validacion)(value);
					if ($.isEmpty(mensaje)) {
						mensaje = '';
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val);
			} else if ('Block' === conf[confCampo].tipoDeDato) {
				var val = function(value, campoName) {
					var mensaje = '';
					var operadores = validadarFormOtrosDev[campoName].campo.validacion.split(' ');
					if (!eval(operadores[0])(datosTablas.comprobantesOtrosDebito.length, operadores[1])) {
						mensaje = 'Este campo es requerido';
					}
					return mensaje;
				};
				validadarFormOtrosDev[confCampo].validaciones.push(val);
			}
		}
	}
};

// Maxi ignorar el error esta version de Eclipse no se banca ECMAScript 6
// TODO Voy a ver si puedo usar funciones en ves de clase
// class Validador {
// contructor(nombreCampo, validaciones, value) {
// this.nombre = nombreCampo;
// this.validaciones = [];
// this.value = value;
// };
// val(valor) {
// var mensaje = '';
// var valido = true;
// $('#error' + capitalizeFirstChart(this.nombre) +'OtrosDeb').text('');
// // errorFechaVencimientoOtrosDeb
// for (var functio in this.validaciones) {
// mensaje = this.validaciones[functio](valor, this.nombre);
// if (!$.isEmpty(mensaje)) {
// $('#error' + capitalizeFirstChart(this.nombre) +'OtrosDeb').text(mensaje);
// return false;
// }
// }
// return valido;
// };
// };
function Validador(nombreCampo, validaciones, value) {
	this.nombre = nombreCampo;
	this.validaciones = [];
	this.value = value;

	this.val = function(valor) {
		var mensaje = '';
		var valido = true;
		$('#error' + capitalizeFirstChart(this.nombre) + 'OtrosDeb').text('');
		// errorFechaVencimientoOtrosDeb
		for ( var functio in this.validaciones) {
			mensaje = this.validaciones[functio](valor, this.nombre);
			if (!$.isEmpty(mensaje)) {
				$('#error' + capitalizeFirstChart(this.nombre) + 'OtrosDeb').text(mensaje);
				return false;
			}
		}
		return valido;
	};
};

function capitalizeFirstChart(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}

function noVisualizarControlesForm() {
	$("div .conf").hide();
	ocultarErrores();
	validadarFormOtrosDev = [];

};

function ocultarErrores() {
	$("#bloqueReferenciaPago .error").text('');
	$("#errorClienteDiv .error").text('');
	$("#errorGrillaOtrosDeb .error").text('');
}

function mostrarErrores() {
	$("#bloqueReferenciaPago .error").show();
}

function cargarOtrosDebito() {
	$('#selectSistemaOtrosDeb').prop('disabled', true);
	$('#selectTipoDebitoOtrosDeb').prop('disabled', true);
	$('#selectMonedaOtrosDeb').prop('disabled', true);
}
function agregarOpcionCombo(comboId, valor, descripcion) {
	var opt = document.createElement("option");
	comboId.options.add(opt);
	opt.text = descripcion;
	opt.value = valor;
};
function objetoVacio(obj) {
	return Object.keys(obj).length === 0;
};

// CONFOTROSDEBITOS[returnNameMoneda(_monedaDelCobro)]
function crearCombo(idCombo, conf, tranformada) {
	// Lo basio al combo
	if (objetoVacio(CONFOTROSDEBITOS)) {
		$('#alertErrorGuardado').show();
		$('#errorGuardado').text("No existe configuracion de campos de la solapa Otros debitos.");
		return;
	}
	vaciarCombo(idCombo);
	var keys = Object.keys(conf);
	var options = [];
	var optionsAux = [];
	var op = null;

	if (keys.length > 1) {
		options.push(new Option(leyendaComboSeleccionar, ''));
	}
	for ( var key in keys) {
		op = new Option(tranformada[keys[key]].descripcion, keys[key]);
		optionsAux.push(op);
	}
	$.merge(options, optionsAux.sort(function(a, b) {
		return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
	}));

	$('#' + idCombo).replaceOptions(options);
	if (keys.length == 1) {
		$('#' + idCombo).change();
	}
	$('#' + idCombo).prop('disabled', false);

}
function vaciarCombo(comboName) {
	$('#' + comboName).empty();
};

function vaciarCombo(comboName) {
	$('#' + comboName).empty();
}

var importarCSVOtrosDebitos = function() {
	$("#impOtrosDebitos").ajaxForm({
		data : {
			idCobro : $('#idCobro').val(),
			monedaCobro : returnNameMoneda(_monedaDelCobro)
		},
		dataType : 'json',
		success : function(data) {
			$('.fileupload').fileupload('clear');
			if (data.success) {
				var sizeInTable = datosTablas.otrosDebitosSeleccionados.length;
				if (!$.isEmpty(data.otrosDebitos)) {
					sizeInTable = sizeInTable + data.otrosDebitos.length;
				}
				if (sizeInTable > CANTIDAD_MAX_OTROS_DEBITOS) {

					$('#errorAgregarOtroDeb').text('ERROR: La cantidad total de otros debito que se intentan subir superan el maximo de ' + CANTIDAD_MAX_OTROS_DEBITOS + ' otros debito esperados.');
					flagBloqueDeEspera.terminar();
					return false;
				}
				if (data.clientes && data.clientes.length > 0) {
					var existe = false;
					var agregar = [];

					$(data.clientes).each(function(i, nuevoCliente) {
						if (nuevoCliente.idClienteLegado.length <= 10) {
							nuevoCliente.idClienteLegado = completarCerosADerecha(nuevoCliente.idClienteLegado);
						}
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
						tablas.clientesOtrosDebitos.fnAddData(agregar, true);
						datosTablas.clientesSeleccionados = $.merge(datosTablas.clientesSeleccionados, agregar);
					}
				}
				if (data.otrosDebitos && data.otrosDebitos.length > 0) {
					// var size = data.otrosDebitos.length;
					// var monedaImporteCobrar = _monedaDelCobro;
					// seteo la moneda
					// Operacion en los
					// debitos importados
					// for (var indice = 0; indice < size; indice++) {
					// data.otrosDebitos[indice].monedaImporteCobrar =
					// monedaImporteCobrar;
					// }
					$.merge(datosTablas.otrosDebitosSeleccionados, data.otrosDebitos);
					tablas.otrosDebSeleccionados.fnAddData(data.otrosDebitos, true);
					totalDeDocumentos.actualizar();
				}
				$('#resultadoImpText').val("Resultado de la importación OK.");
				detalleDebitosDelCobro.addLista(datosTablas.otrosDebitosSeleccionados);

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
				$('#resultadoImpOtrosDebText').val(data.errors);
				flagBloqueDeEspera.terminar();
			}
			$('#resultadoImportacionOtrosDebitos').show();
			$('#btnImportarOtrosDebitos').attr('disabled', true);
			// if (tablas.debitosSeleccionados.fnGetData() != null) {
			// $('#cantidadDebitosEnGrilla').val(tablas.debitosSeleccionados.fnGetData().length);
			// }
		}
	}).submit();
};

function calcularImporteMonedaOrigen(selectorImporte, selectorTipoDeCambio) {
	puedeCalcular = false;
	if (!$.isEmpty($(selectorImporte).val()) && !$.isEmpty($(selectorTipoDeCambio).val())) {
		importe = importeToFloat($(selectorImporte).val());
		tipoDeCambio = importeToFloat($(selectorTipoDeCambio).val());
		importeMonedaOrigen = dividirNumeros(importe, tipoDeCambio);
		if (importeMonedaOrigen > 0) {
			importeMonedaOrigen = formatear(importeMonedaOrigen);
			$('#textImporteMonedaOrigenOtrosDeb').val(importeMonedaOrigen);
			puedeCalcular = true;
		}
	}
	return puedeCalcular;
};

function hayCamposSinAgregarOtrosDeb() {
	hayCamposSinAgregar = false;
	$("div#bloqueReferenciaPago :input").each(function() {
		var input = $(this);
		if ($('#selectMotivo') == COMPENSACION) {
			if (!$.isEmpty(input.val())) {
				if (this.id != 'mantenerComprobantesAdjuntosVal' && this.id != 'selectMonedaOtrosDeb' && this.id != 'textClienteOtrosDeb') {
					hayCamposSinAgregar = true;
				}
			}
		} else {
			if (!$.isEmpty(input.val())) {
				if (this.id != 'textSinDiferenciaDeCambioOtrosDeb' && this.id != 'mantenerComprobantesAdjuntosVal' && this.id != 'selectMonedaOtrosDeb' && this.id != 'textClienteOtrosDeb') {
					hayCamposSinAgregar = true;
				}
			}
		}
	});
	return hayCamposSinAgregar;
}
function checkComprobantes() {
	if ($("#mantenerComprobantesAdjuntosVal:checked").val()) {
		$("#mantenerComprobantesAdjuntos").val(true);
	} else {
		$("#mantenerComprobantesAdjuntos").val(false);
	}
}
