package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transferencia", propOrder = {"idValor","importe","saldoDisponible","fechaValor","motivo","operacionAsociada",
						"observaciones","cliente","acuerdo","bancoOrigen",
						"numeroReferencia", "cuit"})
public class ValoresTransferencia {

	@XmlElement
	private String idValor;
	@XmlElement
	private String importe;
	@XmlElement
	private String saldoDisponible;
	@XmlElement
	private String fechaValor;
	@XmlElement
	private String motivo;
	@XmlElement
	private String operacionAsociada;
	@XmlElement
	private String observaciones;
	@XmlElement
	private String cliente;
	@XmlElement
	private String acuerdo;
	@XmlElement
	private String bancoOrigen;
	@XmlElement
	private String numeroReferencia;
	@XmlElement
	private String cuit;
	
	public String getIdValor() {
		return idValor;
	}
	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public String getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(String fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getOperacionAsociada() {
		return operacionAsociada;
	}
	public void setOperacionAsociada(String operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	public String getBancoOrigen() {
		return bancoOrigen;
	}
	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
}
