<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<title><spring:message code="titulo.ingresar"/></title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.signin.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	
<!-- JS  -->
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>      
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/spin.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.functions.js"></script>
<jsp:include page="../template/bloques.jsp"></jsp:include>	

	<style>
		[class*="col-lg-"] {
			background-color: rgba(185, 74, 72, .15);
			border: 1px solid rgba(185, 74, 72, .2);
		}

		.alert {
			padding: 8px 35px 8px 14px;
			margin-bottom: 18px;
			color: #c09853;
			text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
			background-color: #fcf8e3;
			border: 1px solid #fbeed5;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
		}
		
		.alert-heading {
			color: inherit;
		}
		
		.alert .close {
			position: relative;
			top: -2px;
			right: -21px;
			line-height: 18px;
		}
		
.alert-danger,.alert-error {
			color: #b94a48;
			background-color: #f2dede;
			border-color: #eed3d7;
}

.grisado {
	padding: 8px 8px 8px 8px;
	margin-bottom: 18px;
	color: #c09853;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	border: 1px dotted #fbeed5;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	color: #b94a48;
	background-color: #E6E6E6;
	border-color: #585858;
	position: relative;
		}
	</style>
</head>
<body>

<div id="container">

	<jsp:include page="../template/cabecera.jsp"></jsp:include>

	<div id="main">

		<div class="wrap">

			<div class="container">
					<form:form id="formLogin" class="form-signin" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST" autocomplete="off">
		    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		    		
						<input type="hidden" id="j_usar_captcha" name="j_usar_captcha" value="${usarCaptcha}" />
		        	<div class="container-fluid" style="border:0; padding-top:70px;padding-bottom: 20px">	
							<h3 class="form-signin-heading text-center" style="margin-bottom: 20px; margin-top: 5px">
								<strong><spring:message code="titulo.ingresar" /></strong>
							</h3>
		
		        		<label class="control-label" for="usuario"><spring:message code="tilulo.usuario"/></label>
		        		<div class="input-append">
								<input type="text" id="<%=Constantes.SECURITY_USERNAME%>" name="<%=Constantes.SECURITY_USERNAME%>" class="form-control" autofocus
									style="margin-bottom: 20px" value="${j_username}"> <span class="add-on"
									style="padding-bottom: 0px; padding-top: 2px; padding-left: 3px; padding-right: 3px"><i class="icon-user"></i></span>
		        		</div>
		        
		        		<label class="control-label" for="contrasena"><spring:message code="titulo.contrasena"/></label>
		        		<div class="input-append">
								<input type="password" id="j_password" name="j_password" class="form-control" style="margin-bottom: 20px"> <span class="add-on"
									style="padding-bottom: 0px; padding-top: 2px; padding-left: 3px; padding-right: 3px"><i class="icon-lock"></i></span>
		        		</div>
		        
							<c:if test="${mostrarCaptcha}">
							<div class="grisado">
								<label class="control-label" for="jcaptcha"><spring:message code="titulo.codigo.verificacion" /></label>
								<div class="input-append">
									<input id="j_captcha_response" name="j_captcha_response" type="text" class="input" value="">
								</div>
								<div style="width: 170px;">
									<img src="${pageContext.request.contextPath}/jcaptcha" id="captchaImage" />
									<div style="top: 90px; left: 183px; position: absolute;">
										<button id="refreshCaptcha" class="btn btn-primary btn-small" type="button">
											<i class="icon-white icon-refresh"></i>
										</button>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								$('#refreshCaptcha').click(function() {
								    $('#bloqueEspera').trigger('click');
								    $('#formLogin')[0].action = "${pageContext.request.contextPath}/actualizarCaptcha";
								    $('#formLogin')[0].submit();
								});
							</script>
							</c:if>
							<button id="login" class="btn btn-primary btn-small" style="margin-left: 80px; margin-right: 63px" type="submit">
		        			<i class="icon-white icon-off"></i>
		        			<spring:message code="accion.into"/>
		        		</button>
						</div>

		        		<br>

		        	<c:if test="${error}">
						<div class="alert alert-error">
							<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br />${errorMessage}
						</div>
					</c:if>
				</form:form>
		    </div> 

			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->

	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- end #container -->

</body>
<script type="text/javascript">
	$('#login').click(function() {
	    $('#bloqueEspera').trigger('click');
	});
</script>
</html>