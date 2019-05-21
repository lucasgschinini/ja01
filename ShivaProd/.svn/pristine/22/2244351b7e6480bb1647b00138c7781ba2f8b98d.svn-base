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

<title><spring:message code="descobro.busqueda.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>

<script src="${pageContext.request.contextPath}/js/descobro-busqueda.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>

<script>
	var mensajeAnular = '<spring:message code="descobro.busqueda.mensaje.anular.ok"/>';
	var cobroFiltroAnalista = "${descobroBusquedaFiltro.idAnalista}";
	var decobroFiltroIdSistemaDestino = "${descobroBusquedaFiltro.idSistemaDestino}";
	var volviendoABusqueda = <c:out value = "${volviendoABusqueda}"/>;
</script>


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
								<spring:message code="descobro.busqueda.titulo" />
							</div>

							<div class="pagos_anticipos">
								<form:form commandName="descobroDto">
									<input type="hidden" id="idDescobro" name="idDescobro"/>
									<input type="hidden" id="idCobro" name="idCobro"/>
									<input type="hidden" id="idEstado" name="idEstado"/>
									<input type="hidden" id=idOperacionDescobro name="idOperacionDescobro"/>
									<input type="hidden" id="opcion" name="opcion"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="volver" name="volver" value="/descobro-busqueda" />
									<input type="hidden" id="goBack" name="goBack"/>
									<input type="hidden" id="idAltaEdicion" name="idAltaEdicion" value="E"/>
									<input type="hidden" id="idsReversion" name="idsReversion" />
									
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="descobro.busqueda.filtrosDescobros.titulo" /></strong>
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
													<c:if test="${comboSegmento}">
														<option value=""><spring:message
																code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq descobroBusquedaFiltro.idSegmento}">
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
									<!-- FILA 3 -->
									<div class="row">
										<!-- Estado -->
										<div class="span3">
											<label class="control-label" for="estado"><spring:message
													code="recibo.estado" /></label>
											<div class="controls">
												<select id="selectEstado" name="selectEstado" class="input">
													<option value=""><spring:message
															code="combo.seleccionar" /></option>
													<c:forEach items="${estados}" var="est">
														<c:choose>
															<c:when test="${est eq descobroBusquedaFiltro.idEstado}">
																<option value="${est}" selected>${est}</option>
															</c:when>
															<c:otherwise>
																<option value="${est}">${est}</option>
														</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- Motivo -->
										<div class="span3">
											<label class="control-label" for="idMotivo"><spring:message
													code="conf.cobro.motivo" /></label>
											<div class="controls">
												<select id="selectMotivo" name="idMotivo" class="input">
													<c:if test="${comboMotivo}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${motivos}" var="mot">
														<c:choose>
															<c:when test="${mot.idMotivoDescobro eq descobroBusquedaFiltro.idMotivo}">
															<option value="${mot.idMotivoDescobro}" selected>${mot.descripcion}</option>
															</c:when>
															<c:otherwise>
															<option value="${mot.idMotivoDescobro}">${mot.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
									<!-- fin de row -->

									<!-- FILA 4 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorIdEstado" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdMotivo" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->


									<!-- FILA 5 -->
									<div class="row">

										<!-- Id Operacion Cobro -->
										<div class="span3">
											<label class="control-label" for="idOpCobro"><spring:message
													code="cobro.busqueda.idOperacionCobro" /></label>
											<div class="controls">
												<input id="idOpCobro" name="idOpCobro" maxlength="7"
													type="text" class="input" value="${descobroBusquedaFiltro.idOpCobro}"/>
											</div>
										</div>
										<!-- fin de span3 -->

										<!-- Fecha Desde -->
										<div class="span3">
											<label class="control-label" for="fechaDesde"><spring:message
													code="recibo.fechaDesde" /></label>
											<div class="controls">
												<input id="fechaDesde" name="fechaDesde" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${descobroBusquedaFiltro.fechaDesde}"/>
											</div>
										</div>
										<!-- fin de span3 -->

										<!-- Fecha Hasta -->
										<div class="span3">
											<label class="control-label" for="fechaHasta"><spring:message
													code="recibo.fechaHasta" /></label>
											<div class="controls">
												<input id="fechaHasta" name="fechaHasta" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${descobroBusquedaFiltro.fechaHasta}" />
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 5 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdOpCobro" class="error"></span>
										</div>

										<div class="span3">
											<span id="errorFechaDesde" class="error"></span>
										</div>

										<div class="span3">
											<span id="errorFechaHasta" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->

									<!-- FILA 6 -->
									<div class="row">
									
									<!-- Id Reversion -->
										<div class="span3">
											<label class="control-label" for="idReversion"><spring:message
													code="descobro.busqueda.resultado.idReversion" /></label>
											<div class="controls">
												<input id="idOperacionReversion" name="idOperacionReversion" maxlength="7"
													type="text" class="input" value="${descobroBusquedaFiltro.idOperacionReversion}"/>
											</div>
										</div>
										<!-- fin de span3 -->
										
										<!-- Analista -->
										<div class="span3">
											<label class="control-label" for="idAnalista"><spring:message
													code="valor.analista" /></label>
											<div class="controls">
												<select id="selectAnalista" name="analista" class="input"></select>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 6 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorIdReversionCobro" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdAnalista" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosCliente" /></strong>
									</p>								
											<div class="row" >			
												<div class="span3">
													<label class="control-label" for="selectCliente">Datos Cliente</label>
													<div class="controls">
														<select id="selectCliente" name="selectCliente" class="input">
															<option value=""><spring:message code="combo.seleccionar"/></option>
															<c:forEach items="${criterioBusquedaCliente}" var="criterioBusquedaCliente">
															<c:choose>
																<c:when test="${criterioBusquedaCliente.nombre eq descobroBusquedaFiltro.selectCliente}">
																	<option value="${criterioBusquedaCliente.nombre}" selected>${criterioBusquedaCliente.descripcion}</option>
																</c:when>
																<c:otherwise>
																	<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
																</c:otherwise>
															</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
												<label class="control-label" for="selectCliente">&nbsp;</label>
													<div class="controls">
														<input id="textCliente" name="textCliente" type="text" maxlength="13"
															class="input" style="margin: 0 auto;" value="${descobroBusquedaFiltro.textCliente}"/>
													</div>
												</div>
											</div>
											<!-- fin de row -->
									
											<!-- FILA 2 Error -->
											
											<div class="row rowError">
												<div class="span3">
													<span id="#errorSelectCliente" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorBusqueda" class="error"></span>
												</div>
											</div>
											<!-- fin de rowError -->
										
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosDebito.titulo" /></strong>
									</p>

									<!-- FILA 7 -->
									<div class="row">

										<!-- Numero Documento -->
										<div class="span3">
											<label class="control-label" for="nroDocumento"><spring:message
													code="cobro.busqueda.nroDocumento" /></label>
											<div class="controls">
												<input id="nroDocumento" name="nroDocumento" maxlength="15"
													type="text" class="input" value="${descobroBusquedaFiltro.nroDocumento}"/>
											</div>
										</div>
										<!-- fin de span3 -->
										
										

										<!-- Numero de Referencia Mic -->
										<div class="span3">
											<label class="control-label" for="nroRef"><spring:message
													code="cobro.busqueda.numeroRef" /></label>
											<div class="controls">
												<input id="nroRef" name="nroRef" maxlength="15"
													type="text" class="input" value="${descobroBusquedaFiltro.nroRef}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 7 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorNroDocumento" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorNroRefMic" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->
									
									
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosMedioPago.titulo" /></strong>
									</p>

									<!-- FILA 8 -->
									<div class="row">
										<!-- Tipo	Medio de Pago -->
										<div class="span3">
											<label class="control-label" for="idTipoMedioPago">Tipo
												Medio de Pago</label>
											<div class="controls">
												<select id="idTipoMedioPago" name="idTipoMedioPago"
													class="input">
													<c:if test="${comboTMP}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>

													<c:forEach items="${mediosPago}" var="mp">
														<c:choose>
															<c:when test="${mp.valor eq descobroBusquedaFiltro.idTipoMedioPago}">
																<option value="${mp.valor}" selected>${mp.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${mp.valor}">${mp.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->

										<!-- Referencia	MP -->
										<div class="span3">
											<label class="control-label" for="refMP"><spring:message
													code="cobro.busqueda.refMedioPago" /></label>
											<div class="controls">
												<input id="refMP" name="refMP" maxlength="25" type="text"
													class="input" value="${filtroUltimaBusqueda.refMP}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<div class="row rowError">

										<div class="span3">
											<span id="errorIdTipoMedioPago" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorRefMP" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->
																	<!-- FILA 8 -->
									<div class="row">
										<!-- SubTipo Compensacion -->
										<div class="span3" id="divSubComp">
											<label class="control-label" for="idSubtipoCompensacion">Subtipo
												Compensación</label>
											<div class="controls">
												<select id="idSubtipoCompensacion" name="idSubtipoCompensacion"
													class="input">
													<c:if test="${comboSTCOMP}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>

													<c:forEach items="${subtipoCompensacion}" var="stcomp">
														<c:choose>
															<c:when test="${stcomp.codigo eq descobroBusquedaFiltro.idSubtipoCompensacion}">
																<option value="${stcomp.codigo}" selected>${stcomp.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${stcomp.codigo}">${stcomp.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->

										<!-- numero de SAP (K$) -->
										<div class="span3" id="divNroSAP">
											<label class="control-label" for="nroSAP"><spring:message
													code="cobro.busqueda.nroSAP" /></label>
											<div class="controls">
												<input id="nroSAP" name="nroSAP" type="text"
													class="input" value="${descobroBusquedaFiltro.nroSAP}"/>
											</div>
										</div>
									</div>
									<!-- fin de span3 -->
									<!-- Row Error SubtipoCompensacion -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdSubtipoCompensacion" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorNroSAP" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->
										

									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="descobro.busqueda.filtrosTratamientoDiferencia.titulo" /></strong>
									</p>
									
									<!-- FILA 9 -->
									<div class="row">
										<!-- Tipo Tratamiento de Diferencia -->
										<div class="span3">
											<label class="control-label" for="idTratamientoDiferencia">Tipo Tratamiento de Diferencia</label>
											<div class="controls">
												<select id="idTratamientoDiferencia" name="idTratamientoDiferencia" class="input">
													<c:if test="${comboTD}">
														<option value="" selected><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${tratamientoDiferencia}" var="td">
														<c:choose>
															<c:when test="${td.idTipoMedioPagoAsociado eq descobroBusquedaFiltro.idTratamientoDiferencia}">
																<option value="${td.idTipoMedioPagoAsociado}" selected>${td.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${td.idTipoMedioPagoAsociado}">${td.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->

										<!--Nro de Trámite -->
										<div class="span3">
											<label class="control-label" for="refNT"><spring:message code="descobro.busqueda.filtrosNroTramite.titulo" /></label>
											<div class="controls">
												<input id="refNroTramite" name="refNroTramite" maxlength="10" type="text" class="input" value="${descobroBusquedaFiltro.refNroTramite}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 9 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdTratamientoDiferencia" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorRefNroTramite" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
									<!-- FILA 10 -->
									<div class="row">
										<!-- Tipo Sistema Destino -->
										<div class="span3">
											<label class="control-label" for="idTipoSistemaDestino">Sistema Destino</label>
											<div class="controls">
												<select id="idSistemaDestino" name="idSistemaDestino" class="input">
													<c:if test="${comboSD}">
														<option value="" selected><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${sistemaDestino}" var="sd">
														<c:choose>
															<c:when test="${sd.descripcionCorta eq descobroBusquedaFiltro.idSistemaDestino}">
																<option value="${sd.descripcionCorta}" selected>${sd.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${sd.descripcionCorta}">${sd.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
											<div class="controls">
												<select id="sistemaAplicacionManual" name="sistemaAplicacionManual" class="input">
													<c:if test="${comboSistemaAplicacionManual}">
														<option value="" selected><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${sistemaAplicacionManual}" var="sist">
														<option value="${sist.descripcionCorta}">${sist.descripcion}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->

										<!--Nro Acuerdo Facturacion -->
										<div class="span3">
											<label class="control-label" for="refNT"><spring:message
													code="descobro.busqueda.filtrosNroAcuerdoFact.titulo" /></label>
											<div class="controls">
												<input id="refNroAcuerdoFact" name="refNroAcuerdoFact" maxlength="10" type="text"
													class="input" value="${descobroBusquedaFiltro.refNroAcuerdoFact}"/>
											</div>
										</div>
							
										<!-- Codigo Operacion Externo -->
										<div class="span3">
											<label class="control-label" for="codigoOperacionExterna"><spring:message
													code="descobro.busqueda.filtrosCodOpExt.titulo" /></label>
											<div class="controls">
												<input id="codigoOperacionExterna" name="codigoOperacionExterna"  maxlength="50"
													type="text" class="input"
													value="${descobroBusquedaFiltro.codigoOperacionExterna}" />
											</div>
									</div>
								<!-- fin de span3 -->
									</div>
									<!-- fin de row -->

									<!-- FILA 10 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdSistemaAcuerdo" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorRefNroAcuerdoFact" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorCodOpExt" class="error"></span>
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

									<!-- FILA 9 Error General -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorBusqueda" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->

									<!-- TABLA RESULTADOS BUSQUEDA -->
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="descobro.busqueda.resultado.titulo" /></strong>
									</p>
									<div class="row">
										<div class="span9">
											<table id="descobrosEncontrados" class="tablaResultado">
												<thead>
													<th><input type="checkbox" id="checkBoxDescobrosEncontrados" checked /></th>
													<!-- checkbox -->
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-remove -->
													<th></th>
													<!-- icon-historial -->
													<th><spring:message
															code="descobro.busqueda.resultado.idReversionPadre" /></th>
													<!-- Visible false -->
													<th></th>
													<th><spring:message
															code="descobro.busqueda.resultado.idReversion" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.motivoReversion" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.idOperacionCobro" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.cliente" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.estado" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.subestado" /></th>
<%-- 													<th class="datetimeSeconds"><spring:message --%>
<%-- 															code="descobro.busqueda.resultado.fechaUltimoEstado" /></th> --%>
													<th class="dateTimes"><spring:message
															code="descobro.busqueda.resultado.fechaUltimoEstado" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.analista" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.copropietario" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.analistaTeamComercial" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.codigoOperacionExterna" /></th>
													<th><spring:message
															code="descobro.busqueda.resultado.tipoCobro" /></th>
													<th class="importe"><spring:message
															code="descobro.busqueda.resultado.importeTotal" /></th>
													<th class="dateTimes"><spring:message
															code="descobro.busqueda.resultado.fechaAlta" /></th>
													<th class="dateTimes"><spring:message
															code="descobro.busqueda.resultado.fechaDerivacion" /></th>
													<th class="dateTimes"><spring:message
															code="descobro.busqueda.resultado.fechaReversion" /></th>
													
													<th></th>
													<!-- icon-ver -->
													<th></th>
													<!-- icon-edit -->
													<th></th>
													<!-- icon-remove -->
													<th></th>
													<!-- icon-historial -->
												</thead>
											</table>
										</div>
									</div>
									<!-- FIN TABLA RESULTADOS BUSQUEDA -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnRevertir" name="btnRevertir" type="button" disabled>
												<i class="icon-white icon-search"></i>
												Revertir
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
    var mensajeRevertir = '<spring:message code="descobro.busqueda.mensaje.revertir"/>';
    var mensajeRevertirPlural = '<spring:message code="descobro.busqueda.mensaje.revertir.plural"/>';
</script>

