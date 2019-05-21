<%@page import="ar.com.telecom.shiva.presentacion.bean.UsuarioSesion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="Alta de Operaciones Masivas" content="Modelo">
<title><spring:message code="operaciones.masivas.titulo.general" /></title>

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
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>

<script>
//Cambia dinamicamente el contenido de un elemneto y lo agrega a otro
// Nota: Para este metodo el Elemento a cambiar debe tener un solo elemento hijo
// con el id igual al padre mas un sufijo "_Child".
 function intercambiarElementosDinamicamente(idOrigen, idDestino) {
	var elemOrigen = document.getElementById(idOrigen);
	
	if (elemOrigen != null && elemOrigen.firstChild != null){
		var elemOrigenChild = document.getElementById(idOrigen + "_Child");
		var elemDestino = document.getElementById(idDestino);
		
		if (elemOrigenChild!=null) {
			elemOrigen.removeChild(elemOrigenChild);
			
			//Cambio el nombre del id de elemento hijo original al nombre del elemento 
			//destino mas el sufijo _Child, así de esta manera se pueden intercambiabar
			//tantas veces como se requiera entre dos elementos origen y destino.
			elemOrigenChild.setAttribute("id", idDestino + "_Child");
			
			elemDestino.appendChild(elemOrigenChild);
		}
	}
 };
 
function moverBloqueComprobantes(moverSeccionComprobante) {
 
	var origen  = 'bloqueComprobantesCompleto';
	var destino = 'bloqueComprobantesCompleto2';
	
	if (!moverSeccionComprobante) {
		origen  = 'bloqueComprobantesCompleto2';
		destino = 'bloqueComprobantesCompleto';
	}
 	intercambiarElementosDinamicamente(origen, destino);
	if (moverSeccionComprobante) {
		document.getElementById("mostrarAsterisco").style.display = "inline";
	} else {
		document.getElementById("mostrarAsterisco").style.display = "none";
	};
};


<% UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN); %>
var ACTIVARBOTON = <%= userSesion.isActivarBoton()%>

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
							<div id="edicionTitulo" class="title">
								<spring:message code="operaciones.masivas.titulo.pagina.editar" />
							</div>
							<div class="pagos_anticipos">
								<div id="alta-ope-tabs">

									<form:form id="formModificacion" commandName="operacionMasivaDto" enctype="multipart/form-data">
									<input type="hidden" id="operacionMasivaEditable" value="${operacionMasivaEditable}" />
									<input type="hidden" id="volver" name="volver" value="/operacion-masiva-edicion" />
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}" />
									<input type="hidden" id="goBack" name="goBack" />
									<input type="hidden" id="nombreArchivo" name="nombreArchivo" value="${nombreArchivo}" />
									<input type="hidden" id="perfilEsSupervisor" value="${perfilEsSupervisor}" />
									<input type="hidden" id="archivoValidado" value="${archivoValidado}" />
									<form:hidden path="operation"></form:hidden>
									<form:hidden path="ordenComprobanteSelected" id="ordenComprobanteSelected" />
									<form:hidden path="idAnalista"></form:hidden>
