<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setHeader("Cache-Control",
			"no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setDateHeader("Expires", 0); // Proxies.
%>

<html>
<head>
<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
</head>
<body>

	<div id="container">

		<jsp:include page="../template/cabecera.jsp"></jsp:include>

		<div id="main">

			<div class="wrap">

				<jsp:include page="mantenimientoMenu.jsp"></jsp:include>

				<div id="content">

					<div id="inside">
						<div id="payments" class="box">
							<div class="title">Tablero de Control / Mail</div>

							<p>
								<strong>Mail - Propiedades del Correo</strong>
							</p>
							<div class="alert alert-error">
								<div class="row">
									<div class="span8">
										<label class="control-label">Host:&nbsp;${mail_host}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Port:&nbsp;${mail_port}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Mail
											FROM:&nbsp;${mail_from}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Autenticacion:&nbsp;${mail_auth}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Encode:&nbsp;${mail_encoding}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Debug:&nbsp;${mail_debug}</label>
									</div>
								</div>

							</div>
							<p>
								<strong>Mail - Prueba del Correo</strong>
							</p>
							<div class="alert alert-error">
								<div class="row">
									<div class="span8">
										<label class="control-label">Se enviara el correo de
											prueba a los siguientes destinatarios:&nbsp;</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">${mail_toTest}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label"></label>
										<div class="controls">
											<span><a
												href="${pageContext.request.contextPath}/mantenimientoMailTest.htm">Probar
													Correo</a> <span> | </span> Resultado: <br>${mail}</span>
										</div>
									</div>
								</div>
							</div>
							<div class="row" style="margin: 25px 30px;">
								<div align="right">
									<button class="btn btn-primary btn-small"
										onclick="window.location.href='${pageContext.request.contextPath}/principal';">
										<i class="icon-white icon-home"></i> Inicio
									</button>
								</div>
							</div>
						</div>
					</div>
					<!-- end #"inside" -->
				</div>
				<!-- end #payments -->

			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->

		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div>
	<!-- end #container -->

</body>
</html>