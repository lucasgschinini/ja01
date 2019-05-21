package ar.com.telecom.shiva.base.jms.util.definicion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.telecom.shiva.base.comparador.CampoCompPorOrden;
import ar.com.telecom.shiva.base.utils.Validaciones;


public class Campo {

	protected int orden;
	protected String nombre;
	protected int longitud;
	protected TipoDatoEnum tipoDato;
	protected List<Campo> agrupador;
	
	/**
	 * Me devuelve la lista ordenada por orden
	 */
	public List<Campo> getAgrupadorOrdenada() {
		List<Campo> list = new ArrayList<Campo>();
		if (Validaciones.isCollectionNotEmpty(agrupador)) {
			for (Campo campo : agrupador) {
				list.add(campo);
			}
			Collections.sort(list,
					new CampoCompPorOrden());
		}
		return list;
	}
	
	/***********************************************************************
	 * Getters & Setters
	 ***********************************************************************/
	
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public TipoDatoEnum getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(TipoDatoEnum tipoDato) {
		this.tipoDato = tipoDato;
	}
	public List<Campo> getAgrupador() {
		return agrupador;
	}
	public void setAgrupador(List<Campo> agrupador) {
		this.agrupador = agrupador;
	}
}
