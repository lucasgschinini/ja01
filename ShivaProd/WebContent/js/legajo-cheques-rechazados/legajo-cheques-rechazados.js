var BTN_NO_CLASS = 'btn-no-class';	// No es una clase CSS se utiliza como
									// selector
var HTML_ICON_BLANK = '<i class="icon-plus bigger-120 icon-hidden"></i>';
var HTML_ICON_PLUS = '<i class="icon-plus bigger-120"></i>';
var HTML_ICON_MINUS = '<i class="icon-minus bigger-120"></i>';
var BTN_NO_CLASS = 'btn-no-class'; // No es una clase CSS se utiliza como
									// selector
var BTN_NO_CLASS_PLUS = 'btn-no-class-plus';
var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100;
var SISTEMA_USUARIO = "";
var alta = true;
var primeraVes = true;
var selectCopropietario = '';
var clienteSieble = null;
var solapaReturn = true;
var coso = true;

var exportExcel = false;


var tablas = {
	tablaBusquedaCheques : null,
	tablaChequesSeleccionados : null,
	tablaCobrosRelacionados : null,
	tablaDocumentosRelacionados: null,
	tablaNotificaciones : null,
	comprobantes : null,
	tablaChequesPrev : null,
	tablaCobrosRelacionadosPrev : null,
	tablaDocumentosRelacionadosPrev : null,
	tablaNotificacionesPrev : null,
	comprobantesPrev : null
};
var datosTablas = {
	tablaChequesSeleccionados: [],
	comprobantes : [],
	notificaciones : []
};
var combos = {
	acuerdo : null,
	banco : null,
	bancoCuenta : null,
	optionBacia: null,
	edbancoOrigen:null,
	bancoOrigen: null
};
var solapa = {
	CHEQUE : 0,
	COBRO : 1,
	REEMBOLSO : 2,
	NOTIFICACION : 3,
	COMPROBANTE : 4,
	RESUMEN : 5,
	size: 6,
	current: 0,
	next: null,
	solapaReturn: true,
	pasarSolapa: true,
	permitir : function() {
		// En el momento del alta. No se puede mover a ningun solapa
		if (alta && (this.next != this.CHEQUE)) {
			ocultarBloqueEspera();
			return false;
		} else if (
			!alta &&
			SISTEMA_MAP.USUARIO.descripcion === legajoChequeRechazado.sistemaOrigen && this.next === this.COBRO ) {
			ocultarBloqueEspera();
			return false;
		}
		return true;
	},
	validar: function (solapa, legajo) {
		salida = false;
		$('#selectUbicacion').attr('disabled', true);
		switch (solapa) {
		// Solapa de datos generales del cheque:
		
		case this.CHEQUE:
			if (
				EDICION_MAP.SIN_EDICION.name !== legajo.edicionTipo 
			) {
				$('#selectCopropietario').attr('disabled', false);
				$('#prevObservText').attr('disabled', false);
			} else {
				$('#selectCopropietario').attr('disabled', true);
				$('#prevObservText').attr('disabled', true);
			}

			if (
				EDICION_MAP.EDICION_PARCIAL_2.name === legajo.edicionTipo ||
				EDICION_MAP.EDICION_PARCIAL_6.name === legajo.edicionTipo
			) {
				$('#selectUbicacion').attr('disabled', false);
			} else if (
				EDICION_MAP.EDICION_PARCIAL_4_1.name === legajo.edicionTipo ||
				EDICION_MAP.EDICION_PARCIAL_5_1.name === legajo.edicionTipo
			) {
				$('#selectUbicacion').attr('disabled', false);
			}
			salida = true;
			break;
			
		// Solapa de cobros relacionados
		case this.COBRO:
			// TODO Usuario se restrige desde el desgrisar... ver si meter aca
			if (EDICION_MAP.EDICION_PARCIAL_2.name === legajo.edicionTipo) {
				$('#btnConfirmarManual').show();
				salida = true;
			} else {
				$('#btnConfirmarManual').hide();
				salida = false;
			}
			break;
		case this.REEMBOLSO: 
			salida = true;
			break;
		case this.NOTIFICACION:
			if (EDICION_MAP.EDICION_PARCIAL_1.name === legajo.edicionTipo) {
				$('#idNotificacionesInput').hide();
			}
			salida = true;
			break;
		case this.COMPROBANTE:
			if (EDICION_MAP.EDICION_PARCIAL_1.name === legajo.edicionTipo) {
				$('#bloqueAgregarComprobante').hide();
			}
			salida = true;
			break;
		default:
			break;
		}
		return salida;
	},
};
var draw = null;
var clear = null;
$(document).ready(function() {
	beforeUnload.unbind();
	beforeUnload.off();
	beforeUnload.on();
	// Mantengo en memoria las opciones de los combos banco, acuerdo y cuenta
	combos.acuerdo = $('#edAcuerdoCheque option');
	combos.banco = $('#edBancoDeposito option');
	combos.bancoCuenta = $('#edCuentaDeposito option'); 
	combos.bancoOrigen = $('#codigoBanco option');
	combos.edbancoOrigen = $('#edCodigoBancoOrigen option');
	// Tabs
	// Eventos que dibuja los tabs
	var drawTabCheques = function() {
		solapa.validar(0 , legajoChequeRechazado);
	};
	var clearTabCheques = function() {
	};
	
	var drawTabComprobantes = function() {
		solapa.validar(solapa.next , legajoChequeRechazado);
//		tablas.comprobantes.fnClearTable();
//		
//		
//		tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);

	};
	var clearTabComprobantes = function() {
	};
	
	
	draw = [drawTabCheques, drawTabCobros, drawTabReembolso, drawTabNotificaciones, drawTabComprobantes, drawTabResumenlegajo];
	clear = [clearTabCheques, clearTabCobros, clearTabReembolso, clearTabNotificaciones, clearTabComprobantes, clearTabResumenlegajo];
	

	var onStepChangingFunction = function(event, currentIndex, newIndex) {
		if (!solapa.permitir()) {
			ocultarBloqueEspera();
			return false;
		}
		
		if (!validarLegajo()) {
			ocultarBloqueEspera();
			return false;
		} 
//		setTimeout(
//			function() {
//				draw[newIndex]();
//				clear[currentIndex]();
//			}, 1300);
		//ocultarBloqueEspera();
		return true;
	};
	
	$("#gestion-cheques-rechazados-tabs").steps({
		headerTag : "h3",
		bodyTag : "div",
		transitionEffect : "none",
		enableFinishButton : false,
		enablePagination : false,
		enableAllSteps : true,
		finish : true,
		titleTemplate : "#title#",
		cssClass : "tabcontrol",
		onStepChanging : function(event, currentIndex, newIndex) {
			$('#bloqueEspera').trigger('click');
	
			solapa.current = currentIndex;
			solapa.next = newIndex;
			
			if (solapa.next === solapa.NOTIFICACION  && !solapa.pasarSolapa) {
				solapa.pasarSolapa = true;
				setTimeout(function() {
					$("#gestion-cheques-rechazados-tabs-t-" + solapa.current).click();}
				, 1);
				return true;
			} else if (solapa.current === solapa.NOTIFICACION && solapa.solapaReturn) {
				if (validarDivImputSinGuardar("idNotificaciones")) {
					var mensaje = 'Existe una notificación en proceso de configuración. Si presiona "Aceptar" se perderá la información que aún no fue agregada a la grilla.';
					bootbox.confirm(mensaje, function(result) {
						if (result) {
							solapa.solapaReturn = false;
							ocultarBloqueEspera();
							solapa.pasarSolapa = false;
							clearSelectInput("idNotificaciones");
						} else {
							solapa.solapaReturn = false;
							solapa.pasarSolapa = true;
						}
						$("#gestion-cheques-rechazados-tabs-t-" + solapa.current).click();
						
					});
					ocultarBloqueEspera();
					return true;
					
				} else {
					return onStepChangingFunction(event, currentIndex, newIndex);
				}
			} else {
				solapa.solapaReturn = true;
				solapa.pasarSolapa = true; 
				return onStepChangingFunction(event, currentIndex, newIndex);
			}
		},
		onStepChanged : function(event, currentIndex, priorIndex) {
			
			
			$("#divLoader").css({
				height : ($.getDocHeight()) + 'px'
			});
			if(currentIndex == solapa.COMPROBANTE){
				tablas.comprobantes.fnAdjustColumnSizing(true);
			} else if (currentIndex == solapa.NOTIFICACION){
				tablas.tablaNotificaciones.fnAdjustColumnSizing(true);
			}
			return true;
		}
	});

	// Para actualizar informaciones del paginado
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(
			oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0
					: Math.ceil(oSettings._iDisplayStart
							/ oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0
					: Math.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB Cheques		cheques-rechazados-cheques.js
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	if (isUndefinedNullDashEmptyOrFalse(edicion)) {
		grisarSolapas();
		// Tabla de cheques
		var chequesSearchSettings = {
			dom : '<"botonExcelchequesSearchSettings">Bfrt',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"bProcessing" : true,
			"bPaginate" : false,
			"bStateSave" : false,
			"bSortClasses" : false,
			"iDisplayLength" : DISPLAY_LENGTH,
			buttons : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Cheques_a_seleccionar",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
						body : function ( data, column, row ) {
							if (column === 0) {
								data = '-';
							} else if (column === 5) { //clientes
								if (!$.isEmpty(data)) {
									data = data.split("<br>").join(" - ");
								} else  if (column === 6) {
									data = '-';
								}
							}
							return data;
						}
					}
				}
			} ],
			"aoColumns" : [{
				"mData" : null,
				"render" : function(data, type, row) {
					if (type === 'display') {
	
						return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">'
								+ '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS_PLUS
								+ data.idPantalla
								+ '" title="Agregar cheque" idPantalla=' + data.idPantalla +'>'
								+ HTML_ICON_PLUS
								+ '</button>' + '</div>';
					}
					return data;
	
				},
				"className" : "dt-body-center",
				"searchable" : false,
				"bSortable" : false
			}, {
				"mData" : "idPantalla",
				"visible" : false,
				"searchable" : false
			}, // 1
			{
				"mData" : "sistemaOrigen"
			}, // 2
			{
				"mData" : "nroCheque",
			}, // 3
			{
				"mData" : "descripcionBancoOrigen"
			}, //4
			{
				"mData" : "codBancoOrigen"
			},// 5
			{
				"mData" : "clientes", // Cliente
				"mRender" : function(data, type, row) {
				if (!$.isEmpty(data)) {
					var clientes = "";
					for (var i = 0; i < data.length; i++) {
						clientes +=   (i === 0 ? "" : "<br>");
						var nombre = data[i].idClienteLegado + (data[i].razonSocial == null ? "" : " "  + data[i].razonSocial.substr(0, 15));
						clientes += nombre;
					}
					return clientes;
				}
				return '-';
			}
			}, // 3
			{
				"mData" : "tipoCheque"
			}, // 3
			{
				"mData" : "fechaVenc"
			},// 9
			// 5
			{
				"mData" : "moneda",
				"mRender" : function(data, type, row) {
					if (row.moneda !== "") {
						return returnSignoMoneda(row.moneda);
					}
					return '-';
				}
			}, // 10
			{
				"mData" : "importe",
			}, // 11
			{
				"mData" : "fechaDeposito"
			}, // 7
			{
				"mData" : "bancoDeposito"
			}, // 13
			{
				"mData" : "cuentaDeposito"
			}, // 14
			{
				"mData" : "acuerdo"
			}, // 12
			{
				"mData" : "fechaRecepcion"
			}, // 8
			{
				"mData" : "estado"
			}, // 15
			{
				"mData" : "empresa"
			}, // 16
			{
				"mData" : "segmento"
			}, // 17
			{
				"mData" : "analista"
			}, // 18
			{
				"mData" : "copropietario"
			}, // 19
			{
				"mData" : "analistaTeamComercial"
			}, // 20
			{
				"mData" : "idInternoValor"
			} // 21
			],
			'columnDefs' : [ // targets son los th con las class // date o importe
			{
				type : 'latam_date', targets : 'date'
			}, {
				type : 'comparador-currency', targets : 'importe'
			}]
		};
		tablas.tablaBusquedaCheques = $("#tablaBusquedaCheques").dataTable(chequesSearchSettings);
		agregarBotonExcel(tablas.tablaBusquedaCheques.api(),
				"Cheques_a_seleccionar",
				null,
				function(data, column, row) {
					
					return data;
				});
		crearTablaFixedColumn(datosTablas, tablas,"tablaBusquedaCheques");
		registrarEventoPaginacion('Cheques', 'tablaBusquedaCheques', CANTIDAD_POR_PAGINA_CHEQUE, 'next');
		registrarEventoPaginacion('Cheques', 'tablaBusquedaCheques', CANTIDAD_POR_PAGINA_CHEQUE, 'previous');
		
	}
	var chequesSeleccionadosSettings = {
			dom : '<"agregarTodosCaps">Bfrt',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"bProcessing" : true,
			"bPaginate" : false,
			"bStateSave" : false,
			"bSortClasses" : false,
			"iDisplayLength" : DISPLAY_LENGTH,
			"buttons" : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Cheques_seleccionados",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
						body : function ( data, column, row ) {
							if (column === 0) {
								data = '-';
							} else if (column === 5) {
								if (!$.isEmpty(data)) {
									data = data.split("<br>").join(" - ");
								}
							} else if (column === 6) {
								data = '-';
							}
							return data;
						}
					}
				}
			} ],
			"aoColumns" : [ // 0
					{
						"mData" : null,
						"render" : function(data, type, row) {
							if (type === 'display') {
	
								return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">'
										+ '<button type="button" class="btn btn-xs btn-link ' + BTN_NO_CLASS
										+ data.idPantalla
										+ '" title="Agregar cheque" idPantalla=' + data.idPantalla +'>'
										+ HTML_ICON_MINUS
										+ '</button>' + '</div>';
							}
							return data;
	
						},
						"className" : "dt-body-center",
						"searchable" : false,
						"bSortable" : false
					}, {
						"mData" : "idPantalla",
						"visible" : false,
						"searchable" : false
					}, // 1
					{
						"mData" : "sistemaOrigen"
					}, // 2
					{
						"mData" : "nroCheque",
					}, // 6
					{
						"mData" : "descripcionBancoOrigen"
					}, 
					{
						"mData" : "codBancoOrigen"
					},// 4
					{
						"mData" : "clientes", // Cliente
						"mRender" : function(data, type, row) {
						if (!$.isEmpty(data)) {
							var clientes = "";
							for (var i = 0; i < data.length; i++) {
								clientes +=   (i === 0 ? "" : "<br>");
								var nombre = data[i].idClienteLegado + (data[i].razonSocial == null ? "" : " "  + data[i].razonSocial.substr(0, 15));
								clientes += nombre;
							}
							
							return clientes;
						}
						return '-';
					}
					}, // 3
					{
						"mData" : "tipoCheque"
					}, // 3
					{
						"mData" : "fechaVenc"
					},// 9
					// 5
					{
						"mData" : "moneda",
						"mRender" : function(data, type, row) {
							if (row.moneda !== "") {
								return returnSignoMoneda(row.moneda);
							}
							return '-';
						}
					}, // 10
					{
						"mData" : "importe",
					}, // 11
					{
						"mData" : "fechaDeposito"
					}, // 7
					{
						"mData" : "bancoDeposito"
					}, // 13
					{
						"mData" : "cuentaDeposito"
					}, // 14
					{
						"mData" : "acuerdo"
					}, // 12
					{
						"mData" : "fechaRecepcion"
					}, // 8
					{
						"mData" : "estado"
					}, // 15
					{
						"mData" : "empresa"
					}, // 16
					{
						"mData" : "segmento"
					}, // 17
					{
						"mData" : "analista"
					}, // 18
					{
						"mData" : "copropietario"
					}, // 19
					{
						"mData" : "analistaTeamComercial"
					}, // 20
					{
						"mData" : "idInternoValor"
					} // 21
			],
			'columnDefs' : [ // targets son los th con las class
			// date o importe
			{
				type : 'latam_date',
				targets : 'date'
			}, {
				type : 'comparador-currency',
				targets : 'importe'
			} ]
		};
	
