window.onload = function() {

	var descobroHistorial = 'descobroHistorial';

	oTable = $('#' + descobroHistorial).dataTable( {
		dom: 'Bfrtip' ,
		"scrollX": true,
		"scrollY": '500px',
		"aaSorting": [4,'desc'],
		"bScrollCollapse": true,
		buttons: [{	

			extend:'excelHtml5',
			text:'Excel',
			className: 'excelbtn'
		}],
		"columnDefs": [
		                 {type: 'latam_datetime_seconds', targets: 'date'},
		                 { "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]}
		                 ]
	});
	ocultarBloqueEspera();
};
function volverBusqueda() {
	$('#goBack').val("true");
	$('#formHist')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formHist')[0].submit();
};

