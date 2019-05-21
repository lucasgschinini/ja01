/***********************************************************
 * Validaciones y Funciones para cobros
 **********************************************************/
$(document).ready(function() {
	$.validator.addMethod("importe", function(value, element) {
		return /^[0-9]{1,3}(\.[0-9]{3})*\,([0-9]{2})+$/
				.test(value);
	}, "Este campo debe respetar el formato 999.999.999,99");

	$.validator.addMethod("importeConDecimales", function(
			value, element) {
		return /^[0-9]+\,([0-9]{1}|([0-9]{2}))$/.test(value);
	}, "Este campo debe respetar el formato 999.999.999,99");
	
// Funciones que no se utilizan y pueden utilizarse en otro lugar!!!
//	(function($) {
//		$.cuitValidarFormato = function(cuit) {
//			return /^[0-9]{2}-[0-9]{8}-[0-9]$/.test(cuit);
//		};
//	})(jQuery);
//
//	(function($) {
//		$.cuitValidarDosPrimerodDigitos = function(cuit) {
//			var dig = cuit.substring(0, 2);
//			if (dig == "27" || dig == "20" || dig == "23"
//					|| dig == "24" || dig == "30"
//					|| dig == "33")
//				return false;
//			return true;
//		};
//	})(jQuery);
//
//	(function($) {
//		$.cuitValidarDigitoVerificador = function(cuit) {
//			cuit = cuit.toString().replace(/[-_]/g, "");
//			var codigo_valido = true;
//			if (cuit.length != 11)
//				codigo_valido = false;
//			else {
//				var mult = [ 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 ];
//				var total = 0;
//				for (var i = 0; i < mult.length; i++) {
//					total += parseInt(cuit[i]) * mult[i];
//				}
//				var mod = total % 11;
//				var digito = mod == 0 ? 0 : mod == 1 ? 9
//						: 11 - mod;
//				codigo_valido = digito != parseInt(cuit[10]);
//			}
//			return codigo_valido;
//		};
//	})(jQuery);

//	(function($) {
//		$.validacionNumeroCantDigitos = function(valor,
//				cantidadDigs) {
//			return (/^\d+$/.test(valor) && valor.length == cantidadDigs);
//		};
//	})(jQuery);

	(function($) {
		$.validacionNumeroMaxDigitos = function(valor,
				maxCantDigs) {
			return (/^\d+$/.test(valor) && valor.length <= maxCantDigs);
		};
	})(jQuery);

//	(function($) {
//		$.validacionImporteDecimales = function(valor) {
//			return /^\d+(,\d+)?$/.test(valor);
//		};
//	})(jQuery);
	
	(function($) {
		$.validacionImportePuntoDecimales = function(valor) {
			return /^\d+(.\d+)?$/.test(valor);
		};
	})(jQuery);
	(function($) {
		$.validarImporteComaMilesPuntoDecimales = function(
				valor) {
			return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:,[0-9]{3})+)(\.\d+)$/
					.test(valor);

		};
	})(jQuery);
//	(function($) {
//		$.validarImportePuntoMilesYOComaDecimales = function(
//				valor) {
//			return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:.[0-9]{3})+)(\,\d+)$/
//					.test(valor);
//
//		};
//	})(jQuery);
//	(function($) {
//		$.validacionIsCaracterNumericoComaPunto = function(
//				valor) {
//			return /[0-9,\.]/.test(valor);
//		};
//	})(jQuery);

	

	
	(function($) {
		$.formatoFecha = function(valor) {
			return /(0[1-9]|[12][0-9]|3[01])[\/](0[1-9]|1[012])[\/](19|20)\d\d/
					.test(valor);
		};
	})(jQuery);

	(function($) {
		$.validacionFecha = function(valor) {
			if (/(0[1-9]|[12][0-9]|3[01])[\/](0[1-9]|1[012])[\/](19|20)\d\d/
					.test(valor)) {
				var parts = valor.split("/");
				var day = parseInt(parts[0], 10);
				var month = parseInt(parts[1], 10);
				var year = parseInt(parts[2], 10);
				if (year < 1000 || year > 3000 || month == 0
						|| month > 12) {
					return false;
				}
				var monthLength = [ 31, 28, 31, 30, 31, 30, 31,
						31, 30, 31, 30, 31 ];
				if (year % 400 == 0
						|| (year % 100 != 0 && year % 4 == 0)) {
					monthLength[1] = 29;
				}
				return day > 0 && day <= monthLength[month - 1];
			}
			return false;
		};
	})(jQuery);

	(function($) {
		$.validacionCantCaracteres = function(valor, cantidad) {
			return valor.length <= cantidad;
		};
	})(jQuery);

//	(function($) {
//			$.validacionImportePuntoMilesSinDecimales2 = function(valor) {
//				return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:.[0-9]{3})+)$/
//					.test(valor);
//			};
//		})(jQuery);
		
		
		(function($) {
			$.validarImportePuntoMilesYOComaDecimales2 = function(valor) {
				return /^(?:0|[1-9][0-9]{3,}|[1-9][0-9]{0,2}(?:.[0-9]{3})+)(\,\d{2})$/.test(valor);
			};
		})(jQuery);
});

