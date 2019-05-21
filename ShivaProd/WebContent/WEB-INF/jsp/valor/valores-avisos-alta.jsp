<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<title><spring:message code="valor.alta.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/valor/valores-avisos-alta.js"></script>
	<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
	<script src="${pageContext.request.contextPath}/js/valor/validaciones.valor.js"></script>
	<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	
	<meta name="Alta de Avisos" content="Modelo">
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
	<script>
		
		var bancoDescripcion = ${bancoDescripcion};
		var paramTipoGestion = ${paramTipoGestion};
		var tipoValores = ${tipoValores};
		var leyendaComboSeleccionar = '${leyendaComboSeleccionar}';
		var TipoValorEnum = ${TipoValorEnum};
		var mensajeBtnAceptar = '<spring:message code="error.valorEnProcesoDeContinuar"/>';
		var codigos = '';
		<%String lista= (String)request.getAttribute("listaLegadoRazonSocial");
			if (lista!=null && !"".equalsIgnoreCase(lista)) {%>
			codigos = <%=lista%>;
		<%}%>
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
						<div class="title"><spring:message code="valor.alta.titulo.pagina"/></div>
						<div class="pagos_anticipos">
							<p><strong><spring:message code="valor.datosGenerales"/></strong></p>

							<form:form id="formValor" method="POST">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" id="mensajeAlta" name="mensajeAlta" />
								<input type="hidden" id="mensajeAltaDuplicado" name="mensajeAltaDuplicado" />
								<input type="hidden" id="url" name="url" />
							</form:form>
							<form:form id="formAlta" enctype="multipart/form-data" commandName="valoresDto">
								<form:hidden path="idValorSelected" id="idValorSelected"/>
								<form:hidden path="valorDto.operation" id="operation"/>
								<form:hidden path="valorDto.idAnalista" id="idAnalista"/>
								<form:hidden path="valorDto.empresa" id="empresa"/>
								<form:hidden path="valorDto.segmento" id="segmento"/>
								<form:hidden path="valorDto.copropietario" id="copropietario"/>
								<form:hidden path="valorDto.tipoValor" id="tipoValor"/>
								<form:hidden path="valorDto.motivo" id="motivo"/>
								<form:hidden path="valorDto.acuerdo" id="acuerdo"/>
								<form:hidden path="valorDto.bancoDeposito" id="bancoDeposito"/>
								<form:hidden path="valorDto.numeroCuenta" id="numeroCuenta"/>
								<form:hidden path="valorDto.bancoOrigen" id="bancoOrigen"/>
								<form:hidden path="valorDto.tipoImpuesto" id="tipoImpuesto"/>
								<form:hidden path="valorDto.provincia" id="provincia"/>
								<form:hidden path="valorDto.tipoComprobante" id="tipoComprobante"/>
								<form:hidden path="valorDto.letraComprobante" id="letraComprobante"/>
								<form:hidden path="valorDto.comboComprobante" id="comboComprobante"/>
								<form:hidden path="mantenerComprobantesAdjuntos" id="mantenerComprobantesAdjuntos"/>
							<div class="row">
								<div class="span3">
									<label class="control-label" for="valorDto.idEmpresa"><span class="rojo">* </span> <spring:message code="valor.empresa"/></label>
									<div class="controls">
										<select id="selectEmpresa" name="valorDto.idEmpresa" class="input">
											<c:if test="${comboEmpresa}">
												<option value=""><spring:message code="combo.seleccionar"/></option>
											</c:if>
											<c:forEach items="${empresas}" var="emp">
												<option value="${emp.idEmpresa}">${emp.descripcion}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="span3">
									<label class="control-label" for="valorDto.idSegmento"><span class="rojo">* </span> <spring:message code="valor.segmento"/></label>
									<div class="controls">
										<select id="selectSegmento" name="valorDto.idSegmento" class="input">
											<c:if test="${comboSegmento}">
												<option value=""><spring:message code="combo.seleccionar"/></option>
											</c:if>
											<c:forEach items="${segmentos}" var="seg">
												<option value="${seg.idSegmento}">${seg.descripcion}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row rowError" >
								<div class="span3">
									<span id="errorEmpresa" class="error"></span>
								</div>
								<div class="span3">
									<span id="errorSegmento" class="error"></span>
								</div>
							</div>
							
