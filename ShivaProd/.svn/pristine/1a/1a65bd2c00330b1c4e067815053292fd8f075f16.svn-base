package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

/**
TIPO_TAREA:
   - Confirmar anulacion registro AVC
   - Autorizar Valor
   - Confirmar Aviso de Pago
   - Asignar Gestor de Talonario
   - Autorizar Rendicion de Talonario
   - Confirmar Alta de Valores a partir de Registro AVC

ESTADO:
	•	Para Valores
		o	Boleta Pendiente de Autorizar
		o	Aviso de Pago Pendiente de Confirmar
	•	Para Registros AVC
		o	Pendiente Confirmación de Anulación
		o	Pendiente de Confirmar Alta de Valor
	•	Para Talonarios
		o	Asignado a Supervisor de Talonarios
		o	Asignado a Administrador de Talonarios para Rendición
*/
public enum TipoTareaEnum {
    /**
    15 caracteres 
    123456789012345  
     */
	AUTR_VALOR("Autorizar Valor", "Boleta Pendiente de Autorizar"),
	REV_VALOR("Revisar Boleta Rechazada", "Boleta Rechazada"),
	CONF_AVISO_PAGO("Confirmar Aviso de Pago", "Aviso de Pago Pendiente de Confirmar"),
	REV_AVISO_PAGO("Revisar Aviso de Pago Rechazado", "Aviso de Pago Rechazado"),
	CONF_ALTA_V_AVC("Confirmar Alta de Valores a partir de Registro AVC", "Pendiente de Confirmar Alta de Valor"),
	REV_ALTA_V_AVC("Revisar Alta de Valor a partir de AVC Rechazada", "Alta de Valor Rechazada"),
	CONF_ALTA_V_REV("Confirmar Alta de Valores a partir de Reversión", "Alta de Valor por Reversión pendiente de confirmar"),
	REV_ALTA_V_REV("Revisar Alta de Valor por  Reversión Rechazada", "Alta de Valor por Reversión Rechazada"),
	CONF_ANUL_R_AVC("Confirmar anulacion registro AVC", "Pendiente Confirmación de Anulación"),
	REV_ANUL_R_AVC("Revisar Rechazo de Anulación de Registro AVC", "Pendiente de Conciliación"),
	ASIG_GESTOR_TAL("Asignar Gestor de Talonario", "Asignado a Supervisor de Talonarios"),
	REV_TAL("Revisar Asignación de Talonario Rechazada", "Derivado a Administrador de Talonarios por error en rango"),
	AUTR_REND_TAL("Autorizar Rendicion de Talonario", "Asignado a Administrador de Talonarios para Rendición"),
	REV_REND_TAL("Revisar Rendición Rechazada", "Rendición Rechazada"),
	COB_REV_DEB_IMP("Revisar resultado de validación de débitos importados",Estado.COB_EN_EDICION.descripcion()),
	COB_AUTR_COB("Autorizar Cobro", Estado.COB_PEND_REFERENTE_COBRANZA.descripcion()),
	COB_AUTR_COB_SUP_OP_ESP("Autorizar Cobro", Estado.COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES.descripcion()),
	COB_REV_RECH("Revisar Cobro Rechazado", Estado.COB_RECHAZADO.descripcion()),
	AUTR_COB("ESTE YA NO VA", "ESTE YA NO VA"),
	