// Convierte importe con formato 999.999.999,99 a 999999999,99 o '999999999.99'
// a float
var importeToFloat = function(importe) {
	var importeFloat = 0.00;

	if (typeof(importe) == 'number') {
		return importe;
	}
	if (importe !== '-' && importe !== '') {
		importe = importe.trim();
		var negativo = importe.split('-');
		importe = importe.split('-').join('');
		if ($.validarImportePuntoMilesYOComaDecimales(importe)) {
			importeFloat = desformatear(importe);
		} else if ($.validacionImportePuntoMilesSinDecimales2(importe)) {
			importeFloat = desformatear(importe);
		} else if ($.validacionImporteDecimales(importe)) {
			importeFloat = desformatear(importe);
		} else {
			importeFloat = parseFloat(importe);
		}
		if (negativo.length > 1) {
			importeFloat = importeFloat * -1;
		}
		return importeFloat;
	}
	return importe;
};

//var validarImporteValido = function(importe) {
//	var importeFloat = false;
//
//	if (importe !== '-' && importe !== '') {
//		if ($.validarImportePuntoMilesYOComaDecimales(importe)) {
//			importeFloat = true;
//		} else if ($.validacionImportePuntoMilesSinDecimales2(importe)) {
//			importeFloat = true;
//		} else if ($.validacionImporteDecimales(importe)) {
//			importeFloat = true;
//		}
//	}
//	return importeFloat;
//};
//var validarImporteValidoNegativo = function(importe) {
//	var salida = false;
//	if (importe.length > 1) {
//		if (importe[0] === '-') {
//			var importePositivo = importe.substring(1, importe.length);
//			if (validarImporteValido(importePositivo)) {
//				salida = true;
//			}
//		} else {
//			salida = false;
//		}
//	}
//	return salida;
//};
// Se espera un valor con formato 9,999,999.0000 ó 9999999.0000
// Se retorna formato 9999999,00
function truncarImporte(importe) {
	if (!$.isEmpty(importe)) {
		importe = importe.replace(/[,]/g, '').replace(/[.]/g, ',');
		var lastIndex = importe.lastIndexOf(',');
		return importe.substring(0, lastIndex + 3);
	} else {
		return "";
	}
}
// trunco un importe con mas de 2 decimales (No rendondeo)
// 1.606,2500 a 1.606,25
function truncoADosDecimalesImporte(importe, moneda) {
	if (importe === '-') {
		return importe;
	}
	var numeroDescompuesto = importe.split(',');
	if (numeroDescompuesto.length == 2) {
		if (numeroDescompuesto[1].length > 2) {
			numeroDescompuesto[1] = numeroDescompuesto[1].substring(0,2);
		}
		importe = numeroDescompuesto.join(',');
	}
	formatear(importeToFloat(importe));
	return ((moneda != null) ? returnSignoMoneda(moneda) : '')
			+ formatear(importeToFloat(importe));
}
function truncoADosDecimales(vector) {
	if (vector.length > 1) {
		if (vector[1].length < 2) {
			vector[1] += "0";
		} else if (vector[1].length > 2) {
			vector[1] = vector[1].substring(0, 2);
		}
	} else {
		vector[1] = "00";
	}
	return vector;
}
// Formateo de numerico a "999.999.999,99"
function formatear(importe) {
	if (/-$/.test(importe)) {
		return 0;
	}
	var decimal = importe.toFixed(2).toString().split('.');
	var regex = /(\d+)(\d{3})/;
	while (regex.test(decimal[0])) {
		decimal[0] = decimal[0].replace(regex, '$1' + '.' + '$2');
	}
	decimal = truncoADosDecimales(decimal);
	return decimal.join(',');
}
function desformatear(importe) {
	return parseFloat(importe.split('.').join('').split(',').join('.'));
};

function returnSignoMoneda(moneda) {
	var signo = '';
	if (moneda == 'Pesos') {
		signo = '$';
	} else if (moneda == 'Dolares') {
		signo = 'U$S';
	} else if (moneda == 'Euros') {
		signo = '€';
	} else if (moneda == '$') {
		signo = moneda;
	} else if (moneda == 'U$S') {
		signo = moneda;
	} else if (moneda == '€') {
		signo = moneda;
	} else if (moneda == 'PES') {
		signo ='$';
	} else if (moneda == 'DOL') {
		signo ='U$S';
	} else if (moneda == 'EUR') {
		signo ='€';
}
	return signo;
};
function returnNameMoneda(moneda) {
	var name = '';
	if (moneda === '$') {
		name = 'PES';
	} else  if (moneda === 'U$S') {
		name = 'DOL';
	} else  if (moneda === '€') {
		name = 'EUR';
	}
	return name;
};
function completarCerosADerecha(id) {
	var mascara = ['0','0','0','0','0','0','0','0','0','0'];
	var sizeMascara = mascara.length;
	var size = id.length;
	
	//si el id tiene mas de diez posiciones se retorna
	if (size == sizeMascara || size == 11) {
		return id;
	}
	if (size < sizeMascara) {
		for (var i = sizeMascara - 1, j = 1; i > 0 ; i--, j++) {
			if (j <= size) {
				mascara[i] = id.charAt(size-j);
			} else {
				break;
			}
		}
	}
	return mascara.join('');
};

