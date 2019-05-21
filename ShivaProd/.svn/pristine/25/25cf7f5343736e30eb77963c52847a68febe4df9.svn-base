var SCROLL_Y = '300px';

var legajosEncontrados = null;
var volviendoABusqueda = false;
var combos = {
		acuerdo : null,
		banco : null,
		bancoCuenta : null,
		optionBacia: null,
		edbancoOrigen:null,
		bancoOrigen: null
	};
$(document).ready(function() {
	combos.bancoOrigen = $('#codigoBanco option');
	var generarCuadroUsuario = function(nombre, urlFoto, mail) {
		if (nombre !== '-') {
			return '<div style="width: 150px; text-align: left;"> ' +
			'<img src="'+ urlFoto + '" ' +
			'style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; ' + 
			'margin-left: 5px; float: left; margin-bottom: 3px;" ' +
			'onerror=\'src="'+urlAsynchronousHTTP+'/img/default2.png" \'> ' + nombre + '<br> ' + 
			'<a href="sip:'+ mail + '" title="Chat"> ' +
			'<i class="icon-comment" style="margin-top: 3px"></i> ' +
			'</a> ' +
			'<a href="mailto:'+ mail + '" title="Email"> ' +
			'<i class="icon-envelope" style="margin-left: 3px; margin-top: 2px"></i> ' +
			'</a> ' +
			'</div>';
		} else {
			return '<div style="width: 150px; text-align: center;"> ' + nombre + ' <br> ' + '</div>';
		}
	};
	
	var legajosEncontradosSettings = {
			 dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
//			"order": [[ 17, "desc" ]],
//			"order": [[ 4, "asc" ]],
			buttons: [{
					extend:'excelHtml5',
					text:'Excel',
					title: "Busqueda_De_Legajos",
					className: 'excelbtn'
			}],
			"aoColumns" : [
//							{ "targets" : 0,
//							   "searchable" : false,
//							   "bSortable" : false,
//							   "data" : null,
//							   "render" : function(data, type, row) {
//								   if (type === 'display') {
//									   var idLeg = data.idLegajo;
//										return '<div><input type="checkbox" id="check'+idLeg+'" class="checkSelect editor-active" onclick="checking(\'seleccionarTodos\',\'checkSelect\', cobrosEncontrados, \'btnDesapropiar\')"/></div>';
//									}
//									return '';
//							   }
//							},
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   var idLeg = data.idLegajo;
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver resumen" onclick="resumenLegajo('+idLeg+')"><i class="icon-eye-open bigger-120"></i></button></div>';
			            		   }
			            		   return null;
			            	   }
			               },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   if (data.esModificablePorUsuario == true) {
			            				   var idLeg = data.idLegajo;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarLegajo('+idLeg+')"><i class="icon-edit bigger-120"></i></button></div>';
			            			   }
			            		   }
			            		   return null;
			            	   }
			               },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   if (data.esAnulable == true) {
			            				   var idLeg = data.idLegajo;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularLegajo('+idLeg+')"><i class="icon-remove bigger-120"></i></button></div>';
			            			   }
			            		   }
			            		   return null;
			            	   }
			               },
			               { "mData" : "empresa" },
			               { "mData" : "segmento" },
			               { "mData" : "idLegajo"},
			               { "mData" : "cuit",
			            	   "mRender" : function(data, type, row) {
			           			if (!$.isEmpty(data)) {
			           				var datosCuit = data.split('/');
			           				var cuit = "";
			           				for (var i = 0; i < datosCuit.length; i++) {
			           					cuit +=   (i === 0 ? "" : "<br>");
			           					var nombre = datosCuit[i];
			           					cuit += nombre;
			           				}
			           				
			           				return cuit;
			           			}
			           			return '-';
			           		}
			               },
			               { "mData" : "cliente",
			            	   "mRender" : function(data, type, row) {
			           			if (!$.isEmpty(data) && data != '-') {
			           				var datosClientes = data.split('-');
			           				var clientes = "";
			           				for (var i = 0; i < datosClientes.length; i++) {
			           					clientes +=   (i === 0 ? "" : "<br>");
			           					var nombre = datosClientes[i];
			           					clientes += nombre;
			           				}
			           				
			           				return clientes;
			           			}
			           			return '-';
			           		}
			               },
			               { "mData" : "bancoOrigen" },
			               { "mData" : "nroCheque"},
			               { "mData" : "estadoDescripcion"},
			               { "mData" : "motivo"},
			               { "mData" : "fechaUltimoEstado" },
			               { "mData" : "analista" ,
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.analista, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "copropietario" ,
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.copropietario, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "analistaTeamComercial" ,
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   if(!isUndefinedNullDashEmptyOrFalse(row.mailAnalistaTeamComercial)){
			            				   return generarCuadroUsuario(row.analistaTeamComercial, row.urlFotoAnalista, row.mailAnalista);
			            			   }
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "analistaCobranzas" ,
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.analistaCobranzas, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "analistaCobranzasCopropietario" ,
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.analistaCobranzasCopropietario, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "importe" },
			               { "mData" : "fechaAltaString" },
			               { "mData" : "fechaNotificacion" },
			               { "mData" : "fechaCierre" },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   var idLeg = data.idLegajo;
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver resumen" onclick="resumenLegajo('+idLeg+')"><i class="icon-eye-open bigger-120"></i></button></div>';
			            		   }
			            		   return null;
			            	   }
			               },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   if (data.esModificablePorUsuario == true) {
			            				   var idLeg = data.idLegajo;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarLegajo('+idLeg+')"><i class="icon-edit bigger-120"></i></button></div>';
			            			   }
			            		   }
			            		   return null;
			            	   }
			               },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   if (data.esAnulable == true) {
			            				   var idLeg = data.idLegajo;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularLegajo('+idLeg+')"><i class="icon-remove bigger-120"></i></button></div>';
			            			   }
			            		   }
			            		   return null;
			            	   }
			               },
			               
			],
