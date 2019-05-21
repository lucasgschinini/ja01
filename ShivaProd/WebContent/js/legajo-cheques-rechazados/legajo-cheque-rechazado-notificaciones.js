var registrarEventoSolapaNotificaciones = function() {
	$("#fechaContacto").datepicker({firstDay : 1});
	$("#fechaRecepcion").datepicker({firstDay : 1});
	$("#cartaFechaReembolso").datepicker({firstDay : 1});
	
	$('#selectTipoContacto').change(function() {
		if ($('#selectTipoContacto').val() == ""){
			$("#errortipoContacto").text("Este campo es requerido.");
			$("#datos-carta-notificacion").css("display","none");
			clearSelectInput("datos-carta-notificacion");
		} else{
			if (validarTipoNotificacion()){
				$("#errortipoContacto").text("");
			}
		}
		
//		if ($('#selectTipoContacto').val() == 1){
//			if ($('#selectTipoNotificacion').val() == 2){
//				$("#datos-carta-notificacion").css("display","initial");
//			}else {
//				$("#datos-carta-notificacion").css("display","none");
//			}
//		} else {
//			$("#datos-carta-notificacion").css("display","none");
//		}
	});
	
	$('#selectTipoNotificacion').change(function() {
		if ($('#selectTipoNotificacion').val() == ""){
			$("#errortipoNotificacion").text("Este campo es requerido.");
			$("#datos-carta-notificacion").css("display","none");
			clearSelectInput("datos-carta-notificacion");
		} else {
			if (validarTipoNotificacion()){
				$("#errortipoNotificacion").text("");
			}
		}
		
	//		
//		if ($('#selectTipoContacto').val() == 1){
//			if ($('#selectTipoNotificacion').val() == 2){
//					$("#datos-carta-notificacion").css("display","initial");
//			} else {
//				$("#datos-carta-notificacion").css("display","none");
//			}
//		} else {
//			$("#datos-carta-notificacion").css("display","none");
//		}
	});
	
	$('#btnGuardarContacto').click(function() {
		
		$('#bloqueEspera').trigger('click');
		if (validarTipoNotificacion() && validarCamposNotificaciones()){
			
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : 'legajo-cheque-rechazado/guardar-contacto',
				"data" : JSON.stringify(obtenerNotificacion()),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					$('#bloqueEspera').trigger('click');
					if (result.success) {
						clearSelectInput('idNotificaciones');
						$("#prevDatosContacto").val("");
						$("#prevObservacionesNotificacion").val("");
						$("#datos-carta-notificacion").css("display","none");
						$('#estadoLegajo').val(result.resultado.estadoDescripcion);
						legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
						legajoChequeRechazado.estado = result.resultado.estado;

						legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;

						$('#selectUbicacion').val(result.resultado.ubicacion);
						legajoChequeRechazado.ubicacion = result.resultado.ubicacion;
						legajoChequeRechazado.ubicacionDesc = result.resultado.ubicacionDesc;
						
						tablas.tablaNotificaciones.fnClearTable();
						datosTablas.notificaciones = result.listaLegajoChequeRechazadoNotificacionDto;
						if (!$.isEmpty(result.listaLegajoChequeRechazadoNotificacionDto)) {
							tablas.tablaNotificaciones.fnAddData(result.listaLegajoChequeRechazadoNotificacionDto, true);
						}
						solapa.validar(solapa.NOTIFICACION, legajoChequeRechazado);
					}
					ocultarBloqueEspera();
				}
			});
		}
	});
};
	
