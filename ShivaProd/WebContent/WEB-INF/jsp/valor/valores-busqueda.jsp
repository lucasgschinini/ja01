<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado" %>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="valor.busqueda.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
	
	
<!--[if lte IE]>
<style>
.custom-combobox-toggle {
	position: absolute;
	top: 0;
	bottom: 10px;
	margin-left: -30px;
	padding: 0;
	/* support: IE7 */
	*height: 1.7em;
	*top: 0.1em;
}
</style>
<![endif]-->
<style>
   .custom-combobox { 
     position: relative; 
     display: inline-block; 
   } 
   
 
  @media screen and (-webkit-min-device-pixel-ratio:0) {
	.custom-combobox-toggle { 
		position: absolute; 
		top: 0; 
		bottom: 0px; 
		margin-left: -19px; 
		padding: 0; 
		/* support: IE7 */ 
		*height: 1.7em; 
		*top: 0.1em; 
	}
  }
   .custom-combobox-input { 
     margin: 0; 
     padding: 0.3em; 
   } 
   	/* button text element */ 
	.ui-button .ui-button-text { 
		display: block; 
/* 		line-height: normal;  */
		  color: #ffffff; 
/* 	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);  */
	  background-color: #E6E6E6; 
	  *background-color: #0044cc; 
	  background-image: -moz-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0044cc)); 
	  background-image: -webkit-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: -o-linear-gradient(top, #0088cc, #0044cc); 
	  background-image: linear-gradient(to bottom, #0088cc, #0044cc); 
	  background-repeat: repeat-x; 
	  border-color: #0044cc #0044cc #002a80; 
	  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); 
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0); 
	  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false); 
	} 
 	.ui-button:hover { 
 	  color: #ffffff; 
 	  background-color: #A9DBF6; 
 	  *background-color: #003bb3; 
 	} 
 	.ui-state-hover { 
	 	border: 1px solid #999999; 
	 	background: #dadada url(../img/jquery/ui-bg_glass_75_dadada_1x400.png) 50% 50% repeat-x; 
	 	font-weight: normal; 
	 	color: #212121; 
 	} 
