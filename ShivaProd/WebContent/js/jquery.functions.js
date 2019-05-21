/*************************************************************************/
//Verifico el tipo de navegador
var matched, browser;
jQuery.uaMatch = function( ua ) {
	ua = ua.toLowerCase();

	var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
     	/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
     	/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
     	/(msie) ([\w.]+)/.exec( ua ) ||
     	ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
     	[];

	return {
		browser: match[ 1 ] || "",
		version: match[ 2 ] || "0"
	};
};

matched = jQuery.uaMatch(navigator.userAgent);
browser = {};

if ( matched.browser ) {
	browser[ matched.browser ] = true;
	browser.version = matched.version;
}

//Chrome is Webkit, but Webkit is also Safari.
if (browser.chrome ) {
	browser.webkit = true;
} else if ( browser.webkit ) {
	browser.safari = true;
}

jQuery.browser = browser;


$(function() {
	// Bloque Espera
	$('#bloqueErrorAjax').click(function() {
		$("#divErrorAjax").css({
			height : $.getDocHeight() + 'px'
		});

		mostrarBloqueError();
	});

	// Bloque Espera
    $('#bloqueEspera').click(function(){
    	var opts = {				  
  			lines: 17, // The number of lines to draw
  			  length: 9, // The length of each line
  			  width: 4, // The line thickness
  			  radius: 15, // The radius of the inner circle
  			  corners: 1, // Corner roundness (0..1)
  			  rotate: 3, // The rotation offset
  			  direction: 1, // 1: clockwise, -1: counterclockwise
  			  color: '#fff', // #rgb or #rrggbb or array of colors
  			  speed: 1.3, // Rounds per second
  			  trail: 65, // Afterglow percentage
  			  shadow: false, // Whether to render a shadow
  			  hwaccel: false, // Whether to use hardware acceleration
  			  className: 'spinner', // The CSS class to assign to the spinner
  			  zIndex: 2e9, // The z-index (defaults to 2000000000)
  			  top: ($(window).height() / 2) - 50 + 'px', // Top position relative to parent in px
  			  left: $(window).width() / 2 + 'px' // Left position relative to parent in px
  			  
		};				
		$("#spin").show().spin(opts);
		$("#divLoader").css({
	        height:  $.getDocHeight() + 'px'
	    });
		
		mostrarBloqueEspera();
   });
});

$.getDocHeight = function(){
  var D = document;
  return Math.max(Math.max(D.body.scrollHeight,    D.documentElement.scrollHeight), Math.max(D.body.offsetHeight, D.documentElement.offsetHeight), Math.max(D.body.clientHeight, D.documentElement.clientHeight));
};

$.fn.spin = function(opts) {			
	this.each(function() {				
		var $this = $(this);					
		spinner = $this.data('spinner'); 				
		if (spinner) spinner.stop();				
		if (opts !== false) {				  
			opts = $.extend({color: $this.css('color')}, opts);				  
			spinner = new Spinner(opts).spin(this);				  
			$this.data('spinner', spinner);				
		}			
	});			
	return this;		
};

// show error ajax
function mostrarBloqueError() {
	$('#divErrorAjax').show();
}

//show loading bar
function mostrarBloqueEspera(id){
	if (id!=null) {
		$('#divLoader'+id).show();
	} else {
		$('#divLoader').show();
	}	
}

//hide loading bar
function ocultarBloqueEspera(id){
	if (id!=null) {
		$('#divLoader'+id).hide();
	} else {
		$('#divLoader').hide();
	}
}

function obtenerDescripcionSelect(select){
	if(document.getElementById(select).length > 0){
		var x=document.getElementById(select).selectedIndex;
		var y=document.getElementById(select).options;
		if (y[x].value != "") {
			return y[x].text;
		} else {
			return "";
		}
	} else {
		return "";
	}
}

//textarea event handler to add support for maxlength attribute
$(document).on('input keyup', 'textarea[maxlength]', function(e) {
    // maxlength attribute does not in IE prior to IE10
    // http://stackoverflow.com/q/4717168/740639
    var $this = $(this);
    var maxlength = $this.attr('maxlength');
    if (!!maxlength) {
        var text = $this.val();

        if (text.length > maxlength) {
            // truncate excess text (in the case of a paste)
            $this.val(text.substring(0,maxlength));
            e.preventDefault();
        }

    }

});

//Agregal el metodo .indexOf para IE8
//http://stackoverflow.com/questions/3629183/why-doesnt-indexof-work-on-an-array-ie8
if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length >>> 0;

    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;

    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}
  
