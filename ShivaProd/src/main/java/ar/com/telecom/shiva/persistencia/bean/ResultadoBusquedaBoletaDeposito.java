package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ResultadoBusquedaBoletaDeposito {

	private String idBoleta;
	private String idAcuerdo;
	private String idClienteLegado;
	private BigDecimal importe;	
	private Long   nroBoleta;
	private Long   nroCheque;
	private Long   idBancoOrigen;
	private Date   fechaDeposito;
	
	
	public String getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(String idBoleta) {
		this.idBoleta = idBoleta;
	}
	public String getIdAcuerdo() {
		return idAcuerdo;
	}
	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getNroBoleta() {
		return nroBoleta;
	}
	public void setNroBoleta(Long nroBoleta) {
		this.nroBoleta = nroBoleta;
	}
	public Long getNroCheque() {
		return nroCheque;
	}
	public void setNroCheque(Long nroCheque) {
		this.nroCheque = nroCheque;
	}
	public Long getIdBancoOrigen() {
		return idBancoOrigen;
	}
	public void setIdBancoOrigen(Long idBancoOrigen) {
		this.idBancoOrigen = idBancoOrigen;
	}
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	
	


}
