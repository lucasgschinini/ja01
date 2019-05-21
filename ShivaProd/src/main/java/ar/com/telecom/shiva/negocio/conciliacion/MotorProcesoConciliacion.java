package ar.com.telecom.shiva.negocio.conciliacion;

import java.util.List;

import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;

public class MotorProcesoConciliacion {

	private List<ReglaConciliacionDto> reglas;

	public List<ReglaConciliacionDto> getReglas() {
		return reglas;
	}

	public void setReglas(List<ReglaConciliacionDto> reglas) {
		this.reglas = reglas;
	}
	
	
}
