var cobroRevertir = 'cobroRevertir';
inicializarTabla(cobroRevertir);
var codigoOperacionesExternas = 'codigoOperacionesExternas';
inicializarTabla(codigoOperacionesExternas);
//var prevTransacciones = 'prevTransacciones';
//inicializarTabla(prevTransacciones);
//var operacionesCobroRelacionadas = 'operacionesCobroRelacionadas';
//inicializarTabla(operacionesCobroRelacionadas);




var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100; 
var CANTIDAD_POR_PAGINA_DEB = 80;
var CANTIDAD_POR_PAGINA_CRED = 100;

var DEB_COL_POS_IMPORTE = 17;
var DEB_COL_POS_MENSAJE = 32;
var CRED_COL_POS_IMPORTE = 15;
var CRED_COL_POS_MENSAJE = 34;

var DOC_RELAC_COL_POS_DIVISION_1 = 3;
var DOC_RELAC_COL_POS_DIVISION_2 = 9;

var TR_HEIGHT = 'TR_HEIGHT';
var TR_HEIGHT_ERROR = 'TR_HEIGHT_ERROR';
var TR_HEIGHT_VALOR = 37;
var TR_HEIGHT_ERROR_VALOR = 46;

var validacionOkFecha = true;
var verHistorial = false;
var simulacionOK = false;
var transaccionesOK = false;
var marcaSim = null;

var TRANS_COL_POS_PORCENTAJE = 25;
var TRANS_COL_POS_IMPORTE = 26;
var TRANS_COL_POS_ESTADO = 29;
var TRANS_COL_POS_MENSAJE = 34;

var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var CSS_SELECTED_ROW = 'LightGray';
var BTN_NO_CLASS = 'btn-no-class'; // No es una clase CSS se utiliza como selector
var GESTIONABILIDAD_NO_CLASS = 'gestion-semaforo-no-class-';

var varEdicionSegunEstadoMarca = '';
var KEY_BS_RETROCESO = '8';
var btnSimularClicked = false;
var anularGuardar = false;
var diferencia = '0,00';

var sistemaTransaccion = [];
var sistemaTransaccionText = '';
var simulado = false;
var errorCamposObligatorios = false;
var errorCampoParaRevertir = false;
var errorEnCampoOtroNumerico = false;
var errorEnCampoOtro = false;
var transaccionAcuerdoVacio = false;
var flagExportarExcel = false;
var idTransaccionSistAc = [];
var idTransaccionAc = [];
var cantTransacciones = 0;
var counter = 0;
var estadosErrores=['Error en apropiación de medio de pago', 'Error en apropiación de deuda', 'Error en apropiación de medio de pago y deuda', 'Error en apropiación del tratamiento de diferencia', 'Desapropiada', 'Error. Aplicación Manual Rechazada', 'Sin procesar por error en grupo'];

var tablas = {
	// Tab Clientes
	clientes : null,
	clientesSeleccionados : null,
	// Tab Debitos 
	clientesDebitos : null,
	debitos : null,
	debitosSeleccionados: null,
	// Tab Creditos 
	clientesCreditos : null,
	creditos : null,
	// Tab Medios de Pago
	clientesMedios: null,
	mediosPagos: null,
	comprobantes: null,
	// Tab Previsualizacion
	clientesPrev: null,
	debitosPrev : null,
	creditosPrev : null,
	mediosPagosPrev: null,
	transaccionesPrev: null,
	comprobantesPrev: null,
	cobrosRelacionadosPrev: null,
	documentosRelacionados: null
};

var datosTablas = {
	clientesSeleccionados : [],
	debitosSeleccionados : [],
	creditosSeleccionados : [],
	mediosPagos : [],
	transacciones : [],
	comprobantes : [],
	cobrosRelacionados : [],
	documentosRelacionados : [],
	tratamientoDiferencia : [],
	codigosOperacionExterna : []
};



$(document).ready(function() {
    $('#prevObservacionesAnterior').hide();
	$('#btnRevertir').prop('disabled', true);
	$('#idReversionPadreOtro').attr('disabled', false);
	
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
				title: "Reversion_Cobro_Transacciones",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
		                body: function ( data, column, row ) {
		                	if(!flagExportarExcel){
		                		counter = counter - cantTransacciones;
		                		flagExportarExcel = true;
		                	}
		                	if (column === 31) {
								if (!$.isEmpty(data) && data != '-') {
									
									data = $('#'+idTransaccionSistAc[counter]).val();
									if (data == "CALIPSO") {
										data = "Calipso";
									} else if (data == "MIC") {
										data = "Mic";
									} else {
										data = '-';
									}
								}
							}
							// Columna "Acuerdo Contracargo"
							if (column === 32) {
								data = $('#'+idTransaccionAc[counter]).val();
								counter = counter + 1;
							}
							
		                    return data;
		                }
		            }
		        }
				}]
			,
			"orderFixed": [[ 41, 'desc' ],[ 2, 'desc' ],[ 42, 'desc' ],[ 39, 'asc' ],[ 40, 'asc' ],[ 4, 'desc' ],[ 5, 'desc' ],[ 7, 'desc' ],[ 8, 'desc' ],[ 18, 'desc' ],[ 19, 'asc' ]],
			"columnDefs": [{
			      "targets": 'nosort',
			      "orderable": false
			    }],
//			se deshabilita el orden de las columnas
			"aoColumns" : [
			    { "mData" : null,
            	  "render" : function(data, type, row) {
//            		  if (type === 'display') {
//            			  
//            			  return (showCheck) ? '<input type="checkbox" id="checkRevertir" name="checkRevertir" disabled="disabled" ' + ((data) ? 'checked="checked"' : '') + '>' : '-';
//            			  
//            			  return (showCheck) ? '<input type="checkbox" id="cobrarAl2doVenc" name="cobrarAl2doVenc" ' + ((data) ? 'checked="checked"' : '') + ' disabled="disabled"' + '>' : '-';
//      				  }
//            		  return data;
            		  if (type === 'display') {
            			  var showCheck = (estadosErrores.indexOf(row.estadoTransaccion) < 0);
            			  return  (showCheck) ? '<input type="checkbox" ' + ((data) ? 'checked' : '') + ' disabled>' : '-';
            		  }
            		  return data;
            		  
            	  },
            	  "className" : "dt-body-center",
            	  "searchable" : false,
            	  "bSortable" : false
               	},
               	{ "mData" : "apocopeGrupo" },
			    { "mData" : "numeroTransaccionFicticioFormateado" },//0
			 	{ "mData" : "estadoTransaccion" }, //1
			 	{ "mData" : "sistemaDoc" },//2
			 	{ "mData" : "tipoDocumento" }, //3
			 	{ "mData" : "subtipoDocumento",//4
			 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
			 	        nTd.title = oData.subtipoDocumentoDesc != null?oData.subtipoDocumentoDesc:"";
			 	   }	
			 	},
			 	{ "mData" : "nroDoc" }, //5
			 	{ "mData" : "numeroReferenciaMic" },//6
			 	{ "mData" : "fechaVenc" },//7
			 	{ "mData" : "moneda" },//8
			 	{ "mData" : "fechaCobro" },//9
			 	{ "mData" : "importe",
			 		"mRender": function (data, type, row) {
			 			if(data !== '-'){
			 				return truncoADosDecimalesImporte(data, $('#monedaOperacion').val());
			 			}
			 			return "-";
			 		}
			 	},	//10
//			 	deberia venir con 7 decimales con coma
			 	{ "mData" : "tipoDeCambioFechaCobro",
			 		"mRender": function (data, type, row) {
			 			if (row.moneda !== returnSignoMoneda($('#monedaOperacion').val()) && row.colorLetraRegistro === '0') {
			 				return data;
			 			}
			 			return '-';
			 		}	
			 	},//11
