window.onload = function() {


	var operacionMasivaHistorial = 'OperacionMasivaHistorial';
	
		oTable = $('#' + operacionMasivaHistorial).dataTable( {
				dom: 'Bfrtip' ,
				"scrollX": true,
				"scrollY": '500px',
				"bScrollCollapse": true,
				buttons: [{	
					extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: "Historial_Operacion_Masiva.csv",
	                 className: 'excelbtn'
   							}],
   				"order": [[ 3, "desc" ]],
				'columnDefs': [//targets son los th con las class date o importe - datetimeSeconds
					{type: 'latam_datetime_seconds', targets: 'datetimeSeconds'},
					{ "bSortable": false, "aTargets": [0, 1, 2,3, 4]}
				]
			});
};
function volverBusqueda() {
	$('#bloqueEspera').trigger('click');
	$('#goBack').val("true");
	$('#formHist')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formHist')[0].submit();
};