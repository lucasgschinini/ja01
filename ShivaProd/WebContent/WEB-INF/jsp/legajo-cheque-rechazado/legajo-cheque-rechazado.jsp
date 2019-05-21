
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
<%@ page import="ar.com.telecom.shiva.base.constantes.Propiedades" %>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.MonedaEnum" %>
<%@page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.TipoSimularDisabled"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.TipoValorEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum"%>
<%@page import="ar.com.telecom.shiva.base.enumeradores.SiNoEnum"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
  
<title><spring:message code="legajo.cheques.rechazados.titulo.main"/></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
<!--

<script src="//cdn.jsdelivr.net/jquery.scrollto/2.1.2/jquery.scrollTo.min.js"></script>
-->
<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath}/css/legajo-cheque-rechazado.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>

<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>

<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheques-rechazados.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheques-rechazados-cheque.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-cobro-relacionado.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-reembolso.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-resumen.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-notificaciones.js"></script>

<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<script src="${pageContext.request.contextPath}/js/custom-paginacion-datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-datatables-shiva.js"></script>
<script>
	var acuerdoBancoCuenta = ${acuerdoBancoCuenta};
	var bancoDescripcion = ${bancoDescripcion};
	var SISTEMA_SHIVA_DESC = '<%=SistemaEnum.SHIVA.getDescripcion()%>';
	var SISTEMA_ICE_DESC = '<%=SistemaEnum.ICE.getDescripcion()%>';
	var SISTEMA_USUARIO_DESC = '<%=SistemaEnum.USUARIO.getDescripcion()%>';
	var SISTEMA_SHIVA_DESC_CORTA = '<%=SistemaEnum.SHIVA.getDescripcionCorta()%>';
	var SISTEMA_ICE_DESC_CORTA = '<%=SistemaEnum.ICE.getDescripcionCorta()%>';
	var SISTEMA_USUARIO_DESC_CORTA = '<%=SistemaEnum.USUARIO.getDescripcionCorta()%>';
	var CANTIDAD_POR_PAGINA_CHEQUE = <%=Constantes.CANTIDAD_POR_PAGINA_CHEQUE_RECHAZADO%>;
	var CHEQUE = '<%=TipoValorEnum.CHEQUE.getIdTipoValor()%>';
	var CHEQUE_DIFERIDO = '<%=TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()%>';
	var BOLETA_DEPOSITO_CHEQUE = '<%=TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()%>';
	var BOLETA_DEPOSITO_CHEQUE_DIFERIDO = '<%=TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()%>';
	var LGJ_ENVIADO_A_LEGALES = '<%=Estado.LGJ_ENVIADO_A_LEGALES.name()%>';
	var LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA = '<%=Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.name()%>';
	var LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA_DESC = '<%=Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.descripcion()%>';
	var LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO = '<%=Estado.LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO.name()%>';
	var LGJ_DESISTIDO = '<%=Estado.LGJ_DESISTIDO.name()%>';
	var LGJ_ABIERTO = '<%=Estado.LGJ_ABIERTO.name()%>';
	var LGJ_ABIERTO_DESC = '<%=Estado.LGJ_ABIERTO.descripcion()%>';
	var LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA = '<%=Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA.name()%>';
	var LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA_DESC = '<%=Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA.descripcion()%>';
	var LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA = '<%=Estado.LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA.name()%>';
	var LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA_DESC = '<%=Estado.LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA.descripcion()%>';
	var legajoChequeRechazado = '';
	var LGJ_CERRADO_CON_CHEQUE_ENTREGADO = '<%= Estado.LGJ_CERRADO_CON_CHEQUE_ENTREGADO.name() %>';
	var LGJ_CERRADO_CON_CHEQUE_ENTREGADO_DESC = '<%= Estado.LGJ_CERRADO_CON_CHEQUE_ENTREGADO.descripcion() %>';
	var LGJ_ABIERTO_CON_CHEQUE_ENTREGADO = '<%= Estado.LGJ_ABIERTO_CON_CHEQUE_ENTREGADO.name() %>';
	var LGJ_ABIERTO_CON_CHEQUE_ENTREGADO_DESC = '<%= Estado.LGJ_ABIERTO_CON_CHEQUE_ENTREGADO.descripcion() %>';
	var edicion = false;
	var MONTOS = null;
	var titleEdicion = '<%=Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.titulo.main.edicion")%>';
	<c:if test="${not empty edicion && edicion eq true}">
		legajoChequeRechazado = ${legajoChequeRechazadoDto};
		edicion = ${edicion};
		MONTOS = ${montos};
	</c:if>
	var SISTEMA_MAP = ${sistemMap};
	var SHOW_BUTTON = "${showButton}";
	var FORM_ORIGEN = "${formOrigen}";
	var EDICION_MAP = ${EdicionTipoMap};
	var userSession = "${sessionScope.loginUserName}";
	
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
								<c:when test="${not empty edicion && edicion eq true}">
									<div class="title"><spring:message code="legajo.cheques.rechazados.titulo.main.edicion"/></div>
								</c:when>
								<c:otherwise>
									<div class="title"><spring:message code="legajo.cheques.rechazados.titulo.main"/></div>
								</c:otherwise>
							</c:choose>
								<div class="pagos_anticipos">
									<div class="row">
										<div class="span3" align="left">
											<label class="control-label" for="prevAnalista"><spring:message code="legajo.cheques.rechazados.legajo.idLegajo"/></label>
											<div class="controls">
												<input id="idLegajo" name="idLegajo" type="text" class="input" readonly />
											</div>
											
										</div>
										<div class="span3" align="left">
											
										</div>
										<div class="span6" style="margin-top: 16px" align="right">
											<div>
												<button type="button" class="btn btn-primary btn-small" id="btnExportar" style="display: none;"><i class="icon-white icon-download-alt"></i> Exportar Legajo a Excel</button>
												<button type="button" class="btn btn-primary btn-small" id="btnGuardar" style="display: none;"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.guardar"/></button>
												<button type="button" class="btn btn-primary btn-small" id="btnConfirmar"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.confirmar"/></button>
