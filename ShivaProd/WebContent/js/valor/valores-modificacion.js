var tablas = {
	clientes : null,
	clientesSeleccionados : null
};

var datosTablas = {
		clientesSeleccionados : []
};

var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100;
var BTN_NO_CLASS = 'btn-no-class';
var CSS_SELECTED_ROW = 'LightGray';
var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_BLANK_MENOS = '<i class="icon-minus bigger-120 icon-hidden"></i>';
var HTML_ICON_MENOS = '<i class="icon-minus bigger-120"></i>';




$(document).ready(function() {	
/**-----------------------Tabla Clientes sin modificacion----------------**/
	var tablas = {
			prevCliente: null,
			clientes: null,
			clientesSeleccionados: null
			
	    };

		var prevCliente = {
				dom : 'rt',
				"sScrollX" : true,
				"scrollY" : SCROLL_Y,
				"bScrollCollapse" : true,
				"iDisplayLength" : DISPLAY_LENGTH,
				"bSortClasses" : false,
				"aoColumns" : [
						{
							"mData" : "cuit"
						},
						{
							"mData" : "idClienteLegadoString"
						},
						{
							"mData" : "empresasAsociadas"
						},
						{
							"mData" : "razonSocial"
						},
						{
							"mData" : "origen"
						},
						{
							"mData" : "descripcionHolding"
						},
						{
							"mData" : "agenciaNegocio"
						},
						{
							"mData" : "segmentoAgencia"
						}
				]
			};
		
		tablas.prevCliente = $("#prevCliente").dataTable(prevCliente);
		
/**-----------------------FIN Tabla Clientes sin modificacion----------------**/
/******************************************************************************/
	var clientesSearchSettings = {
		dom : '<"agregarTodosClientes">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"bProcessing" : true,
		"bPaginate" : false,
		"bStateSave" : false,
		"bSortClasses" : false,
		"order": [[ 2, "asc"]],
		"iDisplayLength" : DISPLAY_LENGTH,
		"buttons" : [ {
			extend : 'excelHtml5',
			text : 'Excel',
			title : "Cobro_Clientes_Sin_seleccionar",
			className : 'excelbtn',
			mColumns : "visible"
		} ],
		"aoColumns" : [
		{
			"targets" : -1,
			"searchable" : false,
			"bSortable" : false,
			"data" : null,
			"render" : function(data, type, row) {
				if (type === 'display') {
					return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link  '
							+ BTN_NO_CLASS
							+ data.idClienteLegado
							+ '" title="Agregar cliente" idClienteLegado="'
							+ data.idClienteLegado
							+ '"><i class="icon-plus bigger-120"></i></button></div>';
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
		}
	]};
//numeroOrder
	// Tabla de clientes seleccionados
	var selClientesSettings = {
		dom : '<"eliminarTodosClientes">Bfrtip',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : 10,
		"iDisplayStart" : 0,
		"order": [[ 2, "asc"]],
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
			$('#btnEliminarTodos').attr('disabled',
					!(oSettings.aoData.length > 0));
		},
		"aoColumns" : [
				{
					"targets" : -1,
					"searchable" : false,
					"bSortable" : false,
					"data" : null,
					"render" : function(data, type, row) { // <---------
															// Editable
															// checkbox
						if (type === 'display') {
							return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar cliente" idClienteLegado="'
									+ data.idClienteLegado
									+ '"><i class="icon-minus bigger-120"></i></button></div>';
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
					"mData" : "idClienteLegadoString"
				}, {
					"mData" : "empresasAsociadas"
				}, {
					"mData" : "razonSocial"
				}, {
					"mData" : "origen"
				}, {
					"mData" : "segmentoAgencia"
				},  {
					"mData" : "descripcionHolding"
				}, {
					"mData" : "agenciaNegocio"
				},{
					"mData" : "idClienteLegado",
					"visible" : false,
					"searchable" : false
				}],
				'columnDefs' : [ // targets son los th con las class
									{
										type : 'string-numero',
										targets : 'numeroOrder'
									}]
	};

	
var crearBuscadorClientes = function() {
	tablas.clientes = $("#clientes").dataTable(clientesSearchSettings);

	// fixedColumns(tablas.clientes, 1, 0);
	
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
							
							var aData = tablas.clientes.fnGetData()[j];
	
							tablas.clientesSeleccionados.fnAddData([aData],true);
							datosTablas.clientesSeleccionados.push(aData);
	
							return false;
						}
					});
					
					$(tablas.clientes.fnGetData()).each(function(j, cliente) {
						aparienciaButton.ocultarButtonPlus(tablas.clientes,cliente.idClienteLegado,j);
					});
					
					$("#cuitCliente").val(datosTablas.clientesSeleccionados[0].cuit);
					$("#idClienteLegado").val(datosTablas.clientesSeleccionados[0].idClienteLegado);
					$("#empresasAsociadas").val(datosTablas.clientesSeleccionados[0].empresasAsociadas);
					$("#razonSocialCliente").val(datosTablas.clientesSeleccionados[0].razonSocial);
					$("#origenCliente").val(datosTablas.clientesSeleccionados[0].origen);
					$("#agenciaNegocio").val(datosTablas.clientesSeleccionados[0].agenciaNegocio);
					$("#descripcionAgenciaNegocioCliente").val(datosTablas.clientesSeleccionados[0].descripcionAgenciaNegocio);
					if(!isUndefinedNullDashEmptyOrFalse(datosTablas.clientesSeleccionados[0].codigoHolding)) {
						$("#codigoHolding").val(datosTablas.clientesSeleccionados[0].codigoHolding);
					} else {
						$("#codigoHolding").val('');
					}
					$("#descripcionHoldingCliente").val(datosTablas.clientesSeleccionados[0].descripcionHolding);
					$("#segmentoAgencia").val(datosTablas.clientesSeleccionados[0].segmentoAgencia);	
				}
	});
	};

	crearBuscadorClientes();
	
	tablas.clientesSeleccionados = $("#clientesSeleccionados").dataTable(selClientesSettings);
	
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
		
