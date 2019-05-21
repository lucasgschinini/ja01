<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>



<title><spring:message code="titulo.nombre.app" />&nbsp;-&nbsp;
	
	<c:choose>
		<c:when test="${not empty tipoTareaName and tipoTarea == tipoTareaName}">
			<spring:message code="cobro.info.desapropiacion.CobroManual.title" />
		</c:when>
		<c:otherwise>
			<spring:message code="cobro.AprobacionCobroManual.title" />
		</c:otherwise>
	</c:choose>
</title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/cobro-confirmar-aplicacion-manual.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">

<style type="text/css">
    
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

.customPaginate_button.disabled, .customPaginate_button.disabled:hover, .customPaginate_button.disabled:active {
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
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #006dcc), color-stop(100%, #0044cc));
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
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #0044cc), color-stop(100%, #006dcc));
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
.ui-widget-content .ui-state-focus{
	border: 1px solid #1E90FF;
	background: #1E90FF;
 	border-radius: 0px;
 	color: #FFFFFF;
	position: relative;
 	text-indent: 1px;
 	width: 110%;
 	left: -1px;
}
</style>

<script>
	// Compensaciones Sap
	var T_RENGLON_AGRUPADOR = '<%=TipoRenglonSapEnum.AGRUPADOR.name()%>';
	var T_RENGLON_CAP = '<%=TipoRenglonSapEnum.CAP.name()%>';
	var T_RENGLON_REF = '<%=TipoRenglonSapEnum.REF.name()%>';
	var T_RENGLON_FIN = '<%=TipoRenglonSapEnum.FIN.name()%>';
	var APLI_MANUAL_CONF = '<%=MotivoAdjuntoEnum.APLI_MANUAL_CONF.name()%>';
	var MONEDA_OPERACION = '${monedaOperacion}';
	var TIPOTAREANAME = '${tipoTareaName}';
	var TIPOTAREA = '${tipoTarea}';
	var SISTEMA = '${sistema}';
	var REINTEGRO_CRED_PROX_FAC_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento()%>';
	var DEBITO_PROX_FAC_DESC = '<%=TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion()%>';
	//fin constantes para js
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
							<c:choose>
								<c:when test="${not empty tipoTareaName and tipoTarea == tipoTareaName}">
									<div class="title" id="titleAprobacion"><spring:message code="cobro.info.desapropiacionCobroManual.title"/></div>
								</c:when>
								<c:otherwise>
									<div class="title" id="titleAprobacion"><spring:message code="cobro.AprobacionCobroManual.title"/></div>
								</c:otherwise>
							</c:choose>

							<div class="pagos_anticipos">
							<form:form id="formCobro" commandName="cobroDto"
 								action="${pageContext.request.contextPath}/cobro-confirmar-aplicacion-manual"> 
 								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
								<input type="hidden" name="detalleDoAprobacionA" id="detalleDoAprobacionA" value="${detalleDoAprobacionA}"/>
								<input type="hidden" name="vuelvoBandeja" id="vuelvoBandeja" value="${vuelvoBandeja}"/>
								<input type="hidden" name="sistema" id="sistema" value="${sistema}"/>
								<input type="hidden" name="sociedad" id="sociedad" value="${sociedad}"/>
								<input type="hidden" name="volverAPantalla" id="volverAPantalla" value="${volverAPantalla}"/>
								<input type="hidden" id="idCobroFormatiado" name="idCobroFormatiado" value="${idCobroFormatiado}"/>
								<input type="hidden" id="idOperacionFormateado" name="idOperacionFormateado" value="${idOperacion}"/>
								<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}"/>
								<input type="hidden" id="idCobroPadre" name="idCobroPadre" value="${idCobroPadre}"/>
								<input type="hidden" id="idAnalista" name="idAnalista" value="${idUsuario}"/>
								<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
								<input type="hidden" id="volviendoA" value="${volviendoA}" />
								<input type="hidden" id="volver" name="volver" value="/cobro-confirmar-aplicacion-manual" />
								<input type="hidden" id="opcion" name="opcion" value="D"/>
								<input type="hidden" id="goBack" name="goBack" />
								<input type="hidden" id="idDescobro" name="idDescobro" value="${idDescobro}" />
								<input type="hidden" id="idLegajo" name="idLegajo" value="${idLegajo}" />
								<input type="hidden" id="idLeg" name="idLeg" value="${idLegajo}" />
								<input type="hidden" id="solapa" name="solapa" value="${solapa}" />
								<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" value="${idOperacionRelacionada}" />
								<input type="hidden" id="monedaOperacion" name="monedaOperacion" value="${monedaOperacion}" />
								<input type="hidden" id="observacion" name="observacion" />
								<input type="hidden" id="sistemaDestinoDescripcion" name="sistemaDestinoDescripcion" />
								<input type="hidden" id="idTarea" name="idTarea" value="${idTarea}" />
								<input type="hidden" id="tipoTarea" name="tipoTarea" value="${tipoTarea}" />
							
							</form:form>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idOperacion">Id Operación Cobro</label>
											<div class="controls">
												<input id="idOperacion" name="idOperacion" type="text" class="input" value="${idOperacion}" readonly />
											</div>
										</div>
										<div class="span6" align="right" style="margin-top: 17px">
											<c:if test="${idOperacionMasiva != null}">
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnOpMas" onclick="javascript:verOperacionMasiva();">
												Ver Operación Masiva</button>
											</c:if>
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnHistorial" onclick="javascript:historialCobro();">
												<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial Cobro
												</button>
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnExportar" onclick="javascript:exportarDetalle();">
												<i class="icon-white icon-download-alt"></i> Exportar Cobro a Excel
												</button>
											
										</div>
									</div>
									
									<div class="row" style="margin-top: 10px">
										<div class="span3" align="left" id="divEstado">
											<label class="control-label" for="cobroEstado">Estado Cobro</label>
											<div class="controls">
												<input text id="cobroEstado" name="cobroEstado" type="text" value="${estadoCobro}" class="input" readonly />
											</div>
										</div>
								
										<c:if test="${marcaEstado != ''}">
										<div class="span6" align="left" id="divCobroSubEstados">
											<label class="control-label" for="cobroSubEstados">Sub Estado Cobro</label>
											<div class="controls">
												<input text id="cobroSubEstados" name="cobroSubEstados" value="${marcaEstado}" type="text" class="input span6" readonly />
											</div>
										</div>
										</c:if>
										<c:if test="${idOperacionMasiva != null}">
											<div class="span3" align="left">
												<label class="control-label" for="cobroEstado">ID
													Operación Masiva</label>
												<div class="controls">
													<input text id="idOperacionMasiva" name="idOperacionMasiva"
														type="text" value="${idOperacionMasiva}" class="input"
														readonly />
												</div>
											</div>
											<div class="span3" align="left">
												<label class="control-label" for="cobroEstado">Nombre
													Archivo</label>
												<div class="controls">
													<input text id="nombreArchivo"
														name="nombreArchivo" type="text"
														value="${nombreArchivo}" class="input" readonly />
												</div>
											</div>
										</c:if>
										
										
										<c:if test="${mostrarBtnCartaYResumen}">
											<div class="span6" align="right" >
											
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnCarta" onclick="javascript:exportarDetalle();">
													<i class="icon-white icon-download-alt"></i> Exportar Carta
													</button>
												
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnResumen" onclick="javascript:exportarDetalle();">
													<i class="icon-white icon-download-alt"></i> Exportar Resumen
													</button>
											</div>
										</c:if>
										<c:if test="${mostrarBtnResumen}">
											<div class="span6" align="right" >
												<button type="button" align="right" class="btn btn-primary btn-small" id="btnResumen" onclick="javascript:exportarDetalle();">
													<i class="icon-white icon-download-alt"></i> Exportar Resumen
											</div>
										</c:if>
										
										
									</div>
									<div class="row">
										<div class="span8">
											<div id="alertErrorConfirmacionManual1" class="alert alert-error" align="center" style="display: none;width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
												<button id="btnCruzErrorConfirmacionManual1" class="close" >×</button><strong><spring:message code="error.error"/></strong><br/>
												<span id="errorConfirmacionManual1"></span>
											</div>
										</div>
									</div>
									<div id="conf-cobro-tabs">
									<div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="prevEmpresa">Empresa</label>
												<div class="controls">
													<input id="prevEmpresa" name="prevEmpresa"
														type="text" class="input" readonly
														value="${prevEmpresa}"/>
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevSegmento">Segmento</label>
												<div class="controls">
													<input id="prevSegmento" name="prevSegmento"
														type="text" class="input" readonly
														value="${prevSegmento}"/>
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevMonedaOperacion"><spring:message code="conf.cobro.moneda.operacion" /></label>
												<div class="controls">
													<input id="prevMonedaOperacion" name="prevMonedaOperacion"
															type="text" class="input" readonly
															value="${prevMonedaOperacion}"/>
												</div>
											</div>
										</div>										
										<div class="row">
											<div class="span3">
												<label class="control-label" for="prevAnalista">Analista</label>
												<div class="controls">
													<input id="prevAnalista" name="prevAnalista"
														type="text" class="input" readonly
														value="<c:out value = "${nombreCompletoUsuario}"/>"/>
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevCopropietario">Copropietario</label>
												<div class="controls">
													<input id="prevCopropietario" name="prevCopropietario"
														type="text" class="input" readonly
														value="${prevCopropietario}"/>
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevMotivo">Motivo</label>
												<div class="controls">
													<input id="prevMotivo" name="prevMotivo"
														type="text" class="input" readonly
														value="${prevMotivo}"/>
												</div>
											</div>
										</div>
									
										<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="conf.cobro.clientes.seleccionados"/></strong></p>
										<div class="row">
											<div class="span9">
												<table id="prevClientes" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaClientes}" var="cli" varStatus="i">
															<tr>
																<td></td>
																<td>${cli.cuit}</td>
																<td>${cli.idClienteLegado}</td>
																<td>${cli.empresasAsociadas}</td>
																<td>${cli.razonSocial}</td>
																<td>${cli.origen}</td>
																<td>${cli.segmentoAgencia}</td>
																<td>${cli.codigoHolding}</td>
																<td>${cli.agenciaNegocio}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong>Listado de débitos seleccionados</strong></p>
										<div class="row">
											<div class="span9">
												<table id="prevDebitos" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th>F. Vencimiento</th>
															<th>Saldo 1er Vto. Moneda Origen</th>
															<th>Moneda</th>
															<th>Importe al 1er Vto.</th>
															<th>Importe al 2do Vto.</th>
															<th>Saldo pesificado F. Emisión</th>
															<th>Cobrar al 2do Venc.</th> <!-- Editable checkbox -->
															<th>Destransferir 3ros</th> <!-- Editable checkbox -->
															<th>Importe a cobrar</th> <!-- Editable text -->
															<th>Estado Cptos. de 3ros</th>
															<th>Importe 3ros Transferidos</th>
															<th>Estado origen</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/compensación en proceso</th>
															<th>Op. Asoc. + Analista</th>
															<th>Tipo de Cambio</th>
															<th>F. Emisión</th>
															<th>Sin Dif. de Cambio</th> <!-- Editable checkbox -->
															<th>Nro. Convenio</th>
															<th>Cuota Convenio</th>
															<th>F. ult. pago parcial</th>
															<th>Resultado validación</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaDebitos}" var="deb" varStatus="i">
															<tr>
															<td></td>
															<td>${deb.cliente}</td>
															<td>${deb.cuenta}</td>
															<td>${deb.sistemaOrigen.descripcion}</td>
															<td>${deb.tipoDoc}</td>
															<td>${deb.subtipoDocumento}</td>
															<td>${deb.nroDoc}</td>
															<td>${deb.numeroReferenciaMic}</td>
															<td>${deb.fechaVenc}</td>
															<td>${deb.saldo1erVencMonOrigen}</td>
															<td>${deb.moneda}</td>
															<td>${deb.imp1erVenc}</td>
															<td>${deb.imp2doVenc}</td>
															<c:choose>
																<c:when test="${deb.moneda != monedaOperacion}">
																	<td>${deb.saldoPesificado}</td>
																</c:when>
																<c:otherwise>
																	<td>-</td>
																</c:otherwise>
															</c:choose>
															<td>
																<c:choose>
																	<c:when test="${deb.cobrarAl2doVenc}">
																			<input type="checkbox" class="opcion" id="cobrarAl2doVenc" name="cobrarAl2doVenc" value="${deb.cobrarAl2doVenc}" checked disabled />
																	</c:when>
																	<c:otherwise>
																			<input type="checkbox" class="opcion" id="cobrarAl2doVenc" name="cobrarAl2doVenc" value="${deb.cobrarAl2doVenc}" disabled />
																	</c:otherwise>
																</c:choose>
															</td>
															<td>
																<c:choose>
																	<c:when test="${deb.destransferir3ros}">
																			<input type="checkbox" class="opcion" id="destransferir3ros" name="destransferir3ros" value="${deb.destransferir3ros}" checked disabled />
																	</c:when>
																	<c:otherwise>
																			<input type="checkbox" class="opcion" id="destransferir3ros" name="destransferir3ros" value="${deb.destransferir3ros}" disabled />
																	</c:otherwise>
																</c:choose>
															</td>
															<td>${deb.impACobrar}</td>
															<td>${deb.estadoCptosDe3ros}</td>
															<td>${deb.imp3rosTransf}</td>
															<td>${deb.estadoOrigen}</td>
															<td>${deb.marcaReclamoDescripcion}</td>
															<td>${deb.migradoOrigen}</td>
															<td>${deb.estadoDeimos}</td>
															<td>${deb.marcaPagoCompensacionEnProcesoShiva}</td> 
															<td>${deb.opeAsocAnalista}</td>
															<c:choose>
																<c:when test="${deb.moneda != monedaOperacion}">
																	<td>${deb.tipoCambio}</td>
																</c:when>
																<c:otherwise>
																	<td>-</td>
																</c:otherwise>
															</c:choose>
															<td>${deb.fechaEmision}</td>
															<td>
																<c:choose>
																	<c:when test='${"CALIPSO" == deb.sistemaOrigen && 
																			  (deb.tipoDoc == "Factura" || deb.tipoDoc == "Nota de Debito" 
																			|| deb.tipoDoc == "Nota de Crédito" || deb.tipoDoc == "Pago a Cuenta")
 																			&& deb.moneda != monedaOperacion && idMotivoCobro == "9"}'>
																		<c:choose>
																			<c:when test="${deb.sinDifDeCambio}">
																				<input type="checkbox" class="opcion" id="sinDifDeCambio" name="sinDifDeCambio" value="${deb.sinDifDeCambio}" checked disabled />
																			</c:when>
																			<c:otherwise>
																				<input type="checkbox" class="opcion" id="sinDifDeCambio" name="sinDifDeCambio" value="${deb.sinDifDeCambio}" disabled />
																			</c:otherwise>
																		</c:choose>
																	</c:when>
																	<c:otherwise>-</c:otherwise>
																</c:choose>
															</td>
															<td>${deb.nroConvenio}</td>
															<td>${deb.cuota}</td>
															<td>${deb.fechaUltPagoParcial}</td>
															<td>${deb.resultadoValidacionDescripcionError}</td><!--VER SI NO ES CODIGO ERROR -->
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong>Listado de otros débitos seleccionados</strong></p>
										<div class="row">
											<div class="span9">
												<table id="prevOtrosDebitos" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th>Sociedad</th>
															<th>Sistema</th>
															<th>Tipo de Débito</th>
															<th>Referencia de Pago</th>
															<th>Cliente</th>
															<th>Número Legal</th>
															<th>Fecha de Vencimiento</th>
															<th>Moneda</th>
															<th>Tipo de Cambio</th>
															<th>Importe a Cobrar</th>
															<th>Importe a Cobrar Moneda Origen</th>
															<th>Sin Diferencia de Cambio</th>
															<th>Adjunto</th>
															<th> </th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaOtrosDebitos}" var="otrosdeb" varStatus="i">
															<tr>
															<td></td>
															<td>${otrosdeb.sociedad}</td>
															<td>${otrosdeb.sistema}</td>
															<td>${otrosdeb.tipoDebito}</td>
															<td>${otrosdeb.referenciaPago}</td>
															<td>${otrosdeb.cliente}</td>
															<td>${otrosdeb.numeroDocumento}</td>
															<td>${otrosdeb.fechaVencimiento}</td>
															<td>${otrosdeb.moneda}</td>
															<td>${otrosdeb.tipoCambio}</td>
															<%-- <td>${otrosdeb.monedaImporteCobrar.signo2}${otrosdeb.importeString}</td> --%>
															<c:choose>
																<c:when test="${otrosdeb.moneda != monedaImporteCobrar.signo2}">
																	<td>${otrosdeb.monedaImporteCobrar.signo2}${otrosdeb.importeString}</td>
																</c:when>
																<c:otherwise>
																	<td>-</td>
																</c:otherwise>
															</c:choose>
															<%-- <td>${otrosdeb.moneda}${otrosdeb.importeMonedaOrigen}</td> --%>
															<c:choose>
																<c:when test="${otrosdeb.moneda != monedaImporteCobrar.signo2 && otrosdeb.importeMonedaOrigen!='-'}">
																	<td>${otrosdeb.moneda}${otrosdeb.importeMonedaOrigen}</td>
																</c:when>
																<c:otherwise>
																	<td>-</td>
																</c:otherwise>
															</c:choose>
															<td>${otrosdeb.sinDiferenciaDeCambio}</td>
															<td>${otrosdeb.nombreAdjunto}</td>
															<td>
															<c:choose>
															<c:when test="${!empty otrosdeb.nombreAdjunto && otrosdeb.nombreAdjunto != '-' }">
																<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																		<button action="descargar" type="button" class="btn btn-xs btn-link" title="Descargar adjunto" onclick="javascript:descargarAdjunto('${otrosdeb.idAdjunto}');">
																			<i class="icon-download-alt bigger-120"></i>
																		</button>
																</div>
																<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>
																</c:when>
																</c:choose>
															</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong>Listado de créditos seleccionados</strong></p>
										<div class="row">
											<div class="span9">
												<table id="prevCreditos" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th>Fecha Valor</th>
															<th>Saldo Moneda Origen</th>
															<th>Moneda</th>
															<th>Imp. Moneda Origen</th>
															<th>Imp. pesificado</th>
															<th>Saldo pesificado</th>
															<th>Importe a utilizar</th>
															<th>Fecha alta</th>
															<th>Fecha ingreso recibo</th>
															<th>Fecha depósito</th>
															<th>Fecha transferencia</th>
															<th>Fecha emisión</th>
															<th>Fecha vencimiento</th>
															<th>Fecha último movimiento</th>
															<th>Tipo de Cambio</th>
															<th>Provincia</th>
															<th>CUIT</th>
															<th>Estado Origen</th>
															<th>Motivo</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/compensación en proceso</th>
															<th>Op. Asociada + Analista</th>
															<th>Resultado validación</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaCreditos}" var="cre" varStatus="i">
															<tr>
																<td></td>
																<td>${cre.idClienteLegado}</td>
																<td>${cre.cuenta}</td>
																<td>${cre.sistemaDescripcion}</td>
																<!-- ver si no es sistema origen	-->
																<td>${cre.tipoCredito}</td>
																<td>${cre.subtipoDocumentoDesc}</td>
																<td>${cre.nroDoc}</td>
																<td>${cre.numeroReferenciaMic}</td>
																<td>${cre.fechaValor}</td>
																<td>${cre.saldoMonOrigen}</td>
																<td>${cre.moneda}</td>
																<td>${cre.impMonOrigen}</td>
																<c:choose>
																	<c:when test="${cre.moneda != monedaOperacion}">
																		<td>${cre.impPesificado}</td>
																	</c:when>
																	<c:otherwise>
																		<td>-</td>
																	</c:otherwise>
																</c:choose>
																<c:choose>
																	<c:when test="${cre.moneda != monedaOperacion}">
																		<td>${cre.saldoPesificado}</td>
																	</c:when>
																	<c:otherwise>
																		<td>-</td>
																	</c:otherwise>
																</c:choose>
																<td>${cre.importeAUtilizar}</td>
																<td>${cre.fechaAltaCredito}</td>
																<td>${cre.fechaIngresoRecibo}</td>
																<td>${cre.fechaDeposito}</td>
																<td>${cre.fechaTrans}</td>
																<td>${cre.fechaEmision}</td>
																<td>${cre.fechaVenc}</td>
																<td>${cre.fechaUltimoMov}</td>
																<c:choose>
																	<c:when test="${cre.moneda != monedaOperacion}">
																		<td>${cre.tipoCambio}</td>
																	</c:when>
																	<c:otherwise>
																		<td>-</td>
																	</c:otherwise>
																</c:choose>
																<td>${cre.provincia}</td>
																<td>${cre.cuit}</td>
																<td>${cre.estadoOrigen}</td>
																<td>${cre.motivoDescripcion}</td>
																<td>${cre.marcaReclamoDescripcion}</td>
																<td>${cre.marcaMigradoDeimosDesc}</td>
																<td>${cre.estadoDeimos}</td>
																<td>${cre.marcaPagoCompensacionEnProcesoShivaDesc}</td>
																<td>${cre.opeAsocAnalista}</td>
																<td>${cre.resultadoValidacionDescripcionError}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong>Listado de otros medios de pago</strong></p>
										<div class="row" style="margin-top: 20px">
											<div class="span9">
												<table id="prevMediosPagos" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th></th>
															<th>Tipo</th>
															<th>Sub Tipo</th>
															<th>Importe</th>
															<th>Nro. Acta</th>
															<th>Nro. Compensación</th>
															<th>Fecha</th>
															<th>Cliente</th>
															<th>Provincia</th>
															<th>Nro de Documento Interno</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaMediosPagos}" var="med" varStatus="i">