</style>	
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
						<div class="title"><spring:message code="valor.busqueda.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="valor.criteriosBusqueda"/></strong></p>
							<form:form id="formBusqueda" commandName="boletaConValorFiltro" action="${pageContext.request.contextPath}/valores-busqueda">
								<form:hidden path="valoresSelected" id="valoresSelected"/>
									<!-- EMPRESA -->
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa"><span class="rojo">* </span><spring:message code="valor.empresa"/></label>
											<div class="controls">
											<select id ="selectEmpresa" name="empresa" class="input">		
											<c:if test="${comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>									
													<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq boletaConValorFiltro.empresa}">
															<option value="${emp.idEmpresa}" selected>${emp.descripcion}</option>
														</c:when>
														<c:otherwise>
															<option value="${emp.idEmpresa}">${emp.descripcion}</option>
														</c:otherwise>
													</c:choose>
													</c:forEach> 
											</select>
											</div>
										</div>								
										<!-- SEGMENTO -->									
										<div class="span3">
											<label class="control-label" for="selectSegmento"><span class="rojo">* </span><spring:message code="valor.segmento"/></label>
											<div class="controls">
												<select id ="selectSegmento" name="segmento" class="input">
													<c:if test="${comboSegmento}">
														<option value="" selected>
															<spring:message code="combo.seleccionar"/>
														</option>
													</c:if>	
													<c:forEach items="${segmentos}" var="seg">
														<c:choose>
															<c:when test="${seg.idSegmento eq boletaConValorFiltro.segmento}">
																<option value="${seg.idSegmento}" selected>${seg.descripcion}</option>
															</c:when>
															<c:otherwise>
																<option value="${seg.idSegmento}">${seg.descripcion}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach> 
												</select>
											</div>
										</div>
										<!-- ESTADO -->									
										<div class="span3">
											<label class="control-label" for="selectEstado"><spring:message code="valor.estado"/></label>
											<div class="controls">
												<select id ="selectEstado" name="selectEstado" class="input">
													<option value=""><spring:message code="combo.seleccionar"/></option>
													<c:forEach items="${estados}" var="seg" >
														<c:choose>																										
															<c:when test="${seg.iDestado eq boletaConValorFiltro.selectEstado}">
																<option value="${seg.iDestado}" selected>${seg.selectEstado}</option>
															</c:when>
															<c:otherwise>
																<option value="${seg.iDestado}" >${seg.selectEstado}</option>
															</c:otherwise>	
														</c:choose>											
													</c:forEach>												
												</select>
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="empresa" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="segmento" cssClass="error" />
										</div>
									</div>
									<!-- CLIENTE SIEBEL -->	
									<p style="padding: 5px 0px;">
										<strong><spring:message
												code="cobro.busqueda.filtrosCliente" /></strong>
									</p>								
											<div class="row" >			
												<div class="span3">
													<label class="control-label" for="selectCriterio">Datos Cliente</label>
													<div class="controls">
														<select id="selectCriterio" name="selectCriterio" class="input">
															<option value=""><spring:message code="combo.seleccionar"/></option>
															<c:forEach items="${criterioBusquedaCliente}" var="criterioBusquedaCliente">
															<c:choose>
																<c:when test="${criterioBusquedaCliente.nombre eq busquedaFiltro.selectCriterio}">
																	<option value="${criterioBusquedaCliente.nombre}" selected>${criterioBusquedaCliente.descripcion}</option>
																</c:when>
																<c:otherwise>
																	<option value="${criterioBusquedaCliente.nombre}">${criterioBusquedaCliente.descripcion}</option>
																</c:otherwise>
															</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="span3">
												<label class="control-label" for="textCriterio">&nbsp;</label>
													<div class="controls">
														<input id="textCriterio" name="textCriterio" type="text" maxlength="13"
															class="input" style="margin: 0 auto;" value="${busquedaFiltro.textCriterio}"/>
													</div>
												</div>
											</div>
											<!-- fin de row -->
									
											<!-- FILA 5 Error -->
											
											<div class="row rowError">
												<div class="span3">
													<span id="errorSelectCriterio" class="error">${errorSelectCriterio}</span>
												</div>
												<div class="span3">
													<span id="errorBusqueda" class="error">${errorBusqueda}</span>
												</div>
											</div>
											<!-- fin de rowError -->		
									<div class="row">
										<!-- NUMERO BOLETA-->
										<div class="span3">
											<label class="control-label" for="nroBoletaFiltro"><spring:message
													code="valor.nroBoleta" /></label>
											<div class="controls">
												<input id="nroBoletaFiltro" name="nroBoletaFiltro" type="text"
													value="${boletaConValorFiltro.nroBoletaFiltro}">
											</div>
										</div>
										<!-- NRO REFERENCIA VALOR-->
										<div class="span3">
											<label class="control-label" for="referenciaDelValorFiltro"><spring:message
													code="valor.referenciaValor" /></label>
											<div class="controls">
												<input id="referenciaDelValorFiltro" name="referenciaDelValorFiltro" type="text"
													value="${boletaConValorFiltro.referenciaDelValorFiltro}">
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
										<!-- ID VALOR-->
										<div class="span3">
											<label class="control-label" for="idValorFiltro"><spring:message
													code="valor.identificadorInternoValor" /></label>
											<div class="controls">
												<input id="idValorFiltro" name="idValorFiltro" type="text"
													value="${boletaConValorFiltro.idValorFiltro}">
											</div>
										</div>
										<!-- ID COBRO SHIVA-->
										<div class="span3">
											<label class="control-label" for="idCobroShivaFiltro"><spring:message
													code="valor.cobroFormateado" /></label>
											<div class="controls">
												<input id="idCobroShivaFiltro" name="idCobroShivaFiltro"
													type="text"
													value="${boletaConValorFiltro.idCobroShivaFiltro}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="idValorFiltro" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors path="idCobroShivaFiltro" cssClass="error" />
										</div>
									</div>								
									<div class="row">
										<!-- FECHA DESDE-->
										<div class="span3">
											<label class="control-label" for="fechaDesde"><spring:message
													code="valor.fechaAltaDesde" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaDesde"
													data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaDesde" required
													value="${boletaConValorFiltro.fechaDesde}">
											</div>
										</div>
										<!-- FECHA HASTA-->
										<div class="span3">
											<label class="control-label" for="fechaHasta"><spring:message
													code="valor.fechaAltaHasta" /></label>
											<div class="controls">
												<input class="span3" type="text" id="fechaHasta"
													data-date-format="dd/mm/yyyy" data-date-language='es'
													name="fechaHasta" required
													value="${boletaConValorFiltro.fechaHasta}">
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div id="mensajeErrorFecha" class="span6" style="display:none">
											<span  class="error">
												<spring:message code="error.fechaMenorIgualA30Diass" />
											</span>											
										</div>
										<div class="span3">
											<form:errors id="fechaDesdeErrorFormato" path="fechaDesde" cssClass="error" />
										</div>
										<div class="span3">
											<form:errors id="fechaHastaErrorFormato" path="fechaHasta" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<!-- IMPORTE DESDE-->
										<div class="span3">
											<label class="control-label" for="importeDesde"><spring:message
													code="valor.importeDesde" /></label>
											<div class="controls">
												<input id="importeDesde" name="importeDesde" type="text"
													maxlength="16"
													class="input" maxlength="16" required
													value="${boletaConValorFiltro.importeDesde}">
											</div>
										</div>
										<!-- IMPORTE HASTA-->
										<div class="span3">
											<label class="control-label" for="importeHasta"><spring:message
													code="valor.importeHasta" /></label>
											<div class="controls">
												<input id="importeHasta" name="importeHasta" type="text"
													maxlength="16"
													class="input" maxlength="16" required
													value="${boletaConValorFiltro.importeHasta}">
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
										<!-- ANALISTA -->
										<div class="span3">
											<label class="control-label" for="analistaFiltro">
												<spring:message code="valor.analista" />
											</label>
											<div class="controls">
												<select id ="analistaFiltro" name="analistaFiltro" class="input">
													<c:if test="${comboAnalistaFiltro}">
														<option value="" selected>
															<spring:message code="combo.seleccionar"/>
														</option>
													</c:if>	
													<c:forEach items="${listaAnalistaFiltro}" var="analistaFiltroItem">
														<c:choose>
															<c:when test="${analistaFiltroItem.tuid eq boletaConValorFiltro.analistaFiltro}">
																<option value="${analistaFiltroItem.tuid}" selected>${analistaFiltroItem.tuid} - ${analistaFiltroItem.nombreCompleto}</option>
															</c:when>
															<c:otherwise>
																<option value="${analistaFiltroItem.tuid}">${analistaFiltroItem.tuid} - ${analistaFiltroItem.nombreCompleto}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach> 
												</select>
											</div>
										</div>
									</div>
									<div class="row rowError">
										<div class="span3">
											<form:errors path="analistaFiltro" cssClass="error" />
										</div>
									</div>
									<div class="row"
										style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
										<div align="right">
											<button class="btn btn-primary btn-small" id="btnBuscar"
												name="btnBuscar"
												title=<spring:message code="valor.botonBuscar"/>
												type="button">
												<i class="icon-white icon-search"></i>
												<spring:message code="valor.botonBuscar" />
											</button>
											<input type="hidden" id="esAnalista"
												value="<c:out value='${usuarioSessionAnalista}'></c:out>" />
											<input type="hidden" id="esAdministrador"
												value="<c:out value='${usuarioSessionAdministrador}'></c:out>" />
											<input type="hidden" id="esConsulta"
												value="<c:out value='${usuarioSessionConsulta}'></c:out>" />
											<input type="hidden" id="idUsuario"
												value="<c:out value='${usuarioSessionIdUsuario}'></c:out>" />
											<input type="hidden" id="esCajeroPagador"
												value="<c:out value='${usuarioSessionCajeroPagador}'></c:out>" />
										</div>
									</div>
								</form:form>
							</div>

							<div class="pagos_anticipos">
								<p>
									<strong><spring:message code="valor.resultadoBusqueda" /></strong>
								</p>
								<form:form id="segundaParteFormulario" commandName="valorDto">
									<input type="hidden" name="idValor" id="idValor" />
									<input type="hidden" name="pantallaDestino" id="pantallaDestino" />
									<input type="hidden" name="errorCliente" id="errorCliente" value="${errorCliente}"/>

									<div class="row">
										<div class="span9">
											<table id="resultadoBusqueda" class="tablaResultado">
												<thead>
													<tr>
														<th style="padding: 3px 10px; text-align: center;"><input 
															type="checkbox" id="seleccionarTodos" name="seleccionarTodos" ></th>
														<th>&nbsp;</th>
														<th><spring:message code="valor.empresa" /></th>
														<th><spring:message code="valor.empresasAsociadas" /></th>
														<th><spring:message code="valor.segmento" /></th>
														<th><spring:message code="valor.cuitClienteLegado" /></th>
														<th><spring:message code="valor.codCliente" /></th>
														<th><spring:message code="valor.razonSocialAgrupador" /></th>
														<th class="date"><spring:message code="valor.fechaValor" /></th>
														<th><spring:message code="valor.tipoValor" /></th>
														<th><spring:message code="valor.importe" /></th>
														<th><spring:message code="valor.saldoDisponible" /></th>
														<th><spring:message code="valor.estadoValor" /></th>
														<th><spring:message code="valor.analista" /></th>
														<th><spring:message code="valor.copropietario" /></th>
														<th><spring:message code="valor.usuarioAutorizacion" /></th>
														<th><spring:message code="valor.ejecutivo" /></th>
														<th><spring:message code="valor.asistente" /></th>
														<th><spring:message
																code="valor.analistaTeamComercial" /></th>
														<th><spring:message
																code="valor.supervisorTeamComercial" /></th>
														<th><spring:message
																code="valor.gerenteRegionalTeamComercial" /></th>
														<th><spring:message
																code="valor.identificadorInternoValor" /></th>
														<th style="width: 30 em;"><spring:message
																code="valor.origen" /></th>
														<th><spring:message code="valor.bancoDeposito" /></th>
														<th><spring:message code="valor.nroAcuerdo" /></th>
														<th><spring:message code="valor.nroBoleta" /></th>
														<th><spring:message code="valor.referenciaValor" /></th>
														<th><spring:message
																code="valor.descripcionBancoOrigen" /></th>
														<th><spring:message
																code="valor.descripcionTipoImpuesto" /></th>
														<th><spring:message code="valor.provincias" /></th>
														<th><spring:message code="valor.cuitRetencion" /></th>
														<th><spring:message code="valor.codOrganismo" /></th>
														<th><spring:message code="valor.nroRecibo" /></th>
														<th><spring:message
																code="valor.nroConstanciaRecepcion" /></th>
														<th><spring:message code="valor.operacionAsociada" /></th>
														<th><spring:message code="valor.facturaRelacionada" /></th>
														<th><spring:message
																code="valor.documentacionOriginalRecibida" /></th>
														<th><spring:message code="valor.motivo" /></th>
														<th><spring:message code="valor.nroDocumentoContable" /></th>
														<th><spring:message code="valor.motivoSuspencion" /></th>
														<th><spring:message code="valor.idLegajoChequeRechazado" /></th>
														<th class="date"><spring:message code="valor.fechaNotificacionRechazo" /></th>
														<th class="date"><spring:message code="valor.fechaRechazo" /></th>
														<th><spring:message
																code="valor.archivoValoresConciliar" /></th>
														<th class="dateTime"><spring:message
																code="valor.fechaAlta" /></th>
														<th class="dateTime"><spring:message
																code="valor.fechaNotiDispRetiro" /></th>
														<th class="date"><spring:message
																code="valor.fechaIngresoRecibo" /></th>
														<th class="date"><spring:message
																code="valor.fechaEmision" /></th>
														<th class="date"><spring:message
																code="valor.fechaEmisionCheque" /></th>
														<th class="date"><spring:message
																code="valor.fechaVencimiento" /></th>
														<th class="date"><spring:message
																code="valor.fechaTransferencia" /></th>
														<th class="date"><spring:message
																code="valor.fechaDeposito" /></th>
														<th class="date"><spring:message
																code="valor.fechaDisponible" /></th>
														<th class="date"><spring:message
																code="valor.fechaUltimoEstado" /></th>
														<th><spring:message code="valor.bdImpresa" /></th>
														<th><spring:message code="valor.bdEnviadaMail" /></th>
														<th><spring:message code="valor.observaciones" /></th>
														<th><spring:message code="valor.cobroFormateado" /></th>
														<th>&nbsp;</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									<br>
														
								</form:form>
								<div class="row">
									<div class="span9" align="right">
										<button class="btn btn-primary btn-small"
											id="btnCambiarEstado" name="btnCambiarEstado"
											onclick="javascript:cambiarEstado();">
											<i class="icon-white icon-ok"></i>
											<spring:message code="valor.botonCambiarEstado" />
										</button>
										<button class="btn btn-primary btn-small"
											id="btnImprimirConstRecep" name="btnImprimirConstRecep"
											onclick="javascript:impConst();">
											<i class="icon-white icon-print"></i>
											<spring:message code="valor.imprimirConstRecepcion" />
										</button>
										<button class="btn btn-primary btn-small" id="btnMailBD"
											name="btnMailBD" onclick="javascript:mailBD();">
											<i class="icon-white icon-envelope"></i>
											<spring:message code="valor.botonEnviarMailBolDep" />
										</button>
										<button class="btn btn-primary btn-small" id="btnImprimirBD"
											name="btnImprimirBD" onclick="javascript:impBD();">
											<i class="icon-white icon-print"></i>
											<spring:message code="valor.botonImprimirBolDep" />
										</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- end #content -->
			</div>
			<!-- end .wrap -->
			
		</div>
		<!-- end #main -->
		
		<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
		
			
	</div>
	<!-- end #container -->
	<!-- FIN WEB -->

	<script>
	
	$(document).ready(function(){
		
		var idTabla = "resultadoBusqueda";
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";
		var tableResultados;
		
		
		var fechaDesde = document.getElementById('fechaDesde').value;
		var fechaHasta = document.getElementById('fechaHasta').value;
		
		var codCliente = document.getElementById('textCriterio').value;
		var nroBoleta = document.getElementById('nroBoletaFiltro').value;
		var referenciaDelValor = document.getElementById('referenciaDelValorFiltro').value;
		var idValor = document.getElementById('idValorFiltro').value;
		var idCobroShiva = document.getElementById('idCobroShivaFiltro').value;
		var analistaFiltro = document.getElementById('analistaFiltro').value;
		
		d2 = new Date();
		var hoy2=d2.yyyymmdd();
		var fromDate = new Date();
		var toDate = new Date(fromDate.getTime() - (29 * 24 * 3600 * 1000));
		var mesAtras=toDate.yyyymmdd();
		
		var estadoValorDisponible= '<%=Estado.VAL_DISPONIBLE.descripcion()%>';
		var estadoValorUsado= '<%=Estado.VAL_USADO.descripcion()%>';
		var estadoValorSuspendido= '<%=Estado.VAL_SUSPENDIDO.descripcion()%>';
		var estadoValorAnulado= '<%=Estado.VAL_ANULADO.descripcion()%>';
		var estadoValorBoletaPendienteConciliacion= '<%=Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.descripcion()%>';
		var estadoAvisoDePagoRechazado= '<%=Estado.VAL_AVISO_PAGO_RECHAZADO.descripcion()%>';
		var estadoBoletaRechazado= '<%=Estado.VAL_BOLETA_RECHAZADA.descripcion()%>';
		 
		var idUsuario = document.getElementById('idUsuario').value;
		var estadoValor = document.getElementById('selectEstado').value;
		var esAnalista = document.getElementById('esAnalista').value;
		var esAdministrador = document.getElementById('esAdministrador').value;
		var esConsulta = document.getElementById('esConsulta').value;
		var esCajeroPagador = document.getElementById('esCajeroPagador').value;
		 
		var primero;
		var valorFalso;
		var evaluarPrimero=false;		
		var str;
		var codigos = '';
		
		var generarCuadroUsuario = function(nombre, urlFoto, mail) {
			if (nombre !== '-') {
				return '<div style="width: 150px"> ' +
							'<img src="'+ urlFoto + '" ' +
							'style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; ' + 
							'margin-left: 5px; float: left; margin-bottom: 3px;" ' +
							'onerror=\'src="${pageContext.request.contextPath}/img/default2.png" \'> ' + nombre +
						'</div>';
			} else {
				return '<div style="width: 150px; text-align: center;"> ' + nombre + ' <br> ' + '</div>';
			}
		}
		
		//@author u573005, sprint3
		//Se refactoriza la funcion para recorrer los
		//Check que esten tildados y contar en que estado estan
		//Luego se decide si habilitar el boton o dejarlo desactivado
		//Se agrega el parametro para que cuando se llama al seleccionar todos
		//se seleccionen los registros que estan en la pagina
		var habilitarBotones = function(){
			var str = '';
			seleccion = "." + claseRegistros + ":checked";
			var w=0;
		   	var imprimBoletaContador=0;
		   	var envioMailContador=0;
			var noMostrarConstancia = 0;
			var cantChecks = 0;
			var validador = new ValidadorEstado;
			
			$(seleccion, tableResultados.fnGetNodes()).each(function(i,elem){
				cantChecks= cantChecks + 1;
				var aData = tableResultados.fnGetData($(this).closest('tr')[0]);
				var valor = aData.idValor;
				var hddCodEstado = aData.estadoValor;
				var hddCodTipoValo = aData.idTipoValor;
		   		var idEstadoValor = aData.idEstadoValor;
		   		var idOrigen = aData.idOrigen;
		   			
		   		validador.agregar(idEstadoValor);
		   			
		   		//Pregunta si ese registro tiene origen cons. automatica	
		   		//Si no lo agrega al contador
		   		if(idOrigen != 4){
		   			noMostrarConstancia++; 
		   		}

				var coma=(w==0)?"":",";
	   			str+=coma+valor;
	   			
				w++;
					
				//para imprimir boleta y envio mail
				if (hddCodTipoValo != 2 && hddCodTipoValo != 3 && hddCodTipoValo != 4) {
					++imprimBoletaContador;				
				}
					
				//enivo mail
				if (hddCodEstado == estadoValorAnulado || hddCodEstado == estadoValorUsado ||  hddCodEstado == estadoValorSuspendido) {
					++envioMailContador;
				}
			
			});
			
	   		//Se cargan los id para luego enviar al cambiar el estado
			$("#valoresSelected").val(str);
			
		   	var estadoSeleccionado = validador.getEstadoSeleccionado();
		   		
		   	$('#btnCambiarEstado').attr('disabled', true);
		   	$('#btnMailBD').attr('disabled', true);
		   	$('#btnImprimirBD').attr('disabled', true);
		   	$('#btnImprimirConstRecep').attr('disabled', true);
		   	
		   	if (cantChecks > 0) {
		   		
		   		if (estadoSeleccionado != null) {
					if (estadoSeleccionado == '<%=Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name()%>'
						&& (esAnalista == 'true'
						||  esCajeroPagador == 'true')
						){
						$('#btnCambiarEstado').attr('disabled', false);						
					} else if (estadoSeleccionado == '<%=Estado.VAL_BOLETA_RECHAZADA.name()%>'
						&&  (esAnalista == 'true'
						||  esCajeroPagador == 'true')
						){
						$('#btnCambiarEstado').attr('disabled', false);		
					} else if (estadoSeleccionado == '<%=Estado.VAL_AVISO_PAGO_RECHAZADO.name()%>'
						&&  (esAnalista == 'true'
						||  esCajeroPagador == 'true')
						){
						$('#btnCambiarEstado').attr('disabled', false);		
					} else if ((estadoSeleccionado == '<%=Estado.VAL_DISPONIBLE.name()%>'
							|| estadoSeleccionado == '<%=Estado.VAL_USADO.name()%>'
							|| estadoSeleccionado == '<%=Estado.VAL_SUSPENDIDO.name()%>')
						&&  (esAdministrador == 'true')
						){
						$('#btnCambiarEstado').attr('disabled', false);		
					}
				}
				
				//envio mail
				if (envioMailContador == 0 
					&& imprimBoletaContador == 0
					&& esAdministrador == 'false'
					&& esConsulta == 'false') {
					$('#btnMailBD').attr('disabled', false);
				}
				
				//imprimir boleta
				if (imprimBoletaContador == 0 
					&& esAdministrador == 'false'
					&& esConsulta == 'false') {
					$('#btnImprimirBD').attr('disabled', false);		
				}
				
				//constancia
				if (noMostrarConstancia == 0
					&& esConsulta == 'false') {
					$('#btnImprimirConstRecep').attr('disabled', false);
				}	
		   		
		   	}
				
		};
		
		var resultadoBusquedaSetting = {
				dom : 'Bfrtip',
				"sScrollY": '500px',
			   	"sScrollX": "100%",
			    "sScrollXInner": "110%",
			    "bScrollCollapse": true,
			    "iDisplayLength" : 10,
			    
			    buttons: [{	
						extend:'pdfHtml5',
						Orientation:"landscape",
					   	text:"PDF",
						title: "Busqueda_de_Valores",
						"mColumns": "visible",
						className: 'pdfbtn'
					},{
						extend:'excelHtml5',
						text:'Excel',
						title: "Busqueda_de_Valores",
						"mColumns": "visible",
						className: 'excelbtn'
					}]
				,
			    "aoColumns" : [
			     	{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return '<input type="checkbox" class="opcion" id="opcion_'+ data.idValor + '" name="opcion" value="'+ data.idValor + '"/><input type="hidden" class="editarOcultar" value="'+ data.idValor + '"/>';
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
						"bSortable": false 
			        },
			        { 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"> ' +
											'<button type="button" id="btnModificar_'+ data.idValor + '" class="btn btn-xs btn-link" title="Editar" 		onclick="javascript:modificar('+ data.idValor + ');"> ' +
												'<i class="icon-edit bigger-120"></i> ' +
											'</button> ' +
											'<button type="button" id="btnHistorial" class="btn btn-xs btn-link" title="Historial de Modificaciones" onclick="javascript:historial('+ data.idValor + ');"> ' +
												'<i class="icon-list-alt bigger-120"></i> ' +
											'</button> ' +
										'</div>';
							}
				            return data;
				          },
				        "className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
				    { 	"mData" : "empresa" },
				    { 	"mData" : "empresasAsociadas" },
				    { 	"mData" : "segmento" },
				    { 	"mData" : "cuitCliente" },
				    { 	"mData" : "codCliente" },
				    { 	"mData" : "razonSocialClienteAgrupador" },
				    { 	"mData" : "fechaValorFormateado" },
				    { 	"mData" : "tipoValor" },
				    { 	"mData" : "importe" },
				    { 	"mData" : "saldoDisponible" },
			        { 	"mData" : "estadoValor" },
			        { 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.nombreAnalista, data.urlFotoAnalista, data.mailAnalista);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.copropietario, data.urlFotoCopropietario, data.mailCopropietario);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
			        { 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioAutorizacion, data.urlFotoUsuarioAutorizacion, data.mailUsuarioAutorizacion);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioEjecutivo, data.urlFotoUsuarioEjecutivo, data.mailUsuarioEjecutivo);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioAsistente, data.urlFotoUsuarioAsistente, data.mailUsuarioAsistente);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioAnalistaTC, data.urlFotoAnalistaTC, data.mailUsuarioAnalistaTC);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioSupervisorTC, data.urlFotoSupervisorTC, data.mailusuarioSupervisorTC);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return generarCuadroUsuario(data.usuarioGerenteRegionalTC, data.urlFotoGerenteRegionalTC, data.mailUsuarioGerenteRegionalTC);
							}
							return data;
						},
						"className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    },
					{ 	"mData" : "id" },
					{ 	"mData" : "origen" },
					{ 	"mData" : "bancoDeposito" },
					{ 	"mData" : "idAcuerdo" },
					{ 	"mData" : "nroBoleta" },
					{ 	"mData" : "referenciaValor" },
					{ 	"mData" : "bancoOrigen" },
					{ 	"mData" : "tipoRetencion" },
					{ 	"mData" : "provincia" },
					{ 	"mData" : "nroCuitRetencion" },
					{ 	"mData" : "codigoOrganismo" },
					{ 	"mData" : "reciboPreImpreso" },
					{ 	"mData" : "constancia" },
					{ 	"mData" : "operacionAsociada" },
					{ 	"mData" : "facturaRelacionada" },
					{ 	"mData" : "documentacionOriginalRecibida" },
					{ 	"mData" : "motivo" },
