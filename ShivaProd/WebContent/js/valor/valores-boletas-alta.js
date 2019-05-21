var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100;
var BTN_NO_CLASS = 'btn-no-class';
var CSS_SELECTED_ROW = 'LightGray';
var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_BLANK_MINUS = '<i class="icon-minus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_MINUS = '<i class="icon-minus bigger-120"></i>';
var tablas = {
	listaValores: null,
	comprobantes: null,
	clientes : null,
	clientesSeleccionados : null
};
var edicionValorDto = null;
var datosTablas = {
	listaValores: [],
	comprobantes: [],
	clientesSeleccionados : []
};
var combos = {
	acuerdo : null,
	banco : null,
	bancoCuenta : null,
	optionBacia: null,
	edbancoOrigen:null,
	bancoOrigen: null
};
var acuerdoBancoCuenta = null;

var combosAcuerdoBancoDepositoCuenta = {
	acuerdo : null,
	bancoDeposito: null,
	bancoCuenta : null
};
$(document).ready(function() {
	siEsCajeroPagador();
	
	if (!$.isEmpty($('#selectSegmento').val())){
		$('#bloqueEspera').trigger('click');
		segPrev = $('#selectSegmento').val();
		$.ajax({
			"dataType" : 'json',
			"type" : "GET",
			"url" : 'valor/buscarCopropietarios',
			"data" : {
				idEmpresa : $("#selectEmpresa").val(),
				idSegmento : segPrev
			},
			"success" : function(result) {
				var options = [{
					text : leyendaComboSeleccionar,
					value : ''
				}];
				$.each(result, function(index, option) {
					options.push(option);
				});
				$("#selectCopropietario").replaceOptions(options);
				
				completarComboTipoValor();
				ocultarBloqueEspera();
			}
		});
	}
	
	mostarTipoValor(document.getElementById('selectTipoValor'));
	mostrarRecibo(document.getElementById('selectOrigen'));
/******************************************************************************/
	$('#inputFechaVencimiento').datepicker();
	$('#inputFechaIngresoRecibo').datepicker();
	$('#inputFechaNotificacionValor').datepicker();
	$('#inputFechaEmisionCheque').datepicker();

/******************************************************************************/
	$('#checkEnviarMailBoleta').click(function(){
		document.getElementById("checkImprimirBoleta").checked=false;
		document.getElementById("observacionMail").disabled=false;
		$('#emailObligatorio').show();
	});
	$('#checkImprimirBoleta').click(function(){
		document.getElementById("checkEnviarMailBoleta").checked=false;
		document.getElementById("observacionMail").disabled=true;
		document.getElementById("observacionMail").value = "";
		$('#emailObligatorio').hide();
	});
/******************************************************************************/
	$("#selectEmpresa").change(function () {
	});
/******************************************************************************/
	$("#selectSegmento").focus(function() {
		segPrev = this.value;
	}).change(function() {
		if ($.isEmpty(this.value)) {
			var options = [{
				text : 'Seleccione un item...',
				value : ''
			}];
			limpiarTodo();
			//mostarTipoValor('XX');
			//completarComboTipoValor();
			$("#selectCopropietario").replaceOptions(options);
			$("#selectCopropietario").val(options[0].value);
		} else if (this.value != segPrev) {
			$('#bloqueEspera').trigger('click');
			segPrev = this.value;
			$.ajax({
				"dataType" : 'json',
				"type" : "GET",
				"url" : 'valor/buscarCopropietarios',
				"data" : {
					idEmpresa : $("#selectEmpresa").val(),
					idSegmento : this.value
				},
				"success" : function(result) {
					var options = [{
						text : leyendaComboSeleccionar,
						value : ''
					}];
					$.each(result, function(index, option) {
						options.push(option);
					});
					$("#selectCopropietario").replaceOptions(options);
					
					completarComboTipoValor();
					//completarComboOrigen();
					ocultarBloqueEspera();
				}
			});
		}
		$('#inputFechaNotificacionValor').change();
	});
/******************************************************************************/	
	$("#selectOrigen").change(function () {
		$('#bloqueEspera').trigger('click');
		//limpiarCamposByCambioTipoAviso();
		// Si el tipo valor es Retencion/Impuesto no tiene combos banco
		completarCombosAcuerdoBancoDepositoCuenta(this.value);
		clearSelectInput('bloqueRecibo');
		$('#inputFechaNotificacionValor').val('');
		if (!$.isEmpty(edicionValorDto)) {
			llenarFormEdicion();
		}
		$('#inputFechaNotificacionValor').change();
		ocultarBloqueEspera();
	});
	
	$("#selectTipoValor").change(function () {
		$('#bloqueEspera').trigger('click');
		//limpiarCamposByCambioTipoAviso();
		// Si el tipo valor es Retencion/Impuesto no tiene combos banco
		completarCombosAcuerdoBancoDepositoCuenta(null);
		$('#inputFechaNotificacionValor').val('');
		completarComboOrigen(this.value);
		mostrarConBorrar(this);
		if (!$.isEmpty(edicionValorDto)) {
			llenarFormEdicion();
		}
		$('#inputFechaNotificacionValor').change();
		ocultarBloqueEspera();
	});
	
	$('#inputFechaNotificacionValor').change(function(){
		if ( isUndefinedNullDashEmptyOrFalse(this.value) ) {
			$('#comprobanteCruz').hide();
		} else {
			$('#comprobanteCruz').show();
		}
	});
/******************************************************************************/
	customCombobox("custom.combobox2");

	$("#selectBancoDeposito").combobox2();
	$("#toggle").click(function() {
		$("#combobox2").toggle();
	});
	$('#selectAcuerdo').change(function () {
		vaciarCombo('selectBancoDeposito');
		vaciarCombo('selectNumeroCuenta');
		if ($.isEmpty(this.value)) {
			if ($('#selectAcuerdo option').length != combosAcuerdoBancoDepositoCuenta.acuerdo) {
				vaciarCombo('selectAcuerdo');
				$('#selectAcuerdo').append(combosAcuerdoBancoDepositoCuenta.acuerdo);
			}
			$('#selectBancoDeposito').append(combosAcuerdoBancoDepositoCuenta.banco);
			$("#selectNumeroCuenta").append(combosAcuerdoBancoDepositoCuenta.bancoCuenta);
			$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
			$('#selectNumeroCuenta').val('');
			$('#selectAcuerdo').val('');
		} else {
			crearOpcionesByAcuerdo(this.value);
		}
	});

	$('#selectBancoDeposito').combobox2({
		'': function(event, item) {
			vaciarCombo('selecselecttAcuerdo');
			vaciarCombo('selectNumeroCuenta');
			crearOpcionesByBanco(this.value);
		},
		'vacio' : function(event) {
			vaciarCombo('selectAcuerdo');
			vaciarCombo('selectNumeroCuenta');
			vaciarCombo('selectBancoDeposito');
			$('#selectAcuerdo').append(combosAcuerdoBancoDepositoCuenta.acuerdo);
			$("#selectNumeroCuenta").append(combosAcuerdoBancoDepositoCuenta.bancoCuenta);
			$("#selectBancoDeposito").append(combosAcuerdoBancoDepositoCuenta.banco);
			$('#selectAcuerdo').val('');
			$("#selectNumeroCuenta").val('');
		},
	});
	$('#selectNumeroCuenta').change(function () {
		vaciarCombo('selectBancoDeposito');
		vaciarCombo('selectAcuerdo');
		
		if ($.isEmpty(this.value)) {
			if ($('#selectNumeroCuenta option').length != combosAcuerdoBancoDepositoCuenta.acuedo) {
				vaciarCombo('selectNumeroCuenta');
				$('#selectNumeroCuenta').append(combosAcuerdoBancoDepositoCuenta.acuerdo);
				
			}
			$('#selectBancoDeposito').append(combosAcuerdoBancoDepositoCuenta.banco);
			$("#selectAcuerdo").append(combosAcuerdoBancoDepositoCuenta.bancoCuenta);
			$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
			$('#selectAcuerdo').val('');
			$('#selectNumeroCuenta').val('');
		} else {
			var selectOption = this.value;
			vaciarCombo('selectNumeroCuenta');
			selectOpcionByCuenta(selectOption);
		}
	});
	
/*****************************************************************************/
	combos.bancoOrigen = $('#codigoBanco option');
	$("#descripcionBanco").combobox2();
	$('#codigoBanco').change(function () {
		$('#descripcionBanco').combobox2("refresh", $('#codigoBanco option:selected').attr('idAgrupador'), 'descripcionBanco');
		if ($.isEmpty(this.value)) {
			vaciarCombo('codigoBanco');
			$('#codigoBanco').append(combos.bancoOrigen);
			$('#codigoBanco').val("");
		}
	});
	$('#descripcionBanco').combobox2({
		'select': function(event, item) {
			vaciarCombo('codigoBanco');
			var optios = crearOpcionesByAgrupador(item.item.value, "codigoBanco");
			if (optios.length > 1) {
				$('#codigoBanco').append(combos.bancoOrigen[0]);
			}
			$('#codigoBanco').append(optios);
		},
		'vacio': function(event) {
			vaciarCombo('codigoBanco');
			$('#codigoBanco').append(combos.bancoOrigen);
			$('#codigoBanco').val("");
		}
	});
/*****************************************************************************/
/* Configuracion de tabla "listaValores"
 * 
 *****************************************************************************/
	tablas.listaValores = $('#listaValores').dataTable( {
		dom: 'frtip' ,
		"scrollX": true,
		"scrollY": '500px',
		"bScrollCollapse": true,
		"aoColumns" : [
			{
				"mData" : "tipoValor",											//<td>${val.tipoValor}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "idAcuerdo",											//<td>${val.idAcuerdo}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "bancoDeposito",										//<td>${val.bancoDeposito}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "numeroCuenta",										//<td>${val.numeroCuenta}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "importe",											//<td>${val.importe}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "bancoOrigen",										//<td>${val.bancoOrigen}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			},																	//<td>${val.reciboPreImpreso}</td>
			  {
				"mData" : "reciboPreImpreso",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaIngresoRecibo",									// <td>${val.fechaIngresoRecibo}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, 																	//<td>${val.origen}</td>
			{
				"mData" : "origen",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			},{
				"mData" : "motivo",												//	<td>${val.motivo}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "numeroCheque",										//<td>${val.numeroCheque}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaVencimiento",									//<td>${val.fechaVencimiento}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "boletaImpresaEstado",								//			<td>${val.boletaImpresaEstado}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "boletaEnviadaMailEstado",										//<td>${val.boletaEnviadaMailEstado}</td>
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			},{
				"targets" : -1,
				"data" : null,
				"defaultContent" : '',
				"render" : function(data, type, row) {
					if (type === 'display') {
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnEditar" onclick="editar(\''+ data.id + '\');" class="btn btn-xs btn-link" type="button" title="Editar"> <i class="icon-edit bigger-120"></i></button><a> </a>	<button id="btnEliminar" class="btn btn-xs btn-link" type="button" title="Eliminar de la lista" onclick="eliminar(\'' + data.id + '\');"><i class="icon-trash bigger-120"></i></button></div>';
					}
					return data;
				},
				"searchable" : false,
				"bSortable" : false
			}],
		'columnDefs' : [ {
			type : 'latam_date',
			targets : 'date'
		} ]
	});
/*****************************************************************************/
	$('#btnAgregarValor').click(function() {
		$('#bloqueEspera').trigger('click');
		var aviso = {
				"idValor" : 1213213
			};
			$.ajax({
				"type" : "POST",
				"url" : "isAliveValores",
				"dataType" : "json",
				"data" : JSON.stringify(eval(aviso)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
//					console.log('isAliveValores');
				},
				"error" : function() {
					beforeUnload.off();
					// Para que false
					$('#formCobro')[0].action = urlAsynchronousHTTP + "/valores-boletas-alta";
					$('#formCobro')[0].submit();
				}
			});
		var valorDto = procesarFormValor();
		var listaErrores = [];
		$('#bloqueEspera').trigger('click');
		$('#divAlertErrorFoot').hide();
		$('#alertErrorFoot').val('');
		$('.error').text('');
		$('#alertError').text('');
		$('#divAlertError').hide();
		listaErrores = validarFormValor(valorDto);
		if (!$.isEmpty(listaErrores)) {
			var sizeErrores = listaErrores.length;
			for (var u = 0; u < sizeErrores; u++) {
				$('#'+listaErrores[u].campo).text(listaErrores[u].mensaje);
			}
		} else {
			var error = validarDuplicidad(datosTablas.listaValores, valorDto);
			if ($.isEmpty(error)) {
				valorDto.id = generarIdDinamico(valorDto.idTipoValor);
				datosTablas.listaValores.push(valorDto);
				$('#btnBuscarClientes').attr('disabled',true);
				aparienciaButton.ocultarButtonMinus(tablas.clientesSeleccionados,0);
				tablas.listaValores.fnAddData([ valorDto ], true);
				
				tablas.listaValores.fnSort([[0, 'asc']]);
				limpiarFormAlAgregarValor();
				
				var size = datosTablas.listaValores.length;
				$('#generarConstancia').attr('disabled', true);
				for (var i = 0; i < size; i++) {
					if (datosTablas.listaValores[i].idOrigen === '4') {
						$('#generarConstancia').attr('disabled', false);
						break;
					}
				}
				if (!$('#mantenerComprobantesAdjuntosVal').is(':checked')) {
					tablas.comprobantes.fnClearTable();
					datosTablas.comprobantes = [];
				}
				$('#inputFechaNotificacionValor').change();
			} else {
				$('#alertError').text(error.mensaje);
				$('#divAlertError').show();
			}
		}
		ocultarBloqueEspera();
		
	 });
/*****************************************************************************/
	function onClickBtnAceptar() {
		$('#bloqueEspera').trigger('click');
		$('#btnAceptar').attr('disabled', true);
		var valoresDto = {
			valorDto: datosTablas.listaValores[0],
			listaValoresDto: datosTablas.listaValores,
			generarConstancia: $('#generarConstancia').prop("checked"),
			mantenerComprobantesAdjuntos: false,
			observacionMail : $('#observacionMail').val(),
			mantenerComprobantesAdjuntos: false
		};
		$('#divAlertErrorFoot').hide();

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'valor/crear-boleta-con-valor',
			"data" : JSON.stringify(eval(
				valoresDto
			)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					$('#mensajeAlta').val(result.mensajeAlta);
					$('#mensajeAltaDuplicado').val(result.mensajeAltaDuplicado);
					$("#imprimible").val(result.imprimible);
					$('#mensajesMostrarEnvioMail').val(result.mensajesMostrarEnvioMail);
					$('#url').val(result.url);
					$('#formValor')[0].action = urlAsynchronousHTTP + "/" + result.action;
					$('#formValor')[0].submit();
				} else {
					$('#divAlertErrorFoot').show();
					if (result.errorListaVacia) {
						$('#alertErrorFoot').text('Debe cargar al menos un Valor.');
					} else if (result.errorRecibo) {
						var indexFoot = result.listaErrores.length;
						var errorText = '';
						
						for (var indexEr = 0; indexEr < indexFoot; indexEr++) {
							if (!$.isEmpty(errorText)) {
								errorText += "\n";
							}
							errorText += result.listaErrores[indexEr];
						}
						$('#alertErrorFoot').text(errorText);
					} else if (result.errorValorDuplicado) {
						var indexFoot2 = result.listaErrores.length;
						var errorText2 = '';
						
						for (var indexErX = 0; indexErX < indexFoot2; indexErX++) {
							if (!$.isEmpty(errorText2)) {
								errorText2 += "\n";
							}
							errorText2 += result.listaErrores[indexErX];
						}
						$('#alertErrorFoot').text(errorText2);
					}
					$('#btnAceptar').attr('disabled', false);
					ocultarBloqueEspera();
				}
				
			}
		});
	}
	$('#btnAceptar').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#divAlertErrorFoot').hide();
		$('#alertErrorFoot').val('');
		$('.error').text('');
		$('#alertError').text('');
		$('#divAlertError').hide();
		if ($.isEmpty(datosTablas.listaValores)) {
			$('#divAlertErrorFoot').show();
		    $('#alertErrorFoot').text('Debe cargar al menos un Valor.');
			ocultarBloqueEspera();
		} else {
			if (validarCampos()) {
				bootbox.confirm(mensajeBtnAceptar, function(result) {
					if (result) {
						onClickBtnAceptar();
					} else {
						ocultarBloqueEspera();
					}
				});
			} else {
				onClickBtnAceptar();
			};
		}
	});
	
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
			
