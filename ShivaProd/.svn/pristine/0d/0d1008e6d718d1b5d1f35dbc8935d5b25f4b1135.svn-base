var tablas = {
	clientes : null,
	clientesSeleccionados : null
};
var datosTablas = {
		clientesSeleccionados : []
	};

var clienteDefault = {
		agenciaNegocio : "",
		codigoHolding : "",
		cuit : "",
		descripcionAgenciaNegocio : "",
		descripcionHolding : "",
		empresasAsociadas : "",
		idClienteLegado : "",
		origen : "",
		razonSocial : "",
		segmentoAgencia : ""
}
var BTN_NO_CLASS = 'btn-no-class';
var CSS_SELECTED_ROW = 'LightGray';
var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_BLANK_MENOS = '<i class="icon-minus bigger-120 icon-hidden"></i>';
var HTML_ICON_MENOS = '<i class="icon-minus bigger-120"></i>';

$(document).ready(function() {
	/*
	 * Req 67475 
	 * Se agrega combo de busqueda de clientes - u576779
	 */
	var SCROLL_Y = '300px';
	var DISPLAY_LENGTH = 100;
	
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
								return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link ' 
										+ data.idClienteLegado
										+ BTN_NO_CLASS
										+ data.idClienteLegado
										+ '" title="Eliminar cliente" idClienteLegado="'
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
						"mData" : "idClienteLegado"
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
					} ],
					'columnDefs' : [ // targets son los th con las class
										{
											type : 'string-numero',
											targets : 'numeroOrder'
										}]
		};

		
	var crearBuscadorClientes = function() {
		tablas.clientes = $("#clientes").dataTable(clientesSearchSettings);

		$('#clientes tbody').on('click', 'button', function() {
				var existe = false;
				var idClienteLegado = $(this).attr('idClienteLegado');
				$(tablas.clientesSeleccionados.fnGetData()).each(
						function(j, seleccionado) {
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
						
						guardarDatosDelClienteEnElForm(false);
						
					}
		});
		};

		function guardarDatosDelClienteEnElForm(limpiarClienteForm){
			
			if (limpiarClienteForm){
				
				$("#cuit").val('');
				$("#idClienteLegado").val('');
				$("#empresasAsociadas").val('');
				$("#razonSocial").val('');
				$("#origen").val('');
				$("#agenciaNegocio").val('');
				$("#descripcionAgenciaNegocio").val('');
				$("#codigoHolding").val('');
				$("#descripcionHolding").val('');
				$("#segmentoAgencia").val(''); 
				$("#codCliente").val('');
				
			} else {
				
				//Guardo los datos del cliente en el form
				$("#cuit").val(datosTablas.clientesSeleccionados[0].cuit);
				$("#idClienteLegado").val(datosTablas.clientesSeleccionados[0].idClienteLegado);
				$("#empresasAsociadas").val(datosTablas.clientesSeleccionados[0].empresasAsociadas);
				$("#razonSocial").val(datosTablas.clientesSeleccionados[0].razonSocial);
				$("#origen").val(datosTablas.clientesSeleccionados[0].origen);
				$("#agenciaNegocio").val(datosTablas.clientesSeleccionados[0].agenciaNegocio);
				$("#descripcionAgenciaNegocio").val(datosTablas.clientesSeleccionados[0].descripcionAgenciaNegocio);
				if(!isUndefinedNullDashEmptyOrFalse(datosTablas.clientesSeleccionados[0].codigoHolding)) {
					$("#codigoHolding").val(datosTablas.clientesSeleccionados[0].codigoHolding);
				} else {
					$("#codigoHolding").val('');
				}
				$("#descripcionHolding").val(datosTablas.clientesSeleccionados[0].descripcionHolding);
				$("#segmentoAgencia").val(datosTablas.clientesSeleccionados[0].segmentoAgencia);
				if (!$.isEmpty($("#codCliente").val())) {
					$("#textCriterio").val($("#codCliente").val());
				}
			}
			
			//Actualizo la variable clienteDefault
			clienteDefault.agenciaNegocio = $("#agenciaNegocio").val();
			clienteDefault.codigoHolding = $("#codigoHolding").val();
			clienteDefault.cuit = $("#cuit").val();
			clienteDefault.descripcionAgenciaNegocio = $("#descripcionAgenciaNegocio").val();
			clienteDefault.descripcionHolding = $("#descripcionHolding").val();
			clienteDefault.empresasAsociadas = $("#empresasAsociadas").val();
			clienteDefault.idClienteLegado = $("#idClienteLegado").val();
			clienteDefault.origen = $("#origen").val();
			clienteDefault.razonSocial = $("#razonSocial").val();
			clienteDefault.segmentoAgencia = $("#segmentoAgencia").val();
	
			$("#codCliente").val(eliminaCerosAIzquierda($("#idClienteLegado").val()));
		}
		
		function eliminaCerosAIzquierda(id) {
			var lastIndex = -1;
			var size = id.length;
			for (var i= 0; i < size; i++) {
				if (id[i] != '0') {
					break;
				} else {
					lastIndex = i;
				}
			}
			if (lastIndex >= 0) {
				return id.substring(lastIndex + 1, size);
			}
			return id;
		};
		crearBuscadorClientes();
		$('#selectCriterio').val("BUSQUEDA_POR_CLIENTE_LEGADO");
		
		$('#btnAceptar').click(function() {
			$('#bloqueEspera').trigger('click');
			var listaErrores = [];
			$("#segmento").val(obtenerDescripcionSelect("selectSegmento"));
			listaErrores = validarFormValor();
			if (!$.isEmpty(listaErrores)) {
				var sizeErrores = listaErrores.length;
				for (var u = 0; u < sizeErrores; u++) {
					$('#'+listaErrores[u].campo).text(listaErrores[u].mensaje);
				}
				ocultarBloqueEspera();
			} else {
				
				$('#operation').val(crearValor);
				$("#adjuntando").val(false);
				$('#formAlta')[0].submit();
			}
			//ocultarBloqueEspera();
		});
		
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
					"url" : "conciliacion-valor-alta/busquedaClientes",
					"dataType" : "json",
					"data" : JSON.stringify(eval(clienteFiltro)),
					"contentType" : "application/json",
					"mimeType" : "application/json",
					"success" : function(result) {
						if (result.success) {
							tablas.clientes.fnClearTable();
							tablas.clientes.fnAdjustColumnSizing(false);
							
		
							if (result.clientes.length > 0) {
								tablas.clientes.fnAddData(result.clientes,true);
		
								aparienciaButton.ocultarButtonClientesSeleccionados(tablas.clientes,tablas.clientesSeleccionados);
								
								ocultarBloqueEspera();
							}
						} else {
							$(result.campoError).text(result.descripcionError);
							ocultarBloqueEspera();
						}
						
					}
				});

		});
		
		$('#clientesSeleccionados tbody').on('click', 'button',	function() {
			var idClienteLegado = $(this).attr('idClienteLegado');

			$(tablas.clientesSeleccionados.fnGetData()).each( function(j,cliente) {
				if (cliente.idClienteLegado == idClienteLegado 
						&& $.isEmpty(datosTablas.listaValores)) {
					tablas.clientesSeleccionados.fnDeleteRow(j);
					datosTablas.clientesSeleccionados.length = 0;
					return false;
				}
			});
			
			$(tablas.clientes.fnGetData()).each( function(j,cliente) {
				aparienciaButton.descubrirButtonPlus(tablas.clientes, cliente.idClienteLegado,j);
			});
			tablas.clientesSeleccionados.fnAdjustColumnSizing(false);
			
			guardarDatosDelClienteEnElForm(true);
		
		});
		