// 					{ 	"mData" : "valorPadre" },
					{ 	"mData" : "numeroDocumentoContable" },
					{ 	"mData" : "motivoSuspension" },
					{ 	"mData" : "idLegajoChequeRechazado" },
					{ 	"mData" : "fechaNotificacionRechazo" },
					{ 	"mData" : "fechaRechazo" },
					{ 	"mData" : "archivoValoresAconciliar" },
					{ 	"mData" : "fechaAltaValor" },
					{ 	"mData" : "fechaNtifDisponRetiroVal" },
					{ 	"mData" : "fechaIngresoRecibo" },
					{ 	"mData" : "fechaEmision" },
					{ 	"mData" : "fechaEmisionCheque" },
					{ 	"mData" : "fechaVencimiento" },
					{ 	"mData" : "fechaTransferencia" },
					{ 	"mData" : "fechaDeposito" },
					{ 	"mData" : "fechaDisponible" },
					{ 	"mData" : "fechaUltimoEstado" },
					{ 	"mData" : "boletaImpresaEstado" },
					{ 	"mData" : "boletaEnviadaMailEstado" },
					{ 	"mData" : "observaciones" },
					{ 	"mData" : "cobroFormateado" },
					{ 	"mData": null,
						"render": function (data, type, row) {
							if (type === 'display') {
								return '<div class="visible-md visible-lg hidden-sm hidden-xs btn-group"> ' +
											'<button type="button" id="btnModificarFinTabla_'+ data.idValor + '" class="btn btn-xs btn-link" title="Editar" onclick="javascript:modificar('+ data.idValor + ');"> ' +
												'<i class="icon-edit bigger-120"></i> ' +
											'</button> ' +
											'<button type="button" id="btnHistorial" class="btn btn-xs btn-link" title="Historial de Modificaciones" onclick="javascript:historial('+ data.idValor + ');"> ' +
												'<i class="icon-list-alt bigger-120"></i> ' +
											'</button> ' +
										'</div>';
							}
				            return data;
				          },
				        "className": "dt-body-center",
						"searchable": false,
				        "bSortable": false 
				    }],
				    "fnDrawCallback": function( oSettings ) {
	    				verificarCheckboxs('seleccionarTodos', 'opcion');
	    				$('.opcion').click(function() {
	    					verificarCheckboxs('seleccionarTodos', 'opcion');
	    					habilitarBotones();
		    			});
	    			
	    				var idUsuario = document.getElementById('idUsuario').value;
	    				var esAdministrador = document.getElementById('esAdministrador').value;
	    			
	    				$(this.fnGetData()).each(function(i, aData) {

	    					var idValor = aData.idValor;
		    	        	var estadoValor = aData.estadoValor;
		    				var idCopropietario = aData.idCopropietario;
		    				var idAnalista = aData.idAnalista;
		    				var idAnalistaTeamComercial = aData.idAnalistaTC;
		    				var esSupervisorEmpresaSegmento = aData.esSupervisorEmpresaSegmento;
		    	        	
		    	        	$('#opcion_'+idValor).hide();
		    	        	$('#btnModificar_'+idValor).hide();
		    	        	$('#btnModificarFinTabla_'+idValor).hide();
		    	        	
		    	        	if (idAnalista == idUsuario || idCopropietario == idUsuario || esSupervisorEmpresaSegmento || idAnalistaTeamComercial == idUsuario) {
		    					if(estadoValor == "<%=Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.descripcion()%>" 
		    							|| estadoValor == "<%=Estado.VAL_BOLETA_RECHAZADA.descripcion()%>"
		    							|| estadoValor == "<%=Estado.VAL_AVISO_PAGO_RECHAZADO.descripcion()%>" 
		    							|| estadoValor == "<%=Estado.VAL_DISPONIBLE.descripcion()%>" 
		    							|| estadoValor == "<%=Estado.VAL_USADO.descripcion()%>"
		    							|| estadoValor == "<%=Estado.VAL_ANULADO.descripcion()%>"
		    							|| estadoValor == "<%=Estado.VAL_SUSPENDIDO.descripcion()%>"
		    					) {				
		    								$('#opcion_'+idValor).show();    	
		    								$('#btnModificar_'+idValor).show();		 
		    				            	$('#btnModificarFinTabla_'+idValor).show();
		    				    };
		    				} else { 
		    					if(esAdministrador =='true'){		
		    						if(estadoValor =='<%=Estado.VAL_DISPONIBLE.descripcion()%>' 
		    								|| estadoValor =='<%=Estado.VAL_USADO.descripcion()%>' 
		    								|| estadoValor =='<%=Estado.VAL_SUSPENDIDO.descripcion()%>')
		    						{
		    							$('#opcion_'+idValor).show();    
		    							$('#btnModificar_'+idValor).show();	
		    							$('#btnModificarFinTabla_'+idValor).show();
		    				        };
		    					};
		    				};
	    				});
	    		}
	    		
		};
		
		//HDY: UTILIZANDO DATEPICKER DE JQUERY-UI
		$(function () {
			$.datepicker.setDefaults($.datepicker.regional["es"]);
			$("#fechaDesde").datepicker({firstDay: 1});
			$("#fechaHasta").datepicker({firstDay: 1});
		});	
		
		//@author u573005, sprint3, 
		//Defino este objeto para llevar la lista
		//de estados seleccionados
		var ValidadorEstado = function () {
			// aquí irán los métodos y propiedades del objeto
			this.listaEstados = [];
			
			this.agregar = function agregar(estado){
				if(this.listaEstados.indexOf(estado) < 0){
					this.listaEstados[this.listaEstados.length] = estado;
				}				
			}
			
			this.getEstadoSeleccionado = function estadosDistintos () {
				//aca devuelvo null si 	hay mas de un estado
				//si no devuelvo el estado
				var estadoSeleccionado = null;
				if(this.listaEstados.length == 1){
					estadoSeleccionado = this.listaEstados[0];
				}
				return estadoSeleccionado;
			}
		};
		
		(function($){
			$.isEmpty = function(obj){
				return(!obj || $.trim(obj) === "");
			};
		})(jQuery);
		
		(function($, window) {
			$.fn.replaceOptions = function(options) {
				var self, $option;
				this.empty();
				self = this;
				$.each(options, function(index, option) {
					$option = $("<option></option>")
					.attr("value", option.value)
					.text(option.text);
					self.append($option);
				});
			};
		})(jQuery, window);
		
		(function () {
		    var segPrev;
		    $("#selectSegmento").focus(function () {
		        segPrev = this.value;
		    }).change(function() {
		    	if ($.isEmpty(this.value)) {
		    		var options = [{text: 'Seleccione un item...', value : ''}];
		    		$("#analistaFiltro").replaceOptions(options);
					$("#analistaFiltro").val(options[0].value);
		    	} else if (this.value != segPrev) {
		    		$('#bloqueEspera').trigger('click');
		    		segPrev = this.value; 
		    		$.ajax({
		    			"dataType" : 'json',
		    			"type" : "GET",
		    			"url" : 'buscarAnalistas',
		    			"data" : { idEmpresa : $("#selectEmpresa").val(), idSegmento : this.value },
		    			"success" : function(result) {
		    				if (result.length == 1) {
		    					$("#analistaFiltro").replaceOptions(result);
		    				} else {
		    					var options = [];
		    					$.each(result, function(index, option) {
		    						options.push(option);
		    					});
		    					$("#analistaFiltro").replaceOptions(options);
		    					$("#analistaFiltro").val('');
		    					$('.ui-autocomplete-input').last().val('Seleccione un item...')
		    				}
		    				ocultarBloqueEspera();
		    			}
		    		});
		    	};
		    });
		})();
		
		tableResultados = $('#'+ idTabla).dataTable(resultadoBusquedaSetting);
		
		if (<c:out value = "${imprimible}"/>) {
			window.location ="${pageContext.request.contextPath}/abrir-pdf";
		}
		
		if(<c:out value = "${realizarBusqueda}"/>) {
			$('#bloqueEspera').trigger('click');
			$.ajax({
				"dataType" : 'json',
				"type" : "GET",
				"url" : "jsonBuscarValores",
				"success" : function(data) {
					tableResultados.fnClearTable();
					if (data.aaData.length > 0) {
// 						if (data.aaData.length > limiteParaExportarBusquedaValores) {
// 							exportarBusquedaValores();
// 						} else {
							tableResultados.fnAddData(data.aaData, true);
							agregarOrdenFecha(idTabla);
// 						}
					}
					tableResultados.fnSort( [ [43,'asc'] ] );
					ocultarBloqueEspera();
				}
			});
		}
		
		$("#" + idSeleccionarTodos).click(function(){
			$("." + claseRegistros + ":visible").prop('checked', $(this).prop('checked'));
			habilitarBotones();
	    });
		
		if(codCliente == "" && nroBoleta == "" && referenciaDelValor == "" && idValor == "" && idCobroShiva == ""
			&& analistaFiltro == "" && fechaDesde == "" && fechaHasta == ""){
			$("#fechaDesde").val(mesAtras);
			$("#fechaHasta").val(hoy2);
		}
		
		$('#btnCambiarEstado').attr('disabled', true);
		$('#btnImprimirConstRecep').attr('disabled', true);		
		$('#btnMailBD').attr('disabled', true);
		$('#btnImprimirBD').attr('disabled', true);
		
		$('#btnBuscar').click(function() {
		    
			var estadoValor = document.getElementById('selectEstado').value;
			var fechaDesde = document.getElementById('fechaDesde').value;
			var fechaHasta = document.getElementById('fechaHasta').value;
			
			var codCliente = document.getElementById('textCriterio').value;
			var nroBoleta = document.getElementById('nroBoletaFiltro').value;
			var referenciaDelValor = document.getElementById('referenciaDelValorFiltro').value;
			var idValor = document.getElementById('idValorFiltro').value;
			var idCobroShiva = document.getElementById('idCobroShivaFiltro').value;
			var analistaFiltro = document.getElementById('analistaFiltro').value;
			
			d2 = new Date();
			var dia= compare_dates(fechaDesde,fechaHasta);

			// Si alguno de los filtros opcionales tiene valor, no se valida la fecha
			if(codCliente =='' 
					&& nroBoleta ==''
					&& referenciaDelValor ==''
					&& idValor ==''
					&& idCobroShiva ==''
					&& analistaFiltro =='')
			{
				if(estadoValor!='<%=Estado.VAL_DISPONIBLE.name()%>'
						|| estadoValor==''){
					
					if(fechaDesde=='' || fechaHasta=='' || dia>30){
						$("#mensajeErrorFecha").show();
						$("#fechaDesdeErrorFormato").hide();
						$("#fechaHastaErrorFormato").hide();
						return false;
					};
				}
			}
				
			$('#bloqueEspera').trigger('click');
			$('#formBusqueda')[0].action="${pageContext.request.contextPath}/buscar-valores";
			$('#formBusqueda')[0].submit();
			
		});
		
		$('#btnImprimirConstRecep').click(function() {
			$('#bloqueEspera').trigger('click');
		});
		
		$('#btnMailBD').click(function() {
			$('#bloqueEspera').trigger('click');
		});
		
		$('#btnImprimirBD').click(function() {
			$('#bloqueEspera').trigger('click');
		});
		
		if ($('#selectCriterio').val() == "BUSQUEDA_POR_CUIT"){
			$("#textCriterio").attr("maxlength", 13);
		} else {
			$("#textCriterio").attr("maxlength", 11);
		}
		
		$('#selectCriterio').change(function() {
			$("#textCriterio").val("");
			
			if (this.value == "BUSQUEDA_POR_CUIT"){
				$("#textCriterio").attr("maxlength", 13);
			} else {
				$("#textCriterio").attr("maxlength", 11);
			} 
		});
		
		    $('#errorBusqueda').attr('disabled', false);
		    $('#errorSelectCriterio').attr('disabled', false);
		    
		
	});
	
