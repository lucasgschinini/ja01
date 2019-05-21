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

<title><spring:message code="operacionesMasivas.busqueda.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/shiva-busqueda-operacionesMasivas.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>




<style type="text/css">
.customAll {
	float: left;
}

<!--[if lte IE]>
<style>
.custom-combobox-toggle {
	position: absolute;
	top: 0;
	bottom: 10px;
	margin-left: -30px;
	padding: 0;
	/* support: IE7 */
	*height: 1.7em;
	*top: 0.1em;
}
</style>
<![endif]-->
<style>
   .custom-combobox { 
     position: relative; 
     display: inline-block; 
   } 
   
 
  @media screen and (-webkit-min-device-pixel-ratio:0) {
	.custom-combobox-toggle { 
		position: absolute; 
		top: 0; 
		bottom: 0px; 
		margin-left: -19px; 
		padding: 0; 
		/* support: IE7 */ 
		*height: 1.7em; 
		*top: 0.1em; 
	}
  }
   .custom-combobox-input { 
     margin: 0; 
     padding: 0.3em; 
   } 
   	/* button text element */ 
	.ui-button .ui-button-text { 
		display: block; 
/* 		line-height: normal;  */
		  color: #ffffff; 
/* 	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);  */
	  background-color: #E6E6E6; 
	  *background-color: #0044cc; 
	  background-image: -moz-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0044cc)); 
	  background-image: -webkit-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: -o-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: linear-gradient(to bottom, #0088cc, #0044cc); 
	  background-repeat: repeat-x; 
	  border-color: #0044cc #0044cc #002a80; 
	  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); 
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0); 
	  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false); 
	} 
 	.ui-button:hover { 
 	  color: #ffffff; 
 	  background-color: #A9DBF6; 
 	  *background-color: #003bb3; 
 	} 
 	.ui-state-hover { 
	 	border: 1px solid #999999; 
	 	background: #dadada url(../img/jquery/ui-bg_glass_75_dadada_1x400.png) 50% 50% repeat-x; 
	 	font-weight: normal; 
	 	color: #212121; 
 	}
 	

 	
 	
</style>

<script type="text/javascript">
	var idAnalista = '${operacionMasivaBusquedaFiltro.idAnalista}';
	var volviendoABusqueda = <c:out value = "${volviendoABusqueda}"/>;
