package ar.com.telecom.shiva.negocio.workflow.definicion;

import java.util.ArrayList;
import java.util.List;

public enum Estado {
	
	INICIO("Inicio",""),

	// Wf Boleta
	BOL_PENDIENTE("Pendiente de Conciliación",""),
	BOL_ANULADO("Anulado",""),
	BOL_CONCILIADO("Conciliado",""),
	BOL_SUGERIDA("Conciliación Sugerida",""),
	BOL_DIFERENCIA("Conciliado con Diferencias",""),

	// Wf Registros AVC
	AVC_PENDIENTE("Pendiente de Conciliación",""),
	AVC_PENDIENTE_ANULACION("Pendiente Confirmación de Anulacion",""),
	AVC_ANULADO("Anulado",""),
	AVC_CONCILIADO_SIN_BOLETA("Conciliado sin Boleta",""),
	AVC_CONCILIADO("Conciliado",""),
	AVC_CONCILIACION_SUGERIDA("Conciliación Sugerida",""),
	AVC_DIFERENCIA("Conciliado con Diferencias",""),
	AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR("Pendiente de Confirmar Alta de Valor",""),
	AVC_ALTA_VALOR_RECHAZADA("Alta de Valor Rechazada",""),

	// Wf Cobros
	COB_NO_DISPONIBLE("Cobro no disponible",""),
	COB_EN_EDICION("En Edición","enEdicion"),
	COB_EN_EDICION_VERIFCANDO_DEBITOS("En Edición verificando Débitos","enEdicion"),
	COB_PEND_REFERENTE_COBRANZA("Pend. Referente Cobranza","pendienteDeAprobacion"),
	COB_PEND_SUPERVISOR_COBRANZA("Pend. Supervisor Cobranza","pendienteDeAprobacion"),
	COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES("Pend. Supervisor Operaciones Especiales","pendienteDeAprobacion"),
	COB_MEDIO_PAGO_EN_PROCESO("Medio de Pago en Proceso",""),
	COB_RECHAZADO("Cobro Rechazado","rechazado"),
	COB_PENDIENTE("Pendiente de Imputar Cobro","enProceso"),
	COB_PROCESO("Cobro en proceso","enProceso"),
	COB_ERROR_COBRO("Error en cobro","conError"),
	COB_ERROR_APROPIACION("Error en apropiación","conError"),
	COB_ERROR_DESAPROPIACION("Error en desapropiación","conError"),
	COB_COBRADO("Cobrado","cobrado"),
	COB_ERROR_CONFIRMACION("Error en confirmación","conError"),
	COB_CONFIRMADO_CON_ERROR("Confirmado con error","cobrado"),
	COB_REEDITADO("Cobro reeditado","conError"),
	COB_REEDITADO_PARCIAL("Cobro Parcialmente Reeditado","cobrado"),
	COB_ANULADO("Cobro anulado","anulado"),
	COB_PENDIENTE_MIC("Cobro con respuesta pendiente de MIC","enProceso"),
	COB_PEND_PROCESAR_MIC("Cobro pendiente de procesar MIC","enProceso"),
	COB_PENDIENTE_CONFIRMACION_MANUAL("Pendiente de Confirmación Manual","enProceso"),
	// NeedIT72562
	COB_PROCESO_APLICACION_EXTERNA("En Proceso de Aplicación Externa","enProceso"),
	COB_COBRADO_PARCIALMENTE("Cobrado Parcialmente","cobrado"),
	COB_ERROR_CONFIRMACION_PARCIAL("Error en Confirmación Parcial","conError"),
	COB_ERROR_DESAPROPIACION_PARCIAL("Error en Desapropiación Parcial","conError"),
	COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC("En Proceso de Aplicación Externa y con Respuesta Pendiente de MIC","enProceso"),
	COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA("Pendiente de Confirmación Manual y en Proceso de Aplicación Externa","enProceso"),
	
	COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC("En Proceso de Aplicación Externa y Pendiente de Procesar MIC","enProceso"),
	COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC("Pendiente de Confirmación Manual, en Proceso de Aplicación Externa y con Rta. Pendiente de MIC","enProceso"),
	COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC("Pendiente de Confirmación Manual, en Proceso de Aplicación Externa y Pendiente de Procesar MIC","enProceso"),
	COB_CONFIRMADO_PARCIALMENTE_CON_ERROR("Confirmado Parcialmente con error","cobrado"),

