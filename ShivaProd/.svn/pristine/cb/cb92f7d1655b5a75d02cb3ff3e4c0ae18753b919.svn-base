 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/conciliacion/conciliacion-consultar-registros-avc-sin-conciliar-detalle.js"></script>
	<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
	<script src="${pageContext.request.contextPath}/js/valor/validaciones.valor.js"></script>
	<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	
<title><spring:message code="conciliacion.titulo.general"/></title>

	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
   
    <meta name=<spring:message code="conciliacion.creacion.titulo"/> content="Modelo">
 	
<script>
var crearValor = '<%=Constantes.CREAR_VALOR_REGISTRO_AVC%>'; 
var TipoValorEnum = ${TipoValorEnum};
var leyendaComboSeleccionar = '${leyendaComboSeleccionar}';
	function mostrarTipoValor(tipoValor) {
		var tipoValorSeleccionado = tipoValor.value;
		switch (tipoValorSeleccionado) {
			case "5": // Cheque
				bloqueBancoOrigen.style.display = 'block';
				bloqueNroCheque.style.display = 'block';
				bloqueFechaDeposito.style.display = 'block';
				<c:if test="${empty valorDto.valorPorReversion}">
					bloqueRecibo.style.display = 'block';
				</c:if>
				bloqueFechaEmision.style.display = 'block';
				bloqueFechaNotificacion.style.display = 'block';
			break;
			case "6": // Cheque PD
				bloqueBancoOrigen.style.display = 'block';
				bloqueNroCheque.style.display = 'block';
				bloqueFechaDeposito.style.display = 'block';
				bloqueFechaVencimiento.style.display = 'block';
				<c:if test="${empty valorDto.valorPorReversion}">
					bloqueRecibo.style.display = 'block';
				</c:if>
				bloqueFechaEmision.style.display = 'block';
				bloqueFechaNotificacion.style.display = 'block';
				break;
			case "7": //Efectivo
				bloqueNumeroBoleta.style.display = 'block';
				bloqueFechaDeposito.style.display = 'block';
				<c:if test="${empty valorDto.valorPorReversion}">
					bloqueRecibo.style.display = 'block';
				</c:if>
				bloqueFechaNotificacion.style.display = 'block';
				break;
			case "8": //Transferencia
				<c:if test="${not empty valorDto.valorPorReversion}">
					bloqueBancoOrigen.style.display = 'block';
				</c:if>
				bloqueTransferencia.style.display = 'block';
				break;
			case "9": //Interdeposito
				bloqueInterdeposito.style.display = 'block';
				bloqueFechaNotificacion.style.display = 'block';
				break;
			default:
				break;
		}
	};
	
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
							<c:when test="${empty valorDto.valorPorReversion}">
								<div class="title"><spring:message code="conciliacion.creacion.titulo.pagina"/></div>
							</c:when>
							<c:otherwise>
								<div class="title"><spring:message code="conciliacion.creacion.titulo.pagina.valorPorReversion"/></div>
							</c:otherwise>
						</c:choose>
						<div class="pagos_anticipos">
							<p><strong><spring:message code="conciliacion.datosGenerales"/></strong></p>
							
							<form:form id="formAlta" commandName="valorDto" enctype="multipart/form-data" action="${pageContext.request.contextPath}/conciliacion-aceptar-alta-valor">		
								<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>
								
								<form:hidden path="operation" id="operation"/>
								<form:hidden path="idValor" id="idValor"/>
								<form:hidden path="idRegistroAvc" id="idRegistroAvc"/>
								<form:hidden path="idEmpresa" id="idEmpresa"/>
								<form:hidden path="segmento" id="segmento"/>
								<form:hidden path="mailAnalista" id="mailAnalista"/>
								<form:hidden path="idAnalista" id="idAnalista"/>
								<form:hidden path="copropietario" id="copropietario"/>
								<form:hidden path="idTipoValor" id="idTipoValor"/>
								<form:hidden path="motivo" id="motivo"/>
								<form:hidden path="idOrigen" id="idOrigen"/>
								<form:hidden path="idBancoOrigen" id="idBancoOrigen"/>
								<form:hidden path="adjuntando"/>
								<form:hidden path="fechaAlta" id="fechaAlta"/>	
								<form:hidden path="fechaAltaValor" id="fechaAltaValor"/>
								<form:hidden path="valorPorReversion" id="valorPorReversion"/>
								<form:hidden path="timeStamp" id="timeStamp"/>
								<form:hidden path="pantallaDestino" id="pantallaDestino"/>
								<form:hidden path="pantallaRegreso" id="pantallaRegreso"/>
								<form:input path="cliente.cuit" id="cuit" type="hidden"/>
								<form:input path="cliente.idClienteLegado" id="idClienteLegado" type="hidden"/>
								<form:input path="cliente.empresasAsociadas" id="empresasAsociadas" type="hidden"/>
								<form:input path="cliente.razonSocial" id="razonSocial" type="hidden"/>
								<form:input path="cliente.origen" id="origen" type="hidden"/>
								<form:input path="cliente.descripcionAgenciaNegocio" id="descripcionAgenciaNegocio" type="hidden"/>
								<form:input path="cliente.codigoHolding" id="codigoHolding" type="hidden"/>
								<form:input path="cliente.segmentoAgencia" id="segmentoAgencia" type="hidden"/>
								<form:input path="cliente.descripcionHolding" id="descripcionHolding" type="hidden"/>
								<form:input path="cliente.agenciaNegocio" id="agenciaNegocio" type="hidden"/>
								<form:input path="codCliente" id="codCliente" type="hidden"/>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="selectEmpresa"><span class="rojo">* </span><spring:message code="boleta.empresa"/></label>
										<div class="controls">
											<select id ="selectEmpresa" name="valorDto.idEmpresa" class="input">
												<c:if test="${valoresDto.valorDto.comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${empresas}" var="emp">
												<c:choose>
													<c:when test="${emp.idEmpresa eq valoresDto.valorDto.idEmpresa}">
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
										<label class="control-label" for="selectSegmento"><span class="rojo">* </span><spring:message code="boleta.segmento"/></label>
										<div id="ctrlSegmento" class="controls">
											<select id ="selectSegmento" name="idSegmento" class="input">
												<c:if test="${valorDto.comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq valorDto.idSegmento}">
															<option value="${seg.idSegmento}" selected>${seg.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${seg.idSegmento}">${seg.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
<!-- 										<div class="span3"> -->
<%-- 											<label class="control-label" for="valorDto.idSegmento"><span class="rojo">* </span> <spring:message code="valor.segmento"/></label> --%>
<!-- 											<div class="controls"> -->
<!-- 												<select id="selectSegmento" name="valorDto.idSegmento" class="input"> -->
<%-- 													<c:if test="${comboSegmento}"> --%>
<%-- 														<option value=""><spring:message code="combo.seleccionar"/></option> --%>
<%-- 													</c:if> --%>
<%-- 													<c:forEach items="${segmentos}" var="seg"> --%>
<%-- 														<option value="${seg.idSegmento}">${seg.descripcion}</option> --%>
<%-- 													</c:forEach> --%>
<!-- 												</select> -->
<!-- 											</div> -->
<!-- 										</div> -->
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="empresa" cssClass="error" />
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
														maxlength="13" class="input" style="margin: 0 auto;"/>
												</div>
											</div>
											<div class="span3">
												<div>
													<button type="button" class="btn btn-primary btn-small" id="btnBuscarClientes">
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
															<th><spring:message	code="conf.cobro.tabla.clientes.cuit" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.cliente" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.empresasAsociadas" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.razonSocial" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.origen" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia.segmento" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.holding" /></th>
															<th><spring:message code="conf.cobro.tabla.clientes.agencia" /></th>
															<th></th>
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
									
<!-- 								<div class="row"> -->
<!-- 									<div class="span3"> -->
<!-- 										<label class="control-label" for="codCliente"> -->
<%-- 											<span class="rojo">* </span><spring:message code="boleta.codClienteLegado"/> --%>
<!-- 										</label> -->
<!-- 										<div class="controls"> -->
<!-- 											<input id="codCliente" name="codCliente" type="text" class="input" maxlength="11" -->
<%-- 												value="${valorDto.codCliente}"	style="margin: 0 auto;" /> --%>
<!-- 										</div> -->
<!-- 									</div> -->
							
<!-- 									<div class="span6"> -->
<%-- 										<label class="control-label" for="razonSocial"><spring:message code="boleta.razonSocialClienteLegado"/></label> --%>
<!-- 										<div class="controls"> -->
<%-- 											<input id="razonSocial" name="razonSocial" type="text" value="${valorDto.razonSocialClientePerfil}"  --%>
<!-- 												   class="input span6" readonly /> -->
<!-- 										</div>							 -->
<!-- 									</div> -->
<!-- 								</div> -->
								
<!-- 							   <div class="row rowError" > -->
<!-- 									<div class="span3"> -->
<%-- 										<form:errors path="codCliente" cssClass="error"/>  --%>
<!-- 										<span id="wsConsultaClienteSiebel" class="error" style="display: none;"></span> -->
<!-- 									</div> -->
<!-- 								</div> -->


<!-- 								<div class="row"> -->
<!-- 									<div class="span3"> -->
<%-- 										<label class="control-label" for="codClienteAgrupador"><spring:message code="boleta.codClienteAgrupador"/> --%>
<!-- 										</label> -->
<!-- 										<div class="controls"> -->
<!-- 											<input id="codClienteAgrupador" name="codClienteAgrupador" type="text" class="input" -->
<%-- 												readonly value="${valorDto.codClienteAgrupador}" style="margin: 0 auto;" /> --%>
<!-- 										</div> -->
<!-- 									</div> -->

<!-- 									<div class="span6"> -->
<%-- 										<label class="control-label" for="razonSocialClienteAgrupador"><spring:message code="boleta.razonSocialClienteAgrupador"/></label> --%>
<!-- 										<div class="controls"> -->
<!-- 											<input id="razonSocialClienteAgrupador" name="razonSocialClienteAgrupador" type="text" class="input span6"  -->
<%-- 												   readonly value="${valorDto.razonSocialClienteAgrupador}"> --%>
<!-- 										</div>							 -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="span3"> -->
<%-- 										<label class="control-label" for="numeroHolding"><spring:message code="boleta.numeroHolding"/></label> --%>
<!-- 										<div class="controls"> -->
<!-- 											<input id="numeroHolding" name="numeroHolding" type="text" class="input" -->
<%-- 												readonly value="${valorDto.numeroHolding}" style="margin: 0 auto;" /> --%>
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="span6"> -->
<%-- 										<label class="control-label" for="nombreHolding"><spring:message code="boleta.nombreHolding"/></label> --%>
<!-- 										<div class="controls"> -->
<!-- 											<input id="nombreHolding" name="nombreHolding" type="text" class="input span6"  -->
<%-- 												   readonly value="${valorDto.nombreHolding}"> --%>
<!-- 										</div>							 -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div class="row">
									<div class="span3">
										<label class="control-label" for="nombreAnalista"><spring:message code="boleta.analista"/></label>
										<div class="controls" style="white-space: nowrap;">
											<input class="input" id="nombreAnalista" name="nombreAnalista" type="text" value="${valorDto.nombreAnalista}" readonly>
										</div>
									</div>
										<div class="span3">
											<label class="control-label" for="selectCopropietario"><spring:message code="boleta.copropietario"/></label>
												<div class="controls">
														<select id="selectCopropietario" name="idCopropietario" class="input">
															<c:if test="${valorDto.comboCopropietario}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
															<c:forEach items="${copropietarios}" var="cop">
																<c:choose>
																	<c:when test="${cop.tuid eq valorDto.idCopropietario}">
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
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="nombreAnalista" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="copropietario" cssClass="error" />
									</div>
								</div>
								
								
								<p>	<strong><spring:message code="valor.valores"/></strong> </p>
								
								<div class="row">
									<div class="span3"> 
 										<label class="control-label" for="tipoValor"><spring:message code="valor.tipoValor"/></label> 
										<div class="controls"> 
											<input id="tipoValor" name="tipoValor" type="text" class="input" value="${valorDto.tipoValor}" readonly> 
										</div> 
									</div>
									<div class="span3">
										<label class="control-label" for="importe"><spring:message code="valor.importe"/></label>
										<div class="controls">
											<input id="importe" name="importe" type="text" class="input" readonly 
												value="${valorDto.importe}">
										</div>
									</div>
									<c:if test="${not empty valorDto.valorPorReversion}">
										<div class="span3"> 
	 										<label class="control-label" for="saldoDisponible"><spring:message code="valor.saldoDisponible"/></label> 
											<div class="controls"> 
												<input id="saldoDisponible" name="saldoDisponible" type="text" class="input" value="${valorDto.saldoDisponible}" readonly/> 
											</div> 
										</div>
									</c:if>
								</div>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idMotivo"><spring:message code="valor.motivo"/></label>
										<div id="ctrlMotivo" class="controls">
											<select id ="selectMotivo" name="idMotivo" class="input">
												<option value=""><spring:message code="combo.seleccionar"/></option>
												<c:forEach items="${motivos}" var="mot">
													<c:choose>
														<c:when test="${mot.idMotivo eq valorDto.idMotivo}">
															<option value="${mot.idMotivo}" selected>${mot.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${mot.idMotivo}">${mot.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="origen"><spring:message code="valor.origen"/></label>
										<div class="controls">
											<input id="origen" name="origen" type="text" class="input" readonly value="${valorDto.origen}"/>
										</div>
									</div>
									<div class="span3">
										<c:choose>
											<c:when test="${empty valorDto.valorPorReversion}">	
												<label class="control-label" for="estadoRegistroAvc"><spring:message code="conciliacion.estadoRegistroAVC"/></label>
											</c:when>
											<c:otherwise>
												<label class="control-label" for="estadoRegistroAvc"><spring:message code="conciliacion.estadoValorPorReversion"/></label>
											</c:otherwise>
										</c:choose>
										<div class="controls">												
											<input id="estadoRegistroAvc" name="estadoRegistroAvc" type="text" value="${valorDto.estadoRegistroAvc}" class="input" readonly/>
										</div> 
									</div>
								</div>
								<div class="row">
									<div class="span3">
										<c:choose>
											<c:when test="${not empty valorDto.acuerdo}">
												<label class="control-label" for="acuerdo"><spring:message code="valor.acuerdo"/></label>
												<div class="controls">	
													<input id="acuerdo" name="acuerdo" type="text" class="input" readonly 
														value="${valorDto.acuerdo}"/>
													<input id="idAcuerdo" name="idAcuerdo" type="hidden" value="${valorDto.idAcuerdo}"/>
												</div>
											</c:when>
											<c:otherwise>
												<label class="control-label" for="acuerdo"><span class="rojo">* </span><spring:message code="valor.acuerdo"/></label>
												<div class="controls">	
													<select id ="selectAcuerdo" name="idAcuerdo" class="input">
														<c:if test="${valorDto.comboAcuerdo}"><option value="" selected><spring:message code="combo.seleccionar"/></option></c:if>
														<c:forEach items="${acuerdos}" var="acu">
															<c:choose>
																<c:when test="${valorDto.idAcuerdo eq acu.idAcuerdo.toString()}">
																	<option value="${acu.idAcuerdo}" selected>${acu.descripcion}</option>
																</c:when>
																<c:otherwise>
																	<option value="${acu.idAcuerdo}">${acu.descripcion}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach> 
													</select>
												</div>
											</c:otherwise>
										</c:choose>
										<div class="row rowError" >
											<div class="span3">
												<form:errors path="acuerdo" cssClass="error" />
											</div>
											<div class="span3">
												<span id="errorAcuerdo" class="error"></span>
											</div>
										</div>
									</div>
									
									<div class="span3">
										<c:choose>
											<c:when test="${not empty valorDto.acuerdo}">
												<label class="control-label" for="bancoDeposito"><spring:message code="valor.bancoDeposito"/></label>
												<div class="controls">
													<input id="bancoDeposito" name="bancoDeposito" type="text" class="input" readonly value="${valorDto.bancoDeposito}"/>
													<input id="idBancoDeposito" name="idBancoDeposito" type="hidden" value="${valorDto.bancoDeposito}"/>
												</div>
											</c:when>
											<c:otherwise>
												<label class="control-label" for="selectBancoDeposito"><span class="rojo">* </span><spring:message code="valor.bancoDeposito"/></label>
												<div class="controls">
													<select id="selectBancoDeposito" name="idBancoDeposito" class="input">
														<c:if test="${valorDto.comboBanco}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
														<c:forEach items="${bancos}" var="banco">
															<c:choose>
																<c:when test="${banco.idBanco eq valorDto.idBancoDeposito}">
																	<option value="${banco.idBanco}" selected>${banco.descripcion}</option>
																</c:when>
																<c:otherwise>
																	<option value="${banco.idBanco}">${banco.descripcion}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
											</c:otherwise>
										</c:choose>
										<div class="row rowError" >
											<div class="span3">
												<form:errors path="bancoDeposito" cssClass="error" />
											</div>
											<div class="span3">
												<span id="errorBancoDeposito" class="error"></span>
											</div>
										</div>
									</div>
									
									<div class="span3">
										<c:choose>
											<c:when test="${not empty valorDto.acuerdo}">
												<label class="control-label" for="numeroCuenta"><spring:message code="valor.nroCuenta"/></label>
												<div class="controls">
													<input id="numeroCuenta" name="numeroCuenta" type="text" class="input" readonly value="${valorDto.numeroCuenta}">
													<input id="idNroCuenta" name="idNroCuenta" type="hidden" value="${valorDto.numeroCuenta}"/>
												</div>
											</c:when>
											<c:otherwise>
												<label class="control-label" for="selectNumeroCuenta"><span class="rojo">* </span><spring:message code="valor.nroCuenta"/></label>
												<div class="controls">
													<select id ="selectNumeroCuenta" name="idNroCuenta" class="input">
														<c:if test="${valorDto.comboCuenta}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
														<c:forEach items="${cuentas}" var="cuen">
															<c:choose>
																<c:when test="${cuen.idBancoCuenta eq valorDto.idNroCuenta}">
																	<option value="${cuen.idBancoCuenta}" selected>${cuen.cuentaBancaria}</option>
																</c:when>
																<c:otherwise>
																	<option value="${cuen.idBancoCuenta}">${cuen.cuentaBancaria}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
												</div>
											</c:otherwise>
										</c:choose>
										<div class="row rowError" >
											<div class="span3">
												<form:errors path="numeroCuenta" cssClass="error" />
											</div>
											<div class="span3">
												<span id="errorNumeroCuenta" class="error"></span>
											</div>
										</div>
									</div>									
								</div>
								
								<!-- DEPOSITO CHEQUE y CHEQUE DIFERIDO-->
								<div class="row">
									<div class="span3" id="bloqueNumeroBoleta" style="display:none">
										<label class="control-label" for="numeroBoleta"><spring:message code="valor.nroBoleta"/></label>
										<div class="controls">
											<input id="numeroBoleta" name="numeroBoleta" type="text" class="input" 
												value="${valorDto.numeroBoleta}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueFechaDeposito" style="display:none">
										<label class="control-label" for="fechaDeposito"><spring:message code="valor.fechaDeposito"/></label>
										<div class="controls">
											<input type="text" id="fechaDeposito" name="fechaDeposito" data-date-format="dd/mm/yyyy"  data-date-language='es' class="input" 
												value="${valorDto.fechaDeposito}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueNroCheque" style="display:none">
										<label class="control-label" for="numeroCheque"><spring:message code="valor.nroCheque"/></label>
										<div class="controls">
											<input id="numeroCheque" name="numeroCheque" type="text" class="input" 
												value="${valorDto.numeroCheque}" readonly>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="span3" id="bloqueBancoOrigen" style="display:none">
										<label class="control-label" for="bancoOrigen"><spring:message code="valor.bancoOrigen"/></label>
										<div class="controls">
											<input id="bancoOrigen" name="bancoOrigen" type="text" class="input" readonly 
												value="${valorDto.bancoOrigen}">
										</div>
									</div>
									<div class="span3" id="bloqueFechaVencimiento" style="display:none">
										<label class="control-label" for="fechaVencimiento"><spring:message code="valor.fechaVencimiento"/></label>
										<div class="controls">
											<input type="text" id="fechaVencimiento" name="fechaVencimiento" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" 
												value="${valorDto.fechaVencimiento}" readonly>
										</div>
									</div>
								</div>
						<!-- BLOQUE RECIBO -->
						<div id="bloqueRecibo" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="reciboPreImpreso"><spring:message code="valor.recibo"/></label>
										<div class="controls">
											<input id="reciboPreImpreso" name="reciboPreImpreso" type="text"  
												class="input" maxlength="13" value="${valorDto.reciboPreImpreso}">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaIngresoRecibo"><spring:message code="valor.fechaIngresoRecibo"/></label>
										<div class="controls">
											<input id="fechaIngresoRecibo" name="fechaIngresoRecibo" type="text"
											data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaIngresoRecibo}">
										</div>
									</div>
								</div>
								
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="reciboPreImpreso" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaIngresoRecibo" cssClass="error" />
									</div>
								</div>
							</div>
							<!-- FECHA EMISION -->							<div id="bloqueFechaEmision" style="display:none">

								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaEmision">
										<span class="rojo">* </span>
										<spring:message code="valor.fechaEmision.cheque"/></label>
										<div class="controls">
											<input id="fechaEmision" name="fechaEmision" type="text"
											data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaEmision}">
										</div>
									</div>
								</div>							</div>

								<div class="row rowError" >
									<div class="span3">
										<span id="errorinputFechaEmisionCheque" class="error"></span>
									</div>
								</div>
									
						<!-- FECHA NOTIFICACION -->
								
							<div id="bloqueFechaNotificacion" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaNotificacionDisponibilidadRetiroValor"><spring:message code="valor.fecha.notificacion.disponibilidad.retiro.valor"/></label>
										<div class="controls">
											<input id="fechaNotificacionDisponibilidadRetiroValor" name="fechaNotificacionDisponibilidadRetiroValor" type="text"
											data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaNotificacionDisponibilidadRetiroValor}">
										</div>
									</div>
								</div>
								
								<div class="row rowError" >
									<div class="span3">
										<span id="errorinputFechaNotificacionValor" class="error"></span>
									</div>
								</div>
							</div>
							
									<!-- TRANSFERENCIA -->
									<div class="row">
										<div id="bloqueTransferencia" style="display:none">
											<div class="span3">
												<label class="control-label" for="numeroReferencia"><spring:message code="valor.nroReferencia"/></label>
												<div class="controls">
													<input id="numeroReferencia" name="numeroReferencia" type="text" class="input" value="${valorDto.numeroReferencia}" readonly>
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="fechaTransferencia"><spring:message code="valor.fechaTransferencia"/></label>
												<div class="controls">
													<input type="text" id="fechaTransferencia" name="fechaTransferencia" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" 
														value="${valorDto.fechaTransferencia}" readonly>
												</div>
											</div>
												<c:if test="${empty valorDto.valorPorReversion}">
													<div class="span3">
														<label class="control-label" for="cuit"><spring:message code="valor.cuit"/></label>
														<div class="controls">
															<input id="cuit" name="cuit" type="text" class="input" value="${valorDto.cuit}" readonly>
														</div>
													</div>
												</c:if>
											</div>
										</div>
								
								<!-- INTERDEPOSITO -->
								<div class="row">
									<div id="bloqueInterdeposito" style="display:none">
										<div class="span3">
											<label class="control-label" for="fechaInterdeposito"><spring:message code="valor.fechaInterdeposito"/></label>
											<div class="controls">
												<input type="text" id="fechaInterdeposito" data-date-format="dd/mm/yyyy" name="fechaInterdeposito" data-date-language='es' class="input" value="${valorDto.fechaInterdeposito}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="numeroInterdepositoCdif"><spring:message code="valor.nroInterdepositoCdif"/></label>
											<div class="controls">
												<input id="nroInterdeposito" name="numeroInterdepositoCdif" type="text" class="input" value="${valorDto.numeroInterdepositoCdif}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="codOrganismo"><spring:message code="valor.codOrganismo"/></label>
											<div class="controls">
												<input id="codigoOrganismo" name="codOrganismo" type="text" class="input" value="${valorDto.codOrganismo}" readonly>
											</div>
										</div>
									</div>
								</div>
								
								<c:if test="${valorDto.tipoValor eq 'Transferencia'}">
								
						
  									
  									<!-- OBSERVACIONES ARCHIVO AVC -->
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacionRegistroAvc"><spring:message code="valor.observacionesArchivoAVC"/></label>
											<textarea class="field span9" id="observacionRegistroAvc" name="observacionRegistroAvc" readonly>${valorDto.observacionRegistroAvc}</textarea>
										</div>
									</div>
								</c:if>
								
							
								<!-- OBSERVACIONES ANTERIORES -->
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observacionesAnteriores"><spring:message code="valor.observacionesAnteriores"/></label>
										<textarea class="field span9" id="observacionesAnteriores" name="observacionesAnteriores" readonly>${valorDto.observaciones}</textarea>
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="nuevaObservacion"><spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="nuevaObservacion" name="nuevaObservacion" maxlength="250">${valorDto.nuevaObservacion}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="observaciones" cssClass="error" />
										<span id="errorObservaciones" class="error"></span>
									</div>
								</div>


								<!-- COMPROBANTES -->
								<div id="bloqueComprobantesAgregar">
										<p>
											<label><c:if test="${empty valorDto.valorPorReversion }"><span class="rojo">* </span></c:if><spring:message code="conciliacion.comprobantes"/></label>
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
														<span class="btn btn-file btn-primary btn-small" style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
															<span class="fileupload-new"><spring:message code="conciliacion.seleccionarArchivo" /></span>
															<span class="fileupload-exists"><spring:message code="conciliacion.cambiar" /></span>
																<form:input type="file" path="fileComprobanteModificacion"/></span>
															<a href="#" class="btn fileupload-exists btn-primary btn-small" style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;"
																data-dismiss="fileupload"><spring:message code="conciliacion.eliminar" /></a>
													</div>
												</div>
												<div class="row rowError" >
													<div class="span3">
														<form:errors path="comprobanteError" cssClass="error" />
													</div>
												</div>
												<div class="row rowError" >
													<div class="span3">
														<form:errors path="comprobantePathVacioModif" cssClass="error" />
													</div>
												</div>
												<div class="row rowError" >
													<div class="span3">
														<form:errors path="errorArchivoVacio" cssClass="error" />
													</div>
												</div>
												
												<div class="row" style="margin-top: 0px; margin-bottom: 5px">
													<div class="span9">
														<label class="control-label" for=""><spring:message
																code="conciliacion.descripcionComprobante" /></label>
														<textarea class="field span9" id="descripcionComprobante" maxlength="150"
															name="descripcionComprobante">${valorDto.descripcionComprobante}</textarea>
													</div>
												</div>
												<div class="row rowError" >
													<div class="span3">
														<form:errors path="descripcionComprobante" cssClass="error" />
													</div>
												</div>
												<div class="row rowError" >
													<div class="span3">
														<form:errors path="comprobanteDescripcionVacioModif" cssClass="error" />
													</div>
												</div>
												<div class="row" align="right"
													style="margin-top: 15px; margin-bottom: 15px; width: 699px;">
													<button type="button" class="btn btn-primary btn-small"
														id="btnAdjuntar" name="btnAdjuntar">
														<i class="icon-white icon-upload"></i>
														<spring:message code="valor.botonAdjuntar" />
													</button>
												</div>
												<br>
											</div>
										</div>
									</div> <!-- fin div Bolque comprobantes agregar -->
									<div id="bloqueComprobantes">
										<div class="row">
											<div class="span9">
												<table
													style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit;"
													class="tablaResultadoHistorialComprobante"
													id="listaComprobantes">
													<thead>
														<tr>
															<th class="tituloTabla"><spring:message code="valor.nombreArchivo"/></th>
															<th class="tituloTabla"><spring:message code="valor.descripcion"/></th>
															<th class="tituloTabla"></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${valorDto.listaComprobantes}" var="compro" varStatus="i" begin="0">
															<tr>
																<td class="registroTabla">${compro.nombreArchivo}</td>
																<td class="registroTabla">${compro.descripcionArchivo}</td>
																<td>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																		<button id="btnVerComprobante" type="button"
																			class="btn btn-xs btn-link"
																			title="Ver documento adjunto"
																			onclick="abrirComprobante(${compro.id});">
																			<i class="icon-download-alt bigger-120"></i>
																		</button>
																	</div>
																	<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																		<c:if test="${(sessionScope.loginUser.idUsuario eq compro.usuarioCreacion)}">
																			<button id="btnEliminarComprobante" type="button"
																				class="btn btn-xs btn-link"
																				title="Eliminar documento"
																				onclick="eliminarComprobante(${compro.id});">
																				<i class="icon-trash bigger-120"></i>
																			</button>
																		</c:if>
																	</div>
																</td>
																<form:input	path="listaComprobantes[${i.index}].nombreArchivo"	id="nombreArchivo${i.index}" type="hidden" />
																<form:input path="listaComprobantes[${i.index}].descripcionArchivo" id="descripcionArchivo${i.index}" type="hidden" />
																<form:input path="listaComprobantes[${i.index}].documento" id="documento${i.index}" type="hidden"/>
																<form:input path="listaComprobantes[${i.index}].id" id="id${i.index}" type="hidden" />
																<form:input path="listaComprobantes[${i.index}].usuarioCreacion" id="usuarioCreacion${i.index}" type="hidden" />
															</tr>
														</c:forEach>
														<c:if test="${empty valorDto.listaComprobantes}">
															<tr>
																<td colspan="3" class="registroTabla" align="left">&nbsp;&nbsp;<spring:message code="mensaje.tabla.vacia"/></td>
															</tr>
														</c:if>
													</tbody>

												</table>
											</div>
										</div>
										<c:if test="${valorDto.errorComprobanteVacioModif}">
											<div class="row rowError">
												<div class="span9" style="color: red; font-size: 9px;">
													<br>
													<spring:message code="valor.comprobante.vacio" />
												</div>
											</div>
										</c:if>
													
									</div> <!-- fin div bloque comprobantes -->
								
								
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button" ><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="boleta.boton.aceptar"/></button>
										<c:choose>
	      									<c:when test="${valorDto.volverBandeja}">
	      										<a href="bandeja-de-entrada" class="btn btn-primary btn-small" id="btnBandeja" name="btnBandeja"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="valor.botonCancelar"/></a>
	      									</c:when>
		  									<c:otherwise>
												<button class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar" type="button" onclick="javascript:volver();"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="boleta.boton.cancelar"/></button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								
								<c:if test="${valorDto.errorUnicidadRegistro}">
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong>Error!</strong><br />${valorDto.descripcionUnicidadRegistro}
									</div>
								</c:if>
								<c:if test="${valorDto.existeReciboError}">
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong>Error!</strong><br />${valorDto.mensajeReciboError}
									</div>
								</c:if>
								<c:if test="${valorDto.errorSaldoReversado}">
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong>Error!</strong><br />
										<form:errors path="errorSaldoReversado" />
									</div>
								</c:if>
							</form:form>
							<form:form id="formRetorno" commandName="registroAVCDto">
							</form:form>
						</div>
					</div>
				</div>
			</div><!-- end #content -->
		</div><!-- end .wrap -->

	</div><!-- end #main -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div><!-- end #container -->
	
	<script>
		window.onload = function() {
			if(<c:out value = "${imprimibleArchivo}"/>) {
				window.location ="${pageContext.request.contextPath}/abrir-documento-comprobante";
			}
			mostrarTipoValor(document.getElementById('idTipoValor'));
			
		};

				 
		$('#fechaIngresoRecibo').datepicker();
		
		$('#fechaEmision').datepicker();
		
		$('#fechaNotificacionDisponibilidadRetiroValor').datepicker();
		
		function volver() {
			$('#bloqueEspera').trigger('click');
			$('#formAlta')[0].action="${pageContext.request.contextPath}/conciliacion-cancelar-alta-valor";
			$('#formAlta')[0].submit();	
		};
		
		

