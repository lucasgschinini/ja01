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
							<div class="title">Tablero de Control / Servicio de
								Mensajeria (JMS)</div>

							<p>
								<strong>JMS - Mic</strong>
							</p>
							<div class="alert alert-error">
								<div class="row">
									<div class="span8">
										<label class="control-label">Host:&nbsp;${mq_mic_host}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Queue
											Manager:&nbsp;${mq_mic_queueManager}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Port:&nbsp;${mq_mic_port}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">TransportType:&nbsp;${mq_mic_transportType}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Channel:&nbsp;${mq_mic_channel}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Cola
											Receptor:&nbsp;${mq_mic_queue_receptor}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Cola
											Emisor:&nbsp;${mq_mic_queue_emisor}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">&nbsp;</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Aplicacion:&nbsp;${mq_mic_aplicacion}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Servicio:&nbsp;${mq_mic_servicio}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Contrato
											1:&nbsp;${mq_mic_contrato_1}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Contrato
											2:&nbsp;${mq_mic_contrato_2}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">&nbsp;</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Estado Servidor
											MQ:&nbsp;${micEstadoServidor}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Estado Queue
											Manager:&nbsp;${micEstadoQueueManager}</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">&nbsp;</label>
									</div>
								</div>
								<div class="row">
									<div class="span8">
										<label class="control-label">Excepcion:&nbsp;${micExcepcion}</label>
									</div>
								</div>
							</div>

							<c:if test="${mostrarPruebaMic}">
								<p>
									<strong>JMS - Mic Prueba</strong>
								</p>
								<div class="alert alert-error">
									<div class="row">
										<div class="span8">
											<form id=primeraParteFormulario method="post">
												<label class="control-label">Ingrese el texto a
													enviar:</label>
												<textarea class="field span8" id="mensajeAEnviar"
													name="mensajeAEnviar" maxlength="250">${micMensajeEnviado}</textarea>
												<div align="right">
													<button class="btn btn-primary btn-small" id="btnEmisor"
														type="button" onclick="javascript:enviar(0);">
														Destino MIC</button>
													&nbsp;
													<button class="btn btn-primary btn-small" id="btnReceptor"
														type="button" onclick="javascript:enviar(1);">
														Destino SHIVA (Receptor)</button>
												</div>
											</form>
										</div>
									</div>
									<div class="row">
										<div class="span8">
											<label class="control-label">&nbsp;</label>
										</div>
									</div>
									<div class="row">
										<div class="span8">
											<label class="control-label">Estado Mensaje
												Enviado:&nbsp;${micEstadoMensajeADCRecepcion}</label>
										</div>
									</div>
									<div class="row">
										<div class="span8">
											<label class="control-label">Excepcion:&nbsp;${micExcepcionEnvioMensajeADCRecepcion}</label>
										</div>
									</div>
									<div class="row">
										<div class="span8">
											<div align="center">
												<button class="btn btn-primary btn-small" id="btnEmisor1"
													type="button" onclick="javascript:enviarADC();">Mic
													ADC Test - Envio</button>
												&nbsp;
												<button class="btn btn-primary btn-small" id="btnEmisor2"
													type="button" onclick="javascript:enviarReintentos();">Mic
													Test - Reintentos</button>
												&nbsp;
											</div>
										</div>
									</div>
								</div>
							</c:if>
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

	<script>
		function enviar(queue) {
			document.getElementById("primeraParteFormulario").action = "${pageContext.request.contextPath}/mantenimientoEnviarMensajeMic?queue="
					+ queue;
			document.getElementById("primeraParteFormulario").submit();
		}

		function enviarADC() {
			document.getElementById("primeraParteFormulario").action = "${pageContext.request.contextPath}/mantenimientoTestADC";
			document.getElementById("primeraParteFormulario").submit();
		}

		function enviarReintentos() {
			document.getElementById("primeraParteFormulario").action = "${pageContext.request.contextPath}/mantenimientoTestReintentos";
			document.getElementById("primeraParteFormulario").submit();
		}

		$(document).ready(function() {
			$('#btnEmisor').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			$('#btnEmisor1').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			$('#btnEmisor2').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			$('#btnReceptor').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			$('#testMicMensajeSimple').click(function() {
				$('#bloqueEspera').trigger('click');
			});

		});
	</script>
</body>
</html>