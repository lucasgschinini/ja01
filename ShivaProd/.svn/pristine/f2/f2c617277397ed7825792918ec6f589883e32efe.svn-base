/**
 * Comparador de valores en formato de moneda "$999,999,999.99"
 * Sin distincion de pesos y dolares.
 * Este comparador es para ser utilizado en el sort de dataTables.net y esta basado en los ejemplos.
 * 
 * forma de uso
 * @example
 *    $('#example').dataTable( {
 *       columnDefs: [
 *         { type: 'comparador-currency', targets: 0 }
 *       ]
 *    } );
 *	// targets es la columna a donde aplica
 */

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"comparador-currency-pre": function (a) {
		if (a == '-') {
			a = -1;
		} else if (/U\$S/.test(a)) {
			a = a.split('U$S').join("");
		} else if (a.indexOf('$') >= 0) {
			a = a.split('$').join("");
		}
		return importeToFloat(a);
	},

	"comparador-currency-asc": function (a, b) {
		return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"comparador-currency-desc": function (a, b) {
		return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
} );