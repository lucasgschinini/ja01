package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.IdDocumento;

@XmlAccessorType(XmlAccessType.FIELD)
public class Factura implements Serializable,Comparable<Factura> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true)
	private IdDocumento	idDocumento;
	@XmlElement(required=true)
	private Long		idClienteLegado;
	@XmlElement(required=true)
	private BigDecimal  importeOriginal;
	@XmlElement(required=true)
	private BigDecimal  importeCobrar;
	@XmlElement(required=true)
	private BigDecimal  saldoActual;
	@XmlElement(required=true)
	private Long		fechaValor;
	@XmlElement(required=true)
	private String		acuerdoFacturacionTransladoCargo;
	@XmlElement(required=true)
	private int			porcentajeBonificacionIntereses;
	@XmlElement(required=true)
	private String		accionSobreIntereses;
	
	
	/**
	 * @return the idDocumento
	 */
	public IdDocumento getIdDocumento() {
		return idDocumento;
	}


	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}


	/**
	 * @return the idClienteLegado
	 */
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}


	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}


	/**
	 * @return the importeOriginal
	 */
	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}


	/**
	 * @param importeOriginal the importeOriginal to set
	 */
	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
	}


	/**
	 * @return the importeCobrar
	 */
	public BigDecimal getImporteCobrar() {
		return importeCobrar;
	}


	/**
	 * @param importeCobrar the importeCobrar to set
	 */
	public void setImporteCobrar(BigDecimal importeCobrar) {
		this.importeCobrar = importeCobrar;
	}


	/**
	 * @return the saldoActual
	 */
	public BigDecimal getSaldoActual() {
		return saldoActual;
	}


	/**
	 * @param saldoActual the saldoActual to set
	 */
	public void setSaldoActual(BigDecimal saldoActual) {
		this.saldoActual = saldoActual;
	}


	/**
	 * @return the fechaValor
	 */
	public Long getFechaValor() {
		return fechaValor;
	}


	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Long fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the acuerdoFacturacionTransladoCargo
	 */
	public String getAcuerdoFacturacionTransladoCargo() {
		return acuerdoFacturacionTransladoCargo;
	}


	/**
	 * @param acuerdoFacturacionTransladoCargo the acuerdoFacturacionTransladoCargo to set
	 */
	public void setAcuerdoFacturacionTransladoCargo(String acuerdoFacturacionTransladoCargo) {
		this.acuerdoFacturacionTransladoCargo = acuerdoFacturacionTransladoCargo;
	}


	/**
	 * @return the porcentajeBonificacionIntereses
	 */
	public int getPorcentajeBonificacionIntereses() {
		return porcentajeBonificacionIntereses;
	}


	/**
	 * @param porcentajeBonificacionIntereses the porcentajeBonificacionIntereses to set
	 */
	public void setPorcentajeBonificacionIntereses(int porcentajeBonificacionIntereses) {
		this.porcentajeBonificacionIntereses = porcentajeBonificacionIntereses;
	}


	/**
	 * @return the accionSobreIntereses
	 */
	public String getAccionSobreIntereses() {
		return accionSobreIntereses;
	}


	/**
	 * @param accionSobreIntereses the accionSobreIntereses to set
	 */
	public void setAccionSobreIntereses(String accionSobreIntereses) {
		this.accionSobreIntereses = accionSobreIntereses;
	}


	@Override
	public int compareTo(Factura o) {
		if (this.fechaValor.compareTo(o.fechaValor)==0){
			return this.importeCobrar.compareTo(o.importeCobrar);
		} else {
			return this.fechaValor.compareTo(o.fechaValor);
		}
	}
}