function eliminaCerosADerecha(id) {
	var lastIndex = -1;
	var size = id.length;
	for (var i= 0; i < size; i++) {
		if (id[i] != '0') {
			break;
		} else {
			lastIndex = i;
		}
	}
	if (lastIndex >= 0) {
		return id.substring(lastIndex + 1, size);
	}
	return id;
};
// Verificar que si estan visibles
function checkAllVisibleSelected(leaderCheckId, classNameChecks, table) {
	var cant = $('.' + classNameChecks + ':visible', table.fnGetNodes()).length;
	var checks = 0;
	$('.' + classNameChecks + ':visible', table.fnGetNodes()).each(
			function(i, elem) {
				if ($(this).is(":checked"))
					checks = checks + 1;
			});
	$('#' + leaderCheckId).prop('checked', (cant == checks));
};

// Verificar todo si estan seleccionados
function verifyAllSelected(leaderCheckId, classNameChecks, table, searchBtnId) {
	var allSelected = true;
	var allClientesId11Pos = true;
	$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
		if (!$(this).is(":checked")) {
			allSelected = false;
//			return false;
		}else{
//			if($('.' + classNameChecks, table.fnGetNodes()).prevObject[i].cells[1].innerText.length <= 10) {
			if (leaderCheckId !== 'trasladarTodosIntereses') {
				if(table.fnGetData($(elem).closest('tr')).idClienteLegado.length <= 10) {
					allClientesId11Pos = false; 
				}
			}
		}
	});
	
	$('#' + leaderCheckId).prop('checked', allSelected);
	if(searchBtnId == 'btnBuscarDebitos'){
		$('#' + searchBtnId).prop('disabled', !($('.' + classNameChecks + ':checked', table.fnGetNodes()).length > 0) || allClientesId11Pos);
	}else{
		$('#' + searchBtnId).prop('disabled', !($('.' + classNameChecks + ':checked', table.fnGetNodes()).length > 0));
	}
		
};
function validarInputNumerico(event) {
	return $.validacionIsNumeric(String.fromCharCode(event.keyCode));
};
////Valida los imputs numericos con decimales
//function validarInputNumericosComaPunto(event) {
//	return $.validacionIsCaracterNumericoComaPunto(
//		String.fromCharCode(event.keyCode)
//	);
//};

function validarInputNumericosEnteros(event) {
	return $.validacionIsNumeric(
		String.fromCharCode(event.keyCode)
	);
};
// Seleccionar todos o deseleccionar
function checkUncheckAll(classNameChecks, table, state) {
	var allClientesId11Pos = true; 
	
	$('.' + classNameChecks, table.fnGetNodes()).each(function(i, elem) {
		$(this).prop('checked', state);
		if(classNameChecks === 'debClienteSel') {
			if (table.fnGetData($(elem).closest('tr')).idClienteLegado.length <= 10) {
				allClientesId11Pos = false;
			} else {
				cliente10 = true;
			}
		}
	});
	if (classNameChecks === 'debClienteSel') {
		$('#btnBuscarDebitos').prop('disabled', allClientesId11Pos || !state);
	}
	
};

// Buscar la fila
function findIndex(store, id) {
	for (var i = 0, iLen = store.length; i < iLen; i++) {
		if (store[i] && store[i].DT_RowId === id) {
			return i;
		}
	}
	return -1;
};

//function clearInputText(divId) {
//	$('input:text', '#' + divId).val('');
//};
//Limpia inputText y Select(deja seleccionado la primera opcion) contenidos en un div
function clearSearchCriteria(divId) {
	clearInputText(divId);
	$('select', '#' + divId).each(
		function(index){  
			$(this)[0].selectedIndex = 0;
		}
	);
	
};


