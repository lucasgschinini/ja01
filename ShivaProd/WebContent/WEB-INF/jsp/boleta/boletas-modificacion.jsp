<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="boleta.modificacion.titulo.pagina"/></title>
	
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	
</head>

<body onload="bloquearOperacionAsociada()">

	<div id="container">
		<jsp:include page="../template/cabecera.jsp"></jsp:include>

		<div id="main">

			<div class="wrap">

				<jsp:include page="../template/menu.jsp"></jsp:include>

				<div id="content">

					<div id="inside">

						<div id="payments" class="box">
							<div class="title"><spring:message code="boleta.modificacion.titulo.pagina"/></div>

							<div class="pagos_anticipos">
								<p>
									<strong><spring:message code="boleta.datosGenerales"/></strong>
								</p>

							<form:form id="formModificacion" commandName="boletaSinValorDto" 
												action="${pageContext.request.contextPath}/procesar-modificacion-boleta">		
									
									<form:hidden path="id"/>
									<form:hidden path="timeStamp"/>
									<form:hidden path="operation"/>
									<form:hidden path="idEmpresa"/>
									<form:hidden path="idSegmento"/>
									<form:hidden path="idAcuerdo"/>
									<form:hidden path="idOrigen"/>
									<form:hidden path="idAnalista"/>
									<form:hidden path="motivo"/>
									<form:hidden path="copropietario"/>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa"><spring:message code="boleta.empresa"/></label>
											<div class="controls">
												<input id="empresa" name="empresa" type="text"
														value="${boletaSinValorDto.empresa}" class="input"  readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="segmento"><spring:message code="boleta.segmento"/></label>
											<div class="controls">
												<input id="segmento" name="segmento" type="text"
														value="${boletaSinValorDto.segmento}" class="input"  readonly>
											</div>
										</div>
									</div>


									<div class="row">
										<div class="span3">
											<label class="control-label" for="codClienteLegado"><spring:message code="boleta.codClienteLegado"/></label>
											<div class="controls">
												<input id="codCliente" name="codCliente" type="text"
													value="${boletaSinValorDto.codCliente}" readonly class="input">
											</div>
										</div>

										<div class="span6">
											<label class="control-label" for="razonSocialClienteLegado"><spring:message code="boleta.razonSocialClienteLegado"/></label>
											<div class="controls">
												<input id="razonSocialClienteLegado" name="razonSocialClienteLegado" type="text"
													<%-- value="${boletaSinValorDto.razonSocialClienteLegado}" --%> readonly class="span6">
											</div>
										</div>
								   </div>
								   
									<div class="row">
										<div class="span3">
											<label class="control-label" for="codClienteAgrupador"><spring:message code="boleta.codClienteAgrupador"/></label>

											<div class="controls">
												<input id="codClienteAgrupador" name="codClienteAgrupador" type="text"
													value="${boletaSinValorDto.codClienteAgrupador}" readonly class="input">
											</div>

										</div>

										<div class="span6">
											<label class="control-label" for="razonSocialClienteAgrupador"><spring:message code="boleta.razonSocialClienteAgrupador"/></label>
											<div class="controls">
												<input id="razonSocialClienteAgrupador" name="razonSocialClienteAgrupador" type="text"
													value="${boletaSinValorDto.razonSocialClienteAgrupador}" readonly class="span6">
											</div>
										</div>
								   </div>
								   
								   
								   
								   
								   	<div class="row">
										<div class="span3">
											<label class="control-label" for="numeroHolding"><spring:message code="boleta.numeroHolding"/></label>

											<div class="controls">
												<input id="numeroHolding" name="numeroHolding" type="text"
													value="${boletaSinValorDto.numeroHolding}" readonly class="input">
											</div>

										</div>

										<div class="span6">
											<label class="control-label" for="nombreHolding"><spring:message code="boleta.nombreHolding"/></label>
											<div class="controls">
												<input id="nombreHolding" name="nombreHolding" type="text"
													value="${boletaSinValorDto.nombreHolding}" readonly class="span6">
											</div>
										</div>
								   </div>
								   
								   
								   
								   
								   
								<div class="row">	
									<div class="span3">
										<label class="control-label" for="email"><spring:message code="boleta.email"/></label>								
										<div class="controls">
											<input id="email" name="email" type="text" class="input" maxlength="50"
												value="${boletaSinValorDto.email}">
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
										<label class="control-label" for="nombreAnalista"><spring:message code="boleta.analista"/></label>
										<div class="controls" style="white-space: nowrap;">
											<input class="input" id="nombreAnalista" name="nombreAnalista" type="text" value="${boletaSinValorDto.nombreAnalista}" readonly>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="idCopropietario"><spring:message code="boleta.copropietario"/></label>
										<div id="ctrlCopropietario" class="controls">
											<select id="selectCopropietario" name="idCopropietario" class="input">
												<c:if test="${boletaSinValorDto.comboCopropietario}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${copropietarios}" var="cop">
													<c:choose>
														<c:when test="${cop.tuid eq boletaSinValorDto.idCopropietario}">
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
										<form:errors path="idAnalista" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="copropietario" cssClass="error" />
									</div>
								</div>
									
								<div class="row">
									<div class="span3">
										<label class="control-label" for="numeroBoleta"><spring:message code="boleta.nroBoleta"/></label>
										<div class="controls">
											<input id="numeroBoleta" name="numeroBoleta" type="text"
													value="${boletaSinValorDto.numeroBoleta}" class="input"  readonly>
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="estado"><spring:message code="boleta.estado"/></label>
										<div class="controls">
											<input id="estado" name="estado" type="text"
													value="${boletaSinValorDto.estado}" class="input" readonly>
										</div>
									</div>
								</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="importe"><spring:message code="boleta.importe"/></label>
											<div class="controls">
												<input id="importe" name="importe" type="text" 
													value="${boletaSinValorDto.importe}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idMotivo"><spring:message code="boleta.motivo"/></label>
											<div id="ctrlMotivo" class="controls">
												<select id ="selectMotivo" name="idMotivo" class="input">
												<option value=""><spring:message code="combo.seleccionar"/></option>
												<c:forEach items="${motivos}" var="mot">
													<c:choose>
														<c:when test="${mot.idMotivo eq boletaSinValorDto.idMotivo}">
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
											<label class="control-label" for="operacionAsociada"><spring:message code="boleta.operacionAsociada"/></label>
											<div class="controls">
												<input id="operacionAsociada" name="operacionAsociada" type="text" class="input" maxlength="8"
													value="${boletaSinValorDto.operacionAsociada}">
											</div>
										</div>
									</div>

									<div class="row rowError">
										<div class="span3"></div>
										<div class="span3"></div>
										<div class="span3">
											<form:errors path="operacionAsociada" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="origen"><spring:message code="boleta.origen"/></label>
											<div class="controls">
												<input id="origen" name="origen" type="text"
														value="${boletaSinValorDto.origen}" class="input" readonly>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="span3">
											<label class="control-label" for="acuerdo"><spring:message code="boleta.acuerdo"/></label>
											<div class="controls">
												<input id="acuerdo" name="acuerdo" type="text"
													class="input" required value="${boletaSinValorDto.acuerdo}" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="bancoDeposito"><spring:message code="boleta.bancoDeposito"/></label>
											<div class="controls">
												<input id="bancoDeposito" name="bancoDeposito" type="text"
														value="${boletaSinValorDto.bancoDeposito}" class="input" readonly>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="nroCuenta"><spring:message code="boleta.nroCuenta"/></label>
											<div class="controls">
												<input id="nroCuenta" name="nroCuenta" type="text"
														value="${boletaSinValorDto.nroCuenta}" class="input" readonly>
											</div>
										</div>
									</div>
								
									<label class="control-label" for="observaciones"><spring:message code="boleta.observaciones"/></label>
									<textarea class="field span9" id="observaciones" name="observaciones" maxlength="250">${boletaSinValorDto.observaciones}</textarea>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="observaciones" cssClass="error" />
										</div>
									</div>
									<div class="row" style="margin-top: 20px; margin-bottom: 10px">
										<div class="span9">
											<label class="control-label" for="observacionMail"><spring:message code="boleta.observacionMail"/></label>
											<textarea class="field span9" id="observacionMail" name="observacionMail" maxlength="250">${boletaSinValorDto.observacionMail}</textarea>
										</div>
									</div>
									
									<div class="row rowError">
										<div class="span3">
											<form:errors path="observacionMail" cssClass="error" />
										</div>
									</div>
									
									<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button" onclick="javascript:modificar();"><i class="icon-white icon-ok"></i><spring:message code="boleta.boton.aceptar"/></button>
											<button class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar" type="button" onclick="javascript:volver();">
												<i class="icon-white icon-remove"></i> <spring:message code="boleta.boton.cancelar"/>
											</button>
										</div>
									</div>
									<c:if test="${boletaSinValorDto.errorNingunaModificacion}">
										<div class="alert alert-error">
											<a class="close" data-dismiss="alert">×</a><strong>Error!</strong><br />${boletaSinValorDto.descripcionNingunaModificacion}
										</div>
									</c:if>
								</form:form>
							</div>
							
						</div>	
					</div>
				</div>
				<!-- end #content -->
			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- end #container -->

	<!-- FIN WEB -->

	<script>
		function volver() {
			$('#bloqueEspera').trigger('click');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/boletas-busqueda";
			$('#formModificacion')[0].submit();	
		}

		function modificar() {
			$('#bloqueEspera').trigger('click');
			$("#copropietario").val(obtenerDescripcionSelect("selectCopropietario"));
			$("#motivo").val(obtenerDescripcionSelect("selectMotivo"));
							 
			$('#formModificacion')[0].submit();
		}
		
		window.onload = function() {
			$("#copropietario").val(obtenerDescripcionSelect("selectCopropietario"));
			$("#motivo").val(obtenerDescripcionSelect("selectMotivo"));
		};
		
		 $("#selectCopropietario").change(function () {
			 $("#copropietario").val(obtenerDescripcionSelect("selectCopropietario"));
		 });
		 
		 $("#selectMotivo").change(function () {
			 $("#motivo").val(obtenerDescripcionSelect("selectMotivo"));
		 });

	</script>
	
</body>
</html>