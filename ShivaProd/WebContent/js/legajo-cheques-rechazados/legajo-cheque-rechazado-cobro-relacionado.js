// funcion llamada en el ready para registrar los eventos que utilza la solapa
// Cheques
var registrarEventoSolapaCobroRelacionado = function() {
	$("#seleccionarTodos").click(function () {
		var checked = $(this).prop('checked');
		var disabled = false;
		 $('#tablaCobrosRelacionados').find('.odd, .even').find('.checkSelect').each(function(index) {
			 $(this).prop('checked', checked);
		 });
		 $('.checkSelect', tablas.tablaCobrosRelacionados.fnGetNodes()).each(function(index) {
			 disabled = $(this).prop('checked');
			 if (disabled) {
				return false;
			 }
		 });
		 $('#btnRevertir').prop('disabled', !disabled);
	});

	$('#btnRevertir').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#btnRevertir').attr('disabled', true);
		var legajo = {
			idLegajoChequeRechazado: legajoChequeRechazado.idLegajoChequeRechazado,
			estado: legajoChequeRechazado.estado,
			idWorkflow: legajoChequeRechazado.idWorkflow,
			listaCobrosRelacionados:[]
		};

		 $('.checkSelect', tablas.tablaCobrosRelacionados.fnGetNodes()).each(function(index) {
			 if ($(this).prop('checked')) {
				var cobroRelacionado = {
					idChequeRechazadoCobro: this.id
				};
				legajo.listaCobrosRelacionados.push(cobroRelacionado);
			}
		 });
		 
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'legajo-cheque-rechazado/revertir-cobros-relacionados',
			"data" : JSON.stringify(eval(legajo)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					tablas.tablaCobrosRelacionados.fnClearTable();
					$('#seleccionarTodos').attr('disabled',true);
					
					var disabledConfir = false;
					
					if (!$.isEmpty(result.listaCobrosRelacionados)) {
						tablas.tablaCobrosRelacionados.fnAddData(result.listaCobrosRelacionados, true);
						$('#seleccionarTodos').attr('disabled',false);

						var disabledRevertir = true;
						var tablaCobrosRelacionados = tablas.tablaCobrosRelacionados.fnGetData();
						var size = tablaCobrosRelacionados.length;

						for (var i = 0; i < size ; i++) {
							if (tablaCobrosRelacionados[i].estadoCobro === 'COBRADO') {
								disabledRevertir = false;
								break;
							}

							if(SISTEMA_MAP.ICE.descripcion === legajoChequeRechazado.sistemaOrigen) {
								if (tablaCobrosRelacionados[i].estadoCobro !== 'REVERTIDO') {
									disabledConfir = true;
								}
							}
						}



						$('#seleccionarTodos').attr('disabled', disabledRevertir);
						$('#btnRevertir').attr('disabled', disabledRevertir);

						$('#btnConfirmarManual').attr('disabled', disabledConfir);
					}
					

					legajoChequeRechazado.estado = result.resultado.estado;
					legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
					$('#estadoLegajo').val(legajoChequeRechazado.estadoDescripcion);
				} else {
					if (!$.isEmpty(result.errorMensaje)) {
						mostrarErrorGeneral(result.descripcionError);
						//marcarErrorSolapa([solapa.currrent]);
					}
					$('#btnRevertir').attr('disabled', false);
				}
				ocultarBloqueEspera();
			}
		});
	});
	
	$('#btnConfirmarManual').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#btnConfirmarManual').attr('disabled', true);
		var legajo = {
			idAnalista : legajoChequeRechazado.idAnalista,
			idLegajoChequeRechazado: legajoChequeRechazado.idLegajoChequeRechazado,
			estado: legajoChequeRechazado.estado,
			idWorkflow: legajoChequeRechazado.idWorkflow,
			listaCobrosRelacionados:[]
		};

		 $('.checkSelect', tablas.tablaCobrosRelacionados.fnGetNodes()).each(function(index) {
			 if ($(this).prop('checked')) {
				var cobroRelacionado = {
					idChequeRechazadoCobro: this.id
				};
				legajo.listaCobrosRelacionados.push(cobroRelacionado);
			}
		 });
		 
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'legajo-cheque-rechazado/confirmar-fin-reversion',
			"data" : JSON.stringify(eval(legajo)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				$('#bloqueEspera').trigger('click');
				if (result.success) {
					tablas.tablaCobrosRelacionados.fnClearTable();
					$('#seleccionarTodos').attr('disabled',true);
					if (!$.isEmpty(result.listaCobrosRelacionados)) {
						tablas.tablaCobrosRelacionados.fnAddData(result.listaCobrosRelacionados, true);
						$('#seleccionarTodos').attr('disabled',false);
					}
					legajoChequeRechazado.estado = result.resultado.estado;
					legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
					$('#estadoLegajo').val(legajoChequeRechazado.estadoDescripcion);
					$('#btnConfirmarManual').hide();
					legajoChequeRechazado.edicionTipo  = result.resultado.edicionTipo;
					solapa.validar(solapa.COBRO, legajoChequeRechazado);
				} else {
					if (!$.isEmpty(result.errorMensaje)) {
						mostrarErrorGeneral(result.descripcionError);
						$('#btnConfirmarManual').attr('disabled', false);
						//marcarErrorSolapa([solapa.currrent]);
					}
				}
				ocultarBloqueEspera();
			}
		});
	});
	
	
	
};

