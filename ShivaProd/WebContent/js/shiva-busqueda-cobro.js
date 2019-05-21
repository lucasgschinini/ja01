var flagErrorRedMedio = false;
var cobrosEncontrados = null;
$(document).ready(function() {

	
	function exportarBusquedaCobros() {
		$('#bloqueEspera').trigger('click');
	    $('#formBusqueda')[0].action=urlAsynchronousHTTP+"/cobro-busqueda/exportandoBusquedaCobros";
	    $('#formBusqueda')[0].submit();
	};
	
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
	var cobrosEncontradosSettings = {
			 dom : 'Bfrtip',
			"sScrollX" : true,
			"scrollY" : SCROLL_Y,
			"bScrollCollapse" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"order": [[ 17, "desc" ]],
			"order": [[ 4, "asc" ]],
			buttons: [{
					extend:'excelHtml5',
					text:'Excel',
					title: "Busqueda_De_Cobros",
					className: 'excelbtn'
			}],
			"aoColumns" : [
							{ "targets" : 0,
							   "searchable" : false,
							   "bSortable" : false,
							   "data" : null,
							   "render" : function(data, type, row) {
								   if (type === 'display') {
									   var idCob = data.idCobro;
										return '<div><input type="checkbox" id="check'+idCob+'" class="checkSelect editor-active" onclick="checking(\'seleccionarTodos\',\'checkSelect\', cobrosEncontrados, \'btnDesapropiar\')"/></div>';
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
			            			   var idCob = data.idCobro;
			            			   var idCobPadre = data.idCobroPadre;
			            			   var nombreArchivo = data.nombreArchivo;
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+idCob+','+idCobPadre+',\''+nombreArchivo+'\')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
			            				   var idCob = data.idCobro;
			            				   var idCobPadre = data.idCobroPadre;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarCobro('+idCob+','+idCobPadre+')"><i class="icon-edit bigger-120"></i></button></div>';
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
			        		   if (data.mostrarBotonRevertir == true) {
			        		       var idCob = data.idCobro;
			        		       return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Revertir" onclick="revertirCobro('+ idCob + ')"><i class="icon-circle-arrow-left bigger-120"></i></button></div>';
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
			            				   var idCob = data.idCobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularCobro('+idCob+')"><i class="icon-remove bigger-120"></i></button></div>';
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
//			            			   if (data.mostrarBotonAnular == true) {
			            				   var idCob = data.idCobro;
			            				   var idOp = data.idOperacion;
			            				   var idCobPadre = data.idCobroPadre;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialCobro('+idCob+','+idOp+','+idCobPadre+')"><i class="icon-list-alt bigger-120"></i></button></div>';
//			            			   }
			            		   }
			            		   return null;
			            	   }
			               },
			               { "mData" : "idOperacion" },
			               { "mData" : "descripcionMotivoCobro" },
			               { "mData" : "clientesCobro",
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
			            			   if(!$.isEmpty(row.mailAnalistaTeamComercial)){
			            				   return generarCuadroUsuario(row.nombreAnalistaTeamComercial, row.urlFotoAnalistaTeamComercial, row.mailAnalistaTeamComercial);
			            			   }
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
			               { "mData" : "idReversionPadre" },
			               { "mData" : "idOperacionReversion" },
			               { "mData" : "importeTotalDeudaFormateado" },
			               { "mData" : "fechaAltaFormatoJson" },
			               { "mData" : "fechaDerivacionFormatoJson" },
			               { "mData" : "fechaAprobacionReferenteCobranzaFormatoJson" },
			               { "mData" : "nombreApellidoUsuarioAprobacionReferenteCobranza" },
			               { "mData" : "fechaAprobacionOperacionesEspecialesFormatoJson" },
			               { "mData" : "nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales" },
			               { "mData" : "fechaReferenteCajaFormatoJson" ,
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   }
			               },
			               { "mData" : "nombreApellidoReferenteCaja" ,
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   }
			               },
			               { "mData" : "fechaReferenteOperacionExternaFormatoJson" ,
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   }
			               },
			               { "mData" : "nombreApellidoReferenteOperacionExterna" ,
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   }
			               },
			               { "mData" : "fechaImputacionFormatoJson" },
			               { "mData" : "idOperacionMasiva" },
			               { "mData" : "nombreArchivo",
			            	   'render': function(data, type, row) {
			            		   if (data !== '-') {
			            			   return data.split('-').join('<br/>');
			            		   }
			            		   return data;
			            	   } },
			               { "targets" : 0,
			            	   "searchable" : false,
			            	   "bSortable" : false,
			            	   "data" : null,
			            	   "render" : function(data, type, row) {
			            		   if (type === 'display') {
			            			   var idCob = data.idCobro;
			            			   var idCobPadre = data.idCobroPadre;
			            			   var nombreArchivo = data.nombreArchivo;
			            			   
			            			   return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleCobro('+idCob+','+idCobPadre+',\''+nombreArchivo+'\')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
			            				   var idCob = data.idCobro;
			            				   var idCobPadre = data.idCobroPadre;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarCobro('+idCob+','+idCobPadre+')"><i class="icon-edit bigger-120"></i></button></div>';
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
			        		   if (data.mostrarBotonRevertir == true) {
			        		       var idCob = data.idCobro;
			        		       return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Revertir" onclick="revertirCobro('+ idCob + ')"><i class="icon-circle-arrow-left bigger-120"></i></button></div>';
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
			            				   var idCob = data.idCobro;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularCobro('+idCob+')"><i class="icon-remove bigger-120"></i></button></div>';
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
//			            			   if (data.mostrarBotonAnular == true) {
			            				   var idCob = data.idCobro;
			            				   var idOp = data.idOperacion;
			            				   var idCobPadre = data.idCobroPadre;
			            				   return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialCobro('+idCob+','+idOp+','+idCobPadre+')"><i class="icon-list-alt bigger-120"></i></button></div>';
//			            			   }
			            		   }
			            		   return null;
			            	   }
			               }
			],
			"fnDrawCallback": function( oSettings ) {
				if (!$.isEmpty(cobrosEncontrados) && cobrosEncontrados.fnGetData().length > 0) {
					if (isAllCheckedPage('checkSelect', cobrosEncontrados)) {
						$("#seleccionarTodos").prop('checked', true);
					} else {
						$("#seleccionarTodos").prop('checked', false);
					}
				}
		    },
			'columnDefs': [	//targets son los th con las class date o importe
			               	{type: 'latam_datetime', targets: 'dateTimes'},
			               	{type: 'latam_datetime_seconds', targets: 'datetimeSeconds'},
			               	{type: 'comparador-currency', targets: 'importe'}
			]
	};

	cobrosEncontrados = $("#cobrosEncontrados").dataTable(cobrosEncontradosSettings);

	$('#btnBuscar').click(function() {
		
		$('#bloqueEspera').trigger('click');
	
		if (flagErrorRedMedio) {
			ocultarBloqueEspera();
			flagErrorRedMedio = false;
			return;
		}
		var sistDestino="";
		if (!$.isEmpty($('#idSistemaDestino').val())){
			sistDestino = $('#idSistemaDestino').val();
		}else if (!$.isEmpty($('#idSistemaDestinoAplicacionManual').val())){
			sistDestino = $('#idSistemaDestinoAplicacionManual').val();
		}
		var cobroFiltro = {
				idEmpresa : $('#selectEmpresa').val(),
				idSegmento : $('#selectSegmento').val(),
				selectCliente :$('#selectCliente').val(),
				textCliente : $('#textCliente').val(),
				nroDocumento : $('#nroDocumento').val(),
				nroRef : $('#nroRef').val(),
				idEstado : $('#idEstado').val(),
				idMotivo : $('#selectMotivo').val(),
				idOpCobro : $('#idOpCobro').val(),
				fechaDesde : $('#fechaDesde').val(),
				fechaHasta : $('#fechaHasta').val(),
				idAnalista : $('#selectAnalista').val(),
				idOperacionReversion : $('#idOperacionReversion').val(),
				idOperacionMasiva : $('#idOperacionMasiva').val(),
				idTipoMedioPago : $('#idTipoMedioPago').val(),
				refMP : $('#refMP').val(),
				idSubtipoCompensacion : $('#idSubtipoCompensacion').val(),
				nroSAP : $('#nroSAP').val(),
				tipoTratamientoDiferencia : $('#idTipoTratamiento').val(),
				sistemaDestino : sistDestino,
				nroAcuerdoFact : $('#idNroAcuerdoFact').val(),
				nroTramite : $('#idNroTramite').val(),
				codigoOperacionExterna : $('#idCodOpExt').val(),
				referenteCobranza : $("#selectReferenteCobranza").val(),
				referenteCaja : $("#selectReferenteCaja").val(),
				referenteOperacionesExternas : $("#selectReferenteOperacionesExternas").val(),
				aprobadorOperacionesEspeciales : $("#selectAprobadorOperacionesEspeciales").val()
		};

		$.ajax({
			"type" : "POST",
			"url": "cobro-busqueda/buscar-cobros",
			"dataType": "json", 
			"data": JSON.stringify(eval(cobroFiltro)), 
			"contentType": "application/json",
			"mimeType": "application/json",
			"success" : function(resultadoBusqueda) {
				if (resultadoBusqueda != null) {
					cobrosEncontrados.fnClearTable();

					if (resultadoBusqueda.success) {
						if (resultadoBusqueda.aaData != null && resultadoBusqueda.aaData.length > 0){
	 						if (resultadoBusqueda.aaData.length > limiteParaExportarBusquedaCobros) {
	 							exportarBusquedaCobros();
	 						} else {
								cobrosEncontrados.fnAddData(resultadoBusqueda.aaData,true);
								$('#seleccionarTodos').prop('disabled', false);
	 						}
						}
						
						actualizarFiltroReferentes();
					} else {
						if (!$.isEmpty(resultadoBusqueda.errores)){

							for (var i = 0; i < resultadoBusqueda.errores.length; i++) {
								if (!resultadoBusqueda.errores[i].campoError == "#errorBusquedaCliente"){
									if (!$.isEmpty($('#agenciaDeNegocio').val())){
										$('#errorAgenciaDeNegocio').text(resultadoBusqueda.errores[i].descripcionError);
									}
									if (!$.isEmpty($('#holding').val())){
										$('#errorHolding').text(resultadoBusqueda.errores[i].descripcionError);
									}
									if (!$.isEmpty($('#cuit').val())){
										$('#errorCuit').text(resultadoBusqueda.errores[i].descripcionError);
									}	
								} else {
									$(resultadoBusqueda.errores[i].campoError).text(resultadoBusqueda.errores[i].descripcionError);	
								}
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

	// UTILIDADES

	(function($) {
		$.isEmpty = function(obj) {
			return (!obj || $.trim(obj) === "");
		};
	})(jQuery);

	(function($, window) {
		$.fn.replaceOptions = function(options) {
			var self, $option;
			this.empty();
			self = this;
			$.each(options, function(index, option) {
				$option = $("<option></option>").attr("value", option.value).text(option.text);
				self.append($option);
			});
		};
	})(jQuery, window);

	(function($) {
		$.validacionEsNumericoOpcional = function(valor) {
			return /^[0-9]*$/.test(valor);
		};
	})(jQuery);

	(function($) {
		$.validacionFecha = function(valor) {
			if (!$.isEmpty(valor)) {
				if (/(0[1-9]|[12][0-9]|3[01])[\/](0[1-9]|1[012])[\/](19|20)\d\d/.test(valor)) {
					var parts = valor.split("/");
					var day = parseInt(parts[0], 10);
					var month = parseInt(parts[1], 10);
					var year = parseInt(parts[2], 10);
					if (year < 1000 || year > 3000 || month == 0 || month > 12) {
						return false;
					}
					var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];
					if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
						monthLength[1] = 29;
					}
					return day > 0 && day <= monthLength[month - 1];
				}
				return false;
			}
			return true;
		};
	})(jQuery);

	// CARGAR COMBOS
	$(function() {
		$("#selectAnalista").combobox();
		$("#toggle").click(function() {
			$("#combobox").toggle();
		});
	});
	
	customCombobox("custom.combobox2");
	
	$("#selectReferenteCobranza").combobox2();
	completarComboAprobadores(referentesCobranza,$("#selectReferenteCobranza"));
	$("#selectReferenteCaja").combobox2();
	completarComboAprobadores(referentesCaja,$("#selectReferenteCaja"));
	$("#selectReferenteOperacionesExternas").combobox2();
	completarComboAprobadores(referentesOperacionesExternas,$("#selectReferenteOperacionesExternas"));
	$("#selectAprobadorOperacionesEspeciales").combobox2();
	completarComboAprobadores(aprobadoresOperacionesEspeciales,$("#selectAprobadorOperacionesEspeciales"));
	
	$("#toggle").click(function() {
		$("#combobox2").toggle();
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
	(function() {

		var empresaPrev;
		var segPrev;

		$("#selectEmpresa").focus(function() {
			empresaPrev = this.value;
		});

		$("#selectEmpresa").change(function() {
			if ($.isEmpty(this.value)) {
				var options = [ {text : 'Seleccione un item...', value : ''} ];
				$("#selectSegmento").replaceOptions(options);
				$("#selectSegmento").val(options[0].value);
				
			} else { 
				if (this.value != empresaPrev) {
					empresaPrev = this.value;
					buscarSegmentos(this.value);
				}	
			}
		});

		$('#selectCliente').change(function() {
			$("#textCriterio").val("");
			
			if (this.value == "BUSQUEDA_POR_CUIT"){
				$("#textCliente").attr("maxlength", 13);
			} else {
				$("#textCriterio").attr("maxlength", 11);
			} 
		});		
		
		if ($.isEmpty($("#idTipoMedioPago").val())) {
			var medPag = $("#idTipoMedioPago").val();
			$("#refMP").prop( "disabled", true );
			$("#divSubComp").hide();
			$("#divNroSAP").hide();
		}else {
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
			if (medPag == "33" || medPag == "16" || medPag == "34" || medPag == "39" || medPag == "40"){
				$("#refMP").attr("maxlength",25);
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
			if (medPag == "REM"){
				$("#refMP").attr("maxlength",14);
				$("#refMP").prop( "disabled", false );
				$("#divSubComp").hide();
				$("#divNroSAP").hide();
			}
		}
		
		var tipoTratam = $("#idTipoTratamiento").val();
		
		if ($.isEmpty(tipoTratam)) {
			$("#divSistemaDestino").hide();
			$("#divNroAcuerdoFact").hide();
			$("#divNroTramite").hide();
			$("#divSistemaDestinoAplicacionManual").hide();
			$("#divCodOpExtAplicacionManual").hide();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
		}else if (tipoTratam == "REINTEGRO_CRED_PROX_FAC"){
			$("#divSistemaDestino").show();
			$("#divNroAcuerdoFact").show();
			$("#divNroTramite").show();
			$("#divSistemaDestinoAplicacionManual").hide();
			$("#divCodOpExtAplicacionManual").hide();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
		}else if (tipoTratam == "DEBITO_PROX_FAC"){
			$("#divSistemaDestino").show();
			$("#divNroAcuerdoFact").show();
			$("#divNroTramite").hide();
			$("#divSistemaDestinoAplicacionManual").hide();
			$("#divCodOpExtAplicacionManual").hide();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
		}else if (tipoTratam == "REINTEGRO_POR_CHEQUE" || tipoTratam == "REINTEGRO_PAGO_CUENTA_TERCEROS"
			|| tipoTratam == "REINTEGRO_CREDITO_RED_INTEL" || tipoTratam == "REINTEGRO_TRANSFERENCIA_BAN"){
			$("#divNroTramite").show();
			$("#divSistemaDestino").hide();
			$("#divNroAcuerdoFact").hide();
			$("#divSistemaDestinoAplicacionManual").hide();
			$("#divCodOpExtAplicacionManual").hide();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
		}else if (tipoTratam == "ENVIO_A_GANANCIAS" || tipoTratam == "SALDO_A_COBRAR"
			|| tipoTratam == "SALDO_A_PAGAR"){
			$("#divNroTramite").hide();
			$("#divSistemaDestino").hide();
			$("#divNroAcuerdoFact").hide();
			$("#divSistemaDestinoAplicacionManual").hide();
			$("#divCodOpExtAplicacionManual").hide();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
		} else if (tipoTratam == "APLICACION_MANUAL" ){
			$("#divNroTramite").hide();
			$("#divSistemaDestino").hide();
			$("#divSistemaDestinoAplicacionManual").show();
			$("#divNroAcuerdoFact").hide();
			$("#divCodOpExtAplicacionManual").show();
			$("#idNroTramite").attr("maxlength", 10);
			$("#idNroAcuerdoFact").attr("maxlength", 10);
			$("#idCodOpExt").attr("maxlength", 50);
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
				if (this.value == "33" || this.value == "16" || this.value == "34" || this.value == "39" || this.value == "40" || this.value == "41"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",25);
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
				if (this.value == "REM"){
					$("#refMP").val("");
					$("#refMP").attr("maxlength",14);
					$("#refMP").prop( "disabled", false );
					$("#divSubComp").hide();
					$("#divNroSAP").hide();
				}
			}
		});
		
//		if ($.isEmpty($("#idTipoTratamiento").val())) {
//			$("#divSistemaDestino").hide();
//			$("#divNroAcuerdoFact").hide();
//			$("#divNroTramite").hide();
//			$("#idNroTramite").attr("maxlength", 10);
//			$("#idNroAcuerdoFact").attr("maxlength", 10);
//		}
		
		
		$("#idTipoTratamiento").change(function() {

				$("#idSistemaDestino").val("");
				$("#idSistemaDestinoAplicacionManual").val("");
				$('#idNroTramite').val("");
				$('#idNroAcuerdoFact').val("");
				$('#idCodOpExt').val("");
				
				
				if (this.value == "REINTEGRO_CRED_PROX_FAC"){
					$("#divSistemaDestino").show();
					$("#divNroAcuerdoFact").show();
					$("#divNroTramite").show();
					$("#divSistemaDestinoAplicacionManual").hide();
					$("#divCodOpExtAplicacionManual").hide();
				}else if (this.value == "DEBITO_PROX_FAC"){
					$("#divSistemaDestino").show();
					$("#divNroAcuerdoFact").show();
					$("#divNroTramite").hide();
					$("#divSistemaDestinoAplicacionManual").hide();
					$("#divCodOpExtAplicacionManual").hide();
				}else if (this.value == "REINTEGRO_POR_CHEQUE" || this.value == "REINTEGRO_PAGO_CUENTA_TERCEROS"
					|| this.value == "REINTEGRO_CREDITO_RED_INTEL" || this.value == "REINTEGRO_TRANSFERENCIA_BAN"){
					$("#divNroTramite").show();
					$("#divSistemaDestino").hide();
					$("#divNroAcuerdoFact").hide();
					$("#divSistemaDestinoAplicacionManual").hide();
					$("#divCodOpExtAplicacionManual").hide();
				}else if ($.isEmpty(this.value) || this.value == "ENVIO_A_GANANCIAS" || this.value == "SALDO_A_COBRAR"
					|| this.value == "SALDO_A_PAGAR"){
					$("#divNroTramite").hide();
					$("#divSistemaDestino").hide();
					$("#divNroAcuerdoFact").hide();
					$("#divSistemaDestinoAplicacionManual").hide();
					$("#divCodOpExtAplicacionManual").hide();
				} else if (this.value == "APLICACION_MANUAL"){
					$("#divNroTramite").hide();
					$("#divSistemaDestino").hide();
					$("#divSistemaDestinoAplicacionManual").show();
					$("#divNroAcuerdoFact").hide();
					$("#divCodOpExtAplicacionManual").show();
				}
		});
	
//		$('#refMP').focusout(function() {
//			var select = $("#idTipoMedioPago").val();
//			// Estos caso no tiene validaciones
//			var vectorOmitir = ["33", "16", "34", "35"];
//			var valor = $('#refMP').val();
//			if (vectorOmitir.indexOf(select) < 0) {
//				if (select == "CTA" || select == "CRE") {
//					var formato = /^[0-9]{4}-[0-9]{8}$/.test(valor);
//					var formatoCALIPSO = /^[ABX]-[0-9]{4}-[0-9]{8}$/.test(valor);
//					var formatoMIC = /^[AB]-[0-9]{4}-[0-9]{8}$/.test(valor);
//					if (!formato && !formatoCALIPSO && !formatoMIC) {
//						$('#errorRefMP').text("Este campo debe respetar alguno de los siguientes formatos: �X-nnnn-nnnnnnnn� o �nnnn-nnnnnnnn�");
//						flagErrorRedMedio = true;
//					} else {
//						flagErrorRedMedio = false;
//						$('#errorRefMP').text("");
//					}			
//				} else {
//					var salida = $.validacionIsNumeric(valor);
//					if (!salida) {
//						flagErrorRedMedio = true;
//						$('#errorRefMP').text("Este campo debe contener valores num�ricos.");
//					} else {
//						flagErrorRedMedio = false;
//						
//						$('#errorRefMP').text("");
//					}
//				}
//			} else {
//				flagErrorRedMedio = false;
//				$('#errorRefMP').text("");
//			}
//		});

		$("#selectSegmento").focus(function () {
			segPrev = this.value;
		});
		$("#selectSegmento").change(function() {
			if ($.isEmpty(this.value)) {
				buscarAnalistasXDefecto();
			} else {
				if (this.value != segPrev) {
					segPrev = this.value;
					buscarAnalistasXSegmento(this.value);
				}
			};
		});
		
		if ($("#selectSegmento").val() == ''){
			buscarAnalistasXDefecto();
		} else {
			buscarAnalistasXSegmento($("#selectSegmento").val());
		}
	})();
	
	$("#seleccionarTodos").click(function () {
		var checked = $(this).prop('checked');
		 $('#cobrosEncontrados').find('.odd, .even').find('.checkSelect').each(function(index) {
			 $(this).prop('checked', checked);
		 });
		if (habilitarBtnDesapropiar('checkSelect', cobrosEncontrados)) {
			$('#btnDesapropiar').prop('disabled', false);
		} else {
			$('#btnDesapropiar').prop('disabled', true);
		}
	});
	
	$('#btnDesapropiar').click(function() {
		var cobros = new Object();
		var mensaje = mensajeDesapropiar;

		cobros.ids = [];
		cobros.idsOperacion = [];
		$('.checkSelect', cobrosEncontrados.fnGetNodes()).each(function(i, elem) {
			if ($(elem).prop('checked')) {
				$(cobrosEncontrados.fnGetData()).each(function (j, cobro) {
					if ("check" + cobro.idCobro == $(elem).prop('id')) {
						if (!$.isEmpty(cobro.desapropiacionHabilitada)) {
							cobros.ids.push(cobro.idCobro);
							cobros.idsOperacion.push(cobro.idOperacion);
						}
					}
				});
			}
		});
		
		if (cobros.ids.length > 1) {
			mensaje = mensajeDesapropiarPlural;
		}
		bootbox.confirm(mensaje, function(result) {
			if (result) {
				$('#bloqueEspera').trigger('click');
				$('#idsDesapropiar').val(cobros.ids.join(','));
				$('#idsOperacion').val(cobros.idsOperacion.join(','));
				$('#idVolver').val(true);
				$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/desapropiar";
				$('#formBusqueda')[0].submit();
			}
		});
	});
//	$('#btnVolver').click(function() {
//		$('#bloqueEspera').trigger('click');
//		$('#cobroDto')[0].action=urlAsynchronousHTTP+"/" + VUELVO_BANDEJA;
//		$('#cobroDto')[0].submit();
//	});
	
	
	if($('#idOperacionMasiva').val() > 0){
		$('#btnBuscar').trigger('click');
		$('#btnVolver').show();
	}else if(volviendoABusqueda) {
		$('#btnBuscar').click();
	}

});
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
function habilitarBtnDesapropiar(classNameChecks, table) {
	var desapropiacionHabilitada = false;
	var salir = false;
	$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
		if ($(elem).prop('checked')) {
			$(table.fnGetData()).each(function (j, cobro) {
				if ("check" + cobro.idCobro == $(elem).prop('id')) {
					if ($.isEmpty(cobro.desapropiacionHabilitada)) {
						desapropiacionHabilitada = false;
						salir = true;
						return false;
					} else {
						desapropiacionHabilitada = true;
					} 
				}
			});
		}
		if (salir) {
			return false;
		}
	});
	return desapropiacionHabilitada;
};
function checking(leaderCheckId, classNameChecks, table, searchBtnId) {
	if (habilitarBtnDesapropiar(classNameChecks, table)) {
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
function buscarSegmentos(empresa) {
	$('#bloqueEspera').trigger('click');
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'cobro-busqueda/buscarSegmentosCobros',
		"data" : {
			idEmpresa : empresa
		},
		"success" : function(result) {
			if (result.length > 0) {
				var options = [ {text : 'Seleccione un item...',value : ''} ];
				$.each(result,function(index,option) { options.push(option); });
				$("#selectSegmento").replaceOptions(options);
				if (result.length == 1) {
					$("#selectSegmento").val(result[0].value).trigger('change');
				} else {
					$("#selectCopropietario").replaceOptions(options);
					$("#selectSegmento").val(options[0].value);
				}
			}
			ocultarBloqueEspera();
		}
	});
}

function buscarAnalistasXDefecto() {
	$('#bloqueEspera').trigger('click');
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
					options.push(option);
					if(option.value === cobroFiltroAnalista){
						cobFiltroAnalista = option.value;
					}
				});
				idAnalista = null;
				$("#selectAnalista").replaceOptions(options);
				if ($.isEmpty($("#selectSegmento").val())) {
					setearOpcionAnalista('');
				} else {
					setearOpcionAnalista(cobFiltroAnalista);
				}
			}
			if (!volviendoABusqueda) {
				ocultarBloqueEspera();
			}
		}
	});
}

function buscarAnalistasXSegmento(segmento) {
	$('#bloqueEspera').trigger('click');
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'cobro-busqueda/buscarAnalistaCobros',
		"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : segmento},
		"success" : function(result) {
			if (result.length == 1) {
				result[0].text = result[0].value + " - " + result[0].text;
				$("#selectAnalista").replaceOptions(result);
			} else {
				var options = [{text: 'Seleccione un item...', value : ''}];
				var cobroFiltroAnalista = options[0].value;
				$.each(result, function(index, option) {
					option.text = option.value + " - " + option.text;
					options.push(option);
					
					if(option.value === idAnalista){
						cobroFiltroAnalista = option.value;
					}
				});
				idAnalista = null;
				$("#selectAnalista").replaceOptions(options);
				setearOpcionAnalista(cobroFiltroAnalista);
			}
			if (!volviendoABusqueda) {
				ocultarBloqueEspera();
			}
			
		}
	});
}

