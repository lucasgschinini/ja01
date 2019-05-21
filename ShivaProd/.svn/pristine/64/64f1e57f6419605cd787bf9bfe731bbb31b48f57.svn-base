<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="ar.com.telecom.shiva.negocio.workflow.definicion.Estado" %>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>



<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="recibo.modificacion.titulo"/></title>
   
   	<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>
</head>
    
<body style="height: 100%">

<div id="container" >

	<jsp:include page="../template/cabecera.jsp"></jsp:include>

	<div id="main">

		<div class="wrap">

			<jsp:include page="../template/menu.jsp"></jsp:include>

			<div id="content">
			
				<div id="inside">

					<div id="payments" class="box">
						<div class="title"><spring:message code="recibo.modificacion.titulo.pagina"/></div>

							<div class="pagos_anticipos">
								<p>
									<strong><spring:message code="recibo.recibo"/></strong>
								</p>

								<form:form id="formRecibo" commandName="reciboDto">
									<input type="hidden" id="nroTalonario" name="nroTalonario" value="${reciboDto.nroTalonario}">
									<input type="hidden" id="timeStamp" name="timeStamp" value="${reciboDto.timeStamp}"/>
									<input type="hidden" id="idTalonarioSelected" name="idTalonarioSelected"  value="${reciboDto.idTalonarioSelected}"/>
									<input type="hidden" id="volverPantallaRendicion" name="volverPantallaRendicion"  value="${reciboDto.volverPantallaRendicion}"/>
									<input type="hidden" id="listaCompensaciones" name="listaCompensaciones"/>
									<input type="hidden" id="id" name="id" value="${reciboDto.id}"/>
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="empresa"><spring:message code="recibo.empresa"/></label>
											<div class="controls">
												<input id="empresa" name="empresa" readonly type="text" value="${reciboDto.empresa}" class="input">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="segmento"><spring:message code="recibo.segmento"/></label>
											<div class="controls">
												<input id="segmento" name="segmento" readonly type="text" value="${reciboDto.segmento}" class="input">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="nroRecibo"><spring:message code="recibo.nroRecibo"/></label>
											<div class="controls">
												<input id="nroRecibo" name="nroRecibo" readonly type="text" value="${reciboDto.nroRecibo}" class="input">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<label class="control-label" for="usuarioGestor"><spring:message code="recibo.gestor"/></label>
											<div class="controls">
												<input id="usuarioGestor" name="usuarioGestor" readonly type="text" value="${reciboDto.usuarioGestor}" class="input">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="estado"><spring:message code="recibo.estado"/></label>
											<div class="controls">
												<input id="estado" name="estado" type="text"  readonly value="${reciboDto.estado}" class="input">
											</div>
										</div>
										<div class="span3">
											<label class="control-label" for="fechaIngreso"><span class="rojo">* </span><spring:message code="recibo.fechaIngreso"/></label>
											<div class="controls">
												<input id="fechaIngreso" name="fechaIngreso" type="text"
													data-date-format="dd/mm/yyyy" data-date-language='es' value="${reciboDto.fechaIngreso}" class="input" onchange="validarFecha();">
											</div>
										</div>
									</div>
									<div id="divErrorFecha" class="row rowError" style=" display:none;">
										<div class="span3">
										</div>
										<div class="span3">
										</div>
										<div class="span3">
										<span  id="errorFecha" class="error" style=" color: red; font-size: 9px; font-size: 100%; font: inherit; vertical-align: baseline;"></span>
										</div>
									</div>
								</form:form>
							</div>
							<br>

							<div class="pagos_anticipos">
								<p><strong><spring:message code="recibo.agregarCompensaciones"/></strong></p>
	
								<form:form id="formAgregarCompensaciones">
									
									<div class="row">
										<div class="span3">
											<label class="control-label" for="referencia"><spring:message code="recibo.referencia"/></label>
										</div>
										<div class="span3">
											<label class="control-label" for="importe"><spring:message code="recibo.importe"/></label>
										</div>
									</div>
									<div class="row">
										<div class="span3">
											<div class="controls">
												<input id="addReferencia" name="addReferencia" type="text" maxlength="20" class="input">
											</div>
										</div>
										
										<div class="span3">
											<div class="controls">
												<input id="addImporte" name="addImporte" type="text" maxlength="14" class="input">
											</div>
										</div>
										<div class="span3" align="right">
											<button class="btn btn-primary btn-small" id="agregar"  name="agregar" type="button" onclick="agregarCompensacion();"><i class="icon-white icon-ok"></i>&nbsp;<spring:message code="recibo.agregarCompensacion"/></button>
										</div>
									</div>
									<div class="row rowError" id="divError" style=" display:none;">
										<div id="divErrorReferencia" class="span3"  >
											<span  id="errorReferencia" class="error" style=" color: red; font-size: 9px; font-size: 100%; font: inherit; vertical-align: baseline;"></span>
										</div>
										<div id="divErrorImporte" class="span3"  >
											<span  id="errorImporte" class="error" style=" color: red; font-size: 9px; font-size: 100%; font: inherit; vertical-align: baseline;"></span>
										</div>
									</div>	
								</form:form> 
							</div>
							<div class="pagos_anticipos">
								<p><strong><spring:message code="recibo.valoresYCompensaciones"/></strong></p>
							<form:form id="formTabla">
								<div class="row">		
									<div class="span9">
										<table id="tablaCompensaciones" class="tablaResultado">
											<thead>
												<tr>
													<th style="padding: 3px 10px; text-align: center;"><input type="checkbox" id="seleccionarTodos"></th>
													<th><spring:message code="recibo.tipo"/></th>
													<th><spring:message code="recibo.referencia"/></th>
													<th><spring:message code="recibo.importe"/></th>
													<th><spring:message code="recibo.codCliente"/></th>
													<th><spring:message code="recibo.razonSocial"/></th>
												</tr>
											</thead>
												
											<tbody>
												<c:forEach items="${reciboDto.valores}" var="val">
													<tr>
														<td></td>
														<td>${val.tipoValor}</td>
														<td>
															<div class='contenedor-columna' style='text-align: left;'>
															  	<div style="width: 140px;">
																	<img alt="Usuario" src="${reciboDto.urlFotoUsuario(val.idAnalista)}" 
																	style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																	
																	onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																	${val.nombreAnalista} <br>
																	<a href="sip:${val.mailAnalista}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																	<a href="mailto:${val.mailAnalista}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
													 		   </div>
													 		</div>
													 		<div class='contenedor-columna' style='text-align: left;'>
													 			<p style='line-height:12pt; font-size: 11px;' >
																	<br> &nbsp; &nbsp; &nbsp; ${val.numeroValorFormateadoConRetornoCarro}
																	<br> &nbsp; &nbsp; &nbsp; Fecha Valor: ${val.fechaValorFormateado}
																	<br> &nbsp; &nbsp; &nbsp; Acuerdo: ${val.idAcuerdo}
													 		</div>
											 			</td>
 														<td>${val.importe}</td> 
 														<td>${val.codCliente}</td>
 														<td>${val.razonSocialClienteAgrupador}</td>
													</tr>
												</c:forEach>
												
												<c:forEach items="${reciboDto.compensaciones}" var="comp" varStatus="i">
													<tr>
														<td>
															<input type="checkbox" class="opcion" id="${comp.idCompensacion}"name="opcion" value="${comp.referencia}_${comp.importe}" />
														</td>
														<td><spring:message code="recibo.compensacion"/></td>
														<td><input type="hidden" name="referencia" value="${comp.referencia}"/>${comp.referencia}</td>
														<td><input type="hidden" name="importe" value="${comp.importe}"/>${comp.importeFormateado}</td>
														<td>-</td>													
														<td>-</td>													
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>	
								
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-left: 5px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="quitar" name="quitar" type="button" disabled onclick="javascript:quitarCompensaciones();"><i class="icon-white icon-remove"></i><spring:message code="recibo.quitarCompensaciones"/></button>
									</div>
								</div>
								<br></br>
								<div class="row" style="margin-top: 20px; margin-bottom: 30px; margin-right: 5px">
									<div align="right">
										<button class="btn btn-primary btn-small" id="aceptar" type="button" onclick="javascript:aceptarRecibo();"><i class="icon-white icon-ok"></i><spring:message code="recibo.botonAceptar"/></button>
										<button class="btn btn-primary btn-small" id="cancelar" type="button" onclick="javascript:cancelarRecibo();"><i class="icon-white icon-remove"></i><spring:message code="recibo.botonCancelar"/></button>
									</div>
								</div>
								<div class="alert alert-error" style="display:none;" id="divNoModificacion">
									<a class="close" data-dismiss="alert">×</a><strong><spring:message code="error.error"/></strong><br /><spring:message code="error.noHayModificacion"/>
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

	<script>
	
		$.datepicker.setDefaults($.datepicker.regional["es"]);
		$("#fechaIngreso").datepicker({firstDay: 1});
		var listaComparacion;
		var fechaOriginal;
		var contador = 0;
		function aceptarRecibo() {
			
			if ($('#fechaIngreso').val() != ""){
				$('#bloqueEspera').trigger('click');
				listaCompensaciones= "";
				$('.opcion:input').each(function(i,elem){
					listaCompensaciones = listaCompensaciones + $('.opcion:input')[i].value +"|";
				});
				$('#listaCompensaciones').val(listaCompensaciones);
				if (listaComparacion == listaCompensaciones && fechaOriginal == $('#fechaIngreso').val()){
					$('#divNoModificacion').attr('style','display:block;');
					ocultarBloqueEspera();  
				}else{
					$('#aceptar').attr('disabled', true);
					$('#formRecibo')[0].action="${pageContext.request.contextPath}/procesar-modificacion-recibo";
					$('#formRecibo')[0].submit();
				};
			}else{
				$('#divErrorFecha').attr('style','display:block;');
				$('#errorFecha').text('<spring:message code="error.obligatorio"/>');
				$('#fechaIngreso').val("");
			}
		}
		
		function validarFecha(){
			$('#bloqueEspera').trigger('click');
			var url = '${pageContext.request.contextPath}/validacionFechaIngreso?method=load&ajax=true&nocache='+Math.random();
			var param = "&fechaIngreso="+ $('#fechaIngreso').val();
			$.get(url+param, function(data) {
				var res = data;
				if (!res){
					$('#divErrorFecha').attr('style','display:block;');
					$('#errorFecha').text('<spring:message code="error.fechaIncorrecta"/>');
					$('#fechaIngreso').val("");					
				}else{
					$('#divErrorFecha').attr('style','display:none;');
				};
			});
			ocultarBloqueEspera();	
		}
		
		function cancelarRecibo() {
			$('#bloqueEspera').trigger('click');
			<c:choose>
				<c:when test="${reciboDto.volverPantallaRendicion}">
					$('#formRecibo')[0].action="${pageContext.request.contextPath}/volver-revision-rendicion";
					$('#formRecibo')[0].submit();
				</c:when>
				<c:otherwise>
					$('#formTabla')[0].action="${pageContext.request.contextPath}/volver-busqueda";
					$('#formTabla')[0].submit();
				</c:otherwise>
			</c:choose>
			
		}
		
		// add multiple select / deselect functionality
	    $("#seleccionarTodos").click(function () {
	    	$('.opcion').prop('checked', $(this).prop('checked'));
	    	habilitarQuitar();
	    });
		
	    // if all checkbox are selected, check the selectall checkbox
	    // and viceversa
	    $(".opcion").click(function(){
	    	habilitarQuitar();
	    	if($(".opcion").length == $(".opcion:checked").length) {
		           $("#seleccionarTodos").prop("checked", true);
		        } else {
		           $("#seleccionarTodos").prop('checked', false);
		        }
	    });
		
		// Tratamiento de scrolling para las tablas de la pagina.
		window.onload = function() {
			inicializarTabla('tablaCompensaciones');
			listaComparacion= "";
			$('.opcion:input').each(function(i,elem){
				listaComparacion = listaComparacion + $('.opcion:input')[i].value +"|";
			});
			fechaOriginal = $('#fechaIngreso').val();
		};

		function agregarCompensacion() {

			var formatoReferencia = new RegExp(/^[a-zA-Z0-9_\s.,;:!¡¿@#%$?+-{}()\[\]á-úÁ-Úä-üÄ-Ü]*/);													
 			var formatoImporte 	  = new RegExp(/^(([1-9]{1}[\d]{0,2}(\.[\d]{3}){1,2}|([1-9]{1}(\d{1,9})?))(\,[\d]{1,2})?$)|(0\,[\d]{1,2}){1}$/);
 			

			var ref = $('#addReferencia').val();
			var nimp = $('#addImporte').val();
			
			imp = nimp.replace(/(^[\s\xA0]+|[\s\xA0]+$)/g, '');
			
			
			$('#errorReferencia').text('');
			$('#errorImporte').text('');

			$('#divError').attr('style','display: none;');
			
			
			
			if (ref.length == 0){
 	            $('#divError').attr('style','display: block;');
 	            $('#errorReferencia').text('<spring:message code="error.obligatorio"/>');
			}else{
				if(!formatoReferencia.test(ref)){
					$('#divError').attr('style','display: block;');
	 	            $('#errorReferencia').text('<spring:message code="error.alfanumerico"/>');
				};
			};
			
			if (imp.length == 0){
				$('#divError').attr('style','display: block;');
				$('#errorImporte').text('<spring:message code="error.obligatorio"/>');
			}else{
				if(!formatoImporte.test(imp)){
					$('#divError').attr('style','display: block;');
	 	            $('#errorImporte').text('<spring:message code="error.numeroConDosDigitos"/>');
	 	        };
			};
			
			if(ref.length > 0 && imp.length > 0 && formatoReferencia.test(ref) && formatoImporte.test(imp)){     
				$('#tablaCompensaciones').dataTable().fnAddData( [
			        '<input type="checkbox" class="opcion" id="opcion_'+contador+'" onclick="habilitarQuitar()" name="opcion" value="'+$('#addReferencia').val()+"_"+$('#addImporte').val() +'" />',
					"Compensación",
					'<input type="hidden" name="referencia" value="'+ $('#addReferencia').val()+'"/>' + $('#addReferencia').val(),
					'<input type="hidden" name="importe" value="'+ $("#addImporte").val()+'"/>' + "$"+ $('#addImporte').val(),
					"-",
					"-"
				]);
					contador = contador+1;
				$("#addReferencia").val("");
				$("#addImporte").val("");
			};
			

		};
		


		function quitarCompensaciones() {
			$(".opcion:checked").each(function(i, elem) {
				// Obtengo el ID del radio/check que uso para seleccionar el registro
				var idCheck = $('.opcion:checked')[0].id;
				// Luego, le asigno un Id al TR que me contiene que el registro, de manera que luego lo pueda referenciar y eliminar
				// Busco la referencia al padre del checkbox
				var contenedorTRdeCheckBox = $('#' + idCheck).closest('tr');
				     
				// armo un id ficticio en base al checkbox seleccionado
				var idContenedorTRdeCheckBox = '#row' + idCheck + 'tr';
				// y se lo asigno al contenedor TR
				contenedorTRdeCheckBox.attr('id', idContenedorTRdeCheckBox);
				     
				// Ahora elimino el TR mencionado de la Tabla
				var miTablaCompensaciones = $('#tablaCompensaciones').dataTable();
				miTablaCompensaciones.fnDeleteRow(miTablaCompensaciones.fnGetPosition(document.getElementById(idContenedorTRdeCheckBox)));
			});
			$("#seleccionarTodos").prop('checked', false);
		};
      
		function habilitarQuitar() {
			if ($('.opcion:checked').length != 0){
					$('#quitar').attr('disabled',false);
				
			}else{
					$('#quitar').attr('disabled',true);
			}
		};
	</script>
    
	</body>
</html>