	COB_REV_COB_ERR_TELECOM_HUAWEI("Revisar Resultado de cobro con Error ("+ SociedadEnum.TELECOM.getDescripcion() +"/"+ SistemaEnum.HUAWEI.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_TELECOM_SAP("Revisar Resultado de cobro con Error ("+ SociedadEnum.TELECOM.getDescripcion() +"/"+ SistemaEnum.SAP.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_PERSONAL_NEGOCIO_NET("Revisar Resultado de cobro con Error ("+ SociedadEnum.PERSONAL.getDescripcion() +"/"+ SistemaEnum.NEGOCIO_NET.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_PERSONAL_SAP("Revisar Resultado de cobro con Error ("+ SociedadEnum.PERSONAL.getDescripcion() +"/"+ SistemaEnum.SAP.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_FIBERCORP_OPEN("Revisar Resultado de cobro con Error ("+ SociedadEnum.FIBERCORP.getDescripcion() +"/"+ SistemaEnum.OPEN.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_FIBERCORP_SAP("Revisar Resultado de cobro con Error ("+ SociedadEnum.FIBERCORP.getDescripcion() +"/"+ SistemaEnum.SAP.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR_NEXTEL_NEXUS("Revisar Resultado de cobro con Error ("+ SociedadEnum.NEXTEL.getDescripcion() +"/"+ SistemaEnum.NEXUS.descripcion +")",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_ERR("Revisar Resultado de cobro con Error",Estado.COB_ERROR_COBRO.descripcion()),
	COB_REV_COB_APR("Revisar Resultado de Cobro con error en apropiación",Estado.COB_ERROR_APROPIACION.descripcion()),
	COB_REV_COB_DES("Revisar Cobro con error en desapropiación",Estado.COB_ERROR_DESAPROPIACION.descripcion()),
	COB_REV_COB_CON("Revisar Cobro con error en confirmación",Estado.COB_ERROR_CONFIRMACION.descripcion()),
	
	CONF_PEND_COBRO("Cobro Pendiente Confirmación","Cobro Pendiente Confirmación"),
	COB_RES_SIM_OK("Revisar Resultado de Simulación",Estado.COB_EN_EDICION.descripcion()),
	COB_RES_SIM_ERR("Revisar Resultado de Simulación",Estado.COB_EN_EDICION.descripcion()),
	DES_RES_SIM_OK("Revisar Resultado de Simulación de Reversión", Estado.DES_EN_EDICION.descripcion()),
	DES_RES_SIM_ERR("Revisar Resultado de Simulación de Reversión", Estado.DES_EN_EDICION.descripcion()),
	DES_IMP_1ER_ERR("Revisar Resultado de error en primera Reversión", Estado.DES_ERROR_EN_PRIMER_DESCOBRO.descripcion()),
	DES_IMP_ERR("Revisar Resultado de Reversión con Error", Estado.DES_EN_ERROR.descripcion()),
	DES_COMP_PEND("Reversión de Compensación Pendiente", "-"),
	DES_CONFIRMA_APL_MAN("Revertir Cobro Sistema Externo",Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.descripcion()),

	REV_OP_MAS_RECH("Revisar Operación Masiva Rechazada", Estado.MAS_RECHAZADA.descripcion()),
	APROBAR_OP_MAS("Aprobar Operación Masiva", Estado.MAS_PENDIENTE_APROBACION.descripcion()),
	OP_MAS_ERROR("Revisar Resultado de operación masiva En Error", Estado.MAS_ERROR.descripcion()),
	OP_MAS_PARCIAL("Revisar Resultado de operación masiva Parcialmente Imputada",Estado.MAS_PARCIALMENTE_IMPUTADA.descripcion()),
	
	COB_CONF_APLIC_MANUAL("Imputar cobro sistema externo", Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.descripcion()),
	
	COB_ERR_CONF_APLIC_MANUAL("Revisar Cobro con error en aplicación manual","-"),
	
	COB_REV_ERR_APLIC_MANUAL("Revisar Resultado de cobro con Error ","Error en imputación manual"),
	 
	//Desapropiar cobro sistema externo
	COB_DESAPRO_APLI_MANUAL("Desapropiar cobro sistema externo", Estado.COB_PEND_DESAPROPIACION_MANUAL_EXTERNA.descripcion());
	
	String descripcion;
	String estadoPendienteDescripcion;
	
	private TipoTareaEnum(String descripcion, String estadoPendienteDescripcion) {
	    this.descripcion = descripcion;
	    this.estadoPendienteDescripcion = estadoPendienteDescripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public String estadoPendienteDescripcion() {
	    return this.estadoPendienteDescripcion;
	}
	
	/**
	 * Devuelve el enumerador segun el nombre
	 * @param name
	 * @return
	 */
	public static TipoTareaEnum getEnumByName(String name){
		
		for (TipoTareaEnum tarea : TipoTareaEnum.values()) {
			if (tarea.name().equals(name)) {
				return tarea;
			}
		}
		return null; 
	}	
}
