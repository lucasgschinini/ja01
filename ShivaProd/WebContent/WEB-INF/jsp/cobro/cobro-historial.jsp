<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><spring:message code="cobro.historial.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/shiva-cobro-historial.js"></script>


<style type="text/css">
.customAll {
	float: left;
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
								<spring:message code="cobro.historial.titulo" />
							</div>
							<div class="pagos_anticipos">
								<form:form id="formHist" commandName="listaHistorialCobroDto">
									<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}"/>
									<input type="hidden" id="idDescobro" name="idDescobro" value="${idDescobro}"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="goBack" name="goBack" />
									<input type="hidden" id="idCobroPadre" name="idCobroPadre" value="${idCobroPadre}"/>
									<input type="hidden" id="opcion" name="opcion" value="${opcion}"/>
									<input type="hidden" id="nombreArchivo" name="nombreArchivo" value="${nombreArchivo}"/>
									<input type="hidden" id="formOrigen" name="formOrigen" value="${formOrigen}"/>
									<input type="hidden" id="idLegajo" name="idLegajo" value="${idLegajo}"/>
									<input type="hidden" id="idLeg" name="idLeg" value="${idLegajo}"/>
									<input type="hidden" id="solapa" name="solapa" value="${solapa}"/>
									<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" value="${idOperacionRelacionada}" />
									<!-- FILA 1 -->
									<div class="row">

										<!-- ID Operacion Cobro -->
										<div class="span3" align="left">
											<label class="control-label" for="idOperacionCobro"><spring:message code="cobro.historial.resultado.idOperacionCobro" /></label>
											<div class="controls ">
											<input id="idOperacionCobro" name="idOperacionCobro" type="text" class="input" value="${idOperacionFormateado}" readonly />
											</div>
										</div>
										<!-- fin ID Operacion Cobro -->
									</div>
									<!-- FIN FILA 1 -->

									<!-- FILA 2 -->
									<div class="row">

										<!-- Titulo grilla-->
										<div class="span3">
											<p style="padding: 5px 0px;">
											<strong><spring:message code="cobro.historial.titulo.grilla" /></strong>
											</p>
										</div>
										<!-- FIN Titulo grilla -->
									</div>
									<!-- FILA 2 -->


									<!-- TABLA RESULTADOS BUSQUEDA -->
									
									<div class="row">
										<div class="span9">
											<table id="cobroHistorial" class="tablaResultado">
												<thead>
													<tr>
														<th><spring:message
																code="cobro.historial.resultado.idOperacionCobro" /></th>
														<th><spring:message
																code="cobro.historial.resultado.estado" /></th>
														<th><spring:message
																code="cobro.historial.resultado.subestado" /></th>
														<th><spring:message
																code="cobro.historial.resultado.nombreApellidoUsuarioActualizoEstado" /></th>
														<th><spring:message
																code="cobro.historial.resultado.diaHoraActualizacionEstado" /></th>
														<th><spring:message
																code="cobro.historial.resultado.observaciones" /></th>
														<th><spring:message
																code="cobro.historial.resultado.mensajeError" /></th>
														<th><spring:message
																code="cobro.historial.resultado.numeroTransaccion" /></th>
														<th><spring:message
																code="cobro.historial.resultado.numeroDocumentoDebito" /></th>
														<th><spring:message
																code="cobro.historial.resultado.importe" /></th>
														<th><spring:message
																code="cobro.historial.resultado.fechaCobro" /></th>
														<th><spring:message
																code="cobro.historial.resultado.refMedioPago" /></th>
														<th><spring:message
																code="cobro.historial.resultado.acuerdoFactDestinoCargos" /></th>
														<th class="date" hidden></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listaHistorialCobroDto}" var="hist">
														<tr>
															<td>${hist.idOperacionFormateado}</td>
															<td>${hist.estadoModificacion}</td>
															<td>${hist.marcaModificacion}</td>
															<td>${hist.nombreCompletoUsuarioMod}</td>
															<td>${hist.fechaModificacionFormateada}</td>
															<td>${hist.observaciones}</td>
															<td>${hist.mensajeError}</td>
															<td>${hist.numeroTransaccionFicticio}</td>
															<td>${hist.numeroDocumentoDebito}</td>
															<td>${hist.importe}</td>
															<td>${hist.fechaCobro}</td>
															<td>${hist.refMedioPago}</td>
															<td>${hist.acuerdoTrasladoCargo}</td>
															<td hidden>${hist.fechaModificacion}</td>
															
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<!-- FIN TABLA RESULTADOS BUSQUEDA -->

									<!-- Boton volver -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnVolver"
												name="btnVolver" type="button" onclick="javascript:volverBusqueda();"><i class="icon-white icon-repeat"></i>
												<spring:message code="accion.goBack" />
											</button>
										</div>
									</div>

								</form:form>
							</div>
							<!-- fin de pagos_anticipos -->

						</div>
						<!-- fin de payments -->
					</div>
					<!-- fin de inside -->
				</div>
				<!-- fin de content -->
			</div>
			<!-- fin de wrap -->
		</div>
		<!-- fin de main -->

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- fin de container -->
</body>
</html>

<script>
$(document).ready(function() {
	
	$('#bloqueEspera').trigger('click');
	
});
</script>