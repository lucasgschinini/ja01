/**
 * Verifica si una compensacion fue seleccionada
 * Retorna true. Si no se a seleccionado ninguna medio de pago o si se a seleccionado
 * no sea una compensacion
 */
function tieneCompensacionSeleccionada(mediosPagos) {
	var salida = false;
	if (!
		$.isEmpty(mediosPagos) &&
		$('#selectMotivo').val() === COMPENSACION
	) {
		salida = true;
	}
	return salida;
}
function tieneCompensacionNoSeleccionada(mediosPagos) {
	var salida = false;
	if ($('#selectMotivo').val() === COMPENSACION) {
		if ($.isEmpty(mediosPagos)) {
			salida = true;
		} else {
			salida = false;
		}
	} else {
		salida = false;
	}
	return salida;
}

function grisarSolapaCreditosSegunMotivo(motivo) {
	if (motivo == COMPENSACION) {
		$(CONF_COBRO_TAB_CRED).css({"color": "#eee"});
	}
}

function actualizarControlesPorCambioDeMotivo(combo) {
	//TODO PARA PAU
	// Si es motivo = Compensacion 
	// Poner una CONSTANTE!!!!
	var valor = "";
	if (!$.isEmpty(combo)) {
		if (typeof combo == 'object') {
			valor = combo.value;
		} else {
			valor = combo;
		}
		drawOtrosMedioPagoSegunMotivoCobro(valor);
		if (valor == COMPENSACION) {
			$('#cotizacionOnlineDiv').show();
			_monedaDelCobro = '$';
			$('#selectMonedaOperacion').val('$');
			$('#selectMonedaOperacion').attr('disabled', true);
			grisarSolapaCreditosSegunMotivo(valor);
	//		validarGrisadoTrataDif();
		} else {
			$("#fechaTipoCambio").val("");
			$("#tipoDeCambio").val("");
			$('#cotizacionOnlineDiv').hide();
			$('#errorFechaTipoCambio').text("");
			$('#errorTipoDeCambio').text("");
			$('#selectMonedaOperacion').attr('disabled', false);
			$(CONF_COBRO_TAB_CRED).css({"color": "#5f5f5f"});
		}
	}
}