//			"fnDrawCallback": function( oSettings ) {
//				if (!$.isEmpty(cobrosEncontrados) && cobrosEncontrados.fnGetData().length > 0) {
//					if (isAllCheckedPage('checkSelect', cobrosEncontrados)) {
//						$("#seleccionarTodos").prop('checked', true);
//					} else {
//						$("#seleccionarTodos").prop('checked', false);
//					}
//				}
//		    },
			'columnDefs': [	//targets son los th con las class date o importe
			               	{type: 'latam_date', targets: 'date'},
			               	{type: 'latam_datetime_seconds', targets: 'datetimeSeconds'},
			               	{type: 'comparador-currency', targets: 'importe'}
			]
	};

	legajosEncontrados = $("#legajosEncontrados").dataTable(legajosEncontradosSettings);

	
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaAltaDesdeLegajo").datepicker({firstDay : 1});
	$("#fechaAltaHastaLegajo").datepicker({firstDay : 1});
	$("#fechaAltaDesdeCheque").datepicker({firstDay : 1});
	$("#fechaAltaHastaCheque").datepicker({firstDay : 1});
	$("#fechaVencimientoCheque").datepicker({firstDay : 1});
	
	$('#selectCliente').change(function(){
		$('#textCliente').val('');
		$('#textCliente').attr('disabled',false);
		$('#errorTextCriterio').text('');
		if($.isEmpty(this.value)){
			$('#textCliente').attr('disabled',true);
		}else if (this.value == "BUSQUEDA_POR_CUIT"){
			$('#textCliente').attr('maxlength','13');
		} else {
			$('#textCliente').attr('maxlength','11');			
		}
	});
	
	// CARGAR COMBOS
	customCombobox("custom.combobox2");
	
	$("#selectAnalista").combobox2();
	$("#descripcionBanco").combobox2();

	$("#toggle").click(function() {
		$("#combobox2").toggle();
	});

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
	
	var segPrev = null;
	
	if(!$.isEmpty(legajoChequeRechazadoFiltro)){
		completarFiltroVolviendoABusqueda();
	}
	
	$("#selectSegmento").change(function() {
		if ($.isEmpty(this.value)) {
			segPrev = null;
			buscarAnalistasXDefecto(legajoChequeRechazadoFiltro.analista);
		} else {
			if (this.value != segPrev) {
				segPrev = this.value;
				buscarAnalistasXSegmento(this.value, legajoChequeRechazadoFiltro.analista);
			}
		};
	});
	$("#selectSegmento").change();
	
	$('#btnBuscar').click(function(){
		if(validarCampos()){
			buscarLegajo();
		}
	});
	