//	 		if(datosTablas.clientesSeleccionados.length == 0){
				
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
//	 		}
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
		
		});
/******************************************************************************/
		tablas.comprobantes = $('#comprobantes').dataTable( {
			dom: 'rt' ,
			"scrollX": true,
			"scrollY": '500px',
			"bScrollCollapse": true,
			"aoColumns" : [
				{
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
				}],
			'columnDefs' : [ {
				type : 'latam_date',
				targets : 'date'
			} ]
		});
		// Adjuntos
		$('#btnAdjComprobante').click(function() {
	    	$('#bloqueEspera').trigger('click');
	    	$("#errorArchComprobante").text("");
	    	$("#errorDescripcionComp").text("");

	    	var error = false;
	    	if (!$('#comprobanteArch').val()) {
	    	    $("#errorArchComprobante").text("Debe seleccionar un archivo.");
	    	    $("#errorArchComprobante").show();
	    	    error = true;
	    	}
	    	if (!$('#descripcionComprobante').val() 
	    		||  $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, '').length == 0) {
	    	    $("#errorDescripcionComp").text("Debe ingresar una descripción.");
	    	    $("#errorDescripcionComp").show();
	    	    error = true;
	    	}

	    	if (!error) {
	    	    $("#adjComprobanteForm").ajaxForm({
	    		dataType:  'json', 
	    		data: { 
	    		    descripcion : $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, ''),
	    		},
	    		success:function(data) {
	    		    $('.fileupload').fileupload('clear');
	    		    if (data.success) {
	    			var comp = {
	    				idComprobante : data.idComprobante,
	    				nombreArchivo : data.fileName,
	    				descripcionArchivo : data.descripcion,
	    				motivoAdjunto : data.motivoAdjunto
	    			};
	    			
	    			tablas.comprobantes.fnAddData(comp, true);
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
		$('#comprobantes tbody').on('click', 'button', function() {
	    	var node = $(this).parents('tr')[0];
	    	var aData = tablas.comprobantes.fnGetData(node);

	    	if ($(this).attr('action') == 'eliminar') {
	    	    $('#bloqueEspera').trigger('click');
	    	    var existeEnValor = false;
	    	    
	    	    datosTablas.listaValores.forEach(function(valor,i){
	    	    		valor.listaComprobantes.forEach(function(comprobante,j){
	    	    		if(comprobante.idComprobante == aData.idComprobante){
	    	    			existeEnValor = true;
	    	    		}
	    	    	});
	    	    });
	    	    
	    	    if(!existeEnValor){
		    	    $.ajax({
		    		"type" : "POST",
		    		"url": "valor/eliminarComprobante",
		    		"dataType": "json", 
		    		"data": "{ \"idComprobante\": " + aData.idComprobante + "}", 
		    		"contentType": "application/json",
		    		"mimeType": "application/json",
		    		"success" : function(result) {
		    		    if (result.success) {
		    		    	tablas.comprobantes.fnDeleteRow(node);
		    		    	datosTablas.comprobantes = $.grep(datosTablas.comprobantes, function(seleccionado) {
		    		    		return seleccionado.idComprobante != aData.idComprobante;
		    		    	});
		    		    }
		    		}});
	    	    } else {
			    	tablas.comprobantes.fnDeleteRow(node);
			    	datosTablas.comprobantes = $.grep(datosTablas.comprobantes, function(seleccionado) {
			    		return seleccionado.idComprobante != aData.idComprobante;
			    	});
	    	    }
	    	    
	    	    ocultarBloqueEspera();
	    	} else {
	    	    var iframe = document.createElement("iframe");
	    	    iframe.src = "valor/descargarComprobante?id=" + aData.idComprobante;
	    	    iframe.style.display = "none";
	    	    document.body.appendChild(iframe);
	    	}
	    });
});// Fin del ready
function completarComboTipoValor() {
	var seg = $('#selectSegmento').val();
	var optioTipoValor = null;

	if ($.isEmpty(seg)) {
		optioTipoValor = [];
	} else {
		var x = tipoValores.length;
		optioTipoValor = [{
			text : leyendaComboSeleccionar,
			value : ''
		}];
		var empresa = $('#selectEmpresa').val();
		var optionTp = null;
	
		for (var i = 0; i < x; i++) {
			if (seg === tipoValores[i].idSegmento && empresa === tipoValores[i].idEmpresa) {
				optionTp = new Option(tipoValores[i].descripcion, tipoValores[i].idTipoValor);
				optioTipoValor.push(optionTp);
				// Como no son muchos elementos en el array lo recorro completo y de no complico la logica con break;
			}
		}
	}
	$('#selectTipoValor').replaceOptions(optioTipoValor);
	if (!$.isEmpty(seg)) {
		if ($.isEmpty(edicionValorDto)) {
			$('#selectTipoValor option:eq(1)').prop('selected', 'selected').change();
		}	
	} else {
		$('#selectTipoValor').val('');
	}
	if (!$.isEmpty(edicionValorDto)) {
		seleccionarTipoValor();
	}
}
function crearOptionOrigen(id) {
	var size = paramTipoGestion.listaOrigenes.length;
	for (var i = 0; i < size; i++) {
		if (paramTipoGestion.listaOrigenes[i].idOrigen == id) {
			return new Option(paramTipoGestion.listaOrigenes[i].descripcion, paramTipoGestion.listaOrigenes[i].idOrigen);
		}
	}
}

function completarComboOrigen(value) {
	var seg = $('#selectSegmento').val();
	var optioTipoValor = null;

	if ($.isEmpty(seg)) {
		vaciarCombo('selecselecttAcuerdo');
		vaciarCombo('selectNumeroCuenta');
		$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
	
	} else {
		optioTipoValor = [{
			text : leyendaComboSeleccionar,
			value : ''
		}];
	
		if (esCajeroPagado) {
			optioTipoValor = [];
			optioTipoValor.push(crearOptionOrigen(1));
		} else if (esAnalista) {
			optioTipoValor.push(crearOptionOrigen(2));
			optioTipoValor.push(crearOptionOrigen(3));
			optioTipoValor.push(crearOptionOrigen(4));
			if (!$.isEmpty(value) && value != '4') {
				optioTipoValor.push(crearOptionOrigen(8));
			}
		} else {
//			var size = paramTipoGestion.listaOrigenes.length;
//			for (var i = 0; i < size; i++) {
//				if (paramTipoGestion.listaOrigenes[i].idOrigen == 1) {
//					optioTipoValor.push(new Option(paramTipoGestion.listaOrigenes[i].descripcion, paramTipoGestion.listaOrigenes[i].idOrigen));
//				}
//			}
		}
	}
	$('#selectOrigen').replaceOptions(optioTipoValor);
	if (optioTipoValor.length == 1) {
		if ($.isEmpty(edicionValorDto)) {
			$('#selectOrigen option:eq(0)').prop('selected', 'selected').change();
		}	
	} else {
		$('#selectOrigen').val('');
	}
	if (!$.isEmpty(edicionValorDto)) {
		seleccionarOrigen();
	}
}
function siEsCajeroPagador() {
	if (!$.isEmpty(esCajeroPagado) && esCajeroPagado) {
		$('#operacionAsociadaObligatorio').show();
		$('#copropietarioObligatorio').show();
		$('#generarConstanciaDiv').hide();
	} else {
		$('#generarConstanciaDiv').show();
		if ($.isEmpty(datosTablas.listaValores)) {
			$('#generarConstancia').attr('disabled', true);
		} else {
			var size = datosTablas.listaValores.length;
			for (var i = 0; i < size; i++) {
				if (datosTablas.listaValores[i].idOrigen === '4') {
					$('#generarConstancia').attr('disabled', false);
					return;
				}
			}
			$('#generarConstancia').attr('disabled', true);
		}
	}
}

//AGREGAR OPCION AL COMBO
function agregarOpcionCombo (comboId, valor, descripcion) {
	var opt = document.createElement("option");
	comboId.options.add(opt);
	opt.text = descripcion;
	opt.value = valor;
}; 
//MOSTRAR CAMPOS POR TIPO DE VALOR
function mostarTipoValor(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;
	switch (tipoValorSeleccionado) {
	case "2":
		bloqueCheque.style.display = 'block';
		bloqueChequeDiferido.style.display = 'none';
		bloqueChequeDiferidoError.style.display = 'none';
		bloqueFechaEmisionCheques.style.display = 'block';
		//bloqueFechaNotificacionValor.style.display = 'block';
		break;
	case "3":
		bloqueCheque.style.display = 'block';
		bloqueChequeDiferido.style.display = 'block';
		bloqueChequeDiferidoError.style.display = 'block';
		bloqueFechaEmisionCheques.style.display = 'block';
		//bloqueFechaNotificacionValor.style.display = 'block';
		
		break;
	case "4":
		bloqueCheque.style.display = 'none';
		bloqueChequeDiferido.style.display = 'none';
		bloqueChequeDiferidoError.style.display = 'none';
		bloqueFechaEmisionCheques.style.display = 'none';
		//bloqueFechaNotificacionValor.style.display = 'none';
		break;
	}
};
function mostrarConBorrar(referenciaCombo) {
//	document.getElementById("selectBancoOrigen").value = "";
//	document.getElementById("inputNumeroCheque").value = "";
//	document.getElementById("inputFechaVencimiento").value = "";

	if (
		referenciaCombo === undefined ||
		referenciaCombo === null ||
		(!$.isEmpty(referenciaCombo) && referenciaCombo.value == "")
	) {
		bloqueCheque.style.display = 'none';
		bloqueChequeDiferido.style.display = 'none';
		bloqueChequeDiferidoError.style.display = 'none';
		$('#inputFechaNotificacionValor').val('');
		bloqueFechaNotificacionValor.style.display = 'none';
		$('#inputFechaEmisionCheque').val('');
	} else {
		var tipoValorSeleccionado = referenciaCombo.value;
		switch (tipoValorSeleccionado) {
		case "2":
			bloqueCheque.style.display = 'block';
			bloqueChequeDiferido.style.display = 'none';
			bloqueChequeDiferidoError.style.display = 'none';
			bloqueFechaEmisionCheques.style.display = 'block';
			bloqueFechaEmisionChequesError.style.display = 'block';
			$('#errorinputFechaEmisionCheque').val('');
			$('#inputFechaVencimiento').val('');
			if( $('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
				bloqueFechaNotificacionValor.style.display = 'block';
			} else {
				bloqueFechaNotificacionValor.style.display = 'none';
			}
//			bloqueFechaNotificacionValor.style.display = 'block';
			break;
		case "3":
			bloqueCheque.style.display = 'block';
			bloqueChequeDiferido.style.display = 'block';
			bloqueChequeDiferidoError.style.display = 'block';
			bloqueFechaEmisionCheques.style.display = 'block';
			bloqueFechaEmisionChequesError.style.display = 'block';
			$('#errorinputFechaEmisionCheque').val('');
			if( $('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
				bloqueFechaNotificacionValor.style.display = 'block';
			} else {
				bloqueFechaNotificacionValor.style.display = 'none';
			}
			break;
		case "4":
			bloqueCheque.style.display = 'none';
			bloqueChequeDiferido.style.display = 'none';
			bloqueChequeDiferidoError.style.display = 'none';
			bloqueFechaEmisionCheques.style.display = 'none';
			bloqueFechaEmisionChequesError.style.display = 'none';
			$('#errorinputFechaEmisionCheque').val('');
			$('#inputFechaEmisionCheque').val('');
			if( $('#selectOrigen').val() == 1 || $('#selectOrigen').val() == 3 ) {
				bloqueFechaNotificacionValor.style.display = 'block';
			} else {
				bloqueFechaNotificacionValor.style.display = 'none';
			}			
			break;
		}
	}
	
};
//MOSTRAR BLOQUE RECIBO
function mostrarRecibo(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;
	//bloqueFechaEmisionChequesError.style.display = 'block';
	
	$('#errorinputFechaEmisionCheque').val('');
	
	switch (tipoValorSeleccionado) {
	case "1":// Origen Cajero Pagador
		bloqueRecibo.style.display = 'block';
		bloqueFechaNotificacionValor.style.display = 'block';
		break;
	case "2":// cliente
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		$('#inputFechaNotificacionValor').val('');
		bloqueRecibo.style.display = 'none';
		bloqueFechaNotificacionValor.style.display = 'none';
		break;
	case "3":// Oficina Recibo Preimpreso
		bloqueRecibo.style.display = 'block';
		bloqueFechaNotificacionValor.style.display = 'block';
		break;
	case "4":// Oficina Constancia Automática
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		bloqueRecibo.style.display = 'none';
		bloqueFechaNotificacionValor.style.display = 'none';
		$('#inputFechaNotificacionValor').val('');
		break;
	case "8": // Banco
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		bloqueRecibo.style.display = 'none';
		bloqueFechaNotificacionValor.style.display = 'none';
		$('#inputFechaNotificacionValor').val('');
		break;
	case "":
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		bloqueRecibo.style.display = 'none';
		bloqueFechaNotificacionValor.style.display = 'none';
		$('#inputFechaNotificacionValor').val('');
		break;
	}
}
/******************************************************************************/
/* Funciones para el manejo de los combos Acuerdo, banco y cuenta
 *
 ******************************************************************************/
function completarCombosAcuerdoBancoDepositoCuenta(origen) {
	var seg = $('#selectSegmento').val();
	var tipoValor = $('#selectTipoValor').val();
	var size = paramTipoGestion.lista.length;
	var segmentoEncontrado = false;

	if ($.isEmpty(origen)) {
		vaciarCombo('selectAcuerdo');
		vaciarCombo('selectNumeroCuenta');
		vaciarCombo('selectBancoDeposito');
		$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
	} else {
		for(var index = 0; index < size; index++) {
			if (
				paramTipoGestion.lista[index].idSegmento === seg &&
				paramTipoGestion.lista[index].idTipoValor == tipoValor &&
				paramTipoGestion.lista[index].idOrigen == origen
			) {
				segmentoEncontrado = true;
				acuerdoBancoCuenta = paramTipoGestion.lista[index].combo;
				crearOptionCombosAcuerdoBancoDepositoCuenta(paramTipoGestion.lista[index].combo);
				break;
			} else {
				if (segmentoEncontrado) {
					break;
				}
			}
		}
	}
	
}
function crearOpcionesByBanco(idBanco) {
	var optionsAcuerdo = [];
	var optionsCuenta = [];
	var corriente = null;
	var cantidad = 0;
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.banco.idBanco == idBanco) {
			cantidad++;
		}
	}
	if (cantidad != 1) {
		optionsCuenta.push(combosAcuerdoBancoDepositoCuenta.bancoCuenta[0]);
		optionsAcuerdo.push(combosAcuerdoBancoDepositoCuenta.banco[0]);
	}
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.banco.idBanco == idBanco) {
			optionsAcuerdo.push(new Option(corriente.descripcion, corriente.idAcuerdo));
			optionsCuenta.push(new Option(corriente.bancoCuenta.cuentaBancaria, corriente.bancoCuenta.idBancoCuenta));
		}
	}
	$('#selectNumeroCuenta').append(optionsCuenta);
	$('#selectAcuerdo').append(optionsAcuerdo);
	
	
}
function selectOpcionByCuenta(idBancoCuenta) {
	var corriente = null;
	
	$('#selectNumeroCuenta').append(combosAcuerdoBancoDepositoCuenta.bancoCuenta);
	$('#selectAcuerdo').append(combosAcuerdoBancoDepositoCuenta.acuerdo);
	$('#selectBancoDeposito').append(combosAcuerdoBancoDepositoCuenta.banco);
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.idBancoCuenta == idBancoCuenta) {
			$('#selectNumeroCuenta').val(corriente.bancoCuenta.idBancoCuenta);
			$('#selectAcuerdo').val(corriente.idAcuerdo);
			$('#selectBancoDeposito').combobox2("refresh", corriente.bancoCuenta.banco.idBanco, 'selectBancoDeposito');
			return;
		}
	}
}
function crearOptionCombosAcuerdoBancoDepositoCuenta(combo) {
//	combosAcuerdoBancoDepositoCuenta
	var size = combo.listaAcuerdos.length;
	var sizeCuenta = combo.listaCuentas.length;
	var ops;
	
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	
 	
	var op = null;
	
 	
 	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaAcuerdos[index].descripcion, combo.listaAcuerdos[index].idAcuerdo);
		ops.push(op);
	}
	size = combo.listaBancos.length;
	combosAcuerdoBancoDepositoCuenta.acuerdo = ops;
	$('#selectAcuerdo').replaceOptions(ops);
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaBancos[index].descripcion + ' - ' + combo.listaBancos[index].idBanco, combo.listaBancos[index].idBanco);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.banco = ops;
	
	$('#selectBancoDeposito').replaceOptions(ops);
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	for (var index = 0; index < sizeCuenta; index++) {
		op = new Option(combo.listaCuentas[index].cuentaBancaria, combo.listaCuentas[index].idBancoCuenta);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.bancoCuenta = ops;
	$('#selectNumeroCuenta').replaceOptions(ops);
	
	$("#selectBancoDeposito").combobox2('refresh');
	
	if (combosAcuerdoBancoDepositoCuenta.bancoCuenta.length == 1) {
		$('#selectNumeroCuenta').val();
	}
	
}
function vaciarCombo(comboName) {
	$('#' + comboName).empty();
}

