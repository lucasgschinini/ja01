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
<script src="${pageContext.request.contextPath}/js/descobro-historial.js"></script>


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
								<spring:message code="descobro.historial.titulo" />
							</div>

							<div class="pagos_anticipos">
								<form:form id="formHist" commandName="listaHistorialCobroDto">
									<input type="hidden" id="idDescobro" name="idDescobro" value="${idDescobro}"/>
									<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}"/>
									<input type="hidden" id="idOperacionDescobro" name="idOperacionDescobro" value="${idOperacionDescobro}"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="goBack" name="goBack" />
									<input type="hidden" id="volverAPantalla" name="volverAPantalla" value="${vuelvoABandeja}"/>
									
									<!-- FILA 1 -->
									<div class="row">

										<!-- ID Reversion -->
										<div class="span3" align="left">
											<label class="control-label" for="idReversion"><spring:message code="descobro.historial.resultado.idReversion" /></label>
											<div class="controls ">
											<input id="idReversion" name="idReversion" type="text" class="input" value="${idOperacionDescobro}" readonly />
											</div>
										</div>
										<!-- fin ID Reversion -->
									</div>
									<!-- FIN FILA 1 -->

									<!-- FILA 2 -->
									<div class="row">

										<!-- Titulo grilla-->
										<div class="span3">
											<p style="padding: 5px 0px;">
											<strong><spring:message code="descobro.historial.titulo.grilla" /></strong>
											</p>
										</div>
										<!-- FIN Titulo grilla -->
									</div>
									<!-- FILA 2 -->


									<!-- TABLA RESULTADOS BUSQUEDA -->
									
									<div class="row">
										<div class="span9">
											<table id="descobroHistorial" class="tablaResultado">
												<thead>
													<tr>
														<th><spring:message
																code="descobro.historial.resultado.idOperacionReversion" /></th>
														<th><spring:message
																code="descobro.historial.resultado.estado" /></th>
														<th><spring:message
																code="descobro.historial.resultado.subestado" /></th>
														<th><spring:message
																code="descobro.historial.resultado.nombreApellidoUsuarioActualizoEstado" /></th>
														<th class="date"><spring:message
																code="descobro.historial.resultado.diaHoraActualizacionEstado" /></th>
														<th><spring:message
																code="descobro.historial.resultado.mensajeError" /></th>
														<th><spring:message
																code="descobro.historial.resultado.numeroTransaccion" /></th>
														<th><spring:message
																code="descobro.historial.resultado.numeroDocumentoDebito" /></th>
														<th><spring:message
																code="descobro.historial.resultado.importe" /></th>
														<th><spring:message
																code="descobro.historial.resultado.fechaCobro" /></th>
														<th><spring:message
																code="descobro.historial.resultado.refMedioPago" /></th>
														<th><spring:message
																code="descobro.historial.resultado.acuerdoFactDestinoCargos" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listaHistorialDescobroDto}" var="hist">
														<tr>
															<td>${hist.idOperacionFormateado}</td>
															<td>${hist.estado}</td>
															<td>${hist.subEstado}</td>
															<td>${hist.usuarioModificacion}</td>
															<td>${hist.fechaModificacion}</td>
															<td>${hist.mensajeError}</td>
															<td>${hist.nroTransaccionFicticio}</td>
															<td>${hist.nroDocumentoDebito}</td>
															<td>${hist.importeACobrar}</td>
															<td>${hist.fechaCobro}</td>
															<td>${hist.refMedioPago}</td>
															<td>${hist.acuerdoFactDestinoCargo}</td>
															
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