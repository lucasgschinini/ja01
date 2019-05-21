package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facturaMIC")
public class FacturaMIC extends Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private String idReferencia;
	
	@XmlElement(required=true)
	private String accionSobreTerceros;
	
	@XmlElement(required=true)
	private InformacionAdicionalTagetikMIC informacionAdicionalTagetikMIC;
	
	
	public String getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getAccionSobreTerceros() {
		return accionSobreTerceros;
	}
	public void setAccionSobreTerceros(String accionSobreTerceros) {
		this.accionSobreTerceros = accionSobreTerceros;
	}
	public InformacionAdicionalTagetikMIC getInformacionAdicionalTagetikMIC() {
		return informacionAdicionalTagetikMIC;
	}
	public void setInformacionAdicionalTagetikMIC(
			InformacionAdicionalTagetikMIC informacionAdicionalTagetikMIC) {
		this.informacionAdicionalTagetikMIC = informacionAdicionalTagetikMIC;
	}
}