<%-- 									<form:hidden path="idCopropietario"></form:hidden> --%>
									<form:hidden path="mantenerComprobantesAdjuntos" id="mantenerComprobantesAdjuntos" />
									<p>
										<strong><spring:message code="operaciones.masivas.datos.operacion.masiva" /></strong>
									</p>
									<div class="row">
										<div class="span3" align="left" id="idOperacionMasivaDIV"  align="left">
											<label class="control-label" for="idOperacionMasiva">Id Operación Masiva</label>
											<div class="controls">
												<input id="idOperacionMasiva" value="${operacionMasivaDto.idOperacionMasiva}" name="idOperacionMasiva" type="text" class="input" readonly />
											</div>
										</div>
										<div class="span6" style="margin-top: 16px" align="right">
											<div>
												<button type="button" class="btn btn-primary btn-small" id="btnHistorial" onclick="javascript:historialOperacionMasiva();">
													<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial
												</button>
												<button type="button" class="btn btn-primary btn-small" id="btnGuardar"  onclick="javascript:edicionOperacionMasivaGuardar();">
													<i class="icon-white icon-ok"></i> Guardar
												</button>
												<button type="button" class="btn btn-primary btn-small"  id="btnVolver"  onclick="javascript:volverBusqueda();">
													<i class="icon-white icon-repeat"></i>Volver
												</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span3" id="estadoOperacionMasivaDIV"  align="left">
											<label class="control-label" for="operacionMasivaEstado">Estado Operación Masiva</label>
											<div class="controls">
												<input id="descripcionEstado" name="descripcionEstado" value="${operacionMasivaDto.descripcionEstado}" type="text" class="input" readonly />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idEmpresa"><span class="rojo">* </span> <spring:message code="boleta.empresa" /></label>
											<div class="controls ">
												<select id="selectEmpresa" name="idEmpresa" class="input">
													<c:if test="${operacionMasivaDto.comboEmpresa}">
														<option value=""><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${empresas}" var="emp">
														<c:choose>
															<c:when test="${emp.idEmpresa eq operacionMasivaDto.idEmpresa}">
																<option value="${emp.idEmpresa}" selected>${emp.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${emp.idEmpresa}">${emp.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idSegmento"><span class="rojo">* </span> <spring:message code="boleta.segmento" /></label>
											<div id="ctrlSegmento" class="controls">
												<select id="selectSegmento" name="idSegmento" class="input">
													<c:if test="${operacionMasivaDto.comboSegmento}">
														<option value=""><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${segmentos}" var="seg">
														<c:choose>
															<c:when test="${seg.idSegmento eq operacionMasivaDto.idSegmento}">
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
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="empresa" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="segmento" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="nombreAnalista"> <spring:message code="operaciones.masivas.nombreAnalista" />
											</label>

											<div class="controls" style="white-space: nowrap;">
												<input class="input" id="nombreAnalista" name="nombreAnalista" type="text" readonly value="${operacionMasivaDto.nombreAnalista}">
											</div>
										</div>

										<div class="span3">
											<label class="control-label" for="idCopropietario"><spring:message code="boleta.copropietario" /></label>
											<div class="controls">
												<select id="selectCopropietario" name="idCopropietario" class="input">
													<c:if test="${operacionMasivaDto.comboCopropietario}">
														<option value=""><spring:message code="combo.seleccionar" /></option>
													</c:if>
													<c:forEach items="${copropietarios}" var="cop">
														<c:choose>
															<c:when test="${cop.tuid eq operacionMasivaFiltro.idCopropietarioEdicion}">
																<option value="${cop.tuid}" selected>${cop.nombreCompleto}</option>
															</c:when>
															<c:otherwise>
																<option value="${cop.tuid}">${cop.nombreCompleto}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idMotivo"><span class="rojo">* </span> <spring:message code="valor.motivo" /></label>
											<div class="controls">
												<select id="selectMotivo" name="idMotivo" class="input">
													<option value=""><spring:message code="combo.seleccionar" /></option>
													<c:forEach items="${motivos}" var="mot">
														<c:choose>
															<c:when test="${mot.idMotivoCobro eq operacionMasivaFiltro.idMotivoEdicion}">
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

									<div class="row rowError">
										<div class="span6"></div>
										<div class="span3">
											<form:errors path="motivo" cssClass="error" />
										</div>
									</div>


									<div class="row">
										<div class="span6">
											<label class="control-label" for="filePreview"><spring:message code="operaciones.masivas.archivo" /></label>
										
											<div class="fileupload fileupload-new" data-provides="fileupload">
											
												<div class="input-append">
													<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
														<span id="nombreArchivoOperacion" class="fileupload-preview"
															style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"><c:out
																value="${operacionMasivaDto.fileNameOperacionMasiva}" /></span>
													</div>
													<span class="btn btn-file btn-primary btn-small"
														style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
														<span class="fileupload-new" <c:if test="${!operacionMasivaDto.fileNameOperacionMasiva.trim().isEmpty()}">style="display: none;" </c:if>><spring:message
																code="conciliacion.seleccionarArchivo" /></span> <span class="fileupload-exists"
														<c:if test="${!operacionMasivaDto.fileNameOperacionMasiva.trim().isEmpty()}">style="display: inline-block;" </c:if>><spring:message
																code="conciliacion.cambiar" /></span> <form:input type="file" path="fileOperacionMasiva"
															onchange="javascript:moverBloqueYLimpiar(this.value);"></form:input>
													</span> <a href="#" class="btn fileupload-exists btn-primary btn-small"
														style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; <c:if test="${!operacionMasivaDto.fileNameOperacionMasiva.trim().isEmpty()}">display: inline-block;</c:if>"
														data-dismiss="fileupload"><spring:message code="conciliacion.eliminar" /></a>
														
														
												</div>
											</div>
											<div class="row rowError">
												<div class="span3">
													<form:errors path="pathArchivo" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									
									<div id="bloqueComprobantesCompleto2">
									</div>
									
									<div class="row" style="margin-top: 15px; margin-bottom: 0px; margin-right: 2px">
										<div align="right" id="btnSubirArchivoEdicionDIV" >
											<button class="btn btn-primary btn-small" id="btnSubirArchivoEdicion" name="btnSubirArchivoEdicion" type="button" disabled
												onclick="javascript:validarArchivoEdicion(this);">
												<i class="icon-white icon-ok"></i>
												<spring:message code="operaciones.masivas.subirArchivo" />
											</button>
											<c:if test="${operacionMasivaDto.duplicado}">
												<br />
												<br />
											</c:if>
										</div>
									</div>

									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="resultadoValidaciones"><spring:message code="operaciones.masivas.resultadoValidaciones" /></label>
											<textarea style="overflow: auto;" class="field span9" id="resultadoValidaciones" name="resultadoValidaciones" readonly>${operacionMasivaDto.resultadoValidaciones}</textarea>
										</div>
									</div>

									<p style="padding: 25px 0px 5px 0px;">
										<strong><spring:message code="operacionesMasivas.archivoOperacionMasiva" /></strong>
									</p>
									<div class="row">
										<div class="span9">
											<table id="resultadoArchivosPendientes" class="tablaResultadoHistorialComprobante">
												<thead>
													<tr>
														<!-- icon-remove -->
														<th></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.idOperacionMasiva" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.nombreArchivo" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.tipoOperacion" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.cantidadRegistros" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.estado" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.importe" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.analista" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.copropietario" /></strong></th>
														<th class="tituloTabla"><strong><spring:message code="operaciones.masivas.motivo" /></strong></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${operacionMasivaDto.archivosPendientes}" var="archivo" varStatus="i" begin="0">
														<tr>
															<td><div id="btnAnular" class="visible-md visible-lg hidden-sm hidden-xs btn-group"><button style="padding:0px;" type="button" class="btn btn-xs btn-link" title="Eliminar" onclick="eliminarOperacionMasivaActual()"><i class="icon-remove bigger-120"></i></button></div></td>
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

															<form:input path="archivosPendientes[${i.index}].idOperacionMasiva" id="idOperacionMasiva${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].nombreArchivo" id="nombreArchivo${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].tipoOperacion" id="tipoOperacion${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].cantRegistros" id="cantRegistros${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].estado" id="estado${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].importe" id="importe${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].nombreAnalista" id="nombreAnalista${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].urlFotoAnalista" id="urlFotoAnalista${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].mailAnalista" id="mailAnalista${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].nombreCopropietario" id="nombreCopropietario${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].mailCopropietario" id="mailCopropietario${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].urlFotoCopropietario" id="urlFotoCopropietario${i.index}" type="hidden" />
															<form:input path="archivosPendientes[${i.index}].motivo" id="motivo${i.index}" type="hidden" />
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>

									<br>

									<div id="bloqueComprobantesCompleto">
										<div id="bloqueComprobantesCompleto_Child">
											<div id="bloqueComprobantesAgregar">
												<p>
													<strong><span class="rojo" id="mostrarAsterisco" style="display: none;">* </span> <spring:message
															code="conciliacion.comprobantes" /></strong>
												</p>

												<div class="row">
													<div class="span3">
														<label class="control-label" for="fileComprobanteModificacion"><spring:message code="conciliacion.adjuntarComprobante" /></label>
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="hidden">
															<div class="input-append">
																<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																	<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																</div>
																<span class="btn btn-file btn-primary btn-small"
																	style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																	<span class="fileupload-new"><spring:message code="conciliacion.seleccionarArchivo" /></span> <span class="fileupload-exists"><spring:message
																			code="conciliacion.cambiar" /></span> <form:input type="file" path="fileComprobanteModificacion" />
																</span> <a href="#" class="btn fileupload-exists btn-primary btn-small"
																	style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload"><spring:message
																		code="conciliacion.eliminar" /></a>
															</div>
														</div>
														<div class="row rowError">
															<div class="span3">
																<form:errors path="comprobanteError" cssClass="error" />
															</div>
														</div>
														<div class="row rowError">
															<div class="span3">
																<form:errors path="comprobantePathVacioModif" cssClass="error" />
															</div>
														</div>
														<div class="row rowError">
															<div class="span3">
																<form:errors path="errorArchivoVacio" cssClass="error" />
															</div>
														</div>

														<div class="row" style="margin-top: 0px; margin-bottom: 5px">
															<div class="span9">
																<label class="control-label" for=""><spring:message code="operaciones.masivas.descripcionComprobante" /></label>
																<textarea class="field span9" id="descripcionComprobante" name="descripcionComprobante">${operacionMasivaFiltro.descripcionComprobante}</textarea>
															</div>
														</div>
														<div class="row rowError">
															<div class="span3">
																<form:errors path="comprobanteDescripcionVacioModif" cssClass="error" />
															</div>
														</div>
														<div class="row" align="right" style="margin-top: 15px; margin-bottom: 15px; width: 699px;">
															<button type="button" class="btn btn-primary btn-small" id="btnAdjuntar" name="btnAdjuntar">
																<i class="icon-white icon-upload"></i>
																<spring:message code="valor.botonAdjuntar" />
															</button>
														</div>
														<br>

													</div>
												</div>
											</div>
											<!-- fin div Bloque comprobantes agregar -->
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
																<c:forEach items="${operacionMasivaDto.listaComprobantes}" var="comprobante" varStatus="i" begin="0">
																	<tr>
																		<td class="registroTabla">${comprobante.nombreArchivo}</td>
																		<td class="registroTabla">${comprobante.descripcionArchivo}</td>
																		<td>
																			<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																				<button id="btnVerComprobante" type="button" class="btn btn-xs btn-link" title="Ver documento adjunto"
																					onclick="abrirComprobante(${comprobante.ordenTabla});">
																					<i class="icon-download-alt bigger-120"></i>
																				</button>
																			</div>
																			<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																				<button id="btnEliminarComprobante" type="button" class="btn btn-xs btn-link" title="Eliminar documento"
																					onclick="eliminarComprobante(${comprobante.ordenTabla});">
																					<i class="icon-trash bigger-120"></i>
																				</button>
																			</div>
																		</td>
																		<form:input path="listaComprobantes[${i.index}].nombreArchivo" id="nombreArchivo${i.index}" type="hidden" />
																		<form:input path="listaComprobantes[${i.index}].descripcionArchivo" id="descripcionArchivo${i.index}" type="hidden" />
																		<form:input path="listaComprobantes[${i.index}].documento" id="documento${i.index}" type="hidden" />
																		<form:input path="listaComprobantes[${i.index}].idComprobante" id="idComprobante${i.index}" type="hidden" />
																		<form:input path="listaComprobantes[${i.index}].ordenTabla" id="ordenTabla${i.index}" type="hidden" />
																	</tr>
																</c:forEach>
																<c:if test="${empty operacionMasivaDto.listaComprobantes}">
																	<tr>
																		<td colspan="3" class="registroTabla" align="left">&nbsp;&nbsp;<spring:message code="mensaje.tabla.vacia" /></td>
																	</tr>
																</c:if>
															</tbody>

														</table>
													</div>

													<c:if test="${operacionMasivaDto.errorComprobanteVacioModif}">
														<div class="row rowError">
															<div class="span9" style="color: red; font-size: 9px;">
																<br>
																<spring:message code="valor.comprobante.vacio" />
															</div>
														</div>
													</c:if>
												</div>
											</div><!-- fin div bloque comprobantes -->
											<c:choose>
												<c:when test="${operacionMasivaDto.moverSeccionComprobante}">
													<script>
														moverBloqueComprobantes(true);
													</script>
												</c:when>
												<c:otherwise>
													<script>
														moverBloqueComprobantes(false);
													</script>
												</c:otherwise>
											</c:choose>
										</div><!-- fin div bloque Comprobantes completo child -->
									</div><!-- fin div bloque Comprobantes completo -->
											
										<div class="row" style="margin-top: 20px;">
											<div id="observacionAnterior">
												<div class="span9">
													<label class="control-label" for="observacionAnterior">Observaciones Anteriores</label>
													<textarea class="field span9" id="observacionAnterior" readonly name="observacionAnterior">${observacionAnterior}</textarea>
												</div>
											</div>
										</div>
										<div class="row" style="margin-top: 15px;" id="prevObservaciones">
											<div class="span9">
												<label class="control-label" for="observacion">Observaciones </label>
												<textarea class="field span9" id="observacion" name="observacion">${observacion}</textarea>
											</div>
										</div>
										<div class="row rowError" style="margin-bottom: 20px;">
											<div class="span6">
												<span id="errorObservaciones" class="error"></span>
											</div>
										</div>
											

										<div class="row">
											<div class="span9" align="right" id="btnGuardarYVolverDIV">
												<div style="margin-right: -10px">
													<button type="button" class="btn btn-primary btn-small" id="btnHistorial2"  onclick="javascript:historialOperacionMasiva();">
														<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial
													</button>
													<button type="button" class="btn btn-primary btn-small" id="btnGuardar2" onclick="javascript:edicionOperacionMasivaGuardar();">
														<i class="icon-white icon-ok"></i> Guardar
													</button>
													<button type="button" class="btn btn-primary btn-small" id="btnVolver"  onclick="javascript:volverBusqueda();" >
														<i class="icon-white icon-repeat"></i>Volver
													</button>
												</div>
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

	</div>
	<!-- end #container -->

	<!-- FIN WEB -->

	<script>	

//Tratamiento de tablas
window.onload = function() {
    $('#observacionAnterior').hide();
	var resultadoArchivosPendientes = 'resultadoArchivosPendientes';
    inicializarTabla(resultadoArchivosPendientes);

	
	//Recordar que cada metodo del controller que invoque esta pagina debe setear esta variable
	if(<c:out value = "${imprimibleArchivo}"/>) {
		window.location ="${pageContext.request.contextPath}/abrir-documento";
	}
	// 	validarCamposParaLaEdicion();
	if($('#perfilEsSupervisor').val() == true || $('#perfilEsSupervisor').val() == "true"){
		habilitarEdicionParcial();	
	} else {
		if (ACTIVARBOTON) {
			$('#btnSubirArchivoEdicion').prop("disabled", false);
		} else {
			$('#btnSubirArchivoEdicion').prop("disabled", true);
		}
	}
				
				
	if (!$.isEmpty($('#observacionAnterior').text())) {
	    $('#observacionAnterior').show();
	    $('#observacion').val("");
	} else {
		$('#observacionAnterior').hide();
	}			
				
};

	
	//CARGA DE COMBOS
	$("#selectEmpresa").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoEmpresaOperacionMasiva";
		$('#formModificacion')[0].submit();
	});
	$("#selectSegmento").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoSegmentoOperacionMasivaEdicion";
		$('#formModificacion')[0].submit();
