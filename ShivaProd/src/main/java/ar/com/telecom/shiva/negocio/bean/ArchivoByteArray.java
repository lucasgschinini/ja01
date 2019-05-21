package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;

public class ArchivoByteArray implements Serializable {
	private static final long serialVersionUID = 20170125L;

	private byte byteArray[];
	private String nombreArchivo;

	public ArchivoByteArray() {
	}

	/**
	 * @return the byteArray
	 */
	public byte[] getByteArray() {
		return byteArray;
	}

	/**
	 * @param byteArray the byteArray to set
	 */
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	

}
