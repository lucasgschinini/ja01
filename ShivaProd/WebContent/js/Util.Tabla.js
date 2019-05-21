
    /**
     * @Pablo
     * 
	 * Este procedimiento permite agregar el scrolling horizontal
     * a una tabla puntual.
     * Se asume que la tabla ha sido previamente tratada con DataTables.js
	 * Esta es la estructura que se arma con DataTables
	 * 
	 *			<div id="resultadoBusqueda_wrapper" class="dataTables_wrapper"> 
	 * Nivel 0		<div class="DTTT_container"></div>
	 * Nivel 1		<div id="resultadoBusqueda_length" class="dataTables_length"></div>
	 * Nivel 2		<div id="resultadoBusqueda_filter" class="dataTables_filter"></div>
	 *					<table id="resultadoBusqueda" class="tablaResultado dataTable"></table>
	 * Nivel 3		<div id="resultadoBusqueda_info" class="dataTables_info">Mostrando registros del 1 al 10 de un total de 12 registros</div>
	 * Nivel 4		<div id="resultadoBusqueda_paginate" class="dataTables_paginate paging_two_button"></div>
	 * 			</div>
	 * 
	 * Esta es la estructura que queda luego de ejecutar esta funcion
	 *
	 *	<div id="resultadoBusqueda_wrapper" class="dataTables_wrapper">
 	 *		<div class="DTTT_container"></div> (solo si existe previamente, aplica a los botones de exportacion)
	 *		<div id="resultadoBusqueda_length" class="dataTables_length"></div>
	 *		<div id="resultadoBusqueda_filter" class="dataTables_filter"></div>
	 *		<div id="DivRoot">
	 *			<div id="DivHeaderRow"></div>
	 *			<div  id="DivMainContent">
	 *				<table id="resultadoBusqueda" class="tablaResultado dataTable"></table>
	 *			</div>
	 *			<div id="DivFooterRow"></div>
	 *		</div>
	 *		<div id="resultadoBusqueda_info" class="dataTables_info">Mostrando registros del 1 al 10 de un total de 12 registros</div>
	 *		<div id="resultadoBusqueda_paginate" class="dataTables_paginate paging_two_button"></div>
	 *	</div>
	 */
    function agregarScrollingHorizontal(idTablaDeReferencia) {
    	
    	var alturaContenedor = 400;
    	var indice 			= 0;
		var dttContainer 	= null;
		
		// Referencia a la tabla '_wrapper' que genera dataTables.js
		var tablaWrapper = document.getElementById(idTablaDeReferencia + '_wrapper');
		
		// Referencia a mi tabla de datos
		var tabla 			= tablaWrapper.getElementsByTagName('table')[0];
		
		// Sacamos la tabla del Div contenedor 
		tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('table')[0]);

		// Tratamiento especial para campos de exportacion
		if ("DTTT_container" == tablaWrapper.getElementsByTagName('div')[indice].className) {
			dttContainer = tablaWrapper.getElementsByTagName('div')[indice];
			indice ++;
		}

		// Referencia al div '_length' que genera dataTables.js
		var tableLength		= tablaWrapper.getElementsByTagName('div')[indice++];	// 1

		// Referencia al div '_filter' que genera dataTables.js
		var tableFilter		= tablaWrapper.getElementsByTagName('div')[indice++];	// 2

		// Referencia al div '_info' que genera dataTables.js
		var tableInfo		= tablaWrapper.getElementsByTagName('div')[indice++];	// 3	
		
		// Referencia al div '_paginate' que genera dataTables.js
		var tablePaginate	= tablaWrapper.getElementsByTagName('div')[indice];		// 4


		// Eliminamos el div 'dttContainer' para luego moverlo donde nosotros queremos, solo si existe
		if (dttContainer != null) {
			tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('div')[0]);
		}
		
		// Eliminamos el div '_filter' para armarlo luego donde nosotros queremos
		tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('div')[0]);
		
		// Eliminamos el div '_length' para luego moverlo donde nosotros queremos 
		tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('div')[0]);

		// Eliminamos el div '_info' para luego moverlo donde nosotros queremos 
		tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('div')[tablaWrapper.getElementsByTagName('div').length - 1]);

		// Eliminamos el div '_paginate' para luego moverlo donde nosotros queremos
		tablaWrapper.removeChild(tablaWrapper.getElementsByTagName('div')[tablaWrapper.getElementsByTagName('div').length - 1]);
		
		
		// Creo mi propio Div de Scrolling
		var divRoot = $('<div id="DivRoot_' + idTablaDeReferencia + '" align="left" style="height:' + alturaContenedor + 'px"></div>')[0];
		
		var divHeaderRow = $('<div style="overflow: hidden;" id="DivHeaderRow_' + idTablaDeReferencia + '"></div>')[0];
		var divMainContent = $('<div style="overflow-x:scroll; overflow-y:hidden" onscroll="OnScrollDiv(this, \'' + idTablaDeReferencia + '\')" id="DivMainContent_' + idTablaDeReferencia + '"></div>')[0];

		// Agregamos la tabla a nuestro Div de control de Scrolling
		divMainContent.appendChild(tabla);
		
		var divFooterRow = $('<div id="DivFooterRow_' + idTablaDeReferencia + '" style="overflow:hidden"></div>')[0];
				
		divRoot.appendChild(divHeaderRow);
		divRoot.appendChild(divMainContent);
		divRoot.appendChild(divFooterRow);

		
		// Agrego al inicio de este contendor el div '_DTTT_Container' que había eliminado previamente, solo si existe
		if (dttContainer != null) {
			tablaWrapper.appendChild(dttContainer);
		}

		// Agrego al final de este contendor el div '_length' que había eliminado previamente
		tablaWrapper.appendChild(tableFilter);
		
		// Agrego mi div de control de Scrolling al contenedor '_wrapper' que genera dataTable.js
		tablaWrapper.appendChild(divRoot);
		
		// Agrego al final de este contendor el div '_info' que había eliminado previamente
		tablaWrapper.appendChild(tableInfo);
		
		// Agrego al final de este contendor el div '_paginate' que había eliminado previamente
		tablaWrapper.appendChild(tablePaginate);
		
		crearHeaderEstatico(idTablaDeReferencia, alturaContenedor - 43, 680, 52);
		
		//
		// Aqui actualizo dinamicamente la altura de la tabla en base a los registros que pueda llegar a tener
		actualizarAltoTabla(idTablaDeReferencia);
    };
    
	function crearHeaderEstatico(idTablaDeReferencia, height, width, headerHeight) {

		var tbl = document.getElementById(idTablaDeReferencia);
        if (tbl) {
	        var DivHR = document.getElementById('DivHeaderRow_' + idTablaDeReferencia);
	        var DivMC = document.getElementById('DivMainContent_' + idTablaDeReferencia);
	        var DivFR = document.getElementById('DivFooterRow_' + idTablaDeReferencia);
	        
	        //*** Set divheaderRow Properties ****
	        DivHR.style.height = headerHeight + 'px';
	        DivHR.style.width = (parseInt(width) - 16) + 'px';
	        DivHR.style.position = 'relative';
	        DivHR.style.top = -headerHeight + 'px';
	        DivHR.style.zIndex = '10';
	        DivHR.style.verticalAlign = 'top';

	        //*** Set divMainContent Properties ****
	        DivMC.style.width = width + 'px';
	        DivMC.style.height = height + 'px';
	        DivMC.style.position = 'relative';
	        DivMC.style.top = -headerHeight + 'px';
	        DivMC.style.zIndex = '1';

	        //*** Set divFooterRow Properties ****
	        DivFR.style.width = (parseInt(width) - 16) + 'px';
	        DivFR.style.position = 'relative';
	        DivFR.style.top = -headerHeight + 'px';
	        DivFR.style.verticalAlign = 'top';
	        DivFR.style.paddingtop = '2px';

	        //****Copy Header in divHeaderRow****
	        //DivHR.appendChild(tbl.cloneNode(true));
     	}
    }

    function OnScrollDiv(Scrollablediv, idTablaDeReferencia) {
    	document.getElementById('DivHeaderRow_' + idTablaDeReferencia).scrollLeft = Scrollablediv.scrollLeft;
		document.getElementById('DivFooterRow_' + idTablaDeReferencia).scrollLeft = Scrollablediv.scrollLeft;
    }
    
    function actualizarAltoTabla(idTablaDeReferencia) {
    	
		var tablaResultado	= null;
		var cantidadFilas	= 0;
		var alturaTabla 	= 0;
    	
		tablaResultado = document.getElementById(idTablaDeReferencia);
		
		cantidadFilas = tablaResultado.getElementsByTagName('tr').length;
		
		// Mido el Header y voy sumando
		if (cantidadFilas > 0 & tablaResultado.getElementsByTagName('th').length > 0) {
			alturaTabla +=  tablaResultado.getElementsByTagName('tr')[0].offsetHeight;
		}
		
		// Mido la altura de las filas y voy sumando
		if (cantidadFilas > 1) {
			for (var i = 1; i < cantidadFilas; i++) {
				alturaTabla += tablaResultado.getElementsByTagName('tr')[i].offsetHeight;
			}
		}
		
		alturaTabla += 63;
		
        if (document.getElementById('DivRoot_' + idTablaDeReferencia)) {
			document.getElementById('DivRoot_' + idTablaDeReferencia).style.height = alturaTabla + 'px';
	        document.getElementById('DivMainContent_' + idTablaDeReferencia).style.height = (alturaTabla - 43) + 'px';
    	}
    }
	
    
    /************************************************************************/
    /***********  Ordenamiento de columnas numericas e importes  ************/
    /************************************************************************/
    (function(){
    	// Caracteres validos
    	var validChars = "$" + "0123456789" + ".-,";

    	// Se inicializa la expresion regular
    	var
    	str = jQuery.fn.dataTableExt.oApi._fnEscapeRegex(validChars),
    	re = new RegExp('[^'+str+']');

    	jQuery.fn.dataTableExt.aTypes.unshift(function (data) {
    		if (typeof data !== 'string' || re.test(data)) return null;
    		return 'currency';
    	});
    }());
    
    jQuery.extend(jQuery.fn.dataTableExt.oSort, {
        "currency-pre": function (a) {
        	a = (!a || $.trim(a).length === 0) ? 0 : a.replace( /[^\d\-\,]/g, "" ).replace(/,/g,".");
        	return parseFloat(a);
        },
        "currency-asc": function (a,b) {
        	return a - b;
        },
        "currency-desc": function (a,b) {
        	return b - a;
        }
    } );
    /************************************************************************/
	/************************************************************************/
    /************************************************************************/
    
    /**
     * Variable Global
     */
    var oTable;

    /**
     * Inicializa la tabla utilizando DataTables
     * 
     * @param nombreTabla
     */
    function inicializarTabla(nombreTabla) {
        
    	$(document).ready(function() {
   			oTable = $('#' + nombreTabla).dataTable( {
   				dom: 'frtip' ,
   				"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true
   			});
   		}); 
    	
   	};
   	function inicializarTablaConfiguracionOrderSort(nombreTabla, ordering) {
        $(document).ready(function() {
   			oTable = $('#' + nombreTabla).dataTable( {
   				"sDom": 'frtip' ,
   				"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				"ordering": false
   			});
   		}); 
    	
   	};
    /**
     * Inicializa la tabla utilizando DataTables, adicionando botones de exportacion
     * de PDF y Excel
     * 
     * @param nombreTabla
     */
   	function inicializarTablaConExportacionAPdfyXls(nombreTabla, fnCellRender) {

   		$(document).ready(function() {
   			oTable = $('#' + nombreTabla).dataTable( {
   				dom: 'Bfrtip' ,
   				"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{	
   						extend: "pdfHtml5",
   						orientation:"landscape",
   						text:"PDF",
   						title: camelCaseToUnderscore(nombreTabla, true, true),
   						className: 'pdfbtn',
						"mColumns": "visible"
							
   					},{
   						extend: "excelHtml5",
   						text:"Excel",
   						title: camelCaseToUnderscore(nombreTabla, true, true),
						"mColumns": "visible",
						className: 'excelbtn',
						"fnCellRender" : (fnCellRender === 'undefined' || fnCellRender === null || fnCellRender === '') ? '' : fnCellRender
   					}]
   				
   			});
   		}); 
   	};
    
    
    /**
     * Inicializa la tabla utilizando DataTables
     * @param nombreTabla
     */
    function inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrdenada(nombreTabla, fnCellRender) {
    	
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom: 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{	
    					extend: "pdfHtml5",
    					orientation:"landscape",
    					text:"PDF",
    					title: camelCaseToUnderscore(nombreTabla, true, true),
    					className: 'pdfbtn',
						"mColumns": "visible"
    				},{
    					extend: "excelHtml5",
    					text:"Excel",
    					title: camelCaseToUnderscore(nombreTabla, true, true),
						"mColumns": "visible",
						className: 'excelbtn',
						"fnCellRender" : (fnCellRender === 'undefined' || fnCellRender === null || fnCellRender === '') ? '' : fnCellRender
    				}]
    			,
    			"aoColumnDefs": [
    			                 {"bSortable": false, "aTargets": [ 0 ]}
    			                 ]
    		});
    	
    };
    
    
    /**
     * @author u573005, sprint3
     * Inicializa la tabla utilizando DataTables y con un metodo para refrescar
     * el boton de seleccionar todos cuando se pasa de pagina
     * @param idTabla
     * @param claseRegistros
     * @param idSeleccionarTodos
     */
    function inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTabla, claseRegistros, idSeleccionarTodos, habilitarBotones, fnCellRender) {
    	
    	oTable = $('#' + idTabla).dataTable( {
    		dom: 'Bfrtip',
    		"scrollX": true,
   			"scrollY": '500px',
   			"bScrollCollapse": true,
				buttons: [{
				  	extend:'pdfHtml5',
				  	orientation:"landscape",
    			     text: 'PDF',
    			     className: 'pdfbtn',
    			     title: camelCaseToUnderscore(idTabla, true, true)
    				},
    				{
					 extend:'excelHtml5',
       			     text:'Excel',
       			     title: camelCaseToUnderscore(idTabla, true, true),
                     className: 'excelbtn',
                     "fnCellRender" : (typeof fnCellRender === 'undefined' || fnCellRender === null || fnCellRender === '') ? '' : fnCellRender
				}],
			"aoColumnDefs": [
                 {"bSortable": false, "aTargets": [0]}

             ],
			"fnDrawCallback": function( oSettings ) {
				
				verificarCheckboxs(idSeleccionarTodos, claseRegistros);
				
	    		$("." + claseRegistros).click(function() {
	    			verificarCheckboxs(idSeleccionarTodos, claseRegistros);
	    			
	    			if (typeof habilitarBotones != 'undefined') habilitarBotones();
	    		});
                
	         }
    	});
    	
    };
    /**
     * @author u578936, sprint5
     * Inicializa la tabla utilizando DataTables y con un metodo para refrescar
     * el boton de seleccionar todos cuando se pasa de pagina
     * @param idTabla
     * @param claseRegistros
     * @param idSeleccionarTodos
     */
    function inicializarTablaCon_PDF_XLS_SelTodos(idTabla, claseRegistros, idSeleccionarTodos, habilitarBotones, fnCellRender) {


    	oTable = $('#' + idTabla).dataTable( {
    		dom: 'Bfrtip',
    		"scrollX": true,
   			"scrollY": '500px',
   			"bScrollCollapse": true,
				buttons: [{
	     			 extend:'pdfHtml5',
    			     text: 'PDF',
    			     className: 'pdfbtn',
    			     title: camelCaseToUnderscore(idTabla, true, true)
    				},{	
    				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(idTabla, true, true),
	                 className: 'excelbtn'
    							}],
			 "columnDefs": [ 
                 {"orderable": false, "targets": [0]}
             ],
			"fnDrawCallback": function( oSettings ) {
				
				verificarCheckboxs(idSeleccionarTodos, claseRegistros);
				
	    		$("." + claseRegistros).click(function() {
	    			verificarCheckboxs(idSeleccionarTodos, claseRegistros);
	    			
	    			if (typeof habilitarBotones != 'undefined') habilitarBotones();
	    		});
                
	         }
    	});
    	
    };
    
	function verificarCheckboxs(idCheckAll, classCheck){
		var checkAll = false;
		var disableCheckAll = true;
		var cant = $("." + classCheck + ":visible").length;
		var checks = 0;
		$("." + classCheck + ":visible").each(function(i, elem) {
			disableCheckAll = false;
		    if($(this).is(":checked")) checks = checks + 1;
		});
		checkAll = (cant > 0 && cant == checks);
		$("#" + idCheckAll).prop('checked', checkAll);
		$("#" + idCheckAll).prop('disabled', disableCheckAll);
	};
    
    function inicializarTablaCon_PDF_XLS_ColumnaOcultaNoOrdenada(nombreTabla, columna, fnCellRender) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom: 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   					 buttons: [{
	     			 extend:'pdfHtml5',
	     			 text: 'PDF',
	     			 className: 'pdfbtn',
	     			 title: camelCaseToUnderscore(nombreTabla, true, true)
   					 },{	
   				     extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreTabla, true, true),
	                 className: 'excelbtn'
   							}],
    			"aoColumnDefs": [{ "bSortable": false, "aTargets": [ 0 ],
    				"bVisible": false, "aTargets": [ columna ]}]
    		});
    	});
    };
    
    /**
     * Inicializa la tabla utilizando DataTables, adicionando botones de exportacion
     * de PDF
     * 
     * @param nombreTabla
     */
    function inicializarTablaConExportacionAPdf(nombreTabla) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom: 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{
		   					extend:'pdfHtml5',
		   					text: 'PDF',
		   					className: 'pdfbtn',
		   					title: camelCaseToUnderscore(nombreTabla, true, true)
   						}],
    		});
    	});
    };
    
    /**
     * Inicializa la tabla utilizando DataTables, adicionando botones de exportacion
     * de Excel
     * 
     * @param nombreTabla
     */
    function inicializarTablaConExportacionAXls(nombreTabla) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom : 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{	
   				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreTabla, true, true),
	                 className: 'excelbtn'
   							}],
    		});
    	});
    };
    
    /**
     * Inicializa la tabla utilizando DataTables, adicionando botones de exportacion
     * de Excel
     * 
     * @param nombreTabla
     */
    function inicializarTablaConExportacionAXlsConNombre(nombreTabla, nombreExcel) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom : 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				
   				buttons: [{	
   				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreExcel, true, true),
	                 className: 'excelbtn'
   							}],
    		});
    	});
    };
    function inicializarTablaConExportacionAXlsConNombreOrdenada(nombreTabla, nombreExcel, order) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom : 'Bfrtip',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				"order": order,
   				buttons: [{	
   				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreExcel, true, true),
	                 className: 'excelbtn'
   							}],
    		});
    	});
    };
    function inicializarTablaConExportacionAXlsConNombreDom(nombreTabla, nombreExcel, domSetting, fnRowCallback) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			dom : domSetting,
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{	
   				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreExcel, true, true),
	                 className: 'excelbtn'
   				}],
   			 "fnRowCallback": fnRowCallback
    		});
    	});
    };
    /**
     * Este codigo sirve para dar vista bicolor de las tablas
     * con estilo "tablaResultado" para IE en versiones anteriores a IE9
     */
    function cargarEstiloBicolorTablaResultado() {

    	$(".tablaResultado tr:odd").css('background-color','#FFFFFF');
		$(".tablaResultado tr:even").css('background-color','#F2F2F7');
    	
    }
   
    function inicializarTablaConExportacionAPdfyXlsOcultarSearchFiltroInfo(nombreTabla, fnCellRender) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			"bPaginate": false,
    			"bLengthChange": false,
    			"bFilter": false,
    			"bInfo": false,
    			"bAutoWidth": false,
    			dom : 'Brtp',
    			"scrollX": true,
   				"scrollY": '500px',
   				"bScrollCollapse": true,
   				buttons: [{
	     		 extend:'pdfHtml5',
   			     text: 'PDF',
   			     className: 'pdfbtn',
   			     title: camelCaseToUnderscore(nombreTabla, true, true)
   				},{	
   				 extend:'excelHtml5',
	       		     text:'Excel',
	       		     title: camelCaseToUnderscore(nombreTabla, true, true),
	                 className: 'excelbtn',
	                 "fnCellRender" : (typeof fnCellRender === 'undefined' || fnCellRender === null || fnCellRender === '') ? '' : fnCellRender
   							}],
    		});
    	}); 
    };

	    /**
		 * Funcion que permite crear una tabla solo con los datos de la misma,
		 * sin tener en cuenta filtros por defecto, paginadores, etc.
		 */
    function inicializarTablaOcultarSearchFiltroInfo(nombreTabla) {
    	$(document).ready(function() {
    		oTable = $('#' + nombreTabla).dataTable( {
    			"bPaginate": false,
    			"bLengthChange": false,
    			"bFilter": false,
    			"bInfo": false,
    			"scrollX": true,
   				"scrollY": '500px',
   				"bAutoWidth": false
    		});
    	}); 
    };
	     
	/**
	 * @author fabio.giaquinta.ruiz
	 * @summary Esta funcion levanta todas las columnas
	 * que tengan TH de un datatable que tenga en la propiedad
	 * clase los siguientes valores para ordenar los
	 * datos en los formatos correspondientes
	 * utilizando el tipo de dato personalizado de la libreria
	 * date-latam.js
	 * date, dd/MM/yyyy
	 * dateTime, dd/MM/yyyy HH:mm
	 * dateTimeSeconds, dd/MM/yyyy HH:mm:ss
	 * dateTimeSecondsMilliseconds, dd/MM/yyyy HH:mm:ss.SSS
	 * @example Se debe llamar a esta funcion despues de la 
	 * inicializacion de la tabla pero antes de invocar a la 
	 * funcion datatable().fnSort
	 * @param idTabla
	 */
	function agregarOrdenFecha(idTabla) {
	    
		var tmpTable = $('#' + idTabla).dataTable();
		var tmpCols = tmpTable.fnSettings().aoColumns;
	    
	    for(var i=0;i<(tmpCols.length);i++){
            if(jQuery(tmpCols[i].nTh).hasClass("date")){
                tmpTable.fnSettings().aoColumns[i].sType = "latam_date";
            };
	    }
	    for(var i=0;i<(tmpCols.length);i++){
            if(jQuery(tmpCols[i].nTh).hasClass("dateTime")){
                tmpTable.fnSettings().aoColumns[i].sType = "latam_datetime";
            };
	    }
	    for(var i=0;i<(tmpCols.length);i++){
            if(jQuery(tmpCols[i].nTh).hasClass("dateTimeSeconds")){
                tmpTable.fnSettings().aoColumns[i].sType = "latam_datetime_seconds";
            };
	    }
	    for(var i=0;i<(tmpCols.length);i++){
            if(jQuery(tmpCols[i].nTh).hasClass("dateTimeSecondsMilliseconds")){
                tmpTable.fnSettings().aoColumns[i].sType = "latam_datetime_seconds_milliseconds";
            };
	    }
	    for(var i=0;i<(tmpCols.length);i++){
            if(jQuery(tmpCols[i].nTh).hasClass("nroDashNro")){
                tmpTable.fnSettings().aoColumns[i].sType = "numero-dash-numero";
            };
	    }
	};
	/**
	 * fnCellRenderGenerico: Se utiliza para renderizar en la exportacion a EXCEL
	 * Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
	 * No se exportan en forma correcta en Chrome. hay que renderizarlo a mano
	 * En IE8 no hace falta llamar a fnCellRender
	 * 
	 * Los 4 primeros argumentos son los mismo de fnCellRender de dataTables.net 
	 * El primer vector son las columnas que no se van a exportar
	 * El segundo vector son las columnas que contiene la foto de usuario y el email
	 */
	fnCellRenderGenerico = function(sValue, iColumn, nTr, iDataIndex, vecNoVisibles, vecUsuario, vecDatos) {
		var render = '';
		if (sValue.trim() !== '-') {
			if (vecNoVisibles.indexOf(iColumn) >= 0) {
				render = '';
			} else if (typeof vecUsuario !== 'undefined' && vecUsuario.indexOf(iColumn) >= 0) {
				// esta columna posee la imagen del usuario y el email
				if (window.DOMParser) {
					parser = new DOMParser();
					xmlDoc = parser.parseFromString(sValue, 'text/xml');
					// Si tiene elementos img == 0 quiere decir que viene una '-'
					if (xmlDoc.getElementsByTagName('img').length > 0) {
						render = xmlDoc.getElementsByTagName('img')[0].childNodes[0].nodeValue;
						// En los generadors por oTool no se muestran los email. Lo dejo comentadado por si se llega usar mas adelante
//						render += ' ';
//						var mail = xmlDoc.getElementsByTagName('a')[1].getAttribute('href');
//						if (typeof mail !== 'undefined' && mail != null) {
//							var vmail = mail.split(':');
//							if (vmail.length > 1) {
//								mail = vmail[1];
//							}
//							render += mail;
//						}
						
					} else {
						//<p style="text-align: center"> - </p>
						render = '-';
					}
				}
			} else if (typeof vecDatos !== 'undefined' && vecDatos.indexOf(iColumn) >= 0) {
				if (window.DOMParser) {
					parser = new DOMParser();
					sValue = sValue.split('<br>').join('\r\n');
					xmlDoc = parser.parseFromString(sValue, 'text/xml');
					var divs = xmlDoc.getElementsByTagName('div');
					var size = divs.length;
					if (size > 1) {
						if (size == 2) {
							render += '"' + divs[0].getElementsByTagName('strong')[0].childNodes[0].nodeValue + '\r\n';
							render += divs[0].getElementsByTagName('spam')[0].childNodes[0].nodeValue + '"';
						} else {
							for (var i=0, size = divs.length; i < size; i++) {
								if (i != 0) { //ignoro el primer div
									if (i == 1) {
										render += '"' + divs[i].getElementsByTagName('strong')[0].childNodes[0].nodeValue + '\r\n';
										render += divs[i].getElementsByTagName('spam')[0].childNodes[0].nodeValue;
									} else {
										render += divs[i].getElementsByTagName('strong')[0].childNodes[0].nodeValue + '\r\n';
										render += divs[i].getElementsByTagName('spam')[0].childNodes[0].nodeValue;
									}
									if (i != size - 1) {
										render += '\r\n';
									} else {
										render += '"';
									}
								}
							}
						}
					} else {
						// No hay contenido
						render = '-';
					}
				}
			} else {
				render = sValue;
			}
		} else {
			render = sValue;
		}
		return render;
	};
	
	//en este metodo se pueden agregar configuraciones globales de las grillas de datatables
	//solo configuraciones que aplican a todas las grillas, aunque despues se podrian
	//sobrescribir en cada grilla en particular
	$.extend( true, $.fn.dataTable.defaults, {
		"language": {
				"sProcessing":     "Procesando...",
				"sLengthMenu":     "Mostrar _MENU_ registros",
				"sZeroRecords":    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se encontraron resultados",
				"sEmptyTable":     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No hay datos disponibles en la tabla",
				"sInfo":           "Mostrando registros del _START_ al _END_ de _TOTAL_ registros en total",
				"sInfoEmpty":      "Mostrando registros del 0 al 0 de 0 registros en total",
				"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
				"sInfoPostFix":    "",
				"sSearch":         "Buscar en resultado:",
				"sUrl":            "",
				"sInfoThousands":  ".",
				"sLoadingRecords": "Cargando...",
				"oPaginate": {
					"sFirst":    "Primero",
					"sLast":     "Último",
					"sNext":     "Siguiente",
					"sPrevious": "Anterior"
				},
				"oAria": {
					"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
					"sSortDescending": ": Activar para ordenar la columna de manera descendente"
				}
			},
		"pagingType": "simple"
	});