$(document).ready(function() {
	/***********************************************************
	* Ajax
	**********************************************************/
	$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		if (!($.isEmpty(token)) && !($.isEmpty(header))) {
			jqXHR.setRequestHeader(header, token);
		}
	});

	$.ajaxSetup({
		error : function(x, tipoError, error) {
			ocultarBloqueEspera();
			$(window).off('beforeunload');
			$('#bloqueErrorAjax').trigger('click');
			
			if (x.status == 0) {
				$("#spanErrorAjax").html("Se ha producido la caida de la aplicación SHIVA");
			} else if (x.status == 409	&& !$.isEmpty(x.responseText)) {
				var excepcion = $.parseJSON(x.responseText);
				excepcion.versionShiva = versionShiva;
				var respTextError = prepararPantallaExcepcion(excepcion);
//							excepcion.titulo, excepcion.errorFechaHora,
//							excepcion.timeError, excepcion.mensajeAuxiliar,versionShiva);
				$.cargarPantalla(respTextError);
			} else if (x.status == 403 || x.status == 404) {
				window.location.href = urlAsynchronousHTTP + "/denegarAcceso";
			} else if (x.status == 500) {
				var respTextError = prepararPantallaErrorAjax("Se ha producido el error interno (500) del servidor",versionShiva);
				$.cargarPantalla(respTextError);
			} else if (tipoError == 'parsererror') {
				if (x.status == 200 && !$.isEmpty(x.responseText)) {
					// Ahi se captura la cualquier excepcion
					$.cargarPantalla(x.responseText);
				} else {
					var respTextError = prepararPantallaErrorAjax(
							"Se ha producido el error de parseo JSON en la respuesta",
							versionShiva);
					$.cargarPantalla(respTextError);
				}
			} else if (error == 'timeout') {
				var respTextError = prepararPantallaErrorAjax(
					"Se ha producido un time out de la respuesta",
						versionShiva);
				$.cargarPantalla(respTextError);
			} else if (error == 'abort') {
				var respTextError = prepararPantallaErrorAjax(
						"Se ha producido un aborto de la respuesta",
							versionShiva);
				$.cargarPantalla(respTextError);
			} else {
				var respTextError = prepararPantallaErrorAjax(
					"Se ha producido un error desconocido: "
						+ x.responseText, versionShiva);
				$.cargarPantalla(respTextError);
			}
		}
	});

	(function($) {
		$.cargarPantalla = function(responseText) {
			try {
				document.documentElement.innerHTML = responseText;
			} catch (error) {
				// IE makes document.documentElement read only
				document.body.innerHTML = responseText;
			}
		};
	})(jQuery);
	
	
	(function($) {
		$.isEmpty = function(obj) {
			return (!obj || $.trim(obj) === "");
		};
	})(jQuery);

	(function($, window) {
		$.fn.replaceOptions = function(options) {
			var self, $option;
			this.empty();
			self = this;
			$.each(options, function(index, option) {
				$option = $("<option></option>").attr("value",
						option.value).text(option.text);
				self.append($option);
			});
		};
	})(jQuery, window);
});
/**
 * Cambia un string Camel case a undersScorr
 * "CamelCase => Camel_Case"
 * si isUpperCase = true. Mantiene las mayusculas
 * @param camelCase
 * @param isUpperCase
 * @returns {String}
 */