// 		if(datosTablas.clientesSeleccionados.length == 0){
			
			var criterio = $('#selectCriterio').val();
			var busqueda = $('#textCriterio').val();
			criterio = $.trim(criterio);
			busqueda = $.trim(busqueda);
	
			$("span.error").text("");
			var clienteFiltro = {
				criterio : criterio,
				busqueda : busqueda
			};
	
			$.ajax({
				"type" : "POST",
				"url" : "valores-aviso-alta/busquedaClientes",
				"dataType" : "json",
				"data" : JSON.stringify(eval(clienteFiltro)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						tablas.clientes.fnClearTable();
						tablas.clientes.fnAdjustColumnSizing(false);
						
	
						if ( result.clientes.length > 0 ) {
							tablas.clientes.fnAddData(result.clientes,true);
							
							if( tablas.clientesSeleccionados.fnGetData().length > 0 ){
								$(tablas.clientes.fnGetData()).each( function(j,cliente) {
									aparienciaButton.ocultarButtonPlus(tablas.clientes,cliente.idClienteLegado,j);
								});
							}
							
							ocultarBloqueEspera();
						}
					} else {
						$(result.campoError).text(result.descripcionError);
						ocultarBloqueEspera();
					}
					
				}
			});
// 		}
	});
	
	$('#clientesSeleccionados tbody').on('click', 'button',	function() {
		var idClienteLegado = $(this).attr('idClienteLegado');
		
		$(tablas.clientesSeleccionados.fnGetData()).each( function(j,cliente) {
			if (cliente.idClienteLegado == idClienteLegado) {
				tablas.clientesSeleccionados.fnDeleteRow(j);
				datosTablas.clientesSeleccionados.length = 0;
				return false;
			}
		});

		$(tablas.clientes.fnGetData()).each( function(j,cliente) {
			aparienciaButton.descubrirButtonPlus(tablas.clientes, cliente.idClienteLegado,j);
		});
		
		tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
		
		$("#cuitCliente").val('');
		$("#idClienteLegado").val('');
		$("#empresasAsociadas").val('');
		$("#razonSocialCliente").val('');
		$("#origenCliente").val('');
		$("#agenciaNegocio").val('');
		$("#descripcionAgenciaNegocioCliente").val('');
		$("#codigoHolding").val('');
		$("#descripcionHoldingCliente").val('');
		$("#segmentoAgencia").val('');
	
	});
