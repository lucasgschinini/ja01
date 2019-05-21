// funcion llamada en el ready para registrar los eventos que utilza la solapa
// Reembolso
var registrarEventoSolapaReembolso = function() {
	$('#btnConfirmarReembolso').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#btnConfirmarReembolso').attr('disabled',true);
		$('#btnEnviarLegales').attr('disabled',true);
		var idLegajoChequeRechazado = legajoChequeRechazado.idLegajoChequeRechazado;
		 
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'legajo-cheque-rechazado/reembolsar',
			"data" : JSON.stringify(idLegajoChequeRechazado),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				$('#bloqueEspera').trigger('click');
				if (result.success) {
					legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;
					
					$('#estadoLegajo').val(result.resultado.estadoDescripcion);
					legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
					legajoChequeRechazado.estado = result.resultado.estado;

					$('#selectUbicacion').val(result.resultado.ubicacion);
					legajoChequeRechazado.ubicacion = result.resultado.ubicacion;
					legajoChequeRechazado.ubicacionDesc = result.resultado.ubicacionDesc;

					
				} else {
					if (!$.isEmpty(result.errorMensaje)) {
						mostrarErrorGeneral(result.descripcionError);
						//marcarErrorSolapa([solapa.currrent]);
					}
					$('#btnConfirmarReembolso').attr('disabled', false);
					$('#btnEnviarLegales').attr('disabled', false);
				}
				ocultarBloqueEspera();
			}
		});
	});
	
	$('#btnEnviarLegales').click(function() {
		$('#bloqueEspera').trigger('click');
		var idLegajoChequeRechazado = legajoChequeRechazado.idLegajoChequeRechazado;
		$('#btnEnviarLegales').attr('disabled',true);
		$('#btnConfirmarReembolso').attr('disabled', true);
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : 'legajo-cheque-rechazado/enviar-a-legales',
			"data" : JSON.stringify(idLegajoChequeRechazado),
			"contentType" : "application/json",
			"mimeType" : "application/json",
			"success" : function(result) {
				$('#bloqueEspera').trigger('click');
				if (result.success) {
					
					legajoChequeRechazado.edicionTipo = result.resultado.edicionTipo;
					
					$('#estadoLegajo').val(result.resultado.estadoDescripcion);
					legajoChequeRechazado.estadoDescripcion = result.resultado.estadoDescripcion;
					legajoChequeRechazado.estado= result.resultado.estado;
					
					$('#selectUbicacion').val(result.resultado.ubicacion);
					$('#selectUbicacion' ).attr('disabled',true);
					legajoChequeRechazado.ubicacion = result.resultado.ubicacion;
					legajoChequeRechazado.ubicacionDesc = result.resultado.ubicacionDesc;
					
					$('#btnConfirmarReembolso').attr('disabled',true);
					$('#btnEnviarLegales').attr('disabled',true);
				} else {
					if (!$.isEmpty(result.errorMensaje)) {
						mostrarErrorGeneral(result.descripcionError);
						//marcarErrorSolapa([solapa.currrent]);
					}
					$('#btnEnviarLegales').attr('disabled',false);
					$('#btnConfirmarReembolso').attr('disabled', false);
				}
				ocultarBloqueEspera();
			}
		});
	});
};

var drawTabReembolso = function() {
	
	if (solapa.validar(solapa.next , legajoChequeRechazado)) {
		$('#btnEnviarLegales').show();
		$('#btnEnviarLegales').attr('disabled',true);
		$('#btnConfirmarReembolso').show();
		$('#btnConfirmarReembolso').attr('disabled',true);

		if (SISTEMA_MAP.SHIVA.descripcion === legajoChequeRechazado.sistemaOrigen) {
			if ( 	 EDICION_MAP.EDICION_PARCIAL_4.name === legajoChequeRechazado.edicionTipo
				  || EDICION_MAP.EDICION_PARCIAL_4_1.name === legajoChequeRechazado.edicionTipo) {
				$('#btnConfirmarReembolso').attr('disabled',false); 
				$('#btnEnviarLegales').attr('disabled',false);
			}
		} else if (SISTEMA_MAP.ICE.descripcion === legajoChequeRechazado.sistemaOrigen) {
			if (	 EDICION_MAP.EDICION_PARCIAL_4.name === legajoChequeRechazado.edicionTipo
				  || EDICION_MAP.EDICION_PARCIAL_4_1.name === legajoChequeRechazado.edicionTipo ) {
				$('#btnConfirmarReembolso').attr('disabled',false);
				$('#btnEnviarLegales').attr('disabled',false);
			}
		} else if (SISTEMA_MAP.USUARIO.descripcion === legajoChequeRechazado.sistemaOrigen) {
			if( 	 EDICION_MAP.EDICION_PARCIAL_2.name === legajoChequeRechazado.edicionTipo){
				$('#btnEnviarLegales').attr('disabled',false);
			}
		}
	}
};
var clearTabReembolso = function() {
};

function cambiarSolapa(step) {
	$('#gestion-cheques-rechazados-tabs-t-'+step).click();
};