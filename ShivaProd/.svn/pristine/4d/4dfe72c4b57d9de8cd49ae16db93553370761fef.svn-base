<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="titulo.bienvenido" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.signin.css"/>
<style>
	
	.alert {
			padding: 8px 35px 8px 14px;
			margin-bottom: 18px;
			text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
			color: #c09853;
			background-color: #fcf8e3;
			border-color: #eed3d7;
			border: 1px solid #fbeed5;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
	}
	

</style>
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
						<div class="title">&nbsp;</div>
						
						<div class="pagos_anticipos" style="margin-top: 160px; margin-bottom: 160px;">
							
							<div align="center">
									<c:forEach items="${mensajeAlta}" var="acu">									
										<p><strong><c:out value="${acu}"/></strong></p>
									</c:forEach>
							</div>
							
							<div align="center">
									<c:forEach items="${mensajesMostrarEnvioMail}" var="envioMail">
										<p><strong><c:out value="${envioMail}"/></strong></p>
									</c:forEach>
							</div>

							<form id=primeraParteFormulario>		
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="center">
									 	<a href="${url}" class="btn btn-primary btn-small" id="btnAceptar" name="btnAceptar"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="actualizacionOK.boton.aceptar"/></a>  
									</div>
								</div>
								<div class="container form-signin">
									<c:if test="${error}">
										<div class="alert">
											<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.advertencia"/></strong><br />${errorMensaje}
										</div>
									</c:if>
								</div>
							</form>
						</div>
						
					</div>
				</div>
			</div><!-- end #content -->
		</div><!-- end .wrap -->

	</div><!-- end #main -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div><!-- end #container -->

	<script>
	
		window.onload = function() {
			<%if (request.getAttribute("imprimible")!=null) {
				Boolean imprimible = (Boolean) request.getAttribute("imprimible");
				if (Boolean.TRUE.equals(imprimible)) {%>
					window.location ="${pageContext.request.contextPath}/abrir-pdf";
				<%}
			}%>
		};
		
		$('#btnAceptar').click(function() {
			$('#bloqueEspera').trigger('click');
		});
	
		
	</script>
</body>
</html>