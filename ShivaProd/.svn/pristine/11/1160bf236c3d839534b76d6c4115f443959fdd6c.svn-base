package ar.com.telecom.shiva.base.jms.util.definicion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.com.telecom.shiva.base.comparador.CampoCompPorOrden;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class FormatoMensajeJMS {

	protected String descripcion;
	protected int lontitudFijaCabeceraSerializable;
	protected int lontitudFijaCabeceraDeserializableSync;
	protected int lontitudFijaCabeceraDeserializableAsync;
	
	protected int lontitudFijaMensajeSerializable;
	protected int lontitudFijaMensajeDeserializable;
	protected int lontitudFijaMensajeDeserializableError;
	
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

	public int getLontitudFijaMensajeSerializable() {
		return lontitudFijaMensajeSerializable;
	}

	public void setLontitudFijaMensajeSerializable(
			int lontitudFijaMensajeSerializable) {
		this.lontitudFijaMensajeSerializable = lontitudFijaMensajeSerializable;
	}

	public int getLontitudFijaMensajeDeserializable() {
		return lontitudFijaMensajeDeserializable;
	}

	public void setLontitudFijaMensajeDeserializable(
			int lontitudFijaMensajeDeserializable) {
		this.lontitudFijaMensajeDeserializable = lontitudFijaMensajeDeserializable;
	}

	public int getLontitudFijaCabeceraSerializable() {
		return lontitudFijaCabeceraSerializable;
	}

	public void setLontitudFijaCabeceraSerializable(
			int lontitudFijaCabeceraSerializable) {
		this.lontitudFijaCabeceraSerializable = lontitudFijaCabeceraSerializable;
	}

	public int getLontitudFijaMensajeDeserializableError() {
		return lontitudFijaMensajeDeserializableError;
	}

	public void setLontitudFijaMensajeDeserializableError(
			int lontitudFijaMensajeDeserializableError) {
		this.lontitudFijaMensajeDeserializableError = lontitudFijaMensajeDeserializableError;
	}

	public int getLontitudFijaCabeceraDeserializableSync() {
		return lontitudFijaCabeceraDeserializableSync;
	}

	public void setLontitudFijaCabeceraDeserializableSync(
			int lontitudFijaCabeceraDeserializableSync) {
		this.lontitudFijaCabeceraDeserializableSync = lontitudFijaCabeceraDeserializableSync;
	}

	public int getLontitudFijaCabeceraDeserializableAsync() {
		return lontitudFijaCabeceraDeserializableAsync;
	}

	public void setLontitudFijaCabeceraDeserializableAsync(
			int lontitudFijaCabeceraDeserializableAsync) {
		this.lontitudFijaCabeceraDeserializableAsync = lontitudFijaCabeceraDeserializableAsync;
	}
}
