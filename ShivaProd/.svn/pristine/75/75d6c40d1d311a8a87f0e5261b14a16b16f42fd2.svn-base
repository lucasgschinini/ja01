
$('#main :input').attr('disabled', true);
$('#main :button').attr('disabled', true);
$('#main :select').attr('disabled', true);

var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100; 

var CSS_SELECTED_ROW = 'LightGray';

var TRASLADA_INTERESES_LECTURA = 4;
var TRASLADA_INTERESES_NO = 0;
var TRASLADA_INTERESES_DIFERENCIA = 3;
var TRASLADA_INTERESES = 2;
var TRASLADA_ASTERISCOS = 5;

var tablas = {
		transaccionesPrev: null,
		prevComprobanteApliManual: null,
		prevAdjunto: null
};

var datosTablas = {
		transacciones : [],
		prevComprobanteApliManual: [],
		prevAdjunto: []
};
var trCapsBackgroundColor = '';
//Control de colores de grilla de transaccion
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
//Guido.Settecerze
$(document).ready(function() {
	
	
    $.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaAltaTramReint").datepicker({firstDay : 1});
    
	var prevClientes = 'prevClientes';
	inicializarTablaConExportacionAXlsConNombreOrdenada(prevClientes, 'CobroClientesSeleccionados', [[ 1, "asc"]]);
    var prevDebitos = 'prevDebitos';
    inicializarTablaConExportacionAXlsConNombre(prevDebitos, 'CobroDebitosSeleccionados');
    //MAR
    var prevOtrosDebitos = 'prevOtrosDebitos';
    inicializarTablaConExportacionAXlsConNombre(prevOtrosDebitos, 'CobroOtrosDebitosSeleccionados');
    var prevAdjunto = 'prevAdjunto';
   	inicializarTablaConExportacionAXlsConNombre(prevAdjunto, 'CobroAdjunto');
    var prevCreditos = 'prevCreditos';
    inicializarTablaConExportacionAXlsConNombre(prevCreditos, 'CobroCreditosSeleccionados');
    var prevMediosPagos = 'prevMediosPagos';
    inicializarTablaConExportacionAXlsConNombre(prevMediosPagos, 'CobroMediosDePago');
    var prevDocumentosCap = 'prevDocumentosCap';
    //inicializarTablaConExportacionAXlsConNombre(prevDocumentosCap, 'CobroDocumentosCap');
    
    inicializarTablaConExportacionAXlsConNombreDom(prevDocumentosCap, 'CobroDocumentosCap', 'Bft', function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		if ($(nRow).hasClass('even')) {
			$(nRow).removeClass('even');
		} else {
			$(nRow).removeClass('odd');
		}
		var tipoRenglon = $(nRow).find('td.noClassTipoRenglon').text();
		var even = $(nRow).find('td.noClassEven').text();
		var noClassFin =  $(nRow).find('td.noClassFin').text();
		if (tipoRenglon === T_RENGLON_AGRUPADOR) {
			if (even === 'true') {
				//$('td', nRow).css('background-color', '');
				$(nRow).addClass('even');
				$('td', nRow).css('border-bottom', 'none');
				trCapsBackgroundColor = 'even';
			} else {
				//$('td', nRow).css('background-color', '#F2F2F7');
				$('td', nRow).css('border-bottom', 'none');
				$(nRow).addClass('odd');
				trCapsBackgroundColor = 'odd';
			}
		} else if (noClassFin === 'true') {
			//$('td', nRow).css('background-color', trCapsBackgroundColor);
			$(nRow).addClass(trCapsBackgroundColor);
			$('td', nRow).css('border-top', 'none');
			$('td', nRow).css('color', CSS_SELECTED_ROW);
		} else {
			//$('td', nRow).css('background-color', trCapsBackgroundColor);
			$(nRow).addClass(trCapsBackgroundColor);
			$('td', nRow).css('border-top', 'none');
			$('td', nRow).css('border-bottom', 'none');
			$('td', nRow).css('color', CSS_SELECTED_ROW);
		}
	 });
    
    var prevComprobantes = 'prevComprobantes';
    inicializarTablaConExportacionAXlsConNombre(prevComprobantes, 'CobroComprobantes');
    
   
    
    var transaccionesPrevSettings = {
		dom : '<"botonExcelTransaccionesPrevSettings">Bfrtip',
				"sScrollX": true,
				"scrollY": SCROLL_Y,
				"bScrollCollapse": true,
				"iDisplayLength" : DISPLAY_LENGTH,
				"iDisplayStart" : 0,
				buttons: [{	
					extend: "excelHtml5",
					text:"Excel",
					title: "Cobro_Transacciones",
					exportOptions: {
			            columns: ':visible'
			        },
					className: 'excelbtn'
					}],
					"orderFixed" : [ [ 35, 'asc' ], [ 1, 'asc' ], [ 36, 'asc' ], [ 33, 'asc' ], [ 34, 'asc' ], [ 3, 'desc' ], [ 4, 'desc' ], [ 6, 'desc' ], [ 7, 'desc' ], [ 17, 'desc' ], [ 18, 'asc' ] ],
					"columnDefs": [ {
				      "targets": 'nosort',
				      "orderable": false
				    } ],
//				se deshabilita el orden de las columnas
				"aoColumns" : [
				    { "mData" : "apocopeGrupo" },
				    { "mData" : "numeroTransaccionFicticioFormateado" },//0
				 	{ "mData" : "estadoTransaccion" }, //1
				 	{ "mData" : "sistemaDoc" },//2
				 	{ "mData" : "tipoDocumento" }, //3
				 	{ "mData": "subtipoDocumento", //4
				 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
				 	        nTd.title = oData.subtipoDocumentoDesc != null?oData.subtipoDocumentoDesc:"";
				 	   }
				 	},
				 	{ "mData" : "nroDoc" }, //5
				 	{ "mData" : "numeroReferencia" },//6
				 	{ "mData" : "fechaVenc" },//7
				 	{ "mData" : "moneda" },//8
				 	{ "mData" : "fechaCobro" },//9
				 	{ "mData" : "importe",
				 		"mRender": function (data, type, row) {
			 			if (row.monedaMedioPago !== $('#monedaOperacion').val()) {
			 				return data;
			 			}
			 			return '-';
			 		}	
				 	},	//10
//				 	deberia venir con 7 decimales con coma
				 	{ "mData" : "tipoDeCambioFechaCobro" ,//11
				 		"mRender": function (data, type, row) {
				 			//cuando colorLetraRegistro es 0, no es diferencia de cambio
				 			if (row.moneda !== $('#monedaOperacion').val() && row.colorLetraRegistro === '0') {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
//				 	deberia venir con 7 decimales con coma
				 	{ "mData" : "tipoDeCambioFechaEmision" ,//12
				 		"mRender": function (data, type, row) {
				 			//cuando colorLetraRegistro es 1, es diferencia de cambio
				 			if (row.moneda !== $('#monedaOperacion').val() && row.colorLetraRegistro === '1') {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigen" ,//13
				 		"mRender": function (data, type, row) {
				 			if (row.moneda !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "sistemaMedioPago" },//14
				 	{ "mData" : "tipoMedioPago" },//15
				 	{ "mData" : "subtipoMedioPago",
				 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
				 	        nTd.title = oData.subtipoMedioPagoDesc != null?oData.subtipoMedioPagoDesc:"";
				 	   }
				 	},//16
				 	{ "mData" : "referenciaMedioPago" },//17
				 	{ "mData" : "fechaMedioPago" },//18
				 	{ "mData" : "monedaMedioPago" },//19
				 	{ "mData" : "importeMedioPago" ,//20
				 		"mRender": function (data, type, row) {
				 			return data;
				 		}	
				 	},
				 	{ "mData" : "tipoDeCambioFechaCobroMedioPago" ,//21
				 		"mRender": function (data, type, row) {
				 			if (row.monedaMedioPago !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigenMedioPago",//22
				 		"mRender": function (data, type, row) {
				 			if (row.monedaMedioPago !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	//{ "mData" : "intereses" },//23
				 	{
						"mData" : "intereses",// 23
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
					},
				 	{ "mData" : "trasladarInteresesFormateado",
				 		"mRender": function (data, type, row) {
				 			return data;
				 		}
				 	
				 	},//24
				 	{ "mData" : "porcABonificar" ,//25
				 		"mRender": function (data, type, row) {
				 			var resultado = validarHabilitacionCampo(row);

				 			if (resultado == TRASLADA_ASTERISCOS) {
				 				if (!row.trasladarIntereses) {
				 					return '100 %';	
				 				}
				 				return ""; 
				 			} else {
				 				//le concateno el porcentaje si no es un guion 
					 			if (data != '-') {
					 				return data + '%';
					 			}
				 			}
				 			return data;
				 		}
				 	},
					{ "mData" : "importeABonificar",//26
				 		"mRender": function (data, type, row, full) {
							//le concateno la moneda si no es un guion	 
				 			if(data != '-'){
//				 				return datosTablas.transacciones[full.row]['moneda'] + data;
				 				return data;
				 			}
				 			return data;
				 		}
					},
				 	{ "mData" : "acuerdoDestinoCargos"},//27
				 	{ "mData" : "estadoAcuerdoDestinoCargos"},//28
				 	{ "mData" : "mensajeTransaccion"},//29
				 	{ "mData" : "mensajeDebito" },//30
				 	{ "mData" : "mensajeCredito" },//31
				 	//si esta columna viene con 1 es diferencia de cambio
				    { "mData" : "colorLetraRegistro" , "visible": false },//32
				 	// Esta columna se usa para ordenar los registros de diferencia de cambio
				    { "mData" : "numeroTransaccionOriginal" , "visible": false },//33
				    { "mData" : "numeroGrupo", "visible": false },
				    { "mData" : "numeroTransaccionFormateado", "visible": false }
				],
				"fnDrawCallback": function(oSettings) {
//					quita el estilo css por defecto para el cambio de color de fondo de los registros de la grilla de transacciones
//					si se cambia de lugar probablemente no funcione
					$("tr", tablas.transaccionesPrev).removeClass("even odd");
					
				},
				 "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
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
//						if (/.*Autom.*/.test(aData.apocopeGrupo)) {
//							rowColor = rowColorDef[aData.apocopeGrupo.split('-')[1]];
//						} else {
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

				 }
		}; 

    tablas.transaccionesPrev = $("#prevTransacciones").dataTable(transaccionesPrevSettings);
    if ($('#selectEnvio').val() == "Reintegro") {
    	$('.bloqueSistemaDestino').hide();
    	$('.bloqueReintegro').show();
    }
    if ($('#selectEnvio').val() == "Reintegro a próxima factura") {
    	$('.bloqueSistemaDestino').show();
    	$('.bloqueReintegro').hide();
    }
    	/*Devuelve el length de una tabla sin un datosTablas*/
    if ($('table#prevOtrosDebitos  tr:last').index() + 1 !== 0 || $('#selectEnvio').val() == "Aplicación Manual") {
    	$('#bloqueReferencia').hide();
    	
    	 tablas.prevComprobanteApliManual = $("#prevComprobanteApliManual").dataTable({
    			dom : 'rt',
    			"sScrollX" : true,
    			"scrollY" : SCROLL_Y,
    			"bScrollCollapse" : true,
    			// "iDisplayLength" : DISPLAY_LENGTH,
    			"iDisplayStart" : 0,
    			"bPaginate" : false,
    			"aoColumns" : [
    			        
    					{
    						"mData" : "idComprobante",
    						"visible" : false,
    						"searchable" : false
    					},
    					{
    						"mData" : "nombreArchivo"
    					},
    					{
    						"mData" : "descripcionArchivo"
    					},
    					{}
    					]
    		});
    	 
    	 var codigoOperacionesExternas = 'codigoOperacionesExternas';
    	 inicializarTabla(codigoOperacionesExternas);
    	    
    } else {
    	$('#bloqueReferencia').hide();
    	$('#bloqueAplicacionManual').hide();

    }
	
    if ($('#detalleDoAprobacionA').val() == "D") {
    	$('#titleAprobacion').hide();
    	$('#titleDetalle').show();
    	$('#btnAceptar').hide();
    	$('#btnRechazar').hide();
    	$('#prevObservText2').attr('disabled', true);
    	
    	if($('#tramReintInput').val()==""){
    		$('#tramReintInput').hide();
    		$('#labeltramReintInput').hide();
    	}
    	if($('#fechaAltaTramReint').val()==""){
    		$('#fechaAltaTramReint').hide();
    		$('#labelfechaAltaTramReint').hide();
    	}
    	if($('#sistDestino').val()==""){
    		$('#sistDestino').hide();
    		$('#labelSistDestino').hide();
    	}
    	if($('#lineaInput').val()==""){
    		$('#lineaInput').hide();
    		$('#labelLinea').hide();
    	}
    	if($('#acuerdoFactInput').val()==""){
    		$('#acuerdoFactInput').hide();
    		$('#labelAcuerdo').hide();
    	}
	} else if($('#detalleDoAprobacionA').val() == "A"){
		$('#titleAprobacion').show();
		$('#titleDetalle').hide();
    }
    
    $('#btnCarta').click(function() {
    	$('#btnCarta').attr('disabled', true);
		$('#bloqueEspera').trigger('click');
		$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/generarPdfCartaCompensacion";
		$('#formCobro')[0].submit();
		setTimeout(function() {
			$('#btnCarta').attr('disabled', false);
			ocultarBloqueEspera();
		},5000);
	});
    $('#btnResumen').click(function() {
    	$('#btnResumen').attr('disabled', true);
		$('#bloqueEspera').trigger('click');
		$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/generarPdfResumenCompensacion";
		$('#formCobro')[0].submit();
		setTimeout(function() {
			$('#btnResumen').attr('disabled', false);
			ocultarBloqueEspera();
		},5000);
	});
 
});


//Guido.Settecerze
function descargarComprobante(idComprobante) {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/descargarComprobante?id=" + idComprobante;
	$('#formCobro')[0].submit();
};
//Mar
function descargarAdjunto(idAdjunto) {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/descargarAdjunto?id=" + idAdjunto;
	$('#formCobro')[0].submit();
};
//Guido.Settecerze
function exportarDetalle() {
    $('#btnExportar').attr('disabled', true);
    $('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/exportacionDetalleCobro";
    $('#formCobro')[0].submit();
    setTimeout(function(){$('#btnExportar').attr('disabled', false);},5000);
};

function verOperacionMasiva() {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/operacion-masiva-detalle-aprobacion";
	$('#formCobro')[0].submit();
};

function historialCobro() {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-historial";
	$('#formCobro')[0].submit();
};


//Guido.Settecerze
function volverBusqueda() {
	
	//logica Brian
	$('#goBack').val("true");
	$('#formCobro')[0].action=urlAsynchronousHTTP+ $('#idVolver').val();
	$('#formCobro')[0].submit();
		
	//logica Guido	
};

//Guido.Settecerze
function autorizarAprobarCobro() {
    $('#bloqueEspera').trigger('click');
    $('#formCobro')[0].action=urlAsynchronousHTTP+"/autorizacion-aprobacion-cobro";
    $('#formCobro')[0].submit();
};

//Guido.Settecerze
function rechazarAprobacionCobro() {
    if ($.isEmpty($('#prevObservText2').val())) {
    	$('#errorPrevObservText2').text("Este campo es requerido.");
    	$('#errorPrevObservText2').show();
    }else{
    	$('#errorPrevObservText2').hide();
    	$('#bloqueEspera').trigger('click');
    	$('#formCobro')[0].action=urlAsynchronousHTTP+"/rechazar-aprobacion-cobro";
    	$('#formCobro')[0].submit();
    }
};


var buscarTransacciones = function (id,sistema,sociedad){
	
	var cobro = {
			idCobro : id,
			sistema : sistema,
			sociedad :sociedad,
			monedaOperacion : $("#monedaOperacion").val()
		};
	
	$.ajax({
		"type" : "POST",
		"url": "detalle-cobro/buscarTransacciones",
		"dataType": "json", 
		"data": JSON.stringify(eval(cobro)), 
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {

			if(result.aaData != null && !$.isEmpty(result.aaData)){
				datosTablas.transacciones = result.aaData;
				tablas.transaccionesPrev.fnClearTable();
				tablas.transaccionesPrev.fnAddData(result.aaData, true);
				
				$("#prevSumInteresesTraslados").val(result.total.totalTrasladosString);
				$("#prevSumInteresesBonificados").val(result.total.totalBonificadosString);
				$("#prevSumInteresesReintegro").val(result.total.totalReintegroString);
				
				$("#prevSumInteresesTrasladosDolares").val(result.total.totalTrasladosU$SString);
				$("#prevSumInteresesBonificadosDolares").val(result.total.totalBonificadosU$SString);
				$("#prevSumInteresesReintegroDolares").val(result.total.totalReintegroU$SString);
			}
			
		    if (result.observacionesDocCap !== null || !$.isEmpty(result.observacionesDocCap)){
		    	$("#prevObservCapText2").val(result.observacionesDocCap);
		    }
			
		    ocultarBloqueEspera();
		}
	});
	
};

//u573005, sprint 5
//dentro de esta validacion contemplo si la grilla se tiene que ver en solo
//lectura o no
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
		}
	}
	return salida;
};
