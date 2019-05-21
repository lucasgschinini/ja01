var edicionValorDto = null;

var combos = {
	acuerdo : null,
	banco : null,
	bancoCuenta : null,
	optionVacia: null,
	origen: null
};
var acuerdoBancoCuenta = null;

var combosAcuerdoBancoDepositoCuenta = {
	acuerdo : null,
	bancoDeposito: null,
	bancoCuenta : null
};

function agregarOpcionCombo(comboId, valor, descripcion) {
	var opt = document.createElement("option");
	comboId.options.add(opt);
	opt.text = descripcion;
	opt.value = valor;
};


$(document).ready(function() {
	/*************************************************************************/
	 $('#checkEnviarMailBoleta').click(function(){
			document.getElementById("checkImprimirBoleta").checked=false;
			document.getElementById("observacionMail").disabled=false;
			$('#labelEmail').attr("style","display:inline;");
		 });
		 
		 $('#checkImprimirBoleta').click(function(){
				document.getElementById("observacionMail").value = "";
				document.getElementById("checkEnviarMailBoleta").checked=false;
				document.getElementById("observacionMail").disabled=true;
				$('#labelEmail').attr("style","display:none;");
		});
	/*************************************************************************/
//	 $("#selectEmpresa").change(function () {
//		$('#bloqueEspera').trigger('click');
//		$('#formAlta')[0].action=urlAsynchronousHTTP + "/seleccionoEmpresa";
//		$('#formAlta')[0].submit();
//	 });
	var segPrev = '';
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
			
			$('#selectOrigen option').remove();
			$('#selectAcuerdo option').remove();
			$('#selectBancoDeposito option').remove();
			$('#selectCuenta option').remove();
			
		} else if (this.value != segPrev) {
			$('#bloqueEspera').trigger('click');
			segPrev = this.value;
			$.ajax({
				"dataType" : 'json',
				"type" : "GET",
				"url" : 'boleta/buscarCopropietarios',
				"data" : {
					idEmpresa : $("#selectEmpresa").val(),
					idSegmento : this.value
				},
				"success" : function(result) {
					var options = [{
						text : leyendaComboSeleccionar,
						value : ''
					}];
					$.each(result, function(index, option) {
						options.push(option);
					});
					$("#selectCopropietario").replaceOptions(options);
					
					completarComboOrigen();
					completarCombosAcuerdoBancoDepositoCuenta($('#selectOrigen').val());
					ocultarBloqueEspera();
				}
			});
		}
	});
	 

	 
	 
	 $('#btnAceptar').click(function() {
		 $('#bloqueEspera').trigger('click');
			var boletaSinValorDto = procesarFormValor();
			var listaErrores = [];
			$('#divAlertErrorFoot').hide();
			$('#alertErrorFoot').val('');
			$('.error').text('');
			$('#alertError').text('');
			$('#divAlertError').hide();
			listaErrores = validarFormValor(boletaSinValorDto);
			if (!$.isEmpty(listaErrores)) {
				var sizeErrores = listaErrores.length;
				for (var u = 0; u < sizeErrores; u++) {
					$('#'+listaErrores[u].campo).text(listaErrores[u].mensaje);
				}
				ocultarBloqueEspera();
			} else {
				$.ajax({
					"dataType" : 'json',
					"type" : "POST",
					"url" : 'procesar-alta-boleta',
					"data" : JSON.stringify(eval(boletaSinValorDto)),
					"contentType" : "application/json",
					"mimeType" : "application/json",
					"success" : function(result) {
						if (result.success) {
							$('#mensajeAlta').val(result.mensajeAlta);
							$('#url').val(result.url);
							$("#imprimible").val(result.imprimible);
							$('#mensajesMostrarEnvioMail').val(result.mensajesMostrarEnvioMail);
							$('#formBoleta')[0].action = urlAsynchronousHTTP + "/" + result.action;
							$('#formBoleta')[0].submit();
						}
						ocultarBloqueEspera();
					}
				});
			}
	 });
	if (!$.isEmpty($("#selectSegmento").val()) && $.isEmpty(segPrev)) {
		$("#selectSegmento").change();
	}
});

function crearOptionOrigen(id) {
	var size = paramTipoGestion.listaOrigenes.length;
	for (var i = 0; i < size; i++) {
		if (paramTipoGestion.listaOrigenes[i].idOrigen == id) {
			return new Option(paramTipoGestion.listaOrigenes[i].descripcion, paramTipoGestion.listaOrigenes[i].idOrigen);
		}
	}
}
function completarComboOrigen() {
	var optioTipoValor = null;

	optioTipoValor = [];
	if (esAnalista) {
		optioTipoValor.push(crearOptionOrigen(2));
	} else {
		optioTipoValor = [{
			text : leyendaComboSeleccionar,
			value : ''
		}];
		var size = paramTipoGestion.listaOrigenes.length;
		for (var i = 0; i < size; i++) {
			if (paramTipoGestion.listaOrigenes[i].idOrigen == id) {
				optioTipoValor.push(new Option(paramTipoGestion.listaOrigenes[i].descripcion, paramTipoGestion.listaOrigenes[i].idOrigen));
			}
		}
	}
	$('#selectOrigen').replaceOptions(optioTipoValor);
	if (optioTipoValor.length = 1) {
		if ($.isEmpty(edicionValorDto)) {
			$('#selectOrigen option:eq(1)').prop('selected', 'selected').change();
		}	
	} else {
		$('#selectOrigen').val('');
	}
	if (!$.isEmpty(edicionValorDto)) {
		seleccionarTipoValor();
	}
}
function completarCombosAcuerdoBancoDepositoCuenta(origen) {
	var seg = $('#selectSegmento').val();
	var tipoValor = 1;
	var size = paramTipoGestion.lista.length;
	var segmentoEncontrado = false;
	
	for(var index = 0; index < size; index++) {
		if (
			paramTipoGestion.lista[index].idSegmento === seg &&
			paramTipoGestion.lista[index].idTipoValor == tipoValor &&
			paramTipoGestion.lista[index].idOrigen == origen
		) {
			segmentoEncontrado = true;
			acuerdoBancoCuenta = paramTipoGestion.lista[index].combo;
			crearOptionCombosAcuerdoBancoDepositoCuenta(paramTipoGestion.lista[index].combo);
			break;
		} else {
			if (segmentoEncontrado) {
				break;
			}
		}
	}
}

