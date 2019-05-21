package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"cabecera",
					  "detalle",
					  "pieDePagina"})

public class Documento {

	private Cabecera cabecera;
	private Detalle detalle;
	private PieDePagina pieDePagina;
	
	@XmlElement
	public Cabecera getCabecera() {
		return cabecera;
	}
	public void setCabecera(Cabecera cabecera) {
		this.cabecera = cabecera;
	}
	@XmlElement
	public Detalle getDetalle() {
		return detalle;
	}
	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	@XmlElement(name="pie_de_pagina")
	public PieDePagina getPieDePagina() {
		return pieDePagina;
	}
	public void setPie(PieDePagina pieDePagina) {
		this.pieDePagina = pieDePagina;
	}
}
