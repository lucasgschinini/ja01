<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title><spring:message code="valor.autorizacion.titulo"/></title>
	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
    <meta name="Alta de Avisos" content="Modelo">
<script>
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
						<div class="title"><spring:message code="valor.autorizacion.titulo.pagina"/></div>
						<div class="pagos_anticipos">
							<form:form id="formAutorizacion" commandName="boletaConValorFiltro" action="${pageContext.request.contextPath}/valores-autorizacion">
								<form:hidden path="valoresSelected" id="valoresSelected"/>
								
								<input type="hidden" id="cancel" name="cancel" value="${botonCancelarDisplay}" />
								<input type="hidden" id="observaciones" name="observaciones" value="${observacionesErrorVuelta}"/>
								
								<c:if test="${!botonBuscarDisplay}">
									<input type="hidden" id="idValorBandeja" name="idValorBandeja" value="${boletaConValorFiltro.idValorBandeja}"/>
									<input type="hidden" id="pantallaDestino" name="pantallaDestino" value="${boletaConValorFiltro.pantallaDestino}"/>
								</c:if>
								<c:if test="${botonBuscarDisplay}">
								<p><strong><spring:message code="valor.autorizacion.criterioBusqueda"/></strong></p>
								
								<div class="row">
									<!-- EMPRESA -->
									<div class="span3">
										<label class="control-label" for="selectEmpresa"><span class="rojo">* </span><spring:message code="valor.empresa"/></label>
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
										<span id="mensajeObligatorioEmpresa" class="error" style="display: none;"></span>
									</div>
									<!-- SEGMENTO -->									
									<div class="span3">
										<label class="control-label" for="selectSegmento"><span class="rojo">* </span><spring:message code="valor.segmento"/></label>
										<div class="controls">
											<select id ="selectSegmento" name="segmento" class="input">	
											<c:if test="${comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
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
										<span id="mensajeObligatorioSegmento" class="error" style="display: none;"></span>
									</div>
								</div>
								
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="empresa" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="segmento" cssClass="error" />
									</div>
								</div>

								<div class="row" align="right" style="margin-top: 15px; margin-bottom: 15px; width: 699px;">
								
									<button class="btn btn-primary btn-small" id="btnBuscar" name="btnBuscar" title=<spring:message code="valor.botonBuscar"/> type="button">
											<i class="icon-white icon-search"></i>  <spring:message code="valor.botonBuscar"/>											
										</button>
										
