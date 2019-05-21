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
	<title><spring:message code="recibo.revision.titulo"/></title>
   
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
						<div class="title"><spring:message code="recibo.revision.titulo"/></div>
							<form:form id="formTalonarioTabla" commandName="talonarioDto">
							<div class="pagos_anticipos">
								<input type="hidden" id="timeStampAux" name="timeStampAux" value="${timeStampAux}"/>
								<input type="hidden" id="operation" name="operation"/>
								<input type="hidden" id="idTalonario" name="idTalonario">
								<input type="hidden" id="empresaDto" name="empresa">
								<input type="hidden" id="rangoDesdeDto" name="rangoDesde">
								<input type="hidden" id="pantallaDestino" name="pantallaDestino" value="<%=Constantes.DESTINO_BANDEJA_ENTRADA%>"/>
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
							</div>
							</form:form>
							
							
							
							<form:form id="formRecibo" commandName="reciboDto">
								<input type="hidden" id="id" name="id" />
								<input type="hidden" id="timeStamp" name="timeStamp"/>
								<input type="hidden" id="recibosAAnularSelected" name="recibosAAnularSelected"/>
								<input type="hidden" id="idTalonarioSelected" name="idTalonarioSelected"  value="${talonarioDto.idTalonario}"/>
								<input type="hidden" id="volverPantallaRendicion" name="volverPantallaRendicion" value="true"/>
								
								<div class="pagos_anticipos">
									<p><strong><spring:message code="recibo.recibosDelTalonario"/></strong></p>
									<div class="row">		
										<div class="span9">
		  								
										<table id="resultadoBusquedaReciboPreimpreso" class="tablaResultado">
											<thead>
												<tr>
													<th style="padding: 3px 10px; text-align: center;"></th>
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
							<c:if test="${sessionScope.loginUser.puedeRendirTalonario}">
								<div class="row">
									<div align="center">
										<button class="btn btn-primary btn-small" id="rendir" name="rendir" type="button" disabled onclick="rendirTalonario()"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="recibo.botonRendir"/></button>
										<button class="btn btn-primary btn-small" id="cancelar" name="cancelar" type="button" onclick="regresarABandejaEntrada();"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="boleta.boton.cancelar"/></button>
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
		
		var idTablaPreimpreso = 'resultadoBusquedaReciboPreimpreso';
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";

		// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
		// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
		// En IE8 no hace falta llamar a fnCellRender
		 var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
			return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0], [9, 11,14,16]);
		};	
		// Tratamiento de scrolling para las tablas de la pagina.
		window.onload = function() {
			var idTablaTalonarios = 'resultadoBusquedaTalonarios';
			inicializarTablaConExportacionAPdfyXlsOcultarSearchFiltroInfo(idTablaTalonarios, (window.DOMParser) ? fnCellRender : null);
			agregarOrdenFecha(idTablaTalonarios);
			$('#' + idTablaTalonarios).dataTable().fnSort( [ [8,'desc'] ] );
			
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
			
			
			var url = '${pageContext.request.contextPath}/consultarRecibosTalonario?method=load&ajax=true&nocache='+Math.random();
			var param = "&talonarioId="+idTalonario+"&estado="+$('#estadoTalonario_'+idTalonario).val();
			$('#resultadoBusquedaReciboPreimpreso').dataTable().fnDestroy();
			inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos('resultadoBusquedaReciboPreimpreso', claseRegistros, idSeleccionarTodos,
					undefined);
			
			$.ajaxSetup({ scriptCharset: "ISO-8859-1" , contentType: "application/json; charset=ISO-8859-1" });
		    $.getJSON(url+param, function(json) {
			     
	   			var dataTable = $('#resultadoBusquedaReciboPreimpreso').dataTable();
	   			dataTable.fnClearTable(this);
	   		   	if (json.aaData.length) {
	   		   		dataTable.fnAddData(json.aaData);
	   		   	}
	   		  	dataTable.fnDraw();
	   			 if ('${sessionScope.loginUser.puedeRendirTalonario}'=='true') {
		   		  	if ($('#habilitarRendicion').val() == 'true') {
		   		  		document.getElementById("rendir").disabled = false;
					}else{
						document.getElementById("rendir").disabled = true;
					};
	   			 }
	   			agregarOrdenFecha('resultadoBusquedaReciboPreimpreso');
	   			dataTable.fnSort( [ [2,'desc'] ] );
	   			
	   			// Oculto los checkboxs de la tabla de recibos
	   			$(".opcion").hide();
	   		 	
	   		 	ocultarBloqueEspera();      		
	   		 	
		   		 $("#seleccionarTodos").click(function () {
			    	$('.opcion:visible').prop('checked', $(this).prop('checked'));
			    });
    		});        
		}
				
 		function modificarRecibo(idRecibo) {
 			$('#bloqueEspera').trigger('click');
 			$('#id').val(idRecibo);
 			$('#formRecibo')[0].action="${pageContext.request.contextPath}/talonario-modificacion-recibo";
 			$('#formRecibo')[0].submit();	
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
		
		function regresarABandejaEntrada() {
			$('#bloqueEspera').trigger('click');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/regresar-bandeja-de-entrada";
			$('#formTalonarioTabla')[0].submit();	
		}
		
	</script>
    
	</body>
</html>


