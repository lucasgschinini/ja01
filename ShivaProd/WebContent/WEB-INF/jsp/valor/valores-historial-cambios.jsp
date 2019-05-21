<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><spring:message code="valor.historialModificacion.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/valor/valores-historial-cambios.js"></script>
<style>
 
 div.DTTT_container {
float: right;
}
.tablaResultadoHist {
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px;
		border-radius: 5px;
		border:1px solid #E6E6E6;
		background-color:#FFFFFF;
		border-spacing: 0;
	}
	
	
.tablaResultadoHist th {
		background: #F7F7F7; 
		color: #797979; 
		font: normal 11px Tahoma; 
		margin: 1px 3px;
		vertical-align: middle;
		padding-left: 5px;
		padding-right: 5px;
	}
	
.tablaResultadoHist td {
		text-align: center; 
	 	border-top: 1px solid #e1e1e1; 
	 	vertical-align: middle;
		padding: 0px 3px 0px 3px;
		font: normal 11px Tahoma;
		color: #787878;
		/*white-space:nowrap;*/
	}		


</style>	
 <script>
 
	
	// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
	// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
	// En IE8 no hace falta llamar a fnCellRende
	var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
		return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [], [1], [3]);
	};
	// Tratamiento de tablas
	window.onload = function() {
		var idTabla = 'resultadoBusquedaHistorial';
		inicializarTablaConExportacionAPdfyXlsOcultarSearchFiltroInfo(idTabla, (window.DOMParser) ? fnCellRender : null);
		agregarOrdenFecha(idTabla);
		$('#' + idTabla).dataTable().fnSort( [ [2,'desc'] ] );
		cargarEstiloBicolorTablaResultado();
		
		// Muestro los campos de valores de acuerdo al valor seleccionado por defecto
		mostarTipoValor(document.getElementById('idTipoValor'));
		mostarTipoImpuesto(document.getElementById('idTipoImpuesto'));
		mostarConstancia(document.getElementById('idOrigen'));
		
		if(<c:out value = "${imprimibleArchivo}"/>) {
			window.location ="${pageContext.request.contextPath}/abrir-documento";
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
							<div class="title"><spring:message code="valor.historialModificacion.titulo.pagina"/></div>
							<form:form id="formHistorial" commandName="valorDto" >
							
							<form:hidden path="idValor" id="idValor"/>
							<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>
							
							<div class="pagos_anticipos">
								<p><strong><spring:message code="valor.datosGenerales"/></strong></p>
								<div class="row" id="bloqueIdDisplay" style="display:none">
										<div class="span3">
											<label class="control-label" for="idTipoValor"></label>
											<div class="controls ">
												<input id="idTipoValor" name="idTipoValor" type="text" value="${valorDto.idTipoValor}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idTipoImpuesto"></label>
											<div class="controls ">
												<input id="idTipoImpuesto" name="idTipoImpuesto" type="text" value="${valorDto.idTipoImpuesto}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idOrigen"></label>
											<div class="controls ">
												<input id="idOrigen" name="idOrigen" type="text" value="${valorDto.idOrigen}" class="input" readonly>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa"><spring:message code="valor.empresa"/></label>
											<div class="controls ">
												<input id="empresa" name="empresa" type="text" value="${valorDto.empresa}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="segmento"><spring:message code="valor.segmento"/></label>
											<div class="controls">
												<input id="segmento" name="segmento" type="text" value="${valorDto.segmento}" class="input" readonly>
											</div>
										</div>
									</div>
									<!-- Req 67475				u576779 - Se reemplazan los campos de clientes por combo de busqueda y grilla de clientes -->
<!-- 							
INICIO NUEVO COMBO CLIENTES -->
								<br/>
								<p>
									<strong><spring:message	code="conf.cobro.clientes.seleccionados" /></strong>
								</p>
								<div class="row">
									<div class="span9">
										<table id="clientesSeleccionados" class="tablaResultado">
											<thead>
												<tr>
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
								<br/>
<!-- 							FIN NUEVO COMBO CLIENTES -->
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
										<c:choose>
											<c:when test="${fn:trim(valorDto.nombreAnalista) ne ''}">
											<label class="control-label" for="analista"><spring:message code="valor.analista"/></label>
											<div class="controls" style="white-space: nowrap;">
												<img alt="Usuario" src="${valorDto.urlFotoUsuario(valorDto.idAnalista)}"
													style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 6px; margin-top: 1px; margin-left: 1px; float: left; margin-bottom: 9px;" onerror='src="${pageContext.request.contextPath}/img/default2.png"'>						
														
												<input class="span2" id=analista name="analista" type="text" value="${valorDto.nombreAnalista}" readonly style="margin-bottom: 2px; width: 164px;">
												<div class="row">													
													<a href="sip:${valorDto.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
													<a href="mailto:${valorDto.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
												</div>
											</div>
											</c:when>
											<c:otherwise><div class="controls" style="white-space: nowrap;"><br></div></c:otherwise>
										</c:choose>
										</div>
										<div class="span3">
											<label class="control-label" for="copropietario"><spring:message code="valor.copropietario"/></label>
											<div class="controls" style="white-space: nowrap;">
												<img alt="Usuario" src="${valorDto.urlFotoUsuario(valorDto.idCopropietario)}"
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
							
							<div class="pagos_anticipos">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="tipoValor"><spring:message code="valor.tipoValor"/></label>
										<input id="tipoValorStr" name="tipoValorStr" type="text" value="${valorDto.tipoValor}" class="input" readonly>
									</div>
									<div class="span3"> 
 										<label class="control-label" for="importe"><spring:message code="valor.importe"/></label> 
										<div class="controls"> 
											<input id="importe" name="importe" type="text" class="input" value="${valorDto.importe}" readonly> 
										</div> 
									</div>
									<div class="span3"> 
 										<label class="control-label" for="saldoDisponible"><spring:message code="valor.saldoDisponible"/></label> 
										<div class="controls"> 
											<input id="saldoDisponible" name="saldoDisponible" type="text" class="input" value="${valorDto.saldoDisponible}" readonly> 
										</div> 
									</div>
								</div>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="estadoValor"><spring:message code="valor.estadoValor"/></label> 
										<input id="estadoValor" name="estadoValor" type="text" value="${valorDto.estadoValor}" class="input" readonly>
									</div>
							
									<div class="span6">
										<label class="control-label" for="numeroValor"><spring:message code="valor.nroValor"/></label>
										<div class="controls">
											<input id="numeroValor" name="numeroValor" type="text" value="${valorDto.numeroValor}" class="input span3" readonly>
										</div>							
									</div>
								</div>
																	
								<div class="row">
									<div class="span3"> 
 										<label class="control-label" for="motivo"><spring:message code="valor.motivo"/></label> 
										<div class="controls"> 
											<input id="motivo" name="movito" type="text" class="input" value="${valorDto.motivo}" readonly> 
										</div> 
									</div>
									<div class="span3"> 
 										<label class="control-label" for="operacionAsociada"><spring:message code="valor.operacionAsociada"/></label> 
										<div class="controls"> 
											<input id="operacionAsociada" name="operacionAsociada" type="text" class="input" value="${valorDto.operacionAsociada}" readonly> 
										</div> 
									</div>
									<div class="span3" id="bloqueOrigen" style="display:none">
										<label class="control-label" for="origen"><spring:message code="valor.origen"/></label> 
										<div class="controls"> 
											<input id="origen" name="origen" type="text" class="input" value="${valorDto.origen}" readonly> 
										</div>
									</div>
									<div class="span3" id="bloqueNroDocumentoContable" style="display:none">
										<label class="control-label" for="numeroDocumentoContable"><spring:message code="valor.nroDocumentoContable"/></label> 
										<div class="controls"> 
											<input id="numeroDocumentoContable" name="numeroDocumentoContable" type="text" class="input" value="${valorDto.numeroDocumentoContable}" readonly> 
										</div>
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
									<div class="span3" id="bloqueFechaEmisionCheque" style="display:block">
										<label class="control-label" for="fechaEmisionCheque"><spring:message code="valor.fechaEmision.cheque"/></label> 
										<div class="controls"> 
											<input id="fechaEmisionCheque" name="fechaEmisionCheque" type="text" class="input" value="${valorDto.fechaEmision}" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="span3" id="bloqueFechaVencimiento" style="display:none">
										<label class="control-label" for="fechaVencimiento"><span id="space" style="display:none">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span><spring:message code="valor.fechaVencimiento"/></label> 
										<div class="controls"> 
											<input id="fechaVencimiento" name="fechaVencimiento" type="text" class="input" value="${valorDto.fechaVencimiento}" readonly> 
										</div>
									</div>
									<div class="span3" id="bloqueFechaNotificacionDisponibilidadRetiroValor" style="display:none">
										<label class="control-label" for="fechaNotificacionDisponibilidadRetiroValor"><spring:message code="valor.fecha.notificacion.disponibilidad.retiro.valor"/></label> 
										<div class="controls"> 
											<input id="fechaNotificacionDisponibilidadRetiroValor" name="fechaNotificacionDisponibilidadRetiroValor" type="text" class="input" value="${valorDto.fechaNotificacionDisponibilidadRetiroValor}" readonly> 
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
								<p>	<strong><spring:message code="valor.facturaRelacionada"/></strong> </p>
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

									<div id="bloqueChequeRechazado" style="display:none">
											<div class="row">
												<div class="span3">
													<label class="control-label" for="motivoRechazo"><spring:message
															code="valor.motivoRechazo" /></label>
													<div class="controls">
														<input id="motivoRechazo" name="motivoRechazo" type="text"
															class="input" value="${valorDto.motivoRechazo}" readonly>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="fechaRechazo"><spring:message
															code="valor.fechaRechazo" /></label>
													<div class="controls">
														<input id="fechaRechazo" name="fechaRechazo" type="text"
															class="input" value="${valorDto.fechaRechazo}" readonly>
													</div>
												</div>
												<div class="span3">
													<label class="control-label" for="fechaNotificacionRechazo"><spring:message
															code="valor.fechaNotificacionRechazo" /></label>
													<div class="controls">
														<input id="fechaNotificacionRechazo"
															name="fechaNotificacionRechazo" type="text" class="input"
															value="${valorDto.fechaNotificacionRechazo}" readonly>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="span3">
													<label class="control-label" for="idLegajoChequeRechazado"><spring:message
															code="valor.idLegajoChequeRechazado" /></label>
													<div class="controls">
														<input id="idLegajoChequeRechazado"
															name="idLegajoChequeRechazado" type="text" class="input"
															value="${valorDto.idLegajoChequeRechazado}" readonly>
													</div>
												</div>
											</div>
									</div>
									<div id="bloqueObsMail" style="display:none">
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacionMail"><spring:message code="valor.observacionesEmail"/></label>
											<textarea class="field span9" id="observacionMail" name="observacionMail" maxlength="250" readonly
												>${valorDto.observacionMail}</textarea>
										</div>
									</div>
								</div>
								
		
		
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observaciones"><spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="observaciones" name="observaciones" readonly>${valorDto.observaciones}</textarea>
									</div>
								</div>

								<!-- INCIO SECCION COMPROBANTE -->
								<div id="bloqueComprobantes" style="display: none">
									<p><strong><spring:message code="valor.comprobantes"/></strong></p>
									<div class="row">
									<div class="span9">
										<table style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit;"
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
															<button id="btnVerComprobante" type="button" class="btn btn-xs btn-link" title="Ver documento adjunto" onclick="abrirComprobante(${compro.id});">
																<i class="icon-download-alt bigger-120"></i>
															</button>
														</div>
													</td>
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
								<!-- FIN SECCION COMPROBANTE -->
							</div>						
									
							<div class="pagos_anticipos">
								<p><strong><spring:message code="valor.historialModificacion"/></strong></p>
										
								<div class="row" style="margin-top: 10px; margin-bottom: 30px; margin-right: 2px">
										
									<div class="span9" id="ninguno">
										<table id="resultadoBusquedaHistorial" style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit; " class="tablaResultadoHist">
											<thead>
												<tr>
													<th class="tituloTabla"><spring:message code="valor.estado"/></th>
													<th class="tituloTabla"><spring:message code="valor.usuario"/></th>
													<th class="tituloTabla dateTimeSecondsMilliseconds"><spring:message code="valor.fechaActualizacion"/></th>
													<th class="tituloTabla"><spring:message code="valor.datosModificados"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${historicoDto}" var="hist" varStatus="i" begin="0">
												<tr>
													<td class="registroTabla" style="white-space: nowrap" >${hist.estadoModificacion}</td>
													<td class="registroTabla" style="text-align: left;white-space: nowrap;width: 160px ">
											 			<c:choose>
															<c:when test="${fn:trim(hist.estadoModificacion) ne '-'}">
																<div >
																<img alt="Usuario" src="${hist.urlFotoUsuario(hist.idUsuarioModificacion)}" 
																style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 														
																onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																${hist.usuarioModificacion} <br>
																<a href="sip:${hist.mailUsuarioModificacion}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																<a href="mailto:${hist.mailUsuarioModificacion}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																</div>
															</c:when>
															<c:otherwise><div style="text-align: center;">${val.usuarioAutorizacion} <br></div></c:otherwise>
														</c:choose>
											 		</td>
													<td class="registroTabla">
														${hist.fechaModificacion}
												 	</td>
													<td class="registroTabla" style="text-align: left;">
														<div>
														<c:forEach items="${hist.camposModificados}" varStatus="j" begin="0">
															<div>
																<strong>${hist.camposModificados[j.index]}</strong><br>
																<spam>${hist.valoresOriginales[j.index]}</spam><br><br>
															</div>
														</c:forEach>
														</div>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
									<a href="${pageContext.request.contextPath}/vuelta-busqueda"class="btn btn-primary btn-small" id="btnVolver"><i class="icon-white icon-repeat"></i> <spring:message code="valor.botonVolver"/></a>  
									</div>
								</div>
																
							</div>	
							</form:form>
						</div>
				</div><!-- end #content -->
			</div><!-- end .wrap -->
		</div><!-- end #main -->
		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	</div><!-- end #container -->
	</div><!-- end #container -->
</body>

<script>
	$('#btnVolver').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	function abrirComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formHistorial')[0].action="${pageContext.request.contextPath}/procesar-abrir-comprobante-historial";
		$('#formHistorial')[0].submit();
	};
</script>
</html>