<!-- Req 67475				u576779 - Se reemplazan los campos de clientes por combo de busqueda y grilla de clientes -->
<!-- 							INICIO NUEVO COMBO CLIENTES -->

								<div id='searchCriteraCliente'>
									<p>
										<strong><spring:message	code="conf.cobro.body.title.criterios" /></strong>
									</p>

									<div class="row">
										<div class="span3">
											<div class="controls">
												<select id="selectCriterio" name="selectCriterio"
													class="input">
													<option value=""><spring:message code="combo.seleccionar" /></option>
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
								<p>
									<strong><spring:message	code="conf.cobro.clientes.busqueda.resultado" /></strong>
								</p>
								<div class="row">
									<div class="span9">
										<table id="clientes" class="tablaResultado">
											<thead>
												<tr>
													<th></th>
													<th></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.cuit" /></th>
													<th class="numeroOrder"><spring:message
															code="conf.cobro.tabla.clientes.cliente" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.razonSocial" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.origen" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.holding" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.agencia" /></th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<p>
									<strong><span class="rojo">*</span><spring:message	code="conf.cobro.clientes.seleccionados" /></strong>
								</p>
								<div class="row">
									<div class="span9">
										<table id="clientesSeleccionados" class="tablaResultado">
											<thead>
												<tr>
													<th></th>
													<th></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.cuit" /></th>
													<th class="numeroOrder"><spring:message
															code="conf.cobro.tabla.clientes.cliente" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.razonSocial" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.origen" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.holding" /></th>
													<th><spring:message
															code="conf.cobro.tabla.clientes.agencia" /></th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<div class="row rowError">
									<div class="span9" align="left">
										<span id="errorClienteSeleccionado" class="error"></span>
									</div>
								</div>
<!-- 							FIN NUEVO COMBO CLIENTES -->
<!-- Bloque Email -->		<div id='bloqueEmail'>
								<div class="row">	
									<div class="span3">
										<label class="control-label" for="valorDto.email"><spring:message code="valor.email"/></label>
										<div class="controls">
											<input id="email" name="valorDto.email" type="email" class="input" maxlength="50">
										</div>
									</div>
						      	</div>
						      	<div class="row rowError" >
									<div class="span3">
										<span id="errorEmail" class="error"></span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="span3">
									<label class="control-label" for="valorDto.nombreAnalista"><span class="rojo">* </span><spring:message code="valor.analista"/></label>
									<div class="controls" style="white-space: nowrap;">
										<input class="input" id="nombreAnalista" name="valorDto.nombreAnalista" type="text" value="${nombreCompletoUsuario}" readonly>
									</div>
								</div>
								<div class="span3">
									<label class="control-label" for="valorDto.idCopropietario"><spring:message code="valor.copropietario"/></label>
									<div class="controls">
										<select id="selectCopropietario" name="valorDto.idCopropietario" class="input">
											<c:if test="${comboCopropietarios}">
												<option value=""><spring:message code="combo.seleccionar" /></option>
											</c:if>
											<c:forEach items="${copropietarios}" var="cop">
												<option value="${cop.tuid}">${cop.nombreCompleto}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>		
							<div class="row rowError" >
								<div class="span3">
									<span id="errorNombreAnalista" class="error"></span>
								</div>
								<div class="span3">
									<span id="errorCopropietario" class="error"></span>
								</div>
							</div>

