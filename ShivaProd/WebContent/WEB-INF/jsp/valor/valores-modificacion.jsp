
<%@page import="ar.com.telecom.shiva.base.enumeradores.TipoValorEnum"%>
<%@page import="ar.com.telecom.shiva.presentacion.bean.dto.ValorDto"%>
<%@page import="ar.com.telecom.shiva.base.utils.Validaciones"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<%@ page import="ar.com.telecom.shiva.base.utils.Validaciones" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="valor.modificacion.titulo"/></title>
	<meta name="Alta de Valores" content="Modificacion de Valores">
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	
	<script src="${pageContext.request.contextPath}/js/valor/valores-modificacion.js"></script>
	<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<style>
.custom-combobox {
    position: relative;
    display: inline-block;
}
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
.custom-combobox-input {
    margin: 0;
    padding: 0.3em;
}

/* button text element */
.ui-button .ui-button-text {
	display: block;
/* 	line-height: normal; */
  	color: #ffffff;
/*   	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); */
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


<script>
var BOLETA_DEPOSITO_CHEQUE = '' + <%=TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor() %>;
var BOLETA_DEPOSITO_CHEQUE_DIFERIDO = '' + <%=TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor() %>;
var BOLETA_DEPOSITO_EFECTIVO = '' + <%=TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor() %>;
var CHEQUE = '' + <%=TipoValorEnum.CHEQUE.getIdTipoValor() %>;
var CHEQUE_DIFERIDO = '' + <%=TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor() %>;
var EFECTIVO = '' + <%=TipoValorEnum.EFECTIVO.getIdTipoValor() %>;
var TRANSFERENCIA = '' + <%=TipoValorEnum.TRANSFERENCIA.getIdTipoValor() %>;
var INTERDEPOSITO = '' + <%=TipoValorEnum.INTERDEPÓSITO.getIdTipoValor() %>;
var RETENCION_IMPUESTO = '' + <%=TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor() %>;
var TIPOMODIFANARECHA = <c:out value = "${valorDto.modifAnaRecha}"/>;

function agregarOpcionCombo (comboId, valor, descripcion) {
	var opt = document.createElement("option");
	comboId.options.add(opt);
	opt.text = descripcion;
	opt.value = valor;
};


	
function mostarTipoImpuesto(referenciaCombo) {
	var tipoImpuestoSeleccionado = referenciaCombo.value;
	switch (tipoImpuestoSeleccionado) {
	case "1":
		bloqueCuitIbb.style.display = 'block';
		break;
	case "2":
		document.getElementById("selectTipoComprobante").value = "";
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueCuitIbb');
		bloqueCuitIbb.style.display = 'none';
		break;
	case "3":
		document.getElementById("selectTipoComprobante").value = "";
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueCuitIbb');
		bloqueCuitIbb.style.display = 'none';
		break;
	case "4":
		document.getElementById("selectTipoComprobante").value = "";
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueCuitIbb');
		bloqueCuitIbb.style.display = 'none';
		break;
	case "5":
		document.getElementById("selectTipoComprobante").value = "";
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueCuitIbb');
		bloqueCuitIbb.style.display = 'none';
		break;
	case "6":
		document.getElementById("selectTipoComprobante").value = "";
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		clearSelectInput('bloqueCuitIbb');
		bloqueCuitIbb.style.display = 'none';
		break;
	}
};
	
function mostarConstancia(referenciaCombo) {
	var tipoValorSeleccionado = referenciaCombo.value;
	switch (tipoValorSeleccionado) {
	case "1":
		bloqueReciboConstancia.style.display = 'block';
		bloqueConstancia.style.display = 'none';
		break;
	case "2":
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		//document.getElementById("inputFechaIngresoReciboReadOnly").value = "";
		bloqueReciboConstancia.style.display = 'none';
		bloqueConstancia.style.display = 'none';
		break;
	case "3":
		bloqueReciboConstancia.style.display = 'block';
		bloqueConstancia.style.display = 'none';
		break;
	case "4":
		document.getElementById("inputReciboPreImpreso").value = "";
		document.getElementById("inputFechaIngresoRecibo").value = "";
		//document.getElementById("inputFechaIngresoReciboReadOnly").value = "";
		bloqueReciboConstancia.style.display = 'none';
		bloqueConstancia.style.display = 'block';
		break;
	}
};

function mostrarTipoComprobante(referenciaCombo) {
	var tipoComprobanteSeleccionado = referenciaCombo.value;
	if(tipoComprobanteSeleccionado == ""){
		document.getElementById("selectLetraComprobante").value = "";
		document.getElementById("inputNumeroLegalComprobante").value = "";
		checkLetraComprobante.style.display = 'none';
		checkNumeroLegalComprobante.style.display = 'none';
	} else {
		checkLetraComprobante.style.display = 'inline';
		checkNumeroLegalComprobante.style.display = 'inline';
	}
};
function mostrarTipoComprobanteOnload(referenciaCombo) {
	var tipoComprobanteSeleccionado = referenciaCombo.value;
	if(tipoComprobanteSeleccionado == ""){
		checkLetraComprobante.style.display = 'none';
		checkNumeroLegalComprobante.style.display = 'none';
	} else {
		checkLetraComprobante.style.display = 'inline';
		checkNumeroLegalComprobante.style.display = 'inline';
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
							<div class="title"><spring:message code="valor.modificacion.titulo.pagina"/></div>
							<div class="pagos_anticipos">
								<p><strong><spring:message code="valor.datosGenerales"/></strong></p>

<!-- DATOS GENERALES -->
								<form:form id="formModificacion" commandName="valorDto" enctype="multipart/form-data">
									<form:hidden path="operation" id="operation"/>
									<form:hidden path="timeStamp"/>
									<form:hidden path="idValor" id="idValor"/>
									
									<form:hidden path="fechaAltaValor" id="fechaAltaValor"/>
									<!-- DATOS MODIFICACION -->
									<form:hidden path="tipoModificacion" id="tipoModificacion"/>
									<form:hidden path="modifAnaRecha" id="modifAnaRecha"/>
									<form:hidden path="modifAnaNoRecha" id="modifAnaNoRecha"/>
									<form:hidden path="modifAdmRecha" id="modifAdmRecha"/>
									<form:hidden path="modifAdmNoRecha" id="modifAdmNoRecha"/>
									<form:hidden path="modifSupRecha" id="modifSupRecha"/>
																		
									<!-- DATOS GENERALES -->
									<form:hidden path="empresa" id="empresa"/>
									<form:hidden path="segmento" id="segmento"/>
									<form:hidden path="nombreAnalista" id="nombreAnalista"/>
									<form:hidden path="mailAnalista" id="mailAnalista"/>
									<form:hidden path="idAnalista" id="idAnalista"/>
									<form:hidden path="copropietario" id="copropietario"/>
									<form:hidden path="pantallaRegreso" id="pantallaRegreso"/>
									<form:hidden path="idEstadoValor" id="idEstadoValor"/>
									<!-- VALORES -->
									<form:hidden path="tipoValor" id="tipoValor"/>
									<form:hidden path="motivo" id="motivo"/>
									<form:hidden path="origen" id="origen"/>
									<form:hidden path="acuerdo" id="acuerdo"/>
									<form:hidden path="bancoDeposito" id="bancoDeposito"/>
									<form:hidden path="numeroCuenta" id="numeroCuenta"/>
									<form:hidden path="bancoOrigen" id="bancoOrigen"/>
									<form:hidden path="numeroValor" id="numeroValor"/>
									<form:hidden path="referenciaValor" id="referenciaValor"/>
									<form:hidden path="razonSocial" id="razonSocial"/>
									<form:hidden path="idHolding" id="idHolding"/>
									<form:hidden path="descripcionHolding" id="descripcionHolding"/>
									<form:hidden path="segmentoAgenciaNegocio" id="segmentoAgenciaNegocio"/>
									<form:hidden path="idAgenciaNegocio" id="idAgenciaNegocio"/>
									<form:hidden path="descripcionAgenciaNegocio" id="descripcionAgenciaNegocio"/>
									<!-- OBSERVACIONES -->

									<form:hidden path="imprimirBoleta" id="imprimirBoleta"/>
									<form:hidden path="enviarBoletaMail" id="enviarBoletaMail"/>
									<!-- AVISOS -->
									<%--<form:hidden path="nroBoleta" id="nroBoleta"/> --%>
									<form:hidden path="tipoImpuesto" id="tipoImpuesto"/>
									<form:hidden path="provincia" id="provincia"/>
									<form:hidden path="tipoComprobante" id="tipoComprobante"/>
									<form:hidden path="letraComprobante" id="letraComprobante"/>
<%-- 									<form:hidden path="nroChequeAReemplazar" id="nroChequeAReemplazar"/> --%>
									<form:hidden path="fechaEmisionInicialmenteNulo" id="fechaEmisionInicialmenteNulo"/>
									<form:hidden path="fechaNotificacionDisponibilidadRetiroValor" id="fechaNotificacionDisponibilidadRetiroValor"/>
									
									<c:if test="${!(valorDto.modifAnaRecha)}">
										<form:hidden path="idEmpresa" id="idEmpresa"/>
										<form:hidden path="idSegmento" id="idSegmento"/>
										<form:hidden path="idOrigen" id="idOrigen"/>
										<form:hidden path="idAcuerdo" id="idAcuerdo"/>
										<form:hidden path="idBancoDeposito" id="idBancoDeposito"/>
										<form:hidden path="idNroCuenta" id="idNroCuenta"/>		
										<form:hidden path="idBancoOrigen" id="idBancoOrigen"/>	
										<form:hidden path="idTipoImpuesto" id="idTipoImpuesto"/>
										<form:hidden path="idProvincia" id="idProvincia"/>	
<%-- 										<form:hidden path="chequeReemplazarId" id="chequeReemplazarId"/> --%>
										<form:hidden path="fechaDeposito" id="fechaDeposito"/>
										<form:hidden path="fechaVencimiento" id="fechaVencimiento"/>
										<form:hidden path="fechaTransferencia" id="fechaTransferencia"/>
										<form:hidden path="fechaInterdeposito" id="fechaInterdeposito"/>
										<!--<form:hidden path="fechaEmision" id="fechaEmision"/>-->
										<form:hidden path="fechaConstancia" id="fechaConstancia"/>
									</c:if>
									
									<c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha)}">
										<form:hidden path="idCopropietario" id="idCopropietario"/>
										<form:hidden path="idMotivo" id="idMotivo"/>
									</c:if>
									<c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha)}">
										<form:hidden path="fechaIngresoRecibo" id="fechaIngresoRecibo"/>
									</c:if>
									<c:if test="${!(valorDto.modifAnaRecha)}">
										<form:hidden path="idTipoComprobante" id="idTipoComprobante"/>
										<form:hidden path="idLetraComprobante" id="idLetraComprobante"/>
									</c:if>
									
									<!-- CLIENTE -->
									<form:hidden path="cliente.cuit" id="cuitCliente"/>
									<form:hidden path="cliente.idClienteLegado" id="idClienteLegado"/>
									<form:hidden path="cliente.empresasAsociadas" id="empresasAsociadas"/>
									<form:hidden path="cliente.razonSocial" id="razonSocialCliente"/>
									<form:hidden path="cliente.origen" id="origenCliente"/>
									<form:hidden path="cliente.agenciaNegocio" id="agenciaNegocio"/>
									<form:hidden path="cliente.descripcionAgenciaNegocio" id="descripcionAgenciaNegocioCliente"/>
									<form:hidden path="cliente.segmentoAgencia" id="segmentoAgencia"/>
									<form:hidden path="cliente.codigoHolding" id="codigoHolding"/>
									<form:hidden path="cliente.descripcionHolding" id="descripcionHoldingCliente"/>
									
									<form:hidden path="fechaAlta" id="fechaAlta"/>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idEmpresa"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.empresa"/></label>
											<div class="controls ">
												<select id ="selectEmpresa" name="idEmpresa" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
													<c:if test="${valorDto.comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
													<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq valorDto.idEmpresa}">
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
											<label class="control-label" for="idSegmento"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.segmento"/></label>
											<div class="controls">
												<select id ="selectSegmento" name="idSegmento" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
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
										</div>
									</div>
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="empresa" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="segmento" cssClass="error" />
										</div>
									</div>
									<c:choose>
										<c:when test="${(valorDto.modifAnaRecha || valorDto.modifAdmRecha)}">
										
											<div id='searchCriteraCliente'>
												<p>
													<strong><spring:message	code="conf.cobro.body.title.criterios" /></strong>
												</p>
			
												<div class="row">
													<div class="span3">
														<div class="controls">
															<select id="selectCriterio" name="selectCriterio" class="input">
																<option value=""><spring:message code="combo.seleccionar" /></option>
																<c:forEach items="${criterioBusquedaCliente}" var="criterioBusquedaCliente">
																	<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="span3">
														<div class="controls">
															<input id="textCriterio" name="textCriterio" type="text" maxlength="13" class="input" style="margin: 0 auto;" />
														</div>
													</div>
													<div class="span3">
														<div>
															<button type="button" class="btn btn-primary btn-small" id="btnBuscarClientes">
																<i class="icon-white icon-search"></i><spring:message code="accion.find" />
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
											<p><strong><spring:message	code="conf.cobro.clientes.busqueda.resultado" /></strong></p>
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
											
											<p><strong><span class="rojo">*</span><spring:message	code="conf.cobro.clientes.seleccionados" /></strong></p>
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
														<c:if test="${not empty valorDto.cliente.idClienteLegado}">
															<tbody>
																<tr>
																	<td></td>
																	<td></td>
																	<td>${valorDto.cliente.cuit}</td>
																	<td>${valorDto.cliente.idClienteLegadoString}</td>
																	<td>${valorDto.cliente.empresasAsociadas}</td>
																	<td>${valorDto.cliente.razonSocial}</td>
																	<td>${valorDto.cliente.origen}</td>
																	<td>${valorDto.cliente.segmentoAgencia}</td>
																	<td>${valorDto.cliente.descripcionHolding}</td>
																	<td>${valorDto.cliente.agenciaNegocio}</td>
																	<td></td>
																</tr> 
															</tbody>
														</c:if>
													</table>
												</div>
											</div>
											<div class="row rowError">
												<div class="span9" align="left">
													<form:errors path="codCliente" cssClass="error" />
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div id="clienteSinModificacion">
												<label class="control-label" for="cliente"><spring:message code="valor.aviso.pago.tabla.clientes.cliente"/></label>
												<div class="row">
													<div class="span9">
														<table id="prevCliente" class="tablaResultado">
															<thead>
																<tr>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.cuit" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.cliente" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.empresas.asociadas" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.razonSocial" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.origen" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.agencia.segmento" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.holding" /></th>
																	<th><spring:message code="valor.aviso.pago.tabla.clientes.agencia" /></th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>${valorDto.cliente.cuit}</td>
																	<td>${valorDto.cliente.idClienteLegadoString}</td>
																	<td>${valorDto.cliente.empresasAsociadas}</td>
																	<td>${valorDto.cliente.razonSocial}</td>
																	<td>${valorDto.cliente.origen}</td>
																	<td>${valorDto.cliente.segmentoAgencia}</td>
																	<td>${valorDto.cliente.descripcionHolding}</td>
																	<td>${valorDto.cliente.agenciaNegocio}</td>
																</tr> 
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</c:otherwise>	
									</c:choose>							   
									<br>	
									<div class="row">	
										<div class="span3">
											<label class="control-label" for="email"><spring:message code="valor.email"/></label>
											<div class="controls">
												<input id="email" name="email" type="email" class="input" required value="${valorDto.email}" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha)}">readonly</c:if>>
											</div>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="email" cssClass="error" />
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="analista"><spring:message code="valor.analista"/></label>
											<div class="controls" style="white-space: nowrap;">
												<img alt="Usuario"  src="${valorDto.urlFotoUsuario(valorDto.idAnalista)}"
													style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 6px; margin-top: 1px; margin-left: 1px; float: left; margin-bottom: 9px;" onerror='src="${pageContext.request.contextPath}/img/default2.png"'>						
														
												<input class="span2" id=analista name="analista" type="text" value="${valorDto.nombreAnalista}" readonly style="margin-bottom: 2px; width: 164px;">
												<div class="row">
													<a href="#"  title="<spring:message code="valor.chat"/>"><i class="icon-comment"></i></a>
													<a href="#"  title="<spring:message code="valor.email"/>"><i class="icon-envelope" style=" margin-left: 3px; "></i></a>
												</div>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idCopropietario"><spring:message code="valor.copropietario"/></label>
											<div class="controls">
												<select id="selectCopropietario" name="idCopropietario" class="input" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha)}">disabled</c:if>>
												<c:if test="${comboCopropietario}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
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
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="copropietario" cssClass="error" />
										</div>
									</div>

								<p><strong><spring:message code="valor.valores"/></strong> </p>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idTipoValor"><spring:message code="valor.tipoValor"/></label>
										<div class="controls">
											<input id="idTipoValor" name="idTipoValor" type="hidden" value="${valorDto.idTipoValor}" >
											<input id="tipoValorStr" name="tipoValorStr" type="text" value="${valorDto.tipoValor}" class="input" readonly>
										</div>
									</div>
									<div class="span3"> 
 										<label class="control-label" for="importe"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.importe"/></label> 
										<div class="controls"> 
											<input id="importe" name="importe" type="text" class="input" value="${valorDto.importe}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>> 
										</div> 
									</div>
									<div class="span3"> 
 										<label class="control-label" for="saldoDisponible"><spring:message code="valor.saldoDisponible"/></label> 
										<div class="controls"> 
											<input id="saldoDisponible" name="saldoDisponible" type="text" class="input" value="${valorDto.saldoDisponible}" readonly> 
										</div> 
									</div> 
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="idTipoValor" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="importe" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="saldoDisponible" cssClass="error" />
									</div>
								</div>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="estadoValor"><spring:message code="valor.estado"/></label>
										<div class="controls">												
											<input id="estadoValor" name="estadoValor" type="text" value="${valorDto.estadoValor}" class="input" readonly>
										</div> 
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="estadoValor" cssClass="error" />
									</div>
								</div>
								<div class="row" id="bloqueNroBoleta" style="display:none">
									<div class="span3">
										<label class="control-label" for="numeroBoleta"><c:if test="${valorDto.esNumeroBoletaObligatorio}"><span class="rojo">* </span></c:if><spring:message code="valor.nroBoleta"/></label>
										<div class="controls">
											<input id="numeroBoleta" name="numeroBoleta" type="text" class="input" maxlength="10" value="${valorDto.numeroBoleta}" <c:if test="${valorDto.esNumeroBoletaReadOnly}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="numeroBoleta" cssClass="error" />
									</div>
								</div>
								
									
								<div class="row">
									<div class="span3">
									<label class="control-label" for="idMotivo"><spring:message code="valor.motivo"/></label>
										<div class="controls">
											<select id ="selectMotivo" name="idMotivo" class="input" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha)}">disabled</c:if>>
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
										<label class="control-label" for="operacionAsociada"><spring:message code="valor.operacionAsociada"/></label>
										<div class="controls">
											<input id="operacionAsociada" name="operacionAsociada" type="text" class="input" value="${valorDto.operacionAsociada}" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3" id="bloqueOrigen" style="display:none">
										<c:if test="${(valorDto.esInterdeposito)}">
										<label class="control-label" for="origen"><spring:message code="valor.origen"/></label> 
										<div class="controls"> 
											<input id="selectOrigen" name="selectOrigen" type="text" class="input" value="${valorDto.origen}" readonly> 
										</div>
										</c:if>
										<c:if test="${!(valorDto.esInterdeposito)}">
										<label class="control-label" for="idOrigen"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.origen"/></label>
										<div class="controls">
											<select id ="selectOrigen" name="idOrigen" class="input" onchange="mostarConstancia(this)" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
											<c:if test="${valorDto.comboOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${origenes}" var="ori">
													<c:choose>
														<c:when test="${ori.idOrigen eq valorDto.idOrigen}">
															<option value="${ori.idOrigen}" selected>${ori.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${ori.idOrigen}">${ori.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
										</c:if>
									</div>
									<div class="span3" id="bloqueNroDocumentoContable" style="display:none">
										<label class="control-label" for="numeroDocumentoContable"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroDocumentoContable"/></label>
										<div class="controls">
											<input id="numeroDocumentoContable" name="numeroDocumentoContable" type="text" class="input" maxlength="10" value="${valorDto.numeroDocumentoContable}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="motivo" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="operacionAsociada" cssClass="error" />
									</div>
									<div class="span3" id="bloqueOrigenError" style="display:none">
										<form:errors path="origen" cssClass="error" />
									</div>
									<div class="span3" id="bloqueNroDocumentoContableError" style="display:none">
										<form:errors path="numeroDocumentoContable" cssClass="error" />
									</div>
								</div>
									
								<!-- VARIABLES -->
								<div class="row" id="bloqueAcuerdo" style="display:none">
									<c:if test="${valorDto.bloqueAcuerdoDeshabilitado}">
										<div class="span3">
											<label class="control-label" for="acuerdo"><spring:message code="valor.acuerdo"/></label> 
											<div class="controls"> 
												<input id="selectAcuerdo" name="selectAcuerdo" type="text" class="input" value="${valorDto.acuerdo}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="bancoDeposito"><spring:message code="valor.bancoDeposito"/></label> 
											<div class="controls"> 
												<input id="selectBancoDeposito" name="selectBancoDeposito" type="text" class="input" value="${valorDto.bancoDeposito}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="numeroCuenta"><spring:message code="valor.nroCuenta"/></label> 
											<div class="controls"> 
												<input id="selectNumeroCuenta" name="selectNumeroCuenta" type="text" class="input" value="${valorDto.numeroCuenta}" readonly> 
											</div>
										</div>
									</c:if>
									<c:if test="${!(valorDto.bloqueAcuerdoDeshabilitado)}">
									<div class="span3">
										<label class="control-label" for="acuerdo"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.acuerdo"/></label>
										<div class="controls">
											<select id="selectAcuerdo" name="idAcuerdo" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
												<c:if test="${valorDto.comboAcuerdo}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${acuerdos}" var="acu">
													<c:choose>
														<c:when test="${acu.idAcuerdo eq valorDto.idAcuerdo}">
															<option value="${acu.idAcuerdo}" selected>${acu.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${acu.idAcuerdo}">${acu.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idBancoDeposito"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.bancoDeposito"/></label>
										<div class="controls">
											<select id ="selectBancoDeposito" name="idBancoDeposito" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
												<c:if test="${valorDto.comboBanco}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
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
									</div>
									<div class="span3">
										<label class="control-label" for="idNroCuenta"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroCuenta"/></label>
										<div class="controls">
											<select id ="selectNumeroCuenta" name="idNroCuenta" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
												<c:if test="${valorDto.comboCuenta}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
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
									</div>
									</c:if>
								</div>
								<c:if test="${!(valorDto.bloqueAcuerdoDeshabilitado)}">
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="acuerdo" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="bancoDeposito" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="numeroCuenta" cssClass="error" />
									</div>
								</div>
								</c:if>	
								<div class="row">
									<div class="span3" id="bloqueFechaDeposito" style="display:none">
										<label class="control-label" for="fechaDeposito"><c:if test="${valorDto.modifAnaRecha}"><span class="rojo">* </span></c:if><spring:message code="valor.fechaDeposito"/></label>
										<div class="controls">
											<input type="text" id="fechaDeposito" data-date-format="dd/mm/yyyy" name="fechaDeposito" data-date-language='es' maxlength="10" class="input" value="${valorDto.fechaDeposito}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="fechaDeposito" cssClass="error" />
									</div>
								</div>
								
								<div class="row">
									<div class="span3" id="bloqueBancoOrigen" style="display:none">
										<label class="control-label" for="idBancoOrigen"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.bancoOrigen"/></label>
										<div class="controls">
											<select id ="selectBancoOrigen" name="idBancoOrigen" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
												<c:if test="${valorDto.comboBancoOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${bancoOrigenes}" var="ori">
													<c:choose>
														<c:when test="${ori.idBanco eq valorDto.idBancoOrigen}">
															<option value="${ori.idBanco}" selected>${ori.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${ori.idBanco}">${ori.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
									<div class="span3" id="bloqueNroCheque" style="display:none">
										<label class="control-label" for="numeroCheque"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroCheque"/></label>
										<div class="controls">
											<input id="numeroCheque" name="numeroCheque" type="text" maxlength="8" class="input" value="${valorDto.numeroCheque}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3" id="bloqueFechaEmisionCheque" style="display:block">
										<label class="control-label" for="fechaEmisionCheque"><spring:message code="valor.fechaEmision.cheque"/></label> 
										<div class="controls"> 
											<input id="fechaEmisionCheque" path="fechaEmision" name="fechaEmision" maxlength="10" type="text" class="input" value="${valorDto.fechaEmision}" <c:if test="${!valorDto.fechaEmisionInicialmenteNulo}">readonly</c:if>>
										</div>
									</div>

								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="bancoOrigen" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="numeroCheque" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaEmisionCheque" cssClass="error" />
									</div>
									
								</div>
								<div class="row">
									<div class="span3" id="bloqueFechaVencimiento" style="display:none">
										<label class="control-label" for="fechaVencimiento"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><span id="space" style="display:none">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span><spring:message code="valor.fechaVencimiento"/></label>
										<div class="controls">
											<input type="text" id="fechaVencimiento" path="fechaVencimiento" maxlength="10" name="fechaVencimiento" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" value="${valorDto.fechaVencimiento}"<c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3" id="bloqueFechaNotificacionDisponibilidadRetiroValor" style="display:none">
										<label class="control-label" for="fechaNotificacionValor"><spring:message code="valor.fechaNotiDispRetiro"/></label>
										<div class="controls">
											<input type="text" id="fechaNotificacionValor" maxlength="10" name="fechaNotificacionValor" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" value="${valorDto.fechaNotificacionDisponibilidadRetiroValor}" readonly>
										</div>
									</div>
									
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="fechaNotificacionDisponibilidadRetiroValor" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors  cssClass="error" />
										</div>
									</div>
								</div>
								<div class="row" id="bloqueNroReferencia" style="display:none">
									<div class="span3">
										<label class="control-label" for="numeroReferencia"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroReferencia"/></label>
										<div class="controls">
											<input id="numeroReferencia" name="numeroReferencia" type="text" class="input" value="${valorDto.numeroReferencia}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaTransferencia"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.fechaTransferencia"/></label>
										<div class="controls">
											<input type="text" id="fechaTransferencia" maxlength="10" name="fechaTransferencia" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" value="${valorDto.fechaTransferencia}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="cuit"><c:if test="${valorDto.modifAnaRecha && !valorDto.esTransferencia}"><span class="rojo">* </span></c:if><spring:message code="valor.cuit"/></label>
										<div class="controls">
											<input id="cuit" name="cuit" type="text" class="input" value="${valorDto.cuit}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="numeroReferencia" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaTransferencia" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="cuit" cssClass="error" />
									</div>
								</div>
								
								<div id="bloqueInterdeposito" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaInterdeposito"><spring:message code="valor.fechaInterdeposito"/></label>
										<div class="controls">
											<input type="text" id="fechaInterdeposito" maxlength="10" data-date-format="dd/mm/yyyy" name="fechaInterdeposito" data-date-language='es' class="input" value="${valorDto.fechaInterdeposito}" readonly>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="fechaInterdeposito" cssClass="error" />
									</div>
								</div>
								
								<div class="row">
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
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="numeroInterdepositoCdif" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="codOrganismo" cssClass="error" />
									</div>
								</div>
								</div>
								
								<div class="row" id="bloqueTipoImpuesto" style="display:none">
									<div class="span3">
										<label class="control-label" for="idTipoImpuesto"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.tipoImpuesto"/></label>
										<div class="controls ">
											<select id ="selectTipoImpuesto" name="idTipoImpuesto" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if> onchange="mostarTipoImpuesto(this)">
												<c:if test="${valorDto.comboTipoImpuesto}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoRetencionImpuesto}" var="seg">
													<c:choose>
														<c:when test="${seg.idTipoRetencionImpuesto eq valorDto.idTipoImpuesto}">
															<option value="${seg.idTipoRetencionImpuesto}" selected>${seg.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${seg.idTipoRetencionImpuesto}">${seg.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="nroConstancia"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroConstancia"/></label>
										<div class="controls">
											<input id="numeroConstancia" name="nroConstancia" type="text" class="input" value="${valorDto.nroConstancia}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaEmision"><span class="rojo">* </span><spring:message code="valor.fechaEmision"/></label>
										<div class="controls">
											<input type="text" id="fechaEmision" maxlength="10" data-date-format="dd/mm/yyyy" name="fechaEmision" data-date-language='es' class="input" value="${valorDto.fechaEmision}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="tipoImpuesto" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="nroConstancia" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaEmision" cssClass="error" />
									</div>
								</div>
								
<!-- 								<div class="row" id="bloqueChequeReemplazar" style="display:none"> -->
<!-- 									<div class="span3"> -->
<%-- 										<label class="control-label" for="idNroChequeAReemplazar"><spring:message code="valor.nroChequeAReemplazar"/></label> --%>
<!-- 										<div class="controls"> -->
<%-- 											<select id ="selectNroChequeAReemplazar" name="idValorAsociadoAlChequeAReemplazar" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>> --%>
<%-- 												<c:if test="${valorDto.comboChequeReemplazar}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if> --%>
<%-- 												<c:forEach items="${chequesReemplazar}" var="acu"> --%>
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${acu.chequeReemplazarNumero eq valorDto.nroChequeAReemplazar}"> --%>
<%-- 															<option value="${acu.chequeReemplazarId}" selected>${acu.chequeReemplazarNumero}</option> --%>
<%-- 														</c:when> --%>
<%-- 														<c:otherwise> --%>
<%-- 															<option value="${acu.chequeReemplazarId}">${acu.chequeReemplazarNumero}</option> --%>
<%-- 														</c:otherwise> --%>
<%-- 													</c:choose> --%>
<%-- 												</c:forEach> --%>
<!-- 											</select> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="row rowError" > -->
<!-- 									<div class="span3"> -->
<%-- 										<form:errors path="nroChequeAReemplazar" cssClass="error" /> --%>
<!-- 									</div> -->
<!-- 								</div> -->
								
								<div id="bloqueReciboConstancia" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="reciboPreImpreso"><spring:message code="valor.recibo"/></label>
										<div class="controls">
											<input id="inputReciboPreImpreso" name="reciboPreImpreso" type="text" class="input" maxlength="13" value="${valorDto.reciboPreImpreso}" <c:if test="${!((valorDto.modifAnaRecha || valorDto.modifAnaNoRecha) || (valorDto.esAvispoDePago && valorDto.editarFechaIngresoRecibo(sessionScope.loginUser, false)))}">readonly</c:if>>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaIngresoRecibo">
										<c:if test="${(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha) && (valorDto.idTipoValor!='5' && valorDto.idTipoValor!='6' && valorDto.idTipoValor!='7')}">
											<span class="rojo">* </span>
										</c:if><spring:message code="valor.fechaIngresoRecibo"/></label>
										<div class="controls">
											<c:if test="${!valorDto.editarFechaIngresoRecibo(sessionScope.loginUser, true)}">
												<input  id="inputFechaIngresoReciboReadOnly" name="fechaIngresoRecibo" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaIngresoRecibo}" <c:if test="${!valorDto.editarFechaIngresoRecibo(sessionScope.loginUser, true)}">readonly</c:if>>
											</c:if>
											<c:if test="${valorDto.editarFechaIngresoRecibo(sessionScope.loginUser, true)}">
												<input  id="inputFechaIngresoRecibo" maxlength="10" name="fechaIngresoRecibo" type="text" class="input" data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaIngresoRecibo}">
											</c:if>
											
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
								
								<div id="bloqueConstancia" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="constancia"><spring:message code="valor.constancia"/></label>
										<div class="controls">
											<input id="inputConstancia" name="constancia" type="text" class="input" maxlength="16" value="${valorDto.constancia}" readonly>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaConstancia"><spring:message code="valor.fechaConstancia"/></label>
										<div class="controls">
											<input  id="inputFechaConstancia" name="fechaConstancia" type="text" class="span3" data-date-format="dd/mm/yyyy" data-date-language='es' value="${valorDto.fechaConstancia}" readonly>
										</div>
									</div>
								</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="constancia" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaConstancia" cssClass="error" />
									</div>
								</div>
								
								<div id="bloqueCuitIbb" style="display:none">
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="cuitIbb"><span class="rojo">* </span><spring:message code="valor.cuit"/></label>
										<div class="controls">
											<input id="cuitIbb" name="cuitIbb" type="text" class="input" value="${valorDto.cuitIbb}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idProvincia"><span class="rojo">* </span><spring:message code="valor.provincias"/></label>
										<div class="controls">
											<select id ="selectProvincia" name="idProvincia" class="input" <c:if test="${!(valorDto.modifAnaRecha)}">disabled</c:if>>
												<c:if test="${valorDto.comboProvincia}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${provincia}" var="acu">
													<c:choose>
														<c:when test="${acu.provincia eq valorDto.idProvincia}">
															<option value="${acu.provincia}" selected>${acu.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${acu.provincia}">${acu.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="cuitIbb" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="provincia" cssClass="error" />
									</div>
								</div>
								
								<p>	<strong><spring:message code="valor.facturaRelacionada"/></strong> </p>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idTipoComprobante"><spring:message code="valor.tipoComprobante"/></label>
										<div class="controls">
											<select id ="selectTipoComprobante" name="idTipoComprobante" class="input" <c:if test="${!(valorDto.modifAnaRecha || (valorDto.modifAdmRecha && valorDto.modifUltimoMes) || (valorDto.modifAdmNoRecha && valorDto.modifUltimoMes))}">disabled</c:if> onchange="mostrarTipoComprobante(this)">
												<c:if test="${valorDto.comboTipoComprobante}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoComprobante}" var="acu">
													<c:choose>
														<c:when test="${acu.idTipoComprobante eq valorDto.idTipoComprobante}">
															<option value="${acu.idTipoComprobante}" selected>${acu.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${acu.idTipoComprobante}">${acu.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idLetraComprobante"><div id="checkLetraComprobante" style="display:none"><span class="rojo">* </span></div><spring:message code="valor.letraComprobante"/></label>
										<div class="controls">
											<select id ="selectLetraComprobante" name="idLetraComprobante" class="input" <c:if test="${!(valorDto.modifAnaRecha || (valorDto.modifAdmRecha && valorDto.modifUltimoMes) || (valorDto.modifAdmNoRecha && valorDto.modifUltimoMes))}">disabled</c:if>>
												<c:if test="${valorDto.comboLetraComprobante}"><option value=""><spring:message code="combo.seleccionar"/></option> </c:if>
												<c:forEach items="${idTipoLetraComprobante}" var="acu">
													<c:choose>
														<c:when test="${acu.idTipoLetraComprobante eq valorDto.idLetraComprobante}">
															<option value="${acu.idTipoLetraComprobante}" selected>${acu.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${acu.idTipoLetraComprobante}">${acu.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="numeroLegalComprobante"><div id="checkNumeroLegalComprobante" style="display:none"><span class="rojo">* </span></div><spring:message code="valor.nroLegal"/></label>
										<div class="controls">
											<input id="inputNumeroLegalComprobante" name="numeroLegalComprobante" type="text" 
												class="input" maxlength="16" value="${valorDto.numeroLegalComprobante}" <c:if test="${!(valorDto.modifAnaRecha || (valorDto.modifAdmRecha && valorDto.modifUltimoMes) || (valorDto.modifAdmNoRecha && valorDto.modifUltimoMes))}">readonly</c:if>>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="tipoComprobante" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="letraComprobante" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="numeroLegalComprobante" cssClass="error" />
									</div>
								</div>
								</div>
								
<!-- FIN VARIABLES -->
								<div id="bloqueObsMail" style="display:block">
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacionMail"><spring:message code="valor.observacionesEmail"/></label>
											<textarea class="field span9" id="observacionMail" name="observacionMail" 
												maxlength="250" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>${valorDto.observacionMail}</textarea>
										</div>
									</div>
								</div>
								<div id="bloqueObservaciones" style="display:block">
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observaciones"><spring:message code="boleta.observaciones"/></label>
											<textarea class="field span9" id="idObservaciones" name="observaciones"  
												maxlength="250" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAnaNoRecha || valorDto.modifSupRecha || (valorDto.esAvispoDePago && valorDto.editarFechaIngresoRecibo(sessionScope.loginUser, false)))}">readonly</c:if>>${valorDto.observaciones}</textarea>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span9">
											<form:errors path="observaciones" cssClass="error" />
										</div>
									</div>
								</div>
								
							<!-- INCIO SECCION COMPROBANTE -->
							<div id="bloqueComprobantesAgregar" style="display:none">
								<p><strong><spring:message code="valor.comprobantes"/></strong></p>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fileComprobanteModificacion"><spring:message code="valor.adjuntarComprobante"/></label>
										<div class="fileupload fileupload-new" data-provides="fileupload"><input type="hidden">
											<div class="input-append">
												<div class="uneditable-input span3" style="-webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px;">
													<span class="fileupload-preview" style="font-size: 12px; font-family: Helvetica, Arial, sans-serif; color: black"></span>
												</div>
												<span class="btn btn-file btn-primary btn-small" style="margin-left: 15px; font-size: 11, 9px; font-family: Helvetica, Arial, sans-serif; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
												<span class="fileupload-new"><spring:message code="valor.botonSeleccionarArchivo"/></span>
												<span class="fileupload-exists"><spring:message code="valor.botonCambiar"/></span><form:input type="file" path="fileComprobanteModificacion"/></span>
												<a href="#" class="btn fileupload-exists btn-primary btn-small" style="margin-left: 6px; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;" data-dismiss="fileupload"><spring:message code="valor.botonEliminar"/></a>
											</div>
										</div>
									</div>
								</div>
								<!-- Habría que ver de borrar este c:if-->
								<c:if test="${valorDto.comprobantePathVacioModif}">
									<p style="color:red">
										<spring:message code="error.obligatorio"/>
									</p>
								</c:if>
								
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="errorArchivoVacio" cssClass="error" />
									</div>
								</div>
<%-- 								<c:if test="${valorDto.errorArchivoVacio}"> --%>
<!-- 									<p style="color:red"> -->
<%-- 										<spring:message code="valor.error.archivoVacio"/> --%>
<!-- 									</p> -->
<%-- 								</c:if> --%>
								<div class="row" style="margin-top: 0px; margin-bottom: 5px">
									<div class="span9">
										<label class="control-label" for="descripcionComprobante"><spring:message code="valor.descripcionComprobante"/></label>
										<textarea class="field span9" id="descripcionComprobante" name="descripcionComprobante" 
											maxlength="150">${valorDto.descripcionComprobante}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="comprobanteDescripcionVacioModif" cssClass="error" />
									</div>
								</div>
<%-- 								<c:if test="${valorDto.comprobanteDescripcionVacioModif}"> --%>
<!-- 									<p style="color:red"> -->
<%-- 										<spring:message code="error.obligatorio"/> --%>
<!-- 									</p> -->
<%-- 								</c:if> --%>
								<div class="row" align="right" style="margin-top: 15px; margin-bottom: 15px; width: 699px;">
									<button type="button" class="btn btn-primary btn-small" id="btnAdjuntar" name="btnAdjuntar">
										<i class="icon-white icon-upload"></i> <spring:message code="valor.botonAdjuntar"/></button>
								</div>
								
							</div>
							<div id="bloqueComprobantes" style="display: none">
								<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>
								
								<c:if test="${!valorDto.modifAnaRecha}">
									<p><strong><spring:message code="valor.comprobantes"/></strong></p>
								</c:if>
								<div class="row">
								<div class="span9">
									<table
										style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit;"
										class="tablaResultadoHistorialComprobante" id="listaComprobantes">
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
												<td class="registroTabla" align="center">${compro.nombreArchivo}</td>
												<td class="registroTabla" align="center">${compro.descripcionArchivo}</td>
												<td>
													<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<button id="btnVerComprobante" type="button" class="btn btn-xs btn-link" title="Ver documento adjunto"
														onclick="abrirComprobante(${compro.id});">
															<i class="icon-download-alt bigger-120"></i>
														</button>
													</div>
													<c:if test="${(valorDto.modifAnaRecha) and (sessionScope.loginUser.idUsuario eq compro.usuarioCreacion)}">
													<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<button id="btnEliminarComprobante" type="button" class="btn btn-xs btn-link" title="Eliminar documento"
															onclick="eliminarComprobante(${compro.id});">
															<i class="icon-trash bigger-120"></i>
														</button>
													</div>
													</c:if>
												</td>
												<form:input path="listaComprobantes[${i.index}].nombreArchivo" id="nombreArchivo${i.index}" type="hidden"/>
												<form:input path="listaComprobantes[${i.index}].descripcionArchivo" id="descripcionArchivo${i.index}" type="hidden"/>
												<form:input path="listaComprobantes[${i.index}].documento" id="documento${i.index}" type="hidden"/>
												<form:input path="listaComprobantes[${i.index}].id" id="id${i.index}" type="hidden"/>
												<form:input path="listaComprobantes[${i.index}].usuarioCreacion" id="usuarioCreacion${i.index}" type="hidden"/>
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
							
							</div>
							<!-- FIN SECCION COMPROBANTES -->

								<div class="row" style="margin-top: 0px; margin-bottom: 0px">
									<div class="span6" id="bloqueCheckRecibido" style="display: none">
										<label class="checkbox inline"> 
										<input id="documentacionOriginalRecibida" name="documentacionOriginalRecibidaBool" type="checkbox"
										<c:if test="${valorDto.chkDocumentacionOriginalDocumentacion}"> checked</c:if> 
										<c:if test="${!(valorDto.modifAdmNoRecha)}"> disabled</c:if>
										><spring:message code="valor.documentacionOriginalRecibida"/></label>
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button">
											<i class="icon-white icon-ok"></i> <spring:message code="valor.botonAceptar"/>
										</button>
										<c:choose>
	      									<c:when test="${valorDto.volverBandeja}">
	      										<a href="bandeja-de-entrada" class="btn btn-primary btn-small" id="btnBandeja" name="btnBandeja"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="valor.botonCancelar"/></a>
	      									</c:when>
		  									<c:otherwise>
												<button class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar" type="button" onclick="volver();">
													<i class="icon-white icon-remove"></i><spring:message code="valor.botonCancelar" />
												</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								
								<c:if test="${valorDto.errorNingunaModificacion}">
									<br><br>
									<div class="alert alert-error" align="center" style="width:379px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">												
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br>
										<c:out value="${valorDto.descripcionNingunaModificacion}"/>
										
									</div>
								</c:if>
								<c:if test="${valorDto.errorComprobanteVacioModif}">
									<br><br>
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br>
										<spring:message code="valor.modificacion.comprobante.vacio"/>
									</div>
								</c:if>
								<c:if test="${errorValorDuplicado}">
									<br><br>
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br>
										<c:forEach items="${errorValorDuplicadoMensaje}" var="msj" varStatus="i" begin="0">
										<br>${msj}
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${valorDto.existeReciboError}">
									<br><br>
									<div class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br>
										<br>
										${valorDto.mensajeReciboError}
										<br/>
									</div>
								</c:if>
							</form:form>
						</div> 											<!-- end pagos 		-->
					</div> 												<!-- end payment 	-->
				</div> 													<!-- end inside 	-->
			</div> 														<!-- end content 	-->
		</div> 															<!-- end wrap 		-->
	</div> 																<!-- end main 		-->
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
</div> 																	<!-- end container 	-->

<script>
	//INICIO
	$(function() {
		window.onload = function() {
			if(<c:out value = "${imprimibleArchivo}"/>) {
				window.location ="${pageContext.request.contextPath}/abrir-documento";
			}
			
// 			buscarClienteLegadoSiebel($('#inputCodCliente').val().split(" ").join(""));
			mostarTipoValor(document.getElementById('idTipoValor'));
			mostarConstancia(document.getElementById('selectOrigen'));
			mostarTipoImpuesto(document.getElementById('selectTipoImpuesto'));
			mostrarTipoComprobanteOnload(document.getElementById('selectTipoComprobante'));
			$("#divLoader").css({
				height: ($.getDocHeight()) + 'px'
			});
		};
	});
	
	//MANEJO DE FECHAS
	$('#fechaDeposito').datepicker();
	$('#fechaVencimiento').datepicker();
	$('#fechaTransferencia').datepicker();
	$('#fechaInterdeposito').datepicker();
	$('#fechaEmision').datepicker();
	$('#fechaEmisionCheque').datepicker();
	$('#inputFechaIngresoRecibo').datepicker();
	$('#inputFechaConstancia').datepicker();

	//CARGA DE COMBOS
	$("#selectEmpresa").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoEmpresaModificacion";
		$('#formModificacion')[0].submit();
	});
	$("#selectSegmento").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoSegmentoModificacion";
		$('#formModificacion')[0].submit();
	});
	$("#selectOrigen").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoOrigenModificacion";
		$('#formModificacion')[0].submit();
	});
	$("#selectAcuerdo").change(function () {
		if (<c:out value = "${valorDto.bloqueAcuerdoDeshabilitado}"/>) {
		} else {
			$('#bloqueEspera').trigger('click');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoAcuerdoValorModificacion";
			$('#formModificacion')[0].submit();
		}
	});
	$("#selectNumeroCuenta").change(function () {
		if (<c:out value = "${valorDto.bloqueAcuerdoDeshabilitado}"/>) {
		} else {
			$('#bloqueEspera').trigger('click');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoCuentaValorModificacion";
			$('#formModificacion')[0].submit();
		}
	});
	
	
	function volver() {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/vuelta-busqueda";
	    $('#formModificacion')[0].submit();
	};
	
	$('#btnAceptar').click(function() {
		$("#empresa").val(obtenerDescripcionSelect("selectEmpresa"));
		$("#segmento").val(obtenerDescripcionSelect("selectSegmento"));
		$("#copropietario").val(obtenerDescripcionSelect("selectCopropietario"));
		$("#tipoValor").val($("#tipoValorStr").val());
		$("#motivo").val(obtenerDescripcionSelect("selectMotivo"));
		$("#bancoOrigen").val(obtenerDescripcionSelect("selectBancoOrigen"));
// 		$("#nroChequeAReemplazar").val(obtenerDescripcionSelect("selectNroChequeAReemplazar"));
		
		if (<c:out value = "${valorDto.bloqueAcuerdoDeshabilitado}"/>) {
			$("#acuerdo").val($("#selectAcuerdo").val());
			$("#bancoDeposito").val($("#selectBancoDeposito").val());
			$("#numeroCuenta").val($("#selectNumeroCuenta").val());
		} else {
			$("#acuerdo").val(obtenerDescripcionSelect("selectAcuerdo"));
			$("#bancoDeposito").val(obtenerDescripcionSelect("selectBancoDeposito"));
			$("#numeroCuenta").val(obtenerDescripcionSelect("selectNumeroCuenta"));
		}
		
		if (<c:out value = "${valorDto.esInterdeposito}"/>) {
			$("#origen").val($("#selectOrigen").val());
		} else {
			$("#origen").val(obtenerDescripcionSelect("selectOrigen"));
		}
		if (<c:out value = "${valorDto.estadoValor ne 'Disponible'}"/>) {
			$("#saldoDisponible").val($("#importe").val());
		}
		
		$("#tipoImpuesto").val(obtenerDescripcionSelect("selectTipoImpuesto"));
		$("#provincia").val(obtenerDescripcionSelect("selectProvincia"));
		$("#tipoComprobante").val(obtenerDescripcionSelect("selectTipoComprobante"));
		$("#letraComprobante").val(obtenerDescripcionSelect("selectLetraComprobante"));
		
		$('#bloqueEspera').trigger('click');
		$('#btnAceptar').attr('disabled', true);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-modificacion-valor";
		$('#formModificacion')[0].submit();
	});
	
	var codigos = '';
	 
	 <%String lista= (String)request.getAttribute("listaLegadoRazonSocial");
	 if (lista!=null && !"".equalsIgnoreCase(lista)) {%>
	 	codigos = <%=lista%>;
	 <%}%>
	 
	 $('#inputCodCliente').autocomplete({
		 minLength: 0,
	     source: codigos,
	     select: function( event, ui ) {
	    	var value = ui.item.value;
	    	var codigoClienteLegado = value.split("-")[0].split(" ").join("");
			$( "#inputCodCliente" ).val(codigoClienteLegado);
	        return false;
	     }
	 });

	$("#inputCodCliente").keydown(function(event) {
		$("#wsConsultaClienteSiebel").css('display', 'none');
		$("#codClienteSiebel").val("");
		var teclasQNoEscriben = ['27','37','38','39','40','16','17','18','19','20','33','34','35','36','45','144','145','91','92','93', '112','113','114','115','116','117','118','119','120','121','122','123' ];
		//Busco si la tecla presionada fue una tecla que no escribe en el campo de texto	 
		var flag = false;			    
		for (var i = 0; i < teclasQNoEscriben.length; i++) {
			if (teclasQNoEscriben[i] == keycode){
				flag = true;		
				break;
			}
		}
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
// 			document.getElementById('selectNroChequeAReemplazar').options.length = 0;
		}
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			if($('#inputCodCliente').val() != "") {
				$('#inputCodCliente').val($('#inputCodCliente').val().split("-")[0].split(" ").join(""));
				var codigoCliente = $('#inputCodCliente').val().split(" ").join("");
				if (codigoCliente!="") {
					if($.isNumeric(codigoCliente) && Math.floor(codigoCliente) == codigoCliente && parseInt(codigoCliente)>0) {
						buscarClienteLegadoSiebel(codigoCliente);
					} else {
						$("#wsConsultaClienteSiebel").css('display', 'inline-block');
						$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
					}
				} else {
					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
				};			
			} else {
				$("#wsConsultaClienteSiebel").css('display', 'inline-block');
				$("#wsConsultaClienteSiebel").html('<spring:message code="error.obligatorio"/>');
			}
		}
		if(keycode == '9'){
			if($('#inputCodCliente').val() != "") {
				$('#inputCodCliente').val($('#inputCodCliente').val().split("-")[0].split(" ").join(""));
				var codigoCliente = $('#inputCodCliente').val().split(" ").join("");
				if (codigoCliente!="") {
					if($.isNumeric(codigoCliente) && Math.floor(codigoCliente) == codigoCliente && parseInt(codigoCliente)>0) {
						buscarClienteLegadoSiebel(codigoCliente);
					} else {
						$("#wsConsultaClienteSiebel").css('display', 'inline-block');
						$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
					}
				} else {
					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
				};			
			} else {
				$("#wsConsultaClienteSiebel").css('display', 'inline-block');
				$("#wsConsultaClienteSiebel").html('<spring:message code="error.obligatorio"/>');
			}
		}
		event.stopPropagation();
	});

	
	$('#btnAdjuntar').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#operation').val('<%=Constantes.SUBIR_COMPROBANTE%>');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-alta-comprobante-modificacion";
		$('#formModificacion')[0].submit();
	});
	
	function eliminarComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-eliminar-comprobante-modificacion";
		$('#formModificacion')[0].submit();
	};
	
	function abrirComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-abrir-comprobante-modificacion";
		$('#formModificacion')[0].submit();
	};
	
	
	$(function() {
		if (!$('#selectBancoOrigen').is(':disabled')) {
			$( "#selectBancoOrigen").combobox2();
			$( "#toggle" ).click(function() {
				$( "#combobox2" ).toggle();
			});
		}
	  });
	$(function() {
		if (<c:out value = "${valorDto.esInterdeposito}"/>) {
		} else {
			if (!$('#selectBancoOrigen').is(':disabled')) {
			    $( "#selectBancoDeposito" ).combobox();
			    $( "#toggle" ).click(function() {
			      $( "#combobox" ).toggle();
			    });
			}
		};
	  });
	  
	  
	(function( $ ) {
	    $.widget( "custom.combobox2", {
	      _create: function() {
	        	this.wrapper = $( "<span>" ).addClass( "custom-combobox" ).insertAfter( this.element );
	        	this.element.hide();
	        	this._createAutocomplete();
	       		this._createShowAllButton();
	      },
	      _createAutocomplete: function() {
	        	var selected = this.element.children( ":selected" ),
	          	value = selected.val() ? selected.text() : "Seleccione un item...";
	        	this.input = $( "<input>" ).appendTo( this.wrapper ).val( value ).attr( "title", "" ).addClass( "" ).autocomplete({delay: 0,minLength: 0,source: $.proxy( this, "_source" )}).tooltip({tooltipClass: "ui-state-highlight"});
	        	this._on( this.input, {autocompleteselect: function( event, ui ) {ui.item.option.selected = true; this._trigger( "select", event, {item: ui.item.option});}, autocompletechange: "_removeIfInvalid"});
	      },
	      _createShowAllButton: function() {
	        var input = this.input;
	        var wasOpen = false;
	        $( "<a>" ).attr( "tabIndex", -1 ).tooltip().appendTo( this.wrapper ).button({icons: {primary: "ui-icon-triangle-1-s"},text: false}).removeClass( "ui-corner-all" ).removeClass("ui-button")
	          .addClass( "custom-combobox-toggle ui-corner-right" )
	          .mousedown(function() {
	            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
	          }).click(function() {
	            input.focus();
	 
	            // Close if already visible
	            if ( wasOpen ) {
	              return;
	            }
	 
	            // Pass empty string as value to search for, displaying all results
	            input.autocomplete( "search", "" );
	          });
	      },
	 
	      _source: function( request, response ) {
	        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
	        response( this.element.children( "option" ).map(function() {
	          var text = $( this ).text();
	          if ( this.value && ( !request.term || matcher.test(text) ) )
	            return {
	              label: text,
	              value: text,
	              option: this,
	            };
	        }) );
	      },
	 
	      _removeIfInvalid: function( event, ui ) {
	    	  
	    	
	 
	        // Selected an item, nothing to do
	        if ( ui.item ) {
	        	
	          return;
	        }
	 
	        // Search for a match (case-insensitive)
	        var value = this.input.val(),
	          valueLowerCase = value.toLowerCase(),
	          valid = false;
	        this.element.children( "option" ).each(function() {
	          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
	            this.selected = valid = true;
	            
	           
	          
	            return false;
	          }
	        });
	 
	        // Found a match, nothing to do
	        if ( valid ) {
	        	
	          return;
	        }
	       
	       
	        
	        // Remove invalid value
	        this.input
	          .val( "Seleccione un item..." )
	          .attr( "title", value + " no coincide ningun registro" )
	          .tooltip( "open" );
	        this.element.val( "" );
	        this._delay(function() {
	          this.input.tooltip( "close" ).attr( "title", "" );
	         
	        }, 2500 );
	        this.input.data( "ui-autocomplete" ).term = "";
	      },
	 
	      _destroy: function() {
	        this.wrapper.remove();
	        this.element.show();
	      }
	    });
	  })( jQuery );
	  
	  
	  
	  
	  
	var globalSuma=0;
	(function( $ ) {
	    $.widget( "custom.combobox", {
	      _create: function() {
	        this.wrapper = $( "<span>" )
	          .addClass( "custom-combobox" )
	          .insertAfter( this.element );
	 
	        this.element.hide();
	        this._createAutocomplete();
	        this._createShowAllButton();
	      },
	 
	      _createAutocomplete: function() {
	        var selected = this.element.children( ":selected" ),
	        value = selected.val() ? selected.text() : "Seleccione un item...";
	 
	        this.input = $( "<input>" )
	          .appendTo( this.wrapper )
	          .val( value )
	          .attr( "title", "" )
	          .addClass( "" )
	          .autocomplete({
	            delay: 0,
	            minLength: 0,
	            select: function(){
	            	if(globalSuma>1){
	            	$('#bloqueEspera').trigger('click');
	        		$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoBancoValorModificacion";
	        		$('#formModificacion')[0].submit();
	            	}
	            },
	            source: $.proxy( this, "_source" )
	          })
	          .tooltip({
	            tooltipClass: "ui-state-highlight"
	          });
	 
	        this._on( this.input, {
	          autocompleteselect: function( event, ui ) {
	            ui.item.option.selected = true;
	            this._trigger( "select", event, {item: ui.item.option});
	          },autocompletechange: "_removeIfInvalid"});
	      	},
	 
	      _createShowAllButton: function() {
	        var input = this.input,
	          wasOpen = false;
	 
	        $( "<a>" )
	          .attr( "tabIndex", -1 )
	          .tooltip()
	          .appendTo( this.wrapper )
	          .button({
	            icons: {
	              primary: "ui-icon-triangle-1-s"
	            },
	            text: false
	          })
	          .removeClass( "ui-corner-all" )
	          .removeClass("ui-button")
	          .addClass( "custom-combobox-toggle ui-corner-right" )
	          .mousedown(function() {
	            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
	          }).click(function() {
	            input.focus();
	 
	            // Close if already visible
	            if ( wasOpen ) {
	              return;
	            }
	 
	            // Pass empty string as value to search for, displaying all results
	            input.autocomplete( "search", "" );
	          });
	      },
	 
	      _source: function( request, response ) {
	        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
	        response( this.element.children( "option" ).map(function() {
	        	
	        	globalSuma++;
	        	
	          var text = $( this ).text();
	          if ( this.value && ( !request.term || matcher.test(text) ) )
	            return {
	              label: text,
	              value: text,
	              option: this,
	            };
	        }) );
	      },
	 
	      _removeIfInvalid: function( event, ui ) {
	 
	        // Selected an item, nothing to do
	        if ( ui.item ) {
	          return;
	        }
	 
	        // Search for a match (case-insensitive)
	        var value = this.input.val(),
	          valueLowerCase = value.toLowerCase(),
	          valid = false;
	        this.element.children( "option" ).each(function() {
	        	  
	          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
	            this.selected = valid = true;
	            return false;
	          }
	        });
	 
	        // Found a match, nothing to do
	        if ( valid ) {
	          return;
	        }
	        
	        $('#bloqueEspera').trigger('click');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/seleccionoSegmentoModificacion";
			$('#formModificacion')[0].submit();
	        
	 
	        // Remove invalid value
	        this.input
	        .val( "Seleccione un item..." )
	          .attr( "title", value + " no coincide ningun registro" )
	          .tooltip( "open" );
	        this.element.val( "" );
	        this._delay(function() {
	        	 $('#bloqueEspera').trigger('click');
	         
	        }, 2500 );
	        this.input.data( "ui-autocomplete" ).term = "";
	      },
	 
	      _destroy: function() {
	        this.wrapper.remove();
	        this.element.show();
	      }
	    });
	  })( jQuery );

	</script>
</script>

</body>
</html>