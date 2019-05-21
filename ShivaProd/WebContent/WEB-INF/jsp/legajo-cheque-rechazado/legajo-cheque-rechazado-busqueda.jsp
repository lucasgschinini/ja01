<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><spring:message code="legajo.cheques.rechazados.busqueda.titulo.main" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/legajo-cheque-rechazado.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">


<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script	src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script	src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<%-- <script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheques-rechazados-cheque.js"></script> --%>

<script	src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<script	src="${pageContext.request.contextPath}/js/custom-paginacion-datatables.js"></script>
<script	src="${pageContext.request.contextPath}/js/funciones-datatables-shiva.js"></script>
<script	src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-busqueda.js"></script>



<script type="text/javascript">
		var bancoDescripcion = ${bancoDescripcion};
		var legajoChequeRechazadoFiltro = ${legajoChequeRechazadoFiltro};
</script>
</head>
<!-- TODO !!! Maxi u578936  testeo de scroll en custon  combo
<style type="text/css">
.ui-autocomplete {
	overflow-x: hidden;
	overflow-y: auto;
	max-height: 305px;
	padding-right: 20px;
}
</style>
 -->
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
								<spring:message
									code="legajo.cheques.rechazados.busqueda.titulo.main" />
							</div>

							<div class="pagos_anticipos">
								<form:form id="formBusqueda" commandName="legajoChequeRechazadoDto" method="POST">
									<input type="hidden" id="volver" name="volver" value="/cheques-rechazados-busqueda" />