//	if(volviendoABusqueda){
//		$('#btnBuscar').click();
//	}
});
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
function returnIdBancosByAgrupador(idAgrupador, nameSelect) {
	var options = [];

	var corriente = null;
	
	for(var i = 0, size = bancoDescripcion.agrupadores.length; i < size; i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = bancoDescripcion.agrupadores[i];
 		if (corriente.idValue == idAgrupador) {
			for (var j = 0, sizeJ = corriente.bancos.length; j < sizeJ; j++) {
				var bancoCorriente = corriente.bancos[j];
				options.push(bancoCorriente.idBanco);
			}
			break;
		}
	}
	return options.join('-');
}
function buscarLegajo() {
	$('#bloqueEspera').trigger('click');
	$('#errorRespuestaLegajos').val('');
	var idBanco = $('#codigoBanco').val();
	var idBancos = [];

	if ($.isEmpty(idBanco)) {
		if (!$.isEmpty($('#descripcionBanco').val())) {
		idBancos = returnIdBancosByAgrupador($('#descripcionBanco').val()).split('-');
		}
	} else {
		idBancos.push(idBanco);
	}

	var legajoFiltro = {
			idEmpresa : $('#selectEmpresa').val(),
			idSegmento : $('#selectSegmento').val(),
			selectCliente : $('#selectCliente').val(),
			textCliente : $('#textCliente').val(),
			estado : $('#selectEstado').val(),
			idLegajo : $('#idLegajo').val(),
			numeroCheque : $('#nroCheque').val(),
			analista : $('#selectAnalista').val(),
			importeDesde : $('#importeDesde').val(),
			importeHasta : $('#importeHasta').val(),
			fechaAltaLegajoDesde : $('#fechaAltaDesdeLegajo').val(),
			fechaAltaLegajoHasta : $('#fechaAltaHastaLegajo').val(),
			fechaAltaChequeDesde : $('#fechaAltaDesdeCheque').val(),
			fechaAltaChequeHasta : $('#fechaAltaHastaCheque').val(),
			fechaVencimiento : $('#fechaVencimientoCheque').val(),
			codigosBancos : idBancos,
			descripcionBanco : $('#descripcionBanco').val(),
	};
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'legajo-cheque-rechazado/buscarLegajos',
		"data" : JSON.stringify(eval(legajoFiltro)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				legajosEncontrados.fnClearTable();			
				if (!$.isEmpty(result.resultados)) {
					legajosEncontrados.fnAddData(result.resultados, true);
				}
			} else {
				if (!$.isEmpty(result.errorMensaje)) {
					$('#errorRespuestaLegajos').text(result.errorMensaje);
				} else if (!$.isEmpty(result.errores)) {
					for (var i = 0, max = result.errores.length; i < max; i++) {
						$(result.errores[i].campoError).text(result.errores[i].descripcionError);
					}
				}
			}
			ocultarBloqueEspera();
		}
	});
}

function buscarAnalistasXDefecto(idAnalista) {
	$('#bloqueEspera').trigger('click');
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'legajo-busqueda/buscarAnalistaLegajos',
		"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : 'TODOS_LOS_SEGMENTOS' },
		"success" : function(result) {
			if (result.length == 1) {
				result[0].text = result[0].value + " - " + result[0].text;
				$("#selectAnalista").replaceOptions(result);
			} else {
				var options = [{text: 'Seleccione un item...', value : ''}];
				var legFiltroAnalista = options[0].value;
				$.each(result, function(index, option) {
					option.text = option.value + " - " + option.text;
					options.push(option);
					if(option.value === legajoChequeRechazadoFiltro.analista){
						legFiltroAnalista = option.value;
					}
				});
				//legajoChequeRechazadoFiltro.analista = null;
				$("#selectAnalista").replaceOptions(options);
				if ($.isEmpty($("#selectSegmento").val())) {
//					setearOpcionAnalista('');
					$("#selectAnalista").combobox2('refresh', '' , 'selectAnalista');
				} else {
//					setearOpcionAnalista(legFiltroAnalista);
					$("#selectAnalista").combobox2('refresh', legFiltroAnalista , 'selectAnalista');
				}
			}
			if (!volviendoABusqueda) {
				ocultarBloqueEspera();
			} else if (idAnalista !== undefined){
				$("#selectAnalista").combobox2('refresh', idAnalista , 'selectAnalista');
				$('#btnBuscar').click();
				volviendoABusqueda = false;
			}
		}
	});
}

