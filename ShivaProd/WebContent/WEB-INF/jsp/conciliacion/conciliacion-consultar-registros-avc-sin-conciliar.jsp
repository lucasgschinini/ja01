<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="conciliacion.titulo" /></title>

<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<meta name="Consultar Registros AVC sin Conciliar" content="Modelo">

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

							<div class="title">
								<spring:message code="conciliacion.consultar.titulo.pagina" />
							</div>

							<div class="pagos_anticipos">
								<p>
									<strong><spring:message code="conciliacion.criteriosBusqueda" /></strong>
								</p>
								<form:form id="formCriterioDeBusqueda" commandName="registroAVCDto">
									<form:hidden path="operation" id="operation" />
									<div class="row">
										<!-- ESTADO -->
										<div class="span6">
											<label class="control-label" for="idEstado"><spring:message code="recibo.estado" /></label>
											<div class="controls">
												<select id="idEstado" name="estadoFormateado" class="input" style="width:450px">
													<option value=""><spring:message code="combo.seleccionar" /></option>
													<c:forEach items="${estados}" var="est">
														<c:choose>
															<c:when test="${est.name() eq registroAVCDto.estadoFormateado}">
																<option value="${est.name()}" selected>${est.descripcion()}</option>
															</c:when>
															<c:otherwise>
																<option value="${est.name()}">${est.descripcion()}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="nroBoleta"><spring:message code="conciliacion.nro.boleta" /></label>
											<div class="controls">
												<input id="nroBoleta" name="nroBoletaFiltro" type="text" value="${registroAVCDto.nroBoletaFiltro}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="referenciaDelValor"><spring:message code="conciliacion.referenciaValor" /></label>
											<div class="controls">
												<input id="referenciaDelValor" name="referenciaDelValorFiltro" type="text" value="${registroAVCDto.referenciaDelValorFiltro}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="nroBoletaFiltro" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="referenciaDelValorFiltro" cssClass="error" />
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="importeDesde"><spring:message code="valor.importeDesde" /></label>
											<div class="controls">
												<input id="importeDesde" name="importeDesde" type="text" maxlength="16" class="input" maxlength="16" required
													value="${registroAVCDto.importeDesde}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="importeHasta"><spring:message code="valor.importeHasta" /></label>
											<div class="controls">
												<input id="importeHasta" name="importeHasta" type="text" maxlength="16" class="input" maxlength="16" required
													value="${registroAVCDto.importeHasta}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="importeDesde" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="importeHasta" cssClass="error" />
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaAltaDesdeFormateada"><spring:message code="conciliacion.fechaAltaDesdeFormateada" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaAltaDesdeFormateada" data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaAltaDesdeFormateada" required value="${registroAVCDto.fechaAltaDesdeFormateada}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaAltaHastaFormateada"><spring:message code="conciliacion.fechaAltaHastaFormateada" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaAltaHastaFormateada" data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaAltaHastaFormateada" required value="${registroAVCDto.fechaAltaHastaFormateada}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="fechaAltaDesdeFormateada" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="fechaAltaHastaFormateada" cssClass="error" />
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="fechaDerivacionDesdeFormateada"><spring:message
													code="conciliacion.fechaDerivacionDesdeFormateada" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaDerivacionDesdeFormateada" data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaDerivacionDesdeFormateada" required value="${registroAVCDto.fechaDerivacionDesdeFormateada}">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaDerivacionHastaFormateada"><spring:message
													code="conciliacion.fechaDerivacionHastaFormateada" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaDerivacionHastaFormateada" data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaDerivacionHastaFormateada" required value="${registroAVCDto.fechaDerivacionHastaFormateada}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="fechaDerivacionDesdeFormateada" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="fechaDerivacionHastaFormateada" cssClass="error" />
										</div>
									</div>
									<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnBuscar" name="btnBuscar" title=<spring:message code="conciliacion.buscar"/> type="button">
												<i class="icon-white icon-search"></i>
												<spring:message code="valor.botonBuscar" />
											</button>
										</div>
									</div>
								</form:form>
								<div style="margin-top: 103px">
									<p>
										<strong><spring:message code="conciliacion.registrosAvcSinConciliar" /></strong>
									</p>
								</div>
								<form:form id="formBusqueda" commandName="valorDto">
									<form:hidden path="idRegistroAvcSelected" id="idRegistroAvcSelected" />
									<form:hidden path="valorPorReversion" id="valorPorReversion" />
									<div class="row">
										<div class="span9">
											<table id="tablaBusquedaRegistrosSinConciliar" class="tablaResultado">
												<thead>
													<tr>
														<th></th>
														<th><spring:message code="conciliacion.nroCliente" /></th>
														<th><spring:message code="conciliacion.razon.social" /></th>
														<th class="date"><spring:message code="conciliacion.fecha.alta" /></th>
														<th class="date"><spring:message code="conciliacion.fecha.deposito" /></th>
														<th><spring:message code="conciliacion.importe" /></th>
														<th><spring:message code="conciliacion.estado" /></th>
														<th><spring:message code="conciliacion.analista.team.comercial" /></th>
														<th><spring:message code="conciliacion.tipoRegistro" /></th>
														<th><spring:message code="conciliacion.referenciaValor" /></th>
														<th><spring:message code="conciliacion.nroBoleta" /></th>
														<th><spring:message code="conciliacion.cuit" /></th>
														<th><spring:message code="conciliacion.codigoOrganismo" /></th>
														<th><spring:message code="conciliacion.banco.origen" /></th>
														<th class="date"><spring:message code="conciliacion.fechaVencimiento" /></th>
														<th><spring:message code="conciliacion.errorAltaInterdeposito" /></th>
														<th class="dateTimeSeconds"><spring:message code="conciliacion.fechaError" /></th>
														<th><spring:message code="conciliacion.observaciones" /></th>
														<th><spring:message code="conciliacion.nroAcuerdo" /></th>
														<th><spring:message code="conciliacion.nombreArchivo" /></th>
														<th class="date"><spring:message code="conciliacion.fecha.derivacion" /></th>
														<th><spring:message code="conciliacion.analista.derivacion" /></th>
														<th><spring:message code="conciliacion.valorNoShiva" /></th>
													</tr>
												</thead>

												<tfoot></tfoot>

												<tbody>
													<c:forEach items="${listaRegistrosDto}" var="reg">
														<tr>
															<td><c:if
																	test="${((!reg.esEstadoPendConfirmar) 
																		&& (!sessionScope.loginUser.esSupervisor)
																		&& (!sessionScope.loginUser.esPerfilConsulta))}">
																	<button type="button" id="btnCrearBoleta" class="btn btn-xs btn-link"
																		title=<spring:message code="conciliacion.botonDetalleCrearValor"/>
																		onclick="javascript:editar(${reg.idRegistro}, '${reg.esValorReversion}');">
																		<i class="icon-edit bigger-120"></i>
																	</button>
																</c:if></td>
															<td>${reg.codigoClienteFormateado}</td>
															<td>${reg.razonSocial}</td>
															<td>${reg.fechaAltaFormateadaSoloDia}</td>
															<td>${reg.fechaDepositoFormateada}</td>
															<td>${reg.importeFormateado}</td>
															<td>${reg.estadoFormateado}</td>
															<td style="text-align: left"><c:choose>
																	<c:when test="${reg.usuarioTeamComercial eq '-'}">
																		<p style="text-align: center">-</p>
																	</c:when>
																	<c:otherwise>
																		<div style="width:205px;">
																			<img alt="Usuario" src="${reg.urlFotoUsuario(reg.usuarioTeamComercial)}"
																				style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;"
																				onerror='src="${pageContext.request.contextPath}/img/default2.png"'> ${reg.nombreUsuarioTeamComercial} <br> <a
																				href="sip:${reg.mailUsuarioTeamComercial}" title="Chat"><i class="icon-comment" style="margin-top: 3px"></i></a> <a
																				href="mailto:${reg.mailUsuarioTeamComercial}" title="Email"><i class="icon-envelope" style="margin-left: 3px; margin-top: 2px"></i></a>
																		</div>
																	</c:otherwise>
																</c:choose></td>
															<td>${reg.tipoValorFormateado}</td>
															<td>${reg.referenciaValorFormateado}</td>
															<td>${reg.nroBoletaFormateado}</td>
															<td>${reg.cuitFormateado}</td>
															<td>${reg.codigoOrganismo}</td>
															<td>${reg.bancoOrigenFormateado}</td>
															<td>${reg.fechaVencimientoFormateado}</td>
															<td>${reg.errorAltaInterdeposito}</td>
															<td>${reg.fechaErrorFormateado}</td>
															<td>${reg.observacionesFormateado}</td>
															<td>${reg.acuerdo}</td>
															<td>${reg.nombreArchivo}</td>
															<td>${reg.fechaDerivacionFormateada}</td>
															<td style="text-align: left"><c:choose>
																	<c:when test="${reg.usuarioAlta eq '-'}">
																		<p style="text-align: center">-</p>
																	</c:when>
																	<c:otherwise>
																		<div style="width:205px;">
																			<img alt="Usuario" src="${reg.urlFotoUsuario(reg.usuarioAlta)}"
																				style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;"
																				onerror='src="${pageContext.request.contextPath}/img/default2.png"'> ${reg.nombreUsuarioAlta} <br> <a
																				href="sip:${reg.mailUsuarioAlta}" title="Chat"><i class="icon-comment" style="margin-top: 3px"></i></a> <a
																				href="mailto:${reg.mailUsuarioAlta}" title="Email"><i class="icon-envelope" style="margin-left: 3px; margin-top: 2px"></i></a>
																		</div>
																	</c:otherwise>
																</c:choose></td>
															<td>${reg.esValorReversion}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row">
										<div class="span4">
											<label for="mostrarValorNoShiva"><input id="mostrarValorNoShiva" type="checkbox">&nbsp;<spring:message
													code="conciliacion.mostrarValoresNoShiva" /></label>
										</div>
									</div>
								</form:form>
							</div>

						</div>
					</div>
				</div>
				<!-- end #content -->
			</div>
			<!-- end .wrap -->

		</div>
		<!-- end #main -->

	</div>
	<!-- end #container -->
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	<script>
	
		$(function () {
			$.datepicker.setDefaults($.datepicker.regional["es"]);
			$("#fechaAltaDesdeFormateada").datepicker({firstDay: 1});
			$("#fechaAltaHastaFormateada").datepicker({firstDay: 1});
			$("#fechaDerivacionDesdeFormateada").datepicker({firstDay: 1});
			$("#fechaDerivacionHastaFormateada").datepicker({firstDay: 1});
		});
		
		$('#btnBuscar').click(function() {
		    
			$('#bloqueEspera').trigger('click');
			$('#operation').val("<%=Constantes.BUSCAR_REGISTRO_AVC%>");
			
			$('#formCriterioDeBusqueda')[0].action = "${pageContext.request.contextPath}/conciliacion-buscar";
			$('#formCriterioDeBusqueda')[0].submit();
		});
	
		function editar(idRegistro, valorPorReversion) {
			$('#bloqueEspera').trigger('click');
			if (valorPorReversion === 'SI'){
				$('#valorPorReversion').val(idRegistro);
			} else{
				$("#idRegistroAvcSelected").val(idRegistro);
			};
			$('#formBusqueda')[0].action="${pageContext.request.contextPath}/conciliacion-consultar-registros-avc-sin-conciliar-detalle";
			$('#formBusqueda')[0].submit();
		};
		
		var idTabla = 'tablaBusquedaRegistrosSinConciliar';
		
		window.onload = function() {
			inicializarTablaConExportacionAPdfyXls(idTabla);
			agregarOrdenFecha(idTabla);
			$('#' + idTabla).dataTable().fnSort([[3,'asc']]);
		};
		
		$("#mostrarValorNoShiva").click(function(){
			if ($("#mostrarValorNoShiva").is(':checked')){
				oTable.fnFilter( 'SI', 22 );
			} else {
				oTable.fnFilter("", 22 );
			}
		});
		
		 Date.prototype.yyyymmdd = function() {         
		         
		        var yyyy = this.getFullYear().toString();                                    
		        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		        var dd  = this.getDate().toString();             
		        return (dd[1]?dd:"0"+dd[0]) + '/' + (mm[1]?mm:"0"+mm[0]) + '/' + yyyy;
		   };	 
	</script>
</body>
</html>
