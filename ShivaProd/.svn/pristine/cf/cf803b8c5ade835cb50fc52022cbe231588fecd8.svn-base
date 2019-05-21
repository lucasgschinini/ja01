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

<title><spring:message code="titulo.nombre.app" />&nbsp;-&nbsp;<spring:message code="operaciones.masivas.titulo.pagina.aprob" />&nbsp;-&nbsp;<spring:message
		code="operaciones.masivas.titulo.pagina.aprobacion" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/operacion-masiva-aprobacion-detalle.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>

<style type="text/css">
tr.trans-0 td {
	background-color: #F2F2F7;
}

tr.trans-1 td {
	background-color: white;
}

.customAll {
	float: left;
}

.sigma {
	float: left;
	width: 20px;
	height: 20px;
	margin-top: 20px;
}

.sigmaControlGroup {
	float: right;
	margin-right: 70px;
}

.sigmaInputText {
	width: 100px;
	text-align: right;
}

.customPag_info {
	clear: both;
	float: left;
	padding-top: 0.755em;
}

.customPag_simple {
	float: right;
	text-align: right;
	padding-top: 0.25em;
}

.customPaginate_button {
	box-sizing: border-box;
	display: inline-block;
	min-width: 1.5em;
	padding: 0.5em 1em;
	margin-left: -2px;
	text-align: center;
	text-decoration: none !important;
	cursor: pointer;
	*cursor: hand;
	color: #333333 !important;
	border: 1px solid transparent;
}

.customPaginate_button.disabled,.customPaginate_button.disabled:hover,.customPaginate_button.disabled:active
	{
	cursor: default;
	color: #666 !important;
	border: 1px solid transparent;
	background: transparent;
	box-shadow: none;
}