<!-- SEGUNDA PARTE DEL FORMULARIO -->
							<p><strong><spring:message code="valor.boleta"/></strong></p>
							<div class="row">
								<div class="span3">
									<label class="control-label" for="valorDto.idTipoValor"><span class="rojo">* </span><spring:message code="valor.tipo.avisos.pago"/></label>
									<div class="controls">
										<select id ="selectTipoValor" name="valorDto.idTipoValor" class="input">
										<c:if test="${valoresDto.valorDto.comboTipoValor}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
										</select>
									</div>
								</div>
								<div class="span3">
									<label class="control-label" for="valorDto.importe"><span class="rojo">* </span><spring:message code="valor.importe"/></label>
									<div class="controls">
										<input id="inputImporte" name="valorDto.importe" type="text"  
											class="input" maxlength="16" value="${valoresDto.valorDto.importe}">
									</div>
								</div>
							</div>
							<div class="row rowError" >
								<div class="span3">
									<span id="errorTipoValor" class="error"></span>
								</div>
								<div class="span3">
									<span id="errorImporte" class="error"></span>
								</div>
							</div>
							
							<div class="row">
								<div class="span3">
									<label class="control-label" for="valorDto.idMotivo"><spring:message code="valor.motivo"/></label>
									<div class="controls">
										<select id ="selectMotivo" name="valorDto.idMotivo" class="input">
											<c:if test="${comboMotivos}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
											<c:forEach items="${motivos}" var="mot">
												<option value="${mot.idMotivo}">${mot.descripcion}</option>
											</c:forEach> 
										</select>
									</div>
								</div>
								<div class="span3">
									<label class="control-label" for="valorDto.operacionAsociada"><spring:message code="valor.operacionAsociada"/></label>
									<div class="controls">
										<input id="inputOperacionAsociada" name="valorDto.operacionAsociada" type="text"  
											class="input" maxlength="8" >
									</div>
								</div>
								<div class="span3" id="bloqueDocumento"  style="display:none">   
									<label class="control-label" for="valorDto.numeroDocumentoContable">
										<span class="rojo">* </span>
										<spring:message code="valor.nroDocumentoContable"/></label>
									<div class="controls">
										<input id="inputNumeroDocumentoContable" name="valorDto.numeroDocumentoContable" type="text"   
										 class="input" maxlength="10" >
									</div>
								</div>
							</div>
								<div class="row rowError" >
									<div class="span3">
									</div>
									<div class="span3">
										<span id="errorOperacionAsociada" class="error"></span>
									</div>
									<div class="span3"  align="left">
										<span id="errorNumeroDocumentoContable" class="error"></span>
									</div>
								</div>
						
									
<!-- BLOQUE ACUERDO -->		<div id="bloqueAcuerdo"  style="display:none">
								<div class="row">
									<div class="span3">
									<label class="control-label" for="valorDto.idAcuerdo"><span class="rojo">* </span><spring:message code="valor.acuerdo"/></label>
										<div class="controls">
											<select id ="selectAcuerdo" name="valorDto.idAcuerdo" class="input" >
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.idBancoDeposito"><span class="rojo">* </span><spring:message code="valor.bancoDeposito"/></label>
										<div class="controls">
											<select id ="selectBancoDeposito" name="valorDto.idBancoDeposito" class="input">
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.idNroCuenta"><span class="rojo">* </span><spring:message code="valor.nroCuenta"/></label>
										<div class="controls">
											<select id ="selectNumeroCuenta" name="valorDto.idNroCuenta" class="input">
											</select>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorAcuerdo" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorBancoDeposito" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorNumeroCuenta" class="error"></span>
									</div>
								</div>
							</div>
							
<!-- BLOQUE NRO BOLETA -->	<div id="bloqueNroBoleta"  style="display:none">
								<div class="row">	
									<div class="span3">
										<label class="control-label" for="valorDto.numeroBoleta"><span class="rojo" id="numeroBoletaObligatorio">* </span><spring:message code="valor.nroBoleta"/></label>
										<div class="controls">
											<input id="inputNroBoleta" name="valorDto.numeroBoleta" type="text"  
											 class="input" maxlength="10">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.fechaDeposito"><span class="rojo">* </span><spring:message code="valor.fechaDeposito"/></label>
										<div class="controls">
											<input id="inputFechaDeposito"  data-date-format="dd/mm/yyyy" data-date-language='es' name="valorDto.fechaDeposito" type="text"
												class="input" maxlength="10">
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorNumeroBoleta" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorFechaDeposito" class="error"></span>
									</div>
								</div>
							</div>

