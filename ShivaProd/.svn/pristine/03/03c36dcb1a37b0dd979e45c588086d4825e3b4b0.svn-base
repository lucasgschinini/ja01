<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><spring:message code="valor.cambiarEstado.titulo"/></title>
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
						<div class="title"><spring:message code="valor.cambiarEstado.titulo.pagina"/></div>
						<div class="pagos_anticipos">
							<p><strong><spring:message code="valor.listaValores"/></strong></p>
							
							<form:form id="formCambioEstado" commandName="boletaConValorFiltro">
								<form:hidden path="valoresSelected" id="valoresSelected"/>
								<form:hidden path="observacionesAEnviar" id="observacionesAEnviar"/>
								<form:hidden path="estadoAEnviar" id="estadoAEnviar"/>
								<form:hidden path="motivoAEnviar" id="motivoAEnviar"/>
								<form:hidden path="listaCamposAValidar" id="listaCamposAValidar"/>
								
								<div class="row">
									<div class="span9">
										<table id="resultadoBusqueda" class="tablaResultado">
											<thead>
												<tr>
													<th><spring:message code="valor.empresa"/></th>
													<th><spring:message code="valor.segmento"/></th>
													<th><spring:message code="valor.codCliente"/></th>
													<th><spring:message code="valor.razonSocialAgrupador"/></th>
													<th><spring:message code="valor.tipoValor"/></th>
													<th><spring:message code="valor.estadoValor"/></th>
													<th><spring:message code="valor.origen"/></th>
													<th><spring:message code="valor.nroAcuerdo"/></th>
													<th><spring:message code="valor.nroValor"/></th>												
													<th><spring:message code="valor.bdImpresa"/></th>
													<th><spring:message code="valor.bdEnviadaMail"/></th>
													<th><spring:message code="valor.importe"/></th>																									
													<th class="dateTimeSeconds"><spring:message code="valor.fechaAlta"/></th>
													<th><spring:message code="valor.analista"/></th>
													<th><spring:message code="valor.bancoOrigen"/></th>
													<th><spring:message code="valor.nroRecibo"/></th>
													<th><spring:message code="valor.nroConstanciaRecepcion"/></th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaValoresDto}" var="val" varStatus="i" begin="0">
													<tr>
														<td>${val.empresa}</td>
														<td>${val.segmento}</td>
														<td>${val.codCliente}</td>
														<td>${val.razonSocialClienteAgrupador}</td>
														<td>${val.tipoValor}</td>
														<td>${val.estadoValor}</td>
														<td>${val.origen}</td>
														<td>${val.idAcuerdo}</td>
														<td>${val.numeroValor}</td>
														<c:choose>
															<c:when test="${fn:trim(val.boletaImpresaEstado) ne ''}">
															<td>${val.boletaImpresaEstado}</td>
															</c:when>
															<c:otherwise><td>-</td></c:otherwise>
														</c:choose>
														<c:choose>
															<c:when test="${fn:trim(val.boletaEnviadaMailEstado) ne ''}">
															<td>${val.boletaEnviadaMailEstado}</td>
															</c:when>
															<c:otherwise><td>-</td></c:otherwise>
														</c:choose>
														<td>${val.importe}</td>																									
														<td>${val.fechaAltaValor}</td>
														<td class="registroTabla iluminado" style="text-align: left;">
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
														<td>${val.bancoOrigen}</td>
														<td>${val.reciboPreImpreso}</td>
														<td>${val.nroConstancia}</td>
														<td>
															<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																<button id="btnEliminar" class="btn btn-xs btn-link deleteBotton" title="Eliminar" value="${val.idValor}">
																	<i class="icon-remove bigger-120"></i>
																</button>
															</div>
															<input type="hidden" class ="paraEliminar" value="${val.idValor}" />
															<input type="hidden" id="hddTimeStamp_${val.idValor}" name="hddTimeStamp_${val.idValor}" value="${val.timeStamp}" />
															
														</td>
													</tr>
												</c:forEach>										
											</tbody>	
										</table>
																			
									</div>
								</div>
								<br>
								<div class="row">
									<!-- ESTADO -->									
									<div class="span3">
										<label class="control-label" for="estado"><span class="rojo">* </span><spring:message code="valor.estado"/></label>
										<div class="controls">
											<select id ="selectEstado" name="selectEstado" class="input">
										  	<c:if test="${comboEstado}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if> 															
												<c:forEach items="${estados}" var="seg">
														<option value="${seg}" >${seg}</option>										
												</c:forEach> 
											</select>
										</div>
										<span id="mensajeObligatorioEstadoCombo" class="error" style="display: none;"></span>
									</div>
									<!-- MOTIVO -->										
									<div id="bloqueMotivo" class="span3" style="display: none;">
										<label class="control-label" for="motivoSuspension"><span class="rojo">* </span><spring:message code="valor.motivoSuspencion"/></label>
										<div class="controls">
											<select id ="selectMotivoSuspension" name="motivoSuspension" class="input">
												<c:if test="${comboMotivoSuspension}"><option value=""><spring:message code="combo.seleccionar"/></option></c:if>												
												<c:forEach items="${motivosSuspension}" var="seg1">
														<option value="${seg1.idMotivoSuspension}" >${seg1.descripcion}</option>										
												</c:forEach> 
											</select>
										</div>
										<span id="mensajeObligatorio" class="error" style="display: none;"></span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="span9">
										<label class="control-label" for="observaciones"><span id="asteriscoObserva" style="display:none"class="rojo">* </span><spring:message code="valor.observaciones"/></label>
										<textarea class="field span9" id="observaciones" name="observaciones" maxlength="250">${boletaConValorFiltro.observacionesAEnviar}</textarea>
										<span id="mensajeObligatorioObservacion" class="error" style="display: none;"></span>
										<div class="row rowError" style="margin-left:0px;">
											<form:errors path="observacionesAEnviar" cssClass="error" />
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="span9" align="right">	
				                	 	<a href="#"class="btn btn-primary btn-small" id="btnAceptar"  onclick="javascript:volverValorBusqueda();"><i class="icon-white icon-ok"></i><spring:message code="valor.botonAceptar"/></a>
									 	<a href="#"class="btn btn-primary btn-small" id="btnCancelar"  onclick="javascript:volverValorBusquedaCancelar();"><i class="icon-white icon-remove"></i><spring:message code="valor.botonCancelar"/></a>
									</div>
									<input type="hidden"  id="idParaModificar" value="<c:out value='${idAmodificarEnvios}'></c:out>"/>
								</div>
								<br>
								<div id="errorListaVacia" class="alert alert-error" align="center" style="width:279px; margin-left:auto; margin-right:auto; text-align:left;hight:48px;display:none">
									<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br/><spring:message code="error.boletas.vacias"/>
								</div>
								<input id="listaVacia" type="hidden" value="${listaValoresDto.size()}" />
								
								<c:if test="${errorSaldoReversado}">
									<div class="alert alert-error" align="center" style="width:550px; margin-left:auto; margin-right:auto; text-align:left;hight:48px;">
										<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br/>${errorMensaje}
									</div>
								</c:if>
							</form:form>
						</div>
					</div>
				</div>	
			</div><!-- end #content -->
		</div><!-- end .wrap -->
	</div><!-- end #main -->
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>
</div><!-- end #container -->