function borrarDocumentosSegunMotivoCobro(motivoPrevio,motivoNuevo, combo){
	
	var detalleDeseleccionar = '';
	
	//Si cambia de motivo 'Compensaciones' a algun otro, se borra la tabla de mediosPago
	if (motivoPrevio == COMPENSACION && motivoNuevo != '') {
		
		detalleDeseleccionar = detalleDocumentos.detalleCreditosMediosPagoADesasociar();
		
		if(detalleDeseleccionar.cant > 0){
			bootbox.confirm(detalleDeseleccionar.msg, function(result) {
				if (result) {
					datosTablas.debitosSeleccionados.length = 0;
					datosTablas.mediosPagos.length = 0;
					datosTablas.otrosDebitosSeleccionados.length = 0;
					datosTablas.documentosCapsSeleccionados.length = 0;
					totalDeDocumentos.actualizar();
					fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
					fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
					$.datepicker._clearDate($("#fechaTipoCambio"));
				}else{
					$('#selectMotivo').val(motivoPrevio);
				}
				actualizarControlesPorCambioDeMotivo(combo);
			});
		}else{
			$.datepicker._clearDate($("#fechaTipoCambio"));
			actualizarControlesPorCambioDeMotivo(combo);
		}
		//Si cambia de cualquier motivo hacia 'Compensacion', se borra la tabla de mediosPago y creditos
	} else if(motivoPrevio != COMPENSACION && motivoPrevio != '' && motivoNuevo == COMPENSACION) {
		
		if($('#selectMonedaOperacion').val() != '$'){
			if (detalleDocumentos.verificarClientesDeseleccionar()) {
				var detalleDeseleccionar = detalleDocumentos.detalleClientesADesasociar('moneda');
				bootbox.confirm(detalleDeseleccionar.msg, function(result) {
					if (result) {
						datosTablas.debitosSeleccionados.length = 0;
						datosTablas.otrosDebitosSeleccionados.length = 0;
						datosTablas.creditosSeleccionados.length = 0;
						datosTablas.mediosPagos.length = 0;
//						_monedaDelCobro = '$';
						totalDeDocumentos.actualizar();
//						$('#selectMonedaOperacion').val('$');
						$('#acuerdoFactInput').val('');
						$("#estadoAcuerdoFacturacion").val('');

						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
						$.datepicker._clearDate($("#fechaTipoCambio"));
					} else {
						$('#selectMotivo').val(motivoPrevio);
					}
					actualizarControlesPorCambioDeMotivo(combo);
				});	
			} else {
//				_monedaDelCobro = '$';
				totalDeDocumentos.actualizar();
//				$('#selectMonedaOperacion').val('$');
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
				fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
				$.datepicker._clearDate($("#fechaTipoCambio"));
				actualizarControlesPorCambioDeMotivo(combo);
			}
		} else {
			detalleDeseleccionar = detalleDocumentos.detalleCreditosMediosPagoADesasociar();
			
			if(detalleDeseleccionar.cant > 0){
				bootbox.confirm(detalleDeseleccionar.msg, function(result) {
					if (result) {
						datosTablas.debitosSeleccionados.length = 0;
						datosTablas.creditosSeleccionados.length = 0;
						datosTablas.otrosDebitosSeleccionados.length = 0;
						datosTablas.mediosPagos.length = 0;
						totalDeDocumentos.actualizar();
						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraDebito');
						fijarFiltroBusquedaSegunMonedaCobro('searchCriteraCredito');
						$('#selectMonedaOperacion').attr('disabled', true);
						$.datepicker._clearDate($("#fechaTipoCambio"));
					}else{
						$('#selectMotivo').val(motivoPrevio);
					}
					actualizarControlesPorCambioDeMotivo(combo);
				});
			}else{
				$('#selectMonedaOperacion').attr('disabled', true);
				$.datepicker._clearDate($("#fechaTipoCambio"));
				actualizarControlesPorCambioDeMotivo(combo);
			}
		}
	} else {
		// Si es estado final no se deben actualizar los controles de motivo
		if (!esEstadoCobroFinal($('#estado').val())) {
			actualizarControlesPorCambioDeMotivo(combo);	
		}
	}
}
function validarFechaTipoCambio() {
	$("#errorFechaTipoCambio").text("");
	var fecha = $('#fechaTipoCambio').val();
	var salida = true;

	if ($.isEmpty(fecha)) {
		$("#errorFechaTipoCambio").text("Este campo es requerido.");
		salida = false;
	} else if (!$.formatoFecha(fecha)) {
		$("#errorFechaTipoCambio").text("Este campo debe respetar el formato DD/MM/AAAA.");
		salida = false;
	} else if (!$.validacionFecha(fecha)) {
		$("#errorFechaTipoCambio").text("Debe ingresar una fecha valida.");
		salida = false;
	}
	return salida;
}
function actualizarControles(solapa) {
	
	switch (solapa) {
	case 'medio':
		var disabled = tieneCompensacionSeleccionada(datosTablas.mediosPagos);
		$('#selectMedioPago').attr('disabled', disabled);
		$('button[id^="btnAgregarMedio"]').attr('disabled', disabled);
		if ($('#selectMotivo').val() === COMPENSACION) {
			$('#medioPagoFecha').attr('disabled', true);
		} else {
			$('#medioPagoFecha').attr('disabled', false);
		}

		break;
	default:
		break;
	}
}
// TODO para PAU
habilitarMotivo = function(varEdicionSegunEstadoMarca, motivo) {
	if ('edicionParcial' === varEdicionSegunEstadoMarca) {
		if (!$.isEmpty(motivo)) { // Es un  en estado final nacido de operacion masiva
			if(OPERACION_MASIVA[0] !== motivo || OPERACION_MASIVA[1] !== motivo || OPERACION_MASIVA[2] !== motivo ){
				if(COBRO_SALIDA_AUTOMATICA_VALORES !== motivo){
					if (COMPENSACION === motivo) {
							$('#selectMotivo option[value=1]').hide();
							$('#selectMotivo option[value=2]').hide();
							$('#selectMotivo option[value=3]').hide();
							$('#selectMotivo option[value=4]').hide();
							$('#selectMotivo option[value=5]').hide();
							$('#selectMotivo option[value=6]').hide();
							$('#selectMotivo option[value=7]').hide();
							$('#selectMotivo option[value=8]').hide();
							$('#selectMotivo option[value=9]').show();
							$('#selectMotivo option[value=10]').hide();
							$('#selectMotivo option[value=""]').hide();
						} else{
							$('#selectMotivo option[value=1]').hide();
							$('#selectMotivo option[value=2]').hide();
							$('#selectMotivo option[value=3]').hide();
							$('#selectMotivo option[value=4]').show();
							$('#selectMotivo option[value=5]').show();
							$('#selectMotivo option[value=6]').show();
							$('#selectMotivo option[value=7]').show();
							$('#selectMotivo option[value=8]').show();
							$('#selectMotivo option[value=9]').hide();
							$('#selectMotivo option[value=10]').hide();
						} 
					} else{
						$('#selectMotivo option[value=1]').hide();
						$('#selectMotivo option[value=2]').hide();
						$('#selectMotivo option[value=3]').hide();
						$('#selectMotivo option[value=4]').hide();
						$('#selectMotivo option[value=5]').hide();
						$('#selectMotivo option[value=6]').hide();
						$('#selectMotivo option[value=7]').hide();
						$('#selectMotivo option[value=8]').hide();
						$('#selectMotivo option[value=9]').hide();
						$('#selectMotivo option[value=10]').show();
						$('#selectMotivo option[value=""]').hide();
					}
			}else{
				$('#selectMotivo option[value=1]').show();
				$('#selectMotivo option[value=2]').show();
				$('#selectMotivo option[value=3]').show();
				$('#selectMotivo option[value=4]').hide();
				$('#selectMotivo option[value=5]').hide();
				$('#selectMotivo option[value=6]').hide();
				$('#selectMotivo option[value=7]').hide();
				$('#selectMotivo option[value=8]').hide();
				$('#selectMotivo option[value=9]').hide();
				$('#selectMotivo option[value=10]').hide();
				$('#selectMotivo option[value=""]').hide();
			}
		}else {
			$('#selectMotivo option[value=1]').hide();
			$('#selectMotivo option[value=2]').hide();
			$('#selectMotivo option[value=3]').hide();
			$('#selectMotivo option[value=4]').show();
			$('#selectMotivo option[value=5]').show();
			$('#selectMotivo option[value=6]').show();
			$('#selectMotivo option[value=7]').show();
			$('#selectMotivo option[value=8]').show();
			$('#selectMotivo option[value=9]').hide();
			$('#selectMotivo option[value=10]').hide();
		}
	}
};
// TODO FIN PAU 
//-----------------------------------------------------------------------------
//BUSCAR CAP
//-----------------------------------------------------------------------------
function btnBuscarCapOnClick() {
	$('#bloqueEspera').trigger('click');
	$("span.error").text("");

	var clientes = $(".mpClienteSel:checked", tablas.clientesMedios.fnGetNodes());
	var idsClientes = [];
	if (!$.isEmpty(clientes)) {
		$(clientes).each(function(i, elem) {
			aData = tablas.clientesMedios.fnGetData($(this).closest('tr')[0]);
			idsClientes.push(aData);
		});
	}
	var tipoComprobanteSap = $('#selectTipoDocCap').val();
	var confCobroDocCapFiltro = {
		clientes: idsClientes, 
		idDocCap: $('#idDocCap').val(),
		tipoComprobanteSap: ($.isEmpty(tipoComprobanteSap)) ? null : tipoComprobanteSap,
		moneda: $('#selectMonedaCab').val(),
		numeroLegal: $('#nroDocCap').val(),
		fechaDesde: $('#emisionDesdeCap').val(),
		fechaHasta: $('#emisionHastaCap').val(),
		monedaImporte: _monedaDelCobro,
		fechaTipoDeCambio: $('#fechaTipoCambio').val(),
		tipoDeCambio : $('#tipoDeCambio').val(),
		idCobro: $('#idCobro').val(),
	};
	$.ajax({
		"type" : "POST",
		"url": "configuracion-cobro/buscarDocumentosCap",
		"dataType": "json", 
	    "data": JSON.stringify(eval(confCobroDocCapFiltro)), 
	    "contentType": "application/json",
	    "mimeType": "application/json",
		"success" : function(result) {
			$("span.error").text("");
//				tablas.documentosCap.fnClearTable();
			
			
			if (result.success) {
				tablas.documentosCaps.fnClearTable();
				tablas.documentosCaps.fnAdjustColumnSizing(false);
				if (!$.isEmpty(result.proveedoresNoEncontrados)) {
					$('#errorRespuestaCap').text(result.proveedoresNoEncontrados.join('\n'));
				}	
				if (result.resultado.length > 0) {
					tablas.documentosCaps.fnAddData(result.resultado, false);
				}
				actualizarInfoTabla(tablas.documentosCaps, result, 'customPagCap', 'btnAgregarTodosCaps','errorRespuestaCap', CANTIDAD_POR_PAGINA_CAP, aparienciaButton.ocultarButtonDocumentosSeleccionados);
			} else {
				tablas.documentosCaps.fnClearTable();
				tablas.documentosCaps.fnAdjustColumnSizing(false);
				if (!$.isEmpty(result.errorMensaje)) {
					$('#errorRespuestaCap').text(result.errorMensaje);
				} else if (!$.isEmpty(result.proveedoresNoEncontrados)) {
					$('#errorRespuestaCap').html(result.proveedoresNoEncontrados.join('<br />'));
				} else if (!$.isEmpty(result.errores)) {
					for (var i = 0, max = result.errores.length; i < max; i++) {
						$(result.errores[i].campoError).text(result.errores[i].descripcionError);
					}
				}
			}
			ocultarBloqueEspera();
		}
	});
};

