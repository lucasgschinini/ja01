var segPrev = '';
var selectCopropietarioEdicion = '';
// funcion llamada en el ready para registrar los eventos que utilza la solapa
// Cheques



var customPagCheques_next = null;
var customPagCheques_previous = null;

function registrarEventoSolapaCheques() {
	
	$("#selectSegmento").focus(function() {
		segPrev = this.value;
	}).change(function() {
		if ($.isEmpty(this.value)) {
			var options = [{
				text : 'Seleccione un item...',
				value : ''
			}];
			$("#selectCopropietario").replaceOptions(options);
			$("#selectCopropietario").val(options[0].value);
		} else if (this.value != segPrev) {
			$('#bloqueEspera').trigger('click');
			segPrev = this.value;
			$.ajax({
				"dataType" : 'json',
				"type" : "GET",
				"url" : 'legajo-cheque-rechazado/buscarCopropietarios',
				"data" : {
					idEmpresa : $("#selectEmpresa").val(),
					copropietario : legajoChequeRechazado.copropietario,
					idSegmento : this.value
				},
				"success" : function(result) {
					var options = [{
						text : 'Seleccione un item...',
							value : ''
					}];
					$.each(result, function(index, option) {
						options.push(option);
					});
					$("#selectCopropietario").replaceOptions(options);
					if (!isUndefinedNullDashEmptyOrFalse(edicion) && !isUndefinedNullDashEmptyOrFalse(legajoChequeRechazado)) {
						if (!isUndefinedNullDashEmptyOrFalse(selectCopropietarioEdicion)) {
							$("#selectCopropietario").val(selectCopropietarioEdicion);
						} else {
							$("#selectCopropietario").val(options[0].value);
						}
					} else {
						$("#selectCopropietario").val(options[0].value);
					}
					
						ocultarBloqueEspera();
				}
			});
		}
		ocultarBloqueEspera();
	});
	
	$("#fechaNotificacionRechazo").datepicker({firstDay : 1});
	$("#fechaRechazo").datepicker({firstDay : 1});
	
	
	$("#fechaVencimientoCheque").datepicker({firstDay : 1});
	$("#fechaAltaDesdeCheque").datepicker({firstDay : 1});
	$("#fechaAltaHastaCheque").datepicker({firstDay : 1});
	
	$("#edFechaEmision").datepicker({firstDay : 1});
	$("#edFechaDeposito").datepicker({firstDay : 1});
	$("#edFechaVencimiento").datepicker({firstDay : 1});

	// Banco Combo autocompletado
	

		customCombobox("custom.combobox2");
		$("#descripcionBanco").combobox2();
		$("#edDescripcionBancoOrigen").combobox2();
		$("#edBancoDeposito").combobox2();
	
		$("#toggle").click(function() {
			$("#combobox2").toggle();
		});
	
		
/******************************************************************************/
	$('#sistemaGestor').change(function() {
	if ($.isEmpty(this.value)) {
		// MUESTRO TODOS!!!
		$('#divFechaVencimientoCheque').show();
		$('#divErrorfechaVencimientoCheque').show();
		
		$('#nroCheque').attr('maxlength', 8);
		
	} else if (SISTEMA_SHIVA_DESC_CORTA === this.value) {
		// Muestro los campos de busque solo para SHIVA
		$('#divFechaVencimientoCheque').show();
		$('#divErrorfechaVencimientoCheque').show();
		
		$('#nroCheque').attr('maxlength', 8);
		
	} else if (SISTEMA_ICE_DESC_CORTA === this.value) {
		// Muestro los campos de busque solo para ICE
		$('#divFechaVencimientoCheque').hide();
		$('#divErrorfechaVencimientoCheque').hide();
		
		$('#nroCheque').attr('maxlength', 8);
		
	}
});
/******************************************************************************/
	
		$('#codigoBanco').change(function () {
			
			$('#descripcionBanco').combobox2("refresh", $('#codigoBanco option:selected').attr('idAgrupador'), 'descripcionBanco');
			if ($.isEmpty(this.value)) {
				vaciarCombo('codigoBanco');
				$('#codigoBanco').append(combos.bancoOrigen);
				$('#codigoBanco').val("");
			}
		});
	
		$('#edCodigoBancoOrigen').change(function () {
			$("#errorEdCodigoBancoOrigen").text("");
			$("#errorEdDescripcionBancoOrigen").text("");
			$('#edDescripcionBancoOrigen').combobox2("refresh", $('#edCodigoBancoOrigen option:selected').attr('idAgrupador'), 'edDescripcionBancoOrigen');
			if ($.isEmpty(this.value)) {
				vaciarCombo('edCodigoBancoOrigen');
				$('#edCodigoBancoOrigen').append(combos.edbancoOrigen);
				$('#edCodigoBancoOrigen').val("");
			}
		});

		$('#descripcionBanco').combobox2({
			'select': function(event, item) {
				vaciarCombo('codigoBanco');
				var optios = crearOpcionesByAgrupador(item.item.value, "codigoBanco");
				if (optios.length > 1) {
					$('#codigoBanco').append(combos.bancoOrigen[0]);
				}
				$('#codigoBanco').append(optios);
			},
			'vacio': function(event) {
				vaciarCombo('codigoBanco');
				$('#codigoBanco').append(combos.bancoOrigen);
				$('#codigoBanco').val("");
			}
		});
		$('#edDescripcionBancoOrigen').combobox2({
			'select': function(event, item) {
				$("#errorEdCodigoBancoOrigen").text("");
				$("#errorEdDescripcionBancoOrigen").text("");
				vaciarCombo('edCodigoBancoOrigen');
				var optios = crearOpcionesByAgrupador(item.item.value, "edCodigoBancoOrigen");
				if (optios.length > 1) {
					$('#edCodigoBancoOrigen').append(combos.edbancoOrigen[0]);
				}
				$('#edCodigoBancoOrigen').append(optios);
			},
			'vacio' : function(event) {
				vaciarCombo('edCodigoBancoOrigen');
				$('#edCodigoBancoOrigen').val('');
				$('#edCodigoBancoOrigen').append(combos.edbancoOrigen);
				$('#edCodigoBancoOrigen').val("");
			},
		});
/******************************************************************************/
	// Combo Banco Desposito
	$('#edAcuerdoCheque').change(function () {
		vaciarCombo('edBancoDeposito');
		vaciarCombo('edCuentaDeposito');
		if ($.isEmpty(this.value)) {
			if ($('#edAcuerdoCheque option').length != combos.acuedo) {
				vaciarCombo('edAcuerdoCheque');
				$('#edAcuerdoCheque').append(combos.acuerdo);
			}
			$('#edBancoDeposito').append(combos.banco);
			$("#edCuentaDeposito").append(combos.bancoCuenta);
			$('#edBancoDeposito').combobox2("refresh", '', 'edBancoDeposito');
			$('#edCuentaDeposito').val('');
			$('#edAcuerdoCheque').val('');
		} else {
			crearOpcionesByAcuerdo(this.value);
		}
	});

	$('#edBancoDeposito').combobox2({
		'select': function(event, item) {
			vaciarCombo('edAcuerdoCheque');
			vaciarCombo('edCuentaDeposito');
			crearOpcionesByBanco(this.value);
		},
		'vacio' : function(event) {
			vaciarCombo('edAcuerdoCheque');
			vaciarCombo('edCuentaDeposito');
			vaciarCombo('edBancoDeposito');
			$('#edAcuerdoCheque').append(combos.acuerdo);
			$("#edCuentaDeposito").append(combos.bancoCuenta);
			$("#edBancoDeposito").append(combos.banco);
//			$('#edBancoDeposito').combobox2("refresh", '', 'edBancoDeposito');
			$('#edAcuerdoCheque').val("");
			$("#edCuentaDeposito").val("");
		},
	});
	$('#edCuentaDeposito').change(function () {
	
		
		vaciarCombo('edBancoDeposito');
		vaciarCombo('edAcuerdoCheque');
		
		if ($.isEmpty(this.value)) {
			if ($('#edCuentaDeposito option').length != combos.acuedo) {
				vaciarCombo('edCuentaDeposito');
				$('#edCuentaDeposito').append(combos.acuerdo);
				
			}
			$('#edBancoDeposito').append(combos.banco);
			$("#edAcuerdoCheque").append(combos.bancoCuenta);
			$('#edBancoDeposito').combobox2("refresh", '', 'edBancoDeposito');
			$('#edAcuerdoCheque').val('');
			$('#edCuentaDeposito').val('');
		} else {
			var selectOption = this.value;
			vaciarCombo('edCuentaDeposito');
			selectOpcionByCuenta(selectOption);
		}
	});

	// fin Combo Banco Desposito
	function vaciarCombo(comboName) {
		$('#' + comboName).empty();
	}
/******************************************************************************/
	$('#selectCliente').change(function() {
		clearInputText('searchCriteraCliente');
		$("#errorTextCriterio").text("");
		$("#textCriterio").removeAttr('disabled');
		if ($.isEmpty(this.value)) {
			$("#textCriterio").attr('disabled', 'disabled');
		} else if (this.value == "BUSQUEDA_POR_CUIT") {
			$("#textCriterio").attr("maxlength", 13);
		} else {
			$("#textCriterio").attr("maxlength", 11);
		}
	});
	// Busqueda de cheques
	$("#textCriterio").focusout(function() {
		if ($.isEmpty($('#selectCliente').val())) {
			if (!$.isEmpty($("#textCriterio").val())) {
				$("#errorTextCriterio").text(
					"Debe seleccionar un criterio."
				);
			} else {
				$("#errorTextCriterio").text("");
			}
		} else {
			$("#errorTextCriterio").text("");
			var opcion = $('#selectCliente').val();
			var value = $("#textCriterio").val();
			if ("BUSQUEDA_POR_CUIT" === opcion && !$.isEmpty(value)) {
				if (!$.cuitValidarFormato(value)) {
					$("#errorTextCriterio").text(
						"Este campo debe respetar los formatos 99-99999999-9 o 99999999999."
					);
				} else if(!$.cuitValidarDigitoVerificador(value)){
					$("#errorTextCriterio").text(
							"El digito verificador es incorrecto."
						);
				}	else if($.cuitValidarDosPrimerodDigitos(value)){
					$("#errorTextCriterio").text(
							"Los primeros 2 digitos son incorrectos."
						);
				}
			} else {
				if (!$.validacionIsNumeric(value) && !$.isEmpty(value)) {
					$("#errorTextCriterio").text(
						"El campo debe tener un valor numérico."
					);
				}
			}
		}
	});
	
	$('#importesDesde').focusout(function(){
		validarImporte(this.id);
	});

	$('#importesHasta').focusout(function(){
		validarImporte(this.id);
	});
	
/******************************************************************************/
	$('#edTipoCheque').change(function() {
		$("#errorEdTipoCheque").text("");
		if (this.value == 6) {
			$('.verFechaVencimiento').show();
			$('#edFechaVencimiento').val('');
		} else {
			$('.verFechaVencimiento').hide();
			$('#edFechaVencimiento').val('');
		}
	});
/******************************************************************************/
	$('#tablaBusquedaCheques tbody').on('click', 'button', function() {
		limpiarErroresEdicionMaunial();
		clearSelectInput('idChequesEdicionManual');
		completarValoresDefaultEdicionManual();
		$('#edRazonSocialClienteLegado').attr('disabled', false);
		if (!$.isEmpty(tablas.tablaChequesSeleccionados.fnGetData())) {

		} else {
			var idPantalla = $(this).attr('idPantalla');
			$("#descripcionBanco").combobox2("destroy");
			$(tablas.tablaBusquedaCheques.fnGetData()).each(
				function(j, chequeRechazado) {
					if (chequeRechazado.idPantalla === idPantalla) {
						tablas.tablaChequesSeleccionados.fnAddData(chequeRechazado, true);
						datosTablas.tablaChequesSeleccionados.push(chequeRechazado);
						// Griso e inabilito busqueda
						completarEdicionManual(chequeRechazado);
						completarMonto('0,00', chequeRechazado.montoARevertir);
						$('#btnBuscarCheques').attr('disabled', true);
						
						$('#searchCriterioCheque :input').attr('disabled', true);
						$('#searchCriterioCheque :button').attr('disabled', true);

						$('.' + BTN_NO_CLASS_PLUS +  idPantalla).attr('disabled', true).html(HTML_ICON_BLANK);
						
						if ($('#customPagCheques_next').hasClass('active')) {
							customPagCheques_next = true;
							$('#customPagCheques_next').addClass('disabled');
							$('#customPagCheques_next').removeClass('active');
						} else {
							customPagCheques_next = false;
						}
						if ($('#customPagCheques_previous').hasClass('active')) {
							customPagCheques_previous= true;
							$('#customPagCheques_previous').addClass('disabled');
							$('#customPagCheques_previous').removeClass('active');
						} else {
							customPagCheques_previous = false;
						}
					
						return false;
					}
				}
			);
		}
	});
	$('#tablaChequesSeleccionados tbody').on('click', 'button', function() {
		$('#bloqueEspera').trigger('click');
			if ($.isEmpty(tablas.tablaChequesSeleccionados.fnGetData())) {
			
			} else {
				var idPantalla = $(this).attr('idPantalla');
				$('#descripcionBanco').combobox2('create');
				$(tablas.tablaChequesSeleccionados.fnGetData()).each(
					function(j, chequeRechazado) {
						if (chequeRechazado.idPantalla == idPantalla) {
							tablas.tablaChequesSeleccionados.fnDeleteRow(j);
							$('.' + BTN_NO_CLASS_PLUS +  idPantalla).attr('disabled', true).html(HTML_ICON_PLUS);
							//Limpio Edicion manual
							clearSelectInput('idChequesEdicionManual');
							// Seteo valores por defecto en edicion manual;
							tablas.tablaChequesSeleccionados.fnAdjustColumnSizing(true);
							datosTablas.tablaChequesSeleccionados.length = 0;
							completarValoresDefaultEdicionManual();
							$('#searchCriterioCheque :input').attr('disabled', false);
							$('#searchCriterioCheque :button').attr('disabled', false);
							$('#btnBuscarCheques').attr('disabled', false);
							limpiarErroresEdicionMaunial();
							
							
							if (customPagCheques_next) {
								$('#customPagCheques_next').removeClass('disabled');
								$('#customPagCheques_next').addClass('active');
							}
							if (customPagCheques_previous) {
								$('#customPagCheques_previous').removeClass('disabled');
								$('#customPagCheques_previous').addClass('active');
							}
							return false;
						}
					});
			}
			setTimeout(function() {
				ocultarBloqueEspera();
			}, 500);
		});
/******************************************************************************
* Busqueda de Cheques 
*******************************************************************************/
	$('#btnBuscarCheques').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#errorRespuestaCheques').val('');
		var idBanco = $('#codigoBanco').val();
		var idBancos = [];
		if (
			$.isEmpty(idBanco)
		) {
			if (!$.isEmpty($('#descripcionBanco').val())) {
			idBancos = returnIdBancosByAgrupador($('#descripcionBanco').val());
			}
		} else {
			idBancos = idBanco;
		}
		var validacionOk = true;
		
		if(!validarFecha('fechaAltaDesdeCheque',false)){
			validacionOk = false;
		}
		if(!validarFecha('fechaAltaHastaCheque',false)){
			validacionOk = false;
		}
		if(!validarFecha('fechaVencimientoCheque',false)){
			validacionOk = false;
		}
		if(!validarImporte('importesDesde')){
			validacionOk = false;
		}
		if(!validarImporte('importesHasta')){
			validacionOk = false;
		}
		
		if ($.isEmpty($('#selectCliente').val())) {
			if (!$.isEmpty($("#textCriterio").val())) {
				$("#errorTextCriterio").text("Debe seleccionar un criterio.");
				validacionOk = false;
			} else {
				$("#errorTextCriterio").text("");
			}
		} else {
			$("#errorTextCriterio").text("");
			var opcion = $('#selectCliente').val();
			var value = $("#textCriterio").val();
			if ("BUSQUEDA_POR_CUIT" === opcion && !$.isEmpty(value)) {
				if (!$.cuitValidarFormato(value)) {
					$("#errorTextCriterio").text("Este campo debe respetar los formatos 99-99999999-9 o 99999999999.");
					validacionOk = false;
				}else if(!$.cuitValidarDigitoVerificador(value)){
					$("#errorTextCriterio").text("El digito verificador es incorrecto.");
					validacionOk = false;
				} else if($.cuitValidarDosPrimerodDigitos(value)){
					$("#errorTextCriterio").text("Los primeros 2 digitos son incorrectos.");
					validacionOk = false;
				}
			} else {
				if (!$.isEmpty(value)){
					if (!$.validacionIsNumeric(value)) {
						$("#errorTextCriterio").text("El campo debe tener un valor numérico.");
						validacionOk = false;
					}
					
				} else {
					$("#errorTextCriterio").text("El campo debe tener un valor numérico.");

					validacionOk = false;
				}
			}
		}

//		if (!validarImporteValido(importe)) {
//			$("#errorImporte").text("Este campo debe respetar el formato 999.999.999,99 o 999999999,99");
//			validacionOk = false;
//		}
		
		if (validacionOk) {
			var chequeFiltro = {
				fechaDesde: $('#fechaAltaDesdeCheque').val(),
				fechaHasta: $('#fechaAltaHastaCheque').val(),
				importeDesde: $('#importesDesde').val(),
				importeHasta: $('#importesHasta').val(),
				fechaVencimiento:$('#fechaVencimientoCheque').val(),
				idBanco: idBanco,
				idBancos: (!$.isEmpty(idBancos)? idBancos : ''), 
				moneda : 'PES',
				sistema: $('#sistemaGestor').val(),
				referenciaDelValorFiltroLike: $('#nroCheque').val(),
				criterio: $('#selectCliente').val(),
				busqueda: $('#textCriterio').val(),
				idEmpresa: $('#selectEmpresa').val()
			};
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : 'legajo-cheque-rechazado/buscarCheques',
				"data" : JSON.stringify(eval(chequeFiltro)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						tablas.tablaBusquedaCheques.fnClearTable();
						actualizarInfoTabla(
							tablas.tablaBusquedaCheques,
							result,
							'customPagCheques',
							null,
							'errorRespuestaCheques',
							CANTIDAD_POR_PAGINA_CHEQUE
						);
						
						
					} else {
						if (!$.isEmpty(result.errorMensaje)) {
							$('#errorRespuestaCheques').text(result.errorMensaje);
						} else if (!$.isEmpty(result.errores)) {
							for (var i = 0, max = result.errores.length; i < max; i++) {
								$(result.errores[i].campoError).text(result.errores[i].descripcionError);
							}
						}
					}
					ocultarBloqueEspera();
				}
			});
			
		} else {
			ocultarBloqueEspera();	
		}
	});
