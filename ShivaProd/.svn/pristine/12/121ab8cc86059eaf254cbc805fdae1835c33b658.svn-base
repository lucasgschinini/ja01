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


var BTN_NO_CLASS = 'btn-no-class';
var CSS_SELECTED_ROW = 'LightGray';
var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_BLANK_MENOS = '<i class="icon-minus bigger-120 icon-hidden"></i>';
var HTML_ICON_MENOS = '<i class="icon-minus bigger-120"></i>';

function agregarOpcionCombo(comboId, valor, descripcion) {
	var opt = document.createElement("option");
	comboId.options.add(opt);
	opt.text = descripcion;
	opt.value = valor;
};

function mostarTipoValor(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;

	switch (tipoValorSeleccionado) {
	//case "5": // Cheque
	case TipoValorEnum.CHEQUE.idTipoValor.toString():
		bloqueBDIBB.style.display = 'none';
		document.getElementById("selectTipoImpuesto").value = "";
		bloqueDocumento.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaTransferencia.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNumeroCheque.style.display = 'block';
		bloqueNumeroChequeError.style.display = 'block';
		bloqueNroreferencia.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueCuit.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueRecibo.style.display = 'block';
		bloqueFechaEmisionCheques.style.display ='block';
		$("#inputFechaEmisionCheque").val("");
		bloqueFechaNotificacionValor.style.display ='block';
		break;
	//case "6": // ChequeDiferido
	case TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString():
		bloqueBDIBB.style.display = 'none';
		document.getElementById("selectTipoImpuesto").value = "";
		bloqueDocumento.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaTransferencia.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNumeroCheque.style.display = 'block';
		bloqueNumeroChequeError.style.display = 'block';
		bloqueNroreferencia.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'block';
		bloqueCuit.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueRecibo.style.display = 'block';
		bloqueFechaEmisionCheques.style.display ='block';
		$("#inputFechaEmisionCheque").val("");
		bloqueFechaNotificacionValor.style.display ='block';
		break;
	//case "7": // Efectivo
	case TipoValorEnum.EFECTIVO.idTipoValor.toString():	
		bloqueBDIBB.style.display = 'none';
		document.getElementById("selectTipoImpuesto").value = "";
		bloqueDocumento.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaTransferencia.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNumeroCheque.style.display = 'none';
		bloqueNumeroChequeError.style.display = 'none';
		bloqueNroreferencia.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueCuit.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueRecibo.style.display = 'block';
		bloqueFechaEmisionCheques.style.display ='none';
		bloqueFechaNotificacionValor.style.display ='block';
		break;
	//case "8": // Transferencia
	case TipoValorEnum.TRANSFERENCIA.idTipoValor.toString():
		bloqueBDIBB.style.display = 'none';
		document.getElementById("selectTipoImpuesto").value = "";
		bloqueDocumento.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaTransferencia.style.display = 'block';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNumeroCheque.style.display = 'none';
		bloqueNumeroChequeError.style.display = 'none';
		bloqueNroreferencia.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueCuit.style.display = 'block';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueRecibo.style.display = 'none';
		bloqueFechaEmisionCheques.style.display ='none';
		bloqueFechaNotificacionValor.style.display ='none';
		break;
	//case "10": // Retencion/Impuesto
	case TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString():
		bloqueBDIBB.style.display = 'none';
	
		bloqueDocumento.style.display = 'none';
		bloqueAcuerdo.style.display = 'none';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaTransferencia.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNumeroCheque.style.display = 'none';
		bloqueNumeroChequeError.style.display = 'none';
		bloqueNroreferencia.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueCuit.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'block';
		bloqueRecibo.style.display = 'block';
		bloqueFechaEmisionCheques.style.display ='none';
		bloqueFechaNotificacionValor.style.display ='block';
		break;
	default:
		bloqueBDIBB.style.display = 'none';
		document.getElementById("selectTipoImpuesto").value = "";
		bloqueDocumento.style.display = 'none';
		bloqueAcuerdo.style.display = 'none';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaTransferencia.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNumeroCheque.style.display = 'none';
		bloqueNumeroChequeError.style.display = 'none';
		bloqueNroreferencia.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueCuit.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueRecibo.style.display = 'none';
		bloqueFechaEmisionCheques.style.display ='none';
		bloqueFechaNotificacionValor.style.display ='none';
		break;
	}
};
//MOSTRAR BLOQUE IBB
function mostrarIBB(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;
	switch (tipoValorSeleccionado) {
	case "1":
		bloqueBDIBB.style.display = 'block';
		break;
	default:
		//document.getElementById("selectTipoComprobante").value = "";
		//document.getElementById("selectLetraComprobante").value = "";
		//document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueBDIBB');
		bloqueBDIBB.style.display = 'none';
		break;
	}
};