/******************************************************************************/
var limpiarCamposByCambioTipo = function() {
//	$('#inputImporte').val('');
//	$('#selectMotivo').val('');
//	$('#inputOperacionAsociada').val('');
//	$('#inputNumeroDocumentoContable').val('');
//	clearSelectInput('bloqueCheque');
//	clearSelectInput('bloqueAcuerdo');
//	clearSelectInput('bloqueRecibo');
//	
//	
	//clearSelectInput('bloqueBancoOrigen');
	//$('#descripcionBanco').combobox2("refresh", '' , 'descripcionBanco');
	$('#codigoBanco').val('').change();
	$('#selectAcuerdo').val('').change();


	//$('#observaciones').val('');
	//$('.error').text('');
};


var procesarFormValor = function() {
	var valorDto = new Object();
	// Datos de cliente!!!
	
	valorDto.empresa = obtenerDescripcionSelect("selectEmpresa");
	valorDto.segmento = obtenerDescripcionSelect("selectSegmento");
	
	valorDto.copropietario = obtenerDescripcionSelect("selectCopropietario");
	
	valorDto.tipoValor = obtenerDescripcionSelect("selectTipoValor");
	valorDto.motivo = obtenerDescripcionSelect("selectMotivo"); 
	
	valorDto.idAcuerdo = selectAcuerdo.value;
	valorDto.numeroCuenta = obtenerDescripcionSelect("selectNumeroCuenta"); 
	valorDto.bancoDeposito = obtenerDescripcionSelect("selectBancoDeposito");
	valorDto.bancoOrigen = obtenerDescripcionSelect("descripcionBanco");
	valorDto.origen = obtenerDescripcionSelect("selectOrigen");
	
	valorDto.idEmpresa = $('#selectEmpresa').val();
	valorDto.idSegmento = $('#selectSegmento').val();
	valorDto.email = $('#idEmail').val();
	valorDto.nombreAnalista = $('#nombreAnalista').val();
	valorDto.idAnalista = $('#idAnalista').val();
	valorDto.mailAnalista = ''; 												// No veo que se carge en ningun lado
	valorDto.idCopropietario = $('#selectCopropietario').val();
	valorDto.idTipoValor = $('#selectTipoValor').val();
	
	
	valorDto.importe = $('#inputImporte').val();
	valorDto.idMotivo = $('#selectMotivo').val();
	valorDto.operacionAsociada = $('#inputOperacionAsociada').val();
	valorDto.idOrigen = $('#selectOrigen').val();
	
	
	valorDto.idAcuerdo = $('#selectAcuerdo').val();
	valorDto.idBancoDeposito = $('#selectBancoDeposito').val();
	valorDto.idNroCuenta = $('#selectNumeroCuenta').val();
	valorDto.idBancoOrigen = $('#codigoBanco').val();
	valorDto.numeroCheque = $('#inputNumeroCheque').val();
	valorDto.fechaVencimiento = $('#inputFechaVencimiento').val();
	
	
	valorDto.reciboPreImpreso = $('#inputReciboPreImpreso').val();
	valorDto.fechaIngresoRecibo = $('#inputFechaIngresoRecibo').val();
	
	valorDto.observaciones = $('#observaciones').val();
	valorDto.observacionMail = $('#observacionMail').val();
	
	
	
	valorDto.cajeroPagador = esCajeroPagado;
	
	
	
	valorDto.fechaDeposito = $('#inputFechaDeposito').val();
	
	valorDto.fechaTransferencia = $('#inputFechaTransferencia').val();
	valorDto.reciboPreImpreso = $('#inputReciboPreImpreso').val();
	valorDto.fechaIngresoRecibo = $('#inputFechaIngresoRecibo').val();
	
	
	valorDto.numeroReferencia = $('#inputNumeroReferencia').val();


	if (
		TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		valorDto.fechaEmision = $('#inputFechaEmisionCheque').val();
		if (!$.isEmpty($('#inputFechaEmisionCheque').val())) {
			valorDto.fechaEmisionAux = $("#inputFechaEmisionCheque").datepicker("getDate");
		}
	}
	if (valorDto.idOrigen == 3 || valorDto.idOrigen == 1) {
		valorDto.fechaNotificacionDisponibilidadRetiroValor = $("#inputFechaNotificacionValor").val();
		
		if (!$.isEmpty($('#inputFechaNotificacionValor').val())) {
			valorDto.fechaNotificacionDisponibilidadRetiroValorAux = $("#inputFechaNotificacionValor").datepicker("getDate");
		}
	}
	
	if ($('#checkEnviarMailBoleta').prop('checked')) {
		valorDto.enviarBoletaMail = true;
		valorDto.boletaEnviadaMailEstado = 'SI';
		valorDto.checkEnviarMailBoleta = true;
	} else {
		valorDto.enviarBoletaMail = false;
		valorDto.boletaEnviadaMailEstado = 'NO';
		valorDto.checkEnviarMailBoleta = false;
	}
	if ($('#checkImprimirBoleta').prop('checked')) {
		valorDto.imprimirBoleta = true;
		valorDto.boletaImpresaEstado = 'SI';
		valorDto.checkImprimirBoleta = true;
	} else {
		valorDto.imprimirBoleta = false;
		valorDto.boletaImpresaEstado = 'NO';
		valorDto.checkImprimirBoleta = false;
	}

	valorDto.cliente = datosTablas.clientesSeleccionados[0];
	valorDto.listaComprobantes = datosTablas.comprobantes;
	return valorDto;
};
function validarFormValor(valorDto) {
	var listaErrores = [];
	var validador = crearValidador(valorDto);
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
function crearOpcionesByAcuerdo(idAcuerdo) {
	var options = [];
	var optionsBanco = [];
	var corriente = null;
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
 		if (corriente.idAcuerdo == idAcuerdo) {
			options.push(new Option(corriente.bancoCuenta.cuentaBancaria, corriente.bancoCuenta.idBancoCuenta));
			$('#selectNumeroCuenta').append(options);
			optionsBanco.push(new Option(corriente.bancoCuenta.banco.descripcion + ' - ' + corriente.bancoCuenta.banco.idBanco, corriente.bancoCuenta.banco.idBanco));
			$('#selectBancoDeposito').append(optionsBanco);
			$('#selectBancoDeposito').combobox2("refresh", acuerdoBancoCuenta.listaAcuerdos[i].bancoCuenta.banco.idBanco, 'selectBancoDeposito');
			return options;
		}
	}
}
function crearOpcionesByAgrupador(idAgrupador, nameSelect) {
	var options = [];

	var corriente = null;
	
	for(var i = 0, size = bancoDescripcion.agrupadores.length; i < size; i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = bancoDescripcion.agrupadores[i];
 		if (corriente.idValue == idAgrupador) {
			for (var j = 0, sizeJ = corriente.bancos.length; j < sizeJ; j++) {
				var bancoCorriente = corriente.bancos[j];
				var option = new Option(bancoCorriente.idBanco + ' - ' + bancoCorriente.descripcion, bancoCorriente.idBanco);
				$(option).attr({'idAgrupador': bancoCorriente.idAgrupador});
				options.push(option);
			}
			break;
		}
	}
	return options;
}
function generarIdDinamico(prefix) {
	return prefix + '_' + Date.now();
}
function eliminarEnTabla(objeto, tabla) {
	var pos = tabla.indexOf(objeto);
	if (pos > -1) {
		tabla.splice(pos, 1);
	}
}


