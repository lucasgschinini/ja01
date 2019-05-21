jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"string-numero-pre": function (a) {
		return Number(a);
	},

	"string-numero-asc": function (a, b) {
		return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"string-numero-desc": function (a, b) {
		return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
} );