fijarFiltroBusquedaSegunMonedaCobro = function(tipoFiltro) {
	var disabled = false;
	var valueSistema = '';
	var valueMoneda = '';
	var preId = '';
	var valueRefMic = '';

	if (tipoFiltro !== 'searchCriteraDebito') {
		preId = 'credS';
	} else {
		preId = 's';
	}
	
	switch (_monedaDelCobro) {
	case MONEDA_DOLAR_SIGNO_2:
		valueSistema = CALIPSO_DESC_CORTA;
		valueMoneda = MONEDA_DOLAR_SIGLA;
		disabled = true;
		break;
	}
	$('#' + preId + 'electSistema').val(valueSistema);
	$('#' + preId + 'electSistema').attr('disabled', disabled);
	$('#' + preId + 'electMoneda').val(valueMoneda);
	$('#' + preId + 'electMoneda').attr('disabled', disabled);
	if (tipoFiltro === 'searchCriteraDebito') {
		$('#refMIC').val(valueRefMic);
		$('#refMIC').attr('disabled', disabled);
	}
};
/******************************************************************************/
/* Metodos para controlar el bloque de espera cuando se realizan varias       */
/* llamadas ajax                                                              */
/******************************************************************************/
var flagBloqueDeEspera = {
	'comando' : null,
	'comandoName' : '',
	'comandoParam' : null,
	'fCorte': false,
	'fFlags' : [],
	'validar': function() {
		var resultado = true;
		for (var i = 0, size = this.fFlags.length; i < size; i++ ) {
			if (!this[this.fFlags[i]]) {
				resultado = false;
				break;
			}
		}
		return resultado;
	},
	'inicializar': function(nombreFlags) {
		$('#bloqueEspera').trigger('click');
		this.clear();
		this.fFlags = nombreFlags;
		for (var i = 0, size = this.fFlags.length; i < size; i++ ) {
			this[this.fFlags[i]] = false;
		}
		
	},
	'clear': function() {
		this.fCorte = false;
		for (var property in this) {
			var ignorar = ['fCorte', 'fFlags', 'validar', 'inicializar', 'clear', 'terminar', 'comando', 'comandoName', 'comandoParam'];
			if (ignorar.indexOf(property) < 0) {
				delete(this[property]);
			}
		}
		this.fFlags.length = 0;
		this.comando = null;
		this.comandoName = '';
		this.comandoParam = null;
	},
	'terminar': function() {
		for (var property in this) {
			var ignorar = ['fCorte', 'fFlags', 'validar', 'inicializar', 'clear', 'terminar', 'comando', 'comandoName', 'comandoParam'];
			if (ignorar.indexOf(property) < 0) {
				this[property] = true;
			}
		}
		this.fFlags.length = 0;
	}
};
function finBloqueDeEspera() {
	if(flagBloqueDeEspera.fCorte == false) {
		if (flagBloqueDeEspera.validar() == true) {
			flagBloqueDeEspera.fCorte = true;
			if (!$.isEmpty(flagBloqueDeEspera.comando)) {
				if ($.isEmpty(flagBloqueDeEspera.comandoName)) {
					if ($.isEmpty(flagBloqueDeEspera.comandoParam)) {
						flagBloqueDeEspera.comando.ejecutar();
					} else {
						flagBloqueDeEspera.comando.ejecutar(flagBloqueDeEspera.comandoParam);
					}
				} else {
					if ($.isEmpty(flagBloqueDeEspera.comandoParam)) {
						flagBloqueDeEspera.comando[flagBloqueDeEspera.comandoName]();
					} else {
						flagBloqueDeEspera.comando[flagBloqueDeEspera.comandoName](flagBloqueDeEspera.comandoParam);
					}
				}
				// el fin del bloque de espera deberia estar en la tarea que se ejecuta
				//ocultarBloqueEspera();
			} else {
				ocultarBloqueEspera();
			}
		} else {
			window.setTimeout(finBloqueDeEspera, 1000);
		}
	}
};

//Agregar Botones de Excel

function agregarBotonExcelBasico(table, titulo,divBoton) {
	
	new $.fn.dataTable.Buttons( table, {
	    buttons: [{
	    	extend:'excelHtml5',
	    	text:'Excel',
	    	title: titulo,
	    	className: 'excelbtn',
	    	mColumns: "visible"
	    }]
	} );
	
	table.buttons(0, null).container().appendTo(
			divBoton
	);

}



function agregarBotonExcelTodosDebitos(table,titulo,divInsertar){
	
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{
			extend : "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	if (column === 0) {
	                    	return '';
	                    }
	    				if (column === 22) {
	    					if (data != '-') {
	    						return data.replace(/<br>/gi, ' ');
	    					}
	                    }  
		                return data;
		            }
				}
			}
		}]
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}

function agregarBotonExcelBasicoDebitosSelSettings(table,titulo,divInsertar){
	
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [  {
			extend : "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	if (column === 0) {
		                	return '';
		                }
	                	if (column === 14 || column === 15 || column === 28) {
							if (data != '-') {
								if (data.toLowerCase().indexOf("checked") >= 0) {
									return "S";
								} else {
									return "N";
								}
							}
	                    }
	                	// Columna "Importe a Cobrar"
	                	if (column === 16) {
	                		if (!$.isEmpty(data) && data !== '-') {
	                			return $('#selectMonedaOperacion').val() + $('input', data).attr('value');
	                		}
		                } 
						if (column === 25) {
							if (data != '-') {
								return data.replace(/<br>/gi, ' ');
							}
	                    } 
		                return data;
		            }
				}
			 }
		}]
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}
function agregarBotonExcelBasicoOtrosDebitosSelSettings(table,titulo,divInsertar){
	
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [  {
			extend : "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
					body: function ( data, column, row ) {
						if (column === 0 || column === 14 || column === 15) {
							return '';
						}
						if (column === 10) {
							if (!$.isEmpty(data) && data !== '-') {
								return $('#selectMonedaOperacion').val() + $('input', data).attr('value');
							}
						} 
						if (column === 12) {
							if (data != '-') {
								if (data.toLowerCase().indexOf("checked") >= 0) {
									return "S";
								} else {
									return "N";
								}
							}
						}
						// Columna "Importe a Cobrar"
						if (column === 10) {
							if (data != '-') {
								return data.replace(/<br>/gi, ' ');
							}
						} 
						return data;
					}
				}
			}
		}]
	
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}

