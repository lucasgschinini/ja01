
$('#main :input').attr('disabled', true);
$('#main :button').attr('disabled', true);
$('#main :select').attr('disabled', true);

//Guido.Settecerze
window.onload = function() {
    $('#prevObservacionesAnteriores').hide();
    $.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaAltaTramReint").datepicker({firstDay : 1});
    
    var archivosPendientes = 'archivosPendientes';
    inicializarTabla(archivosPendientes);
    
    if ($('#detalleDoAprobacionA').val() == "D") {
		$('#titleAprobacion').hide();
		$('#titleDetalle').show();
		$('#btnAceptar').hide();
		$('#btnRechazar').hide();
		$('#observacion').attr('disabled', true);

    } else if($('#detalleDoAprobacionA').val() == "A"){
		$('#titleAprobacion').show();
		$('#titleDetalle').hide();
		$('#observacion').attr('disabled', false);
    }

    if (!$.isEmpty($('#observacionAnterior').val())) {
	    $('#prevObservacionesAnteriores').show();
	    $('#observacion').val("");
	}else{
	    $('#prevObservacionesAnteriores').hide();
	}
};


//Guido.Settecerze
function descargarComprobante(idComprobante) {
	$('#formOperacionMasiva')[0].action=urlAsynchronousHTTP+"/descargarComprobanteOperacionMasiva?id=" + idComprobante;
	$('#formOperacionMasiva')[0].submit();
};

function volverBusqueda() {
	 $('#goBack').val("true");
	 $('#formOperacionMasiva')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	 $('#formOperacionMasiva')[0].submit();
};


//Guido.Settecerze
function autorizacionAprobacionOperacionMasiva() {
    if (!($.validacionCaracteresEspeciales($('#observacion').val()))) {
    	$("#errorPrevObservText2").text("Este campo debe contener un formato de texto correcto.");
    	$("#errorPrevObservText2").show();
    }else{
    	$("#errorPrevObservText2").hide();
    	$('#bloqueEspera').trigger('click');
    	$('#formOperacionMasiva')[0].action=urlAsynchronousHTTP+"/autorizacion-aprobacion-operacion-masiva";
    	$('#formOperacionMasiva')[0].submit();
    }

};

//Guido.Settecerze
function rechazarAprobacionOperacionMasiva() {
    if ($.isEmpty($('#observacion').val())) {
    	$('#errorPrevObservText2').text("Este campo es requerido.");
		$('#errorPrevObservText2').show();
    }else{
	if (!($.validacionCaracteresEspeciales($('#observacion').val()))) {
	    $("#errorPrevObservText2").text("Este campo debe contener un formato de texto correcto.");
	    $("#errorPrevObservText2").show();
	}else{
	    $('#errorPrevObservText2').hide();
	    $('#bloqueEspera').trigger('click');
	    $('#formOperacionMasiva')[0].action=urlAsynchronousHTTP+"/rechazar-aprobacion-operacion-masiva";
	    $('#formOperacionMasiva')[0].submit();
	}
    }
};

function historialOpMas() {
	$('#bloqueEspera').trigger('click');
	$('#formOperacionMasiva')[0].action=urlAsynchronousHTTP+"/operacion-masiva-historial";
	$('#formOperacionMasiva')[0].submit();
};