/******************************************************************************/
	$("#edInputCodCliente").keydown(function(event) {
		$("#wsConsultaClienteSiebel").css('display', 'none');
		var teclasQNoEscriben = ['27','37','38','39','40','16','17','18','19','20','33','34','35','36','45','144','145','91','92','93', '112','113','114','115','116','117','118','119','120','121','122','123' ];
		//Busco si la tecla presionada fue una tecla que no escribe en el campo de texto	 
		var flag = false;			    
		for (var i = 0; i < teclasQNoEscriben.length; i++) {
			if (teclasQNoEscriben[i] == keycode){
				flag = true;		
				break;
			}
		}
		//Si la tecla presionada escribe en campo texto borro campos razon social, cod.cliente, numero holding y nombre holding 				
		if (!flag) {
			$('#edRazonSocialClienteLegado').val('');
			clienteSieble = null;
		}
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if (keycode == '13' || keycode == '9') {
			if($('#edInputCodCliente').val() != "") {
				$('#edInputCodCliente').val($('#edInputCodCliente').val().split("-")[0].split(" ").join(""));
				var codigoCliente = $('#edInputCodCliente').val().split(" ").join("");
				if (codigoCliente!="") {
					if($.isNumeric(codigoCliente) && Math.floor(codigoCliente) == codigoCliente && parseInt(codigoCliente, '10')>0) {
						buscarClienteLegado(codigoCliente);
					} else {
						$("#errorEdInputCodCliente").text('El campo debe tener un valor numérico de 10 dígitos.');
					}
				} else {
					$("#errorEdInputCodCliente").text('El campo debe tener un valor numérico de 10 dígitos.');
				};			
			} else {
				$("#errorEdInputCodCliente").text('');
			}
		}
		
		event.stopPropagation();
	});
	
	function buscarClienteLegado(codCliente, nameInput) {
		$('#bloqueEspera').trigger('click');
			
		$("#errorBusqueda").text("");
		var criterio = 'BUSQUEDA_POR_CLIENTE_LEGADO';
		var busqueda = $('#edInputCodCliente').val();
		criterio = $.trim(criterio);
		busqueda = $.trim(busqueda);

		$("span.error").text("");
		var filtro = {
			criterio : criterio,
			busqueda : busqueda
		};
		$.ajax({
			"type" : "POST",
			"url" : "legajo-cheque-rechazado/consultarCliente",
			"dataType" : "json",
			"data" : JSON.stringify(eval(filtro)),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				if (result.success) {
					if (result.clientes.length === 1) {
						$('#edRazonSocialClienteLegado').val(result.clientes[0].razonSocial);
						clienteSieble = result.clientes[0];
					} else {
						$(result.campoError).text(result.descripcionError);
					}
				} else {
					$(result.campoError).text(result.descripcionError);
				}
				ocultarBloqueEspera();
			}
		});
	};
	$('#edRazonSocialClienteLegado').change(function () {
		$('#edInputCodCliente').val('');
		if ($.isEmpty(this.value)) {
			$("#edInputCodCliente").attr('disabled', false);
			
		} else {
			$("#edInputCodCliente").attr('disabled', true);
			$('#errorEdInputCodCliente').text('');
			$('#errorEdInputCodCliente2').text('');
		}
	});
	$('#prevObservText').focusout( function() {
		var prevObservText = this.value;
		
		prevObservText = $.trim(prevObservText);
		
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#errorObservaciones").text("Este campo debe contener un formato de texto correcto.");
		} else {
			$("#errorObservaciones").text("");
		}
	});
	/******************************************************************************/
	/*	carga de datos si es edicion											  */
	/******************************************************************************/
	$('#fechaRechazo').change(function() {
		if(validarFecha('fechaRechazo',true)){
			validarFechaIngresadaContraFechaActual('fechaRechazo');
		}
	});
	
	$('#fechaNotificacionRechazo').change(function() {
		if(validarFecha('fechaNotificacionRechazo',true)){
			validarFechaIngresadaContraFechaActual('fechaNotificacionRechazo');
		}
	});
	
	$('#edFechaEmision').change(function() {
		if(validarFecha('edFechaEmision',true)){
			// Se coloco un solo & ya que es necesario que se evaluen los dos casos por mas que el primero ya de como resultado false
			if(	validarFechaIngresadaContraFechaActual('edFechaDeposito') & validarFechaIngresadaContraFechaActual('edFechaEmision') ) {
				$('#erroredFechaEmision').text('');
				validarFechaEmisionContraFechaDeposito();
			}
		}
	});
	
	$('#edFechaDeposito').change(function() {
		if(validarFecha('edFechaDeposito',true)){
			// Se coloco un solo & ya que es necesario que se evaluen los dos casos por mas que el primero ya de como resultado false
			if(	validarFechaIngresadaContraFechaActual('edFechaDeposito') & validarFechaIngresadaContraFechaActual('edFechaEmision') ){
				$('#erroredFechaEmision').text('');
				validarFechaEmisionContraFechaDeposito();
			}
		}
	});
	
	$('#edFechaVencimiento').change(function() {
		validarFecha('edFechaVencimiento',true);
	});
	
	
	$('#fechaAltaDesdeCheque').change(function() {
		validarFecha('fechaAltaDesdeCheque',false);
	});
	
	$('#fechaAltaHastaCheque').change(function() {
		validarFecha('fechaAltaHastaCheque',false);
	});
	
	$('#fechaVencimientoCheque').change(function() {
		validarFecha('fechaVencimientoCheque',false);
	});
	
	$('#nroCheque').change(function() {
		$("#errorNroCheque").text("");
		if (!$.validarCriterioBusqueda($('#nroCheque').val())) {
			$("#errorNroCheque").text("Solo se puede ingresar caracteres numericos y %");
		}
	});
	
	// Validacion de campos Busqueda
	$('#edNroCheque').change(function() {
		$("#errorEdNroCheque").text("");
		if (!$.validacionIsNumeric($('#edNroCheque').val())) {
			$("#errorEdNroCheque").text("Solo se puede ingresar caracteres numericos.");
		}
	});

	$('#selectUbicacion').change(function() {
		$("#errorUbicacion").text("");
		if ( $('#selectUbicacion').val() === '3' ) {
			$("#errorUbicacion").text('La ubicación "Cliente" se asigna automáticamente al momento de cargar una notificación de entrega de cheque');
		} else if ( $('#selectUbicacion').val() === '4' ){
			$("#errorUbicacion").text('La ubicación "Legales" se asigna automáticamente al momento de enviar a legales el legajo');
		}
	});
}
/******************************************************************************/
/*	Fin del ready															  */
/******************************************************************************/
function crearOpcionesByAcuerdo(idAcuerdo) {
	var options = [];
	var optionsBanco = [];
	var corriente = null;
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
 		if (corriente.idAcuerdo == idAcuerdo) {
			options.push(new Option(corriente.bancoCuenta.cuentaBancaria, corriente.bancoCuenta.idBancoCuenta));
			$('#edCuentaDeposito').append(options);
			optionsBanco.push(new Option(corriente.bancoCuenta.banco.descripcion, corriente.bancoCuenta.banco.idBanco));
			$('#edBancoDeposito').append(optionsBanco);
			$('#edBancoDeposito').combobox2("refresh", acuerdoBancoCuenta.listaAcuerdos[i].bancoCuenta.banco.idBanco, 'edBancoDeposito');
			return options;
		}
	}
}
function crearOpcionesByBanco(idBanco) {
	var optionsAcuerdo = [];
	var optionsCuenta = [];
	var corriente = null;
	var cantidad = 0;
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.banco.idBanco == idBanco) {
			cantidad++;
		}
	}
	if (cantidad != 1) {
		optionsCuenta.push(combos.bancoCuenta[0]);
		optionsAcuerdo.push(combos.banco[0]);
	}
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.banco.idBanco == idBanco) {
			optionsAcuerdo.push(new Option(corriente.descripcion, corriente.idAcuerdo));
			optionsCuenta.push(new Option(corriente.bancoCuenta.cuentaBancaria, corriente.bancoCuenta.idBancoCuenta));
		}
	}
	$('#edCuentaDeposito').append(optionsCuenta);
	$('#edAcuerdoCheque').append(optionsAcuerdo);
	
}
function selectOpcionByCuenta(idBancoCuenta) {
	var corriente = null;
	
	$('#edCuentaDeposito').append(combos.bancoCuenta);
	$('#edAcuerdoCheque').append(combos.acuerdo);
	$('#edBancoDeposito').append(combos.banco);
	
	for(var i = 0, size = acuerdoBancoCuenta.listaAcuerdos.length; i < size;i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = acuerdoBancoCuenta.listaAcuerdos[i];
		if (corriente.bancoCuenta.idBancoCuenta == idBancoCuenta) {
			$('#edCuentaDeposito').val(corriente.bancoCuenta.idBancoCuenta);
			$('#edAcuerdoCheque').val(corriente.idAcuerdo);
			$('#edBancoDeposito').combobox2("refresh", corriente.bancoCuenta.banco.idBanco, 'edBancoDeposito');
			return;
		}
	}
}
function completarMonto(montoPendiente, montoAplicado) {
	
	$('[id^=edMontoPendienteReembolsarInput]').val("$" + truncoADosDecimalesImporte(montoPendiente));
	$('[id^=edMontoAplicadoPendienteReembolsarInput]').val("$" +  truncoADosDecimalesImporte(montoAplicado));
}