function editar(idValor) {
	$('#bloqueEspera').trigger('click');
	$('.error').text('');
	$('#alertError').text('');
	$('#divAlertError').hide();
	//limpiarCamposByCambioTipoAviso();
	limpiarFormAlAgregarValor();
	$(tablas.listaValores.fnGetData()).each(function(j, seleccionado) {
		if (seleccionado.id == idValor) {
			llenarForm(seleccionado);
			tablas.listaValores.fnDeleteRow(j);
			eliminarEnTabla(seleccionado, datosTablas.listaValores);
			return false;
		}
	});
	var size = datosTablas.listaValores.length;
	if(size == 0){
		$('#btnBuscarClientes').attr('disabled',false);
		aparienciaButton.descubrirButtonMinus(tablas.clientesSeleccionados,0);
	}
	$('#generarConstancia').attr('disabled', true);
	var encontrado = false;
	for (var i = 0; i < size; i++) {
		if (datosTablas.listaValores[i].idOrigen === '4') {
			$('#generarConstancia').attr('disabled', false);
			encontrado = true;
			break;
		}
	}
	if (!encontrado) {
		$('#generarConstancia').prop("checked", false);
	}
	
}
function eliminar(idValor) {
	$('#bloqueEspera').trigger('click');
	$(tablas.listaValores.fnGetData()).each(function(j, seleccionado) {
		if (seleccionado.id == idValor) {
			eliminarComprobanteAdjunto(seleccionado);
			tablas.listaValores.fnDeleteRow(j);
			datosTablas.listaValores = $.grep(datosTablas.listaValores, function(seleccionado) {
				return seleccionado.id != idValor;
			});
		}
		
	});
	var size = datosTablas.listaValores.length;
	if(size == 0){
		$('#btnBuscarClientes').attr('disabled',false);
		aparienciaButton.descubrirButtonMinus(tablas.clientesSeleccionados,0);
	}
	$('#generarConstancia').attr('disabled', true);
	var encontrado = false;
	for (var i = 0; i < size; i++) {
		if (datosTablas.listaValores[i].idOrigen === '4') {
			$('#generarConstancia').attr('disabled', false);
			break;
		}
	}
	if (!encontrado) {
		$('#generarConstancia').prop("checked", false);
	}
	ocultarBloqueEspera();
}
/*******************************************************************************
 * EDICION
 * ****************************************************************************/