<!-- 												<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnCerrar"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.cerrar"/></button> --!>
<!-- 												<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnDesistir"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.desistir"/></button> --!>
												<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnVolver"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="accion.goBack"/></button>
											</div>
										</div>
									</div>
									
									<div class="row" >
										<div class="span6" align="left">
											<label class="control-label" for="prevAnalista"><spring:message code="legajo.cheques.rechazados.legajo.estadoLegajo"/></label>
											<div class="controls">
												<input id="estadoLegajo" name="estadoLegajo" type="text" class="input span6" readonly />
											</div>
										</div>
								
									</div>
									<div class="row">
										<div class="span8">
											<div id="alertErrorGuardadoLegajo1" class="alert alert-error" align="center" style="display: none;width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
												<button id="btnCruzErrorGuardadoLegajo" class="close" >×</button><strong><spring:message code="error.error"/></strong><br/>
												<span id="errorGuardadoLegajo1"></span>
											</div>
										</div>
									</div>
									<div id="gestion-cheques-rechazados-tabs">
										<h3><spring:message code="legajo.cheques.rechazados.solapa.cheque"/></h3>
										<div id="cheque-tab">
											<div id="divDatosLegajo">
												<!-- BUSQUEDA CLIENTES -->
												<div class="row">
													<div class="span3">
														<label class="control-label" for="empresa"><span class="rojo">* </span> <spring:message code="legajo.cheques.rechazados.legajo.empresa" /></label>
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
														<label class="control-label" for="segmento"><span class="rojo">* </span> <spring:message code="legajo.cheques.rechazados.legajo.segmento" /></label>
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
														<label class="control-label" for="analista"><spring:message code="legajo.cheques.rechazados.legajo.analista" /></label>
														<div class="controls">
															<input id="analista" name="analista" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuario}"/>"
																style="margin: 0 auto;" />
														</div>
													</div>
	
													<div class="span3">
														<label class="control-label" for="copropietario"><spring:message code="legajo.cheques.rechazados.legajo.copropietario" /></label>
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
														<label class="control-label" for="ubicacion"><span class="rojo">* </span> <spring:message code="legajo.cheques.rechazados.legajo.ubicacion.cheque" /></label>
														<div class="controls">
															<select id="selectUbicacion" name="ubicacion" class="input">
																<option value="" selected><spring:message code="combo.seleccionar" /></option>
																<c:forEach items="${ubicacionCheque}" var="ubicacion">
																	<option value="${ubicacion.indice}">${ubicacion.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												
												<div class="row rowError">
													<div class="span3">
														<span id="errorAnalista" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorCopropietario" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorUbicacion" class="error"></span>
													</div>
												</div>
												
												<div class="row">
													<div class="span3">
														<label class="control-label" for="fechaRechazo"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.fecha.rechazo" /></label>
														<div class="controls">
															<input id="fechaRechazo" name="fechaRechazo" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' maxlength="10" />
														</div>
													</div>
	
													<div class="span3">
														<label class="control-label" for="motivoRechazo"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.motivo.rechazo" /></label>
														<div class="controls">
															<select id="motivoRechazo" name="motivoRechazo" class="input">
																<option value="" selected><spring:message code="combo.seleccionar" /></option>
																<c:forEach items="${motivoRechazo}" var="rechazo">
																	<option value="${rechazo.idMotivoLegajo}">${rechazo.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="fechaNotificacionRechazo"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.fecha.notificacion.rechazo" /></label>
														<div class="controls">
															<input id="fechaNotificacionRechazo" name="fechaNotificacionRechazo" type="text" class="input" maxlength="10" />
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorfechaRechazo" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorMotivoRechazo" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorfechaNotificacionRechazo" class="error"></span>
													</div>
												</div>
											</div>
											<div class="row" style="display: none;" id="divObservacionesAnterior">
												<div class="span9">
													<label class="control-label" for="prevObservTextAnterior"><spring:message code="legajo.cheques.rechazados.legajo.observaciones.anteriores"/></label>
													<textarea class="field span9" id="prevObservTextAnterior" readonly name="prevObservTextAnterior"></textarea>
												</div>
											</div>
											<div class="row" style="margin-top: 30px" id="prevObservaciones">
												<div class="span9">
													<label class="control-label" for="prevObservText"><spring:message code="legajo.cheques.rechazados.legajo.observaciones"/></label>
													<textarea class="field span9" id="prevObservText" name="prevObservText" maxlength="250"></textarea>
												</div>
											</div>
											<div class="row rowError" style="margin-bottom: 20px;">
												<div class="span6">
													<span id="errorObservaciones" class="error"></span>
												</div>
											</div>
											<c:choose>
												<c:when test="${not empty edicion && edicion eq true}">
													<div id='searchCriterioCheque' style="display: none;">
												</c:when>
												<c:otherwise>
													<div id='searchCriterioCheque'>
												</c:otherwise>
											</c:choose>
												<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.cheque.subtitulo.criterios.busqueda.cheques"/></strong></p>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="sistemaGestor"><spring:message code="legajo.cheques.rechazados.cheque.filtro.sistema.gestor" /></label>
														<div class="controls">
															<select id="sistemaGestor" name="sistemaGestor" class="input">
																<option value=""><spring:message code="combo.seleccionar"/></option>
																<c:forEach items="${sistemaGestor}" var="sistemaGestor">
																	<option value="${sistemaGestor.descripcionCorta}">${sistemaGestor.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="fechaAltaDesdeCheque"><spring:message code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.desde" /></label>
														<div class="controls">
															<input id="fechaAltaDesdeCheque" name="fechaAltaDesdeCheque" type="text" class="input" maxlength="10"/>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="fechaAltaHastaCheque"><spring:message code="legajo.cheques.rechazados.cheque.filtro.fecha.alta.hasta" /></label>
														<div class="controls">
															<input id="fechaAltaHastaCheque" name="fechaAltaHastaCheque" type="text" class="input" maxlength="10"/>
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorsistemaGestor" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorfechaAltaDesdeCheque" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorfechaAltaHastaCheque" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3" id='divFechaVencimientoCheque'>
														<label class="control-label" for="fechaVencimientoCheque"><spring:message code="legajo.cheques.rechazados.cheque.filtro.fecha.vencimiento" /></label>
														<div class="controls">
															<input id="fechaVencimientoCheque" name="fechaVencimientoCheque" type="text" class="input" maxlength="10"/>
														</div>
													</div>
	
													<div class="span3">
														<label class="control-label" for="importesDesde"><spring:message code="legajo.cheques.rechazados.cheque.filtro.importes.desde" /></label>
														<div class="controls">
															<input id="importesDesde" name="importesDesde" class="input"
															onkeypress="return validarInputNumericosComaPunto(event)" maxlength="14" type="text"/>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="importesHasta"><spring:message code="legajo.cheques.rechazados.cheque.filtro.importes.hasta" /></label>
														<div class="controls">
															<input id="importesHasta" name="importesHasta" class="input"
															onkeypress="return validarInputNumericosComaPunto(event)" maxlength="14" type="text"/>
														</div>
													</div>
												</div>
												
												<div class="row rowError">
													<div class="span3" id="divErrorfechaVencimientoCheque">
														<span id="errorfechaVencimientoCheque" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorimportesDesde" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorimportesHasta" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="codigoBanco"><spring:message code="legajo.cheques.rechazados.cheque.filtro.codigo.banco" /></label>
														<div class="controls">
															<select id="codigoBanco" name="codigoBanco" class="input">
															<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
															<c:forEach items="${bancoOrigenes}" var="ori">
																<option value="${ori.idBanco}" idAgrupador="${ori.idAgrupador}" >${ori.idBanco} - ${ori. descripcion}</option>
															</c:forEach> 
															</select>
														</div>
													</div>
	
													<div class="span3" id="divDescripcionBanco">
														<label class="control-label" for="descripcionBanco"><spring:message code="legajo.cheques.rechazados.cheque.filtro.descripcion.banco" /></label>
														<div class="controls">
															<select id="descripcionBanco" name="descripcionBanco" class="input">
																<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																<c:forEach items="${bancoOrigenesDescripcion}" var="ori">
																	<option value="${ori.idValue}">${ori.descripcion}</option>
																</c:forEach> 
															</select>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="nroCheque"><spring:message code="legajo.cheques.rechazados.cheque.filtro.nro.cheque" /></label>
														<div class="controls">
															<input id="nroCheque" name="nroCheque" type="text" class="input" maxlength="8"/>
														</div>
													</div>
												</div>
												<div class="row rowError">
													<div class="span3">
														<span id="errorCodigoBanco" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorDescripcionBanco" class="error"></span>
													</div>
													<div class="span3">
														<span id="errorNroCheque" class="error"></span>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="moneda"><spring:message code="legajo.cheques.rechazados.legajo.moneda"/></label>
														<div class="controls">
															<select id="selectMoneda" name="moneda" class="input">
<%-- 																<option value="" selected><spring:message code="combo.seleccionar" /></option> --%>
																<c:forEach items="${monedas}" var="mon">
																	<option value="${mon.signo}">${mon.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div id='searchCriteraCliente'>
													<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.filtro.clientes"/></strong></p>
													<div class="row">
														<div class="span3">
															<label class="control-label" for="selectCliente"><spring:message code="legajo.cheques.rechazados.cheque.filtro.cliente" /></label>
															<div class="controls">
																<select id="selectCliente" name="selectCliente" class="input">
																	<option value=""><spring:message code="combo.seleccionar"/></option>
																	<c:forEach items="${criterioBusquedaCliente}" var="criterioBusquedaCliente">
																		<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="span3">
															<div class="controls" style="margin-top: 16px">
																<input id="textCriterio" name="textCriterio" type="text" class="input" disabled="disabled" />
															</div>
														</div>
													</div>
													<div class="row rowError" style="margin-bottom: 40px;">
														<div class="span3">
															<span id="errorSelectCliente" class="error"></span>
														</div>
														<div class="span3">
															<span id="errorTextCriterio" class="error"></span>
														</div>
														<div class="span3">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="span9" align="right">
														<div>
														<button type="button" class="btn btn-primary btn-small" id="btnBuscarCheques">
																<i class="icon-white icon-search"></i>
																<spring:message code="accion.find" />
															</button>
														</div>
													</div>
												</div>
												
												<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.listado.cheques"/></strong></p>
																							
												<div class="row">
													<div class="span9">
														<table id="tablaBusquedaCheques" class="tablaResultado">
															<thead>
																<tr>
																	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																	<th></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.sistemaOrigen" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.numeroCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.descripcionBancoOrigenCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.codigoBancoOrigenCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.clienteLegado" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.tipoCheque" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaVencimientoCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.monedaCheque" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.importeCheque" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.bancoDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.cuentaDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.acuerdo" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaRecepcionCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.estado" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.empresa" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.segmento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaDueño" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.copropietario" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaTeamComercial" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.idInternoValor" /></th>
																</tr>
															</thead>
														</table>
														<div class="customPag_info" id="customPagCheques_info">
															<span>Mostrando registros del 0 al 0 de 0 registros en total</span>
														</div>												
														<div class="customPag_simple" id="customPagCheques_paginate">
															<a class="customPaginate_button customPaginatePrevious disabled" id="customPagCheques_previous">Anterior</a><a
																class="customPaginate_button customPaginateNext disabled" id="customPagCheques_next">Siguiente</a>
														</div>
													</div>
												</div>
												<div class="row rowInformacion">
													<div class="span9">
														<span id="informacionRespuestaCheques" class="informacion"></span>
													</div>
												</div>
												<div class="row rowError">
													<div class="span9">
														<span id="errorRespuestaCheques" class="error"></span>
													</div>
												</div>
											</div>
										
											<div id= "divChequeSeleccionado">
												<p style="padding: 25px 0px 5px 0px;">
													<strong><spring:message code="legajo.cheques.rechazados.legajo.chequeSeleccionado" /></strong>
												</p>
												<div class="row">
													<div class="span9">
														<table id="tablaChequesSeleccionados" class="tablaResultado">
															<thead>
																<tr>
																	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
																	<th></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.sistemaOrigen" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.numeroCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.descripcionBancoOrigenCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.codigoBancoOrigenCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.clienteLegado"  /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.tipoCheque" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaVencimientoCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.monedaCheque" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.importeCheque" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.bancoDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.cuentaDeposito" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.acuerdo" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaRecepcionCheque" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.estado" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.empresa" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.segmento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaDueño" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.copropietario" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaTeamComercial" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.idInternoValor" /></th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
												</div>
											<div id="divEdicionManual">
											<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.legajo.chequeEdicionManual" /></strong></p>
												<div class="row">
													<div class="span9" id="idChequesEdicionManual">
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edsistemaOrigen"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.sistema.origen" /></label>
																<div class="controls">
																	<input id="edsistemaOrigen" name="edsistemaOrigen" type="text" class="input"  readonly
																		value="<c:out value = "${sistemaOrigenDefecto.descripcion}"/>"/>
																</div>
															</div>
			
															<div class="span3">
																<label class="control-label" for="edTipoCheque"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.tipo.cheque" /></label>
																<div class="controls">
																	<select id="edTipoCheque" name="edTipoCheque" class="input">
																		<option value=""><spring:message code="combo.seleccionar"/></option>
																		<c:forEach items="${edTipoCheque}" var="cheq">
																			<option value="${cheq.idTipoCheque}">${cheq.descripcion}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														
														<div class="row rowError">
															<div class="span3">
																<span id="errorEdsistemaOrigen" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdTipoCheque" class="error"></span>
															</div>
														</div>
														
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edCodigoBancoOrigen"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.codigo.banco.origen" /></label>
																<div class="controls">
																	<select id="edCodigoBancoOrigen" name="edCodigoBancoOrigen" class="input">
																	<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																	<c:forEach items="${bancoOrigenes}" var="ori">
																		<option value="${ori.idBanco}" idAgrupador="${ori.idAgrupador}" >${ori.idBanco} - ${ori. descripcion}</option>
																	</c:forEach> 
																	</select>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edDescripcionBancoOrigen"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.descripcion.banco.origen" /></label>
																<div class="controls">
																	<select id="edDescripcionBancoOrigen" name="edDescripcionBancoOrigen" class="input">
																	<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																	<c:forEach items="${bancoOrigenesDescripcion}" var="ori">
																		<option value="${ori.idValue}">${ori.descripcion}</option>
																	</c:forEach> 
																	</select>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edNroCheque"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.cheque.filtro.nro.cheque" /></label>
																<div class="controls">
																	<input id="edNroCheque" name="edNroCheque" type="text" class="input" maxlength="8"/>
																</div>
															</div>
														</div>
														
														<div class="row rowError">
															<div class="span3">
																<span id="errorEdCodigoBancoOrigen" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdDescripcionBancoOrigen" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdNroCheque" class="error"></span>
															</div>
														</div>
														
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edFechaEmision"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.emision" /></label>
																<div class="controls">
																	<input id="edFechaEmision" name="edFechaEmision" type="text" class="input" maxlength="10"/>
																</div>
															</div>
															<div class="span3">
																<label class="control-label" for="edFechaDeposito"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.deposito" /></label>
																<div class="controls">
																	<input id="edFechaDeposito" name="edFechaDeposito" type="text" class="input" maxlength="10"/>
																</div>
															</div>
															<div class="verFechaVencimiento" style="display: none;">
																<div class="span3">
																	<label class="control-label" for="edFechaVencimiento"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.vencimiento" /></label>
																	<div class="controls">
																		<input id="edFechaVencimiento" name="edFechaVencimiento" type="text" class="input" maxlength="10"/>
																	</div>
																</div>
															</div>
														</div>
														
														<div class="row rowError">
															<div class="span3">
																<span id="erroredFechaEmision" class="error"></span>
															</div>
															<div class="span3">
																<span id="erroredFechaDeposito" class="error"></span>
															</div>
															<div class="verFechaVencimiento">
																<div class="span3">
																	<span id="erroredFechaVencimiento" class="error"></span>
																</div>
															</div>
														</div>
														
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edMonedaCheque"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.moneda" /></label>
																<div class="controls">
<!-- 																	<input id="edMonedaCheque" name="edMonedaCheque" type="text" class="input"/> -->
																	<select id="edMonedaCheque" name="edMonedaCheque" class="input">
																		<c:forEach items="${monedas}" var="mon">
																			<option value="${mon.signo}">${mon.descripcion}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>	
			
															<div class="span3">
																<label class="control-label" for="edImporteCheque"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.importe" /></label>
																<div class="controls">
																	<input id="edImporteCheque" name="edImporteCheque" type="text" class="input"
																	onkeypress="return validarInputNumericosComaPunto(event)" maxlength="14"/>
																</div>
															</div>
														</div>	
														
														<div class="row rowError">
															<div class="span3">
																<span id="errorEdMonedaCheque" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdImporteCheque" class="error"></span>
															</div>
														</div>
														
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edAcuerdoCheque"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.acuerdo" /></label>
																<div class="controls">
																	<select id="edAcuerdoCheque" name="edAcuerdoCheque" class="input">
																	<c:if test="${comboAcuerdoDeposito}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																	<c:forEach items="${combo.listaAcuerdos}" var="acu">
																		<option value="${acu.idAcuerdo}">${acu.descripcion}</option>
																	</c:forEach>	 
																	</select>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edBancoDeposito"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.banco.deposito" /></label>
																<div class="controls">
																	<select id="edBancoDeposito" name="edBancoDeposito" class="input">
																	<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																	<c:forEach items="${bancoOrigenes1}" var="ori">
																		<option value="${ori.idBanco}">${ori.descripcion}</option>
																	</c:forEach> 
																	</select>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edCuentaDeposito"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.cuenta.deposito" /></label>
																<div class="controls">
																	<select id="edCuentaDeposito" name="edCuentaDeposito" class="input">
																		<c:if test="${comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
																	<c:forEach items="${combo.listaCuentas}" var="ori">
																		<option value="${ori.idBancoCuenta}">${ori.cuentaBancaria}</option>
																	</c:forEach> 
																	</select>
																</div>
															</div>
														</div>
														
														<div class="row rowError">
															<div class="span3">
																<span id="errorEdAcuerdoCheque" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdBancoDeposito" class="error"></span>
															</div>
															<div class="span3">
																<span id="erroredCuentaDeposito" class="error"></span>
															</div>
														</div>
														<div class="row" id="idDivClienteSiebel">
															<div class="span3">
																<label class="control-label" for="edInputCodCliente"><spring:message code="valor.codCliente"/></label>
																<div class="controls">
																	<input id="edInputCodCliente" name="edInputCodCliente" type="text" class="input" maxlength="11" />
																</div>
															</div>
															<div class="span6">
																<label class="control-label" for="edRazonSocialClienteLegado"><span class="rojo">* </span><spring:message code="valor.razonSocial"/></label>
																<div class="controls">
																	<input id="edRazonSocialClienteLegado" name="edRazonSocialClienteLegado" type="text" class="input span6" maxlength="200"/>
																</div>
															</div>
															
														</div>
														<div class="row rowError" id="idErrorDivClienteSiebel" >
															<div class="span3">
																<span id="errorEdInputCodCliente" class="error"></span>
															</div>
															<div class="span3">
																<span id="errorEdInputCodCliente2" class="error"></span>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edMontoPendienteReembolsar"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.pendiente.reenbolsar" /></label>
															</div>
															<div class="span3">
																<label class="control-label" for="edMontoAplicadoPendienteReembolsar"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.aplicado.pendiente.revertir" /></label>
															</div>
															
														</div>
														<div class="row">
															<div class="span3">
																<input id="edMontoPendienteReembolsarInput" name="edMontoPendienteReembolsarInput" type="text" class="input" value="$0,00" readOnly/>
															</div>
															<div class="span3">
																<input id="edMontoAplicadoPendienteReembolsarInput" name="edMontoPendienteReembolsarInput" type="text" class="input" value="$0,00" readOnly/>
															</div>
															
														</div>
														
													</div>
													
												</div>
												
											</div>
										<!-- FIN BUSQUEDA CLIENTES -->
										</div>
									<form:form id="formLegajo" commandName="legajoChequeRechazadoDto">
										<input type="hidden" id="volver" name="volver" value="/editar-legajo-cheque-rechazado"/>
										<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
										<input type="hidden" id="goBack" name="goBack" />
										<input type="hidden" id="idAnalista" name="idAnalista" value="${idUsuario}" />
										<input type="hidden" id="estado" name="estado" />
										<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" />
										<input type="hidden" id="formOrigen" name="formOrigen"/>
										<input type="hidden" id="idLeg" name="idLeg" value="${idLeg}"/>
										<input type="hidden" id="solapa" name="solapa" value="${solapa}"/>
										
										
									</form:form>
									<form:form id="formNotificacion" commandName="notificacion">
										<input type="hidden" id="idNotificacion" name="idNotificacion" />
									</form:form>
									<h3><spring:message code="legajo.cheques.rechazados.solapa.cobrosrelacionados"/></h3>
									<div id="cobro-tab">
										<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.listado.cobrosrelacionados"/></strong></p>
																						
										<div class="row">
											<div class="span9">
												<table id="tablaCobrosRelacionados" class="tablaResultado">
													<thead>
														<tr>
															<th><input type="checkbox" id="seleccionarTodos" disabled/></th>
															<th></th>
															<th></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.sistemaImputacion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.idOperacion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.tipoDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroLegalDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.convenioFinanciacion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.cuotaFinanciacion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.clienteLegado" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeDocumento" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeCheque" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeEfectivo" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeRetenciones" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeTotalBonos" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeOtrasMonedas" /></th>
															<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaImputacionSistemaOrigen" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.idReversion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.analista" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.copropietario" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.analistaTeamComercial" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.estadoCobro" /></th>
															<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaReversion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nombreArchivoConReversion" /></th>
															<th></th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowInformacion">
											<div class="span9">
												<span id="informacionCobrosRelacionados" class="informacion"></span>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorCobrosRelacionados" class="error"></span>
											</div>
										</div>
										<div class="row">
											<div align="right" div class="span9">
												<button class="btn btn-primary btn-small" id="btnRevertir" name="btnRevertir" type="button" style="display: none;" disabled>
														<spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.revertir" />
												</button>
												<button class="btn btn-primary btn-small" id="btnConfirmarManual" name="btnConfirmarManual" type="button"  disabled>
													<spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.confirmacionManual" />
												</button>
											</div>
										</div>
									</div>
									<h3><spring:message code="legajo.cheques.rechazados.solapa.reembolso"/></h3>
									<div id="reembolso-tab">
									<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.listado.documentosRelacionados"/></strong></p>
																						
										<div class="row">
											<div class="span9">
												<table id="tablaDocumentosRelacionados" class="tablaResultado">
													<thead>
														<tr>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.sistema" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.tipoComprobante" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroLegalDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.clienteLegado" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.moneda" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeTotalDocumento" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoDocumento" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeAplicado" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoActualDocumento" /></th>
															<th class="date-time"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.ultimaFechaConsulta" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="row rowInformacion">
											<div class="span9">
												<span id="informacionDocumentosRelacionados" class="informacion"></span>
											</div>
										</div>
										<div class="row rowError">
											<div class="span9">
												<span id="errorDocumentosRelacionados" class="error"></span>
											</div>
										</div>
										<div class="row">
											<div align="right" div class="span9">
												<button class="btn btn-primary btn-small" id="btnConfirmarReembolso" name="btnConfirmarReembolso" type="button" style="display: none;" disabled>
														<spring:message code="legajo.cheques.rechazados.legajo.reembolso.boton.reembolsar" />
												</button>
												<button class="btn btn-primary btn-small" id="btnEnviarLegales" name="btnEnviarLegales" type="button"  style="display: none;" disabled>
													<spring:message code="legajo.cheques.rechazados.legajo.reembolso.boton.legales" />
												</button>
											</div>
										</div>
									</div>
									<h3><spring:message code="legajo.cheques.rechazados.solapa.notificaciones"/></h3>
									
									
									<div id="notificacion-tab">
										<div class="row">
											<div class="span9" id="idNotificaciones">
												<div  id="idNotificacionesInput">
													<div class="row">
														<div class="span3">
															<label class="control-label" for="tipoNotificacion"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.tipo.notificacion" /></label>
															<div class="controls">
																<select id="selectTipoNotificacion" name="tipoNotificacion" class="input">
																	<c:if test="${comboTipoNotificacion}">
																				<option value=""><spring:message code="combo.seleccionar" /></option>
																	</c:if>
																	<c:forEach items="${tipoNotificacion}" var="notificacion">
																		<option value="${notificacion.indice}">${notificacion.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="span3">
															<label class="control-label" for="notificacionReceptor"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.datos.receptor" /></label>
															<div class="controls">
																<input id="notificacionReceptor" name="notificacionReceptor" type="text" class="input" style="margin: 0 auto;" maxlength="255"/>
															</div>
														</div>
														
														<div class="span3">
															<label class="control-label" for="fechaContacto"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.notificacion.fecha.contacto" /></label>
															<div class="controls">
																<input id="fechaContacto" name="fechaContacto" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' maxlength="10" />
															</div>
														</div>
													</div>
													<div class="row rowError">
														<div class="span3">
															<span id="errortipoNotificacion" class="error" ></span>
														</div>
														<div class="span3">
															<span id="errornotificacionReceptor" class="error" ></span>
														</div>
														<div class="span3">
															<span id="errorfechaContacto" class="error" ></span>
														</div>
													</div>
													<div class="row">
														<div class="span3">
															<label class="control-label" for="tipoContacto"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.tipo.contacto" /></label>
															<div class="controls">
																<select id="selectTipoContacto" name="tipoContacto" class="input">
																	<c:if test="${comboTipoContacto}">
																		<option value=""><spring:message code="combo.seleccionar" /></option>
																	</c:if>
																	<c:forEach items="${tipoContacto}" var="tipoContacto">
																		<option value="${tipoContacto.indice}">${tipoContacto.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
													<div class="row rowError">
														<div class="span3">
															<span id="errortipoContacto" class="error" ></span>
														</div>
													</div>
													<div class="row">
														<div class="span9" id="datos-carta-notificacion" style="display:none">
															<div class="row">
																<div class="span3">
																	<label class="control-label" for="cartaCalle"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.calle" /></label>
																	<div class="controls">
																		<input id="cartaCalle" name="cartaCalle" type="text" class="input" style="margin: 0 auto;" maxlength="50"/>
																	</div>
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaNumero"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.numero" /></label>
																	<div class="controls">
																		<input id="cartaNumero" name="cartaNumero" type="text" class="input" style="margin: 0 auto;" maxlength="50"/>
																	</div>
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaPiso"><spring:message code="legajo.cheques.rechazados.notificacion.carta.piso" /></label>
																	<div class="controls">
																		<input id="cartaPiso" name="cartaPiso" type="text" class="input" style="margin: 0 auto;" maxlength="2"/>
																	</div>
																</div>
															</div>
															<div class="row rowError" style="margin-top: 10px;">
																<div class="span3">
																	<span id="errorcartaCalle" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaNumero" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaPiso" class="error" ></span>
																</div>
															</div>
															
															<div class="row">
																<div class="span3">
																	<label class="control-label" for="cartaDepartamento"><spring:message code="legajo.cheques.rechazados.notificacion.carta.departamento" /></label>
																	<div class="controls">
																		<input id="cartaDepartamento" name="cartaDepartamento" type="text" class="input" style="margin: 0 auto;" maxlength="50"/>
																	</div>
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaCodPostal"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.codigoPostal" /></label>
																	<div class="controls">
																		<input id="cartaCodPostal" name="cartaCodPostal" type="text" class="input" style="margin: 0 auto;" maxlength="8"/>
																	</div>
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaLocalidad"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.localidad" /></label>
																	<div class="controls">
																		<input id="cartaLocalidad" name="cartaLocalidad" type="text" class="input" style="margin: 0 auto;" maxlength="50"/>
																	</div>
																</div>
															</div>
															
															<div class="row rowError" style="margin-top: 10px;">
																<div class="span3">
																	<span id="errorcartaDepartamento" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaCodPostal" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaLocalidad" class="error" ></span>
																</div>
															</div>
															
															<div class="row">
																<div class="span3">
																	<label class="control-label" for="cartaProvincia"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.provincia" /></label>
																	<select id="cartaProvincia" name="cartaProvincia" class="input">
																		<c:if test="${comboProvincias}">
																			<option value=""><spring:message code="combo.seleccionar" /></option>
																		</c:if>
																		<c:forEach items="${provincias}" var="pro">
																			<option value="${pro.idProvincia}">${pro.descripcion}</option>
																		</c:forEach>
																	</select>
																	
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaNumeroServicio"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.numero.servicio" /></label>
																	<div class="controls">
																		<input id="cartaNumeroServicio" name="cartaNumeroServicio" type="text" class="input" style="margin: 0 auto;" maxlength="50"/>
																	</div>
																</div>
																<div class="span3">
																	<label class="control-label" for="cartaFechaReembolso"><span class="rojo">*</span> <spring:message code="legajo.cheques.rechazados.notificacion.carta.fecha.reembolso" /></label>
																	<div class="controls">
																		<input id="cartaFechaReembolso" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' maxlength="10" />
																	</div>
																</div>
															</div>
															<div class="row rowError">
																<div class="span3">
																	<span id="errorcartaProvincia" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaNumeroServicio" class="error" ></span>
																</div>
																<div class="span3">
																	<span id="errorcartaFechaReembolso" class="error" ></span>
																</div>
															</div>
																
															<div class="row">
																<div class="span6">
																	<label class="control-label" for="cartaNombreReceptor">
																		<span class="rojo">*</span>
																		<spring:message code="legajo.cheques.rechazados.notificacion.carta.nombreReceptor"/>
																	</label>
																	<div class="controls">
																		<input id="cartaNombreReceptor" name="cartaNombreReceptor" type="text" class="input span6" maxlength="200"/>
																	</div>
																</div>
															</div>
															
															<div class="row rowError">
																<div class="span6">
																	<span id="errorcartaNombreReceptor" class="error" ></span>
																</div>
															</div>
															
															<div class="row rowError">
																<div class="span6">
																	<span id="errorcartaNombreReceptor" class="error" ></span>
																</div>
															</div>
														</div>
													</div>
											
												
													<div class="row" style="margin-top: 30px" id="prevObservaciones">
														<div class="span9">
															<label class="control-label" for="prevDatosContacto"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.notificacion.datos.contacto"/></label>
															<textarea class="field span9" id="prevDatosContacto" name="prevDatosContacto" maxlength="250"></textarea>
														</div>
													</div>
											
													<div class="row rowError">
														<div class="span9">
															<span id="errorprevDatosContacto" class="error" ></span>
														</div>
													</div>
											
													<div class="row">
														<div class="span3">
															<label class="control-label" for="selectAcuseRecibo"><spring:message code="legajo.cheques.rechazados.notificacion.acuse.recibo" /></label>
															<div class="controls">
																<select id="selectAcuseRecibo" name="selectAcuseRecibo" class="input">
																	<c:if test="${comboAcuseRecibo}">
																		<option value=""><spring:message code="combo.seleccionar" /></option>
																	</c:if>
																	<c:forEach items="${acuseRecibo}" var="acuseRecibo">
																		<option value="${acuseRecibo.indice}">${acuseRecibo.descripcion}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														
														<div class="span3">
															<label class="control-label" for="fechaRecepcion"><spring:message code="legajo.cheques.rechazados.notificacion.fecha.recepcion" /></label>
															<div class="controls">
																<input id="fechaRecepcion" name="fechaRecepcion" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' maxlength="10" />
															</div>
														</div>
													</div>
														<div class="row rowError">
															<div class="span3">
																<span id="errorselectAcuseRecibo" class="error" ></span>
															</div>
															<div class="span3">
																<span id="errorfechaRecepcion" class="error" ></span>
															</div>
															
														</div>
														
														<div class="row" style="margin-top: 30px">
															<div class="span9">
																<label class="control-label" for="prevObservacionesNotificacion"><spring:message code="legajo.cheques.rechazados.legajo.observaciones"/></label>
																<textarea class="field span9" id="prevObservacionesNotificacion" name="prevObservacionesNotificacion" maxlength="250"></textarea>
															</div>
														</div>
														<div class="row rowError">
															<div class="span9">
																<span id="errorprevObservacionesNotificacion" class="error" ></span>
															</div>
														</div>
														<div class="row">
															<div class="span9">
																<button type="button" class="btn btn-primary btn-small" id="btnGuardarContacto" ><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.notificacion.guardar.contacto" /></button>
															</div>
														</div>
													</div>
													<br>
													<br>
													<!--  FER -->
													<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.listado.notificacionesrelacionadas"/></strong></p>
																						
													<div class="row">
														<div class="span9">
															<table id="tablaNotificaciones" class="tablaResultado">
																<thead>
																	<tr>
																		<th></th>
																		<th></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoNotificacion" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosReceptor" /></th>
																		<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaContacto" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoContacto" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosContacto" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.acuseRecibo" /></th>
																		<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaRecepcion" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.observaciones" /></th>
																		<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.usuarioCargoNotificacion" /></th>
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
															<span id="errorNotificaciones" class="error"></span>
														</div>
													</div>
											</div>
										</div>
										
										
									</div>
									<h3><spring:message code="legajo.cheques.rechazados.solapa.comprobantes"/></h3>
									<div id="comprobante-tab">
										<div id="bloqueAgregarComprobante">
											<div class="row" style="margin-top: 20px;">
												<div class="span9">
													<form id="adjComprobanteForm" method="post" action="legajo-cheque-rechazado/adjuntarComprobante" enctype="multipart/form-data">
														<label class="control-label" for="comprobanteArch">
															<spring:message code="legajo.cheques.rechazados.legajo.comprobante.comprobante"/>
														</label>
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="hidden">
															<div class="input-append">
																<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																	<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																</div>
																<span class="btn btn-file btn-primary btn-small" style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																	<span class="fileupload-new">
																		<spring:message code="legajo.cheques.rechazados.legajo.comprobante.seleccionarArchivo"/>
																	</span> 
																	<span class="fileupload-exists">
																		<spring:message code="legajo.cheques.rechazados.legajo.comprobante.cambiar"/>
																	</span>
																	<input name="comprobanteArch" id="comprobanteArch" type="file" />
																</span>
																<a href="#" class="btn fileupload-exists btn-primary btn-small" style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload">
																	<spring:message code="legajo.cheques.rechazados.legajo.comprobante.eliminar"/>
																</a>
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
													<label class="control-label" for="descripcionComprobante">
														<spring:message code="legajo.cheques.rechazados.legajo.comprobante.descripcion"/>
													</label>
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
													<button type="button" class="btn btn-primary btn-small" id="btnAdjComprobante">
														<spring:message code="legajo.cheques.rechazados.legajo.comprobante.adjuntar"/>
													</button>
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
<!-- 									FIN COMPROBANTES -->
<!-- 									RESUMEN LEGAJO -->
									<h3><spring:message code="legajo.cheques.rechazados.solapa.resumenlegajo"/></h3>
									<div id="resumen-tab">
									<div id="divChequePrev">
									
										<p style="padding: 0px 0px 5px 0px;">
											<strong><spring:message
													code="legajo.cheques.rechazados.resumen.subtitulo.datosLegajos" /></strong>
										</p>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="empresa"><spring:message
														code="legajo.cheques.rechazados.legajo.empresa" /></label> <input
													id="empresaPrev" name="empresaPrev" type="text"
													class="input" readonly>
											</div>
											<div class="span3">
												<label class="control-label" for="segmento"><spring:message
														code="legajo.cheques.rechazados.legajo.segmento" /></label> <input
													id="segmentoPrev" name="segmentoPrev" type="text"
													class="input" readonly>
											</div>
										</div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="analista"><spring:message
														code="legajo.cheques.rechazados.legajo.analista" /></label> <input
													id="analistaPrev" name="analistaPrev" type="text"
													class="input" readonly="" style="margin: 0 auto;">
											</div>
											<div class="span3">
												<label class="control-label" for="copropietario"><spring:message
														code="legajo.cheques.rechazados.legajo.copropietario" /></label>
												<input id="copropietarioPrev" name="copropietarioPrev"
													type="text" class="input" readonly>
											</div>
											<div class="span3">
												<label class="control-label" for=ubicacion><spring:message
														code="legajo.cheques.rechazados.legajo.ubicacion.cheque" /></label>
												<input id="ubicacionPrev" name="ubicacionPrev" type="text"
													class="input" readonly>
											</div>
										</div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="fechaRechazo"><spring:message
														code="legajo.cheques.rechazados.legajo.fecha.rechazo" /></label>
												<input id="fechaRechazoPrev" name="fechaRechazoPrev"
													type="text" class="input" readonly>
											</div>
											<div class="span3">
												<label class="control-label" for="motivoRechazo"><spring:message
														code="legajo.cheques.rechazados.legajo.motivo.rechazo" /></label>
												<input id="motivoRechazoPrev" name="motivoRechazoPrev"
													type="text" class="input" readonly>
											</div>
											<div class="span3">
												<label class="control-label" for="fechaNotificacionRechazo"><spring:message
														code="legajo.cheques.rechazados.legajo.fecha.notificacion.rechazo" /></label>
												<input id="fechaNotificacionRechazoPrev"
													name="fechaNotificacionRechazoPrev" type="text"
													class="input" readonly>
											</div>
										</div>

										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message
													code="legajo.cheques.rechazados.resumen.subtitulo.chequeRechazado" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="tablaChequesPrev" class="tablaResultado">
													<thead>
														<tr>
															<th></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.sistemaOrigen" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.numeroCheque" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.descripcionBancoOrigenCheque" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.codigoBancoOrigenCheque" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.clienteLegado" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.tipoCheque" /></th>
															<th class="date"><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.fechaVencimientoCheque" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.monedaCheque" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.importeCheque" /></th>
															<th class="date"><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.fechaDeposito" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.bancoDeposito" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.cuentaDeposito" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.acuerdo" /></th>
															<th class="date"><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.fechaRecepcionCheque" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.estado" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.empresa" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.segmento" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.analistaDueño" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.copropietario" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.analistaTeamComercial" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.tablaResultado.idInternoValor" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										
<!-- 										EDICION MANUAL RESUMEN -->
										<div id="divEdicionManualPrev">
											<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.legajo.chequeEdicionManual" /></strong></p>
												<div class="row">
													<div class="span9" id="idChequesEdicionManualPrev">
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edsistemaOrigenPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.sistema.origen" /></label>
																<div class="controls">
																	<input id="edsistemaOrigenPrev" name="edsistemaOrigenPrev" type="text" class="input"  readonly/>
																</div>
															</div>
			
															<div class="span3">
																<label class="control-label" for="edTipoChequePrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.tipo.cheque" /></label>
																<div class="controls">
																	<input id="edTipoChequePrev" name="edTipoChequePrev" type="text" class="input" readonly/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edCodigoBancoOrigenPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.codigo.banco.origen" /></label>
																<div class="controls">
																	<input id="edCodigoBancoOrigenPrev" name="edCodigoBancoOrigenPrev" type="text" class="input"  readonly/>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edDescripcionBancoOrigenPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.descripcion.banco.origen" /></label>
																<div class="controls">
																	<input id="edDescripcionBancoOrigenPrev" name="edDescripcionBancoOrigenPrev" type="text" class="input"  readonly/>
																</div>
															</div>
															
															<div class="span3">
																<label class="control-label" for="edNroChequePrev"><spring:message code="legajo.cheques.rechazados.cheque.filtro.nro.cheque" /></label>
																<div class="controls">
																	<input id="edNroChequePrev" name="edNroChequePrev" type="text" class="input" readonly/>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edFechaEmisionPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.emision" /></label>
																<div class="controls">
																	<input id="edFechaEmisionPrev" name="edFechaEmisionPrev" type="text" class="input" readonly/>
																</div>
															</div>
															<div class="span3">
																<label class="control-label" for="edFechaDepositoPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.deposito" /></label>
																<div class="controls">
																	<input id="edFechaDepositoPrev" name="edFechaDepositoPrev" type="text" class="input" readonly/>
																</div>
															</div>
															<div class="verFechaVencimiento" style="display: none;">
																<div class="span3">
																	<label class="control-label" for="edFechaVencimientoPrev"><span class="rojo">* </span><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.vencimiento" /></label>
																	<div class="controls">
																		<input id="edFechaVencimientoPrev" name="edFechaVencimientoPrev" type="text" class="input" readonly/>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edMonedaChequePrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.moneda" /></label>
																<div class="controls">
																	<input id="edMonedaChequePrev" name="edMonedaChequePrev" type="text" class="input" readonly/>
																</div>
															</div>	
															<div class="span3">
																<label class="control-label" for="edImporteChequePrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.importe" /></label>
																<div class="controls">
																	<input id="edImporteChequePrev" name="edImporteChequePrev" type="text" class="input" readonly/>
																</div>
															</div>
														</div>	
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edAcuerdoChequePrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.acuerdo" /></label>
																<div class="controls">
																	<input id="edAcuerdoChequePrev" name="edAcuerdoChequePrev" type="text" class="input" readonly/>
																</div>
															</div>
															<div class="span3">
																<label class="control-label" for="edBancoDepositoPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.banco.deposito" /></label>
																<div class="controls">
																	<input id="edBancoDepositoPrev" name="edBancoDepositoPrev" type="text" class="input" readonly/>
																</div>
															</div>
															<div class="span3">
																<label class="control-label" for="edCuentaDepositoPrev"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.cuenta.deposito" /></label>
																<div class="controls">
																	<input id="edCuentaDepositoPrev" name="edCuentaDepositoPrev" type="text" class="input" readonly/>
																</div>
															</div>
														</div>
														<div class="row" id="idDivClienteSiebel">
															<div class="span3">
																<label class="control-label" for="edInputCodClientePrev"><spring:message code="valor.codCliente"/></label>
																<div class="controls">
																	<input id="edInputCodClientePrev" name="edInputCodClientePrev" type="text" class="input" readonly />
																</div>
															</div>
															<div class="span6">
																<label class="control-label" for="edRazonSocialClienteLegadoPrev"><spring:message code="valor.razonSocial"/></label>
																<div class="controls">
																	<input id="edRazonSocialClienteLegadoPrev" name="edRazonSocialClienteLegadoPrev" type="text" class="input span6" readonly />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<label class="control-label" for="edMontoPendienteReembolsar"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.pendiente.reenbolsar" /></label>
															</div>
															<div class="span3">
																<label class="control-label" for="edMontoAplicadoPendienteReembolsar"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.aplicado.pendiente.revertir" /></label>
															</div>
														</div>
														<div class="row">
															<div class="span3">
																<input id="edMontoPendienteReembolsarInputPrev" name="edMontoPendienteReembolsarInputPrev" type="text" class="input" value="$0,00" readonly/>
															</div>
															<div class="span3">
																<input id="edMontoAplicadoPendienteReembolsarInputPrev" name="edMontoPendienteReembolsarInputPrev" type="text" class="input" value="$0,00" readOnly/>
															</div>
														</div>
													</div>
												</div>
											</div>
										<!-- 								COBROS RELACIONADOS PREVISUALIZACION -->
										<p style="padding: 25px 0px 5px 0px;">
											<strong><spring:message
													code="legajo.cheques.rechazados.resumen.subtitulo.cobrosRelacionados" /></strong>
										</p>
										<div class="row">
											<div class="span9">
												<table id="tablaCobrosRelacionadosPrev"
													class="tablaResultado">
													<thead>
														<tr>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.sistemaImputacion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.idOperacion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.tipoDocumento" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroLegalDocumento" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroDocumento" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.convenioFinanciacion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.cuotaFinanciacion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.clienteLegado" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeDocumento" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeCheque" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeEfectivo" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeRetenciones" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeTotalBonos" /></th>
															<th class="importe"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeOtrasMonedas" /></th>
															<th class="date"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaImputacionSistemaOrigen" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.idReversion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.analista" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.copropietario" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.analistaTeamComercial" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.estadoCobro" /></th>
															<th class="date"><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaReversion" /></th>
															<th><spring:message
																	code="legajo.cheques.rechazados.legajo.cobroRelacionado.nombreArchivoConReversion" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
<!-- 										REEMBOLSO -->
										<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.resumen.subtitulo.documentosRelacionados"/></strong></p>
																						
										<div class="row">
											<div class="span9">
												<table id="tablaDocumentosRelacionadosPrev" class="tablaResultado">
													<thead>
														<tr>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.sistema" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.tipoComprobante" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroLegalDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroDocumento" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.clienteLegado" /></th>
															<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.moneda" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeTotalDocumento" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoDocumento" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeAplicado" /></th>
															<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoActualDocumento" /></th>
															<th class="date-time"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.ultimaFechaConsulta" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.solapa.subtitulo.listado.notificacionesrelacionadas"/></strong></p>
										<div class="row">
											<div class="span9">
												<table id="tablaNotificacionesPrev" class="tablaResultado">
													<thead>
														<tr>
															<th>&nbsp;</th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoNotificacion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosReceptor" /></th>
															<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaContacto" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoContacto" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosContacto" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.acuseRecibo" /></th>
															<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaRecepcion" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.observaciones" /></th>
															<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.usuarioCargoNotificacion" /></th>
															<th>&nbsp;</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.resumen.subtitulo.comprobantes"/></strong></p>
										<div class="row" style="margin-top: 20px">
											<div class="span9">
												<table id="comprobantesPrev" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th></th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nombre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
															<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
														</tr>
													</thead>
												</table>
												
											</div>
											
										</div>
										<div class="row" style="margin-top: 20px">
											<div align="right" class="span9">
													<button type="button" class="btn btn-primary btn-small"
														style="display: none;" id="btnDesistir">
														<i class="icon-white icon-repeat"></i>&nbsp;
														<spring:message
															code="legajo.cheques.rechazados.legajo.boton.desistir" />
													</button>
													<button type="button" class="btn btn-primary btn-small"
														style="display: none;" id="btnCerrar">
														<i class="icon-white icon-repeat"></i>&nbsp;
														<spring:message
															code="legajo.cheques.rechazados.legajo.boton.cerrar" />
													</button>
											</div>
										</div>
									</div>
									
								</div>
								</div>
								<div class="span9" style="margin-top: 16px" align="right">
									<div>
										<button type="button" class="btn btn-primary btn-small" id="btnExportarPie" style="display: none;"><i class="icon-white icon-download-alt"></i> Exportar Legajo a Excel</button>
										<button type="button" class="btn btn-primary btn-small" id="btnGuardarPie" style="display: none;"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.guardar" /></button>
										<button type="button" class="btn btn-primary btn-small" id="btnConfirmarPie"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.confirmar" /></button>
										<!-- <button type="button" class="btn btn-primary btn-small" id="btnCancelarPie"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.cancelar" /></button> -->
<!-- 										<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnCerrarPie"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.cerrar"/></button> --!>
<!-- 										<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnDesistirPie"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="legajo.cheques.rechazados.legajo.boton.desistir"/></button> --!>
										<button type="button" class="btn btn-primary btn-small" style="display:none;" id="btnVolverPie"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="accion.goBack"/></button>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<div id="alertErrorGuardadoLegajo2" class="alert alert-error" align="center" style="text-align: left;margin-left: auto; margin-right: auto;display: none;width:279px; margin-top:20px">
											<button id="btnCruzErrorGuardadoLegajoPie" class="close" >×</button><strong><spring:message code="error.error"/></strong><br/>
											<span id="errorGuardadoLegajo2"></span>
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

