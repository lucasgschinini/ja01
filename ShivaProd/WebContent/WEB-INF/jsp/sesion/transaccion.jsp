<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	<style type="text/css">
		table.title{
			border-radius: 4px;
			width: 100%;
		}
		td.title, div.title{
			font-family: verdana;
			font-size: 14px;
			font-weight: bold;
			color: #FFFFFF;
			background-color: #1081C7;
			border: 1px solid #ccc;
		}
		.error{
			color: red;
			font-weight: bold;
		}
		.divError {
			border: 1px solid #ccc;
			OVERFLOW: auto;
			width: 800px;
			height: 240px;
		}
	</style>  
</head>
<body>
	<div id="container">
		<jsp:include page="../template/cabecera.jsp"></jsp:include>
		<div id="main">
			<div class="wrap">
				<div id="content">
					<div id="inside">
							<div class="alert alert-error">
								<strong>Cantidad de ejecuciones del servicio</strong><br />
								<br><spring:message code="error.proceso.titulo"/><br>
								<br><spring:message code="error.fechaHora"/>&nbsp;${errorFechaHora}<br>
								<br><spring:message code="error.numero.error"/>&nbsp;${timeError}<br>
							</div><br>
					</div><!-- end #"inside" -->
				</div><!-- end #payments -->
			</div><!-- end .wrap -->
		</div><!-- end #main -->
		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	</div><!-- end #container -->
</body>
</html>
