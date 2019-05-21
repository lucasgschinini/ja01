<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado" %>

<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="boleta.busqueda.titulo"/></title>

   
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

					<div id="payments" class="box">
						<div class="title"><spring:message code="boleta.busqueda.titulo.pagina"/></div>
						
						<div class="pagos_anticipos">
							<p><strong><spring:message code="boleta.busqueda.criteriosBusqueda"/></strong></p>
			
							<form:form id="formBusqueda" commandName="boletaSinValorFiltro" action="${pageContext.request.contextPath}/buscar-boletas">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="empresa"><span class="rojo">* </span><spring:message code="boleta.empresa"/></label>
										<div class="controls ">
											<select id ="selectEmpresa" name="empresa" class="input">
												<c:if test="${comboEmpresa}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${empresas}" var="emp">
													<c:choose>
														<c:when test="${emp.idEmpresa eq boletaSinValorFiltro.empresa}">
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
									<div class="span3">
										<label class="control-label" for="segmento"><span class="rojo">* </span><spring:message code="boleta.segmento"/></label>
										<div class="controls">
											<select id ="selectSegmento" name="segmento" class="input">
												<c:if test="${comboSegmento}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>
												<c:forEach items="${segmentos}" var="seg">
													<c:choose>
														<c:when test="${seg.idSegmento eq boletaSinValorFiltro.segmento}">
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
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="empresa" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="segmento" cssClass="error" />
									</div>
								</div>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaDesde"><span class="rojo">* </span><spring:message code="boleta.busqueda.fechaAltaDesde"/></label>
										<div class="controls">
											<input class="span3" type="text" id="dp1" name="fechaDesde"
												data-date-format="dd/mm/yyyy" data-date-language='es' value="${boletaSinValorFiltro.fechaDesde}">
										</div>
									</div>
				
									<div class="span3">
										<label class="control-label" for="fechaHasta"><span class="rojo">* </span><spring:message code="boleta.busqueda.fechaAltaHasta"/></label>
										<div class="controls">
											<input class="span3" type="text" id="dp2" name="fechaHasta"
												data-date-format="dd/mm/yyyy" data-date-language='es' value="${boletaSinValorFiltro.fechaHasta}">
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="fechaDesde" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="fechaHasta" cssClass="error" />
									</div>
								</div>
								<div class="row">
									<div class="span3">
										<label class="control-label" for="importeDesde"><spring:message code="boleta.busqueda.importeDesde"/></label>
										<div class="controls">
											<input id="importeDesde" name="importeDesde" type="text" class="input" maxlength="16"
												value="${boletaSinValorFiltro.importeDesde}">
										</div>
									</div>
									<div class="span3">
										<label class="control-label" for="importeHasta"><spring:message code="boleta.busqueda.importeHasta"/></label>
										<div class="controls">
											<input id="importeHasta" name="importeHasta" type="text" class="input" maxlength="16"
												value="${boletaSinValorFiltro.importeHasta}">
										</div>
									</div>
								</div>
								<div class="row rowError" >
									<div class="span3">
										<form:errors path="importeDesde" cssClass="error" />
									</div>
									<div class="span3">
										<form:errors path="importeHasta" cssClass="error" />
									</div>
								</div>
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="btnBuscar" name="btnBuscar" type="submit"><i class="icon-white icon-search"></i><spring:message code="boleta.busqueda.boton.buscar"/></button>
									</div>
								</div>
								
							</form:form>
						</div>

						<!-- SEGUNDA PARTE DEL FORMULARIO -->

						<div class="pagos_anticipos">
							<p><strong><spring:message code="boleta.busqueda.resultadoBusqueda"/></strong></p>
							
							<form:form id="formTabla" commandName="boletaSinValorFiltro">
								<form:hidden path="boletasAAnularSelected" />
								<form:hidden path="boletasAEnviarMailSelected" />
								<form:hidden path="boletasAImprimirSelected" />
								<form:hidden path="idBoleta" />
								<div class="row">
									<div class="span9">
					
										<table id="resultadoBusqueda" class="tablaResultado">
											<thead>
												<tr>
													<th style="padding: 3px 10px; text-align: center;">
														<input type="checkbox" id="seleccionarTodos">
													</th>
													<th><spring:message code="boleta.empresa"/></th>
													<th><spring:message code="boleta.segmento"/></th>
													<th><spring:message code="boleta.estado"/></th>
													<th><spring:message code="boleta.nroBoleta"/></th>
													<th><spring:message code="boleta.codClienteLegado"/></th>
													<th><spring:message code="boleta.razonSocialClienteAgrupador"/></th>
													<th><spring:message code="boleta.importe"/></th>
													<th><spring:message code="boleta.origen"/></th>
													<th><spring:message code="boleta.nroAcuerdo"/></th>
													<th><spring:message code="boleta.bdImpresa"/></th>
													<th><spring:message code="boleta.bdEnviadaMail"/></th>
													<th class="dateTimeSeconds"><spring:message code="boleta.fechaAlta"/></th>
													<th><spring:message code="boleta.analista"/></th>
													<th><spring:message code="boleta.operacionAsociada"/></th>
													<th><spring:message code="boleta.espacio"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaBoletasDto}" var="bol">
													<tr>
														<td>
															<c:if test="${!bol.esEstadoAnulado}">
																<input type="checkbox" class="opcion" id="opcion" name="opcion" value="${bol.id}"/>
																<input type="hidden" id="hddCodEstado_${bol.id}" name="hddCodEstado_${bol.id}" value="${bol.codEstado}" />
																<input type="hidden" id="hddTimeStamp_${bol.id}" name="hddTimeStamp_${bol.id}" value="${bol.timeStamp}" />
															</c:if>
														</td>
														<td>${bol.empresa}</td>
														<td>${bol.segmento}</td>
														<td>${bol.estado}</td>
														<td>${bol.numeroBoleta}</td>
														<td>${bol.codCliente}</td>
														<td>${bol.razonSocialClienteAgrupador}</td>
														<td>${bol.importe}</td>
														<td>${bol.origen}</td>
														<td>${bol.acuerdo}</td>
														<td>${bol.boletaImpresaEstado.descripcion}</td>
														<td>${bol.boletaEnviadaMailEstado.descripcion}</td>
														<td>${bol.fechaAltaFormateado}</td>
 														<td style="text-align: left"> 
 														  <div style="width: 140px;">
 															<img alt="Usuario" src="${bol.urlFotoUsuario(bol.idAnalista)}"  
 															style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
 															onerror='src="${pageContext.request.contextPath}/img/default2.png"'> 
 															${bol.nombreAnalista} <br> 
 															<a href="sip:${bol.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a> 
 															<a href="mailto:${bol.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a> 
 												 		   </div> 
 												 		</td> 
														<td>${bol.operacionAsociada}</td>
														<td>
															<c:if test="${bol.esEstadoPendiente and sessionScope.loginUser.puedeModificarBoletaSinValor}">
																<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																	<button id="btnModificar" class="btn btn-xs btn-link" title="Editar" type="button" onclick="javascript:modificar(${bol.id});">
																		<i class="icon-edit bigger-120"></i>
																	</button>
																</div>
															</c:if>
														</td>
													</tr>
												</c:forEach>
											</tbody>	
										</table>
										
									</div>
								</div>
								
								<br>
									
								<div class="row">
									<div class="span9" align="right">
										<c:if test="${sessionScope.loginUser.puedeEnviarMailBoletaSinValor}">
											<button type="button" class="btn btn-primary btn-small" id="btnMailBD" name="btnMailBD" onclick="javascript:enviarMail();"><i class="icon-white icon-envelope"></i><spring:message code="boleta.busqueda.boton.enviarMailBoleta"/></button>
										</c:if>	
										<c:if test="${sessionScope.loginUser.puedeImprimirBoletaSinValor}">
											<button type="button" class="btn btn-primary btn-small" id="btnImprimirBD" name="btnImprimirBD" onclick="javascript:imprimirBD();"><i class="icon-white icon-print"></i><spring:message code="boleta.busqueda.boton.VerImprBoleta"/></button>
										</c:if>
										<c:if test="${sessionScope.loginUser.puedeAnularBoletaSinValor}">
											<button type="button" class="btn btn-primary btn-small" id="btnAnular" name="btnAnular"><i class="icon-white icon-remove"></i><spring:message code="boleta.busqueda.boton.anular"/></button>
										</c:if>
									</div>
								</div>
				
							</form:form>
						</div>
					</div>
				</div>

			</div><!-- end #content -->
		</div><!-- end .wrap -->

	</div><!-- end #main -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

	</div><!-- end #container -->
	<!-- FIN WEB -->

	<script>
		var listaBoletas = "";
		var estadoBolPendiente= '<%=Estado.BOL_PENDIENTE.name()%>';
		
		var idTabla = "resultadoBusqueda";
		var claseRegistros = "opcion";
		var idSeleccionarTodos = "seleccionarTodos";
		
		var habilitarBotones = function(){
			
			if($("." + claseRegistros + ":checked", $('#' + idTabla).dataTable().fnGetNodes()).length == 0) {
	    		$('#btnImprimirBD').attr('disabled', true);
	    		$('#btnAnular').attr('disabled', true);
	    		$('#btnMailBD').attr('disabled', true);
	    	} else {
	    		habilitarBtnImprimirBD(); 
	    		habilitarBtnMailBD(); 
	    		habilitarBtnAnular();
	    	}
			
		}
		// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
		// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
		// En IE8 no hace falta llamar a fnCellRender
		 var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
			return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0,15], [13]);
		};
		// Tratamiento de tablas
		window.onload = function() {
			
			//Se inicializan las tablas
			inicializarTablaCon_PDF_XLS_PrimeraColumnaNoOrd_SelTodos(idTabla, claseRegistros, idSeleccionarTodos,
					habilitarBotones, (window.DOMParser) ? fnCellRender : null);
			agregarOrdenFecha(idTabla);
			$('#' + idTabla).dataTable().fnSort( [ [12,'asc'] ] );

			if("${imprimibleArchivo}" === true){
				window.location ="${pageContext.request.contextPath}/abrir-documento";
			};
			
			// add multiple select / deselect functionality
		    $("#seleccionarTodos").click(function () {
		    	$('.opcion').prop('checked', $(this).prop('checked'));		    	
		    	habilitarBotones();	
		    });
			
			
		    $.datepicker.setDefaults($.datepicker.regional["es"]);
			$("#dp1").datepicker({firstDay: 1});
			$("#dp2").datepicker({firstDay: 1});
			
			$('#btnMailBD').attr('disabled','disabled');
			$('#btnImprimirBD').attr('disabled','disabled');
			$('#btnAnular').attr('disabled','disabled');
			
	
			$('#btnAnular').click(function() {
				listaBoletas = "";
				
				var sData = $(".opcion:checked", oTable.fnGetNodes()).serialize();
				sData = sData.replace(/opcion=/gi, "");
				sData = sData.replace(/&/gi, ",");
				listaBoletas = sData;

		    	var mensaje = '<spring:message code="boleta.busqueda.mensaje.anular.ok"/>';
		    	
		    	bootbox.confirm(mensaje, function(result) {
		    		if (result) {
		    			$('#bloqueEspera').trigger('click');
		    	
		    			var listaCompleta="";
		    			var lstBoletas = listaBoletas.split(",");
		    			for(var num = 0; num < lstBoletas.length; num++) {
		    				var id = lstBoletas[num];
		    				var timeStamp = $('#hddTimeStamp_' + id).val();	
		    				listaCompleta+=id+"_"+timeStamp+",";	
		    			}
		    			$('#boletasAAnularSelected')[0].value=listaCompleta;
		    			
		    			$('#formTabla')[0].action="${pageContext.request.contextPath}/boletas-anular";
		    			$('#formTabla')[0].submit();
		    		};
		    	});
			   	
			});
			
			 $("#selectEmpresa").change(function () {
				$('#formBusqueda')[0].action="${pageContext.request.contextPath}/seleccionoEmpresaEnBusqueda";
				$('#formBusqueda')[0].submit();
				$('#bloqueEspera').trigger('click');
			});
			
			
		}
		
		$(document).ready(function(){
			$('#btnModificar').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			$('#btnBuscar').click(function() {
				$('#bloqueEspera').trigger('click');
			});
			
			
		});
		
		
		function habilitarBtnImprimirBD() {
			$('#btnImprimirBD').attr('disabled', true);
			
			$(".opcion:checked", oTable.fnGetNodes()).each(function(i,elem){
	        	$('#btnImprimirBD').attr('disabled', false);
	    		return false;
	        });
		}
		
		function habilitarBtnMailBD() {
			$('#btnMailBD').attr('disabled', true);
			
			$(".opcion:checked", oTable.fnGetNodes()).each(function(i,elem){
	        	var valor = $(elem).val();
				var hddCodEstado = $('#hddCodEstado_' + valor).val();
				
				$('#btnMailBD').attr('disabled', false);
				if (hddCodEstado != estadoBolPendiente) {
					$('#btnMailBD').attr('disabled', true);
					return false;
				};
	        });
		}
		
		function habilitarBtnAnular() {
			$('#btnAnular').attr('disabled', true);
			
			$(".opcion:checked", oTable.fnGetNodes()).each(function(i,elem){
	        	var valor = $(elem).val();
				var hddCodEstado = $('#hddCodEstado_' + valor).val();
				
				$('#btnAnular').attr('disabled', false);
				if (hddCodEstado != estadoBolPendiente) {
					$('#btnAnular').attr('disabled', true);
					return false;
				};
	        });
		}
		
		function modificar(idBoleta) {
			$('#bloqueEspera').trigger('click');
			$('#idBoleta')[0].value=idBoleta;
			$('#formTabla')[0].action="${pageContext.request.contextPath}/boletas-modificacion";
			$('#formTabla')[0].submit();
		}

		function enviarMail() {
			listaBoletas= "";
			var sData = $(".opcion:checked", oTable.fnGetNodes()).serialize();
			sData = sData.replace(/opcion=/gi, "");
			sData = sData.replace(/&/gi, ",");
			listaBoletas = sData;
		
			var listaCompleta="";
			var lstBoletas = listaBoletas.split(",");
			for(var num = 0; num < lstBoletas.length; num++) {
				var id = lstBoletas[num];
				listaCompleta+=id+",";	
			}
		
			$('#bloqueEspera').trigger('click');	
			$('#boletasAEnviarMailSelected')[0].value=listaCompleta;		
			$('#formTabla')[0].action="${pageContext.request.contextPath}/enviar-mail-boleta";
			$('#formTabla')[0].submit();
		}

		function imprimirBD() {
			listaBoletas= "";
			var sData = $(".opcion:checked", oTable.fnGetNodes()).serialize();
			sData = sData.replace(/opcion=/gi, "");
			sData = sData.replace(/&/gi, ",");
			listaBoletas = sData;
			
			var listaCompleta="";
			var lstBoletas = listaBoletas.split(",");
			
			for(var num = 0; num < lstBoletas.length; num++) {
				var id = lstBoletas[num];
				listaCompleta+=id+",";	
			}
		
			$('#bloqueEspera').trigger('click');
			$('#boletasAImprimirSelected')[0].value=listaCompleta;
			$('#formTabla')[0].action="${pageContext.request.contextPath}/imprimir-boleta";
			$('#formTabla')[0].submit();
		}
		
</script>
</body>
</html>