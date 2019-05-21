<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><spring:message code="conciliacion.titulo"/></title>
   
   <jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

	<meta name="Resultado de la Conciliación" content="Modelo">
 	
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
						<div class="title"><spring:message code="conciliacion.resultado.titulo.pagina"/></div>

						<div class="pagos_anticipos">							

							<p><strong><spring:message code="conciliacion.criteriosBusqueda"/></strong></p>
							<form:form id="formBusqueda" commandName="archivoAvcFiltro" action="${pageContext.request.contextPath}/conciliacion-archivos-busqueda">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="fechaDesde"><spring:message code="conciliacion.fechaProcesamientoDesde"/></label>
										<div class="controls">
											<input class="span3" type="text" id="dp1" name="fechaDesde"
												data-date-format="dd/mm/yyyy" data-date-language='es' value="${archivoAvcFiltro.fechaDesde}">
										</div>
									</div>
				
									<div class="span3">
										<label class="control-label" for="fechaHasta"><spring:message code="conciliacion.fechaProcesamientoHasta"/></label>
										<div class="controls">
											<input class="span3" type="text" id="dp2" name="fechaHasta"
												data-date-format="dd/mm/yyyy" data-date-language='es' value="${archivoAvcFiltro.fechaHasta}">
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
								
								<div class="row" style="margin-top: 0px; margin-bottom: 0px; margin-right: 2px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="buscar" name="buscar" type="submit"><i class="icon-white icon-search"></i><spring:message code="conciliacion.botonBuscar"/></button>
									</div>
								</div>
								
							</form:form>
							
							<!-- Esto es para que el datepicker este alejado de los botones de exportar -->
							<br><br><br><br><br><br><br><br>

							<p><strong><spring:message code="conciliacion.resultadoBusqueda"/></strong></p>
							<form:form id="resultado" commandName="archivoAvcFiltro" >
								<form:hidden path="idArchivoAvcSelect" id="idArchivoAvcSelect"/>
								
								<div class="row">
									<div class="span9">
										<table id="tablaConciliacionResultado" class="tablaResultado" >
											<thead>
												<tr>
													<th><spring:message code="conciliacion.nombreArchivo"/></th>
													<th class="dateTimeSeconds"><spring:message code="conciliacion.fechaProcesamiento"/></th>
													<th><spring:message code="conciliacion.nroAcuerdo"/></th>
													<th><spring:message code="conciliacion.registrosProcesados"/></th>
													<th><spring:message code="conciliacion.registrosConciliados"/></th>
													<th><spring:message code="conciliacion.registrosConciliacionSugerida"/></th>
													<th><spring:message code="conciliacion.registrosSinConciliar"/></th>
													<th><spring:message code="conciliacion.registrosAnulados"/></th>
													<th><spring:message code="conciliacion.logDeProcesamiento"/></th>
													<th></th>
												</tr>
											</thead>

											<tbody>
											 <c:forEach items="${listaArchivos}" var="arch" varStatus="i">			
												<tr>
													<td>${arch.nombreArchivo}</td>
													<td>${arch.fechaProcesamientoFormateado}</td>
													<td>${arch.nroAcuerdo}</td>
													<td>${arch.registrosProcesados}</td>
													<td>${arch.registrosConciliados}</td>
													<td>${arch.registrosConciliacionSugerida}</td>
													<td>${arch.registrosSinConciliar}</td>
													<td>${arch.registrosAnulados}</td>
													<td title="${arch.logProcesamiento}">${arch.logProcesamientoFormateado}</td> 
													<td>
														<button type="button" id="btnDescargarArchivo" class="btn btn-xs btn-link" onclick="javascript:abrirConciliacion(${arch.id});"
															title=<spring:message code="conciliacion.botonBajarArchivo"/>>
															<i class="icon-download bigger-120"></i>
														</button>
													</td>
												</tr>
							  				 </c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<c:if test="${!sessionScope.loginUser.esPerfilConsulta}">
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 2px">
									<div align="right">
										<button type="button" class="btn btn-primary btn-small" id="btnGestionarResultadosConciliacion" name="btnGestionarResultadosConciliacion" onclick="javascript:gestionarResulConciliacion();"><i class="icon-white icon-ok"></i><spring:message code="conciliacion.gestionarResultadosConciliacion"/></button>
									</div>
								</div>
								</c:if>
							</form:form>
						</div>
					</div>
				</div>
			</div><!-- end #content -->
		</div><!-- end .wrap -->

	</div><!-- end #main -->
	
	</div><!-- end #container -->
	
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>


	<script>
		$('#dp1').datepicker();
		$('#dp2').datepicker();

		// Tratamiento de Tablas
		window.onload = function() {
			var idTabla = 'tablaConciliacionResultado';
			inicializarTablaConExportacionAPdfyXls(idTabla);
			agregarOrdenFecha(idTabla);
			$('#' + idTabla).dataTable().fnSort( [ [1,'desc'] ] );
		};
		
		function gestionarResulConciliacion() {
			$('#bloqueEspera').trigger('click');
			$('#resultado')[0].action="${pageContext.request.contextPath}/conciliacion-gestionar-resultado-conciliacion";
			$('#resultado')[0].submit();
		}

		function abrirConciliacion(idArchivo) {
			$("#idArchivoAvcSelect").val(idArchivo);
			$('#resultado')[0].action="${pageContext.request.contextPath}/conciliacion-procesar-abrir-archivo";
			$('#resultado')[0].submit();
		};
		
	</script>
    
	</body>
</html>