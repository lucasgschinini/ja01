<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>

<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<meta name="Gestionar Resultados Conciliación" content="Modelo">

<style>
.diferencia {
	background-color: #FFFF99;
}

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

<body>

	<div id="container">

		<jsp:include page="../template/cabecera.jsp"></jsp:include>

		<div id="main">

			<div class="wrap">

				<jsp:include page="../template/menu.jsp"></jsp:include>

				<div id="content">

					<div id="inside">

						<div id="payments" class="box">
							<div class="title">
								<spring:message code="conciliacion.gestionar.titulo.pagina" />
							</div>

							<div class="pagos_anticipos">
								<p>
									<strong><spring:message
											code="conciliacion.conciliacionSugerida" /></strong>
								</p>

								<form:form id="formConciliacionSugerida"
									commandName="registroDto">
									<form:hidden path="idRegistroSelected" id="idRegistroSelected" />
									<form:hidden path="registrosAVCAAnularSelected" />
									<form:hidden path="observacionAnulacion"
										id="observacionAnulacion" />
									<form:hidden path="conciliacionesSelected"
										id="conciliacionesSelected" />
									<form:hidden path="operation" />

									<div class="row">
										<div class="span9">
											<table id="resultadoBusquedaConciliacionSugerida"
												class="tablaResultado">
												<thead>
													<tr>
														<th><spring:message code="conciliacion.espacio" /></th>
														<th colspan="5"><spring:message
																code="conciliacion.registrosAvcAConciliar" /></th>
														<th style="background-color: #dcdbdb;"></th>
														<th colspan="5"><spring:message
																code="conciliacion.boletasDeposito" /></th>
														<th style="background-color: #dcdbdb;"></th>
														<th><spring:message code="conciliacion.espacio" /></th>
													</tr>
													<tr>
														<th style="padding: 3px 10px; text-align: center;"><input
															type="checkbox" id="seleccionarTodos" class="seleccionarTodos"></th>
														<th><spring:message code="conciliacion.nroAcuerdo" /></th>
														<th><spring:message code="conciliacion.nroCliente" /></th>
														<th><spring:message code="conciliacion.nroBoleta" /></th>
														<th><spring:message code="conciliacion.nroCheque" /></th>
														<th><spring:message code="conciliacion.importe" /></th>
														<th style="background-color: #dcdbdb"></th>
														<th><spring:message code="conciliacion.nroAcuerdo" /></th>
														<th><spring:message code="conciliacion.nroCliente" /></th>
														<th><spring:message code="conciliacion.nroBoleta" /></th>
														<th><spring:message code="conciliacion.nroCheque" /></th>
														<th><spring:message code="conciliacion.importe" /></th>
														<th style="background-color: #dcdbdb;"></th>
														<th><spring:message code="conciliacion.nombreArchivo" /></th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${listaConciliacionSugeridaDto}"
														var="conciliacion">
														<tr>
															<td><input type="checkbox" class="opcion"
																id="opcion_${conciliacion.idCompuesto}" name="opcion"
																value="${conciliacion.idCompuesto}" /></td>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroAcuerdo}">
																	<td class="diferencia">${conciliacion.registroNroAcuerdo}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.registroNroAcuerdo}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroCliente}">
																	<td class="diferencia">${conciliacion.registroNroCliente}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.registroNroCliente}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroBoleta}">
																	<td class="diferencia">${conciliacion.registroNroBoleta}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.registroNroBoleta}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroCheque}">
																	<td class="diferencia">${conciliacion.registroNroCheque}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.registroNroCheque}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaImporte}">
																	<td class="diferencia">${conciliacion.registroImporte}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.registroImporte}</td>
																</c:otherwise>
															</c:choose>


															<td style="background-color: #dcdbdb"></td>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroAcuerdo}">
																	<td class="diferencia">${conciliacion.boletaNroAcuerdo}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.boletaNroAcuerdo}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroCliente}">
																	<td class="diferencia">${conciliacion.boletaNroCliente}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.boletaNroCliente}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroBoleta}">
																	<td class="diferencia">${conciliacion.boletaNroBoleta}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.boletaNroBoleta}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaNroCheque}">
																	<td class="diferencia">${conciliacion.boletaNroCheque}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.boletaNroCheque}</td>
																</c:otherwise>
															</c:choose>
															<c:choose>
																<c:when test="${conciliacion.diferenciaImporte}">
																	<td class="diferencia">${conciliacion.boletaImporte}</td>
																</c:when>
																<c:otherwise>
																	<td>${conciliacion.boletaImporte}</td>
																</c:otherwise>
															</c:choose>
															<td style="background-color: #dcdbdb"></td>
															<td>${conciliacion.nombreArchivo}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>

									<div class="row"
										style="margin-top: 20px; margin-bottom: 30px; margin-right: 5px">
										<div align="right">
											<button class="btn btn-primary btn-small"
												id="btnConfirmarConciliar" name="btnConfirmarConciliar"
												type="button">
												<i class="icon-white icon-ok"></i>
												<spring:message
													code="conciliacion.confirmarConciliacionSugerida" />
											</button>
											<button class="btn btn-primary btn-small"
												id="btnDeshacerConciliar" name="btnDeshacerConciliar"
												type="button">
												<i class="icon-white icon-remove"></i>
												<spring:message
													code="conciliacion.deshacerConciliacionSugerida" />
											</button>
										</div>
									</div>
									<div class="container form-signin">
										<div class="span9">
											<c:if test="${ not empty duplicidadError}">
												<div class="alert alert-error">
													<a class="close" data-dismiss="alert">×</a><strong><spring:message
															code="error.error" /></strong><br />${duplicidadError}
												</div>
											</c:if>
										</div>
									</div>
									<%-- 									</form:form> --%>


									<!-- 								<div class="pagos_anticipos"> -->
									<p>
										<strong><spring:message
												code="conciliacion.registrosAvcSinConciliar" /></strong>
									</p>

									<%-- 									<form:form id="formRegistrosSinConciliar"> --%>
									<div class="row">
										<div class="span9">
											<table id="tablaBusquedaRegistrosSinConciliar"
												class="tablaResultado">
												<thead>
													<tr>
														<th><input type="checkbox"
															id="sinConciliarSeleccionarTodos"
															name="sinConciliarSeleccionarTodos" /></th>
														<th></th>
														<th><spring:message code="conciliacion.nroCliente" /></th>
														<th><spring:message code="conciliacion.razon.social" /></th>
														<th><spring:message code="conciliacion.fecha.alta" /></th>
														<th><spring:message
																code="conciliacion.fecha.deposito" /></th>
														<th><spring:message code="conciliacion.importe" /></th>
														<th><spring:message
																code="conciliacion.analista.team.comercial" /></th>
														<th><spring:message code="conciliacion.tipoRegistro" /></th>
														<th><spring:message
																code="conciliacion.referenciaValor" /></th>
														<th><spring:message code="conciliacion.nroBoleta" /></th>
														<th><spring:message code="conciliacion.cuit" /></th>
														<th><spring:message
																code="conciliacion.codigoOrganismo" /></th>
														<th><spring:message code="conciliacion.banco.origen" /></th>
														<th><spring:message
																code="conciliacion.fechaVencimiento" /></th>
														<th><spring:message
																code="conciliacion.errorAltaInterdeposito" /></th>
														<th><spring:message code="conciliacion.fechaError" /></th>
														<th><spring:message code="conciliacion.observaciones" /></th>
														<th><spring:message code="conciliacion.nroAcuerdo" /></th>
														<th><spring:message code="conciliacion.nombreArchivo" /></th>
													</tr>
												</thead>

												<tfoot></tfoot>

												<tbody>
													<c:forEach items="${listaRegistrosSinConciliar}" var="reg">
														<tr>
															<td><c:if
																	test="${(!reg.esEstadoPendConfirmar)}">
																<input id="sinConciliarOpcion_${reg.idRegistro}"
																class="sinConciliarOpcion" type="checkbox"
																value="${reg.idRegistro}" name="sinConciliarOpcion" />&nbsp;
															</c:if></td>
															<td><c:if test="${(((reg.tipoDto)=='TRANSFERENCIA')
															&&(!reg.esEstadoPendConfirmar))}">
																	<button type="button" id="btnCrearBoleta"
																		class="btn btn-xs btn-link"
																		title=<spring:message code="conciliacion.editar"/>
																		onclick="javascript:irAEditarRegistroAVCDetalle(${reg.idRegistro});">
																		<i class="icon-edit bigger-120"></i>
																	</button>
																</c:if></td>
															<td>${reg.codigoClienteFormateado}</td>
															<td>${reg.razonSocial}</td>
															<td>${reg.fechaAltaFormateadaSoloDia}</td>
															<td>${reg.fechaDepositoFormateada}</td>
															<td>${reg.importeFormateado}</td>
															<td style="text-align: left"><c:choose>
																	<c:when test="${reg.usuarioTeamComercial eq '-'}">
																		<p style="text-align: center">-</p>
																	</c:when>
																	<c:otherwise>
																		<div style="width: 205px;">
																			<img alt="Usuario"
																				src="${reg.urlFotoUsuario(reg.usuarioTeamComercial)}"
																				style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;"
																				onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																			${reg.nombreUsuarioTeamComercial} <br> <a
																				href="sip:${reg.mailUsuarioTeamComercial}"
																				title="Chat"><i class="icon-comment"
																				style="margin-top: 3px"></i></a> <a
																				href="mailto:${reg.mailUsuarioTeamComercial}"
																				title="Email"><i class="icon-envelope"
																				style="margin-left: 3px; margin-top: 2px"></i></a>
																		</div>
																	</c:otherwise>
																</c:choose></td>
															<td>${reg.tipoValorFormateado}</td>
															<td>${reg.referenciaValorFormateado}</td>
															<td>${reg.nroBoletaFormateado}</td>
															<td>${reg.cuitFormateado}</td>
															<td>${reg.codigoOrganismo}</td>
															<td>${reg.bancoOrigenFormateado}</td>
															<td>${reg.fechaVencimientoFormateado}</td>
															<td>${reg.errorAltaInterdeposito}</td>
															<td>${reg.fechaErrorFormateado}</td>
															<td title="${reg.observacionesFormateado}"><c:choose>
																	<c:when test="${(reg.tipoDto)=='TRANSFERENCIA'}">
																	${reg.observacionesFormateado}
																</c:when>
																	<c:otherwise>-</c:otherwise>
																</c:choose></td>
															<td>${reg.acuerdo}</td>
															<td>${reg.nombreArchivo}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observaciones"> <label
												class="control-label" for="mostrarAsterisco"><span
													class="rojo">* </span> <spring:message
														code="conciliacion.observaciones" /></label> <textarea
													class="field span9" id="observaciones" name="observaciones"
													maxlength="250"></textarea>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="observacionAnulacion" cssClass="error" />
										</div>
									</div>

									<div class="row rowError">
										<div class="span3">
											<span id="validacionSinObservacion" class="error"
												style="display: none;"><spring:message
													code="conciliacion.anular.sinObservacion" /></span>
										</div>
									</div>

									<div class="row rowError">
										<div class="span3">
											<span id="validacionSinRegistros" class="error"
												style="display: none;"><spring:message
													code="conciliacion.anular.sinRegistros" /></span>
										</div>
									</div>

									<div class="row rowError">
										<div class="span3">
											<form:errors path="registrosAVCAAnularSelected"
												cssClass="error" />
										</div>
									</div>

									<div class="row"
										style="margin-top: 20px; margin-bottom: 30px; margin-right: 5px">
										<div align="right">
											<button type="button" class="btn btn-primary btn-small"
												id="btnVolverConciliacionResultado"
												name="btnVolverConciliacionResultado">
												<i class="icon-white icon-repeat"></i>
												<spring:message code="conciliacion.botonVolver" />
											</button>

											<button type="button" class="btn btn-primary btn-small"
												id="btnAnular" name="btnAnular">
												<i class="icon-white icon-remove"></i>
												<spring:message code="conciliacion.botonAnular" />
											</button>
										</div>
									</div>

								</form:form>
							</div>

						</div>
					</div>
				</div>
				<!-- end #content -->
			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->
		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	</div>
	<!-- end #container -->
	<div id="popUpObservacion"
		title=<spring:message code="conciliacion.observaciones"/>
		style="display: none">
		<div class="controls">
			<input id="observacion" name="observacion" type="text"
				class="input" style="height: 100px; width: 260px">
		</div>
	</div>