function crearOptionCombosAcuerdoBancoDepositoCuenta(combo) {
//	combosAcuerdoBancoDepositoCuenta
	var size = combo.listaAcuerdos.length;
	var sizeCuenta = combo.listaCuentas.length;
	var ops;
	
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	
 	
	var op = null;
	
 	
 	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaAcuerdos[index].descripcion, combo.listaAcuerdos[index].idAcuerdo);
		ops.push(op);
	}
	size = combo.listaBancos.length;
	combosAcuerdoBancoDepositoCuenta.acuerdo = ops;
	$('#selectAcuerdo').replaceOptions(ops);
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	for (var index = 0; index < size; index++) {
		op = new Option(combo.listaBancos[index].descripcion/* + ' - ' + combo.listaBancos[index].idBanco*/, combo.listaBancos[index].idBanco);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.banco = ops;
	
	$('#selectBancoDeposito').replaceOptions(ops);
	if (sizeCuenta != 1) {
		ops = [new Option(leyendaComboSeleccionar, '')];
	} else {
		ops = [];
	}
	for (var index = 0; index < sizeCuenta; index++) {
		op = new Option(combo.listaCuentas[index].cuentaBancaria, combo.listaCuentas[index].idBancoCuenta);
		ops.push(op);
	}
	combosAcuerdoBancoDepositoCuenta.bancoCuenta = ops;
	$('#selectCuenta').replaceOptions(ops);
	
	if (combosAcuerdoBancoDepositoCuenta.bancoCuenta.length = 1) {
		$('#selectCuenta').val();
	}
	
}
var procesarFormValor = function() {
	var boletaDto = new Object();
	// Datos de cliente!!!
	boletaDto.tipoValor = 1;
	boletaDto.idTipoValor = 1;
	boletaDto.empresa = $('#selectEmpresa').val();
	boletaDto.idEmpresa = $('#selectEmpresa').val();
	
	boletaDto.segmento = $('#selectSegmento').val();
	boletaDto.idSegmento = $('#selectSegmento').val();
	
	
	boletaDto.codCliente = $("#codCliente").val();
	boletaDto.razonSocial = $('#razonSocial').val();
	
	
	boletaDto.codClienteAgrupador = $("#codClienteAgrupador").val();
	boletaDto.razonSocialClienteAgrupador = $('#razonSocialClienteAgrupador').val();
	
	
	boletaDto.numeroHolding = $('#numeroHolding').val();
	boletaDto.nombreHolding = $('#nombreHolding').val();
	
	
	boletaDto.email = $('#email').val();
	
	
	boletaDto.nombreAnalista = $('#nombreAnalista').val();
	boletaDto.idAnalista = $('#idAnalista').val();
	boletaDto.mailAnalista = ''; 												// No veo que se carge en ningun lado
	boletaDto.idCopropietario = $('#selectCopropietario').val();
	
	
	boletaDto.importe = $('#importe').val();
	boletaDto.idMotivo = $('#selectMotivo').val();
	boletaDto.operacionAsociada = $('#operacionAsociada').val();
	
	
	boletaDto.idOrigen = $('#selectOrigen').val();
	
	
	boletaDto.idAcuerdo = $('#selectAcuerdo').val();
	boletaDto.idBancoDeposito = $('#selectBancoDeposito').val();
	boletaDto.idNroCuenta = $('#selectCuenta').val();

	
	boletaDto.observaciones = $('#observaciones').val();
	boletaDto.observacionMail = $('#observacionMail').val();
	
	
	if ($('#checkImprimirBoleta').prop('checked')) {
		boletaDto.imprimirBoleta = true;
		boletaDto.checkImprimirBoleta = true;
	} else {
		boletaDto.imprimirBoleta = false;
		boletaDto.checkImprimirBoleta = false;
	}
	if ($('#checkEnviarMailBoleta').prop('checked')) {
		boletaDto.enviarBoletaMail = true;
		boletaDto.checkEnviarMailBoleta = true;
	} else {
		boletaDto.enviarBoletaMail = false;
		boletaDto.checkEnviarMailBoleta = false;
	}

	return boletaDto;
};
function validarFormValor(boletaDto) {
	var listaErrores = [];
	var validador = crearValidador(boletaDto);
	var metodosValidador = Object.keys(validador);
	
	var size = metodosValidador.length;
	for (var i = 0; i < size; i++) {
		var error = validador[metodosValidador[i]](boletaDto);
		if (!$.isEmpty(error)) {
			listaErrores.push(error);
		}
	}
	return listaErrores;
}
function procesarAltaBoleta(){
	$('#bloqueEspera').trigger('click');
	$('#formAlta')[0].action=urlAsynchronousHTTP + "/procesar-alta-boleta";
	$('#formAlta')[0].submit();
}