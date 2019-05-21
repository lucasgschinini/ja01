package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlanillaMovika extends Object implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private long numeroPlanilla;
	@XmlElement(required=true)
	private long numeroCaso;
	@XmlElement(required=true)
	private String usuarioGestorPlanilla;
	
	public long getNumeroPlanilla() {
		return numeroPlanilla;
	}
	public void setNumeroPlanilla(long numeroPlanilla) {
		this.numeroPlanilla = numeroPlanilla;
	}
	
	public long getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(long numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public String getUsuarioGestorPlanilla() {
		return usuarioGestorPlanilla;
	}
	public void setUsuarioGestorPlanilla(String usuarioGestorPlanilla) {
		this.usuarioGestorPlanilla = usuarioGestorPlanilla;
	}
	

}
