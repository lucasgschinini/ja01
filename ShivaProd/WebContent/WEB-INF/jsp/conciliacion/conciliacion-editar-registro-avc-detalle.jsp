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
<title><spring:message code="conciliacion.creacion.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

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
								<spring:message code="conciliacion.editar.titulo.pagina" />
							</div>

							<div class="pagos_anticipos">

								<form:form id="formModificacion" commandName="transDto" enctype="multipart/form-data">
								
									<form:hidden path="idComprobanteSelected" id="idComprobanteSelected"/>


									<form:hidden path="operation" id="operation" />
									<form:hidden path="idRegistro" id="idRegistro" />
									<form:hidden path="idAcuerdo" id="idAcuerdo" />
									<form:hidden path="sucursal" id="sucursal" />
									<form:hidden path="codigo" id="codigo" />
									<form:hidden path="subtotal" id="subtotal" />
									<form:hidden path="importeParaComparar"	id="importeParaComparar" />
									<form:hidden path="tipoValor" id="tipoValor" />
									<form:hidden path="fechaIngreso" id="fechaIngreso" />									

									<p>
										<strong><spring:message
												code="conciliacion.datosRegistroAVC" /></strong>
									</p>

									<div class="row">
										<div class="span6">
											<label class="control-label" for="nombreArchivo"><spring:message
													code="conciliacion.nombreArchivo" /></label>
											<div class="controls">
												<input id=nombreArchivo name="nombreArchivo" type="text"
													value="${transDto.nombreArchivo}" class="input span6"
													readonly>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="tipoValorFormateado"><spring:message
													code="conciliacion.tipoRegistro" /></label> <input
												id="tipoValorFormateado" name="tipoValorFormateado" type="text"
												class="input" readonly
												value="${transDto.tipoValorFormateado}">
										</div>
										<div class="span3">
											<label class="control-label" for="importe"><spring:message
													code="conciliacion.importe" /></label>
											<div class="controls">
												<input id="importe" name="importe" type="text" class="input"
													readonly value="${transDto.importeFormateado}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="estadoFormateado"><spring:message
													code="conciliacion.estado" /></label> <input id="estadoFormateado"
												name="estadoFormateado" type="text" class="input" readonly
												value="${transDto.estadoFormateado}">
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="acuerdo"><spring:message
													code="conciliacion.acuerdo" /></label>
											<div class="controls">
												<input id="acuerdo" name="acuerdo" type="text"
													value="${transDto.acuerdo}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="bancoDeposito"><spring:message
													code="conciliacion.bancoDeposito" /></label>
											<div class="controls">
												<input id="bancoDeposito" name="bancoDeposito" type="text"
													value="${transDto.bancoDeposito}" readonly>
												<!-- 													value="transDto.bancoDeposito" readonly> -->
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="nroCuenta"><spring:message
													code="conciliacion.nroCuenta" /></label>
											<div class="controls">
												<input id="numeroCuenta" name="numeroCuenta" type="text" class="input" value="${transDto.numeroCuenta}" readonly>
												<!-- 													value="transDto.nroCuenta" readonly> -->
											</div>
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaTrasferencia"><spring:message
													code="conciliacion.fechaTransferencia" /></label>
											<div class="controls">
												<input type="text" id="dp1" data-date-format="dd/mm/yyyy"
													data-date-language='es' class="input" readonly
													value="${transDto.fechaIngresoFormateada}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="referencia"><spring:message
													code="conciliacion.nroReferencia" /></label>
											<div class="controls">
												<input id="referncia" name="referencia" type="text" class="input"
													readonly value="${transDto.referencia}">
											</div>
										</div>

										<div class="span3">
											<label class="control-label" for="cuit"><spring:message
													code="conciliacion.cuit" /></label>
											<div class="controls">
												<input id="cuit" name="cuit" type="text" onkeyup="habilitarAsterisco();" class="input"
													value="${transDto.cuit}">
											</div>
										</div>
									</div>
									<div class="row rowError" >
										<div class="span3">
										</div>
										<div class="span3">
										</div>
										<div class="span3">
											<form:errors path="cuit" cssClass="error" />
										</div>
									</div>
									
									<c:if test="${transDto.tipoValorFormateado eq 'Transferencia'}">
										<!-- OBSERVACIONES ARCHIVO AVC -->
										<div class="row" style="margin-top: 20px; margin-bottom: 10px">
											<div class="span9">
												<label class="control-label" for="observacionRegistroAvc"><spring:message code="valor.observacionesArchivoAVC"/></label>
												<textarea class="field span9" id="observacionRegistroAvc" name="observacionRegistroAvc" readonly>${transDto.observacion}</textarea>
											</div>
										</div>
									</c:if>
									
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacion"><spring:message
													code="conciliacion.observacionesAnteriores" /></label>
											<textarea class="field span9" id="observacion" name="observacion" readonly>${transDto.observaciones}</textarea>
										</div>
									</div>


									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="nuevaObservacion"><spring:message
													code="conciliacion.observaciones" /></label>
											<textarea class="field span9" id="nuevaObservacion" name="nuevaObservacion" maxlength="250">${transDto.nuevaObservacion}</textarea>
										</div>
									</div>
									
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="observacion" cssClass="error" />
										</div>
									</div>

									<div id="bloqueComprobantesAgregar">
										<p>
											<label><span class="rojo" id="mostrarAsterisco" style="display: none;">* </span><spring:message code="conciliacion.comprobantes"/></label>
										</p>
										<div class="row">
											<div class="span3">
												<label class="control-label" for="fileComprobanteModificacion"><spring:message code="conciliacion.adjuntarComprobante" /></label>
												<div class="fileupload fileupload-new" data-provides="fileupload">
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
														<textarea class="field span9" id="descripcionComprobante" name="descripcionComprobante" maxlength="150">${transDto.descripcionComprobante}</textarea>
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
															<table style="width: 670px; border: 1px solid #e1e1e1; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; border-collapse: inherit;"
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
																	<c:forEach items="${transDto.listaComprobantes}" var="compro" varStatus="i" begin="0">
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
																			<form:input path="listaComprobantes[${i.index}].idComprobante" id="idComprobante${i.index}" type="hidden" />
																			<form:input path="listaComprobantes[${i.index}].id" id="id${i.index}" type="hidden" />
																			<form:input path="listaComprobantes[${i.index}].usuarioCreacion" id="usuarioCreacion${i.index}" type="hidden"/>
																		</tr>
																	</c:forEach>
																	<c:if test="${empty transDto.listaComprobantes}">
																		<tr>
																			<td colspan="3" class="registroTabla" align="left">&nbsp;&nbsp;<spring:message code="mensaje.tabla.vacia"/></td>
																		</tr>
																	</c:if>
																</tbody>

															</table>
														</div>
													</div>
													<c:if test="${transDto.errorComprobanteVacioModif}">
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
											<button type="button" class="btn btn-primary btn-small"
												id="btnAceptar" name="btnAceptar">
												<i class="icon-white icon-ok"></i>
												<spring:message code="conciliacion.botonAceptar" />
											</button>
											<button type="button" class="btn btn-primary btn-small"
												id="btnCancelar" name="btnCancelar">
												<i class="icon-white icon-remove"></i>
												<spring:message code="conciliacion.botonCancelar" />
											</button>
										</div>
									</div>			
									<c:if test="${transDto.errorNingunaModificacion}">
										<div class="alert alert-error">
											<a class="close" data-dismiss="alert">×</a><strong>Error!</strong><br />${transDto.descripcionNingunaModificacion}
										</div>
									</c:if>
								</form:form>
								<form:form id="formRetorno" commandName="registroDto">
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- end #container -->

	<!-- FIN WEB -->