.customPaginate_button:hover {
	color: white !important;
	border: 1px solid #0044cc;
	background-color: #006dcc;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #006dcc),
		color-stop(100%, #0044cc));
	/* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, #006dcc 0%, #0044cc 100%);
	/* Chrome10+,Safari5.1+ */
	background: -moz-linear-gradient(top, #006dcc 0%, #0044cc 100%);
	/* FF3.6+ */
	background: -ms-linear-gradient(top, #006dcc 0%, #0044cc 100%);
	/* IE10+ */
	background: -o-linear-gradient(top, #006dcc 0%, #0044cc 100%);
	/* Opera 11.10+ */
	background: linear-gradient(to bottom, #006dcc 0%, #0044cc 100%);
	/* W3C */
}

.customPaginate_button:active {
	outline: none;
	background-color: #0044cc;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #0044cc),
		color-stop(100%, #006dcc));
	/* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top, #0044cc 0%, #006dcc 100%);
	/* Chrome10+,Safari5.1+ */
	background: -moz-linear-gradient(top, #0044cc 0%, #006dcc 100%);
	/* FF3.6+ */
	background: -ms-linear-gradient(top, #0044cc 0%, #006dcc 100%);
	/* IE10+ */
	background: -o-linear-gradient(top, #0044cc 0%, #006dcc 100%);
	/* Opera 11.10+ */
	background: linear-gradient(to bottom, #0044cc 0%, #006dcc 100%);
	/* W3C */
	box-shadow: inset 0 0 3px #111;
}

.gestion {
	cursor: default;
	padding-left: 0px;
	padding-right: 0px;
}

.gestion-semaforo {
	padding-left: 0px;
	padding-right: 0px;
	border: solid 1px gray;
	width: 10px;
	height: 10px;
}

.gestion-semaforo-rojo {
	background-color: red;
}

.gestion-semaforo-amarillo {
	background-color: yellow;
}

.gestion-semaforo-verde {
	background-color: green;
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
							<div class="title" id="titleDetalle">
								<spring:message code="cobro.detalleOperacionMasiva.title" />
							</div>
							<div class="title" id="titleAprobacion">
								<spring:message code="cobro.AprobacionOperacionMasiva.title" />
							</div>
							<div class="pagos_anticipos">
								<form:form id="formOperacionMasiva" commandName="operacionMasivaDto" action="${pageContext.request.contextPath}/operacion-masiva-detalle-aprobacion">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<input type="hidden" name="detalleDoAprobacionA" id="detalleDoAprobacionA" value="${detalleDoAprobacionA}" />
									<input type="hidden" name="vuelvoBandeja" id="vuelvoBandeja" value="${vuelvoBandeja}" />
									<input type="hidden" id="idOperacionMasivaFormateado" name="idOperacionMasivaFormateado" value="${idOperacionMasivaFormateado}" />
									<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}" />
									<input type="hidden" id="idAnalista" name="idAnalista" value="${idUsuario}" />
									<input type="hidden" id="volver" name="volver" value="/operacion-masiva-detalle-aprobacion"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="goBack" name="goBack"/>
									<input type="hidden" id="volviendoA" value="${volviendoA}" />
									<input type="hidden" id="nombreArchivo" name="nombreArchivo" value="${nombreArchivo}" />

									<div class="row">
										<div class="span3" align="left">
											<label class="control-label" for="idOperacionMasiva">Id Operación Masiva</label>
											<div class="controls">
												<input id="idOperacionMasiva" name="idOperacionMasiva" type="text" class="input" value="${idOperacionMasiva}" readonly />
											</div>
										</div>
										<div class="span6" style="margin-top: 16px" align="right">
											<div>
												<button type="button" class="btn btn-primary btn-small" id="btnHistorial" onclick="javascript:historialOpMas();">
													<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial
												</button>
												<button type="button" class="btn btn-primary btn-small" style="display: none;" id="btnVolverUp" onclick="javascript:volverBusqueda();">
													<i class="icon-white icon-repeat"></i>Volver
												</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span3" align="left" id="divEstado">
											<label class="control-label" for="opeMasEstado">Estado Operación Masiva</label>
											<div class="controls">
												<input id="opeMasEstado" name="opeMasEstado" type="text" value="${estadoOpeMas}" class="input" readonly />
											</div>
										</div>
									</div>
									<div id="conf-cobro-tabs">
										<!-- PREVISUALIZACION -->
										<!-- 									<h3>Previsualización</h3> -->
										<div>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="prevEmpresa">Empresa</label>
													<div class="controls">
														<input id="prevEmpresa" name="prevEmpresa" type="text" class="input" readonly value="${prevEmpresa}" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="prevSegmento">Segmento</label>
													<div class="controls">
														<input id="prevSegmento" name="prevSegmento" type="text" class="input" readonly value="${prevSegmento}" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="prevAnalista">Analista</label>
													<div class="controls">
														<input id="prevAnalista" name="prevAnalista" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuario}"/>" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="prevCopropietario">Copropietario</label>
													<div class="controls">
														<input id="prevCopropietario" name="prevCopropietario" type="text" class="input" readonly value="${prevCopropietario}" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="prevMotivo">Motivo</label>
													<div class="controls">
														<input id="prevMotivo" name="prevMotivo" type="text" class="input" readonly value="${prevMotivo}" />
													</div>
												</div>
											</div>
											
											<p style="padding: 25px 0px 5px 0px;">
												<strong><spring:message code="operacionesMasivas.archivoOperacionMasiva" /></strong>
											</p>
											<div id="archivoOperacionMasiva">
												<div class="row">
													<div class="span9">
														<table id="archivosPendientes" class="tablaResultado">
															<thead>
																<tr>
																	<th>Id Operación Masiva</th>
																	<th>Nombre Archivo</th>
																	<th>Tipo de Operación</th>
																	<th>Cantidad de Registros</th>
																	<th>Estado</th>
																	<th>Importe</th>
																	<th>Analista</th>
																	<th>Copropietario</th>
																	<th>Motivo</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${listaArchivosPendientes}" var="archivo" varStatus="i" begin="0">
																	<tr>
																		<td>${archivo.idOperacionMasiva}</td>
																		<td>${archivo.nombreArchivo}</td>
																		<td>${archivo.tipoOperacion}</td>
																		<td>${archivo.cantRegistros}</td>
																		<td>${archivo.estado}</td>
																		<td>${archivo.importe}</td>
																		<td style="text-align: left"> 
				 														  <div style="width: 140px;">
				 															<img alt="Usuario" src="${archivo.urlFotoAnalista}"  
				 															style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
				 															onerror='src="${pageContext.request.contextPath}/img/default2.png"'> 
				 															${archivo.nombreAnalista} <br> 
				 															<a href="sip:${archivo.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a> 
				 															<a href="mailto:${archivo.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a> 
				 												 		   </div> 
				 												 		</td>
																		<td style="text-align: left"> 
																		  <div style="width: 140px;">
																		  	<c:choose>
																				<c:when test="${archivo.nombreCopropietario eq '-'}">
																					<p style="text-align: center"> - </p>
																				</c:when>
																				<c:otherwise>
					 																<img alt="Usuario" src="${archivo.urlFotoCopropietario}"  
					 																style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
					 																onerror='src="${pageContext.request.contextPath}/img/default2.png"'> 
					 																${archivo.nombreCopropietario} <br> 
					 																<a href="sip:${archivo.mailCopropietario}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a> 
					 																<a href="mailto:${archivo.mailCopropietario}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a> 
				 												 		   		</c:otherwise>
												 		  					 </c:choose>
				 												 		   </div> 
				 												 		</td>
																		<td>${archivo.motivo}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											
											<p style="padding: 25px 0px 5px 0px;">
												<strong>Listado de comprobantes</strong>
											</p>
											<div id="bloqueComprobantes">
												<div class="row">
													<div class="span9">
														<table
															style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit;"
																class="tablaResultadoHistorialComprobante" id="listaComprobantes">
															<thead>
																<tr>
																	<th class="tituloTabla"><spring:message code="valor.nombreArchivo" /></th>
																	<th class="tituloTabla"><spring:message code="valor.descripcion" /></th>
																	<th class="tituloTabla"></th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${operacionMasivaDto.listaComprobantes}" var="com" varStatus="i">
																	<tr>
																		<td>${com.nombreArchivo}</td>
																		<td>${com.descripcionArchivo}</td>
																		<td>
																			<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																				<button type="button" class="btn btn-xs btn-link" title="Descargar comprobante"
																					onclick="javascript:descargarComprobante('${com.idComprobante}');">
																					<i class="icon-download-alt bigger-120"></i>
																				</button>
																			</div>
																			<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>
																		</td>
																	</tr>
																	<form:input path="listaComprobantes[${i.index}].nombreArchivo" id="nombreArchivo${i.index}" type="hidden"/>
																	<form:input path="listaComprobantes[${i.index}].descripcionArchivo" id="descripcionArchivo${i.index}" type="hidden"/>
																	<form:input path="listaComprobantes[${i.index}].documento" id="documento${i.index}" type="hidden"/>
																	<form:input path="listaComprobantes[${i.index}].id" id="id${i.index}" type="hidden"/>
																	<form:input path="listaComprobantes[${i.index}].usuarioCreacion" id="usuarioCreacion${i.index}" type="hidden"/>
																</c:forEach>
																<c:if test="${empty operacionMasivaDto.listaComprobantes}">
																	<tr>
																		<td colspan="3" class="registroTabla" align="left">&nbsp;&nbsp;<spring:message code="mensaje.tabla.vacia" /></td>
																	</tr>
																</c:if>
															</tbody>
														</table>														
													</div>
												</div>
											</div>
											<!-- fin div bloque comprobantes -->
											<c:choose>
												<c:when test="${mostrarObservTextAnterior}">
													<div class="row" style="margin-top: 20px" id="prevObservacionesAnteriores">
														<div class="span9">
															<label class="control-label" for="observacionAnterior">Observaciones Anteriores</label>
															<textarea class="field span9" id="observacionAnterior" readonly name="observacionAnterior">${prevObservTextAnterior}</textarea>
														</div>
													</div>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
											<div class="row" style="margin-top: 20px" id="prevObservaciones">
												<div class="span9">
													<label class="control-label" for="observacion">Observaciones</label>
													<textarea class="field span9" id="observacion" name="observacion">${prevObservText2}</textarea>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3">
													<span id="errorPrevObservText2" class="error"></span>
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top: 17px">
										<div class="span9" align="right">
											<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button" onclick="javascript:autorizacionAprobacionOperacionMasiva();">
												<i class="icon-white icon-ok"></i>&nbsp;&nbsp;Aceptar&nbsp;&nbsp;
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnRechazar" onclick="javascript:rechazarAprobacionOperacionMasiva();">
												<i class="icon-white icon-remove"></i> Rechazar
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnVolver" onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i>&nbsp;&nbsp;&nbsp;Volver&nbsp;&nbsp;&nbsp;
											</button>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
</body>
</html>