function completarComboAprobadores(listaAprobadores, combo) {
	if(!$.isEmpty(listaAprobadores)){
		var options = [{text: 'Seleccione un item...', value : ''}];
		var cobFiltroAprobador = options[0].value;
		$.each(listaAprobadores, function(index, option) {
			option.text = option.tuid + " - " + option.nombreCompleto;
			option.value = option.tuid;
			options.push(option);
			if(option.tuid === cobroFiltroAnalista){
				cobFiltroAprobador = option.tuid;
			}
		});
		combo.replaceOptions(options);
	}
}

function setearOpcionAnalista(opcionSelectValue) {
	$('#selectAnalista').val(opcionSelectValue); 
	$('.ui-autocomplete-input').val($('#selectAnalista option:selected').text());
};

function detalleCobro(idCobro,idCobroPadre,nombreArchivo) {
	$('#bloqueEspera').trigger('click');
	$('#idCobro').val(idCobro);
	$('#idCobroPadre').val(idCobro,idCobroPadre);
	$('#nombreArchivo').val(nombreArchivo);
	$('#opcion').val("D");
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/cobro-detalle-aprobacion";
	$('#formBusqueda')[0].submit();
};

function editarCobro(idCobro,idCobroPadre) {
	$('#bloqueEspera').trigger('click');
	$('#idCobro').val(idCobro);
	$('#idCobroPadre').val(idCobro,idCobroPadre);
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/cobros-configuracion-edicion";
	$('#formBusqueda')[0].submit();
};