function actualizarInfoTablaSinPaginado(cantPag, idCustomPag) {
	var inic = 0;
	if (cantPag === 0) {
		inic = 0;
	} else {
		inic = 1;
	}
	
	$('#' + idCustomPag + '_info span').text('Mostrando registros del ' + inic + ' al ' + cantPag + ' de ' + cantPag  + ' registros en total');
};
function registrarEventoCapsSearch() {
	actualizarInfoTablaSinPaginado(0, "customPagCapSele");
	
	$('#btnEliminarTodosCaps').click(function() {
		var mensaje = "Desea&nbsp;eliminar&nbsp;todos&nbsp;los&nbsp;documetos Caps&nbsp;de&nbsp;la&nbsp;grilla?";
		bootbox.confirm(mensaje, function(result) {
			if (result) {
				aparienciaButton.descubrirButtonPlusDocumentosTodos(
					tablas.documentosCaps,
					tablas.documentosCapsSeleccionados
				);
				tablas.documentosCapsSeleccionados.fnClearTable();
				datosTablas.documentosCapsSeleccionados.length = 0;
				$('#btnEliminarTodosCaps').attr('disabled', true);
				$('#btnAgregarTodosCaps').attr('disabled', false);
				$('button[id^="btnAgregarMedio"]').attr('disabled', true);
				importe = totalDeDocumentos.actualizarCap();
				//selectedRow.clearVariables('creditos');
				actualizarInfoTablaSinPaginado(0, "customPagCapSele");
			}
		});
	});

	$('#caps tbody').off('click');
	$('#caps tbody').on( 'click', 'button', function () {
		$('#bloqueEspera').trigger('click');
		var idPantalla = $(this).attr('idCapVar');
		var idNumeroProveedor = $(this).attr('idProveedor');
		var existe = false;
		var even = false;
		var detalleSeleccionados = null;

		var size = tablas.documentosCapsSeleccionados.fnGetData().length;

		if (size !== 0) {
			even = !tablas.documentosCapsSeleccionados.fnGetData(size -1).even;
		}
		detalleSeleccionados = contadorDocumentosCap(tablas.documentosCapsSeleccionados);
		for (var indice = 0, size = datosTablas.documentosCapsSeleccionados.length; indice < size; indice++) {
			//idNumeroProveedorSeleccionado = datosTablas.documentosCapsSeleccionados[indice].idNumeroProveedor;
			if (datosTablas.documentosCapsSeleccionados[indice].idPantalla === idPantalla) {
				existe = true;
			}
		}
		if (!$.isEmpty(detalleSeleccionados.idProveedor) && idNumeroProveedor !== detalleSeleccionados.idProveedor) {
			$('#errorRespuestaCap').text('No se puede agregar el documento.  No está permitido tener más de un proveedor por compensación');
		} else {
			if (!existe) {
				var aData = null;
				var esAgregable = null;
				$(tablas.documentosCaps.fnGetData()).each(function (j, documentoCap) {
					if (documentoCap.idPantalla === idPantalla) {
						if	(detalleSeleccionados.contador === CANTIDAD_MAX_CAPS) {
							
							esAgregable= 'ERROR: La cantidad total de documentos que se intentan subir superan el maximo de ' + CANTIDAD_MAX_CAPS + ' documentos esperados.';
							
						} else if(!documentoCap.esNoGestionable) { // Si no es gestionable no lo agrego. Antes lo agregaba pero no lo dejaba visible
					    	// Realizo una copia del objeto a agregar
							aData = JSON.parse(JSON.stringify(tablas.documentosCaps.fnGetData()[j]));
							//aData = tablas.documentosCaps.fnGetData()[j];
					    	tablas.documentosCapsSeleccionados.fnAddData(aData, true);
					    	datosTablas.documentosCapsSeleccionados.push(aData);
					    	actualizarInfoTablaSinPaginado(detalleSeleccionados.contador + 1, "customPagCapSele");
					    	aparienciaButton.ocultarButtonPlus(tablas.documentosCaps, idPantalla, j, documentoCap.esReglonAgrupador ? true : undefined);
					    	aData.even = even;
						}
						
						$('button[id^="btnAgregarMedio"]').attr('disabled', false);
						$('#btnEliminarTodosCaps').attr('disabled', false);
						
						if(datosTablas.documentosCapsSeleccionados.length == tablas.documentosCaps.fnGetData().length) {
							$('#btnAgregarTodosCaps').attr('disabled', true);
						}
					}
				});
				
				if (!$.isEmpty(aData)) {
					$('#errorRespuestaCap').text('');
				} else {
					$('#errorRespuestaCap').text(esAgregable);
				}
			}
			totalDeDocumentos.actualizarCap();
		}
		ocultarBloqueEspera();
	});
	
	
	$('#btnAgregarTodosCaps').click(function() {
		var mensaje = "Desea&nbsp;agregar&nbsp;todos&nbsp;los&nbsp;Caps&nbsp;de&nbsp;la&nbsp;grilla?";
		bootbox.confirm(mensaje, function(result) {
			if (result) {
				var cantidadDocumentos = 0;
				//var cantidadDocumentosSeleccionados = 0;
				var gestionable = retornaGestionable(tablas.documentosCaps);
				var detalleSeleccionados = null;
				detalleSeleccionados = contadorDocumentosCap(tablas.documentosCapsSeleccionados);

				if (
					gestionable.idProveedor.length <= 1 &&
					(
						$.isEmpty(detalleSeleccionados.idProveedor) ||
						gestionable.idProveedor[0] === detalleSeleccionados.idProveedor
					)
				) {
					cantidadDocumentos = gestionable.cantidad;
					

					if (CANTIDAD_MAX_CAPS > cantidadDocumentos + detalleSeleccionados.contador){
						
						var sizeaux = gestionable.aux.length;
						
						var aux = [];
						for (var i = 0; i < sizeaux; i++) {
							var encontrado = false;
							var size = datosTablas.documentosCapsSeleccionados.length;
							for (var j = 0; j < size; j++) {
								if (gestionable.aux[i].idPantalla === datosTablas.documentosCapsSeleccionados[j].idPantalla) {
									encontrado = true;
									break;
								}
							}
							if (!encontrado) {
								for (var k = i, salida = false; k <sizeaux && (salida === false); k++) {
									var df = JSON.parse(JSON.stringify(gestionable.aux[k]));
									if (df.idPantalla === gestionable.aux[i].idPantalla) {
										datosTablas.documentosCapsSeleccionados.push(df);
									} else {
										salida = true;
										break;
									}
								}
							}
						}
						datosTablas.documentosCapsSeleccionados = $.merge(
							aux,
							datosTablas.documentosCapsSeleccionados
						);
						tablas.documentosCapsSeleccionados.fnClearTable();
						
						actualizarInfoTablaSinPaginado(colorRenglon(datosTablas.documentosCapsSeleccionados), "customPagCapSele");
						tablas.documentosCapsSeleccionados.fnAddData(datosTablas.documentosCapsSeleccionados, true);
						aparienciaButton.ocultarButtonCreditosDebitosSeleccionados(datosTablas.documentosCapsSeleccionados);
						$('#btnEliminarTodosCaps').attr('disabled', false);
						$('#btnAgregarTodosCaps').attr('disabled', true);
						$('button[id^="btnAgregarMedio"]').attr('disabled', false);
						importe = totalDeDocumentos.actualizarCap();
						
					} else {
						$('#errorRespuestaCap').text(
							'ERROR: La cantidad total de documentos que se intentan subir superan el maximo de ' + CANTIDAD_MAX_CAPS + ' documentos esperados.'
						);
					}
				} else {
					$('#errorRespuestaCap').text(
						'No se puede agregar el documento.  No está permitido tener más de un proveedor por compensación.'
					);
				}
			}
		});
	});
};
function retornaGestionable(tabla) {
	var idPantalla = '';
	var retorno = new Object();
	retorno.aux = [];
	retorno.cantidad = 0;
	retorno.idProveedor = [];
	
	for (var i = 0, size = tabla.fnGetData().length; i < size;i++) {
		var data = tabla.fnGetData(i);
		if (data.idPantalla !== idPantalla && !data.esNoGestionable) {
			idPantalla = data.idPantalla;
			retorno.cantidad++;
			if (retorno.idProveedor.indexOf(data.idNumeroProveedor) < 0) {
				retorno.idProveedor.push(data.idNumeroProveedor);
			}
		}
		
		if (!data.esNoGestionable) {
			retorno.aux.push(data);
		}
	}
	return retorno;
}
function registrarEventosCaps() {
	
	$('#btnBuscarCap').click(function() {
		btnBuscarCapOnClick();
	});

	$('#capSeleccionados tbody').on( 'click', 'button', function () {
		$('#bloqueEspera').trigger('click');
		var idPantalla = $(this).attr('idCapSel');
		//Desgriso el registro de la grilla de arriba
		aparienciaButton.descubrirButtonPlusDocumento(tablas.documentosCaps, idPantalla);
	
		$('#btnAgregarTodosCaps').attr('disabled', false);
		datosTablas.documentosCapsSeleccionados = $.grep(datosTablas.documentosCapsSeleccionados, function(seleccionado) {
			return seleccionado.idPantalla != idPantalla;
		});
		tablas.documentosCapsSeleccionados.fnClearTable();
		
		
		
		if (!$.isEmpty(datosTablas.documentosCapsSeleccionados)) {
			
			tablas.documentosCapsSeleccionados.fnAddData(datosTablas.documentosCapsSeleccionados, true);
		}
		
		actualizarInfoTablaSinPaginado(colorRenglon(datosTablas.documentosCapsSeleccionados), "customPagCapSele");
		$('#btnAgregarTodosCaps').attr('disabled', false);

		if($.isEmpty(datosTablas.documentosCapsSeleccionados)){
			$('#btnEliminarTodosCaps').attr('disabled', true);
			$('button[id^="btnAgregarMedio"]').attr('disabled', true);
		}

		//totalDeDocumentos.actualizar();
		tablas.documentosCapsSeleccionados.fnAdjustColumnSizing(true);
		totalDeDocumentos.actualizarCap();
		setTimeout(function(){
			ocultarBloqueEspera();
		}, 500); 
	});

	$('#capSeleccionados tbody').on('click', 'td', function () {
		selectedRow.withFixedColumnCaps(this, 'capsSele');
	});
	$('#capSeleccionados').on( 'page.dt', function () {
		selectedRow.debitosCambioPag = true;
	});
	
};
// Retorna la cantidad de documentos
function colorRenglon(tabla, asegurarNoGestionable) {
	var size = tabla.length;
	var even = true;
	var tablaSalida = [];
	var cant = 0 ;

	for (var i = 0 ; i < size ; i++) {
		if (!tabla[i].esNoGestionable) {
			if (tabla[i].esReglonAgrupador) {
				even = !even;
				cant++;
			}
			tabla[i].even = even;
			if (asegurarNoGestionable !== undefined) {
				tablaSalida.push(tabla[i]);	
			}
		}
	}
	if (asegurarNoGestionable !== undefined) {
		tabla = tablaSalida;
	}
	return cant;
};
function contadorRenglon(tabla) {
	var size = tabla.length;
	var cant = 0 ;

	for (var i = 0 ; i < size ; i++) {
		if (tabla[i].esReglonAgrupador) {
			cant++;
		}
	
	}
	return cant;
};
function contadorDocumentosCap(tabla) {
	var idPantallaAnterior = '';
	var retorno = new Object();
	retorno.contador = 0;
	retorno.idProveedor = null;
	
	$(tabla.fnGetData()).each(function (j, documentoCap) {
		if ($.isEmpty(retorno.idProveedor)) {
			retorno.idProveedor = documentoCap.idNumeroProveedor;
		}
		if (documentoCap.idPantalla != idPantallaAnterior) {
			idPantallaAnterior = documentoCap.idPantalla;
			retorno.contador++;
		}
	});
	
	return retorno;
	
};

