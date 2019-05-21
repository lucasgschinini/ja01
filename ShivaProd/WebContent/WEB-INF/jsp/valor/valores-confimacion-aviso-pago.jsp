
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.SiNoEnum" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="valor.confirmacionAvisoPago.titulo"/></title>
	<meta name="Alta de Valores" content="Confirmacion de Aviso de Pago">
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<style>
div.DTTT_container {
	float: right;
}
</style>	
<script>
 	function mostarTipoValor(referenciaCombo) {
		var tipoValorSeleccionado = referenciaCombo.value;
		var tipoAcceso = <c:out value = "${valorDto.modifAnaRecha}"/>;
		
		switch (tipoValorSeleccionado) {
		case "2": //Bol. Depósito p/Cheque
			bloqueNroDocumentoContable.style.display = 'none';
			bloqueNroDocumentoContableError.style.display = 'none';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'block';
			bloqueNroCheque.style.display = 'block';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'none';
			bloqueCheckRecibido.style.display = 'none';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'none';
			//}
			bloqueFechaParaCheques.style.display = 'block';
			bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "3": //Bol. Depósito p/Cheque Diferido
			bloqueNroDocumentoContable.style.display = 'none';
			bloqueNroDocumentoContableError.style.display = 'none';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'block';
			bloqueNroCheque.style.display = 'block';
			bloqueFechaVencimiento.style.display = 'block';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'none';
			bloqueCheckRecibido.style.display = 'none';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'none';
			//}
				bloqueFechaEmision.style.display = 'block';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "4": //Bol. Depósito p/Efectivo
			bloqueNroDocumentoContable.style.display = 'none';
			bloqueNroDocumentoContableError.style.display = 'none';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'none';
			bloqueNroCheque.style.display = 'none';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'none';
			bloqueCheckRecibido.style.display = 'none';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'none';
			//}
				bloqueFechaEmision.style.display = 'none';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "5": //Cheque
			bloqueNroDocumentoContable.style.display = 'block';
			bloqueNroDocumentoContableError.style.display = 'block';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'block';
			bloqueBancoOrigen.style.display = 'block';
			bloqueNroCheque.style.display = 'block';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'block';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'block';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "6": //Cheque Diferido
			bloqueNroDocumentoContable.style.display = 'block';
			bloqueNroDocumentoContableError.style.display = 'block';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'block';
			bloqueBancoOrigen.style.display = 'block';
			bloqueNroCheque.style.display = 'block';
			bloqueFechaVencimiento.style.display = 'block';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'block';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'block';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "7": //Efectivo
			bloqueNroDocumentoContable.style.display = 'block';
			bloqueNroDocumentoContableError.style.display = 'block';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'block';
			bloqueBancoOrigen.style.display = 'none';
			bloqueNroCheque.style.display = 'none';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'block';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'none';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "8": //Transferencia
			bloqueNroDocumentoContable.style.display = 'block';
			bloqueNroDocumentoContableError.style.display = 'block';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'none';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'block';
			bloqueNroCheque.style.display = 'none';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'block';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'none';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'block';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'none';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'none';
			break;
		case "9": //Interdeposito
			bloqueNroDocumentoContable.style.display = 'none';
			bloqueNroDocumentoContableError.style.display = 'block';
			bloqueAcuerdo.style.display = 'block';
			bloqueNroBoleta.style.display = 'none';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'none';
			bloqueNroCheque.style.display = 'none';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'block';
			bloqueTipoImpuesto.style.display = 'none';
// 			bloqueChequeReemplazar.style.display = 'none';
			bloqueReciboConstancia.style.display = 'none';
			bloqueCuitIbb.style.display = 'none';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'none';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'none';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		case "10": //Retencion / Impuesto
			bloqueNroDocumentoContable.style.display = 'none';
			bloqueNroDocumentoContableError.style.display = 'none';
			bloqueAcuerdo.style.display = 'none';
			bloqueNroBoleta.style.display = 'none';
			bloqueFechaDeposito.style.display = 'none';
			bloqueBancoOrigen.style.display = 'none';
			bloqueNroCheque.style.display = 'none';
			bloqueFechaVencimiento.style.display = 'none';
			bloqueNroReferencia.style.display = 'none';
			bloqueInterdeposito.style.display = 'none';
			bloqueTipoImpuesto.style.display = 'block';
// 			bloqueChequeReemplazar.style.display = 'block';
			bloqueReciboConstancia.style.display = 'block';
			bloqueComprobantes.style.display = 'block';
			bloqueCheckRecibido.style.display = 'block';
			//if(tipoAcceso){
				bloqueComprobantesAgregar.style.display = 'block';
			//}
				bloqueFechaEmision.style.display = 'none';
				bloqueFechaNotificacionDisponibilidadRetiroValor.style.display = 'block';
			break;
		}
	};
	
	function mostarTipoImpuesto(referenciaCombo) {
		var tipoImpuestoSeleccionado = referenciaCombo.value;
		
		switch (tipoImpuestoSeleccionado) {
		case "1":
			bloqueCuitIbb.style.display = 'block';
			break;
		case "2":
			bloqueCuitIbb.style.display = 'none';
			break;
		case "3":
			bloqueCuitIbb.style.display = 'none';
			break;
		case "4":
			bloqueCuitIbb.style.display = 'none';
			break;
		case "5":
			bloqueCuitIbb.style.display = 'none';
			break;
		case "6":
			bloqueCuitIbb.style.display = 'none';
			break;
		}
	};
	
	
	
	window.onload = function() {
		// Muestro los campos de valores de acuerdo al valor seleccionado por defecto
		mostarTipoValor(document.getElementById('idTipoValor'));
		mostarTipoImpuesto(document.getElementById('idTipoImpuesto'));
		//buscarClienteLegadoSiebel($('#inputCodCliente').val().split(" ").join(""));
		
		<%if (request.getAttribute("imprimibleArchivo")!=null) {
			String imprimibleArchivo = (String) request.getAttribute("imprimibleArchivo");
			if ("true".equalsIgnoreCase(imprimibleArchivo)) {%>
				window.location ="${pageContext.request.contextPath}/abrir-documento-plano";
			<%}
		}%>
		
// 		var prevCliente = 'prevCliente';
// 		inicializarTablaConExportacionAXlsConNombreOrdenada(prevCliente, 'AvisoDePagoClienteSeleccionado', [[ 1, "asc"]]);
		
		var SCROLL_Y = '300px';
		var DISPLAY_LENGTH = 100;
		
		var tablas = {
				prevCliente: null
		    };
		
		
		
		var prevCliente = {
	    		dom : 'rt',
				"sScrollX" : true,
				"scrollY" : SCROLL_Y,
				"bScrollCollapse" : true,
				"iDisplayLength" : DISPLAY_LENGTH,
				"bSortClasses" : false,
				"aoColumns" : [
						{
							"mData" : "cuit"
						},
						{
							"mData" : "idClienteLegado"
						},
						{
							"mData" : "empresasAsociadas"
						},
						{
							"mData" : "razonSocial"
						},
						{
							"mData" : "origen"
						},
						{
							"mData" : "descripcionAgenciaNegocio"
						},
						{
							"mData" : "codigoHolding"
						},
						{
							"mData" : "segmentoAgencia"
						}
				]
			};
		
		tablas.prevCliente = $("#prevCliente").dataTable(prevCliente);
		
		var mensajeValorDuplicado = '<c:out value = "${valorDto.mensajeDuplicadoError}"/>';
		if(!isUndefinedNullDashEmptyOrFalse(mensajeValorDuplicado)){
			$('#divAlertErrorFoot').show();
			$('#alertErrorFoot').text(mensajeValorDuplicado);
		} else {
			$('#divAlertErrorFoot').hide();
			$('#alertErrorFoot').text('');
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
							<div class="title"><spring:message code="valor.confirmacionAvisoPago.titulo.pagina"/></div>
							
							<form:form id="formConfirmacion" commandName="valorDto" enctype="multipart/form-data">
							
								<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>
								
								<form:hidden path="operation" id="operation"/>
								<form:hidden path="idValor" id="idValor"/>
								<form:hidden path="timeStamp"/>
								<form:hidden path="idTipoValor" id="idTipoValor" />
								<form:hidden path="idOrigen" id="idOrigen" />
								<form:hidden path="idEmpresa" id="idEmpresa"/>
								<form:hidden path="idSegmento" id="idSegmento"/>
								<form:hidden path="idOrigen" id="idOrigen"/>
								<form:hidden path="idAcuerdo" id="idAcuerdo"/>
								<form:hidden path="idBancoDeposito" id="idBancoDeposito"/>
								<form:hidden path="idNroCuenta" id="idNroCuenta"/>		
								<form:hidden path="idBancoOrigen" id="idBancoOrigen"/>	
								<form:hidden path="idTipoImpuesto" id="idTipoImpuesto"/>
								<form:hidden path="idProvincia" id="idProvincia"/>
								<form:hidden path="idAnalista" id="idAnalista"/>
								<form:hidden path="idCopropietario" id="idCopropietario"/>
								<form:hidden path="idMotivo" id="idMotivo"/>
								<form:hidden path="idTipoComprobante" id="idTipoComprobante"/>
								<form:hidden path="idLetraComprobante" id="idLetraComprobante"/>
								
								<form:hidden path="empresa" id="empresa"/>
								<form:hidden path="segmento" id="segmento"/>
								<form:hidden path="nombreAnalista" id="nombreAnalista"/>
								<form:hidden path="mailAnalista" id="mailAnalista"/>
								<form:hidden path="mailCopropietario" id="mailCopropietario"/>
								<form:hidden path="tipoValor" id="tipoValor"/>
								<form:hidden path="fechaEmision" id="fechaEmision"/>
								
								
								<div class="pagos_anticipos">
									<p><strong><spring:message code="valor.datosGenerales"/></strong></p>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa"><spring:message code="valor.empresa"/></label>
											<div class="controls ">
												<input id="empresa1" name="empresa1" type="text" value="${valorDto.empresa}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="segmento"><spring:message code="valor.segmento"/></label>
											<div class="controls">
												<input id="segmento1" name="segmento1" type="text" value="${valorDto.segmento}" class="input" readonly>
											</div>
										</div>
									</div>
									
									<p>
										<strong><spring:message	code="conf.cobro.clientes.seleccionados" /></strong>
									</p>
									
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
															<td>${valorDto.cliente.idClienteLegado}</td>
															<td>${valorDto.cliente.empresasAsociadas}</td>
															<td>${valorDto.cliente.razonSocial}</td>
															<td>${valorDto.cliente.origen}</td>
															<td>${valorDto.cliente.segmentoAgencia}</td>
															<td>${valorDto.cliente.descripcionHolding}</td>
															<td>${valorDto.cliente.agenciaNegocio}</td>
															<form:input path="cliente.cuit" id="cuit" type="hidden"/>
															<form:input path="cliente.idClienteLegado" id="idClienteLegado" type="hidden"/>
															<form:input path="cliente.empresasAsociadas" id="empresasAsociadas" type="hidden"/>
															<form:input path="cliente.razonSocial" id="razonSocial" type="hidden"/>
															<form:input path="cliente.origen" id="origen" type="hidden"/>
															<form:input path="cliente.descripcionAgenciaNegocio" id="descripcionAgenciaNegocio" type="hidden"/>
															<form:input path="cliente.codigoHolding" id="codigoHolding" type="hidden"/>
															<form:input path="cliente.segmentoAgencia" id="segmentoAgencia" type="hidden"/>
														</tr> 
													</tbody>
												</table>
											</div>
										</div>
										<br>								   
									<div class="row">	
										<div class="span3">
											<label class="control-label" for="email"><spring:message code="valor.email"/></label>
											<div class="controls">
												<input id="email" name="email" type="email" class="input" required value="${valorDto.email}" readonly>
											</div>
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
													<a href="sip:${valorDto.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
													<a href="mailto:${valorDto.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
												</div>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="copropietario"><spring:message code="valor.copropietario"/></label>
											<div class="controls" style="white-space: nowrap;">
												<img alt="Usuario"  src="${valorDto.urlFotoUsuario(valorDto.idCopropietario)}"
													style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 6px; margin-top: 1px; margin-left: 1px; float: left; margin-bottom: 9px;" onerror='src="${pageContext.request.contextPath}/img/default2.png"'>						
														
												<input class="span2" id="copropietario" name="copropietario" type="text" value="${valorDto.copropietario}" readonly style="margin-bottom: 2px; width: 164px;">
												<div class="row">
													<a href="sip:${valorDto.mailCopropietario}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
													<a href="mailto:${valorDto.mailCopropietario}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- SEGUNDA PARTE -->
								<div class="pagos_anticipos">
									<p><strong><spring:message code="valor.avisoPago"/></strong></p>
							
									<div class="row">
										<div class="span3">
											<label class="control-label" for="tipoValor"><spring:message code="valor.tipoAvisoPago"/></label>
											<input id="tipoValorStr" name="tipoValorStr" type="text" value="${valorDto.tipoValor}" class="input" readonly>
										</div>
										<div class="span3"> 
	 										<label class="control-label" for="importe"><spring:message code="valor.importe"/></label> 
											<div class="controls"> 
												<input id="importe" name="importe" type="text" class="input" value="${valorDto.importe}" readonly> 
											</div> 
										</div>
										<div class="span3">
											<label class="control-label" for="estadoValor"><spring:message code="valor.estado"/></label> 
											<input id="estadoValor" name="estadoValor" type="text" value="${valorDto.estadoValor}" class="input" readonly>
										</div>
									</div>
									<div class="row">
										<div class="span3"> 
	 										<label class="control-label" for="motivo"><spring:message code="valor.motivo"/></label> 
											<div class="controls"> 
												<input id="motivo" name="motivo" type="text" class="input" value="${valorDto.motivo}" readonly> 
											</div> 
										</div>
										<div class="span3"> 
	 										<label class="control-label" for="operacionAsociada"><spring:message code="valor.operacionAsociada"/></label> 
											<div class="controls"> 
												<input id="operacionAsociada" name="operacionAsociada" type="text" class="input" value="${valorDto.operacionAsociada}" readonly> 
											</div> 
										</div>
										<div class="span3" id="bloqueNroDocumentoContable" style="display:none">
											<label class="control-label" for="numeroDocumentoContable"><span class="rojo">* </span><spring:message code="valor.nroDocumentoContable"/></label> 
											<div class="controls"> 
												<input id="numeroDocumentoContable" name="numeroDocumentoContable" type="text" class="input" value="${valorDto.numeroDocumentoContable}"> 
											</div>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span3">&nbsp;</div>
										<div class="span3">&nbsp;</div>
										<div class="span3" id="bloqueNroDocumentoContableError" style="display:none">
											<form:errors path="numeroDocumentoContable" cssClass="error" />
										</div>
									</div>
									
									<!-- VARIABLES -->
									<div class="row" id="bloqueAcuerdo" style="display:none">
										<div class="span3">
											<label class="control-label" for="acuerdo"><spring:message code="valor.acuerdo"/></label> 
											<div class="controls"> 
												<input id="acuerdo" name="acuerdo" type="text" class="input" value="${valorDto.acuerdo}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="bancoDeposito"><spring:message code="valor.bancoDeposito"/></label> 
											<div class="controls"> 
												<input id="bancoDeposito" name="bancoDeposito" type="text" class="input" value="${valorDto.bancoDeposito}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="numeroCuenta"><spring:message code="valor.nroCuenta"/></label> 
											<div class="controls"> 
												<input id="numeroCuenta" name="numeroCuenta" type="text" class="input" value="${valorDto.numeroCuenta}" readonly> 
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="span3" id="bloqueNroBoleta" style="display:none">
											<label class="control-label" for="numeroBoleta"><spring:message code="valor.nroBoleta"/></label> 
											<div class="controls"> 
												<input id="numeroBoleta" name="numeroBoleta" type="text" class="input" value="${valorDto.numeroBoleta}" readonly> 
											</div>
										</div>
										<div class="span3" id="bloqueFechaDeposito" style="display:none">
											<label class="control-label" for="fechaDeposito"><spring:message code="valor.fechaDeposito"/></label> 
											<div class="controls"> 
												<input id="fechaDeposito" name="fechaDeposito" type="text" class="input" value="${valorDto.fechaDeposito}" readonly> 
											</div>
										</div>
									</div>
								
									<div class="row">
										<div class="span3" id="bloqueBancoOrigen" style="display:none">
											<label class="control-label" for="bancoOrigen"><spring:message code="valor.bancoOrigen"/></label> 
											<div class="controls"> 
												<input id="bancoOrigen" name="bancoOrigen" type="text" class="input" value="${valorDto.bancoOrigen}" readonly> 
											</div>
										</div>
										<div class="span3" id="bloqueNroCheque" style="display:none">
											<label class="control-label" for="numeroCheque"><spring:message code="valor.nroCheque"/></label> 
											<div class="controls"> 
												<input id="numeroCheque" name="numeroCheque" type="text" class="input" value="${valorDto.numeroCheque}" readonly> 
											</div>
										</div>
										<div class="span3" id="bloqueFechaVencimiento" style="display:none">
											<label class="control-label" for="fechaVencimiento"><spring:message code="valor.fechaVencimiento"/></label> 
											<div class="controls"> 
												<input id="fechaVencimiento" name="fechaVencimiento" type="text" class="input" value="${valorDto.fechaVencimiento}" readonly> 
											</div>
										</div>
									</div>
								
									<div class="row" id="bloqueNroReferencia" style="display:none">
										<div class="span3">
											<label class="control-label" for="numeroReferencia"><spring:message code="valor.nroReferencia"/></label> 
											<div class="controls"> 
												<input id="numeroReferencia" name="numeroReferencia" type="text" class="input" value="${valorDto.numeroReferencia}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaTransferencia"><spring:message code="valor.fechaTransferencia"/></label> 
											<div class="controls"> 
												<input id="fechaTransferencia" name="fechaTransferencia" type="text" class="input" value="${valorDto.fechaTransferencia}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="cuit"><spring:message code="valor.cuit"/></label> 
											<div class="controls"> 
												<input id="cuit" name="cuit" type="text" class="input" value="${valorDto.cuit}" readonly> 
											</div>
										</div>
									</div>
								
									<div id="bloqueInterdeposito" style="display:none">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="fechaInterdeposito"><spring:message code="valor.fechaInterdeposito"/></label> 
												<div class="controls"> 
													<input id="fechaInterdeposito" name="fechaInterdeposito" type="text" class="input" value="${valorDto.fechaInterdeposito}" readonly> 
												</div>
											</div>
										</div>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="numeroInterdepositoCdif"><spring:message code="valor.nroInterdepositoCdif"/></label> 
												<div class="controls"> 
													<input id="numeroInterdepositoCdif" name="numeroInterdepositoCdif" type="text" class="input" value="${valorDto.numeroInterdepositoCdif}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="codOrganismo"><spring:message code="valor.codOrganismo"/></label> 
												<div class="controls"> 
													<input id="codOrganismo" name="codOrganismo" type="text" class="input" value="${valorDto.codOrganismo}" readonly> 
												</div>
											</div>
										</div>
									</div>
								
									<div class="row" id="bloqueTipoImpuesto" style="display:none">
										<div class="span3">
											<label class="control-label" for="tipoImpuesto"><spring:message code="valor.tipoImpuesto"/></label> 
											<div class="controls"> 
												<input id="tipoImpuesto" name="tipoImpuesto" type="text" class="input" value="${valorDto.tipoImpuesto}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="nroConstancia"><spring:message code="valor.nroConstancia"/></label> 
											<div class="controls"> 
												<input id="nroConstancia" name="nroConstancia" type="text" class="input" value="${valorDto.nroConstancia}" readonly> 
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaEmision"><spring:message code="valor.fechaEmision"/></label> 
											<div class="controls"> 
												<input id="fechaEmision" name="fechaEmision" type="text" class="input" value="${valorDto.fechaEmision}" readonly> 
											</div>
										</div>
									</div>
									<div id="bloqueFechaEmision">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="fechaEmision"><spring:message code="valor.aviso.pago.fecha.emision"/></label>
												<input id="fechaEmision" name="fechaEmision" type="text" value="${valorDto.fechaEmision}" class="input" readonly>
											</div>
										</div>
									</div>
								
									<div id="bloqueReciboConstancia" style="display:none">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="reciboPreImpreso"><spring:message code="valor.recibo"/></label> 
												<div class="controls"> 
													<input id="reciboPreImpreso" name="reciboPreImpreso" type="text" class="input" value="${valorDto.reciboPreImpreso}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="fechaIngresoRecibo"><spring:message code="valor.fechaIngresoRecibo"/></label> 
												<div class="controls"> 
													<input id="fechaIngresoRecibo" name="fechaIngresoRecibo" type="text" class="input" value="${valorDto.fechaIngresoRecibo}" readonly> 
												</div>
											</div>
										</div>
									</div>
									
									<div id="bloqueConstancia" style="display:none">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="constancia"><spring:message code="valor.constancia"/></label> 
												<div class="controls"> 
													<input id="constancia" name="constancia" type="text" class="input" value="${valorDto.constancia}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="fechaConstancia"><spring:message code="valor.fechaConstancia"/></label> 
												<div class="controls"> 
													<input id="fechaConstancia" name="fechaConstancia" type="text" class="input" value="${valorDto.fechaConstancia}" readonly> 
												</div>
											</div>
										</div>
									</div>
								
									<div id="bloqueCuitIbb" style="display:none">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="cuit"><spring:message code="valor.cuit"/></label> 
												<div class="controls"> 
													<input id="cuit" name="cuit" type="text" class="input" value="${valorDto.cuitIbb}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="provincia"><spring:message code="valor.provincias"/></label> 
												<div class="controls"> 
													<input id="provincia" name="provincia" type="text" class="input" value="${valorDto.provincia}" readonly> 
												</div>
											</div>
										</div>
										<p><strong><spring:message code="valor.facturaRelacionada"/></strong> </p>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="tipoComprobante"><spring:message code="valor.tipoComprobante"/></label> 
												<div class="controls"> 
													<input id="tipoComprobante" name="tipoComprobante" type="text" class="input" value="${valorDto.tipoComprobante}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="letraComprobante"><spring:message code="valor.letraComprobante"/></label> 
												<div class="controls"> 
													<input id="letraComprobante" name="letraComprobante" type="text" class="input" value="${valorDto.letraComprobante}" readonly> 
												</div>
											</div>
											<div class="span3">
												<label class="control-label" for="numeroLegalComprobante"><spring:message code="valor.nroLegal"/></label> 
												<div class="controls"> 
													<input id="numeroLegalComprobante" name="numeroLegalComprobante" type="text" class="input" value="${valorDto.numeroLegalComprobante}" readonly> 
												</div>
											</div>
										</div>
									</div>
									<div id="bloqueFechaNotificacionDisponibilidadRetiroValor">
										<div class="row">
											<div class="span3">
												<label class="control-label" for="fechaNotificacionDisponibilidadRetiroValor"><spring:message code="valor.aviso.pago.fecha.notificacion"/></label>
												<input id="fechaNotificacion" name="fechaNotificacionDisponibilidadRetiroValor" type="text" value="${valorDto.fechaNotificacionDisponibilidadRetiroValor}" class="input" readonly>
											</div>
										</div>
									</div>	
									
									<div id="bloqueObservaciones" style="display:block">
										<div class="row" style="margin-top: 20px; margin-bottom: 10px">
											<div class="span9">
												<label class="control-label" for="observaciones"><spring:message code="valor.observaciones"/></label>
												<textarea class="field span9" id="observaciones" name="observaciones"  maxlength="250" readonly>${valorDto.observaciones}</textarea>
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
									<p><span class="rojo">* </span><strong><spring:message code="valor.comprobantes"/></strong></p>
									
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
									<c:if test="${valorDto.comprobantePathVacioModif}">
									<div class="row rowError" >
										<div class="span3" style="color:red;font-size:9px;"><spring:message code="error.obligatorio"/></div>
									</div>
									</c:if>
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="errorArchivoVacio" cssClass="error" />
										</div>
									</div>
<%-- 									<c:if test="${valorDto.errorArchivoVacio}"> --%>
<!-- 									<div class="row rowError" > -->
<%-- 										<div class="span3" style="color:red;font-size:9px;"><spring:message code="valor.error.archivoVacio"/></div> --%>
<!-- 									</div> -->
<%-- 									</c:if> --%>
									<div class="row" style="margin-top: 0px; margin-bottom: 5px">
										<div class="span9">
											<label class="control-label" for="descripcionComprobante"><spring:message code="valor.descripcionComprobante"/></label>
											<textarea class="field span9" id="descripcionComprobante" name="descripcionComprobante" 
												maxlength="150">${valorDto.descripcionComprobante}</textarea>
										</div>
									</div>
									<c:if test="${valorDto.comprobanteDescripcionVacioModif}">
									<div class="row rowError" >
										<div class="span3" style="color:red;font-size:9px;"><spring:message code="error.obligatorio"/></div>
									</div>
									</c:if>
									<div class="row" align="right" style="margin-top: 15px; margin-bottom: 15px; width: 699px;">
										<button type="button" class="btn btn-primary btn-small" id="btnAdjuntar" name="btnAdjuntar">
											<i class="icon-white icon-upload"></i> <spring:message code="valor.botonAdjuntar"/></button>
									</div>
								</div>
								<div id="bloqueComprobantes" style="display: none">
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
														<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<c:if test="${(sessionScope.loginUser.idUsuario eq compro.usuarioCreacion)}">
															<button id="btnEliminarComprobante" type="button" class="btn btn-xs btn-link" title="Eliminar documento"
															onclick="eliminarComprobante(${compro.id});">
																<i class="icon-trash bigger-120"></i>
															</button>
															</c:if>
														</div>
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
									<c:if test="${valorDto.errorComprobanteVacioModif}">
									<div class="row rowError">
										<div class="span9" style="color:red;font-size:9px;"><br><spring:message code="valor.comprobante.vacio"/></div>
									</div>
									</c:if>
								</div>
								
								<!-- FIN SECCION COMPROBANTES -->

								<div class="row" style="margin-top: 0px; margin-bottom: 0px">
									<div class="span6" id="bloqueCheckRecibido" style="display: none">
										<label class="checkbox inline"> 
											<input id="documentacionOriginalRecibida" name="documentacionOriginalRecibidaBool" type="checkbox" 
												<c:if test="${valorDto.chkDocumentacionOriginalDocumentacion}">checked</c:if>
												/>
												<spring:message code="valor.documentacionOriginalRecibida"/>
										</label>
									</div>
								</div>
		
		
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observaciones"><spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="observacionConfirmacion" name="observacionConfirmacion" 
											maxlength="250">${valorDto.observacionConfirmacion}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span9">
										<form:errors path="observacionConfirmacion" cssClass="error" />
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar" type="button" onclick="javascript:cancelar();">
											<i class="icon-white icon-remove"></i><spring:message code="valor.botonCancelar"/>
										</button>
										<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button" onclick="javascript:confirmar();">
											<i class="icon-white icon-ok"></i><spring:message code="valor.botonConfirmar"/>
										</button>
										<button class="btn btn-primary btn-small" id="btnRechazar" name="btnRechazar" type="button" onclick="javascript:rechazar();">
											<i class="icon-white icon-remove"></i><spring:message code="valor.botonRechazar"/>
										</button>
										<div class="alert alert-error" align="center" style="width:279px; margin-top:20px; margin-left:auto; margin-right:auto; text-align:left;hight:48px; display: none;" id="divAlertErrorFoot">
											<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br/>
											<span id="alertErrorFoot"></span>
										</div>
									</div>
								</div>
						</div> 											<!-- end pagos 		-->
						</form:form>
					</div> 												<!-- end payment 	-->
				</div> 													<!-- end inside 	-->
			</div> 														<!-- end content 	-->
		</div> 															<!-- end wrap 		-->
	</div> 																<!-- end main 		-->
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
</div> 																	<!-- end container 	-->

<script type="text/javascript">


	$('#fechaIngresoRecibo').datepicker();

	function cancelar() {
		$('#bloqueEspera').trigger('click');
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/regresar-bandeja-de-entrada";
		$('#formConfirmacion')[0].submit();	
	};
	
	function rechazar() {
		$('#bloqueEspera').trigger('click');
		$('#btnRechazar').attr('disabled', true);
		$('#operation').val('<%=Constantes.RECHAZAR_AVISO_PAGO%>');
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/procesar-rechazo-aviso-pago";
		$('#formConfirmacion')[0].submit();	
	};
	
	function confirmar() {
		$('#bloqueEspera').trigger('click');
		$('#btnAceptar').attr('disabled', true);
		$('#operation').val('<%=Constantes.CONFIRMAR_AVISO_PAGO%>');
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/procesar-confirmacion-aviso-pago";
		$('#formConfirmacion')[0].submit();
	};

	$('#btnAdjuntar').click(function() {
		$('#bloqueEspera').trigger('click');
		$('#operation').val('<%=Constantes.SUBIR_COMPROBANTE%>');
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/procesar-alta-comprobante-aviso-pago";
		$('#formConfirmacion')[0].submit();
	});
	
	function eliminarComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/procesar-eliminar-comprobante-aviso-pago";
		$('#formConfirmacion')[0].submit();
	};
	
	function abrirComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formConfirmacion')[0].action="${pageContext.request.contextPath}/procesar-abrir-comprobante-aviso-pago";
		$('#formConfirmacion')[0].submit();
	};
	
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
			$( "#codCliente" ).val(codigoClienteLegado);
	        return false;
	     }
	 });

	$("#inputCodCliente").keydown(function(event) {
		$("#wsConsultaClienteSiebel").css('display', 'none');
		$("#codClienteSiebel").val("");
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
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			var codigoCliente = $('#inputCodCliente').val().split(" ").join("");
			if (codigoCliente!="") {
				buscarClienteLegadoSiebel(codigoCliente);
			} else {
				$("#wsConsultaClienteSiebel").css('display', 'inline-block');
				$("#wsConsultaClienteSiebel").html('<spring:message code="error.obligatorio"/>');
			};
		}
		if(keycode == '9'){
			var codigoCliente = $('#inputCodCliente').val().split(" ").join("");
			if (codigoCliente!="") {
				buscarClienteLegadoSiebel(codigoCliente);
			};
		}
		event.stopPropagation();
	});
	
 	function buscarClienteLegadoSiebel(codCliente) {
 		var codigoClienteLegado = codCliente.split("-")[0].split(" ").join("");
 		$('#inputCodCliente').val(codigoClienteLegado);
	
 		//$('#bloqueEspera').trigger('click');
 		var url = '${pageContext.request.contextPath}/consultarClienteSiebelValor?method=load&ajax=true&nocache='+Math.random();
 		var param = "&codCliente="+ codigoClienteLegado;
	
 		$.get(url+param, function(data) {
 			if (data.toLowerCase().indexOf("error") >= 0 || data.toLowerCase().indexOf("disponible") >= 0) {
 				$("#wsConsultaClienteSiebel").css('display', 'inline-block');
 				$("#wsConsultaClienteSiebel").html(data);
 			} else {
 				if (data.length == 0) {
 					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
 					$("#wsConsultaClienteSiebel").html('<spring:message code="boleta.alta.mensaje.siebel.vacio"/>');
 				} else {
 					var dividoEnDos = data.split("|");
 					var res = dividoEnDos[0].split(";");
					
 					$("#codClienteAgrupador").prop("readonly",false);
 					$("#codClienteAgrupador").val(res[0]);
 					$("#codClienteAgrupador").prop("readonly",true);
				 		
 					$("#razonSocialClienteAgrupador").prop("readonly",false);
 					$("#razonSocialClienteAgrupador").val(res[1]);
 					$("#razonSocialClienteAgrupador").prop("readonly",true);

 					$("#numeroHolding").val(res[2]);								
 				 	$("#nombreHolding").val(res[3]);
 				}
 			}
 			ocultarBloqueEspera();
 		});
 	};

</script>
</body>
</html>