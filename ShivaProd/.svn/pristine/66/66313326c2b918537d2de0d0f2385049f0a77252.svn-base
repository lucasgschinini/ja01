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
							<div class="title">Tablero de Control / Aplicacion</div>

							<p>
								<strong>Propiedades</strong>
							</p>
							<div class="alert alert-error">
								<div class="row">
									<div class="span8">
										<label class="control-label">Entorno: ${entorno}</label>
									</div>
								</div>
							</div>

							<!-- 							<p><strong>Otras pruebas</strong></p> -->
							<!-- 							<div class="alert alert-error"> -->
							<!-- 								<div class="row"> -->
							<!-- 									<div class="span8"> -->
							<!-- 									<form id=primeraParteFormulario method="post"> -->
							<!-- 									<button class="btn btn-primary btn-small" id="btn" type="button" onclick="javascript:enviar();"> -->
							<!-- 									Probar 5000 Parametros</button> -->
							<!-- 									</form> -->
							<!-- 									</div>									 -->
							<!-- 								</div> -->
							<!-- 							</div>	 -->

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

	<script type="text/javascript">
		function enviar() {
			var cantMaxParametros = 5000;
			var parametros = "";
			for (var i = 0; i <= cantMaxParametros; i++) {
				parametros += "p" + i + "=" + i;
				if (i < cantMaxParametros) {
					parametros += "&";
				} else {
					alert("Ultimo parametro: " + i);
				}
			}
			document.getElementById("primeraParteFormulario").action = "${pageContext.request.contextPath}/mantenimientoParametros?"
					+ parametros;
			document.getElementById("primeraParteFormulario").submit();
		}
	</script>
</body>
</html>