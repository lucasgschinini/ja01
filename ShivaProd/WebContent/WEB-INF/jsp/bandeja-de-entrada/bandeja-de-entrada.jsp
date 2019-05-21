<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum" %>
<%@ page import="ar.com.telecom.shiva.base.constantes.Constantes" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title><spring:message code="bandeja.titulo"/></title>
<jsp:include page="../template/referencias-de-importacion.jsp"></jsp:include>

<script>
	function buscarTarea(idItem, idOperacion, tipoTarea, sociedad, sistema, usuarioAsignacion, perfilAsignacion, idTarea, empresa) {
		
		if (tipoTarea == '<%=TipoTareaEnum.ASIG_GESTOR_TAL.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idTalonario').val(idItem);
			$('#timeStampAux').val($('#timeStampAux').val());
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/talonario-modificacion";
			$('#formTalonarioTabla')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.AUTR_REND_TAL.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idTalonario').val(idItem);
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/talonario-autorizacion-rendicion";
			$('#formTalonarioTabla')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.AUTR_VALOR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idValorBandeja').val(idItem);
			$('#formValores')[0].action="${pageContext.request.contextPath}/valores-autorizacion";
			$('#formValores')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.CONF_ALTA_V_AVC.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idRegistroBandeja').val(idItem);
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-alta-valor";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_ALTA_V_AVC.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idRegistroAvcSelected').val(idItem);
			$('#pantallaRegreso').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-consultar-registros-avc-sin-conciliar-detalle";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.CONF_ALTA_V_REV.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idValorPorReversion').val(idItem);
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-confirmar-alta-valor";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.CONF_ANUL_R_AVC.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idRegistroBandeja').val(idItem);
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-anular-registro-avc";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.CONF_AVISO_PAGO.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idValorBandeja').val(idItem);
			$('#formValores')[0].action="${pageContext.request.contextPath}/ver-confirmacion-aviso-pago";
			$('#formValores')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_AVISO_PAGO.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idValor').val(idItem);
			$('#formValores')[0].action="${pageContext.request.contextPath}/procesar-valores-modificacion";
			$('#formValores')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_VALOR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idValor').val(idItem);
			$('#formValores')[0].action="${pageContext.request.contextPath}/procesar-valores-modificacion";
			$('#formValores')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_TAL.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idTalonario').val(idItem);
			$('#pantallaDestino').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/talonario-modificacion";
			$('#formTalonarioTabla')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_REND_TAL.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idTalonario').val(idItem);
			$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/talonario-revision-rendicion";
			$('#formTalonarioTabla')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_ALTA_V_REV.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#valorPorReversion').val(idItem);
			$('#pantallaRegreso').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-consultar-registros-avc-sin-conciliar-detalle";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_ANUL_R_AVC.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-gestionar-resultado-conciliacion";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_AUTR_COB.name()%>') {
		    if (usuarioAsignacion == "-" && perfilAsignacion!="-") {
				$('#bloqueEspera').trigger('click');
				$('#idCobro').val(idItem);
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-detalle-aprobacion";
				$('#formRegistroAvc')[0].submit();
				}
		}
		
		if (tipoTarea == '<%=TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP.name()%>') {
		    if (usuarioAsignacion == "-" && perfilAsignacion!="-") {
				$('#bloqueEspera').trigger('click');
				$('#idCobro').val(idItem);
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-detalle-aprobacion";
				$('#formRegistroAvc')[0].submit();
				}
		}
		if (tipoTarea == '<%=TipoTareaEnum.APROBAR_OP_MAS.name()%>') {
		    if (usuarioAsignacion == "-" && perfilAsignacion!="-") {
				$('#bloqueEspera').trigger('click');
				$('#idOperacionMasiva').val(idItem);
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/operacion-masiva-detalle-aprobacion";
				$('#formRegistroAvc')[0].submit();
				}
		}
		if (tipoTarea == '<%=TipoTareaEnum.REV_OP_MAS_RECH.name()%>') {
				$('#bloqueEspera').trigger('click');
				$('#idOperacionMasiva').val(idItem);
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/operacion-masiva-edicion";
				$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_CON.name()%>') {
				$('#bloqueEspera').trigger('click');
				$('#idCobro').val(idItem);
				$('#opcion').val('D');
				$('#vuelvoBandeja').val('SI');
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-detalle-aprobacion";
				$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_DES.name()%>') {
				$('#bloqueEspera').trigger('click');
				$('#idCobro').val(idItem);
				$('#idOperacionCob').val(idOperacion);
				$('#empresaTareaVerDetalle').val(empresa);
				$('#idTareaVerDetalle').val(idTarea);
				$('#formVerDetalle')[0].action = "${pageContext.request.contextPath}/cobro-busqueda";
				$('#formVerDetalle')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_REV_RECH.name()%>') {
			if (usuarioAsignacion != "-" && perfilAsignacion!="-") {
				$('#bloqueEspera').trigger('click');
				$('#idCobro').val(idItem);
				$('#vuelvoBandeja').val(true);
				$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-edicion";
				$('#formRegistroAvc')[0].submit();
			    }
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_RES_SIM_OK.name()%>'||
				tipoTarea == '<%=TipoTareaEnum.COB_RES_SIM_ERR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-edicion";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.COB_REV_DEB_IMP.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-edicion";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_RES_SIM_OK.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idDescobro').val(idItem);
			$('#volverAPantalla').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formDescobro')[0].action = "${pageContext.request.contextPath}/descobros-configuracion-edicion";
			$('#formDescobro')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_IMP_1ER_ERR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idDescobro').val(idItem);
			$('#volverAPantalla').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#formDescobro')[0].action = "${pageContext.request.contextPath}/descobros-configuracion-edicion";
			$('#formDescobro')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_IMP_ERR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idDescobro').val(idItem);
			$('#idOperacionDesc').val(idOperacion);
			$('#empresaTarea').val(empresa);
			$('#idTarea').val(idTarea);
			$('#formDescobro')[0].action = "${pageContext.request.contextPath}/descobro-busqueda";
			$('#formDescobro')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_COMP_PEND.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/descobro-reversion-configuracion";
			$('#formRegistroAvc')[0].submit();
		}
		if (
			tipoTarea == '<%=TipoTareaEnum.DES_CONFIRMA_APL_MAN.name()%>'
		) {
			$('#bloqueEspera').trigger('click');
			$('#idDescobro').val(idItem);
			$('#sociedadDescobro').val(sociedad);
			$('#sistemaDescobro').val(sistema);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formDescobro')[0].action = "${pageContext.request.contextPath}/descobros-confirmacion-aplicacion-manual";
			$('#formDescobro')[0].submit();
		}
		if (
			tipoTarea == '<%=TipoTareaEnum.COB_CONF_APLIC_MANUAL.name()%>'
			) {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#sociedad').val(sociedad);
			$('#sistema').val(sistema);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-confirmar-aplicacion-manual";
			$('#formRegistroAvc')[0].submit();
		}
		if (
			tipoTarea == '<%=TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.name()%>'
		) {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#sociedad').val(sociedad);
			$('#sistema').val(sistema);
			$('#tipoTarea').val('<%=TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.name()%>');
			$('#idTarea').val(idTarea);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-informar-desapropiacion-aplicacion-manual";
			$('#formRegistroAvc')[0].submit();
		}
	
		if (
			tipoTarea == '<%=TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_APR.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_ERR.name()%>'
		) {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#idTarea').val(idTarea);
			$('#vuelvoBandeja').val('regresar-bandeja-de-entrada');
			$('#idCobro').val();
			$('#vengoReedicion').val(true);
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-edicion";
			$('#formRegistroAvc')[0].submit();
		}	
	}
	function tomarTarea (tipoTarea, idWorkflow,sociedad,sistema) {
		$('#bloqueEspera').trigger('click');
		$('#tipoTareaformTarea').val(tipoTarea);
		$('#idWorkflow').val(idWorkflow);
		$('#sociedadTarea').val(sociedad);
		$('#sistemaTarea').val(sistema);
		$('#formTarea')[0].action = "${pageContext.request.contextPath}/tomar-tarea-bandeja-de-entrada";
		$('#formTarea')[0].submit();
	}
	function liberarTarea (tipoTarea, idWorkflow) {
		$('#bloqueEspera').trigger('click');
		$('#tipoTarea').val(tipoTarea);
		$('#idWorkflow').val(idWorkflow);
		$('#sociedad').val(sociedad);
		$('#sistema').val(sistema);
		$('#formTarea')[0].action = "${pageContext.request.contextPath}/liberar-tarea-bandeja-de-entrada";
		$('#formTarea')[0].submit();
	}
    function eliminarTarea(idTarea, idItem, tipoTarea) {
	var mensaje;
	var noAjax = true;

	if (tipoTarea == '<%=TipoTareaEnum.REV_AVISO_PAGO.name()%>') {
			mensaje = '<spring:message code="eliminarTarea.lightbox.avisoPago.mensaje"/>';
		} else if (tipoTarea == '<%=TipoTareaEnum.REV_VALOR.name()%>') {
			mensaje = '<spring:message code="eliminarTarea.lightbox.boleta.mensaje"/>';
		} else if (tipoTarea == '<%=TipoTareaEnum.DES_RES_SIM_OK.name()%>') {
			mensaje = '<spring:message code="eliminarTarea.lightbox.simulacion.mensaje"/>';
		} else if (tipoTarea == '<%=TipoTareaEnum.COB_REV_RECH.name()%>'
			|| tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_APR.name()%>') {
			mensaje = '<spring:message code="eliminarTarea.lightbox.cobros"/>';
		} else if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_ERR.name()%>'
				|| tipoTarea == '<%=TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.name()%>') {
			//
			// Llamada a ajax para ver si hay tareas
			noAjax = false;
			var tarea = {
				idCobro : idItem,
				idTarea : idTarea,
				usuarioAsignacion : ''
			}
			$.ajax({
				"type" : "POST",
				"url" : "verificarTareasParaAnularCobro",
				"dataType" : "json",
				"data" : JSON.stringify(eval(tarea)),
				"contentType" : "application/json",
				"mimeType" : "application/json",
				"success" : function(result) {
					if (result.success) {
						var mensaje23 = result.informacionMensaje;
					
						bootbox.confirm(mensaje23, function(result) {
							if (result) {
								$('#msgAux').val(mensaje23);
								if (tipoTarea == '<%=TipoTareaEnum.COB_REV_RECH.name()%>') {
									
									
								    $('#bloqueEspera').trigger('click');
									$('#idCobro').val(idItem);
									$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-eliminar-tarea";
									$('#formRegistroAvc')[0].submit();
								} else {
								    $('#bloqueEspera').trigger('click');
									$('#idCobro').val(idItem);
									$('#idTarea').val(idTarea);
									
									$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/tarea-eliminar";
									$('#formRegistroAvc')[0].submit();
								};
							} 
						});
						
						
						
					}
				}
			});
			
		} else {
			mensaje = '<spring:message code="eliminarTarea.lightbox.mensaje"/>';
		}
		if (noAjax) {
			bootbox.confirm(mensaje, function(result) {
				if (result) {
					if (tipoTarea == '<%=TipoTareaEnum.REV_ALTA_V_AVC.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idRegistroAvcSelected').val(idItem);
						$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-eliminar-tarea-aceptacion-alta-valor";
						$('#formRegistroAvc')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.OP_MAS_ERROR.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idOperacionTarea').val(idItem);
						$('#idTarea').val(idTarea);
						$('#formVerDetalle')[0].action = "${pageContext.request.contextPath}/operacion-masiva-anular";
						$('#formVerDetalle')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.OP_MAS_PARCIAL.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idOperacionTarea').val(idItem);
						$('#idTarea').val(idTarea);
						$('#formVerDetalle')[0].action = "${pageContext.request.contextPath}/operacion-masiva-anular";
						$('#formVerDetalle')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.REV_ALTA_V_REV.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#valorPorReversion').val(idItem);
						$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-eliminar-tarea-aceptacion-alta-valor";
						$('#formRegistroAvc')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.REV_AVISO_PAGO.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idValor').val(idItem);
						$('#formValores')[0].action="${pageContext.request.contextPath}/procesar-eliminar-tarea-anular-aviso-de-pago";
						$('#formValores')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.REV_VALOR.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idValor').val(idItem);
						$('#formValores')[0].action="${pageContext.request.contextPath}/procesar-eliminar-tarea-anular-boleta";
						$('#formValores')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.REV_TAL.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idTalonario').val(idItem);
						$('#formTalonarioTabla')[0].action="${pageContext.request.contextPath}/eliminar-tarea-revision-talonario";
						$('#formTalonarioTabla')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.REV_ANUL_R_AVC.name()%>') {
						$('#bloqueEspera').trigger('click');
						$('#idRegistroSelected').val(idItem);
						$('#formRegistroAvc')[0].action="${pageContext.request.contextPath}/conciliacion-eliminar-tarea-revision-anulacion-registro-avc";
						$('#formRegistroAvc')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.COB_REV_RECH.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idCobro').val(idItem);
						$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobros-configuracion-eliminar-tarea";
						$('#formRegistroAvc')[0].submit();
					} else if (tipoTarea == '<%=TipoTareaEnum.REV_OP_MAS_RECH.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idOperacionMasiva').val(idItem);
						$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/operacion-masiva-eliminar-tarea";
						$('#formRegistroAvc')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_DES.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idCobro').val(idItem);
						$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/eliminar-tarea-error-desapropiacion";
						$('#formRegistroAvc')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_CON.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idCobro').val(idItem);
						$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/eliminar-tarea-error-confirmacion";
						$('#formRegistroAvc')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.DES_RES_SIM_OK.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idDescobro').val(idItem);
						$('#formDescobro')[0].action = "${pageContext.request.contextPath}/eliminar-tarea-anular-descobro";
						$('#formDescobro')[0].submit();
					}else if (tipoTarea == '<%=TipoTareaEnum.DES_IMP_1ER_ERR.name()%>') {
					    $('#bloqueEspera').trigger('click');
						$('#idDescobro').val(idItem);
						$('#formDescobro')[0].action = "${pageContext.request.contextPath}/eliminar-tarea-error-primer-descobro-anular-descobro";
						$('#formDescobro')[0].submit();
					}else{
					    $('#bloqueEspera').trigger('click');
						$('#idCobro').val(idItem);
						$('#idTarea').val(idTarea);
						$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/tarea-eliminar";
						$('#formRegistroAvc')[0].submit();
					};
				} 
			});
		}
	}
	
	function verDetalle(idItem, tipoTarea, usuarioAsignacion, perfilAsignacion, idTarea, empresa) {
		if (tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_DES.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_ERR.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_COB_APR.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#opcion').val('D');
			$('#vuelvoBandeja').val('SI');
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-detalle-aprobacion";
			$('#formRegistroAvc')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.OP_MAS_PARCIAL.name()%>' ||
			tipoTarea == '<%=TipoTareaEnum.OP_MAS_ERROR.name()%>') {
				$('#bloqueEspera').trigger('click');
				$('#idOperacionMasiva2').val(idItem);
				$('#fbd_volver').val('/regresar-bandeja-de-entrada');
				$('#empresaTarea').val(empresa);
				$('#idTarea').val(idTarea);
				$('#formVerDetalle')[0].action = "${pageContext.request.contextPath}/operacion-masiva-busqueda";
				$('#formVerDetalle')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_IMP_ERR.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idDescobro').val(idItem);
			$('#volverAPantalla').val('<%=Constantes.DESTINO_BANDEJA_ENTRADA%>');
			$('#idTarea').val(idTarea);
			$('#formDescobro')[0].action = "${pageContext.request.contextPath}/descobros-reversion-detalle";
			$('#formDescobro')[0].submit();
		}
		if (tipoTarea == '<%=TipoTareaEnum.DES_COMP_PEND.name()%>') {
			$('#bloqueEspera').trigger('click');
			$('#idCobro').val(idItem);
			$('#formRegistroAvc')[0].action = "${pageContext.request.contextPath}/cobro-detalle-aprobacion";
			$('#formRegistroAvc')[0].submit();
		}
	}
	// Cuando se utiliza JSTL Core o taglib o scriptlets para llenar las tablas de dataTables.net
	// No se exportan en forma correncta en Chrome. hay que renderizarlo a mano
	// En IE8 no hace falta llamar a fnCellRende
	var fnCellRender = function(sValue, iColumn, nTr, iDataIndex) {
		return fnCellRenderGenerico(sValue, iColumn, nTr, iDataIndex, [0,22], [8]);
	};
	// Tratamiento de tablas
	window.onload = function() {
		
		$('#bloqueEspera').trigger('click');
		
		var idTabla = 'resultadoBusquedaNotificacion';

		$('#' + idTabla).DataTable( {
				dom: 'Bfrtip',
				"scrollX": true,
				"scrollY": "500px",
				"scrollCollapse": true,
					buttons: [  {
	     			     extend:'pdfHtml5',
	     			     text:"PDF",
	     			     className: 'pdfbtn'
	        	          
	      	    },{
	          
	      			     extend:'excelHtml5',
	       			     text:'Excel',
	       			     title:'Bandeja_de_Notificaciones',
	                     className: 'excelbtn',
	                     "mColumns": "visible",
	                     "fnCellRender" : (window.DOMParser) ? fnCellRender : ''
	          }],
			   	    "columnDefs": [ {
	   					 "targets": [ 0 , 1 , 2, 3, 4, 26, 27, 28, 29, 30],
	  					 "searchable": false,
	  					 "orderable": false
	  				},{
						 "targets": 5,
		    			 "searchable": false,
						 "visible": false
 		 			}]
    		});

			agregarOrdenFecha('resultadoBusquedaNotificacion');
			
			$('#' + idTabla).dataTable().fnSort([ [ 5, 'asc' ], [ 12, 'asc' ] ]);
		
			
			ocultarBloqueEspera();
			
			$('#btnBuscar').click(function() {
				$('#bloqueEspera').trigger('click');
				$('#formBandeja')[0].action = "${pageContext.request.contextPath}/filtrar-bandeja-de-entrada";
				$('#formBandeja')[0].submit();
			});
			
		};
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
							<div class="title"><spring:message code="bandeja.titulo.pagina"/></div>
							<div class="pagos_anticipos">
								<p>
									<strong><spring:message code="bandeja.tareasAsignadas"/></strong>
								</p>
								
								<form:form id="formBandeja">

									<div style="padding: 0 15px;">
										<select id="filtroBandeja" name="filtroBandeja" class="input">
											<c:forEach items="${filtros}" var="filtro">
												<c:choose>
													<c:when test="${filtro.idFiltro eq idFiltro}">
														<option value="${filtro.idFiltro}" selected>${filtro.descripcion}</option>
													</c:when>
													<c:otherwise>
														<option value="${filtro.idFiltro}">${filtro.descripcion}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									
									<div style="margin-bottom: 30px; padding: 0 15px;" align="right">
										<button class="btn btn-primary btn-small" id="btnBuscar" name="btnBuscar" title=<spring:message code="conciliacion.buscar"/> type="button">
											<i class="icon-white icon-search"></i><spring:message code="valor.botonBuscar" />
										</button>
									</div>

									<div class="row">
										<div class="span9">
											<table id="resultadoBusquedaNotificacion" class="tablaResultado">
												<thead>
													<tr>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.tipoTarea"/></th>
														<th><spring:message code="bandeja.estado"/></th>
														<th><spring:message code="bandeja.nroCliente"/></th>
														<th><spring:message code="bandeja.razonSocial"/></th>
														<th><spring:message code="bandeja.importe"/></th>
														<th><spring:message code="bandeja.usuarioAsignado"/></th>
														<th class="dateTimeSeconds"><spring:message code="bandeja.fechaAsignacion"/></th>
														<th><spring:message code="bandeja.usuarioCreacion"/></th>
														<th><spring:message code="bandeja.gestiona.sobre"/></th>
														<th><spring:message code="bandeja.nroBoleta"/></th>
														<th><spring:message code="bandeja.nroReferencia"/></th> 
														<th class="date"><spring:message code="bandeja.vencimiento"/></th>
														<th><spring:message code="bandeja.banco"/></th>
														<th><spring:message code="bandeja.tipoRetencion"/></th>
														<th><spring:message code="bandeja.cuit"/></th>
														<th><spring:message code="bandeja.provincia"/></th>
														<th><spring:message code="bandeja.codOrganismo"/></th>
														<th><spring:message code="bandeja.empresa"/></th>
														<th><spring:message code="bandeja.segmento"/></th>
														<th><spring:message code="bandeja.rango"/></th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														<th><spring:message code="bandeja.espacio"/> </th>
														
													</tr>
												</thead>

												<tbody>
												
													<c:forEach items="${listaTareas}" var="tarea">
														<tr>
															<td>
															
															<c:if test="${((sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																			|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																			|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																			|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName==tarea.usuarioAsignacion)
																			|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																			|| (sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores))
																			&& tarea.sePuedeVerDetalle}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="${tarea.titleTareaVerDetalle}" type="button" onclick="javascript:verDetalle('${tarea.idItem}','${tarea.nameTarea}','${tarea.usuarioAsignacion}','${tarea.perfilAsignacion}');">
																		<i class="icon-eye-open bigger-120"></i>
																	</button>
															</c:if>
																
															</td>
																
															<td>
															<c:if test="${((sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores) 
																	|| (sessionScope.loginUser.esSupervisorAdminValor && tarea.esTareaDeSupervisorAdminValor)
																	|| (sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																	|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																	|| (sessionScope.loginUser.esSupervisorTalonario && tarea.esTareaDeSupervisorTalonarios)
																	|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																	|| (sessionScope.loginUser.esReferenteCobranza && tarea.esReferenteCobranza)
																	|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName == tarea.usuarioAsignacion)
																	|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																	|| (sessionScope.loginUser.esSupervisorOperacionesEspeciales && tarea.esSupervisorOperacionesEspeciales)
																	|| (sessionScope.loginUser.esSupervisor && tarea.esTareaDeSupervisor)
																	|| (sessionScope.loginUser.esReferenteCaja && (tarea.esTareaDescobroConfirmarAplicacionManual || tarea.esTareaCobroConfirmarAplicacionManual || tarea.esTareaDesapropiacionAplicacionManual))
																	|| (sessionScope.loginUser.esReferenteOperacionesExternas && (tarea.esTareaDescobroConfirmarAplicacionManual || tarea.esTareaCobroConfirmarAplicacionManual || tarea.esTareaDesapropiacionAplicacionManual)))
																	&& !(tarea.tipoTarea eq 'OP_MAS_ERROR'|| tarea.tipoTarea eq 'OP_MAS_PARCIAL')}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="${tarea.titleTarea}" type="button"
																		onclick="javascript:buscarTarea('${tarea.idItem}','${tarea.idOperacion}','${tarea.nameTarea}','${tarea.sociedadPantalla}','${tarea.sistemaPantalla}','${tarea.usuarioAsignacion}','${tarea.perfilAsignacion}', '${tarea.id}', '${tarea.idEmpresa}');">
																		<i class="icon-share bigger-120"></i>
																	</button>
															</c:if>
															</td>
															<td>
																<c:if test="${((sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																				|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																				|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																				|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName==tarea.usuarioAsignacion)
																				|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																				|| (sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores))
																				&& tarea.sePuedeEliminar
																				&& not tarea.esTareaErrorConfirmacionCobro}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="eliminarTarea.mensaje"/>" type="button" onclick="javascript:eliminarTarea('${tarea.id}','${tarea.idItem}','${tarea.nameTarea}');">
																		<i class="icon-remove bigger-120"></i>
																	</button>
																</c:if>
																
								
															</td>
															<td>
																<c:if test="${tarea.permitirTomarTarea}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="bandeja.tomarTarea"/>" type="button" onclick="javascript:tomarTarea('${tarea.tipoTarea}','${tarea.idWorkflow}','${tarea.sociedad}','${tarea.sistema}');">
																		<i class="icon-plus-sign bigger-120"></i>
																	</button>
																</c:if>
																
															</td>
															<td>
																<c:if test="${tarea.permitirLiberarTarea}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="bandeja.liberarTarea"/>" type="button" onclick="javascript:liberarTarea('${tarea.tipoTarea}','${tarea.idWorkflow}','${tarea.sociedad}','${tarea.sistema}');">
																	 	<i class="icon-minus-sign bigger-120"></i>
																	</button>
																</c:if>
																
															</td>	
															<td>${tarea.delegado}</td>
															<td>${tarea.tipoTareaDescripcion}</td>
															<td>${tarea.estadoPendienteDescripcion}</td>
															<td>${tarea.nroCliente}</td>
															<td>${tarea.razonSocial}</td>
															<td>${tarea.importe}</td>
															<td style="text-align: left">
																<div style="width: 280px">
																	<c:choose>
																		<c:when test="${fn:trim(tarea.nombreUsuarioAsignado) ne '-'}">
																			<div style="width: 280px">
																				<img alt="Usuario" src="${tarea.urlFotoUsuario(tarea.idUsuarioAsignado)}"
																				style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																				onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																				${tarea.nombreUsuarioAsignado}<br>
																				<a href="im:${tarea.chatUsuarioAsignado}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																				<a href="mailto:${tarea.mailUsuarioAsignado}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																				
																			</div>
																		</c:when>
																		<c:otherwise><div style="width: 150px; text-align: center;">${tarea.nombreUsuarioAsignado} <br></div></c:otherwise>
																	</c:choose>
																</div>
 												 			</td>
															<td>${tarea.fechaCreacionFormateado}</td>
															<td style="text-align: left">
																<div style="width: 280px">
																	<c:choose>
																		<c:when test="${fn:trim(tarea.nombreUsuarioCreacion) ne '-'}">
																			<div style="width: 280px">
																				<img alt="Usuario" src="${tarea.urlFotoUsuario(tarea.idUsuarioCreacion)}"
																				style="cursor: hand; border: none; width: 36px; height: 39px; margin-right: 5px; margin-top: 3px; margin-left: 5px; float: left; margin-bottom: 3px;" 
																				onerror='src="${pageContext.request.contextPath}/img/default2.png"'>
																				${tarea.nombreUsuarioCreacion}<br>
																				<a href="im:${tarea.chatUsuarioCreacion}"  title="Chat"><i class="icon-comment" style=" margin-top: 3px"></i></a>
																				<a href="mailto:${tarea.mailUsuarioCreacion}"  title="Email"><i class="icon-envelope" style=" margin-left: 3px; margin-top: 2px"></i></a>
																				
																			</div>
																		</c:when>
																		<c:otherwise><div style="width: 150px; text-align: center;">${tarea.nombreUsuarioC} <br></div></c:otherwise>
																	</c:choose>
																</div>
 												 			</td>
															<td>${tarea.gestionaSobreFormateado}</td>