function obtenerNotificacion(){
	var carta = null;
	if ($("#selectTipoContacto").val() == 1){
		carta = {
				calle: $("#cartaCalle").val(),
				numero: $("#cartaNumero").val(),
				piso: $("#cartaPiso").val(),
				departamento: $("#cartaDepartamento").val(),
				codigoPostal: $("#cartaCodPostal").val(),
				localidad: $("#cartaLocalidad").val(),
				provincia: $("#cartaProvincia").val(),
				numLineaServicio: $("#cartaNumeroServicio").val(),
				fechaReembolso: $("#cartaFechaReembolso").val(),
				nombreDestinatario : $("#cartaNombreReceptor").val()
		};
	}
	var notificacion = {
			idLegajoChequeRechazado: legajoChequeRechazado.idLegajoChequeRechazado,
			estadoLegajo: legajoChequeRechazado.estado,
			tipoNotificacion: $("#selectTipoNotificacion").val(),
			datosReceptor: $("#notificacionReceptor").val(),
			fechaContacto: $("#fechaContacto").val(),
			tipoContacto: $("#selectTipoContacto").val(),
			datosContacto: $("#prevDatosContacto").val(),
			acuseRecibo: $("#selectAcuseRecibo").val(),
			fechaRecepcion: $("#fechaRecepcion").val(),
			observaciones: $("#prevObservacionesNotificacion").val(),
			segmentoLegajo: legajoChequeRechazado.idSegmento,
			carta: carta
		};
	return notificacion;
}







