<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.SiNoEnum" %>
<%@ page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado" %>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="conciliacion.creacion.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<script>
var tipoBloque;

function mostarTipoValor(tipoValor) {
	var tipoValorSeleccionado = tipoValor.value;
	switch (tipoValorSeleccionado) {
		case "2": // Cheque
		bloqueNumeroCliente.style.display = 'block';
		bloqueNumeroCheque.style.display = 'block';
		bloqueNumeroBoleta.style.display = 'block';
		  tipoBloque = "cheque";
		break;
	case "3": // Cheque PD
		bloqueNumeroCliente.style.display = 'block';
		bloqueNumeroCheque.style.display = 'block';
		bloqueFechaVencimiento.style.display = 'block';
		bloqueNumeroBoleta.style.display = 'block';
		  tipoBloque = "chequeDiferido";
		break;
	case "4": //Efectivo
		bloqueNumeroCliente.style.display = 'block';
		bloqueNumeroBoleta.style.display = 'block';
		  tipoBloque = "efectivo";
		break;
	case "8": //Transferencia
		bloqueFechaTransferencia_T.style.display = 'block'; 
		bloqueNumeroReferencia_T.style.display = 'block'; 
		bloqueCuit_T.style.display = 'block'; 
		  tipoBloque = "transferencia";
		break;
	case "9": //Interdeposito
		bloqueFechaInterdeposito_I.style.display = 'block'; 
		bloqueCodigoOrganismo_I.style.display = 'block'; 
		bloqueNumeroInterdeposito_I.style.display = 'block'; 
		  tipoBloque = "interdeposito";
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
							<div class="title"><spring:message code="conciliacion.confirmar.titulo.pagina"/></div>
							
							<div class="pagos_anticipos">
								<form:form id="formModificacion" commandName="registroDto">
							 
								    <form:hidden path="operation" id="operation" />
								    <form:hidden path="idRegistro" id="idRegistro" />
									<form:hidden path="idAcuerdo" id="idAcuerdo" />
									<form:hidden path="timeStamp" id="timeStamp"/>
<%-- 									<form:hidden path="sucursal" id="sucursal" /> --%>
<%-- 									<form:hidden path="codigo" id="codigo" /> --%>
									<form:hidden path="pantallaDestino" id="pantallaDestino" />
<%-- 									<form:hidden path="importeParaComparar"	id="importeParaComparar" /> --%>
									<form:hidden path="tipoValor" id="tipoValor" />
<%-- 									<form:hidden path="fechaIngreso" id="fechaIngreso" />			 --%>
									
									<p><strong><spring:message code="conciliacion.datosRegistroAVC"/></strong></p>

									<div class="row">
										<div class="span6">
											<label class="control-label" for="nombreArchivo"><spring:message code="conciliacion.nombreArchivo"/></label>
											<div class="controls">
												<input id=nombreArchivo name="nombreArchivo" type="text"
													value="${registroDto.nombreArchivo}" class="input span6" readonly>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="tipoValorFormateado"><spring:message code="conciliacion.tipoRegistro"/></label>
											<input id="tipoValorFormateado" name="tipoValorFormateado" type="text" class="input" readonly value="${registroDto.tipoValorFormateado}">
										</div>
										<div class="span3">
											<label class="control-label" for="importe"><spring:message code="conciliacion.importe"/></label>
											<div class="controls">
												<input id="importe" name="importe" type="text" class="input" readonly value="${registroDto.importe}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="estadoFormateado"><spring:message code="conciliacion.estado"/></label> 
											<input id="estadoFormateado" name="estadoFormateado"
												type="text" class="input" readonly value="${registroDto.estadoFormateado}">
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="acuerdo"><spring:message code="conciliacion.nroAcuerdo"/></label>
											<div class="controls">
												<input id="acuerdo" name="acuerdo" type="text" value="${registroDto.acuerdo}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="bancoDeposito"><spring:message code="conciliacion.bancoDeposito"/></label>
											<div class="controls">
												<input id="bancoDeposito" name="bancoDeposito" type="text" value="${registroDto.bancoDeposito}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="numeroCuenta"><spring:message code="conciliacion.nroCuenta"/></label>
											<div class="controls">
												<input id="numeroCuenta" name="numeroCuenta" type="text"  value="${registroDto.numeroCuenta}" readonly>
											</div>
										</div>
									</div>
									
							<c:if test="${registroDto.esDeposito}">	
								<div class="row">
									<div class="span3" id="bloqueNumeroBoleta" style="display:none">
										<label class="control-label" for="nroBoleta"><spring:message code="conciliacion.numeroBoleta"/></label>
										<div class="controls">
											<input id="nroBoleta" name="nroBoleta" type="text" class="input" value="${registroDto.nroBoleta}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueNumeroCliente" style="display:none">
										<label class="control-label" for="codigoCliente"><spring:message code="conciliacion.numeroCliente"/></label>
										<div class="controls">
											<input id="codigoCliente" name="codigoCliente" type="text" class="input" value="${registroDto.codigoCliente}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueNumeroCheque" style="display:none">
										<label class="control-label" for="numeroCheque"><spring:message code="conciliacion.numeroCheque"/></label>
										<div class="controls">
											<input id="numeroCheque" name="numeroCheque" type="text" class="input" value="${registroDto.numeroCheque}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueFechaVencimiento" style="display:none">
										<label class="control-label" for="fechaVencimientoFormateada"><spring:message code="conciliacion.fechaVencimiento"/></label>
										<div class="controls">
											<input id="fechaVencimientoFormateada" name="fechaVencimientoFormateada" type="text" class="input" value="${registroDto.fechaVencimientoFormateada}" readonly>
										</div>
									</div>
								</div>
							</c:if>
							
<!-- 			BLOQUE TRANSFERENCIA -->
							<c:if test="${registroDto.esTransferencia}">
								<div class="row">
									<div class="span3" id="bloqueFechaTransferencia_T" style="display:none">
										<label class="control-label" for="fechaIngresoFormateada"><spring:message code="conciliacion.fechaTransferencia"/></label>
										<div class="controls">
											<input id="fechaIngresoFormateada" name="fechaIngresoFormateada" type="text" class="input" value="${registroDto.fechaIngresoFormateada}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueNumeroReferencia_T" style="display:none">
										<label class="control-label" for="referencia"><spring:message code="conciliacion.numeroReferencia"/></label>
										<div class="controls">
											<input id="referencia" name="referencia" type="text" class="input" value="${registroDto.referencia}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueCuit_T" style="display:none">
										<label class="control-label" for="cuit"><spring:message code="conciliacion.cuit"/></label>
										<div class="controls">
											<input id="cuit" name="cuit" type="text" class="input" value="${registroDto.cuit}" readonly>
										</div>
									</div>
								</div>
							</c:if>
		<!-- 			BLOQUE INTERDEPOSITO -->
							<c:if test="${registroDto.esInterdeposito}">
								<div class="row">
									<div class="span3" id="bloqueFechaInterdeposito_I" style="display:none">
										<label class="control-label" for="fechaIngresoFormateada"><spring:message code="conciliacion.fechaInterdeposito"/></label>
										<div class="controls">
											<input id="fechaIngresoFormateada" name="fechaIngresoFormateada" type="text" class="input" value="${registroDto.fechaIngresoFormateada}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueCodigoOrganismo_I" style="display:none">
										<label class="control-label" for="codigoOrganismo"><spring:message code="conciliacion.codigoOrganismo"/></label>
										<div class="controls">
											<input id="codigoOrganismo" name="codigoOrganismo" type="text" class="input" value="${registroDto.codigoOrganismo}" readonly>
										</div>
									</div>
									<div class="span3" id="bloqueNumeroInterdeposito_I" style="display:none">
										<label class="control-label" for="codigoInterdeposito"><spring:message code="conciliacion.numeroInterdeposito"/></label>
										<div class="controls">
											<input id="codigoInterdeposito" name="codigoInterdeposito" type="text" class="input" value="${registroDto.codigoInterdeposito}" readonly>
										</div>
									</div>
								</div>
							</c:if>
									
									
									<c:if test="${registroDto.tipoValorFormateado eq 'Transferencia'}">
										<!-- OBSERVACIONES ARCHIVO AVC -->
										<div class="row" style="margin-top: 20px; margin-bottom: 10px">
											<div class="span9">
												<label class="control-label" for="observacionRegistroAvc"><spring:message code="valor.observacionesArchivoAVC"/></label>
												<textarea class="field span9" id="observacionRegistroAvc" name="observacionRegistroAvc" readonly>${registroDto.observacion}</textarea>
											</div>
										</div>
									</c:if>
									
									
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observaciones"><spring:message code="conciliacion.observacionesAnteriores" /></label>
											<textarea class="field span9" id="observaciones" name="observacionAnulacion" readonly>${registroDto.observaciones}</textarea>
										</div>
									</div>


									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacionConfirmarAnulacion"><spring:message
													code="conciliacion.observaciones" /></label>
											<textarea class="field span9" id="observacionConfirmarAnulacion" maxlength="250"
												name="observacionConfirmarAnulacion">${registroDto.observacionConfirmarAnulacion}</textarea>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="observacionConfirmarAnulacion" cssClass="error" />
										</div>
									</div>
									
									
									<div class="row"
										style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
										<div align="right">
											<a href="${pageContext.request.contextPath}/bandeja-de-entrada" class="btn btn-primary btn-small" id="btnCancelar">
												<i class="icon-white icon-remove"></i>&nbsp;<spring:message code="valor.botonCancelar"/>
											</a>
											<button type="button" class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar">
                                                <i class="icon-white icon-ok" ></i> <spring:message code="conciliacion.botonConfirmarAnulacion"/>
                                            </button>
											<button type="button" class="btn btn-primary btn-small" id="btnRechazar" name="btnRechazar">
												<i class="icon-white icon-remove" > </i> <spring:message code="conciliacion.botonRechazarPedido"/>
											</button>
										</div>
									</div>
							  </form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	<!-- FIN WEB -->

	<script>	
	$(function() {
		
		window.onload = function() {
// 			if(<c:out value = "${imprimibleArchivo}"/>) {
// 				window.location ="${pageContext.request.contextPath}/abrir-documento";
// 			}
			mostarTipoValor(document.getElementById('tipoValor'));
			
		};
	});
		 $('#btnAceptar').click(function() {
	    	  $('#operation').val("<%=Constantes.CONFIRMAR_ANULAR_REGISTRO_AVC%>");
			 
	    	  if(tipoBloque == "transferencia"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-pedido-anulacion-transferencia";
					  $('#formModificacion')[0].submit();
				}
			    if(tipoBloque == "interdeposito"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-pedido-anulacion-interdeposito";
					  $('#formModificacion')[0].submit();
				}
				if(tipoBloque == "efectivo" || tipoBloque == "cheque" || tipoBloque == "chequeDiferido"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-pedido-anulacion-deposito";
					  $('#formModificacion')[0].submit();
				}
			});
		 
		 $('#btnCancelar').click(function() {
				$('#bloqueEspera').trigger('click');				
		 }); 

		 
		 $('#btnRechazar').click(function() {
	    	  $('#operation').val("<%=Constantes.RECHAZAR_ANULAR_REGISTRO_AVC%>");

	    	  if(tipoBloque == "transferencia"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-rechazar-pedido-anulacion-transferencia";
					  $('#formModificacion')[0].submit();
				}
			    if(tipoBloque == "interdeposito"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-rechazar-pedido-anulacion-interdeposito";
					  $('#formModificacion')[0].submit();
				}
				if(tipoBloque == "efectivo" || tipoBloque == "cheque" || tipoBloque == "chequeDiferido"){
			    	  $('#bloqueEspera').trigger('click');
			    	  $('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-rechazar-pedido-anulacion-deposito";
					  $('#formModificacion')[0].submit();
				}
		 });
		 
</script>
</body>

</html>