function completarEdicionManual(cheque) {
	$('#edsistemaOrigen').val(cheque.sistemaOrigen);//sistemaOrigen
	
	if ('Shiva' == cheque.sistemaOrigen) {
		if(cheque.idTipoCheque == CHEQUE || cheque.idTipoCheque == BOLETA_DEPOSITO_CHEQUE) {
			$('#edTipoCheque').val(CHEQUE).change();
		} else if (cheque.idTipoCheque == CHEQUE_DIFERIDO || cheque.idTipoCheque == BOLETA_DEPOSITO_CHEQUE_DIFERIDO) {
			$('#edTipoCheque').val(CHEQUE_DIFERIDO).change();
		}
		$('#idDivClienteSiebel').hide();
		$('#idErrorDivClienteSiebel').hide();
		$('#idErrorDivClienteSiebel').hide();
		$('#edNroCheque').attr('maxlength','8');
	} else if ('Usuario' === cheque.sistemaOrigen) {
		$('#idErrorDivClienteSiebel').show();
		$('#idErrorDivClienteSiebel').show();
		$('#idDivClienteSiebel').show();
		$('#edNroCheque').attr('maxlength','8');
	} else {
		$('#idDivClienteSiebel').hide();
		$('#idErrorDivClienteSiebel').hide();
		$('#idErrorDivClienteSiebel').hide();
		$('#edNroCheque').attr('maxlength','8');
		$('#edTipoCheque').val('').change();
	}
	$('#edCodigoBancoOrigen').val(cheque.codBancoOrigen).change();
	$('#edNroCheque').val(cheque.nroCheque);
	
	if ('-' !== legajoChequeRechazado.fechaEmision) {
		$('#edFechaEmision').val(legajoChequeRechazado.fechaEmision);
	}
	if ('-' !== cheque.fechaDeposito) {
		$('#edFechaDeposito').val(cheque.fechaDeposito);
	}
	if ('-' !== cheque.fechaVenc) {
		$('#edFechaVencimiento').val(cheque.fechaVenc);
	//$('#edFechaDeposito').val(cheque.);
	}
	
	if ('-' !== cheque.moneda){
		$('#edMonedaCheque').val(returnSignoMoneda(cheque.moneda));
	}
	
	$('#edImporteCheque').val(cheque.importe);
	$('#edAcuerdoCheque').val(cheque.acuerdo).change();
}
function completarValoresDefaultEdicionManual() {
	$('#edCodigoBancoOrigen').val('').change();
	$('#edAcuerdoCheque').val('').change();
	$('#edsistemaOrigen').val(SISTEMA_USUARIO_DESC);
	$('#edTipoCheque').change();
	$('#idErrorDivClienteSiebel').show();
	$('#idDivClienteSiebel').show();
}


