<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>  
</head>
<body>

	<div id="container">
		
		<jsp:include page="../template/cabecera.jsp"></jsp:include>
		
		<div id="main">
			
			<div class="wrap">
				
				<div id="content">
			
					<div id="inside">
						<div class="alert alert-error">
							<strong>Advertencia!</strong><br /><br>
							<c:if test="${error}">
								${errorMessage}
							</c:if>
							<c:if test="${not error}">
								<spring:message code="error.autenticacion.expirada"/>
							</c:if>
							<br>
						</div>
					</div>
					
				</div><!-- end #content -->
			</div><!-- end .wrap -->
		</div><!-- end #main -->
		
		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	</div><!-- end #container -->
	
</body>
</html>
</body>
</html>




