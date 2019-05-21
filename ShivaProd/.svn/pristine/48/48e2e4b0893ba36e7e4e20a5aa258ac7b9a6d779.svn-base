$(document).ready(function() {
	$.validarCriterioBusqueda = function(criterio) {
		return /^[0-9\%]{0,10}$/.test(criterio);
	};
	
	$.cuitValidarFormato = function(cuit) {
		return /^[0-9]{2}(-[0-9]{8}-|[0-9]{8})[0-9]$/.test(cuit);
	};
	$.cuitValidarDosPrimerodDigitos = function(cuit) {
	var dig = cuit.substring(0, 2);
		if (
			dig == "27" ||
			dig == "20" ||
			dig == "23" ||
			dig == "24" ||
			dig == "30" ||
			dig == "33" ||
			dig == "34"
		) {
			return false;
		}
		return true;
	};

	// Cre3o que esta alrbves
	$.cuitValidarDigitoVerificador = function(cuit) {
		cuit = cuit.toString().replace(/[-_]/g, "");
		var codigo_valido = true;
		if (cuit.length != 11) {
			codigo_valido = false;
		} else {
			var mult = [ 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 ];
			var total = 0;
			for (var i = 0; i < mult.length; i++) {
				total += parseInt(cuit[i]) * mult[i];
			}
			var mod = total % 11;
			var digito = mod == 0 ? 0 : mod == 1 ? 9 : 11 - mod;
			codigo_valido = digito == parseInt(cuit[10]);
		}
		return codigo_valido;
	};
	$.validacionNumeroCantDigitos = function(valor, cantidadDigs) {
		return (/^\d+$/.test(valor) && valor.length == cantidadDigs);
	};
	$.validacionIsCaracterNumericoComaPunto = function(valor) {
		return /[0-9,\.]/.test(valor);
	};
	
	$.validarImportePuntoMilesYOComaDecimales = function(valor) {
		return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:.[0-9]{3})+)(\,\d+)$/.test(valor);
	};
	$.validacionImportePuntoMilesSinDecimales2 = function(valor) {
		return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:.[0-9]{3})+)$/.test(valor);
	};
	$.validacionImporteDecimales = function(valor) {
		return /^\d+(,\d+)?$/.test(valor);
	};
	$.validacionCaracteresEspeciales = function(valor) {
		return /^\s*[a-zA-Z0-9,\s,áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ,\{\}\[\]\(\)\.\,\;\:\!\¡\¿\?\@\#\%\-\+\$]*\s*$/.test(valor);
	};
	$.validacionIsNumeric = function(valor) {
		return /^[0-9]+$/.test(valor);
	};
	
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
	$.isNullOrEmail = function(valor) {
		return /^[a-zA-Z0-9]+[a-zA-Z0-9_\-\.]*@([a-zA-Z0-9\-]+){1}(\.[a-zA-Z0-9]+){1,3}$/.test(valor);
	};
	$.isFormatoRecibo = function(valor) {
		return /^[D|0-9][0-9]{3}-[0-9]{8}$/.test(valor);
	};
});
//Valida los imputs numericos con decimales
function validarInputNumericosComaPunto(event) {
	return $.validacionIsCaracterNumericoComaPunto(
		String.fromCharCode(event.keyCode)
	);
};

var validarImporteValido = function(importe) {
	var importeFloat = false;

	if (importe !== '-' && importe !== '') {
		if ($.validarImportePuntoMilesYOComaDecimales(importe)) {
			importeFloat = true;
		} else if ($.validacionImportePuntoMilesSinDecimales2(importe)) {
			importeFloat = true;
		} else if ($.validacionImporteDecimales(importe)) {
			importeFloat = true;
		}
	}
	return importeFloat;
};
var validarImporteValidoNegativo = function(importe) {
	var salida = false;
	if (importe.length > 1) {
		if (importe[0] === '-') {
			var importePositivo = importe.substring(1, importe.length);
			if (validarImporteValido(importePositivo)) {
				salida = true;
			}
		} else {
			salida = false;
		}
	}
	return salida;
};
var msgSalir = 'Se pueden perder los cambios.';
var beforeUnload = {
	'enable': true,
	'unbind': function() {
		$(window).unbind("beforeunload");
	},
	'off': function() {
		$(window).off('beforeunload');
	},
	'on': function() {
		$(window).on('beforeunload', function(e) {
			$('#bloqueEspera').trigger('click');
			if (!e) {
				e = window.event;
			} else {
				e.returnValue = msgSalir;
			}
			setTimeout(function() {
				ocultarBloqueEspera();
			}, 1000);
			return msgSalir;
		});
	}
};
var isUndefinedNullDashEmptyOrFalse = function(valor) {
	var salida = false;
	if (undefined === valor) {
		salida = true;
	} else if (null === valor) {
		salida = true;
	} else if ($.isEmpty(valor)) {
		salida = true;
	} else if ('-' === valor) {
		salida = true;
	}
	return salida;
};