	COB_PEND_DESAPROPIACION_MANUAL_EXTERNA("Pendiente de Desapropiación Externa", "enProceso"),
	
	COB_PARCIALMENTE_EN_ERROR("Cobro Parcialmente en Error", "enError"),
	
	// Wf Talonarios
	TAL_ASIGNADO_SUPERVISOR("Asignado a Supervisor de Talonarios",""),
	TAL_ASIGNADO_GESTOR("Asignado",""),
	TAL_DERIVADO_ADMINISTRADOR("Derivado a Administrador de Talonarios por error en rango",""),
	TAL_ANULADO("Anulado",""),
	TAL_ASIGNADO_ADMINISTRADOR("Asignado a Administrador de Talonarios para Rendición",""),
	TAL_RENDICION_RECHAZADA("Rendición Rechazada",""),
	TAL_RENDICION_COMPLETADA("Rendición Completada",""),

	// Wf Valores
	VAL_NO_DISPONIBLE("No disponible",""),
	VAL_AVISO_PAGO_RECHAZADO("Aviso de Pago Rechazado",""),
	VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR("Aviso de Pago Pendiente Confirmar",""),
	VAL_BOLETA_PENDIENTE_CONCILIACION("Boleta Pendiente de Conciliación",""),
	VAL_BOLETA_PENDIENTE_AUTORIZAR("Boleta Pendiente Autorizar",""),
	VAL_BOLETA_RECHAZADA("Boleta Rechazada",""),
	VAL_DISPONIBLE("Disponible",""),
	VAL_USADO("Usado",""),
	VAL_SUSPENDIDO("Suspendido",""),
	VAL_ANULADO("Anulado",""),

	// Wf Descobros
	DES_IMPUTAR("Pendiente de imputar descobro",""),
	DES_DESCOBRO_PROCESO("Descobro en proceso",""),
	DES_AMBOS_COBRADORES("Pendiente reverso documentos y medios de pago en cobradores",""),
	DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC("Pendiente reverso documentos en cobradores y medios de pago en MIC",""),
	DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO("Pendiente reverso documentos en cobradores y medios de pago en CALIPSO",""),
	DES_DOCUMENTO_MIC_MEDIOS_COBRADORES("Pendiente reverso documentos en MIC y medios de pago en cobradores",""),
	DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES("Pendiente reverso documentos en CALIPSO y medios de pago en cobradores",""),
	DES_DOCUMENTO_COBRADORES("Pendiente reverso documentos en cobradores",""),
	DES_MEDIOS_COBRADORES("Pendiente reverso medios de pago en cobradores",""),
	DES_AMBOS_CALIPSO("Pendiente reverso medios de pago y documentos en Calipso",""),
	DES_DOCUMENTO_CALIPSO("Pendiente reverso documentos en Calipso",""),
	DES_MEDIOS_CALIPSO("Pendiente reverso medios de pago en Calipso",""),
	DES_AMBOS_MIC("Pendiente reverso medios de pago y documentos en MIC",""),
	DES_MEDIOS_MIC("Pendiente reverso medios de pago en MIC",""),
	DES_DOCUMENTO_MIC("Pendiente reverso documentos en MIC",""),
	DES_DESCOBRADO("Descobrado",""),
	DES_ANULADO("Descobro anulado",""),
	DES_EN_EDICION("Descobro en edición",""),
	DES_EN_ERROR("Descobro en error",""),
	DES_NO_DISPONIBLE("Descobro no disponible",""),
	DES_PENDIENTE("Descobro pendiente",""),
	DES_ERROR_EN_PRIMER_DESCOBRO("Error en primer descobro",""),
	DES_PENDIENTE_MIC("Descobro con respuesta pendiente de MIC",""),
	DES_PEND_PROCESAR_MIC("Descobro pendiente de procesar MIC",""),
	DES_COMPENSACION_PEND("Descobro de Compensación Pendiente",""),
	DES_PENDIENTE_CONFIRMACION_MANUAL("Descobro Pendiente de Confirmación Manual",""),
	
	// Wf Recibos Pre Impresos
	REC_PENDIENTE("Pendiente de Asociar",""),
	REC_COMPENSACION("Recibo con Compensación",""),
	REC_VALORES_CONCILIADOS("Valores Conciliados",""),
	REC_VALORES_PENDIENTE("Valores Pendientes de Conciliar",""),
	REC_ANULADO("Anulado",""),
  
