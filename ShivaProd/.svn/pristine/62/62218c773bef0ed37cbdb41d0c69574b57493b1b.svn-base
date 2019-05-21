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
	<title><spring:message code="boleta.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/valor/boletas-alta.js"></script>
	<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
	<script src="${pageContext.request.contextPath}/js/valor/validaciones.valor.js"></script>
	<link href="${pageContext.request.contextPath}/css/custom-combobox.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	
    <meta name="Alta de Boletas sin Valor" content="Modelo">
 	
 	<script>
 		var TipoValorEnum = ${TipoValorEnum};
 		var paramTipoGestion = ${paramTipoGestion};
 		var leyendaComboSeleccionar = '${leyendaComboSeleccionar}';
 		var esAnalista = ${esAnalista};
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
						<div class="title"><spring:message code="boleta.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="boleta.datosGenerales"/></strong></p>
							<form:form id="formBoleta" method="POST">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<input type="hidden" id="mensajeAlta" name="mensajeAlta" />
								<input type="hidden" id="mensajesMostrarEnvioMail" name="mensajesMostrarEnvioMail" />
								<input type="hidden" id="url" name="url" />
								<input type="hidden" id="imprimible" name = "imprimible" />
							</form:form>
							<form:form id="formAlta" commandName="boletaSinValorDto" action="${pageContext.request.contextPath}/procesar-alta-boleta">		
								<form:hidden path="operation"/>	
								<form:hidden path="idAnalista"/>
								<form:hidden path="empresa"/>
								<form:hidden path="segmento"/>
								<form:hidden path="origen"/>
								<form:hidden path="acuerdo"/>
								<form:hidden path="bancoDeposito"/>
								<form:hidden path="nroCuenta"/>
								<form:hidden path="motivo"/>
								<form:hidden path="copropietario"/>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idEmpresa"><span class="rojo">* </span><spring:message code="boleta.empresa"/></label>
										<div class="controls ">
											<select id ="selectEmpresa" name="idEmpresa" class="input">
												<c:if test="${boletaSinValorDto.comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq boletaSinValorDto.idEmpresa}">
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
										<label class="control-label" for="idSegmento"><span class="rojo">* </span><spring:message code="boleta.segmento"/></label>
										<div id="ctrlSegmento" class="controls">
											<select id ="selectSegmento" name="idSegmento" class="input">
												<c:if test="${boletaSinValorDto.comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq boletaSinValorDto.idSegmento}">
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
										<span id="errorEmpresa" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorSegmento" class="error"></span>
									</div>
								</div>
											
								<div class="row">
									<div class="span3">
										<label class="control-label" for="codCliente">
											<span class="rojo">* </span><spring:message code="boleta.codClienteLegado"/>
										</label>
										<div class="controls">
											<input id="codCliente" name="codCliente" type="text" class="input" maxlength="11"
												value="${boletaSinValorDto.codCliente}"	style="margin: 0 auto;" />
										</div>
									</div>
							
								<div class="span6">
										<label class="control-label" for="razonSocial"><spring:message code="boleta.razonSocialClienteLegado"/></label>
										<div class="controls">
											<input id="razonSocial" name="razonSocial" type="text" 
												   class="input span6" readonly />
										</div>							
									</div>
								</div>
								
							   <div class="row rowError" >
									<div class="span3">
										<span id="wsConsultaClienteSiebel" class="error" style="display: none;"></span>
										<span id="errorCodCliente" class="error"></span>
									</div>
									<div class="span6">
										<span id="errorRazonSocialClienteLegado" class="error"></span>
									</div>
								</div>
								

								<div class="row">
									<div class="span3">
										<label class="control-label" for="codClienteAgrupador"><spring:message code="boleta.codClienteAgrupador"/>
										</label>
										<div class="controls">
											<input id="codClienteAgrupador" name="codClienteAgrupador" type="text" class="input"
												readonly value="${boletaSinValorDto.codClienteAgrupador}" style="margin: 0 auto;" />
										</div>
									</div>
							
									<div class="span6">
										<label class="control-label" for="razonSocialClienteAgrupador"><spring:message code="boleta.razonSocialClienteAgrupador"/></label>
										<div class="controls">
											<input id="razonSocialClienteAgrupador" name="razonSocialClienteAgrupador" type="text" class="input span6" 
												   readonly value="${boletaSinValorDto.razonSocialClienteAgrupador}">
										</div>							
									</div>
								</div>
								

								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="numeroHolding"><spring:message code="boleta.numeroHolding"/></label>
										<div class="controls">
											<input id="numeroHolding" name="numeroHolding" type="text" class="input"
												readonly value="${boletaSinValorDto.numeroHolding}" style="margin: 0 auto;" />
										</div>
									</div>
							
									<div class="span6">
										<label class="control-label" for="nombreHolding"><spring:message code="boleta.nombreHolding"/></label>
										<div class="controls">
											<input id="nombreHolding" name="nombreHolding" type="text" class="input span6" 
												   readonly value="${boletaSinValorDto.nombreHolding}">
										</div>							
									</div>
								</div>
								
				
											
								<div class="row">	
									<div class="span3">
											<label class="control-label" for="email">
												<c:choose>
													<c:when test="${boletaSinValorDto.checkEnviarMailBoleta }">
														<span id="labelEmail" class="rojo" style="display:inline;">* </span>
													</c:when>
													<c:otherwise>
														<span id="labelEmail" class="rojo" style="display:none;">* </span>
													</c:otherwise>
												</c:choose>
												<spring:message code="boleta.email"/></label>								
										<div class="controls">
											<input id="email" name="email" type="email" class="input" maxlength="50"
												value="${boletaSinValorDto.email}">
										</div>
									</div>
						      	</div>
						      	<div class="row rowError" >
									<div class="span3">
										<span id="errorEmail" class="error"></span>
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
										<div class="controls">
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
										<span id="errorNombreAnalista" class="error"></span>
									</div>
									<div class="span3">
										<span id="errorCopropietario" class="error"></span>
									</div>
								</div>

								<div class="row">
									<div class="span3">
										<label class="control-label" for="importe"><span class="rojo">* </span><spring:message code="boleta.importe"/></label>
										<div class="controls">
											<input id="importe" name="importe" type="text" class="input" maxlength="16"
												value="${boletaSinValorDto.importe}">
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
								<div class="row rowError" >
									<div class="span3">
										<span id="errorImporte" class="error"></span>
									</div>
									<div class="span3"></div>
									<div class="span3">
										<span id="errorOperacionAsociada" class="error"></span>
									</div>
								</div>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idOrigen"><span class="rojo">* </span><spring:message code="boleta.origen"/></label>
										<div id="ctrlOrigen" class="controls">
											<select id ="selectOrigen" name="idOrigen" class="input">
											<c:if test="${boletaSinValorDto.comboOrigen}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${origenes}" var="ori">
													<c:choose>
														<c:when test="${ori.idOrigen eq boletaSinValorDto.idOrigen}">
															<option value="${ori.idOrigen}" selected>${ori.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${ori.idOrigen}">${ori.descripcion}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<span id="errorOrigen" class="error"></span>
									</div>
								</div>
								
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idAcuerdo"><span class="rojo">* </span><spring:message code="boleta.acuerdo"/>
										</label>
										<div class="controls">
											<select id ="selectAcuerdo" name="idAcuerdo" class="input">
												<c:if test="${boletaSinValorDto.comboAcuerdo}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${acuerdos}" var="acu">
													<c:choose>
														<c:when test="${acu.idAcuerdo eq boletaSinValorDto.idAcuerdo}">
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
										<label class="control-label" for="idBancoDeposito"><span class="rojo">* </span><spring:message code="boleta.bancoDeposito"/>
										</label>
										<div class="controls">
											<select id ="selectBancoDeposito" name="idBancoDeposito" class="input">
												<c:if test="${boletaSinValorDto.comboBanco}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${bancos}" var="banco">
													<c:choose>
														<c:when test="${banco.idBanco eq boletaSinValorDto.idBancoDeposito}">
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
										<label class="control-label" for="idNroCuenta"><span class="rojo">* </span><spring:message code="boleta.nroCuenta"/>
										</label>
										<div class="controls">
											<select id ="selectCuenta" name="idNroCuenta" class="input">
											<c:if test="${boletaSinValorDto.comboCuenta}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${cuentas}" var="cuen">
													<c:choose>
														<c:when test="${cuen.idBancoCuenta eq boletaSinValorDto.idNroCuenta}">
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
								
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observaciones"><spring:message code="boleta.observaciones"/></label>
										<textarea class="field span9" id="observaciones" name="observaciones"  maxlength="250">${boletaSinValorDto.observaciones}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span9">
										<span id="errorObservaciones" class="error"></span>
									</div>
								</div>

								<div class="row" style="margin-top: 0px; margin-bottom: 0px">
									<div class="span6">
										<label class="radio inline">
										<input id="checkImprimirBoleta" name="checkImprimirBoleta" type="radio" style="margin-top: -1px;"
										<c:if test="${boletaSinValorDto.checkImprimirBoleta}">
											checked 
										 </c:if> 
										 >
										<spring:message code="boleta.check.imprimir"/></label>
									</div>
								</div>
								<div class="row" style="margin-top: 0px; margin-bottom: 10px">
									<div class="span6">
										<label class="radio inline">
										<input id="checkEnviarMailBoleta" name="checkEnviarMailBoleta" type="radio" style="margin-top: -1px;"
										<c:if test="${boletaSinValorDto.checkEnviarMailBoleta}">
											checked 
										</c:if> 
										>
										<spring:message code="boleta.check.mail"/></label>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span9">
										<span id="errorCheck" class="error"></span>
									</div>
								</div>
								
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observacionMail"><spring:message code="boleta.observacionMail"/></label>
										<textarea class="field span9" id="observacionMail" name="observacionMail" maxlength="250"
											<c:if test="${!boletaSinValorDto.checkEnviarMailBoleta}">
												disabled
											</c:if>>${boletaSinValorDto.observacionMail}</textarea>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span9">
										<span id="errorObservacionesMail" class="error"></span>
									</div>
								</div>
						
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar" type="button"><i class="icon-white icon-ok"></i><spring:message code="boleta.boton.aceptar"/></button>
									</div>
								</div>
										
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
	
	$(function() { 
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
			    	}
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
						$('#errorCodCliente').text('');
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

	}); //end function()
	</script>
    
	</body>
</html>