function agregarBotonExcelAgregarTodosCreditos(table,titulo,divInsertar){
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{	
			extend: "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                    if (column === 0) {
	                    	return '';
	                    }
	    				if (column === 31) {
	    					if (data != '-') {
	    						return data.replace(/<br>/gi, ' ');
	    					}
	                    }
	    				if (column === 32) {
	    					if (data != '-') {
	    						if (data.toLowerCase().indexOf("checked") >= 0) {
	    							return "S";
	    						} else {
	    							return "N";
	    						}
	    					}
	                    }
		                return data;
		            }
				}
			}
		}]
	 });
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}

function agregarBotonExcelEliminarTodosCreditos(table,titulo,divInsertar)
{
	
	new $.fn.dataTable.Buttons(table,{
		buttons: [{	
			extend : "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	    	            if (column === 0) {
	    	            	return '';
	    	            }
	    	            // Columna "Importe a Cobrar"
	    	            if (column === 14) {
	    	            	if (!$.isEmpty(data) && data !== '-') {
	    	            		return $('#selectMonedaOperacion').val() + $('input', data).attr('value');
	    	            	}
	    	            } 
	    	        	if (column === 32) {
	    					if (data != '-') {
	    						return data.replace(/<br>/gi, ' ');
	    					}
	    	            }
	    	        	if (column === 33) {
	    					if (data != '-') {
	    						if (data.toLowerCase().indexOf("checked") >= 0) {
	    							return "S";
	    						} else {
	    							return "N";
	    						}
	    					}
	    	            } 
		                return data;
		            }
				}
			}
		}]
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}
// Otros medio de pago

function agregarBotonExcel(table, titulo, divInsertar, fnBody) {
	new $.fn.dataTable.Buttons(table, {
		buttons: [{	
			extend : "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
					body : fnBody
				}
			}
		}]
	}  );
	if (divInsertar !== null) {
		table.buttons(0, null).container().appendTo(
				divInsertar
		);
	}
	
}
//Previsualizaciones

function agregarBotonExceldebitosPrev(table,titulo,divInsertar)
{
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{	
			extend: "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	if (column === 13 || column === 14 || column === 27) {
	    					if (data != '-') {
	    						if (data.toLowerCase().indexOf("checked") >= 0) {
	    							return "S";
	    						} else {
	    							return "N";
	    						}
	    					}
	                    }
	    				if (column === 24) {
	    					if (data != '-') {
	    						return data.replace(/<br>/gi, ' ');
	    					}
	                    }
		                return data;
		            }
				}
			}
		}]
		
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}

function agregarBotonExcelOtrosDebitosPrev(table,titulo,divInsertar)
{
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{	
			extend: "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	if (column === 11) {
	    					if (data != '-') {
	    						if (data.toLowerCase().indexOf("checked") >= 0) {
	    							return "S";
	    						} else {
	    							return "N";
	    						}
	    					}
	                    }
//	    				if (column === 24) {
//	    					if (data != '-') {
//	    						return data.replace(/<br>/gi, ' ');
//	    					}
//	                    }
		                return data;
		            }
				}
			}
		}]
		
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}
function agregarBotonExcelcreditosPrev(table,titulo,divInsertar)
{
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{	
			extend: "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	if (column === 32) {
	    					if (data != '-') {
	    						if (data.toLowerCase().indexOf("checked") >= 0) {
	    							return "S";
	    						} else {
	    							return "N";
	    						}
	    					}
	                    }
	    				if (column === 31) {
	    					if (data != '-') {
	    						return data.replace(/<br>/gi, ' ');
	    					}
	                    }
		                return data;
		            }
				}
			}
		}]
		
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}


function agregarBotonExceltransaccionesPrev(table,titulo,divInsertar)
{

	var moneda = '';
	var monedaMedioPago = '';
	
	new $.fn.dataTable.Buttons(table,{
		
		buttons: [{	
			extend: "excelHtml5",
			text:"Excel",
			title: titulo,
			className: 'excelbtn',
			exportOptions: {
				columns: ':visible',
				format: {
	                body: function ( data, column, row ) {
	                	
	                	
						if (column === 9) {
							if (!$.isEmpty(data) && data !== '-') {
								moneda = data;
							}else{
								moneda = '-';
							}
						}
						
						if (column === 20) {
							if (!$.isEmpty(data) && data !== '-') {
								monedaMedioPago = data;
							}else{
								monedaMedioPago = '-';
							}
							
						}
						
						// Columna "Trasladar Intereses"
						if (column === 25) {
							if (!$.isEmpty(data) && data != '-') {
								if (data.toLowerCase().indexOf("checked") >= 0) {
									return "SI";
								} else {
									return "NO";
								}
							}
						}
						// Columna "% a Bonificar"
						if (column === 26) {
							//esta funcion se usa porque en el data viene el valor del input, con esta funcion se extrae el valor de la celda
							data = $('input', data).attr('value');
							if (!$.isEmpty(data) && data !== '-') {
								return data + "%";
							}
							return '-';
						}
						
						
						// Columna "Importe a Bonificar"
						if (column === 27) {
							//esta funcion se usa porque en el data viene el valor del input, con esta funcion se extrae el valor de la celda
							data = $('input', data).attr('value');
							if (!$.isEmpty(data) && data !== '-') {
								if(!$.isEmpty(moneda) && moneda !== '-'){
									return moneda + data;
								}else{
									return monedaMedioPago + data;
								}
							}
							return '-';
						}
						// Columna "Acuerdo Facturacion Destino Cargos"
						if (column === 28) {
							//esta funcion se usa porque en el data viene el valor del input, con esta funcion se extrae el valor de la celda
							data = $('input', data).attr('value');
							if (!$.isEmpty(data) && data !== '-') {
								return data;
							}
							return '-';
						}
		                return data;
		            }
				}
			}
		}]
		
		
	}  );
	
	table.buttons(0, null).container().appendTo(
			divInsertar
	);
}