<!-- BLOQUE FECHA TRANS -->	<div id="bloqueFechaTransferencia" style="display:none">
								<div class="row">	
									<div class="span3">
										<label class="control-label" for="valorDto.fechaTransferencia"><span class="rojo">* </span><spring:message code="valor.fechaTransferencia"/></label>
										<div class="controls">
											<input id="inputFechaTransferencia"  data-date-format="dd/mm/yyyy" data-date-language='es' name="valorDto.fechaTransferencia" type="text"
												 class="input" maxlength="10">
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorFechaTransferencia" class="error"></span>
									</div>
								</div>
							</div>
									
							<!-- BLOQUE BANCO ORIGEN -->
							<div id="bloqueBancoOrigen" style="display:none">
								<div class="row">	
									<div class="span3">
										<label class="control-label" for="codigoBanco">
											<span class="rojo">* </span>
											<spring:message	code="valor.codBancoOrigen" />
										</label>
										<div class="controls">
											<select id="codigoBanco" name="codigoBanco" class="input">
												<c:if test="${comboBancoOrigen}">
													<option value="">
														<spring:message code="combo.seleccionar" />
													</option>
												</c:if>
												<c:forEach items="${bancoOrigenes}" var="ori">
													<option value="${ori.idBanco}" idAgrupador="${ori.idAgrupador}">${ori.idBanco}
														- ${ori. descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
 									<div class="span3" id="divDescripcionBanco">
										<label class="control-label" for="valorDto.idBancoOrigen">
											<span class="rojo">* </span>
											<spring:message code="valor.descripcionBancoOrigen"/></label>
										<div class="controls">
											<select id ="descripcionBanco" name="valorDto.idBancoOrigen" class="input">
												<c:if test="${comboBancoOrigen}">
													<option value="">
														<spring:message code="combo.seleccionar"/>
													</option>
												</c:if>
												<c:forEach items="${bancoOrigenesDescripcion}" var="ori">
													<option value="${ori.idValue}">${ori.descripcion}</option>
												</c:forEach> 
											</select>
										</div>
									</div>
									
									<div id="bloqueNumeroCheque"  style="display:none">
										<div class="span3">
											<label class="control-label" for="valorDto.numeroCheque"><span class="rojo">* </span><spring:message code="valor.nroCheque"/></label>
											<div class="controls">
												<input id="inputNumeroCheque" name="valorDto.numeroCheque" type="text"  
												 class="input" maxlength="8">
											</div>
										</div>
									</div>
									<div id="bloqueNroreferencia"  style="display:none">
										<div class="span3">
											<label class="control-label" for="valorDto.numeroReferencia"><span class="rojo">* </span><spring:message code="valor.nroReferencia"/></label>
											<div class="controls">
												<input id="inputNumeroReferencia" name="valorDto.numeroReferencia" type="text"  
												 class="input" maxlength="16" >
											</div>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
									</div>
									<div class="span3">
										<span id="errorIdBancoOrigen" class="error"></span>
									</div>
									<div class="span3"  id="bloqueNumeroChequeError">
										<span id="errorNumeroCheque" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorNumeroReferencia" class="error"></span>
									</div>
								</div>
							</div>
<!-- 							<div class="span12"> -->
								<div class ="row">
		<!-- BLOQUE FECHA VENCI -->	<div id="bloqueFechaVencimiento" class="span3"  style="display:none">
										<div class="row">	
											<div class="span3">
												<label class="control-label" for="valorDto.fechaVencimiento"><span class="rojo">* </span><spring:message code="valor.fechaVencimiento"/></label>
												<div class="controls">
													<input id="inputFechaVencimiento"  data-date-format="dd/mm/yyyy" data-date-language='es' name="valorDto.fechaVencimiento" type="text"
														 class="input" maxlength="10" >
												</div>
											</div>
										</div>
										<div class="row rowError" >
											<div class="span3">
												<span id="errorFechaVencimiento" class="error"></span>
											</div>
										</div>
									</div>
		<!--BLOQUE fecha emision  -->						
									<div id="bloqueFechaEmisionCheques" class="span3" style="display:none">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="inputFechaEmisionCheque">
												<span class="rojo" id="rojoinputFechaEmisionCheque" >* </span>
												<spring:message code="valor.fechaEmision.cheque"/></label>
												<div class="controls">
													<input id="inputFechaEmisionCheque" name="inputFechaEmisionCheque" type="text"  
														class="span3" maxlength="10" data-date-format="dd/mm/yyyy" data-date-language='es'>
												</div>
											</div>
										</div>
										<div class="row rowError" >
											<div class="span3">
												<span id="errorinputFechaEmisionCheque" class="error"></span>
											</div>
										</div>
									</div>
								</div>
<!-- 							</div> -->
<!-- BLOQUE CUIT -->		<div id="bloqueCuit"  style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="valorDto.cuit"><spring:message code="valor.cuit"/></label>
										<div class="controls">
											<input id="inputCuit" name="valorDto.cuit" type="text"  
											 class="input" maxlength="16" >
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorCuit" class="error"></span>
									</div>
								</div>
							</div>
							
<!-- BLOQUE TIPO IMPUEST --><div id="bloqueTipoImpuesto" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="valorDto.idTipoImpuesto"><span class="rojo">* </span><spring:message code="valor.tipoImpuesto"/></label>
										<div class="controls">
											<select id ="selectTipoImpuesto" name="valorDto.idTipoImpuesto" class="input" onchange="mostrarIBB(this)">
												<c:if test="${valoresDto.valorDto.comboTipoImpuesto}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoRetencionImpuesto}" var="seg">
													<option value="${seg.idTipoRetencionImpuesto}">${seg.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.nroConstancia"><span class="rojo">* </span><spring:message code="valor.nroConstancia"/></label>
										<div class="controls">
											<input id="inputNroConstancia" name="valorDto.nroConstancia" type="text" 
												class="input" maxlength="16" >
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.fechaEmision"><span class="rojo">* </span><spring:message code="valor.fechaEmision"/></label>
										<div class="controls">
											<input id="inputFechaEmision"  data-date-format="dd/mm/yyyy" data-date-language='es' name="valorDto.fechaEmision" type="text"
												 class="input" maxlength="10" >
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorIdTipoImpuesto" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorNroConstancia" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorFechaEmision" class="error"></span>
									</div>
								</div>
							</div>
							
<!-- BLOQUE RECIBO -->		<div id="bloqueRecibo" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="valorDto.reciboPreImpreso"><spring:message code="valor.recibo"/></label>
										<div class="controls">
											<input id="inputReciboPreImpreso" name="valorDto.reciboPreImpreso" type="text"  
												class="input" maxlength="13">
										</div>
									</div>
									<div class="span3">	
										<label class="control-label" for="valorDto.fechaIngresoRecibo">
											<span class="rojo" id="rojofechaIngresoRecibo" style="display:none">* </span>
											<spring:message code="valor.fechaIngresoRecibo"/></label>
										<div class="controls">
											<input name="valorDto.fechaIngresoRecibo" type="text" class="span3" id="inputFechaIngresoRecibo" maxlength="10"
												data-date-format="dd/mm/yyyy" data-date-language='es'>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorReciboPreImpreso" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorFechaIngresoRecibo" class="error"></span>
									</div>
								</div>
							</div>
<!--BLOQUE fecha notificación para valores -->
							<div id="bloqueFechaNotificacionValor" style="display:none">
								<div class="row">
									<div class="span3">	
										<label class="control-label" for="inputFechaNotificacionValor">
											<spring:message code="valor.fecha.notificacion.disponibilidad.retiro.valor"/></label>
										<div class="controls">
											<input name="inputFechaNotificacionValor" type="text" class="span3" id="inputFechaNotificacionValor" maxlength="10"
												data-date-format="dd/mm/yyyy" data-date-language='es'>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorinputFechaNotificacionValor" class="error"></span>
									</div>
								</div>
							</div>
							
							
<!-- BLOQUE BDIBB -->		<div id="bloqueBDIBB" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="valorDto.cuitIbb"><span class="rojo">* </span><spring:message code="valor.cuit"/></label>
										<div class="controls">
											<input id="cuitIbb" name="valorDto.cuitIbb" type="text" 
											 class="input" maxlength="16">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.idProvincia"><span class="rojo">* </span><spring:message code="valor.provincias"/></label>
										<div class="controls">
											<select id ="selectProvincia" name="valorDto.idProvincia" class="input">
												<c:if test="${valoresDto.valorDto.comboProvincia}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${provincia}" var="acu">
													<option value="${acu.provincia}" tope="${acu.topeMesesFechaEmision}">${acu.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorCuitIbb" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorIdProvincia" class="error"></span>
									</div>
								</div>
								<p><strong><spring:message code="valor.facturaRelacionada"/></strong></p>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="valorDto.idTipoComprobante"><spring:message code="valor.tipoComprobante"/></label>
										<div class="controls">
											<select id ="selectTipoComprobante" name="valorDto.idTipoComprobante" class="input" onchange="mostrarTipoComprobante(this)">
												<c:if test="${valoresDto.valorDto.comboTipoComprobante}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoComprobante}" var="acu">
													<option value="${acu.idTipoComprobante}">${acu.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.idLetraComprobante"><div id="checkLetraComprobante" style="display:none"><span class="rojo">* </span></div><spring:message code="valor.letraComprobante"/></label>
										<div class="controls">
											<select id ="selectLetraComprobante" name="valorDto.idLetraComprobante" class="input">
												<c:if test="${valoresDto.valorDto.comboLetraComprobante}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoLetraComprobante}" var="acu">
													<option value="${acu.idTipoLetraComprobante}">${acu.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="valorDto.numeroLegalComprobante"><div id="checkNumeroLegalComprobante" style="display:none"><span class="rojo">* </span></div><spring:message code="valor.nroLegal"/></label>
										<div class="controls">
											<input id="inputNumeroLegalComprobante" name="valorDto.numeroLegalComprobante" type="text" 
												class="input" maxlength="16" >
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorTipoComprobante" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorLetraComprobante" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorNumeroLegalComprobante" class="error"></span>
									</div>
								</div>	
								<p><strong>&nbsp;</strong></p>
							</div>
						
					<!-- OBSERVACIONES Y BOTONES -->
					<div id="bloqueObservaciones">
						<div class="row" style="margin-top: 20px; margin-bottom: 10px">
							<div class="span9">
								<label class="control-label" for="valorDto.observaciones"><spring:message code="boleta.observaciones"/></label>
								<textarea class="field span9" id="observaciones" name="valorDto.observaciones"  maxlength="250"></textarea>
							</div>
						</div>
						<div class="row rowError" >
							<div class="span9">
								<span id="errorObservaciones" class="error"></span>
							</div>
						</div>
						<div id=bloqueImprimirYEnviarMail style="display:none">
							<div class="row" style="margin-top: 0px; margin-bottom: 0px">
								<div class="span6">
									<label class="checkbox inline">
									<input id="imprimirBoleta" name="valorDto.imprimirBoleta" type="checkbox"><spring:message code="valor.imprimirBoleta"/></label>
								</div>
							</div>
							<div class="row" style="margin-top: 0px; margin-bottom: 10px">
								<div class="span6">
									<label class="checkbox inline">
									<input id="enviarBoletaMail" name="valorDto.enviarBoletaMail" type="checkbox"><spring:message code="valor.check.mail"/></label>
								</div>
							</div>
						</div>
					</div>
					</form:form>			<!-- end form 			 -->
					<!--COMPROBANTES-->
					<div id="bloqueComprobantes" style="display:block;">
											<div class="row">
												<div class="span9">
												<p><strong><span class="rojo">* </span><spring:message code="valor.comprobantes"/></strong></p>
													<form id="adjComprobanteForm" method="post" action="valor/adjuntarComprobante" enctype="multipart/form-data">
														<label class="control-label" for="comprobanteArch">
															<spring:message code="valor.adjuntarComprobante"/>
														</label>
														<div class="fileupload fileupload-new" data-provides="fileupload">
															<input type="hidden">
															<div class="input-append">
																<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
																	<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
																</div>
																<span class="btn btn-file btn-primary btn-small" style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
																	<span class="fileupload-new">
																		<spring:message code="valor.botonSeleccionarArchivo"/>
																	</span> 
																	<span class="fileupload-exists">
																		<spring:message code="valor.botonCambiar"/>
																	</span>
																	<input name="comprobanteArch" id="comprobanteArch" type="file" />
																</span>
																<a href="#" class="btn fileupload-exists btn-primary btn-small" style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload">
																	<spring:message code="valor.botonEliminar"/>
																</a>
															</div>
														</div>
													</form>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorArchComprobante" class="error"></span>
												</div>
											</div>
											<div class="row" style="margin-top: 0px; margin-bottom: 5px">
												<div class="span9">
													<label class="control-label" for="descripcionComprobante">
														<spring:message code="valor.descripcionComprobante"/>
													</label>
													<textarea class="field span9" id="descripcionComprobante" maxlength="150" name="descripcionComprobante" /></textarea>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9">
													<span id="errorDescripcionComp" class="error" ></span>
												</div>
											</div>
										</div>
											<div class="row" style="margin-top: 0px; margin-bottom: 5px">
												<div class="span9" align="right">
													<button type="button" class="btn btn-primary btn-small" id="btnAdjComprobante">
														<i class="icon-white icon-upload"></i><spring:message code="valor.botonAdjuntar" />
													</button>
												</div>
											</div>
										<div class="row" style="margin-top: 20px">
											<div class="span9">
												<table id="comprobantes" class="tablaResultado" width="100%">
													<thead>
														<tr>
															<th></th>
															<th><spring:message code="valor.nombreArchivo"/></th>
															<th><spring:message code="valor.descripcion"/></th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>

						<br>
							<div>
								<label for="mantenerComprobantesAdjuntosVal">
										<input id="mantenerComprobantesAdjuntosVal" type="checkbox" onclick="checkComprobantes();"
											<c:if test="${valoresDto.mantenerComprobantesAdjuntos}">checked</c:if>/>&nbsp;<spring:message
 													code="valor.mantenerComprobantesAdjuntos" /></label>
								<div align="right" style="margin-bottom: 15px">
									<button type="button" class="btn btn-primary btn-small" id="btnAgregarValor" name="agregarValor" >
									<i class="icon-white icon-chevron-down"></i><spring:message code="valor.botonAgregarAvisoDePago"/></button>
									<div class="alert alert-error" id='divAlertError' align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px; display: none;"">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br/>
										<span id="alertError"></span>
									</div>
								</div>
							</div>
							<p><strong>Avisos de pago configurados</strong></p>
							<div class="row">		
								<div class="span9">
									<table id="listaValores" class="tablaResultado">
										<thead>
											<tr>
												<th><spring:message code="valor.tipo.avisos.pago"/></th>
												<th><spring:message code="valor.acuerdo"/></th>
												<th><spring:message code="valor.bancoDeposito"/></th>
												<th><spring:message code="valor.nroCuenta"/></th>
												<th><spring:message code="valor.importe"/></th>
												<th class="date"><spring:message code="valor.fechaDeposito"/></th>
												<th class=><spring:message code="valor.fechaEmision"/></th>
												<th class="date"><spring:message code="valor.fechaTransferencia"/></th>
												<th><spring:message code="valor.bancoOrigen"/></th>
												<th><spring:message code="valor.reciboConstancia"/></th>
												<th class="date"><spring:message code="valor.fechaIngresoRecibo"/></th>
												<th><spring:message code="valor.motivo"/></th>
												<th><spring:message code="valor.nroCheque"/></th>
												<th class="date"><spring:message code="valor.fechaVencimiento"/></th>
												<th><spring:message code="valor.nroReferencia"/></th>
												<th>&nbsp;</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
								<div align="right">
									<button type="button" class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar"><i class="icon-white icon-ok"></i>  Aceptar</button>
									<input id="listaVacia" type="hidden" value="${valoresDto.listaValoresDto.size()}" />
									
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px; display: none;" id="divAlertErrorFoot">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br/>
										<span id="alertErrorFoot"></span>
									</div>
								</div>
							</div>
							
						</div>						<!-- end pagos_anticipos -->
					</div>							<!-- end payments 		 -->
				</div>								<!-- end inside 		 -->
			</div>									<!-- end content  		 -->
		</div>										<!-- end wrap 		 	 -->
	</div>											<!-- end main	  		 -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

</div>												<!-- end container		 -->
</body>
</html>