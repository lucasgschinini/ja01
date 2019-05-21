<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="Autorizacion de Talonarios" content="Modelo">
	<title><spring:message code="recibo.autorizacion.titulo"/></title>
   	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
   	<script src="${pageContext.request.contextPath}/js/numero-dash-numero.js"></script>
</head>
    
<body style="height: 100%">

<div id="container" >

	<jsp:include page="../template/cabecera.jsp"></jsp:include>

	<div id="main">

		<div class="wrap">

			<jsp:include page="../template/menu.jsp"></jsp:include>

			<div id="content">
			
				<div id="inside">

					<div id="payments" class="box">
						<div class="title"><spring:message code="recibo.autorizacion.titulo.pagina"/></div>

						<div class="pagos_anticipos">
							<p><strong><spring:message code="recibo.talonarios"/></strong></p>

							<div class="row">
																<div class="span9">

									<table id="talonariosRendidos" class="tablaResultado">
										<thead>
										<tr>
											<th><spring:message code="recibo.espacio"/></th>
											<th><spring:message code="recibo.empresa"/></th>
											<th><spring:message code="recibo.segmento"/></th>
											<th><spring:message code="recibo.nroSerie"/></th>
											<th><spring:message code="recibo.nroReciboDesde"/></th>
											<th><spring:message code="recibo.nroReciboHasta"/></th>
											<th><spring:message code="recibo.estado"/></th>
											<th class="dateTimeSeconds"><spring:message code="recibo.fechaAlta"/></th>
											<th><spring:message code="recibo.usuarioAlta"/></th>
											<th class="dateTimeSeconds"><spring:message code="recibo.fechaAsignacion"/></th>
											<th><spring:message code="recibo.usuarioAsignacion"/></th>
											<th><spring:message code="recibo.gestorAsignado"/></th>
											<th class="dateTimeSeconds"><spring:message code="recibo.fechaRendicion"/></th>
											<th><spring:message code="recibo.usuarioRendicion"/></th>
										</tr>
										</thead>
										<tbody>
											<c:forEach items="${listaTalonariosDto}" var="tal">
												<tr>
													<td>
														<input type="radio" id="radioIdTalonario" name="radioIdTalonario" value="${tal.idTalonario}" onclick="javascript:buscarRecibos(${tal.idTalonario})"/>
														<input type="hidden" id="empresa_${tal.idTalonario}" name="empresa_${tal.idTalonario}" value="${tal.empresa}">
														<input type="hidden" id="rangoDesde_${tal.idTalonario}" name="rangoDesde_${tal.idTalonario}" value="${tal.rangoDesde}">
														<input type="hidden" id="rangoHasta_${tal.idTalonario}" name="rangoHasta_${tal.idTalonario}" value="${tal.rangoHasta}">
													</td>
													<td>${tal.empresa}</td>
													<td>${tal.segmento}</td>
													<td>${tal.nroSerie}</td>
													<td>${tal.rangoDesde}</td>
													<td>${tal.rangoHasta}</td>
													<td>${tal.estado}</td>
													<td>${tal.fechaAltaFormateado}</td>									
													<td style="text-align: left">
														<c:choose>
															<c:when test="${tal.usuarioAlta eq '-'}">
																<p style="text-align: center"> - </p>
															</c:when>
															<c:otherwise>
															  <div style="width: 140px;">
																	<img alt="Usuario" src="${tal.urlFotoUsuario(tal.usuarioAlta)}" 
																	style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																	onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																		
																	${tal.nombreUsuarioAlta} <br>
																	<a href="sip:${tal.mailUsuarioAlta}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																	<a href="mailto:${tal.mailUsuarioAlta}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
															   </div>
														   </c:otherwise>
														</c:choose>
													</td>
													<td>${tal.fechaAsignacion}</td>
													<td style="text-align: left">
														<c:choose>
															<c:when test="${tal.usuarioAsignacion eq '-'}">
																<p style="text-align: center"> - </p>
															</c:when>
															<c:otherwise>
															  <div style="width: 140px;">
																<img alt="Usuario" src="${tal.urlFotoUsuario(tal.usuarioAsignacion)}" 
																style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																${tal.nombreUsuarioAsignacion} <br>
																<a href="sip:${tal.mailUsuarioAsignacion}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																<a href="mailto:${tal.mailUsuarioAsignacion}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
															   </div>
															</c:otherwise>
														</c:choose>
													</td>
													<td>${tal.usuarioGestor}</td>
													<td>${tal.fechaRendicion}</td>
													<td style="text-align: left">
														<c:choose>
															<c:when test="${tal.usuarioRendicion eq '-'}">
																<p style="text-align: center"> - </p>
															</c:when>
															<c:otherwise>
																<div style="width: 140px;">
																	<img alt="Usuario" src="${tal.urlFotoUsuario(tal.usuarioRendicion)}" 
																	style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																	onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																	${tal.nombreUsuarioRendicion} <br>
																	<a href="sip:${tal.mailUsuarioRendicion}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																	<a href="mailto:${tal.mailUsuarioRendicion}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																</div>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
													
						<div class="pagos_anticipos">
							<p><strong><spring:message code="recibo.recibosDelTalonario"/></strong></p>
								
							<div class="row">		
								<div class="span9">
	  								<table id="recibosDelTalonario" class="tablaResultado">
										<thead>
											<tr>
												<th class="nroDashNro"><spring:message code="recibo.nroRecibo"/></th>
												<th><spring:message code="recibo.valores"/></th>
												<th><spring:message code="recibo.compensaciones"/></th>
												<th><spring:message code="recibo.importe"/></th>
												<th><spring:message code="recibo.estado"/></th>
												<th><spring:message code="recibo.usuarioAnulacion"/></th>
												<th class="dateTimeSeconds"><spring:message code="recibo.fechaAnulacion"/></th>											
												<th class="date"><spring:message code="recibo.fechaIngreso"/></th>	
												<th><spring:message code="recibo.segmento"/></th>
												<th><spring:message code="recibo.empresa"/></th>
											</tr>
										</thead>
										<tbody>
										
										</tbody>
									</table>
								</div>
							</div>	
							
							<form:form id="formAutorizacion" commandName="talonarioDto" action="${pageContext.request.contextPath}/autorizar-talonarios">
								<input type="hidden" id="operation" name="operation"/>
								<input type="hidden" id="idTalonario" name="idTalonario">
								<input type="hidden" id="empresaDto" name="empresa">
								<input type="hidden" id="rangoDesdeDto" name="rangoDesde">
								<input type="hidden" id="rangoHastaDto" name="rangoHasta">
								<input type="hidden" id="pantallaDestino" name="pantallaDestino" value="${pantallaDestino}"/>
								
								<div id="bloqueObservacion">
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observaciones"><spring:message code="recibo.observaciones"/></label>
											<textarea class="field span9" id="observaciones" name="observaciones" maxlength="250">${talonarioDto.observaciones}</textarea>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span9">
											<form:errors path="observaciones" cssClass="error"/>
										</div>
									</div>
								</div>
								
								<div id="bloqueBotonera">
									<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 5px">
										<div align="right">
											<button class="btn btn-primary btn-small" id="autorizar" name="autorizar" disabled type="button" onclick="autorizarRendicion();"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="recibo.botonAutorizarRendicion"/></button>
											<button class="btn btn-primary btn-small" id="rechazar" name="rechazar" disabled type="button" onclick="rechazarRendicion();"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="recibo.botonRechazarRendicion"/></button>
											<c:if test="${mostrarBotonCancelar}">
												<button class="btn btn-primary btn-small" id="cancelar" name="cancelar" type="button" onclick="regresarABandejaEntrada();"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="recibo.botonCancelar"/></button>
											</c:if>
										</div>
									</div>
								</div>
								<c:if test="${talonarioDto.errorRendicion}">
									<div class="alert alert-error">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br />${talonarioDto.errorRendicionMensaje}
									</div>
								</c:if>
							</form:form>
							
						</div>								
					</div>
				</div>
			</div><!-- end #content -->
		</div><!-- end .wrap -->
	</div><!-- end #main -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	