	// Wf Valores Reversión
  	REV_PENDIENTE("Alta de Valor por Reversión Pendiente",""),
  	REV_PENDIENTE_CONFIRMAR("Alta de Valor por Reversión Pendiente de Confirmar",""),
  	REV_CONFIRMADA("Alta de Valor por Reversión Confirmada",""),
  	REV_RECHAZADA("Alta de Valor por Reversión Rechazada",""),
  	REV_SALDO_ACTUALIZADO("Actualización de Saldo en Valor por Reversión existente",""),
	
	// Wf Operaciones Masivas
	MAS_CONFIGURACION("Operacion Masiva en Configuracion",""),
	MAS_PENDIENTE_PROCESAR("Operacion Masiva Pendiente de Procesar",""),
	MAS_PROCESO_IMPUTACION("Operacion Masiva en Proceso de Imputacion",""),
	MAS_ERROR("Operacion Masiva en Error",""),
	MAS_PENDIENTE_APROBACION("Operacion Masiva Pendiente de Aprobacion",""),
	
	MAS_IMPUTADA("Operacion Masiva Imputada",""),
	MAS_RECHAZADA("Operacion Masiva Rechazada",""),
	MAS_ANULADA("Operacion Masiva Anulada",""),
	MAS_PARCIALMENTE_IMPUTADA("Operacion Masiva Parcialmente Imputada",""),
	
	// Wf de Legajos de Cheques Rechazados
	LGJ_NO_DISPONIBLE("No Disponible",""),
	LGJ_ABIERTO("Abierto",""),
	LGJ_EN_PROCESO_REVERSION_COBROS_RELACIONADOS("En Proceso de Reversión de Cobros Relacionados",""),
	LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA("En Proceso de Reembolso / Cheque Rechazado Pendiente de Entrega",""),
	LGJ_ENVIADO_A_LEGALES("Enviado a Legales",""),
	LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA("Reembolsado / Cheque Rechazado Pendiente de Entrega",""),
	LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA("Cerrado / Cheque Rechazado Pendiente de Entrega",""),
	LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_ENTREGADO("En proceso de Reembolso / Cheque Rechazado Entregado",""),
	LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO("Reembolsado / Cheque Rechazado Entregado",""),
	LGJ_ABIERTO_CON_CHEQUE_ENTREGADO("Abierto / Cheque Rechazado Entregado",""),
	LGJ_ANULADO("Anulado",""),
	LGJ_DESISTIDO("Desistido",""),
	LGJ_CERRADO_CON_CHEQUE_ENTREGADO("Cerrado / Cheque Rechazado Entregado","");
	
	String descripcion;
	String filtro;
	
	/**
	 * Recibe un String (debe ser de 3 letras) y lo compara con las primeras 3
	 * de los estados. Arma una lista con todas las coincidencias (ignora mayusculas)
	 * @param filtro
	 * @return
	 */
	public static List<Estado> listarEstados (String filtro){
		List<Estado> resultado = new ArrayList<Estado>();
		for (Estado e: Estado.values()){
			if (e.name().substring(0,3).equalsIgnoreCase(filtro)){
				resultado.add(e);
			}
		}
		return resultado;
	}
	
	/**
	 * 
	 * @param descripcion
	 */
	private Estado(String descripcion, String filtro) {
	    this.descripcion = descripcion;
	    this.filtro=filtro;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String filtro(){
		return this.filtro;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Estado getEnumByName(String name) {
		
		for (Estado estado : Estado.values()) {
			if (estado.name().equals(name)) {
				return estado;
			}
		}
		        
		return null; 
	}
	public static Estado getEnumByDescripcion(String descripcion) {
		
		for (Estado estado : Estado.values()) {
			if (estado.descripcion().equals(descripcion)) {
				return estado;
			}
		}
		        
		return null; 
	}
	
	public static List<Estado> obtenerEstadoporFiltro(String filtro) {
		List<Estado> listaEstados = new ArrayList<Estado>();
		for (Estado estado : Estado.values()) {
			if (estado.filtro().equals(filtro)){
				listaEstados.add(estado);
			}
		}
		return listaEstados;
	}
	
}
