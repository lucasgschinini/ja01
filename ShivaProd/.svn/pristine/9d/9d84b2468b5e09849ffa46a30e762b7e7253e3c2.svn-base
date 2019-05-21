<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.SistemaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.ConstantesCobro"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MarcaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Propiedades"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><spring:message code="titulo.nombre.app" />&nbsp;-&nbsp;<spring:message code="conf.descobro.page.title" />&nbsp;</title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>


<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dataTables.fixedColumns.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/dataTables.fixedColumns.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/descobro-configuracion.js"></script>


<script>
var TIPO_MENSAJE_OK_NAME = '<%=TipoMensajeEstadoEnum.OK.name()%>';
var TIPO_MENSAJE_ERROR_NAME = '<%=TipoMensajeEstadoEnum.ERR.name()%>';
var SIMULACION_BATCH_FINALIZADA_CON_EXITO = '<%=MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.name()%>';
var COMPROBANTE_DESCOBRO = '<%=MotivoAdjuntoEnum.COMPROBANTE_DESCOBRO.name() %>';
</script>


<style type="text/css">

 tr.trans-0 td{ background-color : #F2F2F7; }
 tr.trans-1 td{ background-color : white; }
 
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

.tooltip-inner {
	max-width: 400px;
	background-color: red;
}

.tooltip.top .tooltip-arrow {
	border-top-color: red;
}

.alert {
	padding: 8px 35px 8px 14px;
	margin-bottom: 18px;
	color: #c09853;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	background-color: #fcf8e3;
	border: 1px solid #fbeed5;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

.alert-heading {
	color: inherit;
}

.alert {
	position: relative;
	top: -2px;
	right: -21px;
	line-height: 18px;
}

.alert-danger,.alert-error {
	color: #b94a48;
	background-color: #f2dede;
	border-color: #eed3d7;
}

table.dataTable tbody tr.selectedCustom {
	background-color: #ffff00;
}

.deshabilitado {
	pointer-events:none;
    background:#EEEEEE;
}
</style>
<script>
var DESTINO_BANDEJA_ENTRADA = '<%=Constantes.DESTINO_BANDEJA_ENTRADA%>';
var DESTINO_BUSQUEDA_COBRO = '<%=Constantes.DESTINO_BUSQUEDA_COBRO%>';
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
								<spring:message code="conf.descobro.page.title" />
							</div>
							<div class="pagos_anticipos">
								<div class="row">
									<div class="span3" align="left">
										<label class="control-label" for="idOperacionDescobro">Id Reversion</label>
										<div class="controls">
											<input id="idOperacionDescobro" name="idOperacionDescobro" type="text" class="input" readonly />
										</div>
									</div>
									<div class="span6" style="margin-top: 16px;" align="right">
										<div>
											<button type="button" class="btn btn-primary btn-small" id="btnHistorial" onclick="historialCobro();">
												<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnExportar">
												<i class="icon-white icon-download-alt"></i> Exportar a Excel
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnGuardar" onclick="javascript:guardarReversion();">
												<i class="icon-white icon-ok"></i> Guardar
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnVolver" onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i> Cancelar
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="span3" align="left" style="display: none;" id="divEstado">
										<label class="control-label" for="cobroEstado">Estado Reversión</label>
										<div class="controls">
											<input text id="cobroEstado" name="cobroEstado" type="text" class="input" readonly />
										</div>
									</div>
									<div class="span6" align="left" style="display: none;" id="divCobroSubEstados">
										<label class="control-label" for="cobroSubEstados">Sub Estado Reversión</label>
										<div class="controls">
											<input text id="cobroSubEstados" name="cobroSubEstados" type="text" class="input span6" readonly />
										</div>
									</div>
								</div>
<!-- 								<div class="row" style="margin-top: 10px"> -->
<!-- 									<div class="span8"> -->
<!-- 										<div class="alert alert-error" style="display: none" id="alertErrorGuardado"> -->
<!-- 											<a class="close">×</a> -->
<%-- 												<strong><spring:message code="error.error"/></strong><br /> --%>
<!-- 											<span id="errorGuardado"></span> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div id="conf-cobro-tabs">
									<div>
										<div id="inicioCliente">
											<!-- BUSQUEDA CLIENTES -->
											<div class="row">
												<div class="span3">
													<label class="control-label" for="empresa"><span class="rojo">* </span> <spring:message code="conf.cobro.empresa" /></label>
													<div class="controls">
														<select id="selectEmpresa" name="empresa" class="input">
															<c:if test="${comboEmpresa}">
																<option value=""><spring:message code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${empresas}" var="emp">
																<option value="${emp.idEmpresa}">${emp.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="segmento"><span class="rojo">* </span> <spring:message code="conf.cobro.segmento" /></label>
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
												<div class="span3">
													<label class="control-label" for="monedaOperacion"><spring:message code="conf.cobro.moneda.operacion" /></label>
													<div class="controls">
														<input text id="monedaOperacion" name="monedaOperacion" value="${monedaOperacion}"
															type="text" class="input span3" readonly />
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3">
													<span id="errorEmpresa" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorSegmento" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorSegmento" class="error"></span>
												</div>
											</div>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="analista"><spring:message code="conf.cobro.analista" /></label>
													<div class="controls">
														<input id="analista" name="analista" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuario}"/>"
															style="margin: 0 auto;" />
													</div>
												</div>

												<div class="span3">
													<label class="control-label" for="copropietario"><spring:message code="conf.cobro.copropietario" /></label>
													<div class="controls">
														<select id="selectCopropietario" name="copropietario" class="input">
															<c:if test="${comboCopropietarios}">
																<option value=""><spring:message code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${copropietarios}" var="cop">
																<option value="${cop.tuid}">${cop.nombreCompleto}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="selectMotivo"><span class="rojo">* </span> <spring:message code="conf.cobro.motivo" /></label>
													<div class="controls">
														<select id="selectMotivo" name="selectMotivo" class="input">
															<c:if test="${comboMotivo}">
																<option value=""><spring:message code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${motivos}" var="mot">
																<option value="${mot.idMotivoDescobro}">${mot.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="row rowError" style="margin-bottom: 15px;">
												<div class="span3"></div>
												<div class="span3">
													<span id="errorCopropietario" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorMotivo" class="error"></span>
												</div>
											</div>
										</div>
										<div class="row" id="comboIdReversionPadre">
											<div class="span3">
												<label class="control-label" for="idReversionPadre"><span class="rojo">* </span> <spring:message
														code="conf.descobro.idReversionPadre" /></label>
												<div class="controls">
													<select id="selectIdReversionPadre" name="idReversionPadre" class="input">
														<c:if test="${comboIdReversionPadre}">
															<option value=""><spring:message code="combo.seleccionar" /></option>
														</c:if>
														<c:forEach items="${idReversionPadre}" var="idrp">
															<option value="${idrp}">${idrp}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="span3" id="idReversionPadreOtroCombo">
												<label class="control-label" for="idReversionPadreOtro"><span class="rojo">* </span>&nbsp;</label>
												<div class="controls" id="controlsOtro">
													<input class="otrosBloque" id="idReversionPadreOtro" name="idReversionPadreOtro" type="text" class="input" maxlength="7" />
												</div>
											</div>
										</div>
										<div class="row rowError" style="margin-bottom: 15px;">
											<div class="span3">
												<span id="idErrorReversion" class="error"
													style="display: none;"></span>
											</div>
											<div class="span3">
												<span id="idErrorOtros" class="error" style="display: none;"></span>
											</div>
										</div>
										<div class="row">
											<div class="span3" align="left" id="divLegajo" style="display: none;">
												<label class="control-label" for="idLegajoChequeRechazado">Id
													Legajo</label>
												<div class="controls">
													<input id="idLegajo" name="idLegajo" value="${idLegajo}" 
														type="text" class="input" readonly />
												</div>
											</div>
										</div>
										<p style="padding: 5px 0px;">
											<strong><spring:message code="conf.descobro.cobro.revertir" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="cobroRevertir" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th></th>
															<th><spring:message code="conf.descobro.cobro.empresa" /></th>
															<th><spring:message code="conf.descobro.cobro.segmento" /></th>
															<th><spring:message code="conf.descobro.cobro.idOperacion" /></th>
															<th><spring:message code="conf.descobro.cobro.monedaOperacion" /></th>
															<th><spring:message code="conf.descobro.cobro.motivoCobro" /></th>
															<th><spring:message code="conf.descobro.cobro.cliente" /></th>
															<th><spring:message code="conf.descobro.cobro.estado" /></th>
															<th><spring:message code="conf.descobro.cobro.subEstado" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaUltEstado" /></th>
															<th><spring:message code="conf.descobro.cobro.analista" /></th>
															<th><spring:message code="conf.descobro.cobro.copropietario" /></th>
															<th><spring:message code="conf.descobro.cobro.analistaTeamComercial" /></th>
															<th><spring:message code="conf.descobro.cobro.impTotal" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaAlta" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaDerivacion" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaAutoriRef" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaImputacion" /></th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${listaCobro}" var="cob" varStatus="i">
															<tr>
																<td><div class="visible-md visible-lg hidden-sm hidden-xs ar-group">
																		<button style="padding: 0px;" type="button" id="btnVer1" class="btn btn-xs btn-link" title="Ver detalle"
																			onclick="detalleCobro(${cob.idCobro},null)">
																			<i class="icon-eye-open bigger-120"></i>
																		</button>
																	</div></td>
																<td></td>
																<td>${cob.empresa}</td>
																<td>${cob.segmento}</td>
																<td>${cob.idOperacion}</td>
																<td>${cob.monedaOperacion}</td>
																<td>${cob.descripcionMotivoCobro}</td>
																<!--ver cliente -->
																<td>${cob.listaIdClientesLegadoRazonSocialConBr}</td>
																<td>${cob.descripcionEstado}</td>
																<!--ver subEstado -->
																<td>-</td>
																<td>${cob.fechaUltimoEstadoFormateado}</td>
																<td>${cob.nombreAnalista}</td>
																<td>${cob.nombreCopropietario}</td>
																<td>${cob.nombreAnalistaTeamComercial}</td>
																<td>${cob.importeTotalDeudaFormateado}</td>
																<td>${cob.fechaAltaFormatoJson}</td>
																<td>${cob.fechaDerivacionFormatoJson}</td>
																<td>${cob.fechaAprobacionReferenteCobranzaFormatoJson}</td>
																<td>${cob.fechaImputacionFormatoJson}</td>
																<td><div class="visible-md visible-lg hidden-sm hidden-xs ar-group">
																		<button style="padding: 0px;" type="button" id="btnVer2" class="btn btn-xs btn-link" title="Ver detalle"
																			onclick="detalleCobro(${cob.idCobro},null)">
																			<i class="icon-eye-open bigger-120"></i>
																		</button>
																	</div></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<!-- Grilla Transacciones -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Grilla de transacciones</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="prevTransacciones" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th class="nosort">Grupo</th>
															<th class="nosort">Nro. Transacción</th>
															<th class="nosort">Estado Transacción</th>
															<th class="nosort">Sistema Documento</th>
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
															<th class="nosort" class="nosort">Fecha&nbsp;Medio de&nbsp;Pago</th>
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
															<th class="nosort">Sistema Acuerdo</th>
															<!-- Editable text -->
															<th class="nosort">Acuerdo&nbsp;Facturacion Destino&nbsp;Cargos</th>
															<th class="nosort">Estado&nbsp;Acuerdo Facturacion</th>
															<th class="nosort">Estado cargo próxima factura</th>
															<th class="nosort">Sistema acuerdo de facturación destino de contracargos</th>
															<th class="nosort">Acuerdo de facturación destino de contracargos</th>
															<th class="nosort">Estado del acuerdo de facturación destino de contracargos</th>
															<th class="nosort">Mensaje Transaccion</th>
															<th class="nosort">Mensaje Débito</th>
															<th class="nosort">Mensaje Crédito</th>
															<th></th>
														</tr>
													</thead>
												</table>												
											</div>
										</div>
										<!-- Grilla Transacciones -->
										<!-- Boton Simular -->
										<div class="row">
											<div class="span9" align="right">
												<button type="button" class="btn btn-primary btn-small" id="btnSimular">
													<i class="icon-white icon-cog"></i> Simular
												</button>
											</div>
										</div>
										<!-- Mensaje de Error Simular -->
										<div class="row rowError" style="margin-top: 10px">
											<div class="span9" style="margin-top: 10px">
												<span id="mensajeErrorSimular" class="error" ></span>
											</div>
										</div>
										<!-- Boton Simular -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Documentos Relacionados a la Reversión</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="documentosRelacionados" class="tablaResultado">
													<thead>
														<tr>
															<th><spring:message code="conciliacion.espacio" /></th>
															<th><spring:message code="conciliacion.espacio" /></th>
															<th><spring:message code="conciliacion.espacio" /></th>
															<th style="background-color: #dcdbdb;"></th>
															<th colspan="5"><spring:message code="conf.descobro.documentoRelacionado.titulo.documentoOriginal" /></th>
															<th style="background-color: #dcdbdb;"></th>
															<th colspan="5"><spring:message code="conf.descobro.documentoRelacionado.titulo.documentoCancelatorio" /></th>
														</tr>
														<tr>
															<th><spring:message code="conf.descobro.documentoRelacionado.nroTransaccion" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.idCobranza" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.fechaImputacion" /></th>
															<th style="background-color: #dcdbdb;"></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.sistemaDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.tipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.subtipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.nroDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.importe" /></th>
															<th style="background-color: #dcdbdb;"></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.sistemaDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.tipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.subtipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.nroDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.importe" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 40px 0px 5px 0px;">
											<strong><spring:message code="conf.descobro.cobro.operacionesCobroRelacionadas" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="operacionesCobroRelacionadas" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th><spring:message code="conf.descobro.cobro.sistema" /></th>
															<th><spring:message code="conf.descobro.cobro.idOperacCobroRelacionada" /></th>
															<th><spring:message code="conf.descobro.cobro.idOpeCobroPadre" /></th>
															<th><spring:message code="conf.descobro.cobro.idTransaccionCobPadre" /></th>
															<th><spring:message code="conf.descobro.cobro.tipoDocumentoRelacionado" /></th>
															<th><spring:message code="conf.descobro.cobro.nroDocumentoRelacionado" /></th>
															<th><spring:message code="conf.descobro.cobro.motivoCobro" /></th>
															<th><spring:message code="conf.descobro.cobro.cliente" /></th>
															<th><spring:message code="conf.descobro.cobro.estado" /></th>
															<th><spring:message code="conf.descobro.cobro.subEstado" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaUltEstado" /></th>
															<th><spring:message code="conf.descobro.cobro.analista" /></th>
															<th><spring:message code="conf.descobro.cobro.copropietario" /></th>
															<th><spring:message code="conf.descobro.cobro.analistaTeamComercial" /></th>
															<th><spring:message code="conf.descobro.cobro.impTotal" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaAlta" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaDerivacion" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaAutoriRef" /></th>
															<th><spring:message code="conf.descobro.cobro.fechaImputacion" /></th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<!-- COMPROBANTES -->
										<p style="padding: 5px 0px;">
											<strong><spring:message code="conf.descobro.seccion.comprobantes" /></strong>
										</p>
										<div>
											<div id="bloqueAgregarComprobante">
												<div class="row" style="margin-top: 20px;">
													<div class="span9">
														<form id="adjComprobanteForm" method="post" action="configuracion-descobro/adjuntarComprobante" enctype="multipart/form-data">
															<label class="control-label" for="comprobanteArch">Comprobante</label>
															<div class="fileupload fileupload-new" data-provides="fileupload">
																<input type="hidden">
																<div class="input-append">
																	<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																		<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																	</div>
																	<span class="btn btn-file btn-primary btn-small"
																		style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																		<span class="fileupload-new">Seleccionar archivo</span> <span class="fileupload-exists">Cambiar</span><input name="comprobanteArch"
																		id="comprobanteArch" type="file" />
																	</span> <a href="#" class="btn fileupload-exists btn-primary btn-small"
																		style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload">Eliminar</a>
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
														<label class="control-label" for="descripcionComprobante">Descripcion</label>
														<textarea class="field span9" id="descripcionComprobante" maxlength="150" name="descripcionComprobante" /></textarea>
													</div>
												</div>
												<div class="row rowError">
													<div class="span9">
														<span id="errorDescripcionComp" class="error" hidden="true"></span>
													</div>
												</div>
												<div class="row" style="margin-top: 0px; margin-bottom: 5px">
													<div class="span9">
														<button type="button" class="btn btn-primary btn-small" id="btnAdjComprobante">Adjuntar</button>
													</div>
												</div>
											</div>
											<div class="row" style="margin-top: 20px">
												<div class="span9">
													<table id="comprobantes" class="tablaResultado" width="100%">
														<thead>
															<tr>
																<th></th>
																<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<!-- FIN COMPROBANTES -->
										<!-- CODIGO OPERACIONES EXTERNAS -->
										<c:if test="${ not empty listaCodigoOperacionesExternas }">
											<p style="padding: 25px 0px 5px 0px;">
												<strong>Listado de Operaciones Externas</strong>
											</p>
											<div class="row">
												<div class="span9">
													<table id="codigoOperacionesExternas" class="tablaResultado" width="100%">
														<thead>
															<tr>
																<th>Código de Operación Externa</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${listaCodigoOperacionesExternas}" var="cod" varStatus="i">
																<tr>
																	<td>${cod.codigoOperacionExterna}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</c:if>
										<!-- FIN CODIGO OPERACIONES EXTERNAS -->
										<div class="row" style="margin-top: 20px;">
											<div id="prevObservacionesAnterior">
												<div class="span9">
													<label class="control-label" for="prevObservTextAnterior">Observaciones Anteriores</label>
													<textarea class="field span9" id="prevObservTextAnterior" readonly name="prevObservTextAnterior">${prevObservTextAnterior}</textarea>
												</div>
											</div>
										</div>
										<div class="row" style="margin-top: 15px;" id="prevObservaciones">
											<div class="span9">
												<label class="control-label" for="prevObservText">Observaciones </label>
												<textarea class="field span9" id="prevObservText" name="prevObservText"></textarea>
											</div>
										</div>
										<div class="row rowError" style="margin-bottom: 20px;">
											<div class="span6">
												<span id="errorObservaciones" class="error"></span>
											</div>
										</div>
										<div class="row" style="margin-top: 17px;">
											<div class="span9" align="right">
												<button class="btn btn-primary btn-small" id="btnRevertir" type="button">
													<i class="icon-white icon-ok"></i> Revertir
												</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row" style="margin-top: 12px;">
									<div class="span9" align="right">
										<button type="button" class="btn btn-primary btn-small" id="btnHistorial2" onclick="historialCobro();">
											<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial
										</button>
										<button type="button" class="btn btn-primary btn-small" id="btnExportar2">
											<i class="icon-white icon-download-alt"></i> Exportar a Excel
										</button>
										<button type="button" class="btn btn-primary btn-small" id="btnGuardar2" onclick="javascript:guardarReversion();">
											<i class="icon-white icon-ok"></i> Guardar
										</button>
										<button type="button" class="btn btn-primary btn-small" id="btnVolver" onclick="javascript:volverBusqueda();">
											<i class="icon-white icon-repeat"></i> Cancelar
										</button>
									</div>
								</div>
								<form:form id="formDescobro" commandName="descobroDto">
										<input type="hidden" id="idLegajo" name="idLegajo" value="${idLegajo}"/>
										<input type="hidden" id="idLeg" name="idLeg" value="${idLegajo}"/>
										<input type="hidden" id="solapa" name="solapa" value="${solapa}"/>
										<input type="hidden" id="idReversion" name="idReversion" value="${idReversion}"/>
										<input type="hidden" id="idDescobro" name="idDescobro" value="${idReversion}"/>
										<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}"/>
										<input type="hidden" id="booleanIdPadreDescobroOtro" name="booleanIdPadreDescobroOtro" value="${booleanIdPadreDescobroOtro}"/>
										<input type="hidden" id="opcion" name="opcion"/>
										<input type="hidden" id="idCobroPadre" name="idCobroPadre" />
										<input type="hidden" id="idOperacionFormateado" name="idOperacionFormateado" />
										<input type="hidden" id="idDescobroPadreFormateado" name="idDescobroPadreFormateado" />
										<input type="hidden" id="idDescobroPadreFormateadoOtro" name="idDescobroPadreFormateadoOtro" />
										<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" value="${idOperacionRelacionada}"/>
										<input type="hidden" id="volver" name="volver" value="/descobros-configuracion-edicion" />
										<input type="hidden" id="idAnalista" name="idAnalista" value="${idUsuario}" />
										<input type="hidden" id="modificarTransacciones" name="modificarTransacciones" />
										<input type="hidden" id="estado" name="estado" />
										<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
										<input type="hidden" id="goBack" name="goBack" />
										<input type="hidden" id="idOperacionDescobroOculto" name="idOperacionDescobro" />
										<input type="hidden" id="idEmpresa" name="idEmpresa"/>
										<input type="hidden" id="idSegmento" name="idSegmento"/>
										<input type="hidden" id="nombreAnalista" name="nombreAnalista"/>
										<input type="hidden" id="idMotivoReversion" name="idMotivoReversion"/>
										<input type="hidden" id="idCopropietario" name="idCopropietario"/>
										<input type="hidden" id="observacion" name="observacion"/>
										<input type="hidden" id="volverAPantalla" name="volverAPantalla" value="${volverAPantalla}"/>
										<input type="hidden" id="vuelvoBandeja" name="vuelvoBandeja" value="descobros-configuracion-edicion" />
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

<script>
$(document).ready(function() {
	if (${cobroEditable}) {
		buscarDescobro(${idReversion});
	
		$('button[id="btnExportar"]').prop('disabled', false);
		$('button[id="btnHistorial"]').prop('disabled', false);
		$('button[id="btnExportar2"]').prop('disabled', false);
		$('button[id="btnHistorial2"]').prop('disabled', false);
	}else{
	    if ($('#idReversion').val()==""){
		    $('button[id="btnExportar"]').prop('disabled', true);
		    $('button[id="btnHistorial"]').prop('disabled', true);
		    $('button[id="btnExportar2"]').prop('disabled', true);
		    $('button[id="btnHistorial2"]').prop('disabled', true);
		}else{
		    $('button[id="btnExportar"]').prop('disabled', false);
		    $('button[id="btnHistorial"]').prop('disabled', false);
		    $('button[id="btnExportar2"]').prop('disabled', false);
		    $('button[id="btnHistorial2"]').prop('disabled', false);
		}
	    
	    buscarTransacciones(${idCobro});
	}
});
</script>