function revertirCobro(cobro) {
    $('#bloqueEspera').trigger('click');
    $('#idCobro').val(cobro);
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : 'cobro-busqueda/validarReversion',
		"data" : {
			idCobro : cobro
		},
		"success" : function(result) {
			
			if (result != null) {
				if (result.success) {
				    $('#formBusqueda')[0].action = urlAsynchronousHTTP + "/descobro-reversion-configuracion";
				    $('#formBusqueda')[0].submit();
				} else {
					$(result.campoError).text(result.descripcionError);
					ocultarBloqueEspera();
				}
			}
		}
	});
};

function anularCobro(idCobro) {
	bootbox.confirm(mensajeAnular, function(result) {
		if (result) {
	$('#bloqueEspera').trigger('click');
	$('#idCobro').val(idCobro);
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/cobro-anulacion";
	$('#formBusqueda')[0].submit();
		}
	});
};

function historialCobro(idCobro,idOperacion,idCobroPadre) {
	$('#bloqueEspera').trigger('click');
	$('#idCobro').val(idCobro);
	$('#idCobroPadre').val(idCobroPadre);
	$('#idOperacionFormateado').val(idOperacion);
	$('#formBusqueda')[0].action=urlAsynchronousHTTP+"/cobro-historial";
	$('#formBusqueda')[0].submit();
};

