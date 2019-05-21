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

<title><spring:message code="operacion.masiva.historial.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/shiva-operacion-masiva-historial.js"></script>
<script>
</script>

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
								<spring:message code="operacion.masiva.historial.titulo" />
							</div>

							<div class="pagos_anticipos">
								<form:form id="formHist" commandName="listaHistorialOperacionMasivaDto">
									<input type="hidden" id="idOperacionMasiva" name=""idOperacionMasiva"" value="${idOperacionMasiva}"/>
									<input type="hidden" id="nombreArchivo" name="nombreArchivo" value="${nombreArchivo}" " />
									<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}" />
									<input type="hidden" id="volver" name="volver" value="/operacion-masiva-historial"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="goBack" name="goBack" />
									<input type="hidden" id="opcion" name="opcion" value="D"/>
									<!-- FILA 1 -->
									<div class="row">

										<!-- ID Operacion Masiva -->
										<div class="span3" align="left">
											<label class="control-label" for="idOperacionMasiva"><spring:message code="operacion.masiva.historial.resultado.idOperacionMasiva" /></label>
											<div class="controls ">
											<input id="idOperacionMasiva" name="idOperacionMasiva" type="text" class="input" value="${idOperacionMasiva}" readonly />
											</div>
										</div>
										<!-- fin ID Operacion Cobro -->
									</div>
									<!-- FIN FILA 1 -->
									<!-- FILA 2 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="tipoOperacion">Tipo de Operacion</label>
											<div class="controls">
												<input id="tipoOperacion" name="tipoOperacion" type="text" class="input" readonly value="${tipoOperacion}" />
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="nombreArchivo">Nombre del Archivo</label>
											<div class="controls">
												<input id="nombreArchivo" name="nombreArchivo" type="text" class="input" readonly value="${nombreArchivo}" />
											</div>
										</div>
									</div>
									
									<!-- FILA 2 -->
									<div class="row">

										<!-- Titulo grilla-->
										<div class="span9">
											<p style="padding: 5px 0px;">
											<strong><spring:message code="operacion.masiva.historial.titulo.grilla" /></strong>
											</p>
										</div>
										<!-- FIN Titulo grilla -->
									</div>
									

									<!-- TABLA RESULTADOS BUSQUEDA -->
									
									<div class="row">
										<div class="span9">
											<table id="OperacionMasivaHistorial" class="tablaResultado">
												<thead>
													<tr>
														<th><spring:message
																code="operacion.masiva.historial.resultado.idOperacionMasiva" /></th>
														<th><spring:message
																code="operacion.masiva.historial.resultado.estado" /></th>
														<th><spring:message
																code="operacion.masiva.historial.resultado.nombreApellidoUsuarioActualizoEstado" /></th>
														<th class="datetimeSeconds" ><spring:message
																code="operacion.masiva.historial.resultado.diaHoraActualizacionEstado" /></th>
														<th><spring:message
																code="operacion.masiva.historial.resultado.observaciones" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listaHistorialOperacionMasivaDto}" var="hist">
														<tr>
															<td>${hist.idOperacionMasiva}</td>
															<td>${hist.estado}</td>
															<td>${hist.nombreCompletoUsuarioMod}</td>
															<td>${hist.fechaModificacion}</td>
															<td>${hist.observaciones}</td>
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