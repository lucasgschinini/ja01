
var registrarEventoSolapaResumen = function() {
	
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	//  TAB RESUMEN
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------




	var chequesSeleccionadosPrevSettings = {
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
	
	tablas.tablaChequesPrev = $("#tablaChequesPrev").dataTable(chequesSeleccionadosPrevSettings);
	

	
	
	
	var cobrosRelacionadosPrevSettings = {
			dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : DISPLAY_LENGTH,
			"iDisplayStart" : 0,
			"order": [[ 14, "desc"]],
			"buttons" : [ {
				extend : "excelHtml5",
				text:"Excel",
				title: "Cobros_Relacionados",
				className: 'excelbtn',
				exportOptions: {
					columns: ':visible',
					format: {
						body : function ( data, column, row ) {
							if (column === 5) {
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
			               {
			            	   "mData" : "sistema"//0
			               },
			               {
			            	   "mData" : "idOperacion"//1
			               },
			               {
			            	   "mData" : "tipoDocumentoDescripcion"//2
			               }, 
			               {
			            	   "mData" : "numeroLegal"//3
			               },
			               {
			            	   "mData" : "numeroReferencia"//4
			               },
			               {
			            	   "mData" : "convenioFinanciacion"//5
			               },
			               {
			            	   "mData" : "cuotaFinanciacion"//6
			               },
			               {
			            	   "mData" : "clienteLegado"//7
			               },
			               {
			            	   "mData" : "importeTotalDocumento"//8
			               },
			               {
			            	   "mData" : "importeTotalCheque"//9
			               },
			               {
			            	   "mData" : "importeTotalEfectivo"//10
			               },
			               {
			            	   "mData" : "importeTotalRetenciones"//11
			               },
			               {
			            	   "mData" : "importeTotalBonos"//12
			               },
			               {
			            	   "mData" : "importeTotalOtrasMonedas"//13
			               },
			               {
			            	   "mData" : "fechaImputacion"//14
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

		tablas.tablaCobrosRelacionadosPrev = $("#tablaCobrosRelacionadosPrev").dataTable(cobrosRelacionadosPrevSettings);
		
		agregarBotonExcel(tablas.tablaCobrosRelacionados.api(),
				"Cobros_Relacionados",
				null, function(data, column, row) {
					
					return data;
		});
	
	
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	// FIN TAB RESUMEN
	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		// TAB REEMBOLSOS
		// ---------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------
		
		var documentosRelacionadosPrev = {
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

			tablas.tablaDocumentosRelacionadosPrev = $("#tablaDocumentosRelacionadosPrev").dataTable(documentosRelacionadosPrev);
			agregarBotonExcel(tablas.tablaDocumentosRelacionados.api(),
					"Documentos_Relacionados",
					null, function(data, column, row) {
						
						return data;
					});
			
			
			tablas.comprobantesPrev = $("#comprobantesPrev").dataTable(
					{
						dom : 'rt',
						"sScrollX" : true,
						"scrollY" : SCROLL_Y,
						"bScrollCollapse" : true,
//						"iDisplayLength" : DISPLAY_LENGTH,
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
									"defaultContent" : '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button id="btnDescargar" action="descargar" type="button" class="btn btn-xs btn-link bloqueDescargar" title="Descargar comprobante"><i class="icon-download-alt bigger-120"></i></button></div><div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>',
									"searchable" : false,
									"bSortable" : false
								} ]
					});
			
			$('#comprobantesPrev tbody').on('click','button',function() {
				var node = $(this).parents('tr')[0];
				var aData = tablas.comprobantesPrev.fnGetData(node);

				var iframe = document.createElement("iframe");
				iframe.src = "legajo-cheque-rechazado/descargarComprobante?id=" + aData.idComprobante;
				iframe.style.display = "none";
				document.body.appendChild(iframe);
			});
		
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
			
			
			var notificacionesPrev = {
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
						title: "Notificaciones_historicas",
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
										} else {
											data = '-';
										}
									} else if (column === 9) {
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
		
		tablas.tablaNotificacionesPrev = $("#tablaNotificacionesPrev").dataTable(notificacionesPrev);
				agregarBotonExcel(tablas.tablaNotificacionesPrev.api(),
						"Notificaciones_historicas",
						null, function(data, column, row) {
							
							return data;
		});
			
			
			
		$('[id^=btnCerrar]').click(function() {
			$('#btnCerrar').attr('disabled',true);
			$('#bloqueEspera').trigger('click');
			cerrarLegajo();
		});
		
			
		$('[id^=btnDesistir]').click(function() {
			
			$('#btnDesistir').attr('disabled',true);
			$('#bloqueEspera').trigger('click'); 
			desistirLegajo();
		});	
		

};
	
var drawTabResumenlegajo = function() {
	
	tablas.tablaNotificacionesPrev.fnClearTable();
	if (!$.isEmpty(datosTablas.notificaciones)) {
		tablas.tablaNotificacionesPrev.fnAddData(datosTablas.notificaciones, true);
	}
	mostrarBotonCerrar();
	mostrarBotonDesistir();
};

var mostrarBotonDesistir = function(){
	if (LGJ_ENVIADO_A_LEGALES == legajoChequeRechazado.estado || LGJ_DESISTIDO == legajoChequeRechazado.estado) {
		$('[id^=btnGuardar]').show();
		$('[id^=btnCerrar]').hide();
		$('[id^=btnDesistir]').show();
		if(LGJ_DESISTIDO == legajoChequeRechazado.estado){
			$('#btnDesistir').attr('disabled',true);
		}
	}
}
	
var mostrarBotonCerrar = function(){
		if (LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA == legajoChequeRechazado.estado 
				|| LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO == legajoChequeRechazado.estado
				|| LGJ_ABIERTO == legajoChequeRechazado.estado
				|| LGJ_ABIERTO_CON_CHEQUE_ENTREGADO == legajoChequeRechazado.estado
				|| LGJ_CERRADO_CON_CHEQUE_ENTREGADO == legajoChequeRechazado.estado
				|| LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA == legajoChequeRechazado.estado) {

			$('[id^=btnGuardar]').show();
			$('[id^=btnCerrar]').show();
			$('[id^=btnDesistir]').hide();
			if(LGJ_CERRADO_CON_CHEQUE_ENTREGADO == legajoChequeRechazado.estado
					|| LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA == legajoChequeRechazado.estado){
				$('#btnCerrar').attr('disabled',true);
			}
		}
	}
	
var clearTabResumenlegajo = function() {
	$('[id^=btnGuardar]').show();
	$('[id^=btnCerrar]').hide();
	$('[id^=btnDesistir]').hide();
};


function cerrarLegajo(){
	var legajo = {
			idLegajoChequeRechazado : $('#idLegajo').val()
	};
	$.ajax({
		"type" : "POST",
		"url" : "legajo-cheque-rechazado/cerrar",
		"dataType" : "json",
		"data" : JSON.stringify(eval(legajo)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				
				legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;
				
				$('#estadoLegajo').val(result.resultado.estadoDescripcion);
				legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
				legajoChequeRechazado.estado = result.resultado.estado;
			}
			else if(legajoChequeRechazado.estado == LGJ_CERRADO_CON_CHEQUE_ENTREGADO || legajoChequeRechazado.estado == LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA){
				$('#btnCerrar').attr('disabled',true);
				if (!$.isEmpty(result.errorMensaje)) {
					mostrarErrorGeneral(result.descripcionError);
					//marcarErrorSolapa([solapa.currrent]);
				}
			} else {
				if (!$.isEmpty(result.errorMensaje)) {
	
					mostrarErrorGeneral(result.descripcionError);
				}
				$('#btnCerrar').attr('disabled',false);
			}
		ocultarBloqueEspera(); 
		}
	});
}


function desistirLegajo(){
	var legajo = {
		idLegajoChequeRechazado : $('#idLegajo').val()
	};
	
	$.ajax({
		"type" : "POST",
		"url" : "legajo-cheque-rechazado/desistir",
		"dataType" : "json",
		"data" : JSON.stringify(eval(legajo)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
				
				legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;
				$('#estadoLegajo').val(result.resultado.estadoDescripcion);
				legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
				legajoChequeRechazado.estado = result.resultado.estado;
				
			} else if(legajoChequeRechazado.estado == LGJ_DESISTIDO){
				$('#btnDesistir').attr('disabled',true);
				if (!$.isEmpty(result.errorMensaje)) {
					mostrarErrorGeneral(result.descripcionError);
					//marcarErrorSolapa([solapa.currrent]);
				}
			} else {
				if (!$.isEmpty(result.errorMensaje)) {
					mostrarErrorGeneral(result.descripcionError);
					//marcarErrorSolapa([solapa.currrent]);
				}
			}
		ocultarBloqueEspera(); 
		}
	
	});

};

function cargarDatosResumen(legajo){
	$('#empresaPrev').val(legajo.empresa);
	$('#segmentoPrev').val(legajo.segmento);
	$('#analistaPrev').val(legajo.analista);
	if('Seleccione un item...' === legajo.copropietario){
		$('#copropietarioPrev').val('');
	}else {
		$('#copropietarioPrev').val(legajo.copropietario);
	}
	$('#ubicacionPrev').val(legajo.ubicacionDesc);
	$('#fechaRechazoPrev').val(legajo.fechaRechazo);
	$('#motivoRechazoPrev').val(legajo.motivoLegajoDescripcion);
	$('#fechaNotificacionRechazoPrev').val(legajo.fechaNotificacion);
	
//	edicion manual
	
	$('#edsistemaOrigenPrev').val(legajo.sistemaOrigen);
	$('#edTipoChequePrev').val(legajo.tipoChequeDescripcion);
	$('#edCodigoBancoOrigenPrev').val(legajo.idBancoOrigen +' - '+ legajo.bancoOrigenDescripcion);
	$('#edDescripcionBancoOrigenPrev').val(legajo.bancoOrigenDescripcion);
	$('#edNroChequePrev').val(legajo.numeroCheque);
	$('#edFechaDepositoPrev').val(legajo.fechaDeposito);
	$('#edFechaEmisionPrev').val(legajo.fechaEmision);
	$('#edFechaVencimientoPrev').val(legajo.fechaVencimiento);
	$('#edMonedaChequePrev').val(legajo.mondeDesc);
	$('#edImporteChequePrev').val(legajo.importe);
	if(legajo.idAcuerdo != '' && legajo.idAcuerdo != null ){
		$('#edAcuerdoChequePrev').val('Acuerdo '+legajo.idAcuerdo);
	}
	$('#edBancoDepositoPrev').val(legajo.bancoDepositoDescripcion);
	$('#edCuentaDepositoPrev').val(legajo.numeroCuentaDeposito);
	$('#edInputCodClientePrev').val(legajo.idCliente);
	$('#edRazonSocialClienteLegadoPrev').val(legajo.descripcionCliente);
//	$('#edMontoPendienteReembolsarInputPrev').val(legajo.);
//	$('#edMontoAplicadoPendienteReembolsarInputPrev').val(legajo.);
}