<script>

var idTablaConciliacionSugerida = "resultadoBusquedaConciliacionSugerida";
var claseRegistrosConciliacionSugerida = "opcion";
var idSeleccionarTodosConciliacionSugerida = "seleccionarTodos";

var idTablaSinConciliar = "tablaBusquedaRegistrosSinConciliar";
var claseRegistrosSinConciliar = "sinConciliarOpcion";
var idSeleccionarTodosSinConciliar = "sinConciliarSeleccionarTodos";

var habilitarBotonAnular = function() {
	var registrosTildados = $("." + claseRegistrosSinConciliar + ':checked', $("#" + idTablaSinConciliar).dataTable().fnGetNodes()).length > 0;
	$('#btnAnular').attr('disabled', !registrosTildados);
	
	if(registrosTildados){		
		$("#mostrarAsterisco").css('display', 'inline');
	}else{
		$("#mostrarAsterisco").css('display', 'none');
	}
}

var habilitarBotonesConciliacionSugerida = function() {
	var registrosTildados = $("." + claseRegistrosConciliacionSugerida + ':checked', $("#" + idTablaConciliacionSugerida).dataTable().fnGetNodes()).length > 0;
    $('#btnConfirmarConciliar').attr('disabled', true);
	$('#btnDeshacerConciliar').attr('disabled', true);
	if (registrosTildados){
		$('#btnConfirmarConciliar').attr('disabled', false);
		$('#btnDeshacerConciliar').attr('disabled', false);
	}
}