// 		function aceptar() {
// 			$("#segmento").val(obtenerDescripcionSelect("selectSegmento"));
<%-- 			$('#operation').val("<%=Constantes.CREAR_VALOR_REGISTRO_AVC%>"); --%>
// 			$('#bloqueEspera').trigger('click');
// 			$("#adjuntando").val(false);
// 			$("#")
// 			$('#formAlta')[0].submit();
// 		};
		
		function eliminarComprobante(idComprobante) {
			$('#bloqueEspera').trigger('click');
			$("#idComprobanteSelected").val(idComprobante);
			$('#formAlta')[0].action="${pageContext.request.contextPath}/conciliacion-alta-valor-eliminar-comprobante-conciliacion";
			$('#formAlta')[0].submit();
		};
		
		function abrirComprobante(idComprobante) {
			$('#bloqueEspera').trigger('click');
			$("#idComprobanteSelected").val(idComprobante);
			$('#formAlta')[0].action="${pageContext.request.contextPath}/conciliacion-alta-valor-abrir-comprobante-conciliacion";
			$('#formAlta')[0].submit();
		};
		
		 $('#btnAdjuntar').click(function() {
		    $('#bloqueEspera').trigger('click');
		    $('#operation').val("<%=Constantes.SUBIR_COMPROBANTE%>");
		    $("#adjuntando").val(true);
		    $('#formAlta')[0].action="${pageContext.request.contextPath}/conciliacion-alta-valor-alta-comprobante-conciliacion";
		    $('#formAlta')[0].submit();
		 });
		 
		 $("#selectSegmento").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#formAlta')[0].action="${pageContext.request.contextPath}/conciliacion-alta-valor-selecciono-segmento";
			$('#formAlta')[0].submit();
		 });
		 
		 $("#selectAcuerdo").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#formAlta')[0].action="${pageContext.request.contextPath}/seleccionoAcuerdoConciliacion";
			$('#formAlta')[0].submit();
		});
			
		$("#selectNumeroCuenta").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#formAlta')[0].action="${pageContext.request.contextPath}/seleccionoCuentaConciliacion";
			$('#formAlta')[0].submit();
		});
		 
		$("#selectBancoDeposito").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#formAlta')[0].action="${pageContext.request.contextPath}/seleccionoBancoConciliacion";
			$('#formAlta')[0].submit();
		});
		 
		
		
		var codigos = '';
		 
		 <%String lista= (String)request.getAttribute("listaLegadoRazonSocial");
	 				 if (lista!=null && !"".equalsIgnoreCase(lista)) {%>
		 	codigos = <%=lista%>;
		 <%}%>
		 
		 $('#codCliente').autocomplete({
			 minLength: 0,
		     source: codigos,
		     select: function( event, ui ) {
		    	var value = ui.item.value;
		    	var codigoClienteLegado = value.split("-")[0].split(" ").join("");
				$( "#codCliente" ).val(codigoClienteLegado);
		        return false;
			 }
		 });
		 
		 $("#codCliente").keydown(function(event) {
			 if (document.getElementById('codCliente.errors')!=null) {
				 document.getElementById('codCliente.errors').innerHTML='';
			 }
			 			 
			 $("#wsConsultaClienteSiebel").css('display', 'none');
			 $("#codClienteSiebel").val("");
			 var keycode = (event.keyCode ? event.keyCode : event.which);
			 var teclasQNoEscriben = ['27','37','38','39','40','16','17','18','19','20','33','34','35','36','45','144','145','91','92','93', '112','113','114','115','116','117','118','119','120','121','122','123' ];

		//Busco si la tecla presionada fue una tecla que no escribe en el campo de texto	 
			var flag = false;			    
			 for (var i = 0; i < teclasQNoEscriben.length; i++) {
				if (teclasQNoEscriben[i] == keycode){
					flag = true;		
					  break;
				};
			};

		//Si la tecla presionada escribe en campo texto borro campos razon social, cod.cliente, numero holding y nombre holding 				
				    if (!flag) {
		 	 			 $("#razonSocialClienteAgrupador").prop("readonly",false);
		 	 			 $("#razonSocialClienteAgrupador").val("");
		 	 			 $("#razonSocialClienteAgrupador").prop("readonly",true);
		 	 			 $("#numeroHolding").prop("readonly",false);
		 	 			 $("#numeroHolding").val("");
		 	 			 $("#numeroHolding").prop("readonly",true);
		 	 			 $("#nombreHolding").prop("readonly",false);
		 	 			 $("#nombreHolding").val("");
		 	 			 $("#nombreHolding").prop("readonly",true);
		 	 			 $("#codClienteAgrupador").prop("readonly",false);
		 	 			 $("#codClienteAgrupador").val("");
		 	 			 $("#codClienteAgrupador").prop("readonly",true);
					    }
			 
			 if(keycode == '13'){
				var codigoCliente = $('#codCliente').val().split(" ").join("");
		    	if (codigoCliente!="") {
		    		buscarClienteLegadoSiebel(codigoCliente);
		    	} else {
		    		$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html('<spring:message code="error.obligatorio"/>');
		    	};
		    }
			 if(keycode == '9'){
					var codigoCliente = $('#codCliente').val().split(" ").join("");
			    	if (codigoCliente!="") {
			    		buscarClienteLegadoSiebel(codigoCliente);
			    	};
			    }
			    
			event.stopPropagation();
		});
		
		 function buscarClienteLegadoSiebel(codCliente) {
			var codigoClienteLegado = codCliente.split("-")[0].split(" ").join("");
			$('#codCliente').val(codigoClienteLegado);
			
			
			$('#bloqueEspera').trigger('click');
   			var url = '${pageContext.request.contextPath}/consultarClienteSiebel?method=load&ajax=true&nocache='+Math.random();
			var param = "&codCliente="+ codigoClienteLegado;
			$.get(url+param, function(data) {
				if (data.toLowerCase().indexOf("error") >= 0 
						|| data.toLowerCase().indexOf("disponible") >= 0 || data.toLowerCase().indexOf("contener valores") >= 0) 
				{
					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html(data);
				} else {
					if (data.length == 0) {
						$("#wsConsultaClienteSiebel").css('display', 'inline-block');
						$("#wsConsultaClienteSiebel").html('<spring:message code="boleta.alta.mensaje.siebel.vacio"/>');
					} else {
						var res = data.split(";");
						$("#codClienteAgrupador").prop("readonly",false);
						$("#codClienteAgrupador").val(res[0]);
						$("#codClienteAgrupador").prop("readonly",true);
					 		
						$("#razonSocialClienteAgrupador").prop("readonly",false);
						$("#razonSocialClienteAgrupador").val(res[1]);
						$("#razonSocialClienteAgrupador").prop("readonly",true);

						$("#razonSocialClienteAgrupador").prop("readonly",false);
						$("#numeroHolding").val(res[2]);
						$("#razonSocialClienteAgrupador").prop("readonly",true);

						$("#razonSocialClienteAgrupador").prop("readonly",false);								
					 	$("#nombreHolding").val(res[3]);
					 	$("#razonSocialClienteAgrupador").prop("readonly",true);
					 	
					}
				}
				ocultarBloqueEspera();
			});
		};

	</script>
</body>
</html>