var descobrosEncontrados = null;

$(document).ready(function() {
    $("#checkBoxDescobrosEncontrados").prop('checked', false);
    
	var SCROLL_Y = '300px';
	
	var generarCuadroUsuario = function(nombre, urlFoto, mail) {
		if (nombre !== '-') {
			return '<div style="width: 180px; text-align: left;"> ' +
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
	var descobrosEncontradosSettings = {
			dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"order": [[19, "desc" ]],
			buttons: [{	
				 extend:'excelHtml5',
      		     text:'Excel',
      		     title: "Busqueda_De_Reversiones",
                 className: 'excelbtn'
							}],
			"aoColumns" : [
                                      { "targets" : 0,
					   "searchable" : false,
					   "bSortable" : false,
					   "data" : null,
					   "render" : function(data, type, row) {
						   if (type === 'display') {
							   var idDescob = data.idDescobro;
							   
								return '<div><input type="checkbox" id="check'+idDescob+'" class="checkSelect editor-active" onclick="checking(\'checkBoxDescobrosEncontrados\',\'checkSelect\', descobrosEncontrados, \'btnRevertir\')"/></div>';
							}
							return '';
					   }
					},
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   var idDesc = data.idDescobro;
			            			   
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleDescobro(' + idDesc + ',' + data.idCobro +')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
			            			   if (data.mostrarBotonModificar == true) {
			            				   var idDesc = data.idDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarDescobro('+idDesc+')"><i class="icon-edit bigger-120"></i></button></div>';
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
			            			   if (data.mostrarBotonAnular == true) {
			            				   var idDesc = data.idDescobro;
			            				   var estado = data.estado;
			            				   var idOperacionDescobro = data.idOperacionDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularDescobro('+idDesc+','+idOperacionDescobro+',\''+estado+'\')"><i class="icon-remove bigger-120"></i></button></div>';
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
			            			   var idDesc = data.idDescobro;
			            			   var idOpDesc = data.idOperacionDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialDescobro('+idDesc+','+idOpDesc+')"><i class="icon-list-alt bigger-120"></i></button></div>';
			            		   }
			            		   return null;
			            	   }
			               },
			               { "mData" : "idDescobroPadreFormateado" },
						   { "mData" : "idDescobroFormateado" , "visible": false },
			               { "mData" : "idOperacionDescobro" },
			               { "mData" : "descripcionMotivoReversion" },
			               { "mData" : "idOperacionCobro" },
			               { "mData" : "clientesDescobro",
			            	   'render': function(data, type, row) {
			            		   if (!$.isEmpty(data)) {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return '-';
			            	   }
			               },
			               { "mData" : "descripcionEstado" },
			               { "mData" : "descripcionMarca" },
			               { "mData" : "fechaUltimoEstadoFormatoJson" },
			               { "mData": "nombreAnalista",
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.nombreAnalista, row.urlFotoAnalista, row.mailAnalista);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData": "nombreCopropietario",
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.nombreCopropietario, row.urlFotoCopropietario, row.mailCopropietario);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData": "nombreAnalistaTeamComercial",
			            	   "render": function (data, type, row) {
			            		   if (type === 'display') {
			            			   return generarCuadroUsuario(row.nombreAnalistaTeamComercial, row.urlFotoAnalistaTeamComercial, row.mailAnalistaTeamComercial);
			            		   }
			            		   return data;
			            	   },
			            	   "className": "dt-body-center",
			            	   "searchable": true,
			            	   "bSortable": true 
			               },
			               { "mData" : "codigoOperacionExterna",
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   }
			               },
			               { "mData" : "tipoCobro" },
			               { "mData" : "importeTotalFormateado" },
			               { "mData" : "fechaAltaFormatoJson" },
			               { "mData" : "fechaDerivacionFormatoJson" },
			               { "mData" : "fechaReversionFormatoJson" },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   var idDesc = data.idDescobro;
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleDescobro(' + idDesc + ',' + data.idCobro +')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
			            			   if (data.mostrarBotonModificar == true) {
			            				   var idDesc = data.idDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarDescobro('+idDesc+')"><i class="icon-edit bigger-120"></i></button></div>';
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
			            			   if (data.mostrarBotonAnular == true) {
			            				   var idDesc = data.idDescobro;
			            				   var estado = data.estado;
			            				   var idOperacionDescobro = data.idOperacionDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularDescobro('+idDesc+','+idOperacionDescobro+',\''+estado+'\')"><i class="icon-remove bigger-120"></i></button></div>';
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
			            			   	var idDesc = data.idDescobro;
			            			   	var idOpDesc = data.idOperacionDescobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialDescobro('+idDesc+','+idOpDesc+')"><i class="icon-list-alt bigger-120"></i></button></div>';
//			            			   }
			            		   }
			            		   return null;
			            	   }
			               }
			],
			"fnDrawCallback": function( oSettings ) {
				if (!$.isEmpty(descobrosEncontrados) && descobrosEncontrados.fnGetData().length > 0) {
					if (isAllCheckedPage('checkSelect', descobrosEncontrados)) {
						$("#checkBoxDescobrosEncontrados").prop('checked', true);
					} else {
						$("#checkBoxDescobrosEncontrados").prop('checked', false);
					}
				}
			},
			'columnDefs': [	//targets son los th con las class date o importe
			               	{type: 'latam_datetime', targets: 'dateTimes'},
			               	{type: 'latam_datetime_seconds', targets: 'datetimeSeconds'},
			               	{type: 'comparador-currency', targets: 'importe'}
			],
	};

	descobrosEncontrados = $("#descobrosEncontrados").dataTable(descobrosEncontradosSettings);
	
	
	$('#btnBuscar').click(function() {

		$('#bloqueEspera').trigger('click');
		
		var sistDestino="";
		if (!$.isEmpty($('#idSistemaDestino').val())){
			sistDestino = $('#idSistemaDestino').val();
		}else if (!$.isEmpty($('#sistemaAplicacionManual').val())){
			sistDestino = $('#sistemaAplicacionManual').val();
		}
		
		var descobroFiltro = {
				idEmpresa : $('#selectEmpresa').val(),
				idSegmento : $('#selectSegmento').val(),
				selectCliente :$('#selectCliente').val(),
				textCliente : $('#textCliente').val(),
				nroDocumento : $('#nroDocumento').val(),
				nroRef : $('#nroRef').val(),
				idEstado : $('#selectEstado').val(),
				idMotivo : $('#selectMotivo').val(),
				idOpCobro : $('#idOpCobro').val(),
				idOperacionReversion : $('#idOperacionReversion').val(),
				fechaDesde : $('#fechaDesde').val(),
				fechaHasta : $('#fechaHasta').val(),
				idAnalista : $('#selectAnalista').val(),
				idTipoMedioPago : $('#idTipoMedioPago').val(),
				refMP : $('#refMP').val(),
				idSubtipoCompensacion : $('#idSubtipoCompensacion').val(),
				nroSAP : $('#nroSAP').val(),
				idTratamientoDiferencia : $('#idTratamientoDiferencia').val(),
				refNroTramite : $('#refNroTramite').val(),
				refNroAcuerdoFact : $('#refNroAcuerdoFact').val(),
				idSistemaDestino : sistDestino,
				codigoOperacionExterna : $('#codigoOperacionExterna').val()
		};

		$.ajax({
			"type" : "POST",
			"url": "descobro-busqueda/buscar-descobros",
			"dataType": "json", 
			"data": JSON.stringify(eval(descobroFiltro)), 
			"contentType": "application/json",
			"mimeType": "application/json",
			"success" : function(resultadoBusqueda) {
				
				if (resultadoBusqueda != null) {
					descobrosEncontrados.fnClearTable();

					if (resultadoBusqueda.success) {
						if (resultadoBusqueda.aaData != null && resultadoBusqueda.aaData.length > 0){
							descobrosEncontrados.fnAddData(resultadoBusqueda.aaData,true);
							$('#checkBoxDescobrosEncontrados').prop('disabled', false);
						}
					} else {
						if (!$.isEmpty(resultadoBusqueda.errores)){

							for (var i = 0; i < resultadoBusqueda.errores.length; i++) {
								
								$(resultadoBusqueda.errores[i].campoError).text(resultadoBusqueda.errores[i].descripcionError);	
							}
						}
					}
				}
				volviendoABusqueda = false;
				ocultarBloqueEspera();
			}
		});
		$("span.error").text("");
	});
	
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaDesde").datepicker({firstDay : 1});
	$("#fechaHasta").datepicker({firstDay : 1});
	
	// CARGAR COMBOS

	(function() {

		var empresaPrev;
		var segPrev;

		$("#selectEmpresa").focus(function() {
			empresaPrev = this.value;
		}).change(function() {
			if ($.isEmpty(this.value)) {
				var options = [ {text : 'Seleccione un item...', value : ''} ];
				$("#selectSegmento").replaceOptions(options);
				$("#selectSegmento").val(options[0].value);
			} else if (this.value != empresaPrev) {
				$('#bloqueEspera').trigger('click');
				empresaPrev = this.value;
				$.ajax({
					"dataType" : 'json',
					"type" : "POST",
					"url" : 'cobro-busqueda/buscarSegmentosCobros',
					"data" : {
						idEmpresa : this.value
					},
					"success" : function(result) {
						if (result.length > 0) {
							if (result.length == 1) {
								$("#selectSegmento").replaceOptions(result);
								$("#selectSegmento").val(result[0].value).trigger('change');
							} else {
								var options = [ {text : 'Seleccione un item...',value : ''} ];
								$("#selectCopropietario").replaceOptions(options);
								$("#selectSegmento").val(options[0].value);
								$.each(result,function(index,option) { options.push(option); });
								$("#selectSegmento").replaceOptions(options);
								$("#selectSegmento").val(options[0].value);
							}
						}
						ocultarBloqueEspera();
					}
				});
			};
		});
		

		if ($("#selectSegmento").val() == ''){
			$('#bloqueEspera').trigger('click');
			var options = [{text: 'Seleccione un item...', value : ''}];
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : 'cobro-busqueda/buscarAnalistaCobros',
				"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : 'TODOS_LOS_SEGMENTOS' },
				"success" : function(result) {
					if (result.length == 1) {
						result[0].text = result[0].value + " - " + result[0].text;
						$("#selectAnalista").replaceOptions(result);
					} else {
						var options = [{text: 'Seleccione un item...', value : ''}];
						var cobFiltroAnalista = options[0].value;
						$.each(result, function(index, option) {
							option.text = option.value + " - " + option.text;
							if(option.value === cobroFiltroAnalista){
								cobFiltroAnalista = option.value;
							}
							options.push(option);
						});
						$("#selectAnalista").replaceOptions(options);
						
						$("#selectAnalista").val(cobFiltroAnalista);
					}
					if (!volviendoABusqueda) {
						ocultarBloqueEspera();
					}
				}
			});
		}

		$("#selectSegmento").focus(function () {
			segPrev = this.value;
		}).change(function() {
			if ($.isEmpty(this.value)) {
				$('#bloqueEspera').trigger('click');
				var options = [{text: 'Seleccione un item...', value : ''}];
				$.ajax({
					"dataType" : 'json',
					"type" : "POST",
					"url" : 'cobro-busqueda/buscarAnalistaCobros',
					"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : 'TODOS_LOS_SEGMENTOS' },
					"success" : function(result) {
						if (result.length == 1) {
							result[0].text = result[0].value + " - " + result[0].text;
							$("#selectAnalista").replaceOptions(result);
						} else {
							var options = [{text: 'Seleccione un item...', value : ''}];
							$.each(result, function(index, option) {
								option.text = option.value + " - " + option.text;
								options.push(option);
							});
							$("#selectAnalista").replaceOptions(options);
							$("#selectAnalista").val(options[0].value);
						}
						ocultarBloqueEspera();
					}
				});
			} else if (this.value != segPrev) {
				$('#bloqueEspera').trigger('click');
				segPrev = this.value;
				$.ajax({
					"dataType" : 'json',
					"type" : "POST",
					"url" : 'cobro-busqueda/buscarAnalistaCobros',
					"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : this.value },
					"success" : function(result) {
						if (result.length == 1) {
							result[0].text = result[0].value + " - " + result[0].text;
							$("#selectAnalista").replaceOptions(result);
						} else {
							var options = [{text: 'Seleccione un item...', value : ''}];
							$.each(result, function(index, option) {
								option.text = option.value + " - " + option.text;
								options.push(option);
							});
							$("#selectAnalista").replaceOptions(options);
							$("#selectAnalista").val(options[0].value);
						}
						ocultarBloqueEspera();
					}
				});
			};
		});
		
		if ($.isEmpty($("#idTipoMedioPago").val())) {
			$("#refMP").prop( "disabled", true );
			$("#divSubComp").hide();
			$("#divNroSAP").hide();
		} else{
			var medPag = $("#idTipoMedioPago").val();
			if (medPag == "CRE" || medPag == "CTA"){
				$("#refMP").attr("maxlength",15);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
			if (medPag == "CHEQ" || medPag == "CHEQDIF" || medPag == "INTER"){
				$("#refMP").attr("maxlength",8);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
			if (medPag == "TRANS" || medPag == "RET"){
				$("#refMP").attr("maxlength",16);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
			if (medPag == "EFE" || medPag == "25"){
				$("#refMP").attr("maxlength",10);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
			if (medPag == "33" || medPag == "16" || medPag == "34"){
				$("#refMP").attr("maxlength",25);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
			if (medPag == "REM"){
				$("#refMP").attr("maxlength",14);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}

			if (medPag == "35"){
				$("#refMP").attr("maxlength",25);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").show();
				$("#divNroSAP").show();
				$("#nroSAP").attr("maxlength",10);
			}
			
			if (medPag == "41"){
				$("#refMP").attr("maxlength",25);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").show();
				$("#nroSAP").attr("maxlength",10);
			}
		}
		
		if ($.isEmpty($("#selectCliente").val())) {
			$("#textCliente").prop( "disabled", true );
		}
	
		$("#selectCliente").change(function() {
			$('#errorTextCliente').text("");
			if ($.isEmpty(this.value)) {
				$("#textCliente").val("");
				$('#errorBusqueda').text("");
				$('#errorBusqueda').hide();
				$("#textCliente").prop( "disabled", true );
			}else if (this.value == "BUSQUEDA_POR_CUIT"){
				$("#textCliente").attr("maxlength", 13);
				$("#textCliente").val("");
				$("#textCliente").prop( "disabled", false );
			} else {
				$("#textCliente").attr("maxlength", 11);
				$("#textCliente").val("");
				$("#textCliente").prop( "disabled", false );
			}
		});
		
		$("#idTipoMedioPago").change(function() {
			$('#errorRefMP').text("");
			flagErrorRedMedio = false;
			if ($.isEmpty(this.value)) {
				$("#refMP").val("");
				$('#errorRefMP').text("");
				$('#errorRefMP').hide();
				$("#refMP").prop( "disabled", true );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			} else {
				if (this.value == "CRE" || this.value == "CTA"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",15);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
				if (this.value == "CHEQ" || this.value == "CHEQDIF" || this.value == "INTER"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",8);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
				if (this.value == "TRANS" || this.value == "RET"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",16);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
				if (this.value == "EFE" || this.value == "25"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",10);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
				if (this.value == "33" || this.value == "16" || this.value == "34"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",25);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
				if (this.value == "REM"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",14);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}

				if (this.value == "35"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",25);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").show();
					$("#idSubtipoCompensacion").val('');
					$("#divNroSAP").show();
					$("#nroSAP").attr("maxlength",10);
					$("#nroSAP").val("");
				}
				
				if (this.value == "41"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",25);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").show();
					$("#nroSAP").attr("maxlength",10);
					$("#nroSAP").val("");
				}
			}
			
			
		});
		
		if ($.isEmpty($("#idTratamientoDiferencia").val())) {
			$("#refNroTramite").prop( "disabled", true );
			$("#idSistemaDestino").prop( "disabled", true );
			$("#refNroAcuerdoFact").prop( "disabled", true );
			$("#codigoOperacionExterna").prop( "disabled", true );
			$("#sistemaAplicacionManual").hide();
		}
		
		$("#idTratamientoDiferencia").change(function() {
			$('#errorRefNroTramite').text("");
			$('#errorRefNroAcuerdoFact').text("");
			$('#errorCodOpExt').text("");
//			flagErrorRedMedio = false;
			if ($.isEmpty(this.value)) {
				$("#refNroTramite").val("");
				$('#errorRefNroTramite').text("");
				$("#refNroAcuerdoFact").val("");
				$('#errorRefNroAcuerdoFact').text("");
				$("#codigoOperacionExterna").val("");
				$('#errorCodOpExt').text("");
				$("#sistemaAplicacionManual").hide();
				$("#sistemaAplicacionManual").val("");
				$("#idSistemaDestino").show();
				$("#idSistemaDestino").val("");
//				$('#errorRefNroAcuerdoFact').text("");
//				$('#errorRefMP').hide();
				$("#codigoOperacionExterna").prop( "disabled", true );
				$("#refNroTramite").prop( "disabled", true );
				$("#idSistemaDestino").prop( "disabled", true );
				$("#refNroAcuerdoFact").prop( "disabled", true );
			} else {
				if (this.value == "37" || this.value == "26" || this.value == "15" || this.value == "27" || this.value == "30"){
					$("#refNroTramite").val("");
					$("#refNroTramite").prop( "disabled", false );
					$("#sistemaAplicacionManual").hide();
					$("#sistemaAplicacionManual").val("");
					$("#idSistemaDestino").show();
					$("#idSistemaDestino").val("");
					$("#idSistemaDestino").prop( "disabled", true );
					$("#refNroAcuerdoFact").val("");
					$("#refNroAcuerdoFact").prop( "disabled", true );
					$("#codigoOperacionExterna").val("");
					$("#codigoOperacionExterna").prop( "disabled", true );
					
				}
				if (this.value == "38"){
					$("#refNroTramite").val("");
					$("#refNroTramite").prop( "disabled", true );
					$("#sistemaAplicacionManual").hide();
					$("#sistemaAplicacionManual").val("");
					$("#idSistemaDestino").show();
					$("#idSistemaDestino").val("");
					$("#idSistemaDestino").prop( "disabled", false );
					if(volviendoABusqueda){
						$("#idSistemaDestino").val(decobroFiltroIdSistemaDestino);
					}
					$("#refNroAcuerdoFact").val("");
					$("#refNroAcuerdoFact").prop( "disabled", false );
					$("#codigoOperacionExterna").val("");
					$("#codigoOperacionExterna").prop( "disabled", true );
				}
				if (this.value == "37"){
					$("#refNroTramite").val("");
					$("#refNroTramite").prop( "disabled", false );
					$("#sistemaAplicacionManual").hide();
					$("#sistemaAplicacionManual").val("");
					$("#idSistemaDestino").show();
					$("#idSistemaDestino").val("");
					if(volviendoABusqueda){
						$("#idSistemaDestino").val(decobroFiltroIdSistemaDestino);
					}
					$("#idSistemaDestino").prop( "disabled", false );
					$("#refNroAcuerdoFact").val("");
					$("#refNroAcuerdoFact").prop( "disabled", false );
					$("#codigoOperacionExterna").val("");
					$("#codigoOperacionExterna").prop( "disabled", true );
				}
				if(this.value == "14" || this.value == "42" || this.value == "43"){
					$("#refNroTramite").val("");
					$("#refNroTramite").prop( "disabled", true );
					$("#idSistemaDestino").show();
					$("#idSistemaDestino").val("");
					$("#idSistemaDestino").prop( "disabled", true );
					$("#sistemaAplicacionManual").hide();
					$("#sistemaAplicacionManual").val("");
					$("#refNroAcuerdoFact").val("");
					$("#refNroAcuerdoFact").prop( "disabled", true );
					$("#codigoOperacionExterna").val("");
					$("#codigoOperacionExterna").prop( "disabled", true );
				}
				if(this.value == "46"){
					$("#refNroTramite").val("");
					$("#refNroTramite").prop( "disabled", true );
					$("#idSistemaDestino").val("");
					$("#sistemaAplicacionManual").show();
					$("#sistemaAplicacionManual").val("");
					if(volviendoABusqueda){
						$("#sistemaAplicacionManual").val(decobroFiltroIdSistemaDestino);
					}
					$("#refNroAcuerdoFact").val("");
					$("#refNroAcuerdoFact").prop( "disabled", true );
					$("#idSistemaDestino").hide();
					$("#codigoOperacionExterna").val("");
					$("#codigoOperacionExterna").prop( "disabled", false );
				}
			}
		});
	})();

	if(volviendoABusqueda){
		$('#btnBuscar').click();
		$('#idTratamientoDiferencia').change();
	}
	
	function checking(leaderCheckId, classNameChecks, table, searchBtnId) {
		if (habilitarBtnRevertir(classNameChecks, table)) {
			$('#' + searchBtnId).prop('disabled', false);
		} else {
			$('#' + searchBtnId).prop('disabled', true);
		}
		if (isAllCheckedPage(classNameChecks, table)) {
			$('#' + leaderCheckId).prop('checked', true);
		} else {
			$('#' + leaderCheckId).prop('checked', false);
		}
	};
	
	$("#checkBoxDescobrosEncontrados").click(function () {
		var checked = $(this).prop('checked');
		 $('#descobrosEncontrados').find('.odd, .even').find('.checkSelect').each(function(index) {
			 $(this).prop('checked', checked);
		 });
		if (habilitarBtnRevertir('checkSelect', descobrosEncontrados)) {
			$('#btnRevertir').prop('disabled', false);
		} else {
			$('#btnRevertir').prop('disabled', true);
		}
	});
	
	$('#btnRevertir').click(function() {
		var descobros = new Object();
		var mensaje = mensajeRevertir;

		descobros.ids = [];
		$('.checkSelect', descobrosEncontrados.fnGetNodes()).each(function(i, elem) {
			if ($(elem).prop('checked')) {
				$(descobrosEncontrados.fnGetData()).each(function (j, descobro) {
					if ("check" + descobro.idDescobro == $(elem).prop('id')) {
						if (!$.isEmpty(descobro.reversionHabilitada)) {
						    descobros.ids.push(descobro.idDescobro);
						}
					}
				});
			}
		});
		
		if (descobros.ids.length > 1) {
			mensaje = mensajeRevertirPlural;
		}
		bootbox.confirm(mensaje, function(result) {
			if (result) {
				$('#bloqueEspera').trigger('click');
				$('#idsReversion').val(descobros.ids.join(','));
				$('#descobroDto')[0].action=urlAsynchronousHTTP+"/revertir";
				$('#descobroDto')[0].submit();
			}
		});
	});
	
	// CARGAR COMBOS
	$(function() {
		$("#selectAnalista").combobox();
		$("#toggle").click(function() {
			$("#combobox").toggle();
		});
	});

   (function( $ ) {
	    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
        
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "Seleccione un item...";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr( "title", "" )
          .addClass( "" )
          .autocomplete({
            delay: 0,
            minLength: 0,
           
            source: $.proxy( this, "_source" )
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });
 
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
        	 
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
             
            });
          },
 
          autocompletechange: "_removeIfInvalid"
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )
          .tooltip()
          .appendTo( this.wrapper )
          .button({
            icons: {
                primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .removeClass("ui-button")
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // Close if already visible
            if ( wasOpen ) {
              return;
            }
 
            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
      },
	 
	      _source: function( request, response ) {
	        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
	        response( this.element.children( "option" ).map(function() {
	          var text = $( this ).text();
	          if ( this.value && ( !request.term || matcher.test(text) ) )
	            return {
	              label: text,
	              value: text,
	              option: this
	            };
	        }) );
	      },
	 
	      _removeIfInvalid: function( event, ui ) {
	    	  
	        // Selected an item, nothing to do
	        if ( ui.item ) {
	        	
	          return;
	        }
	 
	        // Search for a match (case-insensitive)
	        var value = this.input.val(),
	          valueLowerCase = value.toLowerCase(),
	          valid = false;
	        this.element.children( "option" ).each(function() {
	          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
	            this.selected = valid = true;
	            
	            return false;
	          }
	        });
	 
	        // Found a match, nothing to do
	        if ( valid ) {
	        	
	          return;
	        }
	        
	        // Remove invalid value
	        this.input
	          .val( "Seleccione un item..." )
	          .attr( "title", value + " no coincide ningun registro" )
	          .tooltip( "open" );
	        this.element.val( "" );
	        this._delay(function() {
	          this.input.tooltip( "close" ).attr( "title", "" );
	         
	        }, 2500 );
	        this.input.data( "ui-autocomplete" ).term = "";
	      },
	 
	      _destroy: function() {
	        this.wrapper.remove();
	        this.element.show();
	      }
	    });
	  })( jQuery );
	
});

//Guido.Settecerze
function isAllCheckedPage(classNameChecks, tableName) {
	var allChecked = true;
	if ($(tableName).find('.odd', '.even').find('.checkSelect').length < 1) {
		return false;
	}
	$(tableName).find('.odd, .even').find('.checkSelect').each(function(index) {
		allChecked = $(this).prop('checked');
		if (!allChecked) {
			return false;
		}
	});
	
	return allChecked;
}

//Guido.Settecerze
function habilitarBtnRevertir(classNameChecks, table) {
	var reversionHabilitada = false;
	var salir = false;
	$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
		if ($(elem).prop('checked')) {
			$(table.fnGetData()).each(function (j, descobro) {
				if ("check" + descobro.idDescobro == $(elem).prop('id')) {
					if ($.isEmpty(descobro.reversionHabilitada)) {
					    reversionHabilitada = false;
						salir = true;
						return false;
					} else {
					    reversionHabilitada = true;
					} 
				}
			});
		}
		if (salir) {
			return false;
		}
	});
	return reversionHabilitada;
};

