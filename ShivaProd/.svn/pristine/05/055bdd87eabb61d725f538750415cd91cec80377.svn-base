function paginarTabla(tabla, servicio, accion, idCustomPag, idBtnAgregarTodos, idMostrarError, cantPorPag, funcionOcultarSeleccionados) {
	$('#bloqueEspera').trigger('click');
	var data = {
		accion : accion
	};
	$.ajax({
		"dataType" : 'json',
		"type" : 'POST',
		"url" : servicio,
		"data" : data,
		"success" : function(data) {
			actualizarInfoTabla(tabla, data, idCustomPag, idBtnAgregarTodos, idMostrarError, cantPorPag, funcionOcultarSeleccionados);
			ocultarBloqueEspera();
		}
	});
}

/**
 * Si tableName es debito
 * #customPagDebitos_next
 * paginarDebitos
 * btnAgregarTodosDebitos
 * errorRespuestaDebitos
 * @param tableName
 */
//Controles de paginacion
function registrarEventoPaginacion(tableName, name, cantPorPagina, evento, funcionOcultarSeleccionados) {
	var postFijo = '';
	var eventoName = '';

	if ("next" === evento) {
		postFijo = '_next';
		eventoName = 'stge';
	} else {
		postFijo = '_previous';
		eventoName = 'ant';
	}
	
	$("#customPag"+ tableName + postFijo).click(function() {
		if (
			$(this).hasClass('active') &&
			!($(this).hasClass('disabled'))
		) {
			paginarTabla(
				tablas[name],
				'paginar' + tableName,
				eventoName,
				'customPag' + tableName,
				'btnAgregarTodos' + tableName,
				'errorRespuesta' + tableName,
				cantPorPagina,
				funcionOcultarSeleccionados
			);
		}
	});
}

function actualizarInfoTabla(
	tabla,
	resultado,
	idCustomPag,
	idBtnAgregarTodos,
	idMuestroError,
	cantPorPag,
	funcionOcultarSeleccionados
) {
	var pgActual = 0;
	var cantMostrados = resultado.controlPaginacion.cantRegistrosMostrados;
	var total = resultado.controlPaginacion.totalRegistros;
	var infoIni = 0;
	var infoFin = 0;

	if (cantMostrados > 0) {
		pgActual = resultado.controlPaginacion.paginaActual;
		cantMostrados = resultado.controlPaginacion.cantRegistrosMostrados;
		infoIni = ((pgActual - 1) * cantPorPag) + 1;
		infoFin = ((pgActual - 1) * cantPorPag) + cantMostrados;
	}

	$('#' + idCustomPag + '_info span').text(
		'Mostrando registros del ' + infoIni + ' al ' + infoFin + ' de ' + total + ' registros en total'
	);

	if (resultado.controlPaginacion.inhabilitarAnt) {
		$('#' + idCustomPag + '_previous').removeClass('active').addClass(
			'disabled'
		);
	} else {
		$('#' + idCustomPag + '_previous').removeClass('disabled').addClass(
			'active'
		);
	}

	if (resultado.controlPaginacion.inhabilitarSig) {
		$('#' + idCustomPag + '_next').removeClass('active').addClass(
			'disabled'
		);
	} else {
		$('#' + idCustomPag + '_next').removeClass('disabled').addClass(
			'active'
		);
	}

	tabla.fnClearTable();
	if (resultado.resultado.length > 0) {
		tabla.fnAddData(resultado.resultado, true);
		// TODO Esto no quedo muy generico!!!!!! u578936
		if (undefined !== funcionOcultarSeleccionados) {
			funcionOcultarSeleccionados(
				tabla,
				idBtnAgregarTodos
			);
		}
		
		$('#' + idBtnAgregarTodos).attr('disabled', false);
	} else {
		$('#' + idBtnAgregarTodos).attr('disabled', true);
	}

	if (resultado.errorMensaje !== null) {
		// Mostrar el error
		$('#' + idMuestroError).html(resultado.errorMensaje).addClass("error");
	} else {
		$('#' + idMuestroError).html("");
	}

	if (resultado.informacionMensaje !== null) {
		// Mostrar la informacion en color verde
		$('#informacionRespuestaDebitos').text(resultado.informacionMensaje);
	} else {
		$('#informacionRespuestaDebitos').text("");
	}

}
