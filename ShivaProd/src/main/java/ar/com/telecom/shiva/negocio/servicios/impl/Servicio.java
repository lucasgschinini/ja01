package ar.com.telecom.shiva.negocio.servicios.impl;

import ar.com.telecom.shiva.base.mapeadores.Mapeador;

public abstract class Servicio {
	
	Mapeador defaultMapeador;

	public Mapeador getDefaultMapeador() {
		return defaultMapeador;
	}

	public void setDefaultMapeador(Mapeador defaultMapeador) {
		this.defaultMapeador = defaultMapeador;
	}

	
}