// 	function exportarBusquedaValores() {
// 		$('#bloqueEspera').trigger('click');
// 	    $('#formBusqueda')[0].action="${pageContext.request.contextPath}/exportandoBusquedaValores";
// 	    $('#formBusqueda')[0].submit();
// 	};
	
	function cambiarEstado() {
		$('#bloqueEspera').trigger('click');	
		$("#valoresSelected").val($("#valoresSelected").val());
		$('#formBusqueda')[0].action="${pageContext.request.contextPath}/valores-cambio-estado";
		$('#formBusqueda')[0].submit();
	}
	
	function mailBD() {
		$('#bloqueEspera').trigger('click');	
		$("#valoresSelected").val($("#valoresSelected").val());
		$('#formBusqueda')[0].action="${pageContext.request.contextPath}/busqueda-mailbd";
		$('#formBusqueda')[0].submit();
	}
	
	function impBD() {
		$('#bloqueEspera').trigger('click');
		$("#valoresSelected").val($("#valoresSelected").val());
		$('#formBusqueda')[0].action="${pageContext.request.contextPath}/busqueda-Imprimirbd";
		$('#formBusqueda')[0].submit();
	}
	
	function impConst() {
		$('#bloqueEspera').trigger('click');
		$("#valoresSelected").val($("#valoresSelected").val());
		$('#formBusqueda')[0].action="${pageContext.request.contextPath}/busqueda-Imprimir-constancia";
		$('#formBusqueda')[0].submit();
	}
		
	//CARGA DE CAMPOS
	$("#selectEmpresa").click(function () {
		$("#selectEmpresa option:selected").css('display', 'none');
	});

	$("#selectEmpresa").change(function () {
		$('#bloqueEspera').trigger('click');
		$('#formBusqueda')[0].action="${pageContext.request.contextPath}/seleccionoEmpresaEnBusquedaValor";
		$('#formBusqueda')[0].submit();
	});
	 
	<%String lista= (String)request.getAttribute("listaLegadoRazonSocial");
	 if (lista!=null && !"".equalsIgnoreCase(lista)) {%>
	 	codigos = <%=lista%>;
	<%}%>
	 