var drawTabNotificaciones = function() {
	tablas.tablaNotificaciones.fnClearTable();
	if (!$.isEmpty(datosTablas.notificaciones)) {
		tablas.tablaNotificaciones.fnAddData(datosTablas.notificaciones, true);
	}
	solapa.validar(solapa.next , legajoChequeRechazado);

};
var clearTabNotificaciones = function() {
};
var validarCamposNotificaciones =function(){
	var validacionOk = true;
	
	if (!validarFecha('fechaContacto',true) || !validarFechaIngresadaContraFechaActual('fechaContacto')){
		validacionOk=false;
	}
	
	if (!validarFecha('fechaRecepcion',false) || !validarFechaIngresadaContraFechaActual('fechaRecepcion')){
		validacionOk=false;
	}
	
	if ($('#selectTipoContacto').val() == 1){
		if ($('#selectTipoNotificacion').val() == 2){
			if (!validarFecha('cartaFechaReembolso',true) || !validarFechaIngresadaContraFechaActual('cartaFechaReembolso')){
				validacionOk=false;
			}
		} else{
			if (!validarFecha('cartaFechaReembolso',false) || !validarFechaIngresadaContraFechaActual('cartaFechaReembolso')){
				validacionOk=false;
			}
		}
	}

	$('#fechaContacto').change(function() {
		if (!validarFecha('fechaContacto',true)){
			validacionOk=false;
		}
	});
	
	$('#cartaFechaReembolso').change(function() {
		if (!validarFecha('cartaFechaReembolso',true)){
			validacionOk=false;
		}
	});
//	$('#fechaRecepcion').change(function() {
//		if (!validarFecha('fechaRecepcion',true)){
//			validacionOk=false;
//		}
//	});
	
	if ($.isEmpty($('#selectTipoContacto').val())) {
		$("#errortipoContacto").text("Este campo es requerido.");
		validacionOk = false;
	} else {
		$("#errortipoContacto").text("");
	}
	
	if ($.isEmpty($('#selectTipoNotificacion').val())) {
		$("#errortipoNotificacion").text("Este campo es requerido.");
		validacionOk = false;
	} else {
		$("#errortipoNotificacion").text("");
	}
	
	if ($('#selectTipoContacto').val() == 1){
		if ($('#selectTipoNotificacion').val() == 2){
			
			if ($.isEmpty($('#cartaNumero').val())){ 
				$("#errorcartaNumero").text("Este campo es requerido.");
				validacionOk = false;
			}else if (!$.validacionIsNumeric($('#cartaNumero').val())) {
				$("#errorcartaNumero").text("Solo se puede ingresar caracteres numericos.");
				validacionOk = false;
			}
			
			if ($.isEmpty($('#cartaCodPostal').val())){ 
				$("#errorcartaCodPostal").text("Este campo es requerido.");
				validacionOk = false;
			}else if (!$.validacionIsNumeric($('#cartaCodPostal').val())) {
				$("#errorcartaCodPostal").text("Solo se puede ingresar caracteres numericos.");
				validacionOk = false;
			}
			
					
			if (!$.isEmpty($('#cartaLocalidad').val())) {
				var prevObservText = $('#cartaLocalidad').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!($.validacionCaracteresEspeciales(prevObservText))) {
					$("#errorcartaLocalidad").text("Este campo debe contener un formato de texto correcto.");
					validacionOk = false;
				} else {
					$("#errorcartaLocalidad").text("");
				}
			} else {
				$("#errorcartaLocalidad").text("Este campo es requerido.");
			}
			
			if (!$.isEmpty($('#cartaProvincia').val())) {
				var prevObservText = $('#cartaProvincia').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!($.validacionCaracteresEspeciales(prevObservText))) {
					$("#errorcartaProvincia").text("Este campo debe contener un formato de texto correcto.");
					validacionOk = false;
				} else {
					$("#errorcartaProvincia").text("");
				}
			} else {
				$("#errorcartaProvincia").text("Este campo es requerido.");
			}
			
			if (!$.isEmpty($('#cartaCalle').val())) {
				var prevObservText = $('#cartaCalle').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!($.validacionCaracteresEspeciales(prevObservText))) {
					$("#errorcartaCalle").text("Este campo debe contener un formato de texto correcto.");
					validacionOk = false;
				} else {
					$("#errorcartaCalle").text("");
				}
			} else {
				$("#errorcartaCalle").text("Este campo es requerido.");
			}
			
			if (!$.isEmpty($('#cartaPiso').val())) {
				var prevObservText = $('#cartaPiso').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!$.validacionIsNumeric($('#cartaPiso').val())) {
					$("#errorcartaPiso").text("Solo se puede ingresar caracteres numericos.");
					validacionOk = false;
				} else {
					$("#errorcartaPiso").text("");
				}
			}
			
			if (!$.isEmpty($('#cartaNumeroServicio').val())) {
				var prevObservText = $('#cartaNumeroServicio').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!$.validacionIsNumeric($('#cartaNumeroServicio').val())) {
					$("#errorcartaNumeroServicio").text("Solo se puede ingresar caracteres numericos.");
					validacionOk = false;
				} else {
					$("#errorcartaNumeroServicio").text("");
				}
			} else {
				$("#errorcartaNumeroServicio").text("Este campo es requerido.");
			}
			
			if (!$.isEmpty($('#cartaDepartamento').val())) {
				var prevObservText = $('#cartaDepartamento').val();
				
				prevObservText = $.trim(prevObservText);
				
				if (!($.validacionCaracteresEspeciales(prevObservText))) {
					$("#errorcartaDepartamento").text("Este campo debe contener un formato de texto correcto.");
					validacionOk = false;
				} else {
					$("#errorcartaDepartamento").text("");
				}
			}
			if ($.isEmpty($('#cartaNombreReceptor').val())) {
				$("#errorcartaNombreReceptor").text("Este campo es requerido.");
				validacionOk = false;
			} else {
				$("#errorcartaNombreReceptor").text("");
			}
		}
	}
	
	if (!$.isEmpty($('#notificacionReceptor').val())) {
		var prevObservText = $('#notificacionReceptor').val();
		
		prevObservText = $.trim(prevObservText);
		
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#errornotificacionReceptor").text("Este campo debe contener un formato de texto correcto.");
			validacionOk = false;
		} else {
			$("#errornotificacionReceptor").text("");
		}
	} else {
		validacionOk = false;
		$("#errornotificacionReceptor").text("Este campo es requerido.");
	}
	
	if (!$.isEmpty($('#prevDatosContacto').val())) {
		var prevObservText = $('#prevDatosContacto').val();
		
		prevObservText = $.trim(prevObservText);
		
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#errorprevDatosContacto").text("Este campo debe contener un formato de texto correcto.");
			validacionOk = false;
		} else {
			$("#errorprevDatosContacto").text("");
		}
	} else {
		validacionOk = false;
		$("#errorprevDatosContacto").text("Este campo es requerido.");
	}
	
	if (!$.isEmpty($('#prevObservacionesNotificacion').val())) {
		var prevObservText = $('#prevObservacionesNotificacion').val();
		
		prevObservText = $.trim(prevObservText);
		
		if (!($.validacionCaracteresEspeciales(prevObservText))) {
			$("#errorprevObservacionesNotificacion").text("Este campo debe contener un formato de texto correcto.");
			validacionOk = false;
		} else {
			$("#errorprevObservacionesNotificacion").text("");
		}
	}
	
	return validacionOk;
};
function eliminarNotificacion(idNotificacion, idLegajoChequeRechazado) {
	var notificacion = {
			idLegajoChequeRechazado: idLegajoChequeRechazado,
			idNotificacion: idNotificacion,
	};
	$.ajax({
		"type" : "POST",
		"url" : "legajo-cheque-rechazado/eliminar-notificacion",
		"dataType" : "json",
		"data" : JSON.stringify(eval(notificacion)),
		"contentType" : "application/json",
		"mimeType" : "application/json",
		"success" : function(result) {
			if (result.success) {
					tablas.tablaNotificaciones.fnClearTable();
					if (!$.isEmpty(result.listaLegajoChequeRechazadoNotificacionDto)) {
						tablas.tablaNotificaciones.fnAddData(result.listaLegajoChequeRechazadoNotificacionDto, true);
						datosTablas.notificaciones = result.listaLegajoChequeRechazadoNotificacionDto;
					}
			} else {
				mostrarErrorGeneral(result.descripcionError);
				marcarErrorSolapa([solapa.currrent]);
			}
			ocultarBloqueEspera();
		}
	});
};
function descargarNotificacion(idNotificacion) {
	beforeUnload.off();
	$('#formNotificacion')[0].action=urlAsynchronousHTTP+"/generarPdfCartaLegajo";
	$('#idNotificacion').val(idNotificacion);
	$('#formNotificacion')[0].submit();
	setTimeout(function() { beforeUnload.on(); }, 500);
};