function crearOpcionesByAgrupador(idAgrupador, nameSelect) {
	var options = [];

	var corriente = null;
	
	for(var i = 0, size = bancoDescripcion.agrupadores.length; i < size; i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = bancoDescripcion.agrupadores[i];
 		if (corriente.idValue == idAgrupador) {
			for (var j = 0, sizeJ = corriente.bancos.length; j < sizeJ; j++) {
				var bancoCorriente = corriente.bancos[j];
				var option = new Option(bancoCorriente.idBanco + ' - ' + bancoCorriente.descripcion, bancoCorriente.idBanco);
				$(option).attr({'idAgrupador': bancoCorriente.idAgrupador});
				options.push(option);
			}
			break;
		}
	}
	return options;
}
function returnIdBancosByAgrupador(idAgrupador, nameSelect) {
	var options = [];

	var corriente = null;
	
	for(var i = 0, size = bancoDescripcion.agrupadores.length; i < size; i++) {
		// Se usa "==" dado que no son del mismo tipo
		corriente = bancoDescripcion.agrupadores[i];
 		if (corriente.idValue == idAgrupador) {
			for (var j = 0, sizeJ = corriente.bancos.length; j < sizeJ; j++) {
				var bancoCorriente = corriente.bancos[j];
				options.push(bancoCorriente.idBanco);
			}
			break;
		}
	}
	return options.join('-');
}

