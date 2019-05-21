<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="titulo.bienvenido"/></title>
   
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

					</div>
				
				</div><!-- end #content -->

			</div><!-- end .wrap -->

		</div><!-- end #main -->

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div><!-- end #container -->

    
	</body>
</html>