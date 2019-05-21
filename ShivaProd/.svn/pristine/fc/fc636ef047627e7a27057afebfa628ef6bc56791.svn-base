package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"mediosDePago","facturas","chequesRechazados"})
public class Detalle {

	private MedioDePago mediosDePago;
	private Facturas facturas;
	private ChequeRechazado chequesRechazados;
	
	@XmlElement(name="medios_de_pago")
	public MedioDePago getMediosDePago() {
		return mediosDePago;
	}
	public void setMediosDePago(MedioDePago mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
	
	@XmlElement
	public Facturas getFacturas() {
		return facturas;
	}
	public void setFacturas(Facturas facturas) {
		this.facturas = facturas;
	}
	@XmlElement(name="cheques_rechazados")
	public ChequeRechazado getChequesRechazados() {
		return chequesRechazados;
	}
	public void setChequesRechazados(ChequeRechazado chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}
}