//			 	deberia venir con 7 decimales con coma
			 	{ "mData" : "tipoDeCambioFechaEmision" ,
			 		"mRender": function (data, type, row) {
			 			if (row.moneda !== returnSignoMoneda($('#monedaOperacion').val()) && row.colorLetraRegistro === '1') {
			 				return data;
			 			}
			 			return '-';
			 		}	
			 	},//12
			 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigen",
			 		"mRender": function (data, type, row) {
			 			if (row.moneda !== returnSignoMoneda($('#monedaOperacion').val())) {
			 				return truncoADosDecimalesImporte(data, row.moneda);
			 			}
			 			return '-';
			 		}	
			 	},//13
			 	{ "mData" : "sistemaMedioPago" },//14
			 	{ "mData" : "tipoMedioPago" },//15
			 	{ "mData" : "subtipoMedioPago",//16
			 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
			 	        nTd.title = oData.subtipoMedioPagoDesc != null?oData.subtipoMedioPagoDesc:"";
			 	   }
			 	},
			 	{ "mData" : "referenciaMedioPago" },//17
			 	{ "mData" : "fechaMedioPago" },//18
			 	{ "mData" : "monedaMedioPago" },//19
			 	{ "mData" : "importeMedioPago",
			 		"mRender": function (data, type, row) {
			 			if(data !== '-'){
			 				return truncoADosDecimalesImporte(data, $('#monedaOperacion').val());
			 			}
			 			return "-";
			 		}
			 	},//20	
			 	{ "mData" : "tipoDeCambioFechaCobroMedioPago" ,//21
			 		"mRender": function (data, type, row) {
			 			if (row.monedaMedioPago !== returnSignoMoneda($('#monedaOperacion').val())) {
			 				return data;
			 			}
			 			return '-';
			 		}
			 	},
			 	{ "mData" : "importeAplicadoFechaEmisionMonedaOrigenMedioPago",//22
			 		"mRender": function (data, type, row) {
			 			if (row.monedaMedioPago !== returnSignoMoneda($('#monedaOperacion').val())) {
			 				return truncoADosDecimalesImporte(data, row.monedaMedioPago);
			 			}
			 			return '-';
			 		}
			 	},
			 	{ "mData" : "intereses",
			 		"mRender": function (data, type, row) {
			 			if(row.moneda !== '-'){
							return truncoADosDecimalesImporte(data, row.moneda);
						}else{
							return truncoADosDecimalesImporte(data, row.monedaMedioPago);
						}
			 		}
			 	},//23
			 	{ "mData" : "trasladarInteresesFormateado"},//24
			 	{ "mData" : "porcABonificar",
			 		"mRender": function (data, type, row) {
			 			if(data !== '-'){
			 				return data+'%';
			 			}
			 			return "-";
			 		}
			 	},//25
				{ "mData" : "importeABonificar",
			 		"mRender": function (data, type, row) {
			 			if(row.moneda !== '-'){
							return truncoADosDecimalesImporte(data, row.moneda);
						}else{
							return truncoADosDecimalesImporte(data, row.monedaMedioPago);
						}
			 		}
				},//26
				{ "mData" : "sistemaAcuerdo"},//27
			 	{ "mData" : "acuerdoDestinoCargos"},//28
			 	{ "mData" : "estadoAcuerdoDestinoCargos"},//29
			 	{ "mData" : "estadoCargoProximaFactura"},//30
			 	{ "mData": "sistemaAcuerdoFactDestinoContracargos",//31
		              "render": function (data, type, row, full) {
		            	  if (type === 'display') {
		            		  if(flagExportarExcel){
		            			  counter = 0;
		            			  flagExportarExcel = false;
		            		  }
		            		  //contador para hacer funcionar la exportacion a excel (campos editable). Brian
		            		  cantTransacciones = full.settings.aoData.length;
		            		  idTransaccionSistAc [counter]  = "rowSistAcSelected_"+full.row;
		            		  
//TODO Se comenta hasta que funcionen los contracargos cruzados  
//		            		  sistemaTransaccionText = '<select id="'+idTransaccionSistAc[counter]+'" style="margin: 0 auto;width: 80px; align: center; " onfocusout="updateValue(datosTablas.transacciones, $(\'#'+idTransaccionSistAc[counter]+'\').val(),'+full.row+', \'sistemaAcuerdoFactDestinoContracargos\')" ' + ((validaEdicionSistemaAcuerdo(row) && simulado && row.acuerdoDestinoCargos != '-') ? ' ' :  'class="deshabilitado"'  ) + '>';  
		            		  sistemaTransaccionText = '<select id="'+idTransaccionSistAc[counter]+'" style="margin: 0 auto;width: 80px; align: center; " onfocusout="updateValue(datosTablas.transacciones, $(\'#'+idTransaccionSistAc[counter]+'\').val(),'+full.row+', \'sistemaAcuerdoFactDestinoContracargos\')" ' + ((validaEdicionSistemaAcuerdo(row) && simulado && row.acuerdoDestinoCargos != '-') ? 'class="deshabilitado"' :  'class="deshabilitado"'  ) + '>';  
		            		  
		            		  if(data == "-"){
		            			  sistemaTransaccionText = sistemaTransaccionText +('<option value=" "> </option>');
		            		  }
		            		  $.each(sistemaTransaccion, function(index, elemento) {
		            			  sistemaTransaccionText = sistemaTransaccionText +('<option value="'+(elemento.value)+'"'+((data === elemento.value) ? 'selected' :'')+ '>'+(elemento.text)+'</option>');
		      				  });
		            		  sistemaTransaccionText = sistemaTransaccionText +'</select>';
		            		  return sistemaTransaccionText;
		            	  }
		            	  return data;
		              }
					},
			  
			 	{ "mData" : "acuerdoFactDestinoContracargos",//32
						"render": function (data, type, row, full) {
			            	  if (type === 'display') {
			            		  
			            		  idTransaccionAc [counter] = "rowAcSelected_"+full.row;
			            		  counter = counter +1;
//TODO Se comenta hasta que funcionen los contracargos cruzados	 
//			            		  return '<input type="text" id="'+idTransaccionAc[counter - 1]+'" value="'+(data)+'" style="margin: 0 auto;width: 110px; text-align: right;  font: normal 11px Verdana;" ' + ((validaEdicionAcuerdo(row) && simulado && row.acuerdoDestinoCargos != '-') ? ' onfocusout="validarAcuerdo(datosTablas.transacciones,\''+idTransaccionAc[counter - 1]+'\','+full.row+', \'acuerdoFactDestinoContracargos\' )" ' :  'readonly '  ) + '><div></div>';
			            		  return '<input type="text" id="'+idTransaccionAc[counter - 1]+'" value="'+(data)+'" style="margin: 0 auto;width: 110px; text-align: right;  font: normal 11px Verdana;" ' + ((validaEdicionAcuerdo(row) && simulado && row.acuerdoDestinoCargos != '-') ? ' onfocusout="validarAcuerdo(datosTablas.transacciones,\''+idTransaccionAc[counter - 1]+'\','+full.row+', \'acuerdoFactDestinoContracargos\' )" readonly ' :  'readonly '  ) + '><div></div>';
			            	  }
			            	  return data;
			              }
						},
			 	{ "mData" : "estadoAcuerdoDestinoContracargos"},//33
			 	{ "mData" : "mensajeTransaccion"},//34
			 	{ "mData" : "mensajeDebito" },//35
			 	{ "mData" : "mensajeCredito" },//36
			 	//si esta columna viene con 1 es diferencia de cambio
				{ "mData" : "colorLetraRegistro" , "visible": false },//37
				// Esta columna se usa para ordenar los registros de diferencia de cambio
			    { "mData" : "numeroTransaccionOriginal" , "visible": false },//38
				{ "mData" : "numeroGrupo", "visible" : false},//39
			    { "mData" : "numeroTransaccionFormateado" , "visible": false }
			],
			"fnDrawCallback": function(oSettings) {
//				quita el estilo css por defecto para el cambio de color de fondo de los registros de la grilla de transacciones
//				si se cambia de lugar probablemente no funcione
				$("tr", tablas.transaccionesPrev).removeClass("even odd");
				
			},
			 "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				 var registroAnterior = "1";
				 if(iDisplayIndexFull > 0){
                	 registroAnterior = tablas.transaccionesPrev.fnGetData(tablas.transaccionesPrev.fnGetNodes()[iDisplayIndexFull-1]).colorFondoRegistro;
                 }
				 $('td', nRow).css('border-top', 'none');
				 $('td', nRow).css('border-bottom', 'none');
				 
                 if ( aData.colorFondoRegistro === "0" ){
                	 if(registroAnterior === "1"){
                		 $('td', nRow).css('border-top', '1px solid #E1E1E1');
                	 }
                	 $('td', nRow).css('background-color', '#F2F2F7');
                 } else {
                	 if(registroAnterior === "0"){
                		 $('td', nRow).css('border-top', '1px solid #E1E1E1');
                	 }
                	 $('td', nRow).css('background-color', '');
                 }                                 

                 if ( aData.colorLetraRegistro === "0" ){
                	 $('td', nRow).css('color', '');                	 
                 } else {
                	 $('td', nRow).css('color', CSS_SELECTED_ROW);
                 }       
			 }
		};
	
	tablas.transaccionesPrev = $("#prevTransacciones").dataTable(transaccionesPrevSettings);

    $('#comboIdReversionPadre').hide();
    $('#idReversionPadreOtroCombo').hide();
    
    (function () {
	var empresaPrev;
	var segPrev;
	$("#selectEmpresa").focus(function () {
	    empresaPrev = this.value;
	}).change(function() {
	    if ($.isEmpty(this.value)) {
		var options = [{text: 'Seleccione un item...', value : ''}];
		$("#selectSegmento").replaceOptions(options);
		$("#selectSegmento").val(options[0].value);
		$("#selectCopropietario").replaceOptions(options);
		$("#selectCopropietario").val(options[0].value);
	    } else if (this.value != empresaPrev) {
		$('#bloqueEspera').trigger('click');
		empresaPrev = this.value; 
		$.ajax({
		    "dataType" : 'json',
		    "type" : "GET",
		    "url" : 'configuracion-cobro/buscarSegmentos',
		    "data" : { idEmpresa : this.value },
		    "success" : function(result) {
			if(result!=null){
			    if (result.length > 0) {
				if (result.length == 1) {
				    $("#selectSegmento").replaceOptions(result);
				    $("#selectSegmento").val(result[0].value).trigger('change');
				} else {
				    var options = [{text: 'Seleccione un item...', value : ''}];
				    $("#selectCopropietario").replaceOptions(options);
				    $("#selectSegmento").val(options[0].value);
				    $.each(result, function(index, option) {
					options.push(option);
				    });
				    $("#selectSegmento").replaceOptions(options);
				    $("#selectSegmento").val(options[0].value);
				}
			    }
			    ocultarBloqueEspera();
			}
		    }
		});
	    };
	});

	$("#selectSegmento").focus(function () {segPrev = this.value;}).change(function() {
	    if ($.isEmpty(this.value)) {
		var options = [{text: 'Seleccione un item...', value : ''}];
		$("#selectCopropietario").replaceOptions(options);
		$("#selectCopropietario").val(options[0].value);
	    } else if (this.value != segPrev) {
		$('#bloqueEspera').trigger('click');
		segPrev = this.value; 
		$.ajax({
		    "dataType" : 'json',
		    "type" : "GET",
		    "url" : 'configuracion-cobro/buscarCopropietarios',
		    "data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : this.value },
		    "success" : function(result) {
			var options = [{text: 'Seleccione un item...', value : ''}];
			$.each(result, function(index, option) {
			    options.push(option);
			});
			$("#selectCopropietario").replaceOptions(options);
			$("#selectCopropietario").val(options[0].value);
			ocultarBloqueEspera();
		    }
		});
	    };
	    ocultarBloqueEspera();
	});
    })();

    
    
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------
//  TABLA OPERACIONES RELACIONADAS
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------
    
    var cobrosRelacionadosPrevSettings = {
    		dom : '<"botonExcelCobrosRelacionadosSettings">Bfrtip',
			"sScrollX": true,
			"scrollY": SCROLL_Y,
			"bScrollCollapse": true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"order": [[ 19, 'desc' ]],
			buttons: [{	
				extend: "excelHtml5",
				text:"Excel",
				title: "Operaciones_Cobros_Relacionadas",
				"mColumns": "visible",
				className: 'excelbtn'
			}]				
			,
//			se deshabilita el orden de las columnas
			"aoColumns" : [
							{ "targets" : 0,
								   "searchable" : false,
								   "bSortable" : false,
								   "data" : null,
								   "render" : function(data, type, row) {
									   if (type === 'display') {
										   if(row.sistema == 'Shiva'){
											   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+null+','+row.idOperacionRelacionada+')"><i class="icon-eye-open bigger-120"></i></button></div>';
										   }
									   }
									   return null;
								   }
							},
			               { "mData" : "sistema" },
			               { "mData" : "idOperacionRelacionada" },
			               { "mData" : "idOperacionCobroPadre" },
			               { "mData" : "idTransaccionCobroPadre" },
			               { "mData" : "tipoDocumentoRelacionado" },
			               { "mData" : "nroDocumentoRelacionado" },
			               { "mData" : "motivoCobro" },
			               { "mData" : "idCliente" },
			               { "mData" : "estadoCobro" },
			               { "mData" : "subEstadoCobro" },
			               { "mData" : "fechaUltimoEstado" },
			               { "mData" : "analista" },
			               { "mData" : "copropietario" },
			               { "mData" : "analistaTeamComercial" },
			               { "mData" : "importeFormateado" },
			               { "mData" : "fechaAltaOp" },
			               { "mData" : "fechaDerivacion" },
			               { "mData" : "fechaAutorizacionReferente" },
			               { "mData" : "fechaImputacion" },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   if(row.sistema == 'Shiva'){
										   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+null+','+row.idOperacionRelacionada+')"><i class="icon-eye-open bigger-120"></i></button></div>';
									   }
			            		   }
			            		   return null;
			            	   }
			               },
			]
		};
	tablas.cobrosRelacionadosPrev = $("#operacionesCobroRelacionadas").dataTable(cobrosRelacionadosPrevSettings);
	
	
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------
//  TABLA DOCUMENTOS RELACIONADOS
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------
    var documentosRelacionadosSettings = {
    		dom : '<"botonExcelDocumentosRelacionadosSettings">Bfrtip',
			"sScrollX": true,
			"scrollY": SCROLL_Y,
			"bScrollCollapse": true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			 buttons: [{	
					extend: "excelHtml5",
					text:"Excel",
					title: "Documentos_Relacionados",
					customize: function ( xlsx ){
						var sheet = xlsx.xl.worksheets['sheet1.xml'];
						
						
						//Se cambia el formato de las celdas para que aparezcan como texto
						$('row c[r^="A"]', sheet).add($('row c[r^="B"]', sheet)).each(function(){
							var val = $(this).text();
							$(this).attr('t', 'inlineStr').html('<is><t>'+ val +'</t></is>');
						});
						
						//Se agrega una fila mas para poder desplazar el sheet
						addRow(sheet);
						
						//Se desplaza el sheet entero una fila mas hacia abajo
						moveDown(sheet);
						
						//Se agrega estilo negrita al titulo
						$('row c[r*="2"]', sheet).each(function(){ $(this).attr('s','2');});
						$('row c[r^="J"]', sheet).add($('row c[r^="D"]', sheet)).each(function(){ $('is t', this).text("");$(this).attr('s','5');});
						
//						Se combinan las celdas del primer titulo y se agrega el tipo de documento
						$('row[r="1"]', sheet).attr('spans','1:3 5:9 7:11');
						$('worksheet', sheet).append('<mergeCells count="1">'
								+'<mergeCell ref="A1:C1"/>'
								+'<mergeCell ref="E1:I1"/>'
								+'<mergeCell ref="K1:O1"/>'
								+'</mergeCells>');
						$('row c[r="E1"] t', sheet).text('Documento Original');
						$('row c[r="K1"] t', sheet).text('Documento Cancelatorio');
						columnWidth(sheet);
					},
					exportOptions: {
						columns: ':visible'
					},
					className: 'excelbtn'
				}],
//			se deshabilita el orden de las columnas
			"aoColumns" : [
			               { "mData" : "nroTransaccionFicticioFormateado" },
			               { "mData" : "idCobranzaGenerada" },
			               { "mData" : "fechaImputacion" },
			               { "targets" : 0,
							   "searchable" : false,
							   "bSortable" : false,
							   "data" : null,
							   "render" : function(data, type, row) {
								   return "";
							   }
			               },
			               { "mData" : "sistemaOrigen" },
			               { "mData" : "tipoComprobanteOriginal" },
			               { "mData" : "origenDocumentoOriginal",
			            	   'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
						 	        nTd.title = oData.origenDocumentoOriginalDesc != null?oData.origenDocumentoOriginalDesc:"";
						 	   }
			               },
			               { "mData" : "nroDocumentoOriginalFormateado" },
			               { "mData" : "importeAplicadoOriginalFormateado" },
			               { "targets" : 0,
							   "searchable" : false,
							   "bSortable" : false,
							   "data" : null,
							   "render" : function(data, type, row) {
								   return "";
							   }
			               },
			               { "mData" : "sistemaOrigen" },
			               { "mData" : "tipoComprobanteGenerado" },
			               { "mData" : "origenDocumentoGenerado",
			            	   'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
						 	        nTd.title = oData.origenDocumentoGeneradoDesc != null?oData.origenDocumentoGeneradoDesc:"";
						 	   }   
			               },
			               { "mData" : "nroDocumentoGeneradoFormateado" },
			               { "mData" : "importeAplicadoGeneradoFormateado" }
			],
			 "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					 $('td:eq('+DOC_RELAC_COL_POS_DIVISION_1+')', nRow).css('background-color', '#dcdbdb');
					 $('td:eq('+DOC_RELAC_COL_POS_DIVISION_2+')', nRow).css('background-color', '#dcdbdb');

			 }
		};
	tablas.documentosRelacionados = $("#documentosRelacionados").dataTable(documentosRelacionadosSettings);
	
	 