// 		if ($('#archivoValidado').val() == "true" || $('#archivoValidado').val() == true) {
// 			$('#btnSubirArchivoEdicion').prop("disabled", true);
// 		}
// 		else {
// 			$('#btnSubirArchivoEdicion').prop("disabled", false);
// 		}
	});
	
	function comprobanteObligatorio(){
		 var codigoCliente = $('#cuit').val().split(" ").join("");
		 if(codigoCliente!=""){
			 document.getElementById("mostrarAsterisco").style.display = 'inline';
		 }else{
			 document.getElementById("mostrarAsterisco").style.display = 'none';
		 };
	};
	
	function validarArchivo (referenciaArchivo) {
		 $('#bloqueEspera').trigger('click');
		 $('#operation').val("<%=Constantes.SUBIR_ARCHIVO_OPERACION_MASIVA%>");
		 $('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-operacion-masiva-alta";
		 $('#formModificacion')[0].submit();
	}
	function validarArchivoEdicion (referenciaArchivo) {
		 $('#bloqueEspera').trigger('click');
		 $('#operation').val("<%=Constantes.SUBIR_ARCHIVO_OPERACION_MASIVA%>");
		 $('#operacionMasivaEditable').val(true);
		 $('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-operacion-masiva-alta-edicion";
		 $('#formModificacion')[0].submit();

		
	}
	
	function habilitarEdicionParcial(){
		$('#alta-ope-tabs :input[type!=hidden]').attr('disabled', true);
		$('#alta-ope-tabs :button').attr('disabled', false);
		$('#selectCopropietario').attr('disabled', false);
		$('#selectMotivo').attr('disabled', false);
		$('#prevObservaciones').attr('disabled', false);
		$("#btnAnular").hide();
		$('#btnAdjuntar').attr('disabled', true);
		$('#btnSubirArchivoEdicion').attr('disabled', true);
		$('.fileupload-new .input-append .btn-file').attr('disabled', true);
		$('#observacion').attr('disabled', false);
	};

	
	 $('#btnAdjuntar').click(function() {		    
		    $('#operation').val("<%=Constantes.SUBIR_COMPROBANTE%>");
		    $('#bloqueEspera').trigger('click');
		    $('#formModificacion')[0].action="${pageContext.request.contextPath}/adjuntar-comprobante-operacion-masiva-edicion";
		    $('#formModificacion')[0].submit();
	 });
	 
	 
	 	
	 
	function eliminarComprobante(ordenTablaComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#ordenComprobanteSelected").val(ordenTablaComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/operacion-masiva-procesar-eliminar-comprobante-edicion";
		$('#formModificacion')[0].submit();
	};
	
	function abrirComprobante(ordenTablaComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#ordenComprobanteSelected").val(ordenTablaComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/operacion-masiva-procesar-abrir-comprobante-edicion";
		$('#formModificacion')[0].submit();
	};
	
	function eliminarOperacionMasivaActual() {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/operacion-masiva-setear-archivo-validado";
		$('#formModificacion')[0].submit();
	};
	
	// u579607
	var historialOperacionMasiva = function () {
		
	    $('#bloqueEspera').trigger('click');
	    
		var operacionMasiva = {
			idSegmento : $('#selectSegmento').val(),
			idCopropietarioEdicion : $('#selectCopropietario').val(),
		    idMotivoEdicion : $('#selectMotivo').val(),
		    descripcionComprobante : $('#descripcionComprobante').val(),
		    observaciones : $('#observacion').val()
	    };

	    $.ajax({
			"type" : "POST",
			"url": "operacion-masiva/guardoOperacionMasivaEnSesion",
			"dataType": "json",
			"data": JSON.stringify(eval(operacionMasiva)),
			"contentType": "application/json",
			"mimeType": "application/json",
			"success" : function(result) {
			    if (result.success) {
			    	irHistorial();
			    }
			    
			    setTimeout(function() {
					ocultarBloqueEspera();
			    }, 3000);
			}   
	    });
	};
	


	function irHistorial () {
// 		$('#idOperacionMasiva').val(idOperacionMasiva);
		$('#formModificacion')[0].action=urlAsynchronousHTTP+"/operacion-masiva-historial";
		$('#formModificacion')[0].submit();
	};
	
	function edicionOperacionMasivaGuardar() {
		
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action=urlAsynchronousHTTP+"/operacion-masiva-edicion-guardar";
		$('#formModificacion')[0].submit();
	}
	
	function volverBusqueda() {
		$('#goBack').val("true");
		$('#formModificacion')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
		$('#formModificacion')[0].submit();
	};
	
	function moverBloque(path){
		var nombre = path.split("\\")[path.split("\\").length-1];
		moverBloqueComprobantes(nombre.split("\_")[0].toUpperCase() == "DSIST");
	};
	
	function moverBloqueYLimpiar(path){
		moverBloque(path);
		document.getElementById("resultadoValidaciones").value = "";
	};
	
	
	function checkComprobantes(){
	    if ($("#mantenerComprobantesAdjuntosOM:checked").val()){
			$("#mantenerComprobantesAdjuntos").val(true);
		} else {
			$("#mantenerComprobantesAdjuntos").val(false);
		}
	}
	
</script>

</body>
</html>