</script>
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
								<spring:message code="operacionesMasivas.busqueda.titulo" />
							</div>	

							<div class="pagos_anticipos">
								<form:form commandName="operacionMasivaDto">
									<input type="hidden" id="idCobro" name="idCobro"/>
									<input type="hidden" id="idOperacionMasiva" name="idOperacionMasiva"/>
									<input type="hidden" id=idOperacionFormateado name="idOperacionFormateado"/>
									<input type="hidden" id="opcion" name="opcion"/>
									<input type="hidden" id="volver" name="volver" value="/operacion-masiva-busqueda"/>
									<input type="hidden" id="goBack" name="goBack"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="idAltaEdicion" name="idAltaEdicion" value="A" />
									<input type="hidden" id="volverAPantalla" name="volverAPantalla" />
									<input type="hidden" id="idsDesapropiar" name="idsDesapropiar" />
									<input type="hidden" id="idsOperacion" name="idsOperacion" />
									<input type="hidden" id="nombreArchivo" name="nombreArchivo" />
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="operacionesMasivas.busqueda.filtrosOperacionesMasivas.titulo" /></strong>
									</p>

									<!-- FILA 1 -->
									<div class="row">

										<!-- Empresa -->
										<div class="span3">
											<label class="control-label" for="empresa"><span
												class="rojo">*</span></span> <spring:message code="recibo.empresa" /></label>
											<div class="controls ">
												<select id="selectEmpresa" name="empresa" class="input">

													<c:if test="${comboEmpresa}">
														<option value=""><spring:message
																code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${empresas}" var="emp">
														<option value="${emp.idEmpresa}">${emp.descripcion}</option>
													</c:forEach>

												</select>
											</div>
										</div>
										<!-- fin de span3 -->

										<!-- Segmento -->
										<div class="span3">
											<label class="control-label" for="segmento"></span> <spring:message
													code="recibo.segmento" /></label>
												<div id="ctrlSegmento" class="controls">
													<select id="selectSegmento" name="segmento" class="input">
														<option value=""><spring:message
																code="combo.seleccionar" /></option>
															<c:forEach items="${segmentos}" var="seg">
																<c:choose>
																	<c:when test="${seg.idSegmento eq operacionMasivaBusquedaFiltro.idSegmento}">
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
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

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
										<!-- Estado -->
										<div class="span3">
											<label class="control-label" for="estado"><spring:message
													code="recibo.estado" /></label>
											<div class="controls">
												<select id="selectEstado" name="estado" class="input">
													<option value=""><spring:message code="combo.seleccionar" /></option>
													<c:forEach items="${estados}" var="est">
														<c:choose>
															<c:when test="${est.codigo eq operacionMasivaBusquedaFiltro.idEstado}">
																<option value="${est.codigo}" selected>${est.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${est.codigo}">${est.descripcion}</option>
													    	</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- Motivo -->
										<div class="span3">
											<label class="control-label" for="Motivo"><spring:message
													code="conf.cobro.motivo" /></label>
											<div class="controls">
												<select id="selectMotivo" name="Motivo" class="input">
													<option value="" selected><spring:message
																code="combo.seleccionar" /></option>													
													<c:forEach items="${motivos}" var="mot">
														<c:choose>
															<c:when test="${mot.idMotivoCobro eq operacionMasivaBusquedaFiltro.idMotivo}">
																<option value="${mot.idMotivoCobro}" selected>${mot.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${mot.idMotivoCobro}">${mot.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										
										<!-- Analista -->
										<div class="span3">
											<label class="control-label" for="analista">
												<spring:message code="valor.analista" />
											</label>
											<div class="controls">
												<select id="selectAnalista" name="analista" class="input">
												</select>

											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 2 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorIdEstado" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdMotivo" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->


									<!-- FILA 3 -->
									<div class="row">

										<!-- Tipo Operación Masiva -->
										<div class="span3">
											<label class="control-label" for="tipoOperacionMasiva"><spring:message
													code="operacionesMasivas.busqueda.tipoOperacionMasiva" /></label>
											<div class="controls">
												<select id="selectTipoOperacionMasiva" name="tipoOperacionMasiva" class="input">
<%-- 												<c:if test="${comboTipoOperacion}"> --%>
															<option value=""><spring:message
																code="combo.seleccionar" /></option>
<%-- 														</c:if> --%>
															<c:forEach items="${tiposOperacionesMasivas}" var="tipo">
																<c:choose>
																	<c:when test="${tipo.name eq operacionMasivaBusquedaFiltro.idTipoOperacionMasiva}">
																				<option value="${tipo.name}" selected>${tipo.descripcion}</option>
																	</c:when>
																		<c:otherwise>
																			<option value="${tipo.name}">${tipo.descripcion}</option>
																		</c:otherwise>
																</c:choose>
															</c:forEach> 
												</select>
											</div>
										</div>
										<!-- fin de span3 -->
										
											<!-- Id Operacion Masiva -->
										
										<div class="span3">
											<label class="control-label" for="idOperacionMasiva"><spring:message
													code="operacionesMasivas.busqueda.idOperacionesMasivas"/></label>
											<div class="controls">
												<c:if test="${volviendoABusqueda}">
													<input id="selectIdOperacionMasiva" name="idOperacionMasiva" type="text" class="input" value="${operacionMasivaBusquedaFiltro.idOperacionMasiva}" class="input"/>
												</c:if>
												<c:if test="${not volviendoABusqueda}">
													<input id="selectIdOperacionMasiva" name="idOperacionMasiva" type="text" class="input" value="${idOperacionMasiva}" />
												</c:if>
											</div>
										</div>
										
										<!-- Fin Id Operacion Masiva -->

										

									</div>
									<div class="row rowError">
										<div class="span3">
											<span id="errorTipoOperacionMasiva" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdOperacionMasiva" class="error"></span>
										</div>
									</div>
									<!-- fin de row -->

								

									<!-- FILA 4 -->
									<div class="row">
									
									<!-- Fecha Desde -->
										<div class="span3">
											<label class="control-label" for="fechaDesde"><spring:message
													code="recibo.fechaDesde" /></label>
											<div class="controls">
												<input id="fechaDesde" name="fechaDesde" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${operacionMasivaBusquedaFiltro.fechaDesde}"/>
											</div>
										</div>

										<!-- Fecha Hasta -->
										<div class="span3">
											<label class="control-label" for="fechaHasta"><spring:message
													code="recibo.fechaHasta" /></label>
											<div class="controls">
												<input id="fechaHasta" name="fechaHasta" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${operacionMasivaBusquedaFiltro.fechaHasta}" />
											</div>
										</div>
										<!-- fin de span3 -->
																								
									</div>
									<!-- fin de row -->
									
										<!-- FILA 4 Error -->
									<div class="row rowError">


										<div class="span3">
											<span id="errorFechaDesde" class="error"></span>
										</div>

										<div class="span3">
											<span id="errorFechaHasta" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->

									<!-- FILA 4 Error -->
									
									<div class="row rowError">
										<div class="span3">
											<span id="errorIdReversion" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdAnalista" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
								
									<!-- Boton Buscar -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnBuscar"
												name="btnBuscar" type="button">
												<i class="icon-white icon-search"></i>
												<spring:message code="recibo.botonBuscar" />
											</button>
										</div>
									</div>

									<!-- FILA  Error General -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorBusqueda" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->

									<!-- TABLA RESULTADOS BUSQUEDA -->
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="operacionesMasivas.busqueda.resultado.titulo" /></strong>
									</p>
									<div class="row">
										<div class="span9">
											<table id="tablaOperacionesMasivas" class="tablaResultado">
												<thead>
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-revertir -->
													<th></th>
													<!-- icon-remove -->
													<th></th>
													<!-- icon-historial -->
													<th></th>
													<!-- icon-VerCobros -->
													<th><spring:message
 															code="operacionesMasivas.busqueda.idOperacionesMasivas"/></th>
												 	<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.archivoOriginal"/></th>
 													<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.estado"/></th> 
 													<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.tipoOperacion"/></th> 
 													<th><spring:message
  															code="operacionesMasivas.busqueda.resultado.fechaUltimaModificacion"/></th> 
 													<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.motivoOm"/></th>
 													<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.analista"/></th>
 													<th><spring:message
 															code="operacionesMasivas.busqueda.resultado.copropietario"/></th>
 													<th class="dateTimes"><spring:message
 															code="operacionesMasivas.busqueda.resultado.fechaAlta"/></th>
 													<th class="dateTimes"><spring:message
 															code="operacionesMasivas.busqueda.resultado.fechaDerivacion"/></th>
 													<th class="dateTimes"><spring:message
  															code="operacionesMasivas.busqueda.resultado.fechaAutorizacionReferente"/></th> 
 													<th class="dateTimes"><spring:message
 															code="operacionesMasivas.busqueda.resultado.fechaDeProcesamiento"/></th>
 													<th><spring:message
  															code="operacionesMasivas.busqueda.resultado.registrosIngresados"/></th> 
 													<th><spring:message
  															code="operacionesMasivas.busqueda.resultado.registrosProcesadosCorrectamente"/></th>
													<th><spring:message
															code="operacionesMasivas.busqueda.resultado.registrosProcesadosConError" /></th>
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-revertir -->
													<th></th>
													<!-- icon-remove -->
													<th></th>
													<!-- icon-historial -->
													<th></th>
													<!-- icon-VerCobros -->
												</thead>
											</table>
										</div>
									</div>
									<!-- FIN TABLA RESULTADOS BUSQUEDA -->
								
								</form:form>


							</div>
							<!-- fin -->

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

	<script>
		var mensajeAnular = '<spring:message code="operaciones.masivas.mensaje.anular.ok"/>';
	</script>

</body>
</html>