/******************************************************************************/
});
//Se llama cuando se realiza una nueva busqueda y ya hay clientes
//seleccionados
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
	'ocultarButtonMinus' : function(tabla, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index); // cambio el font color de los
		// clientes seleccionados
		$(aRow).children().css('color', CSS_SELECTED_ROW);
		if (isNotButton === undefined) {
			$('#clientesSeleccionados button').attr('disabled', true).html(HTML_ICON_BLANK_MINUS);
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
	'descubrirButtonMinus' : function(tabla, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index);
		
		if (isNotButton === undefined) {
			$(aRow).children().css('color', '');
			$('#clientesSeleccionados button').attr('disabled', false).html(HTML_ICON_MINUS);
		}
	},
	'descubrirButtonPlusCliente' : function(tabla, id) {
		$(tabla.fnGetData()).each(function(j, cliente) {
			if (cliente.idClienteLegado == id ){
				aparienciaButton.descubrirButtonPlus(tabla, id, j);
				return false;
				
			} else if (cliente.idClienteLegado != id 
					&& datosTablas.clientesSeleccionados.length == 0) {
				aparienciaButton.descubrirButtonPlus(tabla, cliente.idClienteLegado, j);
				return false;
			}

		});
	},
	'ocultarButtonClientesSeleccionados' : function(tabla, tablaSeleccionados) {
		
		$(tablaSeleccionados.fnGetData()).each(function(j, clienteSeleccionado) {
			
			$(tabla.fnGetData()).each(function(i, cliente) {
				if (clienteSeleccionado.idClienteLegado == cliente.idClienteLegado
						|| (clienteSeleccionado.idClienteLegado != cliente.idClienteLegado 
								&& datosTablas.clientesSeleccionados.length > 0)) {
					aparienciaButton.ocultarButtonPlus(tabla, cliente.idClienteLegado, i);
					return false;
				}
			});
		});
	}
};


function mostarTipoValor(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;

	switch (tipoValorSeleccionado) {
	case BOLETA_DEPOSITO_CHEQUE: 
		bloqueOrigen.style.display = 'block';
		bloqueOrigenError.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueNroDocumentoContableError.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueCheckRecibido.style.display = 'none';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'none';
		}
		if ($('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
			//bloqueFechaNotificacionValor.style.display = 'block';
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		break;
	case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:  
		bloqueOrigen.style.display = 'block';
		bloqueOrigenError.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueNroDocumentoContableError.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueCheckRecibido.style.display = 'none';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'none';
		}
		if ($('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
			//bloqueFechaNotificacionValor.style.display = 'block';
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		break;
	case BOLETA_DEPOSITO_EFECTIVO:
		bloqueOrigen.style.display = 'block';
		bloqueOrigenError.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueNroDocumentoContableError.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueCheckRecibido.style.display = 'none';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'none';
		}
		if ($('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
			//bloqueFechaNotificacionValor.style.display = 'block';
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		break;
	case CHEQUE:
		bloqueOrigen.style.display = 'none';
		bloqueOrigenError.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueNroDocumentoContableError.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'block';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		//bloqueFechaNotificacionValor.style.display = 'block';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		$('#space').hide();
		break;
	case CHEQUE_DIFERIDO:
		bloqueOrigen.style.display = 'none';
		bloqueOrigenError.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueNroDocumentoContableError.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'block';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		//bloqueFechaNotificacionValor.style.display = 'block';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		$('#space').show();
		break;
	case EFECTIVO: //Efectivo
		bloqueOrigen.style.display = 'none';
		bloqueOrigenError.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueNroDocumentoContableError.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'block';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		$('#space').hide();
		break;
	case TRANSFERENCIA: //Transferencia
		bloqueOrigen.style.display = 'none';
		bloqueOrigenError.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueNroDocumentoContableError.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'block';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'none';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'block';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		$('#space').hide();
		break;
	case INTERDEPOSITO: //Interdeposito
		bloqueOrigen.style.display = 'block';
		bloqueOrigenError.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueNroDocumentoContableError.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'block';
		bloqueTipoImpuesto.style.display = 'none';
// 		bloqueChequeReemplazar.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'none';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'none';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		bloqueFechaEmisionCheque.style.display = 'none';
		$('#space').hide();
		break;
	case RETENCION_IMPUESTO: //Retencion / Impuesto
		bloqueOrigen.style.display = 'none';
		bloqueOrigenError.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueNroDocumentoContableError.style.display = 'none';
		bloqueAcuerdo.style.display = 'none';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'block';
// 		bloqueChequeReemplazar.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueComprobantes.style.display = 'block';
		bloqueCheckRecibido.style.display = 'none';
		if (TIPOMODIFANARECHA) {
			bloqueComprobantesAgregar.style.display = 'block';
		}
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		bloqueFechaEmisionCheque.style.display = 'none';
		$('#space').hide();
		break;
	}
};