<script>
var global="";
var estadoSeleccionado;
	//Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
	// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
	// En IE8 no hace falta llamar a fnCellRende
	var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
		return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [17], [13]);
	};
	
	window.onload = function() {
		estadoSeleccionado =$("#selectEstado").val();
		if(estadoSeleccionado=='Suspendido'){			
			$('#bloqueMotivo').css('display', 'inline-block');
		}
		
		if(estadoSeleccionado=='Suspendido'||estadoSeleccionado=='Anulado'){
			$('#asteriscoObserva').css('display', 'inline-block');
		}else{
			$('#asteriscoObserva').css('display', 'none');
		}
		
		var idTabla = 'resultadoBusqueda';
		inicializarTablaConExportacionAPdfyXls(idTabla, (window.DOMParser) ? fnCellRender : null);
		agregarOrdenFecha(idTabla);
		$('#' + idTabla).dataTable().fnSort( [ [12,'desc'] ] );
	};
	
	function volverValorBusqueda() {
		global="";
		var evaluacionObservacion=false;
		var evaluacionMotio=false;
		$("#mensajeObligatorioObservacion").css('display', 'none');
		$("#mensajeObligatorio").css('display', 'none');
		$("#mensajeObligatorioEstadoCombo").css('display', 'none');
		
		var estado=$('#selectEstado').val();
		var motivoSusp=$('#selectMotivoSuspension').val();
		var observaciones=$('#observaciones').val();
		
		var coma ="";
		var i=0;
		$('#resultadoBusqueda').each(function () {		
		    
			$(this).find('.paraEliminar').each(function () {
		    	var id = $(this).val();
	        	var timeStamp = $('#hddTimeStamp_'+id).val();
	        	var valor = id+"_"+timeStamp;
	        	
	        	coma=(i==0)?"":",";
	        	global+=coma+valor;
	        	i++;
	        });
	    });
		
		if(i==0){		
			var listaSize =document.getElementById("errorListaVacia");
			listaSize.style.display = 'block';
			return false;
		}
		
		if(estado==''){
			$("#mensajeObligatorioEstadoCombo").css('display', 'inline-block');
			$("#mensajeObligatorioEstadoCombo").html('<spring:message code="error.obligatorio"/>');	
			return false;
			
		}
		
		if(estadoSeleccionado=='Suspendido'||estadoSeleccionado=='Anulado'){
			if(observaciones==''){
				evaluacionObservacion=true;
			}
		}
		
		if(estadoSeleccionado=='Suspendido'&&motivoSusp==''){
			evaluacionMotio=true;
		}
		
		if(evaluacionObservacion&&evaluacionMotio){
			$("#mensajeObligatorioObservacion").css('display', 'inline-block');
			$("#mensajeObligatorioObservacion").html('<spring:message code="error.obligatorio"/>');	
			$("#mensajeObligatorio").css('display', 'inline-block');
			$("#mensajeObligatorio").html('<spring:message code="error.obligatorio"/>');
			return false;
		}
		
		if(evaluacionObservacion){
			$("#mensajeObligatorioObservacion").css('display', 'inline-block');
			$("#mensajeObligatorioObservacion").html('<spring:message code="error.obligatorio"/>');	
			return false;
		}
		if(evaluacionMotio){
			$("#mensajeObligatorio").css('display', 'inline-block');
			$("#mensajeObligatorio").html('<spring:message code="error.obligatorio"/>');	
			return false;
		}
		
		var idParaModificarse=$('#idParaModificar').val();
		var estadoModificion=$("#selectEstado").val();
		var motivoSus=$("#selectMotivoSuspension").val();
		
		
		var auxiliar="";
		if(global!=""){
			auxiliar=global;
		}else{
			auxiliar=idParaModificarse;
		}
		
		
		$('#bloqueEspera').trigger('click');
		$("#valoresSelected").val(auxiliar);
		$("#estadoAEnviar").val(estadoModificion);
		$("#motivoAEnviar").val(motivoSus);
		$("#listaCamposAValidar").val(["1"]);
		$("#observacionesAEnviar").val(observaciones);
		
		$('#formCambioEstado')[0].action="${pageContext.request.contextPath}/valores-cambio-estado-update";
		$('#formCambioEstado')[0].submit();
	};
	
	function volverValorBusquedaCancelar(){
		$('#bloqueEspera').trigger('click');
		$("#btnCancelar").attr("href", "${pageContext.request.contextPath}/vuelta-busqueda-cambioestado");		
	}

	function mostrarCamposMotivo() {
		bloqueMotivo.style.display = 'none';
		if (estadoValor.value == "3") {
			bloqueMotivo.style.display = 'block';
		}
	};
	
	$("#selectEstado").change(function () {
		estadoSeleccionado=$(this).val();
		if(estadoSeleccionado=='Suspendido'||estadoSeleccionado=='Anulado'){
			$('#asteriscoObserva').css('display', 'inline-block');
		}else{
			$('#asteriscoObserva').css('display', 'none');
		}
	});

	function ocultarBloqueEspera(id){
		if (id!=null) {
			$('#divLoader'+id).hide();
		} else {
			$('#divLoader').hide();
		}
	}

	$(".deleteBotton").click(function () {
		var valorEliminar=$(this).val();	
		$('#resultadoBusqueda').each(function () {				
	        $(this).find('.paraEliminar').each(function () {
	        	if(valorEliminar==$(this).val()){
	        		 var td = $(this).parent();
	        	      var tr = td.parent();
	        	      var idFicticio = '#row' + $(this).val() + 'tr';
	        	      tr.attr('id', idFicticio);
	        	      var miTablaCompensaciones = $('#resultadoBusqueda').dataTable();
	      			  miTablaCompensaciones.fnDeleteRow(miTablaCompensaciones.fnGetPosition(document.getElementById(idFicticio)));
	        	      //tr.remove();
	        	}
	        });
	    });
		actualizarAltoTabla('resultadoBusqueda');
	});
</script>

</body>
</html>