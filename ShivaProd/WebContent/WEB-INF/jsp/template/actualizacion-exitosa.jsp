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
								<p><strong>${mensaje}</strong></p>
							</div>

							<form id=primeraParteFormulario>		
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="center">
										<button class="btn btn-primary btn-small" id="btnAceptar" type="submit" onclick="javascript:volver();">
										<i class="icon-white icon-ok"></i><spring:message code="accion.aceptar"/></button>
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
		function volver() {
			document.getElementById("primeraParteFormulario").action="${pageContext.request.contextPath}/"+"${url}";
		}
		
		$(document).ready(function(){
			$('#btnAceptar').click(function() {
				$('#bloqueEspera').trigger('click');
			});
		});
	</script>
</body>
</html>