function camelCaseToUnderscore(camelCase, isUpperCase, isfirst) {
	var upperCase = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	var under = "";

	for(var i = 0, size = camelCase.length; i<size; i++) {
		if (upperCase.indexOf(camelCase.charAt(i), 0) != -1) {
			if (i == 0) {
				if (isfirst) {
					under = "" + (isUpperCase?camelCase.charAt(i).toUpperCase() : camelCase.charAt(i).toLowerCase());
				} else {
					under = "" + (isUpperCase?camelCase.charAt(i).toLowerCase() : camelCase.charAt(i).toLowerCase());
				}
			} else {
				under += "_" + (isUpperCase?camelCase.charAt(i) : camelCase.charAt(i).toLowerCase());
			}
		} else {
			if (i == 0) {
				under += (isUpperCase?camelCase.charAt(i).toUpperCase() : camelCase.charAt(i).toLowerCase());
			} else {
				under += (isUpperCase?camelCase.charAt(i) : camelCase.charAt(i).toLowerCase());
			}
		}
	}
	
	return under;
}
//Preparo el mensaje de error via ajax
function prepararPantallaErrorAjax(mensajeError, versionShiva) {
	var hoy = new Date();
	var fecha=hoy.getDate() + "/" + (hoy.getMonth()+1) + "/" + hoy.getFullYear() +" "+ hoy.getHours() + ':' + hoy.getMinutes() + ':' + hoy.getSeconds();
	var html = "<html><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
			"<link href=\""+urlAsynchronousHTTP+"/css/bootstrap.css\" rel=\"stylesheet\"><link href=\""+urlAsynchronousHTTP+"/css/style.css\" rel=\"stylesheet\">" +
			"<link href=\""+urlAsynchronousHTTP+"/css/jquery-ui.css\" rel=\"stylesheet\"><link href=\""+urlAsynchronousHTTP+"/css/TableTools.css\" rel=\"stylesheet\">"+
			"<link href=\""+urlAsynchronousHTTP+"/css/jquery.dataTables-1.10.css\" rel=\"stylesheet\">" +
			"<script src=\""+urlAsynchronousHTTP+"/js/jquery-1.10.2.js\"></script><script src=\""+urlAsynchronousHTTP+"/js/jquery-ui.js\"></script>" +
			"<script src=\""+urlAsynchronousHTTP+"/js/bootstrap.js\"></script></head>" +
			"<body><div id=\"container\"><div id=\"header\"><div class=\"wrap\"><div id=\"logos\">" +
			"<img title=\"Telecom\" src=\""+urlAsynchronousHTTP+"/img/logo.png\" alt=\"Telecom\"   style=\"margin: 1px 6px 6px 6px\" />" +
			"<img title=\"Arnet\" src=\""+urlAsynchronousHTTP+"/img/logo-arnet.jpg\" alt=\"Arnet\"  style=\"margin: 1px 6px 6px 6px\" />" +
			"</div></div></div>" +
			"<div id=\"sso\"><div class=\"wrap\"><p>Bienvenido a Shiva </p></div></div><div id=\"main\"><div class=\"wrap\"><div id=\"content\">" +
			"<div id=\"inside\"><div class=\"alert alert-error\"><strong>Error de Aplicación!</strong><br />" +
			"<br>Se produjo un error de aplicaci\u00F3n, int\u00E9ntelo nuevamente o consulte con el administrador.<br>" +
			"<br>Fecha y hora del incidente:&nbsp;"+fecha+"<br>" +
			"<br>"+mensajeError+"<br>" +
			"</div></div></div><!-- end #content --></div><!-- end .wrap --></div><!-- end #main -->" +
			"<div id=\"footer\" style=\"display: block;\"><div style=\"color: #fff; padding-top: 10px; padding-right: 10px;\" align=\"right\">" +
			"<p>Versión&nbsp;"+versionShiva+"</p></div></div> <!-- end #footer  --></div><!-- end #container --></body></html>";
	return html;
}