function buscarAnalistasXSegmento(segmento , idAnalista) {
	$('#bloqueEspera').trigger('click');
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'legajo-busqueda/buscarAnalistaLegajos',
		"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : segmento},
		"success" : function(result) {
			if (result.length == 1) {
				result[0].text = result[0].value + " - " + result[0].text;
				$("#selectAnalista").replaceOptions(result);
			} else {
				var options = [{text: 'Seleccione un item...', value : ''}];
				var legFiltroAnalista = options[0].value;
				$.each(result, function(index, option) {
					option.text = option.value + " - " + option.text;
					options.push(option);
					
					if(option.value === legajoChequeRechazadoFiltro.analista){
						legFiltroAnalista = option.value;
					}
				});
				legajoChequeRechazadoFiltro.analista = null;
				$("#selectAnalista").replaceOptions(options);
				$("#selectAnalista").combobox2('refresh', legFiltroAnalista , 'selectAnalista');
//				setearOpcionAnalista(legFiltroAnalista);
			}
			if (!volviendoABusqueda) {
				ocultarBloqueEspera();
			} else if (idAnalista !== undefined){
				$("#selectAnalista").combobox2('refresh', idAnalista , 'selectAnalista');
				$('#btnBuscar').click();
				volviendoABusqueda = false;
			}
		}
	});
	
}


var validarCampos = function(){
	$("#errorTextCriterio").text('');
	$("#errorIdLegajo").text('');
	$("#errorNroCheque").text('');
	
	var validacionOk = true;
	if(!$.isEmpty($('#selectCliente').val())){		
		var opcion = $('#selectCliente').val();
		var value = $("#textCliente").val();
		if ("BUSQUEDA_POR_CUIT" === opcion && !$.isEmpty(value)) {
			if (!$.cuitValidarFormato(value)) {
				$("#errorTextCriterio").text(
					"Este campo debe respetar el formato 99-99999999-9."
				);
				validacionOk = false;
			} else if(!$.cuitValidarDigitoVerificador(value)){
				$("#errorTextCriterio").text(
						"El digito verificador es incorrecto."
					);
				validacionOk = false;
			}	else if($.cuitValidarDosPrimerodDigitos(value)){
				$("#errorTextCriterio").text(
						"Los primeros 2 digitos son incorrectos."
					);
				validacionOk = false;
			}
		} else if (!$.validacionIsNumeric(value) && !$.isEmpty(value)) {
				$("#errorTextCriterio").text(
					"El campo debe tener un valor numérico."
				);
				validacionOk = false;
		} else if( $.isEmpty(value) ){
				$("#errorTextCriterio").text("Este campo es requerido.");
				validacionOk = false;
		}
	}
	if (!$.validacionIsNumeric($('#idLegajo').val()) && !$.isEmpty($('#idLegajo').val())) {
		$("#errorIdLegajo").text("Solo se puede ingresar caracteres numericos.");
		validacionOk = false;
	}
	if (!$.validacionIsNumeric($('#nroCheque').val()) && !$.isEmpty($('#nroCheque').val())) {
		$("#errorNroCheque").text("Solo se puede ingresar caracteres numericos.");
		validacionOk = false;
	}
	if (!validarImporte('importeDesde')) {
		$("#errorImporteDesde").text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		validacionOk = false;
	}
	if (!validarImporte('importeHasta')) {
		$("#errorImporteHasta").text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		validacionOk = false;
	}
	if(!validarFecha('fechaAltaDesdeLegajo',false)){
		validacionOk = false;
	}
	if(!validarFecha('fechaAltaHastaLegajo',false)){
		validacionOk = false;
	}
	if(!validarFecha('fechaAltaDesdeCheque',false)){
		validacionOk = false;
	}
	if(!validarFecha('fechaAltaHastaCheque',false)){
		validacionOk = false;
	}
	if(!validarFecha('fechaVencimientoCheque',false)){
		validacionOk = false;
	}
	return validacionOk;
};

function validarImporte (nombreCampoImporte) {
	
	var importeError = $("#error" + nombreCampoImporte.substr(0,1).toUpperCase() + nombreCampoImporte.substr(1));
	var importe = $("#" + nombreCampoImporte).val();
	var salida = true;
	
	if($.isEmpty(importe)){
		importeError.text("");
		
	}else if(!validarImporteValido(importe)){
		importeError.text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		salida = false;
	}else {
		importeError.text("");
	}
	
	return salida;
}