//  ---------------------------------------------------------------------------------------documentosRelacionadosSettings
//  ---------------------------------------------------------------------------------------
//  TAB COMPROBANTES
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------

    tablas.comprobantes = $("#comprobantes").dataTable({
	"sDom" : 'rt',
	"sScrollX": true,
	"scrollY": SCROLL_Y,
	"bScrollCollapse": true,
//	"iDisplayLength" : DISPLAY_LENGTH,
	"iDisplayStart" : 0,
	"bPaginate": false,
	"aoColumns" : [
	               { "mData" : "idComprobante", "visible": false, "searchable": false },           
	               { "mData" : "nombreArchivo" },
	               { "mData" : "descripcionArchivo" },
	               { "targets": -1, "data": null, "defaultContent": '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button action="eliminar" type="button" class="btn btn-xs btn-link" title="Eliminar comprobante"><i class="icon-trash bigger-120"></i></button></div>', "searchable": false, "bSortable": false }
	               ]
    });

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
		    idDescobro: $('#idDescobro').val(),
		    descripcion : $('#descripcionComprobante').val().replace(/^\s+|\s+$/g, ''),
		    motivoAdjunto : COMPROBANTE_DESCOBRO
		},
		success:function(data) {
		    $('.fileupload').fileupload('clear');
		    if (data.success) {
			var comp = {
				idComprobante : data.idComprobante,
				nombreArchivo : data.fileName,
				descripcionArchivo : data.descripcion
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
		"url": "configuracion-descobro/eliminarComprobante",
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
	    iframe.src = "configuracion-descobro/descargarComprobante?id=" + aData.idComprobante;
	    iframe.style.display = "none";
	    document.body.appendChild(iframe);
	}
    });