function llenarForm(valorDto) {
	$('#selectEmpresa').val(valorDto.idEmpresa);
	$('#email').val(valorDto.email);
	edicionValorDto = valorDto;
	if ($('#selectSegmento').val() === valorDto.idSegmento) {
		completarComboTipoValor();
	} else {
		$('#selectSegmento').val(valorDto.idSegmento).change();
	}
}
function seleccionarTipoValor() {
	var valorDto = edicionValorDto;
	$('#selectCopropietario').val(valorDto.idCopropietario);
	$('#selectTipoValor').val(valorDto.idTipoValor).change();
}
function seleccionarOrigen() {
	$('#selectOrigen').val(edicionValorDto.idOrigen).change();
}
function validarCampos() {
	if ($("#inputImporte").val() != null) {
		if ($("#inputImporte").val().length) {
			return true;
		}
	}
	if ($("#selectMotivo").val() != null) {
		if ($("#selectMotivo").val().length) {
			return true;
		}
	}
	if ($("#inputOperacionAsociada").val() != null) {
		if ($("#inputOperacionAsociada").val().length) {
			return true;
		}
	}
	if ($("#selectOrigen").val() != null) {
		if ($("#selectOrigen").val()>1) {
			if ($("#selectOrigen").val().length) {
				return true;
			}
		}
	}
	if ($("#selectBancoOrigen").val() != null) {
		if ($("#selectBancoOrigen").val().length) {
			return true;
		}
	}
	if ($("#inputNumeroCheque").val() != null) {
		if ($("#inputNumeroCheque").val().length) {
			return true;
		}
	}
	if ($("#inputFechaVencimiento").val() != null) {
		if ($("#inputFechaVencimiento").val().length) {
			return true;
		}
	}
	if ($("#selectNroChequeAReemplazar").val() != null) {
		if ($("#selectNroChequeAReemplazar").val().length) {
			return true;
		}
	}
	if ($("#observaciones").val() != null) {
		if ($("#observaciones").val().length) {
			return true;
		}
	}
	return false;
}

