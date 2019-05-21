<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
  
<title><spring:message code="legajo.cheques.rechazados.titulo.main"/></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
<!--
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.11/features/scrollResize/dataTables.scrollResize.js"></script>

<script src="//cdn.jsdelivr.net/jquery.scrollto/2.1.2/jquery.scrollTo.min.js"></script>
-->
<%-- <link href="${pageContext.request.contextPath}/css/jquery.steps.css" rel="stylesheet"> --%>
<%-- <link href="${pageContext.request.contextPath}/css/legajo-cheque-rechazado.css" rel="stylesheet"> --%>

<%-- <script src="${pageContext.request.contextPath}/js/jquery.steps.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.steps.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.jeditable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/comparador-currency.js"></script>

<script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-detalle.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/legajo-cheques-rechazados/legajo-cheque-rechazado-cobro-relacionado.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.funciones.cobros.js"></script>
<script src="${pageContext.request.contextPath}/js/custom.comboBox.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-validacion-formato.js"></script>
<script src="${pageContext.request.contextPath}/js/custom-paginacion-datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/funciones-datatables-shiva.js"></script>

<script type="text/javascript">
	var	legajoChequeRechazado = ${legajoChequeRechazadoDto};		

</script>
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
								<div class="title"><spring:message code="legajo.cheques.rechazados.resumen.titulo.main"/></div>
								<div class="pagos_anticipos">
									<p style="padding: 0 0 15px 0;"><strong><spring:message code="legajo.cheques.rechazados.solapa.resumenlegajo"/></strong></p>
									<div class="row">
										<div class="span3" align="left">
											<label class="control-label" for="prevAnalista"><spring:message code="legajo.cheques.rechazados.legajo.idLegajo"/></label>
											<div class="controls">
												<input id="idLegajo" name="idLegajo" type="text" class="input" readonly value="<c:out value = "${idLegajo}"/>"/>
											</div>
										</div>
										<div class="span6" align="right" style="margin-top: 16px">
											<button type="button" align="right" class="btn btn-primary btn-small" id="btnExportar" onclick="javascript:exportarDetalle();">
												<i class="icon-white icon-download-alt"></i>Exportar Legajo a Excel
											</button>
										</div>
									</div>
									
									<div class="row">
										<div class="span6" align="left">
											<label class="control-label" for="prevAnalista"><spring:message code="legajo.cheques.rechazados.legajo.estadoLegajo"/></label>
											<div class="controls">
												<input id="estadoLegajo" name="estadoLegajo" type="text" class="input span6" readonly value="<c:out value = "${estado}"/>"/>
											</div>
										</div>
									</div>									
									<div class="row">
										<div class="span3" align="left">
											<label class="control-label" for="empresa"><spring:message code="legajo.cheques.rechazados.legajo.empresa" /></label>
											<div class="controls">
												<input id="inputEmpresa" name="inputEmpresa" type="text" class="input" readonly value="<c:out value = "${empresa}"/>"/>
											</div>
										</div>
										<div class="span3" align="left">
											<label class="control-label" for="segmento"><spring:message code="legajo.cheques.rechazados.legajo.segmento" /></label>
											<div class="controls">
												<input id="inputSegmento" name="inputSegmento" type="text" class="input" readonly value="<c:out value = "${segmento}"/>"/>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="analista"><spring:message code="legajo.cheques.rechazados.legajo.analista" /></label>
											<div class="controls">
												<input id="analista" name="analista" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuario}"/>"
													style="margin: 0 auto;" />
											</div>
										</div>

										<div class="span3">
											<label class="control-label" for="copropietario"><spring:message code="legajo.cheques.rechazados.legajo.copropietario" /></label>
											<div class="controls">
												<input id="copropietario" name="copropietario" type="text" class="input" readonly value="<c:out value = "${nombreCompletoUsuarioCopropietario}"/>"
													style="margin: 0 auto;" />
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="ubicacion"><spring:message code="legajo.cheques.rechazados.legajo.ubicacion.cheque" /></label>
											<div class="controls">
												<input id="ubicacion" name="ubicacion" type="text" class="input" readonly value="<c:out value = "${ubicacion}"/>"/>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaRechazo"><spring:message code="legajo.cheques.rechazados.legajo.fecha.rechazo" /></label>
											<div class="controls">
												<input id="fechaRechazo" name="fechaRechazo" type="text" class="input" readonly value="<c:out value = "${fechaRechazo}"/>"/>
											</div>
										</div>

										<div class="span3">
											<label class="control-label" for="motivoRechazo"></span><spring:message code="legajo.cheques.rechazados.legajo.motivo.rechazo" /></label>
											<div class="controls">
												<input id="motivoRechazo" name="motivoRechazo" type="text" class="input" readonly value="<c:out value = "${motivoRechazo}"/>"/>
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaNotificacionRechazo"></span><spring:message code="legajo.cheques.rechazados.legajo.fecha.notificacion.rechazo" /></label>
											<div class="controls">
												<input id="fechaNotificacionRechazo" name="fechaNotificacionRechazo" type="text" class="input" readonly value="<c:out value = "${fechaNotificacionRechazo}"/>"/>
											</div>
										</div>
									</div>
									
									<div class="row" style="margin-top: 30px" id="prevObservacionesAnteriores">
										<div class="span9">
											<label class="control-label" for="observacionesAnterioresResumen"><spring:message code="legajo.cheques.rechazados.legajo.observaciones.anteriores"/></label>
											<textarea class="field span9" id="observacionesAnterioresResumen" name="observacionesAnterioresResumen" readonly ><c:out value = "${observacionesAnterioresResumen}"/></textarea>
										</div>
									</div>
									
									<div class="row" style="margin-top: 30px" id="prevObservaciones">
										<div class="span9">
											<label class="control-label" for="prevObservText"><spring:message code="legajo.cheques.rechazados.legajo.observaciones"/></label>
											<textarea class="field span9" id="prevObservText" name="prevObservText" readonly><c:out value = "${prevObservText}"/></textarea>
										</div>
									</div>
									
									<div id="cheques-rechazados-datos-resumen">
										<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.solapa.cheque" /></strong></p>
										<div id= "divChequeSeleccionado">
											<div class="row">
												<div class="span9">
													<table id="tablaChequeSeleccionadoResumen" class="tablaResultado">
														<thead>
															<tr>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.sistemaOrigen" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.numeroCheque" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.descripcionBancoOrigenCheque" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.codigoBancoOrigenCheque" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.clienteLegado"  /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.tipoCheque" /></th>
																<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaVencimientoCheque" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.monedaCheque" /></th>
																<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.importeCheque" /></th>
																<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaDeposito" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.bancoDeposito" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.cuentaDeposito" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.acuerdo" /></th>
																<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.fechaRecepcionCheque" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.estado" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.empresa" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.segmento" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaDueño" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.copropietario" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.analistaTeamComercial" /></th>
																<th><spring:message code="legajo.cheques.rechazados.legajo.tablaResultado.idInternoValor" /></th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<br>
										<br>
										<div class="row">
											<div class="span9" id="idChequesEdicionManual">
												<div class="row">
													<div class="span3">
														<label class="control-label" for="sistemaOrigenResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.sistema.origen" /></label>
														<div class="controls">
															<input id="sistemaOrigenResumen" name="sistemaOrigenResumen" type="text" class="input"  readonly
																value="<c:out value = "${sistemaOrigen}"/>"/>
														</div>
													</div>
			
													<div class="span3">
														<label class="control-label" for="tipoChequeResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.tipo.cheque" /></label>
														<div class="controls">
															<input id="tipoChequeResumen" name="tipoChequeResumen" type="text" class="input"  readonly
																value="<c:out value = "${edTipoCheque}"/>"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="codigoBancoOrigenResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.codigo.banco.origen" /></label>
														<div class="controls">
															<input id="codigoBancoOrigenResumen" name="codigoBancoOrigenResumen" type="text" class="input"  readonly
																value="<c:out value = "${comboBancoOrigen}"/>"/>
														</div>
													</div>
													
													<div class="span3">
														<label class="control-label" for="descripcionBancoOrigenResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.descripcion.banco.origen" /></label>
														<div class="controls">
															<input id="descripcionBancoOrigenResumen" name="descripcionBancoOrigenResumen" type="text" class="input"  readonly
																value="<c:out value = "${bancoOrigenesDescripcion}"/>"/>
														</div>
													</div>
													
													<div class="span3">
														<label class="control-label" for="nroChequeResumen"><spring:message code="legajo.cheques.rechazados.cheque.filtro.nro.cheque" /></label>
														<div class="controls">
															<input id="nroChequeResumen" name="nroChequeResumen" type="text" class="input"  readonly
																value="<c:out value = "${nroCheque}"/>"/>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="span3">
														<label class="control-label" for="fechaEmisionResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.emision" /></label>
														<div class="controls">
															<input id="fechaDepositoResumen" name="fechaDepositoResumen" type="text" class="input" readonly
																value="<c:out value = "${fechaEmisionResumen}"/>"/>
														</div>
													</div>
													<div class="span3">
														<label class="control-label" for="fechaDepositoResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.deposito" /></label>
														<div class="controls">
															<input id="fechaDepositoResumen" name="fechaDepositoResumen" type="text" class="input" readonly
																value="<c:out value = "${fechaDepositoResumen}"/>"/>
														</div>
													</div>
													<div class="span3" id="verFechaVencimiento" style="display:none;">
														<label class="control-label" for="fechaVencimientoResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.fecha.vencimiento" /></label>
														<div class="controls">
															<input id="fechaVencimientoResumen" name="fechaVencimientoResumen" type="text" class="input" readonly
															value="<c:out value = "${fechaVencimientoResumen}"/>"/>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="span3">
														<label class="control-label" for="monedaChequeResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.moneda" /></label>
														<div class="controls">
															<input id="monedaChequeResumen" name="monedaChequeResumen" type="text" class="input" readonly
																value="<c:out value = "${moneda}"/>"/>
														</div>
													</div>	
			
													<div class="span3">
														<label class="control-label" for="importeChequeResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.importe" /></label>
														<div class="controls">
															<input id="importeChequeResumen" name="importeChequeResumen" type="text" class="input" readonly
																value="<c:out value = "${importeChequeResumen}"/>"/>
														</div>
													</div>
												</div>	
												
												<div class="row">
													<div class="span3">
														<label class="control-label" for="acuerdoChequeResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.acuerdo" /></label>
														<div class="controls">
															<input id="acuerdoChequeResumen" name="acuerdoChequeResumen" type="text" class="input" readonly
																value="<c:out value = "${comboAcuerdoDeposito}"/>"/>
														</div>
													</div>
													
													<div class="span3">
														<label class="control-label" for="bancoDepositoResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.banco.deposito" /></label>
														<div class="controls">
															<input id="bancoDepositoResumen" name="bancoDepositoResumen" type="text" class="input" readonly
																value="<c:out value = "${comboBancoDeposito}"/>"/>
														</div>
													</div>
													
													<div class="span3">
														<label class="control-label" for="cuentaDepositoResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.cuenta.deposito" /></label>
														<div class="controls">
															<input id="cuentaDepositoResumen" name="cuentaDepositoResumen" type="text" class="input" readonly
																value="<c:out value = "${cuentaDepositoResumen}"/>"/>
														</div>
													</div>
												</div>
												
												<div class="row" id="idDivClienteSiebel">
													<div class="span3">
														<label class="control-label" for="inputCodClienteResumen"><spring:message code="valor.codCliente"/></label>
														<div class="controls">
															<input id="inputCodClienteResumen" name="inputCodClienteResumen" type="text" class="input" readonly
																value="<c:out value = "${inputCodClienteResumen}"/>"/>
														</div>
													</div>
													<div class="span6">
														<label class="control-label" for="razonSocialClienteLegadoResumen"><spring:message code="valor.razonSocial"/></label>
														<div class="controls">
															<input id="razonSocialClienteLegadoResumen" name="razonSocialClienteLegadoResumen" type="text" class="input span6" readonly
																value="<c:out value = "${razonSocialClienteLegadoResumen}"/>"/>
														</div>
													</div>
													
												</div>
												
												<div class="row">
													<div class="span3">
														<label class="control-label" for="montoPendienteReembolsarResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.pendiente.reenbolsar" /></label>
													</div>
													<div class="span3">
														<label class="control-label" for="montoAplicadoPendienteReembolsarResumen"><spring:message code="legajo.cheques.rechazados.legajo.edicion.manual.monto.aplicado.pendiente.revertir" /></label>
													</div>
													
												</div>
												<div class="row">
													<div class="span3">
														<input id="montoPendienteReembolsarInputResumen" name="montoPendienteReembolsarInputResumen" type="text" class="input" value="$<c:out value = "${montoAReenvolsar}"/>" readOnly/>
													</div>
													<div class="span3">
														<input id="montoAplicadoPendienteReembolsarInputResumen" name="montoAplicadoPendienteReembolsarInputResumen" type="text" class="input" value="$<c:out value = "${montoARevertir}"/>" readOnly/>
													</div>
													
												</div>
												
											</div>
											</div>
											<div id="cobro-relacionado-cheque">
												<p style="padding: 25px 0px 5px 0px;">
													<strong>
														<spring:message code="legajo.cheques.rechazados.solapa.cobrosrelacionados" />
													</strong>
												</p>
												
												<div class="row">
													<div class="span9">
														<table id="tablaCobrosRelacionados" class="tablaResultado">
															<thead>
																<tr>
																	<th></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.sistemaImputacion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.idOperacion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.tipoDocumento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroLegalDocumento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nroDocumento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.convenioFinanciacion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.cuotaFinanciacion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.clienteLegado" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeDocumento" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeCheque" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeEfectivo" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeRetenciones" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeTotalBonos" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.importeOtrasMonedas" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaImputacionSistemaOrigen" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.idReversion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.analista" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.copropietario" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.analistaTeamComercial" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.estadoCobro" /></th>
																	<th class="date-time"><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.fechaReversion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.cobroRelacionado.nombreArchivoConReversion" /></th>
																	<th></th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
												<div class="row rowInformacion">
													<div class="span9">
														<span id="informacionCobrosRelacionados" class="informacion"></span>
													</div>
												</div>
												<div class="row rowError">
													<div class="span9">
														<span id="errorCobrosRelacionados" class="error"></span>
													</div>
												</div>
												
											</div>
											<div id="reembolso">
												<p  style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.solapa.reembolso" /></strong></p>
												<div class="row">
													<div class="span9">
														<table id="tablaDocumentosRelacionados" class="tablaResultado">
															<thead>
																<tr>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.sistema" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.tipoComprobante" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroLegalDocumento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.nroDocumento" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.clienteLegado" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.legajo.reembolso.moneda" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeTotalDocumento" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoDocumento" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.importeAplicado" /></th>
																	<th class="importe"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.saldoActualDocumento" /></th>
																	<th class="date-time"><spring:message code="legajo.cheques.rechazados.legajo.reembolso.ultimaFechaConsulta" /></th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
											<div id="notificaciones">
												<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.solapa.notificaciones" /></strong></p>
												<div class="row">
													<div class="span9">
														<table id="tablaNotificacionesDetalle" class="tablaResultado">
															<thead>
																<tr>
																	<th></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoNotificacion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosReceptor" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaContacto" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.tipoContacto" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.datosContacto" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.acuseRecibo" /></th>
																	<th class="date"><spring:message code="legajo.cheques.rechazados.solapa.notificacion.fechaRecepcion" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.observaciones" /></th>
																	<th><spring:message code="legajo.cheques.rechazados.solapa.notificacion.usuarioCargoNotificacion" /></th>
																	<th></th>
																	<th></th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
											<div id="comprobantes-adjuntos">
												<p style="padding: 25px 0px 5px 0px;"><strong><spring:message code="legajo.cheques.rechazados.solapa.comprobantes" /></strong></p>
												<div class="row">
													<div class="span9">
														<table id="comprobantes" class="tablaResultado" width="100%">
															<thead>
																<tr>
																	<th></th>
																	<th>Nombre</th>
																	<th>Descripcion</th>
																	<th></th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="span9" style="margin-top: 16px" align="right">
													<div>
														<button type="button" class="btn btn-primary btn-small" id="btnVolver" onclick="javascript:volverBusqueda();">
															<i class="icon-white icon-repeat"></i>&nbsp;&nbsp;&nbsp;Volver&nbsp;&nbsp;&nbsp;
														</button>
													</div>
												</div>
											</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
	
	<form:form id="formResumen" commandName="legajoDto">
		<input type="hidden" id="idLeg" name="idLeg" value="${idLeg}"/>
		<input type="hidden" id="volver" name="volver"	value="/legajo-cheque-rechazado-detalle" />
		<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}" />
		<input type="hidden" id="goBack" name="goBack" />
		<input type="hidden" id="tipoCheque" name="tipoCheque" value="${tipoCheque}" />
		<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" />
	</form:form>
	
	<form:form id="formNotificacion" commandName="notificacion">
		<input type="hidden" id="idNotificacion" name="idNotificacion" />
	</form:form>
	<form:form id="formLegajo" commandName="legajoChequeRechazadoDto">
		<input type="hidden" id="volver" name="volver" value="/legajo-cheque-rechazado-detalle"/>
		<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}"/>
		<input type="hidden" id="goBack" name="goBack" />
		<input type="hidden" id="estado" name="estado" />
		<input type="hidden" id="idOperacionRelacionada" name="idOperacionRelacionada" />
		<input type="hidden" id="formOrigen" name="formOrigen"/>
		<input type="hidden" id="idLeg" name="idLeg" value="${idLeg}"/>						
										
	</form:form>
</body>
</html>