function mostrarTipoComprobante(referenciaCombo) {
	var tipoComprobanteSeleccionado = referenciaCombo.value;
	if(tipoComprobanteSeleccionado == ""){
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		checkLetraComprobante.style.display = 'none';
		checkNumeroLegalComprobante.style.display = 'none';
	} else {
		checkLetraComprobante.style.display = 'inline';
		checkNumeroLegalComprobante.style.display = 'inline';
	}
};
function mostrarTipoComprobanteOnload(referenciaCombo) {
	var tipoComprobanteSeleccionado = referenciaCombo.value;
	if(tipoComprobanteSeleccionado == ""){
		checkLetraComprobante.style.display = 'none';
		checkNumeroLegalComprobante.style.display = 'none';
	} else {
		checkLetraComprobante.style.display = 'inline';
		checkNumeroLegalComprobante.style.display = 'inline';
	}
};
var limpiarCamposByCambioTipoAviso = function() {
	$('#inputImporte').val('');
	$('#selectMotivo').val('');
	$('#inputOperacionAsociada').val('');
	$('#inputNumeroDocumentoContable').val('');
	clearSelectInput('bloqueBDIBB');
	clearSelectInput('bloqueAcuerdo');
	clearSelectInput('bloqueNroBoleta');
	clearSelectInput('bloqueFechaTransferencia');
	clearSelectInput('bloqueBancoOrigen');
	$('#descripcionBanco').combobox2("refresh", '' , 'descripcionBanco');
	$('#codigoBanco').val('').change();
	$('#selectAcuerdo').val('').change();
	
	clearSelectInput('bloqueNumeroCheque');
	clearSelectInput('bloqueNroreferencia');
	clearSelectInput('bloqueFechaVencimiento');
	clearSelectInput('bloqueCuit');
	clearSelectInput('bloqueTipoImpuesto');
	clearSelectInput('bloqueRecibo');
	$('#observaciones').val('');
	$('.error').text('');
	$('#inputFechaEmisionCheque').val('');
	$('#inputFechaNotificacionValor').val('');
};
var validarCampos = function() {
	if (!$.isEmpty($('#inputImporte').val())) {
		return true;
	}
	if (!$.isEmpty($('#selectMotivo').val())) {
		return true;
	}
	if (!$.isEmpty($('#inputOperacionAsociada').val())) {
		return true;
	}
	if (!$.isEmpty($("#observaciones").val())) {
		return true;
	}
	if ($.isEmpty($('#selectSegmento').val())) {
		return false;
	}
	if (!$.isEmpty($("#selectMotivo").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputOperacionAsociada").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputNumeroDocumentoContable").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputNroBoleta").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputNroConstancia").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaDeposito").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputReciboPreImpreso").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaTransferencia").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputNumeroCheque").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputCuit").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputNumeroReferencia").val())) {
		return true; 
	}
	if (!$.isEmpty($("#inputFechaVencimiento").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaEmision").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaEmision").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaIngresoRecibo").val())) {
		return true;
	}
	if (!$.isEmpty($("#inputFechaIngresoRecibo").val())) {
		return true;
	}
	if (!$.isEmpty($("#observaciones").val())) {
		return true;
	}
	if (!$.isEmpty($("#selectTipoImpuesto").val())) {
		return true;
	}
	if (!$.isEmpty($("#descripcionBanco").val())) {
		return true;
	}
	if (!$.isEmpty($("#selectAcuerdo").val())) {
			return true;
	}
	if (!$.isEmpty($("#selectBancoDeposito").val())) {
		return true;
	}
	if (!$.isEmpty($("#selectNumeroCuenta").val())) {
		return true;
	}

	return false;
};
$(document).ready(function() {

//	$('#inputCodCliente').autocomplete({
//		minLength: 0,
//		source: codigos,
//		select: function( event, ui ) {
//			var value = ui.item.value;
//			var codigoClienteLegado = value.split("-")[0].split(" ").join("");
//			$( "#inputCodCliente" ).val(codigoClienteLegado);
//			return false;
//		}
//	});
	
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
	
/*****************************************************************************/
/* Configuracion de tabla "listaValores"
* 
****************************************************************(*************/
	combos.bancoOrigen = $('#codigoBanco option');
	// Combos Autocompletados
	customCombobox("custom.combobox2");
	$("#descripcionBanco").combobox2();
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
		'select': function(event, item) {
			vaciarCombo('selectAcuerdo');
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
				"mData" : "tipoValor",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "idAcuerdo",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "bancoDeposito",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "numeroCuenta",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "importe",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaDeposito",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaEmision",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data) ||TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() != row.idTipoValor) {
						return '-';
					} 
					return data;
				}
			}, {
				"mData" : "fechaTransferencia",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "bancoOrigen",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "reciboPreImpreso",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaIngresoRecibo",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "motivo",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "numeroCheque",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "fechaVencimiento",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
				"mData" : "numeroReferencia",
				"mRender" : function(data, type, row) {
					if ($.isEmpty(data)) {
						return '-';
					}
					return data;
				}
			}, {
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

/******************************************************************************/
//	// Muestro los campos de valores de acuerdo al valor seleccionado por defecto.
//	mostarTipoValor(document.getElementById('selectTipoValor'));
//	mostrarIBB(document.getElementById('selectTipoImpuesto'));
//	mostrarTipoComprobanteOnload(document.getElementById('selectTipoComprobante'));
	
//	if(<c:out value = "${imprimibleArchivo}"/>) {
//		window.location ="${pageContext.request.contextPath}/abrir-documento";
//	}
	
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
	
	
	$('#btnCancelar').click(function() {
		$('#bloqueEspera').trigger('click');
	});

	//MANEJO DE FECHAS	
	$('#inputFechaDeposito').datepicker();
	$('#inputFechaVencimiento').datepicker();
	$('#inputFechaTransferencia').datepicker();
	$('#inputFechaEmision').datepicker();
	$('#inputFechaIngresoRecibo').datepicker();
	
	$('#inputFechaNotificacionValor').datepicker();
	$('#inputFechaEmisionCheque').datepicker();
	
	$("#selectSegmento").focus(function() {
		segPrev = this.value;
	}).change(function() {
		if ($.isEmpty(this.value)) {
			var options = [{
				text : 'Seleccione un item...',
				value : ''
			}];
			limpiarCamposByCambioTipoAviso();
			mostarTipoValor('XX');
			completarComboTipoValor();
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
					ocultarBloqueEspera();
				}
			});
		}
	});
	$("#selectTipoValor").change(function () {
		$('#bloqueEspera').trigger('click');
		mostarTipoValor(this);
		limpiarCamposByCambioTipoAviso();
		// Si el tipo valor es Retencion/Impuesto no tiene combos banco
		if (this.value != '10') {
			completarCombosAcuerdoBancoDepositoCuenta(6);
		}
		if (this.value == '7') {
			$("#numeroBoletaObligatorio").show();
		} else {
			$("#numeroBoletaObligatorio").hide();
		}
		if (this.value != '5' && this.value != '6' && this.value !='7') {
			$("#rojofechaIngresoRecibo").show();
		} else {
			$("#rojofechaIngresoRecibo").hide();
		}
		if (!$.isEmpty(edicionValorDto)) {
			llenarFormEdicion();
		}
		ocultarBloqueEspera();
	});