//Guido.Settecerze
function checking(leaderCheckId, classNameChecks, table, searchBtnId) {
	if (habilitarBtnRevertir(classNameChecks, table)) {
		$('#' + searchBtnId).prop('disabled', false);
	} else {
		$('#' + searchBtnId).prop('disabled', true);
	}
	if (isAllCheckedPage(classNameChecks, table)) {
		$('#' + leaderCheckId).prop('checked', true);
	} else {
		$('#' + leaderCheckId).prop('checked', false);
	}
};

function anularDescobro(idDescobro, idOperacionDescobro, idEstado) {
	bootbox.confirm(mensajeAnular, function(result) {
		if (result) {
	$('#bloqueEspera').trigger('click');
	$('#idDescobro').val(idDescobro);
	$('#idOperacionDescobro').val(idOperacionDescobro);
	$('#idEstado').val(idEstado);
	$('#descobroDto')[0].action=urlAsynchronousHTTP+"/descobro-anulacion";
	$('#descobroDto')[0].submit();
		}
	});
};

//U572497 Guido.Settecerze
function editarDescobro(idDescobro) {
	$('#bloqueEspera').trigger('click');
	$('#idDescobro').val(idDescobro);
	$('#descobroDto')[0].action=urlAsynchronousHTTP+"/descobros-configuracion-edicion";
	$('#descobroDto')[0].submit();
};

//U572497 Guido.Settecerze
function detalleDescobro(idDescobro, idCobro) {
    $('#bloqueEspera').trigger('click');
    $('#idDescobro').val(idDescobro);
    $('#idCobro').val(idCobro);
    $('#descobroDto')[0].action=urlAsynchronousHTTP+"/descobros-reversion-detalle";
    $('#descobroDto')[0].submit();
};

function historialDescobro(idDescobro,idOperacion) {
	$('#bloqueEspera').trigger('click');
	$('#idDescobro').val(idDescobro);
	$('#idOperacionDescobro').val(idOperacion);
	$('#descobroDto')[0].action=urlAsynchronousHTTP+"/descobro-historial";
	$('#descobroDto')[0].submit();
};