// 	 $('#textCriterio').autocomplete({
// 		 minLength: 0,
// 	     source: codigos,
// 	     select: function( event, ui ) {
// 	    	var value = ui.item.value;
// 	    	var codigoClienteLegado = value.split("-")[0].split(" ").join("");
// 			$( "#textCriterio" ).val(codigoClienteLegado);
// 	        return false;
// 	     }
// 	 });

	$("#textCriterio").keydown(function(event) {
		$("#wsConsultaClienteSiebel").css('display', 'none');
		$("#codClienteSiebel").val("");
		var teclasQNoEscriben = ['27','37','38','39','40','16','17','18','19','20','33','34','35','36','45','144','145','91','92','93', '112','113','114','115','116','117','118','119','120','121','122','123' ];
		//Busco si la tecla presionada fue una tecla que no escribe en el campo de texto	 
		var flag = false;			    
		for (var i = 0; i < teclasQNoEscriben.length; i++) {
			if (teclasQNoEscriben[i] == keycode){
				flag = true;		
				break;
			};
		}
		//Si la tecla presionada escribe en campo texto borro campos razon social, cod.cliente, numero holding y nombre holding 				
		if (!flag) {
			$("#razonSocialClienteAgrupador").prop("readonly",false);
			$("#razonSocialClienteAgrupador").val("");
			$("#razonSocialClienteAgrupador").prop("readonly",true);
			$("#numeroHolding").prop("readonly",false);
			$("#numeroHolding").val("");
			$("#numeroHolding").prop("readonly",true);
			$("#nombreHolding").prop("readonly",false);
			$("#nombreHolding").val("");
			$("#nombreHolding").prop("readonly",true);
			$("#codClienteAgrupador").prop("readonly",false);
			$("#codClienteAgrupador").val("");
			$("#codClienteAgrupador").prop("readonly",true);
		}
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13' || keycode == '9'){
			$("#codClienteError").css('display', 'none');
			if($('#textCriterio').val() != "") {
				$('#textCriterio').val($('#textCriterio').val().split("-")[0].split(" ").join(""));
				var codigoCliente = $('#textCriterio').val().split(" ").join("");
				if (codigoCliente!="") {
					if($.isNumeric(codigoCliente) && Math.floor(codigoCliente) == codigoCliente && parseInt(codigoCliente)>0) {
// 						buscarClienteLegadoSiebel(codigoCliente);
					} else {
						$("#wsConsultaClienteSiebel").css('display', 'inline-block');
						$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
					};
				} else {
					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html('<spring:message code="error.numerico"/>');
				};			
			};
		}
		event.stopPropagation();
	});
	
	function buscarClienteLegadoSiebel(codCliente) {
		var codigoClienteLegado = codCliente.split("-")[0].split(" ").join("");
		$('#textCriterio').val(codigoClienteLegado);
		
		$('#bloqueEspera').trigger('click');
			var url = '${pageContext.request.contextPath}/consultarClienteSiebelValor?method=load&ajax=true&nocache='+Math.random();
			var param = "&codCliente="+ codigoClienteLegado;
		
			$.get(url+param, function(data) {
				if (data.toLowerCase().indexOf("error") >= 0 || data.toLowerCase().indexOf("disponible") >= 0) {
					$("#wsConsultaClienteSiebel").css('display', 'inline-block');
					$("#wsConsultaClienteSiebel").html(data);
				} else {
					if (data.length == 0) {
						$("#wsConsultaClienteSiebel").css('display', 'inline-block');
						$("#wsConsultaClienteSiebel").html('<spring:message code="boleta.alta.mensaje.siebel.vacio"/>');
					} else {
						var dividoEnDos = data.split("|");
						var res = dividoEnDos[0].split(";");
						$("#codClienteAgrupador").prop("readonly",false);
						$("#codClienteAgrupador").val(res[0]);
						$("#codClienteAgrupador").prop("readonly",true);
					 		
						$("#razonSocialClienteAgrupador").prop("readonly",false);
						$("#razonSocialClienteAgrupador").val(res[1]);
						$("#razonSocialClienteAgrupador").prop("readonly",true);
	
						$("#numeroHolding").val(res[2]);								
					 	$("#nombreHolding").val(res[3]);
					}
				}
				ocultarBloqueEspera();
			});
	};
	
	function modificar(idValor) {
		$('#bloqueEspera').trigger('click');
		
		$("#idValor").val(idValor);
		$("#pantallaDestino").val('<%=Constantes.DESTINO_VUELTA_BUSQUEDA%>');
		$('#segundaParteFormulario')[0].action="${pageContext.request.contextPath}/procesar-valores-modificacion";
		$('#segundaParteFormulario')[0].submit();
	};
	
	function historial(idValor) {
		$('#bloqueEspera').trigger('click');
		
		$("#idValor").val(idValor);
		$('#segundaParteFormulario')[0].action="${pageContext.request.contextPath}/buscar-historial-valor";
		$('#segundaParteFormulario')[0].submit();
	};
	
	 function compare_dates(fecha, fecha2)  
	  {
		var xMonth=fecha.substring(3, 5);  
	    var xDay=fecha.substring(0, 2);  
	    var xYear=fecha.substring(6,10);  
	    var yMonth=fecha2.substring(3, 5);  
	    var yDay=fecha2.substring(0, 2);  
	    var yYear=fecha2.substring(6,10);  
	    
	  	var date1 = new Date(xYear,xMonth,xDay);
		var date2 = new Date(yYear,yMonth, yDay);
		var fin = date2.getTime() - date1.getTime();
		var dias = Math.floor(fin / (1000 * 60 * 60 * 24));
		
		
		return dias;
	
	  }
	 Date.prototype.yyyymmdd = function() {         
         
	        var yyyy = this.getFullYear().toString();                                    
	        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
	        var dd  = this.getDate().toString();             
	        return (dd[1]?dd:"0"+dd[0]) + '/' + (mm[1]?mm:"0"+mm[0]) + '/' + yyyy;
	   };	 
	 
	   $(function() {
		    $( "#analistaFiltro" ).combobox();
		    $( "#toggle" ).click(function() {
		      $( "#combobox" ).toggle();
		    });
		});
	 
	   (function( $ ) {
		    $.widget( "custom.combobox", {
		      _create: function() {
		        this.wrapper = $( "<span>" )
		          .addClass( "custom-combobox" )
		          .insertAfter( this.element );
		 
		        this.element.hide();
		        this._createAutocomplete();
		        this._createShowAllButton();
		        
		      },
		 
		      _createAutocomplete: function() {
		        var selected = this.element.children( ":selected" ),
		          value = selected.val() ? selected.text() : "Seleccione un item...";
		 
		        this.input = $( "<input>" )
		          .appendTo( this.wrapper )
		          .val( value )
		          .attr( "title", "" )
		          .addClass( "" )
		          .autocomplete({
		            delay: 0,
		            minLength: 0,
		           
		            source: $.proxy( this, "_source" )
		          })
		          .tooltip({
		            tooltipClass: "ui-state-highlight"
		          });
		 
		        this._on( this.input, {
		          autocompleteselect: function( event, ui ) {
		        	 
		            ui.item.option.selected = true;
		            this._trigger( "select", event, {
		              item: ui.item.option
		             
		            });
		          },
		 
		          autocompletechange: "_removeIfInvalid"
		        });
		      },
		 
		      _createShowAllButton: function() {
		        var input = this.input,
		          wasOpen = false;
		 
		        $( "<a>" )
		          .attr( "tabIndex", -1 )
		          .tooltip()
		          .appendTo( this.wrapper )
		          .button({
		            icons: {
		                primary: "ui-icon-triangle-1-s"
		            },
		            text: false
		          })
		          .removeClass( "ui-corner-all" )
		          .removeClass("ui-button")
		          .addClass( "custom-combobox-toggle ui-corner-right" )
		          .mousedown(function() {
		            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
		          })
		          .click(function() {
		            input.focus();
		 
		            // Close if already visible
		            if ( wasOpen ) {
		              return;
		            }
		 
		            // Pass empty string as value to search for, displaying all results
		            input.autocomplete( "search", "" );
		          });
		      },
		 
		      _source: function( request, response ) {
		        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
		        response( this.element.children( "option" ).map(function() {
		          var text = $( this ).text();
		          if ( this.value && ( !request.term || matcher.test(text) ) )
		            return {
		              label: text,
		              value: text,
		              option: this
		            };
		        }) );
		      },
		 
		      _removeIfInvalid: function( event, ui ) {
		    	  
		        // Selected an item, nothing to do
		        if ( ui.item ) {
		        	
		          return;
		        }
		 
		        // Search for a match (case-insensitive)
		        var value = this.input.val(),
		          valueLowerCase = value.toLowerCase(),
		          valid = false;
		        this.element.children( "option" ).each(function() {
		          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
		            this.selected = valid = true;
		            
		            return false;
		          }
		        });
		 
		        // Found a match, nothing to do
		        if ( valid ) {
		        	
		          return;
		        }
		        
		        // Remove invalid value
		        this.input
		          .val( "Seleccione un item..." )
		          .attr( "title", value + " no coincide ningun registro" )
		          .tooltip( "open" );
		        this.element.val( "" );
		        this._delay(function() {
		          this.input.tooltip( "close" ).attr( "title", "" );
		         
		        }, 2500 );
		        this.input.data( "ui-autocomplete" ).term = "";
		      },
		 
		      _destroy: function() {
		        this.wrapper.remove();
		        this.element.show();
		      }
		    });
		  })( jQuery );
	 	
		
</script>

</body>
</html>