/******************************************************************************/
/* Evento al agregar
 *
 ******************************************************************************/
//	$('#btnAgregarOtroDeb').click(function() {
		
//		agregarOtrosDeb();
//		totalDeDocumentos.actualizar();
//
//	});
//	$("#textFechaVencimientoOtrosDeb").datepicker({
//		firstDay : 1
//	});
	$('#btnAgregarValor').click(function() {
		$('#bloqueEspera').trigger('click');
		var aviso = {
				"idValor" : 1213213
			};
			$.ajax({
				"type" : "POST",
				"url" : "isAliveAvisos",
				"dataType" : "json",
				"data" : JSON.stringify(eval(aviso)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
//					console.log('isAlive');
				},
				"error" : function() {
					beforeUnload.off();
					// Para que false
					$('#formCobro')[0].action = urlAsynchronousHTTP + "/valores-avisos-alta";
					$('#formCobro')[0].submit();
				}
			});
//		if ($.isEmpty(datosTablas.clientesSeleccionados)){
//			$("#errorClienteSeleccionado").val(errorClienteSeleccionado);
//		} else {
//			$("#errorClienteSeleccionado").val("");
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
					if ($.isEmpty(datosTablas.listaValores)) {
						//TODO: VER SI FUNCA ACÁ
						aparienciaButton.ocultarButtonMenosClientesSeleccionados(tablas.clientesSeleccionados);
//						aparienciaButton.ocultarButtonClientesSeleccionados(tablas.clientes,tablas.clientesSeleccionados);
						
					}
					datosTablas.listaValores.push(valorDto);
					tablas.listaValores.fnAddData([ valorDto ], true);
					
					tablas.listaValores.fnSort([[0, 'asc']]);
					limpiarCamposByCambioTipoAviso();
					
					if (!$('#mantenerComprobantesAdjuntosVal').is(':checked')) {
						tablas.comprobantes.fnClearTable();
						datosTablas.comprobantes = [];
					}
				} else {
					$('#alertError').text(error.mensaje);
					$('#divAlertError').show();
				}
			}
