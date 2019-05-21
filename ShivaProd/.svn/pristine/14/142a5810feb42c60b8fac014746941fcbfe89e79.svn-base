<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
response.setHeader("pragma", "no-cache");              
response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
response.setHeader("Expires", "0"); 
%>

	<div id="header">
	
		<div class="wrap" >
		
			<div id="logos" style="text-align:center">
				 <img title="Telecom" src="${pageContext.request.contextPath}/img/logo.png" alt="Telecom" style="margin: 8px 6px 6px 6px">
				 <img title="Personal" src="${pageContext.request.contextPath}/img/Logo-Personal.png" alt="Personal" style="margin: 8px 1px 2px 6px">
				 <img title="Cablevision" src="${pageContext.request.contextPath}/img/Logo-CableVision.png" alt="Cablevisión" style="margin: 8px 6px 6px 6px">
				 <img title="Fibertel" src="${pageContext.request.contextPath}/img/Logo-Fibertel.png" alt="Fibertel" style="margin: 8px 6px 6px 6px">
				 <img title="Fibercorp" src="${pageContext.request.contextPath}/img/Logo-Fibercorp.jpg" alt="Fibercorp" style="margin: 2px 6px 6px 6px">
    			 
			</div>
		</div>

	</div>
	
	<div id="sso">
		<div class="wrap">
			<c:if test="${sessionScope.loginUserName != null}">
				<p><spring:message code="titulo.bienvenido"/>, <strong><c:out value="${sessionScope.loginUserName}" /></strong></p>
				<ul>
					<li><a href="${pageContext.request.contextPath}/salir" title="Salir"><spring:message code="accion.salir"/></a></li>
				</ul>
			</c:if> 
		
			<c:if test="${sessionScope.loginUserName == null}">
				<p><spring:message code="titulo.bienvenido"/> </p>
			</c:if> 
		</div>
	</div>