//		if (clienteJsonDefault.success) {
//			tablas.clientes.fnClearTable();
//			tablas.clientes.fnAdjustColumnSizing(false);
//			
//
//			if (clienteJsonDefault.clientes.length > 0) {
//				tablas.clientes.fnAddData(clienteJsonDefault.clientes,true);
//
//				aparienciaButton.ocultarButtonClientesSeleccionados(tablas.clientes,tablas.clientesSeleccionados);
//				
//				ocultarBloqueEspera();
//			}
//		} else {
//			$(clienteJsonDefault.campoError).text(clienteJsonDefault.descripcionError);
//			ocultarBloqueEspera();
//		}
//		$('.btn-no-class'+idClienteLegado).click();
		
		//Actualizo la variable clienteDefault
		clienteDefault.agenciaNegocio = $("#agenciaNegocio").val();
		clienteDefault.codigoHolding = $("#codigoHolding").val();
		clienteDefault.cuit = $("#cuit").val();
		clienteDefault.descripcionAgenciaNegocio = $("#descripcionAgenciaNegocio").val();
		clienteDefault.descripcionHolding = $("#descripcionHolding").val();
		clienteDefault.empresasAsociadas = $("#empresasAsociadas").val();
		clienteDefault.idClienteLegado = $("#idClienteLegado").val();
		clienteDefault.origen = $("#origen").val();
		clienteDefault.razonSocial = $("#razonSocial").val();
		clienteDefault.segmentoAgencia = $("#segmentoAgencia").val();
		$("#textCriterio").val($("#codCliente").val());
		
		if (!$.isEmpty(clienteDefault.idClienteLegado)){
			tablas.clientesSeleccionados.fnAddData([clienteDefault],true);
			datosTablas.clientesSeleccionados.push(clienteDefault);
		}
		
});

