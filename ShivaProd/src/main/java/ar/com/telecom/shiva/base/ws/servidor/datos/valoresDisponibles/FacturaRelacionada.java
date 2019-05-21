package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facturaRelacionada", propOrder = {"tipoComprobante","letraComprobante","numeroLegalComprobante"})
public class FacturaRelacionada {

	@XmlElement(name="tipo")
	private String tipoComprobante;
	@XmlElement(name="letra")
	private String letraComprobante;
	@XmlElement(name="numeroLegal")
	private String numeroLegalComprobante;
	
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getLetraComprobante() {
		return letraComprobante;
	}
	public void setLetraComprobante(String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}
	public String getNumeroLegalComprobante() {
		return numeroLegalComprobante;
	}
	public void setNumeroLegalComprobante(String numeroLegalComprobante) {
		this.numeroLegalComprobante = numeroLegalComprobante;
	}

	
}