<%-- 															<c:forEach items="${med.listaMedioPagoCliente}" var="cli" varStatus="status"> --%>
																<tr>
																	<td></td>
																	<td>${med.descMpTipoCredito}</td>
																	<td>${med.subTipoDescripcion}</td>
																	<td>${med.monedaImporteUtilizarSigno2}${med.importeFixed}</td>
																	<td>${med.nroActa}</td>
																	<td>${med.nroCompensacion}</td>
																	<td>${med.fecha}</td>
																	<td>${med.clientesLegados}</td>
																	<td>${med.provincia}</td>
																	<td>${med.nroDeDocumentoInterno}</td>
																</tr>
<%-- 															</c:forEach> --%>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										
										<c:forEach items="${listaMediosPagos}" var="med" varStatus="i">
											<c:set var="mostrarCap" scope="session" value="${false}"/>
											<c:if test="${(med.idMpTipoCredito == '35') || (med.idMpTipoCredito == '41')}">
												<c:set var="mostrarCap" scope="session" value="${true}"/>
											</c:if>
										</c:forEach>
										<c:if test="${mostrarCap}">
											<p style="padding: 25px 0px 5px 0px;"><th><strong><spring:message code="cobro.seleccionCap.listadoCap.seleccionado"/></strong></th></p>
											<div class="row">
												<div class="span9">
													<table id="prevDocumentosCap" class="tablaResultado">
														<thead>
															<tr>
																<th></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.sociedad"/></th>
																<th >Número&nbsp;de&nbsp;Cliente</th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroProveedor"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeDocumento"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoInterno"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoLegal"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.fechaEmision"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.renglon"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.noneda"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigen"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambio"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeDelRenglon"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoMonedaOrigen"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoPesificado"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importePesificadoDocumentoAsociado"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigenDocumentoAsociado"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambioDocumentoAsociado"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.checkSinDiferenciaDeCambio"/></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeDiferenciaCambio"/></th>
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${listaMediosPagos}" var="med" varStatus="i">
														
																<c:forEach items="${med.listaDocumentoCap}" var="docCap" varStatus="i">
																	<tr>
																	<td></td>
																	<td>${docCap.codigoSociedad}</td>
																	<td>${docCap.idClienteLegado}</td>
																	<td>${docCap.idNumeroProveedor}</td>
																	<td>${docCap.tipoDocumento}</td>
																	<td>${docCap.nroDocumentoSap}</td>
																	<td>${docCap.nroLegalSap}</td>
																	<td>${docCap.fechaEmision}</td>
																	<td>${docCap.numeroRenglon}</td>
																	<td>${docCap.monedaSigno2Detalle}</td>
																	<td>${docCap.importeMonedaOrigenDetalle}</td>
																	<td>${docCap.tipoCambio}</td>
																	<td>${docCap.importeGestionableDetalle}</td>
																	<td>${docCap.saldoMonedaOrigenDetalle}</td>
																	<td>${docCap.saldoPesificadoDetalle}</td>
																	<td>${docCap.importePesificadoDocAsociadoDetalle}</td>
																	<td>${docCap.importeMonedaOrigenDocAsociadoDetalle}</td>
																	<td>${docCap.tipoCambioDocAsociado}</td>
																	<td>${docCap.sinDifDeCambioDetalle}</td>
																	<td>${docCap.importeDiferenciaCambioDetalle}</td>
																	<td style="margin-top: 20px; display: none;" class="noClassEven">${docCap.even}</td>
																	<td style="margin-top: 20px; display: none;" class="noClassTipoRenglon">${docCap.tipoRenglon}</td>
																	<td style="margin-top: 20px; display: none;" class="noClassFin">${docCap.esRenglonFin}</td>
																	</tr>
																</c:forEach>
															</c:forEach>
														</tbody>
													</table>
													<div class="customPag_info" id="customPagCapPrev_info">
														<span><spring:message code="cobro.busqueda.leyenda.parametrizada" arguments="${controlPaginacion.cantPorPagina},${controlPaginacion.cantRegistrosMostrados},${controlPaginacion.totalRegistros}"/></span>
													</div>
												</div>
											</div>
										</c:if>
										<p style="padding: 25px 0px 5px 0px;"><strong>Listado de comprobantes</strong></p>
										<div class="row">
											<div class="span9">
												<table id="prevComprobantes" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th></th>
															<th>Nombre</th>
															<th>Descripcion</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaComprobantes}" var="com" varStatus="i">
															<tr>
																<td></td>
																<td>${com.nombreArchivo}</td>
																<td>${com.descripcionArchivo}</td>
																<td>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																		<button action="descargar" type="button" class="btn btn-xs btn-link" title="Descargar comprobante"
																			onclick="javascript:descargarComprobante('${com.idComprobante}');">
																			<i class="icon-download-alt bigger-120"></i>
																		</button>
																	</div>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<p style="padding: 30px 0px 5px 0px;"><strong>Totales documentos</strong></p>
										<div class="row">
											<div class="span3">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" /> 
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p4">Total Débitos</label>
													<input id="prevSumDebitos_p4" value="${prevSumDebitos_p4}" name="prevSumDebitos" type="text" class="input sigmaInputText" readonly/>
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" /> 
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p4"> Total MP</label>
													<input id="prevSumCreditos_p4" value="${prevSumCreditos_p4}" name="prevSumCreditos" type="text" class="input sigmaInputText" readonly/>
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p4">Diferencia</label>
												<input id="prevTotal_p4" value="${prevTotal_p4}" name="prevTotal" type="text" class="input sigmaInputText" readonly/>
											</div>
										</div>
										<div id="tratamientoDeDiferencia" style="display: none">
										<p style="padding: 25px 0px 5px 0px;"><strong>Tratamiento de diferencia</strong></p>
											<div class="row" style="padding-top: 8px;">
												<div class="span3">
													<label class="control-label" for="envio">Envio a</label>
														<div class="controls">
															<input id="selectEnvio" name="selectEnvio" type="text" class="input" readonly value="${selectEnvio}" />
														</div>
												</div>
											</div>
											<div class="row">
												<div class="span3">
													<div class="bloqueSistemaDestino">
														<label class="control-label" id="labelSistDestino" for="sistDestino">Sistema destino</label>
														<div class="controls">
															<input id="sistDestino" name="sistDestino" type="text" class="input" readonly value="${selectSistDestino}" />
														</div>
													</div>
												</div>
												<div class="span6">
													<label class="control-label" for="codigoOperacionText"><spring:message
															code="cobro.detalleCobro.referencia" /></label> <input
														id="idReferencia" name="idReferencia" type="text"
														class="input span6" value="${idReferencia}" readonly /></input>
												</div>
											</div>
										</div>
										<!-- Grilla Transacciones -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Transacciones</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="prevTransacciones" class="tablaResultado">
													<thead>
														<tr>
															<th class="nosort">Grupo</th>
															<th class="nosort">Nro. Transacción</th>
															<th class="nosort">Estado Transacción</th>
															<th class="nosort">Sistema&nbsp;Doc</th>
															<th class="nosort">Tipo Documento</th>
															<th class="nosort">Subtipo Documento</th>
															<th class="nosort">Nro. Documento</th>
															<th class="nosort">Nro. Referencia</th>
															<th class="nosort">Fecha Vencimiento</th>
															<th class="nosort">Moneda</th>
															<th class="nosort">Fecha Cobro</th>
															<th class="nosort">Importe</th>
															<th class="nosort">Tipo&nbsp;Cambio al&nbsp;Cobro</th>
															<th class="nosort">Tipo&nbsp;Cambio Fecha&nbsp;Emisión</th>
															<th class="nosort">Importe&nbsp;Cobrado&nbsp;en Moneda&nbsp;Origen</th>
															<th class="nosort">Sistema MP</th>
															<th class="nosort">Tipo&nbsp;Medio de&nbsp;Pago</th>
															<th class="nosort">Subtipo&nbsp;Medio de&nbsp;Pago</th>
															<th class="nosort">Referencia Medio&nbsp;de&nbsp;Pago</th>
															<th class="nosort">Fecha&nbsp;Medio de&nbsp;Pago</th>
															<th class="nosort">Moneda&nbsp;Medio de&nbsp;Pago</th>
															<th class="nosort">Importe&nbsp;del Medio&nbsp;de&nbsp;Pago</th>
															<th class="nosort">Tipo&nbsp;Cambio al&nbsp;Cobro Medio&nbsp;de&nbsp;Pago</th>
															<th class="nosort">Importe&nbsp;Cobrado&nbsp;en Moneda&nbsp;Origen Medio&nbsp;de&nbsp;Pago</th>
															<th class="nosort">Intereses</th>
															<!-- Editable checkbox -->
															<th class="nosort">Trasladar Intereses</th>
															<!-- Editable text -->
															<th class="nosort">%&nbsp;a&nbsp;Bonificar</th>
															<!-- Editable text -->
															<th class="nosort">Importe a&nbsp;Bonificar</th>
															<!-- Editable text -->
															<th class="nosort">Acuerdo&nbsp;Facturacion Destino&nbsp;Cargos</th>
															<th class="nosort">Estado&nbsp;Acuerdo Facturacion</th>
															<th class="nosort">Mensaje Transaccion</th>
															<th class="nosort">Mensaje Débito</th>
															<th class="nosort">Mensaje Crédito</th>
															<th></th><!-- Campo oculto colorLetraRegistro -->
															<th></th><!-- Campo oculto numeroTransaccionOriginal -->
															<th></th><!-- Campo oculto numeroGrupo -->
															<th></th><!-- Campo oculto numeroTransaccionFormateado -->
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<!-- Grilla Transacciones -->
										<!-- Observaciones Documentos CAP -->
										<c:if test="${mostrarCap}">
											<div class="row" style="margin-top: 20px; display: none;" id="prevObservacionesCapAnterior2">
													<div class="span9">
														<label class="control-label" for="prevObservCapTextAnterior2">Observaciones Anteriores</label>
														<textarea class="field span9" id="prevObservCapTextAnterior2" readonly name="prevObservCapTextAnterior2"></textarea>
													</div>
												</div>
												<div class="row" style="margin-top: 20px" id="prevObservacionesCap">
													<div class="span9">
														<label class="control-label" for="prevObservCapText2">Observaciones de Documentos Cap</label>
														<textarea class="field span9" id="prevObservCapText2" readonly name="prevObservCapText2"></textarea>
													</div>
											</div>
										</c:if>
										<!-- FIN Observaciones Documentos CAP -->
										<!-- Totales Intereses -->
										<p style="padding: 30px 0px 5px 0px;"><strong>Totales Intereses</strong></p>
										<div class="row">
											<div class="span3" style="margin-left:70px;">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesTraslados">Intereses Traslados</label>
													<input id="prevSumInteresesTraslados" value="${prevSumInteresesTraslados}" name="prevSumInteresesTraslados" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
											<div class="span3" style="margin-left: -10px">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesBonificados">Intereses Bonificados</label>
													<input id="prevSumInteresesBonificados" value="${prevSumInteresesBonificados}" name="prevSumInteresesBonificados" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
											<div class="span3" style="margin-left: -10px">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesReintegro">Intereses por Reintegro</label>
													<input id="prevSumInteresesReintegro" value="${prevSumInteresesReintegro}" name="prevSumInteresesReintegro" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="span3" style="margin-left:70px;">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesTraslados">Intereses Trasladados</label>
													<input id="prevSumInteresesTrasladosDolares" value="${prevSumInteresesTrasladosDolares}" name="prevSumInteresesTraslados" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
											<div class="span3" style="margin-left: -10px">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesBonificados">Intereses Bonificados</label>
													<input id="prevSumInteresesBonificadosDolares" value="${prevSumInteresesBonificadosDolares}" name="prevSumInteresesBonificados" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
											<div class="span3" style="margin-left: -10px">
												<img class="sigma" src="${pageContext.request.contextPath}/img/sigma.png" style="margin-left:-36px;"> 
												<div class="sigmaControlGroup" style="width:150px;">
													<label class="control-label" for="prevSumInteresesReintegroDolares">Intereses por Reintegro</label>
													<input id="prevSumInteresesReintegroDolares" value="${prevSumInteresesReintegroDolares}" name="prevSumInteresesReintegroDolares" type="text" class="input sigmaInputText" readonly style="width:130px;">
												</div>
											</div>
										</div>
										<!-- Totales Intereses -->
										
										<!-- COMPROBANTES / DETALLES  -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message code="cobros.confirmacion.aplicacion.manual.seccion.detalles" /></strong>
										</p>
									<div id="comprobanteAplicacionManualDiv">
										<!-- ADJUNTAR NUEVOS DETALLES -->
											<div class="row" style="margin-top: 20px;">
												<div class="span9">
													<form id="adjComprobanteForm" method="post" action="configuracion-cobro/adjuntarComprobanteConfirmacion" enctype="multipart/form-data">
														<label class="control-label" for="comprobanteArch"><spring:message code="cobros.confirmacion.aplicacion.manual.detalle" /></label>
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="hidden">
															<div class="input-append">
																<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																	<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																</div>
																<span class="btn btn-file btn-primary btn-small" style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																	<span class="fileupload-new">Seleccionar Archivo</span> <span class="fileupload-exists">Cambiar</span>
																	<input name="comprobanteArch" id="comprobanteArch" type="file" />
																</span>
																<a href="#" class="btn fileupload-exists btn-primary btn-small" style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload">Eliminar</a>
															</div>
														</div>
													</form>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorArchComprobante" class="error" hidden="true"></span>
												</div>
											</div>
											<div class="row" style="margin-top: 0px; margin-bottom: 5px">
												<div class="span9">
													<label class="control-label" for="descripcionComprobante">Descripción</label>
													<textarea class="field span9" id="descripcionComprobante" maxlength="150" name="descripcionComprobante" /></textarea>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorDescripcionComp" class="error" hidden="true"></span>
												</div>
											</div>
											<div class="row" style="margin-top: 0px; margin-bottom: 5px; margin-right: 2px">
												<div class="span9" align="right">
													<button type="button" class="btn btn-primary btn-small" id="btnAdjComprobante">
														<i class="icon-white icon-upload"></i> Adjuntar
													</button>
												</div>
											</div>
									</div>
										
										<div class="row" style="margin-top: 20px; margin-bottom: 5px">
											<div class="span9">
												<table id="comprobantes" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th></th>
															<th>Nombre</th>
															<th>Descripcion</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaComprobanteAplicacionManual}" var="com" varStatus="i">
															<tr>
																<td>${com.idComprobante}</td>
																<td>${com.nombreArchivo}</td>
																<td>${com.descripcionArchivo}</td>
																<td>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																		<button action="descargar" type="button" class="btn btn-xs btn-link" title="Descargar comprobante" onclick="javascript:descargarComprobante('${com.idComprobante}');">
																			<i class="icon-download-alt bigger-120"></i>
																		</button>
																	</div>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<!-- FIN COMPROBANTES / DETALLES -->
										
										<!-- COMIENZO CODIGO DE OPERACIONES EXTERNAS -->
							<div id="operExp">			
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Sección de Operaciones Externas</strong>
										</p>
						
										<div class="row">
											<div class="span3" id="divNroTransaccion">
												<label class="control-label">
													<span class="rojo">* </span>
													<spring:message code="cobros.confirmacion.aplicacion.manual.nro.transaccion"/>
												</label>
												<div class="controls">
													<select class="input nroTransaccion" id ="nroTransaccionCodOpExt" ></select>
												</div>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorNroTransaccionCodOpExt" class="error"></span>
												<!-- hidden="true"-->
											</div>
										</div>
									<div id="bloqueCodigoOperacionExterna">
										<!--INPUT CÓDIGO DE OPERACION -->
										<div class="row" style="margin-top: 20px;">
											<div class="span3">
												<label class="control-label" for="codigoOperacionText"><span class="rojo">* </span><spring:message code="cobros.confirmacion.aplicacion.manual.codigo.operacion.externa" /></label> <input type="text"
													id="codigoOperacionText" maxlength="50"
													name="codigoOperacionText">
												<div class="row rowError">
													<div class="span9">
														<span id="errorCodigoOperacion" class="error"></span>
														<!-- hidden="true"-->
													</div>
												</div>
	
											</div>
										<!-- IMPORTE -->
											<div class="span3">
												<label class="control-label" for="codigoOperacionText"><span class="rojo">* </span><spring:message code="cobros.confirmacion.aplicacion.manual.importe" /></label> 
												<span style="float: left">${monedaOperacion}</span>
												<input type="text"
													id="importeCodOpExtText" maxlength="20"
													name="importeCodOpExtText" type="text" style="margin: 0 auto; width: 170px">
												<div class="row rowError">
													<div class="span9" style="margin-top: 10px;">
														<span id="errorImporteCodOpExtText" class="error"></span>
														<!-- hidden="true"-->
													</div>
												</div>
											</div>
										</div>
										<div id="divReferenciaYNumDoc">
										
											<div class="row" style="margin-top: 20px;">
											<!-- REFERENCIA -->
												<div class="span3" id="divReferencia">
													<label class="control-label" for="codigoOperacionText"><spring:message code="cobros.confirmacion.aplicacion.manual.referencia" /></label> <input type="text"
														id="referenciaCodOpExtText" maxlength="22"
														name="referenciaCodOpExtText">
													<div class="row rowError">
														<div class="span9">
															<span id="errorReferenciaCodOpExtText" class="error"></span>
															<!-- hidden="true"-->
														</div>
													</div>
												</div>
											<!-- FACTURA -->
												<div class="span3" id="divFactura">
													<label class="control-label" for="codigoOperacionText"><spring:message code="cobros.confirmacion.aplicacion.manual.factura" /></label> <input type="text"
														id="facturaCodOpExtText" maxlength="20"
														name="facturaCodOpExtText">
													<div class="row rowError">
														<div class="span9">
															<span id="errorFacturaCodOpExtText" class="error"></span>
															<!-- hidden="true"-->
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="txtAreadiv" class="row" style="display:none">
										
											<div class="span3" id="divReferencia">
												<label class="control-label" for="codigoOperacionText"><spring:message code="cobros.confirmacion.aplicacion.manual.referencia" /></label> <textarea type="text"
													id="referenciaCodOpExtTextArea" 
													name="referenciaCodOpExtTextArea"></textarea>
												<div class="row rowError">
													<div class="span9">
														<span id="errorReferenciaCodOpExtTextArea" class="error"></span>
														<!-- hidden="true"-->
													</div>
												</div>
											</div>
											<div class="span3" id="divFacturatxtArea">
												<label class="control-label" for="codigoOperacionTextArea"><spring:message code="cobros.confirmacion.aplicacion.manual.factura" /></label> <textarea type="text"
													id="codigoOperacionTextArea"
													name="codigoOperacionTextArea"></textarea>
												<div class="row rowError">
													<div class="span9">
														<span id="errorFacturaCodOpExtTextArea" class="error"></span>
														<!-- hidden="true"-->
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="span9" align="right">
												<div
													style="margin-top: 15px; margin-bottom: 0px; margin-right: 2px">
													<button type="button" class="btn btn-primary btn-small"
														id="btnAgregarCodigo">
														<i class="icon-white icon-chevron-down"></i> <spring:message code="cobros.confirmacion.aplicacion.manual.button.agregar.codigo"/>
													</button>
	
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="span9">
											<table id="codigoOperacionesExternas" class="tablaResultado" width="100%">
												<thead>
													<tr>
														<th></th>
														<th></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.codigo.operacion.externa" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.importe" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.referencia" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.factura" /></th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									
									<div class="row" style="margin-top: 20px;" >
										<div class="span9" align="right">
											<div
												style="margin-top: 15px; margin-bottom: 0px; margin-right: 2px">
												<button type="button" class="btn btn-primary btn-small"
													id="btnAgregarCombinacionPorTransaccion">
													<i class="icon-white icon-chevron-down"></i> <spring:message code="cobros.confirmacion.aplicacion.manual.button.agregar"/>
												</button>
											</div>
										</div>
										
									</div>
									<div class="row rowError">
											<div class="span9">
													<span id="errorAgregar" class="error errorOtro"></span>
											</div>
											</div>
						</div>
									<!-- GRILLA CODIGO POR TRANSACCION -->
									<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message code="cobros.confirmacion.aplicacion.manual.codigo.por.transaccion"/></strong>
									</p>
									<div class="row" style="margin-top: 20px;">
										<div class="span9">
											<table id="codigoPorTransaccion" class="tablaResultado" width="100%">
												
												<c:choose>
													<c:when test="${not empty tipoTareaName and tipoTarea == tipoTareaName}">
													<thead>
															<tr>
																<th></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.grupo"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.transaccion"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.sistema"/></th>
																<th><spring:message code="conf.cobro.codigo.operacion.externa" /></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.ref.impu"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.importe"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.responsable.imputacion"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.fecha.imputacion"/></th>
																<th></th>
															</tr>
															</thead>
														<tbody>
															<c:forEach items="${listaCodigoOperacionesExterna}" var="codOperacion">
																<tr>
																	<td></td>
																	<td>${codOperacion.grupo}</td>
																	<td>${codOperacion.nroTransaccion}</td>
																	<td>${codOperacion.sistema}</td>
																	<td>${codOperacion.codigoOperacionExterna}</td>
																	<td>${codOperacion.referencia}</td>
																	<td>${codOperacion.importe}</td>		
																	<td>${codOperacion.responsableImputacion}</td>
																	<td>${codOperacion.fechaImputacion}</td>
																	<td></td>
																</tr>
															</c:forEach>
														</tbody>
													</c:when>
													<c:otherwise>
													<thead>
													<tr>
														<th></th>
														<th></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.nro.transaccion" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.codigo.operacion.externa" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.importe" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.referencia" /></th>
														<th><spring:message code="cobros.confirmacion.aplicacion.manual.factura" /></th>
													</tr>
												</thead>
												</c:otherwise>
													
												</c:choose>
											</table>
										</div>
									</div>
										
										<!-- FIN CODIGO DE OPERACIONES EXTERNAS -->
										
											<c:choose>
												<c:when test="${mostrarObservTextAnterior}">
													<div class="row" style="margin-top: 20px" id="prevObservaciones">
														<div class="span9">
															<label class="control-label" for="prevObservTextAnterior">Observaciones Anteriores</label>
															<textarea class="field span9" id="prevObservTextAnterior" readonly name="prevObservTextAnterior">${prevObservTextAnterior}</textarea>
														</div>
													</div>
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
											<div class="row" style="margin-top: 20px" id="prevObservaciones">
												<div class="span9">
													<label class="control-label" for="prevObservText2">Observaciones</label>
													<textarea class="field span9" id="prevObservText2" name="prevObservText2">${prevObservText2}</textarea>
												</div>
											</div>
											<div class="row rowError">
											<div class="span9">
												<span id="errorObservacionObligatorio" class="error"></span>
											</div>
										</div>
										</div>
								
								<div class="row" style="margin-top: 17px">
								
										<div class="span9" align="right">
										<c:choose>
										<c:when test="${not empty tipoTareaName and tipoTarea == tipoTareaName}">	
											<button class="btn btn-primary btn-small" id="btnInformar" name="btnInformar" type="button" >
												<i class="icon-white icon-ok"></i>&nbsp;&nbsp;Aceptar&nbsp;&nbsp;
											</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button" >
												<i class="icon-white icon-ok"></i>&nbsp;&nbsp;Confirmar&nbsp;&nbsp;
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnRechazar" onclick="javascript:validarObservacionObligatoriedad();">
												<i class="icon-white icon-remove"></i> Rechazar
											</button>
										</c:otherwise>
										</c:choose>
											<button type="button" class="btn btn-primary btn-small" id="btnVolver" onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i>&nbsp;&nbsp;&nbsp;Volver&nbsp;&nbsp;&nbsp;
											</button>
										</div>
										
										
								</div>
								
								<div class="row">
										<div class="span8">
											<div id="alertErrorConfirmacionManual2" class="alert alert-error" align="center" style="text-align: left;margin-left: auto; margin-right: auto;display: none;width:279px; margin-top:20px">
												<button id="btnCruzErrorConfirmacionManualPie" class="close" >×</button><strong><spring:message code="error.error"/></strong><br/>
												<span id="errorConfirmacionManual2"></span>
											</div>
										</div>
									</div>
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
<script>
$(document).ready(function() {

	$('#bloqueEspera').trigger('click');
	buscarTransacciones(${idCobro}, '${sistema}', '${sociedad}','${prevEmpresa}','${prevSegmento}');
});
</script>