function limpiarTodo() {
	vaciarCombo('selectAcuerdo');
	vaciarCombo('selectNumeroCuenta');
	vaciarCombo('selectBancoDeposito');
	$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
	$('#descripcionBanco').combobox2("refresh", '', 'descripcionBanco');  // Ver por que no se limpia 
	$('#codigoBanco').val("").change();
	vaciarCombo('selectAcuerdo');
	clearInputText("inputForm");
	vaciarCombo('selectOrigen');
	vaciarCombo('selectTipoValor');
	mostrarConBorrar();
	//$('#observacionMail').text('');
	$('#observacion').text('');
	$('#selectMotivo').val('');
}
function limpiarFormAlAgregarValor() {
	vaciarCombo('selectAcuerdo');
	vaciarCombo('selectNumeroCuenta');
	vaciarCombo('selectBancoDeposito');
	$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
	$('#codigoBanco').val("").change();
	$('#descripcionBanco').combobox2("refresh", '', 'descripcionBanco');  // Ver por que no se limpia 
	
	vaciarCombo('selectAcuerdo');
	clearInputText("inputForm");
	$('#selectOrigen').val('').change();
	//mostrarConBorrar();
	//$('#observacionMail').text('');
	$('#observaciones').text('');
	$('#observaciones').val('');
	$('#selectMotivo').val('');
	$('#selectBancoDeposito').combobox2("refresh", '', 'selectBancoDeposito');
	$('#codigoBanco').val("").change();
	$('#descripcionBanco').combobox2("refresh", '', 'descripcionBanco');  // Ver por que no se limpia
	
}
function llenarFormEdicion() {
	var valorDto = edicionValorDto;

	$('#idEmail').val(valorDto.email);
	
	
	$('#inputImporte').val(valorDto.importe);
	$('#selectMotivo').val(valorDto.idMotivo);
	$('#inputOperacionAsociada').val(valorDto.operacionAsociada);
	
	if ($('#selectAcuerdo option').length > 1) {
		$('#selectAcuerdo').val(valorDto.idAcuerdo).change();
		$('#selectBancoDeposito').val(valorDto.idBancoDeposito);
		$('#selectNumeroCuenta').val(valorDto.idNroCuenta);
	};
	

	$('#observaciones').val(valorDto.observaciones);
	$('#observacionMail').val(valorDto.observacionMail);

	valorDto.idNroCuenta = $('#selectNumeroCuenta').val();

	$('#inputFechaNotificacionValor').val(valorDto.fechaNotificacionDisponibilidadRetiroValor);

	if (
		TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		$('#codigoBanco').val(valorDto.idBancoOrigen).change();
		 $('#inputNumeroCheque').val(valorDto.numeroCheque);
		 if (TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor) {
			 $('#inputFechaVencimiento').val(valorDto.fechaVencimiento);
		 }
		
	} else {
		//validador.validacion_idNroCuenta = validacion_idNroCuenta;
	}
	
	
	
	if (valorDto.idOrigen == 3 || valorDto.idOrigen == 1) { 
		$('#inputReciboPreImpreso').val(valorDto.reciboPreImpreso);
		$('#inputFechaIngresoRecibo').val(valorDto.fechaIngresoRecibo);
	}
	
	
	$('#inputFechaEmisionCheque').val(valorDto.fechaEmision);
	$('#checkEnviarMailBoleta').prop('checked', valorDto.enviarBoletaMail);
	$('#checkImprimirBoleta').prop('checked', valorDto.imprimirBoleta);
	
	valorDto.cajeroPagador = esCajeroPagado;
	if (!$.isEmpty(valorDto.listaComprobantes)) {
		tablas.comprobantes.fnClearTable();
		datosTablas.comprobantes = [];

		datosTablas.comprobantes = valorDto.listaComprobantes;
		tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);
		
	}
	edicionValorDto = null;
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
function checkComprobantes(){
    if ($("#mantenerComprobantesAdjuntosVal:checked").val()){
		$("#mantenerComprobantesAdjuntos").val(true);
	} else {
		$("#mantenerComprobantesAdjuntos").val(false);
	}
}
function eliminarComprobanteAdjunto(valorDto){
	valorDto.listaComprobantes.forEach(function(comprobanteValor,i){
		var existeEnTabla = false;
		//Pregunta si el comprobante existe en la grilla de comprobantes
		datosTablas.comprobantes.forEach(function(comprobanteLista,j){
			if(comprobanteValor.idComprobante == comprobanteLista.idComprobante){
				existeEnTabla = true;
			}
		});
		
		//Pregunta si el comprobante existe en otro valor
		datosTablas.listaValores.forEach(function(valorLista,j){
			if(valorDto.id != valorLista.id){
				valorLista.listaComprobantes.forEach(function(comprobanteLista,j){
					if(comprobanteValor.idComprobante == comprobanteLista.idComprobante){
						existeEnTabla = true;
					}
				});						
			}
		});
		
		//Si no existe en la grilla de comprobantes ni en un valor se elimina el comprobante
		if(!existeEnTabla){
			$.ajax({
				"type" : "POST",
	    		"url": "valor/eliminarComprobante",
	    		"dataType": "json", 
	    		"data": "{ \"idComprobante\": " + comprobanteValor.idComprobante + "}", 
	    		"contentType": "application/json",
	    		"mimeType": "application/json"
	    	});
		}
	});
}