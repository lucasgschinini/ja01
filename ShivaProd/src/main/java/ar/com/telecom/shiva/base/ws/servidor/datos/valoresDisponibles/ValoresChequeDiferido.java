package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chequeDiferido", propOrder = {"idValor","importe","saldoDisponible","fechaValor","motivo","operacionAsociada",
						"observaciones","cliente","origen","acuerdo","bancoOrigen",
						"numeroBoleta", "numeroCheque"})
public class ValoresChequeDiferido {

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
	private String origen;
	@XmlElement
	private String acuerdo;
	@XmlElement
	private String bancoOrigen;
	@XmlElement
	private String numeroBoleta;
	@XmlElement
	private String numeroCheque;
	
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
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
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
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	
	

}
