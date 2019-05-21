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
<%@ page import="ar.com.telecom.shiva.base.constantes.Propiedades"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><spring:message code="confirmacion.aplicacion.manual.descobro.page.title" /></title>

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

<script src="${pageContext.request.contextPath}/js/descobro-confirmacion-aplicacion-manual.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>

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
</style>
<script>
		var MOTIVO_ADJUNTO_APLICACION_MANUAL = '<%=MotivoAdjuntoEnum.APLICACION_MANUAL.name()%>';
		var MOTIVO_ADJUNTO_COMPROBANTE_DESCOBRO = '<%=MotivoAdjuntoEnum.COMPROBANTE_DESCOBRO.name()%>';
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
								<spring:message code="confirmacion.aplicacion.manual.descobro.page.title" />
							</div>
							<div class="pagos_anticipos">
								<div class="row">
									<div class="span3" align="left">
										<label class="control-label" for="idOperacionDescobro">Id Reversion</label>
										<div class="controls">
											<input id="idOperacionDescobro" name="idOperacionDescobro" type="text" class="input" readonly value="${idOperacionDescobro}" />
										</div>
									</div>
									<div class="span6" style="margin-top: 16px" align="right">
										<div>
											<button type="button" class="btn btn-primary btn-small" id="btnHistorial" onclick="javascript:historialCobro();">
												<i class="icon-white icon-list-alt bigger-120"></i> Ver Historial Reversión
											</button>
											<button type="button" class="btn btn-primary btn-small" id="btnExportar" onclick="javascript:exportarDetalleAExcel();">
												<i class="icon-white icon-download-alt"></i> Exportar Reversión a Excel
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
								<div class="row">
									<div class="span8">
										<div id="alertErrorConfirmacionManual1" class="alert alert-error" align="center" style="display: none;width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
											<button id="btnCruzErrorConfirmacionManual1" class="close" >×</button><strong><spring:message code="error.error"/></strong><br/>
											<span id="errorConfirmacionManual1"></span>
										</div>
									</div>
								</div>
								<div class="row" style="margin-top: 10px">
									<div class="span8">
										<div class="alert alert-error" style="display: none" id="alertErrorGuardado">
											<a class="close">×</a> <strong><spring:message code="error.error" /></strong><br /> <span id="errorGuardado"></span>
										</div>
									</div>
								</div>
								<div id="conf-cobro-tabs">
									<div>
										<div id="inicioCliente">
											<div class="row">
												<div class="span3">
													<label class="control-label" for="selectEmpresa">Empresa</label>
													<div class="controls">
														<input id="selectEmpresa" name="empresa" type="text" class="input" readonly value="${empresas}" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="selectSegmento">Segmento</label>
													<div class="controls">
														<input id="selectSegmento" name="segmento" type="text" class="input" readonly value="${segmentos}" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="monedaOperacion"><spring:message code="conf.cobro.moneda.operacion" /></label>
													<div class="controls">
														<input id="monedaOperacionDesc" name="monedaOperacionDesc" value="${monedaOperacionDesc}" type="text" class="input span3" readonly />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="analista"><spring:message code="conf.cobro.analista" /></label>
													<div class="controls">
														<input id="analista" name="analista" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuario}"/>" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="selectCopropietario"><spring:message code="conf.cobro.copropietario" /></label>
													<div class="controls">
														<input id="selectCopropietario" name="copropietario" type="text" class="input" readonly value="${copropietarios}" />
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="selectMotivo"><spring:message code="conf.cobro.motivo" /></label>
													<div class="controls">
														<input id="selectMotivo" name="motivo" type="text" class="input" readonly value="${motivos}" />
													</div>
												</div>
											</div>
										</div>
										<div class="row" style="margin-top: 20px" id="comboIdReversionPadre">
											<div class="span3">
												<label class="control-label" for="idReversionPadre">  <spring:message
														code="conf.descobro.idReversionPadre" /></label>
												<div class="controls">
													<input id="idReversionPadre" name="idReversionPadre" type="text" class="input" readonly value="${idReversionPadre}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="span3" align="left" id="divLegajo" style="display: none;">
												<label class="control-label" for="idLegajoChequeRechazado">Id Legajo</label>
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
																		<button style="padding: 0px;" type="button" id="btnVer" class="btn btn-xs btn-link" title="Ver detalle"
																			onclick="detalleCobro(null,${cob.idCobro})">
																			<i class="icon-eye-open bigger-120"></i>
																		</button>
																	</div></td>
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
																		<button style="padding: 0px;" type="button" id="btnVer" class="btn btn-xs btn-link" title="Ver detalle"
																			onclick="detalleCobro(null,${cob.idCobro})">
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
															<th style="background-color: #dcdbdb"></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.sistemaDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.tipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.subtipoDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.nroDoc" /></th>
															<th><spring:message code="conf.descobro.documentoRelacionado.importe" /></th>
															<th style="background-color: #dcdbdb"></th>
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
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de comprobantes</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="comprobantesDescobro" class="tablaResultado" width="100%">
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
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"></div>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										
										<!-- ADJUNTAR NUEVOS COMPROBANTES-->
										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message code="confirmacion.aplicacion.manual.descobro.seccion.detalles"/></strong>
										</p>
										
										
										<div class="row" style="margin-top: 20px;">
												<div class="span9">
													<form id="adjComprobanteForm" method="post" action="configuracion-descobro/adjuntarComprobante" enctype="multipart/form-data">
														<label class="control-label" for="comprobanteArch"><spring:message code="confirmacion.aplicacion.manual.descobro.detalle" /></label>
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
										
										<div class="row">
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
												</table>
											</div>
										</div>
										<!-- FIN COMPROBANTES -->
										
										<!-- COMIENZO CODIGO DE OPERACIONES EXTERNAS -->
										
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Sección de Operaciones Externas</strong>
										</p>
										<div class="row" style="margin-top: 20px;">
											<div class="span3">
											<label class="control-label" for="codigoOperacionText">Código de Operación Externa</label>
											 <input type="text" id="codigoOperacionText" maxlength="50" name="codigoOperacionText" /></input>
											<div class="row rowError">
												<div class="span9">
													<span id="errorCodigoOperacion" class="error"></span><!-- hidden="true"-->
												</div>
											</div>
										</div>
										<div class="span3">
											<div style="margin-top: 15px; margin-bottom: 0px; margin-right: 2px">
												<button type="button" class="btn btn-primary btn-small" id="btnAgregarCodigo">
													<i class="icon-white icon-chevron-down"></i> Agregar
												</button>
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
															<th>Código de Operación Externa</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										
										<!-- FIN CODIGO DE OPERACIONES EXTERNAS -->
										
										<div class="row" style="margin-top: 15px" id="prevObservaciones">
											<div class="span9">
												<label class="control-label" for="prevObservText"> Observaciones Anteriores
												</label>
												<textarea class="field span9" id="prevObservText" name="prevObservText" readonly>${prevObservText}</textarea>
											</div>
										</div>
										<div class="row" style="margin-top: 15px" id="observaciones">
											<div class="span9">
												<label class="control-label" for="observText"> Observaciones
												</label>
												<textarea class="field span9" id="observText" name="observText"></textarea>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorObservaciones" class="error" ></span><!-- hidden="true"-->
											</div>
										</div>
										<div class="row" style="margin-top: 17px">
											<div class="span9" align="right">
												<button type="button" class="btn btn-primary btn-small" id="btnConfirmar">
													<i class="icon-white icon-ok"></i> Confirmar
												</button>
												<button type="button" class="btn btn-primary btn-small" id="btnCancelar">
													<i class="icon-white icon-remove"></i> Cancelar
												</button>
											</div>
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
									<form:form id="formDescobro" commandName="descobroDto">
										<input type="hidden" id="idCobro" name="idCobro" value="${idCobro}"/>
										<input type="hidden" id="idDescobro" name="idDescobro" value="${idReversion}"/>
										<input type="hidden" id="idReversion" name="idReversion" value="${idReversion}"/>
										<input type="hidden" id="idCobroPadre" name="idCobroPadre" />
										<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" />
										<input type="hidden" id="idOperacionDescobro" name="idOperacionDescobro" value="${idOperacionDescobro}"/>
										<input type="hidden" id="imputarCobro" name="imputarCobro" />
										<input type="hidden" id="imputarConAprobacion" name="imputarConAprobacion" />
										<input type="hidden" id="vuelvoABandeja" name="vuelvoABandeja" value="${vuelvoABandeja}" />
										<input type="hidden" id="simularCobro" name="simularCobro" />
										<input type="hidden" id="opcion" name="opcion" />
										<input type="hidden" id="volver" name="volver" value="/descobros-confirmacion-aplicacion-manual" />
										<input type="hidden" id="goBack" name="goBack" />
										<input type="hidden" id="idAnalista" id="idAnalista" value="${idUsuario}" />
										<input type="hidden" id="exportarExcel" id="exportarExcel" />
										<input type="hidden" id="modificarTransacciones" name="modificarTransacciones" />
										<input type="hidden" id="estado" />
										<input type="hidden" id="estadoDescripcion" value="${estadoDescripcion}" />
										<input type="hidden" id="marcaDescripcion" value="${marcaDescripcion}" />
										<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
										<input type="hidden" id="volviendoA" value="${idVolver}"/>
										<input type="hidden" id="monedaOperacion" name="monedaOperacion" value="${monedaOperacion}"/>
										<input type="hidden" id="observacion" name="observacion"/>
										<input type="hidden" name="sistema" id="sistema" value="${sistema}"/>
										<input type="hidden" name="sociedad" id="sociedad" value="${sociedad}"/>
									</form:form>
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
	buscarOperacRelac(${idReversion});
});
</script>