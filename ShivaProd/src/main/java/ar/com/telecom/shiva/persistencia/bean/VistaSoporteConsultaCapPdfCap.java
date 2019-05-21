package ar.com.telecom.shiva.persistencia.bean;

import java.util.Date;

public class VistaSoporteConsultaCapPdfCap extends VistaSoporteConsultaPdfCap {

	private Date fechaEmision;
	private String numeroDocumentoSap;
	private String codigoSociedad;

	
	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	public VistaSoporteConsultaCapPdfCap() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the numeroDocumentoSap
	 */
	public String getNumeroDocumentoSap() {
		return numeroDocumentoSap;
	}
	/**
	 * @param numeroDocumentoSap the numeroDocumentoSap to set
	 */
	public void setNumeroDocumentoSap(String numeroDocumentoSap) {
		this.numeroDocumentoSap = numeroDocumentoSap;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VistaSoporteConsultaCapPdfCap [fechaEmision=" + fechaEmision
				+ ", cliente=" + cliente + ", nroDeComprobante="
				+ nroDeComprobante + ", moneda=" + moneda + ", importeOrigen="
				+ importeOrigen + ", saldoActual=" + saldoActual
				+ ", aCompensarEnPesos=" + aCompensarEnPesos
				+ ", montoReclamado=" + montoReclamado
				+ ", montoTotalACompensarEnPesos="
				+ montoTotalACompensarEnPesos + ", tipoCambio=" + tipoCambio
				+ ", importeACobrar=" + importeACobrar + ", sistema=" + sistema
				+ "]";
	}
}