//	// TODO VER POR QUE NO ANDA!!!!!

	tablas.tablaChequesSeleccionados = $("#tablaChequesSeleccionados").dataTable(chequesSeleccionadosSettings);
	agregarBotonExcel(tablas.tablaChequesSeleccionados.api(),
			"Cheques_seleccionados",
			null, function(data, column, row) {
				
				return data;
			});
	crearTablaFixedColumn(datosTablas, tablas,"tablaChequesSeleccionados");
	
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// TAB COBROS RELACIONADOS
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	var cobrosRelacionados = {
			dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : DISPLAY_LENGTH,
			"iDisplayStart" : 0,
			"order": [[ 17, "desc"]],
			"buttons" : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Cobros_Relacionados",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
						body : function ( data, column, row ) {
							if (column === 0 || column === 1 || column === 2 || column === 25 || column === 26) {
								data = '';
							} else if (column === 5) {
								if (!$.isEmpty(data)) {
									data = data.split("<br>").join(" - ");
								}
							} else if (column === 6) {
								data = '-';
							}
							return data;
						}
					}
				}
			} ],
			"aoColumns" : [
			    { "targets" : 0,
					"searchable" : false,
					"bSortable" : false,
					"data" : null,
					"render" : function(data, type, row) {
						if (
							type === 'display' &&
							SISTEMA_MAP.ICE.descripcion === legajoChequeRechazado.sistemaOrigen &&
							'COBRADO' === data.estadoCobro
						) {
							return '<div><input type="checkbox" id='+data.idChequeRechazadoCobro+' class="checkSelect editor-active" onclick="checking(\'seleccionarTodos\',\'checkSelect\', tablas.tablaCobrosRelacionados, \'btnRevertir\')"/></div>';
						}
						return '';//0
					}
				},
				{	"targets" : 0,
					"searchable" : false,
					"bSortable" : false,
					"data" : null,
					"render" : function(data, type, row) {
						if (type === 'display') {
							if(legajoChequeRechazado.sistemaOrigen == 'Shiva'){
								var idOpCobro = data.idOperacion;
								return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+idOpCobro+')"><i class="icon-eye-open bigger-120"></i></button></div>';
							}
						}
						return null;//1
					}
				},
				{ "targets" : 0,
					"searchable" : false,
					"bSortable" : false,
					"data" : null,
					"render" : function(data, type, row) {
						if (
							type === 'display' &&
							SISTEMA_MAP.SHIVA.descripcion === legajoChequeRechazado.sistemaOrigen &&
//							'DES_DESCOBRADO' !== data.estadoCobro &&
							'DUC' !== data.tipoDocumento
						) {
							var idOpCobro = data.idOperacion;
							return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Revertir" onclick="revertirCobro('+ idOpCobro + ')"><i class="icon-circle-arrow-left bigger-120"></i></button></div>';
						}
						return null;//2
					}
				},
				{
					"mData" : "sistema"//3
				},
				{
					"mData" : "idOperacion"//4
				},
				{
					"mData" : "tipoDocumentoDescripcion"//5
				}, 
				{
					"mData" : "numeroLegal"//6
				},
				{
					"mData" : "numeroReferencia"//7
				},
				{
					"mData" : "convenioFinanciacion"//8
				},
				{
					"mData" : "cuotaFinanciacion"//9
				},
				{
					"mData" : "clienteLegado"//10
				},
				{
					"mData" : "importeTotalDocumento"//11
				},
				{
					"mData" : "importeTotalCheque"//12
				},
				{
					"mData" : "importeTotalEfectivo"//13
				},
				{
					"mData" : "importeTotalRetenciones"//14
				},
				{
					"mData" : "importeTotalBonos"//15
				},
				{
					"mData" : "importeTotalOtrasMonedas"//16
				},
				{
					"mData" : "fechaImputacion"//17
				},
				{
					"mData" : "idOperacionDescobro"
				},
				{
					"mData" : "analista"
				},
				{
					"mData" : "copropietario"
				},
				{
					"mData" : "analistaTeamComercial"
				},
				{
					"mData" : "estadoCobroDescripcion",
					"mRender" : function(data, type, row) {
						if (!isUndefinedNullDashEmptyOrFalse(row.estadoCobroDescripcion)) {
							return row.estadoCobroDescripcion;
						}
						return '-';
					}
				},
				{
					"mData" : "fechaReversion"
				},
				{
					"mData" : "nombreArchivoConReversion"
				},
                {  	   "targets" : 0,
	            	   "searchable" : false,
	            	   "bSortable" : false,
	            	   "data" : null,
	            	   "render" : function(data, type, row) {
	            		   if (type === 'display') {
								if(legajoChequeRechazado.sistemaOrigen == 'Shiva'){
									var idOpCobro = data.idOperacion;
									return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+idOpCobro+')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
						if (
							type === 'display' &&
							SISTEMA_MAP.SHIVA.descripcion === legajoChequeRechazado.sistemaOrigen &&
//							'DES_DESCOBRADO' !== data.estadoCobro
							'DUC' !== data.tipoDocumento
						) {
							var idOpCobro = data.idOperacion;
							return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Revertir" onclick="revertirCobro('+ idOpCobro + ')"><i class="icon-circle-arrow-left bigger-120"></i></button></div>';
						}
						return null;
					}
				}
                ],
    			"fnDrawCallback": function( oSettings ) {
    				if (!$.isEmpty(tablas.tablaCobrosRelacionados) && tablas.tablaCobrosRelacionados.fnGetData().length > 0) {
    					if (isAllCheckedPage('checkSelect', tablas.tablaCobrosRelacionados)) {
    						$("#seleccionarTodos").prop('checked', true);
    					} else {
    						$("#seleccionarTodos").prop('checked', false);
    					}
    				}
    		    },
                'columnDefs' : [ // targets son los th con las class
                                 // date o importe
                                 {
                                	 type : 'latam_date',
                                	 targets : 'date'
                                 }, {
                                	 type : 'comparador-currency',
                                	 targets : 'importe'
                                 } ]
	};

		tablas.tablaCobrosRelacionados = $("#tablaCobrosRelacionados").dataTable(cobrosRelacionados);
		agregarBotonExcel(tablas.tablaCobrosRelacionados.api(),
				"Cobros_Relacionados",
				null, function(data, column, row) {
					
					return data;
				});
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// FIN TAB COBROS RELACIONADOS
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// TAB REEMBOLSOS
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		
		var documentosRelacionados = {
				dom : 'Bfrtip',
				"sScrollX" : true,
				"scrollY" : SCROLL_Y,
				"bScrollCollapse" : true,
				"iDisplayLength" : DISPLAY_LENGTH,
				"iDisplayStart" : 0,
				"order": [[ 0, "asc"], [1, "asc"], [2, "asc"], [ 3, "asc"]],
				"buttons" : [ {
					extend : "excelHtml5",
					text:"Excel",
					title: "Documentos_Relacionados",
					className: 'excelbtn'
				} ],
				"aoColumns" : [
					{
						"mData" : "sistemaOrigenDescripcion"
					},
					{
						"mData" : "tipoDocumento"
					},
					{
						"mData" : "numeroLegal"
					}, 
					{
						"mData" : "numeroReferencia"
					},
					{
						"mData" : "clienteLegado"
					},
					{
						"mData" : "monedaDocumento"
					},
					{
						"mData" : "importeTotalDocumento"
					},
					{
						"mData" : "saldoDocumentoLuegoAplicarCheque"
					},
					{
						"mData" : "importeAplicadoDelCheque"
					},
					{
						"mData" : "saldoActualDocumento"
					},
					{
						"mData" : "fechaConsultaSaldo"
					},
	                ],
	                'columnDefs' : [ // targets son los th con las class
	                                 // date o importe
	                                 {
	                                	 type : 'latam_datetime',
	                                	 targets : 'date-time'
	                                 }, {
	                                	 type : 'comparador-currency',
	                                	 targets : 'importe'
	                                 } ]
		};

			tablas.tablaDocumentosRelacionados = $("#tablaDocumentosRelacionados").dataTable(documentosRelacionados);
			agregarBotonExcel(tablas.tablaDocumentosRelacionados.api(),
					"Documentos_Relacionados",
					null, function(data, column, row) {
						
						return data;
					});
		
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// FIN TAB REEMBOLSOS
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// TAB NOTIFICACION
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
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
			var notificaciones = {
					dom : 'Bfrtip',
					"sScrollX" : true,
					"scrollY" : SCROLL_Y,
					"bScrollCollapse" : true,
					"iDisplayLength" : 10,
					"iDisplayStart" : 0,
					"order": [[ 4, "desc"], [13, "desc"]],
					"buttons" : [ {
						extend : "excelHtml5",
						text:"Excel",
						title: "Notificaciones_historicas",
						className: 'excelbtn',
						exportOptions: {
							columns: ':visible',
							format: {
								body : function ( data, column, row ) {
									if (column === 0 || column === 1 || column === 11 || column === 12) {
										data = '';
									} else if (column === 5) {
										if (!$.isEmpty(data)) {
											data = data.split("<br>").join(" - ");
										}
									} else if (column === 6) {
										if (!$.isEmpty(data)) {
											data = data.split("<br>").join(" - ");
										} else {
											data = '-';
										}
									} else if (column === 10) {
										data = datosTablas.notificaciones[row].usuarioDesc;
									}
									return data;
								}
							}
						}
					} ],
					"aoColumns" : [
						{  	   "targets" : 0,	//0
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (data.tipoContacto === 1 && data.tipoNotificacion === 2) {
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Descargar Carta" onclick="descargarNotificacion('+data.idNotificacion+')"><i class="icon-download-alt bigger-120"></i></button></div>';
			            		   }
			            		   return null;
			            	   }
		                },
		                { "targets" : 0,//1
							"searchable" : false,
							"bSortable" : false,
							"data" : null,
							"render" : function(data, type, row) {
								if (data.tipoNotificacion !== 3 && data.usuarioAlta === userSession && legajoChequeRechazado.edicionTipo !== EDICION_MAP.EDICION_PARCIAL_1.name) {
									return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Eliminar" onclick="eliminarNotificacion('+ data.idNotificacion + ', '+ data.idLegajoChequeRechazado +')"><i class="icon-trash bigger-120"></i></button></div>';
									
								}
								return null;
							}
						},
						
						{
							"mData" : "tipoNotificacionDescripicion" //2
						},
						{
							"mData" : "datosReceptor" //03
						},
						{
							"mData" : "fechaContacto" //4
						}, 
						{
							"mData" : "tipoContactoDescripcion" //5
						},
						{
							"mData" : "datosContacto" //6
						},
						{
							"mData" : "acuseReciboDescripcion" //7
						},
						{
							"mData" : "fechaRecepcion" //8
						},
						{
							"mData" : "observaciones" //9
						},
						{
							"mData" : "usuarioDesc", //10
							   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.usuarioDesc, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
						},
		                {  	   "targets" : 0,	//11
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (data.tipoContacto === 1 && data.tipoNotificacion === 2) {
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Descargar Carta" onclick="descargarNotificacion('+data.idNotificacion+')"><i class="icon-download-alt bigger-120"></i></button></div>';
			            		   }
			            		   return null;
			            	   }
		                },
		                { "targets" : 0,//12
							"searchable" : false,
							"bSortable" : false,
							"data" : null,
							"render" : function(data, type, row) {
								if (data.tipoNotificacion !== 3 && data.usuarioAlta === userSession && legajoChequeRechazado.edicionTipo !== EDICION_MAP.EDICION_PARCIAL_1.name) {
									return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Eliminar" onclick="eliminarNotificacion('+ data.idNotificacion + ', '+ data.idLegajoChequeRechazado +')"><i class="icon-trash bigger-120"></i></button></div>';
								}
								return null;
							}
						},
						{
							"mData" : "idNotificacion", //13
							"visible" : false,
							"searchable" : false
						}
		                ],
		                'columnDefs' : [ // targets son los th con las class
		                                 // date o importe
		                                 {
		                                	 type : 'latam_date',
		                                	 targets : 'date'
		                                 }, {
		                                	 type : 'comparador-currency',
		                                	 targets : 'importe'
		                                 } ]
			};
		
		tablas.tablaNotificaciones = $("#tablaNotificaciones").dataTable(notificaciones);
				agregarBotonExcel(tablas.tablaNotificaciones.api(),
						"Notificaciones_historicas",
						null, function(data, column, row) {
							
							return data;
						});
			
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// FIN TAB NOTIFICACION
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
			
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// TAB COMPROBANTES
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------

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
									|| $('#descripcionComprobante').val().replace(/^\s+|\s+$/g,'').length == 0) {
								$("#errorDescripcionComp").text("Debe ingresar una descripción.");
								$("#errorDescripcionComp").show();
								error = true;
							}

							if (!error) {
								$("#adjComprobanteForm")
										.ajaxForm(
												{
													dataType : 'json',
													data : {
														idLegajo : $('#idLegajo').val(),
														descripcion : $('#descripcionComprobante').val().replace(/^\s+|\s+$/g,'')
													},
													success : function(data) {
														$('.fileupload').fileupload('clear');
														if (data.success) {
															var comp = {
																idComprobante : data.id,
																nombreArchivo : data.fileName,
																descripcionArchivo : data.descripcion
															};
															tablas.comprobantes.fnAddData([ comp ],	true);
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

		tablas.comprobantes = $("#comprobantes").dataTable(
						{
							dom : 'rt',
							"sScrollX" : true,
							"scrollY" : SCROLL_Y,
							"bScrollCollapse" : true,
//							"iDisplayLength" : DISPLAY_LENGTH,
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
									{
										"targets" : -1,
										"data" : null,
										"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button action="eliminar" type="button" class="btn btn-xs btn-link" title="Eliminar comprobante" '+ (legajoChequeRechazado.edicionTipo === EDICION_MAP.EDICION_PARCIAL_1.name ? ' style="display: none;" ': '') +'><i class="icon-trash bigger-120"></i></button></div>',
										"searchable" : false,
										"bSortable" : false
									} ]
						});

		$('#comprobantes tbody').on('click','button',function() {
							var node = $(this).parents('tr')[0];
							var aData = tablas.comprobantes.fnGetData(node);
							
					    	if ($(this).attr('action') == 'eliminar') {
					    	    $('#bloqueEspera').trigger('click');
					    	    $.ajax({
					    		"type" : "POST",
					    		"url": "legajo-cheque-rechazado/eliminarComprobante",
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
								iframe.src = "legajo-cheque-rechazado/descargarComprobante?id=" + aData.idComprobante;
								iframe.style.display = "none";
								document.body.appendChild(iframe);
					    	}
						});
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// FIN TAB COMPROBANTES
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		
		
		registrarEventoSolapaCheques();
		registrarEventoSolapaCobroRelacionado();
		registrarEventoSolapaReembolso();
		registrarEventoSolapaNotificaciones();
		registrarEventoSolapaResumen();
		
//		$('#fechaRechazo').change(function() {
//			if(validarFecha('fechaRechazo',true)){
//				validarFechaIngresadaContraFechaActual('fechaRechazo');
//			}
//		});
//		
//		$('#fechaNotificacionRechazo').change(function() {
//			if(validarFecha('fechaNotificacionRechazo',true)){
//				validarFechaIngresadaContraFechaActual('fechaNotificacionRechazo');
//			}
//		});
//		
//		$('#edFechaDeposito').change(function() {
//			if(validarFecha('edFechaDeposito',true)){
//				validarFechaIngresadaContraFechaActual('edFechaDeposito');
//			}
//		});
//		
//		$('#edFechaVencimiento').change(function() {
//			validarFecha('edFechaVencimiento',true);
//		});
//		
//		
//		$('#fechaAltaDesdeCheque').change(function() {
//			validarFecha('fechaAltaDesdeCheque',false);
//		});
//		
//		$('#fechaAltaHastaCheque').change(function() {
//			validarFecha('fechaAltaHastaCheque',false);
//		});
//		
//		$('#fechaVencimientoCheque').change(function() {
//			validarFecha('fechaVencimientoCheque',false);
//		});
//		
//		$('#nroCheque').change(function() {
//			$("#errorNroCheque").text("");
//			if (!$.validarCriterioBusqueda($('#nroCheque').val())) {
//				$("#errorNroCheque").text("Solo se puede ingresar caracteres numericos y %");
//			}
//		});
//		
//		// Validacion de campos Busqueda
//		$('#edNroCheque').change(function() {
//			$("#errorEdNroCheque").text("");
//			if (!$.validacionIsNumeric($('#edNroCheque').val())) {
//				$("#errorEdNroCheque").text("Solo se puede ingresar caracteres numericos.");
//			}
//		});
//
//		$('#selectUbicacion').change(function() {
//			$("#errorUbicacion").text("");
//			if ( $('#selectUbicacion').val() === '3' ) {
//				$("#errorUbicacion").text('La ubicación "Cliente" se asigna automáticamente al momento de cargar una notificación de entrega de cheque');
//			} else if ( $('#selectUbicacion').val() === '4' ){
//				$("#errorUbicacion").text('La ubicación "Legales" se asigna automáticamente al momento de enviar a legales el legajo');
//			}
//		});
		
	
		
/*****************************************************************************/
/*		Botones de funcionalidad											 */
	$('[id^=btnConfirmar]').click(function() {
		solapa.current = $("#gestion-cheques-rechazados-tabs .current").index();
		solapa.next = null;
		$('#bloqueEspera').trigger('click');
		habilitarBotonConfirmar(false);
		validarLegajo();
	});
	$('[id^=btnGuardar]').click(function() {
		marcarErrorSolapa();
		solapa.current = $("#gestion-cheques-rechazados-tabs .current").index();
		solapa.next = null;
		$('#bloqueEspera').trigger('click');
		$('[id^=btnGuardar]').attr('disabled', true);
		validarLegajo();
	});
	$('[id^=btnCruzErrorGuardadoLegajo]').click(function() {
		$("#errorGuardadoLegajo1").text("");
		$("#alertErrorGuardadoLegajo1").hide();
		$("#errorGuardadoLegajo2").text("");
		$("#alertErrorGuardadoLegajo2").hide();
	});
	
	$('[id^=btnExportar]').click(function() {
		marcarErrorSolapa();
		solapa.current = $("#gestion-cheques-rechazados-tabs .current").index();
		solapa.next = null;
		$('[id^=btnExportar]').attr('disabled', true);
		$('#bloqueEspera').trigger('click');
		exportExcel = true;
		validarLegajo();
		
	});

	$('#edImporteCheque').focusout( function() {
			
		$("#errorEdImporteCheque").text("");
		
		if (!validarImporte(this.id)) {
			$("#errorEdImporteCheque").text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		} else if (!$.isEmpty(this.value) && (this.value * 1) == 0 ) {
			$("#errorEdImporteCheque").text("Este campo debe ser mayor a 0.");
		}else {
			$('#edImporteCheque').val(formatear(importeToFloat(this.value)));
		}
	});
	if (!isUndefinedNullDashEmptyOrFalse(edicion) && !isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado)) {
		$('[id^=btnExportar]').show();
		cargarDatosLegajo(legajoChequeRechazado);
		tablas.tablaCobrosRelacionados.fnClearTable();
		$('#seleccionarTodos').attr('disabled',true);
		if (!$.isEmpty(legajoChequeRechazado.listaCobrosRelacionados)) {
			tablas.tablaCobrosRelacionados.fnAddData(legajoChequeRechazado.listaCobrosRelacionados, true);
			$('#seleccionarTodos').attr('disabled',false);
		}
		if (!$.isEmpty(legajoChequeRechazado.listaNotificaciones)) {
			datosTablas.notificaciones = legajoChequeRechazado.listaNotificaciones;
		}
		/* Lista de Comprobantes */
		if(!$.isEmpty(legajoChequeRechazado.listaComprobantes)){
			var size = legajoChequeRechazado.listaComprobantes.length;
			for (var i = 0; i < size; i++) {
				datosTablas.comprobantes.push(legajoChequeRechazado.listaComprobantes[i]);
			}
			tablas.comprobantes.fnAddData(datosTablas.comprobantes, true);
		}
		
		/* observaciones historicas */
		if (!isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado.observacionesAnteriores)) {
			$('#prevObservTextAnterior').val(legajoChequeRechazado.observacionesAnteriores);
			$('#divObservacionesAnterior').show();
		}
		
	}
	
	if (!$.isEmpty(SHOW_BUTTON)) {
		$('button[id^=btnVolver]').show();
	}
	
	$('[id^=btnVolver]').click(function() {
		volverBusqueda();
	});
	
	if(!$.isEmpty($("#solapa").val())){
		cambiarSolapa($("#solapa").val());
	}
	
});


function limpiarInputsObligatorios(){
	$("#errorEmpresa").text("");
	$("#errorSegmento").text("");
	$("#errorMotivo").text("");
	$("#errorfechaRechazo").text("");
	$("#errorfechaNotificacionRechazo").text("");
	$("#errorUbicacion").text("");
	$("#errorMotivoRechazo").text("");
	$("#errorObservaciones").text("");
	limpiarErroresEdicionMaunial();
} 
function limpiarErroresEdicionMaunial() {
	$("#errorEdCodigoBancoOrigen").text("");
	$("#errorEdNroCheque").text("");
	$("#erroredFechaDeposito").text("");
	$("#errorEdMonedaCheque").text("");
	$("#errorEdImporteCheque").text("");
	$("#erroredAcuerdoCheque").text("");
	$("#errorEdTipoCheque").text("");
	$("#erroredCuentaDeposito").text("");
	$("#errorEdDescripcionBancoOrigen").text("");
//	$("#errorEdInputCodCliente").text("");
//	$("#errorEdInputCodCliente2").text("");
}

function validarCamposLegajo() {
	var tabChequesOk = true;
	limpiarInputsObligatorios();
	$("#errorGuardadoLegajo1").text("");
	$("#alertErrorGuardadoLegajo1").hide();
	$("#errorGuardadoLegajo2").text("");
	$("#alertErrorGuardadoLegajo2").hide();
	
	if ($.isEmpty($('#selectEmpresa').val())) {
		$("#errorEmpresa").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	if ($.isEmpty($('#selectSegmento').val())) {
		$("#errorSegmento").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	if ($.isEmpty($('#motivoRechazo').val())) {
		$("#errorMotivoRechazo").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	
	
	
	if (isUndefinedNullDashEmptyOrFalse(edicion) && isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado)) {
		if ($.isEmpty($('#selectUbicacion').val())) {
			$("#errorUbicacion").text("Este campo es requerido.");
			tabChequesOk = false;
		} else if ( $('#selectUbicacion').val() === '3' ) { // TODO LO SACO PARA PRUEBAS
			$("#errorUbicacion").text('La ubicación "Cliente" se asigna automáticamente al momento de cargar una notificación de entrega de cheque');
			tabChequesOk = false;
		} else if ( $('#selectUbicacion').val() === '4' && (legajoChequeRechazado.estado !== LGJ_ENVIADO_A_LEGALES && legajoChequeRechazado.estado !== LGJ_DESISTIDO)) {
			$("#errorUbicacion").text('La ubicación "Legales" se asigna automáticamente al momento de enviar a legales el legajo');
			tabChequesOk = false;
		}
	} else if (
		EDICION_MAP.EDICION_PARCIAL_2.name === legajoChequeRechazado.edicionTipo ||
		EDICION_MAP.EDICION_PARCIAL_6.name === legajoChequeRechazado.edicionTipo ||
		EDICION_MAP.EDICION_PARCIAL_4_1.name === legajoChequeRechazado.edicionTipo ||
		EDICION_MAP.EDICION_PARCIAL_5_1.name === legajoChequeRechazado.edicionTipo
	) {
		if ($.isEmpty($('#selectUbicacion').val())) {
			$("#errorUbicacion").text("Este campo es requerido.");
			tabChequesOk = false;
		} else if ( $('#selectUbicacion').val() === '3' ) { // TODO LO SACO PARA PRUEBAS
			$("#errorUbicacion").text('La ubicación "Cliente" se asigna automáticamente al momento de cargar una notificación de entrega de cheque');
			tabChequesOk = false;
		} else if ( $('#selectUbicacion').val() === '4' && (legajoChequeRechazado.estado !== LGJ_ENVIADO_A_LEGALES && legajoChequeRechazado.estado !== LGJ_DESISTIDO)) {
			$("#errorUbicacion").text('La ubicación "Legales" se asigna automáticamente al momento de enviar a legales el legajo');
			tabChequesOk = false;
		}
	}
	
	if (!$.isEmpty($('#prevObservText').val())) {
		var prevObservText = $('#prevObservText').val();
		
		prevObservText = $.trim(prevObservText);
		
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#errorObservaciones").text("Este campo debe contener un formato de texto correcto.");
			tabChequesOk = false;
		} else {
			$("#errorObservaciones").text("");
		}
	}
	
	if (!validarFecha('fechaRechazo',true) || !validarFechaIngresadaContraFechaActual('fechaRechazo')){
		tabChequesOk=false;
	}
	
	if (!validarFecha('fechaNotificacionRechazo',true) || !validarFechaIngresadaContraFechaActual('fechaNotificacionRechazo')){
		tabChequesOk=false;
	}
	if (!validarFecha('edFechaVencimiento',false)){ 
		tabChequesOk = false;
	}
	
	if (!$.isEmpty($('#edsistemaOrigen').val())){
		if ($('#edsistemaOrigen').val() === 'Usuario') {
			
//			$('#spanRazonSocial').css('display','inline-block');
			
			if ($.isEmpty($('#edTipoCheque').val())){
				$("#erroredTipoCheque").text("Este campo es requerido.");
				tabChequesOk = false;
			} else if ($('#edTipoCheque').val() == 6){
				if (!validarFecha('edFechaVencimiento',true)){
					tabChequesOk=false;
				}
			}
			
			
			if ($.isEmpty($('#edInputCodCliente').val())){
				if($.isEmpty($('#edRazonSocialClienteLegado').val())){
					//$("#errorEdInputCodCliente").text("Este campo es requerido.");
					$("#errorEdInputCodCliente2").text("Este campo es requerido.");
					tabChequesOk = false;
				}
			}
		}
	} else {
		$("#errorEdsistemaOrigen").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	
	if ($.isEmpty($('#edCodigoBancoOrigen').val())){ 
		$("#errorEdCodigoBancoOrigen").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	if ($.isEmpty($('#edNroCheque').val())){ 
		$("#errorEdNroCheque").text("Este campo es requerido.");
		tabChequesOk = false;
	}else if (!$.validacionIsNumeric($('#edNroCheque').val())) {
		$("#errorEdNroCheque").text("Solo se puede ingresar caracteres numericos.");
		tabChequesOk = false;
	}
	if ($.isEmpty($('#edFechaEmision').val())){ 
		$("#erroredFechaEmision").text("Este campo es requerido.");
		tabChequesOk = false;
	}else if(!validarFecha('edFechaEmision',true) || !validarFechaEmisionContraFechaDeposito() || !validarFechaIngresadaContraFechaActual('edFechaEmision')){
		tabChequesOk = false;
	}
	if ($.isEmpty($('#edFechaDeposito').val())){ 
		$("#erroredFechaDeposito").text("Este campo es requerido.");
		tabChequesOk = false;
	}else if(!validarFecha('edFechaDeposito',true) || !validarFechaEmisionContraFechaDeposito() || !validarFechaIngresadaContraFechaActual('edFechaDeposito')){
		tabChequesOk = false;
	}
	if ($.isEmpty($('#edMonedaCheque').val())){ 
		$("#errorEdMonedaCheque").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	if ($.isEmpty($('#edImporteCheque').val())){ 
		$("#errorEdImporteCheque").text("Este campo es requerido.");
		tabChequesOk = false;
	} else if (!validarImporte('edImporteCheque') ) {
		$("#errorEdImporteCheque").text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
		tabChequesOk = false;
	} else if (($('#edImporteCheque').val() * 1) == 0 ) {
		$("#errorEdImporteCheque").text("Este campo debe ser mayor a 0.");
		tabChequesOk = false;
	}
//	if ($.isEmpty($('#edAcuerdoCheque').val())){ 
//		$("#erroredAcuerdoCheque").text("Este campo es requerido.");
//		tabChequesOk = false;
//	}
	if ($.isEmpty($('#edTipoCheque').val())){ 
		$("#errorEdTipoCheque").text("Este campo es requerido.");
		tabChequesOk = false;
	}
//	if ($.isEmpty($('#edCuentaDeposito').val())){ 
//		$("#erroredCuentaDeposito").text("Este campo es requerido.");
//		tabChequesOk = false;
//	}
	if ($.isEmpty($('#edDescripcionBancoOrigen').val())){ 
		$("#errorEdDescripcionBancoOrigen").text("Este campo es requerido.");
		tabChequesOk = false;
	}
	
	if (!$.isEmpty($('#edInputCodCliente').val()) && !$.isEmpty($('#errorEdInputCodCliente').val())) {
		tabChequesOk = false;
	}

	if (!$.isEmpty($('#edInputCodCliente').val()) && $.isEmpty($('#edRazonSocialClienteLegado').val())) {
		tabChequesOk = false;
		$('#errorEdInputCodCliente').text('El cliente no fue validado contra el servicio de Consulta de Clientes.');
	}

//	if($.isEmpty($('#edRazonSocialClienteLegado').val())){
//		//$("#errorEdInputCodCliente").text("Este campo es requerido.");
//		$("#errorEdInputCodCliente2").text("Este campo es requerido.");
//		tabChequesOk = false;
//	}
	return tabChequesOk;
}

function validarFecha(fechaNombre,esObligatorio) {
	var fechaInputError= $("#error" + fechaNombre);
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

function validarImporte (nombreCampoImporte) {
	
	var importeError = $("#error" + nombreCampoImporte);
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


function validarLegajo() {
	var legajo = {
		idAnalista : $('#idAnalista').val(),
		analista : $('#analista').val(),
		idCopropietario : $('#selectCopropietario').val(),
		copropietario : $('#selectCopropietario option:selected').text(),
		empresa : $('#selectEmpresa option:selected').text(),
		idEmpresa : $('#selectEmpresa').val(),
		segmento : $('#selectSegmento option:selected').text(),
		idSegmento : $('#selectSegmento').val(),
		fechaNotificacion : $('#fechaNotificacionRechazo').val(),
		motivoLegajo : $('#motivoRechazo').val(),
		motivoLegajoDescripcion : $('#motivoRechazo option:selected').text(),
		observaciones : $('#prevObservText').val(),
		ubicacion : $('#selectUbicacion').val(),
		ubicacionDesc : $('#selectUbicacion option:selected').text(),
		fechaRechazo : $('#fechaRechazo').val(),
		idBancoOrigen : $('#edCodigoBancoOrigen').val(),
		numeroCheque : $('#edNroCheque').val(),
		fechaVencimiento : $('#edFechaVencimiento').val(),
		moneda : $('#edMonedaCheque').val(),
		importe : $('#edImporteCheque').val(),
		idAcuerdo : $('#edAcuerdoCheque').val(),
		idbancoDeposito : $('#edBancoDeposito').val(),
		sistemaOrigen : $('#edsistemaOrigen').val(),
		tipoCheque : $('#edTipoCheque').val(),
		cuentaDeposito : $('#edCuentaDeposito').val(),
		fechaDeposito : $('#edFechaDeposito').val(),
		fechaEmision : $('#edFechaEmision').val(),
		idCliente : $('#edInputCodCliente').val(),
		descripcionCliente : $('#edRazonSocialClienteLegado').val(),
		cuitCliente: !$.isEmpty(clienteSieble)? clienteSieble.cuit : ''
	};
	var salida = false;
	if (validarCamposLegajo()) {
		if ($('#edsistemaOrigen').val() !== 'Usuario') {
			legajo.chequeRechazado = datosTablas.tablaChequesSeleccionados[0];
		}

		if (!isUndefinedNullDashEmptyOrFalse(edicion) && !isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado)) {
			legajo.idSolapaDesde = solapa.current;
			legajo.idSolapaHasta = solapa.next;
			modificarLegajo(legajo);
		} else {
			primeraVes = true;
			guardarLegajo(legajo);
		}
		salida = true;
	} else {
		$("#errorGuardadoLegajo1").text("Error al guardar. Revise los errores.");
		$("#alertErrorGuardadoLegajo1").show();
		$("#errorGuardadoLegajo2").text("Error al guardar. Revise los errores.");
		$("#alertErrorGuardadoLegajo2").show();

		if (!isUndefinedNullDashEmptyOrFalse(edicion) && !isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado)) {
			$('[id^=btnGuardar]').attr('disabled', false);
			marcarErrorSolapa([solapa.current]);
		} else {
			habilitarBotonConfirmar(true);
			marcarErrorSolapa([solapa.CHEQUE]);
		}
		salida = false;
		ocultarBloqueEspera();
	}
	return salida;
}

function habilitarBotonConfirmar(habilitar) {
	
	if (habilitar){
		$('[id^=btnConfirmar]').prop('disabled', false);
	} else {
		$('[id^=btnConfirmar]').prop('disabled', true);
	}
	
}
function modificarLegajo(legajo) {
	legajo.idLegajoChequeRechazado = $('#idLegajo').val();
	legajo.idWorkflow = legajoChequeRechazado.idWorkflow;
	legajo.estado = legajoChequeRechazado.estado;
	$.ajax({
		"type" : "POST",
		"url" : "legajo-cheque-rechazado/modificar-legajo",
		"dataType" : "json",
		"data" : JSON.stringify(eval(legajo)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				
				if(exportExcel){
					exportExcel = false;
					$('#idLeg').val(legajo.idLegajoChequeRechazado);
					$('[id^=btnExportar]').attr('disabled', false);
					beforeUnload.off();
					$('#formLegajo')[0].action = urlAsynchronousHTTP + "/legajo-cheque-rechazado/exportacionDetalleLegajo";
					$('#formLegajo')[0].submit();
					setTimeout(function() { beforeUnload.on(); }, 500);
				}
				if (legajo.idSolapaDesde === solapa.CHEQUE) {
					legajoChequeRechazado.idCopropietario = $('#selectCopropietario').val();
					legajoChequeRechazado.copropietario = $('#selectCopropietario option:selected').text();
					legajoChequeRechazado.ubicacion = $('#selectUbicacion').val();
					legajoChequeRechazado.ubicacionDesc = $('#selectUbicacion option:selected').text();
				}
				if (legajo.idSolapaHasta === solapa.COBRO) {
					tablas.tablaCobrosRelacionados.fnClearTable();
					$('#seleccionarTodos').attr('disabled',true);
					if (!$.isEmpty(result.listaCobrosRelacionados)) {
						tablas.tablaCobrosRelacionados.fnAddData(result.listaCobrosRelacionados, true);
						$('#seleccionarTodos').attr('disabled',false);
					}
					
				} else if (legajo.idSolapaHasta === solapa.REEMBOLSO) {
					tablas.tablaDocumentosRelacionados.fnClearTable();
					if (!$.isEmpty(result.listaChequeDocumentos)) {
						tablas.tablaDocumentosRelacionados.fnAddData(result.listaChequeDocumentos, true);
					}
				} else if (legajo.idSolapaHasta === solapa.NOTIFICACION){
//					tablas.tablaNotificaciones.fnClearTable();
//					if (!$.isEmpty(result.listaLegajoChequeRechazadoNotificacionDto)) {
//						tablas.tablaNotificaciones.fnAddData(result.listaLegajoChequeRechazadoNotificacionDto, true);
//					}
				} else if (legajo.idSolapaHasta === solapa.RESUMEN){
					
					tablas.tablaChequesPrev.fnClearTable();
					tablas.tablaCobrosRelacionadosPrev.fnClearTable();
					tablas.tablaDocumentosRelacionadosPrev.fnClearTable();
					tablas.comprobantesPrev.fnClearTable();
					
					cargarDatosResumen(legajoChequeRechazado);
					
					if (!$.isEmpty(datosTablas.tablaChequesSeleccionados)) {
						tablas.tablaChequesPrev.fnAddData(datosTablas.tablaChequesSeleccionados, true);
					}
					
					if (!$.isEmpty(datosTablas.comprobantes)) {
						tablas.comprobantesPrev.fnAddData(datosTablas.comprobantes, true);
					}
					
					if (!$.isEmpty(result.listaCobrosRelacionados)) {
						tablas.tablaCobrosRelacionadosPrev.fnAddData(result.listaCobrosRelacionados, true);
					}
					
					if (!$.isEmpty(result.listaChequeDocumentos)) {
						tablas.tablaDocumentosRelacionadosPrev.fnAddData(result.listaChequeDocumentos, true);
					}

//					En caso de fallas con esta grilla, borrar esto, y dejar solo la carga de la grilla por el "draw" pero tener en cuenta que al exportar excel se desacomoda "un poquito" la grilla
					tablas.tablaNotificacionesPrev.fnClearTable();
					if (!$.isEmpty(datosTablas.notificaciones)) {
						tablas.tablaNotificacionesPrev.fnAddData(datosTablas.notificaciones, true);
					}
				}
				legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;
				completarMonto(
					result.montos.montoAReenvolsar,
					result.montos.montoARevertir
				);
				if (!$.isEmpty(legajo.idSolapaHasta)) {
					draw[legajo.idSolapaHasta]();
					clear[legajo.idSolapaDesde]();
				}
				
			} else {
				mostrarErrorGeneral(result.descripcionError);
				marcarErrorSolapa([solapa.currrent]);
			}
			$('[id^=btnGuardar]').attr('disabled', false);
			ocultarBloqueEspera();
		}
	});
}
// Mas que guardar es crear!!1 u578936
function guardarLegajo(legajo) {
	if (primeraVes) {
		if ($('#edsistemaOrigen').val() == 'Shiva') {
			legajo.validarAnulacionInmediataDelValor = true;
		} else {
			legajo.validarAnulacionInmediataDelValor = false;
		}
	}
	primeraVes = false;
	$.ajax({
		"type" : "POST",
		"url" : "legajo-cheques-rechazados-crear",
		"dataType" : "json",
		"data" : JSON.stringify(eval(legajo)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			var salir = false;
			if (result.success) {
				marcarErrorSolapa();
				legajoChequeRechazado = result.resultado;
				habilitoEdicion(result.resultado);
				$('[id^=btnExportar]').show();
				salir = true;
				edicion = true;
				$(".title").text(titleEdicion);
				document.title = titleEdicion;
			} else if( result.mostrarPopUpLegajo ) {
				var mensaje = 'La confirmación del alta de este legajo de cheque rechazado procederá a anular el cheque, ¿desea continuar?';
				bootbox.confirm(mensaje, function(result) {
					if (result) {
						legajo.validarAnulacionInmediataDelValor = false;
						guardarLegajo(legajo);
					} else {
						ocultarBloqueEspera();
						marcarErrorSolapa([solapa.CHEQUE]);
						grisarSolapas();
						habilitarBotonConfirmar(true);
						salir = true;
					}
				});
			} else {
				mostrarErrorGeneral(result.descripcionError);
				salir = true;
			}
			if (salir) {
				ocultarBloqueEspera();
				habilitarBotonConfirmar(true);
			}
		}
	});
}
function cargoEstadoId(legajo) {
	$('#estadoLegajo').val(legajo.estadoDescripcion);
	$('#idLegajo').val(legajo.idLegajoChequeRechazado);
}
function habilitoEdicion(legajo) {
	cargoEstadoId(legajo);
	$('[id^=btnConfirmar]').hide();
	$('[id^=btnGuardar]').show();
	$('#searchCriterioCheque').hide();
	
	
	alta = false;
	
	$('#divDatosLegajo :input').attr('disabled', true);
	$('#divDatosLegajo :button').attr('disabled', true);
	
	

	// Tengo que habilitar
	$('#divEdicionManual :input').attr('disabled', true);
	$('#divEdicionManual :button').attr('disabled', true);
	
	$('#edDescripcionBancoOrigen').combobox2("destroy");
	$('#edBancoDeposito').combobox2("destroy");

	
	//------------------------------
	solapa.validar(solapa.CHEQUE, legajo);
	
	//-----------------
	if (!$.isEmpty(datosTablas.tablaChequesSeleccionados)) {
		var sizeL = datosTablas.tablaChequesSeleccionados.length;
		for (var i = 0; i < sizeL; i++) {
			$('.' + BTN_NO_CLASS +  datosTablas.tablaChequesSeleccionados[i].idPantalla).attr('disabled', true).html(HTML_ICON_BLANK);
		}
	}
	grisarSolapas();
	desGrisarSolapas();
	
	//$('[id^=btnGuardar]').attr('disabled', true);
}


function mostrarErrorGeneral(text, posicion) {
	if (posicion === undefined) {
		$('[id^=errorGuardadoLegajo]').text(text);
		$("[id^=alertErrorGuardadoLegajo]").show();
	}
}
function grisarSolapas() {
	for (var i = 1; i < solapa.size; i++) {
		$('#gestion-cheques-rechazados-tabs-t-' + i)
			.css({"color": "#eee"})
			.addClass("step-disabled-li-a")
			.parent().css({"border-color" : "#fff #fff #bbb"});
	}
};

function desGrisarSolapas() {
	for (var i = 1; i < solapa.size; i++) {
		if ( !( SISTEMA_MAP.USUARIO.descripcion == legajoChequeRechazado.sistemaOrigen && i === solapa.COBRO ) ) {
			$('#gestion-cheques-rechazados-tabs-t-' + i)
				.css({"color": "#5f5f5f"})
				.removeClass("step-disabled-li-a")
				.removeAttr('style')
				.parent().css({"border-color" : "rgb(187, 187, 187) rgb(187, 187, 187) rgb(120, 120, 120)"});
		}
	}
}

// solapas es un vector con los indices que contendran error
function marcarErrorSolapa(solapas) {
	if (solapas === undefined) {
		solapas = [];
	}
	for (var i = 0; i < solapa.size; i++) {
		if (solapas.indexOf(i) >= 0) {
			$('#gestion-cheques-rechazados-tabs-t-' + i).css({"color": "red", "font-weight" : "bold"});
		} else {
			if (!$('#gestion-cheques-rechazados-tabs-t-' + i).hasClass("step-disabled-li-a")) {
				$('#gestion-cheques-rechazados-tabs-t-' + i).css({"color": "#5f5f5f", "font-weight" : "normal"});
			}
		}
	}
}

//Brian
function volverBusqueda() {
	beforeUnload.off();
	$('#bloqueEspera').trigger('click');
	$('#goBack').val("true");
	if(!$.isEmpty($('#idVolver').val())){
		$('#formLegajo')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
		$('#formLegajo')[0].submit();
	}else {
		$('#formLegajo')[0].action=urlAsynchronousHTTP + '/regresar-bandeja-de-entrada';
		$('#formLegajo')[0].submit();
	}
};

function validarFechaIngresadaContraFechaActual(nombreFecha) {
	
	var fechaError = $("#error" + nombreFecha);
//	fechaError.text("");
	var fecha = $("#" + nombreFecha).val().split("/");
	var salida = true;
	var fechaActual = new Date();
	var fechaIngresada= new Date(fecha[2], fecha[1] - 1, fecha[0]);
	
	if (fechaActual < fechaIngresada) {
		fechaError.text("La fecha ingresada no puede ser mayor a la fecha actual");
		salida = false;
	}
	return salida;
};

// TODO Meter Todo en Archivo. Y sacarlos jquery.funciones.cobros
function returnSignoMoneda(moneda) {
	var signo = '';
	if (moneda == 'Pesos') {
		signo = '$';
	} else if (moneda == 'Dolares') {
		signo = 'U$S';
	} else if (moneda == 'Euros') {
		signo = '€';
	} else if (moneda == '$') {
		signo = moneda;
	} else if (moneda == 'U$S') {
		signo = moneda;
	} else if (moneda == '€') {
		signo = moneda;
	} else if (moneda == 'PES') {
		signo ='$';
	} else if (moneda == 'DOL') {
		signo ='U$S';
	} else if (moneda == 'EUR') {
		signo ='€';
}
	return signo;
};

//Formateo de numerico a "999.999.999,99"
function formatear(importe) {
	if (/-$/.test(importe)) {
		return 0;
	}
	var decimal = importe.toFixed(2).toString().split('.');
	var regex = /(\d+)(\d{3})/;
	while (regex.test(decimal[0])) {
		decimal[0] = decimal[0].replace(regex, '$1' + '.' + '$2');
	}
	decimal = truncoADosDecimales(decimal);
	return decimal.join(',');
}
function desformatear(importe) {
	return parseFloat(importe.split('.').join('').split(',').join('.'));
};
//trunco un importe con mas de 2 decimales (No rendondeo)
//1.606,2500 a 1.606,25
function truncoADosDecimalesImporte(importe, moneda) {
	if (importe === '-') {
		return importe;
	}
	var numeroDescompuesto = importe.split(',');
	if (numeroDescompuesto.length == 2) {
		if (numeroDescompuesto[1].length > 2) {
			numeroDescompuesto[1] = numeroDescompuesto[1].substring(0,2);
		}
		importe = numeroDescompuesto.join(',');
	}
	formatear(importeToFloat(importe));
	return ((moneda != null) ? returnSignoMoneda(moneda) : '')
			+ formatear(importeToFloat(importe));
}
function truncoADosDecimales(vector) {
	if (vector.length > 1) {
		if (vector[1].length < 2) {
			vector[1] += "0";
		} else if (vector[1].length > 2) {
			vector[1] = vector[1].substring(0, 2);
		}
	} else {
		vector[1] = "00";
	}
	return vector;
}
function mostrarSegunEstado(legajo) {
	var edicion = false;
	if ($.isEmpty(legajo)) {
		edicion = true;
	} else if ('LGJ_ABIERTO' === legajo.estado) {
		edicion = false;
	}
	return edicion;
};

function validarCamposNotificaciones(){
	if (!$.isEmpty($('#prevObservacionesNotificacion').val())) {
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#prevObservacionesNotificacion").text("Este campo debe contener un formato de texto correcto.");
			tabChequesOk = false;
		} else {
			$("#errorprevObservacionesNotificacion").text("");
		}
	}
	
	if (!$.isEmpty($('#prevDatosContacto').val())) {
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#prevDatosContacto").text("Este campo debe contener un formato de texto correcto.");
			tabChequesOk = false;
		} else {
			$("#errorprevDatosContacto").text("");
		}
	}
}

//function exportarDetalle() {
//	
//	$('#bloqueEspera').trigger('click');
//	
//	$('#formLegajo')[0].action = urlAsynchronousHTTP + "/legajo-cheque-rechazado/exportacionDetalleLegajo";
//    $('#formLegajo')[0].submit();
//    
//    setTimeout(function() {
//		$('#btnExportar').attr('disabled', false);
//		$('#btnExportarPie').attr('disabled', false);
//		beforeUnload.on();
//		ocultarBloqueEspera();
//    }, 3000);
//    
//}