var drawTabCobros = function() {
if (solapa.validar(solapa.next , legajoChequeRechazado)) {
		
		if (SISTEMA_MAP.SHIVA.descripcion === legajoChequeRechazado.sistemaOrigen) {
			$('#seleccionarTodos').hide();
			if ($('#edMontoAplicadoPendienteReembolsarInput').val() === "$0,00") {
				$('#btnConfirmarManual').show(); //
				$('#btnConfirmarManual').attr('disabled',false);
			} else {
				$('#btnConfirmarManual').attr('disabled',true);
			}
		} else if (SISTEMA_MAP.ICE.descripcion === legajoChequeRechazado.sistemaOrigen) {
			$('#btnRevertir').show().attr('disabled', true);
			
			var disabledRevertir = true;
			var tablaCobrosRelacionados = tablas.tablaCobrosRelacionados.fnGetData();
			var size = tablaCobrosRelacionados.length;
			var contadorRevertidos = 0;
			
			for (var i = 0; i < size ; i++) {
				if (tablaCobrosRelacionados[i].estadoCobro === 'REVERTIDO'
					|| tablaCobrosRelacionados[i].estadoCobro === 'ENVIADO_ICE') {
					contadorRevertidos++;
				}
			}
			
			if (contadorRevertidos === size) {
				$('#btnConfirmarManual').attr('disabled',false);
				$('#btnRevertir').hide();
			} else {
				$('#btnConfirmarManual').attr('disabled',true);
			}
			
			for (var i = 0; i < size ; i++) {
				if (tablaCobrosRelacionados[i].estadoCobro === 'COBRADO') {
					disabledRevertir = false;
					break;
				}
			}
			$('#seleccionarTodos').attr('disabled', disabledRevertir);
		} else if (SISTEMA_MAP.USUARIO.descripcion === legajoChequeRechazado.sistemaOrigen) {
			$('#seleccionarTodos').hide();
			//$('#btnConfirmarManual').attr('disabled',false);
		}
	}
	
};
var clearTabCobros = function() {
	
};
function isAllCheckedPage(classNameChecks, tableName) {
	var allChecked = true;
	if ($(tableName).find('.odd', '.even').find('.checkSelect').length < 1) {
		return false;
	}
	$(tableName).find('.odd, .even').find('.checkSelect').each(function(index) {
		allChecked = $(this).prop('checked');
		if (!allChecked) {
			return false;
		}
	});
	
	return allChecked;
}
function checking(leaderCheckId, classNameChecks, table, searchBtnId) {
	$('#' + searchBtnId).prop('disabled', !habilitarBtnRevertir(classNameChecks, table));
	
	if (isAllCheckedPage(classNameChecks, table)) {
		$('#' + leaderCheckId).prop('checked', true);
	} else {
		$('#' + leaderCheckId).prop('checked', false);
	}
};

function habilitarBtnRevertir(classNameChecks, table) {
	var reversionHabilitada = false;
	$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
		if ($(elem).prop('checked')) {
			reversionHabilitada = true;
		}
	});
	return reversionHabilitada;
};

function cambiarSolapa(step) {
	
	$('#gestion-cheques-rechazados-tabs-t-'+step).click();
};

//isUndefinedNullDashEmptyOrFalse(edicion)

function detalleCobro(idOperacion) {
	$('#bloqueEspera').trigger('click');

	$('#idOperacionRelacionada').val(idOperacion);
	$('#solapa').val(solapa.COBRO);
	
	$('#formLegajo')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion";
	$('#formLegajo')[0].submit();
};

function revertirCobro(idOperacion) {
	$('#bloqueEspera').trigger('click');
	$('#errorCobrosRelacionados').text("");
	var legajo = {
		idOperacion:idOperacion
	};

	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'legajo-cheque-rechazado/verificar-reversion-shiva-en-proceso',
		"data" : JSON.stringify(eval(legajo)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				$('#bloqueEspera').trigger('click');

				$('#idOperacionRelacionada').val(idOperacion);
				$('#solapa').val(solapa.COBRO);
				if (isUndefinedNullDashEmptyOrFalse($('#idLeg').val())) {
					$('#idLeg').val($('#idLegajo').val());
				}
				$('#formLegajo')[0].action=urlAsynchronousHTTP+"/descobro-reversion-configuracion";
				$('#formLegajo')[0].submit();
			} else {
				$('#errorCobrosRelacionados').text(result.errors);
				ocultarBloqueEspera();
			}
			
		}
	});
};