</div><!-- end #container -->

<c:if test="${(not empty talonarioDto) and (not empty talonarioDto.idTalonario)}">
	<c:set var="codigoTalonario" value="${fn:replace(talonarioDto.idTalonario, ' ', '')}" />
</c:if>

<script>
	// Tratamiento de scrolling para las tablas de la pagina.
	// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
		// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
		// En IE8 no hace falta llamar a fnCellRender
	 var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
		return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0], [8,10,13]);
	};
	window.onload = function() {
		var idTablaTalonariosRendidos = 'talonariosRendidos';
		var idTablaPreimpreso = 'recibosDelTalonario';
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";
		
		inicializarTablaCon_PDF_XLS_ColumnaOcultaNoOrdenada(idTablaTalonariosRendidos,6, (window.DOMParser) ? fnCellRender : null);
		agregarOrdenFecha(idTablaTalonariosRendidos);
		$('#' + idTablaTalonariosRendidos).dataTable().fnSort( [ [7,'asc'] ] );
		
		inicializarTablaCon_PDF_XLS_SelTodos(idTablaPreimpreso, claseRegistros, idSeleccionarTodos,
					undefined);
		
		agregarOrdenFecha(idTablaPreimpreso);
		
		$('#' + idTablaTalonariosRendidos).ready(function(){
			if($("input:radio", $('#' + idTablaTalonariosRendidos).dataTable().fnGetNodes()).length>0) {
				$('input:radio[name=radioIdTalonario]')[0].checked = true;
				$('#autorizar').attr('disabled',false);
				$('#rechazar').attr('disabled',false);
		        buscarRecibos($('#radioIdTalonario').val());
			}
		});
	};
	
	//************************************************************************************
	//* Metodos funcionales
	//************************************************************************************
	function buscarRecibos(idTalonario) {
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";
		$('#bloqueEspera').trigger('click');
		
		$("#observaciones").val("");
		$('#idTalonario').val(idTalonario);
		$('#empresaDto').val($('#empresa_'+idTalonario).val());
		$('#rangoDesdeDto').val($('#rangoDesde_'+idTalonario).val());
		$('#rangoHastaDto').val($('#rangoHasta_'+idTalonario).val());
			
		var url = '${pageContext.request.contextPath}/consultarRecibosTalonario?method=load&ajax=true&nocache='+Math.random();
		var param = "&tabla=autorizacion&talonarioId="+idTalonario;
		$('#recibosDelTalonario').dataTable().fnDestroy();
		inicializarTablaCon_PDF_XLS_SelTodos('recibosDelTalonario', claseRegistros, idSeleccionarTodos,
				undefined);
	
		$.ajaxSetup({ scriptCharset: "ISO-8859-1" , contentType: "application/json; charset=ISO-8859-1"});
	    $.getJSON(url+param, function(json) {
	    	var dataTable = $('#recibosDelTalonario').dataTable();
	  		dataTable.fnClearTable(this);
	  		
	  		if (json.aaData.length) {
  				//if (json.aaData.toLowerCase().indexOf("error") >= 0) {
  				//	alert(json.aaData);		
  				//} else {
  					dataTable.fnAddData(json.aaData);
  				//}   		
	  		}
	    	dataTable.fnDraw();
	    	agregarOrdenFecha('recibosDelTalonario');
	    	dataTable.fnSort( [ [0,'desc'] ] );
  			ocultarBloqueEspera();   
  			
  			$("#seleccionarTodos").click(function () {
		    	$('.opcion').prop('checked', $(this).prop('checked'));
		    });
		}); 
	}
	
	function autorizarRendicion() {
		$('#bloqueEspera').trigger('click');
		$('#operation').val('<%=Constantes.AUTORIZAR_RENDICION%>');
		$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/autorizar-rendicion-talonario";
		$('#formAutorizacion')[0].submit();
	}
	
	function rechazarRendicion() {
		$('#bloqueEspera').trigger('click');
		$('#operation').val('<%=Constantes.RECHAZAR_RENDICION%>');
		$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/rechazar-rendicion-talonario";
		$('#formAutorizacion')[0].submit();
	}
	
	function regresarABandejaEntrada() {
		$('#bloqueEspera').trigger('click');
		$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/regresar-bandeja-de-entrada";
		$('#formAutorizacion')[0].submit();	
	}
	
	function bloqueUsuario() {
		$(".bloqueUsuario").closest("img").attr("src","${pageContext.request.contextPath}/img/default2.png");
	};
	

</script>
    
</body>
</html>
