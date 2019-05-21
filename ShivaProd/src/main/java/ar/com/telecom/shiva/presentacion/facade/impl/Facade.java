package ar.com.telecom.shiva.presentacion.facade.impl;

import ar.com.telecom.shiva.base.mapeadores.Mapeador;

public class Facade {

	Mapeador defaultMapeador;

	public Mapeador getDefaultMapeador() {
		return defaultMapeador;
	}

	public void setDefaultMapeador(Mapeador defaultMapeador) {
		this.defaultMapeador = defaultMapeador;
	}
	
}
