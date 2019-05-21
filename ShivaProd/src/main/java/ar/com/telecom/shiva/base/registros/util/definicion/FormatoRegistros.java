package ar.com.telecom.shiva.base.registros.util.definicion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.telecom.shiva.base.utils.Validaciones;

public class FormatoRegistros {

	protected String descripcion;
	protected int lontitudFijaCabeceraPie;
	protected int lontitudFijaCuerpoDeserializable;
	protected int lontitudFijaCuerpoSerializable;
	
	protected int cantidadRegistrosCabeceraPie;
	protected int cantidadRegistrosCuerpoDeserializable;
	protected int cantidadRegistrosCuerpoSerializable;
	
	protected List<Campo> serializable;
	protected List<Campo> deserializable;
	
	/**
	 * Me devuelve la lista ordenada por orden
	 */
	public List<Campo> getCamposSerializableOrdenada() {
		List<Campo> list = new ArrayList<Campo>();
		if (Validaciones.isCollectionNotEmpty(serializable)) {
			for (Campo campo : serializable) {
				list.add(campo);
			}
			Collections.sort(list,
					new CampoCompPorOrden());
		}
		return list;
	}
	
	/**
	 * Me devuelve la lista ordenada por orden
	 */
	public List<Campo> getCamposDeserializableOrdenada() {
		List<Campo> list = new ArrayList<Campo>();
		if (Validaciones.isCollectionNotEmpty(deserializable)) {
			for (Campo campo : deserializable) {
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
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Campo> getSerializable() {
		return serializable;
	}

	public void setSerializable(List<Campo> serializable) {
		this.serializable = serializable;
	}

	public List<Campo> getDeserializable() {
		return deserializable;
	}

	public void setDeserializable(List<Campo> deserializable) {
		this.deserializable = deserializable;
	}

	public int getLontitudFijaCabeceraPie() {
		return lontitudFijaCabeceraPie;
	}

	public void setLontitudFijaCabeceraPie(int lontitudFijaCabeceraPie) {
		this.lontitudFijaCabeceraPie = lontitudFijaCabeceraPie;
	}

	public int getLontitudFijaCuerpoDeserializable() {
		return lontitudFijaCuerpoDeserializable;
	}

	public void setLontitudFijaCuerpoDeserializable(
			int lontitudFijaCuerpoDeserializable) {
		this.lontitudFijaCuerpoDeserializable = lontitudFijaCuerpoDeserializable;
	}

	public int getLontitudFijaCuerpoSerializable() {
		return lontitudFijaCuerpoSerializable;
	}

	public void setLontitudFijaCuerpoSerializable(int lontitudFijaCuerpoSerializable) {
		this.lontitudFijaCuerpoSerializable = lontitudFijaCuerpoSerializable;
	}

	public int getCantidadRegistrosCabeceraPie() {
		return cantidadRegistrosCabeceraPie;
	}

	public void setCantidadRegistrosCabeceraPie(int cantidadRegistrosCabeceraPie) {
		this.cantidadRegistrosCabeceraPie = cantidadRegistrosCabeceraPie;
	}

	public int getCantidadRegistrosCuerpoDeserializable() {
		return cantidadRegistrosCuerpoDeserializable;
	}

	public void setCantidadRegistrosCuerpoDeserializable(
			int cantidadRegistrosCuerpoDeserializable) {
		this.cantidadRegistrosCuerpoDeserializable = cantidadRegistrosCuerpoDeserializable;
	}

	public int getCantidadRegistrosCuerpoSerializable() {
		return cantidadRegistrosCuerpoSerializable;
	}

	public void setCantidadRegistrosCuerpoSerializable(
			int cantidadRegistrosCuerpoSerializable) {
		this.cantidadRegistrosCuerpoSerializable = cantidadRegistrosCuerpoSerializable;
	}
}