<!-- 									<input type="hidden" id="idLegajo" name="idLegajo" /> -->
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}" />
									<input type="hidden" id="goBack" name="goBack" />
									
									<p style="padding: 5px 0px;">
										<strong>
											<spring:message	code="legajo.cheques.rechazados.busqueda.filtrosLegajo.titulo" />
										</strong>
									</p>

									<!-- FILA 1 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa">
												<span class="rojo">* </span> 
												<spring:message	code="legajo.cheques.rechazados.legajo.empresa" />
											</label>
											<div class="controls">
												<select id="selectEmpresa" name="empresa" class="input">
													<c:if test="${comboEmpresa}">
														<option value="">
															<spring:message	code="combo.seleccionar" />
														</option>
													</c:if>
													<c:forEach items="${empresas}" var="emp">
														<option value="${emp.idEmpresa}">${emp.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="empresa"> 
												<spring:message code="legajo.cheques.rechazados.legajo.segmento" />
											</label>
											<div class="controls">
												<select id="selectSegmento" name="segmento" class="input">
													<c:if test="${comboSegmento}">
														<option value=""><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${segmentos}" var="seg">
														<option value="${seg.idSegmento}">${seg.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<!-- fin de span3 -->
									<!-- FILA 1 ERROR -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorEmpresa" class="error"></span>
										</div>

										<div class="span3">
											<span id="errorSegmento" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->
									<!-- FILA 2 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="selectCliente">Datos Cliente</label>
											<div class="controls">
												<select id="selectCliente" name="selectCliente"	class="input">
													<option value="">
														<spring:message code="combo.seleccionar" />
													</option>
													<c:forEach items="${criterioBusquedaCliente}" var="criterioBusquedaCliente">
														<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="selectCliente">&nbsp;</label>
											<div class="controls">
												<input id="textCliente" name="textCliente" type="text" maxlength="10" class="input" style="margin: 0 auto;"
												 disabled="disabled" />
											</div>
										</div>
									</div>
									<!-- fin de row -->

									<!-- FILA 2 Error -->

									<div class="row rowError">
										<div class="span3">
											<span id="#errorSelectCriterio" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorTextCriterio" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->

									<!-- FILA 3 -->
									<div class="row">
										<!-- Estado -->
										<div class="span3">
											<label class="control-label" for="estado">
												<spring:message	code="legajo.cheques.rechazados.legajo.estadoLegajo" />
											</label>
											<div class="controls">
												<select id="selectEstado" name="estado" class="input">
													<c:if test="${comboEstados}">
														<option value=""><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${estados}" var="est">
														<option value="${est.nombre}">${est.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- id Legajo -->
										<div class="span3">
											<label class="control-label" for="idLegajo">
												<spring:message code="legajo.cheques.rechazados.legajo.idLegajo" />
											</label>
											<div class="controls">
												<input type="text" id="idLegajo" name="idLegajo" class="input" maxlength="7" />
											</div>
										</div>
									</div>
									<!-- fin de row -->

									<!-- FILA 3 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorEstado" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdLegajo" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
									<!-- FILA 4 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaAltaDesdeLegajo">
												<spring:message code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.desde" />
													</label>
											<div class="controls">
												<input id="fechaAltaDesdeLegajo" name="fechaAltaDesdeLegajo"
												data-date-format="dd/mm/yyyy" type="text" class="input" maxlength="10" />
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaAltaHastaLegajo">
												<spring:message	code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.hasta" />
													</label>
											<div class="controls">
												<input id="fechaAltaHastaLegajo" name="fechaAltaHastaLegajo"
													data-date-format="dd/mm/yyyy" type="text" class="input" maxlength="10" />
											</div>
										</div>
									</div>
									<!-- fin de row -->

									<!-- FILA 4 Error -->

									<div class="row rowError">
										<div class="span3">
											<span id="errorFechaAltaDesdeLegajo" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorFechaAltaHastaLegajo" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
									<!-- Boton volver -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnVolver"
												name="btnVolver" type="button" style="display: none;"
												onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i>
												<spring:message code="accion.goBack" />
											</button>
										</div>
									</div>
									<!-- fin de row -->

									<p style="padding: 5px 0px;">
										<strong>
											<spring:message code="legajo.cheques.rechazados.busqueda.filtrosCheque.titulo" />
										</strong>
									</p>

									<!-- FILA 5 -->
									<div class="row">
										<!-- Estado -->
										<div class="span3">
											<label class="control-label" for="nroCheque">
												<spring:message	code="legajo.cheques.rechazados.cheque.filtro.nro.cheque" />
											</label>
											<div class="controls">
												<input type="text" id="nroCheque" name="nroCheque" class="input" maxlength="8" />
											</div>
										</div>
										<!-- Motivo -->
										<div class="span3">
											<label class="control-label" for="idAnalista">
												<spring:message	code="legajo.cheques.rechazados.legajo.analista" />
											</label>
											<div class="controls">
												<select id="selectAnalista" name="analista" class="input">
												</select>
											</div>
										</div>

									</div>
									<!-- fin de row -->

									<!-- FILA 5 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorNroCheque" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdAnalista" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->


									<!-- FILA 6 -->
									<div class="row">

										<!-- Importe Desde -->
										<div class="span3">
											<label class="control-label" for="importeDesde">
												<spring:message code="legajo.cheques.rechazados.busqueda.importe.desde" /></label>
											<div class="controls">
												<input id="importeDesde" name="importeDesde" type="text" class="input"
												onkeypress="return validarInputNumericosComaPunto(event)" maxlength="16" />
											</div>
										</div>
										<!-- fin de span3 -->

										<!-- Importe Hasta -->
										<div class="span3">
											<label class="control-label" for="importeHasta">
												<spring:message code="legajo.cheques.rechazados.busqueda.importe.hasta" />
											</label>
											<div class="controls">
												<input id="importeHasta" name="importeHasta" type="text" class="input"
												onkeypress="return validarInputNumericosComaPunto(event)" maxlength="16" />
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 6 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorImporteDesde" class="error"></span>
										</div>

										<div class="span3">
											<span id="errorImporteHasta" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->

									<!-- FILA 7 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaAltaDesdeCheque">
												<spring:message code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.desde" />
													</label>
											<div class="controls">
												<input id="fechaAltaDesdeCheque" name="fechaAltaDesdeCheque"
												data-date-format="dd/mm/yyyy" type="text" class="input" maxlength="10" />
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaAltaHastaCheque">
												<spring:message	code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.hasta" />
													</label>
											<div class="controls">
												<input id="fechaAltaHastaCheque" name="fechaAltaHastaCheque"
													data-date-format="dd/mm/yyyy" type="text" class="input" maxlength="10" />
											</div>
										</div>
									</div>
									<!-- fin de row -->

									<!-- FILA 7 Error -->

									<div class="row rowError">
										<div class="span3">
											<span id="errorFechaAltaDesdeCheque" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorFechaAltaHastaCheque" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->

									<!-- FILA 8 -->
									<div class="row">

										<!-- Numero Documento -->
										<div class="span3" id='divFechaVencimientoCheque'>
											<label class="control-label" for="fechaVencimientoCheque">
												<spring:message	code="legajo.cheques.rechazados.cheque.filtro.fecha.vencimiento" />
											</label>
											<div class="controls">
												<input id="fechaVencimientoCheque"
													data-date-format="dd/mm/yyyy" name="fechaVencimientoCheque" type="text" class="input" maxlength="10" />
											</div>
										</div>
										<!-- fin de span3 -->
									</div>
									<!-- fin de row -->

									<!-- FILA 8 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorFechaVencimientoCheque" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->

									<!-- FILA 9 -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="codigoBanco">
												<spring:message	code="legajo.cheques.rechazados.cheque.filtro.codigo.banco" />
											</label>
											<div class="controls">
												<select id="codigoBanco" name="codigoBanco" class="input">
													<c:if test="${comboBancoOrigen}">
														<option value="">
															<spring:message code="combo.seleccionar" />
														</option>
													</c:if>
													<c:forEach items="${bancoOrigenes}" var="ori">
														<option value="${ori.idBanco}" idAgrupador="${ori.idAgrupador}">${ori.idBanco}
															- ${ori. descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="span3" id="divDescripcionBanco">
											<label class="control-label" for="descripcionBanco">
												<spring:message code="legajo.cheques.rechazados.cheque.filtro.descripcion.banco" />
											</label>
											<div class="controls">
												<select id="descripcionBanco" name="descripcionBanco" class="input">
													<c:if test="${comboBancoOrigen}">
														<option value="">
															<spring:message code="combo.seleccionar" />
														</option>
													</c:if>
													<c:forEach items="${bancoOrigenesDescripcion}" var="ori">
														<option value="${ori.idValue}">${ori.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<!-- fin de row -->

									<!-- FILA 9 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorCodigoBanco" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorDescripcionBanco" class="error"></span>
										</div>
									</div>
									<!-- fin de row -->

									<!-- Boton Buscar -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnBuscar" name="btnBuscar" type="button">
												<i class="icon-white icon-search"></i>
												<spring:message code="accion.find" />
											</button>
										</div>
									</div>

									<!-- TABLA RESULTADOS BUSQUEDA -->
									<p style="padding: 5px 0px;">
										<strong>
											<spring:message	code="legajo.cheques.rechazados.busqueda.resultado.titulo" />
										</strong>
									</p>
									<div class="row">
										<div class="span9">
											<table id="legajosEncontrados" class="tablaResultado">
												<thead>
<!-- 													<th><input type="checkbox" id="seleccionarTodos" disabled /></th> -->
<!-- 													CheckBox -->
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-remove -->
<!-- 													<th></th> -->
													<!-- icon-historial -->
													<th><spring:message	code="legajo.cheques.rechazados.legajo.empresa" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.segmento" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.idLegajo" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.cuit" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.cliente" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.banco.origen" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.nro.cheque" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.tablaResultado.estado" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.motivo.rechazo" /></th>
													<th class="date"><spring:message code="legajo.cheques.rechazados.busqueda.fecha.ultimo.estado" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.analista" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.copropietario" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.legajo.tablaResultado.analistaTeamComercial" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.analistaCobranzas" /></th>
													<th><spring:message	code="legajo.cheques.rechazados.busqueda.analistaCobranzas.copropietario" /></th>
													<th class="importe"><spring:message	code="legajo.cheques.rechazados.legajo.tablaResultado.importeCheque" /></th>
													<th class="date"><spring:message code="legajo.cheques.rechazados.busqueda.fecha.alta" /></th>
													<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.fecha.notificacion.rechazo" /></th>
													<th class="date"><spring:message code="legajo.cheques.rechazados.busqueda.fecha.cierre" /></th>
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-remove -->
<!-- 													<th></th> -->
													<!-- icon-historial -->
												</thead>
											</table>
										</div>
									</div>
									<div class="row rowError">
										<div class="span9">
											<span id="errorRespuestaLegajos" class="error"></span>
										</div>
									</div>
									<!-- FIN TABLA RESULTADOS BUSQUEDA -->
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
	$('#volverAPantalla').val('');
	var VUELVO_BANDEJA = "${caminoDeVuelta}";
	var volviendoABusqueda = <c:out value = "${volviendoABusqueda}"/>;
</script>