var SCROLL_Y = '300px';
var DISPLAY_LENGTH = 100;
var CHEQUE_DIFERIDO = 6;


$(document).ready(function() {
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
			"aoColumns" : [ // 1
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
	
	var cobrosRelacionados = {
			dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : DISPLAY_LENGTH,
			"iDisplayStart" : 0,
			"order": [[ 15, "desc"]],
			"buttons" : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Cobros_Relacionados",
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
			"aoColumns" : [ 
							  
			               	{	"targets" : 0,//0
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
							{
								"mData" : "sistema"//1
							},
							{
								"mData" : "idOperacion"//2
							},
							{
								"mData" : "tipoDocumentoDescripcion"//3
							}, 
							{
								"mData" : "numeroLegal"//4
							},
							{
								"mData" : "numeroReferencia"//5
							},
							{
								"mData" : "convenioFinanciacion"//6
							},
							{
								"mData" : "cuotaFinanciacion"//7
							},
							{
								"mData" : "clienteLegado"//8
							},
							{
								"mData" : "importeTotalDocumento"//9
							},
							{
								"mData" : "importeTotalCheque"//10
							},
							{
								"mData" : "importeTotalEfectivo"//11
							},
							{
								"mData" : "importeTotalRetenciones"//12
							},
							{
								"mData" : "importeTotalBonos"//13
							},
							{
								"mData" : "importeTotalOtrasMonedas"//14
							},
							{
								"mData" : "fechaImputacion"//16
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
									return null;
								}
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
	
	var tabla = $("#tablaChequeSeleccionadoResumen").dataTable(chequesSeleccionadosSettings);
	agregarBotonExcel(tabla.api(),
			"Cheque_seleccionado",
			null,
			function(data, column, row) {
				
				return data;
			});

	if (!$.isEmpty(legajoChequeRechazado.chequeRechazado) && legajoChequeRechazado.sistemaOrigen !== 'Usuario') {
		tabla.fnAddData(legajoChequeRechazado.chequeRechazado, true);
	}
	var tablaCobrosRelacionados = $("#tablaCobrosRelacionados").dataTable(cobrosRelacionados);
	agregarBotonExcel(tablaCobrosRelacionados.api(),
			"Cobros_Relacionados",
			null, function(data, column, row) {
				
				return data;
			});
	if (!$.isEmpty(legajoChequeRechazado.listaCobrosRelacionados)) {
		tablaCobrosRelacionados.fnAddData(legajoChequeRechazado.listaCobrosRelacionados, true);
	}

	
	
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

	var tablaDocumentosRelacionados = $("#tablaDocumentosRelacionados").dataTable(documentosRelacionados);
	agregarBotonExcel(tablaDocumentosRelacionados.api(),
			"Documentos_Relacionados",
			null, function(data, column, row) {
				
				return data;
			});
	if (!$.isEmpty(legajoChequeRechazado.listaDetalleDocumentos)) {
		tablaDocumentosRelacionados.fnAddData(legajoChequeRechazado.listaDetalleDocumentos, true);
	}
	


	//---------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------
	// TAB NOTIFICACIONES
	//---------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------
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
			"order": [[ 3, "desc"], [ 11, "desc"]],
			"buttons" : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Notificaciones",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
						body : function ( data, column, row ) {
							if (column === 0 || column === 10) {
								data = '';
							} else if (column === 4) {
								if (!$.isEmpty(data)) {
									data = data.split("<br>").join(" - ");
								}
							} else if (column === 5) {
								if (!$.isEmpty(data)) {
									data = data.split("<br>").join(" - ");
								}else{ 
									data = '-';
								}
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
				{
					"mData" : "tipoNotificacionDescripicion" //1
				},
				{
					"mData" : "datosReceptor" //2
				},
				{
					"mData" : "fechaContacto" //3
				}, 
				{
					"mData" : "tipoContactoDescripcion" //4
				},
				{
					"mData" : "datosContacto" //5
				},
				{
					"mData" : "acuseReciboDescripcion" //6
				},
				{
					"mData" : "fechaRecepcion" //7
				},
				{
					"mData" : "observaciones" //8
				},
				{
					"mData" : "usuarioDesc", //9
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
				{  	   "targets" : 0,	//10
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
				{
					"mData" : "idNotificacion", //11
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

	var tablaNotificaciones = $("#tablaNotificacionesDetalle").dataTable(notificaciones);
		agregarBotonExcel(tablaNotificaciones.api(),
				"Notificaciones",
				null, function(data, column, row) {
					
					return data;
				});
		
		if (!$.isEmpty(legajoChequeRechazado.listaNotificaciones)) {
			tablaNotificaciones.fnAddData(legajoChequeRechazado.listaNotificaciones, true);
		}
	//---------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------
	// FIN TAB NOTIFICACIONES
	//---------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------

	
	var comprobantes = {
			dom : 'rt',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
//			"iDisplayLength" : DISPLAY_LENGTH,
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
						"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div>',
						"searchable" : false,
						"bSortable" : false
					} ]
		};
	
	var tablaComprobantes = $("#comprobantes").dataTable(comprobantes);
	agregarBotonExcel(tablaComprobantes.api(),
			"Comprobantes",
			null, function(data, column, row) {
				
				return data;
			});
	if (!$.isEmpty(legajoChequeRechazado.listaComprobantes)) {
		tablaComprobantes.fnAddData(legajoChequeRechazado.listaComprobantes, true);
	}
	$('#comprobantes tbody').on('click','button',function() {
		var node = $(this).parents('tr')[0];
		var aData = tablaComprobantes.fnGetData(node);
		var iframe = document.createElement("iframe");
		iframe.src = "legajo-cheque-rechazado/descargarComprobante?id=" + aData.idComprobante;
		iframe.style.display = "none";
		document.body.appendChild(iframe);
		
	});
	
	$("#idDivClienteSiebel").css('display','inline-block');
	
	if($('#tipoCheque').val() == CHEQUE_DIFERIDO){
		$('#verFechaVencimiento').show();
	}
});

function detalleCobro(idOperacion) {
	$('#bloqueEspera').trigger('click');

	$('#idOperacionRelacionada').val(idOperacion);
	
	$('#formResumen')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion";
	$('#formResumen')[0].submit();
};

function descargarNotificacion(idNotificacion) {
	$('#formNotificacion')[0].action=urlAsynchronousHTTP+"/generarPdfCartaLegajo";
	$('#idNotificacion').val(idNotificacion);
	$('#formNotificacion')[0].submit();
};


function volverBusqueda() {
	$('#bloqueEspera').trigger('click');
	$('#goBack').val("true");
	$('#formResumen')[0].action = urlAsynchronousHTTP + $('#idVolver').val();
	$('#formResumen')[0].submit();
};

function exportarDetalle() {
	$('#idLeg').val(legajoChequeRechazado.idLegajoChequeRechazado);
	$('[id^=btnExportar]').attr('disabled', false);
	beforeUnload.off();
	$('#formLegajo')[0].action = urlAsynchronousHTTP + "/legajo-cheque-rechazado/exportacionDetalleLegajo";
	$('#formLegajo')[0].submit();
	setTimeout(function() { beforeUnload.on(); }, 500);
};