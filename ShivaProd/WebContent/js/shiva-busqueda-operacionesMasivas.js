var flagErrorRedMedio = false;
var tablaOperacionesMasivas = null;
var volviendoABusqueda = false;

$(document).ready(function() {

	var SCROLL_Y = '300px';

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
	
	var operacionesMasivasSettings = {
			 dom : 'Bfrtip',
			"scrollX": "100%",	
	        "scrollY": "500px",
		    "sScrollXInner": "110%",
		    "bScrollCollapse": true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"autoWidth" : true,
			buttons: [{
					extend:'excelHtml5',
					text:'Excel',
					title: "Busqueda_De_Operaciones_Masivas",
					className: 'excelbtn',
					"mColumns": "visible"
				}]
			,
			"order": [[ 14, "desc" ]],
			"aoColumns" : [ 
                                { "targets" : 0,
                                    "searchable" : false,
                                    "bSortable" : false,
                                    "data" : null,
                                    "render" : function(data, type, row) {
                                	if (type === 'display') {
                                	    var idOpe = data.idOperacionMasiva;
                                	    var nomArch = data.nombreArchivo;
                                	    return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
                                	    	var idOpe = data.idOperacionMasiva;
                                			var nomArch = data.nombreArchivo;
                                			return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-edit bigger-120"></i></button></div>';
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
                                	    	var idOpe = data.idOperacionMasiva;
                                	    	var nomArch = data.nombreArchivo;
                                	    	return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-remove bigger-120"></i></button></div>';
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
                                	    var idOpMas = data.idOperacionMasiva;
                                	    return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialOperacionMasiva('+idOpMas+')"><i class="icon-list-alt bigger-120"></i></button></div>';
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
                                		var idOpMas = data.idOperacionMasiva;
                                		return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Descargar" onclick="descargarArchivo('+idOpMas+')"><i class="icon-download bigger-120"></i></button></div>';
                                	    
                                	}
                                	return null;
                                    }
                                },
                                { "targets" : 0,
                                    "searchable" : false,
                                    "bSortable" : false,
                                    "data" : null,
                                    "render" : function(data, type, row) {
                                	if (data.mostrarBotonVerCobro == true) {
                                		var idOpMas = data.idOperacionMasiva;
                                		return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver Cobros" onclick="verCobros('+idOpMas+')"><i class="icon-th-list bigger-120"></i></button></div>';
                                	    
                                	}
                                	return null;
                                    }
                                },
                {"mData" : "idOperacionMasiva"},
				{"mData" : "nombreArchivo"},
				{"mData" : "descripcionEstado"},
				{"mData" : "tipoDeOperacionMasiva"},
				{"mData" : "fechaUltimaModificacionFormatoJson"},
				{"mData" : "descripcionMotivo"},
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
				{"mData" : "fechaAltaFormatoJson"},
				{"mData" : "fechaDerivacionFormatoJson"},
				{"mData" : "fechaAutorizacionFormatoJson"},
				{"mData" : "fechaProcesamientoFormatoJson"},
				{"mData" : "registrosIngresados"},
				{"mData" : "registrosProcesadosCorrectamente"},
				{"mData" : "registrosProcesadosConError"},
				 { "targets" : 0,
                     "searchable" : false,
                     "bSortable" : false,
                     "data" : null,
                     "render" : function(data, type, row) {
                 	if (type === 'display') {
                 	    var idOpe = data.idOperacionMasiva;
                 	    var nomArch = data.nombreArchivo;
                 	    return '<div class="visible-md visible-lg hidden-sm hidden-xs ar-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver detalle" onclick="detalleOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-eye-open bigger-120"></i></button></div>';
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
                 	    	var idOpe = data.idOperacionMasiva;
                 			var nomArch = data.nombreArchivo;
                 			return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Editar" onclick="editarOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-edit bigger-120"></i></button></div>';
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
                 	    if (data.mostrarBotonAnular == true ) {
                 	    	var idOpe = data.idOperacionMasiva;
                 	    	var nomArch = data.nombreArchivo;
                 	    	return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Anular" onclick="anularOperacionMasiva('+idOpe+',\''+nomArch+'\')"><i class="icon-remove bigger-120"></i></button></div>';
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
                 	    var idOpMas = data.idOperacionMasiva;
                 	    return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Historial" onclick="historialOperacionMasiva('+idOpMas+')"><i class="icon-list-alt bigger-120"></i></button></div>';
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
                 		var idOpMas = data.idOperacionMasiva;
                 		return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Descargar" onclick="descargarArchivo('+idOpMas+')"><i class="icon-download bigger-120"></i></button></div>';
                 	    
                 	}
                 	return null;
                     }
                 },
                 { "targets" : 0,
                     "searchable" : false,
                     "bSortable" : false,
                     "data" : null,
                     "render" : function(data, type, row) {
                 	if (data.mostrarBotonVerCobro == true) {
                 		var idOpMas = data.idOperacionMasiva;
                 		return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Ver Cobros" onclick="verCobros('+idOpMas+')"><i class="icon-th-list bigger-120"></i></button></div>';
                 	    
                 	}
                 	return null;
                     }
                 }
					 
			],
			'columnDefs': [	//targets son los th con las class date o importe
               	{type: 'latam_datetime', targets: 'dateTimes'},
               	{type: 'latam_datetime_seconds', targets: 'datetimeSeconds'},
               	{type: 'comparador-currency', targets: 'importe'}
			]
	};
	
	tablaOperacionesMasivas = $("#tablaOperacionesMasivas").dataTable(operacionesMasivasSettings);

	
		
		$('#btnBuscar').click(function() {
			
			$('#bloqueEspera').trigger('click');
		
			var selectIdOperacionMasiva = $('#selectIdOperacionMasiva').val();
			if (!$.isEmpty(selectIdOperacionMasiva) && !$.validacionIsNumeric(selectIdOperacionMasiva)) {
			    $("#errorIdOperacionMasiva").text("Este campo debe contener valores numéricos.");
			    ocultarBloqueEspera();
			    return;
			}
			if (flagErrorRedMedio) {
				ocultarBloqueEspera();
				flagErrorRedMedio = false;
				return;
			}
			var operacionMasivaFiltro = {
					idEmpresa : $('#selectEmpresa').val(),
					idSegmento : $('#selectSegmento').val(),
					idEstado : $('#selectEstado').val(),
					idMotivo : $('#selectMotivo').val(),
					idAnalista : $('#selectAnalista').val(),
					idTipoOperacionMasiva : $("#selectTipoOperacionMasiva").val(),
					idOperacionMasiva : $('#selectIdOperacionMasiva').val(),					
					fechaDesde : $('#fechaDesde').val(),
					fechaHasta : $('#fechaHasta').val()
			};

			$.ajax({
				"type" : "POST",
				"url": "operacion-masiva-busqueda/buscar-OperacionesMasivas",
				"dataType": "json", 
				"data": JSON.stringify(eval(operacionMasivaFiltro)), 
				"contentType": "application/json",
				"mimeType": "application/json",
				"success" : function(resultadoBusqueda) {
					
					if (resultadoBusqueda != null) {
						tablaOperacionesMasivas.fnClearTable();

						if (resultadoBusqueda.success) {
							if (resultadoBusqueda.aaData != null && resultadoBusqueda.aaData.length > 0){
								tablaOperacionesMasivas.fnAddData(resultadoBusqueda.aaData,true);
								$('#seleccionarTodos').prop('disabled', false);
							}
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

	
	// CARGAR COMBOS
	$(function() {
		$("#selectAnalista").combobox();
		$("#toggle").click(function() {
			$("#combobox").toggle();
		});
	});
	
	
	//ssssssssssssssss
	
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
				var options = [{text : 'Seleccione un item...', value : ''}];
				$("#selectSegmento").replaceOptions(options);
				$("#selectSegmento").val(options[0].value);
				
			} else { 
				if (this.value != empresaPrev) {
					empresaPrev = this.value;
					buscarSegmentos(this.value);
				}	
			}
		});

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
	
	function buscarAnalistasXSegmento(segmento) {
		$('#bloqueEspera').trigger('click');
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : "operacion-masiva-busqueda/buscarAnalistaOperacionesMasivas",
			"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : segmento},
			"success" : function(result) {
				if (result.length == 1) {
					result[0].text = result[0].value + " - " + result[0].text;
					$("#selectAnalista").replaceOptions(result);
				} else {
					var options = [{text: 'Seleccione un item...', value : ''}];
					var filtroAnalista = options[0].value;
					$.each(result, function(index, option) {
						option.text = option.value + " - " + option.text;
						options.push(option);
						
						if(option.value === idAnalista){
							filtroAnalista = option.value;
						}
					});
					idAnalista = null;
					$("#selectAnalista").replaceOptions(options);
					setearOpcionAnalista(filtroAnalista);
				}
				if (!volviendoABusqueda) {
					ocultarBloqueEspera();
				}
			}
		});
	}

	function buscarAnalistasXDefecto() {
		$('#bloqueEspera').trigger('click');
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'operacion-masiva-busqueda/buscarAnalistaOperacionesMasivas',
			"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : 'TODOS_LOS_SEGMENTOS' },
			"success" : function(result) {
				if (result.length == 1) {
					result[0].text = result[0].value + " - " + result[0].text;
					$("#selectAnalista").replaceOptions(result);
				} else {
					var options = [{text: 'Seleccione un item...', value : ''}];
					var filtroAnalista = options[0].value;
					$.each(result, function(index, option) {
						option.text = option.value + " - " + option.text;
						options.push(option);
//						if(option.value === cobroFiltroAnalista){
//							filtroAnalista = option.value;
//						}
					});
					idAnalista = null;
					$("#selectAnalista").replaceOptions(options);
					if ($.isEmpty($("#selectSegmento").val())) {
						setearOpcionAnalista('');
					} else {
						setearOpcionAnalista(filtroAnalista);
					}
				}
				if (!volviendoABusqueda) {
					ocultarBloqueEspera();
				}
			}
		});
	}

	function setearOpcionAnalista(opcionSelectValue) {
		$('#selectAnalista').val(opcionSelectValue); 
		$('.ui-autocomplete-input').val($('#selectAnalista option:selected').text());
	};
	
	if(volviendoABusqueda) {
		$('#btnBuscar').click();
	}
	
	
	if($('#selectIdOperacionMasiva').val() > 0){
		$('#btnBuscar').trigger('click');
	}
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

});
	
function detalleOperacionMasiva(idOperacionMasiva,nombreArchivo) {
	$('#bloqueEspera').trigger('click');
	$('#idOperacionMasiva').val(idOperacionMasiva);
	$('#opcion').val("D");
	$('#nombreArchivo').val(nombreArchivo);
	$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/operacion-masiva-detalle-aprobacion";
	$('#operacionMasivaDto')[0].submit();
};


function editarOperacionMasiva(idOperacionMasiva,nombreArchivo) {
	$('#bloqueEspera').trigger('click');
	$('#idOperacionMasiva').val(idOperacionMasiva);
	$('#nombreArchivo').val(nombreArchivo);
	$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/operacion-masiva-edicion";
	$('#operacionMasivaDto')[0].submit();
};


function anularOperacionMasiva(idOperacionMasiva, nombreArchivo) {
	var mensaje = mensajeAnular.replace("{0}", nombreArchivo);
	bootbox.confirm(mensaje, function(result) {
		if (result) {
			$('#bloqueEspera').trigger('click');
			$('#idOperacionMasiva').val(idOperacionMasiva);
			$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/operacion-masiva-anular";
			$('#operacionMasivaDto')[0].submit();
		}
	});
};


function historialOperacionMasiva (idOperacionMasiva) {
	$('#bloqueEspera').trigger('click');
	$('#idOperacionMasiva').val(idOperacionMasiva);
	$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/operacion-masiva-historial";
	$('#operacionMasivaDto')[0].submit();
};



function descargarArchivo(idOperacionMasiva) {
	$('#bloqueEspera').trigger('click');
	$('#idOperacionMasiva').val(idOperacionMasiva);
	$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/operacion-masiva-descargar";
	$('#operacionMasivaDto')[0].submit();
	ocultarBloqueEspera();
};

function verCobros(idOperacionMasiva){
	$('#bloqueEspera').trigger('click');
	$('#idOperacionMasiva').val(idOperacionMasiva);
	$('#volver').val('/operacion-masiva-busqueda');
	$('#operacionMasivaDto')[0].action=urlAsynchronousHTTP+"/cobro-busqueda";
	$('#operacionMasivaDto')[0].submit();
	
}
