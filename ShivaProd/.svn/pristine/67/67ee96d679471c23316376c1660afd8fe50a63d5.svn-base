<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado" %>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="Búsqueda de Talonarios" content="Modelo">
	<title><spring:message code="recibo.busqueda.titulo"/></title>
   
   	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
   	<script src="${pageContext.request.contextPath}/js/numero-dash-numero.js"></script>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.signin.css"/>
   	<style>
	   	.alert {
				padding: 8px 35px 8px 14px;
				margin-bottom: 18px;
				text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
				color: #c09853;
				background-color: #fcf8e3;
				border-color: #eed3d7;
				border: 1px solid #fbeed5;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
		}
		.alert-error {
		color: #b94a48;
		background-color: #f2dede;
		border-color: #eed3d7;
		}
   	</style>
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
						<div class="title"><spring:message code="recibo.busqueda.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="recibo.busquedaTalonario"/></strong></p>

							<form:form id="formBusqueda" commandName="talonarioFiltro" action="${pageContext.request.contextPath}/buscar-talonarios">
								<form:hidden path="url" id="url"/>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="empresa"><span class="rojo">* </span><spring:message code="recibo.empresa"/></label>
										<div class="controls ">
											<select id ="empresa" name="empresa" class="input">
												<c:if test="${comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq talonarioFiltro.empresa}">
															<option value="${emp.idEmpresa}" selected>${emp.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${emp.idEmpresa}">${emp.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="segmento"><span class="rojo">* </span><spring:message code="recibo.segmento"/></label>
										<div id="ctrlSegmento" class="controls">
											<select id ="segmento" name="segmento" class="input">
												<c:if test="${comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq talonarioFiltro.segmento}">
															<option value="${seg.idSegmento}" selected>${seg.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${seg.idSegmento}">${seg.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idEstado"><spring:message code="recibo.estado"/></label>
										<div class="controls">
											<select id="idEstado" name="idEstado" class="input" >
												<option value=""><spring:message code="combo.seleccionar"/></option>
												<c:forEach items="${estados}" var="est">
													<c:choose>
														<c:when test="${est.name() eq talonarioFiltro.idEstado}">
															<option value="${est.name()}" selected>${est.descripcion()}</option>
														</c:when>
														<c:otherwise>
															<option value="${est.name()}">${est.descripcion()}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>							
									</div>
								</div>
								
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="empresa" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="segmento" cssClass="error" />
									</div>
								</div>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="nroSerie"><spring:message code="recibo.nroSerie"/></label>
										<div class="controls">
											<input id="nroSerie" name="nroSerie" maxlength="4" type="text"
												value="${talonarioFiltro.nroSerie}" class="input">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="rangoDesde"><spring:message code="recibo.nroReciboDesde"/></label>
										<div class="controls">
											<input id="rangoDesde" name="rangoDesde" maxlength="8" type="text"
												value="${talonarioFiltro.rangoDesde}" class="input">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="rangoHasta"><spring:message code="recibo.nroReciboHasta"/></label>
										<div class="controls">
											<input id="rangoHasta" name="rangoHasta" maxlength="8" type="text"
												value="${talonarioFiltro.rangoHasta}" class="input">
										</div>
									</div>
								</div>
			
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="nroSerie" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="rangoDesde" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="rangoHasta" cssClass="error" />
									</div>
								</div>
			
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="buscar" name="buscar" type="button" onclick="buscarTalonarios();"><i class="icon-white icon-search"></i>&nbsp;<spring:message code="recibo.botonBuscar"/></button>
									</div>
								</div>
							</form:form>
						</div>

						<form:form id="formTalonarioTabla" commandName="talonarioDto">
							<div class="pagos_anticipos">
								<p><strong><spring:message code="recibo.resultadoBusquedaTalonarios"/></strong></p>
								<input type="hidden" id="timeStampAux" name="timeStampAux" value="${timeStampAux}"/>
								<input type="hidden" id="operation" name="operation"/>
								<input type="hidden" id="idTalonario" name="idTalonario">
								<input type="hidden" id="empresaDto" name="empresa">
								<input type="hidden" id="rangoDesdeDto" name="rangoDesde">
								<input type="hidden" id="rangoHastaDto" name="rangoHasta">
								<input type="hidden" id="estadoTalonario" name="estado" />
								<div class="row">
									<div class="span9">
										<table id="resultadoBusquedaTalonarios" class="tablaResultado">
										
											<thead>
												<tr>
													<th><spring:message code="recibo.espacio"/></th>
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
													<th class="dateTimeSeconds"><spring:message code="recibo.fechaAprobacionRendicion"/></th>
													<th><spring:message code="recibo.usuarioAprobacionRendicion"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaTalonariosDto}" var="tal" varStatus="i">								
												<tr>
													<td>
														<c:choose>
															<c:when test="${tal.idTalonario eq talonarioDto.idTalonario}">
																<input checked type="radio" id="radioIdTalonario" name="radioIdTalonario" value="${tal.idTalonario}" onclick="javascript:buscarRecibos(${tal.idTalonario})"/>
															</c:when>
															<c:otherwise>
																<input type="radio" id="radioIdTalonario" name="radioIdTalonario" value="${tal.idTalonario}" onclick="javascript:buscarRecibos(${tal.idTalonario})"/>
															</c:otherwise>
														</c:choose>
														<input type="hidden" id="empresa_${tal.idTalonario}" name="empresa_${tal.idTalonario}" value="${tal.empresa}">
														<input type="hidden" id="rangoDesde_${tal.idTalonario}" name="rangoDesde_${tal.idTalonario}" value="${tal.rangoDesde}">
														<input type="hidden" id="rangoHasta_${tal.idTalonario}" name="rangoHasta_${tal.idTalonario}" value="${tal.rangoHasta}">
														<input type="hidden" id="estadoTalonario_${tal.idTalonario}" name="estadoTalonario_${tal.idTalonario}" value="${tal.idEstado}" />
													</td>
													<td>
														<c:if test="${(tal.esAsignadoSupervisor and sessionScope.loginUser.esSupervisorTalonario)
																		or (tal.esDerivAdminTalonario and sessionScope.loginUser.esAdminTalonario)}">
															<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																<button id="btnModificar" class="btn btn-xs btn-link" type="button" title="Editar" onclick="javascript:modificarTalonario(${tal.idTalonario});">
																	<i class="icon-edit bigger-120"></i>
																</button>
															</div>
														</c:if>
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
															  <div style="width: 180px;">
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
													<td>${tal.fechaAprobacionRendicion}</td>
													<td style="text-align: left">
														<c:choose>
															<c:when test="${tal.usuarioAprobacionRendicion eq '-'}">
																<p style="text-align: center"> - </p>
															</c:when>
															<c:otherwise>
															  <div style="width: 140px;">
																<img alt="Usuario" src="${tal.urlFotoUsuario(tal.usuarioAprobacionRendicion)}" 
																style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																
																onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																${tal.nombreUsuarioAprobacionRendicion} <br>
																<a href="sip:${tal.mailUsuarioAprobacionRendicion}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																<a href="mailto:${tal.mailUsuarioAprobacionRendicion}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
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
								
								<c:if test="${sessionScope.loginUser.puedeRechazarTalonario}">
								<div id="bloqueRechazar">
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observaciones"><spring:message code="recibo.observaciones"/></label>
											<textarea class="field span9" id="observaciones" name="observaciones"  maxlength="250" >${talonarioDto.observaciones}</textarea>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span9">
											<form:errors path="observaciones" cssClass="error"/>
										</div>
									</div>
									<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
										<div align="right">
											<button class="btn btn-primary btn-small" id="rechazar" name="rechazar" disabled type="button" onclick="javascript:rechazarTalonario()"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="recibo.botonRechazar"/></button>
										</div>
									</div>
								</div>
								</c:if>
							</div>
							</form:form>
							<form:form id="formRecibo" commandName="reciboDto">
								<input type="hidden" id="id" name="id" />
								<input type="hidden" id="timeStamp" name="timeStamp"/>
								<input type="hidden" id="recibosAAnularSelected" name="recibosAAnularSelected"/>
								<input type="hidden" id="idTalonarioSelected" name="idTalonarioSelected" />
								
								<div class="pagos_anticipos">
									<p><strong><spring:message code="recibo.recibosDelTalonario"/></strong></p>
									<div class="row">		
										<div class="span9">
		  								
										<table id="resultadoBusquedaReciboPreimpreso" class="tablaResultado">
											<thead>
												<tr>
													<th style="padding: 3px 10px; text-align: center;">
													<input type="checkbox" id="seleccionarTodos">
													</th>
													<th><spring:message code="recibo.espacio"/></th>
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
							</div>
							<c:if test="${sessionScope.loginUser.puedeAnularTalonario}">
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 5px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnAnular" name="btnAnular" onclick="anular()" type="button"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="recibo.botonAnular"/></button>
									</div>
								</div>
							</c:if>
							<c:if test="${sessionScope.loginUser.puedeRendirTalonario}">
								<div class="row">
									<div align="center">
										<button class="btn btn-primary btn-small" id="rendir" name="rendir" type="button" disabled onclick="rendirTalonario()"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="recibo.botonRendir"/></button>
									</div>
								</div>
								<div class="row">
									<div class="container form-signin">
										<c:if test="${talonarioDto.errorRendicion}">
											<div class="alert alert-error">
												<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br />${talonarioDto.errorRendicionMensaje}
											</div>
										</c:if>
									</div>
								</div>
							</c:if>
							<div class="row"><div align="center">&nbsp;</div></div>
							<c:if test="${talonarioDto.errorNingunaModificacion}">
							<div class="row">
								<div align="center">
									<div class="alert alert-error" style="width: 450px;" align="left">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br />${talonarioDto.descripcionNingunaModificacion}
									</div>
								</div>
							</div>				
							<div class="row"><div align="center">&nbsp;</div></div>		
							</c:if>
						</form:form>
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
		var primeraVezBuscarRecibos = true;
		$(document).ready(function() {
			$('#btnAnular').attr('disabled','disabled');
 		}); 

		var idTablaPreimpreso = 'resultadoBusquedaReciboPreimpreso';
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";
		
		function habilitarAnular() {
			if ($(".anulable:checked", $("#" + idTablaPreimpreso).dataTable().fnGetNodes()).length != 0){
	        	$('#btnAnular').attr('disabled',false);
			}else{
				$('#btnAnular').attr('disabled',true);
			};
		};
		// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
		// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
		// En IE8 no hace falta llamar a fnCellRender
		 var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
			return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0], [9, 11, 14, 16]);
		};
		// Tratamiento de scrolling para las tablas de la pagina.
		window.onload = function() {
			var idTablaTalonarios = 'resultadoBusquedaTalonarios';
			inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrdenada(idTablaTalonarios, (window.DOMParser) ? fnCellRender : null);
			agregarOrdenFecha(idTablaTalonarios);
			$('#' + idTablaTalonarios).dataTable().fnSort( [ [8,'asc'] ] );
			
			inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTablaPreimpreso, claseRegistros, idSeleccionarTodos,
					undefined);
			agregarOrdenFecha(idTablaPreimpreso);
			
			var radioIdTalonarioChecked = $('input:radio[name=radioIdTalonario]:checked').size(); 
			if (radioIdTalonarioChecked > 0) {
	           	buscarRecibos($('#radioIdTalonario:checked').val());
			} else {
				if ($('input:radio[name=radioIdTalonario]')[0]!= null){	
	        		$('input:radio[name=radioIdTalonario]')[0].checked = true;
		        	buscarRecibos($('#radioIdTalonario').val());
				};
			};
			
			$("#seleccionarTodos").click(function () {
		    	$('.opcion').prop('checked', $(this).prop('checked'));
		    	check();
		    });
		};
		
		$("#empresa").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#url').val("talonario/talonario-busqueda");
			$('#formBusqueda')[0].action="${pageContext.request.contextPath}/talonario-busqueda-seleccionoEmpresa";
			$('#formBusqueda')[0].submit();
	 	});
 
	    // if all checkbox are selected, check the selectall checkbox
	    // and viceversa
	    function check(){
	    	habilitarAnular();
	    };
	    
		//************************************************************************************
		//* Metodos funcionales
		//************************************************************************************
		function buscarRecibos(idTalonario) {
			$('#bloqueEspera').trigger('click');
			
			$('#idTalonario').val(idTalonario);
			$('#empresaDto').val($('#empresa_'+idTalonario).val());
			$('#rangoDesdeDto').val($('#rangoDesde_'+idTalonario).val());
			$('#rangoHastaDto').val($('#rangoHasta_'+idTalonario).val());
			$('#estadoTalonario').val($('#estadoTalonario_'+idTalonario).val());
			if ('${sessionScope.loginUser.puedeRechazarTalonario}'=='true') {
				
				if ($('#estadoTalonario').val() == '<%=Estado.TAL_ASIGNADO_SUPERVISOR.name()%>') {
					habilitarRechazo();
				} else {
					deshabilitarRechazo();	
				}
			}
			
			primeraVezBuscarRecibos = false;			
			$('#' + idTablaPreimpreso).dataTable().fnDestroy();
			inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTablaPreimpreso, claseRegistros, idSeleccionarTodos, undefined);
			
			
			var url = '${pageContext.request.contextPath}/consultarRecibosTalonario?method=load&ajax=true&nocache='+Math.random();
			var param = "&talonarioId="+idTalonario+"&estado="+$('#estadoTalonario_'+idTalonario).val();
			
			$.ajaxSetup({ scriptCharset: "ISO-8859-1" , contentType: "application/json; charset=ISO-8859-1" });
		    $.getJSON(url+param, function(json) {
		    	
	   			var dataTable = $('#' + idTablaPreimpreso).dataTable();
	   			
	   			dataTable.fnClearTable(true);
	   			
	   			if (json.aaData.length) {
	   		   		dataTable.fnAddData(json.aaData);
	   		   	}
	   		   	
	   		  	dataTable.fnDraw();
	   		  	
	   			if ('${sessionScope.loginUser.puedeRendirTalonario}'=='true') {
	   				if (json.habilitarRendicion == 'true') {
		   		  		document.getElementById("rendir").disabled = false;
					}else{
						document.getElementById("rendir").disabled = true;
					};
	   			}
	   			agregarOrdenFecha(idTablaPreimpreso);
				
	   			dataTable.fnSort( [ [2,'desc'] ] );
				check();
				
   				ocultarBloqueEspera();  			 	
			});		    
		}
		
		function buscarTalonarios() {
			$('#bloqueEspera').trigger('click');
			$('#formBusqueda')[0].submit();
		}
		
		function rechazarTalonario() {
			$('#bloqueEspera').trigger('click');
			$('#operation').val('<%=Constantes.RECHAZAR_TALONARIO%>');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/rechazar-talonario";
			$('#formTalonarioTabla')[0].submit();
		}
		
		function habilitarRechazo(){
			if (primeraVezBuscarRecibos==false) {
				$('.error').css('display', 'none');
			}
			$("#observaciones").val("");
			
			document.getElementById("observaciones").disabled = false;
			document.getElementById("rechazar").disabled = false;
		}
		
		function deshabilitarRechazo(){
			if (primeraVezBuscarRecibos==false) {
				$('.error').css('display', 'none');
			}
			$("#observaciones").val("");
			
			document.getElementById("observaciones").disabled = true;
			document.getElementById("rechazar").disabled = true;
		}
		
		function modificarTalonario(idTalonario) {
			$('#bloqueEspera').trigger('click');
			$('#idTalonario').val(idTalonario);
			$('#timeStampAux').val($('#timeStampAux').val());
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/talonario-modificacion";
			$('#formTalonarioTabla')[0].submit();	
		}
		
 		function modificarRecibo(idRecibo) {
 			$('#bloqueEspera').trigger('click');
 			$('#id').val(idRecibo);
 			$('#formRecibo')[0].action="${pageContext.request.contextPath}/talonario-modificacion-recibo";
 			$('#formRecibo')[0].submit();	
 		}
		
 		function anular(){
 			var mensaje = '<spring:message code="recibo.mensaje.anular.ok"/>';
 			bootbox.confirm(mensaje, function(result) {
	    		if (result) {
 		 			$('#bloqueEspera').trigger('click');
		 			listaIdRecibos= "";
		 			
		 			var sData = $(".anulable:checked", oTable.fnGetNodes()).serialize();
					sData = sData.replace(/opcion=/gi, "");
					sData = sData.replace(/&/gi, "|");
					listaIdRecibos = sData;
		 			
					$('#recibosAAnularSelected')[0].value=listaIdRecibos;
					$('#idTalonarioSelected')[0].value=$(':checked[name=radioIdTalonario]').val();
					$('#timeStamp').val($('#timeStampAux').val());
		 			$('#formRecibo')[0].action="${pageContext.request.contextPath}/anular-recibo";
					$('#formRecibo')[0].submit();
	    		}
 			});
 		}

		function rendirTalonario() {
			$('#bloqueEspera').trigger('click');
			$('#operation').val('<%=Constantes.RENDIR_TALONARIO%>');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/rendir-talonario";  
			$('#formTalonarioTabla')[0].submit();
		};
		
		function bloqueUsuario() {
			$(".bloqueUsuario").closest("img").attr("src","${pageContext.request.contextPath}/img/default2.png");
		};
		
	</script>
    
	</body>
</html>