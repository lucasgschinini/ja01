	var cobroRevertir = 'cobroRevertir';
    inicializarTabla(cobroRevertir);
    var prevTransacciones = null;
//    var operacionesCobroRelacionadas = 'operacionesCobroRelacionadas';
//    inicializarTabla(operacionesCobroRelacionadas);
    var comprobantes = 'comprobantes';
    inicializarTabla(comprobantes);
    var faltaCompletar = 'faltaCompletar';
    inicializarTabla(faltaCompletar);
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

    var TRANS_COL_POS_PORCENTAJE = 25;
    var TRANS_COL_POS_IMPORTE = 26;
    var TRANS_COL_POS_ESTADO = 28;
    var TRANS_COL_POS_MENSAJE = 29;

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
    var estadosErrores=['Error en apropiación de medio de pago', 'Error en apropiación de deuda', 'Error en apropiación de medio de pago y deuda', 'Error en apropiación del tratamiento de diferencia', 'Desapropiada', 'Error. Aplicación Manual Rechazada', 'Sin procesar por error en grupo'];
    
    var cobrosRelacionadosPrev = [];

    var tablas = {
	    documentosRelacionados: null,
	    transaccionesPrev: null
    };

    var datosTablas = {
	    documentosRelacionados : [],
    	transacciones : []
    };