function cargarDatosLegajo(legajo) {
	cargoDatosLegajoCheque(legajo);
	cargarDatosLegajoChequeEdicionManual(legajo);
	

	$('#edCuentaDeposito').val(legajo.cuentaDeposito).change();
	$('#edBancoDeposito').val(legajo.idbancoDeposito).change();
	$('#edAcuerdoCheque').val(legajo.idAcuerdo).change();
	
	$('#edDescripcionBancoOrigen').combobox2("destroy");
	$('#edBancoDeposito').combobox2("destroy");
	if (legajo.sistemaOrigen !== 'Usuario'){
		tablas.tablaChequesSeleccionados.fnAddData(legajo.chequeRechazado, true);
		datosTablas.tablaChequesSeleccionados.push(legajo.chequeRechazado);
	} else {
		$('#idDivClienteSiebel').show();
		$('#edInputCodCliente').val(legajo.idCliente);
		$('#edRazonSocialClienteLegado').val(legajo.descripcionCliente);
	}
		
//	if (
//		'EDICION_PARCIAL_2' === legajo.edicionTipo ||
//		'EDICION_SUPERVICION' === legajo.edicionTipo
//	) {
//		$('#selectCopropietario').attr('disabled', false);
//	}
//	if (
//		'EDICION_PARCIAL_2' === legajo.edicionTipo ||
//		'EDICION_SUPERVICION' === legajo.edicionTipo
//	) {
//		$('#prevObservText').attr('disabled', false);
//	}
//	if (
//		'EDICION_PARCIAL_2' === legajo.edicionTipo ||
//		'EDICION_SUPERVICION' === legajo.edicionTipo
//	) {
//		$('#prevObservText').attr('disabled', false);
//	}
//	if ('EDICION_PARCIAL_2' === legajo.edicionTipo) {
//		$('#selectUbicacion').attr('disabled', false);
//	}
	habilitoEdicion(legajo);
	
	if (!$.isEmpty(datosTablas.tablaChequesSeleccionados)) {
		var sizeL = datosTablas.tablaChequesSeleccionados.length;
		for (var i = 0; i < sizeL; i++) {
			$('.' + BTN_NO_CLASS +  datosTablas.tablaChequesSeleccionados[i].idPantalla).attr('disabled', true).html(HTML_ICON_BLANK);
		}
		completarMonto(MONTOS.montoAReenvolsar, MONTOS.montoARevertir);
	} else {
		completarMonto('0,00', '0,00');
	}
	
	//$('[id^=btnGuardar]').attr('disabled', true);
}

