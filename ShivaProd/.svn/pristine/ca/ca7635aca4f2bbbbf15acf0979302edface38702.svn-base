<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="recibo.modificacionTalonario.titulo"/></title>
   
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
						<div class="title"><spring:message code="recibo.modificacionTalonario.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="recibo.modificacionTalonario.titulo"/></strong></p>

							<form:form id="formModificacion" commandName="talonarioDto" action="${pageContext.request.contextPath}/procesar-modificacion">
									
								<input type="hidden" name="operation" value="1"/>
								<input type="hidden" name="pantallaDestino" value="${talonarioDto.pantallaDestino}"/>	
								<input type="hidden" id="idTalonario" name="idTalonario" value="${talonarioDto.idTalonario}"/>
								<input type="hidden" name="timeStamp" value="${talonarioDto.timeStamp}"/>	
								<input type="hidden" name="timeStampAux" value="${talonarioDto.timeStampAux}"/>	
								<form:hidden path="url" id="url"/>
								
								<div id="bloqueAdmin">		
									<div class="row">
										<div class="span3">
											<label class="control-label" for="idEmpresa"><c:if test="${sessionScope.loginUser.esAdminTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.empresa"/></label>
											<div class="controls ">
												<c:choose>
													<c:when test="${sessionScope.loginUser.esSupervisorTalonario}">
														<input type="text" name="empresa" value="${talonarioDto.empresa}" readonly/>
														<input type="hidden" name="idEmpresa" value="${talonarioDto.idEmpresa}"/>
													</c:when>
													<c:otherwise>
														<select id="idEmpresa" name="idEmpresa" class="input">
															<c:forEach items="${empresas}" var="emp">
																<c:choose>
																	<c:when test="${emp.idEmpresa eq talonarioDto.idEmpresa}">
																		<option value="${emp.idEmpresa}|${emp.descripcion}" selected>${emp.descripcion}</option>
																	</c:when>
																	<c:otherwise>
																			<option value="${emp.idEmpresa}|${emp.descripcion}">${emp.descripcion}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach> 
														</select>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="idSegmento"><c:if test="${sessionScope.loginUser.esAdminTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.segmento"/></label>
											<div class="controls">
											<c:choose>
												<c:when test="${sessionScope.loginUser.esSupervisorTalonario}">
													<input type="text" name="segmento" value="${talonarioDto.segmento}" readonly/>
													<input type="hidden" name="idSegmento" value="${talonarioDto.idSegmento}"/>
												</c:when>
												<c:otherwise>
													<select id="idSegmento" name="idSegmento" class="input">
														<c:forEach items="${segmentos}" var="seg">
															<c:choose>
																<c:when test="${seg.idSegmento eq talonarioDto.idSegmento}">
																	<option value="${seg.idSegmento}|${seg.descripcion}" selected>${seg.descripcion}</option>
																</c:when>
																<c:otherwise>
																		<option value="${seg.idSegmento}|${seg.descripcion}">${seg.descripcion}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach> 
													</select>
												</c:otherwise>
											</c:choose>
											</div>
										</div>
									</div>
									<div class="row rowError" >
											<div class="span3">
												<form:errors path="idEmpresa" cssClass="error" />
											</div>
	<!-- 										<div class="span3"> -->
	<%-- 											<form:errors path="idSegmento" cssClass="error" /> --%>
	<!-- 										</div> -->
										</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="nroSerie"><c:if test="${sessionScope.loginUser.esAdminTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.nroSerie"/></label>
											<div class="controls">
												<input id="nroSerie" name="nroSerie" maxlength="4" type="text"
													value="${talonarioDto.nroSerie}" class="input">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="rangoDesde"><c:if test="${sessionScope.loginUser.esAdminTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.nroReciboDesde"/></label>
											<div class="controls">
												<input id="rangoDesde" name="rangoDesde" maxlength="8" type="text"
													value="${talonarioDto.rangoDesde}" class="input">
											</div>
										</div>
										
										<div class="span3">
											<label class="control-label" for="rangoHasta"><c:if test="${sessionScope.loginUser.esAdminTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.nroReciboHasta"/></label>
											<div class="controls">
												<input id="rangoHasta" name="rangoHasta" maxlength="8" type="text"
													value="${talonarioDto.rangoHasta}" class="input">
											</div>							
										</div>
							      	</div>
									<div class="row rowError" >
										<div class="span3">
											<form:errors path="nroSerie" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="rangoDesde" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="rangoHasta" cssClass="error" />
										</div>
									</div>
								</div>	
								<div class="row" id="bloqueGestor">
									<div class="span3">
										<label class="control-label" for="usuarioGestor"><c:if test="${sessionScope.loginUser.esSupervisorTalonario}"><span class="rojo">* </span></c:if><spring:message code="recibo.gestor"/></label>
										<div class="controls">
											<select id="usuarioGestor" name="usuarioGestor" class="input">
												<c:if test="${!talonarioDto.esAsignado}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${listaGestores}" var="ges">
													<c:choose>
														<c:when test="${ges.idGestor eq talonarioDto.idUsuarioGestor}">
															<option value="${ges.idGestor}_${ges.nombreYApellido}" selected>${ges.nombreYApellido}</option>
														</c:when>
														<c:otherwise>
															<option value="${ges.idGestor}_${ges.nombreYApellido}">${ges.nombreYApellido}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
										</div>
									</div>
								</div>

								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="aceptar" name="aceptar" type="button" onclick="javascript:modificarTalonario();" style="margin-right: 5px"><i class="icon-white icon-ok"></i><spring:message code="recibo.botonAceptar"/></button>
										<button class="btn btn-primary btn-small" id="cancelar" name="cancelar" type="button" onclick="javascript:volverBusquedaTalonario();"><i class="icon-white icon-remove"></i><spring:message code="recibo.botonCancelar"/></button>
									</div>
								</div>
								<c:if test="${talonarioDto.errorNingunaModificacion}">
									<div class="alert alert-error">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br />${talonarioDto.descripcionNingunaModificacion}
									</div>
								</c:if>

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
	function modificarTalonario() {
		$('#bloqueEspera').trigger('click');
		$('#formModificacion')[0].submit();	
 	}
	
	function volverBusquedaTalonario() {
			$('#bloqueEspera').trigger('click');
			if ('${talonarioDto.pantallaDestino}'=='<%=Constantes.DESTINO_BANDEJA_ENTRADA%>') {
				$('#formModificacion')[0].action="${pageContext.request.contextPath}/regresar-bandeja-de-entrada";
			} else {
				$('#formModificacion')[0].action="${pageContext.request.contextPath}/volver-busqueda";	
			}
			
			$('#formModificacion')[0].submit();	
	}
	
	$("#idSegmento").change(function () {
		if ('${sessionScope.loginUser.esSupervisorTalonario}' === true){
			$('#bloqueEspera').trigger('click');
			$('#formModificacion')[0].action="${pageContext.request.contextPath}/comboGestor";
			$('#formModificacion')[0].submit();
		}
	});
	
	$("#idEmpresa").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#url').val("talonario/talonario-modificacion");
		$('#formModificacion')[0].action="${pageContext.request.contextPath}/talonario-modificacion-seleccionoEmpresa";
		$('#formModificacion')[0].submit();
	});
	
	$('#bloqueGestor').ready(function(){
		if ('${sessionScope.loginUser.esAdminTalonario}'=='true'){
			$('#bloqueGestor').attr('style','display: none;');
		}else{
			$('#bloqueGestor').attr('style','display: block;');
		}
	});
	
	$('#bloqueAdmin').ready(function(){
		if ('${sessionScope.loginUser.esSupervisorTalonario}'=='true'){
			$('#idEmpresa').attr('readonly',true);
			$('#idSegmento').attr('readonly',true);
			$('#rangoDesde').attr('readonly',true);
			$('#rangoHasta').attr('readonly',true);
			$('#nroSerie').attr('readonly',true);
		}
	});
</script>
</body>
	
</html>