<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="titulo.bienvenido" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-detalle.js"></script>
<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-cobro-relacionado.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/custom-paginacion-datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-datatables-shiva.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.signin.css"/>
<script>
function volverBusqueda() {
	$('#goBack').val("true");
	$('#formAnulacionOk')[0].action=urlAsynchronousHTTP + $('#idVolver').val();
	$('#formAnulacionOk')[0].submit();
};
</script>
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
	<form:form id="formAnulacionOk" method="GET">
		<input type="hidden" id="volver" name="volver" value="/anularLegajo" />
		<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}" />
		<input type="hidden" id="goBack" name="goBack" />
	</form:form>
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
							
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="center">
									  	<button onclick="javascript:volverBusqueda();" class="btn btn-primary btn-small" id="btnAceptar"  >  <i class="icon-white icon-ok">   </i>   <spring:message code="actualizacionOK.boton.aceptar"/></button>  
									</div>
								</div>
						</div>
						
					</div>
				</div>
			</div><!-- end #content -->
		</div><!-- end .wrap -->

	</div><!-- end #main -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div><!-- end #container -->
	
</body>
</html>