//Preparo el mensaje de error via ajax
function prepararPantallaExcepcion(excepcion) {
	var htmMantenimiento = '';

	if ('true' === excepcion.esPerfilMantenimiento) {
		htmMantenimiento = '<br><table class="title"><tr><td><input id="details" type="button" value="Detalles" class="btn btn-primary btn-small"';
		htmMantenimiento += 'onclick="showError();" /></td></tr><tr><td><div id="error" style="visibility:hidden;"><div class="title" style="width:800px;">';
		htmMantenimiento += excepcion.errorDescripcion;
		htmMantenimiento +='</div><div style="width:800px; color:red;">';
		htmMantenimiento += excepcion.errorSpecified;
		htmMantenimiento += '</div><div style="width:800px;">&nbsp;</div><div class="title" style="width:800px;">';
		htmMantenimiento += excepcion.errorDetalle;
		htmMantenimiento += '</div><div class="divError">';
		htmMantenimiento += excepcion.error;
		htmMantenimiento += '</div><div><input id="hide"class="btn btn-primary btn-small" style="margin-right: 63px" type="button" value="Ocultar" ';
		htmMantenimiento += 'style="visibility:hidden;" onclick="hideError();"></div></div></td></tr></table>';
	}
	
	return "<html><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
			"<link href=\""+urlAsynchronousHTTP+"/css/bootstrap.css\" rel=\"stylesheet\"><link href=\""+urlAsynchronousHTTP+"/css/style.css\" rel=\"stylesheet\">" +
			"<link href=\""+urlAsynchronousHTTP+"/css/jquery-ui.css\" rel=\"stylesheet\"><link href=\""+urlAsynchronousHTTP+"/css/TableTools.css\" rel=\"stylesheet\">"+
			"<link href=\""+urlAsynchronousHTTP+"/css/jquery.dataTables-1.10.css\" rel=\"stylesheet\">" +
			"<link href=\""+urlAsynchronousHTTP+"/css/excepcion.css\" rel=\"stylesheet\">" +
			"<script src=\""+urlAsynchronousHTTP+"/js/jquery-1.10.2.js\"></script><script src=\""+urlAsynchronousHTTP+"/js/jquery-ui.js\"></script>" +
			"<script src=\""+urlAsynchronousHTTP+"/js/bootstrap.js\"></script></head>" +
			"<body><div id=\"container\"><div id=\"header\"><div class=\"wrap\"><div id=\"logos\">" +
			"<img title=\"Telecom\" src=\""+urlAsynchronousHTTP+"/img/logo.png\" alt=\"Telecom\"   style=\"margin: 1px 6px 6px 6px\" />" +
			"<img title=\"Arnet\" src=\""+urlAsynchronousHTTP+"/img/logo-arnet.jpg\" alt=\"Arnet\"  style=\"margin: 1px 6px 6px 6px\" />" +
			"</div></div></div>" +
			"<div id=\"sso\"><div class=\"wrap\"><p>Bienvenido a Shiva </p></div></div><div id=\"main\"><div class=\"wrap\"><div id=\"content\">" +
			"<div id=\"inside\"><div class=\"alert alert-error\"><strong>Error de Aplicación!</strong><br />" +
			"<br>"+excepcion.titulo+"<br>" +
			"<br>Fecha y hora del incidente:&nbsp;"+excepcion.errorFechaHora+"<br>" +
			"<br>Numero del incidente:&nbsp;"+ excepcion.timeError+"<br>" +
			"</div>" + htmMantenimiento + "</div></div><!-- end #content --></div><!-- end .wrap --></div><!-- end #main -->" +
			"<div id=\"footer\" style=\"display: block;\"><div style=\"color: #fff; padding-top: 10px; padding-right: 10px;\" align=\"right\">" +
			"<p>Versión&nbsp;"+excepcion.versionShiva+"</p></div></div> <!-- end #footer  --></div><!-- end #container --></body></html>";
}

String.prototype.startsWith = function(prefix) {
    return this.indexOf(prefix) === 0;
};
// u578936 Max  originalmente estaba en jquery.funciones.cobros.js lo agrego para que se pueda acceder desde las demas js
function clearInputText(divId) {
	$('input:text', '#' + divId).val('');
};
//Limpia inputText y Select(deja seleccionado la primera opcion) contenidos en un div
function clearSelectInput(divId) {
	clearInputText(divId);
	$('select', '#' + divId).each(
		function(index) {  
			$(this)[0].selectedIndex = 0;
		}
	);
	clearTextAtra(divId);
};
function clearInputText(divId) {
	$('input:text', '#' + divId).val('');
};
function clearTextAtra(divId) {
	$('textarea', '#' + divId).each(
			function(index) {
				$(this).val("");
			}
	);
};
function validarDivImputSinGuardar(divId) {
	var inputCompleto = false;
	var selectCompleto = false;
	var textAreaCompleto = false;
	
	
	$('input:text', '#' + divId).each(
		function(index) {
			if (!$.isEmpty($(this).val())) {
				inputCompleto = true;
				return false;
			}
		}	
	);
	
	$('select', '#' + divId).each(
		function(index) {  
			if ($(this)[0].selectedIndex !== 0) {
				selectCompleto = true;
				return false;
			}
		}
	);
	$('textarea', '#' + divId).each(
			function(index) {
				if (!$.isEmpty($(this).val())) {
					textAreaCompleto = true;
					return false;
				}
			}	
	);

	return inputCompleto || selectCompleto || textAreaCompleto;
};
var equalTo = function (arg0, arg1) {
	return arg0 == arg1;
};
var notEqual = function(arg0, arg1) {
	return arg0 != arg1;
};
var greaterThan = function(arg0, arg1) {
	return arg0 > arg1;
};
var lessThan = function(arg0, arg1) {
	return arg0 < arg1;
};
var greaterThanOrEqualTo = function(arg0, arg1) {
	return arg0 >= arg1;
};
var lessThanOrEqualTo = function(arg0, arg1) {
	return arg0 <= arg1;
};
function ocultarDiv(selector) {
	$('#' + selector).hide();
}

function mostrarDiv(selector) {
	$('#' + selector).show();
}

function ocultarDivs(listaSelectores) { 
	listaSelectores.forEach(function(selector,i) {
		ocultarDiv(selector);
	});
}

function mostrarDivs(listaSelectores) {
	listaSelectores.forEach(function(selector,i) {
		mostrarDiv(selector);
	});
}