$(document).ready(function() {
	
	var codigoOperacionesExternas = 'codigoOperacionesExternas';
	    inicializarTabla(codigoOperacionesExternas);
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
   					customize: function( xlsx ){
   						var sheet = xlsx.xl.worksheets['sheet1.xml'];
   						
						//Se cambia el formato de las celdas para que aparezcan como texto
						$('row c[r^="A"]', sheet).each(function(){
							var val = $(this).text();
							$(this).attr('t', 'inlineStr').html('<is><t>'+ val +'</t></is>');
						});
   					},
   					title: "Reversion_Cobro_Transacciones",
   					exportOptions: {
   						columns: ':visible'
   					},
   					className: 'excelbtn'
   					}],
   					"orderFixed": [[ 41, 'desc' ],[ 2, 'desc' ],[ 42, 'desc' ],[ 39, 'asc' ],[ 40, 'asc' ],[ 4, 'desc' ],[ 5, 'desc' ],[ 7, 'desc' ],[ 8, 'desc' ],[ 18, 'desc' ],[ 19, 'asc' ]],
   				"columnDefs": [{
				      "targets": 'nosort',
				      "orderable": false
				    }],
				    //se deshabilita el orden de las columnas
   				"aoColumns" : [
 					{ "mData" : null,
 	 					"render" : function(data, type, row) {
 	 						if (type === 'display') {
 	             			  var showCheck = (estadosErrores.indexOf(row.estadoTransaccion) < 0);
 	             			  return (showCheck) ? '<input type="checkbox" id="checkRevertir" name="checkRevertir" disabled="disabled" ' + ((data) ? 'checked="checked"' : '') + '>' : '-';
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
   				 	{ "mData" : "importe" },	//10
   				 	{ "mData" : "tipoDeCambioFechaCobro" ,//11
   				 		"mRender": function (data, type, row) {
   				 			//cuando colorLetraRegistro es 0, no es diferencia de cambio
   				 			if (row.moneda !== $('#monedaOperacion').val() && row.colorLetraRegistro === '0') {
   				 				return data;
   				 			}
   				 			return '-';
   				 		}
   				 	},
// 				 	deberia venir con 7 decimales con coma
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
   				 	{ "mData" : "subtipoMedioPago",//16
   				 		'fnCreatedCell': function(nTd, sData, oData, iRow, iCol) {
   				 	        nTd.title = oData.subtipoMedioPagoDesc != null?oData.subtipoMedioPagoDesc:"";
   				 	   }
   				 	},
   				 	{ "mData" : "referenciaMedioPago" },//17
   				 	{ "mData" : "fechaMedioPago" },//18
   				 	{ "mData" : "monedaMedioPago" },//19
   				 	{ "mData" : "importeMedioPago"},//20
   				 	{ "mData" : "tipoDeCambioFechaCobroMedioPago",//21
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
   				 	{ "mData" : "intereses" },//23
   				 	{ "mData" : "trasladarInteresesFormateado"},//24
   				 	{ "mData" : "porcABonificar"},//25
   					{ "mData" : "importeABonificar"},//26
   					{ "mData" : "sistemaAcuerdo"},//27
   				 	{ "mData" : "acuerdoDestinoCargos"},//28
   				 	{ "mData" : "estadoAcuerdoDestinoCargos"},//29
   				 	{ "mData" : "estadoCargoProximaFactura"},//30
   				 	{ "mData" : "sistemaAcuerdoFactDestinoContracargosDescripcion"},//31
   				 	{ "mData" : "acuerdoFactDestinoContracargos"},//32
   				 	{ "mData" : "estadoAcuerdoDestinoContracargos"},//33
   				 	{ "mData" : "mensajeTransaccion"},//34
   				 	{ "mData" : "mensajeDebito" },//35
   				 	{ "mData" : "mensajeCredito" },//36
   				 	//si esta columna viene con 1 es diferencia de cambio
   				    { "mData" : "colorLetraRegistro" , "visible": false },//37
   				    // Esta columna se usa para ordenar los registros de diferencia de cambio
				    { "mData" : "numeroTransaccionOriginal" , "visible": false },//38
				    { "mData" : "numeroGrupo", "visible" : false },
				    { "mData" : "numeroTransaccionFormateado" , "visible": false }
   				],
   				"fnDrawCallback": function(oSettings) {
//   					quita el estilo css por defecto para el cambio de color de fondo de los registros de la grilla de transacciones
//   					si se cambia de lugar probablemente no funcione
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
					className: 'excelbtn',
					exportOptions: {
   						columns: ':visible'
   					}
				}],
//			se deshabilita el orden de las columnas
			"aoColumns" : [
			               { "mData" : "nroTransaccionFormateado" },
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
    
    
    
    var cobrosRelacionadosPrevSettings = {
			"sDom" : 'Tfrtip',
			"sScrollX": true,
			"scrollY": '500px',
			"bScrollCollapse": true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"order": [[ 19, 'desc' ]],
			"oTableTools": {	
				"sSwfPath": urlAsynchronousHTTP + "/fonts/copy_csv_xls_pdf.swf",
				"aButtons": [{
					"sExtends": "xls",
					"sButtonText":"Excel",
					"sTitle": "Operaciones_Cobros_Relacionados_Previsualizacion",
					"mColumns": "visible"
				}]
				
			},
//			se deshabilita el orden de las columnas
			"aoColumns" : [
							{ "targets" : 0,
								   "searchable" : false,
								   "bSortable" : false,
								   "data" : null,
								   "render" : function(data, type, row) {
									   if (type === 'display') {
										   if(row.sistema == 'Shiva'){
											   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+row.idOperacionRelacionada+','+null+')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
										   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+row.idOperacionRelacionada+','+null+')"><i class="icon-eye-open bigger-120"></i></button></div>';
									   }
			            		   }
			            		   return null;
			            	   }
			               },
			]
		};
	cobrosRelacionadosPrev = $("#operacionesCobroRelacionadas").dataTable(cobrosRelacionadosPrevSettings);
	
	actualizarEstadoCobroVista($("#estadoDescripcion").val(), $("#marcaDescripcion").val());
	
	
	if (!$.isEmpty($('#idLegajo').val())) {
    	$('#divLegajo').show();
    }
});

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

//u573005, fabio.giaquinta.ruiz, sprint 7
var exportarDetalleAExcel = function () {
	
	$('#bloqueEspera').trigger('click');
    $('#btnExportar').attr('disabled', true);
    beforeUnload.off();
    
//    $('#idOperacionFormateado').val($('#idOperacionDescobro').val());
    $('#formDescobro')[0].action=urlAsynchronousHTTP+"/descobro-detalle-aprobacion/exportacionDetalleDescobro";
	$('#formDescobro')[0].submit();
    
    setTimeout(function() {
		$('#btnExportar').attr('disabled', false);
//		beforeUnload.on();
		ocultarBloqueEspera();
    }, 3000);
};

var historialCobro = function () {
	
	$('#bloqueEspera').trigger('click');
    $('#btnHistorial').attr('disabled', true);
    beforeUnload.off();
    
    $('#formDescobro')[0].action=urlAsynchronousHTTP+"/descobro-historial";
	$('#formDescobro')[0].submit();
    
    setTimeout(function() {
		$('#btnHistorial').attr('disabled', false);
		beforeUnload.on();
		ocultarBloqueEspera();
    }, 3000);
};

//Guido.Settecerze
function descargarComprobante(idComprobante) {
	$('#formDescobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion/descargarComprobante?id=" + idComprobante;
	$('#formDescobro')[0].submit();
};

//Guido.Settecerze
function volverBusqueda() {
    beforeUnload.off();
    $('#goBack').val("true");
    $('#formDescobro')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formDescobro')[0].submit();
};

function detalleCobro(idOperacionRelac,idCobro){
	
	$('#opcion').val("D");
	if(idOperacionRelac != null){
		$('#idOperacionRelacionada').val(idOperacionRelac);
	}else if(idCobro != null){
		$('#idCobro').val(idCobro);
	}
	$('#formDescobro')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion";
    $('#formDescobro')[0].submit();
};

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

var buscarOperacRelac = function (id){
	
	var descobro = {
			idDescobro : id,
			monedaOperacion : $("#monedaOperacion").val()
		};
	
	$.ajax({
	    "type" : "POST",
	    "url": "detalle-descobro/buscarOperacionesRelacionadas",
	    "dataType": "json", 
	    "data": JSON.stringify(eval(descobro)), 
	    "contentType": "application/json",
	    "mimeType": "application/json",
	    "success" : function(result) {

	    	if (result.success) {
	    		if(result.aaData != null && !$.isEmpty(result.aaData)){
	    			cobrosRelacionadosPrev.fnClearTable();
	    			cobrosRelacionadosPrev.fnAddData(result.aaData,true);
	    		}

	    		if(result.transacciones != null && !$.isEmpty(result.transacciones)){
	    			datosTablas.transacciones = result.transacciones;
	    			tablas.transaccionesPrev.fnClearTable();
	    			tablas.transaccionesPrev.fnAddData(result.transacciones, true);
	    		}

	    		if(result.listaDocumentosRelacionados != null && !$.isEmpty(result.listaDocumentosRelacionados)){
	    			tablas.documentosRelacionados.fnClearTable();
	    			tablas.documentosRelacionados.fnAddData(result.listaDocumentosRelacionados,true);
	    		}
	    	}
	    	ocultarBloqueEspera();
	    }
	});
	

};