//-----------------------------------------------------------------------------
//BUSCAR CAP
//-----------------------------------------------------------------------------
var TIPO_CAMBIO = 11;
var IMPORTEGESTIONABLE = 12;
var SALDOPESIFICADO = 14;
var IMPORTE_PESIFICADO_DOC_ASOCIADO = 15;
var TIPO_CAMBIO_DOC_ASOCIADO = 17;

function capCheckSaldoMaximo(object, attr) {
	$('#bloqueEspera').trigger('click');
	var checked = $(object).is(":checked");
	var aPos = tablas.documentosCapsSeleccionados.fnGetPosition($(object).parents('td')[0]);
	tablas.documentosCapsSeleccionados.fnGetData(aPos[0])[attr] = checked;
	//datosTablas.documentosCapsSeleccionados[aPos[0]][attr] = checked;

	var data = tablas.documentosCapsSeleccionados.fnGetData(aPos[0]);
	var encontrado = 0;

	
	
	if (data.esReglonAsociado) {
		if (!data['sinDifDeCambio']) {
			//tablas.documentosCapsSeleccionados.fnUpdate(data['tipoCambioShiva'], aPos[0], TIPO_CAMBIO_DOC_ASOCIADO , false);
			tablas.documentosCapsSeleccionados.fnUpdate(data['impDocPESToString'], aPos[0], IMPORTE_PESIFICADO_DOC_ASOCIADO, false);
			tablas.documentosCapsSeleccionados.fnGetData(aPos[0]).importePesificadoDocAsociadoBd = data['impDocPES'];
		} else {
			//tablas.documentosCapsSeleccionados.fnUpdate(data['tipoCambioEmision'], aPos[0], TIPO_CAMBIO_DOC_ASOCIADO , false);
			tablas.documentosCapsSeleccionados.fnUpdate(data['importeDocToString'], aPos[0], IMPORTE_PESIFICADO_DOC_ASOCIADO, false);
			tablas.documentosCapsSeleccionados.fnGetData(aPos[0]).importePesificadoDocAsociadoBd = data['importeDoc'];
		}
		
		for (var i = aPos[0]; i >=0; i--) {
			var dataAgrupador =  tablas.documentosCapsSeleccionados.fnGetData(i);
			if (dataAgrupador.esReglonAgrupador && dataAgrupador.idPantalla == data.idPantalla) {
				// Busca el agrupador donde tengo acumulados todos llos totales
				var acuImportePesificadoDocAsociadoBd = 0;
				for (var j = i, salida = false; salida == false; j++) {
					var dataRenglon = tablas.documentosCapsSeleccionados.fnGetData(j);
					if (dataRenglon === null) {
						salida = true;
					} else {
						if (dataRenglon.idPantalla == data.idPantalla) {
							if (dataRenglon.esReglonAsociado) {
								if (!dataRenglon.esMismaMoneda) {
									if (dataRenglon.sinDifDeCambio == null || dataRenglon.sinDifDeCambio == false) {
										acuImportePesificadoDocAsociadoBd += dataRenglon.impDocPES;
									} else {
										acuImportePesificadoDocAsociadoBd += dataRenglon.importeDoc;
									}
								} else {
									acuImportePesificadoDocAsociadoBd += dataRenglon.importeDocMonedaDoc;
								}
								 dataRenglon.importePesificadoDocAsociadoBd; 
							}
						} else {
							salida = true;
						}
					}
				}
				dataAgrupador.importePesificadoDocAsociadoBd = acuImportePesificadoDocAsociadoBd;
				if (!dataAgrupador.esMismaMoneda) {
					dataAgrupador.importeGestionableAFechaShivaTotalBd =
						dataAgrupador.saldoPesificadoTotalAFechaShivaBd + dataAgrupador.importePesificadoDocAsociadoBd;
					dataAgrupador.importeGestionableAFechaEmisionTotalBd =
						dataAgrupador.saldoPesificadoTotalAFechaEmisionBd + dataAgrupador.importePesificadoDocAsociadoBd;
					
					dataAgrupador.importeGestionableAFechaEmisionTotal = formatear(dataAgrupador.importeGestionableAFechaEmisionTotalBd);
					dataAgrupador.importeGestionableAFechaShivaTotal = formatear(dataAgrupador.importeGestionableAFechaShivaTotalBd);
					if ($.isEmpty(dataAgrupador['sinDifDeCambio'])) {
						dataAgrupador.importeGestionableBg = dataAgrupador['importeGestionableAFechaShivaTotalBd'];
						tablas.documentosCapsSeleccionados.fnUpdate(dataAgrupador['importeGestionableAFechaShivaTotal'], i, IMPORTEGESTIONABLE, false);
					} else {
						dataAgrupador.importeGestionableBg = dataAgrupador['importeGestionableAFechaEmisionTotalBd'];
						tablas.documentosCapsSeleccionados.fnUpdate(dataAgrupador['importeGestionableAFechaEmisionTotal'], i, IMPORTEGESTIONABLE, false);
					}
				} else {
					dataAgrupador.importeGestionableBg = dataAgrupador.saldoMonedaOrigenBd + dataAgrupador.importePesificadoDocAsociadoBd;
					tablas.documentosCapsSeleccionados.fnUpdate(formatear(dataAgrupador['importeGestionableBg']), i, IMPORTEGESTIONABLE, false);
				}
				tablas.documentosCapsSeleccionados.fnUpdate(formatear(dataAgrupador.importePesificadoDocAsociadoBd), i, IMPORTE_PESIFICADO_DOC_ASOCIADO, false);
				
				break;
			}
		}
	} else {
		// Cuando el Check es sobre un agrupador!!!
		for(var i = aPos[0], size = tablas.documentosCapsSeleccionados.fnGetData().length; i < size; i++ ) {
			var dataIndex = tablas.documentosCapsSeleccionados.fnGetData(i);
			if (
					dataIndex.idPantalla == data.idPantalla &&
					!dataIndex.esReglonAsociado
			) {
				if (encontrado === 0) {
					encontrado = 1; 
				}
				if (dataIndex.esReglonAgrupador) {
					if (!data['sinDifDeCambio']) {
						//tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['tipoCambioEmision'], i, TIPO_CAMBIO , false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['importeGestionableAFechaShivaTotal'], i, IMPORTEGESTIONABLE, false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['saldoPesificadoTotalAFechaShivaString'], i, SALDOPESIFICADO, false);
					} else {
						//tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['tipoCambioEmision'], i, TIPO_CAMBIO , false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['importeGestionableAFechaEmisionTotal'], i, IMPORTEGESTIONABLE, false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['saldoPesificadoTotalAFechaEmisionaString'], i, SALDOPESIFICADO, false);
					}
				} else if (dataIndex.esReglonCap){
					if (!data['sinDifDeCambio']) {
						//tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['tipoCambioEmision'], i, TIPO_CAMBIO , false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['impDocPESToString'], i, SALDOPESIFICADO, false);
					} else {
						//tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['tipoCambioEmision'], i, TIPO_CAMBIO , false);
						tablas.documentosCapsSeleccionados.fnUpdate(dataIndex['importeDocToString'], i, SALDOPESIFICADO, false);
					}
				}
				dataIndex['sinDifDeCambio'] = data['sinDifDeCambio'];
			} else {
				if (dataIndex.idPantalla !== data.idPantalla) {
					break;
				}
			}
			
		}
	}
    totalDeDocumentos.actualizarCap();
    ocultarBloqueEspera();
};