function validarFecha(fechaNombre,esObligatorio) {
	var fechaInputError= $("#error" + fechaNombre.substr(0,1).toUpperCase() + fechaNombre.substr(1));
	fechaInputError.text("");
	var fecha = $('#' + fechaNombre).val();
	var salida = true;

	if ($.isEmpty(fecha)) {
		if (esObligatorio){
			fechaInputError.text("Este campo es requerido.");
			salida = false;
			
		} else {
			fechaInputError.text("");
		}
	} else if (!$.formatoFecha(fecha)) {
		fechaInputError.text("Este campo debe respetar el formato DD/MM/AAAA.");
		salida = false;
	} else if (!$.validacionFecha(fecha)) {
		fechaInputError.text("Debe ingresar una fecha valida.");
		salida = false;
	}
	return salida;
}

function completarFiltroVolviendoABusqueda(){
	
	if(!$.isEmpty(legajoChequeRechazadoFiltro.empresa)){
		$('#empresa').val(legajoChequeRechazadoFiltro.empresa);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.idSegmento)){
		$('#selectSegmento').val(legajoChequeRechazadoFiltro.idSegmento);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.selectCliente)){
		$('#selectCliente').val(legajoChequeRechazadoFiltro.selectCliente);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.textCliente)){
		$('#textCliente').val(legajoChequeRechazadoFiltro.textCliente);
		$('#textCliente').attr('disabled',false);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.estado)){
		$('#selectEstado').val(legajoChequeRechazadoFiltro.estado);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.idLegajo)){
		$('#idLegajo').val(legajoChequeRechazadoFiltro.idLegajo);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.fechaAltaLegajoDesde)){
		$('#fechaAltaDesdeLegajo').val(legajoChequeRechazadoFiltro.fechaAltaLegajoDesde);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.fechaAltaLegajoHasta)){
		$('#fechaAltaHastaLegajo').val(legajoChequeRechazadoFiltro.fechaAltaLegajoHasta);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.numeroCheque)){
		$('#nroCheque').val(legajoChequeRechazadoFiltro.numeroCheque);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.importeDesde)){
		$('#importeDesde').val(legajoChequeRechazadoFiltro.importeDesde);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.importeHasta)){
		$('#importeHasta').val(legajoChequeRechazadoFiltro.importeHasta);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.fechaAltaChequeDesde)){
		$('#fechaAltaDesdeCheque').val(legajoChequeRechazadoFiltro.fechaAltaChequeDesde);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.fechaAltaChequeHasta)){
		$('#fechaAltaHastaCheque').val(legajoChequeRechazadoFiltro.fechaAltaChequeHasta);
	}
	if(!$.isEmpty(legajoChequeRechazadoFiltro.fechaVencimiento)){
		$('#fechaVencimientoCheque').val(legajoChequeRechazadoFiltro.fechaVencimiento);
	}
	
	if (
		!$.isEmpty(legajoChequeRechazadoFiltro.codigosBancos) &&
		legajoChequeRechazadoFiltro.codigosBancos.length === 1
	) {
		$('#codigoBanco').val(legajoChequeRechazadoFiltro.codigosBancos[0]);
		$('#codigoBanco').change();
	} else if (!$.isEmpty(legajoChequeRechazadoFiltro.descripcionBanco)) {
		$("#descripcionBanco").combobox2('select1', legajoChequeRechazadoFiltro.descripcionBanco , 'descripcionBanco');
	}

}

function resumenLegajo(idLegajo) {
	$('#bloqueEspera').trigger('click');
	$('#idLegajo').val(idLegajo);
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/legajo-cheque-rechazado-detalle";
	$('#formBusqueda')[0].submit();
};

function editarLegajo(idLegajo) {
	$('#bloqueEspera').trigger('click');
	$('#idLegajo').val(idLegajo);
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/editar-legajo-cheque-rechazado";
	$('#formBusqueda')[0].submit();
};

function anularLegajo(idLegajo) {
	$('#bloqueEspera').trigger('click');
	var mensaje = 'El legajo ' + idLegajo + ' será anulado, ¿desea continuar?';
	bootbox.confirm(mensaje, function(result) {
		if (result) {
			$('#idLegajo').val(idLegajo);
			$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/anularLegajo";
			$('#formBusqueda')[0].submit();
		} else {
			ocultarBloqueEspera();
			salir = true;
		}
	});
	
};

function volverBusqueda() {
	$('#goBack').val("true");
	$('#formBusqueda')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formBusqueda')[0].submit();
};
//fin Combo Banco Desposito
function vaciarCombo(comboName) {
	$('#' + comboName).empty();
}