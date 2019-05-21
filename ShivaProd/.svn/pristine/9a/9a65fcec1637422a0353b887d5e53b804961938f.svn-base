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

<title><spring:message code="cobro.busqueda.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>

<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/shiva-busqueda-cobro.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">

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
	var idAnalista = '${cobroBusquedaFiltro.idAnalista}';
	var referentesCobranza = ${referentesCobranza};
	var cobroFiltroReferenteCobranza = '${cobroBusquedaFiltro.referenteCobranza}';
	var referentesCaja = ${referentesCaja};
	var cobroFiltroReferenteCaja = '${cobroBusquedaFiltro.referenteCaja}';
	var referentesOperacionesExternas = ${referentesOperacionesExternas};
	var cobroFiltroReferenteOperacionesExternas = '${cobroBusquedaFiltro.referenteOperacionesExternas}';
	var aprobadoresOperacionesEspeciales = ${aprobadoresOperacionesEspeciales};
	var cobroFiltroReferenteOperacionesEspeciales = '${cobroBusquedaFiltro.aprobadorOperacionesEspeciales}';
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
								<spring:message code="cobro.busqueda.titulo" />
							</div>

							<div class="pagos_anticipos">
								<form:form id="formBusqueda" commandName="cobroDto">
									<input type="hidden" id="idCobro" name="idCobro"/>
									<input type="hidden" id="idCobroPadre" name="idCobroPadre"/>
									<input type="hidden" id=idOperacionFormateado name="idOperacionFormateado"/>
									<input type="hidden" id=nombreArchivo name="nombreArchivo"/>
									<input type="hidden" id="opcion" name="opcion"/>
									<input type="hidden" id="goBack" name="goBack"/>
									<input type="hidden" id="volver" name="volver" value="/cobro-busqueda"/>
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
									<input type="hidden" id="idAltaEdicion" name="idAltaEdicion" value="A" />
									<input type="hidden" id="volverAPantalla" name="volverAPantalla" />
									<input type="hidden" id="idsDesapropiar" name="idsDesapropiar" />
									<input type="hidden" id="idsOperacion" name="idsOperacion" />
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosCobros.titulo" /></strong>
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
														<c:when test="${seg.idSegmento eq cobroBusquedaFiltro.idSegmento}">
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
											<label class="control-label" for="idEstado"><spring:message
													code="recibo.estado" /></label>
											<div class="controls">
												<select id="idEstado" name="idEstado" class="input">
													<option value=""><spring:message
															code="combo.seleccionar" /></option>
													<c:forEach items="${estados}" var="est">
														<c:choose>
															<c:when test="${est eq cobroBusquedaFiltro.idEstado}">
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
															<c:when test="${mot.idMotivoCobro eq cobroBusquedaFiltro.idMotivo}">
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

										<!-- Id Operacion Cobro -->
										<div class="span3">
											<label class="control-label" for="idOpCobro"><spring:message
													code="cobro.busqueda.idOperacionCobro" /></label>
											<div class="controls">
												<input id="idOpCobro" name="idOpCobro" maxlength="7"
													type="text" class="input" value="${cobroBusquedaFiltro.idOpCobro}"/>
											</div>
										</div>
										<!-- fin de span3 -->

										<!-- Fecha Desde -->
										<div class="span3">
											<label class="control-label" for="fechaDesde"><spring:message
													code="recibo.fechaDesde" /></label>
											<div class="controls">
												<input id="fechaDesde" name="fechaDesde" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${cobroBusquedaFiltro.fechaDesde}"/>
											</div>
										</div>

										<!-- Fecha Hasta -->
										<div class="span3">
											<label class="control-label" for="fechaHasta"><spring:message
													code="recibo.fechaHasta" /></label>
											<div class="controls">
												<input id="fechaHasta" name="fechaHasta" maxlength="16"
													data-date-format="dd/mm/yyyy" type="text" class="input" value="${cobroBusquedaFiltro.fechaHasta}" />
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 3 Error -->
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

									<!-- FILA 4 -->
									<div class="row">
									
									<!-- Id Reversion -->
										<div class="span3">
											<label class="control-label" for="idReversion"><spring:message
													code="cobro.busqueda.idReversion" /></label>
											<div class="controls">
												<input id="idOperacionReversion" name="idOperacionReversion" maxlength="16"
													type="text" class="input" value="${cobroBusquedaFiltro.idOperacionReversion}"/>
											</div>
										</div>
									<!-- fin de span3 -->
									
										<!-- Analista -->
										<div class="span3">
											<label class="control-label" for="idAnalista">
												<spring:message code="valor.analista" />
											</label>
											<div class="controls">
												<select id="selectAnalista" name="analista" class="input">
												</select>

											</div>
										</div>
										<!-- fin de span3 -->
										
										<!-- Id Operacion Masiva -->
										<div class="span3">
											<label class="control-label" for="idOperacionMasiva"><spring:message
													code="cobro.busqueda.idOperacionMasiva" /></label>
											<div class="controls">
												<input id="idOperacionMasiva" name="idOperacionMasiva" maxlength="7"
													type="text" class="input" value="${cobroBusquedaFiltro.idOperacionMasiva}"/>
											</div>
										</div>
									<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 4 Error -->
									
									<div class="row rowError">
										<div class="span3">
											<span id="errorIdReversion" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdAnalista" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorIdOperacionMasiva" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									
									<!-- FILA 5 -->
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
																<c:when test="${criterioBusquedaCliente.nombre eq cobroBusquedaFiltro.selectCliente}">
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
															class="input" style="margin: 0 auto;" value="${cobroBusquedaFiltro.textCliente}"/>
													</div>
												</div>
											</div>
											<!-- fin de row -->
									
											<!-- FILA 5 Error -->
											
											<div class="row rowError">
												<div class="span3">
													<span id="#errorSelectCriterio" class="error"></span>
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

									<!-- FILA 6 -->
									<div class="row">

										<!-- Numero Documento -->
										<div class="span3">
											<label class="control-label" for="nroDocumento"><spring:message
													code="cobro.busqueda.nroDocumento" /></label>
											<div class="controls">
												<input id="nroDocumento" name="nroDocumento" maxlength="15"
													type="text" class="input" value="${cobroBusquedaFiltro.nroDocumento}"/>
											</div>
										</div>
										<!-- fin de span3 -->
										
										

										<!-- Numero de Referencia Mic -->
										<div class="span3">
											<label class="control-label" for="nroRef"><spring:message
													code="cobro.busqueda.numeroRef" /></label>
											<div class="controls">
												<input id="nroRef" name="nroRef" maxlength="15"
													type="text" class="input" value="${cobroBusquedaFiltro.nroRef}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 6 Error -->
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

									<!-- FILA 7 -->
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
															<c:when test="${mp.valor eq cobroBusquedaFiltro.idTipoMedioPago}">
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
													class="input" value="${cobroBusquedaFiltro.refMP}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 7 Error -->
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
															<c:when test="${stcomp.codigo eq cobroBusquedaFiltro.idSubtipoCompensacion}">
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
													class="input" value="${cobroBusquedaFiltro.nroSAP}"/>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->

									<!-- FILA 8 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdSubtipoCompensacion" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorNroSAP" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->


									<p style="padding: 6px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosTratamDif.titulo" /></strong>
									</p>

									<!-- FILA 9 -->
									<div class="row">
										<!-- span 3 -->
										<!-- tipo tratamiento dif -->
										<div class="span3">
											<label class="control-label" for="idTipoTratamiento">Tratamiento
												de Diferencia</label>
											<div class="controls">
												<select id="idTipoTratamiento" name="idTipoTratamiento"
													class="input">
													<c:if test="${comboTipoTratamiento}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>

													<c:forEach items="${tipoTratamiento}" var="tipotratam">
														<c:choose>
															<c:when
																test="${tipotratam.name eq cobroBusquedaFiltro.tipoTratamientoDiferencia}">
																<option value="${tipotratam.name}" selected>${tipotratam.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${tipotratam.name}">${tipotratam.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->
										<!-- numero tramite -->
										<div class="span3" id="divNroTramite">
											<label class="control-label" for="nroTramite"><spring:message
													code="cobro.busqueda.nroTramite" /></label>
											<div class="controls">
												<input id="idNroTramite" name="idNroTramite" type="text"
													class="input" value="${cobroBusquedaFiltro.nroTramite}" />
											</div>
										</div>
										<!-- fin de span3 -->
									</div>

									<!-- fin de row -->
									<!-- FILA 9 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorIdTipoTratamiento" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorNroTramite" class="error"></span>
										</div>

									</div>
									<!-- fin de rowError -->


									<!-- FILA 10 -->
									<div class="row">
										<!-- span 3 -->
										<!-- Sistema Destino -->
										<div class="span3" id="divSistemaDestino">
											<label class="control-label" for="sistemaDestino">Sistema
												Destino</label>
											<div class="controls">
												<select id="idSistemaDestino" name="idSistemaDestino"
													class="input">
													<c:if test="${comboSistemaDestino}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>

													<c:forEach items="${tipoSistemaDestino}" var="sistDestino">
														<c:choose>
															<c:when
																test="${sistDestino.descripcionCorta eq cobroBusquedaFiltro.sistemaDestino}">
																<option value="${sistDestino.descripcionCorta}" selected>${sistDestino.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${sistDestino.descripcionCorta}">${sistDestino.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										
										<div class="span3" id="divSistemaDestinoAplicacionManual">
											<label class="control-label" for="sistemaDestinoAplicacionManual">Sistema
												Destino</label>
											<div class="controls">
												<select id="idSistemaDestinoAplicacionManual" name="idSistemaDestinoAplicacionManual"
													class="input">
													<c:if test="${comboSistemaDestinoAplManual}">
														<option value="" selected><spring:message
																code="combo.seleccionar" /></option>
													</c:if>

													<c:forEach items="${tipoSistemaDestinoAplManual}" var="sistDestino">
														<c:choose>
															<c:when
																test="${sistDestino.descripcionCorta eq cobroBusquedaFiltro.sistemaDestino}">
																<option value="${sistDestino.descripcionCorta}" selected>${sistDestino.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${sistDestino.descripcionCorta}">${sistDestino.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<!-- fin span3 -->

										<!-- numero acuerdo facturacion -->
										<div class="span3" id="divNroAcuerdoFact">
											<label class="control-label" for="nroAcuerdoFact"><spring:message
													code="cobro.busqueda.nroAcuerdoFact" /></label>
											<div class="controls">
												<input id="idNroAcuerdoFact" name="idNroAcuerdoFact"
													type="text" class="input"
													value="${cobroBusquedaFiltro.nroAcuerdoFact}" />
											</div>
										</div>
											<!-- codigo operacion externa -->
										<div class="span3" id="divCodOpExtAplicacionManual">
											<label class="control-label" for="codigoOperacionExterna"><spring:message
													code="cobro.busqueda.codigoOperacionExterna" /></label>
											<div class="controls">
												<input id="idCodOpExt" name="idCodOpExt"
													type="text" class="input"
													value="${cobroBusquedaFiltro.codigoOperacionExterna}" />
											</div>
										</div>
										<!-- fin de span3 -->
									</div>
									<!-- fin de row -->
									<!-- FILA 10 Error -->
									<div class="row rowError">

										<div class="span3">
											<span id="errorSistemaDestino" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorNroAcuerdoFact" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorCodOpExt" class="error"></span>
										</div>
									</div>
									<!-- fin de rowError -->
									<!-- FILA 11 -->
									<p style="padding: 6px 0px;">
										<strong><spring:message	code="cobro.busqueda.filtrosAprobadores.titulo" /></strong>
									</p>
									<div class="row">
										<!-- Referente De Cobranza -->
										<div class="span3">
											<label class="control-label" for="idReferenteCobranza">
												<spring:message code="cobro.busqueda.referenteCobranza" />
											</label>
											<div class="controls">
												<select id="selectReferenteCobranza" name="referenteCobranza" class="input"></select>
											</div>
										</div>
										<!-- Referente De Caja -->
										<div class="span3">
											<label class="control-label" for="idReferenteCaja">
												<spring:message code="cobro.busqueda.referenteCaja" />
											</label>
											<div class="controls">
												<select id="selectReferenteCaja" name="referenteCaja" class="input"></select>
											</div>
										</div>
										<!-- Referente De Operaciones Externas-->
										<div class="span3">
											<label class="control-label" for="idReferenteOperacionesExternas">
												<spring:message code="cobro.busqueda.referenteOperacionesExternas" />
											</label>
											<div class="controls">
												<select id="selectReferenteOperacionesExternas" name="referenteOperacionesExternas" class="input"></select>
											</div>
										</div>
										<!-- fin de span3 -->

									</div>
									<!-- fin de row -->
									<!-- FILA 11 Error -->
									<!-- FILA 12 -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorSelectReferenteCobranza" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorSelectReferenteCaja" class="error"></span>
										</div>
										<div class="span3">
											<span id="errorSelectReferenteOperacionesExternas" class="error"></span>
										</div>
									</div>
									<div class="row">
										<!-- Aprobador De Operaciones Especiales -->
										<div class="span3" style="padding: 10px 0px;">
											<label class="control-label" for="idAprobadorOperacionesEspeciales">
												<spring:message code="cobro.busqueda.aprobadorOperacionesEspeciales" />
											</label>
											<div class="controls">
												<select id="selectAprobadorOperacionesEspeciales" name="aprobadorOperacionesEspeciales" class="input"></select>
											</div>
										</div>
									</div>
									<!-- fin de row -->
									<!-- FILA 12 Error -->
									<div class="row rowError">
										<div class="span3">
											<span id="errorSelectAprobadorOperacionesEspeciales" class="error"></span>
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
												code="cobro.busqueda.resultado.titulo" /></strong>
									</p>
									<div class="row">
										<div class="span9">
											<table id="cobrosEncontrados" class="tablaResultado">
												<thead>
													<th><input type="checkbox" id="seleccionarTodos" disabled/></th>
													<!-- CheckBox -->
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
													<th><spring:message code="cobro.busqueda.resultado.idOperacionCobro" /></th>
													<th><spring:message code="cobro.busqueda.resultado.motivoCobro" /></th>
													<th><spring:message code="cobro.busqueda.resultado.cliente" /></th>
													<th><spring:message code="cobro.busqueda.resultado.estado" /></th>
													<th><spring:message code="cobro.busqueda.resultado.subestado" /></th>
													<th class="datetimeSeconds"><spring:message code="cobro.busqueda.resultado.fechaUltimoEstado" /></th>
													<th><spring:message code="cobro.busqueda.resultado.analista" /></th>
													<th><spring:message code="cobro.busqueda.resultado.copropietario" /></th>
													<th><spring:message code="cobro.busqueda.resultado.analistaTeamComercial" /></th>
													<th><spring:message code="cobro.busqueda.resultado.codigoOperacionExterna" /></th>
													<th><spring:message code="cobro.busqueda.resultado.tipoCobro" /></th>
													<th><spring:message code="cobro.busqueda.resultado.idReversionPadre" /></th>
													<th><spring:message code="cobro.busqueda.resultado.idReversion" /></th>
													<th class="importe"><spring:message code="cobro.busqueda.resultado.importeTotal" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.fechaAlta" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.fechaDerivacion" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.fechaAprobacionReferente" /></th>
													<th><spring:message	code="cobro.busqueda.resultado.referenteCobranzas" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.fechaAprobacionOperacionesEspeciales" /></th>
													<th><spring:message	code="cobro.busqueda.resultado.aprobadorOperacionesEspeciales" /></th>
													<th class="dateTimes"><spring:message	code="cobro.busqueda.resultado.fechaAprobacionReferenteCaja" /></th>
													<th><spring:message	code="cobro.busqueda.resultado.referenteCaja" /></th>
													<th class="dateTimes"><spring:message	code="cobro.busqueda.resultado.fechaAprobacionOperacionesExternas" /></th>
													<th><spring:message	code="cobro.busqueda.resultado.referenteOperacionesExternas" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.fechaImputacion" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.idOperacionMasiva" /></th>
													<th class="dateTimes"><spring:message code="cobro.busqueda.resultado.nombreArchivoOpMas" /></th>
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
												</thead>
											</table>
										</div>
									</div>
									<div class="row rowError">
											<div class="span9">
												<span id="errorAlRevertirCobro" class="error"></span>
											</div>
									</div>
									<!-- FIN TABLA RESULTADOS BUSQUEDA -->
									<!-- BTN DESAPROPIAR -->
									<div class="row" style="margin: 25px 30px;">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnDesapropiar"
												name="btnDesapropiar" type="button" disabled>
												<spring:message code="cobro.busqueda.btn.desapropiar" />
											</button>
<%-- 											<c:if test="${not empty caminoDeVuelta}"> --%>
<!-- 												<button type="button" class="btn btn-primary btn-small" id="btnVolver"> -->
<!-- 												<i class="icon-white icon-repeat"></i>Volver -->
<!-- 											</button> -->
<%-- 											</c:if> --%>
										</div>
									</div>
									<!--FIN BTN DESAPROPIAR -->
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
	$('#volverAPantalla').val('<%=Constantes.DESTINO_BUSQUEDA_COBRO%>');
	var mensajeAnular = '<spring:message code="cobro.busqueda.mensaje.anular.ok"/>';
	var mensajeDesapropiar = '<spring:message code="cobro.busqueda.mensaje.desapropiar"/>';
	var mensajeDesapropiarPlural = '<spring:message code="cobro.busqueda.mensaje.desapropiar.plural"/>';
	var VUELVO_BANDEJA = "${caminoDeVuelta}";
	var cobroFiltroAnalista = "${cobroBusquedaFiltro.idAnalista}";
	var volviendoABusqueda = <c:out value = "${volviendoABusqueda}"/>;
</script>