function calcularPosicion(tabla, posicion) {
	var posicionX = 0;
	for (var i = 0, size = tabla.fnGetData().length; (i != posicion ) &&  (i < size) ; i++) {
		posicionX += $((tablas.debitos.fnGetNodes(i))).height();
		
	}
	return posicionX;
};

cargarGrillaDocumentosCapDinamica = function (index) {
	if ($('#selectMotivo').val() == COMPENSACION && !$.isEmpty(datosTablas.mediosPagos) && index === INDICE_TAB_PREV) {
		if (datosTablas.mediosPagos[0].idMpTipoCredito == LIQUIDOPRODUCTO || datosTablas.mediosPagos[0].idMpTipoCredito == PROVEEDORES) {
			$('.prevDocumentosCapWrapper').css('display', 'block');
		} else {
			$('.prevDocumentosCapWrapper').css('display', 'none');
		}
	}else{
		$('.prevDocumentosCapWrapper').css('display', 'none');
	}
};
function mostrarBtnGenerarDocumentacionCompensaciones(motivo, estado, mediosPagos) {
	if (
		(COMPENSACION  === motivo) &&
		esEstadoCobroFinal(estado) &&
		esCobroCompensacionSap(mediosPagos)
	) {
		$('#divBtnCompensacion').css('display', 'block');
		if (
			mediosPagos[0].idMpTipoCredito === LIQUIDOPRODUCTO &&
			(
					mediosPagos[0].subTipo === 'RET_LIQ_PROD'||
					mediosPagos[0].subTipo === 'LIQ_PROD'
			)
		) {
			$('#btnVerCartaPie').show();
			$('#btnVerCarta').show();
		} else {
			$('#btnVerCartaPie').hide();
			$('#btnVerCarta').hide();
		}
		$('#btnVerResumenCompensacionPie').show();
	} else {
		$('#divBtnCompensacion').css('display', 'none');
		$('#btnVerResumenCompensacionPie').hide();
		$('#btnVerCartaPie').hide();
	}
};

