package ar.com.telecom.shiva.negocio.dto.cobros;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;

@SuppressWarnings("serial")
public class MicDetalleFacturaDto extends JMS {

	private TipoPagoEnum tipoOperacion;
	private TipoDocumentoEnum tipoDocumento;
	private String referenciaFactura;
	private String fechaValor; /**AAAAMMDD*/
	private String acuerdoFacturacionIntereses;
	private BigDecimal importe;
	private String queHacerConLosIntereses;
	private BigDecimal importeBonificacionIntereses;
	private SiNoEnum queHacerConLosTerceros;
	private BigDecimal montoAcumuladoSimulacion;
	private Date fechaCobranzaAcumuladaSimulacion;
	
	public TipoPagoEnum getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoPagoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getReferenciaFactura() {
		return referenciaFactura;
	}
	public void setReferenciaFactura(String referenciaFactura) {
		this.referenciaFactura = referenciaFactura;
	}
	public String getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(String fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getAcuerdoFacturacionIntereses() {
		return acuerdoFacturacionIntereses;
	}
	public void setAcuerdoFacturacionIntereses(String acuerdoFacturacionIntereses) {
		this.acuerdoFacturacionIntereses = acuerdoFacturacionIntereses;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getQueHacerConLosIntereses() {
		return queHacerConLosIntereses;
	}
	public void setQueHacerConLosIntereses(
			String string) {
		this.queHacerConLosIntereses = string;
	}
	public SiNoEnum getQueHacerConLosTerceros() {
		return queHacerConLosTerceros;
	}
	public void setQueHacerConLosTerceros(SiNoEnum queHacerConLosTerceros) {
		this.queHacerConLosTerceros = queHacerConLosTerceros;
	}
	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public BigDecimal getImporteBonificacionIntereses() {
		return importeBonificacionIntereses;
	}
	public void setImporteBonificacionIntereses(
			BigDecimal importeBonificacionIntereses) {
		this.importeBonificacionIntereses = importeBonificacionIntereses;
	}
	public BigDecimal getMontoAcumuladoSimulacion() {
		return montoAcumuladoSimulacion;
	}
	public void setMontoAcumuladoSimulacion(BigDecimal montoAcumuladoSimulacion) {
		this.montoAcumuladoSimulacion = montoAcumuladoSimulacion;
	}
	/**
	 * @return the fechaCobranzaAcumuladaSimulacion
	 */
	public Date getFechaCobranzaAcumuladaSimulacion() {
		return fechaCobranzaAcumuladaSimulacion;
	}
	/**
	 * @param fechaCobranzaAcumuladaSimulacion the fechaCobranzaAcumuladaSimulacion to set
	 */
	public void setFechaCobranzaAcumuladaSimulacion(
			Date fechaCobranzaAcumuladaSimulacion) {
		this.fechaCobranzaAcumuladaSimulacion = fechaCobranzaAcumuladaSimulacion;
	}
}
