window.onload = function() {

	var cobroHistorial = 'cobroHistorial';

	oTable = $('#' + cobroHistorial).dataTable( {
		dom: 'Bfrtip' ,
		"scrollX": true,
		"scrollY": '500px',
		"aaSorting": [[13,'desc'],[0,'desc']],
		"bScrollCollapse": true,
		buttons: [{	
			extend:'excelHtml5',
			text:'Excel',
			title: "Cobros_Historial_Cobro.csv",
			className: 'excelbtn'
		}],
		'columnDefs': [//targets son los th con las class date o importe
		               {type: 'latam_datetime_seconds', targets: 'date'},
		               { "bSortable": false, "aTargets": [0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,12,13]}
		               ]
	});
	ocultarBloqueEspera();
};
function volverBusqueda() {
	$('#goBack').val("true");
	$('#formHist')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formHist')[0].submit();
};