function btnVerCartaOnClick() {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/generarPdfCartaCompensacion";
	$('#formCobro')[0].submit();
};
function btnVerResumenCompensacionOnClick() {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/generarPdfResumenCompensacion";
	$('#formCobro')[0].submit();
};
function btnEditarOtroMedioPagoOnClick(btnCheck) {
	// Como es permitido solo un medio de pago compensacion por Cobro
	var medioDePago = datosTablas.mediosPagos[0];
	// Desgrisar Agregar medio d epago
	// Seleccionar el tipo d emedio de pago // Liquido o provvedo y inaviltar
	// Muestro filtros y buisqueda
	// El cambio debe ir antes dque eliminar el medio de pago para que no se elimine los
	// Documentos caps seleccionados
	$("#selectMedioPago").val(medioDePago.idMpTipoCredito).change();

	// Limpio las tablas
	tablas.mediosPagos.fnDeleteRow($(btnCheck).parents('tr')[0]);
	datosTablas.mediosPagos = tablas.mediosPagos.fnGetData();
	totalDeDocumentos.actualizar();
	
	// Cargo datos a la grilla de documentos caps selecionados
	if (datosTablas.documentosCapsSeleccionados.length > 0) {
		tablas.documentosCapsSeleccionados.fnAddData(datosTablas.documentosCapsSeleccionados, true);
		tablas.documentosCapsSeleccionados.fnAdjustColumnSizing(false);
		actualizarInfoTablaSinPaginado(contadorRenglon(datosTablas.documentosCapsSeleccionados), "customPagCapSele");
		$('#btnEliminarTodosCaps').attr('disabled', false);
		$('button[id^="btnAgregarMedio"]').attr('disabled', false);
	} else {
		tablas.documentosCapPrev.fnAdjustColumnSizing(false);
	}
	// Completar los imput
	$('#textNroActa').val(medioDePago.nroCompensacion);
	if (medioDePago.idMpTipoCredito === LIQUIDOPRODUCTO) {
		$('#selecSubTipo').val(medioDePago.subTipo);
	}
}