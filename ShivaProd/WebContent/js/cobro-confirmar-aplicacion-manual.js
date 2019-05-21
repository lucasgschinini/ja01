
$('#main :input').attr('disabled', true);
$('#main :button').attr('disabled', true);
$('#main :select').attr('disabled', true);
$('#codigoOperacion').attr('disabled', false);

var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100; 

var CSS_SELECTED_ROW = 'LightGray';


var tablas = {
	    documentosRelacionados: null,
	    transaccionesPrev: null,
	    comprobantes: null,
	    codigoOperacionesExternas: null,
	    codigoPorTransaccion: null,
	    prevAdjunto: null
    };

var datosTablas = {
    documentosRelacionados : [],
	transacciones : [],
	comprobantes: [],
	codigoOperacionesExternas: [],
	codigoPorTransaccion : [],
	prevAdjunto: []
};

var nroTransaccionAnterior = null;
var idCodigoPantalla = 0;

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
var TRASLADA_INTERESES_LECTURA = 4;
var TRASLADA_INTERESES_NO = 0;
var TRASLADA_INTERESES_DIFERENCIA = 3;
var TRASLADA_INTERESES = 2;
var TRASLADA_ASTERISCOS = 5;
//Guido.Settecerze
$(document).ready(function() {
	
	
    $.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaAltaTramReint").datepicker({firstDay : 1});
    
	var prevClientes = 'prevClientes';
	inicializarTablaConExportacionAXlsConNombreOrdenada(prevClientes, 'CobroClientesSeleccionados', [[ 1, "asc"]]);
	    var prevDebitos = 'prevDebitos';
    inicializarTablaConExportacionAXlsConNombre(prevDebitos, 'CobroDebitosSeleccionados');
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
				"orderFixed": [[ 35, 'asc' ], [ 1, 'asc' ], [ 36, 'asc' ], [ 33, 'asc' ], [ 34, 'asc' ], [ 3, 'desc' ], [ 4, 'desc' ], [ 6, 'desc' ], [ 7, 'desc' ], [ 17, 'desc' ], [ 18, 'asc' ] ],
				"columnDefs": [ {
				      "targets": 'nosort',
				      "orderable": false
				    } ],
//				se deshabilita el orden de las columnas
				"aoColumns" : [
				    { "mData" : "apocopeGrupo" },//0
				    { "mData" : "numeroTransaccionFicticioFormateado" },//1
				 	{ "mData" : "estadoTransaccion" }, //2
				 	{ "mData" : "sistemaDoc" },//3
				 	{ "mData" : "tipoDocumento" }, //4
				 	{ "mData": "subtipoDocumento", //5
				 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
				 	        nTd.title = oData.subtipoDocumentoDesc != null?oData.subtipoDocumentoDesc:"";
				 	   }
				 	},
				 	{ "mData" : "nroDoc" }, //6
				 	{ "mData" : "numeroReferencia" },//7
				 	{ "mData" : "fechaVenc" },//8
				 	{ "mData" : "moneda" },//9
				 	{ "mData" : "fechaCobro" },//10
				 	{ "mData" : "importe" 
//				 		"mRender": function (data, type, row) {
//				 				return truncoADosDecimalesImporte(data, $('#monedaOperacion').val());
//				 		}	
				 	},	//11
//				 	deberia venir con 7 decimales con coma
				 	{ "mData" : "tipoDeCambioFechaCobro" ,//12
				 		"mRender": function (data, type, row) {
				 			//cuando colorLetraRegistro es 0, no es diferencia de cambio
				 			if (row.moneda !== $('#monedaOperacion').val() && row.colorLetraRegistro === '0') {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
//				 	deberia venir con 7 decimales con coma
				 	{ "mData" : "tipoDeCambioFechaEmision" ,//13
				 		"mRender": function (data, type, row) {
				 			//cuando colorLetraRegistro es 1, es diferencia de cambio
				 			if (row.moneda !== $('#monedaOperacion').val() && row.colorLetraRegistro === '1') {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigen" ,//14
				 		"mRender": function (data, type, row) {
				 			if (row.moneda !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "sistemaMedioPago" },//15
				 	{ "mData" : "tipoMedioPago" },//16
				 	{ "mData" : "subtipoMedioPago",
				 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
				 	        nTd.title = oData.subtipoMedioPagoDesc != null?oData.subtipoMedioPagoDesc:"";
				 	   }
				 	},//17
				 	{ "mData" : "referenciaMedioPago" },//18
				 	{ "mData" : "fechaMedioPago" },//19
				 	{ "mData" : "monedaMedioPago" },//20
				 	{ "mData" : "importeMedioPago" ,//21
				 		"mRender": function (data, type, row) {
				 			return data;
				 		}	
				 	},
				 	{ "mData" : "tipoDeCambioFechaCobroMedioPago" ,//22
				 		"mRender": function (data, type, row) {
				 			if (row.monedaMedioPago !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigenMedioPago",//23
				 		"mRender": function (data, type, row) {
				 			if (row.monedaMedioPago !== $('#monedaOperacion').val()) {
				 				return data;
				 			}
				 			return '-';
				 		}
				 	},
				 	{
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
					},
				 	
				 	{ "mData" : "trasladarInteresesFormateado"},//25
				 	{ "mData" : "porcABonificar" ,//26
				 		"mRender": function (data, type, row) {
				 			var resultado = validarHabilitacionCampo(row);

				 			if (resultado == TRASLADA_ASTERISCOS) {
				 				if (!row.trasladarIntereses) {
				 					return '100 %';
				 				} else {
				 					return '';
				 				}
				 				
				 			} else {
				 				//le concateno el porcentaje si no es un guion 
					 			if (data != '-') {
					 				return data + '%';
					 			}
				 			}
				 			return data;
				 		}
				 	},
					{ "mData" : "importeABonificar",//27
				 		"mRender": function (data, type, row, full) {
							//le concateno la moneda si no es un guion	 
				 			if(data != '-'){
//				 				return datosTablas.transacciones[full.row]['moneda'] + data;
				 				return data;
				 			}
				 			return data;
				 		}
					},
				 	{ "mData" : "acuerdoDestinoCargos"},//28
				 	{ "mData" : "estadoAcuerdoDestinoCargos"},//29
				 	{ "mData" : "mensajeTransaccion"},//30
				 	{ "mData" : "mensajeDebito" },//31
				 	{ "mData" : "mensajeCredito" },//32
				 	//si esta columna viene con 1 es diferencia de cambio
				    { "mData" : "colorLetraRegistro" , "visible": false },//33
				 	// Esta columna se usa para ordenar los registros de diferencia de cambio
				    { "mData" : "numeroTransaccionOriginal" , "visible": false },//34
				    { "mData" : "numeroGrupo" , "visible": false },//35
				    { "mData" : "numeroTransaccionFormateado" , "visible": false }//36
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
//					 var registroAnterior = "1";
//					var nroTransaccion = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull]).numeroTransaccion;
//					var nroTransaccionAnterior = nroTransaccion;
//					if (iDisplayIndexFull > 0) {
//						registroAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).colorFondoRegistro;
//						nroTransaccionAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull - 1]).numeroTransaccion;
//					}
//					$('td', nRow).css('border-top', 'none');
//					$('td', nRow).css('border-bottom', 'none');
//					
//					// Al no venir ordenadas las transacciones por numero de transaccion, se cambia la logica para el cambio de color del fondo de los registros de la grilla.
//					if (nroTransaccion !=nroTransaccionAnterior && aData.colorFondoRegistro === "1" && registroAnterior == "1") {
//						$('td', nRow).css('border-top', '1px solid #E1E1E1');
//						$('td', nRow).css('background-color', '#F2F2F7');
//						aData.colorFondoRegistro = "0";
//					} else if (nroTransaccion === nroTransaccionAnterior && registroAnterior === "0") {
//						$('td', nRow).css('background-color', '#F2F2F7');
//						aData.colorFondoRegistro = "0";
//					} else if (nroTransaccion != nroTransaccionAnterior && aData.colorFondoRegistro === "0" && registroAnterior === "0") {
//						$('td', nRow).css('border-top', '1px solid #E1E1E1');
//						$('td', nRow).css('background-color', '');
//						aData.colorFondoRegistro = "1";
//					} else if( nroTransaccion === nroTransaccionAnterior && registroAnterior === "1") {
//						$('td', nRow).css('background-color', '');
//						aData.colorFondoRegistro = "1";
//					} else {
//						if (aData.colorFondoRegistro === "0") {
//							if (registroAnterior === "1") {
//								$('td', nRow).css('border-top', '1px solid #E1E1E1');
//						}
//						$('td', nRow).css('background-color', '#F2F2F7');
//						} else {
//							if (registroAnterior === "0") {
//								$('td', nRow).css('border-top', '1px solid #E1E1E1');
//							}
//									$('td', nRow).css('background-color', '');
//								}
//							}
//
//					if (aData.colorLetraRegistro === "0") {
//						$('td', nRow).css('color', '');
//					} else {
//						$('td', nRow).css('color', CSS_SELECTED_ROW);
//					}
				 }
		}; 

    tablas.transaccionesPrev = $("#prevTransacciones").dataTable(transaccionesPrevSettings);
 
    if ($.isEmpty(TIPOTAREA) && $.isEmpty(TIPOTAREANAME)) {
	    var codigoOperacionesExternas = {
	    		dom : 'rt',
				"sScrollX" : true,
				"scrollY" : SCROLL_Y,
				"bScrollCollapse" : true,
				"iDisplayLength" : DISPLAY_LENGTH,
				"iDisplayStart" : 0,
	
	//			buttons : [ {
	//				extend : "excelHtml5",
	//				text : "Excel",
	//				title : "Cobro_Codigo_de_Operacion_Externa",
	//				className : 'excelbtn',
	//				"mColumns" : "visible"
	//			} ],
				"fnDrawCallback" : function(oSettings) {
					$('#btnEliminarTodosCodigosOperacion').attr('disabled',
							!(oSettings.aoData.length > 0));
				},
				"aoColumns" : [
						{
							"targets" : -2,
							"data" : null,
							"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar codigo de operacion externa"><i class="icon-minus bigger-120"></i></button></div>',
							"searchable" : false,
							"bSortable" : false
						},
						{
							"mData" : "idCobCobroCodOperExt", 
							"visible": false, 
							"searchable": false 
						},
						{
							"mData" : "codigoOperacionExterna"
						},
						{
							"mData" : "importe",
							"render" : function(data) {
								return MONEDA_OPERACION + formatear(importeToFloat(data));
							}
						},
						{
							"mData" : "referencia"
						},
						{
							"mData" : "factura"
						},
						{
							"mData" : "monedaImporte", 
							"visible": false, 
							"searchable": false,
							"bSortable" : false
						},
						{
							"mData" : "idTransaccion", 
							"visible": false, 
							"searchable": false,
							"bSortable" : false
						},
						{
							"mData" : "sistema", 
							"visible": false, 
							"searchable": false,
							"bSortable" : false
						},
						{
							"mData" : "grupo", 
							"visible": false, 
							"searchable": false,
							"bSortable" : false
						}
				]
			};
		tablas.codigoOperacionesExternas = $("#codigoOperacionesExternas").dataTable(codigoOperacionesExternas);
    
	/* Grilla Codigo de operaciones externas por Transaccion */
	var grillaCodigoPorTransaccion = {
    		dom : 'rt',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : DISPLAY_LENGTH,
			"iDisplayStart" : 0,

//			buttons : [ {
//				extend : "excelHtml5",
//				text : "Excel",
//				title : "Cobro_Codigo_de_Operacion_Externa",
//				className : 'excelbtn',
//				"mColumns" : "visible"
//			} ],
			"order" : [ [ 2, "asc" ]],
			"aoColumns" : [
					{
						"targets" : -2,
						"data" : null,
						"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button type="button" class="btn btn-xs btn-link" title="Eliminar codigo de operacion externa"><i class="icon-minus bigger-120"></i></button></div>',
						"searchable" : false,
						"bSortable" : false
					},
					{
						"mData" : "idCobCobroCodOperExt", 
						"visible": false, 
						"searchable": false,
						"bSortable" : false
					},
					{
						"mData" : "nroTransaccion",
						"bSortable" : false
					},
					{
						"mData" : "codigoOperacionExterna",
						"bSortable" : false
					},
					{
						"mData" : "importe",
						"bSortable" : false,
						"render" : function(data) {
							return MONEDA_OPERACION + formatear(importeToFloat(data));
						}
					},
					{
						"mData" : "referencia",
						"bSortable" : false
					},
					{
						"mData" : "factura",
						"bSortable" : false
					},
					{
						"mData" : "monedaImporte", 
						"visible": false, 
						"searchable": false,
						"bSortable" : false
					},
					{
						"mData" : "idTransaccion", 
						"visible": false, 
						"searchable": false,
						"bSortable" : false
					},
					{
						"mData" : "sistema", 
						"visible": false, 
						"searchable": false,
						"bSortable" : false
					},
					{
						"mData" : "grupo", 
						"visible": false, 
						"searchable": false,
						"bSortable" : false
					}
			],
			"fnDrawCallback" : function(oSettings) {
				var api = this.api();
				var rows = api.rows( {page:'current'} ).nodes();
				var ultimo = null;
				var primero = null;
				
				$('#btnEliminarTodosCodigosOperacion').attr('disabled',
						!(oSettings.aoData.length > 0));
				
				//Logica para agrupar los codigos de operaciones externas por transacciones
				api.column(2, {page:'current'} ).data().each( function ( grupo, i ) {
					if ( ultimo !== grupo ) {						
						primero = i;
						ultimo = grupo;
					} else {
						if ( ultimo == grupo ) {
							
							$( $(rows).eq(primero).children()[0] ).attr('rowspan',i - primero + 1);
							$( $(rows).eq(primero).children()[1] ).attr('rowspan',i - primero + 1);
//							$( $(rows).eq(i).children()).attr('class',$( $(rows).eq(primero).children()[2] ).attr('class'));
							
							if( $(rows).eq(i).children().size() > 4 ){
								
								$( $(rows).eq(i).children()[0] ).remove();
								$( $(rows).eq(i).children()[0] ).remove();
								
							}
						}
					}
				} );
			}
		};
	tablas.codigoPorTransaccion = $("#codigoPorTransaccion").dataTable(grillaCodigoPorTransaccion);
}
	/* Fin Grilla Codigo de operaciones externas por Transaccion */
	
	if (!$.isEmpty($('#idLegajo').val())) {
    	$('#divLegajo').show();
    }
	
	//TABLA COMPROBANTES
	tablas.comprobantes = $("#comprobantes").dataTable({
		dom : 'rt',
		"sScrollX" : true,
		"scrollY" : SCROLL_Y,
		"bScrollCollapse" : true,
		"iDisplayLength" : DISPLAY_LENGTH,
		"iDisplayStart" : 0,
//			buttons : [ {
//			extend : "excelHtml5",
//			text : "Excel",
//			title : "Cobro_Codigo_de_Operacion_Externa",
//			className : 'excelbtn',
//			"mColumns" : "visible"
//		} ],
		
		"bPaginate": false,
		"aoColumns" : [
		               { "mData" : "idComprobante", "visible": false, "searchable": false },           
		               { "mData" : "nombreArchivo" },
		               { "mData" : "descripcionArchivo" },
		               { "targets": -1, 
		            	 "data": null, 
		            	 "searchable": false,
		            	 "bSortable": false, 
//		            	 "defaultContent"
		            	 "mRender": function(data,type,row){
		            		 if(type = 'display'){
		            			 var salida = '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div>';
		            			 if(!isUndefinedNullDashEmptyOrFalse(tablas.comprobantes)){
		            				 salida += '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button action="eliminar" type="button" class="btn btn-xs btn-link" title="Eliminar comprobante"><i class="icon-trash bigger-120"></i></button></div>';
		            			 }
		            			 return salida;
		            		 }
		            	 }
		               }
		               ]
	});
	
	
	if ($.isEmpty(TIPOTAREA) && $.isEmpty(TIPOTAREANAME)) {
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
	    		    idCobro: $('#idCobro').val(),
	    		    descripcion : $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, ''),
	    		    motivoAdjunto : APLI_MANUAL_CONF
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
	    			
	    			tablas.comprobantes.fnAddData([comp], true);
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
	    	    $.ajax({
	    		"type" : "POST",
	    		"url": "configuracion-cobro/eliminarComprobante",
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

    
		$('#codigoOperacionesExternas tbody').on('click', 'button', function() {
			var node = $(this).parents('tr')[0];
			var aData = tablas.codigoOperacionesExternas.fnGetData(node);
			$('#bloqueEspera').trigger('click');
	//		$.ajax({
	//			"type" : "POST",
	//			"url": "cobros/eliminar-codigo-operacion-externa",
	//			"dataType": "json", 
	//			"data": "{ \"idCobCobroCodOperExt\": " + aData.idCobCobroCodOperExt + "}", 
	//			"contentType": "application/json",
	//			"mimeType": "application/json",
	//			"success" : function(result) {
	//				if (result.success) {
	//					tablas.codigoOperacionesExternas.fnDeleteRow(node);
	//					datosTablas.codigoOperacionesExternas = $.grep(datosTablas.codigoOperacionesExternas, function(seleccionado) {
	//						return seleccionado.idCobCobroCodOperExt != aData.idCobCobroCodOperExt;
	//					});
	//				}
	//				ocultarBloqueEspera();
	//			}
	//		});
			tablas.codigoOperacionesExternas.fnDeleteRow(node);
			datosTablas.codigoOperacionesExternas = $.grep(datosTablas.codigoOperacionesExternas, function(seleccionado) {
				return seleccionado.idCobCobroCodOperExt != aData.idCobCobroCodOperExt;
			});
			var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
			var importeTransaccion = 0;
			for(var i = 0; i < datosTablas.transacciones.length; i++) {
				if (
					datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
					(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
				) {
					monedaCobro = MONEDA_OPERACION;
					var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
					importeTransaccion = importeTransaccion + importeToFloat(importeString);
				}
			}
			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
				importeTransaccion = importeTransaccion - importeToFloat(codigo.importe);
			});
			$('#importeCodOpExtText').val(formatear(importeTransaccion));
			ocultarBloqueEspera();
		});
	
		$('#codigoPorTransaccion tbody').on('click', 'button', function() {
			var node = $(this).parents('tr')[0];
			var nodeABorrar = $($(node).children()[0]).attr('rowspan');
			var aData = tablas.codigoPorTransaccion.fnGetData(node);
			var nroTransaccionAEliminar = aData.nroTransaccion;
			$('#bloqueEspera').trigger('click');
			
			if(isUndefinedNullDashEmptyOrFalse(nodeABorrar))
			{
				tablas.codigoPorTransaccion.fnDeleteRow(node);
			} else {
				for(var i=0 ; i < nodeABorrar ; i++){
					tablas.codigoPorTransaccion.fnDeleteRow($(this).parents('tr')[0]);
				}
			}
			
			datosTablas.codigoPorTransaccion = $.grep(datosTablas.codigoPorTransaccion, function(seleccionado) {
				return seleccionado.nroTransaccion != aData.nroTransaccion;
			});
			
			if($('#nroTransaccionCodOpExt').val() == nroTransaccionAEliminar) {
				$('#bloqueCodigoOperacionExterna *').prop('disabled', false);
				
				clearInputText('bloqueCodigoOperacionExterna');
				var transaccion = transaccionDeGrillaTransacciones($('#nroTransaccionCodOpExt').val());
				var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
				var importeTransaccion = 0;
				tipoDocumento = transaccion.factura.tipoDocumento;
    			
    			for(var i = 0; i < datosTablas.transacciones.length; i++) {
					if (
						datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
						(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
					) {
						monedaCobro = MONEDA_OPERACION;
						var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
						importeTransaccion = importeTransaccion + importeToFloat(importeString);
					}
				}
    			
    			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
    				importeTransaccion = importeTransaccion - importeToFloat(codigo.importe);
    			});
    			
    			if(tipoDocumento != "Conjunto de Débitos") {
    				$('#facturaCodOpExtText').prop('disabled', true);
    				$('#referenciaCodOpExtText').prop('disabled', true);
    				$('#facturaCodOpExtText').val(transaccion.factura.nroDoc);
    				$('#referenciaCodOpExtText').val(transaccion.factura.numeroReferencia);
    				$('#importeCodOpExtText').prop('disabled', false);
    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
    			} else {
    				$('#facturaCodOpExtText').prop('disabled', false);
    				$('#referenciaCodOpExtText').prop('disabled', false);
    				$('#facturaCodOpExtText').val('');
    				$('#referenciaCodOpExtText').val('');
    				$('#importeCodOpExtText').prop('disabled', false);
    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
    			}
			}
			
			ocultarBloqueEspera();
		});
	
		$('#btnAgregarCodigo').on('click',function(){
			$('#bloqueEspera').trigger('click');
			/*Logica de agregar codigo a la tabla*/
			
			var confApl = {
					"idCobro" : 1
				};
			
			$.ajax({
				"type" : "POST",
				"url" : "isAlive",
				"dataType" : "json",
				"data" : JSON.stringify(eval(confApl)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					
				},
				"error" : function() {
					beforeUnload.off();
					// Para que false
					$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobro-confirmar-aplicacion-manual";
					$('#formCobro')[0].submit();
				}
			}); 
			
			var nroTransaccion = $('#nroTransaccionCodOpExt').val();
			var codigoOperacionExterna = $('#codigoOperacionText').val();
			var importe = $('#importeCodOpExtText').val();
			var factura = (!$.isEmpty($('#facturaCodOpExtText').val())) ? $('#facturaCodOpExtText').val() : '-';
			var referencia = (!$.isEmpty($('#referenciaCodOpExtText').val())) ? $('#referenciaCodOpExtText').val() : '-';
			var transaccion = transaccionDeGrillaTransacciones($('#nroTransaccionCodOpExt').val());
			
			var cont =0;
			for(var i = 0; i < datosTablas.transacciones.length; i++) {
				if (datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == nroTransaccion 
					&& (!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')) {
					
					cont++;
					
				}
			}
			
			if (cont>1){
				referencia = '-';
				factura = '-';
			}
			
			if (!$.isEmpty($('#codigoOperacionText').val()) && !$.isEmpty($('#importeCodOpExtText').val()) && !$.isEmpty($('#nroTransaccionCodOpExt').val()) && validarImporte('importeCodOpExtText')) {
				var codigo = {
						idCobCobroCodOperExt : idCodigoPantalla,
						nroTransaccion: nroTransaccion,
						codigoOperacionExterna : codigoOperacionExterna,
						importe : importeToFloat(importe),
						factura : factura,
						referencia : referencia,
						monedaImporte : MONEDA_OPERACION,
						idTransaccion : transaccion.factura.numeroTransaccion,
						sistema : SISTEMA,
						grupo : transaccion.factura.apocopeGrupo
						
				};
				
				datosTablas.codigoOperacionesExternas.push(codigo);
				tablas.codigoOperacionesExternas.fnAddData(codigo, true);
				clearInputText('bloqueCodigoOperacionExterna');
				$('#errorImporteCodOpExtText').text('');
				$('#errorCodigoOperacion').text('');
				$('#errorAgregar').text('');
				
				var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
				var importeTransaccion = 0;
				var importeTotal = 0;
				tipoDocumento = transaccion.factura.tipoDocumento;
    			
    			for(var i = 0; i < datosTablas.transacciones.length; i++) {
					if (
						datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
						(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
					) {
						monedaCobro = MONEDA_OPERACION;
						var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
						importeTransaccion = importeTransaccion + importeToFloat(importeString);
					}
				}
    			
    			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
    				importeTransaccion = importeTransaccion - importeToFloat(codigo.importe);
    			});
    			
    			if(tipoDocumento != "Conjunto de Débitos") {
    				$('#facturaCodOpExtText').prop('disabled', true);
    				$('#referenciaCodOpExtText').prop('disabled', true);
    				$('#facturaCodOpExtText').val(transaccion.factura.nroDoc);
    				$('#referenciaCodOpExtText').val(transaccion.factura.numeroReferencia);
    				$('#importeCodOpExtText').prop('disabled', false);
    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
    			} else {
    				$('#facturaCodOpExtText').prop('disabled', false);
    				$('#referenciaCodOpExtText').prop('disabled', false);
    				$('#facturaCodOpExtText').val('');
    				$('#referenciaCodOpExtText').val('');
    				$('#importeCodOpExtText').prop('disabled', false);
    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
    			}
				
				idCodigoPantalla++;
				
				ocultarBloqueEspera();
			} else {
				ocultarBloqueEspera();
				if ($.isEmpty($('#importeCodOpExtText').val())) {
					$('#errorImporteCodOpExtText').text('Este campo es requerido');
				}
				if ($.isEmpty($('#codigoOperacionText').val())) {
					$('#errorCodigoOperacion').text('Este campo es requerido');
				}
				if ($.isEmpty($('#nroTransaccionCodOpExt').val())) {
					$('#errorAgregar').text('Debe seleccionar una transacción');
				}
				
			}
		});
	
		$('#importeCodOpExtText').on('focusout', function(){
			if ($.isEmpty($('#importeCodOpExtText').val())) {
				ocultarBloqueEspera();
				$('#errorImporteCodOpExtText').text('Este campo es requerido');
			} else if (validarImporte('importeCodOpExtText')) {
				$('#errorImporteCodOpExtText').text('');
			}
			
		});

	
		$('#btnAgregarCombinacionPorTransaccion').on('click',function() {
			var importeTotal = 0;
			var importeTransaccion = 0;
			var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
			$('#errorAgregar').text('');
			$('#errorImporteCodOpExtText').text('');
			$('#errorCodigoOperacion').text('');
			var monedaCobro = '';
			
			var confApl = {
					"idCobro" : 1
				};
			
			$.ajax({
				"type" : "POST",
				"url" : "isAlive",
				"dataType" : "json",
				"data" : JSON.stringify(eval(confApl)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					
				},
				"error" : function() {
					beforeUnload.off();
					// Para que false
					$('#formCobro')[0].action = urlAsynchronousHTTP + "/cobro-confirmar-aplicacion-manual";
					$('#formCobro')[0].submit();
				}
			}); 
	
			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
				importeTotal = importeTotal + importeToFloat(codigo.importe);
			});
			
			for(var i = 0; i < datosTablas.transacciones.length; i++) {
				if (
					datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
					(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
				) {
					monedaCobro = MONEDA_OPERACION;
					var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
					importeTransaccion = importeTransaccion + importeToFloat(importeString);
				}
			}
			if (importeTotal !== importeTransaccion) {
				$('#errorAgregar').text('La suma de los importes de las operaciones externas (' + monedaCobro + formatear(importeTotal) + ') debe ser igual al importe de la transaccion ('+monedaCobro+ formatear(importeTransaccion)+').');
				
			} else {
				datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
					datosTablas.codigoPorTransaccion.push(codigo);
					tablas.codigoPorTransaccion.fnAddData(codigo,true);
				});
				tablas.codigoOperacionesExternas.fnClearTable();
				datosTablas.codigoOperacionesExternas = [];
				clearInputText('bloqueCodigoOperacionExterna');
				$('#bloqueCodigoOperacionExterna *').prop('disabled', true);
				
			}
			
		});
		 $('#codigoOperacionText').on('focusout', function(){
			if($.validacionCaracteresEspeciales($('#codigoOperacionText').val())){
				$('#errorCodigoOperacion').text("");
			}else{
				$('#errorCodigoOperacion').text("Este campo debe contener un formato de texto correcto.");
			}
			if ($.isEmpty($('#codigoOperacionText').val())) {
				ocultarBloqueEspera();
				$('#errorCodigoOperacion').text('Este campo es requerido');
			} else {
				$('#errorCodigoOperacion').text('');
			}
		});
		 $('#btnAceptar').on('click', function() {
		    	$('#bloqueEspera').trigger('click');
		    	var listaTransaccionesGrillaTrans = [];
		    	var listaTransaccionesGrillaCodXTrans = [];
		    	var transaccionExisteEnGrilla = -1;
		    	datosTablas.codigoPorTransaccion.forEach(function(transaccion) {
		    		listaTransaccionesGrillaCodXTrans.push(transaccion.nroTransaccion);
				});
		    	for(var i = 0; i < datosTablas.transacciones.length; i++) {
//		    		listaTransaccionesGrillaTrans.push(datosTablas.transacciones[i].numeroTransaccionFormateado);
		    		transaccionExisteEnGrilla = $.inArray(datosTablas.transacciones[i].numeroTransaccionFicticioFormateado,listaTransaccionesGrillaCodXTrans);
		    		
		    	}
		    	
		    	if(transaccionExisteEnGrilla !== -1){
		    		var cobro = {
			    			observacion : $('#prevObservText2').val(),
			    			idCobro : $('#idCobro').val(),
			    			sociedad : $('#sociedad').val(),
			    			listaCodigoOperacionesExternas : datosTablas.codigoPorTransaccion,
			    			sistemaDestinoDescripcion : $('#sistema').val()
			    		};
			    	if ($.validacionCaracteresEspeciales($('#prevObservText2').val())) {
			    		$.ajax({
			    			"type" : "POST",
			    		    "url": "confirmar-aplicacion-manual",
			    		    "dataType": "json",
			    		    "data": JSON.stringify(eval(cobro)),
			    		    "contentType": "application/json",
			    		    "mimeType": "application/json",
			    		    "success" : function(result) {
			    				if (result.success) {
			    					autorizarAprobarCobro();
			    				} else {
			    					ocultarBloqueEspera();
			    					$("#errorConfirmacionManual1").text("Se deberá cargar un código de operación externa o un adjunto obligatoriamente como respaldo de la operación.");
			    					$("#alertErrorConfirmacionManual1").show();
			    					$("#errorConfirmacionManual2").text("Se deberá cargar un código de operación externa o un adjunto obligatoriamente como respaldo de la operación.");
			    					$("#alertErrorConfirmacionManual2").show();
			    				}
			    		    }
			    		});
			    		
			    		
			    	} else {
			    		$('#errorObservacionObligatorio').text("Este campo debe contener un formato de texto correcto.");
			    		ocultarBloqueEspera();
			    	}
		    	} else {
		    		ocultarBloqueEspera();
		    		$('#errorAgregar').text("No se han agregado todas las transacciones a la grilla Código por Transacciones");
		    		$('#errorAgregar').show();
		    	}
		    });
		    
		    customCombobox("custom.combobox2");
		    
		    $('#nroTransaccionCodOpExt').combobox2({
		    	'change' : function(event) {
					var mensaje = 'La transacción ' + ' no fue agregada, con sus respectivos códigos, en la grilla de Código por Transacción. Si desea cambiar la transacción, se borrarán todos los registros de la grilla de código de operaciones externas.';
					var previousValue = nroTransaccionAnterior;
					var tipoDocumento;
					$('#errorAgregar').text('');
					$('#errorImporteCodOpExtText').text('');
					$('#errorCodigoOperacion').text('');
					var transaccion = transaccionDeGrillaTransacciones($('#nroTransaccionCodOpExt').val());
					var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
					var importeTransaccion = 0;
					tipoDocumento = transaccion.factura.tipoDocumento;
	    			
	    			for(var i = 0; i < datosTablas.transacciones.length; i++) {
						if (
							datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
							(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
						) {
							monedaCobro = MONEDA_OPERACION;
							var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
							importeTransaccion = importeTransaccion + importeToFloat(importeString);
						}
					}
	    			
	    			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
	    				if(codigo.nroTransaccion == numeroTransaccion){
	    					importeTransaccion = importeTransaccion - importeToFloat(codigo.importe);
	    				}
	    			});
	    			
					if (!existeTransaccionEnGrilla(previousValue) && !$.isEmpty(datosTablas.codigoOperacionesExternas) && $('#nroTransaccionCodOpExt').val() != previousValue) {
						bootbox.confirm(mensaje, function(result) {
							if (result) {
				    			tablas.codigoOperacionesExternas.fnClearTable();
								tablas.codigoOperacionesExternas.fnAdjustColumnSizing(false);
								datosTablas.codigoOperacionesExternas.length = 0;
								nroTransaccionAnterior = $('#nroTransaccionCodOpExt').val();
				    		} else {
				    			$('#nroTransaccionCodOpExt').combobox2('refresh', previousValue, 'nroTransaccionCodOpExt');
				    			var transaccion = transaccionDeGrillaTransacciones($('#nroTransaccionCodOpExt').val());
				    			var numeroTransaccion = $('#nroTransaccionCodOpExt').val();
								var importeTransaccion = 0;
				    			tipoDocumento = transaccion.factura.tipoDocumento;

				    			for(var i = 0; i < datosTablas.transacciones.length; i++) {
									if (
										datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion &&
										(!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')
									) {
										monedaCobro = MONEDA_OPERACION;
										var importeString = datosTablas.transacciones[i].importe.split(monedaCobro)[1];
										importeTransaccion = importeTransaccion + importeToFloat(importeString);
									}
								}
				    			
				    			datosTablas.codigoOperacionesExternas.forEach(function(codigo) {
				    				if(codigo.nroTransaccion == numeroTransaccion){
				    					importeTransaccion = importeTransaccion - importeToFloat(codigo.importe);
				    				}
				    			});
				    			
				    			if(tipoDocumento != "Conjunto de Débitos") {
				    				$('#facturaCodOpExtText').prop('disabled', true);
				    				$('#referenciaCodOpExtText').prop('disabled', true);
				    				$('#facturaCodOpExtText').val(transaccion.factura.nroDoc);
				    				$('#referenciaCodOpExtText').val(transaccion.factura.numeroReferencia);
				    				$('#importeCodOpExtText').prop('disabled', false);
				    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
				    			} else {
				    				$('#facturaCodOpExtText').prop('disabled', false);
				    				$('#referenciaCodOpExtText').prop('disabled', false);
				    				$('#facturaCodOpExtText').val('');
				    				$('#referenciaCodOpExtText').val('');
				    				$('#importeCodOpExtText').prop('disabled', false);
				    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
				    			}
				    			bootbox.hideAll();
				    			return false;
				    		}
						});
					} else {
						nroTransaccionAnterior = $('#nroTransaccionCodOpExt').val();
					}
			
					if(existeTransaccionEnGrilla(nroTransaccionAnterior)){
						$('#bloqueCodigoOperacionExterna *').prop('disabled', true);
						clearInputText('bloqueCodigoOperacionExterna');
					} else {
						$('#bloqueCodigoOperacionExterna *').prop('disabled', false);
						
						var cont = 0;
						var numDocTxtArea="";
						var referenciaTxtArea="";
						
						for(var i = 0; i < datosTablas.transacciones.length; i++) {
							if (datosTablas.transacciones[i].numeroTransaccionFicticioFormateado == numeroTransaccion 
								&& (!$.isEmpty(datosTablas.transacciones[i].sistemaDoc) && datosTablas.transacciones[i].sistemaDoc != '-')) {
								
								cont++;
								numDocTxtArea += datosTablas.transacciones[i].nroDoc + "\n";
								referenciaTxtArea += datosTablas.transacciones[i].numeroReferencia + "\n";
							}
						}
						
						if (cont>1){
							$('#referenciaCodOpExtTextArea').val(referenciaTxtArea);
							$('#referenciaCodOpExtTextArea').prop('disabled', true);
							$('#codigoOperacionTextArea').prop('disabled', true);
							$('#codigoOperacionTextArea').val(numDocTxtArea);
							$('#txtAreadiv').show();
							$('#divReferenciaYNumDoc').hide();
						} else {
							$('#txtAreadiv').hide();
							$('#divReferenciaYNumDoc').show();
						}
						
						if(tipoDocumento != "Conjunto de Débitos") {
							$('#facturaCodOpExtText').prop('disabled', true);
							$('#facturaCodOpExtText').val(transaccion.factura.nroDoc);
		    				$('#referenciaCodOpExtText').prop('disabled', true);
		    				$('#referenciaCodOpExtText').val(transaccion.factura.numeroReferencia);
		    				$('#importeCodOpExtText').prop('disabled', false);
		    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
						} else {
							$('#facturaCodOpExtText').prop('disabled', false);
		    				$('#referenciaCodOpExtText').prop('disabled', false);
		    				$('#facturaCodOpExtText').val('');
		    				$('#referenciaCodOpExtText').val('');
		    				$('#importeCodOpExtText').prop('disabled', false);
		    				$('#importeCodOpExtText').val(formatear(importeTransaccion));
						}
					}
					
					return false;
		    	}
		    });
	} else if (!$.isEmpty(TIPOTAREA) && TIPOTAREANAME == TIPOTAREA) {
		$("#comprobanteAplicacionManualDiv").hide();
		$("#operExp").hide();
		 var codigoOperacionesExternas = 'codigoPorTransaccion';
    	 inicializarTabla(codigoOperacionesExternas);
	}
	
	
    if ($('#selectEnvio').val() == "Reintegro") {
    	$('.bloqueSistemaDestino').hide();
    	$('.bloqueReintegro').show();
    }
    if ($('#selectEnvio').val() == "Reintegro a próxima factura") {
    	$('.bloqueSistemaDestino').show();
    	$('.bloqueReintegro').hide();
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
    
   
    
	$('#prevObservText2').on('focusout', function(){
		if($.validacionCaracteresEspeciales($('#prevObservText2').val())){
			$('#errorObservacionObligatorio').text("");
		}else{
			$('#errorObservacionObligatorio').text("Este campo debe contener un formato de texto correcto.");
		}
	});
    
   

	$('#btnInformar').on('click', function() {
    	$('#bloqueEspera').trigger('click');
    	if ($.validacionCaracteresEspeciales($('#prevObservText2').val())) {
    		$('#bloqueEspera').trigger('click');
    	    $('#observacion').val($('#prevObservText2').val());
    	    $('#formCobro')[0].action=urlAsynchronousHTTP+"/informa-desapropiacion-cobro-manual";
    	    $('#formCobro')[0].submit();
    	} else {
    		$('#errorObservacionObligatorio').text("Este campo debe contener un formato de texto correcto.");
    		ocultarBloqueEspera();
    	}
    });
});

function validarImporte (nombreCampoImporte) {
	
	var importeError = $("#error" + nombreCampoImporte.substr(0,1).toUpperCase() + nombreCampoImporte.substr(1));
	var importe = $("#" + nombreCampoImporte).val();
	var salida = true;
	
	if($.isEmpty(importe)){
		importeError.text("");
		
	}else if(!validarImporteValido(importe)){
		importeError.text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		salida = false;
	}else if(importeToFloat(importe) == 0) {
		importeError.text("El importe ingresado tiene que ser mayor a 0");
		salida = false;
	}else {
		importeError.text("");
	}
	
	return salida;
}

function importeCodigoExcedeImporteTransaccion(importe) {
	var importeTotalTransacciones = 0;
	var importe;
	var esImporteValido = false; 
	datosTablas.transacciones.forEach(function(transaccion){
		importeTransaccion = parseFloat(transaccion.importe);
		if (!isNaN(importeTransaccion)) {
			importeTotalTransacciones += importeTransaccion;
		}
	});
	
	

	return null;
}

function existeTransaccionEnGrilla(transaccion) {
	var existe = false;
		for (var index = 0, sizeMax = datosTablas.codigoPorTransaccion.length; index < sizeMax; index++) {
			if (datosTablas.codigoPorTransaccion[index].nroTransaccion === transaccion) {
				existe = true;
			}
		}
	return existe;
}

//function validar

function removeDuplicates(originalArray, prop) {
    var newArray = [];
    var lookupObject  = {};

    for(var i in originalArray) {
       lookupObject[originalArray[i][prop]] = originalArray[i];
    }

    for(i in lookupObject) {
        newArray.push(lookupObject[i]);
    }
     return newArray;
}

function cargarSelectTransacciones() {
	
	var options = [{text: 'Seleccione un item...', value : ''}];
	var nroTransaccionFiltro = options[0].value;
	datosTablas.transacciones.forEach(function(transaccion) {
		var option = new Option(transaccion.numeroTransaccionFicticio, transaccion.numeroTransaccionFicticioFormateado);
		options.push(option);
	});
	$("#nroTransaccionCodOpExt").replaceOptions(removeDuplicates(options,"value"));
}
//Mar
function descargarAdjunto(idAdjunto) {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/descargarAdjunto?id=" + idAdjunto;
	$('#formCobro')[0].submit();
};
//Guido.Settecerze
function descargarComprobante(idComprobante) {
	$('#formCobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/descargarComprobante?id=" + idComprobante;
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
    $('#observacion').val($('#prevObservText2').val());
    $('#formCobro')[0].action=urlAsynchronousHTTP+"/autorizacion-aprobacion-cobro-manual";
    $('#formCobro')[0].submit();
};

//Guido.Settecerze
var validarObservacionObligatoriedad = function () {
	if ($.validacionCaracteresEspeciales($('#prevObservText2').val())) {
		if ($.isEmpty($("#prevObservText2").val())) {
			$("#errorObservacionObligatorio").text("Este campo es requerido.");
			$("#errorObservacionObligatorio").css('display', 'inline-block');
			$("#errorBtnAceptar").css('display', 'none');
			ocultarBloqueEspera();
		}else{
			$("#errorObservacionObligatorio").css('display', 'none');
			$("#errorBtnAceptar").css('display', 'inline-block');
			rechazarAprobacionCobroManual();
		}
	}else{
		$('#errorObservacionObligatorio').text("Este campo debe contener un formato de texto correcto.");
		ocultarBloqueEspera();
	}
};

//Guido.Settecerze
function rechazarAprobacionCobroManual() {
	$('#bloqueEspera').trigger('click');
    if ($.isEmpty($('#prevObservText2').val())) {
    	$('#errorObservacionObligatorio').text("Este campo es requerido.");
    	$('#errorObservacionObligatorio').show();
    }else{
    	$('#errorObservacionObligatorio').hide();
    	$('#bloqueEspera').trigger('click');
    	$('#observacion').val($('#prevObservText2').val());
    	$('#formCobro')[0].action=urlAsynchronousHTTP+"/rechazar-aprobacion-cobro-manual";
    	$('#formCobro')[0].submit();
    }
    ocultarBloqueEspera();
};

var buscarTransacciones = function (id, sistema, sociedad, empresa, segmento){
	
	var cobro = {
			idCobro : id,
			sistemaDestinoDescripcion : sistema,
			sociedad : sociedad,
			empresa : empresa,
			segmento : segmento,
			monedaOperacion : $("#monedaOperacion").val()
		};
	
	$.ajax({
		"type" : "POST",
		"url": "detalle-cobro/buscarTransaccionesApliManual",
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
		    cargarSelectTransacciones();
		}
	});
	
	
	
};

function transaccionDeGrillaTransacciones(nroTransaccionFormateado) {
	var transaccion = {
			factura: null,
			medioPago : null
	};
	for(var i = 0; i < datosTablas.transacciones.length; i++) {
		if (datosTablas.transacciones[i].numeroTransaccionFormateado == nroTransaccionFormateado) {
			if (datosTablas.transacciones[i].tipoDocumento != '-') {
				transaccion.factura = datosTablas.transacciones[i];
			} else {
				transaccion.medioPago = datosTablas.transacciones[i];
			}
		}
	}
	
	return transaccion;
}
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
