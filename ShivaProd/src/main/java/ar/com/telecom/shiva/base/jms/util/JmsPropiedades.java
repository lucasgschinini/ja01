package ar.com.telecom.shiva.base.jms.util;

import com.ibm.mq.constants.MQConstants;

public class JmsPropiedades {

	private String aplicacion;
	private String contrato;
	private String servicio;
	
	private String formatoMensaje = MQConstants.MQFMT_STRING;
	private int putApplType = MQConstants.MQAT_JAVA;
	//private int persistence = MQConstants.MQPER_NOT_PERSISTENT;
	private int persistence = MQConstants.MQPER_PERSISTENT;
	
	public int getPutApplType() {
		return putApplType;
	}
	public String getFormatoMensaje() {
		return formatoMensaje;
	}
	public int getPersistence() {
		return persistence;
	}
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
}