<script>	
	window.onload = function() {
		habilitarAsterisco();
		
		//Recordar que cada metodo del controller que invoque esta pagina debe setear esta variable
		if(<c:out value = "${imprimibleArchivo}"/>) {
			window.location ="${pageContext.request.contextPath}/abrir-documento-comprobante";
		}
	};
 
	function eliminarComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-eliminar-comprobante-conciliacion";
		$('#formModificacion')[0].submit();
	};
	
	function abrirComprobante(idComprobante) {
		$('#bloqueEspera').trigger('click');
		$("#idComprobanteSelected").val(idComprobante);
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-abrir-comprobante-conciliacion";
		$('#formModificacion')[0].submit();
	};
	
	function confirmar() {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-confirmacion-conciliacion";
		$('#formModificacion')[0].submit();
	};
	
	function habilitarAsterisco(){
		 var codigoCliente = $('#cuit').val().split(" ").join("");
		 if(codigoCliente!=""){
			 document.getElementById("mostrarAsterisco").style.display = 'inline';
		 }else{
			 document.getElementById("mostrarAsterisco").style.display = 'none';
		 };
	};
	 
	 $('#btnAdjuntar').click(function() {
		    $('#bloqueEspera').trigger('click');
			$('#operation').val('<%=Constantes.SUBIR_COMPROBANTE%>');
		    $('#formModificacion')[0].action="${pageContext.request.contextPath}/procesar-alta-comprobante-conciliacion";
		    $('#formModificacion')[0].submit();
	 });
	 
	 $('#btnAceptar').click(function() {
			$('#bloqueEspera').trigger('click');
			$('#operation').val('<%=Constantes.EDITAR_TRANSFERENCIA%>');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/conciliacion-aceptar-modificacion";
   			$('#formModificacion')[0].submit();	
		 });
	 
	 $('#btnCancelar').click(function() {
			$('#bloqueEspera').trigger('click');				
			$('#formRetorno')[0].action="${pageContext.request.contextPath}/conciliacion-volver-resultado-conciliacion";
			$('#formRetorno')[0].submit();	
	 }); 
</script>

</body>
</html>