<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="conciliacion.creacion.titulo"/></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<script>

function mostrarTipoValor(tipoDeValor) {

 	switch (tipoDeValor) {
	 	case "5": // Cheque
	 		bloqueFechaDeposito.style.display = 'block';
	 		bloqueBancoOrigen.style.display = 'block';
	 		bloqueNroCheque.style.display = 'block';
	 		<c:if test="${empty valorDto.valorPorReversion}">
				bloqueReciboConstancia.style.display = 'block';
			</c:if>
	 		break;
	 	case "6": // Cheque Pago Diferido
	 		bloqueFechaDeposito.style.display = 'block';
	 		bloqueBancoOrigen.style.display = 'block';
	 		bloqueNroCheque.style.display = 'block';
	 		bloqueFechaVencimiento.style.display = 'block';
	 		<c:if test="${empty valorDto.valorPorReversion}">
				bloqueReciboConstancia.style.display = 'block';
			</c:if>
	 		break;
		case "7": //Efectivo
			bloqueNroBoleta.style.display = 'block';
			bloqueFechaDeposito.style.display = 'block';
			<c:if test="${empty valorDto.valorPorReversion}">
				bloqueReciboConstancia.style.display = 'block';
			</c:if>
			break;
	 	case "8": //Transferencia
	 		bloqueNroReferencia.style.display = 'block';
	 		break;
	 	case "9": //Interdeposito
	 		bloqueInterdeposito.style.display = 'block';
	 		break;
		default:
			
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
									<div class="title"><spring:message code="conciliacion.confirmarAlta.titulo.pagina"/></div>
								</c:when>
								<c:otherwise>
									<div class="title"><spring:message code="conciliacion.confirmarAlta.titulo.pagina.valorPorReversion"/></div>
								</c:otherwise>
							</c:choose>
							<div class="pagos_anticipos">
								<p><strong><spring:message code="valor.datosGenerales"/></strong></p>

<!-- DATOS GENERALES -->
								<form:form id="formModificacion" commandName="valorDto" enctype="multipart/form-data">
									
									<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>
									<form:hidden path="operation" id="operation"/>
									<form:hidden path="idRegistroAvc" id="idRegistroAvc"/>
									<form:hidden path="idValor" id="idValor"/>
									<form:hidden path="valorPorReversion" id="valorPorReversion"/>
									<form:hidden path="fechaAltaValor" id="fechaAltaValor"/>
									<!-- DATOS MODIFICACION -->
									<form:hidden path="tipoModificacion" id="tipoModificacion"/>
									<form:hidden path="modifAnaRecha" id="modifAnaRecha"/>
									<form:hidden path="modifAnaNoRecha" id="modifAnaNoRecha"/>
									<form:hidden path="modifAdmRecha" id="modifAdmRecha"/>
									<form:hidden path="modifAdmNoRecha" id="modifAdmNoRecha"/>
																		
									<!-- DATOS GENERALES -->
									<form:hidden path="mailAnalista" id="mailAnalista"/>
									<form:hidden path="idAnalista" id="idAnalista"/>
									<!-- VALORES -->
									<form:hidden path="idTipoValor" id="idTipoValor"/>
									<!-- OBSERVACIONES -->

									<!-- AVISOS -->
									<form:hidden path="tipoImpuesto" id="tipoImpuesto"/>
									<form:hidden path="provincia" id="provincia"/>
									<form:hidden path="tipoComprobante" id="tipoComprobante"/>
									<form:hidden path="letraComprobante" id="letraComprobante"/>
									<form:hidden path="estaRechazandoConfirmacion" id="estaRechazandoConfirmacion"/>
									<form:hidden path="timeStamp" id="timeStamp"/>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idEmpresa"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.empresa"/></label>
											<div class="controls ">
												<input id="empresa" name="empresa" type="text" value="${valorDto.empresa}" readonly></input>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idSegmento"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.segmento"/></label>
											<div class="controls">
												<input id="segmento" name="segmento" type="text" value="${valorDto.segmento}" readonly></input>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="codCliente"><c:if test="${(valorDto.modifAnaRecha || valorDto.modifAdmRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.codCliente"/></label>
											<div class="controls">
												<input id="inputCodCliente" name="codCliente" type="text" maxlength="10" value="${valorDto.codCliente}" class="input" <c:if test="${!(valorDto.modifAnaRecha || valorDto.modifAdmRecha)}">readonly</c:if>>
											</div>
										</div>
										<div class="span6">
											<label class="control-label" for="razonSocialClienteLegado"><spring:message code="valor.razonSocial"/></label>
											<div class="controls">
												<input id="razonSocialClienteLegado" name="razonSocialClienteLegado" type="text" value="${valorDto.razonSocialClientePerfil}" class="span6" readonly>
											</div>
										</div>
									</div>
								   
									<div class="row">
										<div class="span3">
											<label class="control-label" for="codClienteAgrupador"><c:if test="${(valorDto.modifAnaRecha || valorDto.modifAdmRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.codClienteAgrupador"/></label>
											<div class="controls">
												<input id="codClienteAgrupador" name="codClienteAgrupador" type="text" class="input" readonly value="${valorDto.codClienteAgrupador}" style="margin: 0 auto;" />
											</div>
										</div>
										<div class="span6">
											<label class="control-label" for="razonSocialClienteAgrupador"><spring:message code="valor.razonSocialAgrupador"/></label>
											<div class="controls">
												<input id="razonSocialClienteAgrupador" name="razonSocialClienteAgrupador" type="text" class="input span6" readonly value="${valorDto.razonSocialClienteAgrupador}" />
											</div>							
										</div>
									</div>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="numeroHolding"><spring:message code="valor.numeroHolding"/></label>
											<div class="controls">
												<input id="numeroHolding" name="numeroHolding" type="text" class="input" readonly value="${valorDto.numeroHolding}" style="margin: 0 auto;" />
											</div>
										</div>
										<div class="span6">
											<label class="control-label" for="nombreHolding"><spring:message code="valor.nombreHolding"/></label>
											<div class="controls">
												<input id="nombreHolding" name="nombreHolding" type="text" class="input span6" readonly value="${valorDto.nombreHolding}">
											</div>							
										</div>
									</div>
								   
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idAnalista"><spring:message code="valor.analista"/></label>
											<div class="controls" style="white-space: nowrap;">
												<img alt="Usuario"  src="${valorDto.urlFotoUsuario(valorDto.idAnalista)}"
													style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 6px; margin-top: 1px; margin-left: 1px; float: left; margin-bottom: 9px;" onerror='src="${pageContext.request.contextPath}/img/default2.png"'>						
														
												<input class="controls" id=nombreAnalista name="nombreAnalista" type="text" value="${valorDto.nombreAnalista}" readonly style="margin-bottom: 2px; width: 164px;"></input>
												<div class="row">
													<a href="#"  title="<spring:message code="valor.chat"/>"><i class="icon-comment"></i></a>
													<a href="#"  title="<spring:message code="valor.email"/>"><i class="icon-envelope" style=" margin-left: 3px; "></i></a>
												</div>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idCopropietario"><spring:message code="valor.copropietario"/></label>
											<div class="controls">
												<input id="copropietario" name="copropietario" type="text" value="${valorDto.copropietario}" readonly></input>
											</div>
										</div>
									</div>

<!-- SEGUNDA PARTE DEL FORMULARIO -->

								<p>	<strong><spring:message code="valor.valores"/></strong> </p>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idTipoValor"><spring:message code="valor.tipoValor"/></label>
										<div class="controls">
											<input id="tipoValor" name="tipoValor" type="text" value="${valorDto.tipoValor}" readonly></input>
										</div>
									</div>
									<div class="span3"> 
 										<label class="control-label" for="importe"><spring:message code="valor.importe"/></label> 
										<div class="controls"> 
											<input id="importe" name="importe" type="text" class="input" value="${valorDto.importe}" readonly> 
										</div> 
									</div>
									<c:if test="${not empty valorDto.valorPorReversion}">
										<div class="span3"> 
	 										<label class="control-label" for="saldoDisponible"><spring:message code="valor.saldoDisponible"/></label> 
											<div class="controls"> 
												<input id="saldoDisponible" name="saldoDisponible" type="text" class="input" value="${valorDto.saldoDisponible}" readonly> 
											</div> 
										</div>
									</c:if>
								</div>
								
								<div class="row" id="bloqueNroBoleta" style="display:none">
									<div class="span3">
										<label class="control-label" for="numeroBoleta"><spring:message code="valor.nroBoleta"/></label>
										<div class="controls">
											<input id="numeroBoleta" name="numeroBoleta" type="text" class="input" value="${valorDto.numeroBoleta}" readonly></input>
										</div>
									</div>
								</div>
									
								<div class="row">
									<div class="span3">
									<label class="control-label" for="idMotivo"><spring:message code="valor.motivo"/></label>
										<div class="controls">
											<input id="motivo" name="motivo" type="text" value="${valorDto.motivo}" readonly></input>
										</div>
									</div>
									<div class="span3" id="bloqueOrigen"> 
										<label class="control-label" for="idOrigen"><spring:message code="valor.origen"/></label>
										<div class="controls">
											<input id="origen" name="origen" type="text" value="${valorDto.origen}" readonly></input>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="estadoValor"><spring:message code="valor.estado"/></label>
										<div class="controls">												
											<input id="estadoValor" name="estadoValor" type="text" value="${valorDto.estadoValor}" class="input" readonly>
										</div> 
									</div>
								</div>
								
<!-- VARIABLES -->
								<div class="row" id="bloqueAcuerdo">
									<div class="span3">
										<label class="control-label" for="acuerdo"><spring:message code="valor.acuerdo"/></label>
										<div class="controls">
											<input id="acuerdo" name="acuerdo" type="text" value="${valorDto.acuerdo}" readonly></input>
										</div>
										<input id="idAcuerdo" name="idAcuerdo" type="hidden" value="${valorDto.idAcuerdo }"/>
									</div>
									<div class="span3">
										<label class="control-label" for="idBancoDeposito"><spring:message code="valor.bancoDeposito"/></label>
										<div class="controls">
											<input id="bancoDeposito" name="bancoDeposito" type="text" value="${valorDto.bancoDeposito}" readonly></input>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idNroCuenta"><spring:message code="valor.nroCuenta"/></label>
										<div class="controls">
											<input id="numeroCuenta" name="numeroCuenta" type="text" value="${valorDto.numeroCuenta}" readonly></input>
										</div>
									</div>
								</div>
									
								<div class="row">
									<div class="span3" id="bloqueFechaDeposito" style="display:none">
										<label class="control-label" for="fechaDeposito"><spring:message code="valor.fechaDeposito"/></label>
										<div class="controls">
											<input type="text" id="fechaDeposito" data-date-format="dd/mm/yyyy" name="fechaDeposito" data-date-language='es' class="input" value="${valorDto.fechaDeposito}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="span3" id="bloqueBancoOrigen" style="display:none">
										<label class="control-label" for="idBancoOrigen"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.bancoOrigen"/></label>
										<div class="controls">
											<input id="bancoOrigen" name="bancoOrigen" type="text" value="${valorDto.bancoOrigen}" readonly></input>
										</div>
									</div>
									<div class="span3" id="bloqueNroCheque" style="display:none">
										<label class="control-label" for="numeroCheque"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.nroCheque"/></label>
										<div class="controls">
											<input id="numeroCheque" name="numeroCheque" type="text" class="input" value="${valorDto.numeroCheque}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
									<div class="span3" id="bloqueFechaVencimiento" style="display:none">
										<label class="control-label" for="fechaVencimiento"><c:if test="${(valorDto.modifAnaRecha)}"><span class="rojo">* </span></c:if><spring:message code="valor.fechaVencimiento"/></label>
										<div class="controls">
											<input type="text" id="fechaVencimiento" name="fechaVencimiento" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" value="${valorDto.fechaVencimiento}" <c:if test="${!(valorDto.modifAnaRecha)}">readonly</c:if>>
										</div>
									</div>
								</div>
								
								<div class="row" id="bloqueNroReferencia" style="display:none">
									<div class="span3">
										<label class="control-label" for="numeroReferencia"><spring:message code="valor.nroReferencia"/></label>
										<div class="controls">
											<input id="numeroReferencia" name="numeroReferencia" type="text" class="input" value="${valorDto.numeroReferencia}" readonly></input>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="fechaTransferencia"><spring:message code="valor.fechaTransferencia"/></label>
										<div class="controls">
											<input type="text" id="fechaTransferencia" name="fechaTransferencia" data-date-format="dd/mm/yyyy" data-date-language='es' class="input" value="${valorDto.fechaTransferencia}" readonly></input>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="cuit"><spring:message code="valor.cuit"/></label>
										<div class="controls">
											<input id="cuit" name="cuit" type="text" class="input" value="${valorDto.cuit}" readonly></input>
										</div>
									</div>
								</div>
								
								<div id="bloqueInterdeposito" style="display:none">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaInterdeposito"><spring:message code="valor.fechaInterdeposito"/></label>
										<div class="controls">
											<input type="text" id="fechaInterdeposito" data-date-format="dd/mm/yyyy" name="fechaInterdeposito" data-date-language='es' class="input" value="${valorDto.fechaInterdeposito}" readonly>
										</div>
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
								</div>
								
								<div id="bloqueReciboConstancia" style="display: none">
									<div class="row">
										<div class="span3">
											<label class="control-label" for="reciboPreImpreso"><spring:message code="valor.recibo" /></label>
											<div class="controls">
												<input id="reciboPreImpreso" name="reciboPreImpreso" type="text" class="input" value="${valorDto.reciboPreImpreso}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaIngresoRecibo"><spring:message code="valor.fechaIngresoRecibo" /></label>
											<div class="controls">
												<input id="fechaIngresoRecibo" name="fechaIngresoRecibo" type="text" class="input" value="${valorDto.fechaIngresoRecibo}" readonly>
											</div>
										</div>
									</div>
								</div>

									<!-- INCIO SECCION COMPROBANTE -->

								<p><strong><spring:message code="valor.comprobantes"/></strong></p>
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
							
							<c:if test="${valorDto.tipoValor eq 'Transferencia'}">
							<!-- OBSERVACIONES ARCHIVO AVC -->
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observacionRegistroAvc"><spring:message code="valor.observacionesArchivoAVC"/></label>
										<textarea class="field span9" id="observacionRegistroAvc" name="observacionRegistroAvc" readonly>${valorDto.observacionRegistroAvc}</textarea>
									</div>
								</div>
							</c:if>
							
							<!-- OBSERVACIONES -->
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observaciones"><spring:message code="conciliacion.observacionesAnteriores"/></label>
										<textarea class="field span9" id="observaciones" name="observaciones" readonly>${valorDto.observaciones}</textarea>
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observacionesConfirmarAlta"> <spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="observacionesConfirmarAlta" name="observacionesConfirmarAlta" maxlength="250">${valorDto.observacionesConfirmarAlta}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="observacionesConfirmarAlta" cssClass="error" />
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<a href="${pageContext.request.contextPath}/bandeja-de-entrada" 
											class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="valor.botonCancelar"/></a>
										<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button">
											<i class="icon-white icon-ok"></i> <spring:message code="conciliacion.botonConfirmar"/>
										</button>
										<button class="btn btn-primary btn-small" id="btnRechazar" name="btnRechazar" type="button">
											<i class="icon-white icon-remove"></i> <spring:message code="valor.botonRechazar"/>
										</button>
									</div>
								</div>
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
	

	function abrirComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-abrir-comprobante-alta-registro-avc-sin-conciliar";
		$('#formModificacion')[0].submit();
	};
	
	$('#btnAceptar').click(function() {
		$('#bloqueEspera').trigger('click');
		$("#estaRechazandoConfirmacion").val(false);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-aceptar-tarea-alta-registro-avc-sin-conciliar";
		$('#formModificacion')[0].submit();
	});
	
	$('#btnRechazar').click(function() {
		$('#bloqueEspera').trigger('click');
		$("#estaRechazandoConfirmacion").val(true);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-rechazar-tarea-alta-registro-avc-sin-conciliar";
		$('#formModificacion')[0].submit();
	});
	
	$('#btnCancelar').click(function() {
		$('#bloqueEspera').trigger('click');
	});
	
	window.onload = function() {
		if(<c:out value = "${imprimibleArchivo}"/>) {
			window.location ="${pageContext.request.contextPath}/abrir-documento-comprobante";
		}
		mostrarTipoValor(document.getElementById("idTipoValor").value);
		buscarClienteLegadoSiebel($('#inputCodCliente').val().split(" ").join(""));
	};
	
	$('#fechaIngresoRecibo').datepicker();
	
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