/******************************************************************************/
var tarea = {
	'ejecutar' : function(tratamientoDiferencia) {
		if (!$.isEmpty(tratamientoDiferencia) && !$.isEmpty(tratamientoDiferencia.tipoTratamientoDiferencia)) {
			if (tratamientoDiferencia.tipoTratamientoDiferencia == 'REINTEGRO_CRED_PROX_FAC') {
				if (tratamientoDiferencia.sistemaDestino == 'MIC') {
					if ($.isEmpty(tratamientoDiferencia.acuerdoFacturacion)) {
						// Aca tengo que verificar si es nulo por que no existe clientes o nulo por que no completo
						verificarAutoCompletarCampoAcuerdo();
					} else {
						validarAcuerdoContraClienteMic($('#acuerdoFactInput'), true, true);
					}
				} else {
					if (!$.isEmpty(tratamientoDiferencia.acuerdoFacturacion)) {
						validarAcuerdoContraClienteCLP($('#acuerdoFactInput'), true, true);
					} else {
						ocultarBloqueEspera();
					}
				}
			} else {
				ocultarBloqueEspera();
			}
		} else {
			ocultarBloqueEspera();
		}
		flagBloqueDeEspera.comandoParam = '';
		flagBloqueDeEspera.comandoName = '';
		flagBloqueDeEspera.comando = null;
	}
};  
var tareaHabilitarBtn = {
	'ejecutar' : function() {
		      
		$('#' + flagBloqueDeEspera.comandoParam).prop('disabled', false);
		flagBloqueDeEspera.comandoParam = '';
		flagBloqueDeEspera.comandoName = '';
		flagBloqueDeEspera.comando = null;
		ocultarBloqueEspera();
		
	}
};

// TODO PARA PAU
var tareaHabilitarMotivo = {
		'ejecutar' : function() {
			habilitarMotivo(varEdicionSegunEstadoMarca, $('#selectMotivo').val());
			flagBloqueDeEspera.comandoParam = '';
			flagBloqueDeEspera.comandoName = '';
			flagBloqueDeEspera.comando = null;
			ocultarBloqueEspera();
		}
	};
var tareaHabilitarInputTratamientoDeDiferencia = {
		'ejecutar' : function() {
			$('#inputTratamientoDeDiferencia').find('input').prop('disabled', false);
			flagBloqueDeEspera.comandoParam = '';
			flagBloqueDeEspera.comandoName = '';
			flagBloqueDeEspera.comando = null;
			ocultarBloqueEspera();
			
		}
	};