//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------
//  FIN TAB COMPROBANTES
//  ---------------------------------------------------------------------------------------
//  ---------------------------------------------------------------------------------------

    //U572497 Guido.Settecerze
    (function () {
	var selectSegmento;
	$("#selectSegmento").focus(function () {
	    selectSegmento = this.value;
	}).change(function() {
	    if(selectSegmento !=null && selectSegmento == ""){
		$('#errorSegmento').hide();
	    }
	});
    })(); 
    
    //U572497 Guido.Settecerze
    (function () {
	var motivoSelect;
	$("#selectMotivo").focus(function () {
	    motivoSelect = this.value;
	}).change(function() {
	    $('#idReversionPadreOtroCombo').hide();
	    $('#idReversionPadreOtro').val("");
	    $('#selectIdReversionPadre').val("");
	    $('#idErrorOtros').hide();
	    $('#idErrorReversion').hide();
	    if(motivoSelect !=null && motivoSelect == ""){
		$('#errorMotivo').hide();
	    }
	    if (this.value == '5') {
		motivoSelect = this.value;
		$('#comboIdReversionPadre').show();
	    }else{
		$('#comboIdReversionPadre').hide();
	    };
	});
    })();    
    
    //U572497 Guido.Settecerze
    (function () {
	var idReversionPadreOtro;
	$("#idReversionPadreOtro").focus(function () {
	    idReversionPadreOtro = this.value;
	}).change(function() {
	    if (this.value != "" && this.value != null) {
		$("#idErrorOtros").hide();
		idReversionPadreOtro = this.value;
		if (!$.validacionIsNumeric(idReversionPadreOtro)) {
		    $("#idErrorOtros").text("Este campo debe contener valores numéricos.");
		    $("#idErrorOtros").css('display', 'inline-block');
		    errorEnCampoOtroNumerico = true;
		    errorEnCampoOtro = true;
		    $('#btnRevertir').prop('disabled', true);
		    $('#btnGuardar').prop('disabled', true);
		    $('#btnGuardar2').prop('disabled', true);
		}else{
		    $("#idErrorOtros").css('display', 'none');
		    $('#btnRevertir').prop('disabled', false);
		    $('#btnGuardar').prop('disabled', false);
		    $('#btnGuardar2').prop('disabled', false);
		    errorEnCampoOtroNumerico = false;
		    errorEnCampoOtro = false;
		    validarEstadoYExistenciaDelIdReversionOtro(idReversionPadreOtro);
		}
	    }else{
		$("#idErrorOtros").css('display', 'none');
		$('#btnRevertir').prop('disabled', false);
		$('#btnGuardar').prop('disabled', false);
		$('#btnGuardar2').prop('disabled', false);
		errorEnCampoOtroNumerico = false;
		errorEnCampoOtro = false;
	    };
	});
    })();
    
    //U572497 Guido.Settecerze
    (function () {
	$("#selectIdReversionPadre").focus(function () {
	}).change(function() {
	    $('#idReversionPadreOtro').val("");
	    $('#idErrorOtros').hide();
	    if (!$.isEmpty($('#selectIdReversionPadre').val()) && $("#selectMotivo").val() == '5') {
		$("#idErrorReversion").hide();
	    }
	    if (this.value == 'Otro') {
		$('#idReversionPadreOtroCombo').show();
		$('#idReversionPadreOtro').show();
		$('#controlsOtro').show();
		$('#idReversionPadreOtro').show();
		$('.otrosBloque').show();
		$('#idReversionPadreOtro').attr('disabled', false);
	    }else{
		$('#idReversionPadreOtroCombo').hide();
		$('#idReversionPadreOtro').hide();
		$('#controlsOtro').hide();
		$('#idReversionPadreOtro').hide();
		$('.otrosBloque').hide();
	    };
	});
    })();    
    
    $('#btnExportar').click(function() {
	validarDatosDescobro();
	if(!errorCamposObligatorios){
	    exportarDetalleAExcel();
	}
    });
    $('#btnExportar2').click(function() {
	validarDatosDescobro();
	if(!errorCamposObligatorios){
	    exportarDetalleAExcel();
	}
    });

    $('#btnSimular').click(function() {
	validarDatosDescobro();
	if(!errorCamposObligatorios){
	    simularDescobro();
	}
    });
    
    $('#btnRevertir').click(function() {
	$('#btnRevertir').prop('disabled', true);
	validarDatosDescobro();
	validarAntesDeRevertir();
	if(!errorCampoParaRevertir && !errorCamposObligatorios){
	    imputarDescobro();
	}else{
	    $('#btnRevertir').prop('disabled', false);
	}
    });
    
    if (!$.isEmpty($('#idLegajo').val())) {
    	$('#divLegajo').show();
//    	$('#selectMotivo').val('1');
//    	$('#selectMotivo').attr('disabled', true);
    } 
//    else {
//    	$("#selectMotivo option[value='1']").remove();
//    }
    
});

//U572497 Guido.Settecerze
var validarEstadoYExistenciaDelIdReversionOtro = function (idReversion) {
	$('#bloqueEspera').trigger('click');
	var descobro = {
		idDescobro : idReversion
	};
     
	$.ajax({
	    "type" : "POST",
	    "url": "reversion-descobro/validarEstadoYExistenciaDelIdReversionOtro",
	    "dataType": "json",
	    "data": JSON.stringify(eval(descobro)),
	    "contentType": "application/json",
	    "mimeType": "application/json",
	    "success" : function(result) {
		if (result.success) {
		    $('#btnRevertir').prop('disabled', false);
		    errorEnCampoOtro = false;
		}else{
		    $('#btnRevertir').prop('disabled', true);
		    $("#idErrorOtros").text(result.descripcionError);
		    $("#idErrorOtros").css('display', 'inline-block');

		    errorEnCampoOtro = true;
		}
		ocultarBloqueEspera();
	    }

	});
};

//U572497 Guido.Settecerze
var validarEstadoYExistenciaDelIdReversionOtroEnReady = function () {
	if ($("#idReversionPadreOtro").val()!="" && $("#idReversionPadreOtro").val()!=null && !$.validacionIsNumeric($("#idReversionPadreOtro").val())) {
	    $("#idErrorOtros").text("Este campo debe respetar el formato 9999999999.");
	    $("#idErrorOtros").css('display', 'inline-block');
	    errorEnCampoOtroNumerico = true;
	    errorEnCampoOtro = true;
	    $('#btnRevertir').prop('disabled', true);
	    $('#btnGuardar').prop('disabled', true);
	    $('#btnGuardar2').prop('disabled', true);
	}else{
	    $("#idErrorOtros").css('display', 'none');
	    $('#btnRevertir').prop('disabled', false);
	    $('#btnGuardar').prop('disabled', false);
	    $('#btnGuardar2').prop('disabled', false);
	    errorEnCampoOtroNumerico = false;
	    errorEnCampoOtro = false;
	    if ($("#idReversionPadreOtro").val()!="" && $("#idReversionPadreOtro").val()!=null){
		validarEstadoYExistenciaDelIdReversionOtro($("#idReversionPadreOtro").val());
	    }
	}
};


//U572497 Guido.Settecerze
var validarBtnHabilitarSimular = function () {
    $('#bloqueEspera').trigger('click');
    var descobro = {
	    idDescobro : $('#idReversion').val(),
	    idOperacionDescobro : $('#idOperacionDescobro').val(),
	    idCobro : $('#idCobro').val(),
	    transacciones: datosTablas.transacciones
    };
    
    $.ajax({
	"type" : "POST",
	"url": "reversion-descobro/validarBtnHabilitarSimular",
	"dataType": "json",
	"data": JSON.stringify(eval(descobro)),
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
	    if (result.success) {
		$('#btnSimular').attr('disabled', false); 		
	    }else{
		$('#btnSimular').attr('disabled', true);
	    }
	    ocultarBloqueEspera();
	}   
    
    });
};

function updateValue(dataTable, value, rowPos, attr) {
	dataTable[rowPos][attr] = value;
}

function setMensajeError(input, mensaje, tipoRow, nombreColImp, idButton) {
	if (mensaje == '') {
		$(input).next('div').html('');
	} else {
		$(input).next('div').html('<span class="errorGrillaEditable">' + mensaje + '</span>');
		contadorMensajesError.sumarErrorByDescripcion(mensaje);
	}
}