function cargoDatosLegajoCheque(legajo) {
	selectCopropietarioEdicion = legajo.idCopropietario;
	$('#selectEmpresa').val(legajo.idEmpresa);
	$('#selectSegmento').val(legajo.idSegmento).change();
	$('#idAnalista').val(legajo.idAnalista);
	
	$('#selectUbicacion').val(legajo.ubicacion);
	$('#fechaRechazo').val(legajo.fechaRechazo);
	$('#motivoRechazo').val(legajo.motivoLegajo);
	$('#fechaNotificacionRechazo').val(legajo.fechaNotificacion);
	

	$('#divDatosLegajo :input').attr('disabled', true);
	$('#divDatosLegajo :button').attr('disabled', true);
}
function cargarDatosLegajoChequeEdicionManual(legajo) {
	$('#edsistemaOrigen').val(legajo.sistemaOrigen);
	if(legajo.tipoCheque == CHEQUE || legajo.tipoCheque == BOLETA_DEPOSITO_CHEQUE) {
		$('#edTipoCheque').val(CHEQUE).change();
	} else if (legajo.tipoCheque == CHEQUE_DIFERIDO || legajo.tipoCheque == BOLETA_DEPOSITO_CHEQUE_DIFERIDO) {
		$('#edTipoCheque').val(CHEQUE_DIFERIDO).change();
	}

	$('#edCodigoBancoOrigen').val(legajo.idBancoOrigen).change();
	$('#edNroCheque').val(legajo.numeroCheque);
	
	if ('-' !== legajo.fechaEmision) {
		$('#edFechaEmision').val(legajo.fechaEmision);
	}
	if ('-' !== legajo.fechaDeposito) {
		$('#edFechaDeposito').val(legajo.fechaDeposito);
	}
	if ('-' !== legajo.fechaVencimiento) {
		$('#edFechaVencimiento').val(legajo.fechaVencimiento);
	}
	
	if ('-' !== legajo.moneda){
		$('#edMonedaCheque').val(returnSignoMoneda(legajo.moneda));
	}
	
	$('#edImporteCheque').val(legajo.importe);
	
	$('#prevObservText').val(legajo.observaciones);

	$('#idDivClienteSiebel').hide();
	$('#idErrorDivClienteSiebel').hide();
	$('#idErrorDivClienteSiebel').hide();
	
	$('#divEdicionManual :input').attr('disabled', true);
	$('#divEdicionManual :button').attr('disabled', true);

}

function cargarObservaciones(legajo) {
	$('#prevObservText').val(legajo.observaciones);
}
function validarFechaEmisionContraFechaDeposito() {
	
	var fechaError = $("#erroredFechaEmision");
//	fechaError.text("");
	var edFechaEmision = $("#edFechaEmision").val().split("/");
	var edFechaDeposito = $("#edFechaDeposito").val().split("/");
	var fechaEmision= new Date(edFechaEmision[2], edFechaEmision[1] - 1, edFechaEmision[0]);
	var fechaDeposito= new Date(edFechaDeposito[2], edFechaDeposito[1] - 1, edFechaDeposito[0]);
	var salida = true;
	
	if (fechaEmision > fechaDeposito) {
		fechaError.text("La fecha ingresada no puede ser mayor a la fecha de depósito");
		salida = false;
	}
	return salida;
};
