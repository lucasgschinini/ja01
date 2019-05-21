package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"descripcion",
		              "importe",
		              "referencia1",
		              "referencia2",
		              "referencia3",
				      "origen", 
					  "monedaOrigen", 
					  "importeMonedaOrigen", 
					  "tipoCambio"})

public class Cheque {

	private String descripcion;
	private String importe;
	private String referencia1;
	private String referencia2;
	private String referencia3;
	
	private String origen;
	private String monedaOrigen;
	private String importeMonedaOrigen;
	private String tipoCambio;
	
	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@XmlElement
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	@XmlElement
	public String getReferencia1() {
		return referencia1;
	}
	public void setReferencia1(String referencia1) {
		this.referencia1 = referencia1;
	}
	@XmlElement
	public String getReferencia2() {
		return referencia2;
	}
	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}
	@XmlElement
	public String getReferencia3() {
		return referencia3;
	}
	public void setReferencia3(String referencia3) {
		this.referencia3 = referencia3;
	}
	
	/**
	 * @return the origen
	 */
	@XmlElement(name="origen")
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	/**
	 * @return the monedaOrigen
	 */
	@XmlElement(name="moneda_origen")
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	/**
	 * @param monedaOrigen the monedaOrigen to set
	 */
	public void setMonedaOrigen(String moneda) {
		this.monedaOrigen = moneda;
	}

	/**
	 * @return the importeMonedaOrigen
	 */
	@XmlElement(name="importe_moneda_ori")
	public String getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}
	/**
	 * @param importeMonedaOrigen the importeMonedaOrigen to set
	 */
	public void setImporteMonedaOrigen(String importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}

	/**
	 * @return the tipoCambio
	 */
	@XmlElement(name="tipo_cambio")
	public String getTipoCambio() {
		return tipoCambio;
	}
	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}	
}