var validaEdicionSistemaAcuerdo = function (transaccion){
	
	var result;
	
	if('Mic' == transaccion.sistemaAcuerdo){
		if('Activo' == transaccion.estadoAcuerdoDestinoContracargos || 'Incomunicado' == transaccion.estadoAcuerdoDestinoContracargos){
			if(transaccion.acuerdoDestinoCargos != transaccion.acuerdoFactDestinoContracargos){
				if('Facturado' == transaccion.estadoCargoProximaFactura){
					result = true;
				}else{
					result = false;
				}
			}else{
				result = false;
			}
		}else{
			if('Facturado' == transaccion.estadoCargoProximaFactura){
				result = true;
			}else{
				result = false;
			}
		}
	}else{
		if('Facturado' == transaccion.estadoCargoProximaFactura){
			result = true;
		}else{
			result = false;
		}
	}
	
	return result;
	
};

var validaEdicionAcuerdo = function (transaccion){

	var result;
	
	if('Mic' == transaccion.sistemaAcuerdo){
		if('Activo' == transaccion.estadoAcuerdoDestinoContracargos || 'Incomunicado' == transaccion.estadoAcuerdoDestinoContracargos){
			if(transaccion.acuerdoDestinoCargos != transaccion.acuerdoFactDestinoContracargos){
				result = true;
			}else{
				result = false;
			}
		}else{
			result = true;
		}
	}else{
		result = true;
	}
	return result;
};


//u573005, fabio.giaquinta.ruiz, sprint 7
var exportarDetalleAExcel = function () {
	
	$('#bloqueEspera').trigger('click');
    $('#btnExportar').attr('disabled', true);
    $('#btnExportar2').attr('disabled', true);
    beforeUnload.off();
    	
	//Cargo los datos para guardar el descobro
	var descobro = {
	    idDescobro : $('#idReversion').val(),
	    monedaOperacion : returnSignoMoneda($('#monedaOperacion').val()),
	    idDescobroPadreFormateado :  $('#selectIdReversionPadre').val(),
	    idDescobroPadreFormateadoOtro : $('#idReversionPadreOtro').val(),
	    idOperacionDescobro : $('#idOperacionDescobroOculto').val(),
	    idEmpresa : $('#selectEmpresa').val(),
	    idSegmento : $('#selectSegmento').val(),
	    idAnalista : $('#idAnalista').val(),
	    nombreAnalista : $('#analista').val(),
	    idMotivoReversion : $('#selectMotivo').val(),
	    idCobro : $('#idCobro').val(),
	    idCopropietario : $('#selectCopropietario').val(),
	    observacion : $('#prevObservText').val(),
	    simulado : true,
	    transacciones: datosTablas.transacciones
    };

    $.ajax({
		"type" : "POST",
		"url": "reversion-descobro/prepararDescobro",
		"dataType": "json",
		"data": JSON.stringify(eval(descobro)),
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {
			
			if (result.success) {
			    	
			    $('#formDescobro')[0].action = urlAsynchronousHTTP + "/descobro-detalle-aprobacion/exportacionDetalleDescobro";
			    $('#formDescobro')[0].submit();
			    
			    if(result.descobro.idDescobro != "" && result.descobro.idDescobro != null){
				$('#idReversion').val(result.descobro.idDescobro);
			    }	
			    
			    if($('#idReversion').val() != "" && $('#idReversion').val() != null){
				    buscarDescobro($('#idReversion').val());
			    }		
			    setTimeout(function() {
					$('#btnExportar').attr('disabled', false);
					$('#btnExportar2').attr('disabled', false);
					beforeUnload.on();
					ocultarBloqueEspera();
			    }, 3000);
				
			}
				
		}
    
    });
    
};


//U572497 Guido.Settecerze
var guardarReversion = function () {
    validarDatosDescobro();
    if(!errorCamposObligatorios && !transaccionAcuerdoVacio){
    	$("#alertErrorGuardado").hide();
		guardarDescobro();
    }else{
    	$("#errorGuardado").text("Error al guardar. Revise los errores.");
    	$("#alertErrorGuardado").show();
    }

};

//U572497 Guido.Settecerze
var guardarDescobro = function () {
	
    $('#bloqueEspera').trigger('click');
    $('#btnGuardar').attr('disabled', true);
    $('#btnGuardar2').attr('disabled', true);
    $('#btnSimular').attr('disabled', true);

	
	var descobro = {
	    idDescobro : $('#idReversion').val(),
	    idLegajo : $('#idLegajo').val(),
	    idDescobroPadreFormateado :  $('#selectIdReversionPadre').val(),
	    idDescobroPadreFormateadoOtro : $('#idReversionPadreOtro').val(),
	    idOperacionDescobro : $('#idOperacionDescobroOculto').val(),
	    monedaOperacion : returnSignoMoneda($('#monedaOperacion').val()),
	    idEmpresa : $('#selectEmpresa').val(),
	    idSegmento : $('#selectSegmento').val(),
	    idAnalista : $('#idAnalista').val(),
	    nombreAnalista : $('#analista').val(),
	    idMotivoReversion : $('#selectMotivo').val(),
	    idCobro : $('#idCobro').val(),
	    idCopropietario : $('#selectCopropietario').val(),
	    observacion : $('#prevObservText').val(),
	    simulado : simulado,
	    transacciones: datosTablas.transacciones
    };

    $.ajax({
	"type" : "POST",
	"url": "reversion-descobro/guardarRevDescobro",
	"dataType": "json",
	"data": JSON.stringify(eval(descobro)),
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
	    if (result.success) {
	    	actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
	    	$('#idOperacionDescobro').val(result.idOperacion);
	    	$('#idOperacionDescobroOculto').val(result.idOperacion);
	    	$('#idReversion').val(result.idReversion);
	    	$('#idDescobro').val(result.idReversion);
	    	$('#idCobro').val(result.idCobro);
	    	$('#booleanIdPadreDescobroOtro').val(result.booleanIdPadreDescobroOtro);

	    	if(result.primerDescobro){
	    		//Activo el boton exportar a excel luego del primer guardado
	    		$('#btnExportar').attr('disabled', false);
	    		$('#btnExportar2').attr('disabled', false);
	    		$('#btnHistorial').attr('disabled', false);
	    		$('#btnHistorial2').attr('disabled', false);
	    	}
//	    	if (ESTADO_VEC_OBSERVACION.indexOf(result.estadoDescripcion) > -1) {
//	    	if (!$.isEmpty(result.observacionAnterior)) {
//	    	$('#prevObservTextAnterior').val(result.observacionAnterior);
//	    	$('#prevObservacionesAnterior').show();
//	    	} else {
//	    	$('#prevObservacionesAnterior').hide();
//	    	}
//	    	$('#prevObservText').val(result.observacion);
//	    	}
	    	if($('#idReversion').val() !="" && $('#idReversion').val()!=null){
	    		buscarDescobro($('#idReversion').val());
	    	}
	    	cambiarModoEdicion(result.edicionSegunEstadoMarca, result.esPerfilSupervisorCobranza);
	    }
	    setTimeout(function() {
	    	$('#btnGuardar').attr('disabled', false);
	    	$('#btnGuardar2').attr('disabled', false);
	    	ocultarBloqueEspera();
	    }, 3000);
	}   
    
    });
};


//Brian
function volverBusqueda() {
	beforeUnload.off();
	$('#goBack').val("true");
	$('#formDescobro')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formDescobro')[0].submit();
};


//U572497 Guido.Settecerze
function irDetalle(idCobro, idOperacion, idDescobro) {
	$('#bloqueEspera').trigger('click');
	if(idOperacion != null){
		$('#idOperacionRelacionada').val(idOperacion);
		$('#idDescobro').val(idDescobro);
	}else if(idCobro != null){
		$('#idCobro').val(idCobro);
		$('#idDescobro').val(idDescobro);
	}
	
	$('#formDescobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion";
	$('#formDescobro')[0].submit();
};

function irHistorial() {
	$('#bloqueEspera').trigger('click');
//	$('#idCobro').val(idCobro);
	$('#opcion').val("D");
	$('#formDescobro')[0].action=urlAsynchronousHTTP+"/descobro-historial";
	$('#formDescobro')[0].submit();
};

//U572497 Guido.Settecerze
var detalleCobro = function (idCobro, idOperacion) {
	var descobro = {
	    idDescobro : $('#idReversion').val(),
	    monedaOperacion : returnSignoMoneda($('#monedaOperacion').val()),
	    idCobro : idCobro,
	    idOperacionDescobro : $('#idOperacionDescobro').val(),
	    idEmpresa : $('#selectEmpresa').val(),
	    idSegmento : $('#selectSegmento').val(),
	    idAnalista : $('#idAnalista').val(),
	    nombreAnalista : $('#analista').val(),
	    idMotivoReversion : $('#selectMotivo').val(),
	    idCobro : $('#idCobro').val(),
	    idCopropietario : $('#selectCopropietario').val(),
	    observacion : $('#prevObservText').val(),
	    marca : marcaSim,
	    listaComprobantes: datosTablas.comprobantes,
	    transacciones: datosTablas.transacciones,
	    documentosRelacionados : datosTablas.documentosRelacionados,
	    operacionesRelacionadas: datosTablas.cobrosRelacionados
    };

    $.ajax({
	"type" : "POST",
	"url": "reversion-descobro/guardoDescobroEnSesion",
	"dataType": "json",
	"data": JSON.stringify(eval(descobro)),
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
	    if (result.success) {
	    	irDetalle(idCobro, idOperacion, $('#idReversion').val());
	    }
	}   
    
    });
};