<%-- 									<input type="hidden" id="empresaValorVuelta" name="empresaValorVuelta" value="${empresaModel}" /> --%>
<%-- 									<input type="hidden" id="segmentoValorVuelta" name="segmentoValorVuelta" value="${segmentoModel}" /> --%>
								</div>
								</c:if>
							</form:form>	
							<br>								
							<p><strong><spring:message code="valor.resultadoBusqueda"/></strong></p>
							<form:form id="formValores" commandName="listaValoresDto">
								<div class="row">
									<div class="span9">
										<table id="resultadoBusqueda" class="tablaResultado">
											<thead>
												<tr>
													<th style="padding: 3px 10px; text-align: center;">
														<input type="checkbox" id="seleccionarTodos">
													</th>
													<th style="display:none"></th>													
													<th><spring:message code="valor.empresa"/></th>
													<th><spring:message code="valor.segmento"/></th>
													<th><spring:message code="valor.codCliente"/></th>
													<th><spring:message code="valor.razonSocialAgrupador"/></th>
													<th><spring:message code="valor.tipoValor"/></th>
													<th><spring:message code="valor.origen"/></th>
													<th><spring:message code="valor.acuerdo"/></th>
													<th><spring:message code="valor.nroValor"/></th>
													<th><spring:message code="valor.importe"/></th>
													<th class="date"><spring:message code="valor.fechaIngresoRecibo"/></th>
													<th><spring:message code="valor.estado"/></th>
													<th><spring:message code="valor.analista"/></th>
													<th><spring:message code="valor.supervisor"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaValoresDto}" var="val">
												<tr>
													<td>
														<input type="checkbox" class="opcion" id="opcion_${val.idValor}" name="opcion" value="${val.idValor}" />
														<input type="hidden" id="hddCodEstado_${val.idValor}" name="hddCodEstado_${val.idValor}" value="${val.estadoValor}" />
														<input type="hidden" id="hddCodTipoValor_${val.idValor}" name="hddCodTipoValor_${val.idValor}" value="${val.idTipoValor}" />
														<input type="hidden" id="hddTimeStamp_${val.idValor}" name="hddTimeStamp_${val.idValor}" value="${val.timeStamp}" />
													</td>
													<td style="display:none">${val.fechaAltaValor}</td>
													<td>${val.empresa}</td>
													<td>${val.segmento}</td>
													<td>${val.codCliente}</td>
													<td>${val.razonSocialClienteAgrupador}</td>
													<td>${val.tipoValor}</td>
													<td>${val.origen}</td>
													<td>${val.idAcuerdo}</td>
													<td>${val.numeroValor}</td>
													<td>${val.importe}</td>
													<td>${val.fechaIngresoRecibo}</td>
													<td>${val.estadoValor}</td>
													<td style="text-align: left;">
														<div style="width: 150px">
															<c:choose>
																<c:when test="${fn:trim(val.nombreAnalista) ne '-'}">
																<div style="width: 150px">
																	<img alt="Usuario" src="${val.urlFotoUsuario(val.idAnalista)}"
																	style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																	
																	onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																	${val.nombreAnalista} <br>
																	<a href="sip:${val.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																	<a href="mailto:${val.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																</div>
																</c:when>
																<c:otherwise><div style="width: 150px; text-align: center;">${val.nombreAnalista} <br></div></c:otherwise>
															</c:choose>
														</div>
													</td>
													<td style="text-align: left;">
														<div style="width: 150px">
															<c:choose>
																<c:when test="${fn:trim(val.nombreSupervisor) ne '-'}">
																<div style="width: 150px">
																	<img alt="Usuario" src="${val.urlFotoUsuario(val.idSupervisor)}"
																	style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																	onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																	${val.nombreSupervisor} <br>
																	<a href="im:${val.mailSupervisorIconoChat}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																	<a href="mailto:${val.mailSupervisorIconoMail}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																	
																</div>
																</c:when>
																<c:otherwise><div style="width: 150px; text-align: center;">${val.nombreSupervisor} <br></div></c:otherwise>
															</c:choose>
														</div>
											 		</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
	
								<div class="row" style="margin-top: 20px; margin-bottom: 10px">
									<div class="span9">
										<label class="control-label" for="observaciones"><spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="observacionesAutorizacion" name="observacionesAutorizacion" maxlength="250">${observacionesErrorVuelta}</textarea>
									</div>
								</div>
								<c:if test="${observacionesObligatorioError}">
									<div class="row rowError">
										<div class="span9" style="color:red;font-size:9px;"><spring:message code="error.obligatorio"/></div>
									</div>
								</c:if>	
								<c:if test="${observacionesFormatoError}">
									<div class="row rowError" >
										<div class="span9" style="color:red;font-size:9px;"><spring:message code="error.formatoTexto"/></div>
									</div>
								</c:if>
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<c:if test="${botonCancelarDisplay}">
											<button class="btn btn-primary btn-small" id="btnCancelar" name="btnCancelar" type="button" onclick="regresarABandejaEntrada();"><i class="icon-white icon-repeat"></i>&nbsp;<spring:message code="valor.botonCancelar"/></button>
										</c:if>
										<button class="btn btn-primary btn-small" id="btnAutorizar" name="btnAutorizar" type="button"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="valor.botonAutorizar"/></button>
										<button class="btn btn-primary btn-small" id="btnRechazar" name="btnRechazar" type="button"><i class="icon-white icon-remove"></i>&nbsp;<spring:message code="valor.botonRechazar"/></button>
									</div>
								</div>	
							</form:form>
							
						</div>	
					</div>
				</div>
			</div>															<!-- end #content -->
		</div>																<!-- end .wrap -->
	</div>																	<!-- end #main -->
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
</div>																		<!-- end #container -->

<script>

//@authorname u573005, sprint 3	
var habilitarBotones = function () {

   	$('#btnAutorizar').attr('disabled', false);
	$('#btnRechazar').attr('disabled', false);
	if($("." + claseRegistros + ":checked", $('#' + idTabla).dataTable().fnGetNodes()).length == 0) {				
		$('#btnAutorizar').attr('disabled', true);	    		
		$('#btnRechazar').attr('disabled', true);
	}
		
}

var idTabla = "resultadoBusqueda";
var claseRegistros = "opcion";
var idSeleccionarTodos = "seleccionarTodos";

	//INICIO DE PAGINA
	// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
		// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
		// En IE8 no hace falta llamar a fnCellRender
	 var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
		// la columna 1 no la muestro por que esta como invisible <td style="display:none">${val.fechaAltaValor}</td>
		return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0, 1], [13,14]);
	};	
	window.onload = function() {
		
		//Se inicializan las tablas
		inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTabla, claseRegistros, idSeleccionarTodos,
				habilitarBotones, (window.DOMParser) ? fnCellRender : null);
		
		agregarOrdenFecha(idTabla);
		
		$('#' + idTabla).dataTable().fnSort( [ [0,'asc'] ] );	
		
		//@author u573005, sprint3
		//Se posiciona justo despues de inicializar la tabla
		if($('#idValorBandeja').length > 0){
			$("." + claseRegistros + ":visible").prop('checked', true);
			$("#" + idSeleccionarTodos).prop('checked', true);
	       	habilitarBotones();
		}else{
			$('#btnAutorizar').attr('disabled', true);
			$('#btnRechazar').attr('disabled', true);
		}
		
		//@author u573005, sprint3
		//Se mantiene este codigo para respetar la seleccion
		//antes de apretar los botones
		if($('#valoresSelected').val() != ""){
			var valorSelectedSplit = $('#valoresSelected').val().split(',');
			for ( var e = 0; e < valorSelectedSplit.length; e++) {
				var valorSplit = valorSelectedSplit[e].split('_');
				$(".opcion").each(function(i,elem){
					var id = $(elem).val();
					if (id === valorSplit[0]){
						$(elem).prop('checked', true);
					}
				});
				
			}
			verificarCheckboxs(idSeleccionarTodos, claseRegistros);
			habilitarBotones();
		}

		 $("#" + idSeleccionarTodos).click(function () {
	       	$("." + claseRegistros + ":visible").prop('checked', $(this).prop('checked'));
	       	habilitarBotones();
	    });
		

		
		$('#btnBuscar').click(function() {		
			var empresa =  $('#selectEmpresa').val();
			var segmento =  $('#selectSegmento').val();
			
			if(empresa=='' ){
				$("#mensajeObligatorioEmpresa").css('display', 'inline-block');
				$("#mensajeObligatorioEmpresa").html('<spring:message code="error.obligatorio"/>');	
				return false;
			}
			
			if(segmento =='' ){
				$("#mensajeObligatorioSegmento").css('display', 'inline-block');
				$("#mensajeObligatorioSegmento").html('<spring:message code="error.obligatorio"/>');	
				return false;
			}
			
			$('#bloqueEspera').trigger('click');		
			$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/buscar-autorizar";
			$('#formAutorizacion').submit();
		});
		

		 
		//CARGA DE CAMPOS
		$("#selectEmpresa").change(function () {
			$('#bloqueEspera').trigger('click');
			$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/seleccionoEmpresaEnAutorizacionValor";
			$('#formAutorizacion')[0].submit();
		});

		//READY 
		var str;

		$('#btnAutorizar').click(function() {
			str = '';
			var w=0;
			$("." + claseRegistros + ":checked", $("#" + idTabla).dataTable().fnGetNodes()).each(function(i,elem){
	        	var id = $(elem).val();
	        	var timeStamp = $('#hddTimeStamp_' + id).val();
	        	var valor = id+"_"+timeStamp;
	        	
	        	if($(this).is(":checked")) {
					 var coma=(w==0)?"":",";
					 str+=coma+valor;
				}
				w++;
		    });
			
			$('#valoresSelected').val(str);
			$('#observaciones').val($('#observacionesAutorizacion').val());
			
			$('#bloqueEspera').trigger('click');
			$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/autorizaciones-autorizar";
			$('#formAutorizacion')[0].submit();
		});

		$('#btnRechazar').click(function() {
			str = '';
			var w=0;
			$("." + claseRegistros + ":checked", $("#" + idTabla).dataTable().fnGetNodes()).each(function(i,elem){
	        	var id = $(elem).val();
	        	var timeStamp = $('#hddTimeStamp_' + id).val();
	        	var valor = id+"_"+timeStamp;
	        	
	        	if($(this).is(":checked")) {
					 var coma=(w==0)?"":",";
					 str+=coma+valor;
				}
				w++;
		    });
			
			$('#valoresSelected').val(str);
			$('#observaciones').val($('#observacionesAutorizacion').val());
			
			$('#bloqueEspera').trigger('click');		
			$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/autorizar-rechazar";
			$('#formAutorizacion')[0].submit();
		});	
		
		function regresarABandejaEntrada(){
			$('#bloqueEspera').trigger('click');
			$('#formAutorizacion')[0].action="${pageContext.request.contextPath}/regresar-bandeja-de-entrada";
			$('#formAutorizacion')[0].submit();		
		}
		
	};
</script>
</body>
</html>