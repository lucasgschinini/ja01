<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum"%>
<%@ page
	import="ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum"%>
<%@ page
	import="ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum"%>
<%@ page
	import="ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.SistemaEnum"%>
<%@ page
	import="ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.ConstantesCobro"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>
<%@ page
	import="ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Propiedades"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MonedaEnum"%>
<%@page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum"%>
<%@page
	import="ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum"%>
<%@page
	import="ar.com.telecom.shiva.base.enumeradores.TipoSimularDisabled"%>
<%@page
	import="ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.SiNoEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title><spring:message code="titulo.nombre.app" />&nbsp;-&nbsp;<spring:message
		code="conf.cobro.page.title" />&nbsp;-&nbsp;<spring:message
		code="cobro.busquedaDebito.previsualizar.cobro" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
<!--
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.11/features/scrollResize/dataTables.scrollResize.js"></script>

<script src="//cdn.jsdelivr.net/jquery.scrollto/2.1.2/jquery.scrollTo.min.js"></script>
-->
<link href="${pageContext.request.contextPath}/css/jquery.steps.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/shiva-conf-cobro.js"></script>
<script
	src="${pageContext.request.contextPath}/js/shiva-conf-cobro-compensacion.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script
	src="${pageContext.request.contextPath}/js/custom-paginacion-datatables.js"></script>
<script
	src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script
	src="${pageContext.request.contextPath}/js/funciones-datatables-shiva.js"></script>
	
<script src="${pageContext.request.contextPath}/js/comparadores/string-numero.js"></script>
<script
	src="${pageContext.request.contextPath}/js/shiva-conf-cobro-otros-debitos.js"></script>

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

