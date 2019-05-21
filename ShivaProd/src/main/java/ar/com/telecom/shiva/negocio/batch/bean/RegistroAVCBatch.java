package ar.com.telecom.shiva.negocio.batch.bean;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.dto.Batch;

public class RegistroAVCBatch extends Batch{

	private static final long serialVersionUID = 1L;
	
	private String acuerdo;
	private String idAcuerdo;
	private String codigoCliente;
	private BigDecimal importe;
	private String tipoValor;
	
	// Log caracteres especiales removidos
	private String logCaractEspecRemovidos = "";
	
	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public String getLogCaractEspecRemovidos() {
		return logCaractEspecRemovidos;
	}

	public void setLogCaractEspecRemovidos(String logCaractEspecRemovidos) {
		this.logCaractEspecRemovidos = logCaractEspecRemovidos;
	}
	
}