//		}
		
		ocultarBloqueEspera();
	});
	function onClickBtnAceptar() {
		$('#bloqueEspera').trigger('click');
		$('#btnAceptar').attr('disabled', true);
		var valoresDto = {
			valorDto: datosTablas.listaValores[0],
			listaValoresDto: datosTablas.listaValores,
			generarConstancia: false,
			mantenerComprobantesAdjuntos: false
		};
		$('#divAlertErrorFoot').hide();

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'valor/crear-aviso-de-pago',
			"data" : JSON.stringify(eval(
				valoresDto
			)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					$('#mensajeAlta').val(result.mensajeAlta);
					$('mensajeAltaDuplicado').val(result.mensajeAltaDuplicado);
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

		// fixedColumns(tablas.clientes, 1, 0);
		
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
							
							aparienciaButton.ocultarButtonPlus(
									tablas.clientes,
									cliente.idClienteLegado,
									j
							);
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
//	 		}
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
		
		});
});
/******************************************************************************/
/* Funciones para el manejo de los combos Acuerdo, banco y cuenta
 *
 ******************************************************************************/
function completarCombosAcuerdoBancoDepositoCuenta(origen) {
	var seg = $('#selectSegmento').val();
	var tipoValor = $('#selectTipoValor').val();
	var size = paramTipoGestion.lista.length;
	var segmentoEncontrado = false;

	for(var index = 0; index < size; index++) {
		if (
			paramTipoGestion.lista[index].idSegmento === seg &&
			paramTipoGestion.lista[index].idTipoValor == tipoValor &&
			paramTipoGestion.lista[index].idOrigen == 6
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
	var ops;
	ops = [new Option(leyendaComboSeleccionar, '')];
 	var op = null;
	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaAcuerdos[index].descripcion, combo.listaAcuerdos[index].idAcuerdo);
		ops.push(op);
	}
	size = combo.listaBancos.length;
	combosAcuerdoBancoDepositoCuenta.acuerdo = ops;
	$('#selectAcuerdo').replaceOptions(ops);
	ops = [new Option(leyendaComboSeleccionar, '')];
	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaBancos[index].descripcion + ' - ' + combo.listaBancos[index].idBanco, combo.listaBancos[index].idBanco);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.banco = ops;
	$('#selectBancoDeposito').replaceOptions(ops);
	ops = [new Option(leyendaComboSeleccionar, '')];
	var sizeCuenta = combo.listaCuentas.length;
	
	for (var index = 0; index < sizeCuenta; index++) {
		op = new Option(combo.listaCuentas[index].cuentaBancaria, combo.listaCuentas[index].idBancoCuenta);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.bancoCuenta = ops;
	$('#selectNumeroCuenta').replaceOptions(ops);
	

	
	$("#selectBancoDeposito").combobox2('refresh');
}
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

