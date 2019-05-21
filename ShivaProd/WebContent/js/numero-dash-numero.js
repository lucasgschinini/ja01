jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"numero-dash-numero-pre": function (a) {
		var numero = a.split('-').join('');
		return Number(numero);
	},

	"numero-dash-numero-asc": function (a, b) {
		return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"numero-dash-numero-desc": function (a, b) {
		return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
} );