function irAEditarRegistroAVCDetalle(idRegistro) {
	$('#bloqueEspera').trigger('click');
	
	$("#idRegistroSelected").val(idRegistro);
	$('#formConciliacionSugerida')[0].action="${pageContext.request.contextPath}/conciliacion-editar-registro-avc-detalle";
	$('#formConciliacionSugerida')[0].submit();
};
	
window.onload = function() {

	//Se inicializan las tablas
	inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTablaConciliacionSugerida, claseRegistrosConciliacionSugerida
			, idSeleccionarTodosConciliacionSugerida, habilitarBotonesConciliacionSugerida);
	inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTablaSinConciliar, claseRegistrosSinConciliar
			, idSeleccionarTodosSinConciliar, habilitarBotonAnular);
	
	//Deshabilito los botones
	$('#btnConfirmarConciliar').attr('disabled', true);
	$('#btnDeshacerConciliar').attr('disabled', true);
	$('#btnAnular').attr('disabled', true);

	// add multiple select / deselect functionality
    $("#" + idSeleccionarTodosConciliacionSugerida).click(function () {    	
    	$("." + claseRegistrosConciliacionSugerida).prop('checked', $(this).prop('checked'));
    	habilitarBotonesConciliacionSugerida();
    });
	
	$("#" + idSeleccionarTodosSinConciliar).click(function () {
		$("." + claseRegistrosSinConciliar).prop('checked', $(this).prop('checked'));
		habilitarBotonAnular();
	});

	$('#btnAnular').click(function() {
		$("#validacionSinRegistros").css('display', 'none');
		$("#validacionSinObservacion").css('display', 'none');
		var mensaje;
		var listaParaAnular = $("." + claseRegistrosSinConciliar + ':checked', $("#" + idTablaSinConciliar).dataTable().fnGetNodes()).serialize().replace(/sinConciliarOpcion=/gi, "").replace(/&/gi, ",");
		if ($.trim(listaParaAnular).length > 0 && $.trim($("#observaciones").val()).length > 0) {
			mensaje = '<spring:message code="conciliacion.mensaje.anular.ok"/>';
			bootbox.confirm(mensaje, function(result) {
				if (result) {
		    		$('#bloqueEspera').trigger('click');
		    		$('#registrosAVCAAnularSelected')[0].value=listaParaAnular;
		    		$("#observacionAnulacion").val($("#observaciones").val());
		    		$('#operation').val("<%=Constantes.ANULAR_REGISTRO_AVC%>");
		    		$('#formConciliacionSugerida')[0].action="${pageContext.request.contextPath}/anular-registros-avc";
		    		$('#formConciliacionSugerida')[0].submit();
		    	};
			});
		} else {
			if ($.trim(listaParaAnular).length == 0){
				$("#validacionSinRegistros").css('display', 'inline');	
			}
			
			if ($.trim($("#observaciones").val()).length == 0){
				$("#validacionSinObservacion").css('display', 'inline');	
			}
			
		}
	});	
			
	$('#btnConfirmarConciliar').click(function() {
		$('#bloqueEspera').trigger('click');
		listaConciliaciones = "";
		
		var sData = $("." + claseRegistrosConciliacionSugerida + ":checked", $("#" + idTablaConciliacionSugerida).dataTable().fnGetNodes()).serialize();
		sData = sData.replace(/opcion=/gi, "");
		sData = sData.replace(/&/gi, ",");
		listaConciliaciones = sData;
		
		var listaCompleta="";
		var lstConciliaciones = listaConciliaciones.split(",");
		for(var num = 0; num < lstConciliaciones.length; num++) {
			var idCompuesto = lstConciliaciones[num];
			listaCompleta += idCompuesto+",";	
		}
 				
		$("#conciliacionesSelected").val(listaCompleta);
		$('#formConciliacionSugerida')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-conciliacion-sugerida";
		$('#formConciliacionSugerida')[0].submit();	
   						
	});
			
	$('#btnDeshacerConciliar').click(function() {
		$('#bloqueEspera').trigger('click');
		listaConciliaciones = "";
		var sData = $("." + claseRegistrosConciliacionSugerida + ":checked", $("#" + idTablaConciliacionSugerida).dataTable().fnGetNodes()).serialize();
		sData = sData.replace(/opcion=/gi, "");
		sData = sData.replace(/&/gi, ",");
		listaConciliaciones = sData;
		
		var listaCompleta="";
		var lstConciliaciones = listaConciliaciones.split(",");
		for(var num = 0; num < lstConciliaciones.length; num++) {
			var idCompuesto = lstConciliaciones[num];
			listaCompleta += idCompuesto+",";	
		}
		
		$("#conciliacionesSelected").val(listaCompleta);
		$('#formConciliacionSugerida')[0].action="${pageContext.request.contextPath}/conciliacion-deshacer-conciliacion-sugerida";
		$('#formConciliacionSugerida')[0].submit();
   						
	});
			
	$('#btnVolverConciliacionResultado').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#formConciliacionSugerida')[0].action="${pageContext.request.contextPath}/conciliacion-resultado-conciliacion";
  		$('#formConciliacionSugerida')[0].submit();				
	});

    $('#btnVolverConciliacionResultado').click(function() {
		$('#bloqueEspera').trigger('click');
	});
};
</script>
</body>
</html>