// u579607
var historialCobro = function () {
	
    $('#bloqueEspera').trigger('click');
    
	var descobro = {
	    idDescobro : $('#idReversion').val(),
	    idOperacionDescobro : $('#idOperacionDescobro').val(),
	    monedaOperacion : returnSignoMoneda($('#monedaOperacion').val()),
	    idEmpresa : $('#selectEmpresa').val(),
	    idSegmento : $('#selectSegmento').val(),
	    idAnalista : $('#idAnalista').val(),
	    nombreAnalista : $('#analista').val(),
	    idMotivoReversion : $('#selectMotivo').val(),
	    idCobro : $('#idCobro').val(),
	    idCopropietario : $('#selectCopropietario').val(),
	    observacion : $('#prevObservText').val(),
	    marca : marcaSim,
	    listaComprobantes: datosTablas.comprobantes,
	    transacciones: datosTablas.transacciones,
	    documentosRelacionados : datosTablas.documentosRelacionados,
	    operacionesRelacionadas: datosTablas.cobrosRelacionados
    };

    $.ajax({
	"type" : "POST",
	"url": "reversion-descobro/guardoDescobroEnSesion",
	"dataType": "json",
	"data": JSON.stringify(eval(descobro)),
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
	    if (result.success) {
	    	irHistorial();
	    }
	    
	    setTimeout(function() {
			ocultarBloqueEspera();
	    }, 3000);
	}   
    
    });
};

// u579607 Brian Keller
function validarAcuerdo(dataTable, idAcuerdo, idSistemaAcuerdo,attr) {
	
	$('#bloqueEspera').trigger('click');

	var transaccion = {
		idCobro : $('#idCobro').val(),
		sistemaAcuerdoFactDestinoContracargos : $("#rowSistAcSelected_"+idSistemaAcuerdo).val(),
		acuerdoFactDestinoContracargos : $("#"+idAcuerdo).val()
	};
	
	if(transaccion.acuerdoFactDestinoContracargos == "" || transaccion.acuerdoFactDestinoContracargos == null){
	    $('#btnSimular').attr('disabled', true);
	}else{
	    $('#btnSimular').attr('disabled', false);
	}
	
	$.ajax({
		"type" : "POST",
		"url": "configuracion-descobro/validarAcuerdoContracargo",
		"dataType": "json", 
		"data": JSON.stringify(eval(transaccion)), 
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {
				
				var rowSelected = tablas.transaccionesPrev.fnGetData(idSistemaAcuerdo);
				rowSelected.sistemaAcuerdoFactDestinoContracargos = transaccion.sistemaAcuerdoFactDestinoContracargos;
				rowSelected.acuerdoFactDestinoContracargos = transaccion.acuerdoFactDestinoContracargos;
				rowSelected.clienteAcuerdoDestinoContracargos = result.clienteAcuerdo;
				rowSelected.estadoAcuerdoDestinoContracargos = result.estadoAcuerdo;
				rowSelected.mensajeTransaccion = result.descripcionError;
				tablas.transaccionesPrev.fnUpdate(rowSelected, idSistemaAcuerdo);
				
				if(result.success){

					updateValue(datosTablas.transacciones, TIPO_MENSAJE_OK_NAME, idSistemaAcuerdo, 'tipoMensajeTransaccion');
					
					$.each(datosTablas.transacciones, function(index, option) {
						if(option.tipoMensajeTransaccion != 'OK'){
							transaccionesOK = false;
							return false;
						}
					});
					if(transaccionesOK){
						$('#btnRevertir').prop('disabled', false);
					}
				}else{
					//valido que el acuerdo no este vacio
					if(result.errorCampoNull){
						$('#'+idAcuerdo).next('div').html('<span class="errorGrillaEditable">' + result.errorCampoNull + '</span>');
						transaccionAcuerdoVacio = true;
					}else{
						updateValue(datosTablas.transacciones, TIPO_MENSAJE_ERROR_NAME, idSistemaAcuerdo, 'tipoMensajeTransaccion');
						transaccionAcuerdoVacio = false;
					}
					$('#btnRevertir').prop('disabled', true);
				}
				ocultarBloqueEspera();
		}
	});
};

var resetearMensajesTransacciones = function (){
	
	 $.each(datosTablas.transacciones, function(index, option) {
	    	option.tipoMensajeTransaccion = "-";
	    	option.mensajeTransaccion = "-";
	    	option.tipoMensajeDebito = "-";
	    	option.mensajeDebito = "-";
	    	option.tipoMensajeCredito = "-";
	    	option.mensajeCredito = "-";
		});
}

//u573005 fabio.giaquinta.ruiz
var simularDescobro = function () {
	
	$('#bloqueEspera').trigger('click');
    $('#btnSimular').attr('disabled', true);
    $('#btnGuardar').attr('disabled', true);
    $('#btnGuardar2').attr('disabled', true);
    
    //borro los mensajes de transaccion, debito y credito
    resetearMensajesTransacciones();
	
    //Envio los datos para guardar el descobro
    var descobro = {
    	    idDescobro : $('#idReversion').val(),
    	    idLegajo : $('#idLegajo').val(),
    	    monedaOperacion :returnSignoMoneda($('#monedaOperacion').val()),
    	    idDescobroPadreFormateado :  $('#selectIdReversionPadre').val(),
    	    idDescobroPadreFormateadoOtro : $('#idReversionPadreOtro').val(),
    	    idOperacionDescobro : $('#idOperacionDescobroOculto').val(),
    	    idEmpresa : $('#selectEmpresa').val(),
    	    idSegmento : $('#selectSegmento').val(),
    	    idAnalista : $('#idAnalista').val(),
    	    nombreAnalista : $('#analista').val(),
    	    idMotivoReversion : $('#selectMotivo').val(),
    	    idCobro : $('#idCobro').val(),
    	    idCopropietario : $('#selectCopropietario').val(),
    	    observacion : $('#prevObservText').val(),
    	    simulado : simulado,
    	    transacciones: datosTablas.transacciones
        };
	
	$.ajax({
		"type" : "POST",
		"url": "configuracion-descobro/simularDescobro",
		"dataType": "json",
		"data": JSON.stringify(eval(descobro)),
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {
		    
		    setTimeout(function() {
				//oculto el relojito
		    	$('#btnGuardar').attr('disabled', false);
		        $('#btnGuardar2').attr('disabled', false);
				ocultarBloqueEspera();
	
				//muestro el mensaje de aviso al usuario 
				if (result.success) {
				    if(result.descripcionError != null){
						$("#mensajeErrorSimular").css('display', 'inline-block');
						$("#mensajeErrorSimular").text(result.descripcionError);
				    }
				    
				    $('#idReversion').val(result.descobro.idDescobro);
				    $('#idDescobro').val(result.descobro.idDescobro);
		    	    $('#idOperacionDescobroOculto').val(result.descobro.idOperacionDescobro);
		    	    $('#idOperacionDescobro').val(result.descobro.idOperacionDescobro);
		    	    $('#idCobro').val(result.descobro.idCobro);
		    	    if(result.descobro.transacciones != null){
					    datosTablas.transacciones = result.descobro.transacciones;
					    tablas.transaccionesPrev.fnClearTable();
					    tablas.transaccionesPrev.fnAddData(result.descobro.transacciones, true);
					}
		    	    $('#btnExportar').attr('disabled', false);
		    	    $('#btnExportar2').attr('disabled', false);
		    	    validarEdicionDescobro();
		    	    actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
				}		    
		    }, 3000);
		}
	    });
};


function imputarDescobro() {
	
	$('#bloqueEspera').trigger('click');
	// Cambio de vista. Desactivo el evento beforenload
	beforeUnload.off();
	
    //borro los mensajes de transaccion, debito y credito
    resetearMensajesTransacciones();
	
	var descobro = {
	    idDescobro : $('#idReversion').val(),
	    monedaOperacion : returnSignoMoneda($('#monedaOperacion').val()),
	    idDescobroPadreFormateado :  $('#selectIdReversionPadre').val(),
	    idDescobroPadreFormateadoOtro : $('#idReversionPadreOtro').val(),
	    idOperacionDescobro : $('#idOperacionDescobroOculto').val(),
	    idEmpresa : $('#selectEmpresa').val(),
	    idSegmento : $('#selectSegmento').val(),
	    idAnalista : $('#idAnalista').val(),
	    nombreAnalista : $('#analista').val(),
	    idMotivoReversion : $('#selectMotivo').val(),
	    idCobro : $('#idCobro').val(),
	    idCopropietario : $('#selectCopropietario').val(),
	    observacion : $('#prevObservText').val(),
	    simulado : true,
	    transacciones: datosTablas.transacciones
    };

    $.ajax({
		"type" : "POST",
		"url": "reversion-descobro/prepararDescobro",
		"dataType": "json",
		"data": JSON.stringify(eval(descobro)),
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {
			
			if (result.success) {
			    	
				$('#formDescobro')[0].action=urlAsynchronousHTTP+"/imputarDescobro";
				$('#formDescobro')[0].submit();
				
			}
				
		}
    
    });
	
};