// TODO Hablar con pablo I. Si se puede reemplaz el codigo
// En ves de contador re correr la lista
var detalleDebitosDelCobro = {
	'cantDuc' : 0,
	'cantNoDuc' : 0,
	'verificar': function(tipo) {
		var salida = '';
		if (tipo === 'TRATAMIENTO') {
			if (this.cantDuc > 0) {
				salida = ERROR_TRATAMIENTO_DUC;
			}
		} else if (tipo === DUC_NAME) {
			if (!$.isEmpty(datosTablas.tratamientoDiferencia.tipoTratamientoDiferencia)) {
				salida = ERROR_DUC_TRATAMIENTO; // la constante esta definidad en el JSP
			} else if (this.cantNoDuc > 0) {
				salida = ERROR_NO_DUC_TAIL;	// la constante esta definidad en el JSP
				if ($.isEmpty(datosTablas.debitosSeleccionados)) {
					this.cantDuc = 0;
					this.cantNoDuc = 0;
				}
			} else {
				saldia = '';
				this.cantDuc++;
			}
		} else if (this.cantDuc > 0) {
			salida = ERROR_DUC_TAIL;	// la constante esta definidad en el JSP
		} else {
			salida = '';
			this.cantNoDuc++;
		}
		return salida;
	},
	'verificarLista' : function (debitos, todaLaLista) {
		var errores = [];
		for (var index = 0, debitosSize = debitos.length; index < debitosSize; index++) {
			var salida = this.verificar(debitos[index].tipoComprobanteEnum);
			if (!$.isEmpty(salida)) {
				var error = new Object();
				error.salida = salida;
				error.index = index;
				error.index++;
				errores.push(error);
				if (!todaLaLista) {
					return errores;
				}
			}
		}
		return errores;
	},
	'subDocumento' : function(tipo) {
		if (tipo === DUC_NAME) {
			this.cantDuc--;
		} else {
			this.cantNoDuc--;
		}
	},
	'subTodosDocumentos' : function () {
		this.cantDuc = 0;
		this.cantNoDuc = 0;
	},
	'addDocumento' : function (tipo) {
		if (tipo === DUC_NAME) {
			this.cantDuc++;
		} else {
			this.cantNoDuc++;
		}
	},
	'addLista' : function(debitos) {
		for (var index = 0, debitosSize = debitos.length; index < debitosSize; index++) {
			if (debitos[index].type == 'CobroDebitoDto') {
				this.addDocumento(debitos[index].tipoComprobanteEnum);
			} else if (debitos[index].type == 'CobroOtrosDebitoDto') {
				this.addDocumento(debitos[index].tipoDebitoEnum);
			}
		}
	},
	'verificarSobreLista' : function(debitos) {
		var errores = [];
		var size = debitos.length;
		if (size <= 1) {
			return errores;
		}
		var pivotComprobante = debitos[0].tipoComprobanteEnum;
		var salida = '';
		if (pivotComprobante == DUC_NAME) {
			salida = ERROR_DUC_TAIL;
		} else {
			salida = ERROR_NO_DUC_TAIL;
		}
		for (var index = 1; index < size; index++) {
			if (pivotComprobante != debitos[index].tipoComprobanteEnum) {
				var error = new Object();
				error.salida = salida;
				error.index = index;
				errores.push(error);
			}
		}
		return errores;
	}
};
function isMismoDocumento(documentoAVerificar, documentoPatron) {
	var salida = false;
	if (documentoAVerificar.sistemaOrigen == MIC_NAME) {
		if ($.isEmpty(documentoAVerificar.numeroReferenciaMic)) {
			salida = (documentoPatron.idPantalla == documentoAVerificar.idPantalla);
		} else {
			salida = (documentoPatron.numeroReferenciaMic == documentoAVerificar.numeroReferenciaMic);
		}
	} else {
		// CASOS CALIPSO Y SHIVA
		salida = (documentoPatron.idPantalla == documentoAVerificar.idPantalla);
	}
	return salida;
};

function listaAContenidosEnListaBDebitosOCreditos(listaA, listaB) {
	var salida = new Object();
	salida.indexPrimero = -1;
	
	salida.sizeListaA = listaA.length;
	salida.sizeListaB = listaB.length;

	var errores = [];
	if (salida.sizeListaA < 1 || salida.sizeListaB < 1) {
		return errores;
	}
	
	for (var i = 0; i < salida.sizeListaA; i++) {
		for (var j = 0; j < salida.sizeListaB; j++) {
			if (isMismoDocumento(listaA[i], listaB[j])) {
				var error = new Obejct();
				error.index = i;
				errores.push(error);
			}
		}
	}
	return errores;
};
//Solo se utiliza para el buttons de simular en la solapa 5 "previsualizar"
var estadoButtons = {
	'btnSimular' : null,
	'btnImputar' : null,
	'crear' : function() {
		this.btnSimular = new Object();
		this.btnSimular.disabled = false;
		this.btnSimular.causa = '';
		this.btnSimular.errorInput = false;
		this.btnImputar = new Object();
		this.btnImputar.causa = '';
		this.btnImputar.errorInput = false;
		this.btnImputar.disabled = false;
	},
	// En la solapa 5 'previsualizar'. Si se requiere habilitar luego invocado 
	// al servicion 'configuracion-cobro/habilitarBtnSimular' se debe hacer atravez 
	// de este metodo
	'habilitarSimularBySelect' : function() {
		if (
			this.btnSimular.disabled &&
			(
				this.btnSimular.causa != CAUSA_DISABLED_SIMULAR_ERROR_NAME ||
				this.btnSimular.errorInput
			)
		) {
			$('#btnSimular').prop('disabled', false);
			this.btnSimular.errorInput = false;
		}
	},
	'habilitarSimularByCorreccionInput' : function() {
		if (this.btnSimular.errorInput && this.btnSimular.causa != CAUSA_DISABLED_SIMULAR_ERROR_NAME) {
			$('#btnSimular').prop('disabled', false);
			this.btnSimular.errorInput = false;
		}
	},
	'habilitarImputarByCorreccionInput' : function() {
		if (this.btnImputar.errorInput && !this.btnImputar.disabled) {
			$('#btnImputar').prop('disabled', false);
			this.btnSimular.errorInput = false;
		}
	}
};

/******************************************************************************/

//COMPENSACION_OTRAS = '33';
//COMPENSACION_INTERCOMPANY = '34';
//COMPENSACION_IIBB = '39';
//COMPENSACION_CESION_CREDITOS = '40';
//COMPENSACION_LIQUIDO_PROD = '35';
//COMPENSACION_PROVEEDORES = '41';

