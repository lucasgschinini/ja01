<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags"prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="recibo.alta.titulo"/></title>
   
   	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
 
    <meta name="Alta de Talonarios" content="Modelo">
 	
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
						<div class="title"><spring:message code="recibo.alta.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="recibo.altaTalonario"/></strong></p>

							<form:form id="formAlta" commandName="talonarioDto" action="${pageContext.request.contextPath}/procesar-alta-talonario">
								<form:hidden path="operation"/>
								<form:hidden path="empresa"/>
								<form:hidden path="segmento"/>				
								<div class="row">
									<div class="span3">
										<label class="control-label" for="idEmpresa" ><span class="rojo">* </span><spring:message code="recibo.empresa"/></label>
										<div class="controls">
											<select id ="selectEmpresa" name="idEmpresa" class="input">
												<c:if test="${talonarioDto.comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq talonarioDto.idEmpresa}">
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
										<label class="control-label" for="idSegmento"><span class="rojo">* </span><spring:message code="recibo.segmento"/></label>
										<div id="ctrlSegmento" class="controls">
											<select id ="selectSegmento" name="idSegmento" class="input">
												<c:if test="${talonarioDto.comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq talonarioDto.idSegmento}">
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
											
								<div class="row">
									<div class="span3">
										<label class="control-label" for="nroSerie"><span class="rojo">* </span><spring:message code="recibo.nroSerie"/></label>
										<div class="controls">
											<input id="nroSerie" name="nroSerie" maxlength="4" type="text"
											value="${talonarioDto.nroSerie}" class="input">
										</div>							
									</div>
									
									<div class="span3">
										<label class="control-label" for="rangoDesde"><span class="rojo">* </span><spring:message code="recibo.nroReciboDesde"/></label>
										<div class="controls">
											<input id="rangoDesde" name="rangoDesde" maxlength="8" type="text"
												value="${talonarioDto.rangoDesde}" class="input">
										</div>
									</div>
									
									<div class="span3">
										<label class="control-label" for="rangoHasta"><span class="rojo">* </span><spring:message code="recibo.nroReciboHasta"/></label>
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
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="aceptar" name="aceptar" type="submit"><i class="icon-white icon-ok"></i><spring:message code="recibo.botonAceptar"/></button>
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
	 $('#aceptar').click(function() {
		$("#empresa").val(obtenerDescripcionSelect("selectEmpresa"));
		$("#segmento").val(obtenerDescripcionSelect("selectSegmento"));
		$('#bloqueEspera').trigger('click');
	 });
	 
	 $("#selectEmpresa").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formAlta')[0].action="${pageContext.request.contextPath}/talonario-alta-seleccionoEmpresa";
		$('#formAlta')[0].submit();
	 });
	</script>
	</body>
</html>