.gestion-semaforo-naranja {
	background-color: orange;
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

.step-disabled-li-a {
	background: #fff;
}
</style>
<script>
	//Constantes para js
	var OTRAS_COMPENSACIONES = '<%=TipoCreditoEnum.OTRAS_COMPENSACIONES.getValor()%>';
	var LIQUIDOPRODUCTO  = '<%=TipoCreditoEnum.LIQUIDOPRODUCTO.getValor()%>';
	var INTERCOMPANY = '<%=TipoCreditoEnum.INTERCOMPANY.getValor()%>';
	var DESISTIMIENTO = '<%=TipoCreditoEnum.DESISTIMIENTO.getValor()%>';
	var PLANPAGO  = '<%=TipoCreditoEnum.PLANPAGO.getValor()%>';
	var IIBB  = '<%=TipoCreditoEnum.IIBB.getValor()%>';
	var CESION_CREDITOS  = '<%=TipoCreditoEnum.CESION_CREDITOS.getValor()%>';
	var PROVEEDORES  = '<%=TipoCreditoEnum.PROVEEDORES.getValor()%>';
	// Cambio la definicion de sprint 5. En un futuro se va volver a usar (NOTACREDPENDEMI)
	var NOTACREDPENDEMI = 'SIN VALOR';
	// fin de modificacion para sprint 5
	//--
	// 	Validacion antes de simular
	var REINTEGRO_CRED_PROX_FAC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.name()%>';
	var DEBITO_PROX_FAC = '<%=TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.name()%>';
	var REINTEGRO_POR_CHEQUE = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.name()%>';
	var REINTEGRO_PAGO_CUENTA_TERCEROS = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.name()%>';
	var REINTEGRO_CREDITO_RED_INTEL = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.name()%>';
	var REINTEGRO_TRANSFERENCIA_BAN = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.name()%>';
	var ENVIO_A_GANANCIAS = '<%=TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.name()%>';
	var SALDO_A_PAGAR = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.name()%>';
	var SALDO_A_COBRAR = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.name()%>';
	var APLICACION_MANUAL = '<%=TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name()%>';
	
// 	Validacion despues de simular
	var REINTEGRO_CRED_PROX_FAC_DESC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getDescripcion()%>';
	//TODO - a Fabio SIM 0 esta descripcion no va a coincidir con la que viene de la base
	var DEBITO_PROX_FAC_DESC = '<%=TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion()%>';
	var REINTEGRO_POR_CHEQUE_DESC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getDescripcion()%>';
	var REINTEGRO_PAGO_CUENTA_TERCEROS_DESC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getDescripcion()%>';
	var REINTEGRO_CREDITO_RED_INTEL_DESC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getDescripcion()%>';
	var REINTEGRO_TRANSFERENCIA_BAN_DESC = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getDescripcion()%>';
	var ENVIO_A_GANANCIAS_DESC = '<%=TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getDescripcion()%>';
	var SALDO_A_PAGAR_DESC = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.getDescripcion()%>';
	var SALDO_A_COBRAR_DESC = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.getDescripcion()%>';
	
// 	Validacion despues de simular
	var REINTEGRO_CRED_PROX_FAC_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento()%>';
	var DEBITO_PROX_FAC_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getSubTipoDocumento()%>';
	var REINTEGRO_POR_CHEQUE_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getSubTipoDocumento()%>';
	var REINTEGRO_PAGO_CUENTA_TERCEROS_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getSubTipoDocumento()%>';
	var REINTEGRO_CREDITO_RED_INTEL_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getSubTipoDocumento()%>';
	var REINTEGRO_TRANSFERENCIA_BAN_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getSubTipoDocumento()%>';
	//var ENVIO_A_GANANCIAS_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getSubTipoDocumento()%>';
	var ENVIO_A_GANANCIAS_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getSubTipoDocumento()%>';
	var SALDO_A_PAGAR_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.getSubTipoDocumento()%>';
	var SALDO_A_COBRAR_SUBTIPO = '<%=TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.getSubTipoDocumento()%>';
	
// 	Sistema Enum
	var CALIPSO_DESC_CORTA = '<%=SistemaEnum.CALIPSO.getDescripcionCorta()%>';
	var MIC_DESC_CORTA = '<%=SistemaEnum.MIC.getDescripcionCorta()%>';
	var CALIPSO_NAME = '<%=SistemaEnum.CALIPSO.name()%>';
	var MIC_NAME = '<%=SistemaEnum.MIC.name()%>';
	var MIC_DESC_PANTALLA = '<%=SistemaEnum.MIC.getDescripcion()%>';
	var CALIPSO_DESC_PANTALLA = '<%=SistemaEnum.CALIPSO.getDescripcion()%>';
// 	Tipo Documento
	var DUC_NAME = '<%=TipoComprobanteEnum.DUC.name()%>';
	
// 	EstadoAcuerdoFacturacionEnum
	var ESTADO_FACTURACION_ACTIVO_DESC = '<%=EstadoAcuerdoFacturacionEnum.ACTIVO.descripcion()%>';
	
	var ERROR_IMPORTACION_HEAD = '<%=ConstantesCobro.LINEA_NRO%>';
	var ERROR_IMPORTACION_DEBITOS_DUPLICADO_TAIL = '<%=ConstantesCobro.CAUSA + ConstantesCobro.ERROR_REGISTRO_DUPLICADO + Constantes.PUNTO%>';
	var ERROR_NO_DUC_TAIL = '<%=Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_NO_DUC)%>';
	var ERROR_DUC_TAIL = '<%=Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_DUC)%>';
	var ERROR_DUC_TRATAMIENTO = '<%=Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_DUC_CON_TRATAMIENTO)%>';
	var ERROR_TRATAMIENTO_DUC = '<%=Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_TRATAMIENTO_CON_DUC)%>';
	var ERRROR_CAMBIO_COBRO_SIMULADO = '<%=Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.error.simulacion.no.validad")%>';

	var ERROR_CALIPSO_NO_GENERAR_DIFERECIA_CAMBIO = '<%=Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.calipso.no.pudo.generar.diferencia.cambio")%>';
	var ERROR_CALIPSO_NO_GENERAR_DIFERECIA_CAMBIO_OLD = '<%=Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.calipso.no.pudo.generar.diferencia.cambio.old")%>';
	
	
	var TIPO_MENSAJE_OK_NAME = '<%=TipoMensajeEstadoEnum.OK.name()%>';
	var TIPO_MENSAJE_WRN_NAME = '<%=TipoMensajeEstadoEnum.WRN.name()%>';
	var TIPO_MENSAJE_ERROR_NAME = '<%=TipoMensajeEstadoEnum.ERR.name()%>';
	var TIPO_MENSAJE_OK_DESC = '<%=TipoMensajeEstadoEnum.OK.getDescripcion()%>';
	var TIPO_MENSAJE_ERROR_DESC = '<%=TipoMensajeEstadoEnum.ERR.getDescripcion()%>';

	var RETENCION_NAME = '<%=TipoCreditoEnum.RETENCION.name()%>';
	
	var MONEDA_DOLAR_SIGLA = '<%=MonedaEnum.DOL.getSigla()%>';
	var MONEDA_PESOS_SIGLA = '<%=MonedaEnum.PES.getSigla()%>'; 
	var MONEDA_DOLAR_SIGNO_2 = '<%=MonedaEnum.DOL.getSigno2()%>';
	var MONEDA_PESOS_SIGNO_2 = '<%=MonedaEnum.PES.getSigno2()%>';
	
	var SHOW_BUTTON = "${showButton}";
	var FORM_ORIGEN = "${formOrigen}";
	var COB_ERROR_COBRO_DESC = '<%=Estado.COB_ERROR_COBRO.descripcion()%>';
	var COB_ERROR_APROPIACION_DESC = '<%=Estado.COB_ERROR_APROPIACION.descripcion()%>';
	
	var COB_ERROR_COBRO_NAME = '<%=Estado.COB_ERROR_COBRO.name()%>';
	var COB_ERROR_APROPIACION_NAME = '<%=Estado.COB_ERROR_APROPIACION.name()%>';
	
	// Estado para la edicion de la observacion del cobro
	var ESTADO_VEC_OBSERVACION = [
		'<%=Estado.COB_PENDIENTE.descripcion()%>',
		'<%=Estado.COB_PROCESO.descripcion()%>',
		'<%=Estado.COB_COBRADO.descripcion()%>',
		'<%=Estado.COB_CONFIRMADO_CON_ERROR.descripcion()%>',
		'<%=Estado.COB_ERROR_CONFIRMACION.descripcion()%>',
		'<%=Estado.COB_ERROR_DESAPROPIACION.descripcion()%>',
		'<%=Estado.COB_ERROR_APROPIACION.descripcion()%>',
		'<%=Estado.COB_ERROR_COBRO.descripcion()%>',
		'<%=Estado.COB_ERROR_COBRO.descripcion()%>'
	];
	// TODO PARA PAU
	var ESTADO_VEC_FINAL = [
		'<%=Estado.COB_COBRADO.name()%>',
		'<%=Estado.COB_CONFIRMADO_CON_ERROR.name()%>'
	];
	// TODO PARA PAU
	// validacion de debitos importados
	var	DEBITO_VALIDACION_OK_NAME = '<%=EstadoDebitoImportadoEnum.VALIDACION_OK%>';
	var	DEBITO_VALIDACION_ERROR_NAME = '<%=EstadoDebitoImportadoEnum.VALIDACION_ERROR%>';
	var DEBITO_ORIGEN_IMPORTACION_NAME = '<%=OrigenDebitoEnum.IMPORTACION%>';
	var DEBITO_PENDIENTE_NAME = '<%=EstadoDebitoImportadoEnum.PENDIENTE%>';
	var ERROR_SALDO_MAXIMO_DEBITO = '<%=Constantes.ERROR_SALDO_MAXIMO_DEBITO%>';
	var ERROR_VALIDACION_EDICION_GRILLAS = '<%=Constantes.ERROR_VALIDACION_EDICION_GRILLAS%>';

	// Habilitacion btn Simular
	var CAUSA_DISABLED_SIMULAR_ERROR_NAME = '<%=TipoSimularDisabled.ERROR.name()%>';
	var CAUSA_DISABLED_SIMULAR_TRATAMIENTO_NAME = '<%=TipoSimularDisabled.TRATAMIENTO.name()%>';
	// tipo de credito
	var CREDITO_CTA = '<%=TipoComprobanteEnum.CTA.descripcion()%>';
	// Max input campo edtoable
	var MAX_IMPORTE_DEFECTO = <%=ConstantesCobro.maxImportePorDefecto%>;
	
	var SI_NAME = '<%=SiNoEnum.SI.name()%>';
	var NO_NAME = '<%=SiNoEnum.NO.name()%>';

	var SEMAFORO_ROJO_DESCRIPCION ='<%=SemaforoGestionabilidadEnum.ROJO.getDescripcion()%>';
	
	// Compensaciones Sap
	var T_RENGLON_AGRUPADOR = '<%=TipoRenglonSapEnum.AGRUPADOR.name()%>';
	var T_RENGLON_CAP = '<%=TipoRenglonSapEnum.CAP.name()%>';
	var T_RENGLON_REF = '<%=TipoRenglonSapEnum.REF.name()%>';
	var T_RENGLON_FIN = '<%=TipoRenglonSapEnum.FIN.name()%>';
	var MOTIVO_ADJUNTO_APLICACION_MANUAL = '<%=MotivoAdjuntoEnum.APLICACION_MANUAL.name()%>';
	var MOTIVO_ADJUNTO_COMPROBANTE_COBRO = '<%=MotivoAdjuntoEnum.COMPROBANTE_COBRO.name()%>';
	var MOTIVO_ADJUNTO_OTROS_DEBITO = '<%=MotivoAdjuntoEnum.OTROS_DEBITO.name()%>';
	//fin constantes para js
	//fin constantes para js
	var CONFOTROSDEBITOS = ${confOtrosDebitos};
	var CONFCOMBOSOCIEDAD = ${confComboSociedad};
	var CONFCOMBOSISTEMA = ${confComboSistema};
	var CONFCOMBOTIPOCOMPROBANTE = ${confComboTipoComprobante};
	var CONFCOMBOMONEDA = ${confComboMoneda};
	var leyendaComboSeleccionar = '${leyendaComboSeleccionar}';
</script>
</head>
<body>
	<!-- @author u573005 sprint 4 estos enums se usan en la logica de llamada a deimos 
		<input id="semaforoGestionabilidadEnumRojoDescripcion" type="hidden" value=''>
	-->

	<div id="container">
		<jsp:include page="../template/cabecera.jsp"></jsp:include>
		<div id="main">
			<div class="wrap">
				<jsp:include page="../template/menu.jsp"></jsp:include>
				<div id="content">
					<div id="inside">
						<div id="payments" class="box">
							<div class="title">
								<spring:message code="conf.cobro.page.title" />
							</div>
							<div class="pagos_anticipos">
								<div class="row">
									<div class="span3" align="left">
										<label class="control-label" for="prevAnalista">Id
											Operación Cobro</label>
										<div class="controls">
											<input id="idOperacion" name="idOperacion" type="text"
												class="input" readonly />
										</div>
									</div>
									<div class="span6" style="margin-top: 16px" align="right">
										<div>
											<button type="button" class="btn btn-primary btn-small"
												id="btnHistorial" onclick="javascript:historialCobro();">
												<i class="icon-white icon-list-alt bigger-120"></i> Ver
												Historial
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnExportar" onclick="javascript:exportarDetalle();">
												<i class="icon-white icon-download-alt"></i> Exportar Cobro
												a Excel
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnGuardar">
												<i class="icon-white icon-ok"></i> Guardar
											</button>
											<button type="button" class="btn btn-primary btn-small"
												style="display: none;" id="btnVolver"
												onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i> Volver
											</button>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="span3" align="left" style="display: none;"
										id="divEstado">
										<label class="control-label" for="cobroEstado">Estado
											Cobro</label>
										<div class="controls">
											<input text id="cobroEstado" name="cobroEstado" type="text"
												class="input" readonly />
										</div>
									</div>
									<div class="span6" align="left" style="display: none;"
										id="divCobroSubEstados">
										<label class="control-label" for="cobroSubEstados">Sub
											Estado Cobro</label>
										<div class="controls">
											<input text id="cobroSubEstados" name="cobroSubEstados"
												type="text" class="input span6" readonly />
										</div>
									</div>
									<div id="divBtnCompensacion" style="display: none"
										class="span6" align="right">
										<br />
										<button type="button" class="btn btn-primary btn-small"
											id="btnVerCarta">
											<i class="icon-white icon-download-alt"></i>
											<spring:message code="conf.cobro.tabla.btn.verCarta" />
										</button>
										<button type="button" class="btn btn-primary btn-small"
											id="btnVerResumenCompensacion">
											<i class="icon-white icon-download-alt"></i>
											<spring:message code="conf.cobro.tabla.btn.verResumen" />
										</button>
									</div>
								</div>
								<div class="row" style="margin-top: 10px">
									<div class="span8">
										<div class="alert alert-error" style="display: none"
											id="alertErrorGuardado">
											<a class="close">×</a> <strong><spring:message
													code="error.error" /></strong><br /> <span id="errorGuardado"></span>
										</div>
									</div>
								</div>
								<div id="conf-cobro-tabs">
									<h3>
										<spring:message code="conf.cobro.tab.title.clientes" />
									</h3>
									<div class="bloqueCliente">
										<div id="inicioCliente">
											<!-- BUSQUEDA CLIENTES -->
											<div class="row">
												<div class="span3">
													<label class="control-label" for="empresa"><span
														class="rojo">* </span> <spring:message
															code="conf.cobro.empresa" /></label>
													<div class="controls">
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
												<div class="span3">
													<label class="control-label" for="segmento"><span
														class="rojo">* </span> <spring:message
															code="conf.cobro.segmento" /></label>
													<div class="controls">
														<select id="selectSegmento" name="segmento" class="input">
															<c:if test="${comboSegmento}">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${segmentos}" var="seg">
																<option value="${seg.idSegmento}">${seg.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="monedaOperacion"><span
														class="rojo">* </span> <spring:message
															code="conf.cobro.moneda.operacion" /></label>
													<div class="controls">
														<select id="selectMonedaOperacion" name="monedaOperacion"
															class="input">
															<c:forEach items="${monedasOperacion}" var="mon">
																<option value="${mon.signo2}">${mon.descripcion}</option>
															</c:forEach>
														</select>
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
											</div>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="analista"><spring:message
															code="conf.cobro.analista" /></label>
													<div class="controls">
														<input id="analista" name="analista" type="text"
															class="input" readonly
															value="<c:out value = "${nombreCompletoUsuario}"/>"
															style="margin: 0 auto;" />
													</div>
												</div>

												<div class="span3">
													<label class="control-label" for="copropietario"><spring:message
															code="conf.cobro.copropietario" /></label>
													<div class="controls">
														<select id="selectCopropietario" name="copropietario"
															class="input">
															<c:if test="${comboCopropietarios}">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${copropietarios}" var="cop">
																<option value="${cop.tuid}">${cop.nombreCompleto}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="motivo"><span
														class="rojo">* </span> <spring:message
															code="conf.cobro.motivo" /></label>
													<div class="controls">
														<select id="selectMotivo" name="motivo" class="input">
															<c:if test="${comboMotivo}">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
															</c:if>
															<c:forEach items="${motivos}" var="mot">
																<option value="${mot.idMotivoCobro}">${mot.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3"></div>
												<div class="span3">
													<span id="errorCopropietario" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorMotivo" class="error"></span>
												</div>
											</div>
											<div class="row" id="cotizacionOnlineDiv"
												style="display: none;">
												<div class="span3">
													<label class="control-label" for="fechaTipoCambio"><span
														class="rojo">* </span>
													<spring:message
															code="conf.cobro.cotizacion.fechaTipoCambio" /></label>
													<div class="controls">
														<input class="span3" type="text" id="fechaTipoCambio"
															data-date-format="dd/mm/yyyy" data-date-language='es'
															name="fechaTipoCambio" maxlength="10">
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="tipoDeCambio"><span
														class="rojo">* </span>
													<spring:message code="conf.cobro.cotizacion.tipoDeCambio" /></label>
													<div class="controls">
														<textarea id="tipoDeCambio" name="tipoDeCambio"
															type="text" readonly
															style="margin: 0 auto; height: 20px; resize: none;" /></textarea>
													</div>
												</div>
												<div class="span3"></div>
											</div>
											<div class="row rowError" style="margin-bottom: 40px;">
												<div class="span3">
													<span id="errorFechaTipoCambio" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorTipoDeCambio" class="error"></span>
												</div>
												<div class="span3"></div>
											</div>
										</div>
										<div class="row" style="margin-top: 20px; display: none;"
											id="prevObservacionesAnterior">
											<div class="span9">
												<label class="control-label" for="prevObservTextAnterior">Observaciones
													Anteriores</label>
												<textarea class="field span9" id="prevObservTextAnterior"
													readonly name="prevObservTextAnterior"></textarea>
											</div>
										</div>
										<div class="row" style="margin-top: -15px"
											id="prevObservaciones">
											<div class="span9">
												<label class="control-label" for="prevObservText"> <span
													id="idSpan" class="rojo">* </span>Observaciones
												</label>
												<textarea class="field span9" id="prevObservText"
													name="prevObservText" maxlength="250"></textarea>
											</div>
										</div>
										<div class="row rowError" style="margin-bottom: 20px;">
											<div class="span6">
												<span id="errorObservaciones" class="error"></span>
											</div>
										</div>
										<div id='searchCriteraCliente'>
											<p style="padding: 0 0 15px 0;">
												<strong><spring:message
														code="conf.cobro.body.title.criterios" /></strong>
											</p>

											<div class="row">
												<div class="span3">
													<div class="controls">
														<select id="selectCriterio" name="selectCriterio"
															class="input">
															<option value=""><spring:message
																	code="combo.seleccionar" /></option>
															<c:forEach items="${criterioBusquedaCliente}"
																var="criterioBusquedaCliente">
																<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
													<div class="controls">
														<input id="textCriterio" name="textCriterio" type="text"
															maxlength="13" class="input" style="margin: 0 auto;" />
													</div>
												</div>
												<div class="span3">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnBuscarClientes">
															<i class="icon-white icon-search"></i>
															<spring:message code="accion.find" />
														</button>
													</div>
												</div>
											</div>
											<div class="row rowError" style="margin-bottom: 40px;">
												<div class="span3">
													<span id="errorSelectCriterio" class="error"></span>
												</div>
												<div class="span3">
													<span id="errorBusqueda" class="error"></span>
												</div>
												<div class="span3"></div>
											</div>
										</div>
										<p style="padding: 5px 0px;">
											<strong><spring:message
													code="conf.cobro.clientes.busqueda.resultado" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientes" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th class="numeroOrder"><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message
													code="conf.cobro.clientes.seleccionados" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientesSeleccionados" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th class="numeroOrder"><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9" align="left">
												<span id="errorClienteConDebitos" class="error"></span>
											</div>
										</div>
										<!-- FIN BUSQUEDA CLIENTES -->
									</div>
									<!-- DEBITOS -->
									<h3>
										<spring:message code="conf.cobro.tab.title.debitos" />
									</h3>
									<div class="bloqueDebitos">
										<p style="padding: 0px 0px 5px 0px;">
											<strong><spring:message
													code="conf.cobro.clientes.seleccionados" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientesDebitos" class="tablaResultado">
													<thead>
														<tr>
															<th><input type="checkbox" id="debTodosClientes"
																checked /></th>
															<th></th>
															<th class="numeroOrder"><spring:message
																	code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.cuit" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.agencia" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorClientesDeb" class="error"></span>
											</div>
										</div>

										<div class="bloqueBuscarDebitos">
											<div id="searchCriteraDebito">
												<p style="padding: 40px 0 15px 0;">
													<strong>Criterios de búsqueda de débitos</strong>
												</p>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="tipoDoc">Tipo de
															documento</label>
														<div class="controls">
															<select id="selectTipoDoc" name="tipoDoc" class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${listaDebitoTipoDocumentos}"
																	var="tipoDoc">
																	<option value="${tipoDoc.valor}">${tipoDoc.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="nroDoc">Nro.
															Documento</label>
														<div class="controls">
															<input id="nroDoc" name="nroDoc" type="text"
																class="input" style="margin: 0 auto;" maxlength="15" />
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="sistema">Sistema</label>
														<div class="controls">
															<select id="selectSistema" name="sistema" class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${listaDebitosSistema}" var="sist">
																	<option value="${sist.descripcionCorta}">${sist.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorTipoDoc" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorNroDoc" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorSistema" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="moneda">Moneda</label>
														<div class="controls">
															<select id="selectMoneda" name="moneda" class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${monedas}" var="mon">
																	<option value="${mon.sigla}">${mon.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="refMIC">Nro.
															Referencia MIC</label>
														<div class="controls">
															<input id="refMIC" name="refMIC" type="text"
																class="input" style="margin: 0 auto;" maxlength="15" />
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="acuerdoFac">Acuerdo
															Facturación</label>
														<div class="controls">
															<input id="acuerdoFac" name="acuerdoFac" type="text"
																class="input" style="margin: 0 auto;" maxlength="10" />
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorMoneda" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorRefMIC" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorAcuerdoFac" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="vencimientoDesde">Fecha
															desde</label>
														<div class="controls">
															<input class="span3" type="text" id="vencimientoDesde"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="vencimientoDesde">
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="vencimientoHasta">Fecha
															hasta</label>
														<div class="controls">
															<input class="span3" type="text" id="vencimientoHasta"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="vencimientoHasta">
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorFechaDesde" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorFechaHasta" class="error"></span>
													</div>
													<div class="span3"></div>
												</div>
											</div>
											<div class="row">
												<div class="span9" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnBuscarDebitos">
															<i class="icon-white icon-search"></i>
															<spring:message code="accion.find" />
														</button>
													</div>
												</div>
											</div>
										</div>
										<p style="padding: 5px 0px;">
											<strong>Listado de débitos</strong>
										</p>
<%-- 										<jsp:include page="../template/grillas-unificadas/debitos/grilla-debitos.jsp"></jsp:include> --%>
									 
										<div class="row">
											<div class="span9">
												<table id="debitos" class="tablaResultado">
													<thead>
														<tr>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th class="date">F. Vencimiento</th>
															<th class="importe">Saldo 1er Vto. Moneda Origen</th>
															<th>Moneda</th>
															<th>Importe al 1er Vto.</th>
															<th>Importe al 2do Vto.</th>
															<th>Saldo pesificado F. Emisión</th>
															<th>Estado Cptos. de 3ros</th>
															<th class="importe">Importe 3ros Transferidos</th>
															<th>Estado origen</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/compensación en proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th>Tipo de Cambio</th>
															<th class="date">F. Emisión</th>
															<th>Nro. Convenio</th>
															<th>Cuota Convenio</th>
															<th class="date">F. ult. pago parcial</th>
														</tr>
													</thead>
												</table>
												<div class="customPag_info" id="customPagDebitos_info">
													<span>Mostrando registros del 0 al 0 de 0 registros
														en total</span>
												</div>
												<div class="customPag_simple" id="customPagDebitos_paginate">
													<a
														class="customPaginate_button customPaginatePrevious disabled"
														id="customPagDebitos_previous">Anterior</a><a
														class="customPaginate_button customPaginateNext disabled"
														id="customPagDebitos_next">Siguiente</a>
												</div>
											</div>
										</div>

										<div class="row rowInformacion">
											<div class="span9">
												<span id="informacionRespuestaDebitos" class="informacion"></span>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorRespuestaDebitos" class="error"></span>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de débitos seleccionados</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="debitosSeleccionados" class="tablaResultado">
													<thead>
														<tr>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th class="date">F. Vencimiento</th>
															<th class="importe">Saldo 1er Vto. Moneda Origen</th>
															<th>Moneda</th>
															<th class="importe">Importe al 1er Vto.</th>
															<th class="importe">Importe al 2do Vto.</th>
															<th class="importe">Saldo pesificado F. Emisión</th>
															<th>Cobrar al 2do Venc.</th>
															<!-- Editable checkbox -->
															<th>Destransferir 3ros</th>
															<!-- Editable checkbox -->
															<th>Importe a cobrar</th>
															<!-- Editable text -->
															<th>Estado Cptos. de 3ros</th>
															<th class="importe">Importe 3ros Transferidos</th>
															<th>Estado Origen</th>
															<th>Reclamo en Origen</th>
															<th>Migrado en Origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/compensación en proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th>Tipo de Cambio</th>
															<th class="date">F. Emisión</th>
															<th>Sin Dif. de Cambio</th>
															<!-- Editable checkbox -->
															<th>Nro. Convenio</th>
															<th>Cuota Convenio</th>
															<th class="date">F. ult. pago parcial</th>
															<th>Resultado validación</th>
															<th></th>
															<th></th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row" style="margin-top: 20px;"
											id="bloqueSubirDebitos">
											<div class="span9">
												<form id="impDebitos" method="post"
													action="configuracion-cobro/importarDebitos"
													enctype="multipart/form-data">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" /> <label class="control-label">Importar
														débitos</label> <input type="hidden" id="cantidadDebitosEnGrilla"
														name="cantidadDebitosEnGrilla" />
													<div style="float: right;">
														<button type="button" class="btn btn-primary btn-small"
															id="btnImportarDebitos" disabled>
															<i class="icon-white icon-upload"></i>&nbsp;&nbsp;Importar
														</button>
													</div>
													<div class="fileupload fileupload-new"
														data-provides="fileupload">
														<input type="hidden">
														<div id="idSelecArchivo" class="input-append">
															<div class="uneditable-input span3"
																style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																<span class="fileupload-preview"
																	style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
															</div>
															<span class="btn btn-file btn-primary btn-small"
																style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																<span class="fileupload-new">Seleccionar archivo</span>
																<span class="fileupload-exists">Cambiar</span><input
																name="impDebitosArch" id="impDebitosArch" type="file" />
															</span> <a href="#"
																class="btn fileupload-exists btn-primary btn-small"
																style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																data-dismiss="fileupload">Eliminar</a>

														</div>
													</div>

												</form>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorArchDebitos" class="error" hidden="true"></span>
											</div>
										</div>
										<div class="row" style="margin-top: 20px"
											id="resultadoImportacion">
											<div class="span9">
												<label class="control-label" for="resultadoImpText">Resultado
													importación</label>
												<textarea class="field span9" id="resultadoImpText"
													name="resultadoImpText" readonly></textarea>
											</div>
										</div>
										<p style="padding: 30px 0px 5px 0px;">
											<strong>Totales documentos</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span3">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p1">Total
														Débitos</label> <input id="prevSumDebitos_p1" value="$0,00"
														name="prevSumDebitos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p1">Total
														MP</label> <input id="prevSumCreditos_p1" value="$0,00"
														name="prevSumCreditos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p1">Diferencia</label>
												<input id="prevTotal_p1" value="$0,00" name="prevTotal"
													type="text" class="input sigmaInputText" readonly />
											</div>
										</div>
									</div>
									
									<!-- FIN DEBITOS -->
									
									<!--OTROS DEBITOS -->
									<h3><spring:message code="cobro.conf.otros.debitos"/></h3>
									<div class="bloqueOtrosDebitos">
										<p style="padding: 0px 0px 5px 0px;">
											<strong><spring:message code="conf.cobro.clientes.seleccionados"/></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientesOtrosDebitos" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th class="numeroOrder"><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowError conf" id="errorClienteDiv" style="display: none;">
											<div class="span9">
												<span id="errorClienteOtrosDeb" class="error errorOtrosDeb"></span>
											</div>
										</div>
										<div class="bloqueSociedadYSistema" id="bloqueSociedadYSistema">
											<p style="padding: 0px 0px 5px 0px;">
												<strong><spring:message code="cobro.conf.otros.debitos.agregar.otros.debitos"/></strong>
											</p>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="sociedadOtrosDeb"><span
														class="rojo">* </span><spring:message code="cobro.conf.otros.debitos.sociedad"/></label>
													<div class="controls">
														<select id="selectSociedadOtrosDeb" name="sociedadOtrosDeb" class="input">
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="sistemaOtrosDeb"><span
														class="rojo">* </span><spring:message code="cobro.conf.otros.debitos.sistema"/></label>
													<div class="controls">
														<select id="selectSistemaOtrosDeb" name="sistema" class="input">
														</select>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="tipoDebitoOtrosDeb"><span
														class="rojo">* </span><spring:message code="cobro.conf.otros.debitos.tipo.debito"/></label>
													<div class="controls">
														<select id="selectTipoDebitoOtrosDeb" name="tipoDebitoOtrosDeb" class="input">
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="bloqueReferenciaPago" id="bloqueReferenciaPago">
											<p style="padding: 0px 0px 5px 0px;">
												<strong><spring:message code="cobro.conf.otros.debitos.referencias.pago"/></strong>
											</p>
											<div class="row">
													<div id="clienteDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="textClienteOtrosDeb">
															<span class="rojo" style="display: none;" id="clienteObligatorio">* </span><spring:message code="valor.aviso.pago.tabla.clientes.cliente"/>
															</label>
														<div class="controls" class="span1">
															<input id="textClienteOtrosDeb" name="textClienteOtrosDeb" type="text" readonly/>
														</div>
													</div>
													<div id="referenciaPagoDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="textReferenciaPagoOtrosDeb">
														<span class="rojo" style="display: none;" id="referenciaPagoObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.referencia.pago"/></label>
														<div class="controls" class="span1">
															<input id="textReferenciaPagoOtrosDeb" name="textReferenciaPagoOtrosDeb" type="text"/>
														</div>
													</div>
													<div id="numeroDocumentoDiv" style="display: none;"  class="conf span3">
														<label class="control-label" for="textDocumentoOtrosDeb">
														<span class="rojo" style="display: none;" id="numeroDocumentoObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.documento"/></label>
														<div class="controls" class="span1">
															<input id="textNumeroDocumentoOtrosDeb" name="textNumeroDocumentoOtrosDeb" type="text"/>
														</div>
													</div>
											</div>
											<div class="row rowError">
												<div class="span3" style="display: block;" >
												</div>
												<div class="span3 conf" id="errorReferenciaPagoDiv">
													<span id="errorReferenciaPagoOtrosDeb" style="display: none;" class="error errorOtrosDeb"></span>
												</div>
												<div class="span3 conf" id="errorNumeroDocumentoDiv">
													<span id="errorNumeroDocumentoOtrosDeb" style="display: none;" class="error errorOtrosDeb"></span>
												</div>
											</div>
											
											<div class="row ">
												<div style="display: none;" id="fechaVencimientoDiv" class="conf span3">
													<label class="control-label" for="textFechaVencimientoOtrosDeb">
														<span class="rojo" style="display: none;" id="fechaVencimientoObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.fecha.vencimiento"/></label>
														<div class="controls">
															<input class="span3" type="text" id="textFechaVencimientoOtrosDeb"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="fechaVencimiento">
														</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3 conf" id="errorFechaVencimientoDiv">
													<span id="errorFechaVencimientoOtrosDeb" style="display: none;"class="error errorOtrosDeb"></span>
												</div>
												<div class="span3" style="display: none;" >
												</div>
												<div class="span3" style="display: none;" >
												</div>
											</div>
											<div class="row">
												<div class="span3">
													<div id="moneda">
														<label class="control-label" for="monedaOtrosDeb">
														<span class="rojo" id="monedaObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.moneda"/></label>
															<div class="controls">
																<select id="selectMonedaOtrosDeb" name="monedaOtrosDeb" class="input">
																</select>
															</div>
													</div>
												</div>
													<div id="importeDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="textImporteOtrosDeb">
														<span class="rojo" style="display: none;" id="importeObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.importe"/></label>
														<div class="controls" class="span1">
															<span id="monedaOperacionOtroDeb" style="float: left">U$S</span>
															<input id="textImporteOtrosDeb" name="textImporteOtrosDeb" type="text" style="margin: 0 auto; width: 170px"/>
														</div>
													</div>
													<div id="tipoCambioDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="textTipoCambioOtrosDeb">
														<span class="rojo" style="display: none;" id="tipoCambioObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.tipo.cambio"/></label>
														<div class="controls" class="span1">
															<input id="textTipoCambioOtrosDeb" name="textTipoCambioOtrosDeb" type="text"/>
														</div>
													</div>
											</div>
											<div class="row rowError errorOtrosDeb">
												<div class="span3 conf" id="errorMonedaDiv" style="display: none;">
													<span id="errorMonedaOtrosDeb"  class="error errorOtrosDeb"></span>
												</div>
												<div class="span3 conf" id="errorImporteDiv" style="display: none;">
													<span id="errorImporteOtrosDeb"  class="error errorOtrosDeb"></span>	
												</div>
												<div class="span3 conf" id="errorTipoCambioDiv" style="display: none;">
													<span id="errorTipoCambioOtrosDeb"  class="error errorOtrosDeb"></span>	
												</div>
											</div>
											<div class="row">
													<div id="importeMonedaOrigenDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="textImporteMonedaOrigenOtrosDeb">
															<spring:message code="cobro.conf.otros.debitos.importe.moneda.origen"/></label>
														<div class="controls" class="span1">
															<span id="monedaOperacionOrigenOtrosDeb" style="float: left">U$S</span>
															<input id="textImporteMonedaOrigenOtrosDeb" name="textImporteOtrosDeb" type="text" style="margin: 0 auto; width: 170px" readonly/>
														</div>
													</div>
													<div id="sinDiferenciaDeCambioDiv" style="display: none;" class="conf span3">
														<label class="control-label" for="sinDiferenciaDeCambioOtrosDeb">
													<span class="rojo" style="display: none;" id="sinDiferenciaDeCambioObligatorio">* </span><spring:message code="cobro.conf.otros.debitos.sin.diferencia.cambio"/></label>
															<div class="controls">
																<select id="textSinDiferenciaDeCambioOtrosDeb" name="sinDiferenciaDeCambioOtrosDeb"
																	class="input">
																	<option value="" selected><spring:message
																			code="combo.seleccionar" /></option>
		 															<option value="S">Si</option>
																		<option value="N">No</option>
																</select>
															</div>
													</div>
											</div>
											<div class="row rowError">
												<div class="span3" >
													<span id="errorXXXX"  class="error"></span>
												</div>
												<div class="span3 conf" id="errorSinDiferenciaDeCambioDiv" style="display: none;">
													<span id="errorSinDiferenciaDeCambioOtrosDeb" class="error errorOtrosDeb"></span>
												</div>
												<div class="span3" >
													<span id="errorXXX" class="error"></span>
												</div>
												
											</div>
											
	

											<div id="agregarDetalleDiv" style="display: none;" class="conf">
												<p style="padding: 30px 0px 5px 0px;">
													<span class="rojo">* </span><strong><spring:message
															code="cobro.conf.otros.debitos.detalle" /></strong>
												</p>
												<div class="row" style="margin-top: 20px;">
													<div class="span9">
														<form id="adjComprobanteFormOtrosDeb" method="post"
															action="configuracion-cobro/adjuntarComprobante"
															enctype="multipart/form-data">
															<label class="control-label"
																for="comprobanteArchOtrosDeb"><span class="rojo" style="display: none;" id="agregarDetalleObligatorio">* </span>Detalle</label>
															<div style="float: right;">
														<button type="button" class="btn btn-primary btn-small"
															id="btnAdjComprobanteOtrosDeb" disabled>
															<i class="icon-white icon-upload"></i>&nbsp;&nbsp;Adjuntar
														</button>
													</div>
															<div class="fileupload fileupload-new"
																data-provides="fileupload">
																<input type="hidden">
																
																<div class="input-append">
																	<div class="uneditable-input span3 borrarDetalle"
																		style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																		<span id="nombreComprobanteOtrosDeb" class="fileupload-preview"
																			style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																	</div>
																	
																	<span class="btn btn-file btn-primary btn-small"
																		style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																		<span class="fileupload-new">Seleccionar
																			archivo</span> <span class="fileupload-exists">Cambiar</span><input
																		name="comprobanteArch" id="comprobanteArchOtrosDeb"
																		type="file" />
																	</span> <a href="#"
																		id="borrarComprobanteOtrosDeb"
																		class="btn fileupload-exists btn-primary btn-small"
																		style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																		data-dismiss="fileupload">Eliminar</a>
																</div>
															</div>
														</form>
													</div>
												</div>
												<div class="row rowError" id="errorAgregarDetalleDiv" style="display: none;">
													<div class="span9">
														<span id="errorAgregarDetalleOtrosDeb" class="error errorOtrosDeb"></span>
													</div>
												</div>
												<div class="row" >
													<div class="span9">
															<table id="comprobantesOtrosDebito" class="tablaResultado" width="100%">
																<thead>
																	<tr>
																		<th></th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																	</tr>
																</thead>
															</table>
													</div>
												</div>
											<label for="mantenerComprobantesAdjuntosVal">
												<input id="mantenerComprobantesAdjuntosVal" type="checkbox" onclick="checkComprobantes();"/>
												<c:if test="${CobroOtrosDebitoDto.mantenerComprobantesAdjuntos}">checked</c:if>&nbsp;
												<spring:message code="valor.mantenerComprobantesAdjuntos" />
											</label>
											</div>
											<div style="float: right; margin-top: 17px; margin-right: 18px" align="right">
												<div>
													<button type="button" class="btn btn-primary btn-small" id="btnAgregarOtroDeb" disabled>
														<i class="icon-white icon-chevron-down"></i><spring:message code="cobro.conf.otros.debitos.boton.agregar"/>
													</button>
												</div>
												<div class="span9">
													<span id="errorAgregarOtroDeb" class="error errorOtrosDeb"></span>
												</div>
											</div>
										</div>
<!-- 										<div style="float: right; margin-top: 17px; margin-right: 18px" align="right"> -->
<!-- 											<div> -->
<!-- 												<button type="button" class="btn btn-primary btn-small" id="btnAgregarOtroDeb" disabled> -->
<%-- 													<i class="icon-white icon-chevron-down"></i><spring:message code="cobro.conf.otros.debitos.boton.agregar"/> --%>
<!-- 												</button> -->
<!-- 											</div> -->
<!-- 											<div class="span9"> -->
<!-- 												<span id="errorAgregarOtroDeb" class="error errorOtrosDeb"></span> -->
<!-- 											</div> -->
<!-- 										</div> -->
										
										
										<br>
										<br>
										<br>
										<div class="bloqueGrillaOtrosDebitos">
											<p style="padding: 0px 0px 5px 0px;">
												<strong><spring:message code="cobro.conf.otros.debitos.grilla.otros.debitos"/></strong>
											</p>
											<div class="row">
												<div class="span9">
													<table id="grillaOtrosDeb" class="tablaResultado">
														<thead>
															<tr>
																<th></th>
																<th><spring:message code="cobro.conf.otros.debitos.sociedad"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.sistema"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.tipo.debito"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.referencia.pago"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.cliente"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.numero.legal"/></th>
																<th class="date"><spring:message code="cobro.conf.otros.debitos.fecha.vencimiento"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.moneda"/></th>
																<th class="importe"><spring:message code="cobro.conf.otros.debitos.tipo.cambio"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.importe"/></th>
																<th class="importe"><spring:message code="cobro.conf.otros.debitos.importe.moneda.origen"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.sin.diferencia.cambio"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.nombre.adjunto"/></th>
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorGrillaOtrosDeb" class="error errorOtrosDeb"></span>
												</div>
											</div>
										</div>
<!-- 											Importar Otros Debitos -->
										<div class="row" style="margin-top: 20px;"
											id="bloqueSubirOtrosDebitos">
											<div class="span9">
												<form id="impOtrosDebitos" method="post"
													action="configuracion-cobro/importarOtrosDebitos"
													enctype="multipart/form-data">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" /> <label class="control-label">Importar
														débitos</label> <input type="hidden" id="cantidadOtrosDebitosEnGrilla"
														name="cantidadOtrosDebitosEnGrilla" />
													<div style="float: right;">
														<button type="button" class="btn btn-primary btn-small"
															id="btnImportarOtrosDebitos" disabled>
															<i class="icon-white icon-upload"></i>&nbsp;&nbsp;Importar
														</button>
													</div>
													<div class="fileupload fileupload-new"
														data-provides="fileupload">
														<input type="hidden">
														<div id="idSelecArchivo" class="input-append">
															<div class="uneditable-input span3"
																style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																<span class="fileupload-preview"
																	style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
															</div>
															<span class="btn btn-file btn-primary btn-small"
																style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																<span class="fileupload-new">Seleccionar archivo</span>
																<span class="fileupload-exists">Cambiar</span><input
																name="impOtrosDebitosArch" id="impOtrosDebitosArch" type="file" />
															</span> <a href="#"
																class="btn fileupload-exists btn-primary btn-small"
																style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																data-dismiss="fileupload">Eliminar</a>

														</div>
													</div>

												</form>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorArchOtrosDebitos" class="error" hidden="true"></span>
											</div>
										</div>
										<div class="row" style="margin-top: 20px"
											id="resultadoImportacionOtrosDebitos">
											<div class="span9">
												<label class="control-label" for="resultadoImpOtrosDebText"><spring:message code="cobro.conf.otros.debitos.resultado.importacion" /></label>
												<textarea class="field span9" id="resultadoImpOtrosDebText"
													name="resultadoImpOtrosDebText" readonly></textarea>
											</div>
										</div>
									
										<p style="padding: 30px 0px 5px 0px;">
											<strong>Totales documentos</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span3">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p5">Total
														Débitos</label> <input id="prevSumDebitos_p5" value="$0,00"
														name="prevSumDebitos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p5">Total
														MP</label> <input id="prevSumCreditos_p5" value="$0,00"
														name="prevSumCreditos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p5">Diferencia</label>
												<input id="prevTotal_p2" value="$0,00" name="prevTotal"
													type="text" class="input sigmaInputText" readonly />
											</div>
										</div>
									</div>
									
									<!--FIN OTROS DEBITOS -->
									
									<!-- CREDITOS -->
									<h3>Créditos</h3>
									<div>
										<p style="padding: 0px 0px 5px 0px;">
											<strong><spring:message
													code="conf.cobro.clientes.seleccionados" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientesCreditos" class="tablaResultado">
													<thead>
														<tr>
															<th><input type="checkbox" id="credTodosClientes"
																checked /></th>
															<th></th>
															<th class="numeroOrder"><spring:message
																	code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.cuit" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.agencia" /></th>
															<th><spring:message
																	code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorClientesCred" class="error"></span>
											</div>
										</div>
										<div class="bloqueBuscarCreditos">
											<div id="searchCriteraCredito">
												<p style="padding: 40px 0 15px 0;">
													<strong>Criterios de búsqueda de créditos</strong>
												</p>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="credTipoMP">Tipo
															Medio Pago</label>
														<div class="controls">
															<select id="credSelectTipoMP" name="credTipoMP"
																class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${mediosPago}" var="mp">
																	<option value="${mp.valor}">${mp.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="credSistema">Sistema</label>
														<div class="controls">
															<select id="credSelectSistema" name="credSistema"
																class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${listaCreditoSistema}" var="sist">
																	<option value="${sist.descripcionCorta}">${sist.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="credMoneda">Moneda</label>
														<div class="controls">
															<select id="credSelectMoneda" name="credMoneda"
																class="input">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${monedas}" var="mon">
																	<option value="${mon.sigla}">${mon.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorCredTipoMP" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorCredSistema" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorCredMoneda" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="credDesde">Fecha
															Desde</label>
														<div class="controls">
															<input class="span3" type="text" id="credDesde"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="credDesde">
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="credHasta">Fecha
															Hasta</label>
														<div class="controls">
															<input class="span3" type="text" id="credHasta"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="credHasta">
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="credAcuerdo">Acuerdo
															de facturación</label>
														<div class="controls">
															<input id="credAcuerdo" name="credAcuerdo" type="text"
																class="input" style="margin: 0 auto;" maxlength="10" />
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorCredDesde" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorCredHasta" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorCredAcuerdo" class="error"></span>
													</div>
												</div>
											</div>
											<div class="row" style="margin-left: -10px">
												<div class="span9" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnBuscarCreditos">
															<i class="icon-white icon-search"></i>
															<spring:message code="accion.find" />
														</button>
													</div>
												</div>
											</div>
										</div>
										<p style="padding: 5px 0px;">
											<strong>Listado de créditos</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="creditos" class="tablaResultado">
													<thead>
														<tr>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th class="date">Fecha Valor</th>
															<th class="importe">Saldo Moneda Origen</th>
															<th>Moneda</th>
															<th class="importe">Imp. Moneda Origen</th>
															<th class="importe">Imp. pesificado</th>
															<th class="importe">Saldo pesificado</th>
															<th class="date">Fecha alta</th>
															<th class="date">Fecha ingreso recibo</th>
															<th class="date">Fecha depósito</th>
															<th class="date">Fecha transferencia</th>
															<th class="date">Fecha emisión</th>
															<th class="date">Fecha vencimiento</th>
															<th class="date">Fecha último movimiento</th>
															<th>Tipo de Cambio</th>
															<th>Provincia</th>
															<th>CUIT</th>
															<th>Estado Origen</th>
															<th>Motivo</th>
															<th>Reclamo en Origen</th>
															<th>Migrado en Origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/Compensacion en Proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th class="date"></th>
														</tr>
													</thead>
												</table>
												<div class="customPag_info" id="customPagCreditos_info">
													<span>Mostrando registros del 0 al 0 de 0 registros
														en total</span>
												</div>
												<div class="customPag_simple"
													id="customPagCreditos_paginate">
													<a
														class="customPaginate_button customPaginatePrevious disabled"
														id="customPagCreditos_previous">Anterior</a><a
														class="customPaginate_button customPaginateNext disabled"
														id="customPagCreditos_next">Siguiente</a>
												</div>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorRespuestaCreditos" class="error"></span>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de créditos seleccionados</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="creditosSeleccionados" class="tablaResultado">
													<thead>
														<tr>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo Documento</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th class="date">Fecha Valor</th>
															<th class="importe">Saldo Moneda Origen</th>
															<th>Moneda</th>
															<th class="importe">Imp. Moneda Origen</th>
															<th class="importe">Imp. pesificado</th>
															<th class="importe">Saldo pesificado</th>
															<th>Importe a utilizar</th>
															<!-- Editable text -->
															<th class="date">Fecha alta</th>
															<th class="date">Fecha ingreso recibo</th>
															<th class="date">Fecha depósito</th>
															<th class="date">Fecha transferencia</th>
															<th class="date">Fecha emisión</th>
															<th class="date">Fecha vencimiento</th>
															<th class="date">Fecha último movimiento</th>
															<th>Tipo de Cambio</th>
															<th>Provincia</th>
															<th>CUIT</th>
															<th>Estado Origen</th>
															<th>Motivo</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/Compensacion en Proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th>Resultado Validación</th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th></th>
															<th class="date"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 30px 0px 5px 0px;">
											<strong>Totales documentos</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span3">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p2">Total
														Débitos</label> <input id="prevSumDebitos_p2" value="$0,00"
														name="prevSumDebitos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p2">Total
														MP</label> <input id="prevSumCreditos_p2" value="$0,00"
														name="prevSumCreditos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p2">Diferencia</label>
												<input id="prevTotal_p2" value="$0,00" name="prevTotal"
													type="text" class="input sigmaInputText" readonly />
											</div>
										</div>
									</div>
									<!-- FIN CREDITOS -->
									<!-- MEDIOS DE PAGO -->
									<h3>Medios de Pago</h3>
									<div>
										<p style="padding: 0px 0px 5px 0px;">
											<strong>Listado de clientes seleccionados</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="clientesMedios" class="tablaResultado">
													<thead>
														<tr>
															<th><input type="checkbox" id="seleccionarTodos" /></th>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th class="numeroOrder"><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorClientesMP" class="error"></span>
											</div>
										</div>
										<div id="inputMedioDePago">
											<p style="padding: 25px 0px 5px 0px;">
												<strong>Agregar Medio de Pago</strong>
											</p>
											<div class="row">
												<div class="span3">
													<label class="control-label" for="empresa"><span
														class="rojo">* </span>Medio de Pago</label>
													<div class="controls">
														<select id="selectMedioPago" name="medioPago"
															class="input">
															<option value="" selected><spring:message
																	code="combo.seleccionar" /></option>
															<c:forEach items="${mediosPagoAgregar}" var="mpa">
																<option value="${mpa.valor}">${mpa.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3" id="impMPagoDiv">
													<label class="control-label" for="textImpMPago"><span
														class="rojo">* </span>Importe</label>
													<div class="controls" class="span1">
														<span id="monedaOperacionMpago" style="float: left">U$S</span>
														<input id="textImpMPago" name="textImpMPago" type="text"
															maxlength="14" style="margin: 0 auto; width: 170px" />
													</div>
												</div>
												<div class="span3" id="provinciaDiv">
													<label class="control-label" for="selectProvincia"><span
														class="rojo">* </span>
													<spring:message code="valor.provincias" /></label>
													<div class="controls">
														<select id="selectProvincia" name="selectProvincia"
															class="input">
															<option value="" selected><spring:message
																	code="combo.seleccionar" /></option>
															<c:forEach items="${provincia}" var="prov">
																<option value="${prov.idProvincia}">${prov.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3" id="selecSubTipoDiv">
													<label class="control-label" for="selecSubTipo"><span
														class="rojo">* </span>SubTipo</label>
													<div class="controls">
														<select id="selecSubTipo" name="selecSubTipo"
															class="input">
															<option value="" selected><spring:message
																	code="combo.seleccionar" /></option>
															<c:forEach items="${subTipoCompensaciones}" var="subTipo">
																<option value="${subTipo.codigo}">${subTipo.descripcion}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3" id="errorMedioPagoDiv">
													<span id="errorMedioPago" class="error"></span>
												</div>
												<div class="span3" id="errorImpMPagoDiv">
													<span id="errorImporte" class="error"></span>
												</div>
												<div class="span3" id="errorProvinciaDiv">
													<span id="errorProvincia" class="error"></span>
												</div>
												<div class="span3" id="errorSelecSubTipoDiv">
													<span id="errorSelecSubTipo" class="error"></span>
												</div>
											</div>
											<div class="row">
												<div class="span3" id="nroActaDiv">
													<label class="control-label" for="textNroActa"><span
														class="rojo">* </span>Nro. de Acta</label>
													<div class="controls">
														<input id="textNroActa" name="textNroActa" type="text"
															maxlength="25" />
													</div>
												</div>
												<div class="span3" id="fechaMedioPagoDiv">
													<label class="control-label" for="medioPagoFecha"
														style="margin: initial"><span class="rojo">*
													</span>Fecha</label>
													<div class="controls">
														<input class="span3" type="text" id="medioPagoFecha"
															data-date-format="dd/mm/yyyy" data-date-language='es'
															name="medioPagoFecha">
													</div>
												</div>
												<div
													style="float: right; margin-top: 17px; margin-right: 18px">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnAgregarMedio">
															<i class="icon-white icon-chevron-down"></i> Agregar
														</button>
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3" id="nroActaErrorDiv">
													<span id="errorNroActa" class="error"></span>
												</div>
												<div class="span3" id="fechaMedioPagoErrorDiv">
													<span id="errorFecha" class="error"></span>
												</div>
											</div>
										</div>
										<!-- Buscar CAP -->
										<div id="bloqueBuscarCap">
											<div id="searchCriteraCap">
												<p style="padding: 40px 0 15px 0;">
													<strong><spring:message
															code="cobro.busquedaCap.criteriosBusqueda" /></strong>
												</p>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="selectTipoDocCap"><spring:message
																code="cobro.busquedaCap.filtro.tipoComprobante" /></label>
														<div class="controls">
															<select id="selectTipoDocCap" name="selectTipoDocCap"
																class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${tiposDeDocumentosACompensar}"
																	var="tipoDoc">
																	<option value="${tipoDoc.codigo}">${tipoDoc.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="selectMonedaCab"><spring:message
																code="cobro.busquedaCap.filtro.moneda" /></label>
														<div class="controls">
															<select id="selectMonedaCab" name="selectMonedaCab"
																class="input">
																<option value="" selected><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${monedasBusquedaCap}" var="mon">
																	<option value="${mon.sigla}">${mon.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="idDocCap"><spring:message
																code="cobro.busquedaCap.filtro.idDocumento" /></label>
														<div class="controls">
															<input id="idDocCap" name="idDocCap" type="text"
																class="input" style="margin: 0 auto;" maxlength="10" />
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorTipoDocCap" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorMonedaCap" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorIdDocCap" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="nroDocCap"><spring:message
																code="cobro.busquedaCap.filtro.NroDocumento" /></label>
														<div class="controls">
															<input id="nroDocCap" name="nroDocCap" type="text"
																class="input" style="margin: 0 auto;" maxlength="15" />
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="emisionDesdeCap"><spring:message
																code="cobro.busquedaCap.filtro.fechaEmisionDesde" /></label>
														<div class="controls">
															<input class="span3" type="text" id="emisionDesdeCap"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="emisionDesdeCap" maxlength="10">
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="emisionHastaCap"><spring:message
																code="cobro.busquedaCap.filtro.fechaEmisionHasta" /></label>
														<div class="controls">
															<input class="span3" type="text" id="emisionHastaCap"
																data-date-format="dd/mm/yyyy" data-date-language='es'
																name="emisionHastaCap" maxlength="10">
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorNroDocCap" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorFechaDesdeCap" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorFechaHastaCap" class="error"></span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="span9" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnBuscarCap">
															<i class="icon-white icon-search"></i>
															<spring:message code="accion.find" />
														</button>
													</div>
												</div>
											</div>
											<p style="padding: 5px 0px;">
												<strong><spring:message code="cobro.busquedaCap.listadoCap" /></strong>
											</p>
											<div class="row">
												<div class="span9">
													<table id="caps" class="tablaResultado">
														<thead>
															<tr>
																<th class="nosort">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.sociedad" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.cliente" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.nroProveedor" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.tipoDeDocumento" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.nroDocumentoInterno" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.nroDocumentoLegal" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.fechaEmision" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.renglon" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.noneda" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.importeMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.tipoDeCambio" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.importeDelRenglon" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.saldoMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.saldoPesificado" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.importePesificadoDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.importeMonedaOrigenDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.busquedaCap.listadoColumna.tipoDeCambioDocumentoAsociado" /></th>
															</tr>
														</thead>
													</table>
													<!-- El orden por default de los registros de la grilla será por fecha de emisión ascendente y id de factura. -->
													<div class="customPag_info" id="customPagCap_info">
														<span><spring:message code="cobro.busqueda.leyenda" /></span>
													</div>
													<div class="customPag_simple" id="customPagCap_paginate">
														<a
															class="customPaginate_button customPaginatePrevious disabled"
															id="customPagCap_previous"> <spring:message
																code="cobro.busqueda.leyenda.anterior" />
														</a> <a
															class="customPaginate_button customPaginateNext disabled"
															id="customPagCap_next"> <spring:message
																code="cobro.busqueda.leyenda.siguiente" />
														</a>
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorRespuestaCap" class="error"></span>
												</div>
											</div>
											<p style="padding: 25px 0px 5px 0px;">
												<strong><spring:message code="cobro.busquedaCap.select.seleccionado" /></strong>
											</p>
											<div class="row">
												<div class="span9">
													<table id="capSeleccionados" class="tablaResultado">
														<thead>
															<tr>
																<th class="nosort">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.sociedad" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.cliente" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroProveedor" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeDocumento" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoInterno" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoLegal" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.fechaEmision" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.renglon" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.noneda" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambio" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeDelRenglon" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoPesificado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importePesificadoDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigenDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambioDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.checkSinDiferenciaDeCambio" /></th>
															</tr>
														</thead>
													</table>
													<div class="customPag_info" id="customPagCapSele_info">
														<span><spring:message code="cobro.busqueda.leyenda" /></span>
													</div>
												</div>
											</div>
											<br /> <br />
											<div class="row rowError">
												<div class="span9">
													<span id="errorRespuestaCapSeleccionado" class="error"></span>
												</div>
											</div>

											<%-- 	
											<p style="padding: 30px 0px 5px 0px;"><strong><spring:message code="cobro.busqueda.leyenda.totales"/></strong></p>
										--%>
											<div class="row">
												<div class="span3"></div>
												<div class="span3" style="margin-left: -31px"></div>
												<div class="span3" style="float: right; margin-right: -56px"
													id="compensacionTotalDiv">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png" />
													<div class="sigmaControlGroup">
														<label class="control-label" for="compensacionTotal">Total</label>
														<input id="compensacionTotal" value="$0,00"
															name="compensacionTotal" type="text"
															class="input sigmaInputText" readonly />
													</div>
												</div>
											</div>

											<div class="row">
												<div class="span3"></div>
												<div class="span3" style="margin-left: -31px"></div>
												<div style="float: right; margin-right: 18px">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnAgregarMedioCompensacion">
															<i class="icon-white icon-chevron-down"></i> Agregar
														</button>
													</div>
												</div>
											</div>
										</div>

										<p style="padding: 25px 0px 5px 0px;">
											<strong>Lista de Otros Medios de Pago Seleccionados</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span9">
												<table id="mediosPagos" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th></th>
															<th></th>
															<th>Tipo</th>
															<th>Sub tipo</th>
															<th class="importe">Importe</th>
															<th>Nro. Acta</th>
															<th>Nro. Compensación</th>
															<th class="date">Fecha</th>
															<th>Cliente</th>
															<th>Provincia</th>
															<th>Nro de Documento Interno</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 30px 0px 5px 0px;">
											<strong>Totales documentos</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span3">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p3">Total
														Débitos</label> <input id="prevSumDebitos_p3" value="$0,00"
														name="prevSumDebitos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p3">Total
														MP</label> <input id="prevSumCreditos_p3" value="$0,00"
														name="prevSumCreditos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p3">Diferencia</label>
												<input id="prevTotal_p3" value="$0,00" name="prevTotal"
													type="text" class="input sigmaInputText" readonly />
											</div>
										</div>
									</div>
									<!-- FIN MEDIOS DE PAGO -->
									<!-- COMPROBANTES -->
									<h3>Comprobantes</h3>
									<div>
										<div id="bloqueAgregarComprobante">
											<div class="row" style="margin-top: 20px;">
												<div class="span9">
													<form id="adjComprobanteForm" method="post"
														action="configuracion-cobro/adjuntarComprobante"
														enctype="multipart/form-data">
														<label class="control-label" for="comprobanteArch"><span
															id="idSpan2" class="rojo">* </span>Comprobante</label>
														<div class="fileupload fileupload-new"
															data-provides="fileupload">
															<input type="hidden">
															<div class="input-append">
																<div class="uneditable-input span3"
																	style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																	<span class="fileupload-preview"
																		style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																</div>
																<span class="btn btn-file btn-primary btn-small"
																	style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																	<span class="fileupload-new">Seleccionar archivo</span>
																	<span class="fileupload-exists">Cambiar</span><input
																	name="comprobanteArch" id="comprobanteArch" type="file" />
																</span> <a href="#"
																	class="btn fileupload-exists btn-primary btn-small"
																	style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																	data-dismiss="fileupload">Eliminar</a>
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
													<textarea class="field span9" id="descripcionComprobante"
														maxlength="150" name="descripcionComprobante" /></textarea>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorDescripcionComp" class="error" hidden="true"></span>
												</div>
											</div>
											<div class="row" style="margin-top: 0px; margin-bottom: 5px">
												<div class="span9">
													<button type="button" class="btn btn-primary btn-small"
														id="btnAdjComprobante">Adjuntar</button>
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
									<!-- PREVISUALIZACION -->
									<h3>Previsualización</h3>
									<div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="prevEmpresa">Empresa</label>
												<div class="controls">
													<input id="prevEmpresa" name="prevEmpresa" type="text"
														class="input" readonly value="Empresa" />
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevSegmento">Segmento</label>
												<div class="controls">
													<input id="prevSegmento" name="prevSegmento" type="text"
														class="input" readonly value="Segmento" />
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevMonedaOperacion">Moneda</label>
												<div class="controls">
													<input id="prevMonedaOperacion" name="prevMonedaOperacion"
														type="text" class="input" readonly value="MonedaOperacion" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="prevAnalista">Analista</label>
												<div class="controls">
													<input id="prevAnalista" name="prevAnalista" type="text"
														class="input" readonly
														value="<c:out value = "${nombreCompletoUsuario}"/>" />
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevCopropietario">Copropietario</label>
												<div class="controls">
													<input id="prevCopropietario" name="prevCopropietario"
														type="text" class="input" readonly value="Copropietario" />
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="prevMotivo">Motivo</label>
												<div class="controls">
													<input id="prevMotivo" name="prevMotivo" type="text"
														class="input" readonly value="Motivo" />
												</div>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message
													code="conf.cobro.clientes.seleccionados" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="prevClientes" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cuit" /></th>
															<th class="numeroOrder"><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de Débitos Seleccionados</strong>
										</p>
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
															<th class="date">F. Vencimiento</th>
															<th class="importe">Saldo 1er Vto. Moneda Origen</th>
															<th>Moneda</th>
															<th class="importe">Importe al 1er Vto.</th>
															<th class="importe">Importe al 2do Vto.</th>
															<th class="importe">Saldo pesificado F. Emisión</th>
															<th class="importe">Cobrar al 2do Venc.</th>
															<!-- Editable checkbox -->
															<th>Destransferir 3ros</th>
															<!-- Editable checkbox -->
															<th class="importe">Importe a cobrar</th>
															<!-- Editable text -->
															<th>Estado Cptos. de 3ros</th>
															<th class="importe">Importe 3ros Transferidos</th>
															<th>Estado origen</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estado en Deimos</th>
															<th>Pago/compensación en proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th>Tipo de Cambio</th>
															<th class="date">F. Emisión</th>
															<th>Sin Dif. de Cambio</th>
															<!-- Editable checkbox -->
															<th>Nro. Convenio</th>
															<th>Cuota Convenio</th>
															<th class="date">F. ult. pago parcial</th>
															<th>Resultado validación</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de Otros Débitos Seleccionados</strong>
										</p>
										<div class="row">
												<div class="span9">
													<table id="prevOtrosDebitos" class="tablaResultado">
														<thead>
															<tr>
																<th><spring:message code="cobro.conf.otros.debitos.sociedad"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.sistema"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.tipo.debito"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.referencia.pago"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.cliente"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.numero.legal"/></th>
																<th class="date"><spring:message code="cobro.conf.otros.debitos.fecha.vencimiento"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.moneda"/></th>
																<th class="importe"><spring:message code="cobro.conf.otros.debitos.tipo.cambio"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.importe"/></th>
																<th class="importe"><spring:message code="cobro.conf.otros.debitos.importe.moneda.origen"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.sin.diferencia.cambio"/></th>
																<th><spring:message code="cobro.conf.otros.debitos.nombre.adjunto"/></th>
																<th></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de Créditos Seleccionados</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="prevCreditos" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th>Cliente</th>
															<th>Cuenta</th>
															<th>Sistema</th>
															<th>Tipo de Crédito</th>
															<th>Subtipo Documento</th>
															<th>Nro. Documento</th>
															<th>Nro. Referencia MIC</th>
															<th class="date">Fecha Valor</th>
															<th class="importe">Saldo Moneda Origen</th>
															<th>Moneda</th>
															<th class="importe">Imp. Moneda Origen</th>
															<th class="importe">Imp. pesificado</th>
															<th class="importe">Saldo pesificado</th>
															<th class="importe">Importe a utilizar</th>
															<th class="date">Fecha alta</th>
															<th class="date">Fecha ingreso recibo</th>
															<th class="date">Fecha depósito</th>
															<th class="date">Fecha transferencia</th>
															<th class="date">Fecha emisión</th>
															<th class="date">Fecha vencimiento</th>
															<th class="date">Fecha último movimiento</th>
															<th>Tipo de Cambio</th>
															<th>Provincia</th>
															<th>CUIT</th>
															<th>Estado Origen</th>
															<th>Motivo</th>
															<th>Reclamo en origen</th>
															<th>Migrado en origen</th>
															<th>Estamo en Deimos</th>
															<th>Pago/Compensacion en Proceso</th>
															<th>Reversión en proceso</th>
															<th>Operación/Analista</th>
															<th>Resultado Validación</th>
															<th class="date"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de Otros Medios de Pago</strong>
										</p>
										<div class="row" style="margin-top: 20px">
											<div class="span9">
												<table id="prevMediosPagos" class="tablaResultado"
													width="100%">
													<thead>
														<tr>
															<th></th>
															<th>Tipo</th>
															<th>Sub tipo</th>
															<th class="importe">Importe</th>
															<th>Nro. Acta</th>
															<th>Nro. Compensación</th>
															<th class="date">Fecha</th>
															<th>Cliente</th>
															<th>Provincia</th>
															<th>Nro de Documento Interno</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<!-- Grilla Documentos Caps Seleccionados -->
										<div class="prevDocumentosCapWrapper" style="display: none">
											<p style="padding: 25px 0px 5px 0px;">
												<th><strong><spring:message code="cobro.seleccionCap.listadoCap.seleccionado" /></strong></th>
											</p>
											<div class="row">
												<div class="span9">
													<table id="prevDocumentosCap" class="tablaResultado">
														<thead>
															<tr>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.sociedad" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.cliente" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroProveedor" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeDocumento" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoInterno" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.nroDocumentoLegal" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.fechaEmision" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.renglon" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.noneda" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambio" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeDelRenglon" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoMonedaOrigen" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.saldoPesificado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importePesificadoDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeMonedaOrigenDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.tipoDeCambioDocumentoAsociado" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.checkSinDiferenciaDeCambio" /></th>
																<th class="nosort"><spring:message code="cobro.seleccionCap.listadoColumna.importeDiferenciaCambio" /></th>
															</tr>
														</thead>
													</table>
													<div class="customPag_info" id="customPagCapPrev_info">
														<span><spring:message code="cobro.busqueda.leyenda" /></span>
													</div>
												</div>
											</div>
										</div>
										<!-- Grilla Documentos Caps Seleccionados -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong>Listado de comprobantes</strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="prevComprobantes" class="tablaResultado"
													width="100%">
													<thead>
														<tr>
															<th></th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										
										<p style="padding: 30px 0px 5px 0px;">
											<strong>Totales Documentos</strong>
										</p>
										<div class="row">
											<div class="span3">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumDebitos_p4">Total
														Débitos</label> <input id="prevSumDebitos_p4" value="$0,00"
														name="prevSumDebitos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -31px">
												<img class="sigma"
													src="${pageContext.request.contextPath}/img/sigma.png" />
												<div class="sigmaControlGroup">
													<label class="control-label" for="prevSumCreditos_p4">
														Total MP</label> <input id="prevSumCreditos_p4" value="$0,00"
														name="prevSumCreditos" type="text"
														class="input sigmaInputText" readonly />
												</div>
											</div>
											<div class="span3" style="margin-left: -17px">
												<label class="control-label" for="prevTotal_p4">Diferencia</label>
												<input id="prevTotal_p4" value="$0,00" name="prevTotal"
													type="text" class="input sigmaInputText" readonly />
											</div>
										</div>
										<div id="inputTratamientoDeDiferencia">
											<div class="row" style="padding-top: 8px;">
												<div class="span3">
													<label class="control-label" for="envio">Tratamiento
														de diferencia</label>
													<div class="controls">
														<div class="comboDebito">
															<select id="selectEnvio1" name="envio" class="input">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${reintegrosDebito}" var="dif">
																	<option value="${dif.name}">${dif.descripcion}</option>
																</c:forEach>
															</select>
														</div>
														<div class="comboCredito">
															<select id="selectEnvio" name="envio" class="input">
																<option value=""><spring:message
																		code="combo.seleccionar" /></option>
																<c:forEach items="${reintegrosCredito}" var="dif">
																	<option value="${dif.name}">${dif.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="reintegroOpc">
														<div id="tramReint">
															<label class="control-label" for="tramReintInput">Trámite
																de reintegro</label> <input id="tramReintInput" maxlength="10"
																id="tramReintInput" type="text" class="input" />
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="reintegroOpc">
														<div id="fechaAltaTramReintOpc">
															<label class="control-label" for="fechaAltaTramReint">Fecha
																alta trámite de reintegro:</label> <input
																id="fechaAltaTramReint" name="fechaAltaTramReint"
																class="span3" type="text" data-date-format="dd/mm/yyyy"
																data-date-language='es'>
														</div>
													</div>
												</div>
												<div class="span3"></div>
												<div class="span3"></div>
											</div>
											<div class="row rowError">
												<div class="envio">
													<div class="span3">
														<span id="errorEnvio" class="error" style="display: none;"></span>
													</div>
												</div>
												<div class="reintegroOpc">
													<div class="span3">
														<span id="errorTramReintInput" class="error"
															style="display: none;"></span>
													</div>
												</div>
												<div class="reintegroOpc">
													<div class="span3">
														<span id="errorFechaAltaTramReint" class="error"
															style="display: none;"></span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="reintegroProxFactOpc">
													<div class="span3">
														<label class="control-label" for="sistDestino"><span id="spanSistemaDestino" class="rojo"></span>Sistema
															destino</label>
														<div class="controls">
															<div class="sistemaDestino">
																<select id="selectSistDestino" name="sistDestino"
																	class="input">
																	<option value=""><spring:message
																			code="combo.seleccionar" /></option>
																	<c:forEach items="${sistemas}" var="sist">
																		<option value="${sist.descripcionCorta}">${sist.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="sistemaApliManual">
																<select id="sistemaAplicacionManual" name="sistemaAplicacionManual" class="input">
																	<option value=""><spring:message code="combo.seleccionar" /></option>
																	<c:forEach items="${sistemaAplicacionManual}"
																		var="sist">
																		<option value="${sist.descripcionCorta}">${sist.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</div>
												<div class="referenciaAplicacionManual">
													<div class="span6">
														<label class="control-label" for="referenciaInput">Referencia</label>
														<input id="referenciaInput" id="referenciaInput" maxlength="50"
															type="text" class="input span6" />
													</div>
												</div>
												<div class="reintegroProxFactOpcLinea">
													<div class="span3">
														<label class="control-label" for="lineaInput">Línea</label>
														<input id="lineaInput" id="lineaInput" maxlength="10"
															type="text" class="input" />
													</div>
												</div>
												<div class="reintegroProxFactOpcAcuerdo">
													<div class="span3">
														<label class="control-label" for="acuerdoFactInput">Acuerdo
															de facturación</label> <input id="acuerdoFactInput"
															name="acuerdoFactInput" maxlength="10" type="text"
															class="input" /> <input type="hidden"
															id="estadoAcuerdoFacturacion"
															name="estadoAcuerdoFacturacion" /> <input type="hidden"
															id="clienteAcuerdoFacturacion"
															name="clienteAcuerdoFacturacion" />
													</div>
												</div>
											</div>
											<div class="row rowError">
												<div class="span3">
													<span id="errorSistemaDestino" class="error"
														style="display: none;"></span>
												</div>
												<div class="reintegroProxFactOpcLinea">
													<div class="span3">
														<span id="wsConsultaJms" class="error"
															style="display: none;"></span>
													</div>
												</div>
												<div class="reintegroProxFactOpcAcuerdo">
													<div class="span3">
														<span id="wsConsultaJms2" class="error"
															style="display: none;"></span>
													</div>
												</div>
												<div class="span3">
													<span id="errorReferenciaInput" class="error"
														style="display: none;"></span>
												</div>
											</div>
											
											
											
											<div id="bloqueAgregarComprobanteAplManual" style="display:none">
												<p style="padding: 30px 0px 5px 0px;">
													<strong>Detalle de la Aplicación Manual</strong>
												</p>
												<div class="row" style="margin-top: 20px;">
													<div class="span9">
														<form id="adjComprobanteFormAplManual" method="post"
															action="configuracion-cobro/adjuntarComprobante"
															enctype="multipart/form-data">
															<label class="control-label" for="comprobanteArchAplManual"><span class="rojo">*</span>Comprobante</label>
															<div class="fileupload fileupload-new"
																data-provides="fileupload">
																<input type="hidden">
																<div class="input-append">
																	<div class="uneditable-input span3"
																		style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																		<span class="fileupload-preview"
																			style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																	</div>
																	<span class="btn btn-file btn-primary btn-small"
																		style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																		<span class="fileupload-new">Seleccionar archivo</span>
																		<span class="fileupload-exists">Cambiar</span><input
																		name="comprobanteArch" id="comprobanteArchAplManual" type="file" />
																	</span> <a href="#"
																		class="btn fileupload-exists btn-primary btn-small"
																		style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																		data-dismiss="fileupload">Eliminar</a>
																</div>
															</div>
														</form>
													</div>
												</div>
												<div class="row" style="margin-top: 0px; margin-bottom: 5px">
													<div class="span9">
														<button type="button" class="btn btn-primary btn-small"
															id="btnAdjComprobanteAplManual">Adjuntar</button>
													</div>
												</div>
												<br>
												<div class="row rowError">
													<div class="span9">
														<span id="errorArchComprobanteAplManual" class="error"
															hidden="true"></span>
													</div>
												</div>
												
												<div class="row" >
													<div class="span9">
															<table id="comprobantesAplManual" class="tablaResultado" width="100%">
																<thead>
																	<tr>
																		<th></th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																		<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																	</tr>
																</thead>
															</table>
													</div>
												</div>
											</div>

											<p style="padding: 25px 0px 5px 0px;">
												<strong><spring:message code="conf.cobro.listado.codigo.operacion.externa"/></strong>
											</p>
											<div class="row" style="margin-top: 20px;">
												<div class="span9">
													<table id="codigoOperacionesExternas"
														class="tablaResultado">
														<thead>
															<tr>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.grupo"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.transaccion"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.sistema"/></th>
																<th><spring:message code="conf.cobro.codigo.operacion.externa" /></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.ref.impu"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.importe"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.responsable.imputacion"/></th>
																<th><spring:message code="cobro.conf.prev.cod.op.ext.fecha.imputacion"/></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>

											<br>
											<br>
											<!-- Boton Simular -->
											<div class="row">
												<div class="span9" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnSimular">
															<i class="icon-white icon-cog"></i> Simular
														</button>
													</div>
												</div>
											</div>
											<!-- Boton Simular -->
											<div class="row rowError" style="margin-top: 10px">
												<div class="span9" style="margin-top: 10px">
													<span id="mensajeErrorSimular" class="error"
														style="display: none;"></span>
												</div>
											</div>
											
											<div class="row rowError" style="margin-top: 17px">
												<div class="span9" align="right">
													<span id="errorComprobanteAplicacionManualSimular" class="error"></span>
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
																<th class="nosort">Tipo&nbsp;Cambio
																	Fecha&nbsp;Emisión</th>
																<th class="nosort">Importe&nbsp;Cobrado&nbsp;en
																	Moneda&nbsp;Origen</th>
																<th class="nosort">Sistema MP</th>
																<th class="nosort">Tipo&nbsp;Medio de&nbsp;Pago</th>
																<th class="nosort">Subtipo&nbsp;Medio de&nbsp;Pago</th>
																<th class="nosort">Referencia
																	Medio&nbsp;de&nbsp;Pago</th>
																<th class="nosort">Fecha&nbsp;Medio de&nbsp;Pago</th>
																<th class="nosort">Moneda&nbsp;Medio de&nbsp;Pago</th>
																<th class="nosort">Importe&nbsp;del
																	Medio&nbsp;de&nbsp;Pago</th>
																<th class="nosort">Tipo&nbsp;Cambio al&nbsp;Cobro
																	Medio&nbsp;de&nbsp;Pago</th>
																<th class="nosort">Importe&nbsp;Cobrado&nbsp;en
																	Moneda&nbsp;Origen Medio&nbsp;de&nbsp;Pago</th>
																<th class="nosort">Intereses</th>
																<!-- Editable checkbox -->
																<th class="nosort">Trasladar Intereses<input
																	type="checkbox" id="trasladarTodosIntereses" /></th>
																<!-- Editable text -->
																<th class="nosort">%&nbsp;a&nbsp;Bonificar</th>
																<!-- Editable text -->
																<th class="nosort">Importe a&nbsp;Bonificar</th>
																<!-- Editable text -->
																<th class="nosort">Acuerdo&nbsp;Facturacion
																	Destino&nbsp;Cargos</th>
																<th class="nosort">Estado&nbsp;Acuerdo Facturacion</th>
																<th class="nosort">Mensaje Transaccion</th>
																<th class="nosort">Mensaje Débito</th>
																<th class="nosort">Mensaje Crédito</th>
																<th></th>
																<!-- Campo oculto colorLetraRegistro -->
																<th></th>
																<!-- Campo oculto numeroTransaccionOriginal -->
																<th></th>
																<!-- Campo oculto numeroGrupo -->
																<th></th>
																<!-- Campo oculto numeroTransaccionFormateado -->
															</tr>
														</thead>
													</table>
												</div>
											</div>
											<!-- Grilla Transacciones -->
											<!-- Observaciones Documentos CAP -->
											<div class="prevDocumentosCapWrapper" style="display: none;">
												<div class="row" style="margin-top: 20px; display: none;"
													id="prevObservacionesCapAnterior2">
													<div class="span9">
														<label class="control-label"
															for="prevObservCapTextAnterior2">Observaciones
															Anteriores</label>
														<textarea class="field span9"
															id="prevObservCapTextAnterior2" readonly
															name="prevObservCapTextAnterior2"></textarea>
													</div>
												</div>
												<div class="row" style="margin-top: 20px"
													id="prevObservacionesCap">
													<div class="span9">
														<label class="control-label" for="prevObservCapText2">Observaciones
															de Documentos Cap</label>
														<textarea class="field span9" id="prevObservCapText2"
															readonly name="prevObservCapText2"></textarea>
													</div>
												</div>
											</div>
											<!-- Fin Observaciones Documentos CAP -->
											<!-- Totales Intereses -->
											<p style="padding: 30px 0px 5px 0px;">
												<strong>Totales Intereses</strong>
											</p>
											<div class="row">
												<div class="span3" style="margin-left: 70px;">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesTraslados">Intereses
															Trasladados</label> <input id="prevSumInteresesTraslados"
															value="$0,00" name="prevSumInteresesTraslados"
															type="text" class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
												<div class="span3" style="margin-left: -10px">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesBonificados">Intereses
															Bonificados</label> <input id="prevSumInteresesBonificados"
															value="$0,00" name="prevSumInteresesBonificados"
															type="text" class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
												<div class="span3" style="margin-left: -10px">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesReintegro">Intereses por
															Reintegro</label> <input id="prevSumInteresesReintegro"
															value="$0,00" name="prevSumInteresesReintegro"
															type="text" class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="span3" style="margin-left: 70px;">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesTraslados">Intereses
															Trasladados</label> <input id="prevSumInteresesTrasladosDolares"
															value="U$S0,00" name="prevSumInteresesTraslados"
															type="text" class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
												<div class="span3" style="margin-left: -10px">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesBonificados">Intereses
															Bonificados</label> <input
															id="prevSumInteresesBonificadosDolares" value="U$S0,00"
															name="prevSumInteresesBonificados" type="text"
															class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
												<div class="span3" style="margin-left: -10px">
													<img class="sigma"
														src="${pageContext.request.contextPath}/img/sigma.png"
														style="margin-left: -36px;">
													<div class="sigmaControlGroup" style="width: 150px;">
														<label class="control-label"
															for="prevSumInteresesReintegroDolares">Intereses
															por Reintegro</label> <input
															id="prevSumInteresesReintegroDolares" value="U$S0,00"
															name="prevSumInteresesReintegroDolares" type="text"
															class="input sigmaInputText" readonly
															style="width: 130px;">
													</div>
												</div>
											</div>
											<!-- Totales Intereses -->
											<!-- Boton Simular -->
											<div class="row" style="margin-top: 17px">
												<div class="span9" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small"
															id="btnImputar"
															onclick="javascript:validarComprobanteObligatorio();">
															<i class="icon-white icon-play"></i> Imputar
														</button>
													</div>
												</div>
											</div>
											<div class="row rowError" style="margin-top: 17px">
												<div class="span9" align="right">
													<span id="errorTransacciones" class="error"></span>
												</div>
											</div>
											
											<div class="row rowError" style="margin-top: 17px">
												<div class="span9" align="right">
													<span id="errorComprobanteAplicacionManualImputar" class="error"></span>
												</div>
											</div>
										
										<!-- Boton Simular -->


											<div class="row" style="margin-top: 20px; display: none;"
												id="prevObservacionesAnterior2">
												<div class="span9">
													<label class="control-label" for="prevObservTextAnterior2">Observaciones
														Anteriores</label>
													<textarea class="field span9" id="prevObservTextAnterior2"
														readonly name="prevObservTextAnterior2"></textarea>
												</div>
											</div>
											<div class="row" style="margin-top: 20px"
												id="prevObservaciones">
												<div class="span9">
													<label class="control-label" for="prevObservText2">Observaciones</label>
													<textarea class="field span9" id="prevObservText2" readonly
														name="prevObservText2"></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row" style="margin-top: 17px">
									<div class="span9" align="right">
										<div>
											<button type="button" class="btn btn-primary btn-small"
												id="btnHistorial" onclick="javascript:historialCobro();">
												<i class="icon-white icon-list-alt bigger-120"></i> Ver
												Historial
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnExportar" onclick="javascript:exportarDetalle();">
												<i class="icon-white icon-download-alt"></i> Exportar Cobro
												a Excel
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnVerCartaPie">
												<i class="icon-white icon-download-alt"></i>
												<spring:message code="conf.cobro.tabla.btn.verCarta" />
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnVerResumenCompensacionPie">
												<i class="icon-white icon-download-alt"></i>
												<spring:message code="conf.cobro.tabla.btn.verResumen" />
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnGuardar">
												<i class="icon-white icon-ok"></i> Guardar
											</button>
											<button type="button" class="btn btn-primary btn-small"
												style="display: none;" id="btnVolver"
												onclick="javascript:volverBusqueda();">
												<i class="icon-white icon-repeat"></i> Volver
											</button>
										</div>
									</div>
								</div>
								<form:form id="formCobro" commandName="cobroDto">
									<input type="hidden" id="idCobro" name="idCobro"
										value="${idCobro}" />
										<input type="hidden" id="idTarea" name="idTarea"
										value="${idTarea}" />
									<input type="hidden" id="vengoReedicion" name="vengoReedicion"
										value="${vengoReedicion}" />
									<input type="hidden" id="idCobroPadre" name="idCobroPadre" />
									<input type="hidden" id="idOperacionFormateado"
										name="idOperacionFormateado" />
									<input type="hidden" id="imputarCobro" name="imputarCobro" />
									<input type="hidden" id="imputarConAprobacion"
										name="imputarConAprobacion" />
									<input type="hidden" id="simularCobro" name="simularCobro" />
									<input type="hidden" id="volver" name="volver"
										value="/cobros-configuracion-edicion" />
									<input type="hidden" id="goBack" name="goBack" />
									<input type="hidden" id="idAnalista" id="idAnalista"
										value="${idUsuario}" />
									<input type="hidden" id="exportarExcel" id="exportarExcel" />
									<input type="hidden" id="modificarTransacciones"
										name="modificarTransacciones" />
									<input type="hidden" id="estado" id="estado" />
									<input type="hidden" id="idVolver" name="idVolver"
										value="${idVolver}" />
									<input type="hidden" id="formOrigen" name="formOrigen" />
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
	flagBloqueDeEspera.inicializar(['buscarConfCobro', 'validarEdicionCobro', 'validarEdicionCobroSegunUsuario']);
	
	if (${cobroEditable}) {
		//if (!$.isEmpty($('#vengoReedicion').val())) {
		//	$('.btn').attr('disabled', true);
		//}
		buscarCobro(${idCobro});
	
		$('button[id="btnExportar"]').prop('disabled', false);
		$
		('button[id="btnHistorial"]').prop('disabled', false);
		// TODO PARA PAU
		flagBloqueDeEspera.comando = tareaHabilitarMotivo;
		flagBloqueDeEspera.comandoName = 'ejecutar';
		finBloqueDeEspera();
		// TODO PARA PAU
	}else{
	    if ($('#idCobro').val()==""){
		    $('button[id="btnExportar"]').prop('disabled', true);
		    $('button[id="btnHistorial"]').prop('disabled', true);
		}else{
		    $('button[id="btnExportar"]').prop('disabled', false);
		    $('button[id="btnHistorial"]').prop('disabled', false);
		}
 	    flagBloqueDeEspera.clear();
	    ocultarBloqueEspera();
	}
});
</script>