function vaciarCombo(comboName) {
	$('#' + comboName).empty();
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
	valorDto.tipoImpuesto = obtenerDescripcionSelect("selectTipoImpuesto");
	valorDto.provincia = obtenerDescripcionSelect("selectProvincia");
	valorDto.tipoComprobante = obtenerDescripcionSelect("selectTipoComprobante");
	valorDto.letraComprobante = obtenerDescripcionSelect("selectLetraComprobante");
	valorDto.importe = $('#inputImporte').val();
	valorDto.fechaDeposito = $('#inputFechaDeposito').val();
	valorDto.fechaEmision = $('#inputFechaEmision').val();
	valorDto.fechaTransferencia = $('#inputFechaTransferencia').val();
	valorDto.reciboPreImpreso = $('#inputReciboPreImpreso').val();
	valorDto.fechaIngresoRecibo = $('#inputFechaIngresoRecibo').val();
//	valorDto.numeroCheque = $('#inputNumeroDocumentoContable').val();
	valorDto.fechaVencimiento = $('#inputFechaVencimiento').val();
	valorDto.numeroReferencia = $('#inputNumeroReferencia').val();
	
	valorDto.idEmpresa = $('#selectEmpresa').val();
	valorDto.idSegmento = $('#selectSegmento').val();
	valorDto.email = $('#email').val();

	valorDto.nombreAnalista = $('#nombreAnalista').val();
	valorDto.idAnalista = $('#idAnalista').val();
	valorDto.mailAnalista = ''; 												// No veo que se carge en ningun lado
	valorDto.idCopropietario = $('#selectCopropietario').val();

	valorDto.idTipoValor = $('#selectTipoValor').val();

	valorDto.importe = $('#inputImporte').val();

	valorDto.idMotivo = $('#selectMotivo').val();

	valorDto.operacionAsociada = $('#inputOperacionAsociada').val();
	//
	valorDto.origen = '';														// No se usa
	valorDto.idOrigen = '';														// No se usa
	
	valorDto.fechaNotificacionDisponibilidadRetiroValor = $("#inputFechaNotificacionValor").val();
	if (!$.isEmpty($('#inputFechaNotificacionValor').val())) {
		valorDto.fechaNotificacionDisponibilidadRetiroValorAux = $("#inputFechaNotificacionValor").datepicker("getDate");
	}

	if (TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() !== valorDto.idTipoValor) {
		valorDto.idAcuerdo = $('#selectAcuerdo').val();
		valorDto.idBancoDeposito = $('#selectBancoDeposito').val();
		valorDto.idNroCuenta = $('#selectNumeroCuenta').val();

		valorDto.numeroDocumentoContable = $('#inputNumeroDocumentoContable').val();
	} else {
		valorDto.idTipoImpuesto = $('#selectTipoImpuesto').val();
		valorDto.nroConstancia = $('#inputNroConstancia').val();
		valorDto.fechaEmision = $('#inputFechaEmision').val();

		if (valorDto.idTipoImpuesto == 1) {
			valorDto.cuitIbb = $('#cuitIbb').val();
			valorDto.idProvincia = $('#selectProvincia').val();
			valorDto.idTipoComprobante = $('#selectTipoComprobante').val();
			valorDto.idLetraComprobante = $('#selectLetraComprobante').val();
			valorDto.numeroLegalComprobante = $('#inputNumeroLegalComprobante').val();
		}
	}
	
	if (
		TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() !== valorDto.idTipoValor &&
		TipoValorEnum.EFECTIVO.idTipoValor.toString() !== valorDto.idTipoValor
	) {
		valorDto.idBancoOrigen = $('#codigoBanco').val();
	}
	if (
		TipoValorEnum.CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		valorDto.numeroCheque = $('#inputNumeroCheque').val();
		valorDto.fechaEmision = $("#inputFechaEmisionCheque").val();
		
	}
	if (
		TipoValorEnum.CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
 		TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.EFECTIVO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		valorDto.numeroBoleta = $('#inputNroBoleta').val();
		valorDto.fechaDeposito = $('#inputFechaDeposito').val();
	}
	if (TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor) {
		valorDto.fechaVencimiento = $('#inputFechaVencimiento').val();
	}
	
	if (TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() !== valorDto.idTipoValor) {
		valorDto.reciboPreImpreso = $('#inputReciboPreImpreso').val();
		valorDto.fechaIngresoRecibo = $('#inputFechaIngresoRecibo').val();
	} else {
		valorDto.cuit = $('#inputCuit').val();
		valorDto.fechaTransferencia = $('#inputFechaTransferencia').val();
		valorDto.numeroReferencia = $('#inputNumeroReferencia').val();
	}
	
	
	valorDto.observaciones = $('#observaciones').val();

	valorDto.observacionMail = '';												// No se usa
	
	valorDto.listaComprobantes = datosTablas.comprobantes;
	//valorDto.imprimirBoleta													// No se usa
	//valorDto.enviarBoletaMail													// No se usa
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

	valorDto.cliente = cliente;

	if (!$.isEmpty($('#inputFechaEmisionCheque').val())) {
		valorDto.fechaEmisionAux = $("#inputFechaEmisionCheque").datepicker("getDate");
	} 
	
	
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

function generarIdDinamico(prefix) {
	return prefix + '_' + Date.now();
}

function eliminarEnTabla(objeto, tabla) {
	var pos = tabla.indexOf(objeto);
	if (pos > -1) {
		tabla.splice(pos, 1);
	}
//	if ($.isEmpty(tabla)) {
//		$('#inputCodCliente').attr('disabled', false);
//	}
}


function editar(idValor) {
	$('#bloqueEspera').trigger('click');
	$('.error').text('');
	$('#alertError').text('');
	$('#divAlertError').hide();
	limpiarCamposByCambioTipoAviso();
	$(tablas.listaValores.fnGetData()).each(function(j, seleccionado) {
		if (seleccionado.id == idValor) {
			llenarForm(seleccionado);
			tablas.listaValores.fnDeleteRow(j);
			eliminarEnTabla(seleccionado, datosTablas.listaValores);
			if ($.isEmpty(datosTablas.listaValores)) {
				
				$(tablas.clientesSeleccionados.fnGetData()).each( function(j,cliente) {
					aparienciaButton.descubrirButtonMenosClienteSeleccionado(tablas.clientesSeleccionados, cliente.idClienteLegado);
				});
			}
			return false;
		}
	});
	
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
	if ($.isEmpty(datosTablas.listaValores)) {
		
		$(tablas.clientesSeleccionados.fnGetData()).each( function(j,cliente) {
			aparienciaButton.descubrirButtonMenosClienteSeleccionado(tablas.clientesSeleccionados, cliente.idClienteLegado);
		});
	}
	ocultarBloqueEspera();
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
function seleccionarTipoValor() {
	var valorDto = edicionValorDto;
	$('#selectCopropietario').val(valorDto.idCopropietario);
	$('#selectTipoValor').val(valorDto.idTipoValor).change();
}
function llenarFormEdicion() {
	var valorDto = edicionValorDto;
	$('#inputImporte').val(valorDto.importe);
	$('#inputFechaDeposito').val(valorDto.fechaDeposito);
	$('#inputFechaEmision').val(valorDto.fechaEmision);
	$('#inputFechaTransferencia').val(valorDto.fechaTransferencia);
	$('#inputReciboPreImpreso').val(valorDto.reciboPreImpreso);
	$('#inputFechaIngresoRecibo').val(valorDto.fechaIngresoRecibo);
	$('#inputNumeroDocumentoContable').val(valorDto.numeroCheque);
	$('#inputFechaVencimiento').val(valorDto.fechaVencimiento);
	$('#inputNumeroReferencia').val(valorDto.numeroReferencia);

	
	$('#nombreAnalista').val(valorDto.nombreAnalista);
	//valorDto.idAnalista = $('#idAnalista').val();
	valorDto.mailAnalista = ''; 												// No veo que se carge en ningun lado
	
	$('#selectMotivo').val(valorDto.idMotivo);

	$('#inputOperacionAsociada').val(valorDto.operacionAsociada);
	
	$('#inputFechaNotificacionValor').val(valorDto.fechaNotificacionDisponibilidadRetiroValor);
	//
	//valorDto.origen = '';														// No se usa
	//valorDto.idOrigen = '';														// No se usa

	if (TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() !== valorDto.idTipoValor) {
		$('#selectAcuerdo').val(valorDto.idAcuerdo).change();
		$('#selectBancoDeposito').val(valorDto.idBancoDeposito);
		$('#selectNumeroCuenta').val(valorDto.idNroCuenta);

		
		$('#inputNumeroDocumentoContable').val(valorDto.numeroDocumentoContable);
	} else {
		$('#selectTipoImpuesto').val(valorDto.idTipoImpuesto);
		
		mostrarIBB(document.getElementById('selectTipoImpuesto'));
		$('#inputNroConstancia').val(valorDto.nroConstancia);
		$('#inputFechaEmision').val(valorDto.fechaEmision);

		if (valorDto.idTipoImpuesto == 1) {
			$('#cuitIbb').val(valorDto.cuitIbb);
			$('#selectProvincia').val(valorDto.idProvincia);
			$('#selectTipoComprobante').val(valorDto.idTipoComprobante);
			$('#selectLetraComprobante').val(valorDto.idLetraComprobante);
			$('#inputNumeroLegalComprobante').val(valorDto.numeroLegalComprobante);
		}
	}
	
	if (
		TipoValorEnum.RETENCIÓN_IMPUESTO.idTipoValor.toString() !== valorDto.idTipoValor &&
		TipoValorEnum.EFECTIVO.idTipoValor.toString() !== valorDto.idTipoValor
	) {
		$('#codigoBanco').val(valorDto.idBancoOrigen).change();
	}
	if (
		TipoValorEnum.CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		$('#inputNumeroCheque').val(valorDto.numeroCheque);
		$('#inputFechaEmisionCheque').val(valorDto.fechaEmision);
	}
	if (
		TipoValorEnum.CHEQUE.idTipoValor.toString() === valorDto.idTipoValor ||
 		TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor ||
		TipoValorEnum.EFECTIVO.idTipoValor.toString() === valorDto.idTipoValor
	) {
		$('#inputNroBoleta').val(valorDto.numeroBoleta);
		$('#inputFechaDeposito').val(valorDto.fechaDeposito);
	}
	if (TipoValorEnum.CHEQUE_DIFERIDO.idTipoValor.toString() === valorDto.idTipoValor) {
		$('#inputFechaVencimiento').val(valorDto.fechaVencimiento);
	}
	
	if (TipoValorEnum.TRANSFERENCIA.idTipoValor.toString() !== valorDto.idTipoValor) {
		$('#inputReciboPreImpreso').val(valorDto.reciboPreImpreso);
		$('#inputFechaIngresoRecibo').val(valorDto.fechaIngresoRecibo);
	} else {
		$('#inputCuit').val(valorDto.cuit);
		$('#fechaTransferencia').val(valorDto.fechaTransferencia);
		$('#inputNumeroReferencia').val(valorDto.numeroReferencia);
	}
	
	
	$('#observaciones').val(valorDto.observaciones);

	valorDto.observacionMail = '';												// No se usa
	
	if (!$.isEmpty(valorDto.listaComprobantes)) {
		tablas.comprobantes.fnClearTable();
		datosTablas.comprobantes = [];

		datosTablas.comprobantes = valorDto.listaComprobantes;
		tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);
		
	}
	edicionValorDto = null;
	ocultarBloqueEspera();
}

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




//Se llama cuando se realiza una nueva busqueda y ya hay clientes
// seleccionados
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
//			if (cliente.idClienteLegado == id ){
				aparienciaButton.descubrirButtonMenos(tabla, id, j);
				return false;
				
//			} else if (cliente.idClienteLegado != id 
//					&& datosTablas.clientesSeleccionados.length == 0) {
//				aparienciaButton.descubrirButtonMenos(tabla, cliente.idClienteLegado, j);
//				return false;
//			}

		});
	},
	'ocultarButtonMenosClientesSeleccionados' : function(tablaSeleccionados) {
		
		$(tablaSeleccionados.fnGetData()).each(function(j, clienteSeleccionado) {
			
//			$(tabla.fnGetData()).each(function(i, cliente) {
//				if (clienteSeleccionado.idClienteLegado == cliente.idClienteLegado
//						|| (clienteSeleccionado.idClienteLegado != cliente.idClienteLegado 
//								&& datosTablas.clientesSeleccionados.length > 0)) {
					aparienciaButton.ocultarButtonMenos(tablaSeleccionados, clienteSeleccionado.idClienteLegado, j);
					return false;
//				}
//			});
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
