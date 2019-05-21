<%@page import="org.hibernate.validator.internal.util.privilegedactions.GetConstructor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setHeader("Cache-Control",
			"no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setDateHeader("Expires", 0); // Proxies.
%>

<html>
<head>
<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
</head>
<body>

	<div id="container">

		<jsp:include page="../template/cabecera.jsp"></jsp:include>

		<div id="main">

			<div class="wrap">

				<jsp:include page="mantenimientoMenu.jsp"></jsp:include>

				<div id="content">

					<div id="inside">

						<div id="payments" class="box">
							<div class="title">Tablero de Control / Trazas</div>

							<div class="alert alert-error">
								<div class="row">
									<div class="span8">
										<label class="control-label" style="font-weight: bold;">Traceos</label><br>
										<div class="controls">
											<c:if test="${!tracearSQL}">
												<a
													href="${pageContext.request.contextPath}/mantenimientoTracearSQL.htm"
													class="btn btn-primary btn-small">Tracear SQL</a>
											</c:if>
											<c:if test="${tracearSQL}">
												<a
													href="${pageContext.request.contextPath}/mantenimientoQuitarTraceoSQL.htm"
													class="btn btn-primary btn-small">Quitar traceo SQL</a>
											</c:if>
										</div>
									</div>
								</div>
								<br> <br>
								<div class="row">
									<div class="span8">
										<label class="control-label" style="font-weight: bold;">Descarga
											de Ficheros</label><br>
										<div class="controls">
											<a
												href="${pageContext.request.contextPath}/mantenimientoDescargarServidorLog.htm"
												class="btn btn-primary btn-small">Descargar Server Log</a> <br>
											<br> <label class="control-label"
												style="font-weight: bold;">Descarga de Trazas de
												Shiva</label><br>
											<form id="resultado" method="post"
												action="${pageContext.request.contextPath}/mantenimientoDescargarShivaLog">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" /> <input type="hidden"
													id="nombreArchivoADescargar" name="nombreArchivoADescargar" />

												<div class="row">
													<div class="span9">
														<table id="tablaArchivosADescargar" class="tablaResultado"
															style="width: 100%;">
															<thead>
																<tr>
																	<th></th>
																	<th>Nombre del Archivo</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${listaArchivosLog}" var="arch"
																	varStatus="i">
																	<tr>
																		<td>
																			<button type="button" id="btnDescargarArchivo"
																				class="btn btn-xs btn-link"
																				onclick="javascript:descargarLog('${arch}');"
																				title="Descargar">
																				<i class="icon-download bigger-120"></i>
																			</button>
																		</td>
																		<td>${arch}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row" style="margin: 25px 30px;">
							<div align="right">
								<button class="btn btn-primary btn-small"
									onclick="window.location.href='${pageContext.request.contextPath}/principal';">
									<i class="icon-white icon-home"></i> Inicio</button>
							</div>
						</div>
					</div>
					<!-- end #"inside" -->

				</div>
				<!-- end #payments -->

			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- end #container -->

	<script>
		// Tratamiento de Tablas
		window.onload = function() {
			var idTabla = 'tablaArchivosADescargar';
			inicializarTabla(idTabla);
			$('#' + idTabla).dataTable().fnSort([ [ 1, 'desc' ] ]);

			var style = $('.dataTables_scrollHead.ui-state-default').attr(
					'style').replace('overflow: hidden', 'overflow: visible');
			$('.dataTables_scrollHead.ui-state-default').attr('style', style);
			$('.dataTables_scroll').attr('style',
					'padding-top: 5px; padding-bottom: 5px;');

		};

		function descargarLog(idArchivo) {
			$("#nombreArchivoADescargar").val(idArchivo);
			$('#resultado')[0].submit();
		};
	</script>

</body>
</html>