//DESISTIMIENTO("16", "16", "Desistimiento", "", ""),
//PLANPAGO("25", "25", "Plan de Pago", "", ""),
//TODO PARA PAU
function drawOtrosMedioPagoSegunMotivoCobro (motivo) {
	if (!$.isEmpty(motivo)) {
		if (motivo == COMPENSACION) {
			$('#selectMedioPago option').each(function() {
				if($(this).val() == '' || $(this).val() == OTRAS_COMPENSACIONES || $(this).val() == INTERCOMPANY || $(this).val() == IIBB
					 || $(this).val() == CESION_CREDITOS || $(this).val() == PROVEEDORES || $(this).val() == LIQUIDOPRODUCTO) { 
					$(this).show();
				}else {
					$(this).hide();
				}
			});
		} else {
			$('#selectMedioPago option').each(function() {
				if($(this).val() == '' || $(this).val() == DESISTIMIENTO || $(this).val() == PLANPAGO){
					$(this).show();
				}else {
					$(this).hide();
				}
			});
		}
	}
};

//U572497 Guido.Settecerze
function validarGrisadoTrataDif (){
	
	if ($('#selectMotivo').val() == COMPENSACION && $.isEmpty(datosTablas.mediosPagos)) {
		$('#selectEnvio').val('');
		$('#selectEnvio').attr('disabled', true);
		$('#selectEnvio1').val('');
		$('#selectEnvio1').attr('disabled', true);
		
	} else if(diferencia != "0,00" 
			&& $('#selectMotivo').val() == COMPENSACION
			&& !$.isEmpty(datosTablas.mediosPagos)){
		
		$('#selectEnvio').val('');
		if(datosTablas.mediosPagos[0].idMpTipoCredito == 41){
			$('#selectEnvio').attr('disabled', false);
			$('#selectEnvio1').val('');
			$('#selectEnvio1').attr('disabled', false);
		} else if(datosTablas.mediosPagos[0].idMpTipoCredito == 35){
			$('#selectEnvio').attr('disabled', true);
			$('#selectEnvio1').val('');
			$('#selectEnvio1').attr('disabled', true);
		}
	
	} else if (diferencia == "0,00" || $('#selectMotivo').val() == COMPENSACION) {
		$('#selectEnvio').val('');
		$('#selectEnvio').attr('disabled', true);
		$('#selectEnvio1').val('');
		$('#selectEnvio1').attr('disabled', true);
	} else {
		$('#selectEnvio').val('');
		$('#selectEnvio').attr('disabled', false);
		$('#selectEnvio1').val('');
		$('#selectEnvio1').attr('disabled', false);
	}
};

function consultaCotizacionCompensaciones (){
	
	$('#bloqueEspera').trigger('click');
	$('#tipoDeCambio').val('');
	$('#errorTipoDeCambio').text('');
	if (validarFechaTipoCambio()) {
		var filtro = {
			fecha: $("#fechaTipoCambio").val(),
			moneda: _monedaDelCobro
		};
		$.ajax({
			"type" : "POST",
			"url": "configuracion-cobro/obtenerCotizacion",
			"dataType": "json", 
			"data": JSON.stringify(eval(filtro)), 
			"contentType": "application/json",
			"mimeType": "application/json",
			"success" : function(result) {
				if (result.success) {
					$('#tipoDeCambio').val(result.cotizacion);
					if($('#tipoDeCambio').val().indexOf('\n') !== -1) {
						$('#tipoDeCambio').css('height','40px');
					} else {
						$('#tipoDeCambio').css('height','20px');
					}
				} else {
					$('#errorTipoDeCambio').text(result.descripcionError);
					$('#tipoDeCambio').css('height','20px');
					//$('#conf-cobro-tabs-t-5').css({"color": "red", "font-weight": "bold"});
				}
				ocultarBloqueEspera();	
				return result.success;
			}
		});
	} else {
		ocultarBloqueEspera();
	}

};
/******************************************************************************/
function agregarDecimales(valor) {
//	var cant = 0
	
//	if (valor === undefined) {
//		cant = 7;
//	} else {
//		cant = cantidad;
//	}
	var numero = valor.split(',');
	var mascara = "0000000";

	if (numero.length === 1) {
		numero[1] = mascara;
	} else {
		if (numero[1].length > 7) {
			numero[1] = numero[1].substring(0, 6);
		} else if (numero[1].length < 7) {
			numero[1] = numero[1].concat(mascara.substring(numero[1].length -1, 6));
		}
	}
	return numero.join(',');
}
// TODO PARA PAU
function esEstadoCobroFinal(estado) {
	return ESTADO_VEC_FINAL.indexOf(estado) > -1 ;
}
function esCobroCompensacionSap(medioPago) {
	if (
		!$.isEmpty(medioPago) &&
		(
			medioPago[0].idMpTipoCredito === LIQUIDOPRODUCTO ||
			medioPago[0].idMpTipoCredito === PROVEEDORES
		)
	) {
		return true;
	}
	return false;
}
function eliminarEnTablaByIndex(index, tabla) {
	tabla.splice(index, 1);
}

function dividirNumeros(numero1, numero2) {
	division = 0;
	if (numero1 > 0 && numero2 > 0) {
		division = numero1 / numero2;
	}
	return division;
}