function volverBusqueda() {
	$('#goBack').val("true");
	$('#formBusqueda')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formBusqueda')[0].submit();
};
//verifyAllSelectedCheck(\'seleccionarTodos\',\'checkSelect\', cobrosEncontrados, \'btnDesapropiar\')

function actualizarFiltroReferentes(){
	if(volviendoABusqueda) {
		if(!isUndefinedNullDashEmptyOrFalse(cobroFiltroReferenteCobranza)){
			$("#selectReferenteCobranza").combobox2("refresh", cobroFiltroReferenteCobranza, 'selectReferenteCobranza');
		}
		if(!isUndefinedNullDashEmptyOrFalse(cobroFiltroReferenteCaja)){
			$("#selectReferenteCaja").combobox2("refresh", cobroFiltroReferenteCaja, 'selectReferenteCaja');
		}
		if(!isUndefinedNullDashEmptyOrFalse(cobroFiltroReferenteOperacionesExternas)){
			$("#selectReferenteOperacionesExternas").combobox2("refresh", cobroFiltroReferenteOperacionesExternas, 'selectReferenteOperacionesExternas');
		}
		if(!isUndefinedNullDashEmptyOrFalse(cobroFiltroReferenteOperacionesEspeciales)){
			$("#selectAprobadorOperacionesEspeciales").combobox2("refresh", cobroFiltroReferenteOperacionesEspeciales, 'selectAprobadorOperacionesEspeciales');
		}
	}
}