function validarFormValor() {

	var cliente = null;
	if (!$.isEmpty(datosTablas.clientesSeleccionados)){
		cliente = {
				idCobroCliente: "",
				idClienteLegado: datosTablas.clientesSeleccionados[0].idClienteLegado,
				empresasAsociadas: datosTablas.clientesSeleccionados[0].empresasAsociadas,
				razonSocial: datosTablas.clientesSeleccionados[0].razonSocial,
				origen: datosTablas.clientesSeleccionados[0].origen,
				descripcionHolding: datosTablas.clientesSeleccionados[0].descripcionHolding,
				codigoHolding: datosTablas.clientesSeleccionados[0].codigoHolding,
				cuit: datosTablas.clientesSeleccionados[0].cuit,
				agenciaNegocio: datosTablas.clientesSeleccionados[0].agenciaNegocio,
				descripcionAgenciaNegocio: datosTablas.clientesSeleccionados[0].descripcionAgenciaNegocio,
				segmentoAgencia: datosTablas.clientesSeleccionados[0].segmentoAgencia,
				idClientePerfil: datosTablas.clientesSeleccionados[0].idClientePerfil,
				idProvincia: datosTablas.clientesSeleccionados[0].idProvincia,
				clienteOrigen: datosTablas.clientesSeleccionados[0].clienteOrigen
			};
	}
	
	var valorDto = {
			idTipoValor : $("#idTipoValor").val(), 
			fechaEmision : $("#fechaEmision").val(),
			fechaEmisionAux : $("#fechaEmision").datepicker("getDate"),
			fechaNotificacionDisponibilidadRetiroValor : $("#fechaNotificacionDisponibilidadRetiroValor").val(),
			fechaNotificacionDisponibilidadRetiroValorAux : $("#fechaNotificacionDisponibilidadRetiroValor").datepicker("getDate"),
			cliente : cliente,
			segmento : $("#segmento").val(),
			idAcuerdo : $("#selectAcuerdo").val(),
			idBancoDeposito : $("#selectBancoDeposito").val(),
			idNroCuenta : $("#selectNumeroCuenta").val(),
			observaciones : $("#nuevaObservacion").val(),
			idOrigen : $("#origen").val()
			};
	var listaErrores = [];
	var validador = crearValidadorAltaValorXAvc(valorDto);
	var metodosValidador = Object.keys(validador);
	
	var size = metodosValidador.length;
	for (var i = 0; i < size; i++) {
		var error = validador[metodosValidador[i]](valorDto);
		if (!$.isEmpty(error)) {
			listaErrores.push(error);
		}
	}
	return listaErrores;
}
//Se llama cuando se realiza una nueva busqueda y ya hay clientes
//seleccionados
var aparienciaButton = {
	// Button para todas las tablas
	'ocultarButtonPlus' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index); // cambio el font color de los
											// clientes seleccionados
		$(aRow).children().css('color', CSS_SELECTED_ROW);
		if (isNotButton === undefined) {
			$('.' + BTN_NO_CLASS + id).attr('disabled', true).html(
					HTML_ICON_BLANK);
		}
	},
	'descubrirButtonPlus' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index);

		if (isNotButton !== undefined) {
			if (isNotButton !== 'renglon') {
				$(aRow).children().css('color', '');
				if ('semaforo-rojo' === isNotButton) {
					$('.' + BTN_NO_CLASS + id + '.semaforo-rojo').attr(
							'disabled', false).html(HTML_ICON_BLANK);
				} else {
					$('.' + BTN_NO_CLASS + id + '.' + isNotButton).attr(
							'disabled', false).html(HTML_ICON_PLUS);
				}
			}
		} else {
			$(aRow).children().css('color', '');
			$('.' + BTN_NO_CLASS + id).attr('disabled', false).html(
					HTML_ICON_PLUS);
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
//					return false;
				}
			});
		});
	},
	'ocultarButtonMenos' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index); // cambio el font color de los
											// clientes seleccionados
		$(aRow).children().css('color', CSS_SELECTED_ROW);
		if (isNotButton === undefined) {
			$('.' + id + BTN_NO_CLASS + id).attr('disabled', true).html(
					HTML_ICON_BLANK_MENOS);
		}
	},
	'descubrirButtonMenos' : function(tabla, id, index, isNotButton) {
		var aRow = tabla.fnGetNodes(index);

		if (isNotButton !== undefined) {
			if (isNotButton !== 'renglon') {
				$(aRow).children().css('color', '');
				if ('semaforo-rojo' === isNotButton) {
					$('.' + id + BTN_NO_CLASS + id + '.semaforo-rojo').attr(
							'disabled', false).html(HTML_ICON_BLANK_MENOS);
				} else {
					$('.' + id + BTN_NO_CLASS + id + '.' + isNotButton).attr(
							'disabled', false).html(HTML_ICON_MENOS);
				}
			}
		} else {
			$(aRow).children().css('color', '');
			$('.' + id + BTN_NO_CLASS + id).attr('disabled', false).html(
					HTML_ICON_MENOS);
		}
	},
	'descubrirButtonMenosClienteSeleccionado' : function(tabla, id) {
		$(tabla.fnGetData()).each(function(j, cliente) {

				aparienciaButton.descubrirButtonMenos(tabla, id, j);
				return false;
				


		});
	},
	'ocultarButtonMenosClientesSeleccionados' : function(tablaSeleccionados) {
		
		$(tablaSeleccionados.fnGetData()).each(function(j, clienteSeleccionado) {
			

					aparienciaButton.ocultarButtonMenos(tablaSeleccionados, clienteSeleccionado.idClienteLegado, j);
					return false;

		});
	}
	
};