<%-- 															<td style="text-align: left">${tarea.informacionAdicionalFormateado}</td> --%>
														
															<td>${tarea.nroBoleta}</td>
															
															<td>${tarea.refBandeja}</td>
<%-- 															<td>${tarea.nroCheque}</td> --%>
<%-- 															<td>${tarea.codInterdeposito}</td> --%>
<%-- 															<td>${tarea.referencia}</td> --%>
<%-- 															<td>${tarea.nroConstancia}</td> --%>
															
															<td>${tarea.vencimiento}</td>
															<td>${tarea.descBanco}</td>
															<td>${tarea.tipo}</td>
															<td>${tarea.cuit}</td>
															<td>${tarea.provincia}</td>
															<td>${tarea.codOrganismo}</td>
															<td>${tarea.empresa}</td>
															<td>${tarea.segmento}</td>
															<td>${tarea.rango}</td>
															<td>
															
															<c:if test="${((sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																			|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																			|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																			|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName==tarea.usuarioAsignacion)
																			|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																			|| (sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores))
																			&& tarea.sePuedeVerDetalle}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="${tarea.titleTareaVerDetalle}" type="button" onclick="javascript:verDetalle('${tarea.idItem}','${tarea.nameTarea}','${tarea.usuarioAsignacion}','${tarea.perfilAsignacion}');">
																		<i class="icon-eye-open bigger-120"></i>
																	</button>
															</c:if>
																
															</td>
																
															<td>
															<c:if test="${((sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores)
																	|| (sessionScope.loginUser.esSupervisorAdminValor && tarea.esTareaDeSupervisorAdminValor)
																	|| (sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																	|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																	|| (sessionScope.loginUser.esSupervisorTalonario && tarea.esTareaDeSupervisorTalonarios)
																	|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																	|| (sessionScope.loginUser.esReferenteCobranza && tarea.esReferenteCobranza)
																	|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName == tarea.usuarioAsignacion)
																	|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																	|| (sessionScope.loginUser.esSupervisorOperacionesEspeciales && tarea.esSupervisorOperacionesEspeciales)
																	|| (sessionScope.loginUser.esSupervisor && tarea.esTareaDeSupervisor)
																	|| (sessionScope.loginUser.esReferenteCaja && (tarea.esTareaDescobroConfirmarAplicacionManual || tarea.esTareaCobroConfirmarAplicacionManual || tarea.esTareaDesapropiacionAplicacionManual))
																	|| (sessionScope.loginUser.esReferenteOperacionesExternas && (tarea.esTareaDescobroConfirmarAplicacionManual || tarea.esTareaCobroConfirmarAplicacionManual || tarea.esTareaDesapropiacionAplicacionManual)))
																	&& !(tarea.tipoTarea eq 'OP_MAS_ERROR'|| tarea.tipoTarea eq 'OP_MAS_PARCIAL')}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="${tarea.titleTarea}" type="button"
																		onclick="javascript:buscarTarea('${tarea.idItem}','${tarea.idOperacion}','${tarea.nameTarea}','${tarea.sociedadPantalla}','${tarea.sistemaPantalla}','${tarea.usuarioAsignacion}','${tarea.perfilAsignacion}', '${tarea.id}', '${tarea.idEmpresa}');">
																		
																		<i class="icon-share bigger-120"></i>
																	</button>
															</c:if>
															</td>
															<td>
																<c:if test="${((sessionScope.loginUser.esAnalista && tarea.esTareaDeAnalista)
																				|| (sessionScope.loginUser.esAdminTalonario && tarea.esTareaDeAdminTalonarios)
																				|| (sessionScope.loginUser.esCajeroPagador && tarea.esTareaDeAnalista)
																				|| (sessionScope.loginUser.esPerfilAnalistaCobranza && sessionScope.loginUserName==tarea.usuarioAsignacion)
																				|| (sessionScope.loginUser.esPerfilAnalistaOperacionMasiva && sessionScope.loginUserName==tarea.usuarioAsignacion)
																				|| (sessionScope.loginUser.esAdminValor && tarea.esTareaDeAdminValores))
																				&& tarea.sePuedeEliminar
																				&& not tarea.esTareaErrorConfirmacionCobro}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="eliminarTarea.mensaje"/>" type="button" onclick="javascript:eliminarTarea('${tarea.id}','${tarea.idItem}','${tarea.nameTarea}');">
																		<i class="icon-remove bigger-120"></i>
																	</button>
																</c:if>
																
																
															</td>
															<td>
																<c:if test="${tarea.permitirTomarTarea}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="bandeja.tomarTarea"/>" type="button" onclick="javascript:tomarTarea('${tarea.tipoTarea}','${tarea.idWorkflow}');">
																		<i class="icon-plus-sign bigger-120"></i>
																	</button>
																</c:if>
																
															</td>
															<td>
																<c:if test="${tarea.permitirLiberarTarea}">
																	<button class="btn btn-xs btn-link" style="padding: 5px;" title="<spring:message code="bandeja.liberarTarea"/>" type="button" onclick="javascript:liberarTarea('${tarea.tipoTarea}','${tarea.idWorkflow}');">
																	 	<i class="icon-minus-sign bigger-120"></i>
																	</button>
																</c:if>
																
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</form:form>
								
								<form:form id="formTalonarioTabla" commandName="talonarioDto">
									<input type="hidden" id="timeStampAux" name="timeStampAux" value="${timeStampAux}"/>
									<input type="hidden" id="pantallaDestino" name="pantallaDestino" value="<%=Constantes.DESTINO_BANDEJA_ENTRADA%>"/>
									<input type="hidden" id="idTalonario" name="idTalonario">
								</form:form>
								
								<form:form id="formValores">
									<input type="hidden" id="pantallaDestino" name="pantallaDestino" value="<%=Constantes.DESTINO_BANDEJA_ENTRADA%>"/>
									<input type="hidden" id="idValorBandeja" name="idValorBandeja">
									<input type="hidden" id="idValor" name="idValor">
								</form:form>
								
								<form:form id="formRegistroAvc">
									<input type="hidden" id="pantallaDestino" name="pantallaDestino"/>
									<input type="hidden" id="pantallaRegreso" name="pantallaRegreso"/>
									<input type="hidden" id="valorPorReversion" name="valorPorReversion">
									<input type="hidden" id="idValorPorReversion" name="idValorPorReversion">
									<input type="hidden" id="idRegistroBandeja" name="idRegistroBandeja">
									<input type="hidden" id="idRegistroAvcSelected" name="idRegistroAvcSelected">
									<input type="hidden" id="idRegistroSelected" name="idRegistroSelected">
									<input type="hidden" id="idValor" name="idValor">
									<input type="hidden" id="idCobro" name="idCobro">
									<input type="hidden" id="sociedad" name="sociedad">
									<input type="hidden" id="sistema" name="sistema">
									<input type="hidden" id="idTarea" name="idTarea">
									<input type="hidden" id="tipoTarea" name="tipoTarea">
									<input type="hidden" id="idOperacionMasiva" name="idOperacionMasiva">
									<input type="hidden" id="opcion" name="opcion" value="A">
									<input type="hidden" id="vuelvoBandeja" name="vuelvoBandeja">
									<input type="hidden" id="volver" name="volver" value="/regresar-bandeja-de-entrada">
									<input type="hidden" id="vengoReedicion" name="vengoReedicion">
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}">
									<input type="hidden" id="goBack" name="goBack">
									<input type="hidden" id="msgAux" name="msgAux">
								</form:form>
								
								<form:form id="formDescobro">
									<input type="hidden" id="idDescobro" name="idDescobro">
									<input type="hidden" id="sociedadDescobro" name="sociedadDescobro">
									<input type="hidden" id="sistemaDescobro" name="sistemaDescobro">
									<input type="hidden" id="idOperacionDesc" name="idOperacionDesc">
									<input type="hidden" id="volverAPantalla" name="volverAPantalla">
									<input type="hidden" id="idTarea" name="idTarea">
									<input type="hidden" id="empresaTarea" name="empresaTarea">
									<input type="hidden" id="volver" name="volver" value="/regresar-bandeja-de-entrada">
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}">
									<input type="hidden" id="goBack" name="goBack">
								</form:form>
								
								<form:form id="formVerDetalle">
									<input type="hidden" id="idCobro" name="idCobro">
									<input type="hidden" id="idOperacionCob" name="idOperacionCob">
									<input type="hidden" id="idTareaVerDetalle" name="idTareaVerDetalle">
									<input type="hidden" id="empresaTareaVerDetalle" name="empresaTareaVerDetalle">
									<input type="hidden" id="fbd_volver" name="fbd_volver">
									<input type="hidden" id="volver" name="volver" value="/regresar-bandeja-de-entrada">
									<input type="hidden" id="idVolver" name="idVolver" value="${idVolver}">
									<input type="hidden" id="goBack" name="goBack">
									<input type="hidden" id="idOperacionMasiva2" name="idOperacionMasiva2">
									<input type="hidden" id="idOperacionTarea" name="idOperacionTarea">
								</form:form>
								<form:form id="formTarea">
									<input type="hidden" id="tipoTareaformTarea" name="tipoTareaformTarea">
									<input type="hidden" id="idWorkflow" name="idWorkflow">
									<input type="hidden" id="idUsuario" name="idUsuario">
									<input type="hidden" id="sociedadTarea" name="sociedadTarea">
									<input type="hidden" id="sistemaTarea" name="sistemaTarea">
								</form:form>
								
							</div>
						</div>				
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../template/pie-de-pagina.jsp"></jsp:include>

</body>
</html>