function validarTipoNotificacion(){
	
	if ($('#selectTipoNotificacion').val() == 3){
		$("#datos-carta-notificacion").css("display","none");
		clearSelectInput("datos-carta-notificacion");
		if ( !(legajoChequeRechazado.estado == LGJ_ABIERTO && legajoChequeRechazado.chequeRechazado.sistemaOrigen == SISTEMA_USUARIO_DESC)
				&& legajoChequeRechazado.estado !== LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA
				&& legajoChequeRechazado.estado !== LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA
				&& legajoChequeRechazado.estado !== LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA){
			
			var textError ="Para seleccionar este tipo de notificación, el legajo debe estar en alguno de los siguientes estados: ";
			textError += LGJ_ABIERTO_DESC + ", "+ LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA_DESC + ", " + LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA_DESC + ", " +  LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA_DESC;
			$("#errortipoNotificacion").text(textError);
			return false;
		}
	} else{
		if ($('#selectTipoNotificacion').val() == 2){
			if ( (legajoChequeRechazado.estado == LGJ_ABIERTO && legajoChequeRechazado.chequeRechazado.sistemaOrigen == SISTEMA_USUARIO_DESC) 
					|| legajoChequeRechazado.estado == LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA
					|| legajoChequeRechazado.estado == LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA){
				if ($('#selectTipoContacto').val() == 1){
					$("#datos-carta-notificacion").css("display","initial");
				} else {
					$("#datos-carta-notificacion").css("display","none");
					clearSelectInput("datos-carta-notificacion");
				}
			} else {
				var textError ="Para seleccionar este tipo de notificación, el legajo debe estar en alguno de los siguientes estados: ";
				textError += LGJ_ABIERTO_DESC + ", " + LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA_DESC + ", " +  LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA_DESC;
				$("#errortipoNotificacion").text(textError);
				return false;
			}
		} else {
			$("#datos-carta-notificacion").css("display","none");
			clearSelectInput("datos-carta-notificacion");
		}
	}
	return true;
}