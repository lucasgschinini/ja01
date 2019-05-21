function mostarTipoValor(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;

	switch (tipoValorSeleccionado) {
	case "2":	// BOLETA_DEPOSITO_CHEQUE
		bloqueOrigen.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueChequeRechazado.style.display= 'block';
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		if ($('#idOrigen').val() == 3 || $('#idOrigen').val() == 1) {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		break;
	case "3":	// BOLETA_DEPOSITO_CHEQUE_DIFERIDO
		bloqueOrigen.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueChequeRechazado.style.display= 'block';
		bloqueFechaVencimiento.style.display = 'block';
		if ($('#idOrigen').val() == 3 || $('#idOrigen').val() == 1) {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		break;
	case "4":	// BOLETA_DEPOSITO_EFECTIVO
		bloqueOrigen.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'block';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'none';
		bloqueChequeRechazado.style.display= 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		if ($('#idOrigen').val() == 3 || $('#idOrigen').val() == 1) {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			$('#space').show();
		} else {
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			$('#space').hide();
		}
		break;
	case "5":	// CHEQUE
		bloqueOrigen.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'block';
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		$('#space').hide();
		break;
	case "6": // CHEQUE_DIFERIDO
		bloqueOrigen.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'block';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'block';
		bloqueFechaEmisionCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		$('#space').show();
		break;
	case "7": // EFECTIVO
		bloqueOrigen.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'block';
		bloqueFechaDeposito.style.display = 'block';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		$('#space').hide();
		break;
	case "8": // TRANSFERENCIA
		bloqueOrigen.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'block';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'block';
		bloqueNroCheque.style.display = 'none';
		bloqueNroReferencia.style.display = 'block';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'none';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		$('#space').hide();
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
		break;
	case "9": // INTERDEPÓSITO
		bloqueOrigen.style.display = 'block';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueAcuerdo.style.display = 'block';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'block';
		bloqueTipoImpuesto.style.display = 'none';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'none';
		bloqueCuitIbb.style.display = 'none';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		break;
	case "10": // RETENCIÓN_IMPUESTO
		bloqueOrigen.style.display = 'none';
		bloqueNroDocumentoContable.style.display = 'none';
		bloqueAcuerdo.style.display = 'none';
		bloqueNroBoleta.style.display = 'none';
		bloqueFechaDeposito.style.display = 'none';
		bloqueBancoOrigen.style.display = 'none';
		bloqueNroCheque.style.display = 'none';
		bloqueNroReferencia.style.display = 'none';
		bloqueInterdeposito.style.display = 'none';
		bloqueTipoImpuesto.style.display = 'block';
		bloqueObsMail.style.display = 'none';
		bloqueReciboConstancia.style.display = 'block';
		bloqueComprobantes.style.display = 'block';
		bloqueChequeRechazado.style.display= 'none';
		bloqueFechaEmisionCheque.style.display = 'none';
		bloqueFechaVencimiento.style.display = 'none';
		bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
		break;
	}
};

function mostarTipoImpuesto(referenciaCombo) {
	var tipoImpuestoSeleccionado = referenciaCombo.value;
	switch (tipoImpuestoSeleccionado) {
	case "1":
		bloqueCuitIbb.style.display = 'block';
		break;
	case "2":
		bloqueCuitIbb.style.display = 'none';
		break;
	case "3":
		bloqueCuitIbb.style.display = 'none';
		break;
	case "4":
		bloqueCuitIbb.style.display = 'none';
		break;
	case "5":
		bloqueCuitIbb.style.display = 'none';
		break;
	case "6":
		bloqueCuitIbb.style.display = 'none';
		break;
	}
};

function mostarConstancia(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;
	switch (tipoValorSeleccionado) {
	case "1":
		bloqueReciboConstancia.style.display = 'block';
		bloqueConstancia.style.display = 'none';
		break;
	case "2":
		bloqueReciboConstancia.style.display = 'none';
		bloqueConstancia.style.display = 'none';
		break;
	case "3":
		bloqueReciboConstancia.style.display = 'block';
		bloqueConstancia.style.display = 'none';
		break;
	case "4":
		bloqueReciboConstancia.style.display = 'none';
		bloqueConstancia.style.display = 'block';
		break;
	case "":
		bloqueConstancia.style.display = 'none';
		break;
	}
};
//Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
// En IE8 no hace falta llamar a fnCellRende
var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
	return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [], [1], [3]);
};


function cargarEstiloBicolorTablaResultado() {
    $(".tablaResultadoHist tr:odd").css('background-color','#FFFFFF');
	$(".tablaResultadoHist tr:even").css('background-color','#F2F2F7');
};



$(document).ready(function() {
	
	
	var selClientesSettings = {
			dom : '<"eliminarTodosClientes">Bfrtip',
			"sScrollX" : true,
			"scrollY" : '300px',
			"bScrollCollapse" : true,
			"iDisplayLength" : 10,
			"iDisplayStart" : 0,
			"order": [[ 2, "asc"]],
			buttons : [ {
				extend : 'excelHtml5',
				text : 'Excel',
				title : "Cobro_Clientes_Seleccionados",
				className : 'excelbtn',
				exportOptions : {
					columns : ':visible'
				}
			} ],
			"fnDrawCallback" : function(oSettings) {
				$('#btnEliminarTodos').attr('disabled',
						!(oSettings.aoData.length > 0));
			},
		
					'columnDefs' : [ // targets son los th con las class
										{
											type : 'string-numero',
											targets : 'numeroOrder'
										}]
		};
	$("#clientesSeleccionados").dataTable(selClientesSettings);
});