//U572497 Guido.Settecerze
var buscarDescobro = function (id){
    $('#bloqueEspera').trigger('click');
	var descobro = {
		idDescobro : id,
		idCobro : $('#idCobro').val()
	};
	$.ajax({
		"type" : "POST",
		"url": "configuracion-descobro/buscarConfDescobro",
		"dataType": "json", 
		"data": JSON.stringify(eval(descobro)), 
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {

			if (result.success) {
			    if(result.habilitarBtnSimular && varEdicionSegunEstadoMarca ==''){
					$('#btnSimular').prop('disabled', false);
			    }else{
					$('#btnSimular').prop('disabled', true);
			    }
				/* Cargo la lista de copropietarios posibles y selecciono el que tiene cargado el cobro encontrado*/
				sistemaTransaccion = [];
				$.each(result.listaSistemaTransaccion, function(index, option) {
					sistemaTransaccion.push(option);
				});
				
				simulado = result.descobro.simulado;
				
				if(result.descobro.transacciones != null){
				    datosTablas.transacciones = result.descobro.transacciones;
				    tablas.transaccionesPrev.fnClearTable();
				    tablas.transaccionesPrev.fnAddData(result.descobro.transacciones, true);
				    
				}
				
				if(result.descobro.documentosRelacionados != null && !$.isEmpty(result.descobro.documentosRelacionados)){
					datosTablas.documentosRelacionados = result.descobro.documentosRelacionados;
					tablas.documentosRelacionados.fnClearTable();
					tablas.documentosRelacionados.fnAddData(result.descobro.documentosRelacionados,true);
				}
				
				if(result.descobro.operacionesRelacionadas != null && !$.isEmpty(result.descobro.operacionesRelacionadas)){
				    datosTablas.cobrosRelacionados = result.descobro.operacionesRelacionadas;
				    tablas.cobrosRelacionadosPrev.fnClearTable();
				    tablas.cobrosRelacionadosPrev.fnAddData(result.descobro.operacionesRelacionadas,true);
				}
				if (!$.isEmpty(result.descobro.listaCodigoDeOperacionesExternas)) {
					datosTablas.codigosOperacionExterna = result.descobro.listaCodigoDeOperacionesExternas;
				}
			
				actualizarEstadoCobroVista(result.estado.estadoDescripcion, result.estado.marcaDescripcion);
				
				$('#idCobro').val(result.descobro.idCobro);
				$('#idReversion').val(result.descobro.idDescobroFormateado);
				$('#idDescobro').val(result.descobro.idDescobroFormateado);
//				$('#estado').val(result.cobro.estado);
				$('#idOperacionDescobro').val(result.descobro.idOperacionDescobro);
				$('#idOperacionDescobroOculto').val(result.descobro.idOperacionDescobro);
				
				if($('#idReversion').val()!="" && $('#idReversion').val()!=null){
				   	transaccionesOK = result.transaccionesOK;
				    validarEdicionDescobro(result.descobro.marca);
				    marcaSim = result.descobro.marca;
//				    validarEdicionDescobroSegunUsuario();
				} else {
					//hasta que no se guarda el descobro no habilito los botones de exportacion e historial
					$('#btnExportar').prop('disabled', true);
					$('#btnExportar2').prop('disabled', true);
					$('#btnHistorial').prop('disabled', true);
					$('#btnHistorial2').prop('disabled', true);
					
					//se tiene que deshabilitar el boton revertir tambien?
				}
				
				if(result.estado.estadoDescripcion == 'Descobrado'){
					$('#btnRevertir').prop('disabled', true);
				}
				$('#selectIdReversionPadre').val(result.descobro.idDescobroPadreFormateado);
				if(result.descobro.idDescobroPadreFormateadoOtro!=null && result.descobro.idDescobroPadreFormateadoOtro!= ""){
				    $('#idReversionPadreOtroCombo').show();
				    $('#idReversionPadreOtro').show();
				    $('#controlsOtro').show();
				    $('#idReversionPadreOtro').show();
				    $('.otrosBloque').show();
				    $('#idReversionPadreOtro').attr('disabled', false);
				    $('#idReversionPadreOtro').val(result.descobro.idDescobroPadreFormateadoOtro);
				    validarEstadoYExistenciaDelIdReversionOtroEnReady();
				}
				$('#selectEmpresa').val(result.descobro.idEmpresa);
				$('#selectSegmento').val(result.descobro.idSegmento);
				$('#idAnalista').val(result.descobro.idAnalista);
				$('#analista').val(result.descobro.nombreAnalista);
				$('#prevAnalista').val(result.descobro.nombreAnalista);
				/* Cargo la lista de copropietarios posibles y selecciono el que tiene cargado el cobro encontrado*/
				var options = [{text: 'Seleccione un item...', value : ''}];
				if(result.listaCopropietarios!=null){
				    $.each(result.listaCopropietarios, function(index, option) {
					options.push(option);
				    });
				}
				$("#selectCopropietario").replaceOptions(options);

				$('#selectCopropietario').val(result.descobro.idCopropietario);
				$('#selectMotivo').val(result.descobro.idMotivoReversion);
				
				if (!$.isEmpty(result.descobro.observacion)) {
					$('#prevObservText').val(result.descobro.observacion);
					$('#prevObservText').show();
				}
				if (!$.isEmpty(result.descobro.observacionAnterior)) {
				    $('#prevObservTextAnterior').val(result.descobro.observacionAnterior);
				    $('#prevObservacionesAnterior').show();
				    $('#prevObservText').val("");
				}else{
				    $('#prevObservacionesAnterior').hide();
				}
				
				if($("#selectMotivo").val() == '5'){
				    $('#comboIdReversionPadre').show();
				}else{
				    $('#comboIdReversionPadre').hide();
				}

//				$("#divLoader").css({
//					height: ($.getDocHeight()) + 'px'
//				});
				/*Lista de Comprobantes*/
				if(result.descobro.listaComprobantes != null
					&& result.descobro.listaComprobantes != undefined){
				    	datosTablas.comprobantes = [];
					$.each(result.descobro.listaComprobantes, function (j, seleccionado){
					    datosTablas.comprobantes.push(seleccionado);
					});
					if(datosTablas.comprobantes!=null){
					    if (datosTablas.comprobantes.length > 0) {
						tablas.comprobantes.fnClearTable();
						tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);
					    } else {
						tablas.comprobantes.fnAdjustColumnSizing(false);
					    }
					}
				}
//				anularGuardar = true;
//				anularGuardar = false;
				
			}
			if($('#prevObservTextAnterior').val() != ""){
			    $('#prevObservacionesAnterior').show();
			}else{
			    $('#prevObservacionesAnterior').hide();
			}
			ocultarBloqueEspera();
		}
	});
};

//Guido.Settecerze
function actualizarEstadoCobroVista(descripcionEstado, descripcionMarca) {
	$('#cobroEstado').val(descripcionEstado);
	$('#divEstado').show();
	if (!$.isEmpty(descripcionMarca) && '-' !== descripcionMarca.trim()) {
		var marcas = descripcionMarca.split('|');
		$('#cobroSubEstados').val(marcas.join('\n'));
		//TODO UEHARA dejo por un tiempo por si hay que retornar todos los marcas
		//$('#cobroSubEstados').css('height', (20 * marcas.length) + 'px');
		$('#divCobroSubEstados').show();
	} else {
		$('#cobroSubEstados').val('');
		$('#divCobroSubEstados').hide();
	}
}

//Guido.Settecerze
var validarDatosDescobro = function(){
    errorCamposObligatorios = false;
    if (!($.validacionCaracteresEspeciales(prevObservText))) {
	$("#errorObservaciones").text("Este campo debe contener un formato de texto correcto.");
	$("#errorObservaciones").show();
	errorCamposObligatorios = true;
    } 
    if ($.isEmpty($('#selectEmpresa').val())) {
	$("#errorEmpresa").text("Este campo es requerido.");
	$("#errorEmpresa").show();
	errorCamposObligatorios = true;
    }
    if ($.isEmpty($('#selectSegmento').val())) {
	$("#errorSegmento").text("Este campo es requerido.");
	$("#errorSegmento").show();
	errorCamposObligatorios = true;
    }
    if ($.isEmpty($('#selectMotivo').val())) {
	$("#errorMotivo").text("Este campo es requerido.");
	$("#errorMotivo").show();
	errorCamposObligatorios = true;
    }
};


//Guido.Settecerze
var validarAntesDeRevertir = function(){
  errorCampoParaRevertir = false;
  if ($.isEmpty($('#selectIdReversionPadre').val()) && $("#selectMotivo").val() == '5') {
	$("#idErrorReversion").text("Este campo es requerido.");
	$("#idErrorReversion").show();
	errorCampoParaRevertir = true;
  }
  if ($('#selectIdReversionPadre').val() == "Otro" && $.isEmpty($('#idReversionPadreOtro').val())) {
	$("#idErrorOtros").text("Este campo es requerido.");
	$("#idErrorOtros").show();
	errorCamposObligatorios = true;
  }
};

//Guido.Settecerze
var validarEdicionDescobro = function (marcaSimulacion){
    if(marcaSimulacion == null){
    	marcaSimulacion = "";
    }
    
    var descobro = {
	    idDescobro : $('#idDescobro').val()
    };
    $.ajax({
	"type" : "POST",
	"url": "configuracion-descobro/validarEdicionDescobro",
	"dataType": "json", 
	"data": JSON.stringify(eval(descobro)), 
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
	    varEdicionSegunEstadoMarca = "";
	    if (result.informacionMensaje=="edicionParcial") {
			habilitarEdicionParcial();
	    } else if(result.informacionMensaje=="sinEdicion") {
	    	$('#conf-cobro-tabs :input').attr('disabled', true);
	    	$('#conf-cobro-tabs :button').attr('disabled', true);
	    	$('#btnVolver').attr('disabled', false);
	    	$('#btnVolver2').attr('disabled', false);
	    	$('#btnVer1').attr('disabled', false);
	    	$('#btnVer2').attr('disabled', false);
	    	$('#prevObservaciones').attr('disabled', true);
	    	$('#idReversionPadreOtro').attr('disabled', true);
	    	
	    	varEdicionSegunEstadoMarca = "sinEdicion";
	    } else if (result.informacionMensaje=="" || result.informacionMensaje=="edicionErrorPrimerDescobro"){
		    $('#conf-cobro-tabs :input').attr('disabled', false);
			$('#conf-cobro-tabs :button').attr('disabled', false);
			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);
			$('#bloqueAgregarComprobante').show();
			$('#idReversionPadreOtro').attr('disabled', false); 
			if(marcaSimulacion == SIMULACION_BATCH_FINALIZADA_CON_EXITO){
				
				simulacionOK = true;
				
				if(transaccionesOK){
		    		$('#btnRevertir').prop('disabled', false);
		    	}else{
		    		$('#btnRevertir').prop('disabled', true);
		    	}
	    	}else{
	    		$('#btnRevertir').prop('disabled', true);
	    	}
			
	    	validarBtnHabilitarSimular();
			varEdicionSegunEstadoMarca = "";
			
			if(result.informacionMensaje=="edicionErrorPrimerDescobro"){
				
				$('#btnRevertir').prop('disabled', false);
			}
		}
	    if (!$.isEmpty($('#idLegajo').val())) {
	    	$('#divLegajo').show();
	    	$('#selectMotivo').val('1');
	    	$('#selectMotivo').attr('disabled', true);
	    }
	    $('.bloqueDescargar').attr('disabled', false);
	    $('.busquedaResultado').attr('disabled', false);
	}
    });
};

//Guido.Settecerze
function habilitarEdicionParcial(){
	$('#conf-cobro-tabs :input').attr('disabled', true);
	$('#conf-cobro-tabs :button').attr('disabled', true);
	$('#searchCriteraCliente').hide();
	$('#bloqueAgregarComprobante').hide();
	$('#selectCopropietario').attr('disabled', false);
	$('#selectMotivo').attr('disabled', false);
	$('#prevObservText').attr('disabled', false);
	$('#btnGuardar').attr('disabled', false);
	$('#btnGuardar2').attr('disabled', false);
	$('#btnVolver').attr('disabled', false);
	$('#btnVolver2').attr('disabled', false);
	$('#btnVer1').attr('disabled', false);
	$('#btnVer2').attr('disabled', false);
	$('#selectIdReversionPadre').attr('disabled', false);
	$('#idReversionPadreOtro').attr('disabled', false);
	$('#controlsOtro').attr('disabled', false);
	$('#idReversionPadreOtro').attr('disabled', false);
	$('.otrosBloque').attr('disabled', false);
	varEdicionSegunEstadoMarca = "edicionParcial";
};


//Guido.Settecerze
var validarEdicionDescobroSegunUsuario = function (){
  var descobro = {
	  idDescobro : $('#idDescobro').val()
  };
  $.ajax({
	"type" : "POST",
	"url": "configuracion-descobro/validarEdicionDescobroSegunUsuario",
	"dataType": "json", 
	"data": JSON.stringify(eval(descobro)), 
	"contentType": "application/json",
	"mimeType": "application/json",
	"success" : function(result) {
		if(result.success) {
			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);

			varEdicionSegunEstadoMarca = "edicionParcial";
		}
	}
  });
};

//Guido.Settecerze
function cambiarModoEdicion(informacionMensaje, validarEdicionCobroSegunUsuario) {
	varEdicionSegunEstadoMarca = "";

	if (validarEdicionCobroSegunUsuario) {
		$('#selectCopropietario').attr('disabled', false);
		$('#selectMotivo').attr('disabled', false);
		$('#prevObservText').attr('disabled', false);
		varEdicionSegunEstadoMarca = "edicionParcial";
		habilitarEdicionParcial();
	} else {
		if (informacionMensaje=="edicionParcial") {
			habilitarEdicionParcial();
		} else if(informacionMensaje=="sinEdicion") {
		    $('#conf-cobro-tabs :input').attr('disabled', true);
			$('#conf-cobro-tabs :button').attr('disabled', true);
			$('#selectCopropietario').attr('disabled', true);
			$('#selectMotivo').attr('disabled', true);
			$('#prevObservText').attr('disabled', true);
			$('#bloqueAgregarComprobante').hide();
			varEdicionSegunEstadoMarca = "sinEdicion";
		} else if (informacionMensaje==""){
		    $('#conf-cobro-tabs :input').attr('disabled', false);
			$('#conf-cobro-tabs :button').attr('disabled', false);
			$('#selectCopropietario').attr('disabled', false);
			$('#selectMotivo').attr('disabled', false);
			$('#prevObservText').attr('disabled', false);
			$('#bloqueAgregarComprobante').show();
			varEdicionSegunEstadoMarca = "";
		}
	}
	$('.bloqueDescargar').attr('disabled', false);
	$('.busquedaResultado').attr('disabled', false);

}

function addRow(sheet){
	var letras = ['','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	var nextRow = $('row:last', sheet).attr('r') * 1 + 1;
	var lastCol = $('col:last', sheet).attr('min') * 1;
	var i = 0;
	var j = 1;
	var k = 0;

	$('sheetData', sheet).append('<row r="'+ nextRow +'"></row>');
	for( i; i <= lastCol; i++){
		if(j <= 26){
			$('sheetData row[r="'+ nextRow +'"]', sheet).append('<c t="inlineStr" r="'+ letras[k] + letras[j] + nextRow +'"><is><t></t></is></c>');
			j++;
		}else{
			j=1;
			k++;
		}
	};	
};

function moveDown(sheet){
	var nextRow = $('row:last', sheet).attr('r') * 1 + 1;
	var i = nextRow;
	for( i ; i > 1 ; i-- ){
		$('row c[r*="' + i + '"]', sheet).each(function(){
			var letra = $(this).attr('r').replace(/[0-9]/,"");
			var cellText = $('row c[r="'+letra + (i-1) +'"] t', sheet).text();
			$('is t', this).text(cellText);
		});
	}
	$('row:first c', sheet).each(function(){$('is t', this).text("");});
}

function columnWidth(sheet){
	$('cols col',sheet).each(function(){
		$(this).attr('width',$(this).attr('width') > 5 ? $(this).attr('width') * 1 + 1: $(this).attr('width'));
	});
}

var buscarTransacciones = function (id){
	
	$('#bloqueEspera').trigger('click');
	var cobro = {
			idCobro : id,
		};
	
	$.ajax({
		"type" : "POST",
		"url": "configuracion-descobro/buscarTransaccionesCobro",
		"dataType": "json", 
		"data": JSON.stringify(eval(cobro)), 
		"contentType": "application/json",
		"mimeType": "application/json",
		"success" : function(result) {

			if (result.success) {
				/* Cargo la lista de copropietarios posibles y selecciono el que tiene cargado el cobro encontrado*/
//				$.each(result.listaSistemaTransaccion, function(index, option) {
//					sistemaTransaccion.push(option);
//				});
				
				$.each(result.listaSistemaTransaccion, function(index, option) {
					sistemaTransaccion.push(option);
				});
				
				datosTablas.transacciones = result.aaData;
				tablas.transaccionesPrev.fnAddData(result.aaData,true);
			}
			ocultarBloqueEspera();
		}
	});
	
};

