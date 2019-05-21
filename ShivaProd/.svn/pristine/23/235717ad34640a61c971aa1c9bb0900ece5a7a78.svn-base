function crearTablaFixedColumn ( datosTablas , tablas , nombreTabla) {
	var nombreFixedCollumns = 'fixedColumns' + nombreTabla[0].toUpperCase() + nombreTabla.substring(1);
	if (tablas[nombreFixedCollumns] === undefined) {
		new $.fn.dataTable.FixedColumns(tablas[nombreTabla], {
			leftColumns: 1,
			heightMatch: 'auto'
		});
	} else if (!tablas[nombreFixedCollumns]) {
		new $.fn.dataTable.FixedColumns(tablas[nombreTabla], {
			leftColumns: 1,
			heightMatch: 'auto'
		});
		tablas[nombreFixedCollumns] = true;
	}
};
function tablesClear(nombre, nombreTabla, tablas, datosTablas) {
	if ($.fn.DataTable.isDataTable( '#' + nombreTabla)) {
		tablas[nombre].fnClearTable();
		if (datosTablas !== undefined) {
